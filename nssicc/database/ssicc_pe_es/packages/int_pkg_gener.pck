CREATE OR REPLACE PACKAGE "INT_PKG_GENER" IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=5000;

/****************************************************************************
Descripcion       : Genera Informacion de Solicitudes en tabla temporal
                    para ser usada por todas las interfaces que la requieran
Fecha Creacion    : 29/02/2012
Autor             : CSVD-FFVV
Parametros:
     psCodigoPais     : Codigo de Pais
     psCodigoSistema  : Codigo de Sistema
     psCodigoInterfaz : Codigo de Interfaz
     psNombreArchivo  : Nombre Arcchivo
************************************************************************/
PROCEDURE INT_PR_GENER_INFOR_SOLIC
  (psCodigoPais           VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psFechaFacturacion     VARCHAR2);

/******************************************************************************
Descripcion : Proceso que registra en la entidad Control de Cierres de SiCC
              los procesos de cierre de SSICC
Fecha Creacion : 03/07/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo Pais
 psCodigoSistema : Codigo Empresa
 psCodigoPaquete : Codigo Paquete
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion: Fecha Facturacion
*******************************************************************************/
PROCEDURE INT_PR_GENER_REGIS_CIERR
 (
  psCodigoPais          VARCHAR2,
  psCodigoSistema       VARCHAR2,
  psCodigoPaquete       VARCHAR2,
  psCodigoPeriodo       VARCHAR2,
  psFechaFacturacion    VARCHAR2,
  psCodigoZona          VARCHAR2,
  psCodigoUsuario       VARCHAR2  
 );

END INT_PKG_GENER;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_GENER IS

/****************************************************************************
Descripcion       : Genera Informacion de Solicitudes en tabla temporal
                    para ser usada por todas las interfaces que la requieran
Fecha Creacion    : 29/02/2012
Autor             : CSVD-FFVV
Parametros:
     psCodigoPais     : Codigo de Pais
     psCodigoSistema  : Codigo de Sistema
     psCodigoInterfaz : Codigo de Interfaz
     psNombreArchivo  : Nombre Arcchivo
************************************************************************/
PROCEDURE INT_PR_GENER_INFOR_SOLIC
  (psCodigoPais           VARCHAR2,
   psCodigoCanal          VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psFechaFacturacion     VARCHAR2)
IS

-- Cursor Principal de Datos

