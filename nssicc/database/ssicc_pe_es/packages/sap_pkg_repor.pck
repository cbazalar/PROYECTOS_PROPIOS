CREATE OR REPLACE PACKAGE SAP_PKG_REPOR IS

   TIPO_REPORTE_MATERIAL_PROM CONSTANT VARCHAR2(3) := 'M';
   TIPO_REPORTE_PROMO_USUARIO CONSTANT VARCHAR2(3) := 'P';
   TIPO_REPORTE_VENTA_LINEA   CONSTANT VARCHAR2(3) := 'V';
   TIPO_REPORTE_ROL_SOCIAL    CONSTANT VARCHAR2(3) := 'S';
   TIPO_OPERACION_VENTA       CONSTANT VARCHAR2(3) := 'T';
   TIPO_OPERACION_DEVOLUCION  CONSTANT VARCHAR2(3) := 'D';
   W_FILAS                    NUMBER := 5000;
   ln_sqlcode                 NUMBER(10);
   ls_sqlerrm                 VARCHAR2(1000);


/**************************************************************************
Descripcion       : Se genera informacion para el reporte de Cuadre de 
                    Productos SAP vs SICC
Fecha Creacion    : 10/06/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE SAP_PR_GENER_REPOR_CUADR(psCodigoPais           VARCHAR2);
 
/**************************************************************************
  Descripcion       : REPORTE DE UNIDADES A DESPACHAR DE CODIGO SAP
  Fecha Creacion    : 27/11/2013
  Parametros Entrada:
    fec_fact     :  fecha de facturacion
    campania     :  campania
  
  Autor             : BelCorp
  Creado            : Frank Gonzales A.
  ***************************************************************************/
  PROCEDURE SAP_PR_REPOR_UNIDA_DESPA_CODIG(ps_varCampa VARCHAR2,
                                           ps_fec_fact VARCHAR2);

END SAP_PKG_REPOR;
/
CREATE OR REPLACE PACKAGE BODY SAP_PKG_REPOR IS

