CREATE OR REPLACE PACKAGE "VEN_PKG_REPOR" IS
   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;
   TYPE TRegProductiTerritorio IS RECORD (
     COD_PAIS         SEG_PAIS.COD_PAIS%TYPE,
     COD_PERI         SEG_PERIO_CORPO.COD_PERI%TYPE,
     COD_REGI         ZON_REGIO.COD_REGI%TYPE,
     COD_ZONA         ZON_ZONA.COD_ZONA%TYPE,
     COD_SECC         ZON_SECCI.COD_SECC%TYPE,
     COD_TERR         ZON_TERRI.COD_TERR%TYPE,
     NUM_ACTI_INIC    INT_FUENT_VENTAS_REAL.NUM_ACTI_INIC%TYPE,
     NUM_ACTI_FINA    INT_FUENT_VENTAS_REAL.NUM_ACTI_FINA%TYPE,
     VTA_NETA         NUMBER,
     NUM_INGR         INT_FUENT_VENTAS_REAL.NUM_INGR%TYPE,
     NUM_REIN         INT_FUENT_VENTAS_REAL.NUM_REIN%TYPE,
     NUM_EGRE         INT_FUENT_VENTAS_REAL.NUM_EGRE%TYPE,
     CONS_4CAMP       NUMBER
   );
   TYPE tablaProductiTerritorio IS TABLE OF TRegProductiTerritorio;
   TYPE TRegConsultoraEgresada IS RECORD (
     COD_PAIS         SEG_PAIS.COD_PAIS%TYPE,
     COD_PERI         SEG_PERIO_CORPO.COD_PERI%TYPE,
     COD_REGI         ZON_REGIO.COD_REGI%TYPE,
     COD_ZONA         ZON_ZONA.COD_ZONA%TYPE,
     COD_SECC         ZON_SECCI.COD_SECC%TYPE,
     COD_TERR         ZON_TERRI.COD_TERR%TYPE,
     COD_CLIE         MAE_CLIEN.COD_CLIE%TYPE,
     NOM_CLIE         VARCHAR2(500),
     DIR_CLIE         VARCHAR2(1000),
     VAL_CAMPA_INGR   SEG_PERIO_CORPO.COD_PERI%TYPE,
     NUM_DOCU_IDEN    MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE,
     IMP_MONT_DEUD    NUMBER,
     TEL_FIJO         MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
     TEL_MOVI         MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
     TEL_TRAB         MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE
   );
  TYPE tablaConsultoraEgresada IS TABLE OF TRegConsultoraEgresada;
  TYPE TABLA_VENTA_VARIABLE IS TABLE OF ven_repor_venta_varia%ROWTYPE;

  TYPE tRegIDPeriodo IS RECORD (
     OID_PERI    NUMBER
   );
  TYPE TABLA_OID_PERIODO IS TABLE OF tRegIDPeriodo ;

  TYPE TRegVariablesVentaSeccion IS RECORD (
       COD_ZONA       VARCHAR2(4),
       COD_SECC       VARCHAR2(1),
       VEN_REAL       NUMBER(9),
       ACT_INIC       NUMBER(7),
       NUM_INGR       NUMBER(5),
       NUM_REIN       NUMBER(5),
       NUM_EGRE       NUMBER(5),
       POR_EGRE       NUMBER(4,1),
       ACT_FINA       NUMBER(7),
       CAP            NUMBER(5),
       POR_ACTI       NUMBER(5,2),
       NUM_PEDI       NUMBER(7),
       PRO_SOLE_PEDI  NUMBER(4),
       NUM_UNID       NUMBER(7),
       PRO_UNID_PEDI  NUMBER(4),
       PPU            NUMBER(5,2)
   );
  TYPE tablaVariablesVentaSeccion IS TABLE OF TRegVariablesVentaSeccion;

  /* Declaracion de Variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=1000;
/***************************************************************************
Descripcion       : Devuelve Codigo Periodo Inicial y Final de acuerdo al Anno
                    y Tipo de Rango Ingresados
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_DEVUE_RANGO_CODI_PERI(
   psTipoRango  VARCHAR2,
   psAnno VARCHAR2,
   psCodPeriodoIni OUT VARCHAR2,
   psCodPeriodoFin OUT VARCHAR2
);
/***************************************************************************
Descripcion       : Devuelve Tabla de OID de Periodos en base al Codigo de Periodo
                    Inicial y final
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_DEVUE_TABLA_OID_PERI(
   psCodPeriodoIni VARCHAR2,
   psCodPeriodoFin VARCHAR2,
   pnIdPais        NUMBER,
   pnIdMarca       NUMBER,
   pnIdCanal       NUMBER
)
RETURN TABLA_OID_PERIODO;
/***************************************************************************
Descripcion       : Devuelve Nro de Campa?as entre un rango de periodo
                    Inicial y final
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_DEVUE_NUME_CAMPA(
   psCodPeriodoIni VARCHAR2,
   psCodPeriodoFin VARCHAR2,
   pnIdPais        NUMBER,
   pnIdMarca       NUMBER,
   pnIdCanal       NUMBER
)
RETURN NUMBER;
/***************************************************************************
Descripcion       : Devuelve Table de OID de Periodos en base a Rango de Codigos de
                    Periodos Inicial y Final
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_OBTIE_LISTA_OID_PERI(
  psCodPeriIni VARCHAR2,
  psCodPeriFin VARCHAR2,
  pnIdPais  NUMBER,
  pnIdMarca NUMBER,
  pnIdCanal NUMBER
)
RETURN TABLA_OID_PERIODO PIPELINED;
/***************************************************************************
Descripcion       : Devuelve Table de OID de Periodos en base a:
                  * Rango de Periodos Actuales Inicial y Final
                  * Rango de Periodos A?o Anterior Inicial y Final
                  * Rango de Periodos Anterior Inicial y Final
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_OBTIE_LISTA_OID_PERI(
  psCodPeriIni VARCHAR2,
  psCodPeriFin VARCHAR2,
  psCodPeriAnnoAnteIni VARCHAR2,
  psCodPeriAnnoAnteFin VARCHAR2,
  psCodPeriAnteIni VARCHAR2,
  psCodPeriAnteFin VARCHAR2,
  pnIdPais  NUMBER,
  pnIdMarca NUMBER,
  pnIdCanal NUMBER
)
RETURN TABLA_OID_PERIODO PIPELINED;
/***************************************************************************
Descripcion       : Devuelve el importe de Venta Neta x Territorio
Fecha Creacion    : 16/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_DEVUE_VENTA_NETA_TERRI(
    pnIdPais    VARCHAR2,
    pnIdPeriodo VARCHAR2,
    pnIdRegion  VARCHAR2,
    pnIdZona    VARCHAR2,
    pnIdTerri   VARCHAR2)
RETURN NUMBER;
/***************************************************************************
Descripcion       : Devuelve Direccion del Cliente relacionando con e? Ubigeo
Fecha Creacion    : 16/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_OBTIE_UBIGEO_CLIEN(
  pnIdPais     NUMBER,
  pnIdCliente  NUMBER)
RETURN VARCHAR2;
/***************************************************************************
Descripcion       : Devuelve Informe de Productividad por Territorios
Fecha Creacion    : 16/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_OBTIE_PRODU_TERRI(
    psCodPais  VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL);
/***************************************************************************
Descripcion       : Devuelve Listado General de Consultoras Egresadas
Fecha Creacion    : 16/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_OBTIE_CONSU_EGRESA(
    psCodPais    VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL)
RETURN  tablaConsultoraEgresada PIPELINED;
/***************************************************************************
Descripcion       :  Obtiene el valor del cobro a 31 diaz , para 3 campañas anteriores
                    segun los parametros ingresados
Parametros        : psCodPeriodo Codigo del Periodo
                    psCodRegion Codigo de la Region
                    psCodZona Codigo de la Zona
                    psCodSeccion Codigo de la Seccion
                    psOidMarca Oid de la Marca
                    psOidCanal Oid de la Canal
                    psOidPais Oid de la Pais
Fecha Creacion    : 18/05/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_COBRO_31DIAS(
    psCodPeriodo VARCHAR2,
    psCodRegion VARCHAR2,
    psCodZona VARCHAR2,
    psCodSeccion VARCHAR2,
    psOidMarca NUMBER,
    psOidCanal NUMBER,
    psOidPais NUMBER
    )
    RETURN NUMBER;
/***************************************************************************
Descripcion       :  Obtiene el valor del cobro a 31 diaz por zona ,
                     para 3 campañas anteriores
                    segun los parametros ingresados
Parametros        : psCodPeriodo Codigo del Periodo
                    psCodRegion Codigo de la Region
                    psCodZona Codigo de la Zona
                    psCodMarca Oid de la Marca
                    psCodCanal Oid de la Canal
                    psCodPais Oid de la Pais
Fecha Creacion    : 18/05/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_COBRO_31DIAS_ZONA(
    psCodPeriodo VARCHAR2,
    psCodRegion VARCHAR2,
    psCodZona VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoPais VARCHAR2
    )
    RETURN NUMBER;
/***************************************************************************
Descripcion       :  Obtiene el cuantos clientes han pasado productos
                     cuatro campañas seguidas a partir de una campaña
                     inicial y el codigo de territorio
                    segun los parametros ingresados
Parametros        : psCodPeriodo Codigo del Periodo
                    psCodTerritorio Codigo de la Territorio
                    psCodMarca Oid de la Marca
                    psCodCanal Oid de la Canal
                    psCodPais Oid de la Pais
Fecha Creacion    : 18/07/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_CLIEN_4CAMP_TERRI(
    psCodPeriodo VARCHAR2,
    psCodTerritorio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoPais VARCHAR2
    )
    RETURN NUMBER ;
/***************************************************************************
Descripcion       : Genera la data para el Reporte de Venta Variable
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE  VEN_PR_OBTIE_VENTA_VARIA(
    psCodPais VARCHAR2,
    psCodSociedad VARCHAR2,
    psCodAlmacen VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psTipoPresentacion VARCHAR2,
    psTipoRango  VARCHAR2,
    psAnno VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodPeriodoFinal VARCHAR2,
    psTipoReporte VARCHAR2
);

/***************************************************************************
Descripcion       :  Funcion para obtener el Valor Total de los montos del
                     reporte de Evalucion de Venta y Variables por Seccion
Fecha Creacion    : 02/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_EVALU_VARIA_SECCI(
    psCodigoPais     VARCHAR2,
    psCodigoPeriodo VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2);

/***************************************************************************
Descripcion       :  Funcion para obtener el Valor Total de los montos del
                     reporte de Evalucion de Venta y Variables por Seccion
                     Para sumarizar por Zona
Fecha Creacion    : 22/06/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_EVALU_SECC_ZONA(
    psCodigoPais     VARCHAR2,
    psCodigoPeriodo VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2,
    lnIdPais     NUMBER,
    lnIdPeriodo NUMBER,
    lnIdMarca NUMBER,
    lnIdCanal NUMBER,
    lnIdRegion NUMBER,
    lnIdZona NUMBER);
/***************************************************************************
Descripcion       :  Reporte Flujo de Resultados Economicos por Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_RESUL_ECONO_ZONA(
    psCodigoPais     VARCHAR2,
    psAnio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2);

/***************************************************************************
Descripcion       :  Obtiene el valor del VAL_VENT_NETA_ESTA segun los parametros
                     ingresados
Parametros        : psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_VENT_PREVI_SAP_NETA(
    psOidPeriodo NUMBER,
    psOidRegion NUMBER,
    psOidZona NUMBER)
    RETURN NUMBER;
/***************************************************************************
Descripcion       :  Obtiene el valor del IMP_VENT_NETA_ESTA segun los parametros
                     ingresados
Parametros        : psOidPais Oid del Pais
                    psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_VENT_REAL_NETA(
    psOidPais NUMBER,
    psOidPeriodo NUMBER,
    psOidRegion NUMBER,
    psOidZona NUMBER)
      RETURN NUMBER ;
/***************************************************************************
Descripcion       :  VENTAS REALES de las CONSULTORAS NUEVAS
Parametros        : psOidPais Oid del Pais
                    psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_VENT_REAL_CLIE(
    psOidPais NUMBER,
    psOidMarca NUMBER,
    psOidCanal NUMBER,
    psOidPeriodo NUMBER,
    psOidRegion NUMBER,
    psOidZona NUMBER)
 RETURN NUMBER ;
/***************************************************************************
Descripcion       : Obtiene el valor del IMP_VENT_NETA_ESTA segun los parametros
                     ingresados, por region (Venta Reales año actual por Region)
Parametros        : psOidPais Oid del Pais
                    psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_REPOR_ECONO_DATO_ADIC(
    psOidPais NUMBER,
    psOidPeri NUMBER,
    psOidZona NUMBER ,
    psOidMarca NUMBER,
    psOidCanal NUMBER,
    psUnidadesVendidas OUT NUMBER,
    psMontoFaltante OUT NUMBER,
    psUnidadesFaltante OUT NUMBER,
    psMontoDevolucion OUT NUMBER,
    psUnidadesDevolucion OUT NUMBER,
    psPedidosAnulados OUT NUMBER,
    psNumeroPedidos OUT NUMBER
    )    ;
/***************************************************************************
Descripcion       :  Reporte Flujo de Resultados Economicos
Fecha Creacion    : 09/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_RESUL_ECONO(
    psCodigoPais     VARCHAR2,
    psAnio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2);
/***************************************************************************
Descripcion       : Reporte Evaluacion Cobertura y Niveles por Seccion
Fecha Creacion    : 10/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_EVALU_COBER_NIVEL(
    psCodigoPais     VARCHAR2,
    psAnio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2);
/***************************************************************************
Descripcion       :  Reporte Flujo de Resultados Economicos por Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_EVALU_COBER_ZONA(
    psCodigoPais     VARCHAR2,
    psAnio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2,
    psTrimeste NUMBER,
    psTotalTrimestre NUMBER);
/***************************************************************************
Descripcion       : Para el reporte de Evaluacion de Cobertura y Niceles
                    por Seccion se necesita determinar que consultoras se
                    encontraban cerradas.

Parametros        : psOidCliente Oid del Cliente
                    psOidPeriodo Oid del Periodo
Fecha Creacion    : 02/11/2007
Autor             : José Antonio Cairampoma Granados
***************************************************************************/
FUNCTION VEN_FN_OBTIE_ULTI_ESTA(
    psOidCliente ped_solic_cabec.clie_oid_clie%TYPE,
    psOidPeriodo NUMBER)
    RETURN VARCHAR;
/***************************************************************************
Descripcion       :  Para el reporte de EVALUACION DE COBERTRURA Y NIVELES POR
                      SECCION, se necesita saber la lista de los codigos de
                      territorios en los cuales no tubieron venta.
Parametros        : psOidPais Oid del Pais
                    psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 11/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_TERR_VACI(
    psOidPais NUMBER,
    psOidPeriodo NUMBER,
    psOidRegion NUMBER,
    psOidZona NUMBER)
    RETURN VARCHAR  ;

/***************************************************************************
Descripcion       : Genera la data para el Reporte de Variables de Venta por
                    Sección
Fecha Creacion    : 08/11/2007
Autor             : José A. Cairampoma
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VARIA_VENTA_SECCI(
    psCodPais VARCHAR2,
    psCodSociedad VARCHAR2,
    psCodAlmacen VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2
)
RETURN tablaVariablesVentaSeccion PIPELINED;


/***************************************************************************
Descripcion       : Genera la data para el Reporte de Analisis General de Area
Fecha Creacion    : 12/02/2008
Parametros        :
      psCodPais     : Codigo de pais
      psCodSociedad : Codigo de sociedad
      psCodAlmacen  : Codigo de almacen
      psCodMarca    : Codigo de marca
      psCodCanal    : Codigo de Canal
      psCodPeriodo  : Codigo de Periodo
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_OBTIE_ANALI_GENER_AREA(
    psCodPais VARCHAR2,
    psCodSociedad VARCHAR2,
    psCodAlmacen VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2
);


/***************************************************************************
Descripcion       : Genera la data para el Reporte de Venta Variable de
                    la tabla Historica
Fecha Creacion    : 15/04/2009
Autor             : Telly Tataje
***************************************************************************/
PROCEDURE  VEN_PR_CARGA_HISTO_VENTA_CATAL(
    psCodPais VARCHAR2,
    psCodSociedad VARCHAR2,
    psCodAlmacen VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodCampanhia VARCHAR2
);

/***************************************************************************
Descripcion       : Inserta la data para el Reporte de Factura x Auditar
                    (Consolidado)
Fecha Creacion    : 12/10/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_REPOR_FACTU_AUDIT_CONSO(
    psFechaDesde VARCHAR2,
    psFechaHasta VARCHAR2
);

/***************************************************************************
Descripcion       : Inserta la data para el Reporte de Factura x Auditar
                    (Detallado)
Fecha Creacion    : 12/10/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_REPOR_FACTU_AUDIT_DETAL(
    psFechaDesde VARCHAR2,
    psFechaHasta VARCHAR2
);

END VEN_PKG_REPOR;
/
CREATE OR REPLACE PACKAGE BODY "VEN_PKG_REPOR"
IS
/***************************************************************************
Descripcion       : Devuelve Codigo Periodo Inicial y Final de acuerdo al A?o
                    y Tipo de Rango Ingresados
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_DEVUE_RANGO_CODI_PERI(
   psTipoRango  VARCHAR2,
   psAnno VARCHAR2,
   psCodPeriodoIni OUT VARCHAR2,
   psCodPeriodoFin OUT VARCHAR2
)
IS
  lsCodPeriIni     CRA_RANGO_PERIO.PER_INIC%TYPE;
  lsCodPeriFin     CRA_RANGO_PERIO.PER_FINA%TYPE;
BEGIN
   SELECT TRIM(A.PER_INIC), TRIM(A.PER_FINA)
   INTO
        lsCodPeriIni, lsCodPeriFin
   FROM CRA_RANGO_PERIO A
   WHERE
        A.COD_RAPE = psTipoRango;
   psCodPeriodoIni := TRIM(psAnno || lsCodPeriIni);
   psCodPeriodoFin := TRIM(psAnno || lsCodPeriFin);
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_DEVUE_RANGO_CODI_PERI: '||ls_sqlerrm);
END VEN_PR_DEVUE_RANGO_CODI_PERI;
/***************************************************************************
Descripcion       : Devuelve Nro de Campa?as entre un rango de periodo
                    Inicial y final
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_DEVUE_NUME_CAMPA(
   psCodPeriodoIni VARCHAR2,
   psCodPeriodoFin VARCHAR2,
   pnIdPais        NUMBER,
   pnIdMarca       NUMBER,
   pnIdCanal       NUMBER
)
RETURN NUMBER
IS
  lsCodPeri        seg_perio_corpo.cod_peri%TYPE;
  X                NUMBER;
BEGIN
  X := 0;
  IF psCodPeriodoIni > psCodPeriodoFin THEN
     RETURN -1;
  END IF;
  lsCodPeri := psCodPeriodoIni;
  while true
  loop
      lsCodPeri := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCodPeri, pnIdPais, pnIdMarca, pnIdCanal, 1);
      IF lsCodPeri <= psCodPeriodoFin THEN
         X := X + 1;
      ELSE
         EXIT;
      END IF;
  end loop;
  RETURN X + 1;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_DEVUE_NUME_CAMPA: '||ls_sqlerrm);
END VEN_FN_DEVUE_NUME_CAMPA;
/***************************************************************************
Descripcion       : Devuelve Tabla de OID de Periodos en base al Codigo de Periodo
                    Inicial y final
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_DEVUE_TABLA_OID_PERI(
   psCodPeriodoIni VARCHAR2,
   psCodPeriodoFin VARCHAR2,
   pnIdPais        NUMBER,
   pnIdMarca       NUMBER,
   pnIdCanal       NUMBER
)
RETURN TABLA_OID_PERIODO
IS
  lsCodPeri        seg_perio_corpo.cod_peri%TYPE;
  lnIdPeriodo      cra_perio.oid_peri%TYPE;
  TablaIdPeriodo   TABLA_OID_PERIODO;
  lnNroCampana     NUMBER;
  X                NUMBER;
BEGIN
  lnNroCampana   := VEN_FN_DEVUE_NUME_CAMPA(psCodPeriodoIni, psCodPeriodoFin, pnIdPais, pnIdMarca, pnIdCanal);
  TablaIdPeriodo := TABLA_OID_PERIODO();
  TablaIdPeriodo.EXTEND(lnNroCampana);
  lnIdPeriodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodoIni, pnIdMarca, pnIdCanal);
  X := 1;
  lsCodPeri := psCodPeriodoIni;
  TablaIdPeriodo(X).oid_peri :=  lnIdPeriodo;
  while true
  loop
      X := X + 1;
      lsCodPeri := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCodPeri, pnIdPais, pnIdMarca, pnIdCanal, 1);
      IF lsCodPeri <= psCodPeriodoFin THEN
         lnIdPeriodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeri, pnIdMarca, pnIdCanal);
         TablaIdPeriodo(X).oid_peri :=  lnIdPeriodo;
      ELSE
         EXIT;
      END IF;
  end loop;
  RETURN TablaIdPeriodo;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_DEVUE_TABLA_OID_PERI: '||ls_sqlerrm);
END VEN_FN_DEVUE_TABLA_OID_PERI;
/***************************************************************************
Descripcion       : Devuelve Table de OID de Periodos en base a Rango de Codigos de
                    Periodos Inicial y Final
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_OBTIE_LISTA_OID_PERI(
  psCodPeriIni VARCHAR2,
  psCodPeriFin VARCHAR2,
  pnIdPais  NUMBER,
  pnIdMarca NUMBER,
  pnIdCanal NUMBER
)
RETURN TABLA_OID_PERIODO PIPELINED
IS
  tablaRegistro      TABLA_OID_PERIODO;
BEGIN
  /* Rango de Periodos */
  IF psCodPeriIni IS NOT NULL THEN
     tablaRegistro := VEN_FN_DEVUE_TABLA_OID_PERI(psCodPeriIni, psCodPeriFin, pnIdPais, pnIdMarca, pnIdCanal);
     IF tablaRegistro.COUNT > 0 THEN
        FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
            PIPE ROW(tablaRegistro(x));
        END LOOP;
     END IF;
  END IF;
  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_OBTIE_LISTA_OID_PERI: '||ls_sqlerrm);
END VEN_FN_OBTIE_LISTA_OID_PERI;
/***************************************************************************
Descripcion       : Devuelve Table de OID de Periodos en base a:
                  * Rango de Periodos Actuales Inicial y Final
                  * Rango de Periodos A?o Anterior Inicial y Final
                  * Rango de Periodos Anterior Inicial y Final
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_OBTIE_LISTA_OID_PERI(
  psCodPeriIni VARCHAR2,
  psCodPeriFin VARCHAR2,
  psCodPeriAnnoAnteIni VARCHAR2,
  psCodPeriAnnoAnteFin VARCHAR2,
  psCodPeriAnteIni VARCHAR2,
  psCodPeriAnteFin VARCHAR2,
  pnIdPais  NUMBER,
  pnIdMarca NUMBER,
  pnIdCanal NUMBER
)
RETURN TABLA_OID_PERIODO PIPELINED
IS
  tablaRegistro      TABLA_OID_PERIODO;
  tablaRegistroAnno  TABLA_OID_PERIODO;
  tablaRegistroAnte  TABLA_OID_PERIODO;
BEGIN
  /* Periodos Actuales */
  IF psCodPeriIni IS NOT NULL THEN
     tablaRegistro := VEN_FN_DEVUE_TABLA_OID_PERI(psCodPeriIni, psCodPeriFin, pnIdPais, pnIdMarca, pnIdCanal);
     IF tablaRegistro.COUNT > 0 THEN
        FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
            PIPE ROW(tablaRegistro(x));
        END LOOP;
     END IF;
  END IF;
  /* Periodos A?o anterior */
  IF psCodPeriAnnoAnteIni IS NOT NULL THEN
     tablaRegistroAnno := VEN_FN_DEVUE_TABLA_OID_PERI(psCodPeriAnnoAnteIni, psCodPeriAnnoAnteFin, pnIdPais, pnIdMarca, pnIdCanal);
     IF tablaRegistroAnno.COUNT > 0 THEN
        FOR x IN tablaRegistroAnno.FIRST .. tablaRegistroAnno.LAST LOOP
            PIPE ROW(tablaRegistroAnno(x));
        END LOOP;
     END IF;
  END IF;
  /* Periodos anteriores */
  IF psCodPeriAnteIni IS NOT NULL THEN
     tablaRegistroAnte := VEN_FN_DEVUE_TABLA_OID_PERI(psCodPeriAnteIni, psCodPeriAnteFin, pnIdPais, pnIdMarca, pnIdCanal);
     IF tablaRegistroAnte.COUNT > 0 THEN
        FOR x IN tablaRegistroAnte.FIRST .. tablaRegistroAnte.LAST LOOP
            PIPE ROW(tablaRegistroAnte(x));
        END LOOP;
     END IF;
  END IF;
  RETURN ;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_OBTIE_LISTA_OID_PERI: '||ls_sqlerrm);
