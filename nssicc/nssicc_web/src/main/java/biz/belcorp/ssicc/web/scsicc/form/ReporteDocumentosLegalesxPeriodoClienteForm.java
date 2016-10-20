package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteDocumentosLegalesxPeriodoClienteForm extends BaseReporteForm
implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8321620944261341274L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codConsultora;
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
	 * @return the codConsultora
	 */
	public String getCodConsultora() {
		return codConsultora;
	}
	/**
	 * @param codConsultora the codConsultora to set
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}
	
	

}
