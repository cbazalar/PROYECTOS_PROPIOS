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
 * Form del Envio de Venta por Campa�a de la Interfaz Leader List.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * 
 * @struts.form name = "interfazLLIEnviarVentaPeriodoForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazLLIEnviarVentaPeriodoForm extends BaseInterfazForm
		implements Serializable {

	private String periodo;

	private String codigoPais;

	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

}