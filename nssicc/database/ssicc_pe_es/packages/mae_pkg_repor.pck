create or replace package MAE_PKG_REPOR is

   W_FILAS                    NUMBER := 5000;
   ln_sqlcode                 NUMBER(10);
   ls_sqlerrm                 VARCHAR2(1000);

/**************************************************************************
     Descripcion       : Generar reporte de MAE - Consultoras Bloqueadas y Desbloquedas
     Autor             : Nicolas Lopez
     Fecha Creacion    : 20/10/2011
     Parametros Entrada :
            psCodigoPais           Pais
            psOidSecuencia         Oid Secuencia
***************************************************************************/
 PROCEDURE MAE_PR_GENER_REPOR_CONSU_BLDES(
   psCodigoPais           VARCHAR2,
   psOidSecuencia         VARCHAR2
 );

/**************************************************************************
     Descripcion       : Generar reportes requeridos para la fuerza de ventas
                         a los cierres de cada campa?ia
     Autor             : Jorge Velasquez
     Fecha Creacion    : 05/12/2012
     Parametros Entrada :
            pscodigopais           Pais
            pscodigoperiodo        codigo de Periodo
            psusuario              usuario logeado
***************************************************************************/
 PROCEDURE MAE_PR_GENER_REPOR_CONSU_PEDID(
   pscodigopais           VARCHAR2,
   pscodigoperiodo        VARCHAR2,
   pscondicionRegion      VARCHAR2,
   pscondicionZona        VARCHAR2,
   psusuario              VARCHAR2,
   pstiporeporte          VARCHAR2,
   psfechaInicio          VARCHAR2,
   psfechaFin             VARCHAR2,
   pscondicionEstado      VARCHAR2,
   pscondicionSaldo       VARCHAR2,
   pscondicionPedido      VARCHAR2,
   pscondicionClienteDocu      VARCHAR2,
   pscondicionDocuPrincipal    VARCHAR2
 );

  /*********************************************************************************
  Descripcion       : Inserta resultados de consulta nuevas o rechazadas en temporal
  Fecha Creacion    : 27/05/2013
  Autor             : Guerra Chacaltana Luis Sebastian
  Parametros        :
    oidpais                oid de país
    oidperiodo             oid de periodo
    oidactividad           oid de actividad
    condicionfechainicio   condición fecha de inicio de creación
    condicionfechafin      condición fecha de termino de creación
    condicionregion1       condición de región
    condicionzona1         condición de zona
    condicionfechainicio2  condición fecha de inicio de modificación
    condicionfechafin2     condición fecha de termino de modificación
    condicionregion2       condición de segunda región
    condicionzona2         condición de segunda zona
    cadena01               cadena que contiene primera parte de sentencia sql
    cadena02               cadena que contiene segunda parte de sentencia sql
    cadena03               cadena que contiene tercera parte de sentencia sql
  **********************************************************************************/
  PROCEDURE mae_pr_gener_tempo_nueva_csv
  (
    psoidpais                VARCHAR2,
    psoidperiodo             NUMBER,
    psoidactividad           VARCHAR2,
    pscondicionfechainicio   VARCHAR2,
    pscondicionfechafin      VARCHAR2,
    pscondicionregion1       VARCHAR2,
    pscondicionzona1         VARCHAR2,
    pscondicionfechainicio2  VARCHAR2,
    pscondicionfechafin2     VARCHAR2,
    pscondicionregion2       VARCHAR2,
    pscondicionzona2         VARCHAR2,
    pstiporeporte            VARCHAR2,
    psIndicadorDesbloqueo    VARCHAR2,
    psoidproceso             OUT NUMBER
  );

  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de Nuevas
  Fecha Creacion    : 28/05/2013
  Autor             : Sebastian Guerra
  Parametros        :
    pscodigopais       codigo de pais
    pscodigousuario    codigo de usuario
    psnombrearchivo    nombre de archivo
    pstitulo           titulo del archivo
    psdirectorio       directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mae_pr_gener_repor_nueva_csv
  (
    pscodigopais        VARCHAR2,
    pscodigousuario     VARCHAR2,
    psoidproceso        NUMBER,
    psnombrearchivo     VARCHAR2,
    pstiporeporte       VARCHAR2,
    pstitulo            VARCHAR2,
    psdirectorio        OUT VARCHAR2
  );

