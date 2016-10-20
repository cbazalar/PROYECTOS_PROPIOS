package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Premio.java.html"> <ci>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */

public class Premio extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1601335001611840370L;

	private String codigoPais;

	private String numeroTarjeta;

	private int correlativo;

	private String codigoProducto;

	private int cantidad;

	private int puntaje;

	private int puntajeTotal;

	private String campañaSolicitud;

	private String campañaDespacho;

	private String status;

	private String indicador;

	private Date fecha;
    
    private String indicadorCarnet;

	/**
	 * 
	 * @uml.property name="primaryKey"
	 * @uml.associationEnd inverse="roleLevel:biz.belcorp.ssicc.model.PremioPK"
	 *                     multiplicity="(0 1)"
	 */
	private PremioPK primaryKey;

	/**
	 * 
	 * @uml.property name="primaryKey"
	 */
	public PremioPK getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * 
	 * @uml.property name="primaryKey"
	 */
	public void setPrimaryKey(PremioPK primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return Returns the campañaDespacho.
	 */
	public String getCampañaDespacho() {
		return campañaDespacho;
	}

	/**
	 * @param campañaDespacho
	 *            The campañaDespacho to set.
	 */
	public void setCampañaDespacho(String campañaDespacho) {
		this.campañaDespacho = campañaDespacho;
	}

	/**
	 * @return Returns the campañaSolicitud.
	 */
	public String getCampañaSolicitud() {
		return campañaSolicitud;
	}

	/**
	 * @param campañaSolicitud
	 *            The campañaSolicitud to set.
	 */
	public void setCampañaSolicitud(String campañaSolicitud) {
		this.campañaSolicitud = campañaSolicitud;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Premio)) {
			return false;
		}
		Premio rhs = (Premio) object;
		return new EqualsBuilder().append(this.primaryKey, rhs.primaryKey)
				.append(this.codigoPais, rhs.codigoPais).append(
						this.numeroTarjeta, rhs.numeroTarjeta).append(
						this.auditInfo, rhs.auditInfo).append(this.status,
						rhs.status).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-880476083, 585891547).append(
				this.primaryKey).append(this.codigoPais).append(
				this.numeroTarjeta).append(this.auditInfo).append(this.status)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("status", this.status).append("codigoPais",
						this.codigoPais).append("auditInfo", this.auditInfo)
				.append("numeroTarjeta", this.numeroTarjeta).append(
						"primaryKey", this.primaryKey).toString();
	}

	/**
	 * @return Returns the cantidad.
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad
	 *            The cantidad to set.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto
	 *            The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
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
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String estado) {
		this.status = estado;
	}

	/**
	 * @return Returns the fecha.
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            The fecha to set.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return Returns the indicador.
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador
	 *            The indicador to set.
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return Returns the numeroTarjeta.
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * @param numeroTarjeta
	 *            The numeroTarjeta to set.
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * @return Returns the puntaje.
	 */
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje
	 *            The puntaje to set.
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * @return Returns the puntajeTotal.
	 */
	public int getPuntajeTotal() {
		return puntajeTotal;
	}

	/**
	 * @param puntajeTotal
	 *            The puntajeTotal to set.
	 */
	public void setPuntajeTotal(int puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

    /**
     * @return Returns the indicadorCarnet.
     */
    public String getIndicadorCarnet() {
        return indicadorCarnet;
    }

    /**
     * @param indicadorCarnet The indicadorCarnet to set.
     */
    public void setIndicadorCarnet(String indicadorCarnet) {
        this.indicadorCarnet = indicadorCarnet;
    }

}