END VEN_FN_OBTIE_LISTA_OID_PERI;
/***************************************************************************
Descripcion       : Devuelve el importe de Venta Neta x Territorio
Fecha Creacion    : 16/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_DEVUE_VENTA_NETA_TERRI(
    pnIdPais    VARCHAR2,
    pnIdPeriodo VARCHAR2,
    pnIdRegion  VARCHAR2,
    pnIdZona    VARCHAR2,
    pnIdTerri   VARCHAR2)
RETURN NUMBER
IS
  lnImporte    INT_FUENT_VENTA_REAL_VACUM.Imp_Vent_Neta_Esta%TYPE;
BEGIN
  SELECT nvl(SUM(A.IMP_VENT_NETA_ESTA),0)
  INTO lnImporte
  FROM INT_FUENT_VENTA_REAL_VACUM A
  WHERE A.PAIS_OID_PAIS = pnIdPais
  AND   A.PERD_OID_PERI = pnIdPeriodo
  AND   A.ZORG_OID_REGI = pnIdRegion
  AND   A.ZZON_OID_ZONA = pnIdZona
  AND   A.TERR_OID_TERR = pnIdTerri;
  RETURN lnImporte;
EXCEPTION
WHEN no_data_found  THEN
     RETURN 0;
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_DEVUE_VENTA_NETA_TERRI: '||ls_sqlerrm);
END VEN_FN_DEVUE_VENTA_NETA_TERRI;
/***************************************************************************
Descripcion       : Devuelve Informe de Productividad por Territorios
Fecha Creacion    : 16/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_OBTIE_PRODU_TERRI(
    psCodPais  VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL)
IS
  tablaRegistro   tablaProductiTerritorio;
  CURSOR cursorRegistro1
  (vnIdPais NUMBER, vnIdPeriodo NUMBER, vnIdRegion NUMBER, vnIdZona NUMBER)
  IS
  SELECT
    psCodPais    AS COD_PAIS,
    psCodPeriodo AS COD_PERI,
    psCodRegion  AS COD_REGI,
    psCodZona    AS COD_ZONA,
    H.COD_SECC,
    F.COD_TERR,
    A.NUM_ACTI_INIC,
    A.NUM_ACTI_FINA,
    VEN_PKG_REPOR.VEN_FN_DEVUE_VENTA_NETA_TERRI(vnIdPais, vnIdPeriodo, vnIdRegion, vnIdZona, A.TERR_OID_TERR) AS VTA_NETA,
    A.NUM_INGR,
    A.NUM_REIN,
    A.NUM_EGRE,
    VEN_PKG_REPOR.VEN_FN_OBTIE_CLIEN_4CAMP_TERRI(psCodPeriodo,F.COD_TERR, psCodMarca,psCodCanal,psCodPais) as CONS_4CAMP
  FROM
    INT_FUENT_VENTAS_REAL A,
    ZON_TERRI F,
    ZON_TERRI_ADMIN G,
    ZON_SECCI H
  WHERE A.PAIS_OID_PAIS = vnIdPais
    AND A.PERD_OID_PERI = vnIdPeriodo
    AND A.ZORG_OID_REGI = vnIdRegion
    AND A.ZZON_OID_ZONA = vnIdZona
    AND F.PAIS_OID_PAIS = A.PAIS_OID_PAIS
    AND F.OID_TERR = A.TERR_OID_TERR
    AND F.OID_TERR = G.TERR_OID_TERR
    AND H.OID_SECC = G.ZSCC_OID_SECC
  ORDER BY
    H.COD_SECC, F.COD_TERR;
  CURSOR cursorRegistro2
  (vnIdPais NUMBER, vnIdPeriodo NUMBER, vnIdRegion NUMBER)
  IS
  SELECT
    psCodPais    AS COD_PAIS,
    psCodPeriodo AS COD_PERI,
    psCodRegion  AS COD_REGI,
    B.COD_ZONA,
    H.COD_SECC,
    F.COD_TERR,
    A.NUM_ACTI_INIC,
    A.NUM_ACTI_FINA,
    VEN_PKG_REPOR.VEN_FN_DEVUE_VENTA_NETA_TERRI(vnIdPais, vnIdPeriodo, vnIdRegion, A.ZZON_OID_ZONA, A.TERR_OID_TERR) AS VTA_NETA,
    A.NUM_INGR,
    A.NUM_REIN,
    A.NUM_EGRE,
    VEN_PKG_REPOR.VEN_FN_OBTIE_CLIEN_4CAMP_TERRI(psCodPeriodo,F.COD_TERR, psCodMarca,psCodCanal,psCodPais) as CONS_4CAMP
  FROM
    INT_FUENT_VENTAS_REAL A,
    ZON_ZONA  B,
    ZON_TERRI F,
    ZON_TERRI_ADMIN G,
    ZON_SECCI H
  WHERE A.PAIS_OID_PAIS = vnIdPais
    AND A.PERD_OID_PERI = vnIdPeriodo
    AND A.ZORG_OID_REGI = vnIdRegion
    AND F.PAIS_OID_PAIS = A.PAIS_OID_PAIS
    AND F.OID_TERR = A.TERR_OID_TERR
    AND F.OID_TERR = G.TERR_OID_TERR
    AND H.OID_SECC = G.ZSCC_OID_SECC
    AND B.OID_ZONA = A.ZZON_OID_ZONA
  ORDER BY
    B.COD_ZONA, H.COD_SECC, F.COD_TERR;
  CURSOR cursorRegistro3
  (vnIdPais NUMBER, vnIdPeriodo NUMBER, vnIdMarca NUMBER, vnIdCanal NUMBER)
  IS
  SELECT
    psCodPais    AS COD_PAIS,
    psCodPeriodo AS COD_PERI,
    C.COD_REGI,
    B.COD_ZONA,
    H.COD_SECC,
    F.COD_TERR,
    A.NUM_ACTI_INIC,
    A.NUM_ACTI_FINA,
    VEN_PKG_REPOR.VEN_FN_DEVUE_VENTA_NETA_TERRI(vnIdPais, vnIdPeriodo, A.ZORG_OID_REGI, A.ZZON_OID_ZONA, A.TERR_OID_TERR) AS VTA_NETA,
    A.NUM_INGR,
    A.NUM_REIN,
    A.NUM_EGRE,
    VEN_PKG_REPOR.VEN_FN_OBTIE_CLIEN_4CAMP_TERRI(psCodPeriodo,F.COD_TERR, psCodMarca,psCodCanal,psCodPais) as CONS_4CAMP
  FROM
    INT_FUENT_VENTAS_REAL A,
    ZON_ZONA  B,
    ZON_REGIO C,
    ZON_TERRI F,
    ZON_TERRI_ADMIN G,
    ZON_SECCI H
  WHERE A.PAIS_OID_PAIS = vnIdPais
    AND A.PERD_OID_PERI = vnIdPeriodo
    AND C.PAIS_OID_PAIS = A.PAIS_OID_PAIS
    AND C.MARC_OID_MARC = vnIdMarca
    AND C.CANA_OID_CANA = vnIdCanal
    AND B.PAIS_OID_PAIS = A.PAIS_OID_PAIS
    AND B.MARC_OID_MARC = vnIdMarca
    AND B.CANA_OID_CANA = vnIdCanal
    AND F.PAIS_OID_PAIS = A.PAIS_OID_PAIS
    AND F.OID_TERR = A.TERR_OID_TERR
    AND F.OID_TERR = G.TERR_OID_TERR
    AND H.OID_SECC = G.ZSCC_OID_SECC
    AND C.OID_REGI = A.ZORG_OID_REGI
    AND B.OID_ZONA = A.ZZON_OID_ZONA
  ORDER BY
    C.COD_REGI, B.COD_ZONA, H.COD_SECC, F.COD_TERR;
  lnIdPais         SEG_PAIS.OID_PAIS%TYPE;
  lnIdCanal        SEG_CANAL.OID_CANA%TYPE;
  lnIdMarca        SEG_MARCA.OID_MARC%TYPE;
  lnIdPeriodo      CRA_PERIO.OID_PERI%TYPE;
  lnIdRegion       ZON_REGIO.OID_REGI%TYPE;
  lnIdZona         ZON_ZONA.OID_ZONA%TYPE;
BEGIN
  /* obteniendo id's */
  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lnIdPeriodo    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);
  delete from VEN_REPOR_INFOR_PRODU_TERRI;
  IF (TRIM(psCodRegion) IS NOT NULL) THEN
     lnIdRegion     := gen_pkg_gener.gen_fn_devuelve_id_region(psCodPais, psCodMarca, psCodCanal, psCodRegion);
  END IF;
  IF (TRIM(psCodZona) IS NOT NULL) THEN
     lnIdZona       := gen_pkg_gener.gen_fn_devuelve_id_zona(psCodPais, psCodMarca, psCodCanal, psCodRegion, psCodZona);
  END IF;
  /* Obteniendo data para Region y Zona diferente de NULL */
  IF (TRIM(psCodRegion) IS NOT NULL) AND (TRIM(psCodZona) IS NOT NULL) THEN
      OPEN cursorRegistro1(lnIdPais, lnIdPeriodo, lnIdRegion, lnIdZona);
      LOOP
           FETCH cursorRegistro1 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 insert into VEN_REPOR_INFOR_PRODU_TERRI values
                 tablaRegistro(x);
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro1%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro1;
      RETURN ;
  END IF;
  /* Obteniendo data para Region diferente de NULL y cualquier zona */
  IF (TRIM(psCodRegion) IS NOT NULL) AND (TRIM(psCodZona) IS NULL) THEN
      OPEN cursorRegistro2(lnIdPais, lnIdPeriodo, lnIdRegion);
      LOOP
           FETCH cursorRegistro2 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 insert into VEN_REPOR_INFOR_PRODU_TERRI values
                 tablaRegistro(x);
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro2%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro2;
      RETURN ;
  END IF;
  /* Obteniendo data para cualquier Region y cualquier zona */
  IF (TRIM(psCodRegion) IS NULL) AND (TRIM(psCodZona) IS NULL) THEN
      OPEN cursorRegistro3(lnIdPais, lnIdPeriodo, lnIdMarca, lnIdCanal);
      LOOP
           FETCH cursorRegistro3 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 insert into VEN_REPOR_INFOR_PRODU_TERRI values
                 tablaRegistro(x);
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro3%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro3;
      RETURN ;
  END IF;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_PRODU_TERRI: '||ls_sqlerrm);
END VEN_PR_OBTIE_PRODU_TERRI ;
/***************************************************************************
Descripcion       : Devuelve Direccion del Cliente relacionando con e? Ubigeo
Fecha Creacion    : 16/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_OBTIE_UBIGEO_CLIEN(
  pnIdPais     NUMBER,
  pnIdCliente  NUMBER)
RETURN VARCHAR2
IS
  lsDireClien  VARCHAR2(500);
  lsOrde1      ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE;
  lsOrde2      ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE;
  lsOrde3      ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE;
  lsOrde4      ZON_VALOR_ESTRU_GEOPO.DES_GEOG%TYPE;
  lsDescUbigeo VARCHAR2(1000);
BEGIN
   /* Se recupera la direccion del cliente */
  /* SELECT
       (SELECT (NVL(TRIM(D.DES_ABRV_TIPO_VIA),' ') ||' '|| NVL(TRIM(A.VAL_NOMB_VIA),' ') )
        FROM
          ZON_VIA C,
          SEG_TIPO_VIA D
        WHERE  C.PAIS_OID_PAIS = pnIdPais
          AND C.OID_VIA = A.TIVI_OID_TIPO_VIA
          AND D.OID_TIPO_VIA = A.TIVI_OID_TIPO_VIA) ||' '||TRIM(A.NUM_PPAL)||' '||TRIM(A.VAL_OBSE) ,
     (SELECT ZONA.DES_GEOG
     FROM ZON_VALOR_ESTRU_GEOPO ZONA
       WHERE  ZONA.ORDE_1 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,1,6)))
        AND  ZONA.ORDE_2  IS NULL
        AND  ZONA.ORDE_3  IS NULL
        AND  ZONA.ORDE_4  IS NULL
        AND  ZONA.PAIS_OID_PAIS = pnIdPais
      AND  ROWNUM = 1) ORDE_1,
       (SELECT ZONA.DES_GEOG
     FROM ZON_VALOR_ESTRU_GEOPO ZONA
        WHERE ZONA.ORDE_1 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,1,6)))
        AND  ZONA.ORDE_2 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,7,6)))
        AND  ZONA.ORDE_3  IS NULL
        AND  ZONA.ORDE_4  IS NULL
        AND  ZONA.PAIS_OID_PAIS = pnIdPais
      AND  ROWNUM = 1) ORDE_2,
       (SELECT ZONA.DES_GEOG
     FROM ZON_VALOR_ESTRU_GEOPO ZONA
        WHERE ZONA.ORDE_1 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,1,6)))
        AND  ZONA.ORDE_2 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,7,6)))
        AND  ZONA.ORDE_3 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,13,6)))
        AND  ZONA.ORDE_4  IS NULL
        AND  ZONA.PAIS_OID_PAIS = pnIdPais
          AND  ROWNUM = 1) ORDE_3,
       (SELECT ZONA.DES_GEOG
     FROM ZON_VALOR_ESTRU_GEOPO ZONA
        WHERE ZONA.ORDE_1 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,1,6)))
        AND  ZONA.ORDE_2 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,7,6)))
        AND  ZONA.ORDE_3 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,13,6)))
        AND  ZONA.ORDE_4 = LTRIM(RTRIM(SUBSTR(A.COD_UNID_GEOG,19,6)))
        AND  ZONA.ORDE_5  IS NULL
        AND  ZONA.PAIS_OID_PAIS = pnIdPais
      AND  ROWNUM = 1) ORDE_4
   INTO
       lsDireClien,
       lsOrde1,
       lsOrde2,
       lsOrde3,
       lsOrde4
   FROM
       MAE_CLIEN_DIREC A
   WHERE   A.CLIE_OID_CLIE = pnIdCliente
       AND A.IND_DIRE_PPAL = 1;
   lsDescUbigeo := NVL(TRIM(lsDireClien),' ') ||' '|| NVL(TRIM(lsOrde1),' ') ||' '||
                   NVL(TRIM(lsOrde2),' ') ||' '|| NVL(TRIM(lsOrde3),' ') ||' '||
                   NVL(TRIM(lsOrde4),' ');*/

       lsDireClien:= GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(pnIdCliente, 'DES_DIRE');
       lsOrde4:= GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(pnIdCliente, 'DES_DIST') ;
       lsOrde3:= GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(pnIdCliente, 'DES_URBA');
       lsDescUbigeo := NVL(TRIM(lsDireClien),' ') ||' '|| NVL(TRIM(lsOrde1),' ') ||' '||
                   NVL(TRIM(lsOrde2),' ') ||' '|| NVL(TRIM(lsOrde3),' ') ||' '||
                   NVL(TRIM(lsOrde4),' ');
   RETURN lsDescUbigeo;
EXCEPTION
WHEN no_data_found THEN
     RETURN '';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_UBIGEO_CLIEN: '||ls_sqlerrm);
END VEN_FN_OBTIE_UBIGEO_CLIEN ;
/***************************************************************************
Descripcion       : Devuelve Listado General de Consultoras Egresadas
Fecha Creacion    : 16/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION VEN_FN_OBTIE_CONSU_EGRESA(
    psCodPais    VARCHAR2,
    psCodMarca   VARCHAR2,
    psCodCanal   VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodRegion  VARCHAR2:=NULL,
    psCodZona    VARCHAR2:=NULL)
RETURN  tablaConsultoraEgresada PIPELINED
IS
  tablaRegistro   tablaConsultoraEgresada;
  CURSOR cursorRegistro1
  (vnIdPais NUMBER, vnIdPeriodo NUMBER, vsCodRegion VARCHAR2, vsCodZona VARCHAR2)
  IS
  SELECT
    psCodPais    AS COD_PAIS,
    psCodPeriodo AS COD_PERI,
    M.COD_REGI,
    L.COD_ZONA,
    K.COD_SECC,
    I.COD_TERR,
    A.COD_CLIE,
    NVL(TRIM(A.VAL_APE1),' ') || ' ' || NVL(TRIM(A.VAL_APE2),' ') || ' ' || NVL(TRIM(A.VAL_NOM1),' ') || ' ' || NVL(TRIM(A.VAL_NOM2),' ') AS NOM_CLIE,
    VEN_PKG_REPOR.VEN_FN_OBTIE_UBIGEO_CLIEN(A.PAIS_OID_PAIS, A.OID_CLIE) AS DIR_CLIE,
    GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_INGRE(A.OID_CLIE) AS VAL_CAMPA_INGR,
    D.NUM_DOCU_IDEN,
    GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_DEUDO(A.COD_CLIE) AS IMP_MONT_DEUD,
    GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(A.OID_CLIE, 'TF') AS TEL_FIJO,
    GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(A.OID_CLIE, 'TM') AS TEL_MOVI,
    GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(A.OID_CLIE, 'TT') AS TEL_TRAB
  FROM MAE_CLIEN A,
       MAE_TIPO_CLIEN B,
       MAE_CLIEN_TIPO_SUBTI C,
       MAE_CLIEN_IDENT D,
       MAE_CLIEN_HISTO_ESTAT F,
       MAE_CLIEN_UNIDA_ADMIN G,
       MAE_ESTAT_CLIEN  H,
       ZON_TERRI I,
       ZON_TERRI_ADMIN J,
       ZON_SECCI K,
       ZON_ZONA  L,
       ZON_REGIO M
 WHERE A.PAIS_OID_PAIS = vnIdPais
       AND B.COD_TIPO_CLIE = '02'
       AND D.VAL_IDEN_DOCU_PRIN = 1
       AND H.COD_ESTA_CLIE = '05'
       AND M.COD_REGI = vsCodRegion
       AND ((vsCodZona IS NOT NULL AND L.COD_ZONA = vsCodZona) OR (vsCodZona IS NULL))
       AND F.PERD_OID_PERI = vnIdPeriodo
       AND G.IND_ACTI = 1
       AND A.OID_CLIE = C.CLIE_OID_CLIE
       AND B.OID_TIPO_CLIE = C.TICL_OID_TIPO_CLIE
       AND A.OID_CLIE = D.CLIE_OID_CLIE
       AND A.OID_CLIE = F.CLIE_OID_CLIE
       AND A.OID_CLIE = G.CLIE_OID_CLIE
       AND H.OID_ESTA_CLIE = F.ESTA_OID_ESTA_CLIE
       AND I.PAIS_OID_PAIS = A.PAIS_OID_PAIS
       AND J.OID_TERR_ADMI = G.ZTAD_OID_TERR_ADMI
       AND I.OID_TERR = J.TERR_OID_TERR
       AND K.OID_SECC = J.ZSCC_OID_SECC
       AND L.OID_ZONA = K.ZZON_OID_ZONA
       AND M.OID_REGI = L.ZORG_OID_REGI
       AND NOT EXISTS (SELECT X.OID_BLOQ
                       FROM MAE_CLIEN_BLOQU X,
                            MAE_TIPO_BLOQU Y
                       WHERE X.CLIE_OID_CLIE = A.OID_CLIE
                        AND  Y.OID_TIPO_BLOQ = X.TIBQ_OID_TIPO_BLOQ
                        AND  X.fec_desb is null
                        AND (Y.COD_TIPO_BLOQ = '01' OR Y.COD_TIPO_BLOQ = '02')
                       )
  ORDER BY
    M.COD_REGI, L.COD_ZONA, K.COD_SECC, I.COD_TERR ;
 CURSOR cursorRegistro2
  (vnIdPais NUMBER, vnIdPeriodo NUMBER, vnIdMarca NUMBER, vnIdCanal NUMBER)
  IS
  SELECT
    psCodPais    AS COD_PAIS,
    psCodPeriodo AS COD_PERI,
    M.COD_REGI,
    L.COD_ZONA,
    K.COD_SECC,
    I.COD_TERR,
    A.COD_CLIE,
    NVL(TRIM(A.VAL_APE1),' ') || ' ' || NVL(TRIM(A.VAL_APE2),' ') || ' ' || NVL(TRIM(A.VAL_NOM1),' ') || ' ' || NVL(TRIM(A.VAL_NOM2),' ') AS NOM_CLIE,
    VEN_PKG_REPOR.VEN_FN_OBTIE_UBIGEO_CLIEN(A.PAIS_OID_PAIS, A.OID_CLIE) AS DIR_CLIE,
    GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_INGRE(A.OID_CLIE) AS VAL_CAMPA_INGR,
    D.NUM_DOCU_IDEN,
    GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_DEUDO(A.COD_CLIE) AS IMP_MONT_DEUD,
    GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(A.OID_CLIE, 'TF') AS TEL_FIJO,
    GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(A.OID_CLIE, 'TM') AS TEL_MOVI,
    GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(A.OID_CLIE, 'TT') AS TEL_TRAB
  FROM MAE_CLIEN A,
       MAE_TIPO_CLIEN B,
       MAE_CLIEN_TIPO_SUBTI C,
       MAE_CLIEN_IDENT D,
       MAE_CLIEN_HISTO_ESTAT F,
       MAE_CLIEN_UNIDA_ADMIN G,
       MAE_ESTAT_CLIEN  H,
       ZON_TERRI I,
       ZON_TERRI_ADMIN J,
       ZON_SECCI K,
       ZON_ZONA  L,
       ZON_REGIO M
 WHERE A.PAIS_OID_PAIS = vnIdPais
       AND B.COD_TIPO_CLIE = '02'
       AND D.VAL_IDEN_DOCU_PRIN = 1
       AND G.IND_ACTI = 1
       AND H.COD_ESTA_CLIE = '05'
       AND F.PERD_OID_PERI = vnIdPeriodo
       AND L.PAIS_OID_PAIS = A.PAIS_OID_PAIS
       AND L.MARC_OID_MARC = vnIdMarca
       AND L.CANA_OID_CANA = vnIdCanal
       AND M.PAIS_OID_PAIS = A.PAIS_OID_PAIS
       AND M.MARC_OID_MARC = vnIdMarca
       AND M.CANA_OID_CANA = vnIdCanal
       AND A.OID_CLIE = C.CLIE_OID_CLIE
       AND B.OID_TIPO_CLIE = C.TICL_OID_TIPO_CLIE
       AND A.OID_CLIE = D.CLIE_OID_CLIE
       AND A.OID_CLIE = F.CLIE_OID_CLIE
       AND A.OID_CLIE = G.CLIE_OID_CLIE
       AND H.OID_ESTA_CLIE = F.ESTA_OID_ESTA_CLIE
       AND I.PAIS_OID_PAIS = A.PAIS_OID_PAIS
       AND J.OID_TERR_ADMI = G.ZTAD_OID_TERR_ADMI
       AND I.OID_TERR = J.TERR_OID_TERR
       AND K.OID_SECC = J.ZSCC_OID_SECC
       AND L.OID_ZONA = K.ZZON_OID_ZONA
       AND M.OID_REGI = L.ZORG_OID_REGI
       AND NOT EXISTS (SELECT X.OID_BLOQ
                       FROM MAE_CLIEN_BLOQU X,
                            MAE_TIPO_BLOQU Y
                       WHERE X.CLIE_OID_CLIE = A.OID_CLIE
                        AND  Y.OID_TIPO_BLOQ = X.TIBQ_OID_TIPO_BLOQ
                        AND  X.fec_desb is null
                        AND (Y.COD_TIPO_BLOQ = '01' OR Y.COD_TIPO_BLOQ = '02')
                       )
  ORDER BY
    M.COD_REGI, L.COD_ZONA, K.COD_SECC, I.COD_TERR ;
  lnIdPais         SEG_PAIS.OID_PAIS%TYPE;
  lnIdCanal        SEG_CANAL.OID_CANA%TYPE;
  lnIdMarca        SEG_MARCA.OID_MARC%TYPE;
  lnIdPeriodo      CRA_PERIO.OID_PERI%TYPE;
BEGIN
  /* obteniendo id's */
  lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lnIdPeriodo    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);
  /* Obteniendo data para Region diferente de NULL */
  IF (TRIM(psCodRegion) IS NOT NULL) THEN
      OPEN cursorRegistro1(lnIdPais, lnIdPeriodo, psCodRegion, psCodZona);
      LOOP
           FETCH cursorRegistro1 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 PIPE ROW(tablaRegistro(x));
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro1%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro1;
      RETURN ;
  END IF;
  IF (TRIM(psCodRegion) IS NULL) THEN
      OPEN cursorRegistro2(lnIdPais, lnIdPeriodo, lnIdMarca, lnIdCanal);
      LOOP
           FETCH cursorRegistro2 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 PIPE ROW(tablaRegistro(x));
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro2%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro2;
      RETURN ;
  END IF;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_CONSU_EGRESA: '||ls_sqlerrm);
END VEN_FN_OBTIE_CONSU_EGRESA ;

