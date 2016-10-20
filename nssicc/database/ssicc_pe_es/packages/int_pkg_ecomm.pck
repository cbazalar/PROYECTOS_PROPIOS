CREATE OR REPLACE PACKAGE "INT_PKG_ECOMM" IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Genera Interfase de Enviar Inscritas de Eccomerce
Fecha Creacion    : 14/11/2006
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE INT_PR_ECO_ENVI_INSC
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psCodigoTipoVinculo        VARCHAR2,
   psCodigoClasificacion      VARCHAR2,
   psCodigoTipoClasificacion  VARCHAR2,
   psCodigoTipoCliente        VARCHAR2,
   psCodigoSubTipoCliente     VARCHAR2);
/**************************************************************************
Descripcion        : Genera el archivo para la Interfaz Enviar Venta BAse Consultora
Fecha Creacion     : 28/11/2006
Autor              : Marco Silva
***************************************************************************/
PROCEDURE INT_PR_ECO_VENTA_BASE_CONSU
   (psCodigoPais    VARCHAR2,
   psCodigoSistema  VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo  VARCHAR2);
/***************************************************************************
Descripcion       : Genera Interfase de Enviar VEntas y Puntajes por Campania
Fecha Creacion    : 14/08/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE INT_PR_ECO_VENTA_PUNT_CAMPA
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psCodigoTipoVinculo        VARCHAR2,
   psCodigoClasificacion      VARCHAR2,
   psCodigoTipoClasificacion  VARCHAR2,
   psCodigoTipoCliente        VARCHAR2,
   psCodigoSubTipoCliente     VARCHAR2,
   psOidParametroGeneral      NUMBER,
   pdFechaActual                VARCHAR );
/***************************************************************************
Descripcion       : Obtiene el Origen de Descripcion. Con el MAe Clien Primer
                    contacto
Fecha Creacion    : 10/08/2006
Autor             : Marco Agurto
***************************************************************************/
FUNCTION INT_FN_ECO_OBTIE_ORIGE_INSCR(psOidClie NUMBER)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Matriz de Productos con Oferta
                    de Cumpleaos
Fecha Creacion    : 16/06/2009
Autor             : Jos A. Cairampoma
***************************************************************************/
PROCEDURE int_pr_ecm_matri_ofert_cumpl
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  pscodigoperiodo  VARCHAR2
);

/****************************************************************************
Descripcion       : Genera Interfaz de Envio de Clientes (ECM-10)
Fecha Creacion    : 17/05/2013
Parametros        :
    psCodigoPais     : Codigo Pais
    psCodigoSistema  : Codigo Empresa
    psCodigoInterfaz : Codigo Interfaz
    psNombreArchivo  : Nombre Arcchivo
Autor             : Aurelio Oviedo
*****************************************************************************/
PROCEDURE INT_PR_ECM_ENVIO_CLIEN
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2
);
END INT_PKG_ECOMM;
/

