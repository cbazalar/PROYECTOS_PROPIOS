CREATE OR REPLACE PACKAGE "INT_PKG_LARIS" IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Interfaz que envía estimados a Yobel
Fecha Creacion    : 17/04/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo  : Tipo Periodo
		  psNumLote    : Numero Lote

***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_ESTIM_YOBEL
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psTipoPeriodo VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2);
/***************************************************************************
Descripcion       : Interfaz que envía documentos cabeceras complemento
Fecha Creacion    : 11/09/2009
Autor             : Cristhian Roman
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo  : Tipo Periodo
		  psNumLote    : Numero Lote

***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_DOCUM_CACOM
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psTipoPeriodo VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 1
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR1 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 2
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR2 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 3
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR3 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2) ;

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 4
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR4 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 5
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
          psCodigoMarca   : Codigo Marca
          psCodigoCanal   : Codigo Canal
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR5 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoMarca    VARCHAR2,
                								 psCodigoCanal    VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 7 cabecera
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR7_CABEC (psCodigoPais     VARCHAR2,
                      								 psCodigoPeriodo  VARCHAR2,
                      								 psTipoPeriodo    VARCHAR2,
                      								 psNumLote        VARCHAR2,
                      								 psCodigoSistema  VARCHAR2,
                      								 psCodigoInterfaz VARCHAR2,
                      								 psNombreArchivo  VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 7 detalles
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR7_DETAL (psCodigoPais     VARCHAR2,
                    									 psCodigoPeriodo  VARCHAR2,
                    									 psTipoPeriodo    VARCHAR2,
                    									 psNumLote        VARCHAR2,
                    									 psCodigoSistema  VARCHAR2,
                    									 psCodigoInterfaz VARCHAR2,
                    									 psNombreArchivo  VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envia la LAR 9
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR9 (psCodigoPais     VARCHAR2,
              									 psCodigoPeriodo  VARCHAR2,
              									 psCodigoSistema  VARCHAR2,
              									 psCodigoInterfaz VARCHAR2,
              									 psNombreArchivo  VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 10
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
          psCodigoMarca   : Codigo Marca
          psCodigoCanal   : Codigo Canal
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR10 (psCodigoPais     VARCHAR2,
                									psCodigoPeriodo  VARCHAR2,
                 									psCodigoMarca    VARCHAR2,
                									psCodigoSistema  VARCHAR2,
                									psCodigoInterfaz VARCHAR2,
                									psNombreArchivo  VARCHAR2);


/***************************************************************************
Descripcion       : Interfaz que envia la LAR 11
Fecha Creacion    : 15/01/2013
Autor             : Danny Amaro
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
          psCodigoMarca   : Codigo Marca
          psCodigoCanal   : Codigo Canal
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR11 (psCodigoPais     VARCHAR2,
                									psCodigoPeriodo  VARCHAR2,
                 									psCodigoMarca    VARCHAR2,
                									psCodigoSistema  VARCHAR2,
                									psCodigoInterfaz VARCHAR2,
                									psNombreArchivo  VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envia la LAR 8
Fecha Creacion    : 28/02/2013
Autor             : Danny Amaro
Parametros:
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo Marca
          psCodigoSistema : Codigo Sistema
          psCodigoInterfaz: Codigo Interfaz
          psNombreArchivo : Nombre Archivo
		      psNumeroLote    : Numero Lote
          psFecha         : Fecha Proceso
          psDesde         : Numero Interno Desde
          psHasta         : Numero Interno Hasta
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR8 (psCodigoPais     VARCHAR2,
                                 psCodigoPeriodo  VARCHAR2,
                                 psCodigoMarca    VARCHAR2,
                                 psCodigoSistema  VARCHAR2,
                                 psCodigoInterfaz VARCHAR2,
                                 psNombreArchivo  VARCHAR2,
                                 psNumeroLote     VARCHAR2,
                                 psFecha		      VARCHAR2,
                                 psDesde		      VARCHAR2,
                                 psHasta		      VARCHAR2);

/***************************************************************************
Descripcion       : Proceso de registro de Historico de Carga de Archivo de
                    Lotes YOBEL
Fecha Creacion    : 06/03/2013
Autor             : Danny Amaro
Parametros:
          psCodigoPais    : Codigo Pais
***************************************************************************/
PROCEDURE INT_PR_LAR_REGIS_HISTO_YOBEL (psCodigoPais     VARCHAR2);
FUNCTION LAR_FN_IND_SERV_RECL2 (
   p_oid_soli_cabe   IN   NUMBER,
   p_oid_peri        IN   NUMBER
)
   RETURN VARCHAR2;

/**************************************************************************
Descripcion         : NTERFAZ PARA ENVIAR DATOS A TIS2
Fecha Creación      : 21/10/2013
Autor               : Ivan Tocto
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_ARCHI_TIS2
(
  pscodigopais       VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz   VARCHAR2,
  psnombrearchivo    VARCHAR2,
  psnumerolote    VARCHAR2
);

/***************************************************************************
Descripcion     : Recepcion de variables de Carga de Informacion Tracking
Fecha Creacion  : 19/11/2015
Autor           : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Archivo
***************************************************************************/
  PROCEDURE INT_PR_LAR_RECEP_CARGA_TRACK
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) ;

END INT_PKG_LARIS;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_LARIS" IS

