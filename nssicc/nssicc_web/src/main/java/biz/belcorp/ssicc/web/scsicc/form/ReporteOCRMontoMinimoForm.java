package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteOCRMontoMinimoForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 12/08/2014
 */
public class ReporteOCRMontoMinimoForm extends BaseReporteForm {	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo region. */
	private String codigoRegion;

	/** The codigo zona. */
	private String codigoZona;

	/** The codigo periodo. */
	private String codigoPeriodo;

	/** The codigo pais. */
	private String codigoPais;

	/** The tipo reporte. */
	private String tipoReporte;

	/** The descripcion region. */
	private String descripcionRegion;

	/** The agrupamiento reporte. */
	private String agrupamientoReporte;

	/** The tipo reporte cabecera detalle. */
	private String tipoReporteCabeceraDetalle;

	/** The formato reporte. */
	private String formatoReporte;
	
	/** The fecha inicial date. */
	private Date fechaInicialDate;
	
	/** The fecha final date. */
	private Date fechaFinalDate;
	
	/** The fecha inicial. */
	private String fechaInicial;
	
	/** The fecha final. */
	private String fechaFinal;
	
	/** The sub reporte name. */
	private String subReporteName;
	
	/** The reporte excel. */
	private String reporteExcel;

	/**
	 * Instantiates a new reporte ocr monto minimo form.
	 */
	public ReporteOCRMontoMinimoForm() {
		super();
	}

	/**
	 * Gets the codigo region.
	 *
	 * @return the codigo region
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * Sets the codigo region.
	 *
	 * @param codigoRegion the new codigo region
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * Gets the codigo zona.
	 *
	 * @return the codigo zona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * Sets the codigo zona.
	 *
	 * @param codigoZona the new codigo zona
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#getCodigoPeriodo()
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#setCodigoPeriodo(java.lang.String)
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * Gets the codigo pais.
	 *
	 * @return the codigo pais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais the new codigo pais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * Gets the tipo reporte.
	 *
	 * @return the tipo reporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * Sets the tipo reporte.
	 *
	 * @param tipoReporte the new tipo reporte
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * Gets the descripcion region.
	 *
	 * @return the descripcion region
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * Sets the descripcion region.
	 *
	 * @param descripcionRegion the new descripcion region
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * Gets the agrupamiento reporte.
	 *
	 * @return the agrupamiento reporte
	 */
	public String getAgrupamientoReporte() {
		return agrupamientoReporte;
	}

	/**
	 * Sets the agrupamiento reporte.
	 *
	 * @param agrupamientoReporte the new agrupamiento reporte
	 */
	public void setAgrupamientoReporte(String agrupamientoReporte) {
		this.agrupamientoReporte = agrupamientoReporte;
	}

	/**
	 * Gets the tipo reporte cabecera detalle.
	 *
	 * @return the tipo reporte cabecera detalle
	 */
	public String getTipoReporteCabeceraDetalle() {
		return tipoReporteCabeceraDetalle;
	}

	/**
	 * Sets the tipo reporte cabecera detalle.
	 *
	 * @param tipoReporteCabeceraDetalle the new tipo reporte cabecera detalle
	 */
	public void setTipoReporteCabeceraDetalle(String tipoReporteCabeceraDetalle) {
		this.tipoReporteCabeceraDetalle = tipoReporteCabeceraDetalle;
	}

	/**
	 * Gets the formato reporte.
	 *
	 * @return the formato reporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * Sets the formato reporte.
	 *
	 * @param formatoReporte the new formato reporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
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

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getSubReporteName() {
		return subReporteName;
	}

	public void setSubReporteName(String subReporteName) {
		this.subReporteName = subReporteName;
	}

	public String getReporteExcel() {
		return reporteExcel;
	}

	public void setReporteExcel(String reporteExcel) {
		this.reporteExcel = reporteExcel;
	}
}
