package biz.belcorp.ssicc.web.spusicc.sto.form;

import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoSTOBeneficioDeudaSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 * 
 * @struts.form name = "mantenimientoSTOBeneficioDeudaSearchForm"
 */

public class MantenimientoSTOBeneficioDeudaSearchForm extends BaseSearchForm{
	
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
	private String porcentajeBeneficio;
	private String montoDeuda;
	private String codigoPeriodo;
	private UploadedFile clienteFile; // objeto que se utilizara para el upload
	private String codigosErradosFile;
	
	/**
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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