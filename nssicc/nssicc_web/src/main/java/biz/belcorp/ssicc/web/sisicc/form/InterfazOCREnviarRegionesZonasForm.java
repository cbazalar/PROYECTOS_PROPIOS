/*
 * Created on 30-nov-2005
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
 * <a href="InterfazOCREnviarRegionesZonasForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 * 
 * @struts.form
 *   name = "interfazOCREnviarRegionesZonasForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazOCREnviarRegionesZonasForm extends BaseInterfazForm implements Serializable{
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
	/* 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
	}
}
