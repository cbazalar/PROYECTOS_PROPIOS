package biz.belcorp.ssicc.web.scsicc.form;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSACControlFacturacionEntregaPedidosForm extends BaseReporteForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//-- Variables
	private String codigoPais;
	private String codigoPeriodoInicial;
	private String codigoPeriodoFinal;	
	
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
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}
	/**
	 * @param codigoPeriodoInicial The codigoPeriodoInicial to set.
	 * 
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}
	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}
	/**
	 * @param codigoPeriodoFinal The codigoPeriodoFinal to set.
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}
}
