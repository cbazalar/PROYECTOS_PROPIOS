package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSICFacturacionEcuadorForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 13/05/2014
 */
public class ReporteSICFacturacionEcuadorForm extends BaseReporteForm {

	private String[] codigoRegion;

	private String codigoPeriodo;

	private String codigoPais;

	private String descripcionRegion;

	private static final long serialVersionUID = 1L;

	private String fechaInicioFacturacion;

	private String fechaFinFacturacion;
	
	private Date fechaInicioFacturacionDate;

	private Date fechaFinFacturacionDate;

	/**
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

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return Returns the fechaFinFacturacion.
	 */
	public String getFechaFinFacturacion() {
		return fechaFinFacturacion;
	}

	/**
	 * @param fechaFinFacturacion
	 */
	public void setFechaFinFacturacion(String fechaFinFacturacion) {
		this.fechaFinFacturacion = fechaFinFacturacion;
	}

	/**
	 * @return Returns the fechaInicioFacturacion.
	 */
	public String getFechaInicioFacturacion() {
		return fechaInicioFacturacion;
	}

	/**
	 * @param fechaInicioFacturacion
	 */
	public void setFechaInicioFacturacion(String fechaInicioFacturacion) {
		this.fechaInicioFacturacion = fechaInicioFacturacion;
	}

	/**
	 * @return the fechaInicioFacturacionDate
	 */
	public Date getFechaInicioFacturacionDate() {
		return fechaInicioFacturacionDate;
	}

	/**
	 * @param fechaInicioFacturacionDate the fechaInicioFacturacionDate to set
	 */
	public void setFechaInicioFacturacionDate(Date fechaInicioFacturacionDate) {
		this.fechaInicioFacturacionDate = fechaInicioFacturacionDate;
	}

	/**
	 * @return the fechaFinFacturacionDate
	 */
	public Date getFechaFinFacturacionDate() {
		return fechaFinFacturacionDate;
	}

	/**
	 * @param fechaFinFacturacionDate the fechaFinFacturacionDate to set
	 */
	public void setFechaFinFacturacionDate(Date fechaFinFacturacionDate) {
		this.fechaFinFacturacionDate = fechaFinFacturacionDate;
	}
	
	

}