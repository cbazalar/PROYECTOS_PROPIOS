/*
 * Created on 29/04/2005 03:58:47 PM
 * biz.belcorp.ssicc.model.DescripcionObjetoPK
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
 * <a href="DescripcionObjetoPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class CuentaClientePK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3840408080077711694L;
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
     * @uml.property name="codigoLenguaje" multiplicity="(0 1)"
     */
    private String codigoLenguaje;

    /**
     * @uml.property name="codigoLenguaje"
     * @struts.form-field
     * @struts.validator type="required"
     */
    public String getCodigoLenguaje() {
        return codigoLenguaje;
    }

    /**
     * @uml.property name="codigoLenguaje"
     */
    public void setCodigoLenguaje(String codigoLenguaje) {
        this.codigoLenguaje = codigoLenguaje;
    }

    /**
     * Constructor por defecto
     */
    public CuentaClientePK() {
    }

    /**
     * @param codigo
     * @param codigoLenguaje
     */
    public CuentaClientePK(String codigo, String codigoLenguaje) {
        super();
        this.codigo = codigo;
        this.codigoLenguaje = codigoLenguaje;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof CuentaClientePK)) {
            return false;
        }
        CuentaClientePK rhs = (CuentaClientePK) object;
        return new EqualsBuilder().append(this.codigoLenguaje,
                rhs.codigoLenguaje).append(this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1818674911, -678842747).append(
                this.codigoLenguaje).append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoLenguaje", this.codigoLenguaje).append("codigo",
                        this.codigo).toString();
    }
}
