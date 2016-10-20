package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class ProcesoPEDPedidoInventariadoWebServiceResponse implements Serializable {

	private static final long serialVersionUID = 6222431448834869834L;
	private boolean ejecucionExitosa;
	private String mensaje;
		
	/**
	 * 
	 */
	public ProcesoPEDPedidoInventariadoWebServiceResponse() {
		this.ejecucionExitosa = true;
		this.mensaje = "";
	}

	/**
	 * @param ejecucionExitosa
	 * @param mensaje
	 */
	public ProcesoPEDPedidoInventariadoWebServiceResponse(boolean ejecucionExitosa, String mensaje) {
		this.ejecucionExitosa = ejecucionExitosa;
		this.mensaje = mensaje;
	}

	/**
	 * @return the ejecucionExitosa
	 */
	public boolean isEjecucionExitosa() {
		return ejecucionExitosa;
	}
	
	/**
	 * @param ejecucionExitosa the ejecucionExitosa to set
	 */
	public void setEjecucionExitosa(boolean ejecucionExitosa) {
		this.ejecucionExitosa = ejecucionExitosa;
	}
	
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
	
	
	
}
