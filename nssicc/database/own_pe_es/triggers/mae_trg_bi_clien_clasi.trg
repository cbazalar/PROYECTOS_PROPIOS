CREATE OR REPLACE TRIGGER MAE_TRG_BI_CLIEN_CLASI
  BEFORE INSERT ON MAE_CLIEN_CLASI
  FOR EACH ROW
BEGIN
      /* actualizacion de fecha clasifiación */
      IF :new.fec_clas IS NULL THEN
        :new.fec_clas := to_date(to_char(SYSDATE,'dd/mm/yyyy'),'dd/mm/yyyy');
      END IF;
END MAE_TRG_BI_CLIEN_CLASI;
/