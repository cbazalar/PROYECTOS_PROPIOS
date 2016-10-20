/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author Jose Luis Rodriguez
 * 
 */
public class ConsultaPEDResultadoChequeoConsultoraForm extends BaseSearchForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3623333348770082005L;
	private String codigoPais;
	private String codigoConsultora;
	private String descripcionConsultora;
	private String codigoPeriodoInicial;	
	private String codigoPeriodoFinal;


	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the descripcionConsultora
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}

	/**
	 * @param descripcionConsultora the descripcionConsultora to set
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}

	/**
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial the codigoPeriodoInicial to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal the codigoPeriodoFinal to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}
	
	
}
