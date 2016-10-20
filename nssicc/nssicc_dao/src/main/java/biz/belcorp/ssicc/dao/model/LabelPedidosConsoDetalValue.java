package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * A simple JavaBean to represent label-value pairs. This is most commonly used
 * when constructing user interface elements which have a label to be displayed
 * to the user, and a corresponding value to be returned to the server. One
 * example is the <code>&lt;html:options&gt;</code> tag. <p/><p/>Note: this
 * class has a natural ordering that is inconsistent with equals.
 * </p>
 * 
 * @see org.apache.struts.util.LabelValueBean 
 */
public class LabelPedidosConsoDetalValue extends AuditableBaseObject implements Serializable {
	
	private static final long serialVersionUID = 5181925767101183848L;
	private String  codigoPais;
    private String  codigoPeriodo;
    private String  codigoCliente;
    private String  codigoVta;
    private String  tipoPosicion;
    private String  descProd;
    private String  valUnidadDemanda;
    private String  estado;
    private String  indCompl;
    private String  valUnidadCompletadas;
    private String  fechaSolicitud;
    private String  indErrRech;
    private String  numLote;
    private String  valorLimiteVent;
    private String  indErrorSSE;
    
    private String  region;
    private String  codZona;
    private String  zona;
    
    private String nombreCliente;
    private String telefonoCliente;    
    
	public LabelPedidosConsoDetalValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LabelPedidosConsoDetalValue)) {
			return false;
		}
		LabelPedidosConsoDetalValue rhs = (LabelPedidosConsoDetalValue) object;
		return new EqualsBuilder().append(
				this.indCompl, rhs.indCompl).append(this.auditInfo,
				rhs.auditInfo)
				.append(this.valorLimiteVent, rhs.valorLimiteVent).append(
						this.indErrorSSE, rhs.indErrorSSE).append(
						this.tipoPosicion, rhs.tipoPosicion).append(
						this.codigoCliente, rhs.codigoCliente).append(
						this.indErrRech, rhs.indErrRech).append(this.codigoVta,
						rhs.codigoVta).append(this.codigoPais, rhs.codigoPais)
				.append(this.numLote, rhs.numLote).append(
						this.valUnidadCompletadas, rhs.valUnidadCompletadas)
				.append(this.descProd, rhs.descProd).append(
						this.fechaSolicitud, rhs.fechaSolicitud).append(
						this.estado, rhs.estado).append(this.valUnidadDemanda,
						rhs.valUnidadDemanda).append(this.codigoPeriodo,
						rhs.codigoPeriodo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(177515449, 1511511823).append(this.indCompl).append(this.auditInfo)
				.append(this.valorLimiteVent).append(this.indErrorSSE).append(
						this.tipoPosicion).append(this.codigoCliente).append(
						this.indErrRech).append(this.codigoVta).append(
						this.codigoPais).append(this.numLote).append(
						this.valUnidadCompletadas).append(this.descProd)
				.append(this.fechaSolicitud).append(this.estado).append(
						this.valUnidadDemanda).append(this.codigoPeriodo)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("auditInfo", this.auditInfo)
				.toString();
	}

	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoVta.
	 */
	public String getCodigoVta() {
		return codigoVta;
	}

	/**
	 * @param codigoVta The codigoVta to set.
	 */
	public void setCodigoVta(String codigoVta) {
		this.codigoVta = codigoVta;
	}

	/**
	 * @return Returns the descProd.
	 */
	public String getDescProd() {
		return descProd;
	}

	/**
	 * @param descProd The descProd to set.
	 */
	public void setDescProd(String descProd) {
		this.descProd = descProd;
	}

	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the fechaSolicitud.
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud The fechaSolicitud to set.
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * @return Returns the indCompl.
	 */
	public String getIndCompl() {
		return indCompl;
	}

	/**
	 * @param indCompl The indCompl to set.
	 */
	public void setIndCompl(String indCompl) {
		this.indCompl = indCompl;
	}

	/**
	 * @return Returns the indErrorSSE.
	 */
	public String getIndErrorSSE() {
		return indErrorSSE;
	}

	/**
	 * @param indErrorSSE The indErrorSSE to set.
	 */
	public void setIndErrorSSE(String indErrorSSE) {
		this.indErrorSSE = indErrorSSE;
	}

	/**
	 * @return Returns the indErrRech.
	 */
	public String getIndErrRech() {
		return indErrRech;
	}

	/**
	 * @param indErrRech The indErrRech to set.
	 */
	public void setIndErrRech(String indErrRech) {
		this.indErrRech = indErrRech;
	}

	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}

	/**
	 * @param numLote The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

	/**
	 * @return Returns the tipoPosicion.
	 */
	public String getTipoPosicion() {
		return tipoPosicion;
	}

	/**
	 * @param tipoPosicion The tipoPosicion to set.
	 */
	public void setTipoPosicion(String tipoPosicion) {
		this.tipoPosicion = tipoPosicion;
	}

	/**
	 * @return Returns the valorLimiteVent.
	 */
	public String getValorLimiteVent() {
		return valorLimiteVent;
	}

	/**
	 * @param valorLimiteVent The valorLimiteVent to set.
	 */
	public void setValorLimiteVent(String valorLimiteVent) {
		this.valorLimiteVent = valorLimiteVent;
	}

	/**
	 * @return Returns the valUnidadCompletadas.
	 */
	public String getValUnidadCompletadas() {
		return valUnidadCompletadas;
	}

	/**
	 * @param valUnidadCompletadas The valUnidadCompletadas to set.
	 */
	public void setValUnidadCompletadas(String valUnidadCompletadas) {
		this.valUnidadCompletadas = valUnidadCompletadas;
	}

	/**
	 * @return Returns the valUnidadDemanda.
	 */
	public String getValUnidadDemanda() {
		return valUnidadDemanda;
	}

	/**
	 * @param valUnidadDemanda The valUnidadDemanda to set.
	 */
	public void setValUnidadDemanda(String valUnidadDemanda) {
		this.valUnidadDemanda = valUnidadDemanda;
	}

	/**
	 * @return Returns the region.
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region The region to set.
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return Returns the zona.
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona The zona to set.
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return Returns the nombreCliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente The nombreCliente to set.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return Returns the telefonoCliente.
	 */
	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	/**
	 * @param telefonoCliente The telefonoCliente to set.
	 */
	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	/**
	 * @return Returns the codZona.
	 */
	public String getCodZona() {
		return codZona;
	}

	/**
	 * @param codZona The codZona to set.
	 */
	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}
	
}