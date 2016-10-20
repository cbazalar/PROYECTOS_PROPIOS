package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */

public class ConsultaHIPCdrRechazadosForm extends BaseSearchForm {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 5904583214020749870L;
	
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	private String selectedItem;
	private String oidSolicCabecera;
	
	
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
	 * @return the selectedItem
	 */
	public String getSelectedItem() {
		return selectedItem;
	}
	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
	/**
	 * @return the oidSolicCabecera
	 */
	public String getOidSolicCabecera() {
		return oidSolicCabecera;
	}
	/**
	 * @param oidSolicCabecera the oidSolicCabecera to set
	 */
	public void setOidSolicCabecera(String oidSolicCabecera) {
		this.oidSolicCabecera = oidSolicCabecera;
	}
		
}