/***************************************************************************
Descripcion       :  Obtiene el valor del cobro a 31 diaz , para 3 campañas anteriores
                    segun los parametros ingresados
Parametros        : psCodPeriodo Codigo del Periodo
                    psCodRegion Codigo de la Region
                    psCodZona Codigo de la Zona
                    psCodSeccion Codigo de la Seccion
                    psOidMarca Oid de la Marca
                    psOidCanal Oid de la Canal
                    psOidPais Oid de la Pais
Fecha Creacion    : 18/05/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_COBRO_31DIAS(
    psCodPeriodo VARCHAR2,
    psCodRegion VARCHAR2,
    psCodZona VARCHAR2,
    psCodSeccion VARCHAR2,
    psOidMarca NUMBER,
    psOidCanal NUMBER,
    psOidPais NUMBER
    )
    RETURN NUMBER  IS
ls_ImporteVenta NUMBER;
ls_ImporteSegundoTramo  NUMBER;
ls_ImportePrimerTramo  NUMBER;
BEGIN


  SELECT sum(com_recup_campa_secci.imp_vent),SUM(com_recup_campa_secci.imp_cobr_segu_tram), SUM(com_recup_campa_secci.imp_cobr_prim_tram)
  INTO   ls_ImporteVenta, ls_ImporteSegundoTramo, ls_ImportePrimerTramo
  FROM   com_recup_campa_secci
  WHERE  com_recup_campa_secci.cod_peri  = PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO
                                           (psCodPeriodo, psOidPais, psOidMarca, psOidCanal, -3)
         AND com_recup_campa_secci.cod_regi = psCodRegion
         AND com_recup_campa_secci.cod_zona = psCodZona
         AND com_recup_campa_secci.cod_secc = psCodSeccion;

  IF  ls_ImporteVenta <> 0 THEN
      RETURN (ls_ImporteSegundoTramo+ ls_ImportePrimerTramo)/ ls_ImporteVenta;
  ELSE
      RETURN 0;
  END IF;
 EXCEPTION
  WHEN no_data_found  THEN
     RETURN 0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_COBRO_31DIA: '||ls_sqlerrm);
END VEN_FN_OBTIE_COBRO_31DIAS;
/***************************************************************************
Descripcion       :  Obtiene el valor del cobro a 31 diaz por zona ,
                     para 3 campañas anteriores
                    segun los parametros ingresados
Parametros        : psCodPeriodo Codigo del Periodo
                    psCodRegion Codigo de la Region
                    psCodZona Codigo de la Zona
                    psCodMarca Oid de la Marca
                    psCodCanal Oid de la Canal
                    psCodPais Oid de la Pais
Fecha Creacion    : 18/05/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_COBRO_31DIAS_ZONA(
    psCodPeriodo VARCHAR2,
    psCodRegion VARCHAR2,
    psCodZona VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoPais VARCHAR2
    )
    RETURN NUMBER  IS
ls_ImporteVenta NUMBER;
ls_ImporteSegundoTramo  NUMBER;
ls_ImportePrimerTramo  NUMBER;
lnIdPais  NUMBER;
lnIdMarca NUMBER;
lnIdCanal NUMBER;
BEGIN

  lnIdPais     := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante
  lnIdMarca    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);-- id del Marca consultante
  lnIdCanal    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);-- id del Canal consultante

  SELECT sum(com_recup_campa_secci.imp_vent),SUM(com_recup_campa_secci.imp_cobr_segu_tram), SUM(com_recup_campa_secci.imp_cobr_prim_tram)
  INTO   ls_ImporteVenta, ls_ImporteSegundoTramo, ls_ImportePrimerTramo
  FROM   com_recup_campa_secci
  WHERE  com_recup_campa_secci.cod_peri  = PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO
                                           (psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -3)
         AND com_recup_campa_secci.cod_regi = psCodRegion
         AND com_recup_campa_secci.cod_zona = psCodZona ;
  IF  ls_ImporteVenta <> 0 THEN
      RETURN (ls_ImporteSegundoTramo+ ls_ImportePrimerTramo)/ ls_ImporteVenta;
  ELSE
      RETURN 0;
  END IF;
 EXCEPTION
  WHEN no_data_found  THEN
     RETURN 0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_COBRO_31DIAZ: '||ls_sqlerrm);
END VEN_FN_OBTIE_COBRO_31DIAS_ZONA;
/***************************************************************************
Descripcion       :  Obtiene el cuantos clientes han pasado productos
                     cuatro campañas seguidas a partir de una campaña
                     inicial y el codigo de territorio
                    segun los parametros ingresados
Parametros        : psCodPeriodo Codigo del Periodo
                    psCodTerritorio Codigo de la Territorio
                    psCodMarca Oid de la Marca
                    psCodCanal Oid de la Canal
                    psCodPais Oid de la Pais
Fecha Creacion    : 18/07/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_CLIEN_4CAMP_TERRI(
    psCodPeriodo VARCHAR2,
    psCodTerritorio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoPais VARCHAR2
    )
    RETURN NUMBER  IS
ls_Resultado  NUMBER;
lnIdPais  NUMBER;
lnIdMarca NUMBER;
lnIdCanal NUMBER;
lnPeriodo NUMBER;
lnPeriodoMenos1 NUMBER;
lnPeriodoMenos2 NUMBER;
lnPeriodoMenos3 NUMBER;
BEGIN

  lnIdPais     := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante
  lnIdMarca    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);-- id del Marca consultante
  lnIdCanal    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);-- id del Canal consultante
  lnPeriodo       := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo,  lnIdMarca, lnIdCanal);
  lnPeriodoMenos1 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal,-1),  lnIdMarca, lnIdCanal);
  lnPeriodoMenos2 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal,-2),  lnIdMarca, lnIdCanal);
  lnPeriodoMenos3 := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO (psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal,-3),  lnIdMarca, lnIdCanal);

  SELECT count(*)
  into ls_Resultado
  from (SELECT cliente.clie_oid_clie,
               COUNT(DISTINCT PERD_OID_PERI)
        FROM   PED_SOLIC_CABEC SC,
               PED_TIPO_SOLIC_PAIS TSP,
               PED_TIPO_SOLIC TS,
               (select MAE_CLIEN_HISTO_ESTAT.Clie_Oid_Clie  from  MAE_CLIEN_HISTO_ESTAT
                where PERD_OID_PERI  = lnPeriodoMenos3  --periodo-3
                and gen_pkg_gener.GEN_FN_CLIEN_DATOS_OID(Clie_Oid_Clie, 'COD_TERR')=psCodTerritorio
                and ESTA_OID_ESTA_CLIE = 2) cliente
        WHERE  SC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
               AND SC.CLIE_OID_CLIE = CLIENTE.CLIE_OID_CLIE
               AND TS.OID_TIPO_SOLI = TSP.TSOL_OID_TIPO_SOLI
               AND SC.FEC_FACT is not null
               AND (SC.PERD_OID_PERI  =lnPeriodo
                    or SC.PERD_OID_PERI  =lnPeriodoMenos1
                    or SC.PERD_OID_PERI  =lnPeriodoMenos2
                    or SC.PERD_OID_PERI  =lnPeriodoMenos3)
               AND TS.IND_ANUL = 0
               AND TS.IND_DEVO =0
               AND SC.IND_OC = 1
               AND sc.OID_SOLI_CABE not in (select oid_soli_cabe
                                from ped_solic_cabec
                               where ped_solic_cabec.oid_soli_cabe = SC.SOCA_OID_SOLI_CABE
                                     AND (perd_oid_peri =lnPeriodo OR
                                          perd_oid_peri =lnPeriodoMenos1 OR
                                          perd_oid_peri =lnPeriodoMenos2 OR
                                          perd_oid_peri =lnPeriodoMenos3)
                                     and FEC_FACT is not null
                                     and ESSO_OID_ESTA_SOLI <> 4
                             )
        GROUP BY  cliente.clie_oid_clie
        having COUNT(DISTINCT PERD_OID_PERI) >=4 ) ;
 RETURN ls_Resultado;

 EXCEPTION
  WHEN no_data_found  THEN
     RETURN 0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_CLIEN_4CAMP_TERRI: '||ls_sqlerrm);
END VEN_FN_OBTIE_CLIEN_4CAMP_TERRI;
/***************************************************************************
Descripcion       : Genera la data para el Reporte de Venta Variable
Fecha Creacion    : 22/01/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_OBTIE_VENTA_VARIA(
    psCodPais VARCHAR2,
    psCodSociedad VARCHAR2,
    psCodAlmacen VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psTipoPresentacion VARCHAR2,
    psTipoRango  VARCHAR2,
    psAnno VARCHAR2,
    psCodPeriodo VARCHAR2,
    psCodPeriodoFinal VARCHAR2,
    psTipoReporte VARCHAR2 -- 1 estadisticable (actual) , 2 venta catalogo (historico)
)
IS
  lnIdPais                    seg_pais.oid_pais%TYPE;
  lnIdCanal                   seg_canal.oid_cana%TYPE;
  lnIdMarca                   seg_marca.oid_marc%TYPE;
  lnIdSociedad                seg_socie.oid_soci%TYPE;
  lnIdAlmacen                 bel_almac.oid_alma%TYPE;
  lsCodPeri18AnnoAnte         seg_perio_corpo.cod_peri%TYPE;
  lsRangoAnterior             VARCHAR2(2);
  lsAnnoAnterior              VARCHAR2(4);
  lnIdPeriInicial             cra_perio.oid_peri%TYPE;
  lnIdPeriAnnoAnteInicial     cra_perio.oid_peri%TYPE;
  lnIdPeriFinal               cra_perio.oid_peri%TYPE;
  lnIdPeri18AnnoAnte          cra_perio.oid_peri%TYPE;
  lsCodPeriInicial            seg_perio_corpo.cod_peri%TYPE;
  lsCodPeriFinal              seg_perio_corpo.cod_peri%TYPE;
  lsCodPeriAnnoAnteInicial    seg_perio_corpo.cod_peri%TYPE;
  lsCodPeriAnnoAnteFinal      seg_perio_corpo.cod_peri%TYPE;
  lsCodPeriAnteriorInicial    seg_perio_corpo.cod_peri%TYPE;
  lsCodPeriAnteriorFinal      seg_perio_corpo.cod_peri%TYPE;
  lnNroCampana                NUMBER;
  lnUnidadDema                NUMBER;
  lnUnidadComp                NUMBER;
  lnUnidadFaltante            NUMBER;
  lnPrecioNeto                NUMBER;
  lnValor                     NUMBER;
  TYPE tRegVenta IS RECORD (
     INDI_TOTAL     VARCHAR2(1),
     COD_CONJ_AGRU  BAS_CONJU_AGRUP_GRUPO_ZONA.COD_CONJ_AGRU%TYPE,
     DES_CONJ_AGRU  BAS_CONJU_AGRUP_GRUPO_ZONA.DES_CONJ_AGRU%TYPE,
     NUM_ORDE_CONJ  BAS_CONJU_AGRUP_GRUPO_ZONA.NUM_ORDE%TYPE,
     COD_AGRU_GRUP  BAS_AGRUP_GRUPO_ZONA.COD_AGRU_GRUP%TYPE,
     DES_AGRU_GRUP  BAS_AGRUP_GRUPO_ZONA.DES_AGRU_GRUP%TYPE,
     NUM_ORDE       BAS_AGRUP_GRUPO_ZONA.NUM_ORDE%TYPE,
     OID_REGI       ZON_REGIO.OID_REGI%TYPE,
     COD_REGI       ZON_REGIO.COD_REGI%TYPE,
     DES_REGI       ZON_REGIO.DES_REGI%TYPE,
     COD_GRUPO      ven_grupo_zona.Cod_Grup%TYPE,
     DES_GRUPO      bas_grupo_zona.Des_Grup%TYPE,
     OID_ZONA       ZON_ZONA.OID_ZONA%TYPE,
     COD_ZONA       ZON_ZONA.COD_ZONA%TYPE,
     DES_ZONA       ZON_ZONA.DES_ZONA%TYPE,
     CLIE_OID_CLIE  ZON_ZONA.CLIE_OID_CLIE%TYPE
  );
  TYPE TABLA_VENTA IS TABLE OF tRegVenta ;
  TablaVenta   TABLA_VENTA;
  RegVenta     tRegVenta;
  RegReporte   VEN_REPOR_VENTA_VARIA%ROWTYPE; --tRegReporteVentaVariable;

  CURSOR cPeriodo(
     psCodPeriInicial VARCHAR2,
     psCodPeriFinal   VARCHAR2,
     pnIdPais  NUMBER,
     pnIdMarca NUMBER,
     pnIdCanal NUMBER
   )
  IS
  SELECT G.OID_PERI
  FROM
    TABLE(VEN_PKG_REPOR.VEN_FN_OBTIE_LISTA_OID_PERI(
        psCodPeriInicial,
        psCodPeriFinal,
        pnIdPais, pnIdMarca, pnIdCanal)) G;


  CURSOR cVenta(
    pnIdPais  NUMBER,
    pnIdMarca NUMBER,
    pnIdCanal NUMBER,
    psCodPeriInicial VARCHAR2,
    psCodPeriFinal   VARCHAR2,
    psCodPeriAnnoAnteInicial VARCHAR2,
    psCodPeriAnnoAnteFinal   VARCHAR2,
    psCodPeriAnteriorInicial VARCHAR2,
    psCodPeriAnteriorFinal   VARCHAR2
    )
  IS
  SELECT DISTINCT
     DECODE(SUBSTR(B.COD_REGI,1,1),'9','1','0') AS INDI_TOTAL,
     H.COD_CONJ_AGRU,
     H.DES_CONJ_AGRU,
     H.NUM_ORDE AS NUM_ORDE_CONJ,
     G.COD_AGRU_GRUP,
     G.DES_AGRU_GRUP,
     G.NUM_ORDE,
     B.OID_REGI,
     B.COD_REGI,
     B.DES_REGI,
     D.COD_GRUP AS COD_GRUPO,
     F.DES_GRUP AS DES_GRUPO,
     A.ZZON_OID_ZONA,
     C.COD_ZONA,
     C.DES_ZONA,
     C.CLIE_OID_CLIE
  FROM
     INT_FUENT_VENTAS_REAL A,
     ZON_REGIO B,
     ZON_ZONA  C,
     VEN_GRUPO_ZONA D,
     BAS_GRUPO_ZONA F,
     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
          psCodPeriInicial,
          psCodPeriFinal,
          psCodPeriAnnoAnteInicial,
          psCodPeriAnnoAnteFinal,
          psCodPeriAnteriorInicial,
          psCodPeriAnteriorFinal,
          pnIdPais, pnIdMarca, pnIdCanal)) E,
     BAS_AGRUP_GRUPO_ZONA G,
     BAS_CONJU_AGRUP_GRUPO_ZONA H

  WHERE A.PAIS_OID_PAIS = pnIdPais
    AND A.PERD_OID_PERI = E.OID_PERI
    AND A.ZORG_OID_REGI = B.OID_REGI
    AND A.ZZON_OID_ZONA = C.OID_ZONA
    AND B.MARC_OID_MARC = pnIdMarca
    AND B.CANA_OID_CANA = pnIdCanal
    AND C.PAIS_OID_PAIS = pnIdPais
    AND C.MARC_OID_MARC = pnIdMarca
    AND C.CANA_OID_CANA = pnIdCanal
    AND C.COD_ZONA = D.COD_ZONA
    AND F.COD_GRUP = D.COD_GRUP
    AND G.COD_AGRU_GRUP = F.COD_AGRU_GRUP
    AND H.COD_CONJ_AGRU = G.COD_CONJ_AGRU

  ORDER BY
     INDI_TOTAL, H.NUM_ORDE, G.NUM_ORDE, B.COD_REGI,
     D.COD_GRUP, C.COD_ZONA  ;
BEGIN

   /* Borrando tabla temporal de Venta Variable */
   DELETE FROM VEN_REPOR_VENTA_VARIA;

   /* obteniendos ids */
   lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
   lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
   lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
   lnIdSociedad  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_SOCIE(psCodSociedad);
   SELECT A.OID_ALMA
   INTO
      lnIdAlmacen
   FROM
      BEL_ALMAC A
   WHERE
      A.PAIS_OID_PAIS = lnIdPais
      AND A.COD_ALMA = psCodAlmacen;

   /* Encontrando los array de periodos */
   IF psTipoPresentacion = 'P' THEN
      lsCodPeriInicial := psCodPeriodo;
      lsCodPeriFinal   := psCodPeriodo;
      lsCodPeriAnnoAnteInicial := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -18);
      lsCodPeriAnnoAnteFinal   := lsCodPeriAnnoAnteInicial;
      lsCodPeriAnteriorInicial := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, -1);
      lsCodPeriAnteriorFinal   := lsCodPeriAnteriorInicial;
      lsCodPeri18AnnoAnte := trim(to_char(to_number(substr(psCodPeriodo,1,4) - 1))) || '18';

   ELSIF psTipoPresentacion = 'R' THEN
      VEN_PR_DEVUE_RANGO_CODI_PERI(psTipoRango, psAnno, lsCodPeriInicial, lsCodPeriFinal);
      lsCodPeri18AnnoAnte :=  trim(to_char(to_number(substr(lsCodPeriInicial,1,4) - 1))) || '18';
      lsCodPeriAnnoAnteInicial := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCodPeriInicial, lnIdPais, lnIdMarca, lnIdCanal, -18);
      lsCodPeriAnnoAnteFinal   := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCodPeriFinal, lnIdPais, lnIdMarca, lnIdCanal, -18);
      IF psTipoRango = '01' THEN
         lsRangoAnterior := '03';
         lsAnnoAnterior  := trim(to_char(to_number(psAnno) - 1));
      ELSE
         lsRangoAnterior := trim(to_char(to_number(psTipoRango) - 1, '00'));
         lsAnnoAnterior  := psAnno;
      END IF;
      VEN_PR_DEVUE_RANGO_CODI_PERI(lsRangoAnterior, lsAnnoAnterior, lsCodPeriAnteriorInicial, lsCodPeriAnteriorFinal);

   ELSIF psTipoPresentacion = 'A' THEN
      lsCodPeriInicial := trim(substr(psCodPeriodoFinal, 1, 4)) || '01';
      lsCodPeriFinal   := psCodPeriodoFinal;
      lsCodPeri18AnnoAnte := trim(to_char(to_number(substr(lsCodPeriInicial,1,4) - 1))) || '18';
      lsCodPeriAnnoAnteInicial := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCodPeriInicial, lnIdPais, lnIdMarca, lnIdCanal, -18);
      lsCodPeriAnnoAnteFinal   := per_pkg_repor_perce.PER_FN_OBTIE_PERIO(lsCodPeriFinal, lnIdPais, lnIdMarca, lnIdCanal, -18);
      lsCodPeriAnteriorInicial := NULL;
      lsCodPeriAnteriorFinal   := NULL;
   END IF;

   /* obteniendo ids periodos */
   lnIdPeriInicial := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriInicial, lnIdMarca, lnIdCanal);
   lnIdPeriFinal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriFinal, lnIdMarca, lnIdCanal);
   lnIdPeriAnnoAnteInicial := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriAnnoAnteInicial, lnIdMarca, lnIdCanal);
   lnIdPeri18AnnoAnte := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeri18AnnoAnte, lnIdMarca, lnIdCanal);

   /* obteniendo nro de campa?as */
   lnNroCampana := VEN_FN_DEVUE_NUME_CAMPA(lsCodPeriInicial, lsCodPeriFinal, lnIdPais, lnIdMarca, lnIdCanal);

   /* obteniendo informacion para el monto faltante */
   --DELETE FROM VEN_GTT_SOLIC_CABEC;
   --DELETE FROM VEN_GTT_MONTO_FALTA;
   EXECUTE IMMEDIATE 'TRUNCATE TABLE VEN_GTT_SOLIC_CABEC';
   EXECUTE IMMEDIATE 'TRUNCATE TABLE VEN_GTT_MONTO_FALTA';

   FOR curPeri IN cPeriodo (lsCodPeriInicial, lsCodPeriFinal, lnIdPais, lnIdMarca, lnIdCanal)  LOOP
       INSERT INTO VEN_GTT_SOLIC_CABEC (
          OID_SOLI_CABE, FEC_PROG_FACT,
          FEC_FACT, NUM_CLIEN,
          VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
          MONE_OID_MONE, TIDS_OID_TIPO_DESP,
          ALMC_OID_ALMA, MODU_OID_MODU,
          TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
          PERD_OID_PERI, SOCA_OID_SOLI_CABE,
          CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
          CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
          CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
          SOCI_OID_SOCI, SBAC_OID_SBAC,
          TERR_OID_TERR, ZZON_OID_ZONA,
          IND_ESTA, IND_IMPR,
          IND_EXEN_FLET, VAL_NUME_SOLI,
          VAL_USUA, VAL_TASA_IMPU,
          FEC_CRON, IND_PERM_UNIO_SOL,
          IND_GENE_CC, IND_APLI_MANU,
          VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
          NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
          FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
          VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
          VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
          VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
          VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
          VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
          VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
          VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
          VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
          VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
          VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
          VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
          VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
          VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
          VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
          VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
          VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
          VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
          VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
          VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
          VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
          VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
          NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
          IND_OC, IND_PEDI_PRUE,
          IND_TS_NO_CONSO, VAL_GLOS_OBSE,
          VAL_OBSE_REVI, NUM_PREM,
          VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
          PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
          VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
          ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
          GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
          ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
          FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
          ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
          ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
          OPER_OID_OPER, PROC_OID_PROC,
          SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
          CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
          NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
          IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
          )
       SELECT
          OID_SOLI_CABE, FEC_PROG_FACT,
          FEC_FACT, NUM_CLIEN,
          VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
          MONE_OID_MONE, TIDS_OID_TIPO_DESP,
          ALMC_OID_ALMA, MODU_OID_MODU,
          TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
          PERD_OID_PERI, SOCA_OID_SOLI_CABE,
          CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
          CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
          CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
          SOCI_OID_SOCI, SBAC_OID_SBAC,
          TERR_OID_TERR, ZZON_OID_ZONA,
          IND_ESTA, IND_IMPR,
          IND_EXEN_FLET, VAL_NUME_SOLI,
          VAL_USUA, VAL_TASA_IMPU,
          FEC_CRON, IND_PERM_UNIO_SOL,
          IND_GENE_CC, IND_APLI_MANU,
          VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
          NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
          FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
          VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
          VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
          VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
          VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
          VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
          VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
          VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
          VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
          VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
          VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
          VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
          VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
          VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
          VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
          VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
          VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
          VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
          VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
          VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
          VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
          VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
          NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
          IND_OC, IND_PEDI_PRUE,
          IND_TS_NO_CONSO, VAL_GLOS_OBSE,
          VAL_OBSE_REVI, NUM_PREM,
          VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
          PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
          VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
          ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
          GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
          ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
          FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
          ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
          ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
          OPER_OID_OPER, PROC_OID_PROC,
          SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
          CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
          NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
          IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
       FROM
            PED_SOLIC_CABEC A
         WHERE  A.PERD_OID_PERI = curPeri.OID_PERI
            AND A.FEC_FACT IS NOT NULL
            AND A.IND_TS_NO_CONSO = 1
            AND A.IND_OC = 1
            AND A.IND_PEDI_PRUE = 0
            AND A.PAIS_OID_PAIS = lnIdPais
            AND A.SOCI_OID_SOCI = lnIdSociedad
            AND A.ALMC_OID_ALMA = lnIdAlmacen;
   END LOOP;
