package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;



public class Calendario extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4041567150861354862L;

	private String status;
	
	private String codigoAnio;
	
	private Date fechaCalendario;
	
	private Integer posicionSemana;
	
	private String indicadorFeriado;
	
	private String descIndicadorFeriado;
	
	private String descripcion;
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1600772383, -1519281453).append(this.codigoAnio).append(
				this.fechaCalendario).append(this.descripcion).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoAnio", this.codigoAnio)
				.append("fechaCalendario",this.fechaCalendario)
				.append("descripcion",this.descripcion).toString();
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Calendario)) {
			return false;
		}
		Calendario rhs = (Calendario) object;
		return new EqualsBuilder()
				.append(this.codigoAnio, rhs.codigoAnio).append(
						this.fechaCalendario, rhs.fechaCalendario).isEquals();
	}

	/**
	 * @return Returns the descIndicadorFeriado.
	 */
	public String getDescIndicadorFeriado() {
		return descIndicadorFeriado;
	}

	/**
	 * @param descIndicadorFeriado The descIndicadorFeriado to set.
	 */
	public void setDescIndicadorFeriado(String descIndicadorFeriado) {
		this.descIndicadorFeriado = descIndicadorFeriado;
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
	 * @return Returns the indicadorFeriado.
	 */
	public String getIndicadorFeriado() {
		return indicadorFeriado;
	}

	/**
	 * @param indicadorFeriado The indicadorFeriado to set.
	 */
	public void setIndicadorFeriado(String indicadorFeriado) {
		this.indicadorFeriado = indicadorFeriado;
	}

	/**
	 * @return Returns the posicionSemana.
	 */
	public Integer getPosicionSemana() {
		return posicionSemana;
	}

	/**
	 * @param posicionSemana The posicionSemana to set.
	 */
	public void setPosicionSemana(Integer posicionSemana) {
		this.posicionSemana = posicionSemana;
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
	
	
	
}
