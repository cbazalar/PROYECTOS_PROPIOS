CREATE OR REPLACE FUNCTION MAV_FN_OBTEN_NUMER_UNIDA (
   pnOidDetalleMAV      IN   NUMBER,
   pnOidRegion          IN   NUMBER,
   pnOidZona            IN   NUMBER,
   pnUnidades           IN   NUMBER
)
   RETURN NUMBER
IS
   lnUnidades     NUMBER;
BEGIN

  --Se recupera el numero de Unidades sea si esta configurado x Zona o x Region
  BEGIN
    SELECT NVL(NUM_UNID,0)
      INTO lnUnidades
      FROM (
            SELECT NUM_UNID
              FROM MAV_DETAL_MAV_UNIDA_ADMIN
             WHERE denv_oid_deta_mav = pnOidDetalleMAV
               AND pnOidZona = zzon_oid_zona
             UNION
            SELECT NUM_UNID
              FROM MAV_DETAL_MAV_UNIDA_ADMIN
             WHERE denv_oid_deta_mav = pnOidDetalleMAV
               AND pnOidRegion = zorg_oid_regi
               AND zzon_oid_zona IS NULL);

    --Si el numero de Unidades es 0, se recupera el valor de UnidadesxCliente
    IF(lnUnidades = 0) THEN
      lnUnidades := pnUnidades;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      lnUnidades := pnUnidades;
  END;

  RETURN lnUnidades;

END MAV_FN_OBTEN_NUMER_UNIDA;
/

