CREATE OR REPLACE PROCEDURE GEN_ACTU_STAT_MONI IS
--*************************************************************************
--Descripcion        : Actualiza la tabla GEN_STATU_MONIT
--Fecha Creacion     : 20/02/2008
--Autor              : Natalia Watanabe
--***************************************************************************
BEGIN
  insert into OWN_PE_ES.GEN_STATU_MONIT
  SELECT  SYSDATE, SUM(VAL_ACUM_GP1) AS GP1, SUM(VAL_ACUM_GP2) AS GP2, SUM(VAL_ACUM_GP3) AS GP3, SUM(VAL_ACUM_GP4) AS GP4, SUM(VAL_ACUM_GP5) AS GP5
  FROM OWN_PE_ES.PED_SOLIC_CABEC_ACUM
  WHERE TRUNC(FEC_PROC) = TRUNC(SYSDATE-1)
  AND tspa_oid_tipo_soli_pais = (SELECT OID_TIPO_SOLI_PAIS FROM OWN_PE_ES.PED_TIPO_SOLIC_PAIS WHERE TSOL_OID_TIPO_SOLI = 2054)
  GROUP BY SYSDATE;
commit;

END GEN_ACTU_STAT_MONI;
/

