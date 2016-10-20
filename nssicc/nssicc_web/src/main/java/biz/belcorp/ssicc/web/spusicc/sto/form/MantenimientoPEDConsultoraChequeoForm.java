package biz.belcorp.ssicc.web.spusicc.sto.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class MantenimientoPEDConsultoraChequeoForm extends BaseSearchForm{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoPeriodoActual;
	private String codigoTipoChequeo;
	private String codigoConsultora;
	private String descripcionConsultora;
	private String oidPais;
	private String longitudCodigoConsultora;
	private UploadedFile clienteFile; // objeto que se utilizara para el upload
	private String codigosErradosFile;

	private String eliminarTodo;
	private String lineaDefecto;
	private String lineaMaxima;

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
	 * @return the codigoTipoChequeo
	 */
	public String getCodigoTipoChequeo() {
		return codigoTipoChequeo;
	}

	/**
	 * @param codigoTipoChequeo the codigoTipoChequeo to set 
	 */
	public void setCodigoTipoChequeo(String codigoTipoChequeo) {
		this.codigoTipoChequeo = codigoTipoChequeo;
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
	 * @return the descripcionConsultora
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}

	/**
	 * @param descripcionConsultora the descripcionConsultora to set
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the longitudCodigoConsultora
	 */
	public String getLongitudCodigoConsultora() {
		return longitudCodigoConsultora;
	}

	/**
	 * @param longitudCodigoConsultora the longitudCodigoConsultora to set
	 */
	public void setLongitudCodigoConsultora(String longitudCodigoConsultora) {
		this.longitudCodigoConsultora = longitudCodigoConsultora;
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
	 * @return the codigoPeriodoActual
	 */
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}

	/**
	 * @param codigoPeriodoActual the codigoPeriodoActual to set
	 */
	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}

	/**
	 * @return the eliminarTodo
	 */
	public String getEliminarTodo() {
		return eliminarTodo;
	}

	/**
	 * @param eliminarTodo the eliminarTodo to set
	 */
	public void setEliminarTodo(String eliminarTodo) {
		this.eliminarTodo = eliminarTodo;
	}

	/**
	 * @return the lineaDefecto
	 */
	public String getLineaDefecto() {
		return lineaDefecto;
	}

	/**
	 * @param lineaDefecto the lineaDefecto to set
	 */
	public void setLineaDefecto(String lineaDefecto) {
		this.lineaDefecto = lineaDefecto;
	}

	/**
	 * @return the lineaMaxima
	 */
	public String getLineaMaxima() {
		return lineaMaxima;
	}

	/**
	 * @param lineaMaxima the lineaMaxima to set
	 */
	public void setLineaMaxima(String lineaMaxima) {
		this.lineaMaxima = lineaMaxima;
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
}