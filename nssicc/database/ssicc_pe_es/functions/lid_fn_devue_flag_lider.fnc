CREATE OR REPLACE FUNCTION LID_FN_DEVUE_FLAG_LIDER (psOidPais IN NUMBER,
                        psOidMarca IN NUMBER,
                        psOidCanal IN NUMBER,
                        psOidZona  IN NUMBER,
                        psOidSeccion  IN NUMBER) RETURN VARCHAR2
IS
NUME NUMBER:=0;
lnOidPeriodo NUMBER;
lnSumaActiFina NUMBER;
lnSumaActiFina1 NUMBER;
lnSumaActiFina2 NUMBER;
lnSumaActiFina3 NUMBER;
lnPromedio NUMBER:=0;
lnTotal NUMBER;
lnContador NUMBER;
lsCodigoPeriodo SEG_PERIO_CORPO.COD_PERI%TYPE;
pnOidUltimoPeriodo2 NUMBER;
pnOidUltimoPeriodo3 NUMBER;
lnMinimoActivaFinaZona    LID_PARAM.MIN_ACTI_FINA_ZONA%type;
lnMinimoActivaFinaSeccion LID_PARAM.MIN_ACTI_FINA_SECC%type;
BEGIN

    SELECT MIN_ACTI_FINA_ZONA , MIN_ACTI_FINA_SECC INTO
         lnMinimoActivaFinaZona,lnMinimoActivaFinaSeccion
    FROM LID_PARAM;


   IF(lnMinimoActivaFinaZona <> 0 OR lnMinimoActivaFinaSeccion <> 0)  THEN
     --OBTIENE LA ULTIMA CAMPAÑA DE CIERRE DE REGION DE LA ZONA
        SELECT OID_PERI , COD_PERI INTO lnOidPeriodo,lsCodigoPeriodo
              FROM (
                    SELECT DISTINCT cra.oid_peri, cor.cod_peri, cra.fec_inic
                      FROM FAC_CONTR_CIERR con,
                           FAC_TIPOS_CIERR tip,
                           CRA_PERIO cra,
                           SEG_PERIO_CORPO cor,
                           SEG_PAIS pai,
                           ZON_REGIO reg,
                           ZON_ZONA zon,
                           SEG_MARCA mar,
                           SEG_CANAL can
                     WHERE con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER
                       AND cra.OID_PERI = con.PERD_OID_PERI
                       AND cor.OID_PERI = cra.PERI_OID_PERI
                       AND pai.OID_PAIS = cra.PAIS_OID_PAIS
                       AND reg.OID_REGI = con.ZORG_OID_REGI
                       AND zon.ZORG_OID_REGI = reg.OID_REGI
                       AND zon.OID_ZONA = psOidZona
                       AND tip.COD_TIPO_CIER = 'R'
                       AND pai.OID_PAIS = psOidPais
                       AND mar.OID_MARC = psOidMarca
                       AND can.OID_CANA = psOidCanal
                       AND con.VAL_RESU_PROC = 'OK'
                       AND reg.MARC_OID_MARC = mar.OID_MARC
                       AND reg.CANA_OID_CANA = can.OID_CANA
                       AND reg.PAIS_OID_PAIS = pai.OID_PAIS
                 ORDER BY cra.FEC_INIC DESC
              ) WHERE ROWNUM = 1;


           --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ULTIMO PERIODO
             SELECT sum(fue.num_acti_fina)
             INTO lnSumaActiFina
             FROM int_fuent_ventas_real fue
             WHERE fue.zzon_oid_zona = psOidZona
             AND fue.perd_oid_peri = lnOidPeriodo;


        --OBTENEMOS PERIODOD ANTERIOR    Y  ANTES DEL ANTERIOR
            SELECT OID_PERI  INTO pnOidUltimoPeriodo2
            FROM (
              SELECT B.OID_PERI, C.COD_PERI
                FROM CRA_PERIO A, cra_perio B, SEG_PERIO_CORPO C, SEG_MARCA M, SEG_CANAL S, SEG_PAIS P, SEG_PERIO_CORPO D
               WHERE B.PAIS_OID_PAIS = A.PAIS_OID_PAIS
                 AND B.MARC_OID_MARC = A.MARC_OID_MARC
                 AND B.CANA_OID_CANA = A.CANA_OID_CANA
                 AND B.FEC_INIC < A.FEC_INIC
                 AND C.OID_PERI = B.PERI_OID_PERI
                 AND D.OID_PERI = A.PERI_OID_PERI
                 AND A.MARC_OID_MARC = M.OID_MARC
                 AND A.CANA_OID_CANA = S.OID_CANA
                 AND A.PAIS_OID_PAIS = p.OID_PAIS
                 AND P.OID_PAIS = psOidPais
                 AND M.OID_MARC =psOidMarca
                 AND S.OID_CANA = psOidCanal
                 AND A.OID_PERI = lnOidPeriodo
               ORDER BY B.FEC_INIC DESC
            )
            WHERE ROWNUM = 1;

        --OBTENEMOS EL ANTEPENULTIMO PERIODO
         SELECT OID_PERI  INTO pnOidUltimoPeriodo3
            FROM (
              SELECT B.OID_PERI, C.COD_PERI
                FROM CRA_PERIO A, cra_perio B, SEG_PERIO_CORPO C, SEG_MARCA M, SEG_CANAL S, SEG_PAIS P, SEG_PERIO_CORPO D
               WHERE B.PAIS_OID_PAIS = A.PAIS_OID_PAIS
                 AND B.MARC_OID_MARC = A.MARC_OID_MARC
                 AND B.CANA_OID_CANA = A.CANA_OID_CANA
                 AND B.FEC_INIC < A.FEC_INIC
                 AND C.OID_PERI = B.PERI_OID_PERI
                 AND D.OID_PERI = A.PERI_OID_PERI
                 AND A.MARC_OID_MARC = M.OID_MARC
                 AND A.CANA_OID_CANA = S.OID_CANA
                 AND A.PAIS_OID_PAIS = p.OID_PAIS
                 AND P.OID_PAIS = psOidPais
                 AND M.OID_MARC =psOidMarca
                 AND S.OID_CANA = psOidCanal
                 AND A.OID_PERI = pnOidUltimoPeriodo2
               ORDER BY B.FEC_INIC DESC
            )
            WHERE ROWNUM = 1 ;


              --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ULTIMO PERIODO
         SELECT sum(fue.num_acti_fina)
         INTO lnSumaActiFina1
         FROM int_fuent_ventas_real fue, zon_terri_admin adm
         WHERE fue.zzon_oid_zona = psOidZona
         AND fue.terr_oid_terr = adm.terr_oid_terr
         AND adm.zscc_oid_secc = psOidSeccion
         AND fue.perd_oid_peri = lnOidPeriodo
         AND (lnOidPeriodo >= adm.PERD_OID_PERI_INIC or adm.PERD_OID_PERI_INIC is null)
         AND (lnOidPeriodo <= adm.PERD_OID_PERI_FINA or adm.PERD_OID_PERI_FINA is null);

         --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL PENULTIMO PERIODO
         SELECT sum(fue.num_acti_fina)
         INTO lnSumaActiFina2
         FROM int_fuent_ventas_real fue, zon_terri_admin adm
         WHERE fue.zzon_oid_zona = psOidZona
         AND fue.terr_oid_terr = adm.terr_oid_terr
         AND adm.zscc_oid_secc = psOidSeccion
         AND fue.perd_oid_peri = pnOidUltimoPeriodo2
         AND (pnOidUltimoPeriodo2 >= adm.PERD_OID_PERI_INIC or adm.PERD_OID_PERI_INIC is null)
         AND (pnOidUltimoPeriodo2 <= adm.PERD_OID_PERI_FINA or adm.PERD_OID_PERI_FINA is null);

         --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ANTEPENULTIMO PERIODO
         SELECT sum(fue.num_acti_fina)
         INTO lnSumaActiFina3
         FROM int_fuent_ventas_real fue, zon_terri_admin adm
         WHERE fue.zzon_oid_zona = psOidZona
         AND fue.terr_oid_terr = adm.terr_oid_terr
         AND adm.zscc_oid_secc = psOidSeccion
         AND fue.perd_oid_peri = pnOidUltimoPeriodo3
         AND (pnOidUltimoPeriodo3 >= adm.PERD_OID_PERI_INIC or adm.PERD_OID_PERI_INIC is null)
         AND (pnOidUltimoPeriodo3 <= adm.PERD_OID_PERI_FINA or adm.PERD_OID_PERI_FINA is null);

         lnTotal := 0;
         lnContador := 0;
         IF(lnSumaActiFina1 IS NOT NULL) THEN
            lnContador := lnContador + 1;
            lnTotal := lnTotal + NVL(lnSumaActiFina1,0);
         END IF;
         IF(lnSumaActiFina2 IS NOT NULL) THEN
            lnContador := lnContador + 1;
            lnTotal := lnTotal + NVL(lnSumaActiFina2,0);
         END IF;
         IF(lnSumaActiFina3 IS NOT NULL) THEN
            lnContador := lnContador + 1;
            lnTotal := lnTotal + NVL(lnSumaActiFina3,0);
         END IF;

         IF(lnContador > 0) THEN
          lnPromedio := round(lnTotal / lnContador);
         END IF;
         /*

         "    Si Activas finales acumuladas por zona >= MIN_ACTI_FINA_ZONA y Activas finals por sección >= MIN_ACTI_FINA_SECC
         "     Mover 1 a Flag_Apta, de lo contrario mover 0.

         */
         IF(lnSumaActiFina >= lnMinimoActivaFinaZona
          AND lnPromedio >= lnMinimoActivaFinaSeccion) THEN
            RETURN '1';
         ELSE
            RETURN '0';
         END IF;


   END IF;


 RETURN '';
END LID_FN_DEVUE_FLAG_LIDER;
/

