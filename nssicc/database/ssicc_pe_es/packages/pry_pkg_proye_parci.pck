CREATE OR REPLACE PACKAGE "PRY_PKG_PROYE_PARCI" IS

  FUNCTION PRY_FN_NUMER_PEDID_ESTIM(PS_COD_PERI IN VARCHAR2,
                                    PS_COD_REGI IN VARCHAR2,
                                    PS_COD_ZONA IN VARCHAR2) RETURN NUMBER;

  FUNCTION PRY_FN_INDIC_CIERR(PS_COD_PERI IN VARCHAR2,
                              PS_COD_REGI IN VARCHAR2,
                              PS_COD_PAIS IN VARCHAR2) RETURN VARCHAR2;

TYPE tRegProyeccionFaltanteDetalle IS RECORD (
  COD_NEGO                      FAC_PROYE_FALTA_NEGOC.COD_NEGO%TYPE,
  DES_NEGO                        FAC_PROYE_FALTA_NEGOC.DES_NEGO%TYPE,
  NUM_UNI_STOC_DISPO              FAC_PROYE_FALTA_NEGOC.NUM_UNI_STOC_DISPO%TYPE,
  TOT_UNID_DEMA                   FAC_PROYE_FALTA_NEGOC.TOT_UNID_DEMA%TYPE,
  TOT_UNID_ATEN                   FAC_PROYE_FALTA_NEGOC.TOT_UNID_ATEN%TYPE,
  TOT_UNID_FALT                   FAC_PROYE_FALTA_NEGOC.TOT_UNID_FALT%TYPE,
  POR_FALT_UNID_DEMA              FAC_PROYE_FALTA_NEGOC.POR_FALT_UNID_DEMA%TYPE,
  TOT_MONT_UNID_DEMA              FAC_PROYE_FALTA_NEGOC.TOT_MONT_UNID_DEMA%TYPE,
  TOT_MONT_UNID_ATEN              FAC_PROYE_FALTA_NEGOC.TOT_MONT_UNID_ATEN%TYPE,
  TOT_MONT_UNID_FALT              FAC_PROYE_FALTA_NEGOC.TOT_MONT_UNID_FALT%TYPE,
  POR_FALT_MONT_DEMA              FAC_PROYE_FALTA_NEGOC.POR_FALT_MONT_DEMA%TYPE,
  POR_FALT_TOTA_FACT              FAC_PROYE_FALTA_NEGOC.POR_FALT_TOTA_FACT%TYPE,
  VAL_PORC_MAXI_PERM              BAS_PARAM_PORCE_FALTA.VAL_PORC_MAXI_PERM%TYPE,
  IND_VALO_CRIT                   FAC_PROYE_FALTA_NEGOC.IND_VALO_CRIT%TYPE,
  COD_PERI                        FAC_PROYE_FALTA_NEGOC.COD_PERI%TYPE,
  FEC_FACT                        FAC_PROYE_FALTA_NEGOC.FEC_FACT%TYPE,
  COD_PROD                        FAC_PROYE_FALTA_PRODU.COD_PROD%TYPE,
  DES_PROD                        FAC_PROYE_FALTA_PRODU.DES_PROD%TYPE,
  COD_MARC_PROD                   FAC_PROYE_FALTA_PRODU.COD_MARC_PROD%TYPE,
  DES_MARC_PROD                   FAC_PROYE_FALTA_PRODU.DES_MARC_PROD%TYPE,
  NUM_UNI_STOC_DISPO_PROD         FAC_PROYE_FALTA_PRODU.NUM_UNI_STOC_DISPO%TYPE,
  TOT_UNID_DEMA_PROD              FAC_PROYE_FALTA_PRODU.TOT_UNID_DEMA%TYPE,
  TOT_UNID_ATEN_PROD              FAC_PROYE_FALTA_PRODU.TOT_UNID_ATEN%TYPE,
  TOT_UNID_FALT_PROD              FAC_PROYE_FALTA_PRODU.TOT_UNID_FALT%TYPE,
  POR_FALT_UNID_DEMA_PROD         FAC_PROYE_FALTA_PRODU.POR_FALT_UNID_DEMA%TYPE,
  TOT_MONT_UNID_DEMA_PROD         FAC_PROYE_FALTA_PRODU.TOT_MONT_UNID_DEMA%TYPE,
  TOT_MONT_UNID_ATEN_PROD         FAC_PROYE_FALTA_PRODU.TOT_MONT_UNID_ATEN%TYPE,
  TOT_MONT_UNID_FALT_PROD         FAC_PROYE_FALTA_PRODU.TOT_MONT_UNID_FALT%TYPE,
  POR_FALT_MONT_DEMA_PROD         FAC_PROYE_FALTA_PRODU.POR_FALT_MONT_DEMA%TYPE,
  POR_FALT_NEGO_PROD              FAC_PROYE_FALTA_PRODU.POR_FALT_NEGO%TYPE,
  POR_FALT_TOTA_FACT_PROD         FAC_PROYE_FALTA_PRODU.POR_FALT_TOTA_FACT%TYPE
);
TYPE tablaProyeccionFaltanteDetalle IS TABLE OF tRegProyeccionFaltanteDetalle;

