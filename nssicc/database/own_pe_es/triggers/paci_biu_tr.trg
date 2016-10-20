create or replace trigger "PACI_BIU_TR" 
   before INSERT OR UPDATE OF oid_part_conc_cabe
   ON inc_parti_concu_cabec
   REFERENCING NEW AS NEW OLD AS OLD
   FOR EACH ROW
BEGIN
   :NEW.val_grup_clie := :NEW.oid_part_conc_cabe;
END;
/

