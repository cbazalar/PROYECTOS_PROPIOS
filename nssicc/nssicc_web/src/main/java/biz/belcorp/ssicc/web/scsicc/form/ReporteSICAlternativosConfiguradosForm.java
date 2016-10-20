package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteSICAlternativosConfiguradosForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 11/08/2014
 */
public class ReporteSICAlternativosConfiguradosForm extends BaseReporteForm {

	
	private String codigoPeriodo;
	private String codigoPais;
	
	private static final long serialVersionUID = 1L;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPais
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	
}
