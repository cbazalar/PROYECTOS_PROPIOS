CREATE OR REPLACE PROCEDURE "IMP_PR_GENER_GUIA_REMIS_MASIV" AS
CURSOR c_numerosBoleta IS
SELECT VAL_NUME_BOLE
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
   IMP_PR_GENER_GUIA_REMIS(r_numerosBoleta.VAL_NUME_BOLE);
  EXCEPTION
    WHEN OTHERS THEN
     l_mensajeError:='ERROR AL GENERAR LA GUIA DE REMISION DEL NUMERO. ' || TO_CHAR(r_numerosBoleta.VAL_NUME_BOLE);
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError); 
  END;
  END LOOP;
  CLOSE c_numerosBoleta;
END ;
/

