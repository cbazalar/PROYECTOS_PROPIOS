CREATE OR REPLACE TRIGGER BAS_TRG_AI_AUDI_PROCE_USUA
  AFTER INSERT ON BAS_AUDI_PROCE_USUA_ACTUA
  FOR EACH ROW
DECLARE

BEGIN
      /* Insercion la tabla BAS_AUDI_PROCE_USUA_ACTUA */
      INSERT INTO BAS_AUDI_PROCE_USUA_HISTO
      (
     NUM_SEQU ,
     PAIS_COD_PAIS  ,
     COD_MENU       ,
     COD_ACCI       ,
     VAL_IP_MAQU    ,
     COD_USUA       ,
     COD_PERI       ,
     FEC_INIC_PROC  ,
     FEC_FINA_PROC  ,
     NUM_DURA_SEGU  ,
     IND_ESTA_PROC
     )
      VALUES
      (
     :NEW.NUM_SEQU,
     :NEW.PAIS_COD_PAIS,
     :NEW.COD_MENU,
     :NEW.COD_ACCI,
     :NEW.VAL_IP_MAQU,
     :NEW.COD_USUA,
     :NEW.COD_PERI,
     :NEW.FEC_INIC_PROC,
     :NEW.FEC_FINA_PROC,
     :NEW.NUM_DURA_SEGU,
     :NEW.IND_ESTA_PROC
    );

END BAS_TRG_AI_AUDI_PROCE_USUA;
/
