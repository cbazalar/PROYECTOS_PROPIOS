package biz.belcorp.ssicc.web.spusicc.ruv.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteRUVDocumentosContablesMantenimientoForm extends BaseReporteForm implements Serializable{
	
	private static final long serialVersionUID = 7155484986378079101L;
	private String descripcionCanal;
	private String descripcionAcceso;
	private String descripcionSubAcceso;
	private String serie;
	private String oidTipoDocumento;
	
	private String numDocInterno;
	private String fechaDesde;	
	private String fechaHasta;
	
	private String oidSubAcceso;
	private String numeroDocumentoInicial;	
	private String numeroDocumentoFinal;
	/**
	 * @return the descripcionCanal
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}
	/**
	 * @param descripcionCanal the descripcionCanal to set
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}
	/**
	 * @return the descripcionAcceso
	 */
	public String getDescripcionAcceso() {
		return descripcionAcceso;
	}
	/**
	 * @param descripcionAcceso the descripcionAcceso to set
	 */
	public void setDescripcionAcceso(String descripcionAcceso) {
		this.descripcionAcceso = descripcionAcceso;
	}
	/**
	 * @return the descripcionSubAcceso
	 */
	public String getDescripcionSubAcceso() {
		return descripcionSubAcceso;
	}
	/**
	 * @param descripcionSubAcceso the descripcionSubAcceso to set
	 */
	public void setDescripcionSubAcceso(String descripcionSubAcceso) {
		this.descripcionSubAcceso = descripcionSubAcceso;
	}
	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}
	/**
	 * @param serie the serie to set
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
	/**
	 * @return the oidTipoDocumento
	 */
	public String getOidTipoDocumento() {
		return oidTipoDocumento;
	}
	/**
	 * @param oidTipoDocumento the oidTipoDocumento to set
	 */
	public void setOidTipoDocumento(String oidTipoDocumento) {
		this.oidTipoDocumento = oidTipoDocumento;
	}
	/**
	 * @return the numDocInterno
	 */
	public String getNumDocInterno() {
		return numDocInterno;
	}
	/**
	 * @param numDocInterno the numDocInterno to set
	 */
	public void setNumDocInterno(String numDocInterno) {
		this.numDocInterno = numDocInterno;
	}
	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}
	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	/**
	 * @return the oidSubAcceso
	 */
	public String getOidSubAcceso() {
		return oidSubAcceso;
	}
	/**
	 * @param oidSubAcceso the oidSubAcceso to set
	 */
	public void setOidSubAcceso(String oidSubAcceso) {
		this.oidSubAcceso = oidSubAcceso;
	}
	/**
	 * @return the numeroDocumentoInicial
	 */
	public String getNumeroDocumentoInicial() {
		return numeroDocumentoInicial;
	}
	/**
	 * @param numeroDocumentoInicial the numeroDocumentoInicial to set
	 */
	public void setNumeroDocumentoInicial(String numeroDocumentoInicial) {
		this.numeroDocumentoInicial = numeroDocumentoInicial;
	}
	/**
	 * @return the numeroDocumentoFinal
	 */
	public String getNumeroDocumentoFinal() {
		return numeroDocumentoFinal;
	}
	/**
	 * @param numeroDocumentoFinal the numeroDocumentoFinal to set
	 */
	public void setNumeroDocumentoFinal(String numeroDocumentoFinal) {
		this.numeroDocumentoFinal = numeroDocumentoFinal;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
