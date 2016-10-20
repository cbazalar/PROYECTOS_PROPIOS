CREATE OR REPLACE FUNCTION "FN_OBT_IND_EJEC_ONLINE" (GRUP_PROC IN NUMBER, TIPO_SOL IN NUMBER)
RETURN NUMBER
IS
SALIDA NUMBER(1);
BEGIN
  SELECT PSP.IND_EJEC_ONLI INTO SALIDA
  FROM PED_SECUE_PROCE PSP
  WHERE PSP.GRPR_OID_GRUP_PROC = GRUP_PROC
    AND PSP.TSPA_OID_TIPO_SOLI_PAIS = TIPO_SOL
    AND ROWNUM = 1
  ORDER BY COD_SECU;
  RETURN SALIDA;
EXCEPTION
   WHEN NO_DATA_FOUND THEN
   RETURN NULL;
END;
/
