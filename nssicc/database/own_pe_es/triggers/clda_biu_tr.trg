CREATE OR REPLACE TRIGGER "CLDA_BIU_TR" 
 BEFORE INSERT OR UPDATE
 ON MAE_CLIEN_DATOS_ADICI
 FOR EACH ROW
BEGIN
  :new.fec_ulti_actu := sysdate;
END;
/
