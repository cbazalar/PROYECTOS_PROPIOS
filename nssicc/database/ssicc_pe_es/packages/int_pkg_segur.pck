CREATE OR REPLACE PACKAGE INT_PKG_SEGUR AS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;

/****************************************************************************
Descripcion       : Interfaz que envía nuevas solicitudes de pólizas (SGR-1)
Fecha Creacion    : 17/05/2011
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 pdFechaCancel    : Fecha Cancelación
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo

Autor: Carlos Mori
Modificdo : Sergio Buchelli
*****************************************************************************/
PROCEDURE INT_PR_SGR_ENVIO_POLIZ_REGIS( psCodigoPais     VARCHAR2,
                                        psCodigoPeriodo  VARCHAR2,
                                        psFechaFact      VARCHAR2,
                                        psCodigoSistema  VARCHAR2,
                                        psCodigoInterfaz VARCHAR2,
                                        psNombreArchivo  VARCHAR2);


/****************************************************************************
Descripcion       : Interfaz que envía pólizas canceladas por egreso (SGR-2)
Fecha Creacion    : 17/05/2011
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 pdFechaCancel    : Fecha Cancelación
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo

Autor: Dany Romero
Modificdo : Sergio Buchelli
*****************************************************************************/
PROCEDURE INT_PR_SGR_ENVIO_POLIZ_CANCE (psCodigoPais       VARCHAR2,
                                         psCodigoPeriodo    VARCHAR2,
                                         psFechaCancel      VARCHAR2,
                                         psCodigoSistema    VARCHAR2,
                                         psCodigoInterfaz   VARCHAR2,
                                         psNombreArchivo    VARCHAR2);


/****************************************************************************
Descripcion       : Interfaz que envía pólizas vigentes (SGR-3)
Fecha Creacion    : 17/05/2011
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 pdFechaFact      : Fecha Facturación
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo

Autor: Dany Romero
Modificdo : Sergio Buchelli
*****************************************************************************/
PROCEDURE INT_PR_SGR_ENVIO_POLIZ_VIGEN(psCodigoPais       VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFact        VARCHAR2,
                                       psCodigoSistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2);



/****************************************************************************
Descripcion       : Interfaz que envía log de errores de carga (SGR-4)
Fecha Creacion    : 24/05/2011
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 pdFechaFact      : Fecha Facturación
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo

Autor: Dany Romero
Modificdo : Sergio Buchelli
*****************************************************************************/
PROCEDURE INT_PR_SGR_ENVIO_LOG_ERROR(  psCodigoPais       VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaRegistro    VARCHAR2,
                                       psCodigoSistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2);

/****************************************************************************
Descripcion       : Proceoso que sencarga de hacer las validaciones del archivo de recewpcion
                    y grabar en el tabala de error (SGR-5)
Fecha Creacion    : 25/05/2011
Parametros:
    psNumeroPoliza         Numero Poliza
    psTipoDocumento        Tipo Documento
    psNumeroDocumento      Numero documento
    psCodigoCliente        Codigo Cliente
    psCodigoTerritorio     Codigo territorio
    psFechaRegistro        Fecha Registro
    psCampanhaCancel       Periodo
    psCodigoMotivoCancel   Motivp rechazo
    psUsuario              Usuario
    psCodigoError          OUT VARCHAR2,
    psMensajeError         OUT VARCHAR2

Autor: Sergio Buchelli
*****************************************************************************/
PROCEDURE INT_PR_SGR_RECEP_POLIZ_CANCE(psNumeroPoliza         VARCHAR2,
                                       psTipoDocumento        VARCHAR2,
                                       psNumeroDocumento      VARCHAR2,
                                       psCodigoCliente        VARCHAR2,
                                       psCodigoTerritorio     VARCHAR2,
                                       psFechaRegistro        VARCHAR2,
                                       psCampanhaCancel       VARCHAR2,
                                       psCodigoMotivoCancel   VARCHAR2,
                                       psUsuario              VARCHAR2,
                                       psCodigoError          OUT VARCHAR2,
                                       psMensajeError         OUT VARCHAR2);

END INT_PKG_SEGUR;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_SEGUR AS

