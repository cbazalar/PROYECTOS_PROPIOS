CREATE OR REPLACE TRIGGER CLAS_BIU_TR BEFORE
INSERT
OR UPDATE ON MAE_CLIEN_CLASI FOR EACH ROW
BEGIN
  :new.fec_ulti_actu := sysdate;
END;
/

