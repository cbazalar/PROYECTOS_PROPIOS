CREATE OR REPLACE PROCEDURE "IMP_PR_GENER_NOTA_CREDI"
    (p_val_nume_soli IN NUMBER, p_num_docu_cont_inte IN NUMBER) AS

-- Constantes
COD_NOTA_CREDI_BOLET VARCHAR2(3) := '020';
COD_NOTA_CREDI_FACTU VARCHAR2(3) := '021';
-- Cursor de cabecera
/*
CURSOR c_cabecera IS
SELECT D.COD_CLIE,
       NVL(B.VAL_SERI_DOCU_LEGA, ' ') VAL_SERI_DOCU_LEGA,
       B.VAL_APE1 || ' ' || B.VAL_APE2 || ', ' || B.VAL_NOM1 || ' ' || B.VAL_NOM2 NOM_CLIE,
       B.NUM_DOCU_CONT_INTE,
       F.COD_PERI,
       B.FEC_EMIS,
       Z.COD_ZONA,
       T.COD_TERR,
       B.VAL_DIRE_COMP,
       B.VAL_NUME_IDEN_FISC,
       A.VAL_NUME_SOLI VAL_NUME_SOLI,
       H.VAL_NUME_SOLI VAL_NUME_SOLI_REFE,
       J.COD_PERI COD_PERI_REFE,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(2001, D.OID_CLIE, 4) VAL_CALL,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(2001, D.OID_CLIE, 3) VAL_DIST,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(2001, D.OID_CLIE, 2) VAL_PROV,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(2001, D.OID_CLIE, 1) VAL_DEPA,
       ABS(A.VAL_IMPO_FLET_TOTA_LOCA) VAL_IMPO_FLET_TOTA_LOCA,
    K.COD_TIPO_DOCU,
    L.VAL_TASA_IMPU
  FROM PED_SOLIC_CABEC A,
       FAC_DOCUM_CONTA_CABEC B,
       MAE_CLIEN D,
       CRA_PERIO E,
       SEG_PERIO_CORPO F,
       ZON_ZONA Z,
       ZON_TERRI T,
    (SELECT DISTINCT X.SOCA_OID_DOCU_REFE, X.SOCA_OID_SOLI_CABE
     FROM PED_SOLIC_CABEC X, PED_SOLIC_CABEC Y
  WHERE Y.OID_SOLI_CABE = X.SOCA_OID_SOLI_CABE
  AND Y.VAL_NUME_SOLI = p_val_nume_soli) G,
--       PED_SOLIC_CABEC G,
       PED_SOLIC_CABEC H,
       CRA_PERIO I,
       SEG_PERIO_CORPO J,
    FAC_TIPO_DOCUM K,
    PED_TASA_IMPUE L
 WHERE A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE
   AND D.OID_CLIE = A.CLIE_OID_CLIE
   AND B.PERD_OID_PERI = E.OID_PERI
   AND E.PERI_OID_PERI = F.OID_PERI
   AND B.ZZON_OID_ZONA = Z.OID_ZONA
   AND B.TERR_OID_TERR = T.OID_TERR
   AND A.OID_SOLI_CABE = G.SOCA_OID_SOLI_CABE
   AND G.SOCA_OID_DOCU_REFE = H.OID_SOLI_CABE(+)
   AND H.PERD_OID_PERI = I.OID_PERI(+)
   AND I.PERI_OID_PERI = J.OID_PERI(+)
   AND B.TIDO_OID_TIPO_DOCU = K.OID_TIPO_DOCU
   AND A.PAIS_OID_PAIS = L.PAIS_OID_PAIS
   AND L.VAL_INDI_IMPU = 'IGV'
   AND A.VAL_NUME_SOLI = p_val_nume_soli
   AND B.NUM_DOCU_CONT_INTE = p_num_docu_cont_inte;
*/

