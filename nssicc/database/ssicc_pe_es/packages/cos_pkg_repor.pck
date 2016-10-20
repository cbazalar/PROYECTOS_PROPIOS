create or replace package COS_PKG_REPOR is

  /* Declaracion de variables */
  ln_sqlcode           NUMBER(10);
  ls_sqlerrm           VARCHAR2(1500);

/*********************************************************************************
     Descripcion       : Generar reporte de Cierre de Costos - Ventas
     Autor             : Nicolás López
     Fecha Creacion    : 17/01/2011
     Parametros Entrada :
                psCodigoPais
                psMesActual
                psOidSecuencia
**********************************************************************************/
 PROCEDURE COS_PR_GENER_REPOR_CCOST_VENTA (
   psCodigoPais           VARCHAR2,
   psMesActual            VARCHAR2,
   psOidSecuencia         VARCHAR2
 );

/*********************************************************************************
     Descripcion       : Generar reporte de Cierre de Costos - Devoluciones
     Autor             : Nicolás López
     Fecha Creacion    : 18/01/2011
     Parametros Entrada :
                psCodigoPais
                psMesActual
                psOidSecuencia
**********************************************************************************/
 PROCEDURE COS_PR_GENER_REPOR_CCOST_DEVOL (
   psCodigoPais           VARCHAR2,
   psMesActual            VARCHAR2,
   psOidSecuencia         VARCHAR2
 );

end COS_PKG_REPOR;
/

create or replace package body COS_PKG_REPOR is

