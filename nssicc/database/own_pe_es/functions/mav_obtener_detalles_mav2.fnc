CREATE OR REPLACE FUNCTION "MAV_OBTENER_DETALLES_MAV2" (
      idperi IN NUMBER,
      idcliente IN NUMBER,
      idestado IN NUMBER
) RETURN OBJ_MAV_OBT_DETAL_MAV_TABLE PIPELINED IS

F_REGISTRO OBJ_MAV_OBTENER_DETALLE_MAV := OBJ_MAV_OBTENER_DETALLE_MAV(null,null,null,null,null,null,null,null,null,null,
                                                                  null,null,null,null,null,null,null,null,null,null,
                                                                  null,null,null,null,null,null,null,null,null,null,
                                                                  null,null,null,null,null,null,null,null,null,null,
                                                                  null,null,null,null,null,null,null,null,null,null,
                                                                  null,null,null);
CURSOR salida_cur IS
SELECT distinct acti_oid_acti, val_base_esti_dest, val_camp_eval, ocat_oid_cata,
       civi_oid_cicl_vida, mav_detal_mav.clas_oid_clas, val_crit, mcur_oid_curs,
       val_edad_desd, val_edad_hast, ind_envi_mens, aest_oid_esta_acti,
       tepr_oid_tipo_esta_proc, val_fact_corr, fcob_oid_form_cobr, licl_oid_list_clie,
       mapr_oid_marc_prod, mens_oid_mens, val_mont, nego_oid_nego, num_aniv, val_obse,
       oid_deta_mav, perd_oid_peri, perd_oid_peri_curs, mav_detal_mav.perd_oid_peri_fina,
       perd_oid_peri_fina_mont, perd_oid_peri_inic_mont, val_prec, val_prec_cont,
       val_prec_stnd, prdn_oid_proc, prod_oid_prod, scas_oid_subc, tccl_oid_tipo_clas,
       mav_detal_mav.ticl_oid_tipo_clie, tofe_oid_tipo_ofer, num_unid_esti,
       MAV_FN_OBTEN_NUMER_UNIDA(oid_deta_mav, zon_regio.oid_regi, zon_zona.oid_zona, num_unid_clie) num_unid_clie,
       num_unid_tota, num_unid_tota_esti, uneg_oid_unid_nego, atde_oid_acti_tipo_desp,
       cras_oid_crit_asig, pentrada.cana_oid_cana, pentrada.marc_oid_marc,
       pentrada.pais_oid_pais, pentrada.val_nomb_peri,
       (SELECT sm.des_marc
          FROM seg_marca sm
         WHERE pentrada.marc_oid_marc = sm.oid_marc) AS des_marc,
       pq_apl_aux.valor_gen_i18n_sicc (1, pentrada.cana_oid_cana, 'SEG_CANAL') des_canal,
       pq_apl_aux.valor_gen_i18n_sicc (1,
                                       mav_detal_mav.acti_oid_acti,
                                       'MAV_ACTIV'
                                      ) des_activ,
       pq_apl_aux.valor_gen_i18n_sicc (1,
                                       mav_detal_mav.ticl_oid_tipo_clie,
                                       'MAE_TIPO_CLIEN'
                                      ) des_tipo_cliente,
       fopa_oid_form_pago
  FROM mav_detal_mav,
       mav_subcr_asign msa,
       cra_perio pentrada,
       cra_perio pini,
       cra_perio pfin,
       mav_subti_clien_detal,
       mae_clien_unida_admin,
       zon_terri_admin,
       zon_secci,
       zon_zona,
       zon_regio,
       mav_detal_mav_unida_admin
 WHERE mav_detal_mav.scas_oid_subc = msa.oid_subc
   AND mav_detal_mav.perd_oid_peri = pentrada.oid_peri
   AND mav_detal_mav.oid_deta_mav = mav_subti_clien_detal.denv_oid_deta_mav(+)
   AND mav_detal_mav.perd_oid_peri = pini.oid_peri
   AND mav_detal_mav.perd_oid_peri_fina = pfin.oid_peri(+)
   AND pentrada.oid_peri = idperi
   AND (   (    mav_detal_mav.perd_oid_peri_fina IS NULL
            AND mav_detal_mav.perd_oid_peri = pentrada.oid_peri
           )
        OR (    mav_detal_mav.perd_oid_peri_fina IS NOT NULL
            AND pini.fec_inic <= pentrada.fec_inic
            AND pentrada.fec_fina <= pfin.fec_fina
           )
       )
   AND mav_detal_mav.tepr_oid_tipo_esta_proc = idestado
   AND mav_detal_mav.oid_deta_mav NOT IN (SELECT denv_oid_deta_mav
                                            FROM mav_envio
                                           WHERE clie_oid_clie = idcliente)
   AND mae_clien_unida_admin.clie_oid_clie = idcliente
   AND MAV_FN_VALID_TIPIF_CLIEN(idcliente, mav_detal_mav.oid_deta_mav) > 0
   AND zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi
   AND zon_terri_admin.zscc_oid_secc = zon_secci.oid_secc
   AND zon_zona.oid_zona = zon_secci.zzon_oid_zona
   AND zon_zona.zorg_oid_regi = zon_regio.oid_regi
   AND mae_clien_unida_admin.perd_oid_peri_fin IS NULL
   AND zon_zona.oid_zona = NVL (mav_detal_mav_unida_admin.zzon_oid_zona, zon_zona.oid_zona)
   AND zon_regio.oid_regi = NVL (mav_detal_mav_unida_admin.zorg_oid_regi, zon_regio.oid_regi)
   AND mav_detal_mav_unida_admin.denv_oid_deta_mav(+) = mav_detal_mav.oid_deta_mav
   AND (MAV_FN_VALID_ESTAT_CLIEN(idcliente, idperi, zon_regio.oid_regi, mav_detal_mav.oid_deta_mav, mav_detal_mav.aest_oid_esta_acti) = 1);

