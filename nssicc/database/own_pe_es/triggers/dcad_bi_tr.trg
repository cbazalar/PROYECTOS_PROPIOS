CREATE OR REPLACE TRIGGER "DCAD_BI_TR" 
 BEFORE INSERT
 ON CCC_DETAL_CARGO_ABONO_DIREC
 FOR EACH ROW
BEGIN
  :NEW.fec_crea := SYSDATE;
END;
/

