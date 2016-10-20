package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class DatosConsultora extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String codigoCliente;
	protected String nombreCliente;
	protected String zona;
	protected String nombreManager;
	
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the nombreManager
	 */
	public String getNombreManager() {
		return nombreManager;
	}

	/**
	 * @param nombreManager the nombreManager to set
	 */
	public void setNombreManager(String nombreManager) {
		this.nombreManager = nombreManager;
	}
	
	/**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Rol)) {
            return false;
        }
     
        return true;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
    		return 0;    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "";
    }
}