CURSOR curDataInter (vnPeriodo NUMBER, vnPeriodoSig NUMBER, vnIndCruce NUMBER,vsIndImpuestoAdici VARCHAR2, vsCodigoPeriodoSig VARCHAR2) IS
SELECT data.*,
       CASE
         WHEN IdCampanaReferencia IS NULL THEN
          'PN' -- Pedido generado en la fecha de proceso
         WHEN (IdCampanaReferencia IS NOT NULL AND oid_soli_cabecon_ref IS NOT NULL) THEN
          'PR' -- Pedido de referencia de CDR procesado en la fecha de proceso
         WHEN VAL_CAMP_REFE_1 IS  NULL THEN
          'PE' -- Pedidos o atenciones especiales.
         ELSE
          'PC' -- CDR de CDR o con casuísticas especiales en su pedido de referencia.
       END Tipodepedido,

      (select soc2.val_impo_flet_tota_loca
         from ped_solic_cabec soc2
        where soc2.oid_soli_cabe = data.oid_soli_cabecon_ref) montoflete_ref, -- Flete obtenido del consolidado

       (select soc2.val_tota_paga_loca
          from ped_solic_cabec soc2
         where soc2.oid_soli_cabe = oid_soli_cabecon_ref) ventafact_ref, -- Total factura del consolidado

       (select soc2.num_clien
          from ped_solic_cabec soc2
         where soc2.oid_soli_cabe = oid_soli_cabecon_ref) numeroclientes_ref, -- Total clientes del consolidado

       (select soc.fec_fact
          from ped_solic_cabec soc
         where soc.oid_soli_cabe = data.oid_soli_cabedet_ref) fec_fact_ref,

       (select soc2.val_nume_soli
          from ped_solic_cabec soc2
         where soc2.oid_soli_cabe = oid_soli_cabecon_ref) val_nume_soli_ref, -- Número de pedido obtenido del consolidado

       (select cod_terr
          from ped_solic_cabec soc, zon_terri
         where soc.oid_soli_cabe = oid_soli_cabedet_ref
           and soc.terr_oid_terr = oid_terr) cod_terr_ref,

       (select cod_cana
          from cra_perio cra, seg_canal ca
         where cana_oid_cana = oid_cana
           and oid_peri = data.IdCampanaReferencia) cod_cana_ref,

       (select cod_acce
          from ped_solic_cabec soc, seg_acces ac, seg_subac sa
         where soc.oid_soli_cabe = oid_soli_cabedet_ref
           and soc.sbac_oid_sbac = sa.oid_sbac
           and sa.acce_oid_acce = ac.oid_acce) cod_acce_ref
  FROM (SELECT (SELECT MIN( ssoc2.oid_soli_cabe ) oid_soli_cabe
                  FROM ped_solic_cabec     ssoc,
                       ped_solic_cabec     ssoc2,
                       ped_tipo_solic_pais stsp,
                       ped_tipo_solic      sts
                 WHERE ssoc.soca_oid_soli_cabe = ssoc2.oid_soli_cabe
                   AND ssoc.tspa_oid_tipo_soli_pais = stsp.oid_tipo_soli_pais
                   AND stsp.tsol_oid_tipo_soli = sts.oid_tipo_soli
                   AND sts.cod_tipo_soli = 'SOC'
                   AND ssoc2.fec_fact IS NOT NULL
                   AND ssoc.perd_oid_peri = a.perd_oid_peri
                   AND ssoc2.oid_soli_cabe = soc.soca_oid_docu_refe
                   AND nvl(pts.num_unid_vend_otro,0) <> 1
                   AND ssoc.clie_oid_clie = soc.clie_oid_clie)  oid_soli_cabecon_ref,

               (SELECT MIN( ssoc.oid_soli_cabe ) oid_soli_cabe
                  FROM ped_solic_cabec     ssoc,
                       ped_solic_cabec     ssoc2,
                       ped_tipo_solic_pais stsp,
                       ped_tipo_solic      sts
                 WHERE ssoc.soca_oid_soli_cabe = ssoc2.oid_soli_cabe
                   AND ssoc.tspa_oid_tipo_soli_pais = stsp.oid_tipo_soli_pais
                   AND stsp.tsol_oid_tipo_soli = sts.oid_tipo_soli
                   AND sts.cod_tipo_soli = 'SOC'
                   AND ssoc2.fec_fact IS NOT NULL
                   AND ssoc.perd_oid_peri = a.perd_oid_peri
                   AND ssoc2.oid_soli_cabe = soc.soca_oid_docu_refe
                   AND nvl(pts.num_unid_vend_otro,0) <> 1
                   AND ssoc.clie_oid_clie = soc.clie_oid_clie) oid_soli_cabedet_ref,
               --
               soci.cod_soci                CodigoSociedad,
               icms.val_medi                Medio,
               marc.cod_marc                CodigoMarca,
               mone.cod_mone                CodigoMoneda,
               CASE WHEN cndp.cod_cond_prom IS NULL THEN '00' ELSE cndp.cod_cond_prom END CondiPromo,
               ocat.cod_cata                CodigoCatalogo,
               mapr.cod_marc_prod           MarcaProducto,
               ca.cod_cana                  CodigoCanal,
               ac.cod_acce                  CodigoAcceso,
               soc.oid_soli_cabe            IdSolicitudCabecera,
               soc2.oid_soli_cabe           IdSolicitudConsolidado,
               pos.oid_soli_posi,
               soc.tspa_oid_tipo_soli_pais,
               soc.clie_oid_clie            IdCliente,
               mc.cod_clie                  CodigoCliente,
               zon_terri.cod_terr           CodigoTerritorio,
               pc.cod_peri                  CampanaSolicitud,
               soc2.val_nume_soli           NumeroSolicitud,
               soc2.num_clien               NumeroClientes,
               soc.fec_fact                 FechaFacturacion,
               soc2.val_tota_paga_loca      ventafact,
               soc2.val_impo_flet_tota_loca MontoFlete,
               --
               CASE WHEN pts.num_unid_anul = 1 THEN 1 ELSE 0 END FlagAnulacion,
               --
               CASE
                 WHEN (pts.num_unid_devu = 1 OR pts.num_unid_canj = 1 OR pts.num_unid_anul = 1 OR pts.num_unid_true = 1
                       OR pts.num_unid_vend_otro = 1) THEN
                      a.perd_oid_peri
                 ELSE NULL
               END IdCampanaReferencia, -- Se halla el oid del periodo de referencia si es un CDR.
               CASE
                 WHEN (pts.num_unid_devu = 1 OR pts.num_unid_canj = 1 OR pts.num_unid_anul = 1 OR pts.num_unid_true = 1
                       OR pts.num_unid_vend_otro = 1) THEN
                      nvl(pc2.cod_peri,'      ')
                 ELSE '      '
               END CampanaReferencia, -- Se halla el periodo de referencia si es un CDR
               --
               pro.cod_sap CodigoProducto,
               SUBSTR( TRIM(pro.codi_anti),-9 ) CodigoBpcs,
               civi.cod_cicl_vida CicloVida,
               pos.val_codi_vent CodigoVenta,
               tof.cod_tipo_ofer TipoOferta,
               --
               CASE WHEN pts.num_unid_vend = 1 OR pts.num_unid_vend_otro = 1 THEN ABS(NVL(pos.num_unid_aten,0))ELSE 0 END UnidadesAtendidas,
               CASE WHEN pts.num_unid_falt = 1 OR pts.num_unid_vend_otro = 1 
                    THEN GREATEST(pos.num_unid_dema_real - pos.num_unid_compr,0) ELSE 0 END UnidadesNoAtendidas,
               ABS(DECODE(pts.num_unid_devu,1,NVL(pos.num_unid_aten, 0),0)) UnidadesDevueltas,
               ABS(DECODE(pts.num_unid_anul,1,NVL(pos.num_unid_aten, 0),0)) UnidadesAnuladas,
               --
               CASE
                  WHEN (pts.num_unid_vend = 1 OR pts.num_unid_vend_otro = 1) THEN
                       DECODE(pos.val_prec_cata_unit_loca,0,0,GREATEST(pos.num_unid_dema_real - pos.num_unid_compr,0) *
                              (pos.val_prec_neto_unit_loca - DECODE(vsIndImpuestoAdici,'1',NVL((SELECT x.val_impu_prod_naci
                                                                                                  FROM int_impue_produ_nacio x
                                                                                                 WHERE prod_oid_prod = pos.prod_oid_prod
                                                                                                    AND x.fec_carg = (SELECT MAX(y.fec_carg)
                                                                                                                        FROM int_impue_produ_nacio y
                                                                                                                       WHERE y.prod_oid_prod = pos.prod_oid_prod
                                                                                                                         AND y.fec_carg <= TO_DATE(psFechaFacturacion,'DD/MM/YYYY'))),0),0)))
                  ELSE 0
               END MontoFaltante,
               --
               CASE
                 WHEN ts.cod_tipo_soli = 'SOC' AND soc.ind_oc = 1 AND soc.modu_oid_modu NOT IN ( 13,15 ) THEN
                      NVL( pos.val_impo_desc_unit_loca,0 ) * NVL( pos.num_unid_compr,0 ) + NVL( pos.val_prec_publ_tota_loca,0 )
                 ELSE 0
               END OportunidadAhorro,
               --
               ABS(DECODE(pts.num_unid_canj,1,NVL(pos.num_unid_aten, 0),0)) UnidadesRetorno,
               ABS(DECODE(pts.num_unid_true,1,NVL(pos.num_unid_aten, 0),0)) UnidadesAtencion,
--               CASE WHEN pts.num_unid_true = 1 or pts.num_unid_vend_otro = 1 THEN NVL(pos.num_unid_aten,0) ELSE 0 END UnidadesAtencion,
               --
               ABS(DECODE(CASE WHEN pts.num_unid_canj=1 AND pos.val_prec_cata_unit_loca <> 0 THEN 1 ELSE 0 END,1,pos.val_prec_neto_tota_loca - DECODE(vsIndImpuestoAdici,'1',NVL(dcli.imp_impu_tota_prod_naci, 0),0),0)) MontoNetoRetorno,
               ABS(DECODE(CASE WHEN pts.num_unid_true=1 AND pos.val_prec_cata_unit_loca <> 0 THEN 1 ELSE 0 END,1,pos.val_prec_neto_tota_loca - DECODE(vsIndImpuestoAdici,'1',NVL(dcli.imp_impu_tota_prod_naci, 0),0),0)) MontoNetoAtencion,
               ABS(DECODE(CASE WHEN (pts.num_unid_vend=1 OR pts.num_unid_vend_otro = 1) AND pos.val_prec_cata_unit_loca <> 0 THEN 1 ELSE 0 END,1,pos.val_prec_neto_tota_loca - DECODE(vsIndImpuestoAdici,'1',NVL(dcli.imp_impu_tota_prod_naci, 0),0),0)) MontoNetoVenta,
               ABS(DECODE(CASE WHEN pts.num_unid_devu=1 AND pos.val_prec_cata_unit_loca <> 0 THEN 1 ELSE 0 END,1,pos.val_prec_neto_tota_loca - DECODE(vsIndImpuestoAdici,'1',NVL(dcli.imp_impu_tota_prod_naci, 0),0),0)) MontoNetoDevolucion,
               ABS(DECODE(CASE WHEN pts.num_unid_anul=1 AND pos.val_prec_cata_unit_loca <> 0 THEN 1 ELSE 0 END,1,pos.val_prec_neto_tota_loca - DECODE(vsIndImpuestoAdici,'1',NVL(dcli.imp_impu_tota_prod_naci, 0),0),0)) MontoNetoAnulacion,
               ABS(DECODE(CASE WHEN (pts.num_unid_vend=1 OR pts.num_unid_vend_otro = 1) AND pos.val_prec_cata_unit_loca <> 0 THEN 1 ELSE 0 END,1,pos.val_prec_fact_tota_loca,0)) PrecioFacturaTotalLocal,
               ABS(DECODE(CASE WHEN (pts.num_unid_vend=1 OR pts.num_unid_vend_otro = 1) THEN 1 ELSE 0 END,1,pos.val_prec_cata_tota_loca,0)) PrecioCatalogoVendidas,
               ABS(DECODE(CASE WHEN (pts.num_unid_falt=1 OR pts.num_unid_vend_otro = 1) THEN 1 ELSE 0 END,1,GREATEST(pos.num_unid_dema_real-pos.num_unid_compr,0)*pos.val_prec_cata_unit_loca,0)) PrecioCatalogoFaltantes,
               ofd.oid_deta_ofer,
               ( SELECT perd_oid_peri
                   FROM ped_solic_cabec pedc
                  WHERE pedc.oid_soli_cabe = soc.soca_oid_docu_refe
                        AND nvl(pts.num_unid_vend_otro,0) <> 1
                  ) val_camp_refe_1
               , nvl(pos.val_porc_desc,0) val_porc_desc
          FROM ped_solic_cabec soc,
               ped_solic_posic pos,
               ped_solic_cabec soc2,
               ped_tipo_solic_pais tsp,
               ped_tipo_solic ts,
               fac_docum_conta_linea dcli,
               mae_clien mc,
               mae_produ pro,
               pre_ofert_detal ofd,
               pre_tipo_ofert tof,
               pre_matri_factu_cabec a,
               pre_ofert b,
               pre_ciclo_vida civi,
               pre_matri_factu e,
               pre_condi_promo cndp,
               pre_catal ocat,
               cra_perio pe,
               cra_perio pe2,
               seg_canal ca,
               seg_acces ac,
               seg_subac sa,
               seg_perio_corpo pc,
               seg_perio_corpo pc2,
               seg_marca marc,
               seg_moned mone,
               seg_pais pais,
               seg_socie soci,
               seg_marca_produ mapr,
               zon_terri,
               int_param_tipo_solic pts,
               int_codig_medio_sapbp icms,
--   Ajuste tabla de fechas
               (SELECT soc.fec_fact  FechaFacturacion,
                       SUM(CASE WHEN pts.num_unid_vend = 1 OR pts.num_unid_falt = 1
                                THEN 1 ELSE  0 END) Ventas
                 FROM ped_solic_cabec      soc,
                      ped_tipo_solic_pais  tsp,
                      cra_perio            pe,
           seg_perio_corpo      pc,
           int_param_tipo_solic pts
     WHERE soc.tspa_oid_tipo_soli_pais = pts.tspa_oid_tipo_soli_pais
       AND fec_fact IS NOT NULL
       AND soc.perd_oid_peri = pe.oid_peri
       AND pe.peri_oid_peri = pc.oid_peri
       AND pts.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
       AND fec_fact BETWEEN fec_inic and fec_fina
       AND pc.cod_peri IN (psCodigoPeriodo, vsCodigoPeriodoSig )
       AND ( pts.num_unid_vend = 1 OR pts.num_unid_falt = 1 OR
             pts.num_unid_anul = 1 OR pts.num_unid_devu = 1 OR
             pts.num_unid_canj = 1 OR pts.num_unid_true = 1 )
       AND fec_fact NOT IN
           (SELECT DISTINCT fec_proc
              FROM int_tmp_datam_ctrol_diari wdiario
             WHERE wdiario.cod_perio IN (psCodigoPeriodo, vsCodigoPeriodoSig ))
       AND fec_fact < to_date(psFechaFacturacion,'DD/MM/YYYY')
                       GROUP BY soc.fec_fact
                       HAVING SUM(CASE WHEN pts.num_unid_vend = 1 OR pts.num_unid_falt = 1
                                       THEN 1 ELSE  0 END)=0
    UNION
                SELECT TO_DATE(psFechaFacturacion,'DD/MM/YYYY') FechaFacturacion, 0 Ventas
                       FROM DUAL
                ) wfechasp

-- Fin ajuste tabla de fechas

         WHERE pos.soca_oid_soli_cabe      = soc.oid_soli_cabe
           AND pos.ofde_oid_deta_ofer      = ofd.oid_deta_ofer(+)
           AND pos.oid_soli_posi           = dcli.sopo_oid_soli_posi(+)
           --
           AND soc.soca_oid_soli_cabe      = soc2.oid_soli_cabe
           AND soc.sbac_oid_sbac           = sa.oid_sbac
           AND soc.tspa_oid_tipo_soli_pais = pts.tspa_oid_tipo_soli_pais
           AND soc.perd_oid_peri           = pe.oid_peri(+)
           AND soc.clie_oid_clie           = mc.oid_clie
           AND soc.soci_oid_soci           = soci.oid_soci(+)
           AND soc.pais_oid_pais           = icms.pais_oid_pais(+)
           AND soc.sbac_oid_sbac           = icms.sbac_oid_sbac(+)
           AND soc.pais_oid_pais           = pais.oid_pais(+)
           --
           AND pais.mone_oid_mone          = mone.oid_mone(+)
           --
           AND e.mfca_oid_cabe             = a.oid_cabe -- Unión con la cabecera de matriz para obtener periodo de referencia.
           AND e.ofde_oid_deta_ofer        = ofd.oid_deta_ofer
           --
           AND a.perd_oid_peri             = pe2.oid_peri(+)
           AND a.oid_cabe                  = b.mfca_oid_cabe
           --
           AND ofd.prod_oid_prod           = pro.oid_prod(+)
           AND ofd.tofe_oid_tipo_ofer      = tof.oid_tipo_ofer(+)
           AND ofd.civi_oid_ciclo_vida     = civi.oid_cicl_vida(+)
           AND ofd.cndp_oid_cond_prom      = cndp.oid_cond_prom(+)
           AND ofd.ocat_oid_catal          = ocat.oid_cata(+)
           --
           AND pro.mapr_oid_marc_prod      = mapr.oid_marc_prod(+)
           --
           AND pe.cana_oid_cana            = ca.oid_cana(+)
           AND pe.peri_oid_peri            = pc.oid_peri(+)
           AND pe.marc_oid_marc            = marc.oid_marc(+)
           --
           AND ofd.ofer_oid_ofer           = b.oid_ofer
           AND tsp.tsol_oid_tipo_soli      = ts.oid_tipo_soli
           AND pe2.peri_oid_peri           = pc2.oid_peri(+)
           AND pts.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
           AND sa.acce_oid_acce            = ac.oid_acce
           --
           AND pos.espo_oid_esta_posi <> 2
           /*AND ( pos.num_unid_aten <> 0 OR ( pos.num_unid_dema_real - pos.num_unid_compr ) <> 0 )*/
          -- AND soc.fec_fact = TO_DATE(psFechaFacturacion,'DD/MM/YYYY')
          AND soc.fec_fact = wfechasp.FechaFacturacion
           AND (
                 soc.perd_oid_peri = vnperiodo OR
                 ( vnindcruce = 0 AND soc.perd_oid_peri = vnperiodosig )
               )
           AND soc.terr_oid_terr = zon_terri.oid_terr
           AND (
                 pts.num_unid_vend = 1 OR
                 pts.num_unid_falt = 1 OR
                 pts.num_unid_anul = 1 OR
                 pts.num_unid_devu = 1 OR
                 pts.num_unid_canj = 1 OR
                 pts.num_unid_true = 1 OR
                 pts.num_unid_vend_otro = 1
               )
        ) Data
