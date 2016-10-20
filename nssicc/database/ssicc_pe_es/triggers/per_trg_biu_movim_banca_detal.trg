CREATE OR REPLACE TRIGGER "PER_TRG_BIU_MOVIM_BANCA_DETAL" 
-- *****************************************************************************
-- Descripcion  : Trigger de Auditoria a la Tabla PER_MOVIM_BANCA_DETAL
-- Especificado : Carlos Bazalar
-- Fecha        : 25/05/2007
-- *****************************************************************************
BEFORE INSERT OR UPDATE ON PER_MOVIM_BANCA_DETAL
FOR EACH ROW
DECLARE
   lnCorrelativo  number;
   lsTipo					varchar2(1) := 'N';
BEGIN
/* obteniendo correlativo para la tabla de auditoria */
SELECT PER_SEQ_BANCA_DETAL_HISTO.NEXTVAL
INTO lnCorrelativo FROM dual;

IF updating THEN
   lsTipo := 'M';
end if;

/* Grabando en Campos de Auditoria */
INSERT INTO PER_MOVIM_BANCA_DETAL_HISTO
(
COR_HIST,
TIP_REGI,
PAIS_COD_PAIS,
TIOR_TIPO_ORIG_DATO,
MOCA_NUM_LOTE_INTE,
CON_TRAN,
FEC_PAGO,
NUM_FABO,
VAL_PAGO,
NUM_CUPO,
COD_CONS,
DIG_CHEQ,
OFI_RECA,
NOM_OFIC,
TIP_TRAN,
NUM_DOCU,
VAL_HORA,
USU_PROC,
DES_OBSE,
FEC_PROC,
HOR_PROC,
IMP_PAGO_APLI,
IMP_PAGO_PEND,
IMP_RECA_GENE,
IMP_PERC,
STA_MOVI,
COD_PLAN_EMPL,
USU_DIGI,
FEC_DIGI,
USU_MODI,
FEC_MODI,
EST_MODE
)
VALUES
(
lnCorrelativo,
lsTipo,
:new.pais_cod_pais,
:new.tior_tipo_orig_dato,
:new.moca_num_lote_inte,
:new.con_tran,
:new.fec_pago,
:new.num_fabo,
:new.val_pago,
:new.num_cupo,
:new.cod_cons,
:new.dig_cheq,
:new.ofi_reca,
:new.nom_ofic,
:new.tip_tran,
:new.num_docu,
:new.val_hora,
:new.usu_proc,
:new.des_obse,
:new.fec_proc,
:new.hor_proc,
:new.imp_pago_apli,
:new.imp_pago_pend,
:new.imp_reca_gene,
:new.imp_perc,
:new.sta_movi,
:new.cod_plan_empl,
:new.usu_digi,
:new.fec_digi,
:new.usu_modi,
:new.fec_modi,
:new.est_mode
);


END PER_TRG_BIU_MOVIM_BANCA_DETAL;
/

