/*
 * Created on 20/09/2006 08:54:06 PM
 * biz.belcorp.ssicc.sisicc.model.ComponenteInterfazPaquete
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ComponenteInterfazPaquete.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class ComponenteInterfazPaquete extends BaseObject implements
        Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6757117462774090627L;

	/**
     * Codigo del pais
     */
    private String codigoPais;

    /**
     * Codigo del Sistema
     */
    private String codigoSistema;

    /**
     * Codigo de la interfaz tipo paquete
     */
    private String codigoInterfazPaquete;

    /**
     * Codigo de la interfaz unitaria
     */
    private String codigoInterfazUnitaria;

    /**
     * Orden en el cual van a ser ejecutadas las interfaces
     */
    private int ordenEjecucion;

    /**
     * Orden de ejecución multihilo
     */
    private Long ordenHilo;
    
    /**
     * Referencia a la interfaz tipo paquete
     */
    private Interfaz interfazPaquete;

    /**
     * Referencia a la interfaz unitaria
     */
    private Interfaz interfazUnitaria;
    
    private Long nivelHilo;

    /**
     * @return Returns the codigoInterfazPaquete.
     */
    public String getCodigoInterfazPaquete() {
        return codigoInterfazPaquete;
    }

    /**
     * @param codigoInterfazPaquete
     *            The codigoInterfazPaquete to set.
     */
    public void setCodigoInterfazPaquete(String codigoInterfazPaquete) {
        this.codigoInterfazPaquete = codigoInterfazPaquete;
    }

    /**
     * @return Returns the codigoInterfazUnitaria.
     */
    public String getCodigoInterfazUnitaria() {
        return codigoInterfazUnitaria;
    }

    /**
     * @param codigoInterfazUnitaria
     *            The codigoInterfazUnitaria to set.
     */
    public void setCodigoInterfazUnitaria(String codigoInterfazUnitaria) {
        this.codigoInterfazUnitaria = codigoInterfazUnitaria;
    }

    /**
	 * @return the ordenEjecucion
	 */
	public int getOrdenEjecucion() {
		return ordenEjecucion;
	}

	/**
	 * @param ordenEjecucion the ordenEjecucion to set
	 */
	public void setOrdenEjecucion(int ordenEjecucion) {
		this.ordenEjecucion = ordenEjecucion;
	}

	/**
	 * @return the ordenHilo
	 */
	public Long getOrdenHilo() {
		return ordenHilo;
	}

	/**
	 * @param ordenHilo the ordenHilo to set
	 */
	public void setOrdenHilo(Long ordenHilo) {
		this.ordenHilo = ordenHilo;
	}

	/**
     * @return Returns the codigoPais.
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * @param codigoPais
     *            The codigoPais to set.
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * @return Returns the codigoSistema.
     */
    public String getCodigoSistema() {
        return codigoSistema;
    }

    /**
     * @param codigoSistema
     *            The codigoSistema to set.
     */
    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    /**
     * @return Returns the interfazPaquete.
     */
    public Interfaz getInterfazPaquete() {
        return interfazPaquete;
    }

    /**
     * @param interfazPaquete
     *            The interfazPaquete to set.
     */
    public void setInterfazPaquete(Interfaz interfazPaquete) {
        this.interfazPaquete = interfazPaquete;
    }

    /**
     * @return Returns the interfazUnitaria.
     */
    public Interfaz getInterfazUnitaria() {
        return interfazUnitaria;
    }

    /**
     * @param interfazUnitaria
     *            The interfazUnitaria to set.
     */
    public void setInterfazUnitaria(Interfaz interfazUnitaria) {
        this.interfazUnitaria = interfazUnitaria;
    }

    
	/**
	 * @return the nivelHilo
	 */
	public Long getNivelHilo() {
		return nivelHilo;
	}

	/**
	 * @param nivelHilo the nivelHilo to set
	 */
	public void setNivelHilo(Long nivelHilo) {
		this.nivelHilo = nivelHilo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComponenteInterfazPaquete [codigoPais=" + codigoPais
				+ ", codigoSistema=" + codigoSistema
				+ ", codigoInterfazPaquete=" + codigoInterfazPaquete
				+ ", codigoInterfazUnitaria=" + codigoInterfazUnitaria
				+ ", ordenEjecucion=" + ordenEjecucion + ", ordenHilo="
				+ ordenHilo + ", interfazPaquete=" + interfazPaquete
				+ ", interfazUnitaria=" + interfazUnitaria + ", nivelHilo="
				+ nivelHilo + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoInterfazPaquete == null) ? 0 : codigoInterfazPaquete
						.hashCode());
		result = prime
				* result
				+ ((codigoInterfazUnitaria == null) ? 0
						: codigoInterfazUnitaria.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoSistema == null) ? 0 : codigoSistema.hashCode());
		result = prime * result
				+ ((interfazPaquete == null) ? 0 : interfazPaquete.hashCode());
		result = prime
				* result
				+ ((interfazUnitaria == null) ? 0 : interfazUnitaria.hashCode());
		result = prime * result
				+ ((nivelHilo == null) ? 0 : nivelHilo.hashCode());
		result = prime * result + ordenEjecucion;
		result = prime * result
				+ ((ordenHilo == null) ? 0 : ordenHilo.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComponenteInterfazPaquete other = (ComponenteInterfazPaquete) obj;
		if (codigoInterfazPaquete == null) {
			if (other.codigoInterfazPaquete != null)
				return false;
		} else if (!codigoInterfazPaquete.equals(other.codigoInterfazPaquete))
			return false;
		if (codigoInterfazUnitaria == null) {
			if (other.codigoInterfazUnitaria != null)
				return false;
		} else if (!codigoInterfazUnitaria.equals(other.codigoInterfazUnitaria))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoSistema == null) {
			if (other.codigoSistema != null)
				return false;
		} else if (!codigoSistema.equals(other.codigoSistema))
			return false;
		if (interfazPaquete == null) {
			if (other.interfazPaquete != null)
				return false;
		} else if (!interfazPaquete.equals(other.interfazPaquete))
			return false;
		if (interfazUnitaria == null) {
			if (other.interfazUnitaria != null)
				return false;
		} else if (!interfazUnitaria.equals(other.interfazUnitaria))
			return false;
		if (nivelHilo == null) {
			if (other.nivelHilo != null)
				return false;
		} else if (!nivelHilo.equals(other.nivelHilo))
			return false;
		if (ordenEjecucion != other.ordenEjecucion)
			return false;
		if (ordenHilo == null) {
			if (other.ordenHilo != null)
				return false;
		} else if (!ordenHilo.equals(other.ordenHilo))
			return false;
		return true;
	}
    
    
    
	
	

}
