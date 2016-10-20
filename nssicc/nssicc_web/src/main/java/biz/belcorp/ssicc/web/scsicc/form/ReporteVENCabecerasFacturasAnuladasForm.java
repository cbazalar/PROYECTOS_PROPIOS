package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteVENCabecerasFacturasAnuladasForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	// -- Variables Instancia
	private String codigoPais;

	private String fechaInicio;

	private String fechaFin;

	private Date fechaInicioD;

	private Date fechaFinD;

	private String idiomaReporte;

	private String paisReporte;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the idiomaReporte
	 */
	public String getIdiomaReporte() {
		return idiomaReporte;
	}

	/**
	 * @param idiomaReporte
	 *            the idiomaReporte to set
	 */
	public void setIdiomaReporte(String idiomaReporte) {
		this.idiomaReporte = idiomaReporte;
	}

	/**
	 * @return the paisReporte
	 */
	public String getPaisReporte() {
		return paisReporte;
	}

	/**
	 * @param paisReporte
	 *            the paisReporte to set
	 */
	public void setPaisReporte(String paisReporte) {
		this.paisReporte = paisReporte;
	}

	/**
	 * @return the fechaInicioD
	 */
	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	/**
	 * @param fechaInicioD
	 *            the fechaInicioD to set
	 */
	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	/**
	 * @return the fechaFinD
	 */
	public Date getFechaFinD() {
		return fechaFinD;
	}

	/**
	 * @param fechaFinD
	 *            the fechaFinD to set
	 */
	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}
}