package biz.belcorp.ssicc.web.spusicc.cronograma.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCRAMatrizDiasPopupForm extends BaseEditForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4407907461545852976L;
	private String codigoPais;

	// private String descripcionActividad;
	private String nombreActividadOrigen;
	private String ordenActividadOrigen;
	private String orden;
	private String diasDesplazamiento;
	private String ordenDesplazado;
	private String nombreGrupoZona;
	private String oidGrupoZona;
	private String nombreActividad;

	private String oidMatrizDias;
	private String salirPantalla = "N";
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
	 * @return the nombreActividadOrigen
	 */
	public String getNombreActividadOrigen() {
		return nombreActividadOrigen;
	}
	/**
	 * @param nombreActividadOrigen the nombreActividadOrigen to set
	 */
	public void setNombreActividadOrigen(String nombreActividadOrigen) {
		this.nombreActividadOrigen = nombreActividadOrigen;
	}
	/**
	 * @return the ordenActividadOrigen
	 */
	public String getOrdenActividadOrigen() {
		return ordenActividadOrigen;
	}
	/**
	 * @param ordenActividadOrigen the ordenActividadOrigen to set
	 */
	public void setOrdenActividadOrigen(String ordenActividadOrigen) {
		this.ordenActividadOrigen = ordenActividadOrigen;
	}
	/**
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}
	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}
	/**
	 * @return the diasDesplazamiento
	 */
	public String getDiasDesplazamiento() {
		return diasDesplazamiento;
	}
	/**
	 * @param diasDesplazamiento the diasDesplazamiento to set
	 */
	public void setDiasDesplazamiento(String diasDesplazamiento) {
		this.diasDesplazamiento = diasDesplazamiento;
	}
	/**
	 * @return the ordenDesplazado
	 */
	public String getOrdenDesplazado() {
		return ordenDesplazado;
	}
	/**
	 * @param ordenDesplazado the ordenDesplazado to set
	 */
	public void setOrdenDesplazado(String ordenDesplazado) {
		this.ordenDesplazado = ordenDesplazado;
	}
	/**
	 * @return the nombreGrupoZona
	 */
	public String getNombreGrupoZona() {
		return nombreGrupoZona;
	}
	/**
	 * @param nombreGrupoZona the nombreGrupoZona to set
	 */
	public void setNombreGrupoZona(String nombreGrupoZona) {
		this.nombreGrupoZona = nombreGrupoZona;
	}
	/**
	 * @return the oidGrupoZona
	 */
	public String getOidGrupoZona() {
		return oidGrupoZona;
	}
	/**
	 * @param oidGrupoZona the oidGrupoZona to set
	 */
	public void setOidGrupoZona(String oidGrupoZona) {
		this.oidGrupoZona = oidGrupoZona;
	}
	/**
	 * @return the nombreActividad
	 */
	public String getNombreActividad() {
		return nombreActividad;
	}
	/**
	 * @param nombreActividad the nombreActividad to set
	 */
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}
	/**
	 * @return the oidMatrizDias
	 */
	public String getOidMatrizDias() {
		return oidMatrizDias;
	}
	/**
	 * @param oidMatrizDias the oidMatrizDias to set
	 */
	public void setOidMatrizDias(String oidMatrizDias) {
		this.oidMatrizDias = oidMatrizDias;
	}
	/**
	 * @return the salirPantalla
	 */
	public String getSalirPantalla() {
		return salirPantalla;
	}
	/**
	 * @param salirPantalla the salirPantalla to set
	 */
	public void setSalirPantalla(String salirPantalla) {
		this.salirPantalla = salirPantalla;
	}
	
	

}