/* Declaracion de Variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(150);
W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Genera el calculo de Proyeccion de Faltante de Facturacion
                    al dia
Fecha Creacion    : 20/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PRY_PR_GENER_PROYE_FALTA_FACTU
(psCodPais VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodIso     VARCHAR2,
 psCodPeriodo OUT VARCHAR2,
 psNumVersion OUT VARCHAR2);


/***************************************************************************
Descripcion       : Genera tabla dinamica para Reporte de Proyeccion
                    de Faltante para el detallado
Fecha Creacion    : 28/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PRY_FN_OBTIE_PROYE_DETAL(
    psCodPais VARCHAR2,
    psFechaFacturacion VARCHAR2,
    psNumeroVersion    VARCHAR2,
    psNumeroRegistros  VARCHAR2)
RETURN tablaProyeccionFaltanteDetalle PIPELINED;

/**************************************************************************
  Descripcion       : Devuelve el numero de pedidos estimados de INT_FUENT_VENTA_PREVI_SAP
  Fecha Creacion    : 30/09/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION PRY_FN_NUMER_PEDID_BYDIA(psCodigoPais IN VARCHAR2,
                                  psCodigoMarca IN VARCHAR2,
                                  psCodigoCanal IN VARCHAR2,
                                  psCodigoPeriodo IN VARCHAR2,
                                  psFechaFacturacion IN VARCHAR2) RETURN NUMBER;

   /**************************************************************************
  Descripcion       : Devuelve el numero de pedidos acumulados
  Fecha Creacion    : 30/09/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION PRY_FN_NUMER_PEDID_ACUMU(psCodigoPais IN VARCHAR2,
                                  psCodigoMarca IN VARCHAR2,
                                  psCodigoCanal IN VARCHAR2,
                                  psCodigoPeriodo IN VARCHAR2,
                                  psFechaFacturacion IN VARCHAR2) RETURN NUMBER;

/***************************************************************************
Descripcion       : Genera tabla dinamica para Reporte de Proyeccion
                    de Faltante para el detallado Excel
Fecha Creacion    : 07/11/2014
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION PRY_FN_OBTIE_PROYE_DETAL_PROL(
    psCodPais VARCHAR2,
    psFechaFacturacion VARCHAR2,
    psNumeroVersion    VARCHAR2,
    psNumeroRegistros  VARCHAR2)
RETURN tablaProyeccionFaltanteDetalle PIPELINED;

END PRY_PKG_PROYE_PARCI;
/
CREATE OR REPLACE PACKAGE BODY "PRY_PKG_PROYE_PARCI"
    IS
  /**************************************************************************
  Descripcion       : Devuelve el numero de pedidos estimados de INT_FUENT_VENTA_PREVI_SAP
  Fecha Creacion    : 05/12/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
      ps_cod_regi   : Codigo de region
      ps_cod_zona   : Codigo de zona
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  FUNCTION PRY_FN_NUMER_PEDID_ESTIM(PS_COD_PERI IN VARCHAR2,
                                    PS_COD_REGI IN VARCHAR2,
                                    PS_COD_ZONA IN VARCHAR2) RETURN NUMBER IS
    RES NUMBER;
  BEGIN
    SELECT SUM(I.NUM_PEDI)
      INTO RES
      FROM INT_FUENT_VENTA_PREVI_SAP I,
           ZON_REGIO                 R,
           ZON_ZONA                  Z,
           CRA_PERIO                 C,
           SEG_PERIO_CORPO           P
     WHERE ((R.OID_REGI = I.ZORG_OID_REGI) AND
           (R.OID_REGI = Z.ZORG_OID_REGI) AND
           (Z.OID_ZONA = I.ZZON_OID_ZONA) AND
           (C.OID_PERI = I.PERD_OID_PERI) AND
           (P.OID_PERI = C.PERI_OID_PERI) AND (PS_COD_REGI = R.COD_REGI) AND
           (PS_COD_ZONA = Z.COD_ZONA) AND (PS_COD_PERI = P.COD_PERI));
    RETURN RES;
  END PRY_FN_NUMER_PEDID_ESTIM;
  /**************************************************************************
  Descripcion       : Devuelve el indicador de cierre,
  Fecha Creacion    : 05/12/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
      ps_cod_regi   : Codigo de region
      ps_cod_zona   : Codigo de pais
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  FUNCTION PRY_FN_INDIC_CIERR(PS_COD_PERI IN VARCHAR2,
                              PS_COD_REGI IN VARCHAR2,
                              PS_COD_PAIS IN VARCHAR2) RETURN VARCHAR2 IS
    cont NUMBER;
  BEGIN
    SELECT COUNT(1)
      INTO cont
      FROM FAC_CONTR_CIERR T,
           FAC_TIPOS_CIERR C,
           ZON_REGIO       R,
		   CRA_PERIO       P,
		   SEG_PERIO_CORPO S
     WHERE T.TCIE_OID_TIPO_CIER = C.OID_TIPO_CIER
       AND C.COD_TIPO_CIER = 'R'
       AND T.VAL_RESU_PROC = 'OK'
       AND T.ZORG_OID_REGI = R.OID_REGI
       AND T.PERD_OID_PERI = P.OID_PERI
	   AND P.PERI_OID_PERI = S.OID_PERI
	   AND S.COD_PERI = PS_COD_PERI
       AND R.COD_REGI = PS_COD_REGI
       AND T.PAIS_OID_PAIS =
           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(PS_COD_PAIS);
    IF (cont > 0) THEN
      RETURN 'X';
    ELSE
      RETURN ' ';
    END IF;
  END PRY_FN_INDIC_CIERR;
/***************************************************************************
Descripcion       : Genera el calculo de Proyeccion de Faltante de Facturacion
                    al dia
Fecha Creacion    : 20/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE PRY_PR_GENER_PROYE_FALTA_FACTU
(psCodPais VARCHAR2,
 psFechaFacturacion VARCHAR2,
 psCodIso     VARCHAR2,
 psCodPeriodo OUT VARCHAR2,
 psNumVersion OUT VARCHAR2)
IS
  /* Declaracion de tipos */
  TYPE tRegTablaProyeccion IS RECORD (
        OID_PROD                 MAE_PRODU.OID_PROD%TYPE,
        COD_SAP                  MAE_PRODU.COD_SAP%TYPE,
        DES_CORT                 MAE_PRODU.DES_CORT%TYPE,
        COD_NEGO                 MAE_NEGOC.COD_NEGO%TYPE,
        COD_MARC_PROD            SEG_MARCA_PRODU.COD_MARC_PROD%TYPE,
        DES_MARC_PROD            SEG_MARCA_PRODU.DES_MARC_PROD%TYPE,
        NUM_UNID_POR_ATEN        PED_SOLIC_POSIC.NUM_UNID_POR_ATEN%TYPE,
        VAL_PREC_CATA_UNIT_LOCA  PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA%TYPE,
        TOT_SOLI_OC              NUMBER
  );
  TYPE tTablaProyeccion IS TABLE OF tRegTablaProyeccion;
  TYPE tRegTablaProyeccionGrupo IS RECORD (
       COD_NEGO                 MAE_NEGOC.COD_NEGO%TYPE,
       NUM_UNI_STOC_DISPO       NUMBER,
       TOT_UNID_DEMA            NUMBER,
       TOT_UNID_ATEN            NUMBER,
       TOT_UNID_FALT            NUMBER,
       TOT_MONT_UNID_DEMA       NUMBER,
       TOT_MONT_UNID_ATEN       NUMBER,
       TOT_MONT_UNID_FALT       NUMBER,
       TOT_SOLI_OC              NUMBER
  );
  TYPE tTablaProyeccionGrupo IS TABLE OF tRegTablaProyeccionGrupo;
  TYPE tRegTablaProyeccionProd IS RECORD (
       COD_PROD                 MAE_PRODU.COD_SAP%TYPE,
       COD_NEGO                 MAE_NEGOC.COD_NEGO%TYPE,
       TOT_MONT_UNID_FALT       NUMBER
  );
  TYPE tTablaProyeccionProd IS TABLE OF tRegTablaProyeccionProd;
  /* Declaracion de variables */
  lnIdPais                 SEG_PAIS.OID_PAIS%TYPE;
  lnIdCanal                SEG_CANAL.OID_CANA%TYPE;
  lnIdMarca                SEG_MARCA.OID_MARC%TYPE;
  lsCodPeri                SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnIdAlmacen              BEL_ALMAC.OID_ALMA%TYPE;
  lnIdPeri                 CRA_PERIO.OID_PERI%TYPE;
  ldFechaFact              DATE;
  tablaProyeccion          tTablaProyeccion;
  regProyeccion            tRegTablaProyeccion;
  tablaProyeccionGrupo     tTablaProyeccionGrupo;
  regProyeccionGrupo       tRegTablaProyeccionGrupo;
  tablaProyeccionProd      tTablaProyeccionProd;
  regProyeccionProd        tRegTablaProyeccionProd;
  lnStockDisponible        NUMBER;
  lnRatioMonto             NUMBER;
  lnSumaMontoUnidDema      NUMBER;
  lnPorcenMaximo           NUMBER;
  lnMontoUniGrupo          NUMBER;
  lnTotalSolicitud         NUMBER;
  lnNumUniStocDispo        NUMBER;
  lnTotUniDema             NUMBER;
  lnTotUniAten             NUMBER;
  lnTotUniFalt             NUMBER;
  lnTotMonUniDema          NUMBER;
  lnTotMonUniAten          NUMBER;
  lnTotMonUniFalt          NUMBER;
  lnTotSoliOC              NUMBER;
  lnNumTotal               NUMBER;
  ldFechaBorrar            DATE;
  lsCodAlmacen             VARCHAR2(20);
  regProyeccionFaltaProd   FAC_PROYE_FALTA_PRODU%ROWTYPE;
  regProyeccionFaltaNego   FAC_PROYE_FALTA_NEGOC%ROWTYPE;
  lnPagina                 NUMBER;
  lnRegistro               NUMBER;
  lnPaginaNego             NUMBER;
  lnConPedi                NUMBER;
  lnNumVersion             NUMBER;
  CURSOR cParam
  IS
    SELECT
      COD_NEGO,
      VAL_PORC_MAXI_PERM
    FROM BAS_PARAM_PORCE_FALTA A
    WHERE A.PAIS_COD_PAIS = psCodPais
      AND A.COD_NEGO <> '999'
      AND A.EST_ACTI = '1';
  CURSOR cProyeccion
    (vnIdPais  NUMBER, vnIdPeri  NUMBER,
     vnIdMarca NUMBER, vnIdCanal NUMBER,
     vnIdAlmacen NUMBER, vsCodNego NUMBER,
     vdFechaFact DATE)
  IS
    SELECT
       H.OID_PROD,
       H.COD_SAP,
       NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(H.OID_PROD,'MAE_PRODU',psCodIso),' ') AS DES_CORT,
       I.COD_UNID_NEGO AS COD_NEGO,
       J.COD_MARC_PROD,
       J.DES_MARC_PROD,
       NVL(SUM(B.NUM_UNID_DEMA_REAL),0) AS NUM_UNID_POR_ATEN ,
       NVL(SUM(B.VAL_PREC_CATA_UNIT_LOCA),0) AS VAL_PREC_CATA_UNIT_LOCA,
       NVL(COUNT(1),0) AS TOT_SOLI_OC
    FROM
       PED_SOLIC_CABEC A,
       PED_SOLIC_POSIC B,
       ZON_ZONA  D,
       PED_ESTAD_POSIC E,
       PRE_OFERT_DETAL F,
       MAE_PRODU H,
       MAE_UNIDA_NEGOC I,
       SEG_MARCA_PRODU J,
       PED_TIPO_SOLIC K,
  	   PED_TIPO_SOLIC_PAIS L
    WHERE
      A.PAIS_OID_PAIS = vnIdPais
      AND A.IND_OC = 1
      AND A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE
      AND B.NUM_UNID_DEMA_REAL > 0
      AND A.GRPR_OID_GRUP_PROC > 1
      AND B.ESPO_OID_ESTA_POSI<>2
      AND ( --  Se incluyen en el reporte los pedidos que han sido ya facturados en una fecha dada, de esta manera aseguramos
		  --  que el resultado corresponda al reporte de faltantes que genera SAP en base a lo que la interfase BPS env¿a.
		  --  Estos pedidos facturados en una fecha dada pudieron tener fechas programadas de facturaci¿n
		  --  menores a la fecha de facturaci¿n
         (A.FEC_PROG_FACT <= vdFechaFact AND A.FEC_FACT = vdFechaFact)
	  	     OR
		  --  Se incluyen tambi¿n los pedidos que ser¿n facturados el d¿a de hoy.
	 	  --  Estos pedidos son los que ser¿n considerados cuando el reporte se ejecute en GP3.
	  	    (A.FEC_FACT IS NULL AND A.FEC_PROG_FACT = vdFechaFact)
	      )
	    AND A.TSPA_OID_TIPO_SOLI_PAIS = L.OID_TIPO_SOLI_PAIS
	    AND K.OID_TIPO_SOLI = L.TSOL_OID_TIPO_SOLI
      -- Validamos que las solicitudes no sean negativas ni que las solicitudes correspondan a anulaciones ni devoluciones.
      AND K.IND_ANUL <> 1 AND K.IND_DEVO <> 1
	    AND K.IND_SOLI_NEGA = 0
      --AND A.ALMC_OID_ALMA = vnIdAlmacen
      AND D.MARC_OID_MARC = vnIdMarca
      AND D.CANA_OID_CANA = vnIdCanal
      AND D.OID_ZONA = A.ZZON_OID_ZONA
      AND E.COD_ESTA_POSI <> 'AN'
      AND E.OID_ESTA_POSI = B.ESPO_OID_ESTA_POSI
      AND F.OID_DETA_OFER = B.OFDE_OID_DETA_OFER
      AND H.OID_PROD = F.PROD_OID_PROD
      AND I.COD_UNID_NEGO = vsCodNego
      AND I.PAIS_OID_PAIS = A.PAIS_OID_PAIS
      AND I.OID_UNID_NEGO = H.UNEG_OID_UNID_NEGO
      AND J.OID_MARC_PROD = H.MAPR_OID_MARC_PROD
    GROUP BY
       H.OID_PROD, H.COD_SAP,
       I.COD_UNID_NEGO, J.COD_MARC_PROD, J.DES_MARC_PROD ;
  CURSOR cProyeccionGrupo(vdFechaFact DATE, vnNumVersion NUMBER)
  IS
    SELECT
       A.COD_NEGO,
       NVL(SUM(A.NUM_UNI_STOC_DISPO),0) AS NUM_UNI_STOC_DISPO,
       NVL(SUM(A.TOT_UNID_DEMA),0) AS TOT_UNID_DEMA,
       NVL(SUM(A.TOT_UNID_ATEN),0) AS TOT_UNID_ATEN,
       NVL(SUM(A.TOT_UNID_FALT),0) AS TOT_UNID_FALT,
       NVL(SUM(A.TOT_MONT_UNID_DEMA),0) AS TOT_MONT_UNID_DEMA,
       NVL(SUM(A.TOT_MONT_UNID_ATEN),0) AS TOT_MONT_UNID_ATEN,
       NVL(SUM(A.TOT_MONT_UNID_FALT),0) AS TOT_MONT_UNID_FALT,
       NVL(SUM(A.TOT_SOLI_OC),0) AS TOT_SOLI_OC
    FROM
       FAC_PROYE_FALTA_PRODU A
    WHERE A.PAIS_COD_PAIS = psCodPais
      AND A.FEC_FACT = vdFechaFact
      AND A.NUM_VERS = vnNumVersion
    GROUP BY
       A.COD_NEGO  ;
  CURSOR cProyeccionProd(vdFechaFact DATE, vnNumVersion NUMBER)
  IS
    SELECT
       A.COD_PROD,
       A.COD_NEGO,
       A.TOT_MONT_UNID_FALT
    FROM
       FAC_PROYE_FALTA_PRODU A
    WHERE A.PAIS_COD_PAIS = psCodPais
      AND A.FEC_FACT = vdFechaFact
      AND A.NUM_VERS = vnNumVersion;
