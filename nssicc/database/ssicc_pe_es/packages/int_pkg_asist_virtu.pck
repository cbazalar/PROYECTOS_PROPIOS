CREATE OR REPLACE PACKAGE INT_PKG_ASIST_VIRTU IS

  /* Declaracion de Variables */
  LN_SQLCODE NUMBER(10);
  LS_SQLERRM VARCHAR2(150);
  W_FILAS             NUMBER := 5000 ;

  PROCEDURE AVI_PR_CARGA_FACTU(ps_Cod_Peri       IN VARCHAR2,
                               ps_Cod_Peri_Cruce IN VARCHAR2,
                               ps_Cod_Pais       IN VARCHAR2,
                               ps_Cod_Marca      IN VARCHAR2,
                               ps_Cod_Canal      IN VARCHAR2);

  PROCEDURE INT_PR_AVI_ENVIO_FACTU_CABEC(ps_Cod_Peri       IN VARCHAR2,
                                     ps_Cod_Peri_Cruce IN VARCHAR2,
                                     ps_Cod_Pais       IN VARCHAR2,
                                     ps_Cod_Marca      IN VARCHAR2,
                                     ps_Cod_Canal      IN VARCHAR2);

  PROCEDURE INT_PR_AVI_ENVIO_FACTU_DETAL(ps_Cod_Peri       IN VARCHAR2,
                                     ps_Cod_Peri_Cruce IN VARCHAR2,
                                     ps_Cod_Pais       IN VARCHAR2,
                                     ps_Cod_Marca      IN VARCHAR2,
                                     ps_Cod_Canal      IN VARCHAR2);

  PROCEDURE INT_PR_AVI_ENVIO_CONSU_PUNTA (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      psCodigoInterfaz        VARCHAR2,
                                      psNombreArchivo         VARCHAR2,
                                      psCodigoPeriodo         VARCHAR2,
                                      psCodigoPeriodoCruce    VARCHAR2,
                                      psEnviarNovedades       VARCHAR2);

  PROCEDURE INT_PR_AVI_ENVIO_CONSU_DECDR (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      psCodigoInterfaz        VARCHAR2,
                                      psNombreArchivo         VARCHAR2,
                                      psCodigoPeriodo         VARCHAR2,
                                      psCodigoPeriodoCruce    VARCHAR2);

  FUNCTION AVI_FN_VALID_REGIO_CERRA(pnOidPeriodo        NUMBER,
                                    pnOidRegion         NUMBER)
    RETURN NUMBER;

  PROCEDURE INT_PR_AVI_ENVIO_SALDO_CONSU (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      psCodigoInterfaz        VARCHAR2,
                                      psNombreArchivo         VARCHAR2,
                                      psCodigoPeriodo         VARCHAR2,
                                      psCodigoPeriodoCruce    VARCHAR2,
                                      psEnviarNovedades       VARCHAR2);

  /***************************************************************************
  Descripcion       : Genera Interfase Consultoras Bloqueadas
  Fecha Creacion    : 16/08/2010
  Autor             : Carlos Diaz Valverde
  Parametros        :
              psCodigoPais     : Codigo de Pais
              psCodigoSistema  : Codigo de Sistema
              psCodigoInterfaz : Codigo de Interfaz
              psNombreArchivo  : Nombre de Archivo a generarse en el servidor
  ***************************************************************************/
  PROCEDURE INT_PR_AVI_ENVIO_CONSU_BLOQU (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      psCodigoInterfaz        VARCHAR2,
                                      psNombreArchivo         VARCHAR2);

  /***************************************************************************
  Descripcion       : Genera Interfase Tipos de Bloqueos
  Fecha Creacion    : 16/08/2010
  Autor             : Carlos Diaz Valverde
  Parametros        :
              psCodigoPais     : Codigo de Pais
              psCodigoSistema  : Codigo de Sistema
              psCodigoInterfaz : Codigo de Interfaz
              psNombreArchivo  : Nombre de Archivo a generarse en el servidor
  ***************************************************************************/
  PROCEDURE INT_PR_AVI_ENVIO_TIPOS_BLOQU (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      Pscodigointerfaz        Varchar2,
                                      psNombreArchivo         VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Secciones (AVI -12)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_SECCI
  (psCodigoPais           VARCHAR2,
   psCodigoMarca          VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoIso            VARCHAR2,
   psCodigoPeriodo  VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Metas / Logros  (AVI-13)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_LOGRO_METAS
  (psCodigoPais           VARCHAR2,
   psCodigoMarca           VARCHAR2,
   psCodigoCanal           VARCHAR2,
   psCodigoSistema           VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo           VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo           VARCHAR2);


/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Venta, Ganancia y Asistencia (AVI-14)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_VENTA_GANAN
  (psCodigoPais           VARCHAR2,
   psCodigoMarca           VARCHAR2,
   psCodigoCanal           VARCHAR2,
   psCodigoSistema           VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo           VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo           VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Venta por Marca (AVI-15)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_VENTA_MARCA
  (psCodigoPais           VARCHAR2,
   psCodigoMarca           VARCHAR2,
   psCodigoCanal           VARCHAR2,
   psCodigoSistema           VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo           VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo           VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Indicadores GGZZ(AVI-16)
Fecha Creacion    : 25/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_INDIC_GGZZ
  (psCodigoPais           VARCHAR2,
   psCodigoMarca           VARCHAR2,
   psCodigoCanal           VARCHAR2,
   psCodigoSistema           VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo           VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo           VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Informacion Adicional(AVI-17)
Fecha Creacion    : 25/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_INFOR_ADICI
  (psCodigoPais           VARCHAR2,
   psCodigoMarca           VARCHAR2,
   psCodigoCanal           VARCHAR2,
   psCodigoSistema           VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo           VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo           VARCHAR2);

/***************************************************************************
Descripcion       : Proceso que encraga d evalidar la informacion por tipod e recepcion
                    tipoRecepcion 0: AVI-18 1:AVI:19
Fecha Creacion    : 25/02/2011
Autor             :  Sergio Buchelli
Parametros:
   psCodigoCliente          Codigo Cliente
   psCodigoTipoLogro        Codigo Tipo Logro
   psTelefono01             Telefono Fijo
   psTelefono02             Telefono Movil
   psTelefono03             Telefono trabajo
   psMail                   Mail
   psTipoRecepcion          Tipo Recepcion,
   psCampanhaInicio         Campanha inicio,
   psCampanhaFin            Campanha Fin,
   psCodigoError            Codigo error
***************************************************************************/
PROCEDURE INT_PR_AVI_VALID_INFO_RECEP
  (psCodigoPais             VARCHAR2,
   psCodigoCliente          VARCHAR2,
   psCodigoTipoLogro        VARCHAR2,
   psTelefono01             VARCHAR2,
   psTelefono02             VARCHAR2,
   psTelefono03             VARCHAR2,
   psMail                   VARCHAR2,
   psFechaActMovil          VARCHAR2,
   psTipoRecepcion          VARCHAR2,
   psCampanhaInicio         OUT VARCHAR2,
   psCampanhaFin            OUT VARCHAR2,
   psCodigoError            OUT VARCHAR2);

/***********************************************************************
Descripcion       : Genera Interfaz de Envio Tipos Logro  (AVI-20)
Fecha Creacion    : 25/07/2011
Autor             :
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_TIPOS_LOGRO
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2);
/************************************************************************
Descripcion       : Genera Interfaz de Envio Territorio  (AVI-8)
Fecha Creacion    : 08/06/2013
Autor             : Juan Gutiérrez
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Archivo

*************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_TERRI
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2);
   
/**************************************************************************
Descripcion       : Envia Prefacturacion Cabecera
Fecha Creacion    : 24/06/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoNuevo  :  Codigo de periodo Nuevo
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_PREFA_CABEC
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoPeriodoNuevo   VARCHAR2);


/**************************************************************************
Descripcion       : Envia Prefacturacion Detalle
Fecha Creacion    : 24/06/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoNuevo  :  Codigo de periodo Nuevo
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_PREFA_DETAL
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoPeriodoNuevo   VARCHAR2);
   
/**************************************************************************
Descripcion       : Envia Consultora CDR Cabecera
Fecha Creacion    : 24/06/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_CLCDR_CABEC
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoPeriodo        VARCHAR2);
   
END INT_PKG_ASIST_VIRTU;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_ASIST_VIRTU IS

PROCEDURE AVI_PR_CARGA_FACTU(ps_Cod_Peri       IN VARCHAR2,
                             ps_Cod_Peri_Cruce IN VARCHAR2,
                             ps_Cod_Pais       IN VARCHAR2,
                             ps_Cod_Marca      IN VARCHAR2,
                             ps_Cod_Canal      IN VARCHAR2) IS
BEGIN
  -- Ejecutamos el detalle ya que el numero de rechazados
  -- enviado por la cabecera lo ha de tomar de esta tabla
  INT_PR_AVI_ENVIO_FACTU_DETAL(ps_Cod_Peri,
                           ps_Cod_Peri_Cruce,
                           ps_Cod_Pais,
                           ps_Cod_Marca,
                           ps_Cod_Canal);

  -- Ejecutamos el calculo de estadisticos de la cabecera
  INT_PR_AVI_ENVIO_FACTU_CABEC(ps_Cod_Peri,
                           ps_Cod_Peri_Cruce,
                           ps_Cod_Pais,
                           ps_Cod_Marca,
                           ps_Cod_Canal);
END AVI_PR_CARGA_FACTU;


/***************************************************************************************************************
Descripcion       : Carga la informacion de perido seleccionado y perido de cruce en la tabla
                    temporal INT_AVI_FACTU_CABEC_TEMPO que servira para la obtencion de la informacion a enviar
                    de Facturacion - Cabecera para la interfaz. (AVI-3)
Fecha Creacion    : 30/07/2008
Parametros Entrada:
  ps_cod_peri     :  Codigo de periodo
  ps_cod_peri_cruce   :  Codigo de periodo de cruce
  ps_cod_pais     :  Codigo de pais
  ps_cod_marca    :  Codigo de marca
  ps_cod_canal    :  Codigo de canal

Autor             : Sergio Apaza

****************************************************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_FACTU_CABEC(ps_Cod_Peri       IN VARCHAR2,
                                   ps_Cod_Peri_Cruce IN VARCHAR2,
                                   ps_Cod_Pais       IN VARCHAR2,
                                   ps_Cod_Marca      IN VARCHAR2,
                                   ps_Cod_Canal      IN VARCHAR2)
IS
  CURSOR C_PEDI_RECH IS
    SELECT INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS,
           INT_AVI_FACTU_DETAL_TEMPO.COD_PERI,
           INT_AVI_FACTU_DETAL_TEMPO.COD_REGI,
           INT_AVI_FACTU_DETAL_TEMPO.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA,
           COUNT(1) NUM_PEDI_RECH
      FROM INT_AVI_FACTU_DETAL_TEMPO, ZON_REGIO, ZON_ZONA
     WHERE ((INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS = ps_Cod_Pais) AND
           (INT_AVI_FACTU_DETAL_TEMPO.COD_REGI = ZON_REGIO.COD_REGI) AND
           (INT_AVI_FACTU_DETAL_TEMPO.COD_ZONA = ZON_ZONA.COD_ZONA) AND
           (INT_AVI_FACTU_DETAL_TEMPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           INT_AVI_FACTU_DETAL_TEMPO.COD_PERI = ps_Cod_Peri_Cruce)))
     GROUP BY INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS,
              INT_AVI_FACTU_DETAL_TEMPO.COD_REGI,
              INT_AVI_FACTU_DETAL_TEMPO.COD_ZONA,
              INT_AVI_FACTU_DETAL_TEMPO.COD_PERI,
              ZON_REGIO.OID_REGI,
              ZON_ZONA.OID_ZONA;

  R_PEDI_RECH C_PEDI_RECH%ROWTYPE;

  CURSOR C_ESTI IS
    SELECT SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           ZON_REGIO.COD_REGI,
           ZON_ZONA.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA,
           INT_FUENT_VENTA_PREVI_SAP.NUM_PEDI AS NUM_PEDI_ESTI,
           INT_FUENT_VENTA_PREVI_SAP.NUM_ACTI_FINA AS NUM_ESTI_ACTI_FINA
      FROM SEG_PAIS,
           SEG_PERIO_CORPO,
           SEG_MARCA,
           SEG_CANAL,
           ZON_ZONA,
           ZON_REGIO,
           CRA_PERIO,
           INT_FUENT_VENTA_PREVI_SAP
     WHERE ((SEG_PAIS.OID_PAIS = ZON_ZONA.PAIS_OID_PAIS) AND
           (ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI) AND
           (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS) AND
           (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
           (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
           (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
           (ZON_ZONA.OID_ZONA = INT_FUENT_VENTA_PREVI_SAP.ZZON_OID_ZONA) AND
           (ZON_REGIO.OID_REGI = INT_FUENT_VENTA_PREVI_SAP.ZORG_OID_REGI) AND
           (CRA_PERIO.OID_PERI = INT_FUENT_VENTA_PREVI_SAP.PERD_OID_PERI) AND
           (SEG_PAIS.COD_PAIS = ps_Cod_Pais) AND
           (SEG_MARCA.COD_MARC = ps_Cod_Marca) AND
           (SEG_CANAL.COD_CANA = ps_Cod_Canal) AND
           (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce)));

  CURSOR C_ACTI_FINA IS
    SELECT SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           ZON_REGIO.COD_REGI,
           ZON_ZONA.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA,
           SUM(INT_FUENT_VENTAS_REAL.NUM_ACTI_FINA) AS NUM_ACTI_FINA
      FROM SEG_PAIS,
           SEG_PERIO_CORPO,
           SEG_MARCA,
           SEG_CANAL,
           ZON_ZONA,
           ZON_REGIO,
           CRA_PERIO,
           INT_FUENT_VENTAS_REAL
     WHERE ((SEG_PAIS.OID_PAIS = ZON_ZONA.PAIS_OID_PAIS) AND
           (ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI) AND
           (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS) AND
           (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
           (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
           (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
           (ZON_ZONA.OID_ZONA = INT_FUENT_VENTAS_REAL.ZZON_OID_ZONA) AND
           (ZON_REGIO.OID_REGI = INT_FUENT_VENTAS_REAL.ZORG_OID_REGI) AND
           (CRA_PERIO.OID_PERI = INT_FUENT_VENTAS_REAL.PERD_OID_PERI) AND
           (SEG_PAIS.COD_PAIS = ps_Cod_Pais) AND
           (SEG_MARCA.COD_MARC = ps_Cod_Marca) AND
           (SEG_CANAL.COD_CANA = ps_Cod_Canal) AND
           (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce)))
     GROUP BY SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           ZON_REGIO.COD_REGI,
           ZON_ZONA.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA;

  CURSOR C_MONTO_FACTU IS
    SELECT  SEG_PAIS.COD_PAIS,
            SEG_PERIO_CORPO.COD_PERI,
            ZON_REGIO.COD_REGI,
            ZON_ZONA.COD_ZONA,
            ZON_REGIO.OID_REGI,
            ZON_ZONA.OID_ZONA,
            SUM(PED_SOLIC_CABEC.VAL_PREC_NETO_TOTA_LOCA) - SUM(PED_SOLIC_CABEC.VAL_IMPO_FLET_SIN_IMPU_TOTA) VAL_MONT_FACT
    FROM CRA_PERIO,
         PED_SOLIC_CABEC,
         PED_TIPO_SOLIC_PAIS,
         SEG_PERIO_CORPO,
         SEG_PAIS,
         SEG_MARCA,
         SEG_CANAL,
         PED_TIPO_SOLIC,
         ZON_ZONA,
         ZON_REGIO
    WHERE (    (CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI)
      AND (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS = PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS)
      AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
      AND (SEG_PAIS.OID_PAIS = PED_SOLIC_CABEC.PAIS_OID_PAIS)
      AND (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
      AND (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
      AND (PED_TIPO_SOLIC.OID_TIPO_SOLI = PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI)
      AND (PED_TIPO_SOLIC.COD_TIPO_SOLI IN (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS))
      AND ((PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 1) OR (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 5))
      AND (PED_SOLIC_CABEC.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA)
      AND (ZON_ZONA.ZORG_OID_REGI = ZON_REGIO.OID_REGI)
      AND (PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC = 5)
      AND (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 0)
      AND (PED_SOLIC_CABEC.FEC_FACT IS NOT NULL)
      AND (SEG_PAIS.COD_PAIS = ps_Cod_Pais)
      AND (SEG_MARCA.COD_MARC = ps_Cod_Marca)
      AND (SEG_CANAL.COD_CANA = ps_Cod_Canal)
      AND (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce)))
     GROUP BY SEG_PAIS.COD_PAIS,
              ZON_REGIO.COD_REGI,
              ZON_ZONA.COD_ZONA,
              SEG_PERIO_CORPO.COD_PERI,
              ZON_REGIO.OID_REGI,
              ZON_ZONA.OID_ZONA;

  R_ESTI           C_ESTI%ROWTYPE;
  R_ACTI_FINA      C_ACTI_FINA%ROWTYPE;
  R_MONTO_FACTU    C_MONTO_FACTU%ROWTYPE;

  EXISTE NUMBER := 0;
  l_user    VARCHAR2(20);
BEGIN

  -- PRIMERO LIMPIAR TODA LA TABLA TEMPORAL
  EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_AVI_FACTU_CABEC_TEMPO';
  --DELETE FROM INT_AVI_FACTU_CABEC_TEMPO;

  -- INSERTAMOS LAS ZONAS CON LOS VALORES DE LOS PEDIDOS FACTURADOS, Y LOS PRIMEROS PEDIDOS
  INSERT INTO INT_AVI_FACTU_CABEC_TEMPO
    (COD_PAIS,
     COD_PERI,
     COD_REGI,
     COD_ZONA,
     OID_REGI,
     OID_ZONA,
     NUM_PEDI_FACT,
     NUM_PRIM_PEDI,
     VAL_MONT_FACT)
    SELECT SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           ZON_REGIO.COD_REGI,
           ZON_ZONA.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA,
           COUNT(DISTINCT PED_SOLIC_CABEC.CLIE_OID_CLIE) AS NROPEDIDOS,
           SUM(CASE WHEN ((MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 1)
                           OR (MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 2 AND (FC2.TOTAL > 0))
                            OR (MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 8 AND (FC2.TOTAL > 0))
                           OR (MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 7 AND (MAE_CLIEN_DATOS_ADICI.IND_ACTI = 1))
                              ) THEN 1
                       ELSE 0 END) AS NUM_PRIM_PEDI,
           0 AS VAL_MONT_FACT
      FROM CRA_PERIO,
           PED_SOLIC_CABEC,
           PED_TIPO_SOLIC_PAIS,
           SEG_PERIO_CORPO,
           SEG_PAIS,
           SEG_MARCA,
           SEG_CANAL,
           PED_TIPO_SOLIC,
           ZON_ZONA,
           ZON_REGIO,
                 MAE_CLIEN,
                 MAE_CLIEN_DATOS_ADICI,
           (SELECT CC.OID_PERI, RR.OID_REGI, INT_PKG_ASIST_VIRTU.AVI_FN_VALID_REGIO_CERRA(CC.OID_PERI,RR.OID_REGI) TOTAL
              FROM CRA_PERIO CC, ZON_REGIO RR, SEG_PERIO_CORPO SS
             WHERE CC.PERI_OID_PERI = SS.OID_PERI
               AND (SS.COD_PERI = ps_Cod_Peri OR
                    (ps_Cod_Peri_Cruce IS NOT NULL AND SS.COD_PERI = ps_Cod_Peri_Cruce))) FC2
     WHERE ((CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI) AND
           (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS =
           PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS) AND
             (MAE_CLIEN.OID_CLIE = PED_SOLIC_CABEC.CLIE_OID_CLIE) AND
             (MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE) AND
           (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
           (SEG_PAIS.OID_PAIS = PED_SOLIC_CABEC.PAIS_OID_PAIS) AND
           (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
           (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
           (PED_TIPO_SOLIC.OID_TIPO_SOLI =
           PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI) AND
           (PED_TIPO_SOLIC.COD_TIPO_SOLI IN
           (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS)) AND
           ((PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 1) OR
           (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 5)) AND
           (PED_SOLIC_CABEC.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA) AND
           (ZON_ZONA.ZORG_OID_REGI = ZON_REGIO.OID_REGI) AND
           (PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC = 5) AND
           (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1) AND
           (PED_SOLIC_CABEC.IND_OC = 1) AND
           (PED_SOLIC_CABEC.FEC_FACT IS NOT NULL) AND
           (FC2.OID_REGI = ZON_REGIO.OID_REGI) AND
           (FC2.OID_PERI = CRA_PERIO.OID_PERI) AND
           (SEG_PAIS.COD_PAIS = ps_Cod_Pais) AND
           (SEG_MARCA.COD_MARC = ps_Cod_Marca) AND
           (SEG_CANAL.COD_CANA = ps_Cod_Canal) AND
           (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce)))
     GROUP BY SEG_PAIS.COD_PAIS,
              ZON_REGIO.COD_REGI,
              ZON_ZONA.COD_ZONA,
              SEG_PERIO_CORPO.COD_PERI,
              ZON_REGIO.OID_REGI,
              ZON_ZONA.OID_ZONA;

  l_user := USER;
  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'INT_AVI_FACTU_CABEC_TEMPO', CASCADE => TRUE );

  -- ACTUALIZAMOS LAS CANTIDADES DE PEDIDOS RECHAZADOS EN CASO SEA NECESARIO
  OPEN C_PEDI_RECH;
  LOOP
    FETCH C_PEDI_RECH
      INTO R_PEDI_RECH;
    EXIT WHEN C_PEDI_RECH%NOTFOUND;
    BEGIN
      SELECT COUNT(1)
        INTO EXISTE
        FROM INT_AVI_FACTU_CABEC_TEMPO
       WHERE COD_PAIS = R_PEDI_RECH.COD_PAIS
         AND COD_PERI = R_PEDI_RECH.COD_PERI
         AND COD_REGI = R_PEDI_RECH.COD_REGI
         AND COD_ZONA = R_PEDI_RECH.COD_ZONA;

      IF (EXISTE = 1) THEN
        UPDATE INT_AVI_FACTU_CABEC_TEMPO
           SET NUM_PEDI_RECH = R_PEDI_RECH.NUM_PEDI_RECH
         WHERE COD_PAIS = R_PEDI_RECH.COD_PAIS
           AND COD_PERI = R_PEDI_RECH.COD_PERI
           AND COD_REGI = R_PEDI_RECH.COD_REGI
           AND COD_ZONA = R_PEDI_RECH.COD_ZONA;
      ELSE
        INSERT INTO INT_AVI_FACTU_CABEC_TEMPO
          (COD_PAIS,
           COD_PERI,
           COD_REGI,
           COD_ZONA,
           OID_REGI,
           OID_ZONA,
           NUM_PEDI_RECH)
        VALUES
          (R_PEDI_RECH.COD_PAIS,
           R_PEDI_RECH.COD_PERI,
           R_PEDI_RECH.COD_REGI,
           R_PEDI_RECH.COD_ZONA,
           R_PEDI_RECH.OID_REGI,
           R_PEDI_RECH.OID_ZONA,
           R_PEDI_RECH.NUM_PEDI_RECH);
      END IF;
    END;
  END LOOP;
  CLOSE C_PEDI_RECH;

  -- ACTUALIZAMOS LOS NUMERO DE ESTIMADOS DE ACTIVAS FINALES, NUMERO DE PEDIDOS ESTIMADOS
  OPEN C_ESTI;
  LOOP
    FETCH C_ESTI
      INTO R_ESTI;
    EXIT WHEN C_ESTI%NOTFOUND;
    BEGIN
      SELECT COUNT(1)
        INTO EXISTE
        FROM INT_AVI_FACTU_CABEC_TEMPO
       WHERE COD_PAIS = R_ESTI.COD_PAIS
         AND COD_PERI = R_ESTI.COD_PERI
         AND COD_REGI = R_ESTI.COD_REGI
         AND COD_ZONA = R_ESTI.COD_ZONA;

      IF (EXISTE = 1) THEN
        UPDATE INT_AVI_FACTU_CABEC_TEMPO
           SET NUM_PEDI_ESTI = R_ESTI.NUM_PEDI_ESTI,
               NUM_ESTI_ACTI_FINA = R_ESTI.NUM_ESTI_ACTI_FINA
         WHERE COD_PAIS = R_ESTI.COD_PAIS
           AND COD_PERI = R_ESTI.COD_PERI
           AND COD_REGI = R_ESTI.COD_REGI
           AND COD_ZONA = R_ESTI.COD_ZONA;
      END IF;
    END;
  END LOOP;
  CLOSE C_ESTI;

  -- ACTUALIZAMOS LOS NUMERO DE ACTIVAS FINALES
  OPEN C_ACTI_FINA;
  LOOP
    FETCH C_ACTI_FINA
      INTO R_ACTI_FINA;
    EXIT WHEN C_ACTI_FINA%NOTFOUND;
    BEGIN
      SELECT COUNT(1)
        INTO EXISTE
        FROM INT_AVI_FACTU_CABEC_TEMPO
       WHERE COD_PAIS = R_ACTI_FINA.COD_PAIS
         AND COD_PERI = R_ACTI_FINA.COD_PERI
         AND COD_REGI = R_ACTI_FINA.COD_REGI
         AND COD_ZONA = R_ACTI_FINA.COD_ZONA;

      IF (EXISTE = 1) THEN
        UPDATE INT_AVI_FACTU_CABEC_TEMPO
           SET NUM_ACTI_FINA      = R_ACTI_FINA.NUM_ACTI_FINA
         WHERE COD_PAIS = R_ACTI_FINA.COD_PAIS
           AND COD_PERI = R_ACTI_FINA.COD_PERI
           AND COD_REGI = R_ACTI_FINA.COD_REGI
           AND COD_ZONA = R_ACTI_FINA.COD_ZONA;
      END IF;
    END;
  END LOOP;
  CLOSE C_ACTI_FINA;

  -- ACTUALIZAMOS LOS MONTOS FACTURADOS
  OPEN C_MONTO_FACTU;
  LOOP
    FETCH C_MONTO_FACTU
      INTO R_MONTO_FACTU;
    EXIT WHEN C_MONTO_FACTU%NOTFOUND;
    BEGIN
      SELECT COUNT(1)
        INTO EXISTE
        FROM INT_AVI_FACTU_CABEC_TEMPO
       WHERE COD_PAIS = R_MONTO_FACTU.COD_PAIS
         AND COD_PERI = R_MONTO_FACTU.COD_PERI
         AND COD_REGI = R_MONTO_FACTU.COD_REGI
         AND COD_ZONA = R_MONTO_FACTU.COD_ZONA;

      IF (EXISTE = 1) THEN
        UPDATE INT_AVI_FACTU_CABEC_TEMPO
           SET VAL_MONT_FACT = R_MONTO_FACTU.VAL_MONT_FACT
         WHERE COD_PAIS = R_MONTO_FACTU.COD_PAIS
           AND COD_PERI = R_MONTO_FACTU.COD_PERI
           AND COD_REGI = R_MONTO_FACTU.COD_REGI
           AND COD_ZONA = R_MONTO_FACTU.COD_ZONA;
      END IF;
    END;
  END LOOP;
  CLOSE C_MONTO_FACTU;

  -- CALCULAMOS EL PORCENTAJE DE PEDIDOS FACTURADOS, LOS PEDIDOS TOTALES POR FACTURAR,
  -- PORCENTAJE DE ACTIVIDAD FINAL, MONTO PROMEDIO PEDIDOS FACTURADOS
  UPDATE INT_AVI_FACTU_CABEC_TEMPO
     SET VAL_PORC_PEDI_FACT = DECODE(NUM_PEDI_ESTI, 0, 0, ROUND(NUM_PEDI_FACT / NUM_PEDI_ESTI * 100)),
         NUM_PEDI_TOTA = NUM_PEDI_FACT + NUM_PEDI_RECH,
         VAL_PORC_ACTI_FINA = DECODE(NUM_ESTI_ACTI_FINA, 0, 0, ROUND(NUM_ACTI_FINA / NUM_ESTI_ACTI_FINA * 100)),
         VAL_PROM_PEDI_FACT = DECODE(NUM_ACTI_FINA, 0, 0, ROUND(VAL_MONT_FACT / NUM_ACTI_FINA, 2));

END INT_PR_AVI_ENVIO_FACTU_CABEC;

/***************************************************************************************************************
Descripcion       : Carga la informacion de perido seleccionado y perido de cruce en la tabla
                    temporal INT_AVI_FACTU_DETAL_TEMPO que servira para la obtencion de la informacion a enviar
                    de Facturacion - Detalle para la interfaz. (AVI-4)
Fecha Creacion    : 30/07/2008
Parametros Entrada:
  ps_cod_peri      :  Codigo de periodo
  ps_cod_peri_cruce   :  Codigo de periodo de cruce
  ps_cod_pais     :  Codigo de pais
  ps_cod_marca    :  Codigo de marca
  ps_cod_canal    :  Codigo de canal

Autor             : Sergio Apaza
***************************************************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_FACTU_DETAL(ps_Cod_Peri       IN VARCHAR2,
                                   ps_Cod_Peri_Cruce IN VARCHAR2,
                                   ps_Cod_Pais       IN VARCHAR2,
                                   ps_Cod_Marca      IN VARCHAR2,
                                   ps_Cod_Canal      IN VARCHAR2) IS

  CURSOR C_FACTURADOS IS
    SELECT INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS,
           INT_AVI_FACTU_DETAL_TEMPO.COD_PERI,
           INT_AVI_FACTU_DETAL_TEMPO.COD_CLIE
      FROM CRA_PERIO,
           PED_SOLIC_CABEC,
           PED_TIPO_SOLIC_PAIS,
           SEG_PERIO_CORPO,
           SEG_PAIS,
           SEG_MARCA,
           SEG_CANAL,
           PED_TIPO_SOLIC,
           INT_AVI_FACTU_DETAL_TEMPO
     WHERE ((CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI) AND
           (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS =
           PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS) AND
           (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
           (SEG_PAIS.OID_PAIS = PED_SOLIC_CABEC.PAIS_OID_PAIS) AND
           (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
           (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
           (PED_TIPO_SOLIC.OID_TIPO_SOLI =
           PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI) AND
           (PED_TIPO_SOLIC.COD_TIPO_SOLI IN
           (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS)) AND
           ((PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 1) OR
           (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 5)) AND
           (PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC = 5) AND
           (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1) AND
           (PED_SOLIC_CABEC.IND_OC = 1) AND
           (PED_SOLIC_CABEC.FEC_FACT IS NOT NULL) AND
           (SEG_PAIS.COD_PAIS = ps_Cod_Pais) AND
           (SEG_MARCA.COD_MARC = ps_Cod_Marca) AND
           (SEG_CANAL.COD_CANA = ps_Cod_Canal) AND
           (SEG_PAIS.COD_PAIS = INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS) AND
           (SEG_PERIO_CORPO.COD_PERI =
           INT_AVI_FACTU_DETAL_TEMPO.COD_PERI) AND
           (PED_SOLIC_CABEC.CLIE_OID_CLIE =
           INT_AVI_FACTU_DETAL_TEMPO.OID_CLIE));

  R_FACTURADOS C_FACTURADOS%ROWTYPE;

  FEC_MAX_FACT DATE;
  l_user  VARCHAR2(20);
BEGIN

  -- primero limpiar toda la tabla
  EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_AVI_FACTU_DETAL_TEMPO';

  -- Insertamos los detalles considerando tambien los activos
  -- para los casos en que un pedido fue inicialmente rechazado
  INSERT INTO INT_AVI_FACTU_DETAL_TEMPO
    (COD_PAIS,
     COD_PERI,
     OID_CLIE,
     COD_CLIE,
     COD_REGI,
     COD_ZONA,
     VAL_SALD,
     COD_MOTI_RECH,
     OID_ESTA_SOLI,
     FEC_PROG_FACT)
    SELECT SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           MAE_CLIEN.OID_CLIE,
           MAE_CLIEN.COD_CLIE,
           (SELECT r.COD_REGI FROM ZON_REGIO r, ZON_ZONA z
             WHERE r.OID_REGI = z.ZORG_OID_REGI
               AND z.OID_ZONA =  PED_SOLIC_CABEC.ZZON_OID_ZONA),
           (SELECT COD_ZONA FROM ZON_ZONA WHERE OID_ZONA = PED_SOLIC_CABEC.ZZON_OID_ZONA),
           (EVI_PKG_EJECU_VIRTU.EVI_FN_CALCU_VALOR_SALDO_DEUD2(MAE_CLIEN.COD_CLIE)) AS SALDO_CONSULTORA,
           (CASE
             WHEN ((MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 1 OR
                  MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 2) AND
                  PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 3) THEN
              ('M')
             ELSE
              CASE
             WHEN (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 3) THEN
              ('D')
             ELSE
              ('N')
           END END),
           PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI,
           PED_SOLIC_CABEC.FEC_PROG_FACT
      FROM PED_SOLIC_CABEC,
           PED_TIPO_SOLIC_PAIS,
           MAE_CLIEN,
           MAE_CLIEN_DATOS_ADICI,
           PED_TIPO_SOLIC,
           SEG_CANAL,
           SEG_MARCA,
           SEG_PAIS,
           SEG_PERIO_CORPO,
           CRA_PERIO
     WHERE (PED_SOLIC_CABEC.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
       AND (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS =
           PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS)
       AND (PED_TIPO_SOLIC.OID_TIPO_SOLI =
           PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI)
       AND (PED_TIPO_SOLIC.COD_TIPO_SOLI IN
           (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS))
       AND (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 2 OR
           PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 3)
       AND (MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
       AND (PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI)
       AND (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
       AND (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
       AND (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS)
       AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
       AND (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1)
       AND (MAE_CLIEN_DATOS_ADICI.IND_ACTI = 1) -- Se envian solamente las consultoras activas
       AND (SEG_PAIS.COD_PAIS = ps_Cod_Pais)
       AND (SEG_MARCA.COD_MARC = ps_Cod_Marca)
       AND (SEG_CANAL.COD_CANA = ps_Cod_Canal)
       AND (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce));

  -- Incluimos los registros rechazados por deuda de la tabla INT_SOLIC_CONSO_CABEC
  INSERT INTO INT_AVI_FACTU_DETAL_TEMPO
    (COD_PAIS,
     COD_PERI,
     COD_CLIE,
     COD_REGI,
     COD_ZONA,
     VAL_SALD,
     COD_MOTI_RECH,
     OID_ESTA_SOLI,
     FEC_PROG_FACT)
    SELECT A.COD_PAIS,
           A.COD_PERI,
           A.COD_CLIE,
           A.COD_REGI,
           A.COD_ZONA,
           A.VAL_SALD_DEUD,
               'D' AS RECHAZO,
           3, -- Usamos el OID de los rechazados por deuda
           A.FEC_SOLI
      FROM INT_SOLIC_CONSO_CABEC A
     WHERE (A.IND_ERRO_DEUD = '2')
          AND (A.IND_ERROR_SGPE = '0')
       AND (A.IND_ERRO_REMP = '0')
       AND (A.IND_ADMI_CART = '0')
       AND (A.IND_OCS_PROC = '0')
       AND (A.IND_CONT_ACT = '0') -- Se envian solo las activas
       AND (A.FEC_SOLI < SYSDATE)
       AND NOT EXISTS
     (SELECT B.COD_PERI
              FROM INT_AVI_FACTU_DETAL_TEMPO B
             WHERE B.COD_PAIS = A.COD_PAIS
               AND B.COD_CLIE = A.COD_CLIE
               AND B.COD_PERI = A.COD_PERI)
       AND (A.COD_PERI = ps_Cod_Peri OR (ps_Cod_Peri_Cruce IS NOT NULL AND
           A.COD_PERI = ps_Cod_Peri_Cruce));

  -- Eliminamos a las consultoras que tiene una solicitud con status valido y que esten en
  -- el periodo correspondiente
  l_user := USER;
  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'INT_AVI_FACTU_DETAL_TEMPO', CASCADE => TRUE );

  OPEN C_FACTURADOS;
  LOOP
    FETCH C_FACTURADOS
      INTO R_FACTURADOS;
    EXIT WHEN C_FACTURADOS%NOTFOUND;
    BEGIN
      DELETE FROM INT_AVI_FACTU_DETAL_TEMPO
       WHERE COD_PAIS = R_FACTURADOS.COD_PAIS
         AND COD_PERI = R_FACTURADOS.COD_PERI
         AND COD_CLIE = R_FACTURADOS.COD_CLIE;
    END;

  END LOOP;
  CLOSE C_FACTURADOS;

  -- Obtenemos la fecha maxima de facturacion
  SELECT MAX(FEC_PROG_FACT)
    INTO FEC_MAX_FACT
    FROM INT_AVI_FACTU_DETAL_TEMPO
   WHERE COD_PAIS = ps_Cod_Pais
     AND COD_PERI = ps_Cod_Peri;

  -- Eliminamos aquellos detalles que no corresponden a la fecha
  -- maxima de facturacion programada
  IF FEC_MAX_FACT IS NOT NULL THEN
    DELETE FROM INT_AVI_FACTU_DETAL_TEMPO
     WHERE FEC_PROG_FACT <> FEC_MAX_FACT
       AND COD_PAIS = ps_Cod_Pais
       AND COD_PERI = ps_Cod_Peri
       AND COD_MOTI_RECH <> 'D';
  END IF;

  -- Hacemos lo mismo en caso haya periodo de cruce
  IF ps_Cod_Peri_Cruce IS NOT NULL THEN
    FEC_MAX_FACT := NULL;
    -- Obtenemos la fecha maxima de facturacion
    SELECT MAX(FEC_PROG_FACT)
      INTO FEC_MAX_FACT
      FROM INT_AVI_FACTU_DETAL_TEMPO
     WHERE COD_PAIS = ps_Cod_Pais
       AND COD_PERI = ps_Cod_Peri_Cruce;

    -- Eliminamos aquellos detalles que no corresponden a la fecha
    -- maxima de facturacion programada
    IF FEC_MAX_FACT IS NOT NULL THEN
      DELETE FROM INT_AVI_FACTU_DETAL_TEMPO
       WHERE FEC_PROG_FACT <> FEC_MAX_FACT
         AND COD_PAIS = ps_Cod_Pais
         AND COD_PERI = ps_Cod_Peri_Cruce
         AND COD_MOTI_RECH <> 'D';
    END IF;
  END IF;

  -- Incluimos los registros rechazados por MontoMinimo y Monto Maximo
  INSERT INTO INT_AVI_FACTU_DETAL_TEMPO
    (COD_PAIS,
     COD_PERI,
     COD_CLIE,
     COD_REGI,
     COD_ZONA,
     VAL_SALD,
     COD_MOTI_RECH,
     OID_ESTA_SOLI,
     FEC_PROG_FACT)
    SELECT A.COD_PAIS,
           A.COD_PERI,
           A.COD_CLIE,
           A.COD_REGI,
           A.COD_ZONA,
           A.VAL_SALD_DEUD,
               (CASE WHEN (A.IND_ERRO_MTMA = '1') THEN 'M'
                     WHEN (A.IND_ERRO_MTMI = '1') THEN 'N'
                END) AS RECHAZO,
           3, -- Usamos el OID de los rechazados por deuda
           A.FEC_SOLI
      FROM INT_SOLIC_CONSO_CABEC A
     WHERE ((A.IND_ERRO_MTMI = '1') OR
                 (A.IND_ERRO_MTMA = '1'))
          AND (A.IND_ERROR_SGPE = '0')
       AND (A.IND_ERRO_REMP = '0')
       AND (A.IND_ADMI_CART = '0')
       AND (A.IND_OCS_PROC = '0')
       AND (A.IND_CONT_ACT = '0') -- Se envian solo las activas
       AND (A.FEC_SOLI < SYSDATE)
       AND NOT EXISTS
     (SELECT B.COD_PERI
              FROM INT_AVI_FACTU_DETAL_TEMPO B
             WHERE B.COD_PAIS = A.COD_PAIS
               AND B.COD_CLIE = A.COD_CLIE
               AND B.COD_PERI = A.COD_PERI)
       AND (A.COD_PERI = ps_Cod_Peri OR (ps_Cod_Peri_Cruce IS NOT NULL AND
           A.COD_PERI = ps_Cod_Peri_Cruce));

   -- Incluimos los registros rechazados por Otros Motivos
  INSERT INTO INT_AVI_FACTU_DETAL_TEMPO
    (COD_PAIS,
     COD_PERI,
     COD_CLIE,
     COD_REGI,
     COD_ZONA,
     VAL_SALD,
     COD_MOTI_RECH,
     OID_ESTA_SOLI,
     FEC_PROG_FACT)
    SELECT A.COD_PAIS,
           A.COD_PERI,
           A.COD_CLIE,
           A.COD_REGI,
           A.COD_ZONA,
           A.VAL_SALD_DEUD,
               'O' AS RECHAZO,
           3, -- Usamos el OID de los rechazados por deuda
           A.FEC_SOLI
      FROM INT_SOLIC_CONSO_CABEC A
     WHERE (A.IND_ERRO_DEUD <> '2')
       AND (A.IND_ERRO_MTMI <> '1')
       AND (A.IND_ERRO_MTMA <> '1')
       AND (A.IND_PROC_GP2 <> 1)
       AND (A.FEC_SOLI < SYSDATE)
       AND NOT EXISTS
     (SELECT B.COD_PERI
              FROM INT_AVI_FACTU_DETAL_TEMPO B
             WHERE B.COD_PAIS = A.COD_PAIS
               AND B.COD_CLIE = A.COD_CLIE
               AND B.COD_PERI = A.COD_PERI)
       AND (A.COD_PERI = ps_Cod_Peri OR (ps_Cod_Peri_Cruce IS NOT NULL AND
           A.COD_PERI = ps_Cod_Peri_Cruce));

END INT_PR_AVI_ENVIO_FACTU_DETAL;

/**************************************************************************
Descripcion       : Carga la informacion de los puntajes de consultoras
                    requeridos por la interface Asistente Virtual (AVI-7)
Fecha Creacion    : 05/08/2008
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoCruce  :  Codigo de periodo cruce
  psEnviarNovedades     : Enviar Novedades (S, N: Completo)

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_CONSU_PUNTA (psCodigoPais            VARCHAR2,
                                    psCodigoSistema         VARCHAR2,
                                    psCodigoInterfaz        VARCHAR2,
                                    psNombreArchivo         VARCHAR2,
                                    psCodigoPeriodo         VARCHAR2,
                                    psCodigoPeriodoCruce    VARCHAR2,
                                    psEnviarNovedades       VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT COD_PAIS,
           COD_CLIE,
           COD_PERI_INIC,
           COD_PERI_FINA,
           NUM_CONC,
           DECODE(COD_TIPO_PUNT,'V','V-Ventas','C','C-Constancia','P','P-Pedido'),
           COD_ESTA_CONC,
           NUM_PUNT
    FROM   AVI_HISTO_CONSU_PUNTA
    WHERE  COD_PAIS = psCodigoPais
      AND  IND_ENVI = '1'
     ORDER BY COD_CLIE,
            COD_PERI_INIC,
            COD_PERI_FINA,
            NUM_CONC,
            COD_ESTA_CONC,
            COD_TIPO_PUNT;

  CURSOR c_interfazPuntaje(oidPais NUMBER, periodo VARCHAR2) IS
    SELECT psCodigoPais,
           CLIEN.COD_CLIE,
           PERD.COD_PERI,
           PERH.COD_PERI,
           CPG.NUM_CONC,
           CPG.TIPO,
           DECODE (IVC.SITUACION,  'V','P', DECODE (IVF.CLIE_OID_CLIE, NULL, 'N', 'G') ),
           SUM (CCP.NUM_PUNT)
    FROM (SELECT IVD.NUM_CONC,
                 IVD.COPA_OID_PARA_GRAL,
                 DECODE(IVD.VICO_OID_VIGE_CONC, 1, 'V', 'T') SITUACION
            FROM INC_VERSI_CONCU IVD
           WHERE IVD.VICO_OID_VIGE_CONC IN (1, 6)) IVC,
         (SELECT CPG1.OID_PARA_GRAL,
                 CPG1.NUM_CONC,
                 CPG1.PERD_OID_PERI_HAST,
                 CPG1.PERD_OID_PERI_DESD,
                 'V' TIPO
            FROM INC_CONCU_PARAM_GENER CPG1
           WHERE CPG1.COIV_OID_CONC_IVR = 1
             AND CPG1.PAIS_OID_PAIS = oidPais --OIDPAIS
          UNION
          SELECT CPG1.OID_PARA_GRAL,
                 CPG1.NUM_CONC,
                 CPG1.PERD_OID_PERI_HAST,
                 CPG1.PERD_OID_PERI_DESD,
                 DECODE(CPG1.COIV_OID_CONC_IVR, 2, 'C', 'P') TIPO
            FROM INC_CONCU_PARAM_GENER CPG1
           WHERE (CPG1.COIV_OID_CONC_IVR = 2 OR
                 CPG1.COIV_OID_CONC_IVR = 3)
             AND CPG1.PAIS_OID_PAIS = oidPais /*OIDPAIS*/
          ) CPG,
         INC_CUENT_CORRI_PUNTO CCP,
         MAE_CLIEN CLIEN,
         (SELECT GAN.CLIE_OID_CLIE, PGP.COPA_OID_PARA_GRAL
            FROM INC_PARAM_GENER_PREMI PGP,
                 INC_PARAM_NIVEL_PREMI PNP,
                 INC_GANAD             GAN
           WHERE GAN.PANP_OID_PARA_NIVE_PREM = PNP.OID_PARA_NIVE_PREM
             AND PNP.PAGP_OID_PARA_GENE_PREM = PGP.OID_PARA_GENE_PREM
           GROUP BY GAN.CLIE_OID_CLIE, PGP.COPA_OID_PARA_GRAL) IVF,
         (SELECT C.OID_PERI,
                 A.COD_PERI,
                 (SELECT COUNT(1)
                    FROM SEG_PERIO_CORPO B
                   WHERE B.COD_PERI <= A.COD_PERI) AS CONTA
            FROM SEG_PERIO_CORPO A, CRA_PERIO C
           WHERE A.OID_PERI = C.PERI_OID_PERI
           ORDER BY A.COD_PERI) PERD,
         (SELECT C.OID_PERI,
                 A.COD_PERI,
                 (SELECT COUNT(1)
                    FROM SEG_PERIO_CORPO B
                   WHERE B.COD_PERI <= A.COD_PERI) AS CONTA
            FROM SEG_PERIO_CORPO A, CRA_PERIO C
           WHERE A.OID_PERI = C.PERI_OID_PERI
           ORDER BY A.COD_PERI) PERH
   WHERE IVC.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
     AND CPG.OID_PARA_GRAL = CCP.COPA_OID_PARA_GRAL
     AND CCP.COPA_OID_PARA_GRAL = IVF.COPA_OID_PARA_GRAL(+)
     AND CCP.CLIE_OID_CLIE = CLIEN.OID_CLIE
     AND CCP.CLIE_OID_CLIE = IVF.CLIE_OID_CLIE(+)
     AND NOT (CCP.TMOV_OID_TIPO_MOVI = 2 AND CCP.VAL_DESC = 'Entrega de Premio')
     AND CCP.TMOV_OID_TIPO_MOVI <> 3
     AND CPG.PERD_OID_PERI_DESD = PERD.OID_PERI
     AND CPG.PERD_OID_PERI_HAST = PERH.OID_PERI
     AND (   (    IVC.SITUACION = 'T'
              AND ((SELECT COUNT (1)
                      FROM SEG_PERIO_CORPO B
                     WHERE B.COD_PERI <= periodo) - PERH.CONTA BETWEEN 1 AND 5)
             )
          OR (    IVC.SITUACION = 'V'
              /*AND PERH.CONTA >= (SELECT COUNT (*)
                                   FROM SEG_PERIO_CORPO B
                                  WHERE B.COD_PERI <= periodo)*/
              AND PERD.CONTA <= (SELECT COUNT (*)
                                   FROM SEG_PERIO_CORPO B
                                  WHERE B.COD_PERI <= periodo)
             )
         )
   GROUP BY CLIEN.COD_CLIE,
            CCP.CLIE_OID_CLIE,
            IVF.CLIE_OID_CLIE,
            PERH.COD_PERI,
            PERD.COD_PERI,
            CPG.NUM_CONC,
            CPG.OID_PARA_GRAL,
            IVC.SITUACION,
            CPG.TIPO;

  TYPE interfazRec IS RECORD
  (
    codigoPais        SEG_PAIS.COD_PAIS%TYPE,
    codigoCliente     MAE_CLIEN.COD_CLIE%TYPE,
    periodoInicio     SEG_PERIO_CORPO.COD_PERI%TYPE,
    periodoFin        SEG_PERIO_CORPO.COD_PERI%TYPE,
    codigoConcurso    INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    tipoPuntaje       VARCHAR2(15),
    estado            VARCHAR2(1),
    puntos            NUMBER(11)
  );

  rRegistro           AVI_HISTO_CONSU_PUNTA%ROWTYPE;
  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

  /* Variables de parametros */
  lnIdPais            NUMBER;
  lsPeriodo           VARCHAR2(6);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* obteniendo id Pais */
  lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante

  /*Obteniendo el periodo a Evaluar*/
  IF (psCodigoPeriodoCruce IS NOT NULL) THEN
    lsPeriodo := psCodigoPeriodoCruce;
  ELSE
    lsPeriodo := psCodigoPeriodo;
  END IF;

  --ENVIAR COMPLETO
  IF(psEnviarNovedades = 'N') THEN
    EXECUTE IMMEDIATE 'TRUNCATE TABLE AVI_HISTO_CONSU_PUNTA';

    INSERT INTO AVI_HISTO_CONSU_PUNTA
      (COD_PAIS,
       COD_CLIE,
       COD_PERI_INIC,
       COD_PERI_FINA,
       NUM_CONC,
       COD_TIPO_PUNT,
       COD_ESTA_CONC,
       NUM_PUNT,
       IND_ENVI,
       FEC_ULTI_ENVI)
      SELECT psCodigoPais,
             CLIEN.COD_CLIE,
             PERD.COD_PERI,
             PERH.COD_PERI,
             CPG.NUM_CONC,
             CPG.TIPO,
             DECODE (IVC.SITUACION,  'V','P', DECODE (IVF.CLIE_OID_CLIE, NULL, 'N', 'G') ),
             SUM (CCP.NUM_PUNT),
             '1',
             TRUNC(SYSDATE)
      FROM (SELECT IVD.NUM_CONC,
                   IVD.COPA_OID_PARA_GRAL,
                   DECODE(IVD.VICO_OID_VIGE_CONC, 1, 'V', 'T') SITUACION
              FROM INC_VERSI_CONCU IVD
             WHERE IVD.VICO_OID_VIGE_CONC IN (1, 6)) IVC,
           (SELECT CPG1.OID_PARA_GRAL,
                   CPG1.NUM_CONC,
                   CPG1.PERD_OID_PERI_HAST,
                   CPG1.PERD_OID_PERI_DESD,
                   'V' TIPO
              FROM INC_CONCU_PARAM_GENER CPG1
             WHERE CPG1.COIV_OID_CONC_IVR = 1
               AND CPG1.PAIS_OID_PAIS = lnIdPais --OIDPAIS
            UNION
            SELECT CPG1.OID_PARA_GRAL,
                   CPG1.NUM_CONC,
                   CPG1.PERD_OID_PERI_HAST,
                   CPG1.PERD_OID_PERI_DESD,
                   DECODE(CPG1.COIV_OID_CONC_IVR, 2, 'C', 'P') TIPO
              FROM INC_CONCU_PARAM_GENER CPG1
             WHERE (CPG1.COIV_OID_CONC_IVR = 2 OR
                   CPG1.COIV_OID_CONC_IVR = 3)
               AND CPG1.PAIS_OID_PAIS = lnIdPais /*OIDPAIS*/
            ) CPG,
           INC_CUENT_CORRI_PUNTO CCP,
           MAE_CLIEN CLIEN,
           (SELECT GAN.CLIE_OID_CLIE, PGP.COPA_OID_PARA_GRAL
              FROM INC_PARAM_GENER_PREMI PGP,
                   INC_PARAM_NIVEL_PREMI PNP,
                   INC_GANAD             GAN
             WHERE GAN.PANP_OID_PARA_NIVE_PREM = PNP.OID_PARA_NIVE_PREM
               AND PNP.PAGP_OID_PARA_GENE_PREM = PGP.OID_PARA_GENE_PREM
             GROUP BY GAN.CLIE_OID_CLIE, PGP.COPA_OID_PARA_GRAL) IVF,
           (SELECT C.OID_PERI,
                   A.COD_PERI,
                   (SELECT COUNT(1)
                      FROM SEG_PERIO_CORPO B
                     WHERE B.COD_PERI <= A.COD_PERI) AS CONTA
              FROM SEG_PERIO_CORPO A, CRA_PERIO C
             WHERE A.OID_PERI = C.PERI_OID_PERI
             ORDER BY A.COD_PERI) PERD,
           (SELECT C.OID_PERI,
                   A.COD_PERI,
                   (SELECT COUNT(1)
                      FROM SEG_PERIO_CORPO B
                     WHERE B.COD_PERI <= A.COD_PERI) AS CONTA
              FROM SEG_PERIO_CORPO A, CRA_PERIO C
             WHERE A.OID_PERI = C.PERI_OID_PERI
             ORDER BY A.COD_PERI) PERH
     WHERE IVC.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
       AND CPG.OID_PARA_GRAL = CCP.COPA_OID_PARA_GRAL
       AND CCP.COPA_OID_PARA_GRAL = IVF.COPA_OID_PARA_GRAL(+)
       AND CCP.CLIE_OID_CLIE = CLIEN.OID_CLIE
       AND CCP.CLIE_OID_CLIE = IVF.CLIE_OID_CLIE(+)
       AND NOT (CCP.TMOV_OID_TIPO_MOVI = 2 AND CCP.VAL_DESC = 'Entrega de Premio')
       AND CCP.TMOV_OID_TIPO_MOVI <> 3
       AND CPG.PERD_OID_PERI_DESD = PERD.OID_PERI
       AND CPG.PERD_OID_PERI_HAST = PERH.OID_PERI
       AND (   (    IVC.SITUACION = 'T'
                AND ((SELECT COUNT (1)
                        FROM SEG_PERIO_CORPO B
                       WHERE B.COD_PERI <= lsPeriodo) - PERH.CONTA BETWEEN 1 AND 5)
               )
            OR (    IVC.SITUACION = 'V'
                /*AND PERH.CONTA >= (SELECT COUNT (*)
                                     FROM SEG_PERIO_CORPO B
                                    WHERE B.COD_PERI <= lsPeriodo)*/
                AND PERD.CONTA <= (SELECT COUNT (1)
                                     FROM SEG_PERIO_CORPO B
                                    WHERE B.COD_PERI <= lsPeriodo)
               )
           )
     GROUP BY CLIEN.COD_CLIE,
              CCP.CLIE_OID_CLIE,
              IVF.CLIE_OID_CLIE,
              PERH.COD_PERI,
              PERD.COD_PERI,
              CPG.NUM_CONC,
              CPG.OID_PARA_GRAL,
              IVC.SITUACION,
              CPG.TIPO;
  ELSE
    --SOLO EN CASO DE ENVIAR NOVEDADES
    UPDATE AVI_HISTO_CONSU_PUNTA
       SET IND_ENVI = '0';

    OPEN c_interfazPuntaje(lnIdPais, lsPeriodo);
    LOOP
       FETCH c_interfazPuntaje BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
         IF interfazRecord.COUNT > 0 THEN
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

              BEGIN
                SELECT *
                 INTO  rRegistro
                 FROM  AVI_HISTO_CONSU_PUNTA
                WHERE COD_PAIS = interfazRecord(x).codigoPais
                  AND COD_CLIE = interfazRecord(x).codigoCliente
                  AND NUM_CONC = interfazRecord(x).codigoConcurso;

                IF((rRegistro.COD_PERI_INIC <> interfazRecord(x).periodoInicio) OR
                   (rRegistro.COD_PERI_FINA <> interfazRecord(x).periodoFin) OR
                   (rRegistro.COD_TIPO_PUNT <> interfazRecord(x).tipoPuntaje) OR
                   (rRegistro.COD_ESTA_CONC <> interfazRecord(x).estado) OR
                   (rRegistro.NUM_PUNT <> interfazRecord(x).puntos)) THEN

                  UPDATE AVI_HISTO_CONSU_PUNTA
                     SET IND_ENVI = '1',
                         FEC_ULTI_ENVI = TRUNC(SYSDATE),
                         COD_PERI_INIC = interfazRecord(x).periodoInicio,
                         COD_PERI_FINA = interfazRecord(x).periodoFin,
                         COD_TIPO_PUNT = interfazRecord(x).tipoPuntaje,
                         COD_ESTA_CONC = interfazRecord(x).estado,
                         NUM_PUNT = interfazRecord(x).puntos
                   WHERE COD_PAIS = rRegistro.COD_PAIS
                     AND COD_CLIE = rRegistro.COD_CLIE
                     AND NUM_CONC = rRegistro.NUM_CONC;
                END IF;

              EXCEPTION
               WHEN OTHERS THEN
                  INSERT INTO AVI_HISTO_CONSU_PUNTA
                    (COD_PAIS,
                     COD_CLIE,
                     NUM_CONC,
                     COD_PERI_INIC,
                     COD_PERI_FINA,
                     COD_TIPO_PUNT,
                     COD_ESTA_CONC,
                     NUM_PUNT,
                     IND_ENVI,
                     FEC_ULTI_ENVI)
                  VALUES
                     (interfazRecord(x).codigoPais,
                      interfazRecord(x).codigoCliente,
                      interfazRecord(x).codigoConcurso,
                      interfazRecord(x).periodoInicio,
                      interfazRecord(x).periodoFin,
                      interfazRecord(x).tipoPuntaje,
                      interfazRecord(x).estado,
                      interfazRecord(x).puntos,
                      '1',
                      TRUNC(SYSDATE));
              END;

            END LOOP;
         END IF;

       EXIT WHEN c_interfazPuntaje%NOTFOUND;
    END LOOP;
  END IF;

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;

     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).codigoCliente         ||';'||
                        interfazRecord(x).periodoInicio         ||';'||
                        interfazRecord(x).periodoFin            ||';'||
                        interfazRecord(x).codigoConcurso        ||';'||
                        interfazRecord(x).tipoPuntaje           ||';'||
                        interfazRecord(x).estado                ||';'||
                        interfazRecord(x).puntos;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR AVI_PR_INTER_CONSU_PUNTA: '||ls_sqlerrm);


END INT_PR_AVI_ENVIO_CONSU_PUNTA;

/**************************************************************************
Descripcion       : Carga la informacion de los Detalles CDR de las consultoras
                    requeridos por la interface Asistente Virtual (AVI-6)
Fecha Creacion    : 07/08/2008
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoCruce  :  Codigo de periodo cruce

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_CONSU_DECDR (psCodigoPais            VARCHAR2,
                                    psCodigoSistema         VARCHAR2,
                                    psCodigoInterfaz        VARCHAR2,
                                    psNombreArchivo         VARCHAR2,
                                    psCodigoPeriodo         VARCHAR2,
                                    psCodigoPeriodoCruce    VARCHAR2)
IS
  CURSOR c_interfaz IS
     SELECT
          psCodigoPais AS CODIGO_PAIS,
          MAE_CLIEN.COD_CLIE AS CODIGO_CLIENTE,
          REC_CABEC_RECLA.NUM_RECL AS NUMERO_RECLAMO,
                COD_VENTA.VAL_CODI_VENT AS CODIGO_VENTA,
          REC_LINEA_OPERA_RECLA.NUM_UNID_RECL AS NUM_UNID_RECL,
                (case WHEN REC_TIPO_MOVIM.COD_TIPO_MOVI = 'E' THEN REC_LINEA_OPERA_RECLA.IMP_CARG
                      WHEN REC_TIPO_MOVIM.COD_TIPO_MOVI = 'D' THEN REC_LINEA_OPERA_RECLA.IMP_ABON
                      ELSE 0
                 end) AS MONTO,
          DECODE(REC_OPERA.COD_OPER,'CM','C','TM','Q','DN','D','DE','D','FM','F','FA','F','TP','P') AS CODIGO_OPERACION,
                (CASE WHEN (REC_CABEC_RECLA.ESRE_OID_ESTA_RECL = 5) THEN 'R'
                      WHEN (REC_TIPO_MOVIM.COD_TIPO_MOVI = 'E') THEN 'E'
                      WHEN (REC_TIPO_MOVIM.COD_TIPO_MOVI = 'D') THEN 'B'
         END) AS ESTADO,
         '' MOTIVO
    FROM REC_CABEC_RECLA,
         MAE_CLIEN,
         MAE_CLIEN_DATOS_ADICI,
         REC_OPERA_RECLA,
         REC_LINEA_OPERA_RECLA,
           REC_TIPO_MOVIM,
         SEG_PERIO_CORPO,
         CRA_PERIO,
         REC_OPERA,
         REC_TIPOS_OPERA,
         (SELECT Z2.PROD_OID_PROD, Z2.VAL_CODI_VENT,Z1.OID_MATR_FACT, Z2.TOFE_OID_TIPO_OFER
           FROM PRE_MATRI_FACTU Z1, PRE_OFERT_DETAL Z2, PRE_MATRI_FACTU_CABEC Z3
           WHERE z3.OID_CABE = Z1.MFCA_OID_CABE
           AND Z1.OFDE_OID_DETA_OFER = Z2.OID_DETA_OFER
          ) COD_VENTA
         WHERE  (REC_CABEC_RECLA.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
         AND (MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
         AND (REC_CABEC_RECLA.OID_CABE_RECL = REC_OPERA_RECLA.CARE_OID_CABE_RECL)
         AND (REC_TIPOS_OPERA.OID_TIPO_OPER = REC_OPERA_RECLA.TIOP_OID_TIPO_OPER)
         AND (REC_TIPOS_OPERA.ROPE_OID_OPER = REC_OPERA.OID_OPER)
         AND (REC_LINEA_OPERA_RECLA.OPRE_OID_OPER_RECL = REC_OPERA_RECLA.OID_OPER_RECL)
         AND (REC_CABEC_RECLA.PERD_OID_PERI_RECL = CRA_PERIO.OID_PERI)
         AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
         AND (REC_CABEC_RECLA.COD_USUA_INGR != 'CALYPSO')
         AND (REC_CABEC_RECLA.COD_USUA_INGR != 'BELCENTER')
         AND (REC_OPERA.COD_OPER IN ('CM','TM','TP','DN','DE','FM','FA'))
           AND (REC_TIPO_MOVIM.OID_TIPO_MOVI = REC_LINEA_OPERA_RECLA.TIMO_OID_TIPO_MOVI)
         AND (MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE != 7)
           AND REC_LINEA_OPERA_RECLA.PROD_OID_PROD = COD_VENTA.PROD_OID_PROD (+)
            AND COD_VENTA.TOFE_OID_TIPO_OFER (+) = REC_LINEA_OPERA_RECLA.TOFE_OID_TIPO_OFER
           AND COD_VENTA.OID_MATR_FACT(+)  = REC_LINEA_OPERA_RECLA.MAFA_OID_MATR_FACT
         AND SEG_PERIO_CORPO.COD_PERI >= psCodigoPeriodo;

  TYPE interfazRec IS RECORD
  (
    codigoPais        SEG_PAIS.COD_PAIS%TYPE,
    codigoCliente     MAE_CLIEN.COD_CLIE%TYPE,
    guiaCDR           REC_CABEC_RECLA.NUM_RECL%TYPE,
    codigoVenta       VARCHAR2(6),
    numUnidadesRecla  REC_LINEA_OPERA_RECLA.NUM_UNID_RECL%TYPE,
    monto             VARCHAR2(11),
    codigoOperacion   VARCHAR2(1),
    estado            VARCHAR2(1),
    motivo            VARCHAR2(1)

  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;
  OPEN c_interfaz;
  LOOP
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).codigoCliente         ||';'||
                        interfazRecord(x).guiaCDR               ||';'||
                        interfazRecord(x).codigoVenta           ||';'||
                        interfazRecord(x).numUnidadesRecla      ||';'||
                        interfazRecord(x).monto                 ||';'||
                        interfazRecord(x).codigoOperacion       ||';'||
                        interfazRecord(x).estado                ||';'||
                        interfazRecord(x).motivo;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_CONSU_DECDR: '||ls_sqlerrm);

END INT_PR_AVI_ENVIO_CONSU_DECDR;


/**************************************************************************
Descripcion        : Verifica si una region esta cerrado para un determinado
                     periodo
Fecha Creacion     : 26/11/2008
Parametros Entrada :
           pnOidPeriodo : Oid Periodo
           pnOidRegion : Oid Region

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION AVI_FN_VALID_REGIO_CERRA(pnOidPeriodo        NUMBER,
                                  pnOidRegion         NUMBER)
RETURN NUMBER IS
  lnTotal NUMBER;
BEGIN
  /* Obteniendo id de periodo */
  SELECT COUNT(OID_CTRL)
    INTO lnTotal
    FROM FAC_CONTR_CIERR CIE
   WHERE PERD_OID_PERI = pnOidPeriodo
     AND TCIE_OID_TIPO_CIER = 1
     AND ZORG_OID_REGI = pnOidRegion;

  RETURN lnTotal;

END AVI_FN_VALID_REGIO_CERRA;

/**************************************************************************
Descripcion       : Envia los Saldos de la Consultora
Fecha Creacion    : 08/04/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoCruce  :  Codigo de periodo cruce
  psEnviarNovedades     : Enviar Novedades (S, N: Completo)
Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_SALDO_CONSU (psCodigoPais            VARCHAR2,
                                    psCodigoSistema         VARCHAR2,
                                    psCodigoInterfaz        VARCHAR2,
                                    psNombreArchivo         VARCHAR2,
                                    psCodigoPeriodo         VARCHAR2,
                                    psCodigoPeriodoCruce    VARCHAR2,
                                    psEnviarNovedades       VARCHAR2)
IS

  CURSOR c_interfazCompleto(oidPais NUMBER) IS
  WITH base AS
      (
        SELECT clie.oid_clie,
               clie.cod_clie,
               --CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_VENCI( p_oid_clie => clie.oid_clie ) saldo,
               clie.sal_deud_ante saldo, --Nuevo
               cuad.ztad_oid_terr_admi
          FROM mae_clien clie,
               mae_clien_datos_adici clda,
               mae_clien_unida_admin cuad,
               mae_clien_tipo_subti ctsu
         WHERE clie.oid_clie = clda.clie_oid_clie
           AND clie.oid_clie = cuad.clie_oid_clie
           AND clie.oid_clie = ctsu.clie_oid_clie
           --
           AND ctsu.ticl_oid_tipo_clie = 2
           AND cuad.ind_acti = 1
    )
      SELECT psCodigoPais codigoPais,
             cod_clie codigoCliente,
             zzon.cod_zona codigoZona,
             saldo
        FROM base,
             zon_terri_admin ztad,
             zon_secci zscc,
             zon_zona zzon
       WHERE base.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.zscc_oid_secc = zscc.oid_secc
         AND zscc.zzon_oid_zona = zzon.oid_zona;

CURSOR c_interfazNovedad(oidPais NUMBER, fecha DATE) IS
  WITH base AS
      (
        SELECT clie.oid_clie,
               clie.cod_clie,
               --CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_VENCI( p_oid_clie => clie.oid_clie ) saldo,
               clie.sal_deud_ante saldo, --Nuevo
               cuad.ztad_oid_terr_admi
          FROM mae_clien clie,
               mae_clien_datos_adici clda,
               mae_clien_unida_admin cuad,
               mae_clien_tipo_subti ctsu
         WHERE clie.oid_clie = clda.clie_oid_clie
           AND clie.oid_clie = cuad.clie_oid_clie
           AND clie.oid_clie = ctsu.clie_oid_clie
           --
           AND ctsu.ticl_oid_tipo_clie = 2
           AND cuad.ind_acti = 1
           AND ( clie.fec_ulti_actu >= fecha OR
                 clda.fec_ulti_actu >= fecha OR
                 cuad.fec_ulti_actu >= fecha )
        )
      SELECT psCodigoPais codigoPais,
             cod_clie codigoCliente,
             zzon.cod_zona codigoZona,
             saldo
        FROM base,
             zon_terri_admin ztad,
             zon_secci zscc,
             zon_zona zzon
       WHERE base.ztad_oid_terr_admi = ztad.oid_terr_admi
         AND ztad.zscc_oid_secc = zscc.oid_secc
         AND zscc.zzon_oid_zona = zzon.oid_zona;

  TYPE interfazRec IS RECORD
  (
    codigoPais        SEG_PAIS.COD_PAIS%TYPE,
    codigoCliente     MAE_CLIEN.COD_CLIE%TYPE,
    codigoZona        ZON_ZONA.COD_ZONA%TYPE,
    saldo             VARCHAR2(15)
  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
  lnIdPais            NUMBER;
  ldUltimaFecha       DATE;
BEGIN

  /* obteniendo id Pais */
  lnIdPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);-- id del pais consultante

  /* Si se va a enviar Novedades obtenemos la ultima fecha de corrida de la interfaz AVI-9*/
  IF(psEnviarNovedades = 'S') THEN
    BEGIN
      SELECT MAX(trunc(FEC_FPRO))
        INTO ldUltimaFecha
        FROM BAS_HISTO_LOTES
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND SIST_COD_SIST = psCodigoSistema
         AND INTE_COD_INTE = psCodigoInterfaz
         AND IND_LOER = 'N';
    EXCEPTION
      WHEN OTHERS THEN
        ldUltimaFecha := NULL;
    END;
  END IF;

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;

  IF((psEnviarNovedades = 'N') OR ((psEnviarNovedades = 'S') AND (ldUltimaFecha IS NULL))) THEN
    OPEN c_interfazCompleto(lnIdPais);
    LOOP
       FETCH c_interfazCompleto BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       /* Procedimiento inicial para generar interfaz */
       IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
       END IF;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                          interfazRecord(x).codigoCliente         ||';'||
                          interfazRecord(x).codigoZona            ||';'||
                          RPAD(TRIM(TO_CHAR(interfazRecord(x).saldo,'999999999990.00')),15,' ')
                          --interfazRecord(x).saldo
                          ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfazCompleto%NOTFOUND;
    END LOOP;

  ELSE

    OPEN c_interfazNovedad(lnIdPais, ldUltimaFecha);
    LOOP
       FETCH c_interfazNovedad BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       /* Procedimiento inicial para generar interfaz */
       IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
       END IF;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                          interfazRecord(x).codigoCliente         ||';'||
                          interfazRecord(x).codigoZona            ||';'||
                          interfazRecord(x).saldo;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfazNovedad%NOTFOUND;
    END LOOP;

  END IF;

  utl_file.fclose(V_HANDLE);

  IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  END IF;

  RETURN;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR AVI_PR_INTER_SALDO_CONSU: '||ls_sqlerrm);

END INT_PR_AVI_ENVIO_SALDO_CONSU;


/***************************************************************************
Descripcion       : Genera Interfase Consultoras Bloqueadas (AVI-10)
Fecha Creacion    : 16/08/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_CONSU_BLOQU
(  psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2
)

IS
   Cursor C_Interfaz Is
      Select    Cl.Oid_Clie,
                Cl.Cod_Clie,
                psCodigoPais as cod_pais,
                null as cod_consu,
                (
                  Nvl(Trim(Val_Nom1), ' ') || ' ' ||
                  Nvl(Trim(Val_Ape1), ' ') || ' ' ||
                  Nvl(Trim(Val_Ape2), ' ')
                ) As Nom_Consu,
                null as Cod_Bloq,
                '1' as ind_activ
      From      MAE_CLIEN CL,
                Mae_Clien_Tipo_Subti Ct,
                Mae_Clien_Datos_Adici Cad
      Where     Cl.Oid_Clie = Ct.Clie_Oid_Clie
        And     Cl.Oid_Clie = Cad.Clie_Oid_Clie
        And     Ct.Ticl_Oid_Tipo_Clie = 2
        And     Ct.Ind_Ppal = 1
        And     Cad.Ind_Acti = 1
        And     (select count(1) from Mae_Clien_Bloqu where clie_oid_clie=cl.oid_clie and fec_desb is null) > 0;

   Type Interfaz Is Record   (
      oidCliente              NUMBER,
      codigoCliente           Varchar2(15),
      codigoPais              Varchar2(3),
      codigoConsultora        Varchar2(15),
      nombreConsultora        Varchar2(100),
      codigoBloqueo           Varchar2(2),
      indicaActivacion        VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;
   v_validaIntefaz     NUMBER;

   /* Variables usadas para los subquerys */
   v_indicadorCodCon   VARCHAR2(1);
   v_numeroDocIde      VARCHAR2(30);
   v_codigoConsultora  VARCHAR2(30);
   v_codigoBloqueo     VARCHAR2(2);

BEGIN

    /* Flag para abrir utl_file */
    lbAbrirUtlFile := TRUE;

    /* Validar si es necesario generar esta interfaz para el país que está consultando */
    select    count(*)
      into    v_validaIntefaz
    from      Int_Param_Gener
    where     Pais_Cod_Pais=psCodigoPais
      And     Cod_Inte=psCodigoInterfaz
      and     ind_acti_inte=1;

    if v_validaIntefaz>0 then

      -- capturar indicador de cod. consultora
      BEGIN
        Select    Ind_Docu_Iden
          into    v_indicadorCodCon
        From      Int_Param_Gener
        Where     Pais_Cod_Pais=psCodigoPais
        And       Cod_Inte=psCodigoInterfaz;
      EXCEPTION
          WHEN NO_DATA_FOUND THEN
          v_indicadorCodCon:=NULL;
      END;

      /* Obteniendo lista */
      OPEN c_interfaz;
      LOOP
         FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

         /* Procedimiento inicial para generar interfaz */
         IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
         END IF;

         /* Formateo filas */
         IF interfazRecord.COUNT > 0 THEN
            For X In Interfazrecord.First .. Interfazrecord.Last Loop

                -- capturar numero de doc. identidad
                BEGIN
                  Select    Num_Docu_Iden
                    into    v_numeroDocIde
                  From      Mae_Clien_Ident
                  Where     Clie_Oid_Clie=interfazRecord(x).oidCliente
                    and     val_iden_docu_prin = 1;
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                    v_numeroDocIde:=NULL;
                END;

                -- capturar codigo consultora
                IF v_indicadorCodCon = 0 THEN
                  v_codigoConsultora := interfazRecord(x).codigoCliente;
                ELSE
                  v_codigoConsultora := v_numeroDocIde;
                END IF;

                -- capturar codigo bloqueo
                BEGIN
                  Select    distinct cod_tipo_bloq
                    into    v_codigoBloqueo
                  From      Mae_Tipo_Bloqu
                  Where     Oid_Tipo_Bloq In (
                              Select    Tibq_Oid_Tipo_Bloq
                              From      Mae_Clien_Bloqu
                              Where     Clie_Oid_Clie = interfazRecord(x).oidCliente
                            )
                    and     num_nive_grav_bloq in (
                              Select    min(num_nive_grav_bloq)
                              From      Mae_Tipo_Bloqu
                              Where     Oid_Tipo_Bloq In (
                                          Select    Distinct Tibq_Oid_Tipo_Bloq
                                          From      Mae_Clien_Bloqu
                                          Where     Clie_Oid_Clie = interfazRecord(x).oidCliente
                                        )
                            );
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                    v_codigoBloqueo:=NULL;
                END;

                lsLinea :=  interfazRecord(x).codigoPais         ||';'||
                            v_codigoConsultora                   ||';'||
                            interfazRecord(x).nombreConsultora   ||';'||
                            v_codigoBloqueo                      ||';'||
                            interfazRecord(x).indicaActivacion   ;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

            END LOOP;
         END IF;
         EXIT WHEN c_interfaz%NOTFOUND;
      END LOOP;
      CLOSE c_interfaz;

    else

        /* generar utl_file vacio */
         IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
         END IF;

    end if;

    /* Cerrar utl_file */
    utl_file.fclose(V_HANDLE);
    IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_CONSU_BLOQU: '||ls_sqlerrm);

END INT_PR_AVI_ENVIO_CONSU_BLOQU;

/***************************************************************************
Descripcion       : Genera Interfase Tipos de Bloqueos (AVI-11)
Fecha Creacion    : 16/08/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_TIPOS_BLOQU
(  psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2
)

IS
   Cursor C_Interfaz Is
      Select    psCodigoPais,
                Bl.Cod_Tipo_Bloq,
                Gen.Val_I18n as des_bloq,
                '1' As Ind_Activ
      from      mae_tipo_bloqu bl,
                Gen_I18n_Sicc_Comun Gen
      Where     Bl.Oid_Tipo_Bloq = Gen.Val_Oid
        And     Gen.Attr_Enti='MAE_TIPO_BLOQU';

   Type Interfaz Is Record   (
      codigoPais              Varchar2(3),
      codigoBloqueo           Varchar2(2),
      descripcionBloqueo      Varchar2(100),
      indicaActivacion        VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;
   v_validaIntefaz     NUMBER;

BEGIN

    /* Flag para abrir utl_file */
    lbAbrirUtlFile := TRUE;

    /* Validar si es necesario generar esta interfaz para el país que está consultando */
    select    count(*)
      into    v_validaIntefaz
    from      Int_Param_Gener
    where     Pais_Cod_Pais=psCodigoPais
      And     Cod_Inte=psCodigoInterfaz
      and     ind_acti_inte=1;

    if v_validaIntefaz>0 then

      /* Obteniendo lista */
      OPEN c_interfaz;
      LOOP
         FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

         /* Procedimiento inicial para generar interfaz */
         IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
         END IF;

         /* Formateo filas */
         IF interfazRecord.COUNT > 0 THEN
            For X In Interfazrecord.First .. Interfazrecord.Last Loop
            lsLinea :=  interfazRecord(x).codigoPais         ||';'||
                        interfazrecord(X).codigoBloqueo      ||';'||
                        interfazRecord(x).descripcionBloqueo ||';'||
                        interfazRecord(x).indicaActivacion   ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END LOOP;
         END IF;
         EXIT WHEN c_interfaz%NOTFOUND;
      END LOOP;
      CLOSE c_interfaz;

    else

        /* generar utl_file vacio */
         IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
         END IF;

    end if;

    /* Cerrar utl_file */
    utl_file.fclose(V_HANDLE);
    IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_TIPOS_BLOQU: '||ls_sqlerrm);

END INT_PR_AVI_ENVIO_TIPOS_BLOQU;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Secciones (AVI-12)
Fecha Creacion    : 14/02/2011
Autor             :  Carlos Mori
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_SECCI
  (psCodigoPais           VARCHAR2,
   psCodigoMarca          VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoIso      VARCHAR2,
   psCodigoPeriodo  VARCHAR2)
IS
CURSOR c_interfaz ( vnOidPeriodo NUMBER )  IS
    SELECT pais.cod_pais,
           zorg.cod_regi,
           zzon.cod_zona,
           zscc.cod_secc,
           zscc.ind_acti
      FROM zon_secci zscc,
           zon_zona zzon,
           zon_regio zorg,
           seg_pais  pais
     WHERE zscc.zzon_oid_zona = zzon.oid_zona(+)
       AND zzon.zorg_oid_regi = zorg.oid_regi(+)
       AND zorg.pais_oid_pais = pais.oid_pais
       --
       AND pais.cod_pais = psCodigoPais
       AND vnOidPeriodo BETWEEN NVL(zscc.perd_oid_peri_inic,vnOidPeriodo) AND
                                NVL(zscc.perd_oid_peri_fina,vnOidPeriodo);

   TYPE interfazRec IS RECORD
       (
        codigoPais                SEG_PAIS.COD_PAIS%TYPE,
        codigoRegion              ZON_REGIO.COD_REGI%TYPE,
        codigoZona                ZON_ZONA.COD_ZONA%TYPE,
        codigoSeccion             ZON_SECCI.COD_SECC%TYPE,
        indicadorActivo           ZON_SECCI.IND_ACTI%TYPE
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;
   lnOidPeriodo        CRA_PERIO.Oid_Peri%TYPE;

BEGIN

    lbAbrirUtlFile:= TRUE;
    lnOidPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( psCodigoPeriodo );

    /* Generando Archivo de Texto (Detalle) */

        OPEN c_interfaz ( lnOidPeriodo );
            LOOP
               FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                                   /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                   GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                   lbAbrirUtlFile := FALSE;
                 END IF;

                   IF interfazRecord.COUNT > 0 THEN
                      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                          lsLinea :=  interfazRecord(x).codigoPais                    ||';'||
                                      interfazRecord(x).codigoRegion                  ||';'||
                                      interfazRecord(x).codigoZona                    ||';'||
                                      interfazRecord(x).codigoSeccion                 ||';'||
                                      interfazRecord(x).indicadorActivo;

                          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                      END LOOP;
                   END IF;
               EXIT WHEN c_interfaz%NOTFOUND;
            END LOOP;
        CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_SECCI: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_SECCI;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Metas / Logros  (AVI-13)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_LOGRO_METAS
  (psCodigoPais           VARCHAR2,
   psCodigoMarca          VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoIso            VARCHAR2,
   psCodigoPeriodo        VARCHAR2)
IS
   CURSOR c_interfaz IS

        SELECT nvcg.cod_pais codigoPais,
               zorg.cod_regi codigoRegion,
               zzon.cod_zona codigoZona,
               nvcg.cod_clie codigoCliente,
               nvcg.cod_tipo_logr codigoTipoLogro,
               nvcg.imp_logr montoLogro,
               nvcg.des_larg descripcionLogro,
               nvcg.ori_regi origenRegistro,
               CASE
                  WHEN psCodigoPeriodo BETWEEN nvcg.cmp_inic AND nvcg.cmp_fina  THEN 1
                  ELSE 0
               END indicadorActivo,
               NVL( nvcg.cod_medi_capt, '0' ) tipoVisitaMeta
          FROM nvs_consu_logro nvcg,
               nvs_tipo_logro nvtl,
               nvs_medio_captu_logro nvcl,
               mae_clien clie,
               mae_clien_unida_admin cuad,
               zon_terri_admin ztad,
               zon_secci zscc,
               zon_zona zzon,
               zon_regio zorg
         WHERE nvcg.cod_clie = clie.cod_clie
           AND nvcg.cod_tipo_logr = nvtl.cod_tipo_logr
           AND nvcg.cod_medi_capt = nvcl.cod_medi_capt(+)
           AND clie.oid_clie = cuad.clie_oid_clie
           AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
           AND ztad.zscc_oid_secc = zscc.oid_secc
           AND zscc.zzon_oid_zona = zzon.oid_zona
           AND zzon.zorg_oid_regi = zorg.oid_regi
           --
           AND cuad.ind_acti = 1
           AND nvcg.est_regi != '9'
           AND nvcg.est_logr = '1' -- Nuevo
             ;

TYPE interfazRecTab IS TABLE OF c_Interfaz%ROWTYPE INDEX BY BINARY_INTEGER;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    lbAbrirUtlFile:= TRUE;
    /* Generando Archivo de Texto (Detalle) */
        OPEN c_interfaz ();
            LOOP
               FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                                   /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                   GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                   lbAbrirUtlFile := FALSE;
                 END IF;

                   IF interfazRecord.COUNT > 0 THEN
                      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                          lsLinea :=  interfazRecord(x).codigoPais                    ||';'||
                                      interfazRecord(x).codigoRegion                  ||';'||
                                      interfazRecord(x).codigoZona                    ||';'||
                                      interfazRecord(x).codigoCliente                 ||';'||
                               RPAD( interfazRecord(x).codigoTipoLogro, 2, ' ' ) ||';'||
                               TO_CHAR( NVL( interfazRecord(x).montoLogro, 0 ),'999999999990.99' ) ||';'||
                               REPLACE( interfazRecord(x).descripcionLogro, ';', ' ' ) ||';'||
                                      interfazRecord(x).origenRegistro                ||';'||
                               interfazRecord(x).indicadorActivo ||';'||
                               interfazRecord(x).tipoVisitaMeta;
                          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                      END LOOP;
                   END IF;

               EXIT WHEN c_interfaz%NOTFOUND;
            END LOOP;
        CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_LOGRO_METAS: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_LOGRO_METAS;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Venta, Ganancia y Asistencia (AVI-14)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_VENTA_GANAN
  (psCodigoPais            VARCHAR2,
   psCodigoMarca           VARCHAR2,
   psCodigoCanal           VARCHAR2,
   psCodigoSistema         VARCHAR2,
   psCodigoInterfaz        VARCHAR2,
   psNombreArchivo         VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo         VARCHAR2)
IS
   CURSOR c_interfaz (vnIdPeriodo NUMBER) IS

     SELECT psCodigoPais codigoPais,
             zr.COD_REGI codigoRegion,
             zz.COD_ZONA codigoZona,
             mc.COD_CLIE codigoCliente,
             psCodigoPeriodo campanhaProceso,
             SUM(NVL(soca.val_prec_cata_tota_loca,0)) AS montoVenta,
             SUM(NVL(soca.val_gana_tota_loca,0)) AS montoGanancia,
             MAX(NVL(FlagAsiste,0)) AS indicadorAsiste,
             '1' indicadorActivo
        FROM MAE_CLIEN mc,
             PED_SOLIC_CABEC soca,
             PED_SOLIC_CABEC soca2,
             MAE_CLIEN_UNIDA_ADMIN mcua,
             ZON_TERRI_ADMIN zta,
             ZON_SECCI zs,
             ZON_ZONA zz,
             ZON_REGIO zr,
             (
              SELECT cod_clie, MAX(CASE WHEN UPPER(cod_acce) IN ('S','A') THEN 1 ELSE 0 END) AS FlagAsiste
                FROM int_solic_pedid_detal
               WHERE cod_peri = psCodigoPeriodo
               GROUP BY cod_clie
             ) asis
       WHERE mc.OID_CLIE = soca.CLIE_OID_CLIE
         AND mc.OID_CLIE = mcua.CLIE_OID_CLIE
         AND mc.COD_CLIE = asis.COD_CLIE(+)
         AND soca.SOCA_OID_SOLI_CABE = soca2.OID_SOLI_CABE
         AND mcua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
         AND zta.ZSCC_OID_SECC = zs.OID_SECC
         AND zs.ZZON_OID_ZONA = zz.OID_ZONA
         AND zz.ZORG_OID_REGI = zr.OID_REGI
         --
         AND mcua.IND_ACTI = 1
         AND soca.IND_OC = 1
         AND soca.ESSO_OID_ESTA_SOLI <> 4
         AND soca.MODU_OID_MODU NOT IN (13,15)
         AND soca.PERD_OID_PERI = vnIdPeriodo
       GROUP BY zr.COD_REGI, zz.COD_ZONA, mc.COD_CLIE;


   TYPE interfazRec IS RECORD
       (
        codigoPais                SEG_PAIS.COD_PAIS%TYPE,
        codigoRegion              ZON_REGIO.COD_REGI%TYPE,
        codigoZona                ZON_ZONA.COD_ZONA%TYPE,
        codigoCliente             MAE_CLIEN.COD_CLIE%TYPE,
        campanhaProceso           VARCHAR2(6),
        montoVenta                PED_SOLIC_CABEC.VAL_PREC_CATA_TOTA_LOCA%TYPE,
        montoGanancia             PED_SOLIC_CABEC.VAL_PREC_CATA_TOTA_LOCA%TYPE,
        indicadorAsiste           VARCHAR2(1),
        indicadorActivo           VARCHAR2(1)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

   lnIdPeriodo         NUMBER;


BEGIN

    lnIdPeriodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);-- id del periodo consultante

    lbAbrirUtlFile:= TRUE;
    /* Generando Archivo de Texto (Detalle) */
        OPEN c_interfaz (lnIdPeriodo);
            LOOP
               FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                                   /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                   GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                   lbAbrirUtlFile := FALSE;
                 END IF;

                   IF interfazRecord.COUNT > 0 THEN
                      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                          lsLinea :=  interfazRecord(x).codigoPais                    ||';'||
                                      interfazRecord(x).codigoRegion                  ||';'||
                                      interfazRecord(x).codigoZona                    ||';'||
                                      interfazRecord(x).codigoCliente                 ||';'||
                                      interfazRecord(x).campanhaProceso               ||';'||
                                      to_char(interfazRecord(x).montoVenta,'999999999990.99')    ||';'||
                                      to_char(interfazRecord(x).montoGanancia,'999999999990.99') ||';'||
                                      interfazRecord(x).indicadorAsiste               ||';'||
                                      interfazRecord(x).indicadorActivo;

                          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                      END LOOP;
                   END IF;
               EXIT WHEN c_interfaz%NOTFOUND;
            END LOOP;
        CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_VENTA_GANAN: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_VENTA_GANAN;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Venta por Marca (AVI-15)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_VENTA_MARCA
  (psCodigoPais           VARCHAR2,
   psCodigoMarca          VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoIso            VARCHAR2,
   psCodigoPeriodo        VARCHAR2)
IS
   CURSOR c_interfaz (vnIdPeriodo NUMBER) IS

 SELECT psCodigoPais codigoPais,
           zorg.cod_regi codigoRegion,
           zzon.cod_zona codigoZona,
           clie.cod_clie codigoCliente,
           psCodigoPeriodo AS campañaProceso,
           NVL(pmar.monto_venta_lbel,0) AS montoVentaLbel,
           NVL(pmar.monto_venta_esika,0) AS montoVentaEsika,
           NVL(pmar.monto_venta_cyzone,0) AS montoVentaCyzone,
           NVL(pmar.monto_venta_otros,0) as montoVentaOtros,
           CASE WHEN pmar.Monto_Total <> 0 THEN
                     ROUND((pmar.Monto_Venta_Lbel / pmar.Monto_Total)*100,0)
                ELSE 0
           END AS porcentajeVentaLbel,
           CASE WHEN pmar.Monto_Total <> 0 THEN
                     ROUND((pmar.Monto_Venta_Esika / pmar.Monto_Total)*100,0)
                ELSE 0
           END as porcentajeVentaEsika,
           CASE WHEN pmar.Monto_Total <> 0 THEN
                     ROUND((pmar.Monto_Venta_Cyzone / pmar.Monto_Total)*100,0)
           ELSE 0
           END AS porcentajeVentaCyzone,
           CASE WHEN pmar.Monto_Total <> 0 THEN
                     ROUND((pmar.Monto_Venta_Otros / pmar.Monto_Total)*100,0)
           ELSE 0
           END AS porcentajeVentaOtros,
           '1' indicadorActivo
      FROM mae_clien_unida_admin cuad,
           zon_terri_admin ztad,
           zon_secci zscc,
           zon_terri terr,
           zon_zona zzon,
           zon_regio zorg,
           mae_clien clie,
           (
            SELECT soca.clie_oid_clie,
                   NVL(SUM(
                       CASE WHEN sopo.val_prec_cata_unit_loca = 0 THEN
                                 sopo.val_prec_cont_unit_loca * NVL(sopo.num_unid_aten,0)
                            ELSE sopo.val_prec_cata_unit_loca * NVL(sopo.num_unid_aten,0)
                       END
                      ),0) AS Monto_Total,
                   NVL(SUM(
                       CASE WHEN mapr.Cod_Marc_Prod = '01' THEN
                             CASE WHEN sopo.val_prec_cata_unit_loca = 0 THEN
                                       sopo.val_prec_cont_unit_loca * NVL(sopo.num_unid_aten,0)
                                  ELSE sopo.val_prec_cata_unit_loca * NVL(sopo.num_unid_aten,0)
                             END
                       END
                      ),0) AS Monto_Venta_Lbel,
                   NVL(SUM(
                       CASE WHEN mapr.Cod_Marc_Prod = '02' THEN
                             CASE WHEN sopo.val_prec_cata_unit_loca = 0 THEN
                                       sopo.val_prec_cont_unit_loca * NVL(sopo.num_unid_aten,0)
                                  ELSE sopo.val_prec_cata_unit_loca * NVL(sopo.num_unid_aten,0)
                             END
                       END
                      ),0) AS Monto_Venta_Esika,
                   NVL(SUM(
                       CASE WHEN mapr.Cod_Marc_Prod = '03' THEN
                             CASE WHEN sopo.val_prec_cata_unit_loca = 0 THEN
                                       sopo.val_prec_cont_unit_loca * NVL(sopo.num_unid_aten,0)
                                  ELSE sopo.val_prec_cata_unit_loca * NVL(sopo.num_unid_aten,0)
                             END
                       END
                      ),0) AS Monto_Venta_Cyzone,
                   NVL(SUM(
                       CASE WHEN mapr.Cod_Marc_Prod NOT IN ('01','02','03') THEN
                             CASE WHEN sopo.val_prec_cata_unit_loca = 0 THEN
                                       sopo.val_prec_cont_unit_loca * NVL(sopo.num_unid_aten,0)
                                  ELSE sopo.val_prec_cata_unit_loca * NVL(sopo.num_unid_aten,0)
                             END
                       END
                      ),0) AS Monto_Venta_Otros
              FROM ped_solic_cabec soca,
                   ped_solic_cabec soca2,
                   ped_solic_posic sopo,
                   mae_produ prod,
                   seg_marca_produ mapr
             WHERE soca.soca_oid_soli_cabe = soca2.oid_soli_cabe
               AND soca.oid_soli_cabe = sopo.soca_oid_soli_cabe
               AND sopo.prod_oid_prod = prod.oid_prod
               AND prod.mapr_oid_marc_prod = mapr.oid_marc_prod(+)
               --
               AND soca.ind_oc = 1
               AND soca.esso_oid_esta_soli <> 4
               AND soca.modu_oid_modu NOT IN (13,15)
               AND soca.perd_oid_peri = vnIdPeriodo
             GROUP BY soca.clie_oid_clie
           ) pmar
     WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
       AND cuad.clie_oid_clie = clie.oid_clie
       AND clie.oid_clie = pmar.clie_oid_clie
       AND ztad.zscc_oid_secc = zscc.oid_secc
       AND ztad.terr_oid_terr = terr.oid_terr
       AND zscc.zzon_oid_zona = zzon.oid_zona
       AND zzon.zorg_oid_regi = zorg.oid_regi
       --
       AND cuad.ind_acti = 1;



   TYPE interfazRec IS RECORD
       (
        codigoPais                SEG_PAIS.COD_PAIS%TYPE,
        codigoRegion              ZON_REGIO.COD_REGI%TYPE,
        codigoZona                ZON_ZONA.COD_ZONA%TYPE,
        codigoCliente             MAE_CLIEN.COD_CLIE%TYPE,
        campanhaProceso           VARCHAR2(6),
        montoVentaLbel            NUMBER(12,2),
        montoVentaEsika           NUMBER(12,2),
        montoVentaCyzone          NUMBER(12,2),
        montoVentaOtros           NUMBER(12,2),
        porcentajeVentaLbel       NUMBER(3),
        porcentajeVentaEsika      NUMBER(3),
        porcentajeVentaCyzone     NUMBER(3),
        porcentajeVentaOtros      NUMBER(3),
        indicadorActivo           VARCHAR2(1)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

   lnIdPeriodo         NUMBER;


BEGIN

    lnIdPeriodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);-- id del periodo consultante

    lbAbrirUtlFile:= TRUE;
    /* Generando Archivo de Texto (Detalle) */
        OPEN c_interfaz (lnIdPeriodo);
            LOOP
               FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                                   /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                   GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                   lbAbrirUtlFile := FALSE;
                 END IF;

                   IF interfazRecord.COUNT > 0 THEN
                      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                          lsLinea :=  interfazRecord(x).codigoPais       ||';'||
                                      interfazRecord(x).codigoRegion     ||';'||
                                      interfazRecord(x).codigoZona       ||';'||
                                      interfazRecord(x).codigoCliente    ||';'||
                                      interfazRecord(x).campanhaProceso  ||';'||
                                      TO_CHAR(interfazRecord(x).montoVentaLbel,'999999999990.99')   ||';'||
                                      TO_CHAR(interfazRecord(x).montoVentaEsika,'999999999990.99')  ||';'||
                                      TO_CHAR(interfazRecord(x).montoVentaCyzone,'999999999990.99') ||';'||
                                      TO_CHAR(interfazRecord(x).montoVentaOtros,'999999999990.99')  ||';'||
                                      TO_CHAR(interfazRecord(x).porcentajeVentaLbel,'990')   ||';'||
                                      TO_CHAR(interfazRecord(x).porcentajeVentaEsika,'990')  ||';'||
                                      TO_CHAR(interfazRecord(x).porcentajeVentaCyzone,'990') ||';'||
                                      TO_CHAR(interfazRecord(x).porcentajeVentaOtros,'990')  ||';'||
                                      interfazRecord(x).indicadorActivo;

                          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                      END LOOP;
                   END IF;
               EXIT WHEN c_interfaz%NOTFOUND;
            END LOOP;
        CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_VENTA_MARCA: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_VENTA_MARCA;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Indicadores GGZZ(AVI-16)
Fecha Creacion    : 25/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_INDIC_GGZZ
  (psCodigoPais           VARCHAR2,
   psCodigoMarca           VARCHAR2,
   psCodigoCanal           VARCHAR2,
   psCodigoSistema           VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo           VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo           VARCHAR2)
IS
   CURSOR c_interfaz(vnIdPeriodo NUMBER) IS

 SELECT BAS.Cod_Pais cod_Pais,
        UAS.Cod_Regi cod_Regi,
        UAS.Cod_Zona cod_Zona,
        BAS.Cod_Peri cod_Peri,
        NVL(esti.Num_Pedi,0) num_Pedi_Esti,
        NVL(vent.Num_Pedi,0) num_Pedi_Real,
        CASE WHEN ( NVL(vent.Num_Pedi,0) / DECODE(esti.Num_Pedi,NULL,1,0,1,esti.Num_Pedi) ) >= 10 THEN 999
             ELSE ROUND(( NVL(vent.Num_Pedi,0) / DECODE(esti.Num_Pedi,NULL,1,0,1,esti.Num_Pedi) ) * 100,0)
        END pct_Num_Pedi_Logr,
        NVL(esti.Val_Vent_Neta_Esta,0) Mto_Pedi_Esti,
        NVL(vent.Imp_Vent_Neta_Esta,0) Mto_Pedi_Real,
        CASE WHEN ( NVL(vent.Imp_Vent_Neta_Esta,0) / DECODE(esti.Val_Vent_Neta_Esta,NULL,1,0,1,esti.Val_Vent_Neta_Esta) ) >= 10 THEN 999
             ELSE ROUND(( NVL(vent.Imp_Vent_Neta_Esta,0) / DECODE(esti.Val_Vent_Neta_Esta,NULL,1,0,1,esti.Val_Vent_Neta_Esta) ) * 100,0)
        END pct_Imp_Pedi_Logr,
        NVL(esti.Num_Ingr,0) num_Ingr_Esti,
        NVL(pedi.Num_Ingr,0) num_Ingr_Real,
        CASE WHEN ( NVL(pedi.Num_Ingr,0) / DECODE(esti.Num_Ingr,NULL,1,0,1,esti.Num_Ingr) ) >= 10 THEN 999
             ELSE ROUND(( NVL(pedi.Num_Ingr,0) / DECODE(esti.Num_Ingr,NULL,1,0,1,esti.Num_Ingr) ) * 100,0)
        END pct_Num_Ingr_Logr,
        NVL(vent.Imp_Vent_Neta_Esta,0) imp_Vent_Neta,
        NVL(pedi.Num_Acti_Fina,0) num_Acti_Fina,
        CASE WHEN ( NVL(vent.Num_Pedi,0) / NVL(DECODE(pedi.Num_Acti_Fina,NULL,1,0,1,pedi.Num_Acti_Fina),1) ) >= 10 THEN 999
             ELSE ROUND(( NVL(vent.Num_Pedi,0) / DECODE(pedi.Num_Acti_Fina,NULL,1,0,1,pedi.Num_Acti_Fina) ) * 100,0)
        END pct_Acti,
        NVL(pedi.Num_Rein,0) Num_Rein,
        NVL(pedi.Num_Egre,0) Num_Egre,
        NVL(pedi.Num_Ingr,0) + NVL(pedi.Num_Rein,0) - NVL(pedi.Num_Egre,0) val_Capi,
        CASE WHEN ( NVL(acum.Num_Egre,0) / ( NVL(DECODE(acum.Num_Acti_Inic,NULL,1,0,1,acum.Num_Acti_Inic),1) / NVL(DECODE(acum.num_camps,NULL,1,0,1,acum.num_camps),1) ) ) >= 10 THEN 999
             ELSE ROUND(( NVL(acum.Num_Egre,0) / ( NVL(DECODE(acum.Num_Acti_Inic,NULL,1,0,1,acum.Num_Acti_Inic),1) / NVL(DECODE(acum.num_camps,NULL,1,0,1,acum.num_camps),1) ) ) * 100,0)
        END pct_Rota,
        NULL pct_Rete,
        1 Ind_Acti
   FROM (
         SELECT fvps.perd_oid_peri, fvps.zorg_oid_regi, fvps.zzon_oid_zona
           FROM int_fuent_venta_previ_sap fvps
          WHERE fvps.perd_oid_peri = vnIdPeriodo
          GROUP BY fvps.perd_oid_peri, fvps.zorg_oid_regi, fvps.zzon_oid_zona
         UNION
         SELECT fvra.perd_oid_peri, fvra.zorg_oid_regi, fvra.zzon_oid_zona
           FROM int_fuent_venta_real_vacum fvra
          WHERE fvra.perd_oid_peri = vnIdPeriodo
          GROUP BY fvra.perd_oid_peri, fvra.zorg_oid_regi, fvra.zzon_oid_zona
         UNION
         SELECT fvrl.perd_oid_peri, fvrl.zorg_oid_regi, fvrl.zzon_oid_zona
           FROM int_fuent_ventas_real fvrl
          WHERE fvrl.perd_oid_peri = vnIdPeriodo
          GROUP BY fvrl.perd_oid_peri, fvrl.zorg_oid_regi, fvrl.zzon_oid_zona
        ) base,
        (
         SELECT   pais.oid_pais, zorg.oid_regi, zzon.oid_zona,
                  pais.cod_pais, zorg.cod_regi, zzon.cod_zona
             FROM zon_terri_admin ztad,
                  seg_pais pais,
                  zon_secci zscc,
                  zon_terri terr,
                  zon_zona zzon,
                  zon_regio zorg
            WHERE ztad.pais_oid_pais = pais.oid_pais
              AND ztad.zscc_oid_secc = zscc.oid_secc
              AND ztad.terr_oid_terr = terr.oid_terr
              AND zscc.zzon_oid_zona = zzon.oid_zona
              AND zzon.zorg_oid_regi = zorg.oid_regi
         GROUP BY pais.oid_pais, zorg.oid_regi, zzon.oid_zona,
                  pais.cod_pais, zorg.cod_regi, zzon.cod_zona
        ) uas,
        (
         SELECT fvps.perd_oid_peri, fvps.zorg_oid_regi, fvps.zzon_oid_zona, SUM(NVL(fvps.Num_Pedi,0)) Num_Pedi,
                SUM(NVL(fvps.Num_Ingr,0)) Num_Ingr, SUM(NVL(val_vent_neta_esta,0)) val_vent_neta_esta
           FROM int_fuent_venta_previ_sap fvps
          WHERE fvps.perd_oid_peri = vnIdPeriodo
          GROUP BY fvps.perd_oid_peri, fvps.zorg_oid_regi, fvps.zzon_oid_zona
        ) esti,
        (
         SELECT fvra.perd_oid_peri, fvra.zorg_oid_regi, fvra.zzon_oid_zona, SUM(NVL(fvra.Num_Pedi,0)) Num_Pedi,
                SUM(NVL(fvra.Imp_Vent_Neta_Esta,0)) Imp_Vent_Neta_Esta
           FROM int_fuent_venta_real_vacum fvra
          WHERE perd_oid_peri = vnIdPeriodo
          GROUP BY fvra.perd_oid_peri, fvra.zorg_oid_regi, fvra.zzon_oid_zona
        ) vent,
        (
         SELECT fvrl.perd_oid_peri, fvrl.zorg_oid_regi, fvrl.zzon_oid_zona, SUM(NVL(fvrl.Num_Ingr,0)) Num_Ingr,
                SUM(NVL(fvrl.Num_Rein,0)) Num_Rein, SUM(NVL(Num_Egre,0)) Num_Egre, SUM(NVL(Num_Acti_Fina,0)) Num_Acti_Fina
           FROM int_fuent_ventas_real fvrl
          WHERE fvrl.perd_oid_peri = vnIdPeriodo
          GROUP BY fvrl.perd_oid_peri, fvrl.zorg_oid_regi, fvrl.zzon_oid_zona
        ) pedi,
        (
         SELECT fvrl.zorg_oid_regi, fvrl.zzon_oid_zona,
                SUM(NVL(Num_Egre,0)) Num_Egre, SUM(NVL(Num_Acti_Inic,0)) Num_Acti_Inic,
                COUNT(DISTINCT fvrl.perd_oid_peri) Num_Camps
           FROM int_fuent_ventas_real fvrl
          WHERE fvrl.perd_oid_peri >= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( SUBSTR(psCodigoPeriodo,1,4)||'01')
            AND fvrl.perd_oid_peri <= vnIdPeriodo
          GROUP BY fvrl.zorg_oid_regi, fvrl.zzon_oid_zona
        ) acum,
        ( SELECT cod_pais, cod_peri, cod_cana FROM bas_ctrl_fact WHERE cod_peri = psCodigoPeriodo ) bas
  WHERE base.perd_oid_peri = esti.perd_oid_peri(+)
    AND base.zorg_oid_regi = esti.zorg_oid_regi(+)
    AND base.zzon_oid_zona = esti.zzon_oid_zona(+)
    --
    AND base.perd_oid_peri = vent.perd_oid_peri(+)
    AND base.zorg_oid_regi = vent.zorg_oid_regi(+)
    AND base.zzon_oid_zona = vent.zzon_oid_zona(+)
    --
    AND base.perd_oid_peri = pedi.perd_oid_peri(+)
    AND base.zorg_oid_regi = pedi.zorg_oid_regi(+)
    AND base.zzon_oid_zona = pedi.zzon_oid_zona(+)
    --
    AND base.zorg_oid_regi = uas.oid_regi(+)
    AND base.zzon_oid_zona = uas.oid_zona(+)
    --
    AND base.zorg_oid_regi = acum.zorg_oid_regi(+)
    AND base.zzon_oid_zona = acum.zzon_oid_zona(+);

   TYPE interfazRec IS RECORD
    (
      cod_Pais   SEG_PAIS.COD_PAIS%TYPE,
      cod_Regi   ZON_REGIO.COD_REGI%TYPE,
      cod_Zona   ZON_ZONA.COD_ZONA%TYPE,
      cod_Peri   SEG_PERIO_CORPO.COD_PERI%TYPE,
      num_Pedi_Esti  NUMBER(7),
      num_Pedi_Real  NUMBER(7),
      pct_num_pedi_logr NUMBER(3),
      mto_pedi_esti  NUMBER(12,2),
      mto_pedi_real  NUMBER(12,2),
      pct_imp_pedi_logr NUMBER(3),
      num_ingr_esti  NUMBER(7),
      num_ingr_real  NUMBER(7),
      pct_num_ingr_logr NUMBER(3),
      imp_vent_neta  NUMBER(12,2),
      num_acti_fina  NUMBER(7),
      pct_acti   NUMBER(3),
      num_rein   NUMBER(12),
      num_egre   NUMBER(12),
      val_capi   NUMBER(12),
      pct_rota   NUMBER(3),
      pct_rete   NUMBER(3),
      ind_acti   NUMBER(1)
    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

   lnIdPeriodo         NUMBER;
BEGIN
    lnIdPeriodo:= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCodigoPeriodo );
    lbAbrirUtlFile:= TRUE;

    /* Generando Archivo de Texto (Detalle) */
        OPEN c_interfaz (lnIdPeriodo);
         LOOP
            FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                           psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                END IF;

             IF interfazRecord.COUNT > 0 THEN
                FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                    lsLinea :=  interfazRecord(x).cod_pais   ||';'||
                                interfazRecord(x).cod_regi   ||';'||
                                interfazRecord(x).cod_zona   ||';'||
                                interfazRecord(x).cod_peri   ||';'||
                                TO_CHAR(interfazRecord(x).num_pedi_esti,'9999990')  ||';'||
                                TO_CHAR(interfazRecord(x).num_pedi_real,'9999990')  ||';'||
                                TO_CHAR(interfazRecord(x).pct_num_pedi_logr,'990') ||';'||
                                TO_CHAR(interfazRecord(x).mto_pedi_esti,'999999999990.99')  ||';'||
                                TO_CHAR(interfazRecord(x).mto_pedi_real,'999999999990.99')  ||';'||
                                TO_CHAR(interfazRecord(x).pct_imp_pedi_logr,'990') ||';'||
                                TO_CHAR(interfazRecord(x).num_ingr_esti,'999999999999990')  ||';'||
                                TO_CHAR(interfazRecord(x).num_ingr_real,'999999999999990')  ||';'||
                                TO_CHAR(interfazRecord(x).pct_num_ingr_logr,'990') ||';'||
                                TO_CHAR(interfazRecord(x).imp_vent_neta,'999999999990.99')  ||';'||
                                TO_CHAR(interfazRecord(x).num_acti_fina,'9999990')  ||';'||
                                TO_CHAR(interfazRecord(x).pct_acti,'990')   ||';'||
                                TO_CHAR(interfazRecord(x).num_rein,'9999990')   ||';'||
                                TO_CHAR(interfazRecord(x).num_egre,'9999990')   ||';'||
                                TO_CHAR(interfazRecord(x).val_capi,'9999990')   ||';'||
                                TO_CHAR(interfazRecord(x).pct_rota,'990')   ||';'||
                                TO_CHAR(interfazRecord(x).pct_rete,'990')   ||';'||
                                interfazRecord(x).ind_acti;

        UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                END LOOP;
             END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
         END LOOP;
        CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
 END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_INDIC_GGZZ: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_INDIC_GGZZ;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Informacion Adicional(AVI-17)
Fecha Creacion    : 25/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_INFOR_ADICI
  (psCodigoPais           VARCHAR2,
   psCodigoMarca          VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoIso            VARCHAR2,
   psCodigoPeriodo        VARCHAR2)
  IS
CURSOR curBase( lnidperiodo NUMBER ) IS
    SELECT psCodigoPais cod_pais,
           zorg.cod_regi cod_regi,
           zzon.cod_zona cod_zona,
           clie.cod_clie cod_clie,
        CASE
           -- si la campaña de 1er ped de la consultora es igual a la campaña de proceso y tiene pedido en esta campaña.
              WHEN cprc.perd_oid_peri = cier.perd_oid_peri OR
                   ( clda.esta_oid_esta_clie IN (1,2,7,8) AND ulti.clie_oid_clie IS NOT NULL )THEN
                '1' -- visita de meta
              WHEN cprc.perd_oid_peri = fin_pkg_gener.fin_fn_obtie_oid_perio( gen_fn_calcu_perio( cier.cod_peri, -1 ) ) THEN
                '2'  -- visita de acompañamiento 1
              WHEN cprc.perd_oid_peri = fin_pkg_gener.fin_fn_obtie_oid_perio( gen_fn_calcu_perio( cier.cod_peri, -2 ) ) AND
                   clda.num_camp_sin_pedi = 0 THEN
                '3'  -- visita de acompañamiento 2
              WHEN cprc.perd_oid_peri = fin_pkg_gener.fin_fn_obtie_oid_perio( gen_fn_calcu_perio( cier.cod_peri, -3 ) ) AND
                   clda.num_camp_sin_pedi = 0 THEN
                '4'  -- visita felicitaciones
              WHEN (cprc.perd_oid_peri = fin_pkg_gener.fin_fn_obtie_oid_perio( gen_fn_calcu_perio( cier.cod_peri, -3 ) ) AND
                    clda.num_camp_sin_pedi >= 2 OR cier.cod_peri < (SELECT nvcp.cmp_inic_prog FROM nvs_param_logro nvcp )) THEN
                '0'  -- no le corresponde visita
              ELSE '5'  -- visita establecida
            END tipo_clas_meto,
           ( SELECT CASE WHEN ( GEN_PKG_GENER.GEN_FN_CLIEN_BLOQU( clie.cod_clie, '01' ) = 1 OR
                                GEN_PKG_GENER.GEN_FN_CLIEN_BLOQU( clie.cod_clie, '02' ) = 1 ) OR
                                clda.ind_acti = 0 THEN 0
               ELSE 1
                    END
               FROM DUAL ) auto_pase_pedi,
           NVL( ulti.val_mont_tota_campa_actu, 0 ) mont_vent_camp,
           NVL((
                SELECT SUM( soca.val_gana_tota_loca )
                  FROM ped_solic_cabec soca,
                       ped_solic_cabec cons,
                       ped_tipo_solic_pais tspa,
                       own_comun.ped_tipo_solic tsol
                 WHERE soca.soca_oid_soli_cabe = cons.oid_soli_cabe(+)
                   AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   --
                   AND soca.perd_oid_peri = ulti.perd_oid_peri -- oid de la ultima facturación de la consultora
                   AND tsol.cod_tipo_soli = 'SOC' -- Tipo de solicitud OC
                   AND soca.ind_oc = 1 -- Indicador de OC = 1
                   AND cons.esso_oid_esta_soli <> 4 -- No anulados
                   AND soca.grpr_oid_grup_proc = 5 -- Facturados/Procesados
                   AND soca.clie_oid_clie = ulti.clie_oid_clie
               ),0) gana_ulti_camp,
           NVL( triu.flagasiste, 0 ) asis_reun_triu,
            0 asis_tall_nuev,
            1 activo,
           clda.fec_naci fechaNacimiento,
        CASE
               WHEN poli.est_poli IS NULL OR poli.est_poli = '1' THEN 0
           ELSE 1
            END inscritaSeguro,
            CASE
               WHEN poli.est_poli IN ('3') THEN 1
               ELSE 0
            END cubiertaSeguro
       FROM mae_clien clie,
            mae_clien_datos_adici clda,
            mae_clien_prime_conta cprc,
           mae_clien_unida_admin cuad,
           zon_terri_admin ztad,
           zon_secci zscc,
           zon_zona zzon,
           zon_regio zorg,
        (
             SELECT cod_clie,
                    MAX( CASE WHEN UPPER( cod_acce ) IN ( 'S','A' ) THEN 1 ELSE 0 END ) flagAsiste
               FROM int_solic_pedid_detal
              WHERE cod_peri = pscodigoperiodo
              GROUP BY cod_clie
           ) triu,
        (
             SELECT zorg_oid_regi,
                    MAX( perd_oid_peri ) perd_oid_peri,
                     fin_pkg_gener.fin_fn_obtie_codig_perio( MAX( perd_oid_peri ) ) cod_peri
                FROM fac_contr_cierr
              WHERE tcie_oid_tipo_cier = 1 -- Cierre de región
                AND perd_oid_peri <= lnidperiodo
               GROUP BY zorg_oid_regi
            ) cier,
         (
            SELECT p.clie_oid_clie,
                   p.est_poli
               FROM sgr_famse_poliz_regis p
              WHERE p.fec_soli = (
                                  SELECT MAX( p1.fec_soli )
                                    FROM sgr_famse_poliz_regis p1
                                   WHERE p1.clie_oid_clie = p.clie_oid_clie
                                     AND p1.est_regi <> '9'
                                     AND p1.cam_regi <= pscodigoperiodo
        )
                AND P.EST_REGI <> '9'
           ) poli,
           (
             SELECT sca2.clie_oid_clie,
                    SUM( CASE WHEN sca2.perd_oid_peri = lnidperiodo THEN sca2.val_mont_tota ELSE 0 END ) val_mont_tota_campa_actu,
                    MAX( sca2.perd_oid_peri ) perd_oid_peri
               FROM ped_solic_cabec_acum2 sca2,
                    mae_clien_datos_adici clda
              WHERE sca2.clie_oid_clie = clda.clie_oid_clie
                AND clda.ind_acti = 1
              GROUP BY sca2.clie_oid_clie
           ) ulti
     WHERE ulti.clie_oid_clie = clie.oid_clie
       AND clie.oid_clie = clda.clie_oid_clie
       AND clie.oid_clie = cprc.clie_oid_clie(+)
       AND clie.oid_clie = poli.clie_oid_clie(+)
       AND clie.cod_clie = triu.cod_clie(+)
       --
       AND clie.oid_clie = cuad.clie_oid_clie
       AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
       AND ztad.zscc_oid_secc = zscc.oid_secc
       AND zscc.zzon_oid_zona = zzon.oid_zona
       AND zzon.zorg_oid_regi = zorg.oid_regi
       --
       AND zorg.oid_regi = cier.zorg_oid_regi(+)
       AND cuad.ind_acti = 1
       AND NOT ( clda.esta_oid_esta_clie IN (1,7) AND ulti.perd_oid_peri != lnidperiodo )
      ;

TYPE tabBase IS TABLE OF curBase%ROWTYPE INDEX BY BINARY_INTEGER;
regBase tabBase;

-- Variables
lsDirTempo BAS_INTER.dir_temp%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
lbAbrirUtlFile BOOLEAN;
ln_sqlcode NUMBER(10);
ls_sqlerrm VARCHAR2(1500);
   lsNombreArchivo     VARCHAR2(50);
--
numLimitRows NUMBER := 5000;
lnidperiodo CRA_PERIO.Oid_Peri%TYPE;
strLinea VARCHAR2(1000);
--
BEGIN
  --
  lnidperiodo := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( psCodigoPeriodo );
    lbAbrirUtlFile:= TRUE;
  --
  OPEN curBase( lnidperiodo );
  LOOP
    FETCH curBase BULK COLLECT INTO regBase LIMIT numLimitRows;

    -- Procedimiento inicial para generar la interfaz
                 IF lbAbrirUtlFile THEN
       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER( psCodigoPais,
                                               psCodigosistema,
                                               psCodigoInterfaz,
                                               psNombreArchivo,
                                               lsDirTempo,
                                               lsNombreArchivo,
                                               V_HANDLE );
                   lbAbrirUtlFile := FALSE;
                 END IF;

    FOR x IN regBase.FIRST .. regBase.LAST LOOP
        -- Generar linea de interfaz
        strLinea :=  regBase(x).cod_pais   ||';'||
                     regBase(x).cod_regi   ||';'||
                     regBase(x).cod_zona   ||';'||
                     regBase(x).COD_CLIE   ||';'||
                     regBase(x).tipo_clas_meto  ||';'||
                     regBase(x).auto_pase_pedi  ||';'||
                     RPAD(TRIM(TO_CHAR(regBase(x).mont_vent_camp,'999999999990.00')),15,' ') ||';'||
                     RPAD(TRIM(TO_CHAR(regBase(x).gana_ulti_camp,'999999999990.00')),15,' ') ||';'||
                     regBase(x).asis_reun_triu  ||';'||
                     regBase(x).asis_tall_nuev ||';'||
                     regBase(x).activo ||';'||
                                CASE
                        WHEN regBase(x).fechaNacimiento IS NULL THEN '19000101'
                        ELSE TO_CHAR( regBase(x).fechaNacimiento, 'YYYYMMDD' )
                                END ||';'||
                     regBase(x).inscritaSeguro ||';'||
                     regBase(x).cubiertaSeguro;

        -- Inserta linea de texto en archivo
        UTL_FILE.PUT_LINE (V_HANDLE, strLinea );
                END LOOP;
    EXIT WHEN curBase%NOTFOUND;
         END LOOP;
  CLOSE curBase;

  /* Procedimiento final para generar interfaz */
    IF NOT lbAbrirUtlFile THEN
     UTL_FILE.FCLOSE(V_HANDLE);
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
 END IF;
EXCEPTION WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'INT_PR_AVI_ENVIO_INFOR_ADICI: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_INFOR_ADICI;

/***************************************************************************************
Descripcion       : Proceso que encraga d evalidar la informacion por tipod e recepcion
                    tipoRecepcion 0: AVI-18 1:AVI:19
Fecha Creacion    : 25/02/2011
Autor             :  Sergio Buchelli
Parametros:
   psCodigoCliente          Codigo Cliente
   psCodigoTipoLogro        Codigo Tipo Logro
   psTelefono01             Telefono Fijo
   psTelefono02             Telefono Movil
   psTelefono03             Telefono trabajo
   psMail                   Mail
   psTipoRecepcion          Tipo Recepcion,
   psCampanhaInicio         Campanha inicio,
   psCampanhaFin            Campanha Fin,
   psCodigoError            Codigo error
****************************************************************************************/
PROCEDURE INT_PR_AVI_VALID_INFO_RECEP
  (psCodigoPais             VARCHAR2,
   psCodigoCliente          VARCHAR2,
   psCodigoTipoLogro        VARCHAR2,
   psTelefono01             VARCHAR2,
   psTelefono02             VARCHAR2,
   psTelefono03             VARCHAR2,
   psMail                   VARCHAR2,
   psFechaActMovil          VARCHAR2,
   psTipoRecepcion          VARCHAR2,
   psCampanhaInicio         OUT VARCHAR2,
   psCampanhaFin            OUT VARCHAR2,
   psCodigoError            OUT VARCHAR2)
  IS
   lnCont         NUMBER;
   lnOidClient    NUMBER;
   lnOidPeriodo   NUMBER;
   lnoidPais      NUMBER;
   lnoidMarca     NUMBER;
   LNOIDCANAL     NUMBER;
   LSPERIODOPRIMERPEDIDO SEG_PERIO_CORPO.COD_PERI%TYPE;
   lsCodigoPeriodo SEG_PERIO_CORPO.COD_PERI%TYPE;
   lsPeriodoFinal SEG_PERIO_CORPO.COD_PERI%TYPE;
   lsValText    VARCHAR2(100);
   lsIndicadorPrincipal VARCHAR2(1);

    CURSOR cursTipoComunicacion IS
       SELECT COD_TIPO_COMU,OID_TIPO_COMU
       FROM MAE_TIPO_COMUN
       WHERE COD_TIPO_COMU IN ('TF','TT','TM','ML');

BEGIN
    PSCODIGOERROR:='10';--ok
    lnOidClient  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(psCodigoCliente);
    lnoidPais    := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    LNOIDMARCA   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    LNOIDCANAL   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

    SELECT COD_PERI INTO LSCODIGOPERIODO
      FROM bas_ctrl_fact WHERE ind_camp_act = 1;

    LNOIDPERIODO := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(LSCODIGOPERIODO);

    if(psTipoRecepcion='0') then

             --verificamos tipo logro
             SELECT COUNT(1) INTO lnCont
               FROM NVS_TIPO_LOGRO
              WHERE COD_TIPO_LOGR = psCodigoTipoLogro;

             IF(LNCONT=0) THEN
                psCodigoError:='0';
              return;
             end if;

             --verificacon cod consultora
             SELECT COUNT(1) into lnCont
             FROM  MAE_CLIEN
             WHERE COD_CLIE = psCodigoCliente;

             if(lnCont=0) then
              psCodigoError:='1';
              return ;
             end if;

             -- Asignación de Periodo Inicio y Fin de Consultora
             SELECT CASE WHEN NVPA.IND_NUEV = 1 THEN
                         CASE
                            -- SI ES REGISTRADA O RETIRADA Y PASA PEDIDO EN CAMPAÑA, PERIODO ES IGUAL A CAMPAÑA FACTURACION
                            WHEN CLDA.ESTA_OID_ESTA_CLIE IN (1,7) AND CAMP.PERD_OID_PERI IS NOT NULL THEN lsCodigoPeriodo
                            -- SI ES NUEVA O REACTIVADA, VERIFICA SI TUVO PEDIDO EN CAMPAÑA ANTERIOR O EN CAMPAÑA ACTUAL
                            -- DE OTRO MODO CAMPAÑA DE INICIO LO TOMA DE PRIMER CONTACTO
                            WHEN CLDA.ESTA_OID_ESTA_CLIE IN (2,8) THEN
                                 CASE WHEN SOC2.PERD_OID_PERI IS NOT NULL THEN FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( SOC2.PERD_OID_PERI )
                                      WHEN CAMP.PERD_OID_PERI IS NOT NULL THEN FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( CAMP.PERD_OID_PERI )
                                      WHEN CPRC.PERD_OID_PERI IS NOT NULL AND
                                           CPRC.PERD_OID_PERI >= FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( NVPA.CMP_INIC_PROG, -1 ) ) THEN
                                           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( CPRC.PERD_OID_PERI )
                                 ELSE NULL
                                 END
                            -- SI ES UNA PEG, CAMPAÑA LA TOMA DE PRIMER CONTACTO
                         ELSE
                            CASE WHEN CPRC.PERD_OID_PERI >= FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( lsCodigoPeriodo, -1 ) ) THEN
                                      FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( CPRC.PERD_OID_PERI )
                            ELSE NULL
                            END
                         END
                    ELSE
                       LSCODIGOPERIODO
                    END INTO lsPeriodoPrimerPedido
               FROM MAE_CLIEN CLIE,
                    MAE_CLIEN_DATOS_ADICI CLDA,
                    MAE_CLIEN_PRIME_CONTA CPRC,
                    NVS_PARAM_LOGRO NVPA,
                   (
                    SELECT SOCA.CLIE_OID_CLIE, SOCA.PERD_OID_PERI
                      FROM PED_SOLIC_CABEC SOCA,
                           PED_TIPO_SOLIC_PAIS TSPA,
                           PED_TIPO_SOLIC TSOL
                     WHERE SOCA.TSPA_OID_TIPO_SOLI_PAIS = TSPA.OID_TIPO_SOLI_PAIS
                       AND TSPA.TSOL_OID_TIPO_SOLI = TSOL.OID_TIPO_SOLI
                       --
                       AND TSOL.COD_TIPO_SOLI = 'SOC'
                       AND SOCA.IND_OC = 1
                       AND SOCA.ESSO_OID_ESTA_SOLI <> 4
                       AND SOCA.MODU_OID_MODU NOT IN (13,15)
                       AND SOCA.PERD_OID_PERI = lnOidPeriodo
                     GROUP BY SOCA.CLIE_OID_CLIE, SOCA.PERD_OID_PERI
                   ) CAMP,
                   ( SELECT *
                       FROM PED_SOLIC_CABEC_ACUM2
                      WHERE PERD_OID_PERI = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( lsCodigoPeriodo, -1 ) ) ) SOC2
              WHERE CLIE.OID_CLIE = CLDA.CLIE_OID_CLIE(+)
                AND CLIE.OID_CLIE = CPRC.CLIE_OID_CLIE(+)
                AND CLIE.OID_CLIE = CAMP.CLIE_OID_CLIE(+)
                AND CLIE.OID_CLIE = SOC2.CLIE_OID_CLIE(+)
                --
                AND NVPA.COD_PAIS = psCodigoPais
                AND CLIE.COD_CLIE = psCodigoCliente
                ;

             IF LSPERIODOPRIMERPEDIDO IS NOT NULL THEN
                SELECT GEN_FN_CALCU_PERIO( lsPeriodoPrimerPedido, 3 )
                  INTO LSPERIODOFINAL
                  FROM DUAL;
             ELSE
                psCodigoError:='1'; -- CREAR NUEVO CODIGO DE ERROR EN SIGUIENTE REQUERIMIENTO CON MODIFICACION DE DECU
                return;
             END IF;

             -- Verificación de traslapamiento de campañas
             SELECT COUNT(1) INTO LNCONT
               FROM NVS_CONSU_LOGRO
              WHERE EST_LOGR = '1' -- Nuevo
                AND COD_PAIS= PSCODIGOPAIS
                AND COD_CLIE= PSCODIGOCLIENTE
                AND ( ( TO_NUMBER(lsPeriodoPrimerPedido) >= TO_NUMBER(CMP_INIC) AND TO_NUMBER(lsPeriodoPrimerPedido) <= TO_NUMBER(CMP_FINA) ) OR
                      ( TO_NUMBER(lsPeriodoFinal) >= TO_NUMBER(CMP_INIC) AND TO_NUMBER(lsPeriodoFinal) <= TO_NUMBER(CMP_FINA) )
                    );

             if (lnCont=0) then --no existe se devuelve campanha de inicio y fin

               PSCAMPANHAINICIO:= lsPeriodoPrimerPedido;
               psCampanhaFin   := lsPeriodoFinal;
             else
              -- existe registros se retorna codigo error
                psCodigoError:='2';
                return;

             end if;


    end if;--fin de recpcion AVI-18

    if(psTipoRecepcion='1') then

             --verificacon cod consultora
             SELECT COUNT(1) into lnCont
             FROM  MAE_CLIEN
             WHERE COD_CLIE = psCodigoCliente;

             if(lnCont=0) then
              psCodigoError:='1';
              return ;
             end if;


             --INSERTAMOS  O ACTUALIZAMOS TIPO DE COMUNICACION TF ,TM, TT, MAIL

             FOR ctipoComunicacion IN cursTipoComunicacion LOOP

                 if(ctipoComunicacion.COD_TIPO_COMU = 'TF') then
                    lsValText := psTelefono01;
                    lsIndicadorPrincipal:='1';
                 end if;

                 if(ctipoComunicacion.COD_TIPO_COMU = 'TM') then
                    lsValText := psTelefono02;
                    --lsIndicadorPrincipal:='0';
                    SELECT COUNT(1)
                      into lnCont
                      FROM MAE_CLIEN_COMUN mc
                     WHERE mc.clie_oid_clie = lnOidClient
                       AND mc.ticm_oid_tipo_comu in
                           (SELECT OID_TIPO_COMU
                              FROM MAE_TIPO_COMUN
                             WHERE COD_TIPO_COMU IN ('TF'));

                    if (psTelefono02 is not null
                      and psTelefono01 is null
                      and lnCont = 0) then
                       lsIndicadorPrincipal:='1';
                    else
                    lsIndicadorPrincipal:='0';
                 end if;
                 end if;

                 if(ctipoComunicacion.COD_TIPO_COMU = 'ML') then
                    lsValText := psMail;
                    --lsIndicadorPrincipal:='0';
                    SELECT COUNT(1)
                      into lnCont
                      FROM MAE_CLIEN_COMUN mc
                     WHERE mc.clie_oid_clie = lnOidClient
                       AND mc.ticm_oid_tipo_comu in
                           (SELECT OID_TIPO_COMU
                              FROM MAE_TIPO_COMUN
                             WHERE COD_TIPO_COMU IN ('TF', 'TM'));

                    if (psMail is not null
                      and psTelefono01 is null
                      and psTelefono02 is null
                      and lnCont = 0) then
                       lsIndicadorPrincipal:='1';
                    else
                       lsIndicadorPrincipal:='0';
                    end if;
                 end if;

                 if(ctipoComunicacion.COD_TIPO_COMU = 'TT') then
                    lsValText := psTelefono03;
                    --lsIndicadorPrincipal:='0';
                    SELECT COUNT(1)
                      into lnCont
                      FROM MAE_CLIEN_COMUN mc
                     WHERE mc.clie_oid_clie = lnOidClient
                       AND mc.ticm_oid_tipo_comu in
                           (SELECT OID_TIPO_COMU
                              FROM MAE_TIPO_COMUN
                             WHERE COD_TIPO_COMU IN ('TF', 'TM', 'ML'));

                    if (psTelefono03 is not null
                      and psTelefono01 is null
                      and psTelefono02 is null
                      and psMail is null
                      and lnCont = 0) then
                       lsIndicadorPrincipal:='1';
                    else
                    lsIndicadorPrincipal:='0';
                 end if;
                 end if;


                 if (lsValText is not null
                     and lsIndicadorPrincipal = '1') then
                    UPDATE MAE_CLIEN_COMUN
                       SET FEC_ULTI_ACTU = TO_DATE(psFechaActMovil,'yyyyMMddHH24MI'),
                           IND_COMU_PPAL = '0'
                     WHERE CLIE_OID_CLIE = lnOidClient
                       AND TICM_OID_TIPO_COMU <>
                           ctipoComunicacion.OID_TIPO_COMU;
                 end if;


                  BEGIN

                  if (lsValText is not null) then
                     INSERT INTO MAE_CLIEN_COMUN
                        (OID_CLIE_COMU,
                         CLIE_OID_CLIE,
                         TICM_OID_TIPO_COMU,
                         VAL_TEXT_COMU,
                         IND_COMU_PPAL,
                         FEC_ULTI_ACTU
                        )
                    VALUES
                        (MAE_CLCO_SEQ.NEXTVAL,
                         lnOidClient,
                         ctipoComunicacion.OID_TIPO_COMU,
                         lsValText,
                         lsIndicadorPrincipal,
                         TO_DATE(psFechaActMovil,'yyyyMMddHH24MI')
                        );
                  end if;

                  EXCEPTION
                   WHEN OTHERS THEN

                    UPDATE  MAE_CLIEN_COMUN
                    SET VAL_TEXT_COMU = lsValText,
                           FEC_ULTI_ACTU = TO_DATE(psFechaActMovil,'yyyyMMddHH24MI'),
                           IND_COMU_PPAL = lsIndicadorPrincipal
                    WHERE CLIE_OID_CLIE = lnOidClient
                        AND  TICM_OID_TIPO_COMU = ctipoComunicacion.OID_TIPO_COMU;

                  END;

             END LOOP;


    end if;--FIN RECEOCION AVI-19

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_VALID_INFO_RECEP: '||ls_sqlerrm);
END INT_PR_AVI_VALID_INFO_RECEP;

