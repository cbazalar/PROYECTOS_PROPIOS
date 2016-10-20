CREATE OR REPLACE PACKAGE RUV_PKG_PROCE is

/**************************************************************************
    Descripcion         : RUV_PR_CANTI_ASIGN_NULOS_XDIAS
                          Procedimiento que devuelve el numero de rangos a Asignar Nulos opcion por dias
    Fecha Creacion      : 28/06/2010
    Parametros Entrada:
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
        psNumeroAsigna OUT  : Devuelve el resultado
    Autor               : Dennys Oliva Iriate
  ***************************************************************************/
 PROCEDURE RUV_PR_CANTI_ASIGN_NULOS_XDIAS(psOidTipoDocumento varchar2,
                                         psNumeroSerie       varchar2,
                                         psCodigoSubacceso   varchar2,
                                         psFechaInicio       varchar2,
                                         psFechaFin          varchar2,
                                         psNumeroAsigna  OUT VARCHAR2);

/**************************************************************************
  Descripcion         : RUV_PR_PROCE_ASIGN_NULOS_XDIAS
                        Proceso de Asignar Nulos opcion por dias
  Fecha Creacion      : 26/08/2015
  Parametros Entrada:
      psOidTipoDocumento  : Oid Tipo de Documento
      psNumeroSerie       : Numero de serie
      psCodigoSubacceso   : Codigo de subacceso
      psFechaInicio       : Fecha de inicio
      psFechaFin          : Fecha de fin
  Autor               : Karina Valencia
***************************************************************************/
PROCEDURE RUV_PR_PROCE_ASIGN_NULOS_XDIAS(psCodigoPais       varchar2,
                                         psOidTipoDocumento varchar2,
                                         psNumeroSerie      varchar2,
                                         psCodigoSubacceso  varchar2,
                                         psFechaInicio      varchar2,
                                         psFechaFin         varchar2);

/**************************************************************************
    Descripcion         : RUV_FN_PROCE_ASIGN_NULOS_RANGO
                          Procedimiento que devuelve el numero de rangos a Asignar Nulos opcion por rango
    Fecha Creacion      : 28/06/2010
    Parametros Entrada:
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
        psRangoInicio       : Rango Inicio
        psRangoFin          : Rango Fin
        psNumeroAsigna OUT  : Devuelve el resultado
    Autor               : Dennys Oliva Iriate
  ***************************************************************************/
 PROCEDURE RUV_PR_CANTI_ASIGN_NULOS_RANGO(psOidTipoDocumento varchar2,
                                         psNumeroSerie      varchar2,
                                         psCodigoSubacceso  varchar2,
                                         psFechaInicio      varchar2,
                                         psFechaFin         varchar2,
                                         psRangoInicio      varchar2,
                                         psRangoFin         varchar2,
                                         psNumeroAsigna OUT varchar2);

/**************************************************************************
  Descripcion         : RUV_PR_PROCE_ASIGN_NULOS_RANGO
                        Proceso de Asignar Nulos opcion por dias
  Fecha Creacion      : 28/06/2010
  Parametros Entrada:
      psCodigoPais        : psCodigoPais
      psOidTipoDocumento  : Oid Tipo de Documento
      psNumeroSerie       : Numero de serie
      psCodigoSubacceso   : Codigo de subacceso
      psFechaInicio       : Fecha de inicio
      psFechaFin          : Fecha de fin
  Autor               : Dennys Oliva Iriate
***************************************************************************/
  PROCEDURE RUV_PR_PROCE_ASIGN_NULOS_RANGO(psCodigoPais       varchar2,
                                           psOidTipoDocumento varchar2,
                                           psNumeroSerie      varchar2,
                                           psCodigoSubacceso  varchar2,
                                           psFechaInicio      varchar2,
                                           psFechaFin         varchar2,
                                           psRangoInicio      varchar2,
                                           psRangoFin         varchar2);
 /******************************************************************************
  Descripcion         : RUV_PR_GENER_DOCUM_CONTA
                        Proceso que genera la data para el reporte de Documentos
                        Contables
  Fecha Creacion      : 30/06/2010
  Parametros Entrada:
      psOidTipoDocumento  : Oid Tipo de Documento
      psNumeroSerie       : Numero de serie
      psCodigoSubacceso   : Codigo de subacceso
      psFechaInicio       : Fecha de inicio
      psFechaFin          : Fecha de fin
  Autor               : Jesse James Rios Franco
 *******************************************************************************/
   PROCEDURE RUV_PR_GENER_DOCUM_CONTA(psOidTipoDocumento NUMBER,
                                      psFechaInicio VARCHAR2,
                                      psFechaFin VARCHAR2,
                                      psCodigoSubacceso VARCHAR2,
                                      psNumeroSerie VARChAR2);


/***************************************************************************
Descripcion       : Valida y Devuelve Nro de Serie del Documento de Pago
Fecha Creacion    : 14/07/2010
Parametros Entrada:
      pnoidTipoDocumento  : Tipo de Documento de Pago
      psNrodocumento   : Numero de Documento de Identidad
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_VALID_NUMER_SERIE(
   pnoidTipoDocumento  VARCHAR2,
   psNrodocumento      VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Valida y Devuelve Nro de Serie del Documento de Pago
                    de Referencia
Fecha Creacion    : 18/08/2010
Parametros Entrada:
      pnoidTipoDocumento  : Tipo de Documento de Pago
      psNrodocumento   : Numero de Documento de Identidad
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_VALID_NUMER_SERIE_REFER(
   psNrodocumento      VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Valida y Devuelve Nro de documento DE IDENTIDAD
Fecha Creacion    : 14/07/2010
Parametros Entrada:
      psTipoDocumSunat  : Tipo de Documento de Sunat
      psNrodocumento    : Numero de Documento de Identidad
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_VALID_DOCUM_IDENTI(
   psTipoDocumSunat VARCHAR2,
   psNrodocumento   VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Devuelve el codigo de homologacion de sunat de un documento DE IDENTIDAD
Fecha Creacion    : 14/07/2010
Parametros Entrada:
      pnOidComprobantePago : Oid del comprobante de pago
      pnOidSubAcceso  : Oid del SubAcceso
      pnOidTipoDocum  : Oid Tipo de Documento
      pnOidPais       : Oid Pais
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_DEVUE_HOMO_TIPO_DOCUM(
   pnOidComprobantePago   NUMBER,
   pnOidSubAcceso   NUMBER,
   pnOidTipoDocum   NUMBER,
   pnOidPais        NUMBER
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Obtiene el monto Base Importe,verificando si es inafecto la
                    tasa
Fecha Creacion    : 14/07/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_OBTIE_BASE_IMPOR(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
RETURN  NUMBER ;

/***************************************************************************
Descripcion       : Obtiene el monto de las operaciones inafectas
Fecha Creacion    : 14/07/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_OBTIE_OPERA_INAFE(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
RETURN  NUMBER;

/******************************************************************************
  Descripcion         : RUV_PR_GENER_REPOR_SUNAT
                        Proceso que genera la data para el reporte RUV de Sunat
                        Contables
  Fecha Creacion      : 14/07/2010
  Parametros Entrada:
      psCodigoPais  : Codigo de Pais
      psCodigoPeriodoInformar: Periodo a Informar
      psCodigoPeriodoEnviar: Periodo a Enviar
      psTipoAccion  : Tipo de Accion
                      '0' Reiniciar Periodo a Informar
                      '1' Generar Periodo a Informar
  Autor             : Carlos Bazalar La Rosa
 *******************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_SUNAT(psCodigoPais VARCHAR2,
                                      psCodigoPeriodoInformar VARCHAR2,
                                      psCodigoPeriodoEnviar VARCHAR2,
                                      psTipoAccion VARCHAR2,
                                      psUsuario VARCHAR2);

/******************************************************************************
  Descripcion         : RUV_PR_GENER_REPOR_SUNAT_REINI
                        Proceso que genera la data para el reporte RUV de Sunat
                        Contables DESDE CERO
  Fecha Creacion      : 14/07/2010
  Parametros Entrada:
      psCodigoPais  : Codigo de Pais
      psCodigoPeriodoInformar: Periodo a Informar
      psCodigoPeriodoEnviar: Periodo a Enviar
  Autor             : Carlos Bazalar La Rosa
 *******************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_SUNAT_REINI(psCodigoPais VARCHAR2,
                                      psCodigoPeriodoInformar VARCHAR2,
                                      psCodigoPeriodoEnviar VARCHAR2,
                                      psUsuario VARCHAR2);

/******************************************************************************
  Descripcion         : RUV_PR_GENER_PLE5
                        Proceso que genera la data para el reporte RUV de Sunat
                        PLE5
  Fecha Creacion      : 13/02/2016
  Parametros Entrada:
      psCodigoPais  : Codigo de Pais
      psCodigoPeriodoInformar: Periodo a Informar
      psCodigoPeriodoEnviar: Periodo a Enviar
  Autor             : Rosalvina Ramirez Guardia
 *******************************************************************************/

PROCEDURE RUV_PR_GENER_PLE5(psCodigoPais VARCHAR2,
                                      psCodigoPeriodoInformar VARCHAR2,
                                      psCodigoPeriodoEnviar VARCHAR2,
                                      psUsuario VARCHAR2);                                      

/***************************************************************************
   Descripcion       : Procedimiento para validar errores del Reporte RUV Sunat
   Fecha Creacion    : 25/08/2010
   Autor             : Carlos Diaz Valverde
   Parametros        :
      psCodigoPeriodo    Codigo Periodo
      psCodigoPais  :    Codigo de Pais
   ***************************************************************************/
PROCEDURE RUV_PR_VALID_ERROR_REPOR_SUNAT (
   psCodigoPeriodo    VARCHAR2,
   psCodigoPais       VARCHAR2
 );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Sunat
Fecha Creacion    : 19/08/2010
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_GENER_REPOR_SUNAT_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    ) ;

/***************************************************************************
Descripcion       : Genera archivo temporal  correspondiente al Reporte Sunat
Fecha Creacion    : 19/08/2010
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_GENER_REPOR_SUNAT_EXCEL(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo TXT correspondiente al Reporte Sunat
Fecha Creacion    : 19/08/2010
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_GENER_REPOR_SUNAT_TXT(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Sunat Error
Fecha Creacion    : 01/09/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_REPOR_SUNAT_ERROR_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    ) ;

/***************************************************************************
Descripcion       : Genera archivo TXT correspondiente al Reporte Sunat Error
Fecha Creacion    : 01/09/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_REPOR_SUNAT_ERROR_TXT(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

  /******************************************************************************
  Descripcion         : RUV_PR_GENER_DOCUM_LEGDP
                        Proceso que genera la data en tabla Global Tempory de Documentos
                        Legales Duplicados
  Fecha Creacion      : 06/01/2011
  Parametros Entrada:
  psOidTipoDocumento  : Oid Tipo de Documento
  psFechaInicio       : Fecha de inicio
  psFechaFin          : Fecha de fin
  psOidSubacceso      : Oid Seg Sub Acceso
  psNumeroSerie       : Numero de serie
  psDescCanal         : Descripción de Canal
  psDescAcceso        : Descripción de Acceso
  psDescSubAcceso     : Descripción de Sub Acceso
  Autor               : Nicolás López
 *******************************************************************************/
 PROCEDURE RUV_PR_GENER_DOCUM_LEGDP(
   psOidTipoDocumento      VARCHAR2,
   psFechaInicio           VARCHAR2,
   psFechaFin              VARCHAR2,
   psOidSubAcceso          VARCHAR2,
   psNumeroSerie           VARCHAR2,
   psDescCanal             VARCHAR2,
   psDescAcceso            VARCHAR2,
   psDescSubAcceso         VARCHAR2
 );

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de Libro de Ventas
                      Mensuales
  Fecha Creacion    : 20/04/2011
  Autor             : Jesse James Rios Franco
  Parametros        :
              psCodigoPais : Codigo Pais
              psNombreArchivo : Nombre del Archivo
              pscodigoPeriodoInformar : codigo Periodo
              psDirectorio: Directorio en donde se encuentra el archivo
  ***************************************************************************/
 PROCEDURE RUV_PR_GENER_REPOR_LIBRO_CSV(psCodigoPais            VARCHAR2,
                                        psNombreArchivo         VARCHAR2,
                                        psTitulo                VARCHAR2,
                                        psCodigoInterfaz        VARCHAR2,
                                        psFechaInicio         VARCHAR2,
                                        psFechaFin            VARCHAR2,
                                        psDirectorio       OUT  VARCHAR2);

  /******************************************************************************
  Descripcion         : Proceso que genera la data en tabla Global Tempory para Numero
                        de Control de Documentos Legales
  Fecha Creacion      : 10/06/2011
  Autor               : Carlos Diaz Valverde
 *******************************************************************************/
 PROCEDURE RUV_PR_GENER_NUMER_CONDL(
   psOidTipoDocumento      VARCHAR2,
   psFechaInicio           VARCHAR2,
   psFechaFin              VARCHAR2,
   psOidSubAcceso          VARCHAR2,
   psNumeroSerie           VARCHAR2,
   psDescCanal             VARCHAR2,
   psDescAcceso            VARCHAR2,
   psDescSubAcceso         VARCHAR2
 );

  /**************************************************************************
   Descripcion         : Proceso de Asignar Nulos opcion por dias por pais
   Fecha Creacion      : 23/06/2011
   Autor               : Carlos Diaz Valverde
  ***************************************************************************/
  PROCEDURE RUV_PR_ASIGN_NULOS_XDIAS_XPAIS(
    psCodigoPais       varchar2,
    psOidTipoDocumento varchar2,
    psNumeroSerie      varchar2,
    psCodigoSubacceso  varchar2,
    psFechaInicio      varchar2,
    psFechaFin         varchar2
  );

/**************************************************************************
    Descripcion         : RUV_PR_CANTI_ASIGN_NULOS_XDIVE
                          Procedimiento que devuelve el numero de rangos a Asignar Nulos opcion por dias para Venezuela
    Fecha Creacion      : 26/03/2014
    Parametros Entrada:
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
        psNumeroAsigna OUT  : Devuelve el resultado
    Autor               : Aurelio Oviedo
  ***************************************************************************/
 PROCEDURE RUV_PR_CANTI_ASIGN_NULOS_XDIVE(psOidTipoDocumento varchar2,
                                         psNumeroSerie       varchar2,
                                         psCodigoSubacceso   varchar2,
                                         psFechaInicio       varchar2,
                                         psFechaFin          varchar2,
                                         psNumeroAsigna  OUT VARCHAR2);

/**************************************************************************
  Descripcion         : RUV_PR_PROCE_ASINU_XDIAS_VENEZ
                        Proceso de Asignar Nulos opcion por dias Venezuela
  Fecha Creacion      : 04/03/2014
  Parametros Entrada:
      psOidTipoDocumento  : Oid Tipo de Documento
      psNumeroSerie       : Numero de serie
      psCodigoSubacceso   : Codigo de subacceso
      psFechaInicio       : Fecha de inicio
      psFechaFin          : Fecha de fin
  Autor               : Aurelio Oviedo
***************************************************************************/
PROCEDURE RUV_PR_PROCE_ASINU_XDIAS_VENEZ(psCodigoPais       varchar2,
                                         psOidTipoDocumento varchar2,
                                         psNumeroSerie      varchar2,
                                         psCodigoSubacceso  varchar2,
                                         psFechaInicio      varchar2,
                                         psFechaFin         varchar2);

/**************************************************************************
    Descripcion         : RUV_PR_CANTI_ASIGN_NULOS_RANVE
                          Procedimiento que devuelve el numero de rangos a Asignar Nulos opcion por rango para Venezuela
    Fecha Creacion      : 26/03/2014
    Parametros Entrada:
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
        psRangoInicio       : Rango Inicio
        psRangoFin          : Rango Fin
        psNumeroAsigna OUT  : Devuelve el resultado
    Autor               : Aurelio Oviedo
  ***************************************************************************/
 PROCEDURE RUV_PR_CANTI_ASIGN_NULOS_RANVE(psOidTipoDocumento varchar2,
                                         psNumeroSerie      varchar2,
                                         psCodigoSubacceso  varchar2,
                                         psFechaInicio      varchar2,
                                         psFechaFin         varchar2,
                                         psRangoInicio      varchar2,
                                         psRangoFin         varchar2,
                                         psNumeroAsigna OUT varchar2);

/**************************************************************************
  Descripcion         : RUV_PR_PROCE_ASINU_RANGO_VENEZ
                        Proceso de Asignar Nulos opcion por dias Venezuela
  Fecha Creacion      : 04/03/2014
  Parametros Entrada:
      psCodigoPais        : psCodigoPais
      psOidTipoDocumento  : Oid Tipo de Documento
      psNumeroSerie       : Numero de serie
      psCodigoSubacceso   : Codigo de subacceso
      psFechaInicio       : Fecha de inicio
      psFechaFin          : Fecha de fin
  Autor               : Aurelio Oviedo
***************************************************************************/
  PROCEDURE RUV_PR_PROCE_ASINU_RANGO_VENEZ(psCodigoPais       varchar2,
                                           psOidTipoDocumento varchar2,
                                           psNumeroSerie      varchar2,
                                           psCodigoSubacceso  varchar2,
                                           psFechaInicio      varchar2,
                                           psFechaFin         varchar2,
                                           psRangoInicio      varchar2,
                                           psRangoFin         varchar2);

  /**************************************************************************
   Descripcion         : Proceso de Asignar Nulos opcion por dias por Venezuela
   Fecha Creacion      : 26/03/2014
   Autor               : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE RUV_PR_ASIGN_NULOS_XDIAS_XVENE(
    psCodigoPais       varchar2,
    psOidTipoDocumento varchar2,
    psNumeroSerie      varchar2,
    psCodigoSubacceso  varchar2,
    psFechaInicio      varchar2,
    psFechaFin         varchar2
  );
  
  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de
                      Detalle de NC por Marca y UN (Bolivia)
  Fecha Creacion    : 25/05/2015
  Autor             : Gonzalo Huertas
  Parametros        :
              psCodigoPais : Codigo Pais
              psNombreArchivo : Nombre del Archivo
              psFechaInicio: Fecha Inicio
              psFechaFin: Fecha Fin
              psDirectorio: Directorio en donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_DNCMU_CSV(
      psCodigoPais        VARCHAR2,
      psNombreArchivo     VARCHAR2,
      psTitulo            VARCHAR2,
      psFechaInicio       VARCHAR2,
      psFechaFin          VARCHAR2,
      psDirectorio        OUT  VARCHAR2
      );

  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de
                      Cabeceras de Facturas Anuladas (Bolivia)
  Fecha Creacion    : 25/05/2015
  Autor             : Gonzalo Huertas
  Parametros        :
              psCodigoPais : Codigo Pais
              psNombreArchivo : Nombre del Archivo
              psFechaInicio: Fecha Inicio
              psFechaFin: Fecha Fin
              psDirectorio: Directorio en donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_CABFA_CSV(
      psCodigoPais        VARCHAR2,
      psNombreArchivo     VARCHAR2,
      psTitulo            VARCHAR2,
      psFechaInicio       VARCHAR2,
      psFechaFin          VARCHAR2,
      psDirectorio        OUT  VARCHAR2
      );
      
  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de
                      Detalle Productos Atendidos x UN (Bolivia)
  Fecha Creacion    : 25/05/2015
  Autor             : Gonzalo Huertas
  Parametros        :
              psCodigoPais : Codigo Pais
              psNombreArchivo : Nombre del Archivo
              psFechaInicio: Fecha Inicio
              psFechaFin: Fecha Fin
              psDirectorio: Directorio en donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_DETAU_CSV(
      psCodigoPais        VARCHAR2,
      psNombreArchivo     VARCHAR2,
      psTitulo            VARCHAR2,
      psFechaInicio       VARCHAR2,
      psFechaFin          VARCHAR2,
      psDirectorio        OUT  VARCHAR2
      );  
   
    
  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de
                      Detalle de Nota de Debito por Marca y UN (Bolivia)
  Fecha Creacion    : 14/08/2015
  Autor             : Karina Valencia
  Parametros        :
              psCodigoPais : Codigo Pais
              psNombreArchivo : Nombre del Archivo
              psFechaInicio: Fecha Inicio
              psFechaFin: Fecha Fin
              psDirectorio: Directorio en donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_DNDMU_CSV(
      psCodigoPais        VARCHAR2,
      psNombreArchivo     VARCHAR2,
      psTitulo            VARCHAR2,
      psFechaInicio       VARCHAR2,
      psFechaFin          VARCHAR2,
      psDirectorio        OUT  VARCHAR2
      ); 
         
END RUV_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY RUV_PKG_PROCE is
  /* Declaracion de Variables */
  ln_sqlcode        NUMBER(10);
  ls_sqlerrm        VARCHAR2(1000);
  W_FILAS           NUMBER := 5000;
  OID_SUBACCESO_ESPECIAL   NUMBER:= 888;
  DEFAULT_FECHA      VARCHAR2(10):= '01/01/0001';
  DEFAULT_CHAR       VARCHAR2(1):= '-';
  DNI_SUNAT          VARCHAR2(1):= '1';
  RUC_SUNAT          VARCHAR2(1):= '6';
  CARNET_EXTRANJERIA VARCHAR2(1):= '4';
  LONGITUD_RUC       NUMBER(2) := 11;
  LONGITUD_DNI       NUMBER(2) := 8;
  RUC_ANULADAS       VARCHAR2(20):= '20100123763';
  CLIENTE_ANULADAS   VARCHAR2(40):= 'CETCO S.A.';
  TIPO_FACTURA_SUNAT VARCHAR2(2):= '01';
  TIPO_SERIE_FACTURA_REFE VARCHAR2(3):= '065';
  TIPO_SERIE_FACTURA_REFE_GRABA VARCHAR2(4):= '0065';


/******************************************************************************
  Descripcion         : RUV_PR_GENER_REPOR_SUNAT_REINI
                        Proceso que genera la data para el reporte RUV de Sunat
                        Contables DESDE CERO
  Fecha Creacion      : 14/07/2010
  Autor             : Carlos Bazalar La Rosa
 *******************************************************************************/
  PROCEDURE RUV_PR_AGRUPA_RANGO_SUNAT(psPeriodo VARCHAR2);


