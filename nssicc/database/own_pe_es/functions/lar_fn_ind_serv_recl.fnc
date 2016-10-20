CREATE OR REPLACE FUNCTION "LAR_FN_IND_SERV_RECL" (
   p_oid_soli_cabe   IN   NUMBER,
   p_oid_peri        IN   NUMBER
)
   RETURN VARCHAR2
IS
   cont   NUMBER;
BEGIN
SELECT COUNT (1)
  INTO cont
  FROM ped_solic_cabec,
       (SELECT NVL (a.soca_oid_soli_cabe, 0), soca_oid_soli_cabe
          FROM ped_solic_cabec a
         WHERE a.perd_oid_peri = p_oid_peri
           AND a.tspa_oid_tipo_soli_pais IN (
                  SELECT d.oid_tipo_soli_pais
                    FROM ped_tipo_solic_pais d, ped_tipo_solic e
                   WHERE d.tsol_oid_tipo_soli = e.oid_tipo_soli
                     AND e.cod_tipo_soli = 'SOC')) tipo_tempo_3
 WHERE (    ped_solic_cabec.perd_oid_peri = p_oid_peri
        AND ped_solic_cabec.modu_oid_modu = 15
        AND ped_solic_cabec.ind_inte_lari_gene = 0
        AND ped_solic_cabec.soca_oid_soli_cabe = p_oid_soli_cabe
        AND ped_solic_cabec.soca_oid_soli_cabe = tipo_tempo_3.soca_oid_soli_cabe(+)
        AND tipo_tempo_3.soca_oid_soli_cabe IS NULL
        AND ped_solic_cabec.tspa_oid_tipo_soli_pais IN (
               SELECT ped_tipo_solic_pais.oid_tipo_soli_pais
                 FROM ped_tipo_solic_pais, ped_tipo_solic
                WHERE ped_tipo_solic_pais.tsol_oid_tipo_soli = ped_tipo_solic.oid_tipo_soli
                  AND ped_tipo_solic.ind_soli_nega = 0
                  AND ped_tipo_solic.ind_cons = 0)
       );


   IF (cont > 0)
   THEN
      RETURN '1';
   ELSE
      RETURN '0';
   END IF;
END LAR_FN_IND_SERV_RECL;
/

