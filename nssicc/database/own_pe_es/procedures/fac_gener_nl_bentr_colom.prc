CREATE OR REPLACE PROCEDURE "FAC_GENER_NL_BENTR_COLOM" (ID_SPOOL IN VARCHAR2)
AS
   CURSOR CUR_1 IS
      	  SELECT GDI.OID_DOCU_IMPR,
	  		 DBE.VAL_CANT_PAGI,
			 GDI.VAL_BUFF,
			 DCC.VAL_SERI_DOCU_LEGA,
			 DCC.NUM_DOCU_LEGA
	  FROM FAC_DOCUM_BOLET_ENTRE DBE,
	  	   GEN_DOCUM_IMPRI GDI,
		   FAC_DOCUM_CONTA_CABEC DCC
	  WHERE GDI.OID_DOCU_IMPR = DBE.GDIM_OID_DOCU_IMPR
	    AND DCC.SOCA_OID_SOLI_CABE = DBE.SOCA_OID_SOLI_CABE
	  	AND DBE.GSPO_OID_SPOO = TO_NUMBER(ID_SPOOL);

   LEN               NUMBER;
   POSI              NUMBER;
   INI               NUMBER;
   POSFIN            NUMBER;
BEGIN
   FOR I IN CUR_1
   LOOP
      DECLARE
         BL1   BLOB;
         BL2   BLOB;
      BEGIN
         LEN := DBMS_LOB.GETLENGTH (I.VAL_BUFF);
         BL2 := I.VAL_BUFF;
         INI := 1;
         POSI := 1;

         FOR X IN 1 .. I.VAL_CANT_PAGI
         LOOP
           POSI := DBMS_LOB.INSTR (BL2, UTL_RAW.CAST_TO_RAW ('$$$$$$$$$$-$$$$$$$$$$')) - 1;
		   IF (POSI > 0) THEN
           	  IF (POSI > 32767) THEN
              	 BL1 := DBMS_LOB.SUBSTR (BL2, 32767, 1);
                 DBMS_LOB.APPEND (BL1, DBMS_LOB.SUBSTR (BL2, (POSI - 32767), 32768));
              ELSE
              	 BL1 := DBMS_LOB.SUBSTR (BL2, POSI, 1);
              END IF;

              INI := POSI + 22;
              DBMS_LOB.APPEND(BL1, UTL_RAW.CAST_TO_RAW(LPAD (I.VAL_SERI_DOCU_LEGA || '-' || TO_CHAR(I.NUM_DOCU_LEGA), 21)));

              POSFIN := (LEN - INI);

              IF (POSFIN > 32767) THEN
                 DBMS_LOB.APPEND (BL1, DBMS_LOB.SUBSTR (BL2, 32767, INI));
                 DBMS_LOB.APPEND (BL1, DBMS_LOB.SUBSTR (BL2, (POSFIN - 32767), (INI + 32767)));
              ELSE
                 DBMS_LOB.APPEND (BL1, DBMS_LOB.SUBSTR (BL2, POSFIN, INI));
              END IF;

              BL2 := BL1;
              BL1 := UTL_RAW.CAST_TO_RAW (' ');
           END IF;
         END LOOP;

         UPDATE GEN_DOCUM_IMPRI
            SET VAL_BUFF = BL2
          WHERE OID_DOCU_IMPR = I.OID_DOCU_IMPR;
      END;
   END LOOP;
END;
/

