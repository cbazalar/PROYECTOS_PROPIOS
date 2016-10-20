CREATE OR REPLACE TRIGGER "I18N_BI_TR" 
INSTEAD OF INSERT
ON GEN_I18N_SICC REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
DECLARE
  w_tipo_objeto  VARCHAR2(30);
BEGIN
  SELECT MAX(object_type)  INTO w_tipo_objeto FROM user_objects  WHERE object_name = :NEW.ATTR_ENTI;
  IF w_tipo_objeto = 'TABLE' THEN
    INSERT INTO GEN_I18N_SICC_PAIS(
       oid_i18n
      ,attr_enti
      ,attr_num_atri
      ,idio_oid_idio
      ,val_i18n
      ,val_oid )
    VALUES(
       :NEW.oid_i18n
      ,:NEW.attr_enti
      ,:NEW.attr_num_atri
      ,:NEW.idio_oid_idio
      ,:NEW.val_i18n
      ,:NEW.val_oid );
  ELSIF w_tipo_objeto = 'SYNONYM' THEN
    INSERT INTO gen_i18n_sicc_comun(
       oid_i18n
      ,attr_enti
      ,attr_num_atri
      ,idio_oid_idio
      ,val_i18n
      ,val_oid )
    VALUES(
       gen_i18c_seq.NEXTVAL
      ,:NEW.attr_enti
      ,:NEW.attr_num_atri
      ,:NEW.idio_oid_idio
      ,:NEW.val_i18n
      ,:NEW.val_oid );
  ELSE
    RAISE_APPLICATION_ERROR( -20001, 'Error: la tabla ' || :NEW.ATTR_ENTI || ' no existe' );
  END IF;
END;
/

