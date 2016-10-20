package biz.belcorp.ssicc.dao.spusicc.proyeccion.model;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;
/**
 * Bean que guarda  informacion  de la entidad  BAS_PARAM_PORCE_FALTA
 * @author peextcbazalar  
 *
 */
public class PorcentajeFaltante extends AuditableBaseObject implements Serializable{
	
	private String codigoPais;
	private String codigoUnidadNegocio; 
	private String descripcionUnidadNegocio;
	private BigDecimal porcentajeMaximo; 
	
	
   /**
	* @return Returns the codigoUnidadNegocio.
    */
	public String getCodigoUnidadNegocio() {
		return codigoUnidadNegocio;
	}

	/**
	 * @param codigoUnidadNegocio The codigoUnidadNegocio to set.
	 */
	public void setCodigoUnidadNegocio(String codigoUnidadNegocio) {
		this.codigoUnidadNegocio = codigoUnidadNegocio;
	}

	/**
	 * @return Returns the descripcionUnidadNegocio.
	 */
	public String getDescripcionUnidadNegocio() {
		return descripcionUnidadNegocio;
	}

	/**
	 * @param descripcionUnidadNegocio The descripcionUnidadNegocio to set.
	 */
	public void setDescripcionUnidadNegocio(String descripcionUnidadNegocio) {
		this.descripcionUnidadNegocio = descripcionUnidadNegocio;
	}

	/**
	 * @return Returns the porcentajeMaximo.
	 */
	public BigDecimal getPorcentajeMaximo() {
		return porcentajeMaximo;
	}

	/**
	 * @param porcentajeMaximo The porcentajeMaximo to set.
	 */
	public void setPorcentajeMaximo(BigDecimal porcentajeMaximo) {
		this.porcentajeMaximo = porcentajeMaximo;
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

	/*
	  * 
	  *  (non-Javadoc)
	  * @see java.lang.Object#equals(java.lang.Object)
	  */
	 public boolean equals(Object object) {
	        if (!(object instanceof GrupoZona)) {
	            return false;
	        }
	        PorcentajeFaltante rhs = (PorcentajeFaltante) object;
	        return new EqualsBuilder().append(this.codigoUnidadNegocio, rhs.codigoUnidadNegocio)
	                .append(this.descripcionUnidadNegocio, rhs.descripcionUnidadNegocio).isEquals();
	  }
	 
	 public String toString() {
	        return new ToStringBuilder(this)
	                .append("codigoUnidadNegocio", this.codigoUnidadNegocio).append("descripcionUnidadNegocio",
	                        this.descripcionUnidadNegocio).toString();
	 }
	 
	 public int hashCode() {
	        return new HashCodeBuilder(-504457923, 1884526667).append(
	                this.codigoUnidadNegocio).append(this.descripcionUnidadNegocio).toHashCode();
	 }
	
}
