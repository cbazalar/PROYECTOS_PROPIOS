/*
 * Created on 07/11/2005 02:10:21 PM biz.belcorp.ssicc.model.ControlFacturacion
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ControlFacturacion extends AuditableBaseObject implements
        Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4265416410962828789L;

	private String codigoPais;

    private String periodoProceso;

    private Date fechaProceso;

    private String codigoStickerActual;

    private String procesoActual;

    private float valorFactor;

    private String rutaArchivoPrivilege;

    private String codigoAlmacenVentaDirecta;

    private String codigoProgramaFidelizacion;

    private String codigoCanalVentaDirecta;

    private String codigoMarcaEbel;


    /**
     * @return Returns the codigoAlmacenVentaDirecta.
     */
    public String getCodigoAlmacenVentaDirecta() {
        return codigoAlmacenVentaDirecta;
    }

    /**
     * @param codigoAlmacenVentaDirecta The codigoAlmacenVentaDirecta to set.
     */
    public void setCodigoAlmacenVentaDirecta(String codigoAlmacenVentaDirecta) {
        this.codigoAlmacenVentaDirecta = codigoAlmacenVentaDirecta;
    }

    /**
     * @return Returns the codigoCanalVentaDirecta.
     */
    public String getCodigoCanalVentaDirecta() {
        return codigoCanalVentaDirecta;
    }

    /**
     * @param codigoCanalVentaDirecta The codigoCanalVentaDirecta to set.
     */
    public void setCodigoCanalVentaDirecta(String codigoCanalVentaDirecta) {
        this.codigoCanalVentaDirecta = codigoCanalVentaDirecta;
    }

    /**
     * @return Returns the codigoMarcaEbel.
     */
    public String getCodigoMarcaEbel() {
        return codigoMarcaEbel;
    }

    /**
     * @param codigoMarcaEbel The codigoMarcaEbel to set.
     */
    public void setCodigoMarcaEbel(String codigoMarcaEbel) {
        this.codigoMarcaEbel = codigoMarcaEbel;
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
     * @return Returns the codigoProgramaFidelizacion.
     */
    public String getCodigoProgramaFidelizacion() {
        return codigoProgramaFidelizacion;
    }

    /**
     * @param codigoProgramaFidelizacion The codigoProgramaFidelizacion to set.
     */
    public void setCodigoProgramaFidelizacion(String codigoProgramaFidelizacion) {
        this.codigoProgramaFidelizacion = codigoProgramaFidelizacion;
    }

    /**
     * @return Returns the codigoStickerActual.
     */
    public String getCodigoStickerActual() {
        return codigoStickerActual;
    }

    /**
     * @param codigoStickerActual The codigoStickerActual to set.
     */
    public void setCodigoStickerActual(String codigoStickerActual) {
        this.codigoStickerActual = codigoStickerActual;
    }

    /**
     * @return Returns the fechaProceso.
     */
    public Date getFechaProceso() {
        return fechaProceso;
    }

    /**
     * @param fechaProceso The fechaProceso to set.
     */
    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    /**
     * @return Returns the periodoProceso.
     */
    public String getPeriodoProceso() {
        return periodoProceso;
    }

    /**
     * @param periodoProceso The periodoProceso to set.
     */
    public void setPeriodoProceso(String periodoProceso) {
        this.periodoProceso = periodoProceso;
    }

    /**
     * @return Returns the procesoActual.
     */
    public String getProcesoActual() {
        return procesoActual;
    }

    /**
     * @param procesoActual The procesoActual to set.
     */
    public void setProcesoActual(String procesoActual) {
        this.procesoActual = procesoActual;
    }

    /**
     * @return Returns the rutaArchivoPrivilege.
     */
    public String getRutaArchivoPrivilege() {
        return rutaArchivoPrivilege;
    }

    /**
     * @param rutaArchivoPrivilege The rutaArchivoPrivilege to set.
     */
    public void setRutaArchivoPrivilege(String rutaArchivoPrivilege) {
        this.rutaArchivoPrivilege = rutaArchivoPrivilege;
    }

    /**
     * @return Returns the valorFactor.
     */
    public float getValorFactor() {
        return valorFactor;
    }

    /**
     * @param valorFactor The valorFactor to set.
     */
    public void setValorFactor(float valorFactor) {
        this.valorFactor = valorFactor;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ControlFacturacion)) {
            return false;
        }
        ControlFacturacion rhs = (ControlFacturacion) object;
        return new EqualsBuilder().append(this.rutaArchivoPrivilege,
                rhs.rutaArchivoPrivilege).append(this.procesoActual,
                rhs.procesoActual).append(this.codigoPais, rhs.codigoPais)
                .append(this.codigoProgramaFidelizacion,
                        rhs.codigoProgramaFidelizacion).append(
                        this.codigoStickerActual, rhs.codigoStickerActual)
                .append(this.valorFactor, rhs.valorFactor).append(
                        this.auditInfo, rhs.auditInfo).append(
                        this.codigoMarcaEbel, rhs.codigoMarcaEbel).append(
                        this.codigoAlmacenVentaDirecta, rhs.codigoAlmacenVentaDirecta).append(
                        this.fechaProceso, rhs.fechaProceso).append(
                        this.codigoCanalVentaDirecta, rhs.codigoCanalVentaDirecta).append(
                        this.periodoProceso, rhs.periodoProceso).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1423335539, -109549301).append(
                this.rutaArchivoPrivilege).append(this.procesoActual).append(
                this.codigoPais).append(this.codigoProgramaFidelizacion)
                .append(this.codigoStickerActual).append(this.valorFactor)
                .append(this.auditInfo).append(this.codigoMarcaEbel).append(
                        this.codigoAlmacenVentaDirecta).append(this.fechaProceso).append(
                        this.codigoCanalVentaDirecta).append(this.periodoProceso)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoPais", this.codigoPais).append("codigoAlmacenVentaDirecta",
                        this.codigoAlmacenVentaDirecta).append("valorFactor",
                        this.valorFactor).append("codigoStickerActual",
                        this.codigoStickerActual).append("auditInfo",
                        this.auditInfo).append("periodoProceso",
                        this.periodoProceso).append(
                        "codigoProgramaFidelizacion",
                        this.codigoProgramaFidelizacion).append(
                        "rutaArchivoPrivilege", this.rutaArchivoPrivilege)
                .append("codigoMarcaEbel", this.codigoMarcaEbel).append(
                        "codigoCanalVentaDirecta", this.codigoCanalVentaDirecta).append("fechaProceso",
                        this.fechaProceso).append("procesoActual",
                        this.procesoActual).toString();
    }

}
