package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCClasificacionParticipanteForm extends BaseEditForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8409962564273669342L;
	private String oidClasificacion;
	private String[] selectedItems;
	private String excluirClasificacion;
	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}
	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}
	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}
	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	/**
	 * @return the excluirClasificacion
	 */
	public String getExcluirClasificacion() {
		return excluirClasificacion;
	}
	/**
	 * @param excluirClasificacion the excluirClasificacion to set
	 */
	public void setExcluirClasificacion(String excluirClasificacion) {
		this.excluirClasificacion = excluirClasificacion;
	}
	
	

}
