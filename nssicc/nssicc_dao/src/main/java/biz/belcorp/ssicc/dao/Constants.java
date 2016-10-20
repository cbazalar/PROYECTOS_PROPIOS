package biz.belcorp.ssicc.dao;

import biz.belcorp.ssicc.dao.Constants;


/**
 * Constantes usadas en toda la aplicación.
 * <p>
 * <a href="Constants.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramirez </a>
 */
public class Constants {

	// name util class
	public static final String I_CONSTANTS = "constants";

	/** Application scoped attribute for authentication url */
	public static final String VAL_STATUS = "valStatus";

	public static final String TERMINO_OK = "proceso.ok";

	public static final String AUTH_URL = "authURL";

	/** Application scoped attributes for SSL Switching */
	public static final String HTTP_PORT = "httpPort";

	public static final String HTTPS_PORT = "httpsPort";
	/** The application scoped attribute for indicating a secure login */
	public static final String SECURE_LOGIN = "secureLogin";

	/** La clave del algoritmo de cifrado a ser usado en las contraseñas */
	public static final String ENC_ALGORITHM = "MD5";

	/** Flag para indicar si los passwords deben ser cifrados */
	public static final String ENCRYPT_PASSWORD = "encryptPassword";

	/** Separador de archivos tomado de las propiedades del Sistema */
	public static final String FILE_SEP = System.getProperty("file.separator");

	/** Carpeta del Usuario tomada de las propiedades del Sistema */
	public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

	/**
	 * Valor del parámetro que indica que se va a exportar a PDF de tal forma
	 * que en el ActionFilter se pueda determinar si se setea o no el objeto
	 * Locale
	 */
	public static final String EXPORT_TO_PDF = "exportToPDF";

	/**
	 * Formato de fecha por defecto
	 */
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
	public static final String DEFAULT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	public static final String DEFAULT_PLAIN_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

	/**
	 * The session scope attribute under which the breadcrumb ArrayStack is
	 * stored
	 */
	public static final String BREADCRUMB = "breadcrumbs";

	public static final String LOCALE_KEY = "localeKey";

	/**
	 * The session scope attribute under which the Usuario object for the
	 * currently logged in user is stored.
	 */
	public static final String USER_KEY = "currentUser";

	public static final String COUNTRY_KEY = "currentCountry";

	public static final String USER_MENU_KEY = "currentUserMenu";

	public static final String USER_ACCESS_KEY = "currentUserAccess";

	/**
	 * The request scope attribute under which an editable user form is stored
	 */
	public static final String USER_EDIT_KEY = "userForm";

	/**
	 * The request scope attribute that holds the user list
	 */
	public static final String USER_LIST = "userList";

	/**
	 * The request scope attribute for indicating a newly-registered user
	 */
	public static final String REGISTERED = "registered";

	/**
	 * Valor usado en la base de datos como flag activo
	 */
	public static final String YES = "S";

	/**
	 * Valor usado en la base de datos como flag activo
	 */
	public static final String SI = "S";

	/**
	 * Valor usado en la base de datos como flag inactivo
	 */
	public static final String NO = "N";

	/**
	 * The name of the Administrator role, as specified in web.xml
	 */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	/**
	 * The name of the Usuario role, as specified in web.xml
	 */
	public static final String ROLE_USER = "ROLE_USER";

	/**
	 * The name of the user's role list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String USER_ROLES = "userRoles";

	/**
	 * El nombre de la lista con todos los roles activos, almacenada en el
	 * contexto de la aplicación
	 */
	public static final String ALL_ROLES = "allRoles";

	/**
	 * El nombre del map conteniendo listas de los roles activos, el key usado
	 * para guardar cada lista es el valor del lenguaje y cada lista contiene
	 * roles cuyas descripciones estan en el lenguaje correspondiente
	 */
	public static final String ALL_ROLES_MAP = "allRolesMap";

	/**
	 * El nombre de la lista con todos los paises activos, almacenada en el
	 * contexto de la aplicación
	 */
	public static final String ALL_PAISES = "allPaises";

	/**
	 * El nombre de la lista con todos los idiomas activos, almacenada en el
	 * contexto de la aplicación
	 */
	public static final String ALL_IDIOMAS = "allIdiomas";

	/**
	 * Nombre del cookie para la funcionalidad "Remember Me"
	 */
	public static final String LOGIN_COOKIE = "sessionId";

	/**
	 * Nombre del hashmap de configuracion almacenado con alcance de aplicacion
	 */
	public static final String CONFIG = "appConfig";

	/**
	 * The request scope attribute that holds the rol form.
	 */
	public static final String ROL_KEY = "rolForm";

	/**
	 * The request scope attribute that holds the rol list
	 */
	public static final String ROL_LIST = "rolList";

	/**
	 * The request scope attribute that holds the menuRolList list
	 */
	public static final String MENU_ROL_LIST = "menuRolList";

	/**
	 * The request scope attribute that holds the menuRolPagedList list
	 */
	public static final String MENU_ROL_PAGED_LIST = "menuRolPagedList";

	/**
	 * The request scope attribute that holds the rolUsuarioList list
	 */
	public static final String ROL_USUARIO_LIST = "rolUsuarioList";

	/**
	 * The request scope attribute that holds the accesoRol list
	 */
	public static final String ACCESO_ROL_LIST = "accesoRolList";

	/**
	 * The request scope attribute that holds the accesoRol list
	 */
	public static final String ACCESO_ROL_PAGED_LIST = "accesoRolPagedList";

	/**
	 * The request scope attribute that holds the descargaPrivilege list
	 */
	public static final String DESCARGA_PRIVILEGE_LIST = "descargaPrivilegeList";

	/**
	 * The request scope attribute that holds the generacionArchivoOCR list
	 */
	public static final String GENERACION_OCR_LIST = "generacionArchivoOCRList";

	/**
	 * The request scope attribute that holds the cargaPrivilege list
	 */
	public static final String CARGA_PRIVILEGE_LIST = "cargaPrivilegeList";

	/**
	 * The request scope attribute that holds the perfilUsuario list
	 */
	public static final String PERFIL_USUARIO_LIST = "perfilUsuarioList";

	/**
	 * The request scope attribute that holds the perfilUsuario list
	 */
	public static final String PERFIL_USUARIO_PAGED_LIST = "perfilUsuarioPagedList";

	/**
	 * The request scope attribute that holds the menu form.
	 */
	public static final String MENU_KEY = "menuForm";

	/**
	 * The request scope attribute that holds the menu list
	 */
	public static final String MENU_LIST = "menuList";

	public static final String MENU_OPCIONES_LIST = "menuOpcionesList";

	/**
	 * The request scope attribute that holds the usuario form.
	 */
	public static final String USUARIO_KEY = "usuarioForm";

	/**
	 * The request scope attribute that holds the usuario list
	 */
	public static final String USUARIO_LIST = "usuarioList";

	/**
	 * The request scope attribute that holds the pais form.
	 */
	public static final String PAIS_KEY = "paisForm";

	/**
	 * The request scope attribute that holds the pais list
	 */
	public static final String PAIS_LIST = "paisList";

	/**
	 * The request scope attribute that holds the idioma form.
	 */
	public static final String IDIOMA_KEY = "idiomaForm";

	/**
	 * The request scope attribute that holds the idioma list
	 */
	public static final String IDIOMA_LIST = "idiomaList";

	/**
	 * The request scope attribute that holds the controlFacturacionList list
	 */
	public static final String CONTROL_FACTURACION_LIST = "controlFacturacionList";

	/**
	 * The request scope attribute that holds the controlFacturacion form.
	 */
	public static final String CONTROL_FACTURACION_KEY = "controlFacturacionForm";

	/**
	 * The request scope attribute that holds the sticker form.
	 */
	public static final String STICKER_KEY = "stickerForm";

	/**
	 * The request scope attribute that holds the sticker list
	 */
	public static final String STICKER_LIST = "stickerList";

	/**
	 * The request scope attribute that holds the premio list
	 */
	public static final String PREMIO_LIST = "premioList";

	/**
	 * The attribute's value by default for Channel of Transport
	 */
	public static final String CODIGO_CANAL = "01";

	/**
	 * Constantes que contienen los valores de algunas de las columnas de las
	 * entidades tales como estados, indicadores, etc.
	 */

	/** Valor usado en los estados para indicar que el registro esta activo */
	public static final String ESTADO_ACTIVO = "1";

	/**
	 * Valor usado en los estados para indicar que el registro esta inactivo o
	 * ha sido eliminado logicamente
	 */
	public static final String ESTADO_INACTIVO = "9";

	/**
	 * La entidad Usuario maneja los estados de manera distinta al resto de
	 * entidades, por eso la siguiente constante almacena el valor del estado de
	 * los usuario que están activos y cuya contraseña no ha expirado
	 */
	public static final String ESTADO_USUARIO_ACTIVO = "2";

	/**
	 * Valor usado en el estado del usuario para indicar que debe cambiar su
	 * clave en el siguiente inicio de sesión
	 */
	public static final String ESTADO_USUARIO_CAMBIAR_CLAVE = "1";

	/*
	 * Valores genericos para indicar exito o error
	 */
	public static String OK = "0";

	public static String ERROR = "1";

	public static String OK_MESSAGE = "OK";

	public static String ERROR_MESSAGE = "ERROR";

	/*
	 * Constantes de los status de la tarjeta de puntos
	 */
	public static final String STATUS_TARJETA_TRANSFERIDO = "T";

	public static final String STATUS_TARJETA_NO_TRANSFERIDO = "N";

	public static final String STATUS_TARJETA_INFORMADO = "I";

	public static final String STATUS_TARJETA_NO_INFORMADO = "F";

	public static final String STATUS_TARJETA_POR_CONFIMAR = "C";

	public static final String STATUS_TARJETA_CONFIRMADO = "D";

	/*
	 * Constantes de los status del cliente
	 */
	public static final String STATUS_COMERCIAL_TRANSFERIDO = "T";

	public static final String STATUS_COMERCIAL_NO_TRANSFERIDO = "N";

	public static final String STATUS_COMERCIAL_INFORMADO = "I";

	public static final String STATUS_COMERCIAL_NO_INFORMADO = "F";

	/*
	 * Constantes de los status del premio
	 */
	public static final String STATUS_PREMIO_NO_TRANSFERIDO = "N";

	public static final String STATUS_PREMIO_TRANSFERIDO = "T";

	public static final String STATUS_PREMIO_ENTREGADO = "P";

	public static final String STATUS_PREMIO_FALTANTE = "R";

	public static final String STATUS_PREMIO_ANULADO = "A";

	public static final String STATUS_PREMIO_CONSULTORA_EN_BLANCO = "C";

	public static final String STATUS_PREMIO_SIN_PEDIDO = "O";

	public static final String STATUS_PREMIO_FALTA_PUNTOS = "F";

	public static final String STATUS_PREMIO_FALTA_PUNTOS_INFORMADO = "B";

	public static final String STATUS_PREMIO_FALTANTE_ANUNCIADO = "U";

	public static final String STATUS_PREMIO_MONTO_MINIMO = "M";

	public static final String STATUS_PREMIO_PENDIENTE = "T";

	public static final String STATUS_PREMIO_PROCESADO = "P";

	public static final String STATUS_PREMIO_NO_PROCESADO = "X";

	public static final String STATUS_PREMIO_NO_REGISTRADO_MATRIZ = "Z";

	public static final String STATUS_PREMIO_ATENCION_MANUAL = "S";

	public static final String STATUS_PREMIO_DEVOLUCION = "D";

	/*
	 * Constants de los indicadores de premio
	 */
	public static final String INDICADOR_PREMIO_CANJE = "P";

	public static final String INDICADOR_PREMIO_OPORTUNIDAD = "Q";

	/*
	 * Constantes de los stickers
	 */
	public static final String STATUS_STICKER_ANULADO = "A";

	/*
	 * Constantes de subgerencias
	 */
	public static final String CODIGO_SUBGERENCIA_DEFAULT = "00";

	/*
	 * Constantes usadas en las consultas o procesos en los cuales se elige
	 * entre usar consulta de fichas o tarjetas
	 */
	public static final String TIPO_CONSULTA_FICHAS = "F";

	public static final String TIPO_CONSULTA_TARJETAS = "T";

	public static final String TIPO_CONSULTA_CANJE_PREMIOS = "P";

	/*
	 * Constantes usadas en las consultas o procesos en los cuales se elige
	 * entre usar rangos por meses o campañas
	 */
	public static final String TIPO_PERIODO_DIAS = "D";

	public static final String TIPO_PERIODO_MESES = "M";

	public static final String TIPO_PERIODO_CAMPAÑAS = "C";

	/*
	 * Constantes de los procesos de carga y descarga, los cuales incluyen los
	 * tipos de proceso y nombres de los archivos
	 */
	public static String TIPO_PROCESO_DESCARGA = "D";

	public static String TIPO_PROCESO_CARGA = "C";

	public static String TIPO_PROCESO_CONTROL = "Z";

	public static String TIPO_PROCESO_CALIFICACION = "E";

	public static final String TIPO_GENERACION_UNITARIA = "U";

	public static final String TIPO_GENERACION_PAQUETE = "P";
	
	public static final String TIPO_GENERACION_COMPUESTA = "C";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_FACTORES = "PRI000";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_FICHAS = "PRI001";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_TARJETAS = "PRI002";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_PREMIOS = "PRI003";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_OCR_PREMIOS_CABECERA = "PRI004";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_OCR_PREMIOS_DETALLE = "PRI005";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_OCR_OPORTUNIDADES_CABECERA = "PRI006";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_OCR_OPORTUNIDADES_DETALLE = "PRI007";

	public static final String INTERFAZ_CARGA_ARCHIVO_CONTROL_FACTURACION = "PRI008";

	public static final String INTERFAZ_CARGA_ARCHIVO_SUBGERENCIAS = "PRI009";

	public static final String INTERFAZ_CARGA_ARCHIVO_REGIONES = "PRI010";

	public static final String INTERFAZ_CARGA_ARCHIVO_ZONAS = "PRI011";

	public static final String INTERFAZ_CARGA_ARCHIVO_CONSULTORAS = "PRI012";

	public static final String INTERFAZ_CARGA_ARCHIVO_PRODUCTOS = "PRI013";

	public static final String INTERFAZ_CARGA_ARCHIVO_STICKERS = "PRI014";

	public static final String INTERFAZ_CARGA_ARCHIVO_FICHAS = "PRI015";

	public static final String INTERFAZ_CARGA_ARCHIVO_TARJETAS = "PRI016";

	public static final String INTERFAZ_CARGA_ARCHIVO_PREMIOS = "PRI017";

	public static final String INTERFAZ_ARCHIVO_CONTROL = "GCONTROL.TXT";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_DESPACHOS_DIRECTOS = "SALESORD.TXT";

	public static final String INTERFAZ_DESCARGA_ARCHIVO_CALIFICACION = "PRVCONSU.TXT";

	public static final String SISTEMA_LIST = "sistemaList";

	public static final String BloqueoLider_LIST = "bloqueoLiderList";

	public static final String LIBRETA_AHORRO_LIST = "libretaAhorroList";

	public static final String INTERFAZ_LIST = "interfazList";

	public static final String HISTORICO_LIST = "historicoList";

	public static final String PROCESO_BATCH_ACTU_LIST = "consultaBASProcesoBatchActuaList";

	public static final String PROCESO_FINAN_ACTU_LIST = "consultaFINProcesoActuaList";

	public static final String PROCESO_BATCH_HISTO_LIST = "consultaBASProcesoBatchHistoList";

	public static final String CONSULTA_COM_RESPONSABLES_UA = "consultaCOMResponsablesUAList";

	public static final String MANTENIMIENTO_COMISION_GERENTE_ZONA = "mantenimientoCOMComisionGerenteZonaList";

	public static final String MANTENIMIENTO_PARAMETRO_ZONA_NUEVA = "mantenimientoCOMParametroZonaNuevaList";

	public static final String CONSULTA_COMISION_GERENTE_ZONA = "consultaCOMComisionGerenteZonaList";

	public static final String CONSULTA_COMISION_LIDERES = "consultaCOMComisionLideresList";

	public static final String PARAMETRO_TRAMO_COMISION_1 = "parametroTramoComision1";

	public static final String PARAMETRO_TRAMO_COMISION_2 = "parametroTramoComision2";

	public static final String TIPO_UA_SUBGERENCIA = "1";

	public static final String TIPO_UA_REGION = "2";

	public static final String TIPO_UA_ZONA = "3";

	public static final String TIPO_UA_SECCION = "4";

	public static final String COLUMNA_CONSULTA_COM_RESPONSABLES_UA = "columnaConsultaCOMResponsablesUAList";

	public static final String ALL_INTERFACES = "allInterfaces";

	public static final String ALL_PROCESO_BATCH = "allProcesoBatch";

	public static final String ALL_SISTEMAS = "allSistemas";

	public static final String ALL_DELIMITADORES = "allDelimitadores";

	public static final String ALL_FORMATOS = "allFormatos";

	public static final String ALL_TIPOS_FORMATO_ARCHIVO = "allTiposFormatoArchivo";

	public static final String INTERFAZ_TIPO_ENTRADA = "E";

	public static final String INTERFAZ_TIPO_ENTRADA_DESCRIPCION = "ENTRADA";

	public static final String INTERFAZ_TIPO_SALIDA = "S";

	public static final String INTERFAZ_TIPO_SALIDA_DESCRIPCION = "SALIDA";

	public static final String INTERFAZ_TIPO_INDICADOR_CABECERA = "1";

	public static final String INTERFAZ_TIPO_INDICADOR_CABECERA_DETALLE = "2";

	public static final String INTERFAZ_TIPO_INDICADOR_CABECERA_DESCRIPCION = "Proceso en Cabecera";

	public static final String INTERFAZ_TIPO_INDICADOR_CABECERA_DETALLE_DESCRIPCION = "Proceso en Cabecera y Detalle";

	public static final String ARCHIVO_FIJO = "F";

	public static final String ARCHIVO_VARIABLE = "V";

	public static final String ENVIO_RED = "1";

	public static final String ENVIO_FTP = "2";
	
	public static final String ENVIO_MIXTO = "3";
	
	public static final String FORMATEAR_ALFANUMERICOS = "A";

	public static final String FORMATEAR_TODOS = "T";

	public static final String INTERFAZ_NUEVO_CODIGO_LIST = "interfazNuevosCodigoList";

	public static final String CODIGO_SUBTIPO_CLIENTE_DEFAULT = "04";

	public static final String CODIGO_CANAL_DEFAULT = "VD";
	
	public static final String CODIGO_REGION_DAFULT="24";
	
	public static final String CODIGO_MARCA_DEFAULT = "T";

	public static final String CODIGO_MARCA_EBEL = "EB";

	public static final String CODIGO_ACTIVIDAD_DEFAULT = "FA";

	public static final String CODIGO_TIPO_CLIENTE_DEFAULT = "02";

	public static final String CODIGO_TIPO_BLOQUEO_DEFAULT = "02";

	public static final String CODIGO_ACCESO_DEFAULT = "GZ";

	public static final String CODIGO_SOCIEDAD_DEFAULT = "PE01";

	public static final String CODIGO_SOCIEDAD_DEFAULT_CALCULO_RECU_COBRANZA = "PE02";

	public static final String CODIGO_SUBACCESO_DEFAULT = "GZ";

	public static final String CODIGO_ALMACEN_DEFAULT = "A01";

	public static final String CODIGO_ESTADO_MERCA_DEFAULT = "LD";

	public static final String CODIGO_PERIODO_DEFAULT = "_codigoPeriodoDefault";

	public static final String CODIGO_TIPO_CLASIFICACION_DEFAULT = "01";

	public static final String CODIGO_TIPO_CLASIFICACION_DUPLA = "23";

	public static final String CODIGO_CLASIFICACION_DEFAULT = "01";

	public static final String CODIGO_TIPO_VINCULO_DEFAULT = "01";

	public static final String INTERFAZ_DATA = "interfazData";

	public static final String INTERFAZ_DATA2 = "interfazData2";

	public static final String SICC_MARCA_LIST = "siccMarcaList";

	public static final String SICC_LINEA_LIST = "siccLineaList";

	public static final String CODIGO_LOTE_LIST = "siccLoteList";

	public static final String SICC_NUMLOTE_LIST = "siccNumLoteList";

	public static final String SICC_TIPO_OPERACION_LIST = "siccTipoOperacionList";

	public static final String SICC_UNIDAD_NEGOCIO_LIST = "siccUnidadNegocioList";

	public static final String SICC_NEGOCIO_LIST = "siccNegocioList";

	public static final String SICC_ESTADO_RECLAMO_LIST = "siccEstadoReclamoList";

	public static final String SICC_ESTADO_OPERACION_LIST = "siccEstadoOperacionList";

	public static final String SICC_CANAL_LIST = "siccCanalList";

	public static final String SICC_TIPOSOL_LIST = "siccTipoSolList";

	public static final String SICC_VERSION_LIST = "siccVersionList";

	public static final String SICC_RANGO_PERIODO_LIST = "siccRangoPeriodoList";

	public static final String SICC_ACTIVIDAD_LIST = "siccActividadList";

	public static final String SICC_PERIODO_LIST = "siccPeriodoList";

	public static final String SICC_CENTROD_LIST = "siccCentroDList";

	public static final String SICC_COMISION_LIST = "siccComisionList";

	public static final String SICC_ACCESO_LIST = "siccAccesoList";

	public static final String SICC_ACCESO_LIST_TODOS = "siccAccesoTodosList";

	public static final String SICC_SUBACCESO_LIST = "siccSubaccesoList";

	public static final String SICC_TIPO_CLIENTE_LIST = "siccTipoClienteList";

	public static final String SICC_SUB_TIPO_CLIENTE_LIST = "siccSubTipoClienteList";

	public static final String SICC_TIPO_DOCUMENTO_LIST = "siccTipoDocumentoList";

	public static final String SICC_TIPO_COMPROBANTE_LIST = "siccTipoComprobanteList";

	public static final String SICC_TIPO_INGRESO_LIST = "siccTipoIngresoList";

	public static final String SICC_TIPO_CIERRE_LIST = "siccTipoCierreList";

	public static final String SICC_ALMACEN_LIST = "siccAlmacenList";

	public static final String SICC_ESTADO_MERCA_LIST = "siccEstadoMercaList";

	public static final String SICC_SOCIEDAD_LIST = "siccSociedadList";

	public static final String SICC_REGION_LIST = "siccRegionList";

	public static final String SICC_SUBGERENCIA_LIST = "siccSubGerenciaList";

	public static final String SICC_MOTIVO_DEVOLUCION_LIST = "siccMotivoDevolucionList";

	public static final String SICC_CLIENTE_LIST = "siccClienteList";

	public static final String SICC_ZONA_LIST = "siccZonaList";

	public static final String SICC_TERRI_LIST = "siccTerriList";

	public static final String SICC_TERRITORIO_LIST = "siccTerritorioList";

	public static final String SICC_SUB_GERENCIA_LIST = "siccSubGerenciaList";

	public static final String SICC_CANAL_ROL_LIST = "siccCanalRolList";

	public static final String SICC_ESTADO_ROL_LIST = "siccEstadoRolList";

	public static final String SICC_PERIODO_INICIAL_LIST = "siccPeriodoInicialList";

	public static final String SICC_TIPO_CLASIFICACION_LIST = "siccTipoClasificacionList";

	public static final String SICC_CLASIFICACION_LIST = "siccClasificacionList";

	public static final String SICC_VINCULO_LIST = "siccVinculoList";

	public static final String SICC_CONCURSO_LIST = "siccConcursoList";

	public static final String SICC_NIVELES_CONCURSO_LIST = "siccNivelesConcursoList";

	public static final String SICC_PLANTILLA_LIST = "siccPlantillaList";

	public static final String SICC_VERSIONES_LIST = "siccVersionesList";

	public static final String SICC_TIPO_OFERTA_LIST = "siccTipoOfertaList";

	public static final String SICC_FORMA_PAGO_LIST = "siccFormaPagoList";
	
	public static final String STO_FORMA_PAGO_CLASIFICACION_LIST = "stoFormaPagoClasificacionList";

	public static final String PERIODO_CAMPANYA = "1";

	public static final int INTERFAZ_MYEBEL_CRONOGRAMA_FACTURACION = 1;

	public static final int INTERFAZ_MYEBEL_MATRIZ_CAMPANYA = 2;

	public static final int INTERFAZ_REUTILIZACION_CONSULTORAS = 3;

	public static final int INTERFAZ_RETAIL_MATRIZ_CAMPANYA = 4;

	public static final int INTERFAZ_MYEBEL_MOVIMIENTOS_CUENTA_CORRIENTE = 5;

	public static final int INTERFAZ_RETAIL_FACTURAS_VENTA_DIRECTA = 6;

	public static final int INTERFAZ_RETAIL_COMPLEMENTO_FACTURAS_VENTA_DIRECTA = 7;

	public static final int INTERFAZ_REUTILIZACION_DOCUMENTOS_ANULADOS = 8;

	public static final int INTERFAZ_OCR_REGIONES = 9;

	public static final int INTERFAZ_OCR_ZONAS = 10;

	public static final int INTERFAZ_SAM_INICIALIZACION_STOCKS = 11;

	public static final int INTERFAZ_SAB_RENTABILIDAD_ZONA = 12;

	public static final int INTERFAZ_BEL_FACTURAS_CABECERA = 13;

	public static final int INTERFAZ_BEL_FACTURAS_DETALLE = 14;

	public static final int INTERFAZ_REUTILIZACION_MATRIZ_CAMPANYA = 15;

	public static final int INTERFAZ_GIS_DIRECCION_CONSULTORAS = 16;

	public static final int INTERFAZ_GIS_REPORTE_DIRECCION_CONSULTORAS = 17;

	public static final int INTERFAZ_GEO_ZONA_TERRITORIO_CLIENTE = 18;

	public static final int INTERFAZ_REU_PERIODOS_FACTURACION = 19;

	public static final int INTERFAZ_BEL_DIRECCION_CLIENTES = 20;

	public static final int INTERFAZ_BEL_PORCENTAJES_REFERENCIA = 21;

	public static final int INTERFAZ_BEL_UNIDADES_ATENDIDAS = 22;

	public static final int INTERFAZ_BEL_UBICACIONES_GEOGRAFICAS = 23;

	public static final int INTERFAZ_SAB_INCENTIVOS_CONSULTORAS = 24;

	public static final int INTERFAZ_SAB_FUENTE_VENTAS_PREVISTA = 25;

	public static final int INTERFAZ_COM_LIBRETA_AHORROS = 26;

	public static final int INTERFAZ_COM_LIDERES_NUEVAS = 27;

	public static final int INTERFAZ_COM_RECOPLA = 28;

	public static final int INTERFAZ_COM_PAGO_LIDERES = 29;

	public static final int INTERFAZ_PRI_CARGA_PRIVILEGE = 30;

	public static final int INTERFAZ_PRI_DESCARGA_CONTROL_FACTURACION = 31;

	public static final int INTERFAZ_PRI_DESCARGA_SUBGERENCIAS = 32;

	public static final int INTERFAZ_PRI_DESCARGA_REGIONES = 33;

	public static final int INTERFAZ_PRI_DESCARGA_ZONAS = 34;

	public static final int INTERFAZ_PRI_DESCARGA_CONSULTORAS = 35;

	public static final int INTERFAZ_PRI_DESCARGA_PRODUCTOS = 36;

	public static final int INTERFAZ_PRI_DESCARGA_STICKERS = 37;

	public static final int INTERFAZ_PRI_DESCARGA_FICHAS_INSCRIPCION = 38;

	public static final int INTERFAZ_PRI_DESCARGA_TARJETAS = 39;

	public static final int INTERFAZ_PRI_DESCARGA_PREMIOS = 40;

	public static final int INTERFAZ_PRI_PERSONALIZACION_SOBRES = 41;

	public static final int INTERFAZ_MYE_PERCEPCIONES_VENTA_DIRECTA_CABECERA = 42;

	public static final int INTERFAZ_MYE_PERCEPCIONES_VENTA_DIRECTA_DETALLE = 44;

	public static final int INTERFAZ_MYE_CODIGO_AUTORIZACION_SUNAT = 45;

	public static final int INTERFAZ_OCR_CARGA_CONSOLIDADO_OCS_CABECERA = 46;

	public static final int INTERFAZ_OCR_CARGA_CONSOLIDADO_OCS_DETALLE = 47;

	public static final int INTERFAZ_OCR_DESCARGA_CONSOLIDADO_OCS_CABECERA = 48;

	public static final int INTERFAZ_OCR_DESCARGA_CONSOLIDADO_OCS_DETALLE = 49;

	public static final String FORMATO_ALFANUMERICO = "A";

	public static final String FORMATO_TOTAL = "T";

	public static final String CARPETA_SALIDA = "1";

	public static final String CARPETA_HISTORICO = "2";

	public static final String DELIMITADOR_EXTENSION_ARCHIVO = ".";

	public static final String DELIMITADOR_NOMBRE_ARCHIVO = "_";

	public final static String STRING_TABULADOR = "\\t";

	public final static String STRING_TABULADOR_XML = "\t";
	public final static String STRING_COMILLA = "\"";

	public final static String STRING_ENCABEZADO_XML = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>";

	public final static String STRING_IGUAL = "=";

	public final static char CHAR_TABULADOR = '\t';

	public final static char CHAR_NULO = '\0';

	public static final String ESTADO_PROCESO_INTERFAZ_OK = "0";

	public static final String ESTADO_PROCESO_INTERFAZ_ERROR_TEMP = "1";

	public static final String ESTADO_PROCESO_INTERFAZ_ERROR_REAL = "2";

	public static final String ESTADO_PROCESO_INTERFAZ_ERROR_HIST = "3";

	public static final String ESTADO_PROCESO_INTERFAZ_ERROR_BORRAR_TEMP = "4";

	public static final String ESTADO_PROCESO_INTERFAZ_ERROR_DESCONOCIDO = "5";

	public static final String ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO = "5";

	public static final String ESTADO_PROCESO_INTERFAZ_ERROR_BASE_DATOS = "6";

	public static final String INTERFAZSICC_ARCHIVO_ENVIADOGENERICO = "El(los) archivo(s) fue(ron) generado(s) y enviado(s) satisfactoriamente";

	public static final String INTERFAZSICC_ARCHIVO_RECIBIDOGENERICO = "El(los) archivo(s) fue(ron) recibido(s) y procesado(s) satisfactoriamente";

	public static final String SICC_TIPO_VINCULO_LIST = "ssiccTipoVinculoList";

	public static final String INTERFAZSICC_ARCHIVO_CEROREGISTROS = "No se encontraron registros";

	public static final String INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA = "No se encontraron datos en el archivo a tratar";

	public static final String INTERFAZSICC_ERROR_EJECUCION_PAQUETE = "Error en la ejecucion del paquete";

	public static final String INTERFAZSICC_ERROR_GENERAR_ARCHIVO = "Error al generar el archivo";

	public static final String INTERFAZSICC_ERROR_LOGUEAR_FTP = "No se pudo loguearse al FTP indicado";

	public static final String INTERFAZSICC_ERROR_CAMBIAR_DIRECTORIO_FTP = "No se pudo cambiar el Directorio Activo del FTP";

	public static final String INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP = "No se pudo enviar al FTP, el archivo generado";

	public static final String INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_FTP = "No se pudo obtener del FTP el archivo de entrada";

	public static final String INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_RED = "No se pudo enviar el archivo generado";

	public static final String INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_RED = "No se pudo obtener el Archivo de la Red";

	public static final String INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_ENTRADA = "No se pudo encontrar el Archivo de Entrada";

	public static final String INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_TEMPORAL = "No se pudo grabar el archivo de entrada en el directorio Temporal";

	public static final String INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_HISTO = "No se pudo grabar el Archivo en el directorio Historico";

	public static final String INTERFAZSICC_ERROR_BORRAR_ARCHIVO_FTP_REAL = "No se pudo borrar el Archivo en el directorio Entrada/Salida";

	public static final String INTERFAZSICC_ERROR_BORRAR_ARCHIVO_FTP_TEMPORAL = "No se pudo borrar el Archivo en el directorio Temporal";
	
	public static final String INTERFAZSICC_ERROR_CARGA_PREVIA = "El archivo fue cargado anteriormente";

	public static final String INTERFAZ_PER_LOTE_CONFIRMADO = "Lote se encuentra en Estado Confirmado";

	public static final String ERROR_COPY_PROPERTIES = "Error al transferir informacion al utilizar Sentencia CopyProperties ";

	public static final Long REGISTROS_ERRONEOS_ZERO = new Long(0);

	public static final String NUMERO_CERO = "0";

	public static final String NUMERO_UNO = "1";

	public static final String NUMERO_DOS = "2";

	public static final String NUMERO_SEIS = "6";

	public static final String ESTRUCTURA_ARCHIVO_LIST = "estructuraArchivoList";

	public static final String TIPO_DATO_LIST = "tipoDatoList";

	public static final String TIPO_DATO_NUMERICO = "N";

	public static final String STICKER_TOTAL = "totalStickers";

	public static final String PREMIO_TOTAL = "totalPremios";

	public static final String TIPO_DATO_CARACTER = "C";

	public static final String IZQUIERDA = "I";

	public static final String DERECHA = "D";

	public static final String ARCHIVO_SEPARADOR = "02";

	public static final String INCREMENTAR_PERIODO = "+";

	public static final String DECREMENTAR_PERIODO = "-";

	public static final int NUMERO_CAMPANYAS = 18;

	public static final String NORMA_INTERFAZ_LIST = "normaInterfazList";

	public static final String EXTENSION = ".txt";

	public static final String VALIDACION_TOTAL_OK = "B";

	public static final String VALIDACION_PARCIAL_OK = "E";

	public static final String VALIDACION_TOTAL_ERR = "R";

	public static final String NUMERO_CLIENTES_GENERAR = "numeroClientesGenerar";

	public static final String LONGITUD_CAMPO_CLIENTES = "longitudCampoClientes";

	public static final String STATUS_CLIENTE_TARJETA = "T";

	public static final String STATUS_CLIENTE_PREMIO = "P";

	public static final String STATUS_CLIENTE_FICHAS = "F";

	public static final String PRODUCTO_LIST = "productoList";

	public static final String DISPLAYTAG_PAGE_SELECTED = "displayTagPageSelected";

	public static final String ALL_INTERFACES_SISTEMA = "allInterfazSistema";

	public static final String ALL_INTERFACES_PAQUETE = "allInterfazPaquete";

	public static final String ALL_INTERFACES_PAQUETE_SELECCIONADAS = "allInterfazPaqueteSeleccionadas";

	public static final String CHECK_LISTA_INTERFACES = "checkAllListaInterfaces";

	public static final String INDICADOR_SELECCION_INTERFAZ = "indicadorSeleccionInterfaz";

	public static final String ALL_EXTENSION_ARCHIVO = "allExtensionArchivo";

	public static final String ALL_EXTENSION_LOGERROR = "allExtensionLogError";

	public static final String EXTENSION_ARCHIVO_NORMAL = "1";

	public static final String EXTENSION_ARCHIVO_LOG = "2";

	public static final String PERCEPCIONES_ORIGEN_IPM = "I";

	public static final String PERCEPCIONES_ORIGEN_MANUAL = "M";

	public static final String PERCEPCIONES_MOVIMIENTO_PROCESADO = "P";

	public static final String PERCEPCIONES_MOVIMIENTO_CONFIRMADO = "C";

	public static final String PERCEPCIONES_MOVIMIENTO_INCIDENCIA = "I";

	public static final String SICC_CUENTA_CORRIENTE_LIST = "siccCuentaCorrienteList";

	public static final String SICC_TIPO_TRANSACCION_LIST = "siccTipoTransaccionList";

	public static final String SICC_HORARIO_LIST = "siccHorarioList";

	public static final String MOVIMIENTOS_BANCARIOS_CABECERA_LIST = "movimientosBancariosCabeceraList";

	public static final String MANTENIMIENTO_FERIADO_LIST = "mantenimientoBASFeriadoList";

	public static final String MANTENIMIENTO_FERIADO_ZONA_LIST = "mantenimientoBASFeriadoZonaList";

	public static final String MOVIMIENTOS_BANCARIOS_DETALLE_LIST = "movimientosBancariosDetalleList";

	public static final String CONSOLIDADOS_OTROS_CANALES_LIST = "consolidadosOtrosCanalesList";

	public static final String CONSOLIDADO_PERCEPCIONES_ACUMULADO_LIST = "consolidadoPercepcionesAcumuladoList";

	public static final String RESULTADO_PROYECCION_FALTANTE_LIST = "resultadoPRYProyeccionFaltanteList";

	public static final String DETALLE_PROYECCION_FALTANTE_LIST = "detallePRYProyeccionFaltanteList";

	public static final String TIPO_VISTA_MES = "1";

	public static final String TIPO_VISTA_PERIODO = "2";

	public static final String BUSQUEDA_CONSULTORA_LIST = "busquedaConsultoraList";

	public static final String BUSQUEDA_PRODUCTO_LIST = "busquedaProductoList";

	public static final String BUSQUEDA_PRODUCTO_MATRIZ_LIST = "busquedaProductoMatrizList";

	public static final String CARGA_OCR_LIST = "cargaOCRList";

	public static final int INTERFAZ_OCR_DESCARGA_CONSOLIDAR_OCS_CABECERA = 998;

	public static final int INTERFAZ_OCR_DESCARGA_CONSOLIDAR_OCS_DETALLE = 999;

	public static final String SICC_BANCO_LIST = "siccBancoList";

	public static final String CODIGO_TIPO_INGRESO_M = "M";

	public static final String CODIGO_TIPO_INGRESO_LOTE = "D";

	public static final String CODIGO_TIPO_INGRESO_LINEA = "I";

	public static final String PRODUCT_RECLAM_LIST = "zonasRecList";

	public static final String CONSULTA_PRODUCT_RECLAM_LIST = "productosRecList";

	public static final String ZONA_MICA_LIST = "zonaEVIList";

	public static final String REGION_MICA_LIST = "regionEVIList";

	public static final String ZONA_MICA_LIST_2 = "zonaEVIList2";

	public static final String PRODUCT_PAGED_RECLAM_LIST = "productosPagedRecList";

	public static final String CODS_PRODUCT_RECLAM_LIST = "codsProductosRecList";

	public static final String PRODUCT_RECLAM_LISTxLOTE = "productosRecListxLote";

	public static final String PRODUCT_RECLAM_LISTxLOTExCONSOLIDADO = "productosRecListxLoteConsolidado";

	public static final String OPCION_BUSCAR = "B";

	public static final String OPCION_GENERAR = "G";

	public static final String SICC_OPERACIONES_LIST = "siccOperacionesList";

	public static final String CUP_PROGRAMAS_LIST = "programaCuponList";

	public static final String CUP_NIVEL_LIST = "nivelCuponList";

	public static final String SSE_COMBO_PROGRAMAS_LIST = "programaComboSessionExperteList";
	public static final String SSE_COMBO_ANHO_LIST = "anhoComboSessionExperteList";
	public static final String SSE_COMBO_TIPOCICLOS_LIST = "tipoCicloComboSessionExperteList";
	public static final String SSE_COMBO_CODIGOCICLOS_LIST = "codigoCicloComboSessionExperteList";
	public static final String SSE_PROGRAMAS_LIST = "mantenimientoSSEProgramaSessionExperteList";

	public static final String SSE_PRODUCTOS_DE_PROGRAMA_LIST = "mantenimientoSSEProductoDeProgramaSSEList";
	public static final String SSE_CICLOS_DE_PRODUCTO_LIST = "mantenimientoSSECiclosDeProductoList";
	public static final String SSE_CICLOS_DE_PROGRAMA_LIST = "mantenimientoSSECicloDeProgramaList";
	public static final String SSE_PROGRAMA_PRODUCTO = "mantenimientoSSEProductoDeProgramaSSEPrograma";
	public static final String SSE_CICLO_PROGRAMA = "mantenimientoSSECicloDePrograma";
	public static final String TIPO_CICLO_DEFAULT = "A";

	public static final String SSE_PERIODOS_PROGRAMA_LIST = "mantenimientoSSEPeriodosProgramaList";
	public static final String SSE_PRODUCTOS_PERIODO_LIST = "mantenimientoSSEProductoPeriodoList";
	public static final String ALL_PROD_NOT_SELECT = "sseProductosPeriodoNoSeleccionado";
	public static final String ALL_PROD_SELECT = "sseProductosPeriodoSeleccionado";
	public static final String SSE_PERIODO = "periodo";

	public static final int INTERFAZ_BEL_PERIODOS_FACTURACION = 19;

	public static final String SICC_TIPO_ORIGEN_DATOS_LIST = "siccTipoOrigenDatosList";

	public static final String SICC_STATUS_MOVIMIENTO_BANCARIO_LIST = "siccStatusMovimientoBancarioList";

	public static final String PATRON_FECHA_AAAAMMDD = "yyyyMMdd";

	/*
	 * Algunos valores para Tipo de Cierre
	 */
	public static final String CODIGO_TIPO_CIERRE_REGION = "R";

	public static final String CODIGO_TIPO_CIERRE_ZONA = "Z";

	public static final String CODIGO_TIPO_CIERRE_PERIODO = "P";

	public static final String SICC_MESES_LIST = "siccMesesList";

	/* Cargo Abono Directo Incobrable */
	public static final String TIPO_ORIGEN_DATOS_DIRECTO_INCOBRABLE = "07";

	public static final String TIPO_VISTA_BANCO = "1";

	public static final String TIPO_VISTA_CUENTA_CORRIENT = "2";

	public static final String SICC_GRUPO_PROCESO_LIST = "siccGrupoProcesoList";

	public static final String RECAUDO_BANCARIO_MANUAL = "01";

	public static final String RECAUDO_BANCARIO_AUTOMATICO = "02";

	public static final String RECAUDO_DESCUENTO_PERSONAL = "03";

	public static final String RECAUDO_ABONOS_WEB = "04";

	public static final String MOVIMIENTO_BANCARIO_PENDIENTE = "P";

	public static final String IMPRESION_NORMAL = "1";

	public static final String IMPRESION_SOLO_CABECERA = "2";

	public static final String SICC_COMPROBANTE_LIST = "siccComprobanteList";

	public static final String TIPO_VISTA_PAIS = "1";

	public static final String TIPO_VISTA_REGION = "2";

	public static final String TIPO_VISTA_ZONA = "3";

	public static final String SICC_SECCION_LIST = "siccSeccionList";

	public static final String SICC_NIVEL_RIESGO_LIST = "siccNivelRiesgoList";

	public static final String BUSQUEDA_CONCURSOS_MANTENER_DUPLA_CYZONE_LIST = "busquedaConcursosMantenerDuplaCyzoneList";

	public static final String SICC_PRESENTACION_LIST = "siccPresentacionList";

	public static final String SICC_PRESENTACION_REPORTE_COMISION_RECUPERACION_LIST = "siccComisionRecuperacionList";

	public static final String SICC_TIPO_COMISION_LIST = "siccTipoComisionRecuperacionList";

	public static final String SICC_PRESENTACION_ESTIMADOS_LIST = "siccPresentacionEstimadosList";

	public static final String SICC_TIPO_UNIDAD_LIST = "siccTipoUnidadnList";

	public static final String REC_SUBACCESO_DEFAULT = "000";

	public static final String OCR_TIPO_RECEPCION_DEFAULT = "EVI-P1";

	public static final String OCR_TIPO_RECEPCION_LIST = "ocrTipoRecepcionList";

	public static final String VALOR_TODAS = "TODAS";

	public static final String EXTENSION_TMP = "TMP";

	public static final String EXTENSION_ZIP = "ZIP";

	public static final String CUPONES_PROG_DESCUENTOS_LIST = "nuevasCuponesList";

	public static final String PED_CONTROL_FACTURACION_LIST = "pedidoControlFactList";

	public static final String CONSULTORAS_LIST = "consultorasList";

	public static final String CONSULTORAS_DETALLE_LIST = "consultorasDetalleList";

	public static final String EDU_CONTROL_FACTURACION_LIST = "pedidoEDUControlFactList";

	public static final String PEDIDOSXZONA_LIST = "pedidosxZonaList";

	public static final String PED_PRODUCTO_PEDIDO_MINIMO_LIST = "productoPedidoMinimoList";

	public static final String CUP_DESPACHO_PRODUCTOS_LIST = "despachoProductosList";

	public static final String PED_ACTUALIZA_DEUDA_LIST = "pedidosDeudaList";

	public static final String PED_ACTUALIZA_DEUDA_MASIVA_LIST = "pedidosDeudaMasivaList";

	public static final String PED_ANULA_PEDIDOS_FACT_LIST = "anulaPedidosFacturadosList";

	public static final String PED_DEPURA_BOLSA_LIST = "depuraPedidosList";

	public static final String CUPONES_TAB_EQUIVAL_LIST = "tablaEquivList";

	public static final String IND_ERRO_ADM_CARTERA_ACT = "1";

	public static final String IND_ERRO_ANUL = "1";

	public static final String IND_ERRO_OCS_BLOQ_ACT = "1";

	public static final String IND_ERRO_OCS_BLOQ_INACT = "0";

	public static final String IND_ERRO_ADM_CARTERA_INACT = "0";

	public static final String IND_ERRO_BLOQ_OCS_ACT = "1";

	public static final String IND_ERRO_BLOQ_OCS_INACT = "0";

	public static final String IND_NO_VALIDA = "0";

	public static final String IND_VALIDA_PERIODO = "1";

	public static final String IND_VALIDA_COD_VENTA = "2";

	public static final String PED_ACTUALIZA_BLOQUEO_MASIVO_LIST = "pedidosBloqueoMasivoList";

	public static final String ALL_OPERACIONES = "allOperaciones";

	public static final String ALL_OPERACIONES_HOMOLOGADAS = "allOperacionesHomologadas";

	public static final String REC_OPERACION_HOMOLOGA_LIST = "operacionesRECList";

	public static final String SICC_RESULTADO_CHEQUEO_LIST = "resultadoChequeoList";

	public static final String TIPO_SEPARADOR_LINEA_SISTEMA_OPERATIVO = "S";

	public static final String TIPO_SEPARADOR_LINEA_WINDOWS = "W";

	public static final String TIPO_SEPARADOR_LINEA_UNIX = "U";

	public static final String SEPARADOR_LINEA_SISTEMA_OPERATIVO = System
			.getProperty("line.separator");

	public static final String SEPARADOR_LINEA_WINDOWS = "\r\n";

	public static final String SEPARADOR_LINEA_UNIX = "\n";

	public static final String PROCESO_IMPRESION_LIST = "procesoImpresionList";

	public static final String PROCESO_IMPRESION_LASER = "LAS";

	public static final String PROCESO_IMPRESION_LASER_COLOR = "LAC";

	public static final String PROCESO_IMPRESION_MATRICIAL = "MAT";
	
	public static final String PROCESO_IMPRESION_RV3 = "RV3";

	public static final String PROCESO_IMPRESION_NOTA_CREDITO = "NOT";

	public static final String PROCESO_IMPRESION_NRO_PAQUETE_NORMAL = "1";

	public static final String PROCESO_IMPRESION_NRO_PAQUETE_BOLETA_DESPACHO = "2";

	public static final String PROCESO_IMPRESION_NRO_PAQUETE_BOLETA_RECOJO = "3";

	public static final String PROCESO_IMPRESION_NOMBRE_HOJA_PICADO = "PICKING";

	public static final String PROCESO_IMPRESION_NRO_ARCHIVOS_SALIDA = "1";

	public static final String ARCHIVO_ENVIADO_GENERICO = "El(los) archivo(s) fue(ron) generado(s) y enviado(s) satisfactoriamente.";

	public static final String PROCESO_BATCH_ENVIADO_GENERICO_ERRORES = "Alguno de los archivos se procesó con errores. Para más detalle ver CONSULTA ESTADO DE INTERFACES. Para ello presione sobre el Número de Lote";

	public static final String CODIGO_PROCESO_BATCH_EN_EJECUCION = "01";

	public static final String CODIGO_PROCESO_BATCH_EN_GENERACION_INTERFAZ = "02";

	public static final String CODIGO_PROCESO_BATCH_OK = "10";

	public static final String CODIGO_PROCESO_BATCH_ERROR = "99";

	public static final String CODIGO_PROCESO_BATCH_CALIFICACION_APTA_AUTOMATICA = "91";

	public static final String INDICADOR_EJECUCION_PROCESO_BATCH_SI = "S";

	public static final String INDICADOR_EJECUCION_PROCESO_BATCH_NO = "N";

	public static final String MANTENIMIENTO_GRUPOZONA_LIST = "mantenimientoGrupoZonaList";

	public static final String AGRUPADO_GRUPOZONA_LIST = "agrupadoGrupoZonaList";

	public static final String MANTENIMIENTO_GRUPOZONA_BUSCAR_ZONA_LIST = "mantenimientoGrupoZonaBuscarZonaList";

	public static final String MANTENIMIENTO_PORCENTAJE_FALTANTE_LIST = "mantenimientoPorcentajeFaltanteList";

	public static final String BUSQUEDA_ZONAPOPUP_LIST = "busquedaZonaPOUPList";

	public static final String BUSQUEDA_REGIONPOPUP_LIST = "busquedaRegionPOUPList";
	
	public static final String BUSQUEDA_SECCIONPOPUP_LIST = "busquedaSeccionPOUPList";

	public static final String PREFIJO_SISTEMA_DATAMART = "DAT-";

	public static final String MANTENIMIENTO_SUBPROCREC_LIST = "mantenimientoSubProcRecList";

	public static final String MANTENIMIENTO_PROCESOVEN_LIST = "mantenimientoProcesoVenList";

	public static final String MANTENIMIENTO_SUBPROCESOVEN_LIST = "mantenimientoSubProcesoVenList";

	public static final String BUSQUEDA_PROCESOPOPUP_LIST = "busquedaProcesoPOUPList";

	public static final String MANTENIMIENTO_PORCENTAJEIGV_LIST = "mantenimientoPorcentajeIgvList";

	public static final String MOVIMIENTO_BANCARIO_CONFIRMADO = "C";

	/**
	 * The request scope attribute that holds the ssicc user list
	 */
	public static final String SICC_USER_LIST = "ssiccUserList";

	public static final String OCR_ENVIA_OCS_LIST = "enviaOCSList";

	public static final String OCR_CONSULTORAS_INACTIVAS = "consultorasInactivasOCSList";

	public static final String ALL_CUP_NOT_SELECT = "ssiccCuponNoSeleccionado";

	public static final String ALL_CUP_SELECT = "ssiccCuponSeleccionado";

	public static final String CUP_PROGRAMA_PERIODO_LIST = "ssiccCuponProgramaPeriodoList";

	public static final String CUP_EQUIVALENCIA_MATRIZ_LIST = "siccMatrizEquivalenciaList";

	public static final String SICC_ANIO_LIST = "ssiccAnioList";

	public static final String INT_RETAIL_ENVIA_MATRIZ_PUNTAJES_CALYPSO = "RET-9";

	public static final String INT_RETAIL_ENVIA_MATRIZ = "RET-1";

	public static final String INT_RETAIL = "RET";

	public static final String TIPO_VISTA_COM_PERIODO = "PERIODO";

	public static final String TIPO_VISTA_COM_FECHAS = "FECHAS";

	public static final String TIPO_COMISION_GERENCIA = "G";

	public static final String TIPO_COMISION_LIDER = "L";

	public static final String SICC_CATALOGO_LIST = "ssiccCatalogoList";

	public static final String IND_MOVIMIENTO_DEVUELVE = "2";

	public static final String IND_MOVIMIENTO_ENVIA = "1";
	/**
	 * Constantes para el Mòdulo de Educaciòn
	 */
	public static final String EDU_EMPRESA_COMERCIALIZADORA_LIST = "empresaComercializadoraList";

	public static final String EDU_MANTENIMIENTO_EMPRESA_LIST = "mantenimientoEDUEmpresaList";

	public static final String EDU_ESTADO_REGISTRO_LIST = "estadoRegistroList";

	public static final String EDU_MANTENIMIENTO_CURSOS_LIST = "mantenimientoCursosCapacitacionList";

	public static final String EDU_REGIONES_CURSOS_LIST = "regionesCursosCapacitacionList";

	public static final String EDU_MANTENIMIENTO_PRODUCTO_CURSOS_LIST = "mantenimientoProductoCursoCapacitacionList";

	public static final String EDU_PARAMETROS_FRECUENCIA_LIST = "parametrosFrecuenciaList";

	public static final String EDU_PARAMETROS_SECUENCIA_LIST = "parametrosSecuenciaList";

	public static final String EDU_PARAMETROS_AMBITO_LIST = "parametrosAmbitoList";

	public static final String EDU_PARAMETROS_NIVEL_VENTAS_LIST = "parametrosNivelVentasList";

	public static final String EDU_PARAMETROS_TIPO_DESPACHO_LIST = "parametrosTipoDespachoList";

	public static final String EDU_PARAMETROS_TIPO_COSTO_CURSO_LIST = "parametrosTipoCostoCursoList";

	public static final String EDU_PARAMETROS_TIPO_INDICADOR_DESPACHO_LIST = "parametrosTipoIndicadorDespachoList";

	public static final String EDU_PARAMETROS_PROGRAMA_LBEL = "01";

	public static final String EDU_PARAMETROS_TIPO_DESPACHO_COSTO = "03";

	public static final String EDU_PARAMETROS_TIPO_DESPACHO_INVITACION = "01";

	public static final String EDU_PARAMETROS_TIPO_DESPACHO_REGALO = "01";

	public static final String EDU_PARAMETROS_TIPO_COSTO_SIN_COSTO = "01";

	public static final String EDU_PARAMETROS_TIPO_COSTO_CON_COSTO = "02";

	public static final String EDU_PARAMETROS_EMPRESA_DEFAULT = "CO01";

	public static final String EDU_PARAMETROS_REGION_LIST = "eduRegionList";

	public static final String EDU_PARAMETROS_ZONA_LIST = "eduZonaList";

	public static final String EDU_PARAMETROS_CURSO_REGION_LIST = "mantenimientoCursoRegionList";

	public static final String EDU_PARAMETROS_CURSO_ZONA_LIST = "mantenimientoCursoZonaList";

	public static final String EDU_TAB_CURSO_INVITACION = "invitacionTab-tab";

	public static final String EDU_TAB_CURSO_COSTO = "costoTab-tab";

	public static final String EDU_TAB_CURSO_DESPACHO = "despacharTab-tab";

	/**
	 * Constantes para el Mòdulo de Educaciòn - Proceso Aptas Demanda
	 */
	public static final String EDU_PARAMETROS_AMBITO_DICTADO_CONSULTORA = "05";

	public static final String EDU_PARAMETROS_AMBITO_DICTADO_REGION = "02";

	public static final String EDU_PARAMETROS_AMBITO_DICTADO_ZONA = "03";

	public static final String EDU_MANTENIMIENTO_PROCESO_APTA_DEMANDA_LIST = "procesoAptaDemandaList";

	public static final String EDU_MANTENIMIENTO_PROCESO_PREVIO_APTA_DEMANDA_LIST = "procesoEDUCalificacionAptasDemandaEjecutarPrevioList";

	public static final String EDU_MANTENIMIENTO_PROCESO_APTA_AUTOMATICA_LIST = "mantenimientoProcesoAptasAutomaticaList";

	public static final String EDU_MANTENIMIENTO_CONSULTORA_APTA_DEMANDA_LIST = "mantenimientoConsultoraAptasDemandaList";

	public static final String EDU_MANTENIMIENTO_CAMPANHA_APTA_DEMANDA_LIST = "mantenimientoCampanhaAptasDemandaList";

	public static final String EDU_MANTENIMIENTO_APTA_DEMANDA_CONSULTORA_TAB = "clienteTab";

	public static final String EDU_MANTENIMIENTO_APTA_DEMANDA_REGION_TAB = "regionTab";

	public static final String EDU_MANTENIMIENTO_APTA_DEMANDA_ZONA_TAB = "zonaTab";

	public static final String FRECUENCIA_A_DEMANDA = "03";

	public static final String FRECUENCIA_N_CAMPANNA = "02";

	public static final String EDU_MANTENIMIENTO_APTA_DEMANDA_CAMPANHA_TAB = "campanhaTab";

	public static final String EDU_MANTENIMIENTO_REGION_APTA_DEMANDA_LIST = "mantenimientoRegionAptasDemandaList";

	public static final String EDU_MANTENIMIENTO_ZONA_APTA_DEMANDA_LIST = "mantenimientoZonaAptasDemandaList";

	public static final String EDU_MANTENIMIENTO_CAMPANHAS_APTA_DEMANDA_LIST = "mantenimientoCampanhasAptasDemandaList";

	public static final String EDU_MANTENIMIENTO_CAMPANHAS_APTA_DEMANDA_FORM = "mantenimientoCampanhasAptasDemandaForm";

	public static final String EDU_TIPO_CALIFICACION_MANUAL = "M";

	public static final String EDU_TIPO_CALIFICACION_AUTOMATICA = "A";

	/**
	 * Constantes para el Mòdulo de Educaciòn - Maestro de Instructoras
	 */
	public static final String EDU_MANTENIMIENTO_INSTRUCTORAS_LIST = "mantenimientoMaestroInstructoraList";
	/**
	 * Constantes para el Mòdulo de Educaciòn - Parametros de Clasificacion
	 */
	public static final String EDU_MANTENIMIENTO_PARAMETROS_CLASIFICACION_LIST = "mantenimientoParametroClasificacionList";

	public static final String EDU_PARAMETROS_TIPO_ASISTENCIA_LIST = "parametrosTipoAsistenciaList";

	public static final String EDU_PARAMETROS_ESTADO_NIVEL_LIST = "parametrosEstadoNivelList";

	public static final String EDU_PARAMETROS_IND_SEL_CAPAC_LIST = "parametrosIndicadorSeleccionList";

	public static final String EDU_TIPO_DESPACHO_CON_COBRO = "03";

	public static final String EDU_CURSO_CON_COSTO = "S";

	public static final String EDU_CURSO_SIN_COSTO = "N";

	public static final String EDU_CURSO_SEL_CAPA_GRUPAL = "G";

	public static final String EDU_CURSO_SEL_CAPA_UNICO = "U";

	public static final String EDU_CURSO_SEL_CAPA_ESPECIFICO = "E";

	/**
	 * Valor usado en los estados para indicar que el registro esta activo o
	 * inactivo
	 */
	public static final String ESTADO_ENTIDAD_ACTIVO = "A";
	public static final String ESTADO_ENTIDAD_INACTIVO = "I";
	public static final String ESTADO_ENTIDAD_RETIRADA = "R";
	/**
	 * Constantes para el Mòdulo de Educaciòn - Registro de Asistencia
	 */
	public static final String EDU_MANTENIMIENTO_ASISTENCIA_LIST = "procesoRegistroAsistenciaList";
	public static final String EDU_MANTENIMIENTO_PLANILLA_LIST = "procesoRegistroAsistenciaPlanillaList";
	/**
	 * Valor usado en los estados para indicar que el registro esta activo o
	 * inactivo
	 */
	public static final String ESTADO_CAPACITADA_PENDIENTE = "PC";
	public static final String ESTADO_CAPACITADA_EXONERADA = "EX";
	public static final String ESTADO_CAPACITADA_CAPACITADA = "CP";
	public static final String ESTADO_CAPACITADA_PROGRAMADA = "PR";
	public static final String ESTADO_PENDIENTE_FACTURACION = "PF";
	public static final String ESTADO_PENDIENTE_PROGRAMADA = "PP";
	public static final String ESTADO_POR_CONFIRMAR = "PO";
	public static final String ESTADO_CURSO_VIGENTE = "V";
	public static final String ESTADO_CURSO_CERRADA = "C";
	public static final String SITUACION_PLANILLA_ACTIVA = "A";
	public static final String SITUACION_PLANILLA_PROCESADA = "P";
	public static final String INDICADOR_ASISTENCIA_SI = "S";
	public static final String INDICADOR_ASISTENCIA_NO = "N";
	public static final String INDICADOR_EVALUACION_CURSO_SI = "S";
	public static final String INDICADOR_EVALUACION_CURSO_NO = "N";
	public static final String EDU_CURSO_DICTADO_LIST = "eduCursoDictadoList";
	public static final String EDU_CURSO_PLANILLA_DICTADO_LIST = "eduCursoPlanillaDictadoList";
	public static final String EDU_CURSO_PLANILLA_INSTRUCTORA_LIST = "eduCursoPlanillaInstructoraList";
	public static final String EDU_CLIENTES_LIST = "busquedaEDUClientesSearchFormList";
	public static final String EDU_USUARIO_LIST = "busquedaEDUUsuarioSearchFormList";
	public static final String EDU_PARAMETRO_TIPO_ASIST_CURSO_PRESENCIAL = "P";
	public static final String EDU_PARAMETRO_TIPO_ASIST_CURSO_EXONERADA = "E";
	public static final String EDU_PARAMETRO_TIPO_ASIST_CURSO_VIRTUAL = "V";
	public static final String EDU_PARAMETRO_TIPO_ASITT_CURSO_REGULAR = "R";
	public static final String EDU_PARAMETRO_TIPO_ASITT_CURSO_EXTEMPORANEA = "E";
	public static final String EDU_PARAMETRO_TIPO_ASITT_CURSO_TODAS = "T";
	public static final String EDU_FLAG_AJAX_COMBO_DESCRIPCION_TODOS = "S";
	public static final String EDU_FLAG_AJAX_COMBO_CODIGO_NOTODOS = "N";
	public static final String EDU_FLAG_AJAX_COMBO_CODIGO_SEPARATED = "P";
	public static final String EDU_MANT_ASISTENCIA_TAB_LUGAR = "lugarTab";
	public static final String EDU_MANT_ASISTENCIA_TAB_PLANILLA = "planillaTab";
	/**
	 * Constantes para el Mòdulo de Educaciòn - Mantenimiento de Region
	 */
	public static final String EDU_REGION_LIST = "mantenimientoRegionList";
	public static final String EDU_ZONA_LIST = "mantenimientoZonaList";
	public static final String EDU_INSTRUCTORA_LIST = "busquedaEDUInstructoraSearchFormList";

	public static final String EDU_TABLA_EQUIVALENCIA_LIST = "equivalenciaServiciosList";
	/**
	 * Constantes creados para el Módulo de Educación - Web Services
	 * */
	public static final String EDU_WEBSERVICE_RESULTADO_OK = "0";
	public static final String EDU_WEBSERVICE_RESULTADO_ERROR = "-1";

	public static final String LET_WEBSERVICE_RESULTADO_OK = "0";
	public static final String LET_WEBSERVICE_RESULTADO_ERROR = "-1";
	
	public static final String TIPO_INVITACION_VIA_GERENTE = "GZ";
	public static final String TIPO_INVITACION_VIA_PAQUETE = "PD";
	public static final String TIPO_INVITACION_AMBOS = "GP";

	public static final String ACTIVO = "1";

	public static final String MENU_NOMBRE_CODIGO_MENU = "codigoMenu";

	public static final String MENU_INFO_MENU = "informacionMenu";

	/**
	 * Constantes para el Mòdulo de Educaciòn - Paràmetro Curso Capacitacion
	 */
	public static final String EDU_MANTENIMIENTO_PARAMETRO_CURSO_LIST = "mantenimientoParametroCursoList";

	public static final String EDU_MANTENIMIENTO_MENSAJE_LIST = "mantenimientoEDUMensajeList";

	public static final String EDU_NUMERO_LOTE_DIARIO = "D";
	public static final String EDU_NUMERO_LOTE_REGION = "R";
	public static final String IND_BASE_CALCULO = "4";

	public static final String CODIGO_SUBTIPO_CLIENTE_DEFAULT_M = "02-04";

	public static final String PERIODO_SIGUIENTE = "1";

	public static final String PERIODO_ANTERIOR = "-1";

	public static final String EDU_TIPO_DESPACHO_INVITACION = "INV";

	public static final String EDU_OBJETIVO_ASISTENCIA_PAIS = "P";

	public static final String EDU_OBJETIVO_ASISTENCIA_REGION = "R";

	public static final String EDU_OBJETIVO_ASISTENCIA_ZONA = "Z";

	public static final String EDU_MANTENIMIENTO_OBJETIVO_ASISTENCIA_LIST = "mantenimientoObjetivoAsistenciaList";

	/**
	 * Constantes para la opción de Consulta
	 */
	public static final String EDU_CURSO_LIST = "listaEDUCursoCapacitacion";
	public static final String EDU_CURSO_CONSULTA_LIST = "consultaEDUCursoCapacitacionList";
	public static final String EDU_CURSO_CONSULTA_EJECUTIVA_LIST = "consultaEDUCursoCapacitacionEjecutivaList";
	public static final String EDU_CURSO_SITUACION_LIST = "consultaSituacionCurso";
	public static final String EDU_CURSO_SITUACION_APTA = "PC";
	public static final String EDU_CURSO_SITUACION_PROGRAMADA = "PR";
	public static final String EDU_CURSO_SITUACION_CAPACITADA = "CA";
	public static final String EDU_CURSO_SITUACION_NO_APTA = "NA";
	public static final String EDU_CURSO_PLANILLA_PROGRAMADA_LIST = "eduCursoPlanillaProgramadaList";
	public static final String EDU_LIMPIAR_AJAX = "0";
	public static final String EDU_NO_LIMPIAR_AJAX = "1";

	public static final String EDU_TIPO_ENVIO_NORMAL = "N";
	public static final String EDU_TIPO_ENVIO_REENVIO = "R";
	public static final String EDU_TIPO_ENVIO_REPROCESO = "RE";

	public static final String EDU_TIPO_PROCESO_NORMAL = "N";
	public static final String EDU_TIPO_PROCESO_REPROCESO = "R";
	// SB
	public static final String EDU_TIPO_PROCESO_REENVIO = "RE";

	public static final String EDU_TIPO_PROCESO_LIST = "eduTipoProceso";

	public static final String EDU_TIPO_ENVIO_INTERFACE_DATAMART_LIST = "eduTipoEnvioInterfaceDatamart";

	/**
	 * Constantes para los WebServices
	 */
	public static final String EDU_IDIOMA_DEFAULT_ES = "es";
	public static final String EDU_IDIOMA_INGLES = "us";

	/**
	 * Constantes para el Mòdulo de Educaciòn - Registro de Calificaciòn a
	 * Ejecutiva
	 */
	public static final String EDU_MANTENIMIENTO_CURSO_CALIFICACION_EJECUTIVA_LIST = "procesoCursoCalificacionEjecutivaList";
	public static final String EDU_MANTENIMIENTO_CALIFICACION_EJECUTIVA_LIST = "procesoRegistroCalificacionEjecutivaList";
	/**
	 * Constantes para el Mòdulo de Educaciòn - Paràmetros del Curso.
	 */
	public static final String EDU_PARAM_CURSO_NIVEL_ADM_REGION = "01";
	public static final String EDU_PARAM_CURSO_NIVEL_ADM_ZONA = "02";
	public static final String EDU_PARAM_CURSO_NIVEL_ADM_SECCION = "03";
	public static final String EDU_PARAM_CURSO_NIVEL_ADM_TERRITORIO = "04";
	public static final String EDU_PARAM_CURSO_NIVEL_ADM = "parametroCursoNivelAdministrativo";
	public static final String EDU_PARAM_CURSO_NO_GENERA_PLANILLA = "0";
	public static final String EDU_PARAM_CURSO_NO_GENERA_PDF = "0";
	public static final String EDU_PARAM_LIST_INDICADOR_NOMBRE = "parametroListIndicadorNombre";
	public static final String EDU_PARAM_LIST_INDICADOR_PLANILLA = "parametroListIndicadorPlanilla";

	public static final String EDU_PARAM_PROC_REGION = "01";
	public static final String EDU_PARAM_PROC_CURSO = "02";

	public static final String NO_CODIGO_INTERFAZ = "SIN_CODIGO";

	public static final String PARM_TIPO_CLASIFICACION = "TIP_CLA";

	public static final String PARM_SUB_TIPO_CLIENTE = "SUB_TIP_CLI";

	public static final String PARM_CLASIFICACION = "CLA";

	public static final String OID_SUBTIPO_CLIENTE_DEFAULT = "1";

	public static final String OID_TIPO_CLIENTE_DEFAULT = "2";

	public static final String OID_CLASIFICACION_DEFAULT = "2020";

	public static final String OID_TIPO_CLASIFICACION_DUPLA = "2012";

	public static final String SICC_TIPO_MOVIMIENTO_LIST = "ssiccTipoMovimientoList";

	public static final String SICC_TIPO_PERIODO_LIST = "ssiccTipoPeriodoList";

	public static final String TIPO_TOTAL_PERIODO = "P";

	public static final String TIPO_TOTAL_REGION = "R";

	public static final String TIPO_TOTAL_ZONA = "Z";

	public static final String EDU_TABLA_CLASIFI_BENEFICIO = "clasificacionBeneficioList";
	public static final String EDU_REGISTRO_CLASIFICACION_LIST = "registroClasificacionList";

	public static final String ACCESO_OPCION_PAGED_LIST = "accesoOpcionPagedList";
	public static final String OPCION_LIST = "opcionList";

	public static final String ACCESO_LIST = "accesoList";

	public static final String SICC_REPORTE_LOG_LIST = "reporteLogList";

	public static final String DAT_MANTENIMIENTO_PARAMETROSCDR_LIST = "mantenimientoDATParametrosCDRList";

	public static final String SICC_TIPO_SOLICITUD_PAIS_LIST = "siccTipoSolicitudPaisList";

	// opcion menu rol

	public static final String OPCION_MENU_ROL_BUSCAR = "00001";
	public static final String OPCION_MENU_ROL_LIMPIAR = "00002";
	public static final String OPCION_MENU_ROL_INSERTAR = "00003";
	public static final String OPCION_MENU_ROL_MODIFICAR = "00004";
	public static final String OPCION_MENU_ROL_ELIMINAR = "00005";
	public static final String OPCION_MENU_ROL_CONSULTAR = "00006";
	public static final String OPCION_MENU_ROL_REGISTRAR = "00007";
	public static final String OPCION_MENU_ROL_ABRIR = "00008";
	public static final String OPCION_MENU_ROL_CERRAR = "00009";
	public static final String OPCION_MENU_ROL_ACEPTAR = "00010";
	public static final String OPCION_MENU_ROL_CANCELAR = "00011";
	public static final String OPCION_MENU_ROL_ACTIVAR = "00012";
	public static final String OPCION_MENU_ROL_DESACTIVAR = "00013";
	public static final String OPCION_MENU_ROL_BLOQUEAR = "00014";
	public static final String OPCION_MENU_ROL_DESBLOQUEAR = "00015";
	public static final String OPCION_MENU_ROL_CARGAR = "00016";
	public static final String OPCION_MENU_ROL_DESCARGAR = "00017";
	public static final String OPCION_MENU_ROL_CONFIRMAR = "00018";
	public static final String OPCION_MENU_ROL_COPIAR = "00019";
	public static final String OPCION_MENU_ROL_EJECUTAR = "00020";
	public static final String OPCION_MENU_ROL_GENERAR = "00021";
	public static final String OPCION_MENU_ROL_PROCESAR = "00022";
	public static final String OPCION_MENU_ROL_GUARDAR = "00023";
	public static final String OPCION_MENU_ROL_IMPRIMIR = "00024";
	public static final String OPCION_MENU_ROL_REPLICAR = "00025";
	public static final String OPCION_MENU_ROL_REGRESAR = "00026";
	public static final String OPCION_MENU_ROL_REFRESCAR = "00027";
	public static final String OPCION_MENU_ROL_VISTA_PREVIA = "00028";
	public static final String OPCION_MENU_ROL_CERRAR_CAMPANA = "00029";
	public static final String OPCION_MENU_ROL_ACTUALIZAR_NUMERO_LOTE = "00030";
	public static final String OPCION_MENU_ROL_ACTUALIZAR_INDICADORES_FACTURACION = "00031";
	public static final String OPCION_MENU_ROL_EDITAR_PARAMETROS_FDV = "00036";
	public static final String OPCION_MENU_ROL_SECUENCIAR_TERRITORIO = "00037";

	public static final String INDICADOR_OBJ_PEDIDO_MODIFICADO = "M";

	public static final String INDICADOR_OBJ_PEDIDO_NOGENERADO = "N";

	public static final String INDICADOR_OBJ_PEDIDO_GENERADO = "G";

	public static final String PRODUCTOS_CON_DESCUENTO = "1";

	public static final String PRODUCTOS_SIN_DESCUENTO = "0";

	public static final String PRODUCTOS_APOY_VTA_PROMO = "2";
	public static final String CODIGO_VARIABLE_VENTA_PORCENTAJE_ACTIVIDA = "4";

	public static final String VAL_OBSE_PROGRAMA_LIDERES = "PROGRAMA-LIDERES";
	public static final String SICC_PERIODO_PROCESO = "periodoProceso";
	public static final String IND_ACTI_UNO = "1";

	public static final String TIPO_INCREMENTO_DEFAULT = "P";

	public static final int FLAG_DEFAULT = 1;

	public static final String IVR_ENVIAR_COMPLETO = "T";

	public static final String IVR_ENVIAR_NOVEDAD = "N";

	public static final String IVR_SISTEMA_COMERCIAL = "C";

	public static final String INDICADOR_REPORTE_EDU_GENERACION_PLANILLA_REGION = "01";

	public static final String INDICADOR_REPORTE_EDU_GENERACION_PLANILLA_ZONA = "02";

	public static final String INDICADOR_REPORTE_EDU_GENERACION_PLANILLA_SIN_GENERAR = "0";

	public static final String REPORTE_EDU_GENERACION_PLANILLA_REGION = "R";

	public static final String REPORTE_EDU_GENERACION_PLANILLA_ZONA = "Z";

	public static final String EDU_GENERACION_PLANILLA_NIVEL_ENVIO_NO = "0";

	public static final String EDU_GENERACION_PLANILLA_NIVEL_ENVIO_REGION = "01";

	public static final String EDU_GENERACION_PLANILLA_NIVEL_ENVIO_ZONA = "02";

	public static final String EDU_MENSAJE_TIPO_MENSAJE_GENERAL = "1";

	public static final String EDU_MENSAJE_TIPO_MENSAJE_ESPECIFICO = "2";

	public static final String EDU_ESTADO_CAPACITACION_LIST = "eduEstadoCapacitacionList";

	public static final String EDU_OPCION_MENSAJE_LIST = "eduOpcionMensajeList";

	public static final String EDU_MENSAJE_PREFIJO_GENERAL = "MG";

	public static final String EDU_MENSAJE_PREFIJO_ESPECIFICO = "ME";

	public static final String EDU_PARAMETRO_MENSAJE_LIST = "mantenimientoEDUMensajeList";

	// sb
	public static final String EDU_MANTENIMIENTO_MULTI_ENTIDAD_LIST = "mantenimientoEDUMultiEntidadList";

	public static final String EDU_MANTENIMIENTO_ENTIDAD_LIST = "mantenimientoMultiEntidadList";

	public static final String EDU_MANTENIMIENTO_ENTIDAD_TIPO_LIST = "mantenimientoTipoEntidadList";

	public static final String EDU_NOMBRE_ENTIDAD = "eduNombreEntidad";

	public static final String EDU_CONEXION_EXTERNA_DESACTIVADO = "0";

	public static final String EDU_CONEXION_EXTERNA_ACTIVADO = "1";

	public static final String EDU_PROCESO_REGULARIZACION_ASISTENCIA_LIST = "procesoRegularizacionAsistenciaList";

	public static final String EDU_PROCESO_REGULARIZACION_APTAS_LIST = "procesoAptasList";

	public static final String EDU_INDICADOR_COSTO_CURSO_SI = "S";

	public static final String EDU_INDICADOR_CURSO_FACTURADO = "F";

	public static final String EDU_INDICADOR_CURSO_NO_FACTURADO = "N";

	public static final String EDU_LOCAL_DICTADO_LIST = "eduLocalDictadoList";

	public static final String EDU_SALA_DICTADO_LIST = "eduSalaDictadoList";

	public static final String ESTADO_MENSAJE_INACTIVO = "I";
	public static final String ESTADO_MENSAJE_ACTIVO = "A";
	public static final String STATUS_FROM_GERENTE_ZONA = "GZ";

	public static final String EDU_CRONOGRAMA_DICTADO_LIST = "mantenimientoEDUCronogramaDictadoList";

	public static final String EDU_ESTADO_CAPACITACION_INDICADOR_REAL = "1";
	public static final String EDU_ESTADO_CAPACITACION_INDICADOR_TEMPORAL = "2";

	public static final String EDU_FRECUANCIA_TIPO_CALI_AUTOMATICA = "A";
	public static final String EDU_FRECUANCIA_TIPO_CALI_MANUAL = "M";

	public static final String EDU_TABLA_TIPO_INDIC_DESPA = "EDU_TIPO_INDIC_DESPA";
	public static final String EDU_TABLA_EDU_ESTAD_CAPAC = "EDU_ESTAD_CAPAC";
	public static final String EDU_TABLA_FRECU_CALIF = "EDU_FRECU_CALIF";

	public static final String EDU_ZONA_DICTADO_LIST = "eduZonaDictadoList";

	public static final String EDU_LOCAL_LIST = "mantenimientoLocalList";
	public static final String EDU_SALA_LIST = "mantenimientoSalaList";

	public static final String EDU_TIPO_EJECUTIVA_LIST = "tipoEjecutivaList";
	public static final String EDU_TIPO_EJECUTIVA_EDUCACION = "EE";
	public static final String EDU_TIPO_JEFA_EDUCACION = "JE";

	public static final String[] EDU_HORA_LIST = { "01", "02", "03", "04",
			"05", "06", "07", "08", "09", "10", "11", "12" };

	public static final String[] EDU_MINUTO_LIST = { "00", "15", "30", "45" };

	public static final String[] EDU_TIEMPO_AM_PM_LIST = { "AM", "PM" };

	public static final String EDU_HORA_DICTADO_LIST = "eduHoraDictadoList";

	public static final String EDU_MINUTO_DICTADO_LIST = "eduMinutoDictadoList";

	public static final String EDU_TIEMPO_AM_PM_DICTADO_LIST = "eduTiempoAMPMDictadoList";

	public static final int HORA_LIMITE_AM_PM = 12;

	public static final String EDU_AUDITORIA_LIST = "auditoriaList";

	public static final String EDU_DETALLE_AUDITORIA_LIST = "detalleAuditoriaList";

	public static final String EDU_INDICADOR_NOMBRE_COMPLETO = "S";

	public static final String EDU_INDICADOR_BLOQUEO_SI = "1";

	public static final String EDU_INDICADOR_RECODIFICACION_SI = "1";

	public static final String EDU_INDICADOR_STATUS_CONSULTORA_SI = "1";

	public static final String EDU_INDICADOR_ENVIO_CRONOGRAMA = "1";

	public static final String EDU_INDICADOR_EQUIVALENCIA_CLASIFICACION_SI = "1";

	public static final String EDU_INDICADOR_CONEXION_EXTERNA_SI = "1";

	public static final String EDU_CONSULTA_CRONOGRAMA_REGIONES_LIST = "eduCronogramaRegionesList";

	public static final String EDU_CONSULTA_CRONOGRAMA_ZONA_LIST = "eduCronogramaZonaList";

	public static final String EDU_MANTENIMIENTO_PARAMETRO_REPORTE_LIST = "mantenimientoEDUParametroReporteList";

	public static final String EDU_ERROR_ABRIR_TABLA_FOX = "Error al tratar de abrir Tabla en el Sistema Comercial: Dicha Tabla se encuentra abierta en el Sistema Comercial";

	public static final int EDU_NRO_ERROR_ABRIR_TABLA_FOX = 393216;

	public static final String EDU_PARAM_REPORTE_NO_ENVIAR_CC = "0";

	public static final String EDU_PARAM_REPORTE_NIVEL_ENVIO_REGION = "01";

	public static final String EDU_PARAM_REPORTE_NIVEL_ENVIO_CC_ZONA = "02";

	public static final String EDU_PARAM_REPORTE_NIVEL_ENVIO_CC = "parametroReporteNivelEnvioCC";

	// USER_CRONO
	public static final String EDU_USER_CRONO = "ADMIN";
	public static final String USUARIO_ADMIN = "ADMIN";

	public static final int PAGINACION_SIZE_BOLETA_RECOJO = 10;

	public static final String PAIS_ECL = "ECL";

	public static final String TIPO_GRAFICO_PIE = "PIE";

	public static final String TIPO_GRAFICO_PIE3D = "PIE3D";

	public static final String TIPO_GRAFICO_BAR3D_V = "BAR3D_V";

	public static final String TIPO_GRAFICO_BAR3D_H = "BAR3D_H";

	public static final String GRAFICO_LIST = "listaGrafico";

	public static final String EDU_INDICADOR_ENVIO_PROGRAMADAS_SI = "1";

	public static final String EDU_INDICADOR_ENVIO_RESUMEN_SI = "1";

	public static final String EDU_INDICADOR_EQUIVALENCIA_MENSAJE_SI = "1";

	public static final String INDICADOR_REPORTE_EDU_GENERACION_RESUMEN_PLANILLA_SIN_GENERAR = "0";

	public static final String EDU_TIPO_CLASIFICACION_EQUIVALENCIA_LIST = "eduTipoClasificacionEquivList";

	public static final String EDU_CODIGO_CLASIFICACION_EQUIVALENCIA_LIST = "eduCodigoClasificacionEquivList";

	public static final String INDICADOR_LIST = "indicadorList";

	public static final String ESTADO_INDICADOR_ENVIADO = "Enviada";

	public static final String ESTADO_INDICADOR_NO_ENVIADO = "No enviada";

	public static final String INDICADOR_ENVIADO = "1";

	public static final String INDICADOR_NO_ENVIADO = "0";

	public static final String OPCION_TODOS = "Todos";

	public static final String CODIGO_TIPO_ESTRATEGIA_INDIVIDUAL = "1";

	public static final String EDU_SITUACION_TODOS = "00";

	public static final String EDU_REGION_TODOS = "Todos";

	public static final String EDU_INDICADOR_DESPACHO_CLASIFICACION_SI = "1";

	public static final String EDU_CAMPANHA_TODAS = "T";

	public static final String EDU_CAMPANHA_ACTIVA = "A";

	public static final String EDU_ENVIO_TODAS = "T";

	public static final String EDU_ENVIO_NUEVAS = "N";

	// Para el manejo de Unidades Geograficas
	public static final String ZON_CAMPO_LIST = "zonCampoList";

	public static final String EXTENSION_ARCHIVO_EXCEL = "XLS";

	public static final String EXTENSION_ARCHIVO_EXCEL_2007 = "XLSX";
	
	public static final String EXTENSION_ARCHIVO_DBF = "DBF";

	public static final String CODIGO_SISTEMA_EDU = "EDU";

	public static final String CODIGO_SISTEMA_MYE = "MYE";

	// Para el manejo de Procesos de Comisiones
	public static final String COM_TIPO_COMISIONISTA_LIST = "comTipoComisionistaList";
	
	public static final String COM_BASE_COMISION_LIST = "comBaseComisionList";

	public static final String COM_TRAMO_LIST = "comTramoList";

	public static final String CODIGO_TIPO_COMISIONISTA_DEFAULT = "03";

	public static final String SICC_PERIOODO_CORPORATIVO_LIST = "siccPeriodoCorporativoList";

	public static final String TIPO_PERIOODO_CORPORATIVO_CAMPANIA = "CM";

	public static final String TIPO_VISTA_LOTE = "3";

	public static final String TIPO_RECAUDO_BANCARIO_TODOS = "T";

	public static final String TIPO_RECAUDO_BANCARIO_MANUAL = "M";

	public static final String TIPO_RECAUDO_BANCARIO_AUTOMATICO = "A";

	public static final String RECAUDO_BANCARIO_PROCESO = "TES001";

	public static final String RECAUDO_BANCARIO_AUTOMATICO_SUBPROCESO = "1";

	public static final String RECAUDO_BANCARIO_MANUAL_SUBPROCESO = "2";

	public static final String RECAUDO_BANCARIO_WEB_SUBPROCESO = "3";

	public static final String IND_OPERACION_ANULADO = "1";

	public static final String LISTA_CAMPANHAS = "listaCampanhas";

	// Modulo de Cobranzas
	public static final String SICC_ETAPA_DEUDA_LIST = "siccEtapaDeudaList";

	public static final String SICC_COBRADORES_LIST = "siccCobradoresList";

	public static final String COB_ETAPA_DEUDA_LIST = "cobEtapaDeudaList";

	public static final String COB_COBRADORES_LIST = "cobCobradoresList";

	public static final String COB_SUPERVISORES_LIST = "cobSupervisoresList";

	public static final String COB_ZONA_NO_CRITICA_LIST = "cobZonaNoCriticaList";
	
	public static final String CONSULTA_VISOR_CARTERA = "cobCarterasList";

	public static final String SICC_MODULO_LIST = "siccModuloList";

	public static final String SICC_PROCESO_LIST = "siccProcesoList";

	public static final String CONSULTA_CRONOGRAMA_CARTERA = "consultaCOBCronogramaCarteraList";

	public static final String COB_CRONOGRAMA_CARTERA_LIST = "mantenimientoCOBCronogramaCarteraList";

	public static final String SICC_CARTERA_LIST = "siccCarteraList";

	public static final String SICC_CENTRALES_RIESGO_LIST = "siccCentralesRiesgoList";

	public static final String SICC_OPERADORES_SMS_LIST = "siccOperadorSMSList";

	public static final String SICC_TIPO_SMS_LIST = "siccTipoSMSList";

	public static final String SICC_TIPO_CARTA_LIST = "siccTipoCartaList";

	public static final String SICC_ACCIONES_COBRANZA_LIST = "siccAccionesCobranzaList";

	public static final String SICC_ACCIONES_COBRANZA_SEARCH = "siccAccionesCobranzaSearch";

	public static final String COB_GESTIONES_COBRANZA_CONSULTORA_LIST = "cobGestionesCobranzaConsultoraList";

	public static final String COB_COBRADORES_PAIS_LIST = "cobCobradoresPaisList";

	public static final String COB_COBRADORES_UNIDAD_ADMINISTRATIVA_LIST = "cobCobradoresUnidadAdministrativaList";

	public static final String COB_UNIDAD_ADMINISTRATIVA_CRONOGRAMA_CARTERA_LIST = "cobUnidadAdministrativaCronogramaCarteraList";

	public static final String COB_CARTERA_ASIGNACION_COBRADOR_LIST = "cobCarteraAsignacionCobradorList";

	public static final String COB_ASIGNACION_CARTERA_SUPERVISOR_LIST = "cobAsignacionCarteraSupervisorList";

	public static final String COB_PROCESO_EXITOSO = "ProcesoExitoso";

	public static final String COB_VALIDACIONES_ASIGNACION_CARTERA_LIST = "cobValidacionesAsignacionCarteraList";

	public static final String COB_EXCEPCIONES_ASIGNACION_CARTERA_LIST = "cobExcepcionesAsignacionCarteraList";

	public static final String COB_EXCEPCIONES_CLASIFICACION_CLIENTE_LIST = "cobExcepcionesClasificacionClienteList";

	public static final String COB_CARTERAS_ASIGNADAS_LIST = "cobCarterasAsignadasList";

	public static final String COB_TIPO_CLIENTE_LIST = "cobTipoClienteList";

	public static final String COB_SUBTIPO_CLIENTE_LIST = "cobSubTipoClienteList";

	public static final String COB_TIPO_CLASIFICACION_CLIENTE_LIST = "cobTipoClasificacionClienteList";

	public static final String COB_CLASIFICACION_CLIENTE_LIST = "cobClasificacionClienteList";

	// Modulo de Cuenta Corriente
	public static final String CCC_VALOR_PARAMETRO_HABILITAR_PROCESO_CAD_MASIVO = "CADMASIVO";
	
	public static final String CCC_CARGOS_ABONOS_DIRETOS_DIGITADOS_LIST = "cccCargoAbonosDirectosList";

	public static final String CCC_TIPOS_CARGOS_ABONOS_DIRETOS_LIST = "cccTiposCargoAbonosDirectosList";
	
	public static final String CCC_TIPOS_CARGOS_DIRECTOS_DIGITABLES_LIST = "cccTiposCargosDirectosDigitablesList";
	
	public static final String CCC_TIPOS_ABONOS_DIRECTOS_DIGITABLES_LIST = "cccTiposAbonosDirectosDigitablesList";
	
	public static final String CCC_CARGOS_ABONOS_DIRECTOS_DIGITADOS_LIST = "cccCargoAbonosDirectosList";
	
	public static final String CCC_CARGOS_DIRECTOS_DIGITADOS_LIST = "cccCargosDirectosDigitadosList";
			
	public static final String CCC_ABONOS_DIRECTOS_DIGITADOS_LIST = "cccAbonosDirectosDigitadosList";
		
	public static final String CCC_TIPO_CARGOS_DIRECTOS_LIST = "cccTipoCargosDirectosList";
	
	public static final String CCC_TIPO_ABONOS_DIRECTOS_LIST = "cccTipoAbonosDirectosList";

	public static final String CCC_TIPOS_CARGOS_ABONOS_DIRETOS_DOCLE_LIST = "cccTiposCargoAbonoDirectosDocleList";
	
	public static final String CCC_TIPOS_ORIGEN_LOTES_BANCARIOS_LIST = "cccTiposOrigenLotesBancariosList";

	public static final String CCC_DOCUMENTOS_CONTABLES_LIST = "cccDocumentosContablesList";

	public static final String CCC_TIPO_BLOQUEOS_LIST = "cccTipoBloqueosList";

	public static final String CCC_ESTADO_LOTE_PENDIENTE = "T";

	public static final String CCC_ESTADO_LOTE_PROCESADO = "P";

	public static final String CCC_TIPOS_LOTE_BANCARIO_LIST = "cccTiposLoteBancarioList";

	public static final String CCC_ESTADOS_LOTE_BANCARIO_LIST = "cccEstadosLoteBancarioList";

	public static final String CCC_ESTADOS_PAGO_BANCARIO_LIST = "cccEstadosPagoBancarioList";
	
	public static final String CCC_TIPO_ERRORES_PAGOS_BANCARIOS_LIST = "CCCTipoErroresPagosBancariosList";
	
	public static final String CCC_CUENTA_CORRIENTE_BANCARIA_LIST = "CCCCuentaCorrienteBancariaList";
	
	public static final String CCC_PAGOS_BANCARIOS_POR_GESTIONAR_LIST = "mantenimientoCCCGestionarErroresPagosBancariosPagosBancariosList";
	
	public static final String CCC_PAGOS_BANCARIOS_POR_REGULARIZAR_LIST = "mantenimientoCCCRegularizacionPagosBancariosList";

	public static final String CCC_PAGOS_BANCARIOS_MANUALES_DIGITADOS_LIST = "cccPagosBancariosManualesList";

	public static final String CCC_LOTES_BANCARIOS_LIST = "cccLotesBancariosList";

	public static final String CCC_LOTES_BANCARIOS_PENDIENTES_LIST = "cccLotesBancariosPendientesList";

	public static final String CCC_CUENTA_CORRIENTES_MOVIMIENTOS_LIST = "cccCuentaCorrienteMovimientosList";

	public static final String CCC_CARGA_MASIVA_ERRORES_LIST = "cccErroresCargaMasivaList";
	
	public static final String CCC_ELIMINAR_CUPONES_LIST = "cccEliminarCuponesList";
	
	public static final String CCC_LEVANTAMIENTO_ERRORES_CLIENTES_LIST = "cccLevantamientoErroresClientesList";
	
	public static final String CCC_GASTOS_ADMINISTRATIVOS_LIST = "cccGastosAdministrativosList";

	public static final String CCC_RESUMEN_CLIENTES_LIST = "cccResumenClientesList";
	
	public static final String CCC_TIPO_CHEQUE_DIA = "Cheque Dia";
	
	public static final String CCC_TIPO_CHEQUE_FECHA = "Cheque Fecha";
	
	public static final String CCC_HOST_PAGOS_LINEA = "hostPagosLinea";
		
	public static final String CCC_USER_PAGOS_LINEA = "userPagosLinea";
		
	public static final String CCC_PWD_PAGOS_LINEA = "passwordPagosLinea";
		
	public static final String CCC_TIPO_CONEXION_PAGOS_LINEA = "tipoConexionPagosLinea";
			
	public static final String CCC_TABLA_PAGOS_LINEA = "tablaPagosLinea";
	
	public static final String COM_CALIFICACION_COMISION_LIST = "comCalificacionComisionList";

	public static final String COM_PORCENTAJE_COMISION_LIST = "comPorcentajeComisionList";

	public static final String COM_COMISION_LIST = "comComisionList";

	public static final String COM_CALIFICACION_COMISION_DETAL_LIST = "comCalificacionComisionDetalList";

	public static final String COM_PORCENTAJE_COMISION_DETAL_LIST = "comPorcentajeComisionDetalList";

	public static final String COM_COMISION_DETAL_LIST = "comComisionDetalList";

	public static final String COM_NIVEL_LIST = "comNivelList";

	public static final String STO_TIPO_DOCUMENTO_LIST = "stoTipoDocumentoList";

	public static final String STO_DOCUMENTO_VALIDACION_LIST = "stoDocumentoValidacionList";
	
	public static final String STO_SECUENCA_VALIDACION_LIST = "stoSecuenciaValidacionList";

	public static final String STO_INFORME_RESUMEN_PRIMEROS_PEDIDOS_LIST = "stoInformeResumenPrimerosPedidosList";

	public static final String STO_DETALLE_DOCUMENTO_LIST = "allDocumentos";

	public static final String STO_LEVANTAMIENTO_ERRORES_VALIDACION_LIST = "stoLevantamientoErroresValidacionList";
	
	public static final String STO_MENSAJES_ERROR = "stoMensajeErrorList";
	
	public static final String STO_GESTION_DATA = "stoGestionData";

	public static final String SICC_MAPA_LIST = "siccMapaList";

	public static final String SICC_MAPAZONA_LIST = "siccMapaZonaList";

	public static final String SICC_DESCRIPCIONPRODUCTO_LIST = "siccDescripcionProductoList";

	public static final String SICC_ZONASPAIS_LIST = "siccZonasPaisList";

	public static final String EDU_CODIGO_MENSAJE_EQUIVALENCIA_LIST = "eduCodigoMensajeEquivList";

	public static final String COM_TIPO_RESUMEN_LIST = "comTipoResumenList";

	public static final String BPS_TIPO_VARIABLE_LIST = "bpsTipoVariableList";

	public static final String OCR_DETALLADO_APTAS_LIST = "detalladoAptasList";

	public static final String OCR_PEDIDOS_DUPLICADOS_LIST = "pedidosDuplicadosList";

	public static final String APE_PEDIDOS_LIST = "pedidosApeList";

	public static final String MAE_DIRECTORIO_VENTAS_LIST = "regionalesVentasList";

	public static final String STO_TIPO_DOCUMENTO_IDENTIDAD_LIST = "stoTipoDocumentoIdentidadList";

	public static final String STO_CLIENTES_LIST = "stoClientesList";

	public static final String REC_LIST_UNIDA_ALMAC_VIRTU = "interfazRECEnviarUnidadesAlmacenVirtualList";

	public static final String REC_LIST_TRANS_BOREC = "interfazRECEnviarTransferenciaBoletasRecojoList";

	public static final String REC_INDIC_TRANS_BOREC_ANULA = "A";

	public static final String EDU_CONSULTA_HISTO_CONSU_APTA_LIST = "eduStatusAptaHistoList";
	public static final String EDU_CONSULTA_HISTO_CONSU_PROGR_LIST = "eduStatusProgrHistoList";
	public static final String EDU_CONSULTA_HISTO_CONSU_CAPAC_LIST = "eduStatusCapacHistoList";
	public static final String EDU_CONSULTA_HISTO_CONSU_BENEF_LIST = "eduStatusBenefHistoList";

	public static final String SICC_COBRADOR_LIST = "siccCobradorList";

	public static final String SICC_ETAPAS_LIST = "siccEtapasList";

	public static final String STO_TIPO_DOCUMENTO_CABECERA = "CABECERA";

	public static final String STO_TIPO_DOCUMENTO_DETALLE = "DETALLE";

	public static final String STO_TIPO_DOCUMENTO_TODOS = "";

	public static final String STO_TIPO_GESTIONABLE_SI = "SI";

	public static final String STO_TIPO_GESTION_RECHAZO_SI = "SI";

	public static final String STO_TIPO_AGRUPACION_REGION = "Region";

	public static final String STO_TIPO_AGRUPACION_ZONA = "Zona";

	public static final String STO_TIPO_GESTIONABLE_NO = "NO";

	public static final String STO_TIPO_GESTIONABLE_TODOS = "";

	public static final String STO_VALIDACIONES_BY_DOCUMENTO = "stoValidacionesByDocumento";

	public static final String STO_ORIGENES_LIST = "stoOrigenesByDocumento";

	public static final String SICC_TELEFONOS_LIST = "siccTelefonosList";

	public static final String STO_INDICADOR_STO = "1";

	public static final String STO_IND_PROCESO_MASIVO = "1";

	public static final String STO_IND_PROCESO_NO_MASIVO = "0";

	public static final String FIN_CODIGO_PAIS_LBEL = "L";

	public static final String STO_ACCIONES_GESTION_LIST = "stoAccionesGestionList";

	public static final String COM_TRAMO_EJECTUIVAS_LIST = "comTramoEjecutivasList";

	public static final String EDU_TIPO_CURSO_MIXTO = "03";
	public static final String EDU_TIPO_CURSO_CON_COSTO = "02";
	public static final String EDU_TIPO_CURSO_SIN_COSTO = "01";

	public static final String COM_CODIGO_COMISION_LIST = "comcodComisionList";

	public static final String COB_LISTA_CONSULTORAS = "listaConsultoras";

	public static final String COB_LISTA_CONSULTORAS_LLAMADA_ENTRANTE = "listaConsultorasLlamadaEntrante";

	public static final String REC_DIGITACION_BOLETAS_RECOJO_CABECERA_LIST = "mantenimientoRECDigitacionBoletasRecojoCabeceraList";

	public static final String REC_DIGITACION_BOLETAS_RECOJO_DETALLE_LIST = "mantenimientoRECDigitacionBoletasRecojoDetalleList";

	public static final String REC_DIGITACION_BOLETAS_RECOJO_DETALLE_ELIM_LIST = "mantenimientoRECDigitacionBoletasRecojoDetalleEliminadosList";

	public static final String REC_DIGITACION_BOLETAS_RECOJO_DISC_LIST = "mantenimientoRECDigitacionBoletasRecojoDiscrepanteList";

	public static final String REC_DIGITACION_BOLETAS_RECOJO_DETALLE_DISC_LIST = "mantenimientoRECDigitacionBoletasRecojoDetalleDiscrepanteList";

	public static final String SICC_TIPOREPORTE_LIST = "siccTipoReporteList";

	public static final String EDU_CONSULTA_NIVELES_CAPAC_LIST = "consultatStatusConsultoraList";

	public static final String REC_LISTA_CODIGOS_VENTA_VALIDOS = "listaRECCodigosVentaValidos";

	public static final String MAE_CLIENTE_SUBTIPO_LIST = "maeClienteSubTipoList";
	public static final String MAE_CLIENTE_CLASIFICACION_LIST = "maeClienteClasificacionList";
	public static final String MAE_CLIENTE_SEXO_LIST = "maeClienteSexoList";
	public static final String MAE_CLIENTE_TRATAMIENTO_LIST = "maeClienteTratamientoList";
	public static final String MAE_CLIENTE_ESTADO_CIVIL_LIST = "maeClienteEstadoCivilList";
	public static final String MAE_CLIENTE_TIPO_VIA_LIST = "maeClienteTipoViaList";
	public static final String MAE_CLIENTE_NIVEL1_LIST = "maeClienteNivel1List";
	public static final String MAE_CLIENTE_NIVEL_ESTUDIO_LIST = "maeClienteNivelEstudioList";
	public static final String MAE_CLIENTE_NACIONALIDAD_LIST = "maeClienteNacionalidadList";

	public static final String MAE_CLIENTE_CARACTER_BARRA = "/";
	public static final String MAE_CLIENTE_CARACTER_GUION = "-";
	public static final String MAE_CLIENTE_CARACTER_PUNTO = ".";
	public static final String MAE_CLIENTE_CARACTER_ENIE = "Ñ";
	public static final String MAE_CLIENTE_CARACTER_X = "X";
	
	public static final String MAE_CLIENTE_CARACTER_1 = "‘";
	public static final String MAE_CLIENTE_CARACTER_2 = "<";
	public static final String MAE_CLIENTE_CARACTER_3 = ">";
	public static final String MAE_CLIENTE_CARACTER_4 = ";";
	public static final String MAE_CLIENTE_CARACTER_5 = "/";
	public static final String MAE_CLIENTE_CARACTER_6 = "~";
	public static final String MAE_CLIENTE_CARACTER_7 = "&";
	
	public static final String MAE_CLIENTE_VOCAL_A = "A";
	public static final String MAE_CLIENTE_VOCAL_E = "E";
	public static final String MAE_CLIENTE_VOCAL_I = "I";
	public static final String MAE_CLIENTE_VOCAL_O = "O";
	public static final String MAE_CLIENTE_VOCAL_U = "U";
	
	public static final String MAE_CLIENTE_COMPUESTO_MARIA = "MARIA";
	public static final String MAE_CLIENTE_COMPUESTO_MA = "MA";
	public static final String MAE_CLIENTE_COMPUESTO_MA_PUNTO = "MA.";
	public static final String MAE_CLIENTE_COMPUESTO_JOSE = "JOSE";
	public static final String MAE_CLIENTE_COMPUESTO_J = "J";
	public static final String MAE_CLIENTE_COMPUESTO_J_PUNTO = "J.";

	public static final int MAE_CLIENTE_NRO_POSICIONES_CON_PESO = 15;
	public static final int MAE_CLIENTE_NRO_BASE = 11;
	public static final String MAE_OPE_CODIGO_CLIENTE = "MAECLT";
	public static final String MAE_CODIGO_CLIENTE_GENERACION_AUTOMATICA = "A";
	public static final String MAE_CODIGO_RUC_2 = "1";
	public static final String MAE_CODIGO_REGISTRO_UNICO_CONTRIBUYENTE_FACT = "2008";
	

	public static final String MAE_TIPO_CLIENTE_CONSULTORA = "02";
	public static final String MAE_TIPO_CLIENTE_GERENTE = "04";
	public static final String MAE_TIPO_CLIENTE_POTENCIAL = "01";
	public static final String MAE_TIPO_CLIENTE_HIJADUPLA = "10";

	public static final String MAE_SUBTIPO_CLIENTE_CONSULTORA_NEGOCIO = "04";
	public static final String MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA = "06";
	
	public static final String MAE_SUBTIPO_DOCUMENTO_CONSULTORA_TIPO_VALIDACION = "VAL_SUB_DOCU";
	public static final String MAE_SUBTIPO_DOCUMENTO_CONSULTORA_MODULO_VALIDACION = "SUBDOC01";
	
	public static final String MAE_SUBTIPO_GERENTE_REGION = "01";
	public static final String MAE_SUBTIPO_GERENTE_ZONA = "02";
	public static final String MAE_SUBTIPO_POTENCIAL_AVAL = "02";
	public static final String MAE_SUBTIPO_HIJADUPLA_HIJADUPLA = "01";

	public static final String MAE_TIPO_CLASIFICACION_DUPLACYZONE = "23";
	public static final String MAE_CLASIFICACION_DUPLACYZONE = "01";

	public static final String MAE_TIPO_VINCULO_DUPLACYZONE = "01";
	public static final String MAE_TIPO_VINCULO_RECOMENDANTE = "03";
	public static final String MAE_TIPO_VINCULO_CONSULTORA_AVAL = "05";

	public static final String MAE_COD_MENSAJE_BIENVENIDA = "MAE01";
	public static final String MAE_COD_MENSAJE_BIENVENIDA_DUPLACYZONE = "MAE02";
	public static final String MAE_COD_MENSAJE_RECHAZO_DUPLACYZONE = "MAE03";

	public static final String MAE_OID_CONFI_CAMPO_NACIONALIDAD = "47";
	public static final String MAE_OID_CONFI_CAMPO_GRADO_INSTRUCCION = "55";
	public static final String MAE_OID_CONFI_CAMPO_TRATAMIENTO = "41";

	public static final String CCC_PROCESO_CONSULTA_DEUDA_APROVISIONADA = "CON002";
	public static final String CCC_SUBPROCESO_CONSULTA_DEUDA_APROVISIONADA = "1";

	public static final Object EDU_COLOMBIA_ESIKA = "COE";

	public static final String BUSQUEDA_CLIENTES_LIST = "busquedaClientesList";
	public static final String BUSQUEDA_DIRECCIONES_LIST = "busquedaDireccionesList";

	public static final String MAE_CLIENTE_MODULO_MAE = "16";

	public static final String BUSQUEDA_PREMIOS_LIST = "busquedaPremiosList";
	public static final String BUSQUEDA_CONSURSOS_LIST = "busquedaConcursosList";

	public static final String MAE_OID_DIRIG_CONSUL = "1";
	public static final String MAE_OID_BASE_CALCU_RECOMEN = "4";

	public static final String SICC_ESTADOS_BOREC_LIST = "RECEstadosBoletasRecojoList";
	public static final String SICC_RESULTADOS_BOREC_LIST = "RECResultadosBoletasRecojoList";
	public static final String SICC_MOTIVOS_NORECOJO_BOREC_LIST = "RECMotivosNoRecojoBoletasRecojoList";

	public static final String REC_PRODUCTOS_LIST_POPUP = "recProductosListPopup";

	public static final String STO_AGRUPACION_FECHA = "P";
	public static final String STO_AGRUPACION_LOTE = "Num. Lote";
	public static final String STO_AGRUPACION_REGION = "Region";
	public static final String STO_AGRUPACION_ZONA = "Zona";
	public static final String STO_AGRUPACION_CLIENTE = "Cliente";

	public static final String STO_LISTA_VALIDACIONES = "stoListaValidaciones";
	public static final String STO_LISTA_PEDIDOS = "stoListaConsultaPedidos";
	public static final String STO_LISTA_RECLAMOS_ELIMINADOS = "stoListaReclamosEliminados";

	public static final String REC_GESTION_BOLETAS_RECOJO_NO_EXITOSAS_LIST = "mantenimientoRECGestionBoletasRecojoNoExitosasCabeceraList";

	public static final String EDU_CONEXION_EXTERNA_FOX = "FOX";

	public static final String EDU_MANTENIMIENTO_OBJETIVO_ASISTENCIA_COPIA_LIST = "mantenimientoObjetivoAsistenciaCopiaList";

	public static final String EDU_CONSULTA_DOWNLOAD_DOCUMENTO_LIST = "consultaDownloadDocumentoList";

	public static final String EDU_DWFILE = "dwfile";
	public static final String EDU_FILE_SEPARATOR = "/";

	public static final String SIC_TIPO_REPORTE_REGION = "RR";

	public static final String SAC_UNIDAD_NEGOCIO_LIST = "sacUnidadNegocioList";
	public static final String SAC_TIPO_CONSULTA_LIST = "sacTipoConsultaList";
	public static final String SAC_INCLUYE_MAV_LIST = "sacIncluyeMavList";

	public static final String EDU_TIPO_TODAS = "T";

	public static final String COM_TIPO_CALCULO_LIST = "comTipoCaluloList";

	public static final String EDU_PROCESO_EXONERACION_ASISTENCIA_LIST = "procesoExoneracionAsistenciaList";

	public static final String REC_GESTION_BOLETAS_RECOJO_DISCREPANCIA_CABECERA_LIST = "mantenimientoRECGestionBoletasRecojoDiscrepanteCabeceraList";

	public static final String REC_GESTION_BOLETAS_RECOJO_DISCREPANCIA_DETALLE_LIST = "mantenimientoRECGestionBoletasRecojoDiscrepanteDetalleList";

	public static final String REC_GESTION_BOLETAS_RECOJO_DISCREPANCIA_DISC_LIST = "mantenimientoRECGestionBoletasRecojoDiscrepanteDiscrepanteList";

	public static final String REC_GESTION_BOLETAS_RECOJO_DISCREPANCIA_DETALLE_DISC_LIST = "mantenimientoRECGestionBoletasRecojoDiscrepanteDetalleDiscrepanteList";

	public static final String REC_GESTION_BOLETAS_RECOJO_DISCREPANCIA_CODIGO_VENTA_LIST = "mantenimientoRECGestionBoletasRecojoDiscrepanteCodigoVentaList";

	public static final String COB_LIST_SOCIEDADES_PARAMS = "mantenimientoSociedadParamsList";

	public static final String EDU_PROCESO_EXONERACION_APTAS_LIST = "procesoExoneracionAptasList";

	public static final String REC_CONSULTORA_PEDIDO_NMPS_LIST = "mantenimientoRECGestionIngresoAnulacionNmpsConsultoraPedidoList";

	public static final String REC_CONSULTORA_TOTAL_NMPS_LIST = "totalRegistrosRecuperados";

	public static final String REC_CONSULTORA_TOTAL_OK = "totalRegistrosOk";

	public static final String REC_CONSULTORA_TOTAL_ERROR = "totalRegistrosError";

	public static final String CHECK_TIPO_01 = "01";
	public static final String CHECK_TIPO_02 = "02";
	public static final String CHECK_TIPO_03 = "03";

	public static final String EDU_PARAMETROS_CAMPANHAS_LIST = "eduCampanhasList";

	public static final String MAE_CLIENTE_NIVEL2_LIST = "STOlistaProvinciasSolicitudCredito";
	public static final String MAE_CLIENTE_NIVEL3_LIST = "STOlistaDistritosSolicitudCredito";
	public static final String MAE_CLIENTE_NIVEL4_LIST = "STOlistaSectoresSolicitudCredito";

	public static final String MAE_CLIENTE_NIVEL1_AVAL_LIST = "maeClienteNivel1AvalList";
	public static final String MAE_CLIENTE_NIVEL2_AVAL_LIST = "maeClienteNivel2AvalList";
	public static final String MAE_CLIENTE_NIVEL3_AVAL_LIST = "maeClienteNivel3AvalList";

	public static final String IMPRESION_LASER_NUMERO_DETALLES_FACTURA = "numeroDetallesFactura";

	public static final String IMPRESION_LASER_NUMERO_DETALLES_GUIA = "numeroDetallesGuia";

	public static final Object IMPRESION_LASER_NUMERO_DETALLES_CUENTA_CORRIENTE = "numeroDetallesCtaCte";

	public static final Object IMPRESION_LASER_INDICADOR_GENERAR_CUENTA_CORRIENTE = "indicadorGenerarCtaCteLaser";

	public static final String IMPRESION_LASER_INDICADOR_GENERAR = "S";

	public static final String MAE_FIADOR_NIVEL1_LIST = "STOlistaDepartamentoFiadorSolicitudCredito";
	public static final String MAE_FIADOR_NIVEL2_LIST = "STOlistaProvinciaFiadorSolicitudCredito";
	public static final String MAE_FIADOR_NIVEL3_LIST = "STOlistaDistritoFiadorSolicitudCredito";
	public static final String MAE_FIADOR_NIVEL4_LIST = "STOlistaSectorFiadorSolicitudCredito";

	public static final String MAE_TIPO_PROCESO = "tipoProcesoMAECalificacionEstatusResolverMensajesFormList";

	public static final String CODIGO_TIPO_PROCESO = "F";

	public static final String MAE_CLASIFICACION_ESTATUS_LIST = "clasificacionEstatusMAECalificacionEstatusResolverMensajesFormList";

	public static final Object IMPRESION_LASER_NUMERO_DETALLES_NOTA_CREDITO = "numeroDetallesNotaCredito";

	public static final Object IMPRESION_LASER_NUMERO_DETALLES_NOTA_DEBITO = "numeroDetallesNotaDebito";

	public static final String OCR_VERIFICACION_LIST = "mantenimientoOCRVerificacionList";
	public static final String OCR_INGRESADA_VERIFICADA_LIST = "reporteOCRIngresadaVerificadaList";
	public static final String OCR_MANTENIMIENTO_PEDIDO_LIST = "mantenimientoOCRPedidoList";
	public static final String OCR_MANTENIMIENTO_VERIFICA_PEDIDO_LIST = "mantenimientoOCRVerificaPedidoList";
	public static final String COD_USUARIO_LIST = "codUsuarioList";

	public static final String SICC_TIPO_MOVIMIENTO = "siccTipoMovimientoList";
	public static final String SICC_ESTADO = "siccEstado";

	public static final String CODIGO_SUBACCESO_000 = "000";
	public static final String TIPO_DOCUMENTO_SPVD = "SPVD";
	public static final String ESTADO_PROCESO_01 = "01";
	public static final String TIPO_SOLICITUD_SOC = "SOC";
	public static final String ACCESO_FISICO_01 = "01";
	public static final String REC_RESULTADO_LIST = "resultadoList";
	public static final String REC_MOTIVO_LIST = "motivoList";
	public static final String TIPO_DOCUMENTO_SPVC = "SPVC";

	public static final String CODIGO_PRODUCTO_DUPLICADO = "PRO";
	public static final String CODIGO_PREMIO_DUPLICADO = "PRE";

	public static final String REC_INDICADOR_IGUAL = "1";
	public static final String REC_INDICADOR_DIFERENTE = "0";

	public static final String STO_TIPO_REPORTE_CABECERA = "C";
	public static final String STO_TIPO_REPORTE_DETALLE = "D";
	public static final String STO_TIPO_REPORTE_TODOS = "T";

	public static final String STO_TIPO_DOCUMENTO_OCC = "OCC";
	public static final String STO_TIPO_DOCUMENTO_OCD = "OCD";
	public static final String STO_TIPO_DOCUMENTO_SPVC = "SPVC";
	public static final String STO_TIPO_DOCUMENTO_SPVD = "SPVD";
	public static final String STO_TIPO_DOCUMENTO_SCC = "SCC";
	public static final String STO_TIPO_DOCUMENTO_SAD = "SAD";
	public static final String STO_TIPO_DOCUMENTO_DCYZ = "DCYZ";
	public static final String STO_TIPO_DOCUMENTO_CUP = "CUP";
	public static final String STO_TIPO_DOCUMENTO_OT = "OT";
	public static final String STO_TIPO_DOCUMENTO_SIM = "SIM";
	public static final String STO_TIPO_DOCUMENTO_CIF = "CIF";
	public static final String STO_TIPO_DOCUMENTO_FAS = "FAS";
	public static final String STO_TIPO_DOCUMENTO_CED = "CED";

	public static final String STO_INTERVALO_CARGA_STO = "STO_INTE_CARG";
	public static final String STO_INTERVALO_PROCESO_STO = "STO_INTE_PROC";
	public static final String STO_IND_AGRUPACION_ELIM_PEDIDOS = "STO_OCC_AGRP_ELPE";
	public static final String STO_IND_ELIM_PEDID_TODOS = "STO_OCC_ELPE_TODO";

	public static final String STO_HORAS_CARGA_LIST = "stoLevantamientoIntervaloCargaList";
	public static final String STO_HORAS_PROCESO_LIST = "stoLevantamientoIntervaloRecepcionList";
	public static final String STO_CLASIFICACION_CONSULTORA_LIST = "stoClasificacionConsultoraList";
	public static final String STO_ESTADISTICA_ULTIMAS_CAMPANAS_LIST = "stoEstadisticaUltimasCampanasList";

	public static final String REC_ESTADOS_LIST = "estadosList";
	public static final String REC_CABECERAS_RECLAMOS_LIST = "mantenimientoRECLiquidacionBoletaRecojoCabeceraList";
	public static final String REC_DETALLE_RECLAMOS_LIST = "mantenimientoRECLiquidacionBoletaRecojoDetalleList";

	public static final String REC_INDICADOR_APRUEBA = "A";
	public static final String REC_INDICADOR_RECHAZA = "R";
	public static final String REC_INDICADOR_ELIMINA = "E";
	public static final String REC_INDICADOR_RECALCULO = "C";

	public static final Object TIPO_TOTAL_SUB_GERENCIA = "S";

	public static final String SQL_SERVER = "SQL";

	public static final String CODIGO_PERCEPCION = "PER";

	public static final String CODIGO_BANCOS = "BAN";

	public static final String INTERFAZ_RECEPCION_RECAUDO_AUTOMATICO = "BAN-1";

	public static final String STO_DETALLES_OCC_LIST = "mantenimientoOCCCabecerasDetallesList";

	public static final String REC_CABECERAS_BOLETA_RECOJO_LIST = "consultaRECBoletaRecojoSearchList";
	public static final String REC_DETALLES_BOLETA_RECOJO_LIST = "consultaRECBoletaRecojoDetallesSearchList";

	public static final String COLUMNA_CONSULTA_COM_RESPONSABLES_UA_EXT = "columnaConsultaCOMResponsablesUAExtList";

	public static final String EDU_CONSULTA_LISTADO_CLASIFICACIONES_LIST = "consultaEDUListadoClasificacionesList";

	public static final String EDU_TIPO_BENEFICIO = "B";

	public static final String REC_BUSQUEDA_INGRESO_ATENCIONES_LIST = "mantenimientoRECIngresoAtencionesSearchList";
	public static final String REC_PROCESAR_INGRESO_ATENCIONES_LIST = "mantenimientoRECIngresoAtencionesProcessList";
	
	public static final String REC_PROCESAR_INGRESO_MASIVO_OPERACIONES_LIST = "mantenimientoRECIngresoMasivoOperacionesProcessList";

	public static final String REC_DETALLES_DIGITADOS_LIST = "mantenimientoRECDigitacionCDRDetallesDigitadosList";

	public static final String REC_CLIENTES_INGRESO_ATENCIONES_LIST = "mantenimientoRECIngresoAtencionesClientesList";

	public static final String REC_CDR_DIGITADOS_LIST = "consultaRECCDRDigitadosList";

	public static final String REC_CDR_DIGITADOS_DETALLES_LIST = "consultaRECCDRDetallesDigitadosList";

	public static final String MAE_CLIENTE_TIPO_DIRECCION_LIST = "maeTipoDireccionList";

	public static final String HIP_UNIDADES_ADMINISTRATIVAS_LIST = "hipUnidadesAdministrativasList";
	public static final String HIP_DTO_DATOS_CLIENTE = "hipDtoDatosCliente";
	public static final String HIP_TIPO_CLIENTE_LIST = "hipTipoClienteList";
	public static final String HIP_SUBTIPO_CLIENTE_LIST = "hipSubTipoClienteList";
	public static final String HIP_TIPO_CLASIFICACION_LIST = "hipTipoClasificacionList";
	public static final String HIP_CLASIFICACION_LIST = "hipClasificacionList";
	public static final String HIP_OPCIONES_LIST = "hipOpcionesList";
	public static final String HIP_PAIS_COLOMBIA = "CO";

	public static final String HIP_OPCION_SEGUIMIENTO_PEDIDO = "HIP-1";
	public static final String HIP_OPCION_CONCURSOS = "HIP-2";
	public static final String HIP_OPCION_CUENTA_CORRIENTE = "HIP-3";
	public static final String HIP_OPCION_CUENTA_CORRIENTE_CAMPANHA = "HIP-30";
	public static final String HIP_OPCION_CUENTA_CORRIENTE_HISTORICA = "HIP-3_1";
	public static final String HIP_OPCION_CRONOGRAMA_ACTIVIDADES = "HIP-4";
	public static final String HIP_OPCION_ENVIOS_PREFERENCIALES = "HIP-5";
	public static final String HIP_OPCION_HISTORIAL_RESPONSABLES_SECCION = "HIP-6";
	public static final String HIP_OPCION_HISTORIAL_POST_VENTAS = "HIP-7";
	public static final String HIP_OPCION_HISTORIAL_BLOQUEO = "HIP-8";
	
	public static final String HIP_OPCION_HISTORIAL_BLOQUESO = "HIP-8";
	public static final String HIP_OPCION_SISTEMA_INTEGRAL_EDUCACION = "HIP-9";
	public static final String HIP_OPCION_SOLICITUD_CERTIFICACIONES = "HIP-10";
	public static final String HIP_OPCION_PROGRAMA_NUEVAS = "HIP-11";
	public static final String HIP_OPCION_ACTUALIZACION_DATOS = "HIP-12";
	public static final String HIP_OPCION_ACTUALIZACION_DUPLA_CYZONE = "HIP-13";
	public static final String HIP_OPCION_CREAR_PEDIDOS = "HIP-14";
	public static final String HIP_OPCION_CREAR_RECLAMOS = "HIP-15";
	public static final String HIP_OPCION_BOLETA_RECOJO = "HIP-7_1";
	public static final String HIP_OPCION_PEDIDOS = "HIP-16";
	public static final String HIP_OPCION_CONSULTA_HISTORICO_VINCULOS = "HIP-18";
	public static final String HIP_OPCION_CONSULTA_SOLICITUDES_POLIZA = "HIP-19";
	public static final String HIP_OPCION_ACTUALIZACION_DATOS_CLIENTE = "HIP-28";
	public static final String HIP_OPCION_ACTUALIZACION_DIRECCION_CLIENTE = "HIP-29";
	
	
	public static final String HIP_RECLAMOS_CABECERA_LIST = "hipReclamosCabeceraList";
	public static final String HIP_RECLAMOS_DETALLE_LIST = "hipReclamosDetalleList";
	public static final String HIP_RECLAMOS_BOLETA_RECOJO_LIST = "hipReclamosBoletaRecojoList";
	public static final String HIP_RECLAMOS_MOVIMIENTO_ENVIA = "E";
	public static final String HIP_RECLAMOS_MOVIMIENTO_DEVUELVE = "D";
	public static final String HIP_RECLAMOS_ESTADO_FACTURADO = "Facturado";
	public static final String HIP_RECLAMOS_ESTADO_PENDIENTE = "Pendiente";

	public static final String HIP_CUENTA_CORRIENTES_MOVIMIENTOS_LIST = "hipCuentaCorrientesMovimientosList";
	public static final String HIP_CUENTA_CORRIENTES_CAMPANHA_MOVIMIENTOS_LIST = "hipCuentaCorrientesCampanhaMovimientosList";
	public static final String HIP_CUENTA_CORRIENTES_CAMPANHA_MOVIMIENTOS_CABECERA = "hipCuentaCorrientesCampanhaMovimientoCabecera";	
	public static final String HIP_CUENTA_CORRIENTES_HISTORICA_MOVIMIENTOS_LIST = "hipCuentaCorrientesHistoricaMovimientosList";
	public static final String HIP_CUENTA_CORRIENTES_DETALLE_LIST = "hipCuentaCorrientesDetalleList";
	public static final String HIP_CUENTA_CORRIENTES_CAMPANHA_PARAMS_REPORTE = "hipCuentaCorrientesCanpanyaParamsReporte";
	public static final String HIP_CUENTA_CORRIENTES_CAMPANHA_MOVIMIENTOS_PBLC_CABECERA = "hipCuentaCorrientesCampanhaMovimientoPbLcCabecera";	
	public static final String HIP_CUENTA_CORRIENTES_HISTORICA_MOVIMIENTOS_PBLC_LIST = "hipCuentaCorrientesHistoricaMovimientosPbLcList";
	public static final String HIP_CUENTA_CORRIENTES_CAMPANHA_ESTADO_ACTIVA = "Activa";
	public static final String HIP_CUENTA_CORRIENTES_CAMPANHA_ESTADO_INACTIVA = "Inactiva";
	public static final String HIP_CUENTA_CORRIENTES_CAMPANHA_ESTADO_CANCELADA = "Cancelada";
	public static final String HIP_CUENTA_CORRIENTES_CAMPANHA_ESTADO_NO_INVITADA = "No Invitada";

	public static final String HIP_CONCURSOS_LIST = "hipConcursosList";
	public static final String HIP_CONCURSOS_PUNTAJE_LIST = "hipConcursosPuntajeList";
	public static final String HIP_CONCURSOS_PREMIOS_LIST = "hipConcursosPremiosList";
	public static final String HIP_CONCURSOS_BOLSA_FALTANTES_LIST = "hipConcursosBolsaFaltantesList";

	public static final String SAB_CARGA_FVP_ERRORES_LIST = "validarErrorCargaFuenteVentaPrevistaList";
	public static final String SAB_CARGA_FVP_CALCULAR_LIST = "calcularCargaFuenteVentaPrevistaList";

	public static final String HIP_CRONOGRAMA_ACTIVIDADES_LIST = "cronogramaActividadesList";
	public static final String HIP_GERENTE_ZONA_LIST = "gerenteZonaList";
	public static final String HIP_ENVIO_PREFERENCIALES_LIST = "envioPreferencialesList";
	public static final String HIP_RESPONSABLE_SECCION_LIST = "responsableSeccionList";
	public static final String HIP_CONSULTA_RECLAMOS_LIST = "consultaReclamosList";
	public static final String HIP_HISTORIA_BLOQUEO_LIST = "historiaBloqueoList";
	public static final String HIP_EDU_CONSULTORAS_LIST = "eduConsultorasList";
	public static final String HIP_EDU_CURSOS_LIST = "eduCursosList";
	public static final String HIP_TIPO_CERTIFIACION_LIST = "tipoCertificacionList";
	public static final String HIP_PREMIOS_CLIENTE_LIST = "premiosClienteList";
	public static final String HIP_MODU_ORIG = "6";

	public static final String HIP_DES_ACTIVIDAD = "d";
	public static final String HIP_PERIODO_ANTERIOR = "a";
	public static final String HIP_PERIODO_ACTUAL = "b";
	public static final String HIP_PERIODO_SIGUIENTE = "c";

	public static final String IMP_TIPO_SOLICITUD_LIST = "impTipoSolicitudList";

	public static final String REC_BOREC_INGRESADA = "I";
	public static final String REC_BOREC_RECIBIDA = "RE";
	public static final String REC_BOREC_GESTION = "GE";
	public static final String REC_BOREC_CERRADA = "CE";
	public static final String REC_BOREC_EXITOSA = "EX";
	public static final String REC_BOREC_NO_EXITOSA = "NX";
	public static final String REC_BOREC_DISCREPANTE = "CD";
	public static final String REC_BOREC_NO_ENTREGADA = "NE";

	public static final String PED_PEDIDOS_FACT_LIST = "pedidosFacturadosList";
	public static final String PED_ELIMINA_PEDIDOS_DIGI_LIST = "eliminaPedidosDigitadosList";
	public static final String PED_PEDIDOS_A_ELIMINAR_FACT_LIST = "pedidosFacturadosAEliminarList";

	public static final String CRA_ZONAS_FACTURAN_HOY_LIST = "craZonasFacturanHoyList";
	public static final String CODIGO_DOCUMENTO_STO_DUPLA_CYZONE = "DCYZ";

	public static final String MAE_NIVEL_RIESGO_LIST = "maeNivelRiesgoList";

	public static final String CODIGO_DOCUMENTO_STO_CUPON_PAGO = "CUP";
	public static final String PARAM_REPOR_BTN = "BTN";
	public static final String PARAM_REPOR_BTN_PDF = "PDF";
	public static final String PARAM_REPOR_BTN_XLS = "XLS";
	public static final String PARAM_REPOR_BTN_AMB = "AMB";
	public static final String PARAM_REPOR_BTN_NIN = "NIN";

	public static final String CUPONES_STO_RECHAZO_SELLO_LIST = "stoCuponesRechazoSelloList";

	public static final String SAC_INDICADORES_LIST = "sacIndicadoresList";

	public static final String EDU_ESTADO_CONSULTORA_ACTIVA = "01";

	public static final String EDU_ESTADO_CONSULTORA_PEGRESO = "02";

	public static final String EDU_ESTADO_CONSULTORA_EGRESO = "03";

	public static final String EDU_PARAMETROS_STATUS_LIST = "parametroStatusList";

	public static final String STO_MOTIVOS_RECHAZO_LIST = "stoMotivosRechazoList";

	public static final String STO_MOTIVOS_RECHAZO_ABAJO_LIST = "stoMotivosRechazoAbajoList";
	public static final String STO_ACCESO_ROL_LIST = "stoAccesoRolList";

	public static final String STO_PANTALLA_EDITABLE = "ED";

	public static final String APE_SUBLINEA_ARMADO_LIST = "apeSubLineArmadoList";
	public static final String APE_DETALLE_ANAQUEL_LIST = "apeDetalleAnaquelList";
	public static final String APE_MAPA_CENTRO_DISTRIBUCION_LIST = "apeMapaCentroDistribucionList";
	public static final String APE_MAPA_ZONAS_LIST = "apeMapaZonasList";
	public static final String APE_MAPA_ORDEN_LIST = "apeMapaOrdenList";

	public static final String REC_LINEA_YOBEL_LIST = "lineaYobelList";
	public static final String REC_LINEA_YOBEL_TOT = "lineaYobelTot";

	public static final String REC_PRODUCTO_RECLAMO_YOBEL_LIST = "productoReclamoYobelList";
	public static final String REC_PRODUCTO_RECLAMO_YOBEL_TOT = "productoReclamoYobelTot";

	public static final String COM_ENVIO_SAP_LIST = "comEnvioSapList";

	public static final String OCR_TIPO_RECEPCION_WEBDD_LIST = "ocrTipoRecepcionList";
	public static final String OCR_TIPO_RECEPCION_DEMANDA_WEBDD_LIST = "ocrTipoRecepcionDemandaWebDDList";

	public static final String LISTA_CODIGOS_MATRIZ = "listaCodigosMatriz";

	public static final String LID_TIPO_ASIGNACION_PUNTAJE_LIST = "tipoAsignacionPuntajeList";
	public static final String LID_OBJETIVO_ASIGNACION_PUNTAJE_LIST = "lidObjetivoAsignacionPuntaje";
	public static final String LID_TIPO_ASIGNACION_PUNTAJE_DEFAULT = "04";
	public static final String LID_TIPO_EVALUACION_LIST = "lidTipoEvaluacionList";
	public static final String LID_TIPO_EVALUACION_DEFAULT = "03";

	public static final String HIP_PRODUCTOS_DESPACHADOS_AUTO_LIST = "hipProduDespaAutoList";
	public static final String HIP_PRODUCTOS_SOLICITADOS_LIST = "hipProduSolicList";

	public static final String HIP_PEDIDOS_CONSULTORA_LIST = "hipPedidosConsultoraList";
	public static final String HIP_DETALLE_PEDIDOS_CONSULTORA_LIST = "hipDetallePedidosConsultoraList";
	public static final String HIP_DETALLE_SOLIC_CONSULTORA_LIST = "hipDetalleSolicConsultoraList";

	public static final String STO_INDICADOR_RECHAZADO = "1";
	public static final String STO_PARAMETRO_INDICADOR_MAIL_GZ_SCC = "IND_ENVI_MAIL_GEZN";
	public static final String STO_INDICADOR_MAIL_GZ_SCC_OK = "1";

	public static final String PRIMER_TRAMO = "01";
	public static final String ULTIMO_TRAMO = "03";

	public static final String STO_BLOQUEOS_CONSULTORA_LIST = "stoBloqueosConsultoraList";

	public static final String COM_ORDEN_ESTADISTICO_ZONA_LIST = "ordenEstadisticoZonaList";

	public static final String HIP_CDR_RECHAZADOS_LIST = "consultaCdrRechazadosList";

	public static final String HIP_CDR_RECHAZADOS_DETALLE_LIST = "consultaDetalleCdrRechazadosList";

	public static final Object HIP_OPCION_CDR_RECHAZADOS = "HIP-17";

	public static final Object HIP_TIPO_DOCUMENTO = "SPVC";

	public static final Object HIP_TIPO_DOCUMENTO_DETALLE = "SPVD";

	public static final String STO_DOCUMENTOS_REFERENCIA_LIST = "stoDocumentosReferenciaList";

	public static final String HIP_TIPO_PREMIO = "tipoPremioList";

	public static final String STO_CODIGO_NUMERO_DOCUMENTO_CUPON = "CP";

	public static final String STO_CUPON_PAGO_LIST = "stoCuponPagoList";

	public static final String REC_SECUENCIA_ZONA_LIST = "recSecuenciaZonaList";

	public static final String CONSULTA_MAE_CLIENTE_LIST = "consultaClientesList";

	public static final String SICC_FORMA_VENTA_LIST = "siccFormaVentaList";

	public static final String MAE_ZONA_LIST = "zonaList";

	public static final String MAE_TERRITORIO_LIST = "territorioList";

	public static final String MAE_INDICADOR_ACTIVO_LIST = "indicadorActivosList";

	public static final String CUP_NIVELES_SUSCRIPCION_LIST = "cupSuscripcionNiveles";

	public static final String CUP_CUPONES_SUSCRIPCION_LIST = "cupSuscripcionNivelesCupones";

	public static final String INDICADOR_ACTIVO = "indActivaCheck";

	public static final String MAE_ESTADO_REGISTRADA = "01";
	public static final String MAE_ESTADO_NUEVA = "02";
	public static final String MAE_ESTADO_NORMAL = "03";
	public static final String MAE_ESTADO_EGRESANTE = "04"; // --POSIBLE
															// EGRESO--
	public static final String MAE_ESTADO_EGRESADA = "05";
	public static final String MAE_ESTADO_REINGRESO = "06";
	public static final String MAE_ESTADO_REACTIVADA = "08";
	public static final String MAE_ESTADO_RETIRADA = "07";

	public static final String TIPO_NOVEDAD_CANTIDAD_INCOMPLETA = "01";
	public static final String TIPO_NOVEDAD_CONSEJERA_NO_PRODUCTO = "02";
	public static final String TIPO_NOVEDAD_DIRECCION_ERRADA = "03";
	public static final String TIPO_NOVEDAD_CONSEJERA_NO_UBICADA = "04";
	public static final String TIPO_NOVEDAD_RECEPCION_DETALLE = "05";
	public static final String TIPO_NOVEDAD_NO_POSTVENTA = "06";
	public static final String TIPO_NOVEDAD_MERCANCIA_RECEPCIONADA = "07";
	public static final String TIPO_NOVEDAD_ERRORES_POSTVENTA = "08";
	public static final String TIPO_NOVEDAD_BR_NO_ENTREGADA = "09";

	public static final String REC_NOVEDADES_RECOJO_LIST = "novedadesRecojoList";

	public static final String IND_RECEPCION_DETALLE = "1";

	public static final String IND_EXPRESS_ACTIVO = "1";
	public static final String IND_EXPRESS_INACTIVO = "0";

	public static final String IND_CHECK_ON = "1";
	public static final String IND_CHECK_OFF = "0";

	public static final String SICC_ESTADO_LIST = "SACestadosList";

	public static final String STO_PARAM_TIPO_CLASIFICACION = "STO_TIPO_CLASI_PEDID";

	public static final String MAE_TIPO_COMUNICACION_TELEFONO_CASA = "TF";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_MOVIL = "TM";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_TRABAJO = "TT";
	public static final String MAE_TIPO_COMUNICACION_MAIL = "ML";

	public static final String MAE_CLIENTE_NIVEL5_LIST = "maeClienteNivel5List";
	public static final String MAE_CLIENTE_NIVEL6_LIST = "maeClienteNivel6List";

	public static final String MAE_CLIENTE_NIVEL2CT_LIST = "maeClienteNivel2CTList";
	public static final String MAE_CLIENTE_NIVEL3CT_LIST = "maeClienteNivel3CTList";
	public static final String MAE_CLIENTE_NIVEL4CT_LIST = "maeClienteNivel4CTList";
	public static final String MAE_CLIENTE_NIVEL5CT_LIST = "maeClienteNivel5CTList";
	public static final String MAE_CLIENTE_NIVEL6CT_LIST = "maeClienteNivel6CTList";

	public static final String MAE_DTO_CLIENTE = "maeDtoCliente";
	public static final String MAE_IDENTIFICACION_TIPO_PERSONA = "P";
	public static final String MAE_IDENTIFICACION_TIPO_EMPRESA = "E";

	public static final String INC_CONCU_PARAMETROS_LIST = "incentivosConcursosParametrosList";
	public static final String INC_CONCU_PUNTAJES_LIST = "incentivosConcursosPuntajesList";

	public static final String INC_CONCU_HABILITADOS_LIST = "incentivosConcursosHabilitadosList";

	public static final String INC_CONCU_RECONOCIDOS_LIST = "incentivosConcursosReconocidosList";

	public static final String INC_MOTIVO_CARGA_LIST = "incentivosMotivosCargaList";

	public static final String INC_ARCHIVO_LIST = "incentivosArchivolist";
	public static final String REC_UNIDADES_INCOMPLETAS = "01";
	public static final String REC_NO_ENTREGO_UNIDADES = "02";
	public static final String REC_NO_UBICO_DOMICILIO = "03";
	public static final String REC_NO_ENCONTRO_CONSULTORA = "04";
	public static final String REC_RECEPCION_DETALLE = "05";
	public static final String REC_NO_REALIZO_POSTVENTA = "06";
	public static final String REC_MERCADERIA_RECEPCIONADA = "07";
	public static final String REC_ERRORES_POSTVENTA = "08";
	public static final String REC_BR_NO_ENTREGADA_TRANSPORTISTA = "09";

	public static final String MAE_MODULO_00 = "MOD00";
	public static final String MAE_MODULO_02 = "MOD02";
	public static final String MAE_MODULO_03 = "MOD03";
	public static final String MAE_MODULO_10 = "MOD10";
	public static final String MAE_MODULO_11 = "MOD11";
	public static final String MAE_MODULO_PR = "MODPR";

	public static final String MAE_VALID_CODCLIE = "VAL_CODCLIE";
	public static final String MAE_VALID_DOCIDENT = "VAL_DOCIDENT";
	public static final String MAE_VALID_PRESENT = "VAL_PRESENT";
	public static final String MAE_MOSTRAR_DIGITO_CONTROL = "MOS_DIGICTRL";

	public static final int MAE_CLIENTE_MODULO_02_BASE = 948;
	public static final int MAE_CLIENTE_MODULO_02_DIVISOR = 97;
	public static final int MAE_CLIENTE_MODULO_02_RESTA = 99;
	public static final String MAE_CLIENTE_MODULO_02_FACTOR = "13171923293137";

	public static final int MAE_CLIENTE_MODULO_03_BASE1 = 13;
	public static final int MAE_CLIENTE_MODULO_03_BASE2 = 448172;
	public static final String MAE_CLIENTE_MODULO_03_FACTOR = "121212";
	public static final String MAE_CLIENTE_MODULO_03_PREFIJO = "01";

	public static final String MAE_TIPO_COMUNICACION_TELEFONO_CASA_DIRECCION_ENTREGA = "TE";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_CELULAR_DIRECCION_ENTREGA = "CE";

	public static final String MAE_CLIENTE_TIPO_VINCULO_LIST = "maeClienteTipoVinculoList";

	public static final String MAE_DPTO_AVAL = "UBIGEO1";

	public static final String MAE_PROVINCIA_AVAL = "UBIGEO2";

	public static final String MAE_DISTRITO_AVAL = "UBIGEO3";

	public static final String OCR_INDICADORES_LIST = "ocrIndicadoresList";

	public static final String OCR_CODIGO_PLANTILLA_LIST = "ocrCodigoPlantillaList";
	public static final String OCR_PLANTILLA_LIST = "ocrPlantillaList";
	public static final String OCR_PLANTILLA_TIPO_SOLICITUD_LIST = "ocrPlantillaTipoSolicitudList";

	public static final String SICC_TIPO_VENTA_LIST = "siccTipoVentaList";

	public static final String STO_TIPO_APROBADO_SI = "SI";

	public static final String STO_TIPO_APROBADO_NO = "NO";

	public static final String STO_TIPO_APROBADO_TODOS = "";

	public static final String HIP_VINCULOS_REFERENCIAS_LIST = "hipVinculosReferenciasList";

	public static final String MAE_VALID_TIPO_VIA = "VAL_TIPOVIA";
	public static final String MAE_VALID_NUMERO_PRINCIPAL = "VAL_NROPPAL";
	public static final String MAE_VALID_UBIGEO = "VAL_UBIGEO";
	public static final String MAE_VALID_DIRECCION_ENTREGA = "VAL_DIRENT";
	public static final String MAE_VALID_COMPLETA_CEROS_IDENTIDAD = "VAL_IDENTVAR";
	public static final String MAE_VALID_SEGUNDO_DOCUMENTO_IDENTIDAD = "VAL_IDENTNIT";

	public static final String REC_CODIGO_VENTA = "0";

	public static final String REC_TIPO_OFERTA = "1";

	public static final String REC_CODIGO_CATALOGO = "2";

	public static final String REC_TIPO_OFERTA_LIST = "recTipoOfertaList";

	public static final String REC_CODIGO_CATALOGO_LIST = "recCodigoCatalogoList";

	public static final String REC_CODIGO_VENTA_OPERA_LIST = "recCodigoVentaOperaList";

	public static final String STO_ESTADOS_GESTION_LIST = "stoEstadosGestionList";

	public static final String COM_BONOS_LIST = "comBonosList";
	public static final String COM_DETALLE_BONOS_LIST = "comDetalleBonosList";

	public static final String REC_MOTIVO_ANULACION_LIST = "recMotivoAnulacionList";

	public static final String STO_CODIGO_ANULACION = "COD_ANUL";

	public static final String APE_PERIODO_ERROR = "200000";

	public static final String HIP_MEDIOS_CONTACTOS_LIST = "hipMediosContactosList";

	public static final String COM_COMIS_RECUP_PERDI_LIST = "comComisionPerdidasList";

	public static final String CODIGO_ESTADO_GP1 = "02";
	public static final String CODIGO_ESTADO_GP2 = "03";
	public static final String CODIGO_ESTADO_GP3 = "04";
	public static final String CODIGO_ESTADO_GP4 = "05";
	public static final String CODIGO_ESTADO_GP5 = "06";
	public static final String CODIGO_ESTADO_FACTURADAS = "03";
	public static final String CODIGO_ESTADO_ERROR = "07";
	public static final String CODIGO_ESTADO_RECHAZADAS = "08";
	public static final String CODIGO_ESTADO_SIN_VALIDAR = "09";

	public static final String INC_TIPO_CIERRE_DIARIO = "D";

	public static final String INC_TIPO_CIERRE_REGION = "R";

	public static final String INC_TIPO_CIERRE_ZONA = "Z";

	public static final String INC_TIPO_CIERRE_CAMPANA = "P";

	public static final String INC_TIPO_CIERRE_LIST = "incTipoCierreList";

	public static final String MANTENIMIENTO_RETAIL_PROCENTAJE_COMISION = "mantenimientoRETPorcentajeComisionList";

	public static final String STO_OPERACION_CODIGO_VENTA_LIST = "stoOperacionCodigoVentaList";

	public static final String STO_CANTIDAD_DEVUELVE_LIST = "stoCantidadDevuelveList";

	public static final String STO_CODIGO_VENTA_PEDIDO_LIST = "stoCodigoVentaPedidoList";

	public static final String STO_MATRIZ_PRECIOS = "1";

	public static final String STO_MATRIZ_INCENTIVOS = "2";

	public static final String STO_CODIGO_VENTA_MATRIZ_LIST = "stoCodigoVentaMatrizList";

	public static final String STO_NUMERO_REGISTROS_PAGINA = "stoNumeroRegistrosPagina";

	public static final String STO_REFERENCIA_OPERACION_LIST = "stoReferenciaOperacionList";

	public static final String INC_CONCU_PARAMETROS_ELECTIVOS_LIST = "mantenimientoINCConcursoElectivosList";

	public static final String INC_CONCU_PREMIOS_ELECTIVOS_LIST = "premiosElectivosList";

	public static final String REC_PRODUCTOS_FFNNEE_LIST = "recProductosFFNNEELits";

	public static final String REC_CODIGOS_SAP_VALIDOS_LIST = "recCodigosSAPValiadosList";

	public static final String LID_LIDERES_LIST = "lidLideresList";

	public static final String ZON_CODIGO_MODULO = "24";
	public static final String ZON_COD_MENSAJE_ASIGNACION_RESPONSABLE = "ZON03";

	public static final String STO_INDICADOR_PANTALLA_GESTION = "IND_PANT_GEST";
	public static final String STO_INDICADOR_PANTALLA_CONSULTA = "IND_PANT_CONS";
	public static final String STO_INDICADOR_PANTALLA_ELIMINACION = "IND_PANT_ELIM";

	public static final String STO_REPORTES_TIPO_DOCUMENTO_LIST = "stoReportesTipoDocumentoList";

	public static final String LID_CONCURSO_ACTIVO_LIST = "lideresConcursoActivosList";

	public static final String INC_CONCURSO_VIGENTES_CERRADOS_LIST = "incConcursoVigenteCerradoList";

	public static final String INC_TIPO_REPORTE_LIST = "incTipoReporteList";

	public static final String MEN_REUNION_GZ = "menGerenteZonalList";

	public static final String MEN_HORA_ACTIVIDAD_LIST = "horaActividadList";

	public static final String MEN_MINUTO_ACTIVIDAD_LIST = "minutoActividadList";

	public static final String MEN_TIEMPO_AM_PM_ACTIVIDAD_LIST = "tiempoActividadList";

	public static final String OCR_BLOQUEO_CONTROL_TODOS = "";

	public static final String OCR_BLOQUEO_CONTROL_SI = "SI";

	public static final String OCR_BLOQUEO_CONTROL_NO = "NO";

	public static final String OCR_RECHAZO_OCR_TODOS = "";

	public static final String OCR_RECHAZO_OCR_SI = "SI";

	public static final String OCR_RECHAZO_OCR_NO = "NO";

	public static final String OCR_FACTURADOS_TODOS = "";

	public static final String OCR_FACTURADOS_SI = "SI";

	public static final String OCR_FACTURADOS_NO = "NO";

	public static final String OCR_RECEPCION_PEDIDOS_LIST = "ocrRecepcionPedidosList";

	public static final String OCR_DETALLE_PEDIDO = "Detalle";

	public static final String OCR_DETALLE_PEDIDO_LIST = "ocrDetallePedidoList";

	public static final String SAC_REPORTE_SIN_FACTURAR = "Sin Facturar";

	public static final String SAC_REPORTE_TODOS = "Todos";

	public static final String MEN_MENSAJE_LIST = "msgMensajeList";

	public static final String MEN_ACTIVIDAD_LIST = "msgActividadList";

	public static final String MEN_PARAMETROS_LIST = "msgParametrosActividadList";

	public static final String MARCA_ESIKA = "ES";

	public static final String MARCA_LBEL = "LB";

	public static final String OCR_PRE_ALTERNATIVOS_AUTOMATICOS_LIST = "ocrPreAlternativosAutomaticosList";

	public static final String OCR_PRE_ALTERNATIVO_AUTOMATICO_ELIMINAR = "Eliminar";

	public static final String OCR_PRE_PRODUCTOS_ICE_LIST = "ocrPreProductosICEList";

	public static final String INC_CONCU_CODIGO_VENTAS_FICTI_LIST = "incCodigoVentasFicticioList";

	public static final String ESTADO_FLAG_CANCELACION_OTROS_CANALES_A = "A";
	public static final String ESTADO_FLAG_CANCELACION_OTROS_CANALES_1 = "1";

	public static final String REPORTE_SIC_GENERACION_PEDIDOS_DIGITADOS_REGION = "R";
	public static final String REPORTE_SIC_GENERACION_PEDIDOS_DIGITADOS_ZONA = "Z";
	public static final String REPORTE_SIC_PEDIDOS_DIGITADOS_SOLICITUD = "SOC";
	public static final String REPORTE_REC_MERCADERIA_SINIESTRADA = "C52";
	public static final String STO_LEVANTAMIENTO_ERRORES_CLIENTES_LIST = "stoLevantamientoErroresClientesList";
	public static final String STO_LEVANTAMIENTO_ERRORES_CLIENTES_LIST_PRINC = "stoLevantamientoErroresClientesListPrinc";

	public static final String PER_CONSO_ACUMDIA_LIST = "consultaPERAcumuladoDiaList";

	public static final String PER_NUME_COMPRO_AUTORIZADOS_SUNAT_LIST = "mantenimientoPERNumeracionComprobantesSunatList";

	public static final String SICC_TIPO_CDR_LIST = "siccTipoCDRList";
	public static final String SICC_TIPO_ATENCION_LIST = "siccTipoAtencionList";

	public static final String HIP_CONCURSOS_PREMIOS_ATENDIDOS_LIST = "hipConcursosPremiosAtendidosList";
	public static final String HIP_CONCURSOS_RECOMENDACIONES_LIST = "hipConcursosRecomendacionesList";

	public static final String HIP_TIPOS_VINCULOS_LIST = "hipTiposVinculosList";
	public static final String HIP_HISTORICO_VINCULOS_LIST = "hipHistoricoVinculosList";
	public static final Object HIP_OPCION_HISTORICO_VINCULOS = "HIP-18";
	
	public static final String EDU_CONEXION_EXTERNA_AS400 = "AS4";

	public static final String REC_IND_INGRESO_DIGITACION = "DIGITACION";
	public static final String REC_IND_INGRESO_ARCHIVO = "ARCHIVO";

	public static final String REC_PRODUCTOS_FFNNEE_ARCHIVO_LIST = "recProductosFFNNEEArchivoList";

	public static final String LOV_INDICADOR_PROCESO_GP3 = "GP3";
	public static final String LOV_INDICADOR_PROCESO_GP4 = "G";
	public static final String LOV_INDICADOR_PROCESO_CIERRE_ZONA = "Z";
	public static final String LOV_INDICADOR_PROCESO_CIERRE_REGION = "R";
	public static final String LOV_INDICADOR_PROCESO_CIERRE_CAMPANA = "P";

	public static final String REC_TIPO_DOCUMENTO_CABECERA = "CABECERA";
	public static final String REC_TIPO_DOCUMENTO_DETALLE = "DETALLE";

	public static final String STO_LONGITUD_NUMERO_DOCUMENTO = "longitudCampoNumeroDocumento";
	public static final String VAL_STO_LONGITUD_NUMERO_DOCUMENTO = "8";

	public static final String MAE_CODIGO_CLASIFICACION = "01";
	public static final String MAE_CODIGO_TIPO_CLASIFICACION = "21";
	public static final String MAE_CODIGO_SUBTIPO_CLIENTE = "04";

	public static final String MANTENIMIENTO_COM_RESPONSABLES_UA = "mantenimientoCOMResponsablesUAList";
	public static final String REPORTE_MAV_ESTADO_ATENCIONES = "reporteMAVEstadoAtencionesList";

	public static final String REPORTE_MAV_NO_PASARON_PEDIDOS = "reporteMAVNoPasaronPedidosList";

	public static final String SMS_TIPO_INTERFAZ_LIST = "smsTipoInterfazList";

	public static final String LID_REPORTE_PUNTAJE_TODAS_VARIABLES = "Todas las Variables";
	public static final String LID_REPORTE_PUNTAJE_TODAS_VARIABLES_VALOR = "06";

	public static final String CODIGO_CLASIFICACION_LIDERES = "L";

	public static final String PED_RESULTADO_CHEQUEO_CONSULTORA_LIST = "pedResultadoChequeoConsultoraList";
	public static final String PED_DETALLE_RESULTADO_CHEQUEO = "Detalle";
	public static final String PED_DETALLE_RESULTADO_CHEQUEO_LIST = "pedDetalleResultadoChequeoList";

	public static final String PED_TIPO_CLIENTE = "pedTipoCliente";

	public static final String STO_INDICADOR_ELIMINACION = "EL";

	public static final String PED_TIPO_CHEQUEO_LIST = "pedTipoChequeoList";
	public static final String PED_RESULTADO_CONSULTORA_CHEQUEAR_LIST = "pedResultadoConsultoraChequearList";
	public static final String EXTENSION_EXCEL = "XLS";
	public static final String EXTENSION_XML = "XML";
	public static final String EXTENSION_CSV = "CSV";
	public static final String CCC_CARGOS_ABONOS_LIST = "cccCargosAbonosList";
	public static final String CCC_ELIMINAR_CARGOS_ABONOS = "Eliminar";

	public static final String LID_CONDI_LIST = "lideresCondicionList";
	public static final String LID_PRODUCTOS_CANASTAS_LIST = "lidProductosCanastasList";

	public static final String LID_TIPO_OFER_LIST = "lidTipoOfertaList";

	public static final String REC_BLOQUEOS_CDR_LIST = "procesoRECBloqueoCDRList";

	public static final String OCR_WEBSERVICE_RESULTADO_ERROR = "-1";
	public static final String OCR_WEBSERVICE_RESULTADO_OK = "0";

	public static final String MAE_WEBSERVICE_RESULTADO_OK = "0";
	public static final String MAE_WEBSERVICE_RESULTADO_ERROR = "-1";
	public static final String MAE_WEBSERVICE_RESULTADO_ERROR2 = "-2";

	public static final String CUP_EXCEPCIONES_LIST = "cupExcepcionesList";

	public static final String TIPO_SOLICITUD_LIST = "tipoSolicitudList";

	public static final String STO_LONGITUD_UNIDADES_MAXIMA = "longitudUnidadesMaximas";

	public static final String CAR_NIVEL_RIESGO_SECCION_LIST = "carNivelRiesgoSeccionList";
	public static final String CAR_NIVEL_RIESGO_LIST = "carNivelRiesgoList";

	public static final String STO_CANTIDAD_DEVUELVE_DETALLE_LIST = "stoCantidadDevuelveDetalleList";

	public static final String PASES_LOG_LIST = "pasesLogList";
	public static final String PASES_RESULTADO_LIST = "pasesResultadoList";
	public static final String PASES_PAISES_MARCA_LIST = "pasesPaisesMarcaList";
	public static final String TIPO_PASE_CODIGO = "0";
	public static final String TIPO_PASE_MES = "1";

	public static final String SAC_TABLA_CONSOLIDADO = "INT_SOLIC_CONSO_CABEC";
	public static final String SAC_TABLA_HISTORICO = "PED_HISTO_SOLIC_CONSO_CABEC";

	public static final String STO_EXISTENCIA_CRONOGRAMA_OCC_LIST = "stoExistenciaCronogramaList";
	public static final String STO_VENCIMIENTO_CRONOGRAMA_OCC_LIST = "stoVencimientoCronogramaList";
	public static final String STO_VENCIMIENTO_CRONOGRAMA_SEGUNDO_CASO_OCC_LIST = "stoVencimientoCronogramaSegundoCasoList";
	public static final String STO_VENCIMIENTO_CRONOGRAMA_TERCER_CASO_OCC_LIST = "stoVencimientoCronogramaTercerCasoList";

	public static final String SAP_TIPO_CAMBIO_VENTAS = "VENTAS";
	public static final String SAP_TIPO_CAMBIO_ABONOS = "ABONOS";
	public static final String SAP_TIPO_CAMBIO_TODOS = "TODOS";

	public static final String SIC_ROL_LIST = "mantenimientoRolSICCList";

	public static final String SIC_USUARIO_LIST = "mantenimientoSICCUsuarioList";
	public static final String SIC_USUARIO_FLAG_BLOCKED = "flagUsuarioBlocked";

	public static final String SIC_CLAVE_DEFAULT = "{SHA} 07b93316eb59893f49fac94cb1aaca29f9ce9b70";

	public static final String SIC_ROL_USUARIO_LIST = "mantenimientoSICCRolUsuarioList";

	public static final String SIC_OPCIONES_LIST = "opcionesSICCList";
	public static final String SIC_USUROL_LIST = "usuarioRolSICCList";

	public static final String PRI_PREMIOS_ESTADO_CONSULTORA_LIST = "priPremiosEstadoConsultoraList";

	public static final String TIPO_DOCUMENTO_CONTABLE_LIST = "tipoDocumentoContableList";

	public static final String IND_NUMERO_CONTROL_DOCUMENTO_LEGAL = "indicadorNumeroControlDocumentoLegal";

	public static final String MAE_VALID_CARACTER_NOVALIDO1 = "VAL_CRT_NV1";
	public static final String MAE_VALID_CARACTER_NOVALIDO2 = "VAL_CRT_NV2";
	public static final String MAE_VALID_CARACTER_NOVALIDO3 = "VAL_CRT_NV3";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_REFERENCIA = "TR";

	public static final String MSG_PARAMETROS_PLANTILLA_LIST = "msgParametrosPlantillaList";
	public static final String MSG_PLANTILLA_LIST = "msgPlantillaList";
	public static final String MSG_VALOR_DEFECTO_LIST = "msgValoresDefectoList";
	public static final String MSG_VALOR_DEFECTO_POPUP_LIST = "msgValoresDefectoPopupList";
	public static final String MSG_PARAMETROS_MULTIPLE_PLANTILLA_LIST = "msgParametrosMultiplePlantillaList";
	public static final String MSG_VISTA_PLANTILLA_LIST = "msgVistaParametrosList";
	public static final String MSG_VISTA_PLANTILLA_POPUP_LIST = "msgVistaPopupParametrosList";
	public static final String MSG_POPUP_LIST = "msgListaPopup";
	public static final String MSG_PLANTILLA_CABEC_LIST = "msgPlantillaCabecList";
	public static final String MSG_PLANTILLA_DETAL_LIST = "msgPlantillaDetalList";
	public static final String MSG_PROCESO_LIST = "msgProcesoList";
	public static final String MSG_CONFIGURACION_PROCESO_LIST = "msgConfiguracionProcesoList";
	public static final String MSG_PROCE_DISPO_LIST = "msgProcesoDisponiblesList";
	public static final String MSG_PROCE_HABI_LIST = "msgProcesoHabilitadosList";

	public static final String TIPO_VARIANTE_LIST = "tipoVarianteList";
	public static final String PRE_CODIGO_VENTA_LIST = "preCodigoVentaList";
	public static final String PRE_FORMA_PAGO_LIST = "preFormaPagoList";
	public static final String PRE_TIPO_CLIENTE_LIST = "preTipoClienteList";
	public static final String PRE_SUB_TIPO_CLIENTE_LIST = "preSubTipoClienteList";
	public static final String PRE_TIPO_CLASIFICACION_LIST = "preTipoClasificacionList";
	public static final String PRE_CLASIFICACION_LIST = "preClasificacionList";
	public static final String PRE_ESTATUS_LIST = "preEstatusList";
	public static final String PRE_CUV_LIST = "preCUVList";
	public static final String PRE_INDICADOR_CUADRE_LIST="preIndicadorCuadreList";
	public static final String PRE_GRUPO_LIST="preGrupoList";
	public static final String PRE_TIPO_CUADRE_LIST="preTipoCuadreList";
	public static final String PRE_CRITERIOS_LIST="preCriteriosList";
	public static final String PRE_CATALOGOS_LIST="preCatalogosList";
	public static final String PRE_COMPONENTES_LIST="preComponentesList";
	public static final String PRE_TIPO_RANGO_R="R";
	public static final String PRE_TIPO_RANGO_P="P";
	
	public static final String PRE_CODIGO_FACTOR_CUADRE_GRUPO = "COD_FACT_CUAD";
	public static final String PRE_CODIGO_INDICADOR_CUADRE_GRUPO = "CUES_OID_IND_CUAD_TIPO_ESTR";
	
	public static final String PRE_CODIGO_TIPO_CUADRE_CONDICION = "ICPR_OID_INDI_CUAD_PROM";
	public static final String PRE_CODIGO_FACTOR_CUADRE_CONDICION = "ICPR_OID_INDI_CUAD_PROM";
	
	public static final String PRE_NOMBRE_MODIFICADO_RANGO = "RANGO";	

	public static final String EDU_PREREQ_CURSO_LIST = "eduCursoPrereqList";
	public static final String EDU_PREREQ_LIST = "mantenimientoCursoPrerequisitoList";

	public static final String MIC_TIPO_PORCESO_LIST = "micTipoProcesoList";
	public static final String MIC_PAQUETE_INTERFAZ_SALIDA = "MIC-P1";
	public static final String MIC_PAQUETE_INTERFAZ_ENTRADA = "MIC-P2";
	public static final String MIC_PAQUETE_INTERFAZ_SALIDA_IPM = "MIC-1";
	public static final String MIC_PAQUETE_INTERFAZ_SALIDA_ASE = "MIC-3";
	public static final String MIC_ALL_INTERFACES_ENTRADA = "micInterfacesEntradaList";
	public static final String MIC_ALL_INTERFACES_SALIDA = "micInterfacesSalidaList";
	public static final String MIC_ALL_INTERFACES_SALIDA_IPM = "micInterfacesSalidaIPMList";
	public static final String MIC_ALL_INTERFACES_SALIDA_ASE = "micInterfacesSalidaASEList";
	public static final String MIC_PROCESO_BATCH_GENERACION = "01";
	public static final String MIC_PROCESO_BATCH_ENVIO_IPM = "03";
	public static final String MIC_PROCESO_BATCH_RECEP = "04";
	public static final String MIC_PROCESO_BATCH_ENVIO_ASEG = "05";
	public static final String MIC_MANT_CRONOGRAMA_LIST = "micCronogramaList";
	public static final String MIC_TIPO_OPERACION_LIST = "micTipoOeracionlist";
	public static final String STO_IND_CALC_PED_CDR = "STO_IND_CALC_PED_CDR";
	public static final String REC_DIGI_CDRS_ORIG_CC = "C";
	public static final String REC_DIGI_CDRS_ORIG_DIG = "D";
	public static final String REC_DIGI_CDRS_ORIG_M = "M";

	public static final String MAE_CLASIFICACIONES_ARCHIVO_LIST = "clasificacionesArchivolist";

	public static final String OCR_REEMPLAZOS_LIST = "mantenimientoOCRReemplazosList";

	public static final String OCR_TIPO_CLIENTE_LIST = "ocrTipoCLienteList";
	public static final String OCR_TIPO_REEMPLAZO_LIST = "ocrTipoReemplazoList";
	public static final String OCR_SUBTIPO_CLIENTE_LIST = "ocrSubtipoClienteList";
	public static final String OCR_TIPO_CLASIFICACION_LIST = "ocrTipoClasificacionList";
	public static final String OCR_CLASIFICACION_LIST = "ocrClasificacionList";
	public static final String OCR_REGION_LIST = "ocrRegionList";
	public static final String OCR_ZONA_LIST = "ocrZonaList";
	public static final String STO_TIPO_VIA_LIST = "stoTipoViaList";

	public static final String MIC_MANT_SEGUROS_LIST = "micSegurosList";
	public static final String MIC_MANT_COBERTURA_LIST = "micCoberturaList";
	public static final String MIC_MANT_COBERTURA_GRUPOS_LIST = "micCoberturaGruposList";
	public static final String MIC_MANT_MICROSEGUROS_LIST = "micMicroSegurosList";
	public static final String MIC_MANT_BANCOS_LIST = "micBancosList";
	public static final String MIC_MANT_GRUPOS_LIST = "micGruposList";
	public static final String MIC_MANT_GRUPOSDETALLE_LIST = "micGruposDetalleList";
	public static final String MIC_TIPO_CLIENTE_LIST = "micTipoClientList";

	public static final String STO_TIPO_ORDEN_NORMAL = "N";
	public static final String STO_TIPO_ORDEN_ESPECIAL = "E";
	public static final String STO_TIPO_ORDEN_PRIMEROS_PEDIDOS = "P";
	public static final String STO_CLIENTE_TIPO_ORDEN = "clientesTipoOrdenList";
	public static final String STO_CODIGO_CLIENTE_LIST = "codigoClienteList";

	public static final String STO_TIPO_CLIENTE_LIST = "stoTipoCLienteList";
	public static final String STO_SUBTIPO_CLIENTE_LIST = "stoSubtipoClienteList";
	public static final String STO_TIPO_CLASIFICACION_LIST = "stoTipoClasificacionList";
	public static final String STO_CLASIFICACION_LIST = "stoClasificacionList";
	public static final String STO_REGION_LIST = "stoRegionList";
	public static final String STO_ZONA_LIST = "stoZonaList";
	public static final String STO_BLOQUEO_CONTROL_LIST = "stoBloqueoControlList";
	public static final String STO_VALOR_OID_NULL = "0";

	public static final String STO_TIPO_CLIENTE_FORM_LIST = "stoTipoCLienteFormList";
	public static final String STO_SUBTIPO_CLIENTE_FORM_LIST = "stoSubtipoClienteFormList";
	public static final String STO_TIPO_CLASIFICACION_FORM_LIST = "stoTipoClasificacionFormList";
	public static final String STO_CLASIFICACION_FORM_LIST = "stoClasificacionFormList";
	public static final String STO_REGION_FORM_LIST = "stoRegionFormList";
	public static final String STO_ZONA_FORM_LIST = "stoZonaFormList";
	public static final String NUMERO_ETAPA_PROCESO_BATCH_DEFAULT = "INICIAL";
	public static final String DESCRIPCION_RECEPCIONAR_OCR_PROCESO = "RECEPCIONAR OCR";
	public static final String DESCRIPCION_STO_VALIDACION_PROCESO = "STO VALIDACIONES";

	public static final String APE_COD_CENTRO_DISTRIBUCION_LIST = "apeCodCentroDistribucionList";
	public static final String APE_DES_CENTRO_DISTRIBUCION_LIST = "apeDesCentroDistribucionList";
	public static final String APE_CENTRO_DISTRIBUCION_LIST = "apeCentroDistribucionList";
	public static final String APE_NIVEL_OUTSOURCING_LIST = "apeNivelOutsourcingList";
	public static final String APE_ORDEN_LIST = "apeOrdenList";
	public static final String APE_ORDEN_LISTA_PICADO_LIST = "apeOrdenListaPicadoList";
	public static final String APE_AGRUPACION_DEFAULT_LIST = "apeAgrupacionDefaultList";
	public static final String APE_VISUALIZACION_CHEQUEO_LIST = "apeVisualizacionChequeoList";
	public static final String APE_AGRUPACION_OLAS_LIST = "apeAgrupacionOlasList";
	public static final String APE_ORDEN_ASCENDENTE = "A";
	public static final String APE_ORDEN_DESCENDENTE = "D";
	public static final String TABLA_CENTRO_DISTRIBUCION = "APP_CONFI_CENTR_DISTR";

	public static final String STO_INDICADOR_MENU = "STO_IND_MENU";

	public static final String APE_SISTEMA_PICADO_LIST = "apeSistemaPicadoList";
	public static final String APE_COD_CD_LIST = "apeCentroDistribucionList";
	public static final String APE_COD_LINEA_ARMADO = "apeLineaArmadoList";
	public static final String APE_NIVEL_AGRP_OLAS = "apeNivelAgrpOlasList";
	public static final String APE_UNIDAD_ADMIN_LINEA_LIST = "apeUnidadAdminLineaList";

	public static final String REC_OPERACION_PARAMETROS_LIST = "recOperacionParametrosList";

	public static final String STO_ELIMINAR_PEDIDOS_CLIENTES_LIST = "stoEliminarPedidosClientesList";
	public static final String STO_CONSULTAR_PEDIDOS_CLIENTES_LIST = "stoConsultarPedidosClientesList";

	public static final String APE_COD_DIST_LISTA = "apeCodCentDistList";
	public static final String APE_MAPA_CDIST_LIST = "apeMapaCDList";
	public static final String APE_COD_LIN_ARM_LIST = "apeCodLinArmadoList";
	public static final String APE_COD_SUBLIN_ARM_LIST = "apeCodSubLinArmadoList";
	public static final String APE_COD_MAPA_ZONA_LIST = "apeCodMapaZonaList";
	public static final String APE_TIPO_ANAQUEL_MAPACD_LIST = "apeTipoAnaquelMapaCDList";
	public static final String APE_TIPO_ANAQUEL_VALORESFIND = "apeTipoAnaquelValoresFind";
	public static final String APE_MANTENIMIENTO_ANAQUELES_MAPACD_LIST = "mantenimientoAnaquelesMapaCDList";
	public static final String APE_TIPO_ANAQUEL_COMBO_LIST = "apeTipoAnaquelComboList";

	public static final String APE_CENTRO_DIST_LIST = "apeCodCentroDistList";
	public static final String APE_DES_LINEA_ARMADO_LIST = "apeDesLineaArmadoList";
	public static final String APE_LINEA_ARMADO_LIST = "apeLineaArmadoList";
	public static final String TABLA_LINEA_ARMADO = "APE_LINEA_ARMAD";
	public static final String APE_PROG_CUBICAJE_LIST = "apeProgCubicajeList";
	public static final String APE_PLATAFORMA_LIST = "apePlataformaList";
	public static final String APE_USUA_ALARMA_LIST = "apeUsuaAlarmaList";
	public static final String APE_TIPO_SOLICITU_CONSO_LIST = "apeTipoSolicitudConsoList";

	public static final String ESTADO_ACTIVO_MAPAZONA = "apeEstadoActivoMapaZona";
	public static final String ESTADO_INACTIVO_MAPAZONA = "apeEstadoInactivoMapaZona";
	public static final String APE_CODDIST_LIST = "apeCodDistList";
	public static final String APE_MAPACDIST_LIST = "apeMapaCDDistList";
	public static final String APE_COD_LINARM_LIST = "apeCodLineaArmadoList";
	public static final String APE_COD_SUBLINEA_ARM_LIST = "apeCodSubLineaArmadoList";
	public static final String TABLA_MAPA_ZONA_CAB = "APE_MAPA_ZONA_CABEC";
	public static final String NRO_CERO = "0";
	public static final String NRO_UNO = "1";
	public static final String NRO_DOS = "2";
	public static final String NRO_TRES = "3";
	public static final String APE_MAPA_ZONALINEA_DET_LIST = "apeMapaZonaLineaDetList";
	public static final String APE_MAPA_ZONA_LIST = "apeMapaZonaDetList";
	public static final String APE_SUBLINEA_COMBOLIST = "apeSubLineaComboList";
	public static final String APE_OID_MAPA_ZONA = "";
	public static final String APE_MAPA_ZONA_DESCRIPCION = "apeMapaZonaDescripcion";
	public static final String APE_MAPA_ZONA_DEFAULT = "apeMapaZonaDefault";

	public static final String RUV_TIPO_DOCUMENTO_CONTABLE_LIST = "ruvTipoDocumentoContableList";
	public static final String RUV_ACCESO_LIST = "ruvAccesoList";
	public static final String RUV_SIN_IMPRESION_LIST = "ruvSinImpresionList";
	public static final String RUV_DUPLICADOS_LIST = "ruvDuplicadosList";
	public static final String RUV_SIN_ASIGNAR_LIST = "ruvSinAsignarList";
	public static final String RUV_NUMERO_REGISTROS_PAGINA = "ruvNumeroRegistrosPagina";
	public static final String RUV_SUBACCESO_LIST = "ruvSubAccesoList";

	public static final String APE_SUB_LINEA_ARMADO_LIST = "apeSubLineaArmadoList";
	public static final String TABLA_SUB_LINEA_ARMADO = "APE_SUBLI_ARMAD";
	public static final String TABLA_SISTEMA_PICADO = "APE_SISTE_PICAD";
	public static final String APE_COD_IMPRESORA_LIST = "apeCodImpresoraList";
	public static final String APE_TIPO_CAJA_SUBLINEA_LIST = "apeTipoCajaSubLineaList";
	public static final String APE_TIPO_CAJA_PRODUCTO_LIST = "apeTipoCajaProductoList";
	public static final String APE_LETRA_ANAQUEL_A = "A";
	public static final String APE_LETRA_ANAQUEL_B = "B";
	public static final String APE_LETRA_ANAQUEL_C = "C";
	public static final String APE_LETRA_ANAQUEL_D = "D";
	public static final String APE_LETRA_ANAQUEL_E = "E";
	public static final String APE_LETRA_ANAQUEL_F = "F";
	public static final String APE_LETRA_ANAQUEL_G = "G";
	public static final String APE_LETRA_ANAQUEL_H = "H";
	public static final String APE_LETRA_ANAQUEL_I = "I";
	public static final String APE_LETRA_ANAQUEL_J = "J";
	public static final String APE_LETRA_ANAQUEL_K = "K";
	public static final String APE_LETRA_ANAQUEL_L = "L";
	public static final String APE_LETRA_ANAQUEL_M = "M";
	public static final String APE_LETRA_ANAQUEL_N = "N";
	public static final String APE_LETRA_ANAQUEL_O = "O";
	public static final String APE_LETRA_ANAQUEL_P = "P";
	public static final String APE_LETRA_ANAQUEL_Q = "Q";
	public static final String APE_LETRA_ANAQUEL_R = "R";
	public static final String APE_LETRA_ANAQUEL_S = "S";
	public static final String APE_LETRA_ANAQUEL_T = "T";
	public static final String APE_LETRA_ANAQUEL_U = "U";
	public static final String APE_LETRA_ANAQUEL_V = "V";
	public static final String APE_LETRA_ANAQUEL_W = "W";
	public static final String APE_LETRA_ANAQUEL_X = "X";
	public static final String APE_LETRA_ANAQUEL_Y = "Y";
	public static final String APE_LETRA_ANAQUEL_Z = "Z";

	public static final String FAC_MES_LIST = "facMesesList";
	public static final String APE_COD_TIPO_ANAQUEL_LIST = "apeCodTipoAnaquelList";
	public static final String APE_TIPO_ANAQUEL_LIST = "apeTipoAnaquelList";
	public static final String TABLA_TIPO_ANAQUEL = "APE_TIPO_ANAQU";
	public static final String TABLA_TIPO_CHANEL = "APE_TIPO_CHANE";
	public static final String TABLA_DEFAULT_TIPO_ANAQUEL = "APE_DEFAU_TIPO_ANAQU";
	public static final String APE_IND_TIPO_DEFAULT_LIST = "apeIndTipoDefaultList";
	public static final String APE_TIPO_CHANEL_LIST = "apeTipoChanelList";
	public static final String APE_ANAQUEL_NORMAL = "0";
	public static final String APE_ANAQUEL_AFRAME = "1";

	public static final String APE_COD_MAPA_ORDEN_LIST = "apeCodMapaOrdenList";
	public static final String APE_MAPA_ORDEN_LINEA_LIST = "apeMapaOrdenLineaList";
	public static final String TABLA_MAPA_CENTRO_DISTRIBUCION_CAB = "APE_MAPA_CENTR_DISTR_CABEC";
	public static final String TABLA_ORDEN_ANAQUEL_CAB = "APE_ORDEN_ANAQU_CABEC";
	public static final String APE_SUBLINEA_ORDEN_LIST = "apeSublineaOrdenList";
	public static final String APE_ORDEN_DETALLE_LIST = "apeOrdenDetalleList";

	public static final String APE_CD_DIST_LISTA = "apeCentroDistList";
	public static final String APE_MAPA_CENTRODIST_LIST = "apeMapaCentroDistList";

	public static final String ESTADO_ANAQUEL_EXPANDIR = "E";
	public static final String ESTADO_ANAQUEL_DIVIDIR = "D";
	public static final String APE_MANTCENTRODIST_VALORES = "apeMantCentroDistValores";
	public static final String SICC_MARCA_LISTA = "siccMarcaLista";
	public static final String SICC_CANAL_LISTA = "siccCanalLista";
	public static final String APE_ANAQUEL_MAPACD_LIST = "apeAnaquelMapaCDList";
	public static final String APE_MANTENER_MAPACD_EXP_LIST = "apeMantMapaCDExpandirList";
	public static final String APE_CODANAQUELORIG_MAPACD_LIST = "apeCodAnaquelOrigMapaCDList";

	public static final String APE_CENTDIS_DIST_LISTA = "apeCentDistLista";
	public static final String APE_MAPA_CENTDIST_LIST = "apeMapaCentDistLista";
	public static final String APE_MAPA_ZONAORIGEN_LIST = "apeMapaZonaOrigList";
	public static final String APE_ORDEN_ANAQUEL_ORIGEN_LIST = "apeOrdenAnaquelOrigenList";
	public static final String APE_MAPA_CENTDIST_DESTINO_LIST = "apeMapaCentDistDestinoList";
	public static final String APE_MAPA_ZONADESTINO_LIST = "apeMapaZonaDestinoList";

	public static final String TABLA_TIPO_CAJA_PRODUCTO = "APP_TIPO_CAJA_PRODU";
	public static final String STO_MOTIVOS_DEVOLUCION_LIST = "stoMotivosDevolucionList";

	public static final String APE_COD_CD_CBALANCEOLIST = "apeCentroDistribucionCBList";
	public static final String APE_COD_LINEA_CBALANCEO_ARMADO = "apeLineaArmadoCBList";
	public static final String APE_CONFIGURACION_BALANCEO_LIST = "apeConfiguracionBalanceoList";
	public static final String APE_CODCDBALANCEO_LIST = "apeCodigoCDBalanceoList";
	public static final String APE_CODLINEA_BALANCEOLIST = "apeCodigoDistBalanceoList";
	public static final String APE_CODFUNCION_DIST_LIST = "apeCodigoFuncionDistList";

	public static final String INC_CONCURSOS_LIST = "incConcursosList";

	public static final String INTERFAZ_FILES = "interfazFiles";

	public static final String REC_TIPO_OPERACION_LIST = "recTipoOperacionList";

	public static final String REC_CODIGO_VENTA_LIST = "recCodigoVentaList";

	public static final String TABLA_TIPO_DISPENSACION = "APE_TIPO_DISPE";
	public static final String APE_TIPO_DISPENSACION_LIST = "apeTipoDispensacionList";
	public static final String APE_MATERIALES_VISTA_LIST = "apeMaterialesVistaList";
	public static final String TABLA_MAESTRO_PRODUCTO = "MAE_PRODU";

	public static final String APE_ASIGNACION_VERSION_LIST = "apeAsignacionVersionList";
	public static final String APE_VERSIONES_LIST = "apeVersionesList";
	public static final String APE_FUENTES_LIST = "apeFuentesList";

	public static final String APE_MANT_TIPO_ANAQUEL_LIST = "apeMantTipoAnaquelList";
	public static final String APE_MANT_NUM_ANAQUEL_LIST = "apeMantNumAnaquelList";
	public static final String APE_TIPO_CHANEL_COMBOLIST = "apeTipoChanelComboList";

	public static final String APE_EMITIR_ALARMA_LIST = "apeEmitirAlarmaList";
	public static final String APE_MAIL_BODY = "MailTxtEmitirAlarmaAPE.vm";

	public static final String APE_ASIGNACION_PRODUCTO_ANAQUEL_LIST = "apeAsignacionProductoAnaquelList";
	public static final String APE_ANAQUEL_DESTINO_LIST = "apeAnaquelDestinoList";

	public static final String CUP_PRODUCTO_PRIEMR_PEDIDO_LIST = "cupProductoPrimerPedidoList";

	public static final String APE_CBSL_COD_CD_LIST = "apeCBSLCodCDList";
	public static final String APE_CBSL_COD_LINEA_ARMADO = "apeCBSLCodLineaArmado";
	public static final String APE_CBSL_COD_SUBLIN_ARM_LIST = "apeCBSLCodSubLinArmList";
	public static final String APE_CBSL_SUBLIN_ARM_LIST = "apeCBSLSubLinArmList";
	public static final String APE_CBSL_CODFUNCION_DIST_LIST = "apeCBSLCodFuncionDistList";

	public static final String APE_FC_COD_MAE_MAGNI = "apeFCCodMaeMagni";
	public static final String APE_FC_COD_MAE_UNIDA_MEDIDORIG = "apeFCCodMaeUnidaMedidOrig";
	public static final String APE_FC_COD_MAE_UNIDA_MEDIDDEST = "apeFCCodMaeUnidaMedidDest";
	public static final String APE_FC_FACTORESCONVERSIONLIST = "apeFCFactoresConversionList";
	public static final String APE_FC_COD_MAE_MAGNI_FORM = "apeFCCodMaeMagniForm";
	public static final String APE_FC_COD_MAE_UNIDA_MEDIDORIG_FORM = "apeFCCodMaeUnidaMedidOrigForm";
	public static final String APE_FC_COD_MAE_UNIDA_MEDIDDEST_FORM = "apeFCCodMaeUnidaMedidDestForm";

	public static final String APE_COD_LINEA_ESTIMADO_VENTA = "apeLineaEstimadoVentaList";

	public static final String APE_ESTIMADO_PRODUCTO_LIST = "apeEstimadoProductoList";
	public static final String APE_ESTIMADO_PRODUCTO_BEAN = "apeEstimadoProductoBean";
	public static final String APE_PROC_ANUAL = "1";
	public static final String APE_DES_PROC_ANUAL = "Manual";

	public static final String APE_ASIGNA_TIPO_CAJA_LINEA_LIST = "apeAsignaTipoCajaLineaList";
	public static final String TABLA_TIPO_CAJA_EMBALAJE = "APE_TIPO_CAJA_EMBAL";
	public static final String APE_TIPO_CAJA_EMBALAJE_COMBOLIST = "apeTipoCajaEmbalajeComboList";

	public static final String APE_ALARMAS_VALORES_CUBICAJE_LIST = "apeAlarmasValoresCubicajeList";

	public static final String APE_TC_TIPOCAJACONSULTA_LIST = "apeTCTipoCajaConsultaList";
	public static final String APE_TC_TIPOCAJAPROD_COMBO_LIST = "apeTCTipoCajaProductoComboList";
	public static final String APE_TC_UNIDADMEDIDACAPA_COMBO_LIST = "apeTCUnidadMedidaCapaComboList";
	public static final String APE_TC_UNIDADMEDIDAEXTE_COMBO_LIST = "apeTCUnidadMedidaExteComboList";
	public static final String APE_TC_INDICADORCUBICAJE_UNO = "1";
	public static final String APE_TC_INDICADORCUBICAJE_CERO = "0";
	public static final String APE_TC_INDICADORCAJAMAES_UNO = "1";
	public static final String APE_TC_INDICADORCAJAMAES_CERO = "0";
	public static final String APE_TC_NOMBRETABLA = "APE_TIPO_CAJA_EMBAL";
	public static final String APE_FACTCAJ_CONSULTA_LIST = "apeFactCajConsultaList";
	public static final String APE_FACTCAJ_PRODUCTOS_LIST = "apeFactCajProductosList";
	public static final String APE_MAIL_ALARMA_PRODUCTO_BODY = "MailTxtEmitirAlarmaProductoAPE.vm";

	public static final String OCR_REEMPLAZOS_ACCION_ACTIVAR = "A";
	public static final String OCR_REEMPLAZOS_ACCION_DESACTIVAR = "D";

	public static final String APE_CONFIGURACION_TEXTOS_VARIABLES_LIST = "apeConfiguracionTextosVariablesList";
	public static final String APE_MAE_TIPO_CLIEN = "apeMaeTipoClien";
	public static final String APE_MAE_SUBTI_CLIEN = "apeMaeSubtiClien";
	public static final String APE_MAE_TIPO_CLASI_CLIEN = "apeMaeTipoClasiClien";
	public static final String APE_MAE_CLASI = "apeMaeClasi";

	public static final String APE_TIPO_ANAQUEL_PRODUCTO_LIST = "apeTipoAnaquelProductoList";

	public static final String RUC_BELCORP = "20100123763";
	public static final String SUFIJO_REPORTE_RUV_SUNAT = "00140100001111";
	public static final String PREFIJO_REPORTE_RUV_SUNAT = "LE";

	public static final String STO_EXCEPCIONES_PASE_FUERA_FECHA_LIST = "stoExcepcionesPaseFueraFechaList";

	public static final String TIPO_ACCION_INSERCION = "I";
	public static final String TIPO_ACCION_ELIMINACION = "E";

	public static final String ZON_MANT_TIPO_CARGO_LIST = "zonTipoCargoList";
	public static final String ZON_MANT_LICEN_LIST = "zonMotivoLicenciaList";
	public static final String ESTADO_LICENCIA = "LI";
	public static final String ESTADO_REEMPLAZO = "RZ";
	public static final String ESTADO_DIRECTORIO_ACTIVO = "A";
	public static final String ESTADO_INACTIVO_TEMPORAL = "IT";

	public static final String ZON_MANT_OPER_LIST = "zonOperacionesDirectorioList";

	public static final String APE_PASIG_VERSIONESORIG_LIST = "apePAsigVersionesOrigList";
	public static final String APE_PASIG_VERSIONESDEST_LIST = "apePAsigVersionesDestList";

	public static final String APE_CONTROL_ENVIO_INTERFACES_LIST = "apeControlEnvioInterfacesList";

	public static final String APE_INT_CODIGO_CENTRODIST = "apeIntCodigoCentroDist";

	public static final String APE_INT_CODIGO_MAPACD = "apeIntCodigoMapaCD";
	public static final String APE_INT_VERSIONES_LIST = "apeIntVersionesList";
	public static final String APE_INT_CONTROLENVIO_OBJECT = "apeIntControlEnvioObject";
	public static final String APE_INT_CONTROLENVIO_CODIGOCD = "apeIntControlEnvioCodigoCD";
	public static final String APE_INT_CONTROLENVIO_CODIGOLINEA = "apeIntControlEnvioCodigoLinea";
	public static final String APE_INT_EOLACENTRODIST = "apeIntEOCentroDist";
	public static final String APE_INT_EOLA_CODLINEA_ARMADO = "apeIntEOCodLineaArmado";

	public static final String INI_TAG_XML = "<";
	public static final String FIN_TAG_XML = ">";

	public static final String APE_CONTROL_BALANCEO_LIST = "apeControlBalanceoList";

	public static final String STO_TIPO_DOC_LIST = "stoTipoDocList";

	public static final String REPORTE_OCR_PEDIDOS_GP1 = "reporteOCRPedidosGP1SinErroList";

	public static final String PED_NIVEL_RIESGO = "pedNivelRiesgo";
	public static final String PED_NIVEL_RIESGO_LIST = "pedNivelRiesgoList";

	public static final String APE_EVALUAR_CARGA_LIST = "apeEvaluarCargaList";

	public static final String APE_COPIARASIGNAPRODUCTOS_LIST = "apeCopiarAsignaProductosList";

	public static final String FUNCION_DISTRIBUCION_AFRAME = "02";

	public static final String NRO_HITO_INCIO_DESPACHO = "15";

	public static final String REC_CRONOGRAMAS_BR_LIST = "recCronogramasBRList";

	public static final String PRE_ESTRATEGIA_LIST = "preEstrategiaList";

	public static final String STO_ESTADO_ENTREGA_ORDEN_TRANSPORTE_LIST = "stoEstadoEntregaOrdenTransporteList";
	public static final String STO_TIPO_ORDEN_TRANSPORTE_LIST = "stoTipoOrdenTransporteList";
	public static final String STO_NOVEDADES_ORDEN_TRANSPORTE_LIST = "stoNovedadesOrdenTransporteList";
	public static final String STO_CALIFICACIONES_ORDEN_TRANSPORTE_LIST = "stoCalificacionesOrdenTransporteList";

	public static final String[] STO_HORA_LIST = { "01", "02", "03", "04",
			"05", "06", "07", "08", "09", "10", "11", "12" };
	public static final String[] STO_MINUTO_LIST = { "00", "01", "02", "03",
			"04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
			"26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
			"37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
			"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58",
			"59" };
	public static final String[] STO_TIPO_HORARIO_AM_PM_LIST = { "AM", "PM" };
	public static final String STO_HORA_ORDEN_TRANSPORTE_LIST = "stoHoraOrdenTransporteList";
	public static final String STO_MINUTO_ORDEN_TRANSPORTE_LIST = "stoMinutoOrdenTransporteList";
	public static final String STO_TIPO_HORARIO_AM_PM_ORDEN_TRANSPORTE_LIST = "stoTipoHorarioAMPMOrdenTransporteList";
	public static final String STO_CLASIFICACIONES_ORDEN_TRANSPORTE_LIST = "stoClasificacionesOrdenTransporteList";

	public static final String STO_CENTRO_ACOPIO_LIST = "stoCentroAcopioList";
	public static final String STO_COMPANIAS_TRANSPORTE_LIST = "stoCompaniasTransporteList";

	public static final String INC_CARGA_RETAIL_ECM_LIST = "incCargaRetailECMList";

	public static final String STO_ZONA_ARRIBO_LIST = "stoZonaArriboByDocumento";

	public static final String STO_BENEFICIO_DEUDA_LIST = "stoBeneficioDeudaList";

	public static final String STO_EXCEPCION_VALIDA_DEUDA_LIST = "stoExcepcionValidaDeudaList";

	public static final String APE_ERROR_CUBICAJE_LIST = "apeErrorCubicajeList";

	public static final String INC_PERIODOS_NO_ATENDIDOS_LIST = "incPeriodosNoAtendidosList";
	public static final String INC_PRODUCTOS_NO_ATENDIDOS_LIST = "incProductosNoAtendidosList";

	public static final String APE_DESCRIPCION_ERROR_PRODUCTO_ANAQUEL_LIST = "apeDescripcionErrorProductoAnaquelList";
	public static final String APE_DESCRIPCION_ERROR_ANAQUEL_LIST = "apeDescripcionErrorAnaquelList";

	public static final String OCC_15 = "OCC-15";
	public static final String OCC_37 = "OCC-37";

	public static final String APP_REGION_ZONA_SEC_LIST = "procesoAPPSecuenciarZonaTerritorioFormList";
	public static final String APP_REGION_ZONA_SEC_POPUP_LIST = "procesoAPPSecuenciarZonaTerritorioPopupFormList";
	public static final String APP_ZONA_SIN_SECUENCIAR_LIST = "procesoAPPZonaSinSecuenciarList";
	public static final String APP_TERRITORIO_SIN_SECUENCIAR_LIST = "procesoAPPTerritorioSinSecuenciarList";

	public static final String PEJ_CARGA_CONSULTORAS_NIVEL_1_ERRORES_LIST = "pejCargaConsultorasNivel1ErroresList";
	public static final String PEJ_CARGA_CONSULTORAS_NIVEL_1_LIST = "consultorasNivel1List";
	public static final String PEJ_CARGA_META_EJECUTIVAS_INGRESOS_PEDIDOS_LIST = "pejCargaMetaEjecutivasIngresosPedidosList";
	public static final String PEJ_CARGA_META_EJECUTIVAS_INGRESOS_PEDIDOS_ERRORES_LIST = "pejCargaMetaEjecutivasIngresosPedidosErroresList";

	public static final String MAE_VALID_BARRIO = "VAL_BARRIO";

	public static final String STO_CONTROL_DEVOLUCIONES_LIST = "stoControlDevolucionesList";

	public static final String APE_DES_ERROR_MODIFICAR_MATERIALES_LIST = "apeDescErrorModificarMaterialesList";

	public static final String SICC_GRUPO_FACTURACION_LIST = "siccGrupoFacturacionList";

	public static final String PEJ_PORCENTAJE_COMISION_LIST = "pejPorcentajeComisionList";
	public static final String PEJ_PORCENTAJE_COMISION_DETAL_LIST = "pejPorcentajeComisionDetalList";
	public static final String PEJ_NIVEL_LIST = "pejNivelList";

	public static final String APE_PRODUCTOS_PENDIENTES_POR_ASIGNACION_LIST = "apeProductosPendientesPorAsignacionList";

	public static final String PEJ_NIVELES_LIST = "pejNivelesList";

	public static final String SICC_TIPO_CDR_RECLAMOS_LIST = "recTipoCDRList";

	public static final String MAE_TIPO_CUTIS_LIST = "maeTipoCutisList";

	public static final String STO_PARAM_MSG_GERE = "STO_PARAM_MSG_GERE";

	public static final String STO_PARAM_MSG_NDOC = "STO_PARAM_MSG_NDOC";

	public static final String STO_PARAM_MSG_CDIG = "STO_PARAM_MSG_CDIG";

	public static final String STO_HORAS_FACTURACION_LIST = "stoHorasFacturacionList";

	public static final String PEJ_ETAPAS_LIST = "pejEtapasList";

	public static final String DAT_PARAM_STA_CAMP_CERO = "0";

	public static final String DAT_PARAM_STA_CAMP_UNO = "1";

	public static final String DAT_PARAM_IND_CAMP_ACT_CERO = "0";

	public static final String DAT_PARAM_IND_CAMP_ACT_UNO = "1";

	public static final String CUP_PROGRAMA_OBLIGATORIO_SI = "1";
	public static final String CUP_PROGRAMA_OBLIGATORIO_NO = "0";

	public static final String PEJ_CANASTAS_LIST = "pejCanastasList";

	public static final String RUV_ENTIDAD_SEG_ACESS = "SEG_ACCES";
	public static final String RUV_ENTIDAD_SEG_SUBAC = "SEG_SUBAC";
	public static final String RUV_DOCLEGALES_LIST = "ruvDocLegalesList";
	public static final String RUV_DOCINTERNOS_LIST = "ruvDocInternosList";
	public static final String RUV_CANTIDAD_FILASPARAM_PAGINA_LEGAL = "ruvCantidadFilasParamPaginaLegal";
	public static final String RUV_CANTIDAD_FILASPARAM_PAGINA_INTERNO = "ruvCantidadFilasParamPaginaInterno";

	public static final String MAV_GERENTES_LIST = "mavGerentesList";
	public static final String MAV_REMISIONES_DIA_LIST = "mavRemisionesDiaList";
	public static final String MAV_ABASTECIMIENTO_MATERIAL_LIST = "mavAbastecimientoMaterialList";
	public static final String MAV_HISTORICO_MATERIAL_LIST = "mavHistoricoMaterialList";
	public static final String MAV_ARMADO_GENERAL_LIST = "mavArmadoGeneralList";
	public static final String MAV_ARMADO_GENERAL_DETALLE_LIST = "mavArmadoGeneralDetalleList";
	public static final String MAV_PLANILLA_ENTREGA_MATERIAL_LIST = "mavPlanillaEntregaMaterialList";

	public static final String RUV_Pestana_ContableLegalTab = "datosContableLegalTab";
	public static final String RUV_Pestana_ContableInternoTab = "datosContableInternoTab";
	public static final String RUV_Pestana_Contable = "RUVpestanaContable";
	public static final String RUV_Ditch_Tab_Ditch_Unfocused = "ditch-tab ditch-unfocused";
	public static final String RUV_Ditch_Tab_Ditch_Focused = "ditch-tab ditch-focused";
	public static final String RUV_Ditch_Tab_Focus_Legal = "RUVDitchTabFocus_Legal";
	public static final String RUV_Ditch_Tab_Focus_Interno = "RUVDitchTabFocus_Interno";

	public static final String CUP_LOGROS_LIST = "cupLogrosList";
	public static final String CUP_TIPO_LOGROS_LIST = "cupTipoLogrosList";
	public static final String CUP_MEDIOS_CAPTURA_LIST = "cupMediosCapturaList";
	public static final String CUP_LOGROS_SISTEMA_COMERCIAL = "C";
	public static final int CUP_LOGROS_NUMERO_PERIODOS = 3;

	public static final String LID_PARAMETROS_CONCURSO_LIST = "lidParametrosConcursoList";
	public static final String LID_INCREMENTO_PEDIDO_LIST = "lidIncrementoPedidoList";
	public static final String LID_META_SEGUNDO_PEDIDO_LIST = "lidMetaSegundoPedidoList";
	public static final String LID_RANGO_PEDIDO_LIST = "lidRangoPedidoList";
	public static final String LID_META_PEDIDO_LIST = "lidMetaPedidoList";
	public static final String LID_RANKING_PEDIDO_LIST = "lidRankingPedidoList";
	public static final String LID_PERIODO_ACTUAL = "lidPeriodoActual";
	public static final String LID_PREMIO_CAMPANIA_LIST = "lidPremioCampaniaList";
	public static final String LID_NUMERO_CONCURSO_LIST = "lidNumeroConcursoList";
	public static final String LID_PREMIO_CONCURSO_LIST = "lidPremioConcursoList";

	public static final String PEJ_PROGRAMA_EJECUTIVAS_LIST = "pejProgramaEjecutivasList";

	public static final String HIP_MOTIVO_BLOQUEO_ACTUALIZACION_DATOS = "AD";
	public static final String REC_INDICADOR_VALIDA_DEVOLUCION = "STO_IND_VALDEV";
	public static final String REC_INDICADOR_VALIDA_FALTANTES = "STO_IND_VALFAL";
	public static final String REC_INDICADOR_VALIDA_CAMBIOS = "STO_IND_VALCAM";

	public static final String ZON_NOVEDADES_LIST = "zonNovedadesList";
	public static final String ZON_PARAMETROS_RUTAS_LIST = "zonParametrosRutasList";
	
	public static final String ZON_LICENCIA_LIST = "zonLicenciaList";

	public static final String STO_LINEA_DEFECTO = "lineaDefecto";
	public static final String STO_LINEA_MAXIMA = "lineaMaxima";

	public static final String STO_CODIGO_SISTEMA = "STO";
	public static final String STO_PROC_BATC_OCC = "01";

	public static final String LET_PARAMETROS_CONCURSO_LIST = "letParametrosConcursoList";
	public static final String LET_NIVEL_CAMPANIA_LIST = "letNivelCampaniaList";
	public static final String LET_NIVEL_CONCURSO_LIST = "letNivelConcursoList";
	public static final String LET_RANGO_PEDIDO_LIST = "letRangoPedidoList";
	public static final String LET_PERIODO_ACTUAL = "letPeriodoActual";
	public static final String LET_NIVEL_CAMPANIA_CONSULT = "letNivelCampaniaConsult";
	public static final String LET_NIVEL_CONCURSO_CONSULT = "letNivelConcursoConsult";
	public static final String LET_RANGO_PEDIDO_CONSULT = "letRangoPedidoConsult";
	public static final String LET_PREMIO_CAMPANIA_LIST = "letPremioCampaniaList";
	public static final String LET_NUMERO_CONCURSO_LIST = "letNumeroConcursoList";
	public static final String LET_PREMIO_CONCURSO_LIST = "letPremioConcursoList";
	public static final String LET_PEDIDOS_OBJETIVOS_ERRORES_LIST = "letPedidosObjetivosErroresList";
	public static final String LET_PEDIDOS_OBJETIVOS_LIST = "letPedidosObjetivosList";
	
	public static final String APP_CARGA_HOMOLOGACION_ERRORES_LIST = "appCargarHomolYobelErroresList";

	public static final String VERIFICAR_TERMINAR_REPORTE = "verificarTerminarReporte";

	public static final String LISTA_REPORTES_EN_EJECUCION = "reportesEnEjecucionList";

	public static final String CODIGO_MENU_ACTIVO = "codigoMenuActivo";

	public static final String LET_LIDERES_LIST = "letLideresList";

	public static final String LET_TIPO_CIERRE_REGION = "R";

	public static final String LET_TIPO_CIERRE_CAMPANHA = "C";
	
	public static final String GEN_INTERFACES_PAQUETE = "genInterfazPaquete";

	public static final String INC_INTERFACES_PAQUETE = "incInterfazPaquete";

	public static final String LET_INTERFACES_PAQUETE = "letInterfazPaquete";

	public static final String STO_CAMBIODOCIDEN_DNI = "SAD-13";

	public static final String STO_CAMBIODOCIDEN_RUC = "SAD-14";

	public static final String EXTENSION_ARCHIVO_TXT = "TXT";

	public static final String STO_INDICADOR_ACTIVA_PARAM = "indicadorActivacion";

	public static final String ZON_MAIL_INGR_BODY = "MailTxtMantenimientoZONIngresoDirectorio.vm";

	public static final String ZON_MAIL_NOVE_BODY = "MailTxtProcesoZONNovedadCambioCargo.vm";

	public static final String ZON_MAIL_RETI_BODY = "MailTxtProcesoZONRetiroPersona.vm";
	
	public static final String ZON_MAIL_ROTA_BODY = "MailTxtProcesoZONRotacionPersona.vm";
	
	public static final String ZON_MAIL_LICE_BODY = "MailTxtProcesoZONLicencia.vm";

	public static final String STO_RETORNA_DETALLE_OFERTA_LIST = "stoRetornaDetalleOfertaList";

	public static final String ZON_LICENCIAS_ACTIVAS_LIST = "zonLicenciasActivasList";

	public static final String PED_LOTE_FIJO_DIGIT_PEDIDOS = "00000000";

	public static final String STO_FACTURA_ADICIONAL_LIST = "stoFacturaAdicionalList";

	public static final String CDR_NOMBRE_TABLA_TIPO_OFER = "PRE_TIPO_OFERT";

	public static final String CDR_OID_IDIOMA = "1";
		
	public static final String CDR_NOMBRE_TABLA_PED_TIPO_SOLIC = "PED_TIPO_SOLIC";
	
	public static final String STO_RETORNA_DETALLE_MOVI_LIST = "stoRetornaDetalleMoviList";	
	
	public static final String ZON_APROBAR_OPERACION_LIST = "zonAprobarOperacionList";
	
	public static final String ZON_APROBAR_OPERACION_TIPO = "zonAprobarOperacionTipo";
	
	public static final String MEN_ESCALERA_GANANCIA_LIST = "msgEscaleraGananciaList";

	public static final String PREFIJO_EGA = "EGA";

	public static final String MEN_CODIGO_VENTA_LIST = "msgMensajeCodigoVentaList";

	public static final String MEN_CODIGO_VENTA_DETALLE_LIST = "msgDetalleMensajeCodigoVentaList";
	
	public static final String CODIGO_SISTEMA_RUV = "RUV";

	public static final String INT_SEG_SOCIEDAD_LIST = "intSegSociedadList";

	public static final String RUV_INDICADOR_ELIMNAR_DOC_CONTA = "indEliminarDocCont";	

	public static final String STO_LIMITE_VENTA_FOCALIZADO_CONSEJERA_LIST = "stoLimiteVentaFocalizadoConsejeraList";
	
	public static final String STO_LIMITE_VENTA_FOCALIZADO_CONSEJERA_REG_ANULADO = "0";
	public static final String SGR_MANT_POLIZA_LIST = "sgrPolizaList";
	
	public static final String SGR_TAB_POLIZA_VIGENCIA = "vigenciaTab-tab";
	public static final String SGR_TAB_POLIZA_KIT = "kitTab-tab";
	public static final String SGR_TAB_POLIZA_DESCUENTO = "descuentoTab-tab";
	public static final String SGR_TAB_POLIZA_ESTATUS = "estatusTab-tab";
	public static final String SGR_TAB_POLIZA_CAMPANIAS_GRATUITAS = "campaniasGratuitasTab-tab";
	public static final String SGR_BENEFICIARIOS_LIST = "sgrBeneficiariosList";
	
	public static final String SGR_ESTATUS_LIST = "sgrEstatusList";
	
	public static final String HIP_SOLICITUDES_POLIZA_LIST = "hipSolicitudesPolizaList";
	public static final String HIP_HISTORICO_CARGOS_POLIZA_LIST = "hipHistoricoCargosPolizaList";
	public static final Object HIP_OPCION_SOLICITUDES_POLIZA = "HIP-19";
	
	public static final String SGR_MANT_INSCRIPCION_POLIZA_LIST = "sgrMantenimientoInscripcionList";

	public static final String MAE_VALID_CARACTER_DOCUMENTO_IDENTIDAD = "VAL_CRT_IDENT";
	public static final String VAL_IDENT_CERO = "VAL_IDENT_CERO";

	public static final String ZON_UNIDADES_ADMINISTRATIVAS_LIST = "zonUnidadesAdministrativasList";
	
	public static final String MAE_OTRAS_MARCAS_LIST = "maeOtrasMarcasList";
    
	public static final String REC_ANULACIONES_ATENCIONES_LINEA_DEFECTO = "lineaDefecto";
	public static final String REC_ANULACIONES_ATENCIONES_NRO_LINEAS_DEFECTO = "10";
	public static final String REC_ANULACIONES_ATENCIONES_LIST = "recAnulacionesAtencionesList";
	
	public static final String REC_ANULACIONES_PARAMETROS_SERVIDOR = "servidorFtpIA";
	public static final String REC_ANULACIONES_PARAMETROS_PUERTO = "puertoFtpIA";
	public static final String REC_ANULACIONES_PARAMETROS_USUARIO = "usuarioFtpIA";
	public static final String REC_ANULACIONES_PARAMETROS_PASSWORD = "passwordFtpIA";
	public static final String REC_ANULACIONES_PARAMETROS_SID = "sidFtpIA";
	public static final String REC_ANULACIONES_PARAMETROS_TIMER = "timerEjecucionIA";
	public static final String REC_ANULACIONES_PARAMETROS_PAIS = "paisFtpIA";
	
	public static final String REC_ANULACIONES_PARAMETROS_URL = "jdbc:oracle:thin:@";
	public static final String REC_ANULACIONES_PARAMETROS_TIPO_CONEXION_EXTERNA="ORA";

	public static final String REC_DETALLE_ATENCIONES_LIST = "recDetalleAtencionesList";
	public static final String REC_DETALLE_ANULACIONES_LIST = "recDetalleAnulacionList";
	
	public static final String RUV_NUMCONTROL_LIST = "ruvNumControlList";
	public static final String RUV_Pestana_ContableControlTab = "datosContableControlTab";
	public static final String RUV_Ditch_Tab_Focus_Control = "RUVDitchTabFocus_Control";
	public static final String RUV_CANTIDAD_FILASPARAM_PAGINA_CONTROL = "ruvCantidadFilasParamPaginaControl";
	public static final String RUV_NUMCON_DUPLICADOS_LIST = "ruvNumConDuplicadosList";
	public static final String RUV_SIN_ASIGNAR_NUMCON_LIST = "ruvSinAsignarNumConList";
	public static final String RUV_SECCION_BUSQUEDA_VAR = "ruvSeccionBusquedaVar";
	public static final String RUV_CANTIDAD_NULOS_ASIGNAR = "ruvCantidadNulosAsignar";
	public static final String RUV_SECCION_BUSQUEDA_1 = "1";
	public static final String RUV_SECCION_BUSQUEDA_2 = "2";
	public static final String RUV_SECCION_BUSQUEDA_3 = "3";
	public static final String RUV_SECCION_BUSQUEDA_4 = "4";
	public static final String RUV_SECCION_BUSQUEDA_5 = "5";
	public static final String RUV_SECCION_BUSQUEDA_6 = "6";
	public static final String RUV_SECCION_BUSQUEDA_7 = "7";
	public static final String RUV_SECCION_BUSQUEDA_8 = "8";
	public static final String RUV_SECCION_BUSQUEDA_9 = "9";
	public static final String RUV_SECCION_BUSQUEDA_10 = "10";
	
	public static final String INC_INICIO_CAMPANHA_INTERFACES_PAQUETE = "incInicioCampanhaInterfazPaquete";
	
	public static final String INC_CONCU_CREADOSVIGENTES_LIST = "incentivosConcursosCreadosVigentesList";
	public static final String INC_ERRORES_PREMIOS_LIST = "incentivosErroresPremioslist";
	
	public static final String RUV_ASIGNULO_NUMCON_LIST = "ruvAsignarNulosNumConList";
	
	public static final String STO_GENER_ESTR_GEO = "STO_GENER_ESTR_GEO";
	
	public static final String PED_TIPO_DOC_LIST = "pedTipoDocList";

	public static final String INC_ESTRATEGIA_INDIVIDUAL = "001";
	
	public static final String STO_INDICADOR_BLOQUEO_CAMPANHA = "indBloqueoCampana";
	
	public static final String APP_SECUENCIA_ZONA_LIST = "appSecuenciaZonaList";
	public static final String OPCION_MENU_ROL_RESETEAR_SEC = "00032";
	public static final String OPCION_MENU_ROL_ENVIAR_MAIL = "00033";
	public static final String OPCION_MENU_ROL_ANHADIR_TERR = "00034";
	public static final String OPCION_MENU_ROL_RESETEAR_TERR = "00035";
	
	public static final String STO_ACCI_VALI_MASI = "VAM";
	public static final String STO_ACCI_VALI_AUTO = "VAA";
	public static final String STO_ACCI_VALI_ON_LINE = "VAO";
	public static final String STO_ACCI_VALI_LINEA = "VAL";
	public static final String STO_ACCI_ELIM_SELE = "ELS";
	public static final String STO_ACCI_ELIM_ONLI = "ELO";
	public static final String STO_ACCI_ELIM_LINEA = "ELL";
	public static final String STO_ACCI_RECU_RECH = "RR";
	public static final String STO_BENEFICIO_DEUDA_SEARCH = "stoBenefioDeudaSearch";
	
	public static final String FAC_MANT_CIERRE_LIST = "facMantenimientoCierreList";
	public static final String FAC_TAB_CIERRE_REGION = "regionTab-tab";
	public static final String FAC_TAB_CIERRE_ZONA = "zonaTab-tab";
	
	public static final String IND_MAX_HILO_INTERFAZ = "indMaxHiloInterfaz";
	public static final String IND_MAX_HILO_PROCESO_BATCH = "indMaxHiloProcesoBatch";
	public static final String TIME_WAIT_HILO_NIVEL = "timeWaitHiloNivel";
	
	public static final String STO_PROC_BATC_FAS = "10";
	public static final String CODIGO_ESTADO_FAS_ERROR = "02";
	public static final String CODIGO_ESTADO_FAS_RECHAZADAS = "03";
	public static final String CODIGO_ESTADO_FAS_ENVIADAS = "04";
	
	public static final String SAP_CODIGO_TIPO_REPORTE_LIST = "sapCodigoTipoReporteList";
	public static final String SAP_TIPO_REPORTE_MATERIAL_PROM = "M";
	public static final String SAP_TIPO_REPORTE_PROMO_USUARIO = "P";
	public static final String SAP_TIPO_REPORTE_VENTA_LINEA = "V";
	public static final String SAP_TIPO_REPORTE_ROL_SOCIAL = "S";
	public static final String SAP_TIPO_OPERACION_VENTA = "T";
	public static final String SAP_TIPO_OPERACION_DEVOLUCION = "D";
	public static final String SAP_TIPO_OPERACION_VENTA_TO87 = "T87";

	public static final String APE_PRODU_IND_CAJA_LIST = "apeProduIndCajaList";
	public static final String APE_PRODU_INDICADOR_LIST = "apeProduIndicadorList";
	
	
	public static final String LET_ZONAS_REZONIFICADAS_LIST = "letZonasRezonificadasList";
	public static final String LET_REGIONES_CERRDAS_LIST = "letRegionesCerradasList";	

	public static final String OCR_DATOS_CLIENTE_ESCANEADOS_SC_LIST = "ocrDatosClienteEscaneadosSCList";
	public static final String OCR_TIPO_CODIGO_CLIENTE_ESCANEADOS_SC = "C";
	public static final String OCR_TIPO_DOC_IDENTIDAD_ESCANEADOS_SC = "D";
	public static final String OCR_CARPETA_MASIVA = "ocrCarpetaMasiva";
	public static final String MEN_DOCUMENTO_LIST = "msgMensajeDocumentoList";
	public static final String MEN_PATRON_LIST = "msgPatronMensajeList";

	public static final String MEN_TAB_CONSIDERACION = "consideracionTab-tab";
	public static final String MEN_TAB_RESTRICCION = "restriccionTab-tab";

	public static final String MEN_SECCION_LIST = "msgSeccionList";
	public static final String MEN_MODULO_LIST = "msgModuloList";

	public static final String MEN_CONSIDERACION_LIST ="msgConsideracionList";
	public static final String MEN_RESTRICCION_LIST =  "msgRestriccionList";
	public static final String MEN_MENSAJE_PATRON_LIST = "msgMensajePatronList";

	public static final String MEN_TIPO_CONSIDERACION = "C";
	public static final String MEN_TIPO_RESTRICCION = "R";
	public static final String MEN_TIPO_SIN_CONDICION = "S";
	public static final String MEN_TIPO_UNA_CONDICION = "U";	
	public static final String MEN_TIPO_UNA_CONDICION_DOBLE = "D";
	public static final String MEN_TIPO_LISTA_CONDICION = "L";
	public static final String MEN_TIPO_LISTA_EXTERNA_CONDICION = "E";
	public static final String MEN_ESTADO_TMP_INSERTAR = "3";
	public static final String MEN_ESTADO_TMP_ELIMINAR = "4";
	
	public static final int MEN_CONRES_CODIGO_PREMIO = 2004;
	public static final int MEN_CONRES_CODIGO_VENTA =  2005;
	public static final int MEN_CONRES_CUV_REEMPLAZO = 2007;
	public static final int MEN_CONRES_ESTATUS_CLIENTE = 2008;
	public static final int MEN_CONRES_LISTA_CONSU = 2010;
	public static final int MEN_CONRES_CLASIFICACIONES_CLIENTE = 2013;
	public static final int MEN_CONRES_UNIDAD_ADM = 2014;
	public static final int MEN_CONRES_CUV_FALTANTE = 2029;

	public static final int MEN_CONRES_CODIGO_PREMIO_REST = 2018;
	public static final int MEN_CONRES_CODIGO_VENTA_REST =  2019;
	public static final int MEN_CONRES_CUV_REEMPLAZO_REST = 2021;
	public static final int MEN_CONRES_ESTATUS_CLIENTE_REST = 2022;
	public static final int MEN_CONRES_LISTA_CONSU_REST = 2024;
	public static final int MEN_CONRES_CLASIFICACIONES_CLIENTE_REST = 2027;
	public static final int MEN_CONRES_UNIDAD_ADM_REST = 2028;
	
	public static final String STO_MOTIVO_CONTROL = "C";
	public static final String STO_MOTIVO_ONLINE = "O";
	public static final String LISTA_DETALLE_PEDIDO = "listaDetallePedido";
	
	//####### INI: PROCESO CLUSTERIZACION ########
	public static final String PROCESO_CLUSTER_LIST = "procesosClusterList";
	public static final String PROCESO_CLUSTER_COD_NUEVO = "1";
	public static final String PROCESO_CLUSTER_DES_NUEVO = "Nuevo";	
	public static final String PROCESO_CLUSTER_COD_BD_CARGADAS = "2";
	public static final String PROCESO_CLUSTER_DES_BD_CARGADAS = "Bases de Datos Cargados";
	public static final String PROCESO_CLUSTER_COD_EN_CLUSTERIZACION = "3";
	public static final String PROCESO_CLUSTER_DES_EN_CLUSTERIZACION = "En proceso de clusterizacion";
	public static final String PROCESO_CLUSTER_COD_CLUSTER_CERRADO = "4";	
	public static final String PROCESO_CLUSTER_DES_CLUSTER_CERRADO = "Clusterizacion Cerrada";
	public static final String PROCESO_CLUSTER_COD_DIST_REALIZADA = "5";	
	public static final String PROCESO_CLUSTER_DES_DIST_REALIZADA = "Distribucin Realizada";
	public static final String PROCESO_CLUSTER_COD_AJUS_REALIZADO = "6";	
	public static final String PROCESO_CLUSTER_DES_AJUS_REALIZADO = "Ajuste Realizado";
	
	public static final int NUMERO_MAX_HOJAS_EXCEL = 5;
	
	public static final String FDV_CAMPANHA_LIST = "fdvCampanhaList";
	public static final String FDV_PROCESS_PERIOD_LIST = "fdvProcessPeriodList";
	public static final String FDV_GROUP_POBLATION_LIST = "fdvGroupPoblationList";
	public static final String FDV_CLU_ASIG_SIST_LIST = "fdvCluAsigSistList";

	public static final String CONSULTA_PARAMETROS_FDV_LIST = "consultaParametrosFdvList";
	public static final String CONSULTA_DISTRIBUCION_META_FDV_LIST = "consultaDistribucionMetaFdvList";
	
	public static final String DISTRIBUCION_CX1	= "013";
	public static final String DISTRIBUCION_CX2	= "014";
	public static final String DISTRIBUCION_CX3	= "015";
	public static final String DISTRIBUCION_CX4	= "016";
	public static final String DISTRIBUCION_CX5	= "017";
	public static final String DISTRIBUCION_CX6	= "018";
	
	public static final String CODIGO_PARAMETRO_ALERTA_REPORTE_CAPITALIZACION_PUP_PPU_ROJO = "019";
	public static final String CODIGO_PARAMETRO_ALERTA_REPORTE_CAPITALIZACION_PUP_PPU_AMBAR = "020";
	
	public static final String CODIGO_PARAMETRO_ALERTA_REPORTE_ACTIVIDAD_ROJO = "021";
	public static final String CODIGO_PARAMETRO_ALERTA_REPORTE_ACTIVIDAD_AMBAR = "022";
	
	public static final String CODIGO_PARAMETRO_CLUSTERIZACION_TAMANYO_MINIMO_GRUPO = "012";
	
	public static final String REPORTE_REGION_ZONA_MAP_LIST = "reporteRegionZonaMapList";
	
	public static final String FORMATO_NOMBRE_PROCESO_LARGO_FDV = "%s-P%s-Ver.%d - %s";
	
	public static final String FORMATO_NOMBRE_REPORTE_CONSOLIDADO = "Consolidado-%s.pdf";
		
	//####### FIN: PROCESO CLUSTERIZACION ########

	public static final int INT_UNO = 1;

	public static final String SISTEMA_IMP = "IMP";
	public static final String SISTEMA_PED = "PED";
	
	public static final String IMP_PARAM_NTHREADS = "002";
	public static final String PED_PARAM_NTHREADS = "018";
	
	public static final String PED_PARAM_SHOW_VALI_PEDI_DIGI = "001";
	
	public static final String SISTEMA_BAS = "BAS";
	public static final String BAS_PAIS_DEFAULT = "000";
	public static final String BAS_SERVER_QUARTZ_DEFAULT = "001";
	
	public static final String SISTEMA_GEN = "GEN";
	public static final String SISTEMA_DIR = "DIR";
	public static final String SISTEMA_STO = "STO";
	public static final String GEN_IDIO_ISO_DEFA = "002";
	public static final String GEN_USUA_WEB = "003";
	public static final String GEN_AUDITORIA = "005";
	public static final String GEN_PROCESO_WS= "006";
	
	
	
	
	public static final String STO_IND_CORREO_CONSULTORA = "indCorreoConsultora";

	public static final String MAE_TAB_NIVEL_RIESGO = "nivelRiesgoTab-tab";
	public static final String MAE_TAB_TIPO_SUBTIPO = "tipoSubTipoTab-tab";
	public static final String MAE_TAB_ACTIVIDAD = "actividadTab-tab";
	
	public static final String MAE_TIPOS_REPORTES_LIST = "maeTiposReportesList";
	
	public static final String LET_BONO_CAMPANIA_LIST = "letBonoCampaniaList";

	
	public static final String STO_ORIGEN_DIGITADO = "G";
	public static final String STO_ORIGEN_OCR = "O";
	public static final String STO_ORIGEN_DD = "D";	
	public static final String STO_ORIGEN_WEB = "W";
	public static final String STO_ORIGEN_IVR = "I";
	public static final String STO_ORIGEN_ONLINE = "L";
	
	public static final String PED_OPERACION_BEL1 = "BEL001";
	
	public static final String LET_BONO_CONCURSO_LIST = "letBonoConcursoList";

	public static final String INC_BLOQUEOPREMIOS_LIST = "incBloqueoPremiosList";
	public static final String SICC_PREMIO_LIST = "siccPremioList";

	public static final String STO_RESUMEN_CLIENTES_LIST = "stoResumenClientesList";
	
	/**
	 * El nombre de la lista con todos los tipos de acciones, almacenada en el
	 * contexto de la aplicación
	 */
	public static final String ALL_TIPOS_ACCIONES = "allTiposAcciones";

	/**
	 * El nombre de la lista con todos los tipos de bloqueos a usuario, almacenada en el
	 * contexto de la aplicación
	 */
	public static final String ALL_TIPOS_BLOQUEO_USUARIO = "allTiposBloqueoUsuario";
	
	public static final String LIST_OPCION_CONSULTA_HIPERCONSULTA = "opcionConsultaHiperConsulta";
	
	/**
	 * El nombre de la lista con todos las pol쳩cas de seguridad para contrase𠱍
	 */
	public static final String ALL_POLITICAS_SEGURIDAD_CONTRASENIA = "allPoliticasSeguridadContrasenia";
	
	public static final String SEG_POLITICA_LONGITUD_MINIMA_CARACTERES	= "001";
	public static final String SEG_POLITICA_CANTIDAD_MINIMA_MAYUSCULA	= "002";
	public static final String SEG_POLITICA_CANTIDAD_MINIMA_NUMEROS	    = "003";
	public static final String SEG_POLITICA_CANTIDAD_MAXIMA_REINTENTOS	= "004";
	public static final String SEG_POLITICA_DIAS_EXPIRACION_CLAVE	 	= "005";
	public static final int SEG_DIAS_MOSTRAR_MENSAJE_EXPIRACION_CLAVE = 7;
	public static final String MOSTRAR_MENSAJE_EXPIRACION_CLAVE	 	= "mostrarMensajeExpiracion";
		
	public static final String FDV_ANYO_PROCESO_DISTRIBUCION_REALIZADA_LIST = "fdvAnyoProcesoDistribucionRealizadaList";
	
	public static final String MANTENIMIENTO_MAE_BLOQUEO_DESBLOQUEO_CLIENTE_LIST = "mantenimientoMAEBloqueoDesbloqueoClienteList";
	
	public static final String MANTENIMIENTO_MAE_BLOQUEO_DESBLOQUEO_FLAG_BOTON_BLOQUEAR = "mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonBloquear";
	
	public static final String MANTENIMIENTO_MAE_BLOQUEO_DESBLOQUEO_FLAG_BOTON_DESBLOQUEAR = "mantenimientoMAEBloqueoDesbloqueoClienteFlagBotonDesbloquear";
	
	public static final String MANTENIMIENTO_MAE_BLOQUEO_DESBLOQUEO_TIPO_BLOQUEO_LIST = "mantenimientoMAEBloqueoDesbloqueoClienteTipoBloqueoList";
	
	public static final String MANTENIMIENTO_MAE_BLOQUEO_DESBLOQUEO_TIPO_AREA_LIST = "mantenimientoMAEBloqueoDesbloqueoClienteTipoAreaList";
	
	public static final String MANTENIMIENTO_MAE_BLOQUEO_DESBLOQUEO_BLOQUEADO_SI = "SI";
	
	public static final String MANTENIMIENTO_MAE_BLOQUEO_DESBLOQUEO_LOG_BLOQUEO_LIST = "logBloqueoClienteList";
	
	public static final String MANTENIMIENTO_MAE_BLOQUEO_DESBLOQUEO_LOG_DESBLOQUEO_LIST = "logDesbloqueoClienteList";
	
	public static final String INC_CLASIFICACION_CONCURSO_LIST = "incClasificacionConcursoList";
	public static final String INC_CONCURSO_LIST = "incConcursoList";
	public static final String INC_TIPO_PROGRAMA_LIST = "incTipoProgramaList";
	public static final String INC_BASE_CALCULO_LIST = "incBaseCalculoList";
	public static final String INC_DIRIGIDOS_LIST = "incDirigidosList";
	public static final String INC_AMBITO_LIST = "incAmbitoList";
	public static final String INC_TIPO_VENTA_LIST = "incTipoVentaList";
	public static final String INC_TIPO_EXIGENCIA_LIST = "incTipoExigenciaList";
	public static final String INC_CONCURSO_RECOMENDADAS_LIST = "incConcursoRecomendadasList";
	
	public static final String INC_ESTATUS_CLIENTE_LIST = "incEstatusClienteList";
	public static final String INC_CLASIFICACIONES_PARTICIPANTES_LIST = "incClasificacionesParticipantesList";
	public static final String INC_TIPOS_PREMIACION_LIST = "incTiposPremiacionList";
	public static final String INC_TIPOS_ELECCION_LIST = "incTiposEleccionList";
	public static final String INC_CENTROS_SERVICIO_LIST = "incCentrosServicioList";
	public static final String INC_NIVEL_LIST = "incNivelList";
	
	public static final String INC_CONCURSO_ESTATUS_LIST = "incConcursoEstatusList";
	public static final String INC_CONCURSO_CLASIFICACIONES_LIST = "incConcursoClasificacionesList";
	public static final String INC_NIVELES_PREMIACION_LIST = "incNivelesPremiacionList";
	public static final String INC_ARTICULOS_LOTE_LIST = "incArticulosLoteList";
	public static final String INC_TIPO_LOTE_LIST = "incTipoLoteList";

	public static final String INC_TAB_AMBITO = "ambitoTab-tab";
	public static final String INC_TAB_PARTICIPANTES = "participantesTab-tab";
	public static final String INC_TAB_PRODUCTOS = "productosTab-tab";
	public static final String INC_TAB_PREMIACION = "premiacionTab-tab";
	public static final String INC_TAB_CALIFICACION = "calificacionTab-tab";
	
	public static final String INC_DTO_CONCURSO = "incDtoConcurso";
	public static final String INC_TIPO_CONCURSO_RECOMENDACION = "E";
	public static final String INC_TIPO_CONCURSO_PARATI_PARAMI = "P";
	public static final String INC_TIPO_CONCURSO_DUPLA_CYZONE = "D";
	public static final String INC_TIPO_CONCURSO_CONSTANCIA = "K";
	public static final String INC_TIPO_CONCURSO_BOLSA_PREMIO = "M";
	public static final String INC_TIPO_PROGRAMA_BONIFICACION = "B";
	public static final String INC_TIPO_PROGRAMA_RETIRO_BIENES = "R";
	public static final String INC_TIPO_PROGRAMA_CONCURSO = "C";
	
	public static final Integer INC_BASE_CALCULO_MONTO = new Integer(1);
	public static final Integer INC_BASE_CALCULO_UNIDADES = new Integer(2);
	public static final Integer INC_BASE_CALCULO_NUMER_PEDIDOS = new Integer(3);
	public static final Integer INC_BASE_CALCULO_RECOMENDADAS = new Integer(4);
	
	public static final String INC_MENSAJE_PUNTOS_RECOMENDACION_DEFAULT = "INC03";
	public static final String INC_MENSAJE_PUNTOS_BONIFICACION_DEFAULT = "INC01";
	public static final String INC_MENSAJE_PUNTOS_CONCURSO_DEFAULT = "INC99";
	public static final String INC_MENSAJE_PUNTOS_DUPLA_DEFAULT = "INC21";
	
	public static final String INC_MENSAJE_DESPACHO_DUPLA_DEFAULT = "INC31";
	public static final String INC_MENSAJE_DESPACHO_NODUPLA_DEFAULT = "INC08";
	
	public static final Integer INC_OID_ESTADO_EN_CREACION = new Integer(1);
    public static final Integer INC_OID_ESTADO_ACTIVO = new Integer(2);
    
    public static final Integer INC_OID_VIG_CONCU_VIGENTE = new Integer(1);
    public static final Integer INC_OID_VIG_CONCU_NO_VIGENTE = new Integer(2);
    
    public static final String INC_TIPO_PREMIACION_BOLSA_PREMIOS = "1";
    public static final String INC_TIPO_PREMIACION_POR_NIVELES = "2";
    public static final String INC_TIPO_PREMIACION_POR_SORTEO = "3";
    
    public static final Long INC_TIPO_PREMIO_MONETARIO = new Long(1);
    public static final Long INC_TIPO_PREMIO_ARTICULO = new Long(2);
    public static final Long INC_TIPO_PREMIO_DESCUENTO = new Long(3);
    public static final Long INC_TIPO_PREMIO_PUNTOS = new Long(4);
    public static final Long INC_TIPO_PREMIO_SORTEO = new Long(5);
 
	public static final Long INC_MONTOVENTA_MONTO_MINIMO = new Long(1);
	public static final Long INC_MONTOVENTA_UNIDAD_MINIMA = new Long(2);
	public static final Long INC_MONTOVENTA_MONTO_TOTAL = new Long(3);
	public static final Long INC_MONTOVENTA_VENTA_PROMEDIO = new Long(4);
	
	public static final String INC_TIPO_ENTREGA_BELCORP = "B";
	public static final String INC_TIPO_ENTREGA_CENTRO = "C";
	
	public static final String INC_INDICADOR_DESPACHO_MANUAL = "M";
	public static final String INC_INDICADOR_DESPACHO_AUTOMATICO = "A";
	
	public static final String REP_SIC_DET_UNIDS_ATENDIDAS_FALTANTES_CLIENTES_LIST= "repSicDetalleUnidadesAtendidasFaltantesClientesList";	
	public static final String REP_SAC_DET_PEDIDOS_CODIGO_SAP_CLIENTES_LIST = "repSacDetallePedidosCodigoSAPClientesList";
	public static final String REP_SAC_DET_TIPO_OFERTA_LIST = "repSacDetalleTipoOfertaList";
	
	public static final String ZON_REGIONES_LIST = "zonRegionList";
	public static final String PEJ_PROGRAMAS_LIST = "pejProgramaList";
	
	public static final String PEJ_PROGRAMAS_GRUPO_COMISION_LIST = "pejProgramaGrupoComisionList";

	public static final String INC_BLOQUE_PRODUCTO_LIST = "incBloqueProductoList";	
	public static final String INC_TIPO_PRODUCTO_LIST = "incTipoProductoList";
	public static final String INC_TIPO_OFERTA_LIST = "incTipoOfertaList";
	public static final String INC_TIPO_AGRUPACION_LIST = "incTipoAgrupacionList";
	public static final String INC_UNIDAD_NEGOCIO_LIST = "incUnidadNegocioList";
	public static final String INC_SUPER_GENERICO_LIST = "incSuperGenericoList";
	public static final String INC_GENERICO_LIST = "incGenericoList";
	public static final String INC_NEGOCIO_LIST = "incNegocioList";
	public static final String INC_CICLO_VIDA_LIST = "incCicloVidaList";
	public static final String INC_MARCA_PRODUCTO_LIST = "incMarcaProductoList";
	
	public static final String INC_PRODUCTOS_VALIDOS_LIST = "incProductosValidosList";
	public static final String INC_PRODUCTOS_BONIFICADOS_LIST = "incProductosBonificadosList";
	public static final String INC_PRODUCTOS_EXCLUIDOS_LIST = "incProductosExcluidosList";
	public static final String INC_PRODUCTOS_EXIGIDOS_LIST = "incProductosExigidosList";

	public static final String INC_BLOQUE_PRODUCTO = "P";
	public static final String INC_BLOQUE_OFERTA = "O";
	public static final String INC_BLOQUE_NEGOCIO = "N";
	public static final String INC_BLOQUE_CUV = "C";
	
	public static final String INC_OID_PRODUCTOS_VALIDOS = "1";
	public static final String INC_OID_PRODUCTOS_EXCLUIDOS = "2";	
	public static final String INC_OID_PRODUCTOS_BONIFICADOS = "3";
	public static final String INC_OID_PRODUCTOS_EXIGIDOS = "4";
	
	public static final String REC_CONCURSO_LIST = "recConcursoList";
	public static final String REC_PREMIO_LIST = "recPremioList";
	public static final String REC_REGION_LIST = "recRegionList";
	public static final String REC_PEDIDOS_EXPRESS_PREM_BLOQ_SEARCH_LIST = "recPedidosExpressPremBloqSearchList";
	public static final String REC_PEDIDOS_EXPRESS_PREM_BLOQ_PROCESS_LIST = "recPedidosExpressPremBloqProcessList";
	public static final String REC_CLIENTES_EXPRESS_PREM_BLOQ_LIST = "recClientesExpressPremBloqList";
	public static final String REC_CLIENTES_EXPRESS_PREM_BLOQ_VALFLAG = "valFlag";

	
	
	public static final String PED_ORIGEN_PROL = "L";
	public static final String PED_IND_ACTIVA_WS_PROL = "005";
	public static final String PED_PAIS_PROL = "006";
	public static final String PED_MARCA_PROL = "007";
	public static final String PED_URL_WS_PROL = "008";
	
	public static final String SAM_TIPO_CARGA_LIST = "samTipoCargaList";
	public static final String NUMERO_TRES = "3";
	public static final String NUMERO_CUATRO = "4";
	public static final String NUMERO_CINCO = "5";
	public static final String NUMERO_SIETE = "7";
	public static final String NUMERO_OCHO = "8";
	public static final String NUMERO_DIESIOCHO = "18";
	
	public static final String SAM_TIPO_CARGA_BATCH = "SAM-10";
	public static final String SAM_TIPO_CARGA_PROL = "SAM-12";
	
	public static final String REC_EXCEPCIONES_TRUEQUES_LIST = "excepcionesTruequesList";	
	
	public static final String RET_USUARIO_GENERICO="USU_RETAIL"; 
	
	public static final String PED_TIPO_SOLICITUD_LIST = "pedTipoSolicitudList";
	public static final String PED_MONTO_MINIMO_LIST = "pedMontoMinimoList";
	public static final String PED_MONTO_MINIMO_HISTORICO_LIST = "pedMontoMinimoHistoricoList";

	public static final String NVS_UNIDAD_ADMINISTRATIVAS_LIST = "nvsUnidadAdministrativasList";
	
	public static final String NVS_DESCUENTOS_LIST = "nvsDescuentosList";
	
	public static final String NVS_TAB_UAS = "parametroUASTab-tab";
	
	public static final String NVS_TAB_DES = "parametroDESTab-tab";
	
	/* INI SA PER-SiCC-2012-0385 */
	public static final String PRE_CODIGO_VENTA_RELAC_LIST = "preCodigoVentaRelacionadoList";
	public static final String PRE_VENTA_EXCLUSIVA_LIST = "preVentaExclusivaList";
	public static final String PRE_LIMITE_VENTA_LIST = "preLimiteVentaList";
	public static final String PRE_CONTROL_STOCK_LIST = "preControlStockList";
	/* FIN SA PER-SiCC-2012-0385 */
	
	public static final String FLX_CALIFICACION_COMPORTAMIENTO_LIST = "flxCalificacionComportamientoList";
	public static final String FLX_CALIFICACION_EXPERIENCIA_LIST = "flxCalificacionExperienciaList";
	public static final String FLX_CONSULTORA_LIST = "consultoraFLXList";
	
	public static final String FLX_CODIGO_ACCION_DESACTIVAR = "01";
	public static final String FLX_CODIGO_ACCION_ACTIVAR = "02";
	public static final String FLX_CODIGO_ACCION_REGISTRAR_OBJECION = "03";
	public static final String FLX_CODIGO_ACCION_ANULAR_OBJECION = "04";
	
	public static final String FLX_CONSULTORA_CARGA_MASIVA_LIST = "flxConsultoraCargaMasivaList";
	
	public static final String FLX_CONSULTORA_CARGA_MASIVA_ERRONEOS = "flxConsultoraCargaMasivaErroneos";
	public static final String FLX_CONSULTORA_OBJETADA_LIST = "flxConsultoraObjetadaList";
	
	public static final String XRX_TIPO_RECEPCION_LIST = "xrxTipoRecepcionList";
	public static final String XRX_CODIGO_INTERFAZ_XRX1 = "XRX-1";
	public static final String XRX_CODIGO_INTERFAZ_XRX2 = "XRX-2";
	public static final String XRX_CODIGO_INTERFAZ_XRX3 = "XRX-3";
	public static final String XRX_CODIGO_INTERFAZ_XRX4 = "XRX-4";
	
	public static final String ECM_CODIGO_INTERFAZ_ENVIAR_INSCRITAS = "ECM-7";
	public static final String ECM_CODIGO_INTERFAZ_ENVIAR_MATRIZ_PRODUCTO_OFERTA_CUMPLEANHOS = "ECM-9";
	public static final String ECM_CODIGO_PROCESO_BATCH_ENVIAR_MATRIZ_PRODUCTO_OFERTA_CUMPLEANHOS = "02";
	public static final String ECM_CODIGO_SISTEMA = "ECM";
	
	public static final String APE_EMPRESA_EXTERNA_LIST = "apeEmpresaExternaList";
	public static final String APE_PRODUCTO_NO_ALMACENADO_PLANTA_PRINCIPAL_LIST = "apeProductosNoalmPlaPriList";
	
	public static final String FLX_CONSULTORA_OBJETADA_CARGA_MASIVA_LIST = "flxConsultoraObjetadaCargaMasivaList";
	public static final String FLX_CONSULTORA_OBJETADA_CARGA_MASIVA_ERRONEOS = "flxConsultoraObjetadaCargaMasivaErroneos";
	
	public static final String STO_IND_BOLELEC = "STO_IND_BOLELEC";
	public static final String DETALLE_PEDIDO_FOLIO_LIST = "detallePedidoFolioList";
	
	public static final String PARAMETRO_PROGRAMA="02";

	/* INI SA PER-SiCC-2012-0459 */
	public static final String MAE_VALID_CIUDAD = "VAL_CIUDAD";
	public static final String MAE_VALID_VILLA = "VAL_VILLA";
	public static final String MAE_CIUDAD_LIST = "maeCiudadList";
	public static final String MAE_CIUDAD_CT_LIST = "maeCiudadCTList";
	/* FIN SA PER-SiCC-2012-0459 */
	
	public static final String SICC_REGION_SELECCIONADA_LIST = "siccRegionSeleccionadaList";
	
	public static final String ESTADO_ELIMINADO_TEMPORAL = "2";
	
	/* INI SA PER-SiCC-2012-0265 */
	public static final String MAE_MODULO_11V = "MOD11V";
	
	/* INI SA SiCC 20126451 */
	public static final String MAE_TIPO_DOCUMENTO_RUT = "01";
	/* INI SA SiCC 20126451 */
	
	/* INI SA PER-SiCC-2012-0265 */
	
	/* INI JJ PER-SiCC-2012-0419*/
	public static final String CONSULTA_LET_RESULTADOS_CONCURSOS = "consultaLETResultadosConcursosList";
	/* FIN JJ PER-SiCC-2012-0419*/
	
	public static final String MUY_PEQUEÑO = "Muy Pequeño";
	public static final String PEQUEÑO = "Pequeño";
	public static final String MEDIANO = "Mediano";
	public static final String GRANDE = "Grande";
	public static final String MUY_GRANDE = "Muy Grande";
	
	public static final String UNO = "1";
	public static final String DOS = "2";
	public static final String TRES = "3";
	public static final String CUATRO = "4";
	public static final String CINCO = "5";	
	
	public static final String REPORTE_VENTAS_CODIGO_ACCION_NOTA_CREDITO = "0";
	public static final String REPORTE_VENTAS_CODIGO_ACCION_BOLETA_VENTA = "1";
	
	public static final String CODIGO_CONSULTORA_EXISTE = "0";
	public static final String CODIGO_CONSULTORA_NO_EXISTE = "9";
	
	public static final String CODIGO_VENTA_EXISTE = "4";
	public static final String CODIGO_VENTA_MATRIZ_NO_EXISTE = "5";
	public static final String CODIGO_VENTA_PREMIO_NO_EXISTE = "6";
	public static final String CODIGO_VENTA_PREMIO_MAS_DE_UNO = "7";
	
	public static final String RECLAMOS_MASIVOS_OPERACIONES_ERRONEOS = "reclamosMasivosOperacionesErroneos";
	public static final String RECLAMOS_MASIVOS_OPERACIONES_EXITOSOS = "reclamosMasivosOperacionesExitosos";
	public static final String RUTA_ABSOLUTA_ARCHIVO_INGRESO_MASIVO = "rutaAbsolutaArchivoIngresoMasivo";
	public static final String NOMBRE_ARCHIVO_INGRESO_MASIVO = "nombreArchivoIngresoMasivo";
	
	public static final String CODIGO_PARAMETRO_INGRESO_MASIVO = "001";
	public static final String RUTA_ARCHIVO_PROCESADO = "rutaArchivoProcesado";
	public static final String CODIGO_SISTEMA_REC = "REC";
	
	public static final String CODIGO_SISTEMA_PED = "PED";
	public static final String CODIGO_PARAMETRO_CONSULTA_HIP = "011";
	public static final String CODIGO_PARAMETRO_SOPORTE_CARACTERES = "017";
	public static final String CODIGO_PARAMETRO_MUESTRA_STOCK_FISICO = "018";
	//sb PER-SiCC-2012-0460 ini
	public static final String MAE_CIUDAD_LIST_ENTR = "maeCiudadListEntr";
	//sb PER-SiCC-2012-0460 Fin

	/* INI SA PER-SiCC-2012-0365 */
	public static final String MAE_VALID_DIRECCION_VACACIONES = "VAL_DIR_ENT_VAC";
	public static final String MAE_TIPO_DIRECCION_VACACIONES = "08";
	
	public static final String MAE_CIUDAD_VAC_LIST = "maeCiudadVacList";
	public static final String MAE_CLIENTE_NIVEL2VAC_LIST = "maeClienteNivel2VacList";
	public static final String MAE_CLIENTE_NIVEL3VAC_LIST = "maeClienteNivel3VacList";
	public static final String MAE_CLIENTE_NIVEL4VAC_LIST = "maeClienteNivel4VacList";
	public static final String MAE_CLIENTE_NIVEL5VAC_LIST = "maeClienteNivel5VacList";
	public static final String MAE_CLIENTE_NIVEL6VAC_LIST = "maeClienteNivel6VacList";
	
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_CASA_DIRECCION_VACACIONES = "TV";
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_CELULAR_DIRECCION_VACACIONES = "CV";
	public static final String MAE_INDICADOR_PROCESO_INICIO_CAMPANA = "I";
	/* FIN SA PER-SiCC-2012-0365 */

	public static final String STO_MODO_ONLINE = "008";
	public static final String STO_MODO_ELIMINA_ONLINE = "009";
	
	/* INI SA PER-SiCC-2012-0375 */
	public static final String DTO_GRUPO_DESCUENTO_LIST = "dtoGrupoDescuentoList";
	public static final String DTO_RANGO_DESCUENTO_LIST = "dtoRangoDescuentoList";
	public static final String DTO_MATRIZ_DESCUENTO_LIST = "dtoMatrizDescuentoList";
	public static final String DTO_CATEGORIA_DESCUENTO_LIST = "dtoCategoriaDescuentoList";
	public static final String DTO_SUB_CATEGORIA1_LIST = "dtoSubCategoria1List";
	public static final String DTO_SUB_CATEGORIA2_LIST = "dtoSubCategoria2List";
	/* FIN SA PER-SiCC-2012-0375 */
	
	/*INI PER-SiCC-2012-0560  */
	public static final String TIPO_GRUPO_DEVOLUCIONES_NC = "STO_TOPE_DEV_NC";
	public static final String TIPO_GRUPO_DEVOLUCIONES = "STO_TOPE_DEV_%";
	
	public static final String TIPO_GRUPO_CAMBIOS_NC = "STO_TOPE_CAM_NC";
	public static final String TIPO_GRUPO_CAMBIOS = "STO_TOPE_CAM_%";
	
	public static final String TIPO_GRUPO_FFNE_NC = "STO_TOPE_FNE_NC";
	public static final String TIPO_GRUPO_FFNE = "STO_TOPE_FNE_%";
	
	public static final String TIPO_GRUPO_OTROS_NC = "STO_TOPE_CAM_NC";
	public static final String TIPO_GRUPO_OTROS = "STO_TOPE_%";

	public static final String STO_DEVOLUCIONES_LIST = "stoDevolucionesList";
	public static final String STO_CAMBIOS_LIST = "stoCambiosList";
	public static final String STO_FFNE_LIST = "stoFfneList";
	public static final String STO_OTROS_LIST = "stoOtrosList";
	
	/* FIN PER-SiCC-2012-0560 */
	
	public static final String BAS_LOGGER_LIST = "basLoggerList";
	
	/* PER-SiCC-2012-0558  */
	public static final String STO_IND_CDR_LINEA    ="STO_IND_CDR_LINEA";

	public static final String ARCHIVO_NO_EXISTE_EN_CONTROL = "-2";

	public static final String VALOR_PARAMETRO_HIP = "valPara";
	public static final String CODIGO_ZONA_DEMANDA_ANTICIPADA= "codigoZonaDemandaAnticipada";
	
	public static final String SISTEMA_PER = "PER";
	public static final String VALOR_PER_REPORTE_SUNAT = "012";
	
	public static final String SISTEMA_OCR = "OCR";
	
	public static final String OCR_URL_WS_SOCCRED = "017";
	
	public static final String OCR_IND_ACTIVA_WS_SOCCRED = "018";
		
	public static final String OCR_URL_PORT_SOCCRED = "019";
	public static final String OCR_URL_HOST_SOCCRED = "026";

	public static final String OCR_LIM_INF_VERDE = "020";
	public static final String OCR_LIM_SUP_VERDE = "021";
	public static final String OCR_LIM_INF_AMARI = "022";
	public static final String OCR_LIM_SUP_AMARI = "023";
	public static final String OCR_LIM_INF_ROJO = "024";
	public static final String OCR_LIM_SUP_ROJO = "025";

	public static final String STO_HOST_COMERCIAL = "012";
	public static final String STO_USER_COMERCIAL = "013";
	public static final String STO_PWD_COMERCIAL = "010";
	public static final String STO_TIPO_CONEXION = "011";

	/* INI SA PER-SiCC-2012-0138 */
	public static final String MAE_TIPO_BLOQUEO_LIST = "maeTipoBloqueoList";
	public static final String MAE_TIPO_DESBLOQUEO_LIST = "maeTipoDesbloqueoList";
	public static final String MAE_ACCION_BLOQUEO_LIST = "maeAccionBloquedoList";
	public static final String MAE_ARCHIVO_LIST = "maeArchivolist";	
	/* FIN SA PER-SiCC-2012-0138 */

	public static final String INTERFAZ_XRX_NUMERO_SECUENCIA_LOTE = "interfazXRXNumeroSecuenciaLote";
	public static final String INTERFAZ_XRX_NO_USAR_SEPARADOR_SECUENCIA = "interfazXRXNOUsarSeparadorSecuencia";
	public static final String INTERFAZ_XRX_EXTENSION_ARCHIVO_FLAG = "FLG";
	
	public static final String MANTENIMIENTO_ZONAS_DEMANDA = "mantenimientoCOMZonasDemandaList";
	
	public static final String MANTENIMIENTO_COMISIONES = "mantenimientoCOMComisionList";
	public static final String DATOS_COMISIONES_CALCULADAS = "datosCOMComisionesList";
	public static final String DATOS_COMISIONES_ESCALONADAS = "datosCOMComisionesEscalonadasList";
	public static final String DATOS_COMISIONES_ESCALONADAS_LIST1 = "datosCOMComisionesEscalonadasList";
	public static final String DATOS_COMISIONES_ESCALONADAS_LIST2 = "datosCOMComisionesEscalonadasList2";
	public static final String DATOS_COMISIONES_ESCALONADAS_LIST3 = "datosCOMComisionesEscalonadasList3";
	public static final String DATOS_COMISIONES_CLASIFICACION = "datosCOMComisionesClasificacionList";
	
	public static final String CONSULTA_COMISION_GERENTE_ZONA_ESCALONADA = "consultaCOMComisionGerenteZonaEscalonadaList";
	
	/* INI SA PER-SiCC-2012-0367 */
	public static final String MAE_VALID_ESTATUS_COMERCIAL = "VAL_ESTAT_COMER";
	/* FIN SA PER-SiCC-2012-0367 */
	
	public static final String REC_NUMERO_REGISTROS_DEFECTO_CONSULTA = "25";
	public static final String REC_NUMERO_REGISTROS_MAXIMO_CONSULTA = "100";
	
	
	public static final String MANTENIMIENTO_COMISIONES_TAB_REGIONES_CLASS = "mantenimientoComisionesTabRegionesClass";
	public static final String MANTENIMIENTO_COMISIONES_TAB_REGIONES_DISPLAY = "mantenimientoComisionesTabRegionesDisplay";
	
	public static final String MANTENIMIENTO_COMISIONES_TAB_COMISION_CLASS = "mantenimientoComisionesTabComisionClass";
	public static final String MANTENIMIENTO_COMISIONES_TAB_COMISION_DISPLAY = "mantenimientoComisionesTabComisionDisplay";
	
	public static final String MANTENIMIENTO_COMISIONES_TAB_ESCALONADA_CLASS = "mantenimientoComisionesTabEscalonadaClass";
	public static final String MANTENIMIENTO_COMISIONES_TAB_ESCALONADA_DISPLAY = "mantenimientoComisionesTabEscalonadaDisplay";
	
	public static final String MANTENIMIENTO_COMISIONES_TAB_CLASIFICACION_CLASS = "mantenimientoComisionesTabClasificacionClass";
	public static final String MANTENIMIENTO_COMISIONES_TAB_CLASIFICACION_DISPLAY = "mantenimientoComisionesTabClasificacionDisplay";
	
	public static final String TAB_ACTIVO_CLASS = "ditch-tab ditch-focused";
	public static final String TAB_INACTIVO_CLASS = "ditch-tab ditch-unfocused";
	
	public static final String TAB_ACTIVO_DISPLAY = "";
	public static final String TAB_INACTIVO_DISPLAY = "none";
	
	public static final int MANTENIMIENTO_COMISIONES_TAB_REGIONES = 0;
	public static final int MANTENIMIENTO_COMISIONES_TAB_COMISION = 1;
	public static final int MANTENIMIENTO_COMISIONES_TAB_ESCALONADA = 2;
	public static final int MANTENIMIENTO_COMISIONES_TAB_CLASIFICACION = 3;
	
	public static final String CCC_PAGOS_CHEQUE = "listaPagosCheques";
	public static final String TIPO_DOCUMENTO_RUT = "RUT";
	public static final String CCC_LONGITUD_NUMERO_CEDULA = "cccLongitudNumeroCedula";
	
	public static final String SICC_MOT_RECHAZ_LIST = "lstCodMotRechazo";
	
	//Ini PER-SiCC-2012-0642 
	public static final String VAL_IND_CDR_MIGR = "STO_IND_CDR_MIGR";
	public static final String VAL_CEROS = "00";
	public static final String INACTIVO = "0";
	//Fin PER-SiCC-2012-0642

	/* INI SA PER-SiCC-2012-0840 */
	public static final String CODIGO_SISTEMA_COB = "COB";
	public static final String GEN_CODIGO_PARAMETRO_PAIS_DEFAULT = "007";
	public static final String COB_INTERFAZ_ENVIO_RECUPERACION_COBRANZA = "COB-5";
	public static final String COB_PROCESO_RECUPERACION_COBRANZA = "12";
	
	public static final String ENVIO_SFTP = "4";
	public static final String INTERFAZSICC_ERROR_LOGUEAR_SFTP = "No se pudo loguearse al SFTP indicado";
	public static final String INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_SFTP = "No se pudo obtener del SFTP el archivo de entrada";
	public static final String INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_SFTP = "No se pudo enviar al SFTP, el archivo generado";
	/* FIN SA PER-SiCC-2012-0840 */	

	/* INICIO */
	public static final String SOA_GERENTE_ZONA = "GZ";
	public static final String SOA_GERENTE_REGION = "GR";
	public static final String SOA_DIRECTOR_VENTAS = "DV";
	public static final String SOA_SISTEMA_COMERCIAL = "SICC";
	public static final String SOA_CODIGO_MENSAJE_ERROR = "1007";
	public static final String SOA_CODIGO_MENSAJE_ERROR_BD = "207";
	public static final String SOA_CODIGO_MENSAJE_ERROR_SOA = "307";
	
	public static final String SOA_ENTIDAD_CREDITICIA = "BELCORP";
	
	public static final String SOA_PARAMETRO_TIPO_CONCURSO = "tipoConcurso";
	public static final String SOA_PARAMETRO_ESTADO_CONCURSO = "vigenciaConcurso";
	
	public static final String SOA_CODIGO_SISTEMA = "SOA";
	
	public static final String SOA_TIPO_VALOR_NUMERICO = "NUMERICO" ;
	public static final String SOA_TIPO_VALOR_PORCENTUAL = "PORCENTUAL" ;
	
	public static final String SOA_TIPO_CONCURSO_REGALO = "001";
	public static final String SOA_TIPO_CONCURSO_CONSTANCIAS_VENTAS = "002";
	public static final String SOA_TIPO_CONCURSO_RECOMENDACION = "003";
	public static final String SOA_TIPO_CONCURSO_NUEVAS = "004";
	public static final String SOA_TIPO_CONCURSO_RECONOCIMIENTO = "005";
	
	public static final String SOA_ESTADO_CONCURSO_VIGENTE = "1";
	public static final String SOA_ESTADO_CONCURSO_CERRADO = "0";
	
	public static final String SOA_TIPO_NUEVAS = "6";
	
	//Indicadores SOA
	public static final String SOA_TIPO_INDICADOR_22_COBRANZA_21 		= "22";
	public static final String SOA_TIPO_INDICADOR_23_COBRANZA_31 		= "23";
	public static final String SOA_TIPO_INDICADOR_24_COBRANZA_60 		= "24";
	
	public static final String SOA_TIPO_INDICADOR_25_PEGS_SIN_DEUDA 	= "25";
	public static final String SOA_TIPO_INDICADOR_32_METAS 				= "32";
	
	public static final String SOA_TIPO_INDICADOR_35_LETS_PRODUCTIVAS 	= "35";
	public static final String SOA_TIPO_INDICADOR_36_LETS_CRITICAS	 	= "36";
	public static final String SOA_TIPO_INDICADOR_37_GZ_PRODUCTIVAS 	= "37";
	public static final String SOA_TIPO_INDICADOR_38_GZ_ESTABLES	 	= "38";
	public static final String SOA_TIPO_INDICADOR_39_GZS_CRITICAS	 	= "39";
	public static final String SOA_TIPO_INDICADOR_40_GRS_PRODUCTIVAS 	= "40";
	public static final String SOA_TIPO_INDICADOR_41_GRS_ESTABLES	 	= "41";
	public static final String SOA_TIPO_INDICADOR_42_GRS_CRITICAS	 	= "42";	
	public static final String SOA_TIPO_INDICADOR_43_PEDIDOS_WEB_COMERCIAL   = "43";
	public static final String SOA_TIPO_INDICADOR_44_PEDIDOS_COMERCIAL       = "44";
	public static final String SOA_TIPO_INDICADOR_45_GZ_MONTO_PEDIDOS_WEB_COMERCIAL = "45";	
	
	public static final String SOA_TIPO_INDICADOR_22_COBRANZA_DESC 			= "Cobranza 21d (%)";
	public static final String SOA_TIPO_INDICADOR_23_COBRANZA_DESC 			= "Cobranza 31d (%)";
	public static final String SOA_TIPO_INDICADOR_24_COBRANZA_DESC 			= "Cobranza 60d (%)";	
	public static final String SOA_TIPO_INDICADOR_25_PEGS_SIN_DEUDA_DESC 	= "PEGS sin deuda (%)";
	public static final String SOA_TIPO_INDICADOR_32_METAS_DESC				= "Metas (%)";	
	public static final String SOA_TIPO_INDICADOR_35_LETS_PRODUCTIVAS_DESC 	= "LET's Productivas";
	public static final String SOA_TIPO_INDICADOR_36_LETS_CRITICAS_DESC	 	= "LET's Criticas";
	public static final String SOA_TIPO_INDICADOR_43_PEDIDOS_WEB_COMERCIAL_DESC	   = "Pedidos Web Comercial";
	public static final String SOA_TIPO_INDICADOR_44_PEDIDOS_COMERCIAL_DESC	       = "Pedidos Comercial";
	public static final String SOA_TIPO_INDICADOR_45_GZ_MONTO_PEDIDOS_WEB_COMERCIAL_DESC	 = "Monto Pedidos Web Comercial";	
	
	public static final String SOA_PARAM_PAIS_DEFAULT = "041";
		
	/* FIN */
	public static final String USUARIO_LIST_ALL = "usuarioListTodos";


	public static final String SOA_INCENTIVOS_LIST = "SOAIncentivosList";
	public static final String SOA_INCENTIVOS_PAGINADO_LIST = "SOAIncentivosPaginadoList";
	
	public static final String RUTEA_PAGO_01 = "BAN_RUTEA_PAGO_01";
	public static final String RUTEA_PAGO_02 = "BAN_RUTEA_PAGO_02";
	public static final String RUTEA_PAGO_03 = "BAN_RUTEA_PAGO_03";
	public static final String RUTEA_PAGO_04 = "BAN_RUTEA_PAGO_04";
	
	/* INI SA VEN-SiCC-2012-0101 */
	public static final String INC_CONCURSOS_FALTANTES_LIST = "incConcursosFaltantesList";
	/* FIN SA VEN-SiCC-2012-0101 */

	public static final int SOA_REGISTRO_PAGINA_DEFAULT = 10;
	public static final int SOA_PAGINA_DEFAULT = 1;
	
	public static final String CRA_PERIODO_CORPORATIVO_LIST = "periodoCorporativoList";	
	public static final String CRA_DURACION_PERIODO_VALOR = "21";	
	public static final String CRA_CAMPANHA_DEFAULT="Campaña - ";
	public static final String CRA_ZONA_FACTURACION_LIST = "craZonaFacturacionList";
	public static final String CRA_GRUPO_ZONA_LIST = "craGrupoZonaList";
	public static final String CRA_ZONA_LIST = "craZonaList";
	public static final String CRA_ACTIVIDAD_REGENERAR_LIST="craActividadRegenerarList";
	public static final String PED_TIPO_DESPACHO_LIST = "pedTipoDespachoList";
	public static final String PED_FLETE_LIST = "pedFleteList";
	
	public static final String FLETE_AUDITORIA_REGISTRO = "R";
	public static final String FLETE_AUDITORIA_MODIFICACION = "M";
	public static final String FLETE_AUDITORIA_ELIMINACION = "E";
	
	public static final String SISTEMA_SOA = "SOA";
	public static final String SOA_HOST_DATAMART = "010";
	public static final String SOA_USER_DATAMART = "011";
	public static final String SOA_PWD_DATAMART = "008";
	public static final String SOA_TIPO_CONEXION = "009";
	public static final String SOA_HOST_WEB = "046";
	public static final String SOA_USER_WEB = "047";
	public static final String SOA_PWD_WEB = "048";
	public static final String SOA_TIPO_CONEXION_WEB = "049";
	public static final String SOA_SI = "SI";
	public static final String SOA_NO = "NO";
	public static final String SOA_IND_CREDI_HISTO = "070";
	
	public static final String REC_MOTIVO_DEVOLUCION_LIST = "recMotivoDevolucionList";

	
	public static final String REC_LIST_MOTIVO_DEVOLUCION = "recListMotivoDevolucion";
	public static final String REC_CODIGO_VENTA_ARCHIVO_LIST = "recCodigoVentaArchivoList";
	public static final String REC_CODIGO_CLIENTE_ARCHIVO_LIST = "recCodigoClienteArchivoList";
	public static final String REC_CODIGO_CLIENTE_INVALIDO_ARCHIVO_LIST = "recCodigoClienteInvalidoArchivoList";
	public static final String REC_LISTA_BLANCA_PRODUCTOS_LIST = "recListaBlancaProductosList";
	
	public static final String REC_OPERACIONES_SEARCH_LIST = "recOperacionesSearchList"; 
	public static final String REC_TIPO_OPERACION_SEARCH_LIST = "recTipoOperacionSearchList";
	public static final String REC_MOTIVO_DEVOLUCION_SEARCH_LIST = "recMotivoDevolucionSearchList";
	public static final String REC_REGION_SEARCH_LIST = "recRegionSearchList";
	public static final String REC_ZONA_SEARCH_LIST = "recZonaSearchList";

	public static final String SOA_VALORACION_NO_APTA = "NO_APTA";
	public static final String SOA_VALORACION_APTA = "APTA";
	public static final String SOA_VALORACION_CONSULTORA = "CONSULTORA";
	public static final String SOA_VALORACION_INCOBRABLE = "INCOBRABLE";
	public static final String SOA_VALORACION_SIN_INFORMACION = "SIN INFORMACION";
	
	public static final String SOA_TIPO_TELEFONO_FIJO 		= "Telefono Fijo";
	public static final String SOA_TIPO_TELEFONO_MOVIL 		= "Telefono Movil";
	public static final String SOA_TIPO_TELEFONO_TRABAJO 	= "Telefono Trabajo";
	
	public static final String ZON_SECCIONES_NO_APTAS_LIST = "zonSeccionesNoAptasList";
	
	public static final String PED_GESTION_STOCK_LIST= "pedGestionStockList";
	public static final String PED_GESTION_STOCK_ZONA_LIST = "pedGestionStockZonaList";
	public static final String PED_GESTION_STOCK_REGION_LIST = "pedGestionStockRegionList";
	public static final String PED_GESTION_STOCK_TIPO_CLIE_LIST = "pedGestionStockTipoClienteList";
	public static final String PED_GESTION_STOCK_SUB_TIPO_CLIE_LIST = "pedGestionStockSubTipoClieList";
	public static final String PED_GESTION_STOCK_TIPO_CLASIF_LIST = "pedGestionStockTipoClasifList";
	public static final String PED_GESTION_STOCK_CLASIF_LIST = "pedGestionStockClasifList";
	public static final String PED_CODIGO_VENTA_ARCHIVO_LIST="pedCodigoVentaArchivoList";
	
	public static final String PED_LEVANTAMIENTO_ERRORES_CODIGOS_VENTA_LIST="pedLevantamientoErroresCodigosVentaList";
	public static final String PED_LEVANTAMIENTO_ERRORES_CODIGOS_SAP_LIST="pedLevantamientoErroresCodigosSapList";
	
	public static final String COM_ESTADO_COMISION = "02"; 
	
	public static final String ZON_UNIDAD_ADMIN_CONSU_LIST = "zonUnidadAdministrativaConsultoraList";
    
    public static final String REC_CODIGO_CONSULTORA_ARCHIVO_LIST = "recCodigoConsultoraArchivoList";


	public static final int SOA_CANTIDAD_PREVIAS_DEFAULT = 6;

    
    public static final String COM_MINIMO_NUEVAS_REGION_LIST = "comMinimoNuevasRegionList";
    public static final String COM_MINIMO_NUEVAS_SEARCH_LIST = "comMinimoNuevasSearchList";
    public static final int NUMERO_CIEN = 100;
    
    public static final String REC_ORIGEN_ARCHIVO_RED = "1";

	public static final String REC_ORIGEN_ARCHIVO_FTP = "2";
	public static final String REC_PROCESAR_INGRESO_MASIVO_OPERACIONES_LIST2 = "mantenimientoRECIngresoMasivoOperacionesProcess2List";
    
    public static final String REC_LISTA_BLANCA_FLAG_CERRAR = "recListaBlancaFlagCerrar";
    public static final String REC_MENSAJE_ERROR_CODIGO_VENTA_CLIENTE_LIST = "recMensajeErrorCodigoVentaClienteList";
    public static final String REC_MENSAJE_ERROR_CODIGO_VENTA_LIST = "recMensajeErrorCodigoVentaList";

	public static final String SOA_ABONO = "A";
	public static final String SOA_CARGO = "C";
	
	public static final String SOA_CAMPO_ORDENAMIENTO = "1";
	/* INI SA PER-SiCC-2012-1021 */
	public static final String MAV_ACTIVIDADES_LIST = "mavActividadesList";
	public static final String MAV_ESTADOS_LIST = "mavEstadosList";
	public static final String MAV_TIPOS_OFERTA_LIST = "mavTiposOfertaList";
	public static final String MAV_CONFIGURACIONES_LIST = "mavConfiguracionesList";
	public static final String MAV_CONFIGURACIONES_ACTIVIDAD_TIPO_OFERTA_LIST = "mavConfiguracionesListActividadTipoOferta";
	public static final String MAV_CONFIGURACIONES_TEMPORALES_LIST = "mavConfiguracionesTemporales";
	
	public static final String MAV_TIPO_CONSIDERACION = "C";
	public static final String MAV_TIPO_RESTRICCION = "R";
	public static final String MAV_TIPO_SIN_CONDICION = "S";
	public static final String MAV_TIPO_UNA_CONDICION = "U";	
	public static final String MAV_TIPO_UNA_CONDICION_DOBLE = "D";
	public static final String MAV_TIPO_LISTA_CONDICION = "L";
	public static final String MAV_TIPO_LISTA_EXTERNA_CONDICION = "E";
	public static final String MAV_TIPO_CONDICIONES_MIXTAS = "M";
	public static final String MAV_ABREVIATURA_LISTA_REGION_ZONA = "LRG";
	public static final String MAV_ABREVIATURA_LISTA_CONSULTORAS = "LCL";
	
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO = 7;
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA = 8;
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD = 9;
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO = 10;
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO = 11;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO = 12;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA = 13;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD = 14;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO = 15;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO = 16;
	public static final int MAV_CONRES_INSCRIPCION_NUEVA_DUPLA = 17;
	public static final int MAV_CONRES_LISTA_CONSU = 18;
	public static final int MAV_CONRES_LISTA_REGION_ZONA = 30;
	public static final String MAV_CONRES_LISTA_REGION_ZONA_STRING = "30";
	public static final int MAV_CONRES_CLASIFICACIONES_CLIENTE = 3;
	public static final int MAV_CONRES_UNIDAD_ADM = 2;
	public static final String MAV_CONRES_TODOS = "1";
	
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO_REST = 37;
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST = 38;
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST = 39;
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST = 40;
	public static final int MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST = 41;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST = 42;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST = 43;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST = 44;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST = 45;
	public static final int MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST = 46;
	public static final int MAV_CONRES_INSCRIPCION_NUEVA_DUPLA_REST = 47;
	public static final int MAV_CONRES_LISTA_CONSU_REST = 48;
	public static final int MAV_CONRES_LISTA_REGION_ZONA_REST = 60;
	public static final int MAV_CONRES_CLASIFICACIONES_CLIENTE_REST = 33;
	public static final int MAV_CONRES_UNIDAD_ADM_REST = 32;

	public static final String MAV_CONSIDERACION_LIST ="mavConsideracionList";
	public static final String MAV_RESTRICCION_LIST =  "mavRestriccionList";
	
	public static final String MAV_TAB_CONSIDERACION = "consideracionTab-tab";
	public static final String MAV_TAB_RESTRICCION = "restriccionTab-tab";
	
	public static final String MAV_ESTADO_TMP_INSERTAR = "3";
	public static final String MAV_ESTADO_TMP_ELIMINAR = "4";
	public static final String MAV_ARCHIVO_LIST = "mavArchivolist";
	public static final String MAV_ARCHIVO_REGIONES_LIST = "mavArchivoRegioneslist";
	/* FIN SA PER-SiCC-2012-1021 */
	
	public static final String COM_COMISION_TIPO_COMISION_EJECUTIVA = "03";
	public static final String COM_COMISION_TIPO_COMISION_GERENTE_ZONA = "02";
	public static final String COM_COMISION_INDICADOR_ESCALONADA_CERO = "0";
	public static final String COM_COMISION_INDICADOR_ESCALONADA_UNO = "1";
	
	/* INI SA PER-SiCC-2012-0975 */
	public static final String DTO_DESCUENTO_ADICIONAL_LIST = "dtoDescuentoAdicionalList";
	public static final String DTO_DESCUENTO_ADICIONAL_DETALLE_LIST = "dtoDescuentoAdicionalDetalleList";
	public static final String DTO_DESCUENTO_ADICIONAL_GRUPO_LIST = "dtoDescuentoAdicionalGrupoList";
	/* FIN SA PER-SiCC-2012-0975 */
	
	public static final String ZON_UNIDAD_ADMINISTRATIVA_REGION_LIST = "zonUnidadAdministrativaRegionList";
	public static final String ZON_UNIDAD_ADMINISTRATIVA_ZONA_LIST = "zonUnidadAdministrativaZonaList";
	
	public static final String COM_REGISTROS_VALIDOS_LIST = "comRegistrosValidosList";
	public static final String COM_REGISTROS_CON_ERROR_LIST = "comRegistrosConErrorList";
	public static final String COM_CONSULTORAS_REGISTRADAS_AL_PROGRAMA_LIST = "comConsultorasRegistradasAlProgramaList";
	
	/* INI DA PER-SiCC-2012-0970 */
	public static final String OPERACION_CDR_USUARIO_LIST 		= "operacionCDRUsuarioList";
	public static final String OPERACIONES_USUARIO_LIST 		= "operacionUsuarioList";
	public static final String TIPOS_OPERACIONES_USUARIO_LIST 	= "tiposOperacionUsuarioList";	
	/* FIN DA PER-SiCC-2012-0970 */
	
	public static final String CUP_LISTA_CODIGOS_CONSULTORAS = "cupListaCodigosConsultoras";
	public static final String CUP_CONSULTORA_OK =  "Valido";
	public static final String CUP_CONSULTORA_ERROR = "Invalido";
	public static final String CUP_CODIGO_NIVEL = "01";
	
	public static final String EMP_CARGA_PRE_EMPRENDEDORAS_LIST = "empCargaPreEmprendedorasList";
	public static final String EMP_CARGA_RUTA = "EMP_CARGA_RUTA";
	public static final String EMP_PROGRAMAS_LIST = "empProgramasList";
	public static final String EMP_CARGA_MASIVA_ERRORES_LIST = "cccErroresCargaMasivaList";
	public static final String EMP_TIPO_EMPRENDEDORA_LIST = "empTipoEmprendedoraList";
	public static final String EMP_REGIMEN_LIST = "empRegimenList";
	public static final String EMP_REQUISITOS_NO_CUMPLE_LIST = "empRequisitosNoCumpleList";
	public static final String EMP_TIPO_EMPRENDEDORA_REGULAR = "R";
	public static final String EMP_TIPO_EMPRENDEDORA_FASTTRACK = "F";
	public static final String EMP_POTENCIAL_EMPRENDEDORA = "0";
	public static final String EMP_EMPRENDEDORA = "2";
	public static final String EMP_RECOMENDADAS ="empRecomendadasList";
	
	public static final String STO_LISTA_DETALLE_PEDIDO_GP1_GP2="stoListaDetallePedidoGP1_GP2";
	public static final String STO_LISTA_DETALLE_PEDIDO_GP3_GP4_GP5 ="stoListaDetallePedidoGP3_GP4_GP5";
	
	/*SOA CONSULTORA PALANCA*/
	public static final String SOA_CONSULTORA_PALANCA_INDICADORFAMILIAPROT = "FP";
	public static final String SOA_CONSULTORA_PALANCA_INDASISTCONFEVENTA = "AUC";

	public static final String ZON_WEBSERVICE_RESULTADO_OK = "0";
	public static final String ZON_WEBSERVICE_RESULTADO_ERROR = "-1";
	
	public static final String HIP_CODIGO_SISTEMA = "HIP";
	public static final String HIP_CODIGO_PARAMETRO_TIPO_CONSULTORA = "001";
	public static final String HIP_CODIGO_PARAMETRO_DATOS_BUZON = "002";
	public static final String HIP_NOMBRE_PARAMETRO_TIPO_CONSULTORA = "tipoConsultora";
	
	public static final String ZON_UNIDAD_GEOGRAFICA_LIST 	= "zonUnidadGeograficaList";
	public static final String ZON_ESTRUCTURA_GEOPOLITICA_LIST = "zonEstructuraGeopoliticaList";
	public static final String ZON_UNIDAD_GEOGRAFICA_NIVEL1_LIST = "zonUnidadGeograficaNivel1List";
	public static final String ZON_UNIDAD_GEOGRAFICA_NIVEL2_LIST = "zonUnidadGeograficaNivel2List";
	public static final String ZON_UNIDAD_GEOGRAFICA_NIVEL3_LIST = "zonUnidadGeograficaNivel3List";
	public static final String ZON_UNIDAD_GEOGRAFICA_NIVEL4_LIST = "zonUnidadGeograficaNivel4List";
	
	public static final String ZON_MANT_ASIGNAR_TIPO_CARGO_LIST = "zonMantAsignarTipoCargoList";
	public static final String ZON_MANT_ASIGNAR_REGION_LIST = "zonMantAsignarRegionList";
	public static final String ZON_MANT_ASIGNAR_ZONA_LIST = "zonMantAsignarZonaList";
	
	public static final String ZON_MANT_ASIGNAR_CARGO = "A";
	public static final String ZON_MANT_CAMBIAR_CARGO = "C";
	
	public static final String ZON_MANT_GERENTE_ZONA = "GZ";
	public static final String ZON_MANT_GERENTE_REGION = "GR";
	
	public static final String ZON_MANT_CAMBIAR_TIPO_CARGO_LIST = "zonMantCambiarTipoCargoList";
	public static final String ZON_MANT_CAMBIAR_REGION_LIST = "zonMantCambiarRegionList";
	public static final String ZON_MANT_CAMBIAR_ZONA_LIST = "zonMantCambiarZonaList";
	public static final String ZON_MANT_ASIGNAR_TIPO_CARGO_CERRAR = "zonMantAsignarTipoCargoCerrar";
	public static final String ZON_MANT_CAMBIAR_TIPO_CARGO_CERRAR = "zonMantCambiarTipoCargoCerrar";
	public static final String ZON_MANT_ROTAR_TIPO_CARGO_CERRAR = "zonMantRotarTipoCargoCerrar";
	public static final String ZON_MANT_LICENCIA_CERRAR = "zonMantLicenciaCerrar";
	public static final String ZON_MANT_RETIRAR_CERRAR = "zonMantRetirarCerrar";
	
	public static final String ZON_MANT_OID_IDIONA = "zonMantOidIdioma";
	
	public static final String ZON_MANT_CODIGO_OPERACION_IN = "IN"; //ASIGNAR/INGRESO
	public static final String ZON_MANT_CODIGO_OPERACION_NM = "NM"; //CAMBIO DE CARGO/NOMBRAMIENTO
	public static final String ZON_MANT_CODIGO_OPERACION_LI = "LI"; //LICENCIA
	public static final String ZON_MANT_CODIGO_OPERACION_RZ = "RZ"; //REEMPLAZO
	public static final String ZON_MANT_CODIGO_OPERACION_RO = "RO"; //ROTAR
	public static final String ZON_MANT_CODIGO_OPERACION_RE = "RE"; //RETIRO
	
	public static final String ZON_MANT_ESTADO_CARGO_ACTIVA = "A";
	public static final String ZON_MANT_ESTADO_CARGO_INACTIVA = "I";
	public static final String ZON_MANT_ESTADO_REGISTRO_APROBADO = "A";
	
	public static final String ZON_MANT_INDICADOR_ESTADO_APROBADO = "A";
	public static final String ZON_MANT_INDICADOR_ESTADO_RECHAZADO = "R";
	public static final String ZON_MANT_INDICADOR_ESTADO_GRABADO = "G";
	
	public static final String ZON_MANT_ESTADO_CARGO_INACTIVA_TEMPORAL = "IT";
	
	public static final String ZON_MANT_TIPO_LICENCIA_LIST = "zonMantTipoLicenciaList";
	
	public static final String COB_PARAM_SERVIDOR_ENVIO_RECUPERACION_COBRANZA = "003";
	
	public static final int SOA_PAGINACION_TODOS = -1;
	
	public static final String SICC_ZONA_DISP_LIST 	= "listaZonaDisp";
	
	/* INI DA PER-SiCC-2012-0970 */
	public static final String STO_TIPO_DOCUMENTO_EXCEPCION_LIST ="stoTipoDocumentoExcepcionList";
	public static final String STO_VALIDACIONES_EXCEPCION_BY_DOCUMENTO ="stoValidacionesExcepcionByDocumento";
	public static final String STO_EXCEPCION_LIST ="stoExcepcionesList";
	public static final String STO_EXCEPCION_CLIENTES_LIST ="stoExcepcionClientesList";
	/* FIN DA PER-SiCC-2012-0970 */
	
	public static final String COM_CODIGO_SISTEMA = "COM";
	public static final String COM_CODIGO_PARAMETRO_TIPO_PROCESO_COMISION = "002";
	public static final String COM_NOMBRE_PARAMETRO_TIPO_PROCESO_COMISION = "tipoProcesoComision";

	
	public static final String EMP_MOTIVO_BAJA_LIST = "empMotivoBajaList";
	
	/* INI SA PER-SiCC-2012-1180 */
	public static final String MAV_CARGA_MASIVA_LIST = "mavCargaMasivalist";
	public static final String MAV_CARGA_MASIVA_RESUMEN_LIST = "mavCargaMasivaResumenlist";
	/* FIN SA PER-SiCC-2012-1180 */
	
	public static final String COM_COMISION_DETAL_LIST_2 =  "comComisionDetalList2";
	public static final String COM_COMISION_DETAL_LIST_3 =  "comComisionDetalList3";
	
	public static final int NUMERO_99999 = 99999;
	
	public static final String CODIGO_BASE_COMISION_01 = "01";
	public static final String CODIGO_BASE_COMISION_02 = "02";
	public static final String CODIGO_BASE_COMISION_03 = "03";
	public static final String CODIGO_BASE_COMISION_04 = "04";
	public static final String CODIGO_BASE_COMISION_05 = "05";
	public static final String CODIGO_BASE_COMISION_06 = "06";
	public static final String CODIGO_BASE_COMISION_07 = "07";
	
	public static final String MOTIVO_BLOQUEO_FNE ="F";
	public static final String MOTIVO_BLOQUEO_DEVOLUCIONES = "C";
	
	public static final String STO_CONTROL_FNE_PORCENTAJE_LIST = "stoControlFnePorcentajeList";
	
	/*INI JV PER-SiCC-2012-1093*/
	public static final String REC_RESULTADO_BR_LIST = "recResultadoBrList";
	public static final String REC_LISTA_RELACION_BOLETAS_RECOJO_LIST = "recRelacionBoletasRecojoList";
	public static final String REC_LISTA_RELACION_BOLETAS_RECOJO_CANTIDAD = "recRelacionBoletasRecojoCantidad";
	public static final String REC_LISTA_RELACION_BOLETAS_RECOJO_CORRECTAS_TEMPORAL_LIST = "recListaRelacionBoletasRecojoCorrectasTemporalList";
	public static final String REC_LISTA_RESULTADO_BR_CONSULTA_LIST = "recListaResultadoBrConsultaList";
	public static final String REC_LISTA_RESULTADO_BR_CONSULTA_DETALLE_LIST = "recListaResultadoBrConsultaDetalleList";
	public static final String REC_LISTA_AGREGAR_INDICADOR = "recListaAgregarIndicador";
	/*INI JV PER-SiCC-2012-1093*/
	public static final String EQUIFAX_ESTADO_APROBADO = "aprobar";
	public static final String OCR_USU_EQUI_SOCCRED = "027";
	public static final String OCR_PWD_EQUI_SOCCRED = "028";
	public static final String OCR_CANAL_EQUI_SOCCRED = "029";
	public static final String OCR_MODEL_EQUI_SOCCRED = "030";
	public static final String OCR_NOM_DATO_EQUI_SOCCRED = "031";
	public static final String OCR_TIPO_DATO_EQUI_SOCCRED = "032";
	public static final String OCR_VALOR_DATO_EQUI_SOCCRED = "033";
	public static final String OCR_TIPO_DOC_EQUI_SOCCRED = "034";
	public static final String OCR_MOSTRAR_INFO_ADICIONAL = "035";
	public static final String EQUIFAX_PATRON_INICIAL = "<Valor>";
	public static final String EQUIFAX_PATRON_FINAL = "</Valor>";
	
	public static final String EQUIFAX_PATRON_APE_PAT_INICIAL = "<ApellidoPaterno>";
	public static final String EQUIFAX_PATRON_APE_PAT_FINAL = "</ApellidoPaterno>";
	public static final String EQUIFAX_PATRON_APE_MAT_INICIAL = "<ApellidoMaterno>";
	public static final String EQUIFAX_PATRON_APE_MAT_FINAL = "</ApellidoMaterno>";
	public static final String EQUIFAX_PATRON_NOMBRES_INICIAL = "<Nombres>";
	public static final String EQUIFAX_PATRON_NOMBRES_FINAL = "</Nombres>";


	public static final String PAIS_CLE = "CLE";
	public static final String PAIS_PE = "PE";
	public static final String PAIS_BOE = "BOE";
	
	public static final String EMP_CARGA_REASIGNACION_LIST = "empCargaReasignacionList";

	public static final String OCR_CARGA_ERROR_VALIDACION = "Error al procesar executeValidacionAutomaticaDocumentoSTO:";

	public static final String CONSULTA_COMISION_GERENTE_REGION = "consultaCOMComisionGerenteRegionList";
	public static final String REPORTE_COMISION_GERENTE_REGION = "reporteCOMComisionGerenteRegionList";
	
	public static final String DIR_VENTAS_LIST = "listaDirectorioVentas";
	
	public static final String COB_PROCESO_BATCH_ASIGNACION_ANUTOMATICA_CARTERA = "10";
	
	public static final String VALUE_PARAMS = "valueParams";
    public static final String CRA_CARGA_MATRIZ_DIAS_LIST = "craCargaMatrizDiasList";
    public static final String CRA_CARGA_MATRIZ_DIAS_FUERA_PERIODO_LIST = "craCargaMatrizDiasFueraPeriodoList";

	public static final String STO_SEGU_PEDID_LIST = "stoSeguimientoPedidosList";
	
	public static final String HIP_OPCION_SEGUIMIENTO_PEDIDOS = "HIP-20";	
	public static final String IND_EXITO = "indExito";
	public static final String CODIGOS_ERRADOS = "numeroErrados";
	
	public static final String CRA_GRUPOS_LIST="craGruposList";
	public static final String CRA_GRUPOS_COMBO_LIST="craGruposComboList";
	public static final String CRA_REGION_NO_ASIGNADAS = "craRegionNoAsignadas";
	public static final String CRA_ZONA_NO_ASIGNADAS = "craZonaNoAsignadas";
	public static final String CRA_ZONA_ASIGNADAS = "craZonaAsignadas";
	public static final String CRA_ZONA_REGENERAR = "craZonaRegenerar";
	public static final String CRA_ZONAS_GRUPO_FINAL="craZonasGrupoFinal";
	public static final String CRA_ZONAS_GRUPO_INICIAL="craZonasGrupoInicial";	
	public static final String CRA_TIPO_ACTIVIDAD_LIST ="craTipoActividadList";
	public static final String CRA_ACTIVIDADES_LIST="craActividadesList";
	public static final String CRA_CLASE_LIST="craClaseList";
	public static final String CRA_INDICADOR_DIAS_LIST="craIndicadorDiasList";
	public static final String CRA_INDICADOR_DIAS_CONTINUOS="CONTINUOS";
	public static final String CRA_INDICADOR_DIAS_LABORABLES="LABORABLES";
	public static final String CRA_TIPO_ACTIVIDAD_CON_ORIGEN="CON ORIGEN";
	public static final String CRA_TPO_ACTIVIDAD_FIJO="FIJO";
	public static final String CODIGO_AREA_RESPONSABLE="004";
	public static final String CRA_LISTA_FECHA_FERIADO="listaFechaFeriado";	
	public static final String CRA_LISTA_FECHA_NO_LABORABLES="listaFechaNoLaborables";
	public static final String CRA_DIA_SABADO="SÁBADO";
	public static final String CRA_DIA_DOMINGO="DOMINGO";
	
	public static final String ACC_INDICADOR_PROCESO_REGISTRADO = "R";
	
	public static final String SICC_TIPO_COMISION = "siccTipoComision";
	
	public static final String SMS_MENSAJES_LIST = "smsList";
	public static final String SMS_LIST_MENS_INT = "interfazSMSGenerarMensajeList";
	
	public static final String COM_CODIGO_SUBGERENCIA = "comCodigoSubGerencia";
	
	public static final String ZON_ESTADO_ACTIVO = "1";
	
	public static final String ZON_ESTADO_INACTIVO = "0";
	public static final String CRA_CARGA_CRONO_FASE1_LIST = "craCargaCronoFase1List";
	public static final String CRA_CARGA_CRONO_FASE1_FUERA_LIST = "craCargaCronoFase1FueraList";

	public static final String CRA_CARGA_CRONO_FASE2_LIST = "craCargaCronoFase2List";
	public static final String CRA_CARGA_CRONO_FASE2_FUERA_LIST = "craCargaCronoFase2FueraList";
	
	public static final String COM_COMIS_INDICADOR_BASE_COMISION = "comComisIndicadorBaseComision";
	public static final String COM_COMIS_CODIGO_TIPO_BASE_COMISION ="comComisCodigoTipoBaseComision";
	
	public static final String PEJ_MANT_PROGRAMA_EJECUTIVAS_LIST = "pejProgramaEjecutivasList";
	
	public static final String PEJ_TAB_PROGRAMA_EJECUTIVAS_FASES = "fasesTab-tab";
	public static final String PEJ_TAB_PROGRAMA_EJECUTIVAS_NIVELES = "nivelesTab-tab";
	public static final String PEJ_TAB_PROGRAMA_EJECUTIVAS_RANGOS = "rangosTab-tab";
	public static final String PEJ_TAB_PROGRAMA_EJECUTIVAS_GRUPOS = "gruposTab-tab";
	public static final String PEJ_TAB_PROGRAMA_EJECUTIVAS_PORCENTAJES = "porcentajesTab-tab";
	public static final String PEJ_TAB_PROGRAMA_EJECUTIVAS_TIPO_ABONO = "tipoAbonoTab-tab";


	public static final Object PROCESO_IMPRESION_APE = "APE";

	
	public static final String ZON_CODIGO_SISTEMA = "ZON";
	public static final String ZON_CODIGO_PARAM = "002";
	public static final String ZON_NOMB_PARAM = "indNombGzGr";
	public static final String ZON_VALOR_PARAM = "1";
	
	public static final String COM_TIPO_CLASIFICACION_LIST = "comTipoClasificacionList";
	public static final String COM_CLASIFICACION_LIST = "comClasificacionList";
	public static final String COM_CLASIFICACION_DISPLAY = "displayClasificacionMostrar"; 
	
	public static final String PED_CARGA_CLIENTE_LIST = "pedClientesList";
	public static final String PED_CARGA_CLIENTE_RESU_LIST = "pedClientesResuList";
	
	
	public static final String ZON_VALOR_SI = "SI";
	public static final String ZON_VALOR_NO = "NO";
	
	public static final String ZON_MANT_CARG_LIST = "zonMantCargoList";
	public static final String ZON_ESTADOS_LIST = "zonEstadosList";

	public static final String FLX_ESTADOS_LIST = "flxEstadosList";

	public static final String CED_CODIGO_SISTEMA = "CED";
	public static final String CED_CODIGO_RUTA_HISTORICA = "001";
	
	public static final String MANTENIMIENTO_REC_DIGITACION_CDR_LISTA_OFERTAS = "mantenimientoRecDigitacionCdrListaOfertas";
	public static final String MANTENIMIENTO_REC_DIGITACION_CDR_OFERTA_PARAMETRO = "mantenimientoRecDigitacionCdrOfertaParametro";
	
	public static final String FTP_DEFAULT_PORT = "21";
	
	public static final String LET_CODIGO_SISTEMA = "LET";
	public static final String LET_CODIGO_PARAM_001 = "001";
	public static final String LET_CODIGO_PARAM_005 = "005";
	public static final String LET_NOMBRE_PARAM_001 = "indAsignaLider";
	
	public static final String LET_TIPO_REGION = "letTipoRegion";
	public static final String LET_TIPO_ZONA = "letTipoZona";
	public static final String LET_TIPO_SECCION = "letTipoSeccion";
	
	public static final boolean LET_TIPO_DESHABILITADO = true;
	public static final boolean LET_TIPO_HABILITADO = false; 
	
	public static final String LET_UA_RESPONSABLE_REGION = "R";	
	public static final String LET_UA_RESPONSABLE_ZONA = "Z";
	public static final String LET_UA_RESPONSABLE_SECCION = "S";
	
	public static final String LET_UA_TODOS_RESPONSABLES = "T";
	
	public static final String LET_SESSION_TIPO_BUSQUEDA = "tipobusquedaSession";
	
	public static final String LET_INDICADOR_ZON_BAS_PAIS = "letIndicadorZonBasPais";
	
	public static final String LET_CODIGO_REGION_ELEGIDO = "letCodigoRegionElegido";
	public static final String LET_CODIGO_ZONA_ELEGIDO = "letCodigoZonaElegido";
	
	public static final String INC_ESTADO_LIST = "incEstadoList";
	public static final String INC_VIGENCIA_LIST = "incVigenciaList";
	public static final String INC_TIPO_VENTA_VENTA_CATALOGO = "3";
	public static final String INC_CICLO_VIDA_DEFAULT = "4";
	public static final String INC_RECOMENDACION_PERIODO_LIST = "incRecomendacionPeriodoList";
	public static final String INC_INDICADOR_TIPO_RECOMENDACION_LIST = "incIndicadorTipoRecomendacionList";
	public static final String INC_BONIFICACION_PERIODO_LIST = "incBonificacionPeriodoList";
	public static final String INC_PERIODO_DESPACHO_LIST = "incPeriodoDespachoList";
	public static final String INC_PUNTAJE_EXIGIDO_LIST = "incPuntajeExigidoList";
	public static final String INC_PUNTAJE_EXIGIDO_LIST_TEMPO = "incPuntajeExigidoListTempo";
	
	public static final String CDR_INDICADOR_VALIDA_PERIODO_EN_PEDIDO = "PE";
	
	public static final String STO_IND_DEV_OFERTA = "STO_IND_DEV_OFERTA";
	public static final String STO_IND_CDR_LINEA_18 = "STO_IND_CDR_LINEA_18";
	public static final String STO_DESV_TRQ = "STO_DESV_TRQ";
	public static final String STO_DESV_TRQ_OPER = "STO_DESV_TRQ_OPER";
	public static final String STO_IND_CAMB_IGUA = "STO_IND_CAMB_IGUA";
	
	public static final String PEJ_PROGRAMA_EJECUTIVAS_ARCHIVO_LIST = "programaEjecutivasArchivolist";
	public static final String PRE_CARGA_ESTIMADOS_ARCHIVO_LIST = "cargaEstimadosArchivolist";
	public static final String PEJ_TIPO_CARGA_LIST = "pejTipoCargaList";
	
	public static final String PEJ_TIPO_REPORTE_LIST = "pejTipoReporteList";
		
	public static final String MAE_CODIGO_SISTEMA = "MAE"; 
	public static final String MAE_CODIGO_PARAMETRO_REPORTE = "013";
	public static final String MAE_NOMBRE_PARAMETRO_REPORTE = "indicadorFormatoReporteMae";
	public static final String MAE_NOMB_PARAM = "indRegComp";
	public static final String MAE_PARAMETRO_CAMPOS_COMPROMISO = "maeParametroCamposCompromiso";
	public static final String MAE_NOMB_PARAM_OTROS = "indSeccionOtros";
	public static final String MAE_MOSTRAR_PESTANIA_OTROS = "maeMostrarPestaniaOtros";
	public static final String MAE_NOMB_PARAM_FACT_ELECT = "indicadorFacturacionElectronica";
	public static final String MAE_NOMB_PARAM_CALC_PERCEP = "indMuestraOpcionCalcularPercep";
	public static final String MAE_LISTA_DIRECCIONES_TELEFONO = "maeListaDireccionesTelefono";
	public static final String MAE_DIRECCIONES_TELEFONO_OK =  "Valido";
	public static final String MAE_DIRECCIONES_TELEFONO_ERROR = "Invalido";
	public static final String MAE_REGISTROS_VALIDOS_LIST = "maeRegistrosValidosList";
	public static final String MAE_REGISTROS_CON_ERROR_LIST = "maeRegistrosConErrorList";
	public static final String MAE_DIRECCIONES_TELEFONO_REGISTRADAS_AL_PROGRAMA_LIST = "maeDireccionesTelefonoRegistradasAlProgramaList";
	public static final String MAE_PARAM_INDICADOR_CAMPOS_ADICIONALES = "indCamposAdicionales";
	public static final String MAE_PARAM_INDICADOR_LLAMADA_BIENVENIDA = "indMuestraReporteMAEBienvenida";

	public static final String IND_RECEP_OCR = "OCR";
	public static final String IND_RECEP_WEB = "Web";
	public static final String IND_RECEP_DIG = "Digitacion";
	
	public static final String HIP_OPCION_FLEXIPAGO = "HIP-21";	
	public static final String FLX_CONSU_DETALLE_LIST = "flxConsuDetalleList";
	public static final String FLX_USO_DETALLE_LIST = "flxUsoDetalleList";
	
	public static final String REPORTE_MAE_CLIENTE_LIST = "reporteClientesList";
	
	public static final String LET_TITLE_LISTA_LIDERES_CODIGO = "letListaLideresCodigo";
	
	public static final String PRE_MATRIZ_FACTURACION_NULOS_DUPLICADOS_LIST = "preMatrizFacturacionNulosDuplicadosList";
	
	public static final String MAE_NOMB_PARAM_PERIODO_VIGENTE = "indPeriVigeActi";
	
	public static final String HIP_CODIGO_PARAMETRO_DIGI_SIMP = "003";
	public static final String HIP_NOMBRE_PARAMETRO_MOSTRAR_DIGI_SIMP = "indMostrarDigiSimp";
	
	public static final String MEN_ARCHIVO_LIST = "menArchivolist";	
	public static final String MEN_TIPO_CARGA_LIST = "menTipoCargaList";
	
	public static final String MEN_FALTANTE_ANUNCIADO_LIST = "menFaltanteAnunciadoList";
	public static final String MSG_CODIGO_SISTEMA = "MSG";
	public static final String MSG_CODIGO_PARAMETRO_TIPO_CARGA_FALTANTE_ANUNCIADO = "002";
	
	public static final String MAE_VALID_PAQUE_DOCUM = "VAL_PANT";
	public static final String MAE_IMPRI_PAQUE_DOCUM = "IMPPQDOC";
	public static final String MAE_VALID_NUME_DOCUM = "VAL_CRT_IDENT";
	public static final String MAE_MOD_VALI_CV = "CV";
	public static final String SICC_TIPO_REGIMEN_LIST = "siccTipoRegimenList";
	
	public static final String MEN_ARCHIVO_CONFERENCIAS_LIST = "menArchivoConferenciaslist";
	
	public static final String MEN_HORAS_LIST = "menHorasList";
	public static final String MEN_MINUTOS_LIST = "menMinutosList";
	public static final String MEN_CODIGO_ZONA_ELEGIDO = "menCodigoZonaElegido";
	
	public static final String MEN_CONFERENCIAS_LIST = "menConferenciasList";
		
	public static final String SICC_REGION_CONFE_LIST = "siccRegionConfeList";
	public static final String SICC_ZONA_CONFE_LIST = "siccZonaConfeList";
	
	public static final String CUP_TIPO_PEDIDO_LIST = "cupTipoPedidoList";

	public static final String MAV_CODIGO_SISTEMA = "MAV";
	public static final String MAV_CODIGO_PARAMETRO_VALIDA_PRECIO = "002";

	public static final int MAV_CONRES_ESTATUS_CLIENTE = 61;
	public static final int MAV_CONRES_ESTATUS_CLIENTE_REST = 62;
	
	public static final String EMA_PARAMETRO_GERENTE_REGION = "emaGerenteRegionDefecto";
	
	public static final String SICC_TIPO_MAV_LIST = "siccTipoMavList";

	public static final String SICC_REGION_STO_FLX_LIST = "siccRegionStoFlxList";
	public static final String SICC_ZONA_STO_FLX_LIST = "siccZonaStoFlxList";
	
	public static final String INDICADOR_FORMATO_REPORTE_MAE ="indicadorFormatoReporteMae";
	
	public static final String FLX_TIPO_VARIABLE_CALCULO_PROBABILIDAD_INCUMPLIMIENTO = "1";
	
	public static final String FLX_GRUPO_LIST = "flxGrupoList";
	public static final String FLX_MOTIVO_RECHAZO_LIST = "flxMotivosRechazoList";
	public static final String FLX_MOTIVO_RECOMENDACION_LIST = "flxMotivosRecomendacionList";
	public static final String FLX_ESTATUS_RECOMENDACION_LIST = "flxEstatusRecomendacionList";
	public static final String FLX_ESTATUS_RECHAZO_LIST = "flxEstatusRechazoList";
	
	public static final String FLX_GRUPO_REGION_LIST = "flxGrupoRegionList";
	public static final String FLX_REGIONES_DISPONIBLES = "flxRegionesDisponibles";
	public static final String FLX_REGIONES_ASIGNADAS = "flxRegionesAsignadas";
	
	public static final String FLX_GESTION_CONSULTORAS_LIST = "flxGestionConsultorasList";
	
	public static final String FLX_CODIGO_PARAMETRO_VERSION = "16";
	
	public static final String INDICADOR_CONSULTA_COMISION_GERENTE_ZONA = "003";

	public static final String STO_CODIGO_INDICADOR_EJECUCION_ENVIO_SCAA = "017";
	public static final String STO_CODIGO_PERIODO_EJECUCION_ENVIO_SCAA = "018";	
	public static final String STO_PROCESO_BATCH_ENVIO_SOLUCIONES_CENTRO_ACOPIO = "15";
	
	public static final String MAE_VALID_DOCUM_LEGAL = "VAL_FACELEC";
	public static final String MAE_IMPRI_DOCUM_LEGAL = "FAC01";
	
	public static final String HIP_VALID_COMPLETA_CEROS_DOCUMENTO_IDENTIDAD = "VAL_IDENTVAR";

	public static final String CCC_REPORTE_MENSUAL = "M";
	public static final String CCC_REPORTE_ACUMULADO = "A"; 
	
	public static final String HIP_OPCION_CONSULTA_BOLETA_RECOJO = "HIP-22";	
	
	public static final String REC_INDICADOR_CAMBIA_OBLIGATORIO_SI = "SI";
	
	public static final String INC_FALTANTES_LIST = "incFaltantesList";

	public static final String STO_SC_ERROR_REFERIDAS_LIST = "stoSCErrorReferidasList";
	
	public static final String REC_LISTA_ATENCIONES_MASIVAS_LIST = "recListaResultadoAtencionesMasivasList";
	
	public static final int FLAG_CERO = 0;
	
	public static final String MAE_ESTADO_CLIENTE_LIST = "maeEstadoClienteList";

	public static final int MAX_VALUE_OF_LIST_PERMIT = 1500;

	
	public static final String CODIGO_SUBTIPO_CLIENTE_GERENTE_REGION = "01";
	
	public static final String CODIGO_SUBTIPO_CLIENTE_GERENTE_ZONA = "02";
	
	public static final String ZON_TIPO_UA_ZONA = "Z";
	public static final String ZON_TIPO_UA_REGION = "R";	
	public static final String ZON_CANTIDAD_UA_VARIOS = "V";
	public static final String ZON_CANTIDAD_UA_UNICO = "U";
	
	public static final String SGR_CODIGO_SISTEMA = "SGR";
	public static final String SGR_CODIGO_PARAMETRO_FAMILIA_PROTEGIDA = "002";

	//INI PER-SiCC-2015-0368
	public static final String HIP_NOMBRE_PARAMETRO_FAMILIA_PROTEGIDA = "indMostrarFamProtegida";
	//FIN PER-SiCC-2015-0368
		
	public static final String HIP_OPCION_SEGUROS = "HIP-23";
	public static final String HIP_HISTORIAL_COBROS_SEGURO_LIST = "hipHistorialCobrosSeguroList";
	
	public static final String CCC_PARAMETRO_RUTA_ARCHIVO_EMAIL = "ZZ-MAIL";
	
	public static final String CCC_PARAMETRO_RUTA_ARCHIVO_FTP = "ZZ-FTP";
	
	public static final String CCC_CODIGO_SISTEMA = "CCC";
	
	public static final String CCC_CODIGO_PARAMETRO_SERVIDOR_FTP = "001";
	public static final String CCC_CODIGO_PARAMETRO_PUERTO_FTP = "002";
	public static final String CCC_CODIGO_PARAMETRO_USUARIO_FTP = "003";
	public static final String CCC_CODIGO_PARAMETRO_CLAVE_FTP = "004";
	public static final String CCC_CODIGO_PARAMETRO_DIRECTORIO_FTP = "005";	
	public static final String CCC_CODIGO_PARAMETRO_EMAIL_DESTINO = "006";
	public static final String CCC_CODIGO_PARAMETRO_ASUNTO_EMAIL_DESTINO = "007";
	
	public static final String CCC_INTERFACES_PAQUETE_LIST = "cccInterfacesPaqueteList";
	
	public static final String HIP_OPCION_METAS = "HIP-24";
	
	public static final String MAE_VALID_VAL_BARRIO = "VAL_BARRIO";
	
	public static final String PED_MOTO_MINIMO_ACCION_REGISTRO = "R";
	public static final String PED_MOTO_MINIMO_ACCION_MODIFICACION = "M";
	public static final String PED_MOTO_MINIMO_ACCION_ELIMINACION = "E";
	
	public static final String PED_MOTO_MINIMO_STATUS_ANTES = "A";
	public static final String PED_MOTO_MINIMO_STATUS_DESPUES = "D";

	public static final String REP_MAE_NUEVAS_PRIMER_PEDIDO = "2";
	public static final String REP_MAE_NUEVAS_SEGUNDO_PEDIDO = "3";
	public static final String REP_MAE_NUEVAS_TERCER_PEDIDO = "4";
	public static final String REP_MAE_NUEVAS_CONSULTORAS_ACTIVAS = "5";
	public static final String REP_MAE_NUEVAS_CONSULTORAS_INACTIVAS = "6";
	
	public static final String SICC_REGION_FALTANTE_LIST = "siccRegionFaltanteList";
	
	public static final String SICC_ZONA_FALTANTE_LIST = "siccZonaFaltanteList";
		
	public static final String SICC_SGR_ESTADO_LIST = "siccSgrEstadoList";
	
	public static final String SICC_SGR_ORIGEN_LIST = "siccSgrOrigenList";
	
    public static final String FLX_CODIGO_SISTEMA = "FLX";
	
	public static final String FLX_INTERFACES_PAQUETE_LIST = "flxInterfacesPaqueteList";
	
	public static final String INC_CONF_CONCURSO_CUPON_ELECTR_LIST = "incConfiguracionConcursoCuponElectronicoList";
	
	public static final String HIP_OPCION_CONSULTA_FAD = "HIP-25";
	
	public static final String HIP_FACTURACION_ADICIONAL_LIST = "hipFacturacionAdicionalList";

	public static final String SICC_REGION_FALTANTE_INGRESO_LIST = "siccRegionFaltanteIngresoList";
	
	public static final String SICC_ZONA_FALTANTE_INGRESO_LIST = "siccZonaFaltanteIngresoList";

	public static final String HIP_OPCION_RETAIL = "HIP-26";
	public static final String HIP_RETAIL_PERIODO_LIST = "hipRetailPeriodoList";
	public static final String HIP_RETAIL_CABECERA_LIST = "hipRetailCabeceraList";
	public static final String HIP_RETAIL_DETALLE_LIST = "hipRetailDetalleList";
	public static final String HIP_RETAIL_DETALLE_PUNTOS_CONCURSO_LIST = "hipRetailDetallePuntosConcursoList";
		
	public static final String STO_CODIGO_PARAMETRO_FAD_EJECUCION = "019"; 
	public static final String STO_CODIGO_PARAMETRO_FAD_RUTA = "020";
	public static final String STO_CODIGO_PARAMETRO_FAD_EMAIL_DESTINO = "021";
	public static final String STO_CODIGO_PARAMETRO_FAD_ASUNTO_EMAIL_DESTINO = "022";
	public static final String STO_CODIGO_PARAMETRO_FAD_EMAIL_ORIGEN = "023";
	
	public static final String PED_ARCHIVO_LIST = "pedidossArchivolist";
	
	public static final String LET_PROGRAMA_CORPORATIVO_LIST = "letProgramaCorporativoList";
	public static final String LET_TAB_PROGRAMA_CORPORATIVO_RANGO_NIVEL = "rangoNivelTab-tab";
	public static final String LET_TAB_PROGRAMA_CORPORATIVO_RANGO_RETENCION = "rangoRetencionTab-tab";
	public static final String LET_TAB_PROGRAMA_CORPORATIVO_TRAMOS = "tramosTab-tab";
	public static final String LET_TAB_PROGRAMA_CORPORATIVO_PREMIOS = "premiosTab-tab";
	public static final String LET_MANT_RANGO_NIVEL_CERRAR = "letMantRangoNivelCerrar";
	public static final String LET_MANT_RANGO_RETENCION_CERRAR = "letMantRangoRetencionCerrar";
	public static final String LET_MANT_TRAMOS_CERRAR = "letMantTramosCerrar";
	public static final String LET_MANT_PREMIOS_CERRAR = "letMantPremiosCerrar";
	public static final String LET_TIPO_NIVEL_EXITO_LIST = "letTipoNivelExitoList";
	
	public static final String LEC_TIPO_PAGO_LIST = "letTipoPagoList";
	public static final String LEC_TIPO_CARGA_LIST = "letTipoCargaList";
	public static final String LEC_PROGRAMA_LIST = "letProgramaList";
	public static final String LEC_PROGRAMA_ARCHIVO_LIST = "letProgramaArchivoList";
	public static final String LEC_GRUPO_REGION_LIST = "lecGrupoRegionList";
	
	public static final String MAV_TIPO_CLIENTE_LIST =  "siccMAVTipoClienteList";	
	public static final String MAV_ORIGEN_LIST =  "siccMAVOrigenList";
	
	public static final String CONEXION_EXTERNA_ORACLE = "ORA";
	public static final String CONEXION_EXTERNA_FOX = "FOX";
	
	public static final String EMP_PARAMETROS_GENERALES_LIST = "empParametrosGeneralesList";

	public static final String ZON_CODIGO_SUBGERENCIA_DEFAULT = "01";

    public static final String SICC_CAPACITADORA_LIST = "siccCapacitadoraList";
	
	public static final String ITEM_INDICADOR_CAPACITADORA = "003";
	
	public static final String INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION = "R";
	public static final String INDICADOR_TIPO_SELECCION_CAPACITADORA_CAPACITADORA= "C";
	
	public static final String COM_LISTA_REGION_OBJETIVO = "listadoRegionObjetivoVenta";

	public static final String SICC_TIPO_CONSULTA_LIST = "listaTipoConsulta";
	
	public static final String INDICADOR_CONSULTA_COMISION_GERENTE_REGION = "004";
	
	public static final String IMP_NOMBRE_PARAMETRO_NRO_ENVIOS = "numeroEnvios";

	public static final String STO_CONTROL_FNE_PORCENTAJE_NRO_LINEAS_DEFECTO = "25";
	public static final String STO_CONTROL_FNE_PORCENTAJE_NRO_LINEAS_MAXIMO = "9999";

	public static final String IMP_TIPO_DOCUMENTO_LIST = "impTipoDocumentoList";

	public static final String STO_MOTIVOS_GESTION = "stoMotivosGestion";
	
	public static final String LET_OBJETIVOS_LIST = "letObjetivosList";
	public static final String LET_OBJETIVOS_ERRORES_LIST = "letObjetivosErroresList";
			
	public static final String EMP_ZONAS_INTERNET_LIST = "empZonasInternetList";
	public static final String EMP_REGIONES_LIST = "empRegionesList";
	public static final String EMP_ZONAS_LIST = "empZonasList";
	public static final String ZON_ROL_LIST = "zonRolList";
	public static final String ZON_PERFIL_LIST = "zonPerfilList";
	public static final String ZON_CODIGO_CARGOS_LIST = "zonCodCargosList";
	public static final String ZON_INDI_RESU_DETA = "zonIndicadorResDet";
	public static final String ZON_CODIGO_PERFIL_TITULAR = "TI";
	
	public static final String INDICADOR_CANT_DATOS_GRILLA="004";
	public static final String CRA_REGION_INTERNET_NO_ASIGNADAS = "craRegionInternetNoAsignadas";
	public static final String CRA_ZONA_INTERNET_NO_ASIGNADAS = "craZonaInternetNoAsignadas";
	public static final String CRA_ZONA_INTERNET_ASIGNADAS = "craZonaInternetAsignadas";
	public static final String CRA_ZONAS_INTERNET_GRUPO_INICIAL = "craZonasInternetGrupoInicial";
	public static final String CRA_ZONAS_INTERNET_GRUPO_FINAL = "craZonasInternetGrupoFinal";

	public static final String OCR_MOSTRAR_TAB_METAS = "037";
	public static final String OCR_MOSTRAR_IND_BUZON = "038";
	
	public static final String HIP_OPCION_ACTUALIZAR_DATOS_CLIENTE = "HIP-28";

	public static final String SICC_ESTATUS_LIST = "siccEstatusList";
	public static final String HIP_OPCION_EJECUTIVAS = "HIP-27";
	public static final String HIP_CONSULTA_EJECUTIVAS_LIST = "hipConsultaEjecutivasList";
	public static final String HIP_ETAPAS_EJECUTIVAS_LIST = "hipEtapasEjecutivasList";
	
	public static final String SGR_ASEGURADORA_LIST = "sgrAseguradoraList";
	
	public static final String HIP_OPCION_ACTUALIZAR_DIRECCION_CLIENTE = "HIP-29";
	
	public static final String PED_CONSULTORAS_CHEQUEAR_LINEA_DEFECTO = "25";
	public static final String PED_CONSULTORAS_CHEQUEAR_LINEA_MAXIMA = "100";
	
	public static final String CCC_TIPO_ORIGEN_LIST="cccTipoOrigenList";
	public static final String MAE_TIPO_REGISTRO_REGION_ZONA = "R";
	public static final String MAE_TIPO_REGISTRO_CAPACITADORA = "C";
		
	public static final String STO_SEGU_PEDID_LIST2 = "stoSeguimientoPedidosList2";
	public static final String STO_SEGU_PEDID_LIST3 = "stoSeguimientoPedidosList3";
	
	public static final String FAC_TIPO_DOCUMENTO_LIST = "facTipoDocumentoList";
	
	public static final String FAC_LISTA_ESTADO_FACTURACION = "listadoEstadoFacturacion";
	public static final String FAC_LISTA_ESTADO_FACTURACION_DETALLADO = "listadoEstadoFacturacionDetallado";
	public static final String FAC_LISTA_ESTADO_FACTURACION_CONSOLIDADO = "listadoEstadoFacturacionConsolidado";

	public static final String MAV_CODIGO_TIPO_CARGO_GR = "GR";
	public static final String MAV_CODIGO_TIPO_CARGO_GZ = "GZ";
	public static final String MAV_CODIGO_TIPO_CARGO_EE = "EE";
	public static final String MAV_CODIGO_TIPO_CARGO_MVR = "MVR";
	public static final String MAV_CODIGO_TIPO_CARGO_MVZ = "MVZ";
	public static final String MAV_CODIGO_INDICADOR_ENVIO_P = "P";
	public static final String MAV_CODIGO_INDICADOR_ENVIO_E = "E";
	public static final String MAV_CODIGO_INDICADOR_ENVIO_D = "D";
	public static final String MAV_COD_INDIC_ALTA = "1";
	public static final String MAV_COD_INDIC_BAJA = "0";
	
	public static final String HIP_PEDIDO_FACTURADO_SOLICITADO_LINEA_DEFECTO = "25";
	public static final String HIP_PEDIDO_FACTURADO_SOLICITADO_LINEA_MAXIMA = "100";
	public static final String HIP_PEDIDO_FACTURADO_SOLICITADO_LINEA_DEFECTO_PS = "25";
	public static final String HIP_PEDIDO_FACTURADO_SOLICITADO_LINEA_MAXIMA_PS = "100";
	public static final String HIP_LINEA_DEFECTO_PS = "lineaDefectoPS";
		
	public static final String SICC_ZONA_SELECCIONADA_LIST = "siccZonaSeleccionadaList";

	public static final String HIP_CODIGO_PARAMETRO_NUMERO_PEDIDO = "008";
	public static final String HIP_NOMBRE_PARAMETRO_NUMERO_PEDIDO = "valorNumeroPedido";
	
	public static final String HIP_NOMBRE_PARAMETRO_CONCURSO_CONSOLIDADO = "indPartInc";
	
	public static final String FAC_NOMBRE_CARPETA_BOLETAS = "CDRBOLETA";
	public static final String FAC_NOMBRE_CARPETA_FACTURAS = "Factura";
	public static final String FAC_NOMBRE_CARPETA_NOTAS_CREDITO = "NCredit";
	public static final String FAC_NOMBRE_CARPETA_NOTAS_DEBITO = "NDebit";
	
	public static final String INTERFAZ_PED_ENVIAR_MATRIZ_FACTURACION_FUTURA = "PED-6";
	
	public static final String COM_LISTA_COMISION_RETAIL = "listadoComisionRetail";

        public static final String APE_CENTRO_ACOPIO_LIST = "apeCentroAcopioList";
	
	public static final String APE_CIA_TRANSPORTE_LIST = "apeCiaTransporteList";
	
	public static final String MAE_HORAS_LIST = "maeReporteHorasList";
	
	public static final String PREFIJO_REPORTE_GESTIONAR_CONSULTORA = "reporteGestionarConsultora";
	
	public static final String SICC_INC_ESTADO_LIST = "INCestadosList";
	
	public static final String CODIGO_ESTADO_DEFAULT = "T";
	
	
/* FIN DA PER-SiCC-2012-0970 */
	
	public static final String IMP_CODIGO_SISTEMA = "IMP";
	public static final String IMP_CODIGO_PARAMETRO_PORCENTAJE_DESVI = "009";
	public static final String IMP_CODIGO_PARAMETRO_PEDIDO_PROMEDIO = "010";
	
	public static final String MANTENIMIENTO_PARAMETRO_PAIS_LIST = "mantenimientoBASParametroPaisList";
	public static final String MANTENIMIENTO_PARAMETRO_VALIDACION_LIST = "mantenimientoSTOParametroValidacionList";
	public static final String MANTENIMIENTO_PARAMETRO_PROCESO_IMP_LIST = "mantenimientoIMPParametroProcesoImpresionList";
	
	
	public static final String IMP_NOMBRE_PARAMETRO_CENTRO = "psCentro";
	
	public static final String STO_VALID_NUM = "STO_VALID_NUM";
	
	public static final String STO_DATO_ENVIA_BR = "STO_DATO_ENVIA_BR";
	public static final String STO_DATO_ENVIA_BR_DEFAULT = "C";

	public static final String CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA = "P";
	public static final String CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES = "N";
	
	public static final String SCC_NOMBRE_LIOER_RECOMENDANTE = "SCC_IND_LIDER_RECOM";
	
	public static final String PARAMETRO_TIPO_PROCESO_1 = "1";
	
	public static final String SICC_SITUACION_LIST = "siccSituacionList";
	

	public static final String TIPO_OPERACION_INSERTAR = "I";
	public static final String TIPO_OPERACION_ACTIVAR = "A";
	public static final String TIPO_OPERACION_DESACTIVAR = "D";
	public static final String TIPO_OPERACION_MODIFICAR = "M";
	public static final String TIPO_OPERACION_ELIMINAR = "E";
	
	public static final String MOMENTO_REGISTRO_ANTES = "A";
	public static final String MOMENTO_REGISTRO_DESPUES = "D";
	
	public static final String PED_MONTO_MAXIMO_LIST = "pedMontoMaximoList";
	

	public static final String STO_TIPO_DOCUMENTO_VALIDACIONES_LIST = "stoTipoDocumentoValidacionesList";
	public static final String STO_VALIDACIONES_LIST = "stoValidacionesList";
	public static final String STO_VALIDACIONES_SECUENCIA_LIST = "stoValidacionesSecuenciaList";
	
	public static final String LET_TRAMO_LIST = "letTramosList";
	
	public static final String COM_CODIGO_BASE_COMISION_07 = "07";
	
	public static final String COM_CODIGO_TIPO_CARGO_GZ = "GZ";
	
	public static final String COM_CODIGO_TIPO_CARGO_GR = "GR";
	
	public static final String COM_GERENTE_RETIRADAS_LIST = "comGerenteRetiradasList";
	
	public static final String COM_GERENTE_RETIRADAS_ESTADO_PENDIENTE = "PENDIENTE";
	
	public static final String FAC_CODIGO_SISTEMA = "FAC";
	public static final String FAC_NOM_PARA_DIFERENCIA_SYSDATE = "indDifSysdate";
	public static final String FAC_VAL_PARA_DIFERENCIA_SYSDATE_DEFAULT = "3";

	public static final String FLX_MOTIVO_LIST = "flxMotivoList";
	
	public static final String MAE_IND_BUSCAR_DIRECCION = "indBuscarDireccion";
	
	public static final String SAC_CODIGO_SISTEMA = "SAC";
	public static final String SAC_CODIGO_PARAMETRO_MD = "004";
	public static final String SAC_NOMBRE_PARAMETRO_MD = "montoDiferencia";

	public static final String ZON_CARGOS_LIST = "zonCargosList";
	
	public static final String ZON_CALLES_LIST = "zonCallesList";	

	public static final String MESSAGE_MAE_NO_EXISTE_CONSULTORA = "No se existe la Consultora Ingresada";
	
	public static final String MESSAGE_MAE_NO_EXISTE_DATOS = "No se encuentran Datos ah actualizar";
	
	public static final String MESSAGE_MAE_NO_EXISTE_REGISTRO_DIREC_DOMICILIO = "No existe el Registro de Direccion de Domicilio";
	
        public static final String MESSAGE_MAE_EXISTEN_REGISTRO_DIREC_DOMICILIO = "Se encontraron caracteres no validos";

	public static final String MESSAGE_MAE_NO_EXISTE_DIREC_DOMICILIO = "No existe Direccion de Domicilio";
	
	public static final String MESSAGE_MAE_NO_EXISTE_REFER_DOMICILIO= "No existe Referencia de Domicilio";
	
	public static final String MESSAGE_MAE_NO_EXISTE_REGISTRO_DIREC_ENTREGA = "No existe el Registro de Direccion de Entrega";
	
	public static final String MESSAGE_MAE_NO_EXISTE_DIREC_ENTREGA = "No existe Direcci򬟤e Entrega";
	
	public static final String MESSAGE_MAE_NO_EXISTE_REFER_ENTREGA = "No existe Referencia de Entrega";
	
	public static final String MESSAGE_MAE_NO_EXISTE_REGISTRO_DIREC_VACACIONES = "No existe el Registro de Direcci򬟤e Vacaciones";
	
	public static final String MESSAGE_MAE_NO_EXISTE_DIREC_VACACIONES = "No existe Direcci򬟤e Vacaciones";
	
	public static final String MESSAGE_MAE_NO_EXISTE_REFER_VACACIONES = "No existe Referencia de Vacaciones";
	
	public static final String MESSAGE_MAE_NO_NUMERICO_TELE_CASA = "Telefono de Casa no es Numerico";
	
	public static final String MESSAGE_MAE_NO_NUMERICO_TELE_CELULAR= "Telefono de Celular no es Numerico";
	
	public static final String MESSAGE_MAE_NO_NUMERICO_TELE_TRABAJO = "Telefono de Trabajo no es Numerico";
	
	public static final String MESSAGE_MAE_EMAIL_SIN_FORMATO = "Email Sin Formato Correcto";
	
	public static final String MESSAGE_LEC_NO_REGISTRO_CORRECTO = "No se Realizo el Registro Correctamente";
	
	public static final String INTERFAZ_OCR_RECEPCION_FAMILIA_SEGURA_TELEMERCADO = "T";	
	public static final String PREFIJO_REPORTE_APE_ARMADO_MGPEDXDIA = "reporteApeAmadoMgpedxdia";
	public static final String PREFIJO_REPORTE_APE_DISTRI_FACTU_REAL = "reporteApeDistribucionFacturacionReal";
	public static final String PREFIJO_REPORTE_APE_INVENT_CAMPO_RESU = "reporteApeInventarioCampoResumen";
	public static final String PREFIJO_REPORTE_APE_INVENT_CAMPO_DETA = "reporteApeInventarioCampoDetalle";
	public static final String PREFIJO_REPORTE_SAC_FACTUR_DETAL = "reporteSacFacturacionDetalle";
	
	public static final String INC_REEMPLAZOS_PENDIENTES_LIST = "incReemplazosPendientesList";
	public static final String INC_REEMPLAZOS_PREMIOS_LIST = "incReemplazosPremiosList";
	public static final String INC_REEMPLAZOS_LIST = "incReemplazosList";
	public static final String INC_CRITERIO_REEMPLAZOS_LIST = "incCriterioReemplazosList";
	public static final String INC_REEMPLAZOS_COMPUESTOS_LIST = "incReemplazosCompuestosList";
	public static final String INC_DETALLE_REEMPLAZOS_LIST = "incDetalleReemplazosList";

	public static final String HIP_OPCION_RECUPERACION_ANULACION = "HIP-31";
	public static final String HIP_RECUPERACION_ANULACION_LIST = "hipRecuperacionAnulacionList";
	
	public static final String HIP_CONCURSOS_PREMIOS_RECHAZADOS_LIST = "hipConcursosPremiosRechazadosList";

	public static final String HIP_ETAPAS_COBRO_LIST = "hipEtapasCobroList";
	
	public static final String TODAS = "T";
	
	public static final String PREFIJO_REPORTE_SIC_DETAL_UNIDA_FALTA = "reporteSICDetalleUnidadesAtendidasFaltante";
	
	public static final String PREFIJO_REPORTE_MAE_CONSE_BLOQU_DESBL = "reporteMAEConsejerasBloqueadasDesbloqueadas";
	
	public static final String INC_TAB_MENSAJES = "mensajesTab-tab";
	public static final String INC_TIPO_CONCURSO_RECONOCIMIENTO = "T";
	public static final String INC_TIPO_CONCURSO_FICTICIO = "F";
	
	public static final String PREFIJO_REPORTE_COB_DETA_RECU_CARTE_COBRA=  "reporteCOBDetalladoRecuperacionCarteraCobrador";
	
	public static final String PREFIJO_REPORTE_COB_CARG_MASI_GESTI=  "reporteCOBCargaMasivaGestion";
	
	
	public static final String PREFIJO_REPORTE_COB_SALDO_PENDIENTE =  "reporteCOBSaldosPendientesForm";
	public static final String PREFIJO_REPORTE_CCC_LIQUI_COBRA =  "reporteCCCLiquidacionCobranzasForm";
	
	public static final String LEC_PROGRAMA_CORPORATIVO_LIST = "lecProgramaCorporativoList";
	
	public static final String LEC_TAB_PROGRAMA_CORPORATIVO_NIVEL = "nivelTab-tab";
	public static final String LEC_TAB_PROGRAMA_CORPORATIVO_OBJE_COBRANZA = "objCobranzaTab-tab";
	public static final String LEC_TAB_PROGRAMA_CORPORATIVO_INCENTIVO = "incentivoTab-tab";
	public static final String LEC_TAB_PROGRAMA_CORPORATIVO_GESTION_DESEM = "desempenioTab-tab";
	public static final String LEC_TAB_PROGRAMA_CORPORATIVO_BONO = "bonoTab-tab";
	public static final String LEC_TAB_PROGRAMA_CORPORATIVO_CALEN_COBRANZA = "caleCobraTab-tab";
	public static final String LEC_TAB_PROGRAMA_CORPORATIVO_CANASTA = "canastaTab-tab";
	public static final String LEC_TAB_PROGRAMA_CORPORATIVO_RANKING = "rankingTab-tab";
	
	public static final String LEC_MANT_NIVEL_CERRAR = "lecMantNivelCerrar";
	public static final String LEC_MANT_OBJCOB_CERRAR = "lecMantObjCobCerrar";
	
	public static final String REC_VALOR_PARAMETRO_NOTA_MERCADERIA_PERDIDA = "010";
	public static final String REC_INDICADOR_NOTA_MERCADERIA_PERDIDA = "indicadorNotaMercaderiaPerdida";
	
	
	public static final String COB_REPORTE_HISTORICO_OPERACION_CREDITICIA = "RepHistOperCred";
	
	public static final String LEC_TIPO_DESEM_LIST = "lecTipoDesemList";
	public static final String LEC_CAMPANIA_LIST = "lecCampaniasList";
	public static final String LEC_CAMPANIA_EVALUAR_LIST = "lecCampaniaEvaluarList";
	public static final String LEC_TIPO_NIVEL_LIST = "lectTipoNivelList";
	public static final String LEC_TIPO_MEDICION_LIST = "lectTipoMedicionList";

	public static final String LEC_TIPO_MEDICION_OBJETIVO_LIST = "lectTipoMedicionObjetivoList";
	
	public static final String LEC_SELECT_TRAMO_LIST = "lecSelectTramosList";
	
	public static final String PREFIJO_REPORTE_COB_MOVIM_RECUP_INCOB = "reporteCOBMovimientoRecuperacionIncobrable";
	public static final String SAPFI_ASIENTO_LIST = "asientoList";
	
	public static final String PREFIJO_REPORTE_MAE_VINCULO_CLIENTE = "reporteMAEVinculosCliente";
	
	public static final String PREFIJO_REPORTE_CCC_DETALLE_CTACTE =  "reporteCCCDetalleCuentaCorrienteContable";
	public static final String PREFIJO_REPORTE_CCC_DETALLE_PAGO_POR_REGULARIZAR =  "reporteCCCDetalladoPagoPorRegularizar";
	
	public static final String MAV_CODIGO_TIPO_VENTA_LIST = "mavCodigoTipoVentaList";
	
	public static final String FLX_NRO_CAMPANYAS_CALCULO_VARIABLES_CTACTE = "9";
	
	public static final String MAV_CODIGO_UNIDAD_DEMANDADA = "VD";
	public static final String MAV_DESCRIPCION_UNIDAD_DEMANDADA = "Venta Demandada";
	
	public static final String MAV_CODIGO_UNIDAD_DESPACHADA = "VF";
	public static final String MAV_DESCRIPCION_UNIDAD_DESPACHADA = "Venta Facturada";
	
	public static final String INSTANCIA_REPORTE_OPCION_MENU = "opcionMenu_IR";
	public static final String INSTANCIA_REPORTE_LOGIN_USUARIO = "loginUsuario_IR";
	public static final String INSTANCIA_REPORTE_CODIGO_PAIS = "codigoPais_IR";
	public static final String INSTANCIA_REPORTE_DESCRIPCION_PAIS = "descripcionPais_IR";
	public static final String INSTANCIA_REPORTE_CODIGO_IDIOMA_ISO = "codigoIdiomaISO_IR";
	
	public static final String RUV_TIPO_DOCUMENTO_CONTABLE_VENEZUELA_LIST = "ruvTipoDocumentoContableVenezuelaList";
	public static final String RUV_NUMERO_REGISTROS_PAGINA_VENEZUELA = "ruvNumeroRegistrosPaginaVenezuela";
	public static final String RUV_ACCESO_VENEZUELA_LIST = "ruvAccesoVenezuelaList";
	public static final String RUV_SUBACCESO_VENEZUELA_LIST = "ruvSubAccesoVenezuelaList";
	public static final String RUV_Pestana_ContableInternoTab_Venezuela = "datosContableInternoTabVenezuela";
	public static final String RUV_Pestana_ContableControlTab_Venezuela = "datosContableControlTabVenezuela";
	public static final String RUV_Pestana_Contable_Venezuela = "RUVpestanaContableVenezuela";
	public static final String RUV_Pestana_ContableLegalTab_Venezuela = "datosContableLegalTabVenezuela";
	public static final String RUV_Ditch_Tab_Focus_Legal_Venezuela = "RUVDitchTabFocus_Legal_Venezuela";
	public static final String RUV_Ditch_Tab_Ditch_Focused_Venezuela = "ditch-tab ditch-focused Venezuela";
	public static final String RUV_Ditch_Tab_Focus_Interno_Venezuela = "RUVDitchTabFocus_Interno_Venezuela";
	public static final String RUV_Ditch_Tab_Ditch_Unfocused_Venezuela = "ditch-tab ditch-unfocused Venezuela";
	public static final String RUV_Ditch_Tab_Focus_Control_Venezuela = "RUVDitchTabFocus_Control_Venezuela";
	public static final String RUV_INDICADOR_ELIMNAR_DOC_CONTA_VENEZUELA = "indEliminarDocContVenezuela";
	public static final String RUV_SIN_IMPRESION_VENEZUELA_LIST = "ruvSinImpresionVenezuelaList";
	public static final String RUV_DUPLICADOS_VENEZUELA_LIST = "ruvDuplicadosVenezuelaList";
	public static final String RUV_SIN_ASIGNAR_VENEZUELA_LIST = "ruvSinAsignarVenezuelaList";
	public static final String RUV_DOCLEGALES_VENEZUELA_LIST = "ruvDocLegalesVenezuelaList";
	public static final String RUV_DOCINTERNOS_VENEZUELA_LIST = "ruvDocInternosVenezuelaList";
	public static final String RUV_NUMCONTROL_VENEZUELA_LIST = "ruvNumControlVenezuelaList";
	public static final String RUV_NUMCON_DUPLICADOS_VENEZUELA_LIST = "ruvNumConDuplicadosVenezuelaList";
	public static final String RUV_SIN_ASIGNAR_NUMCON_VENEZUELA_LIST = "ruvSinAsignarNumConVenezuelaList";
	public static final String RUV_ASIGNULO_NUMCON_VENEZUELA_LIST = "ruvAsignarNulosNumConVenezuelaList";
	public static final String RUV_SECCION_BUSQUEDA_VAR_VENEZUELA = "ruvSeccionBusquedaVarVenezuela";
	public static final String RUV_CANTIDAD_NULOS_ASIGNAR_VENEZUELA = "ruvCantidadNulosAsignarVenezuela";
	public static final String RUV_CANTIDAD_FILASPARAM_PAGINA_LEGAL_VENEZUELA = "ruvCantidadFilasParamPaginaLegalVenezuela";
	public static final String RUV_CANTIDAD_FILASPARAM_PAGINA_INTERNO_VENEZUELA = "ruvCantidadFilasParamPaginaInternoVenezuela";
	public static final String RUV_CANTIDAD_FILASPARAM_PAGINA_CONTROL_VENEZUELA = "ruvCantidadFilasParamPaginaControlVenezuela";

	public static final String REC_ACTIVACION_RECLAMOS_GRATIS_LIST = "recActivacionReclamosGratisList";
	public static final String REC_TIPO_OPERACION = "01";
	public static final String REC_TIPO_PRODUCTO = "Activacion";
	public static final String REC_TIPO_ATENCION_PREMIO = "Premio";
	public static final String REC_TIPO_ATENCION_PRODUCTO = "Producto";
	
	public static final String COB_TIPO_ETAPA_LIST = "cccTipoEtapasList";
	public static final String COB_CARGA_CRONOGRAMA_LIST = "cobCargarCronogramaList";
	
	public static final String COB_CARGA_MASIVA_GESTIONES_LIST = "cobCargaMasivaGestionesList";
	
	public static final String REC_RECEPCION_CDR_LIST = "mantenimientoRECRecepcionCDRList";
	
	public static final String HIP_CONCURSOS_PREMIOS_ELEGIDOS_LIST = "hipConcursosPremiosElegidosList";
	
	public static final String PREFIJO_REPORTE_INC_PUNT_OBTE_PUNT_FALTA =  "reporteINCPuntObtenidosPuntFaltantesForm";
	
	public static final String FLX_CODIGO_PERIODO_EJECUCION_RECEPCION_WEB = "010";
	public static final String FLX_CODIGO_INDICADOR_EJECUCION_RECEPCION_WEB = "011";
	public static final String FLX_PROCESO_RECEPCIONAR_ARCHIVOS_WEB = "FLX-P4";
	public static final String FLX_CODIGO_PROCESO_BATCH_RECEPCIONAR_ARCHIVOS_WEB = "04";
	
	public static final String PREFIJO_REPORTE_CCC_AUDITORIA_SALDO_CUENTA_X_COBRAR = "reporteCCCAuditoriaSaldoCuentasPorCobrar";
	
	public static final String DAT_ESTADO_CIERRE = "P";

	public static final String PREFIJO_REPORTE_CCC_DETALLE_IFC =  "reporteCCCDetalleIFC";
	
	public static final String LEC_CODIGO_FORMA_PAGO_PRODUCTO_CANASTA = "GRT";
	public static final String LEC_TIPOS_OFERTA_PRODUCTO_CANASTA = "lecTiposOfertaProductoCanasta";	
	public static final String LEC_PRODUCTOS_CANASTA_LIST = "lecProductosCanastaList";
	public static final String LEC_CANASTA_LIST = "lecCanastaList";
	
	public static final String PREFIJO_REPORTE_MAE_CLASIFICACION_POR_CLIENTE = "reporteMAEClasificacionPorCliente";
	
	public static final String REC_ENVIAR_CDR_RECEPCIONADOS_LIST = "procesoRECEnviarCDRRecepcionadosList";
	
	public static final String REC_ANULA_MASIVA_BOLETA_RECOJO_LIST = "recAnulaMasivaBorecList";
	public static final String REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD = "recAnulaMasivaBoletaRecojoCantidad";
	public static final String REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD_CORRECTAS = "recAnulaMasivaBoletaRecojoCantidadCorrectas";
	public static final String REC_LISTA_ANULA_MASIVA_BOLETA_RECOJO_CANTIDAD_INCORRECTAS = "recAnulaMasivaBoletaRecojoCantidadIncorrectas";
	
	public static final String MAE_TIPO_COMUNICACION_TELEFONO_ADICIONAL = "TA";
	public static final String LEC_REGION_LIST = "lecRegionList";
	
	public static final String OCR_CODIGO_SISTEMA = "OCR";
	public static final String OCR_CODIGO_PARAMETRO_TIPO_REEM = "047";
	public static final String OCR_NOMBRE_PARAMETRO_MOSTRAR_TIPO_REEM = "indActivoReemplazo";
		
	public static final String PREFIJO_REPORTE_CCC_GASTO_CUPON =  "reporteCCCGastoCupon";
	
	public static final String FAC_CODIGO_PARAMETRO_SERVIDOR_FTP = "servidorFTP";
	public static final String FAC_CODIGO_PARAMETRO_PUERTO_FTP = "puertoFTP";
	public static final String FAC_CODIGO_PARAMETRO_USUARIO_FTP = "usuarioFTP";
	public static final String FAC_CODIGO_PARAMETRO_CLAVE_FTP = "claveFTP";
	public static final String FAC_CODIGO_PARAMETRO_DIRECTORIO_FTP = "directorioFTP";
	
	public static final String OCR_IND_FACTURACION_ELECTRONICA = "048";
	
	public static final String PREFIJO_REPORTE_SAC_RECALL_TRAZABILIDAD = "reporteSACRecallTrazabilidad";

	public static final String STO_NUMERO_DOCUMENTO_ARCHIVO_LIST = "stoNumeroDocumentoArchivoList";
	public static final String STO_NUMERO_DOCUMENTO_INVALIDO_ARCHIVO_LIST = "stoNumeroDocumentoInvalidoArchivoList";
	
	public static final int MAV_CONRES_MONTO_FLEXIPAGO = 63;

	public static final String MAE_TIPO_DOCUMENTO_PRINCIPAL = "01";
	
	public static final String RUV_ELIMINACION_SUBACCESO_LIST = "pedEliminacionSubaccesoList";
	
	public static final String HIP_OPCION_SOCIAS_EMPRESARIAS = "HIP-32";	
	public static final String HIP_SOCIAS_EMPRESARIAS_HISTORIAL_LIST = "hipSociasEmpresariasHistorialList";
	public static final String HIP_SOCIAS_EMPRESARIAS_RESULTADOS_LIST = "hipSociasEmpresariasResultadoList";

        public static final String ZON_ORIGEN_WEB = "W";
	
	public static final String MAV_INDICADOR_TIPOCARGO = "004";

	public static final String MAE_IMPRESION_PAQDOC_ARCHIVO_LIST = "maeImpresionPaqDocArchivolist";

	public static final String LEC_RANKING_CODIGO_TIPO_MEDICION_CUMPLIMIENTO_PEDIDOS = "01";
	public static final String LEC_RANKING_CODIGO_TIPO_MEDICION_SOBRECUMPLIMIENTO_PEDIDOS = "02";	

	public static final String LEC_RANKING_DESCRI_TIPO_MEDICION_CUMPLIMIENTO_PEDIDOS = "CUMPLIMIENTO DE PEDIDOS";
	public static final String LEC_RANKING_DESCRI_TIPO_MEDICION_SOBRECUMPLIMIENTO_PEDIDOS = "SOBRECUMPLIMIENTO DE PEDIDOS";	

	public static final String LEC_TIPO_RANKING_LIST = "lecTipoRankingList";
	public static final String LEC_RANKING_LIST = "lecRankingList";
	public static final String LEC_RANKING_NIVELES_LIST = "lecRankingNivelList";
	
	public static final String OCR_CODIGO_PROCESO_BATCH_RECEPCIONAR_FAMILIA_SEGURA_WEB = "20";
	public static final String OCR_CODIGO_INTERFAZ_RECEPCIONAR_ARCHIVO_FAMILIA_SEGURA_WEB = "OCR-62";
	
	public static final String LET_CODIGO_PROCESO_BATCH_EJECUTAR_PROCESOS_LET = "10";
	public static final String LET_CODIGO_INTERFAZ_EJECUTAR_PROCESOS_LET = "LET-P1";
	
	public static final String REC_CODIGO_PROCESO_BATCH_ENVIAR_BOLETAS_DE_RECOJO = "02";
	public static final String REC_CODIGO_INTERFAZ_ENVIAR_BOLETAS_DE_RECOJO = "REC-P2";
	
	public static final String IMP_CODIGO_PROCESO_BATCH_ENVIAR_DOCUMENTOS_ELECTRONICOS = "01";
	public static final String IMP_CODIGO_INTERFAZ_ENVIAR_DOCUMENTOS_ELECTRONICOS = "IMP-P4";
	
	public static final String CODIGO_SISTEMA_LAR = "LAR";
	public static final String LAR_CODIGO_PROCESO_BATCH_ENVIAR_VISIBILIDAD_CRONOGRAMA_FACTURACION = "05";
	public static final String LAR_CODIGO_INTERFAZ_ENVIAR_VISIBILIDAD_CRONOGRAMA_FACTURACION = "LAR-12";
	
	public static final String CODIGO_SISTEMA_IMP = "IMP";
	public static final String IMP_CODIGO_PROCESO_BATCH_GEN_PROC_NO_DEP_FACT = "13";
	public static final String IMP_CODIGO_INTERFAZ_BATCH_GEN_PROC_NO_DEP_FACT = "IMP-P9";
	
	public static final String CODIGO_SISTEMA_GEN = "GEN";
	public static final String GEN_CODIGO_PROCESO_BATCH_ELIMINAR_MENSAJE_BUZON = "02";	
	public static final String GEN_CODIGO_INTERFAZ_ELIMINAR_MENSAJE_BUZON = "GEN-P1";
	public static final String GEN_CODIGO_INTERFAZ_ELIMINAR_MENSAJE_BUZON_PARTE_DOS = "GEN-P11";
	
	public static final String IMP_CODIGO_PROCESO_BATCH_ENVIAR_DOCUMENTOS_MATRICIALES = "03";
	public static final String IMP_CODIGO_INTERFAZ_ENVIAR_DOCUMENTOS_MATRICIALES = "IMP-P1";
	
	public static final String IMP_CODIGO_PROCESO_BATCH_COMPAGINACION_PAQUETE_DOCUMENTARIO = "02";
	
	public static final String SAM_CODIGO_PROCESO_BATCH_ENVIAR_MOVIMIENTOS_ALMACEN_SICC = "01";
	public static final String SAM_CODIGO_INTERFAZ_ENVIAR_MOVIMIENTOS_ALMACEN_SICC = "SAM-9";
	public static final String CODIGO_SISTEMA_SAM = "SAM";
	
	public static final String EDU_CODIGO_PROCESO_BATCH_GENERAR_PLANILLA_PROGRAMACION = "08";
	
	public static final String SGR_CODIGO_PROCESO_BATCH_ENVIAR_INFORMACION_POLIZA = "01";
	public static final String SGR_CODIGO_INTERFAZ_BATCH_ENVIAR_INFORMACION_POLIZA = "SGR-P1";
	public static final String CODIGO_INTERFAZ_SGR = "SGR";
	
	public static final String OCR_CODIGO_PROCESO_BATCH_RECEPCIONAR_SOLICITUD_CREDITO_WEB = "15";
	public static final String OCR_CODIGO_INTERFAZ_RECEPCIONAR_SOLICITUD_CREDITO_WEB = "OCR-40";
	
	public static final String OCR_CODIGO_PROCESO_BATCH_RECEPCIONAR_ACTUALIZACION_DATOS_WEB = "16";
	public static final String OCR_CODIGO_INTERFAZ_RECEPCIONAR_ACTUALIZACION_DATOS_WEB = "OCR-42";
	
	public static final String DAT_CODIGO_INTERFAZ_ENVIAR_ARCHIVOS_EDUCACION = "DAT-P1";
	public static final String CODIGO_SISTEMA_DAT = "DAT";

	public static final String COB_CODIGO_PROCESO_BATCH_ACTUALIZAR_CARTERA = "01";
	public static final String COB_CODIGO_PROCESO_ACTUALIZAR_CARTERA = "03";
	
	public static final String MAV_CODIGO_PROCESO_BATCH_FACTURACION_GERENTES = "02";
	
	public static final String CCC_CODIGO_PROCESO_BATCH_GENERACION_ARCHIVOS_MOROSAS = "19";
	
	public static final String FLX_CODIGO_INTERFAZ_ENVIAR_RESULADO_PROGRAMA = "FLX-P1";
	public static final String FLX_CODIGO_PROCESO_BATCH_ENVIAR_RESULADO_PROGRAMA = "02";
	
	public static final String FLX_CODIGO_INTERFAZ_PROCESOS_CIERRE_CAMPANIA_G1 = "FLX-P2";
	public static final String FLX_CODIGO_PROCESO_BATCH_PROCESOS_CIERRE_CAMPANIA_G1 = "03";
	
	public static final String FLX_CODIGO_INTERFAZ_PROCESOS_CIERRE_CAMPANIA_G2 = "FLX-P3";
	public static final String FLX_CODIGO_PROCESO_BATCH_PROCESOS_CIERRE_CAMPANIA_G2 = "03";
	
	public static final String FLX_CODIGO_INTERFAZ_RECEPCIONAR_CONSULTORAS_HABILES = "FLX-1";
	public static final String FLX_CODIGO_PROCESO_BATCH_RECEPCIONAR_CONSULTORAS_HABILES = "05";
	
	public static final String LEC_CODIGO_SISTEMA = "LEC";
	
	public static final String SAT_CODIGO_INTERFAZ_RECEPCIONAR_DIVISION_ARMADO_POR_CDP = "SAT-15";
	public static final String SAT_CODIGO_SISTEMA = "SAT";
	
	public static final String OCR_CODIGO_PROCESO_BATCH_ENVIAR_CLIENTES_A_WEB = "02";
	public static final String OCR_CODIGO_INTERFAZ_ENVIAR_CLIENTES_A_WEB = "OCR-P12";
	
	public static final String ACC_CODIGO_PROCESO_BATCH_ENVIAR_TABLA_CLIENTES = "03";
	public static final String ACC_CODIGO_INTERFAZ_ENVIAR_TABLA_CLIENTES = "ACC-1";
	public static final String ACC_CODIGO_SISTEMA = "ACC";

	public static final String SAF_CODIGO_PROCESO_BATCH_INTERFAZ_SAPFI_COLOMBIA = "02";
	public static final String SAF_CODIGO_INTERFAZ_SAPFI_COLOMBIA = "SAF-P1";
	public static final String SAF_CODIGO_SISTEMA = "SAF";
	
	public static final String PRY_CODIGO_PROCESO_BATCH_ENVIAR_PROYECCION_PARCIAL_CENTRO = "03";
	public static final String PRY_CODIGO_INTERFAZ_ENVIAR_PROYECCION_PARCIAL_CENTRO = "PRY-P2";
	public static final String PRY_CODIGO_SISTEMA = "PRY";
	
	public static final String OCR_CODIGO_INTERFAZ_ENVIAR_ORDENES_TRANSPORTE = "OCR-P13";
	
	public static final String APE_CODIGO_INTERFAZ_RECEPCIONAR_CHEQUEO = "APE-11";
	public static final String APE_CODIGO_SISTEMA = "APE";
	
	public static final String SMS_CODIGO_PROCESO_BATCH_ENVIO_MENSAJE_TEXTO_CONSULTORAS = "02";
	public static final String SMS_CODIGO_INTERFAZ_ENVIO_MENSAJE_TEXTO_CONSULTORAS = "SMS-3";
	public static final String SMS_CODIGO_SISTEMA = "SMS";
	
	public static final String STO_CODIGO_PROCESO_BATCH_EJECUTAR_VALIDACIONES = "00";
	
	public static final String STO_PARAMETRO_PORCENTAJE_DESVIACION = "024";
	public static final String STO_PARAMETRO_PROMEDIO_PEDIDO = "025";
	public static final String STO_PARAMETRO_MONTO_REAL = "026";
	
	public static final String SGR_CODIGO_PROCESO_BATCH_RECEPCIONAR_POLIZAS_CANCELADAS = "02";
	public static final String SGR_CODIGO_INTERFAZ_RECEPCIONAR_POLIZAS_CANCELADAS = "SGR-5";
	
	public static final String IVR_CODIGO_PROCESO_BATCH_RECEPCIONAR_PEDIDOS = "03";
	public static final String IVR_CODIGO_INTERFAZ_RECEPCIONAR_PEDIDOS = "IVR-60";
	public static final String IVR_CODIGO_SISTEMA = "IVR";
	
	public static final String OCR_CODIGO_PROCESO_BATCH_RECEPCIONAR_ARCHIVO_FAMILIA_SEGURA = "13";
	public static final String OCR_CODIGO_INTERFAZ_RECEPCIONAR_ARCHIVO_FAMILIA_SEGURA = "OCR-56";
	
	public static final String APE_CODIGO_INTERFAZ_RECEPCIONAR_ANAQUELES = "APE-10";
	
	public static final String SAT_CODIGO_PROCESO_BATCH_RECEPCIONAR_CENTROS_ACOPIO = "01";
	public static final String SAT_CODIGO_INTERFAZ_RECEPCIONAR_CENTROS_ACOPIO = "SAT-13";
	
	public static final String SAT_CODIGO_PROCESO_BATCH_RECEPCIONAR_COBERTURA_POR_CODIGO_TERRITORIAL = "02";
	public static final String SAT_CODIGO_INTERFAZ_RECEPCIONAR_COBERTURA_POR_CODIGO_TERRITORIAL = "SAT-14";
	
	public static final String SAT_CODIGO_INTERFAZ_RECEPCIONAR_ORDEN_IMPRESION_APESAT = "SAT-16";
	
	public static final String SAT_CODIGO_PROCESO_BATCH_RECEPCIONAR_PARAMETRIZACION_CALCULO_FECHA_ENTREGA_EXACTA = "04";
	public static final String SAT_CODIGO_INTERFAZ_RECEPCIONAR_PARAMETRIZACION_CALCULO_FECHA_ENTREGA_EXACTA = "SAT-17";
	
	public static final String SAT_CODIGO_INTERFAZ_RECEPCIONAR_EXEPCIONES_FECHA_ENTREGA_EXACTA = "SAT-18";
	
	public static final String SAT_CODIGO_PROCESO_BATCH_RECEPCIONAR_SEGUIMIENTO_PEDIDO = "03";
	public static final String SAT_CODIGO_INTERFAZ_RECEPCIONAR_SEGUIMIENTO_PEDIDO = "SAT-19";
	
	public static final String CCC_CODIGO_PROCESO_BATCH_CARGAR_LOTES_BANCARIOS = "03";
	public static final String CCC_CODIGO_INTERFAZ_CARGAR_LOTES_BANCARIOS = "CCC-1";
	
	public static final String OCR_CODIGO_PROCESO_BATCH_RECEPCIONAR_BOLETA_ENTREGA = "23";
	public static final String OCR_CODIGO_INTERFAZ_RECEPCIONAR_BOLETA_ENTREGA = "OCR-84";
	
	public static final String OCR_CODIGO_PROCESO_BATCH_RECEPCIONAR_OCSWEBDD = "00";
	public static final String OCR_CODIGO_INTERFAZ_RECEPCIONAR_OCSWEBDD = "OCR-P9";
	
	public static final String RET_CODIGO_PROCESO_BATCH_ENVIAR_INFORMACION_RETAIL = "06";
	public static final String RET_CODIGO_INTERFAZ_ENVIAR_INFORMACION_RETAIL = "RET-P4";
	public static final String CODIGO_SISTEMA_RET = "RET";
	
	public static final String MYE_CODIGO_PROCESO_BATCH_RECEPCIONAR_ACTUALIZACION_DATOS_CONSULTORA = "01";
	public static final String MYE_CODIGO_INTERFAZ_RECEPCIONAR_ACTUALIZACION_DATOS_CONSULTORA = "MYE-17";
	public static final String MYE_CODIGO_SISTEMA = "MYE";

	public static final String FLX_CODIGO_PROCESO_BATCH_RECEPCIONAR_ARCHIVOS_WEB_MANUAL = "04";
	public static final String FLX_CODIGO_INTERFAZ_RECEPCIONAR_ARCHIVOS_WEB_MANUAL = "FLX-P4";
	
	public static final String SAT_CODIGO_INTERFAZ_RECEPCIONAR_IMPRESION_BOLETAS_ENTREGA = "SAT-20";

	public static final String CCC_CODIGO_PROCESO_BATCH_LIQUIDACION_LOTE_BANCARIO = "21";
	
	public static final String REC_CODIGO_PROCESO_BATCH_PRODUCTOS_RECLAMADOS = "04";
	public static final String REC_CODIGO_INTERFAZ_PRODUCTOS_RECLAMADOS = "REC-P1";
	
	public static final String OCR_CODIGO_PROCESO_BATCH_ENVIAR_DETALLES_PEDIDOS_DIGITADOS = "00";
	public static final String OCR_CODIGO_INTERFAZ_ENVIAR_DETALLES_PEDIDOS_DIGITADOS = "OCR-59";
	
	public static final String SOA_CASTIGO_DEUDA_INCOBRABLE = "CASTIGO POR DEUDA INCOBRABLE";
	public static final String SOA_INCOBRABLE = "Incobrable";
	public static final String SOA_BLOQUEO_MASIVO_CASTIGO_INCOBRABLE = "BLOQUEO MASIVO CASTIGO INCOBRABLE";
	public static final String SOA_VALORACION_VALOR_COE = "INCOBRABLE";
	
	public static final String CODIGO_OPERACION_FALTANTE_NMPS = "F";
	
	public static final String ACC_TIPO_ENVIO_NOVEDAD = "N";
	public static final String ACC_TIPO_ENVIO_COMPLETO = "C";
	
	public static final String GEN_CODIGO_PROCESO_BATCH_CIERRE_PROCESOS_DIARIOS_SCDF = "09";
	
	public static final String LLI_CODIGO_SISTEMA = "LLI";
	public static final String LLI_CODIGO_INTERFAZ_ENVIAR_VENTA_REAL_DIARIA_ACUMULADA = "LLI-2";
	public static final String LLI_CODIGO_PROCESO_BATCH_ENVIAR_VENTA_REAL_DIARIA_ACUMULADA = "01";
	
	public static final String LLI_CODIGO_INTERFAZ_ENVIAR_VENTA_PERIODO = "LLI-1";
	public static final String LLI_CODIGO_PROCESO_BATCH_ENVIAR_VENTA_PERIODO = "02";

	public static final String OCR_CODIGO_PROCESO_BATCH_ACTUALIZAR_MATRIZ_FACTURACION = "28";
	
	public static final String OCR_CODIGO_INTERFAZ_RETORNO_CODIGOS_ASIGNADOS = "OCR-54";
	public static final String OCR_CODIGO_PROCESO_BATCH_RETORNO_CODIGOS_ASIGNADOS = "29";
	
	public static final String RET_CODIGO_SISTEMA = "RET";
	public static final String RET_CODIGO_INTERFAZ_ENVIAR_FACTURAS_COMPLEMENTOS_VENTA_DIRECTA = "RET-P2";
	public static final String RET_CODIGO_PROCESO_BATCH_ENVIAR_FACTURAS_COMPLEMENTOS_VENTA_DIRECTA = "08";
	
	public static final String OCR_CODIGO_PROCESO_BATCH_ACTUALIZAR_NUMERO_LOTE = "27";
	
	public static final String SAT_CODIGO_PROCESO_BATCH_RECEPCIONAR_ORDEN_IMPRESION_APESAT = "05";
	
	public static final String MAE_NIVEL_RIESGO_ARCHIVO_LIST = "maeNivelRiesgoArchivolist";
	
	public static final String PED_OFERTAS_POR_CONCURSO_APOYADOS_LIST = "pedOfertasPorConcursoApoyadosList";
	public static final String PED_OFERTAS_POR_CONCURSO_COMPONENTES_LIST = "pedOfertasPorConcursoComponentesList";
	public static final String PED_OFERTAS_POR_CONCURSO_RANGOS_LIST = "pedOfertasPorConcursoRangosList";
	public static final String PED_OFERTAS_POR_CONCURSO_REGALOS_LIST = "pedOfertasPorConcursoRegalosList";
	
	public static final String PED_OFERTAS_POR_CONCURSO_APOYADOS_NUEVO_LIST = "pedOfertasPorConcursoApoyadosNuevoList";
	public static final String PED_OFERTAS_POR_CONCURSO_RANGOS_NUEVO_LIST = "pedOfertasPorConcursoRangosNuevoList";
	
	public static final String PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_PRECIOS = "1";
	public static final String PED_OFERTAS_POR_CONCURSO_TIPO_PROGRAMA_NIVELES_CONCURSOS = "2";

	public static final String PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_RANGO = "R";
	public static final String PED_OFERTAS_POR_CONCURSO_TIPO_RANGO_PRODUCTO = "P";
	
       public static final String PED_OFERTAS_OID_COMPUESTA_VARIABLE = "2003";

	public static final String FLX_PARAMETRO_INDICADOR_CLIENTE_CEDULA = "012";
	
	public static final String GEN_CODIGO_PROCESO_BATCH_EJECUTAR_INTERFAZ = "10";
	
	public static final String MAE_CODIGO_TIPO_CONSULTORA = "02";
	public static final String MAE_CODIGO_SUBTIPO_OFICINA = "06";
			
	
	public static final String MAE_VALID_LIDER_RECOM = "VAL_LIDER_RECOM";
	public static final String MAE_LIDER_RECOM = "LIDERRECOM";
	public static final String MAE_TIPO_VINCULO_LIDER_RECOMENDADA = "08";
	
	public static final String PREFIJO_REPORTE_CUP_NUEVAS_CUPONES = "reporteCUPNuevasCupones";
	
	public static final String INC_CONCURSOS_MIGRACION_PUNTOS_LIST = "incConcursosMigracionPuntosList";
	public static final String INC_MIGRACION_PUNTOS_ARCHIVO_LIST = "incMigracionPuntosArchivolist";
	
	public static final String PRE_VALIDACION_MATRIZ_LIST = "preValidacionMatrizList";
	
	public static final String PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_UNIDADES = "1";
	public static final String PED_OFERTAS_POR_CONCURSO_TIPO_CUADRE_MONTO = "2";
	
	
	public static final String PREFIJO_REPORTE_LEC_RESULTADOS = "reporteLECResultados";
	
	public static final String PREFIJO_REPORTE_SGR_SOLICITUDES_TODOS = "reporteSGRSolicitudesTodos";
	
	public static final String MAE_EXCENCION_FLETE_LIST = "maeExcencionFleteList";
	
	public static final String MAE_EXCENCION_SOBRE_FLETE_LIST = "maeExcencionSobreFleteList";
	
	public static final String INC_PAGO_CONCURSO_LIST = "incPagoConcursoList";
	public static final String INC_PAGO_CONCURSO_CLASIFICACIONES_LIST = "incPagoConcursoClasificacionesList";

	public static final String SMS_INTERFAZ_ENVIO_CONSULTORA_PAGOS_RECAUDO_BANCARIO = "SMS-4";
	public static final String SMS_PROCESO_ENVIO_CONSULTORA_PAGOS = "03";
	
	public static final String GEN_INTERFAZ_REACTIVACION_GERENTES_DIRECTORIO_LICENCIAS = "GEN-43";
	public static final String GEN_INTERFAZ_REACTIVACION_GERENTES_DIRECTORIO_FUTURAS = "GEN-46";
	public static final String GEN_PROCESO_REACTIVACION_GERENTES_DIRECTORIO = "11";
	public static final String GEN_CODIGO_PARAMETRO_EMAIL_DESTINO = "014";
	public static final String GEN_CODIGO_PARAMETRO_ASUNTO_EMAIL_DESTINO = "015";
	public static final String GEN_CODIGO_PARAMETRO_ASUNTO_EMAIL_ORIGEN = "016";
	public static final String GEN_NOMBRE_PARAM_CORREO="procesoDIRreactivacionGerentes";
	
	public static final String PED_OFERTAS_POR_FACTOR_REPETICION_REGALOS_LIST = "pedOfertasPorFactorRepeticionRegalosList";	
	public static final String PED_OFERTAS_POR_FACTOR_REPETICION_APOYADOS_NUEVO_LIST = "pedOfertasPorFactorRepeticionApoyadosNuevoList";
	public static final String PED_OFERTAS_POR_FACTOR_REPETICION_RANGOS_LIST = "pedOfertasPorFactorRepeticionRangosList";
	public static final String PED_OFERTAS_POR_FACTOR_REPETICION_CRITERIOS_LIST = "pedOfertasPorFactorRepeticionCriteriosList";
	public static final String PED_OFERTAS_POR_FACTOR_REPETICION_CRITERIOS_PRODUCTOS_LIST = "pedOfertasPorFactorRepeticionCriteriosProductosList";

	public static final String PED_OFERTAS_POR_CONCURSOS_REGALOS_LIST = "pedOfertasPorConcursosRegalosList";	
	public static final String PED_OFERTAS_POR_CONCURSOS_APOYADOS_NUEVO_LIST = "pedOfertasPorConcursosApoyadosNuevoList";	
	public static final String PED_OFERTAS_POR_CONCURSOS_RANGOS_LIST = "pedOfertasPorConcursosRangosList";
	public static final String PED_OFERTAS_POR_CONCURSOS_CRITERIOS_LIST = "pedOfertasPorConcursosCriteriosList";
	public static final String PED_OFERTAS_POR_CONCURSOS_CRITERIOS_PRODUCTOS_LIST = "pedOfertasPorConcursosCriteriosProductosList";
	
	public static final String MAE_CODIGO_PARAMETRO_IND_TIPO_CLASIFICACION_PAIS = "016";
	public static final String MAE_NOMBRE_PARAMETRO_IND_TIPO_CLASIFICACION_PAIS = "indTipoClasifPais";
	
	public static final String STO_CODIGO_PARAMETRO_IND_ENVIO_VALIDACIONES = "027";
	
	public static final String INC_TRANSACCION_PAGO_CONCURSO_LIST = "incTransaccionPagoConcursoList";
	public static final String INC_MOTIVOS_PAGO_LIST = "incMotivosPagoList";

	public static final String RUV_CODIGO_PARAMETRO_CONSTANTE_FACTURACION = "NCFFacturacion";
	public static final String RUV_CODIGO_PARAMETRO_CONSTANTE_NOTACREDITO = "NCFNotaCredito";
	public static final String RUV_CODIGO_PARAMETRO_CONSTANTE_NOTADEBITO = "NCFNotaDebito";

	public static final String PED_NUMEROS_FACTURACION_LIST = "pedNumerosFacturacionList";

	public static final String PREFIJO_REPORTE_CCC_MEDIOS_MAGNETICOS_CUENTA_CORRIENTE_BIMENSUAL =  "reporteCCCMedMagneBim";
	
	public static final String PREFIJO_REPORTE_CCC_ANTIGUEDAD_SALDOS =  "reporteCCCAntiguedadSaldos";
	
	public static final String INC_PAGO_BONO_PREMIO_LIST = "incPagoBonoPremioList";
	
	public static final String MAE_ENTIDAD_GENERICA_LIST = "maeEntidadGenericaList";
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_LIST = "maeCodigoEntidadGenericaList";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_TIPO_BLOQUEO = "MAE_TIPO_BLOQU";
	public static final String MAE_MOTIVO_RECHAZO_LIST = "maeMotivoRechazoList";
	public static final String MAE_FORMA_BLOQUEO_LIST = "maeFormaBloqueoList";
	public static final String MAE_OID_FORMA_BLOQUEO_AMBOS = "1002";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLIENTE = "MAE_TIPO_CLIEN";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_SUBTIPO_CLIENTE = "MAE_SUBTI_CLIEN";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_ESTATUS_CLIENTE = "MAE_ESTAT_CLIEN";
	
	public static final String MAE_ESTATUS_POSTERIOR_POSIBLE_LIST = "maeEstatusPosteriorPosibleList";
	
	public static final String MAE_CODIGO_TIPO_ESTATUS_LIST = "maeCodigoTipoEstatusList";
	
	public static final String MAE_CODIGO_TIPO_CLIENTE_LIST = "maeCodigoTipoClienteList";
	
	public static final String MAE_CODIGO_SUBTIPO_CLIENTE_LIST_C = "maeCodigoTipoClienteListC";
	
	public static final String MAE_CODIGO_SUBTIPO_CLIENTE_LIST_TC = "maeCodigoTipoClienteListTC";
	
	public static final String MAE_CODIGO_SUBTIPO_CLIENTE_LIST = "maeCodigoSubTipoClienteList";
	
	public static final String MAE_CODIGO_TIPO_CLASIFICACION_LIST = "maeCodigoTipoClasificacionList"; 
	
	public static final String MAE_MARCAS_LIST = "maeMarcasList"; 
	
	public static final String MAE_TIPO_DOCUMENTO_LIST = "maeTipoDocumentoList"; 
	
	public static final String MAE_CRITERIOS_LIST = "maeCriteriosList"; 
	
	public static final String MAE_NOMBRE_PARAMETRO_PRE_FACTURACION = "indBloqClienPreFacturacion";
	
	public static final String MAE_CODIGO_VALIDACION_PEDIDO = "10";
	
	public static final String MAE_ACCION_BLOQUEO = "B";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_CLASIFICACION = "MAE_CLASI";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLASIFICACION = "MAE_TIPO_CLASI_CLIEN";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_TIPO_ESTATUS_CLIENTE = "MAE_TIPO_ESTAT_CLIEN";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_GARANTIAS = "INC_PRODU_GARAN";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_ESTADO_CIVIL = "MAE_ESTAD_CIVIL";
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DIRECCION = "MAE_TIPO_DIREC";
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_TIPO_COMUNICACION = "MAE_TIPO_COMUN";
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_TIPO_VINCULO = "MAE_TIPO_VINCU";
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DOCUMENTO = "MAE_TIPO_DOCUM";
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_CRITERIO_BUSQUEDA = "MAE_CRITE_BUSQU";
	
	public static final String MAE_CODIGO_ENTIDAD_GENERICA_ACCION_PROCESO_BLOQUEO = "MAE_ACCIO_PROCE_BLOQU";
	
	public static final String MAE_TIPO_BLOQUEO_ACCION_LIST = "maeTipoBloqueoAccionList";
	public static final String MAE_PROCESO_BLOQUEO_ACCION_LIST = "maeProcesoBloqueoAccionList";
	public static final String MAE_ACCION_BLOQUEO_ACCION_LIST = "maeAccionBloqueoAccionList";
	
	public static final String MAE_CODIGO_INTERFAZ_INC = "INC";
	public static final String MAE_CODIGO_INTERFAZ_MAE = "MAE";
	
	public static final String STO_CODIGO_PROCESO_BATCH_EN_EJECUCION = "11";
	
	public static final String PED_OFERTA_FACTOR_REPETICION_LIST = "pedOfertaFactorRepeticionList";
	
	
	
	
	
	public static final String PED_OFERTA_CONCURSOS_LIST = "pedOfertaConcursosList";
	public static final String PED_OFERTA_CONCURSOS_CATALOGO_LIST = "pedOfertaConcursosCatalogoList";
	public static final String PED_OFERTA_FACTOR_REPETICION_CATALOGO_LIST = "pedOfertaFactorRepeticionCatalogoList";
	public static final String PED_OFERTAS_CONCURSOS_APOYADOS_NUEVO_LIST = "pedOfertasPorConcursosApoyadosNuevoList";
	public static final String PED_OFERTAS_CONCURSOS_RANGOS_LIST = "pedOfertasPorConcursosRangosList";
	
	
	public static final String CODIGO_PARAMETRO_MUESTRA_REPORTE_LIBRO_VENTAS_VENEZUELA = "010";
	

	public static final String LEC_REPORTE_LIDERES_TIPO_TODAS = "0";
	public static final String LEC_REPORTE_LIDERES_TIPO_NOMBRADAS = "1";
	public static final String LEC_REPORTE_LIDERES_TIPO_DESVINCULADAS = "2";
		public static final String FVP_CODIGO_SISTEMA = "FVP";
	public static final String FVP_CODIGO_PARAMETRO_IND_TIPO_CARGA_FUENTE_VENTAS = "001";
	
	public static final String REC_TIPO_FECHA_GENERACION = "G";
	public static final String REC_TIPO_FECHA_RECOJO = "R";
	
	public static final String REC_REGION_ZONA_BOLETA_RECOJO_INTELIGENTE_LIST = "recRegionZonaBoletaRecojoInteligenteList";	
	
		public static final String PREFIJO_REPORTE_CCC_DETALLE_CONSUL_INCOBR = "reporteCCCDetalladoConsultorasIncobrableCVS";
	
	//CAMPAÑA EXCEL
	public static final String PREFIJO_REPORTE_CCC_DETALLE_PROVIS_INCOBR_CC = "reporteCCCDetalladoProvisionIncobrableCampana";
	//REGION EXCEL
	public static final String PREFIJO_REPORTE_CCC_DETALLE_PROVIS_INCOBR_CR = "reporteCCCDetalladoProvisionIncobrableRegion";
	//CONSULTORAS CSV
	public static final String PREFIJO_REPORTE_CCC_DETALLE_PROVIS_INCOBR_DC = "reporteCCCDetalladoProvIncobrableCon";
	//MOVIMIENTOS CSV
	public static final String PREFIJO_REPORTE_CCC_DETALLE_PROVIS_INCOBR_DM = "reporteCCCDetalladoProvIncobrableMov";
	
	public static final String LEC_TARJETA_PAGO_LIST = "lecTarjetaPagoList";
	public static final String LEST_COD_ESTA = "01";
	public static final String LEC_ESTAD_TARJE_LIST = "lecEstadTarjeList";
	public static final String LEC_TARJETA_PAGO_LIDER_LIST = "lecTarjetaPagoLiderList";
	public static final String LEC_TARJETA_PAGO_TARJETA_PAGO_LIST = "lecTarjetaPagoTarjetaPagoList";
	public static final String LEC_ESTADO_TARJETA_DISPONIBLE = "01";
	public static final String LEC_ESTADO_TARJETA_ASIGNADA = "02";
	public static final String LEC_ESTADO_TARJETA_ENVIADA = "03";
	public static final String LEC_ESTADO_TARJETA_BLOQUEADA = "04";
	
	public static final String MAE_SI = "SI";
	public static final String POR_TARJETA = "1";
	public static final String POR_LIDER = "0";
		
	public static final String MAE_PARAMETRO_VALIDA_INGRESO_TELEFONO= "indValidaIngresoTelefono";
	
	public static final String REC_CODIGO_LIDER_ARCHIVO_LIST = "recCodigoLiderArchivoList";
	public static final String REC_NUMERO_TARJETA_ARCHIVO_LIST = "recNumeroTarjetaArchivoList";
	
	public static final int MAV_CONRES_POR_FORMULA = 64;
	public static final int MAV_CONRES_POR_VARIABLES_VENTA = 65;
	
	public static final String PED_OFERTA_MATRIZ_FACTURACION_PERIODO_LIST = "pedOfertaMatrizFacturacionPeriodoList";
	public static final String PED_OFERTA_MATRIZ_FACTURACION_ESTIMADO_LIST = "pedOfertaMatrizFacturacionEstimadoList";
	
	public static final String PED_MATRIZ_SELECCIONADA = "pedMatrizSeleccionada";
	public static final String PED_CODIGO_PANTALLA_PROCESO_ASIGNACION = "1";
	public static final String PED_CODIGO_PANTALLA_DEFINIR_OFERTA = "2";
	public static final String PED_CODIGO_PANTALLA_REGISTRO_AUTOMATICO = "3";
	public static final String PED_ESTRATEGIA_LIST = "pedEstrategiaList";
	public static final String PED_ACCESO_LIST = "pedAccesoList";
	public static final String PED_SUBACCESO_LIST = "pedSubaccesoList";
	public static final String PED_PRODUCTO_ASOCIADO_SEARCH_LIST = "pedProductoAsociadoSearchList";
	public static final String PED_PRODUCTO_ASOCIADO_SELECCIONADO_SEARCH_LIST = "pedProductoAsociadoSeleccionadoSearchList";
	public static final String PED_PRODUCTO_ASOCIADO_TIPO_OFERTA_LIST = "pedProductoAsociadoTipoOfertaList";
	public static final String PED_OFERTA_CATALOGO_SELECCIONADO = "pedOfertaCatalogoSeleccionado";
	public static final String PED_OFERTA_ESTRATEGIA_SELECCIONADA = "pedOfertaEstrategiaSeleccionada";
	public static final String PED_OFERTA_TIPO_ESTRATEGIA_SELECCIONADA = "pedOfertaTipoEstrategiaSeleccionada";
	public static final String PED_OFERTA_TIPO_BUSQUEDA_SELECCIONADA = "pedOfertaTipoBusquedaSeleccionada";
	
	public static final String PED_OID_TIPO_ESTRATEGIA_UNO = "1";
	public static final String PED_OID_TIPO_ESTRATEGIA_DOS = "2";
	public static final String PED_OID_TIPO_ESTRATEGIA_TRES = "3";
	public static final String PED_OID_TIPO_ESTRATEGIA_CUATRO = "4";	
	public static final String PED_OID_TIPO_ESTRATEGIA_CINCO = "5";
	public static final String PED_OID_TIPO_ESTRATEGIA_SEIS = "6";
	public static final String PED_OID_TIPO_ESTRATEGIA_SIETE = "7";
	public static final String PED_OID_TIPO_ESTRATEGIA_VEINTIDOS = "22";
	
	public static final String PED_PRODUCTO_ASOCIADO_OFERTA_LIST = "pedProductoAsociadoOfertaList";
	public static final String PED_INDICADOR_CUADRE_LIST = "pedIndicadorCuadreList";
	public static final String PED_INDICADOR_CUADRE_GRUPO_LIST = "pedIndicadorCuadreGrupoList";
	public static final String PED_PRODUCTO_ASOCIADO_OFERTA_GRUPO_LIST = "pedProductoAsociadoOfertaGrupoList";	
	public static final String PED_OFERTA_GRUPO_ACTUAL_LIST = "peOfertaGrupoActualList";
	
	public static final String PED_TIPO_BUSQUEDA_PRODUCTO_ASOCIADO = "1";
	public static final String PED_TIPO_BUSQUEDA_PRODUCTO_ASOCIADO_GRUPO = "2";
	
	public static final String PED_OFERTA_GRUPOS_MAP = "pedOfertaGruposMap";
	
	public static final String PED_CONDICION_PROMOCION_TIPO_CUADRE_LIST = "pedCondicionPromocionTipoCuadreList";
	
	public static final String PED_OFERTA_CRITERIOS_LIST = "pedOfertaCriteriosList";
	public static final String PED_OFERTA_COMPONENTES_LIST = "pedOfertaComponentesList";
	
	public static final String PED_TIPO_GRUPO_GRUPO = "Grupo";
	public static final String PED_TIPO_GRUPO_PAQUETE = "Paquete";
	public static final String PED_TIPO_GRUPO_CONDICIONANTE = "Condicionante";
	public static final String PED_TIPO_GRUPO_CONDICIONADO = "Condicionado";
	
	public static final String PRE_MATRIZ_RECUPERACION_LIST = "preMatrizRecuperacionList";
	
	public static final String PRE_REGION_LIST = "preRegionList";
	public static final String PRE_ZONA_LIST = "preZonaList";
	
	public static final String PROCESO_ZON_CARGAR_TUG_VIEW_VALIDA = "procesoZonCargarTugViewValida";
	public static final String PROCESO_ZON_CARGAR_TUG_ERROR_LIST = "procesoZonCargarTugErrorList";
	
	public static final String PRE_MATRIZ_ALTERNATIVOS_LIST = "preMatrizAlternativosList";
	
	public static final String LEC_IND_TIPO_GRUPO_PAGO = "indTipoGrupoPago";
	
	public static final String IND_DOC_FISC_SI = "S";
	public static final String IND_DOC_FISC_NO = "N";
	
	public static final String MAE_NOMB_PARAM_DOC_FISCAL = "indMuestraOpcionDocFiscal";
	
	public static final String LEC_CONSULTA_TARJETA_PAGO_LIST = "lecConsultaTarjetaPagoList";
	
	public static final String INC_CONCURSO_PROGRAMA_PUNTOS_TIPO_DESPACHO_LIST = "incConcursoProgramaPuntosTipoDespachoList";
	
	public static final String INC_TIPO_DESPACHO_CPP_DESPACHA_PREMIO_CODIGO = "0";
	public static final String INC_TIPO_DESPACHO_CPP_SIN_DESPACHO_PREMIO_CODIGO = "2";
	public static final String INC_TIPO_DESPACHO_CPP_PREMIACION_UNICA_CODIGO = "0";
	public static final String INC_TIPO_DESPACHO_CPP_PREMIACIONES_VARIAS_CODIGO = "1";
	
	public static final String INC_TIPO_DESPACHO_CPP_DESPACHA_PREMIO_NOMBRE = "Despacha Premio";
	public static final String INC_TIPO_DESPACHO_CPP_SIN_DESPACHO_PREMIO_NOMBRE = "Sin despacho de Premio";
	public static final String INC_TIPO_DESPACHO_CPP_PREMIACION_UNICA_NOMBRE = "Premiación única";
	public static final String INC_TIPO_DESPACHO_CPP_PREMIACIONES_VARIAS_NOMBRE = "Premiaciones varias";
	
	public static final String INC_PROGRAMAS_CONSTANCIA_PROGRAMA_PUNTOS_LIST = "incProgramasConstanciaProgramaPuntosList";
	public static final String INC_PROGRAMAS_CONSTANCIA_PROGRAMA_PUNTOS_RANGOS_LIST = "incProgramasConstanciaProgramaPuntosRangosList";
	
	public static final String SAM_CODIGO_PROCESO_BATCH_RECEPCIONAR_PRODUCTOS_NACIONALES_IMPORTADOS = "06";	
	public static final String SAM_CODIGO_INTERFAZ_RECEPCIONAR_PRODUCTOS_NACIONALES_IMPORTADOS = "SAM-8";
	
	public static final String CCC_CODIGO_PROCESO_BATCH_RECEPCIONAR_PAGOS_WEB = "06";
	public static final String CCC_CODIGO_PROGRAMA_RECEPCIONAR_PAGOS_WEB = "REPW";
	
	public static final String SAM_CODIGO_PROCESO_BATCH_RECEPCIONAR_STOCK_DIARIO = "02";
	public static final String SAM_CODIGO_INTERFAZ_RECEPCIONAR_STOCK_DIARIO_PROL = "SAM-12";
	public static final String SAM_CODIGO_INTERFAZ_RECEPCIONAR_STOCK_DIARIO_BATCH = "SAM-10";
	
	public static final String MAE_CODIGO_PROCESO_BATCH_CLASIFICACION_LOVE_CLIENTES = "11";
	
	public static final String IMP_CODIGO_PROCESO_BATCH_GENERACION_DIAS_SIN_FACTURACION_CAM = "12";
	public static final String IMP_CODIGO_INTERFAZ_GENERACION_DIAS_SIN_FACTURACION_CAM = "IMP-P8";
	
	public static final String IMP_CODIGO_PROCESO_BATCH_GENERACION_DIAS_SIN_FACTURACION_CAM_MAV = "15";
	public static final String IMP_CODIGO_INTERFAZ_GENERACION_DIAS_SIN_FACTURACION_CAM_MAV = "IMP-P11";

	public static final String COB_CODIGO_PROCESO_BATCH_GENERAR_REPORTES_FFVV_FTP = "19";
	
	public static final String DAT_CODIGO_PROCESO_BATCH_ENVIAR_FLUJOS_INCENTIVOS = "02";
	public static final String DAT_CODIGO_INTERFAZ_ENVIAR_FLUJOS_INCENTIVOS = "DAT-P4";
	
	public static final String LET_CODIGO_PROCESO_BATCH_ENVIAR_INFORMACION_FOX = "14";
	public static final String LET_CODIGO_INTERFAZ_ENVIAR_INFORMACION_FOX = "LET-P5";
	
	public static final String SAM_CODIGO_PROCESO_BATCH_ENVIAR_CANTIDAD_PRODUCTO = "04";
	public static final String SAM_CODIGO_INTERFAZ_ENVIAR_CANTIDAD_PRODUCTO = "SAM-13";
	
	public static final String SAM_CODIGO_PROCESO_BATCH_RECEPCIONAR_LOTES_PRODUCTO = "05";
	public static final String SAM_CODIGO_INTERFAZ_RECEPCIONAR_LOTES_PRODUCTO = "SAM-14";
	
	public static final String IMP_CODIGO_PROCESO_BATCH_GENERACION_NOTAS_CREDITO_DEBITO = "09";
	public static final String IMP_CODIGO_INTERFAZ_GENERACION_NOTAS_CREDITO_DEBITO = "IMP-P5";

	public static final String LAR_CODIGO_PROCESO_BATCH_GENERAR_LAR8_FACTURACION_ELECTRONICA = "04";
	public static final String LAR_CODIGO_INTERFAZ_GENERAR_LAR8_FACTURACION_ELECTRONICA = "LAR-25";
	
	public static final String EMP_CODIGO_SISTEMA = "EMP";
	public static final String EMP_CODIGO_PROCESO_BATCH_VINCULAR_NUEVAS_REACTIVADAS_CIERRE_REGION = "02";
	
	public static final String EMP_CODIGO_PROCESO_BATCH_ENVIAR_VARIABLES_PROGRAMA_EMPRESARIAS = "01";
	public static final String EMP_CODIGO_INTERFAZ_ENVIAR_VARIABLES_PROGRAMA_EMPRESARIAS = "EMP-P1";

	public static final String SMS_CODIGO_PROCESO_BATCH_ENVIAR_CONSULTORAS_PEDIDOS = "01";
	public static final String SMS_CODIGO_INTERFAZ_ENVIAR_CONSULTORAS = "SMS-P1";
	public static final String SMS_CODIGO_INTERFAZ_ENVIAR_PEDIDOS = "SMS-P2";
	
	public static final String HIP_NOMBRE_PARAMETRO_SITUACION_FLEXIPAGO = "indSituacionFlexipago";
	
    public static final String STO_VALIDACION_LIST = "stoValidacionList";
    
    public static final String STO_MOTIVO_RECHAZO_LIST = "stoMotivoRechazoList";
    
    public static final String STO_MENSAJE_VALIDACION_LIST = "stoMensajeValidacionList";
	
	public static final String BEL_CODIGO_PROCESO_BATCH_ENVIAR_INTERFACES_DIARIAS = "01";
	public static final String BEL_CODIGO_INTERFAZ_ENVIAR_INTERFACES_DIARIAS = "BEL-P1";
	public static final String BEL_CODIGO_SISTEMA = "BEL";
	
	public static final String CCC_CODIGO_PROCESO_BATCH_CARGAS_DEUDA_WEB = "20";
	public static final String CCC_CODIGO_INTERFAZ_CARGAS_DEUDA_WEB = "CCC-15";
	
	public static final String CCC_CODIGO_PROCESO_BATCH_ACTUALIZACION_SALDOS_PARA_SEGUIMIENTO_LEVANTAMIENTOS = "01";
	
	public static final String PER_CODIGO_PROCESO_BATCH_GENERACION_CTACTE_DOCUMENTO_LEGAL = "01";
	public static final String PER_CODIGO_INTERFAZ_GENERACION_CTACTE_DOCUMENTO_LEGAL = "PER-P1";
	public static final String PER_CODIGO_SISTEMA = "PER";
	
	public static final String RET_CODIGO_PROCESO_BATCH_RECEPCIONAR_VENTAS = "04";
	public static final String RET_CODIGO_INTERFAZ_RECEPCIONAR_VENTAS = "RET-P3";

	public static final String HIP_CODIGO_PROCESO_BATCH_RECEPCIONAR_REGISTRO_VENTAS = "01";
	public static final String HIP_CODIGO_INTERFAZ_RECEPCIONAR_REGISTRO_VENTAS = "HIP-1";
	
	public static final String PER_CODIGO_PROCESO_BATCH_RECEPCIONAR_PERCEPCIONES_OTROS_CANALES = "08";
	public static final String PER_CODIGO_INTERFAZ_RECEPCIONAR_PERCEPCIONES_OTROS_CANALES = "PER-2";
	
	public static final String APE_CODIGO_PROCESO_BATCH_SECUENCIA_CLIENTES = "01";
	
	public static final String APE_CODIGO_PROCESO_BATCH_INICIO_ARMADO_PEDIDOS = "05";
	public static final String APE_CODIGO_INTERFAZ_INICIO_ARMADO_PEDIDOS = "APE-26";
	
	public static final String APE_CODIGO_PROCESO_BATCH_PEDIDOS_CHEQUEADOS = "06";
	public static final String APE_CODIGO_INTERFAZ_PEDIDOS_CHEQUEADOS = "APE-27";
	
	public static final String APE_CODIGO_PROCESO_BATCH_PEDIDOS_DESPACHADOS = "07";
	public static final String APE_CODIGO_INTERFAZ_PEDIDOS_DESPACHADOS = "APE-28";

	public static final String REC_CODIGO_PROCESO_BATCH_ENVIAR_PRODUCTO_RECLAMADOS_P1 = "04";
	public static final String REC_CODIGO_INTERFAZ_ENVIAR_PRODUCTO_RECLAMADOS_P1 = "REC-P1";
	
	public static final String ORC_ARCHIVO_MULTIHILO = "orcArchivoControlMultihiloList";
	public static final String ORC_ARCHIVO_MULTIHILO_ID_PROC_BATC = "orcArchivoControlMultihiloIdProcBatcList";
	
	public static final String LET_CODIGO_PROCESO_BATCH_ASIGNAR_DESVINCULAR_LIDER = "16";
	
	public static final String CCC_REGULARIZACION_PAGOS_BANCARIOS_DIVIDIR_PAGO_LIST = "mantenimientoCCCRegularizacionPagosBancariosDividirPagoList";

	public static final String STO_PARAMETRO_INDICADOR_MAIL_REPORTE_STO = "IND_ENVI_MAIL_STO";
	
	public static final String EST_REGI = "1";
	
	public static final String PREFIJO_REPORTE_CCC_REGISTRO_VENTAS_BOLIVIA =  "reporteCCCRegVentasBol";
	public static final String PREFIJO_REPORTE_CCC_REGISTRO_ABONOS_BOLIVIA =  "reporteCCCRegAbonosBol";
	
public static final String IMP_PROCESO_IMPRESION_LIST = "impProcesoImpresionList";
	
	public static final String IMP_ETIQUETAS_LIST = "impEtiquetasList";
	
	public static final String IMP_TIPO_ETIQUETAS_LIST = "impTipoEtiquetasList";
	
	public static final String IMP_TIPO_ESTATUS_LIST = "impTipoEstatusList";
	
	public static final String IMP_ETIQUETAS_ESTATUS_LIST = "impEtiquetasEstatusList";
	public static final String STO_PROCESOS_EJECUTADOS_LIST = "stoProcesosEjecutadosList";
	public static final String IMP_TIPO_CLASIFICACION_LIST = "impTipoClasificacionList";
	
	public static final String IMP_CLASIFICACION_LIST = "impClasificacionList";
	
	public static final String IMP_ETIQUETAS_CLASIFICACION_LIST = "impEtiquetasClasificacionList";

	public static final String FLX_ZONAS_EXCLUIDAS_LIST = "flxZonasExcluidasList";
	public static final String IMP_CLASIFICACION_VIP_LIST = "impClasificacionVIPList";
	
	public static final String PROCESO_FLX_CARGAR_LDC_VIEW_VALIDA = "procesoFlxCargarLdcViewValida";
	public static final String PROCESO_FLX_CARGAR_LDC_ERROR_LIST = "procesoFlxCargarLdcErrorList";

	public static final String PROCESO_FLX_CARGAR_CONSULTORAS_VIEW_VALIDA = "procesoFlxCargarConsultorasViewValida";
	public static final String PROCESO_FLX_CARGAR_CONSULTORAS_ERROR_LIST = "procesoFlxCargarConsultorasErrorList";
	
	public static final String PREFIJO_REPORTE_CCC_BURO_CREDITO = "reporteCCCBuroCreditoCVS";
	
	public static final String REC_OPERACIONES_RECLAMO_LIST = "recOperacionesReclamoList";
	public static final String REC_MOTIVOS_BLOQUEO_LIST = "recMotivosBloqueoList";
	public static final String REC_MOVIMIENTO_ALMACEN_LIST = "recMovimientoAlmacenList";
	public static final String REC_MOTIVOS_RECHAZO_DESBLOQUEO_LIST = "recMotivosRechazoDesbloqueoList";
	public static final String REC_TIPOS_SOLICITUD_LIST = "recTiposSolicitudList";
	public static final String REC_TIPOS_SOLICITUD_GENERA_LIST = "recTiposSolicitudGeneraList";
	public static final String REC_ALMACEN_LIST = "recAlmacenList";
	
	public static final String PREFIJO_REPORTE_PED_SEGUIMIENTO_CONSULTORA=  "reportePEDSeguimientoConsultora";	
	
	public static final String HIP_NOMBRE_PARAMETRO_MOSTRAR_NOMAPE_CLIENTE = "indHabiNomApe";
	
	public static final String IMP_INTERFACES_PAQUETE = "impInterfazPaquete";
	
	public static final String MAE_NOMB_PARAM_VALIDA_DOCUMENTO_IDENTIDAD = "indValidaNroDocIdent";
	
	public static final String LEC_TIPO_BONO_LIST = "lecTipoBonoList";
	public static final String LEC_TIPO_PREMIACION_LIST = "lecTipoPremiacionList";
	public static final String LEC_TIPO_CANASTA_LIST = "lecTipoCanastaList";
	
	public static final String LEC_CODIGO_TIPO_CANASTA_OBJETIVO_PEDIDOS = "01";

	
	public static final String OCR_IND_ACTIVA_WS_SCCRECEP = "052";
	public static final String OCR_URL_WS_SCCRECEP = "053";	
	public static final String OCR_TOKEN_WS_SCCRECEP = "054";
	
	public static final String IND_WS_DATCRED_OCC = "057";
	public static final String URL_WS_OCC_DATCRED = "058";
	public static final String WS_OCC_LOGIN_DATCRED = "059";
		
	public static final String STO_IND_VAL_ACEP_CDR = "STO_IND_VAL_ACEP_CDR";
	
	public static final String STO_IND_MOT_ACEP_CDR = "STO_IND_MOT_ACEP_CDR";
	
	public static final String STO_VER_INFO_COME= "STO_VER_INFO_COME";
	
    public static final String INTERFAZ_NO_ASIGNADAS_LIST = "interfazNoAsignadasList";
	
	public static final String INTERFAZ_ASIGNADAS_LIST = "interfazAsignadasList";

	public static final String CCC_CONDONACION_DEUDAS_CASTIGADAS_LIST = "cccCondonacionDeudasCastigadasList";
	
	public static final String MAE_TIPO_DOCUMENTO_CI = "2001";


	/* NSSICC */
	public static final String CODIGO_EXTENSION_ARCHIVO_TXT = "01";
	public static final String CODIGO_EXTENSION_ARCHIVO_ERR = "03";
	public static final String CODIGO_EXTENSION_ARCHIVO_XML = "06";
	
	public static final String TIPO_GENERACION_UNITARIA_DESCRIPCION = "UNITARIO";
	
	public static final String CODIGO_TIPO_DATO_NUMERICO = "02";	
	
	public static final String MENSAJE_PROCESO_EXITO = "OK";
	public static final String MENSAJE_PROCESO_ERROR = "ERROR";
	

	public static final String PREFIJO_REPORTE_COB_DETA_COBRA_31_DIAS=  "reporteCOBDetalladoCobranza31Dias";
	
	public static final String CODIGO_VIGENCIA_ACTIVO  = "4";
	public static final String CODIGO_VIGENCIA_CERRADO = "2";
	public static final String JASPER_DIRECTORIO = "/biz/belcorp/ssicc/reportes/jasper/";
	
	public static final String CODIGO_NEGATIVO_UNO = "-1";
	
	public static final String CODIGO_MENU_PEDDEFINIROFERTA = "20261900";
	
	public static final String CODIGO_MENU_PEDASIGNARCODIGOVENTA = "20261800";
	
	public static final String CODIGO_MENU_PEDSELECCIONMATRIZFACTURACION = "20261700";
	
	
	public static final String  MATRIZ_PRECIOS = "Matriz de Precios";
	
	public static final String  MATRIZ_PREMIOS = "Matriz de Premios";
	
	public static final String  FACTURA = "Factura";
	
	public static final String  CATALOGO = "Catálogo";
	
	public static final String  REC_SI = "SI";
	
	public static final String  REC_NO = "NO";
	
	public static final String LEC_INDICADOR_TIPO_META_LIST = "lecIndicadorTipoMetaList";
	public static final String LEC_INDICADOR_TIPO_META_PEDIDO = "P";
	public static final String LEC_INDICADOR_TIPO_META_VENTA = "V";
	
	public static final String LEC_CODIGO_TIPO_PREMIACION_BONO = "01";
	public static final String LEC_CODIGO_TIPO_PREMIACION_CANASTA = "02";
	
	public static final String PREFIJO_REPORTE_SAC_CONTROL_FACTURACION_ENTREGA_PEDIDOS = "reporteSACControlFacturacionEntregaPedidos";
	
	public static final String INDICADOR_OCULTAR_MENU = "indicadorOcultarMenu";
	
	public static final String NUEVO = "NUEVO";
	
	public static final String PAQUETE = "PAQUETE";
	
	public static final String LEC_CODIGO_TIPO_COMISION_MONTO_FIJO = "01";
	
	public static final String FINALIZADO = "FINALIZADO";
	
	public static final String PREVIO_INTERFAZ = "PREVIO INTERFAZ"; 
	
	public static final String EJECUCION_INTERFAZ = "EJECUCION INTERFAZ"; 
	
	public static final String POSTERIOR_INTERFAZ = "POSTERIOR INTERFAZ";
	
	public static final String SISTEMA_REC = "REC";
	
	public static final String BOTON_ANULAR_ATENCIONES = "019";
	
	public static final String HIP_CCC_DESCRIPCION_TIPO_MOVIMIENTO_APRONO_EN_PROCESO_CDR = "Abono en proceso CDR";
	
	public static final String PRE_MATR_PLAN_CODIGO_SISTEMA = "PRE";
	
	public static final String PRE_CODIGO_INTERFAZ_MATR_PLAN = "PRE-P3";
	
	public static final String PRE_CODIGO_PROCESO_BATCH_MATR_PLAN = "02";
        public static final String HIP_CODIGO_PARAMETRO_RELLENAR_SOLO_ENTER = "017";
	public static final String HIP_NOMBRE_PARAMETRO_RELLENAR_SOLO_ENTER = "indRellenarSoloEnterConsultora";
	
	public static final String OCR_MOSTRAR_IND_CAMPOS_ADICIONALES_SCC = "055";
	public static final String OCR_NOM_PARA_CAMPOS_ADICIONALES_SCC = "SCC-indCamposAdicionales";
	public static final String OCR_MOSTRAR_IND_CAMPOS_ADICIONALES_SAD = "056";
	public static final String OCR_NOM_PARA_CAMPOS_ADICIONALES_SAD = "SAD-indCamposAdicionales";
	
	public static final String PATRON_FECHA_DDMMAAAA = "ddMMyyyy";
	
	public static final String SISTEMA_SEG = "SEG";
	public static final String SEG_INDICADOR_PARAMETRO_JOBBLOQUEO = "indicadorJobBloqueoEliminacion";
	public static final String SEG_NOMBRE_PARAMETRO_JOBBLOQUEO = "jobBloqueoEliminacion";
	public static final String ESTADO_BLOQUEO_USUARIO = "3";
	
	public static final String SEG_CORREO_ADMINISTRADOR_POLITICA_SEGURIDAD = "correoAdminPoliticaSeguridad";
	
	public static final String STO_ACTIVAR_BUSCAR_GRABAR_OCC = "activarBusquedaGrabarOCC";
	public static final String STO_ACTIVAR_CUADRO_OFERTA_OCC = "indCuadroOfertaMontoMinimo";
	
	public static final String PREFIJO_REPORTE_COB_NUMERO_PAGO_CAMPANNA =  "reporteCOBNumeroPagosCampanna";
	
	public static final String ZON_UNID_ADMIN_IND_REGION=  "RE";
	
	public static final String ZON_UNID_ADMIN_IND_ZONA=  "ZO";
	
	public static final String CODIGO_SISTEMA_PRE = "PRE";
	public static final String COD_PARAM_MODIFICA_OFERTA_CERRADA = "002";
	public static final String NOM_PARAM_MODIFICA_OFERTA_CERRADA = "indModificarOfertaCerrada";
	
	public static final String LAR_CODIGO_SISTEMA = "LAR";
	public static final String LAR_CODIGO_PERIODO_EJECUCION_CARGA_TRACKING = "001";
	public static final String LAR_CODIGO_INDICADOR_EJECUCION_CARGA_TRACKING = "002";
	public static final String LAR_PROCESO_RECEPCIONAR_CARGA_TRACKING = "LAR-37";
	public static final String LAR_CODIGO_PROCESO_BATCH_RECEPCIONAR_CARGA_TRACKING = "06";
	
	public static final String MSG_CODIGO_PARAMETRO_RUTA_IMAGENES = "005";
	public static final String MSG_NOMBRE_PARAMETRO_RUTA_IMAGENES = "valRutaImg";
	
	public static final String LAR_NRO_ARCHIVO_PROCESO_QUARTZ_RECEPCIONAR_CARGA_TRACKING = "nroArchivosProcesarQuartzLARWeb";
	
	public static final String CODIGO_MENU_REGISTROAUTOMATICO = "20262400";
	
	public static final String ZON_UA_REGION_ZONA = "indicadorUARegionZona";
	
	public static final String PRE_CODIGO_PROCESO_BATCH_ACTUALIZAR_TIPO_CAMBIO = "03";	
	
	public static final String PROCESO_GP4 = "G";
	
	public static final String STO_ESTA_OID_INFO = "STO_ESTA_OID_INFO";
	
	public static final String VALOR_SECU_PERCEPCION_SUNAT= "00001";
	
	public static final String CUPON_TIPO_ELECTIVO = "L";
	public static final String CUPON_TIPO_CUPON = "B";
	public static final String CUPON_TIPO_AUTOMATICO = "C";


}