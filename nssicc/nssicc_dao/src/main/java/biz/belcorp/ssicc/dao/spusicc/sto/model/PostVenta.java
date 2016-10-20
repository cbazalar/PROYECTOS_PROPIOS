package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/* @author <a href="mailto:cluque@csigcomt.com">Christian Luque</a>
 * 
 */

public class PostVenta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String consultora;
	private String nombre;
	private String zona;
	
	/**
	 * @return the consultora
	 */
	public String getConsultora() {
		return consultora;
	}
	/**
	 * @param consultora the consultora to set
	 */
	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}			
}
