package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

public class DatosMasivo implements Serializable{

	private String codigoCliente;
	private String pedido;
	private String codigoSap ;
	private String cantidad;
	private String estadoProceso;
	private String descError;
	private String cuv;
	private String operacion;
	private String tipo;
	private String unidades;
	private String codigoMotivo;
	
	
	/**
	 * @return the unidades
	 */
	public String getUnidades() {
		return unidades;
	}
	/**
	 * @param unidades the unidades to set
	 */
	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}
	/**
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}
	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the cuv
	 */
	public String getCuv() {
		return cuv;
	}
	/**
	 * @param cuv the cuv to set
	 */
	public void setCuv(String cuv) {
		this.cuv = cuv;
	}
	/**
	 * @return the estadoProceso
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}
	/**
	 * @param estadoProceso the estadoProceso to set
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	/**
	 * @return the descError
	 */
	public String getDescError() {
		return descError;
	}
	/**
	 * @param descError the descError to set
	 */
	public void setDescError(String descError) {
		this.descError = descError;
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
	 * @return the pedido
	 */
	public String getPedido() {
		return pedido;
	}
	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	/**
	 * @return the codigoSap
	 */
	public String getCodigoSap() {
		return codigoSap;
	}
	/**
	 * @param codigoSap the codigoSap to set
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
	 * @return the codigoMotivo
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}
	
	/**
	 * @param codigoMotivo the codigoMotivo to set
	 */	
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	
	public DatosMasivo(String codigoCliente, String pedido, String codigoSap,
			String cantidad) {
		super();
		this.codigoCliente = codigoCliente;
		this.pedido = pedido;
		this.codigoSap = codigoSap;
		this.cantidad = cantidad;
	}
	
	public DatosMasivo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