/******************************************************************************
Descripcion       : Interfaz que envía nuevas solicitudes de pólizas (SGR-1)
Fecha Creacion    : 17/05/2011
Fecha Modificacion: 24/07/2012
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 pdFechaCancel    : Fecha Cancelación
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo

Autor: Carlos Mori
Modificado : CSVD - FFVV
*****************************************************************************/
PROCEDURE INT_PR_SGR_ENVIO_POLIZ_REGIS( psCodigoPais     VARCHAR2,
                                        psCodigoPeriodo  VARCHAR2,
                                        psFechaFact      VARCHAR2,
                                        psCodigoSistema  VARCHAR2,
                                        psCodigoInterfaz VARCHAR2,
                                       psNombreArchivo  VARCHAR2) IS

  CURSOR c_nuevas(vdfechUltmProceso DATE, vsCodPaisOCR VARCHAR2, vsIndValidaTipoDoc VARCHAR2) IS

    SELECT polr.num_poli AS numeroPoliza,
           polr.fec_regi_soli AS fechaProceso, -- corresponde a la fecha en que se proceso la Poliza
           CASE
             WHEN vdfechUltmProceso IS NOT NULL
               AND polr.fec_regi_soli < vdfechUltmProceso
               AND clie_id.fec_ulti_actu > vdfechUltmProceso
               AND clie_id.num_docu_iden <> polr.num_docu_iden
             -- en caso de ser actualizacion de documento de identidad
              THEN '3'
             WHEN polr.num_poli_remp IS NOT NULL
             -- en caso de ser reemplazo de una póliza anterior
              THEN '2'
           -- en caso de ser un nuevo registro normal
             ELSE '1'
           END AS tipoSolicitud,
           CASE
             WHEN vsIndValidaTipoDoc = '0' THEN '01'
             ELSE polr.tip_docu_iden
           END AS tipoDocumentoIdentidadSGR,
           polr.num_docu_iden AS numeroDocumentoIdentidad,
           polr.cod_clie AS codigoConsultora ,
           clie.val_nom1 AS primerNombre,
           clie.val_nom2 AS segundoNombre,
           clie.val_ape1 AS apellidoPaterno,
           clie.val_ape2 AS apellidoMaterno,
           polr.val_sexo AS sexo,
           polr.est_civi AS estadoCivil,
           polr.fec_naci AS fechaNacimiento,
           TRIM( TRIM(fijo.val_text_comu) || ' ' || TRIM(trab.val_text_comu) ) AS telefonoFijo,
           TRIM(celu.val_text_comu) AS telefonoCelular,
           TRIM(mail.val_text_comu) AS correoElectronico,
           TRIM(DECODE(TRIM(dire.des_abrv_tipo_via), NULL, '', ' ' || TRIM(dire.des_abrv_tipo_via)) ||
                DECODE(TRIM(dire.val_nomb_via), NULL, '', ' ' || TRIM(dire.val_nomb_via)) ||
                DECODE(TRIM(dire.num_ppal), NULL, '', ' ' || TRIM(dire.num_ppal)) ||
                DECODE(TRIM(dire.val_obse), NULL, '', ' ' || TRIM(dire.val_obse)) ||
                DECODE(TRIM(dire.val_barr), NULL, '', ' ' || TRIM(dire.val_barr))) AS direccion,
           dire.distrito AS descripcionDistrito,
           SUBSTR(dire.cod_unid_geog,17,2) AS codigoDistrito,
           dire.departamento AS descripcionDepartamento,
           SUBSTR(dire.cod_unid_geog,5,2) AS codigoDepartamento,
           dire.provincia AS descripcionProvincia,
           SUBSTR(dire.cod_unid_geog,10,3) AS codigoProvincia,
           polr.fec_soli AS fechaSolicitud,
           uas.cod_regi || uas.cod_zona || uas.cod_secc ||uas.cod_terr AS codigoTerritorio,
           uas.cod_regi AS codigoRegion,
           uas.cod_zona AS codigoZona,
           camp.fec_inic AS fechaInicioCampana,
           camp.fec_fina AS fechaFinCampana,
           camp.cod_peri AS codigoCampana,
           vsCodPaisOCR|| polr.num_poli || '.TIF' AS nombreImagen,
           CASE
             WHEN vdfechUltmProceso IS NOT NULL AND clie_id.fec_ulti_actu > vdfechUltmProceso AND
                  polr.fec_regi_soli < vdfechUltmProceso AND clie_id.num_docu_iden <> polr.num_docu_iden THEN
              CASE
                WHEN vsIndValidaTipoDoc = '0' THEN ''
                ELSE tdoc.cod_tipo_docu
              END
             ELSE ''
           END AS tipoDocumentoMAE,
           CASE
             WHEN vdfechUltmProceso IS NOT NULL AND clie_id.fec_ulti_actu > vdfechUltmProceso AND
                  polr.fec_regi_soli < vdfechUltmProceso AND clie_id.num_docu_iden <> polr.num_docu_iden
                  THEN clie_id.num_docu_iden
             ELSE ''
           END AS numeroDocumentoMAE,
           polr.num_poli_remp AS numeroPolizaReemplazada,
           polr.ind_orig_regi AS origenRegistroPoliza
      FROM sgr_famse_poliz_regis polr,
           sgr_famse_poliz po,
           mae_clien clie,
           mae_clien_ident clie_id,
           mae_tipo_docum tdoc,
           ( SELECT clie_oid_clie, val_text_comu FROM mae_clien_comun WHERE ticm_oid_tipo_comu = 1 ) fijo,
           ( SELECT clie_oid_clie, val_text_comu FROM mae_clien_comun WHERE ticm_oid_tipo_comu = 6 ) celu,
           ( SELECT clie_oid_clie, val_text_comu FROM mae_clien_comun WHERE ticm_oid_tipo_comu = 7 ) trab,
           ( SELECT clie_oid_clie, val_text_comu FROM mae_clien_comun WHERE ticm_oid_tipo_comu = 3 ) mail,
           (SELECT cuad.clie_oid_clie, zorg.cod_regi, zzon.cod_zona, zscc.cod_secc, terr.cod_terr
              FROM mae_clien_unida_admin cuad,
                   zon_terri_admin ztad,
                   zon_secci zscc,
                   zon_terri terr,
                   zon_zona zzon,
                   zon_regio zorg
             WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
               --AND vnOidPeriodo BETWEEN cuad.perd_oid_peri_ini AND NVL(cuad.perd_oid_peri_fin,vnOidPeriodo) -- Tomar en cuenta si se aplica para reprocesos
               AND ztad.zscc_oid_secc = zscc.oid_secc
               AND ztad.terr_oid_terr = terr.oid_terr
               AND zscc.zzon_oid_zona = zzon.oid_zona
               AND zzon.zorg_oid_regi = zorg.oid_regi
               AND cuad.ind_acti = 1 -- Si hay reprocesos esto se desactiva
           ) uas,
           (SELECT perd.oid_peri, peri.cod_peri, perd.fec_inic, perd.fec_fina
              FROM cra_perio perd, seg_perio_corpo peri
             WHERE perd.peri_oid_peri = peri.oid_peri
               AND cod_peri = pscodigoperiodo  -- Esto corresponde al parámetro de "Campaña de Facturación"
           ) camp,
           (SELECT clie_oid_clie,
                   des_abrv_tipo_via,
                   val_nomb_via,
                   num_ppal,
                   val_obse,
                   cod_terr,
                   nivel_1 AS "DEPARTAMENTO",
                   DECODE (nivel_2, NULL, '', nivel_2) AS "PROVINCIA",
                   DECODE (nivel_3, NULL, '', nivel_3) AS "DISTRITO",
                   DECODE (nivel_4, NULL, '', nivel_4) AS "CIUDAD",
                   DECODE(nivel_5, NULL, '', '/' || nivel_5) || DECODE(nivel_6, NULL, '', '/' || nivel_6) ||
                   DECODE(nivel_7, NULL, '', '/' || nivel_7) || DECODE(nivel_8, NULL, '', '/' || nivel_8) ||
                   DECODE(nivel_9, NULL, '', '/' || nivel_9) AS desc_uni,
                   val_barr,
                   cod_unid_geog
              FROM (SELECT A.clie_oid_clie,
                  A.oid_clie_dire OID,
                  c.des_abrv_tipo_via,
                  A.val_nomb_via,
                  A.num_ppal,
                  A.val_obse,
                  A.val_barr,
                  t.cod_terr,
                  A.cod_unid_geog,
                           (SELECT des_geog
                              FROM zon_valor_estru_geopo
                             WHERE pais_oid_pais = d.pais_oid_pais
                               AND orde_1 = SUBSTR (A.cod_unid_geog, 1, 6)
                               AND orde_2 IS NULL) AS nivel_1,
                           (SELECT des_geog
                              FROM zon_valor_estru_geopo
                             WHERE pais_oid_pais = d.pais_oid_pais
                               AND orde_1 = SUBSTR (A.cod_unid_geog, 1, 6)
                               AND orde_2 = SUBSTR (A.cod_unid_geog, 7, 6)
                               AND orde_3 IS NULL) AS nivel_2,
                           (SELECT des_geog
                              FROM zon_valor_estru_geopo
                             WHERE pais_oid_pais = d.pais_oid_pais
                               AND orde_1 = SUBSTR (A.cod_unid_geog, 1, 6)
                               AND orde_2 = SUBSTR (A.cod_unid_geog, 7, 6)
                               AND orde_3 = SUBSTR (A.cod_unid_geog, 13, 6)
                               AND orde_4 IS NULL) AS nivel_3,
                           CASE
                             WHEN LENGTH(A.cod_unid_geog) > 18 THEN
                              (SELECT des_geog
                                         FROM zon_valor_estru_geopo
                                        WHERE pais_oid_pais = d.pais_oid_pais
                                          AND orde_1 = SUBSTR (A.cod_unid_geog, 1, 6)
                                          AND orde_2 = SUBSTR (A.cod_unid_geog, 7, 6)
                                          AND orde_3 = SUBSTR (A.cod_unid_geog, 13, 6)
                                          AND orde_4 = SUBSTR (A.cod_unid_geog, 19, 6)
                                          AND orde_5 IS NULL)
                             ELSE
                              NULL
                           END AS nivel_4,
                           CASE
                             WHEN LENGTH(A.cod_unid_geog) > 24 THEN
                              (SELECT des_geog
                                         FROM zon_valor_estru_geopo
                                        WHERE pais_oid_pais = d.pais_oid_pais
                                          AND orde_1 = SUBSTR (A.cod_unid_geog, 1, 6)
                                          AND orde_2 = SUBSTR (A.cod_unid_geog, 7, 6)
                                          AND orde_3 = SUBSTR (A.cod_unid_geog, 13, 6)
                                          AND orde_4 = SUBSTR (A.cod_unid_geog, 19, 6)
                                          AND orde_5 = SUBSTR (A.cod_unid_geog, 25, 6)
                                          AND orde_6 IS NULL)
                             ELSE
                              NULL
                           END AS nivel_5,
                           CASE
                             WHEN LENGTH(A.cod_unid_geog) > 30 THEN
                              (SELECT des_geog
                                         FROM zon_valor_estru_geopo
                                        WHERE pais_oid_pais = d.pais_oid_pais
                                          AND orde_1 = SUBSTR (A.cod_unid_geog, 1, 6)
                                          AND orde_2 = SUBSTR (A.cod_unid_geog, 7, 6)
                                          AND orde_3 = SUBSTR (A.cod_unid_geog, 13, 6)
                                          AND orde_4 = SUBSTR (A.cod_unid_geog, 19, 6)
                                          AND orde_5 = SUBSTR (A.cod_unid_geog, 25, 6)
                                          AND orde_6 = SUBSTR (A.cod_unid_geog, 31, 6)
                                          AND orde_7 IS NULL)
                             ELSE
                              NULL
                           END AS nivel_6,
                           CASE
                             WHEN LENGTH(A.cod_unid_geog) > 36 THEN
                              (SELECT des_geog
                                         FROM zon_valor_estru_geopo
                                        WHERE pais_oid_pais = d.pais_oid_pais
                                          AND orde_1 = SUBSTR (A.cod_unid_geog, 1, 6)
                                          AND orde_2 = SUBSTR (A.cod_unid_geog, 7, 6)
                                          AND orde_3 = SUBSTR (A.cod_unid_geog, 13, 6)
                                          AND orde_4 = SUBSTR (A.cod_unid_geog, 19, 6)
                                          AND orde_5 = SUBSTR (A.cod_unid_geog, 25, 6)
                                          AND orde_6 = SUBSTR (A.cod_unid_geog, 31, 6)
                                          AND orde_7 = SUBSTR (A.cod_unid_geog, 37, 6)
                                          AND orde_8 IS NULL)
                             ELSE
                              NULL
                           END AS nivel_7,
                           CASE
                             WHEN LENGTH(A.cod_unid_geog) > 42 THEN
                              (SELECT des_geog
                                         FROM zon_valor_estru_geopo
                                        WHERE pais_oid_pais = d.pais_oid_pais
                                          AND orde_1 = SUBSTR (A.cod_unid_geog, 1, 6)
                                          AND orde_2 = SUBSTR (A.cod_unid_geog, 7, 6)
                                          AND orde_3 = SUBSTR (A.cod_unid_geog, 13, 6)
                                          AND orde_4 = SUBSTR (A.cod_unid_geog, 19, 6)
                                          AND orde_5 = SUBSTR (A.cod_unid_geog, 25, 6)
                                          AND orde_6 = SUBSTR (A.cod_unid_geog, 31, 6)
                                          AND orde_7 = SUBSTR (A.cod_unid_geog, 37, 6)
                                          AND orde_8 = SUBSTR (A.cod_unid_geog, 43, 6)
                                          AND orde_9 IS NULL)
                             ELSE
                              NULL
                           END AS nivel_8,
                           CASE
                             WHEN LENGTH(A.cod_unid_geog) > 48 THEN
                              (SELECT des_geog
                                         FROM zon_valor_estru_geopo
                                        WHERE pais_oid_pais = d.pais_oid_pais
                                          AND orde_1 = SUBSTR (A.cod_unid_geog, 1, 6)
                                          AND orde_2 = SUBSTR (A.cod_unid_geog, 7, 6)
                                          AND orde_3 = SUBSTR (A.cod_unid_geog, 13, 6)
                                          AND orde_4 = SUBSTR (A.cod_unid_geog, 19, 6)
                                          AND orde_5 = SUBSTR (A.cod_unid_geog, 25, 6)
                                          AND orde_6 = SUBSTR (A.cod_unid_geog, 31, 6)
                                          AND orde_7 = SUBSTR (A.cod_unid_geog, 37, 6)
                                          AND orde_8 = SUBSTR (A.cod_unid_geog, 43, 6)
                                          AND orde_9 = SUBSTR (A.cod_unid_geog, 49, 6))
                             ELSE
                              NULL
                           END AS nivel_9
                      FROM mae_clien_direc A,
                           own_comun.mae_tipo_direc b,
                           own_comun.seg_tipo_via c,
                           mae_clien d,
                           zon_terri t
                     WHERE d.oid_clie = A.clie_oid_clie
                       AND A.ind_elim = 0
                       AND b.oid_tipo_dire = A.tidc_oid_tipo_dire
                       AND c.oid_tipo_via = A.tivi_oid_tipo_via
                       AND A.ind_dire_ppal  = 1
                       AND A.terr_oid_terr = t.oid_terr(+))
           ) dire
      WHERE polr.poli_cod_poli = po.cod_poli
        AND polr.cod_clie = clie.cod_clie
        AND clie_id.clie_oid_clie = clie.oid_clie
        AND clie_id.tdoc_oid_tipo_docu = tdoc.oid_tipo_docu
       AND tdoc.cod_tipo_docu = polr.tip_docu_iden
        AND clie.oid_clie = fijo.clie_oid_clie(+)
        AND clie.oid_clie = celu.clie_oid_clie(+)
        AND clie.oid_clie = trab.clie_oid_clie(+)
        AND clie.oid_clie = mail.clie_oid_clie(+)
        AND clie.oid_clie = uas.clie_oid_clie(+)
        AND clie.oid_clie = dire.clie_oid_clie(+)
       AND ( -- en caso de ser nuevos registros o reemplazo de póliza
            vdfechUltmProceso IS NULL OR polr.fec_regi_soli > vdfechUltmProceso
           -- en caso de ser registro antiguo de póliza pero hubo actualizacion de documento de identidad
             OR (clie_id.num_docu_iden <> polr.num_docu_iden
                AND clie_id.fec_ulti_actu > vdfechUltmProceso)
           )
        AND polr.est_regi <>'9'
        ORDER BY polr.poli_cod_poli, polr.num_poli;

  TYPE tRecCancel IS RECORD(
    numeroPoliza             sgr_famse_poliz_regis.num_poli%TYPE,
    fechaProceso             sgr_famse_poliz_regis.fec_soli%TYPE,
    tipoSolicitud            VARCHAR2(1),
    tipoDocumentoIdentidad   sgr_famse_poliz_regis.tip_docu_iden%TYPE,
    numeroDocumentoIdentidad sgr_famse_poliz_regis.num_docu_iden%TYPE,
    codigoConsultora         sgr_famse_poliz_regis.cod_clie%TYPE,
    primerNombre             mae_clien.val_nom1%TYPE,
    segundoNombre            mae_clien.val_nom2%TYPE,
    apellidoPaterno          mae_clien.val_ape1%TYPE,
    apellidoMaterno          mae_clien.val_ape2%TYPE,
    sexo                     sgr_famse_poliz_regis.val_sexo%TYPE,
    estadoCivil              sgr_famse_poliz_regis.est_civi%TYPE,
    fechaNacimiento          sgr_famse_poliz_regis.fec_naci%TYPE,
    telefonoFijo             mae_clien_comun.val_text_comu%TYPE,
    telefonoCelular          mae_clien_comun.val_text_comu%TYPE,
    correoElectronico        mae_clien_comun.val_text_comu%TYPE,
    direccion                VARCHAR2(500),
    descripcionDistrito      zon_valor_estru_geopo.des_geog%TYPE,
    codigoDistrito           zon_valor_estru_geopo.cod_unid_geog%TYPE,
    descripcionDepartamento  zon_valor_estru_geopo.des_geog%TYPE,
    codigoDepartamento       zon_valor_estru_geopo.cod_unid_geog%TYPE,
    descripcionProvincia     zon_valor_estru_geopo.des_geog%TYPE,
    codigoProvincia          zon_valor_estru_geopo.cod_unid_geog%TYPE,
    fechaSolicitud           sgr_famse_poliz_regis.fec_soli%TYPE,
    codigoTerritorio         VARCHAR2(13),
    codigoRegion             zon_regio.cod_regi%TYPE,
    codigoZona               zon_zona.cod_zona%TYPE,
    fechaInicioCampana       cra_perio.fec_inic%TYPE,
    fechaFinCampana          cra_perio.fec_fina%TYPE,
    codigoCampana            sgr_famse_poliz_regis.cam_regi%TYPE,
          nombreImagen              VARCHAR2(50),
    tipoDocumentoMAE         mae_tipo_docum.cod_tipo_docu%TYPE,
    numeroDocumentoMAE       mae_clien_ident.num_docu_iden%TYPE,
    numeroPolizaReemplazada  sgr_famse_poliz_regis.num_poli_remp%TYPE,
    origenRegistroPoliza     sgr_famse_poliz_regis.ind_orig_regi%TYPE
        );

   TYPE tTabtRecCancel  IS TABLE OF tRecCancel ;
   interfazRecord tTabtRecCancel;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_hANDle            UTL_FILE.FILE_TYPE;
   lbAbrirUtlFile      BOOLEAN;

  pdFechaUltimaActualizacion DATE := NULL;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lsIndValidaTipoDoc  VARCHAR2(1);
   lsCodPaisOCR        VARCHAR2(3);
