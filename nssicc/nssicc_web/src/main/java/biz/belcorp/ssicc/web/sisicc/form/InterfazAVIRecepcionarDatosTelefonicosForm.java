package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form del Repcionar Logros Avi.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 * @struts.form name = "interfazAVIRecepcionarDatosTelefonicosForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazAVIRecepcionarDatosTelefonicosForm extends
		BaseInterfazForm implements Serializable {

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