CREATE OR REPLACE PACKAGE "PQ_PED_SOLIC" as

	   FUNCTION ObtenerDetalleSolicitud(oid_solicitud_param IN NUMBER, oid_idioma_param IN NUMBER) RETURN obj_ped_solic_consulta_table PIPELINED;

end pq_ped_solic;
/

CREATE OR REPLACE PACKAGE BODY "PQ_PED_SOLIC" as

FUNCTION ObtenerDetalleSolicitud (oid_solicitud_param IN NUMBER, oid_idioma_param IN
NUMBER) RETURN obj_ped_solic_consulta_table PIPELINED IS

f_registro obj_ped_solic_consulta :=
obj_ped_solic_consulta(null,null,null,null,null,null,null,null,null,null,
			   			  						 						   null,null,null,null,null,null,null,null,null,null,
																		   null,null,null,null,null,null,null,null,null,null,
																		   null,null,null,null,null,null,null,null,null,null,
																		   null,null,null,null,null,null,null,null,null,null,
																		   null,null,null,null,null,null,null,null,null,null,
																		   null,null,null,null,null,null,null,null,null,null,
																		   null,null,null,null,null,null,null,null);

			   f_oid_cabecera NUMBER(12);
			   f_tipo_solicitud VARCHAR2(4000);
		       f_subacceso VARCHAR2(4000);
			   f_numero_solicitud NUMBER(10);
		       f_marca VARCHAR2(30);
			   f_canal VARCHAR2(4000);
			   f_periodo VARCHAR2(40);
		       f_acceso VARCHAR2(4000);
			   f_acceso_fisico NUMBER(12);
		       f_tipo_despacho NUMBER(12);
			   f_sociedad_ventas VARCHAR2(40);
		       f_oid_cliente NUMBER(12);
			   f_codigo_cliente VARCHAR2(15);
		       f_nombre1 VARCHAR2(25);
			   f_apellido1 VARCHAR2(25);
			   f_apellido2 VARCHAR2(25);
		       f_tipo_cliente VARCHAR2(4000);
			   f_subtipo_cliente VARCHAR2(4000);
		       f_subgerencia_ventas VARCHAR2(40);
			   f_region VARCHAR2(40) ;

		       f_zona VARCHAR2(40);
			   f_seccion VARCHAR2(40);
			   f_territorio VARCHAR2(6);
		       f_ubigeo VARCHAR2(4000);
		       f_oid_consultora_asociada NUMBER(12);
			   f_consultora_asociada VARCHAR2(15);
		       f_oid_receptor_factura NUMBER(12);
			   f_receptor_factura VARCHAR2(15);
		       f_oid_pagador_factura NUMBER(12);
			   f_pagador_factura VARCHAR2(15);
		       f_numero_clientes NUMBER(5);
			   f_tipo_direccion NUMBER(12);
			   f_oid_clie_dire NUMBER(12);
		       f_tipo_doc_identidad NUMBER(12);
		       f_tipo_doc_legal VARCHAR2(20);
			   f_numero_doc_legal NUMBER(10);
		       f_indicador_impuesto VARCHAR2(3);
			   f_forma_pago NUMBER(12);
		       f_moneda VARCHAR2(4000);
			   f_mone_oid_mone NUMBER(12);
			   f_monto1 NUMBER(12,2);

			   f_monto2 NUMBER(12,2);
		       f_monto3 NUMBER(12,2);
			   f_flete NUMBER(12,2);
		       f_importe_impuesto NUMBER(12,2);
		       f_total_pagar NUMBER(12,2);
			   f_monto1_2 NUMBER(12,2);
		       f_monto2_2 NUMBER(12,2);
			   f_monto3_2 NUMBER(12,2);
		       f_flete_1 NUMBER(12,2);
		       f_importe_impuesto_1 NUMBER(12,2);
		       f_total_pagar_1 NUMBER(12,2);
			   f_fecha_prevista DATE;
		       f_fecha_fact DATE;
			   f_codigo_estado VARCHAR(2);
		       f_grupo_proceso VARCHAR(3);
			   f_proceso VARCHAR(4);
		       f_estado VARCHAR(4000);
			   f_oid_tipo_solicitud NUMBER(12);
		       f_oid_proceso NUMBER(12);
			   f_oid_sbac NUMBER(12);

		       f_oid_marc NUMBER(12);
			   f_oid_cana NUMBER(12);
		       f_oid_peri NUMBER(12);
			   f_oid_acce NUMBER(12);
		       f_oid_tipo_clie NUMBER(12);
		       f_oid_subt_clie NUMBER(12);
		       f_oid_subg_vent NUMBER(12);
			   f_oid_regi NUMBER(12);
		       f_oid_zona NUMBER(12);
			   f_oid_secc NUMBER(12);
		       f_oid_terr NUMBER(12);
			   f_oid_tipo_docu NUMBER(12);
		       f_oid_mone NUMBER(12);
			   f_oid_esta_solic NUMBER(12);
		       f_oid_grup_proc NUMBER(12);
		       f_numero_consolidado NUMBER(10);
		       --Incidencia BELC300023759
		       f_descripcionestado VARCHAR(4000);

			   cursor  f_cur_ped_solic_cabec IS
					SELECT
					sc.oid_soli_cabe oid_cabecera,
