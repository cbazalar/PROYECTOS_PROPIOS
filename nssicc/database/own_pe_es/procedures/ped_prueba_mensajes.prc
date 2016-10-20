CREATE OR REPLACE PROCEDURE "PED_PRUEBA_MENSAJES" (
   idsoliccabecera   IN       NUMBER,
   idpais            IN       NUMBER,
   idsubacceso       IN       NUMBER,
   idacceso          IN       NUMBER,
   idcanal           IN       NUMBER,
   codigoventa       IN       VARCHAR,
   salida_text       OUT      VARCHAR2
)
IS
   CURSOR salida_cur
   IS
      SELECT ped_solic_cabec.val_nume_soli, rec_cabec_recla.num_aten,
             rec_opera_recla.num_secu_oper,
             rec_linea_opera_recla.num_unid_recl, rec_opera.cod_oper,
             rec_motiv_recha_desbl.cod_rech_desb,
             rec_estad_opera.cod_esta_oper, rec_preci.cod_prec,
             msg_mensa.cod_mens, rec_opera.cod_oper,
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
       WHERE ped_solic_cabec.oid_soli_cabe = idsoliccabecera
         AND rec_solic_opera.opre_oid_oper_recl =
                                                 rec_opera_recla.oid_oper_recl
         AND ped_solic_cabec.oid_soli_cabe =
                                            rec_solic_opera.soca_oid_soli_cabe
         AND ped_solic_cabec.pais_oid_pais = idpais
         AND ped_solic_cabec.sbac_oid_sbac = idsubacceso
         AND ped_solic_cabec.sbac_oid_sbac = seg_subac.oid_sbac
         AND seg_subac.acce_oid_acce = seg_acces.oid_acce
         AND seg_acces.oid_acce = idacceso
         AND seg_acces.cana_oid_cana = idcanal
         AND seg_acces.cana_oid_cana = seg_canal.oid_cana
         AND rec_cabec_recla.oid_cabe_recl =
                                            rec_opera_recla.care_oid_cabe_recl
         AND rec_solic_opera.tspa_oid_tipo_soli_pais =
                                 rec_linea_opera_recla.tspa_oid_tipo_soli_pais
         AND rec_opera_recla.oid_oper_recl =
                                      rec_linea_opera_recla.opre_oid_oper_recl
         AND rec_linea_opera_recla.mafa_oid_matr_fact =
                                                 pre_matri_factu.oid_matr_fact
         AND pre_matri_factu.ofde_oid_deta_ofer =
                                                 pre_ofert_detal.oid_deta_ofer
         AND pre_ofert_detal.val_codi_vent IN (codigoventa)
         AND rec_opera_recla.tiop_oid_tipo_oper =
                                                 rec_tipos_opera.oid_tipo_oper
         AND rec_tipos_opera.rope_oid_oper = rec_opera.oid_oper
         AND rec_opera.mens_oid_mens = msg_mensa.oid_mens
         AND rec_opera.peci_oid_peci = rec_preci.oid_prec
         AND rec_opera_recla.mrdb_oid_moti_rech_desb = rec_motiv_recha_desbl.oid_moti_rech_desb(+)
         AND rec_opera_recla.esop_oid_esta_oper =
                                                 rec_estad_opera.oid_esta_oper;

   campo1    NUMBER;
   campo2    NUMBER;
   campo3    NUMBER;
   campo4    NUMBER;
   campo5    VARCHAR (2);
   campo6    VARCHAR (2);
   campo7    VARCHAR (1);
   campo8    VARCHAR (1);
   campo9    VARCHAR (5);
   campo10   VARCHAR (2);
   campo11   VARCHAR (18);
   campo12   NUMBER;
BEGIN
   salida_text := '|';

   OPEN salida_cur;

   LOOP
      FETCH salida_cur
       INTO campo1, campo2, campo3, campo4, campo5, campo6, campo7, campo8,
            campo9, campo10, campo11, campo12;

      EXIT WHEN salida_cur%NOTFOUND;
      salida_text :=
            salida_text
         || NVL (TO_CHAR (campo1), 'null')
         || ','
         || NVL (TO_CHAR (campo2), 'null')
         || ','
         || NVL (TO_CHAR (campo3), 'null')
         || ','
         || NVL (TO_CHAR (campo4), 'null')
         || ','
         || NVL (TO_CHAR (campo5), 'null')
         || ','
         || NVL (TO_CHAR (campo6), 'null')
         || ','
         || NVL (TO_CHAR (campo7), 'null')
         || ','
         || NVL (TO_CHAR (campo8), 'null')
         || ','
         || NVL (TO_CHAR (campo9), 'null')
         || ','
         || NVL (TO_CHAR (campo10), 'null')
         || ','
         || NVL (TO_CHAR (campo11), 'null')
         || ','
         || NVL (TO_CHAR (campo12), 'null')
         || '|';
   END LOOP;

   CLOSE salida_cur;
END;
/