/***************************************************************************
Descripcion       : Devuelve Gerente de la Zona o Region. Si encaso
                    develve null, nos retorna un correo.
Fecha Creacion    : 22/08/2013
Autor             : Yahir Rivas Luna
***************************************************************************/
FUNCTION MAE_FN_DEVUE_RESPO_CORRE
(psCodRegion      VARCHAR2,
 psCodZona        VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion          : Devuelve Gerente de la Zona. Si encaso develve null no envia el correo.
Fecha Creacion   : 13/08/2014
Autor                  : Sebastian Guerra
***************************************************************************/
FUNCTION MAE_FN_DEVUE_RESPO_CORRE_ZONA
(psCodZona        VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Devuelve los bloqueo activos por cliente
Fecha Creacion    : 11/09/2013
Autor             : JPJC
***************************************************************************/
FUNCTION MAE_FN_DEVUE_BLOQ_X_CLIE
(p_clie_oid_clie NUMBER
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Devuelve cantidad de bloqueo activos por cliente
Fecha Creacion    : 11/09/2013
Autor             : JPJC
***************************************************************************/
FUNCTION MAE_FN_DEVUE_CANT_BLOQ_X_CLIE
(
p_clie_oid_clie NUMBER
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Genera el Reporte MAE Consejeras Bloqueadas Desbloqueadas
Fecha Creacion    : 21/01/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE MAE_PR_REPOR_CONSE_BLOQU_DESBL(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Genera el Reporte MAE Vinculos Clientes en CSV
Fecha Creacion    : 25/02/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE MAE_PR_GENER_REPOR_VINCU_CLIEN(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Devuelve la clasificación de la consultora.
Fecha Creacion    : 10/04/2014
Autor             : Aurelio Oviedo
***************************************************************************/
FUNCTION MAE_FN_DEVUE_CLASI_CLIEN
(psCodigoPais      VARCHAR2,
 psOidCliente        VARCHAR2
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Genera el Reporte Clasificaciones por Cliente.
Fecha Creacion    : 22/04/2014
Autor             : Yahir Rivas
***************************************************************************/
PROCEDURE MAE_PR_REPOR_CLASI_X_CLIEN_CSV(
    psOidTipoClasi   VARCHAR2,
    psOidClasi       VARCHAR2,
    psOidTipoClie    VARCHAR2,
    psOidSubtClie    VARCHAR2,
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
);

/***************************************************************************
Descripcion       : Devuelve las observaciones bloqueo activos por cliente
Fecha Creacion    : 04/11/2015
Autor             : Gonzalo Huertas
***************************************************************************/
FUNCTION MAE_FN_DEVUE_OBS_BLOQ_X_CLIE
(psOidCliente NUMBER
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Devuelve las observaciones bloqueo y sus descripciones x cliente
Fecha Creacion    : 25/11/2015
Autor             : Karina Valencia
***************************************************************************/
FUNCTION MAE_FN_DEVUE_OBS_MOTI_BLOQ
(psOidCliente NUMBER
)
RETURN VARCHAR2;

end MAE_PKG_REPOR;
/
CREATE OR REPLACE PACKAGE BODY MAE_PKG_REPOR is

/**************************************************************************
     Descripcion       : Generar reporte de MAE - Consultoras Bloqueadas y Desbloquedas
     Autor             : Nicolas Lopez
     Fecha Creacion    : 20/10/2011
     Parametros Entrada :
            psCodigoPais           Pais
            psOidSecuencia         Oid Secuencia
***************************************************************************/
 PROCEDURE MAE_PR_GENER_REPOR_CONSU_BLDES(
   psCodigoPais           VARCHAR2,
   psOidSecuencia         VARCHAR2
 )
 IS
  lnIdPais               NUMBER;

  lnOid_terr             zon_terri.oid_terr %TYPE;
  lnCod_terr             zon_terri.cod_terr %TYPE;
  lnOid_secc             zon_secci.oid_secc %TYPE;
  lnOid_zona             zon_zona.oid_zona %TYPE;
  lsCod_secc             zon_secci.cod_secc %TYPE;
  lnOid_regi             zon_regio.oid_regi %TYPE;
  lsCod_zona             zon_zona.cod_zona %TYPE;
  lsDes_regi             zon_regio.des_regi %TYPE;
  lsBloqueo              gen_i18n_sicc_comun.val_i18n %TYPE;
  lsCod_regi             zon_regio.cod_regi %TYPE;
  lsPeri_bloqueo         seg_perio_corpo.cod_peri %TYPE;
  lsPeri_Desbloq         seg_perio_corpo.cod_peri %TYPE;

  TYPE tmptablaConsultBloDes IS RECORD(
    cod_clie                      mae_clien.cod_clie %TYPE,
    nombre                        VARCHAR2(200),
    fec_ing                       VARCHAR2(10),
    estado_actual                 gen_i18n_sicc_comun.val_i18n %TYPE,
    sal_deud_ante                 mae_clien.sal_deud_ante %TYPE,
    obs_bloq                      mae_clien_bloqu.obs_bloq %TYPE,
    fec_bloq                      mae_clien_bloqu.fec_bloq %TYPE,
    fecha_bloqueo                 VARCHAR2(10),
    hora_bloqueo                  VARCHAR2(10),
    val_usua_bloq                 mae_clien_bloqu.val_usua_bloq %TYPE,
    obs_desb                      mae_clien_bloqu.obs_desb %TYPE,
    fec_desb                      mae_clien_bloqu.fec_desb %TYPE,
    fecha_desbloqueo              VARCHAR2(10),
    hora_desbloqueo               VARCHAR2(10),
    val_usua_desb                 mae_clien_bloqu.val_usua_desb %TYPE,
    oid_terr_admi                 zon_terri_admin.oid_terr_admi %TYPE,
    cod_esta_clie                 mae_estat_clien.cod_esta_clie %TYPE,
    oid_tipo_bloq                 mae_tipo_bloqu.oid_tipo_bloq %TYPE
  );

  TYPE tablaRegConsultBloDes IS TABLE OF tmptablaConsultBloDes;
  tablaRegConsultBloDesrecord tablaRegConsultBloDes;

  -- Se obtienen los datos
  CURSOR REPOR_CONSU_BLODES(lnOidPais NUMBER) IS
    SELECT m.cod_clie                                   cod_clie,
           TRIM(m.val_ape1) || ' ' || TRIM(m.val_ape2) || ', ' ||
           TRIM(m.val_nom1) || ' ' || TRIM(m.val_nom2)  nombre,
           TO_CHAR(m.fec_ingr,'dd/MM/yyyy')             fec_ing,
           g.val_i18n                                   estado_actual,
           m.sal_deud_ante                              sal_deud_ante,
           b.obs_bloq                                   obs_bloq,
           b.fec_bloq                                   fec_bloq,
           TO_CHAR(b.fec_bloq, 'dd/MM/YYYY')            fecha_bloqueo,
           TO_CHAR(b.fec_bloq, 'hh:mi:ss')              hora_bloqueo,
           b.val_usua_bloq                              usu_bloq,
           b.obs_desb                                   obs_desb,
           b.fec_desb                                   fec_desb,
           TO_CHAR(b.fec_desb, 'dd/MM/YYYY')            fecha_desbloqueo,
           TO_CHAR(b.fec_desb, 'hh:mi:ss')              hora_desbloqueo,
           b.val_usua_desb                              val_usu_desb,
           ua.ztad_oid_terr_admi                        oid_terr_admi,
           mest.cod_esta_clie                           cod_esta_clie,
           b.tibq_oid_tipo_bloq                         oid_tipo_bloq
      FROM mae_clien                m,
           mae_clien_bloqu          b,
           mae_clien_unida_admin    ua,
           mae_clien_datos_adici    mda,
           gen_i18n_sicc_comun      g,
           mae_estat_clien          mest
     WHERE m.oid_clie            = b.clie_oid_clie
       AND m.pais_oid_pais       = lnOidPais
       AND ua.clie_oid_clie      = m.oid_clie
       AND ua.ind_acti           = 1
       AND g.attr_enti           = 'MAE_ESTAT_CLIEN'
       AND g.val_oid             = mest.oid_esta_clie
       AND m.oid_clie            = mda.clie_oid_clie
       AND mda.esta_oid_esta_clie= g.val_oid;

 BEGIN
   /* obteniendo id's */
   lnIdPais    := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais); -- id del pais consultante

  /* Obtenemos los valores */

     OPEN REPOR_CONSU_BLODES(lnIdPais);
       LOOP
         FETCH REPOR_CONSU_BLODES BULK COLLECT INTO tablaRegConsultBloDesrecord LIMIT W_FILAS;

           IF tablaRegConsultBloDesrecord.COUNT > 0 THEN

             FOR x IN tablaRegConsultBloDesrecord.FIRST .. tablaRegConsultBloDesrecord.LAST LOOP

               -- Obteniendo Id's, codigos y descripciones
               BEGIN
                 SELECT zter.terr_oid_terr, zter.zscc_oid_secc INTO lnOid_terr, lnOid_secc
                   FROM zon_terri_admin zter
                  WHERE zter.OID_TERR_ADMI = tablaRegConsultBloDesrecord(x).oid_terr_admi;
               EXCEPTION WHEN NO_DATA_FOUND THEN
                  lnOid_terr:=NULL;
                  lnOid_secc:=NULL;
               END;

               BEGIN
                 SELECT zt.cod_terr INTO lnCod_terr
                   FROM zon_terri zt
                  WHERE zt.oid_terr = lnOid_terr;
               EXCEPTION WHEN NO_DATA_FOUND THEN
                     lnCod_terr:=NULL;
               END;

               BEGIN
                   SELECT zs.zzon_oid_zona, zs.cod_secc INTO lnOid_zona, lsCod_secc
                     FROM zon_secci zs
                    WHERE zs.oid_secc = lnOid_secc;
               EXCEPTION WHEN NO_DATA_FOUND THEN
                     lnOid_zona:=NULL;
                     lsCod_secc:=NULL;
               END;

               BEGIN
                   SELECT zz.zorg_oid_regi, zz.cod_zona INTO lnOid_regi, lsCod_zona
                     FROM zon_zona zz
                    WHERE zz.oid_zona = lnOid_zona;
               EXCEPTION WHEN NO_DATA_FOUND THEN
                     lnOid_regi:=NULL;
                     lsCod_zona:=NULL;
               END;

               BEGIN
                   SELECT zr.cod_regi, zr.des_regi INTO lsCod_regi, lsDes_regi
                     FROM zon_regio zr
                    WHERE zr.oid_regi = lnOid_regi;
               EXCEPTION WHEN NO_DATA_FOUND THEN
                     lsCod_regi:=NULL;
                     lsDes_regi:=NULL;
               END;

               BEGIN
                   SELECT gnc.val_i18n INTO lsBloqueo
                     FROM gen_i18n_sicc_comun gnc
                    WHERE gnc.attr_enti = 'MAE_TIPO_BLOQU'
                      AND gnc.val_oid   = tablaRegConsultBloDesrecord(x).oid_tipo_bloq;
               EXCEPTION WHEN NO_DATA_FOUND THEN
                    lsBloqueo:=NULL;
               END;

               BEGIN
                   SELECT sp.cod_peri INTO lsPeri_bloqueo
                     FROM cra_perio cp,
                          seg_perio_corpo sp
                    WHERE tablaRegConsultBloDesrecord(x).fec_bloq BETWEEN cp.fec_inic AND cp.fec_fina
                      AND sp.oid_peri = cp.peri_oid_peri
                      AND ROWNUM = 1;
               EXCEPTION WHEN NO_DATA_FOUND THEN
                      lsPeri_bloqueo:='';
               END;

               BEGIN
                   SELECT sp.cod_peri INTO lsPeri_Desbloq
                     FROM cra_perio       cp,
                          seg_perio_corpo sp
                    WHERE tablaRegConsultBloDesrecord(x).fec_desb BETWEEN cp.fec_inic AND cp.fec_fina
                      AND sp.oid_peri = cp.peri_oid_peri
                      AND ROWNUM = 1;
               EXCEPTION WHEN NO_DATA_FOUND THEN
                     lsPeri_Desbloq:='';
               END;

               -- Se procede a insertar los valores a la tabla de reporte MAE Bloqueadas y Desbloqueadas

             /*  INSERT INTO MAE_TEMPO_REPOR_BLDES(DES_REGI,         COD_ZONA,         COD_SECC,
                                                 COD_TERR,         COD_CLIE,         NOM_CLIE,
                                                 FEC_INGR,         SAL_DEUD_ANTE,    VAL_DESC_BLOQ,
                                                 OBS_BLOQ,         FEC_BLOQ,         HOR_BLOQ,
                                                 VAL_USUA_BLOQ,    OBS_DESB,         FEC_DESB,
                                                 HOR_DESB,         VAL_USUA_DESB,    COD_ESTA_CLIE,
                                                 COD_REGI,         VAL_PERI_BLOQ,    VAL_PERI_DBLO,
                                                 VAL_ESTA_ACTU,    VAL_FECH_BLOQ,    VAL_FECH_DBLQ,
                                                 OID_REPO_BLDE)
                         VALUES(   lsDes_regi,                                       lsCod_zona,                                     lsCod_secc,
                                   lnCod_terr,                                       tablaRegConsultBloDesrecord(x).cod_clie,        tablaRegConsultBloDesrecord(x).nombre,
                                tablaRegConsultBloDesrecord(x).fec_ing,              tablaRegConsultBloDesrecord(x).sal_deud_ante,   lsBloqueo,
                                tablaRegConsultBloDesrecord(x).obs_bloq,             tablaRegConsultBloDesrecord(x).fecha_bloqueo,   tablaRegConsultBloDesrecord(x).hora_bloqueo,
                                tablaRegConsultBloDesrecord(x).val_usua_bloq,        tablaRegConsultBloDesrecord(x).obs_desb,        tablaRegConsultBloDesrecord(x).fecha_desbloqueo,
                                tablaRegConsultBloDesrecord(x).hora_desbloqueo,      tablaRegConsultBloDesrecord(x).val_usua_desb,   tablaRegConsultBloDesrecord(x).cod_esta_clie,
                                lsCod_regi,                                          lsPeri_bloqueo,                                 lsPeri_Desbloq,
                                tablaRegConsultBloDesrecord(x).estado_actual,        tablaRegConsultBloDesrecord(x).fec_bloq,        tablaRegConsultBloDesrecord(x).fec_desb,
                                TO_NUMBER(psOidSecuencia));*/

             END LOOP;

           END IF;

         EXIT WHEN REPOR_CONSU_BLODES%NOTFOUND;
       END LOOP;
   CLOSE REPOR_CONSU_BLODES;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_GENER_REPOR_CONSU_BLDES: '||ls_sqlerrm);
 END MAE_PR_GENER_REPOR_CONSU_BLDES;

/**************************************************************************
     Descripcion       : Generar reportes requeridos para la fuerza de ventas
                         a los cierres de cada campa?ia
     Autor             : Karina Valencia
     Fecha Creacion    : 02/09/2015
     Parametros Entrada :
            pscodigopais           Pais
            pscodigoperiodo        codigo de Periodo
            psusuario              usuario logeado
***************************************************************************/
 PROCEDURE MAE_PR_GENER_REPOR_CONSU_PEDID(
   pscodigopais           VARCHAR2,
   pscodigoperiodo        VARCHAR2,
   pscondicionRegion      VARCHAR2,
   pscondicionZona        VARCHAR2,
   psusuario              VARCHAR2,
   pstiporeporte          VARCHAR2,
   psfechaInicio          VARCHAR2,
   psfechaFin             VARCHAR2,
   pscondicionEstado      VARCHAR2,
   pscondicionSaldo       VARCHAR2,
   pscondicionPedido      VARCHAR2,
   pscondicionClienteDocu      VARCHAR2,
   pscondicionDocuPrincipal    VARCHAR2
 )IS

    TYPE ingresopedidosRec IS RECORD (
      oid_clie                     mae_tempo_consu_pedid.oid_clie%type,
      cod_clie                     mae_tempo_consu_pedid.cod_clie%type,
      COD_DIGI_CTRL                mae_tempo_consu_pedid.COD_DIGI_CTRL%type,
      peri_prim_pedi_acti          mae_tempo_consu_pedid.peri_prim_pedi_acti%type,
      val_nomb_peri                mae_tempo_consu_pedid.val_nomb_peri%type,
      peri_prim_pedi               mae_tempo_consu_pedid.peri_prim_pedi%type,
      val_nomb_pedi                mae_tempo_consu_pedid.val_nomb_pedi%type,
      nom_clie                     mae_tempo_consu_pedid.nom_clie%type,
      num_docu_iden                mae_tempo_consu_pedid.num_docu_iden%type,
      cod_regi                     mae_tempo_consu_pedid.cod_regi%type,
      cod_zona                     mae_tempo_consu_pedid.cod_zona%type,
      cod_secc                     mae_tempo_consu_pedid.cod_secc%type,
      cod_terr                     mae_tempo_consu_pedid.cod_terr%type,
      esta_clie                    mae_tempo_consu_pedid.esta_clie%type,
      tipo_via                     mae_tempo_consu_pedid.tipo_via%type,
      val_nomb_via                 mae_tempo_consu_pedid.val_nomb_via%type,
      num_ppal                     mae_tempo_consu_pedid.num_ppal%type,
      val_obse                     mae_tempo_consu_pedid.val_obse%type,
      cod_unid_geog_dir            mae_tempo_consu_pedid.cod_unid_geog_dir%type,
      unid_geog_dir                mae_tempo_consu_pedid.unid_geog_dir%type,
      tel_casa                     mae_tempo_consu_pedid.tel_casa%type,
      tel_celu                     mae_tempo_consu_pedid.tel_celu%type,
      val_emai                     mae_tempo_consu_pedid.val_emai%type,
      fec_ulti_actu                mae_clien_comun.fec_ulti_actu%type,
      val_saldo_avenc              mae_tempo_consu_pedid.val_saldo_avenc%type,
      cod_usua                     mae_tempo_consu_pedid.cod_usua%type,
      cod_clie_vinc                mae_tempo_consu_pedid.cod_clie_vinc%type,
      nom_clie_vinc                mae_tempo_consu_pedid.nom_clie_vinc%type,
      cod_zona_clie_vinc           mae_tempo_consu_pedid.cod_zona_clie_vinc%type,
      cod_secc_clie_vinc           mae_tempo_consu_pedid.cod_secc_clie_vinc%type,
      des_bloq                     mae_tempo_consu_pedid.des_bloq%type,
      DES_OBSE_BLOQ                mae_tempo_consu_pedid.DES_OBSE_BLOQ%type,
      cam_regi                     mae_tempo_consu_pedid.cam_regi%type,
      fec_ingr_soli                mae_tempo_consu_pedid.fec_ingr_soli%type,
      usu_gest                     mae_tempo_consu_pedid.usu_gest%type,
      fec_gest                     mae_tempo_consu_pedid.fec_gest%type,
      cam_crea                     mae_tempo_consu_pedid.cam_crea%type,
      rem_prim_fact                mae_tempo_consu_pedid.rem_prim_fact%type,
      fec_fact_regi                mae_tempo_consu_pedid.fec_fact_regi%type,
      fec_prim_fact                mae_tempo_consu_pedid.fec_prim_fact%type,
      cod_clie_lider_recom                mae_tempo_consu_pedid.cod_clie_lider_recom%type,
      nom_clie_lider_recom                mae_tempo_consu_pedid.nom_clie_lider_recom%type,
      val_cod_post                         mae_tempo_consu_pedid.val_cod_post%type,

      DESC_NAC                         mae_tempo_consu_pedid.DESC_NAC%type,
      COD_SEXO                         mae_tempo_consu_pedid.COD_SEXO%type,
      val_ocr_tdoc                         mae_tempo_consu_pedid.val_ocr_tdoc%type,
      VAL_OCR_ESTA                         mae_tempo_consu_pedid.VAL_OCR_ESTA%type,
      DESC_TIPO_PERSONA                         mae_tempo_consu_pedid.DESC_TIPO_PERSONA%type,
      DESC_ORIGEN_INGRESO                         mae_tempo_consu_pedid.DESC_ORIGEN_INGRESO%type,
      VAL_NOM_BARR                         mae_tempo_consu_pedid.VAL_NOM_BARR%type,
      VAL_NOM_MANZ                         mae_tempo_consu_pedid.VAL_NOM_MANZ%type,
      VAL_ETA_CONJ                         mae_tempo_consu_pedid.VAL_ETA_CONJ%type,
      VAL_CAL_PRIN                         mae_tempo_consu_pedid.VAL_CAL_PRIN%type,
      VAL_CAL_SECU                         mae_tempo_consu_pedid.VAL_CAL_SECU%type,
      IND_HOJA_PEDI                        mae_tempo_consu_pedid.IND_HOJA_PEDI%type,
      DES_GERE                              mae_tempo_consu_pedid.DES_GERE%type
    );


     TYPE ingresopedidostab IS TABLE OF ingresopedidosRec;
    ingresopedidos ingresopedidostab;
    --psoidperiodo
    TYPE CUR_TYP  IS REF CURSOR;
    c_consultoras_primer_pedido CUR_TYP;
    c_consultoras_segundo_pedido CUR_TYP;
    c_consultoras_tercer_pedido CUR_TYP;
    c_consultoras_activas CUR_TYP;
    c_consultoras_inactivas CUR_TYP;

    v_query varchar2(16000) ;
    rows NATURAL := 1000;

    lnIdTipoSolicitudSOC ped_tipo_solic_pais.oid_tipo_soli_pais%type;

    lsoidperiodo seg_perio_corpo.oid_peri%type;

    lsoidperiodoMenosUno seg_perio_corpo.oid_peri%type;
    lsoidperiodoMenosDos seg_perio_corpo.oid_peri%type;

    lscodigoPeriodoMenosUno seg_perio_corpo.cod_peri%type;
    lscodigoPeriodoMenosDos seg_perio_corpo.cod_peri%type;

    lnOidPais NUMBER;
    lnOidMarca NUMBER;
    lnOidCanal NUMBER;

    BEGIN

    --Obtener OId de Periodo
    lsoidperiodo:= gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

    lscodigoPeriodoMenosUno := gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais, pscodigoperiodo, -1);
    lsoidperiodoMenosUno := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCodigoPeriodoMenosUno);

    lscodigoPeriodoMenosDos := gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais, pscodigoperiodo, -2);
    lsoidperiodoMenosDos := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lscodigoPeriodoMenosDos);

    lnOidPais := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
    lnOidMarca := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnOidCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

    SELECT tipo_solicitud_pais.oid_tipo_soli_pais
    INTO lnIdTipoSolicitudSOC
    FROM ped_tipo_solic_pais tipo_solicitud_pais ,ped_tipo_solic tipo_solicitud
    WHERE tipo_solicitud_pais.tsol_oid_tipo_soli = tipo_solicitud.oid_tipo_soli AND tipo_solicitud.cod_tipo_soli = 'SOC';

    --Se elimina la data para el continuo uso del reporte
    DELETE FROM MAE_TEMPO_CONSU_PEDID WHERE COD_USUA = psusuario;

        --pstipoReporte = 2 (REPORTE NUEVAS PRIMER PEDIDO Y REACTIVADAS)
        IF pstiporeporte = '2' THEN
            v_query := '
            SELECT
            TAB.oid_clie,
            TAB.cod_clie,
            NULL COD_DIGI_CTRL,
            TAB.peri_prim_pedi_acti,
            NULL val_nomb_peri,
            TAB.peri_prim_pedi,
            TAB.val_nomb_pedi,
            TAB.nom_clie,
            TAB.num_docu_iden,
            NULL cod_regi,
            TAB.cod_zona,
            TAB.cod_secc,
            TAB.cod_terr,
            TAB.esta_clie,
            TAB.tipo_via,
            TAB.val_nomb_via,
            TAB.num_ppal,
            TAB.val_obse,
            TAB.cod_unid_geog_dir,
            TAB.unid_geog_dir,
            TAB.tel_casa,
            TAB.tel_celu,
            TAB.val_emai,
            NULL fec_ulti_actu,
            TAB.val_saldo_avenc,
            TAB.cod_usua,
            TAB.cod_clie_vinc,
            GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(TAB.cod_clie_vinc, ''NOM_CLIE'') NOM_CLIE_VINC,
            GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(TAB.cod_clie_vinc, ''COD_ZONA'') COD_ZONA_CLIE_VINC,
            GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(TAB.cod_clie_vinc, ''COD_SECC'') COD_SECC_CLIE_VINC,
            TAB.DES_BLOQ,
            TAB.des_Bloqueo,
            NULL CAM_REGI,
            NULL fec_ingr_solic,
            NULL usu_gest,
            NULL fec_gest,
            NULL cam_crea,
            TAB.REM_PRIM_FACT,
            TAB.FEC_FACT_REGI,
            TAB.fec_prim_fact,
            TAB.cod_clie_lider_recom,
            GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(TAB.cod_clie_lider_recom, ''NOM_CLIE'') NOM_CLIE_LIDER_RECOM,
            TAB.val_cod_post,
            TAB.nacionalidad,
             TAB.sexo,
             TAB.tipo_documento_identidad,
             TAB.estado_civil,
             TAB.tipo_persona,
             TAB.origen_ingreso,
             TAB.nombre_barrio,
             TAB.manzana_letra,
             TAB.etapa_conjunto,
             TAB.calle_principal,
             TAB.calle_secundaria,
             NULL IND_HOJA_PEDI,
             NULL DES_GERE
            FROM(
                    SELECT cli.oid_clie
                          ,cli.cod_clie
                          ,NULL AS CAM_REGI
                          ,NULL peri_prim_pedi_acti
                          ,(
                            FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO
                            ( (
                              SELECT MIN(c.perd_oid_peri)
                                FROM ped_solic_cabec c
                               WHERE clie_oid_clie = cli.oid_clie
                                 AND c.tspa_oid_tipo_soli_pais =
                             (SELECT tsp.oid_tipo_soli_pais
                                FROM ped_tipo_solic_pais tsp,
                                     ped_tipo_solic      ts
                               WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                                 AND ts.cod_tipo_soli = ''SOC'')
                             )
                             )
                          ) AS PERI_PRIM_PEDI
                           ,NULL VAL_NOMB_PEDI
                           ,cli.VAL_NOM1 || '' '' || cli.VAL_NOM2 || '' '' || cli.VAL_APE1 || '' '' || cli.VAL_APE2 AS NOM_CLIE
                           ,ced.NUM_DOCU_IDEN AS NUM_DOCU_IDEN
                           ,zon.cod_zona
                           ,sec.cod_secc
                           ,zt.cod_terr
                           ,stat.VAL_I18N AS esta_clie
                           ,dtv.VAL_I18N AS tipo_via
                           ,cdi.VAL_NOMB_VIA
                           ,cdi.NUM_PPAL
                           ,cdi.VAL_OBSE
                           ,cdi.val_cod_post
                           ,( SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mda, SEG_NACIO NAC, V_GEN_I18N_SICC I18N
       WHERE mda.snon_oid_naci=nac.oid_naci
         AND NAC.OID_NACI = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''SEG_NACIO''
         AND MDA.CLIE_OID_CLIE = cli.oid_clie and rownum=1) AS nacionalidad,
   (decode(cli.cod_sexo, ''F'', ''Femenino'', ''Masculino'' )) as sexo,
   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu
   and mci.clie_oid_clie = cli.oid_clie and rownum=1) as tipo_documento_identidad,
   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI
    AND MCDA.CLIE_OID_CLIE= cli.oid_clie and rownum=1) as estado_civil,
    (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
         and TP.OID_TIPO_PERS = I18N.VAL_OID
         AND I18N.IDIO_OID_IDIO = 1
         AND I18N.ATTR_ENTI = ''MAE_TIPO_PERSO''
         and mcda.clie_oid_clie= cli.oid_clie and rownum=1) as tipo_persona,
     (SELECT  I18N.VAL_I18N
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''MAE_ORIG_INGRE''
	    and mcda.clie_oid_clie= cli.oid_clie and rownum=1) as origen_ingreso,
      (select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1) as nombre_barrio,
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1) as manzana_letra,
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  cli.oid_clie and rownum=1) as etapa_conjunto ,
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1) as calle_principal,
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1) as calle_secundaria
                           ,cdi.COD_UNID_GEOG AS COD_UNID_GEOG_DIR
                           ,( SELECT DES_GEOG
                                FROM ZON_VALOR_ESTRU_GEOPO eg
                               WHERE eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                                 AND eg.ORDE_2 IS NULL
                                 AND eg.ORDE_3 IS NULL
                             )  || '' / '' ||
                            ( SELECT DES_GEOG
                                FROM ZON_VALOR_ESTRU_GEOPO eg
                               WHERE eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                                 AND eg.ORDE_2 = SUBSTR(cdi.COD_UNID_GEOG,7,6)
                                 AND eg.ORDE_3 IS NULL
                            )  || '' / '' ||
                            (SELECT DES_GEOG
                               FROM ZON_VALOR_ESTRU_GEOPO eg
                              WHERE  eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                                AND eg.ORDE_2 = SUBSTR(cdi.COD_UNID_GEOG,7,6)
                                AND eg.ORDE_3 = SUBSTR(cdi.COD_UNID_GEOG,13,6)
                                AND eg.orde_4 IS NULL
                             ) AS UNID_GEOG_DIR
                            ,(SELECT VAL_TEXT_COMU
                                FROM MAE_CLIEN_COMUN f1
                               WHERE cli.oid_clie = f1.clie_oid_clie(+)
                                 AND f1.TICM_OID_TIPO_COMU =1
                              ) AS TEL_CASA
                            ,(SELECT VAL_TEXT_COMU
                                FROM MAE_CLIEN_COMUN f2
                               WHERE cli.oid_clie = f2.clie_oid_clie(+)
                                 AND f2.TICM_OID_TIPO_COMU =6
                              ) AS TEL_CELU
                            ,(SELECT VAL_TEXT_COMU
                                FROM MAE_CLIEN_COMUN f2
                               WHERE cli.oid_clie = f2.clie_oid_clie(+)
                                 AND f2.TICM_OID_TIPO_COMU =3
                              ) AS VAL_EMAI
                            ,cli.SAL_DEUD_ANTE VAL_SALDO_AVENC
                            ,'''||psusuario||''' COD_USUA
                            ,(
                                SELECT
                                C.COD_CLIE
                                          FROM  MAE_CLIEN A,
                                                MAE_CLIEN_VINCU  B,
                                                MAE_CLIEN C
                                          WHERE A.OID_CLIE = B.CLIE_OID_CLIE_VNDO
                                            AND B.CLIE_OID_CLIE_VNTE = C.OID_CLIE
                                            AND A.COD_CLIE = cli.cod_clie
                                            AND ROWNUM = 1
                            ) COD_CLIE_VINC
                            ,(
                                SELECT
                                C.COD_CLIE
                                          FROM  MAE_CLIEN A,
                                                MAE_CLIEN_VINCU  B,
                                                MAE_CLIEN C,
                                                MAE_TIPO_VINCU TV
                                          WHERE A.OID_CLIE = B.CLIE_OID_CLIE_VNDO
                                            AND B.CLIE_OID_CLIE_VNTE = C.OID_CLIE
                                            AND B.TIVC_OID_TIPO_VINC=TV.OID_TIPO_VINC
                                            AND A.COD_CLIE = cli.cod_clie AND TV.COD_TIPO_VINC=''08''
                                            AND ROWNUM = 1
                            ) COD_CLIE_LIDER_RECOM
                           ,NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_BLOQ_X_CLIE(cli.oid_clie)), '''') AS DES_BLOQ,
                           NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_OBS_BLOQ_X_CLIE(cli.oid_clie)), '''') des_Bloqueo,
                           TO_CHAR((SELECT MIN(FEC_INIC)
                  from cra_crono cr
                 where cr.perd_oid_peri = '||lsoidperiodo||'
                   and cr.cact_oid_acti =
                       (select oid_acti
                          from cra_activ
                         where cod_acti = ''FA''
                           and pais_oid_pais = '||lnOidPais||')
                   and cr.zzon_oid_zona IN
                       (SELECT OID_ZONA
                          FROM ZON_ZONA
                         WHERE ZORG_OID_REGI = zreg.oid_regi)), ''DD/MM/YYYY'') as fec_fact_regi,
               TO_CHAR((select min(soca.fec_fact)
                  from ped_solic_cabec     soca,
                       ped_solic_cabec     cons,
                       ped_tipo_solic_pais tspa,
                       ped_tipo_solic      tsol
                 where 1 = 1
                   and soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                   and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   and tsol.cod_tipo_soli = ''SOC''
                   and soca.clie_oid_clie = cli.oid_clie
                   and soca.perd_oid_peri = '||lsoidperiodo||'
                   and soca.grpr_oid_grup_proc = 5
                   and cons.esso_oid_esta_soli != 4), ''DD/MM/YYYY'') as fec_prim_fact,
               NVL((select abs((select min(soca.fec_fact)
                  from ped_solic_cabec     soca,
                       ped_solic_cabec     cons,
                       ped_tipo_solic_pais tspa,
                       ped_tipo_solic      tsol
                 where 1 = 1
                   and soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                   and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   and tsol.cod_tipo_soli = ''SOC''
                   and soca.clie_oid_clie = cli.oid_clie
                   and soca.perd_oid_peri = '||lsoidperiodo||'
                   and soca.grpr_oid_grup_proc = 5
                   and cons.esso_oid_esta_soli != 4) - (SELECT MIN(FEC_INIC)
                  from cra_crono cr
                 where cr.perd_oid_peri = '||lsoidperiodo||'
                   and cr.cact_oid_acti =
                       (select oid_acti
                          from cra_activ
                         where cod_acti = ''FA''
                           and pais_oid_pais = '||lnOidPais||')
                   and cr.zzon_oid_zona IN
                       (SELECT OID_ZONA
                          FROM ZON_ZONA
                         WHERE ZORG_OID_REGI = zreg.oid_regi)))
                  from dual),0) + 1 as REM_PRIM_FACT
                      FROM mae_clien cli
                          ,mae_clien_unida_admin ua
                          ,zon_terri_admin za
                          ,zon_secci sec
                          ,zon_zona zon
                          ,zon_regio zreg
                          ,zon_terri zt
                          ,mae_clien_ident ced
                          ,mae_clien_datos_adici cda
                          ,mae_clien_direc cdi
                          ,mae_clien_prime_conta cpc
                          ,seg_tipo_via
                          ,(SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN where ATTR_ENTI = ''SEG_TIPO_VIA'') dtv
                          ,(SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN where ATTR_ENTI = ''MAE_ESTAT_CLIEN'') stat
                          ,(
                            SELECT a.clie_oid_clie
                                     FROM ped_solic_cabec A
                                         ,ped_solic_cabec B
                                    WHERE B.perd_oid_peri           = A.perd_oid_peri
                                      AND A.soca_oid_soli_cabe      = B.OID_SOLI_CABE
                                      AND A.TSPA_OID_TIPO_SOLI_PAIS = '||lnIdTipoSolicitudSOC||'
                                      AND A.fec_fact                  IS NOT NULL
                                      AND B.ESSO_OID_ESTA_SOLI      <> 4
                                      AND A.perd_oid_peri           = '||lsoidperiodo||'
                           )ped1
                     WHERE cli.oid_clie              = ua.clie_oid_clie
                       AND ced.clie_oid_clie(+)      = cli.oid_clie
                       AND ua.ztad_oid_terr_admi     = za.oid_terr_admi
                       AND za.terr_oid_terr          = zt.oid_terr
                       AND za.zscc_oid_secc          = sec.oid_secc
                       AND sec.zzon_oid_zona         = zon.oid_zona
                       AND ua.ind_acti               = 1
                       AND zreg.oid_regi             = zon.zorg_oid_regi
                       AND cda.ind_acti              = 1
                       AND cli.oid_clie              = cda.clie_oid_clie
                       AND cda.esta_oid_esta_clie    = stat.val_oid
                       AND cli.oid_clie              = cdi.clie_oid_clie
                       AND cdi.ind_dire_ppal         = 1
                       AND cdi.ind_elim              = 0
                       AND cdi.tivi_oid_tipo_via     = seg_tipo_via.oid_tipo_via
                       AND seg_tipo_via.oid_tipo_via = dtv.val_oid
                       AND cli.oid_clie              = ped1.clie_oid_clie
                       AND cli.oid_clie              = cpc.clie_oid_clie
                       AND cpc.perd_oid_peri         =
                       '||lsoidperiodo||'
                    '||pscondicionRegion||'
                    '||pscondicionZona||'
                    '||pscondicionSaldo||'
                    ) TAB
                    order by 5, 3';
           --pstipoReporte = 2 (REPORTE NUEVAS PRIMER PEDIDO)
           OPEN c_consultoras_primer_pedido for v_query;
           LOOP
               FETCH c_consultoras_primer_pedido BULK COLLECT
                   INTO ingresopedidos LIMIT rows;

               FORALL i IN 1 .. ingresopedidos.count
                   INSERT INTO mae_tempo_consu_pedid(oid_clie, cod_clie, COD_DIGI_CTRL, peri_prim_pedi_acti,
                    val_nomb_peri, peri_prim_pedi, val_nomb_pedi,
                    nom_clie, num_docu_iden, cod_regi, cod_zona, cod_secc, cod_terr, esta_clie,tipo_via, val_nomb_via,
                    num_ppal, val_obse, cod_unid_geog_dir, tel_casa, tel_celu, val_emai, val_saldo_avenc, cod_usua,
                    cod_clie_vinc, nom_clie_vinc, cod_zona_clie_vinc, cod_secc_clie_vinc, des_bloq, cam_regi,fec_ingr_soli,
                    usu_gest, fec_gest, cam_crea, rem_prim_fact, fec_fact_regi, fec_prim_fact, cod_clie_lider_recom,
                    nom_clie_lider_recom, val_cod_post, DESC_NAC, COD_SEXO,  val_ocr_tdoc, VAL_OCR_ESTA, DESC_TIPO_PERSONA,
                    DESC_ORIGEN_INGRESO, VAL_NOM_BARR, VAL_NOM_MANZ, VAL_ETA_CONJ, VAL_CAL_PRIN, VAL_CAL_SECU, IND_HOJA_PEDI,
                    DES_GERE)
                    VALUES(ingresopedidos(i).oid_clie,
                    ingresopedidos(i).cod_clie,
                    ingresopedidos(i).COD_DIGI_CTRL,
                    ingresopedidos(i).peri_prim_pedi_acti,
                    ingresopedidos(i).val_nomb_peri,
                    ingresopedidos(i).peri_prim_pedi,
                    ingresopedidos(i).val_nomb_pedi,
                    ingresopedidos(i).nom_clie,
                    ingresopedidos(i).num_docu_iden,
                    ingresopedidos(i).cod_regi,
                    ingresopedidos(i).cod_zona,
                    ingresopedidos(i).cod_secc,
                    ingresopedidos(i).cod_terr,
                    ingresopedidos(i).esta_clie,
                    ingresopedidos(i).tipo_via,
                    ingresopedidos(i).val_nomb_via,
                    ingresopedidos(i).num_ppal,
                    ingresopedidos(i).val_obse,
                    ingresopedidos(i).cod_unid_geog_dir,
                    ingresopedidos(i).tel_casa,
                    ingresopedidos(i).tel_celu,
                    ingresopedidos(i).val_emai,
                    ingresopedidos(i).val_saldo_avenc,
                    ingresopedidos(i).cod_usua,
                    ingresopedidos(i).cod_clie_vinc,
                    ingresopedidos(i).nom_clie_vinc,
                    ingresopedidos(i).cod_zona_clie_vinc,
                    ingresopedidos(i).cod_secc_clie_vinc,
                    ingresopedidos(i).des_bloq,
                    ingresopedidos(i).cam_regi,
                    ingresopedidos(i).fec_ingr_soli,
                    ingresopedidos(i).usu_gest,
                    ingresopedidos(i).fec_gest,
                    ingresopedidos(i).cam_crea,
                    ingresopedidos(i).rem_prim_fact,
                    ingresopedidos(i).fec_fact_regi,
                    ingresopedidos(i).fec_prim_fact,
                    ingresopedidos(i).cod_clie_lider_recom,
                    ingresopedidos(i).nom_clie_lider_recom,
                    ingresopedidos(i).val_cod_post,
                    ingresopedidos(i).DESC_NAC,
                    ingresopedidos(i).COD_SEXO,
                    ingresopedidos(i).val_ocr_tdoc,
                    ingresopedidos(i).VAL_OCR_ESTA,
                    ingresopedidos(i).DESC_TIPO_PERSONA,
                    ingresopedidos(i).DESC_ORIGEN_INGRESO,
                    ingresopedidos(i).VAL_NOM_BARR,
                    ingresopedidos(i).VAL_NOM_MANZ,
                    ingresopedidos(i).VAL_ETA_CONJ,
                    ingresopedidos(i).VAL_CAL_PRIN,
                    ingresopedidos(i).VAL_CAL_SECU,
                    ingresopedidos(i).IND_HOJA_PEDI,
                    ingresopedidos(i).DES_GERE);

               EXIT WHEN c_consultoras_primer_pedido%NOTFOUND;
           END LOOP;
           CLOSE c_consultoras_primer_pedido;
        END IF;

        --pstipoReporte = 3 (REPORTE NUEVAS SEGUNDO PEDIDO)
        IF pstiporeporte = '3' THEN
            v_query:='SELECT cli.oid_clie
                          ,cli.cod_clie
                          ,NULL COD_DIGI_CTRL
                          ,NULL peri_prim_pedi_acti
                          , NULL val_nomb_peri
                          ,(
                            FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO
                            (
                              (SELECT MIN(c.perd_oid_peri)
                                FROM ped_solic_cabec c
                               WHERE clie_oid_clie = cli.oid_clie
                                 AND c.tspa_oid_tipo_soli_pais =
                             (SELECT tsp.oid_tipo_soli_pais
                                FROM ped_tipo_solic_pais tsp,
                                     ped_tipo_solic      ts
                               WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                                 AND ts.cod_tipo_soli = ''SOC'')
                            )
                            )
                          )AS Cmp1ped
                           ,NULL VAL_NOM_PERI
                           ,cli.VAL_NOM1 || '' '' || cli.VAL_NOM2 || '' '' || cli.VAL_APE1 || '' '' || cli.VAL_APE2 AS NOM_CLIE
                           ,ced.NUM_DOCU_IDEN AS NUM_DOCU_IDEN
                           ,NULL cod_regi
                           ,zon.cod_zona
                           ,NULL cod_secc
                           ,zt.cod_terr
                           ,stat.VAL_I18N AS ESTA_CLIE
                           ,dtv.VAL_I18N AS Tipo_via
                           ,cdi.VAL_NOMB_VIA
                           ,cdi.NUM_PPAL
                           ,cdi.VAL_OBSE
                           ,cdi.COD_UNID_GEOG AS COD_UNID_GEOG_DIR
                           ,( SELECT DES_GEOG
                                FROM ZON_VALOR_ESTRU_GEOPO eg
                               WHERE eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                                 AND eg.ORDE_2 IS NULL
                                 AND eg.ORDE_3 IS NULL
                             )  || '' / '' ||
                            ( SELECT DES_GEOG
                                FROM ZON_VALOR_ESTRU_GEOPO eg
                               WHERE eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                                 AND eg.ORDE_2 = SUBSTR(cdi.COD_UNID_GEOG,7,6)
                                 AND eg.ORDE_3 IS NULL
                            )  || '' / '' ||
                            (SELECT DES_GEOG
                               FROM ZON_VALOR_ESTRU_GEOPO eg
                              WHERE eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                                AND eg.ORDE_2 = SUBSTR(cdi.COD_UNID_GEOG,7,6)
                                AND eg.ORDE_3 = SUBSTR(cdi.COD_UNID_GEOG,13,6)
                                AND eg.orde_4 IS NULL
                             ) AS UNID_GEOG_DIR
                            ,(SELECT VAL_TEXT_COMU
                                FROM MAE_CLIEN_COMUN f1
                               WHERE cli.oid_clie = f1.clie_oid_clie(+)
                                 AND f1.TICM_OID_TIPO_COMU =1
                              ) AS TEL_CASA
                            ,(SELECT VAL_TEXT_COMU
                                FROM MAE_CLIEN_COMUN f2
                               WHERE cli.oid_clie = f2.clie_oid_clie(+)
                                 AND f2.TICM_OID_TIPO_COMU =6
                              ) AS TEL_CELU
                            ,(SELECT VAL_TEXT_COMU
                                FROM MAE_CLIEN_COMUN f2
                               WHERE cli.oid_clie = f2.clie_oid_clie(+)
                                 AND f2.TICM_OID_TIPO_COMU =3
                              ) AS VAL_EMAI,
                            NULL fec_ulti_actu,
                            cli.SAL_DEUD_ANTE VAL_SALDO_AVENC,
                            '''||psusuario||''' COD_USUA,
                            NULL COD_CLIE_VINC,
                            NULL NOM_CLIE_VINC,
                            NULL COD_ZONA_CLIE_VINC,
                            NULL COD_SECC_CLIE_VINC,
                           NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_BLOQ_X_CLIE(cli.oid_clie)), '''')  DES_BLOQ,
                           NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_OBS_BLOQ_X_CLIE(cli.oid_clie)), '''') des_Bloqueo,
                           NULL AS CAM_REGI,
                           NULL fec_ingr_solic,
                           NULL usu_gest,
                           NULL fec_gest,
                           NULL cam_crea,
                           NULL REM_PRIM_FACT,
                           NULL FEC_FACT_REGI,
                           NULL fec_prim_fact,
                           NULL cod_clie_lider_recom,
                           NULL nom_clie_lider_recom,
                           cdi.val_cod_post,
                           ( SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mda, SEG_NACIO NAC, V_GEN_I18N_SICC I18N
       WHERE mda.snon_oid_naci=nac.oid_naci
         AND NAC.OID_NACI = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''SEG_NACIO''
         AND MDA.CLIE_OID_CLIE = cli.oid_clie and rownum=1) as nacionalidad,
   (decode(cli.cod_sexo, ''F'', ''Femenino'', ''Masculino'' )) AS sexo,
   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu
   and mci.clie_oid_clie = cli.oid_clie and rownum=1) as tipo_documento_identidad,
   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI
    AND MCDA.CLIE_OID_CLIE= cli.oid_clie and rownum=1) AS estado_civil,
    (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
         and TP.OID_TIPO_PERS = I18N.VAL_OID
         AND I18N.IDIO_OID_IDIO = 1
         AND I18N.ATTR_ENTI = ''MAE_TIPO_PERSO''
         and mcda.clie_oid_clie= cli.oid_clie and rownum=1) AS tipo_persona,
     (SELECT  I18N.VAL_I18N
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''MAE_ORIG_INGRE''
	    and mcda.clie_oid_clie= cli.oid_clie and rownum=1) AS origen_ingreso,
      (select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1) AS nombre_barrio,
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1) as manzana_letra,
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  cli.oid_clie and rownum=1) as etapa_conjunto,
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1) as calle_principal,
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1)  as calle_secundaria,
        NULL IND_HOJA_PEDI,
        NULL DES_GERE
                     FROM mae_clien cli
                          ,mae_clien_unida_admin ua
                          ,zon_terri_admin za
                          ,zon_secci sec
                          ,zon_zona zon
                          ,zon_regio zreg
                          ,zon_terri zt
                          ,MAE_CLIEN_IDENT Ced
                          ,MAE_CLIEN_DATOS_ADICI cda
                          ,MAE_CLIEN_DIREC cdi
                          ,SEG_TIPO_VIA
                          ,(SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN where ATTR_ENTI = ''SEG_TIPO_VIA'') dtv
                          ,(SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN where ATTR_ENTI = ''MAE_ESTAT_CLIEN'') stat
                          ,(SELECT A.Clie_Oid_Clie
                                     FROM ped_solic_cabec A
                                         ,ped_solic_cabec B
                                    WHERE B.perd_oid_peri           = A.perd_oid_peri
                                      AND A.soca_oid_soli_cabe      = B.OID_SOLI_CABE
                                      AND A.TSPA_OID_TIPO_SOLI_PAIS = '||lnIdTipoSolicitudSOC||'
                                      AND A.fec_fact                  IS NOT NULL
                                      AND B.ESSO_OID_ESTA_SOLI      <> 4
                                      AND A.perd_oid_peri           = '||lsoidperiodo||'
                            ) ped2
                          ,(SELECT A.clie_oid_clie
                                       FROM ped_solic_cabec A
                                           ,ped_solic_cabec B
                                      WHERE A.perd_oid_peri           = ('||lsoidperiodoMenosUno||')
                                        AND B.perd_oid_peri           = A.perd_oid_peri
                                        AND A.soca_oid_soli_cabe      = B.OID_SOLI_CABE
                                        AND A.TSPA_OID_TIPO_SOLI_PAIS = '||lnIdTipoSolicitudSOC||'
                                        AND A.fec_fact                  IS NOT NULL
                                        AND B.ESSO_OID_ESTA_SOLI      <> 4
                            ) ped1
                     WHERE cli.oid_clie              = ua.clie_oid_clie
                       AND ced.clie_oid_clie(+)      = cli.oid_clie
                       AND ua.ztad_oid_terr_admi     = za.oid_terr_admi
                       AND za.terr_oid_terr          = zt.oid_terr
                       AND za.zscc_oid_secc          = sec.oid_secc
                       AND sec.zzon_oid_zona         = zon.oid_zona
                       AND zon.zorg_oid_regi         = zreg.oid_regi
                       AND ua.ind_acti               = 1
                       AND cli.oid_clie              = cda.clie_oid_clie
                       and cda.ESTA_OID_ESTA_CLIE    = stat.val_oid
                       AND cli.oid_clie              = cdi.clie_oid_clie
                       AND cda.ind_acti              = 1
                       and cdi.IND_DIRE_PPAL         = 1
                       and cdi.IND_ELIM              = 0
                       and cdi.TIVI_OID_TIPO_VIA     = SEG_TIPO_VIA.OID_TIPO_VIA
                       and SEG_TIPO_VIA.OID_TIPO_VIA = dtv.VAL_OID
                       AND cli.oid_clie = ped2.Clie_Oid_Clie
                       AND cli.oid_clie = ped1.clie_oid_clie
                       AND ( (  SELECT MIN(c.perd_oid_peri)
                                FROM ped_solic_cabec c
                               WHERE clie_oid_clie = cli.oid_clie
                                 AND c.tspa_oid_tipo_soli_pais =
                             (SELECT tsp.oid_tipo_soli_pais
                                FROM ped_tipo_solic_pais tsp,
                                     ped_tipo_solic      ts
                               WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                                 AND ts.cod_tipo_soli = ''SOC'')
                              ) = ('||lsoidperiodoMenosUno||')
                           )
                       '||pscondicionRegion||'
                       '||pscondicionZona||'
                       '||pscondicionSaldo||'
                    order by 5, 3';
           OPEN c_consultoras_segundo_pedido for v_query;
           --pstipoReporte = 3 (REPORTE NUEVAS SEGUNDO PEDIDO)
           LOOP
               FETCH c_consultoras_segundo_pedido BULK COLLECT
                   INTO ingresopedidos LIMIT rows;

               FORALL i IN 1 .. ingresopedidos.count
                   INSERT INTO mae_tempo_consu_pedid(oid_clie, cod_clie, COD_DIGI_CTRL, peri_prim_pedi_acti,
                    val_nomb_peri, peri_prim_pedi, val_nomb_pedi,
                    nom_clie, num_docu_iden, cod_regi, cod_zona, cod_secc, cod_terr, esta_clie,tipo_via, val_nomb_via,
                    num_ppal, val_obse, cod_unid_geog_dir, tel_casa, tel_celu, val_emai, val_saldo_avenc, cod_usua,
                    cod_clie_vinc, nom_clie_vinc, cod_zona_clie_vinc, cod_secc_clie_vinc, des_bloq, cam_regi,fec_ingr_soli,
                    usu_gest, fec_gest, cam_crea, rem_prim_fact, fec_fact_regi, fec_prim_fact, cod_clie_lider_recom,
                    nom_clie_lider_recom, val_cod_post, DESC_NAC, COD_SEXO,  val_ocr_tdoc, VAL_OCR_ESTA, DESC_TIPO_PERSONA,
                    DESC_ORIGEN_INGRESO, VAL_NOM_BARR, VAL_NOM_MANZ, VAL_ETA_CONJ, VAL_CAL_PRIN, VAL_CAL_SECU, IND_HOJA_PEDI,
                    DES_GERE)
                    VALUES(ingresopedidos(i).oid_clie,
                    ingresopedidos(i).cod_clie,
                    ingresopedidos(i).COD_DIGI_CTRL,
                    ingresopedidos(i).peri_prim_pedi_acti,
                    ingresopedidos(i).val_nomb_peri,
                    ingresopedidos(i).peri_prim_pedi,
                    ingresopedidos(i).val_nomb_pedi,
                    ingresopedidos(i).nom_clie,
                    ingresopedidos(i).num_docu_iden,
                    ingresopedidos(i).cod_regi,
                    ingresopedidos(i).cod_zona,
                    ingresopedidos(i).cod_secc,
                    ingresopedidos(i).cod_terr,
                    ingresopedidos(i).esta_clie,
                    ingresopedidos(i).tipo_via,
                    ingresopedidos(i).val_nomb_via,
                    ingresopedidos(i).num_ppal,
                    ingresopedidos(i).val_obse,
                    ingresopedidos(i).cod_unid_geog_dir,
                    ingresopedidos(i).tel_casa,
                    ingresopedidos(i).tel_celu,
                    ingresopedidos(i).val_emai,
                    ingresopedidos(i).val_saldo_avenc,
                    ingresopedidos(i).cod_usua,
                    ingresopedidos(i).cod_clie_vinc,
                    ingresopedidos(i).nom_clie_vinc,
                    ingresopedidos(i).cod_zona_clie_vinc,
                    ingresopedidos(i).cod_secc_clie_vinc,
                    ingresopedidos(i).des_bloq,
                    ingresopedidos(i).cam_regi,
                    ingresopedidos(i).fec_ingr_soli,
                    ingresopedidos(i).usu_gest,
                    ingresopedidos(i).fec_gest,
                    ingresopedidos(i).cam_crea,
                    ingresopedidos(i).rem_prim_fact,
                    ingresopedidos(i).fec_fact_regi,
                    ingresopedidos(i).fec_prim_fact,
                    ingresopedidos(i).cod_clie_lider_recom,
                    ingresopedidos(i).nom_clie_lider_recom,
                    ingresopedidos(i).val_cod_post,
                    ingresopedidos(i).DESC_NAC,
                    ingresopedidos(i).COD_SEXO,
                    ingresopedidos(i).val_ocr_tdoc,
                    ingresopedidos(i).VAL_OCR_ESTA,
                    ingresopedidos(i).DESC_TIPO_PERSONA,
                    ingresopedidos(i).DESC_ORIGEN_INGRESO,
                    ingresopedidos(i).VAL_NOM_BARR,
                    ingresopedidos(i).VAL_NOM_MANZ,
                    ingresopedidos(i).VAL_ETA_CONJ,
                    ingresopedidos(i).VAL_CAL_PRIN,
                    ingresopedidos(i).VAL_CAL_SECU,
                    ingresopedidos(i).IND_HOJA_PEDI,
                    ingresopedidos(i).DES_GERE);

               EXIT WHEN c_consultoras_segundo_pedido%NOTFOUND;
           END LOOP;
           CLOSE c_consultoras_segundo_pedido;
        END IF;

        --pstipoReporte = 4 (REPORTE NUEVAS TERCER PEDIDO)
         IF pstiporeporte = '4' THEN
             v_query:=' SELECT cli.oid_clie
                      ,cli.cod_clie
                      ,NULL COD_DIGI_CTRL
                      ,NULL peri_prim_pedi_acti
                      , NULL val_nomb_peri
                      ,(
                        FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO
                        (
                            (SELECT MIN(c.perd_oid_peri)
                        FROM ped_solic_cabec c
                       WHERE clie_oid_clie = cli.oid_clie
                         AND c.tspa_oid_tipo_soli_pais = (SELECT tsp.oid_tipo_soli_pais
                                                            FROM ped_tipo_solic_pais tsp,
                                                                 ped_tipo_solic      ts
                                                           WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                                                             AND ts.cod_tipo_soli = ''SOC'')
                        )
                        )
                                                           ) AS  peri_prim_pedi
                       ,NULL val_nom_peri
                       ,cli.VAL_NOM1 || '' '' || cli.VAL_NOM2 || '' '' || cli.VAL_APE1 || '' '' || cli.VAL_APE2 AS NOM_CLIE
                       ,ced.NUM_DOCU_IDEN AS NUM_DOCU_IDEN
                       ,NULL cod_regi
                       ,zon.cod_zona
                       ,NULL cod_secc
                       ,zt.cod_terr
                       ,stat.VAL_I18N AS ESTA_CLIE
                       ,dtv.VAL_I18N AS Tipo_via
                       ,cdi.VAL_NOMB_VIA
                       ,cdi.NUM_PPAL
                       ,cdi.VAL_OBSE
                       ,cdi.COD_UNID_GEOG AS COD_UNID_GEOG_DIR
                       ,( SELECT DES_GEOG
                            FROM ZON_VALOR_ESTRU_GEOPO eg
                           WHERE eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                             AND eg.ORDE_2 IS NULL
                             AND eg.ORDE_3 IS NULL
                         )  || '' / '' ||
                        ( SELECT DES_GEOG
                            FROM ZON_VALOR_ESTRU_GEOPO eg
                           WHERE eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                             AND eg.ORDE_2 = SUBSTR(cdi.COD_UNID_GEOG,7,6)
                             AND eg.ORDE_3 IS NULL
                        )  || '' / '' ||
                        (SELECT DES_GEOG
                           FROM ZON_VALOR_ESTRU_GEOPO eg
                          WHERE eg.ORDE_1 = SUBSTR(cdi.COD_UNID_GEOG,1,6)
                            AND eg.ORDE_2 = SUBSTR(cdi.COD_UNID_GEOG,7,6)
                            AND eg.ORDE_3 = SUBSTR(cdi.COD_UNID_GEOG,13,6)
                            AND eg.orde_3 IS NULL
                         ) AS UNID_GEOG_DIR
                        ,(SELECT VAL_TEXT_COMU
                            FROM MAE_CLIEN_COMUN f1
                           WHERE cli.oid_clie = f1.clie_oid_clie(+)
                             AND f1.TICM_OID_TIPO_COMU =1
                          ) AS TEL_CASA
                        ,(SELECT VAL_TEXT_COMU
                            FROM MAE_CLIEN_COMUN f2
                           WHERE cli.oid_clie = f2.clie_oid_clie(+)
                             AND f2.TICM_OID_TIPO_COMU =6
                          ) AS TEL_CELU
                        ,(SELECT VAL_TEXT_COMU
                            FROM MAE_CLIEN_COMUN f2
                           WHERE cli.oid_clie = f2.clie_oid_clie(+)
                             AND f2.TICM_OID_TIPO_COMU =3
                          ) AS VAL_EMAI,
                        NULL fec_ulti_actu,
                        cli.SAL_DEUD_ANTE VAL_SALDO_AVENC,
                        '''||psusuario||''' COD_USUA,
                        NULL COD_CLIE_VINC,
                        NULL NOM_CLIE_VINC,
                        NULL COD_ZONA_CLIE_VINC,
                        NULL COD_SECC_CLIE_VINC,
                        NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_BLOQ_X_CLIE(cli.oid_clie)), '''')  DES_BLOQ,
                        NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_OBS_BLOQ_X_CLIE(cli.oid_clie)), '''') des_Bloqueo,
                        NULL AS CAM_REGI,
                        NULL fec_ingr_solic,
                        NULL usu_gest,
                        NULL fec_gest,
                        NULL cam_crea,
                        NULL REM_PRIM_FACT,
                        NULL FEC_FACT_REGI,
                        NULL fec_prim_fact,
                        NULL cod_clie_lider_recom,
                        NULL nom_clie_lider_recom,
                        cdi.val_cod_post,
                        ( SELECT  I18N.VAL_I18N AS DESCRIPCION
        FROM mae_clien_datos_adici mda, SEG_NACIO NAC, V_GEN_I18N_SICC I18N
       WHERE mda.snon_oid_naci=nac.oid_naci
         AND NAC.OID_NACI = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''SEG_NACIO''
         AND MDA.CLIE_OID_CLIE = cli.oid_clie and rownum=1) as nacionalidad,
   (decode(cli.cod_sexo, ''F'', ''Femenino'', ''Masculino'' )) AS sexo,
   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu
   and mci.clie_oid_clie = cli.oid_clie and rownum=1) as tipo_documento_identidad,
   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI
    AND MCDA.CLIE_OID_CLIE= cli.oid_clie and rownum=1) AS estado_civil,
    (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
         and TP.OID_TIPO_PERS = I18N.VAL_OID
         AND I18N.IDIO_OID_IDIO = 1
         AND I18N.ATTR_ENTI = ''MAE_TIPO_PERSO''
         and mcda.clie_oid_clie= cli.oid_clie and rownum=1) AS tipo_persona,
     (SELECT  I18N.VAL_I18N
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''MAE_ORIG_INGRE''
	    and mcda.clie_oid_clie= cli.oid_clie and rownum=1) AS origen_ingreso,
      ( select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1) AS nombre_barrio,
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1) as manzana_letra,
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  cli.oid_clie and rownum=1) as etapa_conjunto,
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1) as calle_principal,
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1)  as calle_secundaria,
        NULL IND_HOJA_PEDI,
        NULL DES_GERE
                  FROM mae_clien                  cli
                      ,mae_clien_unida_admin      ua
                      ,zon_terri_admin            za
                      ,zon_secci                  sec
                      ,zon_zona                   zon
                      ,zon_regio                  zreg
                      ,zon_terri                  zt
                      ,MAE_CLIEN_IDENT            ced
                      ,MAE_CLIEN_DATOS_ADICI      cda
                      ,MAE_CLIEN_DIREC            cdi
                      ,SEG_TIPO_VIA
                      ,(SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN where ATTR_ENTI = ''SEG_TIPO_VIA'') dtv
                      ,(SELECT VAL_I18N, VAL_OID FROM GEN_I18N_SICC_COMUN where ATTR_ENTI = ''MAE_ESTAT_CLIEN'') stat
                      ,(SELECT A.Clie_Oid_Clie
                             FROM ped_solic_cabec A
                                 ,ped_solic_cabec B
                            WHERE B.perd_oid_peri           = A.perd_oid_peri
                              AND A.soca_oid_soli_cabe      = B.OID_SOLI_CABE
                              AND A.TSPA_OID_TIPO_SOLI_PAIS = '||lnIdTipoSolicitudSOC||'
                              AND A.fec_fact                  IS NOT NULL
                              AND B.ESSO_OID_ESTA_SOLI      <> 4
                              AND A.perd_oid_peri           = '||lsoidperiodo||'
                        )ped3
                       ,(SELECT A.clie_oid_clie
                               FROM ped_solic_cabec A
                                   ,ped_solic_cabec B
                              WHERE A.perd_oid_peri           = ('||lsoidperiodoMenosUno||')
                                AND B.perd_oid_peri           = A.perd_oid_peri
                                AND A.soca_oid_soli_cabe      = B.OID_SOLI_CABE
                                AND A.TSPA_OID_TIPO_SOLI_PAIS = '||lnIdTipoSolicitudSOC||'
                                AND A.fec_fact                  IS NOT NULL
                                AND B.ESSO_OID_ESTA_SOLI      <> 4
                        ) ped2
                        ,(SELECT A.clie_oid_clie
                               FROM ped_solic_cabec A
                                   ,ped_solic_cabec B
                              WHERE A.perd_oid_peri           = ('||lsoidperiodoMenosDos||')
                                AND B.perd_oid_peri           = A.perd_oid_peri
                                AND A.soca_oid_soli_cabe      = B.OID_SOLI_CABE
                                AND A.TSPA_OID_TIPO_SOLI_PAIS = '||lnIdTipoSolicitudSOC||'
                                AND A.fec_fact                  IS NOT NULL
                                AND B.ESSO_OID_ESTA_SOLI      <> 4
                        ) ped1
                 WHERE cli.oid_clie              = ua.clie_oid_clie
                   AND ced.clie_oid_clie(+)      = cli.oid_clie
                   AND ua.ztad_oid_terr_admi     = za.oid_terr_admi
                   AND za.terr_oid_terr          = zt.oid_terr
                   AND za.zscc_oid_secc          = sec.oid_secc
                   AND sec.zzon_oid_zona         = zon.oid_zona
                   AND zon.zorg_oid_regi         = zreg.oid_regi
                   AND ua.ind_acti               = 1
                   AND cli.oid_clie              = cda.clie_oid_clie
                   AND cda.ESTA_OID_ESTA_CLIE    = stat.val_oid
                   AND cda.ind_acti              = 1
                   AND cli.oid_clie              = cdi.clie_oid_clie
                   AND cdi.IND_DIRE_PPAL         = 1
                   AND cdi.IND_ELIM              = 0
                   AND cdi.TIVI_OID_TIPO_VIA     = SEG_TIPO_VIA.OID_TIPO_VIA
                   AND SEG_TIPO_VIA.OID_TIPO_VIA = dtv.VAL_OID
                   AND cli.oid_clie              = ped3.Clie_Oid_Clie
                   AND cli.oid_clie              = ped2.Clie_Oid_Clie
                   AND cli.oid_clie              = ped1.Clie_Oid_Clie
                   AND (  SELECT MIN(c.perd_oid_peri)
                            FROM ped_solic_cabec c
                           WHERE clie_oid_clie = cli.oid_clie
                             AND c.tspa_oid_tipo_soli_pais = (SELECT tsp.oid_tipo_soli_pais
                                                                FROM ped_tipo_solic_pais tsp,
                                                                     ped_tipo_solic      ts
                                                               WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                                                                 AND ts.cod_tipo_soli = ''SOC'')
                       ) = ('||lsoidperiodoMenosDos||')
            '||pscondicionRegion||'
            '||pscondicionZona||'
            '||pscondicionSaldo||'
            ORDER BY 5, 3';

            OPEN c_consultoras_tercer_pedido FOR v_query;
            --pstipoReporte = 4 (REPORTE NUEVAS TERCER PEDIDO)
            LOOP
                FETCH c_consultoras_tercer_pedido BULK COLLECT
                    INTO  ingresopedidos LIMIT rows;

                FORALL i IN 1 .. ingresopedidos.count
                    INSERT INTO mae_tempo_consu_pedid(oid_clie, cod_clie, COD_DIGI_CTRL, peri_prim_pedi_acti,
                    val_nomb_peri, peri_prim_pedi, val_nomb_pedi,
                    nom_clie, num_docu_iden, cod_regi, cod_zona, cod_secc, cod_terr, esta_clie,tipo_via, val_nomb_via,
                    num_ppal, val_obse, cod_unid_geog_dir, tel_casa, tel_celu, val_emai, val_saldo_avenc, cod_usua,
                    cod_clie_vinc, nom_clie_vinc, cod_zona_clie_vinc, cod_secc_clie_vinc, des_bloq, cam_regi,fec_ingr_soli,
                    usu_gest, fec_gest, cam_crea, rem_prim_fact, fec_fact_regi, fec_prim_fact, cod_clie_lider_recom,
                    nom_clie_lider_recom, val_cod_post, DESC_NAC, COD_SEXO,  val_ocr_tdoc, VAL_OCR_ESTA, DESC_TIPO_PERSONA,
                    DESC_ORIGEN_INGRESO, VAL_NOM_BARR, VAL_NOM_MANZ, VAL_ETA_CONJ, VAL_CAL_PRIN, VAL_CAL_SECU, IND_HOJA_PEDI,
                    DES_GERE)
                    VALUES(ingresopedidos(i).oid_clie,
                    ingresopedidos(i).cod_clie,
                    ingresopedidos(i).COD_DIGI_CTRL,
                    ingresopedidos(i).peri_prim_pedi_acti,
                    ingresopedidos(i).val_nomb_peri,
                    ingresopedidos(i).peri_prim_pedi,
                    ingresopedidos(i).val_nomb_pedi,
                    ingresopedidos(i).nom_clie,
                    ingresopedidos(i).num_docu_iden,
                    ingresopedidos(i).cod_regi,
                    ingresopedidos(i).cod_zona,
                    ingresopedidos(i).cod_secc,
                    ingresopedidos(i).cod_terr,
                    ingresopedidos(i).esta_clie,
                    ingresopedidos(i).tipo_via,
                    ingresopedidos(i).val_nomb_via,
                    ingresopedidos(i).num_ppal,
                    ingresopedidos(i).val_obse,
                    ingresopedidos(i).cod_unid_geog_dir,
                    ingresopedidos(i).tel_casa,
                    ingresopedidos(i).tel_celu,
                    ingresopedidos(i).val_emai,
                    ingresopedidos(i).val_saldo_avenc,
                    ingresopedidos(i).cod_usua,
                    ingresopedidos(i).cod_clie_vinc,
                    ingresopedidos(i).nom_clie_vinc,
                    ingresopedidos(i).cod_zona_clie_vinc,
                    ingresopedidos(i).cod_secc_clie_vinc,
                    ingresopedidos(i).des_bloq,
                    ingresopedidos(i).cam_regi,
                    ingresopedidos(i).fec_ingr_soli,
                    ingresopedidos(i).usu_gest,
                    ingresopedidos(i).fec_gest,
                    ingresopedidos(i).cam_crea,
                    ingresopedidos(i).rem_prim_fact,
                    ingresopedidos(i).fec_fact_regi,
                    ingresopedidos(i).fec_prim_fact,
                    ingresopedidos(i).cod_clie_lider_recom,
                    ingresopedidos(i).nom_clie_lider_recom,
                    ingresopedidos(i).val_cod_post,
                    ingresopedidos(i).DESC_NAC,
                    ingresopedidos(i).COD_SEXO,
                    ingresopedidos(i).val_ocr_tdoc,
                    ingresopedidos(i).VAL_OCR_ESTA,
                    ingresopedidos(i).DESC_TIPO_PERSONA,
                    ingresopedidos(i).DESC_ORIGEN_INGRESO,
                    ingresopedidos(i).VAL_NOM_BARR,
                    ingresopedidos(i).VAL_NOM_MANZ,
                    ingresopedidos(i).VAL_ETA_CONJ,
                    ingresopedidos(i).VAL_CAL_PRIN,
                    ingresopedidos(i).VAL_CAL_SECU,
                    ingresopedidos(i).IND_HOJA_PEDI,
                    ingresopedidos(i).DES_GERE);

                EXIT WHEN c_consultoras_tercer_pedido%NOTFOUND;
            END LOOP;
            CLOSE c_consultoras_tercer_pedido;
        END IF;

        --pstipoReporte = 5 (REPORTE CONSULTORAS ACTIVAS)
         IF pstiporeporte = '5' THEN
            v_query :='SELECT   NULL oid_clie,
                     cli.cod_clie,
                     cli.COD_DIGI_CTRL,
                     ped_1erped_cump.c1ped AS PERI_PRIM_PEDI_ACTI,
                     (select cp.val_nomb_peri from mae_clien_prime_conta mcpc, cra_perio cp
                     where  mcpc.perd_oid_peri=cp.oid_peri
                     and mcpc.clie_oid_clie = cli.oid_clie),
                     NULL peri_prim_pedi,
                     ped_1erped_cump.cmaxped AS VAL_NOMB_PEDI,
                      cli.val_nom1
                     || '' ''
                     || cli.val_nom2
                     || '' ''
                     || cli.val_ape1
                     || '' ''
                     || cli.val_ape2 AS NOM_CLIE,
                     ced.num_docu_iden AS NUM_DOCU_IDEN,
                     zreg.cod_regi,
                     zon.cod_zona,
                     sec.cod_secc,
                     zt.cod_terr,
                     stat.val_i18n AS ESTA_CLIE,
                     dtv.val_i18n AS tipo_via,
                     cdi.val_nomb_via,
                     cdi.num_ppal,
                     cdi.val_obse,
                     cdi.cod_unid_geog AS cod_unid_geog_dir,
                        (SELECT des_geog
                           FROM zon_valor_estru_geopo eg
                          WHERE eg.orde_1 = SUBSTR (cdi.cod_unid_geog, 1, 6)
                            AND eg.orde_2 IS NULL
                            AND eg.orde_3 IS NULL)
                     || '' / ''
                     || (SELECT des_geog
                           FROM zon_valor_estru_geopo eg
                          WHERE eg.orde_1 = SUBSTR (cdi.cod_unid_geog, 1, 6)
                            AND eg.orde_2 = SUBSTR (cdi.cod_unid_geog, 7, 6)
                            AND eg.orde_3 IS NULL)
                     || '' / ''
                     || (SELECT des_geog
                           FROM zon_valor_estru_geopo eg
                          WHERE eg.orde_1 = SUBSTR (cdi.cod_unid_geog, 1, 6)
                            AND eg.orde_2 = SUBSTR (cdi.cod_unid_geog, 7, 6)
                            AND eg.orde_3 = SUBSTR (cdi.cod_unid_geog, 13, 6)
                            AND ROWNUM = 1) AS unid_geog_dir,
                     (SELECT val_text_comu
                        FROM mae_clien_comun f1
                       WHERE cli.oid_clie = f1.clie_oid_clie(+)
                             AND f1.ticm_oid_tipo_comu = 1) AS TEL_CASA,
                     (SELECT val_text_comu
                        FROM mae_clien_comun f2
                       WHERE cli.oid_clie = f2.clie_oid_clie(+)
                             AND f2.ticm_oid_tipo_comu = 6) AS TEL_CELU,
                     (SELECT val_text_comu
                        FROM mae_clien_comun f2
                       WHERE cli.oid_clie = f2.clie_oid_clie(+)
                             AND f2.ticm_oid_tipo_comu = 3) AS VAL_EMAI,
                     (SELECT mcc.fec_ulti_actu 
                         FROM mae_clien_comun mcc
                          WHERE mcc.clie_oid_clie = oid_clie 
                          AND rownum=1) AS fec_ulti_actu,
                     cli.sal_deud_ante VAL_SALDO_AVENC,
                     ''' || psusuario ||''' COD_USUA,
                    NULL COD_CLIE_VINC,
                    NULL NOM_CLIE_VINC,
                    NULL COD_ZONA_CLIE_VINC,
                    NULL COD_SECC_CLIE_VINC,
                    NVL(MAE_PKG_REPOR.MAE_FN_DEVUE_BLOQ_X_CLIE(cli.oid_clie), '''') DES_BLOQ,
                      NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_OBS_MOTI_BLOQ(cli.oid_clie)), '''') des_Bloqueo,
                    ( SELECT nvl(pi.cod_peri,'''')
                       FROM cra_perio ci,
                            seg_perio_corpo pi ,
                            mae_clien mc
                      WHERE ci.peri_oid_peri = pi.oid_peri
                        AND mc.fec_ingr >= ci.fec_inic and mc.fec_ingr <= ci.fec_fina
                        AND mc.oid_clie = cli.oid_clie
                        AND mc.fec_ingr is not null
                       AND rownum = 1
                     ) AS CAM_REGI,
                     cli.fec_crea fec_ingr_soli,
                     cli.usu_modi usu_gest,
                     cli.fec_ulti_actu fec_gest,
                     (SELECT min(b.cod_peri)
                                FROM cra_perio a,
                                     seg_perio_corpo b
                               WHERE a.pais_oid_pais = '||lnOidPais||'
                                 AND a.marc_oid_marc = '||lnOidMarca||'
                                 AND a.cana_oid_cana = '||lnOidCanal||'
                                 AND a.fec_inic <= gen_pkg_gener.gen_fn_fecha_sin_hora(cli.fec_ingr)
                                 AND a.fec_fina >= gen_pkg_gener.gen_fn_fecha_sin_hora(cli.fec_ingr)
                                 AND b.oid_peri = a.peri_oid_peri
                      ) cam_crea,
                      NULL rem_prim_fact,
                      NULL fec_fact_regi,
                      NULL fec_prim_fact,
                      NULL cod_clie_lider_recom,
                      NULL nom_clie_lider_recom,
                      cdi.val_cod_post,
                      (SELECT  I18N.VAL_I18N AS DESCRIPCION
        FROM mae_clien_datos_adici mda, SEG_NACIO NAC, V_GEN_I18N_SICC I18N
       WHERE mda.snon_oid_naci=nac.oid_naci
         AND NAC.OID_NACI = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''SEG_NACIO''
         AND MDA.CLIE_OID_CLIE = cli.oid_clie and rownum=1) as nacionalidad,
   (decode(cli.cod_sexo, ''F'', ''Femenino'', ''Masculino'' )) AS sexo,
   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu
   and mci.clie_oid_clie = cli.oid_clie and rownum=1) as tipo_documento_identidad,
   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI
    AND MCDA.CLIE_OID_CLIE= cli.oid_clie and rownum=1) AS estado_civil,
    (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
         and TP.OID_TIPO_PERS = I18N.VAL_OID
         AND I18N.IDIO_OID_IDIO = 1
         AND I18N.ATTR_ENTI = ''MAE_TIPO_PERSO''
         and mcda.clie_oid_clie= cli.oid_clie and rownum=1) AS tipo_persona,
     (SELECT  I18N.VAL_I18N
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''MAE_ORIG_INGRE''
	    and mcda.clie_oid_clie= cli.oid_clie and rownum=1) AS origen_ingreso,
      ( select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1) AS nombre_barrio,
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1) as manzana_letra,
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  cli.oid_clie and rownum=1) as etapa_conjunto,
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1) as calle_principal,
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1)  as calle_secundaria,
       (case when nvl(cda.num_pedi_web,0)>=3 then ''Si'' else ''No'' end) as Sin_Hoja_Pedido,
       NULL DES_GERE
                FROM mae_clien cli,
                     mae_clien_datos_adici cda,
                     mae_clien_unida_admin ua,
                     zon_terri_admin za,
                     zon_secci sec,
                     zon_zona zon,
                     zon_regio zreg,
                     zon_terri zt,
                     mae_clien_direc cdi,
                     seg_tipo_via,
                     mae_clien_ident ced,
                     (SELECT val_i18n, val_oid
                        FROM own_comun.gen_i18n_sicc_comun
                       WHERE attr_enti = ''SEG_TIPO_VIA'') dtv,
                     (SELECT val_i18n, val_oid
                        FROM own_comun.gen_i18n_sicc_comun
                       WHERE attr_enti = ''MAE_ESTAT_CLIEN'') stat,
                       (select acum2.clie_oid_clie,
                               MIN (seg_perio_corpo.cod_peri) c1ped,
                               MAX (seg_perio_corpo.cod_peri) cmaxped
                        from ped_solic_cabec_acum2 acum2,
                               cra_perio,
                             seg_perio_corpo
                       where acum2.perd_oid_peri = cra_perio.oid_peri
                         and cra_perio.peri_oid_peri = seg_perio_corpo.oid_peri
                       GROUP BY acum2.clie_oid_clie ) ped_1erped_cump
               WHERE cli.oid_clie           = cda.clie_oid_clie
                 AND cda.ind_acti           = 1
                 AND cda.esta_oid_esta_clie = stat.val_oid
                 AND cli.oid_clie           = ced.clie_oid_clie(+)
                 AND cli.oid_clie           = cdi.clie_oid_clie
                 AND cdi.ind_dire_ppal      = 1
                 AND cdi.ind_elim           = 0
                 AND cli.oid_clie           = ua.clie_oid_clie
                 AND ua.ind_acti            = 1
                 AND ua.ztad_oid_terr_admi  = za.oid_terr_admi
                 AND za.terr_oid_terr       = zt.oid_terr
                 AND za.zscc_oid_secc       = sec.oid_secc
                 AND sec.zzon_oid_zona      = zon.oid_zona
                 AND zon.zorg_oid_regi      = zreg.oid_regi
                 AND cli.oid_clie           = ped_1erped_cump.CLIE_OID_CLIE(+)
                 AND cdi.tivi_oid_tipo_via  = seg_tipo_via.oid_tipo_via
                 AND seg_tipo_via.oid_tipo_via = dtv.val_oid
                 and za.pais_oid_pais = '||lnOidPais||'
                 and za.cana_oid_cana = '||lnOidCanal||'
                 and za.marc_oid_marc = '||lnOidMarca||'
                 '||pscondicionPedido||'
                 '||pscondicionEstado||'
                 '||pscondicionRegion||'
                 '||pscondicionZona||'
                 '||pscondicionSaldo||'
                 '||pscondicionDocuPrincipal||'
                 '||pscondicionClienteDocu;


            OPEN c_consultoras_activas for v_query;
            LOOP
                FETCH c_consultoras_activas BULK COLLECT
                    INTO  ingresopedidos LIMIT rows;

                FORALL i IN 1 .. ingresopedidos.count
                    INSERT INTO mae_tempo_consu_pedid(oid_clie, cod_clie, COD_DIGI_CTRL, peri_prim_pedi_acti,
                    val_nomb_peri, peri_prim_pedi, val_nomb_pedi,
                    nom_clie, num_docu_iden, cod_regi, cod_zona, cod_secc, cod_terr, esta_clie,tipo_via, val_nomb_via,
                    num_ppal, val_obse, cod_unid_geog_dir, UNID_GEOG_DIR, tel_casa, tel_celu, val_emai, fec_ulti_actu,val_saldo_avenc, cod_usua,
                    cod_clie_vinc, nom_clie_vinc, cod_zona_clie_vinc, cod_secc_clie_vinc, des_bloq, DES_OBSE_BLOQ, cam_regi,fec_ingr_soli,
                    usu_gest, fec_gest, cam_crea, rem_prim_fact, fec_fact_regi, fec_prim_fact, cod_clie_lider_recom,
                    nom_clie_lider_recom, val_cod_post, DESC_NAC, COD_SEXO,  val_ocr_tdoc, VAL_OCR_ESTA, DESC_TIPO_PERSONA,
                    DESC_ORIGEN_INGRESO, VAL_NOM_BARR, VAL_NOM_MANZ, VAL_ETA_CONJ, VAL_CAL_PRIN, VAL_CAL_SECU, IND_HOJA_PEDI,
                    DES_GERE)
                    VALUES(ingresopedidos(i).oid_clie,
                    ingresopedidos(i).cod_clie,
                    ingresopedidos(i).COD_DIGI_CTRL,
                    ingresopedidos(i).peri_prim_pedi_acti,
                    ingresopedidos(i).val_nomb_peri,
                    ingresopedidos(i).peri_prim_pedi,
                    ingresopedidos(i).val_nomb_pedi,
                    ingresopedidos(i).nom_clie,
                    ingresopedidos(i).num_docu_iden,
                    ingresopedidos(i).cod_regi,
                    ingresopedidos(i).cod_zona,
                    ingresopedidos(i).cod_secc,
                    ingresopedidos(i).cod_terr,
                    ingresopedidos(i).esta_clie,
                    ingresopedidos(i).tipo_via,
                    ingresopedidos(i).val_nomb_via,
                    ingresopedidos(i).num_ppal,
                    ingresopedidos(i).val_obse,
                    ingresopedidos(i).cod_unid_geog_dir,
                    ingresopedidos(i).unid_geog_dir,
                    ingresopedidos(i).tel_casa,
                    ingresopedidos(i).tel_celu,
                    ingresopedidos(i).val_emai,
                    ingresopedidos(i).fec_ulti_actu,
                    ingresopedidos(i).val_saldo_avenc,
                    ingresopedidos(i).cod_usua,
                    ingresopedidos(i).cod_clie_vinc,
                    ingresopedidos(i).nom_clie_vinc,
                    ingresopedidos(i).cod_zona_clie_vinc,
                    ingresopedidos(i).cod_secc_clie_vinc,
                    ingresopedidos(i).des_bloq,
                    ingresopedidos(i).DES_OBSE_BLOQ,
                    ingresopedidos(i).cam_regi,
                    ingresopedidos(i).fec_ingr_soli,
                    ingresopedidos(i).usu_gest,
                    ingresopedidos(i).fec_gest,
                    ingresopedidos(i).cam_crea,
                    ingresopedidos(i).rem_prim_fact,
                    ingresopedidos(i).fec_fact_regi,
                    ingresopedidos(i).fec_prim_fact,
                    ingresopedidos(i).cod_clie_lider_recom,
                    ingresopedidos(i).nom_clie_lider_recom,
                    ingresopedidos(i).val_cod_post,
                    ingresopedidos(i).DESC_NAC,
                    ingresopedidos(i).COD_SEXO,
                    ingresopedidos(i).val_ocr_tdoc,
                    ingresopedidos(i).VAL_OCR_ESTA,
                    ingresopedidos(i).DESC_TIPO_PERSONA,
                    ingresopedidos(i).DESC_ORIGEN_INGRESO,
                    ingresopedidos(i).VAL_NOM_BARR,
                    ingresopedidos(i).VAL_NOM_MANZ,
                    ingresopedidos(i).VAL_ETA_CONJ,
                    ingresopedidos(i).VAL_CAL_PRIN,
                    ingresopedidos(i).VAL_CAL_SECU,
                    ingresopedidos(i).IND_HOJA_PEDI,
                    ingresopedidos(i).DES_GERE);


                EXIT WHEN c_consultoras_activas%NOTFOUND;
            END LOOP;
            CLOSE c_consultoras_activas;
        END IF;

        --pstipoReporte = 6 (REPORTE CONSULTORAS INACTIVAS)
         IF pstiporeporte = '6' THEN

            v_query:='SELECT NULL oid_clie,
                        cli.cod_clie,
                        NULL COD_DIGI_CTRL,
                        NULL peri_prim_pedi_acti,
                        NULL val_nomb_peri,
                       ped_1erped_cump.c1ped
                       as peri_prim_pedi,
                       ped_1erped_cump.cmaxped AS val_nomb_pedi,
                       cli.VAL_NOM1 || '' '' || cli.VAL_NOM2 || '' '' || cli.VAL_APE1 || '' '' || cli.VAL_APE2 as NOM_CLIE,
                       ced.NUM_DOCU_IDEN as NUM_DOCU_IDEN,
                       zreg.cod_regi,
                       zon.cod_zona,
                       sec.cod_secc,
                       zt.cod_terr,
                       stat.VAL_I18N as ESTA_CLIE,
                       dtv.VAL_I18N as Tipo_via,
                       cdi.VAL_NOMB_VIA,
                       cdi.NUM_PPAL,
                       cdi.VAL_OBSE,
                       cdi.COD_UNID_GEOG as COD_UNID_GEOG_DIR,
                        (
                            select
                                DES_GEOG
                            from
                                ZON_VALOR_ESTRU_GEOPO eg
                            where eg.ORDE_1 = substr(cdi.COD_UNID_GEOG,1,6)
                                and eg.ORDE_2 is null
                                and eg.ORDE_3 is null
                        )  || '' / '' ||
                        (
                            select
                                DES_GEOG
                            from
                                ZON_VALOR_ESTRU_GEOPO eg
                            where eg.ORDE_1 = substr(cdi.COD_UNID_GEOG,1,6)
                                and eg.ORDE_2 = substr(cdi.COD_UNID_GEOG,7,6)
                                and eg.ORDE_3 is null
                        )  || '' / '' ||
                        (
                            select
                                DES_GEOG
                            from
                                ZON_VALOR_ESTRU_GEOPO eg
                            where eg.ORDE_1 = substr(cdi.COD_UNID_GEOG,1,6)
                                and eg.ORDE_2 = substr(cdi.COD_UNID_GEOG,7,6)
                                and eg.ORDE_3 = substr(cdi.COD_UNID_GEOG,13,6)
                                and rownum = 1
                        )  as UNID_GEOG_DIR,
                       (select VAL_TEXT_COMU
                          from MAE_CLIEN_COMUN f1
                         where cli.oid_clie = f1.clie_oid_clie(+)
                           and f1.TICM_OID_TIPO_COMU =1) as TEL_CASA,
                       (select VAL_TEXT_COMU
                          from MAE_CLIEN_COMUN f2
                         where cli.oid_clie = f2.clie_oid_clie(+)
                           and f2.TICM_OID_TIPO_COMU =6) as TEL_CELU,
                       (select VAL_TEXT_COMU
                          from MAE_CLIEN_COMUN f2
                         where cli.oid_clie = f2.clie_oid_clie(+)
                           and f2.TICM_OID_TIPO_COMU =3) as VAL_EMAI,
                       NULL fec_ulti_actu,
                       cli.SAL_DEUD_ANTE VAL_SALDO_AVENC,
                       '''||psusuario||''' COD_USUA,
                        NULL COD_CLIE_VINC,
                        NULL NOM_CLIE_VINC,
                        NULL COD_ZONA_CLIE_VINC,
                        NULL COD_SECC_CLIE_VINC,
                        NVL(MAE_PKG_REPOR.MAE_FN_DEVUE_BLOQ_X_CLIE(cli.oid_clie), '''') DES_BLOQ,
                        NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_OBS_MOTI_BLOQ(cli.oid_clie)), '''') des_Bloqueo,
                        ( SELECT nvl(pi.cod_peri,'''')
                       FROM cra_perio ci,
                            seg_perio_corpo pi ,
                            mae_clien mc
                      WHERE ci.peri_oid_peri = pi.oid_peri
                        AND mc.fec_ingr >= ci.fec_inic and mc.fec_ingr <= ci.fec_fina
                        AND mc.oid_clie = cli.oid_clie
                        AND mc.fec_ingr is not null
                       AND rownum = 1
                      ) AS CAM_REGI,
                     cli.fec_crea fec_ingr_soli,
                     cli.usu_modi usu_gest,
                     cli.fec_ulti_actu fec_gest,
                     (SELECT min(b.cod_peri)
                                FROM cra_perio a,
                                     seg_perio_corpo b
                               WHERE a.pais_oid_pais = '||lnOidPais||'
                                 AND a.marc_oid_marc = '||lnOidMarca||'
                                 AND a.cana_oid_cana = '||lnOidCanal||'
                                 AND a.fec_inic <= gen_pkg_gener.gen_fn_fecha_sin_hora(cli.fec_ingr)
                                 AND a.fec_fina >= gen_pkg_gener.gen_fn_fecha_sin_hora(cli.fec_ingr)
                                 AND b.oid_peri = a.peri_oid_peri
                      ) cam_crea,
                      NULL rem_prim_fact,
                      NULL fec_fact_regi,
                      NULL fec_prim_fact,
                      NULL cod_clie_lider_recom,
                      NULL nom_clie_lider_recom,
                      cdi.val_cod_post,
                      (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mda, SEG_NACIO NAC, V_GEN_I18N_SICC I18N
       WHERE mda.snon_oid_naci=nac.oid_naci
         AND NAC.OID_NACI = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''SEG_NACIO''
         AND MDA.CLIE_OID_CLIE = cli.oid_clie and rownum=1),
   (decode(cli.cod_sexo, ''F'', ''Femenino'', ''Masculino'' )),
   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu
   and mci.clie_oid_clie = cli.oid_clie and rownum=1),
   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI
    AND MCDA.CLIE_OID_CLIE= cli.oid_clie and rownum=1),
    (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
         and TP.OID_TIPO_PERS = I18N.VAL_OID
         AND I18N.IDIO_OID_IDIO = 1
         AND I18N.ATTR_ENTI = ''MAE_TIPO_PERSO''
         and mcda.clie_oid_clie= cli.oid_clie and rownum=1),
     (SELECT  I18N.VAL_I18N
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''MAE_ORIG_INGRE''
	    and mcda.clie_oid_clie= cli.oid_clie and rownum=1),
      ( select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1),
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = cli.oid_clie and rownum=1),
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  cli.oid_clie and rownum=1),
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1),
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =cli.oid_clie and rownum=1),
       NULL IND_HOJA_PEDI,
       (select VAL_APE1 || '' '' || VAL_APE2 || '' '' ||VAL_NOM1 || '' '' || VAL_NOM2
        from mae_clien ger, zon_zona zzona
        where ger.oid_clie = zzona.clie_oid_clie
        and zzona.oid_zona = zon.oid_zona) DES_GERE
                  FROM MAE_CLIEN cli,
                       MAE_CLIEN_DATOS_ADICI cda,
                       MAE_CLIEN_UNIDA_ADMIN ua,
                       ZON_TERRI_ADMIN za,
                       ZON_SECCI sec,
                       ZON_ZONA zon,
                       zon_regio zreg,
                       ZON_TERRI zt,
                       MAE_CLIEN_DIREC cdi,
                       SEG_TIPO_VIA,
                       MAE_CLIEN_IDENT Ced,
                       (select VAL_I18N, VAL_OID from GEN_I18N_SICC_COMUN where ATTR_ENTI = ''SEG_TIPO_VIA'') dtv,
                       (select VAL_I18N, VAL_OID from GEN_I18N_SICC_COMUN where ATTR_ENTI = ''MAE_ESTAT_CLIEN'') stat,
                       (select acum2.clie_oid_clie,
                               MIN (seg_perio_corpo.cod_peri) c1ped,
                               MAX (seg_perio_corpo.cod_peri) cmaxped
                        from ped_solic_cabec_acum2 acum2,
                             cra_perio,
                             seg_perio_corpo
                       where acum2.perd_oid_peri = cra_perio.oid_peri
                         and cra_perio.peri_oid_peri = seg_perio_corpo.oid_peri
                       GROUP BY acum2.clie_oid_clie ) ped_1erped_cump
                 WHERE cli.oid_clie           = ped_1erped_cump.CLIE_OID_CLIE(+)
                   and ced.clie_oid_clie(+) = cli.oid_clie
                   AND cli.oid_clie = ua.clie_oid_clie
                   AND ua.ind_acti = 1
                   AND ua.ztad_oid_terr_admi = za.oid_terr_admi
                   AND za.terr_oid_terr = zt.oid_terr
                   AND za.zscc_oid_secc = sec.oid_secc
                   AND sec.zzon_oid_zona = zon.oid_zona
                   AND zreg.oid_regi     = zon.zorg_oid_regi
                   and cli.oid_clie = cda.clie_oid_clie
                   and cda.ESTA_OID_ESTA_CLIE in (1,5,7)
                   and cda.ESTA_OID_ESTA_CLIE = stat.val_oid
                   AND cli.oid_clie = cdi.clie_oid_clie
                   and cdi.IND_DIRE_PPAL = 1
                   and cdi.IND_ELIM = 0
                   and cdi.TIVI_OID_TIPO_VIA = SEG_TIPO_VIA.OID_TIPO_VIA
                   and SEG_TIPO_VIA.OID_TIPO_VIA = dtv.VAL_OID
                   and za.pais_oid_pais = '||lnOidPais||'
                   and za.cana_oid_cana = '||lnOidCanal||'
                   and za.marc_oid_marc = '||lnOidMarca||'
                 '||pscondicionPedido||'
                 '||pscondicionEstado||'
                   '||pscondicionRegion||'
                   '||pscondicionZona||'
                 '||pscondicionSaldo;

            OPEN c_consultoras_inactivas for v_query;
            LOOP
                FETCH c_consultoras_inactivas BULK COLLECT
                    INTO  ingresopedidos LIMIT rows;

                FORALL i IN 1 .. ingresopedidos.count
                    INSERT INTO mae_tempo_consu_pedid(oid_clie, cod_clie, COD_DIGI_CTRL, peri_prim_pedi_acti,
                    val_nomb_peri, peri_prim_pedi, val_nomb_pedi,
                    nom_clie, num_docu_iden, cod_regi, cod_zona, cod_secc, cod_terr, esta_clie,tipo_via, val_nomb_via,
                    num_ppal, val_obse, cod_unid_geog_dir, tel_casa, tel_celu, val_emai, val_saldo_avenc, cod_usua,
                    cod_clie_vinc, nom_clie_vinc, cod_zona_clie_vinc, cod_secc_clie_vinc, des_bloq, DES_OBSE_BLOQ, cam_regi,fec_ingr_soli,
                    usu_gest, fec_gest, cam_crea, rem_prim_fact, fec_fact_regi, fec_prim_fact, cod_clie_lider_recom,
                    nom_clie_lider_recom, val_cod_post, DESC_NAC, COD_SEXO,  val_ocr_tdoc, VAL_OCR_ESTA, DESC_TIPO_PERSONA,
                    DESC_ORIGEN_INGRESO, VAL_NOM_BARR, VAL_NOM_MANZ, VAL_ETA_CONJ, VAL_CAL_PRIN, VAL_CAL_SECU, IND_HOJA_PEDI,
                    DES_GERE)
                    VALUES(ingresopedidos(i).oid_clie,
                    ingresopedidos(i).cod_clie,
                    ingresopedidos(i).COD_DIGI_CTRL,
                    ingresopedidos(i).peri_prim_pedi_acti,
                    ingresopedidos(i).val_nomb_peri,
                    ingresopedidos(i).peri_prim_pedi,
                    ingresopedidos(i).val_nomb_pedi,
                    ingresopedidos(i).nom_clie,
                    ingresopedidos(i).num_docu_iden,
                    ingresopedidos(i).cod_regi,
                    ingresopedidos(i).cod_zona,
                    ingresopedidos(i).cod_secc,
                    ingresopedidos(i).cod_terr,
                    ingresopedidos(i).esta_clie,
                    ingresopedidos(i).tipo_via,
                    ingresopedidos(i).val_nomb_via,
                    ingresopedidos(i).num_ppal,
                    ingresopedidos(i).val_obse,
                    ingresopedidos(i).cod_unid_geog_dir,
                    ingresopedidos(i).tel_casa,
                    ingresopedidos(i).tel_celu,
                    ingresopedidos(i).val_emai,
                    ingresopedidos(i).val_saldo_avenc,
                    ingresopedidos(i).cod_usua,
                    ingresopedidos(i).cod_clie_vinc,
                    ingresopedidos(i).nom_clie_vinc,
                    ingresopedidos(i).cod_zona_clie_vinc,
                    ingresopedidos(i).cod_secc_clie_vinc,
                    ingresopedidos(i).des_bloq,
                    ingresopedidos(i).DES_OBSE_BLOQ,
                    ingresopedidos(i).cam_regi,
                    ingresopedidos(i).fec_ingr_soli,
                    ingresopedidos(i).usu_gest,
                    ingresopedidos(i).fec_gest,
                    ingresopedidos(i).cam_crea,
                    ingresopedidos(i).rem_prim_fact,
                    ingresopedidos(i).fec_fact_regi,
                    ingresopedidos(i).fec_prim_fact,
                    ingresopedidos(i).cod_clie_lider_recom,
                    ingresopedidos(i).nom_clie_lider_recom,
                    ingresopedidos(i).val_cod_post,
                    ingresopedidos(i).DESC_NAC,
                    ingresopedidos(i).COD_SEXO,
                    ingresopedidos(i).val_ocr_tdoc,
                    ingresopedidos(i).VAL_OCR_ESTA,
                    ingresopedidos(i).DESC_TIPO_PERSONA,
                    ingresopedidos(i).DESC_ORIGEN_INGRESO,
                    ingresopedidos(i).VAL_NOM_BARR,
                    ingresopedidos(i).VAL_NOM_MANZ,
                    ingresopedidos(i).VAL_ETA_CONJ,
                    ingresopedidos(i).VAL_CAL_PRIN,
                    ingresopedidos(i).VAL_CAL_SECU,
                    ingresopedidos(i).IND_HOJA_PEDI,
                    ingresopedidos(i).DES_GERE);

                EXIT WHEN c_consultoras_inactivas%NOTFOUND;
            END LOOP;
            CLOSE c_consultoras_inactivas;
        END IF;

     EXCEPTION
       WHEN OTHERS THEN
         ln_sqlcode := SQLCODE;
         ls_sqlerrm := substr(sqlerrm,1,250);
         RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_GENER_REPOR_CONSU_PEDID: '||ls_sqlerrm);
    END MAE_PR_GENER_REPOR_CONSU_PEDID;

  /*********************************************************************************
  Descripcion       : Inserta resultados de consulta nuevas o rechazadas en temporal
  Fecha Creacion    : 27/05/2013
  Autor             : Guerra Chacaltana Luis Sebastian
  Parametros        :
    oidpais                oid de país
    oidperiodo             oid de periodo
    oidactividad           oid de actividad
    condicionfechainicio   condición fecha de inicio de creación
    condicionfechafin      condición fecha de termino de creación
    condicionregion1       condición de región
    condicionzona1         condición de zona
    condicionfechainicio2  condición fecha de inicio de modificación
    condicionfechafin2     condición fecha de termino de modificación
    condicionregion2       condición de segunda región
    condicionzona2         condición de segunda zona
    pstiporeporte          tipo de reporte
    psoidproceso           oid de proceso
    cadena01               cadena que contiene primera parte de sentencia sql
    cadena02               cadena que contiene segunda parte de sentencia sql
    cadena03               cadena que contiene tercera parte de sentencia sql
  **********************************************************************************/
  PROCEDURE mae_pr_gener_tempo_nueva_csv
  (
    psoidpais                VARCHAR2,
    psoidperiodo             NUMBER,
    psoidactividad           VARCHAR2,
    pscondicionfechainicio   VARCHAR2,
    pscondicionfechafin      VARCHAR2,
    pscondicionregion1       VARCHAR2,
    pscondicionzona1         VARCHAR2,
    pscondicionfechainicio2  VARCHAR2,
    pscondicionfechafin2     VARCHAR2,
    pscondicionregion2       VARCHAR2,
    pscondicionzona2         VARCHAR2,
    pstiporeporte            VARCHAR2,
    psIndicadorDesbloqueo    VARCHAR2,
    psoidproceso             OUT NUMBER
  ) IS

    cadena01 VARCHAR2(8000);
    cadena02 VARCHAR2(8000);
    cadena03 VARCHAR2(8000);
    cadena04 VARCHAR2(8000);
    cadena05 VARCHAR2(8000);
    cadena06 VARCHAR2(8000);
    lsPeriodoActual VARCHAR2(6);
    lsPeriodoAnterior VARCHAR2(6);
    
    BEGIN

        cadena01:='';
        cadena02:='';
        cadena03:='';
        cadena04:='';
        cadena05:='';
        cadena06:='';

        select mae_seq_nueva_recha.nextval into psoidproceso from dual;


        select s.cod_peri
          into lsPeriodoActual
          from cra_perio c, seg_perio_corpo s
         where c.peri_oid_peri = s.oid_peri
           and c.oid_peri = psoidperiodo;

         select gen_pkg_gener.gen_fn_perio_nsigu((select cod_pais from seg_pais  where oid_pais = psoidpais),lsPeriodoActual,-1)
           into lsPeriodoAnterior
           from dual;

        IF pstiporeporte ='0' THEN
            cadena01 :=
            'INSERT INTO mae_tempo_nueva_recha(VAL_OBSE, oid_proc_nuev, cod_clie, oid_clie,
            cod_digi_ctrl, nom_clie, tip_docu, num_docu_iden, fec_crea, des_esta,
            des_bloq, ind_acti, tip_clie, sub_tipo_clie, tip_clasi, des_clas,
            cod_regi, cod_zona, cod_secc, cod_terr, val_dire, VAL_COD_POST, num_tele, num_celu,
            cod_reco, nom_reco, ind_lide, cam_crea, ind_cier, fec_ingr_soli, usu_gest,
            fec_gest, cod_sexo, fec_fact_regi, fec_prim_fact, rem_prim_fact, ind_impr_docu,
            DESC_NAC,VAL_COD_SEXO,val_ocr_tdoc,VAL_OCR_ESTA,DESC_TIPO_PERSONA,DESC_ORIGEN_INGRESO,
            VAL_NOM_BARR,VAL_NOM_MANZ,VAL_ETA_CONJ,VAL_CAL_PRIN,VAL_CAL_SECU,CAM_PRIM_CONT,CAM_PRIM_PEDI,
            DES_UBIG, DES_EMAI, CAM_ANTE_PEDI, CAM_ACTU_PEDI, FEC_FACT, DES_OBSE_BLOQ,
            CAM_ACTI, FEC_ACTI, USU_ACTI, VAL_CANT_BLOQ )
            SELECT
               MCD.VAL_OBSE,
               '||psoidproceso||' id_proc_nueva, mc.cod_clie as Codigo_Cliente,
               mc.oid_clie as Oid_Cliente,
               mc.cod_digi_ctrl,
              (TRIM(mc.val_ape1) || '' '' || TRIM(mc.val_ape2) || '', '' || TRIM(mc.val_nom1) || '' '' || TRIM(mc.val_nom2)) as nombre,
              (SELECT d.val_i18n
                 FROM gen_i18n_sicc_pais d
                WHERE d.attr_enti = ''MAE_TIPO_DOCUM''
                  AND d.val_oid = mci.tdoc_oid_tipo_docu) as tipo_doc,
               mci.num_docu_iden,
               to_char(mc.fec_crea, ''dd/mm/yyyy'') as fec_crea,
               dmec.val_i18n as Status,
               NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_BLOQ_X_CLIE(mc.oid_clie)), '''') as Bloqueo,
               decode(mcda.ind_acti, 0, ''No'', ''Si'') as Activo,
               dmtc.val_i18n as Tipo_Cliente,
               dmsc.val_i18n as SubTipo_Cliente,
              (SELECT d.val_i18n
                 FROM gen_i18n_sicc_comun d
                WHERE d.attr_enti = ''MAE_CLASI''
                  AND d.val_oid IN (mcc.clas_oid_clas)) Tipo_Clasif,
              (SELECT d.val_i18n
                 FROM gen_i18n_sicc_comun d
                WHERE d.attr_enti = ''MAE_TIPO_CLASI_CLIEN''
                  AND d.val_oid IN (mcc.tccl_oid_tipo_clasi)) Clasif,
               zr.des_regi as Region,
               zz.des_zona as Zona,
               zs.des_secci as Seccion,
               zt.cod_terr as Territorio,
              (
               (SELECT nvl(stv.des_abrv_tipo_via, '' '')
                  FROM seg_tipo_via stv
                 WHERE stv.oid_tipo_via = mcd.tivi_oid_tipo_via
                ) || '' '' ||
                mcd.val_nomb_via || '', '' ||
                mcd.val_obse || '', '' ||
                GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mc.oid_clie,''DES_URBA'') || '', '' ||
                mcd.num_ppal
              )  AS DIRECCION,
               mcd.val_cod_post,
               dtel.telefono,
               dcel.telefono as celular,
              (SELECT (SELECT c.cod_Clie
                         FROM mae_clien c
                        WHERE c.oid_clie = b.clie_oid_clie_vnte) as cod_Clie
                 FROM mae_clien_vincu b
                WHERE b.clie_oid_clie_vndo = mc.oid_clie
                AND b.tivc_oid_tipo_vinc = 9) as Cod_Recom,
              (SELECT (SELECT (TRIM(c.val_ape1) || '' '' || TRIM(c.val_ape2) || '', '' ||
                               TRIM(c.val_nom1) || '' '' || TRIM(c.val_nom2)) as nombre
                         FROM mae_clien c
                        WHERE c.oid_clie = b.clie_oid_clie_vnte) as cod_Clie
                 FROM mae_clien_vincu b
                WHERE b.clie_oid_clie_vndo = mc.oid_clie
                AND b.tivc_oid_tipo_vinc = 9) as Nomb_Recom,
               DECODE(NVL(zs.CLIE_OID_CLIE,0),0,''NO'',''SI'') as Lider ,
               GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(mcpc.perd_oid_peri)          campana_creacion,
                 DECODE((MAE_PKG_PROCE_CLIEN.MAE_FN_VALID_CIERR_REGIO('||psoidpais||','||psoidperiodo||', zr.oid_regi, '||psoidactividad||', mc.fec_crea)),''N'',''SI'',''NO'')  IND_CIERRE,
               sto_pkg_gener.sto_fn_devue_gesti_docum(''FEC_DIGI'',''SCC'',mc.cod_clie,'||psoidperiodo||') fec_ingre_solic,
               sto_pkg_gener.sto_fn_devue_gesti_docum(''USU_MODI'',''SCC'',mc.cod_clie,'||psoidperiodo||') usu_gest,
               sto_pkg_gener.sto_fn_devue_gesti_docum(''FEC_MODI'',''SCC'',mc.cod_clie,'||psoidperiodo||') fec_gest,               
               decode(mc.cod_sexo, ''F'', ''Femenino'', ''Masculino'' ) COD_SEXO,
               TO_CHAR((SELECT MIN(FEC_INIC)
                  from cra_crono cr
                 where cr.perd_oid_peri = '||psoidperiodo||'
                   and cr.cact_oid_acti =
                       (select oid_acti
                          from cra_activ
                         where cod_acti = ''FA''
                           and pais_oid_pais = '||psoidpais||')
                   and cr.zzon_oid_zona IN
                       (SELECT OID_ZONA
                          FROM ZON_ZONA
                         WHERE ZORG_OID_REGI = zr.oid_regi)), ''DD/MM/YYYY'') as fec_fact_regi,
               TO_CHAR((select min(soca.fec_fact)
                  from ped_solic_cabec     soca,
                       ped_solic_cabec     cons,
                       ped_tipo_solic_pais tspa,
                       ped_tipo_solic      tsol
                 where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                   and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   and tsol.cod_tipo_soli = ''SOC''
                   and soca.clie_oid_clie = mc.oid_clie
                   and soca.perd_oid_peri = '||psoidperiodo||'
                   and soca.grpr_oid_grup_proc = 5
                   and cons.esso_oid_esta_soli != 4), ''DD/MM/YYYY'') as fec_prim_fact,
                 (SELECT NVL( MIN(seg.ind_comp), 0)
                FROM ped_solic_cabec soca, ped_solic_cabec cons, ped_segui_pedid seg, ped_tipo_solic_pais tspa, ped_tipo_solic tsol
                WHERE soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                   AND cons.oid_soli_cabe = seg.soca_oid_soli_cabe
                   AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   AND tsol.cod_tipo_soli = ''SOC''
                   AND (soca.perd_oid_peri = ( SELECT MPC.PERD_OID_PERI FROM MAE_CLIEN_PRIME_CONTA MPC WHERE MPC.CLIE_OID_CLIE = mc.oid_clie) or
                        mcda.CAM_ACTI = '||lsPeriodoActual||' )
                   AND soca.grpr_oid_grup_proc = 5
                   AND cons.esso_oid_esta_soli != 4
                   AND SOCA.CLIE_OID_CLIE = mc.oid_clie ) REM_PRIM_FACT,
               (case nvl(mcda.ind_impr_docu, ''0'') when ''0'' then ''SI'' else ''NO'' end) as ind_impr_docu,';

         cadena04:=' ( SELECT  I18N.VAL_I18N AS DESCRIPCION
        FROM mae_clien_datos_adici mda, SEG_NACIO NAC, V_GEN_I18N_SICC I18N
       WHERE mda.snon_oid_naci=nac.oid_naci
         AND NAC.OID_NACI = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''SEG_NACIO''
         AND MDA.CLIE_OID_CLIE = mc.oid_clie and rownum=1) as nacionalidad   ,

   (decode(mc.cod_sexo, ''F'', ''Femenino'', ''Masculino'' )) AS sexo,

   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu
   and mci.clie_oid_clie = mc.oid_clie and rownum=1) as tipo_documento_identidad ,


   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI
    AND MCDA.CLIE_OID_CLIE= mc.oid_clie and rownum=1) AS estado_civil ,


    (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
         and TP.OID_TIPO_PERS = I18N.VAL_OID
         AND I18N.IDIO_OID_IDIO = 1
         AND I18N.ATTR_ENTI = ''MAE_TIPO_PERSO''
         and mcda.clie_oid_clie= mc.oid_clie and rownum=1) AS tipo_persona,


     (SELECT  I18N.VAL_I18N
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''MAE_ORIG_INGRE''
	    and mcda.clie_oid_clie= mc.oid_clie and rownum=1) AS origen_ingreso,


      ( select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = mc.oid_clie and rownum=1) AS nombre_barrio,
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = mc.oid_clie and rownum=1) as manzana_letra,
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  mc.oid_clie and rownum=1) as etapa_conjunto,
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =mc.oid_clie and rownum=1) as calle_principal,
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =mc.oid_clie and rownum=1)  as calle_secundaria,
       substr(peri.VAL_NOMB_PERI,-6) as camPrimCon,       
       (SELECT substr(MIN(per.VAL_NOMB_PERI),-6)
        FROM ped_solic_cabec p,cra_perio per
       WHERE p.clie_oid_clie = mc.oid_clie
         AND p.tspa_oid_tipo_soli_pais =  (SELECT tsp.oid_tipo_soli_pais
                                                           FROM ped_tipo_solic_pais tsp,
                                                            ped_tipo_solic      ts
                                                             WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                                                           AND ts.cod_tipo_soli = ''SOC'')
     and per.oid_peri = p.perd_oid_peri    ) as CamPediMin, ';
         cadena06:='     ( SELECT (SELECT des_geog
               FROM zon_valor_estru_geopo
              WHERE orde_1 =
                       (SELECT orde_1
                          FROM zon_valor_estru_geopo
                         WHERE OID_VALO_ESTR_GEOP = d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_2 IS NULL
                    AND orde_3 IS NULL
                    AND orde_4 IS NULL)
         || ''/''
         || (SELECT des_geog
               FROM zon_valor_estru_geopo
              WHERE orde_1 =
                       (SELECT orde_1
                          FROM zon_valor_estru_geopo
                         WHERE OID_VALO_ESTR_GEOP = d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_2 =
                           (SELECT orde_2
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_3 IS NULL
                    AND orde_4 IS NULL)
         || ''/''
         || (SELECT des_geog
               FROM zon_valor_estru_geopo
              WHERE orde_1 =
                       (SELECT orde_1
                          FROM zon_valor_estru_geopo
                         WHERE OID_VALO_ESTR_GEOP = d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_2 =
                           (SELECT orde_2
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_3 =
                           (SELECT orde_3
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_4 IS NULL)
         || ''/''
         || (SELECT des_geog
               FROM zon_valor_estru_geopo
              WHERE orde_1 =
                       (SELECT orde_1
                          FROM zon_valor_estru_geopo
                         WHERE OID_VALO_ESTR_GEOP = d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_2 =
                           (SELECT orde_2
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_3 =
                           (SELECT orde_3
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_4 =
                           (SELECT orde_4
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP))
                         UBIGEO
    FROM zon_terri_admin c,
         mae_clien_unida_admin ua,
         zon_terri d,
         zon_secci e,
         zon_zona f,
         zon_regio g
   WHERE Mc.oid_clie = ua.clie_oid_clie
         AND ua.ind_acti = 1
         AND ua.ZTAD_OID_TERR_ADMI = c.OID_TERR_ADMI
         AND c.ZSCC_OID_SECC = e.OID_SECC
         AND c.TERR_OID_TERR = d.OID_TERR
         AND e.ZZON_OID_ZONA = f.OID_ZONA
         AND f.ZORG_OID_REGI = g.OID_REGI
         AND ROWNUM = 1) as UBIGEO,

        (select val_text_comu from mae_clien_comun com where com.CLIE_OID_CLIE=Mc.oid_clie
        and ticm_oid_tipo_comu=3) as Correo,


        (select max(cra1.cod_peri) from SEG_PERIO_CORPO CRA1, CRA_PERIO CRA2, PED_SOLIC_CABEC PED
        where CRA1.OID_PERI=CRA2.PERI_OID_PERI
                  AND CRA2.OID_PERI=PED.perd_oid_peri and clie_oid_clie=Mc.oid_clie and tspa_oid_tipo_soli_pais in (
                  select tsp.oid_tipo_soli_pais
                                from ped_tipo_solic_pais tsp, ped_tipo_solic ts, v_gen_i18n_sicc i
                                where i.idio_oid_idio = 1
                                and i.attr_enti =''PED_TIPO_SOLIC''
                                and i.val_oid = ts.oid_tipo_soli
                                and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                and ts.cod_tipo_soli=''SOC''
                                and tspa_oid_tipo_soli_pais=tsp.oid_tipo_soli_pais
                  ) and cra1.cod_peri='||lsPeriodoAnterior||') as FACTURO_CAMP_ANT,


        (select max(cra1.cod_peri) from SEG_PERIO_CORPO CRA1, CRA_PERIO CRA2, PED_SOLIC_CABEC PED
        where CRA1.OID_PERI=CRA2.PERI_OID_PERI
         AND CRA2.OID_PERI=PED.perd_oid_peri and clie_oid_clie=mc.oid_clie and tspa_oid_tipo_soli_pais in (
                  select tspa.oid_tipo_soli_pais
                                from ped_tipo_solic_pais tspa, ped_tipo_solic ts, v_gen_i18n_sicc i
                                where i.idio_oid_idio = 1
                                and i.attr_enti =''PED_TIPO_SOLIC''
                                and i.val_oid = ts.oid_tipo_soli
                                and tspa.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                and ts.cod_tipo_soli=''SOC''
                                and tspa_oid_tipo_soli_pais=tspa.oid_tipo_soli_pais
                  ) and cra1.cod_peri='||lsPeriodoActual||') as FACTURO_cAMP_ACTUAL,


          (select max(TO_CHAR( fec_fact,''dd/mm/yyyy''))  from SEG_PERIO_CORPO CRA1, CRA_PERIO CRA2, PED_SOLIC_CABEC PED
        where CRA1.OID_PERI=CRA2.PERI_OID_PERI
         AND CRA2.OID_PERI=PED.perd_oid_peri and clie_oid_clie=mc.oid_clie and tspa_oid_tipo_soli_pais IN (select tsp.oid_tipo_soli_pais
  from ped_tipo_solic_pais tsp, ped_tipo_solic ts, v_gen_i18n_sicc i
  where i.idio_oid_idio = 1
  and i.attr_enti =''PED_TIPO_SOLIC''
  and i.val_oid = ts.oid_tipo_soli
  and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
  and ts.cod_tipo_soli=''SOC'') and fec_fact is not null) as FEC_FACT,
  NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_OBS_MOTI_BLOQ(mc.oid_clie)), '''') as des_Bloqueo,
  mcda.CAM_ACTI,
  mcda.FEC_ACTI,
  mcda.usu_ACTI,
  MAE_PKG_REPOR.MAE_FN_DEVUE_CANT_BLOQ_X_CLIE(mc.oid_clie) as cant_Bloqueo';

            cadena02 :=
            ' FROM mae_clien mc,
               mae_clien_ident mci,
               mae_clien_datos_adici mcda,
               mae_clien_tipo_subti mcts,
               mae_clien_clasi mcc,
               mae_clien_bloqu mcb,
               mae_clien_prime_conta mcpc,
               mae_clien_unida_admin mcua,
               mae_clien_direc mcd,
               zon_terri_admin zta,
               zon_terri zt,
               zon_secci zs,
               zon_zona zz,
               zon_regio zr,
              (SELECT val_i18n,
                      val_oid
                 FROM gen_i18n_sicc_comun
                WHERE attr_enti = ''MAE_TIPO_CLIEN'') dmtc,
              (SELECT val_i18n, val_oid
                 FROM gen_i18n_sicc_comun
                WHERE attr_enti = ''MAE_SUBTI_CLIEN'') dmsc,
              (SELECT val_i18n,
                      val_oid
                 FROM gen_i18n_sicc_comun
                WHERE attr_enti = ''MAE_ESTAT_CLIEN'') dmec,
              (SELECT mcc.clie_oid_clie,
                      mcc.val_text_comu telefono
                 FROM mae_clien_comun mcc,
                      mae_tipo_comun mtc
                WHERE mcc.ticm_oid_tipo_comu = mtc.oid_tipo_comu
                  AND mtc.cod_tipo_comu IN (''TF'')
             ORDER BY 1) dtel,
              (SELECT mcc.clie_oid_clie,
                      mcc.val_text_comu telefono
                 FROM mae_clien_comun mcc,
                      mae_tipo_comun mtc
                WHERE mcc.ticm_oid_tipo_comu = mtc.oid_tipo_comu
                  AND mtc.cod_tipo_comu IN (''TM'')
                 ORDER BY 1) dcel,
               cra_perio  peri
            WHERE mc.oid_clie = mcda.clie_oid_clie
            AND mc.oid_clie = mcts.clie_oid_clie
            AND mc.oid_clie = mcua.clie_oid_clie
            AND mc.oid_clie = mcua.clie_oid_clie
            AND mc.oid_Clie = mci.clie_oid_clie
            AND mcpc.clie_oid_clie = mc.oid_clie
            AND mci.val_iden_docu_prin = 1
            AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
            AND mcua.ind_acti = 1
            AND zta.terr_oid_terr = zt.oid_terr
            AND zta.zscc_oid_secc = zs.oid_secc
            AND zs.zzon_oid_zona = zz.oid_zona
            AND zr.oid_regi = zz.zorg_oid_regi
            AND mc.oid_clie = mcd.clie_oid_clie
            AND mcd.ind_dire_ppal = 1
            AND mcd.ind_elim = 0
            AND mc.oid_clie = dtel.clie_oid_clie(+)
            AND mc.oid_clie = dcel.clie_oid_clie(+)
            AND mc.oid_clie = mcb.clie_oid_clie(+)
            AND mcts.ticl_oid_tipo_clie = dmtc.val_oid
            AND mcts.sbti_oid_subt_clie = dmsc.val_oid
            AND mcda.esta_oid_esta_clie = dmec.val_oid
            AND mcc.ctsu_oid_clie_tipo_subt = mcts.oid_clie_tipo_subt
            AND (SELECT d.val_i18n
                  FROM gen_i18n_sicc_comun d
                 WHERE d.attr_enti = ''MAE_CLASI''
                   AND d.val_oid IN (mcc.clas_oid_clas)) LIKE ''%Consultora%''
            AND mcpc.PERD_OID_PERI = PERI.OID_PERI';

            if (pscondicionfechainicio is null and pscondicionfechafin is null) then
                cadena02 := cadena02 || ' ' || 
                           'and (mcpc.perd_oid_peri=' || psoidperiodo ||
                           ' or mcda.CAM_ACTI = ''' || lsPeriodoActual ||''') '  ;
            end if;

            if (pscondicionfechainicio is not null) then
                cadena02 := cadena02 || ' ' || pscondicionfechainicio;
            end if;

            if (pscondicionfechafin is not null) then
                cadena02 := cadena02 || ' ' || pscondicionfechafin;
            end if;

            if (pscondicionregion1 is not null) then
                cadena02 := cadena02 || ' ' || pscondicionregion1;
            end if;

            if (pscondicionzona1 is not null) then
                cadena02 := cadena02 || ' ' || pscondicionzona1;
            end if;

            if (psIndicadorDesbloqueo = '0') then
                cadena02 := cadena02 || ' ' || 'AND mcb.fec_desb IS NULL';
            end if;

        ELSIF pstiporeporte = '7' THEN
            cadena01 :=
            'INSERT INTO mae_tempo_nueva_recha(VAL_OBSE, oid_proc_nuev, cod_clie, oid_clie,
            cod_digi_ctrl, nom_clie, tip_docu, num_docu_iden, fec_crea, des_esta,
            des_bloq, ind_acti, tip_clie, sub_tipo_clie, tip_clasi, des_clas,
            cod_regi, cod_zona, cod_secc, cod_terr, val_dire, val_dire_entr, VAL_COD_POST, num_tele, num_celu,
            cod_reco, nom_reco, ind_lide, cam_crea, ind_cier, fec_ingr_soli, usu_gest,
            fec_gest, cod_sexo, fec_fact_regi, fec_prim_fact, rem_prim_fact, ind_impr_docu,
            DESC_NAC,VAL_COD_SEXO,val_ocr_tdoc,VAL_OCR_ESTA,DESC_TIPO_PERSONA,DESC_ORIGEN_INGRESO,
            VAL_NOM_BARR,VAL_NOM_MANZ,VAL_ETA_CONJ,VAL_CAL_PRIN,VAL_CAL_SECU,CAM_PRIM_CONT,CAM_PRIM_PEDI,
            DES_UBIG, DES_EMAI, CAM_ANTE_PEDI, CAM_ACTU_PEDI, FEC_FACT, DES_OBSE_BLOQ,VAL_NOM_REFE,
            VAL_TEL_REFE,VAL_CEL_REFE,VAL_NOM_AUX,VAL_TEL_AUX,VAL_CEL_AUX)
            SELECT
               MCD.VAL_OBSE,
               '||psoidproceso||' id_proc_nueva, mc.cod_clie as Codigo_Cliente,
               mc.oid_clie as Oid_Cliente,
               mc.cod_digi_ctrl,
              (TRIM(mc.val_ape1) || '' '' || TRIM(mc.val_ape2) || '', '' || TRIM(mc.val_nom1) || '' '' || TRIM(mc.val_nom2)) as nombre,
              (SELECT d.val_i18n
                 FROM gen_i18n_sicc_pais d
                WHERE d.attr_enti = ''MAE_TIPO_DOCUM''
                  AND d.val_oid = mci.tdoc_oid_tipo_docu) as tipo_doc,
               mci.num_docu_iden,
               to_char(mc.fec_crea, ''dd/mm/yyyy'') as fec_crea,
               dmec.val_i18n as Status,
               NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_BLOQ_X_CLIE(mc.oid_clie)), '''') as Bloqueo,
               decode(mcda.ind_acti, 0, ''No'', ''Si'') as Activo,
               dmtc.val_i18n as Tipo_Cliente,
               dmsc.val_i18n as SubTipo_Cliente,
              (SELECT d.val_i18n
                 FROM gen_i18n_sicc_comun d
                WHERE d.attr_enti = ''MAE_CLASI''
                  AND d.val_oid IN (mcc.clas_oid_clas)) Tipo_Clasif,
              (SELECT d.val_i18n
                 FROM gen_i18n_sicc_comun d
                WHERE d.attr_enti = ''MAE_TIPO_CLASI_CLIEN''
                  AND d.val_oid IN (mcc.tccl_oid_tipo_clasi)) Clasif,
               zr.des_regi as Region,
               zz.des_zona as Zona,
               zs.des_secci as Seccion,
               zt.cod_terr as Territorio,
              (
               (SELECT nvl(stv.des_abrv_tipo_via, '' '')
                  FROM seg_tipo_via stv
                 WHERE stv.oid_tipo_via = mcd.tivi_oid_tipo_via
                ) || '' '' ||
                mcd.val_nomb_via || '', '' ||
                mcd.val_obse || '', '' ||
                GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mc.oid_clie,''DES_URBA'') || '', '' ||
                mcd.num_ppal
              )  AS DIRECCION,              
            (
            SELECT (
                    SELECT nvl(stv.des_abrv_tipo_via, '' '') 
                    FROM seg_tipo_via stv 
                    WHERE stv.oid_tipo_via = mcde.tivi_oid_tipo_via) || '' '' ||
                    mcde.val_nomb_via || '', '' ||
                    mcde.val_obse || '', '' ||
                    mcde.num_ppal
            FROM  MAE_CLIEN_DIREC mcde, MAE_TIPO_DIREC tde
            where mcde.clie_oid_clie = mc.oid_clie AND ind_elim=0 
            AND mcde.tidc_oid_tipo_dire = tde.oid_tipo_dire AND TDE.COD_TIPO_DIRE=''07''
            ) AS DIR_ENT,
               mcd.val_cod_post,
               dtel.telefono,
               dcel.telefono as celular,
              (SELECT (SELECT c.cod_Clie
                         FROM mae_clien c
                        WHERE c.oid_clie = b.clie_oid_clie_vnte) as cod_Clie
                 FROM mae_clien_vincu b
                WHERE b.clie_oid_clie_vndo = mc.oid_clie
                AND b.tivc_oid_tipo_vinc = 9) as Cod_Recom,
              (SELECT (SELECT (TRIM(c.val_ape1) || '' '' || TRIM(c.val_ape2) || '', '' ||
                               TRIM(c.val_nom1) || '' '' || TRIM(c.val_nom2)) as nombre
                         FROM mae_clien c
                        WHERE c.oid_clie = b.clie_oid_clie_vnte) as cod_Clie
                 FROM mae_clien_vincu b
                WHERE b.clie_oid_clie_vndo = mc.oid_clie
                AND b.tivc_oid_tipo_vinc = 9) as Nomb_Recom,
               DECODE(NVL(zs.CLIE_OID_CLIE,0),0,''NO'',''SI'') as Lider ,
               GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(mcpc.perd_oid_peri)          campana_creacion,
                 DECODE((MAE_PKG_PROCE_CLIEN.MAE_FN_VALID_CIERR_REGIO('||psoidpais||','||psoidperiodo||', zr.oid_regi, '||psoidactividad||', mc.fec_crea)),''N'',''SI'',''NO'')  IND_CIERRE,
               cre.fec_hora_crea_soli fec_ingre_solic,
               dig.usu_modi usu_gest,
               TO_CHAR(dig.fec_modi, ''DD/MM/YYYY hh24:mm:ss'') fec_gest,
               decode(mc.cod_sexo, ''F'', ''Femenino'', ''Masculino'' ) COD_SEXO,
               TO_CHAR((SELECT MIN(FEC_INIC)
                  from cra_crono cr
                 where cr.perd_oid_peri = '||psoidperiodo||'
                   and cr.cact_oid_acti =
                       (select oid_acti
                          from cra_activ
                         where cod_acti = ''FA''
                           and pais_oid_pais = '||psoidpais||')
                   and cr.zzon_oid_zona IN
                       (SELECT OID_ZONA
                          FROM ZON_ZONA
                         WHERE ZORG_OID_REGI = zr.oid_regi)), ''DD/MM/YYYY'') as fec_fact_regi,
               TO_CHAR((select min(soca.fec_fact)
                  from ped_solic_cabec     soca,
                       ped_solic_cabec     cons,
                       ped_tipo_solic_pais tspa,
                       ped_tipo_solic      tsol
                 where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                   and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   and tsol.cod_tipo_soli = ''SOC''
                   and soca.clie_oid_clie = mc.oid_clie
                   and soca.perd_oid_peri = '||psoidperiodo||'
                   and soca.grpr_oid_grup_proc = 5
                   and cons.esso_oid_esta_soli != 4), ''DD/MM/YYYY'') as fec_prim_fact,
                 (SELECT NVL( MIN(seg.ind_comp), 0)
                FROM ped_solic_cabec soca, ped_solic_cabec cons, ped_segui_pedid seg, ped_tipo_solic_pais tspa, ped_tipo_solic tsol
                WHERE soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                   AND cons.oid_soli_cabe = seg.soca_oid_soli_cabe
                   AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   AND tsol.cod_tipo_soli = ''SOC''
                   AND soca.perd_oid_peri = ( SELECT MPC.PERD_OID_PERI FROM MAE_CLIEN_PRIME_CONTA MPC WHERE MPC.CLIE_OID_CLIE = mc.oid_clie)
                   AND soca.grpr_oid_grup_proc = 5
                   AND cons.esso_oid_esta_soli != 4
                   AND SOCA.CLIE_OID_CLIE = mc.oid_clie ) REM_PRIM_FACT,
               (case nvl(mcda.ind_impr_docu, ''0'') when ''0'' then ''SI'' else ''NO'' end) as ind_impr_docu,';

         cadena04:=' ( SELECT  I18N.VAL_I18N AS DESCRIPCION
        FROM mae_clien_datos_adici mda, SEG_NACIO NAC, V_GEN_I18N_SICC I18N
       WHERE mda.snon_oid_naci=nac.oid_naci
         AND NAC.OID_NACI = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''SEG_NACIO''
         AND MDA.CLIE_OID_CLIE = mc.oid_clie and rownum=1) as nacionalidad   ,

   (decode(mc.cod_sexo, ''F'', ''Femenino'', ''Masculino'' )) AS sexo,

   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu
   and mci.clie_oid_clie = mc.oid_clie and rownum=1) as tipo_documento_identidad ,


   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI
    AND MCDA.CLIE_OID_CLIE= mc.oid_clie and rownum=1) AS estado_civil ,


    (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
         and TP.OID_TIPO_PERS = I18N.VAL_OID
         AND I18N.IDIO_OID_IDIO = 1
         AND I18N.ATTR_ENTI = ''MAE_TIPO_PERSO''
         and mcda.clie_oid_clie= mc.oid_clie and rownum=1) AS tipo_persona,


     (SELECT  I18N.VAL_I18N
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''MAE_ORIG_INGRE''
	    and mcda.clie_oid_clie= mc.oid_clie and rownum=1) AS origen_ingreso,


      ( select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = mc.oid_clie and rownum=1) AS nombre_barrio,
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = mc.oid_clie and rownum=1) as manzana_letra,
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  mc.oid_clie and rownum=1) as etapa_conjunto,
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =mc.oid_clie and rownum=1) as calle_principal,
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =mc.oid_clie and rownum=1)  as calle_secundaria,
       substr(peri.VAL_NOMB_PERI,-6) as camPrimCon,       
       (SELECT substr(MIN(per.VAL_NOMB_PERI),-6)
        FROM ped_solic_cabec p,cra_perio per
       WHERE p.clie_oid_clie = mc.oid_clie
         AND p.tspa_oid_tipo_soli_pais =  (SELECT tsp.oid_tipo_soli_pais
                                                           FROM ped_tipo_solic_pais tsp,
                                                            ped_tipo_solic      ts
                                                             WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
                                                           AND ts.cod_tipo_soli = ''SOC'')
     and per.oid_peri = p.perd_oid_peri    ) as CamPediMin, ';

         cadena06:='     ( SELECT (SELECT des_geog
               FROM zon_valor_estru_geopo
              WHERE orde_1 =
                       (SELECT orde_1
                          FROM zon_valor_estru_geopo
                         WHERE OID_VALO_ESTR_GEOP = d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_2 IS NULL
                    AND orde_3 IS NULL
                    AND orde_4 IS NULL)
         || ''/''
         || (SELECT des_geog
               FROM zon_valor_estru_geopo
              WHERE orde_1 =
                       (SELECT orde_1
                          FROM zon_valor_estru_geopo
                         WHERE OID_VALO_ESTR_GEOP = d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_2 =
                           (SELECT orde_2
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_3 IS NULL
                    AND orde_4 IS NULL)
         || ''/''
         || (SELECT des_geog
               FROM zon_valor_estru_geopo
              WHERE orde_1 =
                       (SELECT orde_1
                          FROM zon_valor_estru_geopo
                         WHERE OID_VALO_ESTR_GEOP = d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_2 =
                           (SELECT orde_2
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_3 =
                           (SELECT orde_3
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_4 IS NULL)
         || ''/''
         || (SELECT des_geog
               FROM zon_valor_estru_geopo
              WHERE orde_1 =
                       (SELECT orde_1
                          FROM zon_valor_estru_geopo
                         WHERE OID_VALO_ESTR_GEOP = d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_2 =
                           (SELECT orde_2
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_3 =
                           (SELECT orde_3
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP)
                    AND orde_4 =
                           (SELECT orde_4
                              FROM zon_valor_estru_geopo
                             WHERE OID_VALO_ESTR_GEOP =
                                      d.VEPO_OID_VALO_ESTR_GEOP))
                         UBIGEO
    FROM zon_terri_admin c,
         mae_clien_unida_admin ua,
         zon_terri d,
         zon_secci e,
         zon_zona f,
         zon_regio g
   WHERE Mc.oid_clie = ua.clie_oid_clie
         AND ua.ind_acti = 1
         AND ua.ZTAD_OID_TERR_ADMI = c.OID_TERR_ADMI
         AND c.ZSCC_OID_SECC = e.OID_SECC
         AND c.TERR_OID_TERR = d.OID_TERR
         AND e.ZZON_OID_ZONA = f.OID_ZONA
         AND f.ZORG_OID_REGI = g.OID_REGI
         AND ROWNUM = 1) as UBIGEO,

        (select val_text_comu from mae_clien_comun com where com.CLIE_OID_CLIE=Mc.oid_clie
        and ticm_oid_tipo_comu=3) as Correo,


        (select max(cra1.cod_peri) from SEG_PERIO_CORPO CRA1, CRA_PERIO CRA2, PED_SOLIC_CABEC PED
        where CRA1.OID_PERI=CRA2.PERI_OID_PERI
                  AND CRA2.OID_PERI=PED.perd_oid_peri and clie_oid_clie=Mc.oid_clie and tspa_oid_tipo_soli_pais in (
                  select tsp.oid_tipo_soli_pais
                                from ped_tipo_solic_pais tsp, ped_tipo_solic ts, v_gen_i18n_sicc i
                                where i.idio_oid_idio = 1
                                and i.attr_enti =''PED_TIPO_SOLIC''
                                and i.val_oid = ts.oid_tipo_soli
                                and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                and ts.cod_tipo_soli=''SOC''
                                and tspa_oid_tipo_soli_pais=tsp.oid_tipo_soli_pais
                  ) and cra1.cod_peri='||lsPeriodoAnterior||') as FACTURO_CAMP_ANT,


        (select max(cra1.cod_peri) from SEG_PERIO_CORPO CRA1, CRA_PERIO CRA2, PED_SOLIC_CABEC PED
        where CRA1.OID_PERI=CRA2.PERI_OID_PERI
         AND CRA2.OID_PERI=PED.perd_oid_peri and clie_oid_clie=mc.oid_clie and tspa_oid_tipo_soli_pais in (
                  select tspa.oid_tipo_soli_pais
                                from ped_tipo_solic_pais tspa, ped_tipo_solic ts, v_gen_i18n_sicc i
                                where i.idio_oid_idio = 1
                                and i.attr_enti =''PED_TIPO_SOLIC''
                                and i.val_oid = ts.oid_tipo_soli
                                and tspa.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                and ts.cod_tipo_soli=''SOC''
                                and tspa_oid_tipo_soli_pais=tspa.oid_tipo_soli_pais
                  ) and cra1.cod_peri='||lsPeriodoActual||') as FACTURO_cAMP_ACTUAL,


          (select max(TO_CHAR( fec_fact,''dd/mm/yyyy''))  from SEG_PERIO_CORPO CRA1, CRA_PERIO CRA2, PED_SOLIC_CABEC PED
        where CRA1.OID_PERI=CRA2.PERI_OID_PERI
         AND CRA2.OID_PERI=PED.perd_oid_peri and clie_oid_clie=mc.oid_clie and tspa_oid_tipo_soli_pais IN (select tsp.oid_tipo_soli_pais
  from ped_tipo_solic_pais tsp, ped_tipo_solic ts, v_gen_i18n_sicc i
  where i.idio_oid_idio = 1
  and i.attr_enti =''PED_TIPO_SOLIC''
  and i.val_oid = ts.oid_tipo_soli
  and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
  and ts.cod_tipo_soli=''SOC'') and fec_fact is not null) as FEC_FACT,
  NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_OBS_MOTI_BLOQ(mc.oid_clie)), '''') as des_Bloqueo,
  fam.nombreref as VAL_NOM_REFE,
  fam.val_telf as VAL_TEL_REFE,
  fam.val_celu as VAL_CEL_REFE,
  nofam.nombreref as VAL_NOM_AUX,
  nofam.val_telf as VAL_TEL_AUX,
  nofam.val_celu as VAL_CEL_AUX ';

            cadena02 :=
            ' FROM mae_clien mc,
               mae_clien_ident mci,
               mae_clien_datos_adici mcda,
               mae_clien_tipo_subti mcts,
               mae_clien_clasi mcc,
               mae_clien_bloqu mcb,
               mae_clien_prime_conta mcpc,
               mae_clien_unida_admin mcua,
               mae_clien_direc mcd,
               zon_terri_admin zta,
               zon_terri zt,
               zon_secci zs,
               zon_zona zz,
               zon_regio zr,
              (SELECT val_i18n,
                      val_oid
                 FROM gen_i18n_sicc_comun
                WHERE attr_enti = ''MAE_TIPO_CLIEN'') dmtc,
              (SELECT val_i18n, val_oid
                 FROM gen_i18n_sicc_comun
                WHERE attr_enti = ''MAE_SUBTI_CLIEN'') dmsc,
              (SELECT val_i18n,
                      val_oid
                 FROM gen_i18n_sicc_comun
                WHERE attr_enti = ''MAE_ESTAT_CLIEN'') dmec,
              (SELECT mcc.clie_oid_clie,
                      mcc.val_text_comu telefono
                 FROM mae_clien_comun mcc,
                      mae_tipo_comun mtc
                WHERE mcc.ticm_oid_tipo_comu = mtc.oid_tipo_comu
                  AND mtc.cod_tipo_comu IN (''TF'')
             ORDER BY 1) dtel,
              (SELECT mcc.clie_oid_clie,
                      mcc.val_text_comu telefono
                 FROM mae_clien_comun mcc,
                      mae_tipo_comun mtc
                WHERE mcc.ticm_oid_tipo_comu = mtc.oid_tipo_comu
                  AND mtc.cod_tipo_comu IN (''TM'')
                 ORDER BY 1) dcel,
                 sto_docum_digit       dig,
               int_solic_conso_credi cre,
               cra_perio  peri,
             (select cod_clie, val_ape1 ||'' ''|| val_ape2 ||'' ''|| val_nom1||'' ''|| val_nom2 as nombreref,
               val_telf, val_celu from  mae_refer where tipo_refe = 1 )fam,
             (select cod_clie, val_ape1 ||'' ''|| val_ape2 ||'' ''|| val_nom1||'' ''|| val_nom2 as nombreref,
               val_telf, val_celu from  mae_refer where tipo_refe = 2 ) nofam
            WHERE mc.oid_clie = mcda.clie_oid_clie
            AND mc.oid_clie = mcts.clie_oid_clie
            AND mc.oid_clie = mcua.clie_oid_clie
            AND mc.oid_clie = mcua.clie_oid_clie
            AND mc.oid_Clie = mci.clie_oid_clie
            AND mcpc.clie_oid_clie = mc.oid_clie
            AND mci.val_iden_docu_prin = 1
            AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
            AND mcua.ind_acti = 1
            AND zta.terr_oid_terr = zt.oid_terr
            AND zta.zscc_oid_secc = zs.oid_secc
            AND zs.zzon_oid_zona = zz.oid_zona
            AND zr.oid_regi = zz.zorg_oid_regi
            AND mc.oid_clie = mcd.clie_oid_clie
            AND mcd.ind_dire_ppal = 1
            AND mcd.ind_elim = 0
            AND mc.oid_clie = dtel.clie_oid_clie(+)
            AND mc.oid_clie = dcel.clie_oid_clie(+)
            AND mc.oid_clie = mcb.clie_oid_clie(+)
            AND mc.cod_clie = fam.cod_clie(+)
            AND mc.cod_clie = nofam.cod_clie(+)
            AND mcts.ticl_oid_tipo_clie = dmtc.val_oid
            AND mcts.sbti_oid_subt_clie = dmsc.val_oid
            AND mcda.esta_oid_esta_clie = dmec.val_oid
            AND mcc.ctsu_oid_clie_tipo_subt = mcts.oid_clie_tipo_subt
            AND dig.sec_nume_docu(+) = cre.sec_nume_docu
            AND dig.num_lote(+) = cre.num_lote
            AND dig.ind_envi(+) = ''1''
            AND mc.cod_clie = cre.cod_clie(+)
            AND (SELECT d.val_i18n
                  FROM gen_i18n_sicc_comun d
                 WHERE d.attr_enti = ''MAE_CLASI''
                   AND d.val_oid IN (mcc.clas_oid_clas)) LIKE ''%Consultora%''
            AND mcpc.PERD_OID_PERI = PERI.OID_PERI';

            if (pscondicionfechainicio is null and pscondicionfechafin is null) then
                cadena02 := cadena02 || ' ' || 'and mcpc.perd_oid_peri='||psoidperiodo;
            end if;

            if (pscondicionfechainicio is not null) then
                cadena02 := cadena02 || ' ' || pscondicionfechainicio;
            end if;

            if (pscondicionfechafin is not null) then
                cadena02 := cadena02 || ' ' || pscondicionfechafin;
            end if;

            if (pscondicionregion1 is not null) then
                cadena02 := cadena02 || ' ' || pscondicionregion1;
            end if;

            if (pscondicionzona1 is not null) then
                cadena02 := cadena02 || ' ' || pscondicionzona1;
            end if;

            if (psIndicadorDesbloqueo = '0') then
                cadena02 := cadena02 || ' ' || 'AND mcb.fec_desb IS NULL';
            end if;

        ELSIF pstiporeporte = '1' THEN
           cadena01 :=
            'INSERT INTO mae_tempo_nueva_recha(VAL_OBSE, oid_proc_nuev, cod_peri, cod_clie,
            oid_clie, pre_impr, cod_digi_ctrl, nom_clie, tip_docu, num_docu_iden,
            fec_crea, hor_crea, des_esta, des_moti, des_bloq, ind_acti, tip_clie,
            sub_tipo_clie, tip_clasi, des_clas, cod_regi, cod_zona, cod_secc,
            cod_terr, val_dire, val_cod_post, num_tele, num_celu, cod_reco, nom_reco, ind_lide,
            val_moti_rech, cam_crea, ind_cier, fec_ingr_soli, usu_gest, fec_gest,
            fec_fact_regi, fec_prim_fact, rem_prim_fact, ind_impr_docu,
            DESC_NAC,
            VAL_COD_SEXO,
            val_ocr_tdoc,
            VAL_OCR_ESTA,
            DESC_TIPO_PERSONA,
            DESC_ORIGEN_INGRESO,
            VAL_NOM_BARR,
            VAL_NOM_MANZ,
            VAL_ETA_CONJ,
            VAL_CAL_PRIN,
            VAL_CAL_SECU)
            SELECT
               MCD.VAL_OBSE,
               '||psoidproceso||' id_proc_nueva,
               (SELECT MAX (cod_peri)
                  FROM INT_SOLIC_CONSO_CREDI
                 WHERE num_docu_iden = mci.num_docu_iden) campana,
               mc.cod_clie AS codigo_cliente, mc.oid_clie AS oid_cliente,
               (SELECT MAX (num_docu)
                  FROM INT_SOLIC_CONSO_CREDI
                 WHERE num_docu_iden = mci.num_docu_iden) preimpreso,
               mc.cod_digi_ctrl,
               (TRIM (mc.val_ape1) || '' ''
                || TRIM (mc.val_ape2) || '', ''
                || TRIM (mc.val_nom1) || '' ''
                || TRIM (mc.val_nom2)
               ) AS nombre,
               (SELECT d.val_i18n
                  FROM gen_i18n_sicc_pais d
                 WHERE d.attr_enti = ''MAE_TIPO_DOCUM''
                   AND d.val_oid = mci.tdoc_oid_tipo_docu) AS tipo_doc,
               mci.num_docu_iden, TO_CHAR (mc.fec_crea, ''dd/mm/yyyy'') AS fec_crea,to_char(mc.fec_crea,''HH12:MI PM'') as hor_crea,
               dmec.val_i18n AS status, NULL AS motivo,
               NVL((MAE_PKG_REPOR.MAE_FN_DEVUE_BLOQ_X_CLIE(mc.oid_clie)), '''') AS bloqueo,
               DECODE (mcda.ind_acti, 0, ''No'', ''Si'') AS activo,
               dmtc.val_i18n AS tipo_cliente, dmsc.val_i18n AS subtipo_cliente,
               (SELECT d.val_i18n
                  FROM gen_i18n_sicc_comun d
                 WHERE d.attr_enti = ''MAE_CLASI''
                   AND d.val_oid IN (mcc.clas_oid_clas)) tipo_clasif,
               (SELECT d.val_i18n
                  FROM gen_i18n_sicc_comun d
                 WHERE d.attr_enti = ''MAE_TIPO_CLASI_CLIEN''
                   AND d.val_oid IN (mcc.tccl_oid_tipo_clasi)) clasif,
               zr.des_regi AS region, zz.des_zona AS zona, zs.cod_secc AS seccion,
               to_char(zt.cod_terr) AS territorio,
               ( (
                   SELECT NVL(STV.DES_ABRV_TIPO_VIA, '' '')
                   FROM   OWN_COMUN.SEG_TIPO_VIA STV
                   WHERE  STV.OID_TIPO_VIA = mcd.TIVI_OID_TIPO_VIA
                 ) || '' '' ||
                 mcd.VAL_NOMB_VIA || '', '' ||
                 mcd.VAL_OBSE || '', '' ||
                 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(mc.oid_clie,''DES_URBA'') || '', '' ||
                 mcd.num_ppal
               ) AS DIRECCION,
               mcd.val_cod_post,
               dtel.telefono, dcel.telefono AS celular,
               (SELECT (SELECT c.cod_clie
                          FROM mae_clien c
                         WHERE c.oid_clie = b.clie_oid_clie_vnte) AS cod_clie
                  FROM mae_clien_vincu b
                 WHERE b.clie_oid_clie_vndo = mc.oid_clie
                   AND b.tivc_oid_tipo_vinc = 9
                 ) AS cod_recom,
               (SELECT (SELECT (TRIM(c.val_ape1)|| '' ''
                                || TRIM(c.val_ape2)|| '', ''
                                || TRIM(c.val_nom1)|| '' ''
                                || TRIM(c.val_nom2)
                               ) AS nombre
                          FROM mae_clien c
                         WHERE c.oid_clie = b.clie_oid_clie_vnte) AS cod_clie
                  FROM mae_clien_vincu b
                 WHERE b.clie_oid_clie_vndo = mc.oid_clie
                   AND b.tivc_oid_tipo_vinc = 9) AS nomb_recom,';

           cadena02 :=
              'CASE
               WHEN ((SELECT b.clie_oid_clie_vnte
                        FROM mae_clien_vincu b
                       WHERE b.clie_oid_clie_vndo = mc.oid_clie
                         AND b.tivc_oid_tipo_vinc = 9) = zs.clie_oid_clie) THEN
                ''SI''
               WHEN (SELECT b.clie_oid_clie_vnte
                       FROM mae_clien_vincu b
                      WHERE b.clie_oid_clie_vndo = mc.oid_clie
                        AND b.tivc_oid_tipo_vinc = 9) IS NULL THEN
                ''NO''
               ELSE
                ''NO''
             END lider,
               NULL VAL_MOTI_RECH,
               GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(mcpc.perd_oid_peri) campana_creacion,
           DECODE((MAE_PKG_PROCE_CLIEN.MAE_FN_VALID_CIERR_REGIO('||psoidpais||','||psoidperiodo||', zr.oid_regi, '||psoidactividad||', mc.fec_crea)),''N'',''SI'',''NO'')  IND_CIERRE,
               cre.fec_hora_crea_soli fec_ingre_solic,
               dig.usu_modi usu_gest,
               TO_CHAR(dig.fec_modi, ''DD/MM/YYYY hh24:mm:ss'') fec_gest,
               TO_CHAR((SELECT MIN(FEC_INIC)
                  from cra_crono cr
                 where cr.perd_oid_peri = '||psoidperiodo||'
                   and cr.cact_oid_acti =
                       (select oid_acti
                          from cra_activ
                         where cod_acti = ''FA''
                           and pais_oid_pais = '||psoidpais||')
                   and cr.zzon_oid_zona IN
                       (SELECT OID_ZONA
                          FROM ZON_ZONA
                         WHERE ZORG_OID_REGI = zr.oid_regi)), ''DD/MM/YYYY'') as fec_fact_regi,
               TO_CHAR((select min(soca.fec_fact)
                        from ped_solic_cabec     soca,
                             ped_solic_cabec     cons,
                             ped_tipo_solic_pais tspa,
                             ped_tipo_solic      tsol
                 where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                           and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                           and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                           and tsol.cod_tipo_soli = ''SOC''
                           and soca.clie_oid_clie = mc.oid_clie
                           and soca.perd_oid_peri = '||psoidperiodo||'
                           and soca.grpr_oid_grup_proc = 5
                   and cons.esso_oid_esta_soli != 4), ''DD/MM/YYYY'') as fec_prim_fact,
               NVL((select abs((select min(soca.fec_fact)
                  from ped_solic_cabec     soca,
                       ped_solic_cabec     cons,
                       ped_tipo_solic_pais tspa,
                       ped_tipo_solic      tsol
                 where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                   and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   and tsol.cod_tipo_soli = ''SOC''
                   and soca.clie_oid_clie = mc.oid_clie
                   and soca.perd_oid_peri = '||psoidperiodo||'
                   and soca.grpr_oid_grup_proc = 5
                   and cons.esso_oid_esta_soli != 4) - (SELECT MIN(FEC_INIC)
                              from cra_crono cr
                             where cr.perd_oid_peri = '||psoidperiodo||'
                   and cr.cact_oid_acti =
                       (select oid_acti
                                                         from cra_activ
                                                        where cod_acti = ''FA''
                           and pais_oid_pais = '||psoidpais||')
                   and cr.zzon_oid_zona IN
                       (SELECT OID_ZONA
                          FROM ZON_ZONA
                         WHERE ZORG_OID_REGI = zr.oid_regi)))
                  from dual),0) as REM_PRIM_FACT,
               NULL as ind_impr_docu, ';

         cadena05 := '( SELECT  I18N.VAL_I18N AS DESCRIPCION
        FROM mae_clien_datos_adici mda, SEG_NACIO NAC, V_GEN_I18N_SICC I18N
       WHERE mda.snon_oid_naci=nac.oid_naci
         AND NAC.OID_NACI = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''SEG_NACIO''
         AND MDA.CLIE_OID_CLIE = mc.oid_clie and rownum=1) as nacionalidad   ,

   (decode(mc.cod_sexo, ''F'', ''Femenino'', ''Masculino'' )) AS sexo,

   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu
   and mci.clie_oid_clie = mc.oid_clie and rownum=1) as tipo_documento_identidad ,


   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI
    AND MCDA.CLIE_OID_CLIE= mc.oid_clie and rownum=1) AS estado_civil ,


    (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
         and TP.OID_TIPO_PERS = I18N.VAL_OID
         AND I18N.IDIO_OID_IDIO = 1
         AND I18N.ATTR_ENTI = ''MAE_TIPO_PERSO''
         and mcda.clie_oid_clie= mc.oid_clie and rownum=1) AS tipo_persona,


     (SELECT  I18N.VAL_I18N
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID
	       AND I18N.IDIO_OID_IDIO = 1
	       AND I18N.ATTR_ENTI = ''MAE_ORIG_INGRE''
	    and mcda.clie_oid_clie= mc.oid_clie and rownum=1) AS origen_ingreso,


      ( select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = mc.oid_clie and rownum=1) AS nombre_barrio,
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = mc.oid_clie and rownum=1) as manzana_letra,
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  mc.oid_clie and rownum=1) as etapa_conjunto,
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =mc.oid_clie and rownum=1) as calle_principal,
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =mc.oid_clie and rownum=1)  as calle_secundaria

          FROM mae_clien mc,
               mae_clien_ident mci,
               mae_clien_datos_adici mcda,
               mae_clien_tipo_subti mcts,
               mae_clien_clasi mcc,
               mae_clien_bloqu mcb,
               mae_clien_unida_admin mcua,
               mae_clien_prime_conta mcpc,
               mae_clien_direc mcd,
               zon_terri_admin zta,
               zon_terri zt,
               zon_secci zs,
               zon_zona zz,
               zon_regio zr,
               (SELECT val_i18n, val_oid
                  FROM gen_i18n_sicc_comun
                 WHERE attr_enti = ''MAE_TIPO_CLIEN'') dmtc,
               (SELECT val_i18n, val_oid
                  FROM gen_i18n_sicc_comun
                 WHERE attr_enti = ''MAE_SUBTI_CLIEN'') dmsc,
               (SELECT val_i18n, val_oid
                  FROM gen_i18n_sicc_comun
                 WHERE attr_enti = ''MAE_ESTAT_CLIEN'') dmec,
               (SELECT   mcc.clie_oid_clie, mcc.val_text_comu telefono
                    FROM mae_clien_comun mcc, mae_tipo_comun mtc
                   WHERE mcc.ticm_oid_tipo_comu = mtc.oid_tipo_comu
                     AND mtc.cod_tipo_comu IN (''TF'')
                ORDER BY 1) dtel,
               (SELECT   mcc.clie_oid_clie, mcc.val_text_comu telefono
                    FROM mae_clien_comun mcc, mae_tipo_comun mtc
                   WHERE mcc.ticm_oid_tipo_comu = mtc.oid_tipo_comu
                     AND mtc.cod_tipo_comu IN (''TM'')
                ORDER BY 1) dcel,
                sto_docum_digit       dig,
                int_solic_conso_credi cre
         WHERE mc.oid_clie = mcda.clie_oid_clie
           AND mc.oid_clie = mcts.clie_oid_clie
           AND mc.oid_clie = mcua.clie_oid_clie
           AND mc.oid_clie = mcua.clie_oid_clie
           AND mc.oid_clie = mci.clie_oid_clie
           AND mcpc.clie_oid_clie = mc.oid_clie
           AND mci.val_iden_docu_prin = 1
           AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
           AND mcua.ind_acti = 1
           AND zta.terr_oid_terr = zt.oid_terr
           AND zta.zscc_oid_secc = zs.oid_secc
           AND zs.zzon_oid_zona = zz.oid_zona
           AND mc.oid_clie = mcd.clie_oid_clie
           AND mcd.ind_dire_ppal = 1
           AND mcd.ind_elim = 0
           AND mc.oid_clie = dtel.clie_oid_clie(+)
           AND mc.oid_clie = dcel.clie_oid_clie(+)
           AND mc.oid_clie = mcb.clie_oid_clie(+)
           AND mcb.fec_desb IS NULL
           AND mcts.ticl_oid_tipo_clie = dmtc.val_oid
           AND mcts.sbti_oid_subt_clie = dmsc.val_oid
           AND mcda.esta_oid_esta_clie = dmec.val_oid
           AND mcc.ctsu_oid_clie_tipo_subt = mcts.oid_clie_tipo_subt
           AND dig.sec_nume_docu(+) = cre.sec_nume_docu
           AND dig.num_lote(+) = cre.num_lote
           AND dig.ind_envi(+) = ''1''
           AND mc.cod_clie = cre.cod_clie(+)';

         cadena03 :='
           AND (SELECT d.val_i18n
                  FROM gen_i18n_sicc_comun d
                 WHERE d.attr_enti = ''MAE_CLASI''
                       AND d.val_oid IN (mcc.clas_oid_clas)) LIKE ''%Consultora%''
           AND zz.zorg_oid_regi = zr.oid_regi ';

         if (pscondicionfechainicio is not null) then
            cadena03 := cadena03 || ' ' || pscondicionfechainicio;
         end if;

         if (pscondicionfechafin is not null) then
            cadena03 := cadena03 || ' ' || pscondicionfechafin;
         end if;

         if (pscondicionregion1 is not null) then
            cadena03 := cadena03 || ' ' || pscondicionregion1;
         end if;

         if (pscondicionzona1 is not null) then
            cadena03 := cadena03 || ' ' || pscondicionzona1;
         end if;

         cadena03 := cadena03 || ' UNION ALL
         SELECT  NULL AS VAL_OBSE, '||psoidproceso||' id_proc_nueva, a.cod_peri, a.cod_clie AS codigo_cliente, NULL AS oid_cliente,
                 NULL AS cod_digi_ctrl, b.num_docu,
                 (   TRIM (a.val_ape1) || '' ''
                  || TRIM (a.val_ape2) || '', ''
                  || TRIM (a.val_nom1) || '' ''
                  || TRIM (a.val_nom2)
                 ) AS nombre,
                 (SELECT d.val_i18n
                    FROM gen_i18n_sicc_pais d
                   WHERE d.attr_enti = ''MAE_TIPO_DOCUM''
                     AND d.val_oid IN (SELECT j.oid_tipo_docu
                                         FROM mae_tipo_docum j
                                        WHERE j.cod_tipo_docu = a.tip_docu)) AS tipo_doc,
                 a.num_docu_iden, TO_CHAR (b.fec_digi, ''DD/MM/YYYY'') AS fec_crea,TO_CHAR(b.fec_digi,''HH12:MI PM'')AS hor_crea,
                 ''RECHAZADA'' AS status, b.val_obse_rech_defi motivo, NULL AS bloqueo,
                 NULL AS activo, NULL AS tipo_cliente, NULL AS subtipo_cliente,
                 NULL AS tipo_clasif, NULL AS clasif,
                 (select zr.des_regi from zon_regio zr where
                    zr.cod_regi= SUBSTR (a.uni_admi, 1, 2)) AS region,
                 (select zz.des_zona from zon_zona zz where
                    zz.cod_zona = SUBSTR (a.uni_admi, 3, 4)) AS zona,
                 SUBSTR (a.uni_admi, 5, 1),
                 SUBSTR (a.uni_admi, 8, 6),
                 a.val_dire_clie AS direccion, a.VAL_CODI_POST_CLIE, a.val_telf_clie AS telefono,
                 a.val_celu_clie AS celular, a.cod_clie_rete AS cod_recom,
                 TRIM (c.val_ape1) || '' ''
                          || TRIM (c.val_ape2) || '', ''
                          || TRIM (c.val_nom1) || '' ''
                          || TRIM (c.val_nom2),
                   DECODE((select count(1) from zon_secci s
            where s.ind_acti!=9
            and s.ind_borr =0
            and s.clie_oid_clie=c.oid_clie),0,''NO'',''SI'' ),
                 (SELECT des_moti_rech
                  FROM sto_recha_motiv r
                 WHERE r.cod_tipo_docu = b.cod_tipo_docu
                   AND r.cod_moti_rech = b.cod_moti_rech),
                 ''NO'',
                 ''NO'',
                 a.FEC_HORA_CREA_SOLI,
                 b.USU_MODI,
                 TO_CHAR(b.FEC_MODI, ''DD/MM/YYYY hh24:mm:ss''),
                 TO_CHAR((SELECT MIN(FEC_INIC)
                  from cra_crono cr
                 where cr.perd_oid_peri = '||psoidperiodo||'
                   and cr.cact_oid_acti =
                       (select oid_acti
                          from cra_activ
                         where cod_acti = ''FA''
                           and pais_oid_pais = '||psoidpais||')
                   and cr.zzon_oid_zona IN
                       (SELECT OID_ZONA
                          FROM ZON_ZONA
                         where cod_zona = SUBSTR(a.uni_admi, 3, 4))), ''DD/MM/YYYY'') as fec_fact_regi,
                 TO_CHAR((select min(soca.fec_fact)
  from ped_solic_cabec     soca,
       ped_solic_cabec     cons,
       ped_tipo_solic_pais tspa,
       ped_tipo_solic      tsol
                 where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
   and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
   and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
   and tsol.cod_tipo_soli = ''SOC''
   and soca.clie_oid_clie = c.oid_clie
   and soca.perd_oid_peri = '||psoidperiodo||'
   and soca.grpr_oid_grup_proc = 5
                   and cons.esso_oid_esta_soli != 4), ''DD/MM/YYYY''),
                 NVL((select abs( (SELECT MIN(FEC_INIC)
                  from cra_crono cr
                 where cr.perd_oid_peri = '||psoidperiodo||'
                   and cr.cact_oid_acti =
                       (select oid_acti
                                             from cra_activ
                                            where cod_acti = ''FA''
                           and pais_oid_pais = '||psoidpais||')
                   and cr.zzon_oid_zona IN
                       (SELECT OID_ZONA
                          FROM ZON_ZONA
                         where cod_zona = SUBSTR(a.uni_admi, 3, 4))) - (select min(soca.fec_fact)
                  from ped_solic_cabec     soca,
                       ped_solic_cabec     cons,
                       ped_tipo_solic_pais tspa,
                       ped_tipo_solic      tsol
                 where soca.soca_oid_soli_cabe = cons.oid_soli_cabe
                   and soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                   and tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                   and tsol.cod_tipo_soli = ''SOC''
                   and soca.clie_oid_clie = c.oid_clie
                   and soca.perd_oid_peri = '||psoidperiodo||'
                   and soca.grpr_oid_grup_proc = 5
                   and cons.esso_oid_esta_soli != 4)) from dual),0),
               NULL,
               (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mda, SEG_NACIO NAC, V_GEN_I18N_SICC I18N
       WHERE mda.snon_oid_naci=nac.oid_naci
         AND NAC.OID_NACI = I18N.VAL_OID AND I18N.IDIO_OID_IDIO = 1 AND I18N.ATTR_ENTI = ''SEG_NACIO'' AND MDA.CLIE_OID_CLIE = c.oid_clie and rownum=1),
   (decode(c.cod_sexo, ''F'', ''Femenino'', ''Masculino'' )),
   (select mtd.val_ocr_tdoc from
   MAE_TIPO_DOCUM mtd, mae_clien_ident mci
   where mtd.oid_tipo_docu=mci.tdoc_oid_tipo_docu and mci.clie_oid_clie = c.oid_clie and rownum=1),
   (SELECT MEC.VAL_OCR_ESTA FROM MAE_ESTAD_CIVIL MEC , mae_clien_datos_adici MCDA
    WHERE MEC.OID_ESTA_CIVI=MCDA.ESCV_OID_ESTA_CIVI AND MCDA.CLIE_OID_CLIE= c.oid_clie and rownum=1),
    (SELECT  I18N.VAL_I18N
        FROM mae_clien_datos_adici mcda, MAE_TIPO_PERSO TP, V_GEN_I18N_SICC I18N
       WHERE mcda.TPES_OID_TIPO_PERS=tp.oid_tipo_pers
       and TP.OID_TIPO_PERS = I18N.VAL_OID AND I18N.IDIO_OID_IDIO = 1 AND I18N.ATTR_ENTI = ''MAE_TIPO_PERSO'' and mcda.clie_oid_clie= c.oid_clie and rownum=1),
     (SELECT  I18N.VAL_I18N
	      FROM mae_clien_datos_adici mcda, MAE_ORIG_INGRE OI, V_GEN_I18N_SICC I18N
	     WHERE mcda.ORIN_OID_ORIG_INGR=OI.OID_ORIG_INGR
       AND OI.OID_ORIG_INGR = I18N.VAL_OID AND I18N.IDIO_OID_IDIO = 1 AND I18N.ATTR_ENTI = ''MAE_ORIG_INGRE'' and mcda.clie_oid_clie= c.oid_clie and rownum=1),
      (select MCD.VAL_NOM_BARR from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = c.oid_clie and rownum=1),
      (select MCD.VAL_NOM_MANZ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE = c.oid_clie and rownum=1),
      (select MCD.VAL_ETA_CONJ from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =  c.oid_clie and rownum=1),
      (select MCD.VAL_CAL_PRIN from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =c.oid_clie and rownum=1),
       (select MCD.VAL_CAL_SECU from mae_clien_direc MCD WHERE MCD.CLIE_OID_CLIE =c.oid_clie and rownum=1)
            FROM INT_SOLIC_CONSO_CREDI a,
                 STO_DOCUM_DIGIT b,
                 mae_clien c,
                 mae_clien_prime_conta mcpc
           WHERE a.num_lote = b.num_lote
             AND a.sec_nume_docu = b.sec_nume_docu
             and c.cod_clie(+) = a.cod_clie_rete
             AND mcpc.clie_oid_clie(+) = c.oid_clie
             AND b.ind_rech = ''1'' ';

             if (pscondicionfechainicio2 is not null) then
                cadena03 := cadena03 || ' ' || pscondicionfechainicio2;
             end if;

             if (pscondicionfechafin2 is not null) then
                cadena03 := cadena03 || ' ' || pscondicionfechafin2;
             end if;

             if (pscondicionregion2 is not null) then
                cadena03 := cadena03 || ' ' || pscondicionregion2;
             end if;

             if (pscondicionzona2 is not null) then
                cadena03 := cadena03 || ' ' || pscondicionzona2;
             end if;

             cadena03 := cadena03 || ' ORDER BY 1 ASC ';
        ELSE
            RETURN;
        END IF;
        /*
       INSERT INTO MAE_TMP_BLOB(VAL_CADE1, VAL_CADE2, VAL_CADE3, VAL_CADE4, VAL_CADE5, VAL_CADE6)
        VALUES
        (cadena01, cadena04 , cadena06, cadena02 , cadena05 , cadena03);
        COMMIT;
        */
        EXECUTE IMMEDIATE cadena01 || ' ' || cadena04 || ' ' || cadena06 || ' ' || cadena02 || ' ' || cadena05 || ' ' || cadena03;

    EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123, 'ERROR MAE_PR_GENER_TEMPO_NUEVA_CSV' || ls_sqlerrm);
    END mae_pr_gener_tempo_nueva_csv;

  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al Reporte de Nuevas
  Fecha Creacion    : 02/09/2015
  Autor             : Karina Valencia
  Parametros        :
    pscodigopais       codigo de pais
    pscodigousuario    codigo de usuario
    psnombrearchivo    nombre de archivo
    pstitulo           titulo del archivo
    psdirectorio       directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mae_pr_gener_repor_nueva_csv
  (
    pscodigopais        VARCHAR2,
    pscodigousuario     VARCHAR2,
    psoidproceso        NUMBER,
    psnombrearchivo     VARCHAR2,
    pstiporeporte       VARCHAR2,
    pstitulo            VARCHAR2,
    psdirectorio        OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         NUMBER := 5000;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(4000);

    CURSOR c_nueva_rechazada IS
    SELECT * FROM mae_tempo_nueva_recha WHERE oid_proc_nuev = psoidproceso;

    CURSOR c_nueva_pedido_consultora IS
    SELECT * FROM mae_tempo_consu_pedid WHERE cod_usua = pscodigousuario;

	TYPE nuevarechazadatab IS TABLE OF mae_tempo_nueva_recha%ROWTYPE;
	nuevarechazadarecord nuevarechazadatab;

	TYPE pedidoconsultoratab IS TABLE OF mae_tempo_consu_pedid%ROWTYPE;
	pedidoconsultorarecord pedidoconsultoratab;

    lbCabecera BOOLEAN;
    v_para varchar2(150);
    lsFecha  VARCHAR2(10);
  BEGIN

    v_para := NULL;
    lbCabecera := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';


     BEGIN
           SELECT val_para INTO v_para
           FROM BAS_PARAM_PAIS
           WHERE COD_SIST = 'MAE'
           AND NOM_PARA = 'indCamposAdicionales'
           AND cod_pais = pscodigopais;
     EXCEPTION WHEN NO_DATA_FOUND THEN
          v_para:=NULL;
     END;


    gen_pkg_inter_archi.gen_pr_inici_repor_oracl(pscodigopais,
                                                 psnombrearchivo,
                                                 '.csv',
                                                 pstitulo,
                                                 lsdirtempo,
                                                 v_handle);
    psdirectorio   := lsdirtempo;

    IF pstiporeporte ='0' THEN
        OPEN c_nueva_rechazada;
        LOOP
          FETCH c_nueva_rechazada BULK COLLECT
            INTO nuevarechazadarecord LIMIT w_filas;
              IF nuevarechazadarecord.count > 0 THEN
                FOR x IN nuevarechazadarecord.first .. nuevarechazadarecord.last
                LOOP

                 IF (v_para = '1' ) THEN
                    IF lbCabecera THEN



                       lslinea :=   '"Cliente",' ||
                                    '"Codigo Dig. Control",' ||
                                    '"Nombre",' ||
                                    '"Tipo Documento",' ||
                                    '"Documento Identidad",' ||
                                    '"Campaña de Creación",' ||
                                    '"Fecha Creacion",' ||
                                    '"Estado",' ||
                                    '"Bloqueo",' ||
                                    '"Observacion Bloqueo",' ||
                                    '"Cantidad Bloqueo",' ||
                                    '"Activo",' ||
                                    '"Tipo Cliente",' ||
                                    '"Sub Tipo Cliente",' ||
                                    '"Tipo Clasificacion",' ||
                                    '"Clasificacion",' ||
                                    '"Región",' ||
                                    '"Zona",' ||
                                    '"Sección",' ||
                                    '"Territorio",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Teléfono",' ||
                                    '"Celular",' ||
                                    '"Codigo Recomendante",' ||
                                    '"Nombre Recomendante",' ||
                                    '"Es Lider",' ||
                                    '"Creada despues del cierre",' ||
                                    '"Fecha Hora Ingreso Solicitud",' ||
                                    '"Usuario Gestión",' ||
                                    '"Fecha Hora Gestión",' ||
                                    '"Sexo",' ||
                                    '"Fecha Primera Facturacion",' ||
                                    '"Remesa Primera Facturacion",' ||
                                    '"Fecha 1ra Facturacion Region",' ||
                                    '"¿Facturación Electrónica?",' ||
                                    '"Campaña Primer Contacto",' ||
                                    '"Campaña Primer Pedido",' ||
                                    '"Ubigeo",' ||
                                    '"Correo Electrónico",' ||
                                    '"Pedido Campaña Anterior",' ||
                                    '"Pedido Campaña Actual",' ||
                                    '"Fecha Facturación",' ||
                                    '"Nacionalidad",' ||
                                    '"Sexo",' ||
                                    '"Tipo Documento Identidad",' ||
                                    '"Estado Civil",' ||
                                    '"Tipo Persona",' ||
                                    '"Origen del Ingreso",' ||
                                    '"Barrio",' ||
                                    '"Manzana/Letra",' ||
                                    '"Etapa/Conjunto",' ||
                                    '"Calle Principal",' ||
                                    '"Calle Secundaria",' ||
                                    '"Dirección Completa",' ||
                                    '"Campaña Activación",' ||
                                    '"Fecha Activación",' ||
                                    '"Usuario Activación"'
                                    ;

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;
                    lsFecha := to_char(nuevarechazadarecord(x).fec_acti, 'dd/mm/yyyy');
                    lslinea :=  '=T("'|| nuevarechazadarecord(x).cod_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_digi_ctrl || '")' || ',' ||
                                   '"'|| nuevarechazadarecord(x).nom_clie || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_docu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_docu_iden || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cam_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_esta || '")' || ',' ||
                                '"'|| nuevarechazadarecord(x).des_bloq || '"' || ',' ||
                                '"'|| nuevarechazadarecord(x).DES_OBSE_BLOQ || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_cant_bloq || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_acti || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).sub_tipo_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_clasi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_clas || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_regi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_zona || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_secc || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_terr || '")' || ',' ||
                                   '"'|| replace(nuevarechazadarecord(x).val_dire,'-',' ') || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_cod_post || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_tele || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_celu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_reco || '")' || ',' ||
                                   '"'|| nuevarechazadarecord(x).nom_reco || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_lide || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_cier || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_ingr_soli || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).usu_gest || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_gest || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_sexo || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_prim_fact || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).rem_prim_fact || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_fact_regi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_impr_docu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).CAM_PRIM_CONT || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).CAM_PRIM_PEDI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DES_UBIG || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DES_EMAI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).CAM_ANTE_PEDI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).CAM_ACTU_PEDI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).FEC_FACT || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DESC_NAC || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_COD_SEXO || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_ocr_tdoc || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_OCR_ESTA || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DESC_TIPO_PERSONA || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DESC_ORIGEN_INGRESO || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_NOM_BARR || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_NOM_MANZ || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_ETA_CONJ || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_CAL_PRIN || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_CAL_SECU || '")' || ',' ||

                                '"'|| nvl(nuevarechazadarecord(x).VAL_NOM_BARR,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_NOM_MANZ,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_ETA_CONJ,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_CAL_PRIN,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_CAL_SECU,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_OBSE,'')
                                    ||'"' || ',' ||
                                    
                                '"'|| nuevarechazadarecord(x).cam_acti || '"' || ',' ||
                                '"'|| lsFecha || '"' || ',' ||
                                '"'|| nuevarechazadarecord(x).usu_acti || '"' ;
                                
                    utl_file.put_line(v_handle,lslinea);
                 ELSE
                      IF lbCabecera THEN



                       lslinea :=   '"Cliente",' ||
                                    '"Codigo Dig. Control",' ||
                                    '"Nombre",' ||
                                    '"Tipo Documento",' ||
                                    '"Documento Identidad",' ||
                                    '"Campaña de Creación",' ||
                                    '"Fecha Creacion",' ||
                                    '"Estado",' ||
                                    '"Bloqueo",' ||
                                    '"Observacion Bloqueo",' ||
                                    '"Cantidad Bloqueo",' ||
                                    '"Activo",' ||
                                    '"Tipo Cliente",' ||
                                    '"Sub Tipo Cliente",' ||
                                    '"Tipo Clasificacion",' ||
                                    '"Clasificacion",' ||
                                    '"Región",' ||
                                    '"Zona",' ||
                                    '"Sección",' ||
                                    '"Territorio",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Teléfono",' ||
                                    '"Celular",' ||
                                    '"Codigo Recomendante",' ||
                                    '"Nombre Recomendante",' ||
                                    '"Es Lider",' ||
                                    '"Creada despues del cierre",' ||
                                    '"Fecha Hora Ingreso Solicitud",' ||
                                    '"Usuario Gestión",' ||
                                    '"Fecha Hora Gestión",' ||
                                    '"Sexo",' ||
                                    '"Fecha Primera Facturacion",' ||
                                    '"Remesa Primera Facturacion",' ||
                                    '"Fecha 1ra Facturacion Region",' ||
                                    '"¿Facturación Electrónica?",' ||
                                    '"Campaña Primer Contacto",' ||
                                    '"Campaña Primer Pedido",' ||
                                    '"Ubigeo",' ||
                                    '"Correo Electrónico",' ||
                                    '"Pedido Campaña Anterior",' ||
                                    '"Pedido Campaña Actual",' ||
                                    '"Fecha Facturación",' ||
                                    '"Campaña Activación",' ||
                                    '"Fecha Activación",' ||
                                    '"Usuario Activación"'
                                    ;

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;
                    
                    lsFecha := to_char(nuevarechazadarecord(x).fec_acti, 'dd/mm/yyyy');
                    lslinea :=  '=T("'|| nuevarechazadarecord(x).cod_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_digi_ctrl || '")' || ',' ||
                                   '"'|| nuevarechazadarecord(x).nom_clie || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_docu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_docu_iden || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cam_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_esta || '")' || ',' ||
                                '"'|| nuevarechazadarecord(x).des_bloq || '"' || ',' ||
                                '"'|| nuevarechazadarecord(x).DES_OBSE_BLOQ || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_cant_bloq || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_acti || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).sub_tipo_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_clasi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_clas || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_regi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_zona || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_secc || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_terr || '")' || ',' ||
                                   '"'|| replace(nuevarechazadarecord(x).val_dire,'-',' ') || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_cod_post || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_tele || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_celu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_reco || '")' || ',' ||
                                   '"'|| nuevarechazadarecord(x).nom_reco || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_lide || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_cier || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_ingr_soli || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).usu_gest || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_gest || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_sexo || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_prim_fact || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).rem_prim_fact || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_fact_regi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_impr_docu || '")'  || ',' ||
                                '=T("'|| nuevarechazadarecord(x).CAM_PRIM_CONT || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).CAM_PRIM_PEDI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DES_UBIG || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DES_EMAI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).CAM_ANTE_PEDI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).CAM_ACTU_PEDI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).FEC_FACT || '")' || ',' ||
                                    
                                '"'|| nuevarechazadarecord(x).cam_acti || '"' || ',' ||
                                '"'|| lsFecha || '"' || ',' ||
                                '"'|| nuevarechazadarecord(x).usu_acti || '"' ;
                    utl_file.put_line(v_handle,lslinea);
                 END IF;


                END LOOP;
              END IF;
          EXIT WHEN c_nueva_rechazada%NOTFOUND;
        END LOOP;
        CLOSE c_nueva_rechazada;

    ELSIF pstiporeporte = '7' THEN
     OPEN c_nueva_rechazada;
        LOOP
          FETCH c_nueva_rechazada BULK COLLECT
            INTO nuevarechazadarecord LIMIT w_filas;
              IF nuevarechazadarecord.count > 0 THEN
                FOR x IN nuevarechazadarecord.first .. nuevarechazadarecord.last
                LOOP
                   IF lbCabecera THEN

                       lslinea :=   '"Cliente",' ||
                                    '"Campaña Primer Pedido",' ||
                                    '"Fecha Facturación",' ||
                                    '"Nombre",' ||
                                    '"Documento Identidad",' ||
                                    '"Zona",' ||
                                    '"Sección",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Teléfono",' ||
                                    '"Celular",' ||
                                    '"Correo Electrónico",' ||
                                    '"Nombre Ref. Familiar",' ||
                                    '"Telef. Casa",' ||
                                    '"Telef. Celular",' ||
                                    '"Nombre Ref. NO Familiar",' ||
                                    '"Telef. Casa",' ||
                                    '"Telef. Celular"'
                                    ;

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :=  '=T("'|| nuevarechazadarecord(x).cod_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).CAM_PRIM_PEDI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).FEC_FACT || '")' || ',' ||
                                   '"'|| nuevarechazadarecord(x).nom_clie || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_docu_iden || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_zona || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_secc || '")' || ',' ||
                                   '"'|| replace(nuevarechazadarecord(x).val_dire_entr,'-',' ') || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_cod_post || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_tele || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_celu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DES_EMAI || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_NOM_REFE || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_TEL_REFE || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_CEL_REFE || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_NOM_AUX || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_TEL_AUX || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_CEL_AUX || '")';

                    utl_file.put_line(v_handle,lslinea);
         END LOOP;
         END IF;
         EXIT WHEN c_nueva_rechazada%NOTFOUND;
        END LOOP;
        CLOSE c_nueva_rechazada;

    ELSIF pstiporeporte = '1' THEN
        OPEN c_nueva_rechazada;
        LOOP
          FETCH c_nueva_rechazada BULK COLLECT
            INTO nuevarechazadarecord LIMIT w_filas;
              IF nuevarechazadarecord.count > 0 THEN
                FOR x IN nuevarechazadarecord.first .. nuevarechazadarecord.last
                LOOP

                 IF (v_para = '1' ) THEN
                    IF lbCabecera THEN
                        lslinea :=  '"Campaña",' ||
                                    '"Cliente",' ||
                                    '"Número de Preimpreso",' ||
                                    '"Codigo Dig. Control",' ||
                                    '"Nombre",' ||
                                    '"Tipo Documento",' ||
                                    '"Documento Identidad",' ||
                                    '"Fecha Creacion",' ||
                                    '"Hora Creación",' ||
                                    '"Estado",' ||
                                    '"Motivo",' ||
                                    '"Bloqueo",' ||
                                    '"Activo",' ||
                                    '"Tipo Cliente",' ||
                                    '"Sub Tipo Cliente",' ||
                                    '"Tipo Clasificacion",' ||
                                    '"Clasificacion",' ||
                                    '"Región",' ||
                                    '"Zona",' ||
                                    '"Sección",' ||
                                    '"Territorio",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Teléfono",' ||
                                    '"Celular",' ||
                                    '"Codigo Recomendante",' ||
                                    '"Nombre Recomendante",' ||
                                    '"Es Lider",' ||
                                    '"Motivo Rechazo",' ||
                                    '"Campaña de Creación",' ||
                                    '"Creada despues del cierre",' ||
                                    '"Fecha Hora Ingreso Solicitud",' ||
                                    '"Usuario Gestión",' ||
                                    '"Fecha Hora Gestión",' ||

                                    '"Nacionalidad",' ||
                                    '"Sexo",' ||
                                    '"Tipo Documento Identidad",' ||
                                    '"Estado Civil",' ||
                                    '"Tipo Persona",' ||
                                    '"Origen del Ingreso",' ||
                                    '"Barrio"' ||
                                    '"Manzana/Letra",' ||
                                    '"Etapa/Conjunto",' ||
                                    '"Calle Principal",' ||
                                    '"Calle Secundaria",' ||
                                    '"Dirección Completa"'
                                    ;

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :=  '=T("'|| nuevarechazadarecord(x).cod_peri || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).pre_impr || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_digi_ctrl || '")' || ',' ||
                                   '"'|| nuevarechazadarecord(x).nom_clie || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_docu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_docu_iden || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).hor_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_esta || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_moti || '")' || ',' ||
                                '"'|| nuevarechazadarecord(x).des_bloq || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_acti || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).sub_tipo_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_clasi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_clas || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_regi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_zona || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_secc || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_terr || '")' || ',' ||
                                   '"'|| replace(nuevarechazadarecord(x).val_dire,'-',' ') || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_cod_post || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_tele || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_celu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_reco || '")' || ',' ||
                                   '"'|| nuevarechazadarecord(x).nom_reco || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_lide || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_moti_rech || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cam_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_cier || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_ingr_soli || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).usu_gest || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_gest || '")' || ',' ||

                                '=T("'|| nuevarechazadarecord(x).DESC_NAC || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_COD_SEXO || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_ocr_tdoc || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_OCR_ESTA || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DESC_TIPO_PERSONA || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).DESC_ORIGEN_INGRESO || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_NOM_BARR || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_NOM_MANZ || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_ETA_CONJ || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_CAL_PRIN || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).VAL_CAL_SECU || '")' || ',' ||

                                '"'|| nvl(nuevarechazadarecord(x).VAL_NOM_BARR,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_NOM_MANZ,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_ETA_CONJ,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_CAL_PRIN,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_CAL_SECU,'') || ' ' ||
                                      nvl(nuevarechazadarecord(x).VAL_OBSE,'')
                                    || '"';

                    utl_file.put_line(v_handle,lslinea);
                 ELSE
                    IF lbCabecera THEN
                        lslinea :=  '"Campaña",' ||
                                    '"Cliente",' ||
                                    '"Número de Preimpreso",' ||
                                    '"Codigo Dig. Control",' ||
                                    '"Nombre",' ||
                                    '"Tipo Documento",' ||
                                    '"Documento Identidad",' ||
                                    '"Fecha Creacion",' ||
                                    '"Hora Creación",' ||
                                    '"Estado",' ||
                                    '"Motivo",' ||
                                    '"Bloqueo",' ||
                                    '"Activo",' ||
                                    '"Tipo Cliente",' ||
                                    '"Sub Tipo Cliente",' ||
                                    '"Tipo Clasificacion",' ||
                                    '"Clasificacion",' ||
                                    '"Región",' ||
                                    '"Zona",' ||
                                    '"Sección",' ||
                                    '"Territorio",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Teléfono",' ||
                                    '"Celular",' ||
                                    '"Codigo Recomendante",' ||
                                    '"Nombre Recomendante",' ||
                                    '"Es Lider",' ||
                                    '"Motivo Rechazo",' ||
                                    '"Campaña de Creación",' ||
                                    '"Creada despues del cierre",' ||
                                    '"Fecha Hora Ingreso Solicitud",' ||
                                    '"Usuario Gestión",' ||
                                    '"Fecha Hora Gestión"';

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :=  '=T("'|| nuevarechazadarecord(x).cod_peri || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).pre_impr || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_digi_ctrl || '")' || ',' ||
                                   '"'|| nuevarechazadarecord(x).nom_clie || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_docu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_docu_iden || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).hor_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_esta || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_moti || '")' || ',' ||
                                '"'|| nuevarechazadarecord(x).des_bloq || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_acti || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).sub_tipo_clie || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).tip_clasi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).des_clas || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_regi || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_zona || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_secc || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_terr || '")' || ',' ||
                                   '"'|| replace(nuevarechazadarecord(x).val_dire,'-',' ') || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_cod_post || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_tele || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).num_celu || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cod_reco || '")' || ',' ||
                                   '"'|| nuevarechazadarecord(x).nom_reco || '"' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_lide || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).val_moti_rech || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).cam_crea || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).ind_cier || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_ingr_soli || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).usu_gest || '")' || ',' ||
                                '=T("'|| nuevarechazadarecord(x).fec_gest || '")';

                    utl_file.put_line(v_handle,lslinea);
                 END IF;


                END LOOP;
              END IF;
          EXIT WHEN c_nueva_rechazada%NOTFOUND;
        END LOOP;
        CLOSE c_nueva_rechazada;

    ELSIF (pstiporeporte = '2') THEN
        OPEN c_nueva_pedido_consultora;
        LOOP
          FETCH c_nueva_pedido_consultora BULK COLLECT
            INTO pedidoconsultorarecord LIMIT w_filas;
              IF pedidoconsultorarecord.count > 0 THEN
                FOR x IN pedidoconsultorarecord.first .. pedidoconsultorarecord.last
                LOOP

                 IF (v_para = '1' ) THEN
                   IF lbCabecera THEN
                        lslinea :=  '"Código Cliente",' ||
                                    '"Campaña 1er Pedido",' ||
                                    '"Nombre Cliente",' ||
                                    '"Nro. Documento",' ||
                                    '"Código Zona",' ||
                                    '"Código Territorio",' ||
                                    '"Código Sección",' ||
                                    '"Código Consultora Vinculante",' ||
                                    '"Nombre Consultora Vinculante",' ||
                                    '"Código Líder que recomienda",' ||
                                    '"Nombre Líder que recomienda",' ||
                                    '"Código Zona Vinculante",' ||
                                    '"Código Sección Vinculante",' ||
                                    '"Estatus",' ||
                                    '"Bloqueo",' ||
                                    '"Tipo Vía",' ||
                                    '"Nombre Vía",' ||
                                    '"Número Principal",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Código Unidad Geográfica",' ||
                                    '"Dirección Unidad Geográfica",' ||
                                    '"Teléfono Casa",' ||
                                    '"Teléfono Celular",' ||
                                    '"Email",' ||
                                    '"Saldo Deuda Anterior",' ||
                                    '"Nacionalidad",' ||
                                    '"Sexo",' ||
                                    '"Tipo Documento Identidad",' ||
                                    '"Estado Civil",' ||
                                    '"Tipo Persona",' ||
                                    '"Origen del Ingreso",' ||
                                    '"Barrio",' ||
                                    '"Manzana/Letra",' ||
                                    '"Etapa/Conjunto",' ||
                                    '"Calle Principal",' ||
                                    '"Calle Secundaria",' ||
                                    '"Dirección Completa"'
                                    ;

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :='=T("'|| pedidoconsultorarecord(x).cod_clie || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).peri_prim_pedi || '")' || ',' ||
                               '"'|| pedidoconsultorarecord(x).nom_clie || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_docu_iden || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_zona || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_terr || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_secc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_clie_vinc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).nom_clie_vinc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_clie_lider_recom || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).nom_clie_lider_recom || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_zona_clie_vinc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_secc_clie_vinc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).esta_clie || '")' || ',' ||
                            '"'|| pedidoconsultorarecord(x).des_bloq || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tipo_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_nomb_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_ppal || '")' || ',' ||
                               '"'|| replace(pedidoconsultorarecord(x).val_obse,'-',' ') || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_cod_post || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_casa || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_celu || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_emai || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_saldo_avenc || '")' || ',' ||

                            '=T("'|| pedidoconsultorarecord(x).DESC_NAC || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).COD_SEXO || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_ocr_tdoc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_OCR_ESTA || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).DESC_TIPO_PERSONA || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).DESC_ORIGEN_INGRESO || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_NOM_BARR || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_NOM_MANZ || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_ETA_CONJ || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_CAL_PRIN || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_CAL_SECU || '")' || ',' ||

                            '"'|| nvl(pedidoconsultorarecord(x).VAL_NOM_BARR,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_NOM_MANZ,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_ETA_CONJ,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_CAL_PRIN,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_CAL_SECU,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_OBSE,'')
                                    || '"';

                    utl_file.put_line(v_handle,lslinea);
                   ELSE
                    IF lbCabecera THEN
                        lslinea :=  '"Código Cliente",' ||
                                    '"Campaña 1er Pedido",' ||
                                    '"Nombre Cliente",' ||
                                    '"Nro. Documento",' ||
                                    '"Código Zona",' ||
                                    '"Código Territorio",' ||
                                    '"Código Sección",' ||
                                    '"Código Consultora Vinculante",' ||
                                    '"Nombre Consultora Vinculante",' ||
                                    '"Código Líder que recomienda",' ||
                                    '"Nombre Líder que recomienda",' ||
                                    '"Código Zona Vinculante",' ||
                                    '"Código Sección Vinculante",' ||
                                    '"Estatus",' ||
                                    '"Bloqueo",' ||
                                    '"Tipo Vía",' ||
                                    '"Nombre Vía",' ||
                                    '"Número Principal",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Código Unidad Geográfica",' ||
                                    '"Dirección Unidad Geográfica",' ||
                                    '"Teléfono Casa",' ||
                                    '"Teléfono Celular",' ||
                                    '"Email",' ||
                                    '"Saldo Deuda Anterior"' ;

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :='=T("'|| pedidoconsultorarecord(x).cod_clie || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).peri_prim_pedi || '")' || ',' ||
                               '"'|| pedidoconsultorarecord(x).nom_clie || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_docu_iden || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_zona || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_terr || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_secc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_clie_vinc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).nom_clie_vinc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_clie_lider_recom || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).nom_clie_lider_recom || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_zona_clie_vinc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_secc_clie_vinc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).esta_clie || '")' || ',' ||
                            '"'|| pedidoconsultorarecord(x).des_bloq || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tipo_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_nomb_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_ppal || '")' || ',' ||
                               '"'|| replace(pedidoconsultorarecord(x).val_obse,'-',' ') || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_cod_post || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_casa || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_celu || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_emai || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_saldo_avenc || '")';

                    utl_file.put_line(v_handle,lslinea);

                 END IF;



                END LOOP;
              END IF;
          EXIT WHEN c_nueva_pedido_consultora%NOTFOUND;
        END LOOP;
        CLOSE c_nueva_pedido_consultora;

    ELSIF (pstiporeporte = '3' OR pstiporeporte = '4' ) THEN
        OPEN c_nueva_pedido_consultora;
        LOOP
          FETCH c_nueva_pedido_consultora BULK COLLECT
            INTO pedidoconsultorarecord LIMIT w_filas;
              IF pedidoconsultorarecord.count > 0 THEN
                FOR x IN pedidoconsultorarecord.first .. pedidoconsultorarecord.last
                LOOP

                IF (v_para = '1' ) THEN
                   IF lbCabecera THEN
                        lslinea :=  '"Código Cliente",' ||
                                    '"Campaña 1er Pedido",' ||
                                    '"Nombre Cliente",' ||
                                    '"Nro. Documento",' ||
                                    '"Código Zona",' ||
                                    '"Código Territorio",' ||
                                    '"Estatus",' ||
                                    '"Bloqueo",' ||
                                    '"Tipo Vía",' ||
                                    '"Nombre Vía",' ||
                                    '"Número Principal",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Código Unidad Geográfica",' ||
                                    '"Dirección Unidad Geográfica",' ||
                                    '"Teléfono Casa",' ||
                                    '"Teléfono Celular",' ||
                                    '"Email",' ||
                                    '"Saldo Deuda Anterior",'||

                                    '"Nacionalidad",' ||
                                    '"Sexo",' ||
                                    '"Tipo Documento Identidad",' ||
                                    '"Estado Civil",' ||
                                    '"Tipo Persona",' ||
                                    '"Origen del Ingreso",' ||
                                    '"Barrio",' ||
                                    '"Manzana/Letra",' ||
                                    '"Etapa/Conjunto",' ||
                                    '"Calle Principal",' ||
                                    '"Calle Secundaria",' ||
                                    '"Dirección Completa"'
                                    ;

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :='=T("'|| pedidoconsultorarecord(x).cod_clie || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).peri_prim_pedi || '")' || ',' ||
                               '"'|| pedidoconsultorarecord(x).nom_clie || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_docu_iden || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_zona || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_terr || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).esta_clie || '")' || ',' ||
                            '"'|| pedidoconsultorarecord(x).des_bloq || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tipo_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_nomb_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_ppal || '")' || ',' ||
                               '"'|| replace(pedidoconsultorarecord(x).val_obse,'-',' ') || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_cod_post || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_casa || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_celu || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_emai || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_saldo_avenc || '")' || ',' ||

                            '=T("'|| pedidoconsultorarecord(x).DESC_NAC || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).COD_SEXO || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_ocr_tdoc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_OCR_ESTA || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).DESC_TIPO_PERSONA || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).DESC_ORIGEN_INGRESO || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_NOM_BARR || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_NOM_MANZ || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_ETA_CONJ || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_CAL_PRIN || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).VAL_CAL_SECU || '")' || ',' ||

                            '"'|| nvl(pedidoconsultorarecord(x).VAL_NOM_BARR,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_NOM_MANZ,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_ETA_CONJ,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_CAL_PRIN,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_CAL_SECU,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_OBSE,'')
                                    || '"';

                    utl_file.put_line(v_handle,lslinea);
                 ELSE
                    IF lbCabecera THEN
                        lslinea :=  '"Código Cliente",' ||
                                    '"Campaña 1er Pedido",' ||
                                    '"Nombre Cliente",' ||
                                    '"Nro. Documento",' ||
                                    '"Código Zona",' ||
                                    '"Código Territorio",' ||
                                    '"Estatus",' ||
                                    '"Bloqueo",' ||
                                    '"Tipo Vía",' ||
                                    '"Nombre Vía",' ||
                                    '"Número Principal",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Código Unidad Geográfica",' ||
                                    '"Dirección Unidad Geográfica",' ||
                                    '"Teléfono Casa",' ||
                                    '"Teléfono Celular",' ||
                                    '"Email",' ||
                                    '"Saldo Deuda Anterior"';

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :='=T("'|| pedidoconsultorarecord(x).cod_clie || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).peri_prim_pedi || '")' || ',' ||
                               '"'|| pedidoconsultorarecord(x).nom_clie || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_docu_iden || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_zona || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_terr || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).esta_clie || '")' || ',' ||
                            '"'|| pedidoconsultorarecord(x).des_bloq || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tipo_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_nomb_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_ppal || '")' || ',' ||
                               '"'|| replace(pedidoconsultorarecord(x).val_obse,'-',' ') || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_cod_post || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_casa || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_celu || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_emai || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_saldo_avenc || '")';

                    utl_file.put_line(v_handle,lslinea);
                 END IF;


                END LOOP;
              END IF;
          EXIT WHEN c_nueva_pedido_consultora%NOTFOUND;
        END LOOP;
        CLOSE c_nueva_pedido_consultora;


    ELSIF pstiporeporte = '5' THEN
        OPEN c_nueva_pedido_consultora;
        LOOP
          FETCH c_nueva_pedido_consultora BULK COLLECT
            INTO pedidoconsultorarecord LIMIT w_filas;
              IF pedidoconsultorarecord.count > 0 THEN
                FOR x IN pedidoconsultorarecord.first .. pedidoconsultorarecord.last
                LOOP

                 IF (v_para = '1' ) THEN
                   IF lbCabecera THEN
                        lslinea :=  '"Código Cliente",' ||
                                    '"Dígito Verificador",' ||
                                    '"Campaña Registro",' ||
                                    '"Campaña Primer Contacto",' ||
                                    '"Campaña 1er Pedido",' ||
                                    '"Campaña Último Pedido",' ||
                                    '"Estatus",' ||
                                    '"Nombre Cliente",' ||
                                    '"Nro. Documento",' ||
                                    '"Código Región",' ||
                                    '"Código Zona",' ||
                                    '"Código Sección",' ||
                                    '"Código Territorio",' ||
                                    '"Bloqueo",' ||
                                    '"Observacion Bloqueo",' ||
                                    '"Tipo Vía",' ||
                                    '"Nombre Vía",' ||
                                    '"Número Principal",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Código Unidad Geográfica",' ||
                                    '"Dirección Unidad Geográfica",' ||
                                    '"Teléfono Casa",' ||
                                    '"Teléfono Celular",' ||
                                    '"Email",' ||
                                    '"Fecha Última Actualización Comunicación",' ||
                                    '"Saldo Deuda Anterior",' ||
                                    '"Fecha Creación",' ||
                                    '"Usuario Gestión",' ||
                                    '"Fecha Hora Gestión",' ||
                                    '"Campaña Creación",'||

                                    '"Nacionalidad",' ||
                                    '"Sexo",' ||
                                    '"Tipo Documento Identidad",' ||
                                    '"Estado Civil",' ||
                                    '"Tipo Persona",' ||
                                    '"Origen del Ingreso",' ||
                                    '"Barrio",' ||
                                    '"Manzana/Letra",' ||
                                    '"Etapa/Conjunto",' ||
                                    '"Calle Principal",' ||
                                    '"Calle Secundaria",' ||
                                    '"Sin Hoja Pedido",' ||
                                    '"Dirección Completa"'
                                    ;

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :='=T("'|| pedidoconsultorarecord(x).cod_clie || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).COD_DIGI_CTRL || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cam_regi || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_nomb_peri || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).peri_prim_pedi_acti || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_nomb_pedi || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).esta_clie || '")' || ',' ||
                                   '"'|| pedidoconsultorarecord(x).nom_clie || '"' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).num_docu_iden || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cod_regi || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cod_zona || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cod_secc || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cod_terr || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).des_bloq || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).DES_OBSE_BLOQ || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).tipo_via || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_nomb_via || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).num_ppal || '")' || ',' ||
                                   '"'|| replace(pedidoconsultorarecord(x).val_obse,'-',' ') || '"' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_cod_post || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cod_unid_geog_dir || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).unid_geog_dir || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).tel_casa || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).tel_celu || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_emai || '")' || ',' ||
                                '=T("'|| TO_CHAR(pedidoconsultorarecord(x).fec_ulti_actu,'dd/MM/YYYY hh:mi:ss') || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_saldo_avenc || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).fec_ingr_soli || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).usu_gest || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).fec_gest || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cam_crea || '")' || ',' ||

                                '=T("'|| pedidoconsultorarecord(x).DESC_NAC || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).COD_SEXO || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_ocr_tdoc || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_OCR_ESTA || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).DESC_TIPO_PERSONA || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).DESC_ORIGEN_INGRESO || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_NOM_BARR || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_NOM_MANZ || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_ETA_CONJ || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_CAL_PRIN || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_CAL_SECU || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).IND_HOJA_PEDI || '")'  || ',' ||

                                 '"'|| nvl(pedidoconsultorarecord(x).VAL_NOM_BARR,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_NOM_MANZ,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_ETA_CONJ,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_CAL_PRIN,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_CAL_SECU,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_OBSE,'')
                                    || '"';

                    utl_file.put_line(v_handle,lslinea);
                 ELSE
                    IF lbCabecera THEN
                        lslinea :=  '"Código Cliente",' ||
                                    '"Campaña Registro",' ||
                                    '"Campaña 1er Pedido",' ||
                                    '"Campaña Último Pedido",' ||
                                    '"Estatus",' ||
                                    '"Nombre Cliente",' ||
                                    '"Nro. Documento",' ||
                                    '"Código Zona",' ||
                                    '"Código Sección",' ||
                                    '"Código Territorio",' ||
                                    '"Bloqueo",' ||
                                    '"Observacion Bloqueo",' ||
                                    '"Tipo Vía",' ||
                                    '"Nombre Vía",' ||
                                    '"Número Principal",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Código Unidad Geográfica",' ||
                                    '"Dirección Unidad Geográfica",' ||
                                    '"Teléfono Casa",' ||
                                    '"Teléfono Celular",' ||
                                    '"Email",' ||
                                    '"Fecha Última Actualización Comunicación",' ||
                                    '"Saldo Deuda Anterior",' ||
                                    '"Fecha Creación",' ||
                                    '"Usuario Gestión",' ||
                                    '"Fecha Hora Gestión",' ||
                                    '"Campaña Creación"';

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :='=T("'|| pedidoconsultorarecord(x).cod_clie || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cam_regi || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).peri_prim_pedi_acti || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_nomb_pedi || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).esta_clie || '")' || ',' ||
                                   '"'|| pedidoconsultorarecord(x).nom_clie || '"' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).num_docu_iden || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cod_zona || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cod_secc || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cod_terr || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).des_bloq || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).DES_OBSE_BLOQ || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).tipo_via || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_nomb_via || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).num_ppal || '")' || ',' ||
                                   '"'|| replace(pedidoconsultorarecord(x).val_obse,'-',' ') || '"' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_cod_post || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cod_unid_geog_dir || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).unid_geog_dir || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).tel_casa || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).tel_celu || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_emai || '")' || ',' ||
                                '=T("'|| TO_CHAR(pedidoconsultorarecord(x).fec_ulti_actu,'dd/MM/YYYY hh:mi:ss') || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_saldo_avenc || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).fec_ingr_soli || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).usu_gest || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).fec_gest || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).cam_crea || '")';

                    utl_file.put_line(v_handle,lslinea);
                 END IF;


                END LOOP;
              END IF;
          EXIT WHEN c_nueva_pedido_consultora%NOTFOUND;
        END LOOP;
        CLOSE c_nueva_pedido_consultora;

    ELSIF (pstiporeporte = '6') THEN
        OPEN c_nueva_pedido_consultora;
        LOOP
          FETCH c_nueva_pedido_consultora BULK COLLECT
            INTO pedidoconsultorarecord LIMIT w_filas;
              IF pedidoconsultorarecord.count > 0 THEN
                FOR x IN pedidoconsultorarecord.first .. pedidoconsultorarecord.last
                LOOP

                 IF (v_para = '1' ) THEN
                    IF lbCabecera THEN
                        lslinea :=  '"Código Cliente",' ||
                                    '"Campaña Registro",' ||
                                    '"Campaña 1er Pedido",' ||
                                    '"Campaña Último Pedido",' ||
                                    '"Nombre Cliente",' ||
                                    '"Nro. Documento",' ||
                                    '"Código Zona",' ||
                                    '"Código Sección",' ||
                                    '"Código Territorio",' ||
                                    '"Estatus",' ||
                                    '"Bloqueo",' ||
                                    '"Observacion Bloqueo",' ||
                                    '"Tipo Vía",' ||
                                    '"Nombre Vía",' ||
                                    '"Número Principal",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Código Unidad Geográfica",' ||
                                    '"Dirección Unidad Geográfica",' ||
                                    '"Teléfono Casa",' ||
                                    '"Teléfono Celular",' ||
                                    '"Email",' ||
                                    '"Saldo Deuda Anterior",' ||
                                    '"Fecha Creación",' ||
                                    '"Usuario Gestión",' ||
                                    '"Fecha Hora Gestión",' ||
                                    '"Campaña Creación",'||

                                    '"Nacionalidad",' ||
                                    '"Sexo",' ||
                                    '"Tipo Documento Identidad",' ||
                                    '"Estado Civil",' ||
                                    '"Tipo Persona",' ||
                                    '"Origen del Ingreso",' ||
                                    '"Barrio",' ||
                                    '"Manzana/Letra",' ||
                                    '"Etapa/Conjunto",' ||
                                    '"Calle Principal",' ||
                                    '"Calle Secundaria",' ||
                                    '"Dirección Completa"'
                                    ;

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :='=T("'|| pedidoconsultorarecord(x).cod_clie || '")' || ',' ||
                             '=T("'|| pedidoconsultorarecord(x).cam_regi || '")' || ',' ||
                             '=T("'|| pedidoconsultorarecord(x).peri_prim_pedi || '")' || ',' ||
                             '=T("'|| pedidoconsultorarecord(x).val_nomb_pedi || '")' || ',' ||
                               '"'|| pedidoconsultorarecord(x).nom_clie || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_docu_iden || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_zona || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_secc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_terr || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).esta_clie || '")' || ',' ||
                            '"'|| pedidoconsultorarecord(x).des_bloq || '"' || ',' ||
                            '"'|| pedidoconsultorarecord(x).DES_OBSE_BLOQ || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tipo_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_nomb_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_ppal || '")' || ',' ||
                               '"'|| replace(pedidoconsultorarecord(x).val_obse,'-',' ') || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_cod_post || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_casa || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_celu || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_emai || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_saldo_avenc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).fec_ingr_soli || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).usu_gest || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).fec_gest || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cam_crea || '")' || ',' ||

                                '=T("'|| pedidoconsultorarecord(x).DESC_NAC || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).COD_SEXO || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).val_ocr_tdoc || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_OCR_ESTA || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).DESC_TIPO_PERSONA || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).DESC_ORIGEN_INGRESO || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_NOM_BARR || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_NOM_MANZ || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_ETA_CONJ || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_CAL_PRIN || '")' || ',' ||
                                '=T("'|| pedidoconsultorarecord(x).VAL_CAL_SECU || '")'  || ',' ||

                                  '"'|| nvl(pedidoconsultorarecord(x).VAL_NOM_BARR,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_NOM_MANZ,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_ETA_CONJ,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_CAL_PRIN,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_CAL_SECU,'') || ' ' ||
                                      nvl(pedidoconsultorarecord(x).VAL_OBSE,'')
                                    || '"';

                    utl_file.put_line(v_handle,lslinea);
                 ELSE
                    IF lbCabecera THEN
                        lslinea :=  '"Código Cliente",' ||
                                    '"Campaña Registro",' ||
                                    '"Campaña 1er Pedido",' ||
                                    '"Campaña Último Pedido",' ||
                                    '"Nombre Cliente",' ||
                                    '"Nro. Documento",' ||
                                    '"Código Zona",' ||
                                    '"Código Sección",' ||
                                    '"Código Territorio",' ||
                                    '"Estatus",' ||
                                    '"Bloqueo",' ||
                                    '"Observacion Bloqueo",' ||
                                    '"Tipo Vía",' ||
                                    '"Nombre Vía",' ||
                                    '"Número Principal",' ||
                                    '"Dirección",' ||
                                    '"Código Postal",' ||
                                    '"Código Unidad Geográfica",' ||
                                    '"Dirección Unidad Geográfica",' ||
                                    '"Teléfono Casa",' ||
                                    '"Teléfono Celular",' ||
                                    '"Email",' ||
                                    '"Saldo Deuda Anterior",' ||
                                    '"Fecha Creación",' ||
                                    '"Usuario Gestión",' ||
                                    '"Fecha Hora Gestión",' ||
                                    '"Campaña Creación"';

                       lbCabecera := false;
                       utl_file.put_line(v_handle,lslinea);
                    END IF;

                    lslinea :='=T("'|| pedidoconsultorarecord(x).cod_clie || '")' || ',' ||
                             '=T("'|| pedidoconsultorarecord(x).cam_regi || '")' || ',' ||
                             '=T("'|| pedidoconsultorarecord(x).peri_prim_pedi || '")' || ',' ||
                             '=T("'|| pedidoconsultorarecord(x).val_nomb_pedi || '")' || ',' ||
                               '"'|| pedidoconsultorarecord(x).nom_clie || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_docu_iden || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_zona || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_secc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_terr || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).esta_clie || '")' || ',' ||
                            '"'|| pedidoconsultorarecord(x).des_bloq || '"' || ',' ||
                            '"'|| pedidoconsultorarecord(x).DES_OBSE_BLOQ || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tipo_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_nomb_via || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).num_ppal || '")' || ',' ||
                               '"'|| replace(pedidoconsultorarecord(x).val_obse,'-',' ') || '"' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_cod_post || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cod_unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).unid_geog_dir || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_casa || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).tel_celu || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_emai || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).val_saldo_avenc || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).fec_ingr_soli || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).usu_gest || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).fec_gest || '")' || ',' ||
                            '=T("'|| pedidoconsultorarecord(x).cam_crea || '")';

                    utl_file.put_line(v_handle,lslinea);
                 END IF;


                END LOOP;
              END IF;
          EXIT WHEN c_nueva_pedido_consultora%NOTFOUND;
        END LOOP;
        CLOSE c_nueva_pedido_consultora;

    END IF;

    utl_file.fclose(v_handle);

    delete from mae_tempo_nueva_recha WHERE oid_proc_nuev = psoidproceso;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'MAE_PR_GENER_REPOR_NUEVA_CSV' || ls_sqlerrm);
  END mae_pr_gener_repor_nueva_csv;

