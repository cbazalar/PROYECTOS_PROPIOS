/*
 * Created on 09/02/2005 03:55:37 PM biz.belcorp.ssicc.model.Pais
 */
package biz.belcorp.ssicc.dao.scdf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class descripcion here.
 * <p>
 * <a href="Pais.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 * @struts.form include-all="false" extends="BaseEditForm"
 * @hibernate.class table="BAS_PAIS" dynamic-update="true"
 */

public class Region extends AuditableBaseObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3332193989517482925L;

	private String codigo;

	private String codigoSubgerencia;

    private String codigoPais;

    private String descripcion;

    private String estado;

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
     * @return Returns the codigoSubgerencia.
     */
    public String getCodigoSubgerencia() {
        return codigoSubgerencia;
    }

    /**
     * @param codigoSubgerencia The codigoSubgerencia to set.
     */
    public void setCodigoSubgerencia(String codigoSubgerencia) {
        this.codigoSubgerencia = codigoSubgerencia;
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
     * @return Returns the estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado The estado to set.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Region)) {
            return false;
        }
        Region rhs = (Region) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais)
                .append(this.codigoSubgerencia, rhs.codigoSubgerencia).append(
                        this.descripcion, rhs.descripcion).append(
                        this.auditInfo, rhs.auditInfo).append(this.estado,
                        rhs.estado).append(this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1000411799, 980572905).append(
                this.codigoPais).append(this.codigoSubgerencia).append(
                this.descripcion).append(this.auditInfo).append(this.estado)
                .append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoPais", this.codigoPais).append("estado",
                        this.estado).append("auditInfo", this.auditInfo)
                .append("codigoSubgerencia", this.codigoSubgerencia).append(
                        "codigo", this.codigo).append("descripcion",
                        this.descripcion).toString();
    }
    
}