/*********************************************************************************
     Descripcion       : Generar reporte de Cierre de Costos - Ventas
     Autor             : Nicolás López
     Fecha Creacion    : 17/01/2011
     Parametros Entrada :
                psCodigoPais
                psMesActual
                psOidSecuencia
**********************************************************************************/
 PROCEDURE COS_PR_GENER_REPOR_CCOST_VENTA (
   psCodigoPais              VARCHAR2,
   psMesActual            VARCHAR2,
   psOidSecuencia         VARCHAR2
 )
 IS
   W_FILAS              NUMBER := 5000;
   lnIdPais             NUMBER;
   lsCodTipoOfer        pre_tipo_ofert.cod_tipo_ofer%TYPE;
   lsCodSap             mae_produ.cod_sap%TYPE;
   lsDescProd           gen_i18n_sicc_pais.val_i18n%TYPE;
   lnCostEstd           mae_produ.val_cost_estd%TYPE;
   lnOidDetaOfer        pre_ofert_detal.oid_deta_ofer%TYPE;
   lnOidTipoOfer        pre_tipo_ofert.oid_tipo_ofer%TYPE;

   TYPE tmptablaCCostoVentas IS RECORD(
     valMes                  VARCHAR2(6),
     codPeriodo              VARCHAR2(6),
     valFechFact             VARCHAR2(8),
     numUnidAten             fac_docum_conta_linea.num_unid_aten%TYPE,
     valImpoSimpTota         NUMBER(15,2),
     valImpoSimpUnit         NUMBER(15,2),
     valDsctVolu             fac_docum_conta_linea.imp_desc_sin_impu_tota_loca%TYPE,
     valDsctCmrc             fac_docum_conta_linea.val_prec_cont_tota_loca%TYPE,
     valImpoFactTota         NUMBER(12,2),
     oidSoliPosi             ped_solic_posic.oid_soli_posi%TYPE,
     oidProd                 mae_produ.oid_prod%TYPE,
     val_prec_cata_unit      fac_docum_conta_linea.val_prec_cata_unit_loca%TYPE,
     val_prec_sin_impu_tota  fac_docum_conta_linea.val_prec_sin_impu_tota_loca%TYPE
   );

   TYPE tablaRegCCostoVentas IS TABLE OF tmptablaCCostoVentas;
   tablaRegCCostoVentasrecord tablaRegCCostoVentas;

   -- Se obtienen los datos de centro de Costo - Ventas
   CURSOR REPOR_CCOS_VENTA(ln_OidPais NUMBER) IS
      SELECT to_char(cab.fec_fact, 'yyyymm')                    as Mes,
             peco.COD_PERI                                      as CmpVenta,
             to_char(cab.fec_fact, 'yyyymmdd')                  as FecFact,
             det.num_unid_aten                                  as Unidades,
             decode(det.val_prec_cata_unit_loca,
                        0,
                        det.val_prec_cont_tota_loca,
                        det.VAL_PREC_SIN_IMPU_TOTA_LOCA)        as Importe_Sin_Impuesto_Total,
             decode(det.val_prec_cata_unit_loca,
                        0,
                        det.val_prec_cont_tota_loca,
                        det.VAL_PREC_SIN_IMPU_TOTA_LOCA) /
             det.num_unid_aten                                  as Importe_Sin_Impuesto_Unit,
             det.IMP_DESC_SIN_IMPU_TOTA_LOCA                    as Dscto_Volumen,
             det.VAL_PREC_CONT_TOTA_LOCA                        as Dscto_Comercial,
             decode(det.val_prec_cata_unit_loca,
                        0,
                        0,
                        det.VAL_PREC_FACT_TOTA_LOCA)            as Importe_Factura_Total,
             det.sopo_oid_soli_posi                             as oid_soli_posi,
             det.prod_oid_prod                                  as oid_prod,
             det.val_prec_cata_unit_loca                        as val_prec_cata_unit,
             det.val_prec_sin_impu_tota_loca                    as val_prec_sin_impu_tota
        FROM fac_docum_conta_cabec cab,
             fac_docum_conta_linea det,
             cra_perio             peri,
             seg_perio_corpo       peco
       WHERE cab.oid_cabe = det.dcca_oid_cabe
         AND cab.perd_oid_peri = peri.oid_peri
         AND peri.peri_oid_peri = peco.oid_peri
         AND cab.fec_fact IS NOT NULL -- Facturadas
         AND cab.pais_oid_pais = ln_OidPais
         AND TO_CHAR(cab.fec_fact,'YYYYMM') = psMesActual
         AND det.num_unid_aten > 0
         AND cab.tido_oid_tipo_docu IN (1, 9, 29, 34); -- Factura 1, Factura 2, Boleta de Venta y Nota de Débito

 BEGIN
   /* obteniendo id Pais */
   lnIdPais        := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante

   OPEN REPOR_CCOS_VENTA(lnIdPais);
     LOOP
       FETCH REPOR_CCOS_VENTA BULK COLLECT INTO tablaRegCCostoVentasrecord LIMIT W_FILAS;

         IF tablaRegCCostoVentasrecord.COUNT > 0 THEN

           FOR x IN tablaRegCCostoVentasrecord.FIRST .. tablaRegCCostoVentasrecord.LAST LOOP

           -- Se obtiene el Id de Pedido Solicitud
           BEGIN
              SELECT f.ofde_oid_deta_ofer INTO lnOidDetaOfer
                FROM PED_SOLIC_POSIC f
               WHERE f.oid_soli_posi = tablaRegCCostoVentasrecord(x).oidSoliPosi;
           EXCEPTION WHEN NO_DATA_FOUND THEN
                lnOidDetaOfer := NULL;
           END;

           -- Se obtiene el Id de Detalle de Oferta
           BEGIN
              SELECT h.tofe_oid_tipo_ofer INTO lnOidTipoOfer
                FROM pre_ofert_detal h
               WHERE h.oid_deta_ofer = lnOidDetaOfer;
           EXCEPTION WHEN NO_DATA_FOUND THEN
                lnOidTipoOfer := NULL;
           END;

           -- Se obtiene el Código de Tipo de Oferta
           BEGIN
              SELECT i.cod_tipo_ofer INTO lsCodTipoOfer
                FROM pre_tipo_ofert i
               WHERE i.oid_tipo_ofer = lnOidTipoOfer;
           EXCEPTION WHEN NO_DATA_FOUND THEN
              lsCodTipoOfer := NULL;
           END;

           -- Se Obtienen el Código del Producto, la Descripción y Costo Estimado
           BEGIN
               SELECT mp.cod_sap, s.val_i18n, mp.val_cost_estd INTO lsCodSap, lsDescProd, lnCostEstd
                 FROM mae_produ mp,
                      gen_i18n_sicc_pais s
                WHERE mp.oid_prod = tablaRegCCostoVentasrecord(x).oidProd
                  AND mp.oid_prod = s.val_oid
                  AND s.attr_enti = 'MAE_PRODU';
           EXCEPTION WHEN NO_DATA_FOUND THEN
                lsCodSap   := NULL;
                lsDescProd := NULL;
                lnCostEstd := NULL;
           END;

           -- Se inserta en la tabla temporal
           INSERT INTO COS_TMP_REPOR_CCVTA(
             OID_REP_CCOS_VENT                                  ,               VAL_MES                             ,
             COD_PERI                                           ,               VAL_FECH_FACT                       ,
             COD_TIPO_OFER                                      ,               COD_SAP                             ,
             VAL_DESC_PROD                                      ,               NUM_UNID_ATEN                       ,
             VAL_COST_ESTD                                      ,               VAL_IMPO_SIMP_TOTA                  ,
             VAL_IMPO_SIMP_UNIT                                 ,               VAL_DSCT_VOLU                       ,
             VAL_DSCT_CMRC                                      ,               VAL_IMPO_FACT_TOTA                  ,
             OID_SOLI_POSI                                      ,               OID_PROD                            ,
             VAL_PREC_CATA_UNIT                                 ,               VAL_PREC_SIN_IMPU_TOTA               )
           VALUES(
             TO_NUMBER(psOidSecuencia)                          , tablaRegCCostoVentasrecord(x).valMes              ,
             tablaRegCCostoVentasrecord(x).codPeriodo           , tablaRegCCostoVentasrecord(x).valFechFact         ,
             lsCodTipoOfer                                      , lsCodSap                                          ,
             lsDescProd                                         , tablaRegCCostoVentasrecord(x).numUnidAten         ,
             lnCostEstd                                         , tablaRegCCostoVentasrecord(x).valImpoSimpTota     ,
             tablaRegCCostoVentasrecord(x).valImpoSimpUnit      , tablaRegCCostoVentasrecord(x).valDsctVolu         ,
             tablaRegCCostoVentasrecord(x).valDsctCmrc          , tablaRegCCostoVentasrecord(x).valImpoFactTota     ,
             tablaRegCCostoVentasrecord(x).oidSoliPosi          , tablaRegCCostoVentasrecord(x).oidProd             ,
             tablaRegCCostoVentasrecord(x).val_prec_cata_unit   , tablaRegCCostoVentasrecord(x).val_prec_sin_impu_tota);

           END LOOP;

         END IF;

       EXIT WHEN  REPOR_CCOS_VENTA%NOTFOUND;
     END LOOP;
   CLOSE REPOR_CCOS_VENTA;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COS_PR_GENER_REPOR_CCOST_VENTA: '||ls_sqlerrm);
 END COS_PR_GENER_REPOR_CCOST_VENTA;

