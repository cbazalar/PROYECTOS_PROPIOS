package biz.belcorp.ssicc.web.scsicc.form;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar
 *         La Rosa</a>
 * 
 */
public class ReporteVENVentaVariableForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;

	private String codigoSociedad;

	private String descripcionSociedad;
	
	private String codigoPeriodo;
	
	private String codigoPeriodoFinal;
	
	private String codigoAlmacen;

	private String descripcionAlmacen;
	
	private String codigoAnio;
	
	private String codigoRangoPeriodo;
	
	private String tipoPresentacion;
	
	private String tipoReporte;

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
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 *             The codigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return Returns the descripcionSociedad.
	 */
	public String getDescripcionSociedad() {
		return descripcionSociedad;
	}

	/**
	 * @param descripcionSociedad
	 *            The descripcionSociedad to set.
	 */
	public void setDescripcionSociedad(String descripcionSociedad) {
		this.descripcionSociedad = descripcionSociedad;
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
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal
	 *            The codigoPeriodoFinal to set.
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @param codigoAlmacen
	 *            The codigoAlmacen to set.
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}

	/**
	 * @param codigoAlmacen
	 *            The codigoAlmacen to set.
	 */	
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}

	/**
	 * @param descripcionAlmacen
	 *            The descripcionAlmacen to set.
	 */
	public String getDescripcionAlmacen() {
		return descripcionAlmacen;
	}

	public void setDescripcionAlmacen(String descripcionAlmacen) {
		this.descripcionAlmacen = descripcionAlmacen;
	}

	/**
	 * @return Returns the tipoPresentacion.
	 */
	public String getTipoPresentacion() {
		return tipoPresentacion;
	}

	/**
	 * @param tipoPresentacion The tipoPresentacion to set.
	 */
	public void setTipoPresentacion(String tipoPresentacion) {
		this.tipoPresentacion = tipoPresentacion;
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
	 * @return Returns the codigoAnio.
	 */
	public String getCodigoAnio() {
		return codigoAnio;
	}

	/**
	 * @param codigoAnio The codigoAnio to set.
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}

	/**
	 * @return Returns the codigoRangoPeriodo.
	 */
	public String getCodigoRangoPeriodo() {
		return codigoRangoPeriodo;
	}

	/**
	 * @param codigoRangoPeriodo The codigoRangoPeriodo to set.
	 */
	public void setCodigoRangoPeriodo(String codigoRangoPeriodo) {
		this.codigoRangoPeriodo = codigoRangoPeriodo;
	}




	
}
