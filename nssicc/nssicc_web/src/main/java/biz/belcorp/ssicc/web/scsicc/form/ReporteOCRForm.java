package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteOCRForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 14/08/2014
 */
public class ReporteOCRForm extends BaseReporteForm {
	
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
	
	/** The fecha inicial date. */
	private Date fechaInicialDate;
	
	/** The fecha final date. */
	private Date fechaFinalDate;
	
	/** The fecha inicial. */
	private String fechaInicial;
	
	/** The fecha final. */
	private String fechaFinal;
	
	/** The tipo bloqueo. */
	private String tipoBloqueo;
	
	/** The levantamiento. */
	private String levantamiento;
	
	private String reporteExcel;
	
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
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 *            The codigoRegion to set.
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
	 * @param codigoZona
	 *            The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the tipoReporte.
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            The tipoReporte to set.
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion
	 *            The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
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

	/**
	 * @return Returns the tipoBloqueo.
	 */
	public String getTipoBloqueo() {
		return tipoBloqueo;
	}

	/**
	 * @param tipoBloqueo The tipoBloqueo to set.
	 */
	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}

	/**
	 * @return Returns the levantamiento.
	 */
	public String getLevantamiento() {
		return levantamiento;
	}

	/**
	 * @param levantamiento The levantamiento to set.
	 */
	public void setLevantamiento(String levantamiento) {
		this.levantamiento = levantamiento;
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

	public String getReporteExcel() {
		return reporteExcel;
	}

	public void setReporteExcel(String reporteExcel) {
		this.reporteExcel = reporteExcel;
	}

}