pq_apl_aux.Valor_Gen_I18n_Sicc(oid_idioma_param, sc.tspa_oid_tipo_soli_pais,
'PED_TIPO_SOLIC') tipo_solicitud,
pq_apl_aux.Valor_Gen_I18n_Sicc(oid_idioma_param, sc.sbac_oid_sbac, 'SEG_SUBAC')
subacceso,
						sc.val_nume_soli numero_solicitud,
					    m.des_marc marca,
pq_apl_aux.Valor_Gen_I18n_Sicc(oid_idioma_param, p.cana_oid_cana , 'SEG_CANAL') canal,
						p.val_nomb_peri periodo,
pq_apl_aux.Valor_Gen_I18n_Sicc(oid_idioma_param, sb.acce_oid_acce, 'SEG_ACCES') acceso,
						sc.acfi_oid_acce_fisi acceso_fisico,
					    sc.tids_oid_tipo_desp tipo_despacho, sv.val_deno sociedad_ventas,
					    cl.oid_clie oid_cliente, cl.cod_clie codigo_cliente,
					    cl.val_nom1 nombre1, cl.val_ape1 apellido1, cl.val_ape2 apellido2,
pq_apl_aux.Valor_Gen_I18n_Sicc(oid_idioma_param, clts.ticl_oid_tipo_clie, 'MAE_TIPO_CLIEN')
tipo_cliente,
pq_apl_aux.Valor_Gen_I18n_Sicc(oid_idioma_param, clts.sbti_oid_subt_clie,
'MAE_SUBTI_CLIEN') subtipo_cliente,
						tr.cod_terr territorio,
					       veg.orde_1
					    || veg.orde_2
					    || veg.orde_3
					    || veg.orde_4
					    || veg.orde_5
					    || veg.orde_6
					    || veg.orde_7
					    || veg.orde_8
					    || veg.orde_9 ubigeo,
					    ca.oid_clie oid_consultora_asociada, ca.cod_clie consultora_asociada,
					    rf.oid_clie oid_receptor_factura, rf.cod_clie receptor_factura,
					    pf.oid_clie oid_pagador_factura, pf.cod_clie pagador_factura,
					    sc.num_clien numero_clientes, cld.tidc_oid_tipo_dire tipo_direccion,
					    cld.oid_clie_dire oid_clien_direc, sc.tdoc_oid_tipo_docu tipo_doc_identidad,
					    tdl.des_tipo_docu tipo_doc_legal, cdc.num_docu_lega numero_doc_legal,
					    ti.val_indi_impu indicador_impuesto, sc.fopa_oid_form_pago forma_pago,