F_ACTI_OID_ACTI NUMBER;
F_VAL_BASE_ESTI_DEST NUMBER;
F_VAL_CAMP_EVAL NUMBER;
F_OCAT_OID_CATA NUMBER;
F_CIVI_OID_CICL_VIDA NUMBER;
F_CLAS_OID_CLAS NUMBER;
F_VAL_CRIT VARCHAR (20);
F_MCUR_OID_CURS NUMBER;
F_VAL_EDAD_DESD NUMBER;
F_VAL_EDAD_HAST NUMBER;
F_IND_ENVI_MENS NUMBER;
F_AEST_OID_ESTA_ACTI NUMBER;
F_TEPR_OID_TIPO_ESTA_PROC NUMBER;
F_VAL_FACT_CORR NUMBER;
F_FCOB_OID_FORM_COBR NUMBER;
F_LICL_OID_LIST_CLIE NUMBER;
F_MAPR_OID_MARC_PROD NUMBER;
F_MENS_OID_MENS NUMBER;
F_VAL_MONT NUMBER;
F_NEGO_OID_NEGO NUMBER;
F_NUM_ANIV VARCHAR (30);
F_VAL_OBSE VARCHAR (100);
F_OID_DETA_MAV NUMBER;
F_PERD_OID_PERI NUMBER;
F_PERD_OID_PERI_CURS NUMBER;
F_PERD_OID_PERI_FINA NUMBER;
F_PERD_OID_PERI_FINA_MONT NUMBER;
F_PERD_OID_PERI_INIC_MONT NUMBER;
F_VAL_PREC NUMBER;
F_VAL_PREC_CONT NUMBER;
F_VAL_PREC_STND NUMBER;
F_PRDN_OID_PROC NUMBER;
F_PROD_OID_PROD NUMBER;
F_SCAS_OID_SUBC NUMBER;
F_TCCL_OID_TIPO_CLAS NUMBER;
F_TICL_OID_TIPO_CLIE NUMBER;
F_TOFE_OID_TIPO_OFER NUMBER;
F_NUM_UNID_ESTI NUMBER;
F_NUM_UNID_CLIE NUMBER;
F_NUM_UNID_TOTA NUMBER;
F_NUM_UNID_TOTA_ESTI NUMBER;
F_UNEG_OID_UNID_NEGO NUMBER;
F_ATDE_OID_ACTI_TIPO_DESP NUMBER;
F_CRAS_OID_CRIT_ASIG NUMBER;
F_CANA_OID_CANA NUMBER;
F_MARC_OID_MARC NUMBER;
F_PAIS_OID_PAIS NUMBER;
F_VAL_NOMB_PERI VARCHAR (100);
F_DES_MARC VARCHAR (100);
F_DES_CANAL VARCHAR (100);
F_DES_ACTIV VARCHAR (100);
F_DES_TIPO_CLIENTE VARCHAR (100);
F_FOPA_OID_FORM_PAGO NUMBER;

