CREATE OR REPLACE FUNCTION "PED_OBTENER_MATRIZ_PREC_POSIC" (
   oidsolicitud              IN   NUMBER,
   estado_posicion_anulado   IN   NUMBER
)
   RETURN obj_ped_sol_prec_posic_table PIPELINED
IS
   f_registro        obj_ped_sol_prec_posic_matriz
      := obj_ped_sol_prec_posic_matriz (NULL,
                                        NULL,
                                        NULL,
                                        NULL,
                                        NULL,
                                        NULL,
                                        NULL
                                       );
   f_oid_soli_posi   NUMBER (12);
   f_oid_ofer        NUMBER (12);
   f_imp_prec_cata   NUMBER (12, 2);
   f_imp_prec_posi   NUMBER (12, 2);
   f_oferta          NUMBER (12);
   f_venta_excl      NUMBER (12);
   f_val_fact_repe   NUMBER (2);

   CURSOR f_cur_ped_solic_cabec
   IS
      SELECT   p.oid_soli_posi, o.oid_ofer,
               DECODE
                  ((SELECT pr.val_prec_cata
                      FROM pre_detal_ofert_preci pr
                     WHERE pr.ofde_oid_deta_ofer(+) = d.oid_deta_ofer
                       AND p.num_unid_por_aten BETWEEN pr.num_unid_desd
                                                   AND pr.num_unid_hast),
                   NULL, d.imp_prec_cata,
                   (SELECT pr.val_prec_cata
                      FROM pre_detal_ofert_preci pr
                     WHERE pr.ofde_oid_deta_ofer(+) = d.oid_deta_ofer
                       AND p.num_unid_por_aten BETWEEN pr.num_unid_desd
                                                   AND pr.num_unid_hast)
                  ) imp_prec_cata,
               DECODE
                  ((SELECT pr.val_prec_posi
                      FROM pre_detal_ofert_preci pr
                     WHERE pr.ofde_oid_deta_ofer(+) = d.oid_deta_ofer
                       AND p.num_unid_por_aten BETWEEN pr.num_unid_desd
                                                   AND pr.num_unid_hast),
                   NULL, d.imp_prec_posi,
                   (SELECT pr.val_prec_posi
                      FROM pre_detal_ofert_preci pr
                     WHERE pr.ofde_oid_deta_ofer(+) = d.oid_deta_ofer
                       AND p.num_unid_por_aten BETWEEN pr.num_unid_desd
                                                   AND pr.num_unid_hast)
                  ) imp_prec_posi,
               o.oid_ofer oferta, COUNT (ve.oid_vent_excl) venta_excl,
               d.val_fact_repe
          FROM ped_solic_posic p,
               pre_ofert o,
               pre_ofert_detal d,
               pre_venta_exclu ve
         WHERE p.soca_oid_soli_cabe = oidsolicitud
           AND (   p.espo_oid_esta_posi <> estado_posicion_anulado
                OR p.espo_oid_esta_posi IS NULL
               )
           AND (   (p.val_prec_cata_unit_loca = 0)
                OR (p.val_prec_cata_unit_loca IS NULL)
               )
           AND (   (p.val_prec_cont_unit_loca = 0)
                OR (p.val_prec_cont_unit_loca IS NULL)
               )
           AND p.val_codi_vent IS NOT NULL
           AND p.ofde_oid_deta_ofer = d.oid_deta_ofer
           AND d.ofer_oid_ofer = o.oid_ofer
           AND ve.ofer_oid_ofer(+) = o.oid_ofer
      GROUP BY p.oid_soli_posi,
               oid_deta_ofer,
               o.oid_ofer,
               imp_prec_cata,
               imp_prec_posi,
               d.val_fact_repe,
               p.num_unid_por_aten;
BEGIN
   OPEN f_cur_ped_solic_cabec;

   LOOP
      FETCH f_cur_ped_solic_cabec
       INTO f_oid_soli_posi, f_oid_ofer, f_imp_prec_cata, f_imp_prec_posi,
            f_oferta, f_venta_excl, f_val_fact_repe;

      f_registro.oid_soli_posi := f_oid_soli_posi;
      f_registro.oid_ofer := f_oid_ofer;
      f_registro.imp_prec_cata := f_imp_prec_cata;
      f_registro.imp_prec_posi := f_imp_prec_posi;
      f_registro.oferta := f_oferta;
      f_registro.venta_excl := f_venta_excl;
      f_registro.val_fact_repe := f_val_fact_repe;
      EXIT WHEN f_cur_ped_solic_cabec%NOTFOUND;
      PIPE ROW (f_registro);
   END LOOP;

   CLOSE f_cur_ped_solic_cabec;

   RETURN;
END;
/

