CREATE OR REPLACE TRIGGER "CLDI_BIU_TR" 
 BEFORE INSERT OR UPDATE
 ON MAE_CLIEN_DIREC
 FOR EACH ROW
BEGIN
  :new.fec_ulti_actu := sysdate;
END;
/

