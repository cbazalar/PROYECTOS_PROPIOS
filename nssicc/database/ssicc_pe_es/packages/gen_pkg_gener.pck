CREATE OR REPLACE PACKAGE "GEN_PKG_GENER" IS
  /* Declaracion de Tipos */
  TYPE tipocursor IS REF CURSOR;
  TYPE tregobtenerdsctovarios IS RECORD(
    valorespecidscto      VARCHAR2(10),
    indicadorcomisionable NUMBER,
    indicadorcomisionadic NUMBER);

  TYPE tregresponsableuniadm IS RECORD(
    oid_clie mae_clien.oid_clie%TYPE,
    cod_clie mae_clien.cod_clie%TYPE,
    val_ape1 mae_clien.val_ape1%TYPE,
    val_ape2 mae_clien.val_ape2%TYPE,
    val_nom1 mae_clien.val_nom1%TYPE,
    val_nom2 mae_clien.val_nom2%TYPE);
  TYPE tablaresponsableuniadm IS TABLE OF tregresponsableuniadm;

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  w_filas    NUMBER := 1000;

  /* Declaracion de procedures */
  /**************************************************************************
  Descripcion : Devuelve Fecha sin considerar
  la Hora en formato dd/mm/yyyy
  Fecha Creacion : 04/12/2006
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_fecha_sin_hora(vdfecha DATE) RETURN DATE;
  /**************************************************************************
  Descripcion : Devuelve Fecha Actual del Servidor sin considerar
  la Hora en formato dd/mm/yyyy
  Fecha Creacion : 04/12/2006
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_fecha_actual_sin_hora RETURN DATE;
  /**************************************************************************
  Descripcion : Ejecuta un SQL Dinamico
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psSentencia : Sentencia SQL a ejecutar
  Autor : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_ejec_sql_dinam(pssentencia VARCHAR2);
  /**************************************************************************
  Descripcion : Devuelve status del estado de proceso batch
  Fecha Creacion : 30/01/2007
  Parametros Entrada :
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve ""
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_status_estad_batch
  (
    pscodigoestado      VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve Id de Periodo
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_perio
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Periodo de TABLA CRA_PERIO
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  pnidmarca    : Oid de Marca
  pnidcanal    : Oid de Canal
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cra_perio
  (
    pscodperiodo        VARCHAR2,
    pnidmarca           NUMBER,
    pnidcanal           NUMBER,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /***********************************************************************************
  Descripcion : Devuelve Id de Periodo de TABLA CRA_PERIO para marca T Canal VD
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cra_perio2
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

 /***********************************************************************************
  Descripcion : Devuelve Codigo de Periodo(VARCHAR2(6)) de TABLA SEG_PERIO_CORPO
  Fecha Creacion : 25/07/2013
  Parametros Entrada :
  psOidPeriodo : Oid del Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Juan Altamirano
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cra_perio3
  (
    psOidPeriodo        NUMBER,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion : Devuelve Id de Periodo de TABLA INC_CONCU_PARAM_GENER
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodConcurso : Codigo de Concurso
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_para_gral
  (
    pscodconcurso       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Periodo Actual en base al Pais, Marca y Canal
  Fecha Creacion : 12/02/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_perio_actu
  (
    pnidpais   NUMBER,
    pscodmarca VARCHAR2,
    pnidcanal  NUMBER
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, Marca y Canal, fecha
  Devuelve Periodo MENOR si hay cruce de campa?a
  Fecha Creacion : 30/05/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_perio_fecha
  (
    pscodpais  VARCHAR2,
    pscodmarca VARCHAR2,
    pscodcanal VARCHAR2,
    pdfecha    DATE
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, Marca y Canal, fecha
  Si hay cruce y esta en la campa?a activa: Mostrar la campa?a activa.
  Si hay cruce y no esta en campa?a activa: Mostrar la mas cercana.
  Si no hay campa?a para la fecha solicitada: Mostrar Mensaje "Fecha sin campa?a."
  Para ser utilizada desde Java, con el ultimo parametro(fecha) en String
  Fecha Creacion : 11/11/2013
  Autor : Juan Altamirano
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_perio_fecha2
  (
    pscodpais  VARCHAR2,
    pscodmarca VARCHAR2,
    pscodcanal VARCHAR2,
    pdfecha    VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, fecha para FOX
  Si hay cruce y esta en la campa?a activa: Mostrar la campa?a activa.
  Si hay cruce y no esta en campa?a activa: Mostrar la mas cercana.
  Si no hay campa?a para la fecha solicitada: Mostrar Mensaje "Fecha sin campa?a."
  Para ser utilizada desde Java, con el parametro(fecha) en String
  Fecha Creacion : 11/12/2013
  Autor : Juan Altamirano
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_perio_fecfox
  (
    pscodpais  VARCHAR2,
    pdfecha    VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, Marca y Canal, fecha
  Devuelve Periodo MAYOR si hay cruce de campa?a
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devue_perio_mayor_fecha
  (
    pscodpais  VARCHAR2,
    pscodmarca VARCHAR2,
    pscodcanal VARCHAR2,
    pdfecha    DATE
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Verifica si hay Cruce de Campa?a en base al Pais, Marca,
  Canal y fecha
  Retorna:
  1 Existe cruce de campa?a
  0 No existe Cruce de Campa?a
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_verif_cruce_campa
  (
    pscodpais  VARCHAR2,
    pscodmarca VARCHAR2,
    pscodcanal VARCHAR2,
    pdfecha    DATE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion : Devuelve Id de Marca
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  vsCodPais : Codigo de Marca
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_marca
  (
    pscodmarca          VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion : Devuelve Id de Marca Produtco
  Fecha Creacion : 13/02/2007
  Parametros Entrada :
  vsCodPais : Codigo de Marca Producto
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_marca_produ
  (
    pscodmarcaprodu     VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Canal
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  vsCodPais : Codigo de Canal
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_canal
  (
    pscodcanal          VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Cliente
  Fecha Creacion : 30/01/2007
  Parametros Entrada :
  vsCodCliente : Codigo de Cliente
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cliente
  (
    vscodcliente        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Sub Gerencia
  Fecha Creacion : 29/01/2007
  Parametros Entrada :
  vsCodPais : Codigo de Sub Gerencia
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_sub_geren
  (
    pscodsubgerencia    VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Negocio
  Fecha Creacion : 26/01/2007
  Parametros Entrada :
  vsCodPais : Codigo de Negocio
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_negocio
  (
    pscodcanal          VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Unidad de Negocio
  Fecha Creacion : 26/01/2007
  Parametros Entrada :
  vsCodPais : Codigo de Unidad de Negocio
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_unid_nego
  (
    pscodcanal          VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Sociedad
  Fecha Creacion : 05/01/2007
  Parametros Entrada :
  psCodSociedad : Codigo de Sociedad
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_socie
  (
    pscodsociedad       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Pais
  Fecha Creacion : 18/09/2006
  Parametros Entrada :
  vsCodPais : Codigo de Pais
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_pais
  (
    vscodpais           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Producto de mae_produ
  Fecha Creacion : 09/07/2007
  Parametros Entrada :
  psCodPeriodo : Codigo de Producto
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_produ
  (
    pscodproducto       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Proceso
  Fecha Creacion : 18/09/2006
  Parametros Entrada :
  vsCodPais : Codigo de Pais
  vsCodProd : Codigo de Proceso
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_proceso
  (
    vnidpais            NUMBER,
    vscodproc           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Region
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_region
  (
    pscodpais           VARCHAR2,
    pscodmarca          VARCHAR2,
    pscodcanal          VARCHAR2,
    pscodregion         VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Region
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_region
  (
    pnoidpais           NUMBER,
    pnoidmarca          NUMBER,
    pnoidcanal          NUMBER,
    pscodregion         VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Id de Zona
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_zona
  (
    pscodpais           VARCHAR2,
    pscodmarca          VARCHAR2,
    pscodcanal          VARCHAR2,
    pscodregion         VARCHAR2,
    pscodzona           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion : Devuelve Id del Almacen Consultado
  Fecha Creacion : 08/03/2007
  Autor : Jose Nunez Mori
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_almac
  (
    pscodalmac bel_almac.cod_alma%TYPE,
    pnidpais   seg_pais.oid_pais%TYPE
  ) RETURN NUMBER;
  /***************************************************************************
  Descripcion : Devuelve La descripcion de un gerente
  de zona o de region segun lo especificado
  en el reporte de MAV
  Fecha Creacion : 23/04/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devue_descr_geren(oidcliente NUMBER) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve La descripcion de una actividad dado un oid de
  solicitud de Ped_Solic_Cabec
  Fecha Creacion : 08/03/2007
  Parametros Entrada :
  psOidSoca : Oid de la Solicitud
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devue_descr_mav_activ
  (
    psoidsoca           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve La descripcion del Tipo de Solicitud
  Fecha Creacion : 23/08/2007
  Parametros Entrada :
  psOidSoca : Oid de la Solicitud
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devue_descr_tipos_solic
  (
    psoidtiposol        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve Codigo de Periodo con el OID del CRA_PERIO
  Fecha Creacion : 16/01/2007
  Parametros Entrada :
  psCodPeriodo : Codigo de Pais
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_des_perio
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve la Subgerencia de Venta la descripcion corta
  Fecha Creacion : 16/02/2007
  Parametros Entrada :
  psOidSubGerencia : EL OID de la SubGerencia
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_des_sub_geren
  (
    psoidsubgerencia    VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve la Descripcion de la Zona a partir del codigo
  de la subgernecia
  Fecha Creacion : 13/04/2007
  Parametros Entrada :
  psOidSeccion : EL OID de la Seccion
  psTipo : DES Descripcion de la Zona
  COD Codigo de la Zona
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_des_zona_secc
  (
    psoidseccion        VARCHAR2,
    pstipo              VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve la El codigo de la subgerencia de Venta
  Fecha Creacion : 16/02/2007
  Parametros Entrada :
  psOidSubGerencia : EL OID de la SubGerencia
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_sub_geren
  (
    psoidsubgerencia    VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve la El codigo del cliente
  Fecha Creacion : 16/04/2007
  Parametros Entrada :
  psOidSubGerencia : EL OID del Cliente
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_clie
  (
    psoidcliente        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve Codigo de Operacion Reclamo
  Fecha Creacion : 20/02/2007
  Parametros Entrada :
  psOidOperacion: Oid Operacion
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_oper_recl(psoidoperacion VARCHAR2) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve el Sgte Numero de Lote en base al pais,
  Sistema e Interfaz
  Fecha Creacion : 20/09/2006
  Parametros Entrada :
  psCodigoPais : Codigo de Pais
  psCodigoSistema : Codigo de Sistema
  psCodigoInterfaz : Codigo de Interfaz
  Autor : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_devuelve_num_lote_sgte
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnumerolote     OUT VARCHAR2
  );
  /**************************************************************************
  Descripcion : Devuelve el Sgte Numero de Lote en base al pais,
  Sistema e Interfaz
  Fecha Creacion : 20/09/2006
  Parametros Entrada :
  psCodigoPais : Codigo de Pais
  psCodigoSistema : Codigo de Sistema
  psCodigoInterfaz : Codigo de Interfaz
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_num_lote_sgte
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve la descripcion de las tablas que se encuentran en la
  GEN_I18N_SICC_COMUN las mas empleadas en los reportes y otras
  tablas comunes como pais y marca.
  Fecha Creacion : 20/09/2006
  Parametros Entrada :
  psOid :Oid Codigo del cual se requiere la descripcion
  psDescripcion : los valores pueden ser
  * -- Marca : TIPO_MARCA
  * -- Plantilla : TIPO_PLANTILLA
  * -- Pais : PAIS
  * -- Canal : CANAL
  * -- Tipo Calificacion : TIPO_CALIFICACION
  * -- Acceso : ACCESO
  * -- Tipo Venta: TIPO_VENTA
  * -- Tipo Concurso : TIPO_CONCURSO
  psCodigoIso : Idioma del Usuario Logeado
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_descripcion
  (
    psoid         VARCHAR2,
    psdescripcion VARCHAR2,
    pscodigoiso   VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve Fecha Inicial Maxima de la Tabla CRA_CRONO
  basado en el id del periodo y el id de Zona
  Fecha Creacion : 20/09/2006
  Parametros Entrada :
  pnOidPeri : Id de Periodo
  pnOidZona : Id de Zona
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_fecha_ini_maxi_crono
  (
    pnoidperi NUMBER,
    pnoidzona NUMBER
  ) RETURN DATE;
  PROCEDURE gen_pr_actua_valor_saldo_deudo(ps_cod_pais IN VARCHAR);
  FUNCTION gen_fn_calcu_valor_saldo_deudo(ps_cod_clie IN VARCHAR2) RETURN NUMBER;
  FUNCTION gen_fn_exist_clien(pscodcliente VARCHAR2) RETURN BOOLEAN;
  /***************************************************************************
  Descripcion : Devuelve si un cliente es
  gerente de zona o gerente de region
  Retorna el 0 o 1
  Fecha Creacion : 23/04/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_exist_clien_geren(oidcliente NUMBER) RETURN NUMBER;

  FUNCTION gen_fn_perio_valid(pscodperiodo VARCHAR2) RETURN BOOLEAN;
  FUNCTION gen_fn_clien_bloqu
  (
    pscodcliente     VARCHAR2,
    pscodtipobloqueo VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Obtiene el numero segun el tipo de comunicacion
  Fecha Creacion : 21/11/2006
  Parametros Entrada :
  psCodCliente : Codigo del cliente
  psCampo : Nombre del campo a obtener
  COD_REGI: codigo de region
  DES_REGI: descripcion de region
  COD_ZONA: codigo de zona
  DES_ZONA: descripcion de zona
  Autor : Lennon Shimokawa
  ***************************************************************************/
  FUNCTION gen_fn_clien_datos
  (
    pscodcliente VARCHAR2,
    pscampo      VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Obtiene los datos del Cliente segun Codigo
  Fecha Creacion : 18/06/2007
  Parametros Entrada :
  psCodCliente : Codigo del cliente
  psCampo : Nombre del campo a obtener
  COD_REGI: codigo de region
  DES_REGI: descripcion de region
  COD_ZONA: codigo de zona
  DES_ZONA: descripcion de zona
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_clien_datos_codig
  (
    pscodcliente VARCHAR2,
    pscampo      VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Obtiene los datos del Cliente dados el OID
  Fecha Creacion : 21/11/2006
  Parametros Entrada :
  psCodCliente : Codigo del cliente
  psCampo : Nombre del campo a obtener
  COD_REGI: codigo de region
  DES_REGI: descripcion de region
  COD_ZONA: codigo de zona
  DES_ZONA: descripcion de zona
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/

  FUNCTION gen_fn_clien_datos_oid
  (
    psoidcliente VARCHAR2,
    pscampo      VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Obtiene los datos asociados al CCC_BANCO
  Fecha Creacion : 11/01/2007
  Parametros Entrada :
  psCodBanco : Codigo del banco
  psCampo : Nombre del campo a obtener
  OID_BANC: codigo de Banco
  DES_BANC: descripcion de Banco
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_banco_datos
  (
    pscodbanco VARCHAR2,
    pscampo    VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Retorna el valor del tipo de comunicacion del cliente
  en base al codigo de este tipo, pudiendo retornar los
  telefonos (para cuando el segundo parametro es TF Telefono
  Fijo, TT Telefono Trabajo, TM Telefono Movil o Celular)
  o el Email (para cuando el parametro es ML).
  Fecha Creacion : 21/11/2006
  Parametros Entrada :
  psOidCliente : Oid del cliente
  Autor : Lennon Shimokawa
  ***************************************************************************/
  FUNCTION gen_fn_clien_texto_comun
  (
    psoidcliente       NUMBER,
    pstipocomunicacion VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Obtiene el monto minimo de pedido para un determinado
  cliente.
  Fecha Creacion : 21/11/2006
  Parametros Entrada :
  psOidCliente : Oid del cliente
  Autor : Lennon Shimokawa
  ***************************************************************************/
  FUNCTION gen_fn_clien_pedid_monto_minim
  (
    psoidcliente        NUMBER,
    pscodigotipocliente VARCHAR2
  ) RETURN NUMBER;
  /**************************************************************************
   Descripcion : Obtiene el periodo de Ingreso de la consultora,
   Si no lo encuentra en la tabla MAE_CLIEN_PRIME_CONTA
   lo obtiene de la tabla MAE_CLIEN_HISTO_ESTAT
   Fecha Creacion : 19/08/2009
   Autor : Jose Cairampoma
  **************************************************************************/
  FUNCTION gen_fn_clien_perio_ingre(psoidcliente NUMBER) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Obtiene la Descripcion del periodo de ingreso del cliente
  Fecha Creacion : 03/07/2007
  Parametros Entrada :
  psOidCliente : Oid del cliente
  psCampo : Nombre del campo a obtener
  COD_PERI: codigo de la campa?a de ingreso de la tabla
  SEG_PERIO_CORPO
  VAL_NOMB_PERI: descripcion de la campa?a de ingreso
  de la tabla CRA_PERIO
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_clien_perio_ingre_descr
  (
    psoidcliente NUMBER,
    pscampo      VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Obtiene la fecha de egreso de un determinado cliente.
  Fecha Creacion : 24/11/2006
  Parametros Entrada :
  psOidCliente : Oid del cliente
  Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_clien_fecha_egres(psoidcliente NUMBER) RETURN DATE;

  /**************************************************************************
  Descripcion : Obtiene el ultimo periodo de cierre de la region a la que
  pertenece el cliente.
  Fecha Creacion : 10/07/2007
  Parametros Entrada :
  psOidCliente : Oid del cliente
  Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_clien_perio_cierr(psoidcliente NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Obtiene el periodo en el cual un determinado cliente
  paso su ulimo pedido.
  Fecha Creacion : 24/11/2006
  Parametros Entrada :
  psOidCliente : Oid del cliente
  Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_clien_perio_ultim_pedid(psoidcliente NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Obtiene el monto del ultimo pedido de un determinado
  cliente.
  Fecha Creacion : 24/11/2006
  Parametros Entrada :
  psOidCliente : Oid del cliente
  Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_clien_monto_ultim_pedid(psoidcliente NUMBER) RETURN NUMBER;

  FUNCTION gen_fn_clien_monto_ultim_pedid
  (
    psoidcliente          NUMBER,
    psperiodoultimopedido IN VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion : Determina si un cliente es lider o no, retorna 'L' en caso
  de ser lider y 'R' en caso contrario.
  Fecha Creacion : 24/11/2006
  Parametros Entrada :
  psOidCliente : Oid del cliente
  Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_clien_lider
  (
    psoidcliente        NUMBER,
    pscodigotipocliente VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Retorna el valor total de la deuda del cliente, para un cliente
  cuyo oid es pasado como parametro. Esta funcion tiene la
  misma logica que la funcion que recibe el codigo como
  parametro, en lugar del oid.
  Fecha Creacion : 21/03/2007
  Parametros Entrada :
  psOidCliente : Oid del cliente
  Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_clien_saldo_deuda_total(psoidcliente NUMBER) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Retorna el valor total de la deuda del cliente, para un cliente
  cuyo codigo es pasado como parametro. Esta funcion tiene la
  misma logica que la funcion que recibe el oid como parametro,
  en lugar del codigo.
  Fecha Creacion : 21/03/2007
  Parametros Entrada :
  psCodCliente : Codigo del cliente
  Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_clien_saldo_deuda_total(pscodcliente VARCHAR2) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve la descripcion de la estructura geopolita en base al nivel,
  el nivel 1 es el departamente, nivel 2 provicia y nivel 3 distrito.
  Fecha Creacion : 24/11/2006
  Parametros Entrada :
  psOidPais : Oid del pais
  psOidCliente : Oid del cliente
  psNivel : Nivel de la estructura geopolitica
  Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_descr_estru_geopo
  (
    psoidpais    NUMBER,
    psoidcliente NUMBER,
    psnivel      NUMBER
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Te devuelve el indicador de la plantilla concurso
  Fecha Creacion : 23/11/2006
  Parametros Entrada:
  psOid : Codigo de la Plantilla
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_indi_plantilla
  (
    psoid         VARCHAR2,
    psdescripcion VARCHAR2
  ) RETURN NUMBER;
  FUNCTION gen_fn_clien_monto_pedid
  (
    psoidcliente NUMBER,
    psoidperiodo NUMBER
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Devuelve Codigo de Empleado de la Gerente de Zona
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodZona: codigo de Zona
  Autor : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_emp_gzona(pscodzona VARCHAR2) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve Codigo de Resultado de Chequeo
  Fecha Creacion : 30/01/2007
  Parametros Entrada :
  psOidResulChequeo: Oid Resultado Chequeo
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_res_chequ(psoidresulchequeo VARCHAR2) RETURN VARCHAR2;
  /***************************************************************************
  Descripcion : Genera Calendario para el a?o respectivo
  Fecha Creacion : 24/01/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_gener_calen
  (
    pscodigoanno VARCHAR2,
    pscodusuario VARCHAR2,
    pserror      OUT VARCHAR2
  );
  /***************************************************************************
  Descripcion : Recupera Responsables x Unidad administrativa
  Fecha Creacion : 14/11/2006
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION per_fn_recup_respo_uniad
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2,
    pscodseccion VARCHAR2
  ) RETURN tregresponsableuniadm;

  /***************************************************************************
  Descripcion : Recupera Responsables x Unidad administrativa
  Fecha Creacion : 14/11/2006
  psTipo
  C : Devuelve Codigo respectivo del responsable
  N : Devuelve Nombre respectivo del responsable
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION per_fn_recup_respo_uniad
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2,
    pscodseccion VARCHAR2,
    pstipo       VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve el Sgte Numero de Lote en base al pais,
  Sistema e Interfaz
  Fecha Creacion : 20/09/2006
  Parametros Entrada :
  Autor : Mrco Silva
  ***************************************************************************/
  PROCEDURE gen_pr_upd_lote_sgte_mica(pscodigopais VARCHAR2);

  /**************************************************************************
  Descripcion : Obtiene Descuentos Varios
  Fecha Creacion : 01/03/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devue_dscto_vario
  (
    pscodigoperiodo        VARCHAR2,
    pscodigotipocliente    VARCHAR2,
    pscodigosubtipocliente VARCHAR2,
    pscodigotipooferta     VARCHAR2,
    pscodigonegocio        VARCHAR2,
    pscodigounidadnegocio  VARCHAR2,
    pscodigomarcaproducto  VARCHAR2,
    pncomisionable         NUMBER := 1
  ) RETURN tregobtenerdsctovarios;
  /**************************************************************************
  Descripcion : Obtiene El periodo Actual para la fecha de la Base de Datos
  Fecha Creacion : 31/07/2007
  Autor : Marco Agurto
  Parametros : psOidPais : Oid Pais
  psOidCanal: Ois Canal
  psParametro 'M' Minimo
  'X' Maximo
  ***************************************************************************/
  FUNCTION gen_fn_devue_perio_actua
  (
    psoidpais   NUMBER,
    psoidcanal  NUMBER,
    psparametro VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Obtiene Descuento Especifico
  Fecha Creacion : 01/03/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devue_dscto_espec(pnidbase NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Obtiene Valor de Desceunto
  Fecha Creacion : 03/03/2007
  Autor :
  ***************************************************************************/
  FUNCTION gen_fn_devue_dscto_gener
  (
    pnoidperiodo  NUMBER,
    pscodigoventa VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Obtiene Descripcion de las campa?as en la que esta
  fecha ingresada como parametro
  Fecha Creacion : 08/05/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devue_descr_campa_fecha(vsfecha VARCHAR2) RETURN VARCHAR2;
  /***************************************************************************
  Descripcion : Recupera Fechas de Facturacion
  Fecha Creacion : 13/08/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_recup_fecha_activ
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodigozona VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodactiv   VARCHAR2
  ) RETURN DATE;
  /***************************************************************************
  Descripcion : Recupera Fechas de Facturacion
  Fecha Creacion : 13/08/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_recup_fecha_activ
  (
    pnoidpais    NUMBER,
    pnoidmarca   NUMBER,
    pnoidcanal   NUMBER,
    pscodigozona VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodactiv   VARCHAR2
  ) RETURN DATE;
  /**************************************************************************
  Descripcion : Calcula el saldo deudor del cliente todas las deudas
  no solo las vencidas del periodo Siguiente
  Fecha Creacion : 08/08/2007
  Parametros Entrada:
  ps_oid_clie : Oid de cliente
  ldFechaVen : Fecha de Vencimiento
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_calcu_valor_sald_venci
  (
    ps_oid_clie   IN NUMBER,
    ldfechaven    DATE,
    ldfechaactual DATE
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion : Calcula el saldo deudor del cliente restando el valor en cupones,
  considerando todas las deudas no solo las vencidas, (CHAR DECIMAL)
  Fecha Creacion : 02/20/2007
  Parametros Entrada:
  ps_cod_clie : Codigo de cliente
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_calcu_valor_sald_deci(ps_oid_clie IN NUMBER) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Calcula la fecha de vencimiento de un periodo.
  Fecha Creacion : 08/08/2007
  Parametros Entrada:
  ps_cod_clie : Codigo de cliente
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_venci
  (
    pscodzona  VARCHAR2,
    pscodpais  VARCHAR2,
    pscodcanal VARCHAR2,
    pscodmarca VARCHAR2,
    lsperiodo  VARCHAR2
  ) RETURN DATE;

  /**************************************************************************
   Descripcion : Calcula la fecha de vencimiento de un determinado cliente
   y para un periodo determinado, dependiendo de si se haya
   realizado o no el cierre de region.
   Fecha Creacion : 29/10/2007
   Parametros Entrada:
   psCodPais : Codigo de pais
   psCodCanal : Codigo de canal
   psCodMarca : Codigo de marca
   psCodPeriodo : Codigo de periodo
   psCodRegion : Codigo de region
   psCodZona : Codigo de zona
   psOidCliente : OID de cliente
   Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_venci
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2,
    psoidcliente NUMBER
  ) RETURN DATE;

  /**************************************************************************
   Descripcion : Calcula la fecha de vencimiento de un determinado cliente
   y para un periodo determinado, dependiendo de si se haya
   realizado o no el cierre de region.
   Fecha Creacion : 29/10/2007
   Parametros Entrada:
   Parametros Entrada:
   pnoidPais : OID de pais
   pnoidCanal : OID de canal
   pnoidMarca : OID de marca
   pnoidPeriodo : OID de periodo
   psCodRegion : Codigo de region
   psCodZona : Codigo de zona
   psOidCliente : OID de cliente
   Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_venci
  (
    pnoidpais        NUMBER,
    pnoidmarca       NUMBER,
    pnoidcanal       NUMBER,
    pnoidperiodo     NUMBER,
    pscodperiodo     VARCHAR2,
    pscodperiodosgte VARCHAR2,
    pscodregion      VARCHAR2,
    psoidregion      NUMBER,
    pscodzona        VARCHAR2,
    psoidcliente     NUMBER,
    pdfechaactual    DATE
  ) RETURN DATE;

  /**************************************************************************
   Descripcion : Calcula la fecha de vencimiento de un determinada Region Zona
   Fecha Creacion : 12/04/2010
   Autor : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_venci_rezon
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2
  ) RETURN DATE;

  /**************************************************************************
   Descripcion : Calcula el saldo deudor del cliente cuyos montos estan
   vencidos a una fecha determinada, este saldo incluye las
   percepciones.
   Fecha Creacion : 29/10/2007
   Parametros Entrada:
   psOidCliente : Oid de cliente
   pdFechaVencimiento : Fecha de Vencimiento
   Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_calcu_valor_saldo_venci
  (
    psoidcliente       IN NUMBER,
    pdfechavencimiento DATE
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion : Devuelve montos x vencimiento Incluye la Percepcion
  Fecha Creacion : 31/07/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_recup_monto_venci_percp
  (
    poidcliente   NUMBER,
    pdfechainicio DATE := NULL,
    pdfechafin    DATE := NULL,
    psperiodo     VARCHAR2 := NULL
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion : Devuelve oid de mensaje de Tabla MSG_MENSA
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  Parametros :
   psCodMensaje : Codigo de Mensaje
  ***************************************************************************/
  FUNCTION gen_fn_devue_oid_mensa(pscodmensaje VARCHAR2) RETURN NUMBER;

  /***************************************************************************
  Descripcion : Devuelve VALOR DE CAMPO ingresado como parametro
   en la tabla de Mensajes
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  Parametros :
   pnMensaje : ID Mensaje
   psNombreCampo: Nombre de Campo
  ***************************************************************************/
  FUNCTION gen_fn_devue_mensa_datos
  (
    pnidmensaje   NUMBER,
    psnombrecampo VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion : Devuelve SEQUENCIAL SIGUIENTE
   de Tabla MSG_BUZON_MENSA para campo OID_BUZO_MENS
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_buzon_mensa_seque_next RETURN NUMBER;

  /***************************************************************************
  Descripcion : Devuelve SEQUENCIAL SIGUIENTE
   de Tabla MSG_BUZON_MENSA para campo NUM_SECU
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_mensa_nsecu_seque_next RETURN NUMBER;

  /***************************************************************************
  Descripcion : Devuelve la tasa de impuesto por pais
  Fecha Creacion : 06/06/2008
  Autor : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_devue_tasa_impue_pais(pnoidpais NUMBER) RETURN NUMBER;
  /***************************************************************************
  Descripcion : Devuelve el numero de serie del documento Legal
  Fecha Creacion : 06/06/2008
  Autor : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_devue_serie_docum_legal
  (
    pnoidpais          NUMBER,
    pnoidsociedad      NUMBER,
    pnoidsubacceso     NUMBER,
    pnoidtipodocumento NUMBER
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion : Devuelve el OidPeriodo de N campa?as anteriores
  Fecha Creacion : 15/08/2008
  Autor : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_nante_campa
  (
    psoidperiodo        VARCHAR2,
    numcampanhas        NUMBER,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER;

  PROCEDURE pre_pr_elimi_buzon_mensa(codigopais IN VARCHAR2);

  /***************************************************************************
  Descripcion : Devuelve el Oid de Estructura Geopolitica
  Fecha Creacion : 29/12/2008
  Autor : Arturo Blumen
  ***************************************************************************/

  FUNCTION gen_fn_oid_estru_geopo
  (
    pnoidpais    NUMBER,
    pnoidcliente NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion : Obtiene el codigo territorio
  Fecha Creacion : 22/10/2009
  Parametros Entrada :
  psCodCliente : Codigo del cliente
  psCammpanha : Campanha
  Autor : Sergio Buchelli
  ***************************************************************************/
  FUNCTION gen_fn_devue_cod_terri
  (
    pscodcliente VARCHAR2,
    pscampana    VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Trunca una Tabla USANDO PRAGMA TRANSACTION
  Fecha Creacion : 30/10/2009
  Parametros Entrada :
  Autor : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_trunc_table(p_nom_tabl IN VARCHAR2);
  /**************************************************************************
  Descripcion : Ejecuta Estadisticas USANDO PRAGMA TRANSACTION
  Fecha Creacion : 30/10/2009
  Parametros Entrada :
  Autor : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_gener_stats
  (
    p_nom_tabl IN VARCHAR2,
    p_user     IN VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Devuelve SEQUENCIAL SIGUIENTE
                      de Tabla MAE_CLIEN_BLOQU para campo OID_BLOQ
  Fecha Creacion    : 06/05/2008
  Autor             : Sergio Buchelli
  ***************************************************************************/
  FUNCTION gen_fn_clien_bloqu_seque_next RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Inserta en la tabla de LOG el Error producido en el Cursor
                      Para ello debe indicarse nombre del cursor y la iteracion en
                      donde ha generado error el proceso
  Fecha Creacion    : 02/03/2010
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_inser_error_bdato_ssicc
  (
    p_nom_paquete       VARCHAR2,
    p_nom_procedure     VARCHAR2,
    p_nom_cursor        VARCHAR2,
    p_num_item_pagina   NUMBER,
    p_num_item_registro NUMBER,
    p_key_error         VARCHAR2,
    p_des_error         VARCHAR2
  );

  /******************************************************************************
  Descripcion       : Devuelve el oid del maximo periodo de acuerdo al oid del
                      cliente
  Fecha Creacion    : 15/07/2010
  Autor             : Jesse James Rios Franco

  p_oid_clie        : oid del cliente
  p_num_pedido      : Primer Pedido = 0
                      Ultimo Pedido = 1
  ******************************************************************************/
  FUNCTION gen_fn_oid_perio_maxim_clien
  (
    p_oid_clie   NUMBER,
    p_num_pedido NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
   Descripcion : Calcula la fecha de entrega de pedido de un determinada
                 Region Zona
   Fecha Creacion : 12/10/2010
   Autor : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_entre_rezon
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2
  ) RETURN DATE;
  /**************************************************************
  Descripcion        : Devuelve el valor del parametro de pais
  Fecha Creacion     : 23/11/2011
  Parametros         : pscodigopais : Pais
                       pscodigosistema : Sistema
                       pscodigoParametro : Parametro
  Autor              : Jose A.Cairampoma Granados
  ***************************************************************/
  FUNCTION gen_fn_param_pais
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigoparametro VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************
  Descripcion        : Calcula el Periodo de acuerdo al numero de Periodos
  Fecha Creacion     : 23/11/2011
  Parametros         : pscodigoperiodo : Periodo
                       pnnumeroperiodo : Numero Periodos atras
  Autor              : Jose A. Cairampoma Granados

  ***************************************************************/
  FUNCTION gen_fn_perio_nsigu
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pnnumeroperiodo NUMBER
  ) RETURN VARCHAR2;

  /******************************************************************************
  Descripcion       : Devuelve el oid del Tipo de bloqueo

  Fecha Creacion    : 26/12/2011
  Autor             : Ivan Tocto Jaimes

  p_tipo_bloq        : Tipo de Bloqueo
  ******************************************************************************/
  FUNCTION gen_fn_devuelve_id_tipo_bloq(p_tipo_bloq VARCHAR2) RETURN NUMBER;

  /******************************************************************************
  Descripcion       : Devuelve el oid del Tipo de Accion

  Fecha Creacion    : 26/12/2011
  Autor             : Ivan Tocto Jaimes

  p_tipo_acci        : Tipo de Accion
  ******************************************************************************/
  FUNCTION gen_fn_devuelve_id_vacc_bloq(p_tipo_acci VARCHAR2) RETURN NUMBER;

  /**************************************************************
  Descripcion        : Retorna el origen del pedido
  Fecha Creacion     : 02/02/2012
  Parametros         : psoid : Pedido
  Autor              : Vidal Cupe Quispe

  ***************************************************************/
  FUNCTION gen_fn_devue_orige_pedid(psoid VARCHAR2) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Genera nuevo Lote para el Periodo Pasado como  parametro
  Fecha Creacion     : 06/03/2012

  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_actua_lote_fecha_perio
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pdfechaproceso  IN DATE,
    psnumerolote    OUT VARCHAR2
  );
  /**************************************************************************
  Descripcion        : Actualiza como campa?a activa al periodo enviado como
  parametro.
  Fecha Creacion     : 06/03/2012

  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_actua_perio_actua
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2
  );
  /**************************************************************************
  Descripcion        : Actualiza las estadisticas de las tablas parametrizadas
                       en BAS_PARAm_PAIS.
  Fecha Creacion     : 06/03/2012

  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_actua_estad_tabla;

  /**************************************************************************
  Descripcion : Devuelve el telefono fijo y celular del cliente
  Fecha Creacion : 15/06/2012
  Autor : Jorge Velasquez
  ***************************************************************************/
  FUNCTION gen_fn_telf_mae_client(oidcliente NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve la direccion completa del cliente
  Fecha Creacion : 15/06/2012
  Autor : Jorge Velasquez
  ***************************************************************************/
  FUNCTION gen_fn_direc_mae_client(oidcliente NUMBER) RETURN VARCHAR2;

  /**************************************************************
  Descripcion        : Retorna el origen de la posicion del pedido
  Fecha Creacion     : 02/07/2012
  Parametros         : psoid : Pedido
                       pscuv : cuv del pedido
  Autor              : Sandro Quintana Aponte
  ***************************************************************/
  FUNCTION gen_fn_devue_orige_pedid_posic
  (
    psoid VARCHAR2,
    pscuv VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion : Devuelve la cantidad de campa?as de diferencia entre dos campa?as
  Fecha Creacion : 31/01/2013
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Yury Romero Anaya
  ***************************************************************************/
  FUNCTION gen_fn_devue_difer_perio
  (
    pscodperiodo01        VARCHAR2,
    pscodperiodo02        VARCHAR2,
    devuelvevalornodata   BOOLEAN := FALSE
  ) RETURN VARCHAR2;

  FUNCTION gen_fn_devue_difer_perio_pais
  (
    pscodPais             VARCHAR2,
    pscodperiodo01        VARCHAR2,
    pscodperiodo02        VARCHAR2,
    devuelvevalornodata   BOOLEAN := FALSE
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve la cantidad de campañas de diferencia entre dos campañas
                DEVUELVE LA RESTA DE pscodperiodo02 - pscodperiodo01
  Fecha Creacion : 12/03/2015
  Parametros Entrada :
  pscodperiodo01 : Codigo de Periodo1
  pscodperiodo02 : Codigo de Periodo2
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Ivan Tocto Jaimes
  ***************************************************************************/
  FUNCTION gen_fn_devue_difer_perio_pais1
  (
    pscodPais             VARCHAR2,
    pscodperiodo01        VARCHAR2,
    pscodperiodo02        VARCHAR2,
    devuelvevalornodata   BOOLEAN := FALSE
  ) RETURN VARCHAR2;

  FUNCTION gen_fn_reemp_carac_extra(p_text IN VARCHAR2) RETURN VARCHAR2;
  /***************************************************************************
  Descripcion       : Funcion que devuelve el indicador KIT Nueva
                      Indica si es el primer pedido de la consultora
  Fecha Creacion    : 16/05/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_indic_kit_nueva(pscodigocliente VARCHAR2) RETURN VARCHAR2;
  /***************************************************************************
  Descripcion       : Funcion que devuelve el indicador KIT Nueva
                      Indica si es el primer pedido de la consultora
  Fecha Creacion    : 16/05/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_indic_prime_pedid
  (
    pscodigocliente  VARCHAR2,
    psperiodoingreso VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, fecha, para paises FOX
  Devuelve Periodo ACTIVO ACTUAL si hay cruce de campa?a
  Fecha Creacion : 23/07/2013
  Autor : Ivan Tocto Jaimes
  ***************************************************************************/
  FUNCTION gen_fn_devu_perio_fech_pfox
  (
    pscodpais  VARCHAR2,
    pdfecha    DATE
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion           : Devuelve Longitud del Tipo Documento
  Fecha Creacion        : 15/08/2013
  Parametros Entrada    :
        vsCodPais       : Codigo Pais
        vsCodTipoDoc    : Codigo Tipo Documento
  Autor : Aurelio Oviedo
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_long_tipo_docu
  (
    vsCodPais        VARCHAR2,
    vsCodTipoDoc     VARCHAR2
  ) RETURN NUMBER;

   /**************************************************************************
     Descripcion       : DEVUELVE MONTO ULTIMO PEDIDO
     Fecha Creacion    : 22/01/2014
     Autor             : Rosalvina Ramirez Guardia
   ***************************************************************************/
 FUNCTION gen_fn_clien_monto_ultim_pedi1
  (
    psoidcliente          NUMBER,
    psperiodoultimopedido IN VARCHAR2
  ) RETURN NUMBER;

  /***************************************************************************
    Descripcion       : Obtiene el numero de Activas finales por Seccion
    Fecha Creacion    : 25/03/2014
    Autor             : Juan Altamirano
   ***************************************************************************/
   FUNCTION gen_fn_devue_activas_seccion
   (
      psCodigoPeriodo VARCHAR2,
      psCodigoRegion  VARCHAR2,
      psCodigoZona    VARCHAR2,
      psCodigoSeccion VARCHAR2
   )RETURN NUMBER;

   /**************************************************************************
    Descripcion : Calculo digito verificador en MOD 11
    Fecha Creacion : 27/06/2014
    Autor : Juan Carlos Gutierrez
    ***************************************************************************/
    FUNCTION gen_fn_devue_digit_verif_nit
    (
        psNroDocumento VARCHAR2
    )RETURN NUMBER;

  /**************************************************************
  Descripcion        : Devuelve el valor del parametro de pais
  Fecha Creacion     : 17/11/2014
  Parametros         : pscodigopais : Pais
                       pscodigosistema : Sistema
                       psnombreparametro : nombre Parametro
  Autor              : Sergio Apaza
  ***************************************************************/
  FUNCTION gen_fn_nombre_param_pais
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    psnombreparametro VARCHAR2
  ) RETURN VARCHAR2;
  
  /**************************************************************
  Descripcion        : Devuelve el valor del origen de pedido de la consultora
  Fecha Creacion     : 19/08/2015
  Parametros         : pscodclie : Codigo Cliente
                       pscodpe : Codigo Periodo
  Autor              : Aurelio Oviedo
  ***************************************************************/
  FUNCTION gen_fn_devue_pedid_orig
  (
    pscodclie VARCHAR2,
    pscodpe VARCHAR2
  ) RETURN VARCHAR2;

  FUNCTION gen_fn_fecha_dias_habiles
    (
    pdFecha DATE, 
    lnDiferencia NUMBER
    ) RETURN DATE;
 
  /**************************************************************
  Descripcion        : Devuelve el Promedio Días Pago de últimos 18 pedidos de las consultoras 
  Fecha Creacion     : 10/11/2015
  Parametros         : pscodclie : Oid Cliente
                       pscodpe : Codigo Periodo
  Autor              : Karina Valencia
  ***************************************************************/
  FUNCTION gen_fn_devue_prome_pagos_ultim
  (
    psoidclie NUMBER,
    pscodpe VARCHAR2
  ) RETURN NUMBER;
  
END gen_pkg_gener;
/
CREATE OR REPLACE PACKAGE BODY "GEN_PKG_GENER" IS
  /**************************************************************************
  Descripcion : Devuelve Fecha sin considerar
  la Hora en formato dd/mm/yyyy
  Fecha Creacion : 04/12/2006
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_fecha_sin_hora(vdfecha DATE) RETURN DATE IS
    ld_fecha DATE;
    ls_fecha VARCHAR2(10);
  BEGIN
    ls_fecha := TRIM(to_char(vdfecha,
                             'DD/MM/YYYY'));
    ld_fecha := to_date(ls_fecha,
                        'DD/MM/YYYY');
    RETURN ld_fecha;
  END gen_fn_fecha_sin_hora;

  /**************************************************************************
  Descripcion : Devuelve Fecha Actual del Servidor sin considerar
  la Hora en formato dd/mm/yyyy
  Fecha Creacion : 04/12/2006
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_fecha_actual_sin_hora RETURN DATE IS
    ld_fecha DATE;
    ls_fecha VARCHAR2(10);
  BEGIN
    ld_fecha := SYSDATE;
    ls_fecha := TRIM(to_char(SYSDATE,
                             'DD/MM/YYYY'));
    ld_fecha := to_date(ls_fecha,
                        'DD/MM/YYYY');
    RETURN ld_fecha;
  END gen_fn_fecha_actual_sin_hora;
  /**************************************************************************
  Descripcion : Ejecuta un SQL Dinamico
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psSentencia : Sentencia SQL a ejecutar
  Autor : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_ejec_sql_dinam(pssentencia VARCHAR2) IS
    l_cur  INTEGER;
    l_rowp INTEGER;
  BEGIN
    l_cur := dbms_sql.open_cursor;
    dbms_sql.parse(l_cur,
                   pssentencia,
                   dbms_sql.v7);
    l_rowp := dbms_sql.execute(l_cur);
    dbms_sql.close_cursor(l_cur);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_PR_EJEC_SQL_DINAM: ' || ls_sqlerrm);
      END IF;
  END gen_pr_ejec_sql_dinam;
  /**************************************************************************
  Descripcion : Devuelve status del estado de proceso batch
  Fecha Creacion : 30/01/2007
  Parametros Entrada :
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve ""
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_status_estad_batch
  (
    pscodigoestado      VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    lsindejec  VARCHAR2(1);
    lsindok    VARCHAR2(1);
    lsinderro  VARCHAR2(1);
    lsdevuelve VARCHAR2(20);
  BEGIN
    /* Obteniendo status del proceso */
    SELECT a.ind_ejec,
           a.ind_ok,
           a.ind_erro
      INTO lsindejec,
           lsindok,
           lsinderro
      FROM bas_estad_proce_batch a
     WHERE a.cod_esta_proc = pscodigoestado;
    IF lsindejec = '1' THEN
      RETURN 'EN PROCESO';
    END IF;
    IF lsindok = '1' THEN
      RETURN 'OK';
    END IF;
    IF lsinderro = '1' THEN
      RETURN 'ERROR';
    END IF;
    RETURN '';
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro Status para Codigo: ' || pscodigoestado;
        raise_application_error(-20123,
                                'ERROR GEN_FN_STATUS_ESTAD_BATCH: ' || ls_sqlerrm);
      ELSE
        RETURN '';
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_STATUS_ESTAD_BATCH: ' || ls_sqlerrm);
      END IF;
  END gen_fn_status_estad_batch;
  /**************************************************************************
  Descripcion : Devuelve Id de Periodo de seg_periodo_corpo
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_perio
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_periodo seg_perio_corpo.oid_peri%TYPE;
  BEGIN
    /* Obteniendo id de periodo */
    SELECT a.oid_peri INTO ln_id_periodo FROM seg_perio_corpo a WHERE a.cod_peri = pscodperiodo;
    RETURN ln_id_periodo;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Periodo respectivo con Codigo: ' || pscodperiodo;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PERIO: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PERIO: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_perio;
  /**************************************************************************
  Descripcion : Devuelve Id de Producto de mae_produ
  Fecha Creacion : 09/07/2007
  Parametros Entrada :
  psCodPeriodo : Codigo de Producto
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_produ
  (
    pscodproducto       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_produ mae_produ.oid_prod%TYPE;
  BEGIN
    /* Obteniendo id de producto */
    SELECT a.oid_prod INTO ln_id_produ FROM mae_produ a WHERE a.cod_sap = pscodproducto;
    RETURN ln_id_produ;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Producto respectivo con Codigo: ' || pscodproducto;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PRODU: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PRODU: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_produ;

  /**************************************************************************
  Descripcion : Devuelve Periodo Actual en base al Pais, Marca y Canal
  Fecha Creacion : 12/02/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_perio_actu
  (
    pnidpais   NUMBER,
    pscodmarca VARCHAR2,
    pnidcanal  NUMBER
  ) RETURN VARCHAR2 IS
    ld_fecha    DATE;
    ls_fecha    VARCHAR2(10);
    ln_oidmarca seg_marca.oid_marc%TYPE;
    ls_cod_peri seg_perio_corpo.cod_peri%TYPE;
  BEGIN
    /* obtenemos id de marca */
    ln_oidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodmarca);

    /* Obteniendo entidad periodos */
    ld_fecha := SYSDATE;
    ls_fecha := to_char(SYSDATE,
                        'DD/MM/YYYY');
    ld_fecha := to_date(ls_fecha,
                        'DD/MM/YYYY');

    BEGIN
      SELECT b.cod_peri
        INTO ls_cod_peri
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.pais_oid_pais = pnidpais
         AND a.marc_oid_marc = ln_oidmarca
         AND a.cana_oid_cana = pnidcanal
         AND a.fec_inic <= ld_fecha
         AND a.fec_fina >= ld_fecha
         AND b.oid_peri = a.peri_oid_peri
         AND rownum = 1;
      RETURN ls_cod_peri;

    EXCEPTION
      WHEN no_data_found THEN
        IF pscodmarca <> 'T' THEN
          BEGIN
            ln_oidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca('T');
            SELECT b.cod_peri
              INTO ls_cod_peri
              FROM cra_perio       a,
                   seg_perio_corpo b
             WHERE a.pais_oid_pais = pnidpais
               AND a.marc_oid_marc = ln_oidmarca
               AND a.cana_oid_cana = pnidcanal
               AND a.fec_inic <= ld_fecha
               AND a.fec_fina >= ld_fecha
               AND b.oid_peri = a.peri_oid_peri
               AND rownum = 1;
            RETURN ls_cod_peri;
          EXCEPTION
            WHEN no_data_found THEN
              RETURN '';
          END;
        END IF;
        RETURN '';
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_DEVUELVE_PERIO_ACTU: ' || ls_sqlerrm);
  END gen_fn_devuelve_perio_actu;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, Marca y Canal, fecha
  Devuelve Periodo menor si hay cruce de campa?a
  Fecha Creacion : 30/05/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_perio_fecha
  (
    pscodpais  VARCHAR2,
    pscodmarca VARCHAR2,
    pscodcanal VARCHAR2,
    pdfecha    DATE
  ) RETURN VARCHAR2 IS
    ld_fecha    DATE;
    ln_idpais   seg_pais.oid_pais%TYPE;
    ln_id_marca seg_marca.oid_marc%TYPE;
    ln_id_canal seg_canal.oid_cana%TYPE;
    ls_cod_peri VARCHAR2(6);

    CURSOR cperiodo IS
      SELECT b.cod_peri
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.pais_oid_pais = ln_idpais
         AND a.marc_oid_marc = ln_id_marca
         AND a.cana_oid_cana = ln_id_canal
         AND a.fec_inic <= ld_fecha
         AND a.fec_fina >= ld_fecha
         AND b.oid_peri = a.peri_oid_peri
       ORDER BY cod_peri;

  BEGIN
    /* Obteniendo id */
    ln_idpais   := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodpais);
    ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodmarca);
    ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodcanal);
    ld_fecha    := gen_pkg_gener.gen_fn_fecha_sin_hora(pdfecha);

    /* encontrando periodo */
    BEGIN
      SELECT b.cod_peri
        INTO ls_cod_peri
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.pais_oid_pais = ln_idpais
         AND a.marc_oid_marc = ln_id_marca
         AND a.cana_oid_cana = ln_id_canal
         AND a.fec_inic <= ld_fecha
         AND a.fec_fina >= ld_fecha
         AND b.oid_peri = a.peri_oid_peri;
      RETURN ls_cod_peri;

    EXCEPTION
      WHEN too_many_rows THEN
        /* FOR c1 IN cPeriodo LOOP
        ls_cod_peri := c1.cod_peri;
        EXIT;
        END LOOP;*/
        SELECT cod_peri
          INTO ls_cod_peri
          FROM bas_ctrl_fact a
         WHERE a.cod_pais = pscodpais
           AND a.sta_camp = '0'
           AND ind_camp_act = '1';

        RETURN ls_cod_peri;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_DEVUELVE_PERIO_FECHA: ' || ls_sqlerrm);
  END gen_fn_devuelve_perio_fecha;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, Marca y Canal, fecha
  Si hay cruce y esta en la campa?a activa: Mostrar la campa?a activa.
  Si hay cruce y no esta en campa?a activa: Mostrar la mas cercana.
  Si no hay campa?a para la fecha solicitada: Mostrar Mensaje "Campa?a no configurada para la fecha ingresada."
  Para ser utilizada desde Java, con el ultimo parametro(fecha) en String
  Fecha Creacion : 30/05/2007
  Autor : Juan Altamirano
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_perio_fecha2
  (
    pscodpais  VARCHAR2,
    pscodmarca VARCHAR2,
    pscodcanal VARCHAR2,
    pdfecha    VARCHAR2

  ) RETURN VARCHAR2 IS

    ld_fecha    DATE;
    ln_id_pais      seg_pais.oid_pais%TYPE;
    ln_id_marca seg_marca.oid_marc%TYPE;
    ln_id_canal seg_canal.oid_cana%TYPE;
    ls_cod_peri VARCHAR2(6);
    vscodPeri       VARCHAR2(6);
    vscodPeriMenor  VARCHAR2(6);
    vsExiste        VARCHAR2(2);
    vsMenor         NUMBER;
    vsperiodo1      NUMBER;
    vsperiodo2      NUMBER;
    vsTotal         NUMBER;

    CURSOR c_periodo(
    ln_id_pais      seg_pais.oid_pais%TYPE,
    ln_id_marca     seg_marca.oid_marc%TYPE,
    ln_id_canal     seg_canal.oid_cana%TYPE,
    ld_fecha        DATE
    ) IS
      SELECT b.cod_peri
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.pais_oid_pais = ln_id_pais
         AND a.marc_oid_marc = ln_id_marca
         AND a.cana_oid_cana = ln_id_canal
         AND a.fec_inic <= ld_fecha
         AND a.fec_fina >= ld_fecha
         AND b.oid_peri = a.peri_oid_peri
       ORDER BY b.cod_peri;

  BEGIN
    ln_id_pais   := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodpais);
    ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodmarca);
    ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodcanal);


      IF(pdfecha IS NOT NULL) THEN
         SELECT to_date(pdfecha,'dd/MM/yyyy') INTO ld_fecha FROM dual;
         vsExiste := 0;
         vsMenor := 100000;
         vsTotal := 0;

         OPEN c_periodo(ln_id_pais, ln_id_marca, ln_id_canal, ld_fecha);
              LOOP FETCH c_periodo INTO vscodPeri;

               EXIT WHEN c_periodo%NOTFOUND;
                dbms_output.put_line('Entro a cursor: ');
                --obtenemos la campa?a activa
    BEGIN
                   SELECT cod_peri INTO ls_cod_peri
          FROM bas_ctrl_fact a
         WHERE a.cod_pais = pscodpais
           AND a.sta_camp = '0'
           AND ind_camp_act = '1';
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    ls_cod_peri := NULL;
                END;


                CASE
                   WHEN (ls_cod_peri = vscodPeri) THEN vsExiste := 'SI'; EXIT;
                   WHEN (ls_cod_peri <> vscodPeri)  THEN  vsExiste := 'NO';
                     vsperiodo1 := to_number(ls_cod_peri);
                     vsperiodo2 := to_number(vscodPeri);

                     vstotal  := abs(vsperiodo1 - vsperiodo2);

                      if(vsTotal < vsMenor)then
                          vsMenor :=  vstotal;
                          vscodPeriMenor := vscodPeri;
                      end if;


                END CASE;


              END LOOP;
          CLOSE c_periodo;

          IF(vsExiste = 'SI') THEN
        RETURN ls_cod_peri;
          ELSIF(vsExiste = 'NO')THEN
             RETURN vscodPeriMenor;
          ELSE
             RETURN NULL;
          END IF;

      ELSE
        RETURN NULL;

      END IF;--fIN VALIDACION FECHA <> NULL



  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,1,250);
      raise_application_error(-20123,'ERROR gen_fn_devuelve_perio_fecha2: ' || ls_sqlerrm);
  END gen_fn_devuelve_perio_fecha2;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, fecha para FOX
  Si hay cruce y esta en la campa?a activa: Mostrar la campa?a activa.
  Si hay cruce y no esta en campa?a activa: Mostrar la mas cercana.
  Si no hay campa?a para la fecha solicitada: Mostrar Mensaje "Fecha sin campa?a."
  Para ser utilizada desde Java, con el parametro(fecha) en String
  Fecha Creacion : 11/12/2013
  Autor : Juan Altamirano
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_perio_fecfox
  (
    pscodpais  VARCHAR2,
    pdfecha    VARCHAR2
  ) RETURN VARCHAR2 IS

    ld_fecha        DATE;
    ls_cod_peri     VARCHAR2(6);
    vscodPeri       VARCHAR2(6);
    vscodPeriMenor  VARCHAR2(6);
    vsExiste        VARCHAR2(2);

    vsMenor         NUMBER;
    vsperiodo1      NUMBER;
    vsperiodo2      NUMBER;
    vsTotal         NUMBER;

    CURSOR c_periodo(ld_fecha DATE) IS
       SELECT zdc.cod_camp
        FROM zon_direc_campa zdc
       WHERE zdc.pais_cod_pais = pscodpais
         AND zdc.est_regi = '1'
         AND zdc.fec_inic <= ld_fecha
         AND zdc.fec_fina >= ld_fecha
         order by zdc.cod_camp;

  BEGIN

      IF(pdfecha IS NOT NULL) THEN
         ld_fecha := to_date(pdfecha,'dd/MM/yyyy');
         vsExiste := 0;
         vsMenor := 100000;
         vsTotal := 0;

         OPEN c_periodo(ld_fecha);
              LOOP FETCH c_periodo INTO vscodPeri;

               EXIT WHEN c_periodo%NOTFOUND;
                dbms_output.put_line('Entro a cursor: ');
                --obtenemos la campa?a activa
                BEGIN
                   SELECT cf.cam_proc INTO ls_cod_peri
                    FROM zon_direc_cntrl_factu cf
                   WHERE cf.pais_cod_pais = pscodpais
                     AND cf.est_camp = '0'
                     AND cf.ind_camp_acti = '1';
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    ls_cod_peri := NULL;
                END;

                IF(ls_cod_peri IS NOT NULL) THEN

                  CASE
                     WHEN (ls_cod_peri = vscodPeri) THEN vsExiste := 'SI'; EXIT;
                     WHEN (ls_cod_peri <> vscodPeri)  THEN  vsExiste := 'NO';
                       vsperiodo1 := to_number(ls_cod_peri);
                       vsperiodo2 := to_number(vscodPeri);

                       vstotal  := abs(vsperiodo1 - vsperiodo2);

                        if(vsTotal < vsMenor)then
                            vsMenor :=  vstotal;
                            vscodPeriMenor := vscodPeri;
                        end if;


                  END CASE;

                ELSE
                   vscodPeriMenor := vscodPeri;
                   vsExiste := 'NO';
                   EXIT;
                END IF;


              END LOOP;
          CLOSE c_periodo;

          IF(vsExiste = 'SI') THEN
             RETURN ls_cod_peri;
          ELSIF(vsExiste = 'NO')THEN
             RETURN vscodPeriMenor;
          ELSE
             RETURN NULL;
          END IF;

      ELSE
        RETURN NULL;

      END IF;--fIN VALIDACION FECHA <> NULL



  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,1,250);
      raise_application_error(-20123,'ERROR gen_fn_devuelve_perio_fecfox: ' || ls_sqlerrm);
  END gen_fn_devuelve_perio_fecfox;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, Marca y Canal, fecha
  Devuelve Periodo MAYOR si hay cruce de campa?a
  Fecha Creacion : 30/05/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devue_perio_mayor_fecha
  (
    pscodpais  VARCHAR2,
    pscodmarca VARCHAR2,
    pscodcanal VARCHAR2,
    pdfecha    DATE
  ) RETURN VARCHAR2 IS
    ld_fecha    DATE;
    ln_idpais   seg_pais.oid_pais%TYPE;
    ln_id_marca seg_marca.oid_marc%TYPE;
    ln_id_canal seg_canal.oid_cana%TYPE;
    ls_cod_peri VARCHAR2(6);

    CURSOR cperiodo IS
      SELECT b.cod_peri
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.pais_oid_pais = ln_idpais
         AND a.marc_oid_marc = ln_id_marca
         AND a.cana_oid_cana = ln_id_canal
         AND a.fec_inic <= ld_fecha
         AND a.fec_fina >= ld_fecha
         AND b.oid_peri = a.peri_oid_peri
       ORDER BY cod_peri DESC;

  BEGIN
    /* Obteniendo id */
    ln_idpais   := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodpais);
    ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodmarca);
    ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodcanal);
    ld_fecha    := gen_pkg_gener.gen_fn_fecha_sin_hora(pdfecha);

    /* encontrando periodo */
    BEGIN
      SELECT b.cod_peri
        INTO ls_cod_peri
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.pais_oid_pais = ln_idpais
         AND a.marc_oid_marc = ln_id_marca
         AND a.cana_oid_cana = ln_id_canal
         AND a.fec_inic <= ld_fecha
         AND a.fec_fina >= ld_fecha
         AND b.oid_peri = a.peri_oid_peri;
      RETURN ls_cod_peri;

    EXCEPTION
      WHEN too_many_rows THEN
        /*FOR c1 IN cPeriodo LOOP
        ls_cod_peri := c1.cod_peri;
        EXIT;
        END LOOP;
        */
        SELECT cod_peri
          INTO ls_cod_peri
          FROM bas_ctrl_fact a
         WHERE a.cod_pais = pscodpais
           AND a.sta_camp = '0'
           AND ind_camp_act = '1';
        RETURN ls_cod_peri;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_DEVUE_PERIO_MAYOR_FECHA: ' || ls_sqlerrm);
  END gen_fn_devue_perio_mayor_fecha;

  /**************************************************************************
  Descripcion : Verifica si hay Cruce de Campa?a en base al Pais, Marca,
  Canal y fecha
  Retorna:
  1 Existe cruce de campa?a
  0 No existe Cruce de Campa?a
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_verif_cruce_campa
  (
    pscodpais  VARCHAR2,
    pscodmarca VARCHAR2,
    pscodcanal VARCHAR2,
    pdfecha    DATE
  ) RETURN NUMBER IS
    ld_fecha    DATE;
    ln_idpais   seg_pais.oid_pais%TYPE;
    ln_id_marca seg_marca.oid_marc%TYPE;
    ln_id_canal seg_canal.oid_cana%TYPE;
    ls_cod_peri VARCHAR2(6);

  BEGIN
    /* Obteniendo id */
    ln_idpais   := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodpais);
    ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodmarca);
    ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodcanal);
    ld_fecha    := gen_pkg_gener.gen_fn_fecha_sin_hora(pdfecha);

    /* encontrando periodo */
    BEGIN
      SELECT b.cod_peri
        INTO ls_cod_peri
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.pais_oid_pais = ln_idpais
         AND a.marc_oid_marc = ln_id_marca
         AND a.cana_oid_cana = ln_id_canal
         AND a.fec_inic <= ld_fecha
         AND a.fec_fina >= ld_fecha
         AND b.oid_peri = a.peri_oid_peri;
      RETURN 0;

    EXCEPTION
      WHEN too_many_rows THEN
        RETURN 1;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_VERIF_CRUCE_CAMPA: ' || ls_sqlerrm);
  END gen_fn_verif_cruce_campa;

  /***********************************************************************************
  Descripcion : Devuelve Id de Periodo de TABLA CRA_PERIO
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  pnidmarca    : Oid de Marca
  pnidcanal    : Oid de Canal
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cra_perio
  (
    pscodperiodo        VARCHAR2,
    pnidmarca           NUMBER,
    pnidcanal           NUMBER,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_periodo cra_perio.oid_peri%TYPE;
  BEGIN
    /* Obteniendo id de periodo */
    SELECT b.oid_peri
      INTO ln_id_periodo
      FROM seg_perio_corpo a,
           cra_perio       b
     WHERE a.cod_peri = pscodperiodo
       AND b.marc_oid_marc = pnidmarca
       AND b.cana_oid_cana = pnidcanal
       AND a.oid_peri = b.peri_oid_peri;

    RETURN ln_id_periodo;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Periodo respectivo con Codigo: ' || pscodperiodo;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CRA_PERIO: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CRA_PERIO: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_cra_perio;
  /***********************************************************************************
  Descripcion : Devuelve Id de Periodo de TABLA CRA_PERIO para marca T Canal VD
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cra_perio2
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_periodo cra_perio.oid_peri%TYPE;
  BEGIN
    /* Obteniendo id de periodo */
    SELECT b.oid_peri
      INTO ln_id_periodo
      FROM seg_perio_corpo a,
           cra_perio       b,
           seg_canal       c,
           seg_marca       d
     WHERE a.cod_peri = pscodperiodo
       AND a.oid_peri = b.peri_oid_peri
       AND b.cana_oid_cana = c.oid_cana
       AND b.marc_oid_marc = d.oid_marc
       AND c.cod_cana = 'VD'
       AND d.cod_marc = 'T';

    RETURN ln_id_periodo;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Periodo respectivo con Codigo: ' || pscodperiodo;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CRA_PERIO2: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CRA_PERIO2: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_cra_perio2;

  /***********************************************************************************
  Descripcion : Devuelve Codigo de Periodo(VARCHAR2(6)) de TABLA SEG_PERIO_CORPO
  Fecha Creacion : 25/07/2013
  Parametros Entrada :
  psOidPeriodo : Oid del Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Juan Altamirano
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cra_perio3
  (
    psOidPeriodo        NUMBER,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_cod_periodo SEG_PERIO_CORPO.COD_PERI%TYPE;
  BEGIN
    select a.cod_peri into ln_cod_periodo
      from seg_perio_corpo a,
           cra_perio b,
           seg_marca c,
           seg_canal d
       where b.oid_peri = psOidPeriodo
       and a.oid_peri = b.peri_oid_peri
       and b.marc_oid_marc = c.oid_marc
       and b.cana_oid_cana = d.oid_cana
       and c.cod_marc = 'T'
       and d.cod_cana = 'VD';

    return ln_cod_periodo;
   EXCEPTION
     WHEN NO_DATA_FOUND THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro Codigo de Periodo respectivo con Oid: ' || psOidPeriodo;
        raise_application_error(-20123, 'ERROR GEN_FN_DEVUELVE_ID_CRA_PERIO2: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
     WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
       raise_application_error(-20123, 'ERROR GEN_FN_DEVUELVE_ID_CRA_PERIO3: ' || ls_sqlerrm);
      END IF;

  END gen_fn_devuelve_id_cra_perio3;


  /**************************************************************************
  Descripcion : Devuelve Id de Periodo de TABLA INC_CONCU_PARAM_GENER
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodConcurso : Codigo de Concurso
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_para_gral
  (
    pscodconcurso       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_para_gral inc_concu_param_gener.oid_para_gral%TYPE;
  BEGIN
    /* Obteniendo id de periodo */
    SELECT a.oid_para_gral
      INTO ln_id_para_gral
      FROM inc_concu_param_gener a
     WHERE a.num_conc = pscodconcurso;
    RETURN ln_id_para_gral;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Concurso respectivo con Codigo: ' || pscodconcurso;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PARA_GRAL: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PARA_GRAL: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_para_gral;
  /**************************************************************************
  Descripcion : Devuelve Id de Marca
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  vsCodPais : Codigo de Marca
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_marca
  (
    pscodmarca          VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_marca seg_marca.oid_marc%TYPE;
  BEGIN
    /* Obteniendo id de marca */
    SELECT a.oid_marc INTO ln_id_marca FROM seg_marca a WHERE a.cod_marc = pscodmarca;
    RETURN ln_id_marca;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Marca respectivo con Codigo: ' || pscodmarca;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_MARCA: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_MARCA: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_marca;

  /**************************************************************************
  Descripcion : Devuelve Id de Marca Produtco
  Fecha Creacion : 13/02/2007
  Parametros Entrada :
  vsCodPais : Codigo de Marca Producto
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_marca_produ
  (
    pscodmarcaprodu     VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_marca_produ seg_marca_produ.oid_marc_prod%TYPE;
  BEGIN
    /* Obteniendo id de marca procu*/
    SELECT a.oid_marc_prod
      INTO ln_id_marca_produ
      FROM seg_marca_produ a
     WHERE a.cod_marc_prod = pscodmarcaprodu;
    RETURN ln_id_marca_produ;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Marca Producto respectivo con Codigo: ' ||
                      pscodmarcaprodu;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_MARCA_PRODU: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_MARCA_PRODU: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_marca_produ;

  /**************************************************************************
  Descripcion : Devuelve Id de Canal
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  vsCodPais : Codigo de Canal
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_canal
  (
    pscodcanal          VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_canal seg_canal.oid_cana%TYPE;
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.oid_cana INTO ln_id_canal FROM seg_canal a WHERE a.cod_cana = pscodcanal;
    RETURN ln_id_canal;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Canal respectivo con Codigo: ' || pscodcanal;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CANAL: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CANAL: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_canal;
  /**************************************************************************
  Descripcion : Devuelve Id de Cliente
  Fecha Creacion : 30/01/2007
  Parametros Entrada :
  vsCodCliente : Codigo de Cliente
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_cliente
  (
    vscodcliente        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_cliente mae_clien.oid_clie%TYPE;
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.oid_clie INTO ln_id_cliente FROM mae_clien a WHERE a.cod_clie = vscodcliente;
    RETURN ln_id_cliente;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Cliente respectivo con Codigo: ' || vscodcliente;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CLIENTE: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_CLIENTE: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_cliente;
  /**************************************************************************
  Descripcion : Devuelve Id de Sub Gerencia
  Fecha Creacion : 29/01/2007
  Parametros Entrada :
  vsCodPais : Codigo de Sub Gerencia
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_sub_geren
  (
    pscodsubgerencia    VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_subgerencia zon_sub_geren_venta.oid_subg_vent%TYPE;
  BEGIN
    /* Obteniendo id de Sub Gerencia */
    SELECT a.oid_subg_vent
      INTO ln_id_subgerencia
      FROM zon_sub_geren_venta a
     WHERE a.cod_subg_vent = pscodsubgerencia;
    RETURN ln_id_subgerencia;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Sub Gerencia respectivo con Codigo: ' ||
                      pscodsubgerencia;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_SUB_GEREN: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_SUB_GEREN: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_sub_geren;
  /**************************************************************************
  Descripcion : Devuelve Id de Negocio
  Fecha Creacion : 26/01/2007
  Parametros Entrada :
  vsCodPais : Codigo de Negocio
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_negocio
  (
    pscodcanal          VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_negocio mae_negoc.oid_nego%TYPE;
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.oid_nego INTO ln_id_negocio FROM mae_negoc a WHERE a.cod_nego = pscodcanal;
    RETURN ln_id_negocio;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Negocio respectivo con Codigo: ' || pscodcanal;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_NEGOCIO: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_NEGOCIO: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_negocio;
  /**************************************************************************
  Descripcion : Devuelve Id de Unidad de Negocio
  Fecha Creacion : 26/01/2007
  Parametros Entrada :
  vsCodPais : Codigo de Unidad de Negocio
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_unid_nego
  (
    pscodcanal          VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_unid_negocio mae_unida_negoc.oid_unid_nego%TYPE;
  BEGIN
    /* Obteniendo id de canal */
    SELECT a.oid_unid_nego
      INTO ln_id_unid_negocio
      FROM mae_unida_negoc a
     WHERE a.cod_unid_nego = pscodcanal;
    RETURN ln_id_unid_negocio;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Unidad de Negocio respectivo con Codigo: ' ||
                      pscodcanal;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_UNID_NEGO: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_UNID_NEGO: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_unid_nego;
  /**************************************************************************
  Descripcion : Devuelve Id de Pais
  Fecha Creacion : 18/09/2006
  Parametros Entrada :
  vsCodPais : Codigo de Pais
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_pais
  (
    vscodpais           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_idpais seg_pais.oid_pais%TYPE;
  BEGIN
    /* Obteniendo id del pais */
    SELECT a.oid_pais INTO ln_idpais FROM seg_pais a WHERE a.cod_pais = vscodpais;
    RETURN ln_idpais;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Pais respectivo con Codigo: ' || vscodpais;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PAIS: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PAIS: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_pais;
  /**************************************************************************
  Descripcion : Devuelve Id de Sociedad
  Fecha Creacion : 05/01/2007
  Parametros Entrada :
  psCodSociedad : Codigo de Sociedad
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_socie
  (
    pscodsociedad       VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_soci seg_socie.oid_soci%TYPE;
  BEGIN
    /* Obteniendo id de sociedad */
    SELECT a.oid_soci INTO ln_id_soci FROM seg_socie a WHERE a.cod_soci = pscodsociedad;
    RETURN ln_id_soci;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Sociedad respectivo con Codigo: ' || pscodsociedad;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_SOCIE: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_SOCIE: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_socie;
  /**************************************************************************
  Descripcion : Devuelve Id de Region
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_region
  (
    pscodpais           VARCHAR2,
    pscodmarca          VARCHAR2,
    pscodcanal          VARCHAR2,
    pscodregion         VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_pais   seg_pais.oid_pais%TYPE;
    ln_id_canal  seg_canal.oid_cana%TYPE;
    ln_id_marca  seg_marca.oid_marc%TYPE;
    ln_id_region zon_regio.oid_regi%TYPE;
  BEGIN
    ln_id_pais  := gen_fn_devuelve_id_pais(pscodpais,
                                           TRUE);
    ln_id_marca := gen_fn_devuelve_id_marca(pscodmarca,
                                            TRUE);
    ln_id_canal := gen_fn_devuelve_id_canal(pscodcanal,
                                            TRUE);
    /* Obteniendo id de region */
    SELECT a.oid_regi
      INTO ln_id_region
      FROM zon_regio a
     WHERE a.pais_oid_pais = ln_id_pais
       AND a.marc_oid_marc = ln_id_marca
       AND a.cana_oid_cana = ln_id_canal
       AND a.cod_regi = pscodregion;
    RETURN ln_id_region;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Region respectivo con Codigo: ' || pscodregion;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_REGION: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_REGION: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_region;
  /**************************************************************************
  Descripcion : Devuelve Id de Region
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_region
  (
    pnoidpais           NUMBER,
    pnoidmarca          NUMBER,
    pnoidcanal          NUMBER,
    pscodregion         VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_region zon_regio.oid_regi%TYPE;
  BEGIN
    /* Obteniendo id de region */
    SELECT a.oid_regi
      INTO ln_id_region
      FROM zon_regio a
     WHERE a.pais_oid_pais = pnoidpais
       AND a.marc_oid_marc = pnoidmarca
       AND a.cana_oid_cana = pnoidcanal
       AND a.cod_regi = pscodregion;
    RETURN ln_id_region;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Region respectivo con Codigo: ' || pscodregion;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_REGION: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_REGION: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_region;

  /**************************************************************************
  Descripcion : Devuelve Id de Zona
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_zona
  (
    pscodpais           VARCHAR2,
    pscodmarca          VARCHAR2,
    pscodcanal          VARCHAR2,
    pscodregion         VARCHAR2,
    pscodzona           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_id_region zon_regio.oid_regi%TYPE;
    ln_id_zona   zon_zona.oid_zona%TYPE;
  BEGIN
    ln_id_region := gen_fn_devuelve_id_region(pscodpais,
                                              pscodmarca,
                                              pscodcanal,
                                              pscodregion,
                                              TRUE);
    /* Obteniendo id de zona */
    SELECT a.oid_zona
      INTO ln_id_zona
      FROM zon_zona a
     WHERE a.zorg_oid_regi = ln_id_region
       AND a.cod_zona = pscodzona;
    RETURN ln_id_zona;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Zona respectivo con Codigo: ' || pscodregion;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_ZONA: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_ZONA: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_zona;
  /***************************************************************************
  Descripcion : Devuelve Id del Almacen Consultado
  Fecha Creacion : 08/03/2007
  Autor : Jose Nunez Mori
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_almac
  (
    pscodalmac bel_almac.cod_alma%TYPE,
    pnidpais   seg_pais.oid_pais%TYPE
  ) RETURN NUMBER IS
    lnidalm bel_almac.oid_alma%TYPE;
  BEGIN
    SELECT b.oid_alma
      INTO lnidalm
      FROM bel_almac b
     WHERE b.pais_oid_pais = pnidpais
       AND b.cod_alma = pscodalmac;
    RETURN lnidalm;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_DEVUELVE_ID_ALMAC: ' || ls_sqlerrm);
  END gen_fn_devuelve_id_almac;
  /**************************************************************************
  Descripcion : Devuelve Codigo de Empleado de la Gerente de Zona
  Fecha Creacion : 25/09/2006
  Parametros Entrada :
  psCodZona: codigo de Zona
  Autor : Marco Silva
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_emp_gzona(pscodzona VARCHAR2) RETURN VARCHAR2 IS
    ln_id_client mae_clien.oid_clie%TYPE;
    ls_cod_emp   mae_clien.oid_clie%TYPE;
  BEGIN
    SELECT z.clie_oid_clie
      INTO ln_id_client
      FROM zon_zona z
     WHERE z.cod_zona = pscodzona
       AND z.ind_acti = 1;
    /* Obteniendo id de zona */
    SELECT ad.cod_empl
      INTO ls_cod_emp
      FROM mae_clien_datos_adici ad
     WHERE ad.clie_oid_clie = ln_id_client
       AND ad.ind_acti = 1;
    RETURN ls_cod_emp;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_ZONA: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_cod_emp_gzona;
  /**************************************************************************
  Descripcion : Devuelve Codigo de Resultado de Chequeo
  Fecha Creacion : 30/01/2007
  Parametros Entrada :
  psOidResulChequeo: Oid Resultado Chequeo
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_res_chequ(psoidresulchequeo VARCHAR2) RETURN VARCHAR2 IS
    ln_cod_resu_cheq rec_resul_chequ.cod_resu_cheq%TYPE;
  BEGIN
    /* Obteniendo id de Resultado Chequeo */
    SELECT ad.cod_resu_cheq
      INTO ln_cod_resu_cheq
      FROM rec_resul_chequ ad
     WHERE ad.oid_resu_cheq = psoidresulchequeo;
    RETURN ln_cod_resu_cheq;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_COD_RES_CHEQU: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_cod_res_chequ;

  /**************************************************************************
  Descripcion : Devuelve Codigo de Operacion Reclamo
  Fecha Creacion : 20/02/2007
  Parametros Entrada :
  psOidOperacion: Oid Operacion
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_oper_recl(psoidoperacion VARCHAR2) RETURN VARCHAR2 IS
    ln_cod_oper_recl rec_opera.cod_oper%TYPE;
  BEGIN
    /* Obteniendo id de Resultado Chequeo */
    SELECT ad.cod_oper INTO ln_cod_oper_recl FROM rec_opera ad WHERE ad.oid_oper = psoidoperacion;
    RETURN ln_cod_oper_recl;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_COD_OPER_RECL: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_cod_oper_recl;

  /**************************************************************************
  Descripcion : Devuelve Id de Proceso
  Fecha Creacion : 18/09/2006
  Parametros Entrada :
  vsCodPais : Codigo de Pais
  vsCodProd : Codigo de Proceso
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_proceso
  (
    vnidpais            NUMBER,
    vscodproc           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    ln_idproc ccc_proce.oid_proc%TYPE;
  BEGIN
    /* Obteniendo id del proceso */
    SELECT a.oid_proc
      INTO ln_idproc
      FROM ccc_proce a
     WHERE a.pais_oid_pais = vnidpais
       AND a.cod_proc = vscodproc;
    RETURN ln_idproc;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Proceso respectivo con Codigo: ' || vscodproc;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PROCESO: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_PROCESO: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_id_proceso;

  /***************************************************************************
  Descripcion : Devuelve La descripcion de un gerente
  de zona o de region segun lo especificado
  en el reporte de MAV
  Fecha Creacion : 23/04/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_devue_descr_geren(oidcliente NUMBER) RETURN VARCHAR2 IS
    lsretorno VARCHAR2(40);
  BEGIN
    lsretorno := '';

    SELECT zon_zona.cod_zona
      INTO lsretorno
      FROM zon_zona
     WHERE zon_zona.clie_oid_clie = oidcliente
       AND rownum = 1;
    RETURN lsretorno;
  EXCEPTION
    WHEN no_data_found THEN
      SELECT zon_regio.des_regi
        INTO lsretorno
        FROM zon_regio
       WHERE zon_regio.clie_oid_clie = oidcliente
         AND rownum = 1;
      RETURN lsretorno;

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      RETURN '';
  END gen_fn_devue_descr_geren;

  /**************************************************************************
  Descripcion : Devuelve La descripcion de una actividad dado un oid de
  solicitud de Ped_Solic_Cabec
  Fecha Creacion : 08/03/2007
  Parametros Entrada :
  psOidSoca : Oid de la Solicitud
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devue_descr_mav_activ
  (
    psoidsoca           VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_desdeta gen_i18n_sicc_pais.val_i18n%TYPE;
  BEGIN
    /* Obteniendo id del Descripcion de la Actividad */
    ln_desdeta := '';
    SELECT g.val_i18n
      INTO ln_desdeta
      FROM mav_activ          mav,
           mav_envio          env,
           ped_solic_cabec    a,
           mav_solic_envio    mso,
           gen_i18n_sicc_pais g
     WHERE (mav.oid_acti = env.acti_oid_acti)
       AND (env.oid_envi = mso.menv_oid_envi)
       AND (a.oid_soli_cabe = mso.soca_oid_soli_cabe)
       AND g.val_oid = mav.oid_acti
       AND g.attr_enti = 'MAV_ACTIV'
       AND a.oid_soli_cabe = psoidsoca
       AND rownum = 1;
    RETURN ln_desdeta;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN ln_desdeta;
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de la Solicitud Respectivo con ID: ' || psoidsoca;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DESCR_MAV_ACTIV: ' || ls_sqlerrm);
      ELSE
        RETURN ln_desdeta;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DESCR_MAV_ACTIV: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devue_descr_mav_activ;

  /**************************************************************************
  Descripcion : Devuelve La descripcion del Tipo de Solicitud
  Fecha Creacion : 23/08/2007
  Parametros Entrada :
  psOidSoca : Oid de la Solicitud
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devue_descr_tipos_solic
  (
    psoidtiposol        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_destiposolicitud gen_i18n_sicc_pais.val_i18n%TYPE;
  BEGIN
    /* Obteniendo id del Descripcion de la Actividad */
    ln_destiposolicitud := '';
    SELECT g.val_i18n
      INTO ln_destiposolicitud
      FROM gen_i18n_sicc_comun g,
           ped_tipo_solic_pais p
     WHERE p.oid_tipo_soli_pais = psoidtiposol
       AND val_oid = p.tsol_oid_tipo_soli
       AND g.attr_enti = 'PED_TIPO_SOLIC'
       AND rownum = 1;
    RETURN ln_destiposolicitud;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN ln_destiposolicitud;
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de la Solicitud Respectivo con ID: ' || psoidtiposol;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DESCR_TIPOS_SOLIC: ' || ls_sqlerrm);
      ELSE
        RETURN ln_destiposolicitud;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DESCR_TIPOS_SOLIC: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devue_descr_tipos_solic;

  /**************************************************************************
  Descripcion : Devuelve Codigo de Periodo con el OID del CRA_PERIO
  Fecha Creacion : 16/01/2007
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_des_perio
  (
    pscodperiodo        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_codperi seg_perio_corpo.cod_peri%TYPE;
  BEGIN
    /* Obteniendo id del Periodo */
    SELECT a.cod_peri
      INTO ln_codperi
      FROM seg_perio_corpo a,
           cra_perio       b
     WHERE b.oid_peri = pscodperiodo
       AND a.oid_peri = b.peri_oid_peri;
    RETURN ln_codperi;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
