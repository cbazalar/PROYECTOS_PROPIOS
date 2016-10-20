/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * 
 * @struts.form name = "interfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoComisionista;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTipoComisionista() {
		return tipoComisionista;
	}
	/**
	 * @struts.validator type = "required"
	 * @param tipoComisionista the tipoComisionista to set
	 */
	public void setTipoComisionista(String tipoComisionista) {
		this.tipoComisionista = tipoComisionista;
	}

	
}
