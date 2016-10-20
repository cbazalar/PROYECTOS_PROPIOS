CREATE OR REPLACE FUNCTION "FN_CU_CALC_ACTIVAS_FINAL" (
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
   'SELECT  ' ||
   '  COUNT (clientes_pedidos.CLIE_OID_CLIE) CANTIDAD ' ||
   'FROM  ' ||
   '  ( ' || 
   '  SELECT DISTINCT ' || 
   '      sol.CLIE_OID_CLIE ' || 
   '  FROM ' || 
   '    PED_SOLIC_CABEC sol , ' ||  
   '    ( ' ||
   '    SELECT ' || 
   '      SEC.OID_SECC ZSCC_OID_SECC, ' ||  
   '      UNA.CLIE_OID_CLIE ' || 
   '    FROM ' || 
   '      MAE_CLIEN_UNIDA_ADMIN UNA, ' ||  
   '      ZON_TERRI_ADMIN TERR, ' ||  
   '      ZON_SECCI SEC, ' ||        
   '      ZON_ZONA ZON, ' || 
   '      ZON_REGIO REG, ' || 
   '      ZON_SUB_GEREN_VENTA SUB, ' || 
   '      CRA_PERIO PER_DSD,  ' ||
   '      CRA_PERIO PER_HST,  ' ||
   '      CRA_PERIO PER_ENT  ' ||
   '      WHERE UNA.ZTAD_OID_TERR_ADMI = TERR.OID_TERR_ADMI ' || 
   '      AND TERR.ZSCC_OID_SECC = SEC.OID_SECC   ' ||
   '      AND SEC.ZZON_OID_ZONA = ZON.OID_ZONA   ' ||
   '      AND ZON.ZORG_OID_REGI = REG.OID_REGI  ' ||
   '      AND REG.ZSGV_OID_SUBG_VENT = SUB.OID_SUBG_VENT ' || 
   '      AND UNA.PERD_OID_PERI_INI = PER_DSD.OID_PERI  ' ||
   '      AND UNA.PERD_OID_PERI_FIN = PER_HST.OID_PERI(+) ' || 
   '      AND PER_ENT.OID_PERI = :1' ||  
   '      AND PER_DSD.FEC_INIC <= PER_ENT.FEC_INIC ' || 
   '      AND (PER_HST.OID_PERI IS NULL OR PER_HST.FEC_FINA >= PER_ENT.FEC_FINA) ' || 
   '      AND UNA.IND_ACTI = 1 ' ||
   '      AND REG.ZSGV_OID_SUBG_VENT = :2' ||
   '      AND REG.OID_REGI = :3' ||
   '      AND ZON.OID_ZONA = :4' ||
   '    ) terrAdmin ' ||
   '  WHERE ' || 
   '      SOL.CLIE_OID_CLIE = terrAdmin.CLIE_OID_CLIE ' || 
   '    AND SOL.FEC_FACT IS NOT NULL ' ||
   '    AND SOL.IND_OC = 1 ' ||
   '  ) clientes_pedidos '
  INTO var_aux_num
  USING idPeriodo, idSubGV, idRegion, idZona;
    return var_aux_num;
end;
/

