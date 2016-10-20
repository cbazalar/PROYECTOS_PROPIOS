CREATE OR REPLACE FUNCTION "FN_CU_CALC_SALDO_DAT_32" (
 idpais_par in varchar := NULL,
 idzona_par in varchar := NULL,
 idf_desde_par in varchar := '2000-01-01',
 idf_hasta_par in varchar := NULL
)
return number
is
INDICADOR NUMBER;
BEGIN
SELECT SUM (m.imp_pend) INTO INDICADOR
  FROM ccc_movim_cuent_corri m,       
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
           AND u.pais_oid_pais = idpais_par
           AND m.ind_entr_sali LIKE 'E') marcasitu
 WHERE    
   EXISTS(SELECT zs.OID_SECC FROM zon_secci zs 
        where zs.zzon_oid_zona = idzona_par 
     AND zs.OID_SECC = m.ZSCC_OID_SECC) 
   AND FEC_DOCU > TO_DATE(idf_desde_par,'yyyy-MM-dd')
   AND FEC_DOCU < TO_DATE(idf_hasta_par,'yyyy-MM-dd')  
   AND m.imp_pend <> 0
   AND m.masi_oid_marc_situ = marcasitu.oid_marc_situ;
RETURN INDICADOR;
end;
/

