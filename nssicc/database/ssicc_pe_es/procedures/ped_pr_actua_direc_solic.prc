CREATE OR REPLACE PROCEDURE "PED_PR_ACTUA_DIREC_SOLIC"
AS

/*
-- v 1.0
CURSOR c_pedidos(p_oidPeriodo NUMBER, p_tipoDireccionEntrega VARCHAR2) IS
SELECT OID_SOLI_CABE,
       CLIE_OID_CLIE,
       OID_CLIE_DIRE,
       OID_TERR_ADMI,
       ZZON_OID_ZONA,
       TERR_OID_TERR,
       VEPO_OID_VALO_ESTR_GEOP
FROM (

SELECT PSC.CLIE_OID_CLIE,
       PSC.OID_SOLI_CABE,
       MCD.OID_CLIE_DIRE,
       PSC.CLDI_OID_CLIE_DIRE OID_CLIE_DIRE_SOLI,
       MCD.TERR_OID_TERR,
       PSC.TERR_OID_TERR TERR_OID_TERR_SOLI,
       ZTA.OID_TERR_ADMI,
       PSC.ZTAD_OID_TERR_ADMI OID_TERR_ADMI_SOLI,
       SEC.ZZON_OID_ZONA,
       PSC.ZZON_OID_ZONA ZZON_OID_ZONA_SOLI,
       (SELECT VEG.OID_VALO_ESTR_GEOP
          FROM ZON_VALOR_ESTRU_GEOPO VEG
         WHERE VEG.ORDE_1 = SUBSTR (MCD.COD_UNID_GEOG, 0, 6)
           AND VEG.ORDE_2 = SUBSTR (MCD.COD_UNID_GEOG, 7, 6)
           AND VEG.ORDE_3 = SUBSTR (MCD.COD_UNID_GEOG, 13, 6)
           AND NVL(VEG.ORDE_4, 'X') = NVL(SUBSTR (MCD.COD_UNID_GEOG, 19, 6), 'X')
           AND VEG.IND_ACTI = 1
           AND VEG.IND_BORR = 0) VEPO_OID_VALO_ESTR_GEOP,
       PSC.VEPO_OID_VALO_ESTR_GEOP VEPO_OID_VALO_ESTR_GEOP_SOLI
FROM MAE_CLIEN_DIREC MCD,
     MAE_TIPO_DIREC MTD,
     ZON_SECCI SEC,
     ZON_TERRI_ADMIN ZTA,
     PED_SOLIC_CABEC PSC,
     PED_TIPO_SOLIC_PAIS PTSP,
     PED_TIPO_SOLIC PTS
WHERE MCD.TIDC_OID_TIPO_DIRE = MTD.OID_TIPO_DIRE
AND MCD.IND_ELIM = 0
AND (
      (p_tipoDireccionEntrega IS NOT NULL
       AND MTD.COD_TIPO_DIRE = p_tipoDireccionEntrega
      )
      OR MCD.IND_DIRE_PPAL = 1
)
AND ZTA.TERR_OID_TERR = MCD.TERR_OID_TERR
AND ZTA.ZSCC_OID_SECC = SEC.OID_SECC
AND ZTA.IND_BORR = 0
AND ZTA.PERD_OID_PERI_FINA IS NULL
AND SEC.IND_BORR = 0
AND SEC.IND_ACTI = 1
AND PSC.CLIE_OID_CLIE = MCD.CLIE_OID_CLIE
AND PSC.TSPA_OID_TIPO_SOLI_PAIS = PTSP.OID_TIPO_SOLI_PAIS
AND PTSP.TSOL_OID_TIPO_SOLI = PTS.OID_TIPO_SOLI
AND PSC.PERD_OID_PERI = p_oidPeriodo
AND NVL(PTS.IND_SOLI_NEGA, 0) = 0 -- solicitudes positivas
AND PSC.GRPR_OID_GRUP_PROC = 4 -- solicitudes en GP4

)
WHERE OID_CLIE_DIRE != OID_CLIE_DIRE_SOLI
OR TERR_OID_TERR != TERR_OID_TERR_SOLI
OR OID_TERR_ADMI != OID_TERR_ADMI_SOLI
OR ZZON_OID_ZONA != ZZON_OID_ZONA_SOLI
OR VEPO_OID_VALO_ESTR_GEOP  != VEPO_OID_VALO_ESTR_GEOP_SOLI
ORDER BY CLIE_OID_CLIE;
*/

