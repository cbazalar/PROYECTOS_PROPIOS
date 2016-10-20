package biz.belcorp.ssicc.web.spusicc.sto.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoSTOFacturacionAdicionalForm extends BaseEditForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8530364969423824263L;

	private String codigoPais;
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String codigoRegion;
	private String codigoZona;
	private String codigoCliente;
	private String nombreCliente;
	private String observaciones;
	private String codigoPeriodo;
	private UploadedFile clienteFile;
	private String codigosErrados;
	private String codigosErradosFile;
	private String validaPromedio;
	private String validaMontoMinimo;	
	

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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
	 * @return the clienteFile
	 */
	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	/**
	 * @param clienteFile the clienteFile to set
	 */
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}

	/**
	 * @return the codigosErrados
	 */
	public String getCodigosErrados() {
		return codigosErrados;
	}

	/**
	 * @param codigosErrados the codigosErrados to set
	 */
	public void setCodigosErrados(String codigosErrados) {
		this.codigosErrados = codigosErrados;
	}

	/**
	 * @return the codigosErradosFile
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}

	/**
	 * @param codigosErradosFile the codigosErradosFile to set
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}

	/**
	 * @return the validaPromedio
	 */
	public String getValidaPromedio() {
		return validaPromedio;
	}

	/**
	 * @param validaPromedio the validaPromedio to set
	 */
	public void setValidaPromedio(String validaPromedio) {
		this.validaPromedio = validaPromedio;
	}

	/**
	 * @return the validaMontoMinimo
	 */
	public String getValidaMontoMinimo() {
		return validaMontoMinimo;
	}

	/**
	 * @param validaMontoMinimo the validaMontoMinimo to set
	 */
	public void setValidaMontoMinimo(String validaMontoMinimo) {
		this.validaMontoMinimo = validaMontoMinimo;
	}
}
