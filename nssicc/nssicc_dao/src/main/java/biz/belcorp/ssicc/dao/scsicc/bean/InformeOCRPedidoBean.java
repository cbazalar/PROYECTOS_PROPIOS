/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.dao.scsicc.bean;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InformeOCRWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class InformeOCRPedidoBean implements Serializable {

	private static final long serialVersionUID = 8591414961319985519L;
	private String codigoCliente;
	private String codigoPeriodo;
	private String numeroLote;
	private String fechaSolicitud;
	private String nombreCliente;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String codigoTerritorio;
	private String estado;
	private String telefono1;
	private String telefono2;
	private String direccion;
	private String indicadorRecepcionDigiDist;
	private String indicadorRecepcionWeb;
	private String indicadorRecepcionOcr;
	private String indicadorErrorDeuda;
	private String saldoDeudor;
	private String indicadorAdmiCartera;
	private String observacionPrueba;
	private String tipoOrden;
	private String codigoErrados;
	private String montoPedido;
	private String montoPedidoSinFa;
	private String montoOcr;
	private String montoWeb;
	private String montoDigitacionDistr;
	private String numeroFa;
	private String facturado;
	private String errorMontoMinimo;
	private String errorMontoMaximo;
	private String montoPedidoBloq;
	private String montoMinimo;
	private String montoMaximo;
	private String pedidosEstimados;
	
	
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
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	/**
	 * @return the fechaSolicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
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
	 * @return the telefono1
	 */
	public String getTelefono1() {
		return telefono1;
	}
	/**
	 * @param telefono1 the telefono1 to set
	 */
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	/**
	 * @return the telefono2
	 */
	public String getTelefono2() {
		return telefono2;
	}
	/**
	 * @param telefono2 the telefono2 to set
	 */
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
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
	 * @return the indicadorRecepcionDigiDist
	 */
	public String getIndicadorRecepcionDigiDist() {
		return indicadorRecepcionDigiDist;
	}
	/**
	 * @param indicadorRecepcionDigiDist the indicadorRecepcionDigiDist to set
	 */
	public void setIndicadorRecepcionDigiDist(String indicadorRecepcionDigiDist) {
		this.indicadorRecepcionDigiDist = indicadorRecepcionDigiDist;
	}
	/**
	 * @return the indicadorRecepcionWeb
	 */
	public String getIndicadorRecepcionWeb() {
		return indicadorRecepcionWeb;
	}
	/**
	 * @param indicadorRecepcionWeb the indicadorRecepcionWeb to set
	 */
	public void setIndicadorRecepcionWeb(String indicadorRecepcionWeb) {
		this.indicadorRecepcionWeb = indicadorRecepcionWeb;
	}
	/**
	 * @return the indicadorRecepcionOcr
	 */
	public String getIndicadorRecepcionOcr() {
		return indicadorRecepcionOcr;
	}
	/**
	 * @param indicadorRecepcionOcr the indicadorRecepcionOcr to set
	 */
	public void setIndicadorRecepcionOcr(String indicadorRecepcionOcr) {
		this.indicadorRecepcionOcr = indicadorRecepcionOcr;
	}
	/**
	 * @return the indicadorErrorDeuda
	 */
	public String getIndicadorErrorDeuda() {
		return indicadorErrorDeuda;
	}
	/**
	 * @param indicadorErrorDeuda the indicadorErrorDeuda to set
	 */
	public void setIndicadorErrorDeuda(String indicadorErrorDeuda) {
		this.indicadorErrorDeuda = indicadorErrorDeuda;
	}
	/**
	 * @return the saldoDeudor
	 */
	public String getSaldoDeudor() {
		return saldoDeudor;
	}
	/**
	 * @param saldoDeudor the saldoDeudor to set
	 */
	public void setSaldoDeudor(String saldoDeudor) {
		this.saldoDeudor = saldoDeudor;
	}
	/**
	 * @return the indicadorAdmiCartera
	 */
	public String getIndicadorAdmiCartera() {
		return indicadorAdmiCartera;
	}
	/**
	 * @param indicadorAdmiCartera the indicadorAdmiCartera to set
	 */
	public void setIndicadorAdmiCartera(String indicadorAdmiCartera) {
		this.indicadorAdmiCartera = indicadorAdmiCartera;
	}
	/**
	 * @return the observacionPrueba
	 */
	public String getObservacionPrueba() {
		return observacionPrueba;
	}
	/**
	 * @param observacionPrueba the observacionPrueba to set
	 */
	public void setObservacionPrueba(String observacionPrueba) {
		this.observacionPrueba = observacionPrueba;
	}
	/**
	 * @return the tipoOrden
	 */
	public String getTipoOrden() {
		return tipoOrden;
	}
	/**
	 * @param tipoOrden the tipoOrden to set
	 */
	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}
	/**
	 * @return the codigoErrados
	 */
	public String getCodigoErrados() {
		return codigoErrados;
	}
	/**
	 * @param codigoErrados the codigoErrados to set
	 */
	public void setCodigoErrados(String codigoErrados) {
		this.codigoErrados = codigoErrados;
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
	 * @return the montoPedidoSinFa
	 */
	public String getMontoPedidoSinFa() {
		return montoPedidoSinFa;
	}
	/**
	 * @param montoPedidoSinFa the montoPedidoSinFa to set
	 */
	public void setMontoPedidoSinFa(String montoPedidoSinFa) {
		this.montoPedidoSinFa = montoPedidoSinFa;
	}
	/**
	 * @return the montoOcr
	 */
	public String getMontoOcr() {
		return montoOcr;
	}
	/**
	 * @param montoOcr the montoOcr to set
	 */
	public void setMontoOcr(String montoOcr) {
		this.montoOcr = montoOcr;
	}
	/**
	 * @return the montoWeb
	 */
	public String getMontoWeb() {
		return montoWeb;
	}
	/**
	 * @param montoWeb the montoWeb to set
	 */
	public void setMontoWeb(String montoWeb) {
		this.montoWeb = montoWeb;
	}
	/**
	 * @return the montoDigitacionDistr
	 */
	public String getMontoDigitacionDistr() {
		return montoDigitacionDistr;
	}
	/**
	 * @param montoDigitacionDistr the montoDigitacionDistr to set
	 */
	public void setMontoDigitacionDistr(String montoDigitacionDistr) {
		this.montoDigitacionDistr = montoDigitacionDistr;
	}
	/**
	 * @return the numeroFa
	 */
	public String getNumeroFa() {
		return numeroFa;
	}
	/**
	 * @param numeroFa the numeroFa to set
	 */
	public void setNumeroFa(String numeroFa) {
		this.numeroFa = numeroFa;
	}
	/**
	 * @return the facturado
	 */
	public String getFacturado() {
		return facturado;
	}
	/**
	 * @param facturado the facturado to set
	 */
	public void setFacturado(String facturado) {
		this.facturado = facturado;
	}
	/**
	 * @return the errorMontoMinimo
	 */
	public String getErrorMontoMinimo() {
		return errorMontoMinimo;
	}
	/**
	 * @param errorMontoMinimo the errorMontoMinimo to set
	 */
	public void setErrorMontoMinimo(String errorMontoMinimo) {
		this.errorMontoMinimo = errorMontoMinimo;
	}
	/**
	 * @return the errorMontoMaximo
	 */
	public String getErrorMontoMaximo() {
		return errorMontoMaximo;
	}
	/**
	 * @param errorMontoMaximo the errorMontoMaximo to set
	 */
	public void setErrorMontoMaximo(String errorMontoMaximo) {
		this.errorMontoMaximo = errorMontoMaximo;
	}
	/**
	 * @return the montoPedidoBloq
	 */
	public String getMontoPedidoBloq() {
		return montoPedidoBloq;
	}
	/**
	 * @param montoPedidoBloq the montoPedidoBloq to set
	 */
	public void setMontoPedidoBloq(String montoPedidoBloq) {
		this.montoPedidoBloq = montoPedidoBloq;
	}
	/**
	 * @return the montoMinimo
	 */
	public String getMontoMinimo() {
		return montoMinimo;
	}
	/**
	 * @param montoMinimo the montoMinimo to set
	 */
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	/**
	 * @return the montoMaximo
	 */
	public String getMontoMaximo() {
		return montoMaximo;
	}
	/**
	 * @param montoMaximo the montoMaximo to set
	 */
	public void setMontoMaximo(String montoMaximo) {
		this.montoMaximo = montoMaximo;
	}
	/**
	 * @return the pedidosEstimados
	 */
	public String getPedidosEstimados() {
		return pedidosEstimados;
	}
	/**
	 * @param pedidosEstimados the pedidosEstimados to set
	 */
	public void setPedidosEstimados(String pedidosEstimados) {
		this.pedidosEstimados = pedidosEstimados;
	}
	
	
	
}
