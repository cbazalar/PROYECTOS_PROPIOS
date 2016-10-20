/*
 * Created on 10/02/2005 03:01:53 PM
 *
 * biz.belcorp.ssicc.model.NivelRolPK
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
 * <a href="TarjetaPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class TarjetaPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4415640478996522081L;
	/**
     * 
     * @uml.property name="codigoPadre" multiplicity="(0 1)"
     */
    private String codigoPais;

    /**
     * 
     * @uml.property name="codigoPais"
     * @hibernate.property column="ROL_COD_ROL"
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * 
     * @uml.property name="codigoPais"
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * 
     * @uml.property name="codigoHijo" multiplicity="(0 1)"
     */
    private String numeroTarjeta;

    /**
	 * @return Returns the numeroTarjeta.
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * @param numeroTarjeta The numeroTarjeta to set.
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	
	public int correlativo;
	
	
	/**
	 * @return Returns the correlativo.
	 */
	public int getCorrelativo() {
		return correlativo;
	}

	/**
	 * @param correlativo The correlativo to set.
	 */
	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}

	/**
	 * Constructor invocado en InterfazPrivilegeServiceImpl
	 * 
	 */

	public TarjetaPK(String codigoPais, String numeroTarjeta) {
        this.codigoPais = codigoPais;
        this.numeroTarjeta = numeroTarjeta;
    }

    public TarjetaPK() {
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof TarjetaPK)) {
            return false;
        }
        TarjetaPK rhs = (TarjetaPK) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais)
                .append(this.numeroTarjeta, rhs.numeroTarjeta).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1675146587, -1171061823).append(
                this.codigoPais).append(this.numeroTarjeta).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoPais", this.codigoPais).append("numeroTarjeta",
                        this.numeroTarjeta).toString();
    }
}