CREATE OR REPLACE PROCEDURE "INC_PR_PREV_CIERR_ZONA"
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

CURSOR c_activ_clien_perio(oidPais NUMBER, oidPeriodo NUMBER) IS
SELECT INC_SEQ_PROC_CLIE_PERI.nextval,
       A.OID_PAIS PAIS_OID_PAIS,
    A.OID_CLIE CLIE_OID_CLIE,
    A.COD_PERI,
    C.NUM_LOTE,
    '0' IND_PROC,
    SYSDATE FEC_DIGI,
    NULL FEC_MODI
  FROM INC_CLIEN_PREMI_PERIO A, 
       MAE_CLIEN_DATOS_ADICI B,
    BAS_CTRL_FACT C
 WHERE A.OID_CLIE = B.CLIE_OID_CLIE
   AND A.COD_PAIS = C.COD_PAIS
   AND A.COD_PERI = C.COD_PERI
   AND B.IND_ACTI = 0 -- Inactivas
   AND A.OID_PAIS = oidPais
   AND A.OID_PERI = oidPeriodo;

TYPE activ_clien_perio_t IS TABLE OF INC_PROCE_CLIEN_PERIO%ROWTYPE INDEX BY BINARY_INTEGER;
activ_clien_perio activ_clien_perio_t; -- In-memory table

CURSOR c_activ_bloqu_perio(oidPais NUMBER, oidPeriodo NUMBER) IS
SELECT INC_SEQ_PROC_BLOQ_PERI.nextval,
       A.OID_PAIS PAIS_OID_PAIS,
    A.OID_CLIE CLIE_OID_CLIE,
    B.OID_BLOQ CLBL_OID_BLOQ,
    A.COD_PERI,
    C.NUM_LOTE,
    '0' IND_PROC,
    SYSDATE FEC_DIGI,
    NULL FEC_MODI
  FROM INC_CLIEN_PREMI_PERIO A, 
       MAE_CLIEN_BLOQU B,
    BAS_CTRL_FACT C
 WHERE A.OID_CLIE = B.CLIE_OID_CLIE
   AND A.COD_PAIS = C.COD_PAIS
   AND A.COD_PERI = C.COD_PERI
   AND B.FEC_DESB IS NULL -- Bloqueados
   AND A.OID_PAIS = oidPais
   AND A.OID_PERI = oidPeriodo;
   
TYPE activ_bloqu_perio_t IS TABLE OF INC_PROCE_BLOQU_PERIO%ROWTYPE INDEX BY BINARY_INTEGER;
activ_bloqu_perio activ_bloqu_perio_t; -- In-memory table
   
rows        NATURAL := 1000; -- Number of rows to process at a time

BEGIN

  -- Si el periodo es null tomamos la campaña activa
  IF p_codigoPeriodo IS NULL THEN
     SELECT COD_PERI 
  INTO l_codigoPeriodo
  FROM BAS_CTRL_FACT
  WHERE COD_PAIS = p_codigoPais
  AND STA_CAMP = 0
  AND IND_CAMP_ACT = 1;
  ELSE 
    l_codigoPeriodo := p_codigoPeriodo;
  END IF;
  
  -- Obtenemos los oids a usar
  l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
  l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(l_codigoPeriodo, l_oidMarca, l_oidCanal);
  
  -- Insertamos la información en base al query que obtiene todos los clientes
  -- habilitados de recibir premios en el periodo
  OPEN c_activ_clien_perio(l_oidPais, l_oidPeriodo);
  LOOP
    -- Bulk collect 
    FETCH c_activ_clien_perio BULK COLLECT
    INTO activ_clien_perio LIMIT rows;
    EXIT WHEN activ_clien_perio.COUNT = 0;
    
    -- Actualizamos la tabla de datos adicionales
 FOR i IN activ_clien_perio.FIRST .. activ_clien_perio.LAST loop
       UPDATE MAE_CLIEN_DATOS_ADICI
       SET    IND_ACTI = 1 -- Activamos a la consultora
       WHERE CLIE_OID_CLIE = activ_clien_perio(i).CLIE_OID_CLIE;
    END LOOP;

 -- Insertamos los registros en la tabla de control
    FORALL i IN activ_clien_perio.FIRST .. activ_clien_perio.LAST
    INSERT INTO INC_PROCE_CLIEN_PERIO VALUES activ_clien_perio(i);
  
  END LOOP;
  CLOSE c_activ_clien_perio;

  -- Insertamos la información en base al query que obtiene todos los clientes
  -- habilitados de recibir premios en el periodo
  OPEN c_activ_bloqu_perio(l_oidPais, l_oidPeriodo);
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
  
  COMMIT;
  
 EXCEPTION
 WHEN OTHERS THEN
      ROLLBACK;
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_PREV_CIERR_ZONA: '||substr(sqlerrm,1,250));
END INC_PR_PREV_CIERR_ZONA;
/