/***************************************************************************
Descripcion       : Interfaz que envía estimados a Yobel
Fecha Creacion    : 17/04/2009
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo  : Tipo Periodo
		  psNumLote    : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_ESTIM_YOBEL
  (psCodigoPais VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psTipoPeriodo VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2)
IS
   CURSOR c_interfaz IS
  SELECT DISTINCT E.COD_PERI, B.COD_SAP, C.VAL_I18N AS DES_PRODUCTO, NVL (A.NUM_UNID_ESTI, 0) NUM_UNID_ESTIM
           FROM APE_ESTIM_PRODU A,
                MAE_PRODU B,
                GEN_I18N_SICC_PAIS C,
                CRA_PERIO D,
                SEG_PERIO_CORPO E
          WHERE A.PROD_OID_PROD = B.OID_PROD
            AND B.OID_PROD = C.VAL_OID
            AND C.ATTR_ENTI = 'MAE_PRODU'
            AND A.PERD_OID_PERI = D.OID_PERI
            AND D.PERI_OID_PERI = E.OID_PERI
            AND E.COD_PERI = psCodigoPeriodo
  UNION
   SELECT DISTINCT J.COD_PERI, B.COD_SAP, C.VAL_I18N AS DES_PRODUCTO, NVL (G.NUM_UNID_ESTI, 0) NUM_UNID_ESTIM
           FROM PRE_OFERT F,
                PRE_OFERT_DETAL G,
                PRE_MATRI_FACTU_CABEC H,
                CRA_PERIO I,
                SEG_PERIO_CORPO J,
                MAE_PRODU B,
                GEN_I18N_SICC_PAIS C
          WHERE F.OID_OFER = G.OFER_OID_OFER
            AND F.MFCA_OID_CABE = H.OID_CABE
            AND H.PERD_OID_PERI = I.OID_PERI
            AND I.PERI_OID_PERI = J.OID_PERI
            AND G.PROD_OID_PROD = B.OID_PROD
            AND B.OID_PROD = C.VAL_OID
            AND C.ATTR_ENTI = 'MAE_PRODU'
            AND J.COD_PERI = psCodigoPeriodo
            AND G.PROD_OID_PROD NOT IN (
                   SELECT B.OID_PROD
                     FROM APE_ESTIM_PRODU A,
                          MAE_PRODU B,
                          CRA_PERIO D,
                          SEG_PERIO_CORPO E
                    WHERE A.PROD_OID_PROD = B.OID_PROD
                      AND A.PERD_OID_PERI = D.OID_PERI
                      AND D.PERI_OID_PERI = E.OID_PERI
                      AND E.COD_PERI = psCodigoPeriodo)
   UNION
    SELECT DISTINCT SPC.COD_PERI, P.COD_SAP,
                (SELECT I.VAL_I18N
                   FROM GEN_I18N_SICC I
                  WHERE I.IDIO_OID_IDIO = 1
                    AND I.ATTR_ENTI = 'MAE_PRODU'
                    AND I.VAL_OID = P.OID_PROD) AS DES_PRODUCTO,
                0 NUM_UNID_ESTIM
           FROM INC_CONCU_PARAM_GENER CPG,
                INC_VERSI_CONCU VC,
                INC_PARAM_GENER_PREMI PGP,
                INC_PARAM_NIVEL_PREMI PNP,
                INC_PREMI_ARTIC PA,
                INC_LOTE_PREMI_ARTIC LPA,
                INC_ARTIC_LOTE AL,
                MAE_PRODU P,
                OWN_COMUN.INC_PLANT_CONCU PLA,
                CRA_PERIO CP,
                SEG_PERIO_CORPO SPC
          WHERE CPG.OID_PARA_GRAL = PGP.COPA_OID_PARA_GRAL
            AND CPG.OID_PARA_GRAL = VC.COPA_OID_PARA_GRAL
            AND PGP.OID_PARA_GENE_PREM = PNP.PAGP_OID_PARA_GENE_PREM(+)
            AND PNP.OID_PARA_NIVE_PREM = PA.PANP_OID_PARA_NIVE_PREM(+)
            AND PA.OID_PREM_ARTI = LPA.PRAR_OID_PREM_ARTI(+)
            AND LPA.OID_LOTE_PREM_ARTI = AL.LOPA_OID_LOTE_PREM_ARTI(+)
            AND AL.PROD_OID_PROD = P.OID_PROD
            AND CPG.PLC2_OID_PLAN_CONC = PLA.OID_PLAN_CONC
            AND PGP.PERD_OID_PERI = CP.OID_PERI
            AND CP.PERI_OID_PERI = SPC.OID_PERI
            AND SPC.COD_PERI = psCodigoPeriodo;

   TYPE interfazTipo IS RECORD
   (  codigoPeriodo       SEG_PERIO_CORPO.COD_PERI%TYPE,
      codigoProducto        MAE_PRODU.COD_SAP%TYPE,
      descripcionProducto   GEN_I18N_SICC_PAIS.VAL_I18N%TYPE,
      unidades       APE_ESTIM_PRODU.NUM_UNID_ESTI%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsFlag       VARCHAR2(1):=' ';
BEGIN

      /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz;
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
                  lsLinea :=  psCodigoPais              		    ||';'||
                          	  psNumLote                			    ||';'||
                        	  psTipoPeriodo              	   	    ||';'||
                              interfazRecord(x).codigoPeriodo       ||';'||
                              interfazRecord(x).codigoProducto      ||';'||
                              interfazRecord(x).descripcionProducto ||';'||
                              interfazRecord(x).unidades      		||';'||
                              lsFlag;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_ESTIM_YOBEL: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_ESTIM_YOBEL;
/***************************************************************************
Descripcion       : Interfaz que envía documentos cabeceras complemento
Fecha Creacion    : 11/09/2009
Autor             : Cristhian Roman
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo  : Tipo Periodo
		  psNumLote    : Numero Lote

***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_DOCUM_CACOM(psCodigoPais     VARCHAR2,
                                       psCodigoPeriodo  VARCHAR2,
                                       psTipoPeriodo    VARCHAR2,
                                       psNumLote        VARCHAR2,
                                       psCodigoSistema  VARCHAR2,
                                       psCodigoInterfaz VARCHAR2,
                                       psNombreArchivo  VARCHAR2) IS

  CURSOR c_interfaz(lsoidperiodo NUMBER) IS
  SELECT DISTINCT con.clie_oid_clie AS oidcliente,
                  cli.cod_clie AS codigocliente,
                  pq_apl_aux.valor_gen_i18n_sicc(1,
                                                 nvl(trat.oid_tipo_trat,
                                                     1),
                                                 'MAE_TIPO_TRATA') AS tratamiento,
                  cli.val_ape1 AS apellidopaterno,
                  cli.val_ape2 AS apellidomaterno,
                  cli.val_apel_casa AS apellidocasada,
                  val_nom1 AS nombre1,
                  val_nom2 AS nombre2,
                  via.cod_tipo_via AS codigotipovia,
                  dir.val_nomb_via AS nombrevia,
                  dir.num_ppal AS numeroprincipal,
                  dir.val_inte AS numerointerior,
                  dir.val_manz AS manzana,
                  dir.val_lote AS lote,
                  dir.val_km AS km,
                  dir.val_manz AS bloque,
                  decode(gen_pkg_gener.gen_fn_param_pais(psCodigoPais,'OCR','055'),'1',dir.val_barr||' ','')||dir.val_obse AS referencia,
                  CASE
                    WHEN EXISTS (SELECT 1
                            FROM mav_param_geren_zona pagz
                           WHERE pagz.sbti_oid_subt_clie = con.sbti_oid_subt_clie) THEN
                     nvl((SELECT max(zon.cod_zona)
                        FROM zon_zona zon
                       WHERE zon.clie_oid_clie = con.clie_oid_clie),
                       (select cod_zona from zon_zona where oid_zona=con.zzon_oid_zona))
                    ELSE (select cod_zona from zon_zona where oid_zona=con.zzon_oid_zona)
                     /*(SELECT max(zon.cod_zona)
                        FROM mae_clien_direc       dir,
                             zon_terri_admin       zta,
                             zon_secci             sec,
                             zon_zona              zon
                       WHERE dir.clie_oid_clie = con.clie_oid_clie
                         AND dir.ind_dire_ppal = 1
                         and dir.ind_elim = 0
                         --AND uad.perd_oid_peri_fin IS NULL
                         and zta.ind_borr=0
                         AND zta.terr_oid_terr = dir.terr_oid_terr
                         AND sec.oid_secc = zta.zscc_oid_secc
                         AND zon.oid_zona = sec.zzon_oid_zona)*/
                  END codigozona,
                  --CASE
                  --  WHEN EXISTS (SELECT 1
                  --          FROM mav_param_geren_zona pagz
                  --         WHERE pagz.sbti_oid_subt_clie = con.sbti_oid_subt_clie) THEN
                  --   0
                  --  ELSE
                    (select cod_terr from zon_terri where oid_terr=con.terr_oid_terr)
                     /*(SELECT max(zte.cod_terr)
                        FROM mae_clien_direc       dir,
                             zon_terri_admin       zta,
                             zon_terri             zte,
                             zon_secci             sec,
                             zon_zona              zon
                       WHERE dir.clie_oid_clie = con.clie_oid_clie
                         AND dir.ind_dire_ppal = 1
                         and dir.ind_elim = 0
                         --AND uad.perd_oid_peri_fin IS NULL
                         and zta.ind_borr=0
                         AND zta.terr_oid_terr = dir.terr_oid_terr
                         AND zta.terr_oid_terr = zte.oid_terr
                         AND sec.oid_secc = zta.zscc_oid_secc
                         AND zon.oid_zona = sec.zzon_oid_zona)*/
                  --END
                  codigoterritorio,
                  nvl((select VAL_LAR
                          from LAR_TIPO_CLIEN_VIP
                         where OID_CLAS = (select cc.CLAS_OID_CLAS
                                             from mae_clien_tipo_subti cts,
                                                  mae_clien_clasi cc
                                            where cts.OID_CLIE_TIPO_SUBT = cc.CTSU_OID_CLIE_TIPO_SUBT
                                              and cts.CLIE_OID_CLIE = cli.oid_clie
                                              and cc.TCCL_OID_TIPO_CLASI in (select OID_TIPO_CLAS
                                                                              from LAR_TIPO_CLIEN_VIP
                                                                             --where rownum=1
                                                                             )
                                              and rownum=1)),
                          case (select niri_oid_nive_ries
                                  from mae_clien_datos_adici
                                 where clie_oid_clie = cli.oid_clie)
                            when 4 then 9
                            when 5 then 8
                            when 6 then 7
                            else 0
                          end) indicadorvip,

                  substr(dir.cod_unid_geog,
                         1,
                         6) AS ubigeo1,
                  substr(dir.cod_unid_geog,
                         7,
                         6) AS ubigeo2,
                  substr(dir.cod_unid_geog,
                         13,
                         6) AS ubigeo3,
                  substr(dir.cod_unid_geog,
                         19,
                         6) AS ubigeo4,
                  substr(dir.cod_unid_geog,
                         25,
                         6) AS ubigeo5,
                  substr(dir.cod_unid_geog,
                         31,
                         6) AS ubigeo6,
                  substr(dir.cod_unid_geog,
                         37,
                         6) AS ubigeo7,
                  substr(dir.cod_unid_geog,
                         43,
                         6) AS ubigeo8
    FROM ped_solic_cabec con,
         mae_clien       cli,
         mae_tipo_trata  trat,
         mae_clien_direc dir,
         seg_tipo_via    via
   WHERE con.perd_oid_peri = lsoidperiodo
     --and nvl(con.modu_oid_modu,1)<>23
     AND con.tspa_oid_tipo_soli_pais IN
         (SELECT tspa_oid_tipo_soli_pais FROM int_lar_tipo_solici_pedido_dis)
     AND con.fec_fact IS NOT NULL
     AND con.ind_inte_lari_gene = 0
     AND con.num_unid_aten_tota > 0
     AND con.clie_oid_clie = cli.oid_clie
     AND cli.val_trat = trat.cod_tipo_trat
     AND con.clie_oid_clie = dir.clie_oid_clie
     AND (dir.ind_elim = 0 OR dir.ind_elim <> 1)
     --AND dir.ind_dire_ppal = 1
     and dir.oid_clie_dire=decode(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'COD_TIPO_DIRE_ENTR'), null, (select max(oid_clie_dire) from mae_clien_direc where ind_elim=0 and ind_dire_ppal=1 and clie_oid_clie=cli.oid_clie),
                                nvl(
                                           (select max(oid_clie_dire) from mae_clien_direc where ind_elim=0 and tidc_oid_tipo_dire=2007 and clie_oid_clie=cli.oid_clie)
                                           ,(select max(oid_clie_dire) from mae_clien_direc where ind_elim=0 and ind_dire_ppal=1 and clie_oid_clie=cli.oid_clie)
                                  )
                                  )
     AND via.oid_tipo_via = dir.tivi_oid_tipo_via;

   TYPE interfazTipo IS RECORD
   (  oidcliente       		 ped_solic_cabec.CLIE_OID_CLIE%TYPE,
      codigocliente          MAE_CLIEN.COD_CLIE%TYPE,
      tratamiento      	   	 VARCHAR2(10),
      apellidopaterno        MAE_CLIEN.VAL_APE1%TYPE,
	  apellidomaterno		 MAE_CLIEN.VAL_APE2%TYPE,
	  apellidocasada		 MAE_CLIEN.VAL_APEL_CASA%TYPE,
	  nombre1				 MAE_CLIEN.VAL_NOM1%TYPE,
	  nombre2				 MAE_CLIEN.VAL_NOM2%TYPE,
	  codigotipovia			 seg_tipo_via.COD_TIPO_VIA%TYPE,
	  nombrevia				 mae_clien_direc.VAL_NOMB_VIA%TYPE,
	  numeroprincipal		 mae_clien_direc.NUM_PPAL%TYPE,
	  numerointerior		 mae_clien_direc.VAL_INTE%TYPE,
	  manzana				 mae_clien_direc.VAL_MANZ%TYPE,
	  lote					 mae_clien_direc.VAL_LOTE%TYPE,
	  km					 mae_clien_direc.VAL_KM%TYPE,
	  bloque				 mae_clien_direc.VAL_MANZ%TYPE,
	  referencia			 mae_clien_direc.VAL_OBSE%TYPE,
	  codigozona			 ZON_ZONA.COD_ZONA%TYPE,
	  codigoterritorio		 ZON_TERRI.COD_TERR%TYPE,
	  indicadorvip			 VARCHAR(1),
	  ubigeo1				 VARCHAR(6),
	  ubigeo2				 VARCHAR(6),
	  ubigeo3				 VARCHAR(6),
	  ubigeo4				 VARCHAR(6),
	  ubigeo5				 VARCHAR(6),
	  ubigeo6				 VARCHAR(6),
	  ubigeo7				 VARCHAR(6),
	  ubigeo8				 VARCHAR(6)
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo seg_perio_corpo.OID_PERI%type;

BEGIN
	   lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
     

      /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz(lsoidperiodo);
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
                  lsLinea :=  nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_PAIS_LAR'),RPAD(psCodigoPais,3,' '))		    ||';'||
				  		  	  substr(psNumLote,1,8)	         ||';'||
                          	  substr(psNumLote,9,4)			||';'||
							  psTipoPeriodo							||';'||
                        	  psCodigoPeriodo              	   	    ||';'||
                              interfazRecord(x).codigocliente       ||';'||
                              interfazRecord(x).tratamiento      	||';'||
                              interfazRecord(x).apellidopaterno 	||';'||
                              interfazRecord(x).apellidomaterno     ||';'||
							  interfazRecord(x).apellidocasada      ||';'||
                              interfazRecord(x).nombre1      		||';'||
                              interfazRecord(x).nombre2 			||';'||
                              interfazRecord(x).codigotipovia       ||';'||
							  interfazRecord(x).nombrevia       	||';'||
                              interfazRecord(x).numeroprincipal     ||';'||
                              interfazRecord(x).numerointerior 		||';'||
                              interfazRecord(x).manzana      		||';'||
							  interfazRecord(x).lote       			||';'||
                              interfazRecord(x).km      			||';'||
                              interfazRecord(x).bloque 				||';'||
                              interfazRecord(x).referencia      	||';'||
							  interfazRecord(x).codigozona          ||';'||
                              lpad(interfazRecord(x).codigoterritorio,6,0)    ||';'||
                              interfazRecord(x).indicadorvip 		||';'||
                              interfazRecord(x).ubigeo1      		||';'||
							  interfazRecord(x).ubigeo2       		||';'||
                              interfazRecord(x).ubigeo3      		||';'||
                              interfazRecord(x).ubigeo4 			||';'||
                              interfazRecord(x).ubigeo5      		||';'||
							  interfazRecord(x).ubigeo6 			||';'||
                              interfazRecord(x).ubigeo7      		||';'||
                              interfazRecord(x).ubigeo8;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_DOCUM_CACOM: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_DOCUM_CACOM;

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 1
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR1 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2)
IS
  CURSOR c_interfaz(lsoidperiodo NUMBER) IS
	  SELECT pro.oid_prod oidproducto,
	         pro.cod_sap codigoitem,
    			 pro.des_cort descripcionproducto,
    			 mpr.cod_marc_prod marca,
    			 une.cod_unid_nego unidadnegocio,
    			 neg.cod_nego negocio,
    			 esp.cod_esta_prod estatusproducto,
    			 SUM (mfc.num_unid_esti) totalunidadesproducto,
    			 pro.cod_ind_dent_caja indicadordentrofueracaja,
    			 pro.val_dime_larg largocentimetros,
    			 pro.val_dime_anch anchocentimetros,
    			 pro.val_dime_alto altocentimetros,
    			 pro.val_peso pesobrutogramos
		  FROM pre_matri_factu_cabec mfc,
    			 pre_matri_factu mf,
    			 cra_perio per,
    			 pre_ofert_detal ofd,
    			 mae_produ pro,
    			 mae_negoc neg,
    			 seg_marca_produ mpr,
    			 mae_unida_negoc une,
    			 mae_estat_produ esp
	   WHERE mfc.perd_oid_peri = per.oid_peri
  		 AND mf.mfca_oid_cabe = mfc.oid_cabe
	  	 AND mf.ofde_oid_deta_ofer = ofd.oid_deta_ofer
		   AND ofd.prod_oid_prod = pro.oid_prod
		   AND pro.nego_oid_nego = neg.oid_nego(+)
		   AND pro.mapr_oid_marc_prod = mpr.oid_marc_prod(+)
		   AND pro.uneg_oid_unid_nego = une.oid_unid_nego(+)
		   AND pro.meud_oid_esta_prod = esp.oid_esta_prod(+)
		   AND mfc.perd_oid_peri = lsoidperiodo
		   and nvl(pro.IND_PROD_SERV,0) <> 1
	GROUP BY pro.oid_prod,
    			 pro.cod_sap,
    			 pro.des_cort,
    			 mpr.cod_marc_prod,
    			 une.cod_unid_nego,
    			 neg.cod_nego,
    			 esp.cod_esta_prod,
    			 pro.cod_ind_dent_caja,
    			 pro.val_dime_larg,
    			 pro.val_dime_anch,
    			 pro.val_dime_alto,
    			 pro.val_peso
	ORDER BY pro.cod_sap;

   TYPE interfazTipo IS RECORD(
		oidproducto                 mae_produ.oid_prod%TYPE,
		codigoitem                  mae_produ.cod_sap%TYPE,
		descripcionproducto         mae_produ.des_cort%TYPE,
		marca                       seg_marca_produ.cod_marc_prod%TYPE,
		unidadnegocio               mae_unida_negoc.cod_unid_nego%TYPE,
		negocio                     mae_negoc.cod_nego%TYPE,
		estatusproducto             mae_estat_produ.cod_esta_prod%TYPE,
		totalunidadesproducto       pre_matri_factu_cabec.num_unid_esti%TYPE,
		indicadordentrofueracaja    mae_produ.cod_ind_dent_caja%TYPE,
		largocentimetros            mae_produ.val_dime_larg%TYPE,
		anchocentimetros            mae_produ.val_dime_anch%TYPE,
		altocentimetros             mae_produ.val_dime_alto%TYPE,
		pesobrutogramos             mae_produ.val_peso%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;
  c_compania          constant varchar2(3) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_PAIS_LAR'),RPAD(psCodigoPais,3,' '));

BEGIN
	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidperiodo);
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
              lsLinea :=  c_compania              		    			      ||';'||
                      	  to_char(sysdate,'YYYYMMDD')       			    ||';'||
                    	    psNumLote                         	   	    ||';'||
                          psTipoPeriodo                     		    	||';'||
                          psCodigoPeriodo                   			    ||';'||
                          interfazRecord(x).codigoitem      			    ||';'||
                          interfazRecord(x).descripcionproducto			  ||';'||
            						  interfazRecord(x).marca                   	||';'||
            						  interfazRecord(x).unidadnegocio           	||';'||
            						  interfazRecord(x).negocio                 	||';'||
            						  interfazRecord(x).estatusproducto        	 	||';'||
            						  interfazRecord(x).totalunidadesproducto   	||';'||
            						  interfazRecord(x).indicadordentrofueracaja  ||';'||
            						  interfazRecord(x).largocentimetros          ||';'||
            						  interfazRecord(x).anchocentimetros			    ||';'||
            						  interfazRecord(x).altocentimetros 			    ||';'||
            						  interfazRecord(x).pesobrutogramos ;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR1: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR1;

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 2
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR2 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2)
IS
 CURSOR c_interfaz(lsoidperiodo NUMBER) IS
	SELECT *
	  FROM (SELECT pro.oid_prod producto,
                 pro.cod_sap codigosap,
      					 (SELECT gen.val_i18n
      						FROM gen_i18n_sicc_pais gen
      					   WHERE gen.attr_enti = 'MAE_PRODU'
      						 AND gen.attr_num_atri = 1
      						 AND gen.val_oid = pro.oid_prod
      						 AND gen.idio_oid_idio = 1) descripcionsap,
      					 marc.cod_marc_prod codigomarca,
      					 uneg.cod_unid_nego codigounidadnegocio,
      					 nego.cod_nego codigonegocio,
      					 esta.cod_esta_prod estatusproducto,
      					 SUM (pos.num_unid_aten) totalunidades,
      					 DECODE (pro.cod_ind_dent_caja,
      							 NULL, 0,
      							 1
      							) indicadordentrofueracajabolsa,
      					 val_dime_larg largocentimetros,
      					 val_dime_anch anchocentimetros,
      					 val_dime_alto altocentimetros,
                 val_peso pesobrutogramos
				    FROM ped_solic_cabec cab,
      					 ped_solic_cabec cons,
      					 ped_solic_posic pos,
      					 mae_produ pro,
      					 seg_marca_produ marc,
      					 mae_unida_negoc uneg,
      					 mae_negoc nego,
      					 mae_estat_produ esta,
      					 ped_tipo_solic_pais tsp,
      					 ped_tipo_solic ts,
      					 int_lar_tipo_solici_pedido_dis tspd
  			   WHERE pos.soca_oid_soli_cabe = cab.oid_soli_cabe
     				 AND cab.soca_oid_soli_cabe = cons.oid_soli_cabe
  	  			 AND pos.prod_oid_prod = pro.oid_prod
  		  		 AND pro.mapr_oid_marc_prod = marc.oid_marc_prod
  			  	 AND pro.uneg_oid_unid_nego = uneg.oid_unid_nego(+)
  		  		 AND pro.nego_oid_nego = nego.oid_nego(+)
  		  		 AND pro.meud_oid_esta_prod = esta.oid_esta_prod(+)
  		  		 AND cab.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
  		  		 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
  		  		 AND cons.perd_oid_peri = lsoidperiodo
  		  		 AND cons.ind_inte_lari_gene = 0
  		  		 AND cons.fec_fact IS NOT NULL
  		  		 AND cons.ind_ts_no_conso = 0
  		  		 AND (cons.ind_pedi_prue = 0 OR cons.ind_pedi_prue IS NULL)
  		  		 AND cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
  		  		 AND pos.espo_oid_esta_posi <> 2
  		  		 and nvl(pro.IND_PROD_SERV,0) <> 1
			  GROUP BY pro.oid_prod,
      					 pro.cod_sap,
      					 pro.des_cort,
      					 marc.cod_marc_prod,
      					 uneg.cod_unid_nego,
      					 nego.cod_nego,
      					 esta.cod_esta_prod,
      					 pro.cod_ind_dent_caja,
      					 val_dime_larg,
      					 val_dime_anch,
      					 val_dime_alto,
      					 val_peso)
	 WHERE totalunidades > 0;

   TYPE interfazTipo IS RECORD(
		oidproducto                 mae_produ.oid_prod%TYPE,
		codigoitem                  mae_produ.cod_sap%TYPE,
		descripcionproducto         gen_i18n_sicc_pais.val_i18n%TYPE,
		marca                       seg_marca_produ.cod_marc_prod%TYPE,
		unidadnegocio               mae_unida_negoc.cod_unid_nego%TYPE,
		negocio                     mae_negoc.cod_nego%TYPE,
		estatusproducto             mae_estat_produ.cod_esta_prod%TYPE,
		totalunidadesproducto       ped_solic_posic.num_unid_aten%TYPE,
		indicadordentrofueracaja    mae_produ.cod_ind_dent_caja%TYPE,
		largocentimetros            mae_produ.val_dime_larg%TYPE,
		anchocentimetros            mae_produ.val_dime_anch%TYPE,
		altocentimetros             mae_produ.val_dime_alto%TYPE,
		pesobrutogramos             mae_produ.val_peso%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;
  c_compania          constant varchar2(3) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_PAIS_LAR'),RPAD(psCodigoPais,3,' '));

BEGIN
	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidperiodo);
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
              lsLinea :=  c_compania              		    			      ||';'||
                      	  to_char(sysdate,'YYYYMMDD')       			    ||';'||
                    	    psNumLote                         	   	    ||';'||
                          psTipoPeriodo                     			    ||';'||
                          psCodigoPeriodo                   		    	||';'||
                          interfazRecord(x).codigoitem      			    ||';'||
                          interfazRecord(x).descripcionproducto		    ||';'||
            						  interfazRecord(x).marca                   	||';'||
            						  interfazRecord(x).unidadnegocio           	||';'||
            						  interfazRecord(x).negocio                 	||';'||
            						  interfazRecord(x).estatusproducto        	 	||';'||
            						  interfazRecord(x).totalunidadesproducto   	||';'||
            						  interfazRecord(x).indicadordentrofueracaja  ||';'||
            						  interfazRecord(x).largocentimetros          ||';'||
            						  interfazRecord(x).anchocentimetros			    ||';'||
            						  interfazRecord(x).altocentimetros 			    ||';'||
            						  interfazRecord(x).pesobrutogramos ;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR2: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR2;

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 3
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR3 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2)
IS
 CURSOR c_interfaz(lsoidperiodo NUMBER) IS
	SELECT DISTINCT *
	  FROM (SELECT ZON.OID_ZONA OID_ZONA,
				         ZON.COD_ZONA CODIGOZONA,
				         ZON.DES_ZONA DESCRIPCIONZONA
  			    FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS TSPD,
      				   PED_SOLIC_CABEC                SOC,
      				   ZON_ZONA                       ZON
  			   WHERE SOC.ZZON_OID_ZONA = ZON.OID_ZONA
  			     AND SOC.PERD_OID_PERI = lsoidperiodo
  			     AND SOC.IND_INTE_LARI_GENE = 0
  			     AND SOC.IND_TS_NO_CONSO = 0
  			     AND SOC.NUM_UNID_ATEN_TOTA > 0
  			     AND (SOC.IND_PEDI_PRUE = 0 OR SOC.IND_PEDI_PRUE IS NULL)
  			     AND SOC.FEC_FACT IS NOT NULL
  			     AND SOC.TSPA_OID_TIPO_SOLI_PAIS = TSPD.TSPA_OID_TIPO_SOLI_PAIS
  			     AND NOT EXISTS (SELECT 1
  					                   FROM MAV_PARAM_GEREN_ZONA PAGZ
  					                  WHERE PAGZ.SBTI_OID_SUBT_CLIE = SOC.SBTI_OID_SUBT_CLIE)
  			  UNION ALL
  			  SELECT ZON1.OID_ZONA OID_ZONA,
      				   ZON1.COD_ZONA CODIGOZONA,
      				   ZON1.DES_ZONA DESCRIPCIONZONA
  			    FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS TSPD1,
      				   PED_SOLIC_CABEC                SOC1,
      				   ZON_ZONA                       ZON1
  			   WHERE SOC1.CLIE_OID_CLIE = ZON1.CLIE_OID_CLIE
  			     AND SOC1.PERD_OID_PERI = lsoidperiodo
  			     AND SOC1.IND_INTE_LARI_GENE = 0
  			     AND SOC1.IND_TS_NO_CONSO = 0
  			     AND SOC1.NUM_UNID_ATEN_TOTA > 0
  			     AND (SOC1.IND_PEDI_PRUE = 0 OR SOC1.IND_PEDI_PRUE IS NULL)
  			     AND SOC1.FEC_FACT IS NOT NULL
  			     AND SOC1.TSPA_OID_TIPO_SOLI_PAIS = TSPD1.TSPA_OID_TIPO_SOLI_PAIS
  			     AND EXISTS (SELECT 1
  					               FROM MAV_PARAM_GEREN_ZONA PAGZ1
  					              WHERE PAGZ1.SBTI_OID_SUBT_CLIE = SOC1.SBTI_OID_SUBT_CLIE))
	 ORDER BY 2;

   TYPE interfazTipo IS RECORD(
		oid_zona           ZON_ZONA.OID_ZONA%TYPE,
		codigo_zona		     ZON_ZONA.COD_ZONA%TYPE,
		descripcion_zona   ZON_ZONA.DES_ZONA%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;
  c_compania          constant varchar2(3) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_PAIS_LAR'),RPAD(psCodigoPais,3,' '));

BEGIN
	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidperiodo);
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
              lsLinea :=  c_compania              		    			  ||';'||
                      	  to_char(sysdate,'YYYYMMDD')       			||';'||
                    	    psNumLote                         	   	||';'||
                          psTipoPeriodo                     			||';'||
                          psCodigoPeriodo                   			||';'||
                          interfazRecord(x).codigo_zona      			||';'||
                          interfazRecord(x).descripcion_zona;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR3: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR3;


