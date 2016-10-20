package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSICPedidosDigitadosZonaForm extends	BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;	
	private String periodo;	
	private String[] regionList;

	public ReporteSICPedidosDigitadosZonaForm() {
		super();
	}
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return this.codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
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