/**************************************************************************
    Descripcion         : RUV_PR_CANTI_ASIGN_NULOS_XDIAS
                          Procedimiento que devuelve el numero de rangos a Asignar Nulos opcion por dias
    Fecha Creacion      : 26/08/2015
    Parametros Entrada:
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
        psNumeroAsigna OUT  : Devuelve el resultado
    Autor               : Karina Valencia
  ***************************************************************************/
 PROCEDURE RUV_PR_CANTI_ASIGN_NULOS_XDIAS(psOidTipoDocumento varchar2,
                                          psNumeroSerie       varchar2,
                                          psCodigoSubacceso   varchar2,
                                          psFechaInicio       varchar2,
                                          psFechaFin          varchar2,
                                          psNumeroAsigna  OUT VARCHAR2)is
  ls_oid_subacceso number;
  ls_cont          number :=0;
  ls_cont_aux      number :=0;
  rows             NATURAL := 1000;   -- Number of rows to process at a time
  i                BINARY_INTEGER := 0;
  aux_ini          BINARY_INTEGER := 0;
  aux_fin          BINARY_INTEGER := 0;
  v_row_count      NUMBER := 0;
  v_row_count2     NUMBER := 0;

  TYPE t_nume_docu_lega  is table of GTT_RUV_ASIGN_NULOS.VAL_NUME_DOCU_LEGA%type;
  v_nume_docu_lega t_nume_docu_lega;

  type t_inicio is table of number(10) index by pls_integer;
  type t_fin    is table of number(10) index by pls_integer;
  lista_inicio   t_inicio;
  lista_fin      t_fin;

  cursor c_fin is
      select (b.val_nume_docu_lega - 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '1'
       order by b.val_nume_docu_lega;

  cursor c_inicio is
      select (b.val_nume_docu_lega + 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '2'
       order by b.val_nume_docu_lega;

  BEGIN
    -- obtiene el oid de subacceso
    SELECT s.oid_sbac into ls_oid_subacceso FROM SEG_SUBAC S where S.COD_SBAC = psCodigoSubacceso;

    insert into GTT_RUV_ASIGN_NULOS(
        ind_proc, 
        num_docu_cont_inte, 
        oid_regi, 
        val_nume_docu_lega, 
        val_nume_docu_lega_sgte)
      (select '1',
              a.NUM_DOCU_CONT_INTE,
              a.OID_REGI,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA + 1
         from FAC_REGIS_UNICO_VENTA a
        where a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and a.FEC_EMIS >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and a.FEC_EMIS < to_date(psFechaFin   , 'dd/mm/yyyy')+1
          and a.SBAC_OID_SBAC = ls_oid_subacceso
          and a.VAL_NUME_DOCU_LEGA is not null);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '1'
       and b.VAL_NUME_DOCU_LEGA in (select c.VAL_NUME_DOCU_LEGA_SGTE
                                      from GTT_RUV_ASIGN_NULOS c
                                     where c.ind_proc = '1');

    insert into GTT_RUV_ASIGN_NULOS(
        ind_proc, 
        num_docu_cont_inte, 
        oid_regi, 
        val_nume_docu_lega, 
        val_nume_docu_lega_sgte)
      (select '2',
              a.NUM_DOCU_CONT_INTE,
              a.oid_regi,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA - 1 as ante
         from FAC_REGIS_UNICO_VENTA a
        where a.tido_oid_tipo_docu = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and a.FEC_EMIS >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and a.FEC_EMIS < to_date(psFechaFin   , 'dd/mm/yyyy')+1
          and a.SBAC_OID_SBAC = ls_oid_subacceso);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '2'
       and b.VAL_NUME_DOCU_LEGA in
           (select c.VAL_NUME_DOCU_LEGA_SGTE
              from GTT_RUV_ASIGN_NULOS c
             where c.ind_proc = '2');

    -- Carga los arreglos
    OPEN c_fin;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_fin BULK COLLECT INTO v_nume_docu_lega
                              LIMIT rows;

      EXIT WHEN v_row_count = c_fin%ROWCOUNT;
      v_row_count := c_fin%ROWCOUNT;

      -- La posicion del arreglo empieza en 1
      for i in 1..v_nume_docu_lega.count loop
          lista_fin(i) := (v_nume_docu_lega(i));
          --dbms_output.put_line(i||'-> '||(v_nume_docu_lega(i)));
      end loop;

    END LOOP;
    CLOSE c_fin;

    OPEN c_inicio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_inicio BULK COLLECT INTO v_nume_docu_lega
                                  LIMIT rows;

      EXIT WHEN v_row_count2 = c_inicio%ROWCOUNT;
      v_row_count2 := c_inicio%ROWCOUNT;

      -- La posicion del arreglo empieza en 1
      for j in 1..v_nume_docu_lega.count loop
          lista_inicio(j) := (v_nume_docu_lega(j));
          --dbms_output.put_line(j||'-> '||(v_nume_docu_lega(j)));
      end loop;

    END LOOP;
    CLOSE c_inicio;

    -- Ejecuta el proceso de Asignar nulos
    for i in 1..(lista_fin.count-1) loop
        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);
        ls_cont_aux := (aux_fin - aux_ini) + 1;
        ls_cont := ls_cont + ls_cont_aux;
    end loop;
    IF ls_cont >= 0 THEN
        psNumeroAsigna := ls_cont;
    ELSE
        psNumeroAsigna := 'O';
    END IF;

 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_CANTI_ASIGN_NULOS_XDIAS ' || ls_sqlerrm);
  end RUV_PR_CANTI_ASIGN_NULOS_XDIAS;

  /**************************************************************************
    Descripcion         : RUV_PR_PROCE_ASIGN_NULOS_XDIAS
                          Proceso de Asignar Nulos opcion por dias
    Fecha Creacion      : 26/08/2015
    Parametros Entrada:
        psCodigoPais        : psCodigoPais
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
    Autor               : Karina Valencia
  ***************************************************************************/
PROCEDURE RUV_PR_PROCE_ASIGN_NULOS_XDIAS(psCodigoPais       varchar2,
                                           psOidTipoDocumento varchar2,
                                           psNumeroSerie      varchar2,
                                           psCodigoSubacceso  varchar2,
                                           psFechaInicio      varchar2,
                                           psFechaFin         varchar2) as

  TYPE t_nume_docu_lega  is table of GTT_RUV_ASIGN_NULOS.VAL_NUME_DOCU_LEGA%type;
  v_nume_docu_lega t_nume_docu_lega;

  type t_inicio is table of number(10) index by pls_integer;
  type t_fin    is table of number(10) index by pls_integer;
  lista_inicio   t_inicio;
  lista_fin      t_fin;

  cursor c_fin is
      select (b.val_nume_docu_lega - 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '1'
       order by b.val_nume_docu_lega;

  cursor c_inicio is
      select (b.val_nume_docu_lega + 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '2'
       order by b.val_nume_docu_lega;

  ls_oid_subacceso    SEG_SUBAC.Oid_Sbac%type;
  ls_oid_pais         SEG_PAIS.Oid_Pais%type;
  ls_oid_sociedad     seg_socie.oid_soci%type;
  ls_fecha            date;
  ls_oid_tasa_impu    ped_tasa_impue.oid_tasa_impu%type;

  rows NATURAL        := 1000;   -- Number of rows to process at a time
  i    BINARY_INTEGER := 0;
  j    BINARY_INTEGER := 0;
  aux_ini    BINARY_INTEGER := 0;
  aux_fin    BINARY_INTEGER := 0;
  v_row_count      NUMBER := 0;
  v_row_count2      NUMBER := 0;

  BEGIN
    -- obtiene el oid del pais
    select OID_PAIS into ls_oid_pais from SEG_PAIS where cod_pais = psCodigoPais;
    -- obtiene el oid de sociedad
    select ss.oid_soci
      into ls_oid_sociedad
      from seg_socie ss
     where ss.cod_soci in (select VAL_PARA
                             from bas_param_pais
                            where cod_pais = psCodigoPais
                              and cod_sist = 'RUV'
                              and nom_para = 'codigoSociedad');

    -- obtiene el oid de subacceso
    SELECT s.oid_sbac into ls_oid_subacceso FROM SEG_SUBAC S where S.COD_SBAC = psCodigoSubacceso;
    -- obtiene la fecha
    ls_fecha := to_date(psFechaInicio,'dd/mm/yyyy') + trunc((to_date(psFechaFin,'dd/mm/yyyy')-to_date(psFechaInicio,'dd/mm/yyyy'))/2);
    -- Obtiene el oid de tasa de impuesto
    select oid_tasa_impu
    into ls_oid_tasa_impu
    from ped_tasa_impue
    where val_indi_impu = (select VAL_PARA from bas_param_pais where cod_pais = psCodigoPais and cod_sist = 'RUV' and NOM_PARA = 'impuestoVentas');

    insert into GTT_RUV_ASIGN_NULOS(
        ind_proc, 
        num_docu_cont_inte, 
        oid_regi, 
        val_nume_docu_lega, 
        val_nume_docu_lega_sgte)
      (select '1',
              a.NUM_DOCU_CONT_INTE,
              a.OID_REGI,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA + 1
         from FAC_REGIS_UNICO_VENTA a
        where a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and a.FEC_EMIS >= to_date(psFechaInicio, 'dd/mm/yyyy')
          and a.FEC_EMIS < to_date(psFechaFin   , 'dd/mm/yyyy')+1
          and a.SBAC_OID_SBAC = ls_oid_subacceso
          and a.VAL_NUME_DOCU_LEGA is not null);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '1'
       and b.VAL_NUME_DOCU_LEGA in (select c.VAL_NUME_DOCU_LEGA_SGTE
                                      from GTT_RUV_ASIGN_NULOS c
                                     where c.ind_proc = '1');

    insert into GTT_RUV_ASIGN_NULOS(
        ind_proc, 
        num_docu_cont_inte, 
        oid_regi, 
        val_nume_docu_lega, 
        val_nume_docu_lega_sgte)
      (select '2',
              a.NUM_DOCU_CONT_INTE,
              a.oid_regi,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA - 1 as ante
         from FAC_REGIS_UNICO_VENTA a
        where a.tido_oid_tipo_docu = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and a.FEC_EMIS >= to_date(psFechaInicio, 'dd/mm/yyyy')
          and a.FEC_EMIS < to_date(psFechaFin   , 'dd/mm/yyyy')+1
          and a.SBAC_OID_SBAC = ls_oid_subacceso);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '2'
       and b.VAL_NUME_DOCU_LEGA in
           (select c.VAL_NUME_DOCU_LEGA_SGTE
              from GTT_RUV_ASIGN_NULOS c
             where c.ind_proc = '2');

    -- Carga los arreglos
    OPEN c_fin;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_fin BULK COLLECT INTO v_nume_docu_lega
                              LIMIT rows;

      EXIT WHEN v_row_count = c_fin%ROWCOUNT;
      v_row_count := c_fin%ROWCOUNT;

      for i in 1..v_nume_docu_lega.count loop
          lista_fin(i) := (v_nume_docu_lega(i));
          --dbms_output.put_line(i||'-> '||(v_nume_docu_lega(i)));
      end loop; 
    
    END LOOP;
    CLOSE c_fin;

    OPEN c_inicio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_inicio BULK COLLECT INTO v_nume_docu_lega
                                  LIMIT rows;

      EXIT WHEN v_row_count2 = c_inicio%ROWCOUNT;
      v_row_count2 := c_inicio%ROWCOUNT;

      for j in 1..v_nume_docu_lega.count loop
          lista_inicio(j) := (v_nume_docu_lega(j));
          --dbms_output.put_line(j||'-> '||(v_nume_docu_lega(j)));
      end loop;

    END LOOP;
    CLOSE c_inicio;
    
    DELETE RUV_ASIGN_NULOS_XDIRA_TEMPO;

    INSERT INTO RUV_ASIGN_NULOS_XDIRA_TEMPO(
           VAL_NUME_DOCU_LEGA,
           TIDO_OID_TIPO_DOCU,
           FEC_EMIS)
    (SELECT RR.VAL_NUME_DOCU_LEGA,
           RR.TIDO_OID_TIPO_DOCU,
           RR.FEC_EMIS
      FROM FAC_REGIS_UNICO_VENTA RR
     WHERE RR.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
       AND RR.FEC_EMIS >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
       AND RR.FEC_EMIS < TO_DATE(psFechaFin   , 'dd/mm/yyyy')+1);

    -- Ejecuta el proceso de Asignar nulos
    for i in 1..(lista_fin.count-1) loop
        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);
        for j in aux_ini..aux_fin loop

           begin
              SELECT max(FEC_EMIS)
                     INTO ls_fecha
                     FROM RUV_ASIGN_NULOS_XDIRA_TEMPO RR
              WHERE RR.val_nume_docu_lega in (aux_ini - 1)
                     AND RR.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
                     AND RR.FEC_EMIS >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
                     AND RR.FEC_EMIS < TO_DATE(psFechaFin   , 'dd/mm/yyyy')+1;
          exception
            when no_data_found then
              ls_fecha := to_date(psFechaInicio,'dd/mm/yyyy') + trunc((to_date(psFechaFin,'dd/mm/yyyy')-to_date(psFechaInicio,'dd/mm/yyyy'))/2);
          end;

           insert into FAC_REGIS_UNICO_VENTA(OID_REGI,
                                             PAIS_OID_PAIS,
                                             SOCI_OID_SOCI,
                                             SBAC_OID_SBAC,
                                             VAL_EJER_DOCU_INTE,
                                             NUM_DOCU_CONT_INTE,
                                             FEC_EMIS,
                                             VAL_BASE_IMPO,
                                             IMP_IMPU,
                                             IMP_TOTA,
                                             VAL_PUNT_EMIS,
                                             VAL_DOCU_INTE,
                                             VAL_NUME_IDEN_FISC,
                                             VAL_NUME_IDEN_NNAL,
                                             VAL_SERI_DOCU_REFE,
                                             VAL_NUME_DOCU_REFE,
                                             VAL_INTE_MORA,
                                             VAL_DESC,
                                             VAL_COMI,
                                             VAL_FLET,
                                             VAL_BASE_IMPO_NETO,
                                             VAL_NOM1,
                                             VAL_NOM2,
                                             VAL_APE1,
                                             VAL_APE2,
                                             CLIE_OID_CLIE,
                                             IND_ESTA,
                                             IND_TRAN_GRAT,
                                             IND_FACT_GRAT,
                                             DCCA_OID_CABE,
                                             TAIM_OID_TASA_IMPU,
                                             TIDO_OID_TIPO_DOCU,
                                             FEC_CIER,
                                             VAL_INDI_RUV,
                                             VAL_NUME_DOCU_LEGA,
                                             VAL_SERI_DOCU_LEGA,
                                             TIDO_TIPO_DOCU_REFE)
                                          values
                                            (FAC_RUVE_SEQ.NEXTVAL,
                                             ls_oid_pais,
                                             ls_oid_sociedad,
                                             ls_oid_subacceso,
                                             substr(ls_fecha,-2,2),
                                             NULL,
                                             ls_fecha,
                                             0,
                                             0,
                                             0,
                                             '000',
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             0,
                                             decode(substr(psCodigoPais,0,2),'VE','ANULADAS',null), --null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             1,
                                             0,
                                             0,
                                             null,
                                             ls_oid_tasa_impu,
                                             psOidTipoDocumento,
                                             null,
                                             'A',
                                             j,
                                             psNumeroSerie,
                                             null);
        end loop;
    end loop;

 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_PROCE_ASIGN_NULOS_XDIAS ' || ls_sqlerrm);

  END RUV_PR_PROCE_ASIGN_NULOS_XDIAS;


/**************************************************************************
    Descripcion         : RUV_FN_PROCE_ASIGN_NULOS_RANGO
                          Procedimiento que devuelve el numero de rangos a Asignar Nulos opcion por rango
    Fecha Creacion      : 26/08/2015
    Parametros Entrada:
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
        psRangoInicio       : Rango Inicio
        psRangoFin          : Rango Fin
        psNumeroAsigna OUT  : Devuelve el resultado
    Autor               : Karina Valencia
  ***************************************************************************/
 PROCEDURE RUV_PR_CANTI_ASIGN_NULOS_RANGO(psOidTipoDocumento varchar2,
                                         psNumeroSerie      varchar2,
                                         psCodigoSubacceso  varchar2,
                                         psFechaInicio      varchar2,
                                         psFechaFin         varchar2,
                                         psRangoInicio      varchar2,
                                         psRangoFin         varchar2,
                                         psNumeroAsigna OUT varchar2) is
  ls_oid_subacceso number;
  ls_cont          number:=0;
  ls_cont_aux      number:=0;
  rows             NATURAL := 1000;   -- Number of rows to process at a time
  i                BINARY_INTEGER := 0;
  aux_ini          BINARY_INTEGER := 0;
  aux_fin          BINARY_INTEGER := 0;
  v_row_count      NUMBER := 0;
  v_row_count2     NUMBER := 0;

  TYPE t_nume_docu_lega  is table of GTT_RUV_ASIGN_NULOS.VAL_NUME_DOCU_LEGA%type;
  v_nume_docu_lega t_nume_docu_lega;

  type t_inicio is table of number(10) index by pls_integer;
  type t_fin    is table of number(10) index by pls_integer;
  lista_inicio   t_inicio;
  lista_fin      t_fin;

  cursor c_fin is
      select (b.val_nume_docu_lega - 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '1'
       order by b.val_nume_docu_lega;

  cursor c_inicio is
      select (b.val_nume_docu_lega + 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '2'
       order by b.val_nume_docu_lega;

  BEGIN
    -- obtiene el oid de subacceso
    SELECT s.oid_sbac into ls_oid_subacceso FROM SEG_SUBAC S where S.COD_SBAC = psCodigoSubacceso;

    insert into GTT_RUV_ASIGN_NULOS(
        ind_proc, 
        num_docu_cont_inte, 
        oid_regi, 
        val_nume_docu_lega, 
        val_nume_docu_lega_sgte)
      (select '1',
              a.NUM_DOCU_CONT_INTE,
              a.OID_REGI,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA + 1
         from FAC_REGIS_UNICO_VENTA a
        where a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and a.FEC_EMIS >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and a.FEC_EMIS < to_date(psFechaFin   , 'dd/mm/yyyy')+1
          and a.val_nume_docu_lega >= to_number(psRangoInicio)
          and a.val_nume_docu_lega <= to_number(psRangoFin)
          and a.SBAC_OID_SBAC = ls_oid_subacceso
          and a.VAL_NUME_DOCU_LEGA is not null);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '1'
       and b.VAL_NUME_DOCU_LEGA in (select c.VAL_NUME_DOCU_LEGA_SGTE
                                      from GTT_RUV_ASIGN_NULOS c
                                     where c.ind_proc = '1');

    insert into GTT_RUV_ASIGN_NULOS(
        ind_proc, 
        num_docu_cont_inte, 
        oid_regi, 
        val_nume_docu_lega, 
        val_nume_docu_lega_sgte)
      (select '2',
              a.NUM_DOCU_CONT_INTE,
              a.oid_regi,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA - 1 as ante
         from FAC_REGIS_UNICO_VENTA a
        where a.tido_oid_tipo_docu = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and a.FEC_EMIS >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and a.FEC_EMIS < to_date(psFechaFin   , 'dd/mm/yyyy')+1
          and a.val_nume_docu_lega >= to_number(psRangoInicio)
          and a.val_nume_docu_lega <= to_number(psRangoFin)
          and a.SBAC_OID_SBAC = ls_oid_subacceso);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '2'
       and b.VAL_NUME_DOCU_LEGA in
           (select c.VAL_NUME_DOCU_LEGA_SGTE
              from GTT_RUV_ASIGN_NULOS c
             where c.ind_proc = '2');

    -- Carga los arreglos
    OPEN c_fin;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_fin BULK COLLECT INTO v_nume_docu_lega
                              LIMIT rows;

      EXIT WHEN v_row_count = c_fin%ROWCOUNT;
      v_row_count := c_fin%ROWCOUNT;

      for i in 1..v_nume_docu_lega.count loop
          lista_fin(i) := (v_nume_docu_lega(i));
          --dbms_output.put_line(i||'-> '||(v_nume_docu_lega(i)));
      end loop;

    END LOOP;
    CLOSE c_fin;

    dbms_output.put_line('c_inicio');
    OPEN c_inicio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_inicio BULK COLLECT INTO v_nume_docu_lega
                                  LIMIT rows;

      EXIT WHEN v_row_count2 = c_inicio%ROWCOUNT;
      v_row_count2 := c_inicio%ROWCOUNT;

      for j in 1..v_nume_docu_lega.count loop
          lista_inicio(j) := (v_nume_docu_lega(j));
          --dbms_output.put_line(j||'-> '||(v_nume_docu_lega(j)));
      end loop;

    END LOOP;
    CLOSE c_inicio;

    -- Ejecuta el proceso de Asignar nulos
    for i in 1..(lista_fin.count-1) loop
        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);
        ls_cont_aux := (aux_fin - aux_ini) + 1;
        ls_cont := ls_cont + ls_cont_aux;
    end loop;

    IF ls_cont >= 0 THEN
        psNumeroAsigna := ls_cont;
    ELSE
        psNumeroAsigna := 'O';
    END IF;

 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_CANTI_ASIGN_NULOS_RANGO ' || ls_sqlerrm);
  end RUV_PR_CANTI_ASIGN_NULOS_RANGO;

  /**************************************************************************
    Descripcion         : RUV_PR_PROCE_ASIGN_NULOS_RANGO
                          Proceso de Asignar Nulos opcion por dias
    Fecha Creacion      : 26/08/2015
    Parametros Entrada:
        psCodigoPais        : psCodigoPais
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
    Autor               : Karina Valencia
  ***************************************************************************/
  PROCEDURE RUV_PR_PROCE_ASIGN_NULOS_RANGO(psCodigoPais       varchar2,
                                           psOidTipoDocumento varchar2,
                                           psNumeroSerie      varchar2,
                                           psCodigoSubacceso  varchar2,
                                           psFechaInicio      varchar2,
                                           psFechaFin         varchar2,
                                           psRangoInicio      varchar2,
                                           psRangoFin         varchar2) as

  TYPE t_nume_docu_lega  is table of GTT_RUV_ASIGN_NULOS.VAL_NUME_DOCU_LEGA%type;
  v_nume_docu_lega t_nume_docu_lega;

  type t_inicio is table of number(10) index by pls_integer;
  type t_fin    is table of number(10) index by pls_integer;
  lista_inicio   t_inicio;
  lista_fin      t_fin;

  cursor c_fin is
      select (b.val_nume_docu_lega - 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '1'
       order by b.val_nume_docu_lega;

  cursor c_inicio is
      select (b.val_nume_docu_lega + 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '2'
       order by b.val_nume_docu_lega;

  ls_oid_subacceso    SEG_SUBAC.Oid_Sbac%type;
  ls_oid_pais         SEG_PAIS.Oid_Pais%type;
  ls_oid_sociedad     seg_socie.oid_soci%type;
  ls_fecha            date;
  ls_oid_tasa_impu    ped_tasa_impue.oid_tasa_impu%type;

  rows             NATURAL := 1000;   -- Number of rows to process at a time
  i                BINARY_INTEGER := 0;
  j                BINARY_INTEGER := 0;
  aux_ini          BINARY_INTEGER := 0;
  aux_fin          BINARY_INTEGER := 0;
  v_row_count      NUMBER := 0;
  v_row_count2     NUMBER := 0;

  BEGIN
    -- obtiene el oid del pais
    select OID_PAIS into ls_oid_pais from SEG_PAIS where cod_pais = psCodigoPais;
    -- obtiene el oid de sociedad
    select ss.oid_soci
      into ls_oid_sociedad
      from seg_socie ss
     where ss.cod_soci in (select VAL_PARA
                             from bas_param_pais
                            where cod_pais = psCodigoPais
                              and cod_sist = 'RUV'
                              and nom_para = 'codigoSociedad');
    -- obtiene el oid de subacceso
    SELECT s.oid_sbac into ls_oid_subacceso FROM SEG_SUBAC S where S.COD_SBAC = psCodigoSubacceso;
    -- obtiene la fecha
    ls_fecha := to_date(psFechaInicio,'dd/mm/yyyy') + trunc((to_date(psFechaFin,'dd/mm/yyyy')-to_date(psFechaInicio,'dd/mm/yyyy'))/2);
    -- Obtiene el oid de tasa de impuesto
    select oid_tasa_impu
    into ls_oid_tasa_impu
    from ped_tasa_impue
    where val_indi_impu = (select VAL_PARA from bas_param_pais where cod_pais = psCodigoPais and cod_sist = 'RUV' and NOM_PARA = 'impuestoVentas');

    insert into GTT_RUV_ASIGN_NULOS(
        ind_proc, 
        num_docu_cont_inte, 
        oid_regi, 
        val_nume_docu_lega, 
        val_nume_docu_lega_sgte)
      (select '1',
              a.NUM_DOCU_CONT_INTE,
              a.OID_REGI,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA + 1
         from FAC_REGIS_UNICO_VENTA a
        where a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and a.FEC_EMIS >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and a.FEC_EMIS < to_date(psFechaFin   , 'dd/mm/yyyy')+1
          and a.val_nume_docu_lega >= to_number(psRangoInicio)
          and a.val_nume_docu_lega <= to_number(psRangoFin)
          and a.SBAC_OID_SBAC = ls_oid_subacceso
          and a.VAL_NUME_DOCU_LEGA is not null);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '1'
       and b.VAL_NUME_DOCU_LEGA in (select c.VAL_NUME_DOCU_LEGA_SGTE
                                      from GTT_RUV_ASIGN_NULOS c
                                     where c.ind_proc = '1');

    insert into GTT_RUV_ASIGN_NULOS(
        ind_proc, 
        num_docu_cont_inte, 
        oid_regi, 
        val_nume_docu_lega, 
        val_nume_docu_lega_sgte)
      (select '2',
              a.NUM_DOCU_CONT_INTE,
              a.oid_regi,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA - 1 as ante
         from FAC_REGIS_UNICO_VENTA a
        where a.tido_oid_tipo_docu = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and a.FEC_EMIS >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and a.FEC_EMIS < to_date(psFechaFin   , 'dd/mm/yyyy')+1
          and a.val_nume_docu_lega >= to_number(psRangoInicio)
          and a.val_nume_docu_lega <= to_number(psRangoFin)
          and a.SBAC_OID_SBAC = ls_oid_subacceso);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '2'
       and b.VAL_NUME_DOCU_LEGA in
           (select c.VAL_NUME_DOCU_LEGA_SGTE
              from GTT_RUV_ASIGN_NULOS c
             where c.ind_proc = '2');

    -- Carga los arreglos
    OPEN c_fin;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_fin BULK COLLECT INTO v_nume_docu_lega
                              LIMIT rows;

      EXIT WHEN v_row_count = c_fin%ROWCOUNT;
      v_row_count := c_fin%ROWCOUNT;

      for i in 1..v_nume_docu_lega.count loop
          lista_fin(i) := (v_nume_docu_lega(i));
          --dbms_output.put_line(i||'-> '||(v_nume_docu_lega(i)));
      end loop;

    END LOOP;
    CLOSE c_fin;

    OPEN c_inicio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_inicio BULK COLLECT INTO v_nume_docu_lega
                                  LIMIT rows;

      EXIT WHEN v_row_count2 = c_inicio%ROWCOUNT;
      v_row_count2 := c_inicio%ROWCOUNT;

      for j in 1..v_nume_docu_lega.count loop
          lista_inicio(j) := (v_nume_docu_lega(j));
          --dbms_output.put_line(j||'-> '||(v_nume_docu_lega(j)));
      end loop;

    END LOOP;
    CLOSE c_inicio;
    
    DELETE RUV_ASIGN_NULOS_XDIRA_TEMPO;

    INSERT INTO RUV_ASIGN_NULOS_XDIRA_TEMPO(    
           VAL_NUME_DOCU_LEGA,
           TIDO_OID_TIPO_DOCU,
           FEC_EMIS)
    (SELECT RR.VAL_NUME_DOCU_LEGA,
           RR.TIDO_OID_TIPO_DOCU,
           RR.FEC_EMIS
      FROM FAC_REGIS_UNICO_VENTA RR
     WHERE RR.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
       AND RR.FEC_EMIS >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
       AND RR.FEC_EMIS < TO_DATE(psFechaFin   , 'dd/mm/yyyy')+1
       and RR.val_nume_docu_lega >= to_number(psRangoInicio)
       and RR.val_nume_docu_lega <= to_number(psRangoFin));

    -- Ejecuta el proceso de Asignar nulos
    for i in 1..(lista_fin.count-1) loop
        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);
        for j in aux_ini..aux_fin loop

          begin
             SELECT max(RR.FEC_EMIS)
              INTO ls_fecha
              FROM RUV_ASIGN_NULOS_XDIRA_TEMPO RR
             WHERE RR.val_nume_docu_lega in (aux_ini - 1)
               AND RR.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
               AND RR.FEC_EMIS >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
               AND RR.FEC_EMIS < TO_DATE(psFechaFin   , 'dd/mm/yyyy')+1;
          exception
            when no_data_found then
              ls_fecha := to_date(psFechaInicio,'dd/mm/yyyy') + trunc((to_date(psFechaFin,'dd/mm/yyyy')-to_date(psFechaInicio,'dd/mm/yyyy'))/2);
          end;

           insert into FAC_REGIS_UNICO_VENTA(OID_REGI,
                                             PAIS_OID_PAIS,
                                             SOCI_OID_SOCI,
                                             SBAC_OID_SBAC,
                                             VAL_EJER_DOCU_INTE,
                                             NUM_DOCU_CONT_INTE,
                                             FEC_EMIS,
                                             VAL_BASE_IMPO,
                                             IMP_IMPU,
                                             IMP_TOTA,
                                             VAL_PUNT_EMIS,
                                             VAL_DOCU_INTE,
                                             VAL_NUME_IDEN_FISC,
                                             VAL_NUME_IDEN_NNAL,
                                             VAL_SERI_DOCU_REFE,
                                             VAL_NUME_DOCU_REFE,
                                             VAL_INTE_MORA,
                                             VAL_DESC,
                                             VAL_COMI,
                                             VAL_FLET,
                                             VAL_BASE_IMPO_NETO,
                                             VAL_NOM1,
                                             VAL_NOM2,
                                             VAL_APE1,
                                             VAL_APE2,
                                             CLIE_OID_CLIE,
                                             IND_ESTA,
                                             IND_TRAN_GRAT,
                                             IND_FACT_GRAT,
                                             DCCA_OID_CABE,
                                             TAIM_OID_TASA_IMPU,
                                             TIDO_OID_TIPO_DOCU,
                                             FEC_CIER,
                                             VAL_INDI_RUV,
                                             VAL_NUME_DOCU_LEGA,
                                             VAL_SERI_DOCU_LEGA,
                                             TIDO_TIPO_DOCU_REFE)
                                          values
                                            (FAC_RUVE_SEQ.NEXTVAL,
                                             ls_oid_pais,
                                             ls_oid_sociedad,
                                             ls_oid_subacceso,
                                             substr(ls_fecha,-2,2),
                                             NULL,
                                             ls_fecha,
                                             0,
                                             0,
                                             0,
                                             '000',
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             0,
                                             decode(substr(psCodigoPais,0,2),'VE','ANULADAS',null), --null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             1,
                                             0,
                                             0,
                                             null,
                                             ls_oid_tasa_impu,
                                             psOidTipoDocumento,
                                             null,
                                             'A',
                                             j,
                                             psNumeroSerie,
                                             null);
        end loop;
    end loop;

 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_PROCE_ASIGN_NULOS_RANGO ' || ls_sqlerrm);

  end RUV_PR_PROCE_ASIGN_NULOS_RANGO;

  /******************************************************************************
  Descripcion         : RUV_PR_GENER_DOCUM_CONTA
                        Proceso que genera la data para el reporte de Documentos
                        Contables
  Fecha Creacion      : 30/06/2010
  Parametros Entrada:
      psOidTipoDocumento  : Oid Tipo de Documento
      psNumeroSerie       : Numero de serie
      psCodigoSubacceso   : Codigo de subacceso
      psFechaInicio       : Fecha de inicio
      psFechaFin          : Fecha de fin
  Autor               : Jesse James Rios Franco
 *******************************************************************************/
   PROCEDURE RUV_PR_GENER_DOCUM_CONTA(psOidTipoDocumento NUMBER,
                                      psFechaInicio VARCHAR2,
                                      psFechaFin VARCHAR2,
                                      psCodigoSubacceso VARCHAR2,
                                      psNumeroSerie VARChAR2)


    IS

      CURSOR c_interfaz IS
      SELECT *
      FROM GTT_RUV_DOCUM_CONTA
      order by TIDO_OID_TIPO_DOCU,COD_PERI,FEC_EMIS,VAL_SERI_DOCU_LEGA,VAL_NUME_DOCU_LEGA;

      TYPE interfazRecTab  IS TABLE OF GTT_RUV_DOCUM_CONTA%ROWTYPE;
      interfazRecord interfazRecTab;

      regDocuConta GTT_RUV_DOCUM_CONTA%ROWTYPE;

      lnTipoDocumento GTT_RUV_DOCUM_CONTA.TIDO_OID_TIPO_DOCU%TYPE;
      lsPeriodo       GTT_RUV_DOCUM_CONTA.COD_PERI%TYPE;
      ldFecha         GTT_RUV_DOCUM_CONTA.FEC_EMIS%TYPE;
      lsNumeroSerie   GTT_RUV_DOCUM_CONTA.VAL_SERI_DOCU_LEGA%TYPE;
      lnSunat_inicial GTT_RUV_DOCUM_CONTA.VAL_NUME_DOCU_LEGA%TYPE;
      lsAutogenerado_inicial  GTT_RUV_DOCUM_CONTA.NUM_DOCU_CONT_INTE%TYPE;
      lnIndEstado     GTT_RUV_DOCUM_CONTA.IND_ESTA%TYPE;
      lbInsertar BOOLEAN;
      lnContador NUMBER;
    BEGIN

       EXECUTE IMMEDIATE 'TRUNCATE TABLE RUV_TEMPO_DOCUM_CONTA';

       lnTipoDocumento := -1;
       lsPeriodo := '-1';
       ldFecha := null;
       lsNumeroSerie := '-1';
       lnIndEstado := -1;
       lnContador := 0;


       INSERT INTO GTT_RUV_DOCUM_CONTA
       (select a.tido_oid_tipo_docu, --1 orden break
               DECODE(b.perd_oid_peri,null,null,(SUBSTR(gen_pkg_gener.gen_fn_devuelve_des_perio(b.perd_oid_peri),5,2) || '-' || SUBSTR(gen_pkg_gener.gen_fn_devuelve_des_perio(b.perd_oid_peri),3,2))) as campana,-- 2 orden break
               trunc(a.FEC_EMIS) as FEC_EMIS,-- 3 orden break
               a.VAL_SERI_DOCU_LEGA,-- 4 orden break
               a.VAL_NUME_DOCU_LEGA,-- 5 orden
               a.NUM_DOCU_CONT_INTE,
               a.ind_esta -- break
        from FAC_REGIS_UNICO_VENTA a ,
             FAC_DOCUM_CONTA_CABEC b
        where a.dcca_oid_cabe = b.oid_cabe(+)
        and a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
        and trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
        and trunc(a.FEC_EMIS) <= to_date(psFechaFin, 'dd/mm/yyyy')
        and a.SBAC_OID_SBAC = (SELECT s.oid_sbac
                               FROM SEG_SUBAC S
                               WHERE S.COD_SBAC = psCodigoSubacceso)
        and a.val_seri_docu_lega = psNumeroSerie);

        OPEN c_interfaz;

        LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

            IF interfazRecord.COUNT > 0 THEN

               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   lbInsertar := FALSE;
                   regDocuConta := interfazRecord(x);

                   IF (lnTipoDocumento <> regDocuConta.Tido_Oid_Tipo_Docu OR
                       lsPeriodo <> regDocuConta.Cod_Peri OR
                       ldFecha <> regDocuConta.Fec_Emis OR
                       lsNumeroSerie <> regDocuConta.Val_Seri_Docu_Lega OR
                       lnIndEstado <> regDocuConta.Ind_Esta) THEN

                       lnContador := lnContador + 1;
                       lbInsertar := TRUE;
                       lnSunat_inicial := regDocuConta.Val_Nume_Docu_Lega;
                       lsAutogenerado_inicial := regDocuConta.Num_Docu_Cont_Inte;

                       INSERT INTO RUV_TEMPO_DOCUM_CONTA
                       (ID_CORR,
                        FEC_EMIS,
                        VAL_SERI_DOCU_LEGA,
                        COD_PERI,
                        SUN_INIC,
                        AUT_GENE_INIC,
                        TIDO_OID_TIPO_DOCU,
                        IND_ESTA,
                        SUN_FINA,
                        AUT_GENE_FINA)
                       VALUES
                       (lnContador,
                        regDocuConta.Fec_Emis,
                        regDocuConta.Val_Seri_Docu_Lega,
                        regDocuConta.Cod_Peri,
                        lnSunat_inicial,
                        lsAutogenerado_inicial,
                        regDocuConta.Tido_Oid_Tipo_Docu,
                        regDocuConta.Ind_Esta,
                        lnSunat_inicial,
                        lsAutogenerado_inicial);
                   END IF;

                   IF lbInsertar THEN
                      lnTipoDocumento := regDocuConta.Tido_Oid_Tipo_Docu;
                      lsPeriodo := regDocuConta.COD_PERI;
                      ldFecha := regDocuConta.Fec_Emis;
                      lsNumeroSerie := regDocuConta.Val_Seri_Docu_Lega;
                      lnIndEstado := regDocuConta.Ind_Esta;

                   ELSE

                      UPDATE RUV_TEMPO_DOCUM_CONTA
                      SET SUN_FINA = regDocuConta.Val_Nume_Docu_Lega,
                          AUT_GENE_FINA = regDocuConta.Num_Docu_Cont_Inte
                      WHERE ID_CORR = lnContador;
                   END IF;

               END LOOP;
            END IF;

           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        CLOSE c_interfaz;
    EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_DOCUM_CONTA ' || ls_sqlerrm);

    END RUV_PR_GENER_DOCUM_CONTA;


/***************************************************************************
Descripcion       : Valida y Devuelve Nro de Serie del Documento de Pago
Fecha Creacion    : 14/07/2010
Parametros Entrada:
      pnoidTipoDocumento  : Tipo de Documento de Pago
      psNrodocumento   : Numero de Documento de Identidad
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_VALID_NUMER_SERIE(
   pnoidTipoDocumento  VARCHAR2,
   psNrodocumento      VARCHAR2
)
RETURN VARCHAR2
IS
  lsNrodocumento    VARCHAR2(20);
  longitud          NUMBER;
  MAXIMO_CARACTER   NUMBER:=4;
BEGIN
  lsNrodocumento := trim(psNrodocumento);
  IF lsNrodocumento IS NULL THEN
     RETURN NULL;
  END IF;
  BEGIN

       longitud := length(lsNrodocumento);
       IF longitud >= MAXIMO_CARACTER THEN
          lsNrodocumento := substr(lsNrodocumento, longitud - (MAXIMO_CARACTER - 1));
       ELSE
          lsNrodocumento := '0000' || TRIM(lsNrodocumento);
          longitud := length(lsNrodocumento);
          lsNrodocumento := substr(lsNrodocumento, longitud - (MAXIMO_CARACTER - 1));
       END IF;

  EXCEPTION
  WHEN OTHERS THEN
       RETURN '0000';
  END;
 RETURN lsNrodocumento;
END RUV_FN_VALID_NUMER_SERIE;


/***************************************************************************
Descripcion       : Valida y Devuelve Nro de Serie del Documento de Pago
                    de Referencia
Fecha Creacion    : 18/08/2010
Parametros Entrada:
      pnoidTipoDocumento  : Tipo de Documento de Pago
      psNrodocumento   : Numero de Documento de Identidad
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_VALID_NUMER_SERIE_REFER(
   psNrodocumento      VARCHAR2
)
RETURN VARCHAR2
IS
  lsNrodocumento    VARCHAR2(20);
  longitud          NUMBER;
  MAXIMO_CARACTER   NUMBER:=4;
BEGIN
  lsNrodocumento := trim(psNrodocumento);
  IF lsNrodocumento IS NULL THEN
    RETURN NULL;
  END IF;

  longitud := Length(lsNrodocumento);
  lsNrodocumento:= psNrodocumento;
  BEGIN
       IF longitud >= MAXIMO_CARACTER THEN
          lsNrodocumento := substr(lsNrodocumento, longitud - (MAXIMO_CARACTER - 1));
       ELSE
          lsNrodocumento := '0000' || TRIM(lsNrodocumento);
          longitud := length(lsNrodocumento);
          lsNrodocumento := substr(lsNrodocumento, longitud - (MAXIMO_CARACTER - 1));
       END IF;
  EXCEPTION
  WHEN OTHERS THEN
       RETURN '0000';
  END;
 RETURN lsNrodocumento;
END RUV_FN_VALID_NUMER_SERIE_REFER;


/***************************************************************************
Descripcion       : Valida y Devuelve Nro de documento DE IDENTIDAD
Fecha Creacion    : 14/07/2010
Parametros Entrada:
      psTipoDocumSunat  : Tipo de Documento de Sunat
      psNrodocumento    : Numero de Documento de Identidad
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_VALID_DOCUM_IDENTI(
   psTipoDocumSunat VARCHAR2,
   psNrodocumento   VARCHAR2
)
RETURN VARCHAR2
IS
  lsNrodocumento    VARCHAR2(15);
BEGIN
  lsNrodocumento := NVL(trim(substr(psNrodocumento,1,15)),DEFAULT_CHAR);
  IF psTipoDocumSunat = RUC_SUNAT THEN
     IF length(psNrodocumento) > LONGITUD_RUC THEN
         lsNrodocumento := substr(psNrodocumento, -LONGITUD_RUC, LONGITUD_RUC);
     END IF;
  END IF;
  IF psTipoDocumSunat = DNI_SUNAT THEN
     IF length(psNrodocumento) > LONGITUD_DNI THEN
        lsNrodocumento := substr(psNrodocumento, -LONGITUD_DNI, LONGITUD_DNI);
     END IF;
  END IF;
 RETURN lsNrodocumento;
EXCEPTION
WHEN NO_DATA_FOUND  THEN
     RETURN '0';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_FN_VALID_DOCUM_IDENTI: '||ls_sqlerrm);
END RUV_FN_VALID_DOCUM_IDENTI;

/***************************************************************************
Descripcion       : Devuelve el codigo de homologacion de sunat de un documento DE IDENTIDAD
Fecha Creacion    : 14/07/2010
Parametros Entrada:
      pnOidComprobantePago : Oid del comprobante de pago
      pnOidSubAcceso   : Oid del SubAcceso
      pnOidTipoDocum  : Oid Tipo de Documento
      pnOidPais       : Oid Pais
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_DEVUE_HOMO_TIPO_DOCUM(
   pnOidComprobantePago   NUMBER,
   pnOidSubAcceso   NUMBER,
   pnOidTipoDocum   NUMBER,
   pnOidPais        NUMBER
)
RETURN VARCHAR2
IS
  lscodsunat          VARCHAR2(1);
  lnOidTipoDocum      NUMBER;
  ID_TIPO_OTROS       NUMBER:=2007;

BEGIN
 lscodsunat:='';
 lnOidTipoDocum := pnOidTipoDocum;
 IF lnOidTipoDocum = ID_TIPO_OTROS THEN
    RETURN CARNET_EXTRANJERIA;
 END IF;
 IF pnOidSubAcceso = OID_SUBACCESO_ESPECIAL THEN
    IF pnOidComprobantePago = 1 OR  pnOidComprobantePago = 9 OR
       pnOidComprobantePago = 32 OR  pnOidComprobantePago = 33 THEN
       RETURN RUC_SUNAT;
    END IF;
    IF pnOidComprobantePago = 29 OR
       pnOidComprobantePago = 31 THEN
       RETURN DNI_SUNAT;
    END IF;
 END IF;


 SELECT B.COD_HOMO_SUNA INTO lscodsunat
 FROM BAS_HOMOL_TIPO_DOCUM_IDENT B,
      MAE_TIPO_DOCUM F,
      SEG_PAIS P
 WHERE
       F.OID_TIPO_DOCU = lnOidTipoDocum
       AND F.OID_TIPO_DOCU = B.OID_TIPO_DOCU
       AND P.OID_PAIS = pnOidPais
       AND P.COD_PAIS = B.COD_PAIS;
RETURN lscodsunat;
EXCEPTION
WHEN NO_DATA_FOUND  THEN
     RETURN '0';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_FN_DEVUE_HOMO_TIPO_DOCUM: '||ls_sqlerrm);
END RUV_FN_DEVUE_HOMO_TIPO_DOCUM;

 /***************************************************************************
Descripcion       : Obtiene el monto Base Importe,verificando si es inafecto la
                    tasa
Fecha Creacion    : 14/07/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_OBTIE_BASE_IMPOR(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
RETURN  NUMBER  IS
  indicadorImpuesto VARCHAR2(3);
  indicadorImpuestoComparar VARCHAR2(3);
  BEGIN
    indicadorImpuesto :='';
    indicadorImpuestoComparar:= 'EXP';

    SELECT PED_TASA_IMPUE.Val_Indi_Impu
    INTO indicadorImpuesto
    FROM PED_TASA_IMPUE
    WHERE PED_TASA_IMPUE.Oid_Tasa_Impu = oidTasaImpuesto;

    IF indicadorImpuesto = indicadorImpuestoComparar THEN
       RETURN 0;
    ELSE
        RETURN valorRetorno;
    END IF;
  EXCEPTION
  WHEN NO_DATA_FOUND  THEN
       RETURN valorRetorno;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_FN_OBTIE_BASE_IMPOR: '||ls_sqlerrm);
 END RUV_FN_OBTIE_BASE_IMPOR;

/***************************************************************************
Descripcion       : Obtiene el monto de las operaciones inafectas
Fecha Creacion    : 14/07/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION RUV_FN_OBTIE_OPERA_INAFE(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
RETURN  NUMBER  IS
  indicadorImpuesto VARCHAR2(3);
  indicadorImpuestoComparar VARCHAR2(3);
  BEGIN
  indicadorImpuesto :='';
  indicadorImpuestoComparar:= 'EXP';

  SELECT PED_TASA_IMPUE.Val_Indi_Impu
  INTO indicadorImpuesto
  FROM PED_TASA_IMPUE
  WHERE PED_TASA_IMPUE.Oid_Tasa_Impu = oidTasaImpuesto;

  IF indicadorImpuesto = indicadorImpuestoComparar THEN
     RETURN valorRetorno;
  ELSE
      RETURN 0;
  END IF;
  EXCEPTION
  WHEN NO_DATA_FOUND  THEN
       RETURN 0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_FN_OBTIE_OPERA_INAFE: '||ls_sqlerrm);
 END RUV_FN_OBTIE_OPERA_INAFE;


/******************************************************************************
  Descripcion         : RUV_PR_GENER_REPOR_SUNAT
                        Proceso que genera la data para el reporte RUV de Sunat
                        Contables
  Fecha Creacion      : 14/07/2010
  Parametros Entrada:
      psCodigoPais  : Codigo de Pais
      psCodigoPeriodoInformar: Periodo a Informar
      psCodigoPeriodoEnviar: Periodo a Enviar
      psTipoAccion  : Tipo de Accion
                      '0' Reiniciar Periodo a Informar
                      '1' Generar Periodo a Informar
  Autor             : Carlos Bazalar La Rosa
 *******************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_SUNAT(psCodigoPais VARCHAR2,
                                      psCodigoPeriodoInformar VARCHAR2,
                                      psCodigoPeriodoEnviar VARCHAR2,
                                      psTipoAccion VARCHAR2,
                                      psUsuario VARCHAR2)
  IS
  
  lsVersion      VARCHAR2(1);

  BEGIN
    
      lsVersion := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                             'RUV',
                                                             '011');
                                                             
    IF psTipoAccion = '0' THEN
      IF lsVersion = '5' THEN
             RUV_PR_GENER_PLE5(psCodigoPais, psCodigoPeriodoInformar, psCodigoPeriodoEnviar, psUsuario);         
      ELSE
             RUV_PR_GENER_REPOR_SUNAT_REINI(psCodigoPais, psCodigoPeriodoInformar, psCodigoPeriodoEnviar, psUsuario);
      END IF;       
    END IF;
    RETURN;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_SUNAT: '||ls_sqlerrm);
  END RUV_PR_GENER_REPOR_SUNAT;


/******************************************************************************
  Descripcion         : RUV_PR_GENER_REPOR_SUNAT_REINI
                        Proceso que genera la data para el reporte RUV de Sunat
                        Contables DESDE CERO
  Fecha Creacion      : 14/07/2010
  Parametros Entrada:
      psCodigoPais  : Codigo de Pais
      psCodigoPeriodoInformar: Periodo a Informar
      psCodigoPeriodoEnviar: Periodo a Enviar
  Autor             : Carlos Bazalar La Rosa
 *******************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_SUNAT_REINI(psCodigoPais VARCHAR2,
                                      psCodigoPeriodoInformar VARCHAR2,
                                      psCodigoPeriodoEnviar VARCHAR2,
                                      psUsuario VARCHAR2)
  IS
    TYPE reporteSunatTAB  IS TABLE OF RUV_HISTO_REPOR_SUNAT%ROWTYPE;
    reporteSunatRecord reporteSunatTAB;
    regSunat           RUV_HISTO_REPOR_SUNAT%ROWTYPE;
    lsPeriodo          VARCHAR2(6);
    lsTipoFacturaAnulada  VARCHAR2(2);
    searchstr  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    replacestr VARCHAR2(100) := 'a        ';

    /* Cursor de Facturas */
    CURSOR c_factura(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      DEFAULT_FECHA,
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(NVL(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)),DEFAULT_CHAR), searchstr, replacestr),
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      - NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as bas_impo,
      --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      0.00 as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      0.000 as tip_camb,
      DEFAULT_FECHA as fec_refe,
      '00' as tip_refe,
      DEFAULT_CHAR  as ser_refe,
      DEFAULT_CHAR  as dcto_refe ,
      decode(a.ind_esta,1,'2','1') as estado,
      0 as NUM_CORR_ASIN,
      0 as val_desc
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE (a.TIDO_OID_TIPO_DOCU = 1 OR a.TIDO_OID_TIPO_DOCU = 9)
     AND a.ind_esta <> 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

    /* Cursor de Facturas Anuladas */
    CURSOR c_facturaAnulada(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      DEFAULT_FECHA,
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(NVL(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)),DEFAULT_CHAR), searchstr, replacestr),
      0.00 as val_fact_exp,
      0.00 , --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as bas_impo,
      0.00, --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      0.00, --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as imp_tota_inaf,
      0.00 as isc,
      0.00, --ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      0.00, --ABS(a.IMP_TOTA) as imp_tota,
      0.000 as tip_camb,
      DEFAULT_FECHA as fec_refe,
      '00' as tip_refe,
      DEFAULT_CHAR  as ser_refe,
      DEFAULT_CHAR  as dcto_refe ,
      decode(a.ind_esta,1,'2','1') as estado,
      0 as NUM_CORR_ASIN,
      0 as val_desc      
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE (a.TIDO_OID_TIPO_DOCU = 1 OR a.TIDO_OID_TIPO_DOCU = 9)
     AND a.ind_esta = 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;


   /* Cursor de Notas de Debito */
    CURSOR c_debito(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      DEFAULT_FECHA,
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(NVL(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)),DEFAULT_CHAR), searchstr, replacestr),
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) -
      NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as bas_impo,
      --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      0.00 as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      0.000 as tip_camb,
      TO_CHAR(a.FEC_EMIS_REFE,'DD/MM/YYYY'),
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_TIPO_DOCU_REFE,a.pais_oid_pais),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE_REFER(a.VAL_SERI_DOCU_REFE),
      a.VAL_NUME_DOCU_REFE,
      decode(a.ind_esta,1,'2','1') as estado,
      0 as NUM_CORR_ASIN,
      0 as val_desc      
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 34
     AND a.ind_esta <> 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

   /* Cursor de Notas de Debito Anuladas */
    CURSOR c_debitoAnulada(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      DEFAULT_FECHA,
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(NVL(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)),DEFAULT_CHAR), searchstr, replacestr),
      0.00 as val_fact_exp,
      0.00, --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as bas_impo,
      0.00, --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      0.00, --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as imp_tota_inaf,
      0.00 as isc,
      0.00, --ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      0.00, --ABS(a.IMP_TOTA) as imp_tota,
      0.000 as tip_camb,
      TO_CHAR(a.FEC_EMIS_REFE,'DD/MM/YYYY'),
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_TIPO_DOCU_REFE,a.pais_oid_pais),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE_REFER(a.VAL_SERI_DOCU_REFE),
      a.VAL_NUME_DOCU_REFE,
      decode(a.ind_esta,1,'2','1') as estado,
      0 as NUM_CORR_ASIN,
      0 as val_desc      
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 34
     AND a.ind_esta = 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

   /* Cursor de Notas de Credito */
   CURSOR c_credito(vsPeriodo VARCHAR2) IS
   SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      DEFAULT_FECHA,
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(NVL(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)),DEFAULT_CHAR), searchstr, replacestr),
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      (-1) * ( abs(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) -
      abs(NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00))  ) as bas_impo,
      0.00 as imp_tota_exo, --(-1) * abs(NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00)) as imp_tota_exo,
      (-1) * --abs(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
       decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      (-1) * abs(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      (-1) * abs(a.IMP_TOTA) as imp_tota,
      0.000 as tip_camb,
      TO_CHAR(a.FEC_EMIS_REFE,'DD/MM/YYYY'),
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_TIPO_DOCU_REFE,a.pais_oid_pais),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE_REFER(a.VAL_SERI_DOCU_REFE),
      a.VAL_NUME_DOCU_REFE,
      decode(a.ind_esta,1,'2','1') as estado,
      0 as NUM_CORR_ASIN,
      0 as val_desc      
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU in (31,32,33)
     AND a.ind_esta <> 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

   /* Cursor de Notas de Credito Anuladas*/
   CURSOR c_creditoAnulada(vsPeriodo VARCHAR2) IS
   SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      DEFAULT_FECHA,
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(NVL(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)),DEFAULT_CHAR), searchstr, replacestr),
      0.00 as val_fact_exp,
      0.00, --(-1) * abs(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as bas_impo,
      0.00, --(-1) * abs(NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00)) as imp_tota_exo,
      0.00, --(-1) * abs(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as imp_tota_inaf,
      0.00 as isc,
      0.00, --(-1) * abs(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      0.00, --(-1) * abs(a.IMP_TOTA) as imp_tota,
      0.000 as tip_camb,
      TO_CHAR(a.FEC_EMIS_REFE,'DD/MM/YYYY'),
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_TIPO_DOCU_REFE,a.pais_oid_pais),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE_REFER(a.VAL_SERI_DOCU_REFE),
      a.VAL_NUME_DOCU_REFE,
      decode(a.ind_esta,1,'2','1') as estado,
      0 as NUM_CORR_ASIN,
      0 as val_desc      
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU in (31,32,33)
     AND a.ind_esta = 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;


   /* Cursor de Boletas */
   CURSOR c_boleta29(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      DEFAULT_FECHA,
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      DEFAULT_CHAR,
      DEFAULT_CHAR,
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) -
      NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as bas_impo,
      0.00 as imp_tota_exo, --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      0.000 as tip_camb,
      DEFAULT_FECHA as fec_refe,
      '00' as tip_refe,
      DEFAULT_CHAR  as ser_refe,
      DEFAULT_CHAR  as dcto_refe ,
      '1' as estado,
      0 as NUM_CORR_ASIN,
      0 as val_desc      
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 29
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo
     and a.ind_esta = 0;

   CURSOR c_boleta30(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      DEFAULT_FECHA,
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      DEFAULT_CHAR,
      DEFAULT_CHAR,
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(a.val_base_impo_neto) + DECODE (a.TAIM_OID_TASA_IMPU,2002,0,ABS(a.VAL_DESC)) - ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) AS bas_impo,
      0.00  as imp_tota_exo, --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      0.000 as tip_camb,
      DEFAULT_FECHA as fec_refe,
      '00' as tip_refe,
      DEFAULT_CHAR  as ser_refe,
      DEFAULT_CHAR  as dcto_refe ,
      '1' as estado,
      0 as NUM_CORR_ASIN,
      0 as val_desc      
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 30
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo
     and a.ind_esta = 0;

   /* Cursor de Ticket */
   CURSOR c_ticket(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      DEFAULT_FECHA,
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      DEFAULT_CHAR,
      DEFAULT_CHAR,
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) -
      NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00)  as bas_impo,
      0.00  as imp_tota_exo,--NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      0.000 as tip_camb,
      DEFAULT_FECHA as fec_refe,
      '00' as tip_refe,
      DEFAULT_CHAR  as ser_refe,
      DEFAULT_CHAR  as dcto_refe ,
      decode(a.ind_esta,1,'2','1') as estado,
      0 as NUM_CORR_ASIN,
      0 as val_desc      
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 35
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

   lsNroDocumentoFacturaAnulada RUV_HISTO_REPOR_SUNAT.NUM_CORR%TYPE;
   lsFechaFacturaAnulada        RUV_HISTO_REPOR_SUNAT.FEC_EMIS%TYPE;
  BEGIN
    DELETE FROM GTT_RUV_REPOR_SUNAT;
    DELETE FROM GTT_RUV_REPOR_SUNAT_TEMPO;

    lsPeriodo := psCodigoPeriodoEnviar ;
    DELETE FROM RUV_HISTO_REPOR_SUNAT A
    WHERE A.COD_PERI = LsPeriodo || '00';

    /* Insertando Facturas */
    OPEN c_factura(lsPeriodo);
    LOOP
      FETCH c_factura BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FORALL x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST
            INSERT INTO RUV_HISTO_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
      END IF;
      EXIT WHEN c_factura%NOTFOUND;
    END LOOP;
    CLOSE c_factura;

    /* obteniendo 1era Factura Anulada de Serie '065' */
    BEGIN
      SELECT
         oid_regi, fec_emis,
         VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(x.TIDO_OID_TIPO_DOCU,x.pais_oid_pais)
      INTO lsNroDocumentoFacturaAnulada,
           lsFechaFacturaAnulada,
           lsTipoFacturaAnulada
      FROM
         (SELECT TRIM(TO_CHAR(a.oid_regi)) oid_regi,
                TO_CHAR(a.fec_emis,'DD/MM/YYYY') fec_emis,
                TIDO_OID_TIPO_DOCU,
                pais_oid_pais
          FROM FAC_REGIS_UNICO_VENTA a
          WHERE (a.TIDO_OID_TIPO_DOCU = 1 OR a.TIDO_OID_TIPO_DOCU = 9)
            AND a.ind_esta = 1
            and TO_CHAR(a.FEC_EMIS,'YYYYMM') = lsPeriodo
            AND a.val_seri_docu_lega = TIPO_SERIE_FACTURA_REFE
          ORDER BY a.fec_emis, a.oid_regi
          ) x
      WHERE ROWNUM = 1;
    EXCEPTION
    WHEN no_data_found THEN
        lsNroDocumentoFacturaAnulada := NULL;
        lsFechaFacturaAnulada := NULL;
    END;

    /* Insertando Facturas Anuladas */
    OPEN c_facturaAnulada(lsPeriodo);
    LOOP
      FETCH c_facturaAnulada BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
              regSunat := reporteSunatRecord(x);
              regSunat.Tip_Docu_Iden_Clie := RUC_SUNAT;
              regSunat.Num_Docu_Clie := RUC_ANULADAS;
              regSunat.Nom_Clie := CLIENTE_ANULADAS;

              INSERT INTO RUV_HISTO_REPOR_SUNAT
              VALUES regSunat;
          END LOOP;
      END IF;
      EXIT WHEN c_facturaAnulada%NOTFOUND;
    END LOOP;
    CLOSE c_facturaAnulada;

    /* Insertando Notas de Debito */
    OPEN c_debito(lsPeriodo);
    LOOP
      FETCH c_debito BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FORALL x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST
            INSERT INTO RUV_HISTO_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
      END IF;
      EXIT WHEN c_debito%NOTFOUND;
    END LOOP;
    CLOSE c_debito;

   /* Insertando Notas de Debito Anuladas */
    OPEN c_debitoAnulada(lsPeriodo);
    LOOP
      FETCH c_debitoAnulada BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
              regSunat := reporteSunatRecord(x);
              regSunat.Tip_Docu_Iden_Clie := RUC_SUNAT;
              regSunat.Num_Docu_Clie := RUC_ANULADAS;
              regSunat.Nom_Clie := CLIENTE_ANULADAS;

              IF regSunat.IND_ESTA_COMP_PAGO = '2' THEN
                 regSunat.FEC_EMIS_COMP_REFE := DEFAULT_FECHA;
                 regSunat.TIP_COMP_REFE := '00';
                 regSunat.NUM_COMP_REFE := '-';
                 regSunat.Num_Seri_Comp_Refe := '-';
              END IF;

              INSERT INTO RUV_HISTO_REPOR_SUNAT
              VALUES regSunat;
          END LOOP;
      END IF;
      EXIT WHEN c_debitoAnulada%NOTFOUND;
    END LOOP;
    CLOSE c_debitoAnulada;


    /* Insertando Notas de Credito*/
    OPEN c_credito(lsPeriodo);
    LOOP
      FETCH c_credito BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
            regSunat := reporteSunatRecord(x);
            IF regSunat.Mon_Base_Impo = 0.00 AND
              regSunat.Mon_Tota_Comp_Pago = 0.00 AND
              regSunat.Mon_Igv = 0.00 AND
              regSunat.Num_Comp_refe IS NULL THEN
              regSunat.Fec_Emis_Comp_refe := lsFechaFacturaAnulada;
              regSunat.Num_Comp_refe := lsNroDocumentoFacturaAnulada;
              regSunat.Num_Seri_Comp_refe := TIPO_SERIE_FACTURA_REFE_GRABA;
              regSunat.Tip_Comp_Refe := lsTipoFacturaAnulada;
            END IF;
            INSERT INTO RUV_HISTO_REPOR_SUNAT
            VALUES regSunat;

          END LOOP;
      END IF;
      EXIT WHEN c_credito%NOTFOUND;
    END LOOP;
    CLOSE c_credito;

   /* Insertando Notas de Credito Anuladas */
    OPEN c_creditoAnulada(lsPeriodo);
    LOOP
      FETCH c_creditoAnulada BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
              regSunat := reporteSunatRecord(x);
              regSunat.Tip_Docu_Iden_Clie := RUC_SUNAT;
              regSunat.Num_Docu_Clie := RUC_ANULADAS;
              regSunat.Nom_Clie := CLIENTE_ANULADAS;

              IF regSunat.IND_ESTA_COMP_PAGO = '2' THEN
                 regSunat.FEC_EMIS_COMP_REFE := DEFAULT_FECHA;
                 regSunat.TIP_COMP_REFE := '00';
                 regSunat.NUM_COMP_REFE := '-';
                 regSunat.Num_Seri_Comp_refe := '-';
              ELSE
              regSunat.Fec_Emis_Comp_refe := lsFechaFacturaAnulada;
                 regSunat.Tip_Comp_Refe := lsTipoFacturaAnulada;
              regSunat.Num_Comp_refe := lsNroDocumentoFacturaAnulada;
              regSunat.Num_Seri_Comp_refe := TIPO_SERIE_FACTURA_REFE_GRABA;
              END IF;

              INSERT INTO RUV_HISTO_REPOR_SUNAT
              VALUES regSunat;
          END LOOP;
      END IF;
      EXIT WHEN c_creditoAnulada%NOTFOUND;
    END LOOP;
    CLOSE c_creditoAnulada;

    -- Insertando Boletas en Tabla Temporal
    OPEN c_boleta29(lsPeriodo);
    LOOP
      FETCH c_boleta29 BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FORALL x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST
            INSERT INTO GTT_RUV_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
      END IF;
      EXIT WHEN c_boleta29%NOTFOUND;
    END LOOP;
    CLOSE c_boleta29;

    OPEN c_boleta30(lsPeriodo);
    LOOP
      FETCH c_boleta30 BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FORALL x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST
            INSERT INTO GTT_RUV_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
      END IF;
      EXIT WHEN c_boleta30%NOTFOUND;
    END LOOP;
    CLOSE c_boleta30;

    -- Agrupando Boletas
    RUV_PR_AGRUPA_RANGO_SUNAT(LsPeriodo || '00');

    -- Insertando Ticket en Tabla Temporal
    DELETE FROM GTT_RUV_REPOR_SUNAT;
    OPEN c_ticket(lsPeriodo);
    LOOP
      FETCH c_ticket BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
             regSunat := reporteSunatRecord(x);
             IF regSunat.Ind_Esta_Comp_Pago = '2' THEN
                regSunat.Mon_Expo := 0.00;
                regSunat.Mon_Base_Impo := 0.00;
                regSunat.Mon_Tota_Oper_Exon:= 0.00;
                regSunat.Mon_Tota_Oper_Inaf:= 0.00;
                regSunat.Mon_Isc:= 0.00;
                regSunat.Mon_Igv:= 0.00;
                regSunat.Mon_Base_Grav_Ivap:= 0.00;
                regSunat.Mon_Ivap:= 0.00;
                regSunat.Mon_Otro_Trib:= 0.00;
                regSunat.Mon_Tota_Comp_Pago:= 0.00;
             END IF;
            INSERT INTO GTT_RUV_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
          END LOOP;
      END IF;
      EXIT WHEN c_ticket%NOTFOUND;
    END LOOP;
    CLOSE c_ticket;

    -- Agrupando tickets
    RUV_PR_AGRUPA_RANGO_SUNAT(LsPeriodo || '00');

    /* Insertando los agrupados en la Tabla Historica */
    INSERT INTO RUV_HISTO_REPOR_SUNAT
    (COD_PERI, NUM_CORR, FEC_EMIS, FEC_VENC,
     TIP_COMP, NUM_SERI_DOCU, NUM_COMP_DOCU_INIC, NUM_COMP_DOCU_FINA,
     TIP_DOCU_IDEN_CLIE, NUM_DOCU_CLIE, NOM_CLIE, MON_EXPO,
     MON_BASE_IMPO, MON_TOTA_OPER_EXON, MON_TOTA_OPER_INAF, MON_ISC,
     MON_IGV, MON_BASE_GRAV_IVAP, MON_IVAP, MON_OTRO_TRIB,
     MON_TOTA_COMP_PAGO, TIP_CAMB, FEC_EMIS_COMP_REFE, TIP_COMP_REFE,
     NUM_SERI_COMP_REFE, NUM_COMP_REFE, IND_ESTA_COMP_PAGO
    )
    SELECT
     COD_PERI, NUM_CORR, FEC_EMIS, FEC_VENC,
     TIP_COMP, NUM_SERI_DOCU, NUM_COMP_DOCU_INIC, NUM_COMP_DOCU_FINA,
     TIP_DOCU_IDEN_CLIE, NUM_DOCU_CLIE, NOM_CLIE, MON_EXPO,
     MON_BASE_IMPO, MON_TOTA_OPER_EXON, MON_TOTA_OPER_INAF, MON_ISC,
     MON_IGV, MON_BASE_GRAV_IVAP, MON_IVAP, MON_OTRO_TRIB,
     MON_TOTA_COMP_PAGO, TIP_CAMB, FEC_EMIS_COMP_REFE, TIP_COMP_REFE,
     NUM_SERI_COMP_REFE, NUM_COMP_REFE, IND_ESTA_COMP_PAGO
    FROM GTT_RUV_REPOR_SUNAT_TEMPO;

  -- llamada a RUV_PR_VALID_ERROR_REPOR_SUNAT
  RUV_PR_VALID_ERROR_REPOR_SUNAT(psCodigoPeriodoInformar || '00',psCodigoPais);

  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_SUNAT_REINI: '||ls_sqlerrm);
  END RUV_PR_GENER_REPOR_SUNAT_REINI;