CURSOR c_cabecera IS
SELECT MAE_CLIEN.COD_CLIE,
       NVL(DOC_CONT.VAL_SERI_DOCU_LEGA, ' ') VAL_SERI_DOCU_LEGA,
    MAE_CLIEN.VAL_APE1 || ' ' || MAE_CLIEN.VAL_APE2 || ', ' || MAE_CLIEN.VAL_NOM1 || ' ' || MAE_CLIEN.VAL_NOM2 NOM_CLIE,
    DOC_CONT.NUM_DOCU_CONT_INTE,
    SEG_PERIO_CORPO.COD_PERI,
    DOC_CONT.FEC_EMIS,
    ZON_ZONA.COD_ZONA,
    ZON_TERRI.COD_TERR,
    DOC_CONT.VAL_DIRE_COMP,
       DOC_CONT.VAL_NUME_IDEN_FISC,
       CON_DEVO.VAL_NUME_SOLI VAL_NUME_SOLI,
    SOL_REFE.VAL_NUME_SOLI VAL_NUME_SOLI_REFE,
       SEG_PERIO_CORPO_REFE.COD_PERI COD_PERI_REFE,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(MAE_CLIEN.PAIS_OID_PAIS, MAE_CLIEN.OID_CLIE, 4) VAL_CALL,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(MAE_CLIEN.PAIS_OID_PAIS, MAE_CLIEN.OID_CLIE, 3) VAL_DIST,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(MAE_CLIEN.PAIS_OID_PAIS, MAE_CLIEN.OID_CLIE, 2) VAL_PROV,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(MAE_CLIEN.PAIS_OID_PAIS, MAE_CLIEN.OID_CLIE, 1) VAL_DEPA,
    ABS(CON_DEVO.VAL_IMPO_FLET_TOTA_LOCA) VAL_IMPO_FLET_TOTA_LOCA,
    FAC_TIPO_DOCUM.COD_TIPO_DOCU,
    PED_TASA_IMPUE.VAL_TASA_IMPU
FROM FAC_DOCUM_CONTA_CABEC DOC_CONT,
     PED_SOLIC_CABEC CON_DEVO,
     PED_SOLIC_CABEC SOL_REFE,
  MAE_CLIEN,
  CRA_PERIO,
  SEG_PERIO_CORPO,
  CRA_PERIO CRA_PERIO_REFE,
  SEG_PERIO_CORPO SEG_PERIO_CORPO_REFE,
  ZON_ZONA,
  ZON_TERRI,
  FAC_TIPO_DOCUM,
  PED_TASA_IMPUE
WHERE DOC_CONT.SOCA_OID_SOLI_CABE = CON_DEVO.OID_SOLI_CABE
  AND CON_DEVO.SOCA_OID_DOCU_REFE = SOL_REFE.OID_SOLI_CABE (+)
  AND MAE_CLIEN.OID_CLIE = CON_DEVO.CLIE_OID_CLIE
  AND DOC_CONT.PERD_OID_PERI = CRA_PERIO.OID_PERI
  AND CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
  AND DOC_CONT.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA
  AND DOC_CONT.TERR_OID_TERR = ZON_TERRI.OID_TERR
  AND DOC_CONT.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU
  AND SOL_REFE.PERD_OID_PERI = CRA_PERIO_REFE.OID_PERI (+)
  AND CRA_PERIO_REFE.PERI_OID_PERI = SEG_PERIO_CORPO_REFE.OID_PERI (+)
  AND CON_DEVO.PAIS_OID_PAIS = PED_TASA_IMPUE.PAIS_OID_PAIS
  AND PED_TASA_IMPUE.VAL_INDI_IMPU = 'IGV'
  AND DOC_CONT.NUM_DOCU_CONT_INTE = p_num_docu_cont_inte
  AND CON_DEVO.VAL_NUME_SOLI = p_val_nume_soli;

r_cabecera c_cabecera%ROWTYPE;

-- Cursor Detalle
CURSOR c_detalle IS
SELECT X.NUM_UNID_ATEN,
       P.VAL_I18N DES_PROD,
       SUBSTR(R.COD_PERI, -2, 2) || S.VAL_CODI_VENT || DECODE(X.VAL_PREC_UNIT, 0, '0', '8') VAL_CODI_VENT,
       X.VAL_PREC_UNIT,
       X.VAL_PREC_TOTA_UNIT_LOCA,
       DECODE(X.VAL_PREC_UNIT, 0, X.VAL_PREC_TOTA_UNIT_LOCA, X.VAL_PREC_UNIT) VAL_PREC_UNIT_LOCA,
       X.VAL_PREC_FACT_UNIT,
       ABS(X.NUM_UNID_ATEN) * X.VAL_PREC_FACT_UNIT VAL_PREC_FACT_TOTA,
       X.IMP_IMPU_TOTA_LOCA,
       DECODE(X.IND_DENT_FUER_CAJA_BOLS, 'C', 0, 1) IND_DENT_CAJA
