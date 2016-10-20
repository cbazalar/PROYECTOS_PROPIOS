package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * 
 * <p>
 * <a href="ConsultaHIPFacturacionAdicionalForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="cchata@sigcomt.com">Carlos Chata</a>
 * 
 * @struts.form name = "consultaHIPFacturacionAdicionalForm"
 * 
 */
public class ConsultaHIPFacturacionAdicionalForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;

	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
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
	/**
	 * @return the desRegZonTerri
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}
	/**
	 * @param desRegZonTerri the desRegZonTerri to set
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}
}
