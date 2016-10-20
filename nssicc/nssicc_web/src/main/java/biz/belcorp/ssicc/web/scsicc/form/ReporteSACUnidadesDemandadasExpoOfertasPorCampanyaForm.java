package biz.belcorp.ssicc.web.scsicc.form;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSACUnidadesDemandadasExpoOfertasPorCampanyaForm extends BaseReporteForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//-- Variables
	private String codigoPais;
	private String codigoPeriodo;
	
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
}
