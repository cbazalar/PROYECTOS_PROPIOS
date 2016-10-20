package biz.belcorp.ssicc.dao.spusicc.ventas.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * Bean que guarda  informacion  de la entidad VEN_GRUPO_ZONA
 * @author peextjnunez  
 *
 */
public class GrupoZona extends AuditableBaseObject implements Serializable{

	private static final long serialVersionUID = -2424357932421360504L;

	private String codigo; /**Codigo del Grupo Zonal*/
	private String descripcion; /**Descripcion del Grupo Zonal*/
	private String codigoAgrupado; /*Agrupado de Grupo de Zona */
	private String descripcionAgrupado;
	
	//Informacion referente  a la Zona del Grupo
	private Integer oidZona;/** Numero secuencial de la zona*/
	private String codigoZona; /** Codigo de Zona*/
	private String descripcionZona;/** Descripcion de la zona*/
	
	
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the descripciom.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripciom The descripciom to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	        GrupoZona rhs = (GrupoZona) object;
	        return new EqualsBuilder().append(this.codigo, rhs.codigo)
	                .append(this.descripcion, rhs.descripcion).append(
	                        this.oidZona, rhs.oidZona).append(this.codigoZona, rhs.codigoZona).append(
	                        this.descripcionZona, rhs.descripcionZona).isEquals();
	  }
	 
	 public String toString() {
	        return new ToStringBuilder(this)
	                .append("codigo", this.codigo).append("descripcion",
	                        this.descripcion).append("oidZona", this.oidZona).append(
	                        "codigoZona", this.codigoZona).append(
	                        "descripcionZona", this.descripcionZona).toString();
	 }
	 
	 public int hashCode() {
	        return new HashCodeBuilder(-504457923, 1884526667).append(
	                this.codigo).append(this.descripcion).append(
	                this.oidZona).append(this.descripcionZona).append(this.codigoZona).toHashCode();
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
	/**
	 * @return Returns the oidZona.
	 */
	public Integer getOidZona() {
		return oidZona;
	}
	/**
	 * @param oidZona The oidZona to set.
	 */
	public void setOidZona(Integer oidZona) {
		this.oidZona = oidZona;
	}
	/**
	 * @return Returns the codigoAgrupado.
	 */
	public String getCodigoAgrupado() {
		return codigoAgrupado;
	}
	/**
	 * @param codigoAgrupado The codigoAgrupado to set.
	 */
	public void setCodigoAgrupado(String codigoAgrupado) {
		this.codigoAgrupado = codigoAgrupado;
	}
	/**
	 * @return Returns the descripcionAgrupado.
	 */
	public String getDescripcionAgrupado() {
		return descripcionAgrupado;
	}
	/**
	 * @param descripcionAgrupado The descripcionAgrupado to set.
	 */
	public void setDescripcionAgrupado(String descripcionAgrupado) {
		this.descripcionAgrupado = descripcionAgrupado;
	}
	
	

}
