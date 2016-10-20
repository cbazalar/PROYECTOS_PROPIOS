/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSICPrecioCeroForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 17/07/2014
 */
public class ReporteSICPrecioCeroForm  extends BaseReporteForm{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoPeriodo;

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodjigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
}
