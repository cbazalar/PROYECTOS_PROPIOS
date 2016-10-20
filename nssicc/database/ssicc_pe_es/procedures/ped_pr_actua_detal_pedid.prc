CREATE OR REPLACE PROCEDURE "PED_PR_ACTUA_DETAL_PEDID"
AS

CURSOR c_update IS
SELECT B.COD_PAIS,
       B.COD_PERI,
       B.COD_CLIE,
       B.NUM_LOTE,
       B.COD_VENT,
       C.VAL_CODI_VENT_REEM
FROM  INT_SOLIC_CONSO_CABEC A,
      INT_SOLIC_CONSO_DETAL B,
      PED_TMP_CODIG_REEMP   C
WHERE A.COD_PAIS = B.COD_PAIS
  AND A.COD_PERI = B.COD_PERI
  AND A.COD_CLIE = B.COD_CLIE
  AND A.NUM_LOTE = B.NUM_LOTE
  AND B.COD_PAIS = C.COD_PAIS
  AND B.COD_PERI = C.COD_PERI
  AND B.COD_CLIE = C.COD_CLIE
  AND B.COD_VENT = C.VAL_CODI_VENT_ORIG
  AND A.IND_OCS_PROC = '0'
  AND A.IND_ERRO_REMP = '0'
  AND A.IND_PROC_GP2 = '0'
  AND NOT EXISTS (
  SELECT NULL FROM INT_SOLIC_CONSO_DETAL X
  WHERE X.COD_PAIS = A.COD_PAIS
    AND X.COD_PERI = A.COD_PERI
 AND X.COD_CLIE = A.COD_CLIE
 AND X.NUM_LOTE = A.NUM_LOTE
 AND X.COD_PAIS = C.COD_PAIS
 AND X.COD_PERI = C.COD_PERI
 AND X.COD_CLIE = C.COD_CLIE
 AND X.COD_VENT = C.VAL_CODI_VENT_REEM
  );

CURSOR c_delete IS
SELECT B.COD_PAIS,
       B.COD_PERI,
       B.COD_CLIE,
       B.NUM_LOTE,
       B.COD_VENT,
       C.VAL_CODI_VENT_REEM
FROM  INT_SOLIC_CONSO_CABEC A,
      INT_SOLIC_CONSO_DETAL B,
      PED_TMP_CODIG_REEMP   C
WHERE A.COD_PAIS = B.COD_PAIS
  AND A.COD_PERI = B.COD_PERI
  AND A.COD_CLIE = B.COD_CLIE
  AND A.NUM_LOTE = B.NUM_LOTE
  AND B.COD_PAIS = C.COD_PAIS
  AND B.COD_PERI = C.COD_PERI
  AND B.COD_CLIE = C.COD_CLIE
  AND B.COD_VENT = C.VAL_CODI_VENT_ORIG
  AND A.IND_OCS_PROC = '0'
  AND A.IND_ERRO_REMP = '0'
  AND A.IND_PROC_GP2 = '0'
  AND EXISTS (
  SELECT NULL FROM INT_SOLIC_CONSO_DETAL X
  WHERE X.COD_PAIS = A.COD_PAIS
    AND X.COD_PERI = A.COD_PERI
 AND X.COD_CLIE = A.COD_CLIE
 AND X.NUM_LOTE = A.NUM_LOTE
 AND X.COD_PAIS = C.COD_PAIS
 AND X.COD_PERI = C.COD_PERI
 AND X.COD_CLIE = C.COD_CLIE
 AND X.COD_VENT = C.VAL_CODI_VENT_REEM
  );

TYPE t_cod_pais IS TABLE OF INT_SOLIC_CONSO_DETAL.COD_PAIS%TYPE;
TYPE t_cod_peri IS TABLE OF INT_SOLIC_CONSO_DETAL.COD_PERI%TYPE;
TYPE t_cod_clie IS TABLE OF INT_SOLIC_CONSO_DETAL.COD_CLIE%TYPE;
TYPE t_num_lote IS TABLE OF INT_SOLIC_CONSO_DETAL.NUM_LOTE%TYPE;
TYPE t_cod_vent IS TABLE OF INT_SOLIC_CONSO_DETAL.NUM_LOTE%TYPE;
TYPE t_val_unid_dem IS TABLE OF INT_SOLIC_CONSO_DETAL.VAL_UNID_DEM%TYPE;