BEGIN

  OPEN salida_cur;

  LOOP
  FETCH salida_cur INTO
        F_ACTI_OID_ACTI ,F_VAL_BASE_ESTI_DEST ,F_VAL_CAMP_EVAL ,F_OCAT_OID_CATA ,F_CIVI_OID_CICL_VIDA ,
        F_CLAS_OID_CLAS ,F_VAL_CRIT ,F_MCUR_OID_CURS ,F_VAL_EDAD_DESD ,F_VAL_EDAD_HAST ,F_IND_ENVI_MENS ,
        F_AEST_OID_ESTA_ACTI ,F_TEPR_OID_TIPO_ESTA_PROC ,F_VAL_FACT_CORR ,F_FCOB_OID_FORM_COBR ,
        F_LICL_OID_LIST_CLIE ,F_MAPR_OID_MARC_PROD ,F_MENS_OID_MENS ,F_VAL_MONT ,F_NEGO_OID_NEGO ,
        F_NUM_ANIV ,F_VAL_OBSE ,F_OID_DETA_MAV ,F_PERD_OID_PERI ,F_PERD_OID_PERI_CURS ,F_PERD_OID_PERI_FINA ,
        F_PERD_OID_PERI_FINA_MONT ,F_PERD_OID_PERI_INIC_MONT ,F_VAL_PREC ,F_VAL_PREC_CONT ,F_VAL_PREC_STND ,
        F_PRDN_OID_PROC ,F_PROD_OID_PROD ,F_SCAS_OID_SUBC ,F_TCCL_OID_TIPO_CLAS ,F_TICL_OID_TIPO_CLIE ,
        F_TOFE_OID_TIPO_OFER , F_NUM_UNID_ESTI ,F_NUM_UNID_CLIE ,F_NUM_UNID_TOTA ,F_NUM_UNID_TOTA_ESTI ,
        F_UNEG_OID_UNID_NEGO ,F_ATDE_OID_ACTI_TIPO_DESP ,F_CRAS_OID_CRIT_ASIG ,F_CANA_OID_CANA ,
        F_MARC_OID_MARC ,F_PAIS_OID_PAIS ,F_VAL_NOMB_PERI ,F_DES_MARC ,F_DES_CANAL ,F_DES_ACTIV ,
        F_DES_TIPO_CLIENTE ,F_FOPA_OID_FORM_PAGO;


        F_REGISTRO.ACTI_OID_ACTI:=F_ACTI_OID_ACTI ;
        F_REGISTRO.VAL_BASE_ESTI_DEST:=F_VAL_BASE_ESTI_DEST ;
        F_REGISTRO.VAL_CAMP_EVAL:=F_VAL_CAMP_EVAL ;
        F_REGISTRO.OCAT_OID_CATA :=F_OCAT_OID_CATA;
        F_REGISTRO.CIVI_OID_CICL_VIDA :=F_CIVI_OID_CICL_VIDA;
        F_REGISTRO.CLAS_OID_CLAS:=F_CLAS_OID_CLAS ;
        F_REGISTRO.VAL_CRIT :=F_VAL_CRIT;
        F_REGISTRO.MCUR_OID_CURS:=F_MCUR_OID_CURS ;
        F_REGISTRO.VAL_EDAD_DESD:=F_VAL_EDAD_DESD ;
        F_REGISTRO.VAL_EDAD_HAST:=F_VAL_EDAD_HAST ;
        F_REGISTRO.IND_ENVI_MENS:=F_IND_ENVI_MENS ;
        F_REGISTRO.AEST_OID_ESTA_ACTI:=F_AEST_OID_ESTA_ACTI ;
        F_REGISTRO.TEPR_OID_TIPO_ESTA_PROC:=F_TEPR_OID_TIPO_ESTA_PROC ;
        F_REGISTRO.VAL_FACT_CORR:=F_VAL_FACT_CORR ;
        F_REGISTRO.FCOB_OID_FORM_COBR:=F_FCOB_OID_FORM_COBR ;
        F_REGISTRO.LICL_OID_LIST_CLIE:=F_LICL_OID_LIST_CLIE ;
        F_REGISTRO.MAPR_OID_MARC_PROD:=F_MAPR_OID_MARC_PROD ;
        F_REGISTRO.MENS_OID_MENS:=F_MENS_OID_MENS ;
        F_REGISTRO.VAL_MONT:=F_VAL_MONT ;
        F_REGISTRO.NEGO_OID_NEGO:=F_NEGO_OID_NEGO ;
        F_REGISTRO.NUM_ANIV:=F_NUM_ANIV ;
        F_REGISTRO.VAL_OBSE:=F_VAL_OBSE ;
        F_REGISTRO.OID_DETA_MAV:=F_OID_DETA_MAV ;
        F_REGISTRO.PERD_OID_PERI:=F_PERD_OID_PERI ;
        F_REGISTRO.PERD_OID_PERI_CURS:=F_PERD_OID_PERI_CURS ;
        F_REGISTRO.PERD_OID_PERI_FINA:=F_PERD_OID_PERI_FINA ;
        F_REGISTRO.PERD_OID_PERI_FINA_MONT:=F_PERD_OID_PERI_FINA_MONT ;
        F_REGISTRO.PERD_OID_PERI_INIC_MONT:=F_PERD_OID_PERI_INIC_MONT ;
        F_REGISTRO.VAL_PREC:=F_VAL_PREC ;
        F_REGISTRO.VAL_PREC_CONT:=F_VAL_PREC_CONT ;
        F_REGISTRO.VAL_PREC_STND:=F_VAL_PREC_STND ;
        F_REGISTRO.PRDN_OID_PROC:=F_PRDN_OID_PROC ;
        F_REGISTRO.PROD_OID_PROD:=F_PROD_OID_PROD ;
        F_REGISTRO.SCAS_OID_SUBC:=F_SCAS_OID_SUBC ;
        F_REGISTRO.TCCL_OID_TIPO_CLAS:=F_TCCL_OID_TIPO_CLAS ;
        F_REGISTRO.TICL_OID_TIPO_CLIE:=F_TICL_OID_TIPO_CLIE ;
        F_REGISTRO.TOFE_OID_TIPO_OFER:=F_TOFE_OID_TIPO_OFER ;
        F_REGISTRO.NUM_UNID_ESTI:=F_NUM_UNID_ESTI ;
        F_REGISTRO.NUM_UNID_CLIE:=F_NUM_UNID_CLIE ;
        F_REGISTRO.NUM_UNID_TOTA:=F_NUM_UNID_TOTA ;
        F_REGISTRO.NUM_UNID_TOTA_ESTI:=F_NUM_UNID_TOTA_ESTI ;
        F_REGISTRO.UNEG_OID_UNID_NEGO:=F_UNEG_OID_UNID_NEGO ;
        F_REGISTRO.ATDE_OID_ACTI_TIPO_DESP:=F_ATDE_OID_ACTI_TIPO_DESP ;
        F_REGISTRO.CRAS_OID_CRIT_ASIG:=F_CRAS_OID_CRIT_ASIG ;
        F_REGISTRO.CANA_OID_CANA:=F_CANA_OID_CANA ;
        F_REGISTRO.MARC_OID_MARC:=F_MARC_OID_MARC ;
        F_REGISTRO.PAIS_OID_PAIS:=F_PAIS_OID_PAIS ;
        F_REGISTRO.VAL_NOMB_PERI:=F_VAL_NOMB_PERI ;
        F_REGISTRO.DES_MARC:=F_DES_MARC ;
        F_REGISTRO.DES_CANAL:=F_DES_CANAL ;
        F_REGISTRO.DES_ACTIV:=F_DES_ACTIV ;
        F_REGISTRO.DES_TIPO_CLIENTE:=F_DES_TIPO_CLIENTE ;
        F_REGISTRO.FOPA_OID_FORM_PAGO:=F_FOPA_OID_FORM_PAGO;


  EXIT WHEN salida_cur%NOTFOUND;
   PIPE ROW(F_REGISTRO);
  END LOOP;

  CLOSE salida_cur;

  END;
/

