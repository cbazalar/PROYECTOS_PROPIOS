package org.sistema.framework.dao;

import org.sistema.framework.dao.Constants;


/**
 * Constantes usadas en toda la aplicación.
 * <p>
 * <a href="Constants.java.html"> <i>View Source </i> </a>
 * </p>
 * 
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
	 * The request scope attribute that holds the menu form.
	 */
	public static final String MENU_KEY = "menuForm";

	
	/**
	 * The request scope attribute that holds the usuario form.
	 */
	public static final String USUARIO_KEY = "usuarioForm";

	/**
	 * The request scope attribute that holds the pais form.
	 */
	public static final String PAIS_KEY = "paisForm";


	/**
	 * The request scope attribute that holds the idioma form.
	 */
	public static final String IDIOMA_KEY = "idiomaForm";

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



	public static final String ERROR_COPY_PROPERTIES = "Error al transferir informacion al utilizar Sentencia CopyProperties ";

	public static final Long REGISTROS_ERRONEOS_ZERO = new Long(0);

	public static final String NUMERO_CERO = "0";

	public static final String NUMERO_UNO = "1";

	public static final String NUMERO_DOS = "2";

	public static final String NUMERO_SEIS = "6";


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


	public static final String VALOR_TODAS = "TODAS";

	public static final String EXTENSION_TMP = "TMP";

	public static final String EXTENSION_ZIP = "ZIP";



	public static final String TIPO_SEPARADOR_LINEA_SISTEMA_OPERATIVO = "S";

	public static final String TIPO_SEPARADOR_LINEA_WINDOWS = "W";

	public static final String TIPO_SEPARADOR_LINEA_UNIX = "U";

	public static final String SEPARADOR_LINEA_SISTEMA_OPERATIVO = System
			.getProperty("line.separator");

	public static final String SEPARADOR_LINEA_WINDOWS = "\r\n";

	public static final String SEPARADOR_LINEA_UNIX = "\n";

	
	/**
	 * Valor usado en los estados para indicar que el registro esta activo o
	 * inactivo
	 */
	public static final String ESTADO_ENTIDAD_ACTIVO = "A";
	public static final String ESTADO_ENTIDAD_INACTIVO = "I";
	public static final String ESTADO_ENTIDAD_RETIRADA = "R";
	

	public static final String ACTIVO = "1";

	public static final String MENU_NOMBRE_CODIGO_MENU = "codigoMenu";

	public static final String MENU_INFO_MENU = "informacionMenu";



	/**
	 * Constantes para los WebServices
	 */
	public static final String EDU_IDIOMA_DEFAULT_ES = "es";
	public static final String EDU_IDIOMA_INGLES = "us";

	
	public static final String USUARIO_ADMIN = "ADMIN";

	public static final int PAGINACION_SIZE_BOLETA_RECOJO = 10;


	public static final String OPCION_TODOS = "Todos";



		

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
	
	public static final String TIPO_DATO_NUMERICO = "N";

	public static final String ARCHIVO_FIJO = "F";

	public static final String ARCHIVO_VARIABLE = "V";
	
	public static final String EDU_FILE_SEPARATOR = "/";
	
	public static final String MESSAGE_MAE_EMAIL_SIN_FORMATO = "Email Sin Formato Correcto";
}