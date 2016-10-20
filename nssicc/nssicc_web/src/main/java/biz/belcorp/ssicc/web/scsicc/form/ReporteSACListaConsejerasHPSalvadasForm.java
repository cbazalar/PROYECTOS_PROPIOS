package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACListaConsejerasHPSalvadasForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 11/09/2014
 */
public class ReporteSACListaConsejerasHPSalvadasForm extends BaseReporteForm {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo pais. */
	private String codigoPais;

	/** The codigo periodo. */
	private String codigoPeriodo;

	/** The region list. */
	private String regionList;

	/** The zona list. */
	private String zonaList;
	
	/** The fecha proceso. */
	private String fechaProceso;
	
	/** The fecha proceso date. */
	private Date fechaProcesoDate;
	

	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}

	/**
	 * @return the regionList
	 */
	public String getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String zonaList) {
		this.zonaList = zonaList;
	}	
}
