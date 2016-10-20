package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCUnidadAdministrativaForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5173524053055669940L;
	private String oidSubgerencia;
	private String descripcionSubgerencia;
	private String [] regiones;
	private String [] zonas;
	private boolean indActualizarAmbitoGeografico;
	private String[] selectedItems;
	/**
	 * @return the oidSubgerencia
	 */
	public String getOidSubgerencia() {
		return oidSubgerencia;
	}
	/**
	 * @param oidSubgerencia the oidSubgerencia to set
	 */
	public void setOidSubgerencia(String oidSubgerencia) {
		this.oidSubgerencia = oidSubgerencia;
	}
	/**
	 * @return the descripcionSubgerencia
	 */
	public String getDescripcionSubgerencia() {
		return descripcionSubgerencia;
	}
	/**
	 * @param descripcionSubgerencia the descripcionSubgerencia to set
	 */
	public void setDescripcionSubgerencia(String descripcionSubgerencia) {
		this.descripcionSubgerencia = descripcionSubgerencia;
	}
	/**
	 * @return the regiones
	 */
	public String[] getRegiones() {
		return regiones;
	}
	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}
	/**
	 * @return the zonas
	 */
	public String[] getZonas() {
		return zonas;
	}
	/**
	 * @param zonas the zonas to set
	 */
	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}
	/**
	 * @return the indActualizarAmbitoGeografico
	 */
	public boolean isIndActualizarAmbitoGeografico() {
		return indActualizarAmbitoGeografico;
	}
	/**
	 * @param indActualizarAmbitoGeografico the indActualizarAmbitoGeografico to set
	 */
	public void setIndActualizarAmbitoGeografico(
			boolean indActualizarAmbitoGeografico) {
		this.indActualizarAmbitoGeografico = indActualizarAmbitoGeografico;
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
	
	

}
