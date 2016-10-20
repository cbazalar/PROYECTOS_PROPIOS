CREATE OR REPLACE PACKAGE "PER_PKG_REPOR_PERCE" IS
   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;
   TYPE TABLA_PER_MOVIM_BANCA_CABEC IS TABLE OF
        PER_MOVIM_BANCA_CABEC%ROWTYPE;
   TYPE tRegCodigoPeriodo IS RECORD (
     COD_PERI    SEG_PERIO_CORPO.COD_PERI%TYPE
   );
   TYPE tRegCodigoPeriodoOid IS RECORD (
     OID_PERI    CRA_PERIO.OID_PERI%TYPE
   );
   TYPE TABLA_CODIGO_PERIODO IS TABLE OF tRegCodigoPeriodo;
   TYPE TABLA_CODIGO_PERIODO_OID IS TABLE OF tRegCodigoPeriodoOid;
   TYPE TABLA_CODIGO_ZONA IS TABLE OF
        ZON_ZONA.COD_ZONA%TYPE;
   TYPE tRegClienteUnidadAdm IS RECORD (
     OID_CLIE     MAE_CLIEN.OID_CLIE%TYPE,
     VAL_NOM1     MAE_CLIEN.VAL_NOM1%TYPE,
     VAL_NOM2     MAE_CLIEN.VAL_NOM2%TYPE,
     VAL_APE1     MAE_CLIEN.VAL_APE1%TYPE,
     VAL_APE2     MAE_CLIEN.VAL_APE2%TYPE,
     COD_CLIE     MAE_CLIEN.COD_CLIE%TYPE,
     COD_REGI     ZON_REGIO.COD_REGI%TYPE,
     COD_ZONA     ZON_ZONA.COD_ZONA%TYPE,
     COD_SECC     ZON_SECCI.COD_SECC%TYPE,
     COD_TERR     ZON_TERRI.COD_TERR%TYPE
   );
   TYPE tablaClienteUnidadAdm IS TABLE OF tRegClienteUnidadAdm;
   TYPE tRegFechaFacturacion IS RECORD (
     COD_ZONA     ZON_ZONA.COD_ZONA%TYPE,
     FEC_INIC     CRA_CRONO.FEC_INIC%TYPE
   );
   TYPE tablaFechaFacturacion IS TABLE OF tRegFechaFacturacion;
   TYPE tablaFacturaPend IS TABLE OF PER_REPOR_FACTU_PENDI_SECCI%ROWTYPE;
   TYPE tRegControlAsis IS RECORD (
      id        ROWID,
      OID_CLIE  PER_REPOR_CNTRL_ASIST_CONFE.OID_CLIE%TYPE,
      COD_CLIE  PER_REPOR_CNTRL_ASIST_CONFE.COD_CLIE%TYPE,
      NOM_CLIE  PER_REPOR_CNTRL_ASIST_CONFE.NOM_CLIE%TYPE,
      COD_ZONA  PER_REPOR_CNTRL_ASIST_CONFE.COD_ZONA%TYPE,
      FEC_FACT  PER_REPOR_CNTRL_ASIST_CONFE.FEC_FACT%TYPE,
      COD_SECC  PER_REPOR_CNTRL_ASIST_CONFE.COD_SECC%TYPE,
      COD_TERR  PER_REPOR_CNTRL_ASIST_CONFE.COD_TERR%TYPE,
      NOM_GERE  PER_REPOR_CNTRL_ASIST_CONFE.NOM_GERE%TYPE,
      SAL_UNIC  PER_REPOR_CNTRL_ASIST_CONFE.SAL_UNIC%TYPE,
      SAL_PAGO_MIN_FLX  PER_REPOR_CNTRL_ASIST_CONFE.SAL_PAGO_MIN_FLX%TYPE,
      VAL_OBSE  PER_REPOR_CNTRL_ASIST_CONFE.VAL_OBSE%TYPE
   );
   TYPE tablaControlAsis IS TABLE OF tRegControlAsis;
   TYPE tablaEstadoCuenta IS TABLE OF PER_REPOR_ESTAD_CUENT_CORRI%ROWTYPE;
   /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   W_FILAS      NUMBER:=1000;
/* Declaracion de Funciones */
/***************************************************************************
Descripcion       : Devuelve Lista de Clientes por Unidad Administrativa
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_CLIEN_UNIAD(psCodPais VARCHAR2, psCodMarca VARCHAR2, psCodCanal VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL,
    psCodSeccion VARCHAR2:=NULL,
    psCodTerri   VARCHAR2:=NULL)
RETURN  tablaClienteUnidadAdm PIPELINED;
/***************************************************************************
Descripcion       : Devuelve Lista de Clientes por Unidad Administrativa
                    confrontando con PER_CUENT_CORRI_DOCLE
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_CLIEN_UNIAD_DOCLE(
    psCodPais VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL,
    psCodSeccion VARCHAR2:=NULL,
    psCodTerri   VARCHAR2:=NULL)
RETURN  tablaClienteUnidadAdm PIPELINED;
/***************************************************************************
Descripcion       : Devuelve montos x vencimiento
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_RECUP_MONTO_VENCI(poidCliente NUMBER,
    pdFechaInicio  DATE:=NULL,
    pdFechaFin     DATE:=NULL,
    psPeriodo      VARCHAR2:=NULL)
RETURN  NUMBER;

/***************************************************************************
Descripcion       : Recupera Fechas de Facturacion
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_RECUP_FECHA_FACTU(psCodPais VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodigoZona VARCHAR2,
    psCodPeriodo VARCHAR2)
RETURN DATE;

/***************************************************************************
Descripcion       : Obtiene periodo en base al codigo de periodo base y numero
                    de registro
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PERIO(
   psCodPeriodo VARCHAR2,
   pnOidPais  NUMBER,
   pnOidMarca NUMBER,
   pnOidCanal NUMBER,
   valorRegistro NUMBER)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Obtiene periodo en base al codigo de periodo base y numero
                    de registro
Fecha Creacion    : 26/06/2014
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PERIO(
   psCodPeriodo VARCHAR2,
   psCodigoPais VARCHAR2,
   valorRegistro NUMBER)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo + saldo1 + pago_diferido + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTAL_DEUDORAS
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona que cumplen
                    con: saldo + saldo1 + pago_diferido + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTAL_DEUDORAS
  ( psCodZona      VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con:
                    saldo + pago_diferido > 0 Y
                    saldo1 + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PED_RESP
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona  que cumplen
                    con:
                    saldo + pago_diferido > 0 Y
                    saldo1 + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PED_RESP
  ( psCodZona      VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma
                         saldo + pago_diferido +
                         saldo1 + pago_diferido1
                    por zona y seccion
                    para cuando
                    saldo + pago_diferido > 0 y
                    saldo1 + pago_diferido1 > 0
Fecha Creacion    : 04/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_SUMA_RESP
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma
                         saldo + pago_diferido +
                         saldo1 + pago_diferido1
                    por zona
                    para cuando
                    saldo + pago_diferido > 0 y
                    saldo1 + pago_diferido1 > 0
Fecha Creacion    : 04/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_SUMA_RESP
  ( psCodZona      VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo + pago_diferido > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo1 + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS1
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo2 + pago_diferido2 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS2
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo3 + pago_diferido3 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS3
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo4 + pago_diferido4 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS4
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Cargo Directo. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_CARG
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Cargo Directo. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_ABON
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla CCC_MOVIM_CUENT_CORRI
                    para calcular el Total Percepcion Movimiento.
Fecha Creacion    : 10/05/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_PERCE_CCCMO
(        psOidClie  NUMBER,
         fechaVencimiento DATE,
         fechaVencimientoMenos1 DATE
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Obtiene el monto Total de Boleta CDR
Fecha Creacion    : 11/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_BOLE_CDR
(        psOidCliente NUMBER,
         pdFechaInicial DATE,
         pdFechaFinal DATE
         )
RETURN NUMBER ;
/***************************************************************************
Descripcion       : Obtiene el monto Total de Boleta CDR
                    Con codigo de proceso CCC001 - CCC003
Fecha Creacion    : 14/06/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_BOLE_CDR2
(        psOidCliente NUMBER,
         pdFechaInicial DATE,
         pdFechaFinal date
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla CCC_MOVIM_CUENT_CORRI
                    para calcular el Total Percepcion.
Fecha Creacion    : 10/05/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_PERCE_CCCPE
(        psOidClie  NUMBER,
         fechaVencimiento DATE,
         fechaVencimientoMenos1 DATE
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla CCC_MOVIM_CUENT_CORRI
                    para calcular el Total Percepcion Pendiente.
Fecha Creacion    : 10/05/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_PERCE_CCC
(        psOidClie  NUMBER,
         psOidPeriodo VARCHAR2
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Saldo Pendiente. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_SALDO_PEND
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER;

/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla temporal de movimientos cuenta
                    corriente para unas determinadas fechas de vencimiento
Fecha Creacion    : 11/06/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_IMPO_PEND
(        psOidCliente NUMBER,
         pdFechaVen1 DATE,
         pdFechaVen DATE
         )
RETURN NUMBER;

/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Cargo Facturado. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_CARGO_FACTU
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Cargo Directo. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_CARGO_DIREC
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular la Nota de Credito. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_NOTA_CREDI
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Obtiene el numero de boleta para un cliente en un periodo
                   determinado
Fecha Creacion    : 11/06/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_NUME_BOLE
(        psOiodPeri NUMBER,
         psOidCliente NUMBER
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Abono Directo. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_ABON_DIREC
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular los Incobrables. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_INCOB
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER;
/***************************************************************************
Descripcion       : Obtiene Factura
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_OBTIE_FACTU(
   pnOidCliente NUMBER,
   psCodPeri    VARCHAR2,
   pnOidPais    NUMBER,
   pnOidMarca   NUMBER,
   pnOidCanal   NUMBER,
   pdFechaFac   OUT DATE,
   pnValNume    OUT NUMBER
   );
/***************************************************************************
Descripcion       : Genera data para Reporte Facturas Pendientes x Seccion
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_FACTU_PENDI(psCodPais VARCHAR2,
    psCodMarca      VARCHAR2,
    psCodCanal      VARCHAR2,
    psCodPeriodo    VARCHAR2,
    psCodRegion     VARCHAR2,
    psTipoReporte   VARCHAR2,
    psCodZona       VARCHAR2:=NULL,
    psCodSeccion    VARCHAR2:=NULL,
    psCodTerritorio VARCHAR2:=NULL
    );
/***************************************************************************
Descripcion       : Genera data para Reporte Control de Asistencia
Fecha Creacion    : 14/11/2006
   psSeleccion    : 0 <Todos>
                    1 <Activas>
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_CONTR_ASIST(psCodPais VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2,
    psCodZona    VARCHAR2,
    psSeleccion  VARCHAR2,
    psOidProceso VARCHAR2);
/***************************************************************************
Descripcion       : Devuelve Lista de Periodos de acuerdo
                    al tipo de Seleccion
                    'C' : Completo
                    'U' : Ultimos 4 registros
Fecha Creacion    : 06/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_LISTA_PERIO
   (psCodPais   VARCHAR2,
    psCodMarca  VARCHAR2,
    psCodCanal  VARCHAR2,
    psCodPeriodo VARCHAR2,
    psSeleccion VARCHAR2)
RETURN  TABLA_CODIGO_PERIODO PIPELINED;
/***************************************************************************
Descripcion       : Genera data para Reporte de Estado de Cuentas Corrientes
                    Vendedoras
Fecha Creacion    : 07/12/2006
   psSeleccion    : 'C' : Completo
                    'U' : Ultimos 4 registros
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_ESTAD_CUENT_CORRI(
    psCodPais VARCHAR2,
    psCodMarca      VARCHAR2,
    psCodCanal      VARCHAR2,
    psCodRegion     VARCHAR2,
    psCodPeriodo    VARCHAR2,
    psCodZona       VARCHAR2,
    psCodSeccion    VARCHAR2,
    psCodTerritorio VARCHAR2,
    psSeleccion     VARCHAR2
    );
/***************************************************************************
Descripcion       : Genera data para Reporte de Detalle de Cuentas Corrientes
                    Vendedoras
Fecha Creacion    : 07/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_DETAL_CUENT_CORRI(
    psCodPais VARCHAR2,
    psCodMarca      VARCHAR2,
    psCodCanal      VARCHAR2,
    psCodRegion     VARCHAR2,
    psCodZona       VARCHAR2,
    psCodSeccion    VARCHAR2,
    psCodTerritorio VARCHAR2
    );
/***************************************************************************
Descripcion       : Genera data para Reporte de Facturas Pendientes x
                    Campa?a
Fecha Creacion    : 12/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_FACTU_PENDE_CAMPA(
    psCodPais       VARCHAR2,
    psCodMarca      VARCHAR2,
    psCodCanal      VARCHAR2,
    psCodPeriodo    VARCHAR2,
    psCodRegion     VARCHAR2,
    psMontoMinimo   VARCHAR2,
    psCodZona       VARCHAR2,
    psCodSeccion    VARCHAR2,
    psCodTerritorio VARCHAR2
    );
/***************************************************************************
Descripcion       : Genera data para Reporte de Base de Recuperacion de
                    Campa?as
Fecha Creacion    : 12/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_BASE_RECU_CAMPA(
    psCodPais         VARCHAR2,
    psCodMarca        VARCHAR2,
    psCodCanal        VARCHAR2,
    psCodRegion       VARCHAR2,
    psCodZona         VARCHAR2,
    psCodPeriodoDesde VARCHAR2,
    psCodPeriodoHasta VARCHAR2,
    psCodSubgerencia  VARCHAR2,
    psTipoVista       VARCHAR2
);
/***************************************************************************
Descripcion       : Genera data para Reporte de Consolidado de Cobranzas las
                    fechas necesarias para hacer el cruce respectivo
Fecha Creacion    : 08/01/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE PER_PR_GENER_CONSO_COBRANZA(
    fechaDesde VARCHAR2,
    fechaHasta VARCHAR2
);
/***************************************************************************
Descripcion       : Genera data para Reporte Resumen Cuenta Corriente
Fecha Creacion    : 18/09/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE PER_PR_GENER_RESUM_CUENT_CORRI(
    psCodPais         VARCHAR2,
    psCodSociedad     VARCHAR2,
    psFechaDesde      VARCHAR2,
    psFechaHasta      VARCHAR2,
    lsSecuencial     OUT NUMBER
);
/***************************************************************************
Descripcion       : Genera data para Reporte de Consolidado de Cobranzas los
                    importes necesarios
Fecha Creacion    : 08/01/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_SUMA_CONSO_COBR(
    psCodPais                    VARCHAR2,
    psCodBanco                   VARCHAR2,
    psCodCuentaCorriente         VARCHAR2,
    psFecha                      VARCHAR2,
    psCodSociedad                VARCHAR2,
    psIndicador                  VARCHAR2)
RETURN NUMBER ;


/***************************************************************************
Descripcion       : Devuelve Nombres y apellidos del cliente para ser mostrado
                    en el Reporte de Libro de Percepciones
Fecha Creacion    : 11/09/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_NOMBR_CONSU_LIBRO_PERCE(
    psCodPais    VARCHAR2,
    psCodCliente VARCHAR2,
    psApePaterno VARCHAR2,
    psApeMaterno VARCHAR2,
    psNombre     VARCHAR2)
RETURN VARCHAR2;

 /***************************************************************************
Descripcion       : Genera data para Reporte Control de cliente
Fecha Creacion    : 18/09/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_CONTR_CLIEN(
    psCodPais       VARCHAR2,
    psFechaDesde    VARCHAR2,
    psFechaHasta    VARCHAR2,
    psTipoDocum     VARCHAR2,
    psNroDocum      VARCHAR2
);

/***************************************************************************
Descripcion       : Genera data para Reporte Control de cliente
Fecha Creacion    : 18/09/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_CONTR_CLIEN(
    psCodPais       VARCHAR2,
    psFechaDesde    VARCHAR2,
    psFechaHasta    VARCHAR2
    );

 /***************************************************************************
Descripcion       : Genera data para Reporte Resumen Cuenta Corriente, la data
                    correspondiente a los abonos de cobranzas
Fecha Creacion    : 18/09/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE PER_PR_GENER_RESUM_ABONO_COBRA(
    psCodPais         VARCHAR2,
    psCodSociedad     VARCHAR2,
    psFechaDesde      VARCHAR2,
    psFechaHasta      VARCHAR2,
    lsSecuencial     OUT NUMBER
);

/**************************************************************************
Descripcion        : Funcion que devuelve el tipo de documento de identidad
                     homologado al formato de Sunat.
Parametros         :
                   psCodPais   :  Codigo de Pais
                   psTipoDocum :  Codigo de Tipo de documento
Fecha Creacion     : 24/10/2007
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_DOCUM_HOMOL
 (psCodPais   VARCHAR2,
  psTipoDocum VARCHAR2)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Devuelve Lista de Clientes por Unidad Administrativa
                    POR CAMPA?A
Fecha Creacion    : 12/09/2008
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_CLIEN_UNIAD_PERIO(
    psCodPais    VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL,
    psCodSeccion VARCHAR2:=NULL,
    psCodTerri   VARCHAR2:=NULL)
RETURN  tablaClienteUnidadAdm PIPELINED;

/***************************************************************************
Descripcion       : Genera data para Reporte Control de Asistencia
                    de Triunfadoras
Fecha Creacion    : 17/02/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_CONTR_ASIST_TRIUN(
    psCodPais VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2,
    psCodZona    VARCHAR2,
    psOidProceso VARCHAR2);

END PER_PKG_REPOR_PERCE;
/
CREATE OR REPLACE PACKAGE BODY "PER_PKG_REPOR_PERCE"
    IS
/***************************************************************************
Descripcion       : Devuelve Lista de Clientes por Unidad Administrativa
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_CLIEN_UNIAD(
    psCodPais    VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL,
    psCodSeccion VARCHAR2:=NULL,
    psCodTerri   VARCHAR2:=NULL)
RETURN  tablaClienteUnidadAdm PIPELINED
IS
  tablaRegistro   tablaClienteUnidadAdm;
  CURSOR cursorRegistro IS
  SELECT
     I.OID_CLIE,
     I.VAL_NOM1,
     I.VAL_NOM2,
     I.VAL_APE1,
     I.VAL_APE2,
     I.COD_CLIE,
     D.COD_REGI,
     E.COD_ZONA,
     F.COD_SECC,
     G.COD_TERR
  FROM
     SEG_PAIS  A,
     SEG_MARCA B,
     SEG_CANAL C,
     ZON_REGIO D,
     ZON_ZONA   E,
     ZON_SECCI  F,
     ZON_TERRI  G,
     ZON_TERRI_ADMIN H,
     MAE_CLIEN  I,
     MAE_CLIEN_UNIDA_ADMIN J
     --,
--     MAE_CLIEN_BLOQU K
     --,
--  MAE_TIPO_BLOQU L
  WHERE
     A.COD_PAIS = psCodPais   AND
     B.COD_MARC = Pscodmarca  AND
     C.COD_CANA = pscodcanal  AND
     ((pscodregion IS NULL ) OR (pscodregion IS NOT NULL AND D.COD_REGI = pscodregion)) AND
     ((Pscodzona IS NULL ) OR (Pscodzona IS NOT NULL AND E.COD_ZONA = Pscodzona)) AND
     ((pscodseccion IS NULL ) OR (pscodseccion IS NOT NULL AND F.COD_SECC = pscodseccion)) AND
     ((pscodterri IS NULL ) OR (pscodterri IS NOT NULL AND G.COD_TERR = pscodterri)) AND
     D.IND_ACTI = 1 AND
     E.IND_ACTI = 1 AND
     F.IND_ACTI = 1 AND
     G.IND_BORR = 0 AND
     H.IND_BORR = 0 AND
     J.IND_ACTI = 1 AND
     NOT EXISTS (SELECT CLIE_OID_CLIE
                        FROM MAE_CLIEN_BLOQU X
                        WHERE X.CLIE_OID_CLIE = I.OID_CLIE
                        AND X.TIBQ_OID_TIPO_BLOQ = 2
                        AND X.FEC_DESB IS NULL
                 ) AND
   -- (L.COD_TIPO_BLOQ <> '02' OR L.COD_TIPO_BLOQ IS NULL) AND
    J.CLIE_OID_CLIE = I.OID_CLIE AND
    I.PAIS_OID_PAIS = A.OID_PAIS AND
    H.OID_TERR_ADMI = J.ZTAD_OID_TERR_ADMI AND
    H.TERR_OID_TERR = G.OID_TERR AND
     H.ZSCC_OID_SECC = F.OID_SECC AND
    F.ZZON_OID_ZONA = E.OID_ZONA AND
    E.ZORG_OID_REGI = D.OID_REGI AND
  --  K.CLIE_OID_CLIE (+) = I.OID_CLIE AND
  --  L.OID_TIPO_BLOQ (+) = K.TIBQ_OID_TIPO_BLOQ AND
    H.MARC_OID_MARC = B.OID_MARC AND
     H.CANA_OID_CANA = C.OID_CANA;
BEGIN
  OPEN cursorRegistro;
  LOOP
       FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             PIPE ROW(tablaRegistro(x));
         END LOOP;
       END IF;
       EXIT WHEN cursorRegistro%NOTFOUND ;
  END LOOP ;
  CLOSE cursorRegistro;
  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_CLIEN_UNIAD: '||ls_sqlerrm);
END PER_FN_OBTIE_CLIEN_UNIAD ;


/***************************************************************************
Descripcion       : Devuelve Lista de Clientes por Unidad Administrativa
                    POR CAMPA?A
Fecha Creacion    : 12/09/2008
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_CLIEN_UNIAD_PERIO(
    psCodPais    VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL,
    psCodSeccion VARCHAR2:=NULL,
    psCodTerri   VARCHAR2:=NULL)
RETURN  tablaClienteUnidadAdm PIPELINED
IS
  tablaRegistro   tablaClienteUnidadAdm;
  CURSOR cursorRegistro IS
  SELECT
     I.OID_CLIE,
     I.VAL_NOM1,
     I.VAL_NOM2,
     I.VAL_APE1,
     I.VAL_APE2,
     I.COD_CLIE,
     D.COD_REGI,
     E.COD_ZONA,
     F.COD_SECC,
     G.COD_TERR
  FROM
     SEG_PAIS  A,
     SEG_MARCA B,
     SEG_CANAL C,
     ZON_REGIO D,
     ZON_ZONA   E,
     ZON_SECCI  F,
     ZON_TERRI  G,
     ZON_TERRI_ADMIN H,
     MAE_CLIEN  I,
     MAE_CLIEN_UNIDA_ADMIN J,
  --   MAE_CLIEN_BLOQU K,
  --   MAE_TIPO_BLOQU L,
     seg_perio_corpo m,
     seg_perio_corpo m_final,
     cra_perio n,
     cra_perio n_final
  WHERE
     A.COD_PAIS = psCodPais   AND
     B.COD_MARC = Pscodmarca  AND
     C.COD_CANA = pscodcanal  AND
     ((pscodregion IS NULL ) OR (pscodregion IS NOT NULL AND D.COD_REGI = pscodregion)) AND
     ((Pscodzona IS NULL ) OR (Pscodzona IS NOT NULL AND E.COD_ZONA = Pscodzona)) AND
     ((pscodseccion IS NULL ) OR (pscodseccion IS NOT NULL AND F.COD_SECC = pscodseccion)) AND
     ((pscodterri IS NULL ) OR (pscodterri IS NOT NULL AND G.COD_TERR = pscodterri)) AND
    -- (L.COD_TIPO_BLOQ <> '02' OR L.COD_TIPO_BLOQ IS NULL) AND
    NOT EXISTS (SELECT CLIE_OID_CLIE
                        FROM MAE_CLIEN_BLOQU X
                        WHERE X.CLIE_OID_CLIE = I.OID_CLIE
                        AND X.TIBQ_OID_TIPO_BLOQ = 2
                        AND X.FEC_DESB IS NULL
                 ) AND
    J.CLIE_OID_CLIE = I.OID_CLIE AND
    I.PAIS_OID_PAIS = A.OID_PAIS AND
    H.OID_TERR_ADMI = J.ZTAD_OID_TERR_ADMI AND
    H.TERR_OID_TERR = G.OID_TERR AND
     H.ZSCC_OID_SECC = F.OID_SECC AND
    F.ZZON_OID_ZONA = E.OID_ZONA AND
    E.ZORG_OID_REGI = D.OID_REGI AND
 --   K.CLIE_OID_CLIE (+) = I.OID_CLIE AND
  --  L.OID_TIPO_BLOQ (+) = K.TIBQ_OID_TIPO_BLOQ AND
    H.MARC_OID_MARC = B.OID_MARC AND
    H.CANA_OID_CANA = C.OID_CANA AND

    m.cod_peri <= psCodPeriodo AND
    m.oid_peri = n.peri_oid_peri AND
    j.perd_oid_peri_ini = n.oid_peri AND

    m_final.cod_peri >= psCodPeriodo AND
    m_final.oid_peri = n_final.peri_oid_peri AND
    j.perd_oid_peri_fin = n_final.oid_peri ;

CURSOR cursorRegistroFinNulo IS
  SELECT
     I.OID_CLIE,
     I.VAL_NOM1,
     I.VAL_NOM2,
     I.VAL_APE1,
     I.VAL_APE2,
     I.COD_CLIE,
     D.COD_REGI,
     E.COD_ZONA,
     F.COD_SECC,
     G.COD_TERR
  FROM
     SEG_PAIS  A,
     SEG_MARCA B,
     SEG_CANAL C,
     ZON_REGIO D,
     ZON_ZONA   E,
     ZON_SECCI  F,
     ZON_TERRI  G,
     ZON_TERRI_ADMIN H,
     MAE_CLIEN  I,
     MAE_CLIEN_UNIDA_ADMIN J,
  --   MAE_CLIEN_BLOQU K,
  --   MAE_TIPO_BLOQU L,
     seg_perio_corpo m,
     cra_perio n
  WHERE
     A.COD_PAIS = psCodPais   AND
     B.COD_MARC = Pscodmarca  AND
     C.COD_CANA = pscodcanal  AND
     ((pscodregion IS NULL ) OR (pscodregion IS NOT NULL AND D.COD_REGI = pscodregion)) AND
     ((Pscodzona IS NULL ) OR (Pscodzona IS NOT NULL AND E.COD_ZONA = Pscodzona)) AND
     ((pscodseccion IS NULL ) OR (pscodseccion IS NOT NULL AND F.COD_SECC = pscodseccion)) AND
     ((pscodterri IS NULL ) OR (pscodterri IS NOT NULL AND G.COD_TERR = pscodterri)) AND
   --  (L.COD_TIPO_BLOQ <> '02' OR L.COD_TIPO_BLOQ IS NULL) AND
   NOT EXISTS (SELECT CLIE_OID_CLIE
                        FROM MAE_CLIEN_BLOQU X
                        WHERE X.CLIE_OID_CLIE = I.OID_CLIE
                        AND X.TIBQ_OID_TIPO_BLOQ = 2
                        AND X.FEC_DESB IS NULL
                 ) AND
    J.CLIE_OID_CLIE = I.OID_CLIE AND
    I.PAIS_OID_PAIS = A.OID_PAIS AND
    H.OID_TERR_ADMI = J.ZTAD_OID_TERR_ADMI AND
    H.TERR_OID_TERR = G.OID_TERR AND
     H.ZSCC_OID_SECC = F.OID_SECC AND
    F.ZZON_OID_ZONA = E.OID_ZONA AND
    E.ZORG_OID_REGI = D.OID_REGI AND
  --  K.CLIE_OID_CLIE (+) = I.OID_CLIE AND
 --   L.OID_TIPO_BLOQ (+) = K.TIBQ_OID_TIPO_BLOQ AND
    H.MARC_OID_MARC = B.OID_MARC AND
    H.CANA_OID_CANA = C.OID_CANA AND

    m.cod_peri <= psCodPeriodo AND
    m.oid_peri = n.peri_oid_peri AND
    j.perd_oid_peri_ini = n.oid_peri AND
    j.perd_oid_peri_fin IS NULL ;


BEGIN
  OPEN cursorRegistro;
  LOOP
       FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             PIPE ROW(tablaRegistro(x));
         END LOOP;
       END IF;
       EXIT WHEN cursorRegistro%NOTFOUND ;
  END LOOP ;
  CLOSE cursorRegistro;

  OPEN cursorRegistroFinNulo;
  LOOP
       FETCH cursorRegistroFinNulo BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             PIPE ROW(tablaRegistro(x));
         END LOOP;
       END IF;
       EXIT WHEN cursorRegistroFinNulo%NOTFOUND ;
  END LOOP ;
  CLOSE cursorRegistroFinNulo;
  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_CLIEN_UNIAD_PERIO: '||ls_sqlerrm);
END PER_FN_OBTIE_CLIEN_UNIAD_PERIO ;


/***************************************************************************
Descripcion       : Devuelve Lista de Clientes por Unidad Administrativa
                    confrontando con PER_CUENT_CORRI_DOCLE
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_CLIEN_UNIAD_DOCLE(
    psCodPais    VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL,
    psCodSeccion VARCHAR2:=NULL,
    psCodTerri   VARCHAR2:=NULL)
RETURN  tablaClienteUnidadAdm PIPELINED
IS
  tablaRegistro   tablaClienteUnidadAdm;
  CURSOR cursorRegistro IS
  SELECT
     I.OID_CLIE,
     I.VAL_NOM1,
     I.VAL_NOM2,
     I.VAL_APE1,
     I.VAL_APE2,
     I.COD_CLIE,
     D.COD_REGI,
     E.COD_ZONA,
     F.COD_SECC,
     G.COD_TERR
  FROM
     SEG_PAIS  A,
     SEG_MARCA B,
     SEG_CANAL C,
     ZON_REGIO D,
     ZON_ZONA   E,
     ZON_SECCI  F,
     ZON_TERRI  G,
     ZON_TERRI_ADMIN H,
     MAE_CLIEN  I,
     MAE_CLIEN_UNIDA_ADMIN J,
     MAE_CLIEN_BLOQU K,
     MAE_TIPO_BLOQU L,
     PER_CUENT_CORRI_DOCLE M
  WHERE
     A.COD_PAIS = psCodPais   AND
     B.COD_MARC = Pscodmarca  AND
     C.COD_CANA = pscodcanal  AND
     M.PAIS_COD_PAIS = A.COD_PAIS AND
     M.COD_CLIE = I.COD_CLIE AND
     ((pscodregion IS NULL ) OR (pscodregion IS NOT NULL AND D.COD_REGI = pscodregion)) AND
     ((Pscodzona IS NULL ) OR (Pscodzona IS NOT NULL AND E.COD_ZONA = Pscodzona)) AND
     ((pscodseccion IS NULL ) OR (pscodseccion IS NOT NULL AND F.COD_SECC = pscodseccion)) AND
     ((pscodterri IS NULL ) OR (pscodterri IS NOT NULL AND G.COD_TERR = pscodterri)) AND
     D.IND_ACTI = 1 AND
     E.IND_ACTI = 1 AND
     F.IND_ACTI = 1 AND
     G.IND_BORR = 0 AND
     H.IND_BORR = 0 AND
     J.IND_ACTI = 1 AND
     (L.COD_TIPO_BLOQ <> '02' OR L.COD_TIPO_BLOQ IS NULL) AND
     J.CLIE_OID_CLIE = I.OID_CLIE AND
     I.PAIS_OID_PAIS = A.OID_PAIS AND
     H.OID_TERR_ADMI = J.ZTAD_OID_TERR_ADMI AND
     H.TERR_OID_TERR = G.OID_TERR AND
     H.ZSCC_OID_SECC = F.OID_SECC AND
     F.ZZON_OID_ZONA = E.OID_ZONA AND
     E.ZORG_OID_REGI = D.OID_REGI AND
     K.CLIE_OID_CLIE (+) = I.OID_CLIE AND
     L.OID_TIPO_BLOQ (+) = K.TIBQ_OID_TIPO_BLOQ AND
     H.MARC_OID_MARC = B.OID_MARC AND
     H.CANA_OID_CANA = C.OID_CANA;
BEGIN
  OPEN cursorRegistro;
  LOOP
       FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             PIPE ROW(tablaRegistro(x));
         END LOOP;
       END IF;
       EXIT WHEN cursorRegistro%NOTFOUND ;
  END LOOP ;
  CLOSE cursorRegistro;
  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_CLIEN_UNIAD_DOCLE: '||ls_sqlerrm);
END PER_FN_OBTIE_CLIEN_UNIAD_DOCLE ;
/***************************************************************************
Descripcion       : Devuelve montos x vencimiento
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_RECUP_MONTO_VENCI(poidCliente NUMBER,
    pdFechaInicio  DATE:=NULL,
    pdFechaFin     DATE:=NULL,
    psPeriodo      VARCHAR2:=NULL)
RETURN  NUMBER
IS
 ln_sumador NUMBER;
BEGIN
  IF pdFechaInicio IS NOT NULL AND pdFechaFin IS NOT NULL AND psPeriodo IS NOT NULL THEN
      SELECT NVL(SUM(A.IMP_PEND),0)
      INTO ln_sumador
      FROM
         CCC_MOVIM_CUENT_CORRI A,
         CCC_SUBPR B,
         CCC_PROCE C,
         CRA_PERIO D,
         SEG_PERIO_CORPO E
      WHERE
         A.CLIE_OID_CLIE = poidCliente AND
         A.FEC_VENC >= pdFechaInicio  AND
         A.FEC_VENC <= pdFechaFin  AND
         A.IMP_PEND <> 0 AND
         SUBSTR(C.COD_PROC,1,4) <> 'CCCP' AND
         E.COD_PERI = psPeriodo AND
         A.SUBP_OID_SUBP_CREA = B.OID_SUBP AND
         A.PERD_OID_PERI = D.OID_PERI AND
         C.OID_PROC = B.CCPR_OID_PROC AND
         E.OID_PERI = D.PERI_OID_PERI ;
      IF ln_Sumador < 0 THEN
         ln_Sumador:= 0;
      END IF;
      RETURN ln_sumador;
  END IF;

  IF pdFechaInicio IS NOT NULL AND pdFechaFin IS NOT NULL AND psPeriodo IS NULL THEN
      SELECT NVL(SUM(A.IMP_PEND),0)
      INTO ln_sumador
      FROM
         CCC_MOVIM_CUENT_CORRI A,
         CCC_SUBPR B,
         CCC_PROCE C,
         CRA_PERIO D,
         SEG_PERIO_CORPO E
      WHERE
         A.CLIE_OID_CLIE = poidCliente AND
         A.FEC_VENC >= pdFechaInicio  AND
         A.FEC_VENC <= pdFechaFin  AND
         A.IMP_PEND <> 0 AND
         SUBSTR(C.COD_PROC,1,4) <> 'CCCP' AND
         A.SUBP_OID_SUBP_CREA = B.OID_SUBP AND
         A.PERD_OID_PERI = D.OID_PERI AND
         C.OID_PROC = B.CCPR_OID_PROC AND
         E.OID_PERI = D.PERI_OID_PERI ;
      IF ln_Sumador < 0 THEN
         ln_Sumador:= 0;
      END IF;
      RETURN ln_sumador;
  END IF;

  IF pdFechaInicio IS NOT NULL AND pdFechaFin IS NULL AND psPeriodo IS NULL THEN
      SELECT NVL(SUM(A.IMP_PEND),0)
      INTO ln_sumador
      FROM
         CCC_MOVIM_CUENT_CORRI A,
         CCC_SUBPR B,
         CCC_PROCE C,
         CRA_PERIO D,
         SEG_PERIO_CORPO E
      WHERE
         A.CLIE_OID_CLIE = poidCliente AND
         A.FEC_VENC >= pdFechaInicio  AND
         A.IMP_PEND <> 0 AND
         SUBSTR(C.COD_PROC,1,4) <> 'CCCP' AND
         A.SUBP_OID_SUBP_CREA = B.OID_SUBP AND
         A.PERD_OID_PERI = D.OID_PERI AND
         C.OID_PROC = B.CCPR_OID_PROC AND
         E.OID_PERI = D.PERI_OID_PERI ;
      IF ln_Sumador < 0 THEN
         ln_Sumador:= 0;
      END IF;
      RETURN ln_sumador;
  END IF;

  IF pdFechaInicio IS NOT NULL AND pdFechaFin IS NULL AND psPeriodo IS NOT NULL THEN
      SELECT NVL(SUM(A.IMP_PEND),0)
      INTO ln_sumador
      FROM
         CCC_MOVIM_CUENT_CORRI A,
         CCC_SUBPR B,
         CCC_PROCE C,
         CRA_PERIO D,
         SEG_PERIO_CORPO E
      WHERE
         A.CLIE_OID_CLIE = poidCliente AND
         A.FEC_VENC >= pdFechaInicio  AND
         A.IMP_PEND <> 0 AND
         SUBSTR(C.COD_PROC,1,4) <> 'CCCP' AND
         E.COD_PERI = psPeriodo AND
         A.SUBP_OID_SUBP_CREA = B.OID_SUBP AND
         A.PERD_OID_PERI = D.OID_PERI AND
         C.OID_PROC = B.CCPR_OID_PROC AND
         E.OID_PERI = D.PERI_OID_PERI ;
      IF ln_Sumador < 0 THEN
         ln_Sumador:= 0;
      END IF;
      RETURN ln_sumador;
  END IF;

  IF pdFechaInicio IS NULL AND pdFechaFin IS NOT NULL AND psPeriodo IS NOT NULL THEN
      SELECT NVL(SUM(A.IMP_PEND),0)
      INTO ln_sumador
      FROM
         CCC_MOVIM_CUENT_CORRI A,
         CCC_SUBPR B,
         CCC_PROCE C,
         CRA_PERIO D,
         SEG_PERIO_CORPO E
      WHERE
         A.CLIE_OID_CLIE = poidCliente AND
         A.FEC_VENC <= pdFechaFin  AND
         A.IMP_PEND <> 0 AND
         SUBSTR(C.COD_PROC,1,4) <> 'CCCP' AND
         E.COD_PERI = psPeriodo AND
         A.SUBP_OID_SUBP_CREA = B.OID_SUBP AND
         A.PERD_OID_PERI = D.OID_PERI AND
         C.OID_PROC = B.CCPR_OID_PROC AND
         E.OID_PERI = D.PERI_OID_PERI ;
      IF ln_Sumador < 0 THEN
         ln_Sumador:= 0;
      END IF;
      RETURN ln_sumador;
  END IF;

  IF pdFechaInicio IS NULL AND pdFechaFin IS NOT NULL AND psPeriodo IS NULL THEN
      SELECT NVL(SUM(A.IMP_PEND),0)
      INTO ln_sumador
      FROM
         CCC_MOVIM_CUENT_CORRI A,
         CCC_SUBPR B,
         CCC_PROCE C,
         CRA_PERIO D,
         SEG_PERIO_CORPO E
      WHERE
         A.CLIE_OID_CLIE = poidCliente AND
         A.FEC_VENC <= pdFechaFin  AND
         A.IMP_PEND <> 0 AND
         SUBSTR(C.COD_PROC,1,4) <> 'CCCP' AND
         A.SUBP_OID_SUBP_CREA = B.OID_SUBP AND
         A.PERD_OID_PERI = D.OID_PERI AND
         C.OID_PROC = B.CCPR_OID_PROC AND
         E.OID_PERI = D.PERI_OID_PERI ;
      IF ln_Sumador < 0 THEN
         ln_Sumador:= 0;
      END IF;
      RETURN ln_sumador;
  END IF;

  IF pdFechaInicio IS NULL AND pdFechaFin IS NULL AND psPeriodo IS NULL THEN
      SELECT NVL(SUM(A.IMP_PEND),0)
      INTO ln_sumador
      FROM
         CCC_MOVIM_CUENT_CORRI A,
         CCC_SUBPR B,
         CCC_PROCE C,
         CRA_PERIO D,
         SEG_PERIO_CORPO E
      WHERE
         A.CLIE_OID_CLIE = poidCliente AND
         A.IMP_PEND <> 0 AND
         SUBSTR(C.COD_PROC,1,4) <> 'CCCP' AND
         A.SUBP_OID_SUBP_CREA = B.OID_SUBP AND
         A.PERD_OID_PERI = D.OID_PERI AND
         C.OID_PROC = B.CCPR_OID_PROC AND
         E.OID_PERI = D.PERI_OID_PERI ;
      IF ln_Sumador < 0 THEN
         ln_Sumador:= 0;
      END IF;
      RETURN ln_sumador;
  END IF;

  IF pdFechaInicio IS NULL AND pdFechaFin IS NULL AND psPeriodo IS NOT NULL THEN
      SELECT NVL(SUM(A.IMP_PEND),0)
      INTO ln_sumador
      FROM
         CCC_MOVIM_CUENT_CORRI A,
         CCC_SUBPR B,
         CCC_PROCE C,
         CRA_PERIO D,
         SEG_PERIO_CORPO E
      WHERE
         A.CLIE_OID_CLIE = poidCliente AND
         A.IMP_PEND <> 0 AND
         SUBSTR(C.COD_PROC,1,4) <> 'CCCP' AND
         E.COD_PERI = psPeriodo AND
         A.SUBP_OID_SUBP_CREA = B.OID_SUBP AND
         A.PERD_OID_PERI = D.OID_PERI AND
         C.OID_PROC = B.CCPR_OID_PROC AND
         E.OID_PERI = D.PERI_OID_PERI ;
      IF ln_Sumador < 0 THEN
         ln_Sumador:= 0;
      END IF;
      RETURN ln_sumador;
  END IF;


  SELECT NVL(SUM(A.IMP_PEND),0)
  INTO ln_sumador
  FROM
     CCC_MOVIM_CUENT_CORRI A,
     CCC_SUBPR B,
     CCC_PROCE C,
     CRA_PERIO D,
     SEG_PERIO_CORPO E
  WHERE
     A.CLIE_OID_CLIE = poidCliente AND
     ((pdFechaInicio IS NULL) OR (pdFechaInicio IS NOT NULL AND A.FEC_VENC >= pdFechaInicio))  AND
     ((pdFechaFin IS NULL) OR (pdFechaFin IS NOT NULL AND A.FEC_VENC <= pdFechaFin))  AND
     A.IMP_PEND <> 0 AND
     SUBSTR(C.COD_PROC,1,4) <> 'CCCP' AND
     E.COD_PERI = decode(psPeriodo, NULL, E.COD_PERI, psPeriodo) AND
     A.SUBP_OID_SUBP_CREA = B.OID_SUBP AND
     A.PERD_OID_PERI = D.OID_PERI AND
     C.OID_PROC = B.CCPR_OID_PROC AND
     E.OID_PERI = D.PERI_OID_PERI ;
  IF ln_Sumador < 0 THEN
     ln_Sumador:= 0;
  END IF;
  RETURN ln_sumador;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_RECUP_MONTO_VENCI: '||ls_sqlerrm);
END PER_FN_RECUP_MONTO_VENCI;

/***************************************************************************
Descripcion       : Recupera Fechas de Facturacion
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_RECUP_FECHA_FACTU(psCodPais VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodigoZona VARCHAR2,
    psCodPeriodo VARCHAR2)
RETURN DATE
IS
  lnIdPais        seg_pais.oid_pais%TYPE;
  lnIdCanal       seg_canal.oid_cana%TYPE;
  lnIdMarca       seg_marca.oid_marc%TYPE;
  ldFechaIni      DATE;
BEGIN
  lnIdPais  := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  /* obteniendo Fecha de Inicio */
  SELECT B.FEC_INIC
  INTO ldFechaIni
  FROM
       CRA_ACTIV A,
       CRA_CRONO B,
       SEG_PERIO_CORPO C,
       CRA_PERIO D,
       ZON_ZONA  E
  WHERE    E.PAIS_OID_PAIS = lnIdPais
       AND E.MARC_OID_MARC = lnIdMarca
       AND E.CANA_OID_CANA = lnIdCanal
       AND E.IND_ACTI = 1
       AND E.COD_ZONA = psCodigoZona
       AND C.COD_PERI = psCodPeriodo
       AND A.COD_ACTI = 'FA'
       AND (A.OID_ACTI = B.CACT_OID_ACTI)
       AND (C.OID_PERI = D.PERI_OID_PERI)
       AND (D.OID_PERI = B.PERD_OID_PERI)
       AND (E.OID_ZONA = B.ZZON_OID_ZONA)
       AND ROWNUM = 1 ;
  RETURN ldFechaIni ;
