package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSICPedidosDigitadosRegionForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 02/06/2014
 */
public class ReporteSICPedidosDigitadosRegionForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;	
	private String periodo;	
	private String[] regionList;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return this.codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return this.periodo;
	}

	/**
	 * @param periodo the periodo to set
	 * @struts.validator type = "required"
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return this.regionList;
	}

	/**
	 * @param regionList the regionList to set
	 *  
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}	

}
