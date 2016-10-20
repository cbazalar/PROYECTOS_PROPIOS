CREATE OR REPLACE PROCEDURE "IMP_PR_ACTUA_SALDO_CUPON_2" AS


-- Variables usadas para el reemplazo
l_CLOB              CLOB;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

-- Cursor principal
CURSOR c_paquete IS
SELECT
S.COR_PADO,
S.COD_CLIE,
/*
TRIM(SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, '<saldo>'), INSTR(S.XML_CONS, '</saldo>')  + DBMS_LOB.GETLENGTH('</saldo>') - (INSTR(S.XML_CONS, '<saldo>')))) VAL_ACTU,
'<saldo>' ||
TRIM(TO_CHAR(
TO_NUMBER(
TRIM(SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, '<saldo>') + DBMS_LOB.GETLENGTH('<saldo>'), INSTR(S.XML_CONS, '</saldo>') - (INSTR(S.XML_CONS, '<saldo>') + DBMS_LOB.GETLENGTH('<saldo>'))))
, '9999999.00') + 4.20
, '9999999.00')
)
 || '</saldo>' VAL_REEM,*/
TRIM(SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, '<cter>'), INSTR(S.XML_CONS, '</cter>')  + DBMS_LOB.GETLENGTH('</cter>') - (INSTR(S.XML_CONS, '<cter>')))) val_actu2,
'<cter>' || TRIM(SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, '<czon>') + DBMS_LOB.GETLENGTH('<czon>'), INSTR(S.XML_CONS, '</czon>') - (INSTR(S.XML_CONS, '<czon>') + DBMS_LOB.GETLENGTH('<czon>')))) || '</cter>' VAL_REEM2
FROM IMP_PAQUE_DOCUM s;

r_paquete c_paquete%ROWTYPE;

BEGIN
  -- Iteramos sobre el cursor
/* Iteramos sobre cada una de las lineas del paquete documentario */
/*
OPEN c_paquete;
LOOP
FETCH c_paquete INTO r_paquete;
EXIT WHEN c_paquete%NOTFOUND;
  SELECT XML_CONS INTO l_CLOB
  FROM IMP_PAQUE_DOCUM
  WHERE COD_CLIE = r_paquete.COD_CLIE FOR UPDATE;
  IMP_PKG_PROCE_COMPA.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.VAL_ACTU, r_paquete.VAL_REEM, 1);
END LOOP;
CLOSE c_paquete;
*/
OPEN c_paquete;
LOOP
FETCH c_paquete INTO r_paquete;
EXIT WHEN c_paquete%NOTFOUND;
  SELECT XML_CONS INTO l_CLOB
  FROM IMP_PAQUE_DOCUM
  WHERE COD_CLIE = r_paquete.COD_CLIE FOR UPDATE;
  IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.VAL_ACTU2, r_paquete.VAL_REEM2, 1);
END LOOP;
CLOSE c_paquete;

END IMP_PR_ACTUA_SALDO_CUPON_2;
/

