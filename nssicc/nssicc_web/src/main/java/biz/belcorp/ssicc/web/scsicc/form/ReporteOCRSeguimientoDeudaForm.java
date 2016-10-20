package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteOCRSeguimientoDeudaForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 29/08/2014
 */
public class ReporteOCRSeguimientoDeudaForm extends BaseReporteForm {
	private static final long serialVersionUID = 1L;

	private String codigoRegion;

	private String codigoZona;

	private String codigoPeriodo;

	private String codigoPais;

	private String tipoReporte;

	private String descripcionRegion;

	private String agrupamientoReporte;
	
	private String estadoPedido;
	
	private String estadoDeuda ;

	private String tipoBloqueo;
	
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
	 * @return Returns the estadoDeuda.
	 */
	public String getEstadoDeuda() {
		return estadoDeuda;
	}

	/**
	 * @param estadoDeuda The estadoDeuda to set.
	 */
	public void setEstadoDeuda(String estadoDeuda) {
		this.estadoDeuda = estadoDeuda;
	}

	/**
	 * @return Returns the estadoPedido.
	 */
	public String getEstadoPedido() {
		return estadoPedido;
	}

	/**
	 * @param estadoPedido The estadoPedido to set.
	 */
	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public String getReporteExcel() {
		return reporteExcel;
	}

	public void setReporteExcel(String reporteExcel) {
		this.reporteExcel = reporteExcel;
	}
}
