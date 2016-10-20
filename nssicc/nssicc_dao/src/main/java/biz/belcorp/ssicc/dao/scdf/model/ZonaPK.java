/*
 * Created on 10/02/2005 03:21:02 PM biz.belcorp.ssicc.model.OpcionPK
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO Include class description here.
 * <p>
 * <a href="OpcionPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class ZonaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220456005856111948L;

	private String codigoPais;
	
	private String codigoSubgerencia;
	
	private String codigoRegion;
	
	private String codigo;
	
    /**
     *  
     */
    public ZonaPK() {
    }

    /**
     * @param codigoPais
     * @param codigoSubgerencia
     * @param codigoRegion
     */
    public ZonaPK(String codigoPais, String codigoSubgerencia, String codigoRegion) {
        this.codigoPais = codigoPais;
        this.codigoSubgerencia = codigoSubgerencia;
        this.codigoRegion = codigoRegion;
    }
    
    /**
     * @param codigoPais
     * @param codigoSubgerencia
     * @param codigoRegion
     * @param codigo
     */
    public ZonaPK(String codigoPais, String codigoSubgerencia, String codigoRegion, String codigo) {
        this.codigoPais = codigoPais;
        this.codigoSubgerencia = codigoSubgerencia;
        this.codigoRegion = codigoRegion;
        this.codigo = codigo;
    }

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
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
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
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ZonaPK)) {
            return false;
        }
        ZonaPK rhs = (ZonaPK) object;
        return new EqualsBuilder().append(this.codigoSubgerencia, rhs.codigoSubgerencia)
                .append(this.codigoPais, rhs.codigoPais).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-515242531, 564779073)
                .append(this.codigoSubgerencia).append(this.codigoPais).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoSubgerencia", this.codigoSubgerencia).append("codigoPais",
                        this.codigoPais).toString();
    }
}