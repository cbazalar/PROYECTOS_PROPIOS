CREATE OR REPLACE TRIGGER "CUAD_BIU_TR" 
 BEFORE INSERT OR UPDATE
 ON MAE_CLIEN_UNIDA_ADMIN
 FOR EACH ROW
BEGIN
  :new.fec_ulti_actu := sysdate;
END;
/
