/*
 * Created on 10/11/2005 12:11:37 PM biz.belcorp.ssicc.model.DescripcionMenu
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
 * <a href="DescripcionMenu.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class DescripcionMenu extends BaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5811098527038851079L;
	
	/**
     * @uml.property name="codigoMenu" multiplicity="(0 1)"
     */
    private String codigoMenu;
    private String pais_cod_pais;
    
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
     * @uml.property name="codigoIdioma" multiplicity="(0 1)"
     */
    private String codigoIdioma;

    /**
     * @uml.property name="codigoIdioma"
     * @struts.form-field
     */
    public String getCodigoIdioma() {
        return codigoIdioma;
    }

    /**
     * @uml.property name="codigoIdioma"
     */
    public void setCodigoIdioma(String codigoIdioma) {
        this.codigoIdioma = codigoIdioma;
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
     * @uml.property name="informacion" multiplicity="(0 1)"
     */
    private String informacionAyuda;

    /**
     * @uml.property name="informacionAyuda"
     * @struts.form-field
     */
    public String getInformacionAyuda() {
        return informacionAyuda;
    }

    /**
     * @uml.property name="informacionAyuda"
     */
    public void setInformacionAyuda(String informacionAyuda) {
        this.informacionAyuda = informacionAyuda;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof DescripcionMenu)) {
            return false;
        }
        DescripcionMenu rhs = (DescripcionMenu) object;
        return new EqualsBuilder().append(this.descripcion, rhs.descripcion)
                .append(this.codigoIdioma, rhs.codigoIdioma).append(this.codigoMenu, rhs.codigoMenu).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-169506231, -1010329089).append(this.descripcion).append(this.codigoIdioma).append(
                this.codigoMenu).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoMenu", this.codigoMenu).append(
                "codigoIdioma", this.codigoIdioma).append("descripcion", this.descripcion).toString();
    }

	public String getPais_cod_pais() {
		return pais_cod_pais;
	}

	public void setPais_cod_pais(String pais_cod_pais) {
		this.pais_cod_pais = pais_cod_pais;
	}
}
