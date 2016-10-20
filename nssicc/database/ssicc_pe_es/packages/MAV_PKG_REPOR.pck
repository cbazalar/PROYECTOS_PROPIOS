CREATE OR REPLACE PACKAGE MAV_PKG_REPOR IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(150);
W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion Fecha
                   Campa?a Por Gerente Con Fecha.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteConFechaXLS
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_CONFE_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion Fecha
                   Campa?a Por Gerente Con Fecha Region Zona.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionZona
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_CONFE_REGZO_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );


/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion Fecha
                   Campa?a Por Gerente Detalle Con Sin Fecha Tipo Cargo.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargo
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_DETAL_TIPO_CARGO_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2,
    codigoTipoCargo     VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion
                       Fecha Campanha Por Gerente Detalle Con Sin Fecha.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFecha
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_DETAL_CONSI_FECHA_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion
                       Fecha Campanha Por Gerente Sin Fecha Region.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegion
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_SINFE_REGIO_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion
                       Fecha Campanha Por Gerente Sin Fecha Region Zona.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionZona
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_SINFE_REGZO_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion Fecha
                   Campa?a Por Gerente Sin Fecha.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteSinFechaXLS
Fecha Creacion    : 24/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_SINFE_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Envios Fecha Campanha
                       Con Sin Fecha.
                   -  reporteMAVEnviosFechaCampanhaConSinFecha
Fecha Creacion    : 24/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_ENVIO_CONSI_FECHA_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegion
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
***************************************************************************/
PROCEDURE mav_pr_consu_confe_regio_csv
(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
);

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionZona
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
***************************************************************************/
PROCEDURE mav_pr_consu_confe_regzo_csv
(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
);

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraConFecha
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
***************************************************************************/
PROCEDURE mav_pr_consu_confe_csv
(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
);

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraDetalleConSinFecha
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
***************************************************************************/
PROCEDURE mav_pr_consu_detal_consi_csv
(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
);

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegion
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
***************************************************************************/
PROCEDURE mav_pr_consu_sinfe_regio_csv
(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
);

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionZona
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
***************************************************************************/
PROCEDURE mav_pr_consu_sinfe_regzo_csv
(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
);

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraSinFecha
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
***************************************************************************/
PROCEDURE mav_pr_consu_sinfe_csv
(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
);

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegion
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
***************************************************************************/
PROCEDURE mav_pr_geren_confe_regio_csv
(
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
);


END MAV_PKG_REPOR;
/
CREATE OR REPLACE PACKAGE BODY MAV_PKG_REPOR IS

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion Fecha
                   Campa?a Por Gerente Con Fecha.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteConFechaXLS
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_CONFE_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS
  SELECT  cam_para_mav CAMPANA,
        fec_fact FECHA_FACTURACION,
        cod_acti CODACTIVIDAD,
        des_acti ACTIVIDAD,
        cod_tipo_ofer CODTOFERTA,
        des_tipo_ofer TIPOOFERTA,
        cod_sap CODIGOSAP,
        des_prod DESCPRODUCTO,
        val_codi_vent CUV,
        SUM(num_unid_dema_real) UNIDEMANDA,
        SUM(num_unid_aten) UNIATENDIDA,
        SUM(num_unid_falt) UNIFALTANTE
     FROM MAV_REPOR_CAMPA_CONSU
      GROUP BY
	       cam_para_mav,
	       fec_fact,
	       cod_acti,
	       des_acti,
	       cod_tipo_ofer,
	       des_tipo_ofer,
	       cod_sap,
	       des_prod,
	       val_codi_vent;

TYPE interfazTipo IS RECORD (

 v_cam_para_mav      MAV_REPOR_CAMPA_CONSU.CAM_PARA_MAV%TYPE,
 v_fec_fact          MAV_REPOR_CAMPA_CONSU.FEC_FACT%TYPE,
 v_cod_acti          MAV_REPOR_CAMPA_CONSU.COD_ACTI%TYPE,
 v_des_acti          MAV_REPOR_CAMPA_CONSU.DES_ACTI%TYPE,
 v_cod_tipo_ofer     MAV_REPOR_CAMPA_CONSU.COD_TIPO_OFER%TYPE,
 v_des_tipo_ofer     MAV_REPOR_CAMPA_CONSU.DES_TIPO_OFER%TYPE,
 v_cod_sap           MAV_REPOR_CAMPA_CONSU.COD_SAP%TYPE,
 v_des_prod          MAV_REPOR_CAMPA_CONSU.DES_PROD%TYPE,
 v_val_codi_vent     MAV_REPOR_CAMPA_CONSU.VAL_CODI_VENT%TYPE,
 v_num_unid_dema_real NUMBER(20),
 v_num_unid_aten  NUMBER(20),
 v_num_unid_falt  NUMBER(20)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).v_fec_fact ||','||
                    interfazRecord(x).v_des_acti ||','||
                    interfazRecord(x).v_des_tipo_ofer ||','||
                    '=T("'||interfazRecord(x).v_cod_sap||'")' ||','||
                    interfazRecord(x).v_des_prod ||','||
                    '=T("'||interfazRecord(x).v_val_codi_vent||'")' ||','||
                    interfazRecord(x).v_num_unid_dema_real ||','||
                    interfazRecord(x).v_num_unid_aten ||','||
                    interfazRecord(x).v_num_unid_falt;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_GEREN_CONFE_CSV: '||ls_sqlerrm);
END MAV_PR_GEREN_CONFE_CSV;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion Fecha
                   Campa?a Por Gerente Con Fecha Region Zona.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionZonaXLS
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_CONFE_REGZO_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS
  SELECT  cam_para_mav CAMPANA,
				COD_REGI,
				COD_ZONA,
				fec_fact FECHA_FACTURACION,
				cod_acti CODACTIVIDAD,
				des_acti ACTIVIDAD,
				cod_tipo_ofer CODTOFERTA,
				des_tipo_ofer TIPOOFERTA,
				cod_sap CODIGOSAP,
				des_prod DESCPRODUCTO,
				val_codi_vent CUV,
				SUM(num_unid_dema_real) UNIDEMANDA,
				SUM(num_unid_aten) UNIATENDIDA,
				SUM(num_unid_falt) UNIFALTANTE
     FROM MAV_REPOR_CAMPA_CONSU
      GROUP BY
	       cam_para_mav,
	       COD_REGI,
	       COD_ZONA,
	       fec_fact,
	       cod_acti,
	       des_acti,
	       cod_tipo_ofer,
	       des_tipo_ofer,
	       cod_sap,
	       des_prod,
	       val_codi_vent;

TYPE interfazTipo IS RECORD (

 v_cam_para_mav      MAV_REPOR_CAMPA_CONSU.CAM_PARA_MAV%TYPE,
 v_cod_regi				   MAV_REPOR_CAMPA_CONSU.COD_REGI%TYPE,
 v_cod_zona				   MAV_REPOR_CAMPA_CONSU.COD_ZONA%TYPE,
 v_fec_fact          MAV_REPOR_CAMPA_CONSU.FEC_FACT%TYPE,
 v_cod_acti          MAV_REPOR_CAMPA_CONSU.COD_ACTI%TYPE,
 v_des_acti          MAV_REPOR_CAMPA_CONSU.DES_ACTI%TYPE,
 v_cod_tipo_ofer     MAV_REPOR_CAMPA_CONSU.COD_TIPO_OFER%TYPE,
 v_des_tipo_ofer     MAV_REPOR_CAMPA_CONSU.DES_TIPO_OFER%TYPE,
 v_cod_sap           MAV_REPOR_CAMPA_CONSU.COD_SAP%TYPE,
 v_des_prod          MAV_REPOR_CAMPA_CONSU.DES_PROD%TYPE,
 v_val_codi_vent     MAV_REPOR_CAMPA_CONSU.VAL_CODI_VENT%TYPE,
 v_num_unid_dema_real NUMBER(20),
 v_num_unid_aten  NUMBER(20),
 v_num_unid_falt  NUMBER(20)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).v_cod_regi ||','||
                    interfazRecord(x).v_cod_zona ||','||
                    interfazRecord(x).v_fec_fact ||','||
                    interfazRecord(x).v_des_acti ||','||
                    interfazRecord(x).v_des_tipo_ofer ||','||
                    '=T("'||interfazRecord(x).v_cod_sap||'")' ||','||
                    interfazRecord(x).v_des_prod ||','||
                    '=T("'||interfazRecord(x).v_val_codi_vent||'")' ||','||
                    interfazRecord(x).v_num_unid_dema_real ||','||
                    interfazRecord(x).v_num_unid_aten ||','||
                    interfazRecord(x).v_num_unid_falt;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_GEREN_CONFE_REGZO_CSV: '||ls_sqlerrm);