--  WHERE ( val_camp_refe_1 =idcampanareferencia OR val_camp_refe_1 IS NULL )
      ;

TYPE cTabDataInter IS TABLE OF curDataInter%ROWTYPE INDEX BY BINARY_INTEGER;
cRegis cTabDataInter;

-- Cursor para obtener los Códigos de Equivalencia ( Productos Nacionales )

CURSOR curDataEqui IS
    SELECT gede.rowid clave,
           gede.oid_deta_ofer,
           equi.oid_deta_ofer_equi,
           equi.val_codi_vent_equi,
           equi.cod_tipo_ofer_equi,
           equi.cod_sap_equi,
           equi.Codigo_Bpcs_Equi
      FROM int_tmp_gener_solic_detal gede,
           (
            SELECT g.oid_deta_ofer,
                   c.oid_deta_ofer oid_deta_ofer_equi,
                   c.val_codi_vent val_codi_vent_equi,
                   u.cod_tipo_ofer cod_tipo_ofer_equi,
                   j.cod_sap cod_sap_equi,
                   SUBSTR( TRIM(j.codi_anti),-9 ) Codigo_Bpcs_Equi
              FROM pre_matri_factu_cabec a,
                   pre_ofert             b,
                   pre_ofert_detal       c,
                   pre_matri_factu       d,
                   pre_ofert_detal       g,
                   pre_matri_factu       h,
                   pre_matri_codig_alter i,
                   mae_produ             j,
                   mae_produ             k,
                   pre_tipo_ofert        t,
                   pre_tipo_ofert        u,
                   pre_prod_alter_ice    equi,
                   bas_pais,
                   cra_perio             e,
                   seg_perio_corpo       f
             WHERE a.oid_cabe           = b.mfca_oid_cabe
               AND b.oid_ofer           = c.ofer_oid_ofer
               AND c.oid_deta_ofer      = d.ofde_oid_deta_ofer
               AND d.oid_matr_fact      = i.mafa_oid_cod_ppal
               AND i.mafa_oid_cod_alte  = h.oid_matr_fact
               AND h.ofde_oid_deta_ofer = g.oid_deta_ofer
               AND c.prod_oid_prod      = j.oid_prod
               AND g.prod_oid_prod      = k.oid_prod
               AND g.tofe_oid_tipo_ofer = t.oid_tipo_ofer
               AND c.tofe_oid_tipo_ofer = u.oid_tipo_ofer
               AND j.cod_sap            = equi.cod_sap_ppal
               AND f.oid_peri           = e.peri_oid_peri
               AND a.perd_oid_peri      = e.oid_peri
               --
               AND cod_pais             = psCodigoPais
               AND ind_equi             = 1
               AND cod_peri             >= cod_peri_acti_equi
          UNION
            SELECT c.oid_deta_ofer,
                   g.oid_deta_ofer oid_deta_ofer_equi,
                   g.val_codi_vent val_codi_vent_equi,
                   t.cod_tipo_ofer cod_tipo_ofer_equi,
                   k.cod_sap cod_sap_equi,
                   SUBSTR( TRIM(k.codi_anti),-9 ) Codigo_Bpcs_Equi
              FROM pre_matri_factu_cabec a,
                   pre_ofert             b,
                   pre_ofert_detal       c,
                   pre_matri_factu       d,
                   pre_ofert_detal       g,
                   pre_matri_factu       h,
                   pre_matri_codig_alter i,
                   mae_produ             j,
                   mae_produ             k,
                   pre_tipo_ofert        t,
                   pre_tipo_ofert        u,
                   pre_prod_alter_ice    equi,
                   bas_pais,
                   cra_perio             e,
                   seg_perio_corpo       f
             WHERE a.oid_cabe           = b.mfca_oid_cabe
               AND b.oid_ofer           = c.ofer_oid_ofer
               AND c.oid_deta_ofer      = d.ofde_oid_deta_ofer
               AND d.oid_matr_fact      = i.mafa_oid_cod_ppal
               AND i.mafa_oid_cod_alte  = h.oid_matr_fact
               AND h.ofde_oid_deta_ofer = g.oid_deta_ofer
               AND c.prod_oid_prod      = j.oid_prod
               AND g.prod_oid_prod      = k.oid_prod
               AND g.tofe_oid_tipo_ofer = t.oid_tipo_ofer
               AND c.tofe_oid_tipo_ofer = u.oid_tipo_ofer
               AND j.cod_sap            = equi.cod_sap_alte
               AND f.oid_peri           = e.peri_oid_peri
               AND a.perd_oid_peri      = e.oid_peri
               --
              AND cod_pais             = psCodigoPais
              AND ind_equi             = 1
              AND cod_peri             >= cod_peri_acti_equi
           ) equi
     WHERE gede.oid_deta_ofer = equi.oid_deta_ofer
         ;

