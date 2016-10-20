package biz.belcorp.ssicc.web.scsicc.form;


import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePEDReservaProlDioFaltanteForm extends BaseReporteForm {	
	
	private static final long serialVersionUID = 5490065610643837087L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private UploadedFile clienteFile; 
	private String directorioTemporal;
	
	
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
	
	


}
