package biz.belcorp.ssicc.web.spusicc.cronograma.form;


import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


/**
 * 
 * <p>
 * <a href="MantenimientoCRACronogramaFase1Form.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 * @struts.form name = "mantenimientoCRACronogramaFase1PopupForm"
 *                      
 */
public class MantenimientoCRACronogramaFase2PopupForm extends BaseEditForm  implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String oidZona;
	private String oidActividad;	
	private String nombreActividad;
	private String nombreZona;
	
	private String oidCronogramaFase2;
	private String salirPantalla = "N";
	private String diasDesplazamiento;
	private String fecha;
	private String fechaDesplazada;
	private String codigoPeriodo;
	private String indicadorLaborable;
	private String fechaInicio;
	private String nombreActividadOrigen;
	private String fechaActividadOrigen;
	private String oidGrupoZona;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the oidZona
	 */
	public String getOidZona() {
		return oidZona;
	}
	/**
	 * @param oidZona the oidZona to set
	 */
	public void setOidZona(String oidZona) {
		this.oidZona = oidZona;
	}
	/**
	 * @return the oidActividad
	 */
	public String getOidActividad() {
		return oidActividad;
	}
	/**
	 * @param oidActividad the oidActividad to set
	 */
	public void setOidActividad(String oidActividad) {
		this.oidActividad = oidActividad;
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
	 * @return the nombreZona
	 */
	public String getNombreZona() {
		return nombreZona;
	}
	/**
	 * @param nombreZona the nombreZona to set
	 */
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	/**
	 * @return the oidCronogramaFase2
	 */
	public String getOidCronogramaFase2() {
		return oidCronogramaFase2;
	}
	/**
	 * @param oidCronogramaFase2 the oidCronogramaFase2 to set
	 */
	public void setOidCronogramaFase2(String oidCronogramaFase2) {
		this.oidCronogramaFase2 = oidCronogramaFase2;
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
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the fechaDesplazada
	 */
	public String getFechaDesplazada() {
		return fechaDesplazada;
	}
	/**
	 * @param fechaDesplazada the fechaDesplazada to set
	 */
	public void setFechaDesplazada(String fechaDesplazada) {
		this.fechaDesplazada = fechaDesplazada;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the indicadorLaborable
	 */
	public String getIndicadorLaborable() {
		return indicadorLaborable;
	}
	
	/**
	 * @param indicadorLaborable the indicadorLaborable to set
	 */
	public void setIndicadorLaborable(String indicadorLaborable) {
		this.indicadorLaborable = indicadorLaborable;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
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
	 * @return the fechaActividadOrigen
	 */
	public String getFechaActividadOrigen() {
		return fechaActividadOrigen;
	}
	/**
	 * @param fechaActividadOrigen the fechaActividadOrigen to set
	 */
	public void setFechaActividadOrigen(String fechaActividadOrigen) {
		this.fechaActividadOrigen = fechaActividadOrigen;
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
				
}
