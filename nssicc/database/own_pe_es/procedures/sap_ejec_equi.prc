CREATE OR REPLACE PROCEDURE SAP_EJEC_EQUI
(lnOidPais   IN  NUMBER)
IS
	cursor cur_equi is
		select distinct
		       f.COD_PERI,
		       --
		       g.VAL_CODI_VENT,
           j.COD_SAP ,
			     k.COD_SAP ,
			     t.cod_tipo_ofer,
           c.VAL_CODI_VENT cuv_ori,
           u.COD_TIPO_OFER tipo_ofer_ori,
           j.OID_PROD,
           u.OID_TIPO_OFER
           /*tmp.COD_TIPO_OFER tipo_ofer_ori*/
		  from pre_matri_factu_cabec a,
  			   pre_ofert             b,
  			   pre_ofert_detal       c,
  			   pre_matri_factu       d,
  			   cra_perio             e,
  			   seg_perio_corpo       f,
  			   pre_ofert_detal       g,
  			   pre_matri_factu       h,
  			   pre_matri_codig_alter i,
  			   mae_produ             j,
  			   mae_produ             k,
  			   gen_i18n_sicc_pais    l,
  			   gen_i18n_sicc_pais    m,
  			   pre_tipo_ofert        t,
  			   pre_tipo_ofert        u,
  			   pre_prod_alter_ice    equi/*,
  			   ---------------------------
  			   TMP_VENTA_DIARI tmp*/
		 where a.OID_CABE = b.MFCA_OID_CABE
		   and b.OID_OFER = c.OFER_OID_OFER
		   and c.OID_DETA_OFER = d.OFDE_OID_DETA_OFER
		   and a.PERD_OID_PERI = e.OID_PERI
		   and i.MAFA_OID_COD_PPAL = d.OID_MATR_FACT
		   and i.MAFA_OID_COD_ALTE = h.OID_MATR_FACT
		   and h.OFDE_OID_DETA_OFER = g.OID_DETA_OFER
		   and f.OID_PERI = e.PERI_OID_PERI
		   and c.PROD_OID_PROD = j.OID_PROD
		   and j.OID_PROD = l.VAL_OID
		   and l.ATTR_ENTI = 'MAE_PRODU'
		   and g.PROD_OID_PROD = k.OID_PROD
		   and k.OID_PROD = m.VAL_OID
		   and m.ATTR_ENTI = 'MAE_PRODU'
		   and g.tofe_oid_tipo_ofer = t.oid_tipo_ofer
		   and c.tofe_oid_tipo_ofer = u.oid_tipo_ofer
		   and j.COD_SAP = equi.COD_SAP_PPAL;
		   -----------------------------
		   /*and f.COD_PERI = tmp.COD_PERI
       and g.VAL_CODI_VENT = tmp.VAL_CODI_VENT;*/

	TYPE t_cod_peri      is table of seg_perio_corpo.cod_peri%type;
	TYPE t_val_codi_vent is table of pre_ofert_detal.val_codi_vent%type;
  	TYPE t_cod_sap_ori  	 is table of mae_produ.cod_sap%type;
	TYPE t_cod_sap  	 is table of mae_produ.cod_sap%type;
	TYPE t_cod_tipo_ofer is table of pre_tipo_ofert.cod_tipo_ofer%type;
  TYPE t_val_codi_vent_ori is table of pre_ofert_detal.val_codi_vent%type;
  TYPE t_cod_tipo_ofer_ori is table of pre_tipo_ofert.cod_tipo_ofer%type;
	TYPE t_oid_prod  	 is table of mae_produ.oid_prod%type;
	TYPE t_oid_tipo_ofer is table of pre_tipo_ofert.oid_tipo_ofer%type;

	v_cod_peri     	 t_cod_peri     ;
	v_val_codi_vent	 t_val_codi_vent;
  	v_cod_sap_ori  		 t_cod_sap_ori  	;
	v_cod_sap  		 t_cod_sap  	;
	v_cod_tipo_ofer	 t_cod_tipo_ofer;
  v_val_codi_vent_ori t_val_codi_vent_ori;
  v_cod_tipo_ofer_ori t_cod_tipo_ofer_ori;
	v_oid_prod  		 t_oid_prod  	;
	v_oid_tipo_ofer	 t_oid_tipo_ofer;

	rows             NATURAL        := 1000;   -- Number of rows to process at a time
  j                BINARY_INTEGER := 0;
  v_row_count      NUMBER         := 0;
  lsPeriodoEqui    VARCHAR2(6);
BEGIN

  --Obtenemos la campaña inicial de activacion de equivalencia de productos
  select nvl(bp.COD_PERI_ACTI_EQUI,' ')
    into lsPeriodoEqui
    from BAS_PAIS bp, SEG_PAIS sp
   where sp.cod_pais = bp.cod_pais
     and sp.oid_pais = lnOidPais;

	OPEN cur_equi;
    LOOP
    -- Bulk collect data into memory table - X rows at a time
    FETCH cur_equi BULK COLLECT INTO v_cod_peri     ,
                                     v_val_codi_vent,
                                 v_cod_sap_ori,
                                     v_cod_sap      ,
                                     v_cod_tipo_ofer,
                                 v_val_codi_vent_ori,
                                 v_cod_tipo_ofer_ori,
                                 v_oid_prod,
                                 v_oid_tipo_ofer
                                LIMIT rows;

    EXIT WHEN v_row_count = cur_equi%ROWCOUNT;
    v_row_count := cur_equi%ROWCOUNT;

    FORALL j IN 1..v_cod_peri.count
      --Si no tiene periodo de Referencia, se utiliza el codigo de Periodo normal para la conversion
      UPDATE TMP_VENTA_DIARI
         SET VAL_CODI_VENT = v_val_codi_vent_ori(j),--v_val_codi_vent(j),
             COD_SAP = v_cod_sap_ori(j),--v_cod_sap(j),
             COD_TIPO_OFER = v_cod_tipo_ofer_ori(j),
             PROD_OID_PROD = v_oid_prod(j),
             TOFE_OID_TIPO_OFER = v_oid_tipo_ofer(j)
       WHERE COD_PERI = v_cod_peri(j)
         AND COD_PERI >= lsPeriodoEqui
         AND COD_PERI_REFE IS NULL
         AND VAL_CODI_VENT = v_val_codi_vent(j); --v_val_codi_vent_ori(j)

    FORALL j IN 1..v_cod_peri.count
      --Si no tiene periodo de Referencia, se utiliza el codigo de Periodo normal para la conversion
      UPDATE TMP_VENTA_DIARI
         SET VAL_CODI_VENT = v_val_codi_vent_ori(j),--v_val_codi_vent(j),
             COD_SAP = v_cod_sap_ori(j),--v_cod_sap(j),
             COD_TIPO_OFER = v_cod_tipo_ofer_ori(j),--v_cod_tipo_ofer(j)
             PROD_OID_PROD = v_oid_prod(j),
             TOFE_OID_TIPO_OFER = v_oid_tipo_ofer(j)
       WHERE COD_PERI_REFE = v_cod_peri(j)
         AND COD_PERI_REFE >= lsPeriodoEqui
         AND VAL_CODI_VENT = v_val_codi_vent(j); --v_val_codi_vent_ori(j)

    END LOOP;

  CLOSE cur_equi;

END;
/