/***********************************************************************
Descripcion       : Genera Interfaz de Envio Tipos Logro  (AVI-20)
Fecha Creacion    : 25/07/2011
Autor             :
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_TIPOS_LOGRO
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2)
IS
   CURSOR c_interfaz IS

        SELECT psCodigoPais codigoPais,
               ntl.cod_tipo_logr codigoTipoLogro,
               ntl.des_tipo_logr descripcionTipoLogro,
               ntl.imp_mini_logr importeMinimoLogro,
               ntl.imp_maxi_logr importeMaximoLogro,
               (CASE WHEN ntl.est_regi = '1' THEN 1 ELSE 0 END) indicadorActivo
          FROM nvs_tipo_logro ntl;

   TYPE interfazRec IS RECORD
       (
        codigoPais                VARCHAR2(3),
        codigoTipoLogro           nvs_tipo_logro.cod_tipo_logr%TYPE,
        descripcionTipoLogro      nvs_tipo_logro.des_tipo_logr%TYPE,
        importeMinimoLogro        nvs_tipo_logro.imp_mini_logr%TYPE,
        importeMaximoLogro        nvs_tipo_logro.imp_maxi_logr%TYPE,
        indicadorActivo           VARCHAR2(1)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    lbAbrirUtlFile:= TRUE;
    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
             /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
             END IF;

               IF interfazRecord.COUNT > 0 THEN
                  FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                      lsLinea :=  interfazRecord(x).codigoPais                                    ||';'||
                                  interfazRecord(x).codigoTipoLogro                               ||';'||
                                  interfazRecord(x).descripcionTipoLogro                          ||';'||
                                  TO_CHAR(interfazRecord(x).importeMinimoLogro,'999999999990.99') ||';'||
                                  TO_CHAR(interfazRecord(x).importeMaximoLogro,'999999999990.99') ||';'||
                                  interfazRecord(x).indicadorActivo;

                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                  END LOOP;
               END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);
    /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_TIPOS_LOGRO: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_TIPOS_LOGRO;



/************************************************************************
Descripcion       : Genera Interfaz de Envio Territorio  (AVI-8)
Fecha Creacion    : 08/06/2013
Autor             : Juan Gutiérrez
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Archivo
*************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_TERRI
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2)
IS
   CURSOR c_interfaz IS

          SELECT  psCodigoPais AS CODIGO_PAIS,
                  ZON.COD_ZONA AS CODIGO_ZONA, 
                  TER.COD_TERR AS CODIGO_TERRITORIO,
                  ' ' AS DESCRIPCION_TERRITORIO
            FROM ZON_TERRI_ADMIN TA,
                 ZON_SECCI SEC,
                  ZON_ZONA ZON,
                  ZON_REGIO REG,
                  ZON_TERRI TER
            WHERE TA.IND_BORR = 0 AND TA.ZSCC_OID_SECC = SEC.OID_SECC
              AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA AND ZON.ZORG_OID_REGI = REG.OID_REGI
              AND TA.TERR_OID_TERR = TER.OID_TERR
              ORDER BY REG.COD_REGI, ZON.COD_ZONA, SEC.COD_SECC, TER.COD_TERR;


   TYPE interfazRec IS RECORD
       (
        codigoPais              VARCHAR2(3),
        codigoZona              ZON_ZONA.COD_ZONA%TYPE,
        codigoTerritorio        ZON_TERRI.COD_TERR%TYPE,
        descripcionTerritorio   VARCHAR2(30)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    lbAbrirUtlFile:= TRUE;
    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
             /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
             END IF;

               IF interfazRecord.COUNT > 0 THEN
                  FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                      lsLinea :=  interfazRecord(x).codigoPais                            ||';'||
                                  interfazRecord(x).codigoZona                            ||';'||                       
                                  interfazRecord(x).codigoTerritorio                      ||';'||
                                  interfazRecord(x).descripcionTerritorio	                ;
                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                  END LOOP;
               END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);
    /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;


EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_TERRI: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_TERRI;

/**************************************************************************
Descripcion       : Envia Prefacturacion Cabecera (AVI-1)
Fecha Creacion    : 24/06/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoNuevo  :  Codigo de periodo Nuevo
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_PREFA_CABEC
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoPeriodoNuevo   VARCHAR2)
IS
   CURSOR c_interfaz IS
     SELECT psCodigoPais as CODIGO_PAIS,												
			t1.COD_REGI as CODIGO_REGION, 												
			t1.COD_ZONA as CODIGO_ZONA, 												
			t1.COD_PERI as CODIGO_PERIODO, 												
			(t1.NUM_PEDI_REAL - t1.NUM_PEDI_RECH) as PEDIDOS_GESTIONADOS_APROBADOS,												
			DECODE(NUM_PEDI_ESTI, 0, 0, round((t1.NUM_PEDI_REAL - t1.NUM_PEDI_RECH)/NUM_PEDI_ESTI*100)) as PORCENTAJE_RECEPCION,												
			t1.NUM_PRIM_PEDI  as PRIMEROS_PEDIDOS,												
			t1.NUM_PEDI_RECH as PEDIDOS_RETENIDOS, 												
			t1.NUM_PEDI_REAL as PEDIDOS_RECIBIDOS												
	        FROM INT_EVI_PERIO_REGIO_ZONA t1														
			WHERE t1.COD_PERI = ( 												
	            SELECT MAX(t2.COD_PERI) FROM INT_EVI_PERIO_REGIO_ZONA t2 														
	            WHERE t2.COD_ZONA = t1.COD_ZONA 														
	            AND t2.COD_REGI = t1.COD_REGI 														
	            AND (t2.COD_PERI = psCodigoPeriodo OR t2.COD_PERI= DECODE (psCodigoPeriodoNuevo ,NULL, t2.COD_PERI, psCodigoPeriodoNuevo) 
              ))
	       ORDER BY COD_PERI, COD_REGI, COD_ZONA	;
      
   TYPE interfazRec IS RECORD
       (
        codigoPais              VARCHAR2(3),
        codigoRegion            INT_EVI_PERIO_REGIO_ZONA.COD_REGI%TYPE,
        codigoZona              INT_EVI_PERIO_REGIO_ZONA.COD_ZONA%TYPE,
        codigoPeriodo			      INT_EVI_PERIO_REGIO_ZONA.COD_PERI%TYPE,
        pedidosGestionados			INT_EVI_PERIO_REGIO_ZONA.NUM_PEDI_ESTI%TYPE,
        porcentajeRecepcion			INT_EVI_PERIO_REGIO_ZONA.NUM_PEDI_ESTI%TYPE,
        primerosPedidos         INT_EVI_PERIO_REGIO_ZONA.NUM_PRIM_PEDI%TYPE,
        pedidosRetenidos        INT_EVI_PERIO_REGIO_ZONA.NUM_PEDI_RECH%TYPE,
        pedidosRecibidos        INT_EVI_PERIO_REGIO_ZONA.NUM_PEDI_REAL%TYPE
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    lbAbrirUtlFile:= TRUE;
    /* Generando Archivo de Texto (Detalle) */

      OPEN c_interfaz;
          LOOP
             FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
               /* Procedimiento inicial para generar interfaz */
               IF lbAbrirUtlFile THEN
                 GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                 lbAbrirUtlFile := FALSE;
               END IF;
                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                        lsLinea :=  interfazRecord(x).codigoPais                            ||';'||
                                    interfazRecord(x).codigoRegion                           ||';'||    
                                    interfazRecord(x).codigoZona                            ||';'||                       
                                    interfazRecord(x).codigoPeriodo                      ||';'||
                                    interfazRecord(x).pedidosGestionados                      ||';'||
                                    interfazRecord(x).porcentajeRecepcion                      ||';'||
                                    interfazRecord(x).primerosPedidos                      ||';'||
                                    interfazRecord(x).pedidosRetenidos                      ||';'||
                                    interfazRecord(x).pedidosRecibidos	                ;
                        UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
      CLOSE c_interfaz;
    
    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);
    /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_PREFA_CABEC: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_PREFA_CABEC;


