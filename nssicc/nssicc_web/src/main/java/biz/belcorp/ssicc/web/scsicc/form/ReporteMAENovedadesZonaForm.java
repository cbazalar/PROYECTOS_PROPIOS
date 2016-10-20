package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="#">Yahir Rivas Luna</a>
 * 
 * @struts.form name="reporteMAENovedadesZonaForm" extends="baseReporteForm"
 */
public class ReporteMAENovedadesZonaForm extends BaseReporteForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
}