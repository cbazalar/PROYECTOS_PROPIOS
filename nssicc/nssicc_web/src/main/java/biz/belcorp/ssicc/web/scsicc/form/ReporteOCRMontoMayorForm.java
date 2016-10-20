package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteOCRMontoMayorForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 14/08/2014
 */
public class ReporteOCRMontoMayorForm extends BaseReporteForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoRegion;

	private String codigoZona;

	private String codigoPeriodo;

	private String codigoPais;

	private String tipoReporte;

	private String descripcionRegion;

	private String agrupamientoReporte;

	private String tipoReporteCabeceraDetalle;

	private String formatoReporte;

	private String montoMayor;
	
	private String fechaInicial;
	 
	private String fechaFinal;
	
	private Date fechaInicialDate;
	
	private Date fechaFinalDate;

	/**
	 * @return Returns the formatoReporte.
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            The formatoReporte to set.
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return Returns the tipoReporteCabeceraDetalle.
	 */
	public String getTipoReporteCabeceraDetalle() {
		return tipoReporteCabeceraDetalle;
	}

	/**
	 * @param tipoReporteCabeceraDetalle
	 *            The tipoReporteCabeceraDetalle to set.
	 */
	public void setTipoReporteCabeceraDetalle(String tipoReporteCabeceraDetalle) {
		this.tipoReporteCabeceraDetalle = tipoReporteCabeceraDetalle;
	}	

	/**
	 * @return Returns the montoMayor.
	 */
	public String getMontoMayor() {
		return montoMayor;
	}

	/**
	 * @param montoMayor
	 *            The montoMayor to set.
	 * @return Returns the montoMayor.
	 */

	public void setMontoMayor(String montoMayor) {
		this.montoMayor = montoMayor;
	}

	/**
	 * @return Returns the agrupamientoReporte.
	 */
	public String getAgrupamientoReporte() {
		return agrupamientoReporte;
	}

	/**
	 * @param agrupamientoReporte The agrupamientoReporte to set.
	 */
	public void setAgrupamientoReporte(String agrupamientoReporte) {
		this.agrupamientoReporte = agrupamientoReporte;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
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
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return Returns the tipoReporte.
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte The tipoReporte to set.
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return Returns the fechaFinal.
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal The fechaFinal to set.
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return Returns the fechaInicial.
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial The fechaInicial to set.
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaInicialDate() {
		return fechaInicialDate;
	}

	public void setFechaInicialDate(Date fechaInicialDate) {
		this.fechaInicialDate = fechaInicialDate;
	}

	public Date getFechaFinalDate() {
		return fechaFinalDate;
	}

	public void setFechaFinalDate(Date fechaFinalDate) {
		this.fechaFinalDate = fechaFinalDate;
	}

}
