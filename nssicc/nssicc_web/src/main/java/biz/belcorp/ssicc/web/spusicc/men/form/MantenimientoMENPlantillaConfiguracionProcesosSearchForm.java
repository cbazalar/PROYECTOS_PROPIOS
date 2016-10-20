package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoMENPlantillaConfiguracionProcesosSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3264928978967877809L;
	private String codigoPais;
	private String codigoPlantilla;
	private String descripcion;
	private String observaciones;
	private String indicadorActivo;
	private String indicadorGestionUsuario;
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
	 * @return the codigoPlantilla
	 */
	public String getCodigoPlantilla() {
		return codigoPlantilla;
	}
	/**
	 * @param codigoPlantilla the codigoPlantilla to set
	 */
	public void setCodigoPlantilla(String codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the indicadorGestionUsuario
	 */
	public String getIndicadorGestionUsuario() {
		return indicadorGestionUsuario;
	}
	/**
	 * @param indicadorGestionUsuario the indicadorGestionUsuario to set
	 */
	public void setIndicadorGestionUsuario(String indicadorGestionUsuario) {
		this.indicadorGestionUsuario = indicadorGestionUsuario;
	}
	
	

}
