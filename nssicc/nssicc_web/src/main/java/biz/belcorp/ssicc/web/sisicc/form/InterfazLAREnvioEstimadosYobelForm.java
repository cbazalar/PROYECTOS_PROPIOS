package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazLAREnvioEstimadosYobelForm.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 * 
 * @struts.form name = "interfazLAREnvioEstimadosYobelForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazLAREnvioEstimadosYobelForm extends BaseInterfazForm
        implements Serializable {

    private String codigoPeriodo;

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

    /**
     * @struts.validator type = "required"
     */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

   
}

