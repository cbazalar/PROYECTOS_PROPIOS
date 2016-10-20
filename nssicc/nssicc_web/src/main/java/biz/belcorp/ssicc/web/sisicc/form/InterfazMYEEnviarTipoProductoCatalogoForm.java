/*
 * Created on 04-jun-2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazMYEEnviarTipoProductoCatalogoForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 * @struts.form
 *   name = "interfazMYEEnviarTipoProductoCatalogoForm"
 * 	 extends = "baseInterfazForm"	
*/

public class InterfazMYEEnviarTipoProductoCatalogoForm extends BaseInterfazForm implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoMarca;
	private String codigoCanal;
	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal The codigoCanal to set.
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
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}		
}
