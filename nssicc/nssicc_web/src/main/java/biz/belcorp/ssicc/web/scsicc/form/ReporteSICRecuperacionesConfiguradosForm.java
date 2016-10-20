package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/** 
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 * 
 * @struts.form name="reporteSICRecuperacionesConfiguradosForm" extends="baseReporteForm"
 */

public class ReporteSICRecuperacionesConfiguradosForm extends BaseReporteForm {

	private String codigoPais;
	private String codigoPeriodo;
	
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