create or replace trigger "I18N_BU_TR" 
instead OF UPDATE ON GEN_I18N_SICC
DECLARE
  w_tipo_objeto  VARCHAR2(30);
BEGIN
  SELECT MAX(object_type)
  INTO w_tipo_objeto
  FROM user_objects
  WHERE object_name = :OLD.ATTR_ENTI;

  IF w_tipo_objeto = 'TABLE' THEN
    UPDATE GEN_I18N_SICC_PAIS
    SET oid_i18n      = :NEW.oid_i18n
       ,attr_enti     = :NEW.attr_enti
       ,attr_num_atri = :NEW.attr_num_atri
       ,idio_oid_idio = :NEW.idio_oid_idio
       ,val_i18n      = :NEW.val_i18n
       ,val_oid       = :NEW.val_oid
    WHERE oid_i18n = :OLD.OID_I18N;
  ELSIF w_tipo_objeto = 'SYNONYM' THEN
    UPDATE gen_i18n_sicc_comun
    SET oid_i18n      = :NEW.oid_i18n
       ,attr_enti     = :NEW.attr_enti
       ,attr_num_atri = :NEW.attr_num_atri
       ,idio_oid_idio = :NEW.idio_oid_idio
       ,val_i18n      = :NEW.val_i18n
       ,val_oid       = :NEW.val_oid
    WHERE oid_i18n = :OLD.OID_I18N;
  ELSE
    RAISE_APPLICATION_ERROR( -20001, 'Error: la tabla ' || :OLD.ATTR_ENTI || ' no existe' );
  END IF;
END;
/

