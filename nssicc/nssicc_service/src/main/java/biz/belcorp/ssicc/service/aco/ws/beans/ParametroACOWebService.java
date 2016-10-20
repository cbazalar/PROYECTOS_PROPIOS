package biz.belcorp.ssicc.service.aco.ws.beans;

import java.io.Serializable;

public class ParametroACOWebService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6466888775161640355L;
	private String nombre;
	private String valor;
	
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
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
	
	
}
