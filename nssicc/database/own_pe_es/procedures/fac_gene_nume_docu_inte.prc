CREATE OR REPLACE PROCEDURE "FAC_GENE_NUME_DOCU_INTE" (ID_SPOOL IN VARCHAR2)
AS
   CURSOR CUR_1 IS
      SELECT SDI.OID_SECU_DOCU_INTE,
	  		 SDI.VAL_CANT_PAGI,
             SDI.SOCA_OID_SOLI_CABE,
			 SDI.TIDO_OID_TIPO_DOCU,
             SDI.GDIM_OID_DOCU_IMPR,
			 GDI.VAL_BUFF,
			 SDI.ICTP_OID_TIPO_PROG
      FROM FAC_SECUE_DOCUM_INTER SDI,
	  	   GEN_DOCUM_IMPRI GDI
      WHERE SDI.GSPO_OID_SPOO = TO_NUMBER (ID_SPOOL)
        AND GDI.OID_DOCU_IMPR(+) = SDI.GDIM_OID_DOCU_IMPR
      ORDER BY SDI.NUM_SECU, SDI.GDIM_OID_DOCU_IMPR;

   NUME_DOCU_INTE    NUMBER;
   NUME_DOCU_LEGA    NUMBER;
   EJERCICIO         NUMBER;
   PAIS              NUMBER;
   SOCI              NUMBER;
   SBAC              NUMBER;
   LIMI_DOCU_LEGA    NUMBER;
   OID_DOC_SBAC      NUMBER;
   SERIE_DOC_LEGAL   VARCHAR2 (10);
   LEN               NUMBER;
   POSI              NUMBER;
   INI               NUMBER;
   EJE_DOC           NUMBER;
   INVALID_NUM_DOC   EXCEPTION;
   IND_MONO_PAGI     NUMBER;
   POSFIN            NUMBER;