BEGIN
  /* encontrando id's */
  lnIdPais  := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdCanal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
  lnIdMarca := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
  lsCodAlmacen := 'A01';
  /* Obteniendo periodo */
  BEGIN
    SELECT B.COD_PERI
    INTO lsCodPeri
    FROM
       CRA_PERIO A,
       SEG_PERIO_CORPO B
    WHERE A.FEC_INIC <= TO_DATE(psFechaFacturacion, 'DD/MM/YYYY')
      AND A.FEC_FINA >= TO_DATE(psFechaFacturacion, 'DD/MM/YYYY')
      AND A.PERI_OID_PERI = B.OID_PERI and rownum=1;
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
       RAISE_APPLICATION_ERROR(-20123, 'No se encontro Codigo de Periodo en la Tabla BAS_CTRL_FACT');
  END ;
  psCodPeriodo := lsCodPeri;
  lnIdPeri := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeri, lnIdMarca, lnIdCanal );
  /* obteniendo fecha de facturacion */
  ldFechaFact := to_date(psFechaFacturacion,'dd/mm/yyyy');
  ldFechaBorrar := ldFechaFact - 7;
  /* Obteniendo oid de almacen */
  BEGIN
    SELECT A.OID_ALMA
    INTO lnIdAlmacen
    FROM BEL_ALMAC A
    WHERE A.PAIS_OID_PAIS = lnIdPais
      AND A.COD_ALMA = lsCodAlmacen;
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
       RAISE_APPLICATION_ERROR(-20123, 'No se encontro ID para el Almacen A01');
  END ;
  /* borrando datos de hace 7 dias para atras */
  DELETE FROM FAC_PROYE_FALTA_PRODU A
  WHERE A.PAIS_COD_PAIS = psCodPais
    AND A.FEC_FACT <= ldFechaBorrar;
  DELETE FROM FAC_PROYE_FALTA_NEGOC A
  WHERE A.PAIS_COD_PAIS = psCodPais
    AND A.FEC_FACT <= ldFechaBorrar;
  /* obteniendo numero de version a grabar */
  BEGIN
     SELECT NVL(MAX(A.NUM_VERS),0)
     INTO lnNumVersion
     FROM FAC_PROYE_FALTA_NEGOC A
     WHERE A.PAIS_COD_PAIS = psCodPais
       AND A.FEC_FACT = ldFechaFact;
     lnNumVersion := lnNumVersion + 1;
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
       lnNumVersion := 1;
  END;
  psNumVersion := trim(to_char(lnNumVersion));
  /* Recorriendo parametrizacion */
  lnSumaMontoUnidDema := 0.0;
  lnPaginaNego := 0;
  lnConPedi := 0;
  FOR C1 IN cParam LOOP
      lnPaginaNego := lnPaginaNego + 1;
      IF lnPaginaNego = 5 THEN
         lnPaginaNego := 5;
      END IF;
      /* Recorriendo lista de productos para obtener la proyeccion */
      lnPagina := 0;
      OPEN cProyeccion(lnIdPais, lnIdPeri, lnIdMarca, lnIdCanal, lnIdAlmacen, C1.COD_NEGO, ldFechaFact);
      LOOP
          FETCH cProyeccion BULK COLLECT INTO tablaProyeccion LIMIT W_FILAS;
          IF tablaProyeccion.COUNT > 0 THEN
            lnPagina := lnPagina + 1;
            FOR x IN TablaProyeccion.FIRST .. tablaProyeccion.LAST LOOP
                 regProyeccion   := tablaProyeccion(x);
                 lnTotalSolicitud := lnTotalSolicitud + 1;
                 lnRegistro := x;
                 /* Encontrando stock disponible */
                 BEGIN
                   SELECT NVL(A.VAL_SALD,0)
                   INTO
                        lnStockDisponible
                   FROM BEL_STOCK A,
                        BEL_ESTAD_MERCA B
                   WHERE --A.ALMC_OID_ALMA = lnIdAlmacen
                     --AND
                     A.PROD_OID_PROD = regProyeccion.OID_PROD
                     AND B.COD_ESTA = 'LD'
                     AND B.OID_ESTA_MERC = A.ESME_OID_ESTA_MERC ;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                      lnStockDisponible := 0;
                 END;
                 regProyeccionFaltaProd.Pais_Cod_Pais := psCodPais;
                 regProyeccionFaltaProd.Fec_Fact := ldFechaFact;
                 regProyeccionFaltaProd.Num_Vers := lnNumVersion;
                 regProyeccionFaltaProd.Cod_Prod := regProyeccion.COD_SAP;
                 regProyeccionFaltaProd.Des_Prod := regProyeccion.DES_CORT;
                 regProyeccionFaltaProd.Cod_Nego := regProyeccion.COD_NEGO;
                 regProyeccionFaltaProd.Cod_Marc_Prod := regProyeccion.COD_MARC_PROD;
                 regProyeccionFaltaProd.Des_Marc_Prod := regProyeccion.DES_MARC_PROD;
                 regProyeccionFaltaProd.Tot_Unid_Dema := regProyeccion.NUM_UNID_POR_ATEN;
                 regProyeccionFaltaProd.Tot_Mont_Unid_Dema := regProyeccion.VAL_PREC_CATA_UNIT_LOCA;
                 regProyeccionFaltaProd.Tot_Soli_Oc := regProyeccion.TOT_SOLI_OC;
                 regProyeccionFaltaProd.Num_Uni_Stoc_Dispo := lnStockDisponible;
                 -- total unidades atendidas
                 IF lnStockDisponible >= regProyeccionFaltaProd.Tot_Unid_Dema THEN
                    regProyeccionFaltaProd.Tot_Unid_Aten := regProyeccionFaltaProd.Tot_Unid_Dema;
                 ELSE
                    regProyeccionFaltaProd.Tot_Unid_Aten := lnStockDisponible;
                 END IF;
                 -- total unidades faltantes
                 regProyeccionFaltaProd.Tot_Unid_Falt := regProyeccionFaltaProd.Tot_Unid_Dema - regProyeccionFaltaProd.Tot_Unid_Aten;
                 -- % faltante respecto a las unid. demandadas
                 IF regProyeccionFaltaProd.Tot_Unid_Dema > 0 THEN
                    regProyeccionFaltaProd.Por_Falt_Unid_Dema := regProyeccionFaltaProd.Tot_Unid_Falt * 100 / regProyeccionFaltaProd.Tot_Unid_Dema ;
                 ELSE
                    regProyeccionFaltaProd.Por_Falt_Unid_Dema := 0;
                 END IF;
                 IF regProyeccionFaltaProd.Tot_Unid_Dema > 0 THEN
                   lnRatioMonto := regProyeccionFaltaProd.Tot_Mont_Unid_Dema / regProyeccionFaltaProd.Tot_Unid_Dema;
                   -- total valor catalogo de unid. atendidas
                   regProyeccionFaltaProd.Tot_Mont_Unid_Aten := lnRatioMonto * regProyeccionFaltaProd.Tot_Unid_Aten ;
                   -- total valor catalogo de unid. faltantes
                   regProyeccionFaltaProd.Tot_Mont_Unid_Falt := lnRatioMonto * regProyeccionFaltaProd.Tot_Unid_Falt ;
                 ELSE
                   regProyeccionFaltaProd.Tot_Mont_Unid_Aten := 0.0;
                   regProyeccionFaltaProd.Tot_Mont_Unid_Falt := 0.0;
                 END IF;
                 -- % faltante respecto al valor Catalogo demandado
                 IF regProyeccionFaltaProd.Tot_Mont_Unid_Dema > 0 THEN
                    regProyeccionFaltaProd.Por_Falt_Mont_Dema := regProyeccionFaltaProd.Tot_Mont_Unid_Falt * 100 / regProyeccionFaltaProd.Tot_Mont_Unid_Dema;
                 ELSE
                    regProyeccionFaltaProd.Por_Falt_Mont_Dema := 0;
                 END IF;
                 regProyeccionFaltaProd.Por_Falt_Tota_Fact := 0;
                 regProyeccionFaltaProd.Por_Falt_Nego := 0;
                 lnSumaMontoUnidDema := lnSumaMontoUnidDema + regProyeccionFaltaProd.Tot_Mont_Unid_Dema;
                 --dbms_output.put_line(lnSumaMontoUnidDema);
                 -- insertando en la tabla de proyeccion faltantes por producto
                 lnConPedi:=lnConPedi + regProyeccionFaltaProd.Tot_Unid_Dema;
                 INSERT INTO FAC_PROYE_FALTA_PRODU
                 VALUES regProyeccionFaltaProd;
             END LOOP;
          END IF;
          EXIT WHEN cProyeccion%NOTFOUND;
      END LOOP;
      CLOSE cProyeccion;
  END LOOP;
  -- actualizando porcentaje de faltante respecto al total facturacion del dia
  -- dbms_output.put_line('principal '|| lnSumaMontoUnidDema);
  IF lnSumaMontoUnidDema > 0 THEN
      UPDATE FAC_PROYE_FALTA_PRODU A
         SET A.POR_FALT_TOTA_FACT = (A.TOT_MONT_UNID_FALT / lnSumaMontoUnidDema) * 100
      WHERE  A.PAIS_COD_PAIS = psCodPais
         AND A.FEC_FACT = ldFechaFact
         AND A.NUM_VERS = lnNumVersion;
  END IF;
  /* Generando calculo para la proyeccion del grupo */
  OPEN cProyeccionGrupo(ldFechaFact, lnNumVersion);
  LOOP
      FETCH cProyeccionGrupo BULK COLLECT INTO tablaProyeccionGrupo LIMIT W_FILAS;
      IF tablaProyeccionGrupo.COUNT > 0 THEN
        FOR x IN tablaProyeccionGrupo.FIRST .. tablaProyeccionGrupo.LAST LOOP
             regProyeccionGrupo  := tablaProyeccionGrupo(x);
             regProyeccionFaltaNego.Pais_Cod_Pais := psCodPais;
             regProyeccionFaltaNego.Fec_Fact := ldFechaFact;
             regProyeccionFaltaNego.Num_Vers := lnNumVersion;
             regProyeccionFaltaNego.Cod_Nego := regProyeccionGrupo.COD_NEGO;
             regProyeccionFaltaNego.Num_Uni_Stoc_Dispo := regProyeccionGrupo.NUM_UNI_STOC_DISPO;
             regProyeccionFaltaNego.Tot_Unid_Dema := regProyeccionGrupo.TOT_UNID_DEMA;
             regProyeccionFaltaNego.Tot_Unid_Aten := regProyeccionGrupo.TOT_UNID_ATEN;
             regProyeccionFaltaNego.Tot_Unid_Falt := regProyeccionGrupo.TOT_UNID_FALT;
             regProyeccionFaltaNego.Tot_Mont_Unid_Dema := regProyeccionGrupo.TOT_MONT_UNID_DEMA;
             regProyeccionFaltaNego.Tot_Mont_Unid_Aten := regProyeccionGrupo.TOT_MONT_UNID_ATEN;
             regProyeccionFaltaNego.Tot_Mont_Unid_Falt := regProyeccionGrupo.TOT_MONT_UNID_FALT;
             regProyeccionFaltaNego.Tot_Soli_Oc := regProyeccionGrupo.TOT_SOLI_OC;
             regProyeccionFaltaNego.Cod_Peri := lsCodPeri;
             regProyeccionFaltaNego.Fec_Fact := ldFechaFact;
             regProyeccionFaltaNego.Con_Pedi := lnConPedi;
             /* encontrando descripcion del grupo */
             SELECT A.VAL_I18N
             INTO
                  regProyeccionFaltaNego.Des_Nego
             FROM GEN_I18N_SICC_PAIS A,
                  MAE_UNIDA_NEGOC B
             WHERE B.PAIS_OID_PAIS = lnIdPais
               AND B.COD_UNID_NEGO = regProyeccionFaltaNego.Cod_Nego
               AND A.ATTR_ENTI = 'MAE_UNIDA_NEGOC'
               AND A.VAL_OID = B.OID_UNID_NEGO;
             -- % faltante respecto a las unid. demandadas
             IF regProyeccionFaltaNego.Tot_Unid_Dema > 0 THEN
                regProyeccionFaltaNego.Por_Falt_Unid_Dema := regProyeccionFaltaNego.Tot_Unid_Falt * 100 / regProyeccionFaltaNego.Tot_Unid_Dema ;
             ELSE
                regProyeccionFaltaNego.Por_Falt_Unid_Dema := 0;
             END IF;
             -- % faltante respecto al valor Catalogo demandado
             IF regProyeccionFaltaNego.Tot_Mont_Unid_Dema > 0 THEN
                regProyeccionFaltaNego.Por_Falt_Mont_Dema := regProyeccionFaltaNego.Tot_Mont_Unid_Falt * 100 / regProyeccionFaltaNego.Tot_Mont_Unid_Dema;
             ELSE
                regProyeccionFaltaNego.Por_Falt_Mont_Dema := 0;
             END IF;
             regProyeccionFaltaNego.Por_Falt_Tota_Fact := 0;
             regProyeccionFaltaNego.Ind_Valo_Crit := 'N';
             /* obteniendo indicador de valor de criticidad */
             SELECT A.VAL_PORC_MAXI_PERM
             INTO lnPorcenMaximo
             FROM BAS_PARAM_PORCE_FALTA A
             WHERE A.PAIS_COD_PAIS = psCodPais
               AND A.COD_NEGO = regProyeccionFaltaNego.Cod_Nego;
             IF lnPorcenMaximo < regProyeccionFaltaNego.Por_Falt_Mont_Dema THEN
                regProyeccionFaltaNego.Ind_Valo_Crit := 'S';
             END IF;
             -- insertando en la tabla de proyeccion faltantes por negocio o grupo
             INSERT INTO FAC_PROYE_FALTA_NEGOC
             VALUES regProyeccionFaltaNego;
         END LOOP;
      END IF;
      EXIT WHEN cProyeccionGrupo%NOTFOUND;
  END LOOP;
  CLOSE cProyeccionGrupo;
   -- actualizando porcentaje de faltante respecto al total facturacion del dia
  IF lnSumaMontoUnidDema > 0 THEN
      UPDATE FAC_PROYE_FALTA_NEGOC A
         SET A.POR_FALT_TOTA_FACT = (A.TOT_MONT_UNID_FALT / lnSumaMontoUnidDema) * 100
      WHERE  A.PAIS_COD_PAIS = psCodPais
         AND A.FEC_FACT = ldFechaFact
         AND A.NUM_VERS = lnNumVersion;
  END IF;
  /* Generando calculo para la proyeccion del producto */
  OPEN cProyeccionProd(ldFechaFact, lnNumVersion);
  LOOP
      FETCH cProyeccionProd BULK COLLECT INTO tablaProyeccionProd LIMIT W_FILAS;
      IF tablaProyeccionProd.COUNT > 0 THEN
        FOR x IN tablaProyeccionProd.FIRST .. tablaProyeccionProd.LAST LOOP
             regProyeccionProd  := tablaProyeccionProd(x);
             SELECT NVL(A.TOT_MONT_UNID_FALT,0)
             INTO lnMontoUniGrupo
             FROM FAC_PROYE_FALTA_NEGOC A
             WHERE A.PAIS_COD_PAIS = psCodPais
               AND A.FEC_FACT = ldFechaFact
               AND A.NUM_VERS = lnNumVersion
               AND A.COD_NEGO =  regProyeccionProd.COD_NEGO;
             -- Actualizando el porcentaje faltante en relacion al negocio
             IF lnMontoUniGrupo > 0 THEN
                 UPDATE FAC_PROYE_FALTA_PRODU A
                 SET
                     A.POR_FALT_NEGO = ( regProyeccionProd.TOT_MONT_UNID_FALT /lnMontoUniGrupo ) * 100
                 WHERE A.PAIS_COD_PAIS = psCodPais
                   AND A.FEC_FACT = ldFechaFact
                   AND A.NUM_VERS = lnNumVersion
                   AND A.COD_PROD = regProyeccionProd.COD_PROD;
             END IF;
         END LOOP;
      END IF;
      EXIT WHEN cProyeccionProd%NOTFOUND;
  END LOOP;
  CLOSE cProyeccionProd;
  /* ACTUALIZANDO PARA EL CASO DEL GRUPO 999 (TODOS) */
  /* obteniendo indicador de valor de criticidad */
  BEGIN
    SELECT A.VAL_PORC_MAXI_PERM
    INTO lnPorcenMaximo
    FROM BAS_PARAM_PORCE_FALTA A
    WHERE A.PAIS_COD_PAIS = psCodPais
      AND A.COD_NEGO = '999';
  EXCEPTION
  WHEN no_data_found THEN
       RETURN;
  END ;
  SELECT
     NVL(SUM(A.NUM_UNI_STOC_DISPO),0),
     NVL(SUM(A.TOT_UNID_DEMA),0),
     NVL(SUM(A.TOT_UNID_ATEN),0),
     NVL(SUM(A.TOT_UNID_FALT),0),
     NVL(SUM(A.TOT_MONT_UNID_DEMA),0),
     NVL(SUM(A.TOT_MONT_UNID_ATEN),0),
     NVL(SUM(A.TOT_MONT_UNID_FALT),0),
     NVL(SUM(A.TOT_SOLI_OC),0),
     NVL(COUNT(1),0)
  INTO
     lnNumUniStocDispo,
     lnTotUniDema,
     lnTotUniAten,
     lnTotUniFalt,
     lnTotMonUniDema,
     lnTotMonUniAten,
     lnTotMonUniFalt,
     lnTotSoliOC,
     lnNumTotal
  FROM
     FAC_PROYE_FALTA_NEGOC A
  WHERE A.PAIS_COD_PAIS = psCodPais
    AND A.FEC_FACT = ldFechaFact
    AND A.NUM_VERS = lnNumVersion
    AND A.COD_NEGO <> '999';
  IF (lnNumTotal = 0) THEN
     RETURN;
  END IF;
  regProyeccionFaltaNego.Pais_Cod_Pais := psCodPais;
  regProyeccionFaltaNego.Fec_Fact := ldFechaFact;
  regProyeccionFaltaNego.Num_Vers := lnNumVersion;
  regProyeccionFaltaNego.Cod_Nego := '999';
  regProyeccionFaltaNego.Num_Uni_Stoc_Dispo := lnNumUniStocDispo;
  regProyeccionFaltaNego.Tot_Unid_Dema := lnTotUniDema;
  regProyeccionFaltaNego.Tot_Unid_Aten := lnTotUniAten;
  regProyeccionFaltaNego.Tot_Unid_Falt := lnTotUniFalt;
  regProyeccionFaltaNego.Tot_Mont_Unid_Dema := lnTotMonUniDema;
  regProyeccionFaltaNego.Tot_Mont_Unid_Aten := lnTotMonUniAten;
  regProyeccionFaltaNego.Tot_Mont_Unid_Falt := lnTotMonUniFalt;
  regProyeccionFaltaNego.Tot_Soli_Oc := lnTotSoliOC;
  regProyeccionFaltaNego.Cod_Peri    := lsCodPeri;
  regProyeccionFaltaNego.Fec_Fact    := ldFechaFact;
  regProyeccionFaltaNego.Des_Nego    := 'GENERAL';
   -- % faltante respecto a las unid. demandadas
  IF regProyeccionFaltaNego.Tot_Unid_Dema > 0 THEN
     regProyeccionFaltaNego.Por_Falt_Unid_Dema := regProyeccionFaltaNego.Tot_Unid_Falt * 100 / regProyeccionFaltaNego.Tot_Unid_Dema ;
  ELSE
     regProyeccionFaltaNego.Por_Falt_Unid_Dema := 0;
  END IF;
   -- % faltante respecto al valor Catalogo demandado
  IF regProyeccionFaltaNego.Tot_Mont_Unid_Dema > 0 THEN
     regProyeccionFaltaNego.Por_Falt_Mont_Dema := regProyeccionFaltaNego.Tot_Mont_Unid_Falt * 100 / regProyeccionFaltaNego.Tot_Mont_Unid_Dema;
  ELSE
     regProyeccionFaltaNego.Por_Falt_Mont_Dema := 0;
  END IF;
  regProyeccionFaltaNego.Por_Falt_Tota_Fact := 0;
  regProyeccionFaltaNego.Ind_Valo_Crit := 'N';
  IF lnSumaMontoUnidDema > 0 THEN
     regProyeccionFaltaNego.Por_Falt_Tota_Fact := (regProyeccionFaltaNego.Tot_Mont_Unid_Falt / regProyeccionFaltaNego.Tot_Mont_Unid_Dema) * 100;
  END IF;
  IF lnPorcenMaximo < regProyeccionFaltaNego.Por_Falt_Mont_Dema THEN
     regProyeccionFaltaNego.Ind_Valo_Crit := 'S';
  END IF;
  -- insertando en la tabla de proyeccion faltantes por negocio o grupo
  INSERT INTO FAC_PROYE_FALTA_NEGOC
  VALUES regProyeccionFaltaNego;
 -- RETURN;
  -- insertando en el detalle tabla de proyeccion faltante por producto
  INSERT INTO FAC_PROYE_FALTA_PRODU
  (PAIS_COD_PAIS, FEC_FACT, NUM_VERS, COD_PROD, DES_PROD,
   COD_NEGO, COD_MARC_PROD, DES_MARC_PROD, NUM_UNI_STOC_DISPO,
   TOT_UNID_DEMA, TOT_UNID_ATEN, TOT_UNID_FALT, POR_FALT_UNID_DEMA,
   TOT_MONT_UNID_DEMA, TOT_MONT_UNID_ATEN, TOT_MONT_UNID_FALT, POR_FALT_MONT_DEMA,
   POR_FALT_NEGO, POR_FALT_TOTA_FACT, IND_VALO_CRITI, TOT_SOLI_OC)
  SELECT
      PAIS_COD_PAIS, FEC_FACT, NUM_VERS, COD_PROD, DES_PROD,
      '999', COD_MARC_PROD, DES_MARC_PROD, NUM_UNI_STOC_DISPO,
      TOT_UNID_DEMA, TOT_UNID_ATEN, TOT_UNID_FALT, POR_FALT_UNID_DEMA,
      TOT_MONT_UNID_DEMA, TOT_MONT_UNID_ATEN, TOT_MONT_UNID_FALT, POR_FALT_MONT_DEMA,
      POR_FALT_NEGO, POR_FALT_TOTA_FACT, IND_VALO_CRITI, TOT_SOLI_OC
    FROM
       FAC_PROYE_FALTA_PRODU A
    WHERE A.PAIS_COD_PAIS = psCodPais
      AND A.FEC_FACT = ldFechaFact
      AND A.NUM_VERS = lnNumVersion;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR FAC_PR_GENER_PROYE_FALTA_FACTU: '||ls_sqlerrm  || ' Pagina Principal: ' || lnPaginaNego || ' Pagina: '|| lnPagina || ' Registro: '|| lnRegistro );
