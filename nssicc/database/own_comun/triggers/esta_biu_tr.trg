CREATE OR REPLACE TRIGGER "ESTA_BIU_TR" 
 BEFORE INSERT OR UPDATE
 ON MAE_ESTAT_CLIEN
 FOR EACH ROW

BEGIN
  :new.fec_ulti_actu := sysdate;
END;
/

