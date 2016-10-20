package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

/**
 * @author peextrvela
 *
 */
public class ConsultaCursoCapacitacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4014232236883804295L;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;
	private String nombreCurso;
	private String siglasCurso;
	private String codigoInstructora;
	private String apellidoPaternoInstructora;
	private String apellidoMaternoInstructora;
	private String primerNombreInstructora;
	private String segundoNombreInstructora;
	private String codigoCliente;
	private String apellidoPaternoCliente;
	private String apellidoMaternoCliente;
	private String primerNombreCliente;
	private String segundoNombreCliente;
	private String descripcionCliente;
	private String codigoPlanillaProgramacion;
	private String campanhaPrimeraCalificacionApta;
	private String campanhaUltimaCalificacionApta;
	private String indicadorCursoCosto;
	private String indicadorCursoFacturado;
	private String campanhaFacturacionCurso;
	private String campanhaAceptacion;
	private Long   numeroInvitacion;
	private String ultimaCampanhaProgramacion;	
	private String campanhaCapacitacion;
	private String cursoDictado;
	//
	private String codigoRegion;
	private String codigoZona;
	private String estadoCapacitacion;
	
	private String telefonoFijo;
	private String telefonoCelular;
	
	private String campanhaIngreso;
	
	private String status;
	private String tipo;
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
	 * @return Returns the cursoDictado.
	 */
	public String getCursoDictado() {
		return cursoDictado;
	}
	/**
	 * @param cursoDictado The cursoDictado to set.
	 */
	public void setCursoDictado(String cursoDictado) {
		this.cursoDictado = cursoDictado;
	}
	/**
	 * @return Returns the apellidoMaternoCliente.
	 */
	public String getApellidoMaternoCliente() {
		return apellidoMaternoCliente;
	}
	/**
	 * @param apellidoMaternoCliente The apellidoMaternoCliente to set.
	 */
	public void setApellidoMaternoCliente(String apellidoMaternoCliente) {
		this.apellidoMaternoCliente = apellidoMaternoCliente;
	}
	/**
	 * @return Returns the apellidoMaternoInstructora.
	 */
	public String getApellidoMaternoInstructora() {
		return apellidoMaternoInstructora;
	}
	/**
	 * @param apellidoMaternoInstructora The apellidoMaternoInstructora to set.
	 */
	public void setApellidoMaternoInstructora(String apellidoMaternoInstructora) {
		this.apellidoMaternoInstructora = apellidoMaternoInstructora;
	}
	/**
	 * @return Returns the apellidoPaternoCliente.
	 */
	public String getApellidoPaternoCliente() {
		return apellidoPaternoCliente;
	}
	/**
	 * @param apellidoPaternoCliente The apellidoPaternoCliente to set.
	 */
	public void setApellidoPaternoCliente(String apellidoPaternoCliente) {
		this.apellidoPaternoCliente = apellidoPaternoCliente;
	}
	/**
	 * @return Returns the apellidoPaternoInstructora.
	 */
	public String getApellidoPaternoInstructora() {
		return apellidoPaternoInstructora;
	}
	/**
	 * @param apellidoPaternoInstructora The apellidoPaternoInstructora to set.
	 */
	public void setApellidoPaternoInstructora(String apellidoPaternoInstructora) {
		this.apellidoPaternoInstructora = apellidoPaternoInstructora;
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
	 * @return Returns the primerNombreCliente.
	 */
	public String getPrimerNombreCliente() {
		return primerNombreCliente;
	}
	/**
	 * @param primerNombreCliente The primerNombreCliente to set.
	 */
	public void setPrimerNombreCliente(String primerNombreCliente) {
		this.primerNombreCliente = primerNombreCliente;
	}
	/**
	 * @return Returns the primerNombreInstructora.
	 */
	public String getPrimerNombreInstructora() {
		return primerNombreInstructora;
	}
	/**
	 * @param primerNombreInstructora The primerNombreInstructora to set.
	 */
	public void setPrimerNombreInstructora(String primerNombreInstructora) {
		this.primerNombreInstructora = primerNombreInstructora;
	}
	/**
	 * @return Returns the segundoNombreCliente.
	 */
	public String getSegundoNombreCliente() {
		return segundoNombreCliente;
	}
	/**
	 * @param segundoNombreCliente The segundoNombreCliente to set.
	 */
	public void setSegundoNombreCliente(String segundoNombreCliente) {
		this.segundoNombreCliente = segundoNombreCliente;
	}
	/**
	 * @return Returns the segundoNombreInstructora.
	 */
	public String getSegundoNombreInstructora() {
		return segundoNombreInstructora;
	}
	/**
	 * @param segundoNombreInstructora The segundoNombreInstructora to set.
	 */
	public void setSegundoNombreInstructora(String segundoNombreInstructora) {
		this.segundoNombreInstructora = segundoNombreInstructora;
	}
	/**
	 * @return Returns the siglasCurso.
	 */
	public String getSiglasCurso() {
		return siglasCurso;
	}
	/**
	 * @param siglasCurso The siglasCurso to set.
	 */
	public void setSiglasCurso(String siglasCurso) {
		this.siglasCurso = siglasCurso;
	}
	/**
	 * @return Returns the ultimaCampanhaProgramacion.
	 */
	public String getUltimaCampanhaProgramacion() {
		return ultimaCampanhaProgramacion;
	}
	/**
	 * @param ultimaCampanhaProgramacion The ultimaCampanhaProgramacion to set.
	 */
	public void setUltimaCampanhaProgramacion(String ultimaCampanhaProgramacion) {
		this.ultimaCampanhaProgramacion = ultimaCampanhaProgramacion;
	}
	public String getDescripcionCliente() {
		return descripcionCliente;
	}
	public void setDescripcionCliente(String descripcionCliente) {
		this.descripcionCliente = descripcionCliente;
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
	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
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
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo The tipo to set.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