EXCEPTION
WHEN NO_DATA_FOUND THEN
  RETURN NULL;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_RECUP_FECHA_FACTU: '||ls_sqlerrm);
END PER_FN_RECUP_FECHA_FACTU;
/***************************************************************************
Descripcion       : Obtiene periodo en base al codigo de periodo base y numero
                    de registro
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PERIO(
   psCodPeriodo VARCHAR2,
   pnOidPais  NUMBER,
   pnOidMarca NUMBER,
   pnOidCanal NUMBER,
   valorRegistro NUMBER)
RETURN VARCHAR2
IS
   TYPE tipotablaPeriodo IS TABLE OF SEG_PERIO_CORPO.COD_PERI%TYPE INDEX BY BINARY_INTEGER;
   tablaPeriodo tipotablaPeriodo;
   indice       NUMBER;
BEGIN
IF valorRegistro = 0 THEN
   return psCodPeriodo;
end if;
/* Obteniendo Lista de Periodos */
IF valorRegistro > 0 THEN
    WITH TEMPORAL AS
    ( SELECT PERI_OID_PERI
       FROM
          CRA_PERIO
       WHERE
           PAIS_OID_PAIS = pnOidPais   AND
           MARC_OID_MARC = pnOidMarca  AND
           CANA_OID_CANA = pnOidCanal
    )
    SELECT A.COD_PERI
    BULK COLLECT INTO tablaPeriodo
    FROM
       SEG_PERIO_CORPO A,
       TEMPORAL B
    WHERE
       A.OID_PERI = B.PERI_OID_PERI AND
       A.COD_PERI > psCodPeriodo
    ORDER BY A.COD_PERI ;
ELSE
    WITH TEMPORAL AS
    ( SELECT PERI_OID_PERI
       FROM
          CRA_PERIO
       WHERE
           PAIS_OID_PAIS = pnOidPais  AND
           MARC_OID_MARC = pnOidMarca AND
           CANA_OID_CANA = pnOidCanal
    )
    SELECT A.COD_PERI
    BULK COLLECT INTO tablaPeriodo
    FROM
       SEG_PERIO_CORPO A,
       TEMPORAL B
    WHERE
       A.OID_PERI = B.PERI_OID_PERI AND
       A.COD_PERI < psCodPeriodo
    ORDER BY A.COD_PERI DESC;
END IF;
/* Encontrando el periodo */
indice := abs(valorRegistro);
IF tablaPeriodo.COUNT < indice THEN
   RETURN NULL;
END IF ;
IF tablaPeriodo.COUNT <= 0 THEN
   RETURN NULL;
END IF ;
FOR x IN tablaPeriodo.FIRST .. tablaPeriodo.LAST LOOP
    IF (x = indice) THEN
        RETURN tablaPeriodo(x);
    END IF;
END LOOP;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_PERIO: '||ls_sqlerrm);
END PER_FN_OBTIE_PERIO;

/***************************************************************************
Descripcion       : Obtiene periodo en base al codigo de periodo base y numero
                    de registro
Fecha Creacion    : 26/06/2014
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PERIO(
   psCodPeriodo VARCHAR2,
   psCodigoPais VARCHAR2,
   valorRegistro NUMBER)
RETURN VARCHAR2
IS
  lnIdPais  NUMBER;
  lnIdCanal NUMBER;
  lnIdMarca NUMBER;
  lsPeriodoRetorno VARCHAR2(6);
BEGIN
   lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
   lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
   lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
   lsPeriodoRetorno := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, valorRegistro);
   RETURN lsPeriodoRetorno;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_PERIO: '||ls_sqlerrm);
END PER_FN_OBTIE_PERIO;


/***************************************************************************
Descripcion       : Obtiene Factura
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_OBTIE_FACTU(
   pnOidCliente NUMBER,
   psCodPeri    VARCHAR2,
   pnOidPais    NUMBER,
   pnOidMarca   NUMBER,
   pnOidCanal   NUMBER,
   pdFechaFac   OUT DATE,
   pnValNume    OUT NUMBER
   )
IS
   lnOidSoliCabe   PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE%TYPE;
   ldFechaFact     PED_SOLIC_CABEC.FEC_FACT%TYPE;
   lnValNume       PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
   lnIdPeri        CRA_PERIO.OID_PERI%TYPE;

BEGIN
   /* obteniendo id de periodo */
   lnIdPeri := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeri, pnOidMarca, pnOidCanal);

   /* Recuperando numero de boletas de despacho */
  /* SELECT
      A.SOCA_OID_SOLI_CABE
    INTO
      lnOidSoliCabe
   FROM
      PED_SOLIC_CABEC A,
      PED_TIPO_SOLIC D,
      PED_TIPO_SOLIC_PAIS E
   WHERE
      A.CLIE_OID_CLIE = pnOidCliente
      AND A.IND_OC = 1
      AND A.IND_PEDI_PRUE = 0
      AND A.FEC_FACT IS NOT NULL
      AND a.perd_oid_peri = lnIdPeri
      AND D.IND_DEVO = 0
      AND D.IND_ANUL = 0
      AND D.OID_TIPO_SOLI = E.TSOL_OID_TIPO_SOLI
      AND E.OID_TIPO_SOLI_PAIS = A.TSPA_OID_TIPO_SOLI_PAIS
      AND A.ESSO_OID_ESTA_SOLI <> 4
      */


   SELECT B.FEC_FACT, B.VAL_NUME_SOLI
   INTO
      ldFechaFact, lnValNume
   FROM PED_SOLIC_CABEC B
   WHERE
        B.OID_SOLI_CABE IN (SELECT
                           A.SOCA_OID_SOLI_CABE
                           FROM
      PED_SOLIC_CABEC A,
      PED_TIPO_SOLIC D,
      PED_TIPO_SOLIC_PAIS E
   WHERE
      A.CLIE_OID_CLIE = pnOidCliente
      AND A.IND_OC = 1
      AND A.IND_PEDI_PRUE = 0
      AND A.FEC_FACT IS NOT NULL
      AND a.perd_oid_peri = lnIdPeri
      AND D.IND_DEVO = 0
      AND D.IND_ANUL = 0
      AND D.OID_TIPO_SOLI = E.TSOL_OID_TIPO_SOLI
      AND E.OID_TIPO_SOLI_PAIS = A.TSPA_OID_TIPO_SOLI_PAIS
      AND A.ESSO_OID_ESTA_SOLI <> 4 )
      AND B.ESSO_OID_ESTA_SOLI <> 4
      AND ROWNUM = 1;
   pdFechaFac := ldFechaFact;
   pnValNume  := lnValNume;
   RETURN;
END PER_PR_OBTIE_FACTU;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo + saldo1 + pago_diferido + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTAL_DEUDORAS
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT COUNT(A.OID_CORR)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     A.COD_SECC = psCodSeccion AND
     NVL(A.SAL_PEND,0) + NVL(A.SAL_PEN1,0) + NVL(A.PAG_DIFE,0) + NVL(A.PAG_DIF1,0) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTAL_DEUDORAS: '||ls_sqlerrm);
     END IF;
