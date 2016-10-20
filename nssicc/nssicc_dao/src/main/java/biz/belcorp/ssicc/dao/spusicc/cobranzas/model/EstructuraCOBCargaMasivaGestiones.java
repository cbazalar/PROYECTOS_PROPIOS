/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

/**
 * @author Gonzalo Huertas
 *
 */
public class EstructuraCOBCargaMasivaGestiones implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8276586654848013731L;
	private String numeroOrden;
	private String codigoCobrador;
	private String codigoDeuda;
	private String codigoPeriodo;
	private String codigoCliente;
	private String tipoAccion;
	private String codigoAccion;
	private String observacion;
	private String fechaGestion;
	private String fechaCompromisoPago;
	private String importeCompromisoPago;
	private String nuevaDireccion;
	private String nuevoTelefono;
	private String tipoTelefono;
	
	private boolean registroOK;
	
	private int fila;
	
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the numeroOrden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * @param numeroOrden the numeroOrden to set
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * @return the codigoCobrador
	 */
	public String getCodigoCobrador() {
		return codigoCobrador;
	}

	/**
	 * @param codigoCobrador the codigoCobrador to set
	 */
	public void setCodigoCobrador(String codigoCobrador) {
		this.codigoCobrador = codigoCobrador;
	}

	/**
	 * @return the codigoDeuda
	 */
	public String getCodigoDeuda() {
		return codigoDeuda;
	}

	/**
	 * @param codigoDeuda the codigoDeuda to set
	 */
	public void setCodigoDeuda(String codigoDeuda) {
		this.codigoDeuda = codigoDeuda;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return the tipoAccion
	 */
	public String getTipoAccion() {
		return tipoAccion;
	}

	/**
	 * @param tipoAccion the tipoAccion to set
	 */
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	/**
	 * @return the codigoAccion
	 */
	public String getCodigoAccion() {
		return codigoAccion;
	}

	/**
	 * @param codigoAccion the codigoAccion to set
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the fechaGestion
	 */
	public String getFechaGestion() {
		return fechaGestion;
	}

	/**
	 * @param fechaGestion the fechaGestion to set
	 */
	public void setFechaGestion(String fechaGestion) {
		this.fechaGestion = fechaGestion;
	}

	/**
	 * @return the fechaCompromisoPago
	 */
	public String getFechaCompromisoPago() {
		return fechaCompromisoPago;
	}

	/**
	 * @param fechaCompromisoPago the fechaCompromisoPago to set
	 */
	public void setFechaCompromisoPago(String fechaCompromisoPago) {
		this.fechaCompromisoPago = fechaCompromisoPago;
	}

	/**
	 * @return the importeCompromisoPago
	 */
	public String getImporteCompromisoPago() {
		return importeCompromisoPago;
	}

	/**
	 * @param importeCompromisoPago the importeCompromisoPago to set
	 */
	public void setImporteCompromisoPago(String importeCompromisoPago) {
		this.importeCompromisoPago = importeCompromisoPago;
	}

	/**
	 * @return the nuevaDireccion
	 */
	public String getNuevaDireccion() {
		return nuevaDireccion;
	}

	/**
	 * @param nuevaDireccion the nuevaDireccion to set
	 */
	public void setNuevaDireccion(String nuevaDireccion) {
		this.nuevaDireccion = nuevaDireccion;
	}

	/**
	 * @return the nuevoTelefono
	 */
	public String getNuevoTelefono() {
		return nuevoTelefono;
	}

	/**
	 * @param nuevoTelefono the nuevoTelefono to set
	 */
	public void setNuevoTelefono(String nuevoTelefono) {
		this.nuevoTelefono = nuevoTelefono;
	}

	/**
	 * @return the tipoTelefono
	 */
	public String getTipoTelefono() {
		return tipoTelefono;
	}

	/**
	 * @param tipoTelefono the tipoTelefono to set
	 */
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	/**
	 * @return the fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * @param fila the fila to set
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * @return the registroOK
	 */
	public boolean isRegistroOK() {
		return registroOK;
	}

	/**
	 * @param registroOK the registroOK to set
	 */
	public void setRegistroOK(boolean registroOK) {
		this.registroOK = registroOK;
	}
}
