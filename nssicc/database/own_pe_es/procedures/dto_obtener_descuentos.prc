CREATE OR REPLACE PROCEDURE "DTO_OBTENER_DESCUENTOS" (
	idperi IN NUMBER,
	idmarca IN NUMBER,
	idcanal IN NUMBER,
	idacces IN NUMBER,
	idsubacces IN NUMBER,
	idzona IN NUMBER,
	idpais IN NUMBER,
	idregion IN NUMBER,
	idsubgerencia IN NUMBER,
	idcliente IN NUMBER
)
IS
 a number;
 b number;

BEGIN

SELECT oid_desc, dto_descu.num_desc_corr  into a, b
FROM dto_descu,
     cra_perio perio_ini,
     cra_perio perio_fin,
     cra_perio perio_solicitud,
     dto_alcan_dto_admin,
     dto_descu_acces,
     dto_descu_subac,
     v_mae_tipif_clien,
     (SELECT s.dcto_oid_desc oiddesc, rel.ticl_oid_tipo_clie tipo,
             s.sbti_oid_subt_clie subtipo
        FROM dto_descu_subti_clien s, mae_subti_clien rel
       WHERE rel.oid_subt_clie = s.sbti_oid_subt_clie
      UNION
      SELECT t.dcto_oid_desc, t.ticl_oid_tipo_clie, NULL
        FROM dto_descu_tipo_clien t
       WHERE t.ticl_oid_tipo_clie NOT IN (
                SELECT DISTINCT rel.ticl_oid_tipo_clie
                           FROM dto_descu_subti_clien s,
                                mae_subti_clien rel
                          WHERE rel.oid_subt_clie = s.sbti_oid_subt_clie
                            AND s.dcto_oid_desc = t.dcto_oid_desc)) tipo_subtipo_descuento
WHERE dto_descu.ind_acti = 1
 AND dto_descu.pais_oid_pais = idpais
 AND dto_descu.marc_oid_marc = idmarca
 AND dto_descu.cana_oid_cana = idcanal
 AND perio_solicitud.oid_peri = idperi
 AND perio_ini.oid_peri = dto_descu.perd_oid_peri
 AND perio_ini.fec_inic <= perio_solicitud.fec_inic
 AND perio_fin.oid_peri(+) = dto_descu.perd_oid_peri_limi_fin
 AND (   (perio_fin.fec_fina >= perio_solicitud.fec_fina)
      OR perio_fin.fec_fina IS NULL
     )
 AND dto_descu.oid_desc = dto_alcan_dto_admin.dcto_oid_desc(+)
 AND (   dto_descu.ind_naci = 1
      OR     (   dto_alcan_dto_admin.zzon_oid_zona =  idzona
              OR dto_alcan_dto_admin.zzon_oid_zona IS NULL
             )
         AND (   dto_alcan_dto_admin.zorg_oid_regi =  idregion
              OR dto_alcan_dto_admin.zorg_oid_regi IS NULL
             )
         AND (dto_alcan_dto_admin.zsgv_oid_subg_vent = idsubgerencia
		 	 )
     )
 AND dto_descu.oid_desc = dto_descu_acces.dcto_oid_desc(+)
 AND (   dto_descu_acces.acce_oid_acce IS NULL
      OR dto_descu_acces.acce_oid_acce = idacces
     )
 AND dto_descu.oid_desc = dto_descu_subac.dcto_oid_desc(+)
 AND (   dto_descu_subac.sbac_oid_sbac IS NULL
      OR dto_descu_subac.sbac_oid_sbac = idsubacces
     )
 AND v_mae_tipif_clien.clie_oid_clie = idcliente
 AND dto_descu.oid_desc = tipo_subtipo_descuento.oiddesc
 AND tipo_subtipo_descuento.tipo = v_mae_tipif_clien.ticl_oid_tipo_clie
 AND (   tipo_subtipo_descuento.subtipo = v_mae_tipif_clien.sbti_oid_subt_clie
      OR tipo_subtipo_descuento.subtipo IS NULL );
END;
/

