package biz.belcorp.ssicc.web.scsicc.form;


import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a>Yahir Rivas Luna</a>
 * 
 * @struts.form name = "reporteLETMovimientoLiderForm"
 */

public class ReporteLETMovimientoLiderForm extends BaseReporteForm
		implements Serializable {

	private String codigoPais;

	private String codigoPeriodo;
	

		

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
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
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	
	
}
