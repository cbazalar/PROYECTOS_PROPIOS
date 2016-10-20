CREATE OR REPLACE FUNCTION "FN_CU_CALC_CANT_POR_ESTADO" (
    idSubGV in NUMBER,
    idRegion in NUMBER,
    idZona in NUMBER,
    anio in VARCHAR,
    codEstadoIN in VARCHAR
)
return number
is
  var_aux_num number;
begin
  EXECUTE IMMEDIATE 
    'SELECT ' || 
   '  COUNT (*) count ' ||
   'FROM ' ||
   '  mae_clien_unida_admin unida, ' ||
   '  cra_perio p1, ' || 
   '  cra_perio p2, ' ||
   '  zon_terri_admin terrAdmin, ' || 
   '  mae_clien_histo_estat histo, ' ||
   '  cra_perio p3, ' || 
   '  cra_perio p4, ' ||
   '  ZON_SECCI secc, ' || 
   '  ZON_ZONA zona, ' || 
   '  ZON_REGIO reg, ' ||
   '  MAE_ESTAT_CLIEN ESTADO ' ||
   'WHERE ' || 
   '  terrAdmin.ZSCC_OID_SECC = secc.OID_SECC ' ||  
   '  AND secc.ZZON_OID_ZONA = zona.OID_ZONA ' ||  
   '  AND zona.ZORG_OID_REGI = reg.OID_REGI ' || 
   '  AND unida.perd_oid_peri_ini = p1.oid_peri(+) ' ||
   '  AND unida.perd_oid_peri_fin = p2.oid_peri(+) ' ||
   '  AND (p1.fec_inic IS NULL OR p1.fec_inic <= TO_DATE(''31/12/'||anio||''', ''dd/MM/yyyy'')) ' ||
   '  AND (p2.fec_fina IS NULL OR p2.fec_fina >= TO_DATE(''01/01/'||anio||''', ''dd/MM/yyyy'')) ' ||
   '  AND histo.clie_oid_clie = unida.clie_oid_clie ' ||
   '  AND histo.perd_oid_peri = p3.oid_peri(+) ' ||
   '  AND histo.perd_oid_peri_peri_fin = p4.oid_peri(+) ' ||
   '  AND (p3.fec_inic IS NULL OR p3.fec_inic <= TO_DATE(''31/12/'||anio||''', ''dd/MM/yyyy'')) ' ||
   '  AND (p4.fec_fina IS NULL OR p4.fec_fina >= TO_DATE(''01/01/'||anio||''', ''dd/MM/yyyy'')) ' ||
   '  AND unida.ztad_oid_terr_admi = terrAdmin.oid_terr_admi ' ||
   '  AND histo.ESTA_OID_ESTA_CLIE = ESTADO.OID_ESTA_CLIE ' ||
   '  AND ESTADO.COD_ESTA_CLIE IN ('||codEstadoIN||') ' ||
   '  AND reg.ZSGV_OID_SUBG_VENT = '||idSubGV|| 
   '  AND reg.OID_REGI = ' ||idRegion||
   '  AND zona.OID_ZONA ='||idZona 
  INTO var_aux_num;
--  USING idSubGV, idRegion, idZona, anio, codEstadoIN;
    return var_aux_num;
end;
/