END MAV_PR_GEREN_CONFE_REGZO_CSV;


/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion Fecha
                   Campa?a Por Gerente Detalle Con Sin Fecha Tipo Cargo.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargo
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_DETAL_TIPO_CARGO_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2,
    codigoTipoCargo     VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz(codTipoCargo MAV_REPOR_CAMPA_CONSU_DETAL.COD_TIPO_CARG%type) IS
  SELECT cam_para_mav CAMPANA,
				fec_fact FECHA_ENVIO,
				cod_regi REGION,
				cod_zona ZONA,
				cod_acti CODACTIVIDAD,
        des_acti ACTIVIDAD,
				cod_tipo_ofer CODTOFERTA,
				des_tipo_ofer TIPOOFERTA,
				cod_clie CODIGOCLIE,
				des_clie CONSULTORA,
				des_tele TELEFONO,
				des_movi MOVIL,
				cod_sap  CODIGOSAP,
				des_prod DESCPRODUCTO,
				val_codi_vent CUV,
				num_unid_dema_real UNIDEMANDA,
				num_unid_aten UNIATENDIDA,
				num_unid_falt UNIFALTANTE,
				COD_CLIE_ANTE,
				NVL(COD_TIPO_CARG, codTipoCargo) COD_TIPO_CARG,
                COD_SECC,
                COD_TERR,
                NUM_UNID_POR_ATEN,
                VAL_PREC_UNIT,
                VAL_PORC_DESC,
                VAL_NUME_SOLI,
                VAL_ESTA
        FROM MAV_REPOR_CAMPA_CONSU_DETAL
        ORDER BY TO_DATE(fec_fact,'dd/MM/yyyy'), REGION, ZONA;

TYPE interfazTipo IS RECORD (

 v_cam_para_mav      MAV_REPOR_CAMPA_CONSU_DETAL.CAM_PARA_MAV%TYPE,
 v_fec_fact				   MAV_REPOR_CAMPA_CONSU_DETAL.fec_fact%TYPE,
 v_cod_regi				   MAV_REPOR_CAMPA_CONSU_DETAL.cod_regi%TYPE,
 v_cod_zona				   MAV_REPOR_CAMPA_CONSU_DETAL.cod_zona%TYPE,
 v_cod_acti          MAV_REPOR_CAMPA_CONSU_DETAL.cod_acti%TYPE,
 v_des_acti          MAV_REPOR_CAMPA_CONSU_DETAL.des_acti%TYPE,
 v_cod_tipo_ofer     MAV_REPOR_CAMPA_CONSU_DETAL.cod_tipo_ofer%TYPE,
 v_des_tipo_ofer     MAV_REPOR_CAMPA_CONSU_DETAL.des_tipo_ofer%TYPE,
 v_cod_clie          MAV_REPOR_CAMPA_CONSU_DETAL.cod_clie%TYPE,
 v_des_clie          MAV_REPOR_CAMPA_CONSU_DETAL.des_clie%TYPE,
 v_des_tele          MAV_REPOR_CAMPA_CONSU_DETAL.des_tele%TYPE,
 v_des_movi          MAV_REPOR_CAMPA_CONSU_DETAL.des_movi%TYPE,
 v_cod_sap           MAV_REPOR_CAMPA_CONSU_DETAL.cod_sap%TYPE,
 v_des_prod          MAV_REPOR_CAMPA_CONSU_DETAL.des_prod%TYPE,
 v_val_codi_vent     MAV_REPOR_CAMPA_CONSU_DETAL.val_codi_vent%TYPE,
 v_num_unid_dema_real NUMBER(20),
 v_num_unid_aten      NUMBER(20),
 v_num_unid_falt      NUMBER(20),
 v_cod_clie_ante     MAV_REPOR_CAMPA_CONSU_DETAL.COD_CLIE_ANTE%TYPE,
 v_cod_tipo_carg     MAV_REPOR_CAMPA_CONSU_DETAL.COD_TIPO_CARG%TYPE,
 COD_SECC            MAV_REPOR_CAMPA_CONSU_DETAL.COD_SECC%type,
 COD_TERR            MAV_REPOR_CAMPA_CONSU_DETAL.COD_TERR%type,
 NUM_UNID_POR_ATEN   MAV_REPOR_CAMPA_CONSU_DETAL.NUM_UNID_POR_ATEN%type,
 VAL_PREC_UNIT       MAV_REPOR_CAMPA_CONSU_DETAL.VAL_PREC_UNIT%type,
 VAL_PORC_DESC       MAV_REPOR_CAMPA_CONSU_DETAL.VAL_PORC_DESC%type,
 VAL_NUME_SOLI       MAV_REPOR_CAMPA_CONSU_DETAL.VAL_NUME_SOLI%type,
 VAL_ESTA            MAV_REPOR_CAMPA_CONSU_DETAL.VAL_ESTA%type
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz(codigoTipoCargo);
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).v_cod_regi ||','||
                    interfazRecord(x).v_cod_zona ||','||
                    interfazRecord(x).COD_SECC ||','||
                    interfazRecord(x).COD_TERR ||','||
                    interfazRecord(x).v_des_acti ||','||
                    interfazRecord(x).v_des_tipo_ofer ||','||
                    interfazRecord(x).v_cod_tipo_carg ||','||
                    interfazRecord(x).v_fec_fact ||','||
                    '=T("'||interfazRecord(x).v_cod_clie||'")' ||','||
                    '=T("'||interfazRecord(x).v_cod_clie_ante||'")' ||','||
                    interfazRecord(x).v_des_clie ||','||
                    interfazRecord(x).v_des_tele ||','||
                    interfazRecord(x).v_des_movi ||','||
                    '=T("'||interfazRecord(x).v_cod_sap||'")' ||','||
                    interfazRecord(x).v_des_prod ||','||
                    '=T("'||interfazRecord(x).v_val_codi_vent||'")' ||','||
                    interfazRecord(x).v_num_unid_dema_real ||','||
                    interfazRecord(x).v_num_unid_aten ||','||
                    interfazRecord(x).NUM_UNID_POR_ATEN ||','||
                    interfazRecord(x).v_num_unid_falt ||','||
                    interfazRecord(x).VAL_PREC_UNIT ||','||
                    interfazRecord(x).VAL_PORC_DESC ||','||
                    interfazRecord(x).VAL_NUME_SOLI ||','||
                    interfazRecord(x).VAL_ESTA;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_DETAL_TIPO_CARGO_CSV: '||ls_sqlerrm);
END MAV_PR_DETAL_TIPO_CARGO_CSV;


/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion
                       Fecha Campanha Por Gerente Detalle Con Sin Fecha.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaXLS
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_DETAL_CONSI_FECHA_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS
  SELECT cam_para_mav CAMPANA,
				fec_fact FECHA_ENVIO,
				cod_regi REGION,
				cod_zona ZONA,
				cod_acti CODACTIVIDAD,
				des_acti ACTIVIDAD,
				cod_tipo_ofer CODTOFERTA,
				des_tipo_ofer TIPOOFERTA,
				cod_clie CODIGOCLIE,
				des_clie CONSULTORA,
				des_tele TELEFONO,
				des_movi MOVIL,
				cod_sap  CODIGOSAP,
				des_prod DESCPRODUCTO,
				val_codi_vent CUV,
				num_unid_dema_real UNIDEMANDA,
				num_unid_aten UNIATENDIDA,
				num_unid_falt UNIFALTANTE,
				COD_CLIE_ANTE,
                COD_SECC,
                COD_TERR,
                NUM_UNID_POR_ATEN,
                VAL_PREC_UNIT,
                VAL_PORC_DESC,
                VAL_NUME_SOLI,
                VAL_ESTA
 FROM MAV_REPOR_CAMPA_CONSU_DETAL
 ORDER BY TO_DATE(fec_fact,'dd/MM/yyyy'), REGION, ZONA;

