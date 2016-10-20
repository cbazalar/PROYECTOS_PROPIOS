package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteSICPedidosRecupAlMenosUnProductoForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 19/05/2014
 */
public class ReporteSICPedidosRecupAlMenosUnProductoForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String periodo;
	
	private String fechaFacturacion;
	
	private String codigoSap;
	
	private String codigoSapReemplazo;
	
	private String cuvRecuperacion;
	
	private String codigoVentaRecuperacion;
	
	private String descVentaRecuperacion;
	
	private String cuvRecuperado;
	
	private String codigoVentaRecuperado;
	
	private String descVentaRecuperado;

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
	 * @return the codigoVentaRecuperacion
	 */
	public String getCodigoVentaRecuperacion() {
		return this.codigoVentaRecuperacion;
	}

	/**
	 * @param codigoVentaRecuperacion the codigoVentaRecuperacion to set
	 */
	public void setCodigoVentaRecuperacion(String codigoVentaRecuperacion) {
		this.codigoVentaRecuperacion = codigoVentaRecuperacion;
	}

	/**
	 * @return the descVentaRecuperacion
	 */
	public String getDescVentaRecuperacion() {
		return this.descVentaRecuperacion;
	}

	/**
	 * @param descVentaRecuperacion the descVentaRecuperacion to set
	 */
	public void setDescVentaRecuperacion(String descVentaRecuperacion) {
		this.descVentaRecuperacion = descVentaRecuperacion;
	}

	/**
	 * @return the codigoVentaRecuperado
	 */
	public String getCodigoVentaRecuperado() {
		return this.codigoVentaRecuperado;
	}

	/**
	 * @param codigoVentaRecuperado the codigoVentaRecuperado to set
	 */
	public void setCodigoVentaRecuperado(String codigoVentaRecuperado) {
		this.codigoVentaRecuperado = codigoVentaRecuperado;
	}

	/**
	 * @return the descVentaRecuperado
	 */
	public String getDescVentaRecuperado() {
		return this.descVentaRecuperado;
	}

	/**
	 * @param descVentaRecuperado the descVentaRecuperado to set
	 */
	public void setDescVentaRecuperado(String descVentaRecuperado) {
		this.descVentaRecuperado = descVentaRecuperado;
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
	 * @return the cuvRecuperacion
	 */
	public String getCuvRecuperacion() {
		return this.cuvRecuperacion;
	}

	/**
	 * @param cuvRecuperacion the cuvRecuperacion to set
	 */
	public void setCuvRecuperacion(String cuvRecuperacion) {
		this.cuvRecuperacion = cuvRecuperacion;
	}

	/**
	 * @return the cuvRecuperado
	 */
	public String getCuvRecuperado() {
		return this.cuvRecuperado;
	}

	/**
	 * @param cuvRecuperado the cuvRecuperado to set
	 */
	public void setCuvRecuperado(String cuvRecuperado) {
		this.cuvRecuperado = cuvRecuperado;
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