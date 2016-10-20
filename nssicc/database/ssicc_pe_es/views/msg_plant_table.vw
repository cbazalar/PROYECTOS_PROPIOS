CREATE OR REPLACE VIEW MSG_PLANT_TABLE AS
SELECT   oid_marc AS val_oid, des_marc AS val_i18n, 'SEG_MARCA' val_tabl,
            '' val_tabl_refe, 0 ref_oid, '0' ind_refe, cod_marc codigo
       FROM seg_marca
      WHERE oid_marc != 0
   UNION ALL
   SELECT   icanal.val_oid, icanal.val_i18n, 'SEG_CANAL' val_tabl,
            '' val_tabl_refe, 0 ref_oid, '0' ind_refe, cod_cana codigo
       FROM seg_canal c, gen_i18n_sicc_comun icanal
      WHERE icanal.attr_enti = 'SEG_CANAL'
        AND icanal.idio_oid_idio = 1
        AND icanal.val_oid = c.oid_cana
        AND icanal.attr_num_atri = 1
        AND val_oid != 0
   UNION ALL
   SELECT   oid_modu, cod_modu || '  ' || val_desc_be_colo, 'SEG_MODUL',
            '' val_tabl_refe, 0 ref_oid, '0' ind_refe, cod_modu codigo
       FROM seg_modul
   UNION ALL
   SELECT   i.val_oid, i.val_i18n, 'CRA_ACTIV', '' val_tabl_refe, 0 ref_oid,
            '0' ind_refe, cod_acti codigo
       FROM cra_activ a, gen_i18n_sicc_comun i
      WHERE i.attr_enti = 'CRA_ACTIV'
        AND i.idio_oid_idio = 1
        AND i.val_oid = a.oid_acti
        AND i.attr_num_atri = 1
   UNION ALL
   SELECT   i.val_oid, i.val_i18n, 'MAE_TIPO_CLIEN' val_tabl,
            'MAE_SUBTI_CLIEN' val_tabl_refe, 0 ref_oid, '1' ind_refe,
            cod_tipo_clie codigo
       FROM mae_tipo_clien a, gen_i18n_sicc_comun i
      WHERE i.attr_enti = 'MAE_TIPO_CLIEN'
        AND i.idio_oid_idio = 1
        AND i.val_oid = a.oid_tipo_clie
        AND i.attr_num_atri = 1
   UNION ALL
   SELECT   i.val_oid, i.val_i18n, 'MAE_SUBTI_CLIEN' val_tabl,
            'MAE_TIPO_CLASI_CLIEN' val_tabl_refe,
            a.ticl_oid_tipo_clie ref_oid, '1' ind_refe, cod_subt_clie codigo
       FROM mae_subti_clien a, gen_i18n_sicc_comun i
      WHERE i.attr_enti = 'MAE_SUBTI_CLIEN'
        AND i.idio_oid_idio = 1
        AND i.val_oid = a.oid_subt_clie
        AND i.attr_num_atri = 1
   UNION ALL
   SELECT   i.val_oid, i.val_i18n, 'MAE_TIPO_CLASI_CLIEN' val_tabl,
            'MAE_CLASI' val_tabl_refe, a.sbti_oid_subt_clie ref_oid,
            '1' ind_refe, cod_tipo_clas codigo
       FROM mae_tipo_clasi_clien a, gen_i18n_sicc_comun i
      WHERE i.attr_enti = 'MAE_TIPO_CLASI_CLIEN'
        AND i.idio_oid_idio = 1
        AND i.val_oid = a.oid_tipo_clas
        AND i.attr_num_atri = 1
   UNION ALL
   SELECT   i.val_oid, i.val_i18n, 'MAE_CLASI' val_tabl, '' val_tabl_refe,
            a.tccl_oid_tipo_clas ref_oid, '1' ind_refe, cod_clas codigo
       FROM mae_clasi a, gen_i18n_sicc_comun i
      WHERE i.attr_enti = 'MAE_CLASI'
        AND i.idio_oid_idio = 1
        AND i.val_oid = a.oid_clas
        AND i.attr_num_atri = 1
   UNION ALL
   SELECT   i.val_oid, i.val_i18n, 'MAE_PRODU' val_tabl, '' val_tabl_refe,
            0 ref_oid, '0' ind_refe, a.cod_sap codigo
       FROM mae_produ a, gen_i18n_sicc_pais i
      WHERE i.attr_enti = 'MAE_PRODU'
        AND i.idio_oid_idio = 1
        AND i.val_oid = a.oid_prod
        AND i.attr_num_atri = 1
   UNION ALL
   SELECT   y.oid_deta_ofer, y.val_text_brev, 'PRE_OFERT_DETAL' val_tabl,
            '' val_tabl_refe, 0 ref_oid, '0' ind_refe, y.val_codi_vent codigo
       FROM pre_ofert_detal y, pre_ofert x, pre_matri_factu_cabec z
      WHERE y.ofer_oid_ofer = x.oid_ofer
        AND z.oid_cabe = x.mfca_oid_cabe
        AND z.perd_oid_peri =
               (SELECT a.oid_peri
                  FROM cra_perio a, seg_perio_corpo b
                 WHERE a.marc_oid_marc =
                                  gen_pkg_gener.gen_fn_devuelve_id_marca ('T')
                   AND a.cana_oid_cana =
                                 gen_pkg_gener.gen_fn_devuelve_id_canal ('VD')
                   AND a.fec_inic <=
                          TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'),
                                   'DD/MM/YYYY'
                                  )
                   AND a.fec_fina >=
                          TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'),
                                   'DD/MM/YYYY'
                                  )
                   AND b.oid_peri = a.peri_oid_peri
                   AND ROWNUM = 1)
   UNION ALL
   SELECT   i.val_oid, i.val_i18n, 'PED_TIPO_SOLIC' val_tabl,
            '' val_tabl_refe, 0 ref_oid, '0' ind_refe, a.cod_tipo_soli codigo
       FROM ped_tipo_solic a, gen_i18n_sicc_comun i
      WHERE i.attr_enti = 'PED_TIPO_SOLIC'
        AND i.idio_oid_idio = 1
        AND i.val_oid = a.oid_tipo_soli
        AND i.attr_num_atri = 1
   UNION ALL
   SELECT   a.oid_para_gral, a.val_nomb, 'INC_CONCU_PARAM_GENER' val_tabl,
            '' val_tabl_refe, 0 ref_oid, '0' ind_refe, a.num_conc
       FROM inc_concu_param_gener a
      WHERE a.ind_acti = '1'
   UNION ALL
   SELECT   i.val_oid, i.val_i18n, 'PRE_TIPO_OFERT' val_tabl,
            '' val_tabl_refe, 0 ref_oid, '0' ind_refe, a.cod_tipo_ofer codigo
       FROM pre_tipo_ofert a, gen_i18n_sicc_comun i
      WHERE i.attr_enti = 'PRE_TIPO_OFERT'
        AND i.idio_oid_idio = 1
        AND i.val_oid = a.oid_tipo_ofer
        AND i.attr_num_atri = 1
   UNION ALL
   SELECT   a.oid_regi, a.des_regi, 'ZON_REGIO' val_tabl,
            'ZON_ZONA' val_tabl_refe, 0 ref_oid, '1' ind_refe, a.cod_regi
       FROM zon_regio a
      WHERE a.ind_acti = '1'
   UNION ALL
   SELECT   a.oid_zona, a.des_zona, 'ZON_ZONA' val_tabl, '' val_tabl_refe,
            zorg_oid_regi ref_oid, '1' ind_refe, a.cod_zona
       FROM zon_zona a
      WHERE a.ind_acti = '1'
   UNION ALL
   SELECT   a.oid_terr, TO_CHAR (a.cod_terr) val_i18n, 'ZON_TERRI' val_tabl,
            '' val_tabl_refe, 0 ref_oid, '0' ind_refe, TO_CHAR (a.cod_terr)
       FROM zon_terri a
      WHERE ind_borr = 1
   UNION ALL
   SELECT   i.val_oid, i.val_i18n, 'MAE_ESTAT_CLIEN' val_tabl,
            '' val_tabl_refe, 0 ref_oid, '0' ind_refe, a.cod_esta_clie codigo
       FROM mae_estat_clien a, gen_i18n_sicc_comun i
      WHERE i.attr_enti = 'MAE_ESTAT_CLIEN'
        AND i.idio_oid_idio = 1
        AND i.val_oid = a.oid_esta_clie
        AND i.attr_num_atri = 1
   UNION ALL
   SELECT   a.oid_mens, a.des_mens, 'MSG_MENSA' val_tabl, '' val_tabl_refe,
            0 ref_oid, '0' ind_refe, a.cod_mens
       FROM msg_mensa a
   ORDER BY 3, 1;