TYPE cTabDataEqui IS TABLE OF curDataEqui%ROWTYPE INDEX BY BINARY_INTEGER;
cEqui cTabDataEqui;

-- Cursor para obtener el Canal de Ingreso del pedido

CURSOR curDataCanal (vnPeriodo NUMBER, vnPeriodoSig NUMBER, vnIndCruce NUMBER, vnIndCier NUMBER) IS
    SELECT geca.clave,
           CASE
              WHEN vnIndCier = 0 THEN
                   CASE WHEN (NVL(cocc.ind_rece_cc,0)    +
                             NVL(cocc.ind_rece_dd,0)   +
                             NVL(cocc.ind_rece_digi,0) +
                             NVL(cocc.ind_rece_ivr,0)  +
                             NVL(cocc.ind_rece_mens,0) +
                              nvl(cocc.ind_rece_ocr,0)   +
                              NVL(cocc.ind_rece_onli,0)) > 1 AND
                              NVL(cocc.ind_rece_web,0)   = 0 THEN 'MIX'
                        WHEN (NVL(cocc.ind_rece_cc,0)    +
                              NVL(cocc.ind_rece_dd,0)    +
                              NVL(cocc.ind_rece_digi,0)  +
                              NVL(cocc.ind_rece_ivr,0)   +
                              NVL(cocc.ind_rece_mens,0)  +
                              nvl(cocc.ind_rece_ocr,0)   +
                              NVL(cocc.ind_rece_onli,0)) >= 1 AND
                              NVL(cocc.ind_rece_web,0)   = 1 THEN 'WMX'
             ELSE
                          CASE WHEN NVL(cocc.ind_rece_cc,0)   = 1 THEN 'CC'
                               WHEN NVL(cocc.ind_rece_dd,0)   = 1 THEN 'DD'
                               WHEN NVL(cocc.ind_rece_digi,0) = 1 THEN 'SC'
                               WHEN NVL(cocc.ind_rece_ivr,0)  = 1 THEN 'IVR'
                               WHEN NVL(cocc.ind_rece_mens,0) = 1 THEN 'SMS'
                               WHEN NVL(cocc.ind_rece_ocr,0)  = 1 THEN 'OCR'
                               WHEN NVL(cocc.ind_rece_onli,0) = 1 THEN 'OL'
                               when nvl(cocc.ind_rece_web,0)  = 1 then 'WEB'
                          else null -- 'ND' HRG
               END
                   END
              ELSE
                   CASE WHEN (NVL(hicc.ind_rece_cc,0)    +
                              NVL(hicc.ind_rece_dd,0)    +
                              NVL(hicc.ind_rece_digi,0)  +
                              NVL(hicc.ind_rece_ivr,0)   +
                              NVL(hicc.ind_rece_mens,0)  +
                              NVL(hicc.ind_rece_ocr,0)   +
                              NVL(hicc.ind_rece_onli,0)) > 1 AND
                              NVL(hicc.ind_rece_web,0)   = 0 THEN 'MIX'
                        WHEN (NVL(hicc.ind_rece_cc,0)    +
                             NVL(hicc.ind_rece_dd,0)   +
                             NVL(hicc.ind_rece_digi,0) +
                             NVL(hicc.ind_rece_ivr,0)  +
                             NVL(hicc.ind_rece_mens,0) +
                             NVL(hicc.ind_rece_ocr,0)  +
                              NVL(hicc.ind_rece_onli,0)) >= 1 AND
                              NVL(hicc.ind_rece_web,0)   = 1 then 'WMX'
                    ELSE
                             CASE
                               WHEN NVL(hicc.ind_rece_cc,0)   = 1 THEN 'CC'
                               WHEN NVL(hicc.ind_rece_dd,0)   = 1 THEN 'DD'
                               WHEN NVL(hicc.ind_rece_digi,0) = 1 THEN 'SC'
                               WHEN NVL(hicc.ind_rece_ivr,0)  = 1 THEN 'IVR'
                               WHEN NVL(hicc.ind_rece_mens,0) = 1 THEN 'SMS'
                               WHEN NVL(hicc.ind_rece_ocr,0)  = 1 THEN 'OCR'
                               WHEN NVL(hicc.ind_rece_onli,0) = 1 THEN 'OL'
                               WHEN NVL(hicc.ind_rece_web,0)  = 1 THEN 'WEB'
                               ELSE NULL -- 'ND' HRG
                             END
                   END
           END AS CanalIngreso
      FROM (SELECT CASE
                      WHEN sgeca.oid_soli_cabe_refe IS NOT NULL THEN
                           sgeca.oid_soli_cabe_refe
                      ELSE sgeca.oid_soli_cabe
                   END oid_soli_cabe,
                   ROWID Clave
              FROM int_tmp_gener_solic_cabec sgeca) geca,
           int_solic_conso_cabec cocc,
           (SELECT a.soca_oid_soli_cabe_refe,
                   a.perd_oid_peri,
                   a.ind_rece_ocr,
                   a.ind_rece_web,
                   a.ind_rece_dd,
                   a.ind_rece_digi,
                   a.ind_rece_cc,
                   a.ind_rece_mens,
                   a.ind_rece_onli,
                   a.ind_rece_ivr
              FROM ped_histo_solic_conso_cabec a
             WHERE a.cod_pais = psCodigoPais
               AND (a.perd_oid_peri = vnperiodo OR
                   (vnindcruce = 0 AND a.perd_oid_peri = vnPeriodoSig))
               AND a.soca_oid_soli_cabe_refe IS NOT NULL
               AND a.ind_erro_rech = 0) hicc
     WHERE geca.oid_soli_cabe = cocc.soca_oid_soli_cabe_refe(+)
       AND geca.oid_soli_cabe = hicc.soca_oid_soli_cabe_refe(+)
         ;

