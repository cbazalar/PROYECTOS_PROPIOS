CREATE OR REPLACE PACKAGE GEN_PKG_RECOM_ERROR IS
 /* Declaracion de Tipos */
 TYPE TIPOCURSOR IS REF CURSOR;

 /* Declaracion de Variables */
 ln_sqlcode NUMBER(10);
 ls_sqlerrm VARCHAR2(150);
 W_FILAS NUMBER := 1000;
 TAG_SALTO_LINEA_HTML VARCHAR2(10) := '<br/>';
 TAG_INI_NEGRITA VARCHAR2(20) := '<STRONG>';
 TAG_FIN_NEGRITA VARCHAR2(20) := '</STRONG>';
 TAG_INTERFACE_ENTRADA VARCHAR2(200) := 'ES NECESARIO volver a COLOCAR EL ARCHIVO en el FTP, ANTES DE EJECUTAR DE NUEVO LA OPCION<br/>';
 INTERFACE_SALIDA VARCHAR2(1) := 'S';

 /* Declaracion de procedures */


/***************************************************************************
   Descripcion       : Procedimiento que devuelve la Recomendacion en caso
                       exista error en el Proceso BATCH
   Fecha Creacion    : 01/08/2010
   Autor             : Carlos Bazalar
   Parametros        :
      psCodigoPais           Codigo de Pais
      psCodigoSistema        Codigo de Sistema
      psCodigoProcesoBatch   Codigo de Proceso Batch
      psRecomendacion        Variable de Salida que devuelve la recomendacion
****************************************************************************************/
PROCEDURE GEN_PR_DEVUE_RECOM_ERROR_PBATC(
   psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoProcesoBatch VARCHAR2,
   psRecomendacion OUT VARCHAR2 );

/***************************************************************************
   Descripcion       : Funcion que devuelve la Recomendacion COMUN en caso
                       exista error en el Proceso BATCH
   Fecha Creacion    : 01/08/2010
   Autor             : Carlos Bazalar
   Parametros        :
      psCodigoPais           Codigo de Pais
      psCodigoSistema        Codigo de Sistema
      psCodigoProcesoBatch   Codigo de Proceso Batch
      psDescripcionEtapa     Etapa en la que se ecnuentra el proceso BATCH
                             (Por default es INICIAL)
      psError                Error generado en el Proceso BATCH
****************************************************************************************/
FUNCTION GEN_FN_RECOM_ERROR_COMUN_PBATC(
   psCodigoPais         VARCHAR2,
   psCodigoSistema      VARCHAR2,
   psCodigoProcesoBatch VARCHAR2,
   psDescripcionEtapa   VARCHAR2,
   psError              VARCHAR2 )
RETURN VARCHAR2;

/***************************************************************************
   Descripcion       : Funcion que devuelve la Recomendacion Personalizada por PAIS en caso
                       exista error en el Proceso BATCH
   Fecha Creacion    : 01/08/2010
   Autor             : Carlos Bazalar
   Parametros        :
      psCodigoPais           Codigo de Pais
      psCodigoSistema        Codigo de Sistema
      psCodigoProcesoBatch   Codigo de Proceso Batch
      psDescripcionEtapa     Etapa en la que se ecnuentra el proceso BATCH
                             (Por default es INICIAL)
      psRecomendacionComun   Recomendacion COMUN Obtenida
      psError                Error generado en el Proceso BATCH
****************************************************************************************/
FUNCTION GEN_FN_RECOM_ERROR_PAIS_PBATC(
   psCodigoPais         VARCHAR2,
   psCodigoSistema      VARCHAR2,
   psCodigoProcesoBatch VARCHAR2,
   psDescripcionEtapa   VARCHAR2,
   psRecomendacionComun VARCHAR2,
   psError              VARCHAR2 )
RETURN VARCHAR2;

END GEN_PKG_RECOM_ERROR;
/

CREATE OR REPLACE PACKAGE BODY GEN_PKG_RECOM_ERROR IS