END PER_FN_OBTIE_TOTAL_DEUDORAS;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona que cumplen
                    con: saldo + saldo1 + pago_diferido + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTAL_DEUDORAS
  ( psCodZona      VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT COUNT(A.OID_CORR)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     NVL(A.SAL_PEND,0) + NVL(A.SAL_PEN1,0) + NVL(A.PAG_DIFE,0) + NVL(A.PAG_DIF1,0) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTAL_DEUDORAS: '||ls_sqlerrm);
     END IF;
END PER_FN_OBTIE_TOTAL_DEUDORAS;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con:
                    saldo + pago_diferido > 0 Y
                    saldo1 + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PED_RESP
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT COUNT(A.OID_CORR)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     A.COD_SECC = psCodSeccion AND
     (NVL(A.SAL_PEND,0) + NVL(A.PAG_DIFE,0)) > 0  AND
     (NVL(A.SAL_PEN1,0) + NVL(A.PAG_DIF1,0)) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_PED_RESP: '||ls_sqlerrm);
END PER_FN_OBTIE_PED_RESP;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con:
                    saldo + pago_diferido > 0 Y
                    saldo1 + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_PED_RESP
  ( psCodZona      VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT COUNT(A.OID_CORR)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     (NVL(A.SAL_PEND,0) + NVL(A.PAG_DIFE,0)) > 0  AND
     (NVL(A.SAL_PEN1,0) + NVL(A.PAG_DIF1,0)) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_PED_RESP: '||ls_sqlerrm);
END PER_FN_OBTIE_PED_RESP;
/***************************************************************************
Descripcion       : Suma
                         saldo + pago_diferido +
                         saldo1 + pago_diferido1
                    por zona y seccion
                    para cuando
                    saldo + pago_diferido > 0 y
                    saldo1 + pago_diferido1 > 0
Fecha Creacion    : 04/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_SUMA_RESP
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT SUM(A.SAL_PEND + A.SAL_PEN1 + A.PAG_DIFE + A.PAG_DIF1)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     A.COD_SECC = psCodSeccion AND
     (NVL(A.SAL_PEND,0) + NVL(A.PAG_DIFE,0)) > 0  AND
     (NVL(A.SAL_PEN1,0) + NVL(A.PAG_DIF1,0)) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_SUMA_RESP: '||ls_sqlerrm);
END PER_FN_OBTIE_SUMA_RESP;
/***************************************************************************
Descripcion       : Suma
                         saldo + pago_diferido +
                         saldo1 + pago_diferido1
                    por zona y seccion
                    para cuando
                    saldo + pago_diferido > 0 y
                    saldo1 + pago_diferido1 > 0
Fecha Creacion    : 04/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_SUMA_RESP
  ( psCodZona      VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT SUM(A.SAL_PEND + A.SAL_PEN1 + A.PAG_DIFE + A.PAG_DIF1)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     (NVL(A.SAL_PEND,0) + NVL(A.PAG_DIFE,0)) > 0  AND
     (NVL(A.SAL_PEN1,0) + NVL(A.PAG_DIF1,0)) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_SUMA_RESP: '||ls_sqlerrm);
END PER_FN_OBTIE_SUMA_RESP;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo + pago_diferido > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT COUNT(A.OID_CORR)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     A.COD_SECC = psCodSeccion AND
     NVL(A.SAL_PEND,0) + NVL(A.PAG_DIFE,0) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_DEUDORAS: '||ls_sqlerrm);
     END IF;
END PER_FN_OBTIE_DEUDORAS;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo1 + pago_diferido1 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS1
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT COUNT(A.OID_CORR)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     A.COD_SECC = psCodSeccion AND
     NVL(A.SAL_PEN1,0) + NVL(A.PAG_DIF1,0) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_DEUDORAS1: '||ls_sqlerrm);
     END IF;
END PER_FN_OBTIE_DEUDORAS1;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo2 + pago_diferido2 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS2
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT COUNT(A.OID_CORR)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     A.COD_SECC = psCodSeccion AND
     NVL(A.SAL_PEN2,0) + NVL(A.PAG_DIF2,0) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_DEUDORAS2: '||ls_sqlerrm);
END PER_FN_OBTIE_DEUDORAS2;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo3 + pago_diferido3 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS3
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT COUNT(A.OID_CORR)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     A.COD_SECC = psCodSeccion AND
     NVL(A.SAL_PEN3,0) + NVL(A.PAG_DIF3,0) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_DEUDORAS3: '||ls_sqlerrm);
END PER_FN_OBTIE_DEUDORAS3;
/***************************************************************************
Descripcion       : Cuenta los registros por Zona y Seccion que cumplen
                    con: saldo4 + pago_diferido4 > 0
Fecha Creacion    : 20/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_DEUDORAS4
  ( psCodZona      VARCHAR2,
    psCodSeccion   VARCHAR2
  )
RETURN NUMBER
IS
  lnContador NUMBER;
BEGIN
  SELECT COUNT(A.OID_CORR)
  INTO lnContador
  FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
     A.COD_ZONA = psCodZona AND
     A.COD_SECC = psCodSeccion AND
     NVL(A.SAL_PEN4,0) + NVL(A.PAG_DIF4,0) > 0;
  RETURN lnContador;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_DEUDORAS4: '||ls_sqlerrm);
END PER_FN_OBTIE_DEUDORAS4;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Total de Cargo .Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_CARG
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER
IS
  lnTotalCargo NUMBER;
BEGIN
  lnTotalCargo:=0;
  SELECT NVL(SUM(TOTAL_CARGO.IMP_MOVI),0)
  INTO lnTotalCargo
  FROM
  (SELECT a.oid_movi_cc, A.IMP_MOVI
    FROM PER_REPOR_BASE_RECUP_CAMPA A
   WHERE A.COD_PERI = psCodPeri
         AND A.COD_REGI = Decode(psCodRegi, NULL, A.COD_REGI, psCodRegi)
         AND A.COD_ZONA = Decode(psCodZona, NULL, A.COD_ZONA, psCodZona)
         AND ((COD_PROC = 'CCC001') OR (COD_PROC = 'CCC003' AND COD_SUBP = 4))
       AND A.IMP_MOVI > 0
         GROUP BY a.oid_movi_cc, IMP_MOVI) TOTAL_CARGO;
  RETURN lnTotalCargo;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_CARG: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_CARG;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Total Abono. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_ABON
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER
IS
  lnTotalAbono NUMBER;
BEGIN
  lnTotalAbono:=0;
  SELECT NVL(SUM(TOTAL_ABONO.IMP_MOVI),0)
  INTO lnTotalAbono
  FROM
  (SELECT a.oid_movi_cc, A.IMP_MOVI
    FROM PER_REPOR_BASE_RECUP_CAMPA A
   WHERE A.COD_PERI = psCodPeri
          AND A.COD_REGI = Decode(psCodRegi, NULL, A.COD_REGI, psCodRegi)
         AND A.COD_ZONA = Decode(psCodZona, NULL, A.COD_ZONA, psCodZona)
         AND COD_PROC IN ('CCCP01', 'CCC001', 'CCC006')
       AND A.IMP_MOVI < 0
         GROUP BY a.oid_movi_cc, IMP_MOVI) TOTAL_ABONO;
  RETURN lnTotalAbono;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_ABON: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_ABON;


/***************************************************************************
Descripcion       : Obtiene el monto Total de Boleta CDR
Fecha Creacion    : 11/06/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_BOLE_CDR
(        psOidCliente NUMBER,
         pdFechaInicial DATE,
         pdFechaFinal date
         )
RETURN NUMBER IS
CURSOR movi_cta_cte IS
SELECT  A.IMP_MOVI,
        A.OID_MOVI_CC,
        A.VAL_ULTI_NUME_HIST
FROM  CCC_MOVIM_CUENT_CORRI A,
      CCC_SUBPR B,
      CCC_PROCE C
WHERE A.SUBP_OID_SUBP_CREA = B.OID_SUBP
      AND B.CCPR_OID_PROC  = C.OID_PROC
      AND A.IMP_MOVI > 0
      AND A.CLIE_OID_CLIE = psOidCliente
      AND A.FEC_VENC > pdFechaInicial
      AND A.FEC_VENC <= pdFechaFinal
      AND (((C.COD_PROC = 'CCC001' OR C.COD_PROC = 'CCCP01') AND B.COD_SUBP = 1)
       OR (((C.COD_PROC = 'CCC003' OR C.COD_PROC = 'CCCP03') AND B.COD_SUBP = 4))
       );
  TYPE MoviCtaCte IS RECORD (
     IMP_MOVI            CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE,
     OID_MOVI_CC         CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC%TYPE,
     VAL_ULTI_NUME_HIST  CCC_MOVIM_CUENT_CORRI.VAL_ULTI_NUME_HIST%TYPE
   );
  TYPE tMoviCtaCte IS TABLE OF MoviCtaCte;
  tablaMoviCtaCte tMoviCtaCte;
  lnTotalAbono NUMBER;
  lnImporteCDR NUMBER;
  temp NUMBER;
BEGIN
  lnTotalAbono:=0;
  lnImporteCDR:=0;
 OPEN movi_cta_cte;
  LOOP
      FETCH movi_cta_cte BULK COLLECT INTO tablaMoviCtaCte LIMIT W_FILAS;
      IF tablaMoviCtaCte.COUNT > 0 THEN
      FOR x IN tablaMoviCtaCte.FIRST .. tablaMoviCtaCte.LAST LOOP
          lnTotalAbono:=tablaMoviCtaCte(x).IMP_MOVI+lnTotalAbono;

           /*Calculamos el importe de los CCC_MOVIM_CUENT_CORRI**/
          IF (tablaMoviCtaCte(x).VAL_ULTI_NUME_HIST > 0) THEN
            BEGIN
               temp:=0;
               SELECT SUM(A.IMP_PAGO)
               INTO temp
               FROM CCC_MOVIM_CUENT_CORRI A,
                    CCC_SUBPR B,
                    CCC_PROCE C
               WHERE A.SUBP_OID_SUBP_ULTI  = B.OID_SUBP
                    AND B.CCPR_OID_PROC  = C.OID_PROC
                    AND A.OID_MOVI_CC  = tablaMoviCtaCte(x).OID_MOVI_CC
                    AND ((C.COD_PROC = 'CCC002' OR C.COD_PROC = 'CCCP08') AND B.COD_SUBP = 2);
               EXCEPTION
               WHEN NO_DATA_FOUND THEN
                   temp:=0;
               END;
               lnImporteCDR:= lnImporteCDR+nvl(temp, 0);
          END IF;

           /*Calculamos el importe de los historicos**/
          IF (tablaMoviCtaCte(x).VAL_ULTI_NUME_HIST > 1 ) THEN
           BEGIN
             temp:=0;
             SELECT SUM(A.IMP_PAGO)
             INTO temp
             FROM CCC_HISTO_MOVIM_CC A,
                  CCC_SUBPR B,
                  CCC_PROCE C
             WHERE A.MVCC_OID_MOVI_CC = tablaMoviCtaCte(x).OID_MOVI_CC
                  AND A.SUBP_OID_SUBP = B.OID_SUBP
                  AND B.CCPR_OID_PROC  = C.OID_PROC
                  AND ((C.COD_PROC = 'CCC002' OR C.COD_PROC = 'CCCP08') AND B.COD_SUBP = 2);
             EXCEPTION
             WHEN NO_DATA_FOUND THEN
                 temp:=0;
             END;
             lnImporteCDR:= lnImporteCDR+nvl(temp, 0);
          END IF;
      END LOOP;
      END IF;
      EXIT WHEN movi_cta_cte%NOTFOUND;
  END LOOP;
  CLOSE movi_cta_cte;
  RETURN lnTotalAbono-lnImporteCDR;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_BOLE_CDR: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_BOLE_CDR;


/***************************************************************************
Descripcion       : Obtiene el monto Total de Boleta CDR
Fecha Creacion    : 11/09/2008
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_BOLE_CDR
(        psOidCliente  NUMBER,
         pnOidMovim    NUMBER,
         pnValUltiModi NUMBER
)
RETURN NUMBER IS
  lnTotalAbono NUMBER;
  lnImporteCDR NUMBER;
  temp NUMBER;
BEGIN
  lnTotalAbono:=0.0;
  lnImporteCDR:=0.0;

  /* obteniendo importe de pago cdr */
  IF pnValUltiModi > 0 THEN
     BEGIN
          SELECT A.IMP_PAGO
          INTO lnImporteCDR
          FROM CCC_MOVIM_CUENT_CORRI A,
              CCC_SUBPR B,
              CCC_PROCE C
          WHERE A.OID_MOVI_CC = pnOidMovim
            AND A.SUBP_OID_SUBP_ULTI = B.OID_SUBP
            AND B.CCPR_OID_PROC  = C.OID_PROC
            AND C.COD_PROC = 'CCC002' AND B.COD_SUBP = 2;
     EXCEPTION
     WHEN no_data_found THEN
           lnImporteCDR:=0.0;
     END;
  END IF;

  /* obteniendo importes pagos cdr historicos */
  temp:=0;
  IF pnValUltiModi > 1 THEN
     BEGIN
         SELECT nvl(SUM(H.IMP_PAGO),0)
         INTO temp
         FROM
              CCC_HISTO_MOVIM_CC H,
              CCC_MOVIM_CUENT_CORRI A,
              CCC_SUBPR B,
              CCC_PROCE C
         WHERE A.OID_MOVI_CC  = pnOidMovim
           AND H.MVCC_OID_MOVI_CC = A.OID_MOVI_CC
           AND H.SUBP_OID_SUBP  = B.OID_SUBP
           AND B.CCPR_OID_PROC  = C.OID_PROC
           AND C.COD_PROC = 'CCC002' AND B.COD_SUBP = 2 ;
     EXCEPTION
     WHEN NO_DATA_FOUND THEN
           temp:=0;
     END;
  END IF;
  lnImporteCDR := lnImporteCDR + temp;
  RETURN lnImporteCDR;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_BOLE_CDR: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_BOLE_CDR;


/***************************************************************************
Descripcion       : Obtiene el monto Total de Boleta CDR
                    Con codigo de proceso CCC001 - CCC003
Fecha Creacion    : 14/06/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_BOLE_CDR2
(        psOidCliente NUMBER,
         pdFechaInicial DATE,
         pdFechaFinal date
         )
RETURN NUMBER IS
CURSOR movi_cta_cte IS
SELECT  A.IMP_MOVI,
        A.OID_MOVI_CC,
        A.VAL_ULTI_NUME_HIST
FROM  CCC_MOVIM_CUENT_CORRI A,
      CCC_SUBPR B,
      CCC_PROCE C
WHERE A.SUBP_OID_SUBP_CREA = B.OID_SUBP
      AND B.CCPR_OID_PROC  = C.OID_PROC
      AND A.IMP_MOVI > 0
      AND A.CLIE_OID_CLIE = psOidCliente
      AND A.FEC_VENC > pdFechaInicial
      AND A.FEC_VENC <= pdFechaFinal
      AND (((C.COD_PROC = 'CCC001' ) AND B.COD_SUBP = 1)
       OR (((C.COD_PROC = 'CCC003' ) AND B.COD_SUBP = 4))
       );
  TYPE MoviCtaCte IS RECORD (
     IMP_MOVI            CCC_MOVIM_CUENT_CORRI.IMP_MOVI%TYPE,
     OID_MOVI_CC         CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC%TYPE,
     VAL_ULTI_NUME_HIST  CCC_MOVIM_CUENT_CORRI.VAL_ULTI_NUME_HIST%TYPE
   );
  TYPE tMoviCtaCte IS TABLE OF MoviCtaCte;
  tablaMoviCtaCte tMoviCtaCte;
  lnTotalAbono NUMBER;
  lnImporteCDR NUMBER;
  temp NUMBER;
BEGIN
  lnTotalAbono:=0;
  lnImporteCDR:=0;
 OPEN movi_cta_cte;
  LOOP
      FETCH movi_cta_cte BULK COLLECT INTO tablaMoviCtaCte LIMIT W_FILAS;
      IF tablaMoviCtaCte.COUNT > 0 THEN
      FOR x IN tablaMoviCtaCte.FIRST .. tablaMoviCtaCte.LAST LOOP
          lnTotalAbono:=tablaMoviCtaCte(x).IMP_MOVI+lnTotalAbono;
           /*Calculamos el importe de los CCC_MOVIM_CUENT_CORRI**/
          IF (tablaMoviCtaCte(x).VAL_ULTI_NUME_HIST > 0 ) THEN
            BEGIN
               temp:=0;
              SELECT SUM(A.IMP_PAGO)
               INTO temp
               FROM CCC_MOVIM_CUENT_CORRI A,
                    CCC_SUBPR B,
                    CCC_PROCE C
               WHERE A.SUBP_OID_SUBP_ULTI  = B.OID_SUBP
                    AND B.CCPR_OID_PROC  = C.OID_PROC
                    AND A.OID_MOVI_CC  = tablaMoviCtaCte(x).OID_MOVI_CC
                    AND ((C.COD_PROC = 'CCC002' ) AND B.COD_SUBP = 2);
               EXCEPTION
               WHEN NO_DATA_FOUND THEN
                   temp:=0;
                END;
               lnImporteCDR:= lnImporteCDR+nvl(temp,0);
          END IF;
           /*Calculamos el importe de los historicos**/
          IF (tablaMoviCtaCte(x).VAL_ULTI_NUME_HIST > 1 ) THEN
           BEGIN
             temp:=0;
             SELECT SUM(A.IMP_PAGO)
             INTO temp
             FROM CCC_HISTO_MOVIM_CC A,
                  CCC_SUBPR B,
                  CCC_PROCE C
             WHERE A.MVCC_OID_MOVI_CC = tablaMoviCtaCte(x).OID_MOVI_CC
                  AND A.SUBP_OID_SUBP = B.OID_SUBP
                  AND B.CCPR_OID_PROC  = C.OID_PROC
                  AND ((C.COD_PROC = 'CCC002' ) AND B.COD_SUBP = 2);
             EXCEPTION
             WHEN NO_DATA_FOUND THEN
                 temp:=0;
             END;
             lnImporteCDR:= lnImporteCDR+nvl(temp,0);

          END IF;
      END LOOP;
      END IF;
      EXIT WHEN movi_cta_cte%NOTFOUND;
  END LOOP;
  CLOSE movi_cta_cte;
  RETURN lnTotalAbono-lnImporteCDR;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_BOLE_CDR2: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_BOLE_CDR2;


/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla CCC_MOVIM_CUENT_CORRI
                    para calcular el Total Percepcion Movimiento.
Fecha Creacion    : 10/05/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_PERCE_CCCMO
(        psOidClie  NUMBER,
         fechaVencimiento DATE,
         fechaVencimientoMenos1 DATE
         )
RETURN NUMBER
IS
  lnTotalPerc NUMBER;
BEGIN
  lnTotalPerc:=0;
  SELECT SUM(T.IMP_MOVI)
  INTO lnTotalPerc
  FROM CCC_MOVIM_CUENT_CORRI T,
       CCC_PROCE P,
       CCC_SUBPR S
  WHERE T.CLIE_OID_CLIE = psOidClie AND
        T.FEC_VENC  >= fechaVencimiento AND
        T.FEC_VENC  <= fechaVencimientoMenos1 AND
        T.SUBP_OID_SUBP_CREA = S.OID_SUBP AND
        S.CCPR_OID_PROC = P.OID_PROC AND
        P.COD_PROC LIKE 'CCCP%' AND
        T.IMP_PEND <> 0;

  RETURN lnTotalPerc;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_PERCE_CCCMO: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_PERCE_CCCMO;
/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla CCC_MOVIM_CUENT_CORRI
                    para calcular el Total Percepcion Pendiente.
Fecha Creacion    : 10/05/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_PERCE_CCCPE
(        psOidClie  NUMBER,
         fechaVencimiento DATE,
         fechaVencimientoMenos1 DATE
         )
RETURN NUMBER
IS
  lnTotalPerc NUMBER;
BEGIN
  lnTotalPerc:=0;
  SELECT SUM(T.IMP_PEND)
  INTO lnTotalPerc
  FROM CCC_MOVIM_CUENT_CORRI T,
       CCC_PROCE P,
       CCC_SUBPR S
  WHERE T.CLIE_OID_CLIE = psOidClie AND
        T.FEC_VENC  >= fechaVencimiento AND
        T.FEC_VENC  <= fechaVencimientoMenos1 AND
        T.SUBP_OID_SUBP_CREA = S.OID_SUBP AND
        S.CCPR_OID_PROC = P.OID_PROC AND
        P.COD_PROC LIKE 'CCCP%' AND
        T.IMP_PEND <> 0
                            ;
  RETURN lnTotalPerc;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_PERCE_CCCPE: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_PERCE_CCCPE;
/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla CCC_MOVIM_CUENT_CORRI
                    para calcular el Total Percepcion Pendiente.
Fecha Creacion    : 10/05/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_PERCE_CCC
(        psOidClie  NUMBER,
         psOidPeriodo VARCHAR2
         )
RETURN NUMBER
IS
  lnTotalPerc NUMBER;
BEGIN
  lnTotalPerc:=0;
  SELECT SUM(T.IMP_PEND)
  INTO lnTotalPerc
  FROM CCC_MOVIM_CUENT_CORRI T,
       CCC_PROCE P,
       CCC_SUBPR S
  WHERE T.CLIE_OID_CLIE = psOidClie AND
        T.PERD_OID_PERI = psOidPeriodo AND
        T.SUBP_OID_SUBP_CREA = S.OID_SUBP AND
        S.CCPR_OID_PROC = P.OID_PROC AND
        P.COD_PROC LIKE 'CCCP%' AND
        T.IMP_PEND <> 0
                            ;
  RETURN lnTotalPerc;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_PERCE_CCC: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_PERCE_CCC;
/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Saldo Pendiente. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_SALDO_PEND
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER
IS
  lnTotalAbono NUMBER;
BEGIN
  lnTotalAbono:=0;
  SELECT NVL(SUM(TOTAL_ABONO.IMP_PEND),0)
  INTO lnTotalAbono
  FROM
  (SELECT a.oid_movi_cc, A.IMP_PEND
    FROM PER_REPOR_BASE_RECUP_CAMPA A
   WHERE A.COD_PERI = psCodPeri
         AND A.COD_REGI = Decode(psCodRegi, NULL, A.COD_REGI, psCodRegi)
         AND A.COD_ZONA = Decode(psCodZona, NULL, A.COD_ZONA, psCodZona)
         GROUP BY a.oid_movi_cc, IMP_PEND) TOTAL_ABONO;
  RETURN lnTotalAbono;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_SALDO_PEND: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_SALDO_PEND;
/***************************************************************************
Descripcion       : Suma los IMP_PEND de la tabla temporal de movimientos cuenta
                    corriente para unas determinadas fechas de vencimiento
Fecha Creacion    : 11/06/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_IMPO_PEND(
         psOidCliente NUMBER,
         pdFechaVen1 DATE,
         pdFechaVen DATE
         )
RETURN NUMBER
IS
  lnTotalImportePendiente NUMBER;
BEGIN
  lnTotalImportePendiente:=0;
  SELECT sum(A.IMP_PEND)
  INTO lnTotalImportePendiente
    FROM CCC_MOVIM_CUENT_CORRI  A
   WHERE A.CLIE_OID_CLIE = psOidCliente
         AND A.IMP_PEND > 0
         AND A.FEC_VENC >  pdFechaVen
         AND A.FEC_VENC  <= pdFechaVen1  ;
  RETURN lnTotalImportePendiente;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_IMPO_PEND: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_IMPO_PEND;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Cargo Facturado. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_CARGO_FACTU
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER
IS
  lnCargoFactu NUMBER;
BEGIN
  lnCargoFactu:=0;
  SELECT NVL(SUM(CARGO_FACTU.IMP_MOVI),0)
  INTO lnCargoFactu
  FROM
  (SELECT a.oid_movi_cc, A.IMP_MOVI
    FROM PER_REPOR_BASE_RECUP_CAMPA A
   WHERE A.COD_PERI = psCodPeri
         AND A.COD_REGI = Decode(psCodRegi, NULL, A.COD_REGI, psCodRegi)
         AND A.COD_ZONA = Decode(psCodZona, NULL, A.COD_ZONA, psCodZona)
         AND COD_PROC = 'CCC001'
         AND A.IMP_MOVI > 0
         GROUP BY a.oid_movi_cc, IMP_MOVI) CARGO_FACTU;
  RETURN lnCargoFactu;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_CARGO_FACTU: '||ls_sqlerrm);
END PER_FN_OBTIE_CARGO_FACTU;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Cargo Directo. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_CARGO_DIREC
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER
IS
  lnCargoDirecto NUMBER;
BEGIN
  lnCargoDirecto:=0;
  SELECT NVL(SUM(CARGO_DIREC.IMP_MOVI),0)
  INTO lnCargoDirecto
  FROM
  (SELECT a.oid_movi_cc, A.IMP_MOVI
    FROM PER_REPOR_BASE_RECUP_CAMPA A
   WHERE A.COD_PERI = psCodPeri
         AND A.COD_REGI = Decode(psCodRegi, NULL, A.COD_REGI, psCodRegi)
         AND A.COD_ZONA = Decode(psCodZona, NULL, A.COD_ZONA, psCodZona)
         AND COD_PROC = 'CCC003'
         AND COD_SUBP = 4
         AND A.IMP_MOVI > 0
         GROUP BY a.oid_movi_cc, IMP_MOVI) CARGO_DIREC;
  RETURN lnCargoDirecto;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_CARGO_DIREC: '||ls_sqlerrm);
END PER_FN_OBTIE_CARGO_DIREC;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular la Nota de Credito. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_NOTA_CREDI
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER
IS
  lnNotaCredito NUMBER;
BEGIN
  lnNotaCredito:=0;
  SELECT NVL(SUM(NOTA_CREDI.IMP_MOVI),0)
  INTO lnNotaCredito
  FROM
  (SELECT a.oid_movi_cc, A.IMP_MOVI
    FROM PER_REPOR_BASE_RECUP_CAMPA A
   WHERE A.COD_PERI = psCodPeri
         AND A.COD_REGI = Decode(psCodRegi, NULL, A.COD_REGI, psCodRegi)
         AND A.COD_ZONA = Decode(psCodZona, NULL, A.COD_ZONA, psCodZona)
         AND COD_PROC = 'CCC001'
         AND A.IMP_MOVI < 0
         GROUP BY a.oid_movi_cc, IMP_MOVI) NOTA_CREDI;
  RETURN lnNotaCredito;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_NOTA_CREDI: '||ls_sqlerrm);
END PER_FN_OBTIE_NOTA_CREDI;


/***************************************************************************
Descripcion       : Obtiene el numero de boleta para un cliente en un periodo
                   determinado
Fecha Creacion    : 11/06/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_NUME_BOLE
(        psOiodPeri NUMBER,
         psOidCliente NUMBER
         )
RETURN NUMBER
IS
  lnNumeroBoleta NUMBER;
BEGIN
  lnNumeroBoleta:=0;
  SELECT max(A.Val_Nume_Soli)
  INTO lnNumeroBoleta
    FROM PED_SOLIC_CABEC A,
         PED_TIPO_SOLIC_PAIS B,
         PED_TIPO_SOLIC C
   WHERE A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
         AND B.TSOL_OID_TIPO_SOLI = C.OID_TIPO_SOLI
         AND A.PERD_OID_PERI = psOiodPeri
         AND A.CLIE_OID_CLIE = psOidCliente
         AND C.COD_TIPO_SOLI = 'C1';
RETURN lnNumeroBoleta;

EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_NUME_BOLE: '||ls_sqlerrm);
END PER_FN_OBTIE_NUME_BOLE;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular el Abono Directo. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_ABON_DIREC
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER
IS
  lnAbonoDirecto NUMBER;
BEGIN
  lnAbonoDirecto:=0;
  SELECT NVL(SUM(ABONO_DIREC.IMP_MOVI),0)
  INTO lnAbonoDirecto
  FROM
  (SELECT a.oid_movi_cc, A.IMP_MOVI
    FROM PER_REPOR_BASE_RECUP_CAMPA A
   WHERE A.COD_PERI = psCodPeri
         AND A.COD_REGI = Decode(psCodRegi, NULL, A.COD_REGI, psCodRegi)
         AND A.COD_ZONA = Decode(psCodZona, NULL, A.COD_ZONA, psCodZona)
         AND COD_PROC = 'CCC003'
         AND COD_SUBP = 4
       AND A.IMP_MOVI < 0
         GROUP BY a.oid_movi_cc, a.IMP_MOVI) ABONO_DIREC;
  RETURN lnAbonoDirecto;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_ABON_DIREC: '||ls_sqlerrm);
END PER_FN_OBTIE_ABON_DIREC;
/***************************************************************************
Descripcion       : Suma los IMP_MOVI de la tabla temporal PER_REPOR_BASE_RECUP_CAMPA
                    para calcular los Incobrables. Empleado para el reporte de
                    BASE_RECUPERACION_CAMPA?A de Percepciones
Fecha Creacion    : 06/02/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_TOTA_INCOB
(        psCodPeri VARCHAR2,
         psCodRegi VARCHAR2,
         psCodZona VARCHAR2
         )
RETURN NUMBER
IS
  lnIncob NUMBER;
BEGIN
  lnIncob:=0;
  SELECT NVL(SUM(INCOB.IMP_MOVI),0)
  INTO lnIncob
  FROM
  (SELECT a.oid_movi_cc, A.IMP_MOVI
    FROM PER_REPOR_BASE_RECUP_CAMPA A
   WHERE A.COD_PERI = psCodPeri
         AND A.COD_REGI = Decode(psCodRegi, NULL, A.COD_REGI, psCodRegi)
         AND A.COD_ZONA = Decode(psCodZona, NULL, A.COD_ZONA, psCodZona)
         AND COD_PROC = 'CCC006'
         GROUP BY a.oid_movi_cc, IMP_MOVI) INCOB;
  RETURN lnIncob;
EXCEPTION
WHEN NO_DATA_FOUND THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_TOTA_INCOB: '||ls_sqlerrm);
END PER_FN_OBTIE_TOTA_INCOB;
/***************************************************************************
Descripcion       : Genera data para Reporte Facturas Pendientes x Seccion
Fecha Creacion    : 14/11/2006
      psTipoReporte : <1> Detalle de Factura
Autor             : Carlos Bazalar
***************************************************************************/
 PROCEDURE PER_PR_GENER_FACTU_PENDI(
  psCodPais VARCHAR2,
  psCodMarca      VARCHAR2,
  psCodCanal      VARCHAR2,
  psCodPeriodo    VARCHAR2,
  psCodRegion     VARCHAR2,
  psTipoReporte   VARCHAR2,
  psCodZona       VARCHAR2:=NULL,
  psCodSeccion    VARCHAR2:=NULL,
  psCodTerritorio VARCHAR2:=NULL)
 IS

  tablaFactuPend   tablaFacturaPend;
  regFactuPend     PER_REPOR_FACTU_PENDI_SECCI%ROWTYPE; --tRegFacturaPend;
  lnIdPais         seg_pais.oid_pais%TYPE;
  lnIdCanal        seg_canal.oid_cana%TYPE;
  lnIdMarca        seg_marca.oid_marc%TYPE;
  lsPeriodo1       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodo2       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodo3       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodo4       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodo5       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodoMas1    SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeri        SEG_PERIO_CORPO.COD_PERI%TYPE;
  ldFechaFact      PED_SOLIC_CABEC.FEC_FACT%TYPE;
  lnValNume        PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
  regRespon        GEN_PKG_GENER.tRegResponsableUniAdm;

  lsIdPeriodo     NUMBER;
  lsIdPeriodo1    NUMBER;
  lsIdPeriodo2    NUMBER;
  lsIdPeriodo3    NUMBER;
  lsIdPeriodoMas1 NUMBER;
  lb_seguir_2     NUMBER;
  lnValor         NUMBER;
  lb_seguir       BOOLEAN;
  lnContador      NUMBER;
  lsCodGere      PER_REPOR_FACTU_PENDI_SECCI.Cod_Gere%TYPE;
  lsNomGere      PER_REPOR_FACTU_PENDI_SECCI.Nom_Gere%TYPE;

  CURSOR cFactura IS
  SELECT *
  FROM PER_REPOR_FACTU_PENDI_SECCI A;

  CURSOR cZona IS
  SELECT DISTINCT COD_ZONA
  FROM PER_REPOR_FACTU_PENDI_SECCI A;

BEGIN

 DELETE FROM PER_REPOR_FACTU_PENDI_SECCI;

 lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
 lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
 lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
 lsPeriodo1    := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
 lsPeriodo2    := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -2);
 lsPeriodo3    := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -3);
 lsPeriodo4    := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -4);
 lsPeriodo5    := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -5);
 lsPeriodoMas1 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, 1);

 lsIdPeriodo   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);
 lsIdPeriodo1  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo1, lnIdMarca, lnIdCanal);
 lsIdPeriodo2  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo2, lnIdMarca, lnIdCanal);
 lsIdPeriodo3  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo3, lnIdMarca, lnIdCanal);
 lsIdPeriodoMas1 := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodoMas1, lnIdMarca, lnIdCanal);


 /* Insertando informacion del Cliente x Unidad Administrativa */
 INSERT INTO PER_REPOR_FACTU_PENDI_SECCI
  (OID_CORR, OID_CLIE, NOM_CLIE, COD_CLIE,
   COD_ZONA, COD_SECC, COD_TERR,
   PER_RELA, PER_REL1, PER_REL2, PER_REL3, PER_REL4, PER_REL5, IND_DEUD_PEND)
  SELECT
    ROWNUM, OID_CLIE, trim(SUBSTR(VAL_NOM1,1,1)) ||' '|| trim(VAL_APE1), COD_CLIE,
    COD_ZONA, COD_SECC, COD_TERR,
    psCodPeriodo, lsPeriodo1, lsPeriodo2, lsPeriodo3, lsPeriodo4, lsPeriodo5, 0
  FROM TABLE(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_CLIEN_UNIAD(psCodPais, psCodMarca, psCodCanal,
            psCodRegion, psCodZona, psCodSeccion, psCodTerritorio));

  /* Obteniendo Fechas de Vencimiento */
  FOR curZona IN cZona LOOP

      regRespon :=  gen_pkg_gener.per_fn_recup_respo_uniad(psCodPais, psCodMarca, psCodCanal, psCodRegion, curZona.Cod_Zona, NULL); --Gerente de Zona
      lsCodGere := regRespon.COD_CLIE;
      lsNomGere := nvl(TRIM(regRespon.VAL_APE1),' ') || ' ' || nvl(TRIM(regRespon.VAL_APE2),' ') || ' ' ||
                   nvl(TRIM(regRespon.VAL_NOM1),' ') || ' ' || nvl(TRIM(regRespon.VAL_NOM2),' ');

     UPDATE PER_REPOR_FACTU_PENDI_SECCI A
     SET
        A.COD_GERE = lsCodGere,
        A.NOM_GERE = lsNomGere
     WHERE
        A.COD_ZONA = curZona.Cod_Zona;
  END LOOP;

  /* Marcamos de Clientes con deuda pendiente = 0 */
  UPDATE  PER_REPOR_FACTU_PENDI_SECCI A
  SET IND_DEUD_PEND = 1
  WHERE
    (
      CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(a.oid_clie,lsPeriodoMas1)
      )<=0;

  /* Eliminacion de clientes que no han Facturado en las campa?as */
  DELETE FROM PER_REPOR_FACTU_PENDI_SECCI X
  WHERE
    NOT EXISTS (
      SELECT A.OID_CLIE
      FROM  MAE_CLIEN A,
            PED_SOLIC_CABEC B,
            PED_TIPO_SOLIC_PAIS C,
            PED_TIPO_SOLIC D
      WHERE A.OID_CLIE = X.OID_CLIE
        AND B.CLIE_OID_CLIE = A.OID_CLIE
        AND B.FEC_FACT IS NOT NULL
        AND (B.PERD_OID_PERI = lsIdPeriodo OR B.PERD_OID_PERI = lsIdPeriodo1 OR B.PERD_OID_PERI = lsIdPeriodo2 OR B.PERD_OID_PERI = lsIdPeriodo3)
        AND C.OID_TIPO_SOLI_PAIS = B.TSPA_OID_TIPO_SOLI_PAIS
        AND D.COD_TIPO_SOLI = 'C1'
        AND D.OID_TIPO_SOLI = C.TSOL_OID_TIPO_SOLI);

  /* Recorriendo Lista de Clientes */
  lnContador := 0;
  OPEN cFactura;
  LOOP
      FETCH cFactura BULK COLLECT INTO tablaFactuPend LIMIT 5000;
      IF tablaFactuPend.COUNT > 0 THEN
        FOR x IN tablaFactuPend.FIRST .. tablaFactuPend.LAST LOOP
            regFactuPend := tablaFactuPend(x);

            lb_seguir := TRUE;
            BEGIN

              SELECT D.COD_PERI
              INTO
                 lsCodPeri
              FROM
                 MAE_CLIEN A,
                 MAE_CLIEN_PRIME_CONTA B,
                 CRA_PERIO C,
                 SEG_PERIO_CORPO D
              WHERE
                 A.OID_CLIE = regFactuPend.Oid_Clie
                 AND A.OID_CLIE = B.CLIE_OID_CLIE
                 AND B.MARC_OID_MARC = lnIdMarca
                 AND B.CANA_OID_CANA = lnIdCanal
                 AND C.OID_PERI = B.PERD_OID_PERI
                 AND D.OID_PERI = C.PERI_OID_PERI;

              regfactupend.cam_ingr := lsCodPeri;

           EXCEPTION
           WHEN NO_DATA_FOUND THEN
                regfactupend.cam_ingr := NULL;
           END ;

           IF lb_seguir THEN
             /* obteniendo montos */
             lb_seguir_2 := regFactuPend.IND_DEUD_PEND;
             IF lb_seguir_2 = 0 THEN

              regfactupend.sal_pend := ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA(regFactuPend.Oid_Clie,psCodPeriodo) - REC_PKG_PROCE.REC_FN_ABONO_PENDI_CONSU(regFactuPend.Oid_Clie,lsIdPeriodo);
              regfactupend.sal_pen1 := ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA(regFactuPend.Oid_Clie,lsPeriodo1) - REC_PKG_PROCE.REC_FN_ABONO_PENDI_CONSU(regFactuPend.Oid_Clie,lsIdPeriodo1);
              regfactupend.sal_pen2 := ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA(regFactuPend.Oid_Clie,lsPeriodo2) - REC_PKG_PROCE.REC_FN_ABONO_PENDI_CONSU(regFactuPend.Oid_Clie,lsIdPeriodo2);
              regfactupend.sal_pen3 := ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA(regFactuPend.Oid_Clie,lsPeriodo3) - REC_PKG_PROCE.REC_FN_ABONO_PENDI_CONSU(regFactuPend.Oid_Clie,lsIdPeriodo3);
              regfactupend.sal_pen4 := ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA(regFactuPend.Oid_Clie,lsPeriodo4);

              
              /*
              regfactupend.sal_pend := maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_VENTA(regFactuPend.Oid_Clie,psCodPeriodo);
              regfactupend.sal_pen1 := maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_VENTA(regFactuPend.Oid_Clie,lsPeriodo1);
              regfactupend.sal_pen2 := maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_VENTA(regFactuPend.Oid_Clie,lsPeriodo2);
              regfactupend.sal_pen3 := maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_VENTA(regFactuPend.Oid_Clie,lsPeriodo3);
              regfactupend.sal_pen4 := maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_VENTA(regFactuPend.Oid_Clie,lsPeriodo4);

              regfactupend.pag_dife :=  maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_CARTE(regFactuPend.Oid_Clie,psCodPeriodo);
              regfactupend.pag_dif1 := maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_CARTE(regFactuPend.Oid_Clie,lsPeriodo1);
              regfactupend.pag_dif2 := maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_CARTE(regFactuPend.Oid_Clie,lsPeriodo2);
              regfactupend.pag_dif3 := maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_CARTE(regFactuPend.Oid_Clie,lsPeriodo3);
              regfactupend.pag_dif4 := maki_ccc_pkg_gener.CCC_FN_OBTIE_SALDO_CAMPA_CARTE(regFactuPend.Oid_Clie,lsPeriodo4);
              */

                /* Obteniendo los montos de Boleta CDR */

               regfactupend.mon_fact1 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ven1,
                                               regFactuPend.Fec_Venc + 1),0);
               regfactupend.mon_fact2 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ven2,
                                               regFactuPend.Fec_Ven1 + 1),0);
               regfactupend.mon_fact3 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ven3,
                                               regFactuPend.Fec_Ven2 + 1),0);
               regfactupend.mon_fact4 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ven4,
                                               regFactuPend.Fec_Ven3 + 1 ),0);
               regfactupend.mon_fact5 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ini4 ,
                                               regFactuPend.Fec_Ven4 + 1),0);



             /* Recuperando numero de boletas de despacho */
             lnContador := 0;

             FIN_PKG_GENER.FIN_PR_OBTIE_DATOS_FACTU_CAMPA(regfactupend.oid_clie, regfactupend.Per_Rela,ldFechaFact, lnValNume);
             regfactupend.fec_fact := ldFechaFact;
             regfactupend.num_fact := lnValNume;

             FIN_PKG_GENER.FIN_PR_OBTIE_DATOS_FACTU_CAMPA(regfactupend.oid_clie, regfactupend.Per_Rel1,ldFechaFact, lnValNume);
             regfactupend.num_fac1 := lnValNume;

             FIN_PKG_GENER.FIN_PR_OBTIE_DATOS_FACTU_CAMPA(regfactupend.oid_clie, regfactupend.Per_Rel2,ldFechaFact, lnValNume);
             regfactupend.num_fac2 := lnValNume;

             FIN_PKG_GENER.FIN_PR_OBTIE_DATOS_FACTU_CAMPA(regfactupend.oid_clie, regfactupend.Per_Rel3,ldFechaFact, lnValNume);
             regfactupend.num_fac3 := lnValNume;

             FIN_PKG_GENER.FIN_PR_OBTIE_DATOS_FACTU_CAMPA(regfactupend.oid_clie, regfactupend.Per_Rel4,ldFechaFact, lnValNume);
             regfactupend.num_fac4 := lnValNume;

             /* obteniendo responsable */
             regRespon := GEN_PKG_GENER.per_fn_recup_respo_uniad(psCodPais, psCodMarca, psCodCanal, psCodRegion, regfactupend.cod_zona, regfactupend.cod_secc);
             regfactupend.cod_lide := regRespon.COD_CLIE;
             regfactupend.nom_lide := nvl(TRIM(regRespon.VAL_APE1),' ') || ' ' || nvl(TRIM(regRespon.VAL_APE2),' ') || ' ' ||
                                      nvl(TRIM(regRespon.VAL_NOM1),' ') || ' ' || nvl(TRIM(regRespon.VAL_NOM2),' ');

             /* Viendo SALDOS TOTALES */
             regfactupend.sal_unic := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(regfactupend.oid_clie,lsPeriodo3);
             regfactupend.sal_tota := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(regfactupend.oid_clie,lsPeriodoMas1) -
                                      CCC_PKG_GENER.CCC_FN_OBTIE_ABONO_PEND(regfactupend.oid_clie);
             if regfactupend.sal_tota < 0 then
                regfactupend.sal_tota := 0;
             end if;
             regfactupend.sal_pago_min_flx := TO_NUMBER(regfactupend.sal_tota) - FLX_PKG_PROCE.FLX_FN_OBTIE_SALDO_MAXIM(regfactupend.oid_clie,lsIdPeriodoMas1);

             /* Actualizando registro */
             UPDATE per_repor_factu_pendi_secci r
             SET
                CAM_INGR = regfactupend.cam_ingr,
                SAL_PEND = regfactupend.sal_pend,
                SAL_PEN1 = regfactupend.sal_pen1,
                SAL_PEN2 = regfactupend.sal_pen2,
                SAL_PEN3 = regfactupend.sal_pen3,
                SAL_PEN4 = regfactupend.sal_pen4,
                SAL_TOTA = regfactupend.sal_tota,
                SAL_PAGO_MIN_FLX = regfactupend.sal_pago_min_flx,
                r.sal_unic = regfactupend.sal_unic,
                r.pag_dife = regfactupend.pag_dife,
                r.pag_dif1 = regfactupend.pag_dif1,
                r.pag_dif2 = regfactupend.pag_dif2,
                r.pag_dif3 = regfactupend.pag_dif3,
                r.pag_dif4 = regfactupend.pag_dif4,
                FEC_FACT = regfactupend.fec_fact,
                NUM_FACT = regfactupend.num_fact,
                NUM_FAC1 = regfactupend.num_fac1,
                NUM_FAC2 = regfactupend.num_fac2,
                NUM_FAC3 = regfactupend.num_fac3,
                NUM_FAC4 = regfactupend.num_fac4,
                COD_LIDE = regfactupend.cod_lide,
                NOM_LIDE = regfactupend.nom_lide,
                MON_FACT1= regfactupend.mon_fact1,
                MON_FACT2= regfactupend.mon_fact2,
                MON_FACT3= regfactupend.mon_fact3,
                MON_FACT4= regfactupend.mon_fact4,
                MON_FACT5= regfactupend.mon_fact5
             WHERE
                OID_CORR = regfactupend.oid_corr;
            /* IF (Pstiporeporte = '1') THEN
                lnValor :=  NVL(regfactupend.SAL_PEND,0) + NVL(regfactupend.SAL_PEN1,0) +
                            NVL(regfactupend.SAL_PEN2,0) + NVL(regfactupend.SAL_PEN3,0) +
                            NVL(regfactupend.PAG_DIFE,0) + NVL(regfactupend.PAG_DIF1,0) +
                            NVL(regfactupend.PAG_DIF2,0) + NVL(regfactupend.PAG_DIF3,0) +
                            NVL(regfactupend.MON_PERC,0);
               IF lnValor = 0.0 OR lnValor IS NULL THEN
                   DELETE FROM PER_REPOR_FACTU_PENDI_SECCI
                   WHERE
                      OID_CORR = regfactupend.oid_corr;
               END IF;
             END IF;*/
               ELSE
                /* obteniendo responsable */
               regRespon := GEN_PKG_GENER.per_fn_recup_respo_uniad(psCodPais, psCodMarca, psCodCanal, psCodRegion, regfactupend.cod_zona, regfactupend.cod_secc);
               regfactupend.cod_lide := regRespon.COD_CLIE;
               regfactupend.nom_lide := nvl(TRIM(regRespon.VAL_APE1),' ') || ' ' || nvl(TRIM(regRespon.VAL_APE2),' ') || ' ' ||
                                        nvl(TRIM(regRespon.VAL_NOM1),' ') || ' ' || nvl(TRIM(regRespon.VAL_NOM2),' ');

               /* Obteniendo los montos de Boleta CDR */
               regfactupend.mon_fact1 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ven1,
                                               regFactuPend.Fec_Venc + 1),0);
               regfactupend.mon_fact2 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ven2,
                                               regFactuPend.Fec_Ven1 + 1),0);
               regfactupend.mon_fact3 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ven3,
                                               regFactuPend.Fec_Ven2 + 1),0);
               regfactupend.mon_fact4 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ven4,
                                               regFactuPend.Fec_Ven3 + 1 ),0);
               regfactupend.mon_fact5 := NVL(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_TOTA_BOLE_CDR2(
                                               regFactuPend.Oid_Clie,
                                               regFactuPend.Fec_Ini4 ,
                                               regFactuPend.Fec_Ven4 + 1),0);
                UPDATE PER_REPOR_FACTU_PENDI_SECCI
               SET
                  COD_LIDE = regfactupend.cod_lide,
                  NOM_LIDE = regfactupend.nom_lide,
                  MON_FACT1= regfactupend.mon_fact1,
                  MON_FACT2= regfactupend.mon_fact2,
                  MON_FACT3= regfactupend.mon_fact3,
                  MON_FACT4= regfactupend.mon_fact4,
                  MON_FACT5= regfactupend.mon_fact5
               WHERE
                  OID_CORR = regfactupend.oid_corr;
               END IF;
           END IF;
         END LOOP;
      END IF;
      EXIT WHEN cFactura%NOTFOUND;
  END LOOP;
  CLOSE cFactura;
 /* DELETE FROM PER_REPOR_FACTU_PENDI_SECCI A
  WHERE
    NVL(A.SAL_PEND,0) + NVL(A.SAL_PEN1,0) +
    NVL(A.SAL_PEN2,0) + NVL(A.SAL_PEN3,0) +
    NVL(A.PAG_DIFE,0) + NVL(A.PAG_DIF1,0) +
    NVL(A.PAG_DIF2,0) + NVL(A.PAG_DIF3,0) +
    NVL(A.MON_PERC,0) = 0.0;*/
  RETURN;