/**************************************************************************
Descripcion       : Se genera informacion para el reporte de Cuadre de 
                    Productos SAP vs SICC
Fecha Creacion    : 10/06/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE SAP_PR_GENER_REPOR_CUADR(psCodigoPais           VARCHAR2)
IS
  lnIdPais               NUMBER;

  -- Se obtienen los datos 
  CURSOR c_Productos IS
    select OCR_SOLIC_PEDIDOS.GEN_FN_CODSAP_PROD(saldo_final.PROD_OID_PROD) COD_SAP_sicc,
             PQ_APL_AUX.VALOR_GEN_I18N_SICC(1,saldo_final.PROD_OID_PROD,'MAE_PRODU') des_sap_sicc, 
             saldo_final.NUM_UNID_CON_BR   NUM_UNID_CON_BR,
             saldo_final.NUM_UNID_SIN_BR   NUM_UNID_SIN_BR,
             saldo_final.NUM_UNID_CON_BR + saldo_final.NUM_UNID_SIN_BR  unidades_sicc
    from (         
        select distinct saldo_sicc.prod_oid_prod, 
                 sum(saldo_sicc.NUM_UNID_CON_BR) NUM_UNID_CON_BR,
                 sum(saldo_sicc.num_unid_SIN_BR) num_unid_SIN_BR
        from (         
            SELECT  rl.prod_oid_prod, 0 NUM_UNID_CON_BR , rl.num_unid_recl num_unid_SIN_BR
              FROM rec_cabec_recla           rc,
                   rec_opera_recla           ro,
                   rec_linea_opera_recla     rl,
                   rec_opera                 op,
                   rec_tipos_opera           top,
                   mae_produ                 mp,
                   int_rec_opera_homol_borec rh,
                   int_mae_opera_homol_borec mh
             WHERE rc.oid_cabe_recl = ro.care_oid_cabe_recl
               AND ro.oid_oper_recl = rl.opre_oid_oper_recl
               AND rc.esre_oid_esta_recl IN
                   (4, 6)
               AND ro.esop_oid_esta_oper IN
                   (5, 2)
               AND ro.tiop_oid_tipo_oper = top.oid_tipo_oper
               AND top.rope_oid_oper = op.oid_oper
               AND rl.timo_oid_tipo_movi = 2
               AND rl.num_unid_recl > 0
               AND op.ind_anul <> 1
               AND (op.ind_falt_merc = 0 OR
                   (op.ind_falt_merc = 1 AND op.ind_devu_fisi_fact = 0 AND
                   rl.sopo_oid_soli_posi IS NULL))
               AND mp.oid_prod = rl.prod_oid_prod
               AND rh.cod_pais = psCodigoPais   ---- varPais
               AND mh.cod_pais = psCodigoPais   ---- varPais
               AND rh.cod_oper_rec = op.cod_oper
               AND rh.cod_oper_homl = mh.cod_oper_homl
               AND NOT EXISTS
             (SELECT rc.oid_cabe_recl
                      FROM int_rec_linea_borec lb
                     WHERE lb.care_oid_cabe_recl = rc.oid_cabe_recl)
               AND op.cod_oper NOT IN
                   (SELECT cod_oper
                      FROM int_rec_opera_exclu
                     WHERE cod_pais = psCodigoPais) 
        union all
            select LB.PROD_OID_PROD  , LB.NUM_UNID_RECL  NUM_UNID_CON_BR  , 0 num_unid_SIN_BR       
                FROM INT_REC_LINEA_BOREC LB,INT_REC_CABEC_BOREC CB,
                     rec_linea_opera_recla rlo, SEG_PERIO_CORPO SP,
                     REC_OPERA_RECLA RO, REC_TIPOS_OPERA TOP,
                   REC_OPERA OP, BEL_ALMAC AL,
                   SEG_PAIS P,
                   (SELECT COD_OPER
                        FROM INT_MAE_OPER_HOMOL MM, INT_REC_OPER_HOMOL RR
                     WHERE RR.COD_PAIS = RR.COD_PAIS
                         AND RR.COD_PAIS = psCodigoPais   ---- varPais
                       AND RR.COD_OPER_HOMOL = MM.COD_OPER_HOMOL
                       AND MM.COD_OPER_HOMOL = 'YT') T_OPERA,
                    REC_PARAM_ENVIO_SAP PAR
             WHERE CB.COD_CABE_BORE = LB.COD_CABE_BORE
                and LB.LOR_OID_LINE_OPER_RECL = RLO.OID_LINE_OPER_RECL
               AND CB.IND_PROC_ALMA_FISI = 'V'
               AND LB.IND_PROC_ALMA_FISI = 'V'
                 AND CB.COD_PAIS = psCodigoPais   ---- varPais
               AND CB.COD_PERI_PROC = SP.COD_PERI
               AND SP.TIPE_OID_TIPO_PERI IN
                     (SELECT OID_TIPO_PERI
                        FROM SEG_TIPO_PERIO
                       WHERE COD_TIPO_PERI = 'CM')
               AND RO.OID_OPER_RECL = LB.OPRE_OID_OPER_RECL
               AND TOP.OID_TIPO_OPER = RO.TIOP_OID_TIPO_OPER
               AND TOP.ROPE_OID_OPER = OP.OID_OPER
               AND OP.ALMC_OID_ALMA = AL.OID_ALMA
               AND P.COD_PAIS = CB.COD_PAIS
               AND P.COD_PAIS = PAR.COD_PAIS
               AND T_OPERA.COD_OPER = LB.COD_OPER
         ) saldo_sicc 
        group by saldo_sicc.prod_oid_prod
    ) saldo_final ;
             
  TYPE interfazProductos IS RECORD(
    codigoSAP            MAE_PRODU.COD_SAP%TYPE,       
    descripcion          VARCHAR2(100),
    unidadesconBR        NUMBER,
    unidadessinBR        NUMBER,
    unidades             NUMBER
  );

  TYPE interfazProductosTab IS TABLE OF interfazProductos;
  interfazRecordN interfazProductosTab;
  
  lnOcurrencias   NUMBER;
               
BEGIN
  /* obteniendo id's */
  lnIdPais    := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais); -- id del pais consultante
   
  -- Se procesan los datos recuperados en el cursor
  OPEN c_Productos;
  LOOP
    FETCH c_Productos BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        SELECT COUNT(1)
          INTO lnOcurrencias
          FROM REC_TMP_CUADRE_SAP_SICC
         WHERE COD_SAP = interfazRecordN(x).codigoSAP;
           
        --Actualizamos las unidades SICC   
        IF(lnOcurrencias > 0) THEN
          UPDATE REC_TMP_CUADRE_SAP_SICC
             SET VAL_UNID_SICC = interfazRecordN(x).unidades,
                 VAL_UNID_SICC_CON_BR = interfazRecordN(x).unidadesconBR,
                 VAL_UNID_SICC_SIN_BR = interfazRecordN(x).unidadessinBR
           WHERE COD_SAP = interfazRecordN(x).codigoSAP;
           
        ELSE  --Si no se encuentra producto, se inserta registro en la tabla temporal
          INSERT INTO REC_TMP_CUADRE_SAP_SICC
            (COD_SAP,
             DES_PROD,
             VAL_UNID_SICC,
             VAL_UNID_SICC_CON_BR,
             VAL_UNID_SICC_SIN_BR)
          VALUES
            (interfazRecordN(x).codigoSAP,
             interfazRecordN(x).descripcion,
             interfazRecordN(x).unidades,
             interfazRecordN(x).unidadesconBR,
             interfazRecordN(x).unidadessinBR);   
        END IF;
        
      END LOOP;

    END IF;
    EXIT WHEN c_Productos%NOTFOUND;
  END LOOP;
  CLOSE c_Productos;
    
  --Actualizamos las Diferencias    
  UPDATE REC_TMP_CUADRE_SAP_SICC
     SET VAL_DIFE = NVL(VAL_UNID_SICC, 0) - NVL(VAL_UNID_SAP, 0);
    
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SAP_PR_GENER_REPOR_CUADR: '||ls_sqlerrm);
END SAP_PR_GENER_REPOR_CUADR;
 
