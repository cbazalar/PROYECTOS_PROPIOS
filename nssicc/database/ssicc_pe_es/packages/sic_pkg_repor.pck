CREATE OR REPLACE PACKAGE sic_pkg_repor IS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  w_filas    NUMBER := 1000;

/***************************************************************************
Descripcion       : Genera la data para el reporte de Detalle FNA
Fecha Creacion    : 30/11/2009
Autor             : Jose Luis Rodriguez
Parametros        :
            psCodPais : codigo Pais
            psCodPeriodo : codigo Periodo

***************************************************************************/
PROCEDURE SIC_PR_REPOR_DETAL_FNA(
    psCodPais VARCHAR2,
    psCodPeriodo VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera el Reporte Detalle de Unidades Atendidas Faltantes
Fecha Creacion    : 20/01/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SIC_PR_REPOR_DETAL_UNIDA_ATEND(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
) ;

END sic_pkg_repor;
 
/
CREATE OR REPLACE PACKAGE BODY sic_pkg_repor IS

/***************************************************************************
Descripcion       : Genera la data para el reporte de Detalle FNA
Fecha Creacion    : 30/11/2009
Autor             : Jose Luis Rodriguez
Parametros        :
            psCodPais : codigo Pais
            psCodPeriodo : codigo Periodo

***************************************************************************/
PROCEDURE SIC_PR_REPOR_DETAL_FNA(
    psCodPais VARCHAR2,
    psCodPeriodo VARCHAR2
    )
IS
 lnIdPais     seg_pais.oid_pais%TYPE;
 lnIdPeriodo  seg_perio_corpo.oid_peri%TYPE;
 lnIdEstaPosi ped_estad_posic.oid_esta_posi%TYPE;

BEGIN

  /*Obteniendo el oid del Pais y del periodo*/
  lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  lnIdPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodPeriodo);

  /*Obteniendo el oid del estado de la posicion de solicitud con codigo AN*/
  SELECT oid_esta_posi
    INTO lnIdEstaPosi
    FROM ped_estad_posic
   WHERE cod_esta_posi='AN';

  /* Borrando las tablas temporales */
  EXECUTE IMMEDIATE 'TRUNCATE TABLE SIC_GTT_SOLIC_CABEC';
  EXECUTE IMMEDIATE 'TRUNCATE TABLE SIC_TMP_DETAL_FNA';

  INSERT INTO SIC_GTT_SOLIC_CABEC (
           OID_SOLI_CABE, FEC_PROG_FACT,
           FEC_FACT, NUM_CLIEN,
           VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
           MONE_OID_MONE, TIDS_OID_TIPO_DESP,
           ALMC_OID_ALMA, MODU_OID_MODU,
           TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
           PERD_OID_PERI, SOCA_OID_SOLI_CABE,
           CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
           CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
           CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
           SOCI_OID_SOCI, SBAC_OID_SBAC,
           TERR_OID_TERR, ZZON_OID_ZONA,
           IND_ESTA, IND_IMPR,
           IND_EXEN_FLET, VAL_NUME_SOLI,
           VAL_USUA, VAL_TASA_IMPU,
           FEC_CRON, IND_PERM_UNIO_SOL,
           IND_GENE_CC, IND_APLI_MANU,
           VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
           NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
           FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
           VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
           VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
           VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
           VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
           VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
           VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
           VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
           VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
           VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
           VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
           VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
           VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
           VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
           VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
           VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
           VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
           VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
           VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
           VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
           VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
           VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
           NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
           IND_OC, IND_PEDI_PRUE,
           IND_TS_NO_CONSO, VAL_GLOS_OBSE,
           VAL_OBSE_REVI, NUM_PREM,
           VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
           PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
           VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
           ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
           GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
           ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
           FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
           ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
           ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
           OPER_OID_OPER, PROC_OID_PROC,
           SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
           CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
           NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
           IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
      )
     SELECT
           OID_SOLI_CABE, FEC_PROG_FACT,
           FEC_FACT, NUM_CLIEN,
           VAL_GRUP_REVE, TSPA_OID_TIPO_SOLI_PAIS,
           MONE_OID_MONE, TIDS_OID_TIPO_DESP,
           ALMC_OID_ALMA, MODU_OID_MODU,
           TICL_OID_TIPO_CLIE, TAIM_OID_TASA_IMPU,
           PERD_OID_PERI, SOCA_OID_SOLI_CABE,
           CLIE_OID_CLIE, CLIE_OID_CLIE_RECE_FACT,
           CLIE_OID_CLIE_PAGA, CLIE_OID_CLIE_DEST,
           CLDI_OID_CLIE_DIRE, TDOC_OID_TIPO_DOCU,
           SOCI_OID_SOCI, SBAC_OID_SBAC,
           TERR_OID_TERR, ZZON_OID_ZONA,
           IND_ESTA, IND_IMPR,
           IND_EXEN_FLET, VAL_NUME_SOLI,
           VAL_USUA, VAL_TASA_IMPU,
           FEC_CRON, IND_PERM_UNIO_SOL,
           IND_GENE_CC, IND_APLI_MANU,
           VAL_TIPO_CAMB, NUM_DOCU_CONT_INTE,
           NUM_DOCU_ORIG, VAL_LOTE_REPO_FALT,
           FEC_REPO_FALT, VAL_BASE_FLET_LOCA,
           VAL_IMPO_FLET_LOCA, VAL_IMPO_FLET_TOTA_LOCA,
           VAL_IMPO_FLET_SIN_IMPU_TOTA, VAL_RECA_FLET_LOCA,
           VAL_OTRO_RECA_LOCA, VAL_TOTA_PAGA_LOCA,
           VAL_PREC_CATA_TOTA_LOCA, VAL_PREC_CATA_SIN_IMPU_TOTA,
           VAL_PREC_FACT_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_LOCA,
           VAL_IMPO_DESC_1_TOTA_LOCA, VAL_IMPO_DESC_1_TOTA_DOCU,
           VAL_IMPO_DESC_1_SIN_IMPU_TOTA, VAL_IMPO_DESC_3_TOTA_DOCU,
           VAL_IMPO_DESC_3_SIN_IMPU_TOTA, VAL_IMPO_DESC_TOTA_LOCA,
           VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC, VAL_IMPO_REDO_LOCA,
           VAL_BASE_FLET_DOCU, VAL_IMPO_FLET_DOCU,
           VAL_IMPO_DESC_TOTA_DOCU, VAL_IMPO_FLET_SIN_IMPU_DOCU,
           VAL_RECA_FLET_DOCU, VAL_OTRO_RECA_DOCU,
           VAL_TOTA_FLET_DOCU, VAL_IMPO_FLET_TOTA_DOCU,
           VAL_TOTA_FLET_LOCA, VAL_TOTA_PAGA_DOCU,
           VAL_PREC_CATA_TOTA_DOCU, VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
           VAL_PREC_CONT_TOTA_LOCA, VAL_PREC_CONT_SIN_IMPU_TOTA,
           VAL_PREC_CONT_SIN_IMPU_TOTA_1, VAL_PREC_FACT_TOTA_DOCU,
           VAL_PREC_CATA_TOTA_LOC_UNI_DEM, VAL_PREC_NETO_TOTA_DOCU,
           VAL_PREC_NETO_TOTA_LOCA, VAL_IMPO_IMPU_TOTA_DOCU,
           VAL_IMPO_REDO_DOCU, VAL_IMPO_REDO_CONS_LOCA,
           VAL_IMPO_REDO_CONS_DOCU, VAL_UNID_DEMA_REAL_TOTA,
           NUM_UNID_POR_ATEN_TOTA, NUM_UNID_ATEN_TOTA,
           IND_OC, IND_PEDI_PRUE,
           IND_TS_NO_CONSO, VAL_GLOS_OBSE,
           VAL_OBSE_REVI, NUM_PREM,
           VAL_IMPO_DESC_3_TOTA_LOCA, VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
           PAIS_OID_PAIS, TIDO_OID_TIPO_DOCU,
           VEPO_OID_VALO_ESTR_GEOP, RECQ_OID_RESU_CHEQ,
           ESSO_OID_ESTA_SOLI, COPA_OID_PARA_GENE,
           GRPR_OID_GRUP_PROC, SBTI_OID_SUBT_CLIE,
           ACFI_OID_ACCE_FISI, TSPA_OID_TIPO_SOLI_PAIS_CONS,
           FOPA_OID_FORM_PAGO, CLIE_OID_CONS_ASOC,
           ESPE_OID_ESTA_PEDI, CLSO_OID_CLAS_SOLI,
           ZTAD_OID_TERR_ADMI, INRE_OID_INDI_REVI,
           OPER_OID_OPER, PROC_OID_PROC,
           SOCA_OID_DOCU_REFE, TCCL_OID_TCCL_FLET,
           CLAS_OID_CLAS_FLET, VAL_PUNT_EMIS,
           NUM_LOTE_FACT, VAL_PREC_CONT_TOTA_DOCU,
           IND_INTE_LARI_GENE, FEC_PROG_FACT_COMP
     FROM  PED_SOLIC_CABEC A
    WHERE  A.PAIS_OID_PAIS = lnIdPais
     AND   A.PERD_OID_PERI=lnIdPeriodo
     AND   A.FEC_FACT IS NOT NULL
     AND   EXISTS (SELECT *
                     FROM PED_CLASE_SOLIC     PCS,
                          PED_TIPO_SOLIC      PTS,
                          PED_TIPO_SOLIC_PAIS PTSP
                    WHERE PCS.COD_CLAS_SOLI = 'O1'
                      AND PTS.CLSO_OID_CLAS_SOLI = PCS.OID_CLAS_SOLI
                      AND PTSP.TSOL_OID_TIPO_SOLI = PTS.OID_TIPO_SOLI
                      AND A.TSPA_OID_TIPO_SOLI_PAIS = PTSP.OID_TIPO_SOLI_PAIS);

   /*Insertando en la tabla temporal*/
   INSERT INTO SIC_TMP_DETAL_FNA(
          cod_regi,
          des_regi,
          des_zona,
          cod_clie,
          val_nom1,
          val_ape1,
          val_ape2,
          val_codi_vent,
          cod_sap,
          des_prod,
          cod_tipo_ofer,
          num_pagi_cata,
          des_cata,
          uni_falt,
          fec_prog_fact
     )
   SELECT G.COD_REGI,
          G.DES_REGI,
          F.DES_ZONA,
          M.COD_CLIE,
          TRIM(M.VAL_NOM1),
          TRIM(M.VAL_APE1),
          TRIM(M.VAL_APE2),
          Y.VAL_CODI_VENT,
          S.COD_SAP,
          INT_PKG_RECLA.GEN_FN_DESC_PROD(S.OID_PROD),
          TOF.COD_TIPO_OFER,
          OD.NUM_PAGI_CATA,
         (SELECT B.DES_CATA FROM PRE_CATAL B WHERE OD.OCAT_OID_CATAL = B.OID_CATA),
          SUM(Y.NUM_UNID_DEMA_REAL - Y.NUM_UNID_ATEN),
          x.fec_prog_fact
     FROM SIC_GTT_SOLIC_CABEC   X,
          PED_SOLIC_POSIC       Y,
          MAE_CLIEN_UNIDA_ADMIN A,
          MAE_PRODU             S,
          PRE_OFERT_DETAL       OD,
          PRE_OFERT             O,
          PRE_TIPO_OFERT        TOF,
          ZON_TERRI_ADMIN       C,
          ZON_TERRI             D,
          ZON_SECCI             E,
          ZON_ZONA              F,
          ZON_REGIO             G,
          MAE_CLIEN             M
    WHERE X.OID_SOLI_CABE = Y.SOCA_OID_SOLI_CABE
      AND M.OID_CLIE = X.CLIE_OID_CLIE
      AND X.CLIE_OID_CLIE = A.CLIE_OID_CLIE
      AND A.ZTAD_OID_TERR_ADMI = C.OID_TERR_ADMI
      AND C.TERR_OID_TERR = D.OID_TERR
      AND C.ZSCC_OID_SECC = E.OID_SECC
      AND E.ZZON_OID_ZONA = F.OID_ZONA
      AND F.ZORG_OID_REGI = G.OID_REGI
      AND Y.OFDE_OID_DETA_OFER = OD.OID_DETA_OFER
      AND OD.OFER_OID_OFER = O.OID_OFER
      AND TOF.OID_TIPO_OFER = OD.TOFE_OID_TIPO_OFER
      AND Y.PROD_OID_PROD = S.OID_PROD
      AND Y.NUM_UNID_DEMA_REAL - Y.NUM_UNID_ATEN <> 0
      AND Y.ESPO_OID_ESTA_POSI <> lnIdEstaPosi
      AND A.IND_ACTI = 1
 GROUP BY G.COD_REGI,
          G.DES_REGI,
          F.DES_ZONA,
          M.COD_CLIE,
          TRIM(M.VAL_NOM1),
          TRIM(M.VAL_APE1),
          TRIM(M.VAL_APE2),
          Y.VAL_CODI_VENT,
          S.COD_SAP,
          INT_PKG_RECLA.GEN_FN_DESC_PROD(S.OID_PROD),
          TOF.COD_TIPO_OFER,
          OD.NUM_PAGI_CATA,
          OD.OCAT_OID_CATAL,
          x.fec_prog_fact
 ORDER BY 1, 8;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,1,250);
    raise_application_error(-20123,'ERROR SIC_PR_REPOR_DETAL_FNA: ' || ls_sqlerrm);
