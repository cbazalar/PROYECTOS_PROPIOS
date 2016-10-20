CREATE OR REPLACE FUNCTION "PED_OBT_DATOS_MENS_TABLA" (
   IDSOLICCABECERA IN NUMBER,
   IDPAIS IN NUMBER,
   IDSUBACCESO IN NUMBER,
   IDACCESO IN NUMBER,
   IDCANAL IN NUMBER,
   IDIDIOMA IN NUMBER)
RETURN OBJ_PED_OBT_DATOS_MEN_TABLE PIPELINED IS
f_registro OBJ_PED_OBTENER_DATOS_MENSAJE := OBJ_PED_OBTENER_DATOS_MENSAJE(null,null,null,null,null,null,
                        null,null,null,null,null,null);

 F_VAL_NUME_SOLI NUMBER(10);
 F_NUM_ATEN NUMBER(10);
 F_NUM_SECU_OPER NUMBER(3);
 F_NUM_UNID_RECL NUMBER(4);
 F_COD_OPER VARCHAR2(32767);
 F_COD_RECH_DESB VARCHAR2(32767);
 F_COD_ESTA_OPER VARCHAR2(32767);
 F_COD_PREC VARCHAR2(32767);
 F_COD_MENS VARCHAR2(32767);
 F_DESCRIP VARCHAR2(32767);
 F_VAL_CODI_VENT VARCHAR2(32767);
 F_VAL_INFO_EBEL_NOTI NUMBER(1);

  CURSOR f_cur_ped_solic_cabec IS
  SELECT ped_solic_cabec.val_nume_soli,
   rec_cabec_recla.num_aten,
        rec_opera_recla.num_secu_oper,
        rec_linea_opera_recla.num_unid_recl,
  rec_opera.cod_oper,
        rec_motiv_recha_desbl.cod_rech_desb,
        rec_estad_opera.cod_esta_oper, rec_preci.cod_prec,
        msg_mensa.cod_mens,
        pq_apl_aux.valor_gen_i18n_sicc (IDIDIOMA,rec_opera.oid_oper,'REC_OPERA') descrip,
        pre_ofert_detal.val_codi_vent,
        rec_tipos_opera.val_info_ebel_noti
   FROM ped_solic_cabec,
        rec_solic_opera,
        seg_subac,
        seg_acces,
        seg_canal,
        rec_cabec_recla,
        rec_opera_recla,
        rec_linea_opera_recla,
        pre_matri_factu,
        pre_ofert_detal,
        rec_tipos_opera,
        rec_opera,
        msg_mensa,
        rec_preci,
        rec_motiv_recha_desbl,
        rec_estad_opera
   WHERE ped_solic_cabec.oid_soli_cabe = IDSOLICCABECERA
     AND rec_solic_opera.opre_oid_oper_recl = rec_opera_recla.oid_oper_recl
     AND ped_solic_cabec.oid_soli_cabe = rec_solic_opera.soca_oid_soli_cabe
     AND ped_solic_cabec.pais_oid_pais = IDPAIS
     AND ped_solic_cabec.sbac_oid_sbac = IDSUBACCESO
     AND ped_solic_cabec.sbac_oid_sbac = seg_subac.oid_sbac
     AND seg_subac.acce_oid_acce = seg_acces.oid_acce
     AND seg_acces.oid_acce = IDACCESO
     AND seg_acces.cana_oid_cana = IDCANAL
     AND seg_acces.cana_oid_cana = seg_canal.oid_cana
     AND rec_cabec_recla.oid_cabe_recl = rec_opera_recla.care_oid_cabe_recl
     AND rec_solic_opera.tspa_oid_tipo_soli_pais = rec_linea_opera_recla.tspa_oid_tipo_soli_pais
     AND rec_opera_recla.oid_oper_recl = rec_linea_opera_recla.opre_oid_oper_recl
     AND rec_linea_opera_recla.mafa_oid_matr_fact = pre_matri_factu.oid_matr_fact
     AND pre_matri_factu.ofde_oid_deta_ofer = pre_ofert_detal.oid_deta_ofer
     AND pre_ofert_detal.val_codi_vent IN (SELECT VAL_CODI_VENT FROM PED_SOLIC_POSIC WHERE SOCA_OID_SOLI_CABE = IDSOLICCABECERA AND VAL_CODI_VENT IS NOT NULL)
     AND rec_opera_recla.tiop_oid_tipo_oper = rec_tipos_opera.oid_tipo_oper
     AND rec_tipos_opera.rope_oid_oper = rec_opera.oid_oper
     AND rec_opera.mens_oid_mens = msg_mensa.oid_mens
     AND rec_opera.peci_oid_peci = rec_preci.oid_prec
     AND rec_opera_recla.mrdb_oid_moti_rech_desb = rec_motiv_recha_desbl.oid_moti_rech_desb(+)
     AND rec_opera_recla.esop_oid_esta_oper = rec_estad_opera.oid_esta_oper;

   BEGIN
     OPEN f_cur_ped_solic_cabec;
       LOOP
          fetch f_cur_ped_solic_cabec
             into F_VAL_NUME_SOLI, F_NUM_ATEN, F_NUM_SECU_OPER, F_NUM_UNID_RECL, F_COD_OPER,
        F_COD_RECH_DESB, F_COD_ESTA_OPER, F_COD_PREC, F_COD_MENS, F_DESCRIP,
      F_VAL_CODI_VENT, F_VAL_INFO_EBEL_NOTI;


                  F_REGISTRO.VAL_NUME_SOLI := F_VAL_NUME_SOLI;
      F_REGISTRO.NUM_ATEN := F_NUM_ATEN;
      F_REGISTRO.NUM_SECU_OPER := F_NUM_SECU_OPER;
      F_REGISTRO.NUM_UNID_RECL := F_NUM_UNID_RECL;
      F_REGISTRO.COD_OPER := F_COD_OPER;
        F_REGISTRO.COD_RECH_DESB := F_COD_RECH_DESB;
      F_REGISTRO.COD_ESTA_OPER := F_COD_ESTA_OPER;
      F_REGISTRO.COD_PREC := F_COD_PREC;
      F_REGISTRO.COD_MENS := F_COD_MENS;
      F_REGISTRO.DESCRIP := F_DESCRIP;
      F_REGISTRO.VAL_CODI_VENT := F_VAL_CODI_VENT;
      F_REGISTRO.VAL_INFO_EBEL_NOTI := F_VAL_INFO_EBEL_NOTI;

                  

                 EXIT WHEN f_cur_ped_solic_cabec%NOTFOUND;
				 PIPE ROW(f_registro);
          END LOOP;
         CLOSE f_cur_ped_solic_cabec;
    return;
END;
/

