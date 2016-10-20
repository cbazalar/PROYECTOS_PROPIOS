/*
 * Created on 29/12/2015 10:04:15 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaFacturaRMSWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaFacturaRMSWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado
 * 
 * 
 */
public class ConsultaFacturaRMSWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String numeroFactura;
	private String codigoCliente;		
	private String fechaFactura;	
	private String codigoSap;
	private String codigoVenta;
	private String codigoProducto;
	private String numeroUnidadesAtendidas;
	private String valorTotal;
	private String tipoDocumento;
	private String codigoUbigeo;
	private String codigoPeriodo; 
	private String numeroUnidadesReclamadas;
	private String numeroUnidadesOtros;
	private String numeroPedido;	
	private String montoPedido;
	private String montoDevolucion;
	private String porcentajeDevolucion;
	
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
	 * @return the numeroFactura
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}
	/**
	 * @param numeroFactura the numeroFactura to set
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
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
	 * @return the fechaFactura
	 */
	public String getFechaFactura() {
		return fechaFactura;
	}
	/**
	 * @param fechaFactura the fechaFactura to set
	 */
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
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
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return the numeroUnidadesAtendidas
	 */
	public String getNumeroUnidadesAtendidas() {
		return numeroUnidadesAtendidas;
	}
	/**
	 * @param numeroUnidadesAtendidas the numeroUnidadesAtendidas to set
	 */
	public void setNumeroUnidadesAtendidas(String numeroUnidadesAtendidas) {
		this.numeroUnidadesAtendidas = numeroUnidadesAtendidas;
	}
	/**
	 * @return the valorTotal
	 */
	public String getValorTotal() {
		return valorTotal;
	}
	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the codigoUbigeo
	 */
	public String getCodigoUbigeo() {
		return codigoUbigeo;
	}
	/**
	 * @param codigoUbigeo the codigoUbigeo to set
	 */
	public void setCodigoUbigeo(String codigoUbigeo) {
		this.codigoUbigeo = codigoUbigeo;
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
	 * @return the numeroUnidadesReclamadas
	 */
	public String getNumeroUnidadesReclamadas() {
		return numeroUnidadesReclamadas;
	}
	/**
	 * @param numeroUnidadesReclamadas the numeroUnidadesReclamadas to set
	 */
	public void setNumeroUnidadesReclamadas(String numeroUnidadesReclamadas) {
		this.numeroUnidadesReclamadas = numeroUnidadesReclamadas;
	}
	/**
	 * @return the numeroUnidadesOtros
	 */
	public String getNumeroUnidadesOtros() {
		return numeroUnidadesOtros;
	}
	/**
	 * @param numeroUnidadesOtros the numeroUnidadesOtros to set
	 */
	public void setNumeroUnidadesOtros(String numeroUnidadesOtros) {
		this.numeroUnidadesOtros = numeroUnidadesOtros;
	}
	/**
	 * @return the numeroPedido
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}
	/**
	 * @param numeroPedido the numeroPedido to set
	 */
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	/**
	 * @return the montoPedido
	 */
	public String getMontoPedido() {
		return montoPedido;
	}
	/**
	 * @param montoPedido the montoPedido to set
	 */
	public void setMontoPedido(String montoPedido) {
		this.montoPedido = montoPedido;
	}
	/**
	 * @return the montoDevolucion
	 */
	public String getMontoDevolucion() {
		return montoDevolucion;
	}
	/**
	 * @param montoDevolucion the montoDevolucion to set
	 */
	public void setMontoDevolucion(String montoDevolucion) {
		this.montoDevolucion = montoDevolucion;
	}
	/**
	 * @return the porcentajeDevolucion
	 */
	public String getPorcentajeDevolucion() {
		return porcentajeDevolucion;
	}
	/**
	 * @param porcentajeDevolucion the porcentajeDevolucion to set
	 */
	public void setPorcentajeDevolucion(String porcentajeDevolucion) {
		this.porcentajeDevolucion = porcentajeDevolucion;
	}
		
	
}