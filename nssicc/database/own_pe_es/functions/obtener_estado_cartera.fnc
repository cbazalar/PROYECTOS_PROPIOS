CREATE OR REPLACE FUNCTION "OBTENER_ESTADO_CARTERA" (OIDMVCC IN NUMBER) RETURN NUMBER
IS
pragma AUTONOMOUS_TRANSACTION;
salida number(1);
begin
select  case when count(compro.OID_COMP_PAGO) > 0 then  7
   when count(gestion.OID_GEST_COBR) > 0 then 2
   else 3
  end into salida
from COB_GESTI_COBRA gestion, COB_COMPR_PAGO compro ,
(select asi.OID_ASIG_COBR, mcc.CLIE_OID_CLIE, mcc.PERD_OID_PERI,
  crono.ETDE_OID_ETAP_DEUD from
  COB_CRONO_COBRA crono, COB_ASIGN_COBRA asi,
  ccc_movim_cuent_corri mcc
  WHERE
    mcc.OID_MOVI_CC = OIDMVCC AND
    mcc.OID_MOVI_CC =  asi.MVCC_OID_MOVI_CC
    AND asi.CRCO_OID_CRON_COBR = crono.OID_CRON_COBR
    AND asi.HICC_OID_HIST_COMI_COBR IS NULL
	AND asi.ESAS_OID_ESTA_ASIG = 6 --Abierta
) asi2
where
asi2.CLIE_OID_CLIE = gestion.CLIE_OID_CLIE(+)
AND asi2.PERD_OID_PERI = gestion.PERD_OID_PERI(+)
AND asi2.ETDE_OID_ETAP_DEUD = gestion.ETDE_OID_ETAPA_DEUDA(+)
AND gestion.OID_GEST_COBR = compro.GECO_OID_GEST_COBR (+)
group by asi2.OID_ASIG_COBR;
RETURN salida;
end;
/

