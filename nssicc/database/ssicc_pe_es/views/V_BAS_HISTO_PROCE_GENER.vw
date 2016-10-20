CREATE OR REPLACE VIEW V_BAS_HISTO_PROCE_GENER AS
SELECT 'SiCC' nom_cate,
               'Batch' nom_scat,
               cod_proc des_proc,
               cod_proc,
               fec_enco,
               fec_inic fec_ipro,
               fec_fin fec_fpro,
               val_serv,
               val_mens_fina,
               val_usua,
               h1.prbt_cod_proc_padr cod_proc_padr
          FROM gen_proce_batch_histo h1
        UNION ALL
        SELECT 'SiCC' nom_cate,
               'Batch' nom_scat,
               cod_proc des_proc,
               cod_proc,
               fec_enco,
               fec_inic fec_ipro,
               fec_fin fec_fpro,
               val_serv,
               val_mens_fina,
               val_usua,
               h2.prbt_cod_proc_padr cod_proc_padr
          FROM gen_proce_batch h2
        UNION ALL
        SELECT 'SSiCC' nom_cate,
               'Batch' nom_scat,
               h3.sist_cod_sist || '-' || h3.prba_cod_proc_batc || ' ' ||
               des_proc_batc des_proc,
               h3.num_lote cod_proc,
               NULL fec_enco,
               h3.fec_inic_proc fec_ipro,
               h3.fec_fina_proc fec_fpro,
               NULL val_serv,
               h3.des_log val_mens_fina,
               h3.usu_proc val_usua,
               NULL cod_proc_padr
          FROM bas_proce_batch_histo h3,
               bas_proce_batch       p
         WHERE p.sist_cod_sist = h3.sist_cod_sist
           AND p.cod_proc_batc = h3.prba_cod_proc_batc
        UNION ALL
        SELECT 'SSiCC' nom_cate,
               'Batch' nom_scat,
               h3.sist_cod_sist || '-' || h3.prba_cod_proc_batc || ' ' ||
               des_proc_batc des_proc,
               h3.num_lote cod_proc,
               NULL fec_enco,
               h3.fec_inic_proc fec_ipro,
               h3.fec_fina_proc fec_fpro,
               NULL val_serv,
               h3.des_log val_mens_fina,
               h3.usu_proc val_usua,
               NULL cod_proc_padr
          FROM bas_proce_batch_actua h3,
               bas_proce_batch       p
         WHERE p.sist_cod_sist = h3.sist_cod_sist
           AND p.cod_proc_batc = h3.prba_cod_proc_batc
        UNION ALL
        SELECT 'SSiCC' nom_cate,
               'Interfaz' nom_scat,
               h4.inte_cod_inte || ' ' || des_inte des_proc,
               num_lote cod_proc,
               NULL fec_enco,
               h4.fec_ipro,
               h4.fec_fpro,
               NULL val_serv,
               h4.des_obse val_mens_fina,
               h4.usu_proc val_usua,
               h4.inpa_cod_inte cod_proc_padr
          FROM bas_histo_lotes h4,
               bas_inter
         WHERE cod_inte = h4.inte_cod_inte
        UNION ALL
        SELECT 'SSiCC' nom_cate,
               'STO' nom_scat,
               h5.docu_cod_tipo_docu  || '_' || cod_acci || ' ' || des_tipo_docu des_proc,
               proc_num_proc cod_proc,
               NULL fec_enco,
               h5.fec_ipro,
               h5.fec_fpro,
               NULL val_serv,
               h5.log_proc val_mens_fina,
               h5.usu_proc val_usua,
               h5.num_proc_padr cod_proc_padr
          FROM sto_histo_proce h5,
               sto_tipo_docum_digit
         WHERE cod_tipo_docu = h5.docu_cod_tipo_docu;
/