BEGIN

  SELECT B.COD_PAIS_OCR INTO lsCodPaisOCR FROM BAS_PAIS_COMPA B WHERE B.COD_PAIS = psCodigoPais;

  SELECT MAX(FEC_IPRO)
    INTO pdFechaUltimaActualizacion -- debe ser cargada con ultim envio de ssicc
      FROM BAS_HISTO_LOTES
     WHERE PAIS_COD_PAIS = psCodigoPais
       AND SIST_COD_SIST= psCodigosistema
       AND INTE_COD_INTE = psCodigoInterfaz
       AND IND_LOER ='N'
       AND FEC_FPRO IS NOT NULL;

    SELECT TRIM(VAL_PARA)
      INTO lsIndValidaTipoDoc
      FROM BAS_PARAM_PAIS B
     WHERE B.COD_PAIS = psCodigoPais
       AND B.COD_SIST = psCodigosistema
       AND B.COD_PARA = '003'
       AND B.IND_ACTI = '1';

  lbAbrirUtlFile := TRUE;

  OPEN c_nuevas(pdFechaUltimaActualizacion, lsCodPaisOCR, lsIndValidaTipoDoc);
      LOOP
    FETCH c_nuevas BULK COLLECT
      INTO interfazRecord LIMIT W_FILAS;

     IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais,
                                             psCodigoSistema,
                                             psCodigoInterfaz,
                                             psNombreArchivo,
                                             lsDirTempo,
                                             lsNombreArchivo,
                                             V_HANDLE);
       lbAbrirUtlFile := FALSE;
     END IF;

     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).numeroPoliza                             ||';'||
                     TO_CHAR(interfazRecord(x).fechaProceso,'YYYYMMDD')         ||';'||
                       interfazRecord(X).tipoSolicitud                            ||';'||
                       interfazRecord(X).tipoDocumentoIdentidad                   ||';'||
                     interfazRecord(X).numeroDocumentoIdentidad                 ||';'||
                     interfazRecord(x).codigoConsultora                         ||';'||
                     interfazRecord(x).primerNombre                             ||';'||
                     interfazRecord(x).segundoNombre                            ||';'||
                     interfazRecord(x).apellidoPaterno                          ||';'||
                     interfazRecord(x).apellidoMaterno                          ||';'||
                     interfazRecord(x).sexo                                     ||';'||
                     interfazRecord(x).estadoCivil                              ||';'||
                     TO_CHAR(interfazRecord(x).fechaNacimiento,'YYYYMMDD')      ||';'||
                     interfazRecord(x).telefonoFijo                             ||';'||
                     interfazRecord(x).telefonoCelular                          ||';'||
                     interfazRecord(x).correoElectronico                        ||';'||
                     interfazRecord(x).direccion                                ||';'||
                     interfazRecord(x).descripcionDistrito                      ||';'||
                     interfazRecord(x).codigoDistrito                           ||';'||
                     interfazRecord(x).descripcionDepartamento                  ||';'||
                     interfazRecord(x).codigoDepartamento                       ||';'||
                     interfazRecord(x).descripcionProvincia                     ||';'||
                     interfazRecord(x).codigoProvincia                          ||';'||
                     to_char(interfazRecord(x).fechaSolicitud,'YYYYMMDD')       ||';'||
                     interfazRecord(x).codigoTerritorio                         ||';'||
                     interfazRecord(x).codigoRegion                             ||';'||
                     interfazRecord(x).codigoZona                               ||';'||
                     TO_CHAR(interfazRecord(x).fechaInicioCampana,'YYYYMMDD')   ||';'||
                     TO_CHAR(interfazRecord(x).fechaFinCampana,'YYYYMMDD')      ||';'||
                     interfazRecord(x).codigoCampana                            ||';'||
                       interfazRecord(x).nombreImagen                             ||';'||
                       interfazRecord(x).tipoDocumentoMAE                         ||';'||
                       interfazRecord(x).numeroDocumentoMAE                     ||';'||
                       interfazRecord(x).numeroPolizaReemplazada                ||';'||
                       interfazRecord(x).origenRegistroPoliza                     ||';'||
                       ''                                                       ||';'||
                       '';

          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;

    EXIT WHEN c_nuevas%NOTFOUND;
        END LOOP;
  CLOSE c_nuevas;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);
       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SGR_ENVIO_POLIZ_REGIS: '||LS_SQLERRM);