TYPE interfazTipo IS RECORD (

 v_cam_para_mav      MAV_REPOR_CAMPA_CONSU_DETAL.CAM_PARA_MAV%TYPE,
 v_fec_fact				   MAV_REPOR_CAMPA_CONSU_DETAL.fec_fact%TYPE,
 v_cod_regi				   MAV_REPOR_CAMPA_CONSU_DETAL.cod_regi%TYPE,
 v_cod_zona				   MAV_REPOR_CAMPA_CONSU_DETAL.cod_zona%TYPE,
 v_cod_acti          MAV_REPOR_CAMPA_CONSU_DETAL.cod_acti%TYPE,
 v_des_acti          MAV_REPOR_CAMPA_CONSU_DETAL.des_acti%TYPE,
 v_cod_tipo_ofer     MAV_REPOR_CAMPA_CONSU_DETAL.cod_tipo_ofer%TYPE,
 v_des_tipo_ofer     MAV_REPOR_CAMPA_CONSU_DETAL.des_tipo_ofer%TYPE,
 v_cod_clie          MAV_REPOR_CAMPA_CONSU_DETAL.cod_clie%TYPE,
 v_des_clie          MAV_REPOR_CAMPA_CONSU_DETAL.des_clie%TYPE,
 v_des_tele          MAV_REPOR_CAMPA_CONSU_DETAL.des_tele%TYPE,
 v_des_movi          MAV_REPOR_CAMPA_CONSU_DETAL.des_movi%TYPE,
 v_cod_sap           MAV_REPOR_CAMPA_CONSU_DETAL.cod_sap%TYPE,
 v_des_prod          MAV_REPOR_CAMPA_CONSU_DETAL.des_prod%TYPE,
 v_val_codi_vent     MAV_REPOR_CAMPA_CONSU_DETAL.val_codi_vent%TYPE,
 v_num_unid_dema_real NUMBER(20),
 v_num_unid_aten      NUMBER(20),
 v_num_unid_falt      NUMBER(20),
 v_cod_clie_ante     MAV_REPOR_CAMPA_CONSU_DETAL.COD_CLIE_ANTE%TYPE,
 COD_SECC            MAV_REPOR_CAMPA_CONSU_DETAL.COD_SECC%type,
 COD_TERR            MAV_REPOR_CAMPA_CONSU_DETAL.COD_TERR%type,
 NUM_UNID_POR_ATEN   MAV_REPOR_CAMPA_CONSU_DETAL.NUM_UNID_POR_ATEN%type,
 VAL_PREC_UNIT       MAV_REPOR_CAMPA_CONSU_DETAL.VAL_PREC_UNIT%type,
 VAL_PORC_DESC       MAV_REPOR_CAMPA_CONSU_DETAL.VAL_PORC_DESC%type,
 VAL_NUME_SOLI       MAV_REPOR_CAMPA_CONSU_DETAL.VAL_NUME_SOLI%type,
 VAL_ESTA            MAV_REPOR_CAMPA_CONSU_DETAL.VAL_ESTA%type
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

        lsLinea :=  interfazRecord(x).v_cod_regi ||','||
                    interfazRecord(x).v_cod_zona ||','||
                    interfazRecord(x).COD_SECC ||','||
                    interfazRecord(x).COD_TERR ||','||
                    interfazRecord(x).v_des_acti ||','||
                    interfazRecord(x).v_des_tipo_ofer ||','||
                    interfazRecord(x).v_fec_fact ||','||
                    '=T("'||interfazRecord(x).v_cod_clie||'")' ||','||
                    '=T("'||interfazRecord(x).v_cod_clie_ante||'")' ||','||
                    interfazRecord(x).v_des_clie ||','||
                    interfazRecord(x).v_des_tele ||','||
                    interfazRecord(x).v_des_movi ||','||
                    '=T("'||interfazRecord(x).v_cod_sap||'")' ||','||
                    interfazRecord(x).v_des_prod ||','||
                    '=T("'||interfazRecord(x).v_val_codi_vent||'")' ||','||
                    interfazRecord(x).v_num_unid_dema_real ||','||
                    interfazRecord(x).v_num_unid_aten ||','||
                    interfazRecord(x).NUM_UNID_POR_ATEN ||','||
                    interfazRecord(x).v_num_unid_falt ||','||
                    interfazRecord(x).VAL_PREC_UNIT ||','||
                    interfazRecord(x).VAL_PORC_DESC ||','||
                    interfazRecord(x).VAL_NUME_SOLI ||','||
                    interfazRecord(x).VAL_ESTA;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_DETAL_CONSI_FECHA_CSV: '||ls_sqlerrm);
END MAV_PR_DETAL_CONSI_FECHA_CSV;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion
                       Fecha Campanha Por Gerente Sin Fecha Region.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegion
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_SINFE_REGIO_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS
  SELECT  cam_para_mav CAMPANA,
				COD_REGI,
				fec_fact FECHA_FACTURACION,
				cod_acti CODACTIVIDAD,
				des_acti ACTIVIDAD,
				cod_tipo_ofer CODTOFERTA,
				des_tipo_ofer TIPOOFERTA,
				cod_sap CODIGOSAP,
				des_prod DESCPRODUCTO,
				val_codi_vent CUV,
				SUM(num_unid_dema_real) UNIDEMANDA,
				SUM(num_unid_aten) UNIATENDIDA,
				SUM(num_unid_falt) UNIFALTANTE
     FROM MAV_REPOR_CAMPA_CONSU
      GROUP BY
	       cam_para_mav,
	       COD_REGI,
	       fec_fact,
	       cod_acti,
	       des_acti,
	       cod_tipo_ofer,
	       des_tipo_ofer,
	       cod_sap,
	       des_prod,
	       val_codi_vent;

TYPE interfazTipo IS RECORD (

 v_cam_para_mav      MAV_REPOR_CAMPA_CONSU.CAM_PARA_MAV%TYPE,
 v_cod_regi				   MAV_REPOR_CAMPA_CONSU.COD_REGI%TYPE,
 v_fec_fact          MAV_REPOR_CAMPA_CONSU.FEC_FACT%TYPE,
 v_cod_acti          MAV_REPOR_CAMPA_CONSU.COD_ACTI%TYPE,
 v_des_acti          MAV_REPOR_CAMPA_CONSU.DES_ACTI%TYPE,
 v_cod_tipo_ofer     MAV_REPOR_CAMPA_CONSU.COD_TIPO_OFER%TYPE,
 v_des_tipo_ofer     MAV_REPOR_CAMPA_CONSU.DES_TIPO_OFER%TYPE,
 v_cod_sap           MAV_REPOR_CAMPA_CONSU.COD_SAP%TYPE,
 v_des_prod          MAV_REPOR_CAMPA_CONSU.DES_PROD%TYPE,
 v_val_codi_vent     MAV_REPOR_CAMPA_CONSU.VAL_CODI_VENT%TYPE,
 v_num_unid_dema_real NUMBER(20),
 v_num_unid_aten  NUMBER(20),
 v_num_unid_falt  NUMBER(20)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).v_cod_regi ||','||
                    interfazRecord(x).v_des_acti ||','||
                    interfazRecord(x).v_des_tipo_ofer ||','||
                    '=T("'||interfazRecord(x).v_cod_sap||'")' ||','||
                    interfazRecord(x).v_des_prod ||','||
                    '=T("'||interfazRecord(x).v_val_codi_vent||'")' ||','||
                    interfazRecord(x).v_num_unid_dema_real ||','||
                    interfazRecord(x).v_num_unid_aten ||','||
                    interfazRecord(x).v_num_unid_falt;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_GEREN_SINFE_REGIO_CSV: '||ls_sqlerrm);
END MAV_PR_GEREN_SINFE_REGIO_CSV;


/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion
                       Fecha Campanha Por Gerente Sin Fecha Region Zona.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionZona
Fecha Creacion    : 23/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_SINFE_REGZO_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS
  SELECT  cam_para_mav CAMPANA,
				COD_REGI,
				COD_ZONA,
				fec_fact FECHA_FACTURACION,
				cod_acti CODACTIVIDAD,
				des_acti ACTIVIDAD,
				cod_tipo_ofer CODTOFERTA,
				des_tipo_ofer TIPOOFERTA,
				cod_sap CODIGOSAP,
				des_prod DESCPRODUCTO,
				val_codi_vent CUV,
				SUM(num_unid_dema_real) UNIDEMANDA,
				SUM(num_unid_aten) UNIATENDIDA,
				SUM(num_unid_falt) UNIFALTANTE
     FROM MAV_REPOR_CAMPA_CONSU
      GROUP BY
	       cam_para_mav,
	       COD_REGI,
	       COD_ZONA,
	       fec_fact,
	       cod_acti,
	       des_acti,
	       cod_tipo_ofer,
	       des_tipo_ofer,
	       cod_sap,
	       des_prod,
	       val_codi_vent;

TYPE interfazTipo IS RECORD (

 v_cam_para_mav      MAV_REPOR_CAMPA_CONSU.CAM_PARA_MAV%TYPE,
 v_cod_regi				   MAV_REPOR_CAMPA_CONSU.COD_REGI%TYPE,
 v_cod_zona			     MAV_REPOR_CAMPA_CONSU.Cod_Zona%TYPE,
 v_fec_fact          MAV_REPOR_CAMPA_CONSU.FEC_FACT%TYPE,
 v_cod_acti          MAV_REPOR_CAMPA_CONSU.COD_ACTI%TYPE,
 v_des_acti          MAV_REPOR_CAMPA_CONSU.DES_ACTI%TYPE,
 v_cod_tipo_ofer     MAV_REPOR_CAMPA_CONSU.COD_TIPO_OFER%TYPE,
 v_des_tipo_ofer     MAV_REPOR_CAMPA_CONSU.DES_TIPO_OFER%TYPE,
 v_cod_sap           MAV_REPOR_CAMPA_CONSU.COD_SAP%TYPE,
 v_des_prod          MAV_REPOR_CAMPA_CONSU.DES_PROD%TYPE,
 v_val_codi_vent     MAV_REPOR_CAMPA_CONSU.VAL_CODI_VENT%TYPE,
 v_num_unid_dema_real NUMBER(20),
 v_num_unid_aten  NUMBER(20),
 v_num_unid_falt  NUMBER(20)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).v_cod_regi ||','||
                     interfazRecord(x).v_cod_zona ||','||
                    interfazRecord(x).v_des_acti ||','||
                    interfazRecord(x).v_des_tipo_ofer ||','||
                    '=T("'||interfazRecord(x).v_cod_sap||'")' ||','||
                    interfazRecord(x).v_des_prod ||','||
                    '=T("'||interfazRecord(x).v_val_codi_vent||'")' ||','||
                    interfazRecord(x).v_num_unid_dema_real ||','||
                    interfazRecord(x).v_num_unid_aten ||','||
                    interfazRecord(x).v_num_unid_falt;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_GEREN_SINFE_REGZO_CSV: '||ls_sqlerrm);
END MAV_PR_GEREN_SINFE_REGZO_CSV;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Atencion Fecha
                   Campa?a Por Gerente Sin Fecha.
                   -  reporteMAVAtencionFechaCampanhaPorGerenteSinFecha
Fecha Creacion    : 24/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_GEREN_SINFE_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

  CURSOR c_interfaz IS
  SELECT  cam_para_mav CAMPANA,
				fec_fact FECHA_FACTURACION,
				cod_acti CODACTIVIDAD,
				des_acti ACTIVIDAD,
				cod_tipo_ofer CODTOFERTA,
				des_tipo_ofer TIPOOFERTA,
				cod_sap CODIGOSAP,
				des_prod DESCPRODUCTO,
				val_codi_vent CUV,
				SUM(num_unid_dema_real) UNIDEMANDA,
				SUM(num_unid_aten) UNIATENDIDA,
				SUM(num_unid_falt) UNIFALTANTE
     FROM MAV_REPOR_CAMPA_CONSU
      GROUP BY
	       cam_para_mav,
	       fec_fact,
	       cod_acti,
	       des_acti,
	       cod_tipo_ofer,
	       des_tipo_ofer,
	       cod_sap,
	       des_prod,
	       val_codi_vent;

TYPE interfazTipo IS RECORD (

 v_cam_para_mav      MAV_REPOR_CAMPA_CONSU.CAM_PARA_MAV%TYPE,
 v_fec_fact          MAV_REPOR_CAMPA_CONSU.FEC_FACT%TYPE,
 v_cod_acti          MAV_REPOR_CAMPA_CONSU.COD_ACTI%TYPE,
 v_des_acti          MAV_REPOR_CAMPA_CONSU.DES_ACTI%TYPE,
 v_cod_tipo_ofer     MAV_REPOR_CAMPA_CONSU.COD_TIPO_OFER%TYPE,
 v_des_tipo_ofer     MAV_REPOR_CAMPA_CONSU.DES_TIPO_OFER%TYPE,
 v_cod_sap           MAV_REPOR_CAMPA_CONSU.COD_SAP%TYPE,
 v_des_prod          MAV_REPOR_CAMPA_CONSU.DES_PROD%TYPE,
 v_val_codi_vent     MAV_REPOR_CAMPA_CONSU.VAL_CODI_VENT%TYPE,
 v_num_unid_dema_real NUMBER(20),
 v_num_unid_aten  NUMBER(20),
 v_num_unid_falt  NUMBER(20)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).v_des_acti ||','||
                    interfazRecord(x).v_des_tipo_ofer ||','||
                    '=T("'||interfazRecord(x).v_cod_sap||'")' ||','||
                    interfazRecord(x).v_des_prod ||','||
                    '=T("'||interfazRecord(x).v_val_codi_vent||'")' ||','||
                    interfazRecord(x).v_num_unid_dema_real ||','||
                    interfazRecord(x).v_num_unid_aten ||','||
                    interfazRecord(x).v_num_unid_falt;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_GEREN_SINFE_CSV: '||ls_sqlerrm);
END MAV_PR_GEREN_SINFE_CSV;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte MAV Envios Fecha Campanha
                       Con Sin Fecha.
                   -  reporteMAVEnviosFechaCampanhaConSinFecha
Fecha Creacion    : 24/09/2013
Autor             : Yahir Rivas L.
Parametros        :
            psCodigoPais : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE MAV_PR_ENVIO_CONSI_FECHA_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);

   CURSOR c_interfaz IS
   SELECT
                COR_PARA_CONF,
                IND_ENVI,
                CAM_PARA_MAV,
                FEC_FACT,
                COD_TIPO_CARG,
                COD_REGI,
                COD_ZONA,
                COD_ACTI,
                DES_ACTI,
                COD_TIPO_OFER,
                DES_TIPO_OFER,
                COD_CLIE,
                DES_CLIE,
                DES_TELE,
                DES_MOVI,
                COD_SAP,
                DES_PROD,
                VAL_CODI_VENT,
                NUM_UNID_DEMA_REAL
	FROM MAV_REPOR_CAMPA_CONSU_DETAL
    ORDER BY COR_PARA_CONF, COD_REGI, COD_ZONA;