/***************************************************************************
   Descripcion       : Procedimiento que devuelve la Recomendacion en caso
                       exista error en el Proceso BATCH
   Fecha Creacion    : 01/08/2010
   Autor             : Carlos Bazalar
   Parametros        :
      psCodigoPais           Codigo de Pais
      psCodigoSistema        Codigo de Sistema
      psCodigoProcesoBatch   Codigo de Proceso Batch
      psRecomendacion        Variable de Salida que devuelve la recomendacion
****************************************************************************************/
PROCEDURE GEN_PR_DEVUE_RECOM_ERROR_PBATC(
   psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoProcesoBatch VARCHAR2,
   psRecomendacion OUT VARCHAR2 )
IS
   regProcesoBatchActua  bas_proce_batch_actua%ROWTYPE;
   regProcesoBatch       bas_proce_batch%ROWTYPE;
   lscodigoInterfaz      bas_proce_batch.cod_inte%TYPE;
   lsError               VARCHAR2(4000);
   lsTempo               VARCHAR2(4000);
   lsTempoInterfaz       VARCHAR2(4000);
   lsRecomenInterfaz     VARCHAR2(4000);
   lbEsPaquete           BOOLEAN:= FALSE;
   lsTipoInterfaz        bas_inter.tip_inte%TYPE;
   lbBuscar              BOOLEAN;

   CURSOR cInterfaz(vscodigoInterfaz VARCHAR2) IS
   SELECT x.inte_cod_inte
   FROM bas_compo_paque x
   WHERE x.pais_cod_pais = psCodigoPais
     AND x.sist_cod_sist = psCodigoSistema
     AND x.inte_cod_inpa = vscodigoInterfaz;
