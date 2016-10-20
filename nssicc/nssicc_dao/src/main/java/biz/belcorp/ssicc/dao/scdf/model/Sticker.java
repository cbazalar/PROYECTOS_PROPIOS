/*
 * Created on 09/02/2005 03:55:37 PM biz.belcorp.ssicc.model.Sticker
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class descripcion here.
 * <p>
 * <a href="Sticker.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 * @struts.form include-all="false" extends="BaseEditForm"
 * @hibernate.class table="BAS_STICK" dynamic-update="true"
 */

public class Sticker extends AuditableBaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6176052323633203295L;

	private String codigoPais;
	
	private String codigo;
	
	private String codigoProducto;
	
	private String codigoConsultora;
	
	private String codigoSubgerencia;
	
	private String codigoRegion;
	
	private String codigoZona;
	
	private String codigoTerritorio;
	
	private String campaña;
	
	private Date fecha;
	
	private float valorUnitario;
	
	private float factor;
	
	private int puntaje;
	
	private int puntajeFijo;
	
	private int numeroImpresiones;
	
	private String condicion;
	
	private String numeroFactura; 
	
	private String statusAnulado;
	
	
    /**
	 * @return Returns the statusAnulado.
	 */
	public String getStatusAnulado() {
		return statusAnulado;
	}

	/**
	 * @param statusAnulado The statusAnulado to set.
	 */
	public void setStatusAnulado(String statusAnulado) {
		this.statusAnulado = statusAnulado;
	}

	/**
	 * @return Returns the campaa.
	 */
	public String getCampaña() {
		return campaña;
	}

	/**
	 * @param campaa The campaa to set.
	 */
	public void setCampaña(String campaña) {
		this.campaña = campaña;
	}

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the codigoConsultora.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora The codigoConsultora to set.
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
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
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the codigoSubgerencia.
	 */
	public String getCodigoSubgerencia() {
		return codigoSubgerencia;
	}

	/**
	 * @param codigoSubgerencia The codigoSubgerencia to set.
	 */
	public void setCodigoSubgerencia(String codigoSubgerencia) {
		this.codigoSubgerencia = codigoSubgerencia;
	}

	/**
	 * @return Returns the codigoTerritorio.
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}

	/**
	 * @param codigoTerritorio The codigoTerritorio to set.
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}

	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the condicion.
	 */
	public String getCondicion() {
		return condicion;
	}

	/**
	 * @param condicion The condicion to set.
	 */
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	/**
	 * @return Returns the factor.
	 */
	public float getFactor() {
		return factor;
	}

	/**
	 * @param factor The factor to set.
	 */
	public void setFactor(float factor) {
		this.factor = factor;
	}

	/**
	 * @return Returns the fecha.
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha The fecha to set.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return Returns the numeroFactura.
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * @param numeroFactura The numeroFactura to set.
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	/**
	 * @return Returns the numeroImpresiones.
	 */
	public int getNumeroImpresiones() {
		return numeroImpresiones;
	}

	/**
	 * @param numeroImpresiones The numeroImpresiones to set.
	 */
	public void setNumeroImpresiones(int numeroImpresiones) {
		this.numeroImpresiones = numeroImpresiones;
	}

	/**
	 * @return Returns the puntaje.
	 */
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje The puntaje to set.
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * @return Returns the puntajeFijo.
	 */
	public int getPuntajeFijo() {
		return puntajeFijo;
	}

	/**
	 * @param puntajeFijo The puntajeFijo to set.
	 */
	public void setPuntajeFijo(int puntajeFijo) {
		this.puntajeFijo = puntajeFijo;
	}

	/**
	 * @return Returns the valorUnitario.
	 */
	public float getValorUnitario() {
		return valorUnitario;
	}

	/**
	 * @param valorUnitario The valorUnitario to set.
	 */
	public void setValorUnitario(float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Sticker)) {
			return false;
		}
		Sticker rhs = (Sticker) object;
		return new EqualsBuilder().append(this.campaña, rhs.campaña).append(
				this.codigoProducto, rhs.codigoProducto).append(
				this.codigoSubgerencia, rhs.codigoSubgerencia).append(
				this.codigoConsultora, rhs.codigoConsultora).append(
				this.numeroFactura, rhs.numeroFactura).append(this.auditInfo,
				rhs.auditInfo).append(this.puntajeFijo, rhs.puntajeFijo)
				.append(this.valorUnitario, rhs.valorUnitario).append(
						this.codigoTerritorio, rhs.codigoTerritorio).append(
						this.factor, rhs.factor).append(this.fecha, rhs.fecha)
				.append(this.statusAnulado, rhs.statusAnulado).append(
						this.codigoPais, rhs.codigoPais).append(
						this.codigoRegion, rhs.codigoRegion).append(
						this.condicion, rhs.condicion).append(
						this.numeroImpresiones, rhs.numeroImpresiones).append(
						this.puntaje, rhs.puntaje).append(this.codigo,
						rhs.codigo).append(this.codigoZona, rhs.codigoZona)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(200285181, -298770781).append(this.campaña)
				.append(this.codigoProducto).append(this.codigoSubgerencia)
				.append(this.codigoConsultora).append(this.numeroFactura)
				.append(this.auditInfo).append(this.puntajeFijo).append(
						this.valorUnitario).append(this.codigoTerritorio)
				.append(this.factor).append(this.fecha).append(
						this.statusAnulado).append(this.codigoPais).append(
						this.codigoRegion).append(this.condicion).append(
						this.numeroImpresiones).append(this.puntaje).append(
						this.codigo).append(this.codigoZona).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoRegion", this.codigoRegion).append("codigoPais",
						this.codigoPais).append("numeroFactura",
						this.numeroFactura).append("fecha", this.fecha).append(
						"puntajeFijo", this.puntajeFijo).append("campaña",
						this.campaña).append("factor", this.factor).append(
						"numeroImpresiones", this.numeroImpresiones).append(
						"codigoTerritorio", this.codigoTerritorio).append(
						"auditInfo", this.auditInfo).append("codigoConsultora",
						this.codigoConsultora).append("codigoProducto",
						this.codigoProducto).append("puntaje", this.puntaje)
				.append("valorUnitario", this.valorUnitario).append(
						"codigoSubgerencia", this.codigoSubgerencia).append(
						"codigo", this.codigo).append("condicion",
						this.condicion).append("statusAnulado",
						this.statusAnulado).append("codigoZona",
						this.codigoZona).toString();
	}
}