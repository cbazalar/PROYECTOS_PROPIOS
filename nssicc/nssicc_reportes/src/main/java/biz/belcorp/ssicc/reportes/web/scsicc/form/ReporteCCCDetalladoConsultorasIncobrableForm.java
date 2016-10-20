package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCDetalladoConsultorasIncobrableForm extends BaseReporteForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;	
	private String campanaHasta;
	private Date campanaHastaDate;
	private String FechaHasta;	
	private Date FechaHastaDate;
	private String[] regionList;	
	private String[] zonaList;
	private String[] seccionList;	
	private String descripcionRegionList;	
	private String descripcionZonaList;	
	private String descripcionSeccionList;
	
	/*
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}
	*/
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
	
	/**
	 * @return the campanaHasta
	 */
	public String getCampanaHasta() {
		return campanaHasta;
	}
	/**
	 * @param campanaHasta the campanaHasta to set
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict" value="${defaultDatePattern}"
	 */
	public void setCampanaHasta(String campanaHasta) {
		this.campanaHasta = campanaHasta;
	}
	
	/**
	 * @return the FechaHasta
	 */
	public String getFechaHasta() {
		return FechaHasta;
	}
	/**
	 * @param FechaHasta the FechaHasta to set
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict" value="${defaultDatePattern}"
	 */
	public void setFechaHasta(String fechaHasta) {
		FechaHasta = fechaHasta;
	}
	
	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}
	/**
	 * @param regionList the regionList to set
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
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	/**
	 * @return the seccionList
	 */
	public String[] getSeccionList() {
		return seccionList;
	}
	/**
	 * @param seccionList the seccionList to set
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
	 * @param descripcionRegionList the descripcionRegionList to set
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
	 * @param descripcionZonaList the descripcionZonaList to set
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}
	/**
	 * @return the descripcionSeccionList
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}
	/**
	 * @param  descripcionSeccionList the descripcionSeccionList to set
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		this.descripcionSeccionList = descripcionSeccionList;
	}
	public Date getCampanaHastaDate() {
		return campanaHastaDate;
	}
	public void setCampanaHastaDate(Date campanaHastaDate) {
		this.campanaHastaDate = campanaHastaDate;
	}
	public Date getFechaHastaDate() {
		return FechaHastaDate;
	}
	public void setFechaHastaDate(Date fechaHastaDate) {
		FechaHastaDate = fechaHastaDate;
	}
}