/***************************************************************************
Descripcion       : Interfaz que envía la LAR 4
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR4 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2)
IS
 CURSOR c_interfaz(lsoidperiodo NUMBER) IS
	SELECT DISTINCT *
	  FROM (SELECT TER.OID_TERR OIDTERRITORIO,
      				   TER.COD_TERR CODIGOTERRITORIO,
      				   ZON.OID_ZONA OIDZONA,
      				   ZON.COD_ZONA CODZONA
			      FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS TSPD,
      				   PED_SOLIC_CABEC                SOC,
      				   ZON_ZONA                       ZON,
      				   ZON_TERRI                      TER
			     WHERE SOC.TERR_OID_TERR = TER.OID_TERR
			       AND SOC.ZZON_OID_ZONA = ZON.OID_ZONA
			       AND SOC.PERD_OID_PERI = lsoidperiodo
			       AND SOC.IND_INTE_LARI_GENE = 0
			       AND SOC.IND_TS_NO_CONSO = 0
			       AND SOC.NUM_UNID_ATEN_TOTA > 0
			       AND (SOC.IND_PEDI_PRUE = 0 OR SOC.IND_PEDI_PRUE IS NULL)
			       AND SOC.FEC_FACT IS NOT NULL
			       AND SOC.TSPA_OID_TIPO_SOLI_PAIS = TSPD.TSPA_OID_TIPO_SOLI_PAIS
			       AND NOT EXISTS(SELECT 1
        					            FROM MAV_PARAM_GEREN_ZONA PAGZ
        					           WHERE PAGZ.SBTI_OID_SUBT_CLIE = SOC.SBTI_OID_SUBT_CLIE)
        	UNION ALL
        	SELECT 0             OIDTERRITORIO,
        				 0             CODIGOTERRITORIO,
        				 ZON1.OID_ZONA OIDZONA,
        				 ZON1.COD_ZONA CODZONA
        	  FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS TSPD1,
        				 PED_SOLIC_CABEC                SOC1,
        				 ZON_ZONA                       ZON1
        	WHERE SOC1.CLIE_OID_CLIE = ZON1.CLIE_OID_CLIE
      	    AND SOC1.PERD_OID_PERI = lsoidperiodo
       			AND SOC1.IND_INTE_LARI_GENE = 0
       			AND SOC1.IND_TS_NO_CONSO = 0
       			AND SOC1.NUM_UNID_ATEN_TOTA > 0
       			AND (SOC1.IND_PEDI_PRUE = 0 OR SOC1.IND_PEDI_PRUE IS NULL)
       			AND SOC1.FEC_FACT IS NOT NULL
       			AND SOC1.TSPA_OID_TIPO_SOLI_PAIS = TSPD1.TSPA_OID_TIPO_SOLI_PAIS
       			AND EXISTS(SELECT 1
        					       FROM MAV_PARAM_GEREN_ZONA PAGZ1
        					      WHERE PAGZ1.SBTI_OID_SUBT_CLIE = SOC1.SBTI_OID_SUBT_CLIE))
	 ORDER BY 2;

   TYPE interfazTipo IS RECORD(
		oid_territorio     ZON_TERRI.OID_TERR%TYPE,
		codigo_territorio  ZON_TERRI.COD_TERR%TYPE,
		oid_zona           ZON_ZONA.OID_ZONA%TYPE,
		codigo_zona        ZON_ZONA.COD_ZONA%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;
  c_compania          constant varchar2(3) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_PAIS_LAR'),RPAD(psCodigoPais,3,' '));

BEGIN
	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidperiodo);
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
              lsLinea :=  c_compania              		    			  ||';'||
                      	  to_char(sysdate,'YYYYMMDD')       			||';'||
                    	    psNumLote                         	   	||';'||
                          psTipoPeriodo                     			||';'||
                          psCodigoPeriodo                   			||';'||
                          interfazRecord(x).codigo_territorio     ||';'||
                          interfazRecord(x).codigo_zona;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR4: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR4;

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 5
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR5 (psCodigoPais     VARCHAR2,
                								 psCodigoPeriodo  VARCHAR2,
                								 psTipoPeriodo    VARCHAR2,
                								 psNumLote        VARCHAR2,
                								 psCodigoMarca    VARCHAR2,
                								 psCodigoCanal    VARCHAR2,
                								 psCodigoSistema  VARCHAR2,
                								 psCodigoInterfaz VARCHAR2,
                								 psNombreArchivo  VARCHAR2)
IS
   CURSOR c_interfaz(lsoidmarca NUMBER, lsoidcanal NUMBER) IS

	SELECT ts.oid_tipo_soli,
	       ts.cod_tipo_soli,
         (SELECT v.val_i18n
            FROM v_gen_i18n_sicc v
           WHERE v.val_oid = ts.oid_tipo_soli
             AND v.idio_oid_idio = 1
             AND v.attr_num_atri = 1
             AND v.attr_enti = 'PED_TIPO_SOLIC') AS descripsolic,
         cs.oid_clas_soli, cs.cod_clas_soli,
         (SELECT v.val_i18n
            FROM v_gen_i18n_sicc v
           WHERE v.val_oid = cs.oid_clas_soli
             AND v.idio_oid_idio = 1
             AND v.attr_num_atri = 1
             AND v.attr_enti = 'PED_CLASE_SOLIC') AS descripclase
	  FROM ped_tipo_solic_pais tsp,
  		   ped_tipo_solic ts,
  		   ped_clase_solic cs,
  		   seg_acces acc
	 WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
	   AND ts.clso_oid_clas_soli = cs.oid_clas_soli
	   AND ts.acce_oid_acce = acc.oid_acce
	   AND ts.marc_oid_marc = lsoidmarca
	   AND acc.cana_oid_cana = lsoidcanal;

   TYPE interfazTipo IS RECORD(
		oid_tipo_soli  ped_tipo_solic.oid_tipo_soli%TYPE,
		cod_tipo_soli  ped_tipo_solic.cod_tipo_soli%TYPE,
		descripsolic   v_gen_i18n_sicc.val_i18n%TYPE,
		oid_clas_soli  ped_clase_solic.oid_clas_soli%TYPE,
		cod_clas_soli  ped_clase_solic.cod_clas_soli%TYPE,
		descripclase   v_gen_i18n_sicc.val_i18n%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidmarca          ped_tipo_solic.marc_oid_marc%type;
  lsoidcanal          seg_acces.cana_oid_cana%type;
  c_compania          constant varchar2(3) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_PAIS_LAR'),RPAD(psCodigoPais,3,' '));

BEGIN
	lsoidmarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
    lsoidcanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
	/* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidmarca,lsoidcanal);
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
              lsLinea :=  c_compania              		    			  ||';'||
                      	  to_char(sysdate,'YYYYMMDD')       			||';'||
                    	    psNumLote                         	   	||';'||
                          psTipoPeriodo                     			||';'||
                          psCodigoPeriodo                   			||';'||
                          interfazRecord(x).cod_clas_soli        	||';'||
                          interfazRecord(x).descripclase          ||';'||
                          interfazRecord(x).cod_tipo_soli         ||';'||
                          interfazRecord(x).descripsolic;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR5: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR5;

/***************************************************************************
Descripcion       : Interfaz que envia la LAR 7 cabecera
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		  psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR7_CABEC (psCodigoPais     VARCHAR2,
                      								 psCodigoPeriodo  VARCHAR2,
                      								 psTipoPeriodo    VARCHAR2,
                      								 psNumLote        VARCHAR2,
                      								 psCodigoSistema  VARCHAR2,
                      								 psCodigoInterfaz VARCHAR2,
                      								 psNombreArchivo  VARCHAR2)
IS
 CURSOR c_interfaz(lsoidperiodo NUMBER) IS
	SELECT DISTINCT emp.cod_soci codigoempresa,
	                soc2.val_nume_soli numeropedido,
                  cli.cod_clie codigocliente,
					        soc.fec_fact fechapedido,
                  0,--nvl((select max(COD_TIPO_CHEQ) from ped_pedid_chequ where val_nume_soli=soc2.val_nume_soli and cod_peri=(select cod_peri from cra_perio a, seg_perio_corpo b where a.peri_oid_peri=b.oid_peri and soc2.perd_oid_peri=a.oid_peri)),0) indicadorpedidoachequear,
                  DECODE (td.cod_tipo_desp, 'EX', 1, 0) indicadorpedidoexpress,
                  clas.cod_clas_soli clasesolicitud,
                  ts.cod_tipo_soli codigotiposolicitud,
                  SUM (sop.num_unid_aten) OVER (PARTITION BY soc2.val_nume_soli ORDER BY 1)   totalunixpedido,
                  COUNT (sop.oid_soli_posi) OVER (PARTITION BY soc2.val_nume_soli ORDER BY 1) totalitemxpedido
           FROM ped_solic_posic sop,
                ped_solic_cabec soc,
                ped_solic_cabec soc2,
                mae_clien cli,
                --ped_indic_revis rev,
                ped_clase_solic clas,
                ped_tipo_despa td,
                seg_socie emp,
                ped_tipo_solic_pais tsp,
                mae_produ pro,
                ped_tipo_solic ts,
                int_lar_tipo_solici_pedido_dis tspd
          WHERE ts.clso_oid_clas_soli = clas.oid_clas_soli
            AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
            AND soc2.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
            AND soc2.soci_oid_soci = emp.oid_soci
            AND td.oid_tipo_desp = soc.tids_oid_tipo_desp
            --AND soc2.inre_oid_indi_revi = rev.oid_indi_revi(+)
            AND soc.clie_oid_clie = cli.oid_clie
            AND sop.soca_oid_soli_cabe = soc.oid_soli_cabe
            AND soc.soca_oid_soli_cabe = soc2.oid_soli_cabe
            AND soc2.perd_oid_peri = lsoidperiodo
            AND soc2.ind_inte_lari_gene = 0
            AND soc2.fec_fact IS NOT NULL
            AND soc2.ind_ts_no_conso = 0
            AND (soc2.ind_pedi_prue = 0 OR soc2.ind_pedi_prue IS NULL)
            AND sop.espo_oid_esta_posi <> 2
            AND sop.prod_oid_prod = pro.oid_prod
            AND soc2.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
            AND sop.num_unid_aten > 0
            and nvl(pro.IND_PROD_SERV,0) <> 1
            --and nvl(soc2.modu_oid_modu,1)<>23
       ORDER BY soc2.val_nume_soli;

   TYPE interfazTipo IS RECORD(
		codigoempresa                 seg_socie.cod_soci%TYPE,
		numeropedido                  ped_solic_cabec.val_nume_soli%TYPE,
		codigocliente                 mae_clien.cod_clie%TYPE,
		fechapedido                   ped_solic_cabec.fec_fact%TYPE,
		indicadorpedidoachequear      ped_indic_revis.cod_indi_revi%TYPE,
		indicadorpedidoexpress        ped_tipo_despa.cod_tipo_desp%TYPE,
		clasesolicitud                ped_clase_solic.cod_clas_soli%TYPE,
		codigotiposolicitud           ped_tipo_solic.cod_tipo_soli%TYPE,
		totalunixpedido               ped_solic_posic.num_unid_aten%TYPE,
		totalitemxpedido              ped_solic_posic.oid_soli_posi%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;
  c_compania          constant varchar2(3) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_PAIS_LAR'),RPAD(psCodigoPais,3,' '));

BEGIN
	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
	/* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidperiodo);
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
              lsLinea :=  c_compania              		    			        ||';'||
                      	  to_char(sysdate,'YYYYMMDD')       			      ||';'||
                    	    psNumLote                         	   	      ||';'||
                          psTipoPeriodo                     			      ||';'||
                          psCodigoPeriodo                   			      ||';'||
                          interfazRecord(x).codigoempresa        		    ||';'||
                          interfazRecord(x).numeropedido                ||';'||
                          interfazRecord(x).codigocliente               ||';'||
                          to_char(interfazRecord(x).fechapedido,'YYYYMMDD')  ||';'||
                          interfazRecord(x).indicadorpedidoachequear    ||';'||
                          interfazRecord(x).totalitemxpedido            ||';'||
                          interfazRecord(x).totalunixpedido             ||';'||
                          interfazRecord(x).indicadorpedidoexpress      ||';'||
						              ' '                                           ||';'||
						              ' '                                           ||';'||
                          interfazRecord(x).clasesolicitud              ||';'||
                          interfazRecord(x).codigotiposolicitud;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR7_CABEC: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR7_CABEC;


/***************************************************************************
Descripcion       : Interfaz que envia la LAR 7 detalles
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR7_DETAL (psCodigoPais     VARCHAR2,
                    									 psCodigoPeriodo  VARCHAR2,
                    									 psTipoPeriodo    VARCHAR2,
                    									 psNumLote        VARCHAR2,
                    									 psCodigoSistema  VARCHAR2,
                    									 psCodigoInterfaz VARCHAR2,
                    									 psNombreArchivo  VARCHAR2)
IS
 CURSOR c_interfaz(lsoidperiodo NUMBER) IS
	SELECT emp.cod_soci codigoempresa,
	       soc2.val_nume_soli numeropedido,
  			 pro.cod_sap codigoitem,
  			 sop.num_unid_aten unidadesporatender,
  			 pro.cod_ind_dent_caja indicadordentrofueracaja,
  			 clas.cod_clas_soli clasesolicitud,
  			 ts.cod_tipo_soli codigotiposolicitud
		FROM ped_solic_posic sop,
  			 ped_solic_cabec soc,
  			 ped_solic_cabec soc2,
  			 ped_clase_solic clas,
  			 seg_socie emp,
  			 ped_tipo_solic_pais tsp,
  			 mae_produ pro,
  			 ped_tipo_solic ts,
  			 int_lar_tipo_solici_pedido_dis tspd
	 WHERE ts.clso_oid_clas_soli = clas.oid_clas_soli
		 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
		 AND soc2.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
		 AND soc2.soci_oid_soci = emp.oid_soci
		 AND sop.soca_oid_soli_cabe = soc.oid_soli_cabe
		 AND soc.soca_oid_soli_cabe = soc2.oid_soli_cabe
		 AND soc2.perd_oid_peri = lsoidperiodo
		 AND soc2.ind_inte_lari_gene = 0
		 AND soc2.fec_fact IS NOT NULL
		 AND soc2.ind_ts_no_conso = 0
		 AND (soc2.ind_pedi_prue = 0 OR soc2.ind_pedi_prue IS NULL)
		 AND sop.espo_oid_esta_posi <> 2
		 AND sop.prod_oid_prod = pro.oid_prod
		 AND soc2.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
		 AND sop.num_unid_aten > 0
		 and nvl(pro.IND_PROD_SERV,0) <> 1
     --and nvl(soc2.modu_oid_modu,1)<>23
	ORDER BY soc2.val_nume_soli;

   TYPE interfazTipo IS RECORD(
		codigoempresa                 seg_socie.cod_soci%TYPE,
		numeropedido                  ped_solic_cabec.val_nume_soli%TYPE,
		codigoitem                    mae_produ.cod_sap%TYPE,
		unidadesporatender            ped_solic_posic.num_unid_aten%TYPE,
		indicadordentrofueracaja      mae_produ.cod_ind_dent_caja%TYPE,
		clasesolicitud                ped_clase_solic.cod_clas_soli%TYPE,
		codigotiposolicitud           ped_tipo_solic.cod_tipo_soli%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;
  c_compania          constant varchar2(3) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_PAIS_LAR'),RPAD(psCodigoPais,3,' '));

BEGIN
	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
	/* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidperiodo);
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
              lsLinea :=  c_compania              		    			      ||';'||
                      	  to_char(sysdate,'YYYYMMDD')       			    ||';'||
                    	    psNumLote                         	   	    ||';'||
                          psTipoPeriodo                     			    ||';'||
                          psCodigoPeriodo                   			    ||';'||
                          interfazRecord(x).codigoempresa        	    ||';'||
                          interfazRecord(x).numeropedido              ||';'||
                          interfazRecord(x).codigoitem                ||';'||
                          interfazRecord(x).unidadesporatender        ||';'||
                          interfazRecord(x).indicadordentrofueracaja  ||';'||
                          interfazRecord(x).clasesolicitud            ||';'||
                          interfazRecord(x).codigotiposolicitud;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR7_DETAL: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR7_DETAL;


/***************************************************************************
Descripcion       : Interfaz que envia la LAR 9
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR9 (psCodigoPais     VARCHAR2,
              									 psCodigoPeriodo  VARCHAR2,
              									 psCodigoSistema  VARCHAR2,
              									 psCodigoInterfaz VARCHAR2,
              									 psNombreArchivo  VARCHAR2)
IS
 CURSOR c_interfaz(lsoidperiodo NUMBER, v_tipopedserv VARCHAR2) IS
	SELECT zon_zona.cod_zona codigozona,
  			 ped_solic_cabec_secue.num_secu_zona_ruta ruta,
  			 zon_terri.cod_terr codigoterritorio,
  			 ped_solic_cabec.val_nume_soli numeropedido,
  			 ped_solic_cabec_secue.num_secu_fact_diar secuenciaterritorio,
  			 decode(v_tipopedserv,1,lar_fn_ind_serv_recl(ped_solic_cabec.oid_soli_cabe,lsoidperiodo),lar_fn_ind_serv_recl2(ped_solic_cabec.oid_soli_cabe,lsoidperiodo)) indicadorpedidoservicio
		FROM ped_solic_cabec,
  			 ped_solic_cabec_secue,
  			 zon_zona,
  			 zon_terri,
  			 int_lar_tipo_solici_pedido_dis
	 WHERE ped_solic_cabec.oid_soli_cabe = ped_solic_cabec_secue.soca_oid_soli_cabe
     --and nvl(ped_solic_cabec.modu_oid_modu,1)<>23
		 AND zon_zona.oid_zona = ped_solic_cabec.zzon_oid_zona
		 AND zon_terri.oid_terr = ped_solic_cabec.terr_oid_terr
		 AND ped_solic_cabec.perd_oid_peri = lsoidperiodo
		 AND ped_solic_cabec.tspa_oid_tipo_soli_pais = int_lar_tipo_solici_pedido_dis.tspa_oid_tipo_soli_pais
		 AND ped_solic_cabec.ind_inte_lari_gene = 0
		 AND ped_solic_cabec.num_unid_aten_tota > 0
		 AND ped_solic_cabec.fec_fact IS NOT NULL
		 AND NOT EXISTS (SELECT m.sbti_oid_subt_clie
				               FROM mav_param_geren_zona m, zon_zona z
				              WHERE (ped_solic_cabec.sbti_oid_subt_clie = m.sbti_oid_subt_clie)
		 AND ped_solic_cabec.clie_oid_clie = z.clie_oid_clie)
	UNION ALL
	SELECT zon_zona.cod_zona codigozona,
  			 ped_solic_cabec_secue.num_secu_zona_ruta ruta, 0 codigoterritorio,
  			 ped_solic_cabec.val_nume_soli numeropedido,
  			 ped_solic_cabec_secue.num_secu_fact_diar secuenciaterritorio,
  			 decode(v_tipopedserv,1,lar_fn_ind_serv_recl(ped_solic_cabec.oid_soli_cabe,lsoidperiodo),lar_fn_ind_serv_recl2(ped_solic_cabec.oid_soli_cabe,lsoidperiodo)) indicadorpedidoservicio
		FROM ped_solic_cabec,
  			 ped_solic_cabec_secue,
  			 zon_zona,
  			 int_lar_tipo_solici_pedido_dis
   WHERE ped_solic_cabec.oid_soli_cabe = ped_solic_cabec_secue.soca_oid_soli_cabe
     --and nvl(ped_solic_cabec.modu_oid_modu,1)<>23
		 AND ped_solic_cabec.clie_oid_clie = zon_zona.clie_oid_clie
		 AND ped_solic_cabec.perd_oid_peri = lsoidperiodo
		 AND ped_solic_cabec.tspa_oid_tipo_soli_pais = int_lar_tipo_solici_pedido_dis.tspa_oid_tipo_soli_pais
		 AND ped_solic_cabec.ind_inte_lari_gene = 0
		 AND ped_solic_cabec.num_unid_aten_tota > 0
		 AND ped_solic_cabec.fec_fact IS NOT NULL
		 AND EXISTS(SELECT m.sbti_oid_subt_clie
				          FROM mav_param_geren_zona m, zon_zona z
				         WHERE (ped_solic_cabec.sbti_oid_subt_clie = m.sbti_oid_subt_clie)
		 AND ped_solic_cabec.clie_oid_clie = z.clie_oid_clie)
	ORDER BY 2, 5;

   TYPE interfazTipo IS RECORD(
		codigozona                     zon_zona.cod_zona%TYPE,
		ruta                           ped_solic_cabec_secue.num_secu_zona_ruta%TYPE,
		codigoterritorio               zon_terri.cod_terr%TYPE,
		numeropedido                   ped_solic_cabec.val_nume_soli%TYPE,
		secuenciaterritorio            ped_solic_cabec_secue.num_secu_fact_diar%TYPE,
		indicadorpedidoservicio        VARCHAR2(2)
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;

   lv_tipopedserv  VARCHAR2(10) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'LAR_TIPO_PEDSERV'),'1');


BEGIN
	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
	/* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidperiodo,lv_tipopedserv);
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
              lsLinea :=  interfazRecord(x).codigozona        		       ||';'||
                          interfazRecord(x).ruta                         ||';'||
                          interfazRecord(x).codigoterritorio             ||';'||
                          interfazRecord(x).numeropedido            	   ||';'||
                          interfazRecord(x).secuenciaterritorio          ||';'||
                          interfazRecord(x).indicadorpedidoservicio;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR9: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR9;

/***************************************************************************
Descripcion       : Interfaz que envía la LAR 10
Fecha Creacion    : 25/03/2010
Autor             : Dennys Oliva Iriarte
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR10 (psCodigoPais     VARCHAR2,
                									 psCodigoPeriodo  VARCHAR2,
                									 psCodigoMarca    VARCHAR2,
                									 psCodigoSistema  VARCHAR2,
                									 psCodigoInterfaz VARCHAR2,
                									 psNombreArchivo  VARCHAR2)
IS
 CURSOR c_interfaz(lsoidperiodo NUMBER) IS
	WITH clientes AS
  		 (SELECT DISTINCT b.clie_oid_clie
  					 FROM ped_solic_cabec b
  					WHERE b.perd_oid_peri = lsoidperiodo
  					  AND b.fec_fact IS NOT NULL
  					  AND b.ind_inte_lari_gene = 0
              --and nvl(b.modu_oid_modu,1)<>23
  					  AND b.num_unid_aten_tota > 0
  					  AND b.tspa_oid_tipo_soli_pais IN (SELECT tspa_oid_tipo_soli_pais
												                        FROM int_lar_tipo_solici_pedido_dis)),
		   datos AS
		   (SELECT a.cod_clie cod,
               mb.num_docu_iden dni,
      				 SUBSTR (mc.cod_unid_geog, 1, 6) cn1,
      				 SUBSTR (mc.cod_unid_geog, 7, 6) cn2,
      				 SUBSTR (mc.cod_unid_geog, 13, 6) cn3,
      				 trim((SELECT val_text_comu
      					  FROM mae_clien_comun
      				   WHERE clie_oid_clie = a.oid_clie
      					   AND ticm_oid_tipo_comu = 1)) || ' / ' ||
      				 trim((SELECT val_text_comu
      					  FROM mae_clien_comun
      				   WHERE clie_oid_clie = a.oid_clie
      					   AND ticm_oid_tipo_comu = 6)) || ' / ' ||
      				 trim((SELECT val_text_comu
      					  FROM mae_clien_comun
      				   WHERE clie_oid_clie = a.oid_clie
      					   AND ticm_oid_tipo_comu = 7)) telf
			    FROM mae_clien a,
               mae_clien_ident mb,
               mae_clien_direc mc,
               clientes c
		     WHERE a.oid_clie = c.clie_oid_clie
    			 AND mb.clie_oid_clie = a.oid_clie
    			 AND mb.val_iden_docu_prin = 1
    			 AND mc.clie_oid_clie = a.oid_clie
    			 AND mc.ind_dire_ppal = 1
    			 AND mc.ind_elim = 0)
   	SELECT datos.cod,
           datos.dni,
           datos.cn1 cn1,
           datos.cn2 cn2,
           datos.cn3 cn3,
		       nivel1.des_geog des1,
           nivel2.des_geog des2,
           nivel3.des_geog des3,
		       REPLACE (datos.telf, CHR (10), '') telf
	    FROM datos,
    		   zon_valor_estru_geopo nivel1,
    		   zon_valor_estru_geopo nivel2,
    		   zon_valor_estru_geopo nivel3
	   WHERE (nivel1.orde_1 = datos.cn1 AND nivel1.orde_2 IS NULL)
	     AND (nivel2.orde_1 = datos.cn1
			 AND nivel2.orde_2 = datos.cn2
			 AND nivel2.orde_3 IS NULL)
	     AND (nivel3.orde_1 = datos.cn1
			 AND nivel3.orde_2 = datos.cn2
			 AND nivel3.orde_3 = datos.cn3
			 AND nivel3.orde_4 IS NULL)
	     AND nivel1.pais_oid_pais = nivel2.pais_oid_pais
	     AND nivel1.pais_oid_pais = nivel3.pais_oid_pais;

   TYPE interfazTipo IS RECORD(
		codigo     mae_clien.cod_clie%TYPE,
		dni        mae_clien_ident.num_docu_iden%TYPE,
		cn1        mae_clien_direc.cod_unid_geog%TYPE,
		cn2        mae_clien_direc.cod_unid_geog%TYPE,
		cn3        mae_clien_direc.cod_unid_geog%TYPE,
		des1       zon_valor_estru_geopo.des_geog%TYPE,
		des2       zon_valor_estru_geopo.des_geog%TYPE,
		des3       zon_valor_estru_geopo.des_geog%TYPE,
		telf       VARCHAR2(400)
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;

BEGIN
	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
	/* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidperiodo);
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
              lsLinea :=  interfazRecord(x).codigo   ||';'||
                          interfazRecord(x).dni      ||';'||
                          interfazRecord(x).cn1      ||';'||
                          interfazRecord(x).cn2      ||';'||
                          interfazRecord(x).cn3      ||';'||
						              interfazRecord(x).des1     ||';'||
                          interfazRecord(x).des2     ||';'||
                          interfazRecord(x).des3     ||';'||
                          interfazRecord(x).telf;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR10: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR10;

/***************************************************************************
Descripcion       : Interfaz que envia la LAR 11
Fecha Creacion    : 15/01/2013
Autor             : Danny Amaro
Parametros:
          psCodigoPais    : Codigo de Pais
          psCodigoPeriodo : Codigo Periodo
      	  psTipoPeriodo   : Tipo Periodo
          psCodigoMarca   : Codigo Marca
          psCodigoCanal   : Codigo Canal
		      psNumLote       : Numero Lote
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR11 (psCodigoPais     VARCHAR2,
                									psCodigoPeriodo  VARCHAR2,
                 									psCodigoMarca    VARCHAR2,
                									psCodigoSistema  VARCHAR2,
                									psCodigoInterfaz VARCHAR2,
                									psNombreArchivo  VARCHAR2)
IS
CURSOR c_interfaz(codigoPeriodo NUMBER) IS
select seg_perio_corpo.cod_peri        as periodo,
       cod_regi                        as codigoRegion,
       cod_zona                        as codigoZona,
       cod_clie                        as codigoConsultora,
       val_ape1                        as apellidoPaterno,
       val_ape2                        as apellidoMaterno,
       val_nom1                        as primerNombre,
       val_nom2                        as segundoNombre,
       substr(comun.val_i18n,1,100)    as transaccion,
       substr(cod_sap,1,18)            as codigoSAP,
       num_unid_por_aten               as unidadesXAtender
  from cra_perio,
       ped_solic_cabec           sol,
       ped_solic_posic           pos,
       mae_produ,
       mae_clien,
       gen_i18n_sicc_pais        pais,
       zon_zona,
       zon_regio,
       ped_tipo_solic_pais,
       gen_i18n_sicc_comun       comun,
       seg_perio_corpo
 where sol.perd_oid_peri = cra_perio.oid_peri
   and cra_perio.PERI_OID_PERI = seg_perio_corpo.oid_peri
   and seg_perio_corpo.cod_peri >= codigoPeriodo
   and sol.fec_fact is null
   and comun.val_i18n like 'REC A%'
   and sol.oid_soli_cabe = pos.soca_oid_soli_cabe
   and prod_oid_prod = oid_prod
   and sol.CLIE_OID_CLIE = oid_clie
   and pais.attr_enti = 'MAE_PRODU'
   and pais.val_oid = prod_oid_prod
   and sol.zzon_oid_zona = oid_zona
   and zorg_oid_regi = oid_regi
   and sol.tspa_oid_tipo_soli_pais = oid_tipo_soli_pais
   and comun.attr_enti = 'PED_TIPO_SOLIC'
   and comun.val_oid = tsol_oid_tipo_soli
 order by cod_regi, cod_zona, cod_clie;

    TYPE interfazTipo IS RECORD(
		periodo                        seg_perio_corpo.cod_peri%TYPE,
		codigoRegion                   zon_regio.cod_regi%TYPE,
		codigoZona                     zon_zona.cod_zona%TYPE,
		codigoConsultora               mae_clien.cod_clie%TYPE,
		apellidoPaterno                mae_clien.val_ape1%TYPE,
		apellidoMaterno                mae_clien.val_ape2%TYPE,
		primerNombre                   mae_clien.val_nom1%TYPE,
		segundoNombre                  mae_clien.val_nom2%TYPE,
		transaccion                    gen_i18n_sicc_comun.val_i18n%TYPE,
		codigoSAP                      mae_produ.cod_sap%TYPE,
 		unidadesXAtender               ped_solic_posic.num_unid_por_aten%TYPE
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;

BEGIN

	/* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(psCodigoPeriodo);
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
              lsLinea :=  interfazRecord(x).periodo            ||';'||
                          interfazRecord(x).codigoRegion       ||';'||
                          interfazRecord(x).codigoZona         ||';'||
                          interfazRecord(x).codigoConsultora   ||';'||
                          interfazRecord(x).apellidoPaterno    ||';'||
						              interfazRecord(x).apellidoMaterno    ||';'||
                          interfazRecord(x).primerNombre       ||';'||
                          interfazRecord(x).segundoNombre      ||';'||
                          interfazRecord(x).transaccion        ||';'||
                          interfazRecord(x).codigoSAP          ||';'||
                          interfazRecord(x).unidadesXAtender;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR11: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR11;

/***************************************************************************
Descripcion       : Interfaz que envia la LAR 8
Fecha Creacion    : 28/02/2013
Autor             : Danny Amaro
Parametros:
          psCodigoPeriodo : Codigo Periodo
          psCodigoMarca   : Codigo Marca
          psCodigoSistema : Codigo Sistema
          psCodigoInterfaz: Codigo Interfaz
          psNombreArchivo : Nombre Archivo
		      psNumeroLote    : Numero Lote
          psFecha         : Fecha Proceso
          psDesde         : Numero Interno Desde
          psHasta         : Numero Interno Hasta
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_LAR8 (psCodigoPais     VARCHAR2,
                				 psCodigoPeriodo  VARCHAR2,
                				 psCodigoMarca    VARCHAR2,
                				 psCodigoSistema  VARCHAR2,
                				 psCodigoInterfaz VARCHAR2,
                				 psNombreArchivo  VARCHAR2,
                         psNumeroLote     VARCHAR2,
                         psFecha		  VARCHAR2,
                         psDesde		  VARCHAR2,
                         psHasta		  VARCHAR2)
IS
 CURSOR c_interfaz (lsoidperiodo NUMBER, ldfecha date) IS
	SELECT '001' COMPANIA,
		   TO_CHAR(SOC.FEC_FACT, 'YYYYMMDD') FECHA_PROC,
		   SOC.VAL_NUME_SOLI NUMEROPEDIDO,
		   '1' TIPOFORMULARIO, --reg.cod_regi,
		   TRIM(SUBSTR(DOCC.VAL_SERI_DOCU_LEGA, 1, 5)) ||
		   TRIM(TO_CHAR(nvl(DOCC.NUM_DOCU_LEGA, DOCC.NUM_DOCU_CONT_INTE),
						'0000000000')) NUMEROFORMULARIO
	  FROM PED_SOLIC_CABEC       SOC,
		   FAC_TIPO_DOCUM        DOC,
		   FAC_DOCUM_CONTA_CABEC DOCC,
		   ZON_ZONA              ZON,
		   ZON_REGIO             REG
	 WHERE DOCC.SOCA_OID_SOLI_CABE = SOC.OID_SOLI_CABE
	   AND DOC.OID_TIPO_DOCU = DOCC.TIDO_OID_TIPO_DOCU
	   and soc.zzon_oid_zona = zon.oid_zona
     and( NOT EXISTS(SELECT COD_ZONA FROM LAR_PARAM_GENER_LAR8 WHERE TIPO = 'ZONA')
           OR zon.cod_zona in (SELECT COD_ZONA FROM LAR_PARAM_GENER_LAR8 WHERE TIPO = 'ZONA' AND COD_ZONA=zon.cod_zona)
      )
     and( NOT EXISTS(SELECT COD_REGI FROM LAR_PARAM_GENER_LAR8 WHERE TIPO = 'REGION')
           OR reg.cod_regi in (SELECT COD_REGI FROM LAR_PARAM_GENER_LAR8 WHERE TIPO = 'REGION' AND COD_REGI=reg.cod_regi)
      )
	   and zon.zorg_oid_regi = reg.oid_regi
	   and soc.fec_fact = ldfecha
	   and soc.perd_oid_peri = lsoidperiodo
     AND SOC.FEC_FACT IS NOT NULL
      and( (NOT EXISTS(SELECT TIPO_DOC FROM LAR_PARAM_GENER_LAR8 WHERE TIPO = 'TIPODOC') and doc.oid_tipo_docu in (1,29,30))
           OR DOC.COD_TIPO_DOCU in (SELECT TIPO_DOC FROM LAR_PARAM_GENER_LAR8 WHERE TIPO = 'TIPODOC' AND TIPO_DOC=DOC.COD_TIPO_DOCU)
      )
	   AND DOCC.SBAC_OID_SBAC = 888 --oidSubAcceso
	   AND DOCC.NUM_DOCU_CONT_INTE BETWEEN
	   TO_NUMBER(NVL(psDesde, '0')) AND TO_NUMBER(NVL(psHasta, '9999999999'));

   TYPE interfazTipo IS RECORD(
		compania   			  VARCHAR2(3),
		fechaProceso    	VARCHAR2(8),
		numeroPedido    	PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE,
		tipoFormulario  	VARCHAR2(1),
		numeroFormulario  VARCHAR2(15)
   );

   TYPE interfazTab  IS TABLE OF interfazTipo ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsoidperiodo        seg_perio_corpo.OID_PERI%type;
  ldfecproc            date;

BEGIN

  select fec_proc into ldfecproc from bas_ctrl_fact a where a.sta_camp=0 and a.ind_camp_act=1;


	lsoidperiodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
	/* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz(lsoidperiodo, nvl(to_date(psFecha, 'DD/MM/YYYY'),ldfecproc));
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
              lsLinea :=  interfazRecord(x).compania   		  ||';'||
                          interfazRecord(x).fechaProceso    ||';'||
						              substr(psNumeroLote,9) ||';'||
                          interfazRecord(x).numeroPedido    ||';'||
                          interfazRecord(x).tipoFormulario  ||';'||
                          interfazRecord(x).numeroFormulario;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_ENVIO_LAR8: '||ls_sqlerrm);
END INT_PR_LAR_ENVIO_LAR8;

/***************************************************************************
Descripcion       : Proceso de registro de Historico de Carga de Archivo de
                    Lotes YOBEL
Fecha Creacion    : 06/03/2013
Autor             : Danny Amaro
Parametros:
          psCodigoPais    : Codigo Pais
***************************************************************************/
PROCEDURE INT_PR_LAR_REGIS_HISTO_YOBEL (psCodigoPais     VARCHAR2)
IS
  lnIdPais    SEG_PAIS.OID_PAIS%TYPE;
