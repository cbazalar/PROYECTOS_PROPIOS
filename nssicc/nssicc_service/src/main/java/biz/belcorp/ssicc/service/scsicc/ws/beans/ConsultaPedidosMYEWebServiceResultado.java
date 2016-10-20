/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.scsicc.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaPedidosMYEWebServiceResultado.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ConsultaPedidosMYEWebServiceResultado implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String mensaje;    
    private String codigo;
    private RetencionPedidosMYEWebService [] listRetencionPedidosMYEWebService;
    private PedidosDigitadosMYEWebService [] listPedidosDigitadosMYEWebService;
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the listRetencionPedidosMYEWebService
	 */
	public RetencionPedidosMYEWebService[] getListRetencionPedidosMYEWebService() {
		return listRetencionPedidosMYEWebService;
	}
	/**
	 * @param listRetencionPedidosMYEWebService the listRetencionPedidosMYEWebService to set
	 */
	public void setListRetencionPedidosMYEWebService(
			RetencionPedidosMYEWebService[] listRetencionPedidosMYEWebService) {
		this.listRetencionPedidosMYEWebService = listRetencionPedidosMYEWebService;
	}
	/**
	 * @return the listPedidosDigitadosMYEWebService
	 */
	public PedidosDigitadosMYEWebService[] getListPedidosDigitadosMYEWebService() {
		return listPedidosDigitadosMYEWebService;
	}
	/**
	 * @param listPedidosDigitadosMYEWebService the listPedidosDigitadosMYEWebService to set
	 */
	public void setListPedidosDigitadosMYEWebService(
			PedidosDigitadosMYEWebService[] listPedidosDigitadosMYEWebService) {
		this.listPedidosDigitadosMYEWebService = listPedidosDigitadosMYEWebService;
	}
    
    
}
