CREATE OR REPLACE PROCEDURE "INC_PR_CARGA_CLIEN_PREMI_PERIO"
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

CURSOR c_clien_premi_perio(oidPais NUMBER, oidPeriodo NUMBER, codigoPeriodo VARCHAR2, oidCanal NUMBER, oidMarca NUMBER) IS
SELECT oidPais,
       p_codigoPais,
       oidPeriodo,
    codigoPeriodo,
       MC.OID_CLIE,
       MC.COD_CLIE,
       ZON.OID_ZONA,
       ZON.COD_ZONA,
       ZON.DES_ZONA,
    SYSDATE
  FROM MAE_CLIEN_UNIDA_ADMIN C,
       MAE_CLIEN MC,
       CRA_PERIO CP1,
       (SELECT FEC_INIC,
               FEC_FINA
          FROM CRA_PERIO
         WHERE OID_PERI = oidPeriodo) PER_REF,
       ZON_TERRI_ADMIN TA,
       ZON_SECCI SEXY,
       ZON_ZONA ZON
 WHERE 1 = 1
   AND MC.OID_CLIE = C.CLIE_OID_CLIE
   AND C.PERD_OID_PERI_INI = CP1.OID_PERI
   AND C.IND_ACTI = 1
   AND C.CLIE_OID_CLIE IN (
          SELECT   CLIE_OID_CLIE
              FROM INC_CANDI_GANAD GAN,
                   INC_CONCU_PARAM_GENER C,
                   INC_PARAM_GENER_PREMI PRE,
                   (SELECT FEC_INIC,
                           FEC_FINA
                      FROM CRA_PERIO
                     WHERE OID_PERI = oidPeriodo) PER_REF
             WHERE GAN.COPA_OID_PARA_GRAL = C.OID_PARA_GRAL
               AND PRE.COPA_OID_PARA_GRAL = C.OID_PARA_GRAL
               AND (PRE.PERD_OID_PERI = oidPeriodo OR PRE.PERD_OID_PERI IS NULL)
               AND GAN.VAL_REQU_PREM_SUPE <> 1
               AND GAN.BINC_OID_BASE_INCU IS NULL
               AND CLIE_OID_CLIE NOT IN (SELECT CLIE_OID_CLIE
                                           FROM INC_DESCA
                                          WHERE INC_DESCA.COPA_OID_PARA_GRAL = C.OID_PARA_GRAL)
               AND C.MARC_OID_MARC = oidMarca
               AND C.CANA_OID_CANA = oidCanal
               AND C.PAIS_OID_PAIS = oidPais
               AND C.DIRI_OID_DIRI = 1
               AND ((GAN.PERD_OID_PERI_EVAL IS NULL) OR (SELECT FEC_FINA
                                                           FROM CRA_PERIO
                                                          WHERE OID_PERI = GAN.PERD_OID_PERI_EVAL) <= PER_REF.FEC_FINA)
               AND C.IND_ACTI = 1
          GROUP BY CLIE_OID_CLIE)
   AND SEXY.ZZON_OID_ZONA = ZON.OID_ZONA
   AND TA.ZSCC_OID_SECC = SEXY.OID_SECC
   AND TA.OID_TERR_ADMI = C.ZTAD_OID_TERR_ADMI
   AND PER_REF.FEC_INIC >= CP1.FEC_INIC
   AND ((C.PERD_OID_PERI_FIN IS NULL) OR (SELECT FEC_FINA
                                            FROM CRA_PERIO
                                           WHERE OID_PERI = C.PERD_OID_PERI_FIN) <= PER_REF.FEC_FINA);


TYPE clien_premi_perio_t IS TABLE OF INC_CLIEN_PREMI_PERIO%ROWTYPE INDEX BY BINARY_INTEGER;
clien_premi_perio clien_premi_perio_t; -- In-memory table
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
  
  -- Eliminamos la informacion en la tabla INC_CLIEN_PREMI_PERIO para el periodo 
  -- enviado como parametro en el store procedure
  DELETE FROM INC_CLIEN_PREMI_PERIO
  WHERE OID_PERI = l_oidPeriodo;
  
  -- Insertamos la información en base al query que obtiene todos los clientes
  -- habilitados de recibir premios en el periodo
  OPEN c_clien_premi_perio(l_oidPais, l_oidPeriodo, l_codigoPeriodo, l_oidCanal, l_oidMarca);
  LOOP
    -- Bulk collect 
    FETCH c_clien_premi_perio BULK COLLECT
    INTO clien_premi_perio LIMIT rows;
    EXIT WHEN clien_premi_perio.COUNT = 0;
  
    FORALL i IN clien_premi_perio.FIRST .. clien_premi_perio.LAST
    INSERT INTO INC_CLIEN_PREMI_PERIO VALUES clien_premi_perio(i);
  
  END LOOP;
  CLOSE c_clien_premi_perio;
  COMMIT;

 EXCEPTION
 WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_CARGA_CLIEN_PREMI_PERIO: '||substr(sqlerrm,1,250));
END INC_PR_CARGA_CLIEN_PREMI_PERIO;
/