END PER_PR_GENER_FACTU_PENDI;

/***************************************************************************
Descripcion       : Genera data para Reporte Control de Asistencia
Fecha Creacion    : 14/11/2006
   psSeleccion    : 0 <Todos>
                    1 <Activas>
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_CONTR_ASIST(psCodPais VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2,
    psCodZona    VARCHAR2,
    psSeleccion  VARCHAR2,
    psOidProceso VARCHAR2)
IS
  tablaRegistro   tablaControlAsis;
  regRegistro     tRegControlAsis; --PER_REPOR_CNTRL_ASIST_CONFE%ROWTYPE;
  lnIdPais        seg_pais.oid_pais%TYPE;
  lnIdCanal       seg_canal.oid_cana%TYPE;
  lnIdMarca       seg_marca.oid_marc%TYPE;
  lsPeriodo1      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodo2      SEG_PERIO_CORPO.COD_PERI%TYPE;
  ln_monto        NUMBER;
  ln_monto_perc   NUMBER;
  lnContador      NUMBER;
  ldFechaFact     DATE;
  W_FILAS         NUMBER:=25000;
  CURSOR cursorRegistro IS
  SELECT
    ROWID,
    OID_CLIE,
    COD_CLIE,
    NOM_CLIE,
    COD_ZONA,
    FEC_FACT,
    COD_SECC,
    COD_TERR,
    NOM_GERE,
    SAL_UNIC,
    SAL_PAGO_MIN_FLX,
    VAL_OBSE
  FROM PER_REPOR_CNTRL_ASIST_CONFE A
    WHERE OID_PROC = psOidProceso;

 CURSOR cursorZona IS
  SELECT DISTINCT
  COD_ZONA
  FROM PER_REPOR_CNTRL_ASIST_CONFE A
  WHERE OID_PROC = psOidProceso;


  lsPeriodo3                       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodo4                       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnPeriodo                        CRA_PERIO.OID_PERI%TYPE;
  lnPeriodo2                       CRA_PERIO.OID_PERI%TYPE;
  lnPeriodo3                       CRA_PERIO.OID_PERI%TYPE;
  lnPeriodo4                       CRA_PERIO.OID_PERI%TYPE;
  lsPedido                         VARCHAR2(20);
  lsTelefonoMovil                  VARCHAR2(20);
  lnIndicadorCobertura             NUMBER(12);
  lsFamiliaProtegida               VARCHAR2(50);

BEGIN

  --DELETE FROM PER_REPOR_CNTRL_ASIST_CONFE WHERE OID_PROC = psOidProceso;

  /* Insertando informacion del Cliente x Unidad Administrativa */
 INSERT INTO PER_REPOR_CNTRL_ASIST_CONFE
  (OID_CLIE, NOM_CLIE, COD_CLIE,
   COD_ZONA, COD_SECC, COD_TERR,OID_PROC)
  SELECT
    OID_CLIE,
    nvl(TRIM(VAL_APE1),' ') || ' ' || nvl(TRIM(VAL_APE2),' ') || ' ' || nvl(TRIM(VAL_NOM1),' ') || ' ' || nvl(TRIM(VAL_NOM2),' '),
    COD_CLIE, COD_ZONA, COD_SECC, COD_TERR,psOidProceso
  FROM TABLE(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_CLIEN_UNIAD(
            psCodPais, psCodMarca, psCodCanal,
            psCodRegion, psCodZona, NULL, NULL));

  /* En caso se haya seleccionado Activa */
  IF psSeleccion = 'A' THEN
    DELETE FROM PER_REPOR_CNTRL_ASIST_CONFE A
    WHERE
       a.OID_PROC = psOidProceso
       and A.OID_CLIE IN (SELECT Y.CLIE_OID_CLIE
                      FROM
                         MAE_ESTAT_CLIEN X,
                         MAE_CLIEN_DATOS_ADICI Y
                      WHERE
                         Y.CLIE_OID_CLIE = A.OID_CLIE AND
                         X.OID_ESTA_CLIE = Y.ESTA_OID_ESTA_CLIE AND
                         X.COD_ESTA_CLIE IN ('01','05','07')
                      );
  END IF;

  /* Obteniendo periodo-1 */
  lnIdPais   := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal  := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca  := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lsPeriodo1 := PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
  lsPeriodo2 := PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -2);
  lsPeriodo3 := PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -3);
  lsPeriodo4 := PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -4);

  lnPeriodo  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);
  lnPeriodo2 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo2, lnIdMarca, lnIdCanal);
  lnPeriodo3 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo3, lnIdMarca, lnIdCanal);
  lnPeriodo4 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsPeriodo4, lnIdMarca, lnIdCanal);

  /* obteniendo fecha */
  FOR cZona IN cursorZona LOOP
      ldFechaFact := PER_FN_RECUP_FECHA_FACTU(psCodPais, psCodMarca,  psCodCanal,
                               cZona.COD_ZONA, lsPeriodo1);
      UPDATE PER_REPOR_CNTRL_ASIST_CONFE A
      SET
        A.FEC_FACT = ldFechaFact
      WHERE
        A.COD_ZONA = cZona.COD_ZONA
        AND OID_PROC = psOidProceso;
  END LOOP;

  /* Recorriendo Lista de Clientes */
  OPEN cursorRegistro;
  LOOP
   FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
   IF tablaRegistro.COUNT > 0 THEN
    FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
     regRegistro := tablaRegistro(x);

     /*
     ln_monto := PER_FN_RECUP_MONTO_VENCI(
                  regRegistro.Oid_Clie,
                  NULL,
                  regRegistro.Fec_Fact,
                  NULL);

     BEGIN

      SELECT NVL(SUM(X.IMP_PEND),0)
      INTO ln_monto_perc
      FROM
       CCC_MOVIM_CUENT_CORRI X,
       CCC_SUBPR Y,
       CCC_PROCE Z
      WHERE X.CLIE_OID_CLIE = regRegistro.oid_clie
        AND X.FEC_VENC <= regRegistro.Fec_Fact
        AND X.IMP_PEND > 0
        AND SUBSTR(Z.COD_PROC,1,4) = 'CCCP'
        AND Y.OID_SUBP = X.SUBP_OID_SUBP_CREA
        AND Z.OID_PROC = Y.CCPR_OID_PROC;

      EXCEPTION

       WHEN NO_DATA_FOUND THEN
        ln_monto_perc := 0.00;

      END ;

      regRegistro.Sal_Unic := ln_monto + ln_monto_perc;

      */

      regRegistro.Sal_Unic := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(regRegistro.oid_clie,psCodPeriodo) -
                              CCC_PKG_GENER.CCC_FN_OBTIE_ABONO_PEND(regRegistro.oid_clie);
                                       
      if regRegistro.Sal_Unic < 0 then 
         regRegistro.Sal_Unic := 0;
      end if;
                                       
      regRegistro.sal_pago_min_flx :=  TO_NUMBER(regRegistro.Sal_Unic) - 
                                       FLX_PKG_PROCE.FLX_FN_OBTIE_SALDO_MAXIM(regRegistro.oid_clie,lnPeriodo) -
                                       CCC_PKG_GENER.CCC_FN_OBTIE_ABONO_PEND(regRegistro.oid_clie);



      /* Validando si el cliente ha sido coberturada la campa?a X-2 */
      SELECT COUNT(1)
      INTO lnIndicadorCobertura
      FROM sgr_famse_poliz_regis pr
      WHERE pr.cam_acti = GEN_FN_CALCU_PERIO( psCodPeriodo, -2 )
        AND pr.cod_clie = regRegistro.COD_CLIE;

      IF lnIndicadorCobertura > 0 THEN
       lsFamiliaProtegida := 'Cober.';
      ELSE
       lsFamiliaProtegida := 'No Cober.';
      END IF;

      /* Validando si el cliente ha pasado en el periodo anterior */
      BEGIN
       SELECT A.OID_SOLI_CABE
       INTO lnContador
       FROM
        PED_SOLIC_CABEC A,
        PED_TIPO_SOLIC_PAIS B,
        PED_TIPO_SOLIC C,
        CRA_PERIO D,
        SEG_PERIO_CORPO E
       WHERE  A.CLIE_OID_CLIE = regRegistro.oid_clie
                 AND A.PAIS_OID_PAIS = lnidpais
                 AND A.FEC_FACT IS NOT NULL
                 AND A.IND_OC = 1
                 AND C.IND_DEVO = 0
                 AND C.IND_ANUL = 0
                 AND E.COD_PERI = lsPeriodo2
                 AND B.OID_TIPO_SOLI_PAIS = A.TSPA_OID_TIPO_SOLI_PAIS
                 AND C.OID_TIPO_SOLI = B.TSOL_OID_TIPO_SOLI
                 AND D.OID_PERI = A.PERD_OID_PERI
                 AND E.OID_PERI = D.PERI_OID_PERI
                 AND rownum = 1;
           EXCEPTION
           WHEN NO_DATA_FOUND THEN
                regRegistro.Val_Obse := '*';
           END;

           --Paso Segundo Pedido
           lsPedido := NULL;
           BEGIN
             SELECT '2do Ped'
               INTO lsPedido
               FROM MAE_CLIEN_PRIME_CONTA
              WHERE CLIE_OID_CLIE = regRegistro.oid_clie
                AND PERD_OID_PERI = lnPeriodo2;
           EXCEPTION
             WHEN NO_DATA_FOUND THEN
                lsPedido := NULL;
           END;

           --Paso Tercer Pedido
           IF(lsPedido IS NULL) THEN
             BEGIN
               SELECT '3er Ped'
                 INTO lsPedido
                 FROM MAE_CLIEN_PRIME_CONTA
                WHERE CLIE_OID_CLIE = regRegistro.oid_clie
                  AND PERD_OID_PERI = lnPeriodo3;
             EXCEPTION
               WHEN NO_DATA_FOUND THEN
                 lsPedido := NULL;
             END;
           END IF;

           --Paso Cuarto Pedido
           IF(lsPedido IS NULL) THEN
             BEGIN
               SELECT '4to Ped'
                 INTO lsPedido
                 FROM MAE_CLIEN_PRIME_CONTA
                WHERE CLIE_OID_CLIE = regRegistro.oid_clie
                  AND PERD_OID_PERI = lnPeriodo4;
             EXCEPTION
               WHEN NO_DATA_FOUND THEN
                 lsPedido := NULL;
             END;
           END IF;

           --Actualizamos las observaciones que sera ahora conocido como Ciclo de Vida
           regRegistro.Val_Obse := TRIM(regRegistro.Val_Obse || ' ' || NVL(lsPedido,''));

           --Recuperando Telefono Movil de la Consultora
           BEGIN
             SELECT com.val_text_comu
               INTO lsTelefonoMovil
    			     FROM MAE_CLIEN_COMUN com, MAE_TIPO_COMUN tip
              WHERE com.ticm_oid_tipo_comu = tip.oid_tipo_comu
                AND com.clie_oid_clie = regRegistro.oid_clie
                AND tip.cod_tipo_comu = 'TM';
           EXCEPTION
             WHEN OTHERS THEN
               lsTelefonoMovil := '';
           END;

           /* Actualizando tabla */
           UPDATE PER_REPOR_CNTRL_ASIST_CONFE A
           SET
             A.SAL_UNIC = regRegistro.Sal_Unic,
             A.SAL_PAGO_MIN_FLX = regRegistro.sal_pago_min_flx,
             A.VAL_OBSE = regRegistro.Val_Obse,
             A.VAL_TELE_MOVI = lsTelefonoMovil,
             A.Val_Fami_Prot = lsFamiliaProtegida
           WHERE
             ROWID = regRegistro.id
             AND OID_PROC = psOidProceso;
         END LOOP;
      END IF;
      EXIT WHEN cursorRegistro%NOTFOUND;
  END LOOP;
  CLOSE cursorRegistro;
  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_CONTR_ASIST: '||ls_sqlerrm);
