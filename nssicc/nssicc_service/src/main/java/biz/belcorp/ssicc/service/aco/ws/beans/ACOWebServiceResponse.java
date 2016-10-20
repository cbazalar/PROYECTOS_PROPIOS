package biz.belcorp.ssicc.service.aco.ws.beans;

import java.io.Serializable;

public class ACOWebServiceResponse implements Serializable {

	private static final long serialVersionUID = 6222431448834869834L;
	private boolean ejecucionExitosa;
	private String mensaje;
	private ParametroACOWebService respuestaWebService[];
	
		
	/**
	 * 
	 */
	public ACOWebServiceResponse() {
		this.ejecucionExitosa = true;
		this.mensaje = "";
		this.respuestaWebService = null;
	}

	/**
	 * @param ejecucionExitosa
	 * @param mensaje
	 */
	public ACOWebServiceResponse(boolean ejecucionExitosa, String mensaje) {
		this.ejecucionExitosa = ejecucionExitosa;
		this.mensaje = mensaje;
		this.respuestaWebService = null;
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
	
	public ParametroACOWebService[] getRespuestaWebService() {
		return respuestaWebService;
	}
	
	public void setRespuestaWebService(
			ParametroACOWebService[] respuestaWebService) {
		this.respuestaWebService = respuestaWebService;
	}
	
	
}
