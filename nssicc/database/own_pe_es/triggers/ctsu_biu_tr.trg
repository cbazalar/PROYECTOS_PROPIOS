CREATE OR REPLACE TRIGGER "CTSU_BIU_TR" 
 BEFORE INSERT OR UPDATE OF SBTI_OID_SUBT_CLIE
 ON MAE_CLIEN_TIPO_SUBTI
 FOR EACH ROW
BEGIN

SELECT TICL_OID_TIPO_CLIE
INTO :NEW.TICL_OID_TIPO_CLIE
FROM MAE_SUBTI_CLIEN
WHERE OID_SUBT_CLIE = :NEW.SBTI_OID_SUBT_CLIE;
:new.fec_ulti_actu := sysdate;
END;
/

