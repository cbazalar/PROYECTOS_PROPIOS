package biz.belcorp.ssicc.service.spusicc.cuentacorriente.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoLETWebServiceResultado.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 * 
 */
public class ProcesoCCCWebServiceResultado implements Serializable {

 	private static final long serialVersionUID = 1L;
 	
	private String mensaje;
	private String codigoMensaje;
    private String saldo;
    
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
	 * @return the codigoMensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * @param codigoMensaje the codigoMensaje to set
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}
	
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
    
    
   
}
