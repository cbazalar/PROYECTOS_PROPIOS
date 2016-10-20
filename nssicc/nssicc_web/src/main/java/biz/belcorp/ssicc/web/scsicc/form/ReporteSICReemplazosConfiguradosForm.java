package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSICReemplazosConfiguradosForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 22/07/2014
 */
public class ReporteSICReemplazosConfiguradosForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPeriodo;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	
}
