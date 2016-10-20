package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
public class ConsultaHIPSolicitudesPolizaForm extends BaseSearchForm {

	private static final long serialVersionUID = 2374403682870451486L;
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;

	private String numeroPoliza;

	private boolean mostrarLinks;

	/**
	 * @return the codConsultora
	 */
	public String getCodConsultora() {
		return codConsultora;
	}

	/**
	 * @param codConsultora
	 *            the codConsultora to set
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
	 * @param nomConsultora
	 *            the nomConsultora to set
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
	 * @param desRegZonTerri
	 *            the desRegZonTerri to set
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}

	/**
	 * @return the mostrarLinks
	 */
	public boolean isMostrarLinks() {
		return mostrarLinks;
	}

	/**
	 * @param mostrarLinks
	 *            the mostrarLinks to set
	 */
	public void setMostrarLinks(boolean mostrarLinks) {
		this.mostrarLinks = mostrarLinks;
	}

	/**
	 * @return the numeroPoliza
	 */
	public String getNumeroPoliza() {
		return numeroPoliza;
	}

	/**
	 * @param numeroPoliza
	 *            the numeroPoliza to set
	 */
	public void setNumeroPoliza(String numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

}
