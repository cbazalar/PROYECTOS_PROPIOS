CREATE OR REPLACE TRIGGER "COCI_TR" 
 BEFORE INSERT OR UPDATE
 ON FAC_CONTR_CIERR
 FOR EACH ROW
BEGIN
  :new.fec_ulti_actu := sysdate;
EXCEPTION
  WHEN OTHERS THEN
	 RAISE_APPLICATION_ERROR(-20002, 'Error, sqlerrm'||sqlerrm);
END;
/