/******************************************************************************
  Descripcion         : RUV_PR_GENER_PLE5
                        Proceso que genera la data para el reporte RUV de Sunat
                        PLE5
  Fecha Creacion      : 13/02/2016
  Parametros Entrada:
      psCodigoPais  : Codigo de Pais
      psCodigoPeriodoInformar: Periodo a Informar
      psCodigoPeriodoEnviar: Periodo a Enviar
  Autor             : Rosalvina Ramirez Guardia
 *******************************************************************************/

PROCEDURE RUV_PR_GENER_PLE5(psCodigoPais VARCHAR2,
                                      psCodigoPeriodoInformar VARCHAR2,
                                      psCodigoPeriodoEnviar VARCHAR2,
                                      psUsuario VARCHAR2)
  IS
    TYPE reporteSunatTAB  IS TABLE OF RUV_HISTO_REPOR_SUNAT%ROWTYPE;
    reporteSunatRecord reporteSunatTAB;
    regSunat           RUV_HISTO_REPOR_SUNAT%ROWTYPE;
    lsPeriodo          VARCHAR2(6);
    lsTipoFacturaAnulada  VARCHAR2(2);
    searchstr  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    replacestr VARCHAR2(100) := 'a        ';

    /* Cursor de Facturas */
    CURSOR c_factura(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      '',
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)), searchstr, replacestr),
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      - NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as bas_impo,
      --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      0.00 as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      decode((select count(cv.val_tasa) from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis)),0,'0.000',
                     (select to_char(max(cv.val_tasa),'9.999') from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis) )) as tip_camb,
      '' as fec_refe,
      '' as tip_refe,
      ''  as ser_refe,
      ''  as dcto_refe ,
      decode(a.ind_esta,1,'2','1') as estado,
      decode(to_char(a.fec_emis,'DD/MM'),'01/01','A','31/12','C','M')|| a.oid_regi as NUM_CORR_ASIN,
      ABS(a.VAL_DESC) as val_desc
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE (a.TIDO_OID_TIPO_DOCU = 1 OR a.TIDO_OID_TIPO_DOCU = 9)
     AND a.ind_esta <> 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

    /* Cursor de Facturas Anuladas */
    CURSOR c_facturaAnulada(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      '',
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)), searchstr, replacestr),
      0.00 as val_fact_exp,
      0.00 , --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as bas_impo,
      0.00, --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      0.00, --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as imp_tota_inaf,
      0.00 as isc,
      0.00, --ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      0.00, --ABS(a.IMP_TOTA) as imp_tota,
            decode((select count(cv.val_tasa) from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis)),0,'0.000',
                     (select to_char(max(cv.val_tasa),'9.999') from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis) )) as tip_camb,
      '' as fec_refe,
      '00' as tip_refe,
      ''  as ser_refe,
      ''  as dcto_refe ,
      decode(a.ind_esta,1,'2','1') as estado,
      decode(to_char(a.fec_emis,'DD/MM'),'01/01','A','31/12','C','M')|| a.oid_regi as NUM_CORR_ASIN,
      0.00 as val_desc
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE (a.TIDO_OID_TIPO_DOCU = 1 OR a.TIDO_OID_TIPO_DOCU = 9)
     AND a.ind_esta = 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;


   /* Cursor de Notas de Debito */
    CURSOR c_debito(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      '',
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)), searchstr, replacestr),
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) -
      NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as bas_impo,
      --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      0.00 as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      decode((select count(cv.val_tasa) from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis)),0,'0.000',
                     (select to_char(max(cv.val_tasa),'9.999') from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis) )) as tip_camb,
      TO_CHAR(a.FEC_EMIS_REFE,'DD/MM/YYYY'),
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_TIPO_DOCU_REFE,a.pais_oid_pais),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE_REFER(a.VAL_SERI_DOCU_REFE),
      a.VAL_NUME_DOCU_REFE,
      decode(a.ind_esta,1,'2','1') as estado,
      decode(to_char(a.fec_emis,'DD/MM'),'01/01','A','31/12','C','M')|| a.oid_regi as NUM_CORR_ASIN,
      ABS(a.VAL_DESC) as val_desc
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 34
     AND a.ind_esta <> 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

   /* Cursor de Notas de Debito Anuladas */
    CURSOR c_debitoAnulada(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      '',
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)), searchstr, replacestr),
      0.00 as val_fact_exp,
      0.00, --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as bas_impo,
      0.00, --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      0.00, --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as imp_tota_inaf,
      0.00 as isc,
      0.00, --ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      0.00, --ABS(a.IMP_TOTA) as imp_tota,
      decode((select count(cv.val_tasa) from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis)),0,'0.000',
                     (select to_char(max(cv.val_tasa),'9.999') from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis) )) as tip_camb,
      TO_CHAR(a.FEC_EMIS_REFE,'DD/MM/YYYY'),
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_TIPO_DOCU_REFE,a.pais_oid_pais),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE_REFER(a.VAL_SERI_DOCU_REFE),
      a.VAL_NUME_DOCU_REFE,
      decode(a.ind_esta,1,'2','1') as estado,
      decode(to_char(a.fec_emis,'DD/MM'),'01/01','A','31/12','C','M')|| a.oid_regi as NUM_CORR_ASIN,
      0.00 as val_desc      
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 34
     AND a.ind_esta = 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

   /* Cursor de Notas de Credito */
   CURSOR c_credito(vsPeriodo VARCHAR2) IS
   SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      '',
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)), searchstr, replacestr),
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      (-1) * ( abs(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) -
      abs(NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00))  ) as bas_impo,
      0.00 as imp_tota_exo, --(-1) * abs(NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00)) as imp_tota_exo,
      (-1) * --abs(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
       decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      (-1) * abs(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      (-1) * abs(a.IMP_TOTA) as imp_tota,
      decode((select count(cv.val_tasa) from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis)),0,'0.000',
                     (select to_char(max(cv.val_tasa),'9.999') from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis) )) as tip_camb,
      TO_CHAR(a.FEC_EMIS_REFE,'DD/MM/YYYY'),
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_TIPO_DOCU_REFE,a.pais_oid_pais),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE_REFER(a.VAL_SERI_DOCU_REFE),
      a.VAL_NUME_DOCU_REFE,
      decode(a.ind_esta,1,'2','1') as estado,
      decode(to_char(a.fec_emis,'DD/MM'),'01/01','A','31/12','C','M')|| a.oid_regi as NUM_CORR_ASIN,
      (-1) * abs(a.val_desc) as val_desc
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU in (31,32,33)
     AND a.ind_esta <> 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

   /* Cursor de Notas de Credito Anuladas*/
   CURSOR c_creditoAnulada(vsPeriodo VARCHAR2) IS
   SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      '',
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(a.TIDO_OID_TIPO_DOCU, a.sbac_oid_sbac, A.TDOC_OID_TIPO_DOCU, A.PAIS_OID_PAIS),A.VAL_NUME_IDEN_FISC),
      translate(trim(SUBSTR(NVL(TRIM(A.VAL_NOMB), trim(a.val_ape1) || ' ' || trim(a.val_ape2)|| ' ' || trim(a.val_nom1) || ' ' || trim(a.val_nom2)),1,60)), searchstr, replacestr),
      0.00 as val_fact_exp,
      0.00, --(-1) * abs(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as bas_impo,
      0.00, --(-1) * abs(NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00)) as imp_tota_exo,
      0.00, --(-1) * abs(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) as imp_tota_inaf,
      0.00 as isc,
      0.00, --(-1) * abs(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      0.00, --(-1) * abs(a.IMP_TOTA) as imp_tota,
      decode((select count(cv.val_tasa) from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis)),0,'0.000',
                     (select to_char(max(cv.val_tasa),'9.999') from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis) )) as tip_camb,
      TO_CHAR(a.FEC_EMIS_REFE,'DD/MM/YYYY'),
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_TIPO_DOCU_REFE,a.pais_oid_pais),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE_REFER(a.VAL_SERI_DOCU_REFE),
      a.VAL_NUME_DOCU_REFE,
      decode(a.ind_esta,1,'2','1') as estado,
      decode(to_char(a.fec_emis,'DD/MM'),'01/01','A','31/12','C','M')|| a.oid_regi as NUM_CORR_ASIN,
      0.00 as val_desc    
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU in (31,32,33)
     AND a.ind_esta = 1
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;


   /* Cursor de Boletas */
   CURSOR c_boleta29(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      '',
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      '',
      '',
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) -
      NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as bas_impo,
      0.00 as imp_tota_exo, --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      decode((select count(cv.val_tasa) from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis)),0,'0.000',
                     (select to_char(max(cv.val_tasa),'9.999') from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis) )) as tip_camb,
      '' as fec_refe,
      '' as tip_refe,
      ''  as ser_refe,
      ''  as dcto_refe ,
      '1' as estado,
      decode(to_char(a.fec_emis,'DD/MM'),'01/01','A','31/12','C','M')|| a.oid_regi as NUM_CORR_ASIN,
      ABS(a.val_desc) as val_desc
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 29
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo
     and a.ind_esta = 0;

   CURSOR c_boleta30(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      '',
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      '',
      '',
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(a.val_base_impo_neto) + DECODE (a.TAIM_OID_TASA_IMPU,2002,0,ABS(a.VAL_DESC)) - ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) AS bas_impo,
      0.00  as imp_tota_exo, --NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      decode((select count(cv.val_tasa) from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis)),0,'0.000',
                     (select to_char(max(cv.val_tasa),'9.999') from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis) )) as tip_camb,
      '' as fec_refe,
      '' as tip_refe,
      ''  as ser_refe,
      ''  as dcto_refe ,
      '1' as estado,
      decode(to_char(a.fec_emis,'DD/MM'),'01/01','A','31/12','C','M')|| a.oid_regi as NUM_CORR_ASIN,
      ABS(a.val_desc) as val_desc
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 30
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo
     and a.ind_esta = 0;

   /* Cursor de Ticket */
   CURSOR c_ticket(vsPeriodo VARCHAR2) IS
    SELECT
      TO_CHAR(a.fec_emis,'YYYYMM')||'00',
      TRIM(TO_CHAR(a.oid_regi)),
      TO_CHAR(a.fec_emis,'DD/MM/YYYY'),
      '',
      VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(a.TIDO_OID_TIPO_DOCU, A.PAIS_OID_PAIS),
      RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      TRIM(TO_CHAR(A.VAL_NUME_DOCU_LEGA)),
      '0',
      '',
      '',
      --0.00
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo)),0.00) as val_fact_exp,
      ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_BASE_IMPOR(a.TAIM_OID_TASA_IMPU,a.val_base_impo)) -
      NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00)  as bas_impo,
      0.00  as imp_tota_exo,--NVL(DECODE(a.TAIM_OID_TASA_IMPU,2002,0.00,ABS(a.VAL_DESC)), 0.00) as imp_tota_exo,
      --ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))
      decode(RUV_PKG_PROCE.RUV_FN_VALID_NUMER_SERIE(a.TIDO_OID_TIPO_DOCU, a.VAL_SERI_DOCU_LEGA)
             ,'0017',0.00,ABS(RUV_PKG_PROCE.RUV_FN_OBTIE_OPERA_INAFE(a.TAIM_OID_TASA_IMPU,a.val_base_impo))) as imp_tota_inaf,
      0.00 as isc,
      ABS(a.IMP_IMPU) as igv,
      0.00 as base_impo_grav,
      0.00 as ivap,
      0.00 as otro,
      ABS(a.IMP_TOTA) as imp_tota,
      decode((select count(cv.val_tasa) from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis)),0,'0.000',
                     (select to_char(max(cv.val_tasa),'9.999') from RUV_TIPO_CAMBIO cv where trunc(cv.fec_proc) = trunc(a.fec_emis) )) as tip_camb,
      '' as fec_refe,
      '' as tip_refe,
      ''  as ser_refe,
      ''  as dcto_refe ,
      decode(a.ind_esta,1,'2','1') as estado,
      decode(to_char(a.fec_emis,'DD/MM'),'01/01','A','31/12','C','M')|| a.oid_regi as NUM_CORR_ASIN,
      ABS(a.val_desc) as val_desc
   FROM FAC_REGIS_UNICO_VENTA a
   WHERE a.TIDO_OID_TIPO_DOCU = 35
     and TO_CHAR(a.FEC_EMIS,'YYYYMM') = vsPeriodo;

   lsNroDocumentoFacturaAnulada RUV_HISTO_REPOR_SUNAT.NUM_CORR%TYPE;
   lsFechaFacturaAnulada        RUV_HISTO_REPOR_SUNAT.FEC_EMIS%TYPE;
  BEGIN
    DELETE FROM GTT_RUV_REPOR_SUNAT;
    DELETE FROM GTT_RUV_REPOR_SUNAT_TEMPO;

    lsPeriodo := psCodigoPeriodoEnviar ;
    DELETE FROM RUV_HISTO_REPOR_SUNAT A
    WHERE A.COD_PERI = LsPeriodo || '00';

    /* Insertando Facturas */
    OPEN c_factura(lsPeriodo);
    LOOP
      FETCH c_factura BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FORALL x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST
            INSERT INTO RUV_HISTO_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
      END IF;
      EXIT WHEN c_factura%NOTFOUND;
    END LOOP;
    CLOSE c_factura;

    /* obteniendo 1era Factura Anulada de Serie '065' */
    BEGIN
      SELECT
         oid_regi, fec_emis,
         VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(x.TIDO_OID_TIPO_DOCU,x.pais_oid_pais)
      INTO lsNroDocumentoFacturaAnulada,
           lsFechaFacturaAnulada,
           lsTipoFacturaAnulada
      FROM
         (SELECT TRIM(TO_CHAR(a.oid_regi)) oid_regi,
                TO_CHAR(a.fec_emis,'DD/MM/YYYY') fec_emis,
                TIDO_OID_TIPO_DOCU,
                pais_oid_pais
          FROM FAC_REGIS_UNICO_VENTA a
          WHERE (a.TIDO_OID_TIPO_DOCU = 1 OR a.TIDO_OID_TIPO_DOCU = 9)
            AND a.ind_esta = 1
            and TO_CHAR(a.FEC_EMIS,'YYYYMM') = lsPeriodo
            AND a.val_seri_docu_lega = TIPO_SERIE_FACTURA_REFE
          ORDER BY a.fec_emis, a.oid_regi
          ) x
      WHERE ROWNUM = 1;
    EXCEPTION
    WHEN no_data_found THEN
        lsNroDocumentoFacturaAnulada := NULL;
        lsFechaFacturaAnulada := NULL;
    END;

    /* Insertando Facturas Anuladas */
    OPEN c_facturaAnulada(lsPeriodo);
    LOOP
      FETCH c_facturaAnulada BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
              regSunat := reporteSunatRecord(x);
              regSunat.Tip_Docu_Iden_Clie := RUC_SUNAT;
              regSunat.Num_Docu_Clie := RUC_ANULADAS;
              regSunat.Nom_Clie := CLIENTE_ANULADAS;

              INSERT INTO RUV_HISTO_REPOR_SUNAT
              VALUES regSunat;
          END LOOP;
      END IF;
      EXIT WHEN c_facturaAnulada%NOTFOUND;
    END LOOP;
    CLOSE c_facturaAnulada;

    /* Insertando Notas de Debito */
    OPEN c_debito(lsPeriodo);
    LOOP
      FETCH c_debito BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FORALL x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST
            INSERT INTO RUV_HISTO_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
      END IF;
      EXIT WHEN c_debito%NOTFOUND;
    END LOOP;
    CLOSE c_debito;

   /* Insertando Notas de Debito Anuladas */
    OPEN c_debitoAnulada(lsPeriodo);
    LOOP
      FETCH c_debitoAnulada BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
              regSunat := reporteSunatRecord(x);
              regSunat.Tip_Docu_Iden_Clie := RUC_SUNAT;
              regSunat.Num_Docu_Clie := RUC_ANULADAS;
              regSunat.Nom_Clie := CLIENTE_ANULADAS;

              IF regSunat.IND_ESTA_COMP_PAGO = '2' THEN
                 regSunat.FEC_EMIS_COMP_REFE := '';
                 regSunat.TIP_COMP_REFE := '';
                 regSunat.NUM_COMP_REFE := '';
                 regSunat.Num_Seri_Comp_Refe := '';
              END IF;

              INSERT INTO RUV_HISTO_REPOR_SUNAT
              VALUES regSunat;
          END LOOP;
      END IF;
      EXIT WHEN c_debitoAnulada%NOTFOUND;
    END LOOP;
    CLOSE c_debitoAnulada;


    /* Insertando Notas de Credito*/
    OPEN c_credito(lsPeriodo);
    LOOP
      FETCH c_credito BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
            regSunat := reporteSunatRecord(x);
            IF regSunat.Mon_Base_Impo = 0.00 AND
              regSunat.Mon_Tota_Comp_Pago = 0.00 AND
              regSunat.Mon_Igv = 0.00 AND
              regSunat.Num_Comp_refe IS NULL THEN
              regSunat.Fec_Emis_Comp_refe := lsFechaFacturaAnulada;
              regSunat.Num_Comp_refe := lsNroDocumentoFacturaAnulada;
              regSunat.Num_Seri_Comp_refe := TIPO_SERIE_FACTURA_REFE_GRABA;
              regSunat.Tip_Comp_Refe := lsTipoFacturaAnulada;
            END IF;
            INSERT INTO RUV_HISTO_REPOR_SUNAT
            VALUES regSunat;

          END LOOP;
      END IF;
      EXIT WHEN c_credito%NOTFOUND;
    END LOOP;
    CLOSE c_credito;

   /* Insertando Notas de Credito Anuladas */
    OPEN c_creditoAnulada(lsPeriodo);
    LOOP
      FETCH c_creditoAnulada BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
              regSunat := reporteSunatRecord(x);
              regSunat.Tip_Docu_Iden_Clie := RUC_SUNAT;
              regSunat.Num_Docu_Clie := RUC_ANULADAS;
              regSunat.Nom_Clie := CLIENTE_ANULADAS;

              IF regSunat.IND_ESTA_COMP_PAGO = '2' THEN
                 regSunat.FEC_EMIS_COMP_REFE := '';
                 regSunat.TIP_COMP_REFE := '';
                 regSunat.NUM_COMP_REFE := '';
                 regSunat.Num_Seri_Comp_refe := '';
              ELSE
              regSunat.Fec_Emis_Comp_refe := lsFechaFacturaAnulada;
                 regSunat.Tip_Comp_Refe := lsTipoFacturaAnulada;
              regSunat.Num_Comp_refe := lsNroDocumentoFacturaAnulada;
              regSunat.Num_Seri_Comp_refe := TIPO_SERIE_FACTURA_REFE_GRABA;
              END IF;

              INSERT INTO RUV_HISTO_REPOR_SUNAT
              VALUES regSunat;
          END LOOP;
      END IF;
      EXIT WHEN c_creditoAnulada%NOTFOUND;
    END LOOP;
    CLOSE c_creditoAnulada;

    -- Insertando Boletas en Tabla Temporal
    OPEN c_boleta29(lsPeriodo);
    LOOP
      FETCH c_boleta29 BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FORALL x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST
            INSERT INTO GTT_RUV_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
      END IF;
      EXIT WHEN c_boleta29%NOTFOUND;
    END LOOP;
    CLOSE c_boleta29;

    OPEN c_boleta30(lsPeriodo);
    LOOP
      FETCH c_boleta30 BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FORALL x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST
            INSERT INTO GTT_RUV_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
      END IF;
      EXIT WHEN c_boleta30%NOTFOUND;
    END LOOP;
    CLOSE c_boleta30;

    -- Agrupando Boletas
    RUV_PR_AGRUPA_RANGO_SUNAT(LsPeriodo || '00');

    -- Insertando Ticket en Tabla Temporal
    DELETE FROM GTT_RUV_REPOR_SUNAT;
    OPEN c_ticket(lsPeriodo);
    LOOP
      FETCH c_ticket BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      IF reporteSunatRecord.COUNT > 0 THEN
          FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
             regSunat := reporteSunatRecord(x);
             IF regSunat.Ind_Esta_Comp_Pago = '2' THEN
                regSunat.Mon_Expo := 0.00;
                regSunat.Mon_Base_Impo := 0.00;
                regSunat.Mon_Tota_Oper_Exon:= 0.00;
                regSunat.Mon_Tota_Oper_Inaf:= 0.00;
                regSunat.Mon_Isc:= 0.00;
                regSunat.Mon_Igv:= 0.00;
                regSunat.Mon_Base_Grav_Ivap:= 0.00;
                regSunat.Mon_Ivap:= 0.00;
                regSunat.Mon_Otro_Trib:= 0.00;
                regSunat.Mon_Tota_Comp_Pago:= 0.00;
             END IF;
            INSERT INTO GTT_RUV_REPOR_SUNAT
            VALUES reporteSunatRecord(x);
          END LOOP;
      END IF;
      EXIT WHEN c_ticket%NOTFOUND;
    END LOOP;
    CLOSE c_ticket;

    -- Agrupando tickets
    RUV_PR_AGRUPA_RANGO_SUNAT(LsPeriodo || '00');

    /* Insertando los agrupados en la Tabla Historica */
    INSERT INTO RUV_HISTO_REPOR_SUNAT
    (COD_PERI, NUM_CORR, FEC_EMIS, FEC_VENC,
     TIP_COMP, NUM_SERI_DOCU, NUM_COMP_DOCU_INIC, NUM_COMP_DOCU_FINA,
     TIP_DOCU_IDEN_CLIE, NUM_DOCU_CLIE, NOM_CLIE, MON_EXPO,
     MON_BASE_IMPO, MON_TOTA_OPER_EXON, MON_TOTA_OPER_INAF, MON_ISC,
     MON_IGV, MON_BASE_GRAV_IVAP, MON_IVAP, MON_OTRO_TRIB,
     MON_TOTA_COMP_PAGO, TIP_CAMB, FEC_EMIS_COMP_REFE, TIP_COMP_REFE,
     NUM_SERI_COMP_REFE, NUM_COMP_REFE, IND_ESTA_COMP_PAGO
    )
    SELECT
     COD_PERI, NUM_CORR, FEC_EMIS, FEC_VENC,
     TIP_COMP, NUM_SERI_DOCU, NUM_COMP_DOCU_INIC, NUM_COMP_DOCU_FINA,
     TIP_DOCU_IDEN_CLIE, NUM_DOCU_CLIE, NOM_CLIE, MON_EXPO,
     MON_BASE_IMPO, MON_TOTA_OPER_EXON, MON_TOTA_OPER_INAF, MON_ISC,
     MON_IGV, MON_BASE_GRAV_IVAP, MON_IVAP, MON_OTRO_TRIB,
     MON_TOTA_COMP_PAGO, TIP_CAMB, FEC_EMIS_COMP_REFE, TIP_COMP_REFE,
     NUM_SERI_COMP_REFE, NUM_COMP_REFE, IND_ESTA_COMP_PAGO
    FROM GTT_RUV_REPOR_SUNAT_TEMPO;

  -- llamada a RUV_PR_VALID_ERROR_REPOR_SUNAT
  RUV_PR_VALID_ERROR_REPOR_SUNAT(psCodigoPeriodoInformar || '00',psCodigoPais);

  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_PLE5: '||ls_sqlerrm);
  END RUV_PR_GENER_PLE5;

