package biz.belcorp.ssicc.web.spusicc.pre.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPREMatrizAlternativosSearchForm extends BaseSearchForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoVentaPrincipal;
	private String codigoVentaAlternativo;
	private String indicadorActivo;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoVentaPrincipal
	 */
	public String getCodigoVentaPrincipal() {
		return codigoVentaPrincipal;
	}
	/**
	 * @param codigoVentaPrincipal the codigoVentaPrincipal to set
	 */
	public void setCodigoVentaPrincipal(String codigoVentaPrincipal) {
		this.codigoVentaPrincipal = codigoVentaPrincipal;
	}
	/**
	 * @return the codigoVentaAlternativo
	 */
	public String getCodigoVentaAlternativo() {
		return codigoVentaAlternativo;
	}
	/**
	 * @param codigoVentaAlternativo the codigoVentaAlternativo to set
	 */
	public void setCodigoVentaAlternativo(String codigoVentaAlternativo) {
		this.codigoVentaAlternativo = codigoVentaAlternativo;
	}
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 * @struts.validator type="required"
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
}