-- generando estadistico
   DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => USER, TABNAME => 'VEN_GTT_SOLIC_CABEC', CASCADE => TRUE );
   /*
  IF(psTipoReporte = '1') THEN
   INSERT INTO VEN_GTT_MONTO_FALTA (
      ZZON_OID_ZONA,
      IMP_MONTO_FALTA)
   SELECT
       A.ZZON_OID_ZONA,
       sum((D.NUM_UNID_DEMA_REAL - D.NUM_UNID_COMPR) * D.VAL_PREC_NETO_UNIT_LOCA)
     FROM
        VEN_GTT_SOLIC_CABEC A,
        PED_TIPO_SOLIC_PAIS B,
        PED_TIPO_SOLIC  C,
        PED_SOLIC_POSIC D ,
        PRE_OFERT_DETAL S,
        PRE_TIPO_OFERT  W
     WHERE  A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
        AND A.OID_SOLI_CABE = D.SOCA_OID_SOLI_CABE

        AND C.MARC_OID_MARC = lnIdMarca
        AND C.IND_DEVO = 0
        AND C.IND_ANUL = 0
        AND C.OID_TIPO_SOLI = B.TSOL_OID_TIPO_SOLI
        AND W.IND_ESTA = 1
        AND S.OID_DETA_OFER = D.OFDE_OID_DETA_OFER
        AND W.OID_TIPO_OFER = S.TOFE_OID_TIPO_OFER
   GROUP BY  A.ZZON_OID_ZONA;
  ELSE

   INSERT INTO VEN_GTT_MONTO_FALTA (
      ZZON_OID_ZONA,
      IMP_MONTO_FALTA)
   SELECT
       A.ZZON_OID_ZONA,
       sum((D.NUM_UNID_DEMA_REAL - D.NUM_UNID_COMPR) * D.VAL_PREC_NETO_UNIT_LOCA)
     FROM
        VEN_GTT_SOLIC_CABEC A,
        PED_TIPO_SOLIC_PAIS B,
        PED_TIPO_SOLIC  C,
        PED_SOLIC_POSIC D ,
        PRE_OFERT_DETAL S,
        PRE_TIPO_OFERT  W
     WHERE  A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
        AND A.OID_SOLI_CABE = D.SOCA_OID_SOLI_CABE

        AND C.MARC_OID_MARC = lnIdMarca
        AND C.IND_DEVO = 0
        AND C.IND_ANUL = 0
        AND C.OID_TIPO_SOLI = B.TSOL_OID_TIPO_SOLI
        AND W.VAL_FORM_VENT = 1
        AND S.OID_DETA_OFER = D.OFDE_OID_DETA_OFER
        AND W.OID_TIPO_OFER = S.TOFE_OID_TIPO_OFER
   GROUP BY  A.ZZON_OID_ZONA;

  END IF;
  */
     --genrando estadisticos
   --DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => USER, TABNAME => 'VEN_GTT_MONTO_FALTA', CASCADE => TRUE );

   /* Generando informacion */
   OPEN cVenta(
    lnIdPais, lnIdMarca, lnIdCanal,
    lsCodPeriInicial, lsCodPeriFinal,
    lsCodPeriAnnoAnteInicial, lsCodPeriAnnoAnteFinal,
    lsCodPeriAnteriorInicial, lsCodPeriAnteriorFinal );
   LOOP
     FETCH cVenta BULK COLLECT INTO tablaVenta LIMIT W_FILAS;
     IF tablaVenta.COUNT > 0 THEN
         FOR x IN tablaVenta.FIRST .. tablaVenta.LAST LOOP
             RegVenta := tablaVenta(x);
             RegReporte.INDI_TOTAL := RegVenta.INDI_TOTAL;
             RegReporte.OID_REGI   := RegVenta.OID_REGI;
             RegReporte.COD_REGI   := RegVenta.COD_REGI;
             RegReporte.DES_REGI   := RegVenta.DES_REGI;
             RegReporte.COD_GRUPO  := RegVenta.COD_GRUPO;
             RegReporte.DES_GRUPO  := RegVenta.DES_GRUPO;
             RegReporte.OID_ZONA   := RegVenta.OID_ZONA;
             RegReporte.COD_ZONA   := RegVenta.COD_ZONA;
             RegReporte.DES_ZONA   := RegVenta.DES_ZONA;
             RegReporte.CLIE_OID_CLIE  := RegVenta.CLIE_OID_CLIE;
             RegReporte.NUM_CAMP   := lnNroCampana;
             RegReporte.Cod_Agru_Grup := RegVenta.COD_AGRU_GRUP;
             RegReporte.Des_Agru_Grup := RegVenta.Des_AGRU_GRUP;
             RegReporte.Num_Orde := RegVenta.Num_Orde;

             RegReporte.Cod_Conj_Agru := RegVenta.COD_CONJ_AGRU;
             RegReporte.Des_Conj_Agru := RegVenta.DES_CONJ_AGRU;
             RegReporte.Num_Orde_Conj := RegVenta.NUM_ORDE_CONJ;

             /* Encontrando Descripcion del cliente */
             BEGIN
               SELECT
                 COD_CLIE,
                 TRIM(NVL(A.VAL_NOM1,' ') ||' '|| NVL(A.VAL_APE1,' ') ||' '|| NVL(A.VAL_APE2,' '))
               INTO
                  RegReporte.COD_CLIE,
                  RegReporte.DES_CLIE
               FROM MAE_CLIEN A
               WHERE
                  A.OID_CLIE = RegReporte.CLIE_OID_CLIE ;
             EXCEPTION
             WHEN NO_DATA_FOUND THEN
                  RegReporte.DES_CLIE := 'IMPULSADORA';
             END ;
             IF RegReporte.DES_CLIE IS NULL THEN
                RegReporte.DES_CLIE := 'IMPULSADORA';
             END IF;

             /* Venta Estimada, Ingresos Estimados, Reingresos estimados,
                Egresos Estimados, Pedidos estimados, Unidades Estimadas */
             BEGIN
               SELECT
                   nvl(SUM(A.VAL_VENT_NETA_ESTA),0.0),
                   nvl(SUM(A.NUM_INGR),0.0),
                   nvl(SUM(A.NUM_REIN),0.0),
                   nvl(SUM(A.NUM_EGRE),0.0),
                   nvl(SUM(A.NUM_PEDI),0),
                   nvl(SUM(A.NUM_UNID_VEND),0)
               INTO
                   RegReporte.VEN_ESTI_ACTU,
                   RegReporte.NUM_INGR_ESTI,
                   RegReporte.NUM_REIN_ESTI,
                   RegReporte.NUM_EGRE_ESTI,
                   RegReporte.NUM_PEDI_ESTI,
                   RegReporte.NUM_UNID_ESTI
               FROM
                   INT_FUENT_VENTA_PREVI_SAP A,
                   TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                      lsCodPeriInicial,
                      lsCodPeriFinal,
                      lnIdPais, LnIdMarca, LnIdCanal)) B
               WHERE
                       A.ALMC_OID_ALMA = lnIdAlmacen
                   AND A.PERD_OID_PERI = B.OID_PERI
                   AND A.SOCI_OID_SOCI = lnIdSociedad
                   AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
             EXCEPTION
             WHEN no_data_found THEN
                   RegReporte.VEN_ESTI_ACTU := 0.0;
                   RegReporte.NUM_INGR_ESTI := 0;
                   RegReporte.NUM_REIN_ESTI := 0;
                   RegReporte.NUM_EGRE_ESTI := 0;
                   RegReporte.NUM_PEDI_ESTI := 0;
                   RegReporte.NUM_UNID_ESTI := 0;
             END;

             /* Venta Real, Pedidos, Unidades */
             IF psTipoReporte = '1' THEN
               BEGIN
                 SELECT
                     nvl(SUM(A.IMP_VENT_NETA_ESTA),0.0),
                     nvl(SUM(A.NUM_PEDI),0),
                     nvl(SUM(A.NUM_UNID_VEND),0)
                 INTO
                     RegReporte.VEN_REAL_ACTU,
                     RegReporte.NUM_PEDI,
                     RegReporte.NUM_UNID
                 FROM
                     INT_FUENT_VENTA_REAL_VACUM A,
                     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                        lsCodPeriInicial,
                        lsCodPeriFinal,
                        lnIdPais, LnIdMarca, LnIdCanal)) B
                 WHERE   A.PERD_OID_PERI = B.OID_PERI
                     AND A.ALMC_OID_ALMA = lnIdAlmacen
                     AND A.SOCI_OID_SOCI = lnIdSociedad
                     AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
               EXCEPTION
               WHEN no_data_found THEN
                    RegReporte.VEN_REAL_ACTU := 0.0;
                    RegReporte.NUM_PEDI := 0;
                    RegReporte.NUM_UNID := 0;
               END;
             ELSE
               BEGIN
                 SELECT
                     nvl(SUM(A.MON_NETO),0.0),
                     nvl(SUM(A.NUM_UNID_VEN),0)
                 INTO
                     RegReporte.VEN_REAL_ACTU,
                     RegReporte.NUM_UNID
                 FROM
                     VEN_HISTO_VENTA_CATAL A,
                     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                        lsCodPeriInicial,
                        lsCodPeriFinal,
                        lnIdPais, LnIdMarca, LnIdCanal)) B
                 WHERE  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( A.COD_CAMP,LnIdMarca,LnIdCanal) = B.OID_PERI
                     AND A.COD_ALMA = psCodAlmacen
                     AND A.OID_ZONA = RegReporte.OID_ZONA;
               EXCEPTION
               WHEN no_data_found THEN
                    RegReporte.VEN_REAL_ACTU := 0.0;
                    RegReporte.NUM_UNID := 0;
               END;
      --recuperamos el num de pedido cuando es catalogo
              BEGIN
                 SELECT
                     nvl(SUM(A.NUM_PEDI),0)
                 INTO
                     RegReporte.NUM_PEDI
                 FROM
                     INT_FUENT_VENTA_REAL_VACUM A,
                     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                        lsCodPeriInicial,
                        lsCodPeriFinal,
                        lnIdPais, LnIdMarca, LnIdCanal)) B
                 WHERE   A.PERD_OID_PERI = B.OID_PERI
                     AND A.ALMC_OID_ALMA = lnIdAlmacen
                     AND A.SOCI_OID_SOCI = lnIdSociedad
                     AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
               EXCEPTION
               WHEN no_data_found THEN
                    RegReporte.NUM_PEDI := 0;
               END;

             END IF;

             /* % Venta Estimada */
             RegReporte.POR_VENTA_ESTI := 0.0;
             IF RegReporte.VEN_ESTI_ACTU <> 0.0 THEN
                RegReporte.POR_VENTA_ESTI := ((RegReporte.VEN_REAL_ACTU - RegReporte.VEN_ESTI_ACTU) / RegReporte.VEN_ESTI_ACTU) * 100;
             END IF;

             /* Venta Real A?o Anterior, Pedidos A?o Anterior, Unidades A?o anterior */
             IF psTipoReporte = '1' THEN
               BEGIN
                 SELECT
                     nvl(SUM(A.IMP_VENT_NETA_ESTA),0.0),
                     nvl(SUM(A.NUM_PEDI),0),
                     nvl(SUM(A.NUM_UNID_VEND),0)
                 INTO
                     RegReporte.VEN_REAL_ANNO_ANTE,
                     RegReporte.NUM_PEDI_ANNO_ANTE,
                     RegReporte.NUM_UNID_ANNO_ANTE
                 FROM
                     INT_FUENT_VENTA_REAL_VACUM A,
                     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                        lsCodPeriAnnoAnteInicial,
                        lsCodPeriAnnoAnteFinal,
                        lnIdPais, LnIdMarca, LnIdCanal)) B
                 WHERE   A.PERD_OID_PERI = B.OID_PERI
                     AND A.ALMC_OID_ALMA = lnIdAlmacen
                     AND A.SOCI_OID_SOCI = lnIdSociedad
                     AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
               EXCEPTION
               WHEN no_data_found THEN
                    RegReporte.VEN_REAL_ANNO_ANTE := 0.0;
                    RegReporte.NUM_PEDI_ANNO_ANTE := 0;
                    RegReporte.NUM_UNID_ANNO_ANTE := 0;
               END;


             ELSE
               BEGIN
                 SELECT
                     nvl(SUM(A.MON_NETO),0.0),
                     nvl(SUM(A.NUM_UNID_VEN),0)
                 INTO
                     RegReporte.VEN_REAL_ANNO_ANTE,
                     RegReporte.NUM_UNID_ANNO_ANTE
                 FROM
                     VEN_HISTO_VENTA_CATAL A,
                     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                        lsCodPeriAnnoAnteInicial,
                        lsCodPeriAnnoAnteFinal,
                        lnIdPais, LnIdMarca, LnIdCanal)) B
                 WHERE   GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( A.COD_CAMP,LnIdMarca,LnIdCanal) = B.OID_PERI
                     AND A.COD_ALMA = psCodAlmacen
                     AND A.OID_ZONA = RegReporte.OID_ZONA;
               EXCEPTION
               WHEN no_data_found THEN
                    RegReporte.VEN_REAL_ANNO_ANTE := 0.0;
                    RegReporte.NUM_UNID_ANNO_ANTE := 0;
               END;
               --recuperamos los pedidos
      BEGIN
                 SELECT
                     nvl(SUM(A.NUM_PEDI),0)
                 INTO
                     RegReporte.NUM_PEDI_ANNO_ANTE
                 FROM
                     INT_FUENT_VENTA_REAL_VACUM A,
                     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                        lsCodPeriAnnoAnteInicial,
                        lsCodPeriAnnoAnteFinal,
                        lnIdPais, LnIdMarca, LnIdCanal)) B
                 WHERE   A.PERD_OID_PERI = B.OID_PERI
                     AND A.ALMC_OID_ALMA = lnIdAlmacen
                     AND A.SOCI_OID_SOCI = lnIdSociedad
                     AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
               EXCEPTION
               WHEN no_data_found THEN
                    RegReporte.NUM_PEDI_ANNO_ANTE := 0;
               END;

             END IF;

             /* % Venta A?o Anterior */
             RegReporte.POR_VENTA_ANNO_ANTE := 0.0;
             IF RegReporte.VEN_REAL_ANNO_ANTE <> 0.0 THEN
                RegReporte.POR_VENTA_ANNO_ANTE := ((RegReporte.VEN_REAL_ACTU - RegReporte.VEN_REAL_ANNO_ANTE) / RegReporte.VEN_REAL_ANNO_ANTE) * 100;
             END IF;

             IF psTipoPresentacion = 'P' OR psTipoPresentacion = 'R' THEN

                /* Venta Real Rango Anterior, Pedidos Campa?a anterior  */
      IF psTipoReporte = '1' THEN
                BEGIN
                 SELECT
                     nvl(SUM(A.IMP_VENT_NETA_ESTA),0.0),
                     nvl(SUM(A.NUM_PEDI),0.0)
                 INTO
                     RegReporte.VEN_REAL_ANTE,
                     RegReporte.NUM_PEDI_ANTE
                 FROM
                     INT_FUENT_VENTA_REAL_VACUM A,
                     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                        lsCodPeriAnteriorInicial,
                        lsCodPeriAnteriorFinal,
                        lnIdPais, LnIdMarca, LnIdCanal)) B
                 WHERE   A.PERD_OID_PERI = B.OID_PERI
                     AND A.ALMC_OID_ALMA = lnIdAlmacen
                     AND A.SOCI_OID_SOCI = lnIdSociedad
                     AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
               EXCEPTION
               WHEN no_data_found THEN
                     RegReporte.VEN_REAL_ANTE := 0.0;
                     RegReporte.NUM_PEDI_ANTE := 0;
               END;
      ELSE
       BEGIN
                 SELECT
                     nvl(SUM(A.MON_NETO),0.0)
                 INTO
                     RegReporte.VEN_REAL_ANTE
                 FROM
                     VEN_HISTO_VENTA_CATAL A,
                     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                        lsCodPeriAnteriorInicial,
                        lsCodPeriAnteriorFinal,
                        lnIdPais, LnIdMarca, LnIdCanal)) B
                 WHERE   GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( A.COD_CAMP,LnIdMarca,LnIdCanal) = B.OID_PERI
                     AND A.COD_ALMA = psCodAlmacen
                     AND A.OID_ZONA = RegReporte.OID_ZONA;
                 EXCEPTION
                 WHEN no_data_found THEN
                    RegReporte.VEN_REAL_ANTE := 0.0;
               END;
      --recuperamos el num pedido
               BEGIN
                 SELECT
                     nvl(SUM(A.NUM_PEDI),0.0)
                 INTO
                     RegReporte.NUM_PEDI_ANTE
                 FROM
                     INT_FUENT_VENTA_REAL_VACUM A,
                     TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                        lsCodPeriAnteriorInicial,
                        lsCodPeriAnteriorFinal,
                        lnIdPais, LnIdMarca, LnIdCanal)) B
                 WHERE   A.PERD_OID_PERI = B.OID_PERI
                     AND A.ALMC_OID_ALMA = lnIdAlmacen
                     AND A.SOCI_OID_SOCI = lnIdSociedad
                     AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
               EXCEPTION
               WHEN no_data_found THEN
                     RegReporte.NUM_PEDI_ANTE := 0;
               END;


      END IF;


               /* % Venta Campa?a anterior */
               RegReporte.POR_VENTA_ANTE := 0.0;
               IF RegReporte.VEN_REAL_ANTE <> 0.0 THEN
                  RegReporte.POR_VENTA_ANTE := ((RegReporte.VEN_REAL_ACTU - RegReporte.VEN_REAL_ANTE) / RegReporte.VEN_REAL_ANTE) * 100;
               END IF;
             END IF;

             /* Monto Faltante */
             /*BEGIN
                 SELECT A.IMP_MONTO_FALTA
                 INTO
                    RegReporte.IMP_MONTO_FALTA
                 FROM
                    VEN_GTT_MONTO_FALTA A
                 WHERE  A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
             EXCEPTION
             WHEN no_Data_found THEN
                 RegReporte.IMP_MONTO_FALTA := 0;
             END ;*/
         BEGIN
        SELECT
          sum((D.NUM_UNID_DEMA_REAL - D.NUM_UNID_COMPR) * D.VAL_PREC_NETO_UNIT_LOCA)
         INTO
          RegReporte.IMP_MONTO_FALTA
        FROM
           VEN_GTT_SOLIC_CABEC A,
           PED_TIPO_SOLIC_PAIS B,
           PED_TIPO_SOLIC  C,
           PED_SOLIC_POSIC D ,
           PRE_OFERT_DETAL S,
           PRE_TIPO_OFERT  W
        WHERE  A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
           AND A.OID_SOLI_CABE = D.SOCA_OID_SOLI_CABE
           AND C.MARC_OID_MARC = lnIdMarca
           AND C.IND_DEVO = 0
           AND C.IND_ANUL = 0
           AND C.OID_TIPO_SOLI = B.TSOL_OID_TIPO_SOLI
           AND ((psTipoReporte = '1' AND W.IND_ESTA = 1) OR (psTipoReporte = '2' AND W.VAL_FORM_VENT=1))
           AND S.OID_DETA_OFER = D.OFDE_OID_DETA_OFER
           AND W.OID_TIPO_OFER = S.TOFE_OID_TIPO_OFER
           AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA
        GROUP BY  A.ZZON_OID_ZONA;
       EXCEPTION
             WHEN OTHERS THEN
                 RegReporte.IMP_MONTO_FALTA := 0;
             END ;

             /* % Monto Faltante */
             IF (RegReporte.IMP_MONTO_FALTA + RegReporte.VEN_REAL_ACTU <> 0) THEN
                 RegReporte.POR_MONTO_FALTA := (RegReporte.IMP_MONTO_FALTA * 100) /  (RegReporte.IMP_MONTO_FALTA + RegReporte.VEN_REAL_ACTU);
             END IF;

             /* Activos Iniciales */
             BEGIN
               SELECT
                   nvl(SUM(A.NUM_ACTI_INIC),0)
               INTO
                   RegReporte.NUM_ACTI_INIC
               FROM
                   INT_FUENT_VENTAS_REAL A
               WHERE   A.PAIS_OID_PAIS = lnIdPais
                   AND A.PERD_OID_PERI = lnIdPeriInicial
                   AND A.ZORG_OID_REGI = RegReporte.OID_REGI
                   AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
             EXCEPTION
             WHEN no_data_found THEN
                  RegReporte.NUM_ACTI_INIC := 0;
             END;

             /* Activos Iniciales ESTIMADAS */
             BEGIN
               SELECT
                   nvl(SUM(A.NUM_ACTI_INIC),0)
               INTO
                   RegReporte.NUM_ACTI_INIC_ESTI
               FROM
                   INT_FUENT_VENTA_PREVI_SAP A
               WHERE   A.ALMC_OID_ALMA = lnIdAlmacen
                   AND A.PERD_OID_PERI = lnIdPeriInicial
                   AND A.SOCI_OID_SOCI = lnIdSociedad
                   AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
             EXCEPTION
             WHEN no_data_found THEN
                  RegReporte.NUM_ACTI_INIC_ESTI := 0;
             END;

             /* Ingresos, Reingresos, Egresos  */
             BEGIN
               SELECT
                   nvl(SUM(A.NUM_INGR),0.0),
                   nvl(SUM(A.NUM_REIN),0.0),
                   nvl(SUM(A.NUM_EGRE),0.0)
               INTO
                   RegReporte.NUM_INGR,
                   RegReporte.NUM_REIN,
                   RegReporte.NUM_EGRE
               FROM
                   INT_FUENT_VENTAS_REAL A,
                   TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                      lsCodPeriInicial,
                      lsCodPeriFinal,
                      lnIdPais, LnIdMarca, LnIdCanal)) B
               WHERE   A.PAIS_OID_PAIS = lnIdPais
                   AND A.PERD_OID_PERI = B.OID_PERI
                   AND A.ZORG_OID_REGI = RegReporte.OID_REGI
                   AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
             EXCEPTION
             WHEN no_data_found THEN
                  RegReporte.NUM_INGR := 0;
                  RegReporte.NUM_REIN := 0;
                  RegReporte.NUM_EGRE := 0;
             END;

             /* Ingresos A?o anterior, Reingresos A?o anterior, Egresos A?o anterior */
             BEGIN
               SELECT
                   nvl(SUM(A.NUM_INGR),0.0),
                   nvl(SUM(A.NUM_REIN),0.0),
                   nvl(SUM(A.NUM_EGRE),0.0)
               INTO
                   RegReporte.NUM_INGR_ANNO_ANTE,
                   RegReporte.NUM_REIN_ANNO_ANTE,
                   RegReporte.NUM_EGRE_ANNO_ANTE
               FROM
                   INT_FUENT_VENTAS_REAL A,
                   TABLE(VEN_FN_OBTIE_LISTA_OID_PERI(
                      lsCodPeriAnnoAnteInicial,
                      lsCodPeriAnnoAnteFinal,
                      lnIdPais, LnIdMarca, LnIdCanal)) B
               WHERE  A.PAIS_OID_PAIS = lnIdPais
                   AND A.PERD_OID_PERI = B.OID_PERI
                   AND A.ZORG_OID_REGI = RegReporte.OID_REGI
                   AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
             EXCEPTION
             WHEN no_data_found THEN
                  RegReporte.NUM_INGR_ANNO_ANTE := 0;
                  RegReporte.NUM_REIN_ANNO_ANTE := 0;
                  RegReporte.NUM_EGRE_ANNO_ANTE := 0;
             END;

             /* Promedio ingresos, Promedio ingresos estimados, Promedio ingresos A?o anterior */
             IF RegReporte.NUM_CAMP <> 0 THEN
                 RegReporte.PRO_INGR := RegReporte.NUM_INGR / RegReporte.NUM_CAMP;
                 RegReporte.PRO_INGR_ESTI := RegReporte.NUM_INGR_ESTI / RegReporte.NUM_CAMP;
                 RegReporte.PRO_INGR_ANNO_ANTE := RegReporte.NUM_INGR_ANNO_ANTE / RegReporte.NUM_CAMP;
             ELSE
                 RegReporte.PRO_INGR := 0;
                 RegReporte.PRO_INGR_ESTI := 0;
                 RegReporte.PRO_INGR_ANNO_ANTE := 0;
             END IF;

             /* Porcentaje Egresos */
             IF RegReporte.NUM_ACTI_INIC <> 0 THEN
                RegReporte.POR_EGRE := (RegReporte.NUM_EGRE * 100) / RegReporte.NUM_ACTI_INIC;
             ELSE
                RegReporte.POR_EGRE := 0;
             END IF;

             /* Porcentaje Egresos Estimados */
             IF RegReporte.NUM_ACTI_INIC_ESTI <> 0 THEN
                RegReporte.POR_EGRE_ESTI := (RegReporte.NUM_EGRE_ESTI * 100) / RegReporte.NUM_ACTI_INIC_ESTI;
             ELSE
                RegReporte.POR_EGRE_ESTI := 0;
             END IF;

             /* Activa Inicial A?o Anterior */
             BEGIN
               SELECT
                   nvl(SUM(A.NUM_ACTI_INIC),0)
               INTO
                   RegReporte.NUM_ACTI_INIC_ANNO_ANTE
               FROM
                   INT_FUENT_VENTAS_REAL A
               WHERE A.PAIS_OID_PAIS = lnIdPais
                   AND A.PERD_OID_PERI = lnIdPeriAnnoAnteInicial
                   AND A.ZORG_OID_REGI = RegReporte.OID_REGI
                   AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA ;
             EXCEPTION
             WHEN no_data_found THEN
                  RegReporte.NUM_ACTI_INIC_ANNO_ANTE := 0;
             END;

             /* Porcentaje Egresos A?o anterior */
             IF RegReporte.NUM_ACTI_INIC_ANNO_ANTE <> 0 THEN
                RegReporte.POR_EGRE_ANNO_ANTE := (RegReporte.NUM_EGRE_ANNO_ANTE * 100) / RegReporte.NUM_ACTI_INIC_ANNO_ANTE;
             ELSE
                RegReporte.POR_EGRE_ANNO_ANTE := 0;
             END IF;

             /* Estimados activas */
             BEGIN
               SELECT
                   nvl(SUM(A.NUM_ACTI_FINA),0)
               INTO
                   RegReporte.NUM_ESTI_ACTI
               FROM
                   INT_FUENT_VENTA_PREVI_SAP A
               WHERE   A.ALMC_OID_ALMA = lnIdAlmacen
                   AND A.PERD_OID_PERI = lnIdPeriFinal
                   AND A.SOCI_OID_SOCI = lnIdSociedad
                   AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
             EXCEPTION
             WHEN no_data_found THEN
                  RegReporte.NUM_ESTI_ACTI := 0;
             END;

             /* Activas finales */
             BEGIN
               SELECT
                   nvl(SUM(A.NUM_ACTI_FINA),0.0)
               INTO
                   RegReporte.NUM_ACTI_FINA
               FROM
                   INT_FUENT_VENTAS_REAL A
               WHERE  A.PAIS_OID_PAIS = lnIdPais
                   AND A.PERD_OID_PERI = lnIdPeriFinal
                   AND A.ZORG_OID_REGI = RegReporte.OID_REGI
                   AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
             EXCEPTION
             WHEN no_data_found THEN
                  RegReporte.NUM_ACTI_FINA := 0;
             END;

             /* Diferencia Activas */
             RegReporte.NUM_DIFE_ACTI := RegReporte.NUM_ACTI_FINA - RegReporte.NUM_ESTI_ACTI;

             /* Activas Campa?a 18 a?os anterior */
             BEGIN
               SELECT
                   nvl(SUM(A.NUM_ACTI_FINA),0.0)
               INTO
                   RegReporte.NUM_ACTI_18_ANNO_ANTE
               FROM
                   INT_FUENT_VENTAS_REAL A
               WHERE  A.PAIS_OID_PAIS = lnIdPais
                   AND A.PERD_OID_PERI = lnIdPeri18AnnoAnte
                   AND A.ZORG_OID_REGI = RegReporte.OID_REGI
                   AND A.ZZON_OID_ZONA = RegReporte.OID_ZONA;
             EXCEPTION
             WHEN no_data_found THEN
                  RegReporte.NUM_ACTI_18_ANNO_ANTE := 0;
             END;

             /* porcentaje retencion */
             IF RegReporte.NUM_ACTI_18_ANNO_ANTE <> 0 THEN
                RegReporte.POR_RETE := (RegReporte.NUM_ACTI_FINA * 100) / RegReporte.NUM_ACTI_18_ANNO_ANTE;
             ELSE
                RegReporte.POR_RETE := 0;
             END IF;

             /* Capitalizacion */
             RegReporte.IMP_CAPI := RegReporte.NUM_INGR + RegReporte.NUM_REIN - RegReporte.NUM_EGRE;

             /* Capitalizacion A?o anterior*/
             RegReporte.IMP_CAPI_ANNO_ANTE := RegReporte.NUM_INGR_ANNO_ANTE + RegReporte.NUM_REIN_ANNO_ANTE - RegReporte.NUM_EGRE_ANNO_ANTE;

             /* promedio capitalizacion */
             IF RegReporte.NUM_CAMP <> 0 THEN
                RegReporte.PRO_CAPI := RegReporte.IMP_CAPI / RegReporte.NUM_CAMP;
             ELSE
                RegReporte.PRO_CAPI := 0;
             END IF;

             /* porcentaje actividad */
             IF RegReporte.NUM_ACTI_FINA <> 0 THEN
                RegReporte.POR_ACTI := (RegReporte.NUM_PEDI / RegReporte.NUM_ACTI_FINA) * 100;
             ELSE
                RegReporte.POR_ACTI := 0;
             END IF;

             /* porcentaje actividad estimada */
             IF RegReporte.NUM_ESTI_ACTI <> 0 THEN
                RegReporte.POR_ACTI_ESTI := (RegReporte.NUM_PEDI_ESTI / RegReporte.NUM_ESTI_ACTI) * 100;
             ELSE
                RegReporte.POR_ACTI_ESTI := 0;
             END IF;

             /* porcentaje pedidos estimados */
             IF RegReporte.NUM_PEDI_ESTI <> 0 THEN
                RegReporte.POR_PEDI_ESTI := (RegReporte.NUM_PEDI  - RegReporte.NUM_PEDI_ESTI) * 100 / RegReporte.NUM_PEDI_ESTI;
             ELSE
                RegReporte.POR_PEDI_ESTI := 0;
             END IF;

             /* porcentaje pedidos a?o anterior */
             IF RegReporte.NUM_PEDI_ANNO_ANTE <> 0 THEN
                RegReporte.POR_PEDI_ANNO_ANTE := (RegReporte.NUM_PEDI  - RegReporte.NUM_PEDI_ANNO_ANTE) * 100 / RegReporte.NUM_PEDI_ANNO_ANTE;
             ELSE
                RegReporte.POR_PEDI_ANNO_ANTE := 0;
             END IF;

             /* promedio soles por pedido estimado */
              IF RegReporte.NUM_PEDI_ESTI <> 0 THEN
                RegReporte.PRO_SOLE_PEDI_ESTI := RegReporte.VEN_ESTI_ACTU / RegReporte.NUM_PEDI_ESTI;
             ELSE
                RegReporte.PRO_SOLE_PEDI_ESTI := 0;
             END IF;

             /* promedio soles por pedido */
             IF RegReporte.NUM_PEDI <> 0 THEN
                RegReporte.PRO_SOLE_PEDI := RegReporte.VEN_REAL_ACTU / RegReporte.NUM_PEDI;
             ELSE
                RegReporte.PRO_SOLE_PEDI := 0;
             END IF;

             /* incremento soles por pedido estimado */
             IF RegReporte.PRO_SOLE_PEDI_ESTI <> 0 THEN
                RegReporte.INC_SOLE_PEDI_ESTI := ((RegReporte.PRO_SOLE_PEDI - RegReporte.PRO_SOLE_PEDI_ESTI) * 100) / RegReporte.PRO_SOLE_PEDI_ESTI ;
             ELSE
                RegReporte.INC_SOLE_PEDI_ESTI := 0;
             END IF;

             /* incremento soles por pedido AA */
             IF RegReporte.NUM_PEDI_ANNO_ANTE <> 0 THEN
                lnValor := RegReporte.VEN_REAL_ANNO_ANTE / RegReporte.NUM_PEDI_ANNO_ANTE;
             ELSE
                lnValor := 0;
             END IF;

             IF lnValor <> 0 THEN
                RegReporte.INC_SOLE_PEDI_ANNO_ANTE := ((RegReporte.PRO_SOLE_PEDI - lnValor) * 100) / lnValor;
             ELSE
                RegReporte.INC_SOLE_PEDI_ANNO_ANTE := 0;
             END IF;

             /* incremento soles por pedido campa?a anterior */
             IF psTipoPresentacion = 'P' OR psTipoPresentacion = 'R' THEN
                IF RegReporte.NUM_PEDI_ANTE <> 0 THEN
                   lnValor := RegReporte.VEN_REAL_ANTE / RegReporte.NUM_PEDI_ANTE;
                ELSE
                   lnValor := 0;
                END IF;
                IF lnValor <> 0 AND RegReporte.NUM_PEDI <> 0 THEN
                   RegReporte.Inc_Sole_Pedi_Camp_Ante := (((RegReporte.VEN_REAL_ACTU/ RegReporte.NUM_PEDI) - lnValor) * 100) / lnValor;
                ELSE
                   RegReporte.Inc_Sole_Pedi_Camp_Ante := 0;
                END IF;
             END IF;

             /* promedio unid x pedidos */
             IF RegReporte.NUM_PEDI <> 0 THEN
                RegReporte.PRO_UNID_PEDI := RegReporte.NUM_UNID / RegReporte.NUM_PEDI;
             ELSE
                RegReporte.PRO_UNID_PEDI := 0;
             END IF;

             /* promedio unid x pedidos estimado */
             IF RegReporte.NUM_PEDI_ESTI <> 0 THEN
                RegReporte.PRO_UNID_PEDI_ESTI := RegReporte.NUM_UNID_ESTI / RegReporte.NUM_PEDI_ESTI;
             ELSE
                RegReporte.PRO_UNID_PEDI_ESTI := 0;
             END IF;

             /* promedio unid x pedido a?o anterior */
             IF RegReporte.NUM_PEDI_ANNO_ANTE <> 0 THEN
                RegReporte.PRO_UNID_PEDI_ANTE := RegReporte.NUM_UNID_ANNO_ANTE / RegReporte.NUM_PEDI_ANNO_ANTE;
             ELSE
                RegReporte.PRO_UNID_PEDI_ANTE := 0;
             END IF;

             /* ppu */
             IF RegReporte.NUM_UNID <> 0 THEN
                RegReporte.IMP_PPU := RegReporte.VEN_REAL_ACTU / RegReporte.NUM_UNID;
             ELSE
                RegReporte.IMP_PPU := 0;
             END IF;

             /* ppu estimado */
             IF RegReporte.NUM_UNID_ESTI <> 0 THEN
                RegReporte.IMP_PPU_ESTI := RegReporte.VEN_ESTI_ACTU / RegReporte.NUM_UNID_ESTI;
             ELSE
                RegReporte.IMP_PPU_ESTI := 0;
             END IF;

             /* ppu anterior */
             IF RegReporte.NUM_UNID_ANNO_ANTE <> 0 THEN
                RegReporte.IMP_PPU_ANTE := RegReporte.VEN_REAL_ANNO_ANTE / RegReporte.NUM_UNID_ANNO_ANTE;
             ELSE
                RegReporte.IMP_PPU_ANTE := 0;
             END IF;

             /* Pasandolo al table */
             INSERT INTO VEN_REPOR_VENTA_VARIA
             VALUES  RegReporte;
         END LOOP;
     END IF;
     EXIT WHEN cVenta%NOTFOUND;
   END LOOP;
   CLOSE cVenta;
   RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_OBTIE_VENTA_VARIA: '||ls_sqlerrm);
