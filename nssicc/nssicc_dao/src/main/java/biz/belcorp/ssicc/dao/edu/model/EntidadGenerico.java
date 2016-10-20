package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class EntidadGenerico extends AuditableBaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombreEntidad;//nombre dela tabla
	private String descripcionEntidad;//descripcion dela tabla
	private String codigo;//codigo a insertar PK
	private String codigoTipo;//codigo opcional foranho FK
	private String descripcion;//dscripcion a insertar o actualizar
	private String indicadorEstado;
	private String tipoCalificacion;
	private String campoCodigo;
	private String campoDescripcion;
	private String campoCodigoTipo;
	
	/**
	 * @return Returns the indicadorEstado.
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}
	/**
	 * @param indicadorEstado The indicadorEstado to set.
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
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
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	public String getCodigoTipo() {
		return codigoTipo;
	}
	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}
	public String toString() {
		 return new ToStringBuilder(this)
		 			.append("nombreEntidad", this.nombreEntidad)
		 			.append("codigo", this.codigo).append("descripcion", this.descripcion)
	                .append("codigoTipo", this.codigoTipo).toString();
	}
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getDescripcionEntidad() {
		return descripcionEntidad;
	}
	public void setDescripcionEntidad(String descripcionEntidad) {
		this.descripcionEntidad = descripcionEntidad;
	}
	/**
	 * @return Returns the tipoCalificacion.
	 */
	public String getTipoCalificacion() {
		return tipoCalificacion;
	}
	/**
	 * @param tipoCalificacion The tipoCalificacion to set.
	 */
	public void setTipoCalificacion(String tipoCalificacion) {
		this.tipoCalificacion = tipoCalificacion;
	}
	public String getCampoCodigo() {
		return campoCodigo;
	}
	public void setCampoCodigo(String campoCodigo) {
		this.campoCodigo = campoCodigo;
	}
	public String getCampoDescripcion() {
		return campoDescripcion;
	}
	public void setCampoDescripcion(String campoDescripcion) {
		this.campoDescripcion = campoDescripcion;
	}
	public String getCampoCodigoTipo() {
		return campoCodigoTipo;
	}
	public void setCampoCodigoTipo(String campoCodigoTipo) {
		this.campoCodigoTipo = campoCodigoTipo;
	}
	
}
