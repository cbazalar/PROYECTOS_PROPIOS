CREATE OR REPLACE TRIGGER "PER_TRG_BI_PERCE_CONSO" 
  BEFORE INSERT ON PER_PERCE_CONSO   FOR EACH ROW
DECLARE
  ln_Secuencia NUMBER;
BEGIN

  /* Generacion del Correlativo Percepcion Consolidado */
  SELECT PER_SEQ_PERCE_CONSO.NEXTVAL
  INTO ln_Secuencia FROM dual;

  /* Grabando valor del correlativo en el campo COR_PECO */
  :NEW.COR_PECO := ln_Secuencia;

END PER_TRG_BI_PERCE_CONSO;
/

