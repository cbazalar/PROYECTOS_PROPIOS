CREATE OR REPLACE PROCEDURE "IMP_PR_ACTUA_SALDO_CUPON" AS


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
TRIM(SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, '<linea10>') + DBMS_LOB.GETLENGTH('<linea10>'), INSTR(S.XML_CONS, '</linea10>') - (INSTR(S.XML_CONS, '<linea10>') + DBMS_LOB.GETLENGTH('<linea10>')))) MONTO,
TRIM(SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, '<saldo>'), INSTR(S.XML_CONS, '</saldo>')  + DBMS_LOB.GETLENGTH('</saldo>') - (INSTR(S.XML_CONS, '<saldo>')))) VAL_ACTU,
'<saldo>' || TRIM(SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, '<linea10>') + DBMS_LOB.GETLENGTH('<linea10>'), INSTR(S.XML_CONS, '</linea10>') - (INSTR(S.XML_CONS, '<linea10>') + DBMS_LOB.GETLENGTH('<linea10>')))) || '</saldo>' VAL_REEM
FROM IMP_PAQUE_DOCUM s
WHERE S.COR_PADO != 128;

r_paquete c_paquete%ROWTYPE;

BEGIN
  -- Iteramos sobre el cursor
/* Iteramos sobre cada una de las lineas del paquete documentario */
OPEN c_paquete;
LOOP
FETCH c_paquete INTO r_paquete;
EXIT WHEN c_paquete%NOTFOUND;
  SELECT XML_CONS INTO l_CLOB
  FROM IMP_PAQUE_DOCUM
  WHERE COR_PADO = r_paquete.COR_PADO FOR UPDATE;
  IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.VAL_ACTU, r_paquete.VAL_REEM, 1);
END LOOP;
CLOSE c_paquete;

END IMP_PR_ACTUA_SALDO_CUPON;
/

