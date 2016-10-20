CREATE OR REPLACE TRIGGER OWN_PE_ES."CLIE_BU_TR" BEFORE
 UPDATE ON "MAE_CLIEN" FOR EACH ROW
BEGIN

INSERT INTO mae_clien_histo
  (oid_clie,
   cod_clie,
   ind_fich_insc,
   pais_oid_pais,
   cod_digi_ctrl,
   val_ape1,
   val_ape2,
   val_nom1,
   val_nom2,
   val_trat,
   val_crit_bus1,
   val_crit_bus2,
   cod_sexo,
   fec_ingr,
   fopa_oid_form_pago,
   val_apel_casa,
   fec_crea,
   fec_ulti_actu,
   fec_ulti_gene_cupo,
   sal_deud_ante,
   cod_clie_ante,
   fec_rein,
   ind_prol,
   usu_modi,
   ind_orig_regi,
   sal_deud_venc,
   usu_crea,
   val_recl_pend,
   fec_proc,
   usu_BBDD)
VALUES
  (:old.oid_clie,
   :old.cod_clie,
   :old.ind_fich_insc,
   :old.pais_oid_pais,
   :old.cod_digi_ctrl,
   :old.val_ape1,
   :old.val_ape2,
   :old.val_nom1,
   :old.val_nom2,
   :old.val_trat,
   :old.val_crit_bus1,
   :old.val_crit_bus2,
   :old.cod_sexo,
   :old.fec_ingr,
   :old.fopa_oid_form_pago,
   :old.val_apel_casa,
   :old.fec_crea,
   :old.fec_ulti_actu,
   :old.fec_ulti_gene_cupo,
   :old.sal_deud_ante,
   :old.cod_clie_ante,
   :old.fec_rein,
   :old.ind_prol,
   :old.usu_modi,
   :old.ind_orig_regi,
   :old.sal_deud_venc,
   :old.usu_crea,
   :old.val_recl_pend,
   SYSDATE,
   USER);

END;
/