END PER_PR_GENER_CONTR_ASIST;
/***************************************************************************
Descripcion       : Devuelve Lista de Periodos de acuerdo
                    al tipo de Seleccion
                    'C' : Completo 18 REGISTROS
                    'U' : Ultimos 4 registros
Fecha Creacion    : 06/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_OBTIE_LISTA_PERIO
   (psCodPais    VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodPeriodo VARCHAR2,
    psSeleccion  VARCHAR2)
RETURN  TABLA_CODIGO_PERIODO PIPELINED
IS
  tablaRegistro   TABLA_CODIGO_PERIODO;
  CURSOR cursorRegistro IS
  WITH TEMPORAL AS
   (SELECT D.COD_PERI
    FROM
       SEG_PAIS  A,
       SEG_MARCA B,
       SEG_CANAL C,
       SEG_PERIO_CORPO D,
       CRA_PERIO E
    WHERE  A.COD_PAIS = psCodPais
       AND B.COD_MARC = psCodMarca
       AND C.COD_CANA = psCodCanal
       AND D.COD_PERI <= psCodPeriodo
       AND A.OID_PAIS = E.PAIS_OID_PAIS
       AND B.OID_MARC = E.MARC_OID_MARC
       AND C.OID_CANA = E.CANA_OID_CANA
       AND D.OID_PERI = E.PERI_OID_PERI
    ORDER BY E.FEC_FINA DESC
    )
   SELECT X.COD_PERI
   FROM TEMPORAL X
   WHERE
        ROWNUM <= 18;
  CURSOR cursorRegistroU4 IS
  WITH TEMPORAL AS
   (SELECT D.COD_PERI
    FROM
       SEG_PAIS  A,
       SEG_MARCA B,
       SEG_CANAL C,
       SEG_PERIO_CORPO D,
       CRA_PERIO E
    WHERE  A.COD_PAIS = psCodPais
       AND B.COD_MARC = psCodMarca
       AND C.COD_CANA = psCodCanal
       AND D.COD_PERI <= psCodPeriodo
       AND A.OID_PAIS = E.PAIS_OID_PAIS
       AND B.OID_MARC = E.MARC_OID_MARC
       AND C.OID_CANA = E.CANA_OID_CANA
       AND D.OID_PERI = E.PERI_OID_PERI
    ORDER BY E.FEC_FINA DESC
    )
   SELECT X.COD_PERI
   FROM TEMPORAL X
   WHERE
        ROWNUM <= 4;
BEGIN
  IF psSeleccion = 'C' THEN
      OPEN cursorRegistro;
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 PIPE ROW(tablaRegistro(x));
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro;
  ELSE
      OPEN cursorRegistroU4;
      LOOP
           FETCH cursorRegistroU4 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 PIPE ROW(tablaRegistro(x));
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistroU4%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistroU4;
  END IF;
  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_LISTA_PERIO: '||ls_sqlerrm);
END PER_FN_OBTIE_LISTA_PERIO ;
/***************************************************************************
Descripcion       : Genera data para Reporte de Estado de Cuentas Corrientes
                    Vendedoras
Fecha Creacion    : 07/12/2006
   psSeleccion    : 'C' : Completo
                    'U' : Ultimos 4 registros
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_ESTAD_CUENT_CORRI(
    psCodPais VARCHAR2,
    psCodMarca      VARCHAR2,
    psCodCanal      VARCHAR2,
    psCodRegion     VARCHAR2,
    psCodPeriodo    VARCHAR2,
    psCodZona       VARCHAR2,
    psCodSeccion    VARCHAR2,
    psCodTerritorio VARCHAR2,
    psSeleccion     VARCHAR2
    )
IS
  /* Declaracion de Variables */
  TYPE tRegTablaClien IS RECORD (
     OID_CLIE     PER_REPOR_ESTAD_CUENT_CORRI.OID_CLIE%TYPE,
     COD_CLIE     PER_REPOR_ESTAD_CUENT_CORRI.COD_CLIE%TYPE,
     NOM_CLIE     PER_REPOR_ESTAD_CUENT_CORRI.NOM_CLIE%TYPE,
     COD_ZONA     PER_REPOR_ESTAD_CUENT_CORRI.COD_ZONA%TYPE,
     COD_SECC     PER_REPOR_ESTAD_CUENT_CORRI.COD_SECC%TYPE,
     COD_TERR     PER_REPOR_ESTAD_CUENT_CORRI.COD_TERR%TYPE
   );
  TYPE tTablaCliente IS TABLE OF tRegTablaClien;
  tablaPeriodos   TABLA_CODIGO_PERIODO;
  regPeriodos     tRegCodigoPeriodo;
  regCliente      tRegTablaClien;
  tablaCliente    tTablaCliente;
  regRespon       GEN_PKG_GENER.tRegResponsableUniAdm;
  ls_codZona      PER_REPOR_ESTAD_CUENT_CORRI.Cod_Zona%TYPE;
  ls_NomGere      PER_REPOR_ESTAD_CUENT_CORRI.NOM_GERE%TYPE;
  lsCodPeriIni    SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriFin    SEG_PERIO_CORPO.COD_PERI%TYPE;
  W_FILAS         NUMBER:=25000;
  CURSOR cCliente IS
    SELECT
      OID_CLIE, COD_CLIE,
      NVL(TRIM(VAL_APE1),' ') || ' ' || NVL(TRIM(VAL_APE2),' ') || ' ' || NVL(TRIM(VAL_NOM1),' ') || ' ' || NVL(TRIM(VAL_NOM2),' '),
      COD_ZONA, COD_SECC, COD_TERR
    FROM TABLE(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_CLIEN_UNIAD
             (psCodPais, psCodMarca, psCodCanal,
              psCodRegion, psCodZona, psCodSeccion,
              psCodTerritorio))
    ORDER BY COD_ZONA, COD_TERR, COD_CLIE;
BEGIN
  DELETE FROM PER_REPOR_ESTAD_CUENT_CORRI;

  /* Obteniendo menor y maximo periodo */
  SELECT P.COD_PERI
  BULK COLLECT INTO tablaPeriodos
  FROM
     TABLE(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_LISTA_PERIO(
        psCodPais,psCodMarca, psCodCanal, psCodPeriodo, psSeleccion)) P;
  IF tablaPeriodos.COUNT > 0 THEN
      FOR x IN tablaPeriodos.FIRST .. tablaPeriodos.LAST LOOP
          regPeriodos := tablaPeriodos(x);
          IF (x = tablaPeriodos.FIRST) THEN
             lsCodPeriIni := regPeriodos.COD_PERI;
          END IF;
          IF (x = tablaPeriodos.LAST) THEN
             lsCodPeriFin := regPeriodos.COD_PERI;
          END IF;
      END LOOP;
  END IF;
  /* Recorriendo Lista de Clientes */
  ls_codZona := 'xxxx';
  OPEN cCliente;
  LOOP
      FETCH cCliente BULK COLLECT INTO tablaCliente LIMIT W_FILAS;
      IF tablaCliente.COUNT > 0 THEN
         /* Recorriendo la lista de Clientes */
        FOR x IN tablaCliente.FIRST .. tablaCliente.LAST LOOP
             regCliente := tablaCliente(x);
             IF regCliente.COD_ZONA <> ls_codZona THEN
                regRespon  := GEN_PKG_GENER.PER_FN_RECUP_RESPO_UNIAD
                           (psCodPais, psCodMarca, psCodCanal, psCodRegion, regCliente.COD_ZONA, NULL);
                ls_NomGere := nvl(TRIM(regRespon.VAL_APE1),' ') || ' ' || nvl(TRIM(regRespon.VAL_APE2),' ') || ' ' ||
                              nvl(TRIM(regRespon.VAL_NOM1),' ') || ' ' || nvl(TRIM(regRespon.VAL_NOM2),' ');
                ls_codZona := regCliente.COD_ZONA;
             END IF;
             /* Insertando Registros en la tabla de PER_REPOR_ESTAD_CUENT_CORRI */
             INSERT INTO PER_REPOR_ESTAD_CUENT_CORRI
             (COD_PERI_INIC, COD_PERI_FINA, OID_CLIE, COD_CLIE, NOM_CLIE, COD_ZONA,
              COD_SECC, COD_TERR, NOM_GERE, TIP_DOCU,
              NUM_DOCU, SER_DOCU, FEC_DOLE, IMP_TOTA_DOCU, IMP_SALD,
              IMP_PAGO, IMP_PERC, SAL_PERC )
             SELECT
                  lsCodPeriIni,
                  lsCodPeriFin,
                  regCliente.OID_CLIE,
                  regCliente.COD_CLIE,
                  regCliente.NOM_CLIE,
                  regCliente.COD_ZONA,
                  regCliente.COD_SECC,
                  regCliente.COD_TERR,
                  ls_NomGere,
                  H.COD_TIPO_DOCU,
                  G.NUM_DOCU_LEGA,
                  G.VAL_SERI_DOCU_LEGA,
                  F.FEC_EMIS_DOCU,
                  MAX(F.IMP_TOTA_DOCU),
                  SUM(F.IMP_CUOT_PEND),
                  SUM(F.IMP_CUOT_PAGA),
                  SUM(F.IMP_PEND_PERC),
                  SUM(F.IMP_CUOT_PEND + F.IMP_PEND_PERC)
             FROM
                  SEG_PAIS A,
                  SEG_SOCIE L,
                  PER_CUENT_CORRI_DOCLE F,
                  FAC_DOCUM_CONTA_CABEC G,
                  FAC_TIPO_DOCUM H,
                  TABLE(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_LISTA_PERIO(psCodPais,psCodMarca, psCodCanal,psCodPeriodo, psSeleccion)) P
             WHERE    F.PAIS_COD_PAIS = psCodPais
               AND    F.COD_MARC = psCodMarca
               AND    F.COD_CANA = psCodCanal
               AND    F.COD_PERI = P.COD_PERI
            AND    F.COD_CLIE = regCliente.Cod_Clie
               AND    F.FEC_VENC_CUOT <= SYSDATE
               AND   F.IMP_CUOT_PEND > 0
               AND    H.COD_TIPO_DOCU IN ('001','002','003','011','012')
            AND    F.PAIS_COD_PAIS = A.COD_PAIS
            AND   A.OID_PAIS = G.PAIS_OID_PAIS
            AND   F.COD_SOCI = L.COD_SOCI
            AND    L.OID_SOCI = G.SOCI_OID_SOCI
            AND    F.TIP_DOCU_LEGA = H.COD_TIPO_DOCU
            AND    H.OID_TIPO_DOCU = G.TIDO_OID_TIPO_DOCU
            AND    F.NUM_DOCU_INTE = G.NUM_DOCU_CONT_INTE
      GROUP BY H.COD_TIPO_DOCU,
                  G.NUM_DOCU_LEGA,
                  G.VAL_SERI_DOCU_LEGA,
                  F.FEC_EMIS_DOCU;
         END LOOP;
      END IF;
      EXIT WHEN cCliente%NOTFOUND;
  END LOOP;
  CLOSE cCliente;
  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_ESTAD_CUENT_CORRI: '||ls_sqlerrm);
END PER_PR_GENER_ESTAD_CUENT_CORRI;
/***************************************************************************
Descripcion       : Genera data para Reporte de Detalle de Cuentas Corrientes
                    Vendedoras
Fecha Creacion    : 07/12/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_DETAL_CUENT_CORRI(
    psCodPais       VARCHAR2,
    psCodMarca      VARCHAR2,
    psCodCanal      VARCHAR2,
    psCodRegion     VARCHAR2,
    psCodZona       VARCHAR2,
    psCodSeccion    VARCHAR2,
    psCodTerritorio VARCHAR2
    )
IS
  /* Declaracion de Variables */
  TYPE tRegTablaClien IS RECORD (
     OID_CLIE     PER_REPOR_DETAL_CUENT_CORRI.OID_CLIE%TYPE,
     COD_CLIE     PER_REPOR_DETAL_CUENT_CORRI.COD_CLIE%TYPE,
     NOM_CLIE     PER_REPOR_DETAL_CUENT_CORRI.NOM_CLIE%TYPE,
     APP_CLIE     PER_REPOR_DETAL_CUENT_CORRI.NOM_CLIE%TYPE,
     APM_CLIE     PER_REPOR_DETAL_CUENT_CORRI.NOM_CLIE%TYPE,
     COD_ZONA     PER_REPOR_DETAL_CUENT_CORRI.COD_ZONA%TYPE,
     COD_SECC     PER_REPOR_DETAL_CUENT_CORRI.COD_SECC%TYPE,
     COD_TERR     PER_REPOR_DETAL_CUENT_CORRI.COD_TERR%TYPE,
     COD_REGI     PER_REPOR_DETAL_CUENT_CORRI.COD_REGI%TYPE
   );
  TYPE tTablaCliente IS TABLE OF tRegTablaClien;
  regCliente      tRegTablaClien;
  tablaCliente    tTablaCliente;
  lnMonto         NUMBER;
  CURSOR cCliente IS
    SELECT
      OID_CLIE,
      COD_CLIE,
      NVL(TRIM(VAL_NOM1),' ') || ' ' || NVL(TRIM(VAL_NOM2),' '),
      NVL(TRIM(VAL_APE1),' '),
      NVL(TRIM(VAL_APE2),' '),
      COD_ZONA, COD_SECC, COD_TERR, COD_REGI
    FROM TABLE(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_CLIEN_UNIAD
             (psCodPais, psCodMarca, psCodCanal,
              psCodRegion, psCodZona, psCodSeccion,
              psCodTerritorio))
    ORDER BY COD_ZONA, COD_TERR, COD_CLIE;
BEGIN
  DELETE FROM PER_REPOR_DETAL_CUENT_CORRI;

  /* Recorriendo Lista de Clientes */
  OPEN cCliente;
  LOOP
      FETCH cCliente BULK COLLECT INTO tablaCliente LIMIT W_FILAS;
      IF tablaCliente.COUNT > 0 THEN
        FOR x IN tablaCliente.FIRST .. tablaCliente.LAST LOOP
             regCliente := tablaCliente(x);

             /* Insertando Registros en la tabla de PER_REPOR_ESTAD_CUENT_CORRI */
             INSERT INTO PER_REPOR_DETAL_CUENT_CORRI
             (OID_CLIE, COD_CLIE, NOM_CLIE, APP_CLIE, APM_CLIE,
              COD_ZONA, COD_SECC, COD_TERR, COD_REGI,
              TIP_DOCU, NUM_BOLE, NUM_DOCU, SER_DOCU, FEC_DOLE,
              IMP_TOTA_DOCU, IMP_SALD)
             SELECT
                      regCliente.OID_CLIE,
                      regCliente.COD_CLIE,
                      regCliente.NOM_CLIE,
                      regCliente.APP_CLIE,
                      regCliente.APM_CLIE,
                      regCliente.COD_ZONA,
                      regCliente.COD_SECC,
                      regCliente.COD_TERR,
                      regCliente.COD_REGI,
                      H.COD_TIPO_DOCU,
                      J.VAL_NUME_SOLI,
                      G.NUM_DOCU_LEGA,
                      G.VAL_SERI_DOCU_LEGA,
                      F.FEC_EMIS_DOCU,
                      MAX(F.IMP_TOTA_DOCU),
                      SUM(F.IMP_CUOT_PEND)
                 FROM
                      SEG_PAIS A,
                      SEG_SOCIE L,
                      PER_CUENT_CORRI_DOCLE F,
                      FAC_DOCUM_CONTA_CABEC G,
                      FAC_TIPO_DOCUM H,
                      PED_SOLIC_CABEC J,
                      MAE_CLIEN X
                 WHERE   F.PAIS_COD_PAIS = psCodPais
                   AND   F.COD_MARC = psCodMarca
                   AND   F.COD_CANA = psCodCanal
                   AND   F.COD_CLIE = regCliente.Cod_Clie
                   AND   F.FEC_VENC_CUOT <= SYSDATE
                   AND   F.IMP_CUOT_PEND > 0
                   AND   H.COD_TIPO_DOCU IN ('001','002','003','011','012')
                   AND   F.PAIS_COD_PAIS = A.COD_PAIS
                   AND   A.OID_PAIS = G.PAIS_OID_PAIS
                   AND   F.COD_SOCI = L.COD_SOCI
                   AND   L.OID_SOCI = G.SOCI_OID_SOCI
                   AND   F.TIP_DOCU_LEGA = H.COD_TIPO_DOCU
                   AND   H.OID_TIPO_DOCU = G.TIDO_OID_TIPO_DOCU
                   AND   F.NUM_DOCU_INTE = G.NUM_DOCU_CONT_INTE
                   AND   J.OID_SOLI_CABE = G.SOCA_OID_SOLI_CABE
                   AND   X.COD_CLIE = F.COD_CLIE
                   AND   X.OID_CLIE = J.CLIE_OID_CLIE
             GROUP BY
                      H.COD_TIPO_DOCU,
                      J.VAL_NUME_SOLI,
                      G.NUM_DOCU_LEGA,
                      G.VAL_SERI_DOCU_LEGA,
                      F.FEC_EMIS_DOCU;
              /* OBTIENIENDO SALDO A FAVOR */
              SELECT
                   SUM(A.IMP_PEND)
              INTO
                   lnMonto
              FROM
                  CCC_MOVIM_CUENT_CORRI A
              WHERE   A.CLIE_OID_CLIE = regCliente.OID_Clie
                  AND A.IMP_PEND <> 0;
             IF (lnMonto < 0) THEN
                 lnMonto := -lnMonto;
                 -- Insertando Registros en la tabla de PER_REPOR_ESTAD_CUENT_CORRI
                 INSERT INTO PER_REPOR_DETAL_CUENT_CORRI
                 (OID_CLIE, COD_CLIE, NOM_CLIE, APP_CLIE, APM_CLIE,
                  COD_ZONA, COD_SECC, COD_TERR, COD_REGI,
                  TIP_DOCU, NUM_BOLE, NUM_DOCU, SER_DOCU, FEC_DOLE,
                  IMP_TOTA_DOCU, IMP_SALD, IMP_SALD_FAVO)
                 VALUES
                 (
                      regCliente.OID_CLIE,
                      regCliente.COD_CLIE,
                      regCliente.NOM_CLIE,
                      regCliente.APP_CLIE,
                      regCliente.APM_CLIE,
                      regCliente.COD_ZONA,
                      regCliente.COD_SECC,
                      regCliente.COD_TERR,
                      regCliente.COD_REGI,
                      NULL,
                      NULL,
                      NULL,
                      NULL,
                      NULL,
                      NULL,
                      NULL,
                      lnMonto);
             END IF;
         END LOOP;
         /* Eliminando registros con monto pendiente = 0 */
         DELETE FROM PER_REPOR_DETAL_CUENT_CORRI X
         WHERE
            (X.IMP_SALD = 0 OR X.IMP_SALD IS NULL) AND
            (X.IMP_SALD_FAVO = 0 OR X.IMP_SALD_FAVO IS NULL);
      END IF;
      EXIT WHEN cCliente%NOTFOUND;
  END LOOP;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_DETAL_CUENT_CORRI: '||ls_sqlerrm);
END PER_PR_GENER_DETAL_CUENT_CORRI;
/***************************************************************************
Descripcion       : Genera data para Reporte de Facturas Pendientes x
                    Campa?a
Fecha Creacion    : 12/12/2006
Autor             : Carlos Bazalar

Modificacion      : Se cambio PER_CUENT_CORRI_DOCLE por CCC_MOVIM_CUENT_CORRI
Fecha Modificacion: 10/05/2007
AP                :  Marco Agurto
Codigo            :  PR-00002

Modificacion      : Se modifico el calculo de la deuda
Fecha Modificacion: 10/05/2007
AP                :  Marco Agurto
Codigo            :  PR-00001

***************************************************************************/
PROCEDURE PER_PR_GENER_FACTU_PENDE_CAMPA(
    psCodPais       VARCHAR2,
    psCodMarca      VARCHAR2,
    psCodCanal      VARCHAR2,
    psCodPeriodo    VARCHAR2,
    psCodRegion     VARCHAR2,
    psMontoMinimo   VARCHAR2,
    psCodZona       VARCHAR2,
    psCodSeccion    VARCHAR2,
    psCodTerritorio VARCHAR2
    )