CREATE OR REPLACE PACKAGE BODY "INT_PKG_ECOMM" IS
/***************************************************************************
Descripcion       : Genera Interfase de Enviar Inscritas de Eccomerce
Fecha Creacion    : 14/11/2006
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE INT_PR_ECO_ENVI_INSC
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psCodigoTipoVinculo        VARCHAR2,
   psCodigoClasificacion      VARCHAR2,
   psCodigoTipoClasificacion  VARCHAR2,
   psCodigoTipoCliente        VARCHAR2,
   psCodigoSubTipoCliente     VARCHAR2)

IS
   CURSOR c_interfaz IS
   SELECT
   COD_PAIS AS CODIGO_PAIS,
   ' ' AS CODIGO_EMPRESA,
   clientes.COD_CLIE AS CODIGO_CLIENTE,
   inscritas.VAL_APE1 AS APELLIDO_PATERNO,
   inscritas.VAL_APE2 AS APELLIDO_MATERNO,
   inscritas.VAL_NOM1 || ' ' || VAL_NOM2 AS NOMBRE,
   TO_CHAR(MAE_CLIEN_DATOS_ADICI.FEC_NACI, 'YYYYMMDD') AS FECHA_NACIMIENTO,
   cliEmail.EMAIL,
   cliTelefono.TELEFONO,
   cliCelular.CELULAR,
  ( CASE
		WHEN MAE_CLIEN_DATOS_ADICI.IND_CORR = 1 THEN 1
		WHEN MAE_CLIEN_DATOS_ADICI.IND_CORR = 0 OR MAE_CLIEN_DATOS_ADICI.IND_CORR IS NULL THEN 0
	END ) AS IND_COMU_PPAL,
   ( SELECT SEG_PERIO_CORPO.COD_PERI
    FROM   CRA_PERIO,
          SEG_PERIO_CORPO
    WHERE  CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
			and CRA_PERIO.PAIS_OID_PAIS = perActual.Pais
			and CRA_PERIO.MARC_OID_MARC = perActual.Marca
			and CRA_PERIO.CANA_OID_CANA = perActual.Canal
			--and SEG_PERIO_CORPO.VAL_ANIO = perActual.Anio
			and CRA_PERIO.FEC_INIC <= inscritas.FEC_INGR
			and CRA_PERIO.FEC_FINA >= inscritas.FEC_INGR
			and rownum = 1
   ) CODIGO_PERIODO,
   ' ' AS FECHA_ENTREGA_CARNET,
   CASE
   WHEN  (inscritas.FEC_ULTI_ACTU >= MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU)
    AND (inscritas.FEC_ULTI_ACTU >= NVL(cliTelefono.FEC_ULTI_ACTU,inscritas.FEC_ULTI_ACTU))
    AND (inscritas.FEC_ULTI_ACTU >= NVL(cliEmail.FEC_ULTI_ACTU,inscritas.FEC_ULTI_ACTU))
    AND (inscritas.FEC_ULTI_ACTU >= NVL(cliCelular.FEC_ULTI_ACTU,inscritas.FEC_ULTI_ACTU))
        THEN TO_CHAR(inscritas.FEC_ULTI_ACTU, 'YYYYMMDD')
   WHEN  (MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU >= inscritas.FEC_ULTI_ACTU)
    AND (MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU >= NVL(cliTelefono.FEC_ULTI_ACTU,MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU))
    AND (MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU >= NVL(cliEmail.FEC_ULTI_ACTU,MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU))
    AND (MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU >= NVL(cliCelular.FEC_ULTI_ACTU,MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU))
        THEN TO_CHAR(MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU, 'YYYYMMDD')
   WHEN  (NVL(cliTelefono.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU) >= inscritas.FEC_ULTI_ACTU)
    AND (NVL(cliTelefono.FEC_ULTI_ACTU, MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU) >= MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU)
    AND (NVL(cliTelefono.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU) >= NVL(cliEmail.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU))
    AND (NVL(cliTelefono.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU) >= NVL(cliCelular.FEC_ULTI_ACTU,inscritas.FEC_ULTI_ACTU))
        THEN TO_CHAR(NVL(cliTelefono.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU), 'YYYYMMDD')
   WHEN  (NVL(cliEmail.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU) >= inscritas.FEC_ULTI_ACTU)
    AND (NVL(cliEmail.FEC_ULTI_ACTU, MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU) >= MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU)
    AND (NVL(cliEmail.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU) >= NVL(cliTelefono.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU))
    AND (NVL(cliEmail.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU) >= NVL(cliCelular.FEC_ULTI_ACTU,inscritas.FEC_ULTI_ACTU))
        THEN TO_CHAR(NVL(cliEmail.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU), 'YYYYMMDD')
   WHEN  (NVL(cliCelular.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU) >= inscritas.FEC_ULTI_ACTU)
    AND (NVL(cliCelular.FEC_ULTI_ACTU, MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU) >= MAE_CLIEN_DATOS_ADICI.FEC_ULTI_ACTU)
    AND (NVL(cliCelular.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU) >= NVL(cliTelefono.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU))
    AND (NVL(cliCelular.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU) >= NVL(cliEmail.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU))
        THEN TO_CHAR(NVL(cliCelular.FEC_ULTI_ACTU, inscritas.FEC_ULTI_ACTU), 'YYYYMMDD')
     END AS  FECHA_ULTIMA_MODIFICACION,
   TO_CHAR(inscritas.FEC_INGR, 'YYYYMMDD') AS FECHA_INGRESO,
   ' ' AS ULTIMO_USUARIO_ACTUALIZO,
   INT_FN_ECO_OBTIE_ORIGE_INSCR(inscritas.oid_clie) AS ORIGEN_INSCRIPCION,
   ' ' AS CLAVE_ACCESO_DUPLAWEB,
   -- Se agrego el indicador de confirmacion de comunicacion
   cliEmail.indicadorConfirmacion
  FROM
   MAE_CLIEN inscritas,
   MAE_CLIEN_DATOS_ADICI,
   MAE_CLIEN_VINCU,
   MAE_TIPO_VINCU,
   ( SELECT MAE_CLIEN.OID_CLIE,
          MAE_CLIEN.COD_CLIE
    FROM   MAE_CLIEN,
           MAE_CLIEN_TIPO_SUBTI,
           MAE_CLIEN_CLASI,
           MAE_TIPO_CLIEN,
           MAE_SUBTI_CLIEN,
           MAE_TIPO_CLASI_CLIEN,
           MAE_CLASI
    WHERE  MAE_CLIEN.OID_CLIE = MAE_CLIEN_TIPO_SUBTI.CLIE_OID_CLIE
     AND MAE_CLIEN_TIPO_SUBTI.OID_CLIE_TIPO_SUBT = MAE_CLIEN_CLASI.CTSU_OID_CLIE_TIPO_SUBT
     AND MAE_CLIEN_TIPO_SUBTI.TICL_OID_TIPO_CLIE = MAE_TIPO_CLIEN.OID_TIPO_CLIE
     AND MAE_CLIEN_TIPO_SUBTI.SBTI_OID_SUBT_CLIE = MAE_SUBTI_CLIEN.OID_SUBT_CLIE
     AND MAE_CLIEN_CLASI.TCCL_OID_TIPO_CLASI = MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS
     AND MAE_CLIEN_CLASI.CLAS_OID_CLAS = MAE_CLASI.OID_CLAS
     AND MAE_TIPO_CLIEN.OID_TIPO_CLIE  = MAE_SUBTI_CLIEN.TICL_OID_TIPO_CLIE
     AND MAE_SUBTI_CLIEN.OID_SUBT_CLIE = MAE_TIPO_CLASI_CLIEN.SBTI_OID_SUBT_CLIE
     AND MAE_TIPO_CLASI_CLIEN.OID_TIPO_CLAS = MAE_CLASI.TCCL_OID_TIPO_CLAS
     AND MAE_TIPO_CLIEN.COD_TIPO_CLIE  =  psCodigoTipoCliente
     AND COD_TIPO_CLIE || '-' || COD_SUBT_CLIE IN (
                                           SELECT INT_TMP_param_inter.Val_Para_Varc
                                           FROM INT_TMP_param_inter
                                           WHERE INT_TMP_param_inter.Cod_Para = psCodigoSubTipoCliente
                                                 AND INT_TMP_param_inter.Cod_Inte = psCodigoInterfaz
                                           )
     AND cod_tipo_clas||cod_tipo_clie||cod_subt_clie IN (
                                           SELECT INT_TMP_param_inter.Val_Para_Varc
                                           FROM INT_TMP_param_inter
                                           WHERE INT_TMP_param_inter.Cod_Para = psCodigoTipoClasificacion
                                                 AND INT_TMP_param_inter.Cod_Inte = psCodigoInterfaz
                                           )

     AND cod_tipo_clas||cod_tipo_clie||cod_subt_clie||cod_clas IN (
                                  SELECT INT_TMP_param_inter.Val_Para_Varc
                                  FROM INT_TMP_param_inter
                                  WHERE INT_TMP_param_inter.Cod_Para = psCodigoClasificacion
                                     AND INT_TMP_param_inter.Cod_Inte = psCodigoInterfaz
                               )
   ) clientes,
   ( SELECT MAE_CLIEN_COMUN.CLIE_OID_CLIE,
          MAE_CLIEN_COMUN.VAL_TEXT_COMU telefono,
          MAE_CLIEN_COMUN.FEC_ULTI_ACTU
    FROM   MAE_CLIEN_COMUN,
               MAE_TIPO_COMUN
    WHERE  MAE_CLIEN_COMUN.TICM_OID_TIPO_COMU = MAE_TIPO_COMUN.OID_TIPO_COMU
     AND  MAE_TIPO_COMUN.COD_TIPO_COMU in ('TF')
    ORDER BY 1
   ) cliTelefono,
   (SELECT MAE_CLIEN_COMUN.CLIE_OID_CLIE,
           REPLACE(TRANSLATE(MAE_CLIEN_COMUN.VAL_TEXT_COMU, '#%$;', '    '), ' ', '') email,
           MAE_CLIEN_COMUN.FEC_ULTI_ACTU,
           MAE_CLIEN_COMUN.Ind_Comu_Ppal,
           -- Se agrego el indicador de confirmacion de comunicacion
           -- se toma en cuenta solo el indicador del email
           MAE_CLIEN_COMUN.IND_CONF_COMU indicadorConfirmacion
     FROM  MAE_CLIEN_COMUN,
         MAE_TIPO_COMUN
    WHERE MAE_CLIEN_COMUN.TICM_OID_TIPO_COMU = MAE_TIPO_COMUN.OID_TIPO_COMU
     AND MAE_TIPO_COMUN.COD_TIPO_COMU in ('ML')
    ORDER BY 1
   ) cliEmail,
      (SELECT MAE_CLIEN_COMUN.CLIE_OID_CLIE,
              MAE_CLIEN_COMUN.VAL_TEXT_COMU celular,
              MAE_CLIEN_COMUN.FEC_ULTI_ACTU,
              MAE_CLIEN_COMUN.IND_COMU_PPAL
       FROM   MAE_CLIEN_COMUN,
              MAE_TIPO_COMUN
       WHERE  MAE_CLIEN_COMUN.TICM_OID_TIPO_COMU = MAE_TIPO_COMUN.OID_TIPO_COMU
         AND  MAE_TIPO_COMUN.COD_TIPO_COMU in ('TM')
        ORDER BY 1
   ) cliCelular,
   (SELECT COD_PAIS,
              CRA_PERIO.PAIS_OID_PAIS as Pais,
              CRA_PERIO.MARC_OID_MARC as Marca,
              CRA_PERIO.CANA_OID_CANA as Canal,
              SEG_PERIO_CORPO.COD_PERI,
              SEG_PERIO_CORPO.VAL_ANIO as Anio
    FROM  CRA_PERIO,
              SEG_PERIO_CORPO,
              SEG_PAIS,
              SEG_MARCA,
              SEG_CANAL
    WHERE CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
     AND CRA_PERIO.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS
     AND CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC
     AND CRA_PERIO.CANA_OID_CANA = SEG_CANAL.OID_CANA
     AND SEG_PERIO_CORPO.COD_PERI = psCodigoPeriodo
     AND SEG_PAIS.COD_PAIS =  psCodigoPais
     AND SEG_MARCA.COD_MARC = psCodigoMarca
     AND SEG_CANAL.COD_CANA = psCodigoCanal

   ) perActual
  where
   inscritas.OID_CLIE = MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE
   AND inscritas.OID_CLIE = MAE_CLIEN_VINCU.CLIE_OID_CLIE_VNDO
   AND MAE_CLIEN_VINCU.TIVC_OID_TIPO_VINC = MAE_TIPO_VINCU.OID_TIPO_VINC
   AND MAE_TIPO_VINCU.PAIS_OID_PAIS =  perActual.Pais
   AND inscritas.OID_CLIE = cliTelefono.CLIE_OID_CLIE(+)
   AND inscritas.OID_CLIE = cliEmail.CLIE_OID_CLIE(+)
      AND inscritas.OID_CLIE = cliCelular.CLIE_OID_CLIE(+)
   AND MAE_TIPO_VINCU.COD_TIPO_VINC = psCodigoTipoVinculo
   AND MAE_CLIEN_VINCU.CLIE_OID_CLIE_VNTE = clientes.OID_CLIE
   AND MAE_CLIEN_VINCU.Fec_Hast IS NULL;

   TYPE interfazRec IS RECORD
   (
    codigoPais                    seg_pais.cod_pais%TYPE,
    codigoEmpresa                 VARCHAR2(2),
    codigoConsultora              MAE_CLIEN.COD_CLIE%TYPE,
    apellidoPaterno               MAE_CLIEN.VAL_APE1%TYPE,
    apellidoMaterno               MAE_CLIEN.VAL_APE2%TYPE,
    nombre                        VARCHAR2(50),
    fechaNacimiento               VARCHAR2(8),
    email                         MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
    telefono                      MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
    celular                       MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
    indicadorAutorizacion         MAE_CLIEN_COMUN.IND_COMU_PPAL%TYPE,
    codigoPeriodo                 SEG_PERIO_CORPO.COD_PERI%TYPE,
    fechaEntregaCarnet            VARCHAR2(8),
    fechaUltimaModificacion       VARCHAR2(8),
    fechaIngreso                  VARCHAR2(8),
    ultimoUsuarioActualizo        CHAR(1),
    origenInscripcion             CHAR(1),
    claveAccesoDuplaWeb           CHAR(1),
    -- Se agrego el indicador de confirmacion de comunicacion
    indicadorConfirmacion         MAE_CLIEN_COMUN.Ind_Conf_Comu%TYPE
   );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile  BOOLEAN;

BEGIN
     lbAbrirUtlFile := TRUE;
    /* Generando Archivo de Texto (Detalle) */
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
              lsLinea :=  interfazRecord(x).codigoPais                    ||';'||
                          interfazRecord(x).codigoEmpresa                 ||';'||
                          interfazRecord(x).codigoConsultora              ||';'||
                          interfazRecord(x).apellidoPaterno               ||';'||
                          interfazRecord(x).apellidoMaterno               ||';'||
                          interfazRecord(x).nombre                        ||';'||
                          interfazRecord(x).fechaNacimiento               ||';'||
                          interfazRecord(x).email                         ||';'||
                          interfazRecord(x).telefono                      ||';'||
                          interfazRecord(x).celular                       ||';'||
                          interfazRecord(x).indicadorAutorizacion         ||';'||
                          interfazRecord(x).codigoPeriodo                 ||';'||
                          interfazRecord(x).fechaEntregaCarnet            ||';'||
                          interfazRecord(x).fechaUltimaModificacion       ||';'||
                          interfazRecord(x).fechaIngreso                  ||';'||
                          interfazRecord(x).ultimoUsuarioActualizo        ||';'||
                          interfazRecord(x).origenInscripcion             ||';'||
                          interfazRecord(x).claveAccesoDuplaWeb           ||';'||
                          -- Se agrego el indicador de confirmacion de comunicacion
                          nvl(interfazRecord(x).indicadorConfirmacion,'0')
                          ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(v_handle);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_ECO_ENVI_INSC: '||ls_sqlerrm);

