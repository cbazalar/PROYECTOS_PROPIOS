/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

/**
 * @author peextcroman
 *
 */
public class RecepcionPedido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5670480139904424545L;
	private String codigoCliente;
	private String numeroLote;
	private String nombreCliente;
	private String descripcionRegion;
	private String descripcionZona;
	private String codigoTerritorio;
	private String fechaSolicitud;
	private String facturado;
	private String fechaFacturacion;
	private String rechazoOCR;
	private String bloqueoControl;
	private String bloqueoDeuda;
	private String deuda;
	private String levantamiento;
	private String bloqueoMontoMaximo;
	private String bloqueoMontoMinimo;
	private String montoPedido;
	private String recepcionDirecta;
	private String recepcionWeb;
	private String recepcionOCR;
	private String codigoPeriodo;
	private String tipoConsulta;
	private String fechaRecepcion;
	private String descripcionSeccion;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	public String getDescripcionZona() {
		return descripcionZona;
	}
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getFacturado() {
		return facturado;
	}
	public void setFacturado(String facturado) {
		this.facturado = facturado;
	}
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	public String getRechazoOCR() {
		return rechazoOCR;
	}
	public void setRechazoOCR(String rechazoOCR) {
		this.rechazoOCR = rechazoOCR;
	}
	public String getBloqueoControl() {
		return bloqueoControl;
	}
	public void setBloqueoControl(String bloqueoControl) {
		this.bloqueoControl = bloqueoControl;
	}
	public String getBloqueoDeuda() {
		return bloqueoDeuda;
	}
	public void setBloqueoDeuda(String bloqueoDeuda) {
		this.bloqueoDeuda = bloqueoDeuda;
	}
	public String getDeuda() {
		return deuda;
	}
	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}
	public String getLevantamiento() {
		return levantamiento;
	}
	public void setLevantamiento(String levantamiento) {
		this.levantamiento = levantamiento;
	}
	public String getBloqueoMontoMaximo() {
		return bloqueoMontoMaximo;
	}
	public void setBloqueoMontoMaximo(String bloqueoMontoMaximo) {
		this.bloqueoMontoMaximo = bloqueoMontoMaximo;
	}
	public String getBloqueoMontoMinimo() {
		return bloqueoMontoMinimo;
	}
	public void setBloqueoMontoMinimo(String bloqueoMontoMinimo) {
		this.bloqueoMontoMinimo = bloqueoMontoMinimo;
	}
	public String getMontoPedido() {
		return montoPedido;
	}
	public void setMontoPedido(String montoPedido) {
		this.montoPedido = montoPedido;
	}
	public String getRecepcionDirecta() {
		return recepcionDirecta;
	}
	public void setRecepcionDirecta(String recepcionDirecta) {
		this.recepcionDirecta = recepcionDirecta;
	}
	public String getRecepcionWeb() {
		return recepcionWeb;
	}
	public void setRecepcionWeb(String recepcionWeb) {
		this.recepcionWeb = recepcionWeb;
	}
	public String getRecepcionOCR() {
		return recepcionOCR;
	}
	public void setRecepcionOCR(String recepcionOCR) {
		this.recepcionOCR = recepcionOCR;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	public String getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(String fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}
	public void setDescripcionSeccion(String descripcionSeccion) {
		this.descripcionSeccion = descripcionSeccion;
	}
}
