package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


/**
 * <p>
 * <a href="InterfazRUVEnviarRegistroUnicoVentasForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 *
 * @author <a href="mailto:ttataje@csigcomt.com">Telly Tataje</a>
 *
 * @struts.form name = "interfazRUVEnviarRegistroUnicoVentasForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazRUVEnviarRegistroUnicoVentasForm extends BaseInterfazForm {
	
    /**
     * Holds value of property mes.
     */
	private String mes;

    /**
     * Holds value of property anho.
     */
	private String anho;

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	/**
	 *  @struts.validator type = "required"
	 * @param mes The mes to set.
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return the anho
	 */
	public String getAnho() {
		return anho;
	}

	/**
	 * @param anho the anho to set
	 */
	/**
	 *  @struts.validator type = "required"
	 * @param anho The anho to set.
	 */
	public void setAnho(String anho) {
		this.anho = anho;
	}
}