CREATE OR REPLACE PACKAGE "COM_PKG_REPOR" IS
--INI PRODUCCION
   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;

   /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   W_FILAS      NUMBER:=1000;
   COMISION_INGRESO           VARCHAR2(2):= '01';
   COMISION_RECUPERACION      VARCHAR2(2):= '02';
   COMISION_COMERCIALIZACION  VARCHAR2(2):= '03';
   TIPO_PAGO                  VARCHAR2(1):= 'P';
   TIPO_RECLAMO               VARCHAR2(1):= 'R';
   TIPO_CARGO                 VARCHAR2(1):= 'C';
   PARAM_TODAS               CONSTANT VARCHAR2(30):='01_PARAM_TODAS';
   PARAM_TUTORAS             CONSTANT VARCHAR2(30):='02_PARAM_TUTORAS';
   PARAM_EJECUTIVAS          CONSTANT VARCHAR2(30):='03_PARAM_EJECUTIVAS';
   NIVEL_TUTORA              CONSTANT VARCHAR2(2):='TT';
   NIVEL_EJECUTIVA_JUNIOR    CONSTANT VARCHAR2(2):='EJ';
   NIVEL_EJECUTIVA_SENIOR    CONSTANT VARCHAR2(2):='ES';
   NIVEL_EJECUTIVA_MASTER    CONSTANT VARCHAR2(2):='EM';
   NIVEL_SIN_NIVEL           CONSTANT VARCHAR2(2):='SN';

   /* VARIABLES CREADAS POR DAVID RAMOS PARA EL CONTROL DE LAS COMISIONES ESCALONADAS */
   SI_ES_ESCALONADA          VARCHAR2(1):= 'N';
   Gln_IdComision            NUMBER;
   Gln_FlagComisEscal        NUMBER;

   TYPE tRegTablaClienComision IS RECORD (
     OID_CLIE               MAE_CLIEN.OID_CLIE%TYPE,
     COD_CLIE               MAE_CLIEN.COD_CLIE%TYPE,
     NOM_CLIE               VARCHAR2(100),
     COD_SUBG_VENT          ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE,
     COD_REGI               ZON_REGIO.COD_REGI%TYPE,
     COD_ZONA               ZON_ZONA.COD_ZONA%TYPE,
     COD_SECC               ZON_SECCI.COD_SECC%TYPE,
     COD_TERR               ZON_TERRI.COD_TERR%TYPE,
     OID_ZONA               ZON_ZONA.OID_ZONA%TYPE,
     OID_SECC               ZON_SECCI.OID_SECC%TYPE,
     OID_TERR_ADMI          MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI%TYPE,
     IMP_PAGO_ACTU          NUMBER,
     IMP_PAGO_1             NUMBER,
     IMP_PAGO_2             NUMBER,
     IMP_PAGO_3             NUMBER,
     IMP_PAGO_4             NUMBER,
     IMP_PAGO_NETO          NUMBER,
     IMP_COMI               NUMBER,
     COD_LIDE_ZONA          MAE_CLIEN.COD_CLIE%TYPE,
     NOM_LIDE_ZONA          VARCHAR2(100),
     COD_LIDE_SECC          MAE_CLIEN.COD_CLIE%TYPE,
     NOM_LIDE_SECC          VARCHAR2(100),
     IMP_COBR_1             NUMBER,
     IMP_COBR_2             NUMBER,
     IMP_COBR_3             NUMBER,
     IMP_COBR_4             NUMBER,
     IMP_COBR_ACTU          NUMBER,
     IMP_COBR_TOTA          NUMBER
    );

   TYPE tTablaClienteComision IS TABLE OF tRegTablaClienComision;

   TYPE tRegClienteComisionRecupe IS RECORD (
     OID_CLIE               MAE_CLIEN.OID_CLIE%TYPE,
     COD_CLIE               MAE_CLIEN.COD_CLIE%TYPE,
     NOM_CLIE               VARCHAR2(100),
     OID_REGI               NUMBER,
     COD_REGI               ZON_REGIO.COD_REGI%TYPE,
     OID_ZONA               NUMBER,
     COD_ZONA               ZON_ZONA.COD_ZONA%TYPE,
     COD_SUBG_VENT          ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE,
     OID_SECC               NUMBER,
     COD_SECC               ZON_SECCI.COD_SECC%TYPE,
     COD_TERR               ZON_TERRI.COD_TERR%TYPE,
     oid_terr_admin         NUMBER
   );

   TYPE tTablaClienteComisionRecupe IS TABLE OF tRegClienteComisionRecupe;

   TYPE tRegClienteComisionComer IS RECORD (
     OID_CLIE               MAE_CLIEN.OID_CLIE%TYPE,
     COD_CLIE               MAE_CLIEN.COD_CLIE%TYPE,
     NOM_CLIE               VARCHAR2(100),
     OID_REGI               NUMBER(12),
     COD_REGI               ZON_REGIO.COD_REGI%TYPE,
     OID_ZONA               NUMBER(12),
     COD_ZONA               ZON_ZONA.COD_ZONA%TYPE,
     COD_SUBG_VENT          ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE,
     OID_SECC               NUMBER(12),
     COD_SECC               ZON_SECCI.COD_SECC%TYPE,
     COD_TERR               ZON_TERRI.COD_TERR%TYPE,
     OID_TERR_ADMIN         NUMBER(12),
     PERD_OID_PERI          NUMBER(12),
     FEC_DOCU               DATE,
     FEC_ULTI_MOVI          DATE,
     FEC_VENC               DATE,
     IMP_PAGO               NUMBER(12,2),
     OID_MOVI_CC            NUMBER(12),
     VAL_TASA_IMPU          NUMBER(12,2),
     SUBP_OID_SUBP_ULTI     NUMBER(12)
   );

   TYPE tTablaClienteComisionComer IS TABLE OF tRegClienteComisionComer;

   TYPE tRegReporteEst IS RECORD (
     PERD_OID_PERI        INT_FUENT_VENTA_PREVI_SAP.PERD_OID_PERI%TYPE,
     NUM_ACTI_INIC        INT_FUENT_VENTA_PREVI_SAP.NUM_ACTI_INIC%TYPE,
     NUM_INGR             INT_FUENT_VENTA_PREVI_SAP.NUM_INGR%TYPE,
     NUM_REIN             INT_FUENT_VENTA_PREVI_SAP.NUM_REIN%TYPE,
     NUM_EGRE             INT_FUENT_VENTA_PREVI_SAP.NUM_EGRE%TYPE,
     NUM_PEDI             INT_FUENT_VENTA_PREVI_SAP.NUM_PEDI%TYPE,
     NUM_UNID_VEND        INT_FUENT_VENTA_PREVI_SAP.NUM_UNID_VEND%TYPE,
     NUM_CLIE             INT_FUENT_VENTA_PREVI_SAP.NUM_CLIE%TYPE,
     NUM_ACTI_FINA        INT_FUENT_VENTA_PREVI_SAP.NUM_ACTI_FINA%TYPE,
     VAL_VENT_NETA_ESTA   INT_FUENT_VENTA_PREVI_SAP.VAL_VENT_NETA_ESTA%TYPE,
     POR_EGRE             INT_FUENT_VENTA_PREVI_SAP.NUM_EGRE%TYPE,
     CAPITAL              INT_FUENT_VENTA_PREVI_SAP.NUM_INGR%TYPE,
     POR_ACTI             INT_FUENT_VENTA_PREVI_SAP.NUM_INGR%TYPE,
     PPU                  INT_FUENT_VENTA_PREVI_SAP.VAL_VENT_NETA_ESTA%TYPE,
     PUP                  INT_FUENT_VENTA_PREVI_SAP.NUM_UNID_VEND%TYPE,
     PRO_SOL_VEN          INT_FUENT_VENTA_PREVI_SAP.VAL_VENT_NETA_ESTA%TYPE,
     COD_PERI_INIC        VARCHAR2(6),
     COD_PERI_FINA        VARCHAR2(6),
     RES_REGI             VARCHAR2(100),
     RES_ZONA             VARCHAR2(100)
   );

   TYPE tRegIdPeriodo IS RECORD (
       PERD_OID_PERI INT_FUENT_VENTA_PREVI_SAP.PERD_OID_PERI%TYPE
   );
   TYPE TABLA_COMIS_ESTIMADO IS TABLE OF tRegReporteEst;
   TYPE TABLA_VENTA_IDPER IS TABLE OF tRegIdPeriodo;


/***************************************************************************
Descripcion       : Devuelve el ultimo oid de comision en base al codigo
                    de comision
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_ULTI_OID_COMI(psCodComision VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve oid cliente unidad Administrativa en base
                    al oid cliente y codigo de periodo
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_OID_UNADM_HISTO
(pnIdCliente NUMBER,
 pnIdTerriAdmin NUMBER,
 psCodPeriodo VARCHAR2
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve oid unidad Administrativa historico en base
                    al oid cliente y codigo de periodo
Fecha Creacion    : 22/01/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_OID_UNADM_HISTO
(pnIdCliente    NUMBER,
 psCodPeriodo   VARCHAR2
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve Responsable de la Unidad Administrativa en un
                    determinado periodo
Fecha Creacion    : 07/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_RESPO_UNIAD_HISTO
(psCodResponsable OUT VARCHAR2,
 pdFechaIniPeri   DATE,
 pdFechaFinPeri   DATE,
 pnIdPais         NUMBER,
 psCodSubGerencia VARCHAR2,
 psCodRegion      VARCHAR2,
 psCodZona        VARCHAR2,
 psCodSeccion     VARCHAR2:=NULL
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Procedimiemto que genera data que sera mostrada en el
                    Reporte de Comision x Ingresos
Fecha Creacion    : 07/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_GENER_LISTA_COMIS_INGRE(
  psCodPais     VARCHAR2,
  psCodMarca    VARCHAR2,
  psCodCanal    VARCHAR2,
  psCodComision VARCHAR2,
  psCodPeriodo  VARCHAR2,
  psAnioTramo   VARCHAR2,
  psNumTramo    VARCHAR2,
  psCodTipoCalculo VARCHAR2
);

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de comisiones. Genera los
                    importes de pago de acuerdo al cliente y periodo ingresados
Fecha Creacion    : 10/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_MONTO_COMIS_INGRE(
  vbDemandaAnticipada     BOOLEAN,
  vnIdPais                NUMBER,
  vnIdCliente             NUMBER,
  vnIdPeriodoMas2         NUMBER,
  vnIdPeriodoSiguiente    NUMBER,
  vnIdPeriodo             NUMBER,
  vnIdPeriodoAnte         NUMBER,
  vnIdSubprocCreacion     NUMBER,
  vnIdZona                NUMBER,
  vnIdEstadoPedido        NUMBER
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve Listado de Registros Estimados
Fecha Creacion    : 08/03/2007
Autor             : Jose Nunez Mori
***************************************************************************/
FUNCTION COM_FN_OBTIE_ESTIM(
    psCodPais    VARCHAR2,
    psCodSoc     VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodAlmac   VARCHAR2,
    psCodCanal   VARCHAR2,
    psRango      VARCHAR2,
    psAnio       VARCHAR2,
    psIndicador  VARCHAR2,
    psCodRegi    VARCHAR2,
    psCodZona    VARCHAR2
)
RETURN  TABLA_COMIS_ESTIMADO PIPELINED;

/***************************************************************************
Descripcion       : Procedimiemto que genera data para el Calculo de Comision
                    de Recuperacion que ser¿ mostrado en los reportes respectivos.
Parametros        : psTipoComision :
                           'L'  Lider
                           'G'  Gerente de Zona
                           'Gr' Gerente Regional
Fecha Creacion    : 07/03/2007
Autor             : Carlos Bazalar
                    Leonardo Lizana
***************************************************************************/
PROCEDURE COM_PR_GENER_LISTA_COMIS_RECUP(
    psCodPais       VARCHAR2,
    psCodMarca      VARCHAR2,
    psCodCanal      VARCHAR2,
    psCodComision   VARCHAR2,
    psCodPeriodo    VARCHAR2,
    psTipoComision  VARCHAR2,
    psUsuario       VARCHAR2,
    psAnioTramo   VARCHAR2,
    psNumTramo    VARCHAR2,
    psCodTipoCalculo VARCHAR2,
    psCodigoGerenteBase07 VARCHAR2,
    psCodigoRegionBase07 VARCHAR2,
    psCodigoZonaBase07 VARCHAR2,
    psfechaRetiroBase07 VARCHAR2
    );



/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de Comisiones x Recuperacion. Genera los
                    importes de pago de acuerdo al cliente y periodo ingresados
Fecha Creacion    : 16/03/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_MONTO_COMIS_RECUP(
  vsCodigoPais            VARCHAR2,
  vsEvaluaTelecobro       VARCHAR2,
  vsIndicadorCrono        VARCHAR2,
  vsTipoComision          VARCHAR2,
  vbEsSoloTramo1          BOOLEAN,
  vbDemandaAnticipada     BOOLEAN,
  vnNumDiasDemandaTramo1  NUMBER,
  vnNumDiasDemandaTramo2  NUMBER,
  vnIdPeriodo             NUMBER,
  vnIdPeriodoAnterior     NUMBER,
  vsCodPeriAnterior       VARCHAR2,
  vnIdPeriodoSiguiente    NUMBER,
  vnIdPeriodoMas2         NUMBER,
  vnIdComision            NUMBER,
  vnIdRegion              NUMBER,
  vnIdZona                NUMBER,
  vnIdEstadoAnulado       NUMBER,
  vnIdPais                NUMBER,
  vnFlagDescuento         NUMBER,
  vnFlagWeekend           NUMBER,
  vnImporteVenta          NUMBER,
  vnPorRecupera           NUMBER,
  vnPorActivida           NUMBER,
  vnMontoBono             NUMBER,
  vsBaseComision          VARCHAR2,
  vnPorcenVentaConsu      NUMBER,
  vdFechaRetiroBase07     DATE,
  vregRegistro            IN OUT NOCOPY COM_REPOR_COMIS_RECUP%ROWTYPE ,
  vnSumaComisionTramo1    OUT NUMBER,
  vnSumaComisionTramo2    OUT NUMBER
);

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de Comisiones x Recuperacion. Genera los
                    importes de acumulados de saldos de zona
Fecha Creacion    : 06/10/2012
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE COM_PR_COMIS_RECUP_ACUMU_ZONA(
  vnIdPais                NUMBER,
  vsCodigoPais            VARCHAR2,
  vnIdPeriodo             NUMBER,
  vnIdComision            NUMBER,
  vnIdRegion              NUMBER,
  vnCodZona               zon_zona.cod_zona%type,
  vregRegistro            IN OUT NOCOPY COM_REPOR_COMIS_RECUP%ROWTYPE
);

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de Comisiones x Recuperacion.
                    Devuelve Fecha Habil, es decir ni sabado ni domingo ni feriado
                    en base a una fecha base
Fecha Creacion    : 27/03/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_FECHA_HABIL (
   pdFecha              DATE,
   psCodZona            VARCHAR2
)
RETURN DATE;

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de Comisiones x Recuperacion.
                    Devuelve cantidad de dias maximo para una comision
Fecha Creacion    : 16/03/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_NUMER_DIAS (
   pdFecha              DATE,
   pnCantidadDiasMaximo NUMBER,
   psCodZona            VARCHAR2
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de Comisiones x Recuperacion.
                    Devuelve cantidad de dias maximo para una comision
Fecha Creacion    : 11/05/2009
Autor             : Telly Tataje
***************************************************************************/
FUNCTION COM_FN_DEVUE_NUMER_DIAS (
   pdFecha              DATE,
   pnCantidadDiasMaximo NUMBER,
   psCodZona            VARCHAR2,
   pnFlagWeekend        NUMBER
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve suma de importe de comision correspondiente al
                    Periodo y L¿der de Seccion
Fecha Creacion    : 17/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_COMIS_LIDER_SECCI(
   psCodPeriodo      VARCHAR2,
   psCodLiderSeccion VARCHAR2,
   psComisionIngreso VARCHAR2,
   psComisionRecu   VARCHAR2 )
RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve Rango de Fechas entre la que esta comprendio el periodo
                    ingresado como paramtero
Fecha Creacion    : 07/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_RANGO_FECHA_PERIO(
   psCodPeriodo VARCHAR2,
   pnIdPais     NUMBER,
   pnIdMarca    NUMBER,
   pnIdCanal    NUMBER,
   pdFechaIni   OUT DATE,
   pdFechaFin   OUT DATE,
   psMensaje    OUT VARCHAR2
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Devuelve Rango de Fechas entre la que esta comprendio el periodo
                    ingresado como paramtero
Fecha Creacion    : 23/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_RANGO_FECHA_PERIO(
   pnIdPeriodo  NUMBER,
   pnIdPais     NUMBER,
   pnIdMarca    NUMBER,
   pnIdCanal    NUMBER,
   pdFechaIni   OUT DATE,
   pdFechaFin   OUT DATE,
   psMensaje    OUT VARCHAR2
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de comisiones. Devuelve importe
                    de cobranza por periodo y codigo de consultora
Fecha Creacion    : 23/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_COBRA_CONSU(
   psCodPeriodo     VARCHAR2,
   psCodConsultora  VARCHAR2
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Procedimiemto que genera data para el Calculo de Comision
                    de Recuperacion que ser¿ mostrado en los reportes respectivos.
Parametros        : psTipoComision :
                           'L'  Lider
                           'G'  Gerente de Zona

Fecha Creacion    : 07/03/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_GENER_LISTA_COMIS_COMER(
  psCodPais       VARCHAR2,
  psCodMarca      VARCHAR2,
  psCodCanal      VARCHAR2,
  psCodComision   VARCHAR2,
  psFechaInicial  VARCHAR2,
  psFechaFinal    VARCHAR2,
  psUsuario       VARCHAR2
);

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de comisiones. Genera los
                    importes de pago de acuerdo al cliente y periodo ingresados
Fecha Creacion    : 10/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_MONTO_COMIS_COMER(
  vnRegComercializa    com_repor_comis_comer%ROWTYPE,
  vnIdPais             NUMBER,
  vsCodPais            VARCHAR2,
  vnNumDiasComi        NUMBER,
  vdFechaDocu          DATE,
  vdFechaUltiMovi      DATE,
  vdFechaVencimiento   DATE,
  vsFechaIniProceso    VARCHAR2,
  vsFechaFinProceso    VARCHAR2,
  vnIdPeriodoSiguiente NUMBER,
  vnIdPeriodoMas2      NUMBER,
  vnIdRegion           NUMBER
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de comisiones. Genera los
                    importes de pago de acuerdo al cliente y periodo ingresados
Fecha Creacion    : 11/05/2009
Autor             : Telly Tataje
***************************************************************************/
FUNCTION COM_FN_MONTO_COMIS_COMER(
  vnRegComercializa    com_repor_comis_comer%ROWTYPE,
  vnIdPais             NUMBER,
  vsCodPais            VARCHAR2,
  vnNumDiasComi        NUMBER,
  vdFechaDocu          DATE,
  vdFechaUltiMovi      DATE,
  vdFechaVencimiento   DATE,
  vsFechaIniProceso    VARCHAR2,
  vsFechaFinProceso    VARCHAR2,
  vnIdPeriodoSiguiente NUMBER,
  vnIdPeriodoMas2      NUMBER,
  vnIdRegion           NUMBER,
  vnFlagWeekend        NUMBER
)
RETURN NUMBER;

/***************************************************************************
Descripcion       : Procedimiento que devuelve % y Numero de Dias correspondiente
                    al Tramo 1 y Tramo 2 de una comision ingresada como parametro
Fecha Creacion    : 14/05/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_DEVUE_PORCEN_COMIS(
  psCodComision   VARCHAR2,
  pnNumDiasComi   OUT VARCHAR2,
  pnValPorceRecu  OUT VARCHAR2,
  pnValPorceComi  OUT VARCHAR2,
  pnNumDiasComi2  OUT VARCHAR2,
  pnValPorceRecu2 OUT VARCHAR2,
  pnValPorceComi2 OUT VARCHAR2);


/***************************************************************************
Descripcion       : Funcion que devuelve el nro de documento para  Lideres
                    Nuevas
Fecha Creacion    : 16/11/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DOCUM_IDENT_LIDER_NUEV(
  pnIdCliente NUMBER,
  pnIdPais    NUMBER,
  psCodTipoDocum VARCHAR2
  )
RETURN VARCHAR2 ;


/***************************************************************************
Descripcion       : Funcion que devuelve el nivel de la responsable

Fecha Creacion    : 31/07/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
FUNCTION COM_FN_DEVUE_PROCE_TUTOR(psCodPais  VARCHAR2,
                            psCodMarca VARCHAR2,
                            psCodCanal VARCHAR2,
                            psCodEjec  VARCHAR2,
                            psAnioIniTramo VARCHAR2,
                            psCodTramo     VARCHAR2,
                            psTipoComision VARCHAR2,
                            psCodTipoCalculo VARCHAR2
                            ) RETURN BOOLEAN;

/***************************************************************************
Descripcion       : Funcion que verifica que exixte registros en la tabla
                    COM_PARAM_ZONAS_NUEVA, retorna true si no false

Fecha Creacion    : 02/09/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE COM_PR_ZONA_NUEVA(psCodPais VARCHAR2,
                            psIdPais NUMBER,
                            psCodPeriodo VARCHAR2,
                            psIdPeriActual NUMBER,
                            psCodComision VARCHAR2
                           );

/***************************************************************************
Descripcion        : Devuelve el importe de comision de ingreso de ejecutivas
Fecha Creacion     : 25/09/2008
Autor             : RRG
***************************************************************************/
FUNCTION COM_FN_DEVUE_COMIS_INGRE_EJECU(
   psCodPeriodo      VARCHAR2,
   psCodLiderSeccion VARCHAR2,
   psComisionIngreso VARCHAR2)
RETURN NUMBER;
/***************************************************************************
Descripcion        : Devuelve el importe de comision de EJCAL
Fecha Creacion     : 25/09/2008
Autor             : RRG
***************************************************************************/
FUNCTION COM_FN_DEVUE_COMIS_EJCAL(
   psCodPais         VARCHAR2,
   psCodComi        VARCHAR2,
   psCodEjec         VARCHAR2,
   psCodPeri         VARCHAR2,
   factor            NUMBER)
RETURN NUMBER;
/***************************************************************************
Descripcion        : Devuelve el valor del monto de comision
Fecha Creacion     : 25/09/2008
Autor             : RRG
***************************************************************************/
FUNCTION COM_FN_DEVUE_MONTO_COMIS(
   psCodPais         VARCHAR2,
   psCodComi        VARCHAR2,
   psCodEjec         VARCHAR2,
   psCodPeri         VARCHAR2)
RETURN NUMBER;

/***************************************************************************
Descripcion        : Funcion que devuelve la tasa de Impuesto.
Fecha Creacion     : 07/04/2009
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_TASA_IMPUE (
   psCodigoPais VARCHAR2,
   pdFecha DATE
)
RETURN NUMBER;

/***************************************************************************
Descripcion        : Funcion que devuelve la maxima Fecha de Inicio de CRA_ACTIV
                     en base al pais, periodo, zona y codigo de actividad
Fecha Creacion     : 14/05/2009
Parametros         :
       pnIdPais    : Id del Pais
       pnIdPeriodo : Id del Periodo
       pnIdZona    : Id de Zona
       psCodActi   : Codigo de Actividad
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_MAXIM_FINIC_CRONO (
   pnIdPais NUMBER,
   pnIdPeriodo NUMBER,
   pnIdZona NUMBER,
   psCodActi VARCHAR2
)
RETURN DATE;

/********************************************************************************
Descripcion        : Funcion que valida que el responsable que se esta ingresando
                     en el rango de periodo no se encuentre ya registrado en otra
                     zona o seccion
Fecha Creacion     : 20/02/2012
Autor              : Jesse Rios
*********************************************************************************/
FUNCTION COM_FN_VALID_RESPO(psCodigoPais VARCHAR2,
                            psCodigoMarca VARCHAR2,
                            psCodigoCanal VARCHAR2,
                            psCodigoResponsable VARCHAR2,
                            psCodigoPeriodoDesde VARCHAR2,
                            psCodigoPeriodoHasta VARCHAR2) RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Devuelve Responsable de la Unidad Administrativa en base
                    al periodo de proceso
Fecha Creacion    : 11/04/2012
Autor             : Jesse James Rios Franco
***************************************************************************/
FUNCTION COM_FN_DEVUE_RESPO_UNIAD_HIST2
(psCodResponsable OUT VARCHAR2,
 psCodigoPeriodo   VARCHAR2,
 pnIdPais         NUMBER,
 psCodSubGerencia VARCHAR2,
 psCodRegion      VARCHAR2,
 psCodZona        VARCHAR2,
 psCodSeccion     VARCHAR2:=NULL
)
RETURN VARCHAR2;
--FIN PRODUCCION

/***************************************************************************
Descripcion        : Devuelve fecha de Vencimiento para una Gerente que esta
                     siendo calculada su comision escalonada
Fecha Creacion     : 24/12/2012
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION COM_FN_DEVUE_FECHA_VENCI(
   psCodPais         VARCHAR2,
   psCodPeriodo      VARCHAR2,
   pnOidZona         VARCHAR2)
RETURN VARCHAR2;

/***************************************************************************
  Descripcion       : Calculo de comision Objetivo Venta
  Fecha Creacion    : 04/07/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
PROCEDURE COM_PR_CALCU_OBJET_VENTA(
       psCodPais      VARCHAR2,
       psCodPeriodo   VARCHAR2,
       psBaseComision VARCHAR2,
       psTipoComision VARCHAR2);

/***************************************************************************
Descripcion        : Devuelve fecha de Vencimiento para una Gerente que esta
                     siendo calculada su comision escalonada
Fecha Creacion     : 29/01/2013
Autor             :
***************************************************************************/
PROCEDURE COM_PR_CALCU_VENT_ZONA(psCodPais   VARCHAR2,
                                 psCodPeriodo   VARCHAR2);

/***************************************************************************
  Descripcion       : Calculo de comision de OBJETIVO venta Region
  Fecha Creacion    : 04/07/2014
  Autor             : Carlos Bazalar
  ***************************************************************************/
PROCEDURE COM_PR_CALCU_VENT_REGIO(psCodPais   VARCHAR2,
                                 psCodPeriodo   VARCHAR2);

/***************************************************************************
  Descripcion       : Insercion de comision de venta
  Fecha Creacion    : 11/01/2013
  Autor             : Giovanni Ascarza
  ***************************************************************************/

  PROCEDURE COM_PR_INSER_COMIS_VENT ( pscodPeri        varchar2,
                                      pscodRegi        VARCHAR2,
                                      pscodZona        VARCHAR2,
                                      pnvalCata        number ,
                                      pnvalCataEst     number,
                                      pnvalRetail number);

/***************************************************************************
  Descripcion       : Insercion de comision de OBJETIVO venta Region
  Fecha Creacion    : 04/07/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_INSER_COMIS_VENT_REGIO (
                                      pscodPeri        varchar2,
                                      pscodRegi        VARCHAR2,
                                      pnvalCata        number ,
                                      pnvalCataEst     number,
                                      pnvalRetail number);

/***************************************************************************
  Descripcion       : Calculo de comision de venta para Gerente de Zona
  Fecha Creacion    : 21/02/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIS_VENT_ZONA ( pnOidPais      NUMBER,
                                      psCodPeriodo   VARCHAR2,
                                      psCodComision  VARCHAR2,
                                      oidCampana     VARCHAR2);

/***************************************************************************
  Descripcion       : Calculo de comision de Objetivo venta para Gerente de Region
  Fecha Creacion    : 05/07/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIS_VENT_REGIO (
                                      pnOidPais      NUMBER,
                                      psCodPeriodo   VARCHAR2,
                                      psCodComision  VARCHAR2,
                                      oidCampana     VARCHAR2);

/***************************************************************************
  Descripcion       : Actualiza Comision de Venta Retail a partir del Cierre
                      de Campaña
  Fecha Creacion    : 10/06/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
PROCEDURE COM_PR_ACTUA_COMIS_VENTA_RETAI (
        psCodPais      VARCHAR2,
        psCodPeriodo   VARCHAR2);

/**************************************************************************
Descripcion     : Devuelve si existen Registros Calculados para Comision de
                  Base 07
Fecha Creacion  : 12/11/2013
Autor           : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_REGIS_CALCU_BAS07(
   psCodPeri         VARCHAR2,
   psTipoComis       VARCHAR2,
   psCodComi         VARCHAR2,
   psCodClie         VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion     : Realiza la Eliminacion de Registros de Gerentes Retiradas
                  para Comision de Base 07
Fecha Creacion  : 12/11/2013
Autor           : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_ELIMI_COMIS_GEREN_RETIR(
   psCodPeriodo   VARCHAR2,
   psCodigoRegion  VARCHAR2,
   psCodigoZona    VARCHAR2,
   psCodigoCliente   VARCHAR2,
   psCodigoComision   VARCHAR2,
   psTipoComision   VARCHAR2
);

END COM_PKG_REPOR;
/
CREATE OR REPLACE PACKAGE BODY "COM_PKG_REPOR"
IS
/***************************************************************************
INICIO PRODUC
Descripcion        : Procedimiento que realiza validaciones adicionales con Respecto
                     al Gerente de Zona
Fecha Creacion     : 19/05/2009
Parametros         :
Autor              : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_VALID_ADICI_GZONA_RECUP (
   pnIdComision         NUMBER,
   pnIdRegion           NUMBER,
   pnIdZona             NUMBER,
   psCodLiderZona       VARCHAR2,
   pbInsertar           IN OUT BOOLEAN,
   pbInsertarZona       IN OUT BOOLEAN
);

/***************************************************************************
Descripcion        : Procedimiento que obtiene los montos totales correspondiente
                     a la comision de recuperacion correspondiente a LIDERES
Fecha Creacion     : 19/05/2009
Parametros         :
Autor              : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_MONTO_MLIDE_RECUP (
   psCodComision        VARCHAR2,
   psCodPeriodo         VARCHAR2,
   psTipoComision       VARCHAR2,
   psBaseComision       VARCHAR2,
   pnValorIGVVen        NUMBER,
   pnIndDctoImportePago NUMBER,
   pnIndDctoReclamo     NUMBER,
   pnValPorceComi01     NUMBER,
   pnValPorceComi02     NUMBER,
   pnValPorceRecu01     NUMBER,
   pnValPorceRecu02     NUMBER,
   psCodigoRegionBase07 VARCHAR2,
   psCodigoZonaBase07   VARCHAR2
);

/***************************************************************************
Descripcion        : Procedimiento que obtiene los montos totales correspondiente
                     a la comision de recuperacion correspondiente a GERENTES DE
                     ZONA
Fecha Creacion     : 19/05/2009
Parametros         :
Autor              : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_MONTO_GZONA_RECUP (
   lnOidPais            NUMBER,
   psCodPais            VARCHAR2,
   psCodMarca           VARCHAR2,
   psCodCanal           VARCHAR2,
   psCodComision        VARCHAR2,
   psCodPeriodo         VARCHAR2,
   psTipoComision       VARCHAR2,
   psBaseComision       VARCHAR2,
   pnValorIGVVen        NUMBER,
   pnIndDctoImportePago NUMBER,
   pnIndDctoReclamo     NUMBER,
   pnValPorceComi01     NUMBER,
   pnValPorceComi02     NUMBER,
   pnValPorceRecu01     NUMBER,
   pnValPorceRecu02     NUMBER,
   pnIdPeriodo          NUMBER,
   pnValPorceActividad  NUMBER,
   pnValProceActividadCOMI  NUMBER,
   pnValPorceComi       NUMBER,
   pnPorcenVentaConsu   NUMBER,
   psUsuario            VARCHAR2,
   psCodigoRegionBase07 VARCHAR2,
   psCodigoZonaBase07   VARCHAR2
);

/***************************************************************************
Descripcion        : Procedimiento que obtiene los montos totales correspondiente
                     a la comision de recuperacion correspondiente a GERENTES DE
                     REGION
Fecha Creacion     : 19/05/2009
Parametros         :
Autor              : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_MONTO_GREGI_RECUP (
   pnOidPais            NUMBER,
   psCodPais            VARCHAR2,
   psCodMarca           VARCHAR2,
   psCodCanal           VARCHAR2,
   psCodComision        VARCHAR2,
   psCodPeriodo         VARCHAR2,
   pnIdPeriodo          NUMBER,
   psTipoComision       VARCHAR2,
   psBaseComision       VARCHAR2,
   pnValorIGVVen        NUMBER,
   pnIndDctoImportePago NUMBER,
   pnIndDctoReclamo     NUMBER,
   pnValPorceComi01     NUMBER,
   pnValPorceComi02     NUMBER,
   pnValPorceRecu01     NUMBER,
   pnValPorceRecu02     NUMBER,
   psUsuario            VARCHAR2,
   psCodigoRegionBase07 VARCHAR2
);

/***************************************************************************
Descripcion       : Devuelve el ultimo oid de comision en base al codigo
                    de comision
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_ULTI_OID_COMI(psCodComision VARCHAR2)
RETURN NUMBER
IS
 lnVersion    COM_COMIS.NUM_VERS%TYPE;
 lnRetorno    COM_COMIS.OID_COMI%TYPE;
BEGIN
  SELECT MAX(A.NUM_VERS)
  INTO lnVersion
  FROM
      COM_COMIS A
  WHERE
      A.COD_COMI = psCodComision;
  SELECT A.OID_COMI
  INTO lnRetorno
  FROM COM_COMIS A
  WHERE
      A.COD_COMI = psCodComision AND
      A.NUM_VERS = lnVersion;
  RETURN lnRetorno;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN -1;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_OBTIE_VENTA_VARIABLE: '||ls_sqlerrm);
END COM_FN_DEVUE_ULTI_OID_COMI;

/***************************************************************************
Descripcion       : Devuelve oid unidad Administrativa historico en base
                    al oid cliente y codigo de periodo
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_OID_UNADM_HISTO
(pnIdCliente    NUMBER,
 pnIdTerriAdmin NUMBER,
 psCodPeriodo   VARCHAR2
)
RETURN NUMBER
IS
 lnOidUnidad  mae_clien_unida_admin.oid_clie_unid_admi%TYPE;
 lbEncontro   BOOLEAN:=FALSE;
 lbEntro      BOOLEAN:=FALSE;

 CURSOR c1 IS
   SELECT C.OID_CLIE_UNID_ADMI, C.ZTAD_OID_TERR_ADMI
       FROM
          SEG_PERIO_CORPO A,
          CRA_PERIO B,
          MAE_CLIEN_UNIDA_ADMIN C
        WHERE -- C.ZTAD_OID_TERR_ADMI = pnIdTerriAdmin
         --  AND
           C.CLIE_OID_CLIE = pnIdCliente
           AND C.PERD_OID_PERI_INI = B.OID_PERI
           AND A.OID_PERI = B.PERI_OID_PERI
           AND A.COD_PERI <= psCodPeriodo
           AND C.PERD_OID_PERI_FIN IS NULL
        ORDER BY A.COD_PERI DESC   ;

 CURSOR c2 IS
    SELECT C.OID_CLIE_UNID_ADMI, C.ZTAD_OID_TERR_ADMI
    FROM
       SEG_PERIO_CORPO A,
       CRA_PERIO B,
       MAE_CLIEN_UNIDA_ADMIN C,
       SEG_PERIO_CORPO D,
       CRA_PERIO F
    WHERE --C.ZTAD_OID_TERR_ADMI = pnIdTerriAdmin
     -- AND
      C.CLIE_OID_CLIE = pnIdCliente
      AND C.PERD_OID_PERI_INI = B.OID_PERI
      AND A.OID_PERI = B.PERI_OID_PERI
      AND A.COD_PERI <= psCodPeriodo
      AND C.PERD_OID_PERI_FIN = F.OID_PERI
      AND D.OID_PERI = F.PERI_OID_PERI
      AND D.COD_PERI >= psCodPeriodo
   ORDER BY A.COD_PERI DESC   ;

BEGIN
 lnOidUnidad := -1;
 FOR cur1 IN c1 LOOP
     lbEntro := TRUE;
     IF cur1.ztad_oid_terr_admi = pnIdTerriAdmin THEN
        lbEncontro := TRUE;
        lnOidUnidad := cur1.oid_clie_unid_admi;
        EXIT;
     END IF;
 END LOOP;
 IF NOT lbEntro  THEN
    IF NOT lbEncontro THEN
       FOR cur2 IN c2 LOOP
           IF cur2.ztad_oid_terr_admi = pnIdTerriAdmin THEN
              lbEncontro := TRUE;
              lnOidUnidad := cur2.oid_clie_unid_admi;
              EXIT;
           END IF;
       END LOOP;
    END IF;
 END IF;
 RETURN lnOidUnidad;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_OID_UNADM_HISTO: '||ls_sqlerrm);
END COM_FN_DEVUE_OID_UNADM_HISTO;


/***************************************************************************
Descripcion       : Devuelve oid unidad Administrativa historico en base
                    al oid cliente y codigo de periodo
Fecha Creacion    : 22/01/2010
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_OID_UNADM_HISTO
(pnIdCliente    NUMBER,
 psCodPeriodo   VARCHAR2
)
RETURN NUMBER
IS
 lnOidUnidad  mae_clien_unida_admin.oid_clie_unid_admi%TYPE;
 lbEncontro   BOOLEAN:=FALSE;
 lbEntro      BOOLEAN:=FALSE;

 CURSOR c1 IS
   SELECT C.OID_CLIE_UNID_ADMI
       FROM
          SEG_PERIO_CORPO A,
          CRA_PERIO B,
          MAE_CLIEN_UNIDA_ADMIN C
        WHERE C.CLIE_OID_CLIE = pnIdCliente
           AND C.PERD_OID_PERI_INI = B.OID_PERI
           AND A.OID_PERI = B.PERI_OID_PERI
           AND A.COD_PERI <= psCodPeriodo
           AND C.PERD_OID_PERI_FIN IS NULL
        ORDER BY A.COD_PERI DESC   ;

 CURSOR c2 IS
    SELECT C.OID_CLIE_UNID_ADMI
    FROM
       SEG_PERIO_CORPO A,
       CRA_PERIO B,
       MAE_CLIEN_UNIDA_ADMIN C,
       SEG_PERIO_CORPO D,
       CRA_PERIO F
    WHERE C.CLIE_OID_CLIE = pnIdCliente
      AND C.PERD_OID_PERI_INI = B.OID_PERI
      AND A.OID_PERI = B.PERI_OID_PERI
      AND A.COD_PERI <= psCodPeriodo
      AND C.PERD_OID_PERI_FIN = F.OID_PERI
      AND D.OID_PERI = F.PERI_OID_PERI
      AND D.COD_PERI >= psCodPeriodo
   ORDER BY A.COD_PERI DESC   ;

BEGIN
 lnOidUnidad := -1;
 FOR cur1 IN c1 LOOP
     lbEntro := TRUE;
     lnOidUnidad := cur1.oid_clie_unid_admi;
     EXIT;
 END LOOP;
 IF NOT lbEntro  THEN
    FOR cur2 IN c2 LOOP
        lnOidUnidad := cur2.oid_clie_unid_admi;
        EXIT;
    END LOOP;
 END IF;
 RETURN lnOidUnidad;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_OID_UNADM_HISTO: '||ls_sqlerrm);
END COM_FN_DEVUE_OID_UNADM_HISTO;


/***************************************************************************
Descripcion       : Devuelve Responsable de la Unidad Administrativa en un
                    determinado periodo
Fecha Creacion    : 07/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_RESPO_UNIAD_HISTO
(psCodResponsable OUT VARCHAR2,
 pdFechaIniPeri   DATE,
 pdFechaFinPeri   DATE,
 pnIdPais         NUMBER,
 psCodSubGerencia VARCHAR2,
 psCodRegion      VARCHAR2,
 psCodZona        VARCHAR2,
 psCodSeccion     VARCHAR2:=NULL
)
RETURN VARCHAR2
IS
 lsBuscar         ZON_HISTO_GEREN.UA%TYPE;
 lsRetorno        ZON_HISTO_GEREN.GERE%TYPE;
 lsTempo          ZON_HISTO_GEREN.GERE%TYPE;
 lsNombres        VARCHAR2(500);
 ldFechaTempo     DATE;
 ldFecha          DATE;
 CURSOR C1(vsBuscar VARCHAR2)
 IS
   SELECT A.GERE
   FROM ZON_HISTO_GEREN A
   WHERE A.UA = vsBuscar
     AND A.FEC_DESD <= pdFechaIniPeri
     AND A.FEC_HAST IS NULL
   ORDER BY A.FEC_DESD DESC;

 CURSOR C2(vsBuscar VARCHAR2)
 IS
   SELECT A.GERE
   FROM ZON_HISTO_GEREN A
   WHERE A.UA = vsBuscar
     AND A.FEC_DESD <= pdFechaIniPeri
     AND A.FEC_HAST >=  pdFechaFinPeri
     AND A.FEC_HAST IS NOT NULL
   ORDER BY A.FEC_HAST DESC;

 CURSOR C3(vsBuscar VARCHAR2)
 IS
   SELECT A.GERE
   FROM ZON_HISTO_GEREN A
   WHERE A.UA = vsBuscar
     AND A.FEC_DESD >= pdFechaIniPeri
     AND A.FEC_DESD <  pdFechaFinPeri
     AND A.FEC_HAST IS NULL
   ORDER BY A.FEC_DESD DESC;

/* CURSOR C4(vsBuscar VARCHAR2)
 IS
   SELECT A.GERE
   FROM ZON_HISTO_GEREN A
   WHERE A.UA = vsBuscar
     AND A.FEC_DESD >= pdFechaIniPeri
     AND A.FEC_HAST <  pdFechaFinPeri
     AND A.FEC_HAST IS NOT NULL
   ORDER BY A.FEC_DESD DESC;
*/
 CURSOR C4A(vsBuscar VARCHAR2)
 IS
   SELECT A.GERE
   FROM ZON_HISTO_GEREN A
   WHERE A.UA = vsBuscar
     AND A.FEC_DESD >= pdFechaIniPeri
     AND A.FEC_DESD <  pdFechaFinPeri
     AND A.FEC_HAST >= pdFechaFinPeri
     AND A.FEC_HAST IS NOT NULL
   ORDER BY A.FEC_DESD DESC;

 CURSOR C5(vsBuscar VARCHAR2)
 IS
   SELECT A.GERE, A.FEC_HAST
   FROM ZON_HISTO_GEREN A
   WHERE A.UA = vsBuscar
     AND A.FEC_DESD <= pdFechaIniPeri
     AND A.FEC_HAST >= pdFechaIniPeri
     AND A.FEC_HAST < pdFechaFinPeri
     AND A.FEC_HAST IS NOT NULL
   ORDER BY A.FEC_DESD DESC;
BEGIN
 lsBuscar := psCodSubGerencia;
 IF (psCodRegion IS NOT NULL) THEN
    lsBuscar :=  lsBuscar || psCodRegion;
 END IF;
 IF (psCodZona IS NOT NULL) THEN
    lsBuscar :=  lsBuscar || psCodZona;
 END IF;
 IF (psCodSeccion IS NOT NULL) THEN
    lsBuscar :=  lsBuscar || psCodSeccion;
 END IF;

 /* Buscando codigo de Gerente en la tabla ZON_HISTO_GEREN */
 lsRetorno := '';
 psCodResponsable := '';
 FOR CURSOR1 IN C1(lsBuscar) LOOP
     lsRetorno := CURSOR1.GERE;
     EXIT;
 END LOOP;
 IF (lsRetorno IS NULL) THEN
     FOR CURSOR2 IN C2(lsBuscar) LOOP
         lsRetorno := CURSOR2.GERE;
         EXIT;
     END LOOP;
 END IF;
 IF (lsRetorno IS NULL) THEN
     FOR CURSOR3 IN C3(lsBuscar) LOOP
         lsRetorno := CURSOR3.GERE;
         EXIT;
     END LOOP;
 END IF;
/* IF (lsRetorno IS NULL) THEN
     FOR CURSOR4 IN C4(lsBuscar) LOOP
         lsRetorno := CURSOR4.GERE;
         EXIT;
     END LOOP;
 END IF;*/
 IF (lsRetorno IS NULL) THEN
     FOR CURSOR4A IN C4A(lsBuscar) LOOP
         lsRetorno := CURSOR4A.GERE;
         EXIT;
     END LOOP;
 END IF;

 IF (lsRetorno IS NULL) THEN
     FOR CURSOR5 IN C5(lsBuscar) LOOP
         lsTempo   := CURSOR5.GERE;
         ldFecha   := CURSOR5.Fec_Hast;
         ldFecha   := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(ldFecha);
         BEGIN
           SELECT A.FEC_DESD
           INTO ldFechaTempo
           FROM ZON_HISTO_GEREN A
           WHERE A.UA = lsBuscar
             AND A.FEC_DESD >= ldFecha
             AND A.FEC_DESD <= pdFechaFinPeri
             AND A.FEC_HAST >= pdFechaFinPeri
             AND rownum = 1;
           lsRetorno := lsTempo;
         EXCEPTION
         WHEN no_Data_found THEN
              EXIT;
         END ;
         EXIT;
     END LOOP;
 END IF;

 lsRetorno := TRIM(lsRetorno);
 psCodResponsable := lsRetorno;

 /* Encontrando nombre respectivo en la tabla MAE_CLIEN */
 IF (lsRetorno IS NOT NULL) THEN
     BEGIN
       SELECT TRIM(NVL(A.VAL_APE1,' ')) || ' ' ||
              TRIM(NVL(A.VAL_APE2,' ')) || ' ' ||
              TRIM(NVL(A.VAL_NOM1,' '))
       INTO lsNombres
       FROM
          MAE_CLIEN A
       WHERE  A.COD_CLIE = lsRetorno
          AND A.PAIS_OID_PAIS = pnIdPais;
       RETURN lsNombres;
     EXCEPTION
     WHEN NO_DATA_FOUND THEN
          RETURN '';
     END;
 END IF;
 RETURN lsRetorno;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_RESPO_UNIAD_HISTO: '||ls_sqlerrm);
END COM_FN_DEVUE_RESPO_UNIAD_HISTO;

/***************************************************************************
Descripcion       : Devuelve Rango de Fechas entre la que esta comprendio el periodo
                    ingresado como paramtero
Fecha Creacion    : 23/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_RANGO_FECHA_PERIO(
   psCodPeriodo VARCHAR2,
   pnIdPais     NUMBER,
   pnIdMarca    NUMBER,
   pnIdCanal    NUMBER,
   pdFechaIni   OUT DATE,
   pdFechaFin   OUT DATE,
   psMensaje    OUT VARCHAR2
)
RETURN NUMBER
IS
  lnIdPeri             NUMBER;
  lnIdPeri1            NUMBER;
  lsCodPeri1           seg_perio_corpo.cod_peri%TYPE;
  ldFechaIniSiguiente  DATE;

BEGIN
  lnIdPeri   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, pnIdMarca, pnIdCanal);
  lsCodPeri1 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, pnIdPais, pnIdMarca, pnIdCanal, 1);
  lnIdPeri1  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeri1, pnIdMarca, pnIdCanal);

  /* Obteniendo fecha inicial y fecha final del periodo del proceso */
  BEGIN
      SELECT
         A.FEC_INIC, A.FEC_FINA
      INTO
         pdFechaIni, pdFechaFin
      FROM
         CRA_PERIO A
      WHERE A.OID_PERI = lnIdPeri;
  EXCEPTION
  WHEN no_data_found THEN
       psMensaje := 'No se encontro Fecha de Inicio y Fecha Final para el Periodo de Proceso '|| psCodPeriodo;
       RETURN -1;
  WHEN OTHERS THEN
       psMensaje := substr(sqlerrm,1,250);
       RETURN -1;
  END;

  IF pdFechaIni IS NULL THEN
     psMensaje := 'Fecha de Inicio con valor NULO para el Periodo de Proceso '|| psCodPeriodo ;
     RETURN -1;
  END IF;
  IF pdFechaFin IS NULL THEN
     psMensaje := 'Fecha Final con valor NULO para el Periodo de Proceso '|| psCodPeriodo ;
     RETURN -1;
  END IF;

  /* Buscando fecha de incio del siguiente periodo */
  BEGIN
      SELECT
         A.FEC_INIC
      INTO
         ldFechaIniSiguiente
      FROM
         CRA_PERIO A
      WHERE A.OID_PERI = lnIdPeri1;
  EXCEPTION
  WHEN no_data_found THEN
       pdFechaFin := pdFechaFin + 1;
       RETURN 1;
  WHEN OTHERS THEN
       psMensaje := substr(sqlerrm,1,250);
       RETURN -1;
  END;

  /* obteniendo la menor fecha para asignarle a la Fecha Fin */
  IF ldFechaIniSiguiente < pdFechaFin THEN
     pdFechaFin := ldFechaIniSiguiente + 1;
  ELSE
     pdFechaFin := pdFechaFin + 1;
  END IF;
  RETURN 1;
END COM_FN_DEVUE_RANGO_FECHA_PERIO;


/***************************************************************************
Descripcion       : Devuelve Rango de Fechas entre la que esta comprendio el periodo
                    ingresado como paramtero
Fecha Creacion    : 23/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_RANGO_FECHA_PERIO(
   pnIdPeriodo  NUMBER,
   pnIdPais     NUMBER,
   pnIdMarca    NUMBER,
   pnIdCanal    NUMBER,
   pdFechaIni   OUT DATE,
   pdFechaFin   OUT DATE,
   psMensaje    OUT VARCHAR2
)
RETURN NUMBER
IS
  lnIdPeri1            NUMBER;
  lsCodPeri1           seg_perio_corpo.cod_peri%TYPE;
  ldFechaIniSiguiente  DATE;
  LsCodPeriodo         seg_perio_corpo.cod_peri%TYPE;

BEGIN
  SELECT A.COD_PERI
  INTO LsCodPeriodo
  FROM
     SEG_PERIO_CORPO A,
     CRA_PERIO B
  WHERE B.OID_PERI = pnIdPeriodo
    AND B.PERI_OID_PERI = A.OID_PERI ;

  lsCodPeri1 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(LsCodPeriodo, pnIdPais, pnIdMarca, pnIdCanal, 1);
  lnIdPeri1  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeri1, PnIdMarca, PnIdCanal);

  /* Obteniendo fecha inicial y fecha final del periodo del proceso */
  BEGIN
      SELECT
         A.FEC_INIC, A.FEC_FINA
      INTO
         pdFechaIni, pdFechaFin
      FROM
         CRA_PERIO A
      WHERE A.OID_PERI = pnIdPeriodo;
  EXCEPTION
  WHEN no_data_found THEN
       psMensaje := 'No se encontro Fecha de Inicio y Fecha Final para el Periodo de Proceso '|| LsCodPeriodo;
       RETURN -1;
  WHEN OTHERS THEN
       psMensaje := substr(sqlerrm,1,250);
       RETURN -1;
  END;

  IF pdFechaIni IS NULL THEN
     psMensaje := 'Fecha de Inicio con valor NULO para el Periodo de Proceso '|| LsCodPeriodo ;
     RETURN -1;
  END IF;
  IF pdFechaFin IS NULL THEN
     psMensaje := 'Fecha Final con valor NULO para el Periodo de Proceso '|| LsCodPeriodo ;
     RETURN -1;
  END IF;

  /* Buscando fecha de incio del siguiente periodo */
  BEGIN
      SELECT
         A.FEC_INIC
      INTO
         ldFechaIniSiguiente
      FROM
         CRA_PERIO A
      WHERE A.OID_PERI = lnIdPeri1;
  EXCEPTION
  WHEN no_data_found THEN
       pdFechaFin := pdFechaFin + 1;
       RETURN 1;
  WHEN OTHERS THEN
       psMensaje := substr(sqlerrm,1,250);
       RETURN -1;
  END;

  /* obteniendo la menor fecha para asignarle a la Fecha Fin */
  IF ldFechaIniSiguiente < pdFechaFin THEN
     pdFechaFin := ldFechaIniSiguiente + 1;
  ELSE
     pdFechaFin := pdFechaFin + 1;
  END IF;
  RETURN 1;
END COM_FN_DEVUE_RANGO_FECHA_PERIO;



/***************************************************************************
Descripcion       : Procedimiemto que genera data que sera mostrada en el Reporte de
                    Comision x Ingresos
Fecha Creacion    : 07/02/2007
Autor             : Carlos Bazalar
                    Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE COM_PR_GENER_LISTA_COMIS_INGRE( psCodPais     VARCHAR2,
                                          psCodMarca    VARCHAR2,
                                          psCodCanal    VARCHAR2,
                                          psCodComision VARCHAR2,
                                          psCodPeriodo  VARCHAR2,
                                          psAnioTramo   VARCHAR2,
                                          psNumTramo    VARCHAR2,
                                          psCodTipoCalculo VARCHAR2
                                        ) IS
  /* Declaracion de Variables */
  lnIdPais              SEG_PAIS.OID_PAIS%TYPE;
  lnIdCanal             SEG_CANAL.OID_CANA%TYPE;
  lnIdMarca             SEG_MARCA.OID_MARC%TYPE;
  lnIdUnidadAdm         MAE_CLIEN_UNIDA_ADMIN.OID_CLIE_UNID_ADMI%TYPE;
  lsCodPeriMas2         SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriSiguiente1   SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeri1            SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeri2            SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeri3            SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeri4            SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnIdPeriMas2          CRA_PERIO.OID_PERI%TYPE;
  lnIdPeriSiguiente1    CRA_PERIO.OID_PERI%TYPE;
  lnIdPeriActual        CRA_PERIO.OID_PERI%TYPE;
  lnIdPeri1             CRA_PERIO.OID_PERI%TYPE;
  lnIdPeri2             CRA_PERIO.OID_PERI%TYPE;
  lnIdPeri3             CRA_PERIO.OID_PERI%TYPE;
  lnIdPeri4             CRA_PERIO.OID_PERI%TYPE;

  lnIdProcCreacion      CCC_PROCE.OID_PROC%TYPE;

  lnIdSubprocCreacion   CCC_SUBPR.OID_SUBP%TYPE;
  lnIdEstado            PED_ESTAD_SOLIC.OID_ESTA_SOLI%TYPE;
  lnIdZona              ZON_ZONA.OID_ZONA%TYPE;
  lnIdSeccion           ZON_SECCI.OID_SECC%TYPE;
  ldFechaIniPeriodo     DATE;
  ldFechaFinPeriodo     DATE;
  lsNomLiderZona        VARCHAR2(500);
  lsNomLiderSeccion     VARCHAR2(500);
  lsCodZona             ZON_ZONA.COD_ZONA%TYPE;
  lsCodSecc             ZON_SECCI.COD_SECC%TYPE;
  lsCodRegi             ZON_REGIO.COD_REGI%TYPE;
  lsCodLiderZona        MAE_CLIEN.COD_CLIE%TYPE;
  lsCodLiderSeccion     MAE_CLIEN.COD_CLIE%TYPE;

  PORCENTAJE_COMISION     NUMBER:= 0.05;
  lnIdComision            NUMBER;
  lnSumaComision          NUMBER;
  lnSumaImporteNeto       NUMBER;
  lnContaImporteMayorCero NUMBER;
  lnSumaImporteCobranza   NUMBER;
  lsMensaje               VARCHAR2(255);
  lnValor                 NUMBER;
  regRegistro             tRegTablaClienComision;
  tablaRegistro           tTablaClienteComision;
  lnContador              NUMBER;
  lnPagina                NUMBER;
  lbDemandaAnticipada     BOOLEAN;
  lnNumDiasDemandaTramo1  NUMBER;
  lnNumDiasDemandaTramo2  NUMBER;
  lv_procesar             BOOLEAN;
  psCodTipoComision       VARCHAR2(1);

  CURSOR cursorComision(vnIdComision NUMBER)
  IS
  SELECT
     B.ZORG_OID_REGI
  FROM
     COM_COMIS A,
     COM_COMIS_CLIEN B
  WHERE  A.OID_COMI = vnIdComision
     AND A.OID_COMI = B.COMI_OID_COMI
     AND B.ZORG_OID_REGI IS NOT NULL;

  CURSOR cursorRegistro(vnIdPeri NUMBER, vnIdComision NUMBER, vnIdRegion NUMBER)
  IS
  SELECT DISTINCT
      C.OID_CLIE,
      C.COD_CLIE,
      TRIM(NVL(C.VAL_APE1,' ')) || ' ' ||
      TRIM(NVL(C.VAL_APE2,' ')) || ' ' ||
      TRIM(NVL(C.VAL_NOM1,' ')) AS NOM_CLIE,
      L.COD_SUBG_VENT,
      E.COD_REGI,
      F.COD_ZONA,
      G.COD_SECC,
      K.COD_TERR,
      F.OID_ZONA,
      G.OID_SECC,
      D.ZTAD_OID_TERR_ADMI AS OID_TERR_ADMI,
      0 AS IMP_PAGO_ACTU,
      0 AS IMP_PAGO_1,
      0 AS IMP_PAGO_2,
      0 AS IMP_PAGO_3,
      0 AS IMP_PAGO_4,
      0 AS IMP_PAGO_NETO,
      0 AS IMP_COMI,
      '' AS COD_LIDE_ZONA,
      '' AS NOM_LIDE_ZONA,
      '' AS COD_LIDE_SECC,
      '' AS NOM_LIDE_SECC,
      0 AS IMP_COBR_1,
      0 AS IMP_COBR_2,
      0 AS IMP_COBR_3,
      0 AS IMP_COBR_4,
      0 AS IMP_COBR_ACTU,
      0 AS IMP_COBR_TOTA
  FROM
     COM_COMIS A,
     COM_COMIS_CLIEN B,
     MAE_CLIEN C,
     MAE_CLIEN_UNIDA_ADMIN D,
     ZON_REGIO E,
     ZON_ZONA  F,
     ZON_SECCI G,
     ZON_TERRI_ADMIN H,
     MAE_CLIEN_HISTO_ESTAT I,
     MAE_ESTAT_CLIEN J,
     ZON_TERRI K,
     ZON_SUB_GEREN_VENTA L
  WHERE  A.OID_COMI = vnIdComision
     AND I.PERD_OID_PERI = vnIdPeri
     AND E.OID_REGI = vnIdRegion
     AND C.OID_CLIE = I.CLIE_OID_CLIE

     AND J.COD_ESTA_CLIE = '02'
     AND J.OID_ESTA_CLIE = I.ESTA_OID_ESTA_CLIE
     AND A.OID_COMI = B.COMI_OID_COMI
     AND E.OID_REGI = B.ZORG_OID_REGI
     AND E.OID_REGI = F.ZORG_OID_REGI
     AND F.OID_ZONA = G.ZZON_OID_ZONA
     AND G.OID_SECC = H.ZSCC_OID_SECC
     AND H.OID_TERR_ADMI = D.ZTAD_OID_TERR_ADMI
     AND C.OID_CLIE = D.CLIE_OID_CLIE
     AND K.OID_TERR = H.TERR_OID_TERR
     AND L.OID_SUBG_VENT = E.ZSGV_OID_SUBG_VENT
  ORDER BY E.COD_REGI, F.COD_ZONA, G.COD_SECC, K.COD_TERR, C.COD_CLIE ;

BEGIN
  lv_procesar:=FALSE;
  /* Borrando tabla temporal de Comision x Ingresos */
  DELETE FROM COM_REPOR_COMIS_INGRE A
  WHERE A.COD_PERI_ACTU = psCodPeriodo
    AND A.COD_COMI = psCodComision;

  /* Obteniendo oids */
  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lnIdPeriActual := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);
  lnIdComision   := COM_FN_DEVUE_ULTI_OID_COMI(psCodComision);

  /* obtenemos id subproceso banco*/
  DELETE FROM PER_GTT_TABLA_REPOS;
  INSERT INTO PER_GTT_TABLA_REPOS(CAM_NUME, CAM_CADE)
  SELECT C.OID_SUBP, D.TIP_COMI
  FROM  SEG_PAIS  A,
        CCC_PROCE B,
        CCC_SUBPR C,
        COM_COMIS_SUBPR_PAGO D
  WHERE A.OID_PAIS = lnIdPais
    AND B.PAIS_OID_PAIS = A.OID_PAIS
    AND B.OID_PROC = C.CCPR_OID_PROC
    AND D.COD_PAIS = psCodPais
    AND D.COD_COMI = COMISION_INGRESO
    AND B.COD_PROC = D.COD_PROC
    AND C.COD_SUBP = D.COD_SUBP;

  /* obtenemos id subproceso creacion */
  lnIdProcCreacion  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PROCESO(lnIdPais, 'CCC001');
  BEGIN
    SELECT a.oid_subp
    INTO lnIdSubprocCreacion
    FROM CCC_SUBPR A
    WHERE
       a.ccpr_oid_proc = lnIdProcCreacion AND
       a.cod_subp = 1;
  EXCEPTION
  WHEN no_Data_found THEN
       RAISE_APPLICATION_ERROR(-20123, 'No se encontro el ID del Subproceso de Creacion' );
  END;

  /* Obteniendo periodos anteriores */
  lsCodPeriSiguiente1 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, 1);
  lnIdPeriSiguiente1  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriSiguiente1, lnIdMarca, lnIdCanal);
  lsCodPeriMas2 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, 2);
  lnIdPeriMas2  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriMas2, lnIdMarca, lnIdCanal);

  lsCodPeri1 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
  lnIdPeri1  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeri1, lnIdMarca, lnIdCanal);
  lsCodPeri2 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -2);
  lnIdPeri2  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeri2, lnIdMarca, lnIdCanal);
  lsCodPeri3 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -3);
  lnIdPeri3  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeri3, lnIdMarca, lnIdCanal);
  lsCodPeri4 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -4);
  lnIdPeri4  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeri4, lnIdMarca, lnIdCanal);

  /* Obteniendo fecha inicial y fecha final del periodo del proceso */
  lnValor := COM_FN_DEVUE_RANGO_FECHA_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, ldFechaIniPeriodo, ldFechaFinPeriodo, lsMensaje);
  IF lnValor = -1 THEN
     RAISE_APPLICATION_ERROR(-20123, lsMensaje);
  END IF;

  /* Borrando informaci¿n en la tabla COM_COMIS_PERI_CALCU */
  DELETE FROM COM_COMIS_PERIO_CALCU
  WHERE PERI_COD_PERI = psCodPeriodo
    AND COD_COMI = psCodComision
    AND IND_COMI  = '01';

  /* obteniendo id de estado de pedidos */
  SELECT ES.OID_ESTA_SOLI
  INTO lnIdEstado
  FROM PED_ESTAD_SOLIC ES
  WHERE
       ES.COD_ESTA_SOLI = 'AN';

  /* Recorriendo Lista de Clientes */
  lnIdZona := -1;
  lnIdSeccion := -1;
  lsNomLiderZona    := '';
  lsNomLiderSeccion := '';
  lnSumaComision    := 0;
  lnSumaImporteNeto := 0;
  lnSumaImporteCobranza := 0;
  lnPagina := 0;
  lbDemandaAnticipada := TRUE;

  FOR curComis IN cursorComision(lnIdComision) LOOP
  lnPagina := lnPagina + 1;
  OPEN cursorRegistro(lnIdPeri4, lnIdComision, curComis.Zorg_Oid_Regi);
  LOOP
      FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
      IF tablaRegistro.COUNT > 0 THEN
        FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             regRegistro   := tablaRegistro(x);
             lnContador := x;

             lnIdUnidadAdm := COM_FN_DEVUE_OID_UNADM_HISTO(regRegistro.OID_CLIE, regRegistro.OID_TERR_ADMI, psCodPeriodo);
             IF lnIdUnidadAdm != -1 THEN


                        /* Verificando Demanda Anticipada */
                        lbDemandaAnticipada := TRUE;
                        BEGIN
                           SELECT A.NUM_DIAS_TRA1, A.NUM_DIAS_TRA2
                           INTO
                              lnNumDiasDemandaTramo1, lnNumDiasDemandaTramo2
                           FROM COM_ZONA_DEMAN_ANTIC A
                           WHERE A.COD_PAIS = psCodPais
                             AND A.COD_ZONA = regRegistro.COD_ZONA;
                        EXCEPTION
                        WHEN no_data_found THEN
                             lbDemandaAnticipada := FALSE;
                        END;

                        /* Obteniendo importes */
                        regRegistro.IMP_PAGO_ACTU := COM_FN_MONTO_COMIS_INGRE(lbDemandaAnticipada, lnIdPais, regRegistro.OID_CLIE, lnIdPeriMas2, lnIdPeriSiguiente1, lnIdPeriActual, lnIdPeri1, lnIdSubprocCreacion, regRegistro.OID_ZONA, lnIdEstado);
                        regRegistro.IMP_PAGO_1 := COM_FN_MONTO_COMIS_INGRE(lbDemandaAnticipada, lnIdPais, regRegistro.OID_CLIE, lnIdPeriSiguiente1, lnIdPeriActual, lnIdPeri1, lnIdPeri2, lnIdSubprocCreacion, regRegistro.OID_ZONA, lnIdEstado);
                        regRegistro.IMP_PAGO_2 := COM_FN_MONTO_COMIS_INGRE(lbDemandaAnticipada, lnIdPais, regRegistro.OID_CLIE, lnIdPeriActual, lnIdPeri1, lnIdPeri2, lnIdPeri3, lnIdSubprocCreacion, regRegistro.OID_ZONA, lnIdEstado);
                        regRegistro.IMP_PAGO_3 := COM_FN_MONTO_COMIS_INGRE(lbDemandaAnticipada, lnIdPais, regRegistro.OID_CLIE, lnIdPeri1, lnIdPeri2, lnIdPeri3, lnIdPeri4, lnIdSubprocCreacion, regRegistro.OID_ZONA, lnIdEstado);
                        regRegistro.IMP_PAGO_4 := COM_FN_MONTO_COMIS_INGRE(lbDemandaAnticipada, lnIdPais, regRegistro.OID_CLIE, lnIdPeri2, lnIdPeri3, lnIdPeri4, lnIdPeri4, lnIdSubprocCreacion, regRegistro.OID_ZONA, lnIdEstado);
                        regRegistro.IMP_PAGO_NETO := ROUND(regRegistro.IMP_PAGO_ACTU + regRegistro.IMP_PAGO_1 + regRegistro.IMP_PAGO_2 + regRegistro.IMP_PAGO_3 + regRegistro.IMP_PAGO_4, 2);

                        /* Encontrando Lider de Seccion */
                        IF regRegistro.OID_SECC <> lnIdSeccion THEN

                           /* Grabando importe en la tabla de Comisiones calculadas */
                           IF lsNomLiderSeccion IS NOT NULL AND lv_procesar THEN
                              INSERT INTO COM_COMIS_PERIO_CALCU
                              (PERI_COD_PERI, COD_COMI, COD_LIDE_SECC, NOM_LIDE_SECC,
                               IND_COMI, COD_REGI, COD_ZONA, COD_SECC, IMP_COMI,
                               IMP_NETO_SIN_RECL, IMP_COMI_DCTO, FEC_CALC)
                              VALUES
                               (psCodPeriodo, psCodComision , lsCodLiderSeccion, lsNomLiderSeccion,
                                '01', lsCodRegi, lsCodZona, lsCodSecc, lnSumaComision,
                                lnSumaImporteNeto, lnSumaImporteCobranza, SYSDATE);
                           END IF;

                           lnSumaComision    := 0;
                           lnSumaImporteNeto := 0;
                           lnSumaImporteCobranza := 0;
                           lnIdSeccion := regRegistro.OID_SECC;
                           lsCodSecc   := regRegistro.COD_SECC;
                           lsCodZona := regRegistro.COD_ZONA;
                           lsCodRegi := regRegistro.COD_REGI;
                           lsNomLiderSeccion := COM_FN_DEVUE_RESPO_UNIAD_HISTO(
                                             lsCodLiderSeccion,
                                             ldFechaIniPeriodo, ldFechaFinPeriodo,
                                             lnIdPais, regRegistro.COD_SUBG_VENT,
                                             regRegistro.COD_REGI,
                                             regRegistro.COD_ZONA,
                                             regRegistro.COD_SECC);

                           lv_procesar:= COM_FN_DEVUE_PROCE_TUTOR(psCodPais, psCodMarca,psCodCanal, lsCodLiderSeccion,psAnioTramo,psNumTramo,psCodTipoComision,psCodTipoCalculo);

                        END IF;
                        regRegistro.NOM_LIDE_SECC := lsNomLiderSeccion;

                        /* Encontrando Lider de Zona */
                        IF(lv_procesar)THEN
                        IF regRegistro.OID_ZONA <> lnIdZona THEN

                           lnIdZona  := regRegistro.OID_ZONA;
                           lsCodZona := regRegistro.COD_ZONA;
                           lsNomLiderZona := COM_FN_DEVUE_RESPO_UNIAD_HISTO(
                                          lsCodLiderZona,
                                          ldFechaIniPeriodo, ldFechaFinPeriodo,
                                          lnIdPais, regRegistro.COD_SUBG_VENT,
                                          regRegistro.COD_REGI,
                                          regRegistro.COD_ZONA,
                                          NULL);
                        END IF;
                        regRegistro.NOM_LIDE_ZONA := lsNomLiderZona;

                        /* Verificando que existan al menos 4 importes mayores a cero */
                        lnContaImporteMayorCero := 0;
                        IF regRegistro.IMP_PAGO_ACTU > 0 THEN
                           lnContaImporteMayorCero := lnContaImporteMayorCero + 1;
                        END IF;
                        IF regRegistro.IMP_PAGO_1 > 0 THEN
                           lnContaImporteMayorCero := lnContaImporteMayorCero + 1;
                        END IF;
                        IF regRegistro.IMP_PAGO_2 > 0 THEN
                           lnContaImporteMayorCero := lnContaImporteMayorCero + 1;
                        END IF;
                        IF regRegistro.IMP_PAGO_3 > 0 THEN
                           lnContaImporteMayorCero := lnContaImporteMayorCero + 1;
                        END IF;
                        IF regRegistro.IMP_PAGO_4 > 0 THEN
                           lnContaImporteMayorCero := lnContaImporteMayorCero + 1;
                        END IF;
                        lnSumaImporteNeto := lnSumaImporteNeto + regRegistro.IMP_PAGO_NETO;

                        /* Calculando comision */
                        IF lnContaImporteMayorCero >= 4 AND regRegistro.NOM_LIDE_SECC IS NOT NULL THEN
                           regRegistro.IMP_COMI := regRegistro.IMP_PAGO_ACTU +
                                                   regRegistro.IMP_PAGO_1 + regRegistro.IMP_PAGO_2 +
                                                   regRegistro.IMP_PAGO_3 + regRegistro.IMP_PAGO_4;
                           regRegistro.IMP_COMI := regRegistro.IMP_COMI * PORCENTAJE_COMISION;
                           lnSumaComision := lnSumaComision + regRegistro.IMP_COMI;

                           /* obtieniendo cobranzas */
                           regRegistro.IMP_COBR_1 := COM_FN_DEVUE_COBRA_CONSU(lsCodPeri1, regRegistro.COD_CLIE);
                           regRegistro.IMP_COBR_2 := COM_FN_DEVUE_COBRA_CONSU(lsCodPeri2, regRegistro.COD_CLIE);
                           regRegistro.IMP_COBR_3 := COM_FN_DEVUE_COBRA_CONSU(lsCodPeri3, regRegistro.COD_CLIE);
                           regRegistro.IMP_COBR_4 := COM_FN_DEVUE_COBRA_CONSU(lsCodPeri4, regRegistro.COD_CLIE);
                           regRegistro.IMP_COBR_ACTU := COM_FN_DEVUE_COBRA_CONSU(psCodPeriodo, regRegistro.COD_CLIE);
                           regRegistro.IMP_COBR_TOTA := regRegistro.IMP_COBR_1 + regRegistro.IMP_COBR_2 +
                                                        regRegistro.IMP_COBR_3 + regRegistro.IMP_COBR_4 +
                                                        regRegistro.IMP_COBR_ACTU;
                           lnSumaImporteCobranza := lnSumaImporteCobranza + regRegistro.IMP_COBR_TOTA;
                        END IF;

                        /* Insertando data en la tabla detalle de comision de ingreso */
                        INSERT INTO COM_REPOR_COMIS_INGRE
                         (OID_CLIE, COD_COMI, COD_CLIE, NOM_CLIE,
                          COD_SUBG_VENT, COD_REGI, COD_ZONA, COD_SECC,
                          COD_TERR, OID_ZONA, OID_SECC, OID_TERR_ADMI,
                          IMP_PAGO_ACTU, IMP_PAGO_1, IMP_PAGO_2, IMP_PAGO_3, IMP_PAGO_4,
                          IMP_PAGO_NETO, IMP_COMI, COD_LIDE_ZONA, NOM_LIDE_ZONA, COD_LIDE_SECC, NOM_LIDE_SECC,
                          COD_PERI_ACTU, COD_PERI_1, COD_PERI_2, COD_PERI_3, COD_PERI_4,
                          IMP_COBR_1, IMP_COBR_2, IMP_COBR_3, IMP_COBR_4,
                          IMP_COBR_ACTU, IMP_COBR_TOTA)
                        VALUES
                        ( regRegistro.OID_CLIE, psCodComision, regRegistro.COD_CLIE, regRegistro.NOM_CLIE,
                          regRegistro.COD_SUBG_VENT, regRegistro.COD_REGI, regRegistro.COD_ZONA, regRegistro.COD_SECC,
                          regRegistro.COD_TERR, regRegistro.OID_ZONA, regRegistro.OID_SECC, regRegistro.OID_TERR_ADMI,
                          regRegistro.IMP_PAGO_ACTU, regRegistro.IMP_PAGO_1, regRegistro.IMP_PAGO_2, regRegistro.IMP_PAGO_3, regRegistro.IMP_PAGO_4,
                          regRegistro.IMP_PAGO_NETO, regRegistro.IMP_COMI,
                          lsCodLiderZona, regRegistro.NOM_LIDE_ZONA, lsCodLiderSeccion, regRegistro.NOM_LIDE_SECC,
                          psCodPeriodo, lsCodPeri1, lsCodPeri2, lsCodPeri3, lsCodPeri4,
                          regRegistro.IMP_COBR_1, regRegistro.IMP_COBR_2, regRegistro.IMP_COBR_3, regRegistro.IMP_COBR_4,
                          regRegistro.IMP_COBR_ACTU, regRegistro.IMP_COBR_TOTA );
                     END IF;

             END IF;
         END LOOP;
      END IF;
      EXIT WHEN cursorRegistro%NOTFOUND;
  END LOOP;
  CLOSE cursorRegistro;

  END LOOP;
  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_GENER_LISTA_COMIS_INGRE: '||lnPagina||' '||lnContador||' '|| ls_sqlerrm);
END COM_PR_GENER_LISTA_COMIS_INGRE;

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de comisiones. Devuelve importe
                    de cobranza por periodo y codigo de consultora
Fecha Creacion    : 23/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_COBRA_CONSU(
   psCodPeriodo     VARCHAR2,
   psCodConsultora  VARCHAR2
)
RETURN NUMBER
IS
   lnImporteCobranza NUMBER;
BEGIN
   SELECT NVL(SUM(NVL(A.IMP_COM1,0)),0) + NVL(SUM(NVL(A.IMP_COM2,0)),0)
   INTO lnImporteCobranza
   FROM COM_COMIS_DSCTO A
   WHERE A.COD_PERI = psCodPeriodo
     AND A.COD_CONS = psCodConsultora
     AND A.IND_COMI = 'S';
   RETURN lnImporteCobranza;
EXCEPTION
WHEN no_data_found THEN
     RETURN 0.00;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_COBRA_CONSU: '||ls_sqlerrm);
END  COM_FN_DEVUE_COBRA_CONSU;

/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de comisiones. Genera los
                    importes de pago de acuerdo al cliente y periodo ingresados
Fecha Creacion    : 10/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_MONTO_COMIS_INGRE(
  vbDemandaAnticipada     BOOLEAN,
  vnIdPais                NUMBER,
  vnIdCliente             NUMBER,
  vnIdPeriodoMas2         NUMBER,
  vnIdPeriodoSiguiente    NUMBER,
  vnIdPeriodo             NUMBER,
  vnIdPeriodoAnte         NUMBER,
  vnIdSubprocCreacion     NUMBER,
  vnIdZona                NUMBER,
  vnIdEstadoPedido        NUMBER
)
RETURN NUMBER
IS
 TYPE tRegImporte IS RECORD (
     IMP_PAGO               CCC_MOVIM_CUENT_CORRI.Imp_Pago%TYPE,
     VAL_TASA_IMPU          PED_SOLIC_CABEC.VAL_TASA_IMPU%TYPE,
     OID_MOVI_CC            CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC%TYPE,
     PERD_OID_PERI          CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI%TYPE,
     FEC_VENC               DATE,
     FEC_DOCU               DATE,
     SUBP_OID_SUBP_ULTI     CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_ULTI%TYPE
 );

 TYPE tTablaImporte IS TABLE OF tRegImporte;
 regImporte                  tRegImporte;
 regImporteHisto             tRegImporte;
 tablaImporte                tTablaImporte;
 tablaImporteHisto           tTablaImporte;
 lnSumaImporte               NUMBER;
 lbContinuar                 BOOLEAN;
 ldFechaInicio               DATE;
 lnContadorVerifica          NUMBER;
 lbVerificarDiferencia       BOOLEAN;
 CANTIDAD_DIAS_QUIEBRE       NUMBER:=21;

 CURSOR cImporte IS
   SELECT A.IMP_PAGO,
          cons.val_tasa_impu,
          A.OID_MOVI_CC,
          A.PERD_OID_PERI,
          A.FEC_VENC,
          A.FEC_DOCU,
          A.SUBP_OID_SUBP_ULTI
   FROM CCC_MOVIM_CUENT_CORRI A,
        PED_SOLIC_CABEC CONS

   WHERE A.CLIE_OID_CLIE = vnIdCliente
     --AND (A.SUBP_OID_SUBP_ULTI = vnIdSubprocBanco OR A.SUBP_OID_SUBP_ULTI = vnIdSubprocBanco2)
     AND (A.PERD_OID_PERI = vnIdPeriodo OR A.PERD_OID_PERI =  vnIdPeriodoAnte)
     AND CONS.OID_SOLI_CABE = A.SOCA_OID_SOLI_CABE
     AND A.SUBP_OID_SUBP_CREA = vnIdSubprocCreacion
     AND A.IMP_MOVI_CUEN > 0 AND A.IMP_PEND <> A.IMP_MOVI ;

 CURSOR cImporteHisto  IS
   SELECT B.IMP_PAGO,
          CONS.VAL_TASA_IMPU,
          A.OID_MOVI_CC,
          A.PERD_OID_PERI,
          A.FEC_VENC,
          A.FEC_DOCU,
          B.SUBP_OID_SUBP
   FROM CCC_MOVIM_CUENT_CORRI A,
        CCC_HISTO_MOVIM_CC B,
        PED_SOLIC_CABEC CONS
   WHERE B.CLIE_OID_CLIE = vnIdCliente
     --AND (B.SUBP_OID_SUBP = vnIdSubprocBanco OR B.SUBP_OID_SUBP = vnIdSubprocBanco2)
     AND (A.PERD_OID_PERI = vnIdPeriodo OR A.PERD_OID_PERI =  vnIdPeriodoAnte)
     AND A.SUBP_OID_SUBP_CREA = vnIdSubprocCreacion
     AND A.OID_MOVI_CC = B.MVCC_OID_MOVI_CC
     AND cons.OID_SOLI_CABE = A.SOCA_OID_SOLI_CABE
     AND B.IMP_MOVI > 0 ;

BEGIN
  lnSumaImporte := 0;
  /* Recorriendo Lista de Importes (Actuales) */
  OPEN cImporte;
  LOOP
      FETCH cImporte BULK COLLECT INTO tablaImporte LIMIT W_FILAS;
      IF tablaImporte.COUNT > 0 THEN
        FOR y IN tablaImporte.FIRST .. tablaImporte.LAST LOOP
             regImporte    := tablaImporte(y);
             lbContinuar   := TRUE;
             /* obteniendo si corresponde al proceso de pago */
             lbVerificarDiferencia := TRUE;
             BEGIN
                 SELECT A.CAM_NUME
                 INTO lnContadorVerifica
                 FROM PER_GTT_TABLA_REPOS A
                 WHERE A.CAM_NUME = regImporte.SUBP_OID_SUBP_ULTI
                   AND A.CAM_CADE = 'P';
             EXCEPTION
             WHEN NO_DATA_FOUND THEN
                  lbVerificarDiferencia := FALSE;
             END;
             IF lbVerificarDiferencia THEN

                 IF vbDemandaAnticipada THEN
                     /* En caso el periodo anterior sea igual a la periodo */
                     IF vnIdPeriodo = vnIdPeriodoAnte THEN
                        IF regImporte.FEC_VENC - regImporte.FEC_DOCU < CANTIDAD_DIAS_QUIEBRE THEN
                           lbContinuar   := TRUE;
                        ELSE
                           lbContinuar   := FALSE;
                        END IF;
                     ELSE
                        IF regImporte.PERD_OID_PERI = vnIdPeriodo THEN -- PERIODO ACTUAL
                           ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodoMas2, vnIdZona, 'FA');
                           IF ldFechaInicio IS NULL THEN
                               lbContinuar := FALSE;
                           END IF;

                           IF lbContinuar THEN
                              IF regImporte.FEC_VENC <= ldFechaInicio THEN
                                 lbContinuar   := TRUE;
                              ELSE
                                 lbContinuar   := FALSE;
                              END IF;
                           END IF;

                        ELSE -- PERIODO ANTERIOR
                           ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodoSiguiente, vnIdZona, 'FA');
                           IF ldFechaInicio IS NULL THEN
                                lbContinuar := FALSE;
                           END IF;

                           IF lbContinuar THEN
                              IF regImporte.FEC_VENC > ldFechaInicio THEN
                                 lbContinuar   := TRUE;
                               ELSE
                                 lbContinuar   := FALSE;
                               END IF;
                           END IF;
                        END IF;
                     END IF;
                 ELSE
                     /* En caso el periodo anterior sea igual a la periodo */
                     IF vnIdPeriodo = vnIdPeriodoAnte THEN
                        IF regImporte.FEC_VENC - regImporte.FEC_DOCU < CANTIDAD_DIAS_QUIEBRE THEN
                           lbContinuar   := TRUE;
                        ELSE
                           lbContinuar   := FALSE;
                        END IF;
                     ELSE
                        IF regImporte.PERD_OID_PERI = vnIdPeriodo THEN -- PERIODO ACTUAL
                           ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodoSiguiente, vnIdZona, 'FA');
                           IF ldFechaInicio IS NULL THEN
                               lbContinuar := FALSE;
                           END IF;

                           IF lbContinuar THEN
                              IF regImporte.FEC_VENC <= ldFechaInicio THEN
                                 lbContinuar   := TRUE;
                              ELSE
                                 lbContinuar   := FALSE;
                              END IF;
                           END IF;

                        ELSE -- PERIODO ANTERIOR
                           ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodo, vnIdZona, 'FA');
                           IF ldFechaInicio IS NULL THEN
                                lbContinuar := FALSE;
                           END IF;

                           IF lbContinuar THEN
                              IF regImporte.FEC_VENC > ldFechaInicio THEN
                                 lbContinuar   := TRUE;
                               ELSE
                                 lbContinuar   := FALSE;
                               END IF;
                           END IF;
                        END IF;
                     END IF;
                 END IF;

                 IF lbContinuar THEN
                    lnSumaImporte := lnSumaImporte + (regImporte.IMP_PAGO/(1 + regImporte.VAL_TASA_IMPU/100));
                 END IF;
             END IF;

         END LOOP;
      END IF;
      EXIT WHEN cImporte%NOTFOUND;
  END LOOP;
  CLOSE cImporte;

  /* Recorriendo Lista de Importes (Historicos) */
  OPEN cImporteHisto;
  LOOP
      FETCH cImporteHisto BULK COLLECT INTO tablaImporteHisto LIMIT W_FILAS;
      IF tablaImporteHisto.COUNT > 0 THEN
        FOR y IN tablaImporteHisto.FIRST .. tablaImporteHisto.LAST LOOP
             regImporteHisto := tablaImporteHisto(y);
             lbContinuar   := TRUE;

             /* obteniendo si corresponde al proceso de pago */
             lbVerificarDiferencia := TRUE;
             BEGIN
                 SELECT A.CAM_NUME
                 INTO lnContadorVerifica
                 FROM PER_GTT_TABLA_REPOS A
                 WHERE A.CAM_NUME = regImporteHisto.SUBP_OID_SUBP_ULTI
                   AND A.CAM_CADE = 'P';
             EXCEPTION
             WHEN NO_DATA_FOUND THEN
                  lbVerificarDiferencia := FALSE;
             END;

             IF lbVerificarDiferencia THEN

                 IF vbDemandaAnticipada THEN
                     /* En caso el periodo anterior sea igual a la periodo */
                     IF vnIdPeriodo = vnIdPeriodoAnte THEN
                        IF regImporteHisto.FEC_VENC - regImporteHisto.FEC_DOCU < CANTIDAD_DIAS_QUIEBRE THEN
                           lbContinuar   := TRUE;
                        ELSE
                           lbContinuar   := FALSE;
                        END IF;
                     ELSE
                        IF regImporteHisto.PERD_OID_PERI = vnIdPeriodo THEN -- PERIODO ACTUAL
                           ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodoMas2, vnIdZona, 'FA');
                           IF ldFechaInicio IS NULL THEN
                               lbContinuar := FALSE;
                           END IF;

                           IF lbContinuar THEN
                              IF regImporteHisto.FEC_VENC <= ldFechaInicio THEN
                                 lbContinuar   := TRUE;
                              ELSE
                                 lbContinuar   := FALSE;
                              END IF;
                           END IF;

                        ELSE -- PERIODO ANTERIOR
                           ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodoSiguiente, vnIdZona, 'FA');
                           IF ldFechaInicio IS NULL THEN
                                lbContinuar := FALSE;
                           END IF;

                           IF lbContinuar THEN
                              IF regImporteHisto.FEC_VENC > ldFechaInicio THEN
                                 lbContinuar   := TRUE;
                               ELSE
                                 lbContinuar   := FALSE;
                               END IF;
                           END IF;
                        END IF;
                     END IF;
                 ELSE
                     /* En caso el periodo anterior sea igual a la periodo */
                     IF vnIdPeriodo = vnIdPeriodoAnte THEN
                        IF regImporteHisto.FEC_VENC - regImporteHisto.FEC_DOCU < CANTIDAD_DIAS_QUIEBRE THEN
                           lbContinuar   := TRUE;
                        ELSE
                           lbContinuar   := FALSE;
                        END IF;
                     ELSE
                        IF regImporteHisto.PERD_OID_PERI = vnIdPeriodo THEN -- PERIODO ACTUAL
                           ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodoSiguiente, vnIdZona, 'FA');
                           IF ldFechaInicio IS NULL THEN
                               lbContinuar := FALSE;
                           END IF;

                           IF lbContinuar THEN
                              IF regImporteHisto.FEC_VENC <= ldFechaInicio THEN
                                 lbContinuar   := TRUE;
                              ELSE
                                 lbContinuar   := FALSE;
                              END IF;
                           END IF;

                        ELSE -- PERIODO ANTERIOR
                           ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodo, vnIdZona, 'FA');
                           IF ldFechaInicio IS NULL THEN
                                 lbContinuar   := FALSE;
                               END IF;

                           IF lbContinuar THEN
                              IF regImporteHisto.FEC_VENC > ldFechaInicio THEN
                       lbContinuar   := TRUE;
                    ELSE
                       lbContinuar   := FALSE;
                    END IF;
                       END IF;
                       END IF;
                    END IF;
                 END IF;

                 IF lbContinuar THEN
                    lnSumaImporte := lnSumaImporte + (regImporteHisto.IMP_PAGO/(1 + regImporteHisto.VAL_TASA_IMPU/100));
                 END IF;
             END IF;
         END LOOP;
      END IF;
      EXIT WHEN cImporteHisto%NOTFOUND;
  END LOOP;
  CLOSE cImporteHisto;

  RETURN lnSumaImporte;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_MONTO_COMIS_INGRE: '||ls_sqlerrm);
END COM_FN_MONTO_COMIS_INGRE;


/***************************************************************************
Descripcion       : Devuelve Listado de Registros Estimados
Fecha Creacion    : 08/03/2007
Autor             : Jose Nunez Mori
***************************************************************************/
FUNCTION COM_FN_OBTIE_ESTIM(
    psCodPais    VARCHAR2,
    psCodSoc     VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodAlmac   VARCHAR2,
    psCodCanal   VARCHAR2,
    psRango      VARCHAR2,
    psAnio       VARCHAR2,
    psIndicador  VARCHAR2,
    psCodRegi    VARCHAR2,
    psCodZona    VARCHAR2
)
RETURN  TABLA_COMIS_ESTIMADO PIPELINED
IS
  tablaidPerio  TABLA_VENTA_IDPER;
  REGPER        tRegIdPeriodo;
  tablaEstimado TABLA_COMIS_ESTIMADO;
  --CURSOR QUE CONTIENE EL LISTADO DE PERIODOS A TRABAJAR
  CURSOR cursorEstimado1
   (vnIdPais NUMBER, vsPeriini VARCHAR2, vsPeriFina VARCHAR2, vnIdMarca NUMBER, vnIdCanal NUMBER)
  IS
    SELECT C.OID_PERI
    FROM SEG_PERIO_CORPO S,
         CRA_PERIO C
    WHERE  (S.COD_PERI>vsPeriini AND S.COD_PERI<vsPeriFina)
        AND S.OID_PERI = C.PERI_OID_PERI
        AND C.PAIS_OID_PAIS=vnIdPais
        AND C.MARC_OID_MARC=vnIdMarca
        AND C.CANA_OID_CANA=vnIdCanal;

    -- CURSOR QUE SE UTLIZARA EN PARA LA SELECCION DE PAIS
    CURSOR cursorEstimadoPais
      (vnIdPeri NUMBER, vnIdAlmac NUMBER, vnIdSoc NUMBER, vnIdPais NUMBER)
    IS
        SELECT I.PERD_OID_PERI,
               I.NUM_ACTI_INIC,
               I.NUM_INGR,
               I.NUM_REIN,
               I.NUM_EGRE,
               I.NUM_PEDI,
               I.NUM_UNID_VEND,
               I.NUM_CLIE,
               I.NUM_ACTI_FINA,
               I.VAL_VENT_NETA_ESTA,
               DECODE(I.NUM_ACTI_INIC,0,0, ROUND((I.NUM_EGRE*100)/I.NUM_ACTI_INIC,2)) AS POR_EGRE,
               ROUND((I.NUM_INGR+I.NUM_REIN)-I.NUM_EGRE) AS CAPITAL,
               DECODE(I.NUM_ACTI_FINA,0,0, ROUND((I.NUM_PEDI/I.NUM_ACTI_FINA)*100,2)) AS POR_ACTI,
               DECODE(I.NUM_UNID_VEND,0,0, ROUND(I.VAL_VENT_NETA_ESTA/I.NUM_UNID_VEND,2)) AS PPU,
               DECODE(I.NUM_PEDI,0,0, ROUND(I.NUM_UNID_VEND/I.NUM_PEDI,2)) AS PUP,
               DECODE(I.NUM_PEDI,0,0, ROUND(I.VAL_VENT_NETA_ESTA/I.NUM_PEDI,2))  AS PRO_SOL_VEN,
               '',
               '',
               '',
               ''
        FROM INT_FUENT_VENTA_PREVI_SAP I
        WHERE I.PERD_OID_PERI= vnIdPeri
              AND I.ALMC_OID_ALMA=vnIdAlmac
              AND I.SOCI_OID_SOCI=vnIdSoc
              AND I.ZORG_OID_REGI IN
              (
                SELECT R.OID_REGI
                FROM ZON_REGIO R
                WHERE R.PAIS_OID_PAIS=vnIdPais
              );
    -- CURSOR QUE SE UTLIZARA EN PARA LA SELECCION DE REGION
    CURSOR cursorEstimadoReg
    (vnIdPeri NUMBER, vnIdAlmac NUMBER, vnIdSoc NUMBER, vnIdReg NUMBER)
    IS
      SELECT I.PERD_OID_PERI,
             I.NUM_ACTI_INIC,
             I.NUM_INGR,
             I.NUM_REIN,
             I.NUM_EGRE,
             I.NUM_PEDI,
             I.NUM_UNID_VEND,
             I.NUM_CLIE,
             I.NUM_ACTI_FINA,
             I.VAL_VENT_NETA_ESTA,
             DECODE(I.NUM_ACTI_INIC,0,0, ROUND((I.NUM_EGRE*100)/I.NUM_ACTI_INIC,2)) AS POR_EGRE,
             ROUND((I.NUM_INGR+I.NUM_REIN)-I.NUM_EGRE) AS CAPITAL,
             DECODE(I.NUM_ACTI_FINA,0,0, ROUND((I.NUM_PEDI/I.NUM_ACTI_FINA)*100,2)) AS POR_ACTI,
             DECODE(I.NUM_UNID_VEND,0,0, ROUND(I.VAL_VENT_NETA_ESTA/I.NUM_UNID_VEND,2)) AS PPU,
             DECODE(I.NUM_PEDI,0,0, ROUND(I.NUM_UNID_VEND/I.NUM_PEDI,2)) AS PUP,
             DECODE(I.NUM_PEDI,0,0, ROUND(I.VAL_VENT_NETA_ESTA/I.NUM_PEDI,2))  AS PRO_SOL_VEN,
             '',
             '',
             '',
             ''
      FROM INT_FUENT_VENTA_PREVI_SAP I
      WHERE I.PERD_OID_PERI= vnIdPeri
            AND I.ALMC_OID_ALMA=vnIdAlmac
            AND I.SOCI_OID_SOCI=vnIdSoc
            AND I.ZZON_OID_ZONA IN
            (
              SELECT Z.OID_ZONA
              FROM ZON_ZONA Z
              WHERE Z.ZORG_OID_REGI=vnIdReg
            );
     -- CURSOR QUE SE UTLIZARA EN PARA LA SELECCION DE ZONA
    CURSOR cursorEstimadoZona
    (vnIdPeri NUMBER, vnIdAlmac NUMBER, vnIdSoc NUMBER, vnIdZona NUMBER)
    IS
      SELECT I.PERD_OID_PERI,
             I.NUM_ACTI_INIC,
             I.NUM_INGR,
             I.NUM_REIN,
             I.NUM_EGRE,
             I.NUM_PEDI,
             I.NUM_UNID_VEND,
             I.NUM_CLIE,
             I.NUM_ACTI_FINA,
             I.VAL_VENT_NETA_ESTA,
             DECODE(I.NUM_ACTI_INIC,0,0, ROUND((I.NUM_EGRE*100)/I.NUM_ACTI_INIC,2)) AS POR_EGRE,
             ROUND((I.NUM_INGR+I.NUM_REIN)-I.NUM_EGRE) AS CAPITAL,
             DECODE(I.NUM_ACTI_FINA,0,0, ROUND((I.NUM_PEDI/I.NUM_ACTI_FINA)*100,2)) AS POR_ACTI,
             DECODE(I.NUM_UNID_VEND,0,0, ROUND(I.VAL_VENT_NETA_ESTA/I.NUM_UNID_VEND,2)) AS PPU,
             DECODE(I.NUM_PEDI,0,0, ROUND(I.NUM_UNID_VEND/I.NUM_PEDI,2)) AS PUP,
             DECODE(I.NUM_PEDI,0,0, ROUND(I.VAL_VENT_NETA_ESTA/I.NUM_PEDI,2)) AS PRO_SOL_VEN,
             '',
             '',
             '',
             ''
      FROM INT_FUENT_VENTA_PREVI_SAP I
      WHERE I.PERD_OID_PERI= vnIdPeri
            AND I.ALMC_OID_ALMA=vnIdAlmac
            AND I.SOCI_OID_SOCI=vnIdSoc
            AND I.ZZON_OID_ZONA= vnIdZona;

  --Declaracion de variables a usar
 lnIdPais         SEG_PAIS.OID_PAIS%TYPE;
 lnIdAlmac        BEL_ALMAC.OID_ALMA%TYPE;
 lnIdSoc          SEG_SOCIE.OID_SOCI%TYPE;
 lnIdMarca        SEG_MARCA.OID_MARC%TYPE;
 lnIdCanal        SEG_CANAL.OID_CANA%TYPE;
 lnIdReg          ZON_REGIO.OID_REGI%TYPE;
 lnIdZona         ZON_ZONA.OID_ZONA%TYPE;
 lsCodPeriInicial seg_perio_corpo.cod_peri%TYPE;
 lsCodPeriFinal   seg_perio_corpo.cod_peri%TYPE;
 ls_res_regio     VARCHAR2(100);
 ls_res_zona      VARCHAR2(100);
BEGIN
    lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);-- id del pais consultante
    lnIdAlmac := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ALMAC(psCodAlmac,lnIdPais);-- iD  DE ALAMCEN
    lnIdSoc   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_SOCIE(psCodSoc); -- iD  DE LA SOCIEDAD
    lnIdMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca); -- iD  DE LA MARCA
    lnIdCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);-- iD  DE CANAL
    IF (psIndicador='R' OR psIndicador='Z') THEN
        lnIdReg   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_REGION(psCodPais, psCodMarca, psCodCanal, psCodRegi);
    END IF;
    IF (psIndicador='Z') THEN
       lnIdZona  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ZONA(psCodPais, psCodMarca, psCodCanal, psCodRegi, psCodZona);-- iD  DE LA ZONA
    END IF;
    VEN_PKG_REPOR.VEN_PR_DEVUE_RANGO_CODI_PERI(psRango, psAnio, lsCodPeriInicial, lsCodPeriFinal);
    --EJECUTANDO CURSORES
    OPEN cursorEstimado1(lnIdPais,lsCodPeriInicial,lsCodPeriFinal,lnIdMarca,lnIdCanal);
      LOOP
          FETCH cursorEstimado1 BULK COLLECT INTO tablaidPerio LIMIT W_FILAS;
           IF tablaidPerio.COUNT > 0 THEN
             FOR x IN tablaidPerio.FIRST .. tablaidPerio.LAST LOOP
                 REGPER:=tablaidPerio(x);
                 --PROCESAMIENTO DE LA SELECCION DEL PAIS
                 IF(psIndicador='P')THEN
                       OPEN cursorEstimadoPais
                               (REGPER.PERD_OID_PERI,lnIdAlmac,lnIdSoc,lnIdPais);
                        LOOP
                           FETCH cursorEstimadoPais BULK COLLECT INTO tablaEstimado LIMIT W_FILAS;
                           IF tablaEstimado.COUNT > 0 THEN
                             FOR y IN tablaEstimado.FIRST .. tablaEstimado.LAST LOOP
                                tablaEstimado(y).cod_peri_inic := lsCodPeriInicial;
                                tablaEstimado(y).cod_peri_fina := lsCodPeriFinal;
                                PIPE ROW(tablaEstimado(y));
                             END LOOP;
                           END IF;
                           EXIT WHEN cursorEstimadoPais%NOTFOUND;
                        END LOOP;
                        CLOSE cursorEstimadoPais;
                 END IF;
                  --PROCESAMIENTO DE LA SELECCION REGION
                 IF(psIndicador='R')THEN
                       ls_res_regio := GEN_PKG_GENER.PER_FN_RECUP_RESPO_UNIAD
                          (psCodPais, psCodMarca, psCodCanal, psCodRegi, NULL, NULL, 'N');
                       OPEN cursorEstimadoReg (REGPER.PERD_OID_PERI,lnIdAlmac,lnIdSoc,lnIdReg);
                        LOOP
                           FETCH cursorEstimadoReg BULK COLLECT INTO tablaEstimado LIMIT W_FILAS;
                           IF tablaEstimado.COUNT > 0 THEN
                             FOR y IN tablaEstimado.FIRST .. tablaEstimado.LAST LOOP
                                tablaEstimado(y).res_regi := ls_res_regio;
                                tablaEstimado(y).cod_peri_inic := lsCodPeriInicial;
                                tablaEstimado(y).cod_peri_fina := lsCodPeriFinal;
                                PIPE ROW(tablaEstimado(y));
                             END LOOP;
                           END IF;
                           EXIT WHEN cursorEstimadoReg%NOTFOUND;
                        END LOOP;
                        CLOSE cursorEstimadoReg;
                 END IF;
                  --PROCESAMIENTO DE LA SELECCION DE ZONA
                 IF(psIndicador='Z')THEN
                       ls_res_regio := GEN_PKG_GENER.PER_FN_RECUP_RESPO_UNIAD
                          (psCodPais, psCodMarca, psCodCanal, psCodRegi, NULL, NULL, 'N');
                       ls_res_zona := GEN_PKG_GENER.PER_FN_RECUP_RESPO_UNIAD
                          (psCodPais, psCodMarca, psCodCanal, psCodRegi, psCodZona, NULL, 'N');
                       OPEN cursorEstimadoZona (REGPER.PERD_OID_PERI,lnIdAlmac,lnIdSoc,lnIdZona);
                        LOOP
                           FETCH cursorEstimadoZona BULK COLLECT INTO tablaEstimado LIMIT W_FILAS;
                           IF tablaEstimado.COUNT > 0 THEN
                             FOR y IN tablaEstimado.FIRST .. tablaEstimado.LAST LOOP
                                tablaEstimado(y).res_regi := ls_res_regio;
                                tablaEstimado(y).res_zona := ls_res_zona;
                                tablaEstimado(y).cod_peri_inic := lsCodPeriInicial;
                                tablaEstimado(y).cod_peri_fina := lsCodPeriFinal;
                                PIPE ROW(tablaEstimado(y));
                             END LOOP;
                           END IF;
                           EXIT WHEN cursorEstimadoZona%NOTFOUND;
                        END LOOP;
                        CLOSE cursorEstimadoZona;
                 END IF;
             END LOOP;
           END IF;
           EXIT WHEN cursorEstimado1%NOTFOUND ;
      END LOOP ;
      CLOSE cursorEstimado1;
      RETURN;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_OBTIE_ESTIM: '||ls_sqlerrm);
END COM_FN_OBTIE_ESTIM;

/***************************************************************************
Descripcion       : Procedimiemto que genera data para el Calculo de Comision
                    de Recuperacion que ser¿ mostrado en los reportes respectivos.
Parametros        : psTipoComision :
                           'L'  Lider
                           'G'  Gerente de Zona
                           'Gr' Gerente Regional
Fecha Creacion    : 07/03/2007
Autor             : Carlos Bazalar
                    Leonardo Lizana
                    Telly Tataje

Modificacion            : 14/07/2010
Analista Desarrollador  : David Ramos
NOTA IMPORTANTE ESTE PACKAGE ESTA SUFRIENDO CAMBIOS DEBIDO ALA
IMPLEMENTACION DE LAS COMISIONES ESCALONADAS
***************************************************************************/

PROCEDURE COM_PR_GENER_LISTA_COMIS_RECUP(
                                        psCodPais       VARCHAR2,
                                        psCodMarca      VARCHAR2,
                                        psCodCanal      VARCHAR2,
                                        psCodComision   VARCHAR2,
                                        psCodPeriodo    VARCHAR2,
                                        psTipoComision  VARCHAR2,
                                        psUsuario       VARCHAR2,
                                        psAnioTramo   VARCHAR2,
                                        psNumTramo    VARCHAR2,
                                        psCodTipoCalculo VARCHAR2,
                                        psCodigoGerenteBase07 VARCHAR2,
                                        psCodigoRegionBase07 VARCHAR2,
                                        psCodigoZonaBase07 VARCHAR2,
                                        psfechaRetiroBase07 VARCHAR2
                                        )IS
  /* Declaracion de Variables */
  lnIdPais                SEG_PAIS.OID_PAIS%TYPE;
  lnIdCanal               SEG_CANAL.OID_CANA%TYPE;
  lnIdMarca               SEG_MARCA.OID_MARC%TYPE;
  lnIdPeriActual          CRA_PERIO.OID_PERI%TYPE;

  --lnIdProcCreacion        CCC_PROCE.OID_PROC%TYPE;
  lnIdPeriAnterior        CCC_PROCE.OID_PROC%TYPE;
  lnIdPeriSiguiente       CCC_PROCE.OID_PROC%TYPE;
  lnIdPeriMas2            CCC_PROCE.OID_PROC%TYPE;
  --lnIdSubprocCreacion     CCC_SUBPR.OID_SUBP%TYPE;
  lnPag                   NUMBER;
  lnX                     NUMBER;

  lnIdZona                ZON_ZONA.OID_ZONA%TYPE;
  lnIdSeccion             ZON_SECCI.OID_SECC%TYPE;
  lnIdRegion              ZON_REGIO.OID_REGI%TYPE;
  lnNumDiasComi           COM_COMIS_COBRA_TIPO_PARTI.NUM_DIAS_COMI%TYPE;
  lnValPorceRecu          COM_COMIS_COBRA_TIPO_PARTI.VAL_PORC_RECU%TYPE;
  lnValPorceComi          COM_COMIS_COBRA_TIPO_PARTI.VAL_PORC_COMI%TYPE;
  lnNumDiasComi2          COM_COMIS_COBRA_TIPO_PARTI.NUM_DIAS_COMI%TYPE;
  lnValPorceRecu2         COM_COMIS_COBRA_TIPO_PARTI.VAL_PORC_RECU%TYPE;
  lnValPorceComi2         COM_COMIS_COBRA_TIPO_PARTI.VAL_PORC_COMI%TYPE;

  lsCodLiderZona          MAE_CLIEN.COD_CLIE%TYPE;
  lsCodLiderSeccion       MAE_CLIEN.COD_CLIE%TYPE;
  lsCodLiderRegion        MAE_CLIEN.COD_CLIE%TYPE;
  lnIdEstado              MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE;
  lsCodPeriIniConsultora  SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnIdPeriIniConsultora   MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;
  lsCodPeriDescuento      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriAnterior       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriSiguiente      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriMas2           SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodSeccion            ZON_SECCI.COD_SECC%TYPE;
  lsCodZona               ZON_ZONA.COD_ZONA%TYPE;
  lsCodRegion             ZON_REGIO.COD_REGI%TYPE;
  regRecuperacion         COM_REPOR_COMIS_RECUP%ROWTYPE;
  lv_procesar             BOOLEAN;

  lsNomLiderZona          VARCHAR2(500);
  lsNomLiderSeccion       VARCHAR2(500);
  lsNomLiderRegion        VARCHAR2(500);
  lnIdComision            NUMBER;
  ldFechaIniPeriodo       DATE;
  ldFechaFinPeriodo       DATE;
  ldFechaDesde            DATE;
  ldFechaHasta            DATE;
  ldFechaIngreso          DATE;
  lbInsertar              BOOLEAN;
  lbDemandaAnticipada     BOOLEAN;
  lnNumDiasDemandaTramo1   NUMBER;
  lnNumDiasDemandaTramo2   NUMBER;

  lnSumaComisionTramo1    NUMBER;
  lnSumaComisionTramo2    NUMBER;
  lnExisteRegion          NUMBER;
  lnIdUnidadAdm           NUMBER;
  lnIdEstadoPedido        NUMBER;
  lnIdPeriSaldo           number;
  lbInsertarZona          BOOLEAN;
  lsCodPlanilla           VARCHAR2(100);
  lnValor                 NUMBER;
  lsMensaje               VARCHAR2(255);
  W_FILAS                 NUMBER:=5000;
  regRegistro             tRegClienteComisionRecupe;
  tablaRegistro           tTablaClienteComisionRecupe;
  lsCodComisionista       com_comis_dscto.cod_clie_comi%TYPE;

  lnCantRegParamZona      NUMBER;
  lnFlagWeekEnd           com_comis.ind_cons_saba_domi_feri%TYPE;
  lnValPorceActi          COM_COMIS_COBRA_TIPO_PARTI.VAL_PORC_ACTI%TYPE;
  lnValPorceActi2         COM_COMIS_COBRA_TIPO_PARTI.VAL_PORC_ACTI%TYPE;
  lnIndDctoImportePago    com_comis.ind_desc_impo_vent_pago%TYPE;
  lnIndDctoReclamo        com_comis.ind_desc_recl_fact%TYPE;
  lnValorIGVVen           ven_porce_igv.val_porce_igv%TYPE;

  lnImporteVenta          COM_PARAM_ZONAS_NUEVA.IMP_VENT_NETA%TYPE;
  lnPorRecupera           COM_PARAM_ZONAS_NUEVA.POR_RECU%TYPE;
  lnPorActivida           COM_PARAM_ZONAS_NUEVA.POR_ACTI%TYPE;
  lnValPorceActiCOMI      COM_COMIS_COBRA_TIPO_PARTI.VAL_PORC_COMI_ACTI%TYPE;
  lnMontoBono             COM_PARAM_ZONAS_NUEVA.IMP_BONO%TYPE;
  lsCodPeriSaldo          SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsBaseComision          COM_COMIS.COBA_COD_BASE%TYPE;

  CURSOR cursorRegion(vnIdPais NUMBER, vnIdMarca NUMBER, vnIdCanal NUMBER)
  IS
  SELECT
     A.ZORG_OID_REGI, A.OID_ZONA
  FROM
     ZON_ZONA A
  WHERE  A.PAIS_OID_PAIS = vnIdPais
     AND A.CANA_OID_CANA = vnIdCanal
     AND A.MARC_OID_MARC = vnIdMarca
     AND A.IND_ACTI = '1';

  CURSOR cursorComision(vnIdComision NUMBER)
  IS
  SELECT
     B.ZORG_OID_REGI, B.ZZON_OID_ZONA
  FROM
     COM_COMIS_CLIEN B
  WHERE B.COMI_OID_COMI = vnIdComision
    AND B.ZORG_OID_REGI IS NOT NULL;

  CURSOR cursorRegistro
  IS
  SELECT
    OID_CLIE,
    COD_CLIE,
    NOM_CLIE,
    OID_REGI,
    COD_REGI,
    OID_ZONA,
    COD_ZONA,
    COD_SUBG_VENT,
    OID_SECC,
    COD_SECC,
    COD_TERR,
    OID_TERR_ADMI
  FROM
     COM_TEMPO_COMIS_RECUP A
  ORDER BY  A.COD_REGI, A.COD_ZONA, A.COD_SECC, A.COD_TERR, A.COD_CLIE ;

  TYPE tTablaCodCliente IS TABLE OF MAE_CLIEN.COD_CLIE%TYPE;
  tablaRegistroLideres  tTablaCodCliente;

  CURSOR cPoseeComi
  IS
    SELECT A.COD_LIDE_SECC
    FROM COM_COMIS_PERIO_CALCU A
    WHERE A.PERI_COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision
      AND A.IND_COMI = COMISION_RECUPERACION
      AND (A.IMP_COMI_TRA1 > 0 OR A.IMP_COMI_TRA2 > 0);

  CURSOR cPoseeComiSoloTramo1
  IS
    SELECT A.COD_LIDE_SECC
    FROM COM_COMIS_PERIO_CALCU A
    WHERE A.PERI_COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision
      AND A.IND_COMI = COMISION_RECUPERACION
      AND A.IMP_COMI_TRA1 > 0
      AND A.IMP_COMI_TRA2 = 0;

  CURSOR cPoseeComiSoloTramo2
  IS
    SELECT A.COD_LIDE_SECC
    FROM COM_COMIS_PERIO_CALCU A
    WHERE A.PERI_COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision
      AND A.IND_COMI = COMISION_RECUPERACION
      AND A.IMP_COMI_TRA2 > 0
      AND A.IMP_COMI_TRA1 = 0;

  ls_evalua_telecobro  BAS_PARAM_PAIS.VAL_PARA%type;
  lbEsSoloTramo1       boolean;
  lsIndicadorCrono     com_comis.ind_cron%type;
  lnPorcenVentaConsu   com_comis.POR_VENT_CONS%type;
  ldFechaRetiroBase07  DATE;

BEGIN
  --DELETE FROM com_tempo_comis_recup;
  EXECUTE IMMEDIATE 'TRUNCATE TABLE com_tempo_comis_recup';

  /* Obtenemos los valores de los flags y el porcentaje de actividad */
  SELECT NVL(A.IND_CONS_SABA_DOMI_FERI,0), NVL(A.IND_DESC_IMPO_VENT_PAGO,0),
         NVL(IND_DESC_RECL_FACT,0), A.COBA_COD_BASE,
         NVL(A.IND_CRON, '0'), NVL(A.POR_VENT_CONS, 0)
  INTO lnFlagWeekEnd, lnIndDctoImportePago, lnIndDctoReclamo, lsBaseComision, lsIndicadorCrono, lnPorcenVentaConsu
  FROM COM_COMIS A
  WHERE A.COD_COMI = psCodComision;

  IF lsBaseComision <> '07' THEN
  /* Borrando tabla temporal de Comision por Recuperacion */
  DELETE FROM COM_REPOR_COMIS_RECUP A
  WHERE A.COD_PERI = psCodPeriodo
    AND A.COD_COMI = psCodComision;

  IF psTipoComision = '03' THEN --antes L
      /* Borrando informaci?n en la tabla COM_COMIS_PERI_CALCU */
      DELETE FROM COM_COMIS_PERIO_CALCU
      WHERE PERI_COD_PERI = psCodPeriodo
        AND COD_COMI = psCodComision
        AND IND_COMI  = COMISION_RECUPERACION;
  END IF;

  IF psTipoComision = '02' THEN --antes G
      /* Borrando informaci?n en la tabla COM_COMIS_PERI_CALCU_ZONA */
      DELETE FROM COM_COMIS_PERIO_CALCU_ZONA
      WHERE PERI_COD_PERI = psCodPeriodo
        AND COD_COMI = psCodComision;
  END IF;

  IF psTipoComision = '01' THEN --antes Gr
      /* Borrar  informaci?n en la tabla COM_COMIS_PERIO_CALCU_REGIO */
      DELETE FROM COM_COMIS_PERIO_CALCU_REGIO
      WHERE COD_PERI = psCodPeriodo
        AND COD_COMI = psCodComision
        AND IND_COMI  = COMISION_RECUPERACION;
  END IF;

  /* Borrando informaci?n en la tabla COM_COMIS_DSCTO */
  DELETE FROM COM_COMIS_DSCTO
  WHERE COD_PERI = psCodPeriodo
    AND COD_COMI = psCodComision;

  /* Borrando informaci?n en las tablas COM_RECUP_CAMPA_ZONA y COM_RECUP_CAMPA_SECCI */
  DELETE FROM COM_RECUP_CAMPA_ZONA A
  WHERE A.COD_PERI = psCodPeriodo
    AND A.COD_COMI = psCodComision ;

  DELETE FROM COM_RECUP_CAMPA_SECCI A
  WHERE A.COD_PERI = psCodPeriodo
    AND A.COD_COMI = psCodComision ;

  DELETE FROM COM_RECUP_CAMPA_REGIO A
  WHERE A.COD_PERI = psCodPeriodo
    AND A.COD_COMI = psCodComision ;
  ELSE
      ldFechaRetiroBase07 := NULL;
      IF psfechaRetiroBase07 IS NOT NULL THEN
         ldFechaRetiroBase07 := to_date(psfechaRetiroBase07, 'dd/mm/yyyy');
      END IF;

      IF psTipoComision = '01' THEN --antes Gr
          /* Borrando tabla temporal de Comision por Recuperacion */
          DELETE FROM COM_REPOR_COMIS_RECUP A
          WHERE A.COD_PERI = psCodPeriodo
            AND A.COD_COMI = psCodComision
            AND A.COD_REGI = psCodigoRegionBase07;

          /* Borrar  informaci?n en la tabla COM_COMIS_PERIO_CALCU_REGIO */
          DELETE FROM COM_COMIS_PERIO_CALCU_REGIO
          WHERE COD_PERI = psCodPeriodo
            AND COD_COMI = psCodComision
            AND COD_REGI = psCodigoRegionBase07;
      END IF;

      IF psTipoComision = '02' THEN --antes G
          /* Borrando tabla temporal de Comision por Recuperacion */
          DELETE FROM COM_REPOR_COMIS_RECUP A
          WHERE A.COD_PERI = psCodPeriodo
            AND A.COD_COMI = psCodComision
            AND A.COD_REGI = psCodigoRegionBase07
            AND A.COD_ZONA = psCodigoZonaBase07;

          /* Borrando informaci?n en la tabla COM_COMIS_PERI_CALCU_ZONA */
          DELETE FROM COM_COMIS_PERIO_CALCU_ZONA
          WHERE PERI_COD_PERI = psCodPeriodo
            AND COD_COMI = psCodComision
            AND COD_REGI = psCodigoRegionBase07
            AND COD_ZONA = psCodigoZonaBase07;
      END IF;

      /* Borrando informaci?n en la tabla COM_COMIS_DSCTO */
      DELETE FROM COM_COMIS_DSCTO
      WHERE COD_PERI = psCodPeriodo
        AND COD_COMI = psCodComision
        AND COD_CLIE_COMI = psCodigoGerenteBase07;

      /* Borrando informaci?n en las tablas COM_RECUP_CAMPA_ZONA y COM_RECUP_CAMPA_SECCI */
      IF psTipoComision = '01' THEN --antes Gr
          DELETE FROM COM_RECUP_CAMPA_ZONA A
          WHERE A.COD_PERI = psCodPeriodo
            AND A.COD_COMI = psCodComision
            AND A.COD_REGI = psCodigoRegionBase07;

          DELETE FROM COM_RECUP_CAMPA_SECCI A
          WHERE A.COD_PERI = psCodPeriodo
            AND A.COD_COMI = psCodComision
            AND A.COD_REGI = psCodigoRegionBase07;
      END IF;
      IF psTipoComision = '02' THEN --antes Gr
          DELETE FROM COM_RECUP_CAMPA_ZONA A
          WHERE A.COD_PERI = psCodPeriodo
            AND A.COD_COMI = psCodComision
            AND A.COD_REGI = psCodigoRegionBase07
            AND A.COD_ZONA = psCodigoZonaBase07;

          DELETE FROM COM_RECUP_CAMPA_SECCI A
          WHERE A.COD_PERI = psCodPeriodo
            AND A.COD_COMI = psCodComision
            AND A.COD_REGI = psCodigoRegionBase07
            AND A.COD_ZONA = psCodigoZonaBase07;
      END IF;

      DELETE FROM COM_RECUP_CAMPA_REGIO A
      WHERE A.COD_PERI = psCodPeriodo
        AND A.COD_COMI = psCodComision
        AND A.COD_REGI = psCodigoRegionBase07;

  END IF;

  /* Obteniendo oids */
  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lnIdPeriActual := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);
  lnIdComision   := COM_FN_DEVUE_ULTI_OID_COMI(psCodComision);

  /* Obteniendo campa?a de descuento */
  lsCodPeriSaldo	   := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -3);
  lnIdPeriSaldo      := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriSaldo, lnIdMarca, lnIdCanal);
  lsCodPeriDescuento := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -7);
  lsCodPeriAnterior  := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
  lnIdPeriAnterior   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriAnterior, lnIdMarca, lnIdCanal);
  lsCodPeriSiguiente := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, 1);
  lnIdPeriSiguiente  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriSiguiente, lnIdMarca, lnIdCanal);
  lsCodPeriMas2 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, 2);
  lnIdPeriMas2  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriMas2, lnIdMarca, lnIdCanal);
  Gln_IdComision := lnIdComision;


 /* Obteniendo valor de telecobro */
    SELECT VAL_PARA
    INTO   ls_evalua_telecobro
    FROM   BAS_PARAM_PAIS
    WHERE  COD_PAIS = psCodPais
    AND    COD_SIST = 'COM'
    AND    COD_PARA = '001';
    
  /* NUEVO POR ESCALONADA */
  /* PUNTO:1 SE UBICA UN FLAG PARA DIFERENCIA LAS ESCALONADA, SE USA UNA VARIABLE GLOBAL YA QUE SE NECESITA
     UTILIZAR ESTE FLAG EN OTROS PROCEDIMIENTOS */

  BEGIN
     SELECT 'S' INTO SI_ES_ESCALONADA
        FROM COM_COMIS_COBRA A, COM_COMIS_ESCAL B
      WHERE A.COMI_OID_COMI = lnIdComision
       AND  A.OID_COMI_COBR = B.OID_COMI_COBR
       AND ROWNUM=1;
  EXCEPTION
     WHEN NO_DATA_FOUND THEN
         SI_ES_ESCALONADA:='N';
  END;

/* obteniendo cantidad de dias, % de recuperacion y % de comision TRAMO 1*/
  lnNumDiasComi:=0;
  lnValPorceRecu:=0;
  lnValPorceActi:=0;
  lnValPorceComi:=0;
  lnValPorceActiCOMI := 0;
  lnNumDiasComi2  := 0; lnValPorceRecu2 := 0;  lnValPorceComi2 := 0;
  lbEsSoloTramo1 := false;
  Gln_FlagComisEscal := 1;

  IF SI_ES_ESCALONADA = 'N' THEN
      BEGIN
        SELECT B.NUM_DIAS_COMI, B.VAL_PORC_RECU, NVL(B.VAL_PORC_ACTI,0.00), B.VAL_PORC_COMI, B.VAL_PORC_COMI_ACTI
          INTO lnNumDiasComi  , lnValPorceRecu , lnValPorceActi , lnValPorceComi, lnValPorceActiCOMI
        FROM COM_COMIS_COBRA A, COM_COMIS_COBRA_TIPO_PARTI B
        WHERE  A.COMI_OID_COMI = lnIdComision
          AND  A.OID_COMI_COBR = B.COCO_OID_COMI_COBR
          AND  B.VAL_NIVE_TRAM = 1;
        lbEsSoloTramo1 := true;
        Gln_FlagComisEscal := 0;
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
           RAISE_APPLICATION_ERROR(-20123,
           'No se encontro % de Recuperacion ni % de Comision correspondiente al Tramo 1, para la Comision: '||
            psCodComision);
      END;

      /* obteniendo cantidad de dias, % de recuperacion y % de comision TRAMO 2 */
      BEGIN
        SELECT B.NUM_DIAS_COMI, B.VAL_PORC_RECU, NVL(B.VAL_PORC_ACTI,0.00), B.VAL_PORC_COMI
          INTO lnNumDiasComi2, lnValPorceRecu2, lnValPorceActi2, lnValPorceComi2
         FROM COM_COMIS_COBRA A, COM_COMIS_COBRA_TIPO_PARTI B
        WHERE  A.COMI_OID_COMI = lnIdComision
           AND A.OID_COMI_COBR = B.COCO_OID_COMI_COBR
           AND B.VAL_NIVE_TRAM = 2;
        lbEsSoloTramo1 := false;
        Gln_FlagComisEscal := 0;
      EXCEPTION
      WHEN no_data_found THEN
           lnNumDiasComi2  := 0; lnValPorceRecu2 := 0;  lnValPorceComi2 := 0;
           lbEsSoloTramo1 := true;
      END;
  END IF;

  /* obteniendo id de estado de pedidos */
  SELECT ES.OID_ESTA_SOLI
  INTO lnIdEstadoPedido
  FROM PED_ESTAD_SOLIC ES
  WHERE
       ES.COD_ESTA_SOLI = 'AN';

  /* obteniendo id de estado de Cliente */
  SELECT A.OID_ESTA_CLIE
  INTO lnIdEstado
  FROM MAE_ESTAT_CLIEN A
  WHERE
       A.COD_ESTA_CLIE = '02';

  /* Recorriendo Lista de Clientes */
  lnIdRegion:=-1;
  lnIdZona := -1;
  lnIdSeccion := -1;
  lsNomLiderZona    := '';
  lsNomLiderSeccion := '';
  lsNomLiderRegion := '';

  IF lsBaseComision <> '07' THEN
  /* Verificando que la Comision posea Regiones */
  SELECT COUNT(1)
  INTO lnExisteRegion
  FROM
     COM_COMIS A,
     COM_COMIS_CLIEN B
  WHERE  A.OID_COMI = lnIdComision
     AND A.OID_COMI = B.COMI_OID_COMI
     AND B.ZORG_OID_REGI IS NOT NULL;

  /* CAMBIOS:
     1. AL ENCONTRAR NULO SE LE ANTEPONE UN 0 PARA EVITAR NULO
     2. PARA EVITAR HACER UN CONTEO TOTAL Y GENERAR DEMORA,
        SOLO AHORA COMPROBAMOS SI TIENE 1 SOLO REGISTRO Y SERA MAS RAPIDO
  */
  IF lnExisteRegion > 0 THEN
    FOR curComis IN cursorComision(lnIdComision)  LOOP
      IF curComis.Zzon_Oid_Zona IS NULL THEN

      INSERT INTO COM_TEMPO_COMIS_RECUP(
            OID_CLIE, COD_CLIE, NOM_CLIE,
            OID_REGI, COD_REGI, OID_ZONA, COD_ZONA,
            COD_SUBG_VENT, OID_SECC, COD_SECC,
            COD_TERR, OID_TERR_ADMI)
        SELECT DISTINCT
          C.OID_CLIE,
          C.COD_CLIE,
          TRIM(NVL(C.VAL_APE1,' ')) || ' ' ||
          TRIM(NVL(C.VAL_APE2,' ')) || ' ' ||
          TRIM(NVL(C.VAL_NOM1,' ')) AS NOM_CLIE,
          I.OID_REGI,
          I.COD_REGI,
          H.OID_ZONA,
          H.COD_ZONA,
          L.COD_SUBG_VENT,
          G.OID_SECC,
          G.COD_SECC,
          F.COD_TERR,
          e.oid_terr_admi AS OID_TERR_ADMI
        FROM
           MAE_CLIEN C,
           MAE_CLIEN_UNIDA_ADMIN D,
           ZON_TERRI_ADMIN E,
           ZON_TERRI F,
           ZON_SECCI G,
           ZON_ZONA  H,
           ZON_REGIO I,
           ZON_SUB_GEREN_VENTA L,
           COM_COMIS A,
           COM_COMIS_CLIEN B

        WHERE  A.OID_COMI = lnIdComision
             AND I.OID_REGI = curComis.Zorg_Oid_Regi
           AND A.OID_COMI = B.COMI_OID_COMI
           AND I.OID_REGI = B.ZORG_OID_REGI
           AND C.OID_CLIE = D.CLIE_OID_CLIE
           AND E.OID_TERR_ADMI = D.ZTAD_OID_TERR_ADMI
           AND F.OID_TERR = E.TERR_OID_TERR
           AND G.OID_SECC = E.ZSCC_OID_SECC
           AND H.OID_ZONA = G.ZZON_OID_ZONA
           AND I.OID_REGI = H.ZORG_OID_REGI
             AND L.OID_SUBG_VENT = I.ZSGV_OID_SUBG_VENT  ;
  ELSE
         INSERT INTO COM_TEMPO_COMIS_RECUP(
                OID_CLIE, COD_CLIE, NOM_CLIE,
                OID_REGI, COD_REGI, OID_ZONA, COD_ZONA,
                COD_SUBG_VENT, OID_SECC, COD_SECC,
                COD_TERR, OID_TERR_ADMI)
          SELECT DISTINCT
            C.OID_CLIE,
            C.COD_CLIE,
            TRIM(NVL(C.VAL_APE1,' ')) || ' ' ||
            TRIM(NVL(C.VAL_APE2,' ')) || ' ' ||
            TRIM(NVL(C.VAL_NOM1,' ')) AS NOM_CLIE,
            I.OID_REGI,
            I.COD_REGI,
            H.OID_ZONA,
            H.COD_ZONA,
            L.COD_SUBG_VENT,
            G.OID_SECC,
            G.COD_SECC,
            F.COD_TERR,
            e.oid_terr_admi AS OID_TERR_ADMI
          FROM
             MAE_CLIEN C,
             MAE_CLIEN_UNIDA_ADMIN D,
             ZON_TERRI_ADMIN E,
             ZON_TERRI F,
             ZON_SECCI G,
             ZON_ZONA  H,
             ZON_REGIO I,
             ZON_SUB_GEREN_VENTA L,
             COM_COMIS A,
             COM_COMIS_CLIEN B

          WHERE  A.OID_COMI = lnIdComision
             AND I.OID_REGI = curComis.Zorg_Oid_Regi
             AND H.OID_ZONA = curComis.Zzon_Oid_Zona
             AND A.OID_COMI = B.COMI_OID_COMI
             AND I.OID_REGI = B.ZORG_OID_REGI
             AND C.OID_CLIE = D.CLIE_OID_CLIE
             AND E.OID_TERR_ADMI = D.ZTAD_OID_TERR_ADMI
             AND F.OID_TERR = E.TERR_OID_TERR
             AND G.OID_SECC = E.ZSCC_OID_SECC
             AND H.OID_ZONA = G.ZZON_OID_ZONA
             AND I.OID_REGI = H.ZORG_OID_REGI
             AND L.OID_SUBG_VENT = I.ZSGV_OID_SUBG_VENT    ;
      END IF;
    END LOOP;
      ELSE
    FOR curRegi IN cursorRegion(lnIdPais, lnIdMarca, lnIdCanal)  LOOP
         INSERT INTO COM_TEMPO_COMIS_RECUP(
                OID_CLIE, COD_CLIE, NOM_CLIE,
                OID_REGI, COD_REGI, OID_ZONA, COD_ZONA,
                COD_SUBG_VENT, OID_SECC, COD_SECC,
                COD_TERR, OID_TERR_ADMI)
          SELECT DISTINCT
            C.OID_CLIE,
            C.COD_CLIE,
            TRIM(NVL(C.VAL_APE1,' ')) || ' ' ||
            TRIM(NVL(C.VAL_APE2,' ')) || ' ' ||
            TRIM(NVL(C.VAL_NOM1,' ')) AS NOM_CLIE,
            I.OID_REGI,
            I.COD_REGI,
            H.OID_ZONA,
            H.COD_ZONA,
            L.COD_SUBG_VENT,
            G.OID_SECC,
            G.COD_SECC,
            F.COD_TERR,
            E.OID_TERR_ADMI AS OID_TERR_ADMI
          FROM
             MAE_CLIEN C,
             MAE_CLIEN_UNIDA_ADMIN D,
             ZON_TERRI_ADMIN E,
             ZON_TERRI F,
             ZON_SECCI G,
             ZON_ZONA  H,
             ZON_REGIO I,
             ZON_SUB_GEREN_VENTA L

          WHERE  I.OID_REGI = curRegi.ZORG_OID_REGI
             AND H.OID_ZONA = curRegi.Oid_Zona
             AND C.OID_CLIE = D.CLIE_OID_CLIE

             AND E.OID_TERR_ADMI = D.ZTAD_OID_TERR_ADMI
             AND F.OID_TERR = E.TERR_OID_TERR
             AND G.OID_SECC = E.ZSCC_OID_SECC
             AND H.OID_ZONA = G.ZZON_OID_ZONA
             AND I.OID_REGI = H.ZORG_OID_REGI
             AND L.OID_SUBG_VENT = I.ZSGV_OID_SUBG_VENT    ;

      END LOOP;
  END IF;
  ELSE
    IF psTipoComision = '01' THEN
       INSERT INTO COM_TEMPO_COMIS_RECUP(
                    OID_CLIE, COD_CLIE, NOM_CLIE,
                    OID_REGI, COD_REGI, OID_ZONA, COD_ZONA,
                    COD_SUBG_VENT, OID_SECC, COD_SECC,
                    COD_TERR, OID_TERR_ADMI)
              SELECT DISTINCT
                C.OID_CLIE,
                C.COD_CLIE,
                TRIM(NVL(C.VAL_APE1,' ')) || ' ' ||
                TRIM(NVL(C.VAL_APE2,' ')) || ' ' ||
                TRIM(NVL(C.VAL_NOM1,' ')) AS NOM_CLIE,
                I.OID_REGI,
                I.COD_REGI,
                H.OID_ZONA,
                H.COD_ZONA,
                L.COD_SUBG_VENT,
                G.OID_SECC,
                G.COD_SECC,
                F.COD_TERR,
                E.OID_TERR_ADMI AS OID_TERR_ADMI
              FROM
                 MAE_CLIEN C,
                 MAE_CLIEN_UNIDA_ADMIN D,
                 ZON_TERRI_ADMIN E,
                 ZON_TERRI F,
                 ZON_SECCI G,
                 ZON_ZONA  H,
                 ZON_REGIO I,
                 ZON_SUB_GEREN_VENTA L

              WHERE  I.COD_REGI = psCodigoRegionBase07
                 AND C.OID_CLIE = D.CLIE_OID_CLIE

                 AND E.OID_TERR_ADMI = D.ZTAD_OID_TERR_ADMI
                 AND F.OID_TERR = E.TERR_OID_TERR
                 AND G.OID_SECC = E.ZSCC_OID_SECC
                 AND H.OID_ZONA = G.ZZON_OID_ZONA
                 AND I.OID_REGI = H.ZORG_OID_REGI
                 AND L.OID_SUBG_VENT = I.ZSGV_OID_SUBG_VENT    ;
    ELSE
       INSERT INTO COM_TEMPO_COMIS_RECUP(
                    OID_CLIE, COD_CLIE, NOM_CLIE,
                    OID_REGI, COD_REGI, OID_ZONA, COD_ZONA,
                    COD_SUBG_VENT, OID_SECC, COD_SECC,
                    COD_TERR, OID_TERR_ADMI)
              SELECT DISTINCT
                C.OID_CLIE,
                C.COD_CLIE,
                TRIM(NVL(C.VAL_APE1,' ')) || ' ' ||
                TRIM(NVL(C.VAL_APE2,' ')) || ' ' ||
                TRIM(NVL(C.VAL_NOM1,' ')) AS NOM_CLIE,
                I.OID_REGI,
                I.COD_REGI,
                H.OID_ZONA,
                H.COD_ZONA,
                L.COD_SUBG_VENT,
                G.OID_SECC,
                G.COD_SECC,
                F.COD_TERR,
                E.OID_TERR_ADMI AS OID_TERR_ADMI
              FROM
                 MAE_CLIEN C,
                 MAE_CLIEN_UNIDA_ADMIN D,
                 ZON_TERRI_ADMIN E,
                 ZON_TERRI F,
                 ZON_SECCI G,
                 ZON_ZONA  H,
                 ZON_REGIO I,
                 ZON_SUB_GEREN_VENTA L

              WHERE  I.COD_REGI = psCodigoRegionBase07
                 AND H.COD_ZONA = psCodigoZonaBase07
                 AND C.OID_CLIE = D.CLIE_OID_CLIE

                 AND E.OID_TERR_ADMI = D.ZTAD_OID_TERR_ADMI
                 AND F.OID_TERR = E.TERR_OID_TERR
                 AND G.OID_SECC = E.ZSCC_OID_SECC
                 AND H.OID_ZONA = G.ZZON_OID_ZONA
                 AND I.OID_REGI = H.ZORG_OID_REGI
                 AND L.OID_SUBG_VENT = I.ZSGV_OID_SUBG_VENT    ;

    END IF;

  END IF;

  COMMIT;
  ------------------------------------------------------------

  --  Invocar Caso de Uso Calcular Objetivo Venta
  DELETE FROM COM_GTT_TABLA_REPOS;
  COM_PR_CALCU_OBJET_VENTA(psCodPais, psCodPeriodo, lsBaseComision, psTipoComision);

  /* Para el tema de los acumulados por zona */
  DELETE FROM COM_GTT_TABLA_REPOS;

  INSERT INTO COM_GTT_TABLA_REPOS(CAM_CADE, CAM_NUME)
  SELECT DISTINCT COD_ZONA, 0.00
  FROM COM_TEMPO_COMIS_RECUP;

  /* obtenemos id subproceso banco*/
  DELETE FROM PER_GTT_TABLA_REPOS;

  INSERT INTO PER_GTT_TABLA_REPOS(CAM_NUME, CAM_CADE, CAM_CAD2)
  SELECT C.OID_SUBP, D.TIP_COMI, D.IND_COMI
  FROM  SEG_PAIS  A,
        CCC_PROCE B,
        CCC_SUBPR C,
        COM_COMIS_SUBPR_PAGO D
  WHERE A.OID_PAIS = lnIdPais
    AND B.PAIS_OID_PAIS = A.OID_PAIS
    AND B.OID_PROC = C.CCPR_OID_PROC
    AND D.COD_PAIS = psCodPais
    AND D.COD_COMI = COMISION_RECUPERACION
    AND B.COD_PROC = D.COD_PROC
    AND C.COD_SUBP = D.COD_SUBP;

  /* Inicio LOOP */
  lnPag := 0;
  lbDemandaAnticipada := TRUE;
  lv_procesar := TRUE;
  OPEN cursorRegistro;
  LOOP
      FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
      lnPag := lnPag + 1;
      IF tablaRegistro.COUNT > 0 THEN
        FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             regRegistro := tablaRegistro(x);
             lnX := x;
             IF regRegistro.Cod_Clie= '000076813' THEN
                regRegistro.Cod_Clie := '000076813';
             END IF;
             lbInsertar := TRUE;
             lnIdUnidadAdm := COM_FN_DEVUE_OID_UNADM_HISTO(regRegistro.OID_CLIE, regRegistro.oid_terr_admin, psCodPeriodo);
             IF lnIdUnidadAdm != -1 THEN

                 lnSumaComisionTramo1 := 0;
                 lnSumaComisionTramo2 := 0;
                 regRecuperacion.Cod_Peri := psCodPeriodo;
                 regRecuperacion.Cod_Comi := psCodComision;
                 regRecuperacion.Oid_Clie := regRegistro.OID_CLIE;
                 regRecuperacion.Cod_Clie := regRegistro.COD_CLIE;
                 regRecuperacion.Nom_Clie := regRegistro.NOM_CLIE;
                 regRecuperacion.Cod_Regi := regRegistro.COD_REGI;
                 regRecuperacion.Oid_Regi := regRegistro.OID_REGI;
                 regRecuperacion.Oid_Zona := regRegistro.OID_ZONA;
                 regRecuperacion.Cod_Zona := regRegistro.COD_ZONA;
                 regRecuperacion.Cod_Subg_Vent := regRegistro.COD_SUBG_VENT;
                 regRecuperacion.Oid_Secc := regRegistro.OID_SECC;
                 regRecuperacion.Cod_Secc := regRegistro.COD_SECC;
                 regRecuperacion.Cod_Terr := regRegistro.COD_TERR;

                 regRecuperacion.NUM_DIAS_COMI := lnNumDiasComi;
                 regRecuperacion.VAL_PORC_RECU := lnValPorceRecu;
                 regRecuperacion.VAL_PORC_COMI := lnValPorceComi;
                 regRecuperacion.VAL_PORC_ACTI := lnValPorceActi;

                 regRecuperacion.Num_Dias_Comi_Tra2 := lnNumDiasComi2;
                 regRecuperacion.Val_Porc_Recu_Tra2 := lnValPorceRecu2;
                 regRecuperacion.Val_Porc_Comi_Tra2 := lnValPorceComi2;

                 /* Acumulando los saldos detalle por zona */
                 IF psTipoComision = '02' THEN --Antes G
                     COM_PR_COMIS_RECUP_ACUMU_ZONA(
                              lnIdPais,
                              psCodPais,
                              lnIdPeriSaldo,
                              lnIdComision,
                              regRegistro.OID_REGI,
                              regRecuperacion.cod_Zona,
                              regRecuperacion);
                 END IF;

                 /* Encontrar Lider de la Region */
                 IF regRecuperacion.Oid_Regi <> lnIdRegion THEN
                    lnIdRegion  := regRecuperacion.OID_REGI;
                    lsCodRegion := regRecuperacion.Cod_Secc;
                    IF lsBaseComision <> '07' THEN
                    lsNomLiderRegion := COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                         lsCodLiderRegion,
                                         psCodPeriodo,
                                         lnIdPais, regRecuperacion.COD_SUBG_VENT,
                                         regRecuperacion.COD_REGI,
                                         NULL,
                                         NULL);
                     ELSE
                       lsCodLiderRegion := psCodigoGerenteBase07;
                       SELECT TRIM(NVL(A.VAL_APE1,' ')) || ' ' ||
                              TRIM(NVL(A.VAL_APE2,' ')) || ' ' ||
                              TRIM(NVL(A.VAL_NOM1,' '))
                       INTO lsNomLiderRegion
                       FROM
                          MAE_CLIEN A
                       WHERE  A.COD_CLIE = lsCodLiderRegion
                          AND A.PAIS_OID_PAIS = lnIdPais;
                     END IF;

                     regRecuperacion.Cod_Lide_Regi := lsCodLiderRegion;
                     regRecuperacion.Nom_Lide_Regi := lsNomLiderRegion;
                 END IF;


                 /* ENCONTRANDO LIDER DE SECCION */
                 IF regRecuperacion.OID_SECC <> lnIdSeccion THEN
                    lnIdSeccion  := regRecuperacion.OID_SECC;
                    lsCodSeccion := regRecuperacion.Cod_Secc;
                    lsNomLiderSeccion := COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                         lsCodLiderSeccion,
                                         psCodPeriodo,
                                         lnIdPais, regRecuperacion.COD_SUBG_VENT,
                                         regRecuperacion.COD_REGI,
                                         regRecuperacion.COD_ZONA,
                                         regRecuperacion.COD_SECC);
                    lv_procesar:= COM_FN_DEVUE_PROCE_TUTOR(psCodPais, psCodMarca,psCodCanal,lsCodLiderSeccion,psAnioTramo,psNumTramo,psTipoComision,psCodTipoCalculo);
                    regRecuperacion.NOM_LIDE_SECC := lsNomLiderSeccion;
                 END IF;
                 regRecuperacion.Cod_Lide_Secc := lsCodLiderSeccion;
                 regRecuperacion.NOM_LIDE_SECC := lsNomLiderSeccion;

                 /* ENCONTRANDO LIDER DE ZONA */
                 IF(lv_procesar)THEN
                   IF regRecuperacion.OID_ZONA <> lnIdZona THEN

                      /* Verificando Demanda Anticipada */
                      lbDemandaAnticipada := TRUE;
                      BEGIN
                         IF psTipoComision = '02' THEN --En caso sea Gerente de Zona -- Antes G
                             SELECT NVL(A.NUM_DIAS_TRA1_GZON,0), NVL(A.NUM_DIAS_TRA2_GZON,0)
                             INTO
                                lnNumDiasDemandaTramo1, lnNumDiasDemandaTramo2
                             FROM COM_ZONA_DEMAN_ANTIC A
                             WHERE A.COD_PAIS = psCodPais
                               AND A.COD_ZONA = regRecuperacion.COD_ZONA;
                         ELSIF (psTipoComision = '03')THEN --Antes L
                             SELECT NVL(A.NUM_DIAS_TRA1,0), NVL(A.NUM_DIAS_TRA2,0)
                             INTO
                                lnNumDiasDemandaTramo1, lnNumDiasDemandaTramo2
                             FROM COM_ZONA_DEMAN_ANTIC A
                             WHERE A.COD_PAIS = psCodPais
                               AND A.COD_ZONA = regRecuperacion.COD_ZONA;
                         ELSIF (psTipoComision = '01')THEN --Antes Gr
                             SELECT NVL(A.NUM_DIAS_TRA1_GREG, 0), NVL(A.NUM_DIAS_TRA1_GREG, 0)
                               INTO lnNumDiasDemandaTramo1, lnNumDiasDemandaTramo2
                               FROM COM_ZONA_DEMAN_ANTIC A
                              WHERE A.COD_PAIS = psCodPais
                                AND A.COD_ZONA = regRecuperacion.COD_ZONA;
                         END IF;
                      EXCEPTION
                      WHEN no_data_found THEN
                          lbDemandaAnticipada := FALSE;
                      END;

                      lbInsertarZona := TRUE;
                      lnIdZona  := regRecuperacion.OID_ZONA;
                      lsCodZona := regRecuperacion.COD_ZONA;
                      IF lsBaseComision <> '07' THEN
                      lsNomLiderZona := COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                        lsCodLiderZona,
                                        psCodPeriodo,
                                        lnIdPais, regRecuperacion.COD_SUBG_VENT,
                                        regRecuperacion.COD_REGI,
                                        regRecuperacion.COD_ZONA,
                                        NULL);
                      ELSE
                         lsCodLiderZona := psCodigoGerenteBase07;
                         SELECT TRIM(NVL(A.VAL_APE1,' ')) || ' ' ||
                                TRIM(NVL(A.VAL_APE2,' ')) || ' ' ||
                                TRIM(NVL(A.VAL_NOM1,' '))
                         INTO lsNomLiderZona
                         FROM
                            MAE_CLIEN A
                         WHERE  A.COD_CLIE = lsCodLiderZona
                            AND A.PAIS_OID_PAIS = lnIdPais;
                      END IF;

                      /* Efectuando validaciones adicionales al Gerente de Zona cuando el
                         tipo de Comision = '02'  -- Antes G */
                      IF psTipoComision = '02' AND lsCodLiderZona IS NOT NULL THEN
                         COM_PR_VALID_ADICI_GZONA_RECUP (
                             lnIdComision,
                             regRegistro.OID_REGI,
                             regRegistro.OID_ZONA,
                             lsCodLiderZona,
                             lbInsertar ,
                             lbInsertarZona
                         );
                      END IF;
                   END IF;


                   IF psTipoComision = '02' AND lsCodLiderZona IS NULL THEN --Antes G
                      lbInsertarZona := TRUE;
                   END IF;


                   IF psTipoComision = '01' AND lsCodLiderRegion IS NULL THEN --Antes Gr
                      lbInsertar := TRUE;
                      lbInsertarZona := FALSE;
                   END IF;

                   regRecuperacion.Cod_Lide_Zona := lsCodLiderZona;
                   regRecuperacion.NOM_LIDE_ZONA := lsNomLiderZona;

                   /* Agregando la zona a la tabla de zonas nuevas en caso se inicialize en el periodo actual */
                   INSERT INTO COM_ZONAS_NUEVA
                   (COD_PAIS, COD_ZONA, COD_CAMP_INIC, COD_CAMP_FINA)
                   SELECT DISTINCT psCodPais, regRecuperacion.Cod_Zona, psCodPeriodo,NULL
                   FROM COM_ZONAS_NUEVA CZN,
                   ZON_ZONA ZZ
                   WHERE ZZ.PERD_OID_PERI_INIC = lnIdPeriActual
                   AND CZN.COD_ZONA != regRecuperacion.Cod_Zona;

                   /* Verificando si es zona nueva */
                   BEGIN
                     SELECT CPZN.IMP_VENT_NETA, CPZN.POR_RECU, CPZN.POR_ACTI, CPZN.IMP_BONO
                     INTO lnImporteVenta, lnPorRecupera, lnPorActivida, lnMontoBono
                     FROM ZON_ZONA ZZ,
                          COM_ZONAS_NUEVA CZN,
                          COM_PARAM_ZONAS_NUEVA CPZN
                     WHERE CPZN.COD_PAIS = psCodPais
                       AND ZZ.COD_ZONA = regRecuperacion.Cod_Zona
                       AND CZN.COD_PAIS = CPZN.COD_PAIS
                       AND (ZZ.PERD_OID_PERI_INIC = lnIdPeriActual
                        OR (CZN.COD_ZONA = ZZ.COD_ZONA AND (CZN.COD_CAMP_FINA = regRecuperacion.Cod_Peri OR CZN.COD_CAMP_FINA IS NULL)));
                   EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                          lnImporteVenta := 0;
                          lnPorRecupera := 0;
                          lnPorActivida := 0;
                   END;

                   /* INSERTANDO DATA */
                   IF lbInsertar AND lbInsertarZona THEN
                       /* Generando importes */
                       COM_PR_MONTO_COMIS_RECUP(
                          psCodPais,
                          ls_evalua_telecobro,
                          lsIndicadorCrono,
                          psTipoComision,
                          lbEsSoloTramo1,
                          lbDemandaAnticipada,
                          lnNumDiasDemandaTramo1,
                          lnNumDiasDemandaTramo2,
                          lnIdPeriActual,
                          lnIdPeriAnterior,
                          lsCodPeriAnterior,
                          lnIdPeriSiguiente,
                          lnIdPeriMas2,
                          lnIdComision,
                          regRegistro.OID_REGI,
                          regRecuperacion.Oid_Zona,
                          lnIdEstadoPedido,
                          lnIdPais,
                          lnIndDctoImportePago,
                          lnFlagWeekend,
                          lnImporteVenta,
                          lnPorRecupera,
                          lnPorActivida,
                          lnMontoBono,
                          lsBaseComision,
                          lnPorcenVentaConsu,
                          ldFechaRetiroBase07,
                          regRecuperacion,
                          lnSumaComisionTramo1,
                          lnSumaComisionTramo2
                          );

                       /* obteniendo primera campa?a de ingreso de la consultora */
                       IF regRecuperacion.Cod_Lide_Secc IS NOT NULL AND psTipoComision = '03' THEN --Solo para Lideres --Antes L
                          BEGIN
                             SELECT MIN(A.PERD_OID_PERI)
                             INTO lnIdPeriIniConsultora
                             FROM
                                MAE_CLIEN_HISTO_ESTAT A
                             WHERE A.CLIE_OID_CLIE = regRecuperacion.Oid_Clie
                               AND A.ESTA_OID_ESTA_CLIE = lnIdEstado;
                           EXCEPTION
                           WHEN no_Data_found THEN
                                lnIdPeriIniConsultora := -1;
                          END;

                          IF lnIdPeriIniConsultora <> -1 THEN
                             SELECT B.COD_PERI
                             INTO
                                lsCodPeriIniConsultora
                             FROM
                                CRA_PERIO A,
                                SEG_PERIO_CORPO B
                             WHERE A.OID_PERI = lnIdPeriIniConsultora
                               AND B.OID_PERI = A.PERI_OID_PERI;

                             IF TO_NUMBER(lsCodPeriIniConsultora) >= TO_NUMBER(lsCodPeriDescuento) THEN
                                BEGIN
                                   SELECT A.COD_CONS
                                   INTO lsCodComisionista
                                   FROM
                                      COM_COMIS_DSCTO A
                                   WHERE A.COD_PERI = psCodPeriodo
                                     AND A.COD_COMI = psCodComision
                                     AND A.COD_CONS = regRecuperacion.Cod_Clie
                                     AND A.COD_CLIE_COMI = regRecuperacion.Cod_Lide_Secc;

                                   UPDATE COM_COMIS_DSCTO A
                                        SET A.IMP_COM1 = lnSumaComisionTramo1,
                                            A.IMP_COM2 = lnSumaComisionTramo2,
                                            A.NUM_TRAM = DECODE(lnSumaComisionTramo2, 0, 1,2),
                                            A.FEC_MODI = SYSDATE,
                                            A.USU_MODI = psUsuario
                                     WHERE A.COD_PERI = psCodPeriodo
                                       AND A.COD_COMI = psCodComision
                                       AND A.COD_CONS = regRecuperacion.Cod_Clie
                                       AND A.COD_CLIE_COMI = regRecuperacion.Cod_Lide_Secc;
                                EXCEPTION
                                WHEN no_data_found THEN
                                     INSERT INTO COM_COMIS_DSCTO
                                       (COD_PERI, COD_CONS, COD_COMI, COD_CLIE_COMI, NUM_TRAM,
                                        IMP_COM1, IMP_COM2, IND_COMI, FEC_DIGI, USU_DIGI)
                                     VALUES
                                       (psCodPeriodo, regRecuperacion.Cod_Clie, psCodComision,
                                        regRecuperacion.Cod_Lide_Secc, DECODE(lnSumaComisionTramo2, 0, 1,2),
                                        lnSumaComisionTramo1, lnSumaComisionTramo2, 'N', SYSDATE, psUsuario);
                                END;
                             END IF;
                          END IF;
                       END IF;
                   END IF;
                 END IF;
               END IF;
         END LOOP;
      END IF;
      EXIT WHEN cursorRegistro%NOTFOUND;
  END LOOP;
  CLOSE cursorRegistro;

  COMMIT;
  --------------------------------------------------------------
  /* ACTUALIZANDO MONTOS TOTALES PARA CASO DE LIDERES, GERENTES DE REGION Y GERENTES DE ZONA */

  /* EN CASO DE LIDERES */
  IF psTipoComision = '03' THEN --Antes L
     COM_PR_OBTIE_MONTO_MLIDE_RECUP (
         psCodComision ,
         psCodPeriodo,
         psTipoComision,
         lsBaseComision,
         lnValorIGVVen,
         lnIndDctoImportePago,
         lnIndDctoReclamo,
         lnValPorceComi,
         lnValPorceComi2,
         lnValPorceRecu,
         lnValPorceRecu2,
         psCodigoRegionBase07,
         psCodigoZonaBase07
     );
  ELSIF psTipoComision = '02' THEN --Antes G
      COM_PR_OBTIE_MONTO_GZONA_RECUP (
         lnIdPais,
         psCodPais,
         psCodMarca,
         psCodCanal,
         psCodComision,
         psCodPeriodo,
         psTipoComision,
         lsBaseComision,
         lnValorIGVVen,
         lnIndDctoImportePago,
         lnIndDctoReclamo,
         lnValPorceComi,
         lnValPorceComi2,
         lnValPorceRecu,
         lnValPorceRecu2,
         lnIdPeriActual,
         lnValPorceActi,
         lnValPorceActiCOMI,
         lnValPorceComi,
         lnPorcenVentaConsu,
         psUsuario,
         psCodigoRegionBase07,
         psCodigoZonaBase07
      );
  END IF;

  /* Logica Zonas Nuevas */
  --COM_PR_ZONA_NUEVA(psCodPais, lnIdPais,psCodPeriodo, lnIdPeriActual,psCodComision);
  /* Fin Logica Zonas Nuevas */

 IF psTipoComision = '01' THEN --Antes Gr
    COM_PR_OBTIE_MONTO_GREGI_RECUP (
       lnIdPais,
       psCodPais,
       psCodMarca,
       psCodCanal,
       psCodComision,
       psCodPeriodo,
       lnIdPeriActual,
       psTipoComision,
       lsBaseComision,
       lnValorIGVVen,
       lnIndDctoImportePago,
       lnIndDctoReclamo,
       lnValPorceComi,
       lnValPorceComi2,
       lnValPorceRecu,
       lnValPorceRecu2,
       psUsuario,
       psCodigoRegionBase07
    );
  END IF;

  /* ACTUALIZANDO TABLA COM_COMIS_DSTO */
  IF psTipoComision = '03' THEN --Antes L
      /* Actualizando indicador de Comision a S */
      OPEN cPoseeComi;
      LOOP
          FETCH cPoseeComi BULK COLLECT INTO tablaRegistroLideres LIMIT W_FILAS;
          IF tablaRegistroLideres.COUNT > 0 THEN
            FORALL x IN tablaRegistroLideres.FIRST .. tablaRegistroLideres.LAST
                 UPDATE COM_COMIS_DSCTO A
                 SET A.IND_COMI = 'S'
                 WHERE A.COD_PERI = psCodPeriodo
                   AND A.COD_COMI = psCodComision
                   AND A.COD_CLIE_COMI = tablaRegistroLideres(x);
          END IF;
          EXIT WHEN cPoseeComi%NOTFOUND;
      END LOOP;
      CLOSE cPoseeComi;

      /* Actualizando comision Tramo 2 = 0 */
      OPEN cPoseeComiSoloTramo1;
      LOOP
          FETCH cPoseeComiSoloTramo1 BULK COLLECT INTO tablaRegistroLideres LIMIT W_FILAS;
          IF tablaRegistroLideres.COUNT > 0 THEN
            FORALL x IN tablaRegistroLideres.FIRST .. tablaRegistroLideres.LAST
                 UPDATE COM_COMIS_DSCTO A
                 SET A.IMP_COM2 = 0
                 WHERE A.COD_PERI = psCodPeriodo
                   AND A.COD_COMI = psCodComision
                   AND A.COD_CLIE_COMI = tablaRegistroLideres(x);
          END IF;
          EXIT WHEN cPoseeComiSoloTramo1%NOTFOUND;
      END LOOP;
      CLOSE cPoseeComiSoloTramo1;

      /* Actualizando comision Tramo 1 = 0 */
      OPEN cPoseeComiSoloTramo2;
      LOOP
          FETCH cPoseeComiSoloTramo2 BULK COLLECT INTO tablaRegistroLideres LIMIT W_FILAS;
          IF tablaRegistroLideres.COUNT > 0 THEN
            FORALL x IN tablaRegistroLideres.FIRST .. tablaRegistroLideres.LAST
                 UPDATE COM_COMIS_DSCTO A
                 SET A.IMP_COM1 = 0
                 WHERE A.COD_PERI = psCodPeriodo
                   AND A.COD_COMI = psCodComision
                   AND A.COD_CLIE_COMI = tablaRegistroLideres(x);
          END IF;
          EXIT WHEN cPoseeComiSoloTramo2%NOTFOUND;
      END LOOP;
      CLOSE cPoseeComiSoloTramo2;
  END IF;

  /* Grabando importes en Recuperaciones por Campa?a x Seccion */
  IF psTipoComision = '03' THEN --Antes L
    INSERT INTO COM_RECUP_CAMPA_SECCI
      (COD_PERI, COD_COMI, COD_REGI,
       COD_ZONA, COD_SECC,
       IMP_VENT, IMP_COBR_PRIM_TRAM, IMP_COBR_SEGU_TRAM,
       FEC_DIGI, USU_DIGI)
    SELECT
       psCodPeriodo, psCodComision, A.COD_REGI,
       A.COD_ZONA, A.COD_SECC,
       A.IMP_NETO_SIN_RECL, A.IMP_MONT_PAGO_COMI, A.IMP_MONT_PAGO_COMI_TRA2,
       SYSDATE, psUsuario
    FROM COM_COMIS_PERIO_CALCU A
    WHERE A.PERI_COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision
      AND A.IND_COMI = COMISION_RECUPERACION;
  END IF;

  IF psTipoComision = '02' THEN --Antes G
     /* Grabando importes en Recuperaciones por Campa?a zona */
     IF lsBaseComision <> '07' THEN
     INSERT INTO COM_RECUP_CAMPA_ZONA
        (COD_PERI, COD_COMI, COD_REGI, COD_ZONA,
         IMP_VENT, IMP_COBR_PRIM_TRAM, IMP_COBR_SEGU_TRAM,
         FEC_DIGI, USU_DIGI)
     SELECT
        psCodPeriodo, psCodComision, A.COD_REGI, A.COD_ZONA,
        A.IMP_NETO_SIN_RECL, A.IMP_MONT_PAGO_COMI, A.IMP_MONT_PAGO_COMI_TRA2,
        SYSDATE, psUsuario
     FROM COM_COMIS_PERIO_CALCU_ZONA A
     WHERE A.PERI_COD_PERI = psCodPeriodo
       AND A.COD_COMI = psCodComision;
     ELSE
        INSERT INTO COM_RECUP_CAMPA_ZONA
          (COD_PERI, COD_COMI, COD_REGI, COD_ZONA,
           IMP_VENT, IMP_COBR_PRIM_TRAM, IMP_COBR_SEGU_TRAM,
           FEC_DIGI, USU_DIGI)
         SELECT
            psCodPeriodo, psCodComision, A.COD_REGI, A.COD_ZONA,
            A.IMP_NETO_SIN_RECL, A.IMP_MONT_PAGO_COMI, A.IMP_MONT_PAGO_COMI_TRA2,
            SYSDATE, psUsuario
         FROM COM_COMIS_PERIO_CALCU_ZONA A
         WHERE A.PERI_COD_PERI = psCodPeriodo
           AND A.COD_COMI = psCodComision
           AND A.COD_REGI = psCodigoRegionBase07
           AND A.COD_ZONA = psCodigoZonaBase07;
     END IF;

  END IF;

  IF psTipoComision = '01' THEN --Antes Gr
     /* Grabando importes en Recuperaciones por Campa?a region */
     IF lsBaseComision <> '07' THEN
     INSERT INTO COM_RECUP_CAMPA_REGIO
        (COD_PERI, COD_COMI, COD_REGI,
         IMP_VENT, IMP_COBR_PRIM_TRAM, IMP_COBR_SEGU_TRAM,
         FEC_DIGI, USU_DIGI)
     SELECT
        psCodPeriodo, psCodComision, A.COD_REGI,
        A.IMP_MONT_VENT, A.IMP_MONT_PAGO_COMI, A.IMP_MONT_PAGO_COMI_TRA2,
        SYSDATE, psUsuario
     FROM COM_COMIS_PERIO_CALCU_REGIO A
     WHERE A.COD_PERI = psCodPeriodo
       AND A.COD_COMI = psCodComision;
     ELSE
        INSERT INTO COM_RECUP_CAMPA_REGIO
            (COD_PERI, COD_COMI, COD_REGI,
             IMP_VENT, IMP_COBR_PRIM_TRAM, IMP_COBR_SEGU_TRAM,
             FEC_DIGI, USU_DIGI)
         SELECT
            psCodPeriodo, psCodComision, A.COD_REGI,
            A.IMP_MONT_VENT, A.IMP_MONT_PAGO_COMI, A.IMP_MONT_PAGO_COMI_TRA2,
            SYSDATE, psUsuario
         FROM COM_COMIS_PERIO_CALCU_REGIO A
         WHERE A.COD_PERI = psCodPeriodo
           AND A.COD_COMI = psCodComision
           AND A.COD_REGI = psCodigoRegionBase07;
    END IF;
  END IF;

  COMMIT;
  RETURN ;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_GENER_LISTA_COMIS_RECUP: '||ls_sqlerrm||' Pag: '||lnPag||' x: '||lnX);
END COM_PR_GENER_LISTA_COMIS_RECUP;


/***************************************************************************
Descripcion        : Funcion auxiliar de Lista de Comisiones x Recuperacion. Genera los
                    importes de pago de acuerdo al cliente y periodo ingresados
Fecha Creacion     : 16/03/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_MONTO_COMIS_RECUP(
  vsCodigoPais            VARCHAR2,
  vsEvaluaTelecobro       VARCHAR2,
  vsIndicadorCrono        VARCHAR2,
  vsTipoComision          VARCHAR2,
  vbEsSoloTramo1          BOOLEAN,
  vbDemandaAnticipada     BOOLEAN,
  vnNumDiasDemandaTramo1  NUMBER,
  vnNumDiasDemandaTramo2  NUMBER,
  vnIdPeriodo             NUMBER,
  vnIdPeriodoAnterior     NUMBER,
  vsCodPeriAnterior       VARCHAR2,
  vnIdPeriodoSiguiente    NUMBER,
  vnIdPeriodoMas2         NUMBER,
  vnIdComision            NUMBER,
  vnIdRegion              NUMBER,
  vnIdZona                NUMBER,
  vnIdEstadoAnulado       NUMBER,
  vnIdPais                NUMBER,
  vnFlagDescuento         NUMBER,
  vnFlagWeekend           NUMBER,
  vnImporteVenta          NUMBER,
  vnPorRecupera           NUMBER,
  vnPorActivida           NUMBER,
  vnMontoBono             NUMBER,
  vsBaseComision          VARCHAR2,
  vnPorcenVentaConsu      NUMBER,
  vdFechaRetiroBase07     DATE,
  vregRegistro            IN OUT NOCOPY COM_REPOR_COMIS_RECUP%ROWTYPE,
  vnSumaComisionTramo1    OUT NUMBER,
  vnSumaComisionTramo2    OUT NUMBER
)
IS
 TYPE tRegImporte IS RECORD (
     IMP_MOVI               CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE,
     IMP_PAGO               CCC_MOVIM_CUENT_CORRI.IMP_PAGO%TYPE,
     VAL_ULTI_NUME_HIST     CCC_MOVIM_CUENT_CORRI.VAL_ULTI_NUME_HIST%TYPE,
     SUBP_OID_SUBP_ULTI     CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_ULTI%TYPE,
     OID_MOVI_CC            CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC%TYPE,
     FEC_ULTI_MOVI          DATE,
     FEC_DOCU               DATE,
     NUM_IDEN_CUOT          CCC_MOVIM_CUENT_CORRI.NUM_IDEN_CUOT%TYPE,
     PERD_OID_PERI          CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI%TYPE,
     FEC_VENC               DATE,
     VAL_TASA_IMPU          PED_SOLIC_CABEC.VAL_TASA_IMPU%TYPE,
     SOCA_OID_SOLI_CABE     CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE%TYPE,
     SUBP_OID_SUBP_CREA     CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_CREA%TYPE,
     PERD_OID_PERI_PEDI     PED_SOLIC_CABEC.PERD_OID_PERI%TYPE
 );

 TYPE tTablaImporte IS TABLE OF tRegImporte;

 regImporte               tRegImporte;
 regImporteHisto          tRegImporte;
 tablaImporte             tTablaImporte;
 tablaImporteHisto        tTablaImporte;
 ldFechaLimite            DATE;
 ldFechaInicio            DATE;
 ldFechaFaturacionAdelantada DATE;
 lnContador               NUMBER;
 lnContadorVerifica       NUMBER;
 lnSumImporteReclamo      NUMBER;
 lnSumImporteReclamo_cimp NUMBER;
 lbInsertarHisto          BOOLEAN;


 lnValorIGV               NUMBER;
 vnImpuVent               NUMBER;
 lnNumDiasTramo1          NUMBER;
 lnNumDiasTramo2          NUMBER;
 lnNumDiasComisionable    NUMBER;
 lbContinuar              BOOLEAN;
 lbVerificarDiferencia    BOOLEAN;
 lb_facturacionAdelantada BOOLEAN;

 lnValorPorcenTramo1      NUMBER;
 lnSumNumActiFina         NUMBER;
 lnSumNumPedi             NUMBER;
 lsIndicadorComisionable  COM_COMIS_SUBPR_PAGO.ind_comi%TYPE;

 regRegistroDetalle       COM_REPOR_COMIS_RECUP%ROWTYPE;
 regRegistroDetalleActual COM_REPOR_COMIS_RECUP%ROWTYPE;

 CURSOR cImporte IS
   SELECT A.IMP_MOVI,
          A.IMP_PAGO,
          A.VAL_ULTI_NUME_HIST,
          A.SUBP_OID_SUBP_ULTI,
          A.OID_MOVI_CC,
          A.FEC_ULTI_MOVI,
          A.FEC_DOCU,
          A.NUM_IDEN_CUOT,
          A.PERD_OID_PERI,
          A.FEC_VENC,
          CONS.VAL_TASA_IMPU,
          A.SOCA_OID_SOLI_CABE,
          A.SUBP_OID_SUBP_CREA,
          CONS.PERD_OID_PERI
   FROM CCC_MOVIM_CUENT_CORRI A,
        PED_SOLIC_CABEC CONS

   WHERE A.CLIE_OID_CLIE = vregRegistro.OID_CLIE
     AND A.SUBP_OID_SUBP_CREA in (
              SELECT CS.OID_SUBP FROM CCC_PROCE CP, CCC_SUBPR CS, COM_COMIS_SUBPR_PAGO CCSP
              WHERE CP.OID_PROC = CS.CCPR_OID_PROC
              AND CCSP.COD_PROC = CP.COD_PROC
              AND CCSP.COD_SUBP = CS.COD_SUBP
              AND CCSP.COD_COMI = COMISION_RECUPERACION
              AND CCSP.TIP_COMI = TIPO_CARGO
              AND CP.PAIS_OID_PAIS = vnIdPais
           )
     AND (A.PERD_OID_PERI = vnIdPeriodo)
     AND A.IMP_MOVI > 0
     AND CONS.OID_SOLI_CABE = A.SOCA_OID_SOLI_CABE
     AND A.SOCA_OID_SOLI_CABE IS NOT NULL
     AND ((vsBaseComision <> '07') OR (vsBaseComision = '07' AND A.FEC_DOCU <= vdFechaRetiroBase07))
   UNION

   SELECT A.IMP_MOVI,
          A.IMP_PAGO,
          A.VAL_ULTI_NUME_HIST,
          A.SUBP_OID_SUBP_ULTI,
          A.OID_MOVI_CC,
          A.FEC_ULTI_MOVI,
          A.FEC_DOCU,
          A.NUM_IDEN_CUOT,
          A.PERD_OID_PERI,
          A.FEC_VENC,
          0.00 AS VAL_TASA_IMPU,
          A.SOCA_OID_SOLI_CABE,
          A.SUBP_OID_SUBP_CREA,
          NULL
   FROM CCC_MOVIM_CUENT_CORRI A
   WHERE A.CLIE_OID_CLIE = vregRegistro.OID_CLIE
     AND A.SUBP_OID_SUBP_CREA in (
              SELECT CS.OID_SUBP FROM CCC_PROCE CP, CCC_SUBPR CS, COM_COMIS_SUBPR_PAGO CCSP
              WHERE CP.OID_PROC = CS.CCPR_OID_PROC
              AND CCSP.COD_PROC = CP.COD_PROC
              AND CCSP.COD_SUBP = CS.COD_SUBP
              AND CCSP.COD_COMI = COMISION_RECUPERACION
              AND CCSP.TIP_COMI = TIPO_CARGO
              AND CP.PAIS_OID_PAIS = vnIdPais
           )
     AND (A.PERD_OID_PERI = vnIdPeriodo)
     AND A.IMP_MOVI > 0
     AND A.SOCA_OID_SOLI_CABE IS NULL
     AND ((vsBaseComision <> '07') OR (vsBaseComision = '07' AND A.FEC_DOCU <= vdFechaRetiroBase07))
   ORDER BY FEC_ULTI_MOVI ;

 CURSOR cImporteHisto(vnIdMovimCC NUMBER)  IS
   SELECT A.IMP_MOVI,
          A.IMP_PAGO,
          0,
          A.SUBP_OID_SUBP,
          A.MVCC_OID_MOVI_CC,
          A.FEC_MOVI,
          A.FEC_DOCU,
          A.NUM_HIST,
          0,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL
   FROM CCC_HISTO_MOVIM_CC A
   WHERE A.MVCC_OID_MOVI_CC = vnIdMovimCC
   ORDER BY A.FEC_MOVI  ;
  cENCONTRADO VARCHAR2(1);
  ldFechaLimiteComis     DATE;
  lnNum_Dias_LimiComis   NUMBER;

  ln_contadorTelecobro1   number(6);
  ln_contadorTelecobro2   number(6);
  lnIdPeriActual          NUMBER;
  ldFechaTelecobro        date;
  lbGrabarPago            boolean;
  lbGrabarPagoHistorico   boolean;
  lbVerificarEsPagoActual boolean;
  lbEsPago                BOOLEAN;
  lbEsReclamo             BOOLEAN;
  lbGenerarReclamo        BOOLEAN;
  lsCodTipoClasi          COM_CLASI_CLIEN.COD_TIPO_CLAS%TYPE;
  lsCodClasi              COM_CLASI_CLIEN.COD_CLAS%TYPE;
  lnValorDscto            NUMBER;
  lnSumaPagosHistoricos   NUMBER;

BEGIN
  /* Recorriendo Lista de Importes */
  lnContador := 0;
  lnSumImporteReclamo := 0;
  lnSumImporteReclamo_cimp := 0;
  vnSumaComisionTramo1 := 0;
  vnSumaComisionTramo2 := 0;
  lnNumDiasComisionable := 0;
  lnNumDiasTramo1 := 0;
  lnNumDiasTramo2 := 0;
  lnNumDiasTramo1 := vregRegistro.Num_Dias_Comi;
  lnNumDiasTramo2 := vregRegistro.Num_Dias_Comi_Tra2;

  IF not vbDemandaAnticipada THEN
     IF Gln_FlagComisEscal = 0 THEN --SI_ES_ESCALONADA  =  'N'
        lnNumDiasTramo1 := vregRegistro.Num_Dias_Comi;
        lnNumDiasTramo2 := vregRegistro.Num_Dias_Comi_Tra2;
     ELSE
        /* PTO 17 */
        BEGIN
          SELECT b.num_dias_recu, b.num_dias_comi
          INTO lnNumDiasTramo1, lnNumDiasComisionable
          FROM COM_COMIS_ESCAL B, COM_COMIS_COBRA A
          WHERE A.COMI_OID_COMI  = vnIdComision
            AND A.OID_COMI_COBR = B.OID_COMI_COBR
            AND ROWNUM=1;
          regRegistroDetalle.Num_Dias_Limi_Comi_Esca := lnNumDiasComisionable;
        EXCEPTION
        WHEN no_data_found THEN
             lnNumDiasTramo1 := 0;
             lnNumDiasTramo2 := 0;
             lnNumDiasComisionable := 0;
             regRegistroDetalle.Num_Dias_Limi_Comi_Esca := 0;
        END ;
     END IF;
  END IF;

  ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodo, vnIdZona, 'FA');
  OPEN cImporte;
  LOOP
      FETCH cImporte BULK COLLECT INTO tablaImporte LIMIT W_FILAS;
      IF tablaImporte.COUNT > 0 THEN
         FOR x IN tablaImporte.FIRST .. tablaImporte.LAST LOOP
              regImporte    := tablaImporte(x);
              regImporte.FEC_DOCU := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(regImporte.FEC_DOCU);
              regImporte.FEC_ULTI_MOVI := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(regImporte.FEC_ULTI_MOVI);

              lbContinuar := TRUE;
              lbGrabarPago := true;
              lbVerificarEsPagoActual := false;
            --ldFechaInicio := regImporte.FEC_DOCU;
              lb_facturacionAdelantada:=FALSE;
            lsIndicadorComisionable := '0';

            /* obteniendo valor de impuesto */
            lnValorIGV := regImporte.VAL_TASA_IMPU;
            IF regImporte.SOCA_OID_SOLI_CABE IS NULL THEN
                vnImpuVent := COM_FN_DEVUE_TASA_IMPUE(vsCodigoPais, regImporte.FEC_DOCU);
                IF vnImpuVent > 0 THEN
                   lnValorIGV := vnImpuVent;
                ELSE
                   RAISE_APPLICATION_ERROR(-20123, 'ERROR en BAS_TASA_IMPUE: Debe ingresar Monto de Impuesto para fecha: '||TO_CHAR(regImporte.FEC_DOCU, 'dd/mm/yyyy'));
                END IF;
            END IF;

             /* EVALUACION TELECOBRO */
            ldFechaTelecobro := null;
            IF vsEvaluaTelecobro = '1' THEN
               --Antes G
               IF  vsTipoComision <> '02' OR
                  (vsTipoComision = '02' and Gln_FlagComisEscal = 0 and vbEsSoloTramo1) OR
                  (vsTipoComision = '02' AND vsBaseComision = '04') THEN
                    BEGIN
                      select X.FEC_ASIG
                      into  ldFechaTelecobro
                      from  COB_DETAL_MOVIM_CARTE X
                      where OID_CLIE = vregRegistro.OID_CLIE
                      and   COD_PERI = vregRegistro.COD_PERI
                      and   MVCC_OID_MOVI_CC = regImporte.OID_MOVI_CC
                      AND   COD_ETAP_DEUD = 'TEL';
                    EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                         lbContinuar := TRUE;
                         ldFechaTelecobro := null;
                   END;
               END IF;
             END IF;

             IF lbContinuar THEN
                IF vsEvaluaTelecobro = '1' THEN
                 --Antes G
                 IF  vsTipoComision <> '02' OR
                    (vsTipoComision = '02' and Gln_FlagComisEscal = 0 and vbEsSoloTramo1) OR
                    (vsTipoComision = '02' AND vsBaseComision = '04')THEN
                   IF regImporte.SOCA_OID_SOLI_CABE IS NOT NULL THEN
                      IF (regImporte.PERD_OID_PERI_PEDI <> regImporte.PERD_OID_PERI) THEN
                          ln_contadorTelecobro1 := 0;
                          SELECT COUNT(1)
                          into ln_contadorTelecobro1
                          FROM CCC_MOVIM_CUENT_CORRI A,
                               COB_DETAL_MOVIM_CARTE X

                          WHERE A.CLIE_OID_CLIE = vregRegistro.OID_CLIE
                           AND A.SUBP_OID_SUBP_CREA = regImporte.SUBP_OID_SUBP_CREA
                           AND A.PERD_OID_PERI = vnIdPeriodoAnterior
                           AND A.SOCA_OID_SOLI_CABE = regImporte.SOCA_OID_SOLI_CABE

                           AND OID_CLIE = A.CLIE_OID_CLIE
                           AND COD_PERI = vsCodPeriAnterior
                           AND MVCC_OID_MOVI_CC = A.OID_MOVI_CC
                           AND COD_ETAP_DEUD = 'TEL';

                          IF ln_contadorTelecobro1 > 0  THEN
                             lbContinuar := FALSE;
                          END IF;

                       END IF;
                   END IF;
                 END IF;
                END IF;
             END IF;

             IF lbContinuar THEN

               /* seteando valores */
               regRegistroDetalle := vregRegistro;
               lnContador := lnContador + 1;

               regRegistroDetalle.Num_Item   := lnContador;
               regRegistroDetalle.Imp_Neto   := 0;
               regRegistroDetalle.Imp_Neto_cimp   := 0;
               regRegistroDetalle.IMP_CARG_FRAC := 0;
               regRegistroDetalle.IMP_CARG_FRAC_CIMP := 0;
               regRegistroDetalle.Imp_Pago   := 0;
               regRegistroDetalle.Imp_Pago_Cimp   := 0;
               regRegistroDetalle.Imp_Recl   := 0;
               regRegistroDetalle.Imp_Comi   := 0;
               regRegistroDetalle.Por_Recu   := 0;
               regRegistroDetalle.Imp_Comi_Tra2 := 0;
               regRegistroDetalle.Imp_Pago_Ante_Limi := 0;
               regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2 := 0;
               regRegistroDetalle.Imp_Pago_Ante_Limi_Cimp := 0;
               regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2_Cimp := 0;

               regRegistroDetalle.Imp_Mont_Pago_Comi := 0.00;
               regRegistroDetalle.Imp_Mont_Pago_Comi_CIMP := 0.00;
               regRegistroDetalle.Imp_Mont_Pago_Comi_tra2 := 0.00;
               regRegistroDetalle.Imp_Mont_Pago_Comi_tra2_CIMP := 0.00;
               regRegistroDetalle.Fec_Emis   := regImporte.FEC_DOCU;
               regRegistroDetalle.Fec_Pago   := regImporte.FEC_ULTI_MOVI;
               regRegistroDetalle.Num_Bole_Desp := regImporte.NUM_IDEN_CUOT;
               regRegistroDetalle.Imp_impu   := 0;
               lnSumImporteReclamo := 0;
               lnSumImporteReclamo_cimp := 0;
               regRegistroDetalle.Fec_Emis :=  regImporte.FEC_DOCU;
               lbEsPago := TRUE;
               lbEsReclamo := FALSE;

               /* OBTENIENDO FECHA DE EMIS */
               IF regImporte.SOCA_OID_SOLI_CABE IS NOT NULL THEN
                  IF (regImporte.PERD_OID_PERI_PEDI <> vnIdPeriodo) THEN
                      regImporte.fec_docu := ldFechaInicio;
                      regRegistroDetalle.FEC_EMIS := ldFechaInicio;
                   END IF;
               END IF;

               /* obteniendo importe neto / carga fraccionaria */
               IF (regImporte.perd_oid_peri = regImporte.PERD_OID_PERI_PEDI OR regImporte.SOCA_OID_SOLI_CABE IS NULL) THEN
                  regRegistroDetalle.Imp_Neto := ROUND(regImporte.IMP_MOVI/(1 + lnValorIGV / 100),2);
                  regRegistroDetalle.Imp_Neto_cimp := ROUND(regImporte.IMP_MOVI,2);
                  regRegistroDetalle.Imp_impu := regRegistroDetalle.Imp_Neto_cimp - regRegistroDetalle.Imp_Neto;
               ELSE
                  regRegistroDetalle.IMP_CARG_FRAC := ROUND(regImporte.IMP_MOVI/(1 + lnValorIGV / 100),2);
                  regRegistroDetalle.IMP_CARG_FRAC_CIMP := ROUND(regImporte.IMP_MOVI,2);
                  regRegistroDetalle.Imp_impu := regRegistroDetalle.IMP_CARG_FRAC_CIMP - regRegistroDetalle.IMP_CARG_FRAC;
               END IF;

               IF regImporte.VAL_ULTI_NUME_HIST > 0 THEN
                  lbVerificarDiferencia := TRUE;
                  BEGIN
                    SELECT A.CAM_CAD2
                    INTO lsIndicadorComisionable
                    FROM PER_GTT_TABLA_REPOS A
                    WHERE A.CAM_NUME = regImporte.SUBP_OID_SUBP_ULTI
                      AND A.CAM_CADE = TIPO_PAGO;
                  EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       lbVerificarDiferencia := FALSE;
                  END;

                  IF (lbVerificarDiferencia) THEN
                     lbEsPago := TRUE;
                     lbVerificarEsPagoActual := true;

                     /* Ver si se grba comisiones por Telecobro */
                     IF vsEvaluaTelecobro = '1' AND ldFechaTelecobro IS NOT NULL  THEN
                        IF ldFechaTelecobro < regImporte.FEC_ULTI_MOVI THEN
                           lbGrabarPago := false;
                        END IF;
                     END IF;

                     IF (vsBaseComision = '07'  AND regRegistroDetalle.Fec_Pago > vdFechaRetiroBase07)  THEN
                         lbGrabarPago := false;
                     END IF;

                     /* obteniendo importes DE PAGO Actuales */
                     regRegistroDetalle.Imp_Pago_Cimp := ROUND(regImporte.IMP_PAGO,2);
                     regRegistroDetalle.Imp_Pago := ROUND(regImporte.IMP_PAGO/(1 + lnValorIGV / 100),2);

                     /* verificando diferencia de dias para tramo 1 */
                     ldFechaLimite := regRegistroDetalle.Fec_Emis + lnNumDiasTramo1;
                     ldFechaLimite := trunc(ldFechaLimite);
                     regRegistroDetalle.Num_Dias_Limi := COM_FN_DEVUE_NUMER_DIAS(ldFechaLimite, lnNumDiasTramo1, vregRegistro.Cod_Zona, vnFlagWeekend);

                     /* obteniendo diferencia de dias */
                     IF regImporte.SOCA_OID_SOLI_CABE IS NOT NULL THEN
                        IF (regImporte.PERD_OID_PERI_PEDI = vnIdPeriodo) THEN
                            IF regImporte.FEC_DOCU < ldFechaInicio and vsIndicadorCrono = '1' THEN
                               regRegistroDetalle.Num_Dias_Dife:=regImporte.fec_ulti_movi - ldFechaInicio;
                            ELSE
                               regRegistroDetalle.Num_Dias_Dife:=regImporte.fec_ulti_movi -  regImporte.fec_docu;
                            END IF;
                        ELSE
                            regRegistroDetalle.Num_Dias_Dife:=regImporte.fec_ulti_movi -  regImporte.fec_docu;
                        END IF;
                     ELSE
                        regRegistroDetalle.Num_Dias_Dife:=regImporte.fec_ulti_movi -  regImporte.fec_docu;
                     END IF;

                     IF Gln_FlagComisEscal = 0 THEN --SI_ES_ESCALONADA  =  'N'
                         IF (vsBaseComision <> '07' AND  regRegistroDetalle.Num_Dias_Dife <= regRegistroDetalle.Num_Dias_Limi) OR
                            (vsBaseComision = '07' AND regRegistroDetalle.Fec_Pago <= vdFechaRetiroBase07)  THEN
                            regRegistroDetalle.Imp_Pago_Ante_Limi_Cimp := regRegistroDetalle.Imp_Pago_Cimp;
                            regRegistroDetalle.Imp_Pago_Ante_Limi := regRegistroDetalle.Imp_Pago;
                            IF lsIndicadorComisionable = '1' THEN
                               regRegistroDetalle.Imp_Mont_Pago_Comi_CIMP := regRegistroDetalle.Imp_Pago_Cimp;
                               regRegistroDetalle.Imp_Mont_Pago_Comi := regRegistroDetalle.Imp_Pago;
                            END IF;
                         ELSE
                            regRegistroDetalle.Imp_Pago_Ante_Limi_Cimp := 0.00;
                            regRegistroDetalle.Imp_Pago_Ante_Limi := 0.00;
                            regRegistroDetalle.Imp_Mont_Pago_Comi_CIMP := 0.00;
                            regRegistroDetalle.Imp_Mont_Pago_Comi := 0.00;

                            /* verificando diferencia de dias para tramo 2 */
                            ldFechaLimite := regRegistroDetalle.Fec_Emis + lnNumDiasTramo2;
                            ldFechaLimite := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(ldFechaLimite);

                            regRegistroDetalle.Num_Dias_Limi_Tra2 := COM_FN_DEVUE_NUMER_DIAS(ldFechaLimite, lnNumDiasTramo2, vregRegistro.Cod_Zona, vnFlagWeekend);
                            IF regRegistroDetalle.Num_Dias_Dife >= 0 THEN
                               IF (vsBaseComision <> '07' AND regRegistroDetalle.Num_Dias_Dife <= regRegistroDetalle.Num_Dias_Limi_Tra2 ) OR
                                  (vsBaseComision = '07' AND regRegistroDetalle.Fec_Pago <= vdFechaRetiroBase07)  THEN
                                  regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2_Cimp := regRegistroDetalle.Imp_Pago_Cimp;
                                  regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2 := regRegistroDetalle.Imp_Pago;
                                  IF lsIndicadorComisionable = '1' THEN
                                     regRegistroDetalle.Imp_Mont_Pago_Comi_tra2_CIMP := regRegistroDetalle.Imp_Pago_Cimp;
                                     regRegistroDetalle.Imp_Mont_Pago_Comi_tra2 := regRegistroDetalle.Imp_Pago;
                                  END IF;
                               ELSE
                                 regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2_Cimp := 0.00;
                                 regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2 := 0.00;
                                 regRegistroDetalle.Imp_Mont_Pago_Comi_tra2_CIMP := 0.00;
                                 regRegistroDetalle.Imp_Mont_Pago_Comi_tra2 := 0.00;
                               END IF;
                            END IF;
                         END IF;
                     ELSE
                         /* PUNTO 15: SI ES ESCALONADA , LAS CONDICIONES SE RIGEN BASADO EN QUERYS */
                         IF (vsBaseComision <> '07' AND regRegistroDetalle.Num_Dias_Dife <= regRegistroDetalle.Num_Dias_Limi ) OR
                            (vsBaseComision = '07'  AND regRegistroDetalle.Fec_Pago <= vdFechaRetiroBase07)  THEN
                            regRegistroDetalle.Imp_Pago_Ante_Limi_Cimp := regRegistroDetalle.Imp_Pago_Cimp;
                            regRegistroDetalle.Imp_Pago_Ante_Limi := regRegistroDetalle.Imp_Pago;
                            IF lsIndicadorComisionable = '1' THEN
                               regRegistroDetalle.Imp_Mont_Pago_Comi_CIMP := regRegistroDetalle.Imp_Pago_Cimp;
                               regRegistroDetalle.Imp_Mont_Pago_Comi := regRegistroDetalle.Imp_Pago;
                            END IF;
                         ELSE
                            regRegistroDetalle.Imp_Pago_Ante_Limi_Cimp := 0.00;
                            regRegistroDetalle.Imp_Pago_Ante_Limi := 0.00;
                            regRegistroDetalle.Imp_Mont_Pago_Comi_CIMP := 0.00;
                            regRegistroDetalle.Imp_Mont_Pago_Comi := 0.00;
                         END IF;
                         regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2_Cimp := 0.00;
                         regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2 := 0.00;
                         regRegistroDetalle.Imp_Mont_Pago_Comi_tra2_CIMP := 0.00;
                         regRegistroDetalle.Imp_Mont_Pago_Comi_tra2 := 0.00;

                         ldFechaLimiteComis := regRegistroDetalle.Fec_Emis + lnNumDiasComisionable;
                         ldFechaLimiteComis := trunc(ldFechaLimiteComis);
                         lnNum_Dias_LimiComis := COM_FN_DEVUE_NUMER_DIAS(ldFechaLimiteComis, lnNumDiasComisionable, vregRegistro.Cod_Zona, vnFlagWeekend);

                     END IF;
                  ELSE

                     lbVerificarDiferencia := TRUE;
                     BEGIN
                       SELECT A.CAM_CAD2
                       INTO lsIndicadorComisionable
                       FROM PER_GTT_TABLA_REPOS A
                       WHERE A.CAM_NUME = regImporte.SUBP_OID_SUBP_ULTI
                         AND A.CAM_CADE = TIPO_RECLAMO;
                     EXCEPTION
                     WHEN NO_DATA_FOUND THEN
                          lbVerificarDiferencia := FALSE;
                     END;
                     IF (lbVerificarDiferencia) THEN
                        lbEsReclamo := TRUE;

                        /* obteniendo RECLAMOS Actuales */
                        regRegistroDetalle.Imp_Recl := ROUND((regImporte.IMP_PAGO/(1 + lnValorIGV / 100)),2);
                        regRegistroDetalle.Imp_Recl_CImp := ROUND(regImporte.IMP_PAGO,2);
                        lnSumImporteReclamo := lnSumImporteReclamo + regRegistroDetalle.Imp_Recl;
                        lnSumImporteReclamo_cimp := lnSumImporteReclamo_cimp + regRegistroDetalle.Imp_Recl_CImp;
                     END IF;
                  END IF;
               END IF;

               /* obteniendo comision tramo 1 */
               IF regRegistroDetalle.Imp_Mont_Pago_Comi <> 0 THEN
                  regRegistroDetalle.Imp_Comi := round((regRegistroDetalle.Imp_Mont_Pago_Comi * vregRegistro.Val_Porc_Comi) / 100, 2) ;
                  regRegistroDetalle.Imp_Comi_Tra2 := round((regRegistroDetalle.Imp_Mont_Pago_Comi * vregRegistro.Val_Porc_Comi_Tra2) / 100, 2) ;
                  vnSumaComisionTramo1 := vnSumaComisionTramo1 + regRegistroDetalle.Imp_Comi;
                  vnSumaComisionTramo2 := vnSumaComisionTramo2 + regRegistroDetalle.Imp_Comi_Tra2;
               ELSE
                  /* obteniendo comision tramo 2 */
                  IF regRegistroDetalle.Imp_Mont_Pago_Comi_Tra2 <> 0 THEN
                     regRegistroDetalle.Imp_Comi_Tra2 := round((regRegistroDetalle.Imp_Mont_Pago_Comi_Tra2 * vregRegistro.Val_Porc_Comi_Tra2) / 100, 2) ;
                     vnSumaComisionTramo2 := vnSumaComisionTramo2 + regRegistroDetalle.Imp_Comi_Tra2;
                  END IF;
               END IF;

               /* Guardando temporalmente el Detalle actual */
               regRegistroDetalle.imp_impu_pago := regRegistroDetalle.Imp_Mont_Pago_Comi_Cimp - regRegistroDetalle.Imp_Mont_Pago_Comi;
               regRegistroDetalleActual := regRegistroDetalle;

               /* Recorriendo Lista de Importes (Historicos) */
               lnSumaPagosHistoricos := 0;
               IF regImporte.VAL_ULTI_NUME_HIST > 1 THEN
                   OPEN cImporteHisto(regImporte.OID_MOVI_CC);
                   LOOP
                        FETCH cImporteHisto BULK COLLECT INTO tablaImporteHisto LIMIT W_FILAS;
                        IF tablaImporteHisto.COUNT > 0 THEN
                           FOR y IN tablaImporteHisto.FIRST .. tablaImporteHisto.LAST LOOP
                               lbInsertarHisto := TRUE;
                               regImporteHisto := tablaImporteHisto(y);
                               lbGrabarPagoHistorico := true;

                               /* obteniendo si corresponde al proceso de pago */
                               lbVerificarDiferencia := TRUE;
                               BEGIN
                                   SELECT A.CAM_CAD2
                                   INTO lsIndicadorComisionable
                                   FROM PER_GTT_TABLA_REPOS A
                                   WHERE A.CAM_NUME = regImporteHisto.SUBP_OID_SUBP_ULTI
                                    AND (A.CAM_CADE = 'P' OR  A.CAM_CADE = 'R');
                               EXCEPTION
                               WHEN NO_DATA_FOUND THEN
                                    lbVerificarDiferencia := FALSE;
                               END;
                               IF lbVerificarDiferencia THEN

                                   regImporteHisto.FEC_DOCU := TRUNC(regImporteHisto.FEC_DOCU);
                                   regImporteHisto.FEC_ULTI_MOVI := TRUNC(regImporteHisto.FEC_ULTI_MOVI);

                                   lnContador := lnContador + 1;
                                   regRegistroDetalle := vregRegistro;
                                   regRegistroDetalle.Num_Item   := lnContador;
                                   regRegistroDetalle.Imp_Neto   := 0;
                                   regRegistroDetalle.Imp_Pago   := 0;
                                   regRegistroDetalle.Imp_Pago_Cimp  := 0;
                                   regRegistroDetalle.Imp_Recl   := 0;
                                   regRegistroDetalle.Imp_Comi   := 0;
                                   regRegistroDetalle.Por_Recu   := 0;
                                   regRegistroDetalle.Imp_Comi_Tra2 := 0;
                                   regRegistroDetalle.Imp_Pago_Ante_Limi := 0;
                                   regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2 := 0;
                                   regRegistroDetalle.Imp_Pago_Ante_Limi_Cimp := 0;
                                   regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2_Cimp := 0;
                                   regRegistroDetalle.Imp_Mont_Pago_Comi := 0;
                                   regRegistroDetalle.Imp_Mont_Pago_Comi_cimp := 0;
                                   regRegistroDetalle.Imp_Mont_Pago_Comi_tra2 := 0;
                                   regRegistroDetalle.Imp_Mont_Pago_Comi_tra2_cimp := 0;

                                   regRegistroDetalle.Fec_Emis   := regImporteHisto.FEC_DOCU;
                                   regRegistroDetalle.Fec_Pago   := regImporteHisto.FEC_ULTI_MOVI;
                                   regRegistroDetalle.Num_Bole_Desp := regImporte.NUM_IDEN_CUOT;

                                   /* OBTENIENDO FECHA DE EMIS */
                                   IF regImporte.SOCA_OID_SOLI_CABE IS NOT NULL THEN
                                      IF (regImporte.PERD_OID_PERI_PEDI <> vnIdPeriodo) THEN
                                          regImporteHisto.fec_docu := ldFechaInicio;
                                          regRegistroDetalle.FEC_EMIS := ldFechaInicio;
                                      END IF;
                                   END IF;

                                   /* obteniendo importes historicos */
                                   lbVerificarDiferencia := TRUE;
                                   BEGIN
                                      SELECT A.CAM_CAD2
                                      INTO lsIndicadorComisionable
                                      FROM PER_GTT_TABLA_REPOS A
                                      WHERE A.CAM_NUME = regImporteHisto.SUBP_OID_SUBP_ULTI
                                        AND A.CAM_CADE = 'P';
                                   EXCEPTION
                                   WHEN NO_DATA_FOUND THEN
                                         lbVerificarDiferencia := FALSE;
                                   END;
                                   IF (lbVerificarDiferencia) THEN

                                      /* Ver si se graba comisiones por Telecobro */
                                      IF vsEvaluaTelecobro = '1' AND ldFechaTelecobro IS NOT NULL  THEN
                                         IF ldFechaTelecobro < regImporteHisto.FEC_ULTI_MOVI THEN
                                            CONTINUE;
                                         END IF;
                                      END IF;

                                      IF (vsBaseComision = '07'  AND regRegistroDetalle.Fec_Pago > vdFechaRetiroBase07)  THEN
                                          CONTINUE;
                                      END IF;

                                      /* obteniendo importes PAGO HISTORICO  */
                                      regRegistroDetalle.Imp_Pago_Cimp := ROUND(regImporteHisto.IMP_PAGO,2);
                                      regRegistroDetalle.Imp_Pago := ROUND(regImporteHisto.IMP_PAGO/(1 + lnValorIGV /100),2);
                                      lnSumaPagosHistoricos := lnSumaPagosHistoricos + regRegistroDetalle.Imp_Pago_Cimp;

                                      /* verificando diferencia de dias para tramo 1 */
                                      ldFechaLimite := regRegistroDetalle.Fec_Emis + lnNumDiasTramo1;
                                      ldFechaLimite := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(ldFechaLimite);
                                      regRegistroDetalle.Num_Dias_Limi := COM_FN_DEVUE_NUMER_DIAS(ldFechaLimite, lnNumDiasTramo1, vregRegistro.Cod_Zona, vnFlagWeekend);

                                      /* obteniendo diferencia de dias */
                                      IF regImporte.SOCA_OID_SOLI_CABE IS NOT NULL THEN
                                         IF (regImporte.PERD_OID_PERI_PEDI = vnIdPeriodo) THEN
                                             IF regImporte.FEC_DOCU < ldFechaInicio and vsIndicadorCrono = '1' THEN
                                                regRegistroDetalle.Num_Dias_Dife:=regImporteHisto.fec_ulti_movi - ldFechaInicio;
                                             ELSE
                                                regRegistroDetalle.Num_Dias_Dife:=regImporteHisto.fec_ulti_movi -  regImporte.fec_docu;
                                             END IF;
                                         ELSE
                                             regRegistroDetalle.Num_Dias_Dife:=regImporteHisto.fec_ulti_movi -  regImporte.fec_docu;
                                         END IF;
                                      ELSE
                                         regRegistroDetalle.Num_Dias_Dife:=regImporteHisto.fec_ulti_movi -  regImporte.fec_docu;
                                      END IF;

                                      IF Gln_FlagComisEscal = 0 THEN --SI_ES_ESCALONADA  =  'N'
                                          IF (vsBaseComision <> '07' AND regRegistroDetalle.Num_Dias_Dife <= regRegistroDetalle.Num_Dias_Limi ) or
                                             (vsBaseComision = '07'  AND regRegistroDetalle.Fec_Pago <= vdFechaRetiroBase07)  THEN
                                             regRegistroDetalle.Imp_Pago_Ante_Limi_Cimp := regRegistroDetalle.Imp_Pago_Cimp;
                                             regRegistroDetalle.Imp_Pago_Ante_Limi := regRegistroDetalle.Imp_Pago;
                                             IF lsIndicadorComisionable = '1' THEN
                                                regRegistroDetalle.Imp_Mont_Pago_Comi_CIMP := regRegistroDetalle.Imp_Pago_Cimp;
                                                regRegistroDetalle.Imp_Mont_Pago_Comi := regRegistroDetalle.Imp_Pago;
                                             END IF;
                                          ELSE
                                             /* verificando diferencia de dias para tramo 2 */
                                             ldFechaLimite := regRegistroDetalle.Fec_Emis + lnNumDiasTramo2;
                                             ldFechaLimite := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(ldFechaLimite);

                                             regRegistroDetalle.Num_Dias_Limi_Tra2 := COM_FN_DEVUE_NUMER_DIAS(ldFechaLimite, lnNumDiasTramo2, vregRegistro.Cod_Zona, vnFlagWeekend);
                                             IF regRegistroDetalle.Num_Dias_Dife >= 0 THEN
                                                IF (vsBaseComision <> '07' AND regRegistroDetalle.Num_Dias_Dife <= regRegistroDetalle.Num_Dias_Limi_Tra2 ) OR
                                                   (vsBaseComision = '07'  AND regRegistroDetalle.Fec_Pago <= vdFechaRetiroBase07)  THEN

                                                   regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2_Cimp := regRegistroDetalle.Imp_Pago_Cimp;
                                                   regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2 := regRegistroDetalle.Imp_Pago;
                                                   IF lsIndicadorComisionable = '1' THEN
                                                      regRegistroDetalle.Imp_Mont_Pago_Comi_tra2_CIMP := regRegistroDetalle.Imp_Pago_Cimp;
                                                      regRegistroDetalle.Imp_Mont_Pago_Comi_tra2 := regRegistroDetalle.Imp_Pago;
                                                   END IF;
                                                END IF;
                                             END IF;
                                          END IF;
                                      ELSE
                                          /* PUNTO 15: SI ES ESCALONADA , LAS CONDICIONES SE RIGEN BASADO EN QUERYS */
                                         IF regRegistroDetalle.Num_Dias_Dife <= regRegistroDetalle.Num_Dias_Limi THEN
                                            regRegistroDetalle.Imp_Pago_Ante_Limi_Cimp := regRegistroDetalle.Imp_Pago_Cimp;
                                            regRegistroDetalle.Imp_Pago_Ante_Limi := regRegistroDetalle.Imp_Pago;
                                            IF lsIndicadorComisionable = '1' THEN
                                               regRegistroDetalle.Imp_Mont_Pago_Comi_CIMP := regRegistroDetalle.Imp_Pago_Cimp;
                                               regRegistroDetalle.Imp_Mont_Pago_Comi := regRegistroDetalle.Imp_Pago;
                                            END IF;
                                         ELSE
                                            regRegistroDetalle.Imp_Pago_Ante_Limi_Cimp := 0.00;
                                            regRegistroDetalle.Imp_Pago_Ante_Limi := 0.00;
                                            regRegistroDetalle.Imp_Mont_Pago_Comi_CIMP := 0.00;
                                            regRegistroDetalle.Imp_Mont_Pago_Comi := 0.00;
                                         END IF;
                                         regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2_Cimp := 0.00;
                                         regRegistroDetalle.Imp_Pago_Ante_Limi_Tra2 := 0.00;
                                         regRegistroDetalle.Imp_Mont_Pago_Comi_tra2_CIMP := 0.00;
                                         regRegistroDetalle.Imp_Mont_Pago_Comi_tra2 := 0.00;

                                         ldFechaLimiteComis := regRegistroDetalle.Fec_Emis + lnNumDiasComisionable;
                                         ldFechaLimiteComis := trunc(ldFechaLimiteComis);
                                         lnNum_Dias_LimiComis := COM_FN_DEVUE_NUMER_DIAS(ldFechaLimiteComis, lnNumDiasComisionable, vregRegistro.Cod_Zona, vnFlagWeekend);

                                     END IF;

                                   ELSE
                                      lbVerificarDiferencia := TRUE;
                                      BEGIN
                                         SELECT A.CAM_CAD2
                                         INTO lsIndicadorComisionable
                                         FROM PER_GTT_TABLA_REPOS A
                                         WHERE A.CAM_NUME = regImporteHisto.SUBP_OID_SUBP_ULTI
                                           AND A.CAM_CADE = 'R';
                                      EXCEPTION
                                      WHEN NO_DATA_FOUND THEN
                                           lbVerificarDiferencia := FALSE;
                                      END;
                                      IF (lbVerificarDiferencia) THEN
                                         /* obteniendo RECLAMOS HISTORICOS */
                                         regRegistroDetalle.Imp_Recl := ROUND(regImporteHisto.IMP_PAGO/(1 + lnValorIGV / 100),2);
                                         regRegistroDetalle.Imp_Recl_CImp := ROUND(regImporteHisto.IMP_PAGO,2);
                                         lnSumImporteReclamo := lnSumImporteReclamo + regRegistroDetalle.Imp_Recl;
                                         lnSumImporteReclamo_cimp := lnSumImporteReclamo_cimp + regRegistroDetalle.Imp_Recl_CImp;
                                         lbInsertarHisto := FALSE;
                                      END IF;
                                   END IF;

                                   /* obteniendo comision tramo 1*/
                                   IF regRegistroDetalle.Imp_Mont_Pago_Comi <> 0 THEN
                                      regRegistroDetalle.Imp_Comi := ROUND((regRegistroDetalle.Imp_Mont_Pago_Comi * vregRegistro.Val_Porc_Comi) / 100, 2) ;
                                      regRegistroDetalle.Imp_Comi_Tra2 := ROUND((regRegistroDetalle.Imp_Mont_Pago_Comi * vregRegistro.Val_Porc_Comi_Tra2) / 100, 2) ;
                                      vnSumaComisionTramo1 := vnSumaComisionTramo1 + regRegistroDetalle.Imp_Comi;
                                      vnSumaComisionTramo2 := vnSumaComisionTramo2 + regRegistroDetalle.Imp_Comi_Tra2;
                                   ELSE
                                      /* obteniendo comision tramo 2*/
                                      IF regRegistroDetalle.Imp_Mont_Pago_Comi_Tra2 <> 0 THEN
                                         regRegistroDetalle.Imp_Comi_Tra2 := ROUND((regRegistroDetalle.Imp_Mont_Pago_Comi_Tra2 * vregRegistro.Val_Porc_Comi_Tra2) / 100, 2) ;
                                         vnSumaComisionTramo2 := vnSumaComisionTramo2 + regRegistroDetalle.Imp_Comi_Tra2;
                                      END IF;
                                   END IF;

                                   regRegistroDetalle.Imp_Neto_Sin_Recl := 0;
                                   regRegistroDetalle.imp_impu_pago := regRegistroDetalle.Imp_Mont_Pago_Comi_Cimp - regRegistroDetalle.Imp_Mont_Pago_Comi;

                                   IF lbInsertarHisto THEN
                                      /*Inicio modificacion RCR COL-SiCC-2015-0159*/
                                        if vsBaseComision = '07' then
                                          regRegistroDetalle.IMP_COMI:= ROUND((regRegistroDetalle.IMP_NETO_SIN_RECL * regRegistroDetalle.VAL_PORC_COMI)/100,2);
                                        end if;
                                      /*Fin modificacion RCR COL-SiCC-2015-0159*/
                                       INSERT INTO COM_REPOR_COMIS_RECUP
                                       VALUES regRegistroDetalle;
                                   END IF;
                               END IF;
                           END LOOP;
                        END IF;
                        EXIT WHEN cImporteHisto%NOTFOUND;
                   END LOOP;
                   CLOSE cImporteHisto;
               END IF;

               /* Insertando en com_repor_comis_recup (Actual) */
               IF NOT lbGrabarPago then
                  regRegistroDetalleActual.FEC_PAGO := NULL;
                  regRegistroDetalleActual.IMP_PAGO := 0.0;
                  regRegistroDetalleActual.IMP_PAGO_ANTE_LIMI := 0.0;
                  regRegistroDetalleActual.IMP_PAGO_ANTE_LIMI_TRA2 := 0.0;
                  regRegistroDetalleActual.Imp_Mont_Pago_Comi := 0.0;
                  regRegistroDetalleActual.Imp_Mont_Pago_Comi_Tra2 := 0.0;
                  regRegistroDetalleActual.IMP_PAGO_CIMP := 0.0;
                  regRegistroDetalleActual.IMP_PAGO_ANTE_LIMI_CIMP := 0.0;
                  regRegistroDetalleActual.IMP_PAGO_ANTE_LIMI_TRA2_CIMP := 0.0;
                  regRegistroDetalleActual.Imp_Mont_Pago_Comi_cimp := 0.0;
                  regRegistroDetalleActual.Imp_Mont_Pago_Comi_Tra2_cimp := 0.0;
                  regRegistroDetalleActual.Num_Dias_Dife := 0 ;
                  regRegistroDetalleActual.imp_comi := 0.0;
               END IF;

               if not lbVerificarEsPagoActual then
                 regRegistroDetalleActual.FEC_PAGO := NULL;
               end if;
               regRegistroDetalleActual.Imp_Recl := lnSumImporteReclamo;
               regRegistroDetalleActual.Imp_Recl_cimp := lnSumImporteReclamo_cimp ;
               regRegistroDetalleActual.Imp_Neto_Sin_Recl := regRegistroDetalleActual.Imp_Neto + regRegistroDetalleActual.IMP_CARG_FRAC - regRegistroDetalleActual.Imp_Recl;
               regRegistroDetalleActual.Imp_Neto_Srec_Cimp := regRegistroDetalleActual.Imp_Neto_Cimp + regRegistroDetalleActual.Imp_Carg_Frac_Cimp - regRegistroDetalleActual.Imp_Recl_cimp;


               /* Si el Tipo  de Comisionista seleccionada es 02  ( GZ ) y COBA_COD_BASE  =  04 y Porcentaje
                  Venta Consultora > 0  ( Maestro de comisiones )  y se encontraron registros en la tabla  de
                  clasificaciones
               */
               regRegistroDetalleActual.IMP_DCTO_NETO := 0.00;
               regRegistroDetalleActual.IMP_DCTO_NETO_CIMP := 0.00;
               IF vnPorcenVentaConsu > 0 AND lbEsPago THEN
                  IF (vsTipoComision = '02' OR vsTipoComision = '01') AND vsBaseComision = '04' THEN
                     lbGenerarReclamo := true;
                     BEGIN
                       SELECT X.COD_TIPO_CLAS, X.COD_CLAS
                       INTO
                           lsCodTipoClasi, lsCodClasi
                       FROM COM_CLASI_CLIEN X,
                            MAE_CLIEN_TIPO_SUBTI Y,
                            MAE_CLIEN_CLASI Z,
                            MAE_TIPO_CLASI_CLIEN W,
                            MAE_CLASI U
                       WHERE X.COD_COMI = vregRegistro.Cod_Comi
                         AND Y.CLIE_OID_CLIE = vregRegistro.OID_CLIE
                         AND Y.OID_CLIE_TIPO_SUBT = Z.CTSU_OID_CLIE_TIPO_SUBT
                         AND Z.TCCL_OID_TIPO_CLASI = W.OID_TIPO_CLAS
                         AND X.COD_TIPO_CLAS = W.COD_TIPO_CLAS
                         AND W.OID_TIPO_CLAS = U.TCCL_OID_TIPO_CLAS
                         AND Z.CLAS_OID_CLAS = U.OID_CLAS
                         AND X.COD_CLAS = U.COD_CLAS;
                     EXCEPTION
                     WHEN NO_DATA_FOUND THEN
                          lbGenerarReclamo := FALSE;
                     END;

                     IF lbGenerarReclamo THEN
                        lnValorDscto :=    ROUND((regRegistroDetalleActual.IMP_NETO_CIMP + regRegistroDetalleActual.Imp_Carg_Frac_Cimp - regRegistroDetalleActual.IMP_RECL_CIMP) * vnPorcenVentaConsu / 100 ,2);
                        IF regRegistroDetalleActual.IMP_PAGO_CIMP + lnSumaPagosHistoricos >= lnValorDscto THEN
                           lnValorDscto := regRegistroDetalleActual.IMP_NETO_CIMP + regRegistroDetalleActual.Imp_Carg_Frac_Cimp - regRegistroDetalleActual.IMP_RECL_CIMP - regRegistroDetalleActual.IMP_PAGO_CIMP - lnSumaPagosHistoricos ;
                           regRegistroDetalleActual.IMP_DCTO_NETO_CIMP :=  lnValorDscto;
                           regRegistroDetalleActual.IMP_DCTO_NETO := ROUND((lnValorDscto /(1 + lnValorIGV / 100)),2);
                        END IF;
                     END IF;
                  END IF;
               END IF;
               
              /*Inicio modificacion RCR COL-SiCC-2015-0159*/
               if vsBaseComision = '07' then
                  regRegistroDetalleActual.IMP_COMI:= ROUND((regRegistroDetalleActual.IMP_NETO_SIN_RECL * regRegistroDetalleActual.VAL_PORC_COMI)/100,2);
               end if;
               /*Fin modificacion RCR COL-SiCC-2015-0159*/

               INSERT INTO COM_REPOR_COMIS_RECUP
               VALUES regRegistroDetalleActual;

             END IF;
         END LOOP;
      END IF;
      EXIT WHEN cImporte%NOTFOUND;
  END LOOP;
  CLOSE cImporte;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_MONTO_COMIS_RECUP: '||ls_sqlerrm);
END COM_PR_MONTO_COMIS_RECUP;



/***************************************************************************
Descripcion       : Funcion auxiliar de Lista de Comisiones x Recuperacion. Genera los
                    importes de acumulados de saldos de zona
Fecha Creacion    : 06/10/2012
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE COM_PR_COMIS_RECUP_ACUMU_ZONA(
  vnIdPais                NUMBER,
  vsCodigoPais            VARCHAR2,
  vnIdPeriodo             NUMBER,
  vnIdComision            NUMBER,
  vnIdRegion              NUMBER,
  vnCodZona               zon_zona.cod_zona%type,
  vregRegistro            IN OUT NOCOPY COM_REPOR_COMIS_RECUP%ROWTYPE
)
IS
 TYPE tRegImporte IS RECORD (
     IMP_PEND               CCC_MOVIM_CUENT_CORRI.IMP_PEND%TYPE,
     FEC_DOCU               DATE
 );

 TYPE tTablaImporte IS TABLE OF tRegImporte;

 regImporte               tRegImporte;
 tablaImporte             tTablaImporte;
 lnValorIGV               NUMBER;
 vnImpuVent               NUMBER;
 lnImporte                number(12,2);

 CURSOR cImporte IS
   SELECT A.IMP_PEND,
          A.FEC_DOCU
   FROM CCC_MOVIM_CUENT_CORRI A
   WHERE A.CLIE_OID_CLIE = vregRegistro.OID_CLIE
     AND A.PERD_OID_PERI = vnIdPeriodo
 --    AND A.ZORG_OID_REGI = vnIdRegion

     AND A.SUBP_OID_SUBP_CREA in (
              SELECT CS.OID_SUBP FROM CCC_PROCE CP, CCC_SUBPR CS, COM_COMIS_SUBPR_PAGO CCSP
              WHERE CP.OID_PROC = CS.CCPR_OID_PROC
              AND CCSP.COD_PROC = CP.COD_PROC
              AND CCSP.COD_SUBP = CS.COD_SUBP
              AND CCSP.COD_COMI = COMISION_RECUPERACION
              AND CCSP.TIP_COMI = TIPO_CARGO
              AND CP.PAIS_OID_PAIS = vnIdPais
           )
     AND A.IMP_MOVI > 0
     AND A.IMP_PEND > 0
   ORDER BY FEC_ULTI_MOVI ;


BEGIN
  /* Recorriendo Lista de Importes */

  OPEN cImporte;
  LOOP
      FETCH cImporte BULK COLLECT INTO tablaImporte LIMIT W_FILAS;
      IF tablaImporte.COUNT > 0 THEN
         FOR x IN tablaImporte.FIRST .. tablaImporte.LAST LOOP
            regImporte    := tablaImporte(x);
            regImporte.FEC_DOCU := trunc(regImporte.FEC_DOCU);

            /* obteniendo valor de impuesto */
            lnValorIGV := 0.0;
            vnImpuVent := COM_FN_DEVUE_TASA_IMPUE(vsCodigoPais, regImporte.FEC_DOCU);
            IF vnImpuVent > 0 THEN
               lnValorIGV := vnImpuVent;
            ELSE
               RAISE_APPLICATION_ERROR(-20123, 'ERROR en BAS_TASA_IMPUE: Debe ingresar Monto de Impuesto para fecha: '||TO_CHAR(regImporte.FEC_DOCU, 'dd/mm/yyyy'));
            END IF;
            lnImporte := ROUND(regImporte.IMP_PEND/(1 + lnValorIGV / 100),2);

            /* Actualizando en COM_GTT_TABLA_REPOR (Tabla temporal) */
            UPDATE COM_GTT_TABLA_REPOS A
            SET A.CAM_NUME = A.CAM_NUME + lnImporte
            WHERE A.CAM_CADE = vnCodZona;
         END LOOP;
      END IF;
      EXIT WHEN cImporte%NOTFOUND;
  END LOOP;
  CLOSE cImporte;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_COMIS_RECUP_ACUMU_ZONA: '||ls_sqlerrm);
END COM_PR_COMIS_RECUP_ACUMU_ZONA;


/***************************************************************************
Descripcion        : Funcion auxiliar de Lista de Comisiones x Recuperacion.
                    Devuelve Fecha Habil, es decir ni sabado ni domingo ni feriado
                    en base a una fecha base
Fecha Creacion     : 27/03/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_FECHA_HABIL (
   pdFecha              DATE,
   psCodZona            VARCHAR2
)
RETURN DATE
IS
  ldFecha    DATE;
  ldFechaAux DATE;
  lbFeriado  BOOLEAN;
  lsNumDiaSemana VARCHAR2(1);
  lnNumDiaSemana NUMBER;
BEGIN
  ldFecha := pdFecha;

  /* Verificando si es Sabado o Domingo */
  BEGIN
    SELECT A.NUM_POSI_SEMA
    INTO lnNumDiaSemana
    FROM BAS_CALEN A
    WHERE
         A.FEC_CALE = ldFecha;
  EXCEPTION
  WHEN no_data_found THEN
    RAISE_APPLICATION_ERROR(-20123, 'Debe previamente generar Calendario para el a¿o respectivo');
  END;
  lsNumDiaSemana := trim(to_char(lnNumDiaSemana));
  --lsNumDiaSemana := TRIM(to_char(ldFecha,'D'));

  IF lsNumDiaSemana = '7' THEN
     ldFecha := ldFecha + 2;
  ELSE
     IF lsNumDiaSemana = '1' THEN
        ldFecha := ldFecha + 1;
     END IF;
  END IF;

  lbFeriado :=  FALSE;
  WHILE TRUE
  LOOP
      /* Verificando si es Feriado global */
      BEGIN
        SELECT A.FEC_CALE
        INTO ldFechaAux
        FROM bas_calen a
        WHERE A.FEC_CALE = ldFecha
          AND A.IND_FERI = 'S';

        ldFecha := ldFecha + 1;
        lbFeriado :=  TRUE;
      EXCEPTION
      WHEN no_data_found THEN
           lbFeriado :=  FALSE;
      END;

      /* Verificando si es Feriado global */
      IF NOT lbFeriado THEN
          BEGIN
            SELECT A.FEC_CALE
            INTO ldFechaAux
            FROM ven_feria_zona a
            WHERE A.FEC_CALE = ldFecha
              AND A.COD_ZONA = psCodZona;

            ldFecha := ldFecha + 1;
            lbFeriado :=  TRUE;
          EXCEPTION
          WHEN no_data_found THEN
               lbFeriado :=  FALSE;
          END ;
       END IF;

       /* Verificando si es Sabado o Domingo */
      IF NOT lbFeriado THEN
          BEGIN
            SELECT A.NUM_POSI_SEMA
            INTO lnNumDiaSemana
            FROM BAS_CALEN A
            WHERE
                 A.FEC_CALE = ldFecha;
          EXCEPTION
          WHEN no_data_found THEN
            RAISE_APPLICATION_ERROR(-20123, 'Debe previamente generar Calendario para el a¿o respectivo');
          END;
          lsNumDiaSemana := trim(to_char(lnNumDiaSemana));
          --lsNumDiaSemana := TRIM(to_char(ldFecha,'D'));

          IF lsNumDiaSemana = '7' THEN
             ldFecha := ldFecha + 2;
             lbFeriado := TRUE;
          ELSE
             IF lsNumDiaSemana = '1' THEN
                ldFecha := ldFecha + 1;
                lbFeriado := TRUE;
             END IF;
          END IF;
      END IF;
      IF NOT lbFeriado THEN
         EXIT;
      END IF;
  END LOOP;
  RETURN ldFecha;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_FECHA_HABIL: '||ls_sqlerrm);
END COM_FN_DEVUE_FECHA_HABIL;

/***************************************************************************
Descripcion        : Funcion auxiliar de Lista de Comisiones x Recuperacion.
                    Devuelve cantidad de dias maximo para una comision
Fecha Creacion     : 16/03/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_NUMER_DIAS (
   pdFecha              DATE,
   pnCantidadDiasMaximo NUMBER,
   psCodZona            VARCHAR2
)
RETURN NUMBER
IS
BEGIN
     RETURN COM_FN_DEVUE_NUMER_DIAS(pdFecha,
            pnCantidadDiasMaximo,
            psCodZona,
            1);
END COM_FN_DEVUE_NUMER_DIAS;

/***************************************************************************
Descripcion        : Funcion auxiliar de Lista de Comisiones x Recuperacion.
                    Devuelve cantidad de dias maximo para una comision
Fecha Creacion     : 16/03/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_NUMER_DIAS (
   pdFecha              DATE,
   pnCantidadDiasMaximo NUMBER,
   psCodZona            VARCHAR2,
   pnFlagWeekend        NUMBER
)
RETURN NUMBER
IS
  ldFecha        DATE;
  lnDiferencia   NUMBER;
  lnCantidadDiasMaximo NUMBER;

BEGIN
  lnDiferencia := 0;
  lnCantidadDiasMaximo := pnCantidadDiasMaximo;
  IF pnCantidadDiasMaximo IS NULL THEN
     lnCantidadDiasMaximo := 0;
  END IF;
  /* Se verifica el flag de fin de semana */
  IF pnFlagWeekend = 1 THEN
     ldFecha := COM_FN_DEVUE_FECHA_HABIL(pdFecha, psCodZona);
  ELSE
     ldFecha := pdFecha;
  END IF;
  IF pdFecha <> ldFecha THEN
     lnDiferencia :=  ldFecha - pdFecha;
  END IF;
  RETURN lnCantidadDiasMaximo + lnDiferencia;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_NUMER_DIAS: '||ls_sqlerrm);
END COM_FN_DEVUE_NUMER_DIAS;


/***************************************************************************
Descripcion        : Devuelve suma de importe de comision correspondiente al
                    Periodo y L¿der de Seccion
Fecha Creacion     : 17/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_COMIS_LIDER_SECCI(
   psCodPeriodo      VARCHAR2,
   psCodLiderSeccion VARCHAR2,
   psComisionIngreso VARCHAR2,
   psComisionRecu   VARCHAR2)
RETURN NUMBER
IS
   lnMontoComision NUMBER;
   lnMontoTempo    NUMBER;
   lnMontoDcto     NUMBER;
BEGIN
   BEGIN
       SELECT NVL(SUM(A.IMP_COMI),0), NVL(SUM(A.IMP_COMI_DCTO),0)
       INTO
            lnMontoComision, lnMontoDcto
       FROM COM_COMIS_PERIO_CALCU A
       WHERE A.PERI_COD_PERI = psCodPeriodo
         AND A.COD_COMI = psComisionIngreso
         AND A.COD_LIDE_SECC = psCodLiderSeccion;
   EXCEPTION
   WHEN no_Data_found THEN
        lnMontoComision := 0;
   END;

   BEGIN
     SELECT NVL(SUM(A.IMP_COMI_TRA1),0) + NVL(SUM(A.IMP_COMI_TRA2),0)
     INTO
          lnMontoTempo
     FROM COM_COMIS_PERIO_CALCU A
     WHERE A.PERI_COD_PERI = psCodPeriodo
       AND A.COD_COMI =  psComisionRecu
       AND A.COD_LIDE_SECC = psCodLiderSeccion;
   EXCEPTION
   WHEN no_Data_found THEN
        lnMontoTempo := 0;
   END;
   lnMontoComision := lnMontoComision + lnMontoTempo - lnMontoDcto;
   IF lnMontoComision IS NULL THEN
      RETURN 0;
   END IF;
   RETURN  lnMontoComision;
EXCEPTION
WHEN no_data_found THEN
   RETURN  0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_COMIS_LIDER_SECCI: '||ls_sqlerrm);
END COM_FN_DEVUE_COMIS_LIDER_SECCI;


/***************************************************************************
Descripcion        : Procedimiemto que genera data para el Calculo de Comision
                    de Recuperacion que ser¿ mostrado en los reportes respectivos.
Parametros        : psTipoComision :
                           'L'  Lider
                           'G'  Gerente de Zona
Fecha Creacion     : 07/03/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_GENER_LISTA_COMIS_COMER(
  psCodPais       VARCHAR2,
  psCodMarca      VARCHAR2,
  psCodCanal      VARCHAR2,
  psCodComision   VARCHAR2,
  psFechaInicial  VARCHAR2,
  psFechaFinal    VARCHAR2,
  psUsuario       VARCHAR2
)
IS
  /* Declaracion de Variables */
  lnIdPais                SEG_PAIS.OID_PAIS%TYPE;
  lnIdCanal               SEG_CANAL.OID_CANA%TYPE;
  lnIdMarca               SEG_MARCA.OID_MARC%TYPE;
  lnIdPeriSiguiente       CCC_PROCE.OID_PROC%TYPE;
  lnIdPeriMas2            CCC_PROCE.OID_PROC%TYPE;

  lnIdProcCreacion        CCC_PROCE.OID_PROC%TYPE;
  lnIdSubprocCreacion     CCC_SUBPR.OID_SUBP%TYPE;
  lsCodPeriSiguiente      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriMas2           SEG_PERIO_CORPO.COD_PERI%TYPE;

  lnIdZona                ZON_ZONA.OID_ZONA%TYPE;
  lnNumDiasComi           COM_COMIS_COBRA_TIPO_PARTI.NUM_DIAS_COMI%TYPE;
  lnValPorceRecu          COM_COMIS_COBRA_TIPO_PARTI.VAL_PORC_RECU%TYPE;
  lnValPorceComi          COM_COMIS_COBRA_TIPO_PARTI.VAL_PORC_COMI%TYPE;

  lsCodLiderZona          MAE_CLIEN.COD_CLIE%TYPE;

  lsCodZona               ZON_ZONA.COD_ZONA%TYPE;
  regComercializa         COM_REPOR_COMIS_COMER%ROWTYPE;

  lsNomLiderZona          VARCHAR2(500);
  lnIdComision            NUMBER;
  ldFechaIniPeriodo       DATE;
  ldFechaFinPeriodo       DATE;
  lnExisteRegion          NUMBER;
  W_FILAS                 NUMBER:=5000;
  lnValor                 NUMBER;
  lsMensaje               VARCHAR2(255);

  regRegistro             tRegClienteComisionComer;
  tablaRegistro           tTablaClienteComisionComer;
  lnX                     NUMBER;
  lnPagina                NUMBER;
  lbInsertarZona          BOOLEAN := TRUE;
  lsCodEmpl               MAE_CLIEN_DATOS_ADICI.Cod_Empl%TYPE;
  lsCodclien              MAE_CLIEN.COD_CLIE%TYPE;
  lsCodPeri               SEG_PERIO_CORPO.COD_PERI%TYPE;

  CURSOR cursorRegistro
  IS
  SELECT DISTINCT
    OID_CLIE,
    COD_CLIE,
    NOM_CLIE,
    OID_REGI,
    COD_REGI,
    OID_ZONA,
    COD_ZONA,
    COD_SUBG_VENT,
    OID_SECC,
    COD_SECC,
    COD_TERR,
    OID_TERR_ADMI,
    PERD_OID_PERI,
    FEC_DOCU,
    FEC_ULTI_MOVI,
    FEC_VENC,
    IMP_PAGO,
    OID_MOVI_CC,
    VAL_TASA_IMPU,
    SUBP_OID_SUBP_ULTI
  FROM
     COM_TEMPO_COMIS_COMER A
  ORDER BY  A.COD_REGI, A.COD_ZONA, A.COD_SECC, A.COD_TERR, A.COD_CLIE ;


BEGIN
  DELETE FROM COM_TEMPO_COMIS_COMER;

  DELETE FROM COM_COMIS_PERIO_CALCU_COMER A
  WHERE A.FEC_INIC = to_date(psFechaInicial, 'DD/MM/YYYY')
    AND A.FEC_FINA = to_date(psFechaFinal, 'DD/MM/YYYY')
    AND A.COD_COMI = psCodComision;

  /* Borrando tabla temporal de Comision por Recuperacion */
  DELETE FROM COM_REPOR_COMIS_COMER A
  WHERE A.FEC_INIC = to_date(psFechaInicial, 'DD/MM/YYYY')
    AND A.FEC_FINA = to_date(psFechaFinal, 'DD/MM/YYYY')
    AND A.COD_COMI = psCodComision;

  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => USER, TABNAME => 'COM_REPOR_COMIS_COMER', CASCADE => TRUE );
  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => USER, TABNAME => 'COM_COMIS_PERIO_CALCU_COMER', CASCADE => TRUE );
  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => USER, TABNAME => 'COM_TEMPO_COMIS_COMER', CASCADE => TRUE );

  /* Obteniendo oids */
  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lnIdComision   := COM_FN_DEVUE_ULTI_OID_COMI(psCodComision);

  /* obtenemos id subproceso banco*/
  DELETE FROM COM_GTT_TABLA_REPOS;
  INSERT INTO COM_GTT_TABLA_REPOS(CAM_NUME, CAM_CADE)
  SELECT C.OID_SUBP, D.TIP_COMI
  FROM  SEG_PAIS  A,
        CCC_PROCE B,
        CCC_SUBPR C,
        COM_COMIS_SUBPR_PAGO D
  WHERE A.OID_PAIS = lnIdPais
    AND B.PAIS_OID_PAIS = A.OID_PAIS
    AND B.OID_PROC = C.CCPR_OID_PROC
    AND D.COD_PAIS = psCodPais
    AND D.COD_COMI = COMISION_COMERCIALIZACION
    AND B.COD_PROC = D.COD_PROC
    AND C.COD_SUBP = D.COD_SUBP;

  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => USER, TABNAME => 'COM_GTT_TABLA_REPOS', CASCADE => TRUE );

  /* obteniendo cantidad de dias, % de recuperacion y % de comision */
  BEGIN
    SELECT B.NUM_DIAS_COMI, B.VAL_PORC_RECU, B.VAL_PORC_COMI
    INTO lnNumDiasComi, lnValPorceRecu, lnValPorceComi
    FROM
       COM_COMIS_COBRA A,
       COM_COMIS_COBRA_TIPO_PARTI B
    WHERE  A.COMI_OID_COMI = lnIdComision
       AND A.OID_COMI_COBR = B.COCO_OID_COMI_COBR
       AND rownum=1;
  EXCEPTION
  WHEN no_data_found THEN
       RAISE_APPLICATION_ERROR(-20123, 'No se encontr¿ % de Recuperaci¿n ni % de Comisi¿n correspondiente al Tramo 1, para la Comisi¿n: '|| psCodComision);
  END;

  /* obtenemos id subproceso creacion */
  lnIdProcCreacion  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PROCESO(lnIdPais, 'CCC001');
  BEGIN
    SELECT a.oid_subp
    INTO lnIdSubprocCreacion
    FROM CCC_SUBPR A
    WHERE
       a.ccpr_oid_proc = lnIdProcCreacion AND
       a.cod_subp = 1;
  EXCEPTION
  WHEN no_Data_found THEN
       RAISE_APPLICATION_ERROR(-20123, 'No se encontr¿ el ID del Subproceso de Creacion' );
  END;


  /* Verificando que la Comision posea Regiones */
  SELECT COUNT(1)
  INTO lnExisteRegion
  FROM
     COM_COMIS A,
     COM_COMIS_CLIEN B
  WHERE  A.OID_COMI = lnIdComision
     AND A.OID_COMI = B.COMI_OID_COMI
     AND B.ZORG_OID_REGI IS NOT NULL;

  IF lnExisteRegion > 0 THEN
      INSERT INTO COM_GTT_COMIS_COMER(
            OID_CLIE, COD_CLIE, NOM_CLIE,
            OID_REGI, COD_REGI, OID_ZONA, COD_ZONA,
            COD_SUBG_VENT, OID_SECC, COD_SECC,
            COD_TERR, OID_TERR_ADMI, PERD_OID_PERI, FEC_DOCU, FEC_ULTI_MOVI, FEC_VENC,
            IMP_PAGO, OID_MOVI_CC, VAL_TASA_IMPU, SUBP_OID_SUBP_ULTI)
        SELECT DISTINCT
          M.CLIE_OID_CLIE,
          NULL,
          NULL,
          B.ZORG_OID_REGI,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          M.ZTAD_OID_TERR_ADMI AS OID_TERR_ADMI,
          M.PERD_OID_PERI,
          M.FEC_DOCU,
          M.FEC_ULTI_MOVI,
          M.FEC_VENC,
          M.IMP_PAGO,
          M.OID_MOVI_CC,
          0.00,
          M.SUBP_OID_SUBP_ULTI
        FROM
           COM_COMIS A,
           COM_COMIS_CLIEN B,
           CCC_MOVIM_CUENT_CORRI M

        WHERE  A.OID_COMI = lnIdComision
           AND A.OID_COMI = B.COMI_OID_COMI
           AND M.FEC_ULTI_MOVI >= TO_date(psFechaInicial,'dd/mm/yyyy')
           AND M.SUBP_OID_SUBP_CREA = lnIdSubprocCreacion

           AND M.IMP_MOVI > 0
           AND M.ZORG_OID_REGI = B.ZORG_OID_REGI

           AND EXISTS (SELECT x.oid_hist_movi
                       FROM CCC_HISTO_MOVIM_CC X,
                            COM_GTT_TABLA_REPOS Y
                       WHERE X.MVCC_OID_MOVI_CC = M.OID_MOVI_CC
                         AND X.SUBP_OID_SUBP = Y.CAM_NUME
                         AND X.IMP_MOVI > 0
                         AND X.IMP_MOVI <> X.IMP_PEN
                         AND X.FEC_MOVI >= TO_date(psFechaInicial,'dd/mm/yyyy')
                         AND X.FEC_MOVI < TO_date(psFechaFinal,'dd/mm/yyyy') + 1);

       INSERT INTO COM_GTT_COMIS_COMER(
            OID_CLIE, COD_CLIE, NOM_CLIE,
            OID_REGI, COD_REGI, OID_ZONA, COD_ZONA,
            COD_SUBG_VENT, OID_SECC, COD_SECC,
            COD_TERR, OID_TERR_ADMI, PERD_OID_PERI, FEC_DOCU, FEC_ULTI_MOVI, FEC_VENC,
            IMP_PAGO, OID_MOVI_CC, VAL_TASA_IMPU, SUBP_OID_SUBP_ULTI)
        SELECT DISTINCT
          M.CLIE_OID_CLIE,
          NULL,
          NULL,
          B.ZORG_OID_REGI,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          NULL,
          M.ZTAD_OID_TERR_ADMI AS OID_TERR_ADMI,
          M.PERD_OID_PERI,
          M.FEC_DOCU,
          M.FEC_ULTI_MOVI,
          M.FEC_VENC,
          M.IMP_PAGO,
          M.OID_MOVI_CC,
          0.00,
          M.SUBP_OID_SUBP_ULTI
        FROM
           COM_COMIS A,
           COM_COMIS_CLIEN B,
           CCC_MOVIM_CUENT_CORRI M,
           COM_GTT_TABLA_REPOS Y

        WHERE  A.OID_COMI = lnIdComision
           AND A.OID_COMI = B.COMI_OID_COMI
           AND M.FEC_ULTI_MOVI >= TO_date(psFechaInicial,'dd/mm/yyyy')
           AND M.FEC_ULTI_MOVI <  TO_date(psFechaFinal,'dd/mm/yyyy') + 1

           AND M.SUBP_OID_SUBP_ULTI = Y.CAM_NUME
           AND M.SUBP_OID_SUBP_CREA = lnIdSubprocCreacion

           AND M.IMP_MOVI > 0
           AND M.ZORG_OID_REGI = B.ZORG_OID_REGI;

  END IF;

  INSERT INTO COM_TEMPO_COMIS_COMER(
            OID_CLIE, COD_CLIE, NOM_CLIE,
            OID_REGI, COD_REGI, OID_ZONA, COD_ZONA,
            COD_SUBG_VENT, OID_SECC, COD_SECC,
            COD_TERR, OID_TERR_ADMI, PERD_OID_PERI, FEC_DOCU, FEC_ULTI_MOVI, FEC_VENC,
            IMP_PAGO, OID_MOVI_CC, VAL_TASA_IMPU, SUBP_OID_SUBP_ULTI)
        SELECT
          C.OID_CLIE,
          C.COD_CLIE,
          TRIM(NVL(C.VAL_APE1,' ')) || ' ' ||
          TRIM(NVL(C.VAL_APE2,' ')) || ' ' ||
          TRIM(NVL(C.VAL_NOM1,' ')) AS NOM_CLIE,
          I.OID_REGI,
          I.COD_REGI,
          H.OID_ZONA,
          H.COD_ZONA,
          L.COD_SUBG_VENT,
          G.OID_SECC,
          G.COD_SECC,
          F.COD_TERR,
          M.OID_TERR_ADMI AS OID_TERR_ADMI,
          M.PERD_OID_PERI,
          M.FEC_DOCU,
          M.FEC_ULTI_MOVI,
          M.FEC_VENC,
          M.IMP_PAGO,
          M.OID_MOVI_CC,
          0.00,
          M.SUBP_OID_SUBP_ULTI
        FROM
           COM_GTT_COMIS_COMER M,
           MAE_CLIEN C,
           ZON_TERRI_ADMIN E,
           ZON_TERRI F,
           ZON_SECCI G,
           ZON_ZONA  H,
           ZON_REGIO I,
           ZON_SUB_GEREN_VENTA L
        WHERE  I.OID_REGI = M.OID_REGI
           AND C.OID_CLIE = M.OID_CLIE
           AND E.OID_TERR_ADMI = M.OID_TERR_ADMI
           AND F.OID_TERR = E.TERR_OID_TERR
           AND G.OID_SECC = E.ZSCC_OID_SECC
           AND H.OID_ZONA = G.ZZON_OID_ZONA
           AND I.OID_REGI = H.ZORG_OID_REGI
           AND L.OID_SUBG_VENT = I.ZSGV_OID_SUBG_VENT;

  --SELECT COUNT(*) INTO lnPagina FROM  COM_TEMPO_COMIS_COMER;

  /* Recorriendo Lista de Clientes */
  lsNomLiderZona    := '';
  OPEN cursorRegistro;
  lnPagina := 0;
  LOOP
      FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
      lnPagina := lnPagina + 1;
      IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
            regRegistro := tablaRegistro(x);

            /* obteniendo codigo de periodo */
            SELECT a.cod_peri
            INTO lsCodPeri
            FROM
               seg_perio_corpo a,
               cra_perio b
            WHERE b.oid_peri = regRegistro.PERD_OID_PERI
              AND a.oid_peri = b.peri_oid_peri ;

            /* obteniendo id periodo anterior */
            lsCodPeriSiguiente := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodPeri, lnIdPais, lnIdMarca, lnIdCanal, 1);
            lnIdPeriSiguiente  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriSiguiente, lnIdMarca, lnIdCanal);
            lsCodPeriMas2 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodPeri, lnIdPais, lnIdMarca, lnIdCanal, 2);
            lnIdPeriMas2  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriMas2, lnIdMarca, lnIdCanal);

            /* obteniendo porcentaje IGV */
            regComercializa.Val_Tasa_Impu := COM_FN_DEVUE_TASA_IMPUE(psCodPais, regRegistro.FEC_DOCU);

             regComercializa.Fec_Inic := TO_DATE(psFechaInicial,'DD/MM/YYYY');
             regComercializa.Fec_Fina := TO_DATE(psFechaFinal,'DD/MM/YYYY');
             regComercializa.Cod_Comi := psCodComision;
             regComercializa.Oid_Clie := regRegistro.OID_CLIE;
             regComercializa.Cod_Clie := regRegistro.COD_CLIE;
             regComercializa.Nom_Clie := regRegistro.NOM_CLIE;
             regComercializa.Cod_Regi := regRegistro.COD_REGI;
             regComercializa.Oid_Zona := regRegistro.OID_ZONA;
             regComercializa.Cod_Zona := regRegistro.COD_ZONA;
             regComercializa.Cod_Subg_Vent := regRegistro.COD_SUBG_VENT;
             regComercializa.Oid_Secc := regRegistro.OID_SECC;
             regComercializa.Cod_Secc := regRegistro.COD_SECC;
             regComercializa.Cod_Terr := regRegistro.COD_TERR;
             regComercializa.Imp_Pago := regRegistro.IMP_PAGO;
             regComercializa.Oid_Movi_Cc := regRegistro.OID_MOVI_CC;
             regComercializa.Fec_Ulti_Movi := regRegistro.FEC_ULTI_MOVI;
             regComercializa.Subp_Oid_Subp_Ulti := regRegistro.SUBP_OID_SUBP_ULTI;


             /* ENCONTRANDO LIDER DE ZONA */
                lbInsertarZona := TRUE;
                lnIdZona  := regComercializa.OID_ZONA;
                lsCodZona := regComercializa.COD_ZONA;
                lsNomLiderZona := COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                  lsCodLiderZona,
                                  lsCodPeri,
                                  lnIdPais, regComercializa.COD_SUBG_VENT,
                                  regComercializa.COD_REGI,
                                  regComercializa.COD_ZONA,
                                  NULL);

                /* verificando que posea lider de zona */
                IF lsCodLiderZona IS NULL THEN
                   lbInsertarZona := FALSE;
                END IF;

                IF lbInsertarZona THEN
                    /* Obteniendo tipo de comision */
                    regComercializa.TIP_COMI := 'G';
                    lsCodEmpl := NULL;
                    BEGIN
                       SELECT A.COD_EMPL
                       INTO lsCodEmpl
                       FROM
                          MAE_CLIEN_DATOS_ADICI A,
                          MAE_CLIEN B
                       WHERE B.COD_CLIE =  lsCodLiderZona
                         AND B.PAIS_OID_PAIS = lnIdPais
                         AND A.CLIE_OID_CLIE = B.OID_CLIE;

                       IF lsCodEmpl IS NULL THEN
                          regComercializa.TIP_COMI := 'P';
                       END IF;
                    EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                         regComercializa.TIP_COMI := 'P';
                    END;
                    regComercializa.cod_empl := lsCodEmpl;

                    /* Validando en caso sea Gte de Zona */
                    IF regComercializa.TIP_COMI = 'G' THEN
                       BEGIN
                         SELECT A.COD_CLIE
                         INTO lsCodclien
                         FROM
                             MAE_CLIEN A
                          WHERE A.COD_CLIE =  lsCodLiderZona
                            AND A.PAIS_OID_PAIS = lnIdPais
                            AND A.FEC_INGR < TO_DATE('01/07/2001','DD/MM/YYYY');
                       EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                            lbInsertarZona := FALSE;
                       END;
                    END IF;
                END IF;

               IF lbInsertarZona THEN
                   regComercializa.Cod_Lide_Zona := lsCodLiderZona;
                   regComercializa.NOM_LIDE_ZONA := lsNomLiderZona;
                   regComercializa.Imp_Pago := COM_FN_MONTO_COMIS_COMER(
                                            regComercializa,
                                            lnIdPais,
                                            psCodPais,
                                            lnNumDiasComi,
                                            regRegistro.FEC_DOCU,
                                            regRegistro.FEC_ULTI_MOVI,
                                            regRegistro.FEC_VENC,
                                            psFechaInicial,
                                            psFechaFinal,
                                            lnIdPeriSiguiente,
                                            lnIdPeriMas2,
                                            regRegistro.OID_REGI);
                   IF regComercializa.Imp_Pago > 0 THEN
                       regComercializa.Imp_Comi := regComercializa.Imp_Pago * lnValPorceComi / 100;

                       INSERT INTO COM_REPOR_COMIS_COMER
                       VALUES regComercializa;
                   END IF;
               END IF;
         END LOOP;
      END IF;
     EXIT WHEN cursorRegistro%NOTFOUND;
  END LOOP;
  CLOSE cursorRegistro;

  INSERT INTO COM_COMIS_PERIO_CALCU_COMER
  (FEC_INIC, FEC_FINA, COD_COMI,
   COD_REGI, COD_ZONA, OID_ZONA,
   COD_SUBG_VENT, COD_LIDE_ZONA, NOM_LIDE_ZONA,
   IMP_PAGO, IMP_COMI, TIP_COMI, COD_EMPL)
  SELECT
        A.FEC_INIC, A.FEC_FINA, A.COD_COMI,
        A.COD_REGI, A.COD_ZONA, A.OID_ZONA,
        A.COD_SUBG_VENT, A.COD_LIDE_ZONA, A.NOM_LIDE_ZONA,
        SUM(A.IMP_PAGO), SUM(A.IMP_COMI), TIP_COMI, COD_EMPL
  FROM
      COM_REPOR_COMIS_COMER A
  WHERE A.FEC_INIC = TO_DATE(psFechaInicial,'DD/MM/YYYY')
    AND A.FEC_FINA = TO_DATE(psFechaFinal,'DD/MM/YYYY')
    AND A.COD_COMI = psCodComision
    AND A.COD_LIDE_ZONA IS NOT NULL
  GROUP BY
      A.FEC_INIC, A.FEC_FINA, A.COD_COMI,
      A.COD_REGI, A.COD_ZONA, A.OID_ZONA,
      A.COD_SUBG_VENT, A.COD_LIDE_ZONA, A.NOM_LIDE_ZONA, TIP_COMI, COD_EMPL;

  RETURN ;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, lnPagina||' '||lnX||' '||'ERROR COM_PR_GENER_LISTA_COMIS_COMER: '||ls_sqlerrm);
END COM_PR_GENER_LISTA_COMIS_COMER;

/***************************************************************************
Descripcion        : Funcion auxiliar de Lista de comisiones. Genera los
                    importes de pago de acuerdo al cliente y periodo ingresados
Fecha Creacion     : 10/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_MONTO_COMIS_COMER(
  vnRegComercializa    com_repor_comis_comer%ROWTYPE,
  vnIdPais             NUMBER,
  vsCodPais            VARCHAR2,
  vnNumDiasComi        NUMBER,
  vdFechaDocu          DATE,
  vdFechaUltiMovi      DATE,
  vdFechaVencimiento   DATE,
  vsFechaIniProceso    VARCHAR2,
  vsFechaFinProceso    VARCHAR2,
  vnIdPeriodoSiguiente NUMBER,
  vnIdPeriodoMas2      NUMBER,
  vnIdRegion           NUMBER
)
RETURN NUMBER
IS
BEGIN
   RETURN COM_FN_MONTO_COMIS_COMER(vnRegComercializa,
   vnIdPais ,
   vsCodPais,
   vnNumDiasComi,
   vdFechaDocu,
   vdFechaUltiMovi,
   vdFechaVencimiento,
   vsFechaIniProceso,
   vsFechaFinProceso,
   vnIdPeriodoSiguiente,
   vnIdPeriodoMas2,
   vnIdRegion,
   1);
END COM_FN_MONTO_COMIS_COMER;

/***************************************************************************
Descripcion        : Funcion auxiliar de Lista de comisiones. Genera los
                    importes de pago de acuerdo al cliente y periodo ingresados
Fecha Creacion     : 10/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_MONTO_COMIS_COMER(
  vnRegComercializa    com_repor_comis_comer%ROWTYPE,
  vnIdPais             NUMBER,
  vsCodPais            VARCHAR2,
  vnNumDiasComi        NUMBER,
  vdFechaDocu          DATE,
  vdFechaUltiMovi      DATE,
  vdFechaVencimiento   DATE,
  vsFechaIniProceso    VARCHAR2,
  vsFechaFinProceso    VARCHAR2,
  vnIdPeriodoSiguiente NUMBER,
  vnIdPeriodoMas2      NUMBER,
  vnIdRegion           NUMBER,
  vnFlagWeekend        NUMBER
)
RETURN NUMBER
IS
 TYPE tRegImporte IS RECORD (
     IMP_MOVI               CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE,
     IMP_PAGO               CCC_MOVIM_CUENT_CORRI.IMP_PAGO%TYPE,
     FEC_ULTI_MOVI          DATE,
     FEC_DOCU               DATE,
     SUBP_OID_SUBP          CCC_HISTO_MOVIM_CC.SUBP_OID_SUBP%TYPE
 );

 TYPE tTablaImporte IS TABLE OF tRegImporte;

 regImporteHisto          tRegImporte;
 tablaImporteHisto        tTablaImporte;
 lnSumaImporte            NUMBER;
 LnNum_Dias_Limi          NUMBER;
 LnNum_Dias_Dife          NUMBER;
 ldFechaLimite            DATE;
 lbInsertar               BOOLEAN;
 lbVerificarDiferencia    BOOLEAN;
 lncontador               NUMBER;
 ldFechaDocu              DATE;
 ldFechaUltiMovi          DATE;
 ldFechaVenci             DATE;
 lbDemandaAnticipada      BOOLEAN;
 lbContinuar              BOOLEAN;
 ldFechaInicio            DATE;
 lnNumDiasDemandaTramo1   NUMBER;
 lnNumDiasDemandaTramo2   NUMBER;
 lnNumDiasComi            NUMBER;



 CURSOR cImporteHisto  IS
   SELECT A.IMP_MOVI,
          A.IMP_PAGO,
          A.FEC_MOVI,
          A.FEC_DOCU,
          A.SUBP_OID_SUBP
   FROM CCC_HISTO_MOVIM_CC A
   WHERE A.MVCC_OID_MOVI_CC = vnRegComercializa.Oid_Movi_Cc
     AND A.IMP_MOVI > 0
     AND A.IMP_MOVI <> A.IMP_PEN
     AND A.FEC_MOVI >= TO_date(vsFechaIniProceso,'dd/mm/yyyy')
     AND A.FEC_MOVI < TO_date(vsFechaFinProceso,'dd/mm/yyyy') + 1
   ORDER BY A.FEC_MOVI  ;


BEGIN
  lnSumaImporte := 0;
  lbInsertar := FALSE;
  lbContinuar := TRUE;
  lnNumDiasComi := vnNumDiasComi;

  /* Verificando Demanda Anticipada */
  lbDemandaAnticipada := TRUE;
  BEGIN
     SELECT A.NUM_DIAS_TRA1, A.NUM_DIAS_TRA2
     INTO
        lnNumDiasDemandaTramo1, lnNumDiasDemandaTramo2
     FROM COM_ZONA_DEMAN_ANTIC A
     WHERE A.COD_PAIS = vsCodPais
       AND A.COD_ZONA = vnRegComercializa.COD_ZONA;
    lnNumDiasComi := lnNumDiasDemandaTramo1;
  EXCEPTION
  WHEN no_data_found THEN
       lbDemandaAnticipada := FALSE;
  END;

  ldFechaDocu := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(vdFechaDocu);
  ldFechaUltiMovi := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(vdFechaUltiMovi);
  ldFechaVenci := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(vdFechaVencimiento);

  IF lbDemandaAnticipada THEN
      -- Obteniendo fecha de inicio de CRA_CRONO
      ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodoMas2, vnRegComercializa.Oid_Zona, 'FA');
      IF ldFechaInicio IS NULL THEN
         lbContinuar := FALSE;
      END IF;
      IF lbContinuar THEN
         IF ldFechaVenci > ldFechaInicio THEN
            ldFechaDocu := ldFechaInicio;
         END IF;
      END IF;

  ELSE
     ldFechaInicio := COM_FN_MAXIM_FINIC_CRONO (vnIdPais, vnIdPeriodoSiguiente, vnRegComercializa.Oid_Zona, 'FA');
     IF ldFechaInicio IS NULL THEN
        lbContinuar := FALSE;
     END IF;
     IF lbContinuar THEN
         IF ldFechaVenci > ldFechaInicio THEN
            ldFechaDocu := ldFechaInicio;
         END IF;
  END IF;

  END IF;

  IF lbContinuar THEN
      ldFechaLimite := ldFechaDocu + lnNumDiasComi;
      lnNum_Dias_Limi := COM_FN_DEVUE_NUMER_DIAS(ldFechaLimite, lnNumDiasComi, vnRegComercializa.Cod_Zona, vnFlagWeekend);
      lnNum_Dias_Dife := ldFechaUltiMovi - ldFechaDocu;
      IF lnNum_Dias_Dife <= lnNum_Dias_Limi THEN
            lbInsertar := TRUE;
      END IF;
  END IF;

  IF lbInsertar THEN
      IF (vnRegComercializa.Fec_Ulti_Movi >= to_date(vsFechaIniProceso,'dd/mm/yyyy')) AND (vnRegComercializa.Fec_Ulti_Movi < to_date(vsFechaFinProceso,'dd/mm/yyyy') + 1) THEN
         lbVerificarDiferencia := TRUE;
         BEGIN
            SELECT A.CAM_NUME
            INTO lnContador
            FROM COM_GTT_TABLA_REPOS A
            WHERE A.CAM_NUME = vnRegComercializa.SUBP_OID_SUBP_ULTI;
         EXCEPTION
         WHEN NO_DATA_FOUND THEN
               lbVerificarDiferencia := FALSE;
         END;
         IF (lbVerificarDiferencia) THEN
            lnSumaImporte := ROUND(vnRegComercializa.Imp_Pago/(1 + vnRegComercializa.Val_Tasa_Impu/100),2);
         END IF;
      END IF;
  END IF;

      /* Recorriendo Lista de Importes (Historicos) */
      OPEN cImporteHisto;
      LOOP
          FETCH cImporteHisto BULK COLLECT INTO tablaImporteHisto LIMIT W_FILAS;
          IF tablaImporteHisto.COUNT > 0 THEN
             FOR y IN tablaImporteHisto.FIRST .. tablaImporteHisto.LAST LOOP
                 regImporteHisto := tablaImporteHisto(y);

                 /* verificando diferencia de dias */
                 regImporteHisto.FEC_DOCU := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(regImporteHisto.FEC_DOCU);
                 regImporteHisto.FEC_ULTI_MOVI := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(regImporteHisto.FEC_ULTI_MOVI);

                 ldFechaLimite    := regImporteHisto.FEC_DOCU + lnNumDiasComi;
                 LnNum_Dias_Limi := COM_FN_DEVUE_NUMER_DIAS(ldFechaLimite, lnNumDiasComi, vnRegComercializa.Cod_Zona);
                 LnNum_Dias_Dife := regImporteHisto.FEC_ULTI_MOVI - regImporteHisto.FEC_DOCU;

                 IF LnNum_Dias_Dife <= LnNum_Dias_Limi THEN
                    lbVerificarDiferencia := TRUE;
                    BEGIN
                        SELECT A.CAM_NUME
                        INTO lnContador
                        FROM COM_GTT_TABLA_REPOS A
                        WHERE A.CAM_NUME = regImporteHisto.SUBP_OID_SUBP;
                    EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                         lbVerificarDiferencia := FALSE;
                    END;
                    IF (lbVerificarDiferencia) THEN
                        lnSumaImporte := lnSumaImporte + ROUND(regImporteHisto.Imp_Pago/(1 + vnRegComercializa.Val_Tasa_Impu/100),2);
                    END IF;
                 END IF;
             END LOOP;
          END IF;
          EXIT WHEN cImporteHisto%NOTFOUND;
      END LOOP;
      CLOSE cImporteHisto;
  RETURN lnSumaImporte;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_MONTO_COMIS_COMER: '||ls_sqlerrm);
END COM_FN_MONTO_COMIS_COMER;

/***************************************************************************
Descripcion        : Procedimiento que devuelve % y Numero de Dias correspondiente
                    al Tramo 1 y Tramo 2 de una comision ingresada como parametro
Fecha Creacion     : 14/05/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_DEVUE_PORCEN_COMIS(
  psCodComision   VARCHAR2,
  pnNumDiasComi   OUT VARCHAR2,
  pnValPorceRecu  OUT VARCHAR2,
  pnValPorceComi  OUT VARCHAR2,
  pnNumDiasComi2  OUT VARCHAR2,
  pnValPorceRecu2 OUT VARCHAR2,
  pnValPorceComi2 OUT VARCHAR2)
IS
  lnIdComision NUMBER;
BEGIN
  lnIdComision   := COM_FN_DEVUE_ULTI_OID_COMI(psCodComision);

  -- obteniendo cantidad de dias, % de recuperacion y % de comision TRAMO 1
  BEGIN
    SELECT B.NUM_DIAS_COMI, B.VAL_PORC_RECU, B.VAL_PORC_COMI
    INTO pnNumDiasComi, pnValPorceRecu, pnValPorceComi
    FROM
       COM_COMIS_COBRA A,
       COM_COMIS_COBRA_TIPO_PARTI B
    WHERE  A.COMI_OID_COMI = lnIdComision
       AND A.OID_COMI_COBR = B.COCO_OID_COMI_COBR
       AND B.VAL_NIVE_TRAM = 1;
  EXCEPTION
  WHEN no_data_found THEN
       pnNumDiasComi2  := 0;
       pnValPorceRecu2 := 0;
       pnValPorceComi2 := 0;
  END;

  -- obteniendo cantidad de dias, % de recuperacion y % de comision TRAMO 2
  BEGIN
    SELECT B.NUM_DIAS_COMI, B.VAL_PORC_RECU, B.VAL_PORC_COMI
    INTO pnNumDiasComi2, pnValPorceRecu2, pnValPorceComi2
    FROM
       COM_COMIS_COBRA A,
       COM_COMIS_COBRA_TIPO_PARTI B
    WHERE  A.COMI_OID_COMI = lnIdComision
       AND A.OID_COMI_COBR = B.COCO_OID_COMI_COBR
       AND B.VAL_NIVE_TRAM = 2;
  EXCEPTION
  WHEN no_data_found THEN
       pnNumDiasComi2  := 0;
       pnValPorceRecu2 := 0;
       pnValPorceComi2 := 0;
  END;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_DEVUE_PORCEN_COMIS: '||ls_sqlerrm);
END COM_PR_DEVUE_PORCEN_COMIS;


/***************************************************************************
Descripcion        : Funcion que devuelve el nro de documento para  Lideres
                    Nuevas
Fecha Creacion     : 16/11/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DOCUM_IDENT_LIDER_NUEV(
  pnIdCliente NUMBER,
  pnIdPais    NUMBER,
  psCodTipoDocum VARCHAR2
  )
RETURN VARCHAR2
IS
  lnIdTipo   NUMBER;
  lsNumdocum MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE;
  lsTemporal MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE;
  lbError    BOOLEAN;
BEGIN

  /* obteniendo id del tipo de documento */
  SELECT X.OID_TIPO_DOCU
  INTO  lnIdTipo
  FROM MAE_TIPO_DOCUM X
  WHERE X.PAIS_OID_PAIS = pnIdPais
    AND X.COD_TIPO_DOCU = psCodTipoDocum;

  /* Obteniendo nro de dni */
  BEGIN
    SELECT A.NUM_DOCU_IDEN
    INTO lsNumdocum
    FROM
       MAE_CLIEN_IDENT A
    WHERE A.CLIE_OID_CLIE = pnIdCliente
      AND A.TDOC_OID_TIPO_DOCU = lnIdTipo;
  EXCEPTION
  WHEN no_data_found THEN
       BEGIN
         SELECT A.NUM_DOCU_IDEN
         INTO lsNumdocum
         FROM
            MAE_CLIEN_IDENT A
         WHERE A.CLIE_OID_CLIE = pnIdCliente
           AND A.VAL_IDEN_DOCU_PRIN = 1;
       EXCEPTION
       WHEN no_data_found THEN
            lsNumdocum := '';
       END;
  END;
  IF lsNumdocum IS NOT NULL THEN
     BEGIN
        lsTemporal := SUBSTR(lsNumdocum,1,10);
        lsNumdocum := lsTemporal;
     EXCEPTION
     WHEN OTHERS THEN
          lbError := TRUE;
     END;
  END IF;
  RETURN lsNumdocum;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DOCUM_IDENT_LIDER_NUEV: '||ls_sqlerrm);
END COM_FN_DOCUM_IDENT_LIDER_NUEV;

/***************************************************************************
Descripcion        : Funcion que devuelve el nivel de la responsable

Fecha Creacion     : 31/07/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
FUNCTION COM_FN_DEVUE_PROCE_TUTOR(psCodPais  VARCHAR2,
                            psCodMarca VARCHAR2,
                            psCodCanal VARCHAR2,
                            psCodEjec  VARCHAR2,
                            psAnioIniTramo VARCHAR2,
                            psCodTramo     VARCHAR2,
                            psTipoComision VARCHAR2,
                            psCodTipoCalculo VARCHAR2
                            ) RETURN BOOLEAN IS


lv_codNivel         COM_HISTO_VARIA_EJETR_CABEC.COD_NIVE%TYPE;
psCodTramoAn        COM_TRAMO_EJECU.Cod_Rang%TYPE;
psAnioIniTramAnt    NUMBER;


BEGIN


    IF (psTipoComision='02')THEN
       RETURN TRUE;
    END IF;

    IF (psTipoComision='01')THEN
       RETURN TRUE;
    END IF;

    IF (psTipoComision='03' OR psTipoComision IS NULL)THEN
            IF psCodEjec IS NULL THEN
                RETURN FALSE;
            END IF;
            IF(psCodTipoCalculo=PARAM_TODAS)THEN
               RETURN TRUE;
            END IF;

            IF(psCodTipoCalculo=PARAM_TUTORAS) THEN
                     SELECT A.COD_NIVE
                      INTO lv_codNivel
                      FROM COM_HISTO_VARIA_EJETR_CABEC A
                     WHERE A.COD_PAIS = psCodPais
                       AND A.COD_MARC = psCodMarca
                       AND A.COD_CANA = psCodCanal
                       AND A.COD_EJEC = psCodEjec
                       AND A.NUM_ANIO_INIC = psAnioIniTramo
                       AND A.COD_RANG = psCodTramo;

                     IF(lv_codNivel=NIVEL_TUTORA)THEN
                         RETURN TRUE;
                     ELSE
                         RETURN FALSE;
                     END IF;
              END IF;

            IF(psCodTipoCalculo=PARAM_EJECUTIVAS)THEN
                               SELECT A.COD_NIVE
                                INTO lv_codNivel
                                FROM COM_HISTO_VARIA_EJETR_CABEC A
                               WHERE A.COD_PAIS = psCodPais
                                 AND A.COD_MARC = psCodMarca
                                 AND A.COD_CANA = psCodCanal
                                 AND A.COD_EJEC = psCodEjec
                                 AND A.NUM_ANIO_INIC = psAnioIniTramo
                                 AND A.COD_RANG = psCodTramo;

                       IF(lv_codNivel=NIVEL_EJECUTIVA_JUNIOR
                          OR lv_codNivel=NIVEL_EJECUTIVA_SENIOR
                          OR lv_codNivel=NIVEL_EJECUTIVA_MASTER
                          OR lv_codNivel=NIVEL_SIN_NIVEL) THEN

                          IF(psCodTramo = '01')THEN
                               RETURN TRUE;
                          END IF;

                          IF(psCodTramo = '02' OR psCodTramo = '03')THEN
                               COM_PKG_PROCE.COM_PR_DEVUE_TRAMO_ANTER(psCodPais,Pscodtramo,psAnioIniTramo,psCodTramoAn,psAnioIniTramAnt);

                               SELECT A.COD_NIVE
                                 INTO lv_codNivel
                                 FROM COM_HISTO_VARIA_EJETR_CABEC A
                                WHERE A.COD_PAIS = psCodPais
                                  AND A.COD_MARC = psCodMarca
                                  AND A.COD_CANA = psCodCanal
                                  AND A.COD_EJEC = psCodEjec
                                  AND A.NUM_ANIO_INIC =  psAnioIniTramAnt
                                  AND A.COD_RANG =  psCodTramoAn;

                                 IF(lv_codNivel=NIVEL_TUTORA)THEN
                                   RETURN TRUE;
                               ELSE
                                   RETURN FALSE;
                               END IF;
                          END IF;
                          ELSE
                          RETURN FALSE;
                          END IF;
              END IF;

     END IF;

EXCEPTION
WHEN no_data_found THEN
     RETURN FALSE;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_PROCE_TUTURA: '||ls_sqlerrm||' - '||psCodEjec);

END COM_FN_DEVUE_PROCE_TUTOR;


/***************************************************************************
Descripcion        : Funcion que verifica que exixte registros en la tabla
                    COM_PARAM_ZONAS_NUEVA, y que la Zona sea nueva.
                    Si la Zona es nueva retorna true, de lo contrario false

Fecha Creacion     : 02/09/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE COM_PR_ZONA_NUEVA(psCodPais VARCHAR2,
                            psIdPais NUMBER,
                            psCodPeriodo VARCHAR2,
                            psIdPeriActual NUMBER,
                            psCodComision VARCHAR2
                           ) IS

         CURSOR C_ComiCalcPeriZona IS
            SELECT A.*
              FROM COM_COMIS_PERIO_CALCU_ZONA A
             WHERE A.PERI_COD_PERI = psCodPeriodo
               AND COD_COMI = psCodComision
             ORDER BY A.PERI_COD_PERI, A.COD_REGI, A.COD_ZONA;


         lv_count            NUMBER;
         lv_impVentaNeta     NUMBER;
         lv_procesar         BOOLEAN;
         lv_zonaNueva        BOOLEAN;
         lv_insertar         BOOLEAN;
         lv_porceRecupe      NUMBER;
         lv_porceActividad   NUMBER;
         lv_importeBono      NUMBER;
         lv_sumaNumActiFinal NUMBER;
         lv_sumaNumPedidos   NUMBER;
         lv_porcentajeActividad NUMBER;



BEGIN
      /* Verificar si existe registro en la tabla de parametros */
          BEGIN
            SELECT t.imp_vent_neta,
                   t.por_recu,
                   t.por_acti,
                   t.imp_bono
              INTO lv_impVentaNeta,
                   lv_porceRecupe,
                   lv_porceActividad,
                   lv_importeBono
              FROM COM_PARAM_ZONAS_NUEVA T
             WHERE T.COD_PAIS = psCodPais;

            lv_procesar := TRUE;

          EXCEPTION
            WHEN no_data_found THEN
              lv_procesar := FALSE;
          END;
     /* Fin verificar si existe registro en la tabla de parametros */

         IF( lv_procesar)THEN
              FOR fila IN C_ComiCalcPeriZona LOOP
                   /* Verificar si Zona es Nueva*/
                      SELECT count(*)
                      INTO lv_count
                      FROM ZON_ZONA ZZ
                      WHERE ZZ.PAIS_OID_PAIS = psIdPais
                            AND ZZ.PERD_OID_PERI_INIC = psIdPeriActual
                            AND ZZ.COD_ZONA = fila.cod_zona;

                      IF(lv_count>0)THEN
                            lv_zonaNueva :=TRUE;
                            lv_insertar:=TRUE;
                      ELSE
                            SELECT count(*)
                            INTO lv_count
                            FROM COM_ZONAS_NUEVA ZN
                            WHERE ZN.COD_PAIS = psCodPais
                                 AND ZN.COD_ZONA =fila.cod_zona
                                 AND (ZN.COD_CAMP_INIC = psCodPeriodo OR ZN.COD_CAMP_FINA IS NULL);
                            IF(lv_count>0)THEN
                                 lv_zonaNueva:= TRUE;
                                 lv_insertar:= FALSE;
                            ELSE
                                 lv_zonaNueva:= FALSE;
                            END IF;
                      END IF;
                     /* Fin Verificar si Zona es Nueva*/

                      IF(lv_zonaNueva) THEN

                            IF fila.imp_neto_sin_recl < lv_impVentaNeta THEN
                               SELECT sum(ifvr.num_acti_fina)
                                 INTO lv_sumaNumActiFinal
                                 FROM INT_FUENT_VENTAS_REAL IFVR,
                                      ZON_ZONA ZZ
                                WHERE IFVR.PERD_OID_PERI = psIdPeriActual
                                  AND IFVR.PAIS_OID_PAIS = psIdPais
                                  AND IFVR.ZZON_OID_ZONA = ZZ.OID_ZONA
                                  AND ZZ.COD_ZONA = FILA.COD_ZONA;

                              SELECT sum(IFVRV.Num_Pedi)
                                INTO lv_sumaNumPedidos
                                FROM INT_FUENT_VENTA_REAL_VACUM IFVRV,
                                     ZON_ZONA ZZ
                               WHERE IFVRV.PERD_OID_PERI = psIdPeriActual
                                 AND IFVRV.PAIS_OID_PAIS = psIdPais
                                 AND IFVRV.ZZON_OID_ZONA = ZZ.OID_ZONA
                                 AND ZZ.COD_ZONA = FILA.COD_ZONA;

                                 lv_porcentajeActividad:= lv_sumaNumPedidos*100/lv_sumaNumActiFinal;

                                 IF(fila.por_recu>=lv_porceRecupe AND lv_porcentajeActividad >=lv_porceActividad)THEN
                                     UPDATE COM_COMIS_PERIO_CALCU_ZONA CCPCZ
                                     SET    CCPCZ.IMP_COMI_TRA1 = lv_importeBono
                                     WHERE CCPCZ.PERI_COD_PERI=fila.peri_cod_peri
                                           and CCPCZ.COD_ZONA = fila.cod_zona
                                           and CCPCZ.Cod_Regi = fila.cod_regi;
                                 ELSE
                                     UPDATE COM_COMIS_PERIO_CALCU_ZONA CCPCZ
                                     SET    CCPCZ.IMP_COMI_TRA1 = 0
                                     WHERE CCPCZ.PERI_COD_PERI=fila.peri_cod_peri
                                           and CCPCZ.COD_ZONA = fila.cod_zona
                                           and CCPCZ.Cod_Regi = fila.cod_regi;
                                 END IF;
                            END IF;

                             IF(lv_insertar)THEN
                                  SELECT COUNT(1)
                                  INTO lv_count
                                    FROM COM_ZONAS_NUEVA ZN
                                   WHERE ZN.COD_PAIS = psCodPais
                                     AND ZN.COD_ZONA = fila.cod_zona
                                     AND ZN.COD_CAMP_INIC = psCodPeriodo;

                                  IF(lv_count=0)THEN
                                    INSERT INTO COM_ZONAS_NUEVA
                                    (COD_PAIS, COD_ZONA, COD_CAMP_INIC, COD_CAMP_FINA)
                                    VALUES (psCodPais, fila.cod_zona, psCodPeriodo, NULL);
                                  END IF;
                          END IF;
                          IF(fila.imp_neto_sin_recl>=lv_impVentaNeta)THEN
                              UPDATE COM_ZONAS_NUEVA CZN
                              SET CZN.COD_CAMP_FINA = psCodPeriodo
                              WHERE CZN.COD_PAIS = psCodPais
                                    and CZN.COD_ZONA = fila.cod_zona;
                          END IF;
                      END IF;

                END LOOP;
         END IF;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_ZONA_NUEVA: '||ls_sqlerrm);

END COM_PR_ZONA_NUEVA;
/***************************************************************************
Descripcion        : Devuelve el importe de comision de ingreso de ejecutivas
Fecha Creacion     : 25/09/2008
Autor             : RRG
***************************************************************************/
FUNCTION COM_FN_DEVUE_COMIS_INGRE_EJECU(
   psCodPeriodo      VARCHAR2,
   psCodLiderSeccion VARCHAR2,
   psComisionIngreso VARCHAR2)
RETURN NUMBER
IS
   lnMontoComision NUMBER;
   lnMontoDcto     NUMBER;
BEGIN
   BEGIN
       SELECT NVL(SUM(A.IMP_COMI),0), NVL(SUM(A.IMP_COMI_DCTO),0)
       INTO
            lnMontoComision, lnMontoDcto
       FROM COM_COMIS_PERIO_CALCU A
       WHERE A.PERI_COD_PERI = psCodPeriodo
         AND A.COD_COMI = psComisionIngreso
         AND A.COD_LIDE_SECC = psCodLiderSeccion;
   EXCEPTION
   WHEN no_Data_found THEN
        lnMontoComision := 0;
   END;

   lnMontoComision := lnMontoComision - lnMontoDcto;
   IF lnMontoComision IS NULL THEN
      RETURN 0;
   END IF;
   RETURN  lnMontoComision;
EXCEPTION
WHEN no_data_found THEN
   RETURN  0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_COMIS_INGRE_EJECU: '||ls_sqlerrm);
END COM_FN_DEVUE_COMIS_INGRE_EJECU;
/***************************************************************************
Descripcion        : Devuelve el importe de comision de EJCAL + BONO (sb)
Fecha Creacion     : 25/09/2008
Fecha Modified     : 10/06/2009
Autor             : RRG
Modified by     : Sergio Buchelli
***************************************************************************/
FUNCTION COM_FN_DEVUE_COMIS_EJCAL(
   psCodPais         VARCHAR2,
   psCodComi        VARCHAR2,
   psCodEjec         VARCHAR2,
   psCodPeri         VARCHAR2,
   factor            NUMBER)
RETURN NUMBER
IS
   lnMontoComision NUMBER;
BEGIN

   BEGIN
       --SELECT NVL(CM.VAL_MOTO_COMI,0) + NVL(CM.VAL_BONO,0) + ( factor * NVL(CM.VAL_MOTO_COMI,0) )/100
       SELECT (1 + factor/100)*(NVL(CM.VAL_MOTO_COMI,0) + NVL(CM.VAL_BONO,0))
       INTO
            lnMontoComision
       FROM COM_COMIS_EJCAL_CABEC CM
       WHERE CM.COD_PAIS = psCodPais
         AND CM.COD_COMI = psCodComi
         AND CM.COD_EJEC = psCodEjec
         AND CM.COD_CAMP = psCodPeri;
   EXCEPTION
   WHEN no_Data_found THEN
        lnMontoComision := 0;
   END;

   IF lnMontoComision IS NULL THEN
      RETURN 0;
   END IF;
   RETURN  lnMontoComision;
EXCEPTION
WHEN no_data_found THEN
   RETURN  0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_COMIS_EJCAL: '||ls_sqlerrm);
END COM_FN_DEVUE_COMIS_EJCAL;
/***************************************************************************
Descripcion        : Devuelve el valor del monto de comision + BONO (sb)
Fecha Creacion     : 25/09/2008
Fecha Modified     : 10/06/2009
Autor             : RRG
Modified by     : Sergio Buchelli
***************************************************************************/
FUNCTION COM_FN_DEVUE_MONTO_COMIS(
   psCodPais         VARCHAR2,
   psCodComi        VARCHAR2,
   psCodEjec         VARCHAR2,
   psCodPeri         VARCHAR2)
RETURN NUMBER
IS
   lnMontoComision NUMBER;
BEGIN

   BEGIN
       SELECT NVL(CM.VAL_MOTO_COMI,0) + NVL(CM.VAL_BONO,0)
       INTO
            lnMontoComision
       FROM COM_COMIS_EJCAL_CABEC CM
       WHERE CM.COD_PAIS = psCodPais
         AND CM.COD_COMI = psCodComi
         AND CM.COD_EJEC = psCodEjec
         AND CM.COD_CAMP = psCodPeri;
   EXCEPTION
   WHEN no_Data_found THEN
        lnMontoComision := 0;
   END;

   IF lnMontoComision IS NULL THEN
      RETURN 0;
   END IF;
   RETURN  lnMontoComision;
EXCEPTION
WHEN no_data_found THEN
   RETURN  0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_MONTO_COMIS: '||ls_sqlerrm);
END COM_FN_DEVUE_MONTO_COMIS;


/***************************************************************************
Descripcion        : Funcion que devuelve la tasa de Impuesto.
Fecha Creacion     : 07/04/2009
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_TASA_IMPUE (
   psCodigoPais VARCHAR2,
   pdFecha DATE
)
RETURN NUMBER
IS
  lnTasaImpuesto NUMBER;
  ldFecha DATE;
BEGIN
  ldFecha := trunc(pdFecha);
  BEGIN
     SELECT A.VAL_TASA_IMPU
     INTO
         lnTasaImpuesto
     FROM BAS_TASA_IMPUE A
     WHERE A.COD_PAIS = psCodigoPais
       AND TRUNC(A.FEC_INIC) <= ldFecha
       AND A.FEC_FINA IS NULL;
  EXCEPTION
  WHEN no_data_found THEN
       BEGIN
         SELECT A.VAL_TASA_IMPU
         INTO
             lnTasaImpuesto
         FROM BAS_TASA_IMPUE A
         WHERE A.COD_PAIS = psCodigoPais
           AND TRUNC(A.FEC_INIC) <= ldFecha
           AND TRUNC(A.FEC_FINA) >= ldFecha;
       EXCEPTION
       WHEN no_data_found THEN
            lnTasaImpuesto := 0.0;
       END;
  END;
  RETURN lnTasaImpuesto;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_TASA_IMPUE: '||ls_sqlerrm);
END COM_FN_DEVUE_TASA_IMPUE;


/***************************************************************************
Descripcion        : Funcion que devuelve la maxima Fecha de Inicio de CRA_ACTIV
                     en base al pais, periodo, zona y codigo de actividad
Fecha Creacion     : 14/05/2009
Parametros         :
       pnIdPais    : Id del Pais
       pnIdPeriodo : Id del Periodo
       pnIdZona    : Id de Zona
       psCodActi   : Codigo de Actividad
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_MAXIM_FINIC_CRONO (
   pnIdPais NUMBER,
   pnIdPeriodo NUMBER,
   pnIdZona NUMBER,
   psCodActi VARCHAR2
)
RETURN DATE
IS
  ldFechaInicio DATE;
BEGIN
  ldFechaInicio := NULL;
  SELECT MAX(A.FEC_INIC)
  INTO ldFechaInicio
  FROM
     CRA_CRONO A,
     CRA_ACTIV B
  WHERE  B.PAIS_OID_PAIS = pnIdPais
     AND A.PERD_OID_PERI = pnIdPeriodo
     AND B.OID_ACTI = A.CACT_OID_ACTI
     AND A.ZZON_OID_ZONA = pnIdZona
     AND B.COD_ACTI = psCodActi ;
  RETURN ldFechaInicio;

EXCEPTION
WHEN no_data_found THEN
     RETURN NULL;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_MAXIM_FINIC_CRONO: '||ls_sqlerrm);
END COM_FN_MAXIM_FINIC_CRONO;


/***************************************************************************
Descripcion        : Procedimiento que obtiene los montos totales correspondiente
                     a la comision de recuperacion correspondiente a LIDERES
Fecha Creacion     : 19/05/2009
Parametros         :
Autor              : Carlos Bazalar

Fecha Modificacion : 10/08/2010
modificado         : David Ramos
Motivo             : Debido al Requerimiento de Calculo por Escalonada y
                     a su vez el almacenamiento de nuevos campos
***************************************************************************/
PROCEDURE COM_PR_OBTIE_MONTO_MLIDE_RECUP (
   psCodComision        VARCHAR2,
   psCodPeriodo         VARCHAR2,
   psTipoComision       VARCHAR2,
   psBaseComision       VARCHAR2,
   pnValorIGVVen        NUMBER,
   pnIndDctoImportePago NUMBER,
   pnIndDctoReclamo     NUMBER,
   pnValPorceComi01     NUMBER,
   pnValPorceComi02     NUMBER,
   pnValPorceRecu01     NUMBER,
   pnValPorceRecu02     NUMBER,
   psCodigoRegionBase07 VARCHAR2,
   psCodigoZonaBase07   VARCHAR2
)
IS
  TYPE tTablaCalcu IS TABLE OF COM_COMIS_PERIO_CALCU%ROWTYPE;
  tablaCalcu       tTablaCalcu;
  regCalcu         COM_COMIS_PERIO_CALCU%ROWTYPE;
  lnPorComision    com_comis_escal.por_comi%TYPE;
  lnValBono        com_comis_escal.val_bono%TYPE;
  CURSOR cCalcu
  IS
    SELECT A.*
    FROM COM_COMIS_PERIO_CALCU A
    WHERE A.PERI_COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision
      AND A.IND_COMI = COMISION_RECUPERACION;

BEGIN
    /* Borrando registros que no poseean Lideres */
    DELETE FROM COM_REPOR_COMIS_RECUP A
    WHERE A.COD_PERI = psCodPeriodo
       AND A.COD_COMI = psCodComision
       AND A.COD_LIDE_SECC IS NULL;

    /* Insertando Registro en tabla COM_COMIS_PERIO_CALCU */
    IF psBaseComision <> '07' THEN
        INSERT INTO COM_COMIS_PERIO_CALCU
         (PERI_COD_PERI, COD_COMI,
          COD_LIDE_SECC, NOM_LIDE_SECC,
          IND_COMI, COD_REGI, COD_ZONA,
          COD_SECC, IMP_COMI_TRA1, IMP_COMI_TRA2,
          IMP_NETO_SIN_RECL, IMP_PAGO_ANTE_LIMI, IMP_PAGO_ANTE_LIMI_TRA2,
          IMP_PAGO_ANTE_LIMI_CIMP, IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
          FEC_CALC, TIP_COMI,
          IMP_MONT_PAGO_COMI, IMP_MONT_PAGO_COMI_CIMP, IMP_MONT_PAGO_COMI_TRA2, IMP_MONT_PAGO_COMI_TRA2_CIMP,
          IMP_RECL, IMP_RECL_CIMP, IMP_NETO_SREC_CIMP, CAR_FRAC_SIMP, CAR_FRAC_CIMP,
          IMP_IMPU, IMP_NETO, IMP_NETO_CIMP,
          IMP_IMPU_PAGO)
        SELECT
           psCodPeriodo, psCodComision ,
           A.COD_LIDE_SECC, A.NOM_LIDE_SECC,
           '02', A.COD_REGI, A.COD_ZONA,
           A.COD_SECC, 0, 0,
           SUM(A.IMP_NETO_SIN_RECL), SUM(A.IMP_PAGO_ANTE_LIMI), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
           SUM(A.IMP_PAGO_ANTE_LIMI_CIMP), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
           SYSDATE, 'L',
           SUM(A.IMP_MONT_PAGO_COMI), SUM(A.IMP_MONT_PAGO_COMI_CIMP), SUM(A.IMP_MONT_PAGO_COMI_TRA2), SUM(A.IMP_MONT_PAGO_COMI_TRA2_CIMP),
           SUM(A.IMP_RECL), SUM(A.IMP_RECL_CIMP), SUM(A.IMP_NETO_SREC_CIMP), SUM(A.IMP_CARG_FRAC), SUM(A.IMP_CARG_FRAC_CIMP),
           SUM(A.IMP_IMPU), SUM(A.IMP_NETO), SUM(A.IMP_NETO_CIMP),
           SUM(A.IMP_IMPU_PAGO)
        FROM  COM_REPOR_COMIS_RECUP A
        WHERE A.COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodComision
        GROUP BY A.COD_REGI, A.COD_ZONA, A.COD_SECC, A.COD_LIDE_SECC, A.NOM_LIDE_SECC ;
    ELSE

    INSERT INTO COM_COMIS_PERIO_CALCU
     (PERI_COD_PERI, COD_COMI,
      COD_LIDE_SECC, NOM_LIDE_SECC,
      IND_COMI, COD_REGI, COD_ZONA,
      COD_SECC, IMP_COMI_TRA1, IMP_COMI_TRA2,
      IMP_NETO_SIN_RECL, IMP_PAGO_ANTE_LIMI, IMP_PAGO_ANTE_LIMI_TRA2,
      IMP_PAGO_ANTE_LIMI_CIMP, IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
      FEC_CALC, TIP_COMI,
      IMP_MONT_PAGO_COMI, IMP_MONT_PAGO_COMI_CIMP, IMP_MONT_PAGO_COMI_TRA2, IMP_MONT_PAGO_COMI_TRA2_CIMP,
      IMP_RECL, IMP_RECL_CIMP, IMP_NETO_SREC_CIMP, CAR_FRAC_SIMP, CAR_FRAC_CIMP,
      IMP_IMPU, IMP_NETO, IMP_NETO_CIMP,
      IMP_IMPU_PAGO)
    SELECT
       psCodPeriodo, psCodComision ,
       A.COD_LIDE_SECC, A.NOM_LIDE_SECC,
       '02', A.COD_REGI, A.COD_ZONA,
       A.COD_SECC, 0, 0,
       SUM(A.IMP_NETO_SIN_RECL), SUM(A.IMP_PAGO_ANTE_LIMI), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
       SUM(A.IMP_PAGO_ANTE_LIMI_CIMP), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
       SYSDATE, 'L',
       SUM(A.IMP_MONT_PAGO_COMI), SUM(A.IMP_MONT_PAGO_COMI_CIMP), SUM(A.IMP_MONT_PAGO_COMI_TRA2), SUM(A.IMP_MONT_PAGO_COMI_TRA2_CIMP),
       SUM(A.IMP_RECL), SUM(A.IMP_RECL_CIMP), SUM(A.IMP_NETO_SREC_CIMP), SUM(A.IMP_CARG_FRAC), SUM(A.IMP_CARG_FRAC_CIMP),
       SUM(A.IMP_IMPU), SUM(A.IMP_NETO), SUM(A.IMP_NETO_CIMP),
       SUM(A.IMP_IMPU_PAGO)
    FROM  COM_REPOR_COMIS_RECUP A
    WHERE A.COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision
        AND A.COD_REGI = psCodigoRegionBase07
        AND A.COD_ZONA = psCodigoZonaBase07
    GROUP BY A.COD_REGI, A.COD_ZONA, A.COD_SECC, A.COD_LIDE_SECC, A.NOM_LIDE_SECC ;
    END IF;

    /* Actualizando porcentaje de recuperacion (Tramo 1 y Tramo 2) */
    IF pnIndDctoImportePago = 1 THEN
       if pnIndDctoReclamo = 1 THEN
        UPDATE COM_COMIS_PERIO_CALCU A
          SET POR_RECU = (A.IMP_PAGO_ANTE_LIMI / (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_RECL)) * 100,
              POR_RECU_TRA2 = ((A.IMP_PAGO_ANTE_LIMI + A.IMP_PAGO_ANTE_LIMI_TRA2) / (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_RECL)) * 100
        WHERE A.PERI_COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodComision
          AND A.IND_COMI = COMISION_RECUPERACION
            AND (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_RECL) <> 0 ;
    ELSE
        UPDATE COM_COMIS_PERIO_CALCU A
          SET POR_RECU = (A.IMP_PAGO_ANTE_LIMI / (A.IMP_NETO + A.CAR_FRAC_SIMP)) * 100,
              POR_RECU_TRA2 = ((A.IMP_PAGO_ANTE_LIMI + A.IMP_PAGO_ANTE_LIMI_TRA2) / (A.IMP_NETO + A.CAR_FRAC_SIMP )) * 100
        WHERE A.PERI_COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodComision
          AND A.IND_COMI = COMISION_RECUPERACION
            AND (A.IMP_NETO + A.CAR_FRAC_SIMP ) <> 0 ;
       END IF;
    ELSE
       if pnIndDctoReclamo = 1 THEN
          UPDATE COM_COMIS_PERIO_CALCU A
            SET POR_RECU = (A.IMP_PAGO_ANTE_LIMI_CIMP / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_RECL_CIMP)) * 100,
                POR_RECU_TRA2 = ((A.IMP_PAGO_ANTE_LIMI_CIMP + A.IMP_PAGO_ANTE_LIMI_CIMP) / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_RECL_CIMP)) * 100
            WHERE A.PERI_COD_PERI = psCodPeriodo
              AND A.COD_COMI = psCodComision
              AND A.IND_COMI = COMISION_RECUPERACION
              AND (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_RECL_CIMP) <> 0 ;
       ELSE
          UPDATE COM_COMIS_PERIO_CALCU A
            SET POR_RECU = (A.IMP_PAGO_ANTE_LIMI_CIMP / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP )) * 100,
                POR_RECU_TRA2 = ((A.IMP_PAGO_ANTE_LIMI_CIMP + A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP) / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP)) * 100
            WHERE A.PERI_COD_PERI = psCodPeriodo
              AND A.COD_COMI = psCodComision
              AND A.IND_COMI = COMISION_RECUPERACION
              AND (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP) <> 0 ;
       END IF;
    END IF;

    IF Gln_FlagComisEscal = 0 THEN --SI_ES_ESCALONADA  =  'N'
        /* Actualizando importe de comision (Tramo 1) */
        UPDATE COM_COMIS_PERIO_CALCU A
        SET IMP_COMI_TRA1 = A.IMP_MONT_PAGO_COMI * pnValPorceComi01 / 100,
            A.POR_COMI_TRA1 = pnValPorceComi01
        WHERE A.PERI_COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodComision
          AND A.IND_COMI = COMISION_RECUPERACION
          AND A.POR_RECU >=  pnValPorceRecu01;

        IF pnValPorceRecu02 IS NOT NULL AND pnValPorceRecu02 > 0 THEN
           /* Actualizando importe de comision (Tramo 2) */
           UPDATE COM_COMIS_PERIO_CALCU A
           SET IMP_COMI_TRA2 = (A.IMP_MONT_PAGO_COMI + A.IMP_MONT_PAGO_COMI_TRA2) * pnValPorceComi02 / 100,
               A.POR_COMI_TRA2 = pnValPorceComi02
           WHERE A.PERI_COD_PERI = psCodPeriodo
             AND A.COD_COMI = psCodComision
             AND A.IND_COMI = COMISION_RECUPERACION
             AND A.POR_RECU_TRA2 >=  pnValPorceRecu02;

           /* En caso % Recuperacion1 es mayor igual a % Exigido Comision 2 */
           UPDATE COM_COMIS_PERIO_CALCU A
           SET IMP_COMI_TRA2 = (A.IMP_MONT_PAGO_COMI * pnValPorceComi02) / 100,
               A.POR_COMI_TRA2 = pnValPorceComi02
           WHERE A.PERI_COD_PERI = psCodPeriodo
                 AND A.COD_COMI = psCodComision
                 AND A.IND_COMI = COMISION_RECUPERACION
                 AND A.POR_RECU >= pnValPorceRecu02
                 AND A.IMP_PAGO_ANTE_LIMI_TRA2 = 0;
        END IF;
   ELSE /* EN CASO SEA COMISION ESCALONADA */
   -------------------------------------- base 01 o 02
      OPEN cCalcu;
      LOOP
          FETCH cCalcu BULK COLLECT INTO tablaCalcu LIMIT W_FILAS;
          IF tablaCalcu.COUNT > 0 THEN
             FOR x IN tablaCalcu.FIRST .. tablaCalcu.LAST LOOP
                 regCalcu := tablaCalcu(x);
                 BEGIN
                   SELECT B.POR_COMI, B.VAL_BONO
                   INTO
                       lnPorComision, lnValBono
                   FROM COM_COMIS_ESCAL B, COM_COMIS_COBRA A
                   WHERE A.COMI_OID_COMI  = Gln_IdComision
                     AND A.OID_COMI_COBR = B.OID_COMI_COBR
                     AND B.POR_RECU_INIC <= regCalcu.Por_Recu
                     AND B.POR_RECU_FINA >= regCalcu.Por_Recu;
                 EXCEPTION
                 WHEN no_data_found THEN
                   lnPorComision:= 0;
                   lnValBono:= 0;
                 END ;
                 IF lnValBono > 0 THEN
                    UPDATE COM_COMIS_PERIO_CALCU A
                    SET A.IMP_COMI_TRA1 = lnValBono,
                        A.POR_COMI_TRA1 = 0.00
                    WHERE A.PERI_COD_PERI = psCodPeriodo
                      AND A.COD_COMI = psCodComision
                      AND A.IND_COMI = COMISION_RECUPERACION
                      AND A.COD_REGI = regCalcu.COD_REGI
                      AND A.COD_ZONA = regCalcu.COD_ZONA
                      AND A.COD_SECC = regCalcu.COD_SECC;
                 ELSE
                    UPDATE COM_COMIS_PERIO_CALCU A
                    SET A.IMP_COMI_TRA1 = A.IMP_MONT_PAGO_COMI * lnPorComision / 100,
                        A.POR_COMI_TRA1 = lnPorComision
                    WHERE A.PERI_COD_PERI = psCodPeriodo
                      AND A.COD_COMI = psCodComision
                      AND A.IND_COMI = COMISION_RECUPERACION
                      AND A.COD_REGI = regCalcu.COD_REGI
                      AND A.COD_ZONA = regCalcu.COD_ZONA
                      AND A.COD_SECC = regCalcu.COD_SECC;
                 END IF;
             END LOOP;
          END IF;
          EXIT WHEN cCalcu%NOTFOUND;
      END LOOP;
      CLOSE cCalcu;
      -------------------------------------------------------------

   END IF;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_OBTIE_MONTO_MLIDE_RECUP: '||ls_sqlerrm);
END COM_PR_OBTIE_MONTO_MLIDE_RECUP;


/***************************************************************************
Descripcion        : Procedimiento que obtiene los montos totales correspondiente
                     a la comision de recuperacion correspondiente a GERENTES DE
                     ZONA
Fecha Creacion     : 19/05/2009
Parametros         :
Autor              : Carlos Bazalar

Fecha Modificacion : 10/08/2010
modificado         : David Ramos
Motivo             : Debido al Requerimiento de Calculo por Escalonada y
                     a su vez el almacenamiento de nuevos campos
***************************************************************************/
PROCEDURE COM_PR_OBTIE_MONTO_GZONA_RECUP (
   lnOidPais            NUMBER,
   psCodPais            VARCHAR2,
   psCodMarca           VARCHAR2,
   psCodCanal           VARCHAR2,
   psCodComision        VARCHAR2,
   psCodPeriodo         VARCHAR2,
   psTipoComision       VARCHAR2,
   psBaseComision       VARCHAR2,
   pnValorIGVVen        NUMBER,
   pnIndDctoImportePago NUMBER,
   pnIndDctoReclamo     NUMBER,
   pnValPorceComi01     NUMBER,
   pnValPorceComi02     NUMBER,
   pnValPorceRecu01     NUMBER,
   pnValPorceRecu02     NUMBER,
   pnIdPeriodo          NUMBER,
   pnValPorceActividad  NUMBER,
   pnValProceActividadCOMI  NUMBER,
   pnValPorceComi       NUMBER,
   pnPorcenVentaConsu   NUMBER,
   psUsuario            VARCHAR2,
   psCodigoRegionBase07 VARCHAR2,
   psCodigoZonaBase07   VARCHAR2
)
IS
   CURSOR cComisZona IS
     SELECT COD_REGI, COD_ZONA, COD_LIDE_ZONA
     FROM COM_COMIS_PERIO_CALCU_ZONA A
     WHERE A.PERI_COD_PERI = psCodPeriodo
       AND A.COD_COMI = psCodComision;
   lnIdZona           zon_zona.oid_zona%TYPE;
   lnSumaNumActiFina  NUMBER;
   lnSumaNumPedi      NUMBER;
   lnNumeroNivel      number;

   TYPE tTablaCalcu IS TABLE OF COM_COMIS_PERIO_CALCU_ZONA%ROWTYPE;
   tablaCalcu       tTablaCalcu;
   regCalcu         COM_COMIS_PERIO_CALCU_ZONA%ROWTYPE;
   lnPorComision    com_comis_escal.por_comi%TYPE;
   lnValBono        com_comis_escal.val_bono%TYPE;

   TYPE tTablaZonasSaldo IS TABLE OF COM_GTT_TABLA_REPOS%ROWTYPE;
   tablaZonasSaldo       tTablaZonasSaldo;
   regZonasSaldo         COM_GTT_TABLA_REPOS%ROWTYPE;
   lnComisionAdicional   COM_COMIS_ESCAL.COMI_ADI%TYPE;
   lnPorRecuInicial      COM_COMIS_ESCAL.POR_RECU_INIC%TYPE;
   lnPorRecuFinal        COM_COMIS_ESCAL.POR_RECU_FINA%TYPE;

   CURSOR cCalcu
   IS
    SELECT A.*
    FROM COM_COMIS_PERIO_CALCU_ZONA A
    WHERE A.PERI_COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision;

   CURSOR cZonasSaldo
   IS
    SELECT A.*
    FROM COM_GTT_TABLA_REPOS A;

    ------------------------------------
    v_por_comi           COM_COMIS_ESCAL.Por_Comi%type := 0;
    v_porcentaje         number(1);
    v_mon_comi      COM_COMIS_ESCAL.Por_Comi%type := 0;
    v_comi_obtenida      COM_COMIS_ESCAL.Por_Comi%type := 0;
    v_camp              varchar2(25);
    v_cod_regi          varchar2(25);
    v_cod_zona          varchar2(25);
    v_val_cata          COM_COMIS_ESCAL.Por_Comi%type := 0;
    v_val_cata_est      COM_COMIS_ESCAL.Por_Comi%type := 0;
    v_porcentaje_venta  number(3);

    lnImportePago                   COM_COMIS_PERIO_CALCU_ZONA.IMP_PAGO_ANTE_LIMI%TYPE;
    lnImportePagoCImpuesto          COM_COMIS_PERIO_CALCU_ZONA.IMP_PAGO_ANTE_LIMI_CIMP%TYPE;
    lnImporteDctoNeto               COM_COMIS_PERIO_CALCU_ZONA.IMP_DCTO_NETO%TYPE;
    lnImporteDctoNetoCImpuesto      COM_COMIS_PERIO_CALCU_ZONA.IMP_DCTO_NETO_CIMP%TYPE;
    lnImporteNeto                   COM_COMIS_PERIO_CALCU_ZONA.IMP_NETO%TYPE;
    lnPorcentajePagado              NUMBER;
    psIndExigeResponsable  BAS_PARAM_PAIS.VAL_PARA%type;
BEGIN

    /*  Obteniendo el valor de Exige responsables*/    
    SELECT VAL_PARA
    INTO psIndExigeResponsable    
    FROM   BAS_PARAM_PAIS
    WHERE  COD_PAIS = psCodPais
    AND    COD_SIST = 'COM'
    AND    IND_ACTI = '1'
    AND    VAL_PARA = '1'
    AND    NOM_PARA = 'INDEXIGERESPONSABLE';

    /* EN CASO DE GERENTES DE ZONA */
    IF psBaseComision <> '07' THEN
    /* Insertando Registro en tabla COM_COMIS_PERIO_CALCU */
    INSERT INTO COM_COMIS_PERIO_CALCU_ZONA
     (PERI_COD_PERI, COD_COMI,
      COD_REGI, COD_ZONA,
      COD_LIDE_ZONA, NOM_LIDE_ZONA,
      IMP_COMI_TRA1, IMP_COMI_TRA2,
      IMP_NETO_SIN_RECL, IMP_PAGO_ANTE_LIMI, IMP_PAGO_ANTE_LIMI_TRA2,
      IMP_PAGO_ANTE_LIMI_CIMP, IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
      FEC_CALC, TIP_COMI, COD_EMPL,
      VAL_PORC_ACTI, VAL_PORC_COMI_TRA1,
      IMP_MONT_PAGO_COMI, IMP_MONT_PAGO_COMI_CIMP, IMP_MONT_PAGO_COMI_TRA2, IMP_MONT_PAGO_COMI_TRA2_CIMP,
      IMP_RECL, IMP_RECL_CIMP, IMP_NETO_SREC_CIMP, CAR_FRAC_SIMP, CAR_FRAC_CIMP,
      VAL_IMPU, IMP_NETO, IMP_NETO_CIMP, VAL_SALD,
      IMP_DCTO_NETO, IMP_DCTO_NETO_CIMP,
      IMP_IMPU_PAGO )
    SELECT
       psCodPeriodo, psCodComision,
       A.COD_REGI, A.COD_ZONA,
       A.COD_LIDE_ZONA, A.NOM_LIDE_ZONA,
       0, 0,
       SUM(A.IMP_NETO_SIN_RECL), SUM(A.IMP_PAGO_ANTE_LIMI), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
       SUM(A.IMP_PAGO_ANTE_LIMI_CIMP), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
       SYSDATE, 'G', C.COD_EMPL,
       pnValPorceActividad, pnValPorceComi,
       SUM(A.IMP_MONT_PAGO_COMI), SUM(A.IMP_MONT_PAGO_COMI_CIMP), SUM(A.IMP_MONT_PAGO_COMI_TRA2), SUM(A.IMP_MONT_PAGO_COMI_TRA2_CIMP),
       SUM(A.IMP_RECL), SUM(A.IMP_RECL_CIMP), SUM(A.IMP_NETO_SREC_CIMP), SUM(A.IMP_CARG_FRAC), SUM(A.IMP_CARG_FRAC_CIMP),
       SUM(A.IMP_IMPU), SUM(A.IMP_NETO), SUM(A.IMP_NETO_CIMP), 0.00,
       SUM(A.IMP_DCTO_NETO), SUM(A.IMP_DCTO_NETO_CIMP),
       SUM(A.IMP_IMPU_PAGO)
    FROM  COM_REPOR_COMIS_RECUP A,
          MAE_CLIEN B,
          MAE_CLIEN_DATOS_ADICI C
    WHERE A.COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision
      AND B.COD_CLIE = A.COD_LIDE_ZONA
      AND C.CLIE_OID_CLIE = B.OID_CLIE
      AND (psIndExigeResponsable is null or C.COD_EMPL IS NOT NULL)
      AND A.COD_LIDE_ZONA IS NOT NULL
    GROUP BY A.COD_REGI, A.COD_ZONA, A.COD_LIDE_ZONA, A.NOM_LIDE_ZONA, C.COD_EMPL ;

    INSERT INTO COM_COMIS_PERIO_CALCU_ZONA
     (PERI_COD_PERI, COD_COMI,
      COD_REGI, COD_ZONA,
      COD_LIDE_ZONA, NOM_LIDE_ZONA,
      IMP_COMI_TRA1, IMP_COMI_TRA2,
      IMP_NETO_SIN_RECL, IMP_PAGO_ANTE_LIMI, IMP_PAGO_ANTE_LIMI_TRA2,
      IMP_PAGO_ANTE_LIMI_CIMP, IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
      FEC_CALC, TIP_COMI, COD_EMPL,
      VAL_PORC_ACTI, VAL_PORC_COMI_TRA1,
      IMP_MONT_PAGO_COMI, IMP_MONT_PAGO_COMI_CIMP, IMP_MONT_PAGO_COMI_TRA2, IMP_MONT_PAGO_COMI_TRA2_CIMP,
      IMP_RECL, IMP_RECL_CIMP, IMP_NETO_SREC_CIMP, CAR_FRAC_SIMP, CAR_FRAC_CIMP,
      VAL_IMPU, IMP_NETO, IMP_NETO_CIMP, VAL_SALD,
      IMP_DCTO_NETO, IMP_DCTO_NETO_CIMP,
      IMP_IMPU_PAGO )
    SELECT
       psCodPeriodo, psCodComision,
       A.COD_REGI, A.COD_ZONA,
       NULL, NULL,
       0, 0,
       SUM(A.IMP_NETO_SIN_RECL), SUM(A.IMP_PAGO_ANTE_LIMI), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
       SUM(A.IMP_PAGO_ANTE_LIMI_CIMP), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
       SYSDATE, 'G', NULL,
       pnValPorceActividad, pnValPorceComi,
       SUM(A.IMP_MONT_PAGO_COMI), SUM(A.IMP_MONT_PAGO_COMI_CIMP), SUM(A.IMP_MONT_PAGO_COMI_TRA2), SUM(A.IMP_MONT_PAGO_COMI_TRA2_CIMP),
       SUM(A.IMP_RECL), SUM(A.IMP_RECL_CIMP), SUM(A.IMP_NETO_SREC_CIMP), SUM(A.IMP_CARG_FRAC), SUM(A.IMP_CARG_FRAC_CIMP),
       SUM(A.IMP_IMPU), SUM(A.IMP_NETO), SUM(A.IMP_NETO_CIMP), 0.00,
       SUM(A.IMP_DCTO_NETO), SUM(A.IMP_DCTO_NETO_CIMP),
       SUM(A.IMP_IMPU_PAGO)
    FROM  COM_REPOR_COMIS_RECUP A
    WHERE A.COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision
      AND A.COD_LIDE_ZONA IS NULL
    GROUP BY A.COD_REGI, A.COD_ZONA;
    ELSE
        /* Insertando Registro en tabla COM_COMIS_PERIO_CALCU */
        INSERT INTO COM_COMIS_PERIO_CALCU_ZONA
         (PERI_COD_PERI, COD_COMI,
          COD_REGI, COD_ZONA,
          COD_LIDE_ZONA, NOM_LIDE_ZONA,
          IMP_COMI_TRA1, IMP_COMI_TRA2,
          IMP_NETO_SIN_RECL, IMP_PAGO_ANTE_LIMI, IMP_PAGO_ANTE_LIMI_TRA2,
          IMP_PAGO_ANTE_LIMI_CIMP, IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
          FEC_CALC, TIP_COMI, COD_EMPL,
          VAL_PORC_ACTI, VAL_PORC_COMI_TRA1,
          IMP_MONT_PAGO_COMI, IMP_MONT_PAGO_COMI_CIMP, IMP_MONT_PAGO_COMI_TRA2, IMP_MONT_PAGO_COMI_TRA2_CIMP,
          IMP_RECL, IMP_RECL_CIMP, IMP_NETO_SREC_CIMP, CAR_FRAC_SIMP, CAR_FRAC_CIMP,
          VAL_IMPU, IMP_NETO, IMP_NETO_CIMP, VAL_SALD,
          IMP_DCTO_NETO, IMP_DCTO_NETO_CIMP,
          IMP_IMPU_PAGO )
        SELECT
           psCodPeriodo, psCodComision,
           A.COD_REGI, A.COD_ZONA,
           A.COD_LIDE_ZONA, A.NOM_LIDE_ZONA,
           0, 0,
           SUM(A.IMP_NETO_SIN_RECL), SUM(A.IMP_PAGO_ANTE_LIMI), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
           SUM(A.IMP_PAGO_ANTE_LIMI_CIMP), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
           SYSDATE, 'G', C.COD_EMPL,
           pnValPorceActividad, pnValPorceComi,
           SUM(A.IMP_MONT_PAGO_COMI), SUM(A.IMP_MONT_PAGO_COMI_CIMP), SUM(A.IMP_MONT_PAGO_COMI_TRA2), SUM(A.IMP_MONT_PAGO_COMI_TRA2_CIMP),
           SUM(A.IMP_RECL), SUM(A.IMP_RECL_CIMP), SUM(A.IMP_NETO_SREC_CIMP), SUM(A.IMP_CARG_FRAC), SUM(A.IMP_CARG_FRAC_CIMP),
           SUM(A.IMP_IMPU), SUM(A.IMP_NETO), SUM(A.IMP_NETO_CIMP), 0.00,
           SUM(A.IMP_DCTO_NETO), SUM(A.IMP_DCTO_NETO_CIMP),
           SUM(A.IMP_IMPU_PAGO)
        FROM  COM_REPOR_COMIS_RECUP A,
              MAE_CLIEN B,
              MAE_CLIEN_DATOS_ADICI C
        WHERE A.COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodComision
          AND A.COD_REGI = psCodigoRegionBase07
          AND A.COD_ZONA = psCodigoZonaBase07
          AND B.COD_CLIE = A.COD_LIDE_ZONA
          AND C.CLIE_OID_CLIE = B.OID_CLIE
          AND (psIndExigeResponsable is null or C.COD_EMPL IS NOT NULL)
          AND A.COD_LIDE_ZONA IS NOT NULL
        GROUP BY A.COD_REGI, A.COD_ZONA, A.COD_LIDE_ZONA, A.NOM_LIDE_ZONA, C.COD_EMPL ;

        INSERT INTO COM_COMIS_PERIO_CALCU_ZONA
         (PERI_COD_PERI, COD_COMI,
          COD_REGI, COD_ZONA,
          COD_LIDE_ZONA, NOM_LIDE_ZONA,
          IMP_COMI_TRA1, IMP_COMI_TRA2,
          IMP_NETO_SIN_RECL, IMP_PAGO_ANTE_LIMI, IMP_PAGO_ANTE_LIMI_TRA2,
          IMP_PAGO_ANTE_LIMI_CIMP, IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
          FEC_CALC, TIP_COMI, COD_EMPL,
          VAL_PORC_ACTI, VAL_PORC_COMI_TRA1,
          IMP_MONT_PAGO_COMI, IMP_MONT_PAGO_COMI_CIMP, IMP_MONT_PAGO_COMI_TRA2, IMP_MONT_PAGO_COMI_TRA2_CIMP,
          IMP_RECL, IMP_RECL_CIMP, IMP_NETO_SREC_CIMP, CAR_FRAC_SIMP, CAR_FRAC_CIMP,
          VAL_IMPU, IMP_NETO, IMP_NETO_CIMP, VAL_SALD,
          IMP_DCTO_NETO, IMP_DCTO_NETO_CIMP,
          IMP_IMPU_PAGO )
        SELECT
           psCodPeriodo, psCodComision,
           A.COD_REGI, A.COD_ZONA,
           NULL, NULL,
           0, 0,
           SUM(A.IMP_NETO_SIN_RECL), SUM(A.IMP_PAGO_ANTE_LIMI), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
           SUM(A.IMP_PAGO_ANTE_LIMI_CIMP), SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
           SYSDATE, 'G', NULL,
           pnValPorceActividad, pnValPorceComi,
           SUM(A.IMP_MONT_PAGO_COMI), SUM(A.IMP_MONT_PAGO_COMI_CIMP), SUM(A.IMP_MONT_PAGO_COMI_TRA2), SUM(A.IMP_MONT_PAGO_COMI_TRA2_CIMP),
           SUM(A.IMP_RECL), SUM(A.IMP_RECL_CIMP), SUM(A.IMP_NETO_SREC_CIMP), SUM(A.IMP_CARG_FRAC), SUM(A.IMP_CARG_FRAC_CIMP),
           SUM(A.IMP_IMPU), SUM(A.IMP_NETO), SUM(A.IMP_NETO_CIMP), 0.00,
           SUM(A.IMP_DCTO_NETO), SUM(A.IMP_DCTO_NETO_CIMP),
           SUM(A.IMP_IMPU_PAGO)
        FROM  COM_REPOR_COMIS_RECUP A
        WHERE A.COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodComision
          AND A.COD_REGI = psCodigoRegionBase07
          AND A.COD_ZONA = psCodigoZonaBase07
          AND A.COD_LIDE_ZONA IS NULL
        GROUP BY A.COD_REGI, A.COD_ZONA;

    END IF;
    /* actualizando valores de auditoria */
    UPDATE COM_COMIS_PERIO_CALCU_ZONA A
    SET FEC_DIGI = SYSDATE,
        USU_DIGI = psUsuario
    WHERE A.PERI_COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision;

    /* actualizando saldo zona */
    IF psTipoComision = '02' AND  psBaseComision = '01' THEN --Antes G
        OPEN cZonasSaldo;
          LOOP
              FETCH cZonasSaldo BULK COLLECT INTO tablaZonasSaldo LIMIT W_FILAS;
              IF tablaZonasSaldo.COUNT > 0 THEN
                 FOR x IN tablaZonasSaldo.FIRST .. tablaZonasSaldo.LAST LOOP
                     regZonasSaldo := tablaZonasSaldo(x);

                     UPDATE COM_COMIS_PERIO_CALCU_ZONA A
                     SET A.VAL_SALD = regZonasSaldo.CAM_NUME
                     WHERE A.PERI_COD_PERI = psCodPeriodo
                       AND A.COD_COMI = psCodComision
                       AND A.COD_ZONA = regZonasSaldo.CAM_CADE;
                 END LOOP;
              END IF;
              EXIT WHEN cZonasSaldo%NOTFOUND;
          END LOOP;
        CLOSE cZonasSaldo;
    END IF;

    /* Actualizando porcentaje de recuperacion (Tramo 1 y Tramo 2) */
    IF pnIndDctoImportePago = 1 THEN
       if pnIndDctoReclamo = 1 THEN
          UPDATE COM_COMIS_PERIO_CALCU_ZONA A
          SET POR_RECU = ((A.IMP_PAGO_ANTE_LIMI )/ (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_RECL - A.IMP_DCTO_NETO)) * 100,
              POR_RECU_TRA2 = ((A.IMP_PAGO_ANTE_LIMI + A.IMP_PAGO_ANTE_LIMI_TRA2 ) / (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_RECL - A.IMP_DCTO_NETO)) * 100
          WHERE A.PERI_COD_PERI = psCodPeriodo
            AND A.COD_COMI = psCodComision
            AND (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_RECL - A.IMP_DCTO_NETO) <> 0 ;
       ELSE
          UPDATE COM_COMIS_PERIO_CALCU_ZONA A
          SET POR_RECU = ((A.IMP_PAGO_ANTE_LIMI) / (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_DCTO_NETO)) * 100,
              POR_RECU_TRA2 = ((A.IMP_PAGO_ANTE_LIMI + A.IMP_PAGO_ANTE_LIMI_TRA2 ) / (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_DCTO_NETO)) * 100
          WHERE A.PERI_COD_PERI = psCodPeriodo
            AND A.COD_COMI = psCodComision
            AND (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_DCTO_NETO ) <> 0 ;
       END IF;
    ELSE
       if pnIndDctoReclamo = 1 THEN
        UPDATE COM_COMIS_PERIO_CALCU_ZONA A
            SET POR_RECU = ((A.IMP_PAGO_ANTE_LIMI_CIMP ) / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_RECL_CIMP - A.IMP_DCTO_NETO_CIMP)) * 100,
                POR_RECU_TRA2 = ((A.IMP_PAGO_ANTE_LIMI_CIMP + A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP ) / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_RECL_CIMP - A.IMP_DCTO_NETO_CIMP)) * 100
        WHERE A.PERI_COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodComision
              AND (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_RECL_CIMP - A.IMP_DCTO_NETO_CIMP) <> 0 ;
    ELSE
        UPDATE COM_COMIS_PERIO_CALCU_ZONA A
            SET POR_RECU = ((A.IMP_PAGO_ANTE_LIMI_CIMP ) / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_DCTO_NETO_CIMP)) * 100,
                POR_RECU_TRA2 = ((A.IMP_PAGO_ANTE_LIMI_CIMP + A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP ) / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_DCTO_NETO_CIMP)) * 100
        WHERE A.PERI_COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodComision
              AND (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_DCTO_NETO_CIMP) <> 0 ;
       END IF;
    END IF;

    /* (Tramo 1) */
    IF Gln_FlagComisEscal = 0 THEN --SI_ES_ESCALONADA  =  'N'
        /*inicio modificación decu: COL-SiCC-2015-0159*/
        if psBaseComision = '07' then
          UPDATE COM_COMIS_PERIO_CALCU_ZONA A 
             SET IMP_COMI_TRA1 = ( (A.IMP_NETO + A.CAR_FRAC_SIMP) - A.IMP_RECL) * pnValPorceComi01 / 100 ,
                 A.VAL_PORC_COMI_TRA1 = pnValPorceComi01
             WHERE A.PERI_COD_PERI = psCodPeriodo
                  AND A.COD_COMI = psCodComision
                  AND A.POR_RECU >=  pnValPorceRecu01;
        else
          /* Actualizando importe de comision  */
             UPDATE COM_COMIS_PERIO_CALCU_ZONA A
             SET IMP_COMI_TRA1 = A.IMP_MONT_PAGO_COMI * pnValPorceComi01 / 100,
                 A.VAL_PORC_COMI_TRA1 = pnValPorceComi01
             WHERE A.PERI_COD_PERI = psCodPeriodo
                  AND A.COD_COMI = psCodComision
                  AND A.POR_RECU >=  pnValPorceRecu01;
         end if;
         /*fin modificación decu: COL-SiCC-2015-0159*/

    ELSE /* EN CASO SEA COMISION ESCALONADA */


    --base comision 01 o 02-----------------
    if psBaseComision = '01' OR psBaseComision = '02' then
      OPEN cCalcu;
      LOOP
          FETCH cCalcu BULK COLLECT INTO tablaCalcu LIMIT W_FILAS;
          IF tablaCalcu.COUNT > 0 THEN
             FOR x IN tablaCalcu.FIRST .. tablaCalcu.LAST LOOP
                 regCalcu := tablaCalcu(x);
                 BEGIN
                   SELECT NVL(B.POR_COMI,0), B.VAL_BONO, NVL(B.COMI_ADI,0), B.POR_RECU_INIC, B.POR_RECU_FINA, B.NUM_NIVE
                   INTO
                       lnPorComision, lnValBono, lnComisionAdicional, lnPorRecuInicial, lnPorRecuFinal, lnNumeroNivel
                   FROM COM_COMIS_ESCAL B, COM_COMIS_COBRA A
                   WHERE A.COMI_OID_COMI  = Gln_IdComision
                     AND A.OID_COMI_COBR = B.OID_COMI_COBR
                     AND B.POR_RECU_INIC <= regCalcu.Por_Recu
                     AND B.POR_RECU_FINA >= regCalcu.Por_Recu;
                 EXCEPTION
                 WHEN no_data_found THEN
                   lnPorComision:= 0;
                   lnValBono:= 0;
                   lnComisionAdicional := 0;
                 END ;
                 IF psBaseComision = '02' THEN
                    UPDATE COM_COMIS_PERIO_CALCU_ZONA A
                    SET A.IMP_COMI_TRA1 = A.IMP_MONT_PAGO_COMI * lnPorComision / 100,
                        A.VAL_PORC_COMI_TRA1 = lnPorComision
                    WHERE A.PERI_COD_PERI = psCodPeriodo
                      AND A.COD_COMI = psCodComision
                      AND A.COD_REGI = regCalcu.COD_REGI
                      AND A.COD_ZONA = regCalcu.COD_ZONA;
                 ELSE
                    UPDATE COM_COMIS_PERIO_CALCU_ZONA A
                    SET A.VEN_NETA_EFEC = (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.VAL_IMPU - A.IMP_RECL - A.VAL_SALD)  ,
                        A.IMP_COMI_TRA1 = (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.VAL_IMPU - A.IMP_RECL - A.VAL_SALD)  * lnPorComision / 100,
                        A.VAL_PORC_COMI_TRA1 = lnPorComision
                    WHERE A.PERI_COD_PERI = psCodPeriodo
                      AND A.COD_COMI = psCodComision
                      AND A.COD_REGI = regCalcu.COD_REGI
                      AND A.COD_ZONA = regCalcu.COD_ZONA;

                 END IF;

                 IF regCalcu.POR_RECU >= lnPorRecuInicial   THEN
                    UPDATE COM_COMIS_PERIO_CALCU_ZONA A
                    SET A.NUM_NIVE = lnNumeroNivel,
                        A.POR_RECU_INIC = lnPorRecuInicial,
                        A.POR_RECU_FINA = lnPorRecuFinal
                    WHERE A.PERI_COD_PERI = psCodPeriodo
                      AND A.COD_COMI = psCodComision
                      AND A.COD_REGI = regCalcu.COD_REGI
                      AND A.COD_ZONA = regCalcu.COD_ZONA;

                 END IF;

                 IF regCalcu.POR_RECU >= lnPorRecuInicial and lnComisionAdicional > 0  THEN
                    UPDATE COM_COMIS_PERIO_CALCU_ZONA A
                    SET A.DIF_VENT_NETA_EFEC = A.VEN_NETA_EFEC * ((regCalcu.POR_RECU - lnPorRecuInicial) / 100),
                        A.COM_ADIC = (A.VEN_NETA_EFEC * ((regCalcu.POR_RECU - lnPorRecuInicial) / 100)) * lnComisionAdicional  / 100,
                        A.POR_ADIC = lnComisionAdicional
                    WHERE A.PERI_COD_PERI = psCodPeriodo
                      AND A.COD_COMI = psCodComision
                      AND A.COD_REGI = regCalcu.COD_REGI
                      AND A.COD_ZONA = regCalcu.COD_ZONA;

                 END IF;

             END LOOP;
          END IF;
          EXIT WHEN cCalcu%NOTFOUND;
      END LOOP;
      CLOSE cCalcu;
      -----------------------------fin
      end if;

      -- Validacion Comentada, luego descomentar.
      if psBaseComision = '04' then
         COM_PR_CALCU_COMIS_VENT_ZONA (lnOidPais ,
                                      psCodPeriodo,
                                      psCodComision,
                                      pnIdPeriodo);
         --base comision 04 -----------------
         OPEN cCalcu;
         LOOP
            FETCH cCalcu BULK COLLECT INTO tablaCalcu LIMIT W_FILAS;
            IF tablaCalcu.COUNT > 0 THEN
               FOR x IN tablaCalcu.FIRST .. tablaCalcu.LAST LOOP
                   regCalcu := tablaCalcu(x);
                   IF regCalcu.cod_zona = '1113' THEN
                      regCalcu.cod_zona := '1113';
                   END IF;
                   BEGIN
                     SELECT B.NUM_NIVE
                     INTO
                         lnNumeroNivel
                     FROM COM_COMIS_ESCAL B, COM_COMIS_COBRA A
                     WHERE A.COMI_OID_COMI  = Gln_IdComision
                       AND A.OID_COMI_COBR = B.OID_COMI_COBR
                       AND B.POR_RECU_INIC <= regCalcu.Por_Recu
                       AND B.POR_RECU_FINA >= regCalcu.Por_Recu
                       AND B.POR_OBJE_INIC <= regCalcu.Por_Mon_Cata
                       AND B.POR_OBJE_FINA >= regCalcu.Por_Mon_Cata;
                   EXCEPTION
                   WHEN no_data_found THEN
                     lnNumeroNivel:= 0;
                   END ;
                   UPDATE COM_COMIS_PERIO_CALCU_ZONA A
                   SET A.NUM_NIVE = lnNumeroNivel
                      WHERE A.PERI_COD_PERI = psCodPeriodo
                        AND A.COD_COMI = psCodComision
                        AND A.COD_REGI = regCalcu.COD_REGI
                        AND A.COD_ZONA = regCalcu.COD_ZONA;
               END LOOP;
            END IF;
            EXIT WHEN cCalcu%NOTFOUND;
        END LOOP;
        CLOSE cCalcu;

      end if;

    END IF;

    /* (Tramo 2) */
    IF Gln_FlagComisEscal = 0 THEN --SI_ES_ESCALONADA  =  'N'

        /* Actualizando importe de comision  */
        IF pnValPorceRecu02 IS NOT NULL AND pnValPorceRecu02 > 0 THEN
           UPDATE COM_COMIS_PERIO_CALCU_ZONA A
           SET IMP_COMI_TRA2 = A.IMP_MONT_PAGO_COMI_TRA2 * pnValPorceComi02 / 100,
               A.VAL_PORC_COMI_TRA2 = pnValPorceComi02
           WHERE A.PERI_COD_PERI = psCodPeriodo
                  AND A.COD_COMI = psCodComision
                  AND A.POR_RECU_TRA2 >=  pnValPorceRecu02;

           /* En caso % Recuperacion1 es menor  a % Exigido Comision 1 */
           UPDATE COM_COMIS_PERIO_CALCU_ZONA A
           SET IMP_COMI_TRA2 = (A.IMP_MONT_PAGO_COMI + A.IMP_MONT_PAGO_COMI_TRA2) * pnValPorceComi02 / 100,
               A.VAL_PORC_COMI_TRA2 = pnValPorceComi02
           WHERE A.PERI_COD_PERI = psCodPeriodo
                AND A.COD_COMI = psCodComision
                AND A.POR_RECU < pnValPorceRecu01
                AND A.POR_RECU_TRA2 >= pnValPorceRecu02;
        END IF;
    END IF;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_OBTIE_MONTO_GZONA_RECUP: '||ls_sqlerrm);
END COM_PR_OBTIE_MONTO_GZONA_RECUP;

/***************************************************************************
Descripcion        : Procedimiento que obtiene los montos totales correspondiente
                     a la comision de recuperacion correspondiente a GERENTES DE
                     REGION
Fecha Creacion     : 19/05/2009
Parametros         :
Autor              : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_MONTO_GREGI_RECUP (
   pnOidPais            NUMBER,
   psCodPais            VARCHAR2,
   psCodMarca           VARCHAR2,
   psCodCanal           VARCHAR2,
   psCodComision        VARCHAR2,
   psCodPeriodo         VARCHAR2,
   pnIdPeriodo          NUMBER,
   psTipoComision       VARCHAR2,
   psBaseComision       VARCHAR2,
   pnValorIGVVen        NUMBER,
   pnIndDctoImportePago NUMBER,
   pnIndDctoReclamo     NUMBER,
   pnValPorceComi01     NUMBER,
   pnValPorceComi02     NUMBER,
   pnValPorceRecu01     NUMBER,
   pnValPorceRecu02     NUMBER,
   psUsuario            VARCHAR2,
   psCodigoRegionBase07 VARCHAR2
)
IS
  TYPE tTablaCalcu IS TABLE OF COM_COMIS_PERIO_CALCU_REGIO%ROWTYPE;
   tablaCalcu       tTablaCalcu;
   regCalcu         COM_COMIS_PERIO_CALCU_REGIO%ROWTYPE;
   lnPorComision    com_comis_escal.por_comi%TYPE;
   lnValBono        com_comis_escal.val_bono%TYPE;
   CURSOR cCalcu
   IS
    SELECT A.*
    FROM COM_COMIS_PERIO_CALCU_REGIO A
    WHERE A.COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodComision;
BEGIN
  /* EN CASO DE GERENTES REGION */
  IF psBaseComision <> '07' THEN
      INSERT INTO COM_COMIS_PERIO_CALCU_REGIO
        (COD_PERI,
         COD_COMI,
         COD_REGI,
         IMP_MONT_COM1,
         IMP_MONT_COM2,
         IMP_MONT_VENT,
         IMP_MONT_PAGA_TRA1,
         IMP_MONT_PAGA_TRA2,
         IMP_MONT_PAGA_TRA1_CIMP,
         IMP_MONT_PAGA_TRA2_CIMP,
         FEC_CALC,
         COD_LIDE_REGI,
         NOM_LIDE_REGI,
         IND_COMI,
         POR_RECU_TRA1,
         POR_RECU_TRA2,
         POR_COMI_TRA1,
         POR_COMI_TRA2,
         IMP_MONT_PAGO_COMI, IMP_MONT_PAGO_COMI_CIMP, IMP_MONT_PAGO_COMI_TRA2, IMP_MONT_PAGO_COMI_TRA2_CIMP,
         IMP_RECL, IMP_RECL_CIMP, IMP_NETO_SREC_CIMP, CAR_FRAC_SIMP, CAR_FRAC_CIMP,
         IMP_IMPU, IMP_NETO, IMP_NETO_CIMP, IMP_NETO_SIN_RECL,
         IMP_DCTO_NETO, IMP_DCTO_NETO_CIMP,
         IMP_IMPU_PAGO
         )
        SELECT psCodPeriodo,
               psCodComision,
               A.COD_REGI,
               0,
               0,
               SUM(A.IMP_NETO_SIN_RECL),
               SUM(A.IMP_PAGO_ANTE_LIMI),
               SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
               SUM(A.IMP_PAGO_ANTE_LIMI_CIMP),
               SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
               SYSDATE,
               A.COD_LIDE_REGI,
               A.NOM_LIDE_REGI,
               COMISION_RECUPERACION,-- 02=Recuperacion, 01=Ingresos
               0,
               0 ,  pnValPorceComi01 ,pnValPorceComi02,
               SUM(A.IMP_MONT_PAGO_COMI), SUM(A.IMP_MONT_PAGO_COMI_CIMP), SUM(A.IMP_MONT_PAGO_COMI_TRA2), SUM(A.IMP_MONT_PAGO_COMI_TRA2_CIMP),
               SUM(A.IMP_RECL), SUM(A.IMP_RECL_CIMP), SUM(A.IMP_NETO_SREC_CIMP), SUM(A.IMP_CARG_FRAC), SUM(A.IMP_CARG_FRAC_CIMP),
               SUM(A.IMP_IMPU), SUM(A.IMP_NETO), SUM(A.IMP_NETO_CIMP), SUM(A.IMP_NETO_SIN_RECL),
               SUM(A.IMP_DCTO_NETO), SUM(A.IMP_DCTO_NETO_CIMP),
               SUM(A.IMP_IMPU_PAGO)
          FROM COM_REPOR_COMIS_RECUP A
         WHERE A.COD_PERI = psCodPeriodo
           AND A.COD_COMI = psCodComision
         GROUP BY A.COD_REGI, A.COD_LIDE_REGI, A.NOM_LIDE_REGI;
  ELSE
  INSERT INTO COM_COMIS_PERIO_CALCU_REGIO
    (COD_PERI,
     COD_COMI,
     COD_REGI,
     IMP_MONT_COM1,
     IMP_MONT_COM2,
     IMP_MONT_VENT,
     IMP_MONT_PAGA_TRA1,
     IMP_MONT_PAGA_TRA2,
     IMP_MONT_PAGA_TRA1_CIMP,
     IMP_MONT_PAGA_TRA2_CIMP,
     FEC_CALC,
     COD_LIDE_REGI,
     NOM_LIDE_REGI,
     IND_COMI,
     POR_RECU_TRA1,
     POR_RECU_TRA2,
     POR_COMI_TRA1,
     POR_COMI_TRA2,
     IMP_MONT_PAGO_COMI, IMP_MONT_PAGO_COMI_CIMP, IMP_MONT_PAGO_COMI_TRA2, IMP_MONT_PAGO_COMI_TRA2_CIMP,
     IMP_RECL, IMP_RECL_CIMP, IMP_NETO_SREC_CIMP, CAR_FRAC_SIMP, CAR_FRAC_CIMP,
     IMP_IMPU, IMP_NETO, IMP_NETO_CIMP, IMP_NETO_SIN_RECL,
     IMP_DCTO_NETO, IMP_DCTO_NETO_CIMP,
     IMP_IMPU_PAGO
     )
    SELECT psCodPeriodo,
           psCodComision,
           A.COD_REGI,
           0,
           0,
           SUM(A.IMP_NETO_SIN_RECL),
           SUM(A.IMP_PAGO_ANTE_LIMI),
           SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
           SUM(A.IMP_PAGO_ANTE_LIMI_CIMP),
           SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
           SYSDATE,
           A.COD_LIDE_REGI,
           A.NOM_LIDE_REGI,
           COMISION_RECUPERACION,-- 02=Recuperacion, 01=Ingresos
           0,
           0 ,  pnValPorceComi01 ,pnValPorceComi02,
           SUM(A.IMP_MONT_PAGO_COMI), SUM(A.IMP_MONT_PAGO_COMI_CIMP), SUM(A.IMP_MONT_PAGO_COMI_TRA2), SUM(A.IMP_MONT_PAGO_COMI_TRA2_CIMP),
           SUM(A.IMP_RECL), SUM(A.IMP_RECL_CIMP), SUM(A.IMP_NETO_SREC_CIMP), SUM(A.IMP_CARG_FRAC), SUM(A.IMP_CARG_FRAC_CIMP),
           SUM(A.IMP_IMPU), SUM(A.IMP_NETO), SUM(A.IMP_NETO_CIMP), SUM(A.IMP_NETO_SIN_RECL),
           SUM(A.IMP_DCTO_NETO), SUM(A.IMP_DCTO_NETO_CIMP),
           SUM(A.IMP_IMPU_PAGO)
      FROM COM_REPOR_COMIS_RECUP A
     WHERE A.COD_PERI = psCodPeriodo
       AND A.COD_COMI = psCodComision
           AND A.COD_REGI = psCodigoRegionBase07
     GROUP BY A.COD_REGI, A.COD_LIDE_REGI, A.NOM_LIDE_REGI;

  END IF;

  /* actualizar valores de auditoria */
  UPDATE COM_COMIS_PERIO_CALCU_REGIO A
  SET FEC_DIGI = SYSDATE,
      USU_DIGI = psUsuario
  WHERE A.COD_PERI = psCodPeriodo
    AND A.COD_COMI = psCodComision;

  /* Actualizando porcentaje de recuperacion (Tramo 1 y Tramo 2) */
  IF pnIndDctoImportePago = 1 THEN
       if pnIndDctoReclamo = 1 THEN
      UPDATE COM_COMIS_PERIO_CALCU_REGIO A
          SET POR_RECU_TRA1 = (A.IMP_MONT_PAGA_TRA1 / (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_RECL)) * 100,
              POR_RECU_TRA2 = ((A.IMP_MONT_PAGA_TRA1 + A.IMP_MONT_PAGA_TRA2) / (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_RECL)) * 100
      WHERE A.COD_PERI = psCodPeriodo
        AND A.COD_COMI = psCodComision
            AND (A.IMP_NETO + A.CAR_FRAC_SIMP - A.IMP_RECL) <> 0 ;
  ELSE
      UPDATE COM_COMIS_PERIO_CALCU_REGIO A
          SET POR_RECU_TRA1 = (A.IMP_MONT_PAGA_TRA1 / (A.IMP_MONT_VENT + A.CAR_FRAC_SIMP )) * 100,
              POR_RECU_TRA2 = ((A.IMP_MONT_PAGA_TRA1 + A.IMP_MONT_PAGA_TRA2) / (A.IMP_MONT_VENT + A.CAR_FRAC_SIMP )) * 100
      WHERE A.COD_PERI = psCodPeriodo
        AND A.COD_COMI = psCodComision
            AND (A.IMP_MONT_VENT + A.CAR_FRAC_SIMP ) <> 0 ;
       END IF;
  ELSE
       if pnIndDctoReclamo = 1 THEN
          UPDATE COM_COMIS_PERIO_CALCU_REGIO A
            SET POR_RECU_TRA1 = (A.IMP_MONT_PAGA_TRA1_CIMP / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_RECL_CIMP)) * 100,
                POR_RECU_TRA2 = ((A.IMP_MONT_PAGA_TRA1_CIMP + A.IMP_MONT_PAGA_TRA2_CIMP) / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_RECL_CIMP)) * 100
            WHERE A.COD_PERI = psCodPeriodo
              AND A.COD_COMI = psCodComision
              AND (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP - A.IMP_RECL_CIMP) <> 0 ;
       ELSE
          UPDATE COM_COMIS_PERIO_CALCU_REGIO A
            SET POR_RECU_TRA1 = (A.IMP_MONT_PAGA_TRA1_CIMP / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP)) * 100,
                POR_RECU_TRA2 = ((A.IMP_MONT_PAGA_TRA1_CIMP + A.IMP_MONT_PAGA_TRA2_CIMP) / (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP)) * 100
            WHERE A.COD_PERI = psCodPeriodo
              AND A.COD_COMI = psCodComision
              AND (A.IMP_NETO_CIMP + A.CAR_FRAC_CIMP) <> 0 ;
       END IF;
  END IF;

  IF psBaseComision <> '04' THEN
  IF Gln_FlagComisEscal = 0 THEN --SI_ES_ESCALONADA  =  'N'
      /* (Tramo 1) */
               
      /*inicio modificación decu: COL-SiCC-2015-0159*/
        if psBaseComision = '07' then
           UPDATE COM_COMIS_PERIO_CALCU_REGIO A
             SET IMP_MONT_COM1 = ((A.IMP_NETO + A.CAR_FRAC_SIMP) - A.IMP_RECL) * pnValPorceComi01 / 100,
               por_comi_tra1 = pnValPorceComi01
           WHERE A.COD_PERI = psCodPeriodo
                AND A.COD_COMI = psCodComision
                AND A.POR_RECU_TRA1 >=  pnValPorceRecu01;
        else
          /* Actualizando importe de comision  */
           UPDATE COM_COMIS_PERIO_CALCU_REGIO A
             SET IMP_MONT_COM1 = A.IMP_MONT_PAGO_COMI * pnValPorceComi01 / 100,
               por_comi_tra1 = pnValPorceComi01
           WHERE A.COD_PERI = psCodPeriodo
                AND A.COD_COMI = psCodComision
                AND A.POR_RECU_TRA1 >=  pnValPorceRecu01;
         end if;
         /*fin modificación decu: COL-SiCC-2015-0159*/        
      
      /* (Tramo 2) */
      /* Actualizando importe de comision  */
      IF pnValPorceRecu02 IS NOT NULL AND pnValPorceRecu02 > 0 THEN
         UPDATE COM_COMIS_PERIO_CALCU_REGIO A
           SET IMP_MONT_COM2 = A.IMP_MONT_PAGO_COMI_TRA2 * pnValPorceComi02 / 100,
             por_comi_tra2 = pnValPorceComi02
         WHERE A.COD_PERI = psCodPeriodo
           AND A.COD_COMI = psCodComision
           AND A.POR_RECU_TRA2 >=  pnValPorceRecu02;

         /* En caso % Recuperacion1 es menor  a % Exigido Comision 1 */
         UPDATE COM_COMIS_PERIO_CALCU_REGIO A
           SET IMP_MONT_COM2 = (A.IMP_MONT_PAGO_COMI + A.IMP_MONT_PAGO_COMI_TRA2) * pnValPorceComi02 / 100,
             por_comi_tra2 = pnValPorceComi02
         WHERE A.COD_PERI = psCodPeriodo
           AND A.COD_COMI = psCodComision
           AND A.POR_RECU_TRA1 < pnValPorceRecu01
           AND A.POR_RECU_TRA2 >= pnValPorceRecu02;
      END IF;
  ELSE /* EN CASO SEA COMISION ESCALONADA */
      OPEN cCalcu;
      LOOP
          FETCH cCalcu BULK COLLECT INTO tablaCalcu LIMIT W_FILAS;
          IF tablaCalcu.COUNT > 0 THEN
             FOR x IN tablaCalcu.FIRST .. tablaCalcu.LAST LOOP
                 regCalcu := tablaCalcu(x);
                 BEGIN
                   SELECT B.POR_COMI, B.VAL_BONO
                   INTO
                       lnPorComision, lnValBono
                   FROM COM_COMIS_ESCAL B, COM_COMIS_COBRA A
                   WHERE A.COMI_OID_COMI  = Gln_IdComision
                     AND A.OID_COMI_COBR = B.OID_COMI_COBR
                     AND B.POR_RECU_INIC <= regCalcu.Por_Recu_Tra1
                     AND B.POR_RECU_FINA >= regCalcu.Por_Recu_Tra1;
                 EXCEPTION
                 WHEN no_data_found THEN
                   lnPorComision:= 0;
                   lnValBono:= 0;
                 END ;
                 IF lnValBono > 0 THEN
                    UPDATE COM_COMIS_PERIO_CALCU_REGIO A
                    SET IMP_MONT_COM1 = lnValBono,
                        POR_COMI_TRA1 = 0.00
                    WHERE A.COD_PERI = psCodPeriodo
                      AND A.COD_COMI = psCodComision
                      AND A.COD_REGI = regCalcu.COD_REGI
                      AND A.COD_LIDE_REGI = regCalcu.Cod_Lide_Regi;
                 ELSE
                    UPDATE COM_COMIS_PERIO_CALCU_REGIO A
                    SET IMP_MONT_COM1 = A.IMP_MONT_PAGO_COMI * lnPorComision / 100,
                        POR_COMI_TRA1 = lnPorComision
                    WHERE A.COD_PERI = psCodPeriodo
                      AND A.COD_COMI = psCodComision
                      AND A.COD_REGI = regCalcu.COD_REGI
                      AND A.COD_LIDE_REGI = regCalcu.Cod_Lide_Regi;
                 END IF;
             END LOOP;
          END IF;
          EXIT WHEN cCalcu%NOTFOUND;
      END LOOP;
      CLOSE cCalcu;
  END IF;
  ELSE

     COM_PR_CALCU_COMIS_VENT_REGIO (pnOidPais ,
                                  psCodPeriodo,
                                  psCodComision,
                                  pnIdPeriodo);
  END IF;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_OBTIE_MONTO_GREGI_RECUP: '||ls_sqlerrm);
END COM_PR_OBTIE_MONTO_GREGI_RECUP;


/***************************************************************************
Descripcion        : Procedimiento que realiza validaciones adicionales con Respecto
                     al Gerente de Zona
Fecha Creacion     : 19/05/2009
Parametros         :
Autor              : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_VALID_ADICI_GZONA_RECUP (
   pnIdComision         NUMBER,
   pnIdRegion           NUMBER,
   pnIdZona             NUMBER,
   psCodLiderZona       VARCHAR2,
   pbInsertar           IN OUT BOOLEAN,
   pbInsertarZona       IN OUT BOOLEAN
)
IS
   ldFechaDesde     DATE;
   ldFechaHasta     DATE;
   ldFechaIngreso   DATE;
   lsCodPlanilla    MAE_CLIEN_DATOS_ADICI.COD_EMPL%TYPE;
BEGIN

 /* Obteniendo fechas de la comision */
 BEGIN
     SELECT A.FEC_ANTI_DESD, A.FEC_ANTI_HAST
     INTO ldFechaDesde, ldFechaHasta
     FROM COM_COMIS_CLIEN A
     WHERE A.COMI_OID_COMI = pnIdComision
       AND A.ZORG_OID_REGI = pnIdRegion
       AND A.ZZON_OID_ZONA = pnIdZona;
 EXCEPTION
 WHEN no_data_found THEN
      BEGIN
         SELECT A.FEC_ANTI_DESD, A.FEC_ANTI_HAST
         INTO ldFechaDesde, ldFechaHasta
         FROM COM_COMIS_CLIEN A
         WHERE A.COMI_OID_COMI = pnIdComision
           AND A.ZORG_OID_REGI = pnIdRegion
           AND A.ZZON_OID_ZONA IS NULL;
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
      ldFechaDesde := NULL;
      ldFechaHasta := NULL;
 END;
 END;

 /* Encontrando fecha de ingreso de la Lider */
 BEGIN
     SELECT a.fec_ingr
     INTO
        ldFechaIngreso
     FROM
        MAE_CLIEN A
     WHERE A.COD_CLIE = psCodLiderZona;
 EXCEPTION
 WHEN no_Data_found THEN
      pbInsertar := false;
      pbInsertarZona := false;
      RETURN;
 END;

 /* Verificando fecha de ingreso de la Lider */
 IF pbInsertar THEN

    if ldFechaIngreso is null then
       pbInsertar := true;
       pbInsertarZona := true;
       return;
    end if;

    IF ldFechaHasta IS NULL THEN
       IF ldFechaIngreso < ldFechaDesde THEN
          pbInsertar :=FALSE;
          pbInsertarZona := FALSE;
       END IF;
    ELSE
       IF ldFechaIngreso < ldFechaDesde THEN
          pbInsertar :=FALSE;
          pbInsertarZona := FALSE;
       ELSE
           IF ldFechaIngreso > ldFechaHasta THEN
              pbInsertar :=FALSE;
              pbInsertarZona := FALSE;
           END IF;
       END IF;
    END IF;
    IF ldFechaDesde IS NULL AND ldFechaHasta IS NULL THEN
       pbInsertar := TRUE;
       pbInsertarZona := TRUE;
    END IF;
 END IF;

 /* Encontrando codigo de planilla */
 IF psCodLiderZona IS NOT NULL THEN
    BEGIN
       SELECT A.Cod_Empl
       INTO
          lsCodPlanilla
       FROM
          MAE_CLIEN_DATOS_ADICI A,
          MAE_CLIEN B
       WHERE B.OID_CLIE = A.CLIE_OID_CLIE
        AND  B.COD_CLIE = psCodLiderZona;
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
       pbInsertar := TRUE;
       pbInsertarZona := TRUE;
    END;
 END IF;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_VALID_ADICI_GZONA_RECUP: '||ls_sqlerrm);
END COM_PR_VALID_ADICI_GZONA_RECUP;

/********************************************************************************
Descripcion        : Funcion que valida que el responsable que se esta ingresando
                     en el rango de periodo no se encuentre ya registrado en otra
                     zona o seccion
Fecha Creacion     : 20/02/2012
Autor              : Jesse Rios
*********************************************************************************/
FUNCTION COM_FN_VALID_RESPO(psCodigoPais VARCHAR2,
                            psCodigoMarca VARCHAR2,
                            psCodigoCanal VARCHAR2,
                            psCodigoResponsable VARCHAR2,
                            psCodigoPeriodoDesde VARCHAR2,
                            psCodigoPeriodoHasta VARCHAR2) RETURN VARCHAR2
IS

    vnOidPais        SEG_PAIS.OID_PAIS%TYPE;
    vnOidMarca       SEG_MARCA.OID_MARC%TYPE;
    vnOidCanal       SEG_CANAL.OID_CANA%TYPE;
    vnNumeroCampanas NUMBER;
    vsCampanaProceso BAS_CTRL_FACT.COD_PERI%TYPE;
    vnOidCampanaProceso CRA_PERIO.OID_PERI%TYPE;

    retorno VARCHAR2(50);
    vsUA ZON_HISTO_GEREN.UA%TYPE;

    vnCont NATURAL;
BEGIN

    vnOidPais  := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

    vnNumeroCampanas := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(psCodigoPeriodoDesde,psCodigoPeriodoHasta,vnOidPais,vnOidMarca,vnOidCanal);

    retorno := 'OK';

    FOR j IN 0 .. vnNumeroCampanas-1 LOOP

        vsCampanaProceso := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodoDesde,vnOidPais,vnOidMarca,vnOidCanal,j);
        vnOidCampanaProceso := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(vsCampanaProceso);

        SELECT COUNT(1)
        INTO vnCont
        FROM ZON_HISTO_GEREN
        WHERE PAIS_OID_PAIS = vnOidPais
        AND GERE = psCodigoResponsable
        AND PERD_OID_PERI_DESD <= vnOidCampanaProceso
        AND (vnOidCampanaProceso <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

        IF vnCont > 0 THEN
           SELECT UA
           INTO vsUA
           FROM ZON_HISTO_GEREN
           WHERE PAIS_OID_PAIS = vnOidPais
           AND GERE = psCodigoResponsable
           AND PERD_OID_PERI_DESD <= vnOidCampanaProceso
           AND (vnOidCampanaProceso <= PERD_OID_PERI_HAST OR PERD_OID_PERI_HAST IS NULL);

           retorno := 'ER-'||vsUa||'-'||vsCampanaProceso;

                        EXIT;
                     END IF;

                  END LOOP;

    RETURN retorno;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_VALID_RESPO: '||ls_sqlerrm);
END COM_FN_VALID_RESPO;

/***************************************************************************
Descripcion       : Devuelve Responsable de la Unidad Administrativa en base
                    al periodo de proceso
Fecha Creacion    : 11/04/2012
Autor             : Jesse James Rios Franco
***************************************************************************/
FUNCTION COM_FN_DEVUE_RESPO_UNIAD_HIST2
(psCodResponsable OUT VARCHAR2,
 psCodigoPeriodo  VARCHAR2,
 pnIdPais         NUMBER,
 psCodSubGerencia VARCHAR2,
 psCodRegion      VARCHAR2,
 psCodZona        VARCHAR2,
 psCodSeccion     VARCHAR2:=NULL
)RETURN VARCHAR2

IS

  vsUA        ZON_HISTO_GEREN.UA%TYPE;
  lsRetorno   ZON_HISTO_GEREN.GERE%TYPE;
  lsNombres   VARCHAR2(500);
  vnOidPeriodo CRA_PERIO.OID_PERI%TYPE;

BEGIN

   vsUA := psCodSubGerencia;
   vnOidPeriodo := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);

   IF (psCodRegion IS NOT NULL) THEN
      vsUA :=  vsUA || psCodRegion;
   END IF;
   IF (psCodZona IS NOT NULL) THEN
      vsUA :=  vsUA || psCodZona;
   END IF;
   IF (psCodSeccion IS NOT NULL) THEN
      vsUA :=  vsUA || psCodSeccion;
               END IF;

   lsRetorno := '';
   psCodResponsable := '';

   BEGIN
     SELECT GERE
     INTO lsRetorno
     FROM ZON_HISTO_GEREN
     WHERE PAIS_OID_PAIS = pnIdPais
     AND UA = vsUA
     AND (vnOidPeriodo >= PERD_OID_PERI_DESD)
     AND (vnOidPeriodo <= PERD_OID_PERI_HAST or PERD_OID_PERI_HAST IS NULL);
   EXCEPTION
     WHEN NO_DATA_FOUND THEN
       lsRetorno := NULL;
   END;

   lsRetorno := TRIM(lsRetorno);

   psCodResponsable := lsRetorno;

   /* Encontrando nombre respectivo en la tabla MAE_CLIEN */
   IF (lsRetorno IS NOT NULL) THEN
       BEGIN
         SELECT TRIM(NVL(A.VAL_APE1,' ')) || ' ' ||
                TRIM(NVL(A.VAL_APE2,' ')) || ' ' ||
                TRIM(NVL(A.VAL_NOM1,' '))
         INTO lsNombres
         FROM
            MAE_CLIEN A
         WHERE  A.COD_CLIE = lsRetorno
            AND A.PAIS_OID_PAIS = pnIdPais;
         RETURN lsNombres;
       EXCEPTION
       WHEN NO_DATA_FOUND THEN
            RETURN '';
       END;
                 END IF;

   RETURN lsRetorno;

EXCEPTION
  WHEN TOO_MANY_ROWS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123,'Unidad Administrativa tiene mas de 1 responsable '|| vsUA);
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_RESPO_UNIAD_HIST2: '||ls_sqlerrm);
END COM_FN_DEVUE_RESPO_UNIAD_HIST2;
--FIN DE PRODUCCION


/***************************************************************************
Descripcion        : Devuelve fecha de Vencimiento para una Gerente que esta
                     siendo calculada su comision escalonada
Fecha Creacion     : 24/12/2012
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION COM_FN_DEVUE_FECHA_VENCI(
   psCodPais         VARCHAR2,
   psCodPeriodo      VARCHAR2,
   pnOidZona         VARCHAR2)
RETURN VARCHAR2
IS
  lsFechaVencimiento  VARCHAR2(10);
BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  SELECT TO_CHAR(FEC_CIER_VENT, 'dd/MM/yyyy')
    INTO lsFechaVencimiento
    FROM COB_REPOR_ESTAD_RECUP_COBRA
   WHERE COD_PAIS = psCodPais
     AND COD_PERI = psCodPeriodo
     AND COD_ZONA = pnOidZona
     AND ROWNUM = 1;

   RETURN  lsFechaVencimiento;
EXCEPTION
  WHEN no_data_found THEN
    RETURN '';
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_FECHA_VENCI: '||ls_sqlerrm);
END COM_FN_DEVUE_FECHA_VENCI;

/***************************************************************************
  Descripcion       : Calculo de comision Objetivo Venta
  Fecha Creacion    : 04/07/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
PROCEDURE COM_PR_CALCU_OBJET_VENTA(
       psCodPais      VARCHAR2,
       psCodPeriodo   VARCHAR2,
       psBaseComision VARCHAR2,
       psTipoComision VARCHAR2)
IS
BEGIN
   DELETE FROM COM_TEMPO_CALCU_VENTA_ZONA;
   DELETE FROM COM_TEMPO_CALCU_VENTA_REGIO;
   IF psBaseComision = '04' AND psTipoComision = '01' THEN
      COM_PR_CALCU_VENT_REGIO(psCodPais, psCodPeriodo);
      RETURN;
   END IF;
   IF psBaseComision = '04' AND psTipoComision = '02' THEN
      COM_PR_CALCU_VENT_ZONA(psCodPais, psCodPeriodo);
      RETURN;
   END IF;

   RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_CALCU_OBJET_VENTA: '||ls_sqlerrm);
END COM_PR_CALCU_OBJET_VENTA;


/***************************************************************************
  Descripcion       : Calculo de comision de venta
  Fecha Creacion    : 11/01/2013
  Autor             : Giovanni Ascarza
  ***************************************************************************/

PROCEDURE COM_PR_CALCU_VENT_ZONA(psCodPais   VARCHAR2,
                                 psCodPeriodo   VARCHAR2)
is
  v_oid_camp varchar2(100);
  v_oid_sol_pais varchar2(100);
  TYPE  my_curs_type IS REF CURSOR;
  c1 my_curs_type;
  c2 my_curs_type;

    TYPE rec_result IS RECORD
     (
      cod_regi            zon_regio.cod_regi%type,
      cod_zona            zon_zona.cod_zona%type,
      val_cata            PED_SOLIC_POSIC.val_prec_cata_tota_loca%type,
      val_cata_est        number(12,2)
      );

    TYPE r_result  IS TABLE OF rec_result ;
     v_result1 r_result;
     v_result2 r_result;
     lnSumaRetail  number;
     lnRestaRetail number;
   BEGIN

    select SP.OID_TIPO_SOLI_PAIS
      into v_oid_sol_pais
      from PED_TIPO_SOLIC TS, PED_TIPO_SOLIC_PAIS SP
     WHERE TS.OID_TIPO_SOLI = SP.TSOL_OID_TIPO_SOLI
       AND TS.COD_TIPO_SOLI = 'SOC';

    v_oid_camp := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodPeriodo);

    /* Insertando lista de clientes en tabla temporal */
    INSERT INTO COM_GTT_TABLA_REPOS(CAM_NUME, CAM_CADE)
    SELECT 1, A.CLIE_OID_CLIE
    FROM PED_SOLIC_CABEC A
                WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                AND A.PERD_OID_PERI = v_oid_camp
                AND A.FEC_FACT IS NOT NULL
                GROUP BY A.CLIE_OID_CLIE
                HAVING COUNT(*) > 1;

    INSERT INTO COM_GTT_TABLA_REPOS(CAM_NUME, CAM_CADE)
    SELECT 2, A.CLIE_OID_CLIE
    FROM PED_SOLIC_CABEC A
                          WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                          AND A.PERD_OID_PERI = v_oid_camp
                          AND A.FEC_FACT IS NOT NULL
                          GROUP BY A.CLIE_OID_CLIE
                          HAVING COUNT(*) = 1;

--    Caso 1
    SELECT         zr.COD_REGI,
                   zz.cod_zona,
             sum(sd.val_prec_neto_tota_loca) val_cata,
            (
                select sum(er.val_vene)
                from INT_SAB_VENTA_PREVI_ZONA er
                where er.pais_cod_pais = psCodPais
                and er.cod_peri = psCodPeriodo
                and er.cod_regi = zr.cod_regi
                and er.cod_zona = zz.cod_zona --agregado x requerimiento
            ) val_cata_est
      BULK COLLECT INTO v_result1
      FROM PED_SOLIC_CABEC sc, PED_SOLIC_POSIC sd,
           PRE_OFERT_DETAL PRE,
           PRE_TIPO_OFERT TIP,
           SEG_CANAL C,
           zon_zona zz, zon_regio zr,
           COM_GTT_TABLA_REPOS temp
      WHERE sc.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
      AND sc.PERD_OID_PERI = v_oid_camp
      AND sc.FEC_FACT IS NOT NULL
      AND sc.CLIE_OID_CLIE = temp.cam_cade
      AND temp.cam_nume = 1
      and sc.OID_SOLI_CABE = sd.SOCA_OID_SOLI_CABE
      and sc.ZZON_OID_ZONA = zz.OID_ZONA
      and zz.ZORG_OID_REGI = zr.OID_REGI
      AND SD.OFDE_OID_DETA_OFER = PRE.OID_DETA_OFER
      AND PRE.TOFE_OID_TIPO_OFER = TIP.OID_TIPO_OFER
      AND TIP.CANA_OID_CANA = C.OID_CANA
      AND TIP.VAL_FORM_VENT = '1'
      AND C.COD_CANA = 'VD'
      group by zr.COD_REGI, zz.cod_zona;

      IF v_result1.count > 0 THEN
        FOR i IN v_result1.first .. v_result1.last
        LOOP
      lnSumaRetail := 0.00;

      BEGIN
            select nvl(sum(retd.val_mont_cata * retd.uni_vend),0)
            into lnSumaRetail
            from ret_venta_cabec retc,
                       ret_venta_detal retd,
                       MAE_CLIEN A,
                       MAE_CLIEN_HISTO_ESTAT B,
                       MAE_ESTAT_CLIEN  C
            where retc.cod_pais = psCodPais
              and retc.cam_comi = psCodPeriodo
              and retc.cod_regi = v_result1(i).COD_REGI
              and retc.cod_zona = v_result1(i).cod_zona
              and retc.COD_PAIS = retd.COD_PAIS
              and retc.COD_SBAC = retd.COD_SBAC
              and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
              and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
                    and retc.COD_TIPO_DOCU = 'F'
                    AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
                    AND retc.TIP_CLIE = 'CO'
                    AND retc.VAL_CUEN_CONSU = a.cod_clie
                    AND retc.fec_envi = retd.fec_envi
                    AND A.OID_CLIE = B.CLIE_OID_CLIE
                    AND v_oid_camp >= B.PERD_OID_PERI
                    AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  v_oid_camp <= B.PERD_OID_PERI_PERI_FIN)
                    AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
                    AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );

      EXCEPTION
      WHEN NO_DATA_FOUND THEN
           lnSumaRetail := 0.00;
      END;

      lnRestaRetail := 0.00;
      BEGIN
            select nvl(sum(retd.val_mont_cata * retd.Uni_Devu),0)
            into lnRestaRetail
            from ret_venta_cabec retc,
                       ret_venta_detal retd,
                       MAE_CLIEN A,
                       MAE_CLIEN_HISTO_ESTAT B,
                       MAE_ESTAT_CLIEN  C
            where retc.cod_pais = psCodPais
              and retc.cam_comi = psCodPeriodo
              and retc.cod_regi = v_result1(i).COD_REGI
              and retc.cod_zona = v_result1(i).cod_zona
              and retc.COD_PAIS = retd.COD_PAIS
              and retc.COD_SBAC = retd.COD_SBAC
              and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
              and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
              AND retc.COD_TIPO_DOCU = 'N'
                    AND ( retd.TIPO_TRAN_RET = 'RR' OR retd.TIPO_TRAN_RET = 'RD' )
                    AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
                    AND retc.TIP_CLIE = 'CO'
                    AND retc.VAL_CUEN_CONSU = a.cod_clie
                    AND retc.fec_envi = retd.fec_envi
                    AND A.OID_CLIE = B.CLIE_OID_CLIE
                    AND v_oid_camp >= B.PERD_OID_PERI
                    AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  v_oid_camp <= B.PERD_OID_PERI_PERI_FIN)
                    AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
                    AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
           lnRestaRetail := 0.00;
      END;

      COM_PR_INSER_COMIS_VENT ( psCodPeriodo,
                                v_result1(i).cod_regi,
                                v_result1(i).cod_zona,
                                v_result1(i).val_cata,
                                v_result1(i).val_cata_est,
                                lnSumaRetail - lnRestaRetail);
        END LOOP;

  END IF;

-- Caso 2

SELECT
       zr.COD_REGI,
       zz.cod_zona,
       NVL((select SUM(ac.VAL_MONT_FORM_CATA)
               from ped_solic_cabec_acum2 ac,
                   ped_solic_cabec X,
                   COM_GTT_TABLA_REPOS temp
               where ac.perd_oid_peri = v_oid_camp
               and ac.clie_oid_clie = X.clie_oid_clie
               and X.tspa_oid_tipo_soli_pais = v_oid_sol_pais
               and X.zzon_oid_zona = ZZ.oid_zona
               and X.perd_oid_peri = v_oid_camp
               and X.fec_fact is not null
               and X.clie_oid_clie = temp.cam_cade
               AND temp.cam_nume = 2
       ), 0) VAL_CATA,

                (
                    select sum(er.val_vene)
                    from INT_SAB_VENTA_PREVI_ZONA er
                    where er.pais_cod_pais = psCodPais
                    and er.cod_peri = psCodPeriodo
                    and er.cod_regi = zr.cod_regi
                    and er.cod_zona = zz.cod_zona --agregado x requerimiento
                ) val_cata_est
   BULK COLLECT INTO v_result2
  FROM PED_SOLIC_CABEC sc, zon_zona zz, zon_regio zr,
       COM_GTT_TABLA_REPOS T
  WHERE sc.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
  AND sc.PERD_OID_PERI = v_oid_camp
  AND sc.FEC_FACT IS NOT NULL
  AND sc.CLIE_OID_CLIE = t.cam_cade
  AND t.cam_nume = 2
and sc.ZZON_OID_ZONA = zz.OID_ZONA
and zz.ZORG_OID_REGI = zr.OID_REGI
    group by zr.COD_REGI, ZR.oid_regi,zz.cod_zona, ZZ.oid_zona;

 IF v_result2.count > 0 THEN
        FOR i IN v_result2.first .. v_result2.last
        LOOP
            lnSumaRetail := 0.00;
            BEGIN
                  select nvl(sum(retd.val_mont_cata * retd.uni_vend),0)
                  into lnSumaRetail
                  from ret_venta_cabec retc,
                       ret_venta_detal retd,
                       MAE_CLIEN A,
                       MAE_CLIEN_HISTO_ESTAT B,
                       MAE_ESTAT_CLIEN  C
                  where retc.cod_pais = psCodPais
                    and retc.cam_comi = psCodPeriodo
                    and retc.cod_regi = v_result2(i).COD_REGI
                    and retc.cod_zona = v_result2(i).cod_zona
                    and retc.COD_PAIS = retd.COD_PAIS
                    and retc.COD_SBAC = retd.COD_SBAC
                    and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
                    and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
                    and retc.COD_TIPO_DOCU = 'F'
                    AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
                    AND retc.TIP_CLIE = 'CO'
                    AND retc.VAL_CUEN_CONSU = a.cod_clie
                    AND retc.fec_envi = retd.fec_envi
                    AND A.OID_CLIE = B.CLIE_OID_CLIE
                    AND v_oid_camp >= B.PERD_OID_PERI
                    AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  v_oid_camp <= B.PERD_OID_PERI_PERI_FIN)
                    AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
                    AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );
            EXCEPTION
            WHEN NO_DATA_FOUND THEN
                 lnSumaRetail := 0.00;
            END;

            lnRestaRetail := 0.00;
            BEGIN
                  select nvl(sum(retd.val_mont_cata * retd.Uni_Devu),0)
                  into lnRestaRetail
                  from ret_venta_cabec retc,
                       ret_venta_detal retd,
                       MAE_CLIEN A,
                       MAE_CLIEN_HISTO_ESTAT B,
                       MAE_ESTAT_CLIEN  C
                  where retc.cod_pais = psCodPais
                    and retc.cam_comi = psCodPeriodo
                    and retc.cod_regi = v_result2(i).COD_REGI
                    and retc.cod_zona = v_result2(i).cod_zona
                    and retc.COD_PAIS = retd.COD_PAIS
                    and retc.COD_SBAC = retd.COD_SBAC
                    and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
                    and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
                    AND retc.COD_TIPO_DOCU = 'N'
                    AND ( retd.TIPO_TRAN_RET = 'RR' OR retd.TIPO_TRAN_RET = 'RD' )
                    AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
                    AND retc.TIP_CLIE = 'CO'
                    AND retc.VAL_CUEN_CONSU = a.cod_clie
                    AND retc.fec_envi = retd.fec_envi
                    AND A.OID_CLIE = B.CLIE_OID_CLIE
                    AND v_oid_camp >= B.PERD_OID_PERI
                    AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  v_oid_camp <= B.PERD_OID_PERI_PERI_FIN)
                    AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
                    AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );
            EXCEPTION
            WHEN NO_DATA_FOUND THEN
                 lnRestaRetail := 0.00;
            END;


           COM_PR_INSER_COMIS_VENT (psCodPeriodo,
                                    v_result2(i).cod_regi,
                                    v_result2(i).cod_zona,
                                    v_result2(i).val_cata,
                                    v_result2(i).val_cata_est,
                                    lnSumaRetail - lnRestaRetail);

        END LOOP;
  END IF;


 EXCEPTION
  WHEN OTHERS THEN

    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_CALCU_VENT_ZONA: '||ls_sqlerrm);

   END;

/***************************************************************************
  Descripcion       : Calculo de comision de OBJETIVO venta Region
  Fecha Creacion    : 04/07/2014
  Autor             : Carlos Bazalar
  ***************************************************************************/
PROCEDURE COM_PR_CALCU_VENT_REGIO(psCodPais   VARCHAR2,
                                 psCodPeriodo   VARCHAR2)
is
  v_oid_camp varchar2(100);
  v_oid_sol_pais varchar2(100);
  TYPE  my_curs_type IS REF CURSOR;
  c1 my_curs_type;
  c2 my_curs_type;

    TYPE rec_result IS RECORD
     (
      cod_regi            zon_regio.cod_regi%type,
      val_cata            PED_SOLIC_POSIC.val_prec_cata_tota_loca%type,
      val_cata_est        number(12,2)
      );

    TYPE r_result  IS TABLE OF rec_result ;
     v_result1 r_result;
     v_result2 r_result;
     lnSumaRetail  number;
     lnRestaRetail number;
   BEGIN

    select SP.OID_TIPO_SOLI_PAIS
      into v_oid_sol_pais
      from PED_TIPO_SOLIC TS, PED_TIPO_SOLIC_PAIS SP
     WHERE TS.OID_TIPO_SOLI = SP.TSOL_OID_TIPO_SOLI
       AND TS.COD_TIPO_SOLI = 'SOC';

    v_oid_camp := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodPeriodo);

    /* Insertando lista de clientes en tabla temporal */
    INSERT INTO COM_GTT_TABLA_REPOS(CAM_NUME, CAM_CADE)
    SELECT 1, A.CLIE_OID_CLIE
    FROM PED_SOLIC_CABEC A
                WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                AND A.PERD_OID_PERI = v_oid_camp
                AND A.FEC_FACT IS NOT NULL
                GROUP BY A.CLIE_OID_CLIE
                HAVING COUNT(*) > 1;

    INSERT INTO COM_GTT_TABLA_REPOS(CAM_NUME, CAM_CADE)
    SELECT 2, A.CLIE_OID_CLIE
    FROM PED_SOLIC_CABEC A
                          WHERE A.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
                          AND A.PERD_OID_PERI = v_oid_camp
                          AND A.FEC_FACT IS NOT NULL
                          GROUP BY A.CLIE_OID_CLIE
                          HAVING COUNT(*) = 1;


--    Caso 1
    SELECT         zr.COD_REGI,
             sum(sd.val_prec_neto_tota_loca) val_cata,
            (
                select sum(er.val_vene)
                from INT_SAB_VENTA_PREVI_ZONA er
                where er.pais_cod_pais = psCodPais
                and er.cod_peri = psCodPeriodo
                and er.cod_regi = zr.cod_regi
            ) val_cata_est
      BULK COLLECT INTO v_result1
      FROM PED_SOLIC_CABEC sc, PED_SOLIC_POSIC sd,
           PRE_OFERT_DETAL PRE,
           PRE_TIPO_OFERT TIP,
           SEG_CANAL C,
           zon_zona zz,
           zon_regio zr,
           COM_GTT_TABLA_REPOS temp
      WHERE sc.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
      AND sc.PERD_OID_PERI = v_oid_camp
      AND sc.FEC_FACT IS NOT NULL
      AND sc.CLIE_OID_CLIE = temp.cam_cade
      AND temp.cam_nume = 1
      and sc.OID_SOLI_CABE = sd.SOCA_OID_SOLI_CABE
      and sc.ZZON_OID_ZONA = zz.OID_ZONA
      and zz.ZORG_OID_REGI = zr.OID_REGI
      AND SD.OFDE_OID_DETA_OFER = PRE.OID_DETA_OFER
      AND PRE.TOFE_OID_TIPO_OFER = TIP.OID_TIPO_OFER
      AND TIP.CANA_OID_CANA = C.OID_CANA
      AND TIP.VAL_FORM_VENT = '1'
      AND C.COD_CANA = 'VD'
      group by zr.COD_REGI;

      IF v_result1.count > 0 THEN
        FOR i IN v_result1.first .. v_result1.last
        LOOP
      lnSumaRetail := 0.00;
      BEGIN
            select nvl(sum(retd.val_mont_cata * retd.uni_vend),0)
            into lnSumaRetail
            from ret_venta_cabec retc,
                 ret_venta_detal retd,
                 MAE_CLIEN A,
                 MAE_CLIEN_HISTO_ESTAT B,
                 MAE_ESTAT_CLIEN  C
            where retc.cod_pais = psCodPais
              and retc.cam_comi = psCodPeriodo
              and retc.cod_regi = v_result1(i).COD_REGI
              and retc.COD_PAIS = retd.COD_PAIS
              and retc.COD_SBAC = retd.COD_SBAC
              and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
              and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
              and retc.COD_TIPO_DOCU = 'F'
              AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
              AND retc.TIP_CLIE = 'CO'
              AND retc.VAL_CUEN_CONSU = a.cod_clie
              AND retc.fec_envi = retd.fec_envi
              AND A.OID_CLIE = B.CLIE_OID_CLIE
              AND v_oid_camp >= B.PERD_OID_PERI
              AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  v_oid_camp <= B.PERD_OID_PERI_PERI_FIN)
              AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
              AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
           lnSumaRetail := 0.00;
      END;

      lnRestaRetail := 0.00;
      BEGIN
            select nvl(sum(retd.val_mont_cata * retd.Uni_Devu),0)
            into lnRestaRetail
            from ret_venta_cabec retc,
                 ret_venta_detal retd,
                 MAE_CLIEN A,
                 MAE_CLIEN_HISTO_ESTAT B,
                 MAE_ESTAT_CLIEN  C
            where retc.cod_pais = psCodPais
              and retc.cam_comi = psCodPeriodo
              and retc.cod_regi = v_result1(i).COD_REGI
              and retc.COD_PAIS = retd.COD_PAIS
              and retc.COD_SBAC = retd.COD_SBAC
              and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
              and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
              AND retc.COD_TIPO_DOCU = 'N'
              AND ( retd.TIPO_TRAN_RET = 'RR' OR retd.TIPO_TRAN_RET = 'RD' )
              AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
              AND retc.TIP_CLIE = 'CO'
              AND retc.VAL_CUEN_CONSU = a.cod_clie
              AND retc.fec_envi = retd.fec_envi
              AND A.OID_CLIE = B.CLIE_OID_CLIE
              AND v_oid_camp >= B.PERD_OID_PERI
              AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  v_oid_camp <= B.PERD_OID_PERI_PERI_FIN)
              AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
              AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
           lnRestaRetail := 0.00;
      END;

      COM_PR_INSER_COMIS_VENT_REGIO ( psCodPeriodo,
                                v_result1(i).cod_regi,
                                v_result1(i).val_cata,
                                v_result1(i).val_cata_est,
                                lnSumaRetail - lnRestaRetail);
        END LOOP;

  END IF;

-- Caso 2

SELECT
       zr.COD_REGI,
       NVL((select SUM(ac.VAL_MONT_FORM_CATA)
               from ped_solic_cabec_acum2 ac,
                    ped_solic_cabec X,
                    ZON_ZONA zo,
                    COM_GTT_TABLA_REPOS temp
               where ac.perd_oid_peri = v_oid_camp
               and ac.clie_oid_clie = X.clie_oid_clie
               and X.tspa_oid_tipo_soli_pais = v_oid_sol_pais
               and X.perd_oid_peri = v_oid_camp
               and X.fec_fact is not null
               and X.ZZON_OID_ZONA = ZO.OID_ZONA
                 and ZO.ZORG_OID_REGI = ZR.OID_REGI
               and X.clie_oid_clie = temp.cam_cade
               AND temp.cam_nume = 2
       ), 0) VAL_CATA,

                (
                    select sum(er.val_vene)
                    from INT_SAB_VENTA_PREVI_ZONA er
                    where er.pais_cod_pais = psCodPais
                    and er.cod_peri = psCodPeriodo
                    and er.cod_regi = zr.cod_regi
                ) val_cata_est
   BULK COLLECT INTO v_result2
  FROM PED_SOLIC_CABEC sc, zon_zona zz, zon_regio zr,
       COM_GTT_TABLA_REPOS t
  WHERE sc.TSPA_OID_TIPO_SOLI_PAIS = v_oid_sol_pais
  AND sc.PERD_OID_PERI = v_oid_camp
  AND sc.FEC_FACT IS NOT NULL
  AND sc.CLIE_OID_CLIE = t.cam_cade
  AND t.cam_nume = 2
and sc.ZZON_OID_ZONA = zz.OID_ZONA
and zz.ZORG_OID_REGI = zr.OID_REGI
  group by zr.COD_REGI, ZR.OID_REGI;

 IF v_result2.count > 0 THEN
        FOR i IN v_result2.first .. v_result2.last
        LOOP
            lnSumaRetail := 0.00;
            BEGIN
                  select nvl(sum(retd.val_mont_cata * retd.uni_vend),0)
                  into lnSumaRetail
                  from ret_venta_cabec retc,
                       ret_venta_detal retd,
                       MAE_CLIEN A,
                       MAE_CLIEN_HISTO_ESTAT B,
                       MAE_ESTAT_CLIEN  C
                  where retc.cod_pais = psCodPais
                    and retc.cam_comi = psCodPeriodo
                    and retc.cod_regi = v_result2(i).COD_REGI
                    and retc.COD_PAIS = retd.COD_PAIS
                    and retc.COD_SBAC = retd.COD_SBAC
                    and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
                    and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
                    and retc.COD_TIPO_DOCU = 'F'
                    AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
                    AND retc.TIP_CLIE = 'CO'
                    AND retc.VAL_CUEN_CONSU = a.cod_clie
                    AND retc.fec_envi = retd.fec_envi
                    AND A.OID_CLIE = B.CLIE_OID_CLIE
                    AND v_oid_camp >= B.PERD_OID_PERI
                    AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  v_oid_camp <= B.PERD_OID_PERI_PERI_FIN)
                    AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
                    AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );
            EXCEPTION
            WHEN NO_DATA_FOUND THEN
                 lnSumaRetail := 0.00;
            END;

            lnRestaRetail := 0.00;
            BEGIN
                  select nvl(sum(retd.val_mont_cata * retd.Uni_Devu),0)
                  into lnRestaRetail
                  from ret_venta_cabec retc,
                       ret_venta_detal retd,
                       MAE_CLIEN A,
                       MAE_CLIEN_HISTO_ESTAT B,
                       MAE_ESTAT_CLIEN  C
                  where retc.cod_pais = psCodPais
                    and retc.cam_comi = psCodPeriodo
                    and retc.cod_regi = v_result2(i).COD_REGI
                    and retc.COD_PAIS = retd.COD_PAIS
                    and retc.COD_SBAC = retd.COD_SBAC
                    and retc.COD_TIPO_DOCU = retd.COD_TIPO_DOCU
                    and retc.NUM_DOCU_RETA = retd.NUM_DOCU_RETA
                    AND retc.COD_TIPO_DOCU = 'N'
                    AND ( retd.TIPO_TRAN_RET = 'RR' OR retd.TIPO_TRAN_RET = 'RD' )
                    AND (retc.IND_ANUL <> 'A' OR retc.IND_ANUL IS NULL)
                    AND retc.TIP_CLIE = 'CO'
                    AND retc.VAL_CUEN_CONSU = a.cod_clie
                    AND retc.fec_envi = retd.fec_envi
                    AND A.OID_CLIE = B.CLIE_OID_CLIE
                    AND v_oid_camp >= B.PERD_OID_PERI
                    AND (B.PERD_OID_PERI_PERI_FIN IS NULL OR  v_oid_camp <= B.PERD_OID_PERI_PERI_FIN)
                    AND B.ESTA_OID_ESTA_CLIE = C.OID_ESTA_CLIE
                    AND C.COD_ESTA_CLIE IN ( '02', '03', '04', '06', '08' );
            EXCEPTION
            WHEN NO_DATA_FOUND THEN
                 lnRestaRetail := 0.00;
            END;


           COM_PR_INSER_COMIS_VENT_REGIO (psCodPeriodo,
                                    v_result2(i).cod_regi,
                                    v_result2(i).val_cata,
                                    v_result2(i).val_cata_est,
                                    lnSumaRetail - lnRestaRetail);

        END LOOP;
  END IF;


 EXCEPTION
  WHEN OTHERS THEN

    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_CALCU_VENT_REGIO: '||ls_sqlerrm);

  END COM_PR_CALCU_VENT_REGIO;

/***************************************************************************
  Descripcion       : Insercion de comision de venta
  Fecha Creacion    : 11/01/2013
  Autor             : Giovanni Ascarza
  ***************************************************************************/

  PROCEDURE COM_PR_INSER_COMIS_VENT ( pscodPeri        varchar2,
                                      pscodRegi        VARCHAR2,
                                      pscodZona        VARCHAR2,
                                      pnvalCata        number ,
                                      pnvalCataEst     number,
                                      pnvalRetail number) is

  lnValor NUMBER;
begin
   BEGIN
      insert into COM_TEMPO_CALCU_VENTA_ZONA(
                  cod_peri,
                  cod_regi,
                  cod_zona,
                  val_cata,
                  val_cata_est,
                  val_vent_reta
                  )
                  values(
                  pscodPeri,
                  pscodRegi,
                  pscodZona,
                  pnvalCata,
                  pnvalCataEst,
                  pnvalRetail);

   EXCEPTION
   WHEN DUP_VAL_ON_INDEX THEN
        UPDATE COM_TEMPO_CALCU_VENTA_ZONA
        SET val_cata = val_cata + pnvalCata,
            val_vent_reta = pnvalRetail
        WHERE cod_peri = pscodPeri
        AND cod_regi =  pscodRegi
        AND cod_zona = pscodZona ;
   END;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_INSER_COMIS_VENT: '||ls_sqlerrm);

END;

/***************************************************************************
  Descripcion       : Insercion de comision de OBJETIVO venta Region
  Fecha Creacion    : 04/07/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_INSER_COMIS_VENT_REGIO (
                                      pscodPeri        varchar2,
                                      pscodRegi        VARCHAR2,
                                      pnvalCata        number ,
                                      pnvalCataEst     number,
                                      pnvalRetail number) is

  lnValor NUMBER;
begin
   BEGIN
      insert into COM_TEMPO_CALCU_VENTA_REGIO(
                  cod_peri,
                  cod_regi,
                  val_cata,
                  val_cata_est,
                  val_vent_reta
                  )
                  values(
                  pscodPeri,
                  pscodRegi,
                  pnvalCata,
                  pnvalCataEst,
                  pnvalRetail);

   EXCEPTION
   WHEN DUP_VAL_ON_INDEX THEN
        UPDATE COM_TEMPO_CALCU_VENTA_REGIO
        SET val_cata = val_cata + pnvalCata,
            val_vent_reta = pnvalRetail
        WHERE cod_peri = pscodPeri
        AND cod_regi =  pscodRegi ;
   END;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_INSER_COMIS_VENT_REGIO: '||ls_sqlerrm);

END COM_PR_INSER_COMIS_VENT_REGIO;

/***************************************************************************
  Descripcion       : Calculo de comision de venta para Gerente de Zona
  Fecha Creacion    : 21/02/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIS_VENT_ZONA (
                                      pnOidPais      NUMBER,
                                      psCodPeriodo   VARCHAR2,
                                      psCodComision  VARCHAR2,
                                      oidCampana     VARCHAR2)
  is
  v_por_comi           COM_COMIS_ESCAL.Por_Comi%type := 0;
  v_porcentaje         number(12,2);
  v_mon_fijo_comi      COM_COMIS_ESCAL.Por_Comi%type := 0;
  v_comi_obtenida      COM_COMIS_ESCAL.Por_Comi%type := 0;

  v_gerente varchar2(100) := ' ';
  v_nombre_geren varchar2(100) := ' ';
  v_conta number(12,2) := 0;

  CURSOR C_TEMPORAL  IS
        SELECT X.COD_REGI,
               X.COD_ZONA,
               X.VAL_CATA,
               X.VAL_CATA_EST,
               X.VAL_VENT_RETA
        FROM COM_TEMPO_CALCU_VENTA_ZONA X;

  TYPE temporalRec IS RECORD(
                  codRegi         COM_TEMPO_CALCU_VENTA_ZONA.COD_REGI%TYPE,
                  codZona         COM_TEMPO_CALCU_VENTA_ZONA.COD_ZONA%TYPE,
                  valCata         COM_TEMPO_CALCU_VENTA_ZONA.VAL_CATA%TYPE,
                  valCataEst      COM_TEMPO_CALCU_VENTA_ZONA.VAL_CATA_EST%TYPE,
                  valVentReta     COM_TEMPO_CALCU_VENTA_ZONA.VAL_VENT_RETA%TYPE
                 );

  TYPE temporalTab IS TABLE OF temporalRec;
  temporalRecord temporalTab;

begin

  OPEN C_TEMPORAL;
  LOOP
     FETCH C_TEMPORAL BULK COLLECT INTO temporalRecord LIMIT W_FILAS;
     IF temporalRecord.COUNT > 0 THEN
        FOR i IN temporalRecord.FIRST .. temporalRecord.LAST LOOP
            v_porcentaje := ((temporalRecord(i).valCata + temporalRecord(i).valVentReta)/ temporalRecord(i).valCataEst)*100;
            if v_porcentaje != 0 then
                BEGIN
                  select cce.POR_COMI
                    into v_por_comi
                    from com_comis com, COM_COMIS_COBRA cco,
                         COM_COMIS_ESCAL cce,
                         COM_COMIS_PERIO_CALCU_ZONA cal
                   where com.OID_COMI = cco.COMI_OID_COMI
                     and cco.OID_COMI_COBR = cce.OID_COMI_COBR
                     and com.COD_COMI = psCodComision
                     and cal.peri_cod_peri = psCodPeriodo
                     and cal.cod_comi = psCodComision
                     and cal.cod_regi = temporalRecord(i).codRegi
                     and cal.cod_zona = temporalRecord(i).codZona

                     and cce.POR_RECU_INIC <= cal.por_recu
                     and cce.POR_RECU_FINA >= cal.por_recu
                     and cce.por_obje_inic <= v_porcentaje
                     and cce.por_obje_fina >= v_porcentaje;

                EXCEPTION
                WHEN OTHERS THEN
                  v_por_comi := 0;
                END;
            end if;

            UPDATE COM_COMIS_PERIO_CALCU_ZONA A
            SET mon_cata_real= temporalRecord(i).valCata ,
                mon_cata_esti= temporalRecord(i).valCataEst ,
                por_mon_cata= v_porcentaje,
                IMP_COMI_TRA1 = A.IMP_MONT_PAGO_COMI * v_por_comi / 100,
                VAL_PORC_COMI_TRA1 = v_por_comi,
                MON_VENT_RETL = temporalRecord(i).valVentReta
            WHERE A.PERI_COD_PERI = psCodPeriodo
              AND A.COD_COMI = psCodComision
              AND A.COD_REGI = temporalRecord(i).codRegi
              AND A.COD_ZONA = temporalRecord(i).codZona;


        END LOOP;
     END IF;

     EXIT WHEN C_TEMPORAL%NOTFOUND;
  END LOOP;
  CLOSE C_TEMPORAL;


  END COM_PR_CALCU_COMIS_VENT_ZONA;


 /***************************************************************************
  Descripcion       : Calculo de comision de Objetivo venta para Gerente de Region
  Fecha Creacion    : 05/07/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE COM_PR_CALCU_COMIS_VENT_REGIO (
                                      pnOidPais      NUMBER,
                                      psCodPeriodo   VARCHAR2,
                                      psCodComision  VARCHAR2,
                                      oidCampana     VARCHAR2)
  is
  v_por_comi           COM_COMIS_ESCAL.Por_Comi%type := 0;
  v_porcentaje         number(12,2);
  v_mon_fijo_comi      COM_COMIS_ESCAL.Por_Comi%type := 0;
  v_comi_obtenida      COM_COMIS_ESCAL.Por_Comi%type := 0;

  v_gerente varchar2(100) := ' ';
  v_nombre_geren varchar2(100) := ' ';
  v_conta number(12,2) := 0;

  CURSOR C_TEMPORAL  IS
        SELECT X.COD_REGI,
               X.VAL_CATA,
               X.VAL_CATA_EST,
               X.VAL_VENT_RETA
        FROM COM_TEMPO_CALCU_VENTA_REGIO X;

  TYPE temporalRec IS RECORD(
                  codRegi         COM_TEMPO_CALCU_VENTA_ZONA.COD_REGI%TYPE,
                  valCata         COM_TEMPO_CALCU_VENTA_ZONA.VAL_CATA%TYPE,
                  valCataEst      COM_TEMPO_CALCU_VENTA_ZONA.VAL_CATA_EST%TYPE,
                  valVentReta     COM_TEMPO_CALCU_VENTA_ZONA.VAL_VENT_RETA%TYPE
                 );

  TYPE temporalTab IS TABLE OF temporalRec;
  temporalRecord temporalTab;

begin

  OPEN C_TEMPORAL;
  LOOP
     FETCH C_TEMPORAL BULK COLLECT INTO temporalRecord LIMIT W_FILAS;
     IF temporalRecord.COUNT > 0 THEN
        FOR i IN temporalRecord.FIRST .. temporalRecord.LAST LOOP
            v_porcentaje := ((temporalRecord(i).valCata + temporalRecord(i).valVentReta)/ temporalRecord(i).valCataEst)*100;
            if v_porcentaje != 0 then
                BEGIN
                  select cce.POR_COMI
                    into v_por_comi
                    from com_comis com, COM_COMIS_COBRA cco,
                         COM_COMIS_ESCAL cce,
                         COM_COMIS_PERIO_CALCU_REGIO cal
                   where com.OID_COMI = cco.COMI_OID_COMI
                     and cco.OID_COMI_COBR = cce.OID_COMI_COBR
                     and com.COD_COMI = psCodComision
                     and cal.cod_peri = psCodPeriodo
                     and cal.cod_comi = psCodComision
                     and cal.cod_regi = temporalRecord(i).codRegi

                     and cce.POR_RECU_INIC <= cal.POR_RECU_TRA1
                     and cce.POR_RECU_FINA >= cal.POR_RECU_TRA1
                     and cce.por_obje_inic <= v_porcentaje
                     and cce.por_obje_fina >= v_porcentaje;

                EXCEPTION
                WHEN OTHERS THEN
                  v_por_comi := 0;
                END;
            end if;

            UPDATE COM_COMIS_PERIO_CALCU_REGIO A
            SET mon_cata_real= temporalRecord(i).valCata ,
                mon_cata_esti= temporalRecord(i).valCataEst ,
                por_mon_cata= v_porcentaje,
                IMP_MONT_COM1 = A.IMP_MONT_PAGO_COMI * v_por_comi / 100,
                POR_COMI_TRA1 = v_por_comi,
                MON_VENT_RETL = temporalRecord(i).valVentReta
            WHERE A.COD_PERI = psCodPeriodo
              AND A.COD_COMI = psCodComision
              AND A.COD_REGI = temporalRecord(i).codRegi;


        END LOOP;
     END IF;

     EXIT WHEN C_TEMPORAL%NOTFOUND;
  END LOOP;
  CLOSE C_TEMPORAL;
 END COM_PR_CALCU_COMIS_VENT_REGIO;


/***************************************************************************
  Descripcion       : Actualiza Comision de Venta Retail a partir del Cierre
                      de Campaña
  Fecha Creacion    : 10/06/2013
  Autor             : Carlos Bazalar
  ***************************************************************************/
PROCEDURE COM_PR_ACTUA_COMIS_VENTA_RETAI (
        psCodPais      VARCHAR2,
        psCodPeriodo   VARCHAR2)
IS
  /* Declaracion de Variables */
  lnIdPais                SEG_PAIS.OID_PAIS%TYPE;
  lnIdCanal               SEG_CANAL.OID_CANA%TYPE;
  lnIdCanalRT             SEG_CANAL.OID_CANA%TYPE;
  lnIdMarca               SEG_MARCA.OID_MARC%TYPE;
  lnIdPeriActual          CRA_PERIO.OID_PERI%TYPE;

  lsCodPeriAnterior     SEG_PERIO_CORPO.COD_PERI%TYPE;
  ldFechaFinPeriodo     DATE;
  ldFechaIniPeriodo     DATE;
  lnValor               NUMBER;
  lsMensaje             VARCHAR2(1000);
  lnIdUnidadAdm         NUMBER;
  ldFechaCierre         DATE;
  lbActualizar          BOOLEAN;
  lsCocClienAnte        RET_VENTA_CABEC.VAL_CUEN_CONSU%TYPE;
  lsCodZona             ZON_ZONA.COD_ZONA%TYPE;
  lsCodRegi             ZON_REGIO.COD_REGI%TYPE;
  lsCampannaGrabar      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnIdPeriAnterior      NUMBER;

  CURSOR cursorRetail(pnIdPais number, pnidCanalRT number, pdFechaIni DATE, pdFechaFin DATE)
  IS
  SELECT
     A.VAL_CUEN_CONSU, C.OID_CLIE,
     A.COD_SBAC, A.COD_TIPO_DOCU, A.NUM_DOCU_RETA,
     A.FEC_ENVI
  FROM
     RET_VENTA_CABEC  A,
     SEG_ACCES B,
     MAE_CLIEN C
  WHERE A.COD_PAIS = psCodPais
   AND A.FEC_ENVI >= pdFechaIni
   AND A.FEC_ENVI <= pdFechaFin
   AND A.TIP_CLIE = 'CO'
   AND  ( A.IND_ANUL <> 'A'  OR  A.IND_ANUL is null )
   AND A.COD_ACCE = B.COD_ACCE
   AND B.CANA_OID_CANA = pnidCanalRT
   AND B.IND_COMI_RETL ='1'
   AND c.pais_oid_pais = pnIdPais
   AND C.COD_CLIE = A.VAL_CUEN_CONSU
  -- AND C.COD_CLIE = '030171136'
  ORDER BY A.VAL_CUEN_CONSU;

  TYPE regRetail IS RECORD (
     VAL_CUEN_CONSU         RET_VENTA_CABEC.VAL_CUEN_CONSU%TYPE,
     OID_CLIE               MAE_CLIEN.OID_CLIE%TYPE,
     COD_SBAC               RET_VENTA_CABEC.COD_SBAC%TYPE,
     COD_TIPO_DOCU          RET_VENTA_CABEC.COD_TIPO_DOCU%TYPE,
     NUM_DOCU_RETA          RET_VENTA_CABEC.NUM_DOCU_RETA%TYPE,
     FEC_ENVI               RET_VENTA_CABEC.FEC_ENVI%TYPE
  );

  TYPE tablaRetail IS TABLE OF regRetail;
  tablaRegistro tablaRetail;
  regRegistro   regRetail;

BEGIN
    /* Obteniendo oids */
  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
  lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
  lnIdPeriActual := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);

  /* Obteniendo campa¿a de descuento */
  lsCodPeriAnterior  := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
  lnIdCanalRT    := gen_pkg_gener.gen_fn_devuelve_id_canal('RT');
  lnIdPeriAnterior := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriAnterior, lnIdMarca, lnIdCanal);

  /* Obteniendo fecha inicial y fecha final del periodo del proceso */
  lnValor := COM_FN_DEVUE_RANGO_FECHA_PERIO(lsCodPeriAnterior, lnIdPais, lnIdMarca, lnIdCanal, ldFechaIniPeriodo, ldFechaFinPeriodo, lsMensaje);
  IF lnValor = -1 THEN
     RAISE_APPLICATION_ERROR(-20123, lsMensaje);
  END IF;

  ------------------------------------------------------------
  /* Inicio LOOP */
  lsCocClienAnte := '-1';
  lbActualizar := FALSE;

  OPEN cursorRetail(lnIdPais, lnIdCanalRT, ldFechaIniPeriodo, ldFechaFinPeriodo);
  LOOP
      FETCH cursorRetail BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
      IF tablaRegistro.COUNT > 0 THEN
        FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
           regRegistro := tablaRegistro(x);
           lbActualizar := TRUE;

           IF regRegistro.VAL_CUEN_CONSU <> lsCocClienAnte THEN
               lsCocClienAnte := regRegistro.VAL_CUEN_CONSU;
               lnIdUnidadAdm  := COM_FN_DEVUE_OID_UNADM_HISTO(regRegistro.OID_CLIE, lsCodPeriAnterior);
               IF lnIdUnidadAdm != -1 THEN
                  BEGIN
                     SELECT G.FEC_CIER, C.COD_ZONA, B.COD_REGI
                     INTO ldFechaCierre, lsCodZona, lsCodRegi
                     FROM
                        MAE_CLIEN_UNIDA_ADMIN Z,
                        ZON_TERRI_ADMIN A,
                        ZON_REGIO B,
                        ZON_ZONA C,
                        ZON_SECCI D,
                        FAC_CONTR_CIERR G,
                        FAC_TIPOS_CIERR H
                      WHERE Z.OID_CLIE_UNID_ADMI = lnIdUnidadAdm
                        AND Z.ZTAD_OID_TERR_ADMI = A.OID_TERR_ADMI
                        AND A.ZSCC_OID_SECC = D.OID_SECC
                        AND D.ZZON_OID_ZONA = C.OID_ZONA
                        AND C.ZORG_OID_REGI = B.OID_REGI

                        AND B.OID_REGI = G.ZORG_OID_REGI
                        AND G.PERD_OID_PERI = lnIdPeriAnterior
                        AND G.TCIE_OID_TIPO_CIER = H.OID_TIPO_CIER
                        AND H.COD_TIPO_CIER = 'R'
                        AND rownum = 1;
                  EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                     lbActualizar := FALSE;
                  END;
               ELSE
                  lbActualizar := FALSE;
               END IF;
           END IF;
           IF lbActualizar THEN
              IF regRegistro.FEC_ENVI <= ldFechaCierre THEN
                lsCampannaGrabar := lsCodPeriAnterior;
              ELSE
                lsCampannaGrabar := psCodPeriodo;
              END IF;

              UPDATE RET_VENTA_CABEC A
              SET A.COD_ZONA = lsCodZona,
                  A.COD_REGI = lsCodRegi,
                  A.CAM_COMI = lsCampannaGrabar
              WHERE COD_PAIS = psCodPais
                AND COD_SBAC = regRegistro.COD_SBAC
                AND COD_TIPO_DOCU = regRegistro.COD_TIPO_DOCU
                AND NUM_DOCU_RETA = regRegistro.NUM_DOCU_RETA;
           END IF;
         END LOOP;
      END IF;
      EXIT WHEN cursorRetail%NOTFOUND;
  END LOOP;
  CLOSE cursorRetail;

END COM_PR_ACTUA_COMIS_VENTA_RETAI;

/**************************************************************************
Descripcion     : Devuelve si existen Registros Calculados para Comision de
                  Base 07
Fecha Creacion  : 12/11/2013
Autor           : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_DEVUE_REGIS_CALCU_BAS07(
   psCodPeri         VARCHAR2,
   psTipoComis       VARCHAR2,
   psCodComi         VARCHAR2,
   psCodClie         VARCHAR2)
RETURN VARCHAR2
IS
   lsRetorno   VARCHAR2(20);
   lnContador  NUMBER;
BEGIN
   lsRetorno := 'PENDIENTE';
   IF psTipoComis = 'GZ' THEN
       SELECT COUNT(1)
       INTO lnContador
       FROM com_comis_perio_calcu_zona x
       WHERE x.peri_cod_peri = psCodPeri
         AND x.cod_comi = psCodComi
         AND x.cod_lide_zona = psCodClie;
   ELSIF psTipoComis = 'GR' THEN
      SELECT COUNT(1)
       INTO lnContador
       FROM com_comis_perio_calcu_regio x
       WHERE x.cod_peri = psCodPeri
         AND x.cod_comi = psCodComi
         AND x.cod_lide_regi = psCodClie;
   END IF;
   IF lnContador > 0 THEN
      lsRetorno := 'CALCULADA';
   END IF;
   RETURN lsRetorno;
EXCEPTION
WHEN no_data_found THEN
   RETURN  lsRetorno;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_DEVUE_REGIS_CALCU_BAS07: '||ls_sqlerrm);
END COM_FN_DEVUE_REGIS_CALCU_BAS07;


/**************************************************************************
Descripcion     : Realiza la Eliminacion de Registros de Gerentes Retiradas
                  para Comision de Base 07
Fecha Creacion  : 12/11/2013
Autor           : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_ELIMI_COMIS_GEREN_RETIR(
   psCodPeriodo   VARCHAR2,
   psCodigoRegion  VARCHAR2,
   psCodigoZona    VARCHAR2,
   psCodigoCliente   VARCHAR2,
   psCodigoComision   VARCHAR2,
   psTipoComision   VARCHAR2
)
IS
   lsRetorno   VARCHAR2(20);
   lnContador  NUMBER;
BEGIN

   IF psTipoComision = '01' THEN --antes Gr
        /* Borrando tabla temporal de Comision por Recuperacion */
        DELETE FROM COM_REPOR_COMIS_RECUP A
        WHERE A.COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodigoComision
          AND A.COD_LIDE_REGI = psCodigoCliente;

        /* Borrar  informaci?n en la tabla COM_COMIS_PERIO_CALCU_REGIO */
        DELETE FROM COM_COMIS_PERIO_CALCU_REGIO
        WHERE COD_PERI = psCodPeriodo
          AND COD_COMI = psCodigoComision
          AND COD_LIDE_REGI = psCodigoCliente;
    END IF;

    IF psTipoComision = '02' THEN --antes G
        /* Borrando tabla temporal de Comision por Recuperacion */
        DELETE FROM COM_REPOR_COMIS_RECUP A
        WHERE A.COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodigoComision
          AND A.COD_LIDE_ZONA = psCodigoCliente;

        /* Borrando informaci?n en la tabla COM_COMIS_PERI_CALCU_ZONA */
        DELETE FROM COM_COMIS_PERIO_CALCU_ZONA
        WHERE PERI_COD_PERI = psCodPeriodo
          AND COD_COMI = psCodigoComision
          AND COD_LIDE_ZONA = psCodigoCliente;
    END IF;

    /* Borrando informaci?n en la tabla COM_COMIS_DSCTO */
    DELETE FROM COM_COMIS_DSCTO
    WHERE COD_PERI = psCodPeriodo
      AND COD_COMI = psCodigoComision
      AND COD_CLIE_COMI = psCodigoCliente;

    /* Borrando informaci?n en las tablas COM_RECUP_CAMPA_ZONA y COM_RECUP_CAMPA_SECCI */
    IF psTipoComision = '01' THEN --antes Gr
        DELETE FROM COM_RECUP_CAMPA_ZONA A
        WHERE A.COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodigoComision
          AND A.COD_REGI = psCodigoRegion;

        DELETE FROM COM_RECUP_CAMPA_SECCI A
        WHERE A.COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodigoComision
          AND A.COD_REGI = psCodigoRegion;
    END IF;
    IF psTipoComision = '02' THEN --antes Gr
        DELETE FROM COM_RECUP_CAMPA_ZONA A
        WHERE A.COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodigoComision
          AND A.COD_REGI = psCodigoRegion
          AND A.COD_ZONA = psCodigoZona;

        DELETE FROM COM_RECUP_CAMPA_SECCI A
        WHERE A.COD_PERI = psCodPeriodo
          AND A.COD_COMI = psCodigoComision
          AND A.COD_REGI = psCodigoRegion
          AND A.COD_ZONA = psCodigoZona;
    END IF;

    DELETE FROM COM_RECUP_CAMPA_REGIO A
    WHERE A.COD_PERI = psCodPeriodo
      AND A.COD_COMI = psCodigoComision
      AND A.COD_REGI = psCodigoRegion;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_ELIMI_COMIS_GEREN_RETIR: '||ls_sqlerrm);
END COM_PR_ELIMI_COMIS_GEREN_RETIR;


END COM_PKG_REPOR;
/
