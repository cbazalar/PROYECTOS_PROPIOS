CREATE OR REPLACE FUNCTION PED_OBTENER_LIMITE_VENTA2 (
      idperi IN NUMBER,
      idcliente IN NUMBER,
      idCabeceraSolicitud IN NUMBER,
      idestado IN NUMBER,
      idIdioma IN NUMBER
)
RETURN OBJ_PED_OBT_LIMITE_VENTA_TABLE PIPELINED
IS
CURSOR salida_cur IS
SELECT PO.OID_SOLI_POSI,
                           PO.NUM_UNID_DEMA,
                           PO.NUM_UNID_POR_ATEN,
                           CV.VAL_CODI_VENT,
                           CL.COD_CLIE,
                           PO.OFDE_OID_DETA_OFER,
                           MAX(VAL_LIMI_CTRL_VENT) VAL_LIMI_CTRL_VENT,
                           PQ_APL_AUX.Valor_Gen_I18n_Sicc(idIdioma, po.PROD_OID_PROD, 'MAE_PRODU') AS DESC_PRODUCTO
FROM
  PED_SOLIC_POSIC PO, PED_SOLIC_CABEC CA, ZON_ZONA ZO, MAE_CLIEN CL,
  PRE_OFERT_DETAL CV,  (SELECT * FROM PED_GESTI_STOCK ST, V_MAE_TIPIF_CLIEN
  TP  WHERE IND_ULTI_NOTI=1 
  and (st.ind_acti='1' or st.ind_acti is null)
  AND ST.PERD_OID_PERI = idperi
   AND VAL_LIMI_CTRL_VENT
  IS NOT NULL  AND TP.CLIE_OID_CLIE = idcliente AND (ST.TICL_OID_TIPO_CLIE is null or ST.TICL_OID_TIPO_CLIE =
  TP.TICL_OID_TIPO_CLIE) AND (ST.SBTI_OID_SUBT_CLIE IS NULL OR
  ST.SBTI_OID_SUBT_CLIE = TP.SBTI_OID_SUBT_CLIE)  AND (ST.TCCL_OID_TIPO_CLAS
  IS NULL OR ST.TCCL_OID_TIPO_CLAS = TP.TCCL_OID_TIPO_CLASI) AND
  (ST.CLAS_OID_CLAS IS NULL OR ST.CLAS_OID_CLAS = TP.CLAS_OID_CLAS)) ST WHERE
  PO.SOCA_OID_SOLI_CABE = idCabeceraSolicitud AND PO.SOCA_OID_SOLI_CABE =
  CA.OID_SOLI_CABE AND CA.ZZON_OID_ZONA = ZO.OID_ZONA AND CL.OID_CLIE =
  CA.CLIE_OID_CLIE  AND PO.espo_oid_esta_posi <> idestado AND CV.OID_DETA_OFER =
  PO.OFDE_OID_DETA_OFER AND ST.OFDE_OID_DETA_OFER = PO.OFDE_OID_DETA_OFER AND
  (ST.ZORG_OID_REGI IS NULL OR ST.ZORG_OID_REGI = ZO.ZORG_OID_REGI) AND
  (ST.ZZON_OID_ZONA IS NULL OR ST.ZZON_OID_ZONA=CA.ZZON_OID_ZONA) AND
  PO.IND_LIMI_VENT IS NULL
  GROUP BY
  PO.OID_SOLI_POSI,PO.NUM_UNID_DEMA,PO.NUM_UNID_POR_ATEN,CV.VAL_CODI_VENT,
  CL.COD_CLIE,PO.OFDE_OID_DETA_OFER,PQ_APL_AUX.Valor_Gen_I18n_Sicc(idIdioma, po.PROD_OID_PROD, 'MAE_PRODU');

  fila OBJ_PED_OBTENER_LIMITE_VENTA;

BEGIN
  FOR i IN salida_cur LOOP
      BEGIN
          fila := OBJ_PED_OBTENER_LIMITE_VENTA(i.OID_SOLI_POSI, i.NUM_UNID_DEMA, i.NUM_UNID_POR_ATEN, i.VAL_CODI_VENT,
                      i.COD_CLIE, i.OFDE_OID_DETA_OFER, i.VAL_LIMI_CTRL_VENT, i.DESC_PRODUCTO);
          PIPE ROW(fila);
      END;
  END LOOP;

  RETURN;
END;
/