BEGIN

  /* obteniendo id Pais */
  lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);

  INSERT INTO YOB_CARGA_LOTES_TRAZA_HISTO(COD_PERI, NUM_PEDI, COD_SAP,
         UNI_ATEN, NUM_LOTE, FEC_CARG, NUM_LOTE_ENVIO, IND_ENVI_FE)
  SELECT YBL.COD_PERI,
         YBL.NUM_PEDI,
         YBL.COD_SAP,
         YBL.UNI_ATEN,
         YBL.NUM_LOTE,
         YBL.FEC_CARG,
         YBL.NUM_LOTE_ENVIO,
         YBL.IND_ENVI_FE
  FROM YOB_CARGA_LOTES_TRAZA YBL;

  EXECUTE IMMEDIATE 'TRUNCATE TABLE YOB_CARGA_LOTES_TRAZA';

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_LAR_REGIS_HISTO_YOBEL: '||ls_sqlerrm);

END INT_PR_LAR_REGIS_HISTO_YOBEL;


FUNCTION LAR_FN_IND_SERV_RECL2 (
   p_oid_soli_cabe   IN   NUMBER,
   p_oid_peri        IN   NUMBER
)
   RETURN VARCHAR2
IS
   cont   VARCHAR2(1);

BEGIN
SELECT case
when exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=p_oid_soli_cabe and ind_oc=1 ) then '0'
when exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=p_oid_soli_cabe and ind_oc=0 and VAL_GLOS_OBSE is not null ) then '1'
when exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=p_oid_soli_cabe and modu_oid_modu=15) then '1'
when exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=p_oid_soli_cabe and modu_oid_modu=13) then '2'
when exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=p_oid_soli_cabe and modu_oid_modu=9) then '3'
when exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=p_oid_soli_cabe and modu_oid_modu in (23,27)) then '5'
else '0'
end into cont
 from dual;



      RETURN cont;