/**************************************************************************
  Descripcion       : REPORTE DE UNIDADES A DESPACHAR DE CODIGO SAP
  Fecha Creacion    : 27/11/2013
  Parametros Entrada:
    fec_fact     :  fecha de facturacion
    campania     :  campania
  
  Autor             : BelCorp
  Creado            : Frank Gonzales A.
  ***************************************************************************/
  PROCEDURE SAP_PR_REPOR_UNIDA_DESPA_CODIG(ps_varCampa VARCHAR2,
                                           ps_fec_fact VARCHAR2) IS
  
  BEGIN
  
	DELETE FROM SAP_TMP_UNIDA_DESPA_CODIG;
	
    INSERT INTO SAP_TMP_UNIDA_DESPA_CODIG
      (COD_SAP, DES_PROD, NUM_UNID_ATEN)
      SELECT DISTINCT MP.COD_SAP,
                      OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(MP.OID_PROD) AS DES_PROD,
                      SUM(PSP.NUM_UNID_ATEN) AS NUM_UNID_ATEN
        FROM PED_SOLIC_CABEC PSC,
             PED_SOLIC_POSIC PSP,
             MAE_PRODU       MP,
             SAP_CONSO_CODIG SCC
       WHERE PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
         AND PSP.PROD_OID_PROD = MP.OID_PROD
         AND PSC.FEC_FACT = TO_DATE(ps_fec_fact, 'DD/MM/YYYY')
         AND PSC.IND_OC = 1
         AND MP.COD_SAP = SCC.COD_SAP
         AND SCC.COD_PERI = ps_varCampa
       GROUP BY MP.COD_SAP, MP.OID_PROD;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR SAP_PR_REPOR_UNIDA_DESPA_CODIG: ' ||
                              ls_sqlerrm);
  END SAP_PR_REPOR_UNIDA_DESPA_CODIG;
  
END SAP_PKG_REPOR;
/