BEGIN
  /* Obteniendo los datos del Proceso Batch Actual */
  SELECT *
  INTO regProcesoBatchActua
  FROM BAS_PROCE_BATCH_ACTUA A
  WHERE A.PAIS_COD_PAIS = psCodigoPais
     AND A.SIST_COD_SIST = psCodigoSistema
     AND A.PRBA_COD_PROC_BATC = psCodigoProcesoBatch;

  IF regProcesoBatchActua.Epba_Cod_Esta_Proc = '10' THEN
     psRecomendacion := NULL;
     RETURN;
  END IF;

  /* Obteniendo los datos del Proceso Batch */
  SELECT *
  INTO regProcesoBatch
  FROM BAS_PROCE_BATCH A
  WHERE A.PAIS_COD_PAIS = psCodigoPais
    AND A.SIST_COD_SIST = psCodigoSistema
    AND A.COD_PROC_BATC = psCodigoProcesoBatch;

 /* En caso esta asociado a una Interfaz */
 IF regProcesoBatch.Cod_Inte IS NOT NULL THEN
    lscodigoInterfaz := regProcesoBatch.Cod_Inte;

    /* Haciendo bucle a las interfaces del Proceso Batch en caso exista */
    FOR cInte IN cInterfaz(lscodigoInterfaz) LOOP
        lbEsPaquete := TRUE;
        lbBuscar := TRUE;
        lsRecomenInterfaz := '';
        BEGIN
            SELECT y.des_erro
            INTO lsError
            FROM bas_histo_lotes y
            WHERE y.pais_cod_pais = psCodigoPais
              AND y.sist_cod_sist = psCodigoSistema
              AND y.inte_cod_inte = cInte.Inte_Cod_Inte
              AND Y.INPA_COD_INTE = lscodigoInterfaz
              AND y.num_lote = regProcesoBatchActua.Num_Lote;
        EXCEPTION
        WHEN no_data_found THEN
             lbBuscar := FALSE ;
        WHEN TOO_MANY_ROWS THEN
             RETURN;
        END ;

        IF trim(lsError) IS NOT NULL AND lbBuscar THEN
           lbEsPaquete := TRUE;

           /* Verificando la interfaz */
           SELECT a.tip_inte
           INTO lsTipoInterfaz
           FROM bas_inter a
           WHERE a.pais_cod_pais = psCodigoPais
           AND a.sist_cod_sist = psCodigoSistema
           AND a.cod_inte = cInte.Inte_Cod_Inte;

           /* Verificando el error */
           IF lsTipoInterfaz = INTERFACE_SALIDA THEN
              lsTempoInterfaz := TAG_INI_NEGRITA || '* ' || cInte.Inte_Cod_Inte ||': ' || TAG_FIN_NEGRITA;
           ELSE
              lsTempoInterfaz := TAG_INI_NEGRITA || '* ' || cInte.Inte_Cod_Inte ||': ' || TAG_FIN_NEGRITA || TAG_INTERFACE_ENTRADA;
           END IF;
           lsTempo := GEN_FN_RECOM_ERROR_COMUN_PBATC(psCodigoPais, psCodigoSistema, psCodigoProcesoBatch, regProcesoBatchActua.des_etap_proc, lsError);
           lsTempo := GEN_FN_RECOM_ERROR_PAIS_PBATC(psCodigoPais, psCodigoSistema, psCodigoProcesoBatch, regProcesoBatchActua.des_etap_proc, lsTempo, lsError);
           IF trim(lsTempo) IS NOT NULL THEN
              psRecomendacion := psRecomendacion || lsTempoInterfaz || lsTempo  ;
              lsRecomenInterfaz := lsTempoInterfaz || lsTempo;
           END IF;
           IF trim(lsRecomenInterfaz) IS NOT NULL THEN
              UPDATE bas_histo_lotes a
              SET a.des_reco_erro = lsRecomenInterfaz
              WHERE a.pais_cod_pais = psCodigoPais
                AND a.sist_cod_sist = psCodigoSistema
                AND a.inte_cod_inte = cInte.Inte_Cod_Inte
                AND a.num_lote = regProcesoBatchActua.Num_Lote;
           ELSE
              UPDATE bas_histo_lotes a
              SET a.des_reco_erro = NULL
              WHERE a.pais_cod_pais = psCodigoPais
                AND a.sist_cod_sist = psCodigoSistema
                AND a.inte_cod_inte = cInte.Inte_Cod_Inte
                AND a.num_lote = regProcesoBatchActua.Num_Lote;
           END IF;
        END IF;
    END LOOP;
 END IF;
 IF NOT lbEsPaquete OR regProcesoBatch.Cod_Inte IS NOT NULL THEN
    SELECT y.des_log
    INTO lsError
    FROM bas_proce_batch_actua y
    WHERE y.pais_cod_pais = psCodigoPais
      AND y.sist_cod_sist = psCodigoSistema
      AND y.prba_cod_proc_batc = psCodigoProcesoBatch;
    lsTempo := GEN_FN_RECOM_ERROR_COMUN_PBATC(psCodigoPais, psCodigoSistema, psCodigoProcesoBatch, regProcesoBatchActua.des_etap_proc, lsError);
    lsTempo := GEN_FN_RECOM_ERROR_PAIS_PBATC(psCodigoPais, psCodigoSistema, psCodigoProcesoBatch, regProcesoBatchActua.des_etap_proc, lsTempo, lsError);
    IF trim(lsTempo) IS NOT NULL THEN
       psRecomendacion := psRecomendacion || '* ' || lsTempo ;
    END IF;
 END IF;

 IF trim(psRecomendacion) IS NOT NULL THEN
    UPDATE bas_proce_batch_actua a
    SET a.des_reco_erro = psRecomendacion
    WHERE a.pais_cod_pais = psCodigoPais
      AND a.sist_cod_sist = psCodigoSistema
      AND a.prba_cod_proc_batc = psCodigoProcesoBatch;
 ELSE
    UPDATE bas_proce_batch_actua a
    SET a.des_reco_erro = NULL
    WHERE a.pais_cod_pais = psCodigoPais
      AND a.sist_cod_sist = psCodigoSistema
      AND a.prba_cod_proc_batc = psCodigoProcesoBatch;
 END IF;
