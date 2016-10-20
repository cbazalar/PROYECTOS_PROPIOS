CREATE OR REPLACE PACKAGE INT_PKG_DANA IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=5000;

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Campañas (INT-1)
Fecha Creacion    : 14/02/2011
Autor: Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
****************************************************************************************/
PROCEDURE INT_PR_DAN_ENVIO_CAMPA
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoIso            VARCHAR2,
   psCodigoPeriodo        VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Consultoras (DAN-3)
Fecha Creacion    : 14/02/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_DAN_ENVIO_CLIEN
  (psCodigoPais            VARCHAR2,
   psCodigoSistema         VARCHAR2,
   psCodigoInterfaz        VARCHAR2,
   psNombreArchivo         VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo         VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Regiones (DAN-3)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_DAN_ENVIO_REGIO
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoIso            VARCHAR2,
   psCodigoPeriodo        VARCHAR2);

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Zonas (DAN-4)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_DAN_ENVIO_ZONA
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoIso            VARCHAR2,
   psCodigoPeriodo        VARCHAR2);

END INT_PKG_DANA;
/

CREATE OR REPLACE PACKAGE BODY INT_PKG_DANA IS

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de Campañas (DAN-1)
Fecha Creacion    : 14/02/2011
Autor: Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
****************************************************************************************/
PROCEDURE INT_PR_DAN_ENVIO_CAMPA
  (psCodigoPais           VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoIso            VARCHAR2,
   psCodigoPeriodo        VARCHAR2)
   IS
   CURSOR c_interfaz IS
        SELECT bcf.cod_peri codigoCampana,
               cp.val_nomb_peri descripcionCampana,
               bcf.ind_camp_act indicadorActivo
          FROM bas_ctrl_fact bcf,
               seg_perio_corpo spc,
               cra_perio cp
         WHERE bcf.cod_pais = psCodigoPais
         AND   bcf.cod_peri = spc.cod_peri
         AND   spc.oid_peri = cp.peri_oid_peri;


   TYPE interfazRec IS RECORD
       (
        codigoCampana            VARCHAR2(6),
        descripcionCampana       VARCHAR2(50),
        indicadorActivo          VARCHAR2(1)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz();
           LOOP
                   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                                lsLinea := interfazRecord(x).codigoCampana                    ||';'||
                                           interfazRecord(x).descripcionCampana               ||';'||
                                           interfazRecord(x).indicadorActivo;

                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAN_ENVIO_CAMPA: '||ls_sqlerrm);

END INT_PR_DAN_ENVIO_CAMPA;


/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Consultoras (DAN-2)
Fecha Creacion    : 08/02/2011
Autor             : Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_DAN_ENVIO_CLIEN
  (psCodigoPais              VARCHAR2,
   psCodigoSistema           VARCHAR2,
   psCodigoInterfaz          VARCHAR2,
   psNombreArchivo           VARCHAR2,
   psCodigoIso               VARCHAR2,
   psCodigoPeriodo           VARCHAR2)

IS

  CURSOR c_interfaz ( fechaActualizacionInterfaz DATE , vnIdPeriodo NUMBER)IS

    SELECT clie.cod_clie codigoConsultora,
           clie.cod_digi_ctrl digitoControl,
           docu.num_docu_iden numeroDocumentoIdentidad,
           (CASE
              WHEN ( clda.esta_oid_esta_clie = 5 AND clda.num_camp_sin_pedi > 2 ) THEN pq_apl_aux.valor_gen_i18n_sicc(1,7,'MAE_ESTAT_CLIEN')
              ELSE
                 CASE
                    WHEN clda.esta_oid_esta_clie = 8 THEN
                         pq_apl_aux.valor_gen_i18n_sicc (1,2,'MAE_ESTAT_CLIEN')
                    ELSE pq_apl_aux.valor_gen_i18n_sicc (1,clda.esta_oid_esta_clie,'MAE_ESTAT_CLIEN')
                 END
            END
		   ) estatus,
           '01' codigoMarca,
           uadm.cod_regi codigoRegion,
           uadm.cod_zona codigoZona,
           uadm.cod_secc codigoSeccion,
           clie.val_nom1 primerNombre,
           clie.val_nom2 segundoNombre,
           clie.val_ape1 apellidoPaterno,
           clie.val_ape2 apellidoMaterno,
           cltf.val_text_comu telefonoFijo,
           cltm.val_text_comu telefonoMovil,
           CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_TOTAL(TO_NUMBER(clie.oid_clie)) saldo,
           obtener_indicadores (clie.oid_clie, 2) indicadorLider,
           NVL (dupl.flag, '0') flagDuplaCyzone,
           TO_CHAR(pago1.fec_inic,'yyyyMMdd') fechaproximopago
      FROM (
            SELECT oid_clie AS clie_oid_clie FROM mae_clien clie WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_datos_adici WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_unida_admin WHERE ind_acti = 1 AND fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie_vndo AS clie_oid_clie FROM mae_clien_vincu WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_prime_conta WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_direc WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_comun WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie_vndo FROM mae_clien_vincu WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
			UNION
            SELECT clie_oid_clie FROM ccc_movim_cuent_corri WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
           ) data,
           mae_clien clie,
           mae_clien_datos_adici clda,
           (
            SELECT clid.clie_oid_clie, tdoc.oid_tipo_docu, tdoc.cod_tipo_docu, clid.num_docu_iden
              FROM mae_clien_ident clid,
                   mae_tipo_docum tdoc
             WHERE clid.tdoc_oid_tipo_docu = tdoc.oid_tipo_docu
               AND clid.val_iden_docu_prin = 1
           ) docu,
           (
            SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
              FROM mae_clien_comun clco,
                   mae_tipo_comun ticm
             WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
               AND cod_tipo_comu = 'TF'
           ) cltf,
           (
            SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
              FROM mae_clien_comun clco,
                   mae_tipo_comun ticm
             WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
               AND cod_tipo_comu = 'TM'
           ) cltm,
           (
            SELECT mcts.clie_oid_clie, '1' AS flag
              FROM mae_clien_clasi mcc,
                   mae_clien_tipo_subti mcts,
                   mae_clasi mc,
                   mae_tipo_clasi_clien mtcc,
                   mae_subti_clien msc,
                   mae_tipo_clien mtc
             WHERE mcc.ctsu_oid_clie_tipo_subt = mcts.oid_clie_tipo_subt
               AND mcc.clas_oid_clas = mc.oid_clas
               AND mcc.tccl_oid_tipo_clasi = mtcc.oid_tipo_clas
               AND mcts.ticl_oid_tipo_clie = mtc.oid_tipo_clie
               AND mcts.sbti_oid_subt_clie = msc.oid_subt_clie
               AND mc.oid_clas = (select oid_clas from mae_clasi where cod_clas='01' and tccl_oid_tipo_clas = mtcc.oid_tipo_clas)
               AND mtcc.oid_tipo_clas = (select oid_tipo_clas from mae_tipo_clasi_clien where cod_tipo_clas='23' and sbti_oid_subt_clie = mcts.sbti_oid_subt_clie)
           ) dupl,
           (
            SELECT cuad.clie_oid_clie, zorg.cod_regi, zzon.cod_zona, zscc.cod_secc,zzon.oid_zona, zorg.oid_regi,
			 	   acum2.cmp_ulti_pedi
              FROM mae_clien_unida_admin cuad,
                   zon_terri_admin ztad,
                   zon_secci zscc,
                   zon_terri terr,
                   zon_zona zzon,
                   zon_regio zorg,
                   mae_clien clie,
    			   (
   				    SELECT clie_oid_clie,
   					       max(acum2.perd_oid_peri) cmp_ulti_pedi
   				      FROM ped_solic_cabec_acum2 acum2
   				     GROUP BY acum2.clie_oid_clie
    	           ) acum2
             WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
               AND cuad.clie_oid_clie = clie.oid_clie
               AND ztad.zscc_oid_secc = zscc.oid_secc
               AND ztad.terr_oid_terr = terr.oid_terr
               AND zscc.zzon_oid_zona = zzon.oid_zona
               AND zzon.zorg_oid_regi = zorg.oid_regi
               AND cuad.ind_acti = 1
			AND cuad.clie_oid_clie = acum2.clie_oid_clie(+)
           ) uadm,
           (
            SELECT ccro.fec_inic, ccro.zzon_oid_zona, zz.zorg_oid_regi, ccro.perd_oid_peri
              FROM cra_crono ccro,
                   cra_activ craact,
                   zon_zona zz
             WHERE ccro.cact_oid_acti = craact.oid_acti
               AND ccro.zzon_oid_zona = zz.oid_zona
               AND craact.cod_acti = 'V1'
               AND ccro.perd_oid_peri <= vnIdPeriodo
           ) pago1
     WHERE data.clie_oid_clie = clie.oid_clie
       AND data.clie_oid_clie = clda.clie_oid_clie
       AND data.clie_oid_clie = uadm.clie_oid_clie(+)
       AND data.clie_oid_clie = docu.clie_oid_clie(+)
       AND data.clie_oid_clie = cltf.clie_oid_clie(+)
       AND data.clie_oid_clie = cltm.clie_oid_clie(+)
       AND data.clie_oid_clie = dupl.clie_oid_clie(+)
       AND uadm.oid_zona = pago1.zzon_oid_zona(+)
	   AND uadm.oid_regi = pago1.zorg_oid_regi(+)
	   AND uadm.cmp_ulti_pedi = pago1.perd_oid_peri(+);



   TYPE interfazTipo IS RECORD
   (
        codigoconsultora                    MAE_CLIEN.COD_CLIE%TYPE,
        digitoControl                       MAE_CLIEN.COD_DIGI_CTRL%TYPE,
        numeroDocumentoIdentidad            VARCHAR2(30),
        estatus                             VARCHAR2(50),
        codigoMarca                         SEG_MARCA.COD_MARC%TYPE,
        codigoRegion                        ZON_REGIO.COD_REGI%TYPE,
        codigoZona                          ZON_ZONA.COD_ZONA%TYPE,
        codigoSeccion                       ZON_SECCI.COD_SECC%TYPE,
        primerNombre                        MAE_CLIEN.VAL_NOM1%TYPE,
        segundoNombre                       MAE_CLIEN.VAL_NOM2%TYPE,
        apellidoPaterno                     MAE_CLIEN.VAL_APE1%TYPE,
        apellidoMaterno                     MAE_CLIEN.VAL_APE2%TYPE,
        telefonoFijo                        MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        telefonoMovil                       MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        saldo                               NUMBER(12),
        indicadorLider                      VARCHAR2(1),
        flagDuplaCyzone                     VARCHAR2(1),
        fechaproximopago                    VARCHAR2(10)
   );

   TYPE interfazTab IS TABLE OF interfazTipo;

   interfazRecord interfazTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo            BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea                VARCHAR2(1000);
   lbAbrirUtlFile        BOOLEAN;
   lsNombreArchivo        VARCHAR2(50);

   pdFechaUltimaActualizacion DATE;
   lnIdPeriodo  NUMBER;
BEGIN

    lnIdPeriodo    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);-- id del periodo consultante

    SELECT MAX(FEC_IPRO) INTO pdFechaUltimaActualizacion --fecha debe ser cargada con ultimo envio de sicc
      FROM BAS_HISTO_LOTES
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND SIST_COD_SIST= psCodigosistema
       AND INTE_COD_INTE = psCodigoInterfaz
       AND IND_LOER ='N'
       AND FEC_FPRO IS NOT NULL;

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz(pdFechaUltimaActualizacion,lnIdPeriodo);
           LOOP
                   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                             lsLinea := interfazRecord(x).codigoconsultora               ||';'||
                                        interfazRecord(x).digitoControl                  ||';'||
                                        interfazRecord(x).numeroDocumentoIdentidad       ||';'||
                                        interfazRecord(x).estatus                        ||';'||
                                        interfazRecord(x).codigoMarca                    ||';'||
                                        interfazRecord(x).codigoRegion                   ||';'||
                                        interfazRecord(x).codigoZona                     ||';'||
                                        interfazRecord(x).codigoSeccion                  ||';'||
                                        interfazRecord(x).primerNombre      ||' '|| interfazRecord(x).segundoNombre   ||';'||
										interfazRecord(x).apellidoPaterno   ||' '|| interfazRecord(x).apellidoMaterno ||';'||
                                        interfazRecord(x).telefonoFijo                   ||';'||
                                        interfazRecord(x).telefonoMovil                  ||';'||
                                        ltrim(nvl(to_char(interfazRecord(x).saldo,'999999999990.99'),0))||';'||
                                        interfazRecord(x).indicadorLider                 ||';'||
                                        interfazRecord(x).flagDuplaCyzone                ||';'||
                                        interfazRecord(x).fechaproximopago ;


                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAN_ENVIO_CLIEN: '||ls_sqlerrm);

