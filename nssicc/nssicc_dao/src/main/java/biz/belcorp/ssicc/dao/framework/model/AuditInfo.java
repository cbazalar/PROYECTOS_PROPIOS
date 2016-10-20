/*
 * Created on 08/02/2005 06:00:52 PM
 *
 * biz.belcorp.ssicc.model.AuditInfo
 */
package biz.belcorp.ssicc.dao.framework.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class is used to represent an AuditInfo.
 * <p>
 * <a href="AuditInfo.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class AuditInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1818752690886048415L;
	
	private String createdBy;

	/**
	 * @return
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	private Timestamp created;

	/**
	 * @return
	 */
	public Timestamp getCreated() {
		return created;
	}

	/**
	 * @param created
	 */
	public void setCreated(Timestamp created) {
		this.created = created;
	}

	private String updatedBy;

	/**
	 * @return
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	private Timestamp lastUpdated;

	/**
	 * @return
	 */
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated
	 */
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	private String ipMaquinaRemota;
	
	/**
	 * @return
	 */
	public String getIpMaquinaRemota() {
		return ipMaquinaRemota;
	}

	/**
	 * @param ipMaquinaRemota
	 */
	public void setIpMaquinaRemota(String ipMaquinaRemota) {
		this.ipMaquinaRemota = ipMaquinaRemota;
	}

		
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AuditInfo)) {
			return false;
		}
		AuditInfo rhs = (AuditInfo) object;
		return new EqualsBuilder().append(this.created, rhs.created).append(
				this.updatedBy, rhs.updatedBy).append(this.lastUpdated,
				rhs.lastUpdated).append(this.createdBy, rhs.createdBy)
				.append(this.ipMaquinaRemota, rhs.ipMaquinaRemota)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1995598109, -315491091).append(this.created)
				.append(this.updatedBy)
				.append(this.lastUpdated)
				.append(this.createdBy)
				.append(this.ipMaquinaRemota).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("created", this.created)
				.append("updatedBy", this.updatedBy).append("createdBy",
						this.createdBy).append("lastUpdated", this.lastUpdated)
				.toString();
	}
	
}
