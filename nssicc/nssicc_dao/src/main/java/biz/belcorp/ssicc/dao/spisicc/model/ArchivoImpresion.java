/*
 * Created on 18/01/2007 04:06:07 PM
 * biz.belcorp.ssicc.model.ArchivoImpresion
 */
package biz.belcorp.ssicc.dao.spisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ArchivoImpresion.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class ArchivoImpresion extends BaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3012439422738579655L;

	private String codigoProceso;

    private String codigoSubproceso;

    private int correlativo;

    private String patronNombre;

    private int orden;

    private String estado;

    /**
     * @return Returns the codigoProceso.
     */
    public String getCodigoProceso() {
        return codigoProceso;
    }

    /**
     * @param codigoProceso
     *            The codigoProceso to set.
     */
    public void setCodigoProceso(String codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    /**
     * @return Returns the codigoSubproceso.
     */
    public String getCodigoSubproceso() {
        return codigoSubproceso;
    }

    /**
     * @param codigoSubproceso
     *            The codigoSubproceso to set.
     */
    public void setCodigoSubproceso(String codigoSubproceso) {
        this.codigoSubproceso = codigoSubproceso;
    }

    /**
     * @return Returns the correlativo.
     */
    public int getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo
     *            The correlativo to set.
     */
    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    /**
     * @return Returns the estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            The estado to set.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return Returns the orden.
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden
     *            The orden to set.
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return Returns the patronNombre.
     */
    public String getPatronNombre() {
        return patronNombre;
    }

    /**
     * @param patronNombre
     *            The patronNombre to set.
     */
    public void setPatronNombre(String patronNombre) {
        this.patronNombre = patronNombre;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ArchivoImpresion)) {
            return false;
        }
        ArchivoImpresion rhs = (ArchivoImpresion) object;
        return new EqualsBuilder().append(this.correlativo, rhs.correlativo)
                .append(this.orden, rhs.orden).append(this.patronNombre,
                        rhs.patronNombre).append(this.codigoSubproceso,
                        rhs.codigoSubproceso).append(this.estado, rhs.estado)
                .append(this.codigoProceso, rhs.codigoProceso).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1195941613, 610633901).append(
                this.correlativo).append(this.orden).append(this.patronNombre)
                .append(this.codigoSubproceso).append(this.estado).append(
                        this.codigoProceso).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("patronNombre", this.patronNombre).append("estado",
                        this.estado).append("codigoSubproceso",
                        this.codigoSubproceso).append("codigoProceso",
                        this.codigoProceso).append("orden", this.orden).append(
                        "correlativo", this.correlativo).toString();
    }

}
