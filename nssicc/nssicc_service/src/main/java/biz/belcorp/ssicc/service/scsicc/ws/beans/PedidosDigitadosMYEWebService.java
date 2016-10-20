/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.scsicc.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PedidosDigitadosMYEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class PedidosDigitadosMYEWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoConsultora;
	private String codigoRegion;
	private String codigoZona;
	private String montoAhorroEsika;
	private String montoHastaAgotar;
	private String montoCatalogos;
	private String montoTotal;
	private String estatusAnteriorCliente;
	private String segundoPedido;
	private String tercerPedido;
	private String cuartoPedido;
	private String posibleEgreso;
	private String importeSaldoActual;
	private String importeSaldoCom;
	private String medioIngreso;
	private String fechaProceso;
	private String fechaFacturacion;
	
	
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
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
	 * @return the montoAhorroEsika
	 */
	public String getMontoAhorroEsika() {
		return montoAhorroEsika;
	}
	/**
	 * @param montoAhorroEsika the montoAhorroEsika to set
	 */
	public void setMontoAhorroEsika(String montoAhorroEsika) {
		this.montoAhorroEsika = montoAhorroEsika;
	}
	/**
	 * @return the montoHastaAgotar
	 */
	public String getMontoHastaAgotar() {
		return montoHastaAgotar;
	}
	/**
	 * @param montoHastaAgotar the montoHastaAgotar to set
	 */
	public void setMontoHastaAgotar(String montoHastaAgotar) {
		this.montoHastaAgotar = montoHastaAgotar;
	}
	/**
	 * @return the montoCatalogos
	 */
	public String getMontoCatalogos() {
		return montoCatalogos;
	}
	/**
	 * @param montoCatalogos the montoCatalogos to set
	 */
	public void setMontoCatalogos(String montoCatalogos) {
		this.montoCatalogos = montoCatalogos;
	}
	/**
	 * @return the montoTotal
	 */
	public String getMontoTotal() {
		return montoTotal;
	}
	/**
	 * @param montoTotal the montoTotal to set
	 */
	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}
	/**
	 * @return the estatusAnteriorCliente
	 */
	public String getEstatusAnteriorCliente() {
		return estatusAnteriorCliente;
	}
	/**
	 * @param estatusAnteriorCliente the estatusAnteriorCliente to set
	 */
	public void setEstatusAnteriorCliente(String estatusAnteriorCliente) {
		this.estatusAnteriorCliente = estatusAnteriorCliente;
	}
	/**
	 * @return the segundoPedido
	 */
	public String getSegundoPedido() {
		return segundoPedido;
	}
	/**
	 * @param segundoPedido the segundoPedido to set
	 */
	public void setSegundoPedido(String segundoPedido) {
		this.segundoPedido = segundoPedido;
	}
	/**
	 * @return the tercerPedido
	 */
	public String getTercerPedido() {
		return tercerPedido;
	}
	/**
	 * @param tercerPedido the tercerPedido to set
	 */
	public void setTercerPedido(String tercerPedido) {
		this.tercerPedido = tercerPedido;
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
	 * @return the cuartoPedido
	 */
	public String getCuartoPedido() {
		return cuartoPedido;
	}
	/**
	 * @param cuartoPedido the cuartoPedido to set
	 */
	public void setCuartoPedido(String cuartoPedido) {
		this.cuartoPedido = cuartoPedido;
	}
	/**
	 * @return the posibleEgreso
	 */
	public String getPosibleEgreso() {
		return posibleEgreso;
	}
	/**
	 * @param posibleEgreso the posibleEgreso to set
	 */
	public void setPosibleEgreso(String posibleEgreso) {
		this.posibleEgreso = posibleEgreso;
	}
	/**
	 * @return the importeSaldoActual
	 */
	public String getImporteSaldoActual() {
		return importeSaldoActual;
	}
	/**
	 * @param importeSaldoActual the importeSaldoActual to set
	 */
	public void setImporteSaldoActual(String importeSaldoActual) {
		this.importeSaldoActual = importeSaldoActual;
	}
	/**
	 * @return the importeSaldoCom
	 */
	public String getImporteSaldoCom() {
		return importeSaldoCom;
	}
	/**
	 * @param importeSaldoCom the importeSaldoCom to set
	 */
	public void setImporteSaldoCom(String importeSaldoCom) {
		this.importeSaldoCom = importeSaldoCom;
	}
	/**
	 * @return the medioIngreso
	 */
	public String getMedioIngreso() {
		return medioIngreso;
	}
	/**
	 * @param medioIngreso the medioIngreso to set
	 */
	public void setMedioIngreso(String medioIngreso) {
		this.medioIngreso = medioIngreso;
	}
	/**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
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

	
}
