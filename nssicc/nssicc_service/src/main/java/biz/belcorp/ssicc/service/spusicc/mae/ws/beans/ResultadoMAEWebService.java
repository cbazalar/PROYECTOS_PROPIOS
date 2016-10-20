package biz.belcorp.ssicc.service.spusicc.mae.ws.beans;

import java.io.Serializable;
import java.util.List;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ResultadoMAEWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public class ResultadoMAEWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mensaje;    
    private String codResultado;
    private String codCliente;
    private List consultoras;
    
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
	 * @return the codCliente
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * @param codCliente the codCliente to set
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	/**
	 * @return the consultoras
	 */
	public List getConsultoras() {
		return consultoras;
	}
	
	/**
	 * @param consultoras the consultoras to set
	 */
	public void setConsultoras(List consultoras) {
		this.consultoras = consultoras;
	}
	
	
   
}