--        ls_sqlerrm := 'No se encontro ID de Periodo respectivo con ID: ' || pscodperiodo;
--        raise_application_error(-20123,
--                                'ERROR GEN_FN_DEVUELVE_DES_PERIO: ' || ls_sqlerrm);
        RETURN NULL;
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_DES_PERIO: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_des_perio;

  /**************************************************************************
  Descripcion : Devuelve la Subgerencia de Venta la descripcion corta
  Fecha Creacion : 16/02/2007
  Parametros Entrada :
  psOidSubGerencia : EL OID de la SubGerencia
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_des_sub_geren
  (
    psoidsubgerencia    VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_des_subgerencia zon_sub_geren_venta.des_subg_vent%TYPE;
  BEGIN
    /* Obteniendo id del proceso */
    SELECT a.des_subg_vent
      INTO ln_des_subgerencia
      FROM zon_sub_geren_venta a
     WHERE a.oid_subg_vent = psoidsubgerencia;
    RETURN ln_des_subgerencia;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Sub Gerencia respectivo con ID: ' || psoidsubgerencia;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_DES_SUB_GEREN: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_DES_SUB_GEREN: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_des_sub_geren;
  /**************************************************************************
  Descripcion : Devuelve la Descripcion de la Zona a partir del codigo
  de la subgernecia
  Fecha Creacion : 13/04/2007
  Parametros Entrada :
  psOidSeccion : EL OID de la Seccion
  psTipo : DES Descripcion de la Zona
  COD Codigo de la Zona
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_des_zona_secc
  (
    psoidseccion        VARCHAR2,
    pstipo              VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_cod_zona zon_zona.cod_zona%TYPE;
    ln_des_zona zon_zona.des_zona%TYPE;
  BEGIN
    /* Obteniendo id del proceso */

    SELECT a.cod_zona,
           a.des_zona
      INTO ln_cod_zona,
           ln_des_zona
      FROM zon_zona  a,
           zon_secci b
     WHERE a.oid_zona = b.zzon_oid_zona
       AND b.oid_secc = psoidseccion;

    IF pstipo = 'COD' THEN
      RETURN ln_cod_zona;
    ELSE
      RETURN ln_des_zona;
    END IF;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Seccion respectivo con ID: ' || psoidseccion;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_DES_ZONA_SECC: ' || ls_sqlerrm);
      ELSE
        RETURN '';
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_DES_ZONA_SECC: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_des_zona_secc;
  /**************************************************************************
  Descripcion : Devuelve la El codigo de la subgerencia de Venta
  Fecha Creacion : 16/02/2007
  Parametros Entrada :
  psOidSubGerencia : EL OID de la SubGerencia
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_sub_geren
  (
    psoidsubgerencia    VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_des_subgerencia zon_sub_geren_venta.cod_subg_vent%TYPE;
  BEGIN
    /* Obteniendo id del proceso */
    SELECT a.cod_subg_vent
      INTO ln_des_subgerencia
      FROM zon_sub_geren_venta a
     WHERE a.oid_subg_vent = psoidsubgerencia;
    RETURN ln_des_subgerencia;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Sub Gerencia respectivo con ID: ' || psoidsubgerencia;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_COD_SUB_GEREN: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_COD_SUB_GEREN: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_cod_sub_geren;
  /**************************************************************************
  Descripcion : Devuelve la El codigo del cliente
  Fecha Creacion : 16/04/2007
  Parametros Entrada :
  psOidSubGerencia : EL OID del Cliente
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_cod_clie
  (
    psoidcliente        VARCHAR2,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_cod_cliente mae_clien.cod_clie%TYPE;
  BEGIN

    SELECT a.cod_clie INTO ln_cod_cliente FROM mae_clien a WHERE a.oid_clie = psoidcliente;
    RETURN ln_cod_cliente;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID de Cliente respectivo con ID: ' || psoidcliente;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_COD_CLIE: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_COD_CLIE: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_cod_clie;

  /**************************************************************************
  Descripcion : Devuelve el Sgte Numero de Lote en base al pais,
  Sistema e Interfaz
  Fecha Creacion : 20/09/2006
  Parametros Entrada :
  psCodigoPais : Codigo de Pais
  psCodigoSistema : Codigo de Sistema
  psCodigoInterfaz : Codigo de Interfaz
  Autor : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_devuelve_num_lote_sgte
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnumerolote     OUT VARCHAR2
  ) IS
  BEGIN
    psnumerolote := gen_fn_devuelve_num_lote_sgte(pscodigopais,
                                                  pscodigosistema,
                                                  pscodigointerfaz);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_PR_DEVUELVE_NUM_LOTE_SGTE: ' || ls_sqlerrm);
      END IF;
  END gen_pr_devuelve_num_lote_sgte;
  /**************************************************************************
  Descripcion : Devuelve el Sgte Numero de Lote en base al pais,
  Sistema e Interfaz
  Fecha Creacion : 20/09/2006
  Parametros Entrada :
  psCodigoPais : Codigo de Pais
  psCodigoSistema : Codigo de Sistema
  psCodigoInterfaz : Codigo de Interfaz
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_num_lote_sgte
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_numeroloteinterfaz bas_histo_lotes.num_lote%TYPE;
    ls_numerolote         bas_histo_lotes.num_lote%TYPE;
    ls_tipo               bas_inter.tip_gein%TYPE;
    ln_numerolote         NUMBER;
    CURSOR cursorinterfacepaquete IS
      SELECT t.inte_cod_inte
        FROM bas_compo_paque t
       WHERE t.pais_cod_pais = pscodigopais
         AND t.sist_cod_sist = pscodigosistema
         AND t.inte_cod_inpa = pscodigointerfaz;
  BEGIN
    ls_numerolote := '';
    SELECT tip_gein
      INTO ls_tipo
      FROM bas_inter a
     WHERE a.pais_cod_pais = pscodigopais
       AND a.sist_cod_sist = pscodigosistema
       AND a.cod_inte = pscodigointerfaz;
    /* Interfase Unitaria */
    IF ls_tipo = 'U' THEN
      SELECT to_char(SYSDATE,
                     'YYYYMMDD') || lpad(nvl(MAX(substr(h.num_lote,
                                                        9,
                                                        4)) + 1,
                                             1),
                                         4,
                                         '0')
        INTO ls_numerolote
        FROM bas_histo_lotes h
       WHERE h.pais_cod_pais = pscodigopais
         AND h.sist_cod_sist = pscodigosistema
         AND h.inte_cod_inte = pscodigointerfaz
         AND substr(h.num_lote,
                    1,
                    8) = to_char(SYSDATE,
                                 'YYYYMMDD');
      /* Interfase Paquete */
    ELSE
      ln_numerolote := 0;
      FOR curpaquete IN cursorinterfacepaquete
      LOOP
        SELECT to_char(SYSDATE,
                       'YYYYMMDD') || lpad(nvl(MAX(substr(h.num_lote,
                                                          9,
                                                          4)) + 1,
                                               1),
                                           4,
                                           '0')
          INTO ls_numeroloteinterfaz
          FROM bas_histo_lotes h
         WHERE h.pais_cod_pais = pscodigopais
           AND h.sist_cod_sist = pscodigosistema
           AND h.inte_cod_inte = curpaquete.inte_cod_inte
           AND substr(h.num_lote,
                      1,
                      8) = to_char(SYSDATE,
                                   'YYYYMMDD');
        IF to_number(ls_numeroloteinterfaz) > ln_numerolote THEN
          ls_numerolote := ls_numeroloteinterfaz;
          ln_numerolote := to_number(ls_numeroloteinterfaz);
        END IF;
      END LOOP;
    END IF;
    RETURN ls_numerolote;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_NUM_LOTE_SGTE: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_num_lote_sgte;
  /*
  * Funcion que te devuelve las descripciones
  * -- Marca : TIPO_MARCA
  * -- Plantilla : TIPO_PLANTILLA
  * -- Pais : PAIS
  * -- Canal : CANAL
  * -- Tipo Calificacion : TIPO_CALIFICACION
  * -- Acceso : ACCESO
  * -- Tipo Venta : TIPO_VENTA
  * -- Tipo Concurso : TIPO_CONCURSO
  */
  FUNCTION gen_fn_devuelve_descripcion
  (
    psoid         VARCHAR2,
    psdescripcion VARCHAR2,
    pscodigoiso   VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_descripcion VARCHAR(1000);
  BEGIN
    ls_descripcion := '';
    IF psoid IS NULL THEN
      RETURN ls_descripcion;
    END IF;
    IF psdescripcion = 'TIPO_CALIFICACION' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_tipo_calif      calif
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND calif.oid_tipo_cali = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND calif.oid_tipo_cali = psoid
         AND gen.attr_enti = 'INC_TIPO_CALIF';
    END IF;
    IF psdescripcion = 'PAIS' THEN
      SELECT bas_pais.des_pais
        INTO ls_descripcion
        FROM bas_pais
       WHERE bas_pais.cod_pais =
             (SELECT seg_pais.cod_pais FROM seg_pais WHERE seg_pais.oid_pais = psoid);
    END IF;
    IF psdescripcion = 'SEG_MARCA_PRODU' THEN
      SELECT seg_marca_produ.des_marc_prod
        INTO ls_descripcion
        FROM seg_marca_produ
       WHERE seg_marca_produ.oid_marc_prod = psoid;
    END IF;
    IF psdescripcion = 'CANAL' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_canal           can,
             seg_idiom           idi
       WHERE gen.val_oid = can.oid_cana
         AND gen.idio_oid_idio = idi.oid_idio
         AND idi.cod_iso_idio = pscodigoiso
         AND can.oid_cana = psoid
         AND gen.attr_enti = 'SEG_CANAL';
    END IF;
    IF psdescripcion = 'TIPO_PLANTILLA' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_plant_concu     plant
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND plant.oid_plan_conc = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND plant.oid_plan_conc = psoid
         AND gen.attr_enti = 'INC_PLANT_CONCU'
         AND plant.ind_acti = '1';
    END IF;
    IF psdescripcion = 'MARCA' THEN
      SELECT des_marc INTO ls_descripcion FROM seg_marca WHERE oid_marc = psoid;
    END IF;
    IF psdescripcion = 'TIPO_CONCURSO' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_concu_ivr       ivr
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND ivr.oid_conc_ivr = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND ivr.oid_conc_ivr = psoid
         AND gen.attr_enti = 'INC_CONCU_IVR';
    END IF;
    IF psdescripcion = 'TIPO_VENTA' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_tipo_venta      venta
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND venta.oid_tipo_vent = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND venta.oid_tipo_vent = psoid
         AND gen.attr_enti = 'INC_TIPO_VENTA';
    END IF;
    IF psdescripcion = 'ACCESO' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             seg_acces           acc
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND acc.oid_acce = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND acc.oid_acce = psoid
         AND gen.attr_enti = 'SEG_ACCES';
    END IF;
    IF psdescripcion = 'DIRIGIDO' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_dirig           dir
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND dir.oid_diri = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND dir.oid_diri = psoid
         AND gen.attr_enti = 'INC_DIRIG';
    END IF;
    IF psdescripcion = 'BASE_CALCU' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_base_calcu      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_base_calc = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_base_calc = psoid
         AND gen.attr_enti = 'INC_BASE_CALCU';
    END IF;
    IF psdescripcion = 'TIPO_REQUI' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_tipo_requi      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_requ = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_requ = psoid
         AND gen.attr_enti = 'INC_TIPO_REQUI';
    END IF;
    IF psdescripcion = 'TIPO_ESTAT_CLIEN' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun  gen,
             seg_idiom            idi,
             mae_tipo_estat_clien base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_esta = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_esta = psoid
         AND gen.attr_enti = 'MAE_TIPO_ESTAT_CLIEN';
    END IF;
    IF psdescripcion = 'SEG_PERIO_CORPO' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             seg_perio_corpo     base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_peri = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_peri = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'MAE_ESTAT_CLIEN' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             mae_estat_clien     base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_esta_clie = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_esta_clie = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'CRA_PERIO' THEN
      SELECT base.val_nomb_peri
        INTO ls_descripcion
        FROM cra_perio base
       WHERE base.oid_peri = psoid;
    END IF;
    IF psdescripcion = 'INC_TIPO_EXIGE' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_tipo_exige      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_exig = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_exig = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_MONTO_VENTA_RECOM' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun        gen,
             seg_idiom                  idi,
             inc_tipo_monto_venta_recom base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_mont_vent_reco = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_mont_vent_reco = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_DESCU' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_tipo_descu      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_desc = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_desc = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_FORMA_PAGO' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_forma_pago      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_form_pago = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_form_pago = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'REC_MOTIV_RECHA_DESBL' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun   gen,
             seg_idiom             idi,
             rec_motiv_recha_desbl base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_moti_rech_desb = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_moti_rech_desb = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'REC_MOTIV_DEVOL' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             rec_motiv_devol    base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_moti_devo = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_moti_devo = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'REC_ESTAD_RECLA' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             rec_estad_recla     base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_esta_recl = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_esta_recl = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'REC_ESTAD_OPERA' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             rec_estad_opera     base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_esta_oper = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_esta_oper = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'EDU_TIPO_CURSO' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             edu_tipo_curso      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_curs = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_curs = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_PARTI_CONCU_CABEC' THEN
      SELECT gen.des_desc
        INTO ls_descripcion
        FROM inc_parti_concu_cabec gen
       WHERE gen.oid_part_conc_cabe = psoid;
    END IF;
    IF psdescripcion = 'MAE_UNIDA_NEGOC' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             mae_unida_negoc    base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_unid_nego = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_unid_nego = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'MAE_NEGOC' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             mae_negoc          base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_nego = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_nego = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'MAE_SUPER_GENER' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             mae_super_gener    base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_supe_gene = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_supe_gene = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'MAE_GENER' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             mae_gener          base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_gene = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_gene = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'MAE_PRODU' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             mae_produ          base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_prod = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_prod = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'REC_RESUL_CHEQU' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             rec_resul_chequ    base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_resu_cheq = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_resu_cheq = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_VIGEN_CONCU' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             inc_vigen_concu    base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_vige_conc = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_vige_conc = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_VIGEN_CONCU' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             inc_vigen_concu    base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_vige_conc = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_vige_conc = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_ESTAD_CONCU' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_estad_concu     base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_esta_conc = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_esta_conc = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'PRE_TIPO_OFERT' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             pre_tipo_ofert      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_ofer = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_ofer = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'PRE_CICLO_VIDA' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             pre_ciclo_vida      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_cicl_vida = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_cicl_vida = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_VENTA_CALIF' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun  gen,
             seg_idiom            idi,
             inc_tipo_venta_calif base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_vent_cali = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_vent_cali = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_VENTA_INCRE' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun  gen,
             seg_idiom            idi,
             inc_tipo_venta_incre base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_vent_incr = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_vent_incr = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_DETER_METAS' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun  gen,
             seg_idiom            idi,
             inc_tipo_deter_metas base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_dete_meta = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_dete_meta = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_FORMA_CALCU_METAS' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun   gen,
             seg_idiom             idi,
             inc_forma_calcu_metas base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_form_calc_meta = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_form_calc_meta = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_INCRE' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_tipo_incre      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_incr = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_incr = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_PREMI' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_tipo_premi      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_prem = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_prem = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_PCION' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_tipo_pcion      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_pion = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_pion = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_ELECC' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_tipo_elecc      base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_elec = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_elec = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_PREMI_MONET' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun  gen,
             seg_idiom            idi,
             inc_tipo_premi_monet base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_prem_mone = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_prem_mone = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'INC_TIPO_PREMI_PUNTO' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun  gen,
             seg_idiom            idi,
             inc_tipo_premi_punto base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_tipo_prem_punt = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_tipo_prem_punt = psoid
         AND gen.attr_enti = psdescripcion;
    END IF;
    IF psdescripcion = 'SEG_MONED' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             seg_moned           base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_mone = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_mone = psoid
         AND gen.attr_enti = 'SEG_MONED';
    END IF;
    IF psdescripcion = 'INC_FORMA_CALCU' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             inc_forma_calcu     base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_form_calc = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_form_calc = psoid
         AND gen.attr_enti = 'INC_FORMA_CALCU';
    END IF;
    IF psdescripcion = 'MAE_ESTAT_PRODU' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi,
             mae_estat_produ    base
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND base.oid_esta_prod = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND base.oid_esta_prod = psoid
         AND gen.attr_enti = 'MAE_ESTAT_PRODU';
    END IF;
    IF psdescripcion = 'PED_TIPO_SOLIC_PAIS' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             ped_tipo_solic_pais tsp,
             ped_tipo_solic      ts
       WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
         AND gen.idio_oid_idio = idi.oid_idio
         AND ts.oid_tipo_soli = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND tsp.oid_tipo_soli_pais = psoid
         AND gen.attr_enti = 'PED_TIPO_SOLIC';
    END IF;
    IF psdescripcion = 'DES_SOCIE' THEN
      SELECT val_deno INTO ls_descripcion FROM seg_socie p WHERE p.oid_soci = psoid;
    END IF;
    IF psdescripcion = 'MAE_PRODU_RECAL' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_pais gen,
             seg_idiom          idi
       WHERE gen.val_oid = psoid
         AND gen.idio_oid_idio = idi.oid_idio
         AND idi.cod_iso_idio = pscodigoiso
         AND gen.attr_enti = psdescripcion;
    END IF;

    IF psdescripcion = 'SEG_PAIS' THEN
      SELECT gen.val_i18n
        INTO ls_descripcion
        FROM gen_i18n_sicc_comun gen,
             seg_idiom           idi,
             seg_pais            pais
       WHERE gen.idio_oid_idio = idi.oid_idio
         AND pais.oid_pais = gen.val_oid
         AND idi.cod_iso_idio = pscodigoiso
         AND pais.oid_pais = psoid
         AND gen.attr_enti = 'SEG_PAIS';
    END IF;

    IF psdescripcion = 'INC_TIPO_PROG' THEN
      SELECT DES_TIPO_PROG
        INTO ls_descripcion
        FROM INC_CONCU_TIPO_PROG X
       WHERE X.OID_TIPO_PROG= TO_NUMBER(psoid);
    END IF;
    RETURN ls_descripcion;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN ls_descripcion;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_DESCRIPCION: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_descripcion;
  /**************************************************************************
  Descripcion : Devuelve Fecha Inicial Maxima de la Tabla CRA_CRONO
  basado en el id del periodo y el id de Zona
  Fecha Creacion : 20/09/2006
  Parametros Entrada :
  pnOidPeri : Id de Periodo
  pnOidZona : Id de Zona
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_fecha_ini_maxi_crono
  (
    pnoidperi NUMBER,
    pnoidzona NUMBER
  ) RETURN DATE IS
    ldfecha DATE;
  BEGIN
    SELECT MAX(x.fec_inic)
      INTO ldfecha
      FROM cra_crono x
     WHERE x.perd_oid_peri = pnoidperi
       AND x.zzon_oid_zona = pnoidzona;
    RETURN ldfecha;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_FECHA_INI_MAXI_CRONO: ' || ls_sqlerrm);
      END IF;
  END gen_fn_fecha_ini_maxi_crono;
  /**************************************************************************
  Descripcion : Actualiza los saldos deudores de los clientes
  Fecha Creacion : 17/10/2006
  Parametros Entrada:
  ps_cod_peri : codigo de periodo
  ps_cod_peri_cruc : codigo de periodo de cruce
  ps_saldo_rechazo : monto en que se rechaza al cliente
  Autor : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE gen_pr_actua_valor_saldo_deudo(ps_cod_pais IN VARCHAR) IS

    -- Cursor de periodos activos
    CURSOR c_periodos IS
      SELECT cod_peri FROM bas_ctrl_fact WHERE sta_camp = 0 ORDER BY cod_peri;

    r_periodos c_periodos%ROWTYPE;

    -- Cursor con los clientes a actualizar su saldo deudor
    CURSOR c_clientes(p_cod_peri VARCHAR2) IS
      SELECT r.cod_pais,
             r.cod_peri,
             r.cod_clie,
             r.fec_soli,
             r.num_lote
        FROM int_solic_conso_cabec r
       WHERE (r.ind_ocs_proc = 0) -- OCS no enviada a SiCC
         AND (r.ind_erro_remp = 0) -- Para evaluar el ultimo pedido
         AND (r.ind_proc_gp2 = 0) -- Para evaluar las que no han sido facturadas
         AND (r.cod_peri = p_cod_peri)
         AND (r.cod_pais = ps_cod_pais);
    r_clientes c_clientes%ROWTYPE;

    saldo_rechazo NUMBER;
    saldo_deudor  NUMBER;
    l_cod_peri    VARCHAR2(6);
  BEGIN
    -- Iteramos por los periodos activos
    OPEN c_periodos;
    LOOP
      FETCH c_periodos
        INTO r_periodos;
      EXIT WHEN c_periodos%NOTFOUND;
      -- Obtenemos el valor del periodo
      l_cod_peri := r_periodos.cod_peri;

      -- Obtenemos el valor del saldo de deuda minimo
      SELECT b.val_mnt_min_deud
        INTO saldo_rechazo
        FROM bas_ctrl_fact b
       WHERE b.cod_pais = ps_cod_pais
         AND b.cod_peri = l_cod_peri;

      -- Recorremos los clientes del periodo
      OPEN c_clientes(l_cod_peri);
      LOOP
        FETCH c_clientes
          INTO r_clientes;
        EXIT WHEN c_clientes%NOTFOUND;
        BEGIN
          -- Obtenemos el valor del saldo de la deuda
          saldo_deudor := gen_fn_calcu_valor_saldo_deudo(r_clientes.cod_clie);

          -- Actualizamos el saldo de deuda y el indicador
          UPDATE int_solic_conso_cabec r
             SET r.ind_erro_deud = decode(sign(saldo_deudor - saldo_rechazo),
                                          1,
                                          2,
                                          1),
                 r.val_sald_deud = saldo_deudor,
                 r.fec_modi      = SYSDATE
           WHERE r.cod_clie = r_clientes.cod_clie
             AND r.fec_soli = r_clientes.fec_soli
             AND r.cod_peri = r_clientes.cod_peri
             AND r.num_lote = r_clientes.num_lote
             AND r.cod_zona NOT IN (SELECT cod_zona
                                      FROM int_ped_zona_ofici
                                     WHERE est_zoof = '1'
                                       AND ind_vali_deud = 1);

          -- Para los pedidos de oficina no hacemos rechazo por deuda
          UPDATE int_solic_conso_cabec r
             SET r.ind_erro_deud = 1,
                 r.val_sald_deud = saldo_deudor,
                 r.fec_modi      = SYSDATE
           WHERE r.cod_clie = r_clientes.cod_clie
             AND r.fec_soli = r_clientes.fec_soli
             AND r.cod_peri = r_clientes.cod_peri
             AND r.num_lote = r_clientes.num_lote
             AND r.cod_zona IN (SELECT cod_zona
                                  FROM int_ped_zona_ofici
                                 WHERE est_zoof = '1'
                                   AND ind_vali_deud = 1);

        END;
      END LOOP;
      CLOSE c_clientes;

    END LOOP;
    CLOSE c_periodos;

  END gen_pr_actua_valor_saldo_deudo;
  /**************************************************************************
  Descripcion : Calcula el saldo deudor del cliente restando el valor en cupones
  Fecha Creacion : 17/10/2006
  Fecha Modificacion: 26/12/2006
  Parametros Entrada:
  ps_cod_clie : Codigo de cliente
  Autor : Lennon Shimokawa
  Modificaciones : Se esta retornando el valor 0 cuando el saldo de la deuda
  es negativo. (Carlos Hurtado Ramirez - 26/12/2006)
  ***************************************************************************/
  FUNCTION gen_fn_calcu_valor_saldo_deudo(ps_cod_clie IN VARCHAR2) RETURN NUMBER IS

    saldo      NUMBER := 0;
    saldofavor NUMBER := 0;
    cupon      NUMBER := 0;
    banco      NUMBER := 0;
    oid_clie   mae_clien.oid_clie%TYPE;
    res        NUMBER := 0;

  BEGIN

    -- Obtenemos el oid del cliente
    BEGIN
      SELECT oid_clie INTO oid_clie FROM mae_clien WHERE cod_clie = ps_cod_clie;
    EXCEPTION
      WHEN no_data_found THEN
        RETURN 0;
    END;

    -- Obtenemos el saldo del cliente (positivos)
    BEGIN
      -- Primero obtenemos los oid de marcas de situacion
      WITH temp_marca AS
       (SELECT ccc_marca_tipo_abono.masi_oid_marc_sali
          FROM ccc_proce,
               ccc_subpr,
               ccc_tipo_abono_subpr,
               ccc_marca_tipo_abono
         WHERE ((ccc_proce.oid_proc = ccc_subpr.ccpr_oid_proc) AND
               (ccc_subpr.oid_subp = ccc_tipo_abono_subpr.subp_oid_subp) AND
               (ccc_tipo_abono_subpr.oid_tipo_abon_subp =
               ccc_marca_tipo_abono.tasp_oid_tipo_abon_subp) AND (ccc_proce.cod_proc = 'CON001') AND
               (ccc_marca_tipo_abono.ind_entr_sali = 'E')))

      -- Calculamos la deuda en s?
      SELECT SUM (ccc_movim_cuent_corri.imp_pend)
        INTO saldo
        FROM ccc_movim_cuent_corri, temp_marca
       WHERE ccc_movim_cuent_corri.masi_oid_marc_situ = temp_marca.masi_oid_marc_sali AND ccc_movim_cuent_corri.imp_pend > 0 -- positivos
      AND SYSDATE - ccc_movim_cuent_corri.fec_venc > 0 -- Deudas vencidas
      AND ccc_movim_cuent_corri.clie_oid_clie = oid_clie;
      IF saldo IS NULL THEN
        saldo := 0;
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        saldo := 0;
    END;

    -- Obtenemos el saldo del cliente a favor (negativos)
    BEGIN
      WITH temp_marca AS
       (SELECT ccc_marca_tipo_abono.masi_oid_marc_sali
          FROM ccc_proce,
               ccc_subpr,
               ccc_tipo_abono_subpr,
               ccc_marca_tipo_abono
         WHERE ((ccc_proce.oid_proc = ccc_subpr.ccpr_oid_proc) AND
               (ccc_subpr.oid_subp = ccc_tipo_abono_subpr.subp_oid_subp) AND
               (ccc_tipo_abono_subpr.oid_tipo_abon_subp =
               ccc_marca_tipo_abono.tasp_oid_tipo_abon_subp) AND (ccc_proce.cod_proc = 'CON001') AND
               (ccc_marca_tipo_abono.ind_entr_sali = 'E')))

      -- Calculamos la deuda en s?
      SELECT SUM (ccc_movim_cuent_corri.imp_pend)
        INTO saldofavor
        FROM ccc_movim_cuent_corri, temp_marca
       WHERE ccc_movim_cuent_corri.masi_oid_marc_situ = temp_marca.masi_oid_marc_sali AND ccc_movim_cuent_corri.imp_pend < 0 -- negativos
      AND ccc_movim_cuent_corri.clie_oid_clie = oid_clie;
      IF saldofavor IS NULL THEN
        saldofavor := 0;
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        saldofavor := 0;
    END;

    -- Obtenemos los movimientos bancarios
    BEGIN
      SELECT SUM(imp_sald_pend)
        INTO banco
        FROM ccc_movim_banca
       WHERE clie_oid_clie = oid_clie
         AND cod_iden_proc = 'P';
      IF banco IS NULL THEN
        banco := 0;
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        banco := 0;
    END;

    -- Obtengo el valor en cupones del cliente
    BEGIN
      SELECT SUM(ccc_detal_cupon_trami_depur.imp_deta)
        INTO cupon
        FROM mae_clien,
             ccc_detal_cupon_trami_depur,
             ccc_situa_cupon
       WHERE ((ccc_situa_cupon.oid_situ_cupo = ccc_detal_cupon_trami_depur.sicu_oid_situ_cupo) AND
             (ccc_situa_cupon.cod_situ_cupo = 'T') AND
             (mae_clien.oid_clie = ccc_detal_cupon_trami_depur.clie_oid_clie) AND
             (mae_clien.cod_clie = ps_cod_clie));
      IF cupon IS NULL THEN
        cupon := 0;
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        cupon := 0;
    END;

    res := saldo + saldofavor - banco - cupon;
    -- Si el saldo de la deuda es negativo, devolvemos 0
    IF res < 0 THEN
      res := 0;
    END IF;
    RETURN res;
  END gen_fn_calcu_valor_saldo_deudo;

  FUNCTION gen_fn_exist_clien(pscodcliente VARCHAR2) RETURN BOOLEAN IS
    ls_count NUMBER;
  BEGIN
    SELECT COUNT(1) INTO ls_count FROM mae_clien c WHERE c.cod_clie = pscodcliente;
    IF ls_count > 0 THEN
      RETURN TRUE;
    ELSE
      RETURN FALSE;
    END IF;
  END gen_fn_exist_clien;

  /***************************************************************************
  Descripcion : Devuelve si un cliente es
  gerente de zona o gerente de region
  Retorna el 0 o 1
  Fecha Creacion : 23/04/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_exist_clien_geren(oidcliente NUMBER) RETURN NUMBER IS
    lsretorno NUMBER;
  BEGIN
    lsretorno := 0;

    SELECT COUNT(*) INTO lsretorno FROM zon_zona WHERE zon_zona.clie_oid_clie = oidcliente;

    IF (lsretorno > 0) THEN
      lsretorno := 1;
    ELSE
      SELECT COUNT(*) INTO lsretorno FROM zon_regio WHERE zon_regio.clie_oid_clie = oidcliente;

      IF (lsretorno > 0) THEN
        lsretorno := 1;
      ELSE
        lsretorno := 0;
      END IF;
    END IF;
    RETURN lsretorno;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      RETURN 1;
  END gen_fn_exist_clien_geren;

  FUNCTION gen_fn_perio_valid(pscodperiodo VARCHAR2) RETURN BOOLEAN IS
    ls_estado NUMBER;
  BEGIN
    SELECT cra_perio.val_esta
      INTO ls_estado
      FROM cra_perio,
           seg_perio_corpo
     WHERE ((seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri) AND
           (seg_perio_corpo.cod_peri = pscodperiodo));
    IF ls_estado = 1 THEN
      RETURN TRUE;
    ELSE
      RETURN FALSE;
    END IF;
  END gen_fn_perio_valid;
  FUNCTION gen_fn_clien_bloqu
  (
    pscodcliente     VARCHAR2,
    pscodtipobloqueo VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_bloqueo VARCHAR2(2);
  BEGIN
    SELECT COUNT(1)
      INTO ls_bloqueo
      FROM mae_clien       clien,
           mae_clien_bloqu bloqu,
           mae_tipo_bloqu  tipo
     WHERE clien.oid_clie = bloqu.clie_oid_clie
       AND bloqu.tibq_oid_tipo_bloq = tipo.oid_tipo_bloq
       AND tipo.cod_tipo_bloq = pscodtipobloqueo
       AND clien.cod_clie = pscodcliente
       AND bloqu.fec_desb IS NULL;
    RETURN ls_bloqueo;
  END gen_fn_clien_bloqu;

  FUNCTION gen_fn_clien_datos
  (
    pscodcliente VARCHAR2,
    pscampo      VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_result VARCHAR2(100);
  BEGIN
    IF (pscampo = 'COD_REGI') THEN
      SELECT nvl(zon_regio.cod_regi,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.cod_clie = pscodcliente) AND
             (rownum = 1) -- -- la data esta mal devuelve mas de un registro
             );
    END IF;
    IF (pscampo = 'OID_REGI') THEN
      SELECT nvl(zon_regio.oid_regi,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.cod_clie = pscodcliente) AND
             (rownum = 1) -- -- la data esta mal devuelve mas de un registro
             );
    END IF;
    IF (pscampo = 'DES_REGI') THEN
      SELECT nvl(zon_regio.des_regi,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.cod_clie = pscodcliente) AND
             (rownum = 1));
    END IF;
    IF (pscampo = 'COD_ZONA') THEN
      SELECT nvl(zon_zona.cod_zona,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.cod_clie = pscodcliente) AND
             (rownum = 1));
    END IF;
    IF (pscampo = 'OID_ZONA') THEN
      SELECT nvl(zon_zona.oid_zona,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.cod_clie = pscodcliente) AND
             (rownum = 1));
    END IF;
    IF (pscampo = 'DES_ZONA') THEN
      SELECT nvl(zon_zona.des_zona,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.cod_clie = pscodcliente) AND
             (rownum = 1));
    END IF;

    IF (pscampo = 'COD_SECC') THEN
      SELECT nvl(zon_secci.cod_secc,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.cod_clie = pscodcliente) AND
             (rownum = 1));
    END IF;
    IF (pscampo = 'DES_SECC') THEN
      SELECT nvl(zon_secci.des_secci,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.cod_clie = pscodcliente) AND
             (rownum = 1));
    END IF;

    IF (pscampo = 'NOM_CLIE') THEN
      SELECT TRIM(mae_clien.val_nom1) || ' ' || TRIM(mae_clien.val_nom2) || ' ' ||
             TRIM(mae_clien.val_ape1) || ' ' || TRIM(mae_clien.val_ape2)
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.cod_clie = pscodcliente) AND
             (rownum = 1));
    END IF;
    IF (pscampo = 'COD_TERR') THEN
      SELECT nvl(zon_terri.cod_terr,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_terri,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND
             zon_terri_admin.terr_oid_terr = zon_terri.oid_terr AND
             (mae_clien.cod_clie = pscodcliente) AND (rownum = 1));
    END IF;
    IF (pscampo = 'COD_SUBG_VENT') THEN
      SELECT cod_subg_vent
        INTO ls_result
        FROM mae_clien_unida_admin a,
             zon_terri_admin       b,
             zon_secci             c,
             zon_zona              d,
             zon_regio             e,
             zon_sub_geren_venta   f,
             mae_clien             g
       WHERE a.ztad_oid_terr_admi = b.oid_terr_admi
         AND b.zscc_oid_secc = c.oid_secc
         AND c.zzon_oid_zona = d.oid_zona
         AND d.zorg_oid_regi = e.oid_regi
         AND e.zsgv_oid_subg_vent = f.oid_subg_vent
         AND a.ind_acti = 1
         AND c.ind_acti = 1
         AND d.ind_acti = 1
         AND e.ind_acti = 1
         AND f.ind_acti = 1
         AND g.oid_clie = a.oid_clie_unid_admi
         AND g.cod_clie = pscodcliente
         AND (rownum = 1);
    END IF;
    RETURN ls_result;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
  END gen_fn_clien_datos;
  /**************************************************************************
  Descripcion : Obtiene los datos del Cliente segun Codigo
  Fecha Creacion : 18/06/2007
  Parametros Entrada :
  psCodCliente : Codigo del cliente
  psCampo : Nombre del campo a obtener
  COD_REGI: codigo de region
  DES_REGI: descripcion de region
  COD_ZONA: codigo de zona
  DES_ZONA: descripcion de zona
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_clien_datos_codig
  (
    pscodcliente VARCHAR2,
    pscampo      VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_result   VARCHAR2(500);
    lnidcliente NUMBER;

  BEGIN
    /* Obteniendo id del pais */
    lnidcliente := gen_fn_devuelve_id_cliente(pscodcliente,
                                              TRUE);
    IF lnidcliente = -1 THEN
      RETURN '';
    END IF;
    RETURN gen_fn_clien_datos_oid(lnidcliente,
                                  pscampo);
  END gen_fn_clien_datos_codig;

  /**************************************************************************
  Descripcion : Obtiene los datos del Cliente dados el OID
  Fecha Creacion : 21/11/2006
  Parametros Entrada :
  psCodCliente : Codigo del cliente
  psCampo : Nombre del campo a obtener
  COD_REGI: codigo de region
  DES_REGI: descripcion de region
  COD_ZONA: codigo de zona
  DES_ZONA: descripcion de zona
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_clien_datos_oid
  (
    psoidcliente VARCHAR2,
    pscampo      VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_result VARCHAR2(500);
  BEGIN
    IF (pscampo = 'COD_REGI') THEN
      SELECT nvl(zon_regio.cod_regi,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.oid_clie = psoidcliente) AND
             (rownum = 1) -- -- la data esta mal devuelve mas de un registro
             );
    END IF;
    IF (pscampo = 'DES_REGI') THEN
      SELECT nvl(zon_regio.des_regi,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.oid_clie = psoidcliente) AND
             (rownum = 1));
    END IF;
    IF (pscampo = 'COD_ZONA') THEN
      SELECT nvl(zon_zona.cod_zona,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.oid_clie = psoidcliente) AND
             (rownum = 1));
    END IF;
    IF (pscampo = 'DES_ZONA') THEN
      SELECT nvl(zon_zona.des_zona,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.oid_clie = psoidcliente) AND
             (rownum = 1));
    END IF;

    IF (pscampo = 'COD_SECC') THEN
      SELECT nvl(zon_secci.cod_secc,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.oid_clie = psoidcliente) AND
             (rownum = 1));
    END IF;
    IF (pscampo = 'DES_SECC') THEN
      SELECT nvl(zon_secci.des_secci,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.oid_clie = psoidcliente) AND
             (rownum = 1));
    END IF;

    IF (pscampo = 'NOM_CLIE') THEN
      SELECT TRIM(mae_clien.val_nom1) || ' ' || TRIM(mae_clien.val_nom2) || ' ' ||
             TRIM(mae_clien.val_ape1) || ' ' || TRIM(mae_clien.val_ape2)
        INTO ls_result
        FROM mae_clien
       WHERE (mae_clien.oid_clie = psoidcliente)
         AND (rownum = 1);
    END IF;
    IF (pscampo = 'COD_TERR') THEN
      SELECT nvl(zon_terri.cod_terr,
                 '')
        INTO ls_result
        FROM mae_clien,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_terri,
             zon_secci,
             zon_zona,
             zon_regio
       WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
             (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
             (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
             (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
             (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
             (mae_clien_unida_admin.ind_acti = '1') AND
             zon_terri_admin.terr_oid_terr = zon_terri.oid_terr AND
             (mae_clien.oid_clie = psoidcliente) AND (rownum = 1));
    END IF;
    IF (pscampo = 'DIR_CLIE') THEN
      BEGIN
        SELECT replace((nvl(TRIM(d.des_abrv_tipo_via),
                    ' ') || ' ' || nvl(TRIM(a.val_nomb_via),
                                        ' ')) || ' ' || TRIM(a.num_ppal) || ' ' || TRIM(a.val_obse),',','')
          INTO ls_result
          FROM zon_via         c,
               seg_tipo_via    d,
               mae_clien_direc a
         WHERE psoidcliente = a.clie_oid_clie
           AND c.oid_via = a.tivi_oid_tipo_via
           AND d.oid_tipo_via = a.tivi_oid_tipo_via
           AND ind_dire_ppal = 1
           AND ind_elim = 0;
      EXCEPTION
        WHEN no_data_found THEN
          /*Devuelve la direccion de cliente sin incluir la Via*/
          SELECT replace((nvl(TRIM(d.des_abrv_tipo_via),
                      ' ') || ' ' || nvl(TRIM(a.val_nomb_via),
                                          ' ')) || ' ' || TRIM(a.num_ppal) || ' ' ||
                 TRIM(a.val_obse),',','')
            INTO ls_result
            FROM seg_tipo_via    d,
                 mae_clien_direc a
           WHERE psoidcliente = a.clie_oid_clie
             AND d.oid_tipo_via = a.tivi_oid_tipo_via
             AND ind_dire_ppal = 1
             AND ind_elim = 0;
      END;
    END IF;
    IF (pscampo = 'DES_DIRE') THEN
      BEGIN
        SELECT (nvl(TRIM(d.des_abrv_tipo_via),
                    ' ') || ' ' || nvl(TRIM(a.val_nomb_via),
                                        ' ')) || ' ' || TRIM(a.num_ppal) || ' ' || TRIM(a.val_obse)
          INTO ls_result
          FROM zon_via         c,
               seg_tipo_via    d,
               mae_clien_direc a
         WHERE psoidcliente = a.clie_oid_clie
           AND c.oid_via = a.tivi_oid_tipo_via
           AND d.oid_tipo_via = a.tivi_oid_tipo_via
           AND ind_dire_ppal = 1
           AND ind_elim = 0;

      EXCEPTION
        WHEN no_data_found THEN
          /*Devuelve la descripicoin de la direccion de cliente sin incluir la Via*/
          SELECT (nvl(TRIM(d.des_abrv_tipo_via),
                      ' ') || ' ' || nvl(TRIM(a.val_nomb_via),
                                          ' ')) || ' ' || TRIM(a.num_ppal) || ' ' ||
                 TRIM(a.val_obse)
            INTO ls_result
            FROM seg_tipo_via    d,
                 mae_clien_direc a
           WHERE psoidcliente = a.clie_oid_clie
             AND d.oid_tipo_via = a.tivi_oid_tipo_via
             AND ind_dire_ppal = 1
             AND ind_elim = 0;

      END;
    END IF;

    IF (pscampo = 'DES_DPTO') THEN
      SELECT zon_valor_estru_geopo.des_geog
        INTO ls_result
        FROM zon_valor_estru_geopo
       WHERE zon_valor_estru_geopo.orde_1 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                1,
                                6)))
         AND zon_valor_estru_geopo.orde_2 IS NULL
         AND zon_valor_estru_geopo.orde_3 IS NULL
         AND zon_valor_estru_geopo.orde_4 IS NULL
         AND rownum = 1;
    END IF;
    IF (pscampo = 'DES_PROV') THEN
      SELECT zon_valor_estru_geopo.des_geog
        INTO ls_result
        FROM zon_valor_estru_geopo
       WHERE zon_valor_estru_geopo.orde_1 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                1,
                                6)))
         AND zon_valor_estru_geopo.orde_2 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                7,
                                6)))
         AND zon_valor_estru_geopo.orde_3 IS NULL
         AND zon_valor_estru_geopo.orde_4 IS NULL
         AND rownum = 1;
    END IF;
    IF (pscampo = 'DES_DIST') THEN
      SELECT zon_valor_estru_geopo.des_geog
        INTO ls_result
        FROM zon_valor_estru_geopo
       WHERE zon_valor_estru_geopo.orde_1 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                1,
                                6)))
         AND zon_valor_estru_geopo.orde_2 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                7,
                                6)))

         AND zon_valor_estru_geopo.orde_3 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                13,
                                6)))
         AND zon_valor_estru_geopo.orde_4 IS NULL
         AND rownum = 1;
    END IF;

    IF (pscampo = 'DES_URBA') THEN
      SELECT zon_valor_estru_geopo.des_geog
        INTO ls_result
        FROM zon_valor_estru_geopo
       WHERE zon_valor_estru_geopo.orde_1 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                1,
                                6)))
         AND zon_valor_estru_geopo.orde_2 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                7,
                                6)))
         AND zon_valor_estru_geopo.orde_3 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                13,
                                6)))
         AND zon_valor_estru_geopo.orde_4 =
             ltrim(rtrim(substr((SELECT mae_clien_direc.cod_unid_geog
                                  FROM mae_clien_direc
                                 WHERE (psoidcliente = mae_clien_direc.clie_oid_clie)
                                   AND ind_dire_ppal = 1
                                   AND ind_elim = 0),
                                19,
                                6)))
         AND rownum = 1;
    END IF;
    IF (substr(pscampo,
               1,
               8) = 'TEX_COMU') THEN
      SELECT mae_clien_comun.val_text_comu
        INTO ls_result
        FROM mae_clien_comun,
             own_comun.mae_tipo_comun
       WHERE mae_clien_comun.ticm_oid_tipo_comu = mae_tipo_comun.oid_tipo_comu
         AND mae_tipo_comun.cod_tipo_comu = substr(pscampo,
                                                   10,
                                                   2)
         AND mae_clien_comun.clie_oid_clie = psoidcliente;
    END IF;
    IF (pscampo = 'COD_ESTA_CLIE') THEN
      SELECT mae_estat_clien.cod_esta_clie
        INTO ls_result
        FROM mae_clien_datos_adici,
             mae_estat_clien
       WHERE mae_clien_datos_adici.clie_oid_clie = psoidcliente
         AND mae_clien_datos_adici.esta_oid_esta_clie = mae_estat_clien.oid_esta_clie;
    END IF;
    IF (pscampo = 'NUM_TELE') THEN
      SELECT mae_clien_comun.val_text_comu
        INTO ls_result
        FROM mae_clien_comun,
             mae_tipo_comun
       WHERE (mae_clien_comun.clie_oid_clie = psoidcliente)
         AND (mae_clien_comun.ticm_oid_tipo_comu = mae_tipo_comun.oid_tipo_comu)
         AND cod_tipo_comu IN ('TF',
                               'TM',
                               'TT')
         AND rownum = 1;
    END IF;

    IF (pscampo = 'COD_SUBG_VENT') THEN
      SELECT cod_subg_vent
        INTO ls_result
        FROM mae_clien_unida_admin a,
             zon_terri_admin       b,
             zon_secci             c,
             zon_zona              d,
             zon_regio             e,
             zon_sub_geren_venta   f
       WHERE a.clie_oid_clie = psoidcliente
         AND a.ztad_oid_terr_admi = b.oid_terr_admi
         AND b.zscc_oid_secc = c.oid_secc
         AND c.zzon_oid_zona = d.oid_zona
         AND d.zorg_oid_regi = e.oid_regi
         AND e.zsgv_oid_subg_vent = f.oid_subg_vent
         AND a.ind_acti = 1
         AND c.ind_acti = 1
         AND d.ind_acti = 1
         AND e.ind_acti = 1
         AND f.ind_acti = 1
         AND (rownum = 1);
    END IF;
    IF (pscampo = 'TEX_COMUN') THEN
      SELECT mae_clien_comun.val_text_comu
        INTO ls_result
        FROM mae_clien_comun

       WHERE mae_clien_comun.clie_oid_clie = psoidcliente
         AND mae_clien_comun.ticm_oid_tipo_comu = 1;

    END IF;
    IF (pscampo = 'DAT_DIR') THEN
       SELECT VAL_OBSE
         INTO  ls_result
         FROM (SELECT a.OID_CLIE_DIRE OID, a.VAL_OBSE, a.VAL_BARR, t.COD_TERR
                 FROM MAE_CLIEN_DIREC a, MAE_TIPO_DIREC b, ZON_TERRI t
                WHERE a.IND_ELIM = 0
                  AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                  AND a.IND_DIRE_PPAL = 1
                  AND a.TERR_OID_TERR = t.OID_TERR(+)
                  and a.clie_oid_clie = psoidcliente
                ORDER BY a.OID_CLIE_DIRE DESC)
        WHERE ROWNUM = 1;
    END IF;
    IF (pscampo = 'DAT_TER') THEN
       SELECT COD_TERR
         INTO  ls_result
         FROM (SELECT a.OID_CLIE_DIRE OID, a.VAL_OBSE, a.VAL_BARR, t.COD_TERR
                 FROM MAE_CLIEN_DIREC a, MAE_TIPO_DIREC b, ZON_TERRI t
                WHERE a.IND_ELIM = 0
                  AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                  AND a.IND_DIRE_PPAL = 1
                  AND a.TERR_OID_TERR = t.OID_TERR(+)
                  and a.clie_oid_clie = psoidcliente
                ORDER BY a.OID_CLIE_DIRE DESC)
        WHERE ROWNUM = 1;
    END IF;
    IF (pscampo = 'DAT_BAR') THEN
       SELECT VAL_BARR
         INTO  ls_result
         FROM (SELECT a.OID_CLIE_DIRE OID, a.VAL_OBSE, a.VAL_BARR, t.COD_TERR
                 FROM MAE_CLIEN_DIREC a, MAE_TIPO_DIREC b, ZON_TERRI t
                WHERE a.IND_ELIM = 0
                  AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                  AND a.IND_DIRE_PPAL = 1
                  AND a.TERR_OID_TERR = t.OID_TERR(+)
                  and a.clie_oid_clie = psoidcliente
                ORDER BY a.OID_CLIE_DIRE DESC)
        WHERE ROWNUM = 1;
    END IF;
    RETURN ls_result;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
  END gen_fn_clien_datos_oid;

  /**************************************************************************
  Descripcion : Obtiene los datos asociados al CCC_BANCO
  Fecha Creacion : 11/01/2007
  Parametros Entrada :
  psCodBanco : Codigo del banco
  psCampo : Nombre del campo a obtener
  OID_BANC: codigo de Banco
  DES_BANC: descripcion de Banco
  Autor : Marco Agurto
  ***************************************************************************/

  FUNCTION gen_fn_banco_datos
  (
    pscodbanco VARCHAR2,
    pscampo    VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_result VARCHAR2(100);
  BEGIN
    IF (pscampo = 'OID_BANC') THEN
      SELECT nvl(ccc_banco.oid_banc,
                 '')
        INTO ls_result
        FROM ccc_banco
       WHERE ccc_banco.cod_banc = pscodbanco;
    END IF;
    IF (pscampo = 'DES_BANC') THEN
      SELECT nvl(ccc_banco.des_banc,
                 '')
        INTO ls_result
        FROM ccc_banco
       WHERE ccc_banco.cod_banc = pscodbanco;
    END IF;
    RETURN ls_result;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
  END gen_fn_banco_datos;
  FUNCTION gen_fn_clien_texto_comun
  (
    psoidcliente       NUMBER,
    pstipocomunicacion VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_result VARCHAR2(100);
  BEGIN
    SELECT mae_clien_comun.val_text_comu
      INTO ls_result
      FROM mae_clien_comun,
           mae_tipo_comun
     WHERE mae_clien_comun.ticm_oid_tipo_comu = mae_tipo_comun.oid_tipo_comu
       AND mae_tipo_comun.cod_tipo_comu = pstipocomunicacion
       AND mae_clien_comun.clie_oid_clie = psoidcliente;
    RETURN ls_result;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
  END gen_fn_clien_texto_comun;
  FUNCTION gen_fn_clien_pedid_monto_minim
  (
    psoidcliente        NUMBER,
    pscodigotipocliente VARCHAR2
  ) RETURN NUMBER IS
    ls_montominimo NUMBER;
  BEGIN
    SELECT val_niv1
      INTO ls_montominimo
      FROM (
           SELECT distinct h.val_niv1
                , nvl(h.ticl_oid_tipo_clie,0)
                , nvl(h.sbti_oid_subt_clie,0)
                , nvl(h.tccl_oid_tipo_clas,0)
                , nvl(h.clas_oid_clas,0)
                , nvl(h.zzon_oid_zona,0)
                , nvl(h.zorg_oid_regi,0)
        FROM ped_monto_minim       h,
             zon_zona              k,
             zon_regio             l,
             mae_clien_tipo_subti  m,
             mae_clien_clasi       n,
             zon_terri_admin       q,
             zon_secci             r,
             mae_clien_unida_admin s
       WHERE m.clie_oid_clie = psoidcliente
        and s.clie_oid_clie = psoidcliente
         AND q.oid_terr_admi=s.ztad_oid_terr_admi
         AND q.zscc_oid_secc = r.oid_secc
         AND r.zzon_oid_zona = k.oid_zona
         AND k.zorg_oid_regi = l.oid_regi
         AND m.oid_clie_tipo_subt = n.ctsu_oid_clie_tipo_subt
         AND (m.ticl_oid_tipo_clie = h.ticl_oid_tipo_clie or h.ticl_oid_tipo_clie is null)
         AND (m.sbti_oid_subt_clie = h.sbti_oid_subt_clie or h.sbti_oid_subt_clie is null)
         AND (n.tccl_oid_tipo_clasi = h.tccl_oid_tipo_clas or h.tccl_oid_tipo_clas is null)
         AND (n.clas_oid_clas = h.clas_oid_clas or h.clas_oid_clas is null)
         AND (l.oid_regi = h.zorg_oid_regi or h.zorg_oid_regi is null)
         AND (k.oid_zona = h.zzon_oid_zona or h.zzon_oid_zona is null)
         and s.ind_acti=1
             ORDER BY  nvl(h.ticl_oid_tipo_clie,0) desc
          , nvl(h.sbti_oid_subt_clie,0) desc
          , nvl(h.tccl_oid_tipo_clas,0) desc
          , nvl(h.clas_oid_clas,0) desc
          , nvl(h.zzon_oid_zona,0) desc
          , nvl(h.zorg_oid_regi,0) desc

             )
     WHERE rownum = 1;

      /*SELECT DISTINCT ped_monto_minim.val_niv1,
                            ped_monto_minim.sbti_oid_subt_clie,
                            ped_monto_minim.tccl_oid_tipo_clas,
                            ped_monto_minim.clas_oid_clas
              FROM ped_monto_minim,
                   ped_tipo_solic_pais,
                   mae_tipo_clien,
                   mae_subti_clien,
                   mae_clien_tipo_subti,
                   ped_tipo_solic,
                   mae_clien_clasi
             WHERE ((ped_tipo_solic_pais.oid_tipo_soli_pais =
                   ped_monto_minim.tspa_oid_tipo_soli_pais) AND
                   (mae_tipo_clien.oid_tipo_clie = ped_monto_minim.ticl_oid_tipo_clie) AND
                   (mae_tipo_clien.oid_tipo_clie = mae_subti_clien.ticl_oid_tipo_clie) AND
                   (mae_tipo_clien.oid_tipo_clie = mae_clien_tipo_subti.ticl_oid_tipo_clie) AND
                   (mae_subti_clien.oid_subt_clie = mae_clien_tipo_subti.sbti_oid_subt_clie) AND
                   (ped_tipo_solic.oid_tipo_soli = ped_tipo_solic_pais.tsol_oid_tipo_soli) AND
                   (mae_clien_tipo_subti.sbti_oid_subt_clie = ped_monto_minim.sbti_oid_subt_clie)

                   AND (mae_clien_tipo_subti.oid_clie_tipo_subt =
                   mae_clien_clasi.ctsu_oid_clie_tipo_subt) AND
                   (mae_clien_tipo_subti.sbti_oid_subt_clie =
                   decode(ped_monto_minim.sbti_oid_subt_clie,
                            NULL,
                            mae_clien_tipo_subti.sbti_oid_subt_clie,
                            ped_monto_minim.sbti_oid_subt_clie)) AND
                   (mae_clien_clasi.tccl_oid_tipo_clasi =
                   decode(ped_monto_minim.tccl_oid_tipo_clas,
                            NULL,
                            mae_clien_clasi.tccl_oid_tipo_clasi,
                            ped_monto_minim.tccl_oid_tipo_clas)) AND
                   (mae_clien_clasi.clas_oid_clas =
                   decode(ped_monto_minim.clas_oid_clas,
                            NULL,
                            mae_clien_clasi.clas_oid_clas,
                            ped_monto_minim.clas_oid_clas))

                   AND (ped_tipo_solic.cod_tipo_soli = 'SOC') AND
                   (mae_tipo_clien.cod_tipo_clie =
                   nvl(pscodigotipocliente,
                         mae_tipo_clien.cod_tipo_clie)) AND
                   (mae_clien_tipo_subti.clie_oid_clie = psoidcliente))
             ORDER BY 2,
                      3,
                      4*/


    RETURN ls_montominimo;
  END gen_fn_clien_pedid_monto_minim;
  /**************************************************************************
  Descripcion : Obtiene la Descripcion del periodo de ingreso del cliente
  Fecha Creacion : 03/07/2007
  Parametros Entrada :
  psOidCliente : Oid del cliente
  psCampo : Nombre del campo a obtener
  COD_PERI: codigo de la campa?a de ingreso de la tabla
  SEG_PERIO_CORPO
  VAL_NOMB_PERI: descripcion de la campa?a de ingreso
  de la tabla CRA_PERIO
  Autor : Marco Agurto
  ***************************************************************************/

  FUNCTION gen_fn_clien_perio_ingre_descr
  (
    psoidcliente NUMBER,
    pscampo      VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_periodoingreso VARCHAR2(40);
  BEGIN
    IF (pscampo = 'COD_PERI') THEN
      SELECT MAX(seg_perio_corpo.cod_peri)
        INTO ls_periodoingreso
        FROM mae_clien_histo_estat,
             mae_estat_clien,
             seg_perio_corpo,
             cra_perio
       WHERE ((mae_estat_clien.oid_esta_clie = mae_clien_histo_estat.esta_oid_esta_clie) AND
             (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri) AND
             (cra_perio.oid_peri = mae_clien_histo_estat.perd_oid_peri) AND
             (mae_estat_clien.cod_esta_clie = '02') AND
             (mae_clien_histo_estat.clie_oid_clie = psoidcliente))
       GROUP BY mae_clien_histo_estat.clie_oid_clie;
    END IF;
    IF (pscampo = 'VAL_NOMB_PERI') THEN
      SELECT MAX(cra_perio.val_nomb_peri)
        INTO ls_periodoingreso
        FROM mae_clien_histo_estat,
             mae_estat_clien,
             cra_perio
       WHERE ((mae_estat_clien.oid_esta_clie = mae_clien_histo_estat.esta_oid_esta_clie) AND
             (cra_perio.oid_peri = mae_clien_histo_estat.perd_oid_peri) AND
             (mae_estat_clien.cod_esta_clie = '02') AND
             (mae_clien_histo_estat.clie_oid_clie = psoidcliente))
       GROUP BY mae_clien_histo_estat.clie_oid_clie;
    END IF;

    RETURN ls_periodoingreso;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
  END gen_fn_clien_perio_ingre_descr;

  /**************************************************************************
   Descripcion : Obtiene el periodo de Ingreso de la consultora,
   Si no lo encuentra en la tabla MAE_CLIEN_PRIME_CONTA
   lo obtiene de la tabla MAE_CLIEN_HISTO_ESTAT
   Fecha Creacion : 19/08/2009
   Autor : Jose Cairampoma
  **************************************************************************/
  FUNCTION gen_fn_clien_perio_ingre(psoidcliente NUMBER) RETURN NUMBER IS
    ls_periodoingreso NUMBER;
  BEGIN

    SELECT MAX(a.cod_peri)
      INTO ls_periodoingreso
      FROM mae_clien_prime_conta p,
           seg_perio_corpo       a,
           cra_perio             b
     WHERE clie_oid_clie = psoidcliente
       AND p.marc_oid_marc = '2003'
       AND p.cana_oid_cana = '2001'
       AND b.oid_peri = p.perd_oid_peri
       AND a.oid_peri = b.peri_oid_peri;

    IF ls_periodoingreso IS NULL THEN

      SELECT MAX(seg_perio_corpo.cod_peri)
        INTO ls_periodoingreso
        FROM mae_clien_histo_estat,
             mae_estat_clien,
             seg_perio_corpo,
             cra_perio
       WHERE ((mae_estat_clien.oid_esta_clie = mae_clien_histo_estat.esta_oid_esta_clie) AND
             (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri) AND
             (cra_perio.oid_peri = mae_clien_histo_estat.perd_oid_peri) AND
             (mae_estat_clien.cod_esta_clie = '02') AND
             (mae_clien_histo_estat.clie_oid_clie = psoidcliente))
       GROUP BY mae_clien_histo_estat.clie_oid_clie;
    END IF;

    RETURN ls_periodoingreso;

  END gen_fn_clien_perio_ingre;

  FUNCTION gen_fn_clien_fecha_egres(psoidcliente NUMBER) RETURN DATE IS
    ld_fechaegreso   DATE;
    ls_periodoegreso VARCHAR2(6);
  BEGIN
    SELECT MAX(seg_perio_corpo.cod_peri) cam_egre
      INTO ls_periodoegreso
      FROM mae_clien_histo_estat,
           mae_estat_clien,
           seg_perio_corpo,
           cra_perio
     WHERE ((mae_estat_clien.oid_esta_clie = mae_clien_histo_estat.esta_oid_esta_clie) AND
           (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri) AND
           (cra_perio.oid_peri = mae_clien_histo_estat.perd_oid_peri) AND
           (mae_estat_clien.cod_esta_clie = '05') AND
           (mae_clien_histo_estat.clie_oid_clie = psoidcliente));
    SELECT MAX(fac_contr_cierr.fec_cier)
      INTO ld_fechaegreso
      FROM fac_tipos_cierr,
           fac_contr_cierr,
           cra_perio,
           seg_perio_corpo
     WHERE ((fac_tipos_cierr.oid_tipo_cier = fac_contr_cierr.tcie_oid_tipo_cier) AND
           (fac_contr_cierr.perd_oid_peri = cra_perio.oid_peri) AND
           (cra_perio.peri_oid_peri = seg_perio_corpo.oid_peri) AND
           (seg_perio_corpo.cod_peri = ls_periodoegreso) AND (fac_tipos_cierr.cod_tipo_cier = 'R'));
    RETURN ld_fechaegreso;
  END gen_fn_clien_fecha_egres;

  FUNCTION gen_fn_clien_perio_cierr(psoidcliente NUMBER) RETURN VARCHAR2 IS
    ls_periodocierre VARCHAR2(6);
  BEGIN
    SELECT MAX(seg_perio_corpo.cod_peri)
      INTO ls_periodocierre
      FROM mae_clien_unida_admin,
           zon_terri_admin,
           zon_secci,
           zon_zona,
           zon_regio,
           zon_sub_geren_venta,
           fac_contr_cierr,
           seg_perio_corpo,
           cra_perio
     WHERE mae_clien_unida_admin.ztad_oid_terr_admi = zon_terri_admin.oid_terr_admi
       AND zon_terri_admin.zscc_oid_secc = zon_secci.oid_secc
       AND zon_secci.zzon_oid_zona = zon_zona.oid_zona
       AND zon_zona.zorg_oid_regi = zon_regio.oid_regi
       AND zon_regio.zsgv_oid_subg_vent = zon_sub_geren_venta.oid_subg_vent
       AND mae_clien_unida_admin.ind_acti = 1
       AND fac_contr_cierr.zorg_oid_regi = zon_regio.oid_regi
       AND fac_contr_cierr.tcie_oid_tipo_cier = 1
       AND fac_contr_cierr.perd_oid_peri = cra_perio.oid_peri
       AND seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri
       AND mae_clien_unida_admin.clie_oid_clie = psoidcliente;
    RETURN ls_periodocierre;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
  END gen_fn_clien_perio_cierr;

  FUNCTION gen_fn_clien_perio_ultim_pedid(psoidcliente NUMBER) RETURN VARCHAR2 IS
    ls_periodoultimopedido VARCHAR2(6);
  BEGIN
    SELECT MAX(seg_perio_corpo.cod_peri)
      INTO ls_periodoultimopedido
      FROM ped_solic_cabec,
           seg_subac,
           seg_acces,
           seg_canal,
           seg_perio_corpo,
           cra_perio,
           seg_marca
     WHERE ((seg_subac.oid_sbac = ped_solic_cabec.sbac_oid_sbac) AND
           (seg_acces.oid_acce = seg_subac.acce_oid_acce) AND
           (seg_canal.oid_cana = seg_acces.cana_oid_cana) AND
           (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri) AND
           (cra_perio.oid_peri = ped_solic_cabec.perd_oid_peri) AND
           (seg_marca.oid_marc = cra_perio.marc_oid_marc) AND
           (ped_solic_cabec.fec_fact IS NOT NULL) AND (ped_solic_cabec.ind_ts_no_conso = 1) AND
           (ped_solic_cabec.ind_oc = 1) AND (seg_acces.cod_acce = 'GZ') AND
           (seg_subac.cod_sbac = '000') AND (seg_canal.cod_cana = 'VD') AND
           (seg_marca.cod_marc = 'T') AND
           (ped_solic_cabec.tspa_oid_tipo_soli_pais =
           (SELECT tsp.oid_tipo_soli_pais
                FROM ped_tipo_solic_pais tsp,
                     ped_tipo_solic      ts
               WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND ts.cod_tipo_soli = 'SOC')) AND (ped_solic_cabec.clie_oid_clie = psoidcliente));
    RETURN ls_periodoultimopedido;
  END gen_fn_clien_perio_ultim_pedid;
  FUNCTION gen_fn_clien_monto_ultim_pedid(psoidcliente NUMBER) RETURN NUMBER IS
    ls_periodoultimopedido VARCHAR2(6);
    ln_montoultimopedido   NUMBER(12,
                                  2);
  BEGIN
    -- Obtenemos el ultimo periodo de pedido
    ls_periodoultimopedido := gen_pkg_gener.gen_fn_clien_perio_ultim_pedid(psoidcliente);
    ln_montoultimopedido   := gen_pkg_gener.gen_fn_clien_monto_ultim_pedid(psoidcliente,
                                                                           ls_periodoultimopedido);
    RETURN ln_montoultimopedido;
  END gen_fn_clien_monto_ultim_pedid;
  FUNCTION gen_fn_clien_monto_ultim_pedid
  (
    psoidcliente          NUMBER,
    psperiodoultimopedido IN VARCHAR2
  ) RETURN NUMBER IS
    ln_montoultimopedido NUMBER(12,
                                2);
  BEGIN
    IF psperiodoultimopedido IS NOT NULL THEN
      SELECT val_mnto_pedi
        INTO ln_montoultimopedido
        FROM (SELECT nvl(val_tota_paga_loca,
                         0) val_mnto_pedi,
                     row_number() over(ORDER BY ped_solic_cabec.oid_soli_cabe) row_num
                FROM ped_solic_cabec,
                     seg_subac,
                     seg_acces,
                     seg_canal,
                     seg_perio_corpo,
                     cra_perio,
                     seg_marca,
                     ped_tipo_solic_pais,
                     ped_tipo_solic
               WHERE ((seg_subac.oid_sbac = ped_solic_cabec.sbac_oid_sbac) AND
                     (seg_acces.oid_acce = seg_subac.acce_oid_acce) AND
                     (seg_canal.oid_cana = seg_acces.cana_oid_cana) AND
                     (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri) AND
                     (cra_perio.oid_peri = ped_solic_cabec.perd_oid_peri) AND
                     (seg_marca.oid_marc = cra_perio.marc_oid_marc) AND
                     (ped_solic_cabec.tspa_oid_tipo_soli_pais =
                     ped_tipo_solic_pais.oid_tipo_soli_pais) AND
                     (ped_tipo_solic_pais.tsol_oid_tipo_soli = ped_tipo_solic.oid_tipo_soli) AND
                     (ped_solic_cabec.fec_fact IS NOT NULL) AND (ped_solic_cabec.ind_oc = 1) AND
                     (ped_tipo_solic.ind_anul <> 1) AND (ped_tipo_solic.ind_devo <> 1) AND
                     (seg_acces.cod_acce = 'GZ') AND (seg_subac.cod_sbac = '000') AND
                     (seg_canal.cod_cana = 'VD') AND (seg_marca.cod_marc = 'T') AND
                     (ped_solic_cabec.clie_oid_clie = psoidcliente) AND
                     (seg_perio_corpo.cod_peri = psperiodoultimopedido)))
       WHERE row_num = 1;
    END IF;
    RETURN ln_montoultimopedido;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
  END gen_fn_clien_monto_ultim_pedid;
  FUNCTION gen_fn_clien_lider
  (
    psoidcliente        NUMBER,
    pscodigotipocliente VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_clientelider VARCHAR2(1);
    ln_count        NUMBER;
  BEGIN
    SELECT COUNT(*)
      INTO ln_count
      FROM mae_clien_clasi,
           mae_clien_tipo_subti,
           mae_tipo_clasi_clien,
           mae_tipo_clien,
           mae_clasi,
           mae_subti_clien
     WHERE ((mae_clien_tipo_subti.oid_clie_tipo_subt = mae_clien_clasi.ctsu_oid_clie_tipo_subt) AND
           (mae_tipo_clasi_clien.oid_tipo_clas = mae_clien_clasi.tccl_oid_tipo_clasi) AND
           (mae_tipo_clien.oid_tipo_clie = mae_clien_tipo_subti.ticl_oid_tipo_clie) AND
           (mae_tipo_clasi_clien.oid_tipo_clas = mae_clasi.tccl_oid_tipo_clas) AND
           (mae_clasi.oid_clas = mae_clien_clasi.clas_oid_clas) AND
           (mae_tipo_clien.oid_tipo_clie = mae_subti_clien.ticl_oid_tipo_clie) AND
           (mae_subti_clien.oid_subt_clie = mae_clien_tipo_subti.sbti_oid_subt_clie) AND
           (mae_subti_clien.oid_subt_clie = mae_tipo_clasi_clien.sbti_oid_subt_clie) AND
           (mae_clasi.cod_clas = '01') AND (mae_tipo_clasi_clien.cod_tipo_clas = '01') AND
           (mae_subti_clien.cod_subt_clie = '04') AND
           (mae_tipo_clien.cod_tipo_clie =
           nvl(pscodigotipocliente,
                 mae_tipo_clien.cod_tipo_clie)) AND
           (mae_clien_tipo_subti.clie_oid_clie = psoidcliente));
    IF ln_count = 0 THEN
      ls_clientelider := 'R';
    ELSE
      ls_clientelider := 'L';
    END IF;
    RETURN ls_clientelider;
  END gen_fn_clien_lider;
  /*
  FUNCTION GEN_FN_CLIEN_SALDO_DEUDA_TOTAL(psOidCliente NUMBER) RETURN NUMBER IS
  saldo NUMBER;
  BEGIN
  -- Obtengo el saldo del cliente
  SELECT SUM ((CASE
  WHEN SUBPROCESO.VAL_INDI_CONS = 'H'
  THEN CARGODETALLE.IMP * -1
  ELSE CARGODETALLE.IMP
  END)) AS MONOPE
  INTO saldo
  FROM CCC_DETAL_CARGO_ABONO_DIREC CARGODETALLE,
  CCC_CABEC_CARGA_ABONO_DIREC CARGOCABECERA,
  CCC_TIPO_ABONO_SUBPR TIPOABONO,
  CCC_SUBPR SUBPROCESO,
  CCC_PROCE PROCESO,
  CRA_PERIO PERIODOCRONOGRAMA,
  SEG_PERIO_CORPO PERIODOCORPORATIVO,
  CCC_MOVIM_CUENT_CORRI MOVCTACTE
  WHERE CARGODETALLE.CCAD_OID_CABE_CARG = CARGOCABECERA.OID_CABE_CARG
  AND CARGODETALLE.TASP_OID_TIPO_ABON_SUBP = TIPOABONO.OID_TIPO_ABON_SUBP
  AND TIPOABONO.SUBP_OID_SUBP = SUBPROCESO.OID_SUBP
  AND SUBPROCESO.VAL_INDI_CONS IN ('A', 'D', 'H')
  AND CARGODETALLE.MVCC_OID_MOVI_CC = MOVCTACTE.OID_MOVI_CC(+)
  AND SUBPROCESO.CCPR_OID_PROC = PROCESO.OID_PROC
  AND PERIODOCRONOGRAMA.PERI_OID_PERI = PERIODOCORPORATIVO.OID_PERI(+)
  AND MOVCTACTE.PERD_OID_PERI = PERIODOCRONOGRAMA.OID_PERI(+)
  AND CARGODETALLE.CLIE_OID_CLIE = psOidCliente;

  IF saldo IS NULL THEN
  saldo := 0;
  END IF;

  RETURN saldo;
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  saldo := 0;
  RETURN saldo;
  WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm,1,250);
  RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_CLIEN_SALDO_DEUDA_TOTAL: '||ls_sqlerrm);
  END GEN_FN_CLIEN_SALDO_DEUDA_TOTAL;
  */
  FUNCTION gen_fn_clien_saldo_deuda_total(psoidcliente NUMBER) RETURN NUMBER IS
    v_saldo         NUMBER(12,
                           2);
    v_imp_pend_movi NUMBER(12,
                           2);
    v_imp_pend_banc NUMBER(12,
                           2);
  BEGIN

    -- Obtenemos los importes pendientes
    BEGIN
      SELECT SUM(c.imp_pend)
        INTO v_imp_pend_movi
        FROM ccc_movim_cuent_corri c
       WHERE c.imp_pend > 0
         AND c.clie_oid_clie = psoidcliente;
      IF v_imp_pend_movi IS NULL THEN
        v_imp_pend_movi := 0;
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        v_imp_pend_movi := 0;
    END;

    -- Obtenemos los importes pendientes de bancos
    BEGIN
      SELECT SUM(b.imp_sald_pend)
        INTO v_imp_pend_banc
        FROM ccc_movim_banca b
       WHERE b.imp_sald_pend > 0
         AND b.cod_iden_proc = 'P'
         AND b.clie_oid_clie = psoidcliente;
      IF v_imp_pend_banc IS NULL THEN
        v_imp_pend_banc := 0;
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        v_imp_pend_banc := 0;
    END;

    v_saldo := v_imp_pend_movi - v_imp_pend_banc;

    IF v_saldo IS NULL THEN
      v_saldo := 0;
    END IF;

    RETURN v_saldo;
  EXCEPTION
    WHEN no_data_found THEN
      v_saldo := 0;
      RETURN v_saldo;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_CLIEN_SALDO_DEUDA_TOTAL: ' || ls_sqlerrm);
  END gen_fn_clien_saldo_deuda_total;

  FUNCTION gen_fn_clien_saldo_deuda_total(pscodcliente VARCHAR2) RETURN NUMBER IS
    v_oidcliente NUMBER;
    v_saldo      NUMBER := 0;
  BEGIN
    -- Obtenemos el oid del cliente
    SELECT oid_clie INTO v_oidcliente FROM mae_clien WHERE cod_clie = pscodcliente;

    -- Invocamos a la funcion que retorna el saldo en base al oid
    v_saldo := gen_fn_clien_saldo_deuda_total(v_oidcliente);

    RETURN v_saldo;
  EXCEPTION
    WHEN no_data_found THEN
      v_saldo := 0;
      RETURN v_saldo;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_CLIEN_SALDO_DEUDA_TOTAL: ' || ls_sqlerrm);
  END gen_fn_clien_saldo_deuda_total;

  FUNCTION gen_fn_descr_estru_geopo
  (
    psoidpais    NUMBER,
    psoidcliente NUMBER,
    psnivel      NUMBER
  ) RETURN VARCHAR2 IS
    ls_descripcion VARCHAR2(500);
  BEGIN
    IF psnivel = 1 THEN
      SELECT nvl(zona.des_geog,
                 ' ')
        INTO ls_descripcion
        FROM zon_valor_estru_geopo zona,
             mae_clien_direc
       WHERE mae_clien_direc.clie_oid_clie = psoidcliente
         AND zona.orde_1 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       1,
                                       6))
         AND zona.orde_2 IS NULL
         AND zona.orde_3 IS NULL
         AND zona.orde_4 IS NULL
         AND zona.pais_oid_pais = psoidpais
            /*INI
            Para tener la direccion principal y vigente de la consultora
            Modificado 21/02/2007, Marco Agurto
            */
         AND mae_clien_direc.ind_dire_ppal = 1
         AND mae_clien_direc.ind_elim = 0
            /*FIN*/
         AND rownum = 1;
    ELSIF psnivel = 2 THEN
      SELECT nvl(zona.des_geog,
                 ' ')
        INTO ls_descripcion
        FROM zon_valor_estru_geopo zona,
             mae_clien_direc
       WHERE mae_clien_direc.clie_oid_clie = psoidcliente
         AND zona.orde_1 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       1,
                                       6))
         AND zona.orde_2 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       7,
                                       6))
         AND zona.orde_3 IS NULL
         AND zona.orde_4 IS NULL
         AND zona.pais_oid_pais = psoidpais
            /*INI
            Para tener la direccion principal y vigente de la consultora
            Modificado 21/02/2007, Marco Agurto
            */
         AND mae_clien_direc.ind_dire_ppal = 1
         AND mae_clien_direc.ind_elim = 0
            /*FIN*/
         AND rownum = 1;
    ELSIF psnivel = 3 THEN
      SELECT nvl(zona.des_geog,
                 ' ')
        INTO ls_descripcion
        FROM zon_valor_estru_geopo zona,
             mae_clien_direc
       WHERE mae_clien_direc.clie_oid_clie = psoidcliente
         AND zona.orde_1 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       1,
                                       6))
         AND zona.orde_2 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       7,
                                       6))
         AND zona.orde_3 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       13,
                                       6))
         AND zona.orde_4 IS NULL
         AND zona.pais_oid_pais = psoidpais
            /*INI
            Para tener la direccion principal y vigente de la consultora
            Modificado 21/02/2007, Marco Agurto
            */
         AND mae_clien_direc.ind_dire_ppal = 1
         AND mae_clien_direc.ind_elim = 0
            /*FIN*/
         AND rownum = 1;
    ELSIF psnivel = 4 THEN
      SELECT nvl(zona.des_geog,
                 ' ')
        INTO ls_descripcion
        FROM zon_valor_estru_geopo zona,
             mae_clien_direc
       WHERE mae_clien_direc.clie_oid_clie = psoidcliente
         AND zona.orde_1 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       1,
                                       6))
         AND zona.orde_2 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       7,
                                       6))
         AND zona.orde_3 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       13,
                                       6))
         AND zona.orde_4 = TRIM(substr(mae_clien_direc.cod_unid_geog,
                                       19,
                                       6))
         AND zona.pais_oid_pais = psoidpais
            /*INI
            Para tener la direccion principal y vigente de la consultora
            Modificado 21/02/2007, Marco Agurto
            */
         AND mae_clien_direc.ind_dire_ppal = 1
         AND mae_clien_direc.ind_elim = 0
            /*FIN*/
         AND rownum = 1;
    END IF;
    RETURN ls_descripcion;
  END gen_fn_descr_estru_geopo;
  /**************************************************************************
  Descripcion : Te devuelve el indicador de la plantilla concurso
  Fecha Creacion : 23/11/2006
  Parametros Entrada:
  psOid : Codigo de la Plantilla
  Autor : Marco Antonio Agurto Jimenez
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_indi_plantilla
  (
    psoid         VARCHAR2,
    psdescripcion VARCHAR2
  ) RETURN NUMBER IS
    ls_contador NUMBER;
  BEGIN
    ls_contador := 0;
    IF psoid IS NULL THEN
      RETURN ls_contador;
    END IF;
    IF psdescripcion = 'RECOMENDACION' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.bcal_oid_base_calc = 4;
    END IF;
    IF psdescripcion = 'IND_PROD_EXCL' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.ind_prod_excl = 1;
    END IF;
    IF psdescripcion = 'IND_PROD_BONI' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.ind_prod_boni = 1;
    END IF;
    IF psdescripcion = 'IND_PROD_EXIG' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.ind_prod_exig = 1;
    END IF;
    IF psdescripcion = 'IND_PROD_VALI' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.ind_prod_vali = 0;
    END IF;
    /*
    Si esta en 0 deberia de mostrar informacion
    Si esta en 1 no deberia de mostrar informacion p
    porque es para todas las UA
    */
    IF psdescripcion = 'VAL_AMBI_GEOG_COMP' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.val_ambi_geog_comp = 0;
    END IF;
    IF psdescripcion = 'CONSULTORA' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.diri_oid_diri = 1;
    END IF;
    IF psdescripcion = 'GERENCIA' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.diri_oid_diri = 2;
    END IF;
    IF psdescripcion = 'IND_PROG_NUEV' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.ind_prog_nuev = 1;
    END IF;
    IF psdescripcion = 'VAL_FASE_CALI' THEN
      SELECT COUNT(*)
        INTO ls_contador
        FROM inc_plant_concu a
       WHERE a.oid_plan_conc = psoid
         AND a.val_fase_cali = 1;
    END IF;
    RETURN ls_contador;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN ls_contador;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_DESCRIPCION: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devuelve_indi_plantilla;
  /***************************************************************************
  Descripcion : Funcion empleada en el calculo del reporte de
   monto mayor de SAC
  Fecha Creacion : 24/01/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_clien_monto_pedid
  (
    psoidcliente NUMBER,
    psoidperiodo NUMBER
  ) RETURN NUMBER IS
    ln_montopedido NUMBER(12,
                          2);

  BEGIN

    SELECT SUM(round((z.val_tota_paga_loca + (z.val_tota_paga_loca - z.val_impo_flet_tota_loca) *
                 decode(b.pais_oid_pais,
                                                2001,
                                                2,
                                                decode(b.pais_oid_pais,
                                                       2002,
                                                       2,
                                                       0)) / 100),
                 2))
      INTO ln_montopedido
      FROM ped_solic_cabec       a,
           ped_solic_cabec       z,
           mae_clien             b,
           mae_clien_unida_admin c,
           zon_terri_admin       d,
           zon_terri             e,
           zon_secci             h,
           zon_zona              f,
           zon_regio             g,
           ped_tipo_solic_pais   h,
           ped_tipo_solic        i
     WHERE a.clie_oid_clie = b.oid_clie
       AND b.oid_clie = c.clie_oid_clie
       AND c.ztad_oid_terr_admi = d.oid_terr_admi
       AND d.terr_oid_terr = e.oid_terr
       AND d.zscc_oid_secc = h.oid_secc
       AND h.zzon_oid_zona = f.oid_zona
       AND f.zorg_oid_regi = g.oid_regi
       AND a.tspa_oid_tipo_soli_pais = h.oid_tipo_soli_pais
       AND h.tsol_oid_tipo_soli = i.oid_tipo_soli
       AND i.cod_tipo_soli = 'SOC'
       AND a.fec_fact IS NOT NULL
       AND a.perd_oid_peri = psoidperiodo
       AND a.soca_oid_soli_cabe = z.oid_soli_cabe
       AND c.perd_oid_peri_fin IS NULL
       AND c.ind_acti = 1
       AND b.cod_clie = gen_pkg_gener.gen_fn_devuelve_cod_clie(psoidcliente)
       AND z.esso_oid_esta_soli <> 4;

    RETURN ln_montopedido;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      RETURN 0;
  END gen_fn_clien_monto_pedid;
  /***************************************************************************
  Descripcion : Genera Calendario para el a?o respectivo
  Fecha Creacion : 24/01/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_gener_calen
  (
    pscodigoanno VARCHAR2,
    pscodusuario VARCHAR2,
    pserror      OUT VARCHAR2
  ) IS
    lbencontro   BOOLEAN;
    lscodigoanio VARCHAR2(4);
    lsfecha      VARCHAR2(10);
    lnanno       NUMBER;
    lnannofecha  NUMBER;
    ldfecha      DATE;
  BEGIN
    lbencontro := TRUE;
    BEGIN
      SELECT cod_anio
        INTO lscodigoanio
        FROM bas_calen a
       WHERE a.cod_anio = pscodigoanno
         AND rownum = 1;
    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;
    IF lbencontro THEN
      pserror := 'El A?o ' || pscodigoanno || ' ya se encuentra Generado';
      RETURN;
    END IF;

    /* Generando las fechas respectivas */
    lsfecha := '01/01' || pscodigoanno;
    lnanno  := to_number(pscodigoanno);
    ldfecha := to_date(lsfecha,
                       'dd/mm/yyyy');
    WHILE TRUE
    LOOP
      lnannofecha := to_number(to_char(ldfecha,
                                       'yyyy'));
      IF lnannofecha <> lnanno THEN
        EXIT;
      END IF;
      INSERT INTO bas_calen
        (cod_anio,
         fec_cale,
         num_posi_sema,
         ind_feri,
         usu_digi,
         fec_digi)
      VALUES
        (pscodigoanno,
         ldfecha,
         to_char(ldfecha,
                 'D'),
         'N',
         pscodusuario,
         SYSDATE);
      ldfecha := ldfecha + 1;
    END LOOP;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      pserror    := ls_sqlerrm;
      RETURN;
  END gen_pr_gener_calen;
  /***************************************************************************
  Descripcion : Recupera Responsables x Unidad administrativa
  Fecha Creacion : 14/11/2006
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION per_fn_recup_respo_uniad
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2,
    pscodseccion VARCHAR2
  ) RETURN tregresponsableuniadm IS
    registro tregresponsableuniadm;

  BEGIN
    /* Flujo alternativo 1 */
    IF pscodzona IS NULL THEN
      SELECT e.cod_clie,
             e.val_ape1,
             e.val_ape2,
             e.val_nom1,
             e.val_nom2,
             e.oid_clie
        INTO registro.cod_clie,
             registro.val_ape1,
             registro.val_ape2,
             registro.val_nom1,
             registro.val_nom2,
             registro.oid_clie
        FROM seg_pais  a,
             seg_marca b,
             seg_canal c,
             zon_regio d,
             mae_clien e
       WHERE a.cod_pais = pscodpais
         AND b.cod_marc = pscodmarca
         AND c.cod_cana = pscodcanal
         AND d.cod_regi = pscodregion
         AND d.ind_acti = 1
         AND (a.oid_pais = d.pais_oid_pais)
         AND (a.oid_pais = e.pais_oid_pais)
         AND (b.oid_marc = d.marc_oid_marc)
         AND (c.oid_cana = d.cana_oid_cana)
         AND (e.oid_clie = d.clie_oid_clie)
         AND rownum = 1;
      RETURN registro;
    END IF;

    /* Flujo alternativo 2 */
    IF pscodseccion IS NULL THEN
      SELECT e.cod_clie,
             e.val_ape1,
             e.val_ape2,
             e.val_nom1,
             e.val_nom2,
             e.oid_clie
        INTO registro.cod_clie,
             registro.val_ape1,
             registro.val_ape2,
             registro.val_nom1,
             registro.val_nom2,
             registro.oid_clie
        FROM seg_pais              a,
             seg_marca             b,
             seg_canal             c,
             zon_zona              d,
             mae_clien             e,
             mae_clien_datos_adici f
       WHERE a.cod_pais = pscodpais
         AND b.cod_marc = pscodmarca
         AND c.cod_cana = pscodcanal
         AND d.cod_zona = pscodzona
         AND d.ind_acti = 1
         AND f.clie_oid_clie = e.oid_clie
         AND f.cod_empl IS NOT NULL
         AND (a.oid_pais = e.pais_oid_pais)
         AND (a.oid_pais = d.pais_oid_pais)
         AND (b.oid_marc = d.marc_oid_marc)
         AND (c.oid_cana = d.cana_oid_cana)
         AND (e.oid_clie = d.clie_oid_clie)
         AND rownum = 1;
      RETURN registro;
    END IF;

    /* Obteniendo Registro */
    WITH temporal AS
     (SELECT e.clie_oid_clie
        FROM seg_pais  a,
             seg_marca b,
             seg_canal c,
             zon_zona  d,
             zon_secci e
       WHERE a.cod_pais = pscodpais
         AND b.cod_marc = pscodmarca
         AND c.cod_cana = pscodcanal
         AND d.cod_zona = pscodzona
         AND e.cod_secc = pscodseccion
         AND d.ind_acti = 1
         AND e.ind_acti = 1
         AND (a.oid_pais = d.pais_oid_pais)
         AND (b.oid_marc = d.marc_oid_marc)
         AND (c.oid_cana = d.cana_oid_cana)
         AND (d.oid_zona = e.zzon_oid_zona))
    SELECT x.cod_clie, x.val_ape1, x.val_ape2, x.val_nom1, x.val_nom2, x.oid_clie
      INTO registro.cod_clie, registro.val_ape1, registro.val_ape2, registro.val_nom1, registro.val_nom2, registro.oid_clie
      FROM mae_clien x, temporal y
     WHERE x.oid_clie = y.clie_oid_clie AND rownum = 1;

    RETURN registro;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN registro;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR PER_FN_RECUP_RESPO_UNIAD: ' || ls_sqlerrm);
  END per_fn_recup_respo_uniad;

  /***************************************************************************
  Descripcion : Recupera Responsables x Unidad administrativa
  Fecha Creacion : 04/01/2006
  psTipo
  C : Devuelve Codigo respectivo del responsable
  N : Devuelve Nombre respectivo del responsable
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION per_fn_recup_respo_uniad
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2,
    pscodseccion VARCHAR2,
    pstipo       VARCHAR2
  ) RETURN VARCHAR2 IS
    registro tregresponsableuniadm;

  BEGIN
    registro := per_fn_recup_respo_uniad(pscodpais,
                                         pscodmarca,
                                         pscodcanal,
                                         pscodregion,
                                         pscodzona,
                                         pscodseccion);
    IF pstipo = 'C' THEN
      RETURN registro.cod_clie;
    ELSIF pstipo = 'O' THEN
      RETURN registro.oid_clie;
    ELSIF pstipo = 'N' THEN
      RETURN nvl(TRIM(registro.val_ape1),
                 ' ') || ' ' || nvl(TRIM(registro.val_ape2),
                                    ' ') || ' ' || nvl(TRIM(registro.val_nom1),
                                                       ' ') || ' ' || nvl(TRIM(registro.val_nom2),
                                                                          ' ');
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR PER_FN_RECUP_RESPO_UNIAD: ' || ls_sqlerrm);
  END per_fn_recup_respo_uniad;

  /**************************************************************************
  Descripcion : Devuelve el Sgte Numero de Lote en base al pais,
  Sistema e Interfaz
  Fecha Creacion : 20/09/2006
  Parametros Entrada :
  Autor : Mrco Silva
  ***************************************************************************/
  PROCEDURE gen_pr_upd_lote_sgte_mica(pscodigopais VARCHAR2) AS
    p_numero      NUMBER(10);
    p_numerofinal VARCHAR2(10);
  BEGIN

    SELECT nvl(MAX(c.num_lote),
               0)
      INTO p_numero
      FROM bas_contr c
     WHERE c.pais_cod_pais = pscodigopais;

    SELECT substr(to_char((p_numero) + 100000001),
                  2)
      INTO p_numerofinal
      FROM dual;

    UPDATE bas_contr ctr SET ctr.num_lote = p_numerofinal WHERE ctr.pais_cod_pais = pscodigopais;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_PR_UPD_LOTE_SGTE_MICA: ' || ls_sqlerrm);
  END gen_pr_upd_lote_sgte_mica;

  /**************************************************************************
  Descripcion : Obtiene Descuentos Varios
  Fecha Creacion : 01/03/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devue_dscto_vario
  (
    pscodigoperiodo        VARCHAR2,
    pscodigotipocliente    VARCHAR2,
    pscodigosubtipocliente VARCHAR2,
    pscodigotipooferta     VARCHAR2,
    pscodigonegocio        VARCHAR2,
    pscodigounidadnegocio  VARCHAR2,
    pscodigomarcaproducto  VARCHAR2,
    pncomisionable         NUMBER := 1
  ) RETURN tregobtenerdsctovarios IS
    regdevuelve     tregobtenerdsctovarios;
    regregistro     dto_base_aplic_cabec%ROWTYPE;
    lnidperiini     dto_descu.perd_oid_peri%TYPE;
    lnidperifin     dto_descu.perd_oid_peri_limi_fin%TYPE;
    lscodperiini    seg_perio_corpo.cod_peri%TYPE;
    lscodperifin    seg_perio_corpo.cod_peri%TYPE;
    lnidtipocliente NUMBER;
    lbencontro      BOOLEAN;

  BEGIN

    /* Verificando si es Comisionable */
    IF pncomisionable = 0 THEN
      regdevuelve.valorespecidscto      := '0';
      regdevuelve.indicadorcomisionable := 0;
      regdevuelve.indicadorcomisionadic := 0;
      RETURN regdevuelve;
    END IF;

    /* Buscando registro en tabla DTO_BASE_APLIC_CABEC Sin Exclusion */
    lbencontro := TRUE;
    BEGIN

      SELECT a.*
        INTO regregistro
        FROM dto_base_aplic_cabec a,
             dto_base_aplic_detal b,
             pre_tipo_ofert       c,
             mae_negoc            d,
             mae_unida_negoc      e,
             seg_marca_produ      f
       WHERE c.cod_tipo_ofer = pscodigotipooferta
         AND f.cod_marc_prod = pscodigomarcaproducto
         AND d.cod_nego = pscodigonegocio
         AND e.cod_unid_nego = pscodigounidadnegocio
         AND b.ind_excl_tipo_ofer = 0

         AND a.oid_cabe = b.baca_oid_cabe
         AND c.oid_tipo_ofer = b.tofe_oid_tipo_ofer
         AND d.oid_nego = b.nego_oid_nego
         AND e.oid_unid_nego = b.uneg_oid_unid_nego
         AND f.oid_marc_prod = b.mapr_oid_marc_prod
         AND rownum = 1;

      /* Validando Descuento */
      SELECT a.perd_oid_peri,
             a.perd_oid_peri_limi_fin
        INTO lnidperiini,
             lnidperifin
        FROM dto_descu a
       WHERE a.oid_desc = regregistro.dcto_oid_desc;

      SELECT b.cod_peri
        INTO lscodperiini
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.oid_peri = lnidperiini
         AND b.oid_peri = a.peri_oid_peri;

      IF lscodperiini >= pscodigoperiodo THEN
        lbencontro := FALSE;
      END IF;

      IF lbencontro AND lnidperifin IS NOT NULL THEN
        SELECT b.cod_peri
          INTO lscodperifin
          FROM cra_perio       a,
               seg_perio_corpo b
         WHERE a.oid_peri = lnidperifin
           AND b.oid_peri = a.peri_oid_peri;

        IF lscodperifin <= pscodigoperiodo THEN
          lbencontro := FALSE;
        END IF;
      END IF;

      /* Validando Consultora y Negocio */
      IF lbencontro THEN
        SELECT a.oid_tipo_clie
          INTO lnidtipocliente
          FROM mae_tipo_clien        a,
               mae_subti_clien       b,
               dto_descu_subti_clien c
         WHERE a.cod_tipo_clie = '02'
           AND b.cod_subt_clie = '04'
           AND c.dcto_oid_desc = regregistro.dcto_oid_desc
           AND b.oid_subt_clie = c.sbti_oid_subt_clie
           AND a.oid_tipo_clie = b.ticl_oid_tipo_clie;

        /* Invocando a Obtener Descuento Especifico */
        regdevuelve.valorespecidscto := gen_fn_devue_dscto_espec(regregistro.oid_cabe);
        IF regdevuelve.valorespecidscto = 'C' THEN
          regdevuelve.indicadorcomisionable := 1;
          regdevuelve.indicadorcomisionadic := 1;
        ELSE
          regdevuelve.indicadorcomisionable := 1;
          regdevuelve.indicadorcomisionadic := 0;
        END IF;
        RETURN regdevuelve;

      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    /* Buscando registro en tabla DTO_BASE_APLIC_CABEC Con Exclusion */
    BEGIN
      SELECT a.*
        INTO regregistro
        FROM dto_base_aplic_cabec a,
             dto_base_aplic_detal b,
             pre_tipo_ofert       c,
             mae_negoc            d,
             mae_unida_negoc      e,
             seg_marca_produ      f
       WHERE --C.COD_TIPO_OFER = psCodigoTipoOferta
      --AND
       f.cod_marc_prod = pscodigomarcaproducto
       AND d.cod_nego = pscodigonegocio
       AND e.cod_unid_nego = pscodigounidadnegocio
       AND b.ind_excl_tipo_ofer = 1

       AND a.oid_cabe = b.baca_oid_cabe
       AND c.oid_tipo_ofer = b.tofe_oid_tipo_ofer
       AND d.oid_nego = b.nego_oid_nego
       AND e.oid_unid_nego = b.uneg_oid_unid_nego
       AND f.oid_marc_prod = b.mapr_oid_marc_prod
       AND rownum = 1;
    EXCEPTION
      WHEN no_data_found THEN
        regdevuelve.valorespecidscto := 'E';
        RETURN regdevuelve;

      WHEN OTHERS THEN
        regdevuelve.valorespecidscto := 'E';
        RETURN regdevuelve;
        --RAISE_APPLICATION_ERROR(-20123, 'No se encontraron Registros con Exclusion');
    END;

    /* Validando Descuento */
    BEGIN
      SELECT a.perd_oid_peri,
             a.perd_oid_peri_limi_fin
        INTO lnidperiini,
             lnidperifin
        FROM dto_descu a
       WHERE a.oid_desc = regregistro.dcto_oid_desc;
    EXCEPTION
      WHEN OTHERS THEN
        regdevuelve.valorespecidscto := 'E';
        RETURN regdevuelve;
        --RAISE_APPLICATION_ERROR(-20123, 'No se encontro Registro con Descuento con ID: '||regRegistro.Dcto_Oid_Desc);
    END;

    BEGIN
      SELECT b.cod_peri
        INTO lscodperiini
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.oid_peri = lnidperiini
         AND b.oid_peri = a.peri_oid_peri;
    EXCEPTION
      WHEN OTHERS THEN
        regdevuelve.valorespecidscto := 'E';
        RETURN regdevuelve;
        --RAISE_APPLICATION_ERROR(-20123, 'No se encontro Codigo de Periodo correspondiente al ID: '||lnIdPeriIni);
    END;

    IF lscodperiini >= pscodigoperiodo THEN
      regdevuelve.valorespecidscto := 'E';
      RETURN regdevuelve;
      --RAISE_APPLICATION_ERROR(-20123, 'ID de Descuento no es Vigente para Periodo '||psCodigoPeriodo);
    END IF;

    IF lnidperifin IS NOT NULL THEN
      BEGIN
        SELECT b.cod_peri
          INTO lscodperifin
          FROM cra_perio       a,
               seg_perio_corpo b
         WHERE a.oid_peri = lnidperifin
           AND b.oid_peri = a.peri_oid_peri;

        IF lscodperifin <= pscodigoperiodo THEN
          regdevuelve.valorespecidscto := 'E';
          RETURN regdevuelve;
          --RAISE_APPLICATION_ERROR(-20123, 'ID de Descuento no es Vigente para Periodo '||psCodigoPeriodo);
        END IF;
      EXCEPTION
        WHEN OTHERS THEN
          regdevuelve.valorespecidscto := 'E';
          RETURN regdevuelve;
          -- RAISE_APPLICATION_ERROR(-20123, 'No se encontro Codigo de Periodo correspondiente al ID: '||lsCodPeriFin);
      END;
    END IF;

    /* Validando Consultora y Negocio */
    BEGIN
      SELECT a.oid_tipo_clie
        INTO lnidtipocliente
        FROM mae_tipo_clien        a,
             mae_subti_clien       b,
             dto_descu_subti_clien c
       WHERE a.cod_tipo_clie = '02'
         AND b.cod_subt_clie = '04'
         AND c.dcto_oid_desc = regregistro.dcto_oid_desc
         AND b.oid_subt_clie = c.sbti_oid_subt_clie
         AND a.oid_tipo_clie = b.ticl_oid_tipo_clie;
    EXCEPTION
      WHEN OTHERS THEN
        regdevuelve.valorespecidscto := 'E';
        RETURN regdevuelve;
        --RAISE_APPLICATION_ERROR(-20123, 'ID de Descuento no es valido para Tipo cliente: Consultora y Subtipo:Cliente' );
    END;

    /* Invocando a Obtener Descuento Especifico */
    regdevuelve.valorespecidscto := gen_fn_devue_dscto_espec(regregistro.oid_cabe);
    IF regdevuelve.valorespecidscto = 'C' THEN
      regdevuelve.indicadorcomisionable := 1;
      regdevuelve.indicadorcomisionadic := 1;
    ELSE
      regdevuelve.indicadorcomisionable := 1;
      regdevuelve.indicadorcomisionadic := 0;
    END IF;

    RETURN regdevuelve;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DSCTO_VARIO: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devue_dscto_vario;
  /**************************************************************************
  Descripcion : Obtiene El periodo Actual para la fecha de la Base de Datos
  Fecha Creacion : 31/07/2007
  Autor : Marco Agurto
  Parametros : psOidPais : Oid Pais
  psOidCanal: Ois Canal
  psParametro 'M' Minimo
  'X' Maximo
  ***************************************************************************/
  FUNCTION gen_fn_devue_perio_actua
  (
    psoidpais   NUMBER,
    psoidcanal  NUMBER,
    psparametro VARCHAR2
  ) RETURN VARCHAR2 IS
    lsperiodo seg_perio_corpo.cod_peri%TYPE;
  BEGIN

    IF (psparametro IS NULL OR psparametro = 'M') THEN
      SELECT MIN(seg_perio_corpo.cod_peri)
        INTO lsperiodo
        FROM cra_perio,
             seg_perio_corpo
       WHERE (psoidpais = cra_perio.pais_oid_pais)
         AND (psoidcanal = cra_perio.cana_oid_cana)
         AND (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri)
         AND (to_char(cra_perio.fec_inic,
                      'yyyymmdd') <= to_char(SYSDATE,
                                              'yyyymmdd'))
         AND (to_char(cra_perio.fec_fina,
                      'yyyymmdd') >= to_char(SYSDATE,
                                              'yyyymmdd'));
    ELSE
      SELECT MIN(seg_perio_corpo.cod_peri)
        INTO lsperiodo
        FROM cra_perio,
             seg_perio_corpo
       WHERE (psoidpais = cra_perio.pais_oid_pais)
         AND (psoidcanal = cra_perio.cana_oid_cana)
         AND (seg_perio_corpo.oid_peri = cra_perio.peri_oid_peri)
         AND (to_char(cra_perio.fec_inic,
                      'yyyymmdd') <= to_char(SYSDATE,
                                              'yyyymmdd'))
         AND (to_char(cra_perio.fec_fina,
                      'yyyymmdd') >= to_char(SYSDATE,
                                              'yyyymmdd'));
    END IF;
    RETURN lsperiodo;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_PERIO_ACTUA: ' || ls_sqlerrm);
      END IF;

  END gen_fn_devue_perio_actua;
  /**************************************************************************
  Descripcion : Obtiene Descuento Especifico
  Fecha Creacion : 01/03/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devue_dscto_espec(pnidbase NUMBER) RETURN VARCHAR2 IS
    lsdevuelve VARCHAR2(10);
  BEGIN
    SELECT TRIM(to_char(a.val_porc_desc))
      INTO lsdevuelve
      FROM dto_escln a
     WHERE a.baca_oid_cabe = pnidbase;

    RETURN lsdevuelve;

  EXCEPTION
    WHEN too_many_rows THEN
      RETURN 'C';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DSCTO_ESPEC: ' || ls_sqlerrm);
      END IF;

  END gen_fn_devue_dscto_espec;

  /**************************************************************************
  Descripcion : Obtiene
  Fecha Creacion : 03/03/2007
  Autor :
  ***************************************************************************/
  FUNCTION gen_fn_devue_dscto_gener
  (
    pnoidperiodo  NUMBER,
    pscodigoventa VARCHAR2
  )

   RETURN VARCHAR2 IS
    lbencontro BOOLEAN;
    lncontador NUMBER;

  BEGIN
    lbencontro := TRUE;

    /* (Valor Descuento = 0) No Comisionables */
    BEGIN
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.ind_comi = 0
         AND d.val_codi_vent = pscodigoventa;

      IF lncontador > 0 THEN
        RETURN '0';
      END IF;

    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    BEGIN
      /* (Valor Descuento = C) Esika y Cyzone Cosmeticos Fragancias, Maquillaje, Tratamiento Facial y Tratamiento Corporal */
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.cod_tipo_ofer NOT IN (21,
                                       23,
                                       33,
                                       53)
         AND (mp.cod_marc_prod = '02' OR mp.cod_marc_prod = '03')
         AND un.cod_unid_nego = '010'
         AND n.cod_nego IN ('0102',
                            '0101',
                            '0104',
                            '0105')
         AND tof.ind_comi = 1
         AND d.val_codi_vent = pscodigoventa;

      IF lncontador > 0 THEN
        RETURN 'C';
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    BEGIN
      /*(Valor Descuento = C) Ebel */
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.cod_tipo_ofer NOT IN (21,
                                       23,
                                       33,
                                       53)
         AND mp.cod_marc_prod = '01'
         AND tof.ind_comi = 1
         AND d.val_codi_vent = pscodigoventa
         --- NUEVA EXCLUSION DE UNIDAD DE NEGOCIO CUIDADO PERSONAL -----
         AND n.cod_nego <> '0103';
         
      IF lncontador > 0 THEN
        RETURN 'C';
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    BEGIN
      /* (Valor Descuento = 20%) Para los tipos de oferta 21,23,33, 53 */
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.cod_tipo_ofer IN (21,
                                   23,
                                   33,
                                   53)
         AND tof.ind_comi = 1
         AND d.val_codi_vent = pscodigoventa;

      IF lncontador > 0 THEN
        RETURN '20';
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    BEGIN
      /* (Valor Descuento = C) Esika y Cyzone Accesorios Bijouerie */
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.cod_tipo_ofer NOT IN (21,
                                       23,
                                       33,
                                       53)
         AND mp.cod_marc_prod IN ('02',
                                  '03','06')
         AND un.cod_unid_nego = '020'
         AND n.cod_nego = '0201'
         AND tof.ind_comi = 1
         AND d.val_codi_vent = pscodigoventa;

      IF lncontador > 0 THEN
        RETURN 'C';
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    BEGIN
      /* (Valor Descuento = 25%) Esika y Cyzone Cosmeticos Cuidado Personal -- SE INCLUYE A LBEL*/
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.cod_tipo_ofer NOT IN (21,
                                       23,
                                       33,
                                       53)
         AND (mp.cod_marc_prod = '02' OR mp.cod_marc_prod = '03' OR mp.cod_marc_prod = '01')
         AND un.cod_unid_nego = '010'
         AND n.cod_nego = '0103'
         AND tof.ind_comi = 1
         AND d.val_codi_vent = pscodigoventa;

      IF lncontador > 0 THEN
        RETURN '25';
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    BEGIN
      /* (Valor Descuento = 20%) Esika y Cyzone Moda */
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.cod_tipo_ofer NOT IN (21,
                                       23,
                                       33,
                                       53)
         AND (mp.cod_marc_prod = '02' OR mp.cod_marc_prod = '03')
         AND un.cod_unid_nego = '030'
         AND tof.ind_comi = 1
         AND d.val_codi_vent = pscodigoventa;

      IF lncontador > 0 THEN
        RETURN '20';
      END IF;

    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    BEGIN
      /* (Valor Descuento = 20%) Esika y Cyzone Accesorios Relojes y Lentes */
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.cod_tipo_ofer NOT IN (21,
                                       23,
                                       33,
                                       53)
         AND (mp.cod_marc_prod = '02' OR mp.cod_marc_prod = '03')
         AND un.cod_unid_nego = '020'
         AND (n.cod_nego = '0202' OR n.cod_nego = '0203')
         AND tof.ind_comi = 1
         AND d.val_codi_vent = pscodigoventa;

      IF lncontador > 0 THEN
        RETURN '20';
      END IF;

    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    BEGIN
      /* (Valor Descuento = 20%) Esika y Cyzone Cosmeticos Accesorios Cosmeticos */
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.cod_tipo_ofer NOT IN (21,
                                       23,
                                       33,
                                       53)
         AND (mp.cod_marc_prod = '02' OR mp.cod_marc_prod = '03')
         AND un.cod_unid_nego = '010'
         AND n.cod_nego = '0106'
         AND tof.ind_comi = 1
         AND d.val_codi_vent = pscodigoventa;

      IF lncontador > 0 THEN
        RETURN '20';
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    BEGIN
      /* (Valor Descuento = 20%) Esika y Cyzone Hogar */
      SELECT COUNT(d.val_codi_vent)
        INTO lncontador
        FROM pre_ofert_detal    d,
             mae_produ          m,
             mae_negoc          n,
             mae_unida_negoc    un,
             seg_marca_produ    mp,
             pre_tipo_ofert     tof,
             gen_i18n_sicc_pais gn,
             gen_i18n_sicc_pais gun
       WHERE EXISTS (SELECT ofde_oid_deta_ofer
                FROM pre_matri_factu       mf,
                     pre_matri_factu_cabec mfc
               WHERE mf.mfca_oid_cabe = mfc.oid_cabe
                 AND mfc.perd_oid_peri = pnoidperiodo
                 AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
         AND d.prod_oid_prod = m.oid_prod
         AND m.nego_oid_nego = n.oid_nego
         AND m.uneg_oid_unid_nego = un.oid_unid_nego
         AND m.mapr_oid_marc_prod = mp.oid_marc_prod
         AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND gn.val_oid = n.oid_nego
         AND gn.attr_enti LIKE 'MAE_NEGOC%'
         AND gun.val_oid = un.oid_unid_nego
         AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
         AND tof.cod_tipo_ofer NOT IN (21,
                                       23,
                                       33,
                                       53)
         AND (mp.cod_marc_prod = '02' OR mp.cod_marc_prod = '03')
         AND un.cod_unid_nego = '040'
         AND tof.ind_comi = 1
         AND d.val_codi_vent = pscodigoventa;

      IF lncontador > 0 THEN
        RETURN '20';
      END IF;
    EXCEPTION
      WHEN no_data_found THEN
        lbencontro := FALSE;
    END;

    /* (Valor Descuento = C) Esika y Cyzone Apoyo */
    SELECT COUNT(d.val_codi_vent)
      INTO lncontador
      FROM pre_ofert_detal    d,
           mae_produ          m,
           mae_negoc          n,
           mae_unida_negoc    un,
           seg_marca_produ    mp,
           pre_tipo_ofert     tof,
           gen_i18n_sicc_pais gn,
           gen_i18n_sicc_pais gun
     WHERE EXISTS (SELECT ofde_oid_deta_ofer
              FROM pre_matri_factu       mf,
                   pre_matri_factu_cabec mfc
             WHERE mf.mfca_oid_cabe = mfc.oid_cabe
               AND mfc.perd_oid_peri = pnoidperiodo
               AND mf.ofde_oid_deta_ofer = d.oid_deta_ofer)
       AND d.prod_oid_prod = m.oid_prod
       AND m.nego_oid_nego = n.oid_nego
       AND m.uneg_oid_unid_nego = un.oid_unid_nego
       AND m.mapr_oid_marc_prod = mp.oid_marc_prod
       AND d.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
       AND gn.val_oid = n.oid_nego
       AND gn.attr_enti LIKE 'MAE_NEGOC%'
       AND gun.val_oid = un.oid_unid_nego
       AND gun.attr_enti LIKE 'MAE_UNIDA_NEGOC%'
       AND tof.cod_tipo_ofer NOT IN (21,
                                     23,
                                     33,
                                     53)
       AND (mp.cod_marc_prod = '02' OR mp.cod_marc_prod = '03')
       AND un.cod_unid_nego = '050'
       AND tof.ind_comi = 1
       AND d.val_codi_vent = pscodigoventa;

    IF lncontador > 0 THEN
      RETURN 'C';
    END IF;
    -- En caso de no encontrarse en ninguna seccion
    RETURN 'E';
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 'E';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DSCTO_GENER: ' || ls_sqlerrm);
      END IF;

  END gen_fn_devue_dscto_gener;

  /**************************************************************************
  Descripcion : Obtiene Descripcion de las campa?as en la que esta
   fecha ingresada como parametro
  Fecha Creacion : 08/05/2007
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_devue_descr_campa_fecha(vsfecha VARCHAR2) RETURN VARCHAR2 IS
    CURSOR cperiodo IS
      SELECT b.cod_peri
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.fec_inic <= to_date(vsfecha,
                                   'DD/MM/YYYY')
         AND a.fec_fina >= to_date(vsfecha,
                                   'DD/MM/YYYY')
         AND a.peri_oid_peri = b.oid_peri
       ORDER BY b.cod_peri;

    lscodperi    seg_perio_corpo.cod_peri%TYPE;
    lscodperiini seg_perio_corpo.cod_peri%TYPE;
    lscodperifin seg_perio_corpo.cod_peri%TYPE;
    lncontador   NUMBER;
    lsretorno    VARCHAR2(50);

  BEGIN
    BEGIN
      SELECT COUNT(1)
        INTO lncontador
        FROM cra_perio       a,
             seg_perio_corpo b
       WHERE a.fec_inic <= to_date(vsfecha,
                                   'DD/MM/YYYY')
         AND a.fec_fina >= to_date(vsfecha,
                                   'DD/MM/YYYY')
         AND a.peri_oid_peri = b.oid_peri;

      IF lncontador IS NULL THEN
        RETURN NULL;
      END IF;

      /* En caso la fecha este en una sola campa?a */
      IF lncontador = 1 THEN
        SELECT b.cod_peri
          INTO lscodperi
          FROM cra_perio       a,
               seg_perio_corpo b
         WHERE a.fec_inic <= to_date(vsfecha,
                                     'DD/MM/YYYY')
           AND a.fec_fina >= to_date(vsfecha,
                                     'DD/MM/YYYY')
           AND a.peri_oid_peri = b.oid_peri;
        RETURN 'CAMPA?A ' || lscodperi;
      END IF;
      /* En caso exista cruce de campa?a */
      lsretorno  := 'DESDE CAMPA?A ';
      lncontador := 1;
      FOR c1 IN cperiodo
      LOOP
        IF lncontador = 1 THEN
          lscodperiini := c1.cod_peri;
        END IF;
        lncontador   := lncontador + 1;
        lscodperifin := c1.cod_peri;
      END LOOP;
      lsretorno := lsretorno || lscodperiini || ' A CAMPA?A ' || lscodperifin;
      RETURN lsretorno;

    EXCEPTION
      WHEN no_data_found THEN
        RETURN NULL;
    END;
  END gen_fn_devue_descr_campa_fecha;
  /***************************************************************************
  Descripcion : Recupera Fechas de Facturacion
  Fecha Creacion : 13/08/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_recup_fecha_activ
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodigozona VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodactiv   VARCHAR2
  ) RETURN DATE IS
    lnidpais   seg_pais.oid_pais%TYPE;
    lnidcanal  seg_canal.oid_cana%TYPE;
    lnidmarca  seg_marca.oid_marc%TYPE;
    ldfechaini DATE;
  BEGIN
    lnidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodpais);
    lnidcanal := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodcanal);
    lnidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodmarca);
    /* obteniendo Fecha de Inicio */
    SELECT b.fec_inic
      INTO ldfechaini
      FROM cra_activ       a,
           cra_crono       b,
           seg_perio_corpo c,
           cra_perio       d,
           zon_zona        e
     WHERE e.pais_oid_pais = lnidpais
       AND e.marc_oid_marc = lnidmarca
       AND e.cana_oid_cana = lnidcanal
       AND e.ind_acti = 1
       AND e.cod_zona = pscodigozona
       AND c.cod_peri = pscodperiodo
       AND a.pais_oid_pais = lnidpais
       AND a.marc_oid_marc = lnidmarca
       AND a.cana_oid_cana = lnidcanal
       AND a.cod_acti = pscodactiv
       AND (a.oid_acti = b.cact_oid_acti)
       AND (c.oid_peri = d.peri_oid_peri)
       AND (d.oid_peri = b.perd_oid_peri)
       AND (e.oid_zona = b.zzon_oid_zona)
       AND rownum = 1;
    RETURN ldfechaini;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_RECUP_FECHA_ACTIV: ' || ls_sqlerrm);
  END gen_fn_recup_fecha_activ;
  /***************************************************************************
  Descripcion : Recupera Fechas de Facturacion
  Fecha Creacion : 13/08/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_recup_fecha_activ
  (
    pnoidpais    NUMBER,
    pnoidmarca   NUMBER,
    pnoidcanal   NUMBER,
    pscodigozona VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodactiv   VARCHAR2
  ) RETURN DATE IS
    ldfechaini DATE;
  BEGIN
    /* obteniendo Fecha de Inicio */
    SELECT b.fec_inic
      INTO ldfechaini
      FROM cra_activ       a,
           cra_crono       b,
           seg_perio_corpo c,
           cra_perio       d,
           zon_zona        e
     WHERE e.pais_oid_pais = pnoidpais
       AND e.marc_oid_marc = pnoidmarca
       AND e.cana_oid_cana = pnoidcanal
       AND e.ind_acti = 1
       AND e.cod_zona = pscodigozona
       AND c.cod_peri = pscodperiodo
       AND a.pais_oid_pais = pnoidpais
       AND a.marc_oid_marc = pnoidmarca
       AND a.cana_oid_cana = pnoidcanal
       AND a.cod_acti = pscodactiv
       AND (a.oid_acti = b.cact_oid_acti)
       AND (c.oid_peri = d.peri_oid_peri)
       AND (d.oid_peri = b.perd_oid_peri)
       AND (e.oid_zona = b.zzon_oid_zona)
       AND rownum = 1;
    RETURN ldfechaini;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_RECUP_FECHA_ACTIV: ' || ls_sqlerrm);
  END gen_fn_recup_fecha_activ;

  /**************************************************************************
  Descripcion : Calcula el saldo deudor del cliente todas las deudas
  no solo las vencidas del periodo Siguiente
  Fecha Creacion : 08/08/2007
  Parametros Entrada:
  ps_oid_clie : Oid de cliente
  ldFechaVen : Fecha de Vencimiento
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_calcu_valor_sald_venci
  (
    ps_oid_clie   IN NUMBER,
    ldfechaven    DATE,
    ldfechaactual DATE
  ) RETURN NUMBER IS
    res  NUMBER := 0;
    temp DATE;
  BEGIN
    IF ldfechaactual > SYSDATE THEN
      temp := ldfechaactual;
    ELSE
      temp := ldfechaven;
    END IF;
    res := gen_fn_recup_monto_venci_percp(ps_oid_clie,
                                          NULL,
                                          temp,
                                          NULL);
    RETURN res;
  END gen_fn_calcu_valor_sald_venci;
  /**************************************************************************
  Descripcion : Calcula el saldo deudor del cliente restando el valor en cupones,
  considerando todas las deudas no solo las vencidas, (CHAR DECIMAL)
  Fecha Creacion : 02/20/2007
  Parametros Entrada:
  ps_cod_clie : Codigo de cliente
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_calcu_valor_sald_deci(ps_oid_clie IN NUMBER) RETURN VARCHAR2 IS
    res NUMBER := 0;
  BEGIN
    res := gen_pkg_gener.gen_fn_clien_saldo_deuda_total(ps_oid_clie);
    RETURN to_char(res,
                   '9999990D00',
                   'NLS_NUMERIC_CHARACTERS=''.,''');
  END gen_fn_calcu_valor_sald_deci;
  /**************************************************************************
  Descripcion : Calcula la fecha de vencimiento de un periodo.
  Fecha Creacion : 08/08/2007
  Parametros Entrada:
  ps_cod_clie : Codigo de cliente
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_venci
  (
    pscodzona  VARCHAR2,
    pscodpais  VARCHAR2,
    pscodcanal VARCHAR2,
    pscodmarca VARCHAR2,
    lsperiodo  VARCHAR2
  ) RETURN DATE IS
    res          NUMBER := 0;
    lsidperiodo  NUMBER;
    lnidpais     seg_pais.oid_pais%TYPE;
    lnidcanal    seg_canal.oid_cana%TYPE;
    lnidmarca    seg_marca.oid_marc%TYPE;
    pscodperiodo seg_perio_corpo.cod_peri%TYPE;
    lsperiodo1   seg_perio_corpo.cod_peri%TYPE;
    lsoidcliente NUMBER;
    ldfechaven   DATE;
  BEGIN
    ldfechaven := gen_pkg_gener.gen_fn_recup_fecha_activ(pscodpais,
                                                         pscodmarca,
                                                         pscodcanal,
                                                         pscodzona,
                                                         lsperiodo,
                                                         'EV');
    RETURN ldfechaven;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OBTIE_FECHA_VENCI: ' || ls_sqlerrm);
  END gen_fn_obtie_fecha_venci;

  /**************************************************************************
   Descripcion : Calcula la fecha de vencimiento de un determinado cliente
   y para un periodo determinado, dependiendo de si se haya
   realizado o no el cierre de region.
   Fecha Creacion : 29/10/2007
   Parametros Entrada:
   Parametros Entrada:
   psCodPais : Codigo de pais
   psCodCanal : Codigo de canal
   psCodMarca : Codigo de marca
   psCodPeriodo : Codigo de periodo
   psCodRegion : Codigo de region
   psCodZona : Codigo de zona
   psOidCliente : OID de cliente
   Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_venci
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2,
    psoidcliente NUMBER
  ) RETURN DATE IS

    res                   NUMBER := 0;
    lnidperiodo           NUMBER;
    lnidpais              seg_pais.oid_pais%TYPE;
    lnidcanal             seg_canal.oid_cana%TYPE;
    lnidmarca             seg_marca.oid_marc%TYPE;
    lnidregion            zon_regio.oid_regi%TYPE;
    lscodperiodosiguiente VARCHAR2(6);
    ldfechaven            DATE;
    ldfechacierreregion   DATE;
    ldfechaactual         DATE;

  BEGIN
    lnidpais              := gen_fn_devuelve_id_pais(pscodpais,
                                                     TRUE);
    lnidmarca             := gen_fn_devuelve_id_marca(pscodmarca,
                                                      TRUE);
    lnidcanal             := gen_fn_devuelve_id_canal(pscodcanal,
                                                      TRUE);
    lnidperiodo           := gen_fn_devuelve_id_cra_perio(pscodperiodo,
                                                          lnidmarca,
                                                          lnidcanal,
                                                          TRUE);
    lnidregion            := gen_fn_devuelve_id_region(pscodpais,
                                                       pscodmarca,
                                                       pscodcanal,
                                                       pscodregion,
                                                       TRUE);
    lscodperiodosiguiente := gen_fn_calcu_perio(pscodperiodo,
                                                1);

    -- Obtenemos la fecha actual
    SELECT SYSDATE INTO ldfechaactual FROM dual;

    -- Validamos si el cliente paso pedido
    SELECT COUNT(*)
      INTO res
      FROM ped_solic_cabec p
     WHERE p.clie_oid_clie = psoidcliente
       AND p.perd_oid_peri = lnidperiodo
       AND p.ind_oc = 1;

    -- En caso si ha pasado pedido
    IF res > 0 THEN
      -- Obtenemos la fecha de la actividad FA del siguiente periodo
      ldfechaven := gen_pkg_gener.gen_fn_recup_fecha_activ(pscodpais,
                                                           pscodmarca,
                                                           pscodcanal,
                                                           pscodzona,
                                                           lscodperiodosiguiente,
                                                           'FA');
      -- En caso no haya pasado pedido
    ELSE
      -- Obtenemos la fecha de cierre de la region
      SELECT COUNT(*)
        INTO res
        FROM fac_contr_cierr a
       WHERE a.pais_oid_pais = lnidpais
         AND a.perd_oid_peri = lnidperiodo
         AND a.zorg_oid_regi = lnidregion
         AND a.tcie_oid_tipo_cier = 1;

      -- Si existe una fecha de cierre
      IF res > 0 THEN
        SELECT a.fec_cier
          INTO ldfechacierreregion
          FROM fac_contr_cierr a
         WHERE a.pais_oid_pais = lnidpais
           AND a.perd_oid_peri = lnidperiodo
           AND a.zorg_oid_regi = lnidregion
           AND a.tcie_oid_tipo_cier = 1
           AND rownum = 1;

        -- Si fecha de cierre es menor que la fecha actual
        -- obtenemos la fecha de la actividad FA del periodo
        -- siguiente
        IF ldfechacierreregion < ldfechaactual THEN
          ldfechaven := gen_pkg_gener.gen_fn_recup_fecha_activ(pscodpais,
                                                               pscodmarca,
                                                               pscodcanal,
                                                               pscodzona,
                                                               lscodperiodosiguiente,
                                                               'FA');
        END IF;
      ELSE
        ldfechaven := gen_pkg_gener.gen_fn_recup_fecha_activ(pscodpais,
                                                             pscodmarca,
                                                             pscodcanal,
                                                             pscodzona,
                                                             pscodperiodo,
                                                             'FA');
      END IF;
    END IF;

    -- En caso no exista fecha de cierre o la fecha de cierre
    -- sea mayor a la actual o fecha de vencimiento sea null
    IF ldfechaven IS NULL THEN
      ldfechaven := ldfechaactual;
    END IF;

    RETURN ldfechaven;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OBTIE_FECHA_VENCI: ' || ls_sqlerrm);
  END gen_fn_obtie_fecha_venci;

  /**************************************************************************
   Descripcion : Calcula la fecha de vencimiento de un determinado cliente
   y para un periodo determinado, dependiendo de si se haya
   realizado o no el cierre de region.
   Fecha Creacion : 29/10/2007
   Parametros Entrada:
   Parametros Entrada:
   pnoidPais : OID de pais
   pnoidCanal : OID de canal
   pnoidMarca : OID de marca
   pnoidPeriodo : OID de periodo
   psCodRegion : Codigo de region
   psCodZona : Codigo de zona
   psOidCliente : OID de cliente
   Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_venci
  (
    pnoidpais        NUMBER,
    pnoidmarca       NUMBER,
    pnoidcanal       NUMBER,
    pnoidperiodo     NUMBER,
    pscodperiodo     VARCHAR2,
    pscodperiodosgte VARCHAR2,
    pscodregion      VARCHAR2,
    psoidregion      NUMBER,
    pscodzona        VARCHAR2,
    psoidcliente     NUMBER,
    pdfechaactual    DATE
  ) RETURN DATE IS

    res                   NUMBER := 0;
    lscodperiodosiguiente VARCHAR2(6);
    ldfechaven            DATE;
    ldfechacierreregion   DATE;

  BEGIN

    -- Validamos si el cliente paso pedido
    SELECT COUNT(1)
      INTO res
      FROM ped_solic_cabec p
     WHERE p.clie_oid_clie = psoidcliente
       AND p.perd_oid_peri = pnoidperiodo
       AND p.ind_oc = 1;

    -- En caso si ha pasado pedido
    IF res > 0 THEN
      -- Obtenemos la fecha de la actividad FA del siguiente periodo
      ldfechaven := gen_pkg_gener.gen_fn_recup_fecha_activ(pnoidpais,
                                                           pnoidmarca,
                                                           pnoidcanal,
                                                           pscodzona,
                                                           pscodperiodosgte,
                                                           'FA');
      -- En caso no haya pasado pedido
    ELSE
      -- Obtenemos la fecha de cierre de la region
      SELECT COUNT(1)
        INTO res
        FROM fac_contr_cierr a
       WHERE a.pais_oid_pais = pnoidpais
         AND a.perd_oid_peri = pnoidperiodo
         AND a.zorg_oid_regi = psoidregion
         AND a.tcie_oid_tipo_cier = 1;

      -- Si existe una fecha de cierre
      IF res > 0 THEN
        SELECT a.fec_cier
          INTO ldfechacierreregion
          FROM fac_contr_cierr a
         WHERE a.pais_oid_pais = pnoidpais
           AND a.perd_oid_peri = pnoidperiodo
           AND a.zorg_oid_regi = psoidregion
           AND a.tcie_oid_tipo_cier = 1
           AND rownum = 1;

        -- Si fecha de cierre es menor que la fecha actual
        -- obtenemos la fecha de la actividad FA del periodo
        -- siguiente
        IF ldfechacierreregion < pdfechaactual THEN
          ldfechaven := gen_pkg_gener.gen_fn_recup_fecha_activ(pnoidpais,
                                                               pnoidmarca,
                                                               pnoidcanal,
                                                               pscodzona,
                                                               lscodperiodosiguiente,
                                                               'FA');
        END IF;
      ELSE
        ldfechaven := gen_pkg_gener.gen_fn_recup_fecha_activ(pnoidpais,
                                                             pnoidmarca,
                                                             pnoidcanal,
                                                             pscodzona,
                                                             pscodperiodo,
                                                             'FA');
      END IF;
    END IF;

    -- En caso no exista fecha de cierre o la fecha de cierre
    -- sea mayor a la actual o fecha de vencimiento sea null
    IF ldfechaven IS NULL THEN
      ldfechaven := pdfechaactual;
    END IF;

    RETURN ldfechaven;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OBTIE_FECHA_VENCI: ' || ls_sqlerrm);
  END gen_fn_obtie_fecha_venci;
  /**************************************************************************
   Descripcion : Calcula la fecha de vencimiento de un determinada Region Zona
   Fecha Creacion : 12/04/2010
   Autor : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_venci_rezon
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2
  ) RETURN DATE IS

    res                   NUMBER := 0;
    lnidperiodo           NUMBER;
    lnidpais              seg_pais.oid_pais%TYPE;
    lnidcanal             seg_canal.oid_cana%TYPE;
    lnidmarca             seg_marca.oid_marc%TYPE;
    lnidregion            zon_regio.oid_regi%TYPE;
    lscodperiodosiguiente VARCHAR2(6);
    ldfechaven            DATE;
    ldfechacierreregion   DATE;
    ldfechaactual         DATE;
    lsactividad           cra_activ.cod_acti%TYPE;

  BEGIN

    lnidmarca             := gen_fn_devuelve_id_marca(pscodmarca,
                                                      TRUE);
    lnidcanal             := gen_fn_devuelve_id_canal(pscodcanal,
                                                      TRUE);
    lnidperiodo           := gen_fn_devuelve_id_cra_perio(pscodperiodo,
                                                          lnidmarca,
                                                          lnidcanal,
                                                          TRUE);
    lnidregion            := gen_fn_devuelve_id_region(pscodpais,
                                                       pscodmarca,
                                                       pscodcanal,
                                                       pscodregion,
                                                       TRUE);
    lscodperiodosiguiente := gen_fn_calcu_perio(pscodperiodo,
                                                1);

    -- Obtenemos la fecha actual
    SELECT SYSDATE INTO ldfechaactual FROM dual;

    -- Obtenemos la fecha de cierre de la region
    SELECT COUNT(*)
      INTO res
      FROM fac_contr_cierr a
     WHERE a.pais_oid_pais = lnidpais
       AND a.perd_oid_peri = lnidperiodo
       AND a.zorg_oid_regi = lnidregion
       AND a.tcie_oid_tipo_cier = 1;

    -- Se obtiene el codigo actividad Fecha Vencimiento
    BEGIN
        SELECT p.cod_oper INTO lsactividad FROM int_ivr_corpo_param_poven p WHERE p.cod_acce = 'FVP';
    EXCEPTION
      WHEN no_data_found THEN
        lsactividad := 'CV';
    END;

    -- Si existe una fecha de cierre
    IF res > 0 THEN
      SELECT a.fec_cier
        INTO ldfechacierreregion
        FROM fac_contr_cierr a
       WHERE a.pais_oid_pais = lnidpais
         AND a.perd_oid_peri = lnidperiodo
         AND a.zorg_oid_regi = lnidregion
         AND a.tcie_oid_tipo_cier = 1
         AND rownum = 1;

      -- Si fecha de cierre es menor que la fecha actual
      -- obtenemos la fecha de la actividad FA del periodo
      -- siguiente
      IF ldfechacierreregion < ldfechaactual THEN
        ldfechaven := gen_pkg_gener.gen_fn_recup_fecha_activ(pscodpais,
                                                             pscodmarca,
                                                             pscodcanal,
                                                             pscodzona,
                                                             lscodperiodosiguiente,
                                                             lsactividad);
      END IF;
    ELSE
      ldfechaven := gen_pkg_gener.gen_fn_recup_fecha_activ(pscodpais,
                                                           pscodmarca,
                                                           pscodcanal,
                                                           pscodzona,
                                                           pscodperiodo,
                                                           lsactividad);
    END IF;

    -- En caso no exista fecha de cierre o la fecha de cierre
    -- sea mayor a la actual o fecha de vencimiento sea null
    IF ldfechaven IS NULL THEN
      ldfechaven := ldfechaactual;
    END IF;

    RETURN ldfechaven;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OBTIE_FECHA_VENCI_REZON: ' || ls_sqlerrm);
  END gen_fn_obtie_fecha_venci_rezon;

  /**************************************************************************
   Descripcion : Calcula el saldo deudor del cliente cuyos montos estan
   vencidos a una fecha determinada, este saldo incluye las
   percepciones.
   Fecha Creacion : 29/10/2007
   Parametros Entrada:
   psOidCliente : Oid de cliente
   pdFechaVencimiento : Fecha de Vencimiento
   Autor : Carlos Hurtado
  ***************************************************************************/
  FUNCTION gen_fn_calcu_valor_saldo_venci
  (
    psoidcliente       IN NUMBER,
    pdfechavencimiento DATE
  ) RETURN NUMBER IS
    ln_sumador NUMBER;
  BEGIN

    SELECT nvl(SUM(a.imp_pend),
               0)
      INTO ln_sumador
      FROM ccc_movim_cuent_corri a
     WHERE a.clie_oid_clie = psoidcliente
       AND a.fec_venc <= pdfechavencimiento
       AND a.imp_pend <> 0;
    IF ln_sumador < 0 THEN
      ln_sumador := 0;
    END IF;
    RETURN ln_sumador;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_CALCU_VALOR_SALDO_VENCI: ' || ls_sqlerrm);
  END gen_fn_calcu_valor_saldo_venci;

  /***************************************************************************
  Descripcion : Devuelve montos x vencimiento Incluye la Percepcion
  Fecha Creacion : 31/07/2007
  Autor : Marco Agurto
  ***************************************************************************/
  FUNCTION gen_fn_recup_monto_venci_percp
  (
    poidcliente   NUMBER,
    pdfechainicio DATE := NULL,
    pdfechafin    DATE := NULL,
    psperiodo     VARCHAR2 := NULL
  ) RETURN NUMBER IS
    ln_sumador NUMBER;
  BEGIN

    SELECT nvl(SUM(a.imp_pend),
               0)
      INTO ln_sumador
      FROM ccc_movim_cuent_corri a
     WHERE a.clie_oid_clie = poidcliente
       AND a.fec_venc <= pdfechafin
       AND a.imp_pend <> 0;
    IF ln_sumador < 0 THEN
      ln_sumador := 0;
    END IF;
    RETURN ln_sumador;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_RECUP_MONTO_VENCI_PERCP: ' || ls_sqlerrm);
  END gen_fn_recup_monto_venci_percp;

  /***************************************************************************
  Descripcion : Devuelve oid de mensaje de Tabla MSG_MENSA
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  Parametros :
   psCodMensaje : Codigo de Mensaje
  ***************************************************************************/
  FUNCTION gen_fn_devue_oid_mensa(pscodmensaje VARCHAR2) RETURN NUMBER IS
    lnmensaje NUMBER;
  BEGIN
    SELECT a.oid_mens INTO lnmensaje FROM msg_mensa a WHERE a.cod_mens = pscodmensaje;
    RETURN lnmensaje;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_DEVUE_OID_MENSA: ' || ls_sqlerrm);
  END gen_fn_devue_oid_mensa;

  /***************************************************************************
  Descripcion : Devuelve VALOR DE CAMPO ingresado como parametro
   en la tabla de Mensajes
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  Parametros :
   pnMensaje : ID Mensaje
   psNombreCampo: Nombre de Campo
  ***************************************************************************/
  FUNCTION gen_fn_devue_mensa_datos
  (
    pnidmensaje   NUMBER,
    psnombrecampo VARCHAR2
  ) RETURN VARCHAR2 IS
    regregistro msg_mensa%ROWTYPE;
  BEGIN
    SELECT * INTO regregistro FROM msg_mensa a WHERE a.oid_mens = pnidmensaje;

    IF psnombrecampo = 'MODU_OID_MODU' THEN
      RETURN to_char(regregistro.modu_oid_modu);
    END IF;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_DEVUE_MENSA_DATOS: ' || ls_sqlerrm);
  END gen_fn_devue_mensa_datos;

  /***************************************************************************
  Descripcion : Devuelve SEQUENCIAL SIGUIENTE
   de Tabla MSG_BUZON_MENSA para campo OID_BUZO_MENS
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_buzon_mensa_seque_next RETURN NUMBER IS
    lnsequence NUMBER;
  BEGIN
    SELECT msg_bume_seq.nextval INTO lnsequence FROM dual;
    RETURN lnsequence;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_BUZON_MENSA_SEQUE_NEXT: ' || ls_sqlerrm);
  END gen_fn_buzon_mensa_seque_next;

  /***************************************************************************
  Descripcion : Devuelve SEQUENCIAL SIGUIENTE
   de Tabla MSG_BUZON_MENSA para campo NUM_SECU
  Fecha Creacion : 25/01/2008
  Autor : Carlos Bazalar
  ***************************************************************************/
  FUNCTION gen_fn_mensa_nsecu_seque_next RETURN NUMBER IS
    lnsequence NUMBER;
  BEGIN
    SELECT msg_bum2_seq.nextval INTO lnsequence FROM dual;
    RETURN lnsequence;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_MENSA_NSECU_SEQUE_NEXT: ' || ls_sqlerrm);
  END gen_fn_mensa_nsecu_seque_next;
  /***************************************************************************
  Descripcion : Devuelve la tasa de impuesto por pais
  Fecha Creacion : 06/06/2008
  Autor : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_devue_tasa_impue_pais(pnoidpais NUMBER) RETURN NUMBER IS
    lntasa NUMBER;
  BEGIN
    SELECT t.val_tasa_impu
      INTO lntasa
      FROM sac_param_tasa_impue_pais t
     WHERE t.oid_pais = pnoidpais;
    RETURN lntasa;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_DEVUE_TASA_IMPUE_PAIS: ' || ls_sqlerrm);
  END gen_fn_devue_tasa_impue_pais;

  /***************************************************************************
  Descripcion : Devuelve el numero de serie del documento Legal
  Fecha Creacion : 06/06/2008
  Autor : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_devue_serie_docum_legal
  (
    pnoidpais          NUMBER,
    pnoidsociedad      NUMBER,
    pnoidsubacceso     NUMBER,
    pnoidtipodocumento NUMBER
  ) RETURN VARCHAR2 IS
    lsserie VARCHAR2(10);
  BEGIN
    SELECT s.val_seri_docu_lega
      INTO lsserie
      FROM sac_param_serie_docum_legal s
     WHERE pais_oid_pais = pnoidpais
       AND soci_oid_soci = pnoidsociedad
       AND sbac_oid_sbac = pnoidsubacceso
       AND tido_oid_tipo_docu = pnoidtipodocumento;
    RETURN lsserie;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_DEVUE_SERIE_DOCUM_LEGAL: ' || ls_sqlerrm);
  END gen_fn_devue_serie_docum_legal;

  /***************************************************************************
  Descripcion : Devuelve el OidPeriodo de N campa?as anteriores
  Fecha Creacion : 15/08/2008
  Autor : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_id_nante_campa
  (
    psoidperiodo        VARCHAR2,
    numcampanhas        NUMBER,
    devuelvevalornodata BOOLEAN := FALSE
  ) RETURN NUMBER IS
    oidresult NUMBER;
  BEGIN
    SELECT gen_pkg_gener.gen_fn_devuelve_id_cra_perio2((SELECT cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa((SELECT a.cod_peri
                                                                                                           FROM seg_perio_corpo a,
                                                                                                                cra_perio       b,
                                                                                                                seg_canal       c,
                                                                                                                seg_marca       d
                                                                                                          WHERE b.oid_peri =
                                                                                                                psoidperiodo
                                                                                                            AND a.oid_peri =
                                                                                                                b.peri_oid_peri
                                                                                                            AND b.cana_oid_cana =
                                                                                                                c.oid_cana
                                                                                                            AND b.marc_oid_marc =
                                                                                                                d.oid_marc
                                                                                                            AND c.cod_cana = 'VD'
                                                                                                            AND d.cod_marc = 'T'),
                                                                                                         ((-1) *
                                                                                                         (numcampanhas)))
                                                         FROM dual))
      INTO oidresult
      FROM dual;

    RETURN oidresult;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelvevalornodata) THEN
        ls_sqlerrm := 'No se encontro ID anterior con Oid base: ' || psoidperiodo;
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUELVE_ID_NANTE_CAMPA: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
  END gen_fn_devuelve_id_nante_campa;

  PROCEDURE pre_pr_elimi_buzon_mensa(codigopais IN VARCHAR2) IS
  BEGIN

  /*  DELETE FROM msg_buzon_mensa a
     WHERE a.mens_oid_mens IN
           (SELECT v.mens_oid_mens FROM pre_mensa_punto v WHERE v.pais_cod_pais = codigopais)
       AND a.fec_impr IS NULL;
*/
    UPDATE pre_ofert a
       SET a.ind_desp_compl = 0
     WHERE a.ind_desp_compl = 1
       AND a.coes_oid_estr = 2014;

  END pre_pr_elimi_buzon_mensa;

  /***************************************************************************
  Descripcion : Devuelve el Oid de Estructura Geopolitica
  Fecha Creacion : 29/12/2008
  Autor : Arturo Blumen
  ***************************************************************************/

  FUNCTION gen_fn_oid_estru_geopo
  (
    pnoidpais    NUMBER,
    pnoidcliente NUMBER
  ) RETURN NUMBER IS
    oidresult NUMBER;
  BEGIN

    SELECT es.oid_valo_estr_geop
      INTO oidresult
      FROM mae_clien             m,
           mae_clien_direc       md,
           zon_valor_estru_geopo es
     WHERE m.oid_clie = md.clie_oid_clie
       AND md.ind_dire_ppal = 1
       AND md.ind_elim = 0
       AND md.cod_unid_geog =
           nvl(es.orde_1,
               '') || nvl(es.orde_2,
                          '') || nvl(es.orde_3,
                                     '') || nvl(es.orde_4,
                                                '')
       AND m.oid_clie = pnoidcliente
       AND es.pais_oid_pais = pnoidpais;

    RETURN oidresult;
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20123,
                              'ERROR GEN_FN_OID_ESTRU_GEOPO: ' || ls_sqlerrm);
  END gen_fn_oid_estru_geopo;

  /**************************************************************************
  Descripcion : Obtiene el codigo territorio
  Fecha Creacion : 22/10/2009
  Parametros Entrada :
  psCodCliente : Codigo del cliente
  psCammpanha : Campanha
  Autor : Sergio Buchelli
  ***************************************************************************/
  FUNCTION gen_fn_devue_cod_terri
  (
    pscodcliente VARCHAR2,
    pscampana    VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_result   VARCHAR2(100);
    lnoidperido cra_perio.oid_peri%TYPE;
  BEGIN

    lnoidperido := gen_fn_devuelve_id_cra_perio2(pscampana);

    SELECT nvl(zon_terri.cod_terr,
               '')
      INTO ls_result
      FROM mae_clien,
           mae_clien_unida_admin,
           zon_terri_admin,
           zon_terri,
           zon_secci,
           zon_zona,
           zon_regio
     WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
           (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
           (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
           (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
           (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
           zon_terri_admin.terr_oid_terr = zon_terri.oid_terr AND
           (lnoidperido >= mae_clien_unida_admin.perd_oid_peri_ini OR
           mae_clien_unida_admin.perd_oid_peri_ini IS NULL) AND
           (lnoidperido <= mae_clien_unida_admin.perd_oid_peri_fin OR
           mae_clien_unida_admin.perd_oid_peri_fin IS NULL) AND
           (mae_clien.cod_clie = pscodcliente) AND (rownum = 1));

    RETURN ls_result;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
  END gen_fn_devue_cod_terri;

  /**************************************************************************
  Descripcion : Trunca una Tabla USANDO PRAGMA TRANSACTION
  Fecha Creacion : 30/10/2009
  Parametros Entrada :
  Autor : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_trunc_table(p_nom_tabl IN VARCHAR2) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
    v_sql VARCHAR2(100);
  BEGIN
    v_sql := 'TRUNCATE TABLE ' || p_nom_tabl;
    EXECUTE IMMEDIATE (v_sql);
  END gen_pr_trunc_table;

  /**************************************************************************
  Descripcion : Ejecuta Estadisticas USANDO PRAGMA TRANSACTION
  Fecha Creacion : 30/10/2009
  Parametros Entrada :
  Autor : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_gener_stats
  (
    p_nom_tabl IN VARCHAR2,
    p_user     IN VARCHAR2
  ) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    dbms_stats.gather_table_stats(ownname => p_user,
                                  tabname => p_nom_tabl,
                                  cascade => TRUE);
  END gen_pr_gener_stats;

  /***************************************************************************
  Descripcion       : Devuelve SEQUENCIAL SIGUIENTE
                      de Tabla MAE_CLIEN_BLOQU para campo OID_BLOQ
  Fecha Creacion    : 06/05/2008
  Autor             : Sergio Buchelli
  ***************************************************************************/
  FUNCTION gen_fn_clien_bloqu_seque_next RETURN NUMBER IS
    lnsequence NUMBER;
  BEGIN
    SELECT mae_clbl_seq.nextval INTO lnsequence FROM dual;
    RETURN lnsequence;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_CLIEN_BLOQU_SEQUE_NEXT: ' || ls_sqlerrm);
  END gen_fn_clien_bloqu_seque_next;

  /***************************************************************************
  Descripcion       : Inserta en la tabla de LOG el Error producido en el Cursor
                      Para ello debe indicarse nombre del cursor y la iteracion en
                      donde ha generado error el proceso
  Fecha Creacion    : 02/03/2010
  Autor             : Carlos Bazalar
  ***************************************************************************/
  PROCEDURE gen_pr_inser_error_bdato_ssicc
  (
    p_nom_paquete       VARCHAR2,
    p_nom_procedure     VARCHAR2,
    p_nom_cursor        VARCHAR2,
    p_num_item_pagina   NUMBER,
    p_num_item_registro NUMBER,
    p_key_error         VARCHAR2,
    p_des_error         VARCHAR2
  ) IS
    lnsecuencia NUMBER;

    PRAGMA AUTONOMOUS_TRANSACTION;

  BEGIN
    SELECT seq_log_error_bdato_ssicc.nextval INTO lnsecuencia FROM dual;

    INSERT INTO log_error_bdato_ssicc
      (nom_paqu,
       nom_proc,
       num_secu,
       nom_curs,
       num_item_erro_pagi,
       num_item_erro_regi,
       key_erro,
       des_erro,
       fec_ejec)
    VALUES
      (p_nom_paquete,
       p_nom_procedure,
       lnsecuencia,
       p_nom_cursor,
       p_num_item_pagina,
       p_num_item_registro,
       p_key_error,
       p_des_error,
       SYSDATE);

    COMMIT;
  END gen_pr_inser_error_bdato_ssicc;

  /******************************************************************************
  Descripcion       : Devuelve el oid del maximo periodo de acuerdo al oid del
                      cliente
  Fecha Creacion    : 15/07/2010
  Autor             : Jesse James Rios Franco

  p_oid_clie        : oid del cliente
  p_num_pedido      : Primer Pedido = 0
                      Ultimo Pedido = 1
  ******************************************************************************/
  FUNCTION gen_fn_oid_perio_maxim_clien
  (
    p_oid_clie   NUMBER,
    p_num_pedido NUMBER
  ) RETURN NUMBER IS
    oidperiodomax NUMBER;

  BEGIN

    IF p_num_pedido = 0 THEN
      SELECT oid_peri
        INTO oidperiodomax
        FROM (SELECT oid_peri,
                     fec_inic
                FROM cra_perio
               WHERE oid_peri IN (SELECT h.perd_oid_peri
                                    FROM mae_clien_histo_estat h
                                   WHERE h.clie_oid_clie = p_oid_clie
                                     AND h.esta_oid_esta_clie IN (2,
                                                                  8))
               ORDER BY fec_inic DESC)
       WHERE rownum = 1;
    END IF;

    IF p_num_pedido = 1 THEN
      SELECT oid_peri
        INTO oidperiodomax
        FROM (SELECT oid_peri,
                     fec_inic
                FROM cra_perio
               WHERE oid_peri IN (SELECT x.perd_oid_peri
                                    FROM ped_solic_cabec x,
                                         ped_solic_cabec y
                                   WHERE x.clie_oid_clie = p_oid_clie
                                     AND x.tspa_oid_tipo_soli_pais =
                                         int_pkg_recla.gen_fn_devue_oid_tipo_solpa('SOC')
                                     AND x.grpr_oid_grup_proc = 5
                                     AND x.fec_fact IS NOT NULL
                                     AND x.soca_oid_soli_cabe = y.oid_soli_cabe
                                     AND y.esso_oid_esta_soli <> 4)
               ORDER BY fec_inic DESC)
       WHERE rownum = 1;
    END IF;
    RETURN oidperiodomax;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_OID_PERIO_MAXIM_CLIEN: ' || ls_sqlerrm);
  END gen_fn_oid_perio_maxim_clien;

  /**************************************************************************
   Descripcion : Calcula la fecha de entrega de pedido de un determinada
                 Region Zona
   Fecha Creacion : 12/10/2010
   Autor : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION gen_fn_obtie_fecha_entre_rezon
  (
    pscodpais    VARCHAR2,
    pscodmarca   VARCHAR2,
    pscodcanal   VARCHAR2,
    pscodperiodo VARCHAR2,
    pscodregion  VARCHAR2,
    pscodzona    VARCHAR2
  ) RETURN DATE IS

    lnidperiodo         NUMBER;
    lnidpais            seg_pais.oid_pais%TYPE;
    lnidcanal           seg_canal.oid_cana%TYPE;
    lnidmarca           seg_marca.oid_marc%TYPE;
    lnidregion          zon_regio.oid_regi%TYPE;
    ldfechaven          DATE;
    ldfechacierreregion DATE;
    v_oid_acti          cra_activ.oid_acti%TYPE;
    v_oid_periodo_sig   cra_crono.perd_oid_peri%TYPE;
    lnoidzona           cra_crono.zzon_oid_zona%TYPE;

  BEGIN
    lnidpais    := gen_fn_devuelve_id_pais(pscodpais);
    lnidmarca   := gen_fn_devuelve_id_marca(pscodmarca,
                                            TRUE);
    lnidcanal   := gen_fn_devuelve_id_canal(pscodcanal,
                                            TRUE);
    lnidperiodo := gen_fn_devuelve_id_cra_perio(pscodperiodo,
                                                lnidmarca,
                                                lnidcanal,
                                                TRUE);
    /*
    lnidregion   := gen_fn_devuelve_id_region(pscodpais,
                                              pscodmarca,
                                              pscodcanal,
                                              pscodregion,
                                              TRUE);
    lnoidzona    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_ZONA(pscodpais,
                                                          pscodmarca,
                                                          pscodcanal,
                                                          pscodregion,
                                                          pscodzona);
    */
    BEGIN
      SELECT a.oid_regi,
             zz.oid_zona
        INTO lnidregion,
             lnoidzona
        FROM zon_regio a,
             zon_zona  zz
       WHERE a.pais_oid_pais = lnidpais
         AND a.marc_oid_marc = lnidmarca
         AND a.cana_oid_cana = lnidcanal
         AND a.cod_regi = pscodregion
         AND zz.zorg_oid_regi = a.oid_regi
         AND zz.cod_zona = pscodzona;
    EXCEPTION
      WHEN no_data_found THEN
        lnidregion := NULL;
        lnoidzona  := NULL;
    END;

    -- Se obtiene el oid de actividad
    SELECT ca.oid_acti
      INTO v_oid_acti
      FROM cra_activ ca
     WHERE ca.pais_oid_pais = lnidpais
       AND ca.cod_acti =
           (SELECT p.cod_oper FROM int_ivr_corpo_param_poven p WHERE p.cod_acce = 'REP');

    -- Se calcula el oid de periodo siguiente al actual
    v_oid_periodo_sig := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(gen_fn_calcu_perio(pscodperiodo,
                                                                                        1));

    BEGIN
      -- Obtenemos la fecha de cierre de la region
      SELECT a.fec_cier
        INTO ldfechacierreregion
        FROM fac_contr_cierr a
       WHERE a.pais_oid_pais = lnidpais
         AND a.perd_oid_peri = lnidperiodo
         AND a.zorg_oid_regi = lnidregion
         AND a.tcie_oid_tipo_cier = 1
         AND rownum = 1;

      -- Si existe la fecha de cierre y es menor que la fecha actual
      -- obtenemos la fecha de entrega del periodo siguiente
      IF ldfechacierreregion < SYSDATE THEN
        BEGIN
          SELECT cc.fec_inic
            INTO ldfechaven
            FROM cra_crono cc
           WHERE cc.zzon_oid_zona = lnoidzona
             AND cc.cact_oid_acti = v_oid_acti
             AND cc.perd_oid_peri = v_oid_periodo_sig;
        EXCEPTION
          WHEN no_data_found THEN
            ldfechaven := SYSDATE;
        END;
      ELSE
        ldfechaven := SYSDATE;
      END IF;

    EXCEPTION
      WHEN no_data_found THEN
        -- Si aun no se ha cerrado la region, se obtiene la fecha
        -- de entrega del periodo actual
        BEGIN
          SELECT cc.fec_inic
            INTO ldfechaven
            FROM cra_crono cc
           WHERE cc.zzon_oid_zona = lnoidzona
             AND cc.cact_oid_acti = v_oid_acti
             AND cc.perd_oid_peri = lnidperiodo;
        EXCEPTION
          WHEN no_data_found THEN
            ldfechaven := SYSDATE;
        END;
    END;

    RETURN ldfechaven;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR gen_fn_obtie_fecha_entre_rezon: ' || ls_sqlerrm);
  END gen_fn_obtie_fecha_entre_rezon;

  /**************************************************************
  Descripcion        : Devuelve el valor del parametro de pais
  Fecha Creacion     : 23/11/2011
  Parametros         : pscodigopais : Pais
                       pscodigosistema : Sistema
                       pscodigoParametro : Parametro
  Autor              : Jose A.Cairampoma Granados
  ***************************************************************/
  FUNCTION gen_fn_param_pais
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigoparametro VARCHAR2
  ) RETURN VARCHAR2 IS
    lsparametro bas_param_pais.val_para%TYPE;

    lscodigopais SEG_PAIS.COD_PAIS%TYPE;
  BEGIN
    lscodigopais := pscodigopais;
    if lscodigopais is null then
       SELECT MIN(val_para)
      INTO lscodigopais
      FROM bas_param_pais
     WHERE cod_para = '000'
       AND cod_sist = 'BAS';
    END if;

    SELECT MIN(val_para)
      INTO lsparametro
      FROM bas_param_pais
     WHERE cod_para = pscodigoparametro
       AND cod_sist = pscodigosistema
       AND cod_pais = lscodigopais;
    RETURN lsparametro;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR gen_fn_param_pais: ' || ls_sqlerrm);
  END gen_fn_param_pais;
  /**************************************************************
  Descripcion        : Calcula el Periodo de acuerdo al numero de Periodos
  Fecha Creacion     : 23/11/2011
  Parametros         : pscodigoperiodo : Periodo
                       pnnumeroperiodo : Numero Periodos atras
  Autor              : Jose A. Cairampoma Granados

  ***************************************************************/
  FUNCTION gen_fn_perio_nsigu
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pnnumeroperiodo NUMBER
  ) RETURN VARCHAR2 IS
    lscodigoperiodo VARCHAR2(6);

    lnanio         NUMBER(4);
    lnperiodo      NUMBER(12) := 0;
    lnperiodosanio NUMBER(2);

  BEGIN

    lnperiodosanio := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                      'GEN',
                                                      '000');

    /*OBTENGO EL NUMERO DE PERIDOS ANUAL*/
    lnperiodo := substr(pscodigoperiodo,
                        1,
                        4) * lnperiodosanio;

    /*AGREGO EL NUMERO DE PERIODOS DEL CODIGO DE PERIODO*/
    lnperiodo := lnperiodo + substr(pscodigoperiodo,
                                    5,
                                    2);

    /*AGREGO EL NUMERO DE PERIODOS INGRESADOS*/
    lnperiodo := lnperiodo + pnnumeroperiodo;

    /*OBTENGO EL ANIO*/
    lnanio := trunc(lnperiodo / lnperiodosanio);

    /*OBTENGO EL PERIODO*/
    lnperiodo := MOD(lnperiodo,
                     lnperiodosanio);

    IF lnperiodo = 0 THEN
      lnperiodo := lnperiodosanio;
      lnanio    := lnanio - 1;
    END IF;

    RETURN lnanio || lpad(lnperiodo,
                          2,
                          '0');
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR gen_fn_perio_nsigu: ' || ls_sqlerrm);
  END gen_fn_perio_nsigu;

  /******************************************************************************
  Descripcion       : Devuelve el oid del Tipo de bloqueo

  Fecha Creacion    : 26/12/2011
  Autor             : Ivan Tocto Jaimes

  p_tipo_bloq        : Tipo de Bloqueo
  ******************************************************************************/
  FUNCTION gen_fn_devuelve_id_tipo_bloq(p_tipo_bloq VARCHAR2) RETURN NUMBER IS
    ln_oid NUMBER;
  BEGIN
    SELECT oid_tipo_bloq INTO ln_oid FROM mae_tipo_bloqu WHERE cod_tipo_bloq = p_tipo_bloq;

    RETURN ln_oid;
  END;

  /******************************************************************************
  Descripcion       : Devuelve el oid del Tipo de Accion

  Fecha Creacion    : 26/12/2011
  Autor             : Ivan Tocto Jaimes

  p_tipo_acci        : Tipo de Accion
  ******************************************************************************/
  FUNCTION gen_fn_devuelve_id_vacc_bloq(p_tipo_acci VARCHAR2) RETURN NUMBER IS
    ln_oid NUMBER;
  BEGIN
    SELECT oid_valo_acci_bloq
      INTO ln_oid
      FROM mae_valor_accio_bloqu
     WHERE cod_valo_bloq = p_tipo_acci;

    RETURN ln_oid;
  END;

  /**************************************************************
  Descripcion        : Retorna el origen del pedido
  Fecha Creacion     : 02/02/2012
  Parametros         : psoid : Pedido
  Autor              : Vidal Cupe Quispe

  ***************************************************************/
  FUNCTION gen_fn_devue_orige_pedid(psoid VARCHAR2) RETURN VARCHAR2 IS
    lsorigen VARCHAR2(100);
  BEGIN
    SELECT o.des_orig
      INTO lsorigen
      FROM sto_orige_docum       o,
           sto_combi_orige_docum c,
           sto_docum_digit       d,
           int_solic_conso_cabec e
     WHERE d.sec_nume_docu = e.sec_nume_docu
       AND d.num_lote = e.num_lote
       AND o.cod_tipo_docu = d.cod_tipo_docu
       AND o.cod_pais = d.cod_pais
       AND o.cod_tipo_docu = c.cod_tipo_docu
       AND o.cod_orig = c.cod_orig
       AND c.ind_rece_ocr = e.ind_rece_ocr
       AND c.ind_rece_web = e.ind_rece_web
       AND c.ind_rece_dd = e.ind_rece_dd
       AND c.ind_rece_digi = e.ind_rece_digi
       AND c.ind_rece_cc = e.ind_rece_cc
       AND c.ind_rece_mens = e.ind_rece_mens
       AND c.ind_rece_onli = e.ind_rece_onli
       AND c.ind_rece_ivr = e.ind_rece_ivr
       AND e.soca_oid_soli_cabe_refe = psoid;

    RETURN lsorigen;

  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'error gen_fn_devue_orige_pedid: ' || ls_sqlerrm);
  END gen_fn_devue_orige_pedid;

  /**************************************************************
  Descripcion        : Retorna el origen de la posicion del pedido
  Fecha Creacion     : 02/07/2012
  Parametros         : psoid : Pedido
                       pscuv : cuv del pedido
  Autor              : Sandro Quintana Aponte

  ***************************************************************/
  FUNCTION gen_fn_devue_orige_pedid_posic
  (
    psoid VARCHAR2,
    pscuv VARCHAR2
  ) RETURN VARCHAR2 IS
    lsorigen VARCHAR2(100);
  BEGIN
    SELECT o.des_orig
      INTO lsorigen
      FROM sto_orige_docum       o,
           sto_combi_orige_docum c,
           sto_docum_digit       d,
           int_solic_conso_cabec e,
           int_solic_conso_detal f
     WHERE d.sec_nume_docu = e.sec_nume_docu
       AND d.num_lote = e.num_lote
       AND o.cod_tipo_docu = d.cod_tipo_docu
       AND o.cod_pais = d.cod_pais
       AND o.cod_tipo_docu = c.cod_tipo_docu
       AND o.cod_orig = c.cod_orig
       AND c.ind_rece_ocr = e.ind_rece_ocr
       AND c.ind_rece_web = e.ind_rece_web
       AND c.ind_rece_dd = e.ind_rece_dd
       AND c.ind_rece_digi = e.ind_rece_digi
       AND c.ind_rece_cc = e.ind_rece_cc
       AND c.ind_rece_mens = e.ind_rece_mens
       AND c.ind_rece_onli = e.ind_rece_onli
       AND c.ind_rece_ivr = e.ind_rece_ivr
       AND e.sec_nume_docu = f.sec_nume_docu_cabe
       AND e.soca_oid_soli_cabe_refe = psoid
       AND to_number(f.cod_vent) = to_number(pscuv)
       AND rownum = 1;
    RETURN lsorigen;
  EXCEPTION
    WHEN no_data_found THEN
      BEGIN
        SELECT o.des_orig
          INTO lsorigen
          FROM sto_orige_docum             o,
               sto_combi_orige_docum       c,
               sto_histo_docum_digit       d,
               ped_histo_solic_conso_cabec e,
               ped_histo_solic_conso_detal f
         WHERE d.sec_nume_docu = e.sec_nume_docu
           AND d.num_lote = e.num_lote
           AND o.cod_tipo_docu = d.cod_tipo_docu
           AND o.cod_pais = d.cod_pais
           AND o.cod_tipo_docu = c.cod_tipo_docu
           AND o.cod_orig = c.cod_orig
           AND c.ind_rece_ocr = e.ind_rece_ocr
           AND c.ind_rece_web = e.ind_rece_web
           AND c.ind_rece_dd = e.ind_rece_dd
           AND c.ind_rece_digi = e.ind_rece_digi
           AND c.ind_rece_cc = e.ind_rece_cc
           AND c.ind_rece_mens = e.ind_rece_mens
           AND c.ind_rece_onli = e.ind_rece_onli
           AND c.ind_rece_ivr = e.ind_rece_ivr
           AND e.sec_nume_docu = f.sec_nume_docu_cabe
           AND e.soca_oid_soli_cabe_refe = psoid
           AND to_number(f.cod_vent) = to_number(pscuv)
           AND rownum = 1;
        RETURN lsorigen;
      EXCEPTION
        WHEN no_data_found THEN
          RETURN '';
      END;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'error gen_fn_devue_orige_pedid_posic: ' || ls_sqlerrm);
  END gen_fn_devue_orige_pedid_posic;

  /**************************************************************************
  Descripcion        : Genera nuevo Lote para el Periodo Pasado como  parametro
  Fecha Creacion     : 06/03/2012

  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_actua_lote_fecha_perio
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2,
    pdfechaproceso  IN DATE,
    psnumerolote    OUT VARCHAR2
  ) IS
    PRAGMA AUTONOMOUS_TRANSACTION;

    lsnumerolote bas_ctrl_fact.num_lote%TYPE;
  BEGIN

    UPDATE bas_ctrl_fact
       SET num_lote = lpad((nvl(num_lote,
                                0) + 1),
                           8,
                           '0'),
           fec_proc = nvl(pdfechaproceso,
                          fec_proc)
     WHERE cod_pais = pscodigopais
       AND cod_peri = pscodigoperiodo
    RETURNING num_lote INTO lsnumerolote;

    COMMIT;
    psnumerolote := lsnumerolote;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_PR_ACTUA_LOTE_PERIO: ' || ls_sqlerrm);

  END gen_pr_actua_lote_fecha_perio;

  /**************************************************************************
  Descripcion        : Actualiza como campa?a activa al periodo enviado como
  parametro.
  Fecha Creacion     : 06/03/2012

  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_actua_perio_actua
  (
    pscodigopais    IN VARCHAR2,
    pscodigoperiodo IN VARCHAR2
  ) IS
    PRAGMA AUTONOMOUS_TRANSACTION;

    lsnumerolote bas_ctrl_fact.num_lote%TYPE;
  BEGIN

    UPDATE bas_ctrl_fact bas
       SET bas.sta_camp     = '1',
           bas.ind_camp_act = '0'
     WHERE bas.sta_camp = '0'
       AND bas.ind_camp_act = '1'
       AND bas.cod_pais = pscodigopais;

    UPDATE bas_ctrl_fact bas
       SET bas.sta_camp     = '0',
           bas.ind_camp_act = '1'
     WHERE cod_peri = pscodigoperiodo
       AND bas.cod_pais = pscodigopais;

    COMMIT;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR gen_pr_actua_perio_actua: ' || ls_sqlerrm);

  END gen_pr_actua_perio_actua;

  /**************************************************************************
  Descripcion        : Actualiza las estadisticas de las tablas parametrizadas
                       en BAS_PARAm_PAIS.
  Fecha Creacion     : 06/03/2012

  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE gen_pr_actua_estad_tabla IS

    CURSOR c_deuda IS
      SELECT val_para
        FROM bas_param_pais p,
             dba_tables     d
       WHERE p.val_para = d.table_name
         AND d.owner = USER
         AND cod_sist = 'STD'
         AND nom_para = 'estadTablas'
         AND p.ind_acti = '1';

    TYPE t_query IS TABLE OF VARCHAR2(4000);

    v_query t_query;

    j BINARY_INTEGER := 0;

    w_filas NUMBER := 5000;

  BEGIN

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_query LIMIT w_filas;

      IF v_query.count > 0 THEN

        FOR j IN v_query.first .. v_query.last
        LOOP
          dbms_stats.gather_table_stats(ownname => USER,
                                        tabname => v_query(j),
                                        cascade => TRUE);

        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_PR_ACTUA_ESTAD_TABLA: ' || ls_sqlerrm);

  END gen_pr_actua_estad_tabla;

  /**************************************************************************
  Descripcion : Devuelve el telefono fijo y celular del cliente
  Fecha Creacion : 15/06/2012
  Autor : Jorge Velasquez
  ***************************************************************************/
  FUNCTION gen_fn_telf_mae_client(oidcliente NUMBER) RETURN VARCHAR2 IS
    v_telefonos VARCHAR2(256);
  BEGIN

    SELECT ((SELECT val_text_comu
               FROM mae_clien_comun com,
                    mae_tipo_comun  tip
              WHERE com.ticm_oid_tipo_comu = tip.oid_tipo_comu
                AND com.clie_oid_clie = oidcliente
                AND tip.cod_tipo_comu = 'TM') || ' - ' ||
           (SELECT val_text_comu
               FROM mae_clien_comun com,
                    mae_tipo_comun  tip
              WHERE com.ticm_oid_tipo_comu = tip.oid_tipo_comu
                AND com.clie_oid_clie = oidcliente
                AND tip.cod_tipo_comu = 'TF'))
      INTO v_telefonos
      FROM dual;

    RETURN v_telefonos;
  END gen_fn_telf_mae_client;

  /**************************************************************************
  Descripcion : Devuelve el telefono fijo y celular del cliente
  Fecha Creacion : 15/06/2012
  Autor : Jorge Velasquez
  ***************************************************************************/
  FUNCTION gen_fn_direc_mae_client(oidcliente NUMBER) RETURN VARCHAR2 IS
    v_direccion VARCHAR2(1024);
  BEGIN
    SELECT des_abrv_tipo_via || ' ' || val_nomb_via || ' ' || num_ppal || ' ' || val_obse || ' ' ||
           cod_terr || ' ' ||
           (nivel_1 || decode(nivel_2,
                              NULL,
                              '',
                              '/' || nivel_2) ||
           decode(nivel_3,
                   NULL,
                   '',
                   '/' || nivel_3) || decode(nivel_4,
                                              NULL,
                                              '',
                                              '/' || nivel_4) ||
           decode(nivel_5,
                   NULL,
                   '',
                   '/' || nivel_5) || decode(nivel_6,
                                              NULL,
                                              '',
                                              '/' || nivel_6) ||
           decode(nivel_7,
                   NULL,
                   '',
                   '/' || nivel_7) || decode(nivel_8,
                                              NULL,
                                              '',
                                              '/' || nivel_8) ||
           decode(nivel_9,
                   NULL,
                   '',
                   '/' || nivel_9)) || ' ' || val_barr
      INTO v_direccion
      FROM (SELECT a.oid_clie_dire OID,
                   c.des_abrv_tipo_via,
                   a.val_nomb_via,
                   a.num_ppal,
                   a.val_obse,
                   a.val_barr,
                   t.cod_terr,
                   (SELECT des_geog
                      FROM zon_valor_estru_geopo
                     WHERE pais_oid_pais = d.pais_oid_pais
                       AND orde_1 = substr(a.cod_unid_geog,
                                           1,
                                           6)
                       AND orde_2 IS NULL) AS nivel_1,
                   (SELECT des_geog
                      FROM zon_valor_estru_geopo
                     WHERE pais_oid_pais = d.pais_oid_pais
                       AND orde_1 = substr(a.cod_unid_geog,
                                           1,
                                           6)
                       AND orde_2 = substr(a.cod_unid_geog,
                                           7,
                                           6)
                       AND orde_3 IS NULL) AS nivel_2,
                   (SELECT des_geog
                      FROM zon_valor_estru_geopo
                     WHERE pais_oid_pais = d.pais_oid_pais
                       AND orde_1 = substr(a.cod_unid_geog,
                                           1,
                                           6)
                       AND orde_2 = substr(a.cod_unid_geog,
                                           7,
                                           6)
                       AND orde_3 = substr(a.cod_unid_geog,
                                           13,
                                           6)
                       AND orde_4 IS NULL) AS nivel_3,
                   CASE
                     WHEN length(a.cod_unid_geog) > 18 THEN
                      (SELECT des_geog
                         FROM zon_valor_estru_geopo
                        WHERE pais_oid_pais = d.pais_oid_pais
                          AND orde_1 = substr(a.cod_unid_geog,
                                              1,
                                              6)
                          AND orde_2 = substr(a.cod_unid_geog,
                                              7,
                                              6)
                          AND orde_3 = substr(a.cod_unid_geog,
                                              13,
                                              6)
                          AND orde_4 = substr(a.cod_unid_geog,
                                              19,
                                              6)
                          AND orde_5 IS NULL)
                     ELSE
                      NULL
                   END AS nivel_4,
                   CASE
                     WHEN length(a.cod_unid_geog) > 24 THEN
                      (SELECT des_geog
                         FROM zon_valor_estru_geopo
                        WHERE pais_oid_pais = d.pais_oid_pais
                          AND orde_1 = substr(a.cod_unid_geog,
                                              1,
                                              6)
                          AND orde_2 = substr(a.cod_unid_geog,
                                              7,
                                              6)
                          AND orde_3 = substr(a.cod_unid_geog,
                                              13,
                                              6)
                          AND orde_4 = substr(a.cod_unid_geog,
                                              19,
                                              6)
                          AND orde_5 = substr(a.cod_unid_geog,
                                              25,
                                              6)
                          AND orde_6 IS NULL)
                     ELSE
                      NULL
                   END AS nivel_5,
                   CASE
                     WHEN length(a.cod_unid_geog) > 30 THEN
                      (SELECT des_geog
                         FROM zon_valor_estru_geopo
                        WHERE pais_oid_pais = d.pais_oid_pais
                          AND orde_1 = substr(a.cod_unid_geog,
                                              1,
                                              6)
                          AND orde_2 = substr(a.cod_unid_geog,
                                              7,
                                              6)
                          AND orde_3 = substr(a.cod_unid_geog,
                                              13,
                                              6)
                          AND orde_4 = substr(a.cod_unid_geog,
                                              19,
                                              6)
                          AND orde_5 = substr(a.cod_unid_geog,
                                              25,
                                              6)
                          AND orde_6 = substr(a.cod_unid_geog,
                                              31,
                                              6)
                          AND orde_7 IS NULL)
                     ELSE
                      NULL
                   END AS nivel_6,
                   CASE
                     WHEN length(a.cod_unid_geog) > 36 THEN
                      (SELECT des_geog
                         FROM zon_valor_estru_geopo
                        WHERE pais_oid_pais = d.pais_oid_pais
                          AND orde_1 = substr(a.cod_unid_geog,
                                              1,
                                              6)
                          AND orde_2 = substr(a.cod_unid_geog,
                                              7,
                                              6)
                          AND orde_3 = substr(a.cod_unid_geog,
                                              13,
                                              6)
                          AND orde_4 = substr(a.cod_unid_geog,
                                              19,
                                              6)
                          AND orde_5 = substr(a.cod_unid_geog,
                                              25,
                                              6)
                          AND orde_6 = substr(a.cod_unid_geog,
                                              31,
                                              6)
                          AND orde_7 = substr(a.cod_unid_geog,
                                              37,
                                              6)
                          AND orde_8 IS NULL)
                     ELSE
                      NULL
                   END AS nivel_7,
                   CASE
                     WHEN length(a.cod_unid_geog) > 42 THEN
                      (SELECT des_geog
                         FROM zon_valor_estru_geopo
                        WHERE pais_oid_pais = d.pais_oid_pais
                          AND orde_1 = substr(a.cod_unid_geog,
                                              1,
                                              6)
                          AND orde_2 = substr(a.cod_unid_geog,
                                              7,
                                              6)
                          AND orde_3 = substr(a.cod_unid_geog,
                                              13,
                                              6)
                          AND orde_4 = substr(a.cod_unid_geog,
                                              19,
                                              6)
                          AND orde_5 = substr(a.cod_unid_geog,
                                              25,
                                              6)
                          AND orde_6 = substr(a.cod_unid_geog,
                                              31,
                                              6)
                          AND orde_7 = substr(a.cod_unid_geog,
                                              37,
                                              6)
                          AND orde_8 = substr(a.cod_unid_geog,
                                              43,
                                              6)
                          AND orde_9 IS NULL)
                     ELSE
                      NULL
                   END AS nivel_8,
                   CASE
                     WHEN length(a.cod_unid_geog) > 48 THEN
                      (SELECT des_geog
                         FROM zon_valor_estru_geopo
                        WHERE pais_oid_pais = d.pais_oid_pais
                          AND orde_1 = substr(a.cod_unid_geog,
                                              1,
                                              6)
                          AND orde_2 = substr(a.cod_unid_geog,
                                              7,
                                              6)
                          AND orde_3 = substr(a.cod_unid_geog,
                                              13,
                                              6)
                          AND orde_4 = substr(a.cod_unid_geog,
                                              19,
                                              6)
                          AND orde_5 = substr(a.cod_unid_geog,
                                              25,
                                              6)
                          AND orde_6 = substr(a.cod_unid_geog,
                                              31,
                                              6)
                          AND orde_7 = substr(a.cod_unid_geog,
                                              37,
                                              6)
                          AND orde_8 = substr(a.cod_unid_geog,
                                              43,
                                              6)
                          AND orde_9 = substr(a.cod_unid_geog,
                                              49,
                                              6))
                     ELSE
                      NULL
                   END AS nivel_9
              FROM mae_clien_direc a,
                   mae_tipo_direc  b,
                   seg_tipo_via    c,
                   mae_clien       d,
                   mae_clien_comun f,
                   zon_terri       t
             WHERE d.oid_clie = oidcliente
               AND d.oid_clie = a.clie_oid_clie
               AND a.ind_elim = 0
               AND b.oid_tipo_dire = a.tidc_oid_tipo_dire
               AND c.oid_tipo_via = a.tivi_oid_tipo_via
               AND a.ind_dire_ppal = 1
               AND f.clie_oid_clie(+) = d.oid_clie
               AND f.ticm_oid_tipo_comu(+) = 1
               AND a.terr_oid_terr = t.oid_terr(+)
             ORDER BY a.oid_clie_dire DESC)
     WHERE rownum = 1;

    RETURN v_direccion;
  END gen_fn_direc_mae_client;
  /**************************************************************************
  Descripcion : Devuelve la cantidad de campa?as de diferencia entre dos campa?as
  Fecha Creacion : 31/01/2013
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Yury Romero Anaya
  ***************************************************************************/
  FUNCTION gen_fn_devue_difer_perio
  (
    pscodperiodo01        VARCHAR2,
    pscodperiodo02        VARCHAR2,
    devuelvevalornodata   BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_codperi seg_perio_corpo.cod_peri%TYPE;
    numcampanualpais NUMBER := 18;
  BEGIN
    /* Obteniendo id del Periodo */

    ln_codperi := CASE
                    WHEN substr(pscodperiodo02,
                                1,
                                4) > substr(pscodperiodo01,
                                            1,
                                            4) THEN
                     (((to_number(substr(pscodperiodo02,
                                         1,
                                         4)) - 1) -
                     to_number(substr(pscodperiodo01,
                                        1,
                                        4))) * numcampanualpais) +
                     ((to_number(substr(pscodperiodo02,
                                        5,
                                        2)) + numcampanualpais) -
                     to_number(substr(pscodperiodo01,
                                       5,
                                       2)))
                   ELSE
                     (to_number(substr(pscodperiodo02,
                                       5,
                                       2)) - to_number(substr(pscodperiodo01,
                                                               5,
                                                               2)))
                   END;
     RETURN ln_codperi;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DIFER_PERIO: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devue_difer_perio;

/**************************************************************************
  Descripcion : Devuelve la cantidad de campa?as de diferencia entre dos campa?as
  Fecha Creacion : 31/01/2013
  Parametros Entrada :
  psCodPeriodo : Codigo de Periodo
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Doris Martinich
  ***************************************************************************/
  FUNCTION gen_fn_devue_difer_perio_pais
  (
    pscodPais             VARCHAR2,
    pscodperiodo01        VARCHAR2,
    pscodperiodo02        VARCHAR2,
    devuelvevalornodata   BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_codperi seg_perio_corpo.cod_peri%TYPE;
    numcampanualpais NUMBER;

  BEGIN
    numcampanualpais := gen_pkg_gener.gen_fn_param_pais(pscodPais,'GEN','000');
    /* Obteniendo id del Periodo */

    ln_codperi := CASE
                    WHEN substr(pscodperiodo02,
                                1,
                                4) > substr(pscodperiodo01,
                                            1,
                                            4) THEN
                     (((to_number(substr(pscodperiodo02,
                                         1,
                                         4)) - 1) -
                     to_number(substr(pscodperiodo01,
                                        1,
                                        4))) * numcampanualpais) +
                     ((to_number(substr(pscodperiodo02,
                                        5,
                                        2)) + numcampanualpais) -
                     to_number(substr(pscodperiodo01,
                                       5,
                                       2)))
                   ELSE
                     (to_number(substr(pscodperiodo02,
                                       5,
                                       2)) - to_number(substr(pscodperiodo01,
                                                               5,
                                                               2)))
                   END;
     RETURN ln_codperi;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DIFER_PERIO_PAIS: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devue_difer_perio_pais;

  /**************************************************************************
  Descripcion : Devuelve la cantidad de campañas de diferencia entre dos campañas
                DEVUELVE LA RESTA DE pscodperiodo02 - pscodperiodo01
  Fecha Creacion : 12/03/2015
  Parametros Entrada :
  pscodperiodo01 : Codigo de Periodo1
  pscodperiodo02 : Codigo de Periodo2
  devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
  Si es true : devuelve -1
  Si es false: devuelve Excepcion (Por defecto)
  Autor : Ivan Tocto Jaimes
  ***************************************************************************/
  FUNCTION gen_fn_devue_difer_perio_pais1
  (
    pscodPais             VARCHAR2,
    pscodperiodo01        VARCHAR2,
    pscodperiodo02        VARCHAR2,
    devuelvevalornodata   BOOLEAN := FALSE
  ) RETURN VARCHAR2 IS
    ln_codperi seg_perio_corpo.cod_peri%TYPE;
    numcampanualpais NUMBER;

  BEGIN
    numcampanualpais := gen_pkg_gener.gen_fn_param_pais(pscodPais,'GEN','000');
    /* Obteniendo id del Periodo */

    ln_codperi := CASE
                    WHEN substr(pscodperiodo02,
                                1,
                                4) <> substr(pscodperiodo01,
                                            1,
                                            4) THEN
                     (((to_number(substr(pscodperiodo02,
                                         1,
                                         4)) - 1) -
                     to_number(substr(pscodperiodo01,
                                        1,
                                        4))) * numcampanualpais) +
                     ((to_number(substr(pscodperiodo02,
                                        5,
                                        2)) + numcampanualpais) -
                     to_number(substr(pscodperiodo01,
                                       5,
                                       2)))
                   ELSE
                     (to_number(substr(pscodperiodo02,
                                       5,
                                       2)) - to_number(substr(pscodperiodo01,
                                                               5,
                                                               2)))
                   END;
     RETURN ln_codperi;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      IF ln_sqlcode < 0 THEN
        raise_application_error(-20123,
                                'ERROR GEN_FN_DEVUE_DIFER_PERIO_PAIS1: ' || ls_sqlerrm);
      END IF;
  END gen_fn_devue_difer_perio_pais1;

  FUNCTION gen_fn_reemp_carac_extra(p_text IN VARCHAR2) RETURN VARCHAR2 AS
  BEGIN
    RETURN regexp_replace(REPLACE(upper(utl_raw.cast_to_varchar2((nlssort(p_text,
                                                                          'nls_sort=binary_ai')))),
                                  '',
                                  ''),
                          '[^A-Z0-9() _/.,;:@-]',
                          '');
  END gen_fn_reemp_carac_extra;

  /***************************************************************************
  Descripcion       : Funcion que devuelve el indicador KIT Nueva
                      Indica si es el primer pedido de la consultora
  Fecha Creacion    : 16/05/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_indic_kit_nueva(pscodigocliente VARCHAR2) RETURN VARCHAR2 IS

    lncont NUMBER := 0;
  BEGIN

    SELECT COUNT(1) INTO lncont FROM cup_consu_nueva cupc WHERE cupc.cod_cons = pscodigocliente;

    IF lncont > 0 THEN
      RETURN '1';
    ELSE
      RETURN '0';
    END IF;
  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_INDIC_KIT_NUEVA: ' || ls_sqlerrm);

  END gen_fn_indic_kit_nueva;

  /***************************************************************************
  Descripcion       : Funcion que devuelve el indicador KIT Nueva
                      Indica si es el primer pedido de la consultora
  Fecha Creacion    : 16/05/2013
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION gen_fn_indic_prime_pedid
  (
    pscodigocliente  VARCHAR2,
    psperiodoingreso VARCHAR2
  ) RETURN VARCHAR2 IS

    lncont NUMBER := 0;

  BEGIN

    SELECT COUNT(1)
      INTO lncont
      FROM int_solic_conso_cabec c
     WHERE cod_clie = pscodigocliente
       AND ind_ocs_proc = '1';

    IF lncont > 0 THEN

      SELECT COUNT(1)
        INTO lncont
        FROM ped_histo_solic_conso_cabec c
       WHERE cod_clie = pscodigocliente
         AND cod_peri >= psperiodoingreso
         AND ind_ocs_proc = '1'
         AND ind_proc_gp2 = '1';

      IF lncont = 0 THEN
        RETURN '1';
      END IF;

    END IF;

    RETURN '0';

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_INDIC_PRIME_PEDID: ' || ls_sqlerrm);

  END gen_fn_indic_prime_pedid;

  /**************************************************************************
  Descripcion : Devuelve Periodo en base al Pais, fecha, para paises FOX
  Devuelve Periodo ACTIVO ACTUAL si hay cruce de campa?a
  Fecha Creacion : 23/07/2013
  Autor : Ivan Tocto Jaimes
  ***************************************************************************/
  FUNCTION gen_fn_devu_perio_fech_pfox
  (
    pscodpais  VARCHAR2,
    pdfecha    DATE
  ) RETURN VARCHAR2 IS

    ld_fecha    DATE;
    ls_cod_peri VARCHAR2(6);

  BEGIN

    ld_fecha    := gen_pkg_gener.gen_fn_fecha_sin_hora(pdfecha);

    BEGIN

        SELECT COD_CAMP
        INTO ls_cod_peri
        FROM ZON_DIREC_CAMPA
        WHERE PAIS_COD_PAIS = pscodpais
        AND FEC_INIC <= ld_fecha
        AND FEC_FINA >= ld_fecha;

        RETURN ls_cod_peri;

    EXCEPTION
      WHEN too_many_rows THEN

        SELECT CAM_PROC
        INTO ls_cod_peri
        FROM ZON_DIREC_CNTRL_FACTU
        WHERE PAIS_COD_PAIS = pscodpais
        AND EST_CAMP = '0'
        AND IND_CAMP_ACTI = '1';

        RETURN ls_cod_peri;
    END;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR GEN_FN_DEVU_PERIO_FECH_PFOX: ' || ls_sqlerrm);
  END gen_fn_devu_perio_fech_pfox;
  /**************************************************************************
  Descripcion           : Devuelve Longitud del Tipo Documento
  Fecha Creacion        : 15/08/2013
  Parametros Entrada    :
        vsCodPais       : Codigo Pais
        vsCodTipoDoc    : Codigo Tipo Documento
  Autor : Aurelio Oviedo
  ***************************************************************************/
  FUNCTION gen_fn_devuelve_long_tipo_docu
  (
    vsCodPais        VARCHAR2,
    vsCodTipoDoc     VARCHAR2
  ) RETURN NUMBER IS

    val_long MAE_TIPO_DOCUM.VAL_LONG%TYPE;

  BEGIN
    SELECT CASE
          WHEN NOT EXISTS (
                 SELECT *
                   FROM MAE_CLIEN_MODUL
                  WHERE COD_PAIS = vsCodPais
                    AND TIP_VALI = 'VAL_IDENTVAR'
                    AND MOD_VALI = 'NOC00'
                    AND IND_ACTI = 1)
             THEN (SELECT VAL_LONG
                     FROM MAE_TIPO_DOCUM
                    WHERE COD_TIPO_DOCU = vsCodTipoDoc)
          ELSE (SELECT 0
                  FROM DUAL)
       END VAL
    INTO val_long
    FROM DUAL;

    RETURN val_long;
  END gen_fn_devuelve_long_tipo_docu;
/**************************************************************************
     Descripcion       : DEVUELVE MONTO ULTIMO PEDIDO
     Fecha Creacion    : 22/01/2014
     Autor             : Rosalvina Ramirez Guardia
***************************************************************************/
     FUNCTION gen_fn_clien_monto_ultim_pedi1
  (
    psoidcliente          NUMBER,
    psperiodoultimopedido IN VARCHAR2
  ) RETURN NUMBER IS
    ln_montoultimopedido NUMBER(12,
                                2);
  BEGIN
    IF psperiodoultimopedido IS NOT NULL THEN
   SELECT val_tota_paga_loca
   INTO ln_montoultimopedido
     FROM (SELECT psc.val_tota_paga_loca
             FROM PED_SOLIC_CABEC     psc,
                  PED_TIPO_SOLIC_PAIS tsp,
                  PED_TIPO_SOLIC      ts,
                  CRA_PERIO           cra,
                  SEG_PERIO_CORPO     per
            WHERE cra.OID_PERI = psc.PERD_OID_PERI
              AND per.OID_PERI = cra.peri_oid_peri
              AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
              AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
              AND psc.CLIE_OID_CLIE = psoidcliente
              AND ts.COD_TIPO_SOLI = 'C1'
              AND psc.ESSO_OID_ESTA_SOLI <> 4
              AND per.cod_peri <= psperiodoultimopedido
            ORDER BY cra.FEC_INIC DESC, psc.VAL_NUME_SOLI DESC)
    WHERE ROWNUM = 1;
    END IF;
    RETURN ln_montoultimopedido;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
  END gen_fn_clien_monto_ultim_pedi1;

  /***************************************************************************
    Descripcion       : Obtiene el numero de Activas finales por Seccion
    Fecha Creacion    : 25/03/2014
    Autor             : Juan Altamirano
   ***************************************************************************/
   FUNCTION gen_fn_devue_activas_seccion
   (
      psCodigoPeriodo VARCHAR2,
      psCodigoRegion  VARCHAR2,
      psCodigoZona    VARCHAR2,
      psCodigoSeccion VARCHAR2
   )RETURN NUMBER IS

   vnCantActivas    NUMBER;
   vnOidPeriodo     NUMBER;

   BEGIN

    IF(psCodigoPeriodo IS NOT NULL) THEN
     vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

     SELECT COUNT(*) INTO vnCantActivas
     FROM MAE_CLIEN_UNIDA_ADMIN MCUA,
          ZON_TERRI_ADMIN ZTA,
          ZON_SECCI ZS,
          ZON_ZONA ZZ,
          ZON_REGIO ZR,
          MAE_CLIEN_HISTO_ESTAT MCHE,
          MAE_ESTAT_CLIEN MEC
     WHERE MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
     AND  ZTA.ZSCC_OID_SECC = ZS.OID_SECC
     AND ZS.ZZON_OID_ZONA  = ZZ.OID_ZONA
     AND ZZ.ZORG_OID_REGI = ZR.OID_REGI
     AND vnOidPeriodo >= MCUA.PERD_OID_PERI_INI
     AND (vnOidPeriodo <= MCUA.PERD_OID_PERI_FIN OR MCUA.PERD_OID_PERI_FIN IS NULL)
     AND ZZ.COD_ZONA = psCodigoZona
     AND ZR.COD_REGI = psCodigoRegion
     AND ZS.COD_SECC = psCodigoSeccion
     AND MCUA.CLIE_OID_CLIE = MCHE.CLIE_OID_CLIE
     AND vnOidPeriodo >= MCHE.PERD_OID_PERI
     AND (vnOidPeriodo <= MCHE.PERD_OID_PERI_PERI_FIN OR MCHE.PERD_OID_PERI_PERI_FIN IS NULL)
     AND MCHE.ESTA_OID_ESTA_CLIE = MEC.OID_ESTA_CLIE
     AND MEC.COD_ESTA_CLIE IN ('02','03','04','06','08');

     RETURN vnCantActivas;

    END IF;

   EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;

   END gen_fn_devue_activas_seccion;

 /**************************************************************************
  Descripcion : Calculo digito verificador de nit
  Fecha Creacion : 27/06/2014
  Autor : Juan Carlos Gutierrez
  ***************************************************************************/
  FUNCTION gen_fn_devue_digit_verif_nit
   (
    psNroDocumento VARCHAR2
   )RETURN NUMBER IS

   vnFactor         NUMBER;
   vnDigito         NUMBER;
   vnResiduo        NUMBER;

   BEGIN



    IF(psNroDocumento IS NOT NULL) THEN
     vnFactor:=TO_NUMBER(psNroDocumento);

     SELECT
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),15,1)) * 3 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),14,1)) * 7 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),13,1)) * 13 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),12,1)) * 17 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),11,1)) * 19 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),10,1)) * 23 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),9,1)) * 29 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),8,1)) * 37 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),7,1)) * 41 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),6,1)) * 43 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),5,1)) * 47 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),4,1)) * 53 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),3,1)) * 59 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),2,1)) * 67 +
     TO_NUMBER(SUBSTR(LTRIM(TO_CHAR(vnFactor,'000000000000000')),1,1)) * 71
     INTO vnResiduo
     FROM DUAL;
     vnResiduo := vnResiduo MOD 11;

     IF vnResiduo=0 THEN
        vnDigito:=0;
     ELSIF vnResiduo=1 THEN
        vnDigito:=1;
     ELSE
        vnDigito:=11-vnResiduo;
     END IF;

     RETURN vnDigito;

   END IF;

   RETURN 0;

  END gen_fn_devue_digit_verif_nit;

  /**************************************************************
  Descripcion        : Devuelve el valor del parametro de pais
  Fecha Creacion     : 17/11/2014
  Parametros         : pscodigopais : Pais
                       pscodigosistema : Sistema
                       psnombreparametro : nombre Parametro
  Autor              : Sergio Apaza
  ***************************************************************/
  FUNCTION gen_fn_nombre_param_pais
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    psnombreparametro VARCHAR2
  ) RETURN VARCHAR2 IS
    lsparametro bas_param_pais.val_para%TYPE;

    lscodigopais SEG_PAIS.COD_PAIS%TYPE;
  BEGIN
    lscodigopais := pscodigopais;
    if lscodigopais is null then
       SELECT MIN(val_para)
      INTO lscodigopais
      FROM bas_param_pais
     WHERE cod_para = '000'
       AND cod_sist = 'BAS';
    END if;

    SELECT MIN(val_para)
      INTO lsparametro
      FROM bas_param_pais
     WHERE nom_para = psnombreparametro
       AND cod_sist = pscodigosistema
       AND cod_pais = lscodigopais;
    RETURN lsparametro;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR gen_fn_nombre_param_pais: ' || ls_sqlerrm);
  END gen_fn_nombre_param_pais;
  
  /**************************************************************
  Descripcion        : Devuelve el valor del origen de pedido de la consultora
  Fecha Creacion     : 19/08/2015
  Parametros         : pscodclie : Codigo Cliente
                       pscodpe : Codigo Periodo
  Autor              : Aurelio Oviedo
  ***************************************************************/
  FUNCTION gen_fn_devue_pedid_orig
  (
    pscodclie VARCHAR2,
    pscodpe VARCHAR2
  ) RETURN VARCHAR2 IS
  
  lsdorigen sto_orige_docum.des_orig%TYPE;
    
  BEGIN 
     BEGIN
         SELECT MIN(d.des_orig) 
         into lsdorigen
             FROM
             (     SELECT cons.cod_peri,cons.cod_clie,cons.fec_soli,cons.ind_rece_ocr,cons.ind_rece_web,cons.ind_rece_dd,cons.ind_rece_digi,cons.ind_rece_cc,cons.ind_rece_mens,cons.ind_rece_onli,cons.ind_rece_ivr,cons.tipo_soli
                FROM   int_solic_conso_cabec cons
                WHERE  cons.cod_clie=pscodclie
                     AND cons.cod_peri=pscodpe
                     and cons.ind_proc_gp2='1'
                     and cons.ind_error_sgpe='0'
                UNION
                SELECT cons.cod_peri,cons.cod_clie,cons.fec_soli,cons.ind_rece_ocr,cons.ind_rece_web,cons.ind_rece_dd,cons.ind_rece_digi,cons.ind_rece_cc,cons.ind_rece_mens,cons.ind_rece_onli,cons.ind_rece_ivr,cons.tipo_soli
                  FROM  ped_histo_solic_conso_cabec cons
                WHERE  cons.cod_clie=pscodclie
                     AND cons.cod_peri=pscodpe
                     and cons.ind_proc_gp2='1'
                     and cons.ind_error_sgpe='0'
            ) pe,
             sto_combi_orige_docum c,
             sto_orige_docum d
             where c.ind_rece_ocr = pe.ind_rece_ocr
                AND c.ind_rece_web = pe.ind_rece_web
                AND c.ind_rece_dd = pe.ind_rece_dd
                AND c.ind_rece_digi = pe.ind_rece_digi
                AND c.ind_rece_cc = pe.ind_rece_cc
                AND c.ind_rece_mens = pe.ind_rece_mens
                AND c.ind_rece_onli = pe.ind_rece_onli
                AND c.ind_rece_ivr = pe.ind_rece_ivr
                AND c.cod_tipo_docu='OCC'
                AND d.cod_tipo_docu=c.cod_tipo_docu
                AND d.cod_orig=c.cod_orig;
     EXCEPTION
        WHEN NO_DATA_FOUND THEN
            lsdorigen := '';
            RETURN lsdorigen;
     END;
     
     RETURN lsdorigen;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR gen_fn_devue_pedid_orig: ' || ls_sqlerrm);
      
  END gen_fn_devue_pedid_orig;
  
    /**************************************************************************
  Descripcion : Devuelve Fecha sin domingos ni feriados usado en  RET 16
  la Hora en formato dd/mm/yyyy
  Fecha Creacion : 16/09/2015
  Autor : FFVV
  ***************************************************************************/
  FUNCTION gen_fn_fecha_dias_habiles(pdFecha DATE, lnDiferencia NUMBER) RETURN DATE IS
    ldFechaIni            DATE;
    ldFechaFin            DATE;
    lnDiasDomingo         NUMBER;
    lnDiasFeriado         NUMBER;  
   
   BEGIN
    ldFechaFin := pdFecha;
    ldFechaIni := ldFechaFin - lnDiferencia;

    WHILE TRUE
    LOOP
    DBMS_OUTPUT.PUT_LINE('-----------------------------');    
    DBMS_OUTPUT.PUT_LINE('ldFechaFin : ' || ldFechaFin);
    DBMS_OUTPUT.PUT_LINE('ldFechaIni : ' || ldFechaIni);
   
     /* SELECT COUNT(1)
        INTO lnDiasDomingo
        FROM bas_calen a
       WHERE A.FEC_CALE >= ldFechaIni
         AND A.FEC_CALE <= ldFechaFin
         AND A.NUM_POSI_SEMA = '1';
         DBMS_OUTPUT.PUT_LINE('lnDiasDomingo : ' || lnDiasDomingo);*/

      SELECT COUNT(distinct a.fec_feri)
        INTO lnDiasDomingo
        FROM cra_feria a
       WHERE A.FEC_FERI >= ldFechaIni
         AND A.FEC_FERI <= ldFechaFin
         AND a.ind_fest = '0'
         AND TO_CHAR (A.FEC_FERI, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') = 'SUN'
         ;
         DBMS_OUTPUT.PUT_LINE('lnDiasDomingo : ' || lnDiasDomingo);


      /*SELECT COUNT(1)
        INTO lnDiasFeriado
        FROM bas_calen a
       WHERE A.FEC_CALE >= ldFechaIni
         AND A.FEC_CALE <= ldFechaFin
         AND A.NUM_POSI_SEMA <> '1'
         AND A.IND_FERI = 'S';
         DBMS_OUTPUT.PUT_LINE('lnDiasFeriado : ' || lnDiasFeriado);*/

      SELECT COUNT(distinct a.fec_feri)
        INTO lnDiasFeriado
        FROM cra_feria a
       WHERE A.FEC_FERI >= ldFechaIni
         AND A.FEC_FERI <= ldFechaFin
         AND a.ind_fest = '1'
         ;
         DBMS_OUTPUT.PUT_LINE('lnDiasFeriado : ' || lnDiasFeriado);
         
         
         
         
        IF ((lnDiasDomingo + lnDiasFeriado) = 0)  THEN
           EXIT;
        ELSE
          ldFechaFin := ldFechaIni - 1;
          ldFechaIni := ldFechaFin - (lnDiasDomingo + lnDiasFeriado) + 1;
        END IF;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('********************************');
    DBMS_OUTPUT.PUT_LINE('ldFecha : ' || ldFechaIni);
    RETURN ldFechaIni;
  END gen_fn_fecha_dias_habiles;
  
   /**************************************************************
  Descripcion        : Devuelve el Promedio Días Pago de últimos 18 pedidos de las consultoras 
  Fecha Creacion     : 10/11/2015
  Parametros         : pscodclie : Oid Cliente
                       pscodpe : Codigo Periodo
  Autor              : Karina Valencia
  ***************************************************************/
  FUNCTION gen_fn_devue_prome_pagos_ultim
  (
    psoidclie NUMBER,
    pscodpe VARCHAR2
  ) RETURN NUMBER IS
  
  lsPromedio NUMBER;
    
  BEGIN 
    
         SELECT  ROUND(AVG(dias_pago),0)INTO lsPromedio
         FROM ( SELECT *
                 FROM 
                    (SELECT spc.cod_peri,mcc.fec_ulti_movi - mcc.fec_docu dias_pago
                       FROM ccc_movim_cuent_corri mcc,
                            mae_clien mc,
                            cra_perio cp,
                            seg_perio_corpo spc    
                      WHERE mc.oid_clie = mcc.clie_oid_clie 
                        AND mcc.perd_oid_peri = cp.oid_peri
                        AND cp.peri_oid_peri = spc.oid_peri 
                        AND mcc.subp_oid_subp_crea = 2001
                        AND mcc.clie_oid_clie = psoidclie
                        AND mcc.imp_movi > 0
                        AND mcc.imp_pend = 0
                        AND spc.cod_peri < pscodpe
                    ORDER BY 1 DESC)
                    WHERE ROWNUM <= 18)
    
     END;
     
     RETURN lsPromedio;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR gen_fn_devue_prome_pagos_ultim: ' || ls_sqlerrm);
      
  END gen_fn_devue_prome_pagos_ultim; 
  

END gen_pkg_gener;
/