/***************************************************************************
Descripcion       : Devuelve Gerente de la Zona o Region. Si encaso
                    develve null, nos retorna un correo.
Fecha Creacion    : 22/08/2013
Autor             : Yahir Rivas Luna
***************************************************************************/
FUNCTION MAE_FN_DEVUE_RESPO_CORRE
(psCodRegion      VARCHAR2,
 psCodZona        VARCHAR2
)RETURN VARCHAR2

IS
lsCorreo     VARCHAR2(100);
lsEncontro   BOOLEAN:=true;
lsCodLider   NUMBER(12);

BEGIN
      SELECT val_para
        INTO lsCorreo
        FROM BAS_PARAM_PAIS
       WHERE nom_para='psCorreoSAC';

       BEGIN
         SELECT clie_oid_clie
           INTO lsCodLider
         from zon_zona
          WHERE cod_zona = psCodZona;
        EXCEPTION
          WHEN no_data_found THEN
            lsCodLider := NULL;
       END;

       IF  lsCodLider IS NULL THEN
         BEGIN
            SELECT clie_oid_clie
              INTO lsCodLider
              FROM zon_regio
             WHERE cod_regi = psCodRegion;
          EXCEPTION
             WHEN no_data_found THEN
                lsCodLider := NULL;
         END;

            IF  lsCodLider IS NULL THEN
                lsEncontro:=false;
           END IF;
        END IF;

        IF lsEncontro THEN

           SELECT DECODE(val_text_comu,NULL,lsCorreo,val_text_comu)
           INTO lsCorreo
           FROM MAE_CLIEN_COMUN
           WHERE CLIE_OID_CLIE = lsCodLider
           AND TICM_OID_TIPO_COMU = 3 -- email
           AND ROWNUM = 1;


        END IF;

   RETURN lsCorreo;

