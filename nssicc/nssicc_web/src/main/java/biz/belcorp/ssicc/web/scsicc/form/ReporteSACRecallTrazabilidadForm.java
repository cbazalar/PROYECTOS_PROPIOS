package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACRecallTrazabilidadForm.
 */
public class ReporteSACRecallTrazabilidadForm extends BaseReporteForm{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo pais. */
	private String codigoPais;
	
	/** The codigo sap. */
	private String codigoSAP;
	
	/** The numero lote. */
	private String numeroLote;
	
	/** The fecha inicial. */
	private String fechaInicial;
	
	/** The fecha final. */
	private String fechaFinal;
	
	/** The fecha inicial date. */
	private Date fechaInicialDate;
	
	/** The fecha final date. */
	private Date fechaFinalDate;
	
	/**
	 * Gets the codigo pais.
	 *
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}	
	
	/**
	 * Gets the codigo sap.
	 *
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	
	/**
	 * Sets the codigo sap.
	 *
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	
	/**
	 * Gets the numero lote.
	 *
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	
	/**
	 * Sets the numero lote.
	 *
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	
	/**
	 * Gets the fecha inicial.
	 *
	 * @return the fechaInicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}
	
	/**
	 * Sets the fecha inicial.
	 *
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	
	/**
	 * Gets the fecha final.
	 *
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}
	
	/**
	 * Sets the fecha final.
	 *
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	/**
	 * Gets the fecha inicial date.
	 *
	 * @return the fecha inicial date
	 */
	public Date getFechaInicialDate() {
		return fechaInicialDate;
	}
	
	/**
	 * Sets the fecha inicial date.
	 *
	 * @param fechaInicialDate the new fecha inicial date
	 */
	public void setFechaInicialDate(Date fechaInicialDate) {
		this.fechaInicialDate = fechaInicialDate;
	}
	
	/**
	 * Gets the fecha final date.
	 *
	 * @return the fecha final date
	 */
	public Date getFechaFinalDate() {
		return fechaFinalDate;
	}
	
	/**
	 * Sets the fecha final date.
	 *
	 * @param fechaFinalDate the new fecha final date
	 */
	public void setFechaFinalDate(Date fechaFinalDate) {
		this.fechaFinalDate = fechaFinalDate;
	}
}