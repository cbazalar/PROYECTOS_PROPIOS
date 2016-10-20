/*
 * Created on 10/11/2005 09:46:47 AM biz.belcorp.ssicc.model.HistoricoProceso
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="HistoricoProceso.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class HistoricoProceso extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6628699070576479635L;
	private String numero;
	private String status;
	private Date fechaInicio;
	private Date fechaFin;
	
	/**
	 * @return Returns the fechaFin.
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin The fechaFin to set.
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return Returns the fechaInicio.
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio The fechaInicio to set.
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return Returns the numero.
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero The numero to set.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof HistoricoProceso)) {
			return false;
		}
		HistoricoProceso rhs = (HistoricoProceso) object;
		return new EqualsBuilder().append(this.fechaFin, rhs.fechaFin).append(
				this.numero, rhs.numero).append(this.auditInfo, rhs.auditInfo)
				.append(this.status, rhs.status).append(this.fechaInicio,
						rhs.fechaInicio).isEquals();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("numero", this.numero).append("status", this.status)
				.append("auditInfo", this.auditInfo).append("fechaFin",
						this.fechaFin).append("fechaInicio", this.fechaInicio)
				.toString();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1199919153, 672196675).append(this.fechaFin)
				.append(this.numero).append(this.auditInfo).append(this.status)
				.append(this.fechaInicio).toHashCode();
	}

	
    }
