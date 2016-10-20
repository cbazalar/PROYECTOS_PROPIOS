package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEModificacionClienteForm extends BaseEditForm{


	private static final long serialVersionUID = 1L;

	private String oidPais;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;

	private String oidCliente;
	private String codigoCliente;
	private String digitoControl;

	private String fechaIngreso;
	private Date fechaIngresoD;
	private String indicadorActivo;
	private String estatus;

	private String apellidoPaterno;
	private String apellidoMaterno;
	private String apellidoCasada;
	private String nombre1;
	private String nombre2;
	private String sexo;
	private String tratamiento;
	private String edad;
	private String fechaNacimiento;
	private Date fechaNacimientoD;
	private String estadoCivil;
	private String gradoInstruccion;
	private String nacionalidad;
	private String oidPeriodo;
	private String oidPeriodoPrimerPedido;
	private String codigoEmpleado;

	// si se muestra el control de texto para el ingreso del codigo de empleado
	private boolean mostrarCodigoEmpleado;

	// Para obligar a ingresar tratamiento
	private boolean indicadoObligatorioTratamiento;

	// Para obligar a ingresar grado de instruccion
	private boolean indicadoObligatorioGradoInstruccion;

	// Para obligar a ingresar nacionalidad
	private boolean indicadoObligatorioNacionalidad;

	// Se mostrara el documento principal y 2 documentos auxiliares
	private boolean mostrarNumeroIdentidad;
	private String tipoDocumentoIdentidad;
	private String numeroDocumentoIdentidad;
	private String tipoDocumentoIdentidad2;
	private String numeroDocumentoIdentidad2;
	private String tipoDocumentoIdentidad3;
	private String numeroDocumentoIdentidad3;
	private String indDocumentoPrincipal;
	
	//se mostrara los sub tipos de documentos por cada tipo de documento
	private boolean mostrarSubTipoDocumento;
	private String subTipoDocumentoIdentidad;
	private String subTipoDocumentoIdentidad2;
	private String subTipoDocumentoIdentidad3;

	// Almacena la longitud del tipo de documento de Identidad principal y
	// auxiliares
	private String longitudTipoDocumento;
	private String longitudTipoDocumento2;
	private String longitudTipoDocumento3;

	// Datos de TIPOS CLIENTES / CLASIFICACIONES
	private boolean mostrarTiposClientes;
	private String subTipoCliente;
	private String tipoClasificacion;
	private String Clasificacion;

	// si el tipo de cliente y subtipo de cliente seleccionado es HijaDupla (es
	// true), en otro caso (false)
	private boolean esDuplaCyzone;

	// control que va a tener el foco cuando se pinte la pantalla
	private String controlFoco;

	private String descripcionNivel1;
	private String descripcionNivel2;
	private String descripcionNivel3;
	private String descripcionNivel4;
	private String descripcionNivel5;
	private String descripcionNivel6;
	private String totalNiveles;

	// Datos de SECCION DIRECCION DOMICILIO
	private boolean mostrarUnidadAdministrativa;
	private String codigoZona;
	private String codigoTerritorio;
	private String oidTerritorioAdministrativo;
	private String oidTerritorio;

	private boolean mostrarDireccion;
	private String tipoVia;
	private String numeroPrincipal;
	private String nombreVia;
	private String observacionDireccion;
	private String barrio;
	private String nivel1;
	private String nivel2;
	private String nivel3;
	private String nivel4;
	private String nivel5;
	private String nivel6;
	private String telefonoCasa;
	private String telefonoCelular;
	private String telefonoTrabajo;
	private String mail;

	/* INI SA PER-SiCC-2012-0459 */
	private String codigoCiudad;
	private String villaPoblacion;

	private boolean mostrarCiudad;
	private boolean mostrarVillaPoblacion;
	/* FIN SA PER-SiCC-2012-0459 */

	// longitud de codigo de cliente para el pais
	private String longitudCodigoCliente;

	// Para verificar si es automatico la generacion del codigo de cliente para
	// el pais
	private boolean esCodigoClienteAutomatico;

	// Datos de SECCION DIRECCION CENTRO DE TRABAJO
	private String tipoDireccionCT;
	private String tipoViaCT;
	private String numeroPrincipalCT;
	private String nombreViaCT;
	private String observacionDireccionCT;
	private String barrioCT;
	private String nivel1CT;
	private String nivel2CT;
	private String nivel3CT;
	private String nivel4CT;
	private String nivel5CT;
	private String nivel6CT;
	private String telefonoCasaDireccionEntrega;
	private String telefonoCelularDireccionEntrega;

	/* INI SA PER-SiCC-2012-0459 */
	private String codigoCiudadCT;
	private String villaPoblacionCT;
	/* FIN SA PER-SiCC-2012-0459 */

	/* INI SA PER-SiCC-2012-0365 */
	// Datos de SECCION DIRECCION VACACIONES
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String tipoViaVacaciones;
	private String numeroPrincipalVacaciones;
	private String nombreViaVacaciones;
	private String observacionDireccionVacaciones;
	private String barrioVacaciones;

	private String nivel1Vacaciones;
	private String nivel2Vacaciones;
	private String nivel3Vacaciones;
	private String nivel4Vacaciones;
	private String nivel5Vacaciones;
	private String nivel6Vacaciones;
	private String codNivel2Vacaciones;
	private String codNivel3Vacaciones;
	private String codNivel4Vacaciones;
	private String codNivel5Vacaciones;
	private String codNivel6Vacaciones;

	private String codigoCiudadVacaciones;
	private String villaPoblacionVacaciones;

	private String telefonoCasaDireccionVacaciones;
	private String telefonoCelularDireccionVacaciones;

	private boolean mostrarDireccionVacaciones;
	private String indicadorDireccionVacaciones;
	private boolean primeraVezDespliegueDireccionVacaciones;
	private boolean borradoDireccionVacaciones;
	private boolean tieneDireccionVacaciones;

	private String codigoPeriodoInicioVacaciones;
	private String codigoPeriodoFinVacaciones;
	private boolean tienePedidoFacturadoProceso;
	private boolean actualizaUbigeoDirecciones;
	private boolean tienePedidoEnProcesoFacturacion;
	private String lineaDireccionVacaciones;
	/* FIN SA PER-SiCC-2012-0365 */

	// Datos de VINCULO
	private String oidConsultoraVinculo;
	private String codigoConsultoraVinculo;
	private String nombreConsultoraVinculo;
	private String fechaDesde;
	private Date fechaDesdeD;
	private String fechaHasta;
	private Date fechaHastaD;

	private String edadMinima;
	private String edadMaxima;
	private String fechaActual;

	// indica si actualizo correctamente el cliente
	private boolean actualizoOK;

	// si se ha confirmado [Aceptar] cuando el territorio es diferente al ubigeo
	// del distrito
	private String confirmacionTerritorio;

	// En casos que se necesita mostrar mensajes de confirmacion para poder
	// proceder a grabar los datos del cliente
	private String mensajeConfirmacion;

	private boolean mostrarConsultoraVinculo;

	private String indicadorTiposCliente;
	private String indicadorDireccionDomicilio;
	private String indicadorDireccionOpcional;
	private String indicadorConsultoraVinculo;

	private boolean tieneIdentificacionPrincipal;

	private boolean tieneDireccionPrincipal;
	private boolean tieneDireccionOpcional;
	private boolean tieneDocumentoIdentidad2;
	private boolean tieneDocumentoIdentidad3;

	private boolean borradoDireccionPrincipal;
	private boolean borradoDireccionOpcional;
	private boolean borradoDocumentoIdentidad1;
	private boolean borradoDocumentoIdentidad2;
	private boolean borradoDocumentoIdentidad3;

	private String codigoPeriodo;
	private String codigoEstatus;
	private boolean esClienteCaducado;
	private boolean tieneTipologiaConsultora;
	private boolean modificarIdentificacionPrincipal;
	private boolean tienePedidoFacturado;
	private boolean tienePedidoFacturadoVigente;

	// Para el caso de las retiradas, no se borra los datos del recomendante
	// anterior
	private boolean borrarConcursosPremios;

	private boolean tieneTipologiaConsultoraInicio;

	private String codigoConsultoraRecomendante;
	private boolean modificarConsultoraVinculo;

	private String oidPeriodoConcurso;
	private boolean primeraVezDespliegueDireccionEntrega;
	private boolean mostrarPantallaPremios;
	private boolean mostrarDigitoControl;
	private boolean cambioZonaTerritorio;

	// Para validar si cambio Zona, territorio, y obligar a crear una nueva
	// direccion principal
	private String codigoZonaInicial;
	private String codigoTerritorioInicial;

	// Referencia Familiar
	private String apellido1RefFamiliar;
	private String apellido2RefFamiliar;
	private String nombre1RefFamiliar;
	private String nombre2RefFamiliar;
	private String direccionRefFamiliar;
	private String barrioRefFamiliar;
	private String telefonoCasaRefFamiliar;
	private String telefonoCelRefFamiliar;
	private String codigoTipoVinculoRefFamiliar;
	// Referencia No Familiar
	private String apellido1RefNoFamiliar;
	private String apellido2RefNoFamiliar;
	private String nombre1RefNoFamiliar;
	private String nombre2RefNoFamiliar;
	private String direccionRefNoFamiliar;
	private String barrioRefNoFamiliar;
	private String telefonoCasaRefNoFamiliar;
	private String telefonoCelRefNoFamiliar;
	private String codigoTipoVinculoRefNoFamiliar;
	// Referencia A Aval
	private String apellido1Aval;
	private String apellido2Aval;
	private String nombre1Aval;
	private String nombre2Aval;
	private String estado;
	private String municipio;
	private String parroquia;
	private String direccionAval;
	private String barrioAval;
	private String telefonoCasaAval;
	private String telefonoCelAval;
	private String codigoTipoVinculoAval;
	private String codigoDepartamentoAval;
	private String codigoProvinciaAval;
	private String codigoDistritoAval;
	private String oidTipoDocumentoAval;
	private String numeroDocumentoAval;
	private String chkReferencias;

	private String codNivel1;
	private String codNivel2;
	private String codNivel3;
	private String codNivel4;
	private String codNivel5;
	private String codNivel6;

	private String longitudCodigoZona;
	private boolean mostrarTipoVia;
	private boolean mostrarNumeroPrincipal;
	private boolean permitirModificarUbigeo;
	private boolean mostrarUbigeoEntrega;

	private String longitudTipoDocumentoAval;
	private boolean primeraVezDespliegueReferencias;

	private boolean permitirModificarPeriodoIngreso;
	private boolean permitirModificarPeriodoVigente;
	private boolean mostrarMensajeCambioPeriodoVigente;
	private boolean mostrarMensajePedidoExtemporaneo;
	private boolean mostrarMensajePeriodoFinCerrado;
	private boolean consultoraPasoPedido;

	private boolean editable;

	private boolean mostrarRedefinirPeriodo;
	private String oidPeriodoRedefinir;

	private boolean esClientePotencialAval;
	private boolean aprobarAvaladas;

	// Indica si se completa con Ceros el numero de Documento de Identidad
	private boolean permitirCompletarCerosIdentidad;

	// Indica si se valida si se deja grabar la consultora con '0' al inicio del
	// numero de Documento de Identidad
	private boolean permitirComenzarCerosIdentidad;

	private String validarCaracteres1;
	private String validarCaracteres2;
	private String validarCaracteres3;
	private String validarCaracteresIdentidad;

	private String cadenaCaracteresV1;
	private String cadenaCaracteresNV1;
	private String cadenaCaracteresV2;
	private String cadenaCaracteresNV2;
	private String cadenaCaracteresV3;
	private String cadenaCaracteresNV3;
	private String cadenaCaracteresVIdentidad;
	private String cadenaCaracteresNVIdentidad;

	private String telefonoReferencia;
	private String codigoAnterior;

	private boolean mostrarBarrio;

	public String tipoCutis;

	public String otrasMarcas;

	/* INI SA COS-SiCC-2013-0031 */
	private String indicadorImpresionPaqDoc;
	private boolean mostrarIndicadorImpresionPaqDoc;
	/* FIN SA COS-SiCC-2013-0031 */

	/* INI JJ PER-SiCC-2012-0329 */
	private boolean mostrarCodigoCUB;
	private String codigoCUB;
	private String codigoCUBAnterior;

	private boolean indicadorSeccionOtros;
	private boolean indicadorSeccionCompromiso;
	private String valorIndicadorCompromiso;
	private String indicadorCompromiso;
	private String motivo;

	/* INI JP PER-SiCC-2013-0480 */
	private boolean indicadorDocumentosLegales;
	/* FIN JP PER-SiCC-2013-0480 */

	private String codigoRegion;
	private String codigoSeccion;

	private String indicadorSolicitudCredito;

	private String indicadorPantallaModificacion;

	private String indicadorGraboOk;

	private boolean esMayorPeriodoVigente;
	private boolean requiereGenerarEstatus;
	private String codigoPeriodoIniUA;
	
	private boolean mostrarBotonRedifinirVigenciaUA;
	
	/*
	 * indica si se mostrara lupita de buscar direccion
	 */
	private boolean mostrarBuscarDireccion;

	private String correVia;
	private String correViaCT;
	private String correViaVacaciones;
	
	/* INI PER-SiCC-2014-0162 */
	private boolean indicadorFactElect;
	private String valorIndicadorFactElect;
	
	private boolean valorIndicadorFactElectB;
	
	
	private String valorIndicadorFactElectAux;
	
	private boolean valorIndicadorFactElectAuxB;
	/* FIN PER-SiCC-2014-0162 */
	
	
	private String codigoPostal;
	private String codigoPostalCT;
	
	private String telefonoAdicional;
		
	//si el tipo de cliente y subtipo de cliente seleccionado es Consultora, sea Negocio u Oficina
	private boolean esTipoConsultora;
	
	// Datos de VINCULO Lider
	private boolean mostrarVinculoLider;
	private String oidLiderVinculo;
	private String codigoLiderVinculo;
	private String nombreLiderVinculo;
	private String fechaDesdeLider;

	private String fechaHastaLider;

	
	private boolean indicadorDocFiscalAux;
	private String indicadorDocFiscal;
	
	private Date fechaDesdeLiderD;
	private Date fechaHastaLiderD;

	private boolean indicadorDocFiscalB;
	
	//INI ECU-SiCC-2015-0036
		private String tipoPersona;
		private String origenIngreso;
		private boolean indicadorCamposAdicionales;
		
		private String barrioDD;
		private String manzanaLetraDD;
		private String etapaConjuntoDD;
		private String callePrincipalDD;
		private String calleSecundariaDD;
		
		private String barrioDE;
		private String manzanaLetraDE;
		private String etapaConjuntoDE;
		private String callePrincipalDE;
		private String calleSecundariaDE;
		//FIN ECU-SiCC-2015-0036
	
	//INICIO PER-SiCC-2015-0462		
	private Long codigoBanco;
	private String cuentaBancaria;
	//FIN PER-SiCC-2015-0462
	
	/* INI PER-SiCC-2015-0589 */
	private boolean indicadorCalcPercep;
	private boolean booleanValorIndicadorCalcPercep;
	/* FIN PER-SiCC-2015-0589 */
	
	//INICIO ECU-SiCC-2015-0049
	private String codigoTerritorialCorrespondeDD;
	//FIN ECU-SiCC-2015-0049
	
	//INICIO PER-SiCC-2015-0662
	private String banco;
	private String tipoCuenta;
	private String cuentaCorriente;
	//FIN PER-SiCC-2015-0662
	
	public Date getFechaDesdeLiderD() {
		return fechaDesdeLiderD;
	}

	public void setFechaDesdeLiderD(Date fechaDesdeLiderD) {
		this.fechaDesdeLiderD = fechaDesdeLiderD;
	}

	public Date getFechaHastaLiderD() {
		return fechaHastaLiderD;
	}

	public void setFechaHastaLiderD(Date fechaHastaLiderD) {
		this.fechaHastaLiderD = fechaHastaLiderD;
	}

	public boolean isIndicadorDocFiscalB() {
		return indicadorDocFiscalB;
	}

	public void setIndicadorDocFiscalB(boolean indicadorDocFiscalB) {
		this.indicadorDocFiscalB = indicadorDocFiscalB;
	}

	/**
	 * @return
	 */
	public String getCodigoCUBAnterior() {
		return codigoCUBAnterior;
	}

	public boolean isIndicadorFactElect() {
		return indicadorFactElect;
	}

	public String getValorIndicadorFactElect() {
		return valorIndicadorFactElect;
	}

	public void setIndicadorFactElect(boolean indicadorFactElect) {
		this.indicadorFactElect = indicadorFactElect;
	}

	public void setValorIndicadorFactElect(String valorIndicadorFactElect) {
		this.valorIndicadorFactElect = valorIndicadorFactElect;
	}

	/**
	 * @param codigoCUBAnterior
	 */
	public void setCodigoCUBAnterior(String codigoCUBAnterior) {
		this.codigoCUBAnterior = codigoCUBAnterior;
	}

	/**
	 * @return
	 */
	public boolean isMostrarCodigoCUB() {
		return mostrarCodigoCUB;
	}

	/**
	 * @param mostrarCodigoCUB
	 */
	public void setMostrarCodigoCUB(boolean mostrarCodigoCUB) {
		this.mostrarCodigoCUB = mostrarCodigoCUB;
	}

	/**
	 * @return
	 */
	public String getCodigoCUB() {
		return codigoCUB;
	}

	/**
	 * @param codigoCUB
	 */
	public void setCodigoCUB(String codigoCUB) {
		this.codigoCUB = codigoCUB;
	}

	/* FIN JJ PER-SiCC-2012-0329 */
	/**
	 * @return the editable
	 */
	
	
	public boolean isEditable() {
		return editable;
	}

	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}

	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}

	public Date getFechaHastaD() {
		return fechaHastaD;
	}

	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the chkReferencias
	 */
	public String getChkReferencias() {
		return chkReferencias;
	}

	/**
	 * @param chkReferencias
	 *            the chkReferencias to set
	 */
	public void setChkReferencias(String chkReferencias) {
		this.chkReferencias = chkReferencias;
	}

	/**
	 * @return the longitudTipoDocumentoAval
	 */
	public String getLongitudTipoDocumentoAval() {
		return longitudTipoDocumentoAval;
	}

	/**
	 * @param longitudTipoDocumentoAval
	 *            the longitudTipoDocumentoAval to set
	 */
	public void setLongitudTipoDocumentoAval(String longitudTipoDocumentoAval) {
		this.longitudTipoDocumentoAval = longitudTipoDocumentoAval;
	}

	/**
	 * @return the apellido1RefFamiliar
	 */
	public String getApellido1RefFamiliar() {
		return apellido1RefFamiliar;
	}

	/**
	 * @param apellido1RefFamiliar
	 *            the apellido1RefFamiliar to set
	 */
	public void setApellido1RefFamiliar(String apellido1RefFamiliar) {
		this.apellido1RefFamiliar = apellido1RefFamiliar;
	}

	/**
	 * @return the apellido2RefFamiliar
	 */
	public String getApellido2RefFamiliar() {
		return apellido2RefFamiliar;
	}

	/**
	 * @param apellido2RefFamiliar
	 *            the apellido2RefFamiliar to set
	 */
	public void setApellido2RefFamiliar(String apellido2RefFamiliar) {
		this.apellido2RefFamiliar = apellido2RefFamiliar;
	}

	/**
	 * @return the nombre1RefFamiliar
	 */
	public String getNombre1RefFamiliar() {
		return nombre1RefFamiliar;
	}

	/**
	 * @param nombre1RefFamiliar
	 *            the nombre1RefFamiliar to set
	 */
	public void setNombre1RefFamiliar(String nombre1RefFamiliar) {
		this.nombre1RefFamiliar = nombre1RefFamiliar;
	}

	/**
	 * @return the nombre2RefFamiliar
	 */
	public String getNombre2RefFamiliar() {
		return nombre2RefFamiliar;
	}

	/**
	 * @param nombre2RefFamiliar
	 *            the nombre2RefFamiliar to set
	 */
	public void setNombre2RefFamiliar(String nombre2RefFamiliar) {
		this.nombre2RefFamiliar = nombre2RefFamiliar;
	}

	/**
	 * @return the direccionRefFamiliar
	 */
	public String getDireccionRefFamiliar() {
		return direccionRefFamiliar;
	}

	/**
	 * @param direccionRefFamiliar
	 *            the direccionRefFamiliar to set
	 */
	public void setDireccionRefFamiliar(String direccionRefFamiliar) {
		this.direccionRefFamiliar = direccionRefFamiliar;
	}

	/**
	 * @return the telefonoCasaRefFamiliar
	 */
	public String getTelefonoCasaRefFamiliar() {
		return telefonoCasaRefFamiliar;
	}

	/**
	 * @param telefonoCasaRefFamiliar
	 *            the telefonoCasaRefFamiliar to set
	 */
	public void setTelefonoCasaRefFamiliar(String telefonoCasaRefFamiliar) {
		this.telefonoCasaRefFamiliar = telefonoCasaRefFamiliar;
	}

	/**
	 * @return the telefonoCelRefFamiliar
	 */
	public String getTelefonoCelRefFamiliar() {
		return telefonoCelRefFamiliar;
	}

	/**
	 * @param telefonoCelRefFamiliar
	 *            the telefonoCelRefFamiliar to set
	 */
	public void setTelefonoCelRefFamiliar(String telefonoCelRefFamiliar) {
		this.telefonoCelRefFamiliar = telefonoCelRefFamiliar;
	}

	public Date getFechaIngresoD() {
		return fechaIngresoD;
	}

	public void setFechaIngresoD(Date fechaIngresoD) {
		this.fechaIngresoD = fechaIngresoD;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the codigoTipoVinculoRefFamiliar
	 */
	public String getCodigoTipoVinculoRefFamiliar() {
		return codigoTipoVinculoRefFamiliar;
	}

	/**
	 * @param codigoTipoVinculoRefFamiliar
	 *            the codigoTipoVinculoRefFamiliar to set
	 */
	public void setCodigoTipoVinculoRefFamiliar(
			String codigoTipoVinculoRefFamiliar) {
		this.codigoTipoVinculoRefFamiliar = codigoTipoVinculoRefFamiliar;
	}

	/**
	 * @return the apellido1RefNoFamiliar
	 */
	public String getApellido1RefNoFamiliar() {
		return apellido1RefNoFamiliar;
	}

	/**
	 * @param apellido1RefNoFamiliar
	 *            the apellido1RefNoFamiliar to set
	 */
	public void setApellido1RefNoFamiliar(String apellido1RefNoFamiliar) {
		this.apellido1RefNoFamiliar = apellido1RefNoFamiliar;
	}

	/**
	 * @return the apellido2RefNoFamiliar
	 */
	public String getApellido2RefNoFamiliar() {
		return apellido2RefNoFamiliar;
	}

	/**
	 * @param apellido2RefNoFamiliar
	 *            the apellido2RefNoFamiliar to set
	 */
	public void setApellido2RefNoFamiliar(String apellido2RefNoFamiliar) {
		this.apellido2RefNoFamiliar = apellido2RefNoFamiliar;
	}

	/**
	 * @return the nombre1RefNoFamiliar
	 */
	public String getNombre1RefNoFamiliar() {
		return nombre1RefNoFamiliar;
	}

	/**
	 * @param nombre1RefNoFamiliar
	 *            the nombre1RefNoFamiliar to set
	 */
	public void setNombre1RefNoFamiliar(String nombre1RefNoFamiliar) {
		this.nombre1RefNoFamiliar = nombre1RefNoFamiliar;
	}

	/**
	 * @return the nombre2RefNoFamiliar
	 */
	public String getNombre2RefNoFamiliar() {
		return nombre2RefNoFamiliar;
	}

	/**
	 * @param nombre2RefNoFamiliar
	 *            the nombre2RefNoFamiliar to set
	 */
	public void setNombre2RefNoFamiliar(String nombre2RefNoFamiliar) {
		this.nombre2RefNoFamiliar = nombre2RefNoFamiliar;
	}

	/**
	 * @return the direccionRefNoFamiliar
	 */
	public String getDireccionRefNoFamiliar() {
		return direccionRefNoFamiliar;
	}

	/**
	 * @param direccionRefNoFamiliar
	 *            the direccionRefNoFamiliar to set
	 */
	public void setDireccionRefNoFamiliar(String direccionRefNoFamiliar) {
		this.direccionRefNoFamiliar = direccionRefNoFamiliar;
	}

	/**
	 * @return the telefonoCasaRefNoFamiliar
	 */
	public String getTelefonoCasaRefNoFamiliar() {
		return telefonoCasaRefNoFamiliar;
	}

	/**
	 * @param telefonoCasaRefNoFamiliar
	 *            the telefonoCasaRefNoFamiliar to set
	 */
	public void setTelefonoCasaRefNoFamiliar(String telefonoCasaRefNoFamiliar) {
		this.telefonoCasaRefNoFamiliar = telefonoCasaRefNoFamiliar;
	}

	/**
	 * @return the telefonoCelRefNoFamiliar
	 */
	public String getTelefonoCelRefNoFamiliar() {
		return telefonoCelRefNoFamiliar;
	}

	/**
	 * @param telefonoCelRefNoFamiliar
	 *            the telefonoCelRefNoFamiliar to set
	 */
	public void setTelefonoCelRefNoFamiliar(String telefonoCelRefNoFamiliar) {
		this.telefonoCelRefNoFamiliar = telefonoCelRefNoFamiliar;
	}

	/**
	 * @return the codigoTipoVinculoRefNoFamiliar
	 */
	public String getCodigoTipoVinculoRefNoFamiliar() {
		return codigoTipoVinculoRefNoFamiliar;
	}

	/**
	 * @param codigoTipoVinculoRefNoFamiliar
	 *            the codigoTipoVinculoRefNoFamiliar to set
	 */
	public void setCodigoTipoVinculoRefNoFamiliar(
			String codigoTipoVinculoRefNoFamiliar) {
		this.codigoTipoVinculoRefNoFamiliar = codigoTipoVinculoRefNoFamiliar;
	}

	/**
	 * @return the apellido1Aval
	 */
	public String getApellido1Aval() {
		return apellido1Aval;
	}

	/**
	 * @param apellido1Aval
	 *            the apellido1Aval to set
	 */
	public void setApellido1Aval(String apellido1Aval) {
		this.apellido1Aval = apellido1Aval;
	}

	/**
	 * @return the apellido2Aval
	 */
	public String getApellido2Aval() {
		return apellido2Aval;
	}

	/**
	 * @param apellido2Aval
	 *            the apellido2Aval to set
	 */
	public void setApellido2Aval(String apellido2Aval) {
		this.apellido2Aval = apellido2Aval;
	}

	/**
	 * @return the nombre1Aval
	 */
	public String getNombre1Aval() {
		return nombre1Aval;
	}

	/**
	 * @param nombre1Aval
	 *            the nombre1Aval to set
	 */
	public void setNombre1Aval(String nombre1Aval) {
		this.nombre1Aval = nombre1Aval;
	}

	/**
	 * @return the nombre2Aval
	 */
	public String getNombre2Aval() {
		return nombre2Aval;
	}

	/**
	 * @param nombre2Aval
	 *            the nombre2Aval to set
	 */
	public void setNombre2Aval(String nombre2Aval) {
		this.nombre2Aval = nombre2Aval;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the parroquia
	 */
	public String getParroquia() {
		return parroquia;
	}

	/**
	 * @param parroquia
	 *            the parroquia to set
	 */
	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	/**
	 * @return the direccionAval
	 */
	public String getDireccionAval() {
		return direccionAval;
	}

	/**
	 * @param direccionAval
	 *            the direccionAval to set
	 */
	public void setDireccionAval(String direccionAval) {
		this.direccionAval = direccionAval;
	}

	/**
	 * @return the telefonoCasaAval
	 */
	public String getTelefonoCasaAval() {
		return telefonoCasaAval;
	}

	/**
	 * @param telefonoCasaAval
	 *            the telefonoCasaAval to set
	 */
	public void setTelefonoCasaAval(String telefonoCasaAval) {
		this.telefonoCasaAval = telefonoCasaAval;
	}

	/**
	 * @return the telefonoCelAval
	 */
	public String getTelefonoCelAval() {
		return telefonoCelAval;
	}

	/**
	 * @param telefonoCelAval
	 *            the telefonoCelAval to set
	 */
	public void setTelefonoCelAval(String telefonoCelAval) {
		this.telefonoCelAval = telefonoCelAval;
	}

	/**
	 * @return the codigoTipoVinculoAval
	 */
	public String getCodigoTipoVinculoAval() {
		return codigoTipoVinculoAval;
	}

	/**
	 * @param codigoTipoVinculoAval
	 *            the codigoTipoVinculoAval to set
	 */
	public void setCodigoTipoVinculoAval(String codigoTipoVinculoAval) {
		this.codigoTipoVinculoAval = codigoTipoVinculoAval;
	}

	/**
	 * @return the codigoDepartamentoAval
	 */
	public String getCodigoDepartamentoAval() {
		return codigoDepartamentoAval;
	}

	/**
	 * @param codigoDepartamentoAval
	 *            the codigoDepartamentoAval to set
	 */
	public void setCodigoDepartamentoAval(String codigoDepartamentoAval) {
		this.codigoDepartamentoAval = codigoDepartamentoAval;
	}

	/**
	 * @return the codigoProvinciaAval
	 */
	public String getCodigoProvinciaAval() {
		return codigoProvinciaAval;
	}

	/**
	 * @param codigoProvinciaAval
	 *            the codigoProvinciaAval to set
	 */
	public void setCodigoProvinciaAval(String codigoProvinciaAval) {
		this.codigoProvinciaAval = codigoProvinciaAval;
	}

	/**
	 * @return the codigoDistritoAval
	 */
	public String getCodigoDistritoAval() {
		return codigoDistritoAval;
	}

	/**
	 * @param codigoDistritoAval
	 *            the codigoDistritoAval to set
	 */
	public void setCodigoDistritoAval(String codigoDistritoAval) {
		this.codigoDistritoAval = codigoDistritoAval;
	}

	/**
	 * @return the oidTipoDocumentoAval
	 */
	public String getOidTipoDocumentoAval() {
		return oidTipoDocumentoAval;
	}

	/**
	 * @param oidTipoDocumentoAval
	 *            the oidTipoDocumentoAval to set
	 */
	public void setOidTipoDocumentoAval(String oidTipoDocumentoAval) {
		this.oidTipoDocumentoAval = oidTipoDocumentoAval;
	}

	/**
	 * @return the numeroDocumentoAval
	 */
	public String getNumeroDocumentoAval() {
		return numeroDocumentoAval;
	}

	/**
	 * @param numeroDocumentoAval
	 *            the numeroDocumentoAval to set
	 */
	public void setNumeroDocumentoAval(String numeroDocumentoAval) {
		this.numeroDocumentoAval = numeroDocumentoAval;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais
	 *            the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the oidCliente
	 */
	public String getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente
	 *            the oidCliente to set
	 */
	public void setOidCliente(String oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 *            the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the digitoControl
	 */
	public String getDigitoControl() {
		return digitoControl;
	}

	/**
	 * @param digitoControl
	 *            the digitoControl to set
	 */
	public void setDigitoControl(String digitoControl) {
		this.digitoControl = digitoControl;
	}

	/**
	 * @return the fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso
	 *            the fechaIngreso to set
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo
	 *            the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus
	 *            the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return Returns the apellidoPaterno.
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno
	 *            The apellidoPaterno to set.
	 * @struts.validator type="required"
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return Returns the apellidoMaterno.
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno
	 *            The apellidoMaterno to set.
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return Returns the apellidoCasada.
	 */
	public String getApellidoCasada() {
		return apellidoCasada;
	}

	/**
	 * @param apellidoCasada
	 *            The apellidoCasada to set.
	 */
	public void setApellidoCasada(String apellidoCasada) {
		this.apellidoCasada = apellidoCasada;
	}

	/**
	 * @return Returns the nombre1.
	 */
	public String getNombre1() {
		return nombre1;
	}

	/**
	 * @param nombre1
	 *            The nombre1 to set.
	 * @struts.validator type="required"
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	/**
	 * @return Returns the nombre2.
	 */
	public String getNombre2() {
		return nombre2;
	}

	/**
	 * @param nombre2
	 *            The nombre2 to set.
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	/**
	 * @return Returns the sexo.
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            The sexo to set.
	 * @struts.validator type="required"
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return Returns the tratamiento.
	 */
	public String getTratamiento() {
		return tratamiento;
	}

	/**
	 * @param tratamiento
	 *            The tratamiento to set.
	 * @struts.validator type="required"
	 */
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	/**
	 * @return Returns the indicadoObligatorioTratamiento.
	 */
	public boolean isIndicadoObligatorioTratamiento() {
		return indicadoObligatorioTratamiento;
	}

	/**
	 * @param indicadoObligatorioTratamiento
	 *            The indicadoObligatorioTratamiento to set.
	 */
	public void setIndicadoObligatorioTratamiento(
			boolean indicadoObligatorioTratamiento) {
		this.indicadoObligatorioTratamiento = indicadoObligatorioTratamiento;
	}

	/**
	 * @return Returns the edad.
	 */
	public String getEdad() {
		return edad;
	}

	/**
	 * @param edad
	 *            The edad to set.
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}

	/**
	 * @return Returns the fechaNacimiento.
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento
	 *            The fechaNacimiento to set.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return Returns the estadoCivil.
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil
	 *            The estadoCivil to set.
	 * @struts.validator type="required"
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return Returns the gradoInstruccion.
	 */
	public String getGradoInstruccion() {
		return gradoInstruccion;
	}

	/**
	 * @param gradoInstruccion
	 *            The gradoInstruccion to set.
	 */
	public void setGradoInstruccion(String gradoInstruccion) {
		this.gradoInstruccion = gradoInstruccion;
	}

	/**
	 * @return Returns the indicadoObligatorioGradoInstruccion.
	 */
	public boolean isIndicadoObligatorioGradoInstruccion() {
		return indicadoObligatorioGradoInstruccion;
	}

	/**
	 * @param indicadoObligatorioGradoInstruccion
	 *            The indicadoObligatorioGradoInstruccion to set.
	 */
	public void setIndicadoObligatorioGradoInstruccion(
			boolean indicadoObligatorioGradoInstruccion) {
		this.indicadoObligatorioGradoInstruccion = indicadoObligatorioGradoInstruccion;
	}

	/**
	 * @return Returns the nacionalidad.
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad
	 *            The nacionalidad to set.
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @return Returns the indicadoObligatorioNacionalidad.
	 */
	public boolean isIndicadoObligatorioNacionalidad() {
		return indicadoObligatorioNacionalidad;
	}

	/**
	 * @param indicadoObligatorioNacionalidad
	 *            The indicadoObligatorioNacionalidad to set.
	 */
	public void setIndicadoObligatorioNacionalidad(
			boolean indicadoObligatorioNacionalidad) {
		this.indicadoObligatorioNacionalidad = indicadoObligatorioNacionalidad;
	}

	/**
	 * @return Returns the oidPeriodo.
	 */
	public String getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo
	 *            The oidPeriodo to set.
	 * @struts.validator type="required"
	 */
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}

	/**
	 * @return the oidPeriodoPrimerPedido
	 */
	public String getOidPeriodoPrimerPedido() {
		return oidPeriodoPrimerPedido;
	}

	/**
	 * @param oidPeriodoPrimerPedido
	 *            the oidPeriodoPrimerPedido to set
	 * @struts.validator type="required"
	 */
	public void setOidPeriodoPrimerPedido(String oidPeriodoPrimerPedido) {
		this.oidPeriodoPrimerPedido = oidPeriodoPrimerPedido;
	}

	/**
	 * @return the edadMinima
	 */
	public String getEdadMinima() {
		return edadMinima;
	}

	/**
	 * @param edadMinima
	 *            the edadMinima to set
	 */
	public void setEdadMinima(String edadMinima) {
		this.edadMinima = edadMinima;
	}

	/**
	 * @return the edadMaxima
	 */
	public String getEdadMaxima() {
		return edadMaxima;
	}

	/**
	 * @param edadMaxima
	 *            the edadMaxima to set
	 */
	public void setEdadMaxima(String edadMaxima) {
		this.edadMaxima = edadMaxima;
	}

	/**
	 * @return the mostrarTiposClientes
	 */
	public boolean isMostrarTiposClientes() {
		return mostrarTiposClientes;
	}

	/**
	 * @param mostrarTiposClientes
	 *            the mostrarTiposClientes to set
	 */
	public void setMostrarTiposClientes(boolean mostrarTiposClientes) {
		this.mostrarTiposClientes = mostrarTiposClientes;
	}

	/**
	 * @return the subTipoCliente
	 */
	public String getSubTipoCliente() {
		return subTipoCliente;
	}

	/**
	 * @param subTipoCliente
	 *            the subTipoCliente to set
	 */
	public void setSubTipoCliente(String subTipoCliente) {
		this.subTipoCliente = subTipoCliente;
	}

	/**
	 * @return the tipoClasificacion
	 */
	public String getTipoClasificacion() {
		return tipoClasificacion;
	}

	/**
	 * @param tipoClasificacion
	 *            the tipoClasificacion to set
	 */
	public void setTipoClasificacion(String tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}

	/**
	 * @return the clasificacion
	 */
	public String getClasificacion() {
		return Clasificacion;
	}

	/**
	 * @param clasificacion
	 *            the clasificacion to set
	 */
	public void setClasificacion(String clasificacion) {
		Clasificacion = clasificacion;
	}

	/**
	 * @return the descripcionNivel1
	 */
	public String getDescripcionNivel1() {
		return descripcionNivel1;
	}

	/**
	 * @param descripcionNivel1
	 *            the descripcionNivel1 to set
	 */
	public void setDescripcionNivel1(String descripcionNivel1) {
		this.descripcionNivel1 = descripcionNivel1;
	}

	/**
	 * @return the descripcionNivel2
	 */
	public String getDescripcionNivel2() {
		return descripcionNivel2;
	}

	/**
	 * @param descripcionNivel2
	 *            the descripcionNivel2 to set
	 */
	public void setDescripcionNivel2(String descripcionNivel2) {
		this.descripcionNivel2 = descripcionNivel2;
	}

	/**
	 * @return the descripcionNivel3
	 */
	public String getDescripcionNivel3() {
		return descripcionNivel3;
	}

	/**
	 * @param descripcionNivel3
	 *            the descripcionNivel3 to set
	 */
	public void setDescripcionNivel3(String descripcionNivel3) {
		this.descripcionNivel3 = descripcionNivel3;
	}

	/**
	 * @return the descripcionNivel4
	 */
	public String getDescripcionNivel4() {
		return descripcionNivel4;
	}

	/**
	 * @param descripcionNivel4
	 *            the descripcionNivel4 to set
	 */
	public void setDescripcionNivel4(String descripcionNivel4) {
		this.descripcionNivel4 = descripcionNivel4;
	}

	/**
	 * @return the descripcionNivel5
	 */
	public String getDescripcionNivel5() {
		return descripcionNivel5;
	}

	/**
	 * @param descripcionNivel5
	 *            the descripcionNivel5 to set
	 */
	public void setDescripcionNivel5(String descripcionNivel5) {
		this.descripcionNivel5 = descripcionNivel5;
	}

	/**
	 * @return the descripcionNivel6
	 */
	public String getDescripcionNivel6() {
		return descripcionNivel6;
	}

	/**
	 * @param descripcionNivel6
	 *            the descripcionNivel6 to set
	 */
	public void setDescripcionNivel6(String descripcionNivel6) {
		this.descripcionNivel6 = descripcionNivel6;
	}

	/**
	 * @return the totalNiveles
	 */
	public String getTotalNiveles() {
		return totalNiveles;
	}

	/**
	 * @param totalNiveles
	 *            the totalNiveles to set
	 */
	public void setTotalNiveles(String totalNiveles) {
		this.totalNiveles = totalNiveles;
	}

	/**
	 * @return the mostrarDireccion
	 */
	public boolean isMostrarDireccion() {
		return mostrarDireccion;
	}

	/**
	 * @param mostrarDireccion
	 *            the mostrarDireccion to set
	 */
	public void setMostrarDireccion(boolean mostrarDireccion) {
		this.mostrarDireccion = mostrarDireccion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 *            the codigoZona to set
	 * @struts.validator type="required"
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigoTerritorio
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	/**
	 * @param codigoTerritorio
	 *            the codigoTerritorio to set
	 * @struts.validator type="required"
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}

	/**
	 * @return the oidTerritorioAdministrativo
	 */
	public String getOidTerritorioAdministrativo() {
		return oidTerritorioAdministrativo;
	}

	/**
	 * @param oidTerritorioAdministrativo
	 *            the oidTerritorioAdministrativo to set
	 */
	public void setOidTerritorioAdministrativo(
			String oidTerritorioAdministrativo) {
		this.oidTerritorioAdministrativo = oidTerritorioAdministrativo;
	}

	/**
	 * @return the oidTerritorio
	 */
	public String getOidTerritorio() {
		return oidTerritorio;
	}

	/**
	 * @param oidTerritorio
	 *            the oidTerritorio to set
	 */
	public void setOidTerritorio(String oidTerritorio) {
		this.oidTerritorio = oidTerritorio;
	}

	/**
	 * @return the esDuplaCyzone
	 */
	public boolean isEsDuplaCyzone() {
		return esDuplaCyzone;
	}

	/**
	 * @param esDuplaCyzone
	 *            the esDuplaCyzone to set
	 */
	public void setEsDuplaCyzone(boolean esDuplaCyzone) {
		this.esDuplaCyzone = esDuplaCyzone;
	}

	/**
	 * @return the controlFoco
	 */
	public String getControlFoco() {
		return controlFoco;
	}

	/**
	 * @param controlFoco
	 *            the controlFoco to set
	 */
	public void setControlFoco(String controlFoco) {
		this.controlFoco = controlFoco;
	}

	/**
	 * @return the tipoVia
	 */
	public String getTipoVia() {
		return tipoVia;
	}

	/**
	 * @param tipoVia
	 *            the tipoVia to set
	 * @struts.validator type="required"
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	/**
	 * @return the numeroPrincipal
	 */
	public String getNumeroPrincipal() {
		return numeroPrincipal;
	}

	/**
	 * @param numeroPrincipal
	 *            the numeroPrincipal to set
	 */
	public void setNumeroPrincipal(String numeroPrincipal) {
		this.numeroPrincipal = numeroPrincipal;
	}

	/**
	 * @return the nombreVia
	 */
	public String getNombreVia() {
		return nombreVia;
	}

	/**
	 * @param nombreVia
	 *            the nombreVia to set
	 */
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	/**
	 * @return the observacionDireccion
	 */
	public String getObservacionDireccion() {
		return observacionDireccion;
	}

	/**
	 * @param observacionDireccion
	 *            the observacionDireccion to set
	 */
	public void setObservacionDireccion(String observacionDireccion) {
		this.observacionDireccion = observacionDireccion;
	}

	/**
	 * @return the nivel1
	 */
	public String getNivel1() {
		return nivel1;
	}

	/**
	 * @param nivel1
	 *            the nivel1 to set
	 * @struts.validator type="required"
	 */
	public void setNivel1(String nivel1) {
		this.nivel1 = nivel1;
	}

	/**
	 * @return the nivel2
	 */
	public String getNivel2() {
		return nivel2;
	}
	
	

	public boolean isValorIndicadorFactElectB() {
		return valorIndicadorFactElectB;
	}

	public void setValorIndicadorFactElectB(boolean valorIndicadorFactElectB) {
		this.valorIndicadorFactElectB = valorIndicadorFactElectB;
	}

	/**
	 * @param nivel2
	 *            the nivel2 to set
	 * @struts.validator type="required"
	 */
	public void setNivel2(String nivel2) {
		this.nivel2 = nivel2;
	}

	/**
	 * @return the nivel3
	 */
	public String getNivel3() {
		return nivel3;
	}

	/**
	 * @param nivel3
	 *            the nivel3 to set
	 * @struts.validator type="required"
	 */
	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}

	/**
	 * @return the nivel4
	 */
	public String getNivel4() {
		return nivel4;
	}

	/**
	 * @param nivel4
	 *            the nivel4 to set
	 */
	public void setNivel4(String nivel4) {
		this.nivel4 = nivel4;
	}

	/**
	 * @return the nivel5
	 */
	public String getNivel5() {
		return nivel5;
	}

	/**
	 * @param nivel5
	 *            the nivel5 to set
	 */
	public void setNivel5(String nivel5) {
		this.nivel5 = nivel5;
	}

	/**
	 * @return the nivel6
	 */
	public String getNivel6() {
		return nivel6;
	}

	/**
	 * @param nivel6
	 *            the nivel6 to set
	 */
	public void setNivel6(String nivel6) {
		this.nivel6 = nivel6;
	}

	/**
	 * @return the telefonoCasa
	 */
	public String getTelefonoCasa() {
		return telefonoCasa;
	}

	/**
	 * @param telefonoCasa
	 *            the telefonoCasa to set
	 */
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}

	/**
	 * @return the telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	/**
	 * @param telefonoCelular
	 *            the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the mostrarConsultoraVinculo
	 */
	public boolean isMostrarConsultoraVinculo() {
		return mostrarConsultoraVinculo;
	}

	/**
	 * @param mostrarConsultoraVinculo
	 *            the mostrarConsultoraVinculo to set
	 */
	public void setMostrarConsultoraVinculo(boolean mostrarConsultoraVinculo) {
		this.mostrarConsultoraVinculo = mostrarConsultoraVinculo;
	}

	/**
	 * @return the oidConsultoraVinculo
	 */
	public String getOidConsultoraVinculo() {
		return oidConsultoraVinculo;
	}

	/**
	 * @param oidConsultoraVinculo
	 *            the oidConsultoraVinculo to set
	 */
	public void setOidConsultoraVinculo(String oidConsultoraVinculo) {
		this.oidConsultoraVinculo = oidConsultoraVinculo;
	}

	/**
	 * @return the codigoConsultoraVinculo
	 */
	public String getCodigoConsultoraVinculo() {
		return codigoConsultoraVinculo;
	}

	/**
	 * @param codigoConsultoraVinculo
	 *            the codigoConsultoraVinculo to set
	 */
	public void setCodigoConsultoraVinculo(String codigoConsultoraVinculo) {
		this.codigoConsultoraVinculo = codigoConsultoraVinculo;
	}

	/**
	 * @return the nombreConsultoraVinculo
	 */
	public String getNombreConsultoraVinculo() {
		return nombreConsultoraVinculo;
	}

	/**
	 * @param nombreConsultoraVinculo
	 *            the nombreConsultoraVinculo to set
	 */
	public void setNombreConsultoraVinculo(String nombreConsultoraVinculo) {
		this.nombreConsultoraVinculo = nombreConsultoraVinculo;
	}

	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return the tipoDocumentoIdentidad
	 */
	public String getTipoDocumentoIdentidad() {
		return tipoDocumentoIdentidad;
	}

	/**
	 * @param tipoDocumentoIdentidad
	 *            the tipoDocumentoIdentidad to set
	 */
	public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
		this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
	}

	/**
	 * @return the numeroDocumentoIdentidad
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}

	/**
	 * @param numeroDocumentoIdentidad
	 *            the numeroDocumentoIdentidad to set
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}

	/**
	 * @return the tipoDocumentoIdentidad2
	 */
	public String getTipoDocumentoIdentidad2() {
		return tipoDocumentoIdentidad2;
	}

	/**
	 * @param tipoDocumentoIdentidad2
	 *            the tipoDocumentoIdentidad2 to set
	 */
	public void setTipoDocumentoIdentidad2(String tipoDocumentoIdentidad2) {
		this.tipoDocumentoIdentidad2 = tipoDocumentoIdentidad2;
	}

	/**
	 * @return the numeroDocumentoIdentidad2
	 */
	public String getNumeroDocumentoIdentidad2() {
		return numeroDocumentoIdentidad2;
	}

	/**
	 * @param numeroDocumentoIdentidad2
	 *            the numeroDocumentoIdentidad2 to set
	 */
	public void setNumeroDocumentoIdentidad2(String numeroDocumentoIdentidad2) {
		this.numeroDocumentoIdentidad2 = numeroDocumentoIdentidad2;
	}

	/**
	 * @return the tipoDocumentoIdentidad3
	 */
	public String getTipoDocumentoIdentidad3() {
		return tipoDocumentoIdentidad3;
	}

	/**
	 * @param tipoDocumentoIdentidad3
	 *            the tipoDocumentoIdentidad3 to set
	 */
	public void setTipoDocumentoIdentidad3(String tipoDocumentoIdentidad3) {
		this.tipoDocumentoIdentidad3 = tipoDocumentoIdentidad3;
	}

	/**
	 * @return the numeroDocumentoIdentidad3
	 */
	public String getNumeroDocumentoIdentidad3() {
		return numeroDocumentoIdentidad3;
	}

	/**
	 * @param numeroDocumentoIdentidad3
	 *            the numeroDocumentoIdentidad3 to set
	 */
	public void setNumeroDocumentoIdentidad3(String numeroDocumentoIdentidad3) {
		this.numeroDocumentoIdentidad3 = numeroDocumentoIdentidad3;
	}

	/**
	 * @return the mostrarNumeroIdentidad
	 */
	public boolean isMostrarNumeroIdentidad() {
		return mostrarNumeroIdentidad;
	}

	/**
	 * @param mostrarNumeroIdentidad
	 *            the mostrarNumeroIdentidad to set
	 */
	public void setMostrarNumeroIdentidad(boolean mostrarNumeroIdentidad) {
		this.mostrarNumeroIdentidad = mostrarNumeroIdentidad;
	}

	/**
	 * @return the longitudTipoDocumento
	 */
	public String getLongitudTipoDocumento() {
		return longitudTipoDocumento;
	}

	/**
	 * @param longitudTipoDocumento
	 *            the longitudTipoDocumento to set
	 */
	public void setLongitudTipoDocumento(String longitudTipoDocumento) {
		this.longitudTipoDocumento = longitudTipoDocumento;
	}

	/**
	 * @return the longitudTipoDocumento2
	 */
	public String getLongitudTipoDocumento2() {
		return longitudTipoDocumento2;
	}

	/**
	 * @param longitudTipoDocumento2
	 *            the longitudTipoDocumento2 to set
	 */
	public void setLongitudTipoDocumento2(String longitudTipoDocumento2) {
		this.longitudTipoDocumento2 = longitudTipoDocumento2;
	}

	/**
	 * @return the longitudTipoDocumento3
	 */
	public String getLongitudTipoDocumento3() {
		return longitudTipoDocumento3;
	}

	/**
	 * @param longitudTipoDocumento3
	 *            the longitudTipoDocumento3 to set
	 */
	public void setLongitudTipoDocumento3(String longitudTipoDocumento3) {
		this.longitudTipoDocumento3 = longitudTipoDocumento3;
	}

	/**
	 * @return the longitudCodigoCliente
	 */
	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}

	/**
	 * @param longitudCodigoCliente
	 *            the longitudCodigoCliente to set
	 */
	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}

	/**
	 * @return the esCodigoClienteAutomatico
	 */
	public boolean isEsCodigoClienteAutomatico() {
		return esCodigoClienteAutomatico;
	}

	/**
	 * @param esCodigoClienteAutomatico
	 *            the esCodigoClienteAutomatico to set
	 */
	public void setEsCodigoClienteAutomatico(boolean esCodigoClienteAutomatico) {
		this.esCodigoClienteAutomatico = esCodigoClienteAutomatico;
	}

	/**
	 * @return the indicadorTiposCliente
	 */
	public String getIndicadorTiposCliente() {
		return indicadorTiposCliente;
	}

	/**
	 * @param indicadorTiposCliente
	 *            the indicadorTiposCliente to set
	 */
	public void setIndicadorTiposCliente(String indicadorTiposCliente) {
		this.indicadorTiposCliente = indicadorTiposCliente;
	}

	/**
	 * @return the indicadorDireccionDomicilio
	 */
	public String getIndicadorDireccionDomicilio() {
		return indicadorDireccionDomicilio;
	}

	/**
	 * @param indicadorDireccionDomicilio
	 *            the indicadorDireccionDomicilio to set
	 */
	public void setIndicadorDireccionDomicilio(
			String indicadorDireccionDomicilio) {
		this.indicadorDireccionDomicilio = indicadorDireccionDomicilio;
	}

	/**
	 * @return the indicadorDireccionOpcional
	 */
	public String getIndicadorDireccionOpcional() {
		return indicadorDireccionOpcional;
	}

	/**
	 * @param indicadorDireccionOpcional
	 *            the indicadorDireccionOpcional to set
	 */
	public void setIndicadorDireccionOpcional(String indicadorDireccionOpcional) {
		this.indicadorDireccionOpcional = indicadorDireccionOpcional;
	}

	/**
	 * @return the indicadorConsultoraVinculo
	 */
	public String getIndicadorConsultoraVinculo() {
		return indicadorConsultoraVinculo;
	}

	/**
	 * @param indicadorConsultoraVinculo
	 *            the indicadorConsultoraVinculo to set
	 */
	public void setIndicadorConsultoraVinculo(String indicadorConsultoraVinculo) {
		this.indicadorConsultoraVinculo = indicadorConsultoraVinculo;
	}

	/**
	 * @return the telefonoTrabajo
	 */
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}

	/**
	 * @param telefonoTrabajo
	 *            the telefonoTrabajo to set
	 */
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	/**
	 * @return the mostrarUnidadAdministrativa
	 */
	public boolean isMostrarUnidadAdministrativa() {
		return mostrarUnidadAdministrativa;
	}

	/**
	 * @param mostrarUnidadAdministrativa
	 *            the mostrarUnidadAdministrativa to set
	 */
	public void setMostrarUnidadAdministrativa(
			boolean mostrarUnidadAdministrativa) {
		this.mostrarUnidadAdministrativa = mostrarUnidadAdministrativa;
	}

	/**
	 * @return the tipoDireccionCT
	 */
	public String getTipoDireccionCT() {
		return tipoDireccionCT;
	}

	/**
	 * @param tipoDireccionCT
	 *            the tipoDireccionCT to set
	 */
	public void setTipoDireccionCT(String tipoDireccionCT) {
		this.tipoDireccionCT = tipoDireccionCT;
	}

	/**
	 * @return the tipoViaCT
	 */
	public String getTipoViaCT() {
		return tipoViaCT;
	}

	/**
	 * @param tipoViaCT
	 *            the tipoViaCT to set
	 */
	public void setTipoViaCT(String tipoViaCT) {
		this.tipoViaCT = tipoViaCT;
	}

	/**
	 * @return the numeroPrincipalCT
	 */
	public String getNumeroPrincipalCT() {
		return numeroPrincipalCT;
	}

	/**
	 * @param numeroPrincipalCT
	 *            the numeroPrincipalCT to set
	 */
	public void setNumeroPrincipalCT(String numeroPrincipalCT) {
		this.numeroPrincipalCT = numeroPrincipalCT;
	}

	/**
	 * @return the nombreViaCT
	 */
	public String getNombreViaCT() {
		return nombreViaCT;
	}

	/**
	 * @param nombreViaCT
	 *            the nombreViaCT to set
	 */
	public void setNombreViaCT(String nombreViaCT) {
		this.nombreViaCT = nombreViaCT;
	}

	/**
	 * @return the observacionDireccionCT
	 */
	public String getObservacionDireccionCT() {
		return observacionDireccionCT;
	}

	/**
	 * @param observacionDireccionCT
	 *            the observacionDireccionCT to set
	 */
	public void setObservacionDireccionCT(String observacionDireccionCT) {
		this.observacionDireccionCT = observacionDireccionCT;
	}

	/**
	 * @return the nivel1CT
	 */
	public String getNivel1CT() {
		return nivel1CT;
	}

	/**
	 * @param nivel1CT
	 *            the nivel1CT to set
	 */
	public void setNivel1CT(String nivel1CT) {
		this.nivel1CT = nivel1CT;
	}

	/**
	 * @return the nivel2CT
	 */
	public String getNivel2CT() {
		return nivel2CT;
	}

	/**
	 * @param nivel2CT
	 *            the nivel2CT to set
	 */
	public void setNivel2CT(String nivel2CT) {
		this.nivel2CT = nivel2CT;
	}

	/**
	 * @return the nivel3CT
	 */
	public String getNivel3CT() {
		return nivel3CT;
	}

	/**
	 * @param nivel3CT
	 *            the nivel3CT to set
	 */
	public void setNivel3CT(String nivel3CT) {
		this.nivel3CT = nivel3CT;
	}

	/**
	 * @return the nivel4CT
	 */
	public String getNivel4CT() {
		return nivel4CT;
	}

	/**
	 * @param nivel4CT
	 *            the nivel4CT to set
	 */
	public void setNivel4CT(String nivel4CT) {
		this.nivel4CT = nivel4CT;
	}

	/**
	 * @return the nivel5CT
	 */
	public String getNivel5CT() {
		return nivel5CT;
	}

	/**
	 * @param nivel5CT
	 *            the nivel5CT to set
	 */
	public void setNivel5CT(String nivel5CT) {
		this.nivel5CT = nivel5CT;
	}

	/**
	 * @return the nivel6CT
	 */
	public String getNivel6CT() {
		return nivel6CT;
	}

	/**
	 * @param nivel6CT
	 *            the nivel6CT to set
	 */
	public void setNivel6CT(String nivel6CT) {
		this.nivel6CT = nivel6CT;
	}

	/**
	 * @return the actualizoOK
	 */
	public boolean isActualizoOK() {
		return actualizoOK;
	}

	/**
	 * @param actualizoOK
	 *            the actualizoOK to set
	 */
	public void setActualizoOK(boolean actualizoOK) {
		this.actualizoOK = actualizoOK;
	}

	/**
	 * @return the confirmacionTerritorio
	 */
	public String getConfirmacionTerritorio() {
		return confirmacionTerritorio;
	}

	/**
	 * @param confirmacionTerritorio
	 *            the confirmacionTerritorio to set
	 */
	public void setConfirmacionTerritorio(String confirmacionTerritorio) {
		this.confirmacionTerritorio = confirmacionTerritorio;
	}

	/**
	 * @return the mensajeConfirmacion
	 */
	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	/**
	 * @param mensajeConfirmacion
	 *            the mensajeConfirmacion to set
	 */
	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	/**
	 * @return the fechaActual
	 */
	public String getFechaActual() {
		return fechaActual;
	}

	
	
	public boolean isValorIndicadorFactElectAuxB() {
		return valorIndicadorFactElectAuxB;
	}

	public void setValorIndicadorFactElectAuxB(boolean valorIndicadorFactElectAuxB) {
		this.valorIndicadorFactElectAuxB = valorIndicadorFactElectAuxB;
	}

	/**
	 * @param fechaActual
	 *            the fechaActual to set
	 */
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 * @return the tieneDireccionPrincipal
	 */
	public boolean isTieneDireccionPrincipal() {
		return tieneDireccionPrincipal;
	}

	/**
	 * @param tieneDireccionPrincipal
	 *            the tieneDireccionPrincipal to set
	 */
	public void setTieneDireccionPrincipal(boolean tieneDireccionPrincipal) {
		this.tieneDireccionPrincipal = tieneDireccionPrincipal;
	}

	/**
	 * @return the tieneIdentificacionPrincipal
	 */
	public boolean isTieneIdentificacionPrincipal() {
		return tieneIdentificacionPrincipal;
	}

	/**
	 * @param tieneIdentificacionPrincipal
	 *            the tieneIdentificacionPrincipal to set
	 */
	public void setTieneIdentificacionPrincipal(
			boolean tieneIdentificacionPrincipal) {
		this.tieneIdentificacionPrincipal = tieneIdentificacionPrincipal;
	}

	/**
	 * @return the borradoDireccionPrincipal
	 */
	public boolean isBorradoDireccionPrincipal() {
		return borradoDireccionPrincipal;
	}

	/**
	 * @param borradoDireccionPrincipal
	 *            the borradoDireccionPrincipal to set
	 */
	public void setBorradoDireccionPrincipal(boolean borradoDireccionPrincipal) {
		this.borradoDireccionPrincipal = borradoDireccionPrincipal;
	}

	/**
	 * @return the borradoDireccionOpcional
	 */
	public boolean isBorradoDireccionOpcional() {
		return borradoDireccionOpcional;
	}

	/**
	 * @param borradoDireccionOpcional
	 *            the borradoDireccionOpcional to set
	 */
	public void setBorradoDireccionOpcional(boolean borradoDireccionOpcional) {
		this.borradoDireccionOpcional = borradoDireccionOpcional;
	}

	/**
	 * @return the borradoDocumentoIdentidad2
	 */
	public boolean isBorradoDocumentoIdentidad2() {
		return borradoDocumentoIdentidad2;
	}

	/**
	 * @param borradoDocumentoIdentidad2
	 *            the borradoDocumentoIdentidad2 to set
	 */
	public void setBorradoDocumentoIdentidad2(boolean borradoDocumentoIdentidad2) {
		this.borradoDocumentoIdentidad2 = borradoDocumentoIdentidad2;
	}

	/**
	 * @return the borradoDocumentoIdentidad3
	 */
	public boolean isBorradoDocumentoIdentidad3() {
		return borradoDocumentoIdentidad3;
	}

	/**
	 * @param borradoDocumentoIdentidad3
	 *            the borradoDocumentoIdentidad3 to set
	 */
	public void setBorradoDocumentoIdentidad3(boolean borradoDocumentoIdentidad3) {
		this.borradoDocumentoIdentidad3 = borradoDocumentoIdentidad3;
	}

	/**
	 * @return the tieneDocumentoIdentidad2
	 */
	public boolean isTieneDocumentoIdentidad2() {
		return tieneDocumentoIdentidad2;
	}

	/**
	 * @param tieneDocumentoIdentidad2
	 *            the tieneDocumentoIdentidad2 to set
	 */
	public void setTieneDocumentoIdentidad2(boolean tieneDocumentoIdentidad2) {
		this.tieneDocumentoIdentidad2 = tieneDocumentoIdentidad2;
	}

	/**
	 * @return the tieneDocumentoIdentidad3
	 */
	public boolean isTieneDocumentoIdentidad3() {
		return tieneDocumentoIdentidad3;
	}

	/**
	 * @param tieneDocumentoIdentidad3
	 *            the tieneDocumentoIdentidad3 to set
	 */
	public void setTieneDocumentoIdentidad3(boolean tieneDocumentoIdentidad3) {
		this.tieneDocumentoIdentidad3 = tieneDocumentoIdentidad3;
	}

	/**
	 * @return the tieneDireccionOpcional
	 */
	public boolean isTieneDireccionOpcional() {
		return tieneDireccionOpcional;
	}

	/**
	 * @param tieneDireccionOpcional
	 *            the tieneDireccionOpcional to set
	 */
	public void setTieneDireccionOpcional(boolean tieneDireccionOpcional) {
		this.tieneDireccionOpcional = tieneDireccionOpcional;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoEstatus
	 */
	public String getCodigoEstatus() {
		return codigoEstatus;
	}

	/**
	 * @param codigoEstatus
	 *            the codigoEstatus to set
	 */
	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}

	/**
	 * @return the esClienteCaducado
	 */
	public boolean isEsClienteCaducado() {
		return esClienteCaducado;
	}

	/**
	 * @param esClienteCaducado
	 *            the esClienteCaducado to set
	 */
	public void setEsClienteCaducado(boolean esClienteCaducado) {
		this.esClienteCaducado = esClienteCaducado;
	}

	/**
	 * @return the tieneTipologiaConsultora
	 */
	public boolean isTieneTipologiaConsultora() {
		return tieneTipologiaConsultora;
	}

	/**
	 * @param tieneTipologiaConsultora
	 *            the tieneTipologiaConsultora to set
	 */
	public void setTieneTipologiaConsultora(boolean tieneTipologiaConsultora) {
		this.tieneTipologiaConsultora = tieneTipologiaConsultora;
	}

	/**
	 * @return the modificarIdentificacionPrincipal
	 */
	public boolean isModificarIdentificacionPrincipal() {
		return modificarIdentificacionPrincipal;
	}

	/**
	 * @param modificarIdentificacionPrincipal
	 *            the modificarIdentificacionPrincipal to set
	 */
	public void setModificarIdentificacionPrincipal(
			boolean modificarIdentificacionPrincipal) {
		this.modificarIdentificacionPrincipal = modificarIdentificacionPrincipal;
	}

	/**
	 * @return the tienePedidoFacturado
	 */
	public boolean isTienePedidoFacturado() {
		return tienePedidoFacturado;
	}

	/**
	 * @param tienePedidoFacturado
	 *            the tienePedidoFacturado to set
	 */
	public void setTienePedidoFacturado(boolean tienePedidoFacturado) {
		this.tienePedidoFacturado = tienePedidoFacturado;
	}

	/**
	 * @return the tieneTipologiaConsultoraInicio
	 */
	public boolean isTieneTipologiaConsultoraInicio() {
		return tieneTipologiaConsultoraInicio;
	}

	/**
	 * @param tieneTipologiaConsultoraInicio
	 *            the tieneTipologiaConsultoraInicio to set
	 */
	public void setTieneTipologiaConsultoraInicio(
			boolean tieneTipologiaConsultoraInicio) {
		this.tieneTipologiaConsultoraInicio = tieneTipologiaConsultoraInicio;
	}

	/**
	 * @return the codigoConsultoraRecomendante
	 */
	public String getCodigoConsultoraRecomendante() {
		return codigoConsultoraRecomendante;
	}

	/**
	 * @param codigoConsultoraRecomendante
	 *            the codigoConsultoraRecomendante to set
	 */
	public void setCodigoConsultoraRecomendante(
			String codigoConsultoraRecomendante) {
		this.codigoConsultoraRecomendante = codigoConsultoraRecomendante;
	}

	/**
	 * @return the modificarConsultoraVinculo
	 */
	public boolean isModificarConsultoraVinculo() {
		return modificarConsultoraVinculo;
	}

	/**
	 * @param modificarConsultoraVinculo
	 *            the modificarConsultoraVinculo to set
	 */
	public void setModificarConsultoraVinculo(boolean modificarConsultoraVinculo) {
		this.modificarConsultoraVinculo = modificarConsultoraVinculo;
	}

	/**
	 * @return the oidPeriodoConcurso
	 */
	public String getOidPeriodoConcurso() {
		return oidPeriodoConcurso;
	}

	/**
	 * @param oidPeriodoConcurso
	 *            the oidPeriodoConcurso to set
	 */
	public void setOidPeriodoConcurso(String oidPeriodoConcurso) {
		this.oidPeriodoConcurso = oidPeriodoConcurso;
	}

	/**
	 * @return the primeraVezDespliegueDireccionEntrega
	 */
	public boolean isPrimeraVezDespliegueDireccionEntrega() {
		return primeraVezDespliegueDireccionEntrega;
	}

	/**
	 * @param primeraVezDespliegueDireccionEntrega
	 *            the primeraVezDespliegueDireccionEntrega to set
	 */
	public void setPrimeraVezDespliegueDireccionEntrega(
			boolean primeraVezDespliegueDireccionEntrega) {
		this.primeraVezDespliegueDireccionEntrega = primeraVezDespliegueDireccionEntrega;
	}

	/**
	 * @return the mostrarPantallaPremios
	 */
	public boolean isMostrarPantallaPremios() {
		return mostrarPantallaPremios;
	}

	/**
	 * @param mostrarPantallaPremios
	 *            the mostrarPantallaPremios to set
	 */
	public void setMostrarPantallaPremios(boolean mostrarPantallaPremios) {
		this.mostrarPantallaPremios = mostrarPantallaPremios;
	}

	/**
	 * @return the mostrarDigitoControl
	 */
	public boolean isMostrarDigitoControl() {
		return mostrarDigitoControl;
	}

	/**
	 * @param mostrarDigitoControl
	 *            the mostrarDigitoControl to set
	 */
	public void setMostrarDigitoControl(boolean mostrarDigitoControl) {
		this.mostrarDigitoControl = mostrarDigitoControl;
	}

	public void reset() {
		// Referencia Familiar
		this.apellido1RefFamiliar = "";
		this.apellido2RefFamiliar = "";
		this.nombre1RefFamiliar = "";
		this.nombre2RefFamiliar = "";
		this.direccionRefFamiliar = "";
		this.telefonoCasaRefFamiliar = "";
		this.telefonoCelRefFamiliar = "";
		this.codigoTipoVinculoRefFamiliar = "";
		// Referencia No Familiar
		this.apellido1RefNoFamiliar = "";
		this.apellido2RefNoFamiliar = "";
		this.nombre1RefNoFamiliar = "";
		this.nombre2RefNoFamiliar = "";
		this.direccionRefNoFamiliar = "";
		this.telefonoCasaRefNoFamiliar = "";
		this.telefonoCelRefNoFamiliar = "";
		this.codigoTipoVinculoRefNoFamiliar = "";
		// Referencia A Aval
		this.apellido1Aval = "";
		this.apellido2Aval = "";
		this.nombre1Aval = "";
		this.nombre2Aval = "";
		this.estado = "";
		this.municipio = "";
		this.parroquia = "";
		this.direccionAval = "";
		this.telefonoCasaAval = "";
		this.telefonoCelAval = "";
		this.codigoTipoVinculoAval = "";
		this.codigoDepartamentoAval = "";
		this.codigoProvinciaAval = "";
		this.codigoDistritoAval = "";
		this.oidTipoDocumentoAval = "";
		this.numeroDocumentoAval = "";
		this.longitudTipoDocumentoAval = "";
		this.chkReferencias = Constants.NUMERO_CERO;

		/* INI SA COS-SiCC-2013-0031 */
		this.indicadorImpresionPaqDoc = Constants.NO;
		/* FIN SA COS-SiCC-2013-0031 */
		
		this.valorIndicadorFactElect = Constants.UNO;
		this.valorIndicadorFactElectAux = Constants.UNO;
		this.indicadorDocFiscal = Constants.IND_DOC_FISC_NO;
	}

	/**
	 * @return the telefonoCasaDireccionEntrega
	 */
	public String getTelefonoCasaDireccionEntrega() {
		return telefonoCasaDireccionEntrega;
	}

	/**
	 * @param telefonoCasaDireccionEntrega
	 *            the telefonoCasaDireccionEntrega to set
	 */
	public void setTelefonoCasaDireccionEntrega(
			String telefonoCasaDireccionEntrega) {
		this.telefonoCasaDireccionEntrega = telefonoCasaDireccionEntrega;
	}

	/**
	 * @return the telefonoCelularDireccionEntrega
	 */
	public String getTelefonoCelularDireccionEntrega() {
		return telefonoCelularDireccionEntrega;
	}

	/**
	 * @param telefonoCelularDireccionEntrega
	 *            the telefonoCelularDireccionEntrega to set
	 */
	public void setTelefonoCelularDireccionEntrega(
			String telefonoCelularDireccionEntrega) {
		this.telefonoCelularDireccionEntrega = telefonoCelularDireccionEntrega;
	}

	/**
	 * @return the cambioZonaTerritorio
	 */
	public boolean isCambioZonaTerritorio() {
		return cambioZonaTerritorio;
	}

	/**
	 * @param cambioZonaTerritorio
	 *            the cambioZonaTerritorio to set
	 */
	public void setCambioZonaTerritorio(boolean cambioZonaTerritorio) {
		this.cambioZonaTerritorio = cambioZonaTerritorio;
	}

	/**
	 * @return the codigoZonaInicial
	 */
	public String getCodigoZonaInicial() {
		return codigoZonaInicial;
	}

	/**
	 * @param codigoZonaInicial
	 *            the codigoZonaInicial to set
	 */
	public void setCodigoZonaInicial(String codigoZonaInicial) {
		this.codigoZonaInicial = codigoZonaInicial;
	}

	/**
	 * @return the codigoTerritorioInicial
	 */
	public String getCodigoTerritorioInicial() {
		return codigoTerritorioInicial;
	}

	/**
	 * @param codigoTerritorioInicial
	 *            the codigoTerritorioInicial to set
	 */
	public void setCodigoTerritorioInicial(String codigoTerritorioInicial) {
		this.codigoTerritorioInicial = codigoTerritorioInicial;
	}

	/**
	 * @return the codNivel1
	 */
	public String getCodNivel1() {
		return codNivel1;
	}

	/**
	 * @param codNivel1
	 *            the codNivel1 to set
	 */
	public void setCodNivel1(String codNivel1) {
		this.codNivel1 = codNivel1;
	}

	/**
	 * @return the codNivel2
	 */
	public String getCodNivel2() {
		return codNivel2;
	}

	/**
	 * @param codNivel2
	 *            the codNivel2 to set
	 */
	public void setCodNivel2(String codNivel2) {
		this.codNivel2 = codNivel2;
	}

	/**
	 * @return the codNivel3
	 */
	public String getCodNivel3() {
		return codNivel3;
	}

	/**
	 * @param codNivel3
	 *            the codNivel3 to set
	 */
	public void setCodNivel3(String codNivel3) {
		this.codNivel3 = codNivel3;
	}

	/**
	 * @return the codNivel4
	 */
	public String getCodNivel4() {
		return codNivel4;
	}

	/**
	 * @param codNivel4
	 *            the codNivel4 to set
	 */
	public void setCodNivel4(String codNivel4) {
		this.codNivel4 = codNivel4;
	}

	/**
	 * @return the codNivel5
	 */
	public String getCodNivel5() {
		return codNivel5;
	}

	/**
	 * @param codNivel5
	 *            the codNivel5 to set
	 */
	public void setCodNivel5(String codNivel5) {
		this.codNivel5 = codNivel5;
	}

	/**
	 * @return the codNivel6
	 */
	public String getCodNivel6() {
		return codNivel6;
	}

	/**
	 * @param codNivel6
	 *            the codNivel6 to set
	 */
	public void setCodNivel6(String codNivel6) {
		this.codNivel6 = codNivel6;
	}

	/**
	 * @return the longitudCodigoZona
	 */
	public String getLongitudCodigoZona() {
		return longitudCodigoZona;
	}

	/**
	 * @param longitudCodigoZona
	 *            the longitudCodigoZona to set
	 */
	public void setLongitudCodigoZona(String longitudCodigoZona) {
		this.longitudCodigoZona = longitudCodigoZona;
	}

	/**
	 * @return the mostrarTipoVia
	 */
	public boolean isMostrarTipoVia() {
		return mostrarTipoVia;
	}

	/**
	 * @param mostrarTipoVia
	 *            the mostrarTipoVia to set
	 */
	public void setMostrarTipoVia(boolean mostrarTipoVia) {
		this.mostrarTipoVia = mostrarTipoVia;
	}

	/**
	 * @return the mostrarNumeroPrincipal
	 */
	public boolean isMostrarNumeroPrincipal() {
		return mostrarNumeroPrincipal;
	}

	/**
	 * @param mostrarNumeroPrincipal
	 *            the mostrarNumeroPrincipal to set
	 */
	public void setMostrarNumeroPrincipal(boolean mostrarNumeroPrincipal) {
		this.mostrarNumeroPrincipal = mostrarNumeroPrincipal;
	}

	/**
	 * @return the permitirModificarUbigeo
	 */
	public boolean isPermitirModificarUbigeo() {
		return permitirModificarUbigeo;
	}

	/**
	 * @param permitirModificarUbigeo
	 *            the permitirModificarUbigeo to set
	 */
	public void setPermitirModificarUbigeo(boolean permitirModificarUbigeo) {
		this.permitirModificarUbigeo = permitirModificarUbigeo;
	}

	/**
	 * @return the mostrarUbigeoEntrega
	 */
	public boolean isMostrarUbigeoEntrega() {
		return mostrarUbigeoEntrega;
	}

	/**
	 * @param mostrarUbigeoEntrega
	 *            the mostrarUbigeoEntrega to set
	 */
	public void setMostrarUbigeoEntrega(boolean mostrarUbigeoEntrega) {
		this.mostrarUbigeoEntrega = mostrarUbigeoEntrega;
	}

	/**
	 * @return the primeraVezDespliegueReferencias
	 */
	public boolean isPrimeraVezDespliegueReferencias() {
		return primeraVezDespliegueReferencias;
	}

	/**
	 * @param primeraVezDespliegueReferencias
	 *            the primeraVezDespliegueReferencias to set
	 */
	public void setPrimeraVezDespliegueReferencias(
			boolean primeraVezDespliegueReferencias) {
		this.primeraVezDespliegueReferencias = primeraVezDespliegueReferencias;
	}

	/**
	 * @return the tienePedidoFacturadoVigente
	 */
	public boolean isTienePedidoFacturadoVigente() {
		return tienePedidoFacturadoVigente;
	}

	/**
	 * @param tienePedidoFacturadoVigente
	 *            the tienePedidoFacturadoVigente to set
	 */
	public void setTienePedidoFacturadoVigente(
			boolean tienePedidoFacturadoVigente) {
		this.tienePedidoFacturadoVigente = tienePedidoFacturadoVigente;
	}

	/**
	 * @return the borrarConcursosPremios
	 */
	public boolean isBorrarConcursosPremios() {
		return borrarConcursosPremios;
	}

	/**
	 * @param borrarConcursosPremios
	 *            the borrarConcursosPremios to set
	 */
	public void setBorrarConcursosPremios(boolean borrarConcursosPremios) {
		this.borrarConcursosPremios = borrarConcursosPremios;
	}

	/**
	 * @return the permitirModificarPeriodoIngreso
	 */
	public boolean isPermitirModificarPeriodoIngreso() {
		return permitirModificarPeriodoIngreso;
	}

	/**
	 * @param permitirModificarPeriodoIngreso
	 *            the permitirModificarPeriodoIngreso to set
	 */
	public void setPermitirModificarPeriodoIngreso(
			boolean permitirModificarPeriodoIngreso) {
		this.permitirModificarPeriodoIngreso = permitirModificarPeriodoIngreso;
	}

	/**
	 * @return the permitirModificarPeriodoVigente
	 */
	public boolean isPermitirModificarPeriodoVigente() {
		return permitirModificarPeriodoVigente;
	}

	/**
	 * @param permitirModificarPeriodoVigente
	 *            the permitirModificarPeriodoVigente to set
	 */
	public void setPermitirModificarPeriodoVigente(
			boolean permitirModificarPeriodoVigente) {
		this.permitirModificarPeriodoVigente = permitirModificarPeriodoVigente;
	}

	/**
	 * @return the mostrarMensajeCambioPeriodoVigente
	 */
	public boolean isMostrarMensajeCambioPeriodoVigente() {
		return mostrarMensajeCambioPeriodoVigente;
	}

	/**
	 * @param mostrarMensajeCambioPeriodoVigente
	 *            the mostrarMensajeCambioPeriodoVigente to set
	 */
	public void setMostrarMensajeCambioPeriodoVigente(
			boolean mostrarMensajeCambioPeriodoVigente) {
		this.mostrarMensajeCambioPeriodoVigente = mostrarMensajeCambioPeriodoVigente;
	}

	/**
	 * @return the mostrarMensajePeriodoFinCerrado
	 */
	public boolean isMostrarMensajePeriodoFinCerrado() {
		return mostrarMensajePeriodoFinCerrado;
	}

	/**
	 * @param mostrarMensajePeriodoFinCerrado
	 *            the mostrarMensajePeriodoFinCerrado to set
	 */
	public void setMostrarMensajePeriodoFinCerrado(
			boolean mostrarMensajePeriodoFinCerrado) {
		this.mostrarMensajePeriodoFinCerrado = mostrarMensajePeriodoFinCerrado;
	}

	/**
	 * @return the codigoEmpleado
	 */
	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	/**
	 * @param codigoEmpleado
	 *            the codigoEmpleado to set
	 */
	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	/**
	 * @return the mostrarCodigoEmpleado
	 */
	public boolean isMostrarCodigoEmpleado() {
		return mostrarCodigoEmpleado;
	}

	/**
	 * @param mostrarCodigoEmpleado
	 *            the mostrarCodigoEmpleado to set
	 */
	public void setMostrarCodigoEmpleado(boolean mostrarCodigoEmpleado) {
		this.mostrarCodigoEmpleado = mostrarCodigoEmpleado;
	}

	/**
	 * @return the mostrarMensajePedidoExtemporaneo
	 */
	public boolean isMostrarMensajePedidoExtemporaneo() {
		return mostrarMensajePedidoExtemporaneo;
	}

	/**
	 * @param mostrarMensajePedidoExtemporaneo
	 *            the mostrarMensajePedidoExtemporaneo to set
	 */
	public void setMostrarMensajePedidoExtemporaneo(
			boolean mostrarMensajePedidoExtemporaneo) {
		this.mostrarMensajePedidoExtemporaneo = mostrarMensajePedidoExtemporaneo;
	}

	/**
	 * @return the consultoraPasoPedido
	 */
	public boolean isConsultoraPasoPedido() {
		return consultoraPasoPedido;
	}

	/**
	 * @param consultoraPasoPedido
	 *            the consultoraPasoPedido to set
	 */
	public void setConsultoraPasoPedido(boolean consultoraPasoPedido) {
		this.consultoraPasoPedido = consultoraPasoPedido;
	}

	/**
	 * @return the mostrarRedefinirPeriodo
	 */
	public boolean isMostrarRedefinirPeriodo() {
		return mostrarRedefinirPeriodo;
	}

	/**
	 * @param mostrarRedefinirPeriodo
	 *            the mostrarRedefinirPeriodo to set
	 */
	public void setMostrarRedefinirPeriodo(boolean mostrarRedefinirPeriodo) {
		this.mostrarRedefinirPeriodo = mostrarRedefinirPeriodo;
	}

	/**
	 * @return the oidPeriodoRedefinir
	 */
	public String getOidPeriodoRedefinir() {
		return oidPeriodoRedefinir;
	}

	/**
	 * @param oidPeriodoRedefinir
	 *            the oidPeriodoRedefinir to set
	 */
	public void setOidPeriodoRedefinir(String oidPeriodoRedefinir) {
		this.oidPeriodoRedefinir = oidPeriodoRedefinir;
	}

	/**
	 * @return the esClientePotencialAval
	 */
	public boolean isEsClientePotencialAval() {
		return esClientePotencialAval;
	}

	/**
	 * @param esClientePotencialAval
	 *            the esClientePotencialAval to set
	 */
	public void setEsClientePotencialAval(boolean esClientePotencialAval) {
		this.esClientePotencialAval = esClientePotencialAval;
	}

	/**
	 * @return the aprobarAvaladas
	 */
	public boolean isAprobarAvaladas() {
		return aprobarAvaladas;
	}

	/**
	 * @param aprobarAvaladas
	 *            the aprobarAvaladas to set
	 */
	public void setAprobarAvaladas(boolean aprobarAvaladas) {
		this.aprobarAvaladas = aprobarAvaladas;
	}

	/**
	 * @return the permitirCompletarCerosIdentidad
	 */
	public boolean isPermitirCompletarCerosIdentidad() {
		return permitirCompletarCerosIdentidad;
	}

	/**
	 * @param permitirCompletarCerosIdentidad
	 *            the permitirCompletarCerosIdentidad to set
	 */
	public void setPermitirCompletarCerosIdentidad(
			boolean permitirCompletarCerosIdentidad) {
		this.permitirCompletarCerosIdentidad = permitirCompletarCerosIdentidad;
	}

	/**
	 * @return the cadenaCaracteresV1
	 */
	public String getCadenaCaracteresV1() {
		return cadenaCaracteresV1;
	}

	/**
	 * @param cadenaCaracteresV1
	 *            the cadenaCaracteresV1 to set
	 */
	public void setCadenaCaracteresV1(String cadenaCaracteresV1) {
		this.cadenaCaracteresV1 = cadenaCaracteresV1;
	}

	/**
	 * @return the cadenaCaracteresNV1
	 */
	public String getCadenaCaracteresNV1() {
		return cadenaCaracteresNV1;
	}

	/**
	 * @param cadenaCaracteresNV1
	 *            the cadenaCaracteresNV1 to set
	 */
	public void setCadenaCaracteresNV1(String cadenaCaracteresNV1) {
		this.cadenaCaracteresNV1 = cadenaCaracteresNV1;
	}

	/**
	 * @return the cadenaCaracteresV2
	 */
	public String getCadenaCaracteresV2() {
		return cadenaCaracteresV2;
	}

	/**
	 * @param cadenaCaracteresV2
	 *            the cadenaCaracteresV2 to set
	 */
	public void setCadenaCaracteresV2(String cadenaCaracteresV2) {
		this.cadenaCaracteresV2 = cadenaCaracteresV2;
	}

	/**
	 * @return the cadenaCaracteresNV2
	 */
	public String getCadenaCaracteresNV2() {
		return cadenaCaracteresNV2;
	}

	/**
	 * @param cadenaCaracteresNV2
	 *            the cadenaCaracteresNV2 to set
	 */
	public void setCadenaCaracteresNV2(String cadenaCaracteresNV2) {
		this.cadenaCaracteresNV2 = cadenaCaracteresNV2;
	}

	/**
	 * @return the cadenaCaracteresV3
	 */
	public String getCadenaCaracteresV3() {
		return cadenaCaracteresV3;
	}

	/**
	 * @param cadenaCaracteresV3
	 *            the cadenaCaracteresV3 to set
	 */
	public void setCadenaCaracteresV3(String cadenaCaracteresV3) {
		this.cadenaCaracteresV3 = cadenaCaracteresV3;
	}

	/**
	 * @return the cadenaCaracteresNV3
	 */
	public String getCadenaCaracteresNV3() {
		return cadenaCaracteresNV3;
	}

	/**
	 * @param cadenaCaracteresNV3
	 *            the cadenaCaracteresNV3 to set
	 */
	public void setCadenaCaracteresNV3(String cadenaCaracteresNV3) {
		this.cadenaCaracteresNV3 = cadenaCaracteresNV3;
	}

	/**
	 * @return the telefonoReferencia
	 */
	public String getTelefonoReferencia() {
		return telefonoReferencia;
	}

	/**
	 * @param telefonoReferencia
	 *            the telefonoReferencia to set
	 */
	public void setTelefonoReferencia(String telefonoReferencia) {
		this.telefonoReferencia = telefonoReferencia;
	}

	/**
	 * @return the codigoAnterior
	 */
	public String getCodigoAnterior() {
		return codigoAnterior;
	}

	/**
	 * @param codigoAnterior
	 *            the codigoAnterior to set
	 */
	public void setCodigoAnterior(String codigoAnterior) {
		this.codigoAnterior = codigoAnterior;
	}

	/**
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}

	/**
	 * @param barrio
	 *            the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	/**
	 * @return the barrioCT
	 */
	public String getBarrioCT() {
		return barrioCT;
	}

	/**
	 * @param barrioCT
	 *            the barrioCT to set
	 */
	public void setBarrioCT(String barrioCT) {
		this.barrioCT = barrioCT;
	}

	/**
	 * @return the barrioRefFamiliar
	 */
	public String getBarrioRefFamiliar() {
		return barrioRefFamiliar;
	}

	/**
	 * @param barrioRefFamiliar
	 *            the barrioRefFamiliar to set
	 */
	public void setBarrioRefFamiliar(String barrioRefFamiliar) {
		this.barrioRefFamiliar = barrioRefFamiliar;
	}

	/**
	 * @return the barrioRefNoFamiliar
	 */
	public String getBarrioRefNoFamiliar() {
		return barrioRefNoFamiliar;
	}

	/**
	 * @param barrioRefNoFamiliar
	 *            the barrioRefNoFamiliar to set
	 */
	public void setBarrioRefNoFamiliar(String barrioRefNoFamiliar) {
		this.barrioRefNoFamiliar = barrioRefNoFamiliar;
	}

	/**
	 * @return the barrioAval
	 */
	public String getBarrioAval() {
		return barrioAval;
	}

	/**
	 * @param barrioAval
	 *            the barrioAval to set
	 */
	public void setBarrioAval(String barrioAval) {
		this.barrioAval = barrioAval;
	}

	/**
	 * @return the mostrarBarrio
	 */
	public boolean isMostrarBarrio() {
		return mostrarBarrio;
	}

	/**
	 * @param mostrarBarrio
	 *            the mostrarBarrio to set
	 */
	public void setMostrarBarrio(boolean mostrarBarrio) {
		this.mostrarBarrio = mostrarBarrio;
	}

	/**
	 * @return the tipoCutis
	 */
	public String getTipoCutis() {
		return tipoCutis;
	}

	/**
	 * @param tipoCutis
	 *            the tipoCutis to set
	 */
	public void setTipoCutis(String tipoCutis) {
		this.tipoCutis = tipoCutis;
	}

	/**
	 * @return the validarCaracteres1
	 */
	public String getValidarCaracteres1() {
		return validarCaracteres1;
	}

	/**
	 * @param validarCaracteres1
	 *            the validarCaracteres1 to set
	 */
	public void setValidarCaracteres1(String validarCaracteres1) {
		this.validarCaracteres1 = validarCaracteres1;
	}

	/**
	 * @return the validarCaracteres2
	 */
	public String getValidarCaracteres2() {
		return validarCaracteres2;
	}

	/**
	 * @param validarCaracteres2
	 *            the validarCaracteres2 to set
	 */
	public void setValidarCaracteres2(String validarCaracteres2) {
		this.validarCaracteres2 = validarCaracteres2;
	}

	/**
	 * @return the validarCaracteres3
	 */
	public String getValidarCaracteres3() {
		return validarCaracteres3;
	}

	/**
	 * @param validarCaracteres3
	 *            the validarCaracteres3 to set
	 */
	public void setValidarCaracteres3(String validarCaracteres3) {
		this.validarCaracteres3 = validarCaracteres3;
	}

	/**
	 * @return the permitirComenzarCerosIdentidad
	 */
	public boolean isPermitirComenzarCerosIdentidad() {
		return permitirComenzarCerosIdentidad;
	}

	/**
	 * @param permitirComenzarCerosIdentidad
	 *            the permitirComenzarCerosIdentidad to set
	 */
	public void setPermitirComenzarCerosIdentidad(
			boolean permitirComenzarCerosIdentidad) {
		this.permitirComenzarCerosIdentidad = permitirComenzarCerosIdentidad;
	}

	/**
	 * @return the validarCaracteresIdentidad
	 */
	public String getValidarCaracteresIdentidad() {
		return validarCaracteresIdentidad;
	}

	/**
	 * @param validarCaracteresIdentidad
	 *            the validarCaracteresIdentidad to set
	 */
	public void setValidarCaracteresIdentidad(String validarCaracteresIdentidad) {
		this.validarCaracteresIdentidad = validarCaracteresIdentidad;
	}

	/**
	 * @return the cadenaCaracteresVIdentidad
	 */
	public String getCadenaCaracteresVIdentidad() {
		return cadenaCaracteresVIdentidad;
	}

	/**
	 * @param cadenaCaracteresVIdentidad
	 *            the cadenaCaracteresVIdentidad to set
	 */
	public void setCadenaCaracteresVIdentidad(String cadenaCaracteresVIdentidad) {
		this.cadenaCaracteresVIdentidad = cadenaCaracteresVIdentidad;
	}

	/**
	 * @return the cadenaCaracteresNVIdentidad
	 */
	public String getCadenaCaracteresNVIdentidad() {
		return cadenaCaracteresNVIdentidad;
	}

	/**
	 * @param cadenaCaracteresNVIdentidad
	 *            the cadenaCaracteresNVIdentidad to set
	 */
	public void setCadenaCaracteresNVIdentidad(
			String cadenaCaracteresNVIdentidad) {
		this.cadenaCaracteresNVIdentidad = cadenaCaracteresNVIdentidad;
	}

	/**
	 * @return the otrasMarcas
	 */
	public String getOtrasMarcas() {
		return otrasMarcas;
	}

	/**
	 * @param otrasMarcas
	 *            the otrasMarcas to set
	 */
	public void setOtrasMarcas(String otrasMarcas) {
		this.otrasMarcas = otrasMarcas;
	}

	/**
	 * @return the indDocumentoPrincipal
	 */
	public String getIndDocumentoPrincipal() {
		return indDocumentoPrincipal;
	}

	/**
	 * @param indDocumentoPrincipal
	 *            the indDocumentoPrincipal to set
	 */
	public void setIndDocumentoPrincipal(String indDocumentoPrincipal) {
		this.indDocumentoPrincipal = indDocumentoPrincipal;
	}

	/**
	 * @return the borradoDocumentoIdentidad1
	 */
	public boolean isBorradoDocumentoIdentidad1() {
		return borradoDocumentoIdentidad1;
	}

	/**
	 * @param borradoDocumentoIdentidad1
	 *            the borradoDocumentoIdentidad1 to set
	 */
	public void setBorradoDocumentoIdentidad1(boolean borradoDocumentoIdentidad1) {
		this.borradoDocumentoIdentidad1 = borradoDocumentoIdentidad1;
	}

	/**
	 * @return the codigoCiudad
	 */
	public String getCodigoCiudad() {
		return codigoCiudad;
	}

	/**
	 * @param codigoCiudad
	 *            the codigoCiudad to set
	 */
	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}

	/**
	 * @return the villaPoblacion
	 */
	public String getVillaPoblacion() {
		return villaPoblacion;
	}

	/**
	 * @param villaPoblacion
	 *            the villaPoblacion to set
	 */
	public void setVillaPoblacion(String villaPoblacion) {
		this.villaPoblacion = villaPoblacion;
	}

	/**
	 * @return the mostrarCiudad
	 */
	public boolean isMostrarCiudad() {
		return mostrarCiudad;
	}

	/**
	 * @param mostrarCiudad
	 *            the mostrarCiudad to set
	 */
	public void setMostrarCiudad(boolean mostrarCiudad) {
		this.mostrarCiudad = mostrarCiudad;
	}

	/**
	 * @return the mostrarVillaPoblacion
	 */
	public boolean isMostrarVillaPoblacion() {
		return mostrarVillaPoblacion;
	}

	/**
	 * @param mostrarVillaPoblacion
	 *            the mostrarVillaPoblacion to set
	 */
	public void setMostrarVillaPoblacion(boolean mostrarVillaPoblacion) {
		this.mostrarVillaPoblacion = mostrarVillaPoblacion;
	}

	/**
	 * @return the codigoCiudadCT
	 */
	public String getCodigoCiudadCT() {
		return codigoCiudadCT;
	}

	/**
	 * @param codigoCiudadCT
	 *            the codigoCiudadCT to set
	 */
	public void setCodigoCiudadCT(String codigoCiudadCT) {
		this.codigoCiudadCT = codigoCiudadCT;
	}

	/**
	 * @return the villaPoblacionCT
	 */
	public String getVillaPoblacionCT() {
		return villaPoblacionCT;
	}

	/**
	 * @param villaPoblacionCT
	 *            the villaPoblacionCT to set
	 */
	public void setVillaPoblacionCT(String villaPoblacionCT) {
		this.villaPoblacionCT = villaPoblacionCT;
	}

	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio
	 *            the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin
	 *            the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return the tipoViaVacaciones
	 */
	public String getTipoViaVacaciones() {
		return tipoViaVacaciones;
	}

	/**
	 * @param tipoViaVacaciones
	 *            the tipoViaVacaciones to set
	 */
	public void setTipoViaVacaciones(String tipoViaVacaciones) {
		this.tipoViaVacaciones = tipoViaVacaciones;
	}

	/**
	 * @return the numeroPrincipalVacaciones
	 */
	public String getNumeroPrincipalVacaciones() {
		return numeroPrincipalVacaciones;
	}

	/**
	 * @param numeroPrincipalVacaciones
	 *            the numeroPrincipalVacaciones to set
	 */
	public void setNumeroPrincipalVacaciones(String numeroPrincipalVacaciones) {
		this.numeroPrincipalVacaciones = numeroPrincipalVacaciones;
	}

	/**
	 * @return the nombreViaVacaciones
	 */
	public String getNombreViaVacaciones() {
		return nombreViaVacaciones;
	}

	/**
	 * @param nombreViaVacaciones
	 *            the nombreViaVacaciones to set
	 */
	public void setNombreViaVacaciones(String nombreViaVacaciones) {
		this.nombreViaVacaciones = nombreViaVacaciones;
	}

	/**
	 * @return the observacionDireccionVacaciones
	 */
	public String getObservacionDireccionVacaciones() {
		return observacionDireccionVacaciones;
	}

	/**
	 * @param observacionDireccionVacaciones
	 *            the observacionDireccionVacaciones to set
	 */
	public void setObservacionDireccionVacaciones(
			String observacionDireccionVacaciones) {
		this.observacionDireccionVacaciones = observacionDireccionVacaciones;
	}

	/**
	 * @return the barrioVacaciones
	 */
	public String getBarrioVacaciones() {
		return barrioVacaciones;
	}

	/**
	 * @param barrioVacaciones
	 *            the barrioVacaciones to set
	 */
	public void setBarrioVacaciones(String barrioVacaciones) {
		this.barrioVacaciones = barrioVacaciones;
	}

	/**
	 * @return the nivel1Vacaciones
	 */
	public String getNivel1Vacaciones() {
		return nivel1Vacaciones;
	}

	/**
	 * @param nivel1Vacaciones
	 *            the nivel1Vacaciones to set
	 */
	public void setNivel1Vacaciones(String nivel1Vacaciones) {
		this.nivel1Vacaciones = nivel1Vacaciones;
	}

	/**
	 * @return the nivel2Vacaciones
	 */
	public String getNivel2Vacaciones() {
		return nivel2Vacaciones;
	}

	/**
	 * @param nivel2Vacaciones
	 *            the nivel2Vacaciones to set
	 */
	public void setNivel2Vacaciones(String nivel2Vacaciones) {
		this.nivel2Vacaciones = nivel2Vacaciones;
	}

	/**
	 * @return the nivel3Vacaciones
	 */
	public String getNivel3Vacaciones() {
		return nivel3Vacaciones;
	}

	/**
	 * @param nivel3Vacaciones
	 *            the nivel3Vacaciones to set
	 */
	public void setNivel3Vacaciones(String nivel3Vacaciones) {
		this.nivel3Vacaciones = nivel3Vacaciones;
	}

	/**
	 * @return the nivel4Vacaciones
	 */
	public String getNivel4Vacaciones() {
		return nivel4Vacaciones;
	}

	/**
	 * @param nivel4Vacaciones
	 *            the nivel4Vacaciones to set
	 */
	public void setNivel4Vacaciones(String nivel4Vacaciones) {
		this.nivel4Vacaciones = nivel4Vacaciones;
	}

	/**
	 * @return the nivel5Vacaciones
	 */
	public String getNivel5Vacaciones() {
		return nivel5Vacaciones;
	}

	/**
	 * @param nivel5Vacaciones
	 *            the nivel5Vacaciones to set
	 */
	public void setNivel5Vacaciones(String nivel5Vacaciones) {
		this.nivel5Vacaciones = nivel5Vacaciones;
	}

	/**
	 * @return the nivel6Vacaciones
	 */
	public String getNivel6Vacaciones() {
		return nivel6Vacaciones;
	}

	/**
	 * @param nivel6Vacaciones
	 *            the nivel6Vacaciones to set
	 */
	public void setNivel6Vacaciones(String nivel6Vacaciones) {
		this.nivel6Vacaciones = nivel6Vacaciones;
	}

	/**
	 * @return the codNivel2Vacaciones
	 */
	public String getCodNivel2Vacaciones() {
		return codNivel2Vacaciones;
	}

	/**
	 * @param codNivel2Vacaciones
	 *            the codNivel2Vacaciones to set
	 */
	public void setCodNivel2Vacaciones(String codNivel2Vacaciones) {
		this.codNivel2Vacaciones = codNivel2Vacaciones;
	}

	/**
	 * @return the codNivel3Vacaciones
	 */
	public String getCodNivel3Vacaciones() {
		return codNivel3Vacaciones;
	}

	/**
	 * @param codNivel3Vacaciones
	 *            the codNivel3Vacaciones to set
	 */
	public void setCodNivel3Vacaciones(String codNivel3Vacaciones) {
		this.codNivel3Vacaciones = codNivel3Vacaciones;
	}

	/**
	 * @return the codNivel4Vacaciones
	 */
	public String getCodNivel4Vacaciones() {
		return codNivel4Vacaciones;
	}

	/**
	 * @param codNivel4Vacaciones
	 *            the codNivel4Vacaciones to set
	 */
	public void setCodNivel4Vacaciones(String codNivel4Vacaciones) {
		this.codNivel4Vacaciones = codNivel4Vacaciones;
	}

	/**
	 * @return the codNivel5Vacaciones
	 */
	public String getCodNivel5Vacaciones() {
		return codNivel5Vacaciones;
	}

	/**
	 * @param codNivel5Vacaciones
	 *            the codNivel5Vacaciones to set
	 */
	public void setCodNivel5Vacaciones(String codNivel5Vacaciones) {
		this.codNivel5Vacaciones = codNivel5Vacaciones;
	}

	/**
	 * @return the codNivel6Vacaciones
	 */
	public String getCodNivel6Vacaciones() {
		return codNivel6Vacaciones;
	}

	/**
	 * @param codNivel6Vacaciones
	 *            the codNivel6Vacaciones to set
	 */
	public void setCodNivel6Vacaciones(String codNivel6Vacaciones) {
		this.codNivel6Vacaciones = codNivel6Vacaciones;
	}

	/**
	 * @return the codigoCiudadVacaciones
	 */
	public String getCodigoCiudadVacaciones() {
		return codigoCiudadVacaciones;
	}

	/**
	 * @param codigoCiudadVacaciones
	 *            the codigoCiudadVacaciones to set
	 */
	public void setCodigoCiudadVacaciones(String codigoCiudadVacaciones) {
		this.codigoCiudadVacaciones = codigoCiudadVacaciones;
	}

	/**
	 * @return the villaPoblacionVacaciones
	 */
	public String getVillaPoblacionVacaciones() {
		return villaPoblacionVacaciones;
	}

	/**
	 * @param villaPoblacionVacaciones
	 *            the villaPoblacionVacaciones to set
	 */
	public void setVillaPoblacionVacaciones(String villaPoblacionVacaciones) {
		this.villaPoblacionVacaciones = villaPoblacionVacaciones;
	}

	/**
	 * @return the telefonoCasaDireccionVacaciones
	 */
	public String getTelefonoCasaDireccionVacaciones() {
		return telefonoCasaDireccionVacaciones;
	}

	/**
	 * @param telefonoCasaDireccionVacaciones
	 *            the telefonoCasaDireccionVacaciones to set
	 */
	public void setTelefonoCasaDireccionVacaciones(
			String telefonoCasaDireccionVacaciones) {
		this.telefonoCasaDireccionVacaciones = telefonoCasaDireccionVacaciones;
	}

	/**
	 * @return the telefonoCelularDireccionVacaciones
	 */
	public String getTelefonoCelularDireccionVacaciones() {
		return telefonoCelularDireccionVacaciones;
	}

	/**
	 * @param telefonoCelularDireccionVacaciones
	 *            the telefonoCelularDireccionVacaciones to set
	 */
	public void setTelefonoCelularDireccionVacaciones(
			String telefonoCelularDireccionVacaciones) {
		this.telefonoCelularDireccionVacaciones = telefonoCelularDireccionVacaciones;
	}

	/**
	 * @return the mostrarDireccionVacaciones
	 */
	public boolean isMostrarDireccionVacaciones() {
		return mostrarDireccionVacaciones;
	}

	/**
	 * @param mostrarDireccionVacaciones
	 *            the mostrarDireccionVacaciones to set
	 */
	public void setMostrarDireccionVacaciones(boolean mostrarDireccionVacaciones) {
		this.mostrarDireccionVacaciones = mostrarDireccionVacaciones;
	}

	/**
	 * @return the indicadorDireccionVacaciones
	 */
	public String getIndicadorDireccionVacaciones() {
		return indicadorDireccionVacaciones;
	}

	/**
	 * @param indicadorDireccionVacaciones
	 *            the indicadorDireccionVacaciones to set
	 */
	public void setIndicadorDireccionVacaciones(
			String indicadorDireccionVacaciones) {
		this.indicadorDireccionVacaciones = indicadorDireccionVacaciones;
	}

	/**
	 * @return the primeraVezDespliegueDireccionVacaciones
	 */
	public boolean isPrimeraVezDespliegueDireccionVacaciones() {
		return primeraVezDespliegueDireccionVacaciones;
	}

	/**
	 * @param primeraVezDespliegueDireccionVacaciones
	 *            the primeraVezDespliegueDireccionVacaciones to set
	 */
	public void setPrimeraVezDespliegueDireccionVacaciones(
			boolean primeraVezDespliegueDireccionVacaciones) {
		this.primeraVezDespliegueDireccionVacaciones = primeraVezDespliegueDireccionVacaciones;
	}

	/**
	 * @return the borradoDireccionVacaciones
	 */
	public boolean isBorradoDireccionVacaciones() {
		return borradoDireccionVacaciones;
	}

	/**
	 * @param borradoDireccionVacaciones
	 *            the borradoDireccionVacaciones to set
	 */
	public void setBorradoDireccionVacaciones(boolean borradoDireccionVacaciones) {
		this.borradoDireccionVacaciones = borradoDireccionVacaciones;
	}

	/**
	 * @return the tieneDireccionVacaciones
	 */
	public boolean isTieneDireccionVacaciones() {
		return tieneDireccionVacaciones;
	}

	/**
	 * @param tieneDireccionVacaciones
	 *            the tieneDireccionVacaciones to set
	 */
	public void setTieneDireccionVacaciones(boolean tieneDireccionVacaciones) {
		this.tieneDireccionVacaciones = tieneDireccionVacaciones;
	}

	/**
	 * @return the codigoPeriodoInicioVacaciones
	 */
	public String getCodigoPeriodoInicioVacaciones() {
		return codigoPeriodoInicioVacaciones;
	}

	/**
	 * @param codigoPeriodoInicioVacaciones
	 *            the codigoPeriodoInicioVacaciones to set
	 */
	public void setCodigoPeriodoInicioVacaciones(
			String codigoPeriodoInicioVacaciones) {
		this.codigoPeriodoInicioVacaciones = codigoPeriodoInicioVacaciones;
	}

	/**
	 * @return the codigoPeriodoFinVacaciones
	 */
	public String getCodigoPeriodoFinVacaciones() {
		return codigoPeriodoFinVacaciones;
	}

	/**
	 * @param codigoPeriodoFinVacaciones
	 *            the codigoPeriodoFinVacaciones to set
	 */
	public void setCodigoPeriodoFinVacaciones(String codigoPeriodoFinVacaciones) {
		this.codigoPeriodoFinVacaciones = codigoPeriodoFinVacaciones;
	}

	/**
	 * @return the tienePedidoFacturadoProceso
	 */
	public boolean isTienePedidoFacturadoProceso() {
		return tienePedidoFacturadoProceso;
	}

	/**
	 * @param tienePedidoFacturadoProceso
	 *            the tienePedidoFacturadoProceso to set
	 */
	public void setTienePedidoFacturadoProceso(
			boolean tienePedidoFacturadoProceso) {
		this.tienePedidoFacturadoProceso = tienePedidoFacturadoProceso;
	}

	/**
	 * @return the actualizaUbigeoDirecciones
	 */
	public boolean isActualizaUbigeoDirecciones() {
		return actualizaUbigeoDirecciones;
	}

	/**
	 * @param actualizaUbigeoDirecciones
	 *            the actualizaUbigeoDirecciones to set
	 */
	public void setActualizaUbigeoDirecciones(boolean actualizaUbigeoDirecciones) {
		this.actualizaUbigeoDirecciones = actualizaUbigeoDirecciones;
	}

	/**
	 * @return the tienePedidoEnProcesoFacturacion
	 */
	public boolean isTienePedidoEnProcesoFacturacion() {
		return tienePedidoEnProcesoFacturacion;
	}

	/**
	 * @param tienePedidoEnProcesoFacturacion
	 *            the tienePedidoEnProcesoFacturacion to set
	 */
	public void setTienePedidoEnProcesoFacturacion(
			boolean tienePedidoEnProcesoFacturacion) {
		this.tienePedidoEnProcesoFacturacion = tienePedidoEnProcesoFacturacion;
	}

	/**
	 * @return the lineaDireccionVacaciones
	 */
	public String getLineaDireccionVacaciones() {
		return lineaDireccionVacaciones;
	}

	/**
	 * @param lineaDireccionVacaciones
	 *            the lineaDireccionVacaciones to set
	 */
	public void setLineaDireccionVacaciones(String lineaDireccionVacaciones) {
		this.lineaDireccionVacaciones = lineaDireccionVacaciones;
	}

	/**
	 * @return the indicadorSeccionOtros
	 */
	public boolean isIndicadorSeccionOtros() {
		return indicadorSeccionOtros;
	}

	/**
	 * @param indicadorSeccionOtros
	 *            the indicadorSeccionOtros to set
	 */
	public void setIndicadorSeccionOtros(boolean indicadorSeccionOtros) {
		this.indicadorSeccionOtros = indicadorSeccionOtros;
	}

	/**
	 * @return the indicadorCompromiso
	 */
	public String getIndicadorCompromiso() {
		return indicadorCompromiso;
	}

	/**
	 * @param indicadorCompromiso
	 *            the indicadorCompromiso to set
	 */
	public void setIndicadorCompromiso(String indicadorCompromiso) {
		this.indicadorCompromiso = indicadorCompromiso;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo
	 *            the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the indicadorSeccionCompromiso
	 */
	public boolean isIndicadorSeccionCompromiso() {
		return indicadorSeccionCompromiso;
	}

	/**
	 * @param indicadorSeccionCompromiso
	 *            the indicadorSeccionCompromiso to set
	 */
	public void setIndicadorSeccionCompromiso(boolean indicadorSeccionCompromiso) {
		this.indicadorSeccionCompromiso = indicadorSeccionCompromiso;
	}

	/**
	 * @return the valorIndicadorCompromiso
	 */
	public String getValorIndicadorCompromiso() {
		return valorIndicadorCompromiso;
	}

	/**
	 * @param valorIndicadorCompromiso
	 *            the valorIndicadorCompromiso to set
	 */
	public void setValorIndicadorCompromiso(String valorIndicadorCompromiso) {
		this.valorIndicadorCompromiso = valorIndicadorCompromiso;
	}

	/**
	 * @return the indicadorImpresionPaqDoc
	 */
	public String getIndicadorImpresionPaqDoc() {
		return indicadorImpresionPaqDoc;
	}

	/**
	 * @param indicadorImpresionPaqDoc
	 *            the indicadorImpresionPaqDoc to set
	 */
	public void setIndicadorImpresionPaqDoc(String indicadorImpresionPaqDoc) {
		this.indicadorImpresionPaqDoc = indicadorImpresionPaqDoc;
	}

	/**
	 * @return the mostrarIndicadorImpresionPaqDoc
	 */
	public boolean isMostrarIndicadorImpresionPaqDoc() {
		return mostrarIndicadorImpresionPaqDoc;
	}

	/**
	 * @param mostrarIndicadorImpresionPaqDoc
	 *            the mostrarIndicadorImpresionPaqDoc to set
	 */
	public void setMostrarIndicadorImpresionPaqDoc(
			boolean mostrarIndicadorImpresionPaqDoc) {
		this.mostrarIndicadorImpresionPaqDoc = mostrarIndicadorImpresionPaqDoc;
	}

	/**
	 * @return the indicadorDocumentosLegales
	 */
	public boolean isIndicadorDocumentosLegales() {
		return indicadorDocumentosLegales;
	}

	/**
	 * @param indicadorDocumentosLegales
	 *            the indicadorDocumentosLegales to set
	 */
	public void setIndicadorDocumentosLegales(boolean indicadorDocumentosLegales) {
		this.indicadorDocumentosLegales = indicadorDocumentosLegales;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 *            the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion
	 *            the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return the indicadorSolicitudCredito
	 */
	public String getIndicadorSolicitudCredito() {
		return indicadorSolicitudCredito;
	}

	/**
	 * @param indicadorSolicitudCredito
	 *            the indicadorSolicitudCredito to set
	 */
	public void setIndicadorSolicitudCredito(String indicadorSolicitudCredito) {
		this.indicadorSolicitudCredito = indicadorSolicitudCredito;
	}

	/**
	 * @return the indicadorPantallaModificacion
	 */
	public String getIndicadorPantallaModificacion() {
		return indicadorPantallaModificacion;
	}

	/**
	 * @param indicadorPantallaModificacion
	 *            the indicadorPantallaModificacion to set
	 */
	public void setIndicadorPantallaModificacion(
			String indicadorPantallaModificacion) {
		this.indicadorPantallaModificacion = indicadorPantallaModificacion;
	}

	/**
	 * @return the indicadorGraboOk
	 */
	public String getIndicadorGraboOk() {
		return indicadorGraboOk;
	}

	/**
	 * @param indicadorGraboOk
	 *            the indicadorGraboOk to set
	 */
	public void setIndicadorGraboOk(String indicadorGraboOk) {
		this.indicadorGraboOk = indicadorGraboOk;
	}
	
	/**
	 * @return isMostrarBuscarDireccion
	 */
	public boolean isMostrarBuscarDireccion() {
		return mostrarBuscarDireccion;
	}
	
	/**
	 * @param mostrarBuscarDireccion
	 */
	public void setMostrarBuscarDireccion(boolean mostrarBuscarDireccion) {
		this.mostrarBuscarDireccion = mostrarBuscarDireccion;
	}

	/**
	 * @return the esMayorPeriodoVigente
	 */
	public boolean isEsMayorPeriodoVigente() {
		return esMayorPeriodoVigente;
	}

	/**
	 * @param esMayorPeriodoVigente the esMayorPeriodoVigente to set
	 */
	public void setEsMayorPeriodoVigente(boolean esMayorPeriodoVigente) {
		this.esMayorPeriodoVigente = esMayorPeriodoVigente;
	}

	/**
	 * @return the requiereGenerarEstatus
	 */
	public boolean isRequiereGenerarEstatus() {
		return requiereGenerarEstatus;
	}

	/**
	 * @param requiereGenerarEstatus the requiereGenerarEstatus to set
	 */
	public void setRequiereGenerarEstatus(boolean requiereGenerarEstatus) {
		this.requiereGenerarEstatus = requiereGenerarEstatus;
	}

	/**
	 * @return the codigoPeriodoIniUA
	 */
	public String getCodigoPeriodoIniUA() {
		return codigoPeriodoIniUA;
	}

	/**
	 * @param codigoPeriodoIniUA the codigoPeriodoIniUA to set
	 */
	public void setCodigoPeriodoIniUA(String codigoPeriodoIniUA) {
		this.codigoPeriodoIniUA = codigoPeriodoIniUA;
	}

	/**
	 * @return the mostrarBotonRedifinirVigenciaUA
	 */
	public boolean isMostrarBotonRedifinirVigenciaUA() {
		return mostrarBotonRedifinirVigenciaUA;
	}

	/**
	 * @param mostrarBotonRedifinirVigenciaUA the mostrarBotonRedifinirVigenciaUA to set
	 */
	public void setMostrarBotonRedifinirVigenciaUA(
			boolean mostrarBotonRedifinirVigenciaUA) {
		this.mostrarBotonRedifinirVigenciaUA = mostrarBotonRedifinirVigenciaUA;
	}
	
	/**
	 * @return the correVia
	 */
	public String getCorreVia() {
		return correVia;
	}

	/**
	 * @param correVia the correVia to set
	 */
	public void setCorreVia(String correVia) {
		this.correVia = correVia;
	}

	/**
	 * @return the correViaCT
	 */
	public String getCorreViaCT() {
		return correViaCT;
	}

	/**
	 * @param correViaCT the correViaCT to set
	 */
	public void setCorreViaCT(String correViaCT) {
		this.correViaCT = correViaCT;
	}

	/**
	 * @return the correViaVacaciones
	 */
	public String getCorreViaVacaciones() {
		return correViaVacaciones;
	}

	/**
	 * @param correViaVacaciones the correViaVacaciones to set
	 */
	public void setCorreViaVacaciones(String correViaVacaciones) {
		this.correViaVacaciones = correViaVacaciones;
	}


	public String getValorIndicadorFactElectAux() {
		return valorIndicadorFactElectAux;
	}

	public void setValorIndicadorFactElectAux(String valorIndicadorFactElectAux) {
		this.valorIndicadorFactElectAux = valorIndicadorFactElectAux;
	}


	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the codigoPostalCT
	 */
	public String getCodigoPostalCT() {
		return codigoPostalCT;
	}

	/**
	 * @param codigoPostalCT the codigoPostalCT to set
	 */
	public void setCodigoPostalCT(String codigoPostalCT) {
		this.codigoPostalCT = codigoPostalCT;
	}

	/**
	 * @return the telefonoAdicional
	 */
	public String getTelefonoAdicional() {
		return telefonoAdicional;
	}

	/**
	 * @param telefonoAdicional the telefonoAdicional to set
	 */
	public void setTelefonoAdicional(String telefonoAdicional) {
		this.telefonoAdicional = telefonoAdicional;
	}
	
	public boolean isEsTipoConsultora() {
		return esTipoConsultora;
	}

	public void setEsTipoConsultora(boolean esTipoConsultora) {
		this.esTipoConsultora = esTipoConsultora;
	}

	/**
	 * @return the oidLiderVinculo
	 */
	public String getOidLiderVinculo() {
		return oidLiderVinculo;
	}

	/**
	 * @param oidLiderVinculo the oidLiderVinculo to set
	 */
	public void setOidLiderVinculo(String oidLiderVinculo) {
		this.oidLiderVinculo = oidLiderVinculo;
	}

	/**
	 * @return the codigoLiderVinculo
	 */
	public String getCodigoLiderVinculo() {
		return codigoLiderVinculo;
	}

	/**
	 * @param codigoLiderVinculo the codigoLiderVinculo to set
	 */
	public void setCodigoLiderVinculo(String codigoLiderVinculo) {
		this.codigoLiderVinculo = codigoLiderVinculo;
	}

	/**
	 * @return the nombreLiderVinculo
	 */
	public String getNombreLiderVinculo() {
		return nombreLiderVinculo;
	}

	/**
	 * @param nombreLiderVinculo the nombreLiderVinculo to set
	 */
	public void setNombreLiderVinculo(String nombreLiderVinculo) {
		this.nombreLiderVinculo = nombreLiderVinculo;
	}

	/**
	 * @return the fechaDesdeLider
	 */
	public String getFechaDesdeLider() {
		return fechaDesdeLider;
	}

	/**
	 * @param fechaDesdeLider the fechaDesdeLider to set
	 */
	public void setFechaDesdeLider(String fechaDesdeLider) {
		this.fechaDesdeLider = fechaDesdeLider;
	}

	/**
	 * @return the fechaHastaLider
	 */
	public String getFechaHastaLider() {
		return fechaHastaLider;
	}

	/**
	 * @param fechaHastaLider the fechaHastaLider to set
	 */
	public void setFechaHastaLider(String fechaHastaLider) {
		this.fechaHastaLider = fechaHastaLider;
	}

	/**
	 * @return the mostrarVinculoLider
	 */
	public boolean isMostrarVinculoLider() {
		return mostrarVinculoLider;
	}

	/**
	 * @param mostrarVinculoLider the mostrarVinculoLider to set
	 */
	public void setMostrarVinculoLider(boolean mostrarVinculoLider) {
		this.mostrarVinculoLider = mostrarVinculoLider;
	}

	public Date getFechaNacimientoD() {
		return fechaNacimientoD;
	}

	public void setFechaNacimientoD(Date fechaNacimientoD) {
		this.fechaNacimientoD = fechaNacimientoD;
	}

	/**
	 * @return the indicadorDocFiscalAux
	 */
	public boolean isIndicadorDocFiscalAux() {
		return indicadorDocFiscalAux;
	}

	/**
	 * @param indicadorDocFiscalAux the indicadorDocFiscalAux to set
	 */
	public void setIndicadorDocFiscalAux(boolean indicadorDocFiscalAux) {
		this.indicadorDocFiscalAux = indicadorDocFiscalAux;
	}

	/**
	 * @return the indicadorDocFiscal
	 */
	public String getIndicadorDocFiscal() {
		return indicadorDocFiscal;
	}

	/**
	 * @param indicadorDocFiscal the indicadorDocFiscal to set
	 */
	public void setIndicadorDocFiscal(String indicadorDocFiscal) {
		this.indicadorDocFiscal = indicadorDocFiscal;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getOrigenIngreso() {
		return origenIngreso;
	}

	public void setOrigenIngreso(String origenIngreso) {
		this.origenIngreso = origenIngreso;
	}

	public boolean isIndicadorCamposAdicionales() {
		return indicadorCamposAdicionales;
	}

	public void setIndicadorCamposAdicionales(boolean indicadorCamposAdicionales) {
		this.indicadorCamposAdicionales = indicadorCamposAdicionales;
	}

	public String getBarrioDD() {
		return barrioDD;
	}

	public void setBarrioDD(String barrioDD) {
		this.barrioDD = barrioDD;
	}

	public String getManzanaLetraDD() {
		return manzanaLetraDD;
	}

	public void setManzanaLetraDD(String manzanaLetraDD) {
		this.manzanaLetraDD = manzanaLetraDD;
	}

	public String getEtapaConjuntoDD() {
		return etapaConjuntoDD;
	}

	public void setEtapaConjuntoDD(String etapaConjuntoDD) {
		this.etapaConjuntoDD = etapaConjuntoDD;
	}

	public String getCallePrincipalDD() {
		return callePrincipalDD;
	}

	public void setCallePrincipalDD(String callePrincipalDD) {
		this.callePrincipalDD = callePrincipalDD;
	}

	public String getCalleSecundariaDD() {
		return calleSecundariaDD;
	}

	public void setCalleSecundariaDD(String calleSecundariaDD) {
		this.calleSecundariaDD = calleSecundariaDD;
	}

	public String getBarrioDE() {
		return barrioDE;
	}

	public void setBarrioDE(String barrioDE) {
		this.barrioDE = barrioDE;
	}

	public String getManzanaLetraDE() {
		return manzanaLetraDE;
	}

	public void setManzanaLetraDE(String manzanaLetraDE) {
		this.manzanaLetraDE = manzanaLetraDE;
	}

	public String getEtapaConjuntoDE() {
		return etapaConjuntoDE;
	}

	public void setEtapaConjuntoDE(String etapaConjuntoDE) {
		this.etapaConjuntoDE = etapaConjuntoDE;
	}

	public String getCallePrincipalDE() {
		return callePrincipalDE;
	}

	public void setCallePrincipalDE(String callePrincipalDE) {
		this.callePrincipalDE = callePrincipalDE;
	}

	public String getCalleSecundariaDE() {
		return calleSecundariaDE;
	}

	public void setCalleSecundariaDE(String calleSecundariaDE) {
		this.calleSecundariaDE = calleSecundariaDE;
	}

	/**
	 * @return the mostrarSubTipoDocumento
	 */
	public boolean isMostrarSubTipoDocumento() {
		return mostrarSubTipoDocumento;
	}

	/**
	 * @param mostrarSubTipoDocumento the mostrarSubTipoDocumento to set
	 */
	public void setMostrarSubTipoDocumento(boolean mostrarSubTipoDocumento) {
		this.mostrarSubTipoDocumento = mostrarSubTipoDocumento;
	}

	/**
	 * @return the subTipoDocumentoIdentidad
	 */
	public String getSubTipoDocumentoIdentidad() {
		return subTipoDocumentoIdentidad;
	}

	/**
	 * @param subTipoDocumentoIdentidad the subTipoDocumentoIdentidad to set
	 */
	public void setSubTipoDocumentoIdentidad(String subTipoDocumentoIdentidad) {
		this.subTipoDocumentoIdentidad = subTipoDocumentoIdentidad;
	}

	/**
	 * @return the subTipoDocumentoIdentidad2
	 */
	public String getSubTipoDocumentoIdentidad2() {
		return subTipoDocumentoIdentidad2;
	}

	/**
	 * @param subTipoDocumentoIdentidad2 the subTipoDocumentoIdentidad2 to set
	 */
	public void setSubTipoDocumentoIdentidad2(String subTipoDocumentoIdentidad2) {
		this.subTipoDocumentoIdentidad2 = subTipoDocumentoIdentidad2;
	}

	/**
	 * @return the subTipoDocumentoIdentidad3
	 */
	public String getSubTipoDocumentoIdentidad3() {
		return subTipoDocumentoIdentidad3;
	}

	/**
	 * @param subTipoDocumentoIdentidad3 the subTipoDocumentoIdentidad3 to set
	 */
	public void setSubTipoDocumentoIdentidad3(String subTipoDocumentoIdentidad3) {
		this.subTipoDocumentoIdentidad3 = subTipoDocumentoIdentidad3;
	}

	/**
	 * @return the codigoBanco
	 */
	public Long getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return the cuentaBancaria
	 */
	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	/**
	 * @param cuentaBancaria the cuentaBancaria to set
	 */
	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	/**
	 * @return the indicadorCalcPercep
	 */
	public boolean isIndicadorCalcPercep() {
		return indicadorCalcPercep;
	}

	/**
	 * @param indicadorCalcPercep the indicadorCalcPercep to set
	 */
	public void setIndicadorCalcPercep(boolean indicadorCalcPercep) {
		this.indicadorCalcPercep = indicadorCalcPercep;
	}

	/**
	 * @return the booleanValorIndicadorCalcPercep
	 */
	public boolean isBooleanValorIndicadorCalcPercep() {
		return booleanValorIndicadorCalcPercep;
	}

	/**
	 * @param booleanValorIndicadorCalcPercep the booleanValorIndicadorCalcPercep to set
	 */
	public void setBooleanValorIndicadorCalcPercep(
			boolean booleanValorIndicadorCalcPercep) {
		this.booleanValorIndicadorCalcPercep = booleanValorIndicadorCalcPercep;
	}

	/**
	 * @return the codigoTerritorialCorrespondeDD
	 */
	public String getCodigoTerritorialCorrespondeDD() {
		return codigoTerritorialCorrespondeDD;
	}

	/**
	 * @param codigoTerritorialCorrespondeDD the codigoTerritorialCorrespondeDD to set
	 */
	public void setCodigoTerritorialCorrespondeDD(String codigoTerritorialCorrespondeDD) {
		this.codigoTerritorialCorrespondeDD = codigoTerritorialCorrespondeDD;
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * @return the cuentaCorriente
	 */
	public String getCuentaCorriente() {
		return cuentaCorriente;
	}

	/**
	 * @param cuentaCorriente the cuentaCorriente to set
	 */
	public void setCuentaCorriente(String cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	
}
