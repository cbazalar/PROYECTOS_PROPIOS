/*
 * Created on 18/01/2007 03:51:16 PM
 * biz.belcorp.ssicc.model.ProcesoImpresion
 */
package biz.belcorp.ssicc.dao.spisicc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresion.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class ProcesoImpresion extends AuditableBaseObject implements
        Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5062860170685299636L;

	private String codigo;

    private String directorioOrigen;

    private String directorioDestino;

    private String directorioHistorico;

    private String prefijoArchivo;

    private long numeroCorrelativoAcumulado;

    private long numeroCorrelativoDiario;

    private Date fechaUltimaEjecucion;

    private String flagEnvioFTP;

    private String servidorFTP;

    private int puertoFTP;

    private String usuarioFTP;

    private String passwordFTP;

    private String directorioFTP;

    private String descripcion;

    private String estado;

    private List subprocesosImpresion;

    /**
     * @return Returns the subprocesosImpresion.
     */
    public List getSubprocesosImpresion() {
        return subprocesosImpresion;
    }

    /**
     * @param subprocesosImpresion
     *            The subprocesosImpresion to set.
     */
    public void setSubprocesosImpresion(List subprocesosImpresion) {
        this.subprocesosImpresion = subprocesosImpresion;
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
     * @return Returns the directorioDestino.
     */
    public String getDirectorioDestino() {
        return directorioDestino;
    }

    /**
     * @param directorioDestino
     *            The directorioDestino to set.
     */
    public void setDirectorioDestino(String directorioDestino) {
        this.directorioDestino = directorioDestino;
    }

    /**
     * @return Returns the directorioFTP.
     */
    public String getDirectorioFTP() {
        return directorioFTP;
    }

    /**
     * @param directorioFTP
     *            The directorioFTP to set.
     */
    public void setDirectorioFTP(String directorioFTP) {
        this.directorioFTP = directorioFTP;
    }

    /**
     * @return Returns the directorioHistorico.
     */
    public String getDirectorioHistorico() {
        return directorioHistorico;
    }

    /**
     * @param directorioHistorico
     *            The directorioHistorico to set.
     */
    public void setDirectorioHistorico(String directorioHistorico) {
        this.directorioHistorico = directorioHistorico;
    }

    /**
     * @return Returns the directorioOrigen.
     */
    public String getDirectorioOrigen() {
        return directorioOrigen;
    }

    /**
     * @param directorioOrigen
     *            The directorioOrigen to set.
     */
    public void setDirectorioOrigen(String directorioOrigen) {
        this.directorioOrigen = directorioOrigen;
    }

    /**
     * @return Returns the fechaUltimaEjecucion.
     */
    public Date getFechaUltimaEjecucion() {
        return fechaUltimaEjecucion;
    }

    /**
     * @param fechaUltimaEjecucion
     *            The fechaUltimaEjecucion to set.
     */
    public void setFechaUltimaEjecucion(Date fechaUltimaEjecucion) {
        this.fechaUltimaEjecucion = fechaUltimaEjecucion;
    }

    /**
     * @return Returns the flagEnvioFTP.
     */
    public String getFlagEnvioFTP() {
        return flagEnvioFTP;
    }

    /**
     * @param flagEnvioFTP
     *            The flagEnvioFTP to set.
     */
    public void setFlagEnvioFTP(String flagEnvioFTP) {
        this.flagEnvioFTP = flagEnvioFTP;
    }

    /**
     * @return Returns the numeroCorrelativoAcumulado.
     */
    public long getNumeroCorrelativoAcumulado() {
        return numeroCorrelativoAcumulado;
    }

    /**
     * @param numeroCorrelativoAcumulado
     *            The numeroCorrelativoAcumulado to set.
     */
    public void setNumeroCorrelativoAcumulado(long numeroCorrelativoAcumulado) {
        this.numeroCorrelativoAcumulado = numeroCorrelativoAcumulado;
    }

    /**
     * @return Returns the numeroCorrelativoDiario.
     */
    public long getNumeroCorrelativoDiario() {
        return numeroCorrelativoDiario;
    }

    /**
     * @param numeroCorrelativoDiario
     *            The numeroCorrelativoDiario to set.
     */
    public void setNumeroCorrelativoDiario(long numeroCorrelativoDiario) {
        this.numeroCorrelativoDiario = numeroCorrelativoDiario;
    }

    /**
     * @return Returns the passwordFTP.
     */
    public String getPasswordFTP() {
        return passwordFTP;
    }

    /**
     * @param passwordFTP
     *            The passwordFTP to set.
     */
    public void setPasswordFTP(String passwordFTP) {
        this.passwordFTP = passwordFTP;
    }

    /**
     * @return Returns the prefijoArchivo.
     */
    public String getPrefijoArchivo() {
        return prefijoArchivo;
    }

    /**
     * @param prefijoArchivo
     *            The prefijoArchivo to set.
     */
    public void setPrefijoArchivo(String prefijoArchivo) {
        this.prefijoArchivo = prefijoArchivo;
    }

    /**
     * @return Returns the puertoFTP.
     */
    public int getPuertoFTP() {
        return puertoFTP;
    }

    /**
     * @param puertoFTP
     *            The puertoFTP to set.
     */
    public void setPuertoFTP(int puertoFTP) {
        this.puertoFTP = puertoFTP;
    }

    /**
     * @return Returns the servidorFTP.
     */
    public String getServidorFTP() {
        return servidorFTP;
    }

    /**
     * @param servidorFTP
     *            The servidorFTP to set.
     */
    public void setServidorFTP(String servidorFTP) {
        this.servidorFTP = servidorFTP;
    }

    /**
     * @return Returns the usuarioFTP.
     */
    public String getUsuarioFTP() {
        return usuarioFTP;
    }

    /**
     * @param usuarioFTP
     *            The usuarioFTP to set.
     */
    public void setUsuarioFTP(String usuarioFTP) {
        this.usuarioFTP = usuarioFTP;
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
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ProcesoImpresion)) {
            return false;
        }
        ProcesoImpresion rhs = (ProcesoImpresion) object;
        return new EqualsBuilder().append(this.usuarioFTP, rhs.usuarioFTP)
                .append(this.prefijoArchivo, rhs.prefijoArchivo).append(
                        this.directorioFTP, rhs.directorioFTP).append(
                        this.numeroCorrelativoDiario,
                        rhs.numeroCorrelativoDiario).append(
                        this.numeroCorrelativoAcumulado,
                        rhs.numeroCorrelativoAcumulado).append(
                        this.directorioDestino, rhs.directorioDestino).append(
                        this.fechaUltimaEjecucion, rhs.fechaUltimaEjecucion)
                .append(this.auditInfo, rhs.auditInfo).append(
                        this.directorioOrigen, rhs.directorioOrigen).append(
                        this.directorioHistorico, rhs.directorioHistorico)
                .append(this.flagEnvioFTP, rhs.flagEnvioFTP).append(
                        this.puertoFTP, rhs.puertoFTP).append(this.passwordFTP,
                        rhs.passwordFTP).append(this.servidorFTP,
                        rhs.servidorFTP).append(this.codigo, rhs.codigo)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-774752705, 1988861249).append(
                this.usuarioFTP).append(this.prefijoArchivo).append(
                this.directorioFTP).append(this.numeroCorrelativoDiario)
                .append(this.numeroCorrelativoAcumulado).append(
                        this.directorioDestino).append(
                        this.fechaUltimaEjecucion).append(this.auditInfo)
                .append(this.directorioOrigen).append(this.directorioHistorico)
                .append(this.flagEnvioFTP).append(this.puertoFTP).append(
                        this.passwordFTP).append(this.servidorFTP).append(
                        this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("directorioDestino", this.directorioDestino).append(
                        "puertoFTP", this.puertoFTP).append("passwordFTP",
                        this.passwordFTP).append("numeroCorrelativoDiario",
                        this.numeroCorrelativoDiario).append("auditInfo",
                        this.auditInfo).append("prefijoArchivo",
                        this.prefijoArchivo).append("flagEnvioFTP",
                        this.flagEnvioFTP).append("directorioOrigen",
                        this.directorioOrigen).append("directorioHistorico",
                        this.directorioHistorico).append("servidorFTP",
                        this.servidorFTP).append("directorioFTP",
                        this.directorioFTP).append(
                        "numeroCorrelativoAcumulado",
                        this.numeroCorrelativoAcumulado).append(
                        "fechaUltimaEjecucion", this.fechaUltimaEjecucion)
                .append("usuarioFTP", this.usuarioFTP).append("codigo",
                        this.codigo).toString();
    }

}
