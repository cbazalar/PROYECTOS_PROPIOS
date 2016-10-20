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
 * <a href="InterfazPEREnviarSolicitudesMonetariasForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Principe</a>
 * 
 * @struts.form name = "interfazPEREnviarSolicitudesMonetariasForm" extends = "baseInterfazForm"
 */
public class InterfazPEREnviarSolicitudesMonetariasForm extends BaseInterfazForm
		implements Serializable {

	
	private static final long serialVersionUID = -7966772203718425940L;

	private String codigoTipoOrigenDatos;	
	private String numeroLoteSolicitud;
	
	public String getNumeroLoteSolicitud() {
		return numeroLoteSolicitud;
	}

	public void setNumeroLoteSolicitud(String numeroLoteSolicitud) {
		this.numeroLoteSolicitud = numeroLoteSolicitud;
	}
	
	public String getCodigoTipoOrigenDatos() {
		return codigoTipoOrigenDatos;
	}

	public void setCodigoTipoOrigenDatos(String codigoTipoOrigenDatos) {
		this.codigoTipoOrigenDatos = codigoTipoOrigenDatos;
	}



}