/******************************************************************************
  Descripcion         : RUV_PR_GENER_REPOR_SUNAT_REINI
                        Proceso que genera la data para el reporte RUV de Sunat
                        Contables DESDE CERO
  Fecha Creacion      : 14/07/2010
  Autor             : Carlos Bazalar La Rosa
 *******************************************************************************/
  PROCEDURE RUV_PR_AGRUPA_RANGO_SUNAT(psPeriodo VARCHAR2)
  IS
    CURSOR c_temporal IS
     SELECT * FROM GTT_RUV_REPOR_SUNAT A
     ORDER BY A.FEC_EMIS, A.NUM_SERI_DOCU, A.NUM_COMP_DOCU_INIC;

    TYPE reporteSunatTAB  IS TABLE OF RUV_HISTO_REPOR_SUNAT%ROWTYPE;
    reporteSunatRecord reporteSunatTAB;
    regSunat           RUV_HISTO_REPOR_SUNAT%ROWTYPE;
    MONTO_MAXIMO       NUMBER:=700.00;

    lsFechaEmis        GTT_RUV_REPOR_SUNAT.Fec_Emis%TYPE;
    lsTipoDocumIden    GTT_RUV_REPOR_SUNAT.TIP_DOCU_IDEN_CLIE%TYPE;
    lsNumDocumIden     GTT_RUV_REPOR_SUNAT.NUM_DOCU_CLIE%TYPE;
    lsNumSerieDocum    GTT_RUV_REPOR_SUNAT.NUM_SERI_DOCU%TYPE;
    lsNumDocum         GTT_RUV_REPOR_SUNAT.NUM_COMP_DOCU_INIC%TYPE;
    lsAnulado          VARCHAR2(2);
    regFactura         FAC_REGIS_UNICO_VENTA%ROWTYPE;
    lnMonto            NUMBER;
    lnIdCorrelativo    NUMBER;
    lbInsertar         BOOLEAN;
    lbInsertarSiguiente BOOLEAN;
    indice              NUMBER:=0;
    pagina              NUMBER:=0;
  BEGIN
    lnIdCorrelativo := -1;
    lsFechaEmis := '-1';
    lsNumSerieDocum := '-1';
    lsAnulado := '-1';
    lnMonto := 0.00;
    lbInsertarSiguiente := FALSE;
    OPEN c_temporal;
    LOOP
      FETCH c_temporal BULK COLLECT INTO reporteSunatRecord LIMIT W_FILAS;
      pagina := pagina + 1;
      IF reporteSunatRecord.COUNT > 0 THEN
         FOR x IN reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP
             lbInsertar := FALSE;
             indice := x;
             IF indice = 95 THEN
                indice:= x;
             END IF;
             regSunat := reporteSunatRecord(x);
             lnMonto := lnMonto + regSunat.Mon_Tota_Comp_Pago;

             IF regSunat.Mon_Tota_Comp_Pago > MONTO_MAXIMO THEN
                lbInsertar := TRUE;
                lbInsertarSiguiente := TRUE;

                /* buscando datos de la consultora */
                SELECT A.*
                INTO regFactura
                FROM FAC_REGIS_UNICO_VENTA A
                WHERE A.OID_REGI = TO_NUMBER(regSunat.Num_Corr);

                regSunat.Tip_Docu_Iden_Clie := RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(regFactura.TIDO_OID_TIPO_DOCU, regFactura.sbac_oid_sbac, regFactura.TDOC_OID_TIPO_DOCU, regFactura.PAIS_OID_PAIS);
                regSunat.Num_Docu_Clie := RUV_PKG_PROCE.RUV_FN_VALID_DOCUM_IDENTI(RUV_PKG_PROCE.RUV_FN_DEVUE_HOMO_TIPO_DOCUM(regFactura.TIDO_OID_TIPO_DOCU, regFactura.sbac_oid_sbac, regFactura.TDOC_OID_TIPO_DOCU, regFactura.PAIS_OID_PAIS),regFactura.VAL_NUME_IDEN_FISC);
                regSunat.Nom_Clie:=  NVL(trim(SUBSTR(NVL(TRIM(regFactura.VAL_NOMB), trim(regFactura.val_ape1) || ' ' || trim(regFactura.val_ape2)|| ' ' || trim(regFactura.val_nom1) || ' ' || trim(regFactura.val_nom2)),1,60)),DEFAULT_CHAR);

                INSERT INTO GTT_RUV_REPOR_SUNAT_TEMPO VALUES regSunat;
             END IF;

             IF (NOT lbInsertar AND lbInsertarSiguiente) OR
                (NOT lbInsertar AND
                (lsFechaEmis <> regSunat.Fec_Emis OR
                lsNumSerieDocum <> regSunat.Num_Seri_Docu OR
                lsAnulado <> regSunat.Ind_Esta_Comp_Pago)) THEN
                lbInsertar := TRUE;
                lbInsertarSiguiente := FALSE;
                INSERT INTO GTT_RUV_REPOR_SUNAT_TEMPO VALUES regSunat;
             END IF;

             IF lbInsertar THEN
                lsFechaEmis := regSunat.Fec_Emis;
                lsNumSerieDocum := regSunat.Num_Seri_Docu;
                lnMonto := regSunat.Mon_Tota_Comp_Pago;
                lnIdCorrelativo := regSunat.Num_Corr;
                lsAnulado := regSunat.Ind_Esta_Comp_Pago;
             ELSE
                UPDATE GTT_RUV_REPOR_SUNAT_TEMPO A
                SET A.NUM_COMP_DOCU_FINA = regSunat.Num_Comp_Docu_Inic,
                    A.MON_EXPO = A.MON_EXPO + regSunat.mon_expo,
                    A.MON_BASE_IMPO = A.MON_BASE_IMPO + regSunat.Mon_Base_Impo,
                    A.MON_TOTA_OPER_EXON = A.MON_TOTA_OPER_EXON + regSunat.Mon_Tota_Oper_Exon,
                    A.MON_TOTA_OPER_INAF = A.MON_TOTA_OPER_INAF + regSunat.Mon_Tota_Oper_Inaf,
                    A.MON_ISC = A.MON_ISC + regSunat.mon_isc,
                    A.MON_IGV = A.MON_IGV + regSunat.mon_igv,
                    A.MON_BASE_GRAV_IVAP = A.MON_BASE_GRAV_IVAP + regSunat.Mon_Base_Grav_Ivap,
                    A.MON_IVAP = A.MON_IVAP +  regSunat.Mon_Ivap,
                    A.MON_OTRO_TRIB = A.MON_OTRO_TRIB + regSunat.Mon_Otro_Trib,
                    A.MON_TOTA_COMP_PAGO = A.MON_TOTA_COMP_PAGO + regSunat.Mon_Tota_Comp_Pago
                WHERE A.COD_PERI = psPeriodo
                  AND A.NUM_CORR = lnIdCorrelativo;
             END IF;

         END LOOP;
      END IF;
      EXIT WHEN c_temporal%NOTFOUND;
    END LOOP;
    CLOSE c_temporal;
 EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_AGRUPA_RANGO_SUNAT: Pagina:'||pagina||' Indice:'||indice||ls_sqlerrm);
 END RUV_PR_AGRUPA_RANGO_SUNAT;

/***************************************************************************
   Descripcion       : Procedimiento para validar errores del Reporte RUV Sunat
   Fecha Creacion    : 25/08/2010
   Autor             : Carlos Diaz Valverde
   Parametros        :
      psCodigoPeriodo    Codigo Periodo
      psCodigoPais  :    Codigo de Pais
   ***************************************************************************/
PROCEDURE RUV_PR_VALID_ERROR_REPOR_SUNAT (
   psCodigoPeriodo    VARCHAR2,
   psCodigoPais       VARCHAR2
 )
IS
  cursor c_reporte is
    select    *
    from      ruv_histo_repor_sunat
    where     cod_peri = psCodigoPeriodo
    order by  1,2;

    TYPE reporteSunatTAB  IS TABLE OF RUV_HISTO_REPOR_SUNAT%ROWTYPE;
    reporteSunatRecord    reporteSunatTAB;
    regSunat              RUV_HISTO_REPOR_SUNAT%ROWTYPE;

    indice                NUMBER:=0;
    pagina                NUMBER:=0;

BEGIN

     -- Eliminar filas x periodo consultado
     delete
     from      ruv_histo_repor_sunat_error
     where     cod_peri = psCodigoPeriodo;

     -- Logica de inserción de validaciones
     open c_reporte;
     loop

         fetch c_reporte bulk collect into reporteSunatRecord limit W_FILAS;
         pagina := pagina + 1;
         if reporteSunatRecord.count > 0 then
           for x in reporteSunatRecord.FIRST .. reporteSunatRecord.LAST LOOP

             indice := x;
             regSunat := reporteSunatRecord(x);

             -- 1.0 Nombre en Blanco (fact, nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '01' or regSunat.tip_comp = '07' or regSunat.tip_comp = '08') and
                regSunat.nom_clie = '-'
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Nombre en Blanco (fact / nc / nd)'
               );
             end if;

             -- 2.0 Tipo Documento de Identidad (fact, nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '01' or regSunat.tip_comp = '07' or regSunat.tip_comp = '08') and
                regSunat.tip_docu_iden_clie = '-'
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Tipo Documento de Identidad (fact / nc / nd)'
               );
             end if;

             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                ((regSunat.Tip_Docu_Iden_Clie = '1' AND length(regSunat.Num_Docu_Clie) <> 8) OR
                 (regSunat.Tip_Docu_Iden_Clie = '6' AND length(regSunat.Num_Docu_Clie) <> 11)
                ) THEN
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Nro de Documento de Identidad no posee la longitud adecuada(fact / nc / nd)'
               );
             end if;

             -- 3.0 Documento de Identidad (fact, nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '01' or regSunat.tip_comp = '07' or regSunat.tip_comp = '08') and
                regSunat.num_docu_clie = '-'
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Documento de Identidad (fact / nc / nd)'
               );
             end if;

             -- 4.0 Fecha referencia (nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '07' or regSunat.tip_comp = '08') and
                regSunat.fec_emis_comp_refe is null
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Fecha referencia (nc / nd)'
               );
             end if;

             -- 5.0 Tipo Documento referencia (nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '07' or regSunat.tip_comp = '08') and
                regSunat.tip_comp_refe  is null
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Tipo Documento referencia (nc / nd)'
               );
             end if;

             -- 6.0 Numero de Serie referencia (nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '07' or regSunat.tip_comp = '08') and
                regSunat.num_seri_comp_refe  is null
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Numero de Serie referencia (nc / nd)'
               );
             end if;

             -- 7.0 Numero de Comprobante referencia (nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '07' or regSunat.tip_comp = '08') and
                regSunat.num_comp_refe  is null
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Numero de Comprobante referencia (nc / nd)'
               );
             end if;

             -- 8.0 Nombre con Error (fact, nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '01' or regSunat.tip_comp = '07' or regSunat.tip_comp = '08') and
                instrb(regSunat.nom_clie,'|') > 0
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Nombre con Error (fact / nc / nd)'
               );
             end if;

             -- 9.0 Documento Identidad 1 RUC modulo 11(fact,bv, tickets, nc, nd)
             BEGIN
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (
                 regSunat.tip_comp = '01' or regSunat.tip_comp = '03' or regSunat.tip_comp = '12' or
                 regSunat.tip_comp = '07' or regSunat.tip_comp = '08'
                ) and
                regSunat.tip_docu_iden_clie = '6' and
                (

                 /*substr(regSunat.num_docu_clie,length(regSunat.num_docu_clie)) <>
                   sto_pkg_gener.sto_fn_devue_modul_once(substr(regSunat.num_docu_clie,0,length(regSunat.num_docu_clie)-1), psCodigoPais)*/
                   mae_pkg_proce_clien.MAE_FN_VALID_NUMER_RUC_MOD11(regSunat.num_docu_clie)=0
                )
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Documento Identidad 1 RUC modulo 11 (fact / bv / tickets / nc / nd)'
               );
             end if;
             EXCEPTION
               WHEN OTHERS THEN
                 -- En caso halla excepción por error de campo númerico no valido
                 insert into ruv_histo_repor_sunat_error values (
                     regSunat.cod_peri,
                     regSunat.num_corr,
                     'Documento Identidad 1 RUC modulo 11 (fact / bv / tickets / nc / nd)'
                 );
             END;

             -- 10.0 Documento Identidad 2 dni(fact,bv, tickets, nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (
                 regSunat.tip_comp = '01' or regSunat.tip_comp = '03' or regSunat.tip_comp = '12' or
                 regSunat.tip_comp = '07' or regSunat.tip_comp = '08'
                ) and
                regSunat.tip_docu_iden_clie = '1' and
                length(regSunat.num_docu_clie) <> 8
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Documento Identidad 2 dni (fact / bv / tickets / nc / nd)'
               );
             end if;

             -- 11.0 Tipo Documento de Identidad NO ES NI DNI NI RUC (fact, nc, nd)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '01' or regSunat.tip_comp = '07' or regSunat.tip_comp = '08') and
                regSunat.tip_docu_iden_clie = '0'
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Tipo Documento de Identidad NO ES NI DNI NI RUC (fact / nc / nd)'
               );
             end if;

             -- 12.0 Nombre con Error (bv, fact)
             if regSunat.IND_ESTA_COMP_PAGO <> '2' and
                (regSunat.tip_comp = '03' or regSunat.tip_comp = '12') and
                (regSunat.tip_docu_iden_clie = '1' or regSunat.tip_docu_iden_clie = '6') and
                instrb(regSunat.nom_clie,'|') > 0
             then
               insert into ruv_histo_repor_sunat_error values (
                   regSunat.cod_peri,
                   regSunat.num_corr,
                   'Nombre con Error (bv / fact)'
               );
             end if;

           end loop;
         end if;

         exit when c_reporte%notfound;
     end loop;
     Close C_Reporte;


EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_VALID_ERROR_REPOR_SUNAT: Pagina:'||pagina||' Indice:'||indice||' - '||ls_sqlerrm);

END RUV_PR_VALID_ERROR_REPOR_SUNAT;



/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Sunat
Fecha Creacion    : 19/08/2010
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_GENER_REPOR_SUNAT_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lsCodigoPais        VARCHAR2(3);
  CURSOR c_interfaz IS
  SELECT
    cod_peri ,
    trim(num_corr) as num_corr,
    trim(NUM_CORR_ASIN) as NUM_CORR_ASIN,
    trim(fec_emis) as fec_emis,
    trim(fec_venc) as fec_venc ,
    tip_comp,
    trim(num_seri_docu) as num_seri_docu,
    trim(num_comp_docu_inic) as num_comp_docu_inic,
    trim(num_comp_docu_fina) as num_comp_docu_fina,
    trim(tip_docu_iden_clie) as tip_docu_iden_clie,
    trim(num_docu_clie) as num_docu_clie,
    trim(nom_clie) as nom_clie,
    mon_expo,
    mon_base_impo,
    val_desc,
    mon_tota_oper_exon,
    mon_tota_oper_inaf,
    mon_isc,
    mon_igv,
    mon_base_grav_ivap,
    mon_ivap,
    mon_otro_trib,
    mon_tota_comp_pago,
    tip_camb,
    trim(fec_emis_comp_refe) as fec_emis_comp_refe,
    decode(trim(tip_comp_refe),'12','03',trim(tip_comp_refe)) as tip_comp_refe,
    trim(num_seri_comp_refe) as num_seri_comp_refe,
    trim(num_comp_refe) as num_comp_refe,
    IND_ESTA_COMP_PAGO
  FROM ruv_histo_repor_sunat
  WHERE
    COD_PERI = pscodigoPeriodoInformar
  ORDER BY cod_peri, tip_comp, fec_emis, num_seri_docu, num_comp_docu_inic;