END INT_PR_SGR_ENVIO_POLIZ_REGIS;

/*******************************************************************************
Descripcion       : Interfaz que envía pólizas canceladas por egreso (SGR-2)
Fecha Creacion    : 17/05/2011
Fecha Modificacion: 24/07/2012
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 pdFechaCancel    : Fecha Cancelación
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo

Autor: Dany Romero
Modificado : CSVD - FFVV
*********************************************************************************/
PROCEDURE INT_PR_SGR_ENVIO_POLIZ_CANCE (psCodigoPais       VARCHAR2,
                                         psCodigoPeriodo    VARCHAR2,
                                         psFechaCancel      VARCHAR2,
                                         psCodigoSistema    VARCHAR2,
                                         psCodigoInterfaz   VARCHAR2,
                                         psNombreArchivo    VARCHAR2)
IS
   CURSOR c_cancel(vnoidPeriodo NUMBER, vnflagCierre INTEGER) IS

   SELECT *
     FROM (
           (
      SELECT p.num_poli numeroPoliza,
                   p.tip_docu_iden tipoDocumentoIdentidad,
                   p.num_docu_iden numeroDocumentoIdentidad,
                   p.cod_clie codigoConsultora,
                   ''||uas.cod_regi || uas.cod_zona || uas.cod_secc ||uas.cod_terr codigoTerritorio,
                   p.fec_canc fechaCancelacion,
                   p.cam_canc campanaCancelacion,
                   p.moti_cod_moti_canc codigoMotivoCancelacion
              FROM sgr_famse_poliz_regis p,
                   (
                    SELECT cuad.clie_oid_clie, zorg.cod_regi, zzon.cod_zona, zscc.cod_secc, terr.cod_terr
                      FROM mae_clien_unida_admin cuad,
                           zon_terri_admin ztad,
                           zon_secci             zscc,
                           zon_terri             terr,
                           zon_zona              zzon,
                           zon_regio             zorg
                     WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                       AND vnOidPeriodo BETWEEN NVL(cuad.perd_oid_peri_ini,vnOidPeriodo) AND NVL(cuad.perd_oid_peri_fin,vnOidPeriodo)
                       AND ztad.zscc_oid_secc = zscc.oid_secc
                       AND ztad.terr_oid_terr = terr.oid_terr
                       AND zscc.zzon_oid_zona = zzon.oid_zona
                       AND zzon.zorg_oid_regi = zorg.oid_regi
                   ) uas
             WHERE p.est_poli = '4'
               AND CASE
                      WHEN vnFlagCierre = 0 AND p.moti_cod_moti_canc NOT IN ('1','2') THEN 1
                      WHEN vnFlagCierre > 0 AND p.moti_cod_moti_canc IN ('1','2') THEN 1
                      ELSE 0
                   END = 1
               AND TRUNC( p.fec_canc ) = DECODE( vnflagCierre, 0, TO_DATE( psFechaCancel, 'dd/MM/yyyy'), TRUNC(p.fec_canc) )
               AND p.cam_canc = psCodigoPeriodo
               AND uas.clie_oid_clie = p.clie_oid_clie
     )
           UNION
           (
      SELECT f.num_docu       numeroPoliza,
                   td.cod_tipo_docu     tipoDocumentoIdentidad ,
                   f.num_docu_iden  numeroDocumentoIdentidad,
                   f.cod_clie      codigoConsultora ,
                   GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(f.cod_clie, 'COD_TERR') codigoTerritorio,
                   f.fec_proc fechaCancelacion,
                   f.CAM_REGIS  campanaCancelacion    ,
                   '6' codigoMotivoCancelacion
              FROM int_solic_conso_famil_segur f,
                   sto_docum_digit st,
                   mae_tipo_docum td
            WHERE f.sec_nume_docu = st.sec_nume_docu
              AND td.oid_tipo_docu = f.tdoc_oid_tipo_docu
              AND st.ind_rech = '1' -- Rechazado
              AND TRUNC(f.fec_proc) = TO_DATE(psFechaCancel,'dd/MM/yyyy')
              AND f.cam_proc = psCodigoPeriodo -- Nuevo
     )
         ) cp;

   TYPE tRecCancel IS RECORD
     (
         numeroPoliza              sgr_famse_poliz_regis.num_poli%TYPE,
         tipoDocumentoIdentidad    sgr_famse_poliz_regis.num_poli%TYPE,
         numeroDocumentoIdentidad  sgr_famse_poliz_regis.num_poli%TYPE,
         codigoConsultora          sgr_famse_poliz_regis.cod_clie%TYPE,
         codigoTerritorio          VARCHAR2(13),
         fechaCancelacion          sgr_famse_poliz_regis.fec_canc%TYPE,
         campanaCancelacion        sgr_famse_poliz_regis.cam_canc%TYPE,
         codigoMotivoCancelacion   sgr_famse_poliz_regis.moti_cod_moti_canc%TYPE
     );

   TYPE tTabtRecCancel  IS TABLE OF tRecCancel ;
   interfazRecord tTabtRecCancel;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   --W_FILAS             NUMBER := 1000 ;
   v_hANDle            UTL_FILE.FILE_TYPE;
   lbAbrirUtlFile      BOOLEAN;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lnflagCierre INTEGER;
   lnoidPeriodo CRA_PERIO.Oid_Peri%TYPE;
   lnoidPais SEG_PAIS.Oid_Pais%TYPE;