IS
  /* Declaracion de Variables */
  TYPE tRegTablaClien IS RECORD (
     OID_CLIE     PER_REPOR_ESTAD_CUENT_CORRI.OID_CLIE%TYPE,
     COD_CLIE     PER_REPOR_ESTAD_CUENT_CORRI.COD_CLIE%TYPE,
     NOM_CLIE     PER_REPOR_FACTU_PENDI_CAMPA.NOM_CLIE%TYPE,
     COD_ZONA     PER_REPOR_ESTAD_CUENT_CORRI.COD_ZONA%TYPE,
     COD_SECC     PER_REPOR_ESTAD_CUENT_CORRI.COD_SECC%TYPE,
     COD_TERR     PER_REPOR_ESTAD_CUENT_CORRI.COD_TERR%TYPE,
     IMP_PERCE    NUMBER(12,2)
   );
  TYPE tTablaCliente IS TABLE OF tRegTablaClien;
  regCliente      tRegTablaClien;
  tablaCliente    tTablaCliente;

  TYPE tRegTablaDeuda IS RECORD (
     OID_MOVI_CC          CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC%TYPE,
     IMP_PEND             CCC_MOVIM_CUENT_CORRI.IMP_PEND%TYPE,
     FEC_FACT             CCC_MOVIM_CUENT_CORRI.FEC_VALO%TYPE,
     IMP_BOLE             NUMBER(12,2),
     IMP_BOLE_NETO        NUMBER(12,2),
     VAL_ULTI_NUME_HIST   CCC_MOVIM_CUENT_CORRI.VAL_ULTI_NUME_HIST%TYPE
   );

  TYPE tTablaDeuda IS TABLE OF tRegTablaDeuda;

  regDeuda        tRegTablaDeuda;
  tablaDeuda      tTablaDeuda;
  regRespon       GEN_PKG_GENER.tRegResponsableUniAdm;
  lnIdPais        seg_pais.oid_pais%TYPE;
  lnIdCanal       seg_canal.oid_cana%TYPE;
  lnIdMarca       seg_marca.oid_marc%TYPE;
  lsPeriodoMas1   SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodoMenos1 SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsDireClien     PER_REPOR_FACTU_PENDI_CAMPA.DIR_CLIE%TYPE;
  lsDescUbigeo    PER_REPOR_FACTU_PENDI_CAMPA.DES_GEOG%TYPE;
  lsOrde1         ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE;
  lsOrde2         ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE;
  lsOrde3         ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE;
  lsOrde4         ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE;
  lsCodTipoDocu   MAE_TIPO_DOCUM.VAL_SIGL%TYPE;
  lsNumDocuIden   MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE;
  lsNomGeren      PER_REPOR_FACTU_PENDI_CAMPA.NOM_GERE%TYPE;
  ldFechaVen      DATE;
  ldFechaVen1     DATE;
  lsCodZona       PER_REPOR_FACTU_PENDI_CAMPA.Cod_Zona%TYPE;
  /*PR-0002*/
  ls_importeBoletaCDR NUMBER;
  ls_numeroBoleta  PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
 /*PR-0002*/

  /* Cursor con Lista de Clientes */
  CURSOR cCliente IS
  SELECT
    OID_CLIE,
    COD_CLIE,
    NVL(TRIM(VAL_NOM1),' ') ||' '|| NVL(TRIM(VAL_NOM2),' ')||' '|| NVL(TRIM(VAL_APE1),' ')||' '|| NVL(TRIM(VAL_APE2),' '),
    COD_ZONA,
    COD_SECC,
    COD_TERR,
    0.0 AS IMP_PERC
  FROM TABLE(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_CLIEN_UNIAD_PERIO(
            psCodPais, psCodMarca, psCodCanal, psCodPeriodo,
            psCodRegion, psCodZona, psCodSeccion,
            psCodTerritorio))
  ORDER BY COD_ZONA, COD_SECC, COD_TERR, COD_CLIE;

  /* Cursor con la lista de deudas por cliente */
  CURSOR cDeuda(vnIdPeriodo NUMBER)  IS
     SELECT  A.OID_MOVI_CC,
             A.IMP_PEND,
             A.FEC_VALO AS FEC_FACT,
             A.IMP_MOVI AS IMP_BOLE,
             0.0 AS IMP_BOLE_NETO,
             A.VAL_ULTI_NUME_HIST
     FROM
        CCC_MOVIM_CUENT_CORRI A,
        CCC_SUBPR B,
        CCC_PROCE C
     WHERE A.SUBP_OID_SUBP_CREA = B.OID_SUBP
       AND B.CCPR_OID_PROC = C.OID_PROC
       AND A.CLIE_OID_CLIE = regCliente.OID_CLIE
       AND A.IMP_MOVI > 0
       AND ((B.COD_SUBP = 1 AND C.COD_PROC = 'CCC001') OR
            (B.COD_SUBP = 4 AND  C.COD_PROC = 'CCC003'))
       AND A.PERD_OID_PERI = vnIdPeriodo;
           --OR
           --(A.PERD_OID_PERI = vnIdPeriodoMenos1 AND A.FEC_VENC  >=  vdFechaVen))   ;

  CURSOR cReporte IS
    SELECT DISTINCT cod_zona
    FROM PER_REPOR_FACTU_PENDI_CAMPA
    ORDER BY COD_ZONA;

  CURSOR cTotalDeuda IS
     SELECT A.COD_CLIE, SUM(A.IMP_DEUD) AS TOT_DEUD
     FROM PER_REPOR_FACTU_PENDI_CAMPA A
     GROUP BY A.COD_CLIE  ;

  lnPagina NUMBER;
  lnX      NUMBER;
  lnIdPeriodo NUMBER;
  lnIdPeriodoMenos1 NUMBER;
  lnMontoNeto       NUMBER;
  lnSaldoPendiente  NUMBER;
  lncontadorZona    NUMBER;
  lncontadorRegion  NUMBER;
  lnItem            NUMBER;


 BEGIN

  DELETE FROM PER_REPOR_FACTU_PENDI_CAMPA;

  lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lsPeriodoMas1 := PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, 1);
  lsPeriodoMenos1:= PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
  lnIdPeriodo   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodPeriodo);
  lnIdPeriodoMenos1 := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsPeriodoMenos1);

  /* Leyendo informacion del Cliente x Unidad Administrativa */
  lsCodZona  := 'XXXX';
  lnPagina := 0;

  OPEN cCliente;
  LOOP
   FETCH cCliente BULK COLLECT INTO tablaCliente LIMIT W_FILAS;
   IF tablaCliente.COUNT > 0 THEN

    lnPagina := lnPagina + 1;
    /* Recorriendo la lista de Clientes */
    FOR x IN tablaCliente.FIRST .. tablaCliente.LAST LOOP

     regCliente := tablaCliente(x);
     lnItem := 0;
     lnX := x;

     IF lnPagina = 11 AND lnX = 1000 THEN

      lnX := x;

     END IF;

     /* Obteniendo Fechas de Vencimiento */
     IF regCliente.Cod_Zona <> lsCodZona THEN
      lsCodZona   := regCliente.COD_ZONA;
      ldFechaVen  := PER_FN_RECUP_FECHA_FACTU(
                                 psCodPais, psCodMarca,  psCodCanal,
                                 regCliente.Cod_Zona, psCodPeriodo) ;
      ldFechaVen1 := PER_FN_RECUP_FECHA_FACTU(psCodPais, psCodMarca,  psCodCanal,
                                 regCliente.Cod_Zona, lsPeriodoMas1);

      /* Obteniendo Gerente de zona */
      regRespon  := GEN_PKG_GENER.per_fn_recup_respo_uniad(psCodPais, psCodMarca, psCodCanal, psCodRegion, regCliente.cod_zona, NULL);
      lsNomGeren := TRIM(regRespon.VAL_APE1) ||' '||TRIM(regRespon.VAL_APE2) ||' '|| TRIM(regRespon.VAL_NOM1) ||' '|| TRIM(regRespon.VAL_NOM2);
     END IF;

             /* Se recupera la direccion del cliente */
             SELECT
                 (SELECT (NVL(TRIM(D.DES_ABRV_TIPO_VIA),' ') ||' '|| NVL(TRIM(C.NOM_VIA),' '))
                  FROM
                    ZON_VIA C,
                    SEG_TIPO_VIA D
                  WHERE  C.PAIS_OID_PAIS = lnIdPais
                    AND C.OID_VIA = A.ZVIA_OID_VIA
                    AND D.OID_TIPO_VIA = A.TIVI_OID_TIPO_VIA) ||' '||TRIM(A.NUM_PPAL)||' '||TRIM(A.VAL_OBSE),
                 (SELECT ZONA.DES_GEOG
               FROM ZON_VALOR_ESTRU_GEOPO ZONA
                 WHERE ZONA.ORDE_1 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,1,6)))
                  AND  ZONA.ORDE_2  IS NULL
                  AND  ZONA.ORDE_3  IS NULL
                  AND  ZONA.ORDE_4  IS NULL
                  AND  ZONA.PAIS_OID_PAIS = lnIdPais
                AND  ROWNUM = 1) ORDE_1,
                 (SELECT ZONA.DES_GEOG
               FROM ZON_VALOR_ESTRU_GEOPO ZONA
                  WHERE ZONA.ORDE_1 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,1,6)))
                  AND  ZONA.ORDE_2 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,7,6)))
                  AND  ZONA.ORDE_3  IS NULL
                  AND  ZONA.ORDE_4  IS NULL
                  AND  ZONA.PAIS_OID_PAIS = lnIdPais
                AND  ROWNUM = 1) ORDE_2,
                 (SELECT ZONA.DES_GEOG
               FROM ZON_VALOR_ESTRU_GEOPO ZONA
                  WHERE ZONA.ORDE_1 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,1,6)))
                  AND  ZONA.ORDE_2 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,7,6)))
                  AND  ZONA.ORDE_3 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,13,6)))
                  AND  ZONA.ORDE_4  IS NULL
                  AND  ZONA.PAIS_OID_PAIS = lnIdPais
                    AND  ROWNUM = 1) ORDE_3,
                 (SELECT ZONA.DES_GEOG
               FROM ZON_VALOR_ESTRU_GEOPO ZONA
                  WHERE ZONA.ORDE_1 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,1,6)))
                  AND  ZONA.ORDE_2 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,7,6)))
                  AND  ZONA.ORDE_3 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,13,6)))
                  AND  ZONA.ORDE_4 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,19,6)))
                  AND  ZONA.ORDE_5  IS NULL
                  AND  ZONA.PAIS_OID_PAIS = lnIdPais
                AND  ROWNUM = 1) ORDE_4
             INTO
                 lsDireClien,
                 lsOrde1,
                 lsOrde2,
                 lsOrde3,
                 lsOrde4
             FROM
                 MAE_CLIEN_DIREC A
             WHERE   A.CLIE_OID_CLIE = regCliente.OID_CLIE
                 AND A.IND_DIRE_PPAL = 1
                 AND A.IND_ELIM = 0;
             lsDescUbigeo :=  NVL(TRIM(lsDireClien),' ') ||' '|| NVL(TRIM(lsOrde1),' ') ||' '|| NVL(TRIM(lsOrde2),' ') ||' '|| NVL(TRIM(lsOrde3),' ') ||' '|| NVL(TRIM(lsOrde4),' ');

             /* Se recupera el documento de identidad del cliente */
             BEGIN
               SELECT
                  B.VAL_SIGL,
                  A.NUM_DOCU_IDEN
               INTO
                  lsCodTipoDocu,
                  lsNumDocuIden
               FROM
                  MAE_CLIEN_IDENT A,
                  MAE_TIPO_DOCUM B
               WHERE
                  A.CLIE_OID_CLIE = regCliente.OID_CLIE AND
                  A.VAL_IDEN_DOCU_PRIN = 1 AND
                  A.TDOC_OID_TIPO_DOCU = B.OID_TIPO_DOCU;
             EXCEPTION
             WHEN no_data_found THEN
                  lsCodTipoDocu := NULL;
                  lsNumDocuIden := NULL;
             END;

             /* Obteniendo monto de percepcion */
             BEGIN

                 SELECT SUM(A.IMP_PEND)
                 INTO
                    regCliente.IMP_PERCE
                 FROM CCC_MOVIM_CUENT_CORRI A,
                      CCC_SUBPR B,
                      CCC_PROCE C
                 WHERE A.SUBP_OID_SUBP_CREA = B.OID_SUBP
                   AND B.CCPR_OID_PROC  = C.OID_PROC
                   AND A.IMP_MOVI > 0
                   AND A.CLIE_OID_CLIE = regCliente.OID_CLIE
                   AND ((C.COD_PROC = 'CCCP01' AND B.COD_SUBP = 1) OR
                        (C.COD_PROC = 'CCCP03' AND B.COD_SUBP = 4)
                        )
                   AND A.PERD_OID_PERI = lnIdPeriodo ;

             EXCEPTION
             WHEN no_data_found THEN
                 regCliente.IMP_PERCE := 0.0;
             END;

             /* obteniendo monto de deudas */
             OPEN cDeuda(lnIdPeriodo) ;
             LOOP
               FETCH cDeuda BULK COLLECT INTO tablaDeuda LIMIT W_FILAS;
               IF tablaDeuda.COUNT > 0 THEN
                  FOR y IN tablaDeuda.FIRST .. tablaDeuda.LAST LOOP
                      regDeuda := tablaDeuda(y);
                      lnItem :=  lnItem + 1;
                      ls_importeBoletaCDR:= PER_FN_OBTIE_TOTA_BOLE_CDR(regCliente.OID_CLIE, regDeuda.OID_MOVI_CC, regDeuda.VAL_ULTI_NUME_HIST);
                      regDeuda.IMP_BOLE_NETO := regDeuda.IMP_BOLE - ls_importeBoletaCDR;

                      /* Buscando Nro de Boleta */
                      ls_numeroBoleta := NULL;
                      BEGIN
                        SELECT A.VAL_NUME_SOLI
                        INTO  ls_numeroBoleta
                        FROM PED_SOLIC_CABEC A,
                             CCC_MOVIM_CUENT_CORRI b
                        where B.OID_MOVI_CC = regDeuda.OID_MOVI_CC
                          AND A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE;
                      EXCEPTION
                      WHEN NO_DATA_FOUND THEN
                           ls_numeroBoleta := NULL;
                      END;

                      /* Insertando en tabla del reporte */
                      INSERT INTO PER_REPOR_FACTU_PENDI_CAMPA
                       (OID_CLIE, COD_CLIE, NOM_CLIE,
                        DIR_CLIE, DES_UBIG,
                        COD_ZONA, COD_SECC,
                        NOM_GERE, COD_GERE,
                        COD_TERR, CAM_INGR,
                        FEC_VENC, FEC_VEN1,
                        TIP_DOCU, NUM_DOCU,
                        FEC_FACT, NRO_BOLE,
                        IMP_MONT_NETO, IMP_SALD_PEND,
                        IMP_PERC, IMP_DEUD, IMP_CDR,
                        NUM_ITEM
                        )
                      VALUES
                      ( regCliente.OID_CLIE, regCliente.COD_CLIE, regCliente.NOM_CLIE,
                        lsDireClien, lsDescUbigeo,
                        regCliente.COD_ZONA, regCliente.COD_SECC,
                        lsNomGeren, regRespon.COD_CLIE,
                        regCliente.COD_TERR, NULL,
                        ldFechaVen, ldFechaVen1,
                        lsCodTipoDocu, lsNumDocuIden,
                        regDeuda.FEC_FACT, ls_numeroBoleta,
                        NVL(regDeuda.IMP_BOLE_NETO,0),
                        NVL(regDeuda.IMP_PEND,0),
                        NVL(regCliente.IMP_PERCE,0),
                        NVL(regDeuda.IMP_PEND,0) + NVL(regCliente.IMP_PERCE,0),
                        NVL(ls_importeBoletaCDR,0),
                        lnItem
                       );

                       regCliente.IMP_PERCE := 0.00;
                  END LOOP;
               END IF;
               EXIT WHEN cDeuda%NOTFOUND;
             END LOOP;
             CLOSE cDeuda;
         END LOOP;
      END IF;
      EXIT WHEN cCliente%NOTFOUND;
  END LOOP;
  CLOSE cCliente;

  /* Encontrando totales ZONAS*/
  FOR cZona IN cReporte LOOP
      lnMontoNeto:= 0.0;
      lnSaldoPendiente := 0.0;
      SELECT SUM(A.IMP_MONT_NETO), SUM(A.IMP_SALD_PEND) - SUM(A.IMP_PERC)
      INTO lnMontoNeto, lnSaldoPendiente
      FROM PER_REPOR_FACTU_PENDI_CAMPA A
      WHERE A.COD_ZONA = cZona.Cod_Zona
        AND A.NRO_BOLE IS NOT NULL;

      SELECT COUNT(DISTINCT A.COD_CLIE)
      INTO lnContadorZona
      FROM PER_REPOR_FACTU_PENDI_CAMPA A
      WHERE COD_ZONA = cZona.Cod_Zona;

      UPDATE PER_REPOR_FACTU_PENDI_CAMPA A
      SET A.IMP_FACT_ZONA = lnMontoNeto,
          A.IMP_SALD_PEND_ZONA = lnSaldoPendiente,
          A.POR_ZONA = (1 - lnSaldoPendiente/lnMontoNeto) * 100,
          A.TOT_CLIE_ZONA = lnContadorZona
      WHERE A.COD_ZONA = cZona.Cod_Zona;
  END LOOP;

  /* Encontrando totales REGION */
  SELECT SUM(A.IMP_MONT_NETO), SUM(A.IMP_SALD_PEND) - SUM(A.IMP_PERC)
  INTO lnMontoNeto, lnSaldoPendiente
  FROM PER_REPOR_FACTU_PENDI_CAMPA A
  WHERE A.NRO_BOLE IS NOT NULL;

  SELECT COUNT(DISTINCT A.COD_CLIE)
  INTO lnContadorRegion
  FROM PER_REPOR_FACTU_PENDI_CAMPA A;

  UPDATE PER_REPOR_FACTU_PENDI_CAMPA A
  SET A.IMP_FACT_REGI = lnMontoNeto,
      A.IMP_SALD_PEND_REGI = lnSaldoPendiente,
      A.POR_REGI = (1 - lnSaldoPendiente/lnMontoNeto) * 100,
      A.TOT_CLIE_REGI =lnContadorRegion;

  /* Encontrando totales x cliente */
  FOR ctotal IN cTotalDeuda LOOP
      UPDATE PER_REPOR_FACTU_PENDI_CAMPA A
      SET A.TOT_DEUD_CLIE = ctotal.tot_deud
      WHERE A.COD_CLIE = ctotal.cod_clie
        AND A.NUM_ITEM = 1;
  END LOOP;


 EXCEPTION

  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_FACTU_PENDE_CAMPA: '||ls_sqlerrm||lnPagina||'  '||lnX );
END PER_PR_GENER_FACTU_PENDE_CAMPA;
/***************************************************************************
Descripcion       : Genera data para Reporte de Base de Recuperacion de
                    Campa?as
Fecha Creacion    : 12/12/2006
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE PER_PR_GENER_BASE_RECU_CAMPA(
    psCodPais         VARCHAR2,
    psCodMarca        VARCHAR2,
    psCodCanal        VARCHAR2,
    psCodRegion       VARCHAR2,
    psCodZona         VARCHAR2,
    psCodPeriodoDesde VARCHAR2,
    psCodPeriodoHasta VARCHAR2,
    psCodSubgerencia  VARCHAR2,
    psTipoVista         varchar2
)
IS
 FILAS_CURSOR  NUMBER:= 150000;
 TYPE TABLA_REGISTRO IS TABLE OF PER_REPOR_BASE_RECUP_CAMPA%ROWTYPE;
 TablaRegistro   TABLA_REGISTRO;
 CURSOR cRegistro(OidPais NUMBER, OidMarca NUMBER, OidCanal NUMBER, OidPeriodoInicio NUMBER, OidPeriodoFinal NUMBER, oidSubGerencia NUMBER, oidRegion NUMBER, oidZona NUMBER) iS
  SELECT CCC_MOVIM_CUENT_CORRI.IMP_MOVI,
          CCC_MOVIM_CUENT_CORRI.IMP_PEND,
          --CCC_MOVIM_CUENT_CORRI.IMP_PAGA,
          decode(CCC_HISTO_MOVIM_CC.NUM_HIST,0,CCC_MOVIM_CUENT_CORRI.IMP_PAGO,CCC_HISTO_MOVIM_CC.IMP_PAGO) as IMP_PAGA,
          CCC_MOVIM_CUENT_CORRI.FEC_DOCU,
          CCC_HISTO_MOVIM_CC.MVCC_OID_MOVI_CC as  OID_MOVI_CC,
          gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI) AS COD_PERI,
          CCC_PROCE.COD_PROC,
          CCC_SUBPR.COD_SUBP,
          decode(CCC_HISTO_MOVIM_CC.NUM_HIST,0,CCC_MOVIM_CUENT_CORRI.FEC_ULTI_MOVI,CCC_HISTO_MOVIM_CC.FEC_MOVI) as FEC_MOVI,
          --CCC_MOVIM_CUENT_CORRI.FEC_ULTI_MOVI AS FEC_MOVI,
          --  CCC_HISTO_MOVIM_CC.FEC_MOVI,
          (SELECT ZON_REGIO.COD_REGI FROM zon_regio WHERE zon_regio.oid_regi = CCC_MOVIM_CUENT_CORRI.ZORG_OID_REGI ) AS COD_REGI,
          (SELECT ZON_REGIO.DES_REGI FROM zon_regio WHERE zon_regio.oid_regi = CCC_MOVIM_CUENT_CORRI.ZORG_OID_REGI ) AS DES_REGI,
          decode(psTipoVista,'1','','2',
          GEN_PKG_GENER.PER_FN_RECUP_RESPO_UNIAD(psCodPais,
          psCodMarca,psCodCanal,(SELECT ZON_REGIO.COD_REGI FROM zon_regio WHERE zon_regio.oid_regi = CCC_MOVIM_CUENT_CORRI.ZORG_OID_REGI ),null,null,
          'O'),GEN_PKG_GENER.PER_FN_RECUP_RESPO_UNIAD(psCodPais,
          psCodMarca,psCodCanal,(SELECT ZON_REGIO.COD_REGI FROM zon_regio WHERE zon_regio.oid_regi = CCC_MOVIM_CUENT_CORRI.ZORG_OID_REGI ),gen_pkg_gener.GEN_FN_DEVUELVE_DES_ZONA_SECC(CCC_MOVIM_CUENT_CORRI.ZSCC_OID_SECC, 'COD'),null,
          'O') ) as OID_CLIE,
          gen_pkg_gener.GEN_FN_DEVUELVE_DES_ZONA_SECC(CCC_MOVIM_CUENT_CORRI.ZSCC_OID_SECC, 'COD') AS cod_zona,
          gen_pkg_gener.GEN_FN_DEVUELVE_DES_ZONA_SECC(CCC_MOVIM_CUENT_CORRI.ZSCC_OID_SECC, 'DES') AS des_zona
  FROM CCC_MOVIM_CUENT_CORRI,
       CCC_HISTO_MOVIM_CC,
       CCC_PROCE,
       CCC_SUBPR
  WHERE CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI <=OidPeriodoFinal
        AND CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI >=OidPeriodoInicio
        AND CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC = CCC_HISTO_MOVIM_CC.MVCC_OID_MOVI_CC
        AND CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_CREA = CCC_SUBPR.OID_SUBP
        AND CCC_MOVIM_CUENT_CORRI.MARC_OID_MARC = OidMarca
        AND CCC_MOVIM_CUENT_CORRI.ZSGV_OID_SUBG_VENT = decode(oidSubGerencia, NULL,CCC_MOVIM_CUENT_CORRI.ZSGV_OID_SUBG_VENT, oidSubGerencia )
        AND CCC_MOVIM_CUENT_CORRI.ZORG_OID_REGI =  decode(oidRegion, NULL,CCC_MOVIM_CUENT_CORRI.ZORG_OID_REGI, oidRegion )
        AND EXISTS (SELECT * FROM  ZON_SECCI
                    WHERE ZON_SECCI.OID_SECC    = CCC_MOVIM_CUENT_CORRI.ZSCC_OID_SECC
                          AND ZON_SECCI.ZZON_OID_ZONA=   decode(oidZona, NULL,ZON_SECCI.ZZON_OID_ZONA, oidZona ))
        AND CCC_PROCE.OID_PROC = CCC_SUBPR.CCPR_OID_PROC
        AND ((ccc_proce.COD_PROC = 'CCC001') OR
        (ccc_proce.COD_PROC = 'CCC003' AND ccc_subpr.COD_SUBP = 4) OR
        (ccc_proce.COD_PROC = 'CCC006') OR
        (ccc_proce.COD_PROC = 'CCCN01' AND ccc_subpr.COD_SUBP = 1) OR
        (ccc_proce.COD_PROC = 'CCCG01' AND ccc_subpr.COD_SUBP = 1) OR
        (ccc_proce.COD_PROC = 'CCCI01' AND ccc_subpr.COD_SUBP = 1) OR
        (ccc_proce.COD_PROC = 'CCCP01' AND ccc_subpr.COD_SUBP = 1)) ;
  lsIdPais         NUMBER;
  lsIdMarca        NUMBER;
  lsIdCanal        NUMBER;
  lsIdPeriodoDesde NUMBER;
  lsIdPeriodoHasta NUMBER;
  lsIdRegion       NUMBER;
  lsIdZona         NUMBER;
  lsIdSubgerencia  NUMBER;
BEGIN
  --- Eliminamos todos los registros de la tabla PER_REPOR_BASE_RECUP_CAMPA ---
  DELETE FROM PER_REPOR_BASE_RECUP_CAMPA;

  lsIdPais         := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
  lsIdMarca        := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
  lsIdCanal        := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);
  lsIdPeriodoDesde := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodoDesde, lsIdMarca,  lsIdCanal);
  lsIdPeriodoHasta := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodoHasta, lsIdMarca,  lsIdCanal);

  IF psCodRegion IS NOT NULL THEN
     lsIdRegion := gen_pkg_gener.GEN_FN_DEVUELVE_ID_REGION(psCodPais , psCodMarca , psCodCanal, psCodRegion  );
     IF psCodZona IS NOT NULL THEN
        lsIdZona := gen_pkg_gener.GEN_FN_DEVUELVE_ID_ZONA(psCodPais , psCodMarca , psCodCanal, psCodRegion, psCodZona   );
     ELSE
        lsIdZona := NULL;
     END IF;
  ELSE
     lsIdRegion := NULL;
  END IF;

  IF psCodSubgerencia IS NOT NULL THEN
      lsIdSubgerencia := gen_pkg_gener.GEN_FN_DEVUELVE_ID_SUB_GEREN( psCodSubgerencia);
  ELSE
      lsIdSubgerencia := NULL;
  END IF;

  /* Leyendo informacion del Cliente x Unidad Administrativa */
  OPEN cRegistro(lsIdPais, lsIdMarca,lsIdCanal , lsIdPeriodoDesde , lsIdPeriodoHasta, lsIdSubgerencia, lsIdRegion, lsIdZona);
  LOOP
      FETCH cRegistro BULK COLLECT INTO tablaRegistro LIMIT FILAS_CURSOR;
      IF tablaRegistro.COUNT > 0 THEN
         FORALL x IN tablaRegistro.FIRST .. tablaRegistro.LAST
             INSERT INTO PER_REPOR_BASE_RECUP_CAMPA
             VALUES tablaRegistro(x);
      END IF;
      EXIT WHEN cRegistro%NOTFOUND;
  END LOOP;
  CLOSE cRegistro;
END PER_PR_GENER_BASE_RECU_CAMPA;

PROCEDURE PER_PR_GENER_CONSO_COBRANZA(
    fechaDesde VARCHAR2,
    fechaHasta VARCHAR2
)
IS
fechaDesdeDate Date;
fechaHastaDate Date;
correlativo Number;
begin
fechaDesdeDate:= to_date(fechaDesde, 'dd/mm/yyyy');
fechaHastaDate:= to_date(fechaHasta, 'dd/mm/yyyy');
correlativo:=1;
while (fechaDesdeDate <= fechaHastaDate)
loop
 -- insert into PER_CONSO_COBRANZA_TEMPO values(correlativo,to_char(fechaDesdeDate,'dd/mm/yyyy'));
  correlativo:=correlativo+1;
  fechaDesdeDate:=fechaDesdeDate+1;
end loop;
commit;
END PER_PR_GENER_CONSO_COBRANZA;
/***************************************************************************
Descripcion       : Genera data para Reporte de Consolidado de Cobranzas los
                    importes necesarios
Fecha Creacion    : 08/01/2007
Autor             : Marco Agurto
***************************************************************************/
FUNCTION PER_FN_OBTIE_SUMA_CONSO_COBR(
    psCodPais                    VARCHAR2,
    psCodBanco                   VARCHAR2,
    psCodCuentaCorriente         VARCHAR2,
    psFecha                      VARCHAR2,
    psCodSociedad                VARCHAR2,
    psIndicador                  VARCHAR2)
RETURN NUMBER
IS
IMPORTESUMA NUMBER;
CONTADORSUMA NUMBER;
BEGIN
  IMPORTESUMA :=0;
  CONTADORSUMA :=0;
  SELECT SUM(TEMP1.IMPORTESUMA)+SUM(TEMP2.IMPORTESUMA), NVL(TEMP1.CONTADOR,0)+ NVL(TEMP2.CONTADOR,0)
  INTO IMPORTESUMA, CONTADORSUMA
  FROM
  (WITH TEMPORAL AS
  (SELECT CCC_CABEC_CARGA_ABONO_DIREC.OID_CABE_CARG,
          CCC_CABEC_CARGA_ABONO_DIREC.CCBA_OID_CUEN_CORR_BANC
     FROM CCC_CABEC_CARGA_ABONO_DIREC,
        SEG_PAIS,
        SEG_SOCIE,
       CCC_PROCE,
       CCC_SUBPR
   WHERE ((SEG_PAIS.OID_PAIS = CCC_CABEC_CARGA_ABONO_DIREC.PAIS_OID_PAIS)
        AND (SEG_SOCIE.OID_SOCI = CCC_CABEC_CARGA_ABONO_DIREC.SOCI_OID_SOCI)
        AND (CCC_PROCE.OID_PROC = CCC_CABEC_CARGA_ABONO_DIREC.CCPR_OID_PROC)
        AND (CCC_PROCE.OID_PROC = CCC_SUBPR.CCPR_OID_PROC)
     --     AND (CCC_PROCE.COD_PROC ='TESP01')
        AND (CCC_SUBPR.COD_SUBP = 1)
        AND (SEG_PAIS.COD_PAIS = psCodPais)
        AND (SEG_SOCIE.COD_SOCI = psCodSociedad)
        AND (CCC_CABEC_CARGA_ABONO_DIREC.FEC_DOCU = TO_DATE(psFecha,'dd/mm/yyyy'))
        )
  )
  SELECT SUM(CCC_DETAL_CARGO_ABONO_DIREC.IMP) AS IMPORTESUMA,
        COUNT(*) AS CONTADOR
   FROM  CCC_DETAL_CARGO_ABONO_DIREC,
         TEMPORAL,
        CCC_TIPO_ABONO_SUBPR,
        CCC_SUBPR,
        CCC_PROCE,
        CCC_CUENT_CORRI_BANCA,
         CCC_SUCUR,
        CCC_BANCO
   WHERE ((TEMPORAL.OID_CABE_CARG = CCC_DETAL_CARGO_ABONO_DIREC.CCAD_OID_CABE_CARG)
         AND  (CCC_TIPO_ABONO_SUBPR.OID_TIPO_ABON_SUBP = CCC_DETAL_CARGO_ABONO_DIREC.TASP_OID_TIPO_ABON_SUBP)
         AND  (CCC_SUBPR.OID_SUBP = CCC_TIPO_ABONO_SUBPR.SUBP_OID_SUBP)
         AND  (CCC_PROCE.OID_PROC = CCC_SUBPR.CCPR_OID_PROC)
         AND  (CCC_CUENT_CORRI_BANCA.OID_CUEN_CORR_BANC = TEMPORAL.CCBA_OID_CUEN_CORR_BANC)
         AND  (CCC_SUCUR.OID_SUCU = CCC_CUENT_CORRI_BANCA.SUCU_OID_SUCU)
         AND  (CCC_BANCO.OID_BANC = CCC_SUCUR.CBAN_OID_BANC)
         AND  (CCC_BANCO.COD_BANC = psCodBanco)
         AND  (CCC_CUENT_CORRI_BANCA.COD_CC =Decode(psCodCuentaCorriente, null,CCC_CUENT_CORRI_BANCA.COD_CC,'',CCC_CUENT_CORRI_BANCA.COD_CC,'Todos',CCC_CUENT_CORRI_BANCA.COD_CC,psCodCuentaCorriente) )
      )
  ) TEMP1,
  (SELECT SUM(CCC_MOVIM_BANCA.IMP_PAGO) AS IMPORTESUMA,
       COUNT(*) AS CONTADOR
  FROM CCC_MOVIM_BANCA,
       SEG_PAIS,
       SEG_SOCIE,
       CCC_SUBPR,
       CCC_PROCE,
       CCC_CUENT_CORRI_BANCA,
       CCC_SUCUR,
       CCC_BANCO
  WHERE ((SEG_PAIS.OID_PAIS = CCC_MOVIM_BANCA.PAIS_OID_PAIS)
      AND (SEG_SOCIE.OID_SOCI = CCC_MOVIM_BANCA.SOCI_OID_SOCI)
      AND (CCC_SUBPR.OID_SUBP = CCC_MOVIM_BANCA.SUBP_OID_MARC_CREA)
      AND (CCC_PROCE.OID_PROC = CCC_SUBPR.CCPR_OID_PROC)
      AND (CCC_PROCE.COD_PROC IN ('TES001', 'TES002'))
      AND (CCC_MOVIM_BANCA.COD_IDEN_PROC IN ('P'))
      AND   (CCC_CUENT_CORRI_BANCA.OID_CUEN_CORR_BANC = CCC_MOVIM_BANCA.CCBA_OID_CC_BANC)
      AND  (CCC_SUCUR.OID_SUCU = CCC_CUENT_CORRI_BANCA.SUCU_OID_SUCU)
      AND  (CCC_BANCO.OID_BANC = CCC_SUCUR.CBAN_OID_BANC)
      AND (SEG_PAIS.COD_PAIS = psCodPais)
      AND (SEG_SOCIE.COD_SOCI = psCodSociedad)
      AND (CCC_MOVIM_BANCA.FEC_PAGO = TO_DATE(psFecha,'dd/mm/yyyy'))
      AND (CCC_BANCO.COD_BANC = psCodBanco)
        AND (CCC_CUENT_CORRI_BANCA.COD_CC =Decode(psCodCuentaCorriente, null,CCC_CUENT_CORRI_BANCA.COD_CC,'',CCC_CUENT_CORRI_BANCA.COD_CC,'Todos',CCC_CUENT_CORRI_BANCA.COD_CC,psCodCuentaCorriente) )
  )) TEMP2
  GROUP BY NVL(TEMP1.CONTADOR,0)+ NVL(TEMP2.CONTADOR,0);
    IF (psIndicador='IMPORTE') THEN
       RETURN  IMPORTESUMA;
    ELSE
       RETURN  CONTADORSUMA;
    END IF;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_SUMA_CONSO_COBR: '||ls_sqlerrm );
     RETURN 0;
 END PER_FN_OBTIE_SUMA_CONSO_COBR;


