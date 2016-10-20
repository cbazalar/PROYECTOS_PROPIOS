CREATE OR REPLACE PROCEDURE "IMP_PR_GENER_NOTA_CREDI_MASIV" AS
CURSOR c_numerosBoleta IS
SELECT VAL_NUME_BOLE, NUM_DOCU_CONT_INTE
FROM IMP_TMP_NUME_BOLET_DESPA;
r_numerosBoleta c_numerosBoleta%ROWTYPE;
l_mensajeError VARCHAR2(1000);
BEGIN
  -- LOOP
  OPEN c_numerosBoleta;
  LOOP
  BEGIN
   FETCH c_numerosBoleta INTO r_numerosBoleta;
   EXIT WHEN c_numerosBoleta%NOTFOUND;
   IMP_PR_GENER_NOTA_CREDI(r_numerosBoleta.VAL_NUME_BOLE, r_numerosBoleta.NUM_DOCU_CONT_INTE);
  EXCEPTION
    WHEN OTHERS THEN
     l_mensajeError:='ERROR AL GENERAR LA NOTA DE CREDITO CON NUM. DOC. INT. ' || TO_CHAR(r_numerosBoleta.NUM_DOCU_CONT_INTE);
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);
  END;
  END LOOP;
  CLOSE c_numerosBoleta;
END ;
/