BEGIN

    lbAbrirUtlFile := TRUE;

    lnoidPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( pscodigoPeriodo );
    
    SELECT oid_pais
      INTO lnoidPais
      FROM seg_pais
     WHERE cod_pais = psCodigoPais
         ;
    
    -- Verificar cierre de campaña
    BEGIN
        SELECT COUNT(1)
          INTO lnflagCierre
          FROM fac_contr_cierr fcc,
               fac_tipos_cierr ftc
         WHERE fcc.perd_oid_peri = lnoidPeriodo
           AND fcc.pais_oid_pais = lnoidPais
           AND fcc.tcie_oid_tipo_cier = ftc.oid_tipo_cier
           AND fcc.val_resu_proc = 'OK'
           AND ftc.cod_tipo_cier = 'P'
         GROUP BY fcc.perd_oid_peri
             ;
    EXCEPTION WHEN OTHERS THEN
        lnflagCierre := 0;
    END; -- Nuevo
        
    OPEN c_cancel( lnoidPeriodo, lnflagCierre );
      LOOP
         FETCH c_cancel BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

     IF lbAbrirUtlFile THEN
       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigoSistema, psCodigoInterfaz,
                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
       lbAbrirUtlFile := FALSE;
     END IF;

     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).numeroPoliza                          ||';'||
                     interfazRecord(x).tipoDocumentoIdentidad                ||';'||
                     interfazRecord(x).numeroDocumentoIdentidad              ||';'||
                     interfazRecord(x).codigoConsultora                      ||';'||
                     interfazRecord(x).codigoTerritorio                      ||';'||
                     to_char(interfazRecord(x).fechaCancelacion,'YYYYMMDD')  ||';'||
                     interfazRecord(x).campanaCancelacion                    ||';'||
                     interfazRecord(x).codigoMotivoCancelacion               ||';'||
                     ''                                                      ||';'||
                     '';

          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;

       EXIT WHEN c_cancel%NOTFOUND;
    END LOOP;
    CLOSE c_cancel;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SGR_ENVIO_POLIZ_CANCE: '||ls_sqlerrm);
