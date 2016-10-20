/*
 * Created on 18/01/2007 04:51:10 PM
 * biz.belcorp.ssicc.model.SubprocesoImpresionPK
 */
package biz.belcorp.ssicc.dao.spisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="SubprocesoImpresionPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class SubprocesoImpresionPK extends BaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6440255978373990386L;

	private String codigoProceso;

    private String codigo;

    /**
     * Default constructor
     */
    public SubprocesoImpresionPK() {
        super();
    }

    /**
     * @param codigoProceso
     * @param codigo
     */
    public SubprocesoImpresionPK(String codigoProceso, String codigo) {
        super();
        this.codigoProceso = codigoProceso;
        this.codigo = codigo;
    }

    /**
     * @return Returns the codigo.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            The codigo to set.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return Returns the codigoProceso.
     */
    public String getCodigoProceso() {
        return codigoProceso;
    }

    /**
     * @param codigoProceso
     *            The codigoProceso to set.
     */
    public void setCodigoProceso(String codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof SubprocesoImpresionPK)) {
            return false;
        }
        SubprocesoImpresionPK rhs = (SubprocesoImpresionPK) object;
        return new EqualsBuilder()
                .append(this.codigoProceso, rhs.codigoProceso).append(
                        this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(375712521, 833405657).append(
                this.codigoProceso).append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoProceso", this.codigoProceso).append("codigo",
                        this.codigo).toString();
    }

}
