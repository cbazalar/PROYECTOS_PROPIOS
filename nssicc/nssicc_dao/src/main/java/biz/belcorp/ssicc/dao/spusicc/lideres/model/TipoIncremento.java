package biz.belcorp.ssicc.dao.spusicc.lideres.model;

import java.io.Serializable;

public class TipoIncremento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1059178443452249502L;
	private String codigo;
	private String descripcion;
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

}