TYPE interfazTipo IS RECORD (
 cod_peri ruv_histo_repor_sunat.cod_peri%TYPE,
 num_corr ruv_histo_repor_sunat.num_corr%TYPE,
 num_corr_asin ruv_histo_repor_sunat.NUM_CORR_ASIN%TYPE,
 fec_emis ruv_histo_repor_sunat.fec_emis%TYPE,
 fec_venc ruv_histo_repor_sunat.fec_venc%TYPE,
 tip_comp ruv_histo_repor_sunat.tip_comp%TYPE,
 num_seri_docu ruv_histo_repor_sunat.num_seri_docu%TYPE,
 num_comp_docu_inic ruv_histo_repor_sunat.num_comp_docu_inic%TYPE,
 num_comp_docu_fina ruv_histo_repor_sunat.num_comp_docu_fina%TYPE,
 tip_docu_iden_clie ruv_histo_repor_sunat.tip_docu_iden_clie%TYPE,
 num_docu_clie ruv_histo_repor_sunat.num_docu_clie%TYPE,
 nom_clie ruv_histo_repor_sunat.nom_clie%TYPE,
 mon_expo NUMBER,
 mon_base_impo NUMBER,
 val_desc NUMBER,
 mon_tota_oper_exon NUMBER,
 mon_tota_oper_inaf NUMBER,
 mon_isc NUMBER,
 mon_igv NUMBER,
 mon_base_grav_ivap NUMBER,
 mon_ivap NUMBER,
 mon_otro_trib NUMBER,
 mon_tota_comp_pago NUMBER,
 tip_camb NUMBER,
 fec_emis_comp_refe ruv_histo_repor_sunat.fec_emis_comp_refe%TYPE,
 tip_comp_refe ruv_histo_repor_sunat.tip_comp_refe%TYPE,
 num_seri_comp_refe ruv_histo_repor_sunat.num_seri_comp_refe%TYPE,
 num_comp_refe ruv_histo_repor_sunat.num_comp_refe%TYPE,
 IND_ESTA_COMP_PAGO  ruv_histo_repor_sunat.IND_ESTA_COMP_PAGO%TYPE
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).cod_peri ||','||
                    interfazRecord(x).num_corr ||','||
                    interfazRecord(x).num_corr_asin ||','||
                    interfazRecord(x).fec_emis ||','||
                    interfazRecord(x).fec_venc ||','||
                    interfazRecord(x).tip_comp ||','||
                    interfazRecord(x).num_seri_docu ||','||
                    interfazRecord(x).num_comp_docu_inic ||','||
                    interfazRecord(x).num_comp_docu_fina ||','||
                    interfazRecord(x).tip_docu_iden_clie ||','||
                    interfazRecord(x).num_docu_clie ||','||
                    interfazRecord(x).nom_clie ||','||
                    interfazRecord(x).mon_expo ||','||
                    interfazRecord(x).mon_base_impo ||','||
                    interfazRecord(x).val_desc ||','||
                    interfazRecord(x).mon_igv ||','||
                    '0'||','||
                    interfazRecord(x).mon_tota_oper_exon ||','||
                    interfazRecord(x).mon_tota_oper_inaf ||','||                                                          
                    interfazRecord(x).mon_isc ||','||
                    interfazRecord(x).mon_base_grav_ivap ||','||
                    interfazRecord(x).mon_ivap ||','||
                    interfazRecord(x).mon_otro_trib ||','||
                    interfazRecord(x).mon_tota_comp_pago ||','||
                    'PEN' ||','||
                    interfazRecord(x).tip_camb ||','||                    
                    interfazRecord(x).fec_emis_comp_refe ||','||
                    interfazRecord(x).tip_comp_refe ||','||
                    interfazRecord(x).num_seri_comp_refe ||','||
                    interfazRecord(x).num_comp_refe ||','||
                    '' ||','||
                    '' ||','||
                    '1' ||','||
                    interfazRecord(x).IND_ESTA_COMP_PAGO  ;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_SUNAT_CSV: '||ls_sqlerrm);
END RUV_PR_GENER_REPOR_SUNAT_CSV;

/***************************************************************************
Descripcion       : Genera archivo temporal  correspondiente al Reporte Sunat
Fecha Creacion    : 19/08/2010
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_GENER_REPOR_SUNAT_EXCEL(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lsCodigoPais        VARCHAR2(3);
  CURSOR c_interfaz IS
  SELECT
    cod_peri ,
    trim(num_corr) as num_corr,
    trim(fec_emis) as fec_emis,
    trim(fec_venc) as fec_venc ,
    tip_comp,
    trim(num_seri_docu) as num_seri_docu,
    trim(num_comp_docu_inic) as num_comp_docu_inic,
    trim(num_comp_docu_fina) as num_comp_docu_fina,
    trim(tip_docu_iden_clie) as tip_docu_iden_clie,
    trim(num_docu_clie) as num_docu_clie,
    trim(nom_clie) as nom_clie,
    mon_expo,
    mon_base_impo,
    mon_tota_oper_exon,
    mon_tota_oper_inaf,
    mon_isc,
    mon_igv,
    mon_base_grav_ivap,
    mon_ivap,
    mon_otro_trib,
    mon_tota_comp_pago,
    tip_camb,
    trim(fec_emis_comp_refe) as fec_emis_comp_refe,
    decode(trim(tip_comp_refe),'12','03',trim(tip_comp_refe)) as tip_comp_refe,
    trim(num_seri_comp_refe) as num_seri_comp_refe,
    trim(num_comp_refe) as num_comp_refe,
    IND_ESTA_COMP_PAGO
  FROM ruv_histo_repor_sunat
  WHERE
    COD_PERI = pscodigoPeriodoInformar
  ORDER BY cod_peri, tip_comp, fec_emis, num_seri_docu, num_comp_docu_inic;

TYPE interfazTipo IS RECORD (
 cod_peri ruv_histo_repor_sunat.num_corr%TYPE,
 num_corr ruv_histo_repor_sunat.num_corr%TYPE,
 fec_emis ruv_histo_repor_sunat.fec_emis%TYPE,
 fec_venc ruv_histo_repor_sunat.fec_venc%TYPE,
 tip_comp ruv_histo_repor_sunat.tip_comp%TYPE,
 num_seri_docu ruv_histo_repor_sunat.num_seri_docu%TYPE,
 num_comp_docu_inic ruv_histo_repor_sunat.num_comp_docu_inic%TYPE,
 num_comp_docu_fina ruv_histo_repor_sunat.num_comp_docu_fina%TYPE,
 tip_docu_iden_clie ruv_histo_repor_sunat.tip_docu_iden_clie%TYPE,
 num_docu_clie ruv_histo_repor_sunat.num_docu_clie%TYPE,
 nom_clie ruv_histo_repor_sunat.nom_clie%TYPE,
 mon_expo NUMBER,
 mon_base_impo NUMBER,
 mon_tota_oper_exon NUMBER,
 mon_tota_oper_inaf NUMBER,
 mon_isc NUMBER,
 mon_igv NUMBER,
 mon_base_grav_ivap NUMBER,
 mon_ivap NUMBER,
 mon_otro_trib NUMBER,
 mon_tota_comp_pago NUMBER,
 tip_camb NUMBER,
 fec_emis_comp_refe ruv_histo_repor_sunat.fec_emis_comp_refe%TYPE,
 tip_comp_refe ruv_histo_repor_sunat.tip_comp_refe%TYPE,
 num_seri_comp_refe ruv_histo_repor_sunat.num_seri_comp_refe%TYPE,
 num_comp_refe ruv_histo_repor_sunat.num_comp_refe%TYPE,
 IND_ESTA_COMP_PAGO  ruv_histo_repor_sunat.IND_ESTA_COMP_PAGO%TYPE
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lsLineaCabecera  VARCHAR2(4000);
   lbAbrirUtlFile  BOOLEAN;
BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.txt', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).cod_peri ||';'||
                    interfazRecord(x).num_corr ||';'||
                    'A' || interfazRecord(x).num_corr ||';'||
                    interfazRecord(x).fec_emis ||';'||
                    interfazRecord(x).fec_venc ||';'||
                    interfazRecord(x).tip_comp ||';'||
                    interfazRecord(x).num_seri_docu ||';'||
                    interfazRecord(x).num_comp_docu_inic ||';'||
                    interfazRecord(x).num_comp_docu_fina ||';'||
                    interfazRecord(x).tip_docu_iden_clie ||';'||
                    interfazRecord(x).num_docu_clie ||';'||
                    interfazRecord(x).nom_clie ||';'||
                    interfazRecord(x).mon_expo ||';'||
                    interfazRecord(x).mon_base_impo ||';'||
                    interfazRecord(x).mon_tota_oper_exon ||';'||
                    interfazRecord(x).mon_tota_oper_inaf ||';'||
                    interfazRecord(x).mon_isc ||';'||
                    interfazRecord(x).mon_igv ||';'||
                    interfazRecord(x).mon_base_grav_ivap ||';'||
                    interfazRecord(x).mon_ivap ||';'||
                    interfazRecord(x).mon_otro_trib ||';'||
                    interfazRecord(x).mon_tota_comp_pago ||';'||
                    interfazRecord(x).tip_camb ||';'||
                    interfazRecord(x).fec_emis_comp_refe ||';'||
                    interfazRecord(x).tip_comp_refe ||';'||
                    interfazRecord(x).num_seri_comp_refe ||';'||
                    interfazRecord(x).num_comp_refe ||';'||
                    '' ||';'||
                    interfazRecord(x).IND_ESTA_COMP_PAGO  ;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_SUNAT_EXCEL: '||ls_sqlerrm);
END RUV_PR_GENER_REPOR_SUNAT_EXCEL;

/***************************************************************************
Descripcion       : Genera archivo TXT correspondiente al Reporte Sunat
Fecha Creacion    : 19/08/2010
Autor             : Carlos Bazalar
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_GENER_REPOR_SUNAT_TXT(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lsCodigoPais        VARCHAR2(3);
  
  CURSOR c_interfaz IS
  SELECT
    cod_peri ||'|'||
    trim(num_corr)||'|'||
    trim(NUM_CORR_ASIN)||'|'||
    trim(fec_emis)||'|'||
    trim(fec_venc)||'|'||
    tip_comp||'|'||
    trim(num_seri_docu)||'|'||
    trim(num_comp_docu_inic)||'|'||
    trim(num_comp_docu_fina)||'|'||    
    trim(tip_docu_iden_clie)||'|'||
    trim(num_docu_clie)||'|'||    
    trim(nom_clie)||'|'||    
    trim(to_char(mon_expo,'999999990.00'))||'|'||    
    trim(to_char(mon_base_impo,'999999990.00'))||'|'||    
    trim(to_char(val_desc,'999999990.00'))||'|'||  
    trim(to_char(mon_igv,'999999990.00'))||'|'||
    '0'||','||      
    trim(to_char(mon_tota_oper_exon,'999999990.00'))||'|'||
    trim(to_char(mon_tota_oper_inaf,'999999990.00'))||'|'||        
    trim(to_char(mon_isc,'999999990.00'))||'|'||
    trim(to_char(mon_base_grav_ivap,'999999990.00'))||'|'||        
    trim(to_char(mon_ivap,'999999990.00'))||'|'||
    trim(to_char(mon_otro_trib,'999999990.00'))||'|'||    
    trim(to_char(mon_tota_comp_pago,'999999990.00'))||'|'||   
    'PEN' ||','||     
    trim(to_char(tip_camb,'0.000'))||'|'||        
    trim(fec_emis_comp_refe)||'|'||    
    decode(trim(tip_comp_refe),'12','03',trim(tip_comp_refe))||'|'||    
    trim(num_seri_comp_refe)||'|'||
    trim(num_comp_refe)||'|'||
    '' ||','||
    '' ||','||
    '1' ||','||
    IND_ESTA_COMP_PAGO||'|' as campo
  FROM ruv_histo_repor_sunat
  WHERE
   COD_PERI = pscodigoPeriodoInformar
  ORDER BY cod_peri, tip_comp, fec_emis, num_seri_docu, num_comp_docu_inic;
  
TYPE interfazTipo IS RECORD
(
   campo VARCHAR2(4000)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.txt', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).campo ;
          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
  EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;

 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_SUNAT_TXT: '||ls_sqlerrm);
END RUV_PR_GENER_REPOR_SUNAT_TXT;


/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte Sunat Error
Fecha Creacion    : 01/09/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_REPOR_SUNAT_ERROR_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lsCodigoPais        VARCHAR2(3);
  CURSOR c_interfaz IS
    SELECT    a.cod_peri,
              trim(a.num_corr) as num_corr,
              trim(val_obse) as val_obse,
              trim(fec_emis) as fec_emis,
              trim(fec_venc) as fec_venc ,
              tip_comp,
              trim(num_seri_docu) as num_seri_docu,
              trim(num_comp_docu_inic) as num_comp_docu_inic,
              trim(num_comp_docu_fina) as num_comp_docu_fina,
              trim(tip_docu_iden_clie) as tip_docu_iden_clie,
              trim(num_docu_clie) as num_docu_clie,
              trim(nom_clie) as nom_clie,
              mon_expo,
              mon_base_impo,
              mon_tota_oper_exon,
              mon_tota_oper_inaf,
              mon_isc,
              mon_igv,
              mon_base_grav_ivap,
              mon_ivap,
              mon_otro_trib,
              mon_tota_comp_pago,
              tip_camb,
              trim(fec_emis_comp_refe) as fec_emis_comp_refe,
              decode(trim(tip_comp_refe),'12','03',trim(tip_comp_refe)) as tip_comp_refe,
              trim(num_seri_comp_refe) as num_seri_comp_refe,
              trim(num_comp_refe) as num_comp_refe,
              IND_ESTA_COMP_PAGO
    FROM      ruv_histo_repor_sunat a,
              ruv_histo_repor_sunat_error b
    WHERE     a.cod_peri = b.cod_peri
      AND     a.num_corr = b.num_corr
      AND     a.cod_peri = pscodigoPeriodoInformar
    ORDER BY  a.cod_peri, tip_comp, fec_emis, num_seri_docu, num_comp_docu_inic;


TYPE interfazTipo IS RECORD (
 cod_peri ruv_histo_repor_sunat.num_corr%TYPE,
 num_corr ruv_histo_repor_sunat.num_corr%TYPE,
 val_obse ruv_histo_repor_sunat_error.val_obse%TYPE,
 fec_emis ruv_histo_repor_sunat.fec_emis%TYPE,
 fec_venc ruv_histo_repor_sunat.fec_venc%TYPE,
 tip_comp ruv_histo_repor_sunat.tip_comp%TYPE,
 num_seri_docu ruv_histo_repor_sunat.num_seri_docu%TYPE,
 num_comp_docu_inic ruv_histo_repor_sunat.num_comp_docu_inic%TYPE,
 num_comp_docu_fina ruv_histo_repor_sunat.num_comp_docu_fina%TYPE,
 tip_docu_iden_clie ruv_histo_repor_sunat.tip_docu_iden_clie%TYPE,
 num_docu_clie ruv_histo_repor_sunat.num_docu_clie%TYPE,
 nom_clie ruv_histo_repor_sunat.nom_clie%TYPE,
 mon_expo NUMBER,
 mon_base_impo NUMBER,
 mon_tota_oper_exon NUMBER,
 mon_tota_oper_inaf NUMBER,
 mon_isc NUMBER,
 mon_igv NUMBER,
 mon_base_grav_ivap NUMBER,
 mon_ivap NUMBER,
 mon_otro_trib NUMBER,
 mon_tota_comp_pago NUMBER,
 tip_camb NUMBER,
 fec_emis_comp_refe ruv_histo_repor_sunat.fec_emis_comp_refe%TYPE,
 tip_comp_refe ruv_histo_repor_sunat.tip_comp_refe%TYPE,
 num_seri_comp_refe ruv_histo_repor_sunat.num_seri_comp_refe%TYPE,
 num_comp_refe ruv_histo_repor_sunat.num_comp_refe%TYPE,
 IND_ESTA_COMP_PAGO  ruv_histo_repor_sunat.IND_ESTA_COMP_PAGO%TYPE
);

   TYPE interfazTab  IS TABLE OF interfazTipo;
   interfazRecord interfazTab;
   lsLineaCabecera  VARCHAR2(4000);
   lbAbrirUtlFile  BOOLEAN;
BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).cod_peri ||','||
                    interfazRecord(x).num_corr ||','||
                    'A' || interfazRecord(x).num_corr ||','||
                    interfazRecord(x).val_obse ||','||
                    interfazRecord(x).fec_emis ||','||
                    interfazRecord(x).fec_venc ||','||
                    interfazRecord(x).tip_comp ||','||
                    interfazRecord(x).num_seri_docu ||','||
                    interfazRecord(x).num_comp_docu_inic ||','||
                    interfazRecord(x).num_comp_docu_fina ||','||
                    interfazRecord(x).tip_docu_iden_clie ||','||
                    interfazRecord(x).num_docu_clie ||','||
                    interfazRecord(x).nom_clie ||','||
                    interfazRecord(x).mon_expo ||','||
                    interfazRecord(x).mon_base_impo ||','||
                    interfazRecord(x).mon_tota_oper_exon ||','||
                    interfazRecord(x).mon_tota_oper_inaf ||','||
                    interfazRecord(x).mon_isc ||','||
                    interfazRecord(x).mon_igv ||','||
                    interfazRecord(x).mon_base_grav_ivap ||','||
                    interfazRecord(x).mon_ivap ||','||
                    interfazRecord(x).mon_otro_trib ||','||
                    interfazRecord(x).mon_tota_comp_pago ||','||
                    interfazRecord(x).tip_camb ||','||
                    interfazRecord(x).fec_emis_comp_refe ||','||
                    interfazRecord(x).tip_comp_refe ||','||
                    interfazRecord(x).num_seri_comp_refe ||','||
                    interfazRecord(x).num_comp_refe ||','||
                    '' ||','||
                    interfazRecord(x).IND_ESTA_COMP_PAGO  ;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_REPOR_SUNAT_ERROR_CSV: '||ls_sqlerrm);
END RUV_PR_REPOR_SUNAT_ERROR_CSV;

/***************************************************************************
Descripcion       : Genera archivo TXT correspondiente al Reporte Sunat Error
Fecha Creacion    : 01/09/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            pscodigoPeriodoInformar : codigo Periodo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE RUV_PR_REPOR_SUNAT_ERROR_TXT(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    pscodigoPeriodoInformar VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  lsCodigoPais        VARCHAR2(3);
  CURSOR c_interfaz IS
    SELECT    a.cod_peri ||'|'||
              trim(a.num_corr)||'|'||
              'A' || trim(a.num_corr)||'|'||
              trim(val_obse)||'|'||
              trim(fec_emis)||'|'||
              trim(fec_venc)||'|'||
              tip_comp||'|'||
              trim(num_seri_docu)||'|'||
              trim(num_comp_docu_inic)||'|'||
              trim(num_comp_docu_fina)||'|'||
              trim(tip_docu_iden_clie)||'|'||
              trim(num_docu_clie)||'|'||
              trim(nom_clie)||'|'||
              trim(to_char(mon_expo,'999999990.00'))||'|'||
              trim(to_char(mon_base_impo,'999999990.00'))||'|'||
              trim(to_char(mon_tota_oper_exon,'999999990.00'))||'|'||
              trim(to_char(mon_tota_oper_inaf,'999999990.00'))||'|'||
              trim(to_char(mon_isc,'999999990.00'))||'|'||
              trim(to_char(mon_igv,'999999990.00'))||'|'||
              trim(to_char(mon_base_grav_ivap,'999999990.00'))||'|'||
              trim(to_char(mon_ivap,'999999990.00'))||'|'||
              trim(to_char(mon_otro_trib,'999999990.00'))||'|'||
              trim(to_char(mon_tota_comp_pago,'999999990.00'))||'|'||
              trim(to_char(tip_camb,'0.000'))||'|'||
              trim(fec_emis_comp_refe)||'|'||
              decode(trim(tip_comp_refe),'12','03',trim(tip_comp_refe))||'|'||
              trim(num_seri_comp_refe)||'|'||
              trim(num_comp_refe)||'|'||
              ''||'|'||
              IND_ESTA_COMP_PAGO||'|' as campo
    FROM      ruv_histo_repor_sunat a,
              ruv_histo_repor_sunat_error b
    WHERE     a.cod_peri = b.cod_peri
      AND     a.num_corr = b.num_corr
      AND     a.cod_peri = pscodigoPeriodoInformar
    ORDER BY  a.cod_peri, tip_comp, fec_emis, num_seri_docu, num_comp_docu_inic;

TYPE interfazTipo IS RECORD
(
   campo VARCHAR2(4000)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.txt', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).campo ;
          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
  EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;

 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_REPOR_SUNAT_ERROR_TXT: '||ls_sqlerrm);
END RUV_PR_REPOR_SUNAT_ERROR_TXT;

  /******************************************************************************
  Descripcion         : RUV_PR_GENER_DOCUM_LEGDP
                        Proceso que genera la data en tabla Global Tempory de Documentos
                        Legales Duplicados
  Fecha Creacion      : 06/01/2011
  Parametros Entrada:
  psOidTipoDocumento  : Oid Tipo de Documento
  psFechaInicio       : Fecha de inicio
  psFechaFin          : Fecha de fin
  psOidSubacceso      : Oid Seg Sub Acceso
  psNumeroSerie       : Numero de serie
  psDescCanal         : Descripción de Canal
  psDescAcceso        : Descripción de Acceso
  psDescSubAcceso     : Descripción de Sub Acceso
  Autor               : Nicolás López
 *******************************************************************************/
 PROCEDURE RUV_PR_GENER_DOCUM_LEGDP(
   psOidTipoDocumento      VARCHAR2,
   psFechaInicio           VARCHAR2,
   psFechaFin              VARCHAR2,
   psOidSubAcceso          VARCHAR2,
   psNumeroSerie           VARCHAR2,
   psDescCanal             VARCHAR2,
   psDescAcceso            VARCHAR2,
   psDescSubAcceso         VARCHAR2
 )
 IS
  CURSOR c_documentos IS
  SELECT TO_CHAR(b.fec_emis, 'DD/MM/YYYY') as fec_emis,
         psDescCanal as canal,
         psDescAcceso as acceso,
         D.DES_TIPO_DOCU as tipoDocumento,
         psDescSubAcceso as subacceso,
         psNumeroSerie as serie,
         b.NUM_DOCU_CONT_INTE,
         b.val_nume_docu_lega,
         COUNT(1) OVER(partition by b.val_nume_docu_lega) as contador
    FROM FAC_REGIS_UNICO_VENTA B, FAC_TIPO_DOCUM D
   WHERE B.TIDO_OID_TIPO_DOCU = D.OID_TIPO_DOCU
     AND b.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
     AND trunc(b.FEC_EMIS) >= to_date(psFechaInicio, 'dd/MM/yyyy')
     AND trunc(b.FEC_EMIS) <= to_date(psFechaFin, 'dd/MM/yyyy')
     AND b.SBAC_OID_SBAC = psOidSubAcceso
     AND b.val_seri_docu_lega = psNumeroSerie
     AND b.val_nume_docu_lega IS NOT NULL;

   TYPE docum_legal IS RECORD(
     fec_emis       	        VARCHAR2(10),
     canal                    gen_i18n_sicc_comun.val_i18n%TYPE,
     acceso                   gen_i18n_sicc_comun.val_i18n%TYPE,
     tipoDocumento            fac_tipo_docum.des_tipo_docu%TYPE,
     subacceso                gen_i18n_sicc_comun.val_i18n%TYPE,
     serie                    fac_regis_unico_venta.val_seri_docu_lega%TYPE,
     num_docu_cont_inte       fac_regis_unico_venta.num_docu_cont_inte%TYPE,
     val_nume_docu_lega       fac_regis_unico_venta.val_nume_docu_lega%TYPE,
     contador                 NUMBER(10)
   );

   TYPE docum_legalTab  IS TABLE OF docum_legal;
   documentosRecord docum_legalTab;

   BEGIN

        OPEN c_documentos;

        LOOP
            FETCH c_documentos BULK COLLECT INTO documentosRecord LIMIT W_FILAS;

            IF documentosRecord.COUNT > 0 THEN

               FOR x IN documentosRecord.FIRST .. documentosRecord.LAST LOOP

                   IF (documentosRecord(x).contador > 1) THEN

                      INSERT INTO RUV_GTT_DOCU_ANULA(FEC_EMIS                         ,     VAL_DESC_CANA                       ,      VAL_DESC_ACCE             ,
                                                     VAL_DESC_TDOC                    ,     VAL_DESC_SACC                       ,      VAL_SERI_DOCU             ,
                                                     NUM_DOCU_CONT_INTE               ,    VAL_NUME_DOCU_LEGA                   ,      VAL_NUME_CONTA            )
                                         VALUES(documentosRecord(x).fec_emis          ,  documentosRecord(x).canal              ,  documentosRecord(x).acceso    ,
                                                documentosRecord(x).tipoDocumento     ,  documentosRecord(x).subacceso          ,  documentosRecord(x).serie     ,
                                                documentosRecord(x).num_docu_cont_inte,  documentosRecord(x).val_nume_docu_lega ,  documentosRecord(x).contador  );

                   END IF;

               END LOOP;
            END IF;

           EXIT WHEN c_documentos%NOTFOUND;
        END LOOP;

        CLOSE c_documentos;
    EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_DOCUM_LEGDP ' || ls_sqlerrm);

    END RUV_PR_GENER_DOCUM_LEGDP;

  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de Libro de Ventas
                      Mensuales
  Fecha Creacion    : 20/04/2011
  Autor             : Jesse James Rios Franco
  Parametros        :
              psCodigoPais : Codigo Pais
              psNombreArchivo : Nombre del Archivo
              pscodigoPeriodoInformar : codigo Periodo
              psDirectorio: Directorio en donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_LIBRO_CSV(psCodigoPais            VARCHAR2,
                                         psNombreArchivo         VARCHAR2,
                                         psTitulo                VARCHAR2,
                                         psCodigoInterfaz        VARCHAR2,
                                         psFechaInicio         VARCHAR2,
                                         psFechaFin            VARCHAR2,
                                         psDirectorio       OUT  VARCHAR2) IS

    lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS             NUMBER := 5000 ;
    v_handle            UTL_FILE.FILE_TYPE;
    W_DESC              VARCHAR2(200);
    lsLinea             VARCHAR2(4000);
    lsNombreArchivo     VARCHAR2(50);
    lsCodigoPais        VARCHAR2(3);
    lsparametro         BAS_PARAM_PAIS.VAL_PARA%TYPE;

    CURSOR c_interfaz_parametro_activo IS
          SELECT CASE INT_PKG_RUV.RUV_FN_DEVUE_MARC_PAIS(RUV.PAIS_OID_PAIS)
                   WHEN 'ES' THEN          '2'
                   WHEN 'LB' THEN          '1'
                   ELSE          NULL
                   END NEGOCIO,
                  'D' CANAL_VENTA,
                   ROW_NUMBER ( )  OVER (ORDER BY RUV.FEC_EMIS,RUV.VAL_SERI_DOCU_LEGA, RUV.VAL_NUME_DOCU_LEGA ) NUM_TRANSACCION,
                  TO_CHAR(RUV.FEC_EMIS, 'DD/MM/YYYY') FECHA,
                  CASE   WHEN RUV.VAL_SERI_DOCU_LEGA IN ('002','2','004') THEN     'C'
                           WHEN RUV.VAL_SERI_DOCU_LEGA = '001' THEN                ' '
                           WHEN RUV.VAL_SERI_DOCU_LEGA = '003' THEN               ' '
                              ELSE RUV.VAL_SERI_DOCU_LEGA
                  END SERIE,
                 CASE RUV.TIDO_OID_TIPO_DOCU
                          WHEN (SELECT OID_TIPO_DOCU
                                      FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '021') THEN 'N/A'
                          ELSE
                                 LPAD(RUV.VAL_NUME_DOCU_LEGA, 8, '0')
                  END NUM_FACT,
                  TO_CHAR(RUV.NUM_CONT_DOCU_LEGA) NUM_CONTROL,
                   'N/A' NUM_PLANILLA_EXP,
                  CASE WHEN MAE.VAL_IDEN_DOCU_PRIN = 1 THEN
                         'V'||RUV.VAL_NUME_IDEN_NNAL
                         WHEN RUV.VAL_NUME_IDEN_NNAL IS NULL THEN
                            'N/A'
                         ELSE
                            RUV.VAL_NUME_IDEN_NNAL
                  END DOC_IDENTIDAD,
                  NVL(TRIM(SUBSTR((RUV.VAL_APE1 || ' ' || RUV.VAL_APE2 || ' ' ||
                  RUV.VAL_NOM1 || ' ' || RUV.VAL_NOM2),1, 50) ),RUV.VAL_NOMB) RAZON_SOCIAL,
                  CASE WHEN MAE.VAL_IDEN_DOCU_PRIN = 1 THEN
                         'PN'
                         WHEN RUV.VAL_NUME_IDEN_NNAL IS NULL THEN
                            'N/A'
                   ELSE
                        'PJ'
                   END TIPO_PROV,
                   CASE  WHEN RUV.VAL_INDI_RUV = 'A'   AND RUV.IND_ESTA = 1 THEN
                        '03 - ANUL.'
                   ELSE
                        '01 - REG.'
                  END TIPO_TRANSACCION,


                  CASE RUV.TIDO_OID_TIPO_DOCU
                          WHEN (SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '001') THEN 'N/A'
                          ELSE LPAD(RUV.VAL_NUME_DOCU_LEGA, 8, '0')
                  END NOTA_CREDITO,
                  NVL((SELECT TO_CHAR(FACORIG.NUM_DOCU_LEGA) DOCU
                            FROM FAC_REGIS_UNICO_VENTA RUVV,
                                     FAC_DOCUM_CONTA_CABEC F,
                                     PED_SOLIC_CABEC       P,
                                     FAC_DOCUM_CONTA_LINEA FL,
                                     PED_SOLIC_POSIC       PSP,
                                     PED_SOLIC_CABEC       PEDORIG,
                                     FAC_DOCUM_CONTA_CABEC FACORIG,
                                     FAC_DOCUM_CONTA_LINEA FACORIGL,
                                     PED_SOLIC_POSIC       PSPORIG
                          WHERE RUVV.DCCA_OID_CABE = F.OID_CABE
                              AND FL.DCCA_OID_CABE = F.OID_CABE
                              AND FL.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
                              AND P.OID_SOLI_CABE = F.SOCA_OID_SOLI_CABE
                              AND P.SOCA_OID_DOCU_REFE = PEDORIG.OID_SOLI_CABE
                              AND PEDORIG.OID_SOLI_CABE = FACORIG.SOCA_OID_SOLI_CABE
                              AND FACORIGL.DCCA_OID_CABE = FACORIG.OID_CABE
                              AND FACORIGL.SOPO_OID_SOLI_POSI = PSPORIG.OID_SOLI_POSI
                              AND FL.PROD_OID_PROD = FACORIGL.PROD_OID_PROD
                              AND PSP.VAL_CODI_VENT = PSPORIG.VAL_CODI_VENT
                              AND RUVV.OID_REGI = RUV.OID_REGI
                              AND RUVV.TIDO_OID_TIPO_DOCU IN 32
                              AND ROWNUM = 1),'N/A')  PEDIDO_AFECTADO,
               RUV.IMP_TOTA TOTAL_CON_IVA,
              '0' VENTAS_EXO,
               RUV.VAL_BASE_IMPO_NETO TOTAL_SIN_IVA,
               CASE  WHEN RUV.VAL_INDI_RUV = 'A'   AND RUV.IND_ESTA = 1 THEN
                        '0%'
                   ELSE
                        '12 %'
               END PORCENTAJE_IVA,
               RUV.IMP_IMPU MONTO_IVA
          FROM FAC_REGIS_UNICO_VENTA RUV,
                   MAE_CLIEN_IDENT MAE
          WHERE RUV.TIDO_OID_TIPO_DOCU IN ((SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '001'),
                                          (SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '021'))
            AND RUV.FEC_EMIS >= TO_DATE(psFechaInicio , 'DD/MM/YYYY')
            AND RUV.FEC_EMIS <= TO_DATE(psFechaFin, 'DD/MM/YYYY')
             AND MAE.CLIE_OID_CLIE (+) = RUV.CLIE_OID_CLIE
          ORDER BY NUM_TRANSACCION, RUV.FEC_EMIS, RUV.VAL_SERI_DOCU_LEGA, TIPO_TRANSACCION, RUV.VAL_NUME_DOCU_LEGA;

      CURSOR c_interfaz IS
          SELECT CASE INT_PKG_RUV.RUV_FN_DEVUE_MARC_PAIS(RUV.PAIS_OID_PAIS)
                 WHEN 'ES' THEN          '2'
                 WHEN 'LB' THEN          '1'
                 ELSE          NULL
                 END NEGOCIO,
             'D' CANAL_VENTA,
             TO_CHAR(RUV.FEC_EMIS, 'DD/MM/YYYY') FECHA,
             RUV.VAL_NUME_IDEN_NNAL DOC_IDENTIDAD,
             SUBSTR((RUV.VAL_APE1 || ' ' || RUV.VAL_APE2 || ' ' ||
                     RUV.VAL_NOM1 || ' ' || RUV.VAL_NOM2),1, 50) RAZON_SOCIAL,
             CASE RUV.TIDO_OID_TIPO_DOCU
               WHEN (SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '021') THEN NULL
               ELSE LPAD(RUV.VAL_NUME_DOCU_LEGA, 8, '0')
             END NUM_FACT,
             ruv.num_cont_docu_lega NUM_CONTROL,
             ruv.num_cont_docu_lega NUM_CONTROL2,
             CASE RUV.TIDO_OID_TIPO_DOCU
               WHEN (SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '001') THEN NULL
               ELSE LPAD(RUV.VAL_NUME_DOCU_LEGA, 8, '0')
             END NOTA_CREDITO,
             LPAD(NVL(RUV.VAL_NUME_DOCU_REFE,INT_PKG_RUV.RUV_FN_DEVUE_NUME_DOCU(RUV.OID_REGI)),8,'0') FACT_AFECTADO,

             CASE RUV.TIDO_OID_TIPO_DOCU
               WHEN (SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '001') THEN 'FACTURA'/*'1'*/
               ELSE 'NOTA DE CREDITO'/*'3'*/
             END TIPO_TRANSACCION,
             RUV.IMP_TOTA TOTAL_CON_IVA,
             RUV.VAL_BASE_IMPO_NETO TOTAL_SIN_IVA,
             (SELECT VAL_TASA_IMPU FROM PED_TASA_IMPUE
               WHERE PAIS_OID_PAIS = (SELECT OID_PAIS FROM SEG_PAIS WHERE COD_PAIS = psCodigoPais)
                AND VAL_INDI_IMPU = (select b.val_pain
                                       from bas_param_inter b
                                      where b.inte_cod_inte = psCodigoInterfaz
                                        and b.nom_pain = 'nombreImpuesto')) PORCENTAJE_IVA,
             RUV.IMP_IMPU MONTO_IVA
             ,
            DECODE((CASE RUV.TIDO_OID_TIPO_DOCU
                     WHEN (SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '001') THEN
                          1/*'1'*/
                     ELSE
                          3/*'3'*/
                     END ),1,(CASE RUV.TIDO_OID_TIPO_DOCU
                                      WHEN (SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '021') THEN
                                           NULL
                                      ELSE
                                           LPAD(RUV.VAL_NUME_DOCU_LEGA, 8, '0')
                                      END), 3,(CASE RUV.TIDO_OID_TIPO_DOCU
                                                               WHEN (SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '001') THEN
                                                                    NULL
                                                               ELSE
                                                                    LPAD(RUV.VAL_NUME_DOCU_LEGA, 8, '0')
                                                               END)
                   ) as NUM_DOCU_LEGA,
                CLIE_OID_CLIE
        FROM fac_regis_unico_venta ruv
        WHERE ruv.tido_oid_tipo_docu IN ((SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '001'),
                                        (SELECT OID_TIPO_DOCU FROM FAC_TIPO_DOCUM WHERE COD_TIPO_DOCU = '021'))
        AND ruv.fec_emis >= to_date(psFechaInicio, 'DD/MM/YYYY')
        AND ruv.fec_emis <= to_date(psFechaFin, 'DD/MM/YYYY')
        --AND (ruv.imp_tota <> 0 OR ruv.val_base_impo_neto <> 0 OR ruv.imp_impu <> 0)
  order by Fecha,NUM_CONTROL,NUM_DOCU_LEGA;




  TYPE interfazTipo IS RECORD(
       NEGOCIO            VARCHAR2(1),
       CANAL_VENTA        VARCHAR2(1),
       FECHA              VARCHAR2(10),
       DOC_IDENTIDAD      FAC_REGIS_UNICO_VENTA.VAL_NUME_IDEN_NNAL%TYPE,
       RAZON_SOCIAL       VARCHAR2(200),
       NUM_FACT           VARCHAR2(8),
       NUM_CONTROL        FAC_REGIS_UNICO_VENTA.NUM_CONT_DOCU_LEGA%TYPE,
       NUM_CONTROL2       FAC_REGIS_UNICO_VENTA.NUM_CONT_DOCU_LEGA%TYPE,
       NOTA_CREDITO       VARCHAR2(8),
       FACT_AFECTADO      VARCHAR2(8),
       TIPO_TRANSACCION   VARCHAR2(20),
       TOTAL_CON_IVA      FAC_REGIS_UNICO_VENTA.IMP_TOTA%TYPE,
       TOTAL_SIN_IVA      FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO_NETO%TYPE,
       PORCENTAJE_IVA     PED_TASA_IMPUE.VAL_TASA_IMPU%TYPE,
       MONTO_IVA          FAC_REGIS_UNICO_VENTA.IMP_IMPU%TYPE,
       NUM_DOCU_LEGA      FAC_REGIS_UNICO_VENTA.VAL_NUME_DOCU_LEGA%TYPE,
       OID_CLIE           MAE_CLIEN.OID_CLIE%TYPE
  );

  TYPE interfazTab IS TABLE OF interfazTipo;
  interfazRecord interfazTab;

  TYPE interfazTipoParametroActivo IS RECORD(
       NEGOCIO            VARCHAR2(1),
       CANAL_VENTA        VARCHAR2(1),
       NUM_TRANSACCION    VARCHAR2(8),
       FECHA              VARCHAR2(10),
       SERIE              VARCHAR2(5),
       NUM_FACT           VARCHAR2(8),
       NUM_CONTROL        VARCHAR2(8),
       NUM_PLANILLA_EXP   VARCHAR2(3),
       DOC_IDENTIDAD      FAC_REGIS_UNICO_VENTA.VAL_NUME_IDEN_NNAL%TYPE,
       RAZON_SOCIAL       VARCHAR2(200),
       TIPO_PROV          VARCHAR2(3),
       TIPO_TRANSACCION   VARCHAR2(20),
       NOTA_CREDITO       VARCHAR2(8),
       PEDIDO_AFECTADO    VARCHAR2(20),
       TOTAL_CON_IVA      FAC_REGIS_UNICO_VENTA.IMP_TOTA%TYPE,
       VENTAS_EXO         VARCHAR2(4),
       TOTAL_SIN_IVA      FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO_NETO%TYPE,
       PORCENTAJE_IVA     VARCHAR2(4),
       MONTO_IVA          FAC_REGIS_UNICO_VENTA.IMP_IMPU%TYPE
  );

  TYPE interfazParametroActivoTab IS TABLE OF interfazTipoParametroActivo;
  interfazParametroActivoRecord interfazParametroActivoTab;


  lbAbrirUtlFile BOOLEAN;

  codCliente MAE_CLIEN.COD_CLIE%TYPE;

  BEGIN
     --- Nueva variable para obtener parametro de activacion
     SELECT MIN(val_para)
      INTO lsparametro
      FROM bas_param_pais
     WHERE cod_para = '010'
       AND cod_sist = 'RUV'
       AND cod_pais = psCodigoPais
       AND ind_acti = '1';

     lbAbrirUtlFile := TRUE;
     EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

     IF lsparametro = 1 THEN
       OPEN c_interfaz_parametro_activo;
         LOOP

             FETCH c_interfaz_parametro_activo BULK COLLECT INTO interfazParametroActivoRecord LIMIT W_FILAS;

             IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
                psDirectorio := lsDirTempo;
                lbAbrirUtlFile := FALSE;
             END IF;

             IF interfazParametroActivoRecord.COUNT > 0 THEN
               FOR x IN interfazParametroActivoRecord.FIRST .. interfazParametroActivoRecord.LAST LOOP

                    lsLinea :=
                               interfazParametroActivoRecord(x).NUM_TRANSACCION    ||','||
                               interfazParametroActivoRecord(x).FECHA              ||','||
                               interfazParametroActivoRecord(x).SERIE              ||','||
                               interfazParametroActivoRecord(x).NUM_FACT           ||','||
                               interfazParametroActivoRecord(x).NUM_CONTROL        ||','||
                               interfazParametroActivoRecord(x).NUM_PLANILLA_EXP   ||','||
                      '=T("'|| interfazParametroActivoRecord(x).DOC_IDENTIDAD||'")'||','||
                               interfazParametroActivoRecord(x).RAZON_SOCIAL       ||','||
                               interfazParametroActivoRecord(x).TIPO_PROV          ||','||
                               interfazParametroActivoRecord(x).TIPO_TRANSACCION   ||','||
                               interfazParametroActivoRecord(x).NOTA_CREDITO       ||','||
                               interfazParametroActivoRecord(x).PEDIDO_AFECTADO    ||','||
                               interfazParametroActivoRecord(x).TOTAL_CON_IVA      ||','||
                               interfazParametroActivoRecord(x).VENTAS_EXO         ||','||
                               interfazParametroActivoRecord(x).TOTAL_SIN_IVA      ||','||
                               interfazParametroActivoRecord(x).PORCENTAJE_IVA     ||','||
                               interfazParametroActivoRecord(x).MONTO_IVA;

                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
               END LOOP;
             END IF;
         EXIT WHEN c_interfaz_parametro_activo%NOTFOUND;
         END LOOP;
         CLOSE c_interfaz_parametro_activo;
     ELSE
       OPEN c_interfaz;
         LOOP

             FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
             IF lbAbrirUtlFile THEN
                GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
                psDirectorio := lsDirTempo;
                lbAbrirUtlFile := FALSE;
             END IF;

             IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                   begin
                    select COD_CLIE into codCliente
                    from mae_clien
                    where oid_clie= interfazRecord(x).OID_CLIE;
                   exception
                    when others then
                      codCliente:='';
                   end;

                    lsLinea :=
                               codCliente                     ||','||
                               interfazRecord(x).FECHA                     ||','||
                               '=T("'||interfazRecord(x).NUM_DOCU_LEGA||'")' ||','||
                               interfazRecord(x).NUM_CONTROL               ||','||
                               interfazRecord(x).TIPO_TRANSACCION          ||','||
                               '=T("'||interfazRecord(x).FACT_AFECTADO||'")' ||','||
                               interfazRecord(x).RAZON_SOCIAL              ||','||
                               '=T("'||interfazRecord(x).DOC_IDENTIDAD||'")' ||','||
                               ' '                                         ||','||
                               0                                           ||','||
                               0                                           ||','||
                               0                                           ||','||
                               0                                           ||','||
                               0                                           ||','||
                               0                                           ||','||
                               interfazRecord(x).TOTAL_SIN_IVA             ||','||
                               interfazRecord(x).PORCENTAJE_IVA            ||','||
                               interfazRecord(x).MONTO_IVA                 ||','||
                               interfazRecord(x).TOTAL_CON_IVA             ||','||
                               0;
                    UTL_FILE.PUT_LINE (V_HANDLE, lslinea);
               END LOOP;
             END IF;
         EXIT WHEN c_interfaz%NOTFOUND;
         END LOOP;
         CLOSE c_interfaz;
     END IF;



     IF NOT lbAbrirUtlFile THEN
        UTL_FILE.fclose(V_HANDLE);
     END IF;

  EXCEPTION
  WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm, 1, 1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_LIBRO_CSV' || ls_sqlerrm);
  END RUV_PR_GENER_REPOR_LIBRO_CSV;

  /******************************************************************************
  Descripcion         : Proceso que genera la data en tabla Global Tempory para Numero
                        de Control de Documentos Legales
  Fecha Creacion      : 10/06/2011
  Autor               : Carlos Diaz Valverde
  *******************************************************************************/
  PROCEDURE RUV_PR_GENER_NUMER_CONDL(
    psOidTipoDocumento      VARCHAR2,
    psFechaInicio           VARCHAR2,
    psFechaFin              VARCHAR2,
    psOidSubAcceso          VARCHAR2,
    psNumeroSerie           VARCHAR2,
    psDescCanal             VARCHAR2,
    psDescAcceso            VARCHAR2,
    psDescSubAcceso         VARCHAR2
  ) IS

    -- CURSORES ------------
    CURSOR C_NUME_CONT IS
      SELECT    TO_CHAR(b.fec_emis, 'DD/MM/YYYY') as fec_emis,
                psDescCanal as canal,
                psDescAcceso as acceso,
                D.DES_TIPO_DOCU as tipoDocumento,
                psDescSubAcceso as subacceso,
                psNumeroSerie as serie,
                b.NUM_DOCU_CONT_INTE,
                b.val_nume_docu_lega,
                COUNT(1) OVER(partition by b.num_cont_docu_lega) as contador,
                b.num_cont_docu_lega
      FROM      FAC_REGIS_UNICO_VENTA B,
                FAC_TIPO_DOCUM D
      WHERE     B.TIDO_OID_TIPO_DOCU = D.OID_TIPO_DOCU
        AND     b.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
        AND     trunc(b.FEC_EMIS) >= to_date(psFechaInicio, 'dd/MM/yyyy')
        AND     trunc(b.FEC_EMIS) <= to_date(psFechaFin, 'dd/MM/yyyy')
        AND     b.SBAC_OID_SBAC = psOidSubAcceso
        AND     b.val_seri_docu_lega = psNumeroSerie
        AND     b.val_nume_docu_lega IS NOT NULL;
    TYPE numeroControlRecord IS RECORD(
      fec_emis       	      VARCHAR2(10),
      canal                 gen_i18n_sicc_comun.val_i18n%TYPE,
      acceso                gen_i18n_sicc_comun.val_i18n%TYPE,
      tipoDocumento         fac_tipo_docum.des_tipo_docu%TYPE,
      subacceso             gen_i18n_sicc_comun.val_i18n%TYPE,
      serie                 fac_regis_unico_venta.val_seri_docu_lega%TYPE,
      num_docu_cont_inte    fac_regis_unico_venta.num_docu_cont_inte%TYPE,
      val_nume_docu_lega    fac_regis_unico_venta.val_nume_docu_lega%TYPE,
      contador              NUMBER(10),
      num_cont_docu_lega    fac_regis_unico_venta.num_cont_docu_lega%TYPE
    );
    TYPE numeroControlTab IS TABLE OF numeroControlRecord;
    numeroControlList numeroControlTab;

  BEGIN

    -- Cursos de NIVEL CAMPAÑA
    OPEN C_NUME_CONT;
    LOOP

      FETCH C_NUME_CONT BULK COLLECT INTO numeroControlList LIMIT W_FILAS;
      IF numeroControlList.COUNT > 0 THEN

        -- Recorrer el Cursor Paginado de NUMERO CONTROL LISTA
        FOR i IN numeroControlList.FIRST .. numeroControlList.LAST LOOP

          -- Validar si el contador de duplicados es mayor a 0
          IF numeroControlList(i).contador > 0 THEN

            INSERT INTO RUV_GTT_DOCU_ANULA(
              FEC_EMIS,
              VAL_DESC_CANA,
              VAL_DESC_ACCE,
              VAL_DESC_TDOC,
              VAL_DESC_SACC,
              VAL_SERI_DOCU,
              NUM_DOCU_CONT_INTE,
              VAL_NUME_DOCU_LEGA,
              VAL_NUME_CONTA,
              NUM_CONT_DOCU_LEGA
            ) VALUES(
              numeroControlList(i).fec_emis,
              numeroControlList(i).canal,
              numeroControlList(i).acceso,
              numeroControlList(i).tipoDocumento,
              numeroControlList(i).subacceso,
              numeroControlList(i).serie,
              numeroControlList(i).num_docu_cont_inte,
              numeroControlList(i).val_nume_docu_lega,
              numeroControlList(i).contador,
              numeroControlList(i).num_cont_docu_lega
            );

          END IF;

        END LOOP; -- Fin del Cursor Paginado de NUMERO CONTROL LISTA

      END IF; -- numeroControlList.COUNT > 0

      EXIT WHEN C_NUME_CONT%NOTFOUND;
    END LOOP;
    CLOSE C_NUME_CONT;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_NUMER_CONDL : ' || ls_sqlerrm);
  END RUV_PR_GENER_NUMER_CONDL;

  /**************************************************************************
   Descripcion         : Proceso de Asignar Nulos opcion por dias por pais
   Fecha Creacion      : 23/06/2011
   Autor               : Carlos Diaz Valverde
  ***************************************************************************/
  PROCEDURE RUV_PR_ASIGN_NULOS_XDIAS_XPAIS(
    psCodigoPais       varchar2,
    psOidTipoDocumento varchar2,
    psNumeroSerie      varchar2,
    psCodigoSubacceso  varchar2,
    psFechaInicio      varchar2,
    psFechaFin         varchar2
  ) IS

    -- Cursores ---------------------------------
    CURSOR C_FIN IS
      select    (b.NUM_CONT_DOCU_LEGA - 1)
      FROM      RUV_GTT_NUMCO_NULOS b
      where     b.ind_proc = '1'
      order by  b.NUM_CONT_DOCU_LEGA;

    CURSOR C_INICIO IS
      select    (b.NUM_CONT_DOCU_LEGA + 1)
      FROM      RUV_GTT_NUMCO_NULOS b
      where     b.ind_proc = '2'
      order by  b.NUM_CONT_DOCU_LEGA;

    CURSOR C_LISTA_FINAL(
      varOidPais       SEG_PAIS.Oid_Pais%type,
      varOidSubacceso  SEG_SUBAC.Oid_Sbac%type
    ) IS
      select    a.oid_regi,
                a.val_ejer_docu_inte,
                a.fec_emis,
                a.val_nume_docu_lega,
                a.num_cont_docu_lega
      from      FAC_REGIS_UNICO_VENTA a
      where     a.pais_oid_pais = varOidPais
        and     a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
        and     a.val_seri_docu_lega = psNumeroSerie
        and     trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
        and     trunc(a.FEC_EMIS) <= to_date(psFechaFin, 'dd/mm/yyyy')
        and     a.SBAC_OID_SBAC = varOidSubacceso
        and     a.NUM_CONT_DOCU_LEGA is null
      order by  a.val_nume_docu_lega;
    TYPE listaFinalRecord is record (
      oidRegi RUV_GTT_REGIS_NULOS.OID_REGI%TYPE,
      valEjer RUV_GTT_REGIS_NULOS.VAL_EJER_DOCU_INTE%TYPE,
      fecEmis RUV_GTT_REGIS_NULOS.FEC_EMIS%TYPE,
      valNume RUV_GTT_REGIS_NULOS.VAL_NUME_DOCU_LEGA%TYPE,
      numCont RUV_GTT_REGIS_NULOS.NUM_CONT_DOCU_LEGA%TYPE
    );
    TYPE listaFinalTab IS TABLE OF listaFinalRecord;
    listaFinal listaFinalTab;

    TYPE t_num_cont_docu is table of RUV_GTT_NUMCO_NULOS.NUM_CONT_DOCU_LEGA%type;
    v_num_cont_docu t_num_cont_docu;

    type t_inicio is table of number(10) index by pls_integer;
    lista_inicio   t_inicio;

    type t_fin is table of number(10) index by pls_integer;
    lista_fin t_fin;

    type t_corre is table of number(10) index by pls_integer;
    lista_corre t_corre;

    -- Variables --------------------------------
    ls_oid_pais       SEG_PAIS.Oid_Pais%type;
    ls_oid_subacceso  SEG_SUBAC.Oid_Sbac%type;
    rows              NATURAL := 1000;
    aux_ini           BINARY_INTEGER := 0;
    aux_fin           BINARY_INTEGER := 0;
    contador          NUMBER(10) := 0;

  BEGIN

    -- Obtener oid pais
    select    OID_PAIS
      into    ls_oid_pais
    from      SEG_PAIS
    where     cod_pais = psCodigoPais;

    -- Obtener oid subacceso
    SELECT    s.oid_sbac
      into    ls_oid_subacceso
    FROM      SEG_SUBAC S
    where     S.COD_SBAC = psCodigoSubacceso;

    -- Insertar y eliminar los siguientes consecutivos -------------------------
    INSERT INTO RUV_GTT_NUMCO_NULOS
    (
      select    '1',
                a.NUM_DOCU_CONT_INTE,
                a.OID_REGI,
                a.NUM_CONT_DOCU_LEGA,
                (a.NUM_CONT_DOCU_LEGA + 1)
      from      FAC_REGIS_UNICO_VENTA a
      where     a.pais_oid_pais = ls_oid_pais
        and     a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
        and     a.val_seri_docu_lega = psNumeroSerie
        and     trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
        and     trunc(a.FEC_EMIS) <= to_date(psFechaFin, 'dd/mm/yyyy')
        and     a.SBAC_OID_SBAC = ls_oid_subacceso
        and     a.NUM_CONT_DOCU_LEGA is null
    );

    DELETE
    FROM      RUV_GTT_NUMCO_NULOS b
    where     b.ind_proc = '1'
      and     b.NUM_CONT_DOCU_LEGA in (
                select    c.NUM_CONT_DOCU_LEGA_SGTE
                from      RUV_GTT_NUMCO_NULOS c
                where     c.ind_proc = '1'
              );

    -- Insertar y eliminar los anteriores consecutivos ------------------------
    INSERT INTO RUV_GTT_NUMCO_NULOS
    (
      select    '2',
                a.NUM_DOCU_CONT_INTE,
                a.oid_regi,
                a.NUM_CONT_DOCU_LEGA,
                (a.NUM_CONT_DOCU_LEGA - 1)
      from      FAC_REGIS_UNICO_VENTA a
      where     a.pais_oid_pais = ls_oid_pais
        and     a.tido_oid_tipo_docu = psOidTipoDocumento
        and     a.val_seri_docu_lega = psNumeroSerie
        and     trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
        and     trunc(a.FEC_EMIS) <= to_date(psFechaFin, 'dd/mm/yyyy')
        and     a.SBAC_OID_SBAC = ls_oid_subacceso
    );

    DELETE
    FROM      RUV_GTT_NUMCO_NULOS b
    where     b.ind_proc = '2'
      and     b.NUM_CONT_DOCU_LEGA in(
                select    c.NUM_CONT_DOCU_LEGA_SGTE
                from      RUV_GTT_NUMCO_NULOS c
                where     c.ind_proc = '2'
              );

    -- Cargar Arreglos --------------------------------------------------------
    OPEN C_FIN;
    LOOP

      FETCH C_FIN BULK COLLECT INTO v_num_cont_docu LIMIT rows;
      IF v_num_cont_docu.COUNT > 0 THEN

        -- Recorrer el Cursor Paginado de C_FIN
        FOR i IN v_num_cont_docu.FIRST .. v_num_cont_docu.LAST LOOP
          lista_fin(i) := v_num_cont_docu(i);
        END LOOP; -- Fin del Cursor Paginado de C_FIN

      END IF; -- t_num_cont_docu.COUNT

      EXIT WHEN C_FIN%NOTFOUND;
    END LOOP;
    CLOSE C_FIN;

    OPEN C_INICIO;
    LOOP

      FETCH C_INICIO BULK COLLECT INTO v_num_cont_docu LIMIT rows;
      IF v_num_cont_docu.COUNT > 0 THEN

        -- Recorrer el Cursor Paginado de C_INICIO
        FOR j IN v_num_cont_docu.FIRST .. v_num_cont_docu.LAST LOOP
          lista_inicio(j) := v_num_cont_docu(j);
        END LOOP; -- Fin del Cursor Paginado de C_INICIO

      END IF; -- v_num_cont_docu.COUNT > 0

      EXIT WHEN C_INICIO%NOTFOUND;
    END LOOP;
    CLOSE C_INICIO;

    -- Ejecutar el proceso de Asignar nulos -----------------------------------
    IF lista_fin.COUNT > 0 THEN

      FOR i IN lista_fin.FIRST .. (lista_fin.LAST-1) LOOP

        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);

        FOR j IN aux_ini .. aux_fin LOOP
          contador := contador + 1;
          lista_corre(contador) := j;
        END LOOP;

      END LOOP;

    END IF;

    -- Asignar nulos a tabla temporal -----------------------------------------
    OPEN C_LISTA_FINAL(
      ls_oid_pais,
      ls_oid_subacceso
    );
    LOOP
      FETCH C_LISTA_FINAL BULK COLLECT INTO listaFinal LIMIT W_FILAS;
      IF listaFinal.COUNT > 0 AND lista_corre.COUNT > 0 THEN

        -- Recorrer el Cursor Paginado de C_LISTA_FINAL
        FOR i IN listaFinal.FIRST .. listaFinal.LAST LOOP

          INSERT INTO RUV_GTT_REGIS_NULOS (
            OID_REGI,
            VAL_EJER_DOCU_INTE,
            FEC_EMIS,
            VAL_NUME_DOCU_LEGA,
            NUM_CONT_DOCU_LEGA
          ) VALUES (
            listaFinal(i).oidRegi,
            listaFinal(i).valEjer,
            listaFinal(i).fecEmis,
            listaFinal(i).valNume,
            lista_corre(i)
          );

        END LOOP; -- Fin del Cursor Paginado de C_LISTA_FINAL


      END IF; -- listaFinal.COUNT > 0

      EXIT WHEN C_LISTA_FINAL%NOTFOUND;
    END LOOP;
    CLOSE C_LISTA_FINAL;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_ASIGN_NULOS_XDIAS_XPAIS : ' || ls_sqlerrm);
  END RUV_PR_ASIGN_NULOS_XDIAS_XPAIS;

