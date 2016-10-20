package biz.belcorp.ssicc.service.spusicc.dir.ws.beans;

import java.io.Serializable;

public class ProcesoDIRWebServiceResultado implements Serializable {

	private static final long serialVersionUID = 2071529522285555868L;
	
	private String codigo;
	private String mensaje;
	private ClienteDIRWebService clienteDIRWebService[];
	
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
	 * @return the clienteDIRWebService
	 */
	public ClienteDIRWebService[] getClienteDIRWebService() {
		return clienteDIRWebService;
	}
	
	/**
	 * @param clienteDIRWebService the clienteDIRWebService to set
	 */
	public void setClienteDIRWebService(ClienteDIRWebService[] clienteDIRWebService) {
		this.clienteDIRWebService = clienteDIRWebService;
	}
	
	

}