END LAR_FN_IND_SERV_RECL2;

/**************************************************************************
Descripcion         : NTERFAZ PARA ENVIAR DATOS A TIS2
Fecha Creación      : 21/10/2013
Autor               : Ivan Tocto
***************************************************************************/
PROCEDURE INT_PR_LAR_ENVIO_ARCHI_TIS2
(
  pscodigopais       VARCHAR2,
  pscodigosistema    VARCHAR2,
  pscodigointerfaz   VARCHAR2,
  psnombrearchivo    VARCHAR2,
  psnumerolote    VARCHAR2
) IS
  CURSOR c_interfaz IS
SELECT val_seri_docu_lega,
       num_docu_cont_inte,
       nom_archi_origi,
       ind_impr_docu
  FROM (SELECT c.val_seri_docu_lega,
        c.num_docu_cont_inte,
        a.nom_archi_origi,
        --   D.IND_IMPR_DOCU,
        CASE
                 WHEN c.tido_oid_tipo_docu = 30 AND
                      (SELECT COUNT(1)
                  FROM fac_desha_zonas_envio_bolet fdzeb
                 WHERE fdzeb.oid_zona = b.zzon_oid_zona) > 0 THEN
           '0'
          ELSE
           d.ind_impr_docu
               END ind_impr_docu
   FROM yob_carga_lotes_traza a,
        ped_solic_cabec       b,
        fac_docum_conta_cabec c,
        mae_clien_datos_adici d
  WHERE a.num_pedi = b.val_nume_soli
    AND b.oid_soli_cabe = c.soca_oid_soli_cabe
    AND b.clie_oid_clie = d.clie_oid_clie
    AND c.tido_oid_tipo_docu IN (29, 30)
           AND a.num_lote_envio = psnumerolote
           AND EXISTS
     (SELECT NULL
              FROM fac_docum_conta_linea det,
                   ped_solic_posic       psp,
                   pre_ofert_detal       pod
             WHERE det.sopo_oid_soli_posi = psp.oid_soli_posi
               AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
               AND det.num_unid_aten > 0
               AND det.dcca_oid_cabe = c.oid_cabe
               AND NOT EXISTS
             (SELECT NULL
                      FROM fac_tipo_ofert_exclu toe
                     WHERE toe.tofe_oid_tipo_ofer = pod.tofe_oid_tipo_ofer))

           )
 GROUP BY val_seri_docu_lega,
          num_docu_cont_inte,
          ind_impr_docu,
          nom_archi_origi
 ORDER BY val_seri_docu_lega,
          num_docu_cont_inte;

  TYPE interfazrec IS RECORD(
    serie                         FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE,
    numerodocumento     FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE,
    archivo                     YOB_CARGA_LOTES_TRAZA.NOM_ARCHI_ORIGI%TYPE,
    impresion                 MAE_CLIEN_DATOS_ADICI.IND_IMPR_DOCU%TYPE
    );
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;
  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo bas_inter.dir_temp%TYPE;
  w_filas    NUMBER := 1000;
  v_handle   utl_file.file_type;

  lslinea VARCHAR2(1000) := '';

  lsnombrearchivo VARCHAR2(50);
  lbabrirutlfile  BOOLEAN;
