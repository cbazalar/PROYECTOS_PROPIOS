/*
 * Created on 08/11/2005 05:21:43 PM biz.belcorp.ssicc.model.Menu
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MenuRol.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Antonio Agurto Jimenez </a>
 */
public class MenuRol extends AuditableBaseObject implements Serializable {

	private String estadoActivo;
	private String descripcionPadre;	
	private String indicadorOcultarMenu;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3155097074366227655L;
		
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    private String codigoPadre;

    public String getCodigoPadre() {
        return codigoPadre;
    }

    public void setCodigoPadre(String codigoPadre) {
        this.codigoPadre = codigoPadre;
    }

    private String codigoProceso;

    public String getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(String codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private String informacionAyuda;

    public String getInformacionAyuda() {
        return informacionAyuda;
    }

    public void setInformacionAyuda(String informacionAyuda) {
        this.informacionAyuda = informacionAyuda;
    }

    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    private String pagina;

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    private String nivel;

    public String getNivel() {
        return nivel;
    }

    /**
     * @uml.property name="nivel"
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @uml.property name="posicion" multiplicity="(0 1)"
     */
    private int posicion;

    /**
     * @uml.property name="posicion"     
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @uml.property name="posicion"
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * @uml.property name="tipoOculto" multiplicity="(0 1)"
     */
    private Boolean tipoOculto = Boolean.FALSE;

    /**
     * @uml.property name="tipoOculto"     
     */
    public Boolean getTipoOculto() {
        return tipoOculto;
    }

    /**
     * @uml.property name="tipoOculto"
     */
    public void setTipoOculto(Boolean tipoOculto) {
        this.tipoOculto = tipoOculto;
    }

    /**
     * @uml.property name="estado" multiplicity="(0 1)"
     */
    private String estado;

    /**
     * @uml.property name="estado"           
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
     * @uml.property name="menuPadre"
     * @uml.associationEnd inverse="bonus:biz.belcorp.ssicc.model.Menu"
     *                     multiplicity= "(0 1)"
     */
    private MenuRol menuPadre;

    /**
     * @uml.property name="menuPadre"
     */
    public MenuRol getMenuPadre() {
        return menuPadre;
    }

    /**
     * @uml.property name="menuPadre"
     */
    public void setMenuPadre(MenuRol menuPadre) {
        this.menuPadre = menuPadre;
    }

    /**
     * @uml.property name="submenues"
     */
    private List submenues = new ArrayList();

    /**
     * @uml.property name="submenues"
     * @return Returns the submenues.
     */
    public List getSubmenues() {
        return submenues;
    }

    /**
     * @uml.property name="submenues"
     * @param submenues
     *            The submenues to set.
     */
    public void setSubmenues(List submenues) {
        this.submenues = submenues;
    }

    /**
     * @uml.property name="parametros"
     */
    private List parametros = new ArrayList();

    /**
     * @uml.property name="parametros"
     * @return Returns the parametros.
     */
    public List getParametros() {
        return parametros;
    }

    /**
     * @uml.property name="parametros"
     * @param parametros
     *            The parametros to set.
     */
    public void setParametros(List parametros) {
        this.parametros = parametros;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof MenuRol)) {
            return false;
        }
        MenuRol rhs = (MenuRol) object;
        return new EqualsBuilder().append(this.tipoOculto, rhs.tipoOculto).append(this.nivel, rhs.nivel).append(
                this.accion, rhs.accion).append(this.pagina, rhs.pagina).append(this.posicion, rhs.posicion).append(
                this.descripcion, rhs.descripcion).append(this.submenues, rhs.submenues).append(this.auditInfo,
                rhs.auditInfo).append(this.estado, rhs.estado).append(this.codigoPadre, rhs.codigoPadre).append(
                this.menuPadre, rhs.menuPadre).append(this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1214997983, 769619265).append(this.tipoOculto).append(this.nivel).append(
                this.accion).append(this.pagina).append(this.posicion).append(this.descripcion).append(this.submenues)
                .append(this.auditInfo).append(this.estado).append(this.codigoPadre).append(this.menuPadre).append(
                        this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("submenues", this.submenues).append(
                "auditInfo", this.auditInfo).append("menuPadre", this.menuPadre).append("accion", this.accion).append(
                "descripcion", this.descripcion).append("estado", this.estado).append("posicion", this.posicion)
                .append("pagina", this.pagina).append("codigoPadre", this.codigoPadre).append("nivel", this.nivel)
                .append("codigo", this.codigo).append("tipoOculto", this.tipoOculto).toString();
    }

	/**
	 * @return Returns the estadoActivo.
	 */
	public String getEstadoActivo() {
		return estadoActivo;
	}

	/**
	 * @param estadoActivo The estadoActivo to set.
	 */
	public void setEstadoActivo(String estadoActivo) {
		this.estadoActivo = estadoActivo;
	}

	/**
	 * @return Returns the descripcionPadre.
	 */
	public String getDescripcionPadre() {
		return descripcionPadre;
	}

	/**
	 * @param descripcionPadre The descripcionPadre to set.
	 */
	public void setDescripcionPadre(String descripcionPadre) {
		this.descripcionPadre = descripcionPadre;
	}
	
	/* NUEVO ACTUALIZACION FRAMEWORK JSF */
	private String paginaXml;

	/**
	 * @return the paginaXml
	 */
	public String getPaginaXml() {
		return paginaXml;
	}

	/**
	 * @param paginaXml the paginaXml to set
	 */
	public void setPaginaXml(String paginaXml) {
		this.paginaXml = paginaXml;
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

	/**
	 * @return the indicadorOcultarMenu
	 */
	public String getIndicadorOcultarMenu() {
		return indicadorOcultarMenu;
	}

	/**
	 * @param indicadorOcultarMenu the indicadorOcultarMenu to set
	 */
	public void setIndicadorOcultarMenu(String indicadorOcultarMenu) {
		this.indicadorOcultarMenu = indicadorOcultarMenu;
	}
	
	
	
}
