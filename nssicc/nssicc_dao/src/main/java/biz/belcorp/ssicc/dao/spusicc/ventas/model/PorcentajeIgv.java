package biz.belcorp.ssicc.dao.spusicc.ventas.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * Bean  que guarda  informacion de la entidad VEN_PORCE_IGV
 * @author peextjnunez
 *
 */
public class PorcentajeIgv extends AuditableBaseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -544118959543701491L;
	
	private String codigoPais;/**Codigo del Pais*/
	private String codigoPeriodo;/**Periodo*/
	private Integer valIgv;/**Valor de Igv*/
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
	 * @return Returns the valIgv.
	 */
	public Integer getValIgv() {
		return valIgv;
	}
	/**
	 * @param valIgv The valIgv to set.
	 */
	public void setValIgv(Integer valIgv) {
		this.valIgv = valIgv;
	}
	
	
	public boolean equals(Object object) {
        if (!(object instanceof GrupoZona)) {
            return false;
        }
        PorcentajeIgv rhs = (PorcentajeIgv) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais)
                .append(this.codigoPeriodo, rhs.codigoPeriodo).append(
                        this.valIgv, rhs.valIgv).isEquals();
	  }
	 
	 public String toString() {
	        return new ToStringBuilder(this)
	                .append("codigoPais", this.codigoPais).append("codigoPeriodo",
	                        this.codigoPeriodo).append("valIgv", this.valIgv).toString();
	 }
	 
	 public int hashCode() {
	        return new HashCodeBuilder(-504457923, 1884526667).append(
	                this.codigoPais).append(this.codigoPeriodo).append(
	                this.valIgv).toHashCode();
	 }
	
	
	

}
