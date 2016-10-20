package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSICLimitesVentaConfiguradosForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 30/07/2014
 */
public class ReporteSICLimitesVentaConfiguradosForm extends BaseReporteForm {

	/** The codigo pais. */
	private String codigoPais;
	
	/** The codigo periodo. */
	private String codigoPeriodo;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
			
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
	 * @param codigoPais The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}		
}