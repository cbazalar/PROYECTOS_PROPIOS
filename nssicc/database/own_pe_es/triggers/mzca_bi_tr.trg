CREATE OR REPLACE TRIGGER "MZCA_BI_TR" 
 BEFORE INSERT
 ON APE_MAPA_ZONA_CABEC
 FOR EACH ROW
DECLARE
  n_cod_mapa_zona  NUMBER(2);
BEGIN
  SELECT NVL(MAX(COD_MAPA_ZONA),0)
  INTO n_cod_mapa_zona
  FROM APE_MAPA_ZONA_CABEC;

  :NEW.COD_MAPA_ZONA := n_cod_mapa_zona + 1;
END;
/

