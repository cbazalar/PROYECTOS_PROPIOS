create or replace trigger "I18N_BD_TR" 
instead OF DELETE ON GEN_I18N_SICC
DECLARE
  w_tipo_objeto  VARCHAR2(30);
BEGIN
  SELECT MAX(object_type)
  INTO w_tipo_objeto
  FROM user_objects
  WHERE object_name = :OLD.ATTR_ENTI;

  IF w_tipo_objeto = 'TABLE' THEN
    DELETE GEN_I18N_SICC_PAIS
    WHERE oid_i18n = :OLD.OID_I18N;
  ELSIF w_tipo_objeto = 'SYNONYM' THEN
    DELETE gen_i18n_sicc_comun
    WHERE oid_i18n = :OLD.OID_I18N;
  ELSE
    RAISE_APPLICATION_ERROR( -20001, 'Error: la tabla ' || :OLD.ATTR_ENTI || ' no existe' );
  END IF;
END;
/

