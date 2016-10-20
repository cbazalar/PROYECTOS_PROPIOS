package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteHistoricoDatos extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoCliente;
	private String codigoPais;
	
	private String primerApellido;
	private String segundoApellido;
	private String primerNombre;
	private String segundoNombre;

	private String fechaNacimiento;

	private String numeroDocumento;
	
	private String telefonoCelular;
	private String telefonoFijo;
	private String email;
	
	private String ubigeo;
	private String tipoVia;
	private String numeroPrincipal;
	private String barrio;
	private String direccion;
	private String referencia;
	
	private String region;
	private String zona;
	private String seccion;
	private String territorio;
	
	private String primerApellidoAnterior;
	private String segundoApellidoAnterior;
	private String primerNombreAnterior;
	private String segundoNombreAnterior;

	private String fechaNacimientoAnterior;

	private String numeroDocumentoAnterior;
	
	private String telefonoCelularAnterior;
	private String telefonoFijoAnterior;
	private String emailAnterior;
	
	private String ubigeoAnterior;
	private String tipoViaAnterior;
	private String numeroPrincipalAnterior;
	private String barrioAnterior;
	private String direccionAnterior;
	private String referenciaAnterior;
	
	private String regionAnterior;
	private String zonaAnterior;
	private String seccionAnterior;
	private String territorioAnterior;
	
	private String codigoUsuario;
	private String indicadorOrigen;
	
	public ClienteHistoricoDatos() {
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the primerApellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * @param primerApellido the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * @return the segundoApellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * @param segundoApellido the segundoApellido to set
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @param primerNombre the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @param segundoNombre the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
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
	 * @return the telefonoFijo
	 */
	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	/**
	 * @param telefonoFijo the telefonoFijo to set
	 */
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the ubigeo
	 */
	public String getUbigeo() {
		return ubigeo;
	}

	/**
	 * @param ubigeo the ubigeo to set
	 */
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	/**
	 * @return the tipoVia
	 */
	public String getTipoVia() {
		return tipoVia;
	}

	/**
	 * @param tipoVia the tipoVia to set
	 */
	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	/**
	 * @return the numeroPrincipal
	 */
	public String getNumeroPrincipal() {
		return numeroPrincipal;
	}

	/**
	 * @param numeroPrincipal the numeroPrincipal to set
	 */
	public void setNumeroPrincipal(String numeroPrincipal) {
		this.numeroPrincipal = numeroPrincipal;
	}

	/**
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}

	/**
	 * @param barrio the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the seccion
	 */
	public String getSeccion() {
		return seccion;
	}

	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	/**
	 * @return the territorio
	 */
	public String getTerritorio() {
		return territorio;
	}

	/**
	 * @param territorio the territorio to set
	 */
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	/**
	 * @return the primerApellidoAnterior
	 */
	public String getPrimerApellidoAnterior() {
		return primerApellidoAnterior;
	}

	/**
	 * @param primerApellidoAnterior the primerApellidoAnterior to set
	 */
	public void setPrimerApellidoAnterior(String primerApellidoAnterior) {
		this.primerApellidoAnterior = primerApellidoAnterior;
	}

	/**
	 * @return the segundoApellidoAnterior
	 */
	public String getSegundoApellidoAnterior() {
		return segundoApellidoAnterior;
	}

	/**
	 * @param segundoApellidoAnterior the segundoApellidoAnterior to set
	 */
	public void setSegundoApellidoAnterior(String segundoApellidoAnterior) {
		this.segundoApellidoAnterior = segundoApellidoAnterior;
	}

	/**
	 * @return the primerNombreAnterior
	 */
	public String getPrimerNombreAnterior() {
		return primerNombreAnterior;
	}

	/**
	 * @param primerNombreAnterior the primerNombreAnterior to set
	 */
	public void setPrimerNombreAnterior(String primerNombreAnterior) {
		this.primerNombreAnterior = primerNombreAnterior;
	}

	/**
	 * @return the segundoNombreAnterior
	 */
	public String getSegundoNombreAnterior() {
		return segundoNombreAnterior;
	}

	/**
	 * @param segundoNombreAnterior the segundoNombreAnterior to set
	 */
	public void setSegundoNombreAnterior(String segundoNombreAnterior) {
		this.segundoNombreAnterior = segundoNombreAnterior;
	}

	/**
	 * @return the fechaNacimientoAnterior
	 */
	public String getFechaNacimientoAnterior() {
		return fechaNacimientoAnterior;
	}

	/**
	 * @param fechaNacimientoAnterior the fechaNacimientoAnterior to set
	 */
	public void setFechaNacimientoAnterior(String fechaNacimientoAnterior) {
		this.fechaNacimientoAnterior = fechaNacimientoAnterior;
	}

	/**
	 * @return the telefonoCelularAnterior
	 */
	public String getTelefonoCelularAnterior() {
		return telefonoCelularAnterior;
	}

	/**
	 * @param telefonoCelularAnterior the telefonoCelularAnterior to set
	 */
	public void setTelefonoCelularAnterior(String telefonoCelularAnterior) {
		this.telefonoCelularAnterior = telefonoCelularAnterior;
	}

	/**
	 * @return the telefonoFijoAnterior
	 */
	public String getTelefonoFijoAnterior() {
		return telefonoFijoAnterior;
	}

	/**
	 * @param telefonoFijoAnterior the telefonoFijoAnterior to set
	 */
	public void setTelefonoFijoAnterior(String telefonoFijoAnterior) {
		this.telefonoFijoAnterior = telefonoFijoAnterior;
	}

	/**
	 * @return the emailAnterior
	 */
	public String getEmailAnterior() {
		return emailAnterior;
	}

	/**
	 * @param emailAnterior the emailAnterior to set
	 */
	public void setEmailAnterior(String emailAnterior) {
		this.emailAnterior = emailAnterior;
	}

	/**
	 * @return the ubigeoAnterior
	 */
	public String getUbigeoAnterior() {
		return ubigeoAnterior;
	}

	/**
	 * @param ubigeoAnterior the ubigeoAnterior to set
	 */
	public void setUbigeoAnterior(String ubigeoAnterior) {
		this.ubigeoAnterior = ubigeoAnterior;
	}

	/**
	 * @return the tipoViaAnterior
	 */
	public String getTipoViaAnterior() {
		return tipoViaAnterior;
	}

	/**
	 * @param tipoViaAnterior the tipoViaAnterior to set
	 */
	public void setTipoViaAnterior(String tipoViaAnterior) {
		this.tipoViaAnterior = tipoViaAnterior;
	}

	/**
	 * @return the numeroPrincipalAnterior
	 */
	public String getNumeroPrincipalAnterior() {
		return numeroPrincipalAnterior;
	}

	/**
	 * @param numeroPrincipalAnterior the numeroPrincipalAnterior to set
	 */
	public void setNumeroPrincipalAnterior(String numeroPrincipalAnterior) {
		this.numeroPrincipalAnterior = numeroPrincipalAnterior;
	}

	/**
	 * @return the barrioAnterior
	 */
	public String getBarrioAnterior() {
		return barrioAnterior;
	}

	/**
	 * @param barrioAnterior the barrioAnterior to set
	 */
	public void setBarrioAnterior(String barrioAnterior) {
		this.barrioAnterior = barrioAnterior;
	}

	/**
	 * @return the direccionAnterior
	 */
	public String getDireccionAnterior() {
		return direccionAnterior;
	}

	/**
	 * @param direccionAnterior the direccionAnterior to set
	 */
	public void setDireccionAnterior(String direccionAnterior) {
		this.direccionAnterior = direccionAnterior;
	}

	/**
	 * @return the referenciaAnterior
	 */
	public String getReferenciaAnterior() {
		return referenciaAnterior;
	}

	/**
	 * @param referenciaAnterior the referenciaAnterior to set
	 */
	public void setReferenciaAnterior(String referenciaAnterior) {
		this.referenciaAnterior = referenciaAnterior;
	}

	/**
	 * @return the regionAnterior
	 */
	public String getRegionAnterior() {
		return regionAnterior;
	}

	/**
	 * @param regionAnterior the regionAnterior to set
	 */
	public void setRegionAnterior(String regionAnterior) {
		this.regionAnterior = regionAnterior;
	}

	/**
	 * @return the zonaAnterior
	 */
	public String getZonaAnterior() {
		return zonaAnterior;
	}

	/**
	 * @param zonaAnterior the zonaAnterior to set
	 */
	public void setZonaAnterior(String zonaAnterior) {
		this.zonaAnterior = zonaAnterior;
	}

	/**
	 * @return the seccionAnterior
	 */
	public String getSeccionAnterior() {
		return seccionAnterior;
	}

	/**
	 * @param seccionAnterior the seccionAnterior to set
	 */
	public void setSeccionAnterior(String seccionAnterior) {
		this.seccionAnterior = seccionAnterior;
	}

	/**
	 * @return the territorioAnterior
	 */
	public String getTerritorioAnterior() {
		return territorioAnterior;
	}

	/**
	 * @param territorioAnterior the territorioAnterior to set
	 */
	public void setTerritorioAnterior(String territorioAnterior) {
		this.territorioAnterior = territorioAnterior;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the indicadorOrigen
	 */
	public String getIndicadorOrigen() {
		return indicadorOrigen;
	}

	/**
	 * @param indicadorOrigen the indicadorOrigen to set
	 */
	public void setIndicadorOrigen(String indicadorOrigen) {
		this.indicadorOrigen = indicadorOrigen;
	}

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
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the numeroDocumentoAnterior
	 */
	public String getNumeroDocumentoAnterior() {
		return numeroDocumentoAnterior;
	}

	/**
	 * @param numeroDocumentoAnterior the numeroDocumentoAnterior to set
	 */
	public void setNumeroDocumentoAnterior(String numeroDocumentoAnterior) {
		this.numeroDocumentoAnterior = numeroDocumentoAnterior;
	}

}