BEGIN
   FOR I IN CUR_1
   LOOP
      DECLARE
         BL1   BLOB;
         BL2   BLOB;
      BEGIN
         LEN := DBMS_LOB.GETLENGTH (I.VAL_BUFF);
         BL2 := I.VAL_BUFF;
         INI := 1;
         POSI := 1;

         FOR X IN 1 .. I.VAL_CANT_PAGI
         LOOP
            DECLARE
               RANGO_OID        NUMBER;
               RANGO_FIN_NUME   NUMBER;
               RANGO_INI_NUME   NUMBER;
               RANGO_SERIE      VARCHAR2 (9);
               CABE_FAC         NUMBER;
               OK               NUMBER;
            BEGIN
               OK := 0;

               SELECT SC.PAIS_OID_PAIS,
			   		  SC.SOCI_OID_SOCI,
					  SC.SBAC_OID_SBAC,
                      TO_NUMBER (SC.EJERCICIO),
					  FDS.VAL_ULTI_NUME_DOCU_LEGA,
                      FDS.VAL_LIMI_NUME_DOCU_LEGA,
                      FDS.VAL_ULTI_NUME_DOCU_INTE,
					  FDS.VAL_SERI_DOCU_LEGA,
                      FDS.OID_DOCU_SUBA,
					  FDS.VAL_ULTI_EJER_DOCU_INTE,
                      SC.IND_DOCU_MONO_PAGI
                 INTO PAIS,
				 	  SOCI,
					  SBAC,
                      EJERCICIO,
					  NUME_DOCU_LEGA,
                      LIMI_DOCU_LEGA,
                      NUME_DOCU_INTE,
					  SERIE_DOC_LEGAL,
                      OID_DOC_SBAC,
					  EJE_DOC,
                      IND_MONO_PAGI
                 FROM FAC_DOCUM_SUBAC FDS,
                      (SELECT PSC.PAIS_OID_PAIS,
					  		  PSC.SOCI_OID_SOCI,
                              PSC.SBAC_OID_SBAC,
                              TO_CHAR (PSC.FEC_FACT, 'YY') EJERCICIO,
                              FPF.IND_DOCU_MONO_PAGI
                         FROM PED_SOLIC_CABEC PSC,
						 	  FAC_PARAM_FACTU FPF
                        WHERE PSC.OID_SOLI_CABE = I.SOCA_OID_SOLI_CABE
                          AND FPF.PAIS_OID_PAIS = PSC.PAIS_OID_PAIS
					  ) SC
                WHERE FDS.TIDO_OID_TIPO_DOCU(+) = I.TIDO_OID_TIPO_DOCU
                  AND FDS.PAIS_OID_PAIS(+) = SC.PAIS_OID_PAIS
                  AND FDS.SBAC_OID_SBAC(+) = SC.SBAC_OID_SBAC
                  AND FDS.SOCI_OID_SOCI(+) = SC.SOCI_OID_SOCI;

               IF (IND_MONO_PAGI <> 1) THEN
                  IF OID_DOC_SBAC IS NULL THEN
                     BEGIN
                        SELECT OID_RANG,
							   VAL_FINA_RANG_NUME,
                               VAL_INIC_RANG_NUME,
							   VAL_SERI_DOCU
                          INTO RANGO_OID,
						  	   RANGO_FIN_NUME,
                               RANGO_INI_NUME,
							   RANGO_SERIE
                        FROM (SELECT RNO.OID_RANG,
						 	   		 RNO.VAL_FINA_RANG_NUME,
                                     RNO.VAL_INIC_RANG_NUME,
                                     RNO.VAL_SERI_DOCU,
									 RNO.NUM_ORDE_UTIL
                              FROM FAC_RANGO_NUMER_OFICI RNO
                              WHERE RNO.PAIS_OID_PAIS = PAIS
                                AND RNO.SBAC_OID_SBAC = SBAC
                                AND RNO.SOCI_OID_SOCI = SOCI
                                AND RNO.TIDO_OID_TIPO_DOCU = I.TIDO_OID_TIPO_DOCU
                              ORDER BY NUM_ORDE_UTIL)
                        WHERE ROWNUM = 1;

                        IF (RANGO_OID IS NOT NULL) THEN
                           INSERT INTO FAC_DOCUM_SUBAC
                                       (OID_DOCU_SUBA, SOCI_OID_SOCI,
                                        SBAC_OID_SBAC, TIDO_OID_TIPO_DOCU,
                                        VAL_ULTI_NUME_DOCU_LEGA,
                                        VAL_LIMI_NUME_DOCU_LEGA,
                                        VAL_ULTI_EJER_DOCU_INTE,
                                        VAL_SERI_DOCU_LEGA,
                                        VAL_ULTI_NUME_DOCU_INTE,
                                        PAIS_OID_PAIS
                                       )
                                VALUES (FAC_DOSU_SEQ.NEXTVAL, SOCI,
                                        SBAC, I.TIDO_OID_TIPO_DOCU,
                                        RANGO_INI_NUME,
                                        RANGO_FIN_NUME,
                                        EJERCICIO,
                                        RANGO_SERIE,
                                        0,
                                        PAIS
                                       );

                           DELETE FAC_RANGO_NUMER_OFICI WHERE OID_RANG = RANGO_OID;

                           NUME_DOCU_LEGA := RANGO_INI_NUME;
                           LIMI_DOCU_LEGA := RANGO_FIN_NUME;
                           NUME_DOCU_INTE := 0;
                           SERIE_DOC_LEGAL := RANGO_SERIE;
                           OK := 1;
                        END IF;
                     END;
                  ELSE
                     IF (EJERCICIO <> EJE_DOC) THEN
					    UPDATE FAC_DOCUM_SUBAC SET VAL_ULTI_EJER_DOCU_INTE = EJERCICIO WHERE OID_DOCU_SUBA = OID_DOC_SBAC;
                     END IF;

                     IF (NUME_DOCU_LEGA < LIMI_DOCU_LEGA) THEN
                        BEGIN
                           UPDATE FAC_DOCUM_SUBAC
                           SET VAL_ULTI_NUME_DOCU_LEGA = VAL_ULTI_NUME_DOCU_LEGA + 1,
                               VAL_ULTI_NUME_DOCU_INTE = VAL_ULTI_NUME_DOCU_INTE + 1
                            WHERE OID_DOCU_SUBA = OID_DOC_SBAC;

                           OK := 1;
                        END;
                     ELSE
                        BEGIN
                           SELECT OID_RANG,
						   		  VAL_FINA_RANG_NUME,
                                  VAL_INIC_RANG_NUME,
								  VAL_SERI_DOCU
                             INTO RANGO_OID,
							 	  RANGO_FIN_NUME,
                                  RANGO_INI_NUME,
								  RANGO_SERIE
                             FROM (SELECT RNO.OID_RANG,
                                          RNO.VAL_FINA_RANG_NUME,
                                          RNO.VAL_INIC_RANG_NUME,
                                          RNO.VAL_SERI_DOCU,
                                          RNO.NUM_ORDE_UTIL
                                   FROM FAC_RANGO_NUMER_OFICI RNO
                                   WHERE RNO.PAIS_OID_PAIS = PAIS
                                     AND RNO.SBAC_OID_SBAC = SBAC
                                     AND RNO.SOCI_OID_SOCI = SOCI
                                     AND RNO.TIDO_OID_TIPO_DOCU = I.TIDO_OID_TIPO_DOCU
                                   ORDER BY NUM_ORDE_UTIL)
                            WHERE ROWNUM = 1;

                           IF (RANGO_OID IS NOT NULL) THEN
                              UPDATE FAC_DOCUM_SUBAC
                              SET VAL_ULTI_NUME_DOCU_LEGA = RANGO_INI_NUME + 1,
                                  VAL_ULTI_NUME_DOCU_INTE = VAL_ULTI_NUME_DOCU_INTE + 1,
                                  VAL_LIMI_NUME_DOCU_LEGA = RANGO_FIN_NUME,
                                  VAL_SERI_DOCU_LEGA = RANGO_SERIE
                              WHERE OID_DOCU_SUBA = OID_DOC_SBAC;

                              DELETE FAC_RANGO_NUMER_OFICI WHERE OID_RANG = RANGO_OID;

                              NUME_DOCU_LEGA := RANGO_INI_NUME;
                              LIMI_DOCU_LEGA := RANGO_FIN_NUME;
                              NUME_DOCU_INTE := 0;
                              SERIE_DOC_LEGAL := RANGO_SERIE;
                              OK := 1;
                           END IF;
                        END;
                     END IF;
                  END IF;
               ELSE
                  IF (NUME_DOCU_INTE IS NULL) THEN
                     INSERT INTO FAC_DOCUM_SUBAC
                                 (OID_DOCU_SUBA, SOCI_OID_SOCI,
                                  SBAC_OID_SBAC, TIDO_OID_TIPO_DOCU,
                                  VAL_ULTI_NUME_DOCU_LEGA,
                                  VAL_LIMI_NUME_DOCU_LEGA,
                                  VAL_ULTI_EJER_DOCU_INTE,
                                  VAL_SERI_DOCU_LEGA,
                                  VAL_ULTI_NUME_DOCU_INTE, PAIS_OID_PAIS
                                 )
                          VALUES (FAC_DOSU_SEQ.NEXTVAL, SOCI,
                                  SBAC, I.TIDO_OID_TIPO_DOCU,
                                  0,
                                  0,
                                  EJERCICIO,
                                  'MONOPAGINA',
                                  1, PAIS
                                 );

                     NUME_DOCU_INTE := 0;
                  ELSE
                     UPDATE FAC_DOCUM_SUBAC
                     SET VAL_ULTI_NUME_DOCU_INTE = VAL_ULTI_NUME_DOCU_INTE + 1
                     WHERE OID_DOCU_SUBA = OID_DOC_SBAC;
                  END IF;

                  OK := 1;
               END IF;

               IF (OK = 1) THEN
                  BEGIN
                     IF (I.ICTP_OID_TIPO_PROG IS NOT NULL) THEN
                     SELECT OID_CABE
                       INTO CABE_FAC
                       FROM (SELECT   DCC.OID_CABE
                                 FROM FAC_DOCUM_CONTA_CABEC DCC
                                WHERE DCC.SOCA_OID_SOLI_CABE = I.SOCA_OID_SOLI_CABE
                                  AND DCC.TIDO_OID_TIPO_DOCU = I.TIDO_OID_TIPO_DOCU
                                  AND DCC.ICTP_OID_TIPO_PROG = I.ICTP_OID_TIPO_PROG
                                  AND DCC.NUM_DOCU_CONT_INTE IS NULL
                             ORDER BY DCC.OID_CABE)
                      WHERE ROWNUM = 1;
                    ELSE
                    SELECT OID_CABE
                       INTO CABE_FAC
                       FROM (SELECT   DCC.OID_CABE
                                 FROM FAC_DOCUM_CONTA_CABEC DCC
                                WHERE DCC.SOCA_OID_SOLI_CABE = I.SOCA_OID_SOLI_CABE
                                  AND DCC.TIDO_OID_TIPO_DOCU = I.TIDO_OID_TIPO_DOCU
                                  AND DCC.ICTP_OID_TIPO_PROG IS NULL
                                  AND DCC.NUM_DOCU_CONT_INTE IS NULL
                             ORDER BY DCC.OID_CABE)
                      WHERE ROWNUM = 1;
                    END IF;
                     IF (IND_MONO_PAGI = 1) THEN
                        UPDATE FAC_DOCUM_CONTA_CABEC
                           SET NUM_DOCU_CONT_INTE = NUME_DOCU_INTE + 1,
                               VAL_EJER_DOCU_INTE = EJERCICIO,
                               VAL_SERI_DOCU_LEGA = SERIE_DOC_LEGAL
                         WHERE OID_CABE = CABE_FAC;

                        UPDATE FAC_REGIS_UNICO_VENTA
                           SET NUM_DOCU_CONT_INTE = NUME_DOCU_INTE + 1,
                               VAL_EJER_DOCU_INTE = EJERCICIO,
                               VAL_SERI_DOCU_LEGA = SERIE_DOC_LEGAL
                         WHERE DCCA_OID_CABE = CABE_FAC;
                     ELSE
                        UPDATE FAC_DOCUM_CONTA_CABEC
                           SET NUM_DOCU_CONT_INTE = NUME_DOCU_INTE + 1,
                               NUM_DOCU_LEGA = NUME_DOCU_LEGA + 1,
                               VAL_EJER_DOCU_INTE = EJERCICIO,
                               VAL_SERI_DOCU_LEGA = SERIE_DOC_LEGAL
                         WHERE OID_CABE = CABE_FAC;

                        UPDATE FAC_REGIS_UNICO_VENTA
                           SET NUM_DOCU_CONT_INTE = NUME_DOCU_INTE + 1,
                               VAL_NUME_DOCU_LEGA = NUME_DOCU_LEGA + 1,
                               VAL_EJER_DOCU_INTE = EJERCICIO,
                               VAL_SERI_DOCU_LEGA = SERIE_DOC_LEGAL
                         WHERE DCCA_OID_CABE = CABE_FAC;
                     END IF;

					 IF (IND_MONO_PAGI = 1) THEN
                     	POSI := DBMS_LOB.INSTR (BL2, UTL_RAW.CAST_TO_RAW ('$$$$$$$$$$$$$')) - 1;
					 ELSE
                      	POSI := DBMS_LOB.INSTR (BL2, UTL_RAW.CAST_TO_RAW ('$$$$$$$$$$-$$$$$$$$$$')) - 1;
					 END IF;

                     IF (POSI > 0) THEN
                        IF (POSI > 32767) THEN
                           BL1 := DBMS_LOB.SUBSTR (BL2, 32767, 1);
                           DBMS_LOB.APPEND (BL1, DBMS_LOB.SUBSTR (BL2, (POSI - 32767), 32768));
                        ELSE
                           BL1 := DBMS_LOB.SUBSTR (BL2, POSI, 1);
                        END IF;

					    IF (IND_MONO_PAGI = 1) THEN
	                        INI := POSI + 14;
	                        DBMS_LOB.APPEND(BL1, UTL_RAW.CAST_TO_RAW(LPAD (TO_CHAR (NUME_DOCU_INTE + 1),13)));
						ELSE
	                        INI := POSI + 22;
	                        DBMS_LOB.APPEND(BL1, UTL_RAW.CAST_TO_RAW(LPAD (SERIE_DOC_LEGAL || '-' || TO_CHAR(NUME_DOCU_LEGA + 1), 21)));
						END IF;
                        POSFIN := (LEN - INI);

                        IF (POSFIN > 32767) THEN
                           DBMS_LOB.APPEND (BL1, DBMS_LOB.SUBSTR (BL2, 32767, INI));
                           DBMS_LOB.APPEND (BL1, DBMS_LOB.SUBSTR (BL2, (POSFIN - 32767), (INI + 32767)));
                        ELSE
                           DBMS_LOB.APPEND (BL1, DBMS_LOB.SUBSTR (BL2, POSFIN, INI));
                        END IF;

                        BL2 := BL1;
                        BL1 := UTL_RAW.CAST_TO_RAW (' ');
                     END IF;
                  END;
               ELSE
                  RAISE INVALID_NUM_DOC;
               END IF;
            END;
         END LOOP;

         UPDATE GEN_DOCUM_IMPRI
            SET VAL_BUFF = BL2
          WHERE OID_DOCU_IMPR = I.GDIM_OID_DOCU_IMPR;
      END;
   END LOOP;

   --PRE_GENE_ESTA_CV_ACUM;
   CCC_GENE_NUME_LINE_DETA_CAB;
EXCEPTION
   WHEN INVALID_NUM_DOC THEN
      RAISE_APPLICATION_ERROR (-20996, 'Error al Calcular los numeros de documentos');
END;
/

