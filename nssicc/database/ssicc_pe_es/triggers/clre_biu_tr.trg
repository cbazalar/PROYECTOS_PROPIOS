CREATE OR REPLACE TRIGGER CLRE_BIU_TR BEFORE
INSERT OR UPDATE ON MAE_REFER FOR EACH ROW
BEGIN
  :new.fec_modi := sysdate;
END;
/