TYPE cTabDataCanal IS TABLE OF curDataCanal%ROWTYPE INDEX BY BINARY_INTEGER;
cCanal cTabDataCanal;

-- Cursor para obtener indicador PROL del pedido

CURSOR curDataProl IS
SELECT geca.clave,
       NVL( soca.ind_vali_prol, '0' ) ind_vali_prol
  FROM ( SELECT CASE WHEN sgeca.oid_soli_cabe_refe IS NOT NULL THEN
                          sgeca.oid_soli_cabe_refe
                     ELSE sgeca.oid_soli_cabe
                END oid_soli_cabe,
                ROWID clave
           FROM int_tmp_gener_solic_cabec sgeca) geca,
       ped_solic_cabec soca
 WHERE geca.oid_soli_cabe = soca.oid_soli_cabe
     ;

TYPE cTabDataProl IS TABLE OF curDataProl%ROWTYPE INDEX BY BINARY_INTEGER;
cProl cTabDataProl;

-- Cursor para generar el Archivo Consolidado de Pedidos

CURSOR curDataConso IS
        SELECT gede.cod_peri,
               TO_CHAR(geca.fec_fact,'YYYYMMDD') fec_fact,
               gede.cod_marc_prod,
               DECODE(gede.cod_prod_equi, NULL, gede.cod_prod, gede.cod_prod_equi) cod_prod,
               DECODE(gede.cod_bpcs_equi, NULL, gede.cod_bpcs, gede.cod_bpcs_equi) cod_bpcs,
               DECODE(gede.val_codi_vent_equi, NULL, gede.val_codi_vent, gede.val_codi_vent_equi) val_codi_vent,
               DECODE(gede.cod_tipo_ofert_equi, NULL, gede.cod_tipo_ofert, gede.cod_tipo_ofert_equi) cod_tipo_ofert,
               MAX(gede.cod_cata) cod_cata,
               SUM(gede.num_unid_vent) num_unid_vent,
               SUM(gede.num_unid_falt) num_unid_falt,
               SUM(gede.imp_neto_vent) imp_neto_vent,
               SUM(gede.imp_neto_falt) imp_neto_falt,
               SUM(gede.imp_vent_cata) imp_vent_cata,
         SUM(gede.imp_vent_cata_falt) imp_vent_cata_falt,
         0  num_orde_marc,
         0  num_pedi_marc
         FROM int_tmp_gener_solic_detal gede,
               int_tmp_gener_solic_cabec geca
         WHERE gede.oid_soli_cabe      = geca.oid_soli_cabe
           AND geca.cod_peri_refe = gede.cod_peri_refe
           AND geca.cod_pedi IN  ('PN','PE')
           AND geca.cod_peri = psCodigoPeriodo
      GROUP BY gede.cod_peri,
               TO_CHAR(geca.fec_fact,'YYYYMMDD'),
               gede.cod_marc_prod,
               DECODE(gede.cod_prod_equi, NULL, gede.cod_prod, gede.cod_prod_equi),
               DECODE(gede.cod_bpcs_equi, NULL, gede.cod_bpcs, gede.cod_bpcs_equi),
               DECODE(gede.val_codi_vent_equi, NULL, gede.val_codi_vent, gede.val_codi_vent_equi),
               DECODE(gede.cod_tipo_ofert_equi, NULL, gede.cod_tipo_ofert, gede.cod_tipo_ofert_equi)
     ;


