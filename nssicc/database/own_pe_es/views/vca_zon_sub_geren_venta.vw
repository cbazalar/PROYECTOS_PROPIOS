CREATE OR REPLACE VIEW VCA_ZON_SUB_GEREN_VENTA AS
SELECT T1.COD_USUA COD_USUA
          ,T2.OID_SUBG_VENT OID_SUBG_VENT
          ,T2.COD_SUBG_VENT COD_SUBG_VENT
          ,T2.COD_NSE1 COD_NSE1
          ,T2.COD_NSE2 COD_NSE2
          ,T2.COD_NSE3 COD_NSE3
          ,T2.IND_BORR IND_BORR
          ,T2.IND_ACTI IND_ACTI
          ,T2.DES_SUBG_VENT DES_SUBG_VENT
          ,T2.CANA_OID_CANA CANA_OID_CANA
          ,T2.MARC_OID_MARC MARC_OID_MARC
          ,T2.PAIS_OID_PAIS PAIS_OID_PAIS
          ,T2.CLIE_OID_CLIE CLIE_OID_CLIE
FROM VCA_MGU_PERMI_USUAR T1
    ,ZON_SUB_GEREN_VENTA T2
  WHERE t1.val_prop = to_char(t2.oid_SUBG_VENT)
    and t1.cod_prop = 'SubgerenciaVentas';
comment on column VCA_ZON_SUB_GEREN_VENTA.COD_USUA is 'Codigo de usuario';
comment on column VCA_ZON_SUB_GEREN_VENTA.OID_SUBG_VENT is 'Identificador unico secuencial (OID) de la subgerencia de venta';
comment on column VCA_ZON_SUB_GEREN_VENTA.COD_SUBG_VENT is 'Codigo de negocio de la Subgerencia de Ventas';
comment on column VCA_ZON_SUB_GEREN_VENTA.COD_NSE1 is 'Nivel socio-economico 1. Se carga al crear la subgerencia de venta';
comment on column VCA_ZON_SUB_GEREN_VENTA.COD_NSE2 is 'Nivel socio-economico 2';
comment on column VCA_ZON_SUB_GEREN_VENTA.COD_NSE3 is 'Nivel socio-economico 3. Se obtiene a partir de la Moda (valor mas repetido) del nivel socio-economico 3 de las zonas contenidas en la subgerencia';
comment on column VCA_ZON_SUB_GEREN_VENTA.IND_BORR is 'Indicador de borrado logico de la subgerencia de ventas';
comment on column VCA_ZON_SUB_GEREN_VENTA.IND_ACTI is 'Indicador de subgerencia activa';
comment on column VCA_ZON_SUB_GEREN_VENTA.DES_SUBG_VENT is 'Descripcin de subgerencia de ventas';
comment on column VCA_ZON_SUB_GEREN_VENTA.CANA_OID_CANA is 'Identificador unico del canal';
comment on column VCA_ZON_SUB_GEREN_VENTA.MARC_OID_MARC is 'Identificador unico de la marca';
comment on column VCA_ZON_SUB_GEREN_VENTA.PAIS_OID_PAIS is 'Identificador unico del pais';
comment on column VCA_ZON_SUB_GEREN_VENTA.CLIE_OID_CLIE is 'Identificador unico de cliente';

