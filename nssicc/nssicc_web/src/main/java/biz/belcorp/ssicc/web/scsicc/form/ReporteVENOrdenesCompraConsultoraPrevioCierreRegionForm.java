package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 13/11/2014
 */
public class ReporteVENOrdenesCompraConsultoraPrevioCierreRegionForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPeriodo;	
	private String fechaDesde;
	private String fechaHasta;	
	private String[] regionList;
	private String[] zonaList;
	private String[] territorioList;
	private String[] seccionList;		
	private String descripcionRegionList;
	private String descripcionZonaList;
	private String descripcionTerritorioList;
	private String descripcionSeccionList;
	private Date fechaDesdeD;
	private Date fechaHastaD;
	
	
	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type="required"
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
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return Returns the fechaDesde.
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde
	 *            The fechaDesde to set.
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return Returns the fechaHasta.
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta
	 *            The fechaHasta to set.
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	
	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return Returns the territorioList.
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList
	 *            The territorioList to set.
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}	
	
	/**
	 * @return Returns the seccionList.
	 */
	public String[] getSeccionList() {
		return seccionList;
	}
	/**
	 * @param seccionList The seccionList to set.
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}
	
	
	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	/**
	 * @param descripcionRegionList The descripcionRegionList to set.
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&","\n" );
		this.descripcionRegionList = temp;
	}
	/**
	 * @return Returns the descripcionTerritorioList.
	 */
	public String getDescripcionTerritorioList() {
		return descripcionTerritorioList;
	}
	/**
	 * @param descripcionTerritorioList The descripcionTerritorioList to set.
	 */
	public void setDescripcionTerritorioList(String descripcionTerritorioList) {
		String temp = StringUtils.replace(descripcionTerritorioList, "&&","\n" );
		this.descripcionTerritorioList = temp;
	}
	
	/**
	 * @return Returns the descripcionZonaList.
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	/**
	 * @param descripcionZonaList The descripcionZonaList to set.
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		String temp = StringUtils.replace(descripcionZonaList, "&&","\n" );
		this.descripcionZonaList = temp;
	}
	/**
	 * @return Returns the descripcionSeccionList.
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}
	/**
	 * @param descripcionSeccionList The descripcionSeccionList to set.
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		String temp = StringUtils.replace(descripcionSeccionList, "&&","\n" );
		this.descripcionSeccionList = temp;
	}

	/**
	 * @return the fechaDesdeD
	 */
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}

	/**
	 * @param fechaDesdeD the fechaDesdeD to set
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
	 * @param fechaHastaD the fechaHastaD to set
	 */
	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}

}
