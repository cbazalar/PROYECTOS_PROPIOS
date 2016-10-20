package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRECGestionIngresoAnulacionNmpsSearchForm extends BaseSearchForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2966734139793295821L;

	private String codigoPais;

	private String codigoMarca;

	private String codigoCanal;

	private String numeroBoleta;

	private String codigoCliente;

	private String[] regionList;

	private String[] zonaList;

	private String[] seccionList;

	private String[] territorioList;

	private String fechaInicial;

	private String fechaFinal;

	private String codigoPeriodo;

	private String tipoSeleccion;

	private String tipoSeleccionActual;

	private String tipoSeleccion1;
	private String tipoSeleccion2;
	private String tipoSeleccion3;

	private String codigoProceso;

	private UploadedFile archivo; // objeto que se utilizara para el upload del
								// Archivo
	private String directorioTemporal; // directorio temporal del servidor donde
										// se guardara el archivo
	private String nombreArchivo; // nombre del archivo a subirse al servidor
	private String extensionArchivo; // extension del archivo

	private String flagNotaMercaderiaPerdida;
	private String flagMostrarError;
	private String flagGenerarEnvia;

	private String codigoConsultora;
	private String numeroPedido;
	private String numeroNMP;
	private String observaciones;
	private String fechaFacturacion;

	private String motivoAnulacion;

	private String tipoAnulacion;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
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
	 * @param codigoMarca the codigoMarca to set
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
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the numeroBoleta
	 */
	public String getNumeroBoleta() {
		return numeroBoleta;
	}

	/**
	 * @param numeroBoleta the numeroBoleta to set
	 */
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the seccionList
	 */
	public String[] getSeccionList() {
		return seccionList;
	}

	/**
	 * @param seccionList the seccionList to set
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}

	/**
	 * @return the territorioList
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList the territorioList to set
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * @return the fechaInicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the tipoSeleccion
	 */
	public String getTipoSeleccion() {
		return tipoSeleccion;
	}

	/**
	 * @param tipoSeleccion the tipoSeleccion to set
	 */
	public void setTipoSeleccion(String tipoSeleccion) {
		this.tipoSeleccion = tipoSeleccion;
	}

	/**
	 * @return the tipoSeleccionActual
	 */
	public String getTipoSeleccionActual() {
		return tipoSeleccionActual;
	}

	/**
	 * @param tipoSeleccionActual the tipoSeleccionActual to set
	 */
	public void setTipoSeleccionActual(String tipoSeleccionActual) {
		this.tipoSeleccionActual = tipoSeleccionActual;
	}

	/**
	 * @return the tipoSeleccion1
	 */
	public String getTipoSeleccion1() {
		return tipoSeleccion1;
	}

	/**
	 * @param tipoSeleccion1 the tipoSeleccion1 to set
	 */
	public void setTipoSeleccion1(String tipoSeleccion1) {
		this.tipoSeleccion1 = tipoSeleccion1;
	}

	/**
	 * @return the tipoSeleccion2
	 */
	public String getTipoSeleccion2() {
		return tipoSeleccion2;
	}

	/**
	 * @param tipoSeleccion2 the tipoSeleccion2 to set
	 */
	public void setTipoSeleccion2(String tipoSeleccion2) {
		this.tipoSeleccion2 = tipoSeleccion2;
	}

	/**
	 * @return the tipoSeleccion3
	 */
	public String getTipoSeleccion3() {
		return tipoSeleccion3;
	}

	/**
	 * @param tipoSeleccion3 the tipoSeleccion3 to set
	 */
	public void setTipoSeleccion3(String tipoSeleccion3) {
		this.tipoSeleccion3 = tipoSeleccion3;
	}

	/**
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}

	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	

	/**
	 * @return the archivo
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the directorioTemporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	/**
	 * @param directorioTemporal the directorioTemporal to set
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the extensionArchivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * @param extensionArchivo the extensionArchivo to set
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * @return the flagNotaMercaderiaPerdida
	 */
	public String getFlagNotaMercaderiaPerdida() {
		return flagNotaMercaderiaPerdida;
	}

	/**
	 * @param flagNotaMercaderiaPerdida the flagNotaMercaderiaPerdida to set
	 */
	public void setFlagNotaMercaderiaPerdida(String flagNotaMercaderiaPerdida) {
		this.flagNotaMercaderiaPerdida = flagNotaMercaderiaPerdida;
	}

	/**
	 * @return the flagMostrarError
	 */
	public String getFlagMostrarError() {
		return flagMostrarError;
	}

	/**
	 * @param flagMostrarError the flagMostrarError to set
	 */
	public void setFlagMostrarError(String flagMostrarError) {
		this.flagMostrarError = flagMostrarError;
	}

	/**
	 * @return the flagGenerarEnvia
	 */
	public String getFlagGenerarEnvia() {
		return flagGenerarEnvia;
	}

	/**
	 * @param flagGenerarEnvia the flagGenerarEnvia to set
	 */
	public void setFlagGenerarEnvia(String flagGenerarEnvia) {
		this.flagGenerarEnvia = flagGenerarEnvia;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the numeroPedido
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}

	/**
	 * @param numeroPedido the numeroPedido to set
	 */
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	/**
	 * @return the numeroNMP
	 */
	public String getNumeroNMP() {
		return numeroNMP;
	}

	/**
	 * @param numeroNMP the numeroNMP to set
	 */
	public void setNumeroNMP(String numeroNMP) {
		this.numeroNMP = numeroNMP;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the motivoAnulacion
	 */
	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	/**
	 * @param motivoAnulacion the motivoAnulacion to set
	 */
	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	/**
	 * @return the tipoAnulacion
	 */
	public String getTipoAnulacion() {
		return tipoAnulacion;
	}

	/**
	 * @param tipoAnulacion the tipoAnulacion to set
	 */
	public void setTipoAnulacion(String tipoAnulacion) {
		this.tipoAnulacion = tipoAnulacion;
	}
	
	

}
