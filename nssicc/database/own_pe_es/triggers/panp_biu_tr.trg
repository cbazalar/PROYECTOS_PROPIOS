CREATE OR REPLACE TRIGGER "PANP_BIU_TR" 
 BEFORE INSERT OR UPDATE
 ON INC_PARAM_NIVEL_PREMI
 FOR EACH ROW
BEGIN
  :new.fec_ulti_actu := sysdate;
END;
/

