/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.scsicc.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaOCRWebServiceResultado.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ConsultaOCRWebServiceResultado implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String mensaje;    
    private String codigo;
    private InformeOCRPedidoWebService [] listInformeOCRWebService;
    private ConsultorasActivasSinPedidoWebService []  listConsultorasActivasSinPedidoWebService;
    private String fechaUltimaActualizacionZona;
    private String numeroRegistros;
    //mod
    private DetallePedidoFacturadoWebService [] listDetallePedidoFacturado;
    private FaltantesAnunciadosWebService [] listFaltantesAnunciado;
    private VentasRechazadasWebService [] listVentaRechazadas;
    //
    private DetallePedidoNoFacturadoWebService [] listDetallePedidoNoFacturado;
    
	/**
	 * @return the numeroRegistros
	 */
	public String getNumeroRegistros() {
		return numeroRegistros;
	}

	/**
	 * @param numeroRegistros the numeroRegistros to set
	 */
	public void setNumeroRegistros(String numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}

	public ConsultorasActivasSinPedidoWebService[] getListConsultorasActivasSinPedidoWebService() {
		return listConsultorasActivasSinPedidoWebService;
	}

	public void setListConsultorasActivasSinPedidoWebService(
			ConsultorasActivasSinPedidoWebService[] listConsultorasActivasSinPedidoWebService) {
		this.listConsultorasActivasSinPedidoWebService = listConsultorasActivasSinPedidoWebService;
	}

	public String getFechaUltimaActualizacionZona() {
		return fechaUltimaActualizacionZona;
	}

	public void setFechaUltimaActualizacionZona(String fechaUltimaActualizacionZona) {
		this.fechaUltimaActualizacionZona = fechaUltimaActualizacionZona;
	}

	/**
	 * @return the listInformeOCRWebService
	 */
	public InformeOCRPedidoWebService[] getListInformeOCRWebService() {
		return listInformeOCRWebService;
	}

	/**
	 * @param listInformeOCRWebService the listInformeOCRWebService to set
	 */
	public void setListInformeOCRWebService(
			InformeOCRPedidoWebService[] listInformeOCRWebService) {
		this.listInformeOCRWebService = listInformeOCRWebService;
	}

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the mensaje.
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje The mensaje to set.
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the listDetallePedidoFacturado
	 */
	public DetallePedidoFacturadoWebService[] getListDetallePedidoFacturado() {
		return listDetallePedidoFacturado;
	}

	/**
	 * @param listDetallePedidoFacturado the listDetallePedidoFacturado to set
	 */
	public void setListDetallePedidoFacturado(
			DetallePedidoFacturadoWebService[] listDetallePedidoFacturado) {
		this.listDetallePedidoFacturado = listDetallePedidoFacturado;
	}

	/**
	 * @return the listFaltantesAnunciado
	 */
	public FaltantesAnunciadosWebService[] getListFaltantesAnunciado() {
		return listFaltantesAnunciado;
	}

	/**
	 * @param listFaltantesAnunciado the listFaltantesAnunciado to set
	 */
	public void setListFaltantesAnunciado(
			FaltantesAnunciadosWebService[] listFaltantesAnunciado) {
		this.listFaltantesAnunciado = listFaltantesAnunciado;
	}

	/**
	 * @return the listVentaRechazadas
	 */
	public VentasRechazadasWebService[] getListVentaRechazadas() {
		return listVentaRechazadas;
	}

	/**
	 * @param listVentaRechazadas the listVentaRechazadas to set
	 */
	public void setListVentaRechazadas(
			VentasRechazadasWebService[] listVentaRechazadas) {
		this.listVentaRechazadas = listVentaRechazadas;
	}

	/**
	 * @return the listDetallePedidoNoFacturado
	 */
	public DetallePedidoNoFacturadoWebService[] getListDetallePedidoNoFacturado() {
		return listDetallePedidoNoFacturado;
	}

	/**
	 * @param listDetallePedidoNoFacturado the listDetallePedidoNoFacturado to set
	 */
	public void setListDetallePedidoNoFacturado(
			DetallePedidoNoFacturadoWebService[] listDetallePedidoNoFacturado) {
		this.listDetallePedidoNoFacturado = listDetallePedidoNoFacturado;
	}


}
