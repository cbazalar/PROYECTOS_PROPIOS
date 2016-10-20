package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePERResumenDiarioPercepcionesSunatForm extends BaseReporteForm {

	private static final long serialVersionUID = 689190061286084493L;
	
	private String codigoPais;
	private String fechaGeneracion;
	private Date fechaGeneracionDate;
	
	
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
	 * @return the fechaGeneracion
	 */
	public String getFechaGeneracion() {
		return fechaGeneracion;
	}
	/**
	 * @param fechaGeneracion the fechaGeneracion to set
	 */
	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	/**
	 * @return the fechaGeneracionDate
	 */
	public Date getFechaGeneracionDate() {
		return fechaGeneracionDate;
	}
	/**
	 * @param fechaGeneracionDate the fechaGeneracionDate to set
	 */
	public void setFechaGeneracionDate(Date fechaGeneracionDate) {
		this.fechaGeneracionDate = fechaGeneracionDate;
	}	
	
	

}
