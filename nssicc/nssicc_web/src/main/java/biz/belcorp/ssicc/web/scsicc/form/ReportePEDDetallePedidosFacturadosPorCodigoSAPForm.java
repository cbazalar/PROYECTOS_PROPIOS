package biz.belcorp.ssicc.web.scsicc.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePEDDetallePedidosFacturadosPorCodigoSAPForm extends BaseReporteForm{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoSAP;
	private String[] arrayCodigosSAP;
	
	private UploadedFile sapsFile;
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
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	/**
	 * @return the arrayCodigosSAP
	 */
	public String[] getArrayCodigosSAP() {
		return arrayCodigosSAP;
	}
	/**
	 * @param arrayCodigosSAP the arrayCodigosSAP to set
	 */
	public void setArrayCodigosSAP(String[] arrayCodigosSAP) {
		this.arrayCodigosSAP = arrayCodigosSAP;
	}
	/**
	 * @return the sapsFile
	 */
	public UploadedFile getSapsFile() {
		return sapsFile;
	}
	/**
	 * @param sapsFile the sapsFile to set
	 */
	public void setSapsFile(UploadedFile sapsFile) {
		this.sapsFile = sapsFile;
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