CREATE OR REPLACE PACKAGE "GEN_PKG_INTER_ARCHI" IS

  /* Declaracion de Tipos */
  TYPE tipocursor IS REF CURSOR;
  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  w_filas    NUMBER := 1000;

  /* Declaracion de procedures */
  /***************************************************************************
  Descripcion       : Crea directorio para generar el Archivo a traves
                      del UTL_FILE en oracle
  Fecha Creacion    : 14/11/2007
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_crea_direc
  (
    psnombreobjetodir VARCHAR2,
    psnombredirec     VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Obtiene el directorio para generar el Archivo a traves
                      del UTL_FILE en oracle
  Fecha Creacion    : 14/11/2007
  Autor             : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_obtie_direc_proc
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    ps_codigointerfaz VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Obtiene el porcentaje de descuento
  Fecha Creacion    : 20/02/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_obtie_porc_dcto
  (
    psoidcliente           NUMBER,
    ps_porcentajedescuento VARCHAR2
  ) RETURN VARCHAR2;
  /***************************************************************************
  Descripcion       : Obtiene el porcentaje de descuento para una determinado periodo
  Fecha Creacion    : 20/02/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_obtie_porc_dcto_peri
  (
    ps_codigocliente NUMBER,
    ps_codigoperiodo NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Calcula el saldo deudor del cliente restando el valor en cupones
  Fecha Creacion    : 02/20/2007
  Parametros Entrada:
      ps_cod_clie   : Codigo de cliente
  Autor             : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_calcu_valor_sald_deudo2(ps_cod_clie IN VARCHAR2)
    RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Obtiene la cabecera a usar para generar el archivo a traves
                      del UTL_FILE en oracle
  Fecha Creacion    : 14/11/2007
  Autor             : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_obtie_cabec_archi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Comprime archivo usando Clase Java
  Fecha Creacion    : 14/11/2007
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_compr_zip
  (
    psdirectorio VARCHAR2,
    psfilaorigen VARCHAR2
  );
  /**************************************************************************
  Descripcion        : Genera el archivo para la interfaz COB Enviar datos
            para INFOCORP
  Fecha Creacion     : 09/11/2006
  Autor              : Carla Marius
  ***************************************************************************/
  PROCEDURE cob_pr_inter_datos_infoc
  (
    pscodigopais        VARCHAR2,
    pscodigosistema     VARCHAR2,
    pscodigointerfaz    VARCHAR2,
    psnombrearchivo     VARCHAR2,
    pscampanyainicial   VARCHAR2,
    psdiasvencimiento   VARCHAR2,
    psimporte           VARCHAR2,
    pstipomovimiento    VARCHAR2,
    pscodigoentidad     VARCHAR2,
    pstipodoctributario VARCHAR2,
    pstipopersona       VARCHAR2,
    pstipodeudor        VARCHAR2,
    pstipodocumento     VARCHAR2,
    pstipomoneda        VARCHAR2,
    pscondiciondeuda    VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Genera el archivo para la interfaz OCS Consolidado Detalle
  Fecha Creacion     : 27/11/2006
  Autor              : Marco Agurto
  ***************************************************************************/
  PROCEDURE ocr_pr_inter_solic_conso_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psfechafact      VARCHAR2,
    psregiones       VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Genera el archivo para la interfaz OCS Consolidado Detalle
  Fecha Creacion     : 27/11/2006
  Autor              : Marco Silva
  ***************************************************************************/
  PROCEDURE pri_pr_pri_calif_consu
  (
    pscodigopais        VARCHAR2,
    pscodigosistema     VARCHAR2,
    pscodigointerfaz    VARCHAR2,
    psnombrearchivo     VARCHAR2,
    pstipoclasificacion VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Genera el archivo para la interfaz OCS Consolidado
  Fecha Creacion     : 27/11/2006
  Autor              : Marco Agurto
  ***************************************************************************/
  PROCEDURE ocr_pr_inter_solic_conso_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psfechafact      VARCHAR2,
    psregiones       VARCHAR2,
    pstipodespacho   VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Consultora Puntajes
  Fecha Creacion     : 28/11/2006
  Autor              : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE evi_pr_inter_consu_punta
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Venta BAse Consultora
  Fecha Creacion     : 28/11/2006
  Autor              : Marco Silva
  ***************************************************************************/

  PROCEDURE sic_pr_venta_base_consul
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfase de Venta Directa Cabecera
  Fecha Creacion    : 22/01/2007
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE mye_pr_inter_venta_direc_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psfechadesde     VARCHAR2,
    psfechahasta     VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfase de Venta Directa DETALLE
  Fecha Creacion    : 22/01/2007
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE mye_pr_inter_venta_direc_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psfechadesde     VARCHAR2,
    psfechahasta     VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /****************************************************************
    Descripcion        : Genera el archivo para la Interfaz Historico Detalle Venta Retail
    Fecha Creacion     : 28/11/2006
    Autor              : Marco Silva

  ****************************************************************/
  PROCEDURE ret_pr_comis_venta_retail
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfechainicio    VARCHAR2,
    psfechafin       VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Genera el archivo para la interfaz OCS Consolidado
  Fecha Creacion     : 27/11/2006
  Autor              : Marco Agurto
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_conso_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  PROCEDURE ocr_pr_recep_conso_detal
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Obtiene el directorio para generar el Archivo a traves
                      del UTL_FILE en oracle
  Fecha Creacion    : 14/11/2006
  Autor             : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_obtie_direc_temp
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    ps_codigointerfaz VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  );

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
                      de Lectura
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoRetorno Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_lectu
  (
    pscodigopais           VARCHAR2,
    pscodigosistema        VARCHAR2,
    pscodigointerfaz       VARCHAR2,
    psnombrearchivo        VARCHAR2,
    psextensionarchivo     VARCHAR2,
    psdirtempo             OUT VARCHAR2,
    psnombrearchivoretorno OUT VARCHAR2,
    pnhandle               OUT utl_file.file_type,
    pncantidadcaracteres   NUMBER := 0
  );

  /***************************************************************************
  Descripcion       : Procedimiento final para la generación de Interfaces
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCarpetaUtl       Nombre de Objeto carpeta
     psDirTempoZip      Nombre del Directorio donde se encuentra el zipiado
     psNombreArchivoSinExtension    Nombre del Archivo sin extension
     psNombreArchivo    Nombre del Archivo de salida Temporal

  ***************************************************************************/
  PROCEDURE gen_pr_final_inter
  (
    pscarpetautl                VARCHAR2,
    psdirtempozip               VARCHAR2,
    psnombrearchivosinextension VARCHAR2,
    psnombrearchivo             VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Obtiene la fecha de la ultima ejecucion exitosa de la interfaz
  Fecha Creacion    : 01/10/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_ultim_ejecu_exito
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2
  ) RETURN DATE;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Reportes en
                      Oracle
  Fecha Creacion    : 15/08/2010
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psNombreArchivo    Nombre del Archivo sin extension
     psExtensionArchivo Extension del Archivo
     psLineaCabecera    Linea de Cabecera del archivo
     psDirTempo         Directorio en donde se grabara el archivo
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_repor_oracl
  (
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psextensionarchivo VARCHAR2,
    pslineacabecera    VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  );

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Reportes en
                      Oracle
  Fecha Creacion    : 15/08/2010
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psnombreReporte    Nombre del Reporte para buscar la ruta donde grabar que debe
                        estar registrado en la tabla bas_repor_param
     psNombreArchivo    Nombre del Archivo sin extension
     psExtensionArchivo Extension del Archivo
     psLineaCabecera    Linea de Cabecera del archivo
     psDirTempo         Directorio en donde se grabara el archivo
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_repor_oracl
  (
    pscodigopais       VARCHAR2,
    psnombrereporte    VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psextensionarchivo VARCHAR2,
    pslineacabecera    VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  );

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
                       Considera si se crea o no la cabecera del archivo
  Fecha Creacion    : 22/08/2012
  Autor             : Ivan Tocto Jaimes
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     pbFlagCabecera     Flag Cabecera
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pbflagcabecera     BOOLEAN,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  );
  
  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter02
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type,
    pncantidadcaracteres   NUMBER := 0
  );

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 08/04/2013
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_maxca
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pnmaximocaracter   VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  );

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 24/05/2013
  Autor             : Ivan Tocto
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     pbFlagCabecera     Flag para generar o no la cabecera
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_maxca
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pnmaximocaracter   VARCHAR2,
    pbflagcabecera     BOOLEAN,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  );

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 08/04/2013
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_maxca01
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pnmaximocaracter   VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  );

  /***************************************************************************
  Descripcion : Devuelve la estructura de una interfaz en un arreglo
  Fecha Creacion : 28/08/2013
  Autor : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_estru_archi
  (
    pscodigopais     IN VARCHAR2,
    pscodigointerfaz IN VARCHAR2,
    t_estructura     OUT t_all_estru_archi
  );
  /***************************************************************************
  Descripcion : Devuelve el primer campo de una linea basado en la configuracion}
                de la interfaz
  Fecha Creacion : 28/08/2013
  Autor : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_read_campo
  (
    pslinea IN OUT VARCHAR2,
    t_estru IN t_estru_archi,
    pscampo OUT VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
                      de Lectura
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoRetorno Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
     t_estructura     Arreglo con estructura de archivo,
     pncantidadcaracteres   numero de caracteres ara lectura
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_lectu
  (
    pscodigopais           VARCHAR2,
    pscodigosistema        VARCHAR2,
    pscodigointerfaz       VARCHAR2,
    psnombrearchivo        VARCHAR2,
    psextensionarchivo     VARCHAR2,
    psdirtempo             OUT VARCHAR2,
    psnombrearchivoretorno OUT VARCHAR2,
    pnhandle               OUT utl_file.file_type,
    t_estructura           OUT t_all_estru_archi,
    pncantidadcaracteres   NUMBER := 0
  );
END gen_pkg_inter_archi;
/
CREATE OR REPLACE PACKAGE BODY "GEN_PKG_INTER_ARCHI" IS

  /***************************************************************************
  Descripcion       : Obtiene el directorio para generar el Archivo a traves
                      del UTL_FILE en oracle
  Fecha Creacion    : 14/11/2006
  Autor             : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_obtie_direc_proc
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    ps_codigointerfaz VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_dirtempo bas_inter.dir_temp%TYPE;
  BEGIN
    SELECT a.dir_proc
      INTO ls_dirtempo
      FROM bas_inter a
     WHERE a.pais_cod_pais = pscodigopais
       AND a.sist_cod_sist = pscodigosistema
       AND a.cod_inte = ps_codigointerfaz;
    RETURN ls_dirtempo;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OBTIE_DIREC_PROC: ' ||
                              ls_sqlerrm);
  END gen_fn_obtie_direc_proc;

  /***************************************************************************
  Descripcion       : Obtiene el porcentaje de descuento
  Fecha Creacion    : 20/02/2007
  Autor             : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_obtie_porc_dcto
  (
    psoidcliente           NUMBER,
    ps_porcentajedescuento VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_val_por_desc  NUMBER := 0;
    ls_codigoperiodo bas_ctrl_fact.cod_peri%TYPE;
    l_oid_peri       NUMBER(12);

    ls_codigoperiodoante bas_ctrl_fact.cod_peri%TYPE;
    l_oid_peri_ante      NUMBER(12);
  BEGIN
    SELECT MIN(a.cod_peri)
      INTO ls_codigoperiodo
      FROM bas_ctrl_fact a
     WHERE a.sta_camp = 0;

    -- Obtenemos el oid del periodo
    l_oid_peri      := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(ls_codigoperiodo);
    ls_val_por_desc := gen_fn_obtie_porc_dcto_peri(psoidcliente, l_oid_peri);

    IF (ls_val_por_desc = -1) THEN
      ls_codigoperiodoante := gen_fn_calcu_perio(ls_codigoperiodo, -1);
      l_oid_peri_ante      := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(ls_codigoperiodoante);
      ls_val_por_desc      := gen_fn_obtie_porc_dcto_peri(psoidcliente,
                                                          l_oid_peri_ante);
    END IF;
    IF (ls_val_por_desc = -1) THEN
      RETURN ps_porcentajedescuento;
    ELSE
      RETURN to_char(ls_val_por_desc);
    END IF;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OBTIE_PORC_DCTO: ' ||
                              ls_sqlerrm);
  END gen_fn_obtie_porc_dcto;

  /***************************************************************************
  Descripcion       : Obtiene el porcentaje de descuento para una determinado periodo
  Fecha Creacion    : 20/02/2007
  Fecha Modificacion: 19/08/2015 
  Autor             : Marco Agurto
  Autor Modificacion: CSVD - FFVV 
  ***************************************************************************/
  FUNCTION gen_fn_obtie_porc_dcto_peri
  (
    ps_codigocliente NUMBER,
    ps_codigoperiodo NUMBER
  ) RETURN NUMBER IS
    ls_val_por_desc NUMBER := 0;
  BEGIN
    SELECT nvl(MAX(val_porc_desc), -1)
      INTO ls_val_por_desc
      FROM ped_solic_cabec     oc,
           ped_solic_cabec     conso,
           ped_solic_posic,
           mae_produ,
           ped_tipo_solic_pais ptsp,
           ped_tipo_solic      pts
     WHERE oc.ind_ts_no_conso = 1
       AND oc.ind_oc = 1
       AND oc.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
       AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
       AND oc.fec_fact IS NOT NULL
       AND espo_oid_esta_posi <> 2
       AND mae_produ.mapr_oid_marc_prod <> 1
       --AND ped_solic_posic.tpos_oid_tipo_posi = 1
       AND oc.perd_oid_peri = ps_codigoperiodo
       AND oc.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
       AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
       AND pts.ind_anul <> 1
       AND pts.ind_devo <> 1
       AND oc.soca_oid_soli_cabe = conso.oid_soli_cabe(+)
       AND nvl(conso.esso_oid_esta_soli, 1) <> 4
       AND oc.clie_oid_clie = ps_codigocliente;

    IF ls_val_por_desc = 0 THEN
      ls_val_por_desc := -1;
    END IF;

    RETURN ls_val_por_desc;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN - 1;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OBTIE_PORC_DCTO_PERI: ' ||
                              ls_sqlerrm);
  END gen_fn_obtie_porc_dcto_peri;

  /**************************************************************************
  Descripcion       : Calcula el saldo deudor del cliente restando el valor en cupones,
                    considerando todas las deudas no solo las vencidas
  Fecha Creacion    : 02/20/2007
  Parametros Entrada:
      ps_cod_clie   : Codigo de cliente
  Autor             : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_calcu_valor_sald_deudo2(ps_cod_clie IN VARCHAR2)
    RETURN NUMBER IS
    saldo NUMBER;
  BEGIN
    -- Obtengo el saldo del cliente
    SELECT SUM((CASE
                 WHEN subproceso.val_indi_cons = 'H' THEN
                  cargodetalle.imp * -1
                 ELSE
                  cargodetalle.imp
               END)) AS monope
      INTO saldo
      FROM ccc_detal_cargo_abono_direc cargodetalle,
           ccc_cabec_carga_abono_direc cargocabecera,
           ccc_tipo_abono_subpr        tipoabono,
           ccc_subpr                   subproceso,
           ccc_proce                   proceso,
           mae_clien                   cliente,
           cra_perio                   periodocronograma,
           seg_perio_corpo             periodocorporativo,
           ccc_movim_cuent_corri       movctacte
     WHERE cargodetalle.ccad_oid_cabe_carg = cargocabecera.oid_cabe_carg
       AND cargodetalle.tasp_oid_tipo_abon_subp =
           tipoabono.oid_tipo_abon_subp
       AND tipoabono.subp_oid_subp = subproceso.oid_subp
       AND subproceso.val_indi_cons IN ('A', 'D', 'H')
       AND cargodetalle.mvcc_oid_movi_cc = movctacte.oid_movi_cc(+)
       AND cliente.oid_clie = cargodetalle.clie_oid_clie
       AND subproceso.ccpr_oid_proc = proceso.oid_proc
       AND periodocronograma.peri_oid_peri = periodocorporativo.oid_peri(+)
       AND movctacte.perd_oid_peri = periodocronograma.oid_peri(+)
       AND cliente.cod_clie = ps_cod_clie;

    IF saldo IS NULL THEN
      saldo := 0;
    END IF;

    RETURN saldo;
  EXCEPTION
    WHEN no_data_found THEN
      saldo := 0;
      RETURN saldo;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_FN_CALCU_VALOR_SALD_DEUDO2: ' ||
                              ls_sqlerrm);
  END gen_fn_calcu_valor_sald_deudo2;

  /***************************************************************************
  Descripcion       : Obtiene el directorio para generar el Archivo a traves
                      del UTL_FILE en oracle
  Fecha Creacion    : 14/11/2006
  Autor             : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_obtie_direc_temp
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    ps_codigointerfaz VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_dirtempo bas_inter.dir_temp%TYPE;
  BEGIN
    SELECT a.dir_hist
      INTO ls_dirtempo
      FROM bas_inter a
     WHERE a.pais_cod_pais = pscodigopais
       AND a.sist_cod_sist = pscodigosistema
       AND a.cod_inte = ps_codigointerfaz;
    RETURN ls_dirtempo;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OBTIE_DIREC_PROC: ' ||
                              ls_sqlerrm);
  END gen_fn_obtie_direc_temp;

  /***************************************************************************
  Descripcion       : Crea directorio para generar el Archivo a traves
                      del UTL_FILE en oracle
  Fecha Creacion    : 14/11/2006
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_crea_direc
  (
    psnombreobjetodir VARCHAR2,
    psnombredirec     VARCHAR2
  ) IS

    l_sqlc VARCHAR2(1000);
    l_cur  INTEGER;
    l_rowp INTEGER;

  BEGIN
    l_cur  := dbms_sql.open_cursor;
    l_sqlc := 'CREATE OR REPLACE DIRECTORY ' || psnombreobjetodir ||
              ' AS ''' || psnombredirec || '''';
    dbms_sql.parse(l_cur, l_sqlc, dbms_sql.v7);
    l_rowp := dbms_sql.execute(l_cur);
    dbms_sql.close_cursor(l_cur);

  END gen_pr_crea_direc;

  /***************************************************************************
  Descripcion       : Obtiene la cabecera a usar para generar el archivo a traves
                      del UTL_FILE en oracle
  Fecha Creacion    : 14/11/2006
  Autor             : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_obtie_cabec_archi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2
  ) RETURN VARCHAR2 IS
    CURSOR c_cabecera IS
      SELECT ide_camp
        FROM bas_estru_archi a
       WHERE a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY pos_camp;
    lslineacabecera VARCHAR2(4000);
  BEGIN
    /* Generando Texto de Cabecera */
    lslineacabecera := '';
    FOR c_cabec IN c_cabecera
    LOOP
      IF TRIM(lslineacabecera) IS NOT NULL THEN
        lslineacabecera := lslineacabecera || ';' || c_cabec.ide_camp;
      ELSE
        lslineacabecera := c_cabec.ide_camp;
      END IF;
    END LOOP;
    RETURN lslineacabecera;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OBTIE_CABEC_ARCHI: ' ||
                              ls_sqlerrm);
  END gen_fn_obtie_cabec_archi;

  /***************************************************************************
  Descripcion       : Comprime archivo usando Clase Java
  Fecha Creacion    : 14/11/2006
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_compr_zip
  (
    psdirectorio VARCHAR2,
    psfilaorigen VARCHAR2
  ) AS
    LANGUAGE JAVA NAME 'ZIPCompress.comprimir(java.lang.String, java.lang.String)';

  /***************************************************************************
  Descripcion       : Genera Interfase de Venta Directa Cabecera
  Fecha Creacion    : 22/01/2007
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE mye_pr_inter_venta_direc_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psfechadesde     VARCHAR2,
    psfechahasta     VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT p.pais_cod_pais AS cod_pais,
             p.ser_cope AS ser_cope,
             p.num_cope AS num_cope,
             p.cod_cons AS cod_cons,
             p.fec_cope AS fec_cope,
             per_pkg_proce_perce.per_fn_devue_monto_perce_vtadi(p.pais_cod_pais,
                                                                p.ser_cope,
                                                                p.num_cope,
                                                                p.cod_cons) AS mon_perc,

             p.cod_ausu AS cod_ausu,
             1 AS estado,
             (SELECT num_ejin
                FROM bas_inter b
               WHERE b.pais_cod_pais = pscodigopais
                 AND b.sist_cod_sist = pscodigosistema
                 AND b.cod_inte = pscodigointerfaz) AS num_lote,
             1 AS auxiliar
        FROM per_perce_venta_direc p
       WHERE p.pais_cod_pais = pscodigopais
         AND p.fec_cope >= to_date(psfechadesde, 'DD/MM/YYYY')
         AND p.fec_cope <= to_date(psfechahasta, 'DD/MM/YYYY')
       GROUP BY p.pais_cod_pais,
                p.ser_cope,
                p.num_cope,
                p.cod_cons,
                p.fec_cope,
                p.cod_ausu;

    TYPE interfazrec IS RECORD(
      codigopais per_perce_venta_direc.pais_cod_pais%TYPE,
      sercope    per_perce_venta_direc.ser_cope%TYPE,
      numcope    per_perce_venta_direc.num_cope%TYPE,
      concons    per_perce_venta_direc.cod_cons%TYPE,
      fecdole    DATE,
      monperc    NUMBER,
      codausu    per_perce_venta_direc.cod_ausu%TYPE,
      estado     VARCHAR2(1),
      numlote    bas_inter.num_ejin%TYPE,
      auxiliar   VARCHAR2(1));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).sercope || ';' || interfazrecord(x)
                     .numcope || ';' || interfazrecord(x).concons || ';' ||
                      to_char(interfazrecord(x).fecdole, 'YYYYMMDD') || ';' ||
                      to_char(interfazrecord(x).monperc, '9999999.99') || ';' || interfazrecord(x)
                     .codausu || ';' || interfazrecord(x).estado || ';' || interfazrecord(x)
                     .numlote || ';' || interfazrecord(x).auxiliar;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR MYE_PR_INTER_VENTA_DIREC_CABEC: ' ||
                              ls_sqlerrm);

  END mye_pr_inter_venta_direc_cabec;

  /***************************************************************************
  Descripcion       : Genera Interfase de Venta Directa DETALLE
  Fecha Creacion    : 22/01/2007
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE mye_pr_inter_venta_direc_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psfechadesde     VARCHAR2,
    psfechahasta     VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT p.pais_cod_pais AS cod_pais,
             p.ser_cope AS ser_cope,
             p.num_cope AS num_cope,
             (SELECT cod_homo
                FROM per_tipo_docum_ident_legal b
               WHERE b.pais_cod_pais = pscodigopais
                 AND cod_clas = 'TC'
                 AND cod_ahom = p.tip_dole) tip_dole,
             p.ser_dole AS ser_dole,
             p.num_dole AS num_dole,
             p.fec_dole AS fec_dole,
             p.mon_todl AS mon_todl,
             p.por_perc AS por_perc,
             p.mon_perc AS mon_perc,
             p.mon_pago AS mon_pago,
             1 AS auxiliar
        FROM per_perce_venta_direc p
       WHERE p.pais_cod_pais = pscodigopais
         AND p.fec_cope >= to_date(psfechadesde, 'DD/MM/YYYY')
         AND p.fec_cope <= to_date(psfechahasta, 'DD/MM/YYYY')
       ORDER BY ser_cope,
                num_cope,
                ser_dole,
                num_dole;

    TYPE interfazrec IS RECORD(
      codigopais per_perce_venta_direc.pais_cod_pais%TYPE,
      sercope    per_perce_venta_direc.ser_cope%TYPE,
      numcope    per_perce_venta_direc.num_cope%TYPE,
      tipdole    per_perce_venta_direc.tip_dole%TYPE,
      serdole    per_perce_venta_direc.ser_dole%TYPE,
      numdole    per_perce_venta_direc.num_dole%TYPE,
      fecdole    DATE,
      montodl    per_perce_venta_direc.mon_todl%TYPE,
      porperc    per_perce_venta_direc.por_perc%TYPE,
      monperc    per_perce_venta_direc.mon_perc%TYPE,
      monpago    per_perce_venta_direc.mon_pago%TYPE,
      auxiliar   VARCHAR2(1));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).sercope || ';' || interfazrecord(x)
                     .numcope || ';' || interfazrecord(x).tipdole || ';' || interfazrecord(x)
                     .serdole || ';' || interfazrecord(x).numdole || ';' ||
                      to_char(interfazrecord(x).fecdole, 'YYYYMMDD') || ';' ||
                      to_char(interfazrecord(x).montodl, '9999999.99') || ';' ||
                      to_char(interfazrecord(x).porperc, '9999999.99') || ';' ||
                      to_char(interfazrecord(x).monperc, '9999999.99') || ';' ||
                      to_char(interfazrecord(x).monpago, '9999999.99') || ';' || interfazrecord(x)
                     .auxiliar;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR MYE_PR_INTER_VENTA_DIREC_DETAL: ' ||
                              ls_sqlerrm);

  END mye_pr_inter_venta_direc_detal;

  PROCEDURE cob_pr_inter_datos_infoc
  (
    pscodigopais        VARCHAR2,
    pscodigosistema     VARCHAR2,
    pscodigointerfaz    VARCHAR2,
    psnombrearchivo     VARCHAR2,
    pscampanyainicial   VARCHAR2,
    psdiasvencimiento   VARCHAR2,
    psimporte           VARCHAR2,
    pstipomovimiento    VARCHAR2,
    pscodigoentidad     VARCHAR2,
    pstipodoctributario VARCHAR2,
    pstipopersona       VARCHAR2,
    pstipodeudor        VARCHAR2,
    pstipodocumento     VARCHAR2,
    pstipomoneda        VARCHAR2,
    pscondiciondeuda    VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT to_char(SYSDATE, 'YYYYMMDD') AS fec_proc,
             cod_tipo_movi,
             cod_enti,
             cod_clie,
             cod_tipo_docu_trib,
             ' ' AS blanco,
             cod_tipo_docu_clie,
             num_docu_iden,
             cod_tipo_pers,
             cod_tipo_deud,
             nom_comp_clie,
             dir_comp_clie,
             '' cod_ge01,
             des_geo1,
             '' cod_geo2,
             des_geo2,
             to_char(fec_venc, 'YYYYMMDD') AS fec_venc,
             cod_tipo_docu,
             cod_tipo_mone,
             imp_pend,
             cod_cond_deud,
             ' ' per_deud,
             ' ' tip_docu_trib_aval,
             ' ' num_docu_trib_aval,
             ' ' tip_docu_iden_aval,
             ' ' num_docu_iden_aval,
             ' ' tip_pers_aval,
             ' ' nom_aval
        FROM int_cob_clien_casti;

    TYPE interfazrec IS RECORD(
      fechareporte            VARCHAR2(8),
      tipomovimiento          VARCHAR2(1),
      codigoentidad           VARCHAR2(6),
      codigomoroso            mae_clien.cod_clie%TYPE,
      tipodoctributario       VARCHAR2(1),
      blanco                  VARCHAR2(8),
      tipodocidentidad        VARCHAR2(1),
      numerodocidentidad      mae_clien_ident.num_docu_iden%TYPE,
      tipopersona             VARCHAR2(1),
      tipodeudor              VARCHAR2(1),
      nombre                  VARCHAR2(100),
      direccion               VARCHAR2(100),
      codigodistrito          VARCHAR2(6),
      nombredistrito          zon_valor_estru_geopo.des_geog%TYPE,
      codigodepartamento      VARCHAR2(2),
      nombredepartamento      zon_valor_estru_geopo.des_geog%TYPE,
      fechavencimiento        VARCHAR2(8),
      tipodocumento           VARCHAR2(2),
      tipomoneda              VARCHAR2(2),
      montoimpago             VARCHAR2(12),
      condiciondeuda          VARCHAR2(1),
      perfildeudor            VARCHAR2(24),
      tipodoctributarioaval   VARCHAR2(1),
      numerodoctributarioaval VARCHAR2(8),
      tipodocidentidadaval    VARCHAR2(1),
      numdocidentidadaval     VARCHAR2(12),
      tipopersonaaval         VARCHAR2(1),
      nombreaval              VARCHAR2(80));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .tipomovimiento || ';' || interfazrecord(x)
                     .codigoentidad || ';' || interfazrecord(x).codigomoroso || ';' || interfazrecord(x)
                     .tipodoctributario || ';' || interfazrecord(x).blanco || ';' || interfazrecord(x)
                     .tipodocidentidad || ';' || interfazrecord(x)
                     .numerodocidentidad || ';' || interfazrecord(x)
                     .tipopersona || ';' || interfazrecord(x).tipodeudor || ';' || interfazrecord(x)
                     .nombre || ';' || interfazrecord(x).direccion || ';' || interfazrecord(x)
                     .codigodistrito || ';' || interfazrecord(x)
                     .nombredistrito || ';' || interfazrecord(x)
                     .codigodepartamento || ';' || interfazrecord(x)
                     .nombredepartamento || ';' || interfazrecord(x)
                     .fechavencimiento || ';' || interfazrecord(x)
                     .tipodocumento || ';' || interfazrecord(x).tipomoneda || ';' || interfazrecord(x)
                     .montoimpago || ';' || interfazrecord(x).condiciondeuda || ';' || interfazrecord(x)
                     .perfildeudor || ';' || interfazrecord(x)
                     .tipodoctributarioaval || ';' || interfazrecord(x)
                     .numerodoctributarioaval || ';' || interfazrecord(x)
                     .tipodocidentidadaval || ';' || interfazrecord(x)
                     .numdocidentidadaval || ';' || interfazrecord(x)
                     .tipopersonaaval || ';' || interfazrecord(x).nombreaval;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR COB_PR_INTER_DATOS_INFOC: ' ||
                              ls_sqlerrm);

  END cob_pr_inter_datos_infoc;

  PROCEDURE ocr_pr_inter_solic_conso_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psfechafact      VARCHAR2,
    psregiones       VARCHAR2,
    pstipodespacho   VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.cod_pais,
             a.cod_peri,
             a.cod_clie,
             a.num_clie,
             a.tipo_soli,
             a.cod_sbac,
             a.cod_acce,
             pstipodespacho,
             a.fec_soli,
             (SELECT b.fec_proc
                FROM bas_ctrl_fact b
               WHERE b.ind_camp_act = 1) fec_proc,
             a.sta_proc,
             a.num_lote
        FROM int_solic_conso_cabec a
       WHERE a.cod_pais = pscodigopais
         AND a.cod_peri = pscodigoperiodo
         AND a.ind_bloq_admi = '0'
         AND a.ind_bloq_fina = '0'
         AND ((a.ind_erro_deud = '0') OR (a.ind_erro_deud = '1') OR
             (a.ind_erro_deud = '2' AND a.ind_admi_cart = '1'))
         AND a.ind_ocs_proc = '0'
         AND a.ind_error_sgpe = '0'
         AND a.ind_ocs_bloq = '0'
         AND a.ind_erro_rech = '0'
         AND (a.ind_erro_mtmi = '0' OR
             (a.ind_erro_mtmi = '0' AND a.ind_comp_mont = '1')) -- setear a 1 A.IND_ERRO_MTMI
         AND a.ind_erro_mtma = '0'
         AND a.ind_erro_remp = '0'
         AND a.ind_erro_node = '0'
         AND a.ind_cont_act = '0'
            -- Para los pedidos anulados en SSicc
         AND a.ind_anul = 0
         AND a.ind_orig_cabe = '1';

    TYPE interfazcab IS RECORD(
      codigopais      int_solic_conso_cabec.cod_pais%TYPE,
      codigoperiodo   int_solic_conso_cabec.cod_peri%TYPE,
      codigocliente   int_solic_conso_cabec.cod_clie%TYPE,
      numeroclientes  int_solic_conso_cabec.num_clie%TYPE,
      tiposolicitud   int_solic_conso_cabec.tipo_soli%TYPE,
      codigosubacceso int_solic_conso_cabec.cod_sbac%TYPE,
      codigoacceso    int_solic_conso_cabec.cod_acce%TYPE,
      tipodespacho    int_solic_conso_cabec.tip_desp%TYPE,
      fechasolicitud  int_solic_conso_cabec.fec_soli%TYPE,
      fechaproceso    bas_ctrl_fact.fec_proc%TYPE,
      status          int_solic_conso_cabec.sta_proc%TYPE,
      numlote         int_solic_conso_cabec.num_lote%TYPE);

    TYPE interfazcabtab IS TABLE OF interfazcab;
    interfazrecord interfazcabtab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigoperiodo || ';' || interfazrecord(x)
                     .codigocliente || ';' || interfazrecord(x)
                     .numeroclientes || ';' || interfazrecord(x)
                     .tiposolicitud || ';' || interfazrecord(x)
                     .codigosubacceso || ';' || interfazrecord(x)
                     .codigoacceso || ';' || interfazrecord(x).tipodespacho || ';' ||
                      to_char(interfazrecord(x).fechaproceso, 'YYYYMMDD') || ';' || interfazrecord(x)
                     .status || ';' || 0 || ';' || -- numero de documento de origen
                      ' ' || ';' || -- Canal de Referencia
                      ' ' || ';' || -- Acceso de Referencia
                      ' ' || ';' || -- SubAcceso de Referencia
                      0; -- numero de documento de referencia
          utl_file.put_line(v_handle, lslinea);

          UPDATE int_solic_conso_cabec k
             SET k.ind_ocs_proc  = '3',
                 k.fec_prog_fact = interfazrecord(x).fechaproceso
           WHERE k.cod_pais = interfazrecord(x).codigopais
             AND k.cod_peri = interfazrecord(x).codigoperiodo
             AND k.cod_clie = interfazrecord(x).codigocliente
             AND k.num_lote = interfazrecord(x).numlote;
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_INTER_SOLIC_CONSO_CABEC: ' ||
                              ls_sqlerrm);

  END ocr_pr_inter_solic_conso_cabec;

  PROCEDURE ocr_pr_recep_conso_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
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

    TYPE t_codpais IS TABLE OF int_solic_cabec.cod_pais%TYPE;
    TYPE t_camsoli IS TABLE OF int_solic_cabec.cam_soli%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_cabec.cod_clie%TYPE;
    TYPE t_numclie IS TABLE OF int_solic_cabec.num_clie%TYPE;
    TYPE t_tipsoli IS TABLE OF int_solic_cabec.tip_soli%TYPE;
    TYPE t_codsuba IS TABLE OF int_solic_cabec.cod_suba%TYPE;
    TYPE t_codacce IS TABLE OF int_solic_cabec.cod_acce%TYPE;
    TYPE t_tipdesp IS TABLE OF int_solic_cabec.tip_desp%TYPE;
    TYPE t_fecsoli IS TABLE OF int_solic_cabec.fec_soli%TYPE;
    TYPE t_staproc IS TABLE OF int_solic_cabec.sta_proc%TYPE;

    v_codpais t_codpais := t_codpais();
    v_camsoli t_camsoli := t_camsoli();
    v_codclie t_codclie := t_codclie();
    v_numclie t_numclie := t_numclie();
    v_tipsoli t_tipsoli := t_tipsoli();
    v_codsuba t_codsuba := t_codsuba();
    v_codacce t_codacce := t_codacce();
    v_tipdesp t_tipdesp := t_tipdesp();
    v_fecsoli t_fecsoli := t_fecsoli();
    v_staproc t_staproc := t_staproc();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

    v_existe_campana NUMBER := 0;
    v_camactiva      int_solic_cabec.cam_soli%TYPE;

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;

          --Obteniendo la campaña activa
          SELECT c.cod_peri
            INTO v_camactiva
            FROM bas_ctrl_fact c
           WHERE c.cod_pais = pscodigopais
             AND c.ind_camp_act = 1
             AND c.sta_camp = '0';

          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_camsoli.extend;
                  v_camsoli(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      v_camactiva);

                  -- Se valida que exista la campaña en la tabla bas_ctrl_fact
                  SELECT COUNT(1)
                    INTO v_existe_campana
                    FROM bas_ctrl_fact
                   WHERE cod_pais = v_codpais(i)
                     AND cod_peri = v_camsoli(i)
                     AND num_lote IS NOT NULL;

                  IF (v_existe_campana = 0) THEN
                    v_camsoli(i) := v_camactiva;
                  END IF;

                ELSIF (posicion = 3) THEN
                  v_codclie.extend;
                  v_codclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 4) THEN
                  v_numclie.extend;
                  v_numclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      0);
                ELSIF (posicion = 5) THEN
                  v_tipsoli.extend;
                  v_tipsoli(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 6) THEN
                  v_codsuba.extend;
                  v_codsuba(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 7) THEN
                  v_codacce.extend;
                  v_codacce(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 8) THEN
                  v_tipdesp.extend;
                  v_tipdesp(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_fecsoli.extend;
                  v_fecsoli(i) := to_date(substr(lslinea, inicio, longitud),
                                          'yyyyMMdd');
                ELSIF (posicion = 10) THEN
                  v_staproc.extend;
                  v_staproc(i) := TRIM(substr(lslinea, inicio, longitud));
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
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_solic_cabec
        (cod_pais,
         cam_soli,
         cod_clie,
         num_clie,
         tip_soli,
         cod_suba,
         cod_acce,
         tip_desp,
         fec_soli,
         sta_proc,
         oid_cab,
         num_lote,
         cod_inte,
         num_lote_inte,
         num_lote_sto,
         ind_proc)
      VALUES
        (v_codpais(i),
         v_camsoli(i),
         v_codclie(i),
         v_numclie(i),
         v_tipsoli(i),
         v_codsuba(i),
         v_codacce(i),
         v_tipdesp(i),
         v_fecsoli(i),
         v_staproc(i),
         seq_solic_cab.nextval,
         (SELECT bas.num_lote
            FROM bas_ctrl_fact bas
           WHERE bas.cod_pais = v_codpais(i)
             AND bas.cod_peri = v_camsoli(i)),
         pscodigointerfaz,
         psnumerolote,
         psnumerolotesto,
         psindicadororigen);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_RECEP_CONSO_CABEC: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);

  END ocr_pr_recep_conso_cabec;

  PROCEDURE ocr_pr_recep_conso_detal
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
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

    TYPE t_codpais IS TABLE OF int_solic_posic.cod_pais%TYPE;
    TYPE t_camsoli IS TABLE OF int_solic_posic.cam_soli%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_posic.cod_clie%TYPE;
    TYPE t_tipposi IS TABLE OF int_solic_posic.tip_posi%TYPE;
    TYPE t_codprod IS TABLE OF int_solic_posic.cod_prod%TYPE;
    TYPE t_unidema IS TABLE OF int_solic_posic.uni_dema%TYPE;
    TYPE t_sta_proc IS TABLE OF int_solic_posic.sta_proc%TYPE;

    v_codpais  t_codpais := t_codpais();
    v_camsoli  t_camsoli := t_camsoli();
    v_codclie  t_codclie := t_codclie();
    v_tipposi  t_tipposi := t_tipposi();
    v_codprod  t_codprod := t_codprod();
    v_unidema  t_unidema := t_unidema();
    v_sta_proc t_sta_proc := t_sta_proc();

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;

    inicio NUMBER := 0;

    v_existe_campana NUMBER := 0;
    v_camactiva      int_solic_cabec.cam_soli%TYPE;

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;

          --Obteniendo la campaña activa
          SELECT c.cod_peri
            INTO v_camactiva
            FROM bas_ctrl_fact c
           WHERE c.cod_pais = pscodigopais
             AND c.ind_camp_act = 1
             AND c.sta_camp = '0';

          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP

                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;

                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_camsoli.extend;
                  v_camsoli(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      v_camactiva);

                  -- Se valida que exista la campaña en la tabla bas_ctrl_fact
                  SELECT COUNT(1)
                    INTO v_existe_campana
                    FROM bas_ctrl_fact
                   WHERE cod_pais = v_codpais(i)
                     AND cod_peri = v_camsoli(i)
                     AND num_lote IS NOT NULL;

                  IF (v_existe_campana = 0) THEN
                    v_camsoli(i) := v_camactiva;
                  END IF;

                ELSIF (posicion = 3) THEN
                  v_codclie.extend;
                  v_codclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 4) THEN
                  v_tipposi.extend;
                  v_tipposi(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 5) THEN
                  v_codprod.extend;
                  v_codprod(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 6) THEN
                  v_unidema.extend;
                  v_unidema(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      0);
                ELSIF (posicion = 7) THEN
                  v_sta_proc.extend;
                  v_sta_proc(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       0);

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
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_solic_posic
        (oid_posic,
         cod_pais,
         cam_soli,
         cod_clie,
         tip_posi,
         cod_prod,
         uni_dema,
         num_lote,
         sta_proc,
         cod_inte,
         num_lote_inte,
         num_lote_sto,
         ind_proc)
      VALUES
        (seq_solic_pos.nextval,
         v_codpais(i),
         v_camsoli(i),
         v_codclie(i),
         v_tipposi(i),
         v_codprod(i),
         v_unidema(i),
         (SELECT bas.num_lote
            FROM bas_ctrl_fact bas
           WHERE bas.cod_pais = v_codpais(i)
             AND bas.cod_peri = v_camsoli(i)),
         v_sta_proc(i),
         pscodigointerfaz,
         psnumerolote,
         psnumerolotesto,
         psindicadororigen);

    -- Eliminar los productos digitados que sean premios del Programa Nuevas
    --  incidencias por CUVs no digitables q ingresan por Modulos de Digitacion (MyEbel, DD, OCR, ..)
    DELETE FROM int_solic_posic pos
     WHERE pos.cod_pais = pscodigopais
       AND EXISTS (SELECT NULL
              FROM cup_desp_prod prod
             WHERE prod.cod_pais = pos.cod_pais
               AND prod.cod_peri = pos.cam_soli
               AND prod.cod_venta = pos.cod_prod
               AND prod.sta_reg = '1');

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_RECEP_CONSO_DETAL: ' ||
                              ls_sqlerrm);

  END ocr_pr_recep_conso_detal;

  PROCEDURE ocr_pr_inter_solic_conso_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2,
    psfechafact      VARCHAR2,
    psregiones       VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT b.cod_pais,
             b.cod_peri,
             b.cod_clie,
             b.cod_vent,
             b.tip_posic,
             b.val_unid_dem,
             'P',
             b.num_lote
        FROM int_solic_conso_cabec a,
             int_solic_conso_detal b
       WHERE a.cod_pais = pscodigopais
         AND a.cod_peri = pscodigoperiodo
         AND a.cod_pais = b.cod_pais
         AND a.cod_peri = b.cod_peri
         AND a.cod_clie = b.cod_clie
         AND a.num_lote = b.num_lote
         AND b.ind_erro_sse = '0' -- Indicador Bloqueo Session Experte o PRINT
         AND a.ind_ocs_proc = '3';

    TYPE interfazrec IS RECORD(
      codigopais    int_solic_conso_detal.cod_pais%TYPE,
      codigoperiodo int_solic_conso_detal.cod_peri%TYPE,
      codigocliente int_solic_conso_detal.cod_clie%TYPE,
      codigoventa   int_solic_conso_detal.cod_vent%TYPE,
      tipoposicion  int_solic_conso_detal.tip_posic%TYPE,
      unidadesdem   int_solic_conso_detal.val_unid_dem%TYPE,
      status        int_solic_conso_detal.sta_proc%TYPE,
      numlote       int_solic_conso_detal.num_lote%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigoperiodo || ';' || interfazrecord(x)
                     .codigocliente || ';' || interfazrecord(x).tipoposicion || ';' || interfazrecord(x)
                     .codigoventa || ';' || interfazrecord(x).unidadesdem || ';' || interfazrecord(x)
                     .status || ';' || ' ' || ';' || 0 || ';' || ' ' || ';' || 0;

          utl_file.put_line(v_handle, lslinea);

        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);

    -- Actulaiza las cabeceras a 1 procesadas
    UPDATE int_solic_conso_cabec ka
       SET ka.ind_ocs_proc = '1'
     WHERE ka.ind_ocs_proc = '3';

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_INTER_SOLIC_CONSO_DETAL: ' ||
                              ls_sqlerrm);

  END ocr_pr_inter_solic_conso_detal;

  /**************************************************************************
   Descripcion        : Genera el archivo para la Interfaz Enviar Venta BAse Consultora
   Fecha Creacion     : 28/11/2006
   Autor              : Marco Silva
  ***************************************************************************/
  PROCEDURE sic_pr_venta_base_consul
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  )

   IS
    CURSOR c_interfaz IS
      SELECT b.cod_pais,
             b.cod_peri,
             b.cod_cons,
             b.cod_empr,
             b.cod_conc,
             b.val_pntj
        FROM int_inc_venta_base b;

    TYPE interfazrec IS RECORD(
      codigopais     int_inc_venta_base.cod_pais%TYPE,
      codigoperiodo  int_inc_venta_base.cod_peri%TYPE,
      codigocliente  int_inc_venta_base.cod_cons%TYPE,
      codigoempresa  int_inc_venta_base.cod_empr%TYPE,
      codigoconcurso int_inc_venta_base.cod_conc%TYPE,
      puntaje        int_inc_venta_base.val_pntj%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigoconcurso || ';' || interfazrecord(x)
                     .codigoempresa || ';' || interfazrecord(x)
                     .codigoperiodo || ';' || interfazrecord(x)
                     .codigocliente || ';' || interfazrecord(x).puntaje;

          utl_file.put_line(v_handle, lslinea);

        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR SIC_PR_VENTA_BASE_CONSUL: ' ||
                              ls_sqlerrm);

  END sic_pr_venta_base_consul;

  /*****************************************************************/
  /****************************************************************/

  PROCEDURE pri_pr_pri_calif_consu
  (
    pscodigopais        VARCHAR2,
    pscodigosistema     VARCHAR2,
    pscodigointerfaz    VARCHAR2,
    psnombrearchivo     VARCHAR2,
    pstipoclasificacion VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT pri_calif_consu.cod_pais,
             pri_calif_consu.cod_peri,
             pri_calif_consu.cod_cons,
             pstipoclasificacion AS tipo
        FROM pri_calif_consu,
             mae_clien
       WHERE mae_clien.cod_clie = pri_calif_consu.cod_cons;

    TYPE interfazrec IS RECORD(
      codigopais        pri_calif_consu.cod_pais%TYPE,
      codigoperiodo     pri_calif_consu.cod_peri%TYPE,
      codigoconsultora  pri_calif_consu.cod_cons%TYPE,
      tipoclasificacion VARCHAR2(2));

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigoperiodo || ';' || interfazrecord(x).codigopais || ';' || interfazrecord(x)
                     .codigoconsultora || ';' || interfazrecord(x)
                     .tipoclasificacion;

          utl_file.put_line(v_handle, lslinea);

        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR PRI_PR_PRI_CALIF_CONSU: ' ||
                              ls_sqlerrm);

  END pri_pr_pri_calif_consu;

  /***************************************************************************************
    Descripcion        : Genera el archivo para la Interfaz Historico Detalle Venta Retail
    Fecha Creacion     : 28/11/2006
    Autor              : Marco Silva

  ****************************************************************************************/
  PROCEDURE ret_pr_comis_venta_retail
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psfechainicio    VARCHAR2,
    psfechafin       VARCHAR2
  )

   IS
    CURSOR c_interfaz IS
      SELECT ' ',
             z.cod_empl,
             ' ',
             '000000000',
             SUM(z.val_tota_mnto_fact)
        FROM ret_conso_venta_zona z
       WHERE z.fec_trans >= to_date(psfechainicio, 'DD/MM/YYYY')
         AND z.fec_trans <= to_date(psfechafin, 'DD/MM/YYYY')
         AND z.ind_envia = 'N'
       GROUP BY z.cod_empl;

    TYPE interfazrec IS RECORD(
      blancos        VARCHAR(2),
      codigoplanilla VARCHAR2(10),
      nombre         VARCHAR2(50),
      ceros          VARCHAR2(9),
      importe        ret_conso_venta_zona.val_tota_mnto_fact%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .blancos || ';' || interfazrecord(x).codigoplanilla || ';' || interfazrecord(x)
                     .nombre || ';' || interfazrecord(x).ceros || ';' || interfazrecord(x)
                     .importe;

          utl_file.put_line(v_handle, lslinea);

        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;

    utl_file.fclose(v_handle);

    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);

    UPDATE ret_conso_venta_zona zon
       SET zon.ind_envia = 'S'
     WHERE zon.fec_trans >= to_date(psfechainicio, 'DD/MM/YYYY')
       AND zon.fec_trans <= to_date(psfechafin, 'DD/MM/YYYY')
       AND zon.ind_envia = 'N';

    RETURN;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR SIC_PR_VENTA_BASE_CONSUL: ' ||
                              ls_sqlerrm);

  END ret_pr_comis_venta_retail;

  PROCEDURE evi_pr_inter_consu_punta
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2
  ) IS
    CURSOR c_interfaz(pscodigoperiodoatras VARCHAR2) IS
      SELECT pscodigopais AS cod_pais,
             mae_clien.cod_clie,
             inc_concu_param_gener.coiv_oid_conc_ivr AS tipo_puntaje,
             seg_perio_corpo.cod_peri,
             SUM(inc_cuent_corri_punto.num_punt) AS puntos
        FROM inc_cuent_corri_punto,
             mae_clien,
             seg_perio_corpo,
             cra_perio,
             inc_concu_param_gener,
             mae_clien_datos_adici
       WHERE ((inc_cuent_corri_punto.clie_oid_clie = mae_clien.oid_clie) AND
             (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri) AND
             (cra_perio.oid_peri = inc_cuent_corri_punto.perd_oid_peri) AND
             (inc_concu_param_gener.oid_para_gral =
             inc_cuent_corri_punto.copa_oid_para_gral) AND
             (seg_perio_corpo.cod_peri >= pscodigoperiodoatras) AND
             (seg_perio_corpo.cod_peri <= pscodigoperiodo) AND
             (inc_concu_param_gener.coiv_oid_conc_ivr = '1') AND
             (inc_cuent_corri_punto.tmov_oid_tipo_movi <> 2) AND
             (mae_clien_datos_adici.clie_oid_clie = mae_clien.oid_clie) AND
             (mae_clien_datos_adici.esta_oid_esta_clie != 7) AND
             (mae_clien_datos_adici.ind_acti = 1))

       GROUP BY mae_clien.cod_clie,
                inc_concu_param_gener.coiv_oid_conc_ivr,
                seg_perio_corpo.cod_peri
      UNION
      SELECT pscodigopais AS cod_pais,
             mae_clien.cod_clie,
             inc_concu_param_gener.coiv_oid_conc_ivr AS tipo_puntaje,
             seg_perio_corpo.cod_peri,
             SUM(inc_cuent_corri_punto.num_punt) AS puntos
        FROM inc_cuent_corri_punto,
             mae_clien,
             seg_perio_corpo,
             cra_perio,
             inc_concu_param_gener,
             mae_clien_datos_adici
       WHERE ((inc_cuent_corri_punto.clie_oid_clie = mae_clien.oid_clie) AND
             (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri) AND
             (cra_perio.oid_peri = inc_cuent_corri_punto.perd_oid_peri) AND
             (inc_concu_param_gener.oid_para_gral =
             inc_cuent_corri_punto.copa_oid_para_gral) AND
             (seg_perio_corpo.cod_peri >= pscodigoperiodoatras) AND
             (seg_perio_corpo.cod_peri <= pscodigoperiodo) AND
             (inc_concu_param_gener.coiv_oid_conc_ivr = '2') AND
             (inc_cuent_corri_punto.tmov_oid_tipo_movi <> 2) AND
             (mae_clien_datos_adici.clie_oid_clie = mae_clien.oid_clie) AND
             (mae_clien_datos_adici.esta_oid_esta_clie != 7) AND
             (mae_clien_datos_adici.ind_acti = 1))
       GROUP BY mae_clien.cod_clie,
                inc_concu_param_gener.coiv_oid_conc_ivr,
                seg_perio_corpo.cod_peri;

    TYPE interfazrec IS RECORD(
      codigopais    seg_pais.cod_pais%TYPE,
      codigocliente mae_clien.cod_clie%TYPE,
      tipopuntaje   inc_concu_param_gener.coiv_oid_conc_ivr%TYPE,
      codigoperiodo seg_perio_corpo.cod_peri%TYPE,
      puntos        inc_cuent_corri_punto.num_punt%TYPE);

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    w_filas    NUMBER := 1000;
    v_handle   utl_file.file_type;

    lslinea VARCHAR2(1000);

    lsnombrearchivo VARCHAR2(50);

    lscodigoperiodoatras VARCHAR2(6);
    lbabrirutlfile       BOOLEAN;

  BEGIN
    lscodigoperiodoatras := gen_fn_perio_atras(pscodigoperiodo, 4);

    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(lscodigoperiodoatras);
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
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                     .tipopuntaje || ';' || interfazrecord(x).codigoperiodo || ';' || interfazrecord(x)
                     .puntos;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
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
                              'ERROR EVI_PR_INTER_CONSU_PUNTA: ' ||
                              ls_sqlerrm);

  END evi_pr_inter_consu_punta;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;

    lslineacabecera    VARCHAR2(4000);
    lsnombrearchivo    VARCHAR2(50);
    lsnombrearchivozip VARCHAR2(50);
    lnmaximo           NUMBER;
    lbexito            BOOLEAN;
  BEGIN

    dbms_application_info.set_module(module_name => pscodigointerfaz,
                                     action_name => psnombrearchivo);

    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(pscodigopais,
                                                              pscodigosistema,
                                                              pscodigointerfaz);

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

    /* Obtiene campos de la cabecera */
    lslineacabecera := gen_pkg_inter_archi.gen_fn_obtie_cabec_archi(pscodigopais,
                                                                    pscodigosistema,
                                                                    pscodigointerfaz);

    /* Generando Nombre del Archivo TMP */
    lsnombrearchivo    := psnombrearchivo || '.TMP';
    lsnombrearchivozip := psnombrearchivo || '.ZIP';
    lnmaximo           := 10;

    -- Eliminamos el archivo TEMPORAL
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    -- Eliminamos el archivo ZIPIADO
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    /* Generando Archivo de Texto (Cabecera) */
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          pnhandle := utl_file.fopen('SICC_DIR', lsnombrearchivo, 'W');
          utl_file.put_line(pnhandle, lslineacabecera);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
            sys.dbms_lock.sleep(1);
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        utl_file.fclose_all;
        pnhandle := utl_file.fopen('SICC_DIR', lsnombrearchivo, 'W');
        utl_file.put_line(pnhandle, lslineacabecera);

      END IF;
    END LOOP;

    psdirtempo         := lsdirtempo;
    psnombrearchivotmp := lsnombrearchivo;

  EXCEPTION
    WHEN OTHERS THEN
      utl_file.fclose_all;
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_PR_INICI_INTER: ' || ls_sqlerrm);
  END gen_pr_inici_inter;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
                      de Lectura
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoRetorno Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_lectu
  (
    pscodigopais           VARCHAR2,
    pscodigosistema        VARCHAR2,
    pscodigointerfaz       VARCHAR2,
    psnombrearchivo        VARCHAR2,
    psextensionarchivo     VARCHAR2,
    psdirtempo             OUT VARCHAR2,
    psnombrearchivoretorno OUT VARCHAR2,
    pnhandle               OUT utl_file.file_type,
    pncantidadcaracteres   NUMBER := 0
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;

    lsnombrearchivo VARCHAR2(50);

    lbexito  BOOLEAN;
    lnmaximo NUMBER;

    lsdirectoryid VARCHAR2(8) := REPLACE(pscodigointerfaz, '-', '');

  BEGIN
    dbms_application_info.set_module(module_name => pscodigointerfaz,
                                     action_name => psnombrearchivo);

    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_temp(pscodigopais,
                                                              pscodigosistema,
                                                              pscodigointerfaz);

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc(lsdirectoryid, lsdirtempo);

    /* Generando Nombre del Archivo */
     IF(psextensionarchivo IS NOT NULL ) THEN
      lsnombrearchivo := psnombrearchivo || '.' || psextensionarchivo;
     ELSE
      lsnombrearchivo := psnombrearchivo;
     END if;
    /* Generando Archivo de Texto (Cabecera) */
    lnmaximo := 11;
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          IF (pncantidadcaracteres = 0) THEN
            pnhandle := utl_file.fopen(lsdirectoryid, lsnombrearchivo, 'r');
          ELSE
            pnhandle := utl_file.fopen(lsdirectoryid,
                                       lsnombrearchivo,
                                       'r',
                                       pncantidadcaracteres);
          END IF;
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
            sys.dbms_lock.sleep(2);

        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        utl_file.fclose_all;
        IF (pncantidadcaracteres = 0) THEN
          pnhandle := utl_file.fopen(lsdirectoryid, lsnombrearchivo, 'r');
        ELSE
          pnhandle := utl_file.fopen(lsdirectoryid,
                                     lsnombrearchivo,
                                     'r',
                                     pncantidadcaracteres);
        END IF;
      END IF;
    END LOOP;
    psdirtempo             := lsdirtempo;
    psnombrearchivoretorno := lsnombrearchivo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_PR_INICI_INTER_LECTU: ' ||
                              ls_sqlerrm);
  END gen_pr_inici_inter_lectu;

  /***************************************************************************
  Descripcion       : Procedimiento final para la generación de Interfaces
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCarpetaUtl       Nombre de Objeto carpeta
     psDirTempoZip      Nombre del Directorio donde se encuentra el zipiado
     psNombreArchivoSinExtension    Nombre del Archivo sin extension
     psNombreArchivo    Nombre del Archivo de salida Temporal

  ***************************************************************************/
  PROCEDURE gen_pr_final_inter
  (
    pscarpetautl                VARCHAR2,
    psdirtempozip               VARCHAR2,
    psnombrearchivosinextension VARCHAR2,
    psnombrearchivo             VARCHAR2
  ) IS
    lbexito  BOOLEAN;
    lnmaximo NUMBER;
  BEGIN
    lnmaximo := 11;

    -- Comprimimos el archivo
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          gen_pkg_inter_archi.gen_pr_compr_zip(psdirtempozip,
                                               psnombrearchivosinextension);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        gen_pkg_inter_archi.gen_pr_compr_zip(psdirtempozip,
                                             psnombrearchivosinextension);
      END IF;
    END LOOP;

    -- Eliminamos el archivo temporal
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove(pscarpetautl, psnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove(pscarpetautl, psnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;
    dbms_application_info.set_module(module_name => NULL,
                                     action_name => NULL);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_PR_FINAL_INTER: ' || ls_sqlerrm);
  END gen_pr_final_inter;

  /***************************************************************************
  Descripcion       : Obtiene la fecha de la ultima ejecucion exitosa de la interfaz
  Fecha Creacion    : 01/10/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_ultim_ejecu_exito
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2
  ) RETURN DATE IS
    ldresult bas_histo_lotes.fec_ipro%TYPE;
  BEGIN

    SELECT MAX(fec_fpro)
      INTO ldresult
      FROM bas_histo_lotes b
     WHERE b.pais_cod_pais = pscodigopais -- psCodigoPais
       AND b.sist_cod_sist = pscodigosistema --psCodigoSistema
       AND b.inte_cod_inte = pscodigointerfaz --psCodigoInterfaz
       AND b.ind_loer = 'N'
       AND b.fec_fpro IS NOT NULL;

    RETURN ldresult;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_FN_ULTIM_EJECU_EXITO: ' ||
                              ls_sqlerrm);
  END gen_fn_ultim_ejecu_exito;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Reportes en
                      Oracle
  Fecha Creacion    : 15/08/2010
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psNombreArchivo    Nombre del Archivo sin extension
     psExtensionArchivo Extension del Archivo
     psLineaCabecera    Linea de Cabecera del archivo
     psDirTempo         Directorio en donde se grabara el archivo
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_repor_oracl
  (
    pscodigopais       VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psextensionarchivo VARCHAR2,
    pslineacabecera    VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;

    lsnombrearchivo VARCHAR2(50);

    lnmaximo NUMBER;
    lbexito  BOOLEAN;
  BEGIN
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    SELECT a.dir_repo INTO lsdirtempo FROM bas_repor_param_gener a;

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

    /* Generando Nombre del Archivo TMP */
    lsnombrearchivo := psnombrearchivo || psextensionarchivo;
    lnmaximo        := 10;

    -- Eliminamos el archivo TEMPORAL
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    /* Generando Archivo de Texto (Cabecera) */
    pnhandle := utl_file.fopen('SICC_DIR', lsnombrearchivo, 'W', 5000);
    IF pslineacabecera IS NOT NULL THEN
      utl_file.put_line(pnhandle, pslineacabecera);
    END IF;
    psdirtempo := lsdirtempo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_PR_INICI_REPOR_ORACL: ' ||
                              ls_sqlerrm);
  END gen_pr_inici_repor_oracl;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Reportes en
                      Oracle
  Fecha Creacion    : 15/08/2010
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psnombreReporte    Nombre del Reporte para buscar la ruta donde grabar que debe
                        estar registrado en la tabla bas_repor_param
     psNombreArchivo    Nombre del Archivo sin extension
     psExtensionArchivo Extension del Archivo
     psLineaCabecera    Linea de Cabecera del archivo
     psDirTempo         Directorio en donde se grabara el archivo
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_repor_oracl
  (
    pscodigopais       VARCHAR2,
    psnombrereporte    VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psextensionarchivo VARCHAR2,
    pslineacabecera    VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;

    lsnombrearchivo VARCHAR2(50);

    lnmaximo NUMBER;
    lbexito  BOOLEAN;
  BEGIN
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    BEGIN
      SELECT x.val_ruta_grab
        INTO lsdirtempo
        FROM bas_repor_param x
       WHERE x.pais_cod_pais = pscodigopais
         AND x.nom_repo = psnombrereporte;
    EXCEPTION
      WHEN no_data_found THEN
        SELECT a.dir_repo INTO lsdirtempo FROM bas_repor_param_gener a;
    END;

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

    /* Generando Nombre del Archivo TMP */
    lsnombrearchivo := psnombrearchivo || psextensionarchivo;
    lnmaximo        := 10;

    -- Eliminamos el archivo TEMPORAL
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    /* Generando Archivo de Texto (Cabecera) */
    pnhandle := utl_file.fopen('SICC_DIR', lsnombrearchivo, 'W');
    IF pslineacabecera IS NOT NULL THEN
      utl_file.put_line(pnhandle, pslineacabecera);
    END IF;
    psdirtempo := lsdirtempo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_PR_INICI_REPOR_ORACL: ' ||
                              ls_sqlerrm);
  END gen_pr_inici_repor_oracl;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 08/04/2013
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_maxca
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pnmaximocaracter   VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;

    lslineacabecera    VARCHAR2(1000);
    lsnombrearchivo    VARCHAR2(50);
    lsnombrearchivozip VARCHAR2(50);
    lnmaximo           NUMBER;
    lbexito            BOOLEAN;
  BEGIN

    dbms_application_info.set_module(module_name => pscodigointerfaz,
                                     action_name => psnombrearchivo);
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(pscodigopais,
                                                              pscodigosistema,
                                                              pscodigointerfaz);

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

    /* Obtiene campos de la cabecera */
    lslineacabecera := gen_pkg_inter_archi.gen_fn_obtie_cabec_archi(pscodigopais,
                                                                    pscodigosistema,
                                                                    pscodigointerfaz);

    /* Generando Nombre del Archivo TMP */
    lsnombrearchivo    := psnombrearchivo || '.TMP';
    lsnombrearchivozip := psnombrearchivo || '.ZIP';
    lnmaximo           := 10;

    -- Eliminamos el archivo TEMPORAL
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    -- Eliminamos el archivo ZIPIADO
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    /* Generando Archivo de Texto (Cabecera) */
    pnhandle := utl_file.fopen('SICC_DIR',
                               lsnombrearchivo,
                               'W',
                               pnmaximocaracter);
    utl_file.put_line(pnhandle, lslineacabecera);
    psdirtempo         := lsdirtempo;
    psnombrearchivotmp := lsnombrearchivo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_PR_INICI_INTER: ' || ls_sqlerrm);
  END gen_pr_inici_inter_maxca;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 08/04/2013
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_maxca01
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pnmaximocaracter   VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;

    lslineacabecera    VARCHAR2(1000);
    lsnombrearchivo    VARCHAR2(50);
    lsnombrearchivozip VARCHAR2(50);
    lnmaximo           NUMBER;
    lbexito            BOOLEAN;
  BEGIN

    dbms_application_info.set_module(module_name => pscodigointerfaz,
                                     action_name => psnombrearchivo);
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(pscodigopais,
                                                              pscodigosistema,
                                                              pscodigointerfaz);

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

    /* Generando Nombre del Archivo TMP */
    lsnombrearchivo    := psnombrearchivo || '.TMP';
    lsnombrearchivozip := psnombrearchivo || '.ZIP';
    lnmaximo           := 10;

    -- Eliminamos el archivo TEMPORAL
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    -- Eliminamos el archivo ZIPIADO
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    /* Generando Archivo de Texto (Cabecera) */
    pnhandle           := utl_file.fopen('SICC_DIR',
                                         lsnombrearchivo,
                                         'W',
                                         pnmaximocaracter);
    psdirtempo         := lsdirtempo;
    psnombrearchivotmp := lsnombrearchivo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR gen_pr_inici_inter_maxca01: ' ||
                              ls_sqlerrm);
  END gen_pr_inici_inter_maxca01;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
                       Considera si se crea o no la cabecera del archivo
  Fecha Creacion    : 22/08/2012
  Autor             : Ivan Tocto Jaimes
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     pbFlagCabecera     Flag Cabecera
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pbflagcabecera     BOOLEAN,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;

    lslineacabecera    VARCHAR2(4000);
    lsnombrearchivo    VARCHAR2(50);
    lsnombrearchivozip VARCHAR2(50);
    lnmaximo           NUMBER;
    lbexito            BOOLEAN;
  BEGIN
    dbms_application_info.set_module(module_name => pscodigointerfaz,
                                     action_name => psnombrearchivo);
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(pscodigopais,
                                                              pscodigosistema,
                                                              pscodigointerfaz);

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

    /* Obtiene campos de la cabecera */
    lslineacabecera := gen_pkg_inter_archi.gen_fn_obtie_cabec_archi(pscodigopais,
                                                                    pscodigosistema,
                                                                    pscodigointerfaz);

    /* Generando Nombre del Archivo TMP */
    lsnombrearchivo    := psnombrearchivo || '.TMP';
    lsnombrearchivozip := psnombrearchivo || '.ZIP';
    lnmaximo           := 10;

    -- Eliminamos el archivo TEMPORAL
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    -- Eliminamos el archivo ZIPIADO
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    /* Generando Archivo de Texto (Cabecera) */
    pnhandle := utl_file.fopen('SICC_DIR', lsnombrearchivo, 'W');

    IF pbflagcabecera THEN
      utl_file.put_line(pnhandle, lslineacabecera);
    END IF;

    psdirtempo         := lsdirtempo;
    psnombrearchivotmp := lsnombrearchivo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_PR_INICI_INTER: ' || ls_sqlerrm);
  END gen_pr_inici_inter;
  
  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter02
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type,
    pncantidadcaracteres   NUMBER := 0
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;

    lslineacabecera    VARCHAR2(4000);
    lsnombrearchivo    VARCHAR2(50);
    lsnombrearchivozip VARCHAR2(50);
    lnmaximo           NUMBER;
    lbexito            BOOLEAN;
  BEGIN

    dbms_application_info.set_module(module_name => pscodigointerfaz,
                                     action_name => psnombrearchivo);

    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(pscodigopais,
                                                              pscodigosistema,
                                                              pscodigointerfaz);

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

    /* Obtiene campos de la cabecera */
    lslineacabecera := gen_pkg_inter_archi.gen_fn_obtie_cabec_archi(pscodigopais,
                                                                    pscodigosistema,
                                                                    pscodigointerfaz);

    /* Generando Nombre del Archivo TMP */
    lsnombrearchivo    := psnombrearchivo || '.TMP';
    lsnombrearchivozip := psnombrearchivo || '.ZIP';
    lnmaximo           := 10;

    -- Eliminamos el archivo TEMPORAL
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    -- Eliminamos el archivo ZIPIADO
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    /* Generando Archivo de Texto (Cabecera) */
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          IF (pncantidadcaracteres = 0) THEN
             pnhandle := utl_file.fopen('SICC_DIR', lsnombrearchivo, 'W');
          ELSE
             pnhandle := utl_file.fopen('SICC_DIR', lsnombrearchivo, 'W', pncantidadcaracteres);
          END IF;
          utl_file.put_line(pnhandle, lslineacabecera);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
            sys.dbms_lock.sleep(1);
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        utl_file.fclose_all;
        IF (pncantidadcaracteres = 0) THEN
           pnhandle := utl_file.fopen('SICC_DIR', lsnombrearchivo, 'W');
        ELSE
           pnhandle := utl_file.fopen('SICC_DIR', lsnombrearchivo, 'W', pncantidadcaracteres);
        END IF;
        utl_file.put_line(pnhandle, lslineacabecera);

      END IF;
    END LOOP;

    psdirtempo         := lsdirtempo;
    psnombrearchivotmp := lsnombrearchivo;

  EXCEPTION
    WHEN OTHERS THEN
      utl_file.fclose_all;
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_PR_INICI_INTER: ' || ls_sqlerrm);
  END gen_pr_inici_inter02;

  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
  Fecha Creacion    : 24/05/2013
  Autor             : Ivan Tocto
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     pbFlagCabecera     Flag para generar o no la cabecera
     psNombreArchivoTmp Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_maxca
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    pnmaximocaracter   VARCHAR2,
    pbflagcabecera     BOOLEAN,
    psdirtempo         OUT VARCHAR2,
    psnombrearchivotmp OUT VARCHAR2,
    pnhandle           OUT utl_file.file_type
  ) IS
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;

    lslineacabecera    VARCHAR2(1000);
    lsnombrearchivo    VARCHAR2(50);
    lsnombrearchivozip VARCHAR2(50);
    lnmaximo           NUMBER;
    lbexito            BOOLEAN;
  BEGIN
    dbms_application_info.set_module(module_name => pscodigointerfaz,
                                     action_name => psnombrearchivo);
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    lsdirtempo := gen_pkg_inter_archi.gen_fn_obtie_direc_proc(pscodigopais,
                                                              pscodigosistema,
                                                              pscodigointerfaz);

    /* Creando directorio a crear para el UTL_FILE */
    gen_pkg_inter_archi.gen_pr_crea_direc('SICC_DIR', lsdirtempo);

    /* Obtiene campos de la cabecera */
    lslineacabecera := gen_pkg_inter_archi.gen_fn_obtie_cabec_archi(pscodigopais,
                                                                    pscodigosistema,
                                                                    pscodigointerfaz);

    /* Generando Nombre del Archivo TMP */
    lsnombrearchivo    := psnombrearchivo || '.TMP';
    lsnombrearchivozip := psnombrearchivo || '.ZIP';
    lnmaximo           := 10;

    -- Eliminamos el archivo TEMPORAL
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivo);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    -- Eliminamos el archivo ZIPIADO
    FOR i IN 1 .. lnmaximo
    LOOP
      lbexito := TRUE;
      IF i < lnmaximo THEN
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := FALSE;
        END;
        IF lbexito THEN
          EXIT;
        END IF;
      ELSE
        BEGIN
          utl_file.fremove('SICC_DIR', lsnombrearchivozip);
        EXCEPTION
          WHEN OTHERS THEN
            lbexito := TRUE;
        END;
      END IF;
    END LOOP;

    /* Generando Archivo de Texto (Cabecera) */
    pnhandle := utl_file.fopen('SICC_DIR',
                               lsnombrearchivo,
                               'W',
                               pnmaximocaracter);

    IF pbflagcabecera THEN
      utl_file.put_line(pnhandle, lslineacabecera);
    END IF;

    psdirtempo         := lsdirtempo;
    psnombrearchivotmp := lsnombrearchivo;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'GEN_PR_INICI_INTER_MAXCA: ' || ls_sqlerrm);
  END gen_pr_inici_inter_maxca;

  /***************************************************************************
  Descripcion : Funcion para cortar una cadena por un delimitador
                devolviendo un arreglo
  Fecha Creacion : 03/08/2013
  Autor : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_estru_archi
  (
    pscodigopais     IN VARCHAR2,
    pscodigointerfaz IN VARCHAR2,
    t_estructura     OUT t_all_estru_archi
  ) AS
  BEGIN
    SELECT t_estru_archi(e.pos_camp,
                         i.cod_inte,
                         i.foca_cod_foca,
                         i.tfar_cod_tfar,
                         d.val_deli,
                         e.ide_camp,
                         e.tdat_cod_tdat,
                         e.lon_camp,
                         e.can_deci,
                         fc.val_foca,
                         tf.des_tfar) BULK COLLECT
      INTO t_estructura
      FROM bas_estru_archi      e,
           bas_inter            i,
           bas_delim            d,
           bas_forma_campo      fc,
           bas_tipo_forma_archi tf
     WHERE i.cod_inte = e.inte_cod_inte
       AND i.pais_cod_pais = e.pais_cod_pais
       AND i.deli_cod_deli = d.cod_deli
       AND i.foca_cod_foca = fc.cod_foca(+)
       AND i.tfar_cod_tfar = tf.cod_tfar(+)
       AND i.pais_cod_pais = pscodigopais
       AND i.cod_inte = pscodigointerfaz
     ORDER BY e.pos_camp;
  END gen_pr_estru_archi;
  /***************************************************************************
  Descripcion : Devuelve el primer campo de una linea basado en la configuracion}
                de la interfaz
  Fecha Creacion : 28/08/2013
  Autor : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_read_campo
  (
    pslinea IN OUT VARCHAR2,
    t_estru IN t_estru_archi,
    pscampo OUT VARCHAR2
  ) AS

    lnposicion NUMBER;
    lsValdeli VARCHAR2(10);
  BEGIN

    IF t_estru.tfar_cod_tfar = '01' THEN
      /*01  LONGITUD FIJA*/
      pscampo := substr(pslinea, 1, t_estru.lon_camp);
      pslinea := substr(pslinea, t_estru.lon_camp + 1);

    ELSIF t_estru.tfar_cod_tfar = '02' THEN
      /*02  CON SEPARADOR*/
      lsValdeli:=t_estru.val_deli;
      if lsValdeli='\t' then 
       lsValdeli :=CHR(9);
      end if;

      lnposicion := instr(pslinea, lsValdeli);

      IF lnposicion = 0 THEN
        pscampo := gen_pkg_gener.gen_fn_reemp_carac_extra(pslinea);
        pslinea := NULL;
      ELSE
        pscampo := substr(pslinea, 1, lnposicion - 1);
        pslinea := substr(pslinea, lnposicion + 1);
      END IF;
    END IF;
  END gen_pr_read_campo;
  /***************************************************************************
  Descripcion       : Procedimiento inicial para la generación de Interfaces
                      de Lectura
  Fecha Creacion    : 15/08/2008
  Autor             : Carlos Bazalar
  Parametros        :
     psCodigoPais       Codigo de Pais
     psCodigoSistema    Codigo de Sistema
     psCodigoInterfaz   Codigo de Interfaz
     psNombreArchivo    Nombre del Archivo sin extension
     psNombreArchivoRetorno Nombre del Archivo TP (Variable de salida)
     pnHandle           Puntero al archivo creado por utl_file
     t_estructura     Arreglo con estructura de archivo,
     pncantidadcaracteres   numero de caracteres ara lectura
  ***************************************************************************/
  PROCEDURE gen_pr_inici_inter_lectu
  (
    pscodigopais           VARCHAR2,
    pscodigosistema        VARCHAR2,
    pscodigointerfaz       VARCHAR2,
    psnombrearchivo        VARCHAR2,
    psextensionarchivo     VARCHAR2,
    psdirtempo             OUT VARCHAR2,
    psnombrearchivoretorno OUT VARCHAR2,
    pnhandle               OUT utl_file.file_type,
    t_estructura           OUT t_all_estru_archi,
    pncantidadcaracteres   NUMBER := 0
  ) IS

  BEGIN
    gen_pr_inici_inter_lectu(pscodigopais,
                             pscodigosistema,
                             pscodigointerfaz,
                             psnombrearchivo,
                             psextensionarchivo,
                             psdirtempo,
                             psnombrearchivoretorno,
                             pnhandle,
                             pncantidadcaracteres);

    gen_pr_estru_archi(pscodigopais, pscodigointerfaz, t_estructura);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR GEN_PR_INICI_INTER_LECTU: ' ||
                              ls_sqlerrm);
  END gen_pr_inici_inter_lectu;
END gen_pkg_inter_archi;
/