EXCEPTION
  WHEN no_data_found THEN
         select val_para
         into lsCorreo
         from BAS_PARAM_PAIS
         where nom_para='psCorreoSAC';

  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_DEVUE_RESPO_CORRE: '||ls_sqlerrm);


END MAE_FN_DEVUE_RESPO_CORRE;

/***************************************************************************
Descripcion          : Devuelve Gerente de la Zona. Si encaso develve null no envia el correo.
Fecha Creacion   : 13/08/2014
Autor                  : Sebastian Guerra
***************************************************************************/
FUNCTION MAE_FN_DEVUE_RESPO_CORRE_ZONA
(psCodZona        VARCHAR2
)RETURN VARCHAR2

IS
lsCorreo     VARCHAR2(100);
lsCodLider   NUMBER(12);

BEGIN
       SELECT val_para
        INTO lsCorreo
        FROM bas_param_pais
       WHERE nom_para='psCorreoSAC';

       BEGIN
         SELECT clie_oid_clie
           INTO lsCodLider
           FROM zon_zona
          WHERE cod_zona = psCodZona;
       EXCEPTION
          WHEN no_data_found THEN
            lsCodLider := NULL;
            lsCorreo := NULL;
       END;

        IF lsCodLider IS NOT NULL THEN
           SELECT DECODE(val_text_comu,NULL,lsCorreo,val_text_comu)
             INTO lsCorreo
             FROM mae_clien_comun
            WHERE clie_oid_clie = lsCodLider
              AND ticm_oid_tipo_comu = 3 -- email
              AND ROWNUM = 1;
        ELSE
           lsCorreo := NULL;
        END IF;

   RETURN lsCorreo;

EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_DEVUE_RESPO_CORRE_ZONA: '||ls_sqlerrm);

END MAE_FN_DEVUE_RESPO_CORRE_ZONA;

/***************************************************************************
Descripcion       : Devuelve los bloqueo activos por cliente
Fecha Creacion    : 11/09/2013
Autor             : JPJC
***************************************************************************/
FUNCTION MAE_FN_DEVUE_BLOQ_X_CLIE
(
p_clie_oid_clie NUMBER
)
RETURN VARCHAR2
IS
  lsDescripciones VARCHAR2(200);

 BEGIN

lsDescripciones := '';

  FOR x IN (SELECT  gen.val_i18n
               FROM mae_clien_bloqu mcb,
                    mae_tipo_bloqu mtb,
                    gen_i18n_sicc_comun gen
               WHERE mcb.tibq_oid_tipo_bloq=mtb.oid_tipo_bloq
               AND   mtb.oid_tipo_bloq=gen.val_oid
               AND   gen.attr_enti LIKE 'MAE_TIPO_BLOQU'
               AND   gen.idio_oid_idio = 1
               AND   mcb.fec_desb is NULL
               AND   mcb.clie_oid_clie = p_clie_oid_clie) LOOP

 lsDescripciones := lsDescripciones || x.val_i18n ||  ',';