END GEN_PR_DEVUE_RECOM_ERROR_PBATC;

/***************************************************************************
   Descripcion       : Funcion que devuelve la Recomendacion COMUN en caso
                       exista error en el Proceso BATCH
   Fecha Creacion    : 01/08/2010
   Autor             : Carlos Bazalar
   Parametros        :
      psCodigoPais           Codigo de Pais
      psCodigoSistema        Codigo de Sistema
      psCodigoProcesoBatch   Codigo de Proceso Batch
      psDescripcionEtapa     Etapa en la que se ecnuentra el proceso BATCH
                             (Por default es INICIAL)
      psError                Error generado en el Proceso BATCH
****************************************************************************************/
FUNCTION GEN_FN_RECOM_ERROR_COMUN_PBATC(
   psCodigoPais         VARCHAR2,
   psCodigoSistema      VARCHAR2,
   psCodigoProcesoBatch VARCHAR2,
   psDescripcionEtapa   VARCHAR2,
   psError              VARCHAR2 )
RETURN VARCHAR2
IS
lnPosicion      NUMBER;
lsRecomendacion VARCHAR2(1000) := '';

CURSOR cErrorComun IS
SELECT *
FROM ssicc_comun.bas_recom_error a
ORDER BY a.id_erro;

BEGIN
   FOR cError IN cErrorComun LOOP
       lnPosicion := 0;
       IF trim(cError.Cod_Orac) IS NOT NULL THEN
          lnPosicion := INSTR(psError,cError.Cod_Orac);
       END IF;
       IF lnPosicion > 0 THEN
          lsRecomendacion := cError.des_reco_erro || TAG_SALTO_LINEA_HTML;
          RETURN lsRecomendacion;
       END IF;
       IF trim(cError.Cod_Orac) IS NULL THEN
          lnPosicion := INSTR(psError,cError.Des_Orac);
          IF lnPosicion > 0 THEN
             lsRecomendacion := cError.des_reco_erro || TAG_SALTO_LINEA_HTML;
             RETURN lsRecomendacion;
          END IF;
       END IF;
   END LOOP;
   RETURN lsRecomendacion;
END GEN_FN_RECOM_ERROR_COMUN_PBATC;

/***************************************************************************
   Descripcion       : Funcion que devuelve la Recomendacion Personalizada por PAIS en caso
                       exista error en el Proceso BATCH
   Fecha Creacion    : 01/08/2010
   Autor             : Carlos Bazalar
   Parametros        :
      psCodigoPais           Codigo de Pais
      psCodigoSistema        Codigo de Sistema
      psCodigoProcesoBatch   Codigo de Proceso Batch
      psDescripcionEtapa     Etapa en la que se ecnuentra el proceso BATCH
                             (Por default es INICIAL)
      psRecomendacionComun   Recomendacion COMUN Obtenida
      psError                Error generado en el Proceso BATCH
****************************************************************************************/
FUNCTION GEN_FN_RECOM_ERROR_PAIS_PBATC(
   psCodigoPais         VARCHAR2,
   psCodigoSistema      VARCHAR2,
   psCodigoProcesoBatch VARCHAR2,
   psDescripcionEtapa   VARCHAR2,
   psRecomendacionComun VARCHAR2,
   psError              VARCHAR2 )
RETURN VARCHAR2
IS
lnPosicion      NUMBER;
lsRecomendacion VARCHAR2(1000) := '';

CURSOR cErrorPais IS
SELECT
  id_erro,
  pais_cod_pais,
  sist_cod_sist,
  cod_proc_batc,
  des_etap_proc,
  cod_inte,
  cod_orac,
  des_orac,
  des_reco_erro
FROM bas_recom_error_pais a
WHERE a.pais_cod_pais = psCodigoPais
  AND a.sist_cod_sist = psCodigoSistema
  AND a.cod_proc_batc = psCodigoProcesoBatch
  AND a.des_etap_proc = psDescripcionEtapa
  AND a.des_etap_proc IS NOT NULL
