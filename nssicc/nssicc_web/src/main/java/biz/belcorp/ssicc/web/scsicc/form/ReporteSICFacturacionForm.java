package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSICFacturacionForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 12/05/2014
 */
public class ReporteSICFacturacionForm extends BaseReporteForm {
	
	/** The codigo region. */
	private String[] codigoRegion;

	/** The codigo periodo. */
	private String codigoPeriodo;

	/** The codigo pais. */
	private String codigoPais;

	/** The descripcion region. */
	private String descripcionRegion;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fecha inicio facturacion. */
	private String fechaInicioFacturacion;

	/** The fecha fin facturacion. */
	private String fechaFinFacturacion;
	
	/** The fecha inicio facturacion Date. */
	private Date fechaInicioFacturacionDate;

	/** The fecha fin facturacion Date. */
	private Date fechaFinFacturacionDate;
	
	/** The tipo reporte. */
	private String tipoReporte;
	
	/** The codigo zona. */
	private String[] codigoZona;
	
	/** The tipo venta. */
	private String tipoVenta;

	/**
	 * @return the tipoVenta
	 */
	public String getTipoVenta() {
		return tipoVenta;
	}

	/**
	 * @param tipoVenta the tipoVenta to set
	 */
	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
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
	 *            The codigoRegion to set.
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
	 *            The descripcionRegion to set.
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
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String[] getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
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