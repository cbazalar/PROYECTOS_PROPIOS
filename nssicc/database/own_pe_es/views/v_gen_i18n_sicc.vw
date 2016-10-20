CREATE OR REPLACE VIEW V_GEN_I18N_SICC AS
SELECT t1.oid_i18n
      ,t1.attr_enti
      ,t1.attr_num_atri
      ,t1.idio_oid_idio
      ,t1.val_i18n
      ,t1.val_oid
      ,1  ind_idio_real
FROM GEN_I18N_SICC_PAIS t1
UNION ALL
-- traducciones por omision para aquellas que faltan
-- para un idioma concreto
-- Idioma por omision _FIJO_ a 1
SELECT t2."OID_I18N",t2."ATTR_ENTI",t2."ATTR_NUM_ATRI",t2."IDIO_OID_IDIO",t2."VAL_I18N",t2."VAL_OID"
      ,0 ind_idio_real
FROM ( SELECT t21.oid_i18n
             ,t21.attr_enti
             ,t21.attr_num_atri
             ,t22.oid_idio idio_oid_idio
             ,t21.val_i18n
             ,t21.val_oid
       FROM GEN_I18N_SICC_PAIS  t21
           ,seg_idiom      t22
       WHERE t21.idio_oid_idio = 1
         AND t22.oid_idio <> 1 ) t2
    ,GEN_I18N_SICC_PAIS t3
WHERE t3.attr_enti     (+) = t2.attr_enti
  AND t3.attr_num_atri (+) = t2.attr_num_atri
  AND t3.val_oid       (+) = t2.val_oid
  AND t3.idio_oid_idio (+) = t2.idio_oid_idio
  AND t3.val_oid IS NULL
UNION ALL
------------ Parte comun
SELECT t1.oid_i18n
      ,t1.attr_enti
      ,t1.attr_num_atri
      ,t1.idio_oid_idio
      ,t1.val_i18n
      ,t1.val_oid
      ,1  ind_idio_real
FROM gen_i18n_sicc_comun t1
UNION ALL
-- traducciones por omision para aquellas que faltan
-- para un idioma concreto
-- Idioma por omision _FIJO_ a 1
SELECT t2."OID_I18N",t2."ATTR_ENTI",t2."ATTR_NUM_ATRI",t2."IDIO_OID_IDIO",t2."VAL_I18N",t2."VAL_OID"
      ,0 ind_idio_real
FROM ( SELECT t21.oid_i18n
             ,t21.attr_enti
             ,t21.attr_num_atri
             ,t22.oid_idio idio_oid_idio
             ,t21.val_i18n
             ,t21.val_oid
       FROM gen_i18n_sicc_comun  t21
           ,seg_idiom      t22
       WHERE t21.idio_oid_idio = 1
         AND t22.oid_idio <> 1 ) t2
    ,gen_i18n_sicc_comun t3
WHERE t3.attr_enti     (+) = t2.attr_enti
  AND t3.attr_num_atri (+) = t2.attr_num_atri
  AND t3.val_oid       (+) = t2.val_oid
  AND t3.idio_oid_idio (+) = t2.idio_oid_idio
  AND t3.val_oid IS NULL;
comment on column V_GEN_I18N_SICC.OID_I18N is 'Identificador ?nico secuencial de la traducci?n de GEN_I18N_SICC';
comment on column V_GEN_I18N_SICC.ATTR_ENTI is 'Entidad (tabla) que se traduce';
comment on column V_GEN_I18N_SICC.ATTR_NUM_ATRI is 'N?mero de atributo (campo) que se traduce';
comment on column V_GEN_I18N_SICC.IDIO_OID_IDIO is 'Idioma en el que se est? traduciendo (puede o no ser real en funci?n de IND_IDIO_REAL)';
comment on column V_GEN_I18N_SICC.VAL_I18N is 'Valor de la traducci?n (texto traducido)';
comment on column V_GEN_I18N_SICC.VAL_OID is 'Identificador ?nico secuencial del registro que se est? traduciendo';
comment on column V_GEN_I18N_SICC.IND_IDIO_REAL is 'Indica si el idoma de IDIO_OID_IDIO representa el idioma en el que se ha realizado la traducci?n, o bien es una traducci?n predeterminada en el idioma predeterminado';

