package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteMSGValidacionesMensajesForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 25/11/2014
 */
public class ReporteMSGValidacionesMensajesForm extends BaseReporteForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
}