FROM   GEN_I18N_SICC_PAIS P,
       CRA_PERIO Q,
       SEG_PERIO_CORPO R,
       PED_SOLIC_POSIC S,
(
SELECT ABS(C.NUM_UNID_ATEN) NUM_UNID_ATEN,
       DECODE(D.COD_TIPO_DOCU, COD_NOTA_CREDI_BOLET, C.VAL_PREC_CATA_UNIT_LOCA, C.VAL_PREC_SIN_IMPU_UNIT) VAL_PREC_UNIT,
       C.VAL_PREC_TOTA_UNIT_LOCA,
       DECODE(D.COD_TIPO_DOCU, COD_NOTA_CREDI_BOLET, C.VAL_PREC_FACT_UNIT_LOCA, C.VAL_PREC_NETO_UNIT_LOCA) VAL_PREC_FACT_UNIT,
       C.IND_DENT_FUER_CAJA_BOLS,
       C.PROD_OID_PROD,
       A.PERD_OID_PERI,
       A.OID_SOLI_CABE,
       C.SOPO_OID_SOLI_POSI,
       ABS(C.IMP_IMPU_TOTA_LOCA) IMP_IMPU_TOTA_LOCA,
       C.NUM_LINEA
FROM  PED_SOLIC_CABEC A,
      FAC_DOCUM_CONTA_CABEC B,
      FAC_DOCUM_CONTA_LINEA C,
      FAC_TIPO_DOCUM D
WHERE A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE
   AND B.OID_CABE = C.DCCA_OID_CABE
   AND B.TIDO_OID_TIPO_DOCU = D.OID_TIPO_DOCU
   AND C.NUM_UNID_ATEN != 0
   AND A.VAL_NUME_SOLI = p_val_nume_soli
   AND B.NUM_DOCU_CONT_INTE = p_num_docu_cont_inte
) X
WHERE  P.ATTR_ENTI = 'MAE_PRODU'
   AND P.IDIO_OID_IDIO = 1
   AND P.VAL_OID = X.PROD_OID_PROD
   AND X.SOPO_OID_SOLI_POSI = S.OID_SOLI_POSI
   AND S.IND_NO_IMPR = 0
   AND X.PERD_OID_PERI = Q.OID_PERI
   AND Q.PERI_OID_PERI = R.OID_PERI
ORDER BY 10, X.NUM_LINEA;
r_detalle c_detalle%ROWTYPE;

/*
SELECT ABS(C.NUM_UNID_ATEN) NUM_UNID_ATEN,
       I.VAL_I18N DES_PROD,
       SUBSTR(S.COD_PERI, -2, 2) || D.VAL_CODI_VENT || DECODE(C.VAL_PREC_CATA_UNIT_LOCA, 0, '0', '8') VAL_CODI_VENT,
       C.VAL_PREC_CATA_UNIT_LOCA,
       C.VAL_PREC_TOTA_UNIT_LOCA,
       DECODE(C.VAL_PREC_CATA_UNIT_LOCA, 0, C.VAL_PREC_TOTA_UNIT_LOCA, C.VAL_PREC_CATA_UNIT_LOCA) VAL_PREC_UNIT_LOCA,
       C.VAL_PREC_FACT_UNIT_LOCA,
       ABS(C.NUM_UNID_ATEN) * C.VAL_PREC_FACT_UNIT_LOCA VAL_PREC_FACT_LOCA,
       DECODE(C.IND_DENT_FUER_CAJA_BOLS, 'C', 0, 1) IND_DENT_CAJA
  FROM PED_SOLIC_CABEC A,
       FAC_DOCUM_CONTA_CABEC B,
       FAC_DOCUM_CONTA_LINEA C,
       PED_SOLIC_POSIC D,
       GEN_I18N_SICC_PAIS I,
       CRA_PERIO P,
       SEG_PERIO_CORPO S
 WHERE A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE
   AND B.OID_CABE = C.DCCA_OID_CABE
   AND C.SOPO_OID_SOLI_POSI = D.OID_SOLI_POSI
   AND I.ATTR_ENTI = 'MAE_PRODU'
   AND I.IDIO_OID_IDIO = 1
   AND I.VAL_OID = C.PROD_OID_PROD
   AND B.PERD_OID_PERI = P.OID_PERI
   AND P.PERI_OID_PERI = S.OID_PERI
   AND C.NUM_UNID_ATEN != 0
   AND D.IND_NO_IMPR = 0
   AND A.VAL_NUME_SOLI = p_val_nume_soli
   AND B.NUM_DOCU_CONT_INTE = p_num_docu_cont_inte
 ORDER BY 9, C.NUM_LINEA;
*/
-- Variables usadas para la generacion del archivo
l_textoNumeroPagina VARCHAR2(100) := '                                                                   PAG.  1 DE   1  ';
l_cambioLineaEspacioBlanco VARCHAR2(2) := CHR(10) || CHR(32);
l_cambioLinea VARCHAR2(1) := CHR(10);
l_separadorDetalle VARCHAR2(100) := '=================================================================================';
l_textoActual VARCHAR2(100) := '';

