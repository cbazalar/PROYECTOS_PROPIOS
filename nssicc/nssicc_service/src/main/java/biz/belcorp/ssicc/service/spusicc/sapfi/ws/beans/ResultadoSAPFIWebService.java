package biz.belcorp.ssicc.service.spusicc.sapfi.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ResultadoSAFIWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class ResultadoSAPFIWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	  
    private String codResultado;
    private String mensaje;  
    private String codLider;
    
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
	 * @return the codResultado
	 */
	public String getCodResultado() {
		return codResultado;
	}
	/**
	 * @param codResultado the codResultado to set
	 */
	public void setCodResultado(String codResultado) {
		this.codResultado = codResultado;
	}
	/**
	 * @return the codLider
	 */
	public String getCodLider() {
		return codLider;
	}
	/**
	 * @param codLider the codLider to set
	 */
	public void setCodLider(String codLider) {
		this.codLider = codLider;
	}
	
	
	
   
}
