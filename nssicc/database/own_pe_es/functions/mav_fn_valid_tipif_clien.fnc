CREATE OR REPLACE FUNCTION "MAV_FN_VALID_TIPIF_CLIEN" (
   pnOidCliente         IN   NUMBER,
   pnOidDetalleMAV      IN   NUMBER
)
   RETURN NUMBER
IS
   lnTotalOcurrencias     NUMBER;
   lnIndicadorExclusion   MAV_DETAL_MAV.IND_EXCL_TIPO_CLAS%TYPE;
BEGIN

  --Se verifica si existe restriccion por tipificacion de Clientes
  SELECT COUNT(1)
    INTO lnTotalOcurrencias
    FROM MAV_SUBTI_CLIEN_DETAL
   WHERE denv_oid_deta_mav = pnOidDetalleMAV;

  --Se valida la tipificacion del Cliente
  IF(lnTotalOcurrencias > 0) THEN
    SELECT NVL(IND_EXCL_TIPO_CLAS, 0)
      INTO lnIndicadorExclusion
      FROM MAV_DETAL_MAV
     WHERE OID_DETA_MAV = pnOidDetalleMAV;

    IF(lnIndicadorExclusion = 0) THEN
      SELECT COUNT(1)
        INTO lnTotalOcurrencias
       FROM  v_mae_tipif_clien tip, mav_subti_clien_detal sub
       WHERE tip.clie_oid_clie = pnOidCliente
         AND tip.sbti_oid_subt_clie = sub.sbti_oid_subt_clie
         AND sub.denv_oid_deta_mav = pnOidDetalleMAV
         AND tip.tccl_oid_tipo_clasi = nvl(sub.tccl_oid_tipo_clasi, tip.tccl_oid_tipo_clasi)
         AND tip.clas_oid_clas = nvl(sub.clas_oid_clas, tip.clas_oid_clas);
    ELSE
      SELECT COUNT(1)
        INTO lnTotalOcurrencias
       FROM  v_mae_tipif_clien tip, mav_subti_clien_detal sub
       WHERE tip.clie_oid_clie = pnOidCliente
         AND tip.sbti_oid_subt_clie = sub.sbti_oid_subt_clie
         AND sub.denv_oid_deta_mav = pnOidDetalleMAV
         AND tip.tccl_oid_tipo_clasi = sub.tccl_oid_tipo_clasi
         AND tip.clas_oid_clas = nvl(sub.clas_oid_clas, tip.clas_oid_clas);

      IF(lnTotalOcurrencias > 0) THEN
        lnTotalOcurrencias := 0;
      ELSE
        lnTotalOcurrencias := 1;
      END IF;
    END IF;
  ELSE
    lnTotalOcurrencias := 1;
  END IF;

  RETURN lnTotalOcurrencias;

END MAV_FN_VALID_TIPIF_CLIEN;
/

