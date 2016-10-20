CREATE OR REPLACE FUNCTION "FN_CU_CALC_ENTREGADAS" (
    idPeriodo in NUMBER,
    idSubGV in NUMBER,
    idRegion in NUMBER,
    idZona in NUMBER
)
return number
is
  var_aux_num number;
begin
  EXECUTE IMMEDIATE 
'SELECT ' || 
'    COUNT (1) COUNT ' || 
'FROM ' || 
'( ' ||
'SELECT ' ||  
'  COUNT(1) ' || 
'FROM  ' ||
'  mae_clien_unida_admin unida, ' ||
'  zon_terri_admin terrAdmin, ' || 
'  ZON_SECCI secc, ' || 
'  ZON_ZONA zona, ' || 
'  ZON_REGIO reg ' || 
'WHERE terrAdmin.ZSCC_OID_SECC = secc.OID_SECC ' ||  
'  AND secc.ZZON_OID_ZONA = zona.OID_ZONA ' ||  
'  AND zona.ZORG_OID_REGI = reg.OID_REGI  ' || 
'  AND unida.ztad_oid_terr_admi = terrAdmin.oid_terr_admi ' ||
'  AND unida.perd_oid_peri_fin = :1 ' ||
'  AND reg.ZSGV_OID_SUBG_VENT = :2 ' || 
'  AND reg.OID_REGI = :3' ||
'  AND zona.OID_ZONA = :4' ||
'GROUP BY unida.clie_oid_clie)'
  INTO var_aux_num
  USING idPeriodo, idSubGV, idRegion, idZona;
    return var_aux_num;
end;
/

