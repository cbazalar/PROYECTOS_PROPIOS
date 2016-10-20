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
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazPEREnviarSolicitudesMonetariasGeneForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 * @struts.form name = "interfazPEREnviarSolicitudesMonetariasGeneForm" extends = "baseInterfazForm"
 */
public class InterfazPEREnviarSolicitudesMonetariasGeneForm extends BaseInterfazForm
		implements Serializable {

	
	private String numeroLoteSolicitud;
	


	/**
	 * @return Returns the numeroLoteSolicitud.
	 */
	public String getNumeroLoteSolicitud() {
		return numeroLoteSolicitud;
	}


	/**
	 * @param numeroLoteSolicitud The numeroLoteSolicitud to set.
	 */
	public void setNumeroLoteSolicitud(String numeroLoteSolicitud) {
		this.numeroLoteSolicitud = numeroLoteSolicitud;
	}


}
