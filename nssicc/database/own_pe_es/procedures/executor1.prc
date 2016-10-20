CREATE OR REPLACE PROCEDURE "EXECUTOR1" (str_par IN VARCHAR2)
AS
linea_actual VARCHAR2(32767);
	linea_resto VARCHAR2(32767);
	BEGIN
	linea_actual:= SUBSTR(str_par, 1, (INSTRB(str_par ,';')-1));
	IF LENGTH(SUBSTR(str_par, (INSTRB(str_par, ';')),LENGTH(str_par))) = 1 THEN
			EXECUTE IMMEDIATE linea_actual;
	ELSE
			linea_resto:= SUBSTR(str_par, (INSTRB(str_par, ';')+1),LENGTH(str_par));
			LOOP
					EXECUTE IMMEDIATE linea_actual;
					linea_actual:= SUBSTR(linea_resto, 1, (INSTRB(linea_resto ,';')-1));
					IF LENGTH(SUBSTR(linea_resto, (INSTRB(linea_resto, ';')),LENGTH(linea_resto))) = 1 THEN
							EXECUTE IMMEDIATE linea_actual;
							EXIT;
					ELSE
							linea_resto:= SUBSTR(linea_resto, (INSTRB(linea_resto, ';')+1),LENGTH(linea_resto));
					END IF;
			END LOOP;
	END IF;
END;
/

