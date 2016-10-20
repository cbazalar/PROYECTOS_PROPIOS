/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazMYERecepcionarActivacionFlexipagoWebForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 * 
 * @struts.form name = "interfazMYERecepcionarActivacionFlexipagoWebForm" extends = "baseInterfazForm"
 */
public class InterfazMYERecepcionarActivacionFlexipagoWebForm extends BaseInterfazForm	implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String pesoArchivo;

	/**
	 * @return the pesoArchivo
	 */
	public String getPesoArchivo() {
		return pesoArchivo;
	}

	/**
	 * @param pesoArchivo the pesoArchivo to set
	 */
	public void setPesoArchivo(String pesoArchivo) {
		this.pesoArchivo = pesoArchivo;
	}
}