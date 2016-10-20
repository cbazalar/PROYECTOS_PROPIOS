/**
 * 
 */
package biz.belcorp.ssicc.service.zon.ws.beans;

import java.io.Serializable;

/**
 * @author itocto
 *
 */
public class UbigZONWebServiceResultado implements Serializable {
	private String codigo;
	private String mensaje;	
	private UbigeosZONWebService[] ubigeos;
	
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
	 * @return the ubigeos
	 */
	public UbigeosZONWebService[] getUbigeos() {
		return ubigeos;
	}
	/**
	 * @param ubigeos the ubigeos to set
	 */
	public void setUbigeos(UbigeosZONWebService[] ubigeos) {
		this.ubigeos = ubigeos;
	}
	
}
