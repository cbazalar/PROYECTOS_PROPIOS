CREATE OR REPLACE VIEW V_REP_PREMIOS AS
SELECT PAGP_OID_PARA_GENE_PREM, desc_premio.oid_para_nive_prem,  desc_premio.OID_TIPO_PREM, gen_pre.tipo_premio, desc_premio.num_prem, desc_premio.cantidad, desc_premio.oid_tipo, desc_premio.descripcion, desc_premio.idio_tipo_premio
FROM
   (
               SELECT val_oid, val_i18n tipo_premio, idio_oid_idio AS idio_tipo_premio
               FROM v_gen_i18n_sicc
               WHERE attr_enti = 'INC_TIPO_PREMI'
        )gen_pre,
       (
               (
               SELECT niv_pre.PAGP_OID_PARA_GENE_PREM, niv_pre.OID_PARA_NIVE_PREM, tip_pre.OID_TIPO_PREM, lot_art.NUM_PREM, pre_art.NUM_UNID AS cantidad, lot_art.OID_LOTE_PREM_ARTI AS oid_tipo, lot_art.VAL_DESC_LOTE_PREM_ARTI AS descripcion,
      idio_tipo_premio
                FROM INC_PARAM_NIVEL_PREMI niv_pre,
                          inc_tipo_premi tip_pre,
                          INC_PREMI_ARTIC pre_art,
                          INC_LOTE_PREMI_ARTIC lot_art,
        (
                  SELECT val_oid, val_i18n tipo_premio, idio_oid_idio AS idio_tipo_premio
                  FROM v_gen_i18n_sicc
                  WHERE attr_enti = 'INC_TIPO_PREMI'
        ) gen
               WHERE niv_pre.tpre_oid_tipo_prem = tip_pre.oid_tipo_prem
                         AND niv_pre.oid_para_nive_prem = pre_art.panp_oid_para_nive_prem
                         AND pre_art.oid_prem_arti = lot_art.prar_oid_prem_arti
                         AND tip_pre.OID_TIPO_PREM = 2
       AND tip_pre.OID_TIPO_PREM = gen.val_oid(+)
               )
               UNION
               (
               SELECT niv_pre.PAGP_OID_PARA_GENE_PREM, niv_pre.OID_PARA_NIVE_PREM, tip_pre.OID_TIPO_PREM, pre_des.NUM_PREM, pre_des.VAL_CANT_DESC, tip_des.oid_tipo_desc AS oid_tipo, gen_des.descripcion,
      idio_tipo_premio
                FROM INC_PARAM_NIVEL_PREMI niv_pre,
                          inc_tipo_premi tip_pre,
                          INC_PREMI_DESCU pre_des,
                          inc_tipo_descu tip_des,
                          (
                               SELECT val_oid, val_i18n AS descripcion, idio_oid_idio AS idio_tipo_premio
                               FROM v_gen_i18n_sicc
                               WHERE attr_enti = 'INC_TIPO_DESCU'
                          )gen_des
               WHERE niv_pre.tpre_oid_tipo_prem = tip_pre.oid_tipo_prem
                         AND niv_pre.OID_PARA_NIVE_PREM = pre_des.OID_PARA_NIVE_PREM
                         AND pre_des.tdsc_oid_tipo_desc = tip_des.oid_tipo_desc
                         AND tip_pre.OID_TIPO_PREM = 3
                         AND tip_des.oid_tipo_desc = gen_des.val_oid(+)
               )
               UNION
               (
               SELECT niv_pre.PAGP_OID_PARA_GENE_PREM, niv_pre.OID_PARA_NIVE_PREM, tip_pre.OID_TIPO_PREM, pre_pun.NUM_PREM,
                           CASE WHEN tip_pre_pun.OID_TIPO_PREM_PUNT = 1 THEN pre_pun.VAL_CANT
                                       WHEN tip_pre_pun.OID_TIPO_PREM_PUNT = 2 THEN pre_pun.VAL_PORC
                                       END AS cantidad, tip_pre_pun.OID_TIPO_PREM_PUNT AS oid_tipo, gen_pun.descripcion, gen_pun.idio_tipo_premio
                FROM INC_PARAM_NIVEL_PREMI niv_pre,
                          inc_tipo_premi tip_pre,
                          INC_PREMI_PUNTO pre_pun,
                          inc_tipo_premi_punto tip_pre_pun,
                          (
                               SELECT val_oid, val_i18n AS descripcion, idio_oid_idio AS idio_tipo_premio
                               FROM v_gen_i18n_sicc
                               WHERE attr_enti = 'INC_TIPO_PREMI_PUNTO'
                          )gen_pun
               WHERE niv_pre.tpre_oid_tipo_prem = tip_pre.oid_tipo_prem
                         AND niv_pre.OID_PARA_NIVE_PREM = pre_pun.PANP_OID_PARA_NIVE_PREM
                         AND pre_pun.tppu_oid_tipo_prem_punt = tip_pre_pun.oid_tipo_prem_punt
                         AND tip_pre.OID_TIPO_PREM = 4
                         AND tip_pre_pun.OID_TIPO_PREM_PUNT = gen_pun.val_oid(+)
               )
               UNION
               (
               SELECT niv_pre.PAGP_OID_PARA_GENE_PREM, niv_pre.OID_PARA_NIVE_PREM, tip_pre.OID_TIPO_PREM, pre_mon.NUM_PREM,
                           CASE WHEN tip_pre_mon.OID_TIPO_PREM_MONE = 1 THEN pre_mon.VAL_CANT
                                       WHEN tip_pre_mon.OID_TIPO_PREM_MONE = 2 THEN pre_mon.VAL_PORC
                                       END AS cantidad, tip_pre_mon.OID_TIPO_PREM_MONE AS oid_tipo,  gen_mon.descripcion, gen_mon.idio_tipo_premio
                FROM INC_PARAM_NIVEL_PREMI niv_pre,
                          INC_TIPO_PREMI tip_pre,
                          INC_PREMI_MONET pre_mon,
                          inc_tipo_premi_monet tip_pre_mon,
                          (
                               SELECT val_oid, val_i18n AS descripcion, idio_oid_idio AS idio_tipo_premio
                               FROM v_gen_i18n_sicc
                               WHERE attr_enti = 'INC_TIPO_PREMI_MONET'
                          )gen_mon
               WHERE niv_pre.tpre_oid_tipo_prem = tip_pre.oid_tipo_prem
                         AND niv_pre.OID_PARA_NIVE_PREM = pre_mon.PANP_OID_PARA_NIVE_PREM
                         AND pre_mon.tpmo_oid_tipo_prem_mone = tip_pre_mon.oid_tipo_prem_mone
                         AND tip_pre.OID_TIPO_PREM = 1
                         AND tip_pre_mon.OID_TIPO_PREM_MONE = gen_mon.val_oid(+)
               )
       )desc_premio
WHERE desc_premio.OID_TIPO_PREM = gen_pre.val_oid(+)
         AND desc_premio.idio_tipo_premio = gen_pre.idio_tipo_premio(+);

