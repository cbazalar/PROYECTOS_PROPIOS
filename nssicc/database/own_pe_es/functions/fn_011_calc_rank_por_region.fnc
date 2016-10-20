CREATE OR REPLACE FUNCTION "FN_011_CALC_RANK_POR_REGION" (
	idpais_par IN VARCHAR,
	idmarc_par IN VARCHAR,
	idcana_par IN VARCHAR,
	id_reg IN VARCHAR,
	id_peri_desde IN VARCHAR,
	id_peri_hasta IN VARCHAR,
	id_cli IN VARCHAR

)
RETURN NUMBER


IS

  var_aux_num NUMBER;

BEGIN

		 EXECUTE IMMEDIATE


				'SELECT POS                                                 '||
				'FROM                                                       '||
				'(                                                          '||
				'	SELECT CLIE_OID_CLIE,                                   '||
				'		   VENTA,                                           '||
				'		   ROWNUM AS POS                                    '||
				'	FROM                                                    '||
				'	(                                                       '||
				'		SELECT SEG_CLI.CLIE_OID_CLIE,                       '||
				'			   SUM(SEG_CLI.VAL_VENT) VENTA                  '||
				'		FROM DTR_SEGME_CLIEN SEG_CLI,                       '||
				'			 CRA_PERIO PERI,                                '||
				'			 ZON_REGIO REGI,                                '||
				'			 (                                              '||
				'			 SELECT FEC_INIC                                '||
				'			 FROM CRA_PERIO                                 '||
				'			 WHERE OID_PERI = :1	 				        '||
				'			 ) FI,                                          '||
				'			 (                                              '||
				'			 SELECT FEC_FINA                                '||
				'			 FROM CRA_PERIO                                 '||
				'			 WHERE OID_PERI = :2					        '||
				'			 ) FF	                                        '||
				'		WHERE FI.FEC_INIC <= PERI.FEC_INIC                  '||
				'			  AND FF.FEC_FINA >= PERI.FEC_FINA              '||
				'			  AND SEG_CLI.PERD_OID_PERI = PERI.OID_PERI     '||
				'			  AND SEG_CLI.ZORG_OID_REGI = REGI.OID_REGI     '||
				'			  AND REGI.OID_REGI = :3			   	        '||
				'			  AND PERI.PAIS_OID_PAIS = :4  			        '||
				'			  AND PERI.CANA_OID_CANA = :5			        '||
				'			  AND PERI.MARC_OID_MARC = :6			        '||
				'		GROUP BY REGI.OID_REGI,                             '||
				'				 SEG_CLI.CLIE_OID_CLIE	                    '||
				'		ORDER BY VENTA DESC                                 '||
				'	)                                                       '||
				')	                                                        '||
				'WHERE CLIE_OID_CLIE = :7                                    '




	 INTO var_aux_num
	 USING id_peri_desde, id_peri_hasta, id_reg, idpais_par, idcana_par, idmarc_par, id_cli;

  	 RETURN var_aux_num;

END;
/

