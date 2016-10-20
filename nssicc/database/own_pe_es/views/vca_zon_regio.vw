CREATE OR REPLACE VIEW VCA_ZON_REGIO AS
SELECT T1.COD_USUA COD_USUA
          ,T2.OID_REGI OID_REGI
          ,T2.COD_REGI COD_REGI
          ,T2.COD_NSE1 COD_NSE1
          ,T2.COD_NSE2 COD_NSE2
          ,T2.COD_NSE3 COD_NSE3
          ,T2.IND_ACTI IND_ACTI
          ,T2.IND_BORR IND_BORR
          ,T2.DES_REGI DES_REGI
          ,T2.ZSGV_OID_SUBG_VENT ZSGV_OID_SUBG_VENT
          ,T2.CLIE_OID_CLIE CLIE_OID_CLIE
FROM VCA_MGU_PERMI_USUAR T1
    ,ZON_REGIO T2
  WHERE t1.val_prop = to_char(t2.oid_REGI)
    and t1.cod_prop = 'Region';
comment on column VCA_ZON_REGIO.COD_USUA is 'Codigo de usuario';
comment on column VCA_ZON_REGIO.OID_REGI is 'Identificador unico secuencial (OID) de la region';
comment on column VCA_ZON_REGIO.COD_REGI is 'Codigo de negocio de la region';
comment on column VCA_ZON_REGIO.COD_NSE1 is 'Nivel socio-economico 1. Se carga cuando se crea la region';
comment on column VCA_ZON_REGIO.COD_NSE2 is 'Nivel socio-economico 2';
comment on column VCA_ZON_REGIO.COD_NSE3 is 'Nivel socio-economico 3. Se obtiene a partir de la Moda (valor mas repetido) del nivel socio-economico 3 de las secciones';
comment on column VCA_ZON_REGIO.IND_ACTI is 'Indicador de region activa';
comment on column VCA_ZON_REGIO.IND_BORR is 'Indicador de borrado logico de la region';
comment on column VCA_ZON_REGIO.DES_REGI is 'Descripcion de la region';
comment on column VCA_ZON_REGIO.ZSGV_OID_SUBG_VENT is 'Secuencial OID (clave)';
comment on column VCA_ZON_REGIO.CLIE_OID_CLIE is 'Identificador unico de cliente';

