CREATE OR REPLACE FUNCTION "FN_CU_CALC_IND_INCOBRABLE" (
 idpais_par in varchar2,
 idmarca_par in varchar2,
 idcanal_par in varchar2,
 idclien_par in varchar2
)
return number
is
INDICADOR NUMBER;
BEGIN
SELECT CASE WHEN (count(*) >= 1) THEN
      1
    ELSE
         0
    END INTO INDICADOR
FROM CCC_MOVIM_CUENT_CORRI m, CRA_PERIO cp 
WHERE m.CLIE_OID_CLIE = idclien_par
   AND m.IMP_PEND <> 0
   AND cp.CANA_OID_CANA = idcanal_par
   AND cp.pais_oid_pais = idpais_par
   AND m.PERD_OID_PERI = cp.OID_PERI
   AND m.MARC_OID_MARC = idmarca_par
   AND m.MASI_OID_MARC_SITU in (SELECT
     u.OID_MARC_SITU
    FROM
     CCC_MARCA_SITUA u,CCC_MARCA_TIPO_ABONO m,
     CCC_TIPO_ABONO_SUBPR t,CCC_SUBPR s,
     CCC_PROCE p
    WHERE u.OID_MARC_SITU = m.MASI_OID_MARC_SALI
     AND m.TASP_OID_TIPO_ABON_SUBP = t.OID_TIPO_ABON_SUBP
     AND t.SUBP_OID_SUBP = s.OID_SUBP
     AND s.CCPR_OID_PROC = p.OID_PROC
     AND s.COD_SUBP = 1
     AND p.COD_PROC = 'CON002'
     AND u.PAIS_OID_PAIS = idpais_par
     AND m.IND_ENTR_SALI like 'E');
RETURN INDICADOR;
end;
/

