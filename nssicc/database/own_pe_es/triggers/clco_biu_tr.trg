CREATE OR REPLACE TRIGGER "CLCO_BIU_TR" 
 BEFORE INSERT OR UPDATE
 ON MAE_CLIEN_COMUN
 FOR EACH ROW
BEGIN
  :new.fec_ulti_actu := sysdate;
END;
/