pq_apl_aux.Valor_Gen_I18n_Sicc(oid_idioma_param, sc.mone_oid_mone, 'SEG_MONED')
descrip_moneda
					    , sc.mone_oid_mone,
						sc.val_impo_desc_1_tota_docu monto1,
						sc.val_prec_cont_tota_docu monto2,
						sc.val_impo_desc_3_tota_docu monto3,
						sc.val_impo_flet_tota_docu flete,
						sc.val_impo_impu_tota_docu importe_impuesto,
						sc.val_tota_paga_docu total_pagar,
						sc.val_impo_desc_1_tota_loca monto1,
						sc.val_prec_cont_tota_loca monto2,
						sc.val_impo_desc_3_tota_loca monto3,
						sc.val_impo_flet_tota_loca flete,
						sc.val_impo_impu_tota_loca importe_impuesto,
						sc.val_tota_paga_loca total_pagar,
						sc.fec_prog_fact fecha_prevista,
						sc.fec_fact fecha_fact,
						ep.cod_esta codigo_estado,
						gp.cod_grup_proc grupo_proceso, pro.cod_proc proceso,
pq_apl_aux.Valor_Gen_I18n_Sicc(oid_idioma_param, sc.esso_oid_esta_soli,
'PED_ESTAD_SOLIC') estado,
						sc.tspa_oid_tipo_soli_pais oid_tipo_solicitud,
						sc.proc_oid_proc oid_proceso, sc.sbac_oid_sbac oid_sbac,
						p.marc_oid_marc oid_marc, p.cana_oid_cana oid_cana,
						sc.perd_oid_peri oid_peri, sb.acce_oid_acce oid_acce,
						clts.ticl_oid_tipo_clie oid_tipo_clie,
						clts.sbti_oid_subt_clie oid_subt_clie,
						sc.ztad_oid_terr_admi oid_terr, sc.tido_oid_tipo_docu oid_tipo_docu,
						sc.mone_oid_mone oid_mone, sc.esso_oid_esta_soli oid_esta_solic,
						sc.grpr_oid_grup_proc oid_grup_proc,
						con.val_nume_soli numero_consolidado,
pq_apl_aux.Valor_Gen_I18n_Sicc(oid_idioma_param, sc.espe_oid_esta_pedi,
'CAR_ESTAT_PEDID') descripcionestado
					FROM
						ped_solic_cabec sc,
						seg_marca m,
						cra_perio p,
						seg_subac sb,
						seg_socie sv,
						mae_clien cl,
						mae_clien_tipo_subti clts,
						zon_terri tr,
						zon_valor_estru_geopo veg,
						mae_clien ca,
						mae_clien rf,
						mae_clien pf,
						mae_clien_direc cld,
						fac_docum_conta_cabec cdc,
						mae_tipo_docum td,
						fac_tipo_docum tdl,
						ped_tasa_impue ti,
						ped_grupo_proce gp,
						ped_proce pro,
						car_estat_pedid ep,
						ped_solic_cabec con
					WHERE
						sc.oid_soli_cabe = oid_solicitud_param
						AND p.oid_peri = sc.perd_oid_peri
						AND p.marc_oid_marc = m.oid_marc
						AND p.oid_peri = sc.perd_oid_peri
						AND p.oid_peri = sc.perd_oid_peri
						AND sb.oid_sbac = sc.sbac_oid_sbac
						AND sc.soci_oid_soci = sv.oid_soci
						AND sc.clie_oid_clie = cl.oid_clie
						AND clts.clie_oid_clie = cl.oid_clie
						AND tr.oid_terr = sc.terr_oid_terr
						AND veg.oid_valo_estr_geop = sc.vepo_oid_valo_estr_geop
						AND ca.oid_clie(+) = sc.clie_oid_cons_asoc
						AND rf.oid_clie = sc.clie_oid_clie_rece_fact
						AND pf.oid_clie = sc.clie_oid_clie_paga
						AND cld.clie_oid_clie = sc.clie_oid_clie
						AND tdl.oid_tipo_docu(+) = sc.tido_oid_tipo_docu
						AND td.oid_tipo_docu = sc.tdoc_oid_tipo_docu
						AND cdc.soca_oid_soli_cabe(+) = sc.oid_soli_cabe
						AND sc.taim_oid_tasa_impu = ti.oid_tasa_impu(+)
						AND sc.grpr_oid_grup_proc = gp.oid_grup_proc
						AND sc.proc_oid_proc = pro.oid_proc
						AND sc.espe_oid_esta_pedi = ep.oid_esta_pedi(+)
						AND sc.soca_oid_soli_cabe = con.oid_soli_cabe(+);

			   cursor f_cur_zonificacion is
					SELECT
					       sgv.des_subg_vent subgerencia_ventas, re.des_regi region,
					       zo.des_zona zona, sec.des_secci seccion,
					       re.zsgv_oid_subg_vent oid_subg_vent, zo.zorg_oid_regi oid_regi,
					       sec.zzon_oid_zona oid_zona, tr_ad.zscc_oid_secc oid_secc
					  FROM
					  	   ped_solic_cabec sc,
					       zon_zona zo,
					       zon_regio re,
					       zon_sub_geren_venta sgv,
					       zon_terri_admin tr_ad,
					       zon_secci sec

					 WHERE
					   sc.oid_soli_cabe = oid_solicitud_param
					   AND tr_ad.oid_terr_admi = sc.ztad_oid_terr_admi
					   AND tr_ad.zscc_oid_secc = sec.oid_secc
					   AND sec.zzon_oid_zona = zo.oid_zona
					   AND zo.zorg_oid_regi = re.oid_regi
					   AND re.zsgv_oid_subg_vent = sgv.oid_subg_vent;



	   		BEGIN
				open f_cur_ped_solic_cabec;
				LOOP

					fetch f_cur_ped_solic_cabec
					  into f_oid_cabecera, f_tipo_solicitud ,  f_subacceso ,  f_numero_solicitud ,
				       f_marca, f_canal ,  f_periodo ,   f_acceso ,  f_acceso_fisico,
					   f_tipo_despacho, f_sociedad_ventas, f_oid_cliente ,  f_codigo_cliente ,
					   f_nombre1 ,  f_apellido1 ,  f_apellido2 ,f_tipo_cliente , f_subtipo_cliente ,
				       --f_subgerencia_ventas ,  f_region ,   f_zona ,  f_seccion ,