BEGIN

  /* Procedimiento inicial para generar interfaz */
  lbabrirutlfile := TRUE;

  /* Generar Archivo de Texto (Detalle) */
  OPEN c_interfaz;
  LOOP
    FETCH c_interfaz BULK COLLECT
      INTO interfazrecord LIMIT w_filas;
    /* Procedimiento inicial para generar interfaz */
    IF lbabrirutlfile THEN
      gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                             pscodigosistema,
                                             pscodigointerfaz,
                                             psnombrearchivo,
                                             lsdirtempo,
                                             lsnombrearchivo,
                                             v_handle);
      lbabrirutlfile := FALSE;
    END IF;

    IF interfazrecord.count > 0 THEN
      FOR x IN interfazrecord.first .. interfazrecord.last
      LOOP
        lslinea := interfazrecord(x).serie || ';';
        lslinea := lslinea || interfazrecord(x).numerodocumento || ';';
        lslinea := lslinea || interfazrecord(x).archivo || ';';
        lslinea := lslinea || interfazrecord(x).impresion;
        utl_file.put_line(v_handle, lslinea);

      END LOOP;
    END IF;

    EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

  IF NOT lbabrirutlfile THEN
    utl_file.fclose(v_handle);
    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
  END IF;
  /* Generando Archivo de Texto (Detalle) */

  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_LAR_ENVIO_ARCHI_TIS2: ' ||
                            ls_sqlerrm);
