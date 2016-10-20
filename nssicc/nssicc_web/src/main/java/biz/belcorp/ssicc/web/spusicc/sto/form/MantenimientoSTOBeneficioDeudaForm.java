package biz.belcorp.ssicc.web.spusicc.sto.form;

import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


public class MantenimientoSTOBeneficioDeudaForm extends BaseEditForm{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String codigoCliente;
	private String codigoRegion;
	private String codigoZona;
	private String tipoBeneficio;
	private String observaciones;
	private String porcentajeBeneficio;
	private String montoDeuda;
	private String codigoPeriodo;	
	private String codigosErradosFile;
	private String oidSubTipoClienteSelected;
	private String oidTipoClienteSelected;
	private String codigoZonaSelected;
	private String numeroCodigoErrados;
	private UploadedFile clienteFile; // objeto que se utilizara para el upload

	
	/**
	 * @return codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return montoDeuda
	 */
	public String getMontoDeuda() {
		return montoDeuda;
	}

	/**
	 * @param montoDeuda
	 */
	public void setMontoDeuda(String montoDeuda) {
		this.montoDeuda = montoDeuda;
	}

	/**
	 * @return porcentajeBeneficio
	 */
	public String getPorcentajeBeneficio() {
		return porcentajeBeneficio;
	}

	/**
	 * @param porcentajeBeneficio
	 */
	public void setPorcentajeBeneficio(String porcentajeBeneficio) {
		this.porcentajeBeneficio = porcentajeBeneficio;
	}

	/**
	 * @return observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return tipoBeneficio
	 */
	public String getTipoBeneficio() {
		return tipoBeneficio;
	}

	/**
	 * @param tipoBeneficio
	 */
	public void setTipoBeneficio(String tipoBeneficio) {
		this.tipoBeneficio = tipoBeneficio;
	}

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	/**
	 * @return oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return codigosErradosFile
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}

	/**
	 * @param codigosErradosFile
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}

	/**
	 * @return oidSubTipoClienteSelected
	 */
	public String getOidSubTipoClienteSelected() {
		return oidSubTipoClienteSelected;
	}

	/**
	 * @param oidSubTipoClienteSelected
	 */
	public void setOidSubTipoClienteSelected(String oidSubTipoClienteSelected) {
		this.oidSubTipoClienteSelected = oidSubTipoClienteSelected;
	}

	/**
	 * @return oidTipoClienteSelected
	 */
	public String getOidTipoClienteSelected() {
		return oidTipoClienteSelected;
	}

	/**
	 * @param oidTipoClienteSelected
	 */
	public void setOidTipoClienteSelected(String oidTipoClienteSelected) {
		this.oidTipoClienteSelected = oidTipoClienteSelected;
	}

	/**
	 * @return codigoZonaSelected
	 */
	public String getCodigoZonaSelected() {
		return codigoZonaSelected;
	}

	/**
	 * @param codigoZonaSelected
	 */
	public void setCodigoZonaSelected(String codigoZonaSelected) {
		this.codigoZonaSelected = codigoZonaSelected;
	}

	/**
	 * @return the numeroCodigoErrados
	 */
	public String getNumeroCodigoErrados() {
		return numeroCodigoErrados;
	}

	/**
	 * @param numeroCodigoErrados the numeroCodigoErrados to set
	 */
	public void setNumeroCodigoErrados(String numeroCodigoErrados) {
		this.numeroCodigoErrados = numeroCodigoErrados;
	}

	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}	
	
	
}