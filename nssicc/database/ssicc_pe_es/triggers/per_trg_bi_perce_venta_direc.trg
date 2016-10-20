CREATE OR REPLACE TRIGGER "PER_TRG_BI_PERCE_VENTA_DIREC" 
  BEFORE INSERT ON PER_PERCE_VENTA_DIREC   FOR EACH ROW
DECLARE
  ln_Secuencia NUMBER;
BEGIN

  /* Generacion del Correlativo Percepcion Consolidado */
  SELECT PER_SEQ_VENTA_DIREC.NEXTVAL
  INTO ln_Secuencia FROM dual;

  /* Grabando valor del correlativo en el campo COR_PECO */
  :NEW.COR_PEVD := ln_Secuencia;

END PER_TRG_BI_PERCE_VENTA_DIREC;
/

