package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form del Repcionar Logros Avi.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 * @struts.form name = "interfazAVIRecepcionarLogrosForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazAVIRecepcionarLogrosForm extends
		BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 5659868972341103526L;
	
	private String pesoArchivo;

	
	public String getPesoArchivo() {
		return pesoArchivo;
	}
	
	public void setPesoArchivo(String pesoArchivo) {
		this.pesoArchivo = pesoArchivo;
	}
	
}