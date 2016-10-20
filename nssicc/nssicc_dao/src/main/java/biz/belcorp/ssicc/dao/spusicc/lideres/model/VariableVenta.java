package biz.belcorp.ssicc.dao.spusicc.lideres.model;

import java.io.Serializable;

public class VariableVenta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6463506798468540472L;
	private Integer codigo;
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
	public Integer getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
	
}
