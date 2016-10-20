CREATE OR REPLACE FUNCTION "FN_CU_CALC_CARGOS_ABONOS_DAT" (
 idzona_par in varchar2,
 idinda_par in varchar2,
 idindb_par in varchar2
)
return number
is
IMPORTE NUMBER;
BEGIN
SELECT SUM (cd.imp) INTO IMPORTE
  FROM ccc_detal_cargo_abono_direc cd,
       ccc_tipo_abono_subpr ct,
       (SELECT cm.oid_movi_cc
          FROM ccc_movim_cuent_corri cm, zon_secci zs
         WHERE cm.zscc_oid_secc = zs.oid_secc
               AND zs.zzon_oid_zona = idzona_par) movim,
       (SELECT tcab_oid_tipo_carg_abon
          FROM int_tipos_abono_dat itadat
         WHERE itadat.ind_indi = idinda_par OR itadat.ind_indi = idindb_par) idat
 WHERE cd.tasp_oid_tipo_abon_subp = ct.oid_tipo_abon_subp
   AND ct.tcab_oid_tcab = idat.tcab_oid_tipo_carg_abon
   AND cd.mvcc_oid_movi_cc = movim.oid_movi_cc;

RETURN IMPORTE;
end;
/

