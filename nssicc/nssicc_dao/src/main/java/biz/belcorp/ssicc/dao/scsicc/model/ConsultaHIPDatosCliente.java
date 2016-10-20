package biz.belcorp.ssicc.dao.scsicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * Clase que servira para compartir datos de la consultora entre las pantallas de HiperConsulta
 *  
 * <p>
 * <a href="ConsultaHIPDatosCliente.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public class ConsultaHIPDatosCliente extends AuditableBaseObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String oidCliente;
	private String codigoCliente;
	private String nombreCompleto;

	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;

	private String codigoSubGerencia;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String codigoTerritorio;

	private String descripcionRegion;
	private String descripcionZona;
	private String descripcionSeccion;

	private String oidIdioma;
	
	private String saldoUnico;
	private String tipoDocIdentidad;
	private String tipoDocumento;
	private String numeroDocIdentidad;
	private String numeroDocumento;
	private String periodoUltimoPedido;
	
	private String status;
	private String campanaIngreso;
	
	private String telefonoCasa;
	private String telefonoCelular;
	private String fechaNacimiento;
	private String mail;
	
	private String clasificacionLove;
	
	private String apellido1;
	private String apellido2;
	private String nombre1;
	private String nombre2;
	private String oidPais;
	private String oidTipoDocIdentidad;

	private String indicadorActivo;
	private String indActi;
	private String bloqueo;
	private String codigoBloqueo;
	
	private String nombreCompletoGerenteZona;
	private String celularGerenteZona;
	private String nombreCompletoLiderSeccion;
	private String celularLiderSeccion;
	
	//PER-SiCC-2013-0886 HIP (Requerimiento) @ghuertasa inicio
	private String fechaCambio;
	//PER-SiCC-2013-0886 HIP (Requerimiento) @ghuertasa fin
	
	private String estadoFlx;
	private String fechaActivacionFlx;
	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
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
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
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
	 * @return Returns the codigoSeccion.
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion The codigoSeccion to set.
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return Returns the codigoSubGerencia.
	 */
	public String getCodigoSubGerencia() {
		return codigoSubGerencia;
	}

	/**
	 * @param codigoSubGerencia The codigoSubGerencia to set.
	 */
	public void setCodigoSubGerencia(String codigoSubGerencia) {
		this.codigoSubGerencia = codigoSubGerencia;
	}

	/**
	 * @return Returns the codigoTerritorio.
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	/**
	 * @param codigoTerritorio The codigoTerritorio to set.
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
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
	 * @return Returns the descripcionSeccion.
	 */
	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}

	/**
	 * @param descripcionSeccion The descripcionSeccion to set.
	 */
	public void setDescripcionSeccion(String descripcionSeccion) {
		this.descripcionSeccion = descripcionSeccion;
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
	 * @return Returns the nombreCompleto.
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto The nombreCompleto to set.
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return Returns the oidCliente.
	 */
	public String getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente The oidCliente to set.
	 */
	public void setOidCliente(String oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return Returns the oidIdioma.
	 */
	public String getOidIdioma() {
		return oidIdioma;
	}

	/**
	 * @param oidIdioma The oidIdioma to set.
	 */
	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return Returns the numeroDocIdentidad.
	 */
	public String getNumeroDocIdentidad() {
		return numeroDocIdentidad;
	}

	/**
	 * @param numeroDocIdentidad The numeroDocIdentidad to set.
	 */
	public void setNumeroDocIdentidad(String numeroDocIdentidad) {
		this.numeroDocIdentidad = numeroDocIdentidad;
	}

	/**
	 * @return Returns the periodoUltimoPedido.
	 */
	public String getPeriodoUltimoPedido() {
		return periodoUltimoPedido;
	}

	/**
	 * @param periodoUltimoPedido The periodoUltimoPedido to set.
	 */
	public void setPeriodoUltimoPedido(String periodoUltimoPedido) {
		this.periodoUltimoPedido = periodoUltimoPedido;
	}

	/**
	 * @return Returns the saldoUnico.
	 */
	public String getSaldoUnico() {
		return saldoUnico;
	}

	/**
	 * @param saldoUnico The saldoUnico to set.
	 */
	public void setSaldoUnico(String saldoUnico) {
		this.saldoUnico = saldoUnico;
	}

	/**
	 * @return Returns the tipoDocIdentidad.
	 */
	public String getTipoDocIdentidad() {
		return tipoDocIdentidad;
	}

	/**
	 * @param tipoDocIdentidad The tipoDocIdentidad to set.
	 */
	public void setTipoDocIdentidad(String tipoDocIdentidad) {
		this.tipoDocIdentidad = tipoDocIdentidad;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the campanaIngreso
	 */
	public String getCampanaIngreso() {
		return campanaIngreso;
	}

	/**
	 * @param campanaIngreso the campanaIngreso to set
	 */
	public void setCampanaIngreso(String campanaIngreso) {
		this.campanaIngreso = campanaIngreso;
	}

	/**
	 * @return the telefonoCasa
	 */
	public String getTelefonoCasa() {
		return telefonoCasa;
	}

	/**
	 * @param telefonoCasa the telefonoCasa to set
	 */
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}

	/**
	 * @return the telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	/**
	 * @param telefonoCelular the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the clasificacionLove
	 */
	public String getClasificacionLove() {
		return clasificacionLove;
	}

	/**
	 * @param clasificacionLove the clasificacionLove to set
	 */
	public void setClasificacionLove(String clasificacionLove) {
		this.clasificacionLove = clasificacionLove;
	}

	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * @return the nombre1
	 */
	public String getNombre1() {
		return nombre1;
	}

	/**
	 * @param nombre1 the nombre1 to set
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	/**
	 * @return the nombre2
	 */
	public String getNombre2() {
		return nombre2;
	}

	/**
	 * @param nombre2 the nombre2 to set
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the oidTipoDocIdentidad
	 */
	public String getOidTipoDocIdentidad() {
		return oidTipoDocIdentidad;
	}

	/**
	 * @param oidTipoDocIdentidad the oidTipoDocIdentidad to set
	 */
	public void setOidTipoDocIdentidad(String oidTipoDocIdentidad) {
		this.oidTipoDocIdentidad = oidTipoDocIdentidad;
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
	 * @return the bloqueo
	 */
	public String getBloqueo() {
		return bloqueo;
	}

	/**
	 * @param bloqueo the bloqueo to set
	 */
	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
	}

	/**
	 * @return the codigoBloqueo
	 */
	public String getCodigoBloqueo() {
		return codigoBloqueo;
	}

	/**
	 * @param codigoBloqueo the codigoBloqueo to set
	 */
	public void setCodigoBloqueo(String codigoBloqueo) {
		this.codigoBloqueo = codigoBloqueo;
	}

	/**
	 * @return the nombreCompletoGerenteZona
	 */
	public String getNombreCompletoGerenteZona() {
		return nombreCompletoGerenteZona;
	}

	/**
	 * @param nombreCompletoGerenteZona the nombreCompletoGerenteZona to set
	 */
	public void setNombreCompletoGerenteZona(String nombreCompletoGerenteZona) {
		this.nombreCompletoGerenteZona = nombreCompletoGerenteZona;
	}

	/**
	 * @return the celularGerenteZona
	 */
	public String getCelularGerenteZona() {
		return celularGerenteZona;
	}

	/**
	 * @param celularGerenteZona the celularGerenteZona to set
	 */
	public void setCelularGerenteZona(String celularGerenteZona) {
		this.celularGerenteZona = celularGerenteZona;
	}

	/**
	 * @return the nombreCompletoLiderSeccion
	 */
	public String getNombreCompletoLiderSeccion() {
		return nombreCompletoLiderSeccion;
	}

	/**
	 * @param nombreCompletoLiderSeccion the nombreCompletoLiderSeccion to set
	 */
	public void setNombreCompletoLiderSeccion(String nombreCompletoLiderSeccion) {
		this.nombreCompletoLiderSeccion = nombreCompletoLiderSeccion;
	}

	/**
	 * @return the celularLiderSeccion
	 */
	public String getCelularLiderSeccion() {
		return celularLiderSeccion;
	}

	/**
	 * @param celularLiderSeccion the celularLiderSeccion to set
	 */
	public void setCelularLiderSeccion(String celularLiderSeccion) {
		this.celularLiderSeccion = celularLiderSeccion;
	}

	/**
	 * @return the fechaCambio
	 */
	public String getFechaCambio() {
		return fechaCambio;
	}

	/**
	 * @param fechaCambio the fechaCambio to set
	 */
	public void setFechaCambio(String fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	/**
	 * @return the estadoFlx
	 */
	public String getEstadoFlx() {
		return estadoFlx;
	}

	/**
	 * @param estadoFlx the estadoFlx to set
	 */
	public void setEstadoFlx(String estadoFlx) {
		this.estadoFlx = estadoFlx;
	}

	/**
	 * @return the fechaActivacionFlx
	 */
	public String getFechaActivacionFlx() {
		return fechaActivacionFlx;
	}

	/**
	 * @param fechaActivacionFlx the fechaActivacionFlx to set
	 */
	public void setFechaActivacionFlx(String fechaActivacionFlx) {
		this.fechaActivacionFlx = fechaActivacionFlx;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getIndActi() {
		return indActi;
	}

	public void setIndActi(String indActi) {
		this.indActi = indActi;
	}

	
}