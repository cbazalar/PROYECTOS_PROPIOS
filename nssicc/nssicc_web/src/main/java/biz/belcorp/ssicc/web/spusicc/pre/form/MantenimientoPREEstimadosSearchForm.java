package biz.belcorp.ssicc.web.spusicc.pre.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPREEstimadosSearchForm extends BaseSearchForm{
	
	private static final long serialVersionUID = -1455596741664785139L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoCatalago;
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
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
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoCatalago
	 */
	public String getCodigoCatalago() {
		return codigoCatalago;
	}
	/**
	 * @param codigoCatalago the codigoCatalago to set
	 */
	public void setCodigoCatalago(String codigoCatalago) {
		this.codigoCatalago = codigoCatalago;
	}
	
	

}