/**************************************************************************
Descripcion       : Envia Prefacturacion Detalle (AVI-2)
Fecha Creacion    : 24/06/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoNuevo  :  Codigo de periodo Nuevo
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_PREFA_DETAL
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psCodigoPeriodoNuevo   VARCHAR2)
IS
   CURSOR c_interfaz IS
    SELECT 														
	    psCodigoPais as CODIGO_PAIS,													
	    t1.COD_CLIE as CODIGO_CONSULTORA,													
			t1.COD_PERI as PERIODO,											
			'O' as TIPO_CANAL,		 									
	    t1.VAL_SALD_DEUD as SALDO_CONSULTORA,													
			(CASE WHEN (T1.IND_ERRO_DEUD = '2' AND T1.IND_ERRO_MTMI = '0' AND T1.IND_ERRO_MTMA = '0') THEN 'D'											
	        	  WHEN ((T1.IND_ERRO_DEUD = '0' OR T1.IND_ERRO_DEUD = '1') AND T1.IND_ERRO_MTMI = '1' AND T1.IND_ERRO_MTMA = '0') THEN 'N'												
	              WHEN ((T1.IND_ERRO_DEUD = '0' OR T1.IND_ERRO_DEUD = '1') AND T1.IND_ERRO_MTMI = '0' AND T1.IND_ERRO_MTMA = '1') THEN 'M'													
	              WHEN (T1.IND_ERRO_DEUD = '2' AND T1.IND_ERRO_MTMI = '1' AND T1.IND_ERRO_MTMA = '0') THEN 'A'													
	              WHEN ((T1.IND_ERRO_DEUD = '0' OR T1.IND_ERRO_DEUD = '1') AND T1.IND_ERRO_MTMI = '1' AND T1.IND_ERRO_MTMA = '1') THEN 'Z'													
	        	  WHEN ((T1.IND_ERRO_DEUD = '0' OR T1.IND_ERRO_DEUD = '1') AND T1.IND_ERROR_SGPE = '0' AND T1.IND_ERRO_REMP = '0'												
				        AND T1.IND_ERRO_MTMI = '0' AND T1.IND_ERRO_MTMA = '0') THEN ' '				  			  			
			ELSE ' ' END) AS MOTIVO_PEDIDO_OBSERVADO,
			t1.COD_ZONA	  										
      FROM INT_SOLIC_CONSO_CABEC t1				  										
        WHERE ((t1.IND_ERRO_DEUD = '2' AND t1.IND_ERROR_SGPE = '0' AND t1.IND_ERRO_REMP = '0') OR														
         	   (t1.IND_ERRO_MTMI = '1' AND t1.IND_ERROR_SGPE = '0' AND t1.IND_ERRO_REMP = '0') OR													
               (t1.IND_ERRO_MTMA = '1' AND t1.IND_ERROR_SGPE = '0' AND t1.IND_ERRO_REMP = '0') OR														
			   (((t1.IND_ERRO_DEUD = '0') OR (t1.IND_ERRO_DEUD = '1')) AND t1.IND_ERROR_SGPE = '0' AND t1.IND_ERRO_REMP = '0')                											
              )			   											
        AND t1.IND_ADMI_CART != '1'														
        AND t1.COD_PERI = ( 														
            SELECT MAX(t2.COD_PERI) FROM INT_EVI_PERIO_REGIO_ZONA t2 														
            WHERE t2.COD_ZONA = t1.COD_ZONA 														
            AND t2.COD_REGI = t1.COD_REGI 	
            AND (t2.COD_PERI =psCodigoPeriodo	OR 												
                 t2.COD_PERI = DECODE(psCodigoPeriodoNuevo ,NULL, t2.COD_PERI, psCodigoPeriodoNuevo))
        );										 
   
   TYPE interfazRec IS RECORD
       (
        codigoPais              VARCHAR2(3),
        codigoCliente           INT_SOLIC_CONSO_CABEC.COD_CLIE%TYPE,
        codigoPeriodo			      INT_SOLIC_CONSO_CABEC.COD_PERI%TYPE,
        tipoCanal               VARCHAR2(1),     
        saldoConsultora         INT_SOLIC_CONSO_CABEC.VAL_SALD_DEUD%TYPE,
        motivoPedido            VARCHAR2(1), 
        codigoZona              INT_SOLIC_CONSO_CABEC.COD_ZONA%TYPE
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    lbAbrirUtlFile:= TRUE;
    /* Generando Archivo de Texto (Detalle) */
      OPEN c_interfaz;
          LOOP
             FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
               /* Procedimiento inicial para generar interfaz */
               IF lbAbrirUtlFile THEN
                 GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                 lbAbrirUtlFile := FALSE;
               END IF;
                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                        lsLinea :=  interfazRecord(x).codigoPais                            ||';'||
                                    interfazRecord(x).codigoCliente                           ||';'||    
                                    interfazRecord(x).codigoPeriodo                      ||';'||
                                    interfazRecord(x).tipoCanal                      ||';'||
                                    interfazRecord(x).saldoConsultora                      ||';'||
                                    interfazRecord(x).motivoPedido                      ||';'||
                                    interfazRecord(x).codigoZona	                ;
                        UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
      CLOSE c_interfaz;
    
    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);
    /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_PREFA_DETAL: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_PREFA_DETAL;


