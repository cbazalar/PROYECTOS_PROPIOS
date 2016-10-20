/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazMyEbelMovimientosCuentaCorrienteForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 *  
 * @struts.form
 *   name = "interfazMyEbelMovimientosCuentaCorrienteForm"
 * 	 extends = "baseInterfazForm"	
 * @deprecated
 */

public class InterfazMyEbelMovimientosCuentaCorrienteForm extends BaseInterfazForm implements Serializable{
	private String codigoCanal;
	private String codigoAcceso;
	private String codigoSubacceso;

	
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoSubacceso() {
		return codigoSubacceso;
	}

	public void setCodigoSubacceso(String codigoSubacceso) {
		this.codigoSubacceso = codigoSubacceso;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
    	this.codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
    	this.codigoSubacceso = Constants.CODIGO_SUBACCESO_DEFAULT;
    }
}
