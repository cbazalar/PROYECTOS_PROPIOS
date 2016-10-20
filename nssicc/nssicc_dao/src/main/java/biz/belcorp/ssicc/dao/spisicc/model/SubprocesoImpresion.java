/*
 * Created on 18/01/2007 04:04:22 PM
 * biz.belcorp.ssicc.model.SubprocesoImpresion
 */
package biz.belcorp.ssicc.dao.spisicc.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="SubprocesoImpresion.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class SubprocesoImpresion extends AuditableBaseObject implements
        Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -577576621012933248L;

	private String codigoProceso;

    private String codigo;

    private String descripcion;

    private int ordenEjecucion;

    private String estado;

    private String nombreArchivo;

    private List archivosImpresion;;

    /**
     * @return Returns the archivosImpresion.
     */
    public List getArchivosImpresion() {
        return archivosImpresion;
    }

    /**
     * @param archivosImpresion
     *            The archivosImpresion to set.
     */
    public void setArchivosImpresion(List archivosImpresion) {
        this.archivosImpresion = archivosImpresion;
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
     * @return Returns the descripcion.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            The descripcion to set.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return Returns the ordenEjecucion.
     */
    public int getOrdenEjecucion() {
        return ordenEjecucion;
    }

    /**
     * @param ordenEjecucion
     *            The ordenEjecucion to set.
     */
    public void setOrdenEjecucion(int ordenEjecucion) {
        this.ordenEjecucion = ordenEjecucion;
    }

    /**
     * @return Returns the nombreArchivo.
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo
     *            The nombreArchivo to set.
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(870456325, 617889583).append(
                this.ordenEjecucion).append(this.descripcion).append(
                this.auditInfo).append(this.estado).append(this.codigoProceso)
                .append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof SubprocesoImpresion)) {
            return false;
        }
        SubprocesoImpresion rhs = (SubprocesoImpresion) object;
        return new EqualsBuilder().append(this.ordenEjecucion,
                rhs.ordenEjecucion).append(this.descripcion, rhs.descripcion)
                .append(this.auditInfo, rhs.auditInfo).append(this.estado,
                        rhs.estado).append(this.codigoProceso,
                        rhs.codigoProceso).append(this.codigo, rhs.codigo)
                .isEquals();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("estado", this.estado).append("ordenEjecucion",
                        this.ordenEjecucion)
                .append("auditInfo", this.auditInfo).append("codigoProceso",
                        this.codigoProceso).append("codigo", this.codigo)
                .append("descripcion", this.descripcion).toString();
    }

}
