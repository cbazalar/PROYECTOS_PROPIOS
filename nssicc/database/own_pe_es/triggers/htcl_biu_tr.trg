CREATE OR REPLACE TRIGGER HTCL_BIU_TR BEFORE
INSERT
OR UPDATE ON MAE_HISTO_CLASI FOR EACH ROW
BEGIN
  :new.fec_modi := sysdate;
END;
/
