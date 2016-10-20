CREATE OR REPLACE FUNCTION "OBTENER_ULTIMA_FECHA_FACT" (OIDCLIENTE IN NUMBER, OIDPAIS IN NUMBER) RETURN VARCHAR2 AS
SALIDA VARCHAR2(6);
BEGIN
SELECT cod INTO SALIDA
  FROM (select psc2.fec_cron, pcor3.COD_PERI cod
            FROM ped_solic_cabec psc2, cra_perio crp2,
        				 seg_perio_corpo pcor3, ped_tipo_solic ts, ped_tipo_solic_pais tsp
           WHERE psc2.perd_oid_peri = crp2.oid_peri
             AND crp2.peri_oid_peri = pcor3.oid_peri
             AND psc2.pais_oid_pais = OIDPAIS
             AND psc2.fec_fact IS NOT NULL
       			 AND psc2.CLIE_OID_CLIE = OIDCLIENTE
             AND psc2.ind_pedi_prue = 0
             AND psc2.ind_oc = 1
             AND psc2.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
             AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
             AND ts.COD_TIPO_SOLI ='SOC'
        ORDER BY 1 DESC)
 WHERE ROWNUM = 1;
 RETURN SALIDA;
END;
/