END PRY_PR_GENER_PROYE_FALTA_FACTU;
/***************************************************************************
Descripcion       : Genera tabla dinamica para Reporte de Proyeccion
                    de Faltante para el detallado Excel
Fecha Creacion    : 28/02/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION PRY_FN_OBTIE_PROYE_DETAL(
    psCodPais VARCHAR2,
    psFechaFacturacion VARCHAR2,
    psNumeroVersion    VARCHAR2,
    psNumeroRegistros  VARCHAR2)
RETURN tablaProyeccionFaltanteDetalle PIPELINED
IS
lsCodNego           FAC_PROYE_FALTA_NEGOC.COD_NEGO%TYPE;
TablaRegistro       tablaProyeccionFaltanteDetalle;
regRegistro         tRegProyeccionFaltanteDetalle;
lnNumeroRegistros   NUMBER;
lnContador          NUMBER;
CURSOR cursorRegistro IS
SELECT
  A.COD_NEGO,
  A.DES_NEGO,
  A.NUM_UNI_STOC_DISPO,
  A.TOT_UNID_DEMA,
  A.TOT_UNID_ATEN,
  A.TOT_UNID_FALT,
  A.POR_FALT_UNID_DEMA,
  A.TOT_MONT_UNID_DEMA,
  A.TOT_MONT_UNID_ATEN,
  A.TOT_MONT_UNID_FALT,
  A.POR_FALT_MONT_DEMA,
  A.POR_FALT_TOTA_FACT,
  B.VAL_PORC_MAXI_PERM,
  A.IND_VALO_CRIT,
  A.COD_PERI,
  A.FEC_FACT,
  C.COD_PROD,
  C.DES_PROD,
  C.COD_MARC_PROD,
  C.DES_MARC_PROD,
  C.NUM_UNI_STOC_DISPO AS NUM_UNI_STOC_DISPO_PROD,
  C.TOT_UNID_DEMA AS TOT_UNID_DEMA_PROD,
  C.TOT_UNID_ATEN AS TOT_UNID_ATEN_PROD,
  C.TOT_UNID_FALT AS TOT_UNID_FALT_PROD,
  C.POR_FALT_UNID_DEMA AS POR_FALT_UNID_DEMA_PROD,
  C.TOT_MONT_UNID_DEMA AS TOT_MONT_UNID_DEMA_PROD,
  C.TOT_MONT_UNID_ATEN AS TOT_MONT_UNID_ATEN_PROD,
  C.TOT_MONT_UNID_FALT AS TOT_MONT_UNID_FALT_PROD,
  C.POR_FALT_MONT_DEMA AS POR_FALT_MONT_DEMA_PROD,
  C.POR_FALT_NEGO AS POR_FALT_NEGO_PROD,
  C.POR_FALT_TOTA_FACT AS POR_FALT_TOTA_FACT_PROD
FROM FAC_PROYE_FALTA_NEGOC A,
     BAS_PARAM_PORCE_FALTA B,
     FAC_PROYE_FALTA_PRODU C
WHERE A.PAIS_COD_PAIS = psCodPais
  AND A.FEC_FACT = TO_DATE(psFechaFacturacion,'DD/MM/YYYY')
  AND A.NUM_VERS = TO_NUMBER(psNumeroVersion)
  AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
  AND A.COD_NEGO = B.COD_NEGO
  AND A.PAIS_COD_PAIS = C.PAIS_COD_PAIS
  AND A.FEC_FACT = C.FEC_FACT
  AND A.NUM_VERS = C.NUM_VERS
  AND A.COD_NEGO = C.COD_NEGO
ORDER BY A.COD_NEGO, C.TOT_MONT_UNID_FALT DESC, C.COD_PROD;
BEGIN
  lsCodNego := 'xxxxx';
  lnNumeroRegistros := to_number(psNumeroRegistros);
  lnContador := 0;
  OPEN cursorRegistro;
  LOOP
       FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             regRegistro := tablaRegistro(x);
             IF lsCodNego <> regRegistro.COD_NEGO THEN
                lnContador := 0;
                lsCodNego  :=  regRegistro.COD_NEGO;
             END IF;
             lnContador := lnContador + 1;
             IF lnContador <= lnNumeroRegistros THEN
                 PIPE ROW(tablaRegistro(x));
             END IF;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_OBTIE_PROYE_DETAl: '||ls_sqlerrm);
END PRY_FN_OBTIE_PROYE_DETAL;


 /**************************************************************************
  Descripcion       : Devuelve el numero de pedidos por dia
  Fecha Creacion    : 30/09/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION PRY_FN_NUMER_PEDID_BYDIA(psCodigoPais IN VARCHAR2,
                                  psCodigoMarca IN VARCHAR2,
                                  psCodigoCanal IN VARCHAR2,
                                  psCodigoPeriodo IN VARCHAR2,
                                  psFechaFacturacion IN VARCHAR2) RETURN NUMBER IS
    RES NUMBER;

    lnIdPais            NUMBER;
    lnIdMarca           NUMBER;
    lnIdCanal           NUMBER;
    lnIdPeriodo         NUMBER;

  BEGIN

    lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante
    lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);-- id del marca consultante
    lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);-- id del canal consultante
    lnIdPeriodo    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psCodigoPeriodo,lnIdMarca,lnIdCanal);-- id del periodo consultante


    SELECT COUNT(DISTINCT sol.oid_soli_cabe) AS numordenesdia
      INTO res
      FROM ped_solic_cabec      sol,
           ped_tipo_solic_pais  tsp,
           ped_tipo_solic       ts,
           ped_solic_posic      psp,
           mae_produ            prd,
           int_param_tipo_solic pt,
           pre_ofert_detal      pod,
           pre_tipo_ofert       ofe,
           pre_catal            cat
     WHERE sol.pais_oid_pais = lnIdPais
       AND sol.perd_oid_peri = lnIdPeriodo
       AND sol.ind_oc = 1
       AND sol.ind_pedi_prue = 0
       AND sol.modu_oid_modu != 13
       AND sol.fec_fact IS NOT NULL
       AND sol.ind_ts_no_conso = 1
       AND sol.fec_fact = to_date(psFechaFacturacion, 'YYYYMMDD')
       AND psp.espo_oid_esta_posi <> 2
       AND sol.oid_soli_cabe = psp.soca_oid_soli_cabe
       AND psp.prod_oid_prod = prd.oid_prod
       AND prd.mapr_oid_marc_prod IS NOT NULL
       AND sol.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND pt.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer
       AND pod.tofe_oid_tipo_ofer = ofe.oid_tipo_ofer
       AND pod.ocat_oid_catal = cat.oid_cata(+)
       AND (NOT (ofe.cod_tipo_ofer = '21' AND cat.cod_cata = 5) AND
           NOT (ofe.cod_tipo_ofer = '23' AND cat.cod_cata = 5))
       AND ts.ind_anul = 0
       AND ts.ind_devo = 0
     GROUP BY sol.pais_oid_pais,
              sol.perd_oid_peri,
              sol.sbac_oid_sbac,
              --sol.almc_oid_alma,
              sol.soci_oid_soci,
              sol.tspa_oid_tipo_soli_pais,
              ts.ind_anul,
              ts.ind_devo,
              sol.ticl_oid_tipo_clie;

    RETURN RES;
  END PRY_FN_NUMER_PEDID_BYDIA;

  /**************************************************************************
  Descripcion       : Devuelve el numero de pedidos acumulados
  Fecha Creacion    : 30/09/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION PRY_FN_NUMER_PEDID_ACUMU(psCodigoPais IN VARCHAR2,
                                  psCodigoMarca IN VARCHAR2,
                                  psCodigoCanal IN VARCHAR2,
                                  psCodigoPeriodo IN VARCHAR2,
                                  psFechaFacturacion IN VARCHAR2) RETURN NUMBER IS
    RES NUMBER;

    lnIdPais            NUMBER;
    lnIdMarca           NUMBER;
    lnIdCanal           NUMBER;
    lnIdPeriodo         NUMBER;

  BEGIN

    lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante
    lnIdMarca      := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);-- id del marca consultante
    lnIdCanal      := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);-- id del canal consultante
    lnIdPeriodo    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psCodigoPeriodo,lnIdMarca,lnIdCanal);-- id del periodo consultante

    SELECT COUNT(DISTINCT sol.oid_soli_cabe) AS numordenesdia
      INTO res
      FROM ped_solic_cabec      sol,
           ped_tipo_solic_pais  tsp,
           ped_tipo_solic       ts,
           ped_solic_posic      psp,
           mae_produ            prd,
           int_param_tipo_solic pt,
           pre_ofert_detal      pod,
           pre_tipo_ofert       ofe,
           pre_catal            cat
     WHERE sol.pais_oid_pais = lnIdPais
       AND sol.perd_oid_peri = lnIdPeriodo
       AND sol.ind_oc = 1
       AND sol.ind_pedi_prue = 0
       AND sol.modu_oid_modu != 13
       AND sol.fec_fact IS NOT NULL
       AND sol.ind_ts_no_conso = 1
       AND sol.fec_fact <= to_date(psFechaFacturacion, 'YYYYMMDD')
       AND psp.espo_oid_esta_posi <> 2
       AND sol.oid_soli_cabe = psp.soca_oid_soli_cabe
       AND psp.prod_oid_prod = prd.oid_prod
       AND prd.mapr_oid_marc_prod IS NOT NULL
       AND sol.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
       AND pt.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer
       AND pod.tofe_oid_tipo_ofer = ofe.oid_tipo_ofer
       AND pod.ocat_oid_catal = cat.oid_cata(+)
       AND (NOT (ofe.cod_tipo_ofer = '21' AND cat.cod_cata = 5) AND
           NOT (ofe.cod_tipo_ofer = '23' AND cat.cod_cata = 5))
       AND ts.ind_anul = 0
       AND ts.ind_devo = 0
     GROUP BY sol.pais_oid_pais,
              sol.perd_oid_peri,
              sol.sbac_oid_sbac,
              --sol.almc_oid_alma,
              sol.soci_oid_soci,
              sol.tspa_oid_tipo_soli_pais,
              ts.ind_anul,
              ts.ind_devo,
              sol.ticl_oid_tipo_clie;


    RETURN RES;

  END PRY_FN_NUMER_PEDID_ACUMU;
  
/***************************************************************************
Descripcion       : Genera tabla dinamica para Reporte de Proyeccion
                    de Faltante para el detallado Excel
Fecha Creacion    : 07/11/2014
Autor             : Sergio Apaza
***************************************************************************/
FUNCTION PRY_FN_OBTIE_PROYE_DETAL_PROL(
    psCodPais VARCHAR2,
    psFechaFacturacion VARCHAR2,
    psNumeroVersion    VARCHAR2,
    psNumeroRegistros  VARCHAR2)
