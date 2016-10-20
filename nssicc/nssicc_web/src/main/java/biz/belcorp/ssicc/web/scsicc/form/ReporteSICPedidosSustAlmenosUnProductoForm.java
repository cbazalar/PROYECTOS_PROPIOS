package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSICPedidosSustAlmenosUnProductoForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 15/05/2014
 */
public class ReporteSICPedidosSustAlmenosUnProductoForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String periodo;
	
	private String fechaFacturacion;
	
	private String codigoSap;
	
	private String codigoSapReemplazo;
	
	private String cuvReemplazo;
	
	private String codigoVentaReemplazo;
	
	private String descVentaReemplazo;
	
	private String cuvReemplazado;
	
	private String codigoVentaReemplazado;
	
	private String descVentaReemplazado;

	private String region;
	
	private String[] regionList;

	private String zona;
	
	private String[] zonaList;
	
	private Date fechaFacturacionDate;

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
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return this.fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the codigoSap
	 */
	public String getCodigoSap() {
		return this.codigoSap;
	}

	/**
	 * @param codigoSap the codigoSap to set
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	/**
	 * @return the codigoSapReemplazo
	 */
	public String getCodigoSapReemplazo() {
		return this.codigoSapReemplazo;
	}

	/**
	 * @param codigoSapReemplazo the codigoSapReemplazo to set
	 */
	public void setCodigoSapReemplazo(String codigoSapReemplazo) {
		this.codigoSapReemplazo = codigoSapReemplazo;
	}

	/**
	 * @return the cuvReemplazo
	 */
	public String getCuvReemplazo() {
		return this.cuvReemplazo;
	}

	/**
	 * @param cuvReemplazo the cuvReemplazo to set
	 */
	public void setCuvReemplazo(String cuvReemplazo) {
		this.cuvReemplazo = cuvReemplazo;
	}

	/**
	 * @return the codigoVentaReemplazo
	 */
	public String getCodigoVentaReemplazo() {
		return this.codigoVentaReemplazo;
	}

	/**
	 * @param codigoVentaReemplazo the codigoVentaReemplazo to set
	 */
	public void setCodigoVentaReemplazo(String codigoVentaReemplazo) {
		this.codigoVentaReemplazo = codigoVentaReemplazo;
	}

	/**
	 * @return the descVentaReemplazo
	 */
	public String getDescVentaReemplazo() {
		return this.descVentaReemplazo;
	}

	/**
	 * @param descVentaReemplazo the descVentaReemplazo to set
	 */
	public void setDescVentaReemplazo(String descVentaReemplazo) {
		this.descVentaReemplazo = descVentaReemplazo;
	}

	/**
	 * @return the cuvReemplazado
	 */
	public String getCuvReemplazado() {
		return this.cuvReemplazado;
	}

	/**
	 * @param cuvReemplazado the cuvReemplazado to set
	 */
	public void setCuvReemplazado(String cuvReemplazado) {
		this.cuvReemplazado = cuvReemplazado;
	}

	/**
	 * @return the codigoVentaReemplazado
	 */
	public String getCodigoVentaReemplazado() {
		return this.codigoVentaReemplazado;
	}

	/**
	 * @param codigoVentaReemplazado the codigoVentaReemplazado to set
	 */
	public void setCodigoVentaReemplazado(String codigoVentaReemplazado) {
		this.codigoVentaReemplazado = codigoVentaReemplazado;
	}

	/**
	 * @return the descVentaReemplazado
	 */
	public String getDescVentaReemplazado() {
		return this.descVentaReemplazado;
	}

	/**
	 * @param descVentaReemplazado the descVentaReemplazado to set
	 */
	public void setDescVentaReemplazado(String descVentaReemplazado) {
		this.descVentaReemplazado = descVentaReemplazado;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return this.region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return this.regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zona
	 */
	public String getZona() {
		return this.zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return this.zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the fechaFacturacionDate
	 */
	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	/**
	 * @param fechaFacturacionDate the fechaFacturacionDate to set
	 */
	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
}