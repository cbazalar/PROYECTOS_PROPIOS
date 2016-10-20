CREATE OR REPLACE FUNCTION "FN_TOT_DEPURA_DAT_32" (
 idpais_par in varchar:= NULL,
 idzona_par in varchar:= NULL
)
return number
is
INDICADOR NUMBER;
BEGIN
SELECT SUM(mcc.IMP_PEND) INTO INDICADOR
FROM CCC_MOVIM_CUENT_CORRI mcc,
  (SELECT u.oid_marc_situ 
          FROM ccc_marca_situa u,
               ccc_marca_tipo_abono m,
               ccc_tipo_abono_subpr t,
               ccc_subpr s,
               ccc_proce p
         WHERE u.oid_marc_situ = m.masi_oid_marc_sali
           AND m.tasp_oid_tipo_abon_subp = t.oid_tipo_abon_subp
           AND t.subp_oid_subp = s.oid_subp
           AND s.ccpr_oid_proc = p.oid_proc
           AND s.cod_subp = 1
           AND p.cod_proc = 'CON001'
     AND u.IND_CUEN_CAST = 1
           AND u.pais_oid_pais = idpais_par
           AND m.ind_entr_sali LIKE 'E') marcasitu
WHERE 
   EXISTS(SELECT zs.OID_SECC 
            FROM zon_secci zs 
      where zs.OID_SECC = mcc.ZSCC_OID_SECC 
      AND zs.zzon_oid_zona = idzona_par )  
      AND mcc.IMP_PEND <> 0   
      AND mcc.MASI_OID_MARC_SITU = marcasitu.oid_marc_situ;
RETURN INDICADOR;
end;
/

