package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Reclamos extends AuditableBaseObject implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6981922245955937784L;
	private String codigoPais;
    private String codigoOperacionHomologada;
    private String codigoOperacion;
    private String observacion;
    private String descripcion;
    private String[] operaciones;
    private String[] operacionesHomologadas;
    private String estado;
    private String id;

    public String getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(String codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public String getCodigoOperacionHomologada() {
        return codigoOperacionHomologada;
    }

    public void setCodigoOperacionHomologada(String codigoOperacionHomologada) {
        this.codigoOperacionHomologada = codigoOperacionHomologada;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String[] getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(String[] operaciones) {
        this.operaciones = operaciones;
    }

    public String[] getOperacionesHomologadas() {
        return operacionesHomologadas;
    }

    public void setOperacionesHomologadas(String[] operacionesHomologadas) {
        this.operacionesHomologadas = operacionesHomologadas;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais)
                                        .append("observacion", this.observacion)
                                        .append("id", this.id)
                                        .append("estado", this.estado)
                                        .append("operaciones", this.operaciones)
                                        .append("codigoOperacion",
            this.codigoOperacion)
                                        .append("codigoOperacionHomologada",
            this.codigoOperacionHomologada).append("auditInfo", this.auditInfo)
                                        .append("operacionesHomologadas",
            this.operacionesHomologadas).append("descripcion", this.descripcion)
                                        .toString();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Reclamos)) {
            return false;
        }

        Reclamos rhs = (Reclamos) object;

        return new EqualsBuilder().append(this.codigoOperacionHomologada,
            rhs.codigoOperacionHomologada)
                                  .append(this.operacionesHomologadas,
            rhs.operacionesHomologadas).append(this.codigoPais, rhs.codigoPais)
                                  .append(this.operaciones, rhs.operaciones)
                                  .append(this.observacion, rhs.observacion)
                                  .append(this.descripcion, rhs.descripcion)
                                  .append(this.codigoOperacion,
            rhs.codigoOperacion).append(this.auditInfo, rhs.auditInfo)
                                  .append(this.estado, rhs.estado)
                                  .append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1054922849, -863815175).append(this.codigoOperacionHomologada)
                                                          .append(this.operacionesHomologadas)
                                                          .append(this.codigoPais)
                                                          .append(this.operaciones)
                                                          .append(this.observacion)
                                                          .append(this.descripcion)
                                                          .append(this.codigoOperacion)
                                                          .append(this.auditInfo)
                                                          .append(this.estado)
                                                          .append(this.id)
                                                          .toHashCode();
    }
}