END LOOP;

RETURN  SUBSTR(TRIM(lsDescripciones),1,LENGTH(TRIM(lsDescripciones))-1);

EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_DEVUE_BLOQ_X_CLIE: '||ls_sqlerrm);
END MAE_FN_DEVUE_BLOQ_X_CLIE;

/***************************************************************************
Descripcion       : Devuelve cantidad de bloqueo activos por cliente
Fecha Creacion    : 11/09/2013
Autor             : JPJC
***************************************************************************/
FUNCTION MAE_FN_DEVUE_CANT_BLOQ_X_CLIE
(
p_clie_oid_clie NUMBER
)
RETURN VARCHAR2
IS
  lnContador NUMBER(12);

 BEGIN

  SELECT  COUNT(1)
  INTO lnContador
  FROM mae_clien_bloqu mcb,
        mae_tipo_bloqu mtb,
        gen_i18n_sicc_comun gen
   WHERE mcb.tibq_oid_tipo_bloq=mtb.oid_tipo_bloq
   AND   mtb.oid_tipo_bloq=gen.val_oid
   AND   gen.attr_enti LIKE 'MAE_TIPO_BLOQU'
   AND   gen.idio_oid_idio = 1
   AND   mcb.fec_desb is NULL
   AND   mcb.clie_oid_clie = p_clie_oid_clie;

  RETURN  lnContador;

EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_DEVUE_CANT_BLOQ_X_CLIE: '||ls_sqlerrm);
END MAE_FN_DEVUE_CANT_BLOQ_X_CLIE;


/***************************************************************************
Descripcion       : Genera el Reporte MAE Consejeras Bloqueadas Desbloqueadas
Fecha Creacion    : 21/01/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE MAE_PR_REPOR_CONSE_BLOQU_DESBL(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_CONSE_BLOQU_DESBL IS
    SELECT
        DES_TIPO_REPO,
        DES_REGI,
        COD_ZONA,
        COD_SECC,
        COD_TERR,
        COD_CLIE,
        NUM_DOCU_IDEN,
        VAL_APEL_NOMB,
        FEC_INGR,
        CAM_INGR,
        VAL_ESTA_ACTU,
        SAL_DEUD_ANTE,
        VAL_BLOQ,
        OBS_BLOQ,
        COD_PERI_BLOQ_MINI,
        FEC_BLOQ,
        VAL_HORA_BLOQ,
        VAL_USUA_BLOQ,
        OBS_DESB,
        COD_PERI_DESB_MINI,
        FEC_DESB,
        VAL_HORA_DESB,
        VAL_USUA_DESB,
        VAL_ESTA_DESB,
        VAL_AREA_ORIG,
        VAL_ESTA
     FROM MAE_TMP_CONSE_BLOQU_DESBL;

     TYPE detalleUnidadesReg IS RECORD(
          destipoReporte        MAE_TMP_CONSE_BLOQU_DESBL.DES_TIPO_REPO%TYPE,
          region                MAE_TMP_CONSE_BLOQU_DESBL.DES_REGI%TYPE,
          zona                  MAE_TMP_CONSE_BLOQU_DESBL.COD_ZONA%TYPE,
          seccion               MAE_TMP_CONSE_BLOQU_DESBL.COD_SECC%TYPE,
          territorio            MAE_TMP_CONSE_BLOQU_DESBL.COD_TERR%TYPE,
          codigoCliente         MAE_TMP_CONSE_BLOQU_DESBL.COD_CLIE%TYPE,
          numeroIdentidad       MAE_TMP_CONSE_BLOQU_DESBL.NUM_DOCU_IDEN%TYPE,
          apellidoNombre        MAE_TMP_CONSE_BLOQU_DESBL.VAL_APEL_NOMB%TYPE,
          fechaIngreso          MAE_TMP_CONSE_BLOQU_DESBL.FEC_INGR%TYPE,
          campanaIngreso        MAE_TMP_CONSE_BLOQU_DESBL.CAM_INGR%TYPE,
          statusActual          MAE_TMP_CONSE_BLOQU_DESBL.VAL_ESTA_ACTU%TYPE,
          deudaAnterior         MAE_TMP_CONSE_BLOQU_DESBL.SAL_DEUD_ANTE%TYPE,
          tipoBloqueo           MAE_TMP_CONSE_BLOQU_DESBL.VAL_BLOQ%TYPE,
          observacionBloqueo    MAE_TMP_CONSE_BLOQU_DESBL.OBS_BLOQ%TYPE,
          periodoBloqueo        MAE_TMP_CONSE_BLOQU_DESBL.COD_PERI_BLOQ_MINI%TYPE,
          fechaBloqueo          MAE_TMP_CONSE_BLOQU_DESBL.FEC_BLOQ%TYPE,
          horaBloqueo           MAE_TMP_CONSE_BLOQU_DESBL.VAL_HORA_BLOQ%TYPE,
          usuarioBloqueo        MAE_TMP_CONSE_BLOQU_DESBL.VAL_USUA_BLOQ%TYPE,
          observacionDesbloqueo MAE_TMP_CONSE_BLOQU_DESBL.OBS_DESB%TYPE,
          periodoDesbloqueo     MAE_TMP_CONSE_BLOQU_DESBL.COD_PERI_DESB_MINI%TYPE,
          fechaDesbloqueo       MAE_TMP_CONSE_BLOQU_DESBL.FEC_DESB%TYPE,
          horaDesbloqueo        MAE_TMP_CONSE_BLOQU_DESBL.VAL_HORA_DESB%TYPE,
          usuarioDesbloqueo     MAE_TMP_CONSE_BLOQU_DESBL.VAL_USUA_DESB%TYPE,
          estatusDesbloqueo        MAE_TMP_CONSE_BLOQU_DESBL.VAL_ESTA_DESB%TYPE,
          areaOrigen                    MAE_TMP_CONSE_BLOQU_DESBL.VAL_AREA_ORIG%TYPE,
          estado                    MAE_TMP_CONSE_BLOQU_DESBL.VAL_ESTA%TYPE
      );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_CONSE_BLOQU_DESBL;
      LOOP
       FETCH C_REPOR_CONSE_BLOQU_DESBL BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=      '"'|| detalleUnidadesRegRecord(x).destipoReporte || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).region || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).zona || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).seccion || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).territorio || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codigoCliente || '")' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).numeroIdentidad || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).apellidoNombre || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).fechaIngreso || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).campanaIngreso || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).statusActual || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).deudaAnterior || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).tipoBloqueo || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).observacionBloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).periodoBloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).fechaBloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).horaBloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).usuarioBloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).observacionDesbloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).periodoDesbloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).fechaDesbloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).horaDesbloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).usuarioDesbloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).estatusDesbloqueo || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).areaOrigen || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).estado || '"' ;

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_CONSE_BLOQU_DESBL%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_CONSE_BLOQU_DESBL;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_REPOR_CONSE_BLOQU_DESBL: '||ls_sqlerrm);

END MAE_PR_REPOR_CONSE_BLOQU_DESBL;


/***************************************************************************
Descripcion       : Genera el Reporte MAE Vinculos Clientes en CSV
Fecha Creacion    : 25/02/2014
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE MAE_PR_GENER_REPOR_VINCU_CLIEN(
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR_CONSE_BLOQU_DESBL IS
    SELECT
      DES_ZONA_RECO_MIEN,
			COD_CLIE_RECO_MIEN,
			VAL_NOMB_RECO_MIEN,
			DES_ZONA_RECO_MEND,
			COD_CLIE_RECO_MEND,
			VAL_NOMB_RECO_MEND,
			FEC_DESD,
			VAL_CAMP_VINC,
			COD_REGI,
			COD_ZONA,
			COD_TIPO_VINC
     FROM MAE_TMP_VINCU_CLIEN
     ORDER BY VAL_CAMP_VINC;

     TYPE detalleUnidadesReg IS RECORD(
          zona_recomienda       MAE_TMP_VINCU_CLIEN.DES_ZONA_RECO_MIEN%TYPE,
          codigo_recomienda     MAE_TMP_VINCU_CLIEN.COD_CLIE_RECO_MIEN%TYPE,
          nombre_recomienda     MAE_TMP_VINCU_CLIEN.VAL_NOMB_RECO_MIEN%TYPE,
          zona_recomendada      MAE_TMP_VINCU_CLIEN.DES_ZONA_RECO_MEND%TYPE,
          codigo_recomendada    MAE_TMP_VINCU_CLIEN.COD_CLIE_RECO_MEND%TYPE,
          nombre_recomendada    MAE_TMP_VINCU_CLIEN.VAL_NOMB_RECO_MEND%TYPE,
          fecha_vinculo         MAE_TMP_VINCU_CLIEN.FEC_DESD%TYPE,
          campa_vinculo         MAE_TMP_VINCU_CLIEN.VAL_CAMP_VINC%TYPE,
          cod_regi              MAE_TMP_VINCU_CLIEN.COD_REGI%TYPE,
          cod_zona              MAE_TMP_VINCU_CLIEN.COD_ZONA%TYPE,
          cod_tipo_vinc         MAE_TMP_VINCU_CLIEN.COD_TIPO_VINC%TYPE
      );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR_CONSE_BLOQU_DESBL;
      LOOP
       FETCH C_REPOR_CONSE_BLOQU_DESBL BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=      '"'|| detalleUnidadesRegRecord(x).zona_recomienda || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codigo_recomienda || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).nombre_recomienda || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).zona_recomendada || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).codigo_recomendada || '")' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).nombre_recomendada || '"' || ',' ||
                                '"'|| TO_CHAR(detalleUnidadesRegRecord(x).fecha_vinculo, 'DD/MM/YYYY') || '"' || ',' ||
                                '"'|| detalleUnidadesRegRecord(x).campa_vinculo || '"' || ',' ||
                                '=T("'|| detalleUnidadesRegRecord(x).cod_regi || '")' || '"' ;


                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_CONSE_BLOQU_DESBL%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_CONSE_BLOQU_DESBL;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_GENER_REPOR_VINCU_CLIEN: '||ls_sqlerrm);

END MAE_PR_GENER_REPOR_VINCU_CLIEN;

/***************************************************************************
Descripcion       : Devuelve la clasificación de la consultora.
Fecha Creacion    : 10/04/2014
Autor             : Aurelio Oviedo
***************************************************************************/
FUNCTION MAE_FN_DEVUE_CLASI_CLIEN (
    psCodigoPais      VARCHAR2,
    psOidCliente        VARCHAR2
    ) RETURN VARCHAR2

