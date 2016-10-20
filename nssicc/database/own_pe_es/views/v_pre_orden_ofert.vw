CREATE OR REPLACE VIEW V_PRE_ORDEN_OFERT AS
SELECT ROWNUM AS linea, idio_oid_idio, oid_cabe, num_orde, num_line_ofer, num_ofer, cod_sap,
val_codi_vent, desc_produ, imp_prec_cata, grupo,
val_fact_repe, ind_impr_gp, ind_digi, ocat_oid_catal, num_pagi_cata,
oid_tipo_ofer, cod_tipo_ofer, temp.val_i18n AS desc_estra
FROM (SELECT   idio_oid_idio, cmf.oid_cabe, num_line_ofer,
                 dofe.num_orde_deta AS num_orde, /** Anteriormente se obtenia de la oferta */
                 ofe.num_ofer, prod.cod_sap,
                 val_codi_vent,
                 dofe.val_text_brev AS desc_produ,  /**VAL_I18N AS DESC_PRODU*/
                 dofe.imp_prec_cata, ofe.num_grup AS grupo, val_fact_repe,
                 ind_impr_gp, ind_digi, dofe.ocat_oid_catal, /** Agregados por SICC_GCC_PRE_003*/
                 dofe.num_pagi_cata, tofe.oid_tipo_ofer, tofe.cod_tipo_ofer,
                 idio.val_i18n                                 --AS DESC_ESTRA
            FROM pre_matri_factu_cabec cmf,
                 pre_ofert ofe,
                 pre_ofert_detal dofe,
                 mae_produ prod,
                 v_gen_i18n_sicc idio, /** Agregado por SICC_GCC_PRE_003*/
                 pre_tipo_ofert tofe
           WHERE ofe.oid_ofer = dofe.ofer_oid_ofer
             AND dofe.prod_oid_prod = prod.oid_prod
             AND ofe.mfca_oid_cabe = cmf.oid_cabe
             AND idio.attr_enti = 'PRE_ESTRA'
             /** Modificado por SICC_GCC_PRE_003 decia MAE_PRODU*/
             AND idio.val_oid = ofe.coes_oid_estr
             /** Modificado por SICC_GCC_PRE_003 hacia join con el oid de producto*/
             AND dofe.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer
        UNION ALL
        SELECT   oid_idio, cmf.oid_cabe, tofe.num_orde AS num_orde, num_orde,
                 NULL, NULL, NULL, tofe.val_text, NULL, NULL, NULL,
                 1,                                               -- imprimible
                 NULL, tofe.ocat_oid_cata, NULL, NULL, NULL, NULL
            FROM pre_texto_ofert tofe,
                 pre_matri_factu_cabec cmf,
                 seg_idiom                   -- no esta en join no es un error
           WHERE tofe.mfca_oid_cabe = cmf.oid_cabe
        ORDER BY idio_oid_idio, oid_cabe, num_orde, num_line_ofer) temp;
comment on column V_PRE_ORDEN_OFERT.LINEA is 'Linea';
comment on column V_PRE_ORDEN_OFERT.IDIO_OID_IDIO is 'Identificador del idioma';
comment on column V_PRE_ORDEN_OFERT.OID_CABE is 'Identificador de la cabecera de matriz de facturacion';
comment on column V_PRE_ORDEN_OFERT.NUM_ORDE is 'Numero de orden';
comment on column V_PRE_ORDEN_OFERT.NUM_LINE_OFER is 'Numero de linea oferta';
comment on column V_PRE_ORDEN_OFERT.NUM_OFER is 'Numero de oferta';
comment on column V_PRE_ORDEN_OFERT.COD_SAP is 'Codigo SAP';
comment on column V_PRE_ORDEN_OFERT.VAL_CODI_VENT is 'Codigo de venta';
comment on column V_PRE_ORDEN_OFERT.DESC_PRODU is 'Descripcion del producto';
comment on column V_PRE_ORDEN_OFERT.IMP_PREC_CATA is 'Precio de catalogo';
comment on column V_PRE_ORDEN_OFERT.GRUPO is 'Grupo';
comment on column V_PRE_ORDEN_OFERT.VAL_FACT_REPE is 'Factor de repeticion';
comment on column V_PRE_ORDEN_OFERT.IND_IMPR_GP is 'Indicador de impresion Guia Productos';
comment on column V_PRE_ORDEN_OFERT.IND_DIGI is 'Indicador de digitable';
comment on column V_PRE_ORDEN_OFERT.NUM_PAGI_CATA is 'Numero de pagina';
comment on column V_PRE_ORDEN_OFERT.OID_TIPO_OFER is 'Identificador del tipo de oferta';
comment on column V_PRE_ORDEN_OFERT.COD_TIPO_OFER is 'Codigo del tipo de oferta';
comment on column V_PRE_ORDEN_OFERT.DESC_ESTRA is 'Descripcion de la estrategia';