END INT_PR_SGR_ENVIO_POLIZ_CANCE;


/****************************************************************************
Descripcion       : Interfaz que envía pólizas vigentes (SGR-3)
Fecha Creacion    : 17/05/2011
Fecha Modificacion: 24/07/2012
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 pdFechaFact      : Fecha Facturación
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo

Autor: Dany Romero
Modificado : CSVD - FFVV
*****************************************************************************/
PROCEDURE INT_PR_SGR_ENVIO_POLIZ_VIGEN(psCodigoPais       VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFact        VARCHAR2,
                                       psCodigoSistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2)
IS
   CURSOR c_vigen(vsIndValidaTipoDoc VARCHAR2, vnOidPeriodo NUMBER) IS

     SELECT pr.num_poli numeroPoliza,
            CASE
              WHEN vsIndValidaTipoDoc = '0' THEN '01'
              ELSE pr.tip_docu_iden
            END tipoDocumentoIdentidad,
            pr.num_docu_iden numeroDocumentoIdentidad,
            pr.cod_clie codigoCliente,
            cc.val_impo_poli precioPoliza,
            uas.cod_regi || uas.cod_zona || uas.cod_secc ||uas.cod_terr codigoTerritorio,
            cc.fec_inic_poli fechaInicioVigencia,
            cc.fec_fina_poli fechaFinVigencia,
            camp.fec_inic fechaInicioCampana,
            camp.fec_fina fechaFinCampana,
            camp.cod_peri codigoCampana,
            cc.fec_fact fechaFacturacion,
            CASE WHEN clda.esta_oid_esta_clie IN (1,7) AND cc.cod_poli IS NOT NULL THEN 1 ELSE 0 END indConsuNueva,
            CASE WHEN lide.gere IS NULL THEN 0 ELSE 1 END indConsuLider,
            cc.val_desc_poli impDescuPoliz,
            cc.val_impo_carg impCargoAplic
       FROM sgr_famse_poliz_regis pr,
            ccc_histo_cargo_famil_segur cc,
            (
             SELECT peri.cod_peri, perd.fec_inic, perd.fec_fina
               FROM cra_perio perd, seg_perio_corpo peri
              WHERE perd.peri_oid_peri = peri.oid_peri
            ) camp,
            (
             SELECT cuad.clie_oid_clie, zorg.cod_regi, zzon.cod_zona, zscc.cod_secc, terr.cod_terr
               FROM mae_clien_unida_admin cuad,
                    zon_terri_admin       ztad,
                    zon_secci             zscc,
                    zon_terri             terr,
                    zon_zona              zzon,
                    zon_regio             zorg
              WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
                --AND vnOidPeriodo BETWEEN cuad.perd_oid_peri_ini AND NVL(cuad.perd_oid_peri_fin,vnOidPeriodo) -- Tomar en cuenta si se aplica para reprocesos
                AND ztad.zscc_oid_secc = zscc.oid_secc
                AND ztad.terr_oid_terr = terr.oid_terr
                AND zscc.zzon_oid_zona = zzon.oid_zona
                AND zzon.zorg_oid_regi = zorg.oid_regi
                AND cuad.ind_acti = 1 -- Si hay reprocesos esto se desactiva
            ) uas,
            mae_clien clie,
            mae_clien_datos_adici clda,
            (
              SELECT hger.gere
                FROM zon_histo_geren hger
               WHERE LENGTH(hger.ua) = 9
                 AND hger.perd_oid_peri_hast IS NULL
                 AND vnOidPeriodo BETWEEN hger.perd_oid_peri_desd AND hger.perd_oid_peri_hast
               GROUP BY hger.gere
            ) lide
      WHERE pr.est_regi <> '9'
        AND pr.est_poli ='3'
        AND cc.fec_fact = TO_DATE(psFechaFact,'dd/MM/yyyy')
        AND cc.cod_poli = pr.poli_cod_poli
        AND cc.num_poli = pr.num_poli
        AND cc.cod_peri = camp.cod_peri
        AND pr.clie_oid_clie = uas.clie_oid_clie(+)
        AND pr.cod_clie      = clie.cod_clie
        AND clie.oid_clie    = clda.clie_oid_clie
        AND clie.cod_clie    = lide.gere(+)
        ORDER BY  pr.poli_cod_poli, pr.num_poli;

   TYPE tRecCancel IS RECORD
     (
         numeroPoliza              sgr_famse_poliz_regis.num_poli%TYPE,
         tipoDocumentoIdentidad    sgr_famse_poliz_regis.tip_docu_iden%TYPE,
         numeroDocumentoIdentidad  sgr_famse_poliz_regis.num_docu_iden%TYPE,
         codigoConsultora          sgr_famse_poliz_regis.cod_clie%TYPE,
         precioPoliza              ccc_histo_cargo_famil_segur.val_impo_carg%TYPE,
         codigoTerritorio          VARCHAR2(13),
         fechaInicioVigencia       ccc_histo_cargo_famil_segur.fec_inic_poli%TYPE,
         fechaFinVigencia          ccc_histo_cargo_famil_segur.fec_fina_poli%TYPE,
         fechaInicioCampana        cra_perio.fec_inic%TYPE,
         fechaFinCampana           cra_perio.fec_fina%TYPE,
         codigoCampana             seg_perio_corpo.cod_peri%TYPE,
         fechaFacturacion          ccc_histo_cargo_famil_segur.fec_fact%TYPE,
         indConsuNueva             NUMBER(1),
         indConsuLider             NUMBER(1),
         impDescuPoliz             ccc_histo_cargo_famil_segur.val_desc_poli%TYPE,
         impCargoAplic             ccc_histo_cargo_famil_segur.val_impo_carg%TYPE
     );

   TYPE tTabtRecCancel  IS TABLE OF tRecCancel ;
   interfazRecord tTabtRecCancel;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_hANDle            UTL_FILE.FILE_TYPE;
   lbAbrirUtlFile      BOOLEAN;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lsIndValidaTipoDoc  VARCHAR2(1);
   lnOidPeriodo        cra_perio.oid_peri%TYPE;

