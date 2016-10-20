package biz.belcorp.ssicc.web.spusicc.pre.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPRETipoOfertaSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = -4836059514056092651L;
	private String codigoPais;
	private String codigoTipoOferta;
	private String descripcion;

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
	 * @return the codigoTipoOferta
	 */
	public String getCodigoTipoOferta() {
		return codigoTipoOferta;
	}

	/**
	 * @param codigoTipoOferta
	 *            the codigoTipoOferta to set
	 */
	public void setCodigoTipoOferta(String codigoTipoOferta) {
		this.codigoTipoOferta = codigoTipoOferta;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
