CREATE OR REPLACE TRIGGER sto_trg_biu_pasv
-- *****************************************************************************
-- Descripcion  : Trigger de Auditoria a la Tabla STO_PARAM_SECUE_VALID
-- Especificado : Jorge Velasquez
-- Fecha        : 17/07/2012
-- *****************************************************************************
  BEFORE INSERT OR UPDATE ON sto_param_secue_valid
  FOR EACH ROW
DECLARE
  lstipo VARCHAR2(1) := 'N';
BEGIN

  IF updating THEN
    lstipo := 'M';
  END IF;

  /* Grabando en Campos de Auditoria */
  INSERT INTO sto_audit_param
    (usu_modi,
     fec_modi,
     val_ip,
     nom_tabl,
     tip_regi)
  VALUES
    (:new.usu_digi,
     SYSDATE,
     sys_context('USERENV',
                 'IP_ADDRESS'),
     'STO_PARAM_SECUE_VALID',
     lstipo);

END sto_trg_biu_pasv;
/