f_territorio , f_ubigeo , f_oid_consultora_asociada , f_consultora_asociada , f_oid_receptor_factura
,
					   f_receptor_factura , f_oid_pagador_factura ,  f_pagador_factura ,f_numero_clientes ,
f_tipo_direccion, f_oid_clie_dire , f_tipo_doc_identidad , f_tipo_doc_legal , f_numero_doc_legal ,
				       f_indicador_impuesto ,  f_forma_pago ,    f_moneda ,   f_mone_oid_mone ,  f_monto1 ,
					   f_monto2 ,    f_monto3 ,  f_flete ,  f_importe_impuesto ,  f_total_pagar,
					   f_monto1_2 ,  f_monto2_2 ,  f_monto3_2 ,  f_flete_1 , f_importe_impuesto_1 ,
				       f_total_pagar_1 ,  f_fecha_prevista , f_fecha_fact ,  f_codigo_estado ,
				       f_grupo_proceso ,   f_proceso ,     f_estado ,  f_oid_tipo_solicitud ,
				       f_oid_proceso ,   f_oid_sbac , f_oid_marc ,  f_oid_cana ,  f_oid_peri ,
					   f_oid_acce , f_oid_tipo_clie , f_oid_subt_clie ,
					   --f_oid_subg_vent , f_oid_regi ,     f_oid_zona ,   f_oid_secc ,
					   f_oid_terr ,  f_oid_tipo_docu , f_oid_mone ,
					   f_oid_esta_solic ,f_oid_grup_proc ,f_numero_consolidado, f_descripcionestado ;
				  EXIT WHEN f_cur_ped_solic_cabec%NOTFOUND;
				END LOOP;
				CLOSE f_cur_ped_solic_cabec;


				OPEN f_cur_zonificacion;
				LOOP
					fetch f_cur_zonificacion
					into
						f_subgerencia_ventas ,  f_region ,   f_zona ,  f_seccion ,
						f_oid_subg_vent , f_oid_regi ,     f_oid_zona ,   f_oid_secc;
					EXIT WHEN f_cur_zonificacion%NOTFOUND;
				END LOOP;

				CLOSE f_cur_zonificacion;

				f_registro.oid_cabecera :=f_oid_cabecera;
				f_registro.tipo_solicitud :=f_tipo_solicitud;
				f_registro.subacceso :=f_subacceso;
				f_registro.numero_solicitud := f_numero_solicitud ;
				f_registro.marca := f_marca;
				f_registro.canal := f_canal;
				f_registro.periodo := f_periodo;
				f_registro.acceso := f_acceso;
				f_registro.acceso_fisico := f_acceso_fisico;
				f_registro.tipo_despacho := f_tipo_despacho;
				f_registro.sociedad_ventas := f_sociedad_ventas;
				f_registro.oid_cliente := f_oid_cliente;
				f_registro.codigo_cliente := f_codigo_cliente;
				f_registro.nombre1 := f_nombre1;
				f_registro.apellido1 := f_apellido1;
				f_registro.apellido2 := f_apellido2;
				f_registro.tipo_cliente := f_tipo_cliente;
				f_registro.subtipo_cliente := f_subtipo_cliente;
				f_registro.subgerencia_ventas := f_subgerencia_ventas;
				f_registro.region := f_region;
				f_registro.zona := f_zona;
				f_registro.seccion := f_seccion;
				f_registro.territorio := f_territorio;
				f_registro.ubigeo := f_ubigeo;
				f_registro.oid_consultora_asociada := f_oid_consultora_asociada;
				f_registro.consultora_asociada := f_consultora_asociada;
				f_registro.oid_receptor_factura := f_oid_receptor_factura;
				f_registro.receptor_factura := f_receptor_factura;
				f_registro.oid_pagador_factura := f_oid_pagador_factura;
				f_registro.pagador_factura := f_pagador_factura;
				f_registro.numero_clientes := f_numero_clientes;
				f_registro.tipo_direccion := f_tipo_direccion;

				--BELC300023533
				f_registro.oid_cliente_direccion := f_oid_clie_dire;

				f_registro.tipo_doc_identidad := f_tipo_doc_identidad;
				f_registro.tipo_doc_legal := f_tipo_doc_legal;
				f_registro.numero_doc_legal := f_numero_doc_legal;
				f_registro.indicador_impuesto := f_indicador_impuesto;
				f_registro.forma_pago := f_forma_pago;
				f_registro.moneda := f_moneda;
				f_registro.mone_oid_mone := f_mone_oid_mone;
				f_registro.monto1 := f_monto1;
				f_registro.monto2 := f_monto2;
				f_registro.monto3 := f_monto3;
				f_registro.flete := f_flete;
				f_registro.importe_impuesto := f_importe_impuesto;
				f_registro.total_pagar := f_total_pagar;
				f_registro.monto1_2 := f_monto1_2;
				f_registro.monto2_2 := f_monto2_2;
				f_registro.monto3_2 := f_monto3_2;
				f_registro.flete_1 := f_flete_1;
				f_registro.importe_impuesto_1 := f_importe_impuesto_1;
				f_registro.total_pagar_1 := f_total_pagar_1;
				f_registro.fecha_prevista := f_fecha_prevista;
				f_registro.fecha_fact := f_fecha_fact;
				f_registro.codigo_estado := f_codigo_estado;
				f_registro.grupo_proceso := f_grupo_proceso;
				f_registro.proceso := f_proceso;
				f_registro.estado := f_estado;
				f_registro.oid_tipo_solicitud := f_oid_tipo_solicitud;
				f_registro.oid_proceso := f_oid_proceso;
				f_registro.oid_sbac := f_oid_sbac;
				f_registro.oid_marc := f_oid_marc;
				f_registro.oid_cana := f_oid_cana;
				f_registro.oid_peri := f_oid_peri;
				f_registro.oid_acce := f_oid_acce;
				f_registro.oid_tipo_clie := f_oid_tipo_clie;
				f_registro.oid_subt_clie := f_oid_subt_clie;
				f_registro.oid_subg_vent := f_oid_subg_vent;
				f_registro.oid_regi := f_oid_regi;
				f_registro.oid_zona := f_oid_zona;
				f_registro.oid_secc := f_oid_secc;
				f_registro.oid_terr := f_oid_terr;
				f_registro.oid_tipo_docu := f_oid_tipo_docu;
				f_registro.oid_mone := f_oid_mone;
				f_registro.oid_esta_solic := f_oid_esta_solic;
				f_registro.oid_grup_proc := f_oid_grup_proc;
				f_registro.numero_consolidado := f_numero_consolidado;
				f_registro.descripcionestado := f_descripcionestado;

				PIPE ROW(f_registro);

	   		return;
	   END;

END pq_ped_solic;
/

