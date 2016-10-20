CREATE OR REPLACE TRIGGER "PER_TRG_BIU_CUENT_CORRI_DOCLE" 
-- *****************************************************************************
-- Descripcion  : Trigger de Auditoria a la Tabla PER_CUENT_CORRI_DOCLE
-- Especificado : Carlos Bazalar
-- Fecha        : 25/05/2007
-- *****************************************************************************
BEFORE INSERT OR UPDATE ON PER_CUENT_CORRI_DOCLE
FOR EACH ROW
DECLARE
   lnCorrelativo  number;
   lsTipo					varchar2(1) := 'N';
BEGIN
/* obteniendo correlativo para la tabla de auditoria */
SELECT PER_SEQ_CORRI_DOCLE_HISTO.NEXTVAL
INTO lnCorrelativo FROM dual;

IF updating THEN
   lsTipo := 'M';
end if;

/* Grabando en Campos de Auditoria */
INSERT INTO PER_CUENT_CORRI_DOCLE_HISTO
(
COR_HIST,
TIP_REGI,
PAIS_COD_PAIS,
COR_CUEN_CORR_DOLE,
COD_SOCI,
COD_CANA,
COD_ACCE,
COD_SBAC,
NUM_SOLI_CONS,
TIP_DOCU_LEGA,
EJE_DOCU_INTE,
NUM_DOCU_INTE,
NUM_ORDE_CUOT,
COD_MARC,
COD_PERI,
COD_CLIE,
TIP_DOCU_IDEN_FISC,
NUM_IDEN_FISC,
TIP_DOCU_IDEN_NNAL,
NUM_IDEN_NNAL,
FEC_EMIS_DOCU,
FEC_FACT,
FEC_VENC_CUOT,
COD_FORM_PAGO,
COD_MEDI_PAGO,
COD_MARC_SITU,
TIP_CARG_ABON,
COD_PROC_CREA,
COD_SUBP_CREA,
COD_CANA_REFE,
COD_ACCE_REFE,
COD_SBAC_REFE,
NUM_SOLI_CONS_REFE,
COD_CANA_PERC,
COD_ACCE_PERC,
COD_SUBA_PERC,
NUM_SOLI_CONS_PERC,
TIP_SOLI_PERC,
IND_AFEC,
IND_RECA_PERC,
POR_PERC,
FAC_CALC_PERC,
IMP_TOTA_DOCU,
IMP_CUOT,
IMP_CUOT_PAGA,
IMP_CUOT_PEND,
IMP_INAF_PERC,
IMP_PAGA_INAF_PERC,
IMP_PEND_INAF_PERC,
IMP_AFEC_PERC,
IMP_PAGA_AFEC_PERC,
IMP_PEND_AFEC_PERC,
IMP_PERC_PERC,
IMP_PAGA_PERC,
IMP_PEND_PERC,
USU_DIGI,
FEC_DIGI,
USU_MODI,
FEC_MODI,
EST_CUEN_CORR_DOLE)

VALUES

(
lnCorrelativo,
lsTipo,
:new.pais_cod_pais,
:new.cor_cuen_corr_dole,
:new.cod_soci,
:new.cod_cana,
:new.cod_acce,
:new.cod_sbac,
:new.num_soli_cons,
:new.tip_docu_lega,
:new.eje_docu_inte,
:new.num_docu_inte,
:new.num_orde_cuot,
:new.cod_marc,
:new.cod_peri,
:new.cod_clie,
:new.tip_docu_iden_fisc,
:new.num_iden_fisc,
:new.tip_docu_iden_nnal,
:new.num_iden_nnal,
:new.fec_emis_docu,
:new.fec_fact,
:new.fec_venc_cuot,
:new.cod_form_pago,
:new.cod_medi_pago,
:new.cod_marc_situ,
:new.tip_carg_abon,
:new.cod_proc_crea,
:new.cod_subp_crea,
:new.cod_cana_refe,
:new.cod_acce_refe,
:new.cod_sbac_refe,
:new.num_soli_cons_refe,
:new.cod_cana_perc,
:new.cod_acce_perc,
:new.cod_suba_perc,
:new.num_soli_cons_perc,
:new.tip_soli_perc,
:new.ind_afec,
:new.ind_reca_perc,
:new.por_perc,
:new.fac_calc_perc,
:new.imp_tota_docu,
:new.imp_cuot,
:new.imp_cuot_paga,
:new.imp_cuot_pend,
:new.imp_inaf_perc,
:new.imp_paga_inaf_perc,
:new.imp_pend_inaf_perc,
:new.imp_afec_perc,
:new.imp_paga_afec_perc,
:new.imp_pend_afec_perc,
:new.imp_perc_perc,
:new.imp_paga_perc,
:new.imp_pend_perc,
:new.usu_digi,
:new.fec_digi,
:new.usu_modi,
:new.fec_modi,
:new.est_cuen_corr_dole
);

END PER_TRG_BIU_CUENT_CORRI_DOCLE;
/

