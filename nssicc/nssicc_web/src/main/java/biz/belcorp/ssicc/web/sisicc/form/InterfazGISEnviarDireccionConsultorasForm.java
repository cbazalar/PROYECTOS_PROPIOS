/*
 * Created on 26-dic-2005
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
 * <a href="InterfazGISEnviarDireccionConsultorasForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 * @struts.form
 *   name = "interfazGISEnviarDireccionConsultorasForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazGISEnviarDireccionConsultorasForm extends BaseInterfazForm implements Serializable {
	
	private static final long serialVersionUID = 508352490213422851L;
	
	private String codigoMarca;
	private String codigoCanal;
	private String codigoTipoCliente;
	private String codigoRegion;
	private String tipoAccion;
		
	
	public String getTipoAccion() {
		return tipoAccion;
	}
	
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
	
	public String getCodigoCanal() {
		return codigoCanal;
	}
	
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	
	public String getCodigoMarca() {
		return codigoMarca;
	}
	
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	public String getCodigoRegion() {
		return codigoRegion;
	}
	
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}
	
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}
	
}
