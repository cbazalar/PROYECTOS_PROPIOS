CREATE OR REPLACE TRIGGER "MAFA_BIU_TR" 
 BEFORE INSERT OR UPDATE
 ON PRE_MATRI_FACTU
 FOR EACH ROW
BEGIN
  :NEW.fec_ulti_actu := SYSDATE;
END;
/

