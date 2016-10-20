package biz.belcorp.ssicc.web.spusicc.pre.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPREMatrizRecuperacionesSearchForm extends BaseSearchForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2420732453188652932L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoVentaPrincipal;
	private String codigoVentaRecuperar;
	private String indicadorActivo;
	private String numUnidMaxRecu;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * 
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
	 * 
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
	 * @return the codigoVentaRecuperar
	 */
	public String getCodigoVentaRecuperar() {
		return codigoVentaRecuperar;
	}
	/**
	 * @param codigoVentaRecuperar the codigoVentaRecuperar to set
	 */
	public void setCodigoVentaRecuperar(String codigoVentaRecuperar) {
		this.codigoVentaRecuperar = codigoVentaRecuperar;
	}
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 * 
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the numUnidMaxRecu
	 */
	public String getNumUnidMaxRecu() {
		return numUnidMaxRecu;
	}
	/**
	 * @param numUnidMaxRecu the numUnidMaxRecu to set
	 */
	public void setNumUnidMaxRecu(String numUnidMaxRecu) {
		this.numUnidMaxRecu = numUnidMaxRecu;
	}
	
	
}