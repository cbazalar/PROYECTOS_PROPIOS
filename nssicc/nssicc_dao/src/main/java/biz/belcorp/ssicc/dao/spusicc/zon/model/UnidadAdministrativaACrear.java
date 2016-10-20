package biz.belcorp.ssicc.dao.spusicc.zon.model;

import java.io.Serializable;

public class UnidadAdministrativaACrear implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String clave;
	private String codigo;
	private String descripcion;
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
