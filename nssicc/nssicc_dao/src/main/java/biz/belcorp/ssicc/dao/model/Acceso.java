/*
 * Created on 10/11/2005 09:43:53 AM biz.belcorp.ssicc.model.Acceso
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Acceso.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class Acceso extends AuditableBaseObject implements Serializable {

    private static final long serialVersionUID = 4529153004045635075L;
    
	/**
     * @uml.property name="codigoMenu" multiplicity="(0 1)"
     */
    private String codigoMenu;

    /**
     * @uml.property name="codigoMenu"
     * @struts.form-field
     */
    public String getCodigoMenu() {
        return codigoMenu;
    }

    /**
     * @uml.property name="codigoMenu"
     */
    public void setCodigoMenu(String codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

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
     * @uml.property name="codigoRol" multiplicity="(0 1)"
     */
    private String codigoRol;

    /**
     * @uml.property name="codigoRol"
     * @struts.form-field
     */
    public String getCodigoRol() {
        return codigoRol;
    }

    /**
     * @uml.property name="codigoRol"
     */
    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
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
     * @uml.property name="menu"
     * @uml.associationEnd inverse="user:biz.belcorp.ssicc.model.Menu"
     *                     multiplicity= "(0 1)"
     */
    private Menu menu = new Menu();

    /**
     * @uml.property name="menu"
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @uml.property name="menu"
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * @uml.property name="rol"
     * @uml.associationEnd inverse="user:biz.belcorp.ssicc.model.Rol"
     *                     multiplicity= "(0 1)"
     */
    private Rol rol = new Rol();

    /**
     * @uml.property name="rol"
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @uml.property name="rol"
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Acceso)) {
            return false;
        }
        Acceso rhs = (Acceso) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais).append(this.codigoRol, rhs.codigoRol)
                .append(this.auditInfo, rhs.auditInfo).append(this.rol, rhs.rol).append(this.estado, rhs.estado)
                .append(this.menu, rhs.menu).append(this.codigoMenu, rhs.codigoMenu).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-115939767, 880369291).append(this.codigoPais).append(this.codigoRol).append(
                this.auditInfo).append(this.rol).append(this.estado).append(this.menu).append(this.codigoMenu)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais).append(
                "estado", this.estado).append("codigoRol", this.codigoRol).append("rol", this.rol).append("codigoMenu",
                this.codigoMenu).append("auditInfo", this.auditInfo).append("menu", this.menu).toString();
    }
}
