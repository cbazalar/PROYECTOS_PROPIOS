CREATE OR REPLACE FUNCTION "FN_008_CALC_OID_PER_ANT" (
	idpais_par in varchar,
	idmarc_par in varchar,
	idcana_par in varchar,
	id_peri in varchar,
	n_peri in varchar
)
return number
is
  var_aux_num number;
begin
		 EXECUTE IMMEDIATE
		 		 'SELECT OID_PERI			                    '||
                 'FROM                                          '||
				 '	(                                           '||
				 '	SELECT OID_PERI,                            '||
				 '		   ROWNUM AS POS                        '||
				 '	FROM                                        '||
				 '		(                                       '||
				 '		SELECT PERI.OID_PERI	                '||
				 '		FROM CRA_PERIO PERI,                    '||
				 '			 (                                  '||
				 '			 SELECT FEC_FINA                    '||
				 '			 FROM CRA_PERIO                     '||
				 '			 WHERE OID_PERI = :1                '||
				 '			 ) FF  			  					'||
				 '		WHERE FF.FEC_FINA >= PERI.FEC_FINA		'||
				 '			  AND PERI.PAIS_OID_PAIS = :2	  	'||
				 '			  AND PERI.MARC_OID_MARC = :3		'||
				 '			  AND PERI.CANA_OID_CANA = :4		'||
				 '		ORDER BY PERI.FEC_FINA DESC  			'||
				 '		)	  	 								'||
				 '	)											'||
				 'WHERE POS =	:5								'
	 INTO var_aux_num
	 USING id_peri, idpais_par, idmarc_par, idcana_par, n_peri;
  	 return var_aux_num;
end;
/

