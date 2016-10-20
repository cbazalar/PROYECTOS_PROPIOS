/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoRETWebServiceResultado.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ProcesoRETWebServiceResultado implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String mensaje;    
    private String codigo;
    private LocalCabecRETWebService[] localCabecRETWebServices = new LocalCabecRETWebService[0];
    private LocalDetalRETWebService[] localDetalRETWebServices = new LocalDetalRETWebService[0];
   
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the mensaje.
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje The mensaje to set.
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the localCabecRETWebServices
	 */
	public LocalCabecRETWebService[] getLocalCabecRETWebServices() {
		return localCabecRETWebServices;
	}

	/**
	 * @param localCabecRETWebServices the localCabecRETWebServices to set
	 */
	public void setLocalCabecRETWebServices(LocalCabecRETWebService[] localCabecRETWebServices) {
		this.localCabecRETWebServices = localCabecRETWebServices;
	}

	/**
	 * @return the localDetalRETWebServices
	 */
	public LocalDetalRETWebService[] getLocalDetalRETWebServices() {
		return localDetalRETWebServices;
	}

	/**
	 * @param localDetalRETWebServices the localDetalRETWebServices to set
	 */
	public void setLocalDetalRETWebServices(LocalDetalRETWebService[] localDetalRETWebServices) {
		this.localDetalRETWebServices = localDetalRETWebServices;
	}

	


}
