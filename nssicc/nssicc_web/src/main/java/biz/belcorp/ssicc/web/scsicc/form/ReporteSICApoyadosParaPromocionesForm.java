package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * Form base para los Reportes SIC de ScSiCC.
 * 
 * @author <a href="">Marco Agurto</a>
 * 
 */
public class ReporteSICApoyadosParaPromocionesForm extends BaseReporteForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoSap;

	private String descripcionCorta;

	private String codigoCatalogo;
	
	private String paginaCatalogoDesde;
	
	private String paginaCatalogoHasta;

	private String codigoPeriodo;

	private String codigoVenta;

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
	 * @return Returns the codigoVenta.
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta
	 *            The codigoVenta to set.
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
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
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoCatalogo.
	 */
	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}

	/**
	 * @param codigoCatalogo The codigoCatalogo to set.
	 */
	public void setCodigoCatalogo(String codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}

	/**
	 * @return Returns the paginaCatalogoDesde.
	 */
	public String getPaginaCatalogoDesde() {
		return paginaCatalogoDesde;
	}

	/**
	 * @param paginaCatalogoDesde The paginaCatalogoDesde to set.
	 */
	public void setPaginaCatalogoDesde(String paginaCatalogoDesde) {
		this.paginaCatalogoDesde = paginaCatalogoDesde;
	}

	/**
	 * @return Returns the paginaCatalogoHasta.
	 */
	public String getPaginaCatalogoHasta() {
		return paginaCatalogoHasta;
	}

	/**
	 * @param paginaCatalogoHasta The paginaCatalogoHasta to set.
	 */
	public void setPaginaCatalogoHasta(String paginaCatalogoHasta) {
		this.paginaCatalogoHasta = paginaCatalogoHasta;
	}

}
