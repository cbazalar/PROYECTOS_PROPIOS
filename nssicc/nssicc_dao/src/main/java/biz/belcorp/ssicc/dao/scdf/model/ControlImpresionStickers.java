/*
 * Created on 22/05/2006 06:42:19 PM
 * biz.belcorp.ssicc.scdf.model.ControlImpresionStickers
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlImpresionStickers.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class ControlImpresionStickers extends AuditableBaseObject implements
        Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4819228170460629260L;

	private String codigoPais;

    private String nombreArchivo;

    private String directorioArchivo;

    private String plantillaSticker;

    private Boolean indicadorEnvioImpresora;

    private String plantillaSeparador;

    private Boolean indicadorSeparador;

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
     * @return Returns the directorioArchivo.
     */
    public String getDirectorioArchivo() {
        return directorioArchivo;
    }

    /**
     * @param directorioArchivo
     *            The directorioArchivo to set.
     */
    public void setDirectorioArchivo(String directorioArchivo) {
        this.directorioArchivo = directorioArchivo;
    }

    /**
     * @return Returns the indicadorEnvioImpresora.
     */
    public Boolean getIndicadorEnvioImpresora() {
        return indicadorEnvioImpresora;
    }

    /**
     * @param indicadorEnvioImpresora
     *            The indicadorEnvioImpresora to set.
     */
    public void setIndicadorEnvioImpresora(Boolean indicadorEnvioImpresora) {
        this.indicadorEnvioImpresora = indicadorEnvioImpresora;
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
     * @return Returns the plantillaSticker.
     */
    public String getPlantillaSticker() {
        return plantillaSticker;
    }

    /**
     * @param plantillaSticker
     *            The plantillaSticker to set.
     */
    public void setPlantillaSticker(String plantillaSticker) {
        this.plantillaSticker = plantillaSticker;
    }

    /**
     * @return Returns the indicadorSeparador.
     */
    public Boolean getIndicadorSeparador() {
        return indicadorSeparador;
    }

    /**
     * @param indicadorSeparador
     *            The indicadorSeparador to set.
     */
    public void setIndicadorSeparador(Boolean indicadorSeparador) {
        this.indicadorSeparador = indicadorSeparador;
    }

    /**
     * @return Returns the plantillaSeparador.
     */
    public String getPlantillaSeparador() {
        return plantillaSeparador;
    }

    /**
     * @param plantillaSeparador
     *            The plantillaSeparador to set.
     */
    public void setPlantillaSeparador(String plantillaSeparador) {
        this.plantillaSeparador = plantillaSeparador;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ControlImpresionStickers)) {
            return false;
        }
        ControlImpresionStickers rhs = (ControlImpresionStickers) object;
        return new EqualsBuilder().append(this.plantillaSeparador,
                rhs.plantillaSeparador).append(this.codigoPais, rhs.codigoPais)
                .append(this.indicadorSeparador, rhs.indicadorSeparador)
                .append(this.indicadorEnvioImpresora,
                        rhs.indicadorEnvioImpresora).append(this.auditInfo,
                        rhs.auditInfo).append(this.plantillaSticker,
                        rhs.plantillaSticker).append(this.nombreArchivo,
                        rhs.nombreArchivo).append(this.directorioArchivo,
                        rhs.directorioArchivo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1626967641, -1891506779).append(
                this.plantillaSeparador).append(this.codigoPais).append(
                this.indicadorSeparador).append(this.indicadorEnvioImpresora)
                .append(this.auditInfo).append(this.plantillaSticker).append(
                        this.nombreArchivo).append(this.directorioArchivo)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("indicadorSeparador", this.indicadorSeparador)
                .append("codigoPais", this.codigoPais)
                .append("plantillaSticker", this.plantillaSticker)
                .append("directorioArchivo", this.directorioArchivo)
                .append("indicadorEnvioImpresora", this.indicadorEnvioImpresora)
                .append("plantillaSeparador", this.plantillaSeparador).append(
                        "auditInfo", this.auditInfo).append("nombreArchivo",
                        this.nombreArchivo).toString();
    }

}
