package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazHIPRecepcionarRegistroVentasForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 * @struts.form name = "interfazHIPRecepcionarRegistroVentasForm" extends = "baseInterfazForm"
 */
public class InterfazHIPRecepcionarRegistroVentasForm extends BaseInterfazForm
		implements Serializable {

	private static final long serialVersionUID = 7678457351684104230L;
	
	private String pesoArchivo;
	private String codigoSociedad;

	
	public String getPesoArchivo() {
		return pesoArchivo;
	}
	public void setPesoArchivo(String pesoArchivo) {
		this.pesoArchivo = pesoArchivo;
	}
	
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
}