package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class ProcesoPEDPedidoErroresRechazadoWebServiceResponse implements Serializable {

	private static final long serialVersionUID = 6222431448834869834L;
	
	private String codigo;
	private String descrip;
	
	public ProcesoPEDPedidoErroresRechazadoWebServiceResponse() {
		this.codigo = "";
		this.descrip = "";
	}

	public ProcesoPEDPedidoErroresRechazadoWebServiceResponse(String codigo, String descrip) {
		this.codigo = codigo;
		this.descrip = descrip;
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
	 * @return the descrip
	 */
	public String getDescrip() {
		return descrip;
	}

	/**
	 * @param descrip the descrip to set
	 */
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
}