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
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Menu.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class Menu extends AuditableBaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4043211484394042180L;

	private String codigo;
    
    protected String disponibles;
    protected String activos;
    
    protected String[] arregloDisponibles;
    protected String[] arregloActivos;
    
    
    protected String codigoOpciones;
    
    protected String codigoMenu;
    
    protected String codPais;
    
	private String[] botonesAsignados;

	private String[] botonesNoAsignados;
	
	private String indicadorOcultarMenu;
	
	private Integer numeroParametros;
	
	private String  nroSession;

    /**
	 * @return the numeroParametros
	 */
	public Integer getNumeroParametros() {
		return numeroParametros;
	}

	/**
	 * @param numeroParametros the numeroParametros to set
	 */
	public void setNumeroParametros(Integer numeroParametros) {
		this.numeroParametros = numeroParametros;
	}

	public String[] getBotonesAsignados() {
		return botonesAsignados;
	}

	public void setBotonesAsignados(String[] botonesAsignados) {
		this.botonesAsignados = botonesAsignados;
	}

	public String[] getBotonesNoAsignados() {
		return botonesNoAsignados;
	}

	public void setBotonesNoAsignados(String[] botonesNoAsignados) {
		this.botonesNoAsignados = botonesNoAsignados;
	}

	public String getCodigoMenu() {
		return codigoMenu;
	}

	public void setCodigoMenu(String codigoMenu) {
		this.codigoMenu = codigoMenu;
	}

	public String getCodigoOpciones() {
		return codigoOpciones;
	}

	public void setCodigoOpciones(String codigoOpciones) {
		this.codigoOpciones = codigoOpciones;
	}

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

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    private int posicion;

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    private Boolean tipoOculto = Boolean.FALSE;

    public Boolean getTipoOculto() {
        return tipoOculto;
    }

    public void setTipoOculto(Boolean tipoOculto) {
        this.tipoOculto = tipoOculto;
    }

    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private String informacionAyuda;

    public String getInformacionAyuda() {
        return informacionAyuda;
    }

    public void setInformacionAyuda(String informacionAyuda) {
        this.informacionAyuda = informacionAyuda;
    }

    private Menu menuPadre;

    public Menu getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(Menu menuPadre) {
        this.menuPadre = menuPadre;
    }

    private List submenues = new ArrayList();

    public List getSubmenues() {
        return submenues;
    }

    public void setSubmenues(List submenues) {
        this.submenues = submenues;
    }

    private List parametros = new ArrayList();

    public List getParametros() {
        return parametros;
    }

    public void setParametros(List parametros) {
        this.parametros = parametros;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu rhs = (Menu) object;
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

	public String getActivos() {
		return activos;
	}
	
	public void setActivos(String activos) {
		this.activos = activos;
	}
	
	public String getDisponibles() {
		return disponibles;
	}
	
	public void setDisponibles(String disponibles) {
		this.disponibles = disponibles;
	}
	
	public String[] getArregloActivos() {
		return arregloActivos;
	}
	
	public void setArregloActivos(String[] arregloActivos) {
		this.arregloActivos = arregloActivos;
	}
	
	public String[] getArregloDisponibles() {
		return arregloDisponibles;
	}
	
	public void setArregloDisponibles(String[] arregloDisponibles) {
		this.arregloDisponibles = arregloDisponibles;
	}
	
	public String getCodPais() {
		return codPais;
	}
	
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	

	/* NUEVO ACTUALIZACION FRAMEWORK JSF */
	private String urlJSF;
	private String paginaXml;
	private String parametrosXml;
	private String parametrosVariables;
	private String parametrosValores;
	
	/**
	 * @return the urlJSF
	 */
	public String getUrlJSF() {
		return urlJSF;
	}

	/**
	 * @param urlJSF the urlJSF to set
	 */
	public void setUrlJSF(String urlJSF) {
		this.urlJSF = urlJSF;
	}

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

	/**
	 * @return the parametrosXml
	 */
	public String getParametrosXml() {
		return parametrosXml;
	}

	/**
	 * @param parametrosXml the parametrosXml to set
	 */
	public void setParametrosXml(String parametrosXml) {
		this.parametrosXml = parametrosXml;
	}

	/**
	 * @return the parametrosVariables
	 */
	public String getParametrosVariables() {
		return parametrosVariables;
	}

	/**
	 * @param parametrosVariables the parametrosVariables to set
	 */
	public void setParametrosVariables(String parametrosVariables) {
		this.parametrosVariables = parametrosVariables;
	}

	/**
	 * @return the parametrosValores
	 */
	public String getParametrosValores() {
		return parametrosValores;
	}

	/**
	 * @param parametrosValores the parametrosValores to set
	 */
	public void setParametrosValores(String parametrosValores) {
		this.parametrosValores = parametrosValores;
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

	/**
	 * @return the nroSession
	 */
	public String getNroSession() {
		return nroSession;
	}

	/**
	 * @param nroSession the nroSession to set
	 */
	public void setNroSession(String nroSession) {
		this.nroSession = nroSession;
	}
	
	
	
	
}
