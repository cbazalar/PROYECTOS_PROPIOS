package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class DatosComisionClasificacion extends AuditableBaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String oidListaClasificacion;
	
	private String oidTipoClasificacion;
	private String codigoTipoClasificacion;
	private String descripcionTipoClasificacion;
	private String codigoClasificacion;
	private String descripcionClasificacion;
	
	private String codigoUsuario;
	private String codigoComision;
	private String estado;
	
	/**
	 * @return the oidListaClasificacion
	 */
	public String getOidListaClasificacion() {
		return oidListaClasificacion;
	}

	/**
	 * @param oidListaClasificacion the oidListaClasificacion to set
	 */
	public void setOidListaClasificacion(String oidListaClasificacion) {
		this.oidListaClasificacion = oidListaClasificacion;
	}

	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return the codigoTipoClasificacion
	 */
	public String getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}

	/**
	 * @param codigoTipoClasificacion the codigoTipoClasificacion to set
	 */
	public void setCodigoTipoClasificacion(String codigoTipoClasificacion) {
		this.codigoTipoClasificacion = codigoTipoClasificacion;
	}

	/**
	 * @return the descripcionTipoClasificacion
	 */
	public String getDescripcionTipoClasificacion() {
		return descripcionTipoClasificacion;
	}

	/**
	 * @param descripcionTipoClasificacion the descripcionTipoClasificacion to set
	 */
	public void setDescripcionTipoClasificacion(String descripcionTipoClasificacion) {
		this.descripcionTipoClasificacion = descripcionTipoClasificacion;
	}

	/**
	 * @return the codigoClasificacion
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}

	/**
	 * @param codigoClasificacion the codigoClasificacion to set
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

	/**
	 * @return the descripcionClasificacion
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}

	/**
	 * @param descripcionClasificacion the descripcionClasificacion to set
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}

	/**
	 * @return the codigoComision
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision the codigoComision to set
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
