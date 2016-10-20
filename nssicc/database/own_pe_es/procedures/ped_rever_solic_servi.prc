CREATE OR REPLACE PROCEDURE PED_REVER_SOLIC_SERVI
(pnOidPais      IN  NUMBER,
 psFechaInicio  IN  VARCHAR2,
 salida         OUT VARCHAR2)
IS
  cursor cur_soli is
    SELECT cab.OID_SOLI_CABE
      FROM PED_SOLIC_CABEC cab, PED_TIPO_SOLIC_REVER_SOLIC rev
     WHERE cab.pais_oid_pais = pnOidPais
       AND cab.tspa_oid_tipo_soli_pais = rev.tspa_oid_tipo_soli_pais
       AND trunc(cab.fec_prog_fact) >= trunc(to_date(psFechaInicio, 'dd/MM/yyyy'))
       AND cab.grpr_oid_grup_proc = 4;

/*  cursor cur_nume_soli is
    SELECT DISTINCT det.MVAL_OID_MOVI_ALMA
      FROM PED_SOLIC_CABEC cab, PED_TIPO_SOLIC_REVER_SOLIC rev, BEL_MOVIM_ALMAC_DETAL det
     WHERE cab.pais_oid_pais = pnOidPais
       AND cab.tspa_oid_tipo_soli_pais = rev.tspa_oid_tipo_soli_pais
       AND Cab.Val_Nume_Soli = det.NUM_DOCU_REFE
       AND trunc(cab.fec_prog_fact) >= trunc(to_date(psFechaInicio, 'dd/MM/yyyy'))
       AND cab.grpr_oid_grup_proc = 4;
*/
  TYPE t_oid_solicitud     IS TABLE OF PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
--  TYPE t_oid_movalmacen    IS TABLE OF BEL_MOVIM_ALMAC_DETAL.MVAL_OID_MOVI_ALMA%TYPE;
  v_oid_solicitud           t_oid_solicitud;
--  v_oid_movalmacen          t_oid_movalmacen;*/

  rows             NATURAL        := 2000;   -- Number of rows to process at a time
  j                BINARY_INTEGER := 0;
  v_row_count      NUMBER         := 0;
BEGIN

/*  OPEN cur_nume_soli;
    LOOP
    FETCH cur_nume_soli BULK COLLECT INTO v_oid_movalmacen
    LIMIT rows;
    EXIT WHEN v_row_count = cur_nume_soli%ROWCOUNT;
    v_row_count := cur_nume_soli%ROWCOUNT;

     --  Borrar detalles BEL
    FORALL j IN 1..v_oid_movalmacen.count
      DELETE FROM BEL_MOVIM_ALMAC_DETAL
       WHERE MVAL_OID_MOVI_ALMA = v_oid_movalmacen(j);

    -- Borrar cabecera BEL
    FORALL j IN 1..v_oid_movalmacen.count
      DELETE FROM BEL_MOVIM_ALMAC_CABEC
       WHERE OID_MOVI_ALMA = v_oid_movalmacen(j);

    END LOOP;

  CLOSE cur_nume_soli;*/

  v_row_count := 0;

  OPEN cur_soli;
    LOOP
    FETCH cur_soli BULK COLLECT INTO v_oid_solicitud
    LIMIT rows;
    EXIT WHEN v_row_count = cur_soli%ROWCOUNT;
    v_row_count := cur_soli%ROWCOUNT;

    -- Actualizamos las solicitudes de servicio a GP3
    FORALL j IN 1..v_oid_solicitud.count
      UPDATE ped_solic_cabec
         SET grpr_oid_grup_proc = 3,
             fec_repo_falt      = null,
             proc_oid_proc      = (select proc_oid_proc from ped_secue_proce where grpr_oid_grup_proc = 3
                                      and tspa_oid_tipo_soli_pais = ped_solic_cabec.tspa_oid_tipo_soli_pais)
       WHERE OID_SOLI_CABE = v_oid_solicitud(j);

     --Actualizamos las posiciones de las solicitudes de servicio
    FORALL j IN 1..v_oid_solicitud.count
      UPDATE ped_solic_posic
         SET num_unid_compr = num_unid_por_aten
       WHERE SOCA_OID_SOLI_CABE = v_oid_solicitud(j);

     --Actualizamos las posiciones de las solicitudes de servicio
    FORALL j IN 1..v_oid_solicitud.count
    DELETE FROM INC_ARTIC_PREMI_ALTER where bofa_oid_bols_falt in
    (
      SELECT OID_BOLS_FALT FROM INC_BOLSA_FALTA
       WHERE SOPO_OID_SOLI_POSI
             IN (SELECT OID_SOLI_POSI FROM PED_SOLIC_POSIC WHERE SOCA_OID_SOLI_CABE = v_oid_solicitud(j))
    );

    FORALL j IN 1..v_oid_solicitud.count
      DELETE FROM INC_BOLSA_FALTA
       WHERE SOPO_OID_SOLI_POSI
             IN (SELECT OID_SOLI_POSI FROM PED_SOLIC_POSIC WHERE SOCA_OID_SOLI_CABE = v_oid_solicitud(j));


    END LOOP;

  CLOSE cur_soli;


END;
/

