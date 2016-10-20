/*
 * Created on 04-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
 * 
 * <p>
 * <a href="ClienteUAHistoricoPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class ClienteUAHistoricoPK extends BaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -900372505888978556L;

	private String codigoPais;

    private String codigo;

    private String numeroLote;

    /**
     * 
     */
    public ClienteUAHistoricoPK() {
        super();
    }

    /**
     * @param codigoPais
     * @param codigo
     */
    public ClienteUAHistoricoPK(String codigoPais, String codigo,
            String numeroLote) {
        super();
        this.codigoPais = codigoPais;
        this.codigo = codigo;
        this.numeroLote = numeroLote;
    }

    /**
     * @return Returns the codigo.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            The codigo to set.
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
     * @param codigoPais
     *            The codigoPais to set.
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * @return Returns the numeroLote.
     */
    public String getNumeroLote() {
        return numeroLote;
    }

    /**
     * @param numeroLote
     *            The numeroLote to set.
     */
    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ClienteUAHistoricoPK)) {
            return false;
        }
        ClienteUAHistoricoPK rhs = (ClienteUAHistoricoPK) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais)
                .append(this.numeroLote, rhs.numeroLote).append(this.codigo,
                        rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(746666743, 476125065)
                .append(this.codigoPais).append(this.numeroLote).append(
                        this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoPais", this.codigoPais).append("numeroLote",
                        this.numeroLote).append("codigo", this.codigo)
                .toString();
    }

}