/**************************************************************************
    Descripcion         : RUV_PR_CANTI_ASIGN_NULOS_XDIVE
                          Procedimiento que devuelve el numero de rangos a Asignar Nulos opcion por dias para Venezuela
    Fecha Creacion      : 26/03/2014
    Parametros Entrada:
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
        psNumeroAsigna OUT  : Devuelve el resultado
    Autor               : Aurelio Oviedo
  ***************************************************************************/
 PROCEDURE RUV_PR_CANTI_ASIGN_NULOS_XDIVE(psOidTipoDocumento varchar2,
                                          psNumeroSerie       varchar2,
                                          psCodigoSubacceso   varchar2,
                                          psFechaInicio       varchar2,
                                          psFechaFin          varchar2,
                                          psNumeroAsigna  OUT VARCHAR2)is
  ls_oid_subacceso number;
  ls_cont          number :=0;
  ls_cont_aux      number :=0;
  rows             NATURAL := 1000;   -- Number of rows to process at a time
  i                BINARY_INTEGER := 0;
  aux_ini          BINARY_INTEGER := 0;
  aux_fin          BINARY_INTEGER := 0;
  v_row_count      NUMBER := 0;
  v_row_count2     NUMBER := 0;

  TYPE t_nume_docu_lega  is table of GTT_RUV_ASIGN_NULOS.VAL_NUME_DOCU_LEGA%type;
  v_nume_docu_lega t_nume_docu_lega;

  type t_inicio is table of number(10) index by pls_integer;
  type t_fin    is table of number(10) index by pls_integer;
  lista_inicio   t_inicio;
  lista_fin      t_fin;

  cursor c_fin is
      select (b.val_nume_docu_lega - 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '1'
       order by b.val_nume_docu_lega;

  cursor c_inicio is
      select (b.val_nume_docu_lega + 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '2'
       order by b.val_nume_docu_lega;

  BEGIN
    -- obtiene el oid de subacceso
    SELECT s.oid_sbac into ls_oid_subacceso FROM SEG_SUBAC S where S.COD_SBAC = psCodigoSubacceso;

    insert into GTT_RUV_ASIGN_NULOS
      (select '1',
              a.NUM_DOCU_CONT_INTE,
              a.OID_REGI,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA + 1
         from FAC_REGIS_UNICO_VENTA a
        where a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and trunc(a.FEC_EMIS) <= to_date(psFechaFin   , 'dd/mm/yyyy')
          and a.SBAC_OID_SBAC = ls_oid_subacceso
          and a.VAL_NUME_DOCU_LEGA is not null);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '1'
       and b.VAL_NUME_DOCU_LEGA in (select c.VAL_NUME_DOCU_LEGA_SGTE
                                      from GTT_RUV_ASIGN_NULOS c
                                     where c.ind_proc = '1');

    insert into GTT_RUV_ASIGN_NULOS
      (select '2',
              a.NUM_DOCU_CONT_INTE,
              a.oid_regi,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA - 1 as ante
         from FAC_REGIS_UNICO_VENTA a
        where a.tido_oid_tipo_docu = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and trunc(a.FEC_EMIS) <= to_date(psFechaFin   , 'dd/mm/yyyy')
          and a.SBAC_OID_SBAC = ls_oid_subacceso);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '2'
       and b.VAL_NUME_DOCU_LEGA in
           (select c.VAL_NUME_DOCU_LEGA_SGTE
              from GTT_RUV_ASIGN_NULOS c
             where c.ind_proc = '2');

    -- Carga los arreglos
    OPEN c_fin;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_fin BULK COLLECT INTO v_nume_docu_lega
                              LIMIT rows;

      EXIT WHEN v_row_count = c_fin%ROWCOUNT;
      v_row_count := c_fin%ROWCOUNT;

      -- La posicion del arreglo empieza en 1
      for i in 1..v_nume_docu_lega.count loop
          lista_fin(i) := (v_nume_docu_lega(i));
          --dbms_output.put_line(i||'-> '||(v_nume_docu_lega(i)));
      end loop;

    END LOOP;
    CLOSE c_fin;

    OPEN c_inicio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_inicio BULK COLLECT INTO v_nume_docu_lega
                                  LIMIT rows;

      EXIT WHEN v_row_count2 = c_inicio%ROWCOUNT;
      v_row_count2 := c_inicio%ROWCOUNT;

      -- La posicion del arreglo empieza en 1
      for j in 1..v_nume_docu_lega.count loop
          lista_inicio(j) := (v_nume_docu_lega(j));
          --dbms_output.put_line(j||'-> '||(v_nume_docu_lega(j)));
      end loop;

    END LOOP;
    CLOSE c_inicio;

    -- Ejecuta el proceso de Asignar nulos
    for i in 1..(lista_fin.count-1) loop
        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);
        ls_cont_aux := (aux_fin - aux_ini) + 1;
        ls_cont := ls_cont + ls_cont_aux;
    end loop;
    IF ls_cont >= 0 THEN
        psNumeroAsigna := ls_cont;
    ELSE
        psNumeroAsigna := 'O';
    END IF;

 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_CANTI_ASIGN_NULOS_XDIVE ' || ls_sqlerrm);
  end RUV_PR_CANTI_ASIGN_NULOS_XDIVE;

  /**************************************************************************
    Descripcion         : RUV_PR_PROCE_ASINU_XDIAS_VENEZ
                          Proceso de Asignar Nulos opcion por dias Venezuela
    Fecha Creacion      : 04/03/2014
    Parametros Entrada:
        psCodigoPais        : psCodigoPais
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
    Autor               : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE RUV_PR_PROCE_ASINU_XDIAS_VENEZ(psCodigoPais       varchar2,
                                           psOidTipoDocumento varchar2,
                                           psNumeroSerie      varchar2,
                                           psCodigoSubacceso  varchar2,
                                           psFechaInicio      varchar2,
                                           psFechaFin         varchar2) as

  TYPE t_nume_docu_lega  is table of GTT_RUV_ASIGN_NULOS.VAL_NUME_DOCU_LEGA%type;
  v_nume_docu_lega t_nume_docu_lega;

  type t_inicio is table of number(10) index by pls_integer;
  type t_fin    is table of number(10) index by pls_integer;
  lista_inicio   t_inicio;
  lista_fin      t_fin;

  cursor c_fin is
      select (b.val_nume_docu_lega - 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '1'
       order by b.val_nume_docu_lega;

  cursor c_inicio is
      select (b.val_nume_docu_lega + 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '2'
       order by b.val_nume_docu_lega;

  ls_oid_subacceso    SEG_SUBAC.Oid_Sbac%type;
  ls_oid_pais         SEG_PAIS.Oid_Pais%type;
  ls_oid_sociedad     seg_socie.oid_soci%type;
  ls_fecha            date;
  ls_oid_tasa_impu    ped_tasa_impue.oid_tasa_impu%type;

  rows NATURAL        := 1000;   -- Number of rows to process at a time
  i    BINARY_INTEGER := 0;
  j    BINARY_INTEGER := 0;
  aux_ini    BINARY_INTEGER := 0;
  aux_fin    BINARY_INTEGER := 0;
  v_row_count      NUMBER := 0;
  v_row_count2      NUMBER := 0;

  BEGIN
    -- obtiene el oid del pais
    select OID_PAIS into ls_oid_pais from SEG_PAIS where cod_pais = psCodigoPais;
    -- obtiene el oid de sociedad
    select ss.oid_soci
      into ls_oid_sociedad
      from seg_socie ss
     where ss.cod_soci in (select VAL_PARA
                             from bas_param_pais
                            where cod_pais = psCodigoPais
                              and cod_sist = 'RUV'
                              and nom_para = 'codigoSociedad');

    -- obtiene el oid de subacceso
    SELECT s.oid_sbac into ls_oid_subacceso FROM SEG_SUBAC S where S.COD_SBAC = psCodigoSubacceso;
    -- obtiene la fecha
    ls_fecha := to_date(psFechaInicio,'dd/mm/yyyy') + trunc((to_date(psFechaFin,'dd/mm/yyyy')-to_date(psFechaInicio,'dd/mm/yyyy'))/2);
    -- Obtiene el oid de tasa de impuesto
    select oid_tasa_impu
    into ls_oid_tasa_impu
    from ped_tasa_impue
    where val_indi_impu = (select VAL_PARA from bas_param_pais where cod_pais = psCodigoPais and cod_sist = 'RUV' and NOM_PARA = 'impuestoVentas');

    insert into GTT_RUV_ASIGN_NULOS
      (select '1',
              a.NUM_DOCU_CONT_INTE,
              a.OID_REGI,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA + 1
         from FAC_REGIS_UNICO_VENTA a
        where a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and trunc(a.FEC_EMIS) <= to_date(psFechaFin   , 'dd/mm/yyyy')
          and a.SBAC_OID_SBAC = ls_oid_subacceso
          and a.VAL_NUME_DOCU_LEGA is not null);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '1'
       and b.VAL_NUME_DOCU_LEGA in (select c.VAL_NUME_DOCU_LEGA_SGTE
                                      from GTT_RUV_ASIGN_NULOS c
                                     where c.ind_proc = '1');

    insert into GTT_RUV_ASIGN_NULOS
      (select '2',
              a.NUM_DOCU_CONT_INTE,
              a.oid_regi,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA - 1 as ante
         from FAC_REGIS_UNICO_VENTA a
        where a.tido_oid_tipo_docu = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and trunc(a.FEC_EMIS) <= to_date(psFechaFin   , 'dd/mm/yyyy')
          and a.SBAC_OID_SBAC = ls_oid_subacceso);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '2'
       and b.VAL_NUME_DOCU_LEGA in
           (select c.VAL_NUME_DOCU_LEGA_SGTE
              from GTT_RUV_ASIGN_NULOS c
             where c.ind_proc = '2');

    -- Carga los arreglos
    OPEN c_fin;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_fin BULK COLLECT INTO v_nume_docu_lega
                              LIMIT rows;

      EXIT WHEN v_row_count = c_fin%ROWCOUNT;
      v_row_count := c_fin%ROWCOUNT;

      for i in 1..v_nume_docu_lega.count loop
          lista_fin(i) := (v_nume_docu_lega(i));
          --dbms_output.put_line(i||'-> '||(v_nume_docu_lega(i)));
      end loop;

    END LOOP;
    CLOSE c_fin;

    OPEN c_inicio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_inicio BULK COLLECT INTO v_nume_docu_lega
                                  LIMIT rows;

      EXIT WHEN v_row_count2 = c_inicio%ROWCOUNT;
      v_row_count2 := c_inicio%ROWCOUNT;

      for j in 1..v_nume_docu_lega.count loop
          lista_inicio(j) := (v_nume_docu_lega(j));
          --dbms_output.put_line(j||'-> '||(v_nume_docu_lega(j)));
      end loop;

    END LOOP;
    CLOSE c_inicio;

    DELETE RUV_ASIGN_NULOS_XDIAS_TEMPO;

    INSERT INTO RUV_ASIGN_NULOS_XDIAS_TEMPO(
    SELECT RR.VAL_NUME_DOCU_LEGA,
           RR.TIDO_OID_TIPO_DOCU,
           RR.FEC_EMIS
      FROM FAC_REGIS_UNICO_VENTA RR
     WHERE RR.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
       AND TRUNC(RR.FEC_EMIS) >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
       AND TRUNC(RR.FEC_EMIS) <= TO_DATE(psFechaFin   , 'dd/mm/yyyy'));

    -- Ejecuta el proceso de Asignar nulos
    for i in 1..(lista_fin.count-1) loop
        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);
        for j in aux_ini..aux_fin loop

           begin
            SELECT max(FEC_EMIS)
              INTO ls_fecha
              FROM RUV_ASIGN_NULOS_XDIAS_TEMPO RR
             WHERE rr.val_nume_docu_lega in (aux_ini - 1)
               AND RR.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
               AND TRUNC(RR.FEC_EMIS) >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
               AND TRUNC(RR.FEC_EMIS) <= TO_DATE(psFechaFin   , 'dd/mm/yyyy');
          exception
            when no_data_found then
              ls_fecha := to_date(psFechaInicio,'dd/mm/yyyy') + trunc((to_date(psFechaFin,'dd/mm/yyyy')-to_date(psFechaInicio,'dd/mm/yyyy'))/2);
          end;

           insert into FAC_REGIS_UNICO_VENTA(OID_REGI,
                                             PAIS_OID_PAIS,
                                             SOCI_OID_SOCI,
                                             SBAC_OID_SBAC,
                                             VAL_EJER_DOCU_INTE,
                                             NUM_DOCU_CONT_INTE,
                                             FEC_EMIS,
                                             VAL_BASE_IMPO,
                                             IMP_IMPU,
                                             IMP_TOTA,
                                             VAL_PUNT_EMIS,
                                             VAL_DOCU_INTE,
                                             VAL_NUME_IDEN_FISC,
                                             VAL_NUME_IDEN_NNAL,
                                             VAL_SERI_DOCU_REFE,
                                             VAL_NUME_DOCU_REFE,
                                             VAL_INTE_MORA,
                                             VAL_DESC,
                                             VAL_COMI,
                                             VAL_FLET,
                                             VAL_BASE_IMPO_NETO,
                                             VAL_NOM1,
                                             VAL_NOM2,
                                             VAL_APE1,
                                             VAL_APE2,
                                             CLIE_OID_CLIE,
                                             IND_ESTA,
                                             IND_TRAN_GRAT,
                                             IND_FACT_GRAT,
                                             DCCA_OID_CABE,
                                             TAIM_OID_TASA_IMPU,
                                             TIDO_OID_TIPO_DOCU,
                                             FEC_CIER,
                                             VAL_INDI_RUV,
                                             VAL_NUME_DOCU_LEGA,
                                             VAL_SERI_DOCU_LEGA,
                                             TIDO_TIPO_DOCU_REFE)
                                          values
                                            (FAC_RUVE_SEQ.NEXTVAL,
                                             ls_oid_pais,
                                             ls_oid_sociedad,
                                             ls_oid_subacceso,
                                             substr(ls_fecha,-2,2),
                                             NULL,
                                             ls_fecha,
                                             0,
                                             0,
                                             0,
                                             '000',
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             0,
                                             decode(substr(psCodigoPais,0,2),'VE','ANULADAS',null), --null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             1,
                                             0,
                                             0,
                                             null,
                                             ls_oid_tasa_impu,
                                             psOidTipoDocumento,
                                             null,
                                             'A',
                                             j,
                                             psNumeroSerie,
                                             null);
        end loop;
    end loop;

 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_PROCE_ASINU_XDIAS_VENEZ ' || ls_sqlerrm);

  end RUV_PR_PROCE_ASINU_XDIAS_VENEZ;

/**************************************************************************
    Descripcion         : RUV_PR_CANTI_ASIGN_NULOS_RANVE
                          Procedimiento que devuelve el numero de rangos a Asignar Nulos opcion por rango para Venezuela
    Fecha Creacion      : 26/03/2014
    Parametros Entrada:
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
        psRangoInicio       : Rango Inicio
        psRangoFin          : Rango Fin
        psNumeroAsigna OUT  : Devuelve el resultado
    Autor               : Aurelio Oviedo
  ***************************************************************************/
 PROCEDURE RUV_PR_CANTI_ASIGN_NULOS_RANVE(psOidTipoDocumento varchar2,
                                         psNumeroSerie      varchar2,
                                         psCodigoSubacceso  varchar2,
                                         psFechaInicio      varchar2,
                                         psFechaFin         varchar2,
                                         psRangoInicio      varchar2,
                                         psRangoFin         varchar2,
                                         psNumeroAsigna OUT varchar2) is
  ls_oid_subacceso number;
  ls_cont          number:=0;
  ls_cont_aux      number:=0;
  rows             NATURAL := 1000;   -- Number of rows to process at a time
  i                BINARY_INTEGER := 0;
  aux_ini          BINARY_INTEGER := 0;
  aux_fin          BINARY_INTEGER := 0;
  v_row_count      NUMBER := 0;
  v_row_count2     NUMBER := 0;

  TYPE t_nume_docu_lega  is table of GTT_RUV_ASIGN_NULOS.VAL_NUME_DOCU_LEGA%type;
  v_nume_docu_lega t_nume_docu_lega;

  type t_inicio is table of number(10) index by pls_integer;
  type t_fin    is table of number(10) index by pls_integer;
  lista_inicio   t_inicio;
  lista_fin      t_fin;

  cursor c_fin is
      select (b.val_nume_docu_lega - 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '1'
       order by b.val_nume_docu_lega;

  cursor c_inicio is
      select (b.val_nume_docu_lega + 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '2'
       order by b.val_nume_docu_lega;

  BEGIN
    -- obtiene el oid de subacceso
    SELECT s.oid_sbac into ls_oid_subacceso FROM SEG_SUBAC S where S.COD_SBAC = psCodigoSubacceso;

    insert into GTT_RUV_ASIGN_NULOS
      (select '1',
              a.NUM_DOCU_CONT_INTE,
              a.OID_REGI,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA + 1
         from FAC_REGIS_UNICO_VENTA a
        where a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and trunc(a.FEC_EMIS) <= to_date(psFechaFin   , 'dd/mm/yyyy')
          and a.val_nume_docu_lega >= to_number(psRangoInicio)
          and a.val_nume_docu_lega <= to_number(psRangoFin)
          and a.SBAC_OID_SBAC = ls_oid_subacceso
          and a.VAL_NUME_DOCU_LEGA is not null);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '1'
       and b.VAL_NUME_DOCU_LEGA in (select c.VAL_NUME_DOCU_LEGA_SGTE
                                      from GTT_RUV_ASIGN_NULOS c
                                     where c.ind_proc = '1');

    insert into GTT_RUV_ASIGN_NULOS
      (select '2',
              a.NUM_DOCU_CONT_INTE,
              a.oid_regi,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA - 1 as ante
         from FAC_REGIS_UNICO_VENTA a
        where a.tido_oid_tipo_docu = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and trunc(a.FEC_EMIS) <= to_date(psFechaFin   , 'dd/mm/yyyy')
          and a.val_nume_docu_lega >= to_number(psRangoInicio)
          and a.val_nume_docu_lega <= to_number(psRangoFin)
          and a.SBAC_OID_SBAC = ls_oid_subacceso);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '2'
       and b.VAL_NUME_DOCU_LEGA in
           (select c.VAL_NUME_DOCU_LEGA_SGTE
              from GTT_RUV_ASIGN_NULOS c
             where c.ind_proc = '2');

    -- Carga los arreglos
    OPEN c_fin;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_fin BULK COLLECT INTO v_nume_docu_lega
                              LIMIT rows;

      EXIT WHEN v_row_count = c_fin%ROWCOUNT;
      v_row_count := c_fin%ROWCOUNT;

      for i in 1..v_nume_docu_lega.count loop
          lista_fin(i) := (v_nume_docu_lega(i));
          --dbms_output.put_line(i||'-> '||(v_nume_docu_lega(i)));
      end loop;

    END LOOP;
    CLOSE c_fin;

    dbms_output.put_line('c_inicio');
    OPEN c_inicio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_inicio BULK COLLECT INTO v_nume_docu_lega
                                  LIMIT rows;

      EXIT WHEN v_row_count2 = c_inicio%ROWCOUNT;
      v_row_count2 := c_inicio%ROWCOUNT;

      for j in 1..v_nume_docu_lega.count loop
          lista_inicio(j) := (v_nume_docu_lega(j));
          --dbms_output.put_line(j||'-> '||(v_nume_docu_lega(j)));
      end loop;

    END LOOP;
    CLOSE c_inicio;

    -- Ejecuta el proceso de Asignar nulos
    for i in 1..(lista_fin.count-1) loop
        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);
        ls_cont_aux := (aux_fin - aux_ini) + 1;
        ls_cont := ls_cont + ls_cont_aux;
    end loop;

    IF ls_cont >= 0 THEN
        psNumeroAsigna := ls_cont;
    ELSE
        psNumeroAsigna := 'O';
    END IF;

 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_CANTI_ASIGN_NULOS_RANVE ' || ls_sqlerrm);
  end RUV_PR_CANTI_ASIGN_NULOS_RANVE;

  /**************************************************************************
    Descripcion         : RUV_PR_PROCE_ASINU_RANGO_VENEZ
                          Proceso de Asignar Nulos opcion por dias Venezuela
    Fecha Creacion      : 04/03/2014
    Parametros Entrada:
        psCodigoPais        : psCodigoPais
        psOidTipoDocumento  : Oid Tipo de Documento
        psNumeroSerie       : Numero de serie
        psCodigoSubacceso   : Codigo de subacceso
        psFechaInicio       : Fecha de inicio
        psFechaFin          : Fecha de fin
    Autor               : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE RUV_PR_PROCE_ASINU_RANGO_VENEZ(psCodigoPais       varchar2,
                                           psOidTipoDocumento varchar2,
                                           psNumeroSerie      varchar2,
                                           psCodigoSubacceso  varchar2,
                                           psFechaInicio      varchar2,
                                           psFechaFin         varchar2,
                                           psRangoInicio      varchar2,
                                           psRangoFin         varchar2) as

  TYPE t_nume_docu_lega  is table of GTT_RUV_ASIGN_NULOS.VAL_NUME_DOCU_LEGA%type;
  v_nume_docu_lega t_nume_docu_lega;

  type t_inicio is table of number(10) index by pls_integer;
  type t_fin    is table of number(10) index by pls_integer;
  lista_inicio   t_inicio;
  lista_fin      t_fin;

  cursor c_fin is
      select (b.val_nume_docu_lega - 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '1'
       order by b.val_nume_docu_lega;

  cursor c_inicio is
      select (b.val_nume_docu_lega + 1)
        FROM GTT_RUV_ASIGN_NULOS b
       where b.ind_proc = '2'
       order by b.val_nume_docu_lega;

  ls_oid_subacceso    SEG_SUBAC.Oid_Sbac%type;
  ls_oid_pais         SEG_PAIS.Oid_Pais%type;
  ls_oid_sociedad     seg_socie.oid_soci%type;
  ls_fecha            date;
  ls_oid_tasa_impu    ped_tasa_impue.oid_tasa_impu%type;

  rows             NATURAL := 1000;   -- Number of rows to process at a time
  i                BINARY_INTEGER := 0;
  j                BINARY_INTEGER := 0;
  aux_ini          BINARY_INTEGER := 0;
  aux_fin          BINARY_INTEGER := 0;
  v_row_count      NUMBER := 0;
  v_row_count2     NUMBER := 0;

  BEGIN
    -- obtiene el oid del pais
    select OID_PAIS into ls_oid_pais from SEG_PAIS where cod_pais = psCodigoPais;
    -- obtiene el oid de sociedad
    select ss.oid_soci
      into ls_oid_sociedad
      from seg_socie ss
     where ss.cod_soci in (select VAL_PARA
                             from bas_param_pais
                            where cod_pais = psCodigoPais
                              and cod_sist = 'RUV'
                              and nom_para = 'codigoSociedad');
    -- obtiene el oid de subacceso
    SELECT s.oid_sbac into ls_oid_subacceso FROM SEG_SUBAC S where S.COD_SBAC = psCodigoSubacceso;
    -- obtiene la fecha
    ls_fecha := to_date(psFechaInicio,'dd/mm/yyyy') + trunc((to_date(psFechaFin,'dd/mm/yyyy')-to_date(psFechaInicio,'dd/mm/yyyy'))/2);
    -- Obtiene el oid de tasa de impuesto
    select oid_tasa_impu
    into ls_oid_tasa_impu
    from ped_tasa_impue
    where val_indi_impu = (select VAL_PARA from bas_param_pais where cod_pais = psCodigoPais and cod_sist = 'RUV' and NOM_PARA = 'impuestoVentas');

    insert into GTT_RUV_ASIGN_NULOS
      (select '1',
              a.NUM_DOCU_CONT_INTE,
              a.OID_REGI,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA + 1
         from FAC_REGIS_UNICO_VENTA a
        where a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and trunc(a.FEC_EMIS) <= to_date(psFechaFin   , 'dd/mm/yyyy')
          and a.val_nume_docu_lega >= to_number(psRangoInicio)
          and a.val_nume_docu_lega <= to_number(psRangoFin)
          and a.SBAC_OID_SBAC = ls_oid_subacceso
          and a.VAL_NUME_DOCU_LEGA is not null);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '1'
       and b.VAL_NUME_DOCU_LEGA in (select c.VAL_NUME_DOCU_LEGA_SGTE
                                      from GTT_RUV_ASIGN_NULOS c
                                     where c.ind_proc = '1');

    insert into GTT_RUV_ASIGN_NULOS
      (select '2',
              a.NUM_DOCU_CONT_INTE,
              a.oid_regi,
              a.VAL_NUME_DOCU_LEGA,
              a.VAL_NUME_DOCU_LEGA - 1 as ante
         from FAC_REGIS_UNICO_VENTA a
        where a.tido_oid_tipo_docu = psOidTipoDocumento
          and a.val_seri_docu_lega = psNumeroSerie
          and trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
    		  and trunc(a.FEC_EMIS) <= to_date(psFechaFin   , 'dd/mm/yyyy')
          and a.val_nume_docu_lega >= to_number(psRangoInicio)
          and a.val_nume_docu_lega <= to_number(psRangoFin)
          and a.SBAC_OID_SBAC = ls_oid_subacceso);

    DELETE FROM GTT_RUV_ASIGN_NULOS b
     where b.ind_proc = '2'
       and b.VAL_NUME_DOCU_LEGA in
           (select c.VAL_NUME_DOCU_LEGA_SGTE
              from GTT_RUV_ASIGN_NULOS c
             where c.ind_proc = '2');

    -- Carga los arreglos
    OPEN c_fin;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_fin BULK COLLECT INTO v_nume_docu_lega
                              LIMIT rows;

      EXIT WHEN v_row_count = c_fin%ROWCOUNT;
      v_row_count := c_fin%ROWCOUNT;

      for i in 1..v_nume_docu_lega.count loop
          lista_fin(i) := (v_nume_docu_lega(i));
          --dbms_output.put_line(i||'-> '||(v_nume_docu_lega(i)));
      end loop;

    END LOOP;
    CLOSE c_fin;

    OPEN c_inicio;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH c_inicio BULK COLLECT INTO v_nume_docu_lega
                                  LIMIT rows;

      EXIT WHEN v_row_count2 = c_inicio%ROWCOUNT;
      v_row_count2 := c_inicio%ROWCOUNT;

      for j in 1..v_nume_docu_lega.count loop
          lista_inicio(j) := (v_nume_docu_lega(j));
          --dbms_output.put_line(j||'-> '||(v_nume_docu_lega(j)));
      end loop;

    END LOOP;
    CLOSE c_inicio;

    DELETE RUV_ASIGN_NULOS_XDIAS_TEMPO;

    INSERT INTO RUV_ASIGN_NULOS_XDIAS_TEMPO(
    SELECT RR.VAL_NUME_DOCU_LEGA,
           RR.TIDO_OID_TIPO_DOCU,
           RR.FEC_EMIS
      FROM FAC_REGIS_UNICO_VENTA RR
     WHERE RR.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
       AND TRUNC(RR.FEC_EMIS) >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
       AND TRUNC(RR.FEC_EMIS) <= TO_DATE(psFechaFin   , 'dd/mm/yyyy')
       and RR.val_nume_docu_lega >= to_number(psRangoInicio)
       and RR.val_nume_docu_lega <= to_number(psRangoFin));

    -- Ejecuta el proceso de Asignar nulos
    for i in 1..(lista_fin.count-1) loop
        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);
        for j in aux_ini..aux_fin loop

          begin
            SELECT max(RR.FEC_EMIS)
              INTO ls_fecha
              FROM RUV_ASIGN_NULOS_XDIAS_TEMPO RR
             WHERE rr.val_nume_docu_lega in (aux_ini - 1)
               AND RR.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
               AND TRUNC(RR.FEC_EMIS) >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
               AND TRUNC(RR.FEC_EMIS) <= TO_DATE(psFechaFin   , 'dd/mm/yyyy');
          exception
            when no_data_found then
              ls_fecha := to_date(psFechaInicio,'dd/mm/yyyy') + trunc((to_date(psFechaFin,'dd/mm/yyyy')-to_date(psFechaInicio,'dd/mm/yyyy'))/2);
          end;

           insert into FAC_REGIS_UNICO_VENTA(OID_REGI,
                                             PAIS_OID_PAIS,
                                             SOCI_OID_SOCI,
                                             SBAC_OID_SBAC,
                                             VAL_EJER_DOCU_INTE,
                                             NUM_DOCU_CONT_INTE,
                                             FEC_EMIS,
                                             VAL_BASE_IMPO,
                                             IMP_IMPU,
                                             IMP_TOTA,
                                             VAL_PUNT_EMIS,
                                             VAL_DOCU_INTE,
                                             VAL_NUME_IDEN_FISC,
                                             VAL_NUME_IDEN_NNAL,
                                             VAL_SERI_DOCU_REFE,
                                             VAL_NUME_DOCU_REFE,
                                             VAL_INTE_MORA,
                                             VAL_DESC,
                                             VAL_COMI,
                                             VAL_FLET,
                                             VAL_BASE_IMPO_NETO,
                                             VAL_NOM1,
                                             VAL_NOM2,
                                             VAL_APE1,
                                             VAL_APE2,
                                             CLIE_OID_CLIE,
                                             IND_ESTA,
                                             IND_TRAN_GRAT,
                                             IND_FACT_GRAT,
                                             DCCA_OID_CABE,
                                             TAIM_OID_TASA_IMPU,
                                             TIDO_OID_TIPO_DOCU,
                                             FEC_CIER,
                                             VAL_INDI_RUV,
                                             VAL_NUME_DOCU_LEGA,
                                             VAL_SERI_DOCU_LEGA,
                                             TIDO_TIPO_DOCU_REFE)
                                          values
                                            (FAC_RUVE_SEQ.NEXTVAL,
                                             ls_oid_pais,
                                             ls_oid_sociedad,
                                             ls_oid_subacceso,
                                             substr(ls_fecha,-2,2),
                                             NULL,
                                             ls_fecha,
                                             0,
                                             0,
                                             0,
                                             '000',
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             0,
                                             decode(substr(psCodigoPais,0,2),'VE','ANULADAS',null), --null,
                                             null,
                                             null,
                                             null,
                                             null,
                                             1,
                                             0,
                                             0,
                                             null,
                                             ls_oid_tasa_impu,
                                             psOidTipoDocumento,
                                             null,
                                             'A',
                                             j,
                                             psNumeroSerie,
                                             null);
        end loop;
    end loop;

 EXCEPTION
   WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm, 1, 1000);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_PROCE_ASINU_RANGO_VENEZ ' || ls_sqlerrm);

  end RUV_PR_PROCE_ASINU_RANGO_VENEZ;

  /**************************************************************************
   Descripcion         : Proceso de Asignar Nulos opcion por dias por Venezuela
   Fecha Creacion      : 26/03/2014
   Autor               : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE RUV_PR_ASIGN_NULOS_XDIAS_XVENE(
    psCodigoPais       varchar2,
    psOidTipoDocumento varchar2,
    psNumeroSerie      varchar2,
    psCodigoSubacceso  varchar2,
    psFechaInicio      varchar2,
    psFechaFin         varchar2
  ) IS

    -- Cursores ---------------------------------
    CURSOR C_INICIA (
        varOidPais            SEG_PAIS.OID_PAIS%TYPE,
        varOidSubacceso  SEG_SUBAC.OID_SBAC%TYPE) IS
      SELECT A.VAL_NUME_DOCU_LEGA
        FROM FAC_REGIS_UNICO_VENTA A
      WHERE A.PAIS_OID_PAIS = varOidPais
           AND A.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
           AND A.VAL_SERI_DOCU_LEGA = psNumeroSerie
           AND TRUNC(A.FEC_EMIS) >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
           AND TRUNC(A.FEC_EMIS) <= TO_DATE(psFechaFin, 'dd/mm/yyyy')
           AND A.SBAC_OID_SBAC = varOidSubacceso
           AND A.NUM_CONT_DOCU_LEGA IS NULL
       ORDER BY A.VAL_NUME_DOCU_LEGA;

    /*CURSOR C_FIN IS
      select    (b.NUM_CONT_DOCU_LEGA - 1)
      FROM      RUV_GTT_NUMCO_NULOS b
      where     b.ind_proc = '1'
      order by  b.NUM_CONT_DOCU_LEGA;

    CURSOR C_INICIO IS
      select    (b.NUM_CONT_DOCU_LEGA + 1)
      FROM      RUV_GTT_NUMCO_NULOS b
      where     b.ind_proc = '2'
      order by  b.NUM_CONT_DOCU_LEGA;

    CURSOR C_LISTA_FINAL(
      varOidPais       SEG_PAIS.Oid_Pais%type,
      varOidSubacceso  SEG_SUBAC.Oid_Sbac%type
    ) IS
      select    a.oid_regi,
                a.val_ejer_docu_inte,
                a.fec_emis,
                a.val_nume_docu_lega,
                a.num_cont_docu_lega
      from      FAC_REGIS_UNICO_VENTA a
      where     a.pais_oid_pais = varOidPais
        and     a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
        and     a.val_seri_docu_lega = psNumeroSerie
        and     trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
        and     trunc(a.FEC_EMIS) <= to_date(psFechaFin, 'dd/mm/yyyy')
        and     a.SBAC_OID_SBAC = varOidSubacceso
        and     a.NUM_CONT_DOCU_LEGA is null
      order by  a.val_nume_docu_lega;*/

    TYPE listaFinalRecord is record (
      oidRegi RUV_ASIGN_NUMER_CONTR_TEMPO.OID_REGI%TYPE,
      valEjer RUV_ASIGN_NUMER_CONTR_TEMPO.VAL_EJER_DOCU_INTE%TYPE,
      fecEmis RUV_ASIGN_NUMER_CONTR_TEMPO.FEC_EMIS%TYPE,
      valNume RUV_ASIGN_NUMER_CONTR_TEMPO.VAL_NUME_DOCU_LEGA%TYPE,
      numCont RUV_ASIGN_NUMER_CONTR_TEMPO.NUM_CONT_DOCU_LEGA%TYPE
    );

    TYPE listaFinalTab IS TABLE OF listaFinalRecord;
    listaFinal listaFinalTab;

    TYPE t_num_cont_docu is table of RUV_GTT_NUMCO_NULOS.NUM_CONT_DOCU_LEGA%type;
    v_num_cont_docu t_num_cont_docu;

    type t_inicio is table of number(10) index by pls_integer;
    lista_inicio   t_inicio;

    type t_fin is table of number(10) index by pls_integer;
    lista_fin t_fin;

    type t_corre is table of number(10) index by pls_integer;
    lista_corre t_corre;

    -- Variables --------------------------------
    ls_oid_pais       SEG_PAIS.Oid_Pais%type;
    ls_oid_subacceso  SEG_SUBAC.Oid_Sbac%type;
    rows              NATURAL := 1000;
    aux_ini           BINARY_INTEGER := 0;
    aux_fin           BINARY_INTEGER := 0;
    contador          NUMBER(10) := 0;

    numContDocuLega   FAC_REGIS_UNICO_VENTA.NUM_CONT_DOCU_LEGA%type;
    valNumeDocuLega   FAC_REGIS_UNICO_VENTA.VAL_NUME_DOCU_LEGA%type;
    numContDocuLegaSgte   FAC_REGIS_UNICO_VENTA.NUM_CONT_DOCU_LEGA%type;
    i NATURAL := 1;
    j NATURAL := 1;

  BEGIN

    -- Obtener oid pais
    select    OID_PAIS
      into    ls_oid_pais
    from      SEG_PAIS
    where     cod_pais = psCodigoPais;

    -- Obtener oid subacceso
    SELECT    s.oid_sbac
      into    ls_oid_subacceso
    FROM      SEG_SUBAC S
    where     S.COD_SBAC = psCodigoSubacceso;

    -- Insertar y eliminar los siguientes consecutivos -------------------------
    /*INSERT INTO RUV_GTT_NUMCO_NULOS
    (
      select    '1',
                a.NUM_DOCU_CONT_INTE,
                a.OID_REGI,
                a.NUM_CONT_DOCU_LEGA,
                (a.NUM_CONT_DOCU_LEGA + 1)
      from      FAC_REGIS_UNICO_VENTA a
      where     a.pais_oid_pais = ls_oid_pais
        and     a.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
        and     a.val_seri_docu_lega = psNumeroSerie
        and     trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
        and     trunc(a.FEC_EMIS) <= to_date(psFechaFin, 'dd/mm/yyyy')
        and     a.SBAC_OID_SBAC = ls_oid_subacceso
        and     a.NUM_CONT_DOCU_LEGA is not null
    );

    DELETE
    FROM      RUV_GTT_NUMCO_NULOS b
    where     b.ind_proc = '1'
      and     b.NUM_CONT_DOCU_LEGA in (
                select    c.NUM_CONT_DOCU_LEGA_SGTE
                from      RUV_GTT_NUMCO_NULOS c
                where     c.ind_proc = '1'
              );*/

    -- Insertar y eliminar los anteriores consecutivos ------------------------
    /*INSERT INTO RUV_GTT_NUMCO_NULOS
    (
      select    '2',
                a.NUM_DOCU_CONT_INTE,
                a.oid_regi,
                a.NUM_CONT_DOCU_LEGA,
                (a.NUM_CONT_DOCU_LEGA - 1)
      from      FAC_REGIS_UNICO_VENTA a
      where     a.pais_oid_pais = ls_oid_pais
        and     a.tido_oid_tipo_docu = psOidTipoDocumento
        and     a.val_seri_docu_lega = psNumeroSerie
        and     trunc(a.FEC_EMIS) >= to_date(psFechaInicio, 'dd/mm/yyyy')
        and     trunc(a.FEC_EMIS) <= to_date(psFechaFin, 'dd/mm/yyyy')
        and     a.SBAC_OID_SBAC = ls_oid_subacceso
    );

    DELETE
    FROM      RUV_GTT_NUMCO_NULOS b
    where     b.ind_proc = '2'
      and     b.NUM_CONT_DOCU_LEGA in(
                select    c.NUM_CONT_DOCU_LEGA_SGTE
                from      RUV_GTT_NUMCO_NULOS c
                where     c.ind_proc = '2'
              );*/

    -- Cargar Arreglos --------------------------------------------------------
    /*OPEN C_FIN;
    LOOP

      FETCH C_FIN BULK COLLECT INTO v_num_cont_docu LIMIT rows;
      IF v_num_cont_docu.COUNT > 0 THEN

        -- Recorrer el Cursor Paginado de C_FIN
        FOR i IN v_num_cont_docu.FIRST .. v_num_cont_docu.LAST LOOP
          lista_fin(i) := v_num_cont_docu(i);
        END LOOP; -- Fin del Cursor Paginado de C_FIN

      END IF; -- t_num_cont_docu.COUNT

      EXIT WHEN C_FIN%NOTFOUND;
    END LOOP;
    CLOSE C_FIN;

    OPEN C_INICIO;
    LOOP

      FETCH C_INICIO BULK COLLECT INTO v_num_cont_docu LIMIT rows;
      IF v_num_cont_docu.COUNT > 0 THEN

        -- Recorrer el Cursor Paginado de C_INICIO
        FOR j IN v_num_cont_docu.FIRST .. v_num_cont_docu.LAST LOOP
          lista_inicio(j) := v_num_cont_docu(j);
        END LOOP; -- Fin del Cursor Paginado de C_INICIO

      END IF; -- v_num_cont_docu.COUNT > 0

      EXIT WHEN C_INICIO%NOTFOUND;
    END LOOP;
    CLOSE C_INICIO;*/

    -- Cargar Arreglos --------------------------------------------------------
    /*OPEN C_FIN;
    LOOP
      FETCH C_FIN INTO numContDocuLega;
      EXIT WHEN C_FIN%NOTFOUND;

      lista_fin(i) := numContDocuLega;
      i := i + 1;

    END LOOP;
    CLOSE C_FIN;

    OPEN C_INICIO;
    LOOP
      FETCH C_INICIO INTO numContDocuLega;
      EXIT WHEN C_INICIO%NOTFOUND;

      lista_inicio(j) := numContDocuLega;
      j := j + 1;

    END LOOP;
    CLOSE C_INICIO;*/

    -- Ejecutar el proceso de Asignar nulos -----------------------------------
    /*IF lista_fin.COUNT > 0 THEN

      FOR i IN lista_fin.FIRST .. (lista_fin.LAST-1) LOOP

        aux_ini := lista_inicio(i);
        aux_fin := lista_fin(i+1);

        FOR j IN aux_ini .. aux_fin LOOP
          contador := contador + 1;
          lista_corre(contador) := j;
        END LOOP;

      END LOOP;
    END IF;*/

    DELETE RUV_OBTIE_NUMER_CONTR_TEMPO;

    INSERT INTO RUV_OBTIE_NUMER_CONTR_TEMPO (OID_REGI, VAL_EJER_DOCU_INTE, FEC_EMIS, VAL_NUME_DOCU_LEGA, NUM_CONT_DOCU_LEGA)
    (SELECT A.OID_REGI,
                  A.VAL_EJER_DOCU_INTE,
                  A.FEC_EMIS,
                  A.VAL_NUME_DOCU_LEGA,
                  A.NUM_CONT_DOCU_LEGA
        FROM FAC_REGIS_UNICO_VENTA A
      WHERE A.PAIS_OID_PAIS = ls_oid_pais
           AND A.TIDO_OID_TIPO_DOCU = psOidTipoDocumento
           AND A.VAL_SERI_DOCU_LEGA = psNumeroSerie
           AND TRUNC(A.FEC_EMIS) >= TO_DATE(psFechaInicio, 'dd/mm/yyyy')
           AND TRUNC(A.FEC_EMIS) <= TO_DATE(psFechaFin, 'dd/mm/yyyy')
           AND A.SBAC_OID_SBAC = ls_oid_subacceso);

    DELETE RUV_ASIGN_NUMER_CONTR_TEMPO;

    OPEN C_INICIA(
      ls_oid_pais,
      ls_oid_subacceso
    );
    LOOP
      FETCH C_INICIA INTO valNumeDocuLega;
      EXIT WHEN C_INICIA%NOTFOUND;

      SELECT NUM_CONT_DOCU_LEGA + 1
         INTO numContDocuLegaSgte
        FROM RUV_OBTIE_NUMER_CONTR_TEMPO
      WHERE VAL_NUME_DOCU_LEGA = valNumeDocuLega - 1;

      UPDATE RUV_OBTIE_NUMER_CONTR_TEMPO
            SET NUM_CONT_DOCU_LEGA = numContDocuLegaSgte
       WHERE VAL_NUME_DOCU_LEGA = valNumeDocuLega;

      INSERT INTO RUV_ASIGN_NUMER_CONTR_TEMPO (
            OID_REGI,
            VAL_EJER_DOCU_INTE,
            FEC_EMIS,
            VAL_NUME_DOCU_LEGA,
            NUM_CONT_DOCU_LEGA)
       (SELECT A.OID_REGI,
                  A.VAL_EJER_DOCU_INTE,
                  A.FEC_EMIS,
                  A.VAL_NUME_DOCU_LEGA,
                  A.NUM_CONT_DOCU_LEGA
        FROM RUV_OBTIE_NUMER_CONTR_TEMPO A
      WHERE VAL_NUME_DOCU_LEGA = valNumeDocuLega);

    END LOOP;
    CLOSE C_INICIA;

    -- Asignar nulos a tabla temporal -----------------------------------------
    /*OPEN C_LISTA_FINAL(
      ls_oid_pais,
      ls_oid_subacceso
    );
    LOOP
      FETCH C_LISTA_FINAL BULK COLLECT INTO listaFinal LIMIT W_FILAS;
      IF listaFinal.COUNT > 0 THEN

        -- Recorrer el Cursor Paginado de C_LISTA_FINAL
        FOR i IN listaFinal.FIRST .. listaFinal.LAST LOOP

          INSERT INTO RUV_ASIGN_NUMER_CONTR_TEMPO (
            OID_REGI,
            VAL_EJER_DOCU_INTE,
            FEC_EMIS,
            VAL_NUME_DOCU_LEGA,
            NUM_CONT_DOCU_LEGA
          ) VALUES (
            listaFinal(i).oidRegi,
            listaFinal(i).valEjer,
            listaFinal(i).fecEmis,
            listaFinal(i).valNume,
            lista_corre(i)
          );

        END LOOP; -- Fin del Cursor Paginado de C_LISTA_FINAL
      END IF; -- listaFinal.COUNT > 0

      EXIT WHEN C_LISTA_FINAL%NOTFOUND;
    END LOOP;
    CLOSE C_LISTA_FINAL;*/

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_ASIGN_NULOS_XDIAS_XVENE : ' || ls_sqlerrm);
  END RUV_PR_ASIGN_NULOS_XDIAS_XVENE;
  
/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte de
                    Detalle de NC por Marca y UN (Bolivia)
Fecha Creacion    : 25/05/2015
Autor             : Gonzalo Huertas
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psFechaInicio: Fecha Inicio
            psFechaFin: Fecha Fin
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_DNCMU_CSV(
      psCodigoPais        VARCHAR2,
      psNombreArchivo     VARCHAR2,
      psTitulo            VARCHAR2,
      psFechaInicio       VARCHAR2,
      psFechaFin          VARCHAR2,
      psDirectorio        OUT  VARCHAR2
      )
  IS

    lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS             NUMBER := 5000 ;
    v_handle            UTL_FILE.FILE_TYPE;
    W_DESC              VARCHAR2(200);
    lsLinea             VARCHAR2(4000);
    lsNombreArchivo     VARCHAR2(50);
    lsCodigoPais        VARCHAR2(3);
    CURSOR c_interfaz IS
    select f.cod_sap SAP,
           j.val_i18n DESCRIPCION,
           r.VAL_DESC_LARG TIPO,
           k.des_marc_prod MARCA,
           g.cod_unid_nego UNIDAD_NEGOCIO,
           m.cod_tipo_ofer TIPO_OFERTA,
           b.num_unid_aten UNIDADES,
           (b.VAL_PREC_SIN_IMPU_TOTA_LOCA - b.imp_desc_sin_impu_tota_loca) VENTA_NETA,
           b.IMP_IMPU_TOTA_LOCA IVA,
           --decode(b.val_prec_cata_unit_loca, 0, 0, b.val_prec_fact_tota_loca) TOTAL_PRODUCTO,
           b.val_prec_cata_tota_loca-b.imp_desc_tota_loca TOTAL_PRODUCTO,
           i.cod_peri CAMPAÑA_PROCESO,
           a.fec_fact FECHA_PROCESO,
           n.camp_refe CAMPAÑA_REFERENCIA,
           n.fec_emis_refe FECHA_REFERENCIA
      from fac_docum_conta_cabec a,
           fac_docum_conta_linea b,
           ped_solic_posic       c,
           mae_clien_direc       d,
           mae_clien             e,
           mae_produ             f,
           mae_unida_negoc       g,
           cra_perio             h,
           seg_perio_corpo       i,
           gen_i18n_sicc_pais    j,
           seg_marca_produ       k,
           pre_ofert_detal       l,
           pre_tipo_ofert        m,
           fac_regis_unico_venta n,
           ped_solic_cabec       n1,
           rec_solic_opera       o,
           rec_opera_recla       p,
           rec_tipos_opera       q,
           rec_opera             r
     where trunc(a.fec_fact) >= to_date(psFechaInicio, 'dd/mm/yyyy')
       and trunc(a.fec_fact) <= to_date(psFechaFin, 'dd/mm/yyyy')
       and a.tido_oid_tipo_docu = 32
       and a.oid_cabe = b.dcca_oid_cabe
       and b.sopo_oid_soli_posi = c.oid_soli_posi
       and a.cldi_oid_clie_dire = d.oid_clie_dire
       and d.clie_oid_clie = e.oid_clie
       and b.prod_oid_prod = f.oid_prod
       and f.oid_prod = j.val_oid
       and j.attr_enti = 'MAE_PRODU'
       and f.uneg_oid_unid_nego = g.oid_unid_nego
       and f.mapr_oid_marc_prod = k.oid_marc_prod
       and a.perd_oid_peri = h.oid_peri
       and h.peri_oid_peri = i.oid_peri
       and a.oid_cabe = n.dcca_oid_cabe
       and c.ofde_oid_deta_ofer = l.oid_deta_ofer(+)
       and l.tofe_oid_tipo_ofer = m.oid_tipo_ofer(+)
       and c.soca_oid_soli_cabe = n1.oid_soli_cabe
       and n1.oid_soli_cabe = o.soca_oid_soli_cabe
       and o.opre_oid_oper_recl = p.oid_oper_recl
       and p.tiop_oid_tipo_oper = q.oid_tipo_oper
       and q.ROPE_OID_OPER = r.oid_oper
       and a.pais_oid_pais = gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais)
     order by f.cod_sap,
              j.val_i18n,
              r.VAL_DESC_LARG,
              k.des_marc_prod,
              g.cod_unid_nego,
              m.cod_tipo_ofer,
              c.val_codi_vent,
              i.cod_peri,
              j.val_i18n;

  TYPE interfazTipo IS RECORD (
       sap          mae_produ.cod_sap%type,
       descripcion  gen_i18n_sicc_pais.val_i18n%type,
       tipo         rec_opera.VAL_DESC_LARG%type,
       marca        seg_marca_produ.des_marc_prod%type,
       UNIDAD_NEGOCIO  mae_unida_negoc.cod_unid_nego%type,
       TIPO_OFERTA     pre_tipo_ofert.cod_tipo_ofer%type,
       UNIDADES        fac_docum_conta_linea.num_unid_aten%type,
       VENTA_NETA      fac_docum_conta_linea.VAL_PREC_SIN_IMPU_TOTA_LOCA%type,
       IVA             fac_docum_conta_linea.IMP_IMPU_TOTA_LOCA%type,
       TOTAL_PRODUCTO  fac_docum_conta_linea.val_prec_cata_unit_loca%type,
       CAMPANA_PROCESO seg_perio_corpo.cod_peri%type,
       FECHA_PROCESO   fac_docum_conta_cabec.fec_fact%type,
       CAMPANA_REFERENCIA     fac_regis_unico_venta.camp_refe%type,
       FECHA_REFERENCIA       fac_regis_unico_venta.fec_emis_refe%type  
  );

     TYPE interfazTab  IS TABLE OF interfazTipo ;
     interfazRecord interfazTab;
     lbAbrirUtlFile  BOOLEAN;
  BEGIN
    lbAbrirUtlFile := TRUE;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_interfaz;
    LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
        psDirectorio := lsDirTempo;
        lbAbrirUtlFile := FALSE;
     END IF ;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea := '=T("'||interfazRecord(x).sap||'")' ||','||
                      REPLACE(interfazRecord(x).descripcion,',','-') ||','||
                      interfazRecord(x).tipo ||','||
                      interfazRecord(x).marca ||','||
                      '=T("'||interfazRecord(x).UNIDAD_NEGOCIO||'")' ||','||
                      '=T("'||interfazRecord(x).TIPO_OFERTA||'")' ||','||
                      '=T("'||interfazRecord(x).UNIDADES||'")' ||','||
                      '=T("'||interfazRecord(x).VENTA_NETA||'")' ||','||
                      '=T("'||interfazRecord(x).IVA||'")' ||','||
                      '=T("'||interfazRecord(x).TOTAL_PRODUCTO||'")' ||','||
                      interfazRecord(x).CAMPANA_PROCESO ||','||
                      TO_CHAR(interfazRecord(x).FECHA_PROCESO,'dd/mm/yyyy')||','||
                      interfazRecord(x).CAMPANA_REFERENCIA ||','||
                      TO_CHAR(interfazRecord(x).FECHA_REFERENCIA,'dd/mm/yyyy');

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
   END LOOP;
   CLOSE c_interfaz;
   IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);
   END IF;
   RETURN;
  EXCEPTION
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_DNCMU_CSV: '||ls_sqlerrm);
  END RUV_PR_GENER_REPOR_DNCMU_CSV;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte de
                      Cabeceras de Facturas Anuladas (Bolivia)