TYPE cTabDataConso IS TABLE OF curDataConso%ROWTYPE INDEX BY BINARY_INTEGER;
rConso cTabDataConso;

-- Variables de trabajo
  lnCampaFactu cra_perio.oid_peri%TYPE;
  lnCampaProxi cra_perio.oid_peri%TYPE;
  lnCruceCampa NUMBER(1);
  lsImpueAdici VARCHAR2(1);
  lnOk         NUMBER(1) := 0;
  lsIndEqui    VARCHAR2(1);
  lnIndcier    NUMBER(1);
  lsCodigoPeriodoSig  VARCHAR2(6);

-- Variables de Control
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  lnFilas    NATURAL := 5000;

BEGIN
    /*
      Limpiar las tablas temporales
    */
    DELETE FROM int_tmp_gener_solic_cabec;
    DELETE FROM int_tmp_gener_solic_detal;

    /*
      Obtener valores para las variables de proceso
    */
    lnCampaFactu := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( psCodigoPeriodo );
    lnCampaProxi := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2( GEN_FN_CALCU_PERIO( psCodigoPeriodo, 1 ) );
    lsCodigoPeriodoSig := GEN_FN_CALCU_PERIO( psCodigoPeriodo, 1 );

    /*
      Verificar si pais tiene activado el proceso de equivalencia de códigos
    */
    SELECT NVL(bp.ind_equi,'0')
      INTO lsIndEqui
      FROM bas_pais bp,
           seg_pais sp
     WHERE sp.cod_pais = bp.cod_pais
       AND sp.cod_pais =  psCodigoPais
         ;

    /*
      Verificar si hay cruce de campañas en el pais
    */
    SELECT perd.ind_peri_cruc
      INTO lnCruceCampa
      FROM cra_perio perd
     WHERE perd.oid_peri = lnCampaFactu;

    /*
      Verificar si pais tiene activado el proceso de impuesto ICE
    */
    BEGIN
        SELECT papa.val_para
          INTO lsImpueAdici
          FROM bas_param_pais papa
         WHERE papa.cod_pais = psCodigoPais
           AND papa.cod_sist = 'SAB'
           AND papa.cod_para = '001'
             ;
    EXCEPTION WHEN OTHERS THEN
        lsImpueAdici := '0';
    END;

    /*
      Verificar si hay cierre de campaña vigente
    */
    SELECT COUNT(1)
      INTO lnIndcier
      FROM fac_contr_cierr ccie
     WHERE ccie.tcie_oid_tipo_cier = 3
       AND (ccie.perd_oid_peri = lnCampaFactu OR (lnCruceCampa = 0 AND ccie.perd_oid_peri = lnCampaProxi))
       AND ROWNUM = 1 -- Nuevo
         ;

    OPEN curDataInter(lnCampaFactu,lnCampaProxi,lnCruceCampa,lsImpueAdici, lsCodigoPeriodoSig);
      LOOP
         FETCH curDataInter BULK COLLECT INTO cRegis LIMIT lnFilas;
         EXIT WHEN cRegis.COUNT = 0;
         FOR x IN cRegis.FIRST .. cRegis.LAST LOOP

             /*
               Crear registro en cabecera
             */
             BEGIN
                INSERT INTO int_tmp_gener_solic_cabec
                VALUES(
                         cRegis(x).IDSOLICITUDCABECERA, --oid_soli_cabe
                         cRegis(x).IDSOLICITUDCONSOLIDADO, --soca_oid_soli_cabe
                         cRegis(x).CAMPANASOLICITUD, --cod_peri
                         cRegis(x).CODIGOSOCIEDAD, --cod_soci
                         cRegis(x).MEDIO, --val_medi
                         cRegis(x).CODIGOMARCA, --cod_marc
                         cRegis(x).CODIGOMONEDA, --cod_mone
                         NULL, --cod_cana_ingr
                         cRegis(x).FECHAFACTURACION, --fec_fact
                         cRegis(x).CODIGOCANAL, --cod_canal
                         cRegis(x).CODIGOACCESO, --cod_acces
                         cRegis(x).CODIGOCLIENTE, --cod_clie
                         cRegis(x).CODIGOTERRITORIO, --cod_terr
                         cRegis(x).NUMEROSOLICITUD, --val_nume_soli
                         cRegis(x).TSPA_OID_TIPO_SOLI_PAIS, --tspa_oid_tipo_soli
                         cRegis(x).NUMEROCLIENTES, --val_nume_clie
                         cRegis(x).VENTAFACT, --imp_vent_neta
                         cRegis(x).MONTOFLETE, --imp_flet
                         cRegis(x).FLAGANULACION, --ind_anul
                         cRegis(x).TIPODEPEDIDO, --cod_pedi
                         cRegis(x).OID_SOLI_CABEDET_REF, --oid_soli_cabe_refe
                         cRegis(x).OID_SOLI_CABECON_REF, --soca_oid_soli_cabe_refe
                         cRegis(x).CAMPANAREFERENCIA, --cod_peri_refe
                         cRegis(x).COD_CANA_REF, --cod_cana_refe
                         cRegis(x).COD_ACCE_REF, --cod_acce_refe
                         cRegis(x).FEC_FACT_REF, --fec_fact_refe
                         cRegis(x).COD_TERR_REF, --cod_terr_refe
                         cRegis(x).VAL_NUME_SOLI_REF, --val_nume_soli_refe
                         cRegis(x).NUMEROCLIENTES_REF, --val_nume_clie_refe
                         cRegis(x).VENTAFACT_REF, --imp_vent_neta_refe
                         cRegis(x).MONTOFLETE_REF, --imp_flet_refe
                         NULL --ind_rese_onli
                       )
                       ;
             EXCEPTION WHEN dup_val_on_index THEN
                lnOk := 0;
             END;

             BEGIN
             INSERT INTO INT_TMP_DATAM_CTROL_DIARI
              VALUES (cRegis(x).FECHAFACTURACION, --fec_fact
                      cRegis(x).CAMPANASOLICITUD, --cod_peri
                      NULL,
                      SYSDATE);
             EXCEPTION WHEN dup_val_on_index THEN
                lnOk := 0;
             END;

             /*
               Crear registro en detalle
             */
             IF ( cRegis(x).unidadesatendidas +
                  cRegis(x).unidadesnoatendidas +
                  cRegis(x).unidadesdevueltas +
                  cRegis(x).unidadesanuladas +
                  cRegis(x).unidadesretorno +
                  cRegis(x).unidadesatencion ) > 0 THEN

             INSERT INTO int_tmp_gener_solic_detal
             VALUES (
                      cRegis(x).IDSOLICITUDCABECERA, --oid_soli_cabe
                      cRegis(x).IDSOLICITUDCONSOLIDADO, --soca_oid_soli_cabe
                      cRegis(x).CAMPANASOLICITUD, --cod_peri
                      cRegis(x).OID_SOLI_POSI, --oid_soli_posi
                      cRegis(x).CODIGOPRODUCTO, --cod_prod
                      cRegis(x).CODIGOBPCS, -- cod_bpcs
                      cRegis(x).CODIGOVENTA, --val_codi_vent
                      cRegis(x).TIPOOFERTA, --cod_tipo_ofert
                      cRegis(x).CICLOVIDA, -- cod_cicl_vida
                      cRegis(x).CONDIPROMO, -- cod_cond_prom
                      cRegis(x).CODIGOCATALOGO, -- cod_cata
                      cRegis(x).MARCAPRODUCTO, -- cod_marc_prod
                      cRegis(x).UNIDADESATENDIDAS, --num_unid_vent
                      cRegis(x).UNIDADESNOATENDIDAS, --num_unid_falt
                      cRegis(x).UNIDADESDEVUELTAS, --num_unid_devo
                      cRegis(x).UNIDADESANULADAS, --num_unid_anul
                      cRegis(x).UNIDADESRETORNO, --num_unid_reto
                      cRegis(x).UNIDADESATENCION, --num_unid_aten
                      cRegis(x).MONTONETOVENTA, --imp_neto_vent
                      cRegis(x).MONTOFALTANTE, --imp_neto_falt
                      cRegis(x).MONTONETODEVOLUCION, --imp_neto_devo
                      cRegis(x).MONTONETOANULACION, --imp_neto_anul
                      cRegis(x).MONTONETORETORNO, --imp_neto_reto
                      cRegis(x).MONTONETOATENCION, --imp_neto_aten
                      cRegis(x).PRECIOCATALOGOVENDIDAS, --imp_vent_cata
                      cRegis(x).PRECIOCATALOGOFALTANTES, --imp_vent_cata_falt
                      cRegis(x).PRECIOFACTURATOTALLOCAL, --pre_fact_tota_loca
                      cRegis(x).OPORTUNIDADAHORRO, --imp_opor_ahor
                      cRegis(x).OID_DETA_OFER, --oid_deta_ofer
                      NULL, --oid_deta_ofer_equi
                      NULL, --cod_prod_equi
                      NULL, --cod_bpcs_equi
                      NULL, --val_cod_vent_equi
                            NULL, --cod_tipo_ofert_equi
                            cRegis(x).VAL_PORC_DESC, --val_porc_desc
                            cRegis(x).CAMPANAREFERENCIA --val_porc_desc
                          );
             END IF;
         END LOOP;
      END LOOP;
    CLOSE curDataInter;

    /*
      Obtiene datos de equivalencia de códigos para el detalle
    */
    IF lsIndEqui = '1' THEN
       OPEN curDataEqui;
         LOOP
            FETCH curDataEqui BULK COLLECT INTO cEqui LIMIT lnFilas;
            EXIT WHEN cEqui.COUNT = 0;
            FOR x IN cEqui.FIRST .. cEqui.LAST LOOP
                UPDATE int_tmp_gener_solic_detal gede
                   SET gede.oid_deta_ofer_equi  = cEqui(x).oid_deta_ofer_equi,
                       gede.cod_prod_equi       = cEqui(x).cod_sap_equi,
                       gede.cod_bpcs_equi       = cEqui(x).Codigo_Bpcs_Equi,
                       gede.val_codi_vent_equi   = cEqui(x).val_codi_vent_equi,
                       gede.cod_tipo_ofert_equi = cEqui(x).cod_tipo_ofer_equi
                 WHERE gede.rowid = cEqui(x).clave
                     ;
            END LOOP;
         END LOOP;
       CLOSE curDataEqui;
    END IF;

    /*
      Obtiene datos del canal de ingreso
    */
    OPEN curDataCanal(lnCampaFactu,lnCampaProxi,lnCruceCampa,lnIndCier);
      LOOP
         FETCH curDataCanal BULK COLLECT INTO cCanal LIMIT lnFilas;
         EXIT WHEN cCanal.COUNT = 0;
         FOR x IN cCanal.FIRST .. cCanal.LAST LOOP
             UPDATE int_tmp_gener_solic_cabec geca
                SET geca.cod_cana_ingr = cCanal(x).CanalIngreso
              WHERE geca.rowid = cCanal(x).clave
                  ;
         END LOOP;
       END LOOP;
    CLOSE curDataCanal;

    /*
      Obtiene indicador de PROL de los pedidos
    */
    OPEN curDataProl;
      LOOP
         FETCH curDataProl BULK COLLECT INTO cProl LIMIT lnFilas;
         EXIT WHEN cProl.COUNT = 0;
         FOR x IN cProl.FIRST .. cProl.LAST LOOP
             UPDATE int_tmp_gener_solic_cabec geca
                SET geca.ind_rese_onli = CASE WHEN geca.cod_cana_ingr != 'WEB' THEN '0' ELSE cProl(x).ind_vali_prol END
              WHERE geca.rowid = cProl(x).clave
                  ;
         END LOOP;
      END LOOP;
    CLOSE curDataProl;

    /*
      Actualiza datos en la tabla de consolidado por día
    */

    delete from int_gener_diari_conso geco where geco.cod_peri=psCodigoPeriodo
          and geco.fec_fact in (select distinct SUBSTR(to_char(fec_fact,'dd/mm/yyyy' ),7,4) ||
              SUBSTR(to_char(fec_fact,'dd/mm/yyyy' ),4,2) ||
              SUBSTR(to_char(fec_fact,'dd/mm/yyyy' ),1,2)
              from int_tmp_gener_solic_cabec where cod_pedi in ('PN','PE'));

    OPEN curDataConso;
      LOOP
         FETCH curDataConso BULK COLLECT INTO rConso LIMIT lnFilas;
         EXIT WHEN rConso.COUNT = 0;
         FOR x IN rConso.FIRST .. rConso.LAST LOOP
     BEGIN
        INSERT INTO int_gener_diari_conso
        VALUES(
                rConso(x).cod_peri,
                rConso(x).fec_fact,
                rConso(x).cod_marc_prod,
                rConso(x).cod_prod,
                rConso(x).cod_bpcs,
                rConso(x).val_codi_vent,
                rConso(x).cod_tipo_ofert,
                rConso(x).cod_cata,
                rConso(x).num_unid_vent,
                rConso(x).num_unid_falt,
                rConso(x).imp_neto_vent,
                rConso(x).imp_neto_falt,
                rConso(x).imp_vent_cata,
                rConso(x).imp_vent_cata_falt,
                rConso(x).num_orde_marc,
                rConso(x).num_pedi_marc
              )
               ;
     EXCEPTION WHEN dup_val_on_index THEN
        lnOk := 0;
     END;
         END LOOP;
       END LOOP;
    CLOSE curDataConso;

    UPDATE INT_TMP_DATAM_CTROL SET FEC_PROC = psFechaFacturacion, COD_PERIO = psCodigoPeriodo, FEC_MODI=SYSDATE;