END INT_PR_ECO_ENVI_INSC;

/***************************************************************************
Descripcion       : Genera Interfase de Enviar VEntas y Puntajes por Campania
Fecha Creacion    : 14/08/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE INT_PR_ECO_VENTA_PUNT_CAMPA
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psCodigoTipoVinculo        VARCHAR2,
   psCodigoClasificacion      VARCHAR2,
   psCodigoTipoClasificacion  VARCHAR2,
   psCodigoTipoCliente        VARCHAR2,
   psCodigoSubTipoCliente     VARCHAR2,
   psOidParametroGeneral      NUMBER,
   pdFechaActual                VARCHAR )

IS
   CURSOR c_interfaz( ls_fechaActual DATE, ls_oidMarca NUMBER, ls_oidCanal NUMBER, ls_oiPeriodo NUMBER) IS
  select
	cod_pais,
	num_conc,
	empresa,
	cod_clie,
	campaa,
	puntos_campaa,
	decode(puntos_campaa,0,0,sum(puntos_campaa) over (partition by cod_clie order by cod_clie, rownum)) puntos_acumulado,
	venta
from
	(
		select
			clientes.cod_pais,
			clientes.num_conc,
			' ' as empresa,
			clientes.cod_clie,
			clientes.cod_peri as campaa,
			case
				when puntos is not null and puntos < 0 then 0
				else nvl(puntos,0)
			end as puntos_campaa,
			case
				when venta is not null and venta < 0 then 0
				else nvl(venta,0)
			end as venta
		from
			(
				select
					*
				from
					(
						select
							mae_clien.oid_clie,
							mae_clien.cod_clie
						from
							mae_clien,
							mae_clien_tipo_subti,
							mae_clien_clasi
						where
							mae_clien.oid_clie = mae_clien_tipo_subti.clie_oid_clie
							and mae_clien_tipo_subti.oid_clie_tipo_subt = mae_clien_clasi.ctsu_oid_clie_tipo_subt
               AND mae_clien_tipo_subti.ticl_oid_tipo_clie  =  psCodigoTipoCliente
               AND mae_clien_tipo_subti.sbti_oid_subt_clie IN (
                                                     SELECT INT_TMP_param_inter.Val_Para_Varc
                                                     FROM INT_TMP_param_inter
                                                     WHERE INT_TMP_param_inter.Cod_Para = psCodigoSubTipoCliente
                                                           AND INT_TMP_param_inter.Cod_Inte = psCodigoInterfaz
                                                     )
               AND mae_clien_clasi.tccl_oid_tipo_clasi IN (
                                                     SELECT INT_TMP_param_inter.Val_Para_Varc
                                                     FROM INT_TMP_param_inter
                                                     WHERE INT_TMP_param_inter.Cod_Para = psCodigoTipoClasificacion
                                                           AND INT_TMP_param_inter.Cod_Inte = psCodigoInterfaz
                                                     )

               AND mae_clien_clasi.clas_oid_clas IN (
                                            SELECT INT_TMP_param_inter.Val_Para_Varc
                                            FROM INT_TMP_param_inter
                                            WHERE INT_TMP_param_inter.Cod_Para = psCodigoClasificacion
                                               AND INT_TMP_param_inter.Cod_Inte = psCodigoInterfaz
                                         )
						ORDER BY mae_clien.cod_clie
					) clientes,
					(
						select
							seg_perio_corpo.cod_peri,
							seg_pais.cod_pais,
							num_conc
						from
							inc_concu_param_gener,
							cra_perio per_desde,
							cra_perio,
							seg_perio_corpo,
							seg_pais,
							(
								select
									cra_perio.fec_inic
								from
									cra_perio
								where
									cra_perio.oid_peri = ls_oiPeriodo--parmetro
							) peractual
						where
							1=1
							and cra_perio.peri_oid_peri = seg_perio_corpo.oid_peri
							and cra_perio.MARC_OID_MARC = ls_oidMarca--parmetro
							and cra_perio.CANA_OID_CANA = ls_oidCanal--parmetro
							and cra_perio.pais_oid_pais = seg_pais.oid_pais
							and inc_concu_param_gener.oid_para_gral = psOidParametroGeneral--parmetro
							and inc_concu_param_gener.perd_oid_peri_desd = per_desde.oid_peri
							and cra_perio.fec_inic <= peractual.fec_inic
							and cra_perio.fec_inic >= per_desde.fec_inic
					) per
			) clientes left outer join
			(
				select
					INC_SOLIC_CONCU_PUNTA.clie_oid_clie,
					seg_perio_corpo.cod_peri,
					sum(INC_SOLIC_CONCU_PUNTA.IMP_MONT) venta
				from
					INC_SOLIC_CONCU_PUNTA,
					inc_concu_param_gener,
					cra_perio,
					seg_perio_corpo
				WHERE INC_SOLIC_CONCU_PUNTA.perd_oid_peri = cra_perio.oid_peri
					and cra_perio.peri_oid_peri = seg_perio_corpo.oid_peri
					and inc_concu_param_gener.oid_para_gral = psOidParametroGeneral--parmetro
					and INC_SOLIC_CONCU_PUNTA.COPA_OID_PARA_GRAL = inc_concu_param_gener.oid_para_gral
					and (
							(INC_SOLIC_CONCU_PUNTA.IND_ANUL = 0	and INC_SOLIC_CONCU_PUNTA.NUM_PUNT >= 0)
						or (INC_SOLIC_CONCU_PUNTA.IND_ANUL = 1	and INC_SOLIC_CONCU_PUNTA.NUM_PUNT < 0)
						)
				group by
					INC_SOLIC_CONCU_PUNTA.clie_oid_clie,
					seg_perio_corpo.cod_peri
			) pedidos on (clientes.oid_clie = pedidos.clie_oid_clie and clientes.cod_peri = pedidos.cod_peri) left outer join
			(
				select
					inc_cuent_corri_punto.clie_oid_clie,
					seg_perio_corpo.cod_peri,
					sum(inc_cuent_corri_punto.num_punt) puntos
				from
					inc_cuent_corri_punto,
					cra_perio,
					seg_perio_corpo
				where
					inc_cuent_corri_punto.copa_oid_para_gral = psOidParametroGeneral--parmetro
					and inc_cuent_corri_punto.perd_oid_peri = cra_perio.oid_peri
					and cra_perio.peri_oid_peri = seg_perio_corpo.oid_peri
				group by
					clie_oid_clie,
					seg_perio_corpo.cod_peri
			) puntos on (clientes.oid_clie = puntos.clie_oid_clie and clientes.cod_peri = puntos.cod_peri)
		order by
			clientes.cod_clie,
			clientes.cod_peri
	);
   ls_fechaActual DATE;
   ls_oidMarca NUMBER;
   ls_oidCanal NUMBER;
   ls_oidPeriodo NUMBER;
   TYPE interfazRec IS RECORD
   (
    codigoPais                    seg_pais.cod_pais%TYPE,
    numeroConcurso                inc_concu_param_gener.num_conc%TYPE,
    codigoEmpresa                 VARCHAR2(2),
    codigoConsultora              MAE_CLIEN.COD_CLIE%TYPE,
    codigoPeriodo                 SEG_PERIO_CORPO.COD_PERI%TYPE,
    puntosCampania                NUMBER,
    puntosAcumulado               NUMBER,
    venta                         NUMBER
    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile  BOOLEAN;

BEGIN

    ls_fechaActual:= TO_Date(pdFechaActual,'dd/mm/yyyy');
    ls_oidMarca:= gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca );
    ls_oidCanal:=gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL( psCodigoCanal);
    ls_oidPeriodo:= gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo ,  ls_oidMarca , ls_oidCanal );
    lbAbrirUtlFile := TRUE;

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz(ls_fechaActual,ls_oidMarca,ls_oidCanal,ls_oidPeriodo);
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
              lsLinea :=  interfazRecord(x).codigoPais                ||';'||
                          interfazRecord(x).numeroConcurso            ||';'||
                          interfazRecord(x).codigoEmpresa             ||';'||
                          interfazRecord(x).codigoConsultora          ||';'||
                          interfazRecord(x).codigoPeriodo             ||';'||
                          interfazRecord(x).puntosCampania            ||';'||
                          interfazRecord(x).puntosAcumulado           ||';'||
                          interfazRecord(x).venta          ;
              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(v_handle);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_ECO_VENTA_PUNT_CAMPA: '||ls_sqlerrm);

END INT_PR_ECO_VENTA_PUNT_CAMPA;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Venta BAse Consultora
  Fecha Creacion     : 28/11/2006
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_ECO_VENTA_BASE_CONSU (
   psCodigoPais     VARCHAR2,
   psCodigoSistema  VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo  VARCHAR2)

IS
   CURSOR c_interfaz IS
     SELECT  B.COD_PAIS,
             B.COD_PERI,
             B.COD_CONS,
             B.COD_EMPR,
             B.COD_CONC,
             B.VAL_PNTJ
      FROM INT_INC_VENTA_BASE B;

   TYPE interfazRec IS RECORD
   (
   codigoPais                   INT_INC_VENTA_BASE.COD_PAIS%TYPE,
   codigoPeriodo                INT_INC_VENTA_BASE.COD_PERI%TYPE,
   codigoCliente                INT_INC_VENTA_BASE.COD_CONS%TYPE,
   codigoEmpresa                INT_INC_VENTA_BASE.COD_EMPR%TYPE,
   codigoConcurso               INT_INC_VENTA_BASE.COD_CONC%TYPE,
   puntaje                      INT_INC_VENTA_BASE.VAL_PNTJ%TYPE
   );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   -- Variables usadas para la ejecucion del PL/SQL Dinamico.
  l_Sqlc       VARCHAR2(1000);
  l_Cur        INTEGER;
  l_Rowp       INTEGER;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

  /* Variables de parametros */
  ls_compila          VARCHAR2(1000);
BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                          interfazRecord(x).codigoConcurso                 ||';'||
                          interfazRecord(x).codigoEmpresa                ||';'||
                          interfazRecord(x).codigoPeriodo                ||';'||
                          interfazRecord(x).codigoCliente                 ||';'||
                          interfazRecord(x).puntaje  ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_ECO_VENTA_BASE_CONSU: '||ls_sqlerrm);

END INT_PR_ECO_VENTA_BASE_CONSU;
/***************************************************************************
Descripcion       : Obtiene el Origen de Descripcion. Con el MAe Clien Primer
                    contacto
Fecha Creacion    : 10/08/2006
Autor             : Marco Agurto
***************************************************************************/
FUNCTION INT_FN_ECO_OBTIE_ORIGE_INSCR(psOidClie NUMBER)
RETURN VARCHAR2 IS
ls_resultado VARCHAR2(6);

BEGIN
SELECT MAE_CLIEN_PRIME_CONTA.COD_TIPO_CONT
INTO ls_resultado
FROM MAE_CLIEN_PRIME_CONTA
WHERE MAE_CLIEN_PRIME_CONTA.Clie_Oid_Clie = psOidClie;

IF ls_resultado = 'W' THEN
   ls_resultado :='W';
ELSE
   ls_resultado :='C';
