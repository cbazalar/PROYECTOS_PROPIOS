package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteOCRPrimerosPedidosForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/08/2014
 */
public class ReporteOCRPrimerosPedidosForm extends BaseReporteForm{
	
	private static final long serialVersionUID = 1L;
	
	private String[] codigoRegion;

	private String[] codigoZona;

	private String codigoPeriodo;

	private String codigoPais;

	private String tipoReporte;

	private String descripcionRegion;

	private String agrupamientoReporte;
	
	private Date fechaReporteDate;
	
	private Date fechaFinDate;
	
	private String fechaReporte;	
	
	private String fechaFin;	
	
	
	public ReporteOCRPrimerosPedidosForm() {
		super();
	}

	/**
	 * @return Returns the fechaReporte.
	 */
	public String getFechaReporte() {
		return fechaReporte;
	}

	/**
	 * @param fechaReporte
	 *            The fechaReporte to set.
	 */
	public void setFechaReporte(String fechaReporte) {
		this.fechaReporte = fechaReporte;
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
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the codigoZona.
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String[] codigoZona) {
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
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaReporteDate() {
		return fechaReporteDate;
	}

	public void setFechaReporteDate(Date fechaReporteDate) {
		this.fechaReporteDate = fechaReporteDate;
	}

	public Date getFechaFinDate() {
		return fechaFinDate;
	}

	public void setFechaFinDate(Date fechaFinDate) {
		this.fechaFinDate = fechaFinDate;
	}
}