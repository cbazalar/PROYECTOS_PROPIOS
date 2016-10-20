/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.scsicc.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlPERAsistenciaWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ControlPERAsistenciaWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoZona;
	private String codigoSeccion;
	private String codigoTerritorio;
	private String nombreCliente;
	private String fechaFacturacion;
	private String nomGere;
	private String saldo;
	private String valObservacion;//ciclo vida
	private String codigoCliente;
	private String telefonoMovil;
	private String valFamiliaProtegida;
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @return the codigoTerritorio
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}
	/**
	 * @param codigoTerritorio the codigoTerritorio to set
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return the nomGere
	 */
	public String getNomGere() {
		return nomGere;
	}
	/**
	 * @param nomGere the nomGere to set
	 */
	public void setNomGere(String nomGere) {
		this.nomGere = nomGere;
	}
	/**
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the valObservacion
	 */
	public String getValObservacion() {
		return valObservacion;
	}
	/**
	 * @param valObservacion the valObservacion to set
	 */
	public void setValObservacion(String valObservacion) {
		this.valObservacion = valObservacion;
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
	 * @return the telefonoMovil
	 */
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	/**
	 * @param telefonoMovil the telefonoMovil to set
	 */
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	/**
	 * @return the valFamiliaProtegida
	 */
	public String getValFamiliaProtegida() {
		return valFamiliaProtegida;
	}
	/**
	 * @param valFamiliaProtegida the valFamiliaProtegida to set
	 */
	public void setValFamiliaProtegida(String valFamiliaProtegida) {
		this.valFamiliaProtegida = valFamiliaProtegida;
	}
	
	
	
}