-- v 2.0
CURSOR c_pedidos(p_oidPeriodo NUMBER) IS
SELECT OID_SOLI_CABE,
       CLIE_OID_CLIE,
       OID_CLIE_DIRE,
       OID_TERR_ADMI,
       ZZON_OID_ZONA,
       TERR_OID_TERR,
       VEPO_OID_VALO_ESTR_GEOP
FROM (
SELECT SOL.CLIE_OID_CLIE,
       SOL.OID_SOLI_CABE,
       MCD.OID_CLIE_DIRE,
       SOL.CLDI_OID_CLIE_DIRE_SOLI,
       MCD.TERR_OID_TERR,
       SOL.TERR_OID_TERR_SOLI,
       ZTA.OID_TERR_ADMI,
       SOL.ZTAD_OID_TERR_ADMI_SOLI,
       SEC.ZZON_OID_ZONA,
       SOL.ZZON_OID_ZONA_SOLI,
       (SELECT VEG.OID_VALO_ESTR_GEOP
          FROM ZON_VALOR_ESTRU_GEOPO VEG
         WHERE VEG.ORDE_1 = SUBSTR (MCD.COD_UNID_GEOG, 0, 6)
           AND VEG.ORDE_2 = SUBSTR (MCD.COD_UNID_GEOG, 7, 6)
           AND VEG.ORDE_3 = SUBSTR (MCD.COD_UNID_GEOG, 13, 6)
           AND NVL(VEG.ORDE_4, 'X') = NVL(SUBSTR (MCD.COD_UNID_GEOG, 19, 6), 'X')
           AND VEG.IND_ACTI = 1
           AND VEG.IND_BORR = 0) VEPO_OID_VALO_ESTR_GEOP,
       SOL.VEPO_OID_VALO_ESTR_GEOP_SOLI
FROM MAE_CLIEN_DIREC MCD,
     ZON_SECCI SEC,
     ZON_TERRI_ADMIN ZTA,
    (
    SELECT PSC.CLIE_OID_CLIE,
           PSC.OID_SOLI_CABE,
           PSC.CLDI_OID_CLIE_DIRE CLDI_OID_CLIE_DIRE_SOLI,
           PSC.ZTAD_OID_TERR_ADMI ZTAD_OID_TERR_ADMI_SOLI,
           PSC.ZZON_OID_ZONA ZZON_OID_ZONA_SOLI,
           PSC.TERR_OID_TERR TERR_OID_TERR_SOLI,
           PSC.VEPO_OID_VALO_ESTR_GEOP VEPO_OID_VALO_ESTR_GEOP_SOLI,
           (SELECT X.OID_CLIE_DIRE
            FROM MAE_CLIEN_DIREC X
            WHERE X.CLIE_OID_CLIE = PSC.CLIE_OID_CLIE
              AND X.IND_ELIM = 0
              AND X.IND_DIRE_PPAL = 1
              AND ROWNUM = 1
           ) OID_CLIE_DIRE_ACTU
    FROM PED_SOLIC_CABEC PSC,
         PED_TIPO_SOLIC_PAIS PTSP,
         PED_TIPO_SOLIC PTS
    WHERE PSC.TSPA_OID_TIPO_SOLI_PAIS = PTSP.OID_TIPO_SOLI_PAIS
    AND PTSP.TSOL_OID_TIPO_SOLI = PTS.OID_TIPO_SOLI
    AND PSC.PERD_OID_PERI = p_oidPeriodo
    AND NVL(PTS.IND_SOLI_NEGA, 0) = 0 -- solicitudes positivas
    AND PSC.GRPR_OID_GRUP_PROC = 4 -- solicitudes en GP4
    ) SOL
WHERE ZTA.TERR_OID_TERR = MCD.TERR_OID_TERR
AND ZTA.ZSCC_OID_SECC = SEC.OID_SECC
AND ZTA.IND_BORR = 0
AND ZTA.PERD_OID_PERI_FINA IS NULL
AND SEC.IND_BORR = 0
AND SEC.IND_ACTI = 1
AND SOL.CLIE_OID_CLIE = MCD.CLIE_OID_CLIE
AND SOL.OID_CLIE_DIRE_ACTU = MCD.OID_CLIE_DIRE
)
WHERE OID_CLIE_DIRE != CLDI_OID_CLIE_DIRE_SOLI
OR TERR_OID_TERR != TERR_OID_TERR_SOLI
OR OID_TERR_ADMI != ZTAD_OID_TERR_ADMI_SOLI
OR ZZON_OID_ZONA != ZZON_OID_ZONA_SOLI
OR VEPO_OID_VALO_ESTR_GEOP  != VEPO_OID_VALO_ESTR_GEOP_SOLI
ORDER BY CLIE_OID_CLIE;

TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
TYPE t_clie_oid_clie IS TABLE OF ped_solic_cabec.clie_oid_clie%TYPE;
TYPE t_oid_clie_dire IS TABLE OF ped_solic_cabec.cldi_oid_clie_dire%TYPE;
TYPE t_oid_terr_admi IS TABLE OF ped_solic_cabec.ztad_oid_terr_admi%TYPE;
TYPE t_zzon_oid_zona IS TABLE OF ped_solic_cabec.zzon_oid_zona%TYPE;
TYPE t_terr_oid_terr IS TABLE OF ped_solic_cabec.terr_oid_terr%TYPE;
TYPE t_vepo_oid_valo_estr_geop IS TABLE OF ped_solic_cabec.vepo_oid_valo_estr_geop%TYPE;

v_oid_soli_cabe t_oid_soli_cabe;
v_clie_oid_clie t_clie_oid_clie;
v_oid_clie_dire t_oid_clie_dire;
v_oid_terr_admi t_oid_terr_admi;
v_zzon_oid_zona t_zzon_oid_zona;
v_terr_oid_terr t_terr_oid_terr;
v_vepo_oid_valo_estr_geop t_vepo_oid_valo_estr_geop;

rows NATURAL        := 500;   -- Numero de filas a procesar a la vez
i    BINARY_INTEGER := 0;

l_codigoPeriodo         VARCHAR2(6);
l_oidPeriodo            NUMBER;

BEGIN

    -- Obtenemos el valor del periodo vigente
    SELECT COD_PERI
    INTO l_codigoPeriodo
    FROM BAS_CTRL_FACT
    WHERE STA_CAMP = 0
    AND IND_CAMP_ACT = 1;

    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(l_codigoPeriodo);

    OPEN c_pedidos(l_oidPeriodo);
    LOOP
        FETCH c_pedidos BULK COLLECT INTO
                        v_oid_soli_cabe,
                        v_clie_oid_clie,
                        v_oid_clie_dire,
                        v_oid_terr_admi,
                        v_zzon_oid_zona,
                        v_terr_oid_terr,
                        v_vepo_oid_valo_estr_geop LIMIT rows;
        EXIT WHEN v_oid_soli_cabe.count = 0;

        FORALL i IN 1..v_oid_soli_cabe.count
        UPDATE ped_solic_cabec psc
           SET psc.cldi_oid_clie_dire = v_oid_clie_dire(i),
               psc.ztad_oid_terr_admi = v_oid_terr_admi(i),
               psc.zzon_oid_zona = v_zzon_oid_zona(i),
               psc.terr_oid_terr = v_terr_oid_terr(i),
               psc.vepo_oid_valo_estr_geop = v_vepo_oid_valo_estr_geop(i)
         WHERE psc.oid_soli_cabe = v_oid_soli_cabe(i);

    END LOOP;
    CLOSE c_pedidos;

END;
/

