package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteSACPedidoOrigenForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 22/09/2014
 */
public class ReporteSACPedidoOrigenForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;
	
	//-- Variables
	private String codigoPais;
	private String codigoPeriodoInicial;
	private String codigoPeriodoFinal;
	private String tipoReporte;
	private String tipoAgrupacion;
	private String codigoRegion;
	private String codigoZona;
	private String fechaInicial;
	private String fechaFinal;
	private Date fechaInicialDate;
	private Date fechaFinalDate;
	private String codigoOrigen;
	private String descripcionOrigen;
	private String formatoExportacion;
	private String tipoRepAgr;
	
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
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}
	/**
	 * @param codigoPeriodoInicial the codigoPeriodoInicial to set
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}
	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}
	/**
	 * @param codigoPeriodoFinal the codigoPeriodoFinal to set
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}
	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}
	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	/**
	 * @return the tipoAgrupacion
	 */
	public String getTipoAgrupacion() {
		return tipoAgrupacion;
	}
	/**
	 * @param tipoAgrupacion the tipoAgrupacion to set
	 */
	public void setTipoAgrupacion(String tipoAgrupacion) {
		this.tipoAgrupacion = tipoAgrupacion;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the fechaInicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}
	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}
	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	/**
	 * @return the codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}
	/**
	 * @param codigoOrigen the codigoOrigen to set
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}
	/**
	 * @return the descripcionOrigen
	 */
	public String getDescripcionOrigen() {
		return descripcionOrigen;
	}
	/**
	 * @param descripcionOrigen the descripcionOrigen to set
	 */
	public void setDescripcionOrigen(String descripcionOrigen) {
		this.descripcionOrigen = descripcionOrigen;
	}
	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}
	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}
	/**
	 * @return the tipoRepAgr
	 */
	public String getTipoRepAgr() {
		return tipoRepAgr;
	}
	/**
	 * @param tipoRepAgr the tipoRepAgr to set
	 */
	public void setTipoRepAgr(String tipoRepAgr) {
		this.tipoRepAgr = tipoRepAgr;
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
