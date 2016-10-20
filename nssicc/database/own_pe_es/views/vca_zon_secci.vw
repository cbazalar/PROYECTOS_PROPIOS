CREATE OR REPLACE VIEW VCA_ZON_SECCI AS
SELECT T1.COD_USUA COD_USUA
          ,T2.OID_SECC OID_SECC
          ,T2.COD_NSE1 COD_NSE1
          ,T2.COD_NSE2 COD_NSE2
          ,T2.COD_NSE3 COD_NSE3
          ,T2.COD_SECC COD_SECC
          ,T2.IND_ACTI IND_ACTI
          ,T2.IND_BORR IND_BORR
          ,T2.DES_SECCI DES_SECCI
          ,T2.ZZON_OID_ZONA ZZON_OID_ZONA
          ,T2.CLIE_OID_CLIE CLIE_OID_CLIE
FROM VCA_MGU_PERMI_USUAR T1
    ,ZON_SECCI T2
  WHERE t1.val_prop = to_char(t2.oid_SECC)
    and t1.cod_prop = 'Seccion';
comment on column VCA_ZON_SECCI.COD_USUA is 'Codigo de usuario';
comment on column VCA_ZON_SECCI.OID_SECC is 'Identificador unico secuencial (OID) de la seccion';
comment on column VCA_ZON_SECCI.COD_NSE1 is 'Nivel socio-economico 3. Se obtiene a partir de la Moda (valor mas repetido) del nivel socio-economico 3 de los territorios asociados a la seccion';
comment on column VCA_ZON_SECCI.COD_NSE2 is 'Nivel socio-economico 2';
comment on column VCA_ZON_SECCI.COD_NSE3 is 'Nivel socio-economico 1. Se carga al crear la seccion';
comment on column VCA_ZON_SECCI.COD_SECC is 'Codigo de negocio de la seccion';
comment on column VCA_ZON_SECCI.IND_ACTI is 'Indicador de seccion activa';
comment on column VCA_ZON_SECCI.IND_BORR is 'Indicador de borrado logico de la seccion';
comment on column VCA_ZON_SECCI.DES_SECCI is 'Descripcion de la seccion';
comment on column VCA_ZON_SECCI.ZZON_OID_ZONA is 'Identificador unico de zona';
comment on column VCA_ZON_SECCI.CLIE_OID_CLIE is 'Identificador unico de cliente';

