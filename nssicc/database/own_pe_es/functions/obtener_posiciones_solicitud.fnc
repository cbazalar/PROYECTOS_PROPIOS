CREATE OR REPLACE FUNCTION "OBTENER_POSICIONES_SOLICITUD" (oid_solicitud_param IN NUMBER)
RETURN obj_ped_sposic_cons_table PIPELINED IS
f_registro obj_ped_solic_posic_consulta := obj_ped_solic_posic_consulta(null,null,null,null,null,null,null,null,null,null,
                                                                                                                              null,null,null,null,null,null,null,null,null,null,
                                                                                                                        null,null,null,null);

        f_oid_posiciones NUMBER(12);
        f_val_codi_venta VARCHAR2(18);
        f_num_unid_dema NUMBER(6);
        f_num_unid_aten NUMBER(6);
        f_num_unid_por_aten NUMBER(6);
        f_num_unid_dema_real NUMBER(6);
        f_oid_deta_ofer NUMBER(12);
        f_oid_tipo_posi NUMBER(12);
        f_oid_subt_posi NUMBER(12);
        f_oid_ofer NUMBER(12);
        f_num_ofer NUMBER(12);
        f_num_grup NUMBER(12);
        f_oid_tipo_estr NUMBER(12);
        f_oid_indi_cuad NUMBER(12);
        f_cod_fact_cuad NUMBER(12);
        f_ind_cndt NUMBER(1);
        f_num_posi_rank NUMBER(12);
        f_num_pagi_cata NUMBER(3);
        f_val_prec_cata_unit_loca NUMBER(12,2);
        f_cod_posi NUMBER(10) ;
        f_val_fact_repe NUMBER(12);
        f_oid_prod NUMBER(12);
        f_oid_line_prod NUMBER(12);
        f_oid_catal NUMBER(12);

        CURSOR f_cur_ped_solic_cabec IS
                SELECT pos.oid_soli_posi, pos.val_codi_vent, pos.num_unid_dema,
                       pos.num_unid_aten, pos.num_unid_por_aten, pos.num_unid_dema_real,
                       pos.ofde_oid_deta_ofer, pos.tpos_oid_tipo_posi, pos.stpo_oid_subt_posi,
                       det.ofer_oid_ofer, ofer.num_ofer, grup.num_grup,
                       est.ties_oid_tipo_estr, indcuadtip.indc_oid_indi_cuad,
                       grup.cod_fact_cuad, grup.ind_cndt, det.num_posi_rank,
                       det.num_pagi_cata, pos.val_prec_cata_unit_loca, pos.cod_posi,
                       det.val_fact_repe, det.prod_oid_prod, prod.lipr_oid_line_prod, det.OCAT_OID_CATAL
              FROM ped_solic_posic pos,
                 pre_ofert_detal det,
                         pre_ofert ofer,
                         pre_grupo_ofert grup,
                         pre_estra est,
                         pre_indic_cuadr_tipo_estra indcuadtip,
                         mae_produ prod
                WHERE soca_oid_soli_cabe = oid_solicitud_param
                  AND pos.ofde_oid_deta_ofer = det.oid_deta_ofer
                  AND det.ofer_oid_ofer = ofer.oid_ofer
                  AND det.gofe_oid_grup_ofer = grup.oid_grup_ofer(+)
                  AND ofer.coes_oid_estr = est.oid_estr
                  AND grup.cues_oid_ind_cuad_tipo_estr = indcuadtip.oid_ind_cuad_tipo_estr(+)
                  AND prod.oid_prod = det.prod_oid_prod
			AND pos.ESPO_OID_ESTA_POSI <> (select oid_esta_posi from ped_estad_posic where cod_esta_posi = 'AN');

        BEGIN
           OPEN f_cur_ped_solic_cabec;
          LOOP
                fetch f_cur_ped_solic_cabec
                  into f_oid_posiciones, f_val_codi_venta, f_num_unid_dema,        f_num_unid_aten,
                             f_num_unid_por_aten, f_num_unid_dema_real, f_oid_deta_ofer, f_oid_tipo_posi,
                           f_oid_subt_posi, f_oid_ofer, f_num_ofer, f_num_grup, f_oid_tipo_estr,
                           f_oid_indi_cuad, f_cod_fact_cuad, f_ind_cndt, f_num_posi_rank, f_num_pagi_cata,
                           f_val_prec_cata_unit_loca, f_cod_posi, f_val_fact_repe, f_oid_prod,
                           f_oid_line_prod,        f_oid_catal;

                           f_registro.oid_posiciones := f_oid_posiciones;
                           f_registro.val_codi_venta := f_val_codi_venta;
                           f_registro.num_unid_dema := f_num_unid_dema;
                           f_registro.num_unid_aten := f_num_unid_aten;
                           f_registro.num_unid_por_aten := f_num_unid_por_aten;
                           f_registro.num_unid_dema_real := f_num_unid_dema_real;
                           f_registro.oid_deta_ofer := f_oid_deta_ofer;
                           f_registro.oid_tipo_posi := f_oid_tipo_posi;
                           f_registro.oid_subt_posi := f_oid_subt_posi;
                           f_registro.oid_ofer := f_oid_ofer;
                           f_registro.num_ofer := f_num_ofer;
                           f_registro.num_grup := f_num_grup;
                           f_registro.oid_tipo_estr := f_oid_tipo_estr;
                           f_registro.oid_indi_cuad := f_oid_indi_cuad;
                           f_registro.cod_fact_cuad := f_cod_fact_cuad;
                           f_registro.ind_cndt := f_ind_cndt;
                           f_registro.num_posi_rank := f_num_posi_rank;
                           f_registro.num_pagi_cata := f_num_pagi_cata;
                           f_registro.val_prec_cata_unit_loca := f_val_prec_cata_unit_loca;
                           f_registro.cod_posi := f_cod_posi;
                           f_registro.val_fact_repe := f_val_fact_repe;
                           f_registro.oid_prod := f_oid_prod;
                           f_registro.oid_line_prod := f_oid_line_prod;
                           f_registro.oid_catal := f_oid_catal;

                 EXIT WHEN f_cur_ped_solic_cabec%NOTFOUND;
     PIPE ROW(f_registro);
          END LOOP;
         CLOSE f_cur_ped_solic_cabec;
    return;
END;
/

