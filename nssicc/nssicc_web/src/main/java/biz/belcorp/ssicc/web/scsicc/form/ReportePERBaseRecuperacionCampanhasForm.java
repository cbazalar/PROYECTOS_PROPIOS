package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePERBaseRecuperacionCampanhasForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;

	private String codigoSubgerencia;

	private String descripcionSubgerencia;

	private String codigoRegion;

	private String descripcionRegion;

	private String codigoZona;

	private String descripcionZona;

	private String codigoSeccion;

	private String descripcionSeccion;

	private String periodoDesde;

	private String periodoHasta;

	private String tipoVista;

//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		this.tipoVista = Constants.TIPO_VISTA_PAIS;
//	}

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
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
	 * @return Returns the codigoSeccion.
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion
	 *            The codigoSeccion to set.
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return Returns the codigoSubgerencia.
	 */
	public String getCodigoSubgerencia() {
		return codigoSubgerencia;
	}

	/**
	 * @param codigoSubgerencia
	 *            The codigoSubgerencia to set.
	 */
	public void setCodigoSubgerencia(String codigoSubgerencia) {
		this.codigoSubgerencia = codigoSubgerencia;
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
	 * @return Returns the descripcionCanal.
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal
	 *            The descripcionCanal to set.
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	/**
	 * @return Returns the descripcionMarca.
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca
	 *            The descripcionMarca to set.
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
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
	 * @return Returns the descripcionSeccion.
	 */
	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}

	/**
	 * @param descripcionSeccion
	 *            The descripcionSeccion to set.
	 */
	public void setDescripcionSeccion(String descripcionSeccion) {
		this.descripcionSeccion = descripcionSeccion;
	}

	/**
	 * @return Returns the descripcionSubgerencia.
	 */
	public String getDescripcionSubgerencia() {
		return descripcionSubgerencia;
	}

	/**
	 * @param descripcionSubgerencia
	 *            The descripcionSubgerencia to set.
	 */
	public void setDescripcionSubgerencia(String descripcionSubgerencia) {
		this.descripcionSubgerencia = descripcionSubgerencia;
	}

	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona
	 *            The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	/**
	 * @return Returns the periodoDesde.
	 */
	public String getPeriodoDesde() {
		return periodoDesde;
	}

	/**
	 * @param periodoDesde
	 *            The periodoDesde to set.
	 */
	public void setPeriodoDesde(String periodoDesde) {
		this.periodoDesde = periodoDesde;
	}

	/**
	 * @return Returns the periodoHasta.
	 */
	public String getPeriodoHasta() {
		return periodoHasta;
	}

	/**
	 * @param periodoHasta
	 *            The periodoHasta to set.
	 */
	public void setPeriodoHasta(String periodoHasta) {
		this.periodoHasta = periodoHasta;
	}

	/**
	 * @return Returns the tipoVista.
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 *            The tipoVista to set.
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}
}