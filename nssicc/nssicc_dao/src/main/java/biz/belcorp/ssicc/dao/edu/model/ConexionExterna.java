package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ConexionExterna extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8698842609414701701L;

	private String codigoPais;

	private String tipoBaseDatosExterna;
	
	

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the tipoBaseDatosExterna.
	 */
	public String getTipoBaseDatosExterna() {
		return tipoBaseDatosExterna;
	}

	/**
	 * @param tipoBaseDatosExterna The tipoBaseDatosExterna to set.
	 */
	public void setTipoBaseDatosExterna(String tipoBaseDatosExterna) {
		this.tipoBaseDatosExterna = tipoBaseDatosExterna;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("codigoPais", this.codigoPais)
				.append("auditInfo", this.auditInfo).append("tipoBaseDatosExterna",
						this.tipoBaseDatosExterna).toString();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ControlFacturacion)) {
			return false;
		}
		ConexionExterna rhs = (ConexionExterna) object;
		return new EqualsBuilder().append(
				this.codigoPais, rhs.codigoPais).append(this.tipoBaseDatosExterna, rhs.tipoBaseDatosExterna).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-391484411, 1136916517).append(this.codigoPais).append(this.tipoBaseDatosExterna)
				.toHashCode();
	}
	
	

}
