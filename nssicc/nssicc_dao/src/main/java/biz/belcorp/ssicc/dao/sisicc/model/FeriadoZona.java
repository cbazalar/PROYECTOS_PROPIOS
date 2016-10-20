package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class FeriadoZona extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1218629347647778931L;

	private String status;
	
	private String codigoZona;
	
	
	private String descripcionZona;
		
	private String codigoAnio;
	
	private Date   fechaCalendario;
	
	private String descripcion;
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1600772383, -1519281453).append(this.codigoAnio).append(this.codigoZona).append(
				this.fechaCalendario).append(this.descripcion).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoAnio", this.codigoAnio)
				.append("codigoZona", this.codigoZona)
				.append("fechaCalendario",this.fechaCalendario)
				.append("descripcion",this.descripcion).toString();
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof FeriadoZona)) {
			return false;
		}
		FeriadoZona rhs = (FeriadoZona) object;
		return new EqualsBuilder()
				.append(this.codigoAnio, rhs.codigoAnio)
				.append(this.codigoZona, rhs.codigoZona)
				.append(this.fechaCalendario, rhs.fechaCalendario).isEquals();
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

	/**
	 * @return Returns the fechaCalendario.
	 */
	public Date getFechaCalendario() {
		return fechaCalendario;
	}

	/**
	 * @param fechaCalendario The fechaCalendario to set.
	 */
	public void setFechaCalendario(Date fechaCalendario) {
		this.fechaCalendario = fechaCalendario;
	}

	
	/**
	 * @return Returns the codigoAnio.
	 */
	public String getCodigoAnio() {
		return codigoAnio;
	}
	/**
	 * @param codigoAnio The codigoAnio to set.
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}
	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	
	
	
}