EXCEPTION WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,1,250);
    raise_application_error(-20123,'ERROR INT_PR_GENER_INFOR_SOLIC: ' || ls_sqlerrm);
END INT_PR_GENER_INFOR_SOLIC;


/******************************************************************************
Descripcion : Proceso que registra en la entidad Control de Cierres de SiCC
              los procesos de cierre de SSICC
Fecha Creacion : 03/07/2013
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo Pais
 psCodigoSistema : Codigo Empresa
 psCodigoPaquete : Codigo Paquete
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion: Fecha Facturacion
*******************************************************************************/
PROCEDURE INT_PR_GENER_REGIS_CIERR
 (
  psCodigoPais          VARCHAR2,
  psCodigoSistema       VARCHAR2,
  psCodigoPaquete       VARCHAR2,
  psCodigoPeriodo       VARCHAR2,
  psFechaFacturacion    VARCHAR2,
  psCodigoZona          VARCHAR2,
  psCodigoUsuario       VARCHAR2
 )
IS

  CURSOR c_procesos IS

     SELECT bi.des_inte desProceso
       FROM bas_compo_paque bcp,
            bas_inter bi
      WHERE bcp.pais_cod_pais = psCodigoPais
        AND bcp.sist_cod_sist = psCodigoSistema
        AND bcp.inte_cod_inpa = psCodigoPaquete
        AND bcp.pais_cod_pais = bi.pais_cod_pais
        AND bcp.sist_cod_sist = bi.sist_cod_sist
        AND bcp.inte_cod_inte = bi.cod_inte;


   TYPE procesoTipo IS RECORD
   (
    desProceso      bas_inter.des_inte%TYPE
   );

   TYPE procesoTab IS TABLE OF procesoTipo;

   procesoRecord procesoTab;

   /* Variables usadas para el proceso */
   lnidPais                    NUMBER;
   lnidPeriodo                 NUMBER;

