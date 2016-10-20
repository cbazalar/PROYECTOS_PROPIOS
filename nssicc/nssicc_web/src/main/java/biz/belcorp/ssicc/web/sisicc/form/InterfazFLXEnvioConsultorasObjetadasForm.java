package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazFLXEnvioConsultorasObjetadasForm.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 * 
 * @struts.form name = "interfazFLXEnvioConsultorasObjetadasForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazFLXEnvioConsultorasObjetadasForm extends BaseInterfazForm
        implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3024903817565526307L;
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

