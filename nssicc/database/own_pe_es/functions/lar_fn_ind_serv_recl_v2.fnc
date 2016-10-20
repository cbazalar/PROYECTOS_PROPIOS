CREATE OR REPLACE FUNCTION "LAR_FN_IND_SERV_RECL_V2"
    (p_oid_soli_cabe IN NUMBER,
                              p_oid_peri IN number) RETURN VARCHAR2 IS
  cont NUMBER;
BEGIN
    SELECT COUNT(1)
    INTO cont
    FROM PED_SOLIC_CABEC , TIPO_TEMPO_3
   WHERE (
         (PED_SOLIC_CABEC.PERD_OID_PERI=p_oid_peri)
        AND (PED_SOLIC_CABEC.MODU_OID_MODU = 15)
        AND (PED_SOLIC_CABEC.IND_INTE_LARI_GENE = 0)
        AND (PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE = p_oid_soli_cabe)
  AND (PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS in (Select OID_TIPO_SOLI_PAIS from TIPO_TEMPO_1))
  AND (PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE =TIPO_TEMPO_3.SOCA_OID_SOLI_CABE(+) and
           TIPO_TEMPO_3.SOCA_OID_SOLI_CABE is NULL  )        );
IF (cont > 0) THEN
      RETURN '1';
    ELSE
      RETURN '0';
    END IF;
END LAR_FN_IND_SERV_RECL_V2;
/