END VEN_PR_OBTIE_VENTA_VARIA;

/***************************************************************************
Descripcion       :  Funcion para obtener el Valor Total de los montos del
                     reporte de Evalucion de Venta y Variables por Seccion
Fecha Creacion    : 02/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_EVALU_VARIA_SECCI(
    psCodigoPais     VARCHAR2,
    psCodigoPeriodo VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2)

IS
 CURSOR c_cursor (oid_codigoPais NUMBER,
 oid_codigoPeriodo NUMBER,
 oid_codigoMarca NUMBER,
 oid_codigoCanal NUMBER,
 oid_codigoRegion NUMBER,
 oid_codigoZona NUMBER
 ) IS
   SELECT ZON_REGIO.COD_REGI,
          ZON_REGIO.OID_REGI,
          ZON_ZONA.COD_ZONA  ,
          ZON_ZONA.OID_ZONA
   FROM   ZON_REGIO,
          ZON_ZONA
  WHERE   ZON_ZONA.ZORG_OID_REGI =ZON_REGIO.OID_REGI
          AND ZON_REGIO.OID_REGI =DECODE(psCodigoRegion, NULL, ZON_REGIO.OID_REGI ,oid_codigoRegion)
          AND ZON_ZONA.OID_ZONA =DECODE(psCodigoZona, NULL,ZON_ZONA.OID_ZONA,oid_codigoZona)
          AND ZON_ZONA.IND_ACTI = 1
          AND ZON_ZONA.IND_BORR = 0
  ORDER BY ZON_REGIO.COD_REGI;

 FILAS_CURSOR  NUMBER:= 3000;

   TYPE TVEN_REPOR_EVALU_SECCI IS RECORD (
     COD_REGI         ZON_REGIO.COD_REGI%TYPE,
     OID_REGI         ZON_REGIO.OID_REGI%TYPE,
     COD_ZONA         ZON_ZONA.COD_ZONA%TYPE,
     OID_ZONA         ZON_ZONA.OID_ZONA%TYPE
   );
   TYPE TABLA_REGISTRO IS TABLE OF TVEN_REPOR_EVALU_SECCI;
 TablaRegistro   TABLA_REGISTRO;

  lnIdPais         SEG_PAIS.OID_PAIS%TYPE;
  lnIdPeriodo      SEG_PERIO_CORPO.OID_PERI%TYPE;
  lnIdRegion       ZON_REGIO.OID_REGI%TYPE;
  lnIdZona         ZON_ZONA.OID_ZONA%TYPE;
  lnIdMarca        SEG_MARCA.OID_MARC%TYPE;
  lnIdCanal        SEG_CANAL.OID_CANA%TYPE;

BEGIN
  /*se comienza elmimiando los datos temporales de la tabla*/
  DELETE FROM VEN_REPOR_EVALU_VARIA_SECCI;

  lnIdPais     := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante
  IF psCodigoRegion IS NOT NULL   THEN
     lnIdRegion   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_REGION(psCodigoPais,psCodigoMarca, psCodigoCanal, psCodigoRegion);-- id del Region consultante
  END IF;
  IF psCodigoZona IS NOT NULL THEN
    lnIdZona     := gen_pkg_gener.GEN_FN_DEVUELVE_ID_ZONA(  psCodigoPais,psCodigoMarca, psCodigoCanal, psCodigoRegion,psCodigoZona);-- id del Zona consultante
  END IF;
  lnIdMarca    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);-- id del Marca consultante
  lnIdCanal    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);-- id del Canal consultante
  lnIdPeriodo  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo , lnIdMarca , lnIdCanal );-- id del Periodo consultante

  OPEN c_cursor(lnIdPais, lnIdPeriodo, lnIdMarca ,lnIdCanal , lnIdRegion, lnIdZona  );
  LOOP
      FETCH c_cursor BULK COLLECT INTO tablaRegistro LIMIT FILAS_CURSOR;
      IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
            VEN_PR_CARGA_EVALU_SECC_ZONA(
              psCodigoPais     ,
              psCodigoPeriodo ,
              psCodigoMarca ,
              psCodigoCanal ,
              tablaRegistro(x).COD_REGI,
              tablaRegistro(x).COD_ZONA,
              lnIdPais,
              lnIdPeriodo,
              lnIdMarca,
              lnIdCanal,
              tablaRegistro(x).OID_REGI,
              tablaRegistro(x).OID_ZONA
            );
         END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
  END LOOP;
  CLOSE c_cursor;

  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CARGA_EVALU_VARIA_SECCI: '||ls_sqlerrm);
END VEN_PR_CARGA_EVALU_VARIA_SECCI;

/***************************************************************************
Descripcion       :  Funcion para obtener el Valor Total de los montos del
                     reporte de Evalucion de Venta y Variables por Seccion
                     Para sumarizar por Zona
Fecha Creacion    : 22/06/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_EVALU_SECC_ZONA(
    psCodigoPais     VARCHAR2,
    psCodigoPeriodo VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2,
    lnIdPais     NUMBER,
    lnIdPeriodo NUMBER,
    lnIdMarca NUMBER,
    lnIdCanal NUMBER,
    lnIdRegion NUMBER,
    lnIdZona NUMBER)

IS
 CURSOR c_cursor  IS
 SELECT GEN_PKG_GENER.PER_FN_RECUP_RESPO_UNIAD(psCodigoPais,psCodigoMarca, psCodigoCanal,
        ZON_REGIO.COD_REGI,
        ZON_ZONA.COD_ZONA,
        ZON_SECCI.COD_SECC,
        'N') AS LID_SECCI,
        ZON_REGIO.COD_REGI,
        ZON_REGIO.DES_REGI,
        ZON_ZONA.COD_ZONA,
        ZON_ZONA.DES_ZONA,
        ZON_SECCI.COD_SECC,
        VEN_FN_OBTIE_COBRO_31DIAS( psCodigoPeriodo, ZON_REGIO.COD_REGI, ZON_ZONA.COD_ZONA, ZON_SECCI.COD_SECC,lnIdMarca,lnIdCanal,lnIdPais),
        SUM(INT_FUENT_VENTA_REAL_VACUM.NUM_UNID_VEND) AS NUM_UNID_VEND,
        SUM(INT_FUENT_VENTA_REAL_VACUM.NUM_CLIE) AS NUM_CLIE,
        SUM(INT_FUENT_VENTA_REAL_VACUM.NUM_PEDI) AS NUM_PEDI,
        SUM(INT_FUENT_VENTA_REAL_VACUM.IMP_VENT_NETA_ESTA) AS IMP_VENT_NETA_ESTA,
        SUM(INT_FUENT_VENTAS_REAL.NUM_INGR) AS NUM_INGR,
        SUM(INT_FUENT_VENTAS_REAL.NUM_REIN) AS NUM_REIN,
        SUM(INT_FUENT_VENTAS_REAL.NUM_EGRE) AS NUM_EGRE,
        SUM(INT_FUENT_VENTAS_REAL.NUM_ACTI_FINA) AS NUM_ACTI_FINA,
        SUM(0),SUM(0),SUM(0),SUM(0),SUM(0),SUM(0),SUM(0),SUM(0)
FROM    INT_FUENT_VENTA_REAL_VACUM,
        INT_FUENT_VENTAS_REAL,
        ZON_REGIO,
        ZON_ZONA,
        ZON_SECCI,
        ZON_TERRI_ADMIN,
        ZON_TERRI
WHERE   INT_FUENT_VENTA_REAL_VACUM.PAIS_OID_PAIS =INT_FUENT_VENTAS_REAL.PAIS_OID_PAIS
        AND INT_FUENT_VENTA_REAL_VACUM.ZORG_OID_REGI =INT_FUENT_VENTAS_REAL.ZORG_OID_REGI
        AND INT_FUENT_VENTA_REAL_VACUM.ZZON_OID_ZONA =INT_FUENT_VENTAS_REAL.ZZON_OID_ZONA
        AND INT_FUENT_VENTA_REAL_VACUM.TERR_OID_TERR =INT_FUENT_VENTAS_REAL.TERR_OID_TERR
        AND INT_FUENT_VENTA_REAL_VACUM.PERD_OID_PERI =INT_FUENT_VENTAS_REAL.PERD_OID_PERI
        AND INT_FUENT_VENTA_REAL_VACUM.ZORG_OID_REGI =ZON_REGIO.OID_REGI
        AND INT_FUENT_VENTA_REAL_VACUM.ZZON_OID_ZONA =ZON_ZONA.OID_ZONA
        AND INT_FUENT_VENTA_REAL_VACUM.TERR_OID_TERR =ZON_TERRI.OID_TERR
        AND INT_FUENT_VENTA_REAL_VACUM.PAIS_OID_PAIS =lnIdPais
        AND INT_FUENT_VENTA_REAL_VACUM.PERD_OID_PERI =lnIdPeriodo
        AND INT_FUENT_VENTA_REAL_VACUM.ZORG_OID_REGI =lnIdRegion
        AND INT_FUENT_VENTA_REAL_VACUM.ZZON_OID_ZONA =lnIdZona
        AND ZON_ZONA.ZORG_OID_REGI =ZON_REGIO.OID_REGI
        AND ZON_SECCI.ZZON_OID_ZONA=ZON_ZONA.OID_ZONA
        AND ZON_TERRI_ADMIN.ZSCC_OID_SECC =ZON_SECCI.OID_SECC
        AND ZON_TERRI.OID_TERR = ZON_TERRI_ADMIN.TERR_OID_TERR
        AND ZON_ZONA.IND_ACTI = 1
        AND ZON_ZONA.IND_BORR = 0
        AND ZON_SECCI.IND_ACTI = 1
        AND ZON_SECCI.IND_BORR = 0
        AND ZON_TERRI_ADMIN.IND_BORR = 0
GROUP BY ZON_REGIO.COD_REGI, ZON_REGIO.DES_REGI, ZON_ZONA.COD_ZONA, DES_ZONA, ZON_SECCI.COD_SECC
ORDER BY ZON_REGIO.COD_REGI, ZON_REGIO.DES_REGI, ZON_ZONA.COD_ZONA, DES_ZONA, ZON_SECCI.COD_SECC;

 FILAS_CURSOR  NUMBER:= 3000;
 TYPE TABLA_REGISTRO IS TABLE OF VEN_REPOR_EVALU_VARIA_SECCI%ROWTYPE;
 TablaRegistro   TABLA_REGISTRO;

  ls_SUM_NUM_UNID_VEND NUMBER(14);
 ls_SUM_NUM_CLIE NUMBER(14);
  ls_SUM_NUM_PEDI NUMBER(14);
  ls_SUM_IMP_VENT_NETA_ESTA NUMBER(15,2);
  ls_SUM_NUM_INGR NUMBER(14);
  ls_SUM_NUM_REIN NUMBER(14);
  ls_SUM_NUM_EGRE NUMBER(14);
  ls_SUM_NUM_ACTI_FINA NUMBER(14)          ;

BEGIN

  ls_SUM_NUM_UNID_VEND :=0;
  ls_SUM_NUM_CLIE :=0;
  ls_SUM_NUM_PEDI :=0;
  ls_SUM_IMP_VENT_NETA_ESTA :=0;
  ls_SUM_NUM_INGR :=0;
  ls_SUM_NUM_REIN :=0;
  ls_SUM_NUM_EGRE :=0;
  ls_SUM_NUM_ACTI_FINA :=0;

  OPEN c_cursor;
  LOOP
      FETCH c_cursor BULK COLLECT INTO tablaRegistro LIMIT FILAS_CURSOR;
      IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             INSERT INTO VEN_REPOR_EVALU_VARIA_SECCI
             VALUES tablaRegistro(x);
              ls_SUM_NUM_UNID_VEND := ls_SUM_NUM_UNID_VEND +          tablaRegistro(x).NUM_UNID_VEND;
             ls_SUM_NUM_CLIE :=ls_SUM_NUM_CLIE +                     tablaRegistro(x).NUM_CLIE ;
              ls_SUM_NUM_PEDI :=ls_SUM_NUM_PEDI +                     tablaRegistro(x).NUM_PEDI ;
              ls_SUM_IMP_VENT_NETA_ESTA :=ls_SUM_IMP_VENT_NETA_ESTA + tablaRegistro(x).IMP_VENT_NETA_ESTA ;
              ls_SUM_NUM_INGR :=ls_SUM_NUM_INGR +                     tablaRegistro(x).NUM_INGR ;
              ls_SUM_NUM_REIN :=ls_SUM_NUM_REIN +                     tablaRegistro(x).NUM_REIN ;
              ls_SUM_NUM_EGRE :=ls_SUM_NUM_EGRE +                     tablaRegistro(x).NUM_EGRE ;
              ls_SUM_NUM_ACTI_FINA :=ls_SUM_NUM_ACTI_FINA +           tablaRegistro(x).NUM_ACTI_FINA ;
         END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
  END LOOP;
  CLOSE c_cursor;

  UPDATE  VEN_REPOR_EVALU_VARIA_SECCI
  SET
     SUM_NUM_UNID_VEND =ls_SUM_NUM_UNID_VEND ,
   SUM_NUM_CLIE =     ls_SUM_NUM_CLIE  ,
   SUM_NUM_PEDI =     ls_SUM_NUM_PEDI  ,
   SUM_IMP_VENT_NETA_ESTA = ls_SUM_IMP_VENT_NETA_ESTA ,
   SUM_NUM_INGR =           ls_SUM_NUM_INGR ,
   SUM_NUM_REIN =           ls_SUM_NUM_REIN ,
   SUM_NUM_EGRE =           ls_SUM_NUM_EGRE ,
   SUM_NUM_ACTI_FINA =      ls_SUM_NUM_ACTI_FINA
  WHERE  COD_REGI = psCodigoRegion AND
         COD_ZONA = psCodigoZona ;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CARGA_EVALU_SECC_ZONA: '||ls_sqlerrm);
END VEN_PR_CARGA_EVALU_SECC_ZONA;

/***************************************************************************
Descripcion       :  Reporte Flujo de Resultados Economicos por Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_RESUL_ECONO_ZONA(
    psCodigoPais     VARCHAR2,
    psAnio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2)

IS
 CURSOR c_cursor (oid_codigoPais NUMBER,
 anio_inicial VARCHAR2,
 anio_final VARCHAR2,
 oid_codigoMarca NUMBER,
 oid_codigoCanal NUMBER,
 num_restar NUMBER
 ) IS
 SELECT list_periodo.oid_peri,
        gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(list_periodo.oid_peri) AS camp_actu,
        per_pkg_repor_perce.PER_FN_OBTIE_PERIO(gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(list_periodo.oid_peri),
        oid_codigoPais,oid_codigoMarca, oid_codigoCanal,(-1) * num_restar) AS camp_ante,
        per_pkg_repor_perce.PER_FN_OBTIE_PERIO(gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(list_periodo.oid_peri),
        gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais),
        gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
        gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal), -1) AS campa_menos_uno
 FROM
 TABLE (VEN_PKG_REPOR.VEN_FN_OBTIE_LISTA_OID_PERI(anio_inicial, anio_final,
       oid_codigoPais, oid_codigoMarca,  oid_codigoCanal ) )  list_periodo
 ORDER BY 1;

 FILAS_CURSOR  NUMBER:= 100;
 TYPE TABLA_REGISTRO IS TABLE OF c_cursor%ROWTYPE;
 TablaRegistro   TABLA_REGISTRO;

  lnIdPais         SEG_PAIS.OID_PAIS%TYPE;
  lnIdRegion       ZON_REGIO.OID_REGI%TYPE;
  lnIdZona         ZON_ZONA.OID_ZONA%TYPE;
  lnIdMarca        SEG_MARCA.OID_MARC%TYPE;
  lnIdCanal        SEG_CANAL.OID_CANA%TYPE;

  ls_periodoInicial          VARCHAR2(2);
  ls_periodoFinal            VARCHAR2(2);
  ls_trimestre               NUMBER;
  ls_contador                NUMBER;
  ls_orden_mostrar           NUMBER;
  ls_oidPeriAnte             NUMBER;
  ls_venta_estimado          NUMBER;
  ls_venta_real              NUMBER;
  ls_venta_real_ante         NUMBER;
  ls_venta_consultora        NUMBER;
  ls_venta_real_region       NUMBER;
  ls_venta_real_region_ante  NUMBER;
  ls_por_ca                  NUMBER;
  ls_por_venta_consultora    NUMBER;
  ls_por_part_real           NUMBER;
  ls_por_part_real_ante      NUMBER;
  ls_unidades_vendidas       NUMBER;
  ls_monto_faltante          NUMBER;
  ls_unidades_faltante       NUMBER;
  ls_monto_devolucion        NUMBER;
  ls_unidades_devolucion     NUMBER;
  ls_pedidos_anulados        NUMBER;
  ls_numero_pedidos          NUMBER;
  ls_por_monto_faltante      NUMBER;
  ls_por_unidades_faltante   NUMBER;
  ls_por_monto_devolucion    NUMBER;
  ls_por_unidades_devolucion NUMBER;
  ls_por_pedidos_anulados    NUMBER;
  ls_sum_venta_estimado      NUMBER;
  ls_sum_venta_real          NUMBER;
  ls_sum_venta_real_ante     NUMBER;
  ls_sum_por_ca              NUMBER;
  ls_sum_venta_consultora    NUMBER;
  ls_oidCampaAnte            NUMBER;
  ls_venta_real_campa_ante   NUMBER;
  ls_sum_por_venta_consultora    NUMBER;
  ls_sum_por_part_real           NUMBER;
  ls_sum_por_part_real_ante      NUMBER;
  ls_sum_monto_faltante          NUMBER;
  ls_sum_unidades_faltante       NUMBER;
  ls_sum_por_monto_faltante      NUMBER;
  ls_sum_por_unidades_faltante   NUMBER;
  ls_sum_monto_devolucion        NUMBER;
  ls_sum_unidades_devolucion     NUMBER;
  ls_sum_por_monto_devolucion    NUMBER;
  ls_sum_por_unidades_devolucion NUMBER;
  ls_sum_pedidos_anulados        NUMBER;
  ls_sum_por_pedidos_anulados    NUMBER;

BEGIN

  ls_periodoInicial:='01'; -- campaña con la que comienzan todos los periodos
  ls_periodoFinal  :='18'; -- campaña con la que culminan todos los periodos
  ls_trimestre := 6; -- cada cuanto se va a hacer la suma
  ls_contador := 1;
  ls_orden_mostrar:=1;
  ls_sum_venta_estimado           :=    0;
  ls_sum_venta_real               :=    0;
  ls_sum_venta_real_ante          :=    0;
  ls_sum_por_ca                   :=    0;
  ls_sum_venta_consultora         :=    0;
  ls_sum_por_venta_consultora     :=    0;
  ls_sum_por_part_real            :=    0;
  ls_sum_por_part_real_ante       :=    0;
  ls_sum_monto_faltante           :=    0;
  ls_sum_unidades_faltante        :=    0;
  ls_sum_por_monto_faltante       :=    0;
  ls_sum_por_unidades_faltante    :=    0;
  ls_sum_monto_devolucion         :=    0;
  ls_sum_unidades_devolucion      :=    0;
  ls_sum_por_monto_devolucion     :=    0;
  ls_sum_por_unidades_devolucion  :=    0;
  ls_sum_pedidos_anulados         :=    0;
  ls_sum_por_pedidos_anulados   :=    0;
  lnIdPais :=  gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);-- id del pais consultante
  IF psCodigoRegion IS NOT NULL   THEN
     lnIdRegion   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_REGION(psCodigoPais,psCodigoMarca, psCodigoCanal, psCodigoRegion);-- id del Region consultante
  END IF;
  IF psCodigoZona IS NOT NULL THEN
    lnIdZona     := gen_pkg_gener.GEN_FN_DEVUELVE_ID_ZONA(  psCodigoPais,psCodigoMarca, psCodigoCanal, psCodigoRegion,psCodigoZona);-- id del Zona consultante
  END IF;
  lnIdMarca    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);-- id del Marca consultante
  lnIdCanal    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);-- id del Canal consultante

  OPEN c_cursor(lnIdPais, psAnio||ls_periodoInicial, psAnio||ls_periodoFinal , lnIdMarca , lnIdCanal , to_number(ls_periodoFinal) );
  LOOP
      FETCH c_cursor BULK COLLECT INTO tablaRegistro LIMIT FILAS_CURSOR;
      IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
              ls_oidPeriAnte := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(tablaRegistro(x).camp_ante, lnIdMarca  ,lnIdCanal );
              ls_oidCampaAnte := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(tablaRegistro(x).campa_menos_uno, lnIdMarca  ,lnIdCanal );
              /*Calculamos el primer campo el estimado*/
              ls_venta_estimado    := VEN_FN_VENT_PREVI_SAP_NETA( tablaRegistro(x).oid_peri , lnIdRegion, lnIdZona);
              ls_venta_real        := VEN_FN_VENT_REAL_NETA( lnIdPais,  tablaRegistro(x).oid_peri , lnIdRegion, lnIdZona);
              ls_venta_real_ante   := VEN_FN_VENT_REAL_NETA( lnIdPais, ls_oidPeriAnte , lnIdRegion, lnIdZona);
              ls_venta_real_campa_ante   := VEN_FN_VENT_REAL_NETA( lnIdPais, ls_oidCampaAnte , lnIdRegion, lnIdZona);
              ls_venta_consultora  := VEN_FN_VENT_REAL_CLIE (lnIdPais,lnIdMarca,lnIdCanal,       tablaRegistro(x).oid_peri , lnIdRegion, lnIdZona);
              ls_venta_real_region := VEN_FN_VENT_REAL_NETA(lnIdPais,  tablaRegistro(x).oid_peri , lnIdRegion, NULL);
              ls_venta_real_region_ante := VEN_FN_VENT_REAL_NETA(lnIdPais, ls_oidPeriAnte   , lnIdRegion, NULL);
              IF (ls_venta_real_campa_ante IS NOT NULL AND ls_venta_real_campa_ante > 0) THEN
                 ls_por_ca := ((ls_venta_real - ls_venta_real_campa_ante)*100)/ls_venta_real_campa_ante ;
              ELSE
                 ls_por_ca := 0;
              END IF;
              IF (ls_venta_real IS NOT NULL AND ls_venta_real > 0) THEN
                 ls_por_venta_consultora :=(ls_venta_consultora*100)/ls_venta_real;
              ELSE
                 ls_por_venta_consultora := 0;
              END IF;
              IF (ls_venta_real_region_ante IS NOT NULL AND ls_venta_real_region_ante > 0) THEN
                 ls_por_part_real_ante :=(ls_venta_real_ante*100)/ ls_venta_real_region_ante;
              ELSE
                 ls_por_part_real_ante := 0;
              END IF;
              IF (ls_venta_real_region IS NOT NULL AND ls_venta_real_region > 0) THEN
                 ls_por_part_real :=(ls_venta_real*100)/ ls_venta_real_region;
              ELSE
                 ls_por_part_real := 0;
              END IF;


              VEN_PR_REPOR_ECONO_DATO_ADIC( lnIdPais, tablaRegistro(x).oid_peri , lnIdZona, lnIdMarca , lnIdCanal ,
              ls_unidades_vendidas,
              ls_monto_faltante,
              ls_unidades_faltante,
              ls_monto_devolucion,
              ls_unidades_devolucion,
              ls_pedidos_anulados,
              ls_numero_pedidos
              );
              IF ((ls_monto_faltante + ls_venta_real) > 0) THEN
                 ls_por_monto_faltante   := ls_monto_faltante*100/(ls_monto_faltante + ls_venta_real) ;
              ELSE
                 ls_por_monto_faltante   := 0;
              END IF;
              IF (ls_unidades_faltante + ls_unidades_vendidas > 0) THEN
                 ls_por_unidades_faltante:= ls_unidades_faltante*100/(ls_unidades_faltante + ls_unidades_vendidas) ;
              ELSE
                 ls_por_unidades_faltante   := 0;
              END IF;
              IF ((ls_monto_devolucion + ls_venta_real) > 0) THEN
                 ls_por_monto_devolucion   := ls_monto_devolucion*100/(ls_monto_devolucion + ls_venta_real) ;
              ELSE
                 ls_por_monto_devolucion   := 0;
              END IF;
              IF ((ls_unidades_devolucion + ls_unidades_vendidas) > 0) THEN
                 ls_por_unidades_devolucion:= ls_unidades_devolucion*100/(ls_unidades_devolucion + ls_unidades_vendidas) ;
              ELSE
                 ls_por_monto_faltante   := 0;
              END IF;
              IF (ls_numero_pedidos > 0) THEN
                 ls_por_pedidos_anulados:= ls_pedidos_anulados*100/(ls_numero_pedidos) ;
              ELSE
                 ls_por_pedidos_anulados:= 0;
              END IF;

             INSERT INTO VEN_REPOR_RESUL_ECONO_ZONA(ORD_REPOR,
                     COD_PERI,
                      COD_ZONA,
                     VAL_ESTI,
                     VAL_REAL,
                     VAL_ANTE,
                     POR_PERI_ANTE,
                     MON_VENT,
                      POR_VENT_ZONA,
                     POR_PART_REAL,
                     POR_PART_ANTE,
                      MON_FALT,
                     UNI_FALT,
                     POR_MONT_FALT,
                     POR_UNID_FALT,
                     MON_DEVO,
                     UNI_DEVO,
                     POR_MONT_DEVO,
                     POR_UNID_DEVO,
                     PED_ANUL,
                      POR_PEDI_ANUL  )
               VALUES (ls_orden_mostrar,
                       tablaRegistro(x).camp_actu,
                       psCodigoZona,
                       ls_venta_estimado,
                       ls_venta_real,
                       ls_venta_real_ante,
                       ls_por_ca,
                       ls_venta_consultora,
                       ls_por_venta_consultora,
                       ls_por_part_real,
                       ls_por_part_real_ante,
                       ls_monto_faltante,
                       ls_unidades_faltante,
                       ls_por_monto_faltante,
                       ls_por_unidades_faltante,
                       ls_monto_devolucion,
                       ls_unidades_devolucion,
                       ls_por_monto_devolucion,
                       ls_por_unidades_devolucion,
                       ls_pedidos_anulados,
                       ls_por_pedidos_anulados
                        );
              ls_sum_venta_estimado           :=    ls_sum_venta_estimado         +    ls_venta_estimado          ;
              ls_sum_venta_real               :=    ls_sum_venta_real             +    ls_venta_real              ;
              ls_sum_venta_real_ante          :=    ls_sum_venta_real_ante        +    ls_venta_real_ante         ;
              ls_sum_por_ca                   :=    ls_sum_por_ca                 +    ls_por_ca                  ;
              ls_sum_venta_consultora         :=    ls_sum_venta_consultora       +    ls_venta_consultora        ;
              ls_sum_por_part_real            :=    ls_sum_por_part_real          +    ls_por_part_real           ;
              ls_sum_por_part_real_ante       :=    ls_sum_por_part_real_ante     +    ls_por_part_real_ante      ;
              ls_sum_monto_faltante           :=    ls_sum_monto_faltante         +    ls_monto_faltante          ;
              ls_sum_unidades_faltante        :=    ls_sum_unidades_faltante      +    ls_unidades_faltante       ;
              ls_sum_por_monto_faltante       :=    ls_sum_por_monto_faltante     +    ls_por_monto_faltante      ;
              ls_sum_por_unidades_faltante    :=    ls_sum_por_unidades_faltante  +    ls_por_unidades_faltante   ;
              ls_sum_monto_devolucion         :=    ls_sum_monto_devolucion       +    ls_monto_devolucion        ;
              ls_sum_unidades_devolucion      :=    ls_sum_unidades_devolucion    +    ls_unidades_devolucion     ;
              ls_sum_por_monto_devolucion     :=    ls_sum_por_monto_devolucion   +    ls_por_monto_devolucion    ;
              ls_sum_por_unidades_devolucion  :=    ls_sum_por_unidades_devolucion+    ls_por_unidades_devolucion ;
              ls_sum_pedidos_anulados         :=    ls_sum_pedidos_anulados       +    ls_pedidos_anulados        ;
              ls_sum_por_pedidos_anulados   :=    ls_sum_por_pedidos_anulados  +    ls_por_pedidos_anulados  ;
              ls_orden_mostrar :=ls_orden_mostrar+1;
              IF MOD(ls_contador, ls_trimestre) = 0 THEN
              BEGIN
                 ls_orden_mostrar :=ls_orden_mostrar+1;
                 IF ls_sum_venta_real IS NOT NULL AND NOT ls_sum_venta_real = 0 THEN
                    ls_sum_por_venta_consultora     :=    (ls_sum_venta_consultora * 100) / ls_sum_venta_real;
                 ELSE
                     ls_sum_por_venta_consultora:=0;
                 END IF ;

                 INSERT INTO VEN_REPOR_RESUL_ECONO_ZONA(ORD_REPOR,
                     COD_PERI,
                      COD_ZONA,
                     VAL_ESTI,
                     VAL_REAL,
                     VAL_ANTE,
                     POR_PERI_ANTE,
                     MON_VENT,
                      POR_VENT_ZONA,
                     POR_PART_REAL,
                     POR_PART_ANTE,
                      MON_FALT,
                     UNI_FALT,
                     POR_MONT_FALT,
                     POR_UNID_FALT,
                     MON_DEVO,
                     UNI_DEVO,
                     POR_MONT_DEVO,
                     POR_UNID_DEVO,
                     PED_ANUL,
                      POR_PEDI_ANUL  )
               VALUES (ls_orden_mostrar,
                       '',
                        psCodigoZona,
                      ls_sum_venta_estimado,
                      ls_sum_venta_real,
                      ls_sum_venta_real_ante,
                      ls_sum_por_ca/ls_trimestre ,
                      ls_sum_venta_consultora,
                      ls_sum_por_venta_consultora,
                      ls_sum_por_part_real/ls_trimestre,
                      ls_sum_por_part_real_ante/ls_trimestre,
                      ls_sum_monto_faltante,
                      ls_sum_unidades_faltante,
                      ls_sum_por_monto_faltante/ls_trimestre,
                      ls_sum_por_unidades_faltante/ls_trimestre,
                      ls_sum_monto_devolucion,
                      ls_sum_unidades_devolucion,
                      ls_sum_por_monto_devolucion/ls_trimestre,
                      ls_sum_por_unidades_devolucion/ls_trimestre,
                      ls_sum_pedidos_anulados,
                      ls_sum_por_pedidos_anulados /ls_trimestre
                        );
                 ls_contador:= 1;
                 ls_sum_venta_estimado           :=   0;
                 ls_sum_venta_real               :=   0;
                 ls_sum_venta_real_ante          :=   0;
                 ls_sum_por_ca                   :=   0;
                 ls_sum_venta_consultora         :=   0;
                 ls_sum_por_venta_consultora     :=   0;
                 ls_sum_por_part_real            :=   0;
                 ls_sum_por_part_real_ante       :=   0;
                 ls_sum_monto_faltante           :=   0;
                 ls_sum_unidades_faltante        :=   0;
                 ls_sum_por_monto_faltante       :=   0;
                 ls_sum_por_unidades_faltante    :=   0;
                 ls_sum_monto_devolucion         :=   0;
                 ls_sum_unidades_devolucion      :=   0;
                 ls_sum_por_monto_devolucion     :=   0;
                 ls_sum_por_unidades_devolucion  :=   0;
                 ls_sum_pedidos_anulados         :=   0;
                 ls_sum_por_pedidos_anulados   :=   0;
               END;
               ELSE
                  ls_contador:= ls_contador+1;
               END IF;
         END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
  END LOOP;
  CLOSE c_cursor;

  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CARGA_RESUL_ECONO_ZONA: '||ls_sqlerrm);