v_cod_pais t_cod_pais;
v_cod_peri t_cod_peri;
v_cod_clie t_cod_clie;
v_num_lote t_num_lote;
v_cod_vent t_cod_vent;
v_cod_vent_reem t_cod_vent;
v_val_unid_reem t_val_unid_dem;

v_val_unid_dem  INT_SOLIC_CONSO_DETAL.VAL_UNID_DEM%TYPE;

rows NATURAL        := 500;   -- Numero de filas a procesar a la vez
i    BINARY_INTEGER := 0;

BEGIN

-- Actualizamos aquellos que no tienen su valor de reemplazo
-- dentro de los codigos de venta ya ingresados

OPEN c_update;
LOOP
    FETCH c_update BULK COLLECT INTO
                            v_cod_pais,
                            v_cod_peri,
                            v_cod_clie,
                            v_num_lote,
                            v_cod_vent,
       v_cod_vent_reem LIMIT rows;
    EXIT WHEN v_cod_clie.count = 0;

    -- Bulk bind of data in memory table...
    FORALL i IN 1..v_cod_clie.count
    UPDATE INT_SOLIC_CONSO_DETAL detal
    SET    detal.cod_vent = v_cod_vent_reem(i),
           detal.usu_modi = 'ADMIN',
           detal.fec_modi = SYSDATE
    WHERE detal.cod_pais = v_cod_pais(i)
      AND detal.cod_peri = v_cod_peri(i)
      AND detal.cod_clie = v_cod_clie(i)
      AND detal.num_lote = v_num_lote(i)
      AND detal.cod_vent = v_cod_vent(i);

END LOOP;
CLOSE c_update;

OPEN c_delete;
LOOP
    FETCH c_delete BULK COLLECT INTO
                            v_cod_pais,
                            v_cod_peri,
                            v_cod_clie,
                            v_num_lote,
                            v_cod_vent,
       v_cod_vent_reem LIMIT rows;
    EXIT WHEN v_cod_clie.count = 0;

    FOR i IN v_cod_clie.FIRST .. v_cod_clie.LAST LOOP
    -- Obtenemos el numero de unidades
    -- del codigo de venta original
    SELECT VAL_UNID_DEM
    INTO v_val_unid_dem
    FROM INT_SOLIC_CONSO_DETAL
    WHERE COD_PAIS = v_cod_pais(i)
      AND COD_PERI = v_cod_peri(i)
   AND COD_CLIE = v_cod_clie(i)
   AND NUM_LOTE = v_num_lote(i)
   AND COD_VENT = v_cod_vent(i);

    -- Eliminamos el codigo de venta
    DELETE FROM INT_SOLIC_CONSO_DETAL
    WHERE COD_PAIS = v_cod_pais(i)
      AND COD_PERI = v_cod_peri(i)
   AND COD_CLIE = v_cod_clie(i)
   AND NUM_LOTE = v_num_lote(i)
   AND COD_VENT = v_cod_vent(i);

    -- Actualizamos el numero de unidades
    -- del codigo de venta de reemplazo que
    -- ya existia como detalle
       UPDATE INT_SOLIC_CONSO_DETAL detal
       SET    detal.val_unid_dem = detal.val_unid_dem + v_val_unid_dem,
              detal.usu_modi = 'ADMIN',
              detal.fec_modi = SYSDATE
       WHERE detal.cod_pais = v_cod_pais(i)
         AND detal.cod_peri = v_cod_peri(i)
         AND detal.cod_clie = v_cod_clie(i)
         AND detal.num_lote = v_num_lote(i)
         AND detal.cod_vent = v_cod_vent_reem(i);
 END LOOP;

END LOOP;
CLOSE c_delete;

COMMIT;

END;
/