END IF;
RETURN ls_resultado;
EXCEPTION
WHEN no_data_found THEN
     RETURN 'C';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_FN_ECO_OBTIE_ORIGE_INSCR: '||ls_sqlerrm);
END INT_FN_ECO_OBTIE_ORIGE_INSCR;

/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Matriz de Productos con Oferta
                    de Cumpleaos
Fecha Creacion    : 16/06/2009
Autor             : Jos A. Cairampoma
***************************************************************************/
PROCEDURE int_pr_ecm_matri_ofert_cumpl
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  pscodigoperiodo  VARCHAR2
) IS

  CURSOR c_interfaz(vnidperiodo NUMBER) IS

    SELECT pscodigopais codigopais,
           gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri) periodo,
           (SELECT prod.cod_sap
              FROM mae_produ prod
             WHERE prod.oid_prod = c.prod_oid_prod) codproducto,
           (SELECT pto.cod_tipo_ofer
              FROM pre_tipo_ofert pto
             WHERE pto.oid_tipo_ofer = c.tofe_oid_tipo_ofer),
           c.val_codi_vent codigoventa,
           c.imp_prec_cata precio
      FROM pre_matri_factu_cabec a,
           pre_matri_factu       b,
           pre_ofert_detal       c,
           pre_ofert             d,
           pre_venta_exclu       e
     WHERE a.oid_cabe = b.mfca_oid_cabe
       AND b.ofde_oid_deta_ofer = c.oid_deta_ofer
       AND c.ofer_oid_ofer = d.oid_ofer
       AND d.oid_ofer = e.ofer_oid_ofer
       AND a.perd_oid_peri = vnidperiodo
       AND e.ticl_oid_tipo_clie IN
           (SELECT DISTINCT mtc.oid_tipo_clie
              FROM mae_tipo_clien      mtc,
                   int_ecm_param_matri i
             WHERE cod_inte = pscodigointerfaz
               AND cod_pais = pscodigopais
               AND mtc.cod_tipo_clie = i.cod_tipo_clie)
       AND e.sbti_oid_subt_clie IN
           (SELECT mstc.oid_subt_clie
              FROM int_ecm_param_matri i,
                   mae_subti_clien     mstc
             WHERE cod_inte = pscodigointerfaz
               AND cod_pais = pscodigopais
               AND mstc.cod_subt_clie = i.cod_subt_clie)
       AND e.tccl_oid_tipo_clas IN
           (SELECT mtcl.oid_tipo_clas
              FROM mae_tipo_clasi_clien mtcl,
                   int_ecm_param_matri  i
             WHERE cod_inte = pscodigointerfaz
               AND cod_pais = pscodigopais
               AND mtcl.cod_tipo_clas = i.cod_tipo_clas)
       AND e.clas_oid_clas IN
           (SELECT mcl.oid_clas
              FROM mae_clasi           mcl,
                   int_ecm_param_matri i
             WHERE cod_inte = pscodigointerfaz
               AND cod_pais = pscodigopais
               AND mcl.cod_clas = i.cod_subt_clas)
     GROUP BY a.perd_oid_peri,
              c.prod_oid_prod,
              c.tofe_oid_tipo_ofer,
              c.val_codi_vent,
              c.imp_prec_cata;

  TYPE interfazrec IS RECORD(
    codpais       bas_pais.cod_pais%TYPE,
    periodo       seg_perio_corpo.cod_peri%TYPE,
    codproducto   mae_produ.cod_sap%TYPE,
    codtipooferta pre_tipo_ofert.cod_tipo_ofer%TYPE,
    codventa      pre_ofert_detal.val_codi_vent%TYPE,
    precio        pre_ofert_detal.imp_prec_cata%TYPE);
  TYPE interfazrectab IS TABLE OF interfazrec;
  interfazrecord interfazrectab;

  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo           bas_inter.dir_temp%TYPE;
  w_filas              NUMBER := 1000;
  v_handle             utl_file.file_type;
  lslinea              VARCHAR2(1000);
  lsnombrearchivo      VARCHAR2(50);
  lnidperiodo          NUMBER;
  lnidmarca            NUMBER;
  lnidcanal            NUMBER;
  lnidvigenciaconcurso NUMBER;
  lnidtipoarticulo     NUMBER;
  lbabrirutlfile       BOOLEAN;

