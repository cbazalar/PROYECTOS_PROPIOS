package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author sbuchelli
 *
 */
public class ClienteReferencias extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoCliente;
	private String tipoReferencia;
	//Referencia Familiar
	private String apellido1RefFamiliar;
	private String apellido2RefFamiliar;
	private String nombre1RefFamiliar;
	private String nombre2RefFamiliar;
	private String direccionRefFamiliar;
	private String barrioRefFamiliar;
	private String telefonoCasaRefFamiliar;
	private String telefonoCelRefFamiliar;
	private String codigoTipoVinculoRefFamiliar;
	//Referencia No Familiar
	private String apellido1RefNoFamiliar;
	private String apellido2RefNoFamiliar;
	private String nombre1RefNoFamiliar;
	private String nombre2RefNoFamiliar;
	private String direccionRefNoFamiliar;
	private String barrioRefNoFamiliar;
	private String telefonoCasaRefNoFamiliar;
	private String telefonoCelRefNoFamiliar;
	private String codigoTipoVinculoRefNoFamiliar;	
	//Referencia A Aval
	private String apellido1Aval;
	private String apellido2Aval;
	private String nombre1Aval;
	private String nombre2Aval;
	private String estado;
	private String municipio;
	private String parroquia;
	private String direccionAval;
	private String barrioAval;
	private String telefonoCasaAval;
	private String telefonoCelAval;
	private String codigoTipoVinculoAval;	
	private String codigoDepartamentoAval;
	private String codigoProvinciaAval;
	private String codigoDistritoAval;
	private String oidTipoDocumentoAval;
	private String numeroDocumentoAval;
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
	/**
	 * @return the oidTipoDocumentoAval
	 */
	public String getOidTipoDocumentoAval() {
		return oidTipoDocumentoAval;
	}

	/**
	 * @param oidTipoDocumentoAval the oidTipoDocumentoAval to set
	 */
	public void setOidTipoDocumentoAval(String oidTipoDocumentoAval) {
		this.oidTipoDocumentoAval = oidTipoDocumentoAval;
	}

	/**
	 * @return the numeroDocumentoAval
	 */
	public String getNumeroDocumentoAval() {
		return numeroDocumentoAval;
	}

	/**
	 * @param numeroDocumentoAval the numeroDocumentoAval to set
	 */
	public void setNumeroDocumentoAval(String numeroDocumentoAval) {
		this.numeroDocumentoAval = numeroDocumentoAval;
	}

	/**
	 * @return the codigoDepartamentoAval
	 */
	public String getCodigoDepartamentoAval() {
		return codigoDepartamentoAval;
	}

	/**
	 * @param codigoDepartamentoAval the codigoDepartamentoAval to set
	 */
	public void setCodigoDepartamentoAval(String codigoDepartamentoAval) {
		this.codigoDepartamentoAval = codigoDepartamentoAval;
	}

	/**
	 * @return the codigoProvinciaAval
	 */
	public String getCodigoProvinciaAval() {
		return codigoProvinciaAval;
	}

	/**
	 * @param codigoProvinciaAval the codigoProvinciaAval to set
	 */
	public void setCodigoProvinciaAval(String codigoProvinciaAval) {
		this.codigoProvinciaAval = codigoProvinciaAval;
	}

	/**
	 * @return the codigoDistritoAval
	 */
	public String getCodigoDistritoAval() {
		return codigoDistritoAval;
	}

	/**
	 * @param codigoDistritoAval the codigoDistritoAval to set
	 */
	public void setCodigoDistritoAval(String codigoDistritoAval) {
		this.codigoDistritoAval = codigoDistritoAval;
	}

	/**
	 * @return the apellido1RefFamiliar
	 */
	public String getApellido1RefFamiliar() {
		return apellido1RefFamiliar;
	}

	/**
	 * @param apellido1RefFamiliar the apellido1RefFamiliar to set
	 */
	public void setApellido1RefFamiliar(String apellido1RefFamiliar) {
		this.apellido1RefFamiliar = apellido1RefFamiliar;
	}

	/**
	 * @return the apellido2RefFamiliar
	 */
	public String getApellido2RefFamiliar() {
		return apellido2RefFamiliar;
	}

	/**
	 * @param apellido2RefFamiliar the apellido2RefFamiliar to set
	 */
	public void setApellido2RefFamiliar(String apellido2RefFamiliar) {
		this.apellido2RefFamiliar = apellido2RefFamiliar;
	}

	/**
	 * @return the nombre1RefFamiliar
	 */
	public String getNombre1RefFamiliar() {
		return nombre1RefFamiliar;
	}

	/**
	 * @param nombre1RefFamiliar the nombre1RefFamiliar to set
	 */
	public void setNombre1RefFamiliar(String nombre1RefFamiliar) {
		this.nombre1RefFamiliar = nombre1RefFamiliar;
	}

	/**
	 * @return the nombre2RefFamiliar
	 */
	public String getNombre2RefFamiliar() {
		return nombre2RefFamiliar;
	}

	/**
	 * @param nombre2RefFamiliar the nombre2RefFamiliar to set
	 */
	public void setNombre2RefFamiliar(String nombre2RefFamiliar) {
		this.nombre2RefFamiliar = nombre2RefFamiliar;
	}

	/**
	 * @return the direccionRefFamiliar
	 */
	public String getDireccionRefFamiliar() {
		return direccionRefFamiliar;
	}

	/**
	 * @param direccionRefFamiliar the direccionRefFamiliar to set
	 */
	public void setDireccionRefFamiliar(String direccionRefFamiliar) {
		this.direccionRefFamiliar = direccionRefFamiliar;
	}

	/**
	 * @return the telefonoCasaRefFamiliar
	 */
	public String getTelefonoCasaRefFamiliar() {
		return telefonoCasaRefFamiliar;
	}

	/**
	 * @param telefonoCasaRefFamiliar the telefonoCasaRefFamiliar to set
	 */
	public void setTelefonoCasaRefFamiliar(String telefonoCasaRefFamiliar) {
		this.telefonoCasaRefFamiliar = telefonoCasaRefFamiliar;
	}

	/**
	 * @return the telefonoCelRefFamiliar
	 */
	public String getTelefonoCelRefFamiliar() {
		return telefonoCelRefFamiliar;
	}

	/**
	 * @param telefonoCelRefFamiliar the telefonoCelRefFamiliar to set
	 */
	public void setTelefonoCelRefFamiliar(String telefonoCelRefFamiliar) {
		this.telefonoCelRefFamiliar = telefonoCelRefFamiliar;
	}

	/**
	 * @return the codigoTipoVinculoRefFamiliar
	 */
	public String getCodigoTipoVinculoRefFamiliar() {
		return codigoTipoVinculoRefFamiliar;
	}

	/**
	 * @param codigoTipoVinculoRefFamiliar the codigoTipoVinculoRefFamiliar to set
	 */
	public void setCodigoTipoVinculoRefFamiliar(String codigoTipoVinculoRefFamiliar) {
		this.codigoTipoVinculoRefFamiliar = codigoTipoVinculoRefFamiliar;
	}

	/**
	 * @return the apellido1RefNoFamiliar
	 */
	public String getApellido1RefNoFamiliar() {
		return apellido1RefNoFamiliar;
	}

	/**
	 * @param apellido1RefNoFamiliar the apellido1RefNoFamiliar to set
	 */
	public void setApellido1RefNoFamiliar(String apellido1RefNoFamiliar) {
		this.apellido1RefNoFamiliar = apellido1RefNoFamiliar;
	}

	/**
	 * @return the apellido2RefNoFamiliar
	 */
	public String getApellido2RefNoFamiliar() {
		return apellido2RefNoFamiliar;
	}

	/**
	 * @param apellido2RefNoFamiliar the apellido2RefNoFamiliar to set
	 */
	public void setApellido2RefNoFamiliar(String apellido2RefNoFamiliar) {
		this.apellido2RefNoFamiliar = apellido2RefNoFamiliar;
	}

	/**
	 * @return the nombre1RefNoFamiliar
	 */
	public String getNombre1RefNoFamiliar() {
		return nombre1RefNoFamiliar;
	}

	/**
	 * @param nombre1RefNoFamiliar the nombre1RefNoFamiliar to set
	 */
	public void setNombre1RefNoFamiliar(String nombre1RefNoFamiliar) {
		this.nombre1RefNoFamiliar = nombre1RefNoFamiliar;
	}

	/**
	 * @return the nombre2RefNoFamiliar
	 */
	public String getNombre2RefNoFamiliar() {
		return nombre2RefNoFamiliar;
	}

	/**
	 * @param nombre2RefNoFamiliar the nombre2RefNoFamiliar to set
	 */
	public void setNombre2RefNoFamiliar(String nombre2RefNoFamiliar) {
		this.nombre2RefNoFamiliar = nombre2RefNoFamiliar;
	}

	/**
	 * @return the direccionRefNoFamiliar
	 */
	public String getDireccionRefNoFamiliar() {
		return direccionRefNoFamiliar;
	}

	/**
	 * @param direccionRefNoFamiliar the direccionRefNoFamiliar to set
	 */
	public void setDireccionRefNoFamiliar(String direccionRefNoFamiliar) {
		this.direccionRefNoFamiliar = direccionRefNoFamiliar;
	}

	/**
	 * @return the telefonoCasaRefNoFamiliar
	 */
	public String getTelefonoCasaRefNoFamiliar() {
		return telefonoCasaRefNoFamiliar;
	}

	/**
	 * @param telefonoCasaRefNoFamiliar the telefonoCasaRefNoFamiliar to set
	 */
	public void setTelefonoCasaRefNoFamiliar(String telefonoCasaRefNoFamiliar) {
		this.telefonoCasaRefNoFamiliar = telefonoCasaRefNoFamiliar;
	}

	/**
	 * @return the telefonoCelRefNoFamiliar
	 */
	public String getTelefonoCelRefNoFamiliar() {
		return telefonoCelRefNoFamiliar;
	}

	/**
	 * @param telefonoCelRefNoFamiliar the telefonoCelRefNoFamiliar to set
	 */
	public void setTelefonoCelRefNoFamiliar(String telefonoCelRefNoFamiliar) {
		this.telefonoCelRefNoFamiliar = telefonoCelRefNoFamiliar;
	}

	/**
	 * @return the codigoTipoVinculoRefNoFamiliar
	 */
	public String getCodigoTipoVinculoRefNoFamiliar() {
		return codigoTipoVinculoRefNoFamiliar;
	}

	/**
	 * @param codigoTipoVinculoRefNoFamiliar the codigoTipoVinculoRefNoFamiliar to set
	 */
	public void setCodigoTipoVinculoRefNoFamiliar(
			String codigoTipoVinculoRefNoFamiliar) {
		this.codigoTipoVinculoRefNoFamiliar = codigoTipoVinculoRefNoFamiliar;
	}

	/**
	 * @return the apellido1Aval
	 */
	public String getApellido1Aval() {
		return apellido1Aval;
	}

	/**
	 * @param apellido1Aval the apellido1Aval to set
	 */
	public void setApellido1Aval(String apellido1Aval) {
		this.apellido1Aval = apellido1Aval;
	}

	/**
	 * @return the apellido2Aval
	 */
	public String getApellido2Aval() {
		return apellido2Aval;
	}

	/**
	 * @param apellido2Aval the apellido2Aval to set
	 */
	public void setApellido2Aval(String apellido2Aval) {
		this.apellido2Aval = apellido2Aval;
	}

	/**
	 * @return the nombre1Aval
	 */
	public String getNombre1Aval() {
		return nombre1Aval;
	}

	/**
	 * @param nombre1Aval the nombre1Aval to set
	 */
	public void setNombre1Aval(String nombre1Aval) {
		this.nombre1Aval = nombre1Aval;
	}

	/**
	 * @return the nombre2Aval
	 */
	public String getNombre2Aval() {
		return nombre2Aval;
	}

	/**
	 * @param nombre2Aval the nombre2Aval to set
	 */
	public void setNombre2Aval(String nombre2Aval) {
		this.nombre2Aval = nombre2Aval;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the parroquia
	 */
	public String getParroquia() {
		return parroquia;
	}

	/**
	 * @param parroquia the parroquia to set
	 */
	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	/**
	 * @return the direccionAval
	 */
	public String getDireccionAval() {
		return direccionAval;
	}

	/**
	 * @param direccionAval the direccionAval to set
	 */
	public void setDireccionAval(String direccionAval) {
		this.direccionAval = direccionAval;
	}

	/**
	 * @return the telefonoCasaAval
	 */
	public String getTelefonoCasaAval() {
		return telefonoCasaAval;
	}

	/**
	 * @param telefonoCasaAval the telefonoCasaAval to set
	 */
	public void setTelefonoCasaAval(String telefonoCasaAval) {
		this.telefonoCasaAval = telefonoCasaAval;
	}

	/**
	 * @return the telefonoCelAval
	 */
	public String getTelefonoCelAval() {
		return telefonoCelAval;
	}

	/**
	 * @param telefonoCelAval the telefonoCelAval to set
	 */
	public void setTelefonoCelAval(String telefonoCelAval) {
		this.telefonoCelAval = telefonoCelAval;
	}

	/**
	 * @return the codigoTipoVinculoAval
	 */
	public String getCodigoTipoVinculoAval() {
		return codigoTipoVinculoAval;
	}

	/**
	 * @param codigoTipoVinculoAval the codigoTipoVinculoAval to set
	 */
	public void setCodigoTipoVinculoAval(String codigoTipoVinculoAval) {
		this.codigoTipoVinculoAval = codigoTipoVinculoAval;
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
		return ReflectionToStringBuilder.toString(this);  
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
	 * @return the tipoReferencia
	 */
	public String getTipoReferencia() {
		return tipoReferencia;
	}

	/**
	 * @param tipoReferencia the tipoReferencia to set
	 */
	public void setTipoReferencia(String tipoReferencia) {
		this.tipoReferencia = tipoReferencia;
	}

	/**
	 * @return the barrioRefFamiliar
	 */
	public String getBarrioRefFamiliar() {
		return barrioRefFamiliar;
	}

	/**
	 * @param barrioRefFamiliar the barrioRefFamiliar to set
	 */
	public void setBarrioRefFamiliar(String barrioRefFamiliar) {
		this.barrioRefFamiliar = barrioRefFamiliar;
	}

	/**
	 * @return the barrioRefNoFamiliar
	 */
	public String getBarrioRefNoFamiliar() {
		return barrioRefNoFamiliar;
	}

	/**
	 * @param barrioRefNoFamiliar the barrioRefNoFamiliar to set
	 */
	public void setBarrioRefNoFamiliar(String barrioRefNoFamiliar) {
		this.barrioRefNoFamiliar = barrioRefNoFamiliar;
	}

	/**
	 * @return the barrioAval
	 */
	public String getBarrioAval() {
		return barrioAval;
	}

	/**
	 * @param barrioAval the barrioAval to set
	 */
	public void setBarrioAval(String barrioAval) {
		this.barrioAval = barrioAval;
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

}
