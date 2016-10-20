CREATE OR REPLACE PROCEDURE "GEN_PR_LISTA_DIREC"
    (p_directorio VARCHAR2, p_patron VARCHAR2) AS
LANGUAGE JAVA NAME 'SystemUtils.list(java.lang.String,java.lang.String)';
/

