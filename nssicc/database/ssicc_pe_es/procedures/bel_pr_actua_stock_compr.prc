CREATE OR REPLACE PROCEDURE "BEL_PR_ACTUA_STOCK_COMPR"
AS

CURSOR c_stocks(p_oidPeriodo NUMBER) IS
select x.prod_oid_prod,
       x.cod_sap,
       x.val_stoc_inic,
       x.val_sald,
       x.num_unid_compr,
       x.val_stoc_inic - x.num_unid_compr val_ok
from (
select stock.prod_oid_prod,
       stock.cod_sap,
       stock.val_cant val_stoc_inic,
       stock.val_sald,
       nvl(pedidos.num_unid_compr, 0) num_unid_compr
from (
select cab.pais_oid_pais,
       det.almc_oid_alma_sali,
       det.prod_oid_prod,
       mp.cod_sap,
       det.val_cant,
       bs.val_sald
from bel_movim_almac_cabec cab,
     bel_movim_almac_detal det,
     mae_produ mp,
     bel_stock bs
where cab.oid_movi_alma = det.mval_oid_movi_alma
and det.prod_oid_prod = mp.oid_prod
and mp.oid_prod = bs.prod_oid_prod
and bs.almc_oid_alma = 2001 -- Almacen VD
and bs.esme_oid_esta_merc = 2001 -- Libre Disponibilidad
and cab.n_movimiento = (
                        select max(cab.n_movimiento) numeroMovimiento
                        from bel_movim_almac_cabec cab
                        where cab.almc_oid_alma_2 = 2001 -- Almacen VD
                        and cab.val_obse like '%SAM6%'
                       ) -- Nro Movimiento
order by val_sald
) stock,
(
select psp.prod_oid_prod,
       sum(psp.num_unid_compr) num_unid_compr
from ped_solic_cabec psc,
     ped_solic_posic psp,
     ped_tipo_solic_pais ptsp,
     ped_tipo_solic pts
where psc.oid_soli_cabe = psp.soca_oid_soli_cabe
and psc.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
and ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
and nvl(pts.ind_soli_nega, 0) = 0
and psc.perd_oid_peri = p_oidPeriodo -- OID Periodo
and psc.grpr_oid_grup_proc = 4 -- GP4
and psp.espo_oid_esta_posi != 2 -- Detalles correctos
and psp.num_unid_compr > 0
group by psp.prod_oid_prod
) pedidos
where stock.prod_oid_prod = pedidos.prod_oid_prod (+)
) x
where x.val_stoc_inic - x.num_unid_compr != x.val_sald
and x.val_stoc_inic - x.num_unid_compr > 0; -- Evitamos cantidades negativas

TYPE t_prod_oid_prod  IS TABLE OF bel_stock.prod_oid_prod%TYPE;
TYPE t_cod_sap        IS TABLE OF mae_produ.cod_sap%TYPE;
TYPE t_val_stoc_inic  IS TABLE OF bel_stock.val_sald%TYPE;
TYPE t_val_sald       IS TABLE OF bel_stock.val_sald%TYPE;
TYPE t_num_unid_compr IS TABLE OF bel_stock.val_sald%TYPE;
TYPE t_val_ok         IS TABLE OF bel_stock.val_sald%TYPE;

v_prod_oid_prod     t_prod_oid_prod;
v_cod_sap           t_cod_sap;
v_val_stoc_inic     t_val_stoc_inic;
v_val_sald          t_val_sald;
v_num_unid_compr    t_num_unid_compr;
v_val_ok            t_val_ok;

rows NATURAL        := 500;   -- Numero de filas a procesar a la vez
i    BINARY_INTEGER := 0;

l_codigoPeriodo         VARCHAR2(6);
l_oidPeriodo            NUMBER;

BEGIN

    -- Obtenemos el valor del periodo vigente
    SELECT COD_PERI
    INTO l_codigoPeriodo
    FROM BAS_CTRL_FACT
    WHERE STA_CAMP = 0
    AND IND_CAMP_ACT = 1;

    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(l_codigoPeriodo);

    OPEN c_stocks(l_oidPeriodo);
    LOOP
        FETCH c_stocks BULK COLLECT INTO
                        v_prod_oid_prod,
                        v_cod_sap,
                        v_val_stoc_inic,
                        v_val_sald,
                        v_num_unid_compr,
                        v_val_ok  LIMIT rows;
        EXIT WHEN v_prod_oid_prod.count = 0;

        FORALL i IN 1..v_prod_oid_prod.count
        UPDATE bel_stock bs
           SET bs.val_sald = v_val_ok(i)
         WHERE bs.prod_oid_prod = v_prod_oid_prod(i)
         AND bs.almc_oid_alma = 2001 -- Almacen de VD
         AND bs.esme_oid_esta_merc = 2001; -- Estado de Libre Disposicion

    END LOOP;
    CLOSE c_stocks;

END;
/