BEGIN

     SELECT TRIM(VAL_PARA)
      INTO lsIndValidaTipoDoc
      FROM BAS_PARAM_PAIS B
     WHERE B.COD_PAIS = psCodigoPais
       AND B.COD_SIST = psCodigosistema
       AND B.COD_PARA = '003'
       AND B.IND_ACTI = '1';

    lbAbrirUtlFile := TRUE;
    lnOidPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( psCodigoPeriodo );

    OPEN c_vigen(lsIndValidaTipoDoc,lnOidPeriodo);
    LOOP
       FETCH c_vigen BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

     IF lbAbrirUtlFile THEN
       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigoSistema, psCodigoInterfaz,
                                              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
       lbAbrirUtlFile := FALSE;
     END IF;

     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).numeroPoliza                              ||';'||
                       interfazRecord(x).tipoDocumentoIdentidad                                             ||';'||
                     interfazRecord(x).numeroDocumentoIdentidad                  ||';'||
                     interfazRecord(x).codigoConsultora                          ||';'||
                     interfazRecord(x).precioPoliza                              ||';'||
                     interfazRecord(x).codigoTerritorio                          ||';'||
                     to_char(interfazRecord(x).fechaInicioVigencia,'YYYYMMDD')   ||';'||
                     to_char(interfazRecord(x).fechaFinVigencia,'YYYYMMDD')      ||';'||
                     to_char(interfazRecord(x).fechaInicioCampana,'YYYYMMDD')    ||';'||
                     to_char(interfazRecord(x).fechaFinCampana,'YYYYMMDD')       ||';'||
                     interfazRecord(x).codigoCampana                             ||';'||
                     to_char(interfazRecord(x).fechaFacturacion,'YYYYMMDD')      ||';'||
                       interfazRecord(x).indConsuNueva                             ||';'||
                       interfazRecord(x).indConsuLider                             ||';'||
                       interfazRecord(x).impDescuPoliz                             ||';'||
                       interfazRecord(x).impCargoAplic                             ||';'||
                       ''                                                          ||';'||
                       ''                                                          ;

          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;

       EXIT WHEN c_vigen%NOTFOUND;
    END LOOP;
    CLOSE c_vigen;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SGR_ENVIO_POLIZ_VIGEN: '||ls_sqlerrm);
END INT_PR_SGR_ENVIO_POLIZ_VIGEN;



/****************************************************************************
Descripcion       : Interfaz que envía log de errores de carga (SGR-4)
Fecha Creacion    : 24/05/2011
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 pdFechaFact      : Fecha Facturación
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo

Autor: Dany Romero
*****************************************************************************/
PROCEDURE INT_PR_SGR_ENVIO_LOG_ERROR(  psCodigoPais       VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaRegistro    VARCHAR2,
                                       psCodigoSistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2)
IS
   CURSOR c_error IS

     SELECT pe.tier_cod_tipo_erro codigoTipoError,
            te.des_tipo_erro descripcionError,
            pe.num_poli numeroPoliza,
            pe.tip_docu tipoDocumentoIdentidad,
            pe.num_docu numeroDocumentoIdentidad,
            pe.cod_clie codigoConsultora
       FROM sgr_famse_poliz_error pe,
            sgr_famse_tipo_error te
      WHERE trunc(pe.fec_proc) = TO_DATE(psFechaRegistro,'dd/MM/yyyy') AND
            te.cod_tipo_erro = pe.tier_cod_tipo_erro(+);

   TYPE tRecError IS RECORD
     (
         codigoTipoError             sgr_famse_poliz_error.tier_cod_tipo_erro%TYPE,
         descripcionError            sgr_famse_tipo_error.des_tipo_erro%TYPE,
         numeroPoliza                sgr_famse_poliz_error.num_poli%TYPE,
         tipoDocumentoIdentidad      sgr_famse_poliz_error.tip_docu%TYPE,
         numeroDocumentoIdentidad    sgr_famse_poliz_error.num_docu%TYPE,
         codigoConsultora            sgr_famse_poliz_error.cod_clie%TYPE
     );

   TYPE tTabtRecError  IS TABLE OF tRecError ;
   interfazRecord tTabtRecError;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   --W_FILAS             NUMBER := 1000 ;
   v_hANDle            UTL_FILE.FILE_TYPE;
   lbAbrirUtlFile      BOOLEAN;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN

    lbAbrirUtlFile := TRUE;

    OPEN c_error;
    LOOP
       FETCH c_error BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

    IF lbAbrirUtlFile THEN
     GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigoSistema, psCodigoInterfaz,
                                            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
     lbAbrirUtlFile := FALSE;
    END IF;

    IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

        lsLinea := interfazRecord(x).codigoTipoError               ||';'||
                   interfazRecord(x).descripcionError              ||';'||
                   interfazRecord(x).numeroPoliza                  ||';'||
                   interfazRecord(x).tipoDocumentoIdentidad        ||';'||
                   interfazRecord(x).numeroDocumentoIdentidad      ||';'||
                   interfazRecord(x).codigoConsultora              ||';'||
                   ''                                              ||';'||
                   '';

        UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;

       EXIT WHEN c_error%NOTFOUND;
    END LOOP;
    CLOSE c_error;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SGR_ENVIO_LOG_ERROR: '||ls_sqlerrm);
END INT_PR_SGR_ENVIO_LOG_ERROR;



/***********************************************************************************************
Descripcion       : Proceso que se encarga de hacer las validaciones del archivo de recepcion
                    y grabar en el tabla de error (SGR-5)
Fecha Creacion    : 25/05/2011
Parametros:
    psNumeroPoliza         Numero Poliza
    psTipoDocumento        Tipo Documento
    psNumeroDocumento      Numero documento
    psCodigoCliente        Codigo Cliente
    psCodigoTerritorio     Codigo territorio
    psFechaRegistro        Fecha Registro
    psCampanhaCancel       Periodo
    psCodigoMotivoCancel   Motivp rechazo
    psUsuario              Usuario
    psCodigoError          OUT VARCHAR2,
    psMensajeError         OUT VARCHAR2

Autor: Sergio Buchelli
************************************************************************************************/
PROCEDURE INT_PR_SGR_RECEP_POLIZ_CANCE(psNumeroPoliza         VARCHAR2,
                                       psTipoDocumento        VARCHAR2,
                                       psNumeroDocumento      VARCHAR2,
                                       psCodigoCliente        VARCHAR2,
                                       psCodigoTerritorio     VARCHAR2,
                                       psFechaRegistro        VARCHAR2,
                                       psCampanhaCancel       VARCHAR2,
                                       psCodigoMotivoCancel   VARCHAR2,
                                       psUsuario              VARCHAR2,
                                       psCodigoError          OUT VARCHAR2,
                                       psMensajeError         OUT VARCHAR2)
  IS
   lnCont         NUMBER;
   datosProceso   BAS_CTRL_FACT%ROWTYPE;
   registroPoliza sgr_FAMSE_POLIZ_REGIS%ROWTYPE;


