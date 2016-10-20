package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSICFacturacionMatrizForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 16/07/2014
 */
public class ReporteSICFacturacionMatrizForm extends BaseReporteForm {

	private String[] codigoRegion;
	private String codigoPeriodo;
	private String codigoPais;
	private String descripcionRegion;
	private static final long serialVersionUID = 1L;
	private String fechaInicioFacturacion;
	private String fechaFinFacturacion;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	public String getFechaFinFacturacion() {
		return fechaFinFacturacion;
	}
	public void setFechaFinFacturacion(String fechaFinFacturacion) {
		this.fechaFinFacturacion = fechaFinFacturacion;
	}
	public String getFechaInicioFacturacion() {
		return fechaInicioFacturacion;
	}
	public void setFechaInicioFacturacion(String fechaInicioFacturacion) {
		this.fechaInicioFacturacion = fechaInicioFacturacion;
	}
		
	
}