UNION
SELECT
  id_erro,
  pais_cod_pais,
  sist_cod_sist,
  cod_proc_batc,
  des_etap_proc,
  cod_inte,
  cod_orac,
  des_orac,
  des_reco_erro
FROM bas_recom_error_pais x
WHERE x.pais_cod_pais = psCodigoPais
  AND x.sist_cod_sist = psCodigoSistema
  AND x.cod_proc_batc = psCodigoProcesoBatch
  AND x.des_etap_proc IS NULL
ORDER BY cod_orac, des_orac, des_etap_proc DESC;

BEGIN
 IF trim(psRecomendacionComun) IS NOT NULL THEN
   FOR cError IN cErrorPais LOOP
       lnPosicion := 0;
       IF trim(cError.Cod_Orac) IS NOT NULL THEN
          lnPosicion := INSTR(psError,cError.Cod_Orac);
       END IF;
       IF lnPosicion > 0 THEN
          IF trim(cError.des_reco_erro) IS NOT NULL THEN
             lsRecomendacion := cError.des_reco_erro || TAG_SALTO_LINEA_HTML;
          ELSE
             lsRecomendacion := psRecomendacionComun;
          END IF;
          RETURN lsRecomendacion;
       END IF;
       IF trim(cError.Cod_Orac) IS NULL THEN
          lnPosicion := INSTR(psError,cError.Des_Orac);
          IF lnPosicion > 0 THEN
             IF trim(cError.des_reco_erro) IS NOT NULL THEN
                lsRecomendacion := cError.des_reco_erro || TAG_SALTO_LINEA_HTML;
             ELSE
                lsRecomendacion := psRecomendacionComun;
             END IF;
             RETURN lsRecomendacion;
          END IF;
       END IF;
       IF trim(cError.Cod_Orac) IS NULL AND trim(cError.Des_Orac) IS NULL THEN
          IF trim(cError.des_reco_erro) IS NOT NULL THEN
             lsRecomendacion := cError.des_reco_erro || TAG_SALTO_LINEA_HTML;
          ELSE
             lsRecomendacion := psRecomendacionComun;
          END IF;
          RETURN lsRecomendacion;
       END IF;
   END LOOP;
   IF lsRecomendacion IS NULL THEN
      RETURN psRecomendacionComun;
   END IF;
   RETURN lsRecomendacion;
 END IF;
 FOR cError IN cErrorPais LOOP
     lnPosicion := 0;
     IF trim(cError.Cod_Orac) IS NOT NULL THEN
        lnPosicion := INSTR(psError,cError.Cod_Orac);
     END IF;
     IF lnPosicion > 0 THEN
        IF trim(cError.des_reco_erro) IS NOT NULL THEN
           lsRecomendacion := cError.des_reco_erro || TAG_SALTO_LINEA_HTML;
           RETURN lsRecomendacion;
        END IF;
     END IF;
     IF trim(cError.Cod_Orac) IS NULL THEN
        lnPosicion := INSTR(psError,cError.Des_Orac);
        IF lnPosicion > 0 THEN
           IF trim(cError.des_reco_erro) IS NOT NULL THEN
              lsRecomendacion := cError.des_reco_erro || TAG_SALTO_LINEA_HTML;
              RETURN lsRecomendacion;
           END IF;
        END IF;
     END IF;
     IF trim(cError.Cod_Orac) IS NULL AND trim(cError.Des_Orac) IS NULL THEN
        IF trim(cError.des_reco_erro) IS NOT NULL THEN
           lsRecomendacion := cError.des_reco_erro || TAG_SALTO_LINEA_HTML;
        END IF;
        RETURN lsRecomendacion;
    END IF;
 END LOOP;
 RETURN lsRecomendacion;


END GEN_FN_RECOM_ERROR_PAIS_PBATC;

END GEN_PKG_RECOM_ERROR;
/