-- Variables para almacenar la direccion
l_direccionLinea1 VARCHAR2(100) := ' ';
l_direccionLinea2 VARCHAR2(100) := ' ';
l_direccionLinea3 VARCHAR2(100) := ' ';

-- Variables para almacenar la Ubicacion Geografica
l_ubicacionGeografica VARCHAR2(100) := ' ';
l_ubicacionLinea1 VARCHAR2(100) := ' ';
l_ubicacionLinea2 VARCHAR2(100) := ' ';

-- Variables para guardar los montos y contadores
l_numeroDetalles      NUMBER := 0;
l_sumaUnidades        NUMBER := 0;
l_sumaPrecioCatalogo  NUMBER(12, 2) := 0.0;
l_sumaPrecioFacturado NUMBER(12, 2) := 0.0;
l_sumaPrecioDescuento NUMBER(12, 2) := 0.0;
l_igv                 NUMBER(12, 2) := 0.0;
l_numeroCambiosLinea  NUMBER := 0;
-- Variables usadas para la carga del archivo
l_CLOBDest              CLOB;
l_flagSeparador         BOOLEAN := FALSE;
BEGIN
  -- Eliminamos la nota de credito en caso exista
  DELETE FROM IMP_TMP_NOTA_CREDI
  WHERE VAL_NUME_SOLI = p_val_nume_soli
  AND NUM_DOCU_CONT_INTE = p_num_docu_cont_inte;
  -- Obtenemos la informacion de la cabecera
  OPEN c_cabecera;
  LOOP
  FETCH c_cabecera INTO r_cabecera;
  EXIT WHEN c_cabecera%NOTFOUND;
  BEGIN
       INSERT INTO IMP_TMP_NOTA_CREDI (
       COD_CLIE,
       VAL_SERI_DOCU_LEGA,
       NUM_DOCU_CONT_INTE,
       COD_PERI,
       FEC_EMIS,
       VAL_NUME_SOLI,
       VAL_NUME_SOLI_REFE,
       COD_PERI_REFE,
       TEX_NOTA_CRED)
       VALUES (
       r_cabecera.COD_CLIE,
       r_cabecera.VAL_SERI_DOCU_LEGA,
       r_cabecera.NUM_DOCU_CONT_INTE,
       r_cabecera.COD_PERI,
       r_cabecera.FEC_EMIS,
       r_cabecera.VAL_NUME_SOLI,
       r_cabecera.VAL_NUME_SOLI_REFE,
       r_cabecera.COD_PERI_REFE,
       EMPTY_CLOB())
       RETURNING TEX_NOTA_CRED INTO l_CLOBDest;
       -- Creamos el contenido de la nota de credito
       -- CABECERA
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoNumeroPagina), l_textoNumeroPagina);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       -- Campaña y fecha de emision
       l_textoActual := 'CAMPAÑA:' || RPAD(r_cabecera.COD_PERI, 9, ' ') || 'EMISION:' || TO_CHAR(r_cabecera.FEC_EMIS, 'DD/MM/YYYY');
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       -- Boleta y fecha de referencia
       l_textoActual := 'BOL. DESPACHO REF:' || RPAD(r_cabecera.VAL_NUME_SOLI_REFE, 14, ' ') || 'CMP. REF:' || r_cabecera.COD_PERI_REFE;
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       -- Zona - Territorio y Codigo de Cliente
       l_textoActual := 'ZONA:' || r_cabecera.COD_ZONA || '-' || r_cabecera.COD_TERR || '   CUENTA:' || r_cabecera.COD_CLIE;
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       -- Nombre de la Cliente
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(r_cabecera.NOM_CLIE), r_cabecera.NOM_CLIE);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       -- DNI/RUC
       IF r_cabecera.COD_TIPO_DOCU = COD_NOTA_CREDI_BOLET THEN
           l_textoActual := 'DNI:' || r_cabecera.VAL_NUME_IDEN_FISC;
       ELSE
        l_textoActual := 'RUC:' || r_cabecera.VAL_NUME_IDEN_FISC;
       END IF;
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       -- Direccion
       IF r_cabecera.VAL_DIRE_COMP IS NOT NULL THEN
           l_direccionLinea1 := SUBSTR(r_cabecera.VAL_DIRE_COMP, 1, 47);
           IF LENGTH(r_cabecera.VAL_DIRE_COMP) > 47 THEN
               l_direccionLinea2 := SUBSTR(r_cabecera.VAL_DIRE_COMP, 48, 47);
           END IF;
           IF LENGTH(r_cabecera.VAL_DIRE_COMP) > 94 THEN
               l_direccionLinea3 := SUBSTR(r_cabecera.VAL_DIRE_COMP, 95, 47);
           END IF;
       END IF;
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_direccionLinea1), l_direccionLinea1);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_direccionLinea2), l_direccionLinea2);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_direccionLinea3), l_direccionLinea3);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       -- Ubicacion Geografica y Codigo Interno
       l_ubicacionGeografica := r_cabecera.VAL_DIST || '/' || r_cabecera.VAL_PROV || '/' || r_cabecera.VAL_DEPA;
       IF r_cabecera.VAL_CALL IS NOT NULL THEN
           l_ubicacionGeografica := r_cabecera.VAL_CALL || '/' || l_ubicacionGeografica;
       END IF;
       l_ubicacionLinea1 := SUBSTR(l_ubicacionGeografica, 1, 47);
       IF LENGTH(l_ubicacionGeografica) > 47 THEN
           l_ubicacionLinea2 := SUBSTR(l_ubicacionGeografica, 48, 47);
       END IF;
       l_textoActual := RPAD(l_ubicacionLinea1, 52, ' ') || 'CODIGO INTERNO:' || LPAD(r_cabecera.NUM_DOCU_CONT_INTE, 14, ' ');
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       -- Numero Boleta Despacho
       l_textoActual := RPAD(l_ubicacionLinea2, 52, ' ') || 'BOLETA DESPACHO:' || LPAD(r_cabecera.VAL_NUME_SOLI, 13, ' ');
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       -- DETALLE
       OPEN c_detalle;
       LOOP
       FETCH c_detalle INTO r_detalle;
       EXIT WHEN c_detalle%NOTFOUND;
        BEGIN
        IF r_detalle.IND_DENT_CAJA != 0 AND l_flagSeparador = FALSE THEN
            -- Separador detalle
            DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_separadorDetalle), l_separadorDetalle);
            DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
            l_flagSeparador := TRUE;
        END IF;
        l_textoActual := LPAD(r_detalle.NUM_UNID_ATEN, 4, ' ') || ' ' || RPAD(r_detalle.DES_PROD, 37, ' ') || RPAD(r_detalle.VAL_CODI_VENT, 8, ' ');
        -- Precio Catalogo Unitario
        l_textoActual := l_textoActual || LPAD(TRIM(TO_CHAR(r_detalle.VAL_PREC_UNIT_LOCA, '9999999990.00')), 10, ' ');
        -- Precio Facturado Unitario
        l_textoActual := l_textoActual || LPAD(TRIM(TO_CHAR(r_detalle.VAL_PREC_FACT_UNIT, '9999999990.00')), 9, ' ');
        -- Precio Facturado Unitario * Numero Unidades
        l_textoActual := l_textoActual || LPAD(TRIM(TO_CHAR(r_detalle.NUM_UNID_ATEN * r_detalle.VAL_PREC_FACT_UNIT, '9999999990.00')), 12, ' ');
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
        l_numeroDetalles := l_numeroDetalles + 1;
        l_sumaUnidades := l_sumaUnidades + r_detalle.NUM_UNID_ATEN;
        l_sumaPrecioCatalogo := l_sumaPrecioCatalogo + r_detalle.NUM_UNID_ATEN * r_detalle.VAL_PREC_UNIT_LOCA;
      -- Se comenta la siguiente linea para hacer el calculo del igv al final
      -- l_igv := l_igv + r_detalle.IMP_IMPU_TOTA_LOCA;
        IF r_detalle.VAL_PREC_UNIT = 0 THEN
            l_sumaPrecioDescuento := l_sumaPrecioDescuento + r_detalle.NUM_UNID_ATEN * r_detalle.VAL_PREC_FACT_UNIT;
        ELSE
            l_sumaPrecioDescuento := l_sumaPrecioDescuento + r_detalle.NUM_UNID_ATEN * (r_detalle.VAL_PREC_UNIT_LOCA - r_detalle.VAL_PREC_FACT_UNIT);
        END IF;
       END;
       END LOOP;
       CLOSE c_detalle;
       IF l_flagSeparador = FALSE THEN
        -- Separador detalle
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_separadorDetalle), l_separadorDetalle);
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       END IF;
       -- Imprimos tantos cambios de linea como sean necesarios
       l_numeroCambiosLinea := 53 - l_numeroDetalles;
       FOR i IN 1..l_numeroCambiosLinea LOOP
           DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       END LOOP;
       -- Total de unidades
       l_textoActual := LPAD(l_sumaUnidades, 6, ' ') || ' UNIDADES';
       -- Total Precio Catalogo
       l_textoActual := l_textoActual || LPAD(TRIM(TO_CHAR(l_sumaPrecioCatalogo, '9999999990.00')), 17, ' ');
       -- Total Descuento
       l_textoActual := l_textoActual || LPAD('(' || TRIM(TO_CHAR(l_sumaPrecioDescuento, '9999999990.00')) || ')', 14, ' ');
       -- Total Precio Facturado
       l_sumaPrecioFacturado := l_sumaPrecioCatalogo - l_sumaPrecioDescuento;
       l_textoActual := l_textoActual || LPAD(TRIM(TO_CHAR(l_sumaPrecioFacturado, '9999999990.00')), 22, ' ');
       -- Final del texto de totales
       l_textoActual := l_textoActual || LPAD(TRIM(TO_CHAR(r_cabecera.VAL_IMPO_FLET_TOTA_LOCA, '9999999990.00')), 13, ' ');
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);

       -- Monto Final en Texto
       l_sumaPrecioFacturado := l_sumaPrecioFacturado + r_cabecera.VAL_IMPO_FLET_TOTA_LOCA;
    IF r_cabecera.COD_TIPO_DOCU != COD_NOTA_CREDI_BOLET THEN
        l_igv := l_sumaPrecioFacturado * r_cabecera.VAL_TASA_IMPU / 100;
     l_sumaPrecioFacturado := l_sumaPrecioFacturado + l_igv;
    END IF;
       l_textoActual := 'SON: ' || GEN_FN_NUME_TO_TEXT(TRUNC(l_sumaPrecioFacturado)) || ' y ' || TO_CHAR((l_sumaPrecioFacturado - TRUNC(l_sumaPrecioFacturado)) * 100) || '/100';
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);

       -- Monto Final en Numeros
    IF r_cabecera.COD_TIPO_DOCU = COD_NOTA_CREDI_BOLET THEN
           l_textoActual := LPAD(' ', 57, ' ') || ' S/.' || LPAD(TRIM(TO_CHAR(l_sumaPrecioFacturado, '9999999990.00')), 20, ' ');
       ELSE
           l_textoActual := LPAD(TRIM(TO_CHAR(l_igv, '9999999990.00')), 57, ' ') || ' S/.' || LPAD(TRIM(TO_CHAR(l_sumaPrecioFacturado, '9999999990.00')), 20, ' ');
    END IF;
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
    -- Para el ultimo cambio de linea no consideramos el espacio en blanco
       DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLinea), l_cambioLinea);
  END;
  END LOOP;
  CLOSE c_cabecera;
  COMMIT;
END IMP_PR_GENER_NOTA_CREDI;
/