Fecha Creacion    : 25/05/2015
Autor             : Gonzalo Huertas
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psFechaInicio: Fecha Inicio
            psFechaFin: Fecha Fin
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_CABFA_CSV(
      psCodigoPais        VARCHAR2,
      psNombreArchivo     VARCHAR2,
      psTitulo            VARCHAR2,
      psFechaInicio       VARCHAR2,
      psFechaFin          VARCHAR2,
      psDirectorio        OUT  VARCHAR2
      )
  IS

    lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS             NUMBER := 5000 ;
    v_handle            UTL_FILE.FILE_TYPE;
    W_DESC              VARCHAR2(200);
    lsLinea             VARCHAR2(4000);
    lsNombreArchivo     VARCHAR2(50);
    lsCodigoPais        VARCHAR2(3);
    CURSOR c_interfaz IS
    select a.fec_fact FECHA_ANULACION,
           i.cod_peri CAMPANA_ANULACION,
           e.cod_clie CONSULTORA,
           e.val_ape1 || '/' || a.val_ape2 || '/' || a.val_nom1 || '/' || a.val_nom2 NOMBRE,
           c.val_nume_soli_refe NUMERO_PEDIDO_ORIGEN,
           c.camp_refe CAMPANA_ORIGEN,
           c.fec_emis_refe FECHA_ORIGEN,
           a.imp_flet_tota_loca FLETE,
           a.val_tota_gast_admi INTERES,
           a.val_prec_cata_tota_loca - a.imp_desc_tota_loca TOTAL_PRODUCTO,
           a.val_tota_paga_loca TOTAL_PEDIDO,
           (select ruv.imp_tota 
              from fac_regis_unico_venta ruv,  fac_docum_conta_cabec fc,  ped_solic_cabec psc
             where ruv.dcca_oid_cabe = fc.oid_cabe
               and fc.soca_oid_soli_cabe = psc.oid_soli_cabe
               and psc.tspa_oid_tipo_soli_pais = 20377 
               and ruv.tido_oid_tipo_docu =32
               and ruv.clie_oid_clie=c.clie_oid_clie
               and ruv.val_nume_soli_refe=c.val_nume_soli_refe) FALTANTE_ANULACION
      from fac_docum_conta_cabec a,
           ped_solic_cabec       b,
           fac_regis_unico_venta c,
           mae_clien_direc       d,
           mae_clien             e,
           cra_perio             h,
           seg_perio_corpo       i,
           ped_tipo_solic_pais   j,
           ped_tipo_solic        k
     where trunc(a.fec_fact) >= to_date(psFechaInicio,'dd/mm/yyyy')
       and trunc(a.fec_fact) <= to_date(psFechaFin,'dd/mm/yyyy')
       and a.tido_oid_tipo_docu = 32
       and a.oid_cabe = c.dcca_oid_cabe
       and a.soca_oid_soli_cabe = b.oid_soli_cabe
       and b.tspa_oid_tipo_soli_pais = j.oid_tipo_soli_pais
       and j.tsol_oid_tipo_soli = k.oid_tipo_soli
       and k.ind_anul = 1
       and a.cldi_oid_clie_dire = d.oid_clie_dire
       and d.clie_oid_clie = e.oid_clie
       and a.perd_oid_peri = h.oid_peri
       and h.peri_oid_peri = i.oid_peri
       and a.pais_oid_pais = gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

  TYPE interfazTipo IS RECORD (
           FECHA_ANULACION    fac_docum_conta_cabec.fec_fact%TYPE,
           CAMPANA_ANULACION  seg_perio_corpo.cod_peri%TYPE,
           CONSULTORA         mae_clien.cod_clie%TYPE,
           NOMBRE VARCHAR2(500),
           NUMERO_PEDIDO_ORIGEN fac_regis_unico_venta.val_nume_soli_refe%TYPE,
           CAMPANA_ORIGEN       fac_regis_unico_venta.camp_refe%TYPE,
           FECHA_ORIGEN         fac_regis_unico_venta.fec_emis_refe%TYPE,
           FLETE                fac_docum_conta_cabec.imp_flet_tota_loca%TYPE,
           INTERES              fac_docum_conta_cabec.val_tota_gast_admi%TYPE,
           TOTAL_PRODUCTO       fac_docum_conta_cabec.val_prec_cata_tota_loca%TYPE,
           TOTAL_PEDIDO         fac_docum_conta_cabec.val_tota_paga_loca%TYPE,
           FALTANTE_ANULACION   fac_regis_unico_venta.imp_tota%TYPE
  );

     TYPE interfazTab  IS TABLE OF interfazTipo ;
     interfazRecord interfazTab;
     lbAbrirUtlFile  BOOLEAN;
  BEGIN
    lbAbrirUtlFile := TRUE;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_interfaz;
    LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
        psDirectorio := lsDirTempo;
        lbAbrirUtlFile := FALSE;
     END IF ;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea := TO_CHAR(interfazRecord(x).FECHA_ANULACION,'dd/mm/yyyy')||','||
                      interfazRecord(x).CAMPANA_ANULACION ||','||
                      '=T("'||interfazRecord(x).CONSULTORA||'")' ||','||
                      interfazRecord(x).NOMBRE ||','||
                      '=T("'||interfazRecord(x).NUMERO_PEDIDO_ORIGEN||'")' ||','||
                      interfazRecord(x).CAMPANA_ORIGEN ||','||
                      TO_CHAR(interfazRecord(x).FECHA_ORIGEN,'dd/mm/yyyy')||','||
                      '=T("'||interfazRecord(x).FLETE||'")' ||','||
                      '=T("'||interfazRecord(x).INTERES||'")' ||','||                      
                      '=T("'||interfazRecord(x).TOTAL_PRODUCTO||'")' ||','||
                      '=T("'||interfazRecord(x).TOTAL_PEDIDO||'")' ||','||
                      '=T("'||interfazRecord(x).FALTANTE_ANULACION||'")';
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
   END LOOP;
   CLOSE c_interfaz;
   IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);
   END IF;
   RETURN;
  EXCEPTION
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_CABFA_CSV: '||ls_sqlerrm);
  END RUV_PR_GENER_REPOR_CABFA_CSV;
  