BEGIN
    psCodigoError:='-1';
    --a.Verifica que el número de póliza exista en la entidad Registro de Pólizas.
    SELECT COUNT(1) INTO lncont
    FROM sgr_famse_poliz_regis pr, sgr_famse_poliz p
    WHERE pr.poli_cod_poli = p.cod_poli
          AND p.est_regi = '1'
          AND p.ind_acti = 1
          AND pr.est_regi='1'
          AND pr.num_poli = psNumeroPoliza;

    select b.* into datosProceso
    from bas_ctrl_fact b
    where b.sta_camp=0 and b.ind_camp_act=1 and rownum=1;

    IF(lnCont=0)THEN
      psCodigoError:='0';

      SELECT DES_TIPO_ERRO  INTO psMensajeError
      FROM sgr_FAMSE_TIPO_ERROR
      WHERE COD_TIPO_ERRO ='01';

      --INSERTA EN TABLA DE ERRORS
        INSERT INTO sgr_FAMSE_POLIZ_ERROR (
                COR_POLI_ERRO,
                TIER_COD_TIPO_ERRO,
                COD_CLIE,
                NUM_POLI,
                TIP_DOCU,
                NUM_DOCU,
                FEC_REGI,
                CAM_PROC,
                USU_MODI,
                FEC_MODI,
                EST_REGI,
                FEC_PROC)
        VALUES ( sgr_SEQ_POER.nextval,
                 '01',
                 psCodigoCliente,
                 psNumeroPoliza,
                 psTipoDocumento,
                 psNumeroDocumento,
                 TO_DATE(psFechaRegistro,'YYYYMMDD'),
                 datosProceso.Cod_Peri,
                 psUsuario,
                 SYSDATE,
                 '1',
                 datosProceso.Fec_Proc);

      RETURN;
    END IF;

    --b. Verifica que el código de consultora exista en la entidad Registro de Pólizas.
    SELECT COUNT(1) INTO lnCont
    FROM sgr_famse_poliz_regis pr, sgr_famse_poliz p
    WHERE pr.poli_cod_poli = p.cod_poli
          AND p.est_regi = '1'
          AND p.ind_acti = 1
          AND pr.est_regi='1'
          AND pr.COD_CLIE =psCodigoCliente;

    IF(lnCont=0)THEN
      psCodigoError:='1';
      psMensajeError:='';
      
        --INSERTA EN TABLA DE ERRORS
        INSERT INTO sgr_FAMSE_POLIZ_ERROR (
                COR_POLI_ERRO,
                TIER_COD_TIPO_ERRO,
                COD_CLIE,
                NUM_POLI,
                TIP_DOCU,
                NUM_DOCU,
                FEC_REGI,
                CAM_PROC,
                USU_MODI,
                FEC_MODI,
                EST_REGI,
                FEC_PROC)
        VALUES ( sgr_SEQ_POER.nextval,
                 '04',
                 psCodigoCliente,
                 psNumeroPoliza,
                 psTipoDocumento,
                 psNumeroDocumento,
                 TO_DATE(psFechaRegistro,'YYYYMMDD'),
                 datosProceso.Cod_Peri,
                 psUsuario,
                 SYSDATE,
                 '1',
                 datosProceso.Fec_Proc);
      
      RETURN;
    END IF;


    --c. Verifica que el código de consultora exista en la entidad Maestro de Clientes.
    SELECT COUNT(1) INTO lnCont
    FROM MAE_CLIEN
    WHERE COD_CLIE =psCodigoCliente;

    IF(lnCont=0)THEN
      psCodigoError:='2';
      psMensajeError:='';
      RETURN;
    END IF;

    /*
    --d. Verifica que la campaña de cancelación exista en la entidad Campañas.
    SELECT COUNT(1) INTO lnCont
    FROM SEG_PERIO_CORPO
    WHERE COD_PERI =psCampanhaCancel;

    IF(lnCont=0)THEN
      psCodigoError:='3';
      psMensajeError:='';
      RETURN;
    END IF;
    */

    SELECT pr.* INTO registroPoliza
    FROM sgr_famse_poliz_regis pr, sgr_famse_poliz p
    WHERE pr.poli_cod_poli = p.cod_poli
          AND p.est_regi = '1'
          AND p.ind_acti = 1
          AND pr.est_regi='1'
          AND pr.num_poli = psNumeroPoliza
          AND pr.cod_clie =  psCodigoCliente;


   IF(registroPoliza.EST_POLI = '3'
      OR registroPoliza.EST_POLI = '1') THEN

      UPDATE sgr_FAMSE_POLIZ_REGIS X
      SET EST_POLI = '4',
          --FEC_CANC = TO_DATE(psFechaRegistro,'ddMMyyyy'),
          FEC_CANC = datosProceso.Fec_Proc,
          CAM_CANC = datosProceso.Cod_Peri,
          MOTI_COD_MOTI_CANC = psCodigoMotivoCancel,
          USU_MODI = psUsuario,
          FEC_MODI = SYSDATE
      WHERE POLI_COD_POLI=  registroPoliza.POLI_COD_POLI
       AND COD_CLIE=  registroPoliza.COD_CLIE
       AND NUM_POLI=  registroPoliza.NUM_POLI;

      RETURN;
   END IF;

   IF(registroPoliza.EST_POLI ='4') THEN

        INSERT INTO sgr_FAMSE_POLIZ_ERROR (
                COR_POLI_ERRO,
                TIER_COD_TIPO_ERRO,
                COD_CLIE,
                NUM_POLI,
                TIP_DOCU,
                NUM_DOCU,
                FEC_REGI,
                CAM_PROC,
                USU_MODI,
                FEC_MODI,
                EST_REGI,
                FEC_PROC)
        VALUES ( SGR_SEQ_POER.nextval,
                 '02',
                 psCodigoCliente,
                 psNumeroPoliza,
                 psTipoDocumento,
                 psNumeroDocumento,
                 TO_DATE(psFechaRegistro,'YYYYMMDD'),
                 datosProceso.Cod_Peri,
                 psUsuario,
                 SYSDATE,
                 '1',
                 datosProceso.Fec_Proc);

   ELSE
        INSERT INTO sgr_FAMSE_POLIZ_ERROR (
                COR_POLI_ERRO,
                TIER_COD_TIPO_ERRO,
                COD_CLIE,
                NUM_POLI,
                TIP_DOCU,
                NUM_DOCU,
                FEC_REGI,
                CAM_PROC,
                USU_MODI,
                FEC_MODI,
                EST_REGI,
                FEC_PROC)
        VALUES ( sgr_SEQ_POER.nextval,
                 '03',
                 psCodigoCliente,
                 psNumeroPoliza,
                 psTipoDocumento,
                 psNumeroDocumento,
                 TO_DATE(psFechaRegistro,'YYYYMMDD'),
                 datosProceso.Cod_Peri,
                 psUsuario,
                 SYSDATE,
                 '1',
                 datosProceso.Fec_Proc);

   END IF;


EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_SGR_RECEP_POLIZ_CANCE: '||ls_sqlerrm);
END INT_PR_SGR_RECEP_POLIZ_CANCE;



END INT_PKG_SEGUR;
/
