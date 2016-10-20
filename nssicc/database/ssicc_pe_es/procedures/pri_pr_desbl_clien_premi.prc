CREATE OR REPLACE PROCEDURE "PRI_PR_DESBL_CLIEN_PREMI"
(p_codigoPais IN VARCHAR2,
 p_codigoPeriodo IN VARCHAR2 := NULL
) AS

l_oidPais    NUMBER(12);
l_oidPeriodo   NUMBER(12);
l_codigoPeriodo  VARCHAR2(6);
l_oidMarca    NUMBER(12);
l_oidCanal    NUMBER(12);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

CURSOR c_activ_bloqu_perio IS
SELECT INC_SEQ_PROC_BLOQ_PERI.NEXTVAL,
       MC.PAIS_OID_PAIS,
       MC.OID_CLIE CLIE_OID_CLIE,
       MCB.OID_BLOQ CLBL_OID_BLOQ,
       p_codigoPeriodo COD_PERI,
       (SELECT NUM_LOTE FROM BAS_CTRL_FACT WHERE COD_PAIS = p_codigoPais AND COD_PERI = p_codigoPeriodo) NUM_LOTE,
       '0' IND_PROC,
       SYSDATE FEC_DIGI,
       NULL FEC_MODI
FROM MAE_CLIEN MC,
     MAE_CLIEN_BLOQU MCB,
     MAE_TIPO_BLOQU MTB
WHERE MC.OID_CLIE = MCB.CLIE_OID_CLIE
  AND MCB.TIBQ_OID_TIPO_BLOQ = MTB.OID_TIPO_BLOQ
  AND MCB.FEC_DESB IS NULL -- Bloqueados
  AND MTB.COD_TIPO_BLOQ = 'BR' -- Bloqueo por Boleta de Recojo
  AND EXISTS (
   SELECT NULL
   FROM PRI_TARJE PT,
        PRI_PREMI PP
   WHERE PT.PAIS_COD_PAIS = PP.PAIS_COD_PAIS
     AND PT.NUM_TARJ = PP.TARJ_NUM_TARJ
     AND PP.STA_PREM = 'P' -- Despachado
     AND PT.PAIS_COD_PAIS = p_codigoPais
     AND PT.COD_CONS = MC.COD_CLIE
  );

TYPE activ_bloqu_perio_t IS TABLE OF INC_PROCE_BLOQU_PERIO%ROWTYPE INDEX BY BINARY_INTEGER;
activ_bloqu_perio activ_bloqu_perio_t; -- In-memory table

rows        NATURAL := 1000; -- Number of rows to process at a time

BEGIN

  -- Insertamos la información en base al query que obtiene todos los clientes
  -- habilitados de recibir premios en el periodo
  OPEN c_activ_bloqu_perio;
  LOOP
    -- Bulk collect
    FETCH c_activ_bloqu_perio BULK COLLECT
    INTO activ_bloqu_perio LIMIT rows;
    EXIT WHEN activ_bloqu_perio.COUNT = 0;

    -- Actualizamos la tabla de datos adicionales
    FOR i IN activ_bloqu_perio.FIRST .. activ_bloqu_perio.LAST loop
       UPDATE MAE_CLIEN_BLOQU
       SET    FEC_DESB = SYSDATE -- Desbloqueamos a la consultora
       WHERE CLIE_OID_CLIE = activ_bloqu_perio(i).CLIE_OID_CLIE
       AND   OID_BLOQ = activ_bloqu_perio(i).CLBL_OID_BLOQ;
    END LOOP;

    -- Insertamos los registros en la tabla de control
    FORALL i IN activ_bloqu_perio.FIRST .. activ_bloqu_perio.LAST
    INSERT INTO INC_PROCE_BLOQU_PERIO VALUES activ_bloqu_perio(i);

  END LOOP;
  CLOSE c_activ_bloqu_perio;

 EXCEPTION
 WHEN OTHERS THEN
      ROLLBACK;
      RAISE_APPLICATION_ERROR(-20123, 'ERROR PRI_PR_DESBL_CLIEN_PREMI: '||substr(sqlerrm,1,250));
END;
/

