CREATE OR REPLACE TRIGGER "PRI_TRG_AIU_DATOS_ADICI" AFTER
INSERT OR UPDATE OF "ESTA_OID_ESTA_CLIE"
ON "MAE_CLIEN_DATOS_ADICI" FOR EACH ROW
DECLARE
 v_oid_clie number;
 contador number;
BEGIN
 IF INSERTING THEN
    v_oid_clie:=:new.clie_oid_clie;
 ELSE
    v_oid_clie:=:old.clie_oid_clie;
 END IF;
 SELECT COUNT(*) INTO contador FROM PRI_OID_CONSU WHERE oid_clie=v_oid_clie;
 IF contador=0 then
   INSERT INTO PRI_OID_CONSU values (v_oid_clie,sysdate);
 ELSE
   UPDATE PRI_OID_CONSU SET fecha=sysdate WHERE oid_clie=v_oid_clie;
 END IF;
END;
/