BEGIN
  lnidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

  --Recorremos las zonas seleccionadas
  FOR y IN (SELECT OID_ZONA, COD_ZONA
              FROM ZON_ZONA
             WHERE INSTR(psCodigoZona, COD_ZONA) > 0
               AND IND_ACTI = 1
               AND IND_BORR = 0) LOOP
   
   --ACTUALIZAMOS EN TABLA FAC_PROGR_CIERR
    UPDATE FAC_PROGR_CIERR
       SET EST_CIER = 'P', 
           USU_MODI = psCodigoUsuario, 
           FEC_MODI = SYSDATE
     WHERE EST_CIER = 'A'
       AND COD_ZONA = y.COD_ZONA
       AND TIP_CIER = 'Z'
       AND CAM_PROC = psCodigoPeriodo;
    
  END LOOP;
  
  /* Abriendo cursos de procesos de cierre */
  OPEN c_procesos;
    LOOP
     FETCH c_procesos BULK COLLECT INTO procesoRecord LIMIT W_FILAS;
      IF procesoRecord.COUNT > 0 THEN
       FOR x IN procesoRecord.FIRST .. procesoRecord.LAST LOOP
           INSERT INTO fac_contr_cierr
           (
            oid_ctrl,
            pais_oid_pais,
            val_secu_cier,
            fec_cier,
            val_proc_ejec,
            val_resu_proc,
            perd_oid_peri,
            zzon_oid_zona,
            tcie_oid_tipo_cier,
            zorg_oid_regi,
            fec_ulti_actu
           )
           SELECT FAC_COCI_SEQ.NEXTVAL,
                  zr.pais_oid_pais,
                  FAC_COCI_SEQ.CURRVAL,
                  fpc.fec_cier,
                  procesoRecord(x).desProceso,
                  'OK',
                  lnidPeriodo,
                  zz.oid_zona,
                  2, --2: Tipo Cierre Zona
                  NULL,
                  SYSDATE
             FROM fac_progr_cierr fpc,
                  zon_zona zz,
                  zon_regio zr
            WHERE fpc.cam_proc = psCodigoPeriodo
              AND TRUNC(fpc.fec_cier) = TO_DATE(psFechaFacturacion,'dd/mm/yyyy')
              AND fpc.tip_cier = 'Z' -- Z: Cierre Zona
              AND fpc.est_cier = 'P' -- P:Procesado
              AND fpc.cod_regi = zr.cod_regi
              AND fpc.cod_zona = zz.cod_zona
              AND zr.oid_regi = zz.zorg_oid_regi;

       END LOOP;
      END IF;
     EXIT WHEN c_procesos%NOTFOUND;
    END LOOP;
   CLOSE c_procesos;
  
RETURN;
EXCEPTION
 WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_GENER_REGIS_CIERR: '||ls_sqlerrm);
END INT_PR_GENER_REGIS_CIERR;

END INT_PKG_GENER;
/
