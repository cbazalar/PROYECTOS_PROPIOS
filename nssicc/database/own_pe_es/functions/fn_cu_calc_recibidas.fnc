CREATE OR REPLACE FUNCTION "FN_CU_CALC_RECIBIDAS" (
    idPeriodo in NUMBER,
    idSubGV in NUMBER,
    idRegion in NUMBER,
    idZona in NUMBER,
    codEstRegistrada in VARCHAR,
    codEstNueva in VARCHAR
)
return number
is
  var_aux_num number;
begin
  EXECUTE IMMEDIATE 
'  SELECT ' ||  
'    COUNT(*) ' || 
'  FROM ' || 
'    mae_clien_unida_admin unida, ' || 
'    MAE_CLIEN_DATOS_ADICI adic, ' || 
'    zon_terri_admin terrAdmin, ' || 
'    ZON_SECCI secc, ' || 
'    ZON_ZONA zona, ' || 
'    ZON_REGIO reg, ' ||
'    MAE_ESTAT_CLIEN MEC_REG, ' ||
'    MAE_ESTAT_CLIEN MEC_NUE ' ||
'  WHERE terrAdmin.ZSCC_OID_SECC = secc.OID_SECC ' ||  
'    AND secc.ZZON_OID_ZONA = zona.OID_ZONA ' ||  
'    AND zona.ZORG_OID_REGI = reg.OID_REGI ' ||  
'    AND adic.clie_oid_clie = unida.clie_oid_clie ' ||          
'    AND unida.ztad_oid_terr_admi = terrAdmin.oid_terr_admi ' ||
'    AND adic.esta_oid_esta_clie = MEC_REG.OID_ESTA_CLIE ' ||
'    AND adic.esta_oid_esta_clie = MEC_NUE.OID_ESTA_CLIE ' ||
'    AND unida.perd_oid_peri_ini = :1 ' ||
'    AND reg.ZSGV_OID_SUBG_VENT = :2 ' ||
'    AND reg.OID_REGI = :3 ' ||
'    AND zona.OID_ZONA = :4 ' ||  
'    AND MEC_REG.COD_ESTA_CLIE <> :5 ' ||
'    AND MEC_NUE.COD_ESTA_CLIE <> :6' 
  INTO var_aux_num
  USING idPeriodo, idSubGV, idRegion, idZona, codEstRegistrada, codEstNueva;
    return var_aux_num;
end;
/