END VEN_PR_CARGA_RESUL_ECONO_ZONA;
/***************************************************************************
Descripcion       :  Obtiene el valor del VAL_VENT_NETA_ESTA segun los parametros
                     ingresados
Parametros        : psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_VENT_PREVI_SAP_NETA(
    psOidPeriodo NUMBER,
    psOidRegion NUMBER,
    psOidZona NUMBER)
    RETURN NUMBER
    IS
ls_valorVenta  int_fuent_venta_previ_sap.VAL_VENT_NETA_ESTA%TYPE;
BEGIN

  ls_valorVenta:=0;

  SELECT int_fuent_venta_previ_sap.VAL_VENT_NETA_ESTA
  INTO   ls_valorVenta
  FROM   int_fuent_venta_previ_sap
  WHERE  int_fuent_venta_previ_sap.perd_oid_peri = psOidPeriodo
         AND int_fuent_venta_previ_sap.zorg_oid_regi = psOidRegion
         AND int_fuent_venta_previ_sap.zzon_oid_zona = psOidZona   ;

 RETURN  ls_valorVenta;

 EXCEPTION
  WHEN no_data_found  THEN
     RETURN 0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_VENT_PREVI_SAP_NETA: '||ls_sqlerrm);
END VEN_FN_VENT_PREVI_SAP_NETA;

/***************************************************************************
Descripcion       :  Obtiene el valor del IMP_VENT_NETA_ESTA segun los parametros
                     ingresados
Parametros        : psOidPais Oid del Pais
                    psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_VENT_REAL_NETA(
    psOidPais NUMBER,
    psOidPeriodo NUMBER,
    psOidRegion NUMBER,
    psOidZona NUMBER)
      RETURN NUMBER
    IS
ls_valorVenta  int_fuent_venta_previ_sap.VAL_VENT_NETA_ESTA%TYPE;
BEGIN

  ls_valorVenta:=0;

  SELECT nvl(sum(int_fuent_venta_real_vacum.IMP_VENT_NETA_ESTA),0)
  INTO   ls_valorVenta
  FROM   int_fuent_venta_real_vacum
  WHERE  int_fuent_venta_real_vacum.pais_oid_pais = psOidPais
         AND int_fuent_venta_real_vacum.perd_oid_peri = psOidPeriodo
         AND int_fuent_venta_real_vacum.zorg_oid_regi = psOidRegion
         AND int_fuent_venta_real_vacum.zzon_oid_zona = Decode(psOidZona, NULL,int_fuent_venta_real_vacum.zzon_oid_zona, psOidZona)    ;

 RETURN  ls_valorVenta;

 EXCEPTION
  WHEN no_data_found  THEN
     RETURN 0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_VENT_REAL_NETA: '||ls_sqlerrm);
END VEN_FN_VENT_REAL_NETA;
/***************************************************************************
Descripcion       :  VENTAS REALES de las CONSULTORAS NUEVAS
Parametros        : psOidPais Oid del Pais
                    psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_VENT_REAL_CLIE(
    psOidPais NUMBER,
    psOidMarca NUMBER,
    psOidCanal NUMBER,
    psOidPeriodo NUMBER,
    psOidRegion NUMBER,
    psOidZona NUMBER)
    RETURN NUMBER
    IS
ls_valorVenta  int_fuent_venta_previ_sap.VAL_VENT_NETA_ESTA%TYPE;
BEGIN
  ls_valorVenta:=0;

    SELECT    sum(ped_solic_posic.VAL_PREC_NETO_TOTA_LOCA)
      INTO  ls_valorVenta
     FROM
        zon_zona,
        zon_secci,
        zon_terri,
        zon_terri_admin,
        mae_clien,
        mae_clien_histo_estat,
        ped_solic_cabec,
        ped_solic_posic,
        pre_ofert,
        pre_ofert_detal,
        pre_tipo_ofert,
        pre_matri_factu_cabec,
        mae_clien_unida_admin
    WHERE  (    (MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE = mae_clien_histo_estat.clie_oid_clie)
        and (MAE_CLIEN.OID_CLIE = mae_clien_histo_estat.clie_oid_clie)
        and (ped_solic_cabec.clie_oid_clie = mae_clien_histo_estat.clie_oid_clie)
        AND (ped_solic_cabec.perd_oid_peri = mae_clien_histo_estat.perd_oid_peri)
        and (MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI = ZON_TERRI_ADMIN.OID_TERR_ADMI)
        and (ZON_TERRI_ADMIN.ZSCC_OID_SECC = ZON_SECCI.OID_SECC)
        and (ZON_SECCI.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA)
        and (ZON_ZONA.ZORG_OID_REGI = psOidRegion)
        and (MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = 1)
        AND (zon_terri_admin.cana_oid_cana = psOidCanal)
        AND (zon_terri_admin.marc_oid_marc = psOidMarca)
        AND (zon_terri_admin.pais_oid_pais = psOidPais)
        and(zon_terri_admin.TERR_OID_TERR = zon_terri.OID_TERR)
        AND (zon_terri.oid_terr = ped_solic_cabec.terr_oid_terr)
        AND (ped_solic_cabec.oid_soli_cabe = ped_solic_posic.SOCA_OID_SOLI_CABE)
        and (ped_solic_posic.OFDE_OID_DETA_OFER = pre_ofert_detal.OID_DETA_OFER)
        and (pre_ofert_detal.OFER_OID_OFER = pre_ofert.OID_OFER)
        and (pre_ofert.MFCA_OID_CABE = pre_matri_factu_cabec.OID_CABE)
        and (pre_matri_factu_cabec.PERD_OID_PERI = psOidPeriodo )
        and (pre_ofert_detal.TOFE_OID_TIPO_OFER = pre_tipo_ofert.OID_TIPO_OFER))
        And pre_tipo_ofert.IND_ESTA = 1
        and ped_solic_posic.NUM_UNID_ATEN > 0
        AND mae_clien_histo_estat.ESTA_OID_ESTA_CLIE = 2
        AND zon_zona.oid_zona = psOidZona  ;
 RETURN  ls_valorVenta;

 EXCEPTION
  WHEN no_data_found  THEN
     RETURN 0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_VENT_REAL_CLIE: '||ls_sqlerrm);
END VEN_FN_VENT_REAL_CLIE;
/***************************************************************************
Descripcion       : Obtiene el valor del IMP_VENT_NETA_ESTA segun los parametros
                     ingresados, por region (Venta Reales año actual por Region)
Parametros        : psOidPais Oid del Pais
                    psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_REPOR_ECONO_DATO_ADIC(
    psOidPais NUMBER,
    psOidPeri NUMBER,
    psOidZona NUMBER ,
    psOidMarca NUMBER,
    psOidCanal NUMBER,
    psUnidadesVendidas OUT NUMBER,
    psMontoFaltante OUT NUMBER,
    psUnidadesFaltante OUT NUMBER,
    psMontoDevolucion OUT NUMBER,
    psUnidadesDevolucion OUT NUMBER,
    psPedidosAnulados OUT NUMBER,
    psNumeroPedidos OUT NUMBER
    )     IS
BEGIN
SELECT  SUM (pos.num_unid_aten) unidadesvendidas,
        SUM (pos.NUM_UNID_DEMA_REAL - pos.NUM_UNID_COMPR) UNIDFALTANTE,
        SUM ((pos.NUM_UNID_DEMA_REAL - pos.NUM_UNID_COMPR) * pos.VAL_PREC_NETO_UNIT_LOCA) MONTOfaltante,
        SUM (Decode(ptsol.num_unid_devu ,1,pos.num_unid_aten, 0)) AS unidadesvendidasdevueltas,
        SUM (Decode(num_unid_devu , 1, pos.val_prec_fact_tota_loca, 0 )) AS preciofacdevueltas,
       COUNT (DISTINCT (CASE WHEN (ts.ind_anul = 1 AND ts.ind_devo = 0) THEN soc.clie_oid_clie END))  pedidosanulados,
        COUNT (DISTINCT (soc.clie_oid_clie))  numeropedidos
INTO  psUnidadesVendidas,
         psUnidadesFaltante,
         psMontoFaltante,
         psUnidadesDevolucion,
         psMontoDevolucion,
         psPedidosAnulados,
         psNumeroPedidos
FROM  ped_solic_cabec soc,
      ped_solic_posic pos,
      seg_socie soci,
      pre_ofert_detal ofd,
      pre_tipo_ofert tof,
      ped_tipo_solic ts,
      ped_tipo_solic_pais tsp,
      int_param_tipo_solic ptsol
WHERE pos.soca_oid_soli_cabe = soc.oid_soli_cabe
      AND soc.perd_oid_peri =   psOidPeri
      AND pos.ofde_oid_deta_ofer = ofd.oid_deta_ofer
      AND ofd.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
      AND soc.pais_oid_pais = psOidPais
      AND soc.soci_oid_soci = soci.oid_soci
      AND soc.zzon_oid_zona = psOidZona
      AND soc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
      AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
      AND ptsol.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
      AND soc.fec_fact IS NOT NULL
      AND soc.ind_ts_no_conso = 1
      AND soc.ind_oc = 1
      AND soc.ind_pedi_prue = 0
      AND tof.ind_esta = 1     ;
 EXCEPTION
  WHEN no_data_found  THEN
    psUnidadesVendidas :=0;
    psMontoFaltante  :=0;
    psUnidadesFaltante  :=0;
    psMontoDevolucion  :=0;
    psUnidadesDevolucion  :=0;
    psPedidosAnulados  :=0;
    psNumeroPedidos   :=0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_REPOR_ECONO_DATO_ADIC: '||ls_sqlerrm);
END VEN_PR_REPOR_ECONO_DATO_ADIC;
/***************************************************************************
Descripcion       :  Reporte Flujo de Resultados Economicos
Fecha Creacion    : 09/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_RESUL_ECONO(
    psCodigoPais     VARCHAR2,
    psAnio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2)

IS
 CURSOR c_cursor  IS
 SELECT zon_regio.cod_regi,
        zon_zona.cod_zona
 FROM zon_regio,
      zon_zona
 WHERE zon_regio.oid_regi = zon_zona.zorg_oid_regi
       AND zon_regio.cod_regi =    Decode(psCodigoRegion, NULL,zon_regio.cod_regi, 'Todos', zon_regio.cod_regi, psCodigoRegion)
       AND zon_zona.cod_zona =    Decode(psCodigoZona, NULL,zon_zona.cod_zona, 'Todos', zon_zona.cod_zona, psCodigoZona)
       AND ZON_ZONA.IND_ACTI = 1
         AND ZON_ZONA.IND_BORR = 0;

 FILAS_CURSOR  NUMBER:= 100;
 TYPE TABLA_REGISTRO IS TABLE OF c_cursor%ROWTYPE;
 TablaRegistro   TABLA_REGISTRO;
 BEGIN

  /*se comienza elmimiando los datos temporales de la tabla*/
  DELETE FROM VEN_REPOR_RESUL_ECONO_ZONA;

 OPEN c_cursor;
  LOOP
      FETCH c_cursor BULK COLLECT INTO tablaRegistro LIMIT FILAS_CURSOR;
      IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
              VEN_PR_CARGA_RESUL_ECONO_ZONA(psCodigoPais , psAnio , psCodigoMarca , psCodigoCanal ,  tablaRegistro(x).cod_regi,  tablaRegistro(x).cod_zona);
         END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
  END LOOP;
  CLOSE c_cursor;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CARGA_RESUL_ECONO: '||ls_sqlerrm);

  END VEN_PR_CARGA_RESUL_ECONO;

/***************************************************************************
Descripcion       :  Reporte Flujo de Resultados Economicos
Fecha Creacion    : 09/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_EVALU_COBER_NIVEL(
    psCodigoPais     VARCHAR2,
    psAnio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2)

IS
 CURSOR c_cursor  IS
 SELECT zon_regio.cod_regi,
        zon_zona.cod_zona
 FROM zon_regio,
      zon_zona
 WHERE zon_regio.oid_regi = zon_zona.zorg_oid_regi
       AND zon_regio.cod_regi =  Decode(psCodigoRegion, NULL,zon_regio.cod_regi, 'Todos', zon_regio.cod_regi, psCodigoRegion)
       AND zon_zona.cod_zona =   Decode(psCodigoZona, NULL,zon_zona.cod_zona, 'Todos', zon_zona.cod_zona, psCodigoZona)
       AND ZON_ZONA.IND_ACTI = 1
       AND ZON_ZONA.IND_BORR = 0;
 FILAS_CURSOR  NUMBER:= 100;
 TYPE TABLA_REGISTRO IS TABLE OF c_cursor%ROWTYPE;
 TablaRegistro   TABLA_REGISTRO;
 BEGIN

  /*se comienza elmimiando los datos temporales de la tabla*/
  DELETE FROM VEN_REPOR_EVALU_COBER_NIVEL;

 OPEN c_cursor;
  LOOP
      FETCH c_cursor BULK COLLECT INTO tablaRegistro LIMIT FILAS_CURSOR;
      IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
              VEN_PR_CARGA_EVALU_COBER_ZONA(psCodigoPais , psAnio , psCodigoMarca , psCodigoCanal ,  tablaRegistro(x).cod_regi,  tablaRegistro(x).cod_zona , 1, 6);
              VEN_PR_CARGA_EVALU_COBER_ZONA(psCodigoPais , psAnio , psCodigoMarca , psCodigoCanal ,  tablaRegistro(x).cod_regi,  tablaRegistro(x).cod_zona , 2, 6);
              VEN_PR_CARGA_EVALU_COBER_ZONA(psCodigoPais , psAnio , psCodigoMarca , psCodigoCanal ,  tablaRegistro(x).cod_regi,  tablaRegistro(x).cod_zona , 3, 6);
         END LOOP;
      END IF;
      EXIT WHEN c_cursor%NOTFOUND;
  END LOOP;
  CLOSE c_cursor;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CARGA_EVALU_COBER_NIVEL: '||ls_sqlerrm);
  END VEN_PR_CARGA_EVALU_COBER_NIVEL;
/***************************************************************************
Descripcion       :  Evaluacion de cobertura y niveles por seccion
Fecha Creacion    : 03/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
PROCEDURE VEN_PR_CARGA_EVALU_COBER_ZONA(
    psCodigoPais     VARCHAR2,
    psAnio VARCHAR2,
    psCodigoMarca VARCHAR2,
    psCodigoCanal VARCHAR2,
    psCodigoRegion VARCHAR2,
    psCodigoZona VARCHAR2,
    psTrimeste NUMBER,
    psTotalTrimestre NUMBER)
IS
  lnIdPais         SEG_PAIS.OID_PAIS%TYPE;
  lnIdMarca        SEG_MARCA.OID_MARC%TYPE;
  lnIdCanal        SEG_CANAL.OID_CANA%TYPE;
  psCodigoFinal    VARCHAR2(6);
  psCodigoInicial  VARCHAR2(6);
  lnIdPeriodoInicial NUMBER;
  lnIdPeriodoFinal   NUMBER;
  lnIdZona          NUMBER;
  --lnIdRegion         NUMBER;
BEGIN

  lnIdPais :=  gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);-- id del pais consultante
  lnIdMarca    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);-- id del Marca consultante
  lnIdCanal    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);-- id del Canal consultante
  lnIdZona    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_ZONA( psCodigoPais, psCodigoMarca,psCodigoCanal, psCodigoRegion, psCodigoZona);-- id de la Zona consultante
  --lnIdRegion    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_REGION(psCodigoPais, psCodigoMarca,psCodigoCanal, psCodigoRegion);-- id de la Region consultante

  psCodigoInicial := psAnio ||to_char((psTrimeste - 1)*psTotalTrimestre+1, 'FM9909' );
  psCodigoFinal   := psAnio ||to_char((psTrimeste - 1)*psTotalTrimestre+psTotalTrimestre, 'FM9909' );
  lnIdPeriodoInicial:= gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoInicial);
  lnIdPeriodoFinal:= gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoFinal);

  INSERT INTO VEN_REPOR_EVALU_COBER_NIVEL
 (COD_TRIM,
  COD_REGI,
  COD_ZONA,
  COD_SECC,
  COD_CLIE,
  NOM_CLIE,
  MON_VENT,
  MON_TOTA_VENT,
  POR_VENT,
  VAL_ACT,
  VAL_PL,
  NUM_TERR,
  VAL_VT,
  VAL_ET,
  VAL_TC,
  POR_TC,
  VAL_M4,
  VAL_M3,
  VAL_M2,
  VAL_M1,
  POR_M4,
  POR_M3,
  POR_M2,
  POR_M1,
  VAL_UNO_N,
  VAL_DOS_N,
  VAL_TRES_N,
  VAL_CUAT_N,
  POR_UNO_N,
  POR_DOS_N,
  POR_TRES_N,
  POR_CUAT_N,
  VAL_UNO_NN,
  VAL_DOS_NN,
  VAL_TRES_NN,
  VAL_CUAT_NN,
  POR_UNO_NN,
  POR_DOS_NN,
  POR_TRES_NN,
  POR_CUAT_NN,
  COD_TERR_VACI)
  (------------------------ Evaluación Cobertura y Niveles Por Sección
SELECT    psTrimeste,
          psCodigoRegion,
          psCodigoZona,
          TEMP.SEC COD_SECC,
          gen_pkg_gener.PER_FN_RECUP_RESPO_UNIAD(psCodigoPais, psCodigoMarca, psCodigoCanal, psCodigoZona,psCodigoZona, TEMP.SEC, 'C') COD_CLIE,
          gen_pkg_gener.PER_FN_RECUP_RESPO_UNIAD(psCodigoPais, psCodigoMarca, psCodigoCanal, psCodigoZona,psCodigoZona, TEMP.SEC, 'N') NOM_CLIE,
          TEMP.VENTA MON_VENT ,
          TEMP.TOTAL_VENTA MON_TOTA_VENTA,
          DECODE(TEMP.TOTAL_VENTA,0,0,TEMP.VENTA / TEMP.TOTAL_VENTA) * 100 POR_VENT,
          TEMP.ACT VAL_ACT,
          CASE WHEN TEMP.ACT >= 50 THEN 'PL' WHEN TEMP.ACT < 50 THEN ' ' END AS VAL_PL,
          TEMP.NUM_TERRI,
          DECODE(TEMP.NUM_TERRI, 0,0,TEMP.VENTA / TEMP.NUM_TERRI) VAL_VT,
          DECODE(TEMP.NUM_TERRI, 0,0,TEMP.ACT / TEMP.NUM_TERRI) VAL_ET,
          TEMP.TNOVENTA VAL_TC,
          DECODE(TEMP.NUM_TERRI,0,0,(TEMP.TNOVENTA) / TEMP.NUM_TERRI) * 100 POR_TC,
          TEMP.INGR VAL_M4 ,
          TEMP.REIN VAL_M3,
          TEMP.EGRE VAL_M2,
          TEMP.ACT - (TEMP.INGR + TEMP.REIN + TEMP.EGRE) VAL_M1,
          DECODE( ACT , 0 ,0 ,TEMP.INGR / ACT * 100) POR_M4,
          DECODE( ACT , 0 ,0 ,TEMP.REIN / ACT * 100) POR_M3,
          DECODE( ACT , 0 ,0 ,TEMP.EGRE / ACT * 100)POR_M2,
          DECODE( ACT , 0 ,0 ,(TEMP.ACT - (TEMP.INGR + TEMP.REIN + TEMP.EGRE)) / ACT * 100) POR_M1,
          TEMP.UNON VAL_UNO_N,
          TEMP.DOSN VAL_DOS_N,
          TEMP.TRESN VAL_TRES_N,
          TEMP.CUATRON VAL_CUAT_N,
          DECODE( TEMP.TNOVENTA , 0 ,0 ,TEMP.UNON / (TEMP.ACT) * 100) POR_UNO_N,
          DECODE( TEMP.TNOVENTA , 0 ,0 ,TEMP.DOSN / (TEMP.ACT) * 100) POR_DOS_N,
          DECODE( TEMP.TNOVENTA , 0 ,0 ,TEMP.TRESN / (TEMP.ACT) * 100) POR_TRES_N,
          DECODE( TEMP.TNOVENTA , 0 ,0 ,TEMP.CUATRON / (TEMP.ACT) * 100) POR_CUAT_N,
          TEMP.UNONN    VAL_UNO_NN,
          TEMP.DOSNN    VAL_DOS_NN,
          TEMP.TRESNN   VAL_TRES_NN,
          TEMP.CUATRONN VAL_CUAT_NN,
          DECODE( TEMP.TNOVENTA , 0 ,0 ,TEMP.UNONN / (TEMP.ACT) * 100) POR_UNO_NN,
          DECODE( TEMP.TNOVENTA , 0 ,0 ,TEMP.DOSNN / (TEMP.ACT) * 100) POR_DOS_NN,
          DECODE( TEMP.TNOVENTA , 0 ,0 ,TEMP.TRESNN / (TEMP.ACT) * 100) POR_TRES_NN,
          DECODE( TEMP.TNOVENTA , 0 ,0 ,TEMP.CUATRONN / (TEMP.ACT) * 100) POR_CUAT_NN,
          VEN_FN_OBTIE_TERR_VACI(1, 1,1,1) AS COD_TERR_VAC
    FROM (
  SELECT  S.COD_SECC SEC,
          SUM(int_fuent_venta_real_vacum.IMP_VENT_NETA_ESTA) VENTA,
          SUM(int_fuent_ventas_real.NUM_INGR) INGR,
          SUM(int_fuent_ventas_real.NUM_REIN) REIN,
          SUM(int_fuent_ventas_real.NUM_EGRE) EGRE,
           SUM(CASE WHEN int_fuent_venta_real_vacum.perd_oid_peri=lnIdPeriodoFinal THEN int_fuent_ventas_real.NUM_ACTI_FINA ELSE 0 END) ACT,
             (SELECT count(*) FROM zon_terri_admin w
             WHERE w.zscc_oid_secc=s.oid_secc
             AND   (w.perd_oid_peri_inic IS NULL OR  w.perd_oid_peri_inic>=lnIdPeriodoInicial)
             AND   (w.perd_oid_peri_fina IS NULL OR  w.perd_oid_peri_fina<=lnIdPeriodoFinal) ) NUM_TERRI,
         COUNT(distinct(CASE WHEN int_fuent_venta_real_vacum.perd_oid_peri=lnIdPeriodoFinal THEN zon_terri.COD_TERR  END))  TNOVENTA,
      (SELECT SUM(int_fuent_venta_real_vacum.IMP_VENT_NETA_ESTA)
          FROM  zon_secci,
                zon_terri,
                zon_terri_admin,
                int_fuent_venta_real_vacum
          WHERE (lnIdZona= zon_secci.zzon_oid_zona)
                AND (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc)
                AND (zon_terri.oid_terr = zon_terri_admin.terr_oid_terr)
                AND (lnIdCanal = zon_terri_admin.cana_oid_cana)
                AND (lnIdMarca = zon_terri_admin.marc_oid_marc)
                AND (lnIdPais = zon_terri_admin.pais_oid_pais)
                AND (zon_terri.oid_terr = int_fuent_venta_real_vacum.terr_oid_terr)
                AND int_fuent_venta_real_vacum.perd_oid_peri >=lnIdPeriodoInicial
                AND int_fuent_venta_real_vacum.perd_oid_peri <=lnIdPeriodoFinal

    ) TOTAL_VENTA,
      table_nn.CUATRON,
     table_nn.TRESN,
     table_nn.DOSN,
     table_nn.UNON,
     table_nn.CUATRONN,
     table_nn.TRESNN,
      table_nn.DOSNN,
     table_nn.UNONN
  FROM   zon_secci S,
         zon_terri,
         zon_terri_admin,
         int_fuent_venta_real_vacum,
         int_fuent_ventas_real,
         (SELECT COD_SECC,
                 SUM (CASE WHEN TEMPORAL1.NETO >= 0 AND TEMPORAL1.NETO <= 390 THEN 1 ELSE 0 END) AS CUATRON,
                 SUM (CASE WHEN TEMPORAL1.NETO > 390 AND TEMPORAL1.NETO <= 790  THEN 1 ELSE 0 END) AS TRESN,
                 SUM (CASE WHEN TEMPORAL1.NETO > 790 AND TEMPORAL1.NETO <= 1390 THEN 1 ELSE 0 END) AS DOSN,
                 SUM (CASE WHEN TEMPORAL1.NETO > 1390 THEN 1 ELSE 0 END) AS UNON,
                 SUM (CASE WHEN TEMPORAL1.NETO >= 0 AND TEMPORAL1.NETO <= 440  THEN 1 ELSE 0 END) AS CUATRONN,
                 SUM (CASE WHEN TEMPORAL1.NETO > 440 AND TEMPORAL1.NETO <= 880  THEN 1 ELSE 0 END) AS TRESNN,
                 SUM (CASE WHEN TEMPORAL1.NETO > 880 AND TEMPORAL1.NETO <= 1500 THEN 1 ELSE 0 END) AS DOSNN,
                 SUM (CASE WHEN TEMPORAL1.NETO > 1500  THEN 1 ELSE 0 END) AS UNONN
            FROM
            (
            SELECT zon_secci.cod_secc,
                   ped_solic_cabec.clie_oid_clie,
                sum(ped_solic_posic.VAL_PREC_NETO_TOTA_LOCA) / psTotalTrimestre AS neto
             FROM zon_secci,
                   zon_terri_admin,
                   ped_solic_cabec,
                  ped_solic_posic,
                  pre_ofert,
                  pre_ofert_detal,
                  pre_tipo_ofert,
                  pre_matri_factu_cabec,
                   mae_clien_unida_admin
            WHERE  (( MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE = ped_solic_cabec.clie_oid_clie)
                and (MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI = ZON_TERRI_ADMIN.OID_TERR_ADMI)
                and (ZON_TERRI_ADMIN.ZSCC_OID_SECC = ZON_SECCI.OID_SECC)
                and (MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = 1)
                AND (lnIdCanal = zon_terri_admin.cana_oid_cana)
                AND (lnIdMarca = zon_terri_admin.marc_oid_marc)
                AND (lnIdPais = zon_terri_admin.pais_oid_pais)
                AND (zon_terri_admin.TERR_OID_TERR = ped_solic_cabec.terr_oid_terr)
                AND (ped_solic_cabec.oid_soli_cabe = ped_solic_posic.SOCA_OID_SOLI_CABE)
                and (ped_solic_posic.OFDE_OID_DETA_OFER = pre_ofert_detal.OID_DETA_OFER)
                and (pre_ofert_detal.OFER_OID_OFER = pre_ofert.OID_OFER)
                AND VEN_FN_OBTIE_ULTI_ESTA(ped_solic_cabec.clie_oid_clie,  lnIdPeriodoFinal)<>5
                and (pre_ofert.MFCA_OID_CABE = pre_matri_factu_cabec.OID_CABE)
                AND pre_matri_factu_cabec.PERD_OID_PERI >= lnIdPeriodoInicial
                AND pre_matri_factu_cabec.PERD_OID_PERI <= lnIdPeriodoFinal
                and (pre_ofert_detal.TOFE_OID_TIPO_OFER = pre_tipo_ofert.OID_TIPO_OFER))
                And pre_tipo_ofert.IND_ESTA = 1
                and ped_solic_posic.NUM_UNID_ATEN > 0
              AND (zon_secci.zzon_oid_zona = lnIdZona)
              and (ped_solic_cabec.tspa_oid_tipo_soli_pais = 2037)
            group by zon_secci.cod_secc,
                   ped_solic_cabec.clie_oid_clie
            ) TEMPORAL1
            GROUP BY COD_SECC
              ) table_nn
  WHERE (lnIdZona = S.zzon_oid_zona)
        AND (S.oid_secc = zon_terri_admin.zscc_oid_secc)
        AND (zon_terri.oid_terr = zon_terri_admin.terr_oid_terr)
        AND (lnIdCanal = zon_terri_admin.cana_oid_cana)
        AND (lnIdMarca = zon_terri_admin.marc_oid_marc)
        AND (lnIdPais = zon_terri_admin.pais_oid_pais)
        AND (zon_terri.oid_terr = int_fuent_venta_real_vacum.terr_oid_terr)
        AND (zon_terri.oid_terr = int_fuent_ventas_real.terr_oid_terr)
        AND (int_fuent_venta_real_vacum.perd_oid_peri = int_fuent_ventas_real.perd_oid_peri)
        AND int_fuent_venta_real_vacum.perd_oid_peri >= lnIdPeriodoInicial
        AND int_fuent_venta_real_vacum.perd_oid_peri <= lnIdPeriodoFinal
        AND table_nn.cod_secc = s.cod_secc
  GROUP BY  s.oid_secc,
            s.COD_SECC  ,
            table_nn.CUATRON,
            table_nn.TRESN,
            table_nn.DOSN,
            table_nn.UNON,
            table_nn.CUATRONN,
            table_nn.TRESNN,
            table_nn.DOSNN,
            table_nn.UNONN
  ) TEMP)
    ;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CARGA_EVALU_COBER_ZONA: '||ls_sqlerrm);
END VEN_PR_CARGA_EVALU_COBER_ZONA;
/***************************************************************************
Descripcion       : Para el reporte de Evaluacion de Cobertura y Niceles
                    por Seccion se necesita determinar que consultoras se
                    encontraban cerradas.

Parametros        : psOidCliente Oid del Cliente
                    psOidPeriodo Oid del Periodo
Fecha Creacion    : 02/11/2007
Autor             : José Antonio Cairampoma Granados
***************************************************************************/
FUNCTION VEN_FN_OBTIE_ULTI_ESTA(
    psOidCliente ped_solic_cabec.clie_oid_clie%TYPE,
    psOidPeriodo NUMBER)
    RETURN VARCHAR
    IS