END INT_PR_DAN_ENVIO_CLIEN;


/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Regiones (DAN-3)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_DAN_ENVIO_REGIO
  (psCodigoPais            VARCHAR2,
   psCodigoSistema         VARCHAR2,
   psCodigoInterfaz        VARCHAR2,
   psNombreArchivo         VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo         VARCHAR2)
IS
   CURSOR c_interfaz (vnIdPeriodo NUMBER) IS

       SELECT zr.cod_regi codigoRegion,
              zr.des_regi nombreRegion,
              clie.cod_clie codigoGerenteRegional,
              cliedocu.num_docu_iden numeroDocumento,
              '01' codigoMarca,
              clie.val_nom1 nombre1,
              clie.val_nom2 nombre2,
              clie.val_ape1 apellido1,
              clie.val_ape2 apellido2,
              clietf.val_text_comu telefonoFijo,
              clietm.val_text_comu telefonoMovil,
              clieml.val_text_comu email,
              CASE WHEN cierre.oid_regi IS NULL THEN to_char(ffreg1.fec_inic,'yyyyMMdd') ELSE to_char(ffreg2.fec_inic,'yyyyMMdd') END fecfacRegion
         FROM ZON_REGIO zr,
              MAE_CLIEN clie,
              (
               SELECT clid.clie_oid_clie, tdoc.oid_tipo_docu, tdoc.cod_tipo_docu, clid.num_docu_iden
                 FROM mae_clien_ident clid,
                      mae_tipo_docum tdoc
                WHERE clid.tdoc_oid_tipo_docu = tdoc.oid_tipo_docu
                  AND clid.val_iden_docu_prin = 1
              ) cliedocu,
              (
               SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
                 FROM mae_clien_comun clco,
                      mae_tipo_comun ticm
                WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
                  AND cod_tipo_comu = 'TF'
              ) clietf,
              (
               SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
                 FROM mae_clien_comun clco,
                      mae_tipo_comun ticm
                WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
                  AND cod_tipo_comu = 'TM'
              ) clietm,
              (
               SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
                 FROM mae_clien_comun clco,
                      mae_tipo_comun ticm
                WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
                  AND cod_tipo_comu = 'ML'
              ) clieml,
              (
               SELECT ccro.fec_inic, zr.oid_regi, ccro.perd_oid_peri
                 FROM cra_crono ccro,
                      cra_activ cact,
                      zon_regio zr,
                      zon_zona zz
                WHERE ccro.cact_oid_acti = cact.oid_acti
                  AND trim(cact.cod_acti) = 'FA'
                  AND ccro.perd_oid_peri = vnIdPeriodo
                  AND zr.oid_regi = zz.zorg_oid_regi
                  AND ccro.zzon_oid_zona = zz.oid_zona
                GROUP BY ccro.fec_inic, zr.oid_regi, ccro.perd_oid_peri
              ) ffreg1,
              (
               SELECT ccro.fec_inic, zr.oid_regi, ccro.perd_oid_peri
                 FROM cra_crono ccro,
                      cra_activ cact,
                      zon_regio zr,
                      zon_zona zz
                WHERE ccro.cact_oid_acti = cact.oid_acti
                  AND trim(cact.cod_acti) = 'FA'
                  AND ccro.perd_oid_peri = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( psCodigoPeriodo, 1 ) )
                  AND zr.oid_regi = zz.zorg_oid_regi
                  AND ccro.zzon_oid_zona = zz.oid_zona
                GROUP BY ccro.fec_inic, zr.oid_regi, ccro.perd_oid_peri
              ) ffreg2,
              (
               SELECT zorg.oid_regi, zzon.oid_zona, zorg.cod_regi, zzon.cod_zona, coci.fec_cier
                 FROM fac_contr_cierr coci,
                      own_comun.fac_tipos_cierr tcie,
                      zon_regio zorg,
                      zon_regio zorg2,
                      zon_zona zzon,
                      cra_perio perd
                WHERE coci.zzon_oid_zona = zzon.oid_zona(+)
                  AND coci.perd_oid_peri = perd.oid_peri
                  AND tcie.oid_tipo_cier(+) = coci.tcie_oid_tipo_cier
                  AND zorg.oid_regi(+) = coci.zorg_oid_regi
                  AND zzon.zorg_oid_regi = zorg2.oid_regi(+)
                  AND perd.oid_peri = vnIdPeriodo
                  AND tcie.oid_tipo_cier in (1,2)  ---1 = Zona , 2: Region
                GROUP BY zorg.oid_regi, zzon.oid_zona, zorg.cod_regi, zzon.cod_zona, fec_cier
              ) cierre
                 WHERE zr.clie_oid_clie = clie.oid_clie (+)
                   AND zr.clie_oid_clie = cliedocu.clie_oid_clie (+)
                   AND zr.clie_oid_clie = clietf.clie_oid_clie (+)
                   AND zr.clie_oid_clie = clietm.clie_oid_clie (+)
                   AND zr.clie_oid_clie = clieml.clie_oid_clie (+)
                   AND zr.oid_regi = ffreg1.oid_regi (+)
                   AND ffreg1.oid_regi = cierre.oid_regi (+)
                   AND zr.oid_regi = ffreg2.oid_regi (+)
                   AND zr.ind_acti = 1
                   AND zr.ind_borr = 0
                   AND zr.cod_regi NOT IN ('09','99','88');


   TYPE interfazRec IS RECORD
       (
        codigoRegion                ZON_REGIO.COD_REGI%TYPE,
        nombreRegion                ZON_REGIO.DES_REGI%TYPE,
        codigoGerenteRegional       MAE_CLIEN.COD_CLIE%TYPE,
        numeroDocumento             MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE,
        codigoMarca                 SEG_MARCA.COD_MARC%TYPE,
        nombre1                     MAE_CLIEN.VAL_NOM1%TYPE,
        nombre2                     MAE_CLIEN.VAL_NOM2%TYPE,
        apellido1                   MAE_CLIEN.VAL_APE1%TYPE,
        apellido2                   MAE_CLIEN.VAL_APE2%TYPE,
        telefonoFijo                MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        telefonoMovil               MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        email                       MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        fecfacRegion                VARCHAR2(10)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lnIdPeriodo         NUMBER;
   lbAbrirUtlFile      BOOLEAN;

BEGIN

    lnIdPeriodo    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);-- id del periodo consultante

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz(lnIdPeriodo);
        LOOP
             FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                          lsLinea :=  interfazRecord(x).codigoRegion                 ||';'||
                                      interfazRecord(x).nombreRegion                 ||';'||
                                      interfazRecord(x).codigoGerenteRegional        ||';'||
                                      interfazRecord(x).numeroDocumento              ||';'||
                                      interfazRecord(x).codigoMarca                  ||';'||
                                      interfazRecord(x).nombre1 ||' '||interfazRecord(x).nombre2 ||' '|| interfazRecord(x).apellido1 ||' '||   interfazRecord(x).apellido2  ||';'||
                                      interfazRecord(x).telefonoFijo                 ||';'||
                                      interfazRecord(x).telefonoMovil                ||';'||
                                      interfazRecord(x).email                        ||';'||
                                      interfazRecord(x).fecfacRegion;

                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAN_ENVIO_REGIO: '||ls_sqlerrm);