TYPE interfazTipo IS RECORD (

 v_cor_para_conf     MAV_REPOR_CAMPA_CONSU_DETAL.COR_PARA_CONF%TYPE,
 v_ind_envi          MAV_REPOR_CAMPA_CONSU_DETAL.IND_ENVI%TYPE,
 v_cam_para_mav      MAV_REPOR_CAMPA_CONSU_DETAL.CAM_PARA_MAV%TYPE,
 v_fec_fact				   MAV_REPOR_CAMPA_CONSU_DETAL.FEC_FACT%TYPE,
 v_cod_tipo_carg     MAV_REPOR_CAMPA_CONSU_DETAL.COD_TIPO_CARG%TYPE,
 v_cod_regi				   MAV_REPOR_CAMPA_CONSU_DETAL.cod_regi%TYPE,
 v_cod_zona				   MAV_REPOR_CAMPA_CONSU_DETAL.cod_zona%TYPE,
 v_cod_acti          MAV_REPOR_CAMPA_CONSU_DETAL.cod_acti%TYPE,
 v_des_acti          MAV_REPOR_CAMPA_CONSU_DETAL.des_acti%TYPE,
 v_cod_tipo_ofer     MAV_REPOR_CAMPA_CONSU_DETAL.cod_tipo_ofer%TYPE,
 v_des_tipo_ofer     MAV_REPOR_CAMPA_CONSU_DETAL.des_tipo_ofer%TYPE,
 v_cod_clie          MAV_REPOR_CAMPA_CONSU_DETAL.cod_clie%TYPE,
 v_des_clie          MAV_REPOR_CAMPA_CONSU_DETAL.des_clie%TYPE,
 v_des_tele          MAV_REPOR_CAMPA_CONSU_DETAL.des_tele%TYPE,
 v_des_movi          MAV_REPOR_CAMPA_CONSU_DETAL.des_movi%TYPE,
 v_cod_sap           MAV_REPOR_CAMPA_CONSU_DETAL.cod_sap%TYPE,
 v_des_prod          MAV_REPOR_CAMPA_CONSU_DETAL.des_prod%TYPE,
 v_val_codi_vent     MAV_REPOR_CAMPA_CONSU_DETAL.val_codi_vent%TYPE,
 v_num_unid_dema_real NUMBER(20)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN
  lbAbrirUtlFile := TRUE;

  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := interfazRecord(x).v_cor_para_conf ||','||
                    interfazRecord(x).v_ind_envi ||','||
                    interfazRecord(x).v_cam_para_mav ||','||
                    interfazRecord(x).v_fec_fact ||','||
                    '=T("'||interfazRecord(x).v_cod_tipo_carg||'")' ||','||
                    '=T("'||interfazRecord(x).v_cod_regi||'")' ||','||
                    '=T("'||interfazRecord(x).v_cod_zona||'")' ||','||
                    '=T("'||interfazRecord(x).v_cod_acti||'")' ||','||
                    interfazRecord(x).v_des_acti ||','||
                    '=T("'||interfazRecord(x).v_cod_tipo_ofer||'")' ||','||
                    interfazRecord(x).v_des_tipo_ofer ||','||
                    '=T("'||interfazRecord(x).v_cod_clie||'")' ||','||
                    interfazRecord(x).v_des_clie ||','||
                    interfazRecord(x).v_des_tele ||','||
                    interfazRecord(x).v_des_movi ||','||
                    '=T("'||interfazRecord(x).v_cod_sap||'")' ||','||
                    interfazRecord(x).v_des_prod ||','||
                    '=T("'||interfazRecord(x).v_val_codi_vent||'")' ||','||
                    interfazRecord(x).v_num_unid_dema_real;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_ENVIO_CONSI_FECHA_CSV: '||ls_sqlerrm);
END MAV_PR_ENVIO_CONSI_FECHA_CSV;

/***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegion
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mav_pr_consu_confe_regio_csv
  (
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR c_consu_confe_regio IS
    SELECT  cam_para_mav,
                cod_regi,
                fec_fact,
                cod_acti,
                des_acti,
                cod_tipo_ofer,
                des_tipo_ofer,
                cod_sap,
                des_prod,
                val_codi_vent,
                SUM(num_unid_dema_real) num_unid_dema_real,
                SUM(num_unid_aten) num_unid_aten,
                SUM(num_unid_falt) num_unid_falt
     FROM mav_repor_campa_consu
      GROUP BY
           cam_para_mav,
           cod_regi,
           fec_fact,
           cod_acti,
           des_acti,
           cod_tipo_ofer,
           des_tipo_ofer,
           cod_sap,
           des_prod,
           val_codi_vent;

        TYPE consuconferegiorec IS RECORD(
            CAM_PARA_MAV              MAV_REPOR_CAMPA_CONSU.CAM_PARA_MAV%type,
            COD_REGI                        MAV_REPOR_CAMPA_CONSU.COD_REGI%type,
            FEC_FACT                        MAV_REPOR_CAMPA_CONSU.FEC_FACT%type,
            COD_ACTI                       MAV_REPOR_CAMPA_CONSU.COD_ACTI%type,
            DES_ACTI                        MAV_REPOR_CAMPA_CONSU.DES_ACTI%type,
            COD_TIPO_OFER            MAV_REPOR_CAMPA_CONSU.COD_TIPO_OFER%type,
            DES_TIPO_OFER             MAV_REPOR_CAMPA_CONSU.DES_TIPO_OFER%type,
            COD_SAP                         MAV_REPOR_CAMPA_CONSU.COD_SAP%type,
            DES_PROD                        MAV_REPOR_CAMPA_CONSU.DES_PROD%type,
            VAL_CODI_VENT               MAV_REPOR_CAMPA_CONSU.VAL_CODI_VENT%type,
            NUM_UNID_DEMA_REAL   MAV_REPOR_CAMPA_CONSU.NUM_UNID_DEMA_REAL%type,
            NUM_UNID_ATEN              MAV_REPOR_CAMPA_CONSU.NUM_UNID_ATEN%type,
            NUM_UNID_FALT               MAV_REPOR_CAMPA_CONSU.NUM_UNID_FALT%type
        );

        TYPE consuconferegiotab IS TABLE OF consuconferegiorec;
        consuconferegiorecord consuconferegiotab;

   lbAbrirUtlFile  BOOLEAN;
  BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_consu_confe_regio;
      LOOP
       FETCH c_consu_confe_regio BULK COLLECT INTO consuconferegiorecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF consuconferegiorecord.COUNT > 0 THEN
          FOR x IN consuconferegiorecord.FIRST .. consuconferegiorecord.LAST LOOP
                lslinea :=  '=T("'|| consuconferegiorecord(x).cod_regi || '")' || ',' ||
                            '=T("'|| consuconferegiorecord(x).fec_fact || '")' || ',' ||
                               '"'|| consuconferegiorecord(x).des_acti || '"' || ',' ||
                               '"'|| consuconferegiorecord(x).des_tipo_ofer || '"' || ',' ||
                            '=T("'|| consuconferegiorecord(x).cod_sap || '")' || ',' ||
                               '"'|| replace(consuconferegiorecord(x).des_prod,',',' ') || '"' || ',' ||
                            '=T("'|| consuconferegiorecord(x).val_codi_vent || '")' || ',' ||
                               '"'|| consuconferegiorecord(x).num_unid_dema_real || '"' || ',' ||
                               '"'|| consuconferegiorecord(x).num_unid_aten || '"' || ',' ||
                               '"'|| consuconferegiorecord(x).num_unid_falt || '"';

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
        END IF;
        EXIT WHEN c_consu_confe_regio%NOTFOUND;
     END LOOP;
    CLOSE c_consu_confe_regio;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'MAV_PR_CONSU_CONFE_REGIO_CSV' || ls_sqlerrm);
  END mav_pr_consu_confe_regio_csv;

  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionZona
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mav_pr_consu_confe_regzo_csv
  (
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR c_consu_confe_regzo IS
    SELECT  cam_para_mav,
        cod_regi,
        cod_zona,
        fec_fact,
        cod_acti,
        des_acti,
        cod_tipo_ofer,
        des_tipo_ofer,
        cod_sap,
        des_prod,
        val_codi_vent,
        SUM(num_unid_dema_real) num_unid_dema_real,
        SUM(num_unid_aten) num_unid_aten,
        SUM(num_unid_falt) num_unid_falt
     FROM mav_repor_campa_consu
      GROUP BY
         cam_para_mav,
         cod_regi,
         cod_zona,
         fec_fact,
         cod_acti,
         des_acti,
         cod_tipo_ofer,
         des_tipo_ofer,
         cod_sap,
         des_prod,
         val_codi_vent;

        TYPE consuconferegzorec IS RECORD(
            CAM_PARA_MAV        MAV_REPOR_CAMPA_CONSU.CAM_PARA_MAV%TYPE,
            COD_REGI            MAV_REPOR_CAMPA_CONSU.COD_REGI%TYPE,
            COD_ZONA            MAV_REPOR_CAMPA_CONSU.COD_ZONA%TYPE,
            FEC_FACT            MAV_REPOR_CAMPA_CONSU.FEC_FACT%TYPE,
            COD_ACTI            MAV_REPOR_CAMPA_CONSU.COD_ACTI%TYPE,
            DES_ACTI            MAV_REPOR_CAMPA_CONSU.DES_ACTI%TYPE,
            COD_TIPO_OFER       MAV_REPOR_CAMPA_CONSU.COD_TIPO_OFER%TYPE,
            DES_TIPO_OFER       MAV_REPOR_CAMPA_CONSU.DES_TIPO_OFER%TYPE,
            COD_SAP             MAV_REPOR_CAMPA_CONSU.COD_SAP%TYPE,
            DES_PROD            MAV_REPOR_CAMPA_CONSU.DES_PROD%TYPE,
            VAL_CODI_VENT       MAV_REPOR_CAMPA_CONSU.VAL_CODI_VENT%TYPE,
            NUM_UNID_DEMA_REAL  MAV_REPOR_CAMPA_CONSU.NUM_UNID_DEMA_REAL%TYPE,
            NUM_UNID_ATEN       MAV_REPOR_CAMPA_CONSU.NUM_UNID_ATEN%TYPE,
            NUM_UNID_FALT       MAV_REPOR_CAMPA_CONSU.NUM_UNID_FALT%TYPE
        );

        TYPE consuconferegzotab IS TABLE OF consuconferegzorec;
        consuconferegzorecord consuconferegzotab;

   lbAbrirUtlFile  BOOLEAN;
  BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_consu_confe_regzo;
      LOOP
       FETCH c_consu_confe_regzo BULK COLLECT INTO consuconferegzorecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF consuconferegzorecord.COUNT > 0 THEN
          FOR x IN consuconferegzorecord.FIRST .. consuconferegzorecord.LAST LOOP
                lslinea :=  '=T("'|| consuconferegzorecord(x).cod_regi || '")' || ',' ||
                            '=T("'|| consuconferegzorecord(x).cod_zona || '")' || ',' ||
                            '=T("'|| consuconferegzorecord(x).fec_fact || '")' || ',' ||
                               '"'|| consuconferegzorecord(x).des_acti || '"' || ',' ||
                               '"'|| consuconferegzorecord(x).des_tipo_ofer || '"' || ',' ||
                            '=T("'|| consuconferegzorecord(x).cod_sap || '")' || ',' ||
                               '"'|| replace(consuconferegzorecord(x).des_prod,',',' ') || '"' || ',' ||
                            '=T("'|| consuconferegzorecord(x).val_codi_vent || '")' || ',' ||
                               '"'|| consuconferegzorecord(x).num_unid_dema_real || '"' || ',' ||
                               '"'|| consuconferegzorecord(x).num_unid_aten || '"' || ',' ||
                               '"'|| consuconferegzorecord(x).num_unid_falt || '"';

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
        END IF;
        EXIT WHEN c_consu_confe_regzo%NOTFOUND;
     END LOOP;
    CLOSE c_consu_confe_regzo;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'MAV_PR_CONSU_CONFE_REGZO_CSV' || ls_sqlerrm);
  END mav_pr_consu_confe_regzo_csv;


  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraConFecha
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mav_pr_consu_confe_csv
  (
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR c_consu_confe IS
    SELECT  cam_para_mav,
				fec_fact,
				cod_acti,
				des_acti,
                cod_tipo_ofer,
                des_tipo_ofer,
                cod_sap,
                des_prod,
                val_codi_vent,
                SUM(num_unid_dema_real) num_unid_dema_real,
                SUM(num_unid_aten) num_unid_aten,
                SUM(num_unid_falt) num_unid_falt
     FROM mav_repor_campa_consu
      GROUP BY
           cam_para_mav,
           fec_fact,
           cod_acti,
           des_acti,
           cod_tipo_ofer,
           des_tipo_ofer,
           cod_sap,
           des_prod,
           val_codi_vent;

        TYPE consuconferec IS RECORD(
            CAM_PARA_MAV            MAV_REPOR_CAMPA_CONSU.CAM_PARA_MAV%TYPE,
            FEC_FACT                MAV_REPOR_CAMPA_CONSU.FEC_FACT%TYPE,
            COD_ACTI                MAV_REPOR_CAMPA_CONSU.COD_ACTI%TYPE,
            DES_ACTI                MAV_REPOR_CAMPA_CONSU.DES_ACTI%TYPE,
            COD_TIPO_OFER           MAV_REPOR_CAMPA_CONSU.COD_TIPO_OFER%TYPE,
            DES_TIPO_OFER           MAV_REPOR_CAMPA_CONSU.DES_TIPO_OFER%TYPE,
            COD_SAP                 MAV_REPOR_CAMPA_CONSU.COD_SAP%TYPE,
            DES_PROD                MAV_REPOR_CAMPA_CONSU.DES_PROD%TYPE,
            VAL_CODI_VENT           MAV_REPOR_CAMPA_CONSU.VAL_CODI_VENT%TYPE,
            NUM_UNID_DEMA_REAL      MAV_REPOR_CAMPA_CONSU.NUM_UNID_DEMA_REAL%TYPE,
            NUM_UNID_ATEN           MAV_REPOR_CAMPA_CONSU.NUM_UNID_ATEN%TYPE,
            NUM_UNID_FALT           MAV_REPOR_CAMPA_CONSU.NUM_UNID_FALT%TYPE
        );

        TYPE consuconfetab IS TABLE OF consuconferec;
        consuconferecord consuconfetab;

   lbAbrirUtlFile  BOOLEAN;
  BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_consu_confe;
      LOOP
       FETCH c_consu_confe BULK COLLECT INTO consuconferecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF consuconferecord.COUNT > 0 THEN
          FOR x IN consuconferecord.FIRST .. consuconferecord.LAST LOOP
                lslinea :=  '=T("'|| consuconferecord(x).fec_fact || '")' || ',' ||
                               '"'|| consuconferecord(x).des_acti || '"' || ',' ||
                               '"'|| consuconferecord(x).des_tipo_ofer || '"' || ',' ||
                            '=T("'|| consuconferecord(x).cod_sap || '")' || ',' ||
                               '"'|| replace(consuconferecord(x).des_prod,',',' ') || '"' || ',' ||
                            '=T("'|| consuconferecord(x).val_codi_vent || '")' || ',' ||
                               '"'|| consuconferecord(x).num_unid_dema_real || '"' || ',' ||
                               '"'|| consuconferecord(x).num_unid_aten || '"' || ',' ||
                               '"'|| consuconferecord(x).num_unid_falt || '"';

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
        END IF;
        EXIT WHEN c_consu_confe%NOTFOUND;
     END LOOP;
    CLOSE c_consu_confe;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'MAV_PR_CONSU_CONFE_CSV' || ls_sqlerrm);
  END mav_pr_consu_confe_csv;


  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraDetalleConSinFecha
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mav_pr_consu_detal_consi_csv
  (
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR c_consu_detal_consi IS
    SELECT cam_para_mav,
				fec_fact,
                cod_regi,
                cod_zona,
                cod_secc,
                cod_terr,
                cod_acti,
                des_acti,
                cod_tipo_ofer,
                des_tipo_ofer,
                cod_clie,
                des_clie,
                des_tele,
                des_movi,
                cod_sap,
                des_prod,
                val_codi_vent,
                num_unid_dema_real,
                num_unid_aten,
                num_unid_por_aten,
                num_unid_falt,
                val_prec_unit,
                val_porc_desc,
                val_nume_soli,
                val_esta,
                cod_clie_ante
    FROM mav_repor_campa_consu_detal ;

        TYPE consudetalconsirec IS RECORD(
            CAM_PARA_MAV            MAV_REPOR_CAMPA_CONSU_DETAL.CAM_PARA_MAV%TYPE,
            FEC_FACT                MAV_REPOR_CAMPA_CONSU_DETAL.FEC_FACT%TYPE,
            COD_REGI                MAV_REPOR_CAMPA_CONSU_DETAL.COD_REGI%TYPE,
            COD_ZONA                MAV_REPOR_CAMPA_CONSU_DETAL.COD_ZONA%TYPE,
            cod_secc                MAV_REPOR_CAMPA_CONSU_DETAL.COD_SECC%TYPE,
            cod_terr                MAV_REPOR_CAMPA_CONSU_DETAL.COD_TERR%TYPE,
            COD_ACTI                MAV_REPOR_CAMPA_CONSU_DETAL.COD_ACTI%TYPE,
            DES_ACTI                MAV_REPOR_CAMPA_CONSU_DETAL.DES_ACTI%TYPE,
            COD_TIPO_OFER           MAV_REPOR_CAMPA_CONSU_DETAL.COD_TIPO_OFER%TYPE,
            DES_TIPO_OFER           MAV_REPOR_CAMPA_CONSU_DETAL.DES_TIPO_OFER%TYPE,
            COD_CLIE                MAV_REPOR_CAMPA_CONSU_DETAL.COD_CLIE%TYPE,
            DES_CLIE                MAV_REPOR_CAMPA_CONSU_DETAL.DES_CLIE%TYPE,
            DES_TELE                MAV_REPOR_CAMPA_CONSU_DETAL.DES_TELE%TYPE,
            DES_MOVI                MAV_REPOR_CAMPA_CONSU_DETAL.DES_MOVI%TYPE,
            COD_SAP                 MAV_REPOR_CAMPA_CONSU_DETAL.COD_SAP%TYPE,
            DES_PROD                MAV_REPOR_CAMPA_CONSU_DETAL.DES_PROD%TYPE,
            VAL_CODI_VENT           MAV_REPOR_CAMPA_CONSU_DETAL.VAL_CODI_VENT%TYPE,
            NUM_UNID_DEMA_REAL      MAV_REPOR_CAMPA_CONSU_DETAL.NUM_UNID_DEMA_REAL%TYPE,
            NUM_UNID_ATEN           MAV_REPOR_CAMPA_CONSU_DETAL.NUM_UNID_ATEN%TYPE,
            num_unid_por_aten       MAV_REPOR_CAMPA_CONSU_DETAL.NUM_UNID_POR_ATEN%TYPE,
            NUM_UNID_FALT           MAV_REPOR_CAMPA_CONSU_DETAL.NUM_UNID_FALT%TYPE,
            val_prec_unit           MAV_REPOR_CAMPA_CONSU_DETAL.VAL_PREC_UNIT%TYPE,
            val_porc_desc           MAV_REPOR_CAMPA_CONSU_DETAL.VAL_PORC_DESC%TYPE,
            val_nume_soli           MAV_REPOR_CAMPA_CONSU_DETAL.VAL_NUME_SOLI%TYPE,
            val_esta                MAV_REPOR_CAMPA_CONSU_DETAL.VAL_ESTA%TYPE,
            COD_CLIE_ANTE           MAV_REPOR_CAMPA_CONSU_DETAL.COD_CLIE_ANTE%TYPE
        );

        TYPE consudetalconsitab IS TABLE OF consudetalconsirec;
        consudetalconsirecord consudetalconsitab;

   lbAbrirUtlFile  BOOLEAN;
  BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_consu_detal_consi;
      LOOP
       FETCH c_consu_detal_consi BULK COLLECT INTO consudetalconsirecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF consudetalconsirecord.COUNT > 0 THEN
          FOR x IN consudetalconsirecord.FIRST .. consudetalconsirecord.LAST LOOP
                lslinea :=  '=T("'|| consudetalconsirecord(x).cod_regi || '")' || ',' ||
                            '=T("'|| consudetalconsirecord(x).cod_zona || '")' || ',' ||
                            '=T("'|| consudetalconsirecord(x).cod_secc || '")' || ',' ||
                            '=T("'|| consudetalconsirecord(x).cod_terr || '")' || ',' ||                                                        
                            '=T("'|| consudetalconsirecord(x).fec_fact || '")' || ',' ||
                            '=T("'|| consudetalconsirecord(x).des_acti || '")' || ',' ||
                            '=T("'|| consudetalconsirecord(x).DES_TIPO_OFER || '")' || ',' ||
                            '=T("'|| consudetalconsirecord(x).cod_clie || '")' || ',' ||
                            '=T("'|| consudetalconsirecord(x).cod_clie_ante || '")' || ',' ||
                               '"'|| consudetalconsirecord(x).des_clie || '"' || ',' ||
                               '"'|| consudetalconsirecord(x).des_tele || '"' || ',' ||
                               '"'|| consudetalconsirecord(x).des_movi || '"' || ',' ||
                            '=T("'|| consudetalconsirecord(x).cod_sap || '")' || ',' ||
                               '"'|| replace(consudetalconsirecord(x).des_prod,',',' ') || '"' || ',' ||
                            '=T("'|| consudetalconsirecord(x).val_codi_vent || '")' || ',' ||
                               '"'|| consudetalconsirecord(x).num_unid_dema_real || '"' || ',' ||
                               '"'|| consudetalconsirecord(x).num_unid_aten || '"' || ',' ||
                               '"'|| consudetalconsirecord(x).num_unid_por_aten || '"' || ',' ||                               
                               '"'|| consudetalconsirecord(x).num_unid_falt || '"' || ',' ||
                               '"'|| consudetalconsirecord(x).val_prec_unit || '"' ||','||
                               '"'|| consudetalconsirecord(x).val_porc_desc || '"' ||','||
                            '=T("'|| consudetalconsirecord(x).val_nume_soli || '")' || ',' ||               
                            '=T("'|| consudetalconsirecord(x).val_esta || '")';
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
        END IF;
        EXIT WHEN c_consu_detal_consi%NOTFOUND;
     END LOOP;
    CLOSE c_consu_detal_consi;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'MAV_PR_CONSU_DETAL_CONSI_CSV' || ls_sqlerrm);
  END mav_pr_consu_detal_consi_csv;


  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegion
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mav_pr_consu_sinfe_regio_csv
  (
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%type;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR c_consu_sinfe_regio IS
    SELECT  cam_para_mav,
		cod_regi,
        fec_fact,
        cod_acti,
        des_acti,
        cod_tipo_ofer,
        des_tipo_ofer,
        cod_sap,
        des_prod,
        val_codi_vent,
        SUM(num_unid_dema_real) num_unid_dema_real,
        SUM(num_unid_aten) num_unid_aten,
        SUM(num_unid_falt) num_unid_falt
     FROM mav_repor_campa_consu
      GROUP BY
         cam_para_mav,
         cod_regi,
         fec_fact,
         cod_acti,
         des_acti,
         cod_tipo_ofer,
         des_tipo_ofer,
         cod_sap,
         des_prod,
         val_codi_vent;

        TYPE consusinferegiorec IS RECORD(
            cam_para_mav            mav_repor_campa_consu.cam_para_mav%type,
            cod_regi                mav_repor_campa_consu.cod_regi%type,
            fec_fact                mav_repor_campa_consu.fec_fact%type,
            cod_acti                mav_repor_campa_consu.cod_acti%type,
            des_acti                mav_repor_campa_consu.des_acti%type,
            cod_tipo_ofer           mav_repor_campa_consu.cod_tipo_ofer%type,
            des_tipo_ofer           mav_repor_campa_consu.des_tipo_ofer%type,
            cod_sap                 mav_repor_campa_consu.cod_sap%type,
            des_prod                mav_repor_campa_consu.des_prod%type,
            val_codi_vent           mav_repor_campa_consu.val_codi_vent%type,
            num_unid_dema_real      mav_repor_campa_consu.num_unid_dema_real%type,
            num_unid_aten           mav_repor_campa_consu.num_unid_aten%type,
            num_unid_falt           mav_repor_campa_consu.num_unid_falt%type
        );

        TYPE consusinferegiotab IS TABLE OF consusinferegiorec;
        consusinferegiorecord consusinferegiotab;

   lbAbrirUtlFile  BOOLEAN;
  BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_consu_sinfe_regio;
      LOOP
       FETCH c_consu_sinfe_regio BULK COLLECT INTO consusinferegiorecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF consusinferegiorecord.COUNT > 0 THEN
          FOR x IN consusinferegiorecord.FIRST .. consusinferegiorecord.LAST LOOP
                lslinea :=  '=T("'|| consusinferegiorecord(x).cod_regi || '")' || ',' ||
                               '"'|| consusinferegiorecord(x).des_acti || '"' || ',' ||
                               '"'|| consusinferegiorecord(x).des_tipo_ofer || '"' || ',' ||
                            '=T("'|| consusinferegiorecord(x).cod_sap || '")' || ',' ||
                               '"'|| replace(consusinferegiorecord(x).des_prod,',',' ') || '"' || ',' ||
                            '=T("'|| consusinferegiorecord(x).val_codi_vent || '")' || ',' ||
                               '"'|| consusinferegiorecord(x).num_unid_dema_real || '"' || ',' ||
                               '"'|| consusinferegiorecord(x).num_unid_aten || '"' || ',' ||
                               '"'|| consusinferegiorecord(x).num_unid_falt || '"';

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
        END IF;
        EXIT WHEN c_consu_sinfe_regio%NOTFOUND;
     END LOOP;
    CLOSE c_consu_sinfe_regio;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'MAV_PR_CONSU_SINFE_REGIO_CSV' || ls_sqlerrm);
  END mav_pr_consu_sinfe_regio_csv;


  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionZona
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mav_pr_consu_sinfe_regzo_csv
  (
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR c_consu_sinfe_regzo IS
    SELECT  cam_para_mav,
		cod_regi,
		cod_zona,
        fec_fact,
        cod_acti,
        des_acti,
        cod_tipo_ofer,
        des_tipo_ofer,
        cod_sap,
        des_prod,
        val_codi_vent,
        SUM(num_unid_dema_real) num_unid_dema_real,
        SUM(num_unid_aten) num_unid_aten,
        SUM(num_unid_falt) num_unid_falt
     FROM mav_repor_campa_consu
      GROUP BY
         cam_para_mav,
         COD_REGI,
         COD_ZONA,
         fec_fact,
         cod_acti,
         des_acti,
         cod_tipo_ofer,
         des_tipo_ofer,
         cod_sap,
         des_prod,
         val_codi_vent;

        TYPE consusinferegzorec IS RECORD(
            cam_para_mav            mav_repor_campa_consu.cam_para_mav%type,
            cod_regi                mav_repor_campa_consu.cod_regi%type,
            cod_zona                mav_repor_campa_consu.cod_zona%type,
            fec_fact                mav_repor_campa_consu.fec_fact%type,
            cod_acti                mav_repor_campa_consu.cod_acti%type,
            des_acti                mav_repor_campa_consu.des_acti%type,
            cod_tipo_ofer           mav_repor_campa_consu.cod_tipo_ofer%type,
            des_tipo_ofer           mav_repor_campa_consu.des_tipo_ofer%type,
            cod_sap                 mav_repor_campa_consu.cod_sap%type,
            des_prod                mav_repor_campa_consu.des_prod%type,
            val_codi_vent           mav_repor_campa_consu.val_codi_vent%type,
            num_unid_dema_real      mav_repor_campa_consu.num_unid_dema_real%type,
            num_unid_aten           mav_repor_campa_consu.num_unid_aten%type,
            num_unid_falt           mav_repor_campa_consu.num_unid_falt%type
        );

        TYPE consusinferegzotab IS TABLE OF consusinferegzorec;
        consusinferegzorecord consusinferegzotab;

   lbAbrirUtlFile  BOOLEAN;
  BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_consu_sinfe_regzo;
      LOOP
       FETCH c_consu_sinfe_regzo BULK COLLECT INTO consusinferegzorecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF consusinferegzorecord.COUNT > 0 THEN
          FOR x IN consusinferegzorecord.FIRST .. consusinferegzorecord.LAST LOOP
                lslinea :=  '=T("'|| consusinferegzorecord(x).cod_regi || '")' || ',' ||
                            '=T("'|| consusinferegzorecord(x).cod_zona || '")' || ',' ||
                               '"'|| consusinferegzorecord(x).des_acti || '"' || ',' ||
                               '"'|| consusinferegzorecord(x).des_tipo_ofer || '"' || ',' ||
                            '=T("'|| consusinferegzorecord(x).cod_sap || '")' || ',' ||
                               '"'|| replace(consusinferegzorecord(x).des_prod,',',' ') || '"' || ',' ||
                            '=T("'|| consusinferegzorecord(x).val_codi_vent || '")' || ',' ||
                               '"'|| consusinferegzorecord(x).num_unid_dema_real || '"' || ',' ||
                               '"'|| consusinferegzorecord(x).num_unid_aten || '"' || ',' ||
                               '"'|| consusinferegzorecord(x).num_unid_falt || '"';

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
        END IF;
        EXIT WHEN c_consu_sinfe_regzo%NOTFOUND;
     END LOOP;
    CLOSE c_consu_sinfe_regzo;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'MAV_PR_CONSU_SINFE_REGZO_CSV' || ls_sqlerrm);
  END mav_pr_consu_sinfe_regzo_csv;


  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorConsultoraSinFecha
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mav_pr_consu_sinfe_csv
  (
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR c_consu_sinfe IS
    SELECT  cam_para_mav,
        fec_fact,
        cod_acti,
        des_acti,
        cod_tipo_ofer,
        des_tipo_ofer,
        cod_sap,
        des_prod,
        val_codi_vent,
        SUM(num_unid_dema_real) num_unid_dema_real,
        SUM(num_unid_aten) num_unid_aten,
        SUM(num_unid_falt) num_unid_falt
     FROM mav_repor_campa_consu
      GROUP BY
         cam_para_mav,
         fec_fact,
         cod_acti,
         des_acti,
         cod_tipo_ofer,
         des_tipo_ofer,
         cod_sap,
         des_prod,
         val_codi_vent;

        TYPE consusinferec IS RECORD(
            cam_para_mav            mav_repor_campa_consu.cam_para_mav%type,
            fec_fact                mav_repor_campa_consu.fec_fact%type,
            cod_acti                mav_repor_campa_consu.cod_acti%type,
            des_acti                mav_repor_campa_consu.des_acti%type,
            cod_tipo_ofer           mav_repor_campa_consu.cod_tipo_ofer%type,
            des_tipo_ofer           mav_repor_campa_consu.des_tipo_ofer%type,
            cod_sap                 mav_repor_campa_consu.cod_sap%type,
            des_prod                mav_repor_campa_consu.des_prod%type,
            val_codi_vent           mav_repor_campa_consu.val_codi_vent%type,
            num_unid_dema_real      mav_repor_campa_consu.num_unid_dema_real%type,
            num_unid_aten           mav_repor_campa_consu.num_unid_aten%type,
            num_unid_falt           mav_repor_campa_consu.num_unid_falt%type
        );

        TYPE consusinfetab IS TABLE OF consusinferec;
        consusinferecord consusinfetab;

   lbAbrirUtlFile  BOOLEAN;
  BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_consu_sinfe;
      LOOP
       FETCH c_consu_sinfe BULK COLLECT INTO consusinferecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF consusinferecord.COUNT > 0 THEN
          FOR x IN consusinferecord.FIRST .. consusinferecord.LAST LOOP
                lslinea :=     '"'|| consusinferecord(x).des_acti || '"' || ',' ||
                               '"'|| consusinferecord(x).des_tipo_ofer || '"' || ',' ||
                            '=T("'|| consusinferecord(x).cod_sap || '")' || ',' ||
                               '"'|| replace(consusinferecord(x).des_prod,',',' ') || '"' || ',' ||
                            '=T("'|| consusinferecord(x).val_codi_vent || '")' || ',' ||
                               '"'|| consusinferecord(x).num_unid_dema_real || '"' || ',' ||
                               '"'|| consusinferecord(x).num_unid_aten || '"' || ',' ||
                               '"'|| consusinferecord(x).num_unid_falt || '"';

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
        END IF;
        EXIT WHEN c_consu_sinfe%NOTFOUND;
     END LOOP;
    CLOSE c_consu_sinfe;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'MAV_PR_CONSU_SINFE_CSV' || ls_sqlerrm);
  END mav_pr_consu_sinfe_csv;


  /***************************************************************************
  Descripcion       : Genera archivo CSV correspondiente al
                              reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegion
                              de Atenciones MAV
  Fecha Creacion : 23/09/2013
  Autor                 : Sebastian Guerra
  Parametros       :
    pscodigopais            codigo de pais
    psnombrearchivo     nombre de archivo
    pstitulo                    titulo del archivo
    psdirectorio            directorio donde se encuentra el archivo
  ***************************************************************************/
  PROCEDURE mav_pr_geren_confe_regio_csv
  (
    pscodigopais           VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pstitulo                    VARCHAR2,
    psdirectorio             OUT VARCHAR2
  ) IS

    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas           number := 5000;
    v_handle       utl_file.file_type;
    lslinea            varchar2(4000);

    CURSOR c_geren_confe_regio IS
    SELECT  cam_para_mav,
				cod_regi,
				fec_fact,
				cod_acti,
				des_acti,
				cod_tipo_ofer,
				des_tipo_ofer,
				cod_sap,
				des_prod,
				val_codi_vent,
				SUM(num_unid_dema_real) num_unid_dema_real,
				SUM(num_unid_aten) num_unid_aten,
				SUM(num_unid_falt) num_unid_falt
     FROM mav_repor_campa_consu
      GROUP BY
	       cam_para_mav,
	       cod_regi,
	       fec_fact,
	       cod_acti,
	       des_acti,
	       cod_tipo_ofer,
	       des_tipo_ofer,
	       cod_sap,
	       des_prod,
	       val_codi_vent;

        TYPE gerenconferegiorec IS RECORD(
            cam_para_mav            mav_repor_campa_consu.cam_para_mav%type,
            cod_regi                mav_repor_campa_consu.cod_regi%type,
            fec_fact                mav_repor_campa_consu.fec_fact%type,
            cod_acti                mav_repor_campa_consu.cod_acti%type,
            des_acti                mav_repor_campa_consu.des_acti%type,
            cod_tipo_ofer           mav_repor_campa_consu.cod_tipo_ofer%type,
            des_tipo_ofer           mav_repor_campa_consu.des_tipo_ofer%type,
            cod_sap                 mav_repor_campa_consu.cod_sap%type,
            des_prod                mav_repor_campa_consu.des_prod%type,
            val_codi_vent           mav_repor_campa_consu.val_codi_vent%type,
            num_unid_dema_real      mav_repor_campa_consu.num_unid_dema_real%type,
            num_unid_aten           mav_repor_campa_consu.num_unid_aten%type,
            num_unid_falt           mav_repor_campa_consu.num_unid_falt%type
        );

        TYPE gerenconferegiotab IS TABLE OF gerenconferegiorec;
        gerenconferegiorecord gerenconferegiotab;

   lbAbrirUtlFile  BOOLEAN;
  BEGIN

    lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN c_geren_confe_regio;
      LOOP
       FETCH c_geren_confe_regio BULK COLLECT INTO gerenconferegiorecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF gerenconferegiorecord.COUNT > 0 THEN
          FOR x IN gerenconferegiorecord.FIRST .. gerenconferegiorecord.LAST LOOP
                lslinea :=  '=T("'|| gerenconferegiorecord(x).cod_regi || '")' || ',' ||
                            '=T("'|| gerenconferegiorecord(x).fec_fact || '")' || ',' ||
                               '"'|| gerenconferegiorecord(x).des_acti || '"' || ',' ||
                               '"'|| gerenconferegiorecord(x).des_tipo_ofer || '"' || ',' ||
                            '=T("'|| gerenconferegiorecord(x).cod_sap || '")' || ',' ||
                               '"'|| replace(gerenconferegiorecord(x).des_prod,',',' ') || '"' || ',' ||
                            '=T("'|| gerenconferegiorecord(x).val_codi_vent || '")' || ',' ||
                               '"'|| gerenconferegiorecord(x).num_unid_dema_real || '"' || ',' ||
                               '"'|| gerenconferegiorecord(x).num_unid_aten || '"' || ',' ||
                               '"'|| gerenconferegiorecord(x).num_unid_falt || '"';

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
        END IF;
        EXIT WHEN c_geren_confe_regio%NOTFOUND;
     END LOOP;
    CLOSE c_geren_confe_regio;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           1000);
      raise_application_error(-20123,
                              'MAV_PR_GEREN_CONFE_REGIO_CSV' || ls_sqlerrm);
  END mav_pr_geren_confe_regio_csv;



END MAV_PKG_REPOR;
/
