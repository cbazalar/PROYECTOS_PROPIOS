CREATE OR REPLACE PACKAGE "LLI_PKG_LEADE_LIST" AS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  W_FILAS    NUMBER := 1000;

  PROCEDURE LLI_PR_CARGA_VENTA_PERIO(PS_COD_PAIS IN VARCHAR2,
                                     PS_COD_PERI IN VARCHAR2);

  /**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar Venta Real Diaria-Acumulada
   Fecha Creacion     : 28/11/2006
   Autor              : Luis Mendoza
  ***************************************************************************/
  PROCEDURE INT_PR_LLI_VENTA_REAL_DIARIA(psCodigoPais              VARCHAR2,
                                         psCodigosistema           VARCHAR2,
                                         psCodigoInterfaz          VARCHAR2,
                                         psNombreArchivo           VARCHAR2,
                                         psCodigoMarca             VARCHAR2,
                                         psCodigoCanal             VARCHAR2,
                                         psCodigoPeriodo           VARCHAR2,
                                         psFechaFacturacionInicial VARCHAR2,
                                         psFechaFacturacionFinal   VARCHAR2,
										 psCodigoMarcaLeaderList   VARCHAR2,
                     psNumUltimosDigitos VARCHAR2);

/**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar Venta
                        en el periodo, aplicando equivalencia
   Fecha Creacion     : 26/11/2010
   Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
PROCEDURE LLI_PR_EQUIV_VENTA_PERIO(PS_COD_PAIS IN VARCHAR2,
                                   PS_COD_PERI IN VARCHAR2) ;

/**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar Venta
                        Real Diaria-Acumulada, aplicando equivalencia
   Fecha Creacion     : 26/11/2010
   Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE INT_PR_EQUIV_VENTA_REAL_DIARIA(psCodigoPais              VARCHAR2,
                                           psCodigosistema           VARCHAR2,
                                           psCodigoInterfaz          VARCHAR2,
                                           psNombreArchivo           VARCHAR2,
                                           psCodigoMarca             VARCHAR2,
                                           psCodigoCanal             VARCHAR2,
                                           psCodigoPeriodo           VARCHAR2,
                                           psFechaFacturacionInicial VARCHAR2,
                                           psFechaFacturacionFinal   VARCHAR2,
                      										 psCodigoMarcaLeaderList   VARCHAR2,
                                           psNumUltimosDigitos       VARCHAR2);

END;
/

CREATE OR REPLACE PACKAGE BODY "LLI_PKG_LEADE_LIST" AS
  PROCEDURE LLI_PR_CARGA_VENTA_PERIO(PS_COD_PAIS IN VARCHAR2,
                                     PS_COD_PERI IN VARCHAR2) IS
  BEGIN
    -- Elimina todos las filas de la Tabla INT_LLI_VENTA_PERIO
    EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_LLI_VENTA_PERIO';
    /* Insertamos los valores calculandolos desde la tabla
    cargada por la interfaz BPS */
    INSERT INTO INT_LLI_VENTA_PERIO
      SELECT SEG_PAIS.COD_PAIS,
             SEG_PERIO_CORPO.COD_PERI,
             PRE_TIPO_OFERT.COD_TIPO_OFER,
             MAE_PRODU.COD_SAP,
             MAE_PRODU.CODI_ANTI,
             --PED_SOLIC_POSIC.Val_Codi_Vent,
             SUM(CASE
                   WHEN INT_PARAM_TIPO_SOLIC.NUM_UNID_VEND = 1 AND
                        PED_SOLIC_CABEC.IND_OC = 1 THEN
                    PED_SOLIC_POSIC.NUM_UNID_ATEN
                   ELSE
                    0
                 END) AS UNID_ATEN_VENDIDAS,
             SUM(CASE
                   WHEN INT_PARAM_TIPO_SOLIC.NUM_UNID_FALT = 1 AND
                        PED_SOLIC_CABEC.IND_OC = 1 THEN
                    (PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL -
                    PED_SOLIC_POSIC.NUM_UNID_COMPR)
                   ELSE
                    0
                 END) AS UNID_ATEN_FALTANTES,
             SUM(CASE
                   WHEN INT_PARAM_TIPO_SOLIC.NUM_UNID_VEND = 1 AND
                        PED_SOLIC_CABEC.IND_OC = 1 AND
                        PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA <> 0 THEN
                    PED_SOLIC_POSIC.VAL_PREC_NETO_TOTA_LOCA
                   ELSE
                    0
                 END) AS VENTA_NETA_VENDIDAS,
             SUM(CASE
                   WHEN INT_PARAM_TIPO_SOLIC.NUM_UNID_FALT = 1 AND
                        PED_SOLIC_CABEC.IND_OC = 1 AND
                        PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA <> 0 THEN
                    (PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL -
                    PED_SOLIC_POSIC.NUM_UNID_COMPR) *
                    PED_SOLIC_POSIC.VAL_PREC_NETO_UNIT_LOCA
                   ELSE
                    0
                 END) AS VENTA_NETA_FALTANTES
        FROM PED_SOLIC_CABEC,
             PED_SOLIC_POSIC,
             PED_TIPO_SOLIC_PAIS,
             INT_PARAM_TIPO_SOLIC,
             PRE_OFERT_DETAL,
             SEG_PAIS,
             SEG_SOCIE,
             BEL_ALMAC,
             INT_CODIG_MEDIO_SAPBP,
             INT_CANAL_BPS,
             CRA_PERIO,
             SEG_PERIO_CORPO,
             MAE_PRODU,
             PRE_CICLO_VIDA,
             PRE_TIPO_OFERT,
             PRE_CONDI_PROMO,
             SEG_MARCA,
             PED_TIPO_SOLIC
       WHERE PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE =
             PED_SOLIC_CABEC.OID_SOLI_CABE
         AND PED_SOLIC_POSIC.OFDE_OID_DETA_OFER =
             PRE_OFERT_DETAL.OID_DETA_OFER
         AND PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS =
             PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS
         AND INT_PARAM_TIPO_SOLIC.TSPA_OID_TIPO_SOLI_PAIS =
             PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS
         AND PED_SOLIC_CABEC.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS
         AND PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1
         AND PED_SOLIC_CABEC.IND_PEDI_PRUE = 0
         AND PED_SOLIC_CABEC.FEC_FACT IS NOT NULL
         AND PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI <> 2
         AND PED_SOLIC_POSIC.VAL_CODI_VENT = PRE_OFERT_DETAL.VAL_CODI_VENT
         AND PED_SOLIC_CABEC.SOCI_OID_SOCI = SEG_SOCIE.OID_SOCI
         AND PED_SOLIC_CABEC.ALMC_OID_ALMA = BEL_ALMAC.OID_ALMA
         AND PED_SOLIC_CABEC.PAIS_OID_PAIS =
             INT_CODIG_MEDIO_SAPBP.PAIS_OID_PAIS
         AND PED_SOLIC_CABEC.SBAC_OID_SBAC =
             INT_CODIG_MEDIO_SAPBP.SBAC_OID_SBAC
         AND PED_SOLIC_CABEC.TICL_OID_TIPO_CLIE =
             INT_CANAL_BPS.TICL_OID_TIPO_CLIE
         AND PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS =
             INT_CANAL_BPS.TSPA_OID_TIPO_SOLI_PAIS
         AND PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI
         AND CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
         AND PED_SOLIC_POSIC.PROD_OID_PROD = MAE_PRODU.OID_PROD
         AND PRE_OFERT_DETAL.CIVI_OID_CICLO_VIDA =
             PRE_CICLO_VIDA.OID_CICL_VIDA(+)
         AND PRE_OFERT_DETAL.TOFE_OID_TIPO_OFER =
             PRE_TIPO_OFERT.OID_TIPO_OFER(+)
         AND PRE_OFERT_DETAL.CNDP_OID_COND_PROM =
             PRE_CONDI_PROMO.OID_COND_PROM(+)
         AND CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC
         AND PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI =
             PED_TIPO_SOLIC.OID_TIPO_SOLI
         AND SEG_PAIS.COD_PAIS = PS_COD_PAIS
         AND SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI
         AND ((INT_PARAM_TIPO_SOLIC.NUM_UNID_VEND = 1 AND
             PED_TIPO_SOLIC.IND_ANUL = 0 AND PED_TIPO_SOLIC.IND_DEVO = 0 AND
             PED_SOLIC_CABEC.IND_OC = 1) OR
             (INT_PARAM_TIPO_SOLIC.NUM_UNID_FALT = 1 AND
             PED_TIPO_SOLIC.IND_ANUL = 0 AND PED_TIPO_SOLIC.IND_DEVO = 0 AND
             PED_SOLIC_CABEC.IND_OC = 1))
       GROUP BY SEG_PAIS.COD_PAIS,
                SEG_PERIO_CORPO.COD_PERI,
                PRE_TIPO_OFERT.COD_TIPO_OFER,
                MAE_PRODU.COD_SAP,
                MAE_PRODU.CODI_ANTI
                --PED_SOLIC_POSIC.Val_Codi_Vent
                ;

    /* Insertamos los productos / ofertas que no estaban en la tabla
    anterior con valores 0 para las unidades y montos */
    INSERT INTO INT_LLI_VENTA_PERIO
      SELECT DISTINCT SEG_PAIS.COD_PAIS,
                      SEG_PERIO_CORPO.COD_PERI,
                      PRE_TIPO_OFERT.COD_TIPO_OFER,
                      MAE_PRODU.COD_SAP,
                      MAE_PRODU.CODI_ANTI,
                      --PRE_OFERT_DETAL.Val_Codi_Vent,
                      0,
                      0,
                      0,
                      0
        FROM PRE_OFERT,
             PRE_OFERT_DETAL,
             PRE_MATRI_FACTU_CABEC,
             CRA_PERIO,
             MAE_PRODU,
             PRE_TIPO_OFERT,
             SEG_PERIO_CORPO,
             SEG_PAIS
       WHERE ((PRE_OFERT.OID_OFER = PRE_OFERT_DETAL.OFER_OID_OFER) AND
             (PRE_MATRI_FACTU_CABEC.OID_CABE = PRE_OFERT.MFCA_OID_CABE) AND
             (CRA_PERIO.OID_PERI = PRE_MATRI_FACTU_CABEC.PERD_OID_PERI) AND
             (MAE_PRODU.OID_PROD = PRE_OFERT_DETAL.PROD_OID_PROD) AND
             (PRE_TIPO_OFERT.OID_TIPO_OFER =
             PRE_OFERT_DETAL.TOFE_OID_TIPO_OFER) AND
             (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
             (SEG_PAIS.OID_PAIS = MAE_PRODU.PAIS_OID_PAIS) AND
             (SEG_PAIS.COD_PAIS = PS_COD_PAIS) AND
             (SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI) AND
             ((SEG_PAIS.COD_PAIS, SEG_PERIO_CORPO.COD_PERI,
              PRE_TIPO_OFERT.COD_TIPO_OFER, MAE_PRODU.COD_SAP) NOT IN
             (SELECT COD_PAIS, COD_PERI, COD_TIPO_OFER, COD_SAP
                  FROM INT_LLI_VENTA_PERIO)));

    /* Se comentaron los inserts por solicitud de cambio de
    Martiza Murguia (26/03/2007) */
    /*
      INSERT INTO INT_LLI_VENTA_PERIO
      SELECT SEG_PAIS.COD_PAIS,
             SEG_PERIO_CORPO.COD_PERI,
             PRE_TIPO_OFERT.COD_TIPO_OFER,
             MAE_PRODU.COD_SAP,
             MAE_PRODU.CODI_ANTI,
             0,
             0,
             0,
             0
        FROM PRE_ESTIM_VENTA,
             MAE_PRODU,
             CRA_PERIO,
             SEG_PAIS,
             SEG_PERIO_CORPO,
             PRE_TIPO_OFERT
       WHERE ((CRA_PERIO.OID_PERI = PRE_ESTIM_VENTA.PERD_OID_PERI) AND
             (SEG_PAIS.OID_PAIS = MAE_PRODU.PAIS_OID_PAIS) AND
             (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
             (PRE_TIPO_OFERT.OID_TIPO_OFER =
             PRE_ESTIM_VENTA.TOFE_OID_TIPO_OFER) AND
             (PRE_ESTIM_VENTA.PROD_OID_PROD = MAE_PRODU.OID_PROD) AND
             (SEG_PAIS.COD_PAIS = PS_COD_PAIS) AND
             (SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI) AND
             ((SEG_PAIS.COD_PAIS, SEG_PERIO_CORPO.COD_PERI,
              PRE_TIPO_OFERT.COD_TIPO_OFER, MAE_PRODU.COD_SAP) NOT IN
             (SELECT COD_PAIS,
                       COD_PERI,
                       COD_TIPO_OFER,
                       COD_SAP
                  FROM INT_LLI_VENTA_PERIO)));

    INSERT INTO INT_LLI_VENTA_PERIO
      SELECT SEG_PAIS.COD_PAIS,
             SEG_PERIO_CORPO.COD_PERI,
             PRE_TIPO_OFERT.COD_TIPO_OFER,
             MAE_PRODU.COD_SAP,
             MAE_PRODU.CODI_ANTI,
             0,
             0,
             0,
             0
        FROM SEG_PERIO_CORPO,
             SEG_PAIS,
             PRE_TIPO_OFERT,
             MAV_BPS_INTER,
             MAE_PRODU,
             CRA_PERIO
       WHERE ((PRE_TIPO_OFERT.OID_TIPO_OFER =
             MAV_BPS_INTER.TOFE_OID_TIPO_OFER) AND
             (SEG_PAIS.OID_PAIS = MAE_PRODU.PAIS_OID_PAIS) AND
             (MAE_PRODU.OID_PROD = MAV_BPS_INTER.PROD_OID_PROD) AND
             (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
             (CRA_PERIO.OID_PERI = MAV_BPS_INTER.PERD_OID_PERI) AND
             (SEG_PAIS.COD_PAIS = PS_COD_PAIS) AND
             (SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI) AND
             ((SEG_PAIS.COD_PAIS, SEG_PERIO_CORPO.COD_PERI,
              PRE_TIPO_OFERT.COD_TIPO_OFER, MAE_PRODU.COD_SAP) NOT IN
             (SELECT COD_PAIS,
                       COD_PERI,
                       COD_TIPO_OFER,
                       COD_SAP
                  FROM INT_LLI_VENTA_PERIO)));
    */

  END LLI_PR_CARGA_VENTA_PERIO;

  /**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar Venta Real Diaria-Acumulada
   Fecha Creacion     : 28/11/2006
   Autor              : Luis Mendoza
  ***************************************************************************/
  PROCEDURE INT_PR_LLI_VENTA_REAL_DIARIA(psCodigoPais              VARCHAR2,
                                         psCodigosistema           VARCHAR2,
                                         psCodigoInterfaz          VARCHAR2,
                                         psNombreArchivo           VARCHAR2,
                                         psCodigoMarca             VARCHAR2,
                                         psCodigoCanal             VARCHAR2,
                                         psCodigoPeriodo           VARCHAR2,
                                         psFechaFacturacionInicial VARCHAR2,
                                         psFechaFacturacionFinal   VARCHAR2,
										 psCodigoMarcaLeaderList   VARCHAR2,
                     psNumUltimosDigitos VARCHAR2)

   IS
    CURSOR c_interfaz(vnIdPeriodo number) IS
      SELECT pscodigomarcaleaderlist AS marca,
             spc.cod_peri AS campaña,
             CASE
               WHEN length(mp.codi_anti) > psNumUltimosDigitos THEN
                substr(mp.codi_anti, length(mp.codi_anti) - psNumUltimosDigitos + 1,
                       psNumUltimosDigitos)
               ELSE
                mp.codi_anti
             END codprd,
             substr(pto.cod_tipo_ofer, 1, 2) tipoferta,
             TRIM(to_char(SUM(psp.num_unid_aten), '00000000')) undaten,
             TRIM(to_char(SUM(psp.val_prec_neto_tota_loca),
                          '0000000009.99')) totalneto
        FROM ped_solic_cabec psc,
             ped_solic_posic psp,
             mae_produ       mp,
             cra_perio       cp,
             seg_perio_corpo spc,
             pre_ofert_detal pod,
             pre_tipo_ofert  pto,
             seg_pais        pais
       WHERE psc.fec_fact >=
             to_date(psfechafacturacioninicial, 'dd/mm/yyyy')
         AND psc.fec_fact <=
             to_date(psfechafacturacionfinal, 'dd/mm/yyyy')
         AND psc.perd_oid_peri = vnidperiodo
         AND pais.cod_pais = pscodigopais
         AND psc.pais_oid_pais = pais.oid_pais
         AND psc.ind_ts_no_conso = 1
         AND psc.ind_pedi_prue = 0
         AND ((psc.ind_oc = 1 AND
             psc.modu_oid_modu NOT IN (13, 15)) OR
             psc.modu_oid_modu = 9)
         AND psc.oid_soli_cabe = psp.soca_oid_soli_cabe
         AND psp.num_unid_aten > 0
         AND psc.esso_oid_esta_soli <> 4 --ANULADO
         AND psp.espo_oid_esta_posi <> 2 --ANULADO
         AND pod.oid_deta_ofer = psp.ofde_oid_deta_ofer
         AND pto.oid_tipo_ofer = pod.tofe_oid_tipo_ofer
         AND psp.prod_oid_prod = mp.oid_prod
         AND psc.perd_oid_peri = cp.oid_peri
         AND spc.oid_peri = cp.peri_oid_peri
       GROUP BY '0',
                spc.cod_peri,
                CASE
               WHEN length(mp.codi_anti) > psNumUltimosDigitos THEN
                substr(mp.codi_anti, length(mp.codi_anti) - psNumUltimosDigitos + 1,
                       psNumUltimosDigitos)
               ELSE
                mp.codi_anti
             END ,
                substr(pto.cod_tipo_ofer, 1, 2);

    TYPE interfazRec IS RECORD(
      codigoMarca       varchar2(1),
      codigoPeriodo     varchar2(6),
      codigoProd        varchar2(9),
      tipoOferta        varchar2(2),
      unidadesAtendidas varchar2(8),
      importeNeto       varchar(13));

    TYPE interfazRecTab IS TABLE OF interfazRec;
    interfazRecord interfazRecTab;

    /* Variables usadas para la generacion del archivo de texto */
    lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS         NUMBER := 1000;
    v_handle        UTL_FILE.FILE_TYPE;
    W_DESC          VARCHAR2(200);
    lsLinea         VARCHAR2(1000);
    lsLineaCabecera VARCHAR2(1000);
    lsNombreArchivo VARCHAR2(50);
    lbAbrirUtlFile  BOOLEAN;
    lnIdPeriodo     number;
  BEGIN
    lbAbrirUtlFile := TRUE;
    lnIdPeriodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                        gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca),
                                                        gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal));

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnIdPeriodo);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazRecord LIMIT W_FILAS;
      /* Procedimiento inicial para generar interfaz */
       IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
       END IF;

      IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).codigoMarca 			 ||';'||
		  		  	 interfazRecord(x).codigoPeriodo 		 ||';'||
					 interfazRecord(x).codigoProd 			 ||';'||
					 interfazRecord(x).tipoOferta 			 ||';'||
					 interfazRecord(x).unidadesAtendidas 	 ||';'||
					 interfazRecord(x).importeNeto;
          UTL_FILE.PUT_LINE(V_HANDLE, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LLI_VENTA_REAL_DIARIA: '||ls_sqlerrm);
  END INT_PR_LLI_VENTA_REAL_DIARIA;

/**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar Venta
                        en el periodo, aplicando equivalencia
   Fecha Creacion     : 26/11/2010
   Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
PROCEDURE LLI_PR_EQUIV_VENTA_PERIO(PS_COD_PAIS IN VARCHAR2,
                                   PS_COD_PERI IN VARCHAR2) IS

cursor cur_equi is
		select distinct
		       f.COD_PERI,
		       --
		       g.VAL_CODI_VENT,
           j.COD_SAP ,
			     k.COD_SAP ,
			     t.cod_tipo_ofer,
           c.VAL_CODI_VENT cuv_ori,
           u.cod_tipo_ofer tipo_ofer_ori
           /*tmp.COD_TIPO_OFER tipo_ofer_ori*/
		  from pre_matri_factu_cabec a,
  			   pre_ofert             b,
  			   pre_ofert_detal       c,
  			   pre_matri_factu       d,
  			   cra_perio             e,
  			   seg_perio_corpo       f,
  			   pre_ofert_detal       g,
  			   pre_matri_factu       h,
  			   pre_matri_codig_alter i,
  			   mae_produ             j,
  			   mae_produ             k,
  			   gen_i18n_sicc_pais    l,
  			   gen_i18n_sicc_pais    m,
  			   pre_tipo_ofert        t,
  			   pre_tipo_ofert        u,
  			   pre_prod_alter_ice    equi/*,
  			   ---------------------------
  			   TMP_LEALI_VENTA_PERIO tmp*/
		 where a.OID_CABE = b.MFCA_OID_CABE
		   and b.OID_OFER = c.OFER_OID_OFER
		   and c.OID_DETA_OFER = d.OFDE_OID_DETA_OFER
		   and a.PERD_OID_PERI = e.OID_PERI
		   and i.MAFA_OID_COD_PPAL = d.OID_MATR_FACT
		   and i.MAFA_OID_COD_ALTE = h.OID_MATR_FACT
		   and h.OFDE_OID_DETA_OFER = g.OID_DETA_OFER
		   and f.OID_PERI = e.PERI_OID_PERI
		   and c.PROD_OID_PROD = j.OID_PROD
		   and j.OID_PROD = l.VAL_OID
		   and l.ATTR_ENTI = 'MAE_PRODU'
		   and g.PROD_OID_PROD = k.OID_PROD
		   and k.OID_PROD = m.VAL_OID
		   and m.ATTR_ENTI = 'MAE_PRODU'
		   and g.tofe_oid_tipo_ofer = t.oid_tipo_ofer
		   and c.tofe_oid_tipo_ofer = u.oid_tipo_ofer
		   and j.COD_SAP = equi.COD_SAP_PPAL/*
		   -----------------------------
		   and f.COD_PERI = tmp.COD_PERI
		   and c.VAL_CODI_VENT = tmp.VAL_CODI_VENT*/;

	TYPE t_cod_peri          is table of seg_perio_corpo.cod_peri%type;
	TYPE t_val_codi_vent     is table of pre_ofert_detal.val_codi_vent%type;
	TYPE t_cod_sap_ori  	       is table of mae_produ.cod_sap%type;
	TYPE t_cod_sap  	       is table of mae_produ.cod_sap%type;
	TYPE t_cod_tipo_ofer     is table of pre_tipo_ofert.cod_tipo_ofer%type;
  TYPE t_val_codi_vent_ori is table of pre_ofert_detal.val_codi_vent%type;
  TYPE t_cod_tipo_ofer_ori is table of pre_tipo_ofert.cod_tipo_ofer%type;

	v_cod_peri     	    t_cod_peri;
	v_val_codi_vent	    t_val_codi_vent;
  	v_cod_sap_ori  		      t_cod_sap_ori;
	v_cod_sap  		      t_cod_sap;
	v_cod_tipo_ofer	    t_cod_tipo_ofer;
  v_val_codi_vent_ori t_val_codi_vent_ori;
  v_cod_tipo_ofer_ori t_cod_tipo_ofer_ori;

  v_cod_anti mae_produ.codi_anti%type;

	rows             NATURAL        := 1000;   -- Number of rows to process at a time
  j                BINARY_INTEGER := 0;
  v_row_count      NUMBER         := 0;
--borrar number;
  BEGIN
    -- Elimina todos las filas de la Tabla TMP_LEALI_VENTA_PERIO
    EXECUTE IMMEDIATE 'TRUNCATE TABLE TMP_LEALI_VENTA_PERIO';
    /* Insertamos los valores calculandolos desde la tabla
    cargada por la interfaz BPS */
    INSERT INTO TMP_LEALI_VENTA_PERIO(COD_PAIS,
                                      COD_PERI,
                                      COD_TIPO_OFER,
                                      COD_SAP,
                                      COD_ANTI,
                                      NUM_UNID_VEND,
                                      NUM_UNID_FALT,
                                      IMP_VENT_NETA_ATEN,
                                      IMP_VENT_NETA_FALT,
                                      VAL_CODI_VENT)
      SELECT SEG_PAIS.COD_PAIS,
             SEG_PERIO_CORPO.COD_PERI,
             PRE_TIPO_OFERT.COD_TIPO_OFER,
             MAE_PRODU.COD_SAP,
             MAE_PRODU.CODI_ANTI,
             --SUM(
                 CASE
                   WHEN INT_PARAM_TIPO_SOLIC.NUM_UNID_VEND = 1 AND
                        PED_SOLIC_CABEC.IND_OC = 1 THEN
                    PED_SOLIC_POSIC.NUM_UNID_ATEN
                   ELSE
                    0
                 END
               --  )
                 AS UNID_ATEN_VENDIDAS,
             --SUM(
                 CASE
                   WHEN INT_PARAM_TIPO_SOLIC.NUM_UNID_FALT = 1 AND
                        PED_SOLIC_CABEC.IND_OC = 1 THEN
                    (PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL -
                    PED_SOLIC_POSIC.NUM_UNID_COMPR)
                   ELSE
                    0
                 END
               --  )
                 AS UNID_ATEN_FALTANTES,
             --SUM(
                 CASE
                   WHEN INT_PARAM_TIPO_SOLIC.NUM_UNID_VEND = 1 AND
                        PED_SOLIC_CABEC.IND_OC = 1 AND
                        PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA <> 0 THEN
                    PED_SOLIC_POSIC.VAL_PREC_NETO_TOTA_LOCA
                   ELSE
                    0
                 END
               --  )
                 AS VENTA_NETA_VENDIDAS,
             --SUM(
                 CASE
                   WHEN INT_PARAM_TIPO_SOLIC.NUM_UNID_FALT = 1 AND
                        PED_SOLIC_CABEC.IND_OC = 1 AND
                        PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA <> 0 THEN
                    (PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL -
                    PED_SOLIC_POSIC.NUM_UNID_COMPR) *
                    PED_SOLIC_POSIC.VAL_PREC_NETO_UNIT_LOCA
                   ELSE
                    0
                 END
               --  )
                  AS VENTA_NETA_FALTANTES,
             PED_SOLIC_POSIC.Val_Codi_Vent
        FROM PED_SOLIC_CABEC,
             PED_SOLIC_POSIC,
             PED_TIPO_SOLIC_PAIS,
             INT_PARAM_TIPO_SOLIC,
             PRE_OFERT_DETAL,
             SEG_PAIS,
             SEG_SOCIE,
             BEL_ALMAC,
             INT_CODIG_MEDIO_SAPBP,
             INT_CANAL_BPS,
             CRA_PERIO,
             SEG_PERIO_CORPO,
             MAE_PRODU,
             PRE_CICLO_VIDA,
             PRE_TIPO_OFERT,
             PRE_CONDI_PROMO,
             SEG_MARCA,
             PED_TIPO_SOLIC
       WHERE PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE =
             PED_SOLIC_CABEC.OID_SOLI_CABE
         AND PED_SOLIC_POSIC.OFDE_OID_DETA_OFER =
             PRE_OFERT_DETAL.OID_DETA_OFER
         AND PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS =
             PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS
         AND INT_PARAM_TIPO_SOLIC.TSPA_OID_TIPO_SOLI_PAIS =
             PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS
         AND PED_SOLIC_CABEC.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS
         AND PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1
         AND PED_SOLIC_CABEC.IND_PEDI_PRUE = 0
         AND PED_SOLIC_CABEC.FEC_FACT IS NOT NULL
         AND PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI <> 2
         AND PED_SOLIC_POSIC.VAL_CODI_VENT = PRE_OFERT_DETAL.VAL_CODI_VENT
         AND PED_SOLIC_CABEC.SOCI_OID_SOCI = SEG_SOCIE.OID_SOCI
         AND PED_SOLIC_CABEC.ALMC_OID_ALMA = BEL_ALMAC.OID_ALMA
         AND PED_SOLIC_CABEC.PAIS_OID_PAIS =
             INT_CODIG_MEDIO_SAPBP.PAIS_OID_PAIS
         AND PED_SOLIC_CABEC.SBAC_OID_SBAC =
             INT_CODIG_MEDIO_SAPBP.SBAC_OID_SBAC
         AND PED_SOLIC_CABEC.TICL_OID_TIPO_CLIE =
             INT_CANAL_BPS.TICL_OID_TIPO_CLIE
         AND PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS =
             INT_CANAL_BPS.TSPA_OID_TIPO_SOLI_PAIS
         AND PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI
         AND CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
         AND PED_SOLIC_POSIC.PROD_OID_PROD = MAE_PRODU.OID_PROD
         AND PRE_OFERT_DETAL.CIVI_OID_CICLO_VIDA =
             PRE_CICLO_VIDA.OID_CICL_VIDA(+)
         AND PRE_OFERT_DETAL.TOFE_OID_TIPO_OFER =
             PRE_TIPO_OFERT.OID_TIPO_OFER(+)
         AND PRE_OFERT_DETAL.CNDP_OID_COND_PROM =
             PRE_CONDI_PROMO.OID_COND_PROM(+)
         AND CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC
         AND PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI =
             PED_TIPO_SOLIC.OID_TIPO_SOLI
         AND SEG_PAIS.COD_PAIS = PS_COD_PAIS
         AND SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI
         AND ((INT_PARAM_TIPO_SOLIC.NUM_UNID_VEND = 1 AND
             PED_TIPO_SOLIC.IND_ANUL = 0 AND PED_TIPO_SOLIC.IND_DEVO = 0 AND
             PED_SOLIC_CABEC.IND_OC = 1) OR
             (INT_PARAM_TIPO_SOLIC.NUM_UNID_FALT = 1 AND
             PED_TIPO_SOLIC.IND_ANUL = 0 AND PED_TIPO_SOLIC.IND_DEVO = 0 AND
             PED_SOLIC_CABEC.IND_OC = 1))
/*       GROUP BY SEG_PAIS.COD_PAIS,
                SEG_PERIO_CORPO.COD_PERI,
                PRE_TIPO_OFERT.COD_TIPO_OFER,
                MAE_PRODU.COD_SAP,
                MAE_PRODU.CODI_ANTI,
                PED_SOLIC_POSIC.Val_Codi_Vent*/
                ;

           --     select count(1) into borrar from TMP_LEALI_VENTA_PERIO;
       --APLICO EQUIVALENCIA
       /*OPEN cur_equi;

        LOOP
    		-- Bulk collect data into memory table - X rows at a time
    		FETCH cur_equi BULK COLLECT INTO v_cod_peri     ,
    		                                 v_val_codi_vent,
    		                                 v_cod_sap  	,
    		                                 v_cod_tipo_ofer,
                                         v_val_codi_vent_ori,
                                         v_cod_tipo_ofer_ori
    									LIMIT rows;

    		EXIT WHEN v_row_count = cur_equi%ROWCOUNT;
    		v_row_count := cur_equi%ROWCOUNT;

    		-- Bulk bind of data in memory table...
    		--FORALL j IN 1..v_cod_peri.count
        FOR j in 1..v_cod_peri.count loop
          UPDATE TMP_LEALI_VENTA_PERIO
    			   SET VAL_CODI_VENT = v_val_codi_vent(j),
    				     COD_SAP = v_cod_sap(j),
    				     COD_TIPO_OFER = v_cod_tipo_ofer(j)
    			 WHERE COD_PERI = v_cod_peri(j)
    		     AND VAL_CODI_VENT = v_val_codi_vent_ori(j)
             AND COD_TIPO_OFER = v_cod_tipo_ofer_ori(j);

            borrar := SQL%ROWCOUNT;

         end loop;
    	  END LOOP;

    	CLOSE cur_equi;

      -- Elimina todos las filas de la Tabla INT_LLI_VENTA_PERIO
      EXECUTE IMMEDIATE 'delete from INT_LLI_VENTA_PERIO';

      INSERT INTO INT_LLI_VENTA_PERIO (select cod_pais,
                                              cod_peri,
                                              cod_tipo_ofer,
                                              cod_sap,
                                              cod_anti,
                                              sum(num_unid_vend),
                                              sum(num_unid_falt),
                                              sum(imp_vent_neta_aten),
                                              sum(imp_vent_neta_falt)
                                         from tmp_leali_venta_perio
                                     group by cod_pais,
                                              cod_peri,
                                              cod_tipo_ofer,
                                              cod_sap,
                                              cod_anti,
                                              num_unid_vend,
                                              num_unid_falt,
                                              imp_vent_neta_aten,
                                              imp_vent_neta_falt);

    select count(1) into borrar from INT_LLI_VENTA_PERIO;

    EXECUTE IMMEDIATE 'delete from tmp_leali_venta_perio';   */

    /* Insertamos los productos / ofertas que no estaban en la tabla
    anterior con valores 0 para las unidades y montos */
    INSERT INTO TMP_LEALI_VENTA_PERIO(COD_PAIS,
                                      COD_PERI,
                                      COD_TIPO_OFER,
                                      COD_SAP,
                                      COD_ANTI,
                                      VAL_CODI_VENT,
                                      NUM_UNID_VEND,
                                      NUM_UNID_FALT,
                                      IMP_VENT_NETA_ATEN,
                                      IMP_VENT_NETA_FALT)
      SELECT DISTINCT SEG_PAIS.COD_PAIS,
                      SEG_PERIO_CORPO.COD_PERI,
                      PRE_TIPO_OFERT.COD_TIPO_OFER,
                      MAE_PRODU.COD_SAP,
                      MAE_PRODU.CODI_ANTI,
                      PRE_OFERT_DETAL.Val_Codi_Vent,
                      0,
                      0,
                      0,
                      0
        FROM PRE_OFERT,
             PRE_OFERT_DETAL,
             PRE_MATRI_FACTU_CABEC,
             CRA_PERIO,
             MAE_PRODU,
             PRE_TIPO_OFERT,
             SEG_PERIO_CORPO,
             SEG_PAIS
       WHERE ((PRE_OFERT.OID_OFER = PRE_OFERT_DETAL.OFER_OID_OFER) AND
             (PRE_MATRI_FACTU_CABEC.OID_CABE = PRE_OFERT.MFCA_OID_CABE) AND
             (CRA_PERIO.OID_PERI = PRE_MATRI_FACTU_CABEC.PERD_OID_PERI) AND
             (MAE_PRODU.OID_PROD = PRE_OFERT_DETAL.PROD_OID_PROD) AND
             (PRE_TIPO_OFERT.OID_TIPO_OFER =
             PRE_OFERT_DETAL.TOFE_OID_TIPO_OFER) AND
             (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
             (SEG_PAIS.OID_PAIS = MAE_PRODU.PAIS_OID_PAIS) AND
             (SEG_PAIS.COD_PAIS = PS_COD_PAIS) AND
             (SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI) /*AND
             ((SEG_PAIS.COD_PAIS, SEG_PERIO_CORPO.COD_PERI,
              PRE_TIPO_OFERT.COD_TIPO_OFER, MAE_PRODU.COD_SAP) NOT IN
             (SELECT COD_PAIS, COD_PERI, COD_TIPO_OFER, COD_SAP
                  FROM INT_LLI_VENTA_PERIO))*/
              AND
             ((SEG_PAIS.COD_PAIS, SEG_PERIO_CORPO.COD_PERI,
              PRE_TIPO_OFERT.COD_TIPO_OFER, MAE_PRODU.COD_SAP) NOT IN
             (SELECT COD_PAIS, COD_PERI, COD_TIPO_OFER, COD_SAP
                  FROM TMP_LEALI_VENTA_PERIO))

                  );

      --select count(1) into borrar from TMP_LEALI_VENTA_PERIO;

     OPEN cur_equi;

        LOOP
    		-- Bulk collect data into memory table - X rows at a time
    		FETCH cur_equi BULK COLLECT INTO v_cod_peri     ,
    		                                 v_val_codi_vent,
                                         v_cod_sap_ori,
    		                                 v_cod_sap  	,
    		                                 v_cod_tipo_ofer,
                                         v_val_codi_vent_ori,
                                         v_cod_tipo_ofer_ori
    									LIMIT rows;

    		EXIT WHEN v_row_count = cur_equi%ROWCOUNT;
    		v_row_count := cur_equi%ROWCOUNT;

    		-- Bulk bind of data in memory table...
    		--FORALL j IN 1..v_cod_peri.count
        FOR j IN 1..v_cod_peri.count	LOOP

          --select CODI_ANTI into v_cod_anti from mae_produ where COD_SAP = v_cod_sap(j);
          select CODI_ANTI into v_cod_anti from mae_produ where COD_SAP = v_cod_sap_ori(j);

          UPDATE TMP_LEALI_VENTA_PERIO
    			   SET VAL_CODI_VENT = v_val_codi_vent_ori(j),--v_val_codi_vent(j),
    				     COD_SAP = v_cod_sap_ori(j),--v_cod_sap(j),
    				     COD_TIPO_OFER = v_cod_tipo_ofer_ori(j),--v_cod_tipo_ofer(j),
                 COD_ANTI = v_cod_anti
    			 WHERE COD_PERI = v_cod_peri(j)
    		     AND VAL_CODI_VENT = v_val_codi_vent(j)--v_val_codi_vent_ori(j)
             AND COD_TIPO_OFER = v_cod_tipo_ofer(j);--v_cod_tipo_ofer_ori(j);

        END LOOP;

    	  END LOOP;

    	CLOSE cur_equi;

      -- Elimina todos las filas de la Tabla INT_LLI_VENTA_PERIO
      EXECUTE IMMEDIATE 'delete from INT_LLI_VENTA_PERIO';

      INSERT INTO INT_LLI_VENTA_PERIO (select cod_pais,
                                              cod_peri,
                                              cod_tipo_ofer,
                                              cod_sap,
                                              cod_anti,
                                              sum(num_unid_vend),
                                              sum(num_unid_falt),
                                              sum(imp_vent_neta_aten),
                                              sum(imp_vent_neta_falt)
                                         from tmp_leali_venta_perio
                                        /*where ((COD_PAIS, COD_PERI, COD_TIPO_OFER, COD_SAP) NOT IN
                                                 (SELECT COD_PAIS, COD_PERI, COD_TIPO_OFER, COD_SAP
                                                      FROM INT_LLI_VENTA_PERIO))                                         */
                                     group by cod_pais,
                                              cod_peri,
                                              cod_tipo_ofer,
                                              cod_sap,
                                              cod_anti/*,
                                              num_unid_vend,
                                              num_unid_falt,
                                              imp_vent_neta_aten,
                                              imp_vent_neta_falt*/);
     --select count(1) into borrar from INT_LLI_VENTA_PERIO;
  END LLI_PR_EQUIV_VENTA_PERIO;

/**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar Venta
                        Real Diaria-Acumulada, aplicando equivalencia
   Fecha Creacion     : 26/11/2010
   Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE INT_PR_EQUIV_VENTA_REAL_DIARIA(psCodigoPais              VARCHAR2,
                                           psCodigosistema           VARCHAR2,
                                           psCodigoInterfaz          VARCHAR2,
                                           psNombreArchivo           VARCHAR2,
                                           psCodigoMarca             VARCHAR2,
                                           psCodigoCanal             VARCHAR2,
                                           psCodigoPeriodo           VARCHAR2,
                                           psFechaFacturacionInicial VARCHAR2,
                                           psFechaFacturacionFinal   VARCHAR2,
                      										 psCodigoMarcaLeaderList   VARCHAR2,
                                           psNumUltimosDigitos       VARCHAR2)
   IS
    cursor cur_equi is
  		select distinct
  		       f.COD_PERI,
  		       --
  		       g.VAL_CODI_VENT,
  			     j.COD_SAP ,
  			     k.COD_SAP ,
  			     t.cod_tipo_ofer,
             c.VAL_CODI_VENT cuv_ori,
             u.cod_tipo_ofer tipo_ofer_ori
            /* tmp.COD_TIPO_OFER tipo_ofer_ori*/
  		  from pre_matri_factu_cabec a,
    			   pre_ofert             b,
    			   pre_ofert_detal       c,
    			   pre_matri_factu       d,
    			   cra_perio             e,
    			   seg_perio_corpo       f,
    			   pre_ofert_detal       g,
    			   pre_matri_factu       h,
    			   pre_matri_codig_alter i,
    			   mae_produ             j,
    			   mae_produ             k,
    			   gen_i18n_sicc_pais    l,
    			   gen_i18n_sicc_pais    m,
    			   pre_tipo_ofert        t,
    			   pre_tipo_ofert        u,
    			   pre_prod_alter_ice    equi/*,
    			   ---------------------------
    			   TMP_VENTA_REAL_DIARIA tmp*/
  		 where a.OID_CABE = b.MFCA_OID_CABE
  		   and b.OID_OFER = c.OFER_OID_OFER
  		   and c.OID_DETA_OFER = d.OFDE_OID_DETA_OFER
  		   and a.PERD_OID_PERI = e.OID_PERI
  		   and i.MAFA_OID_COD_PPAL = d.OID_MATR_FACT
  		   and i.MAFA_OID_COD_ALTE = h.OID_MATR_FACT
  		   and h.OFDE_OID_DETA_OFER = g.OID_DETA_OFER
  		   and f.OID_PERI = e.PERI_OID_PERI
  		   and c.PROD_OID_PROD = j.OID_PROD
  		   and j.OID_PROD = l.VAL_OID
  		   and l.ATTR_ENTI = 'MAE_PRODU'
  		   and g.PROD_OID_PROD = k.OID_PROD
  		   and k.OID_PROD = m.VAL_OID
  		   and m.ATTR_ENTI = 'MAE_PRODU'
  		   and g.tofe_oid_tipo_ofer = t.oid_tipo_ofer
  		   and c.tofe_oid_tipo_ofer = u.oid_tipo_ofer
  		   and j.COD_SAP = equi.COD_SAP_PPAL/*
  		   -----------------------------
  		   and f.COD_PERI = tmp.COD_PERI
  		   and c.VAL_CODI_VENT = tmp.VAL_CODI_VENT*/;

  	TYPE t_cod_peri      is table of seg_perio_corpo.cod_peri%type;
  	TYPE t_val_codi_vent is table of pre_ofert_detal.val_codi_vent%type;
  	TYPE t_cod_sap_ori  	 is table of mae_produ.cod_sap%type;
  	TYPE t_cod_sap  	 is table of mae_produ.cod_sap%type;
  	TYPE t_cod_tipo_ofer is table of pre_tipo_ofert.cod_tipo_ofer%type;
    TYPE t_val_codi_vent_ori is table of pre_ofert_detal.val_codi_vent%type;
    TYPE t_cod_tipo_ofer_ori is table of pre_tipo_ofert.cod_tipo_ofer%type;

  	v_cod_peri     	    t_cod_peri     ;
  	v_val_codi_vent	    t_val_codi_vent;
  	v_cod_sap_ori  	  	    t_cod_sap_ori	   ;
  	v_cod_sap  	  	    t_cod_sap  	   ;
  	v_cod_tipo_ofer	    t_cod_tipo_ofer;
    v_val_codi_vent_ori t_val_codi_vent_ori;
    v_cod_tipo_ofer_ori t_cod_tipo_ofer_ori;

  	rows             NATURAL        := 1000;   -- Number of rows to process at a time
    j                BINARY_INTEGER := 0;
    v_row_count      NUMBER         := 0;

   ----------------------

    CURSOR c_interfaz(vnIdPeriodo number) IS
       select cod_marca,
              cod_peri,
              cod_prod,
              cod_tipo_ofer,
              TRIM(to_char(SUM(num_unid_aten), '00000000')) undaten,
              TRIM(to_char(SUM(val_prec_neto_tota_loca), '0000000009.99')) totalneto
         from TMP_VENTA_REAL_DIARIA
     group by cod_marca,
              cod_peri,
              cod_prod,
              cod_tipo_ofer;

    TYPE interfazRec IS RECORD(
      codigoMarca       TMP_VENTA_REAL_DIARIA.COD_MARCA%TYPE,--varchar2(1),
      codigoPeriodo     TMP_VENTA_REAL_DIARIA.COD_PERI%TYPE,--varchar2(6),
      codigoProd        TMP_VENTA_REAL_DIARIA.COD_PROD%TYPE,--varchar2(9),
      tipoOferta        TMP_VENTA_REAL_DIARIA.COD_TIPO_OFER%TYPE,--varchar2(2),
      unidadesAtendidas varchar2(8),
      importeNeto       varchar(13));

    TYPE interfazRecTab IS TABLE OF interfazRec;
    interfazRecord interfazRecTab;

    /* Variables usadas para la generacion del archivo de texto */
    lsDirTempo      BAS_INTER.DIR_TEMP%TYPE;
    W_FILAS         NUMBER := 1000;
    v_handle        UTL_FILE.FILE_TYPE;
    W_DESC          VARCHAR2(200);
    lsLinea         VARCHAR2(1000);
    lsNombreArchivo VARCHAR2(50);
    lbAbrirUtlFile  BOOLEAN;
    lnIdPeriodo     number;
    v_cod_anti mae_produ.codi_anti%type;

  BEGIN
    lbAbrirUtlFile := TRUE;
    lnIdPeriodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                        gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca),
                                                        gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal));

    -- Carga la tabla temporal con los datos sin agrupar
    insert into TMP_VENTA_REAL_DIARIA(cod_marca,
                                      cod_peri,
                                      cod_prod,
                                      cod_tipo_ofer,
                                      cod_sap,
                                      val_codi_vent,
                                      num_unid_aten,
                                      val_prec_neto_tota_loca)
    (SELECT pscodigomarcaleaderlist AS marca,
             spc.cod_peri AS campaña,
             CASE
               WHEN length(mp.codi_anti) > psNumUltimosDigitos THEN
                substr(mp.codi_anti, length(mp.codi_anti) - psNumUltimosDigitos + 1,
                       psNumUltimosDigitos)
               ELSE
                mp.codi_anti
             END codprd,
             substr(pto.cod_tipo_ofer, 1, 2) tipoferta,
             mp.cod_sap,
             pod.val_codi_vent,
             psp.num_unid_aten,
             psp.val_prec_neto_tota_loca
        FROM ped_solic_cabec psc,
             ped_solic_posic psp,
             mae_produ       mp,
             cra_perio       cp,
             seg_perio_corpo spc,
             pre_ofert_detal pod,
             pre_tipo_ofert  pto,
             seg_pais        pais
       WHERE psc.fec_fact >=
             to_date(psfechafacturacioninicial, 'dd/mm/yyyy')
         AND psc.fec_fact <=
             to_date(psfechafacturacionfinal, 'dd/mm/yyyy')
         AND psc.perd_oid_peri = lnIdPeriodo
         AND pais.cod_pais = pscodigopais
         AND psc.pais_oid_pais = pais.oid_pais
         AND psc.ind_ts_no_conso = 1
         AND psc.ind_pedi_prue = 0
         AND ((psc.ind_oc = 1 AND
             psc.modu_oid_modu NOT IN (13, 15)) OR
             psc.modu_oid_modu = 9)
         AND psc.oid_soli_cabe = psp.soca_oid_soli_cabe
         AND psp.num_unid_aten > 0
         AND psc.esso_oid_esta_soli <> 4 --ANULADO
         AND psp.espo_oid_esta_posi <> 2 --ANULADO
         AND pod.oid_deta_ofer = psp.ofde_oid_deta_ofer
         AND pto.oid_tipo_ofer = pod.tofe_oid_tipo_ofer
         AND psp.prod_oid_prod = mp.oid_prod
         AND psc.perd_oid_peri = cp.oid_peri
         AND spc.oid_peri = cp.peri_oid_peri);
     -- Realiza la equivalencia
      OPEN cur_equi;

        LOOP
    		-- Bulk collect data into memory table - X rows at a time
    		FETCH cur_equi BULK COLLECT INTO v_cod_peri     ,
    		                                 v_val_codi_vent,
                                         v_cod_sap_ori,
    		                                 v_cod_sap  	,
    		                                 v_cod_tipo_ofer,
                                         v_val_codi_vent_ori,
                                         v_cod_tipo_ofer_ori
    									LIMIT rows;

    		EXIT WHEN v_row_count = cur_equi%ROWCOUNT;
    		v_row_count := cur_equi%ROWCOUNT;

    		-- Bulk bind of data in memory table...
    		--FORALL j IN 1..v_cod_peri.count
        FOR j IN 1..v_cod_peri.count		loop

          --select CODI_ANTI into v_cod_anti from mae_produ where COD_SAP = v_cod_sap(j);
          select CASE
                   WHEN length(codi_anti) > psNumUltimosDigitos THEN
                    substr(codi_anti,
                           length(codi_anti) - psNumUltimosDigitos + 1,
                           psNumUltimosDigitos)
                   ELSE
                    codi_anti
                 END
            into v_cod_anti
            from mae_produ
           where COD_SAP = v_cod_sap_ori(j)/*v_cod_sap(j)*/;

          UPDATE TMP_VENTA_REAL_DIARIA
    			   SET VAL_CODI_VENT = v_val_codi_vent_ori(j),--v_val_codi_vent(j),
    				     COD_SAP = v_cod_sap_ori(j),--v_cod_sap(j),
                 COD_PROD = v_cod_anti,
    				     COD_TIPO_OFER = v_cod_tipo_ofer_ori(j)--v_cod_tipo_ofer(j)
    			 WHERE COD_PERI = v_cod_peri(j)
    		     AND VAL_CODI_VENT = v_val_codi_vent(j)--v_val_codi_vent_ori(j)
             AND COD_TIPO_OFER = v_cod_tipo_ofer(j);--v_cod_tipo_ofer_ori(j);

         END LOOP;

    	  END LOOP;

    	CLOSE cur_equi;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(lnIdPeriodo);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazRecord LIMIT W_FILAS;
      /* Procedimiento inicial para generar interfaz */
       IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
       END IF;

      IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).codigoMarca 			 ||';'||
		  		  	       interfazRecord(x).codigoPeriodo 		 ||';'||
          					 interfazRecord(x).codigoProd 			 ||';'||
          					 interfazRecord(x).tipoOferta 			 ||';'||
          					 interfazRecord(x).unidadesAtendidas ||';'||
          					 interfazRecord(x).importeNeto;
          UTL_FILE.PUT_LINE(V_HANDLE, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_EQUIV_VENTA_REAL_DIARIA: '||ls_sqlerrm);
  END INT_PR_EQUIV_VENTA_REAL_DIARIA;

END;
/

