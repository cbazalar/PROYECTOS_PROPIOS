package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * <p>
 * <a href="ConsultaOCRSolicitudesCreditoForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 *
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public class ConsultaOCRSolicitudesCreditoForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoCliente;
	private String numeroDocIdentidad;
	
	private String tipoArchivo;
	
	private UploadedFile clienteFile;
	private UploadedFile clienteFile2;
	
	private String longitudCodigoCliente;
	private String oidIdioma;

	private boolean mostrarReporteImagenesSC;
	private String totalConsultora;
	private boolean mostrarListaDatos;
	
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
	 * @return the mostrarReporteImagenesSC
	 */
	public boolean isMostrarReporteImagenesSC() {
		return mostrarReporteImagenesSC;
	}

	/**
	 * @param mostrarReporteImagenesSC the mostrarReporteImagenesSC to set
	 */
	public void setMostrarReporteImagenesSC(boolean mostrarReporteImagenesSC) {
		this.mostrarReporteImagenesSC = mostrarReporteImagenesSC;
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
