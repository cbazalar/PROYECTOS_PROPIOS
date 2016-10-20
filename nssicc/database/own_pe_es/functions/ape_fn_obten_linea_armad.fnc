CREATE OR REPLACE FUNCTION APE_FN_OBTEN_LINEA_ARMAD
  (pnOidTipoSolicitud        NUMBER,
   pnOidConsolidado          NUMBER,
   pnOidCentroDistribucion   NUMBER,
   pnOidAgruOlas             NUMBER)
RETURN NUMBER
IS
  lnTotal        NUMBER;
  lnLineaArmado  APE_LINEA_ARMAD.OID_LINE_ARMA%TYPE;
BEGIN
  lnLineaArmado := NULL;

  --Se recupera las lineas de Armado para el Centro de Distribucion solicitado
  SELECT COUNT(1)
    INTO lnTotal
    FROM APE_TIPO_SOLIC_LINEA tsl, APE_LINEA_ARMAD lin
   WHERE tsl.TSPA_OID_TIPO_SOLI_PAIS = pnOidTipoSolicitud
     AND tsl.LIAR_OID_LINE_ARMA = lin.OID_LINE_ARMA
     AND lin.CCDI_OID_CONF_CENT_DIST = pnOidCentroDistribucion;

  IF(lnTotal = 1) THEN
    SELECT OID_LINE_ARMA
      INTO lnLineaArmado
      FROM APE_TIPO_SOLIC_LINEA tsl, APE_LINEA_ARMAD lin
     WHERE tsl.TSPA_OID_TIPO_SOLI_PAIS = pnOidTipoSolicitud
       AND tsl.LIAR_OID_LINE_ARMA = lin.OID_LINE_ARMA
       AND lin.CCDI_OID_CONF_CENT_DIST = pnOidCentroDistribucion;

  ELSIF(lnTotal > 1) THEN
    --Se busca en la tabla de unidades administrativas de la Linea de Armado, y
    --filtrando por Linea de Armado y Agrupacion de Olas
    BEGIN
      SELECT oid
        INTO lnLineaArmado
        FROM (
              SELECT grupo.liar_oid_line_arma AS oid,
                     grupo.zzon_oid_zona AS zona,
                     grupo.zscc_oid_secc AS secc

                FROM (SELECT det.LIAR_OID_LINE_ARMA,
                             det.OID_LINE_UADM,
                             det.ZORG_OID_REGI,
                             det.ZZON_OID_ZONA,
                             det.ZSCC_OID_SECC
                        FROM APE_LINEA_UADMI det
                       WHERE det.AAFP_OID_AAFP = pnOidAgruOlas ) grupo,
                     (SELECT zona.ZORG_OID_REGI,
                             cons.ZZON_OID_ZONA,
                             tad.ZSCC_OID_SECC
                        FROM PED_SOLIC_CABEC cons,
                             ZON_ZONA zona,
                             ZON_TERRI_ADMIN tad
                       WHERE cons.OID_SOLI_CABE = pnOidConsolidado
                         AND cons.ZTAD_OID_TERR_ADMI = tad.OID_TERR_ADMI
                         AND cons.ZZON_OID_ZONA = zona.OID_ZONA) datoscons,
                      (SELECT OID_LINE_ARMA
                         FROM APE_TIPO_SOLIC_LINEA tsl, APE_LINEA_ARMAD lin
                        WHERE tsl.TSPA_OID_TIPO_SOLI_PAIS = pnOidTipoSolicitud
                          AND tsl.LIAR_OID_LINE_ARMA = lin.OID_LINE_ARMA
                          AND lin.CCDI_OID_CONF_CENT_DIST = pnOidCentroDistribucion) linea

                       WHERE datoscons.ZORG_OID_REGI = grupo.ZORG_OID_REGI
                         AND ((datoscons.ZZON_OID_ZONA = grupo.ZZON_OID_ZONA) OR (grupo.ZZON_OID_ZONA IS NULL))
                         AND ((datoscons.ZSCC_OID_SECC = grupo.ZSCC_OID_SECC) OR (grupo.ZSCC_OID_SECC IS NULL))
                         AND grupo.LIAR_OID_LINE_ARMA = linea.OID_LINE_ARMA

                    ORDER BY zona NULLS LAST
                           , secc NULLS LAST)
        WHERE ROWNUM = 1;
    EXCEPTION
      WHEN OTHERS THEN
        SELECT OID_LINE_ARMA
          INTO lnLineaArmado
          FROM (
                SELECT lin.OID_LINE_ARMA
                  FROM APE_TIPO_SOLIC_LINEA tsl,
                       APE_LINEA_ARMAD lin
                 WHERE tsl.TSPA_OID_TIPO_SOLI_PAIS = pnOidTipoSolicitud
                   AND tsl.LIAR_OID_LINE_ARMA = lin.OID_LINE_ARMA
                   AND lin.CCDI_OID_CONF_CENT_DIST = pnOidCentroDistribucion
                   AND lin.NUM_VALO_DEFE = 1
                 ORDER BY lin.NUM_VALO_DEFE DESC)
         WHERE ROWNUM = 1;
    END;
  END IF;

  RETURN lnLineaArmado;

EXCEPTION
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123, 'ERROR APE_FN_OBTEN_LINEA_ARMAD: ' || substr(SQLERRM, 1, 250));

END APE_FN_OBTEN_LINEA_ARMAD;
/

