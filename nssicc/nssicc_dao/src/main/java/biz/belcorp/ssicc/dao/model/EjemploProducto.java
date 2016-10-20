package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

public class EjemploProducto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -698437390408791042L;
	
	private String codigo;
	private String descripcion;
	private String observacion;
	private String unidadMedida;
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
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	

}
