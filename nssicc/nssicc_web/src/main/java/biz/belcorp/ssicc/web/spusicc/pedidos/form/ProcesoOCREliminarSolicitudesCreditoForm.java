package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * <p>
 * <a href="ProcesoOCREliminarSolicitudesCreditoForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 *
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public class ProcesoOCREliminarSolicitudesCreditoForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoCliente;
	private String numeroDocIdentidad;
	
	private String tipoArchivo;
	
	private UploadedFile clienteFile;
	private UploadedFile clienteFile2;
	
	private String longitudCodigoCliente;
	private String oidIdioma;

	private String totalConsultora;
	private boolean mostrarListaDatos;
	
	private String indicadorConsultorasInd;
	private String indicadorConsultorasFile1;
	private String indicadorConsultorasFile2;
	
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
	 * @return the numeroDocIdentidad
	 */
	public String getNumeroDocIdentidad() {
		return numeroDocIdentidad;
	}

	/**
	 * @param numeroDocIdentidad the numeroDocIdentidad to set
	 */
	public void setNumeroDocIdentidad(String numeroDocIdentidad) {
		this.numeroDocIdentidad = numeroDocIdentidad;
	}

	/**
	 * @return the tipoArchivo
	 */
	public String getTipoArchivo() {
		return tipoArchivo;
	}

	/**
	 * @param tipoArchivo the tipoArchivo to set
	 */
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	/**
	 * @return the longitudCodigoCliente
	 */
	public String getLongitudCodigoCliente() {
		return longitudCodigoCliente;
	}

	/**
	 * @param longitudCodigoCliente the longitudCodigoCliente to set
	 */
	public void setLongitudCodigoCliente(String longitudCodigoCliente) {
		this.longitudCodigoCliente = longitudCodigoCliente;
	}

	/**
	 * @return the oidIdioma
	 */
	public String getOidIdioma() {
		return oidIdioma;
	}

	/**
	 * @param oidIdioma the oidIdioma to set
	 */
	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}

	/**
	 * @return the totalConsultora
	 */
	public String getTotalConsultora() {
		return totalConsultora;
	}

	/**
	 * @param totalConsultora the totalConsultora to set
	 */
	public void setTotalConsultora(String totalConsultora) {
		this.totalConsultora = totalConsultora;
	}

	/**
	 * @return the mostrarListaDatos
	 */
	public boolean isMostrarListaDatos() {
		return mostrarListaDatos;
	}

	/**
	 * @param mostrarListaDatos the mostrarListaDatos to set
	 */
	public void setMostrarListaDatos(boolean mostrarListaDatos) {
		this.mostrarListaDatos = mostrarListaDatos;
	}

	/**
	 * @return the indicadorConsultorasInd
	 */
	public String getIndicadorConsultorasInd() {
		return indicadorConsultorasInd;
	}

	/**
	 * @param indicadorConsultorasInd the indicadorConsultorasInd to set
	 */
	public void setIndicadorConsultorasInd(String indicadorConsultorasInd) {
		this.indicadorConsultorasInd = indicadorConsultorasInd;
	}

	/**
	 * @return the indicadorConsultorasFile1
	 */
	public String getIndicadorConsultorasFile1() {
		return indicadorConsultorasFile1;
	}

	/**
	 * @param indicadorConsultorasFile1 the indicadorConsultorasFile1 to set
	 */
	public void setIndicadorConsultorasFile1(String indicadorConsultorasFile1) {
		this.indicadorConsultorasFile1 = indicadorConsultorasFile1;
	}

	/**
	 * @return the indicadorConsultorasFile2
	 */
	public String getIndicadorConsultorasFile2() {
		return indicadorConsultorasFile2;
	}

	/**
	 * @param indicadorConsultorasFile2 the indicadorConsultorasFile2 to set
	 */
	public void setIndicadorConsultorasFile2(String indicadorConsultorasFile2) {
		this.indicadorConsultorasFile2 = indicadorConsultorasFile2;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.indicadorConsultorasInd = "0";
		this.indicadorConsultorasFile1 = "0";
		this.indicadorConsultorasFile2 = "0";
	}

	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}

	public UploadedFile getClienteFile2() {
		return clienteFile2;
	}

	public void setClienteFile2(UploadedFile clienteFile2) {
		this.clienteFile2 = clienteFile2;
	}
	
}

