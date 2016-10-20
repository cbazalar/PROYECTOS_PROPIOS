CREATE OR REPLACE PROCEDURE "FAC_REFRE_ARBOL_IMPUESTOS"
(IDSPOOL IN VARCHAR2) AS
BEGIN
         DBMS_SNAPSHOT.REFRESH(
          LIST                 => 'MV_ARBOL_IMPUE_UBIGE'
        ,PUSH_DEFERRED_RPC    => TRUE
          ,REFRESH_AFTER_ERRORS => FALSE
          ,PURGE_OPTION         => 1
         ,PARALLELISM          => 0
         ,ATOMIC_REFRESH       => TRUE
         ,NESTED               => FALSE);
END;
/

