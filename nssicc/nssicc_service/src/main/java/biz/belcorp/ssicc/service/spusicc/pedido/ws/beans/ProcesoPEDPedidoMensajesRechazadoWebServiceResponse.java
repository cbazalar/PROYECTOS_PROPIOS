package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class ProcesoPEDPedidoMensajesRechazadoWebServiceResponse implements Serializable {

	private static final long serialVersionUID = 6222431448834869834L;
	
	private String codigo;
	private String texto;
	
	public ProcesoPEDPedidoMensajesRechazadoWebServiceResponse() {
		this.codigo = "";
		this.texto = "";
	}
	
	public ProcesoPEDPedidoMensajesRechazadoWebServiceResponse(String codigo, String texto) {
		this.codigo = codigo;
		this.texto = texto;
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
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
}