RETURN tablaProyeccionFaltanteDetalle PIPELINED
IS
lsCodNego           FAC_PROYE_FALTA_NEGOC.COD_NEGO%TYPE;
TablaRegistro       tablaProyeccionFaltanteDetalle;
regRegistro         tRegProyeccionFaltanteDetalle;
lnNumeroRegistros   NUMBER;
lnContador          NUMBER;
CURSOR cursorRegistro IS
SELECT
  A.COD_NEGO,
  A.DES_NEGO,
  A.NUM_UNI_STOC_DISPO,
  A.TOT_UNID_DEMA,
  A.TOT_UNID_ATEN,
  A.TOT_UNID_FALT,
  A.POR_FALT_UNID_DEMA,
  A.TOT_MONT_UNID_DEMA,
  A.TOT_MONT_UNID_ATEN,
  A.TOT_MONT_UNID_FALT,
  A.POR_FALT_MONT_DEMA,
  A.POR_FALT_TOTA_FACT,
  B.VAL_PORC_MAXI_PERM,
  A.IND_VALO_CRIT,
  A.COD_PERI,
  A.FEC_FACT,
  C.COD_PROD,
  C.DES_PROD,
  C.COD_MARC_PROD,
  C.DES_MARC_PROD,
  C.NUM_UNI_STOC_DISPO AS NUM_UNI_STOC_DISPO_PROD,
  C.TOT_UNID_DEMA AS TOT_UNID_DEMA_PROD,
  C.TOT_UNID_ATEN AS TOT_UNID_ATEN_PROD,
  C.TOT_UNID_FALT AS TOT_UNID_FALT_PROD,
  C.POR_FALT_UNID_DEMA AS POR_FALT_UNID_DEMA_PROD,
  C.TOT_MONT_UNID_DEMA AS TOT_MONT_UNID_DEMA_PROD,
  C.TOT_MONT_UNID_ATEN AS TOT_MONT_UNID_ATEN_PROD,
  C.TOT_MONT_UNID_FALT AS TOT_MONT_UNID_FALT_PROD,
  C.POR_FALT_MONT_DEMA AS POR_FALT_MONT_DEMA_PROD,
  C.POR_FALT_NEGO AS POR_FALT_NEGO_PROD,
  C.POR_FALT_TOTA_FACT AS POR_FALT_TOTA_FACT_PROD
FROM FAC_PROYE_FALTA_NEGOC_PROL A,
     BAS_PARAM_PORCE_FALTA B,
     FAC_PROYE_FALTA_PRODU_PROL C
WHERE A.PAIS_COD_PAIS = psCodPais
  AND A.FEC_FACT = TO_DATE(psFechaFacturacion,'DD/MM/YYYY')
  AND A.NUM_VERS = TO_NUMBER(psNumeroVersion)
  AND A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
  AND A.COD_NEGO = B.COD_NEGO
  AND A.PAIS_COD_PAIS = C.PAIS_COD_PAIS
  AND A.FEC_FACT = C.FEC_FACT
  AND A.NUM_VERS = C.NUM_VERS
  AND A.COD_NEGO = C.COD_NEGO
ORDER BY A.COD_NEGO, C.TOT_MONT_UNID_FALT DESC, C.COD_PROD;
BEGIN
  lsCodNego := 'xxxxx';
  lnNumeroRegistros := to_number(psNumeroRegistros);
  lnContador := 0;
  OPEN cursorRegistro;
  LOOP
       FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
       IF tablaRegistro.COUNT > 0 THEN
         FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
             regRegistro := tablaRegistro(x);
             IF lsCodNego <> regRegistro.COD_NEGO THEN
                lnContador := 0;
                lsCodNego  :=  regRegistro.COD_NEGO;
             END IF;
             lnContador := lnContador + 1;
             IF lnContador <= lnNumeroRegistros THEN
                 PIPE ROW(tablaRegistro(x));
             END IF;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PRY_FN_OBTIE_PROYE_DETAL_PROL: '||ls_sqlerrm);
END PRY_FN_OBTIE_PROYE_DETAL_PROL;
  
END PRY_PKG_PROYE_PARCI;
/
