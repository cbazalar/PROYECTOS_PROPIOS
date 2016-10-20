package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>

 */
public class ReporteRETDetalleComisionVentaRetailForm extends BaseReporteForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String fechaInicio;
	
	private Date fechaInicioDt;

	private String fechaFinal;
	
	private Date fechaFinalDt;
	
	
	
	public Date getFechaInicioDt() {
		return fechaInicioDt;
	}
	public void setFechaInicioDt(Date fechaInicioDt) {
		this.fechaInicioDt = fechaInicioDt;
	}
	public Date getFechaFinalDt() {
		return fechaFinalDt;
	}
	public void setFechaFinalDt(Date fechaFinalDt) {
		this.fechaFinalDt = fechaFinalDt;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	private String[] regionList;

	private String[] zonaList;

	public void reset() {
		this.regionList = this.zonaList = null;
	}
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

	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}


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
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	
}
