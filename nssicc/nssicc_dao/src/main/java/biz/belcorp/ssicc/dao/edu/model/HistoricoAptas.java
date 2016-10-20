package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class HistoricoAptas extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1708484319614789318L;
	private String id;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;
	private String nombreCurso;
	private String codigoCliente;
	private String codigoCursoDictado;
	private String codigoPlanillaProgramacion;
	private String campanhaPrimeraCalificacionApta;
	private String campanhaUltimaCalificacionApta;
	private String campanhaCapacitacion;
	private String tipoCalificacionApta;
	private Long   numeroInvitacion;
	private String indicadorInicialCalificacionApta;
	private String indicadorFinalCalificacionApta;
	private String indicadorCursoCosto;
	private String indicadorCursoFacturado;
	private String campanhaFacturacionCurso;
	private String campanhaAceptacion;
	private String ultimaCampanhaProgramacionDictado;
	private String estadoCapacitacion;
	private String estadoRegistro;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String primerNombre;
	private String segundoNombre;
	private String descripcionCliente;
	private String asistencia; 
	
	private String codigoRegion;
	private String codigoZona;
	private String campanhaIngreso;
	
	private String prerequisito;
	private String siglaCurso;
	
	private String [] nivelesAlcanzar;

	private String primerPedido;
	private String secuenciaPedido;
	private String [] cursos;
	
	private String indicadorExoneracion;
	private String campanhaRegularizacion;
	
	private String status;
	private String situacionDescripcion;
	
	public String getSituacionDescripcion() {
		return situacionDescripcion;
	}
	public void setSituacionDescripcion(String situacionDescripcion) {
		this.situacionDescripcion = situacionDescripcion;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the campanhaRegularizacion.
	 */
	public String getCampanhaRegularizacion() {
		return campanhaRegularizacion;
	}
	/**
	 * @param campanhaRegularizacion The campanhaRegularizacion to set.
	 */
	public void setCampanhaRegularizacion(String campanhaRegularizacion) {
		this.campanhaRegularizacion = campanhaRegularizacion;
	}
	/**
	 * @return Returns the indicadorExoneracion.
	 */
	public String getIndicadorExoneracion() {
		return indicadorExoneracion;
	}
	/**
	 * @param indicadorExoneracion The indicadorExoneracion to set.
	 */
	public void setIndicadorExoneracion(String indicadorExoneracion) {
		this.indicadorExoneracion = indicadorExoneracion;
	}
	/**
	 * @return Returns the siglaCurso.
	 */
	public String getSiglaCurso() {
		return siglaCurso;
	}
	/**
	 * @param siglaCurso The siglaCurso to set.
	 */
	public void setSiglaCurso(String siglaCurso) {
		this.siglaCurso = siglaCurso;
	}
	/**
	 * @return Returns the campanhaFacturacionCurso.
	 */
	public String getCampanhaFacturacionCurso() {
		return campanhaFacturacionCurso;
	}
	/**
	 * @param campanhaFacturacionCurso The campanhaFacturacionCurso to set.
	 */
	public void setCampanhaFacturacionCurso(String campanhaFacturacionCurso) {
		this.campanhaFacturacionCurso = campanhaFacturacionCurso;
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
	 * @return Returns the indicadorCursoCosto.
	 */
	public String getIndicadorCursoCosto() {
		return indicadorCursoCosto;
	}
	/**
	 * @param indicadorCursoCosto The indicadorCursoCosto to set.
	 */
	public void setIndicadorCursoCosto(String indicadorCursoCosto) {
		this.indicadorCursoCosto = indicadorCursoCosto;
	}
	/**
	 * @return Returns the indicadorCursoFacturado.
	 */
	public String getIndicadorCursoFacturado() {
		return indicadorCursoFacturado;
	}
	/**
	 * @param indicadorCursoFacturado The indicadorCursoFacturado to set.
	 */
	public void setIndicadorCursoFacturado(String indicadorCursoFacturado) {
		this.indicadorCursoFacturado = indicadorCursoFacturado;
	}
	/**
	 * @return Returns the indicadorFinalCalificacionApta.
	 */
	public String getIndicadorFinalCalificacionApta() {
		return indicadorFinalCalificacionApta;
	}
	/**
	 * @param indicadorFinalCalificacionApta The indicadorFinalCalificacionApta to set.
	 */
	public void setIndicadorFinalCalificacionApta(
			String indicadorFinalCalificacionApta) {
		this.indicadorFinalCalificacionApta = indicadorFinalCalificacionApta;
	}
	/**
	 * @return Returns the indicadorInicialCalificacionApta.
	 */
	public String getIndicadorInicialCalificacionApta() {
		return indicadorInicialCalificacionApta;
	}
	/**
	 * @param indicadorInicialCalificacionApta The indicadorInicialCalificacionApta to set.
	 */
	public void setIndicadorInicialCalificacionApta(
			String indicadorInicialCalificacionApta) {
		this.indicadorInicialCalificacionApta = indicadorInicialCalificacionApta;
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
	 * @return Returns the numeroInvitacion.
	 */
	public Long getNumeroInvitacion() {
		return numeroInvitacion;
	}
	/**
	 * @param numeroInvitacion The numeroInvitacion to set.
	 */
	public void setNumeroInvitacion(Long numeroInvitacion) {
		this.numeroInvitacion = numeroInvitacion;
	}
	/**
	 * @return Returns the tipoCalificacionApta.
	 */
	public String getTipoCalificacionApta() {
		return tipoCalificacionApta;
	}
	/**
	 * @param tipoCalificacionApta The tipoCalificacionApta to set.
	 */
	public void setTipoCalificacionApta(String tipoCalificacionApta) {
		this.tipoCalificacionApta = tipoCalificacionApta;
	}
	/**
	 * @return Returns the ultimaCampanhaProgramacionDictado.
	 */
	public String getUltimaCampanhaProgramacionDictado() {
		return ultimaCampanhaProgramacionDictado;
	}
	/**
	 * @param ultimaCampanhaProgramacionDictado The ultimaCampanhaProgramacionDictado to set.
	 */
	public void setUltimaCampanhaProgramacionDictado(
			String ultimaCampanhaProgramacionDictado) {
		this.ultimaCampanhaProgramacionDictado = ultimaCampanhaProgramacionDictado;
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
	 * @return Returns the campanhaAceptacion.
	 */
	public String getCampanhaAceptacion() {
		return campanhaAceptacion;
	}
	/**
	 * @param campanhaAceptacion The campanhaAceptacion to set.
	 */
	public void setCampanhaAceptacion(String campanhaAceptacion) {
		this.campanhaAceptacion = campanhaAceptacion;
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
	public String getDescripcionCliente() {
		return descripcionCliente;
	}
	public void setDescripcionCliente(String descripcionCliente) {
		this.descripcionCliente = descripcionCliente;
	}
	/**
	 * @return Returns the asistencia.
	 */
	public String getAsistencia() {
		return asistencia;
	}
	/**
	 * @param asistencia The asistencia to set.
	 */
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
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
	/**
	 * @return Returns the prerequisito.
	 */
	public String getPrerequisito() {
		return prerequisito;
	}
	/**
	 * @param prerequisito The prerequisito to set.
	 */
	public void setPrerequisito(String prerequisito) {
		this.prerequisito = prerequisito;
	}
	/**
	 * @return Returns the nivelesAlcanzar.
	 */
	public String[] getNivelesAlcanzar() {
		return nivelesAlcanzar;
	}
	/**
	 * @param nivelesAlcanzar The nivelesAlcanzar to set.
	 */
	public void setNivelesAlcanzar(String[] nivelesAlcanzar) {
		this.nivelesAlcanzar = nivelesAlcanzar;
	}
	public String[] getCursos() {
		return cursos;
	}
	public void setCursos(String[] cursos) {
		this.cursos = cursos;
	}
	public String getPrimerPedido() {
		return primerPedido;
	}
	public void setPrimerPedido(String primerPedido) {
		this.primerPedido = primerPedido;
	}
	public String getSecuenciaPedido() {
		return secuenciaPedido;
	}
	public void setSecuenciaPedido(String secuenciaPedido) {
		this.secuenciaPedido = secuenciaPedido;
	}

}
