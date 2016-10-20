CREATE OR REPLACE FUNCTION "MAV_FN_VALID_ESTAT_CLIEN" (
   pnOidCliente         IN   NUMBER,
   pnOidPeriodo         IN   NUMBER,
   pnOidRegion          IN   NUMBER,
   pnOidDetalleMAV      IN   NUMBER,
   pnOidEstadoMAV       IN   NUMBER
)
   RETURN NUMBER
IS
   lnTotalOcurrencias     NUMBER;
   lnOidEstatus           MAE_ESTAT_CLIEN.OID_ESTA_CLIE%TYPE;
   lnOidPeriodo           CRA_PERIO.OID_PERI%TYPE;
BEGIN

  --Si no tiene definido la configuracion de estatus para el detalle MAV se sale rapido
  IF(pnOidEstadoMAV IS NULL) THEN
    RETURN 1;
  END IF;

  --Se verifica si existe restriccion por tipificacion de Clientes
  SELECT COUNT(1)
    INTO lnTotalOcurrencias
    FROM FAC_CONTR_CIERR fcc,
	       FAC_TIPOS_CIERR ftc
   WHERE fcc.PERD_OID_PERI = pnOidPeriodo ---periodo
     AND fcc.TCIE_OID_TIPO_CIER = ftc.oid_tipo_cier
     AND fcc.VAL_RESU_PROC = 'OK'
     AND ftc.COD_TIPO_CIER = 'R'
     AND fcc.ZORG_OID_REGI = pnOidRegion;


  IF(lnTotalOcurrencias = 0) THEN
    --No ha cerrado la Region, se consulta con su estatus de DATOS_ADICIONALES
    SELECT COUNT (1)
      INTO lnTotalOcurrencias
      FROM mae_clien_datos_adici,
           mae_estat_clien,
           mav_estad_mav_mae,
           mav_estad_mav,
           mav_activ_estad,
           mav_detal_mav
     WHERE mae_clien_datos_adici.clie_oid_clie = pnOidCliente
       AND mav_detal_mav.oid_deta_mav = pnOidDetalleMAV
       AND mae_clien_datos_adici.esta_oid_esta_clie = mae_estat_clien.oid_esta_clie
       AND mae_estat_clien.oid_esta_clie = mav_estad_mav_mae.esta_oid_esta_clie
       AND mav_estad_mav_mae.emav_oid_esta_mav = mav_estad_mav.oid_esta_mav
       AND mav_estad_mav.oid_esta_mav = mav_activ_estad.emav_oid_esta_mav
       AND mav_activ_estad.oid_esta_acti = mav_detal_mav.aest_oid_esta_acti;

  ELSE
    --Verificamos si ha habido cambio de estado respecto al periodo anterior
    SELECT COUNT(1)
      INTO lnTotalOcurrencias
      FROM MAE_CLIEN_HISTO_ESTAT
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND PERD_OID_PERI = pnOidPeriodo;

    IF(lnTotalOcurrencias = 0)  THEN
      --No ha habido cambio de estado, se consulta con su estatus de DATOS_ADICIONALES
      SELECT COUNT (1)
        INTO lnTotalOcurrencias
        FROM mae_clien_datos_adici,
             mae_estat_clien,
             mav_estad_mav_mae,
             mav_estad_mav,
             mav_activ_estad,
             mav_detal_mav
       WHERE mae_clien_datos_adici.clie_oid_clie = pnOidCliente
         AND mav_detal_mav.oid_deta_mav = pnOidDetalleMAV
         AND mae_clien_datos_adici.esta_oid_esta_clie = mae_estat_clien.oid_esta_clie
         AND mae_estat_clien.oid_esta_clie = mav_estad_mav_mae.esta_oid_esta_clie
         AND mav_estad_mav_mae.emav_oid_esta_mav = mav_estad_mav.oid_esta_mav
         AND mav_estad_mav.oid_esta_mav = mav_activ_estad.emav_oid_esta_mav
         AND mav_activ_estad.oid_esta_acti = mav_detal_mav.aest_oid_esta_acti;

    ELSE
      --Se ubica el estatus del periodo anterior del Periodo de Proceso
      BEGIN
        --Obtenemos el Periodo Anterior
        SELECT OID_PERI
         INTO  lnOidPeriodo
         FROM (
                SELECT B.oid_peri
                 FROM cra_perio A, SEG_PERIO_CORPO C, cra_perio B, SEG_PERIO_CORPO D
                WHERE A.oid_peri = pnOidPeriodo
                  AND B.pais_oid_pais = A.pais_oid_pais
                  AND B.marc_oid_marc = A.marc_oid_marc
                  AND B.cana_oid_cana = A.cana_oid_cana
                  AND A.Peri_Oid_Peri = C.Oid_Peri
                  AND B.Peri_Oid_Peri = D.Oid_Peri
                  AND D.cod_peri < C.cod_peri
                ORDER BY D.COD_PERI DESC)
         WHERE ROWNUM = 1;

        SELECT esta_oid_esta_clie
          INTO lnOidEstatus
          FROM MAE_CLIEN_HISTO_ESTAT his
         WHERE his.clie_oid_clie = pnOidCliente
           AND his.PERD_OID_PERI_PERI_FIN = lnOidPeriodo;

      EXCEPTION
        WHEN OTHERS THEN
          lnOidEstatus := 1;
      END;

      SELECT COUNT (1)
        INTO lnTotalOcurrencias
        FROM mae_estat_clien,
             mav_estad_mav_mae,
             mav_estad_mav,
             mav_activ_estad,
             mav_detal_mav
       WHERE mae_estat_clien.oid_esta_clie = lnOidEstatus
         AND mav_detal_mav.oid_deta_mav = pnOidDetalleMAV
         AND mae_estat_clien.oid_esta_clie = mav_estad_mav_mae.esta_oid_esta_clie
         AND mav_estad_mav_mae.emav_oid_esta_mav = mav_estad_mav.oid_esta_mav
         AND mav_estad_mav.oid_esta_mav = mav_activ_estad.emav_oid_esta_mav
         AND mav_activ_estad.oid_esta_acti = mav_detal_mav.aest_oid_esta_acti;

    END IF;

  END IF;

  IF(lnTotalOcurrencias > 0) THEN
    lnTotalOcurrencias := 1;
  ELSE
    lnTotalOcurrencias := 0;
  END IF;

  RETURN lnTotalOcurrencias;

END MAV_FN_VALID_ESTAT_CLIEN;
/

