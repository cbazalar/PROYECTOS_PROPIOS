CREATE OR REPLACE VIEW V_CCC_LOTES_MOVIM AS
SELECT ROWNUM AS num_line, oid_cuen_corr_banc, oid_banc, num_lote, fec_pago,
       des_banc, cod_cc_banc, imp_tota, oid_mov
  FROM (SELECT   oid_cuen_corr_banc, oid_banc, num_lote, fec_pago, ba.des_banc,
                 cc.cod_cc_banc, SUM (imp_pago) imp_tota, max(mv.OID_MOVI_BANC) oid_mov
            FROM ccc_movim_banca mv,
                 ccc_sucur su,
                 ccc_banco ba,
                 ccc_cuent_corri_banca cc
           WHERE cc.oid_cuen_corr_banc = mv.ccba_oid_cc_banc
             AND su.oid_sucu = cc.sucu_oid_sucu
             AND su.cban_oid_banc = ba.oid_banc
        GROUP BY oid_cuen_corr_banc,
                 oid_banc,
                 num_lote,
                 fec_pago,
                 ba.des_banc,
                 cc.cod_cc_banc
        ORDER BY oid_cuen_corr_banc,
                 oid_banc,
                 num_lote,
                 ba.des_banc,
                 cc.cod_cc_banc,
                 fec_pago) xxx;
comment on column V_CCC_LOTES_MOVIM.NUM_LINE is 'Numero de linea';
comment on column V_CCC_LOTES_MOVIM.OID_CUEN_CORR_BANC is 'Identificador unico de la cuenta corriente bancaria';
comment on column V_CCC_LOTES_MOVIM.OID_BANC is 'Identificador unico del banco';
comment on column V_CCC_LOTES_MOVIM.NUM_LOTE is 'Numero de lote';
comment on column V_CCC_LOTES_MOVIM.FEC_PAGO is 'Fecha de pago';
comment on column V_CCC_LOTES_MOVIM.DES_BANC is 'Descripcion del banco';
comment on column V_CCC_LOTES_MOVIM.COD_CC_BANC is 'Codigo de cuenta';
comment on column V_CCC_LOTES_MOVIM.IMP_TOTA is 'Importe total';
comment on column V_CCC_LOTES_MOVIM.OID_MOV is 'Movimiento bancario';

