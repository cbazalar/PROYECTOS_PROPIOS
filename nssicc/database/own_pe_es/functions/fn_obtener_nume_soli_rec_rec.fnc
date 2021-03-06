CREATE OR REPLACE FUNCTION "FN_OBTENER_NUME_SOLI_REC_REC" (OID_CABE IN NUMBER)
RETURN NUMBER
IS
 VAL_NUME_SOLI_AUX NUMBER;
 OID_CABE_ANT NUMBER;
 OID_CABE2 NUMBER;
BEGIN
 OID_CABE2 := OID_CABE;
 WHILE (OID_CABE2 IS NOT NULL)
 LOOP
      OID_CABE_ANT := OID_CABE2;
      SELECT SOCA_OID_DOCU_REFE INTO OID_CABE2 FROM PED_SOLIC_CABEC WHERE OID_SOLI_CABE = OID_CABE2;
      IF (OID_CABE2 IS NULL) THEN
     SELECT VAL_NUME_SOLI INTO VAL_NUME_SOLI_AUX FROM PED_SOLIC_CABEC WHERE OID_SOLI_CABE = OID_CABE_ANT;
   END IF;
 END LOOP;
 RETURN VAL_NUME_SOLI_AUX;

EXCEPTION
   WHEN NO_DATA_FOUND THEN
   SELECT VAL_NUME_SOLI INTO VAL_NUME_SOLI_AUX FROM PED_SOLIC_CABEC WHERE OID_SOLI_CABE = OID_CABE_ANT;
   RETURN VAL_NUME_SOLI_AUX;
END;
/

