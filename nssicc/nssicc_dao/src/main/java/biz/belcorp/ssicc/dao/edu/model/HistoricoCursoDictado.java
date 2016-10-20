package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class HistoricoCursoDictado extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3387108706722761077L;
	private String id;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;
	private String nombreCurso;
	private String codigoCursoDictado;
	private String codigoInstructora;
	private String nombreInstructora;
	private String campanhaInicio;
	private String campanhaInicioSearch;
	private Date   fechaInicio;
	private Integer numeroSesiones;
	private String codigoRegion;
	private String descripcionRegion;
	private String codigoZona;
	private String descripcionZona;
	private String lugarCapacitacion;
	private String categoriaLugar;
	private String indicadorEvaluacionCurso;
	private String indicadorEvaluacionCapacitada;	
	private Double calificacionPromedio;
	private String estadoRegistro;
	private String codigoPlanilla;
	private String estadoCursoDictado;
	private String login;
	private String sinClientes;
	private String codigoLocal;
	private String codigoSala;
	//
	private String codigoSistema;
	
	
	/**
	 * @return Returns the calificacionPromedio.
	 */
	public Double getCalificacionPromedio() {
		return calificacionPromedio;
	}
	/**
	 * @param calificacionPromedio The calificacionPromedio to set.
	 */
	public void setCalificacionPromedio(Double calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}
	/**
	 * @return Returns the campanhaInicio.
	 */
	public String getCampanhaInicio() {
		return campanhaInicio;
	}
	/**
	 * @param campanhaInicio The campanhaInicio to set.
	 */
	public void setCampanhaInicio(String campanhaInicio) {
		this.campanhaInicio = campanhaInicio;
	}
	/**
	 * @return Returns the codigoCurso.
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}
	/**
	 * @param codigoCurso The codigoCurso to set.
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	/**
	 * @return Returns the codigoCursoDictado.
	 */
	public String getCodigoCursoDictado() {
		return codigoCursoDictado;
	}
	/**
	 * @param codigoCursoDictado The codigoCursoDictado to set.
	 */
	public void setCodigoCursoDictado(String codigoCursoDictado) {
		this.codigoCursoDictado = codigoCursoDictado;
	}
	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	/**
	 * @return Returns the codigoInstructora.
	 */
	public String getCodigoInstructora() {
		return codigoInstructora;
	}
	/**
	 * @param codigoInstructora The codigoInstructora to set.
	 */
	public void setCodigoInstructora(String codigoInstructora) {
		this.codigoInstructora = codigoInstructora;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the estadoCursoDictado.
	 */
	public String getEstadoCursoDictado() {
		return estadoCursoDictado;
	}
	/**
	 * @param estadoCursoDictado The estadoCursoDictado to set.
	 */
	public void setEstadoCursoDictado(String estadoCursoDictado) {
		this.estadoCursoDictado = estadoCursoDictado;
	}
	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return Returns the fechaInicio.
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio The fechaInicio to set.
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the indicadorEvaluacionCurso.
	 */
	public String getIndicadorEvaluacionCurso() {
		return indicadorEvaluacionCurso;
	}
	/**
	 * @param indicadorEvaluacionCurso The indicadorEvaluacionCurso to set.
	 */
	public void setIndicadorEvaluacionCurso(String indicadorEvaluacionCurso) {
		this.indicadorEvaluacionCurso = indicadorEvaluacionCurso;
	}
	/**
	 * @return Returns the nombreCurso.
	 */
	public String getNombreCurso() {
		return nombreCurso;
	}
	/**
	 * @param nombreCurso The nombreCurso to set.
	 */
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	/**
	 * @return Returns the nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * @param nombreEmpresa The nombreEmpresa to set.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * @return Returns the numeroSesiones.
	 */
	public Integer getNumeroSesiones() {
		return numeroSesiones;
	}
	/**
	 * @param numeroSesiones The numeroSesiones to set.
	 */
	public void setNumeroSesiones(Integer numeroSesiones) {
		this.numeroSesiones = numeroSesiones;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}
	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	/**
	 * @return Returns the lugarCapacitacion.
	 */
	public String getLugarCapacitacion() {
		return lugarCapacitacion;
	}
	/**
	 * @param lugarCapacitacion The lugarCapacitacion to set.
	 */
	public void setLugarCapacitacion(String lugarCapacitacion) {
		this.lugarCapacitacion = lugarCapacitacion;
	}
	/**
	 * @return Returns the nombreInstructora.
	 */
	public String getNombreInstructora() {
		return nombreInstructora;
	}
	/**
	 * @param nombreInstructora The nombreInstructora to set.
	 */
	public void setNombreInstructora(String nombreInstructora) {
		this.nombreInstructora = nombreInstructora;
	}
	/**
	 * @return Returns the categoriaLugar.
	 */
	public String getCategoriaLugar() {
		return categoriaLugar;
	}
	/**
	 * @param categoriaLugar The categoriaLugar to set.
	 */
	public void setCategoriaLugar(String categoriaLugar) {
		this.categoriaLugar = categoriaLugar;
	}
	/**
	 * @return Returns the codigoPlanilla.
	 */
	public String getCodigoPlanilla() {
		return codigoPlanilla;
	}
	/**
	 * @param codigoPlanilla The codigoPlanilla to set.
	 */
	public void setCodigoPlanilla(String codigoPlanilla) {
		this.codigoPlanilla = codigoPlanilla;
	}
	/**
	 * @return Returns the indicadorEvaluacionCapacitada.
	 */
	public String getIndicadorEvaluacionCapacitada() {
		return indicadorEvaluacionCapacitada;
	}
	/**
	 * @param indicadorEvaluacionCapacitada The indicadorEvaluacionCapacitada to set.
	 */
	public void setIndicadorEvaluacionCapacitada(
			String indicadorEvaluacionCapacitada) {
		this.indicadorEvaluacionCapacitada = indicadorEvaluacionCapacitada;
	}
	/**
	 * @return Returns the login.
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login The login to set.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return Returns the campanhaInicioSearch.
	 */
	public String getCampanhaInicioSearch() {
		return campanhaInicioSearch;
	}
	/**
	 * @param campanhaInicioSearch The campanhaInicioSearch to set.
	 */
	public void setCampanhaInicioSearch(String campanhaInicioSearch) {
		this.campanhaInicioSearch = campanhaInicioSearch;
	}
	/**
	 * @return Returns the sinClientes.
	 */
	public String getSinClientes() {
		return sinClientes;
	}
	/**
	 * @param sinClientes The sinClientes to set.
	 */
	public void setSinClientes(String sinClientes) {
		this.sinClientes = sinClientes;
	}
	/**
	 * @return Returns the codigoLocal.
	 */
	public String getCodigoLocal() {
		return codigoLocal;
	}
	/**
	 * @param codigoLocal The codigoLocal to set.
	 */
	public void setCodigoLocal(String codigoLocal) {
		this.codigoLocal = codigoLocal;
	}
	/**
	 * @return Returns the codigoSala.
	 */
	public String getCodigoSala() {
		return codigoSala;
	}
	/**
	 * @param codigoSala The codigoSala to set.
	 */
	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
	}
	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}
	/**
	 * @param codigoSistema The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	
	
	

}