BEGIN

  lnidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
  /* Generando Archivo de Texto (Detalle) */

  lnidvigenciaconcurso := 1; -- id del vigencia de concurso corresponde a oid inc_vigen_concu.oid_vige_conc dado que  no existe codigo.
  lnidtipoarticulo     := 2; -- id del Premio tipo articulo corresponde a oid inc_tipo_premi.oid_tipo_prem dado que  no existe codigo.
  lbabrirutlfile       := TRUE;

  OPEN c_interfaz(lnidperiodo);
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

    IF interfazrecord.COUNT > 0 THEN
      FOR x IN interfazrecord.FIRST .. interfazrecord.LAST
      LOOP

        lslinea := interfazrecord(x).codpais || ';' || --
                   interfazrecord(x).periodo || ';' || --
                   interfazrecord(x).codproducto || ';' || --
                   interfazrecord(x).codtipooferta || ';' || --
                   interfazrecord(x).codventa || ';' || --
                   interfazrecord(x).precio;
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
  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR INT_PR_ECM_MATRI_OFERT_CUMPL: ' ||
                             ls_sqlerrm);
END int_pr_ecm_matri_ofert_cumpl;

/****************************************************************************
Descripcion       : Genera Interfaz de Envio de Clientes (ECM-10)
Fecha Creacion    : 17/05/2013
Parametros        :
    psCodigoPais     : Codigo Pais
    psCodigoSistema  : Codigo Empresa
    psCodigoInterfaz : Codigo Interfaz
    psNombreArchivo  : Nombre Arcchivo
Autor             : Aurelio Oviedo
*****************************************************************************/
PROCEDURE INT_PR_ECM_ENVIO_CLIEN
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2
) IS
    
    lnOidPais   SEG_PAIS.OID_PAIS%TYPE;
    lnValor     BAS_HISTO_LOTES.FEC_FPRO%TYPE;

    CURSOR c_interfaz(psOidPais NUMBER, psFecha DATE) IS
        SELECT MC.COD_CLIE CODIGOCLIENTE,
               PA.COD_PAIS CODIGOPAIS,
               MC.VAL_APE1 APELLIDOPATERNO,
               MC.VAL_APE2 APELLIDOMATERNO,
               MC.VAL_APEL_CASA APELLIDOCASADA,
               MC.VAL_NOM1 NOMBREPRIMERO,
               MC.VAL_NOM2 NOMBRESEGUNDO,
               TO_CHAR(CA.FEC_NACI, 'YYYYMMDD') FECHANACIMIENTO,
               MC.COD_SEXO SEXO,
               TC1.COD_TIPO_CLIE TIPOCLIENTE,
               SC.COD_SUBT_CLIE SUBTIPOCLIENTE,
               PA.COD_PAIS NACIONALIDAD,
               EC.COD_ESTA_CIVI ESTADOCIVIL,
               DECODE(TDO.COD_TIPO_DOCU,
                      '01',
                      TDO.COD_TIPO_DOCU,
                      NVL((SELECT TD.COD_TIPO_DOCU
                            FROM MAE_CLIEN_IDENT CI, MAE_TIPO_DOCUM TD
                           WHERE MC.OID_CLIE = CI.CLIE_OID_CLIE
                             AND TD.OID_TIPO_DOCU = CI.TDOC_OID_TIPO_DOCU
                             AND TD.COD_TIPO_DOCU = '01'),
                          TDO.COD_TIPO_DOCU)) TIPODOCUMENTO,
               DECODE(TDO.COD_TIPO_DOCU,
                      '01',
                      CID.NUM_DOCU_IDEN,
                      NVL((SELECT CI.NUM_DOCU_IDEN
                            FROM MAE_CLIEN_IDENT CI, MAE_TIPO_DOCUM TD
                           WHERE MC.OID_CLIE = CI.CLIE_OID_CLIE
                             AND TD.OID_TIPO_DOCU = CI.TDOC_OID_TIPO_DOCU
                             AND TD.COD_TIPO_DOCU = '01'),
                          CID.NUM_DOCU_IDEN)) NUMERODOCUMENTO,
               TV.COD_TIPO_VIA TIPOVIA,
               NVL(CD.VAL_NOMB_VIA, VI.NOM_VIA) NOMBREVIA,
               CD.NUM_PPAL NUMEROPRINCIPAL,
               CD.COD_UNID_GEOG UBIGEO,
               MA.COD_MARC CODIGOMARCA,
               '' CODIGOPAISANTIGUO,
               '' CODIGOCLIENTEANTIGUO,
               '' CODIGOCANALVENTAANTIGUO,
               CA.VAL_PROF PROFESION,
               CA.VAL_OCUP OCUPACION,
               '' ESPECIALIDAD,
               CA.VAL_CENT_TRAB CENTROTRABAJO,
               CA.VAL_CARG_DESE CARGO,
               NE.COD_NIVE_ESTU NIVELESTUDIOS,
               CA.VAL_CENT_ESTU CENTROESTUDIOS,
               NVL(CA.NUM_HIJO,0) NUMEROHIJOS,
               '' TIPOCLASIFICACION,
               '' CLASIFICACION,
               TC2.COD_TIPO_COMU CODIGOTIPOCOMUNICACION,
               CO.VAL_TEXT_COMU TEXTOMEDIOCOMUNICACION,
               PC.COD_TIPO_CONT CODIGOTIPOCONTACTO,
               TO_CHAR(PC.FEC_CONT, 'YYYYMMDD') FECHACONTACTO,
               TV2.COD_TIPO_VINC CODIGOTIPOVINCULO,
               (SELECT M.COD_CLIE
                  FROM MAE_CLIEN M
                 WHERE M.OID_CLIE = CV.CLIE_OID_CLIE_VNTE) CODIGOCLIENTEVINCULO,
               TO_CHAR(CV.FEC_DESD, 'YYYYMMDD') FECHADESDE,
               TO_CHAR(CV.FEC_HAST, 'YYYYMMDD') FECHAHASTA,
               CV.IND_VINC_PPAL INDVINCULOPRINCIPAL,
               EC1.COD_ESTA_CLIE ESTATUS
          FROM MAE_CLIEN             MC,
               MAE_CLIEN_COMUN       CO,
               MAE_CLIEN_DATOS_ADICI CA,
               MAE_CLIEN_DIREC       CD,
               MAE_CLIEN_IDENT       CID,
               MAE_CLIEN_MARCA       CM,
               MAE_CLIEN_PRIME_CONTA PC,
               MAE_CLIEN_TIPO_SUBTI  CT,
               MAE_CLIEN_VINCU       CV,
               MAE_CODIG_CLIEN_ANTER AN,
               MAE_ESTAD_CIVIL       EC,
               MAE_NIVEL_ESTUD       NE,
               MAE_SUBTI_CLIEN       SC,
               MAE_TIPO_CLIEN        TC1,
               MAE_TIPO_COMUN        TC2,
               MAE_TIPO_DOCUM        TDO,
               MAE_TIPO_VINCU        TV2,
               SEG_MARCA             MA,
               SEG_PAIS              PA,
               SEG_TIPO_VIA          TV,
               ZON_VIA               VI,
               MAE_ESTAT_CLIEN       EC1
         WHERE MC.PAIS_OID_PAIS = psOidPais  --oidPais
           AND ((psFecha is null or psFecha < MC.FEC_ULTI_ACTU) OR --fechaUltimaEjecucion
               (psFecha is null or psFecha < CO.FEC_ULTI_ACTU) OR
               (psFecha is null or psFecha < CA.FEC_ULTI_ACTU) OR
               (psFecha is null or psFecha < CD.FEC_ULTI_ACTU) OR
               (psFecha is null or psFecha < CID.FEC_ULTI_ACTU) OR
               (psFecha is null or psFecha < CM.FEC_ULTI_ACTU) OR
               (psFecha is null or psFecha < PC.FEC_ULTI_ACTU) OR
               (psFecha is null or psFecha < CT.FEC_ULTI_ACTU) OR
               (psFecha is null or psFecha < CV.FEC_ULTI_ACTU))
           AND CT.TICL_OID_TIPO_CLIE = 2
           AND MC.PAIS_OID_PAIS = PA.OID_PAIS
           AND MC.OID_CLIE = CT.CLIE_OID_CLIE
           AND MC.OID_CLIE = CA.CLIE_OID_CLIE(+)
           AND CA.NIED_OID_NIVE_ESTU = NE.OID_NIVE_ESTU(+)
           AND CA.ESCV_OID_ESTA_CIVI = EC.OID_ESTA_CIVI(+)
           AND CA.IND_ACTI = 1
           AND CA.ESTA_OID_ESTA_CLIE = EC1.OID_ESTA_CLIE
           AND EC1.COD_ESTA_CLIE <> '01'
           AND EC1.COD_ESTA_CLIE <> '07'
           AND MC.OID_CLIE = CID.CLIE_OID_CLIE
           AND CID.VAL_IDEN_DOCU_PRIN = 1
           AND TDO.OID_TIPO_DOCU(+) = CID.TDOC_OID_TIPO_DOCU
           AND CT.TICL_OID_TIPO_CLIE = TC1.OID_TIPO_CLIE(+)
           AND CT.SBTI_OID_SUBT_CLIE = SC.OID_SUBT_CLIE
           AND TC1.OID_TIPO_CLIE = SC.TICL_OID_TIPO_CLIE(+)
           AND (SC.COD_SUBT_CLIE = '04' OR SC.COD_SUBT_CLIE = '06')
           AND MC.OID_CLIE = CD.CLIE_OID_CLIE(+)
           AND CD.IND_ELIM = 0
           AND CD.IND_DIRE_PPAL = 1
           AND CD.ZVIA_OID_VIA = VI.OID_VIA(+)
           AND CD.TIVI_OID_TIPO_VIA = TV.OID_TIPO_VIA(+)
           AND MC.OID_CLIE = CM.CLIE_OID_CLIE(+)
           AND CM.MARC_OID_MARC = MA.OID_MARC(+)
           AND MC.OID_CLIE = AN.CLIE_OID_CLIE_NUEV(+)
           AND MC.OID_CLIE = CO.CLIE_OID_CLIE(+)
           AND CO.TICM_OID_TIPO_COMU = TC2.OID_TIPO_COMU(+)
           AND CO.IND_COMU_PPAL(+) = 1
           AND MC.OID_CLIE = PC.CLIE_OID_CLIE(+)
           AND MC.OID_CLIE = CV.CLIE_OID_CLIE_VNDO(+)
           AND CV.TIVC_OID_TIPO_VINC = TV2.OID_TIPO_VINC(+)
           AND CV.IND_VINC_PPAL(+) = 1;

    TYPE interfazrec IS RECORD(
      codcliente        MAE_CLIEN.COD_CLIE%TYPE,
      codpais           SEG_PAIS.COD_PAIS%TYPE,
      apppaterno        MAE_CLIEN.VAL_APE1%TYPE,
      appmaterno        MAE_CLIEN.VAL_APE2%TYPE,
      appcasada         MAE_CLIEN.VAL_APEL_CASA%TYPE,
      prinombre         MAE_CLIEN.VAL_NOM1%TYPE,
      segnombre         MAE_CLIEN.VAL_NOM2%TYPE,
      fecnacimiento     VARCHAR2(8),
      sexo              MAE_CLIEN.COD_SEXO%TYPE,
      codtipocliente    MAE_TIPO_CLIEN.COD_TIPO_CLIE%TYPE,
      codsubtipocliente MAE_SUBTI_CLIEN.COD_SUBT_CLIE%TYPE,
      nacionalidad      SEG_PAIS.COD_PAIS%TYPE,
      estcivil          MAE_ESTAD_CIVIL.COD_ESTA_CIVI%TYPE,
      tipdocidentidad   MAE_TIPO_DOCUM.COD_TIPO_DOCU%TYPE,
      numdocidentidad   MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE,
      tipovia           SEG_TIPO_VIA.COD_TIPO_VIA%TYPE,
      nombrevia         MAE_CLIEN_DIREC.VAL_NOMB_VIA%TYPE,
      numprincipal      MAE_CLIEN_DIREC.NUM_PPAL%TYPE,
      ubigeo            VARCHAR2(60),
      codmarca          SEG_MARCA.COD_MARC%TYPE,
      codpaisant        VARCHAR2(3),
      codclienteant     VARCHAR2(3),
      codcanalventaant  VARCHAR2(2),
      profesion         MAE_CLIEN_DATOS_ADICI.VAL_PROF%TYPE,
      ocupacion         MAE_CLIEN_DATOS_ADICI.VAL_OCUP%TYPE,
      especialidad      VARCHAR2(20),
      centrotrabajo     MAE_CLIEN_DATOS_ADICI.VAL_CENT_TRAB%TYPE,
      cargo             MAE_CLIEN_DATOS_ADICI.VAL_CARG_DESE%TYPE,
      nivelestudios     MAE_NIVEL_ESTUD.COD_NIVE_ESTU%TYPE,
      centroestudios    MAE_CLIEN_DATOS_ADICI.VAL_CENT_ESTU%TYPE,
      numhijos          MAE_CLIEN_DATOS_ADICI.NUM_HIJO%TYPE,
      tipoclasificacion VARCHAR2(2),
      clasificacion     VARCHAR2(2),
      codtipocomun      MAE_TIPO_COMUN.COD_TIPO_COMU%TYPE,
      txtmediocomun     MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
      codtipocontacto   VARCHAR2(2),
      fechacontacto     VARCHAR2(8),
      codtipovinculo    MAE_TIPO_VINCU.COD_TIPO_VINC%TYPE,
      codclientevinculo MAE_CLIEN.COD_CLIE%TYPE,
      fechadesde        VARCHAR2(8),
      fechahasta        VARCHAR2(8),
      indvinculoprin    MAE_CLIEN_VINCU.IND_VINC_PPAL%TYPE,
      estatus           MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;

    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */

    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;

  BEGIN
    
    lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais); 
  
    BEGIN
      SELECT MAX(FEC_FPRO)
          INTO lnValor
        FROM BAS_HISTO_LOTES
       WHERE PAIS_COD_PAIS = pscodigopais
         AND SIST_COD_SIST = pscodigosistema
         AND INTE_COD_INTE = pscodigointerfaz;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            lnValor := '';
    END;
  
    /* Generando Archivo de Texto (Detalle) */

    lbabrirutlfile := TRUE;

    OPEN c_interfaz(lnOidPais, lnValor);
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
          lslinea := interfazrecord(x).codcliente           || ';' ||
                     interfazrecord(x).codpais              || ';' ||
                     interfazrecord(x).apppaterno           || ';' ||
                     interfazrecord(x).appmaterno           || ';' ||
                     interfazrecord(x).appcasada            || ';' ||
                     interfazrecord(x).prinombre            || ';' ||
                     interfazrecord(x).segnombre            || ';' ||
                     interfazrecord(x).fecnacimiento        || ';' ||
                     interfazrecord(x).sexo                 || ';' ||
                     interfazrecord(x).codtipocliente       || ';' ||
                     interfazrecord(x).codsubtipocliente    || ';' ||
                     interfazrecord(x).nacionalidad         || ';' ||
                     interfazrecord(x).estcivil             || ';' ||
                     interfazrecord(x).tipdocidentidad      || ';' ||
                     interfazrecord(x).numdocidentidad      || ';' ||
                     interfazrecord(x).tipovia              || ';' ||
                     interfazrecord(x).nombrevia            || ';' ||
                     interfazrecord(x).numprincipal         || ';' ||
                     interfazrecord(x).ubigeo               || ';' ||
                     interfazrecord(x).codmarca             || ';' ||
                     interfazrecord(x).codpaisant           || ';' ||
                     interfazrecord(x).codclienteant        || ';' ||
                     interfazrecord(x).codcanalventaant     || ';' ||
                     interfazrecord(x).profesion            || ';' ||
                     interfazrecord(x).ocupacion            || ';' ||
                     interfazrecord(x).especialidad         || ';' ||
                     interfazrecord(x).centrotrabajo        || ';' ||
                     interfazrecord(x).cargo                || ';' ||
                     interfazrecord(x).nivelestudios        || ';' ||
                     interfazrecord(x).centroestudios       || ';' ||
                     interfazrecord(x).numhijos             || ';' ||
                     interfazrecord(x).tipoclasificacion    || ';' ||
                     interfazrecord(x).clasificacion        || ';' ||
                     interfazrecord(x).codtipocomun         || ';' ||
                     interfazrecord(x).txtmediocomun        || ';' ||
                     interfazrecord(x).codtipocontacto      || ';' ||
                     interfazrecord(x).fechacontacto        || ';' ||
                     interfazrecord(x).codtipovinculo       || ';' ||
                     interfazrecord(x).codclientevinculo    || ';' ||
                     interfazrecord(x).fechadesde           || ';' ||
                     interfazrecord(x).fechahasta           || ';' ||
                     interfazrecord(x).indvinculoprin       || ';' ||
                     interfazrecord(x).estatus;
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

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_ECM_ENVIO_CLIEN: ' || ls_sqlerrm);
END INT_PR_ECM_ENVIO_CLIEN;

END INT_PKG_ECOMM;
/
