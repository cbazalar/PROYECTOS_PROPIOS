package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class HistoricoCursoDictadoDetalle extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3986411972841695633L;
	private String id;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;
	private String nombreCurso;
	private String codigoCursoDictado;
	private String codigoCliente;
	private String apellidoMaterno;
	private String apellidoPaterno;
	private String primerNombre;
	private String segundoNombre;
	private String descripcionCliente;	
	private String numeroTelefono;
	private String campanhaIngreso;
	private String codigoPlanillaProgramacion;
	private Double calificacionInstructora;
	private Double calificacionConsultora;
	private String estadoRegistro;
	private String indicadorAsistencia;
	private String estadoCursoDictado;
	private String displayNota;
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
	 * @return Returns the calificacionInstructora.
	 */
	public Double getCalificacionInstructora() {
		return calificacionInstructora;
	}
	/**
	 * @param calificacionInstructora The calificacionInstructora to set.
	 */
	public void setCalificacionInstructora(Double calificacionInstructora) {
		this.calificacionInstructora = calificacionInstructora;
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
	 * @return Returns the codigoPlanillaProgramacion.
	 */
	public String getCodigoPlanillaProgramacion() {
		return codigoPlanillaProgramacion;
	}
	/**
	 * @param codigoPlanillaProgramacion The codigoPlanillaProgramacion to set.
	 */
	public void setCodigoPlanillaProgramacion(String codigoPlanillaProgramacion) {
		this.codigoPlanillaProgramacion = codigoPlanillaProgramacion;
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
	 * @return Returns the calificacionConsultora.
	 */
	public Double getCalificacionConsultora() {
		return calificacionConsultora;
	}
	/**
	 * @param calificacionConsultora The calificacionConsultora to set.
	 */
	public void setCalificacionConsultora(Double calificacionConsultora) {
		this.calificacionConsultora = calificacionConsultora;
		if (this.calificacionConsultora!=null) this.displayNota = this.calificacionConsultora.toString(); 
		else this.displayNota = "0.00";
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