END SIC_PR_REPOR_DETAL_FNA;


/***************************************************************************
Descripcion       : Genera el Reporte Detalle de Unidades Atendidas Faltantes
Fecha Creacion    : 20/01/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SIC_PR_REPOR_DETAL_UNIDA_ATEND(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR C_REPOR_DETAL_UNIDA IS
    SELECT 
        VAL_CODI_VENT, 
        COD_SAP, 
        DES_PROD, 
        COD_TIPO_OFER, 
        TO_CHAR(FEC_FACT, 'DD/MM/YYYY'), 
        DES_REGI, 
        DES_ZONA, 
        DES_SECC, 
        COD_TERR, 
        COD_CLIE, 
        VAL_NOM1, 
        VAL_NOM2, 
        VAL_APE1, 
        VAL_APE2, 
        VAL_APEL_CASA, 
        NUM_UNID_DEMA, 
        IND_CTRL_STOC, 
        NUM_UNID_DEMA_REAL, 
        NUM_UNID_ATEN, 
        NUM_UNID_FALT, 
        VAL_IMPO_UNIT, 
        VAL_PORC_DESC, 
        VAL_NUME_SOLI, 
        VAL_STAT_CLIE
     FROM SIC_TMP_DETAL_UNIDA_FALTA;

     TYPE detalleUnidadesReg IS RECORD(
         valCodVenta   SIC_TMP_DETAL_UNIDA_FALTA.VAL_CODI_VENT%TYPE, 
         codigoSap     SIC_TMP_DETAL_UNIDA_FALTA.COD_SAP%TYPE, 
         descripcion   SIC_TMP_DETAL_UNIDA_FALTA.DES_PROD%TYPE, 
         tipoOferta    SIC_TMP_DETAL_UNIDA_FALTA.COD_TIPO_OFER%TYPE, 
         fechaFactu    VARCHAR2(20),
         region        SIC_TMP_DETAL_UNIDA_FALTA.DES_REGI%TYPE, 
         zona          SIC_TMP_DETAL_UNIDA_FALTA.DES_ZONA%TYPE, 
         seccion       SIC_TMP_DETAL_UNIDA_FALTA.DES_SECC%TYPE, 
         territorio    SIC_TMP_DETAL_UNIDA_FALTA.COD_TERR%TYPE, 
         codigoClien   SIC_TMP_DETAL_UNIDA_FALTA.COD_CLIE%TYPE, 
         nombre1       SIC_TMP_DETAL_UNIDA_FALTA.VAL_NOM1%TYPE, 
         nombre2       SIC_TMP_DETAL_UNIDA_FALTA.VAL_NOM2%TYPE, 
         apePaterno    SIC_TMP_DETAL_UNIDA_FALTA.VAL_APE1%TYPE, 
         apeMaterno    SIC_TMP_DETAL_UNIDA_FALTA.VAL_APE2%TYPE, 
         apeCasada     SIC_TMP_DETAL_UNIDA_FALTA.VAL_APEL_CASA%TYPE, 
         uniDeman      SIC_TMP_DETAL_UNIDA_FALTA.NUM_UNID_DEMA%TYPE, 
         indCtrlStock  SIC_TMP_DETAL_UNIDA_FALTA.IND_CTRL_STOC%TYPE, 
         uniDemanReal  SIC_TMP_DETAL_UNIDA_FALTA.NUM_UNID_DEMA_REAL%TYPE, 
         uniAtendidas  SIC_TMP_DETAL_UNIDA_FALTA.NUM_UNID_ATEN%TYPE, 
         uniFaltantes  SIC_TMP_DETAL_UNIDA_FALTA.NUM_UNID_FALT%TYPE, 
         importeUni    SIC_TMP_DETAL_UNIDA_FALTA.VAL_IMPO_UNIT%TYPE, 
         porDescuento  SIC_TMP_DETAL_UNIDA_FALTA.VAL_PORC_DESC%TYPE, 
         boletaRecojo  SIC_TMP_DETAL_UNIDA_FALTA.VAL_NUME_SOLI%TYPE, 
         statusCliente SIC_TMP_DETAL_UNIDA_FALTA.VAL_STAT_CLIE%TYPE   
      );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_DETAL_UNIDA;
      LOOP
       FETCH C_REPOR_DETAL_UNIDA BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=     '=T("'|| detalleUnidadesRegRecord(x).valCodVenta || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codigoSap || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).descripcion || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).tipoOferta || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).fechaFactu || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).region || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).zona || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).seccion || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).territorio || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codigoClien || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).nombre1 || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).nombre2 || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).apePaterno || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).apeMaterno || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).apeCasada || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).uniDeman || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).indCtrlStock || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).uniDemanReal || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).uniAtendidas || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).uniFaltantes || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).importeUni || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).porDescuento || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).boletaRecojo || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).statusCliente || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).importeUni || '"' ;
                
                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
                
          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_DETAL_UNIDA%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_DETAL_UNIDA;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR SIC_PR_REPOR_DETAL_UNIDA_ATEND: '||ls_sqlerrm);

END SIC_PR_REPOR_DETAL_UNIDA_ATEND;


END sic_pkg_repor;
/
