/*
 * Created on 08/10/2005 02:09:25 PM
 *
 * biz.belcorp.ssicc.model.Producto
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * POJO que almacena la informacion de los productos Privilege.
 * <p>
 * <a href="Producto.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class Producto extends AuditableBaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2806656200873556048L;

	private String codigoPais;

    private String codigo;

    private String descripcion;

    private Boolean indicadorGeneracionStickers;

    private String estado;
    
    private int puntajeSticker;

    private ProductoPK primaryKey;

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
     * @return Returns the indicadorGeneracionStickers.
     */
    public Boolean getIndicadorGeneracionStickers() {
        return indicadorGeneracionStickers;
    }

    /**
     * @param indicadorGeneracionStickers
     *            The indicadorGeneracionStickers to set.
     */
    public void setIndicadorGeneracionStickers(
            Boolean indicadorGeneracionStickers) {
        this.indicadorGeneracionStickers = indicadorGeneracionStickers;
    }

    /**
     * @return Returns the puntajeSticker.
     */
    public int getPuntajeSticker() {
        return puntajeSticker;
    }

    /**
     * @param puntajeSticker The puntajeSticker to set.
     */
    public void setPuntajeSticker(int puntajeSticker) {
        this.puntajeSticker = puntajeSticker;
    }

    /**
     * @return Returns the primaryKey.
     */
    public ProductoPK getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param primaryKey
     *            The primaryKey to set.
     */
    public void setPrimaryKey(ProductoPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto rhs = (Producto) object;
        return new EqualsBuilder().append(this.primaryKey, rhs.primaryKey)
                .append(this.codigoPais, rhs.codigoPais).append(
                        this.indicadorGeneracionStickers,
                        rhs.indicadorGeneracionStickers).append(
                        this.descripcion, rhs.descripcion).append(
                        this.puntajeSticker, rhs.puntajeSticker).append(
                        this.auditInfo, rhs.auditInfo).append(this.estado,
                        rhs.estado).append(this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-180655219, -1844531407).append(
                this.primaryKey).append(this.codigoPais).append(
                this.indicadorGeneracionStickers).append(this.descripcion)
                .append(this.puntajeSticker).append(this.auditInfo).append(
                        this.estado).append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoPais", this.codigoPais).append("estado",
                        this.estado).append("indicadorGeneracionStickers",
                        this.indicadorGeneracionStickers).append("auditInfo",
                        this.auditInfo).append("codigo", this.codigo).append(
                        "primaryKey", this.primaryKey).append("puntajeSticker",
                        this.puntajeSticker).append("descripcion",
                        this.descripcion).toString();
    }
}