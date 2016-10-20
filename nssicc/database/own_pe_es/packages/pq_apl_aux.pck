CREATE OR REPLACE PACKAGE "PQ_APL_AUX" as
-------ENCABEZADO-------
  ------------------------------------------------------------------------------
  -- Funcion de uso general que devuelve el valor internacionalizado
  ------------------------------------------------------------------------------
  FUNCTION Valor_Gen_I18n_Sicc(
   oid_idioma_param IN INT,
   val_oid_param IN INT,
   attr_enti_param IN VARCHAR2
  ) RETURN VARCHAR2;

  PROCEDURE Borrar_Bloqueo_CCC(esteTextoNoSeUsa in VARCHAR2, dummy OUT VARCHAR2);

  PROCEDURE Insertar_Bloqueo_CCC(codUsua IN VARCHAR2, descProc in VARCHAR2, dummy OUT VARCHAR2);

END pq_apl_aux;
/

CREATE OR REPLACE PACKAGE BODY "PQ_APL_AUX" as
  ------------------------------------------------------------------------------
  -- Funcion de uso general que devuelve el valor internacionalizado
  ------------------------------------------------------------------------------
FUNCTION Valor_Gen_I18n_Sicc(oid_idioma_param IN INT, val_oid_param IN INT,
         attr_enti_param IN VARCHAR2) RETURN VARCHAR2
IS
salida VARCHAR2(4000);
BEGIN
       SELECT val_i18n
       INTO salida
       FROM gen_i18n_sicc
       WHERE
       idio_oid_idio = oid_idioma_param AND
       val_oid = val_oid_param AND
       attr_enti = attr_enti_param AND
       attr_num_atri = 1;
       RETURN salida;
END;


PROCEDURE Borrar_Bloqueo_CCC(esteTextoNoSeUsa in VARCHAR2, dummy OUT VARCHAR2)
IS
pragma AUTONOMOUS_TRANSACTION;
BEGIN
  DELETE FROM CCC_BLOQU;
  COMMIT;
END;

PROCEDURE Insertar_Bloqueo_CCC(codUsua IN VARCHAR2, descProc in VARCHAR2, dummy OUT VARCHAR2)
IS
pragma AUTONOMOUS_TRANSACTION;
BEGIN
INSERT INTO CCC_BLOQU( OID_BLOQ, COD_USUA,  FEC_BLOQ, VAL_DES_PROC) VALUES (1,codUsua, SYSTIMESTAMP, descProc);
COMMIT;
END;


END pq_apl_aux;
/

