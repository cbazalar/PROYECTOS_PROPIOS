package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoExcepcionesValidacionesForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String tipoDocumento;
	private String validaciones;
	private String[] regionList;
	private String[] zonaList;
	private String codigoPeriodo;
	private String codigoConsultora;
	private UploadedFile clienteFile;
	private String[] clienteList;
	private String codigosErradosFile;
	private String fecha;
	private Date fechaDate;
	private int errados;
	private int correctos;
	
	private String indicadorRegiones;
	private String indicadorClientes; 	
	private String viewRegiones;
	
	public MantenimientoExcepcionesValidacionesForm(){
		this.errados = 0;
		this.correctos = 0;
		this.viewRegiones = "0";
	
	}
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @struts.validator type = "required"
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
	 * @return the clienteList
	 */
	public String[] getClienteList() {
		return clienteList;
	}

	/**
	 * @param clienteList the clienteList to set
	 */
	public void setClienteList(String[] clienteList) {
		this.clienteList = clienteList;
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
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * @return the validaciones
	 */
	public String getValidaciones() {
		return validaciones;
	}

	/**
	 * @struts.validator type = "required"
	 * @param validaciones the validaciones to set
	 */
	public void setValidaciones(String validaciones) {
		this.validaciones = validaciones;
	}

	/**
	 * @return the errados
	 */
	public int getErrados() {
		return errados;
	}

	/**
	 * @param errados the errados to set
	 */
	public void setErrados(int errados) {
		this.errados = errados;
	}

	/**
	 * @return the indicadorRegiones
	 */
	public String getIndicadorRegiones() {
		return indicadorRegiones;
	}

	/**
	 * @param indicadorRegiones the indicadorRegiones to set
	 */
	public void setIndicadorRegiones(String indicadorRegiones) {
		this.indicadorRegiones = indicadorRegiones;
	}

	/**
	 * @return the indicadorClientes
	 */
	public String getIndicadorClientes() {
		return indicadorClientes;
	}

	/**
	 * @param indicadorClientes the indicadorClientes to set
	 */
	public void setIndicadorClientes(String indicadorClientes) {
		this.indicadorClientes = indicadorClientes;
	}

	/**
	 * @return the viewRegiones
	 */
	public String getViewRegiones() {
		return viewRegiones;
	}

	/**
	 * @param viewRegiones the viewRegiones to set
	 */
	public void setViewRegiones(String viewRegiones) {
		this.viewRegiones = viewRegiones;
	}

	/**
	 * @return the correctos
	 */
	public int getCorrectos() {
		return correctos;
	}

	/**
	 * @param correctos the correctos to set
	 */
	public void setCorrectos(int correctos) {
		this.correctos = correctos;
	}

	public Date getFechaDate() {
		return fechaDate;
	}

	public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
	}
}