package biz.belcorp.ssicc.reportes.web.scsicc.form;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePEDSeguimientoConsultoraForm extends BaseReporteForm {
	
	/**
	 *  JPPS
	 */
	private static final long serialVersionUID = 3302766625939859146L;

	private String codigoPais;
	
	private String regionList;

	private String[] zonaList;
	
	private String[] estatusList;

	private String descripcionRegionList;
	
	private String descripcionZonaList;
	
	private String descripcionEstatusList;
	
//	private String region;
	

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}	
			
	public String getRegionList() {
		return regionList;
	}

	public void setRegionList(String regionList) {
		this.regionList = regionList;
	}

	public String[] getZonaList() {
		return zonaList;
	}

	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	
	public String[] getEstatusList() {
		return estatusList;
	}

	public void setEstatusList(String[] estatusList) {
		this.estatusList = estatusList;
	}
	
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&","\n" );
		this.descripcionRegionList = temp;
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
	 * @return Returns the descripcionEstatusList.
	 */
	public String getDescripcionEstatusList() {
		return descripcionEstatusList;
	}
	
	/**
	 * @param descripcionEstatusList The descripcionEstatusList to set.
	 */
	public void setDescripcionEstatusList(String descripcionEstatusList) {
		String temp = StringUtils.replace(descripcionEstatusList, "&&","\n" );
		this.descripcionEstatusList = temp;
	}

//	public String getRegion() {
//		return region;
//	}
//
//	public void setRegion(String region) {
//		this.region = region;
//	}
}