/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte de
                      Detalle Productos Atendidos x UN (Bolivia)
Fecha Creacion    : 25/05/2015
Autor             : Gonzalo Huertas
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psFechaInicio: Fecha Inicio
            psFechaFin: Fecha Fin
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_DETAU_CSV(
      psCodigoPais        VARCHAR2,
      psNombreArchivo     VARCHAR2,
      psTitulo            VARCHAR2,
      psFechaInicio       VARCHAR2,
      psFechaFin          VARCHAR2,
      psDirectorio        OUT  VARCHAR2
      )
  IS

    lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS             NUMBER := 5000 ;
    v_handle            UTL_FILE.FILE_TYPE;
    W_DESC              VARCHAR2(200);
    lsLinea             VARCHAR2(4000);
    lsNombreArchivo     VARCHAR2(50);
    lsCodigoPais        VARCHAR2(3);
    CURSOR c_interfaz IS
    select f.cod_sap SAP,
       g.cod_unid_nego UNIDAD_NEGOCIO,
       c.val_codi_vent CUV,
       j.val_i18n DESCRIPCION,
       i.cod_peri CAMPAnA,
       sum(b.num_unid_aten) TOTAL_ATENDIDO,
       sum(b.num_unid_aten * b.val_prec_cata_unit_loca) TOTAL_CATALOGO,
       sum(nvl(b.imp_desc_tota_loca, 0)) TOTAL_DESCUENTO,
       sum(decode(b.val_prec_cata_unit_loca,
                  0,
                  0,
                  b.val_prec_fact_tota_loca)) TOTAL_FACTURA
    from fac_docum_conta_cabec a,
         fac_docum_conta_linea b,
         ped_solic_posic       c,
         mae_clien_direc       d,
         mae_clien             e,
         mae_produ             f,
         mae_unida_negoc       g,
         cra_perio             h,
         seg_perio_corpo       i,
         gen_i18n_sicc_pais    j
   where trunc(a.fec_fact) >= to_date(psFechaInicio,'dd/mm/yyyy')
     and trunc(a.fec_fact) <= to_date(psFechaFin,'dd/mm/yyyy')
     and a.tido_oid_tipo_docu = 1
     and a.oid_cabe = b.dcca_oid_cabe
     and b.sopo_oid_soli_posi = c.oid_soli_posi
     and a.cldi_oid_clie_dire = d.oid_clie_dire
     and d.clie_oid_clie = e.oid_clie
     and b.prod_oid_prod = f.oid_prod
     and f.oid_prod = j.val_oid
     and j.attr_enti = 'MAE_PRODU'
     and f.uneg_oid_unid_nego = g.oid_unid_nego
     and a.perd_oid_peri = h.oid_peri
     and h.peri_oid_peri = i.oid_peri
     and a.pais_oid_pais = gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais)
   group by f.cod_sap,
            g.cod_unid_nego,
            c.val_codi_vent,
            j.val_i18n,
            i.cod_peri;

  TYPE interfazTipo IS RECORD (
       SAP          mae_produ.cod_sap%type,
       UNIDAD_NEGOCIO  mae_unida_negoc.cod_unid_nego%type,
       CUV             ped_solic_posic.val_codi_vent%type,
       DESCRIPCION     gen_i18n_sicc_pais.val_i18n%type,
       CAMPANA         seg_perio_corpo.cod_peri%type,
       TOTAL_ATENDIDO  fac_docum_conta_linea.num_unid_aten%type,
       TOTAL_CATALOGO  fac_docum_conta_linea.val_prec_cata_unit_loca%type,
       TOTAL_DESCUENTO fac_docum_conta_linea.imp_desc_tota_loca%type,
       TOTAL_FACTURA   fac_docum_conta_linea.val_prec_cata_unit_loca%type
  );

     TYPE interfazTab  IS TABLE OF interfazTipo ;
     interfazRecord interfazTab;
     lbAbrirUtlFile  BOOLEAN;
  BEGIN
    lbAbrirUtlFile := TRUE;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_interfaz;
    LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
        psDirectorio := lsDirTempo;
        lbAbrirUtlFile := FALSE;
     END IF ;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :='=T("'||interfazRecord(x).SAP||'")' ||','|| 
                      '=T("'||interfazRecord(x).UNIDAD_NEGOCIO||'")' ||','||
                      '=T("'||interfazRecord(x).CUV||'")' ||','||
                      REPLACE(interfazRecord(x).DESCRIPCION,',','-') ||','||
                      interfazRecord(x).CAMPANA ||','||
                      '=T("'||interfazRecord(x).TOTAL_ATENDIDO||'")' ||','||
                      '=T("'||interfazRecord(x).TOTAL_CATALOGO||'")' ||','||                      
                      '=T("'||interfazRecord(x).TOTAL_DESCUENTO||'")' ||','||
                      '=T("'||interfazRecord(x).TOTAL_FACTURA||'")';
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
   END LOOP;
   CLOSE c_interfaz;
   IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);
   END IF;
   RETURN;
  EXCEPTION
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_DETAU_CSV: '||ls_sqlerrm);
  END RUV_PR_GENER_REPOR_DETAU_CSV; 
  
/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de
                      Detalle de Nota de Debito por Marca y UN (Bolivia)
  Fecha Creacion    : 14/08/2015
  Autor             : Karina Valencia
  Parametros        :
              psCodigoPais : Codigo Pais
              psNombreArchivo : Nombre del Archivo
              psFechaInicio: Fecha Inicio
              psFechaFin: Fecha Fin
              psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
  PROCEDURE RUV_PR_GENER_REPOR_DNDMU_CSV(
      psCodigoPais        VARCHAR2,
      psNombreArchivo     VARCHAR2,
      psTitulo            VARCHAR2,
      psFechaInicio       VARCHAR2,
      psFechaFin          VARCHAR2,
      psDirectorio        OUT  VARCHAR2
      )
  IS
    lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS             NUMBER := 5000 ;
    v_handle            UTL_FILE.FILE_TYPE;
    W_DESC              VARCHAR2(200);
    lsLinea             VARCHAR2(4000);
    lsNombreArchivo     VARCHAR2(50);
    lsCodigoPais        VARCHAR2(3);
    CURSOR c_interfaz IS
    select f.cod_sap SAP,
           j.val_i18n DESCRIPCION,
           k.des_marc_prod MARCA,
           g.cod_unid_nego UNIDAD_NEGOCIO,
           m.cod_tipo_ofer TIPO_OFERTA,
           b.num_unid_aten UNIDADES,
           (b.VAL_PREC_SIN_IMPU_TOTA_LOCA - b.imp_desc_sin_impu_tota_loca) VENTA_NETA,
           b.IMP_IMPU_TOTA_LOCA IVA,
           decode(b.val_prec_cata_unit_loca, 0, 0, b.val_prec_fact_tota_loca) TOTAL_PRODUCTO,
           i.cod_peri CAMPAÑA_PROCESO,
           a.fec_fact FECHA_PROCESO,
           n.camp_refe CAMPAÑA_REFERENCIA,
           n.fec_emis_refe FECHA_REFERENCIA
      from fac_docum_conta_cabec a,
           fac_docum_conta_linea b,
           ped_solic_posic       c,
           mae_clien_direc       d,
           mae_clien             e,
           mae_produ             f,
           mae_unida_negoc       g,
           cra_perio             h,
           seg_perio_corpo       i,
           gen_i18n_sicc_pais    j,
           seg_marca_produ       k,
           pre_ofert_detal       l,
           pre_tipo_ofert        m,
           fac_regis_unico_venta n,
           ped_solic_cabec       n1
     where trunc(a.fec_fact) >= to_date(psFechaInicio,'dd/mm/yyyy')
       and trunc(a.fec_fact) <= to_date(psFechaFin, 'dd/mm/yyyy')
       and a.tido_oid_tipo_docu = 34
       and a.oid_cabe = b.dcca_oid_cabe
       and b.sopo_oid_soli_posi = c.oid_soli_posi
       and a.cldi_oid_clie_dire = d.oid_clie_dire
       and d.clie_oid_clie = e.oid_clie
       and b.prod_oid_prod = f.oid_prod
       and f.oid_prod = j.val_oid
       and j.attr_enti = 'MAE_PRODU'
       and f.uneg_oid_unid_nego = g.oid_unid_nego
       and f.mapr_oid_marc_prod = k.oid_marc_prod
       and a.perd_oid_peri = h.oid_peri
       and h.peri_oid_peri = i.oid_peri
       and a.oid_cabe = n.dcca_oid_cabe
       and c.ofde_oid_deta_ofer = l.oid_deta_ofer(+)
       and l.tofe_oid_tipo_ofer = m.oid_tipo_ofer(+)
       and c.soca_oid_soli_cabe = n1.oid_soli_cabe
       and a.pais_oid_pais = gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais)
     order by f.cod_sap,
              j.val_i18n,
              k.des_marc_prod,
              g.cod_unid_nego,
              m.cod_tipo_ofer,
              c.val_codi_vent,
              i.cod_peri,
              j.val_i18n;
              
  TYPE interfazTipo IS RECORD (
       sap          mae_produ.cod_sap%type,
       descripcion  gen_i18n_sicc_pais.val_i18n%type,      
       marca        seg_marca_produ.des_marc_prod%type,
       UNIDAD_NEGOCIO  mae_unida_negoc.cod_unid_nego%type,
       TIPO_OFERTA     pre_tipo_ofert.cod_tipo_ofer%type,
       UNIDADES        fac_docum_conta_linea.num_unid_aten%type,
       VENTA_NETA      fac_docum_conta_linea.VAL_PREC_SIN_IMPU_TOTA_LOCA%type,
       IVA             fac_docum_conta_linea.IMP_IMPU_TOTA_LOCA%type,
       TOTAL_PRODUCTO  fac_docum_conta_linea.val_prec_cata_unit_loca%type,
       CAMPANA_PROCESO seg_perio_corpo.cod_peri%type,
       FECHA_PROCESO   fac_docum_conta_cabec.fec_fact%type,
       CAMPANA_REFERENCIA     fac_regis_unico_venta.camp_refe%type,
       FECHA_REFERENCIA       fac_regis_unico_venta.fec_emis_refe%type  
  );

     TYPE interfazTab  IS TABLE OF interfazTipo ;
     interfazRecord interfazTab;
     lbAbrirUtlFile  BOOLEAN;
  BEGIN
    lbAbrirUtlFile := TRUE;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_interfaz;
    LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
        psDirectorio := lsDirTempo;
        lbAbrirUtlFile := FALSE;
     END IF ;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea := '=T("'||interfazRecord(x).sap||'")' ||','||
                      REPLACE(interfazRecord(x).descripcion,',','-') ||','||                      
                      interfazRecord(x).marca ||','||
                      '=T("'||interfazRecord(x).UNIDAD_NEGOCIO||'")' ||','||
                      '=T("'||interfazRecord(x).TIPO_OFERTA||'")' ||','||
                      '=T("'||interfazRecord(x).UNIDADES||'")' ||','||
                      '=T("'||interfazRecord(x).VENTA_NETA||'")' ||','||
                      '=T("'||interfazRecord(x).IVA||'")' ||','||
                      '=T("'||interfazRecord(x).TOTAL_PRODUCTO||'")' ||','||
                      interfazRecord(x).CAMPANA_PROCESO ||','||
                      TO_CHAR(interfazRecord(x).FECHA_PROCESO,'dd/mm/yyyy')||','||
                      interfazRecord(x).CAMPANA_REFERENCIA ||','||
                      TO_CHAR(interfazRecord(x).FECHA_REFERENCIA,'dd/mm/yyyy');

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
   END LOOP;
   CLOSE c_interfaz;
   IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);
   END IF;
   RETURN;
  EXCEPTION
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,1000);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR RUV_PR_GENER_REPOR_DNDMU_CSV: '||ls_sqlerrm);
  END RUV_PR_GENER_REPOR_DNDMU_CSV;
  
End Ruv_Pkg_Proce;
/
