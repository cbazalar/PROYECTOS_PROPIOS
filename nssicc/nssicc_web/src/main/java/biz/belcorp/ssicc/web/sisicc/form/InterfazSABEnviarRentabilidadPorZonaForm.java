/*
 * Created on 14-dic-2005
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
 * <a href="InterfazSABEnviarRentabilidadPorZonaForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 * @struts.form
 *   name = "interfazSABEnviarRentabilidadPorZonaForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazSABEnviarRentabilidadPorZonaForm extends BaseInterfazForm implements Serializable {
	
	private static final long serialVersionUID = -9135310700865114785L;
	
	private String codigoSociedad;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoPeriodo;
		
	
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
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}	
}
