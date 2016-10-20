package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteSTODetalleEntregaForm extends BaseReporteForm	implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7077337707267788622L;

	private String codigoPais;	
	private String codigoPeriodo;	
	private String codigoRegion;
	private String[] zonaList;
	private String codigoTipoOrden;
	private String codigoCompaniaTransporte;	
	private String tipoReporte;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
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
	
	
}


