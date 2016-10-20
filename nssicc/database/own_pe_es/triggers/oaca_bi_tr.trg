CREATE OR REPLACE TRIGGER "OACA_BI_TR" 
   BEFORE INSERT
   ON APE_ORDEN_ANAQU_CABEC    REFERENCING NEW AS NEW OLD AS OLD
   FOR EACH ROW
DECLARE
   ultimocodigo   NUMBER (2);
   primerodisponible NUMBER(2);
BEGIN
   SELECT NVL (MAX (cod_mapa_orde), 0)
     INTO ultimocodigo
     FROM ape_orden_anaqu_cabec
    WHERE mzca_oid_mapa_zona_cabe = :NEW.mzca_oid_mapa_zona_cabe;

   IF ((ultimocodigo + 1) <= 99)
   THEN
      :NEW.cod_mapa_orde := ultimocodigo + 1;
   ELSE
      SELECT NVL (MIN (cod_mapa_orde), 0) + 1
        INTO primerodisponible
        FROM ape_orden_anaqu_cabec
       WHERE mzca_oid_mapa_zona_cabe = :NEW.mzca_oid_mapa_zona_cabe
         AND (cod_mapa_orde + 1) NOT IN (
                   SELECT cod_mapa_orde
                     FROM ape_orden_anaqu_cabec
                    WHERE mzca_oid_mapa_zona_cabe = :NEW.mzca_oid_mapa_zona_cabe);

      :NEW.cod_mapa_orde := primerodisponible;
   END IF;
END;
/