END INT_PR_LAR_ENVIO_ARCHI_TIS2;

/***************************************************************************
Descripcion     : Recepcion de variables de Carga de Informacion Tracking
Fecha Creacion  : 19/11/2015
Autor           : Carlos Bazalar
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Archivo
***************************************************************************/
  PROCEDURE INT_PR_LAR_RECEP_CARGA_TRACK
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS

    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
                  bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;

    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;
    interfazrecord interfazcabtab;

    TYPE t_consultora           IS TABLE OF VARCHAR2(50);
    TYPE t_campanna             IS TABLE OF VARCHAR2(50);
    TYPE t_nroPedido            IS TABLE OF NUMBER(14);
    TYPE t_nroCajas             IS TABLE OF VARCHAR2(50);
    TYPE t_achequeo             IS TABLE OF VARCHAR2(50);
    TYPE t_fechaFact            IS TABLE OF VARCHAR2(50);
    TYPE t_tiempo               IS TABLE OF VARCHAR2(50);
    TYPE t_errorEmbalaje        IS TABLE OF VARCHAR2(50);
    TYPE t_inducido             IS TABLE OF VARCHAR2(50);
    TYPE t_indHora              IS TABLE OF VARCHAR2(50);
    TYPE t_armado               IS TABLE OF VARCHAR2(50);
    TYPE t_chequeado            IS TABLE OF VARCHAR2(50);
    TYPE t_chequedoHoraIni      IS TABLE OF VARCHAR2(50);
    TYPE t_chequedoHoraFin      IS TABLE OF VARCHAR2(50);
    TYPE t_enDespacho           IS TABLE OF VARCHAR2(50);
    TYPE t_enTransporte         IS TABLE OF VARCHAR2(50);
    TYPE t_enTransporteHora     IS TABLE OF VARCHAR2(50);
    TYPE t_tipoPedido           IS TABLE OF VARCHAR2(50);
    TYPE t_costoPedido          IS TABLE OF VARCHAR2(50);
    TYPE t_ordenImp             IS TABLE OF VARCHAR2(50);
    TYPE t_entregado            IS TABLE OF VARCHAR2(50);
    TYPE t_entregadoHora        IS TABLE OF VARCHAR2(50);
    TYPE t_latitud              IS TABLE OF VARCHAR2(50);
    TYPE t_longitud             IS TABLE OF VARCHAR2(50);
    TYPE t_codigoNovedad        IS TABLE OF VARCHAR2(50);
    TYPE t_fechaHoraNovedad     IS TABLE OF VARCHAR2(50);
    TYPE t_oidSolicCabe         IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
    TYPE t_encontro             IS TABLE OF BOOLEAN;

    v_consultora       t_consultora              := t_consultora();           
    v_campanna         t_campanna                := t_campanna();             
    v_nroPedido        t_nroPedido               := t_nroPedido();            
    v_nroCajas         t_nroCajas                := t_nroCajas();             
    v_achequeo         t_achequeo                := t_achequeo();             
    v_fechaFact        t_fechaFact               := t_fechaFact();            
    v_tiempo           t_tiempo                  := t_tiempo();               
    v_errorEmbalaje    t_errorEmbalaje           := t_errorEmbalaje();        
    v_inducido         t_inducido                := t_inducido();             
    v_indHora          t_indHora                 := t_indHora();              
    v_armado           t_armado                  := t_armado();               
    v_chequeado        t_chequeado               := t_chequeado();            
    v_chequedoHoraIni  t_chequedoHoraIni         := t_chequedoHoraIni();      
    v_chequedoHoraFin  t_chequedoHoraFin         := t_chequedoHoraFin();      
    v_enDespacho       t_enDespacho              := t_enDespacho();           
    v_enTransporte     t_enTransporte            := t_enTransporte();         
    v_enTransporteHora t_enTransporteHora        := t_enTransporteHora();     
    v_tipoPedido       t_tipoPedido              := t_tipoPedido();           
    v_costoPedido      t_costoPedido             := t_costoPedido();          
    v_ordenImp         t_ordenImp                := t_ordenImp();             
    v_entregado        t_entregado               := t_entregado();            
    v_entregadoHora    t_entregadoHora           := t_entregadoHora();        
    v_latitud          t_latitud                 := t_latitud();              
    v_longitud         t_longitud                := t_longitud();             
    v_codigoNovedad    t_codigoNovedad           := t_codigoNovedad();        
    v_fechaHoraNovedad t_fechaHoraNovedad        := t_fechaHoraNovedad();     
    v_oidSolicCabe     t_oidSolicCabe            := t_oidSolicCabe(); 
    v_encontro         t_encontro                := t_encontro();
    
        
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i  BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';

    lnCantidad NUMBER;
    sqlQuery    VARCHAR2(100);
    lsArchivo   VARCHAR2(500);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    BEGIN
      gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                   pscodigosistema,
                                                   pscodigointerfaz,
                                                   psnombrearchivo,
                                                   'TXT',
                                                   lsdirtempo,
                                                   lsnombrearchivo,
                                                   v_handle);
    EXCEPTION
      WHEN OTHERS THEN
           gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                   pscodigosistema,
                                                   pscodigointerfaz,
                                                   psnombrearchivo,
                                                   'txt',
                                                   lsdirtempo,
                                                   lsnombrearchivo,
                                                   v_handle);
    END ;
        
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN

          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;

          IF lslinea IS NULL THEN
            EXIT;
          END IF;

          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT  INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last  LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                    v_consultora.extend;
                    v_consultora(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 2) THEN
                    v_campanna.extend;
                    v_campanna(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 3) THEN
                  v_nroPedido.extend;
                  v_nroPedido(i) := TO_NUMBER(TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace)), '99999999999999');
                  v_encontro.extend;
                  v_oidSolicCabe.extend;
                                                 
                  BEGIN
                    SELECT oid_soli_cabe
                     INTO v_oidSolicCabe(i)
                     FROM ped_solic_cabec psc
                    WHERE PSC.VAL_NUME_SOLI = v_nroPedido(i);
                    v_encontro(i) := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                         v_encontro(i) := FALSE;
                         v_oidSolicCabe(i) := 0;
                  END;
                ELSIF (posicion = 4) THEN
                  v_nroCajas.extend;
                  v_nroCajas(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_achequeo.extend;
                  v_achequeo(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 6) THEN
                  v_fechaFact.extend;
                  v_fechaFact(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 7) THEN
                  v_tiempo.extend;
                  v_tiempo(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 8) THEN
                  v_errorEmbalaje.extend;
                  v_errorEmbalaje(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 9) THEN
                  v_inducido.extend;
                  v_inducido(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 10) THEN
                  v_indHora.extend;
                  v_indHora(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 11) THEN
                  v_armado.extend;
                  v_armado(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 12) THEN
                  v_chequeado.extend;
                  v_chequeado(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 13) THEN
                  v_chequedoHoraIni.extend;
                  v_chequedoHoraIni(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));

                ELSIF (posicion = 14) THEN
                  v_chequedoHoraFin.extend;
                  v_chequedoHoraFin(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 15) THEN
                  v_enDespacho.extend;
                  v_enDespacho(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 16) THEN
                  v_enTransporte.extend;
                  v_enTransporte(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));

                ELSIF (posicion = 17) THEN
                  v_enTransporteHora.extend;
                  v_enTransporteHora(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 18) THEN
                  v_tipoPedido.extend;
                  v_tipoPedido(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 19) THEN
                  v_costoPedido.extend;
                  v_costoPedido(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 20) THEN
                  v_ordenImp.extend;
                  v_ordenImp(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 21) THEN
                  v_entregado.extend;
                  v_entregado(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 22) THEN
                  v_entregadoHora.extend;
                  v_entregadoHora(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 23) THEN
                  v_latitud.extend;
                  v_latitud(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 24) THEN
                  v_longitud.extend;
                  v_longitud(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 25) THEN
                  v_codigoNovedad.extend;
                  v_codigoNovedad(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 26) THEN
                  v_fechaHoraNovedad.extend;
                  v_fechaHoraNovedad(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
               
                END IF;

                inicio := inicio + longitud;

              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;

        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;

    utl_file.fclose(v_handle);

    -- Bulk bind of data in memory table...
    lnCantidad := 0;
    lsArchivo := psnombrearchivo || '.txt';
    FOR x IN v_consultora.first .. v_consultora.last  LOOP 
        IF v_encontro(x) THEN
            lnCantidad := lnCantidad + 1;
            update ped_segui_pedid
             set tip_cheq_inic= SUBSTR(v_achequeo(x),1,2),
                 cod_nove = SUBSTR(v_codigoNovedad(x),1,2), 
                 fec_nove     = TO_DATE(replace(v_fechaHoraNovedad(x),'T',''), 'YYYY-MM-DD HH24:MI:SS'),
                 fec_cheq_inic= decode(v_chequeado(x),'S', TO_DATE(replace(v_chequedoHoraIni(x),'T',''), 'YYYY-MM-DD HH24:MI:SS') , null),
                 fec_indu     = decode(v_inducido(x),'S',  TO_DATE(replace(v_indHora(x),'T',''), 'YYYY-MM-DD HH24:MI:SS'), null),
                 fec_tran     = decode(v_enTransporte(x),'S', TO_DATE(replace(v_enTransporteHora(x),'T',''), 'YYYY-MM-DD HH24:MI:SS') , null),
                 fec_entr     = decode(v_entregado(x),'S', TO_DATE(replace(v_entregadoHora(x),'T',''), 'YYYY-MM-DD HH24:MI:SS') , null),
                 val_lati     = decode(v_entregado(x),'S', v_latitud(x) , null),
                 val_long     = decode(v_entregado(x),'S', v_longitud(x) , null)
             where soca_oid_soli_cabe = v_oidSolicCabe(x);
        END IF;
    END LOOP;
    
    UPDATE INT_ARCHI_CNTRL_WTRK 
		SET NUM_REGI_OK = lnCantidad
		WHERE nom_arch = lsArchivo;
    
        
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_LAR_RECEP_CARGA_TRACK: ' || ls_sqlerrm);

  END INT_PR_LAR_RECEP_CARGA_TRACK;

END INT_PKG_LARIS;
/