ls_ultEstado  MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
BEGIN

  SELECT DISTINCT FIRST_VALUE(ESTA_OID_ESTA_CLIE)
    OVER (ORDER BY MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI DESC ROWS UNBOUNDED PRECEDING) INTO ls_ultEstado
  FROM MAE_CLIEN_HISTO_ESTAT
  WHERE
        MAE_CLIEN_HISTO_ESTAT.CLIE_OID_CLIE = psOidCliente
    AND MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI <=psOidPeriodo;

  RETURN ls_ultEstado;

 EXCEPTION
  WHEN no_data_found  THEN
     RETURN 0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_ULT_EST: '||ls_sqlerrm);
END VEN_FN_OBTIE_ULTI_ESTA;
/***************************************************************************
Descripcion       :  Para el reporte de EVALUACION DE COBERTRURA Y NIVELES POR
                      SECCION, se necesita saber la lista de los codigos de
                      territorios en los cuales no tubieron venta.
Parametros        : psOidPais Oid del Pais
                    psOidPeriodo Oid del Periodo
                    psOidRegion Oid de la Region
                    psOidZona Oid de la Zona
Fecha Creacion    : 11/04/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_TERR_VACI(
    psOidPais NUMBER,
    psOidPeriodo NUMBER,
    psOidRegion NUMBER,
    psOidZona NUMBER)
    RETURN VARCHAR
    IS
ls_valorVenta  int_fuent_venta_previ_sap.VAL_VENT_NETA_ESTA%TYPE;
BEGIN
     RETURN '';

 RETURN  ls_valorVenta;

 EXCEPTION
  WHEN no_data_found  THEN
     RETURN '';
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_TERR_VACI: '||ls_sqlerrm);
END VEN_FN_OBTIE_TERR_VACI;

/***************************************************************************
Descripcion       : Genera la data para el Reporte de Variables de Venta por
                    Sección
Fecha Creacion    : 08/11/2007
Autor             : José A. Cairampoma
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VARIA_VENTA_SECCI(
    psCodPais VARCHAR2,
    psCodSociedad VARCHAR2,
    psCodAlmacen VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2
)
RETURN tablaVariablesVentaSeccion PIPELINED
IS
  tablaRegistro   tablaVariablesVentaSeccion;
  REG TRegVariablesVentaSeccion;

  CURSOR cursorRegistro( vnIdPais NUMBER, vnIdSociedad NUMBER, vnIdAlmacen NUMBER, vnIdMarca NUMBER, vnIdCanal NUMBER,vnIdPeriodo NUMBER)
  IS
    SELECT COD_ZONA COD_ZONA,
            COD_SECC COD_SECC,
            SUM(VEN_REAL) VEN_REAL,
            SUM(ACT_INIC) ACT_INIC,
            SUM(NUM_INGR) NUM_INGR,
            SUM(NUM_REIN) NUM_REIN,
            SUM(NUM_EGRE) NUM_EGRE,
            DECODE(SUM(ACT_INIC), 0, 0, SUM(NUM_EGRE) * 100 / SUM(ACT_INIC)) POR_EGRE,
            SUM(ACT_FINA) ACT_FINA,
            SUM(CAP) CAP,
            DECODE(SUM(ACT_FINA), 0, 0, SUM(NUM_PEDI) * 100 / SUM(ACT_FINA)) POR_ACTI,
            SUM(NUM_PEDI) NUM_PEDI,
            DECODE(SUM(NUM_PEDI), 0, 0, SUM(VEN_REAL) / SUM(NUM_PEDI)) PRO_SOLE_PEDI,
            SUM(NUM_UNID) NUM_UNID,
            DECODE(SUM(NUM_PEDI), 0, 0, SUM(NUM_UNID) / SUM(NUM_PEDI)) PRO_UNI_PEDI,
            DECODE(SUM(NUM_UNID), 0, 0, SUM(VEN_REAL) / SUM(NUM_UNID)) PPU
       FROM ((SELECT ZONA.COD_ZONA COD_ZONA,
                     SECC.COD_SECC COD_SECC,
                     SUM(NVL(VEAC.IMP_VENT_NETA_ESTA, 0)) VEN_REAL,
                     0 ACT_INIC,
                     0 NUM_INGR,
                     0 NUM_REIN,
                     0 NUM_EGRE,
                     0 ACT_FINA,
                     0 CAP,
                     SUM(NVL(VEAC.NUM_PEDI, 0)) NUM_PEDI,
                     SUM(NVL(NUM_UNID_VEND, 0)) NUM_UNID
                FROM INT_FUENT_VENTA_REAL_VACUM VEAC,
                     ZON_TERRI_ADMIN            TEAD,
                     ZON_SECCI                  SECC,
                     ZON_ZONA                   ZONA,
                     ZON_TERRI                  TERR
               WHERE SECC.OID_SECC = TEAD.ZSCC_OID_SECC
                 AND ZONA.OID_ZONA = SECC.ZZON_OID_ZONA
                 AND VEAC.TERR_OID_TERR = TERR.OID_TERR
                 AND TEAD.PERD_OID_PERI_INIC <= VEAC.PERD_OID_PERI
                 AND (TEAD.PERD_OID_PERI_FINA IS NULL OR
                     TEAD.PERD_OID_PERI_FINA >= VEAC.PERD_OID_PERI)
                 AND TERR.OID_TERR = TEAD.TERR_OID_TERR
                 AND VEAC.ALMC_OID_ALMA = vnIdAlmacen --oidAlmacen
                 AND VEAC.SOCI_OID_SOCI = vnIdSociedad --oidSoc
                 AND VEAC.PERD_OID_PERI = vnIdPeriodo --oidPeriodo
                 AND VEAC.PAIS_OID_PAIS = vnIdPais --oidPais
                 AND TEAD.CANA_OID_CANA = vnIdCanal --oidCana
                 AND TEAD.MARC_OID_MARC = vnIdMarca --oidMarca
               GROUP BY ZONA.COD_ZONA, SECC.COD_SECC, VEAC.ALMC_OID_ALMA)
             UNION (SELECT ZONA.COD_ZONA COD_ZONA,
                           SECC.COD_SECC COD_SECC,
                           0 VEN_REAL,
                           SUM(NVL(VERE.NUM_ACTI_INIC, 0)) ACT_INIC,
                           SUM(NVL(VERE.NUM_INGR, 0)) NUM_INGR,
                           SUM(NVL(VERE.NUM_REIN, 0)) NUM_REIN,
                           SUM(NVL(VERE.NUM_EGRE, 0)) NUM_EGRE,
                           SUM(NVL(VERE.NUM_ACTI_FINA, 0)) ACT_FINA,
                           SUM(NVL(VERE.NUM_INGR, 0) + NVL(VERE.NUM_REIN, 0) -
                               NVL(VERE.NUM_EGRE, 0)) CAP,
                           0 NUM_PEDI,
                           0 NUM_UNID
                      FROM INT_FUENT_VENTAS_REAL VERE,
                           ZON_TERRI_ADMIN       TEAD,
                           ZON_SECCI             SECC,
                           ZON_ZONA              ZONA,
                           ZON_TERRI             TERR
                     WHERE SECC.OID_SECC = TEAD.ZSCC_OID_SECC
                       AND ZONA.OID_ZONA = SECC.ZZON_OID_ZONA
                       AND VERE.TERR_OID_TERR = TERR.OID_TERR
                       AND TEAD.PERD_OID_PERI_INIC <= VERE.PERD_OID_PERI
                       AND (TEAD.PERD_OID_PERI_FINA IS NULL OR
                           TEAD.PERD_OID_PERI_FINA >= VERE.PERD_OID_PERI AND
                           TERR.OID_TERR = TEAD.TERR_OID_TERR)
                       AND VERE.PERD_OID_PERI = vnIdPeriodo --oidPeriodo
                       AND VERE.PAIS_OID_PAIS = vnIdPais --oidPais
                       AND TEAD.CANA_OID_CANA = vnIdCanal --oidCana
                       AND TEAD.MARC_OID_MARC = vnIdMarca --oidMarca
                     GROUP BY ZONA.COD_ZONA, SECC.COD_SECC)) TEMP01
      GROUP BY TEMP01.COD_ZONA, TEMP01.COD_SECC
      ORDER BY TEMP01.COD_ZONA, TEMP01.COD_SECC;

  --Declaracion de variables a usar
    lnIdPais NUMBER;
    lnIdSociedad NUMBER;
    lnIdAlmacen NUMBER;
    lnIdMarca NUMBER;
    lnIdCanal NUMBER;
    lnIdPeriodo NUMBER;
BEGIN

     lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
     lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
     lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
     lnIdSociedad  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_SOCIE(psCodSociedad);
     lnIdPeriodo   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);

     SELECT A.OID_ALMA
     INTO
      lnIdAlmacen
     FROM
       BEL_ALMAC A
     WHERE
       A.PAIS_OID_PAIS = lnIdPais
       AND A.COD_ALMA = psCodAlmacen;

     OPEN cursorRegistro(lnIdPais,lnIdSociedad,lnIdAlmacen,lnIdMarca,lnIdCanal, lnIdPeriodo);
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 REG :=tablaRegistro(x);
                 PIPE ROW(REG);
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro%NOTFOUND ;
     END LOOP ;
     CLOSE cursorRegistro;

   RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_VARIA_VENTA_SECCI: '||ls_sqlerrm);
END VEN_FN_OBTIE_VARIA_VENTA_SECCI;



/***************************************************************************
Descripcion       : Genera la data para el Reporte de Analisis General de Area
Fecha Creacion    : 12/02/2008
Parametros        :
      psCodPais     : Codigo de pais
      psCodSociedad : Codigo de sociedad
      psCodAlmacen  : Codigo de almacen
      psCodMarca    : Codigo de marca
      psCodCanal    : Codigo de Canal
      psCodPeriodo  : Codigo de Periodo
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_OBTIE_ANALI_GENER_AREA(
    psCodPais VARCHAR2,
    psCodSociedad VARCHAR2,
    psCodAlmacen VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodPeriodo VARCHAR2
)
IS

  CURSOR cursorRegistro( vnIdPais NUMBER, vnIdMarca NUMBER, vnIdCanal NUMBER,vnIdPeriodo NUMBER)
  IS
  SELECT DISTINCT
     B.OID_REGI,
     B.COD_REGI,
     B.DES_REGI,
     A.ZZON_OID_ZONA,
     C.COD_ZONA,
     C.DES_ZONA,
     L.COD_SUBG_VENT
  FROM
     INT_FUENT_VENTAS_REAL A,
     ZON_REGIO B,
     ZON_ZONA  C,
     ZON_SUB_GEREN_VENTA L
  WHERE A.PAIS_OID_PAIS = vnIdPais
    AND A.PERD_OID_PERI = vnIdPeriodo
    AND A.ZORG_OID_REGI = B.OID_REGI
    AND A.ZZON_OID_ZONA = C.OID_ZONA
    AND B.MARC_OID_MARC = vnIdMarca
    AND B.CANA_OID_CANA = vnIdCanal
    AND C.PAIS_OID_PAIS = A.PAIS_OID_PAIS
    AND C.MARC_OID_MARC = vnIdMarca
    AND C.CANA_OID_CANA = vnIdCanal
    AND L.OID_SUBG_VENT = B.ZSGV_OID_SUBG_VENT

  ORDER BY
     B.COD_REGI, C.COD_ZONA;

  TYPE t_OID_REGI IS TABLE OF ZON_REGIO.OID_REGI%TYPE ;
  TYPE t_COD_REGI IS TABLE OF ZON_REGIO.COD_REGI%TYPE ;
  TYPE t_DES_REGI IS TABLE OF ZON_REGIO.DES_REGI%TYPE ;
  TYPE t_ZZON_OID_ZONA IS TABLE OF INT_FUENT_VENTAS_REAL.ZZON_OID_ZONA%TYPE ;
  TYPE t_COD_ZONA IS TABLE OF ZON_ZONA.COD_ZONA%TYPE ;
  TYPE t_DES_ZONA IS TABLE OF ZON_ZONA.DES_ZONA%TYPE ;
  TYPE t_COD_SUBG_VENT IS TABLE OF ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE ;


  v_OID_REGI       t_OID_REGI;
  v_COD_REGI       t_COD_REGI;
  v_DES_REGI       t_DES_REGI;
  v_ZZON_OID_ZONA  t_ZZON_OID_ZONA;
  v_COD_ZONA       t_COD_ZONA;
  v_DES_ZONA       t_DES_ZONA;
  v_COD_SUBG_VENT  t_COD_SUBG_VENT;

  --Declaracion de variables a usar
  lnIdPais      NUMBER;
  lnIdSociedad  NUMBER;
  lnIdAlmacen   NUMBER;
  lnIdMarca     NUMBER;
  lnIdCanal     NUMBER;
  lnIdPeriodo   NUMBER;
  ldFechaIniPeriodo   DATE;
  ldFechaFinPeriodo   DATE;
  lnValor             NUMBER;
  lnOidRegi           NUMBER;
  lnOidZona           NUMBER;
  lnUnidadDevuelta    NUMBER;
  lnVentaNetaDevuelta NUMBER;
  lnUnidadAtendida    NUMBER;
  lnValorVentaNeta    NUMBER;
  lnFaltanteNeto      NUMBER;
  lnCantidadClientes  NUMBER;
  lnCantidadPedidos   NUMBER;
  lnIngresos          NUMBER;
  lnReingresos        NUMBER;
  lnEgresos           NUMBER;
  lnActivasFinal      NUMBER;
  lnActivasInicial    NUMBER;
  lnCapitalizacion        NUMBER;
  lnUnidades              NUMBER;
  lnVentaNeta             NUMBER;
  lnPorcentajeActividad   NUMBER;
  lnPromUnidadPedido      NUMBER;
  lnPromSolesPedido       NUMBER;
  lnPPU                   NUMBER;
  lnPorcentajeFaltante    NUMBER;
  lsNombreGerenteRegion   VARCHAR2(200);
  lsNombreGerenteZona     VARCHAR2(200);
  lsMensaje               VARCHAR2(250);
  lsCodLiderRegion        MAE_CLIEN.COD_CLIE%TYPE;
  lsCodLiderZona          MAE_CLIEN.COD_CLIE%TYPE;


BEGIN
  /* borrando tabla temporales */
  DELETE FROM VEN_GTT_ANALI_GENER_DETAL;
  DELETE FROM VEN_GTT_ANALI_GENER_CABEC;

  /* obteniendo id */
  lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  lnIdSociedad  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_SOCIE(psCodSociedad);
  lnIdPeriodo   := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo, lnIdMarca, lnIdCanal);

  SELECT A.OID_ALMA
  INTO
    lnIdAlmacen
  FROM
     BEL_ALMAC A
  WHERE A.PAIS_OID_PAIS = lnIdPais
    AND A.COD_ALMA = psCodAlmacen;

  /* Obteniendo fecha inicial y fecha final del periodo del proceso */
  lnValor := com_pkg_repor.COM_FN_DEVUE_RANGO_FECHA_PERIO(psCodPeriodo, lnIdPais, lnIdMarca, lnIdCanal, ldFechaIniPeriodo, ldFechaFinPeriodo, lsMensaje);
  IF lnValor = -1 THEN
     RAISE_APPLICATION_ERROR(-20123, lsMensaje);
     RETURN;
  END IF;

  /* Grabando oid Zona y Region en la tabla de reporte */
  INSERT INTO VEN_GTT_ANALI_GENER_CABEC(ZORG_OID_REGI, ZZON_OID_ZONA, COD_REGI, COD_ZONA)
  SELECT distinct
     A.ZORG_OID_REGI,
     A.ZZON_OID_ZONA,
     B.COD_REGI,
     C.COD_ZONA
  FROM
     INT_FUENT_VENTAS_REAL A,
     ZON_REGIO B,
     ZON_ZONA  C
  WHERE A.PAIS_OID_PAIS = lnIdPais
    AND A.PERD_OID_PERI = lnIdPeriodo
    AND A.ZORG_OID_REGI = B.OID_REGI
    AND A.ZZON_OID_ZONA = C.OID_ZONA
    AND B.MARC_OID_MARC = lnIdMarca
    AND B.CANA_OID_CANA = lnIdCanal
    AND C.PAIS_OID_PAIS = A.PAIS_OID_PAIS
    AND C.MARC_OID_MARC = lnIdMarca
    AND C.CANA_OID_CANA = lnIdCanal;

  /* Pasando universo inicial de ped_solic_cabec a tabla temporal por tema de performance */
  INSERT INTO VEN_GTT_SOLIC_CABEC (
          OID_SOLI_CABE, FEC_PROG_FACT,
          FEC_FACT, NUM_CLIEN,
          VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
          MONE_OID_MONE, TIDS_OID_TIPO_DESP,
          ALMC_OID_ALMA, MODU_OID_MODU,
          TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
          PERD_OID_PERI, SOCA_OID_SOLI_CABE,
          CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
          CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
          CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
          SOCI_OID_SOCI, SBAC_OID_SBAC,
          TERR_OID_TERR, ZZON_OID_ZONA,
          IND_ESTA, IND_IMPR,
          IND_EXEN_FLET, VAL_NUME_SOLI,
          VAL_USUA, VAL_TASA_IMPU,
          FEC_CRON, IND_PERM_UNIO_SOL,
          IND_GENE_CC, IND_APLI_MANU,
          VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
          NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
          FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
          VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
          VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
          VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
          VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
          VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
          VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
          VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
          VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
          VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
          VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
          VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
          VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
          VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
          VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
          VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
          VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
          VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
          VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
          VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
          VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
          VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
          NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
          IND_OC, IND_PEDI_PRUE,
          IND_TS_NO_CONSO, VAL_GLOS_OBSE,
          VAL_OBSE_REVI, NUM_PREM,
          VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
          PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
          VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
          ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
          GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
          ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
          FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
          ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
          ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
          OPER_OID_OPER, PROC_OID_PROC,
          SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
          CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
          NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
          IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
          )
       SELECT
          OID_SOLI_CABE, FEC_PROG_FACT,
          FEC_FACT, NUM_CLIEN,
          VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
          MONE_OID_MONE, TIDS_OID_TIPO_DESP,
          ALMC_OID_ALMA, MODU_OID_MODU,
          TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
          PERD_OID_PERI, SOCA_OID_SOLI_CABE,
          CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
          CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
          CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
          SOCI_OID_SOCI, SBAC_OID_SBAC,
          TERR_OID_TERR, A.ZZON_OID_ZONA,
          IND_ESTA, IND_IMPR,
          IND_EXEN_FLET, VAL_NUME_SOLI,
          VAL_USUA, VAL_TASA_IMPU,
          FEC_CRON, IND_PERM_UNIO_SOL,
          IND_GENE_CC, IND_APLI_MANU,
          VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
          NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
          FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
          VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
          VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
          VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
          VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
          VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
          VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
          VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
          VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
          VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
          VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
          VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
          VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
          VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
          VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
          VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
          VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
          VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
          VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
          VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
          VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
          VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
          NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
          IND_OC, IND_PEDI_PRUE,
          IND_TS_NO_CONSO, VAL_GLOS_OBSE,
          VAL_OBSE_REVI, NUM_PREM,
          VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
          PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
          VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
          ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
          GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
          ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
          FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
          ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
          ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
          OPER_OID_OPER, PROC_OID_PROC,
          SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
          CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
          NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
          IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
       FROM
            PED_SOLIC_CABEC A
         WHERE  A.PERD_OID_PERI = lnIdPeriodo
            AND A.FEC_FACT IS NOT NULL
            AND A.IND_TS_NO_CONSO = 1
            AND A.IND_OC = 1
            AND A.IND_PEDI_PRUE = 0
            AND A.PAIS_OID_PAIS = lnIdPais
            AND A.SOCI_OID_SOCI = lnIdSociedad
            AND A.ALMC_OID_ALMA = lnIdAlmacen;


  /* obteniendo valores de ped_solic_cabec */
  INSERT INTO VEN_GTT_ANALI_GENER_DETAL
  (OID_SOLI_CABE, ZORG_OID_REGI, ZZON_OID_ZONA, NUM_CLIEN,
   NUM_UNID_ATEN, VAL_PREC_NETO_TOTA_LOCA, NUM_UNID_DEMA_REAL,
   NUM_UNID_COMPR, VAL_PREC_NETO_UNIT_LOCA)
  SELECT
     A.OID_SOLI_CABE,
     T.ZORG_OID_REGI,
     T.ZZON_OID_ZONA,
     A.NUM_CLIEN,
     D.NUM_UNID_ATEN,
     D.VAL_PREC_NETO_TOTA_LOCA,
     D.NUM_UNID_DEMA_REAL,
     D.NUM_UNID_COMPR,
     D.VAL_PREC_NETO_UNIT_LOCA
  FROM
     VEN_GTT_SOLIC_CABEC A,
     PED_TIPO_SOLIC_PAIS B,
     PED_TIPO_SOLIC  C,
     PED_SOLIC_POSIC D,
     PED_ESTAD_SOLIC E,
     VEN_GTT_ANALI_GENER_CABEC T

  WHERE  A.ZZON_OID_ZONA = T.ZZON_OID_ZONA
     AND C.MARC_OID_MARC = lnIdMarca
     AND C.IND_DEVO = 0
     AND C.IND_ANUL = 0
     AND A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
     AND C.OID_TIPO_SOLI = B.TSOL_OID_TIPO_SOLI

     AND A.OID_SOLI_CABE = D.SOCA_OID_SOLI_CABE

     AND E.COD_ESTA_SOLI <> 'AN'
     AND E.OID_ESTA_SOLI = A.ESSO_OID_ESTA_SOLI;



  /* Recorriendo cursor */
  lnOidRegi := -1;
  lnOidZona := -1;
  OPEN cursorRegistro(lnIdPais, lnIdMarca,lnIdCanal, lnIdPeriodo);
  LOOP
     FETCH cursorRegistro BULK COLLECT INTO
           v_OID_REGI, v_COD_REGI, v_DES_REGI,
           v_ZZON_OID_ZONA, v_COD_ZONA, v_DES_ZONA,
           v_COD_SUBG_VENT
           LIMIT W_FILAS;

     IF v_OID_REGI.COUNT > 0 THEN
       FOR x IN v_OID_REGI.FIRST .. v_OID_REGI.LAST LOOP
           /* obteniendo Gerente de Region */
           IF lnOidRegi <> v_OID_REGI(x) THEN
              lnOidRegi := v_OID_REGI(x);
              lsNombreGerenteRegion := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HISTO(
                                         lsCodLiderRegion,
                                         ldFechaIniPeriodo, ldFechaFinPeriodo,
                                         lnIdPais, v_COD_SUBG_VENT(x),
                                         v_COD_REGI(x),
                                         NULL,
                                         NULL);
           END IF;
           /* obteniendo Gerente de Zona */
           IF lnOidZona <> v_ZZON_OID_ZONA(x) THEN
              lnOidZona := v_ZZON_OID_ZONA(x);
              lsNombreGerenteZona := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HISTO(
                                         lsCodLiderZona,
                                         ldFechaIniPeriodo, ldFechaFinPeriodo,
                                         lnIdPais,  v_COD_SUBG_VENT(x),
                                         v_COD_REGI(x),
                                         v_COD_ZONA(x),
                                         NULL);
           END IF;

           /* obteniendo acumulados iniciales */
           BEGIN
             SELECT nvl(SUM(A.NUM_UNID_ATEN),0),
                    nvl(SUM(A.VAL_PREC_NETO_TOTA_LOCA),0),
                    nvl(SUM((A.NUM_UNID_DEMA_REAL - A.NUM_UNID_COMPR) * A.VAL_PREC_NETO_UNIT_LOCA),0),
                    nvl(SUM(A.NUM_CLIEN),0),
                    COUNT(1)
             INTO
                   lnUnidadAtendida,
                   lnValorVentaNeta,
                   lnFaltanteNeto,
                   lnCantidadClientes,
                   lnCantidadPedidos
             FROM VEN_GTT_ANALI_GENER_DETAL A
             WHERE A.ZORG_OID_REGI = v_OID_REGI(X)
               AND A.ZZON_OID_ZONA = v_ZZON_OID_ZONA(x);
           EXCEPTION
           WHEN no_data_found THEN
                lnUnidadAtendida := 0;
                lnValorVentaNeta := 0;
                lnFaltanteNeto   := 0;
                lnCantidadClientes := 0;
                lnCantidadPedidos  := 0;
           END;

           /* Obteniendo unidades devueltas */
           BEGIN
             SELECT nvl(SUM(D.NUM_UNID_ATEN),0),
                    nvl(SUM(D.VAL_PREC_TOTA_TOTA_LOCA),0)
             INTO  lnUnidadDevuelta,
                   lnVentaNetaDevuelta

             FROM VEN_GTT_ANALI_GENER_DETAL T,
                  PED_SOLIC_CABEC A,
                  PED_TIPO_SOLIC_PAIS B,
                  PED_TIPO_SOLIC  C,
                  PED_SOLIC_POSIC D
             WHERE T.OID_SOLI_CABE = A.SOCA_OID_DOCU_REFE
               AND C.IND_DEVO = 1
               AND A.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
               AND C.OID_TIPO_SOLI = B.TSOL_OID_TIPO_SOLI
               AND A.OID_SOLI_CABE = D.SOCA_OID_SOLI_CABE
             GROUP BY T.ZORG_OID_REGI, T.ZZON_OID_ZONA;
          EXCEPTION
          WHEN no_data_found THEN
               lnUnidadDevuelta := 0;
               lnVentaNetaDevuelta := 0;
          END;

          /* obteniendo acumulados de INT_FUENT_VENTA_REAL_VACUM */
          BEGIN
             SELECT
                 nvl(SUM(A.NUM_INGR),0),
                 nvl(SUM(A.NUM_REIN),0),
                 nvl(SUM(A.NUM_EGRE),0)
             INTO
                 lnIngresos,
                 lnReingresos,
                 lnEgresos
             FROM
                 INT_FUENT_VENTAS_REAL A
             WHERE   A.PAIS_OID_PAIS = lnIdPais
                 AND A.PERD_OID_PERI = lnIdPeriodo
                 AND A.ZZON_OID_ZONA = v_ZZON_OID_ZONA(x);
          EXCEPTION
          WHEN no_data_found THEN
               lnIngresos := 0;
               lnReingresos := 0;
               lnEgresos := 0;
          END;

          /* obteniendo acumulados de INT_FUENT_VENTA_REAL_VACUM */
          BEGIN
             SELECT
                 nvl(SUM(A.NUM_ACTI_FINA),0),
                 nvl(SUM(A.NUM_ACTI_INIC),0)
             INTO
                 lnActivasFinal,
                 lnActivasInicial
             FROM
                 INT_FUENT_VENTAS_REAL A
             WHERE   A.PAIS_OID_PAIS = lnIdPais
                 AND A.PERD_OID_PERI = lnIdPeriodo
                 AND A.ZZON_OID_ZONA = v_ZZON_OID_ZONA(x);
          EXCEPTION
          WHEN no_data_found THEN
               lnActivasFinal := 0;
               lnActivasInicial := 0;
          END;

          lnCapitalizacion :=  lnIngresos + lnReingresos - lnEgresos;
          lnUnidades := lnUnidadAtendida - lnUnidadDevuelta;
          lnVentaNeta := lnValorVentaNeta - lnVentaNetaDevuelta;
          lnPorcentajeActividad := 0;
          IF (lnActivasFinal > 0) THEN
              lnPorcentajeActividad := lnCantidadPedidos * 100 / lnActivasFinal;
          END IF;
          lnPromUnidadPedido := 0;
          lnPromSolesPedido  := 0;
          IF (lnCantidadPedidos > 0) THEN
              lnPromUnidadPedido := lnUnidades / lnCantidadPedidos;
              lnPromSolesPedido  := lnVentaNeta / lnCantidadPedidos;
          END IF;
          lnPPU := 0;
          IF (lnUnidades > 0) THEN
              lnPPU := lnVentaNeta / lnUnidades;
          END IF;
          lnPorcentajeFaltante := 0;
          IF (lnFaltanteNeto + lnVentaNeta > 0) THEN
             lnPorcentajeFaltante := (lnFaltanteNeto * 100) / (lnFaltanteNeto + lnVentaNeta);
          END IF;

          /* actualizando en la tabla del reporte */
          UPDATE VEN_GTT_ANALI_GENER_CABEC A
          SET
             NOM_GERE_REGI = lsNombreGerenteRegion,
             NOM_GERE_ZONA = lsNombreGerenteZona,
             NUM_INGR = lnIngresos,
             NUM_REIN = lnReingresos,
             NUM_ACTI_FINA = lnActivasFinal,
             NUM_ACTI_INIC = lnActivasInicial,
             VAL_CAPI = lnCapitalizacion,
             NUM_PEDI = lnCantidadPedidos,
             NUM_UNID = lnUnidades,
             NUM_CLIE = lnCantidadClientes,
             NUM_VENT_NETA = lnVentaNeta,
             POR_ACTI = lnPorcentajeActividad,
             VAL_PROM_UNID_PEDI = lnPromUnidadPedido,
             VAL_PROM_SOLE_PEDI = lnPromSolesPedido,
             VAL_PPU = lnPPU,
             VAL_MONT_FALT = lnFaltanteNeto,
             POR_FALT = lnPorcentajeFaltante

          WHERE A.ZORG_OID_REGI = v_OID_REGI(X)
            AND A.ZZON_OID_ZONA = v_ZZON_OID_ZONA(x);



       END LOOP;
     END IF;
     EXIT WHEN cursorRegistro%NOTFOUND ;
  END LOOP ;
  CLOSE cursorRegistro;


  RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_OBTIE_ANALI_GENER_AREA: '||ls_sqlerrm);
