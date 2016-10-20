CREATE OR REPLACE FUNCTION "FN_OBTENER_TIPO_CAMBIO" (
    idPais in NUMBER,
    codMonedaAlt in VARCHAR2,
    idPeriodo in NUMBER,
    año in NUMBER,
    fecha in VARCHAR2
)
RETURN NUMBER
IS
  var_aux_num number;
BEGIN
IF (idPeriodo IS NOT NULL) THEN
    SELECT AVG(STC.VAL_TIPO_CAMB) INTO var_aux_num
    FROM SEG_TIPO_CAMBI STC,
        (
         SELECT FEC_INIC, FEC_FINA
         FROM CRA_PERIO PER
         WHERE PER.OID_PERI = idPeriodo
        ) PER
    WHERE STC.MONE_OID_MON1 = (
                   SELECT PAI.MONE_OID_MONE
                   FROM SEG_PAIS PAI
                 WHERE PAI.OID_PAIS = idPais
               )
      AND STC.MONE_OID_MON2 = (
                 SELECT OID_MONE
                 FROM SEG_MONED
                 WHERE COD_MONE = ''||codMonedaAlt||''
               )
      AND STC.FEC_DESD >= PER.FEC_INIC
      AND STC.FEC_DESD <= PER.FEC_FINA;
   IF (var_aux_num IS NULL) THEN
        SELECT VAL_TIPO_CAMB INTO var_aux_num
      FROM
        (
         SELECT STC.VAL_TIPO_CAMB VAL_TIPO_CAMB
         FROM SEG_TIPO_CAMBI STC,
            (
             SELECT FEC_INIC, FEC_FINA
        FROM CRA_PERIO PER
        WHERE PER.OID_PERI = idPeriodo
          ) PER
          WHERE STC.MONE_OID_MON1 = (
                         SELECT PAI.MONE_OID_MONE
                      FROM SEG_PAIS PAI
                    WHERE PAI.OID_PAIS = idPais
                  )
          AND STC.MONE_OID_MON2 = (
                          SELECT OID_MONE
                    FROM SEG_MONED
                    WHERE COD_MONE = ''||codMonedaAlt||''
                  )
         AND STC.FEC_DESD <= PER.FEC_INIC
           ORDER BY FEC_DESD DESC
      )
      WHERE ROWNUM = 1;
     END IF;
ELSE
    IF (año IS NOT NULL) THEN
      SELECT AVG(STC.VAL_TIPO_CAMB) INTO var_aux_num
    FROM SEG_TIPO_CAMBI STC,
       (
          SELECT MIN(FEC_INIC) FEC_INIC, MAX(FEC_FINA) FEC_FINA
        FROM CRA_PERIO PER,
               SEG_PERIO_CORPO PC
           WHERE PER.PERI_OID_PERI = PC.OID_PERI
             AND PC.VAL_ANIO = año
          ) PER
        WHERE STC.MONE_OID_MON1 = (
                       SELECT PAI.MONE_OID_MONE
                 FROM SEG_PAIS PAI
                 WHERE PAI.OID_PAIS = idPais
                )
       AND STC.MONE_OID_MON2 = (
                       SELECT OID_MONE
                    FROM SEG_MONED
                    WHERE COD_MONE = ''||codMonedaAlt||''
                  )
       AND STC.FEC_DESD >= PER.FEC_INIC
       AND STC.FEC_DESD <= PER.FEC_FINA;

    IF (var_aux_num IS NULL) THEN
       SELECT VAL_TIPO_CAMB INTO var_aux_num
       FROM
         (
          SELECT STC.VAL_TIPO_CAMB VAL_TIPO_CAMB
          FROM SEG_TIPO_CAMBI STC,
           (
            SELECT MIN(FEC_INIC) FEC_INIC, MAX(FEC_FINA) FEC_FINA
            FROM CRA_PERIO PER,
                SEG_PERIO_CORPO PC
             WHERE PER.PERI_OID_PERI = PC.OID_PERI
              AND PC.VAL_ANIO = año
          ) PER
          WHERE STC.MONE_OID_MON1 = (
                      SELECT PAI.MONE_OID_MONE
                      FROM SEG_PAIS PAI
                               WHERE PAI.OID_PAIS = idPais
                    )
           AND STC.MONE_OID_MON2 = (
                             SELECT OID_MONE
                     FROM SEG_MONED
                     WHERE COD_MONE = ''||codMonedaAlt||''
                    )
           AND STC.FEC_DESD <= PER.FEC_INIC
           ORDER BY FEC_DESD DESC
      )
      WHERE ROWNUM = 1;
      END IF;
  ELSE
    IF (fecha IS NOT NULL) THEN
      SELECT AVG(STC.VAL_TIPO_CAMB) INTO var_aux_num
      FROM SEG_TIPO_CAMBI STC
       WHERE STC.MONE_OID_MON1 = (
                  SELECT PAI.MONE_OID_MONE
                  FROM SEG_PAIS PAI
                  WHERE PAI.OID_PAIS = idPais
                 )
      AND STC.MONE_OID_MON2 = (
                    SELECT OID_MONE
                    FROM SEG_MONED
                    WHERE COD_MONE = ''||codMonedaAlt||''
                  )
      AND STC.FEC_DESD = TRUNC(TO_DATE(''||fecha||'' ,'DD/MM/YYYY'));

      IF (var_aux_num IS NULL) THEN
       SELECT VAL_TIPO_CAMB INTO var_aux_num
       FROM
        (
         SELECT STC.VAL_TIPO_CAMB VAL_TIPO_CAMB
         FROM SEG_TIPO_CAMBI STC
         WHERE STC.MONE_OID_MON1 = (
                 SELECT PAI.MONE_OID_MONE
                 FROM SEG_PAIS PAI
               WHERE PAI.OID_PAIS = idPais
              )
           AND STC.MONE_OID_MON2 = (
                   SELECT OID_MONE
               FROM SEG_MONED
               WHERE COD_MONE = ''||codMonedaAlt||''
              )
          AND STC.FEC_DESD <= TRUNC(TO_DATE(''||fecha||'' ,'DD/MM/YYYY'))
          ORDER BY FEC_DESD DESC
      )
      WHERE ROWNUM = 1;
      END IF;
     ELSE
       RETURN 1;
     END IF;
END IF;
END IF;
IF (var_aux_num IS NULL) THEN
   RETURN 1;
ELSE
   RETURN var_aux_num;
END IF;
END;
/