END INT_PR_DAN_ENVIO_REGIO;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Zonas (DAN-4)
Fecha Creacion    : 14/02/2011
Autor             :  Sergio Buchelli
Parametros:
 psCodigoPais :   Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoPeriodo : Codigo Periodo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
***************************************************************************/
PROCEDURE INT_PR_DAN_ENVIO_ZONA
  (psCodigoPais            VARCHAR2,
   psCodigoSistema         VARCHAR2,
   psCodigoInterfaz        VARCHAR2,
   psNombreArchivo         VARCHAR2,
   psCodigoIso             VARCHAR2,
   psCodigoPeriodo         VARCHAR2)

IS
   CURSOR c_interfaz (vnIdPeriodo NUMBER) IS

     SELECT clie.cod_clie codigoGerenteZona,
            cliedocu.num_docu_iden numeroDocumento,
            '01' codigoMarca,
            zr.cod_regi codigoRegion,
            zz.cod_zona codigoZona,
            zz.des_zona nombreZona,
            clie.val_nom1 nombre1,
            clie.val_nom2 nombre2,
            clie.val_ape1 apellido1,
            clie.val_ape2 apellido2,
            clietf.val_text_comu telefonoFijo,
            clietm.val_text_comu telefonoMovil,
            clieml.val_text_comu email,
            CASE WHEN cierre.oid_zona IS NULL THEN to_char(fechas1.fec_confe,'yyyyMMdd') ELSE to_char(fechas2.fec_confe,'yyyyMMdd') END fecconZona,
            CASE WHEN cierre.oid_zona IS NULL THEN to_char(fechas1.fec_cdr,'yyyyMMdd') ELSE to_char(fechas2.fec_cdr,'yyyyMMdd') END feccdrZona
       FROM ZON_ZONA zz,
            ZON_REGIO zr,
            MAE_CLIEN clie,
            (
             SELECT clid.clie_oid_clie, tdoc.oid_tipo_docu, tdoc.cod_tipo_docu, clid.num_docu_iden
               FROM mae_clien_ident clid,
                    mae_tipo_docum tdoc
              WHERE clid.tdoc_oid_tipo_docu = tdoc.oid_tipo_docu
                AND clid.val_iden_docu_prin = 1
            ) cliedocu,
            (
             SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
               FROM mae_clien_comun clco,
                    mae_tipo_comun ticm
              WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
                AND cod_tipo_comu = 'TF'
            ) clietf,
            (
             SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
               FROM mae_clien_comun clco,
                    mae_tipo_comun ticm
              WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
                AND cod_tipo_comu = 'TM'
            ) clietm,
            (
             SELECT clco.clie_oid_clie, ticm.oid_tipo_comu, ticm.cod_tipo_comu, clco.val_text_comu
               FROM mae_clien_comun clco,
                    mae_tipo_comun ticm
              WHERE clco.ticm_oid_tipo_comu = ticm.oid_tipo_comu
                AND cod_tipo_comu = 'ML'
            ) clieml,
            (
             SELECT zzon_oid_zona, MAX(fec_confe) AS fec_confe, MAX(fec_cdr) As fec_cdr
               FROM (
                     SELECT ccro.zzon_oid_zona,
                            CASE WHEN cact.cod_acti = 'RE' THEN ccro.fec_inic END AS fec_confe,
                            CASE WHEN cact.cod_acti = 'CD' THEN ccro.fec_inic END AS fec_cdr
                       FROM cra_crono ccro,
                            cra_activ cact
                      WHERE ccro.cact_oid_acti = cact.oid_acti
                        AND cact.cod_acti IN ('RE','CD')
                        AND ccro.perd_oid_peri = vnIdPeriodo
                    )
              GROUP BY zzon_oid_zona
            ) fechas1,
            (
             SELECT zzon_oid_zona, MAX(fec_confe) AS fec_confe, MAX(fec_cdr) As fec_cdr
               FROM (
                     SELECT ccro.zzon_oid_zona,
                            CASE WHEN cact.cod_acti = 'RE' THEN ccro.fec_inic END AS fec_confe,
                            CASE WHEN cact.cod_acti = 'CD' THEN ccro.fec_inic END AS fec_cdr
                       FROM cra_crono ccro,
                            cra_activ cact
                      WHERE ccro.cact_oid_acti = cact.oid_acti
                        AND cact.cod_acti IN ('RE','CD')
                        AND ccro.perd_oid_peri = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( psCodigoPeriodo, 1 ) )
                    )
              GROUP BY zzon_oid_zona
            ) fechas2,
            (
             SELECT zorg.oid_regi, zzon.oid_zona, zorg.cod_regi, zzon.cod_zona, coci.fec_cier
               FROM fac_contr_cierr coci,
                    own_comun.fac_tipos_cierr tcie,
                    zon_regio zorg,
                    zon_regio zorg2,
                    zon_zona zzon,
                    cra_perio perd
              WHERE coci.zzon_oid_zona = zzon.oid_zona(+)
                AND coci.perd_oid_peri = perd.oid_peri
                AND tcie.oid_tipo_cier(+) = coci.tcie_oid_tipo_cier
                AND zorg.oid_regi(+) = coci.zorg_oid_regi
                AND zzon.zorg_oid_regi = zorg2.oid_regi(+)
                AND perd.oid_peri = vnIdPeriodo
                AND tcie.oid_tipo_cier in (1,2)  ---1 = Zona , 2: Region
              GROUP BY zorg.oid_regi, zzon.oid_zona, zorg.cod_regi, zzon.cod_zona, fec_cier
             ) cierre
       WHERE zz.zorg_oid_regi = zr.oid_regi
         AND zz.clie_oid_clie = clie.oid_clie (+)
         AND zz.clie_oid_clie = cliedocu.clie_oid_clie (+)
         AND zz.clie_oid_clie = clietf.clie_oid_clie (+)
         AND zz.clie_oid_clie = clietm.clie_oid_clie (+)
         AND zz.clie_oid_clie = clieml.clie_oid_clie (+)
         AND zz.oid_zona = fechas1.zzon_oid_zona (+)
         AND fechas1.zzon_oid_zona = cierre.oid_zona (+)
         AND zz.oid_zona = fechas2.zzon_oid_zona (+)
         AND zz.ind_acti = 1
         AND zz.ind_borr = 0
         AND zr.cod_regi NOT IN ('09','99','88');


   TYPE interfazRec IS RECORD
       (
        codigoGerenteZona         MAE_CLIEN.COD_CLIE%TYPE,
        numeroDocumento           MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE,
        codigoMarca               VARCHAR2(3),
        codigoRegion              ZON_REGIO.COD_REGI%TYPE,
        codigoZona                ZON_ZONA.COD_ZONA%TYPE,
        nombreZona                ZON_ZONA.DES_ZONA%TYPE,
        nombre1                   MAE_CLIEN.VAL_NOM1%TYPE,
        nombre2                   MAE_CLIEN.VAL_NOM2%TYPE,
        apellido1                 MAE_CLIEN.VAL_APE1%TYPE,
        apellido2                 MAE_CLIEN.VAL_APE2%TYPE,
        telefonoFijo              MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        telefonoMovil             MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        email                     MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        fecconZona                VARCHAR2(10),
        feccdrZona                VARCHAR2(10)
       );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lnIdPeriodo         NUMBER;
   lbAbrirUtlFile      BOOLEAN;


BEGIN

    lnIdPeriodo    := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);-- id del periodo consultante

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz(lnIdPeriodo);
         LOOP
             FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                             lsLinea := interfazRecord(x).codigoGerenteZona            ||';'||
                                        interfazRecord(x).numeroDocumento                 ||';'||
                                        interfazRecord(x).codigoMarca                     ||';'||
                                        interfazRecord(x).codigoRegion                    ||';'||
                                        interfazRecord(x).codigoZona                      ||';'||
                                        interfazRecord(x).nombreZona                      ||';'||
                                        interfazRecord(x).nombre1 ||' '||interfazRecord(x).nombre2 ||' '|| interfazRecord(x).apellido1 ||' '||   interfazRecord(x).apellido2  ||';'||
                                        interfazRecord(x).telefonoFijo                    ||';'||
                                        interfazRecord(x).telefonoMovil                   ||';'||
                                        interfazRecord(x).email                           ||';'||
                                        interfazRecord(x).fecconZona                      ||';'||
                                        interfazRecord(x).feccdrZona;


                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
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
   RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAN_ENVIO_ZONA: '||ls_sqlerrm);

END INT_PR_DAN_ENVIO_ZONA;

END INT_PKG_DANA;
/