/*********************************************************************************
     Descripcion       : Generar reporte de Cierre de Costos - Devoluciones
     Autor             : Nicolás López
     Fecha Creacion    : 18/01/2011
     Parametros Entrada :
                psCodigoPais
                psMesActual
                psOidSecuencia
**********************************************************************************/
 PROCEDURE COS_PR_GENER_REPOR_CCOST_DEVOL (
   psCodigoPais           VARCHAR2,
   psMesActual            VARCHAR2,
   psOidSecuencia         VARCHAR2
 )
 IS
   W_FILAS              NUMBER := 5000;
   lnIdPais             NUMBER;
   lsNombPeri           cra_perio.val_nomb_peri%TYPE;
   lsCodOper            rec_opera.cod_oper%TYPE;
   lsCodTipoProg        inc_concu_tipo_prog.cod_tipo_prog%TYPE;
   lnOidSoliCabe        ped_solic_cabec.oid_soli_cabe%TYPE;
   lnOidPeriDocuRefe    rec_cabec_recla.perd_oid_peri_docu_refe%TYPE;
   lnImpoBiNeta         NUMBER(15,2);
   lnPrecSinImpuTotaLoc ped_solic_posic.val_prec_sin_impu_tota_loca%TYPE;

   TYPE tmptablaCCostoDevolucion IS RECORD(
     valMes            VARCHAR2(6),
     valFecNC          VARCHAR2(8),
     producto          mae_produ.cod_sap%TYPE,
     descripcion       gen_i18n_sicc_pais.val_i18n%TYPE,
     origen            seg_subac.cod_sbac%TYPE,
     negocio           mae_negoc.cod_nego%TYPE,
     marca             seg_marca_produ.cod_marc_prod%TYPE,
     numUnidAten       fac_docum_conta_linea.num_unid_aten%TYPE,
     costEstUnit       mae_produ.val_cost_estd%TYPE,
     costEstTotal      NUMBER(15,2),
     precioCataUnit    fac_docum_conta_linea.val_prec_cata_unit_loca%TYPE,
     valImpoSimpTota   NUMBER(15,2),
     valImpoSimpUnit   NUMBER(15,2),
     valDsctVolu       fac_docum_conta_linea.imp_desc_sin_impu_tota_loca%TYPE,
     valDsctCmrc       fac_docum_conta_linea.val_prec_cont_tota_loca%TYPE,
     valImpoNCTotal    NUMBER(15,2),
     oidSoliCabe       ped_solic_cabec.oid_soli_cabe%TYPE,
     oidTipoProg       inc_concu_tipo_prog.oid_tipo_prog%TYPE,
     oidSoliPosi       ped_solic_posic.oid_soli_posi%TYPE,
     valPrecSinImpuTot fac_docum_conta_linea.val_prec_sin_impu_tota_loca%TYPE
   );

   TYPE tablaRegCCostoDevolucion IS TABLE OF tmptablaCCostoDevolucion;
   tablaRegCCostoDevolucionrecord tablaRegCCostoDevolucion;

   -- Se obtienen los datos de centro de Costo - Devoluciones
   CURSOR REPOR_CCOS_DEVOL(ln_OidPais NUMBER) IS
        SELECT TO_CHAR(cab.fec_fact, 'yyyymm') as Mes,
               TO_CHAR(cab.fec_fact, 'yyyymmdd') as FecNC,
               prd.cod_sap as Producto,
               gen.val_i18n as Descripción,
               subac.cod_sbac as Origen,
               neg.cod_nego as Negocio,
               marc.cod_marc_prod as Marca,
               det.num_unid_aten as Unidades,
               prd.val_cost_estd as Costo_Standard_Unitario,
               det.num_unid_aten * prd.val_cost_estd as Costo_Standard_Total,
               det.val_prec_cata_unit_loca as Precio_Catalogo_Unitario,
               DECODE(det.val_prec_cata_unit_loca,
                      0,
                      det.val_prec_cont_tota_loca,
                      det.VAL_PREC_SIN_IMPU_TOTA_LOCA) as Importe_Sin_Impuesto_Total,
               DECODE(det.val_prec_cata_unit_loca,
                      0,
                      det.val_prec_cont_tota_loca,
                      det.VAL_PREC_SIN_IMPU_TOTA_LOCA) as Importe_Sin_Impuesto_Unit,
               det.IMP_DESC_SIN_IMPU_TOTA_LOCA as Dscto_Volumen,
               det.VAL_PREC_CONT_TOTA_LOCA as Dscto_Comercial,
               DECODE(det.val_prec_cata_unit_loca,
                0,
                0,
                det.val_prec_fact_tota_loca) as Importe_NotaCredito_Total,
               cab.soca_oid_soli_cabe,
               cab.ictp_oid_tipo_prog,
               det.sopo_oid_soli_posi,
               det.val_prec_sin_impu_tota_loca
          FROM fac_docum_conta_cabec cab,
               fac_docum_conta_linea det,
               mae_produ             prd,
               mae_negoc             neg,
               seg_marca_produ       marc,
               seg_subac             subac,
               gen_i18n_sicc_pais    gen
         WHERE cab.fec_fact is not null -- Facturadas
           AND cab.pais_oid_pais      = ln_OidPais
           AND to_char(cab.fec_fact, 'YYYYMM') = psMesActual -- MES
           AND cab.tido_oid_tipo_docu in (31, 32, 33)
           AND cab.oid_cabe = det.dcca_oid_cabe
           AND det.prod_oid_prod = prd.oid_prod
           AND prd.nego_oid_nego = neg.oid_nego
           AND prd.mapr_oid_marc_prod = marc.oid_marc_prod
           AND cab.sbac_oid_sbac = subac.oid_sbac
           AND gen.attr_enti = 'MAE_PRODU'
           AND gen.val_oid = det.prod_oid_prod;

 BEGIN
   /* obteniendo id Pais */
   lnIdPais        := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais); -- id del pais consultante

   OPEN REPOR_CCOS_DEVOL(lnIdPais);
     LOOP
       FETCH REPOR_CCOS_DEVOL BULK COLLECT INTO tablaRegCCostoDevolucionrecord LIMIT W_FILAS;

         IF tablaRegCCostoDevolucionrecord.COUNT > 0 THEN

           FOR x IN tablaRegCCostoDevolucionrecord.FIRST .. tablaRegCCostoDevolucionrecord.LAST LOOP

           -- Obtenemos el id de Solicitud de Pedidos Cabecera
           BEGIN
              SELECT l.oid_soli_cabe
                INTO lnOidSoliCabe
                FROM ped_solic_cabec l
               WHERE l.soca_oid_soli_cabe = tablaRegCCostoDevolucionrecord(x).oidSoliCabe;
           EXCEPTION WHEN NO_DATA_FOUND THEN
                lnOidSoliCabe := NULL;
           END;

           -- Obtenemos el Id de Periodo de referencia
           BEGIN
              SELECT rcr.PERD_OID_PERI_DOCU_REFE INTO lnOidPeriDocuRefe
                FROM rec_solic_opera rso,
                     rec_opera_recla ror,
                     rec_cabec_recla rcr
               WHERE rso.OPRE_OID_OPER_RECL = ror.OID_OPER_RECL
                 AND ror.CARE_OID_CABE_RECL = rcr.OID_CABE_RECL
                 AND rso.soca_oid_soli_cabe = lnOidSoliCabe
                 AND ROWNUM = 1;
            EXCEPTION WHEN NO_DATA_FOUND THEN
                 lnOidPeriDocuRefe := NULL;
            END;

            -- Obtenemos la descripción larga de Campaña
            BEGIN
              SELECT val_nomb_peri INTO lsNombPeri
                FROM cra_perio
               WHERE oid_peri = lnOidPeriDocuRefe;
            EXCEPTION WHEN NO_DATA_FOUND THEN
                 lsNombPeri := NULL;
            END;

            -- Obtenemos el Código de Operación
            BEGIN
              SELECT rop.COD_OPER
                INTO lsCodOper
                FROM rec_solic_opera rso, rec_opera rop
               WHERE rso.TSPA_OID_TIPO_SOLI_PAIS =
                     rop.TSPA_OID_SOLI_PAIS_GENE
                 AND rso.soca_oid_soli_cabe = lnOidSoliCabe
                 AND ROWNUM = 1;
            EXCEPTION WHEN NO_DATA_FOUND THEN
                   lsCodOper := NULL;
            END;

            -- Obtenemos el código de Tipo de Programación
            BEGIN
                SELECT pr.cod_tipo_prog INTO lsCodTipoProg
                FROM INC_CONCU_TIPO_PROG pr
                WHERE pr.oid_tipo_prog = tablaRegCCostoDevolucionrecord(x).oidTipoProg;
            EXCEPTION WHEN NO_DATA_FOUND THEN
                lsCodTipoProg := NULL;
            END;

            BEGIN
              SELECT psp.val_prec_sin_impu_tota_loca
                INTO lnPrecSinImpuTotaLoc
                FROM PED_SOLIC_POSIC psp
               WHERE psp.oid_soli_posi = tablaRegCCostoDevolucionrecord(x).oidSoliPosi;
            EXCEPTION WHEN NO_DATA_FOUND THEN
                lnPrecSinImpuTotaLoc := NULL;
            END;

            IF (lsCodTipoProg = 'B' OR  lsCodTipoProg = 'C' OR lsCodTipoProg = 'R') THEN
               lnImpoBiNeta := 0;
            ELSE
               lnImpoBiNeta := tablaRegCCostoDevolucionrecord(x).valPrecSinImpuTot -
                               ((CASE WHEN tablaRegCCostoDevolucionrecord(x).valDsctCmrc = 0 THEN
                                0 ELSE lnPrecSinImpuTotaLoc END) + tablaRegCCostoDevolucionrecord(x).valDsctVolu);
            END IF;

            -- Se inserta en la tabla temporal
           INSERT INTO COS_TMP_REPOR_CCDEV(
             OID_REP_CCOS_DEVO                                  ,               VAL_MES                             ,
             VAL_FECH_NCRE                                      ,               VAL_NOMB_PERI                       ,
             COD_SAP                                            ,               VAL_DESC_PROD                       ,
             COD_OPER                                           ,               COD_TIPO_PROG                       ,
             COD_SBAC                                           ,               COD_NEGO                            ,
             COD_MARC_PROD                                      ,               NUM_UNID_ATEN                       ,
             VAL_COST_ESTD                                      ,               VAL_COST_ESTT                       ,
             VAL_PREC_CATA_UNIT                                 ,               VAL_IMPO_SIMP_TOTA                  ,
             VAL_IMPO_SIMP_UNIT                                 ,               VAL_DSCT_VOLU                       ,
             VAL_DSCT_CMRC                                      ,               VAL_IMPO_NCRE_TOTA                  ,
             VAL_IMPO_BINT                                      )
           VALUES(
             TO_NUMBER(psOidSecuencia)                          , tablaRegCCostoDevolucionrecord(x).valMes          ,
             tablaRegCCostoDevolucionrecord(x).valFecNC         , lsNombPeri                                        ,
             tablaRegCCostoDevolucionrecord(x).producto         , tablaRegCCostoDevolucionrecord(x).descripcion     ,
             lsCodOper                                          , lsCodTipoProg                                     ,
             tablaRegCCostoDevolucionrecord(x).origen           , tablaRegCCostoDevolucionrecord(x).negocio         ,
             tablaRegCCostoDevolucionrecord(x).marca            , tablaRegCCostoDevolucionrecord(x).numUnidAten     ,
             tablaRegCCostoDevolucionrecord(x).costEstUnit      , tablaRegCCostoDevolucionrecord(x).costEstTotal    ,
             tablaRegCCostoDevolucionrecord(x).precioCataUnit   , tablaRegCCostoDevolucionrecord(x).valImpoSimpTota ,
             tablaRegCCostoDevolucionrecord(x).valImpoSimpUnit  ,tablaRegCCostoDevolucionrecord(x).valDsctVolu      ,
             tablaRegCCostoDevolucionrecord(x).valDsctCmrc      , tablaRegCCostoDevolucionrecord(x).valImpoNCTotal  ,
             lnImpoBiNeta                                     );

           END LOOP;

         END IF;

       EXIT WHEN  REPOR_CCOS_DEVOL%NOTFOUND;
     END LOOP;
   CLOSE REPOR_CCOS_DEVOL;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COS_PR_GENER_REPOR_CCOST_DEVOL: '||ls_sqlerrm);
 END COS_PR_GENER_REPOR_CCOST_DEVOL;

end COS_PKG_REPOR;
/

