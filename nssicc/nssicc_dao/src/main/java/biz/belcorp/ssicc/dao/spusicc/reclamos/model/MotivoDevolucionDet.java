package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

public class MotivoDevolucionDet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idMotivoDevolucion;
	private String codigo;
	private String descripcion;
	private String tipoDevolucion;
	private String indicadorDevolucion;
	
	
	/**
	 * @return the idMotivoDevolucion
	 */
	public String getIdMotivoDevolucion() {
		return idMotivoDevolucion;
	}
	/**
	 * @param idMotivoDevolucion the idMotivoDevolucion to set
	 */
	public void setIdMotivoDevolucion(String idMotivoDevolucion) {
		this.idMotivoDevolucion = idMotivoDevolucion;
	}
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the tipoDevolucion
	 */
	public String getTipoDevolucion() {
		return tipoDevolucion;
	}
	/**
	 * @param tipoDevolucion the tipoDevolucion to set
	 */
	public void setTipoDevolucion(String tipoDevolucion) {
		this.tipoDevolucion = tipoDevolucion;
	}

	/**
	 * @return the indicadorDevolucion
	 */
	public String getIndicadorDevolucion() {
		return indicadorDevolucion;
	}
	/**
	 * @param indicadorDevolucion the indicadorDevolucion to set
	 */
	public void setIndicadorDevolucion(String indicadorDevolucion) {
		this.indicadorDevolucion = indicadorDevolucion;
	}

}
