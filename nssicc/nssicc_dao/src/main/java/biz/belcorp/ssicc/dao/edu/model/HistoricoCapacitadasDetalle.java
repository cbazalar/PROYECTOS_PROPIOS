package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class HistoricoCapacitadasDetalle extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3007662922950690004L;
	private String id;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCliente;
	private String apellidoMaterno;
	private String apellidoPaterno;
	private String primerNombre;
	private String descripcionCliente;
	private String numeroTelefono;
	private String campanhaIngreso;
	private String segundoNombre;
	private String codigoCurso;
	private String nombreCurso;
	private String codigoTipoAsistencia;
	private String descripcionTipoAsistencia;
	private String codigoTipoAsistente;
	private String descripcionTipoAsistente;
	private Long   numeroInvitaciones;
	private String indicadorPagoCurso;
	private String codigoCursoDictado;
	private String codigoPlanilla;
	private String codigoInstructora;
	private String campanhaPrimeraCalificacionApta;
	private String campanhaUltimaCalificacionApta;
	private String campanhaCapacitacion;
	private String campanhaInvitacion;
	private String campanhaRegistroAsistencia;
	private String indicadorEvaluacionCurso;
	private Double calificacionEvaluacionCurso;
	private String indicadorEvaluacionInstructora;
	private Double calificacionEvaluacionInstructora;
	private String displayNota;
	private String estadoRegistro;
	
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
	}
	/**
	 * @return Returns the campanhaCapacitacion.
	 */
	public String getCampanhaCapacitacion() {
		return campanhaCapacitacion;
	}
	/**
	 * @param campanhaCapacitacion The campanhaCapacitacion to set.
	 */
	public void setCampanhaCapacitacion(String campanhaCapacitacion) {
		this.campanhaCapacitacion = campanhaCapacitacion;
	}
	/**
	 * @return Returns the campanhaRegistroAsistencia.
	 */
	public String getCampanhaRegistroAsistencia() {
		return campanhaRegistroAsistencia;
	}
	/**
	 * @param campanhaRegistroAsistencia The campanhaRegistroAsistencia to set.
	 */
	public void setCampanhaRegistroAsistencia(String campanhaRegistroAsistencia) {
		this.campanhaRegistroAsistencia = campanhaRegistroAsistencia;
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
	 * @return Returns the codigoTipoAsistencia.
	 */
	public String getCodigoTipoAsistencia() {
		return codigoTipoAsistencia;
	}
	/**
	 * @param codigoTipoAsistencia The codigoTipoAsistencia to set.
	 */
	public void setCodigoTipoAsistencia(String codigoTipoAsistencia) {
		this.codigoTipoAsistencia = codigoTipoAsistencia;
	}
	/**
	 * @return Returns the codigoTipoAsistente.
	 */
	public String getCodigoTipoAsistente() {
		return codigoTipoAsistente;
	}
	/**
	 * @param codigoTipoAsistente The codigoTipoAsistente to set.
	 */
	public void setCodigoTipoAsistente(String codigoTipoAsistente) {
		this.codigoTipoAsistente = codigoTipoAsistente;
	}
	/**
	 * @return Returns the descripcionTipoAsistencia.
	 */
	public String getDescripcionTipoAsistencia() {
		return descripcionTipoAsistencia;
	}
	/**
	 * @param descripcionTipoAsistencia The descripcionTipoAsistencia to set.
	 */
	public void setDescripcionTipoAsistencia(String descripcionTipoAsistencia) {
		this.descripcionTipoAsistencia = descripcionTipoAsistencia;
	}
	/**
	 * @return Returns the descripcionTipoAsistente.
	 */
	public String getDescripcionTipoAsistente() {
		return descripcionTipoAsistente;
	}
	/**
	 * @param descripcionTipoAsistente The descripcionTipoAsistente to set.
	 */
	public void setDescripcionTipoAsistente(String descripcionTipoAsistente) {
		this.descripcionTipoAsistente = descripcionTipoAsistente;
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
	 * @return Returns the indicadorEvaluacionInstructora.
	 */
	public String getindicadorEvaluacionInstructora() {
		return indicadorEvaluacionInstructora;
	}
	/**
	 * @param indicadorEvaluacionInstructora The indicadorEvaluacionInstructora to set.
	 */
	public void setindicadorEvaluacionInstructora(
			String indicadorEvaluacionInstructora) {
		this.indicadorEvaluacionInstructora = indicadorEvaluacionInstructora;
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
	 * @return Returns the indicadorPagoCurso.
	 */
	public String getIndicadorPagoCurso() {
		return indicadorPagoCurso;
	}
	/**
	 * @param indicadorPagoCurso The indicadorPagoCurso to set.
	 */
	public void setIndicadorPagoCurso(String indicadorPagoCurso) {
		this.indicadorPagoCurso = indicadorPagoCurso;
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
	 * @return Returns the numeroInvitaciones.
	 */
	public Long getNumeroInvitaciones() {
		return numeroInvitaciones;
	}
	/**
	 * @param numeroInvitaciones The numeroInvitaciones to set.
	 */
	public void setNumeroInvitaciones(Long numeroInvitaciones) {
		this.numeroInvitaciones = numeroInvitaciones;
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
	 * @return Returns the calificacionEvaluacionInstructora.
	 */
	public Double getCalificacionEvaluacionInstructora() {
		return calificacionEvaluacionInstructora;
	}
	/**
	 * @param calificacionEvaluacionInstructora The calificacionEvaluacionInstructora to set.
	 */
	public void setCalificacionEvaluacionInstructora(
			Double calificacionEvaluacionInstructora) {
		this.calificacionEvaluacionInstructora = calificacionEvaluacionInstructora;
		if (this.calificacionEvaluacionInstructora!=null) this.displayNota = this.calificacionEvaluacionInstructora.toString(); 
		else this.displayNota = "0.00";
	}
	/**
	 * @return Returns the campanhaPrimeraCalificacionApta.
	 */
	public String getCampanhaPrimeraCalificacionApta() {
		return campanhaPrimeraCalificacionApta;
	}
	/**
	 * @param campanhaPrimeraCalificacionApta The campanhaPrimeraCalificacionApta to set.
	 */
	public void setCampanhaPrimeraCalificacionApta(
			String campanhaPrimeraCalificacionApta) {
		this.campanhaPrimeraCalificacionApta = campanhaPrimeraCalificacionApta;
	}
	/**
	 * @return Returns the campanhaUltimaCalificacionApta.
	 */
	public String getCampanhaUltimaCalificacionApta() {
		return campanhaUltimaCalificacionApta;
	}
	/**
	 * @param campanhaUltimaCalificacionApta The campanhaUltimaCalificacionApta to set.
	 */
	public void setCampanhaUltimaCalificacionApta(
			String campanhaUltimaCalificacionApta) {
		this.campanhaUltimaCalificacionApta = campanhaUltimaCalificacionApta;
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
	 * @return Returns the indicadorEvaluacionInstructora.
	 */
	public String getIndicadorEvaluacionInstructora() {
		return indicadorEvaluacionInstructora;
	}
	/**
	 * @param indicadorEvaluacionInstructora The indicadorEvaluacionInstructora to set.
	 */
	public void setIndicadorEvaluacionInstructora(
			String indicadorEvaluacionInstructora) {
		this.indicadorEvaluacionInstructora = indicadorEvaluacionInstructora;
	}
	/**
	 * @return Returns the campanhaInvitacion.
	 */
	public String getCampanhaInvitacion() {
		return campanhaInvitacion;
	}
	/**
	 * @param campanhaInvitacion The campanhaInvitacion to set.
	 */
	public void setCampanhaInvitacion(String campanhaInvitacion) {
		this.campanhaInvitacion = campanhaInvitacion;
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
