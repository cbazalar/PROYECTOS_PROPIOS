CREATE OR REPLACE PROCEDURE "PRI_PR_BLOQU_CLIEN_PREMI"
(p_codigoPais IN VARCHAR2) AS

l_oidPais    NUMBER(12);

BEGIN

  -- Obtenemos los oids a usar
  l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);

  -- Actualizamos la fecha de desbloqueo de la tabla MAE_CLIEN_BLOQU
  UPDATE MAE_CLIEN_BLOQU X
  SET X.FEC_DESB = NULL
  WHERE EXISTS (
    SELECT NULL
    FROM INC_PROCE_BLOQU_PERIO A
    WHERE A.IND_PROC = '0'
      AND A.CLIE_OID_CLIE = X.CLIE_OID_CLIE
      AND A.CLBL_OID_BLOQ = X.OID_BLOQ
      AND A.PAIS_OID_PAIS = l_oidPais
  );

  -- Actualizamos las tablas de control
  UPDATE INC_PROCE_BLOQU_PERIO
  SET IND_PROC = '1',
      FEC_MODI = SYSDATE
  WHERE IND_PROC = '0'
  AND PAIS_OID_PAIS = l_oidPais;

 EXCEPTION
 WHEN OTHERS THEN
      ROLLBACK;
      RAISE_APPLICATION_ERROR(-20123, 'ERROR PRI_PR_BLOQU_CLIEN_PREMI: '||substr(sqlerrm,1,250));
END;
/

