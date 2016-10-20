package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazACCRecepcionarRecomendantesRecomendadasForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 * @struts.form name = "interfazSGRRecepcionarPolizasCanceladasForm" extends = "baseInterfazForm"
 */
public class InterfazSGRRecepcionarPolizasCanceladasForm extends BaseInterfazForm
		implements Serializable {

	private static final long serialVersionUID = 3025039388395741004L;
	
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