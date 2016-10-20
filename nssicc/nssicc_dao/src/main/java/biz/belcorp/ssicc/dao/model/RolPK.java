/*
 * Created on 10/11/2005 10:24:54 AM biz.belcorp.ssicc.model.RolPK
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="RolPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class RolPK extends BaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -554568582231423009L;
	
	/**
     * @uml.property name="codigoPais" multiplicity="(0 1)"
     */
    private String codigoPais;

    /**
     * @uml.property name="codigoPais"
     * @struts.form-field
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * @uml.property name="codigoPais"
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * @uml.property name="codigo" multiplicity="(0 1)"
     */
    private String codigo;

    /**
     * @uml.property name="codigo"
     * @struts.form-field
     * @struts.validator type="required"
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @uml.property name="codigo"
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Constructor por defecto
     */
    public RolPK() {
        super();
    }
    
    
    /**
     * @param codigoPais
     * @param codigo
     */
    public RolPK(String codigoPais, String codigo) {
        super();
        this.codigoPais = codigoPais;
        this.codigo = codigo;
    }
    
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof RolPK)) {
            return false;
        }
        RolPK rhs = (RolPK) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais).append(this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(527242603, -124015047).append(this.codigoPais).append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais).append(
                "codigo", this.codigo).toString();
    }
}
