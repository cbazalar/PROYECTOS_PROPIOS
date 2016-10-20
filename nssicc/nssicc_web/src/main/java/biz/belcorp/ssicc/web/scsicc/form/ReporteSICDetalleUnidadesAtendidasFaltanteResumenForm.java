package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/05/2014
 */
public class ReporteSICDetalleUnidadesAtendidasFaltanteResumenForm extends	BaseReporteForm {
	private static final long serialVersionUID = 1L;

	private String codigoSap;

	private String descripcionCorta;

	private Date fechaFacturacion;

	private String codigoPeriodo;

	private String codigoPais;

	/**
	 * @return Returns the codigoSap.
	 */
	public String getCodigoSap() {
		return codigoSap;
	}

	/**
	 * @param codigoSap
	 *            The codigoSap to set.
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	/**
	 * @return Returns the descripcionCorta.
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * @param descripcionCorta
	 *            The descripcionCorta to set.
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
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

	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}


}
