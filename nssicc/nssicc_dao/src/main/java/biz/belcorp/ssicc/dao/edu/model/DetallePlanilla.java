package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class DetallePlanilla extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7729912400804259984L;
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
	private String campanhaIngreso;
	private Date   fechaInicio;
	private Integer numeroSesiones;
	private String codigoRegion;
	private String descripcionRegion;
	private String codigoZona;
	private String descripcionZona;
	private String lugarCapacitacion;
	private String categoriaLugarCapacitacion;
	private String indicadorEvaluacionCurso;
	private Double calificacionPromedio;
	private Double calificacionEvaluacionCurso;
	private String estadoCursoDictado;
	private String estadoRegistro;
	private String codigoPlanilla;
	private String codigoCliente;
	private String indicadorAsistencia;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String primerNombre;
	private String segundoNombre;
	private String descripcionCliente;	
	private String numeroTelefono;
	private String displayNota;
	private String estadoCapacitacion;
	private String egresada;
	/**
	 * @return the egresada
	 */
	public String getEgresada() {
		return egresada;
	}
	/**
	 * @param egresada the egresada to set
	 */
	public void setEgresada(String egresada) {
		this.egresada = egresada;
	}
	/**
	 * @return Returns the displayNota.
	 */
	public String getDisplayNota() {
		return displayNota;
	}
	/**
	 * @param displayNota The displayNota to set.
	 */
	public void setDisplayNota(String displayNota) {
		this.displayNota = displayNota;
	}
	/**
	 * @return Returns the apellidoMaterno.
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno The apellidoMaterno to set.
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return Returns the apellidoPaterno.
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno The apellidoPaterno to set.
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return Returns the calificacionEvaluacionCurso.
	 */
	public Double getCalificacionEvaluacionCurso() {
		return calificacionEvaluacionCurso;
	}
	/**
	 * @param calificacionEvaluacionCurso The calificacionEvaluacionCurso to set.
	 */
	public void setCalificacionEvaluacionCurso(Double calificacionEvaluacionCurso) {
		this.calificacionEvaluacionCurso = calificacionEvaluacionCurso;
		if (this.calificacionEvaluacionCurso!=null) this.displayNota = this.calificacionEvaluacionCurso.toString(); 
		else this.displayNota = "0.00";
	}
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
	 * @return Returns the categoriaLugarCapacitacion.
	 */
	public String getCategoriaLugarCapacitacion() {
		return categoriaLugarCapacitacion;
	}
	/**
	 * @param categoriaLugarCapacitacion The categoriaLugarCapacitacion to set.
	 */
	public void setCategoriaLugarCapacitacion(String categoriaLugarCapacitacion) {
		this.categoriaLugarCapacitacion = categoriaLugarCapacitacion;
	}
	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
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
	 * @return Returns the indicadorAsistencia.
	 */
	public String getIndicadorAsistencia() {
		return indicadorAsistencia;
	}
	/**
	 * @param indicadorAsistencia The indicadorAsistencia to set.
	 */
	public void setIndicadorAsistencia(String indicadorAsistencia) {
		this.indicadorAsistencia = indicadorAsistencia;
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
	/**
	 * @return Returns the numeroTelefono.
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	/**
	 * @param numeroTelefono The numeroTelefono to set.
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	/**
	 * @return Returns the primerNombre.
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}
	/**
	 * @param primerNombre The primerNombre to set.
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	/**
	 * @return Returns the segundoNombre.
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}
	/**
	 * @param segundoNombre The segundoNombre to set.
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
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
	 * @return Returns the estadoCapacitacion.
	 */
	public String getEstadoCapacitacion() {
		return estadoCapacitacion;
	}
	/**
	 * @param estadoCapacitacion The estadoCapacitacion to set.
	 */
	public void setEstadoCapacitacion(String estadoCapacitacion) {
		this.estadoCapacitacion = estadoCapacitacion;
	}
	/**
	 * @return Returns the campanhaIngreso.
	 */
	public String getCampanhaIngreso() {
		return campanhaIngreso;
	}
	/**
	 * @param campanhaIngreso The campanhaIngreso to set.
	 */
	public void setCampanhaIngreso(String campanhaIngreso) {
		this.campanhaIngreso = campanhaIngreso;
	}
	public String getDescripcionCliente() {
		return descripcionCliente;
	}
	public void setDescripcionCliente(String descripcionCliente) {
		this.descripcionCliente = descripcionCliente;
	}
	
}
