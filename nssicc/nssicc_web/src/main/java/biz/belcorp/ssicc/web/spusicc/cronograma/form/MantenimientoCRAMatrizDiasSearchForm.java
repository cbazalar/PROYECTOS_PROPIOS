package biz.belcorp.ssicc.web.spusicc.cronograma.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCRAMatrizDiasSearchForm extends BaseSearchForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 947301921021001540L;
	private String codigoPais;
	private String[] grupoZona;
	private String[] actividad;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the grupoZona
	 */
	public String[] getGrupoZona() {
		return grupoZona;
	}
	/**
	 * @param grupoZona the grupoZona to set
	 */
	public void setGrupoZona(String[] grupoZona) {
		this.grupoZona = grupoZona;
	}
	/**
	 * @return the actividad
	 */
	public String[] getActividad() {
		return actividad;
	}
	/**
	 * @param actividad the actividad to set
	 */
	public void setActividad(String[] actividad) {
		this.actividad = actividad;
	}
	
	

}
