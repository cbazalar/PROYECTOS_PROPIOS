package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ConsultaHIPHistoricoVinculosForm extends BaseSearchForm{

	private static final long serialVersionUID = -1868471562673253294L;
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;

	private String[] oidTiposVinculos;

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

	/**
	 * @return the oidTiposVinculos
	 */
	public String[] getOidTiposVinculos() {
		return oidTiposVinculos;
	}

	/**
	 * @param oidTiposVinculos the oidTiposVinculos to set
	 */
	public void setOidTiposVinculos(String[] oidTiposVinculos) {
		this.oidTiposVinculos = oidTiposVinculos;
	}

}
