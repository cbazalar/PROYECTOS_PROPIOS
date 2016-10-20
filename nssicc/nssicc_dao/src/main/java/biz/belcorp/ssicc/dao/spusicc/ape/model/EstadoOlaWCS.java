package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Nicols Lpez
 */

public class EstadoOlaWCS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7027254860951907324L;
	private String estado;
	private String numeroOla;
	
	/**
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * @return numeroOla
	 */
	public String getNumeroOla() {
		return numeroOla;
	}
	
	/**
	 * @param numeroOla
	 */
	public void setNumeroOla(String numeroOla) {
		this.numeroOla = numeroOla;
	}
	
}