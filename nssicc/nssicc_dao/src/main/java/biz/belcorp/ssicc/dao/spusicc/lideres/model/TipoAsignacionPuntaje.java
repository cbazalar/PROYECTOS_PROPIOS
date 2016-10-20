package biz.belcorp.ssicc.dao.spusicc.lideres.model;

import java.io.Serializable;

public class TipoAsignacionPuntaje implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6166111840637678553L;
	private String codigo;
	private String descripcion;
	
	
	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
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

}
