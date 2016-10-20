CREATE OR REPLACE PROCEDURE SSICC_EC_LB.ccc_pr_regis_lote_banca_esika
(
  p_cod_pais      seg_pais.cod_pais%TYPE,
  p_cod_soci      seg_socie.cod_soci%TYPE,
  p_cod_cban      ccc_cuent_corri_banca.cod_cc%TYPE,
  p_cod_tipo_orig ccc_tipos_orige_lotes_banca.cod_tipo_orig%TYPE,
  p_num_lote      ccc_movim_banca.num_lote%TYPE,
  p_cod_usua      seg_usuar.use_usua%TYPE
) IS

  lv_cont          ccc_numer_lote.cont%TYPE;
  lv_val_cade_fech ccc_numer_lote.val_cade_fech%TYPE;

  lnnumeroregistros NUMBER;

BEGIN

  lv_val_cade_fech := to_char(trunc(SYSDATE),
                              'YYYYMMDD');

  SELECT MIN(cnl.cont)
    INTO lv_cont
    FROM ccc_numer_lote@dbl_siccece_exec cnl
   WHERE cnl.val_cade_fech = lv_val_cade_fech;

  IF lv_cont IS NULL THEN
    INSERT INTO ccc_numer_lote@dbl_siccece_exec
    VALUES
      (to_char(trunc(SYSDATE),
               'YYYYMMDD'),
       300);
  END IF;

  IF lv_cont < 300 THEN
    UPDATE ccc_numer_lote@dbl_siccece_exec cnl
       SET cnl.cont = 300
     WHERE cnl.val_cade_fech = lv_val_cade_fech;

  END IF;

  SELECT COUNT(1) INTO lnnumeroregistros FROM ccc_movim_banca_ruteo WHERE num_lote = p_num_lote;

  IF lnnumeroregistros > 0 THEN
    ccc_pkg_gener.ccc_pr_regis_lote_banca@dbl_siccece_exec(p_cod_cban,
                                                           p_cod_tipo_orig,
                                                           p_num_lote,
                                                           p_cod_usua);
  END IF;

END ccc_pr_regis_lote_banca_esika;
/
