/**
 * 
 */
package biz.belcorp.ssicc.service.zon.ws.beans;

import java.io.Serializable;

/**
 * @author itocto
 *
 */
public class TerritZONWebServiceResultado implements Serializable {
	
	private String codigo;
	private String mensaje;	
	private TerritoriosZONWebService[] territorios;
	
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
	 * @return the territorios
	 */
	public TerritoriosZONWebService[] getTerritorios() {
		return territorios;
	}
	/**
	 * @param territorios the territorios to set
	 */
	public void setTerritorios(TerritoriosZONWebService[] territorios) {
		this.territorios = territorios;
	}
	
}