/***************************************************************************
Descripcion       : Devuelve Nombres y apellidos del cliente para ser mostrado
                    en el Reporte de Libro de Percepciones
Fecha Creacion    : 11/09/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_NOMBR_CONSU_LIBRO_PERCE(
    psCodPais    VARCHAR2,
    psCodCliente VARCHAR2,
    psApePaterno VARCHAR2,
    psApeMaterno VARCHAR2,
    psNombre     VARCHAR2)
RETURN VARCHAR2
IS
    lsNombre    VARCHAR2(500);
BEGIN
    /* En caso el codigo de cliente sea Nulo */
    IF psCodCliente IS NULL THEN
       lsNombre := NVL(TRIM(psNombre),' ')||' '||
                   NVL(TRIM(psApePaterno),' ')||' '||
                   NVL(TRIM(psApeMaterno),' ');
       RETURN lsNombre;
    END IF;

    /* Obteniendo datos del cliente */
    SELECT NVL(TRIM(A.VAL_NOM1),' ')||' '||
           NVL(TRIM(A.VAL_NOM2),' ')||' '||
           NVL(TRIM(A.VAL_APE1),' ')||' '||
           NVL(TRIM(A.VAL_APE2),' ')
    INTO lsNombre
    FROM MAE_CLIEN A,
         SEG_PAIS B
    WHERE B.COD_PAIS = psCodPais
      AND A.PAIS_OID_PAIS = B.OID_PAIS
      AND A.COD_CLIE = psCodCliente ;
    RETURN lsNombre;
EXCEPTION
WHEN no_data_found THEN
      lsNombre := NVL(TRIM(psNombre),' ')||' '||
                  NVL(TRIM(psApePaterno),' ')||' '||
                  NVL(TRIM(psApeMaterno),' ');
      RETURN lsNombre;
WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_NOMBR_CONSU_LIBRO_PERCE: '||ls_sqlerrm );
END PER_FN_NOMBR_CONSU_LIBRO_PERCE;


/***************************************************************************
Descripcion       : Genera data para Reporte Control de cliente
Fecha Creacion    : 18/09/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_CONTR_CLIEN(
    psCodPais       VARCHAR2,
    psFechaDesde    VARCHAR2,
    psFechaHasta    VARCHAR2,
    psTipoDocum     VARCHAR2,
    psNroDocum      VARCHAR2
    )
IS
  TYPE t_COR_PECO   IS TABLE OF PER_PERCE_CONSO.COR_PECO%TYPE ;
  TYPE t_FEC_COPE   IS TABLE OF PER_PERCE_CONSO.FEC_COPE%TYPE ;
  TYPE t_TIP_DOID   IS TABLE OF PER_PERCE_CONSO.TIP_DOID%TYPE ;
  TYPE t_NUM_DOID   IS TABLE OF PER_PERCE_CONSO.NUM_DOID%TYPE ;
  TYPE t_TIP_DOLE   IS TABLE OF PER_PERCE_CONSO.TIP_DOLE%TYPE ;
  TYPE t_SER_DOLE   IS TABLE OF PER_PERCE_CONSO.SER_DOLE%TYPE ;
  TYPE t_NUM_DOLE   IS TABLE OF PER_PERCE_CONSO.NUM_DOLE%TYPE ;
  TYPE t_SER_COPE   IS TABLE OF PER_PERCE_CONSO.SER_COPE%TYPE ;
  TYPE t_NUM_COPE   IS TABLE OF PER_PERCE_CONSO.NUM_COPE%TYPE ;
  TYPE t_COD_CLIE   IS TABLE OF PER_PERCE_CONSO.COD_CLIE%TYPE ;
  TYPE t_APE_PATE   IS TABLE OF PER_PERCE_CONSO.APE_PATE%TYPE ;
  TYPE t_APE_MATE   IS TABLE OF PER_PERCE_CONSO.APE_MATE%TYPE ;
  TYPE t_VAL_NOMB   IS TABLE OF PER_PERCE_CONSO.VAL_NOMB%TYPE ;
  TYPE t_MON_TODL   IS TABLE OF PER_PERCE_CONSO.MON_TODL%TYPE ;

  regcorIdRo  t_COR_PECO ;
  regcorPeco  t_COR_PECO ;
  regfecCope  t_FEC_COPE ;
  regtipDoid  t_TIP_DOID ;
  regnumDoid  t_NUM_DOID ;
  regtipDole  t_TIP_DOLE ;
  regserDole  t_SER_DOLE ;
  regnumDole  t_NUM_DOLE ;
  regserCope  t_SER_COPE ;
  regnumCope  t_NUM_COPE ;
  regcodClie  t_COD_CLIE ;
  regapePate  t_APE_PATE ;
  regapeMate  t_APE_MATE ;
  regvalNomb  t_VAL_NOMB ;
  regmonTodl  t_MON_TODL ;
  regmonPerc  t_MON_TODL ;
  regmonDebe  t_MON_TODL ;
  regmonHabe  t_MON_TODL ;
  regmonSald  t_MON_TODL ;
  lnSaldo     PER_GTT_REPOR_CONTR_CLIEN.Mon_Sald%TYPE;
  lnPadre     PER_GTT_REPOR_CONTR_CLIEN.Cor_Padr%TYPE;

  CURSOR cCabecera IS
  SELECT
       MIN(PER.COR_PECO),
       MIN(PER.FEC_COPE),
       PER.TIP_DOID,
       PER.NUM_DOID,
       PER.TIP_DOLE,
       PER.SER_DOLE,
       PER.NUM_DOLE,
       PER.SER_COPE,
       PER.NUM_COPE,
       PER.COD_CLIE,
       PER.APE_PATE,
       PER.APE_MATE,
       PER.VAL_NOMB,
       PER.MON_TODL

  FROM PER_PERCE_CONSO PER
  WHERE PER.PAIS_COD_PAIS   = psCodPais AND
        PER.FEC_COPE >= TO_DATE (psFechaDesde,'dd/MM/yyyy') AND
        PER.FEC_COPE < TO_DATE (psFechaHasta,'dd/MM/yyyy') + 1
  GROUP BY
        PER.TIP_DOID,
        PER.NUM_DOID,
        PER.TIP_DOLE,
        PER.SER_DOLE,
        PER.NUM_DOLE,
        PER.SER_COPE,
        PER.NUM_COPE,
        PER.COD_CLIE,
        PER.APE_PATE,
        PER.APE_MATE,
        PER.VAL_NOMB,
        PER.MON_TODL ;

  CURSOR cCabeceraDocum IS
  SELECT
       MIN(PER.COR_PECO),
       MIN(PER.FEC_COPE),
       PER.TIP_DOID,
       PER.NUM_DOID,
       PER.TIP_DOLE,
       PER.SER_DOLE,
       PER.NUM_DOLE,
       PER.SER_COPE,
       PER.NUM_COPE,
       PER.COD_CLIE,
       PER.APE_PATE,
       PER.APE_MATE,
       PER.VAL_NOMB,
       PER.MON_TODL

  FROM PER_PERCE_CONSO PER
  WHERE PER.PAIS_COD_PAIS   = psCodPais AND
        PER.FEC_COPE >= TO_DATE (psFechaDesde,'dd/MM/yyyy') AND
        PER.FEC_COPE < TO_DATE (psFechaHasta,'dd/MM/yyyy') + 1 AND
        PER.TIP_DOID = psTipoDocum AND
        TRIM(PER.NUM_DOID) = psNroDocum
  GROUP BY
        PER.TIP_DOID,
        PER.NUM_DOID,
        PER.TIP_DOLE,
        PER.SER_DOLE,
        PER.NUM_DOLE,
        PER.SER_COPE,
        PER.NUM_COPE,
        PER.COD_CLIE,
        PER.APE_PATE,
        PER.APE_MATE,
        PER.VAL_NOMB,
        PER.MON_TODL ;


  CURSOR cDetalle IS
  SELECT
       PER.COR_PECO,
       PER.FEC_COPE,
       PER.TIP_DOID,
       PER.NUM_DOID,
       PER.TIP_DOLE,
       PER.SER_DOLE,
       PER.NUM_DOLE,
       PER.SER_COPE,
       PER.NUM_COPE,
       PER.COD_CLIE,
       PER.APE_PATE,
       PER.APE_MATE,
       PER.VAL_NOMB,
       PER.MON_PERC,
       NVL(PER.MON_PAGO,0) - NVL(PER.MON_PERC,0) AS MON_HABE

  FROM PER_PERCE_CONSO PER
  WHERE PER.PAIS_COD_PAIS   = psCodPais AND
        PER.FEC_COPE >= TO_DATE (psFechaDesde,'dd/MM/yyyy') AND
        PER.FEC_COPE < TO_DATE (psFechaHasta,'dd/MM/yyyy') + 1 AND
        PER.MON_PERC > 0;

  CURSOR cDetalleDocum IS
  SELECT
       PER.COR_PECO,
       PER.FEC_COPE,
       PER.TIP_DOID,
       PER.NUM_DOID,
       PER.TIP_DOLE,
       PER.SER_DOLE,
       PER.NUM_DOLE,
       PER.SER_COPE,
       PER.NUM_COPE,
       PER.COD_CLIE,
       PER.APE_PATE,
       PER.APE_MATE,
       PER.VAL_NOMB,
       PER.MON_PERC,
       NVL(PER.MON_PAGO,0) - NVL(PER.MON_PERC,0) AS MON_HABE

  FROM PER_PERCE_CONSO PER
  WHERE PER.PAIS_COD_PAIS   = psCodPais AND
        PER.FEC_COPE >= TO_DATE (psFechaDesde,'dd/MM/yyyy') AND
        PER.FEC_COPE < TO_DATE (psFechaHasta,'dd/MM/yyyy') + 1 AND
        PER.MON_PERC > 0 AND
        PER.TIP_DOID = psTipoDocum AND
        TRIM(PER.NUM_DOID) = psNroDocum;


  CURSOR cSaldo IS
  SELECT
     A.COR_REGI,
     A.COR_IDRO,
     A.MON_SALD,
     A.MON_HABE
  FROM
    PER_GTT_REPOR_CONTR_CLIEN A
  ORDER BY
    FEC_COPE, TIP_DOID, NUM_DOID, TIP_DOLE, SER_DOLE, NUM_DOLE, SER_COPE, NUM_COPE, COR_REGI ;

  CURSOR cCliente IS
  SELECT
     A.COR_IDRO,
     A.COD_CLIE,
     A.APE_PATE,
     A.APE_MATE,
     A.VAL_NOMB
  FROM
    PER_GTT_REPOR_CONTR_CLIEN A
  WHERE
    A.COR_REGI = 0;

  CURSOR cTipoDocumCliente IS
  SELECT DISTINCT
     A.TIP_DOID
  FROM
    PER_GTT_REPOR_CONTR_CLIEN A;

  CURSOR cTipoDocumLegal IS
  SELECT DISTINCT
     A.TIP_DOLE
  FROM
    PER_GTT_REPOR_CONTR_CLIEN A;


BEGIN
 DELETE FROM PER_GTT_REPOR_CONTR_CLIEN;

 /* Recorriendo Lista de Cabeceras */
 IF psTipoDocum IS NULL THEN
     OPEN cCabecera;
     LOOP
          FETCH cCabecera BULK COLLECT INTO
                regcorPeco,
                regfecCope, regtipDoid,
                regnumDoid, regtipDole,
                regserDole, regnumDole,
                regserCope, regnumCope,
                regcodClie, regapePate,
                regapeMate, regvalNomb,
                regmonTodl
                LIMIT W_FILAS;
          IF regfecCope.COUNT > 0 THEN
            FORALL i IN 1..regfecCope.count
              INSERT INTO PER_GTT_REPOR_CONTR_CLIEN(
                COR_REGI, COR_IDRO, COR_PADR,
                FEC_COPE,
                TIP_DOID, NUM_DOID,
                TIP_DOLE, SER_DOLE,
                NUM_DOLE, SER_COPE,
                NUM_COPE, COD_CLIE,
                APE_PATE, APE_MATE,
                VAL_NOMB, NOM_CONS,
                MON_TODL, MON_PERC,
                MON_DEBE, MON_HABE,
                MON_SALD)
             VALUES (
                0, regcorPeco(i), regcorPeco(i),
                regfecCope(i),
                regtipDoid(i), regnumDoid(i),
                regtipDole(i), regserDole(i),
                regnumDole(i), regserCope(i),
                regnumCope(i), regcodClie(i),
                regapePate(i), regapeMate(i),
                regvalNomb(i), '',
                regmonTodl(i), 0,
                regmonTodl(i), 0,
                regmonTodl(i)
              );
          END IF;
          EXIT WHEN cCabecera%NOTFOUND;
     END LOOP;
     CLOSE cCabecera;
 ELSE
     OPEN cCabeceraDocum;
     LOOP
          FETCH cCabeceraDocum BULK COLLECT INTO
                regcorPeco,
                regfecCope, regtipDoid,
                regnumDoid, regtipDole,
                regserDole, regnumDole,
                regserCope, regnumCope,
                regcodClie, regapePate,
                regapeMate, regvalNomb,
                regmonTodl
                LIMIT W_FILAS;
          IF regfecCope.COUNT > 0 THEN
            FORALL i IN 1..regfecCope.count
              INSERT INTO PER_GTT_REPOR_CONTR_CLIEN(
                COR_REGI, COR_IDRO, COR_PADR,
                FEC_COPE,
                TIP_DOID, NUM_DOID,
                TIP_DOLE, SER_DOLE,
                NUM_DOLE, SER_COPE,
                NUM_COPE, COD_CLIE,
                APE_PATE, APE_MATE,
                VAL_NOMB, NOM_CONS,
                MON_TODL, MON_PERC,
                MON_DEBE, MON_HABE,
                MON_SALD)
             VALUES (
                0, regcorPeco(i), regcorPeco(i),
                regfecCope(i),
                regtipDoid(i), regnumDoid(i),
                regtipDole(i), regserDole(i),
                regnumDole(i), regserCope(i),
                regnumCope(i), regcodClie(i),
                regapePate(i), regapeMate(i),
                regvalNomb(i), '',
                regmonTodl(i), 0,
                regmonTodl(i), 0,
                regmonTodl(i)
              );
          END IF;
          EXIT WHEN cCabeceraDocum%NOTFOUND;
     END LOOP;
     CLOSE cCabeceraDocum;
 END IF;

 /* Recorriendo Lista de Detalle */
 IF psTipoDocum IS NULL THEN
     OPEN cDetalle;
     LOOP
          FETCH cDetalle BULK COLLECT INTO
                regcorPeco,
                regfecCope, regtipDoid,
                regnumDoid, regtipDole,
                regserDole, regnumDole,
                regserCope, regnumCope,
                regcodClie, regapePate,
                regapeMate, regvalNomb,
                regmonPerc, regmonHabe
                LIMIT W_FILAS;
          IF regfecCope.COUNT > 0 THEN
            FORALL i IN 1..regfecCope.count
              INSERT INTO PER_GTT_REPOR_CONTR_CLIEN(
                COR_REGI, COR_IDRO,
                FEC_COPE,
                TIP_DOID, NUM_DOID,
                TIP_DOLE, SER_DOLE,
                NUM_DOLE, SER_COPE,
                NUM_COPE, COD_CLIE,
                APE_PATE, APE_MATE,
                VAL_NOMB, NOM_CONS,
                MON_TODL, MON_PERC,
                MON_DEBE, MON_HABE,
                MON_SALD)
             VALUES (
                regcorPeco(i), regcorPeco(i),
                regfecCope(i),
                regtipDoid(i), regnumDoid(i),
                regtipDole(i), regserDole(i),
                regnumDole(i), regserCope(i),
                regnumCope(i), regcodClie(i),
                regapePate(i), regapeMate(i),
                regvalNomb(i), '',
                0, regmonPerc(i),
                0, regmonHabe(i),
                0
              );
          END IF;
          EXIT WHEN cDetalle%NOTFOUND;
     END LOOP;
     CLOSE cDetalle;
 ELSE
     OPEN cDetalleDocum;
     LOOP
          FETCH cDetalleDocum BULK COLLECT INTO
                regcorPeco,
                regfecCope, regtipDoid,
                regnumDoid, regtipDole,
                regserDole, regnumDole,
                regserCope, regnumCope,
                regcodClie, regapePate,
                regapeMate, regvalNomb,
                regmonPerc, regmonHabe
                LIMIT W_FILAS;
          IF regfecCope.COUNT > 0 THEN
            FORALL i IN 1..regfecCope.count
              INSERT INTO PER_GTT_REPOR_CONTR_CLIEN(
                COR_REGI, COR_IDRO,
                FEC_COPE,
                TIP_DOID, NUM_DOID,
                TIP_DOLE, SER_DOLE,
                NUM_DOLE, SER_COPE,
                NUM_COPE, COD_CLIE,
                APE_PATE, APE_MATE,
                VAL_NOMB, NOM_CONS,
                MON_TODL, MON_PERC,
                MON_DEBE, MON_HABE,
                MON_SALD)
             VALUES (
                regcorPeco(i), regcorPeco(i),
                regfecCope(i),
                regtipDoid(i), regnumDoid(i),
                regtipDole(i), regserDole(i),
                regnumDole(i), regserCope(i),
                regnumCope(i), regcodClie(i),
                regapePate(i), regapeMate(i),
                regvalNomb(i), '',
                0, regmonPerc(i),
                0, regmonHabe(i),
                0
              );
          END IF;
          EXIT WHEN cDetalleDocum%NOTFOUND;
     END LOOP;
     CLOSE cDetalleDocum;
 END IF;

 /* Actualizando Saldo y Correlativo de Registro Padre */
 lnSaldo := 0;
 lnPadre := -1;
 OPEN cSaldo;
 LOOP
    FETCH cSaldo BULK COLLECT INTO
          regcorPeco, regcorIdRo,
          regmonSald, regmonHabe
          LIMIT W_FILAS;
    IF regcorPeco.COUNT > 0 THEN
       FOR x IN regcorPeco.FIRST .. regcorPeco.LAST LOOP
           IF regcorPeco(x) = 0 THEN
              lnSaldo := regmonSald(x);
              lnPadre := regcorIdRo(x);
           ELSE
              lnSaldo := lnSaldo - regmonHabe(x);
              UPDATE PER_GTT_REPOR_CONTR_CLIEN A
              SET
                A.COR_PADR = lnPadre,
                A.MON_SALD = lnSaldo
              WHERE A.COR_REGI = regcorPeco(x)
                AND A.COR_IDRO = regcorIdRo(x);
           END IF;
       END LOOP;
    END IF;
    EXIT WHEN cSaldo%NOTFOUND;
 END LOOP;
 CLOSE cSaldo;

 /* Colocando nombres de consultoras */
 OPEN cCliente;
 LOOP
      FETCH cCliente BULK COLLECT INTO
            regcorIdRo,
            regcodClie, regapePate,
            regapeMate, regvalNomb
            LIMIT W_FILAS;
      IF regcorIdRo.COUNT > 0 THEN
        FORALL i IN 1..regcorIdRo.count
          UPDATE PER_GTT_REPOR_CONTR_CLIEN A
          SET NOM_CONS = PER_FN_NOMBR_CONSU_LIBRO_PERCE(
                              psCodPais, regcodClie(i),
                              regapePate(i), regapeMate(i),
                              regvalNomb(i))
          WHERE A.COR_PADR = regcorIdRo(i);
      END IF;
      EXIT WHEN cCliente%NOTFOUND;
 END LOOP;
 CLOSE cCliente;

 /* Recorriendo Lista de Tipos de Documentos de Identidad */
 OPEN cTipoDocumCliente;
 LOOP
      FETCH cTipoDocumCliente BULK COLLECT INTO
            regtipDoid
            LIMIT W_FILAS;
      IF regtipDoid.COUNT > 0 THEN
        FOR x IN regtipDoid.FIRST .. regtipDoid.LAST LOOP
            UPDATE PER_GTT_REPOR_CONTR_CLIEN A
            SET A.TIP_DOID = (SELECT X.COD_HOMO
                              FROM PER_TIPO_DOCUM_IDENT_LEGAL X
                              WHERE X.PAIS_COD_PAIS = psCodPais
                                AND X.COD_CLAS = 'DI'
                                AND X.COD_AHOM = A.TIP_DOID)
            WHERE A.TIP_DOID = regtipDoid(x);
        END LOOP;
      END IF;
      EXIT WHEN cTipoDocumCliente%NOTFOUND;
 END LOOP;
 CLOSE cTipoDocumCliente;

 /* Recorriendo Lista de Tipos de Documentos Legal */
 OPEN cTipoDocumLegal;
 LOOP
      FETCH cTipoDocumLegal BULK COLLECT INTO
            regtipDole
            LIMIT W_FILAS;
      IF regtipDole.COUNT > 0 THEN
        FOR x IN regtipDole.FIRST .. regtipDole.LAST LOOP
            UPDATE PER_GTT_REPOR_CONTR_CLIEN A
            SET A.TIP_DOLE = (SELECT X.COD_HOMO
                              FROM PER_TIPO_DOCUM_IDENT_LEGAL X
                              WHERE X.PAIS_COD_PAIS = psCodPais
                                AND X.COD_CLAS = 'TC'
                                AND X.COD_AHOM = A.TIP_DOLE)
            WHERE A.TIP_DOLE = regtipDole(x);
        END LOOP;
      END IF;
      EXIT WHEN cTipoDocumLegal%NOTFOUND;
 END LOOP;
 CLOSE cTipoDocumLegal;

 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_CONTR_CLIEN: '||ls_sqlerrm);
END PER_PR_GENER_CONTR_CLIEN;


