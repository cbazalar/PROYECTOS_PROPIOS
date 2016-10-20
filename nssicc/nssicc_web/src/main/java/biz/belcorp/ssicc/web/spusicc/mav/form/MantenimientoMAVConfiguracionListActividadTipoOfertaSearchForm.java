package biz.belcorp.ssicc.web.spusicc.mav.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoMAVConfiguracionListActividadTipoOfertaSearchForm  extends BaseSearchForm  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String tipoActividad;
	private String actividad;
	private String tipoOferta;
	
	
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
	 * @return the tipoActividad
	 */
	public String getTipoActividad() {
		return tipoActividad;
	}

	/**
	 * @param tipoActividad the tipoActividad to set
	 */
	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	/**
	 * @return the actividad
	 */
	public String getActividad() {
		return actividad;
	}

	/**
	 * @param actividad the actividad to set
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/**
	 * @return the tipoOferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * @param tipoOferta the tipoOferta to set
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}
}