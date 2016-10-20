/*
 * Created on 10/11/2005 09:27:02 AM biz.belcorp.ssicc.model.ParametroMenu
 */
package biz.belcorp.ssicc.dao.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ParametroMenu.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class ParametroMenu extends AuditableBaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2677956446882918275L;
	/**
     *
     */
    private String codigoMenu;

    /**
     *
     */
    public String getCodigoMenu() {
        return codigoMenu;
    }

    /**
     *
     */
    public void setCodigoMenu(String codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    /**
     *
     */
    private long numero;

    /**
     *
     */
    public long getNumero() {
        return numero;
    }

    /**
     *
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }

    /**
     *
     */
    private String nombre;

    /**
     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *      
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     */
    private String valor;

    /**
     * 
     */
    public String getValor() {
        return valor;
    }

    /**
     * 
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * 
     */
    private String estado;

    /**
     *
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

}