/***************************************************************************
Descripcion       : Genera data para Reporte Control de cliente
Fecha Creacion    : 18/09/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_CONTR_CLIEN(
    psCodPais       VARCHAR2,
    psFechaDesde    VARCHAR2,
    psFechaHasta    VARCHAR2
    )
IS
  TYPE t_COR_PECO   IS TABLE OF PER_PERCE_CONSO.COR_PECO%TYPE ;
  TYPE t_FEC_COPE   IS TABLE OF PER_PERCE_CONSO.FEC_COPE%TYPE ;
  TYPE t_TIP_DOID   IS TABLE OF PER_PERCE_CONSO.TIP_DOID%TYPE ;
  TYPE t_NUM_DOID   IS TABLE OF PER_PERCE_CONSO.NUM_DOID%TYPE ;
  TYPE t_TIP_DOLE   IS TABLE OF PER_PERCE_CONSO.TIP_DOLE%TYPE ;
  TYPE t_SER_DOLE   IS TABLE OF PER_PERCE_CONSO.SER_DOLE%TYPE ;
  TYPE t_NUM_DOLE   IS TABLE OF PER_PERCE_CONSO.NUM_DOLE%TYPE ;
  TYPE t_SER_COPE   IS TABLE OF PER_PERCE_CONSO.SER_COPE%TYPE ;
  TYPE t_NUM_COPE   IS TABLE OF PER_PERCE_CONSO.NUM_COPE%TYPE ;
  TYPE t_COD_CLIE   IS TABLE OF PER_PERCE_CONSO.COD_CLIE%TYPE ;
  TYPE t_APE_PATE   IS TABLE OF PER_PERCE_CONSO.APE_PATE%TYPE ;
  TYPE t_APE_MATE   IS TABLE OF PER_PERCE_CONSO.APE_MATE%TYPE ;
  TYPE t_VAL_NOMB   IS TABLE OF PER_PERCE_CONSO.VAL_NOMB%TYPE ;
  TYPE t_MON_TODL   IS TABLE OF PER_PERCE_CONSO.MON_TODL%TYPE ;

  regcorIdRo  t_COR_PECO ;
  regcorPeco  t_COR_PECO ;
  regfecCope  t_FEC_COPE ;
  regtipDoid  t_TIP_DOID ;
  regnumDoid  t_NUM_DOID ;
  regtipDole  t_TIP_DOLE ;
  regserDole  t_SER_DOLE ;
  regnumDole  t_NUM_DOLE ;
  regserCope  t_SER_COPE ;
  regnumCope  t_NUM_COPE ;
  regcodClie  t_COD_CLIE ;
  regapePate  t_APE_PATE ;
  regapeMate  t_APE_MATE ;
  regvalNomb  t_VAL_NOMB ;
  regmonTodl  t_MON_TODL ;
  regmonPerc  t_MON_TODL ;
  regmonDebe  t_MON_TODL ;
  regmonHabe  t_MON_TODL ;
  regmonSald  t_MON_TODL ;
  lnSaldo     PER_GTT_REPOR_CONTR_CLIEN.Mon_Sald%TYPE;
  lnPadre     PER_GTT_REPOR_CONTR_CLIEN.Cor_Padr%TYPE;

  CURSOR cCabecera IS
  SELECT
       MIN(PER.COR_PECO),
       MIN(PER.FEC_COPE),
       PER.TIP_DOID,
       PER.NUM_DOID,
       PER.TIP_DOLE,
       PER.SER_DOLE,
       PER.NUM_DOLE,
       PER.SER_COPE,
       PER.NUM_COPE,
       PER.COD_CLIE,
       PER.APE_PATE,
       PER.APE_MATE,
       PER.VAL_NOMB,
       PER.MON_TODL

  FROM PER_PERCE_CONSO PER
  WHERE PER.PAIS_COD_PAIS   = psCodPais AND
        PER.FEC_COPE >= TO_DATE (psFechaDesde,'dd/MM/yyyy') AND
        PER.FEC_COPE < TO_DATE (psFechaHasta,'dd/MM/yyyy') + 1
  GROUP BY
        PER.TIP_DOID,
        PER.NUM_DOID,
        PER.TIP_DOLE,
        PER.SER_DOLE,
        PER.NUM_DOLE,
        PER.SER_COPE,
        PER.NUM_COPE,
        PER.COD_CLIE,
        PER.APE_PATE,
        PER.APE_MATE,
        PER.VAL_NOMB,
        PER.MON_TODL ;



  CURSOR cDetalle IS
  SELECT
       PER.COR_PECO,
       PER.FEC_COPE,
       PER.TIP_DOID,
       PER.NUM_DOID,
       PER.TIP_DOLE,
       PER.SER_DOLE,
       PER.NUM_DOLE,
       PER.SER_COPE,
       PER.NUM_COPE,
       PER.COD_CLIE,
       PER.APE_PATE,
       PER.APE_MATE,
       PER.VAL_NOMB,
       PER.MON_PERC,
       NVL(PER.MON_PAGO,0) - NVL(PER.MON_PERC,0) AS MON_HABE

  FROM PER_PERCE_CONSO PER
  WHERE PER.PAIS_COD_PAIS   = psCodPais AND
        PER.FEC_COPE >= TO_DATE (psFechaDesde,'dd/MM/yyyy') AND
        PER.FEC_COPE < TO_DATE (psFechaHasta,'dd/MM/yyyy') + 1 AND
        PER.MON_PERC > 0;


  CURSOR cSaldo IS
  SELECT
     A.COR_REGI,
     A.COR_IDRO,
     A.MON_SALD,
     A.MON_HABE
  FROM
    PER_GTT_REPOR_CONTR_CLIEN A
  ORDER BY
    FEC_COPE, TIP_DOID, NUM_DOID, TIP_DOLE, SER_DOLE, NUM_DOLE, SER_COPE, NUM_COPE, COR_REGI ;

  CURSOR cCliente IS
  SELECT
     A.COR_IDRO,
     A.COD_CLIE,
     A.APE_PATE,
     A.APE_MATE,
     A.VAL_NOMB
  FROM
    PER_GTT_REPOR_CONTR_CLIEN A
  WHERE
    A.COR_REGI = 0;

  CURSOR cTipoDocumCliente IS
  SELECT DISTINCT
     A.TIP_DOID
  FROM
    PER_GTT_REPOR_CONTR_CLIEN A;

  CURSOR cTipoDocumLegal IS
  SELECT DISTINCT
     A.TIP_DOLE
  FROM
    PER_GTT_REPOR_CONTR_CLIEN A;


BEGIN
 DELETE FROM PER_GTT_REPOR_CONTR_CLIEN;

 /* Recorriendo Lista de Cabeceras */
     OPEN cCabecera;
     LOOP
          FETCH cCabecera BULK COLLECT INTO
                regcorPeco,
                regfecCope, regtipDoid,
                regnumDoid, regtipDole,
                regserDole, regnumDole,
                regserCope, regnumCope,
                regcodClie, regapePate,
                regapeMate, regvalNomb,
                regmonTodl
                LIMIT W_FILAS;
          IF regfecCope.COUNT > 0 THEN
            FORALL i IN 1..regfecCope.count
              INSERT INTO PER_GTT_REPOR_CONTR_CLIEN(
                COR_REGI, COR_IDRO, COR_PADR,
                FEC_COPE,
                TIP_DOID, NUM_DOID,
                TIP_DOLE, SER_DOLE,
                NUM_DOLE, SER_COPE,
                NUM_COPE, COD_CLIE,
                APE_PATE, APE_MATE,
                VAL_NOMB, NOM_CONS,
                MON_TODL, MON_PERC,
                MON_DEBE, MON_HABE,
                MON_SALD)
             VALUES (
                0, regcorPeco(i), regcorPeco(i),
                regfecCope(i),
                regtipDoid(i), regnumDoid(i),
                regtipDole(i), regserDole(i),
                regnumDole(i), regserCope(i),
                regnumCope(i), regcodClie(i),
                regapePate(i), regapeMate(i),
                regvalNomb(i), '',
                regmonTodl(i), 0,
                regmonTodl(i), 0,
                regmonTodl(i)
              );
          END IF;
          EXIT WHEN cCabecera%NOTFOUND;
     END LOOP;
     CLOSE cCabecera;

 /* Recorriendo Lista de Detalle */
 OPEN cDetalle;
 LOOP
      FETCH cDetalle BULK COLLECT INTO
            regcorPeco,
            regfecCope, regtipDoid,
            regnumDoid, regtipDole,
            regserDole, regnumDole,
            regserCope, regnumCope,
            regcodClie, regapePate,
            regapeMate, regvalNomb,
            regmonPerc, regmonHabe
            LIMIT W_FILAS;
      IF regfecCope.COUNT > 0 THEN
        FORALL i IN 1..regfecCope.count
          INSERT INTO PER_GTT_REPOR_CONTR_CLIEN(
            COR_REGI, COR_IDRO,
            FEC_COPE,
            TIP_DOID, NUM_DOID,
            TIP_DOLE, SER_DOLE,
            NUM_DOLE, SER_COPE,
            NUM_COPE, COD_CLIE,
            APE_PATE, APE_MATE,
            VAL_NOMB, NOM_CONS,
            MON_TODL, MON_PERC,
            MON_DEBE, MON_HABE,
            MON_SALD)
         VALUES (
            regcorPeco(i), regcorPeco(i),
            regfecCope(i),
            regtipDoid(i), regnumDoid(i),
            regtipDole(i), regserDole(i),
            regnumDole(i), regserCope(i),
            regnumCope(i), regcodClie(i),
            regapePate(i), regapeMate(i),
            regvalNomb(i), '',
            0, regmonPerc(i),
            0, regmonHabe(i),
            0
          );
      END IF;
      EXIT WHEN cDetalle%NOTFOUND;
 END LOOP;
 CLOSE cDetalle;

 /* Actualizando Saldo y Correlativo de Registro Padre */
 lnSaldo := 0;
 lnPadre := -1;
 OPEN cSaldo;
 LOOP
    FETCH cSaldo BULK COLLECT INTO
          regcorPeco, regcorIdRo,
          regmonSald, regmonHabe
          LIMIT W_FILAS;
    IF regcorPeco.COUNT > 0 THEN
       FOR x IN regcorPeco.FIRST .. regcorPeco.LAST LOOP
           IF regcorPeco(x) = 0 THEN
              lnSaldo := regmonSald(x);
              lnPadre := regcorIdRo(x);
           ELSE
              lnSaldo := lnSaldo - regmonHabe(x);
              UPDATE PER_GTT_REPOR_CONTR_CLIEN A
              SET
                A.COR_PADR = lnPadre,
                A.MON_SALD = lnSaldo
              WHERE A.COR_REGI = regcorPeco(x)
                AND A.COR_IDRO = regcorIdRo(x);
           END IF;
       END LOOP;
    END IF;
    EXIT WHEN cSaldo%NOTFOUND;
 END LOOP;
 CLOSE cSaldo;

 /* Colocando nombres de consultoras */
 OPEN cCliente;
 LOOP
      FETCH cCliente BULK COLLECT INTO
            regcorIdRo,
            regcodClie, regapePate,
            regapeMate, regvalNomb
            LIMIT W_FILAS;
      IF regcorIdRo.COUNT > 0 THEN
        FORALL i IN 1..regcorIdRo.count
          UPDATE PER_GTT_REPOR_CONTR_CLIEN A
          SET NOM_CONS = PER_FN_NOMBR_CONSU_LIBRO_PERCE(
                              psCodPais, regcodClie(i),
                              regapePate(i), regapeMate(i),
                              regvalNomb(i))
          WHERE A.COR_PADR = regcorIdRo(i);
      END IF;
      EXIT WHEN cCliente%NOTFOUND;
 END LOOP;
 CLOSE cCliente;

 /* Recorriendo Lista de Tipos de Documentos de Identidad */
 OPEN cTipoDocumCliente;
 LOOP
      FETCH cTipoDocumCliente BULK COLLECT INTO
            regtipDoid
            LIMIT W_FILAS;
      IF regtipDoid.COUNT > 0 THEN
        FOR x IN regtipDoid.FIRST .. regtipDoid.LAST LOOP
            UPDATE PER_GTT_REPOR_CONTR_CLIEN A
            SET A.TIP_DOID = (SELECT X.COD_HOMO
                              FROM PER_TIPO_DOCUM_IDENT_LEGAL X
                              WHERE X.PAIS_COD_PAIS = psCodPais
                                AND X.COD_CLAS = 'DI'
                                AND X.COD_AHOM = A.TIP_DOID)
            WHERE A.TIP_DOID = regtipDoid(x);
        END LOOP;
      END IF;
      EXIT WHEN cTipoDocumCliente%NOTFOUND;
 END LOOP;
 CLOSE cTipoDocumCliente;

 /* Recorriendo Lista de Tipos de Documentos Legal */
 OPEN cTipoDocumLegal;
 LOOP
      FETCH cTipoDocumLegal BULK COLLECT INTO
            regtipDole
            LIMIT W_FILAS;
      IF regtipDole.COUNT > 0 THEN
        FOR x IN regtipDole.FIRST .. regtipDole.LAST LOOP
            UPDATE PER_GTT_REPOR_CONTR_CLIEN A
            SET A.TIP_DOLE = (SELECT X.COD_HOMO
                              FROM PER_TIPO_DOCUM_IDENT_LEGAL X
                              WHERE X.PAIS_COD_PAIS = psCodPais
                                AND X.COD_CLAS = 'TC'
                                AND X.COD_AHOM = A.TIP_DOLE)
            WHERE A.TIP_DOLE = regtipDole(x);
        END LOOP;
      END IF;
      EXIT WHEN cTipoDocumLegal%NOTFOUND;
 END LOOP;
 CLOSE cTipoDocumLegal;

 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_CONTR_CLIEN: '||ls_sqlerrm);
END PER_PR_GENER_CONTR_CLIEN;


/***************************************************************************
Descripcion       : Genera data para Reporte Resumen Cuenta Corriente
Fecha Creacion    : 18/09/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE PER_PR_GENER_RESUM_CUENT_CORRI(
    psCodPais         VARCHAR2,
    psCodSociedad     VARCHAR2,
    psFechaDesde      VARCHAR2,
    psFechaHasta      VARCHAR2,
    lsSecuencial     OUT NUMBER
)IS
  lsIdPais         NUMBER;
  lsIdSociedad     NUMBER;
BEGIN

  lsIdPais         := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
  lsIdSociedad     := gen_pkg_gener.GEN_FN_DEVUELVE_ID_SOCIE(psCodSociedad);
  SELECT PER_SEQ_RESUM_CUENT_CORRI.NEXTVAL
  INTO   lsSecuencial
  FROM dual ;


  EXECUTE IMMEDIATE '
    CREATE TABLE PER_REPOR_RESUM_CUENT_'||lsSecuencial||'  AS

     SELECT
           per_repor_param_cuent_corri.cod_parm,
           '''||psCodPais||''' as cod_pais,
           '''||psCodSociedad||''' as cod_soci,
              ccc_proce.cod_proc,
              ccc_subpr.cod_subp,
            ccc_movim_cuent_corri.soca_oid_soli_cabe,
             ccc_movim_cuent_corri.fec_docu,
            ccc_movim_cuent_corri.val_nume_lote_cont,
            sum(ccc_movim_cuent_corri.imp_movi) val_impo
       FROM
              ccc_detal_cargo_abono_direc,
            ccc_movim_cuent_corri,
              ccc_tipo_abono_subpr,
              ccc_subpr,
            ccc_proce,
            per_repor_param_cuent_corri
        WHERE ccc_detal_cargo_abono_direc.mvcc_oid_movi_Cc=ccc_movim_cuent_corri.oid_movi_cc
          AND (ccc_detal_cargo_abono_direc.tasp_oid_tipo_abon_subp = ccc_tipo_abono_subpr.oid_tipo_abon_subp)
          AND (ccc_tipo_abono_subpr.subp_oid_subp = ccc_subpr.oid_subp)
          AND (ccc_subpr.ccpr_oid_proc = ccc_proce.oid_proc)
        AND (per_repor_param_cuent_corri.cod_pais='''||psCodPais||''')
        AND (per_repor_param_cuent_corri.cod_soci='''||psCodSociedad||''')
        AND (per_repor_param_cuent_corri.cod_proc=ccc_proce.cod_proc)
        AND (per_repor_param_cuent_corri.cod_subp=ccc_subpr.cod_subp)
        AND (ccc_movim_cuent_corri.fec_docu >= TO_DATE ('''||psFechaDesde||''', ''DD/MM/YYYY''))
        AND (ccc_movim_cuent_corri.fec_docu <= TO_DATE ('''||psFechaHasta||''', ''DD/MM/YYYY''))
        GROUP BY
            per_repor_param_cuent_corri.cod_parm,
            ccc_proce.cod_proc,
            ccc_subpr.cod_subp,
            ccc_movim_cuent_corri.soca_oid_soli_cabe,
            ccc_movim_cuent_corri.fec_docu,
            ccc_movim_cuent_corri.val_nume_lote_cont';

   EXECUTE IMMEDIATE 'create index GTRE_CUCO_IX on PER_REPOR_RESUM_CUENT_'||lsSecuencial||' (VAL_IMPO, COD_PROC, COD_SUBP)';

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_RESUM_CUENT_CORRI: '||ls_sqlerrm);
END PER_PR_GENER_RESUM_CUENT_CORRI;
 /***************************************************************************
Descripcion       : Genera data para Reporte Resumen Cuenta Corriente, la data
                    correspondiente a los abonos de cobranzas
Fecha Creacion    : 18/09/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE PER_PR_GENER_RESUM_ABONO_COBRA(
    psCodPais         VARCHAR2,
    psCodSociedad     VARCHAR2,
    psFechaDesde      VARCHAR2,
    psFechaHasta      VARCHAR2,
    lsSecuencial     OUT NUMBER
)
IS
  lsIdPais         NUMBER;
  lsIdSociedad     NUMBER;
BEGIN
  lsIdPais         := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
  lsIdSociedad     := gen_pkg_gener.GEN_FN_DEVUELVE_ID_SOCIE(psCodSociedad);
  SELECT PER_SEQ_RESUM_ABONO_COBRAN.NEXTVAL
  INTO   lsSecuencial
  FROM dual ;

  EXECUTE IMMEDIATE 'CREATE TABLE PER_REPOR_RESUM_ABONO_'||lsSecuencial||'  AS
 SELECT CCC_CUENT_CORRI_BANCA.COD_CC_BANC,
        CCC_CUENT_CORRI_BANCA.DES_CC ,
        CCC_MOVIM_BANCA.FEC_PAGO ,
        CCC_DETAL_CARGO_ABONO_DIREC.FEC_PAGO_BANC ,
        SUM(CCC_DETAL_CARGO_ABONO_DIREC.IMP) AS IMP,
        SUM(CCC_MOVIM_BANCA.IMP_PAGO) AS IMP_PAGO
FROM    CCC_MOVIM_BANCA,
        CCC_CUENT_CORRI_BANCA,
        CCC_DETAL_CARGO_ABONO_DIREC,
        CCC_TIPO_ABONO_SUBPR,
        CCC_SUBPR,
        CCC_PROCE,
        PER_REPOR_PARAM_CUENT_CORRI B
  WHERE (CCC_MOVIM_BANCA.PAIS_OID_PAIS =  '||lsIdPais||')
        AND	(CCC_MOVIM_BANCA.SOCI_OID_SOCI = '||lsIdSociedad||')
        AND	(CCC_MOVIM_BANCA.CCBA_OID_CC_BANC = CCC_CUENT_CORRI_BANCA.OID_CUEN_CORR_BANC)
        AND	(CCC_DETAL_CARGO_ABONO_DIREC.CMBA_OID_MOVI_BANC = CCC_MOVIM_BANCA.OID_MOVI_BANC)
        AND	(CCC_DETAL_CARGO_ABONO_DIREC.TASP_OID_TIPO_ABON_SUBP = CCC_TIPO_ABONO_SUBPR.OID_TIPO_ABON_SUBP)
        AND	(CCC_TIPO_ABONO_SUBPR.SUBP_OID_SUBP = CCC_SUBPR.OID_SUBP)
        AND	(CCC_SUBPR.CCPR_OID_PROC = CCC_PROCE.OID_PROC)
        AND B.COD_PARM = ''05''
    AND  CCC_MOVIM_BANCA.PAIS_OID_PAIS = B.PAIS_OID_PAIS
    AND  CCC_PROCE.COD_PROC = B.COD_PROC
    AND  CCC_SUBPR.cod_subp = B.cod_subp
    AND	(CCC_MOVIM_BANCA.FEC_PROC BETWEEN TO_DATE('''||psFechaDesde||''',''DD/MM/YYYY'') AND TO_DATE('''||psFechaHasta||''',''DD/MM/YYYY''))
  GROUP BY CCC_CUENT_CORRI_BANCA.COD_CC_BANC,
        CCC_CUENT_CORRI_BANCA.DES_CC,
        CCC_MOVIM_BANCA.FEC_PAGO,
        CCC_DETAL_CARGO_ABONO_DIREC.FEC_PAGO_BANC
  ORDER BY CCC_MOVIM_BANCA.FEC_PAGO';

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_RESUM_ABONO_COBRA: '||ls_sqlerrm);
END PER_PR_GENER_RESUM_ABONO_COBRA;
/**************************************************************************
Descripcion        : Funcion que devuelve el tipo de documento de identidad
                     homologado al formato de Sunat.
Parametros         :
                   psCodPais   :  Codigo de Pais
                   psTipoDocum :  Codigo de Tipo de documento
Fecha Creacion     : 24/10/2007
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_DOCUM_HOMOL
 (psCodPais   VARCHAR2,
  psTipoDocum VARCHAR2)
RETURN VARCHAR2
IS
  lsCodHomo    PER_TIPO_DOCUM_IDENT_LEGAL.Cod_Homo%TYPE;

BEGIN
  lsCodHomo := '';
  SELECT COD_HOMO
  INTO lsCodHomo
  FROM PER_TIPO_DOCUM_IDENT_LEGAL B
  WHERE B.PAIS_COD_PAIS = psCodPais AND
        B.COD_CLAS = 'DI' AND
        B.COD_AHOM = psTipoDocum;

  RETURN lsCodHomo;
EXCEPTION
WHEN NO_DATA_FOUND THEN
  RETURN '';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_DOCUM_HOMOL: '||ls_sqlerrm);
END  PER_FN_DEVUE_DOCUM_HOMOL;


/***************************************************************************
Descripcion       : Genera data para Reporte Control de Asistencia
                    de Triunfadoras
Fecha Creacion    : 17/02/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_GENER_CONTR_ASIST_TRIUN(
    psCodPais VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2,
    psCodZona    VARCHAR2,
    psOidProceso VARCHAR2)
IS
  tablaRegistro   tablaControlAsis;
  regRegistro     tRegControlAsis; --PER_REPOR_CNTRL_ASIST_CONFE%ROWTYPE;
  lnIdPais        seg_pais.oid_pais%TYPE;
  lnIdCanal       seg_canal.oid_cana%TYPE;
  lnIdMarca       seg_marca.oid_marc%TYPE;
  lsPeriodo1      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodo2      SEG_PERIO_CORPO.COD_PERI%TYPE;
  ln_monto        NUMBER;
  ln_monto_perc   NUMBER;
  lnContador      NUMBER;
  ldFechaFact     DATE;
  W_FILAS         NUMBER:=5000;


BEGIN
 /* Insertando informacion del Cliente x Unidad Administrativa */
 IF psCodZona IS NOT NULL THEN
     INSERT INTO PER_TMP_ASIST_CONFE_TRIUN
       (OID_PROC,
        COD_REGI,
        COD_ZONA,
        COD_SECC,
        COD_TERR,
        NUM_DOCU_IDEN,
        NOM_CLIE,
        VAL_DIRE,
        VAL_BARR,
        FEC_NACI,
        VAL_TELE_CASA,
        VAL_TELE_CELU,
        VAL_ESTA,
        MON_ULTI_FACT,
        VAL_PEDI,
        VAL_ASIS,
        FEC_GENE)
      SELECT
           psOidProceso,
           r.cod_regi cod_regi,
           z.COD_ZONA cod_zona,
           s.cod_secc cod_secc,
           GEN_PKG_GENER.gen_fn_clien_datos_oid(A.OID_CLIE,'DAT_TER') cod_terr,
           b.num_docu_iden num_docu_iden,
           a.val_nom1 || ' ' || a.val_ape1  nom_clie,
           GEN_PKG_GENER.gen_fn_clien_datos_oid(A.OID_CLIE,'DAT_DIR') direccion,
           GEN_PKG_GENER.gen_fn_clien_datos_oid(A.OID_CLIE,'DAT_BAR') barrio,
           TO_CHAR(d.FEC_NACI, 'DD/MM/YYYY') FEC_NACI,
           f.VAL_TEXT_COMU telefono_casa,
           (SELECT com.val_text_comu
              FROM MAE_CLIEN_COMUN com, MAE_TIPO_COMUN tip
             WHERE com.ticm_oid_tipo_comu = tip.oid_tipo_comu
               AND com.clie_oid_clie = a.oid_clie
               AND tip.cod_tipo_comu = 'TM') telefono_celular,
           (select gen.val_i18n
              from gen_i18n_sicc_comun gen
             where gen.attr_enti = 'MAE_ESTAT_CLIEN'
               and gen.val_oid = e.oid_esta_clie) estado,
           x.val_mont_fact - ccc_pkg_gener.CCC_FN_OBTIE_ABONO_PEND(a.oid_clie) monto_ultima_factura, -- aqui
           '' pedido,
           '' asistencia,
           sysdate
      FROM MAE_CLIEN             a,
           MAE_CLIEN_IDENT       b,
           MAE_CLIEN_DATOS_ADICI d,
           MAE_ESTAT_CLIEN       e,
           MAE_CLIEN_COMUN       f,
           mae_clien_unida_admin ua,
           zon_terri             t,
           zon_terri_admin       ta,
           zon_secci             s,
           zon_zona              z,
           zon_regio             r,
           mae_clien_estat       x
     WHERE b.CLIE_OID_CLIE = a.OID_CLIE
       AND b.VAL_IDEN_DOCU_PRIN = 1
       AND d.CLIE_OID_CLIE = a.OID_CLIE
       AND e.OID_ESTA_CLIE(+) = d.ESTA_OID_ESTA_CLIE
       AND f.CLIE_OID_CLIE(+) = a.OID_CLIE
       AND f.TICM_OID_TIPO_COMU(+) = 1
       AND x.oid_clie = a.oid_clie
       and e.cod_esta_clie not in ('01', '05', '07')
       and ua.ind_acti = 1
       and ua.perd_oid_peri_fin is null
       and ua.ztad_oid_terr_admi = ta.oid_terr_admi
       and ta.terr_oid_terr = t.oid_terr
       and ta.zscc_oid_secc = s.oid_secc
       and s.zzon_oid_zona = z.oid_zona
       and z.zorg_oid_regi = r.oid_regi
       and a.oid_clie = ua.clie_oid_clie
       and z.cod_zona = psCodZona
       and r.cod_regi = psCodRegion;
  ELSE
      INSERT INTO PER_TMP_ASIST_CONFE_TRIUN
       (OID_PROC,
        COD_REGI,
        COD_ZONA,
        COD_SECC,
        COD_TERR,
        NUM_DOCU_IDEN,
        NOM_CLIE,
        VAL_DIRE,
        VAL_BARR,
        FEC_NACI,
        VAL_TELE_CASA,
        VAL_TELE_CELU,
        VAL_ESTA,
        MON_ULTI_FACT,
        VAL_PEDI,
        VAL_ASIS,
        FEC_GENE)
      SELECT
           psOidProceso,
           r.cod_regi cod_regi,
           z.COD_ZONA cod_zona,
           s.cod_secc cod_secc,
           GEN_PKG_GENER.gen_fn_clien_datos_oid(A.OID_CLIE,'DAT_TER') cod_terr,
           b.num_docu_iden num_docu_iden,
           a.val_nom1 || ' ' || a.val_ape1  nom_clie,
           GEN_PKG_GENER.gen_fn_clien_datos_oid(A.OID_CLIE,'DAT_DIR') direccion,
           GEN_PKG_GENER.gen_fn_clien_datos_oid(A.OID_CLIE,'DAT_BAR') barrio,
           TO_CHAR(d.FEC_NACI, 'DD/MM/YYYY') FEC_NACI,
           f.VAL_TEXT_COMU telefono_casa,
           (SELECT com.val_text_comu
              FROM MAE_CLIEN_COMUN com, MAE_TIPO_COMUN tip
             WHERE com.ticm_oid_tipo_comu = tip.oid_tipo_comu
               AND com.clie_oid_clie = a.oid_clie
               AND tip.cod_tipo_comu = 'TM') telefono_celular,
           (select gen.val_i18n
              from gen_i18n_sicc_comun gen
             where gen.attr_enti = 'MAE_ESTAT_CLIEN'
               and gen.val_oid = e.oid_esta_clie) estado,
           trunc(x.val_mont_fact) - ccc_pkg_gener.CCC_FN_OBTIE_ABONO_PEND(a.oid_clie) monto_ultima_factura, -- aqui
           '' pedido,
           '' asistencia,
           sysdate
      FROM MAE_CLIEN             a,
           MAE_CLIEN_IDENT       b,
           MAE_CLIEN_DATOS_ADICI d,
           MAE_ESTAT_CLIEN       e,
           MAE_CLIEN_COMUN       f,
           mae_clien_unida_admin ua,
           zon_terri             t,
           zon_terri_admin       ta,
           zon_secci             s,
           zon_zona              z,
           zon_regio             r,
           mae_clien_estat       x
     WHERE b.CLIE_OID_CLIE = a.OID_CLIE
       AND b.VAL_IDEN_DOCU_PRIN = 1
       AND d.CLIE_OID_CLIE = a.OID_CLIE
       AND e.OID_ESTA_CLIE(+) = d.ESTA_OID_ESTA_CLIE
       AND f.CLIE_OID_CLIE(+) = a.OID_CLIE
       AND f.TICM_OID_TIPO_COMU(+) = 1
       AND x.oid_clie = a.oid_clie
       and e.cod_esta_clie not in ('01', '05', '07')
       and ua.ind_acti = 1
       and ua.perd_oid_peri_fin is null
       and ua.ztad_oid_terr_admi = ta.oid_terr_admi
       and ta.terr_oid_terr = t.oid_terr
       and ta.zscc_oid_secc = s.oid_secc
       and s.zzon_oid_zona = z.oid_zona
       and z.zorg_oid_regi = r.oid_regi
       and a.oid_clie = ua.clie_oid_clie
       and r.cod_regi = psCodRegion;

  END IF;

  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_CONTR_ASIST_TRIUN: '||ls_sqlerrm);
END PER_PR_GENER_CONTR_ASIST_TRIUN;

END PER_PKG_REPOR_PERCE;
/
