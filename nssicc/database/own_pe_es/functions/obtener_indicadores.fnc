CREATE OR REPLACE FUNCTION "OBTENER_INDICADORES" (oid_cliente IN NUMBER, indicador IN NUMBER) RETURN NUMBER
IS
indicadorestrella NUMBER := 0;
indicadorlider NUMBER := 0;
indicadorgerentezona NUMBER := 0;
indicadorgerenteregion NUMBER := 0;
BEGIN
SELECT   MAX(DECODE (tcli.cod_tipo_clie, '02', DECODE (tcla.cod_tipo_clas, '03', DECODE (cla.COD_CLAS, '01', 1, 0), 0 ))) indicadorestrella,
         MAX(DECODE (tcli.cod_tipo_clie, '02', DECODE (tcla.cod_tipo_clas, '01', DECODE (cla.COD_CLAS, '01', 1, 0), 0 ))) indicadorlider,
         MAX(DECODE (tcli.cod_tipo_clie, '04', DECODE (scli.COD_SUBT_CLIE, '02', 1, 0), 0 )) indicadorgerentezona,
         MAX(DECODE (tcli.cod_tipo_clie, '04', DECODE (scli.COD_SUBT_CLIE, '01', 1, 0), 0 )) indicadorgerenteregion
		 into indicadorestrella, indicadorlider, indicadorgerentezona, indicadorgerenteregion
    FROM v_mae_tipif_clien cli,
		 mae_subti_clien scli,
         mae_tipo_clien tcli,
         mae_tipo_clasi_clien tcla,
         mae_clasi cla
   WHERE cli.ticl_oid_tipo_clie = tcli.oid_tipo_clie
     AND cli.tccl_oid_tipo_clasi = tcla.oid_tipo_clas(+)
     AND cli.clas_oid_clas = cla.oid_clas (+)
	 AND cli.SBTI_OID_SUBT_CLIE = scli.OID_SUBT_CLIE
     AND cli.clie_oid_clie = oid_cliente;

IF indicador = 1 THEN RETURN indicadorestrella;
ELSIF indicador = 2 THEN RETURN indicadorlider;
ELSIF indicador = 3 THEN RETURN indicadorgerentezona;
ELSIF indicador = 4 THEN RETURN indicadorgerenteregion;
END IF;

END OBTENER_INDICADORES;
/

