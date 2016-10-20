/*
 * Created on 08/11/2005 05:03:24 PM biz.belcorp.ssicc.model.Rol
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Rol.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class Rol extends AuditableBaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2071548685343446940L;
	
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
     * @uml.property name="descripcion" multiplicity="(0 1)"
     */
    private String descripcion;

    /**
     * @uml.property name="descripcion"
     * @struts.form-field
     * @struts.validator type="required"
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @uml.property name="descripcion"
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @uml.property name="estado" multiplicity="(0 1)"
     */
    private String estado;

    /**
     * @uml.property name="estado"
     * @struts.form-field
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @uml.property name="estado"
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @uml.property name="pais"
     * @uml.associationEnd inverse="user:biz.belcorp.ssicc.model.Pais"
     *                     multiplicity= "(0 1)"
     */
    private Pais pais = new Pais();

    /**
     * @uml.property name="pais"
     */
    public Pais getPais() {
        return pais;
    }

    /**
     * @uml.property name="pais"
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * @uml.property name="accesos"
     */
    private List accesos;
    
    /**
     * @uml.property name="accesos"
     */
    public List getAccesos() {
        return accesos;
    }
    
    /**
     * @uml.property name="accesos"
     */
    public void setAccesos(List accesos) {
        this.accesos = accesos;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol rhs = (Rol) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais).append(this.descripcion, rhs.descripcion)
                .append(this.auditInfo, rhs.auditInfo).append(this.estado, rhs.estado).append(this.codigo, rhs.codigo)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(163362635, 951874499).append(this.codigoPais).append(this.descripcion).append(
                this.auditInfo).append(this.estado).append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais).append(
                "estado", this.estado).append("auditInfo", this.auditInfo).append("codigo", this.codigo).append(
                "descripcion", this.descripcion).toString();
    }
    
	private boolean estadoAcceso;

	/**
	 * @return the estadoAcceso
	 */
	public boolean isEstadoAcceso() {
		return estadoAcceso;
	}

	/**
	 * @param estadoAcceso the estadoAcceso to set
	 */
	public void setEstadoAcceso(boolean estadoAcceso) {
		this.estadoAcceso = estadoAcceso;
	}
}
