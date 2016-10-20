package biz.belcorp.ssicc.service.spusicc.let.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoLETWebServiceResultado.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 * 
 */
public class ProcesoLETWebServiceResultado implements Serializable {

 	private static final long serialVersionUID = 1L;
 	
	private String mensaje;
    private String codigo;
   
    private LiderLETWebService consultoraLider;
    private biz.belcorp.ssicc.service.spusicc.let.ws.beans.let.LiderLETWebService[] listaConsultoraLider;
    
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return the consultoraLider
	 */
	public LiderLETWebService getConsultoraLider() {
		return consultoraLider;
	}

	/**
	 * @return the listaConsultoraLider
	 */
	public biz.belcorp.ssicc.service.spusicc.let.ws.beans.let.LiderLETWebService[] getListaConsultoraLider() {
		return listaConsultoraLider;
	}

	/**
	 * @return Returns the mensaje.
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @param consultoraLider the consultoraLider to set
	 */
	public void setConsultoraLider(LiderLETWebService consultoraLider) {
		this.consultoraLider = consultoraLider;
	}

	/**
	 * @param listaConsultoraLider the listaConsultoraLider to set
	 */
	public void setListaConsultoraLider(biz.belcorp.ssicc.service.spusicc.let.ws.beans.let.LiderLETWebService[] listaConsultoraLider) {
		this.listaConsultoraLider = listaConsultoraLider;
	}

	/**
	 * @param mensaje The mensaje to set.
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
