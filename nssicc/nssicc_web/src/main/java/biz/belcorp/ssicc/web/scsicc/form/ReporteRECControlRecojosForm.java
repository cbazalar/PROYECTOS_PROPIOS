/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteRECControlRecojosForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String fechaInicio;
	
	private String fechaFin;
	
    private Date dfechaInicio;
	
	private Date dfechaFin;

	private String[] regionList;
	
	private String[] zonaList;
	
	private String descripcionRegionList;
	
	private String descripcionZonaList;
	
	private String tipo;

		
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}


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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * @return the dfechaFin
	 */
	public Date getDfechaFin() {
		return dfechaFin;
	}

	/**
	 * @param dfechaFin the dfechaFin to set
	 */
	public void setDfechaFin(Date dfechaFin) {
		this.dfechaFin = dfechaFin;
	}	
	
	
	
	
}
