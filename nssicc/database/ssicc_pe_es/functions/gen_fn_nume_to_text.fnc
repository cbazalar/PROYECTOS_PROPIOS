CREATE OR REPLACE FUNCTION "GEN_FN_NUME_TO_TEXT"
    (p_number NUMBER) RETURN VARCHAR2 AS
LANGUAGE JAVA NAME 'Numtotext.convertirLetras(int) return String';
/

