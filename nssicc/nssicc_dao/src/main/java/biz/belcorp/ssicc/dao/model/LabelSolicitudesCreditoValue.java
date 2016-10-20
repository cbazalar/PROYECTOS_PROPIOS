package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * A simple JavaBean to represent label-value pairs. This is most commonly used
 * when constructing user interface elements which have a label to be displayed
 * to the user, and a corresponding value to be returned to the server. One
 * example is the <code>&lt;html:options&gt;</code> tag. <p/><p/>Note: this
 * class has a natural ordering that is inconsistent with equals.
 * </p>
 * 
 * @see org.apache.struts.util.LabelValueBean
 */
public class LabelSolicitudesCreditoValue extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -481619051693747152L;
	private String codigoPais = null;
    private String codigoZona = null;
    private String fechaFacturacion = null;
    private String codigoPeriodo = null;
    private String numSolicitudes = null;
    private String numLote =null;
    
	public LabelSolicitudesCreditoValue(String codigoZona, String fechaFacturacion, String codigoPeriodo, String numSolicitudes) {
		super();
		// TODO Auto-generated constructor stub
		this.codigoZona = codigoZona;
		this.fechaFacturacion = fechaFacturacion;
		this.codigoPeriodo = codigoPeriodo;
		this.numSolicitudes = numSolicitudes;
	}

	public LabelSolicitudesCreditoValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the fechaFacturacion.
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion The fechaFacturacion to set.
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return Returns the numSolicitudes.
	 */
	public String getNumSolicitudes() {
		return numSolicitudes;
	}
	/**
	 * @param numSolicitudes The numSolicitudes to set.
	 */
	public void setNumSolicitudes(String numSolicitudes) {
		this.numSolicitudes = numSolicitudes;
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
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LabelSolicitudesCreditoValue)) {
			return false;
		}
		LabelSolicitudesCreditoValue rhs = (LabelSolicitudesCreditoValue) object;
		return new EqualsBuilder().append(
				this.fechaFacturacion, rhs.fechaFacturacion).append(
				this.auditInfo, rhs.auditInfo).append(this.numSolicitudes,
				rhs.numSolicitudes).append(this.codigoZona, rhs.codigoZona)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(759793761, -741738285).append(this.fechaFacturacion).append(
				this.auditInfo).append(this.numSolicitudes).append(
				this.codigoZona).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("fechaFacturacion",
				this.fechaFacturacion).append("auditInfo", this.auditInfo)
				.append("numSolicitudes", this.numSolicitudes).append(
						"codigoZona", this.codigoZona).toString();
	}
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}

	/**
	 * @param numLote The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

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

}