package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteClasificacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long oid;
	private Long oidClienteSubTipo;
	private Long oidClasificacion;
	private Long oidPeriodo;
	private Long oidTipoClasificacion;
	private Date fechaClasificacion;
	private Integer indicadorPrincipal;
	
	//Para la parte WEB
	private String descripcionTipoClasificacion;
	private String descripcionClasificacion;
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
	private boolean isEliminar;
	/**
	 * @return the isEliminar
	 */
	public boolean isEliminar() {
		return isEliminar;
	}

	/**
	 * @param isEliminar the isEliminar to set
	 */
	public void setEliminar(boolean isEliminar) {
		this.isEliminar = isEliminar;
	}

	public ClienteClasificacion() {
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public String toString() {
		return "ClienteClasificacion [oid=" + oid + ", oidClienteSubTipo="
				+ oidClienteSubTipo + ", oidClasificacion=" + oidClasificacion
				+ ", oidPeriodo=" + oidPeriodo + ", oidTipoClasificacion="
				+ oidTipoClasificacion + ", fechaClasificacion="
				+ fechaClasificacion + ", indicadorPrincipal="
				+ indicadorPrincipal + ", descripcionTipoClasificacion="
				+ descripcionTipoClasificacion + ", descripcionClasificacion="
				+ descripcionClasificacion + ", codigoUsuario=" + codigoUsuario
				+ ", isEliminar=" + isEliminar + "]";
	}

	/**
	 * @return Returns the fechaClasificacion.
	 */
	public Date getFechaClasificacion() {
		return fechaClasificacion;
	}

	/**
	 * @param fechaClasificacion The fechaClasificacion to set.
	 */
	public void setFechaClasificacion(Date fechaClasificacion) {
		this.fechaClasificacion = fechaClasificacion;
	}

	/**
	 * @return Returns the oid.
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid The oid to set.
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return Returns the oidClasificacion.
	 */
	public Long getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion The oidClasificacion to set.
	 */
	public void setOidClasificacion(Long oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return Returns the oidClienteSubTipo.
	 */
	public Long getOidClienteSubTipo() {
		return oidClienteSubTipo;
	}

	/**
	 * @param oidClienteSubTipo The oidClienteSubTipo to set.
	 */
	public void setOidClienteSubTipo(Long oidClienteSubTipo) {
		this.oidClienteSubTipo = oidClienteSubTipo;
	}

	/**
	 * @return Returns the oidTipoClasificacion.
	 */
	public Long getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion The oidTipoClasificacion to set.
	 */
	public void setOidTipoClasificacion(Long oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return Returns the descripcionClasificacion.
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}

	/**
	 * @param descripcionClasificacion The descripcionClasificacion to set.
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}

	/**
	 * @return Returns the descripcionTipoClasificacion.
	 */
	public String getDescripcionTipoClasificacion() {
		return descripcionTipoClasificacion;
	}

	/**
	 * @param descripcionTipoClasificacion The descripcionTipoClasificacion to set.
	 */
	public void setDescripcionTipoClasificacion(String descripcionTipoClasificacion) {
		this.descripcionTipoClasificacion = descripcionTipoClasificacion;
	}

	/**
	 * @return Returns the indicadorPrincipal.
	 */
	public Integer getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	/**
	 * @param indicadorPrincipal The indicadorPrincipal to set.
	 */
	public void setIndicadorPrincipal(Integer indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}

	/**
	 * @return Returns the oidPeriodo.
	 */
	public Long getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo The oidPeriodo to set.
	 */
	public void setOidPeriodo(Long oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
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
	
	
	
}
