CREATE OR REPLACE FUNCTION GEN_FN_DEVUE_FLAG_LIDER (psOidPais IN NUMBER,
                        psOidMarca IN NUMBER,
                        psOidCanal IN NUMBER,
                        psOidZona  IN NUMBER,
                        psOidSeccion  IN NUMBER,
                        psOidPeriodo IN NUMBER) RETURN VARCHAR2
IS
lnOidPeriodo NUMBER;
lcZona VARCHAR2(4);
lcSecc VARCHAR2(1);
lcCodigoPeriodo VARCHAR2(6);
lcCodigoPais VARCHAR2(10);
lnIndProgLide NUMBER;
lnSumaActiFina NUMBER;
lnSumaActiFina1 NUMBER;
lnSumaActiFina2 NUMBER;
lnSumaActiFina3 NUMBER;
lnPromedio NUMBER:=0;
lnTotal NUMBER;
lnContador NUMBER;
vnCantidadPedidos NUMBER:=0;
vnPorcentajeActividadMeta NUMBER:=0;
lsCodigoPeriodo SEG_PERIO_CORPO.COD_PERI%TYPE;
pnOidUltimoPeriodo2 NUMBER;
pnOidUltimoPeriodo3 NUMBER;
lnMinimoActivaFinaZona    LID_PARAM.MIN_ACTI_FINA_ZONA%type;
lnMinimoActivaFinaSeccion LID_PARAM.MIN_ACTI_FINA_SECC%type;
vnEncontroReg NATURAL;
lsCodRegi zon_regio.cod_regi%TYPE;
lsCodZona zon_zona.cod_zona%TYPE;
lsCodSecc zon_secci.cod_secc%TYPE; 
BEGIN

    lcCodigoPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO( psOidPeriodo );
    
    SELECT cod_pais
      INTO lcCodigoPais
      FROM seg_pais
     WHERE oid_pais = psOidPais;
    
    SELECT ind_prog_lide
      INTO lnIndProgLide
      FROM bas_pais
     WHERE cod_pais = lcCodigoPais;
     
    SELECT count(1)
    INTO vnEncontroReg
    FROM ZON_SECCI_NAPT;
    
    IF ( lnIndProgLide = 0 OR lnIndProgLide IS NULL) AND vnEncontroReg = 0 THEN
       RETURN NULL;
    END IF;

    IF  vnEncontroReg > 0 THEN
        SELECT COD_ZONA into LcZOna
        FROM ZON_ZONA
        WHERE OID_ZONA = psOidZona;
        SELECT COD_SECC into lcSecc
        FROM ZON_SECCI
        WHERE OID_SECC = psOidSeccion;
        SELECT count(1) into vnEncontroReg
        FROM  ZON_SECCI_NAPT
        WHERE COD_ZONA = lcZona 
        AND   COD_SECC = lcSecc;
        IF vnEncontroReg = 0 THEN
           RETURN '1';
        ELSE
           RETURN '0';
        END IF;
     END IF;

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
    

    IF lnIndProgLide = 1 THEN
       SELECT MIN_ACTI_FINA_ZONA , MIN_ACTI_FINA_SECC INTO
              lnMinimoActivaFinaZona,lnMinimoActivaFinaSeccion
         FROM LID_PARAM;
        
        IF(lnMinimoActivaFinaZona <> 0 OR lnMinimoActivaFinaSeccion <> 0)  THEN

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
    ELSE
        IF lnIndProgLide = 2 THEN
          ----   SELECT min_acti_fina_zona, min_acti_fina_secc
          ---   INTO lnMinimoActivaFinaZona, lnMinimoActivaFinaSeccion
          ---   FROM let_param_concu_lider
          ---  WHERE lcCodigoPeriodo >= cam_inic
          ---    AND lcCodigoPeriodo <= cam_fina
          ---     AND est_regi = 1   
          SELECT min_acti_fina_zona, lr.can_pedi, POR_ACTI_META
          INTO   lnMinimoActivaFinaZona, vnCantidadPedidos, vnPorcentajeActividadMeta
          FROM  let_param_concu_lider lp, let_param_rango_premi lr
          WHERE lcCodigoPeriodo >= lp.cam_inic
          AND   lcCodigoPeriodo <= lp.cam_fina
          AND   lp.est_regi = 1
          AND   lp.cod_conc = lr.conc_cod_conc
          AND   lr.rang_num_rang = 1;
          
          lnMinimoActivaFinaSeccion := FLOOR(vnCantidadPedidos / (vnPorcentajeActividadMeta / 100));
           
           IF ( lnMinimoActivaFinaZona + lnMinimoActivaFinaSeccion = 0 )  THEN
              RETURN '1';
           ELSE
              -- OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ULTIMO PERIODO
              SELECT sum(fue.num_acti_fina)
                INTO lnSumaActiFina
                FROM int_fuent_ventas_real fue
               WHERE fue.zzon_oid_zona = psOidZona
                 AND fue.perd_oid_peri = lnOidPeriodo;

             --OBTENEMOS EL NUMERO DE ACTIVAS FINALES DEL ULTIMO PERIODO
             SELECT SUM(fue.num_acti_fina)
               INTO lnSumaActiFina1
               FROM int_fuent_ventas_real fue,
                    zon_terri_admin adm
              WHERE fue.zzon_oid_zona = psOidZona
                AND fue.terr_oid_terr = adm.terr_oid_terr
                AND adm.zscc_oid_secc = psOidSeccion
                AND fue.perd_oid_peri = lnOidPeriodo
                AND (lnOidPeriodo >= adm.PERD_OID_PERI_INIC or adm.PERD_OID_PERI_INIC is null)
                AND (lnOidPeriodo <= adm.PERD_OID_PERI_FINA or adm.PERD_OID_PERI_FINA is null);

             IF(lnSumaActiFina >= lnMinimoActivaFinaZona
              AND lnSumaActiFina1 >= lnMinimoActivaFinaSeccion) THEN
                RETURN '1';
             ELSE
                RETURN '0';
             END IF;
           END IF;
        ELSE
          IF lnIndProgLide = 3 THEN
             BEGIN
                SELECT NUM_MINI_ACTI_SECC
                INTO lnMinimoActivaFinaSeccion
                FROM LET_CORPO_PARAM_PROGR
                WHERE     IND_ACTI = '1'
                AND lcCodigoPeriodo >= CAM_INIC
                AND (lcCodigoPeriodo <= CAM_FIN OR CAM_FIN IS NULL);
              EXCEPTION
              WHEN NO_DATA_FOUND
              THEN
                 lnMinimoActivaFinaSeccion := 0;
              END;
              SELECT SUM(fue.num_acti_fina)
               INTO lnSumaActiFina1
               FROM int_fuent_ventas_real fue,
                    zon_terri_admin adm
               WHERE fue.zzon_oid_zona = psOidZona
                AND fue.terr_oid_terr = adm.terr_oid_terr
                AND adm.zscc_oid_secc = psOidSeccion
                AND fue.perd_oid_peri = lnOidPeriodo
                AND (lnOidPeriodo >= adm.PERD_OID_PERI_INIC or adm.PERD_OID_PERI_INIC is null)
                AND (lnOidPeriodo <= adm.PERD_OID_PERI_FINA or adm.PERD_OID_PERI_FINA is null);
               IF lnSumaActiFina1 >= lnMinimoActivaFinaSeccion THEN
                  RETURN '1';
               ELSE
                  RETURN '0';
               END IF;              
           ELSE 
             IF lnIndProgLide = 4 THEN
               BEGIN
                BEGIN
                  SELECT P.NUM_ACTI_SECC_APTA
                    INTO lnMinimoActivaFinaSeccion
                    FROM (SELECT * FROM LEC_PROGR ORDER BY CAM_FIN DESC) P
                   WHERE P.IND_ACTI = '1'
                     AND lcCodigoPeriodo >= P.CAM_INIC
                     AND (lcCodigoPeriodo <= P.CAM_FIN OR P.CAM_FIN IS NULL)
                     AND ROWNUM<=1;  -- Ajuste para evitar que devuelva más de 1 registro
                 
                 EXCEPTION
                   WHEN NO_DATA_FOUND THEN
                     lnMinimoActivaFinaSeccion := 0;
               END;
               
                  SELECT r.cod_regi,
                         z.cod_zona,
                         s.cod_secc      
                    INTO lsCodRegi,
                         lsCodZona,
                         lsCodSecc
                    FROM zon_regio r,
                         zon_zona z,
                         zon_secci s
                   WHERE z.zorg_oid_regi = r.oid_regi
                     AND z.oid_zona      = s.zzon_oid_zona
                     AND z.oid_zona      = psOidZona
                     AND s.oid_secc      = psOidSeccion;         
                   
                         
                  lnSumaActiFina1 := gen_pkg_gener.gen_fn_devue_activas_seccion(lcCodigoPeriodo,
                                                                                lsCodRegi,
                                                                                lsCodZona,
                                                                                lsCodSecc
                                                                                );
               
               IF lnSumaActiFina1 >= lnMinimoActivaFinaSeccion THEN
                  RETURN '1';
               ELSE
                  RETURN '0';
               END IF;
              END;
             END IF;
          END IF;
        END IF;
    END IF;
 
 RETURN '';
END GEN_FN_DEVUE_FLAG_LIDER;
/