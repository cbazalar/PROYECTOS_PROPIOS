package biz.belcorp.ssicc.web.framework.base.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public class BaseSearchForm extends BaseForm {

	private static final long serialVersionUID = 4614846556425574028L;
	protected String codigoPais;
	protected String codigoPeriodo;
	private String anyoPeriodo;
  

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getAnyoPeriodo() {
		return anyoPeriodo;
	}

	public void setAnyoPeriodo(String anyoPeriodo) {
		this.anyoPeriodo = anyoPeriodo;
	}
	
	
	
}