package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;




public class ReporteSTONovedadesBoletasForm extends BaseReporteForm {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;	
	private String codigoPeriodo;	
	private String codigoRegion;
	private String[] zonaList;
	private String codigoTipoOrden;
	private String codigoCompaniaTransporte;	
	private String tipoReporte;
		
	/**	 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return this.codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */	
	public String getCodigoPeriodo() {
		return this.codigoPeriodo;
	}

	

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoTipoOrden
	 */
	public String getCodigoTipoOrden() {
		return codigoTipoOrden;
	}

	/**
	 * @param codigoTipoOrden the codigoTipoOrden to set
	 */
	public void setCodigoTipoOrden(String codigoTipoOrden) {
		this.codigoTipoOrden = codigoTipoOrden;
	}

	/**
	 * @return the codigoCompaniaTransporte
	 */
	public String getCodigoCompaniaTransporte() {
		return codigoCompaniaTransporte;
	}

	/**
	 * @param codigoCompaniaTransporte the codigoCompaniaTransporte to set
	 */
	public void setCodigoCompaniaTransporte(String codigoCompaniaTransporte) {
		this.codigoCompaniaTransporte = codigoCompaniaTransporte;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}


	public void reset() {			
		codigoRegion="";
		codigoTipoOrden="";
		codigoCompaniaTransporte="";
		tipoReporte="";
	}
}
