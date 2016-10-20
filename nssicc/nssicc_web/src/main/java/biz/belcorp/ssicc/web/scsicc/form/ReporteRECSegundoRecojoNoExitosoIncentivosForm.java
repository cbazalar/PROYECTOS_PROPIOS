/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteRECSegundoRecojoNoExitosoIncentivosForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String fechaInicio;
	
	private String fechaFin;
	
	private Date	dfechaInicio;
	
	private Date	dfechaFin;

	private String[] regionList;
	
	private String[] zonaList;
	
	private String descripcionRegionList;
	
	private String descripcionZonaList;

	public void reset() {
		
		this.fechaInicio="";
		this.fechaFin="";
	}
	
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
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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
	 * @return the dfechaInicio
	 */
	public Date getDfechaInicio() {
		return dfechaInicio;
	}

	/**
	 * @param dfechaInicio the dfechaInicio to set
	 */
	public void setDfechaInicio(Date dfechaInicio) {
		this.dfechaInicio = dfechaInicio;
	}
	/**
	 * @return the dfechaInicio
	 */
	public Date getDfechaFin() {
		return dfechaFin;
	}

	/**
	 * @param dfechaInicio the dfechaInicio to set
	 */
	public void setDfechaFin(Date dfechaFin) {
		this.dfechaFin = dfechaFin;
	}
	
}
