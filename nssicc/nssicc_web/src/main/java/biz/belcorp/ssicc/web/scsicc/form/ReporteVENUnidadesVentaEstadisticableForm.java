package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
public class ReporteVENUnidadesVentaEstadisticableForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodo;

	private String fechaDesde;

	private String fechaHasta;

	private Date fechaDesdeD;

	private Date fechaHastaD;

	private String[] regionList;

	private String[] zonaList;

	private String[] territorioList;

	private String[] seccionList;

	private String descripcionRegionList;

	private String descripcionZonaList;

	private String descripcionTerritorioList;

	private String descripcionSeccionList;

	private String tipoTotal;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
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
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the territorioList
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList
	 *            the territorioList to set
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * @return the seccionList
	 */
	public String[] getSeccionList() {
		return seccionList;
	}

	/**
	 * @param seccionList
	 *            the seccionList to set
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}

	/**
	 * @return the descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * @param descripcionRegionList
	 *            the descripcionRegionList to set
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}

	/**
	 * @return the descripcionZonaList
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	/**
	 * @param descripcionZonaList
	 *            the descripcionZonaList to set
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}

	/**
	 * @return the descripcionTerritorioList
	 */
	public String getDescripcionTerritorioList() {
		return descripcionTerritorioList;
	}

	/**
	 * @param descripcionTerritorioList
	 *            the descripcionTerritorioList to set
	 */
	public void setDescripcionTerritorioList(String descripcionTerritorioList) {
		this.descripcionTerritorioList = descripcionTerritorioList;
	}

	/**
	 * @return the descripcionSeccionList
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}

	/**
	 * @param descripcionSeccionList
	 *            the descripcionSeccionList to set
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		this.descripcionSeccionList = descripcionSeccionList;
	}

	/**
	 * @return the tipoTotal
	 */
	public String getTipoTotal() {
		return tipoTotal;
	}

	/**
	 * @param tipoTotal
	 *            the tipoTotal to set
	 */
	public void setTipoTotal(String tipoTotal) {
		this.tipoTotal = tipoTotal;
	}

	/**
	 * @return the fechaDesdeD
	 */
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}

	/**
	 * @param fechaDesdeD
	 *            the fechaDesdeD to set
	 */
	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}

	/**
	 * @return the fechaHastaD
	 */
	public Date getFechaHastaD() {
		return fechaHastaD;
	}

	/**
	 * @param fechaHastaD
	 *            the fechaHastaD to set
	 */
	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}
}