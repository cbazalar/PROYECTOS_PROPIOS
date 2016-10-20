/*
 * Created on 10/02/2005 03:01:53 PM
 *
 * biz.belcorp.ssicc.model.ClientePK
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ClientePK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class ClientePK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7245243895257249836L;
	/**
     * 
     * @uml.property name="codigoPais" multiplicity="(0 1)"
     */
    private String codigoPais;

    /**
     * 
     * @uml.property name="codigoPais"
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * 
     * @uml.property name="codigoPais"
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * 
     * @uml.property name="codigo" multiplicity="(0 1)"
     */
    private String codigo;

    /**
     * 
     * @uml.property name="codigo"
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * 
     * @uml.property name="codigo"
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ClientePK(String codigoPais, String codigo) {
        this.codigoPais = codigoPais;
        this.codigo = codigo;
    }

    public ClientePK() {
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ClientePK)) {
            return false;
        }
        ClientePK rhs = (ClientePK) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais)
                .append(this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1675146587, -1171061823).append(
                this.codigoPais).append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoPais", this.codigoPais).append("codigo",
                        this.codigo).toString();
    }
}