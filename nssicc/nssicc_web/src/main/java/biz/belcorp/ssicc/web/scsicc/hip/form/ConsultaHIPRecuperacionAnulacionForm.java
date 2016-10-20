package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class ConsultaHIPRecuperacionAnulacionForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 04/02/2014
 */
public class ConsultaHIPRecuperacionAnulacionForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;
	
	private String codConsultora;
	private String nomConsultora;
	
	/**
	 * @return the codConsultora
	 */
	public String getCodConsultora() {
		return codConsultora;
	}
	/**
	 * @param codConsultora the codConsultora to set
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}
	/**
	 * @return the nomConsultora
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}
	/**
	 * @param nomConsultora the nomConsultora to set
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}
}