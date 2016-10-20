CREATE OR REPLACE PROCEDURE "IMP_PR_GENER_CUPON" (psCodigoPais IN VARCHAR2,
                                                  psCodigoCliente IN VARCHAR2,
                                                  psFechaFacturacion IN VARCHAR2,
                                                  psCodigoPeriodo IN VARCHAR2,
                                                  pnOidActividad IN NUMBER,
                                                  psIndicadorDetalleCCC IN VARCHAR2 := 'N') AS

lnOidCliente        NUMBER;
lnOidPais           NUMBER;
lnSaldo             NUMBER(12,2);
lsSaldo             VARCHAR2(100);
lsCodigoZona        VARCHAR2(100);
lsCodigoTerritorio  VARCHAR2(100);
lsNombreCliente     VARCHAR2(100);
lsDireccion1        VARCHAR2(100);
lsDireccion2        VARCHAR2(100);
lsPeriodo           VARCHAR2(100);
ldFechaVencimiento  DATE;
lnOidPeriodo        NUMBER;
lnCorrelativo       NUMBER;

l_CLOBDest              CLOB;

BEGIN

    DELETE FROM IMP_PAQUE_DOCUM_CUPON
    WHERE COD_CLIE = psCodigoCliente;

    SELECT NVL(MAX(COR_PDCU), 1) + 1
    INTO lnCorrelativo
    FROM IMP_PAQUE_DOCUM_CUPON;

    INSERT INTO IMP_PAQUE_DOCUM_CUPON (
    COR_PDCU,
    COD_CLIE,
    XML_CONS
    )
    VALUES (
    lnCorrelativo,
    psCodigoCliente,
    EMPTY_CLOB())
    RETURNING XML_CONS INTO l_CLOBDest;

    SELECT OID_CLIE
    INTO lnOidCliente
    FROM MAE_CLIEN
    WHERE COD_CLIE = psCodigoCliente;

    SELECT OID_PAIS
    INTO lnOidPais
    FROM SEG_PAIS
    WHERE COD_PAIS = psCodigoPais;

    SELECT C.DES_ABRV_TIPO_VIA  || ' ' || B.VAL_NOMB_VIA || ' ' || B.NUM_PPAL
    INTO lsDireccion1
    FROM MAE_CLIEN A, MAE_CLIEN_DIREC B, SEG_TIPO_VIA C
    WHERE A.OID_CLIE = B.CLIE_OID_CLIE
    AND B.TIVI_OID_TIPO_VIA = C.OID_TIPO_VIA (+)
    AND B.IND_ELIM = 0
    AND B.IND_DIRE_PPAL = 1
    AND A.COD_CLIE = psCodigoCliente;

    lsPeriodo := SUBSTR(psCodigoPeriodo, 5, 2) || '/' || SUBSTR(psCodigoPeriodo, 1, 4);

    lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

    SELECT CR.FEC_INIC
    INTO ldFechaVencimiento
    FROM CRA_CRONO CR,
         ZON_ZONA ZZ,
         ZON_SECCI ZS,
         ZON_TERRI_ADMIN ZTA,
         MAE_CLIEN_UNIDA_ADMIN MCUA
    WHERE CR.ZZON_OID_ZONA=ZZ.OID_ZONA
      AND ZZ.OID_ZONA=ZS.ZZON_OID_ZONA
      AND ZTA.ZSCC_OID_SECC=ZS.OID_SECC
      AND ZTA.OID_TERR_ADMI=MCUA.ZTAD_OID_TERR_ADMI
      AND MCUA.CLIE_OID_CLIE= lnOidCliente
      AND MCUA.IND_ACTI=1
      AND CR.PERD_OID_PERI= lnOidPeriodo + 1
      AND CR.CACT_OID_ACTI = pnOidActividad;

    SELECT NVL(SUM(MCC.IMP_PEND), 0)
    INTO lnSaldo
    FROM CCC_MOVIM_CUENT_CORRI MCC
    WHERE MCC.CLIE_OID_CLIE = lnOidCliente
    AND MCC.IMP_PEND <> 0
    AND MCC.FEC_VENC <= ldFechaVencimiento;

    lsSaldo := TRIM(TO_CHAR(lnSaldo, '999999990.99'));

    -- Obtenemos la informacion del cliente
    SELECT VAL_APE1 || ' ' || VAL_APE2 || ', ' || VAL_NOM1 || ' ' || VAL_NOM2
    INTO lsNombreCliente
    FROM MAE_CLIEN
    WHERE COD_CLIE = psCodigoCliente;

    lsCodigoTerritorio := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(psCodigoCliente, 'COD_TERR');
    lsCodigoZona := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(psCodigoCliente, 'COD_ZONA');

    lsDireccion2 := GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(lnOidPais, lnOidCliente, 3) || '/' ||
                    GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(lnOidPais, lnOidCliente, 2) || '/' ||
                    GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(lnOidPais, lnOidCliente, 1);

    -- Inicio Cupon
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<frmecc>'), '<frmecc>');

    -- Inicio cabecera
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<cab>'), '<cab>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<ccon>'), '<ccon>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(psCodigoCliente), psCodigoCliente);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</ccon>'), '</ccon>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<ncon>'), '<ncon>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsNombreCliente), lsNombreCliente);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</ncon>'), '</ncon>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<czon>'), '<czon>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsCodigoZona), lsCodigoZona);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</czon>'), '</czon>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<fcam>'), '<fcam>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsPeriodo), lsPeriodo);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</fcam>'), '</fcam>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<cter>'), '<cter>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsCodigoTerritorio), lsCodigoTerritorio);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</cter>'), '</cter>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<dir1>'), '<dir1>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsDireccion1), lsDireccion1);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</dir1>'), '</dir1>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<dir2>'), '<dir2>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsDireccion2), lsDireccion2);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</dir2>'), '</dir2>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<fec>'), '<fec>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(psFechaFacturacion), psFechaFacturacion);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</fec>'), '</fec>');

    -- Fin Cabecera
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</cab>'), '</cab>');

    -- Inicio Bloque Cupon
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<blqcp>'), '<blqcp>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<ccon>'), '<ccon>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(psCodigoCliente), psCodigoCliente);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</ccon>'), '</ccon>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<ncon>'), '<ncon>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsNombreCliente), lsNombreCliente);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</ncon>'), '</ncon>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<czon>'), '<czon>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsCodigoZona), lsCodigoZona);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</czon>'), '</czon>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<cter>'), '<cter>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsCodigoTerritorio), lsCodigoTerritorio);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</cter>'), '</cter>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<saldo>'), '<saldo>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(lsSaldo), lsSaldo);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</saldo>'), '</saldo>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<fec>'), '<fec>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(psFechaFacturacion), psFechaFacturacion);
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</fec>'), '</fec>');

    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<fecven>'), '<fecven>');
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH(TO_CHAR(ldFechaVencimiento, 'DD/MM/YYYY')), TO_CHAR(ldFechaVencimiento, 'DD/MM/YYYY'));
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</fecven>'), '</fecven>');

    -- Fin Bloque Cupon
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</blqcp>'), '</blqcp>');

    IF psIndicadorDetalleCCC = 'S' THEN
        -- Detalle Vacio
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<detctacte><txt><t/><t/><t/><t/><tr/><tr/><tr/></txt></detctacte>'), '<detctacte><txt><t/><t/><t/><t/><tr/><tr/><tr/></txt></detctacte>');
    END IF;

    -- Detalle Vacio
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('<detalle><txt><t/><tr/><tr/><tr/></txt><txt><t/><tr/><tr/><tr/></txt></detalle>'), '<detalle><txt><t/><tr/><tr/><tr/></txt><txt><t/><tr/><tr/><tr/></txt></detalle>');

    -- Fin Cupon
    DBMS_LOB.writeappend(l_CLOBDest, LENGTH('</frmecc>'), '</frmecc>');

END IMP_PR_GENER_CUPON;
/

