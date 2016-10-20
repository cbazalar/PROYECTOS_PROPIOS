/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.scsicc.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaPERWebServiceResultado.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ConsultaPERWebServiceResultado implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String mensaje;    
    private String codigo;
    private ControlPERAsistenciaWebService [] listControlAsistenciaWebService;
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
	 * @return the listControlAsistenciaWebService
	 */
	public ControlPERAsistenciaWebService[] getListControlAsistenciaWebService() {
		return listControlAsistenciaWebService;
	}
	/**
	 * @param listControlAsistenciaWebService the listControlAsistenciaWebService to set
	 */
	public void setListControlAsistenciaWebService(
			ControlPERAsistenciaWebService[] listControlAsistenciaWebService) {
		this.listControlAsistenciaWebService = listControlAsistenciaWebService;
	}
    
    

}
