CREATE OR REPLACE TRIGGER "LPDE_BI_TR" 
BEFORE INSERT
ON APE_LISTA_PICAD_DETAL
FOR EACH ROW
DECLARE
 ultimo_codigo NUMBER(12);
BEGIN
SELECT NVL(MAX(num_codi_posi),0) INTO ultimo_codigo
FROM ape_lista_picad_detal;
:NEW.num_codi_posi := ultimo_codigo + 1;
END;
/