IS
lsDescClasificacion     VARCHAR2(4000);

BEGIN

    BEGIN
        WITH t AS
         (
           select * from
              (select   (CASE
                    WHEN (SELECT COUNT(*)
                            FROM HIP_CLASI_CLIEN
                           WHERE OID_TIPO_CLAS = a.TCCL_OID_TIPO_CLASI
                             AND OID_CLAS = a.CLAS_OID_CLAS
                             AND COD_PAIS = psCodigoPais) > 0 THEN
                     pq_apl_aux.Valor_Gen_I18n_Sicc(1, a.CLAS_OID_CLAS, 'MAE_CLASI')
                    ELSE
                     ''
                  END) AS cls
                FROM V_MAE_TIPIF_CLIEN a
               WHERE a.CLIE_OID_CLIE = psOidCliente) clasifica
            where cls is not null
         )
        SELECT LTRIM(SYS_CONNECT_BY_PATH(cls, ' | '),' | ') cls
        INTO lsDescClasificacion
        FROM ( SELECT cls, ROW_NUMBER() OVER (ORDER BY cls) FILA FROM t)
        WHERE CONNECT_BY_ISLEAF = 1
        START WITH FILA = 1
        CONNECT BY PRIOR FILA = FILA - 1;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            lsDescClasificacion := '';
    END;

    RETURN lsDescClasificacion;

EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_DEVUE_CLASI_CLIEN: '||ls_sqlerrm);
END MAE_FN_DEVUE_CLASI_CLIEN;

/***************************************************************************
Descripcion       : Genera el Reporte Clasificaciones por Cliente.
Fecha Creacion    : 22/04/2014
Autor             : Yahir Rivas
***************************************************************************/
PROCEDURE MAE_PR_REPOR_CLASI_X_CLIEN_CSV(
    psOidTipoClasi   VARCHAR2,
    psOidClasi       VARCHAR2,
    psOidTipoClie    VARCHAR2,
    psOidSubtClie    VARCHAR2,
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo           VARCHAR2,
    psdirectorio       OUT VARCHAR2
) IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);
    lnPosicion      number;

    CURSOR c_clasXClie IS
    SELECT   mc.cod_clie,
             mci.num_docu_iden,
             val_nom1 || ' ' || val_nom2 AS nombres,
             val_ape1 AS ape_pat,
             val_ape2 AS ape_mat,
             g1.val_i18n status,
             reg.cod_regi,
             zon.cod_zona,
             sec.cod_secc,
             zt.cod_terr,
             g2.val_i18n tipo_cliente,
             g3.val_i18n subtipo_cliente,
             g4.val_i18n tip_clasif ,
             g5.val_i18n clasif,
             (SELECT cam.val_nomb_peri FROM cra_perio cam WHERE cam.oid_peri = cc.perd_oid_peri) campana,
             to_char(mc.fec_ulti_actu,'dd/mm/yyyy HH24:MI:SS') fec_ulti_actu
   FROM mae_clien_tipo_subti cts ,
            mae_clien_clasi cc,
            mae_clien_datos_adici cda ,
            mae_clien mc,
            mae_clien_ident mci,
            mae_clien_unida_admin ua,
            zon_terri_admin za,
            zon_secci sec,
            zon_zona zon,
            zon_regio reg,
            zon_terri zt,
           (SELECT * FROM own_comun.gen_i18n_sicc_comun
              WHERE  attr_enti = 'MAE_ESTAT_CLIEN')   g1,
           (SELECT * FROM own_comun.gen_i18n_sicc_comun
              WHERE  attr_enti = 'MAE_TIPO_CLIEN')   g2,
           (SELECT * FROM own_comun.gen_i18n_sicc_comun
              WHERE  attr_enti = 'MAE_SUBTI_CLIEN')   g3,
           (SELECT * FROM own_comun.gen_i18n_sicc_comun
              WHERE  attr_enti = 'MAE_TIPO_CLASI_CLIEN')   g4,
            own_comun.gen_i18n_sicc_comun       g5
  WHERE cts.oid_clie_tipo_subt = cc.ctsu_oid_clie_tipo_subt
        AND mc.oid_clie = cts.clie_oid_clie
        AND cda.clie_oid_clie = cts.clie_oid_clie
        AND mc.oid_clie = mci.clie_oid_clie
        AND g1.val_oid(+) = cda.esta_oid_esta_clie
        AND g2.val_oid(+) = cts.ticl_oid_tipo_clie
        AND g3.val_oid(+) = cts.sbti_oid_subt_clie
        AND g4.val_oid(+) = cc.tccl_oid_tipo_clasi
        AND g5.attr_enti ='MAE_CLASI'
        AND g5.val_oid = cc.clas_oid_clas
        AND mc.oid_clie = ua.clie_oid_clie
        AND ua.ztad_oid_terr_admi = za.oid_terr_admi
        AND za.terr_oid_terr = zt.oid_terr
        AND za.zscc_oid_secc = sec.oid_secc
        AND sec.zzon_oid_zona = zon.oid_zona
        AND zon.zorg_oid_regi = reg.oid_regi
        AND ua.ind_acti = 1
        AND mci.val_iden_docu_prin = 1
        AND CASE WHEN psOidTipoClasi <> 'T' THEN cc.tccl_oid_tipo_clasi ELSE  1 END = CASE WHEN psOidTipoClasi <> 'T' THEN TO_NUMBER(psOidTipoClasi) ELSE 1 END
        AND CASE WHEN psOidClasi <> 'T' THEN cc.clas_oid_clas ELSE  1 END = CASE WHEN psOidClasi <> 'T' THEN TO_NUMBER(psOidClasi) ELSE 1 END
        AND cts.ticl_oid_tipo_clie = TO_NUMBER(psOidTipoClie)
        AND cts.sbti_oid_subt_clie = TO_NUMBER(psOidSubtClie);


     TYPE clasiXClien IS RECORD(
             cod_clie              mae_clien.cod_clie%TYPE,
             num_docu_iden         mae_clien_ident.num_docu_iden%type,
             nombres               varchar2(60),
             apePat                mae_clien.val_ape1%type,
             apeMat                mae_clien.Val_Ape2%TYPE,
             status                VARCHAR2(100),
             cod_regi              zon_regio.cod_regi%TYPE,
             cod_zona              zon_zona.Cod_Zona%TYPE,
             cod_secc              zon_secci.Cod_Secc%TYPE,
             cod_terr              zon_terri.Cod_Terr%TYPE,
             tipo_cliente          VARCHAR2(200),
             subtipo_cliente       VARCHAR2(200),
             tip_clasif            VARCHAR2(200),
             clasif                VARCHAR2(200),
             campania              VARCHAR2(6),
             fec_ulti_actu         VARCHAR2(50)
      );

     TYPE clasiXClienTab IS TABLE OF clasiXClien;
     clasiXClienRecord clasiXClienTab;

     lbAbrirUtlFile  BOOLEAN;

BEGIN
    lnPosicion :=1;
    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_clasXClie;
      LOOP
       FETCH c_clasXClie BULK COLLECT INTO clasiXClienRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF clasiXClienRecord.COUNT > 0 THEN
          FOR x IN clasiXClienRecord.FIRST .. clasiXClienRecord.LAST LOOP
                lslinea :=     '"'|| lnPosicion || '"' || ',' ||
                                '=T("'|| clasiXClienRecord(x).cod_clie || '")' || ',' ||
                                '=T("'|| clasiXClienRecord(x).num_docu_iden || '")' || ',' ||
                                '"'|| clasiXClienRecord(x).nombres || '"' || ',' ||
                                '"'|| clasiXClienRecord(x).apePat || '"' || ',' ||
                                '"'|| clasiXClienRecord(x).apeMat || '"' || ',' ||
                                '"'|| clasiXClienRecord(x).status || '"' || ',' ||
                                '=T("'|| clasiXClienRecord(x).cod_regi || '")' || ',' ||
                                '=T("'|| clasiXClienRecord(x).cod_zona || '")' || ',' ||
                                '"'|| clasiXClienRecord(x).cod_secc || '"' || ',' ||
                                '=T("'|| clasiXClienRecord(x).cod_terr || '")' || ',' ||
                                '"'|| clasiXClienRecord(x).tipo_cliente || '"' || ',' ||
                                '"'|| clasiXClienRecord(x).subtipo_cliente || '"' || ',' ||
                                '"'|| clasiXClienRecord(x).tip_clasif || '"' || ',' ||
                                '"'|| clasiXClienRecord(x).clasif || '"' || ',' ||
                                '=T("'|| clasiXClienRecord(x).campania || '")' || ',' ||
                                '=T("'|| clasiXClienRecord(x).fec_ulti_actu || '")';

                 UTL_FILE.PUT_LINE(V_HANDLE, lslinea );
                 lnPosicion := lnPosicion + 1;
          END LOOP;
        END IF;
        EXIT WHEN c_clasXClie%NOTFOUND;
     END LOOP;
    CLOSE c_clasXClie;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_REPOR_CLASI_X_CLIEN_CSV: '||ls_sqlerrm);

END MAE_PR_REPOR_CLASI_X_CLIEN_CSV;

/***************************************************************************
Descripcion       : Devuelve las observaciones bloqueo activos por cliente
Fecha Creacion    : 04/11/2015
Autor             : Gonzalo Huertas
***************************************************************************/
FUNCTION MAE_FN_DEVUE_OBS_BLOQ_X_CLIE
(psOidCliente NUMBER
)
RETURN VARCHAR2
IS
  lsDescripciones VARCHAR2(4000);

 BEGIN

lsDescripciones := '';

  FOR x IN (select obs_bloq
              from mae_clien_bloqu
             where clie_oid_clie = psOidCliente
               and fec_desb is null) LOOP

 lsDescripciones := lsDescripciones || x.obs_bloq ||  ',';

END LOOP;

RETURN  SUBSTR(TRIM(lsDescripciones),1,LENGTH(TRIM(lsDescripciones))-1);

EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_FN_DEVUE_OBS_BLOQ_X_CLIE: '||ls_sqlerrm);
END MAE_FN_DEVUE_OBS_BLOQ_X_CLIE;

/***************************************************************************
Descripcion       : Devuelve las observaciones bloqueo y sus descripciones x cliente
Fecha Creacion    : 25/11/2015
Autor             : Karina Valencia
***************************************************************************/
FUNCTION MAE_FN_DEVUE_OBS_MOTI_BLOQ(psOidCliente NUMBER) RETURN VARCHAR2 IS
  lsDescripciones VARCHAR2(4000);

BEGIN

  lsDescripciones := '';

  FOR x IN (select val_moti_bloq || '  ' || obs_bloq as descripcion
              from mae_clien_bloqu
             where clie_oid_clie = psOidCliente
               and fec_desb is null) LOOP

    lsDescripciones := lsDescripciones || x.descripcion || ',';

  END LOOP;

  RETURN SUBSTR(TRIM(lsDescripciones),  1, LENGTH(TRIM(lsDescripciones)) - 1);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,  'ERROR MAE_FN_DEVUE_OBS_MOTI_BLOQ: ' ||  ls_sqlerrm);

END MAE_FN_DEVUE_OBS_MOTI_BLOQ;

END MAE_PKG_REPOR;
/
