CREATE OR REPLACE TRIGGER "MVCC_BIU_TR" 
 BEFORE INSERT OR UPDATE
 ON CCC_MOVIM_CUENT_CORRI
 FOR EACH ROW
DECLARE

  w_aux number(1);
BEGIN
  :new.fec_ulti_actu := sysdate;
END;
/

