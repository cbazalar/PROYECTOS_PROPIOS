CREATE OR REPLACE VIEW V_MAE_CLIE_UNIDA_ADMIN AS
SELECT c.oid_clie,
       c.cod_clie,
       c.val_ape1,
       c.val_ape2,
       c.val_nom1,
       c.val_nom2,
       TRIM(c.val_nom1) || ' ' || TRIM(c.val_nom2) || ' ' ||
       TRIM(c.val_ape1) || ' ' || TRIM(c.val_ape2) nom_clie,
       c.fec_ingr,
       c.sal_deud_ante,
       c.sal_deud_venc,
       c.cod_digi_ctrl,
       zt.oid_terr,
       zt.cod_terr,
       zs.oid_secc,
       zs.cod_secc,
       zs.des_secci,
       zz.oid_zona,
       zz.cod_zona,
       zz.des_zona,
       zr.oid_regi,
       zr.cod_regi,
       zr.des_regi,
       da.esta_oid_esta_clie,
       cua.ind_acti,
       cua.perd_oid_peri_ini,
       cua.perd_oid_peri_fin
  FROM mae_clien             c,
       mae_clien_unida_admin cua,
       mae_clien_datos_adici da,
       zon_terri_admin       zta,
       zon_terri             zt,
       zon_secci             zs,
       zon_zona              zz,
       zon_regio             zr
 WHERE zta.oid_terr_admi = cua.ztad_oid_terr_admi
   AND zs.oid_secc = zta.zscc_oid_secc
   AND zz.oid_zona = zs.zzon_oid_zona
   AND zr.oid_regi = zz.zorg_oid_regi
   AND da.clie_oid_clie = c.oid_clie
   AND c.oid_clie = cua.clie_oid_clie
   AND zta.terr_oid_terr = zt.oid_terr;
/