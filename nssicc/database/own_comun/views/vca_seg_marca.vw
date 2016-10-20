CREATE OR REPLACE VIEW VCA_SEG_MARCA AS
SELECT T1.COD_USUA COD_USUA
          ,T2.oid_marc
          ,T2.cod_marc
          ,T2.des_marc
FROM VCA_MGU_PERMI_USUAR T1
    ,SEG_MARCA T2
  WHERE t1.val_prop = to_char(t2.oid_marc)
    and t1.cod_prop = 'Marca';