/**************************************************************************
Descripcion       : Envia Consultora CDR Cabecera (AVI-5)
Fecha Creacion    : 24/06/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_AVI_ENVIO_CLCDR_CABEC
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoPeriodo        VARCHAR2)
IS
   CURSOR c_interfaz IS
   SELECT DISTINCT
        psCodigoPais AS CODIGO_PAIS,														
        REC_CABEC_RECLA.NUM_RECL NUMERO_RECLAMO,														
        MAE_CLIEN.COD_CLIE CODIGO_CLIENTE, 														
		    SEG_PERIO_CORPO.COD_PERI CODIGO_PERIODO,  
		   (SELECT X.COD_PERI
             FROM SEG_PERIO_CORPO X,                                                    
                  CRA_PERIO Y
             WHERE          
                X.OID_PERI = Y.PERI_OID_PERI
                AND REC_CABEC_RECLA.PERD_OID_PERI_DOCU_REFE = Y.OID_PERI
            )CODIGO_PERIODO_ORIGEN,
		    psCodigoPeriodo CODIGO_PERIODO_PROCESO,	          												
		    ' ' AS ESTADO												
	   FROM REC_CABEC_RECLA,													
  	        REC_OPERA_RECLA,													
   	        REC_TIPOS_OPERA,			   										
   	        REC_OPERA,													
	        MAE_CLIEN,													
            MAE_CLIEN_DATOS_ADICI,														
	        SEG_PERIO_CORPO,													
	        CRA_PERIO													
	   WHERE  (REC_CABEC_RECLA.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)													
   	       AND (REC_CABEC_RECLA.OID_CABE_RECL = REC_OPERA_RECLA.CARE_OID_CABE_RECL)													
   	       AND (REC_TIPOS_OPERA.OID_TIPO_OPER = REC_OPERA_RECLA.TIOP_OID_TIPO_OPER)													
   	       AND (REC_TIPOS_OPERA.ROPE_OID_OPER = REC_OPERA.OID_OPER)													
		   AND (REC_OPERA.COD_OPER IN ('C','TM','TP','DN','DE','FM','FA'))												
           AND (MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)														
	       AND (REC_CABEC_RECLA.PERD_OID_PERI_RECL = CRA_PERIO.OID_PERI)													
	       AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)													
           AND (REC_CABEC_RECLA.COD_USUA_INGR != 'CALYPSO')														
           AND (REC_CABEC_RECLA.COD_USUA_INGR != 'BELCENTER')														
           AND (MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE != 7)														
	       AND (SEG_PERIO_CORPO.COD_PERI >= psCodigoPeriodo);			

   TYPE interfazRec IS RECORD
       (
        codigoPais              VARCHAR2(3),
        numeroReclamo           REC_CABEC_RECLA.NUM_RECL%TYPE,
        codigoCliente			      MAE_CLIEN.COD_CLIE%TYPE,
        codigoPeriodo           SEG_PERIO_CORPO.COD_PERI%TYPE,
        codigoPeriodoOrigen     SEG_PERIO_CORPO.COD_PERI%TYPE,
        codigoPeriodoProceso    SEG_PERIO_CORPO.COD_PERI%TYPE,
        estado                  VARCHAR2(1)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    lbAbrirUtlFile:= TRUE;
    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
             /* Procedimiento inicial para generar interfaz */
             IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
             END IF;

               IF interfazRecord.COUNT > 0 THEN
                  FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                      lsLinea :=  interfazRecord(x).codigoPais                            ||';'||
                                  interfazRecord(x).numeroReclamo                           ||';'|| 
                                  interfazRecord(x).codigoCliente                           ||';'||    
                                  interfazRecord(x).codigoPeriodo                      ||';'||
                                  interfazRecord(x).codigoPeriodoOrigen                      ||';'||
                                  interfazRecord(x).codigoPeriodoProceso                      ||';'||
                                  interfazRecord(x).estado	                ;
                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                  END LOOP;
               END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
    CLOSE c_interfaz;
    
    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);
    /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_AVI_ENVIO_CLCDR_CABEC: '||ls_sqlerrm);
END INT_PR_AVI_ENVIO_CLCDR_CABEC;

END INT_PKG_ASIST_VIRTU;
/
