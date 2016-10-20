package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ConsultaHIPProgramasNuevasForm extends BaseSearchForm{

	private static final long serialVersionUID = 8125906626485948457L;
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	private String estatus;
	private String campanaIngreso;
	private String selectedItem;
	
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
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the status to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the campanaIngreso
	 */
	public String getCampanaIngreso() {
		return campanaIngreso;
	}
	/**
	 * @param campanaIngreso the campanaIngreso to set
	 */
	public void setCampanaIngreso(String campanaIngreso) {
		this.campanaIngreso = campanaIngreso;
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
	
			
	
}
