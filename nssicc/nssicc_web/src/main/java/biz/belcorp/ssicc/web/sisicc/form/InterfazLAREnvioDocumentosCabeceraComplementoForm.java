package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazLAREnvioDocumentosCabeceraComplementoForm.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 * @struts.form name = "interfazLAREnvioDocumentosCabeceraComplementoForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazLAREnvioDocumentosCabeceraComplementoForm extends BaseInterfazForm
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

