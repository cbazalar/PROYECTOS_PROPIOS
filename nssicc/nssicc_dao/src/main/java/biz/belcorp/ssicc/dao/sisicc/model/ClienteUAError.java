/*
 * Created on 03-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAError.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class ClienteUAError extends AuditableBaseObject implements Serializable {

	private String codigoPais;
	private String codigoCliente;
	private String numeroDocumento;
	private String nombre;
	private String apellidoPaterno;
	private String nombreVia;
	private String numeroPrincipal;
	private String codigoUbigeo;
	private String descripcionUbigeo;
	private String observacionDireccion;
	private String valorError1;
	private String valorError2;
	private String valorError3;
	private String estado;

	
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
	 * @return Returns the codigoUbigeo.
	 */
	public String getCodigoUbigeo() {
		return codigoUbigeo;
	}
	/**
	 * @param codigoUbigeo The codigoUbigeo to set.
	 */
	public void setCodigoUbigeo(String codigoUbigeo) {
		this.codigoUbigeo = codigoUbigeo;
	}
	/**
	 * @return Returns the descripcionUbigeo.
	 */
	public String getDescripcionUbigeo() {
		return descripcionUbigeo;
	}
	/**
	 * @param descripcionUbigeo The descripcionUbigeo to set.
	 */
	public void setDescripcionUbigeo(String descripcionUbigeo) {
		this.descripcionUbigeo = descripcionUbigeo;
	}
	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Returns the nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre The nombre to set.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return Returns the nombreVia.
	 */
	public String getNombreVia() {
		return nombreVia;
	}
	/**
	 * @param nombreVia The nombreVia to set.
	 */
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}
	/**
	 * @return Returns the numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento The numeroDocumento to set.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return Returns the numeroPrincipal.
	 */
	public String getNumeroPrincipal() {
		return numeroPrincipal;
	}
	/**
	 * @param numeroPrincipal The numeroPrincipal to set.
	 */
	public void setNumeroPrincipal(String numeroPrincipal) {
		this.numeroPrincipal = numeroPrincipal;
	}
	/**
	 * @return Returns the observacionDireccion.
	 */
	public String getObservacionDireccion() {
		return observacionDireccion;
	}
	/**
	 * @param observacionDireccion The observacionDireccion to set.
	 */
	public void setObservacionDireccion(String observacionDireccion) {
		this.observacionDireccion = observacionDireccion;
	}
	/**
	 * @return Returns the valorError1.
	 */
	public String getValorError1() {
		return valorError1;
	}
	/**
	 * @param valorError1 The valorError1 to set.
	 */
	public void setValorError1(String valorError1) {
		this.valorError1 = valorError1;
	}
	/**
	 * @return Returns the valorError2.
	 */
	public String getValorError2() {
		return valorError2;
	}
	/**
	 * @param valorError2 The valorError2 to set.
	 */
	public void setValorError2(String valorError2) {
		this.valorError2 = valorError2;
	}
	/**
	 * @return Returns the valorError3.
	 */
	public String getValorError3() {
		return valorError3;
	}
	/**
	 * @param valorError3 The valorError3 to set.
	 */
	public void setValorError3(String valorError3) {
		this.valorError3 = valorError3;
	}
	/* 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
