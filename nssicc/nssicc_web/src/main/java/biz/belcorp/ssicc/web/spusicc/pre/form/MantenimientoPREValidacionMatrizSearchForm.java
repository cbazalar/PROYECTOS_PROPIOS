package biz.belcorp.ssicc.web.spusicc.pre.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPREValidacionMatrizSearchForm extends BaseSearchForm {
	
	private static final long serialVersionUID = 1L;	
	private String codigoPais;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
}