END VEN_PR_OBTIE_ANALI_GENER_AREA;

/***************************************************************************
Descripcion       : Genera la data para el Reporte de Venta Variable de
                    la tabla Historica
Fecha Creacion    : 15/04/2009
Autor             : Telly Tataje
***************************************************************************/
PROCEDURE VEN_PR_CARGA_HISTO_VENTA_CATAL(
    psCodPais VARCHAR2,
    psCodSociedad VARCHAR2,
    psCodAlmacen VARCHAR2,
    psCodMarca VARCHAR2,
    psCodCanal VARCHAR2,
    psCodCampanhia VARCHAR2
)
IS
  lnIdPais                    seg_pais.oid_pais%TYPE;
  lnIdCanal                   seg_canal.oid_cana%TYPE;
  lnIdMarca                   seg_marca.oid_marc%TYPE;
  lnIdSociedad                seg_socie.oid_soci%TYPE;
  lnIdAlmacen                 bel_almac.oid_alma%TYPE;
  lnNroCampana                NUMBER;
  TYPE tRegHisto IS RECORD (
     COD_CAMP       VEN_HISTO_VENTA_CATAL.COD_CAMP%TYPE,
     COD_TERR       VEN_HISTO_VENTA_CATAL.COD_TERR%TYPE,
     COD_ALMA       VEN_HISTO_VENTA_CATAL.COD_ALMA%TYPE,
     COD_ZONA       VEN_HISTO_VENTA_CATAL.COD_ZONA%TYPE,
     COD_REGI       VEN_HISTO_VENTA_CATAL.COD_REGI%TYPE,
     COD_SECC       VEN_HISTO_VENTA_CATAL.COD_SECC%TYPE,
     OID_TERR       VEN_HISTO_VENTA_CATAL.OID_TERR%TYPE,
     OID_ZONA       VEN_HISTO_VENTA_CATAL.OID_ZONA%TYPE,
     OID_SECC       VEN_HISTO_VENTA_CATAL.OID_SECC%TYPE,
     OID_TERR_ADMI  VEN_HISTO_VENTA_CATAL.OID_TERR_ADMI%TYPE,
     NUM_UNID_VEN   VEN_HISTO_VENTA_CATAL.NUM_UNID_VEN%TYPE,
     MON_NETO       VEN_HISTO_VENTA_CATAL.MON_NETO%TYPE
  );
  TYPE TABLA_HISTO IS TABLE OF tRegHisto ;
  RegHisto     tRegHisto;
  TablaHisto   TABLA_HISTO;
  RegHistorial VEN_HISTO_VENTA_CATAL%ROWTYPE;

  CURSOR cHistorial(pnIdPais NUMBER,
                    pnIdSociedad NUMBER,
                    pnIdAlmacen NUMBER,
                    pnNroCampana NUMBER,
                    pnIdMarca NUMBER,
                    pnIdCanal NUMBER)
  IS
   SELECT
       A.PERD_OID_PERI COD_CAMP,
       T.COD_TERR,
       psCodAlmacen COD_ALMA,
       Z.COD_ZONA,
       R.COD_REGI,
       ZS.COD_SECC,
       T.OID_TERR,
       Z.OID_ZONA,
       ZS.OID_SECC,
       A.ZTAD_OID_TERR_ADMI OID_TERR_ADMIN,
    SUM(D.NUM_UNID_ATEN),
       SUM(CASE WHEN D.VAL_PREC_CATA_UNIT_LOCA > 0 THEN D.VAL_PREC_NETO_TOTA_LOCA ELSE 0 END)
     FROM
        VEN_GTT_SOLIC_CABEC A,
        PED_TIPO_SOLIC_PAIS B,
        INT_PARAM_TIPO_SOLIC I,
        PED_TIPO_SOLIC  C,
        PED_SOLIC_POSIC D,
        PRE_OFERT_DETAL S,
        PRE_TIPO_OFERT  W,
        ZON_ZONA Z,
        ZON_TERRI T,
        ZON_REGIO R,
        ZON_TERRI_ADMIN TA,
        ZON_SECCI ZS
     WHERE C.IND_DEVO = 0
           AND B.OID_TIPO_SOLI_PAIS = A.TSPA_OID_TIPO_SOLI_PAIS
           AND I.TSPA_OID_TIPO_SOLI_PAIS = B.OID_TIPO_SOLI_PAIS
           AND C.OID_TIPO_SOLI = B.TSOL_OID_TIPO_SOLI
           AND D.SOCA_OID_SOLI_CABE = A.OID_SOLI_CABE
           AND S.OID_DETA_OFER = D.OFDE_OID_DETA_OFER
           AND W.OID_TIPO_OFER = S.TOFE_OID_TIPO_OFER
           AND Z.OID_ZONA = A.ZZON_OID_ZONA
           AND T.OID_TERR = A.TERR_OID_TERR
           AND R.OID_REGI = Z.ZORG_OID_REGI
           AND TA.OID_TERR_ADMI = A.ZTAD_OID_TERR_ADMI
           AND ZS.OID_SECC = TA.ZSCC_OID_SECC
     AND ZS.ZZON_OID_ZONA = Z.OID_ZONA
           AND C.IND_ANUL = 0
           AND C.MARC_OID_MARC = pnIdMarca
           AND I.NUM_UNID_VEND = 1
           AND D.ESPO_OID_ESTA_POSI <> 2
           AND D.VAL_CODI_VENT IS NOT NULL
           AND W.VAL_FORM_VENT = 1
     GROUP BY
            PERD_OID_PERI,
            COD_TERR,
            COD_ZONA,
            COD_REGI,
            COD_SECC,
            OID_TERR,
            OID_ZONA,
            OID_SECC,
            ZTAD_OID_TERR_ADMI;
BEGIN

   /* obteniendos ids */
   lnIdPais      := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
   lnIdCanal     := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
   lnIdMarca     := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
   lnIdSociedad  := gen_pkg_gener.GEN_FN_DEVUELVE_ID_SOCIE(psCodSociedad);


   lnNroCampana := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO( psCodCampanhia,lnIdMarca,lnIdCanal);--con este oid de
   --procedera a borrar los registros en el historico de catalogos para volver a cargarlos
   lnIdAlmacen  :=  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ALMAC(psCodAlmacen,lnIdPais);



  /* Borrando tabla temporal */
  DELETE FROM VEN_GTT_SOLIC_CABEC;

       INSERT INTO VEN_GTT_SOLIC_CABEC (
          OID_SOLI_CABE, FEC_PROG_FACT,
          FEC_FACT, NUM_CLIEN,
          VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
          MONE_OID_MONE, TIDS_OID_TIPO_DESP,
          ALMC_OID_ALMA, MODU_OID_MODU,
          TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
          PERD_OID_PERI, SOCA_OID_SOLI_CABE,
          CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
          CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
          CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
          SOCI_OID_SOCI, SBAC_OID_SBAC,
          TERR_OID_TERR, ZZON_OID_ZONA,
          IND_ESTA, IND_IMPR,
          IND_EXEN_FLET, VAL_NUME_SOLI,
          VAL_USUA, VAL_TASA_IMPU,
          FEC_CRON, IND_PERM_UNIO_SOL,
          IND_GENE_CC, IND_APLI_MANU,
          VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
          NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
          FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
          VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
          VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
          VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
          VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
          VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
          VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
          VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
          VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
          VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
          VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
          VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
          VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
          VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
          VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
          VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
          VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
          VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
          VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
          VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
          VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
          VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
          NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
          IND_OC, IND_PEDI_PRUE,
          IND_TS_NO_CONSO, VAL_GLOS_OBSE,
          VAL_OBSE_REVI, NUM_PREM,
          VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
          PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
          VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
          ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
          GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
          ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
          FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
          ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
          ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
          OPER_OID_OPER, PROC_OID_PROC,
          SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
          CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
          NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
          IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
          )
       SELECT
          OID_SOLI_CABE, FEC_PROG_FACT,
          FEC_FACT, NUM_CLIEN,
          VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
          MONE_OID_MONE, TIDS_OID_TIPO_DESP,
          ALMC_OID_ALMA, MODU_OID_MODU,
          TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
          PERD_OID_PERI, SOCA_OID_SOLI_CABE,
          CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
          CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
          CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
          SOCI_OID_SOCI, SBAC_OID_SBAC,
          TERR_OID_TERR, ZZON_OID_ZONA,
          IND_ESTA, IND_IMPR,
          IND_EXEN_FLET, VAL_NUME_SOLI,
          VAL_USUA, VAL_TASA_IMPU,
          FEC_CRON, IND_PERM_UNIO_SOL,
          IND_GENE_CC, IND_APLI_MANU,
          VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
          NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
          FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
          VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
          VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
          VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
          VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
          VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
          VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
          VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
          VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
          VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
          VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
          VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
          VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
          VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
          VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
          VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
          VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
          VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
          VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
          VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
          VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
          VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
          NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
          IND_OC, IND_PEDI_PRUE,
          IND_TS_NO_CONSO, VAL_GLOS_OBSE,
          VAL_OBSE_REVI, NUM_PREM,
          VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
          PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
          VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
          ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
          GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
          ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
          FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
          ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
          ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
          OPER_OID_OPER, PROC_OID_PROC,
          SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
          CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
          NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
          IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
       FROM
            PED_SOLIC_CABEC A
         WHERE  A.PAIS_OID_PAIS = lnIdPais
            AND A.SOCI_OID_SOCI = lnIdSociedad
            AND A.ALMC_OID_ALMA = lnIdAlmacen
            AND A.PERD_OID_PERI = lnNroCampana
            AND A.FEC_FACT IS NOT NULL
            AND A.IND_TS_NO_CONSO = 1
            AND A.IND_OC = 1
            AND A.IND_PEDI_PRUE = 0;

   /* Borrando registros de la misma campaña */
   DELETE FROM VEN_HISTO_VENTA_CATAL WHERE COD_CAMP = psCodCampanhia;

   /* Generando informacion */
   OPEN cHistorial(lnIdPais, lnIdSociedad, lnIdAlmacen, lnNroCampana, lnIdMarca, lnIdCanal );
   LOOP
     FETCH cHistorial BULK COLLECT INTO tablaHisto LIMIT W_FILAS;
     IF tablaHisto.COUNT > 0 THEN
         FOR x IN tablaHisto.FIRST .. tablaHisto.LAST LOOP
             RegHisto := tablaHisto(x);
             RegHistorial.COD_CAMP       := psCodCampanhia;
             RegHistorial.COD_TERR       := RegHisto.COD_TERR;
             RegHistorial.COD_ALMA       := RegHisto.COD_ALMA;
             RegHistorial.COD_ZONA       := RegHisto.COD_ZONA;
             RegHistorial.COD_REGI       := RegHisto.COD_REGI;
             RegHistorial.COD_SECC       := RegHisto.COD_SECC;
             RegHistorial.OID_TERR       := RegHisto.OID_TERR;
             RegHistorial.OID_ZONA       := RegHisto.OID_ZONA;
             RegHistorial.OID_SECC       := RegHisto.OID_SECC;
             RegHistorial.OID_TERR_ADMI  := RegHisto.OID_TERR_ADMI;
             RegHistorial.NUM_UNID_VEN   := RegHisto.NUM_UNID_VEN;
             RegHistorial.MON_NETO       := RegHisto.MON_NETO;

             /* Venta Estimada, Ingresos Estimados, Reingresos estimados,
                Egresos Estimados, Pedidos estimados, Unidades Estimadas */

             /* Pasandolo al table */
             INSERT INTO VEN_HISTO_VENTA_CATAL
             VALUES  RegHistorial;
         END LOOP;
     END IF;
     EXIT WHEN cHistorial%NOTFOUND;
   END LOOP;
   CLOSE cHistorial;
   RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CARGA_HIST_VENTA_CAT: '||ls_sqlerrm);
END VEN_PR_CARGA_HISTO_VENTA_CATAL;

/***************************************************************************
Descripcion       : Inserta la data para el Reporte de Factura x Auditar
                    (Consolidado)
Fecha Creacion    : 12/10/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_REPOR_FACTU_AUDIT_CONSO(
    psFechaDesde VARCHAR2,
    psFechaHasta VARCHAR2
)
IS
 
BEGIN
  
  /* Borrando tabla temporal */
  EXECUTE IMMEDIATE 'truncate table VEN_REPOR_FACTU_AUDIT_CONSO';
  
  VEN_PR_REPOR_FACTU_AUDIT_DETAL(psFechaDesde, psFechaHasta);
  
  /* insertando tabla temporal */
  INSERT INTO VEN_REPOR_FACTU_AUDIT_CONSO(
    DES_TIPO_DOCU, 
    COD_CLIE, 
    VAL_NOMB_CLIE, 
    FEC_FACT, 
    VAL_NUME_SOLI,
    COD_PERI, 
    VAL_TOTA_VENT
  )
  SELECT  
    DES_TIPO_DOCU, 
    COD_CLIE, 
    VAL_NOMB_CLIE, 
    FEC_FACT, 
    VAL_NUME_SOLI,
    COD_PERI, 
    SUM(VAL_TOTA_VENT)
  FROM   
    VEN_REPOR_FACTU_AUDIT_DETAL
  GROUP BY 
    DES_TIPO_DOCU, 
    COD_CLIE, 
    VAL_NOMB_CLIE, 
    FEC_FACT, 
    VAL_NUME_SOLI,
    COD_PERI; 
  RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_REPOR_FACTU_AUDIT_CONSO: '||ls_sqlerrm);
END VEN_PR_REPOR_FACTU_AUDIT_CONSO;


/***************************************************************************
Descripcion       : Inserta la data para el Reporte de Factura x Auditar
                    (Detallado)
Fecha Creacion    : 12/10/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE VEN_PR_REPOR_FACTU_AUDIT_DETAL(
    psFechaDesde VARCHAR2,
    psFechaHasta VARCHAR2
)
IS
 
BEGIN
  
  /* Borrando tabla temporal */
  EXECUTE IMMEDIATE 'truncate table VEN_REPOR_FACTU_AUDIT_DETAL';

  /* insertando tabla temporal */
  INSERT INTO VEN_REPOR_FACTU_AUDIT_DETAL(
    DES_TIPO_DOCU, 
    COD_CLIE, 
    VAL_NOMB_CLIE, 
    FEC_FACT, 
    NUM_DOCU_CONT_INTE, 
    VAL_NUME_SOLI,
    COD_CUV, 
    COD_SAP, 
    VAL_NOMB_PROD, 
    NUM_UNID_ATEN, 
    VAL_PREC_FACT, 
    COD_PERI, 
    VAL_TOTA_VENT
  )
  SELECT 
        m.DES_TIPO_DOCU, 
        l.COD_CLIE,
        trim(l.VAL_NOM1) || ' ' || trim(l.VAL_NOM2) || ' ' || trim(l.VAL_APE1) || ' ' || trim(l.VAL_APE2) NOMBRE,
        i.FEC_FACT,
        i.NUM_DOCU_CONT_INTE,
        j.val_nume_soli,
        nvl(z.val_codi_vent, to_char(z.val_codi_vent_fict)) COD_CUV,
        f.COD_SAP, 
        h.VAL_I18N VAL_NOMB_PROD,
        n.NUM_UNID_ATEN, 
        decode(n.VAL_PREC_CATA_UNIT_LOCA,0,0,n.VAL_PREC_FACT_UNIT_LOCA) VAL_PREC,
        c.COD_PERI PERIODO, 
        n.NUM_UNID_ATEN * decode(n.VAL_PREC_CATA_UNIT_LOCA,0,0,n.VAL_PREC_FACT_UNIT_LOCA) TOTAL_VENTA  
  FROM  cra_perio b, 
        seg_perio_corpo c, 
        fac_docum_conta_cabec i, 
        ped_solic_cabec j, 
        mae_clien l, 
        fac_tipo_docum m, 
        fac_docum_conta_linea n, 
        mae_produ f, 
        gen_i18n_sicc_pais h, 
        ped_solic_posic z
   WHERE  i.FEC_FACT >= TO_DATE (psFechaDesde,'DD/MM/YYYY')  
      AND i.FEC_FACT<= TO_DATE (psFechaHasta,'DD/MM/YYYY')
      AND i.TIDO_OID_TIPO_DOCU=1
      AND n.sopo_oid_soli_posi=z.oid_soli_posi
      AND i.SOCA_OID_SOLI_CABE=j.OID_SOLI_CABE
      AND j.CLIE_OID_CLIE=l.OID_CLIE
      AND i.TIDO_OID_TIPO_DOCU=m.OID_TIPO_DOCU
      AND i.OID_CABE=n.DCCA_OID_CABE 
      AND i.PERD_OID_PERI=b.OID_PERI
      AND b.PERI_OID_PERI=c.OID_PERI 
      AND n.PROD_OID_PROD=f.OID_PROD
      AND f.OID_PROD=h.VAL_OID 
      AND h.ATTR_ENTI='MAE_PRODU'
      AND n.NUM_UNID_ATEN <> 0;

  RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_REPOR_FACTU_AUDIT_DETAL: '||ls_sqlerrm);
END VEN_PR_REPOR_FACTU_AUDIT_DETAL;


END VEN_PKG_REPOR;
/
