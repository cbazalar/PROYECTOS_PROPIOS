package biz.belcorp.ssicc.web._ejemplos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author pecbazalar
 *
 */
public class MMantenimientoEjemploUsuarioSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 8784799075151988354L;
	
	private String codigoRol;
	
	/**
	 * @return the codigoRol
	 */
	public String getCodigoRol() {
		return codigoRol;
	}

	/**
	 * @param codigoRol the codigoRol to set
	 */
	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}
	
	
}
