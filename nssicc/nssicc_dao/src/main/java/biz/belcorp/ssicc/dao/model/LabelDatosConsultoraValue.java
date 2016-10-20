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
public class LabelDatosConsultoraValue extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1627427639154234954L;
	private String codigoConsultora = null;
    private String nombreConsultora = null;
    private String telefono = null;
    private String estatus = null;
    private String codEtatus=null;
    private String primerPedido = null;
    private String ultimoPedido = null;
    private String region = null;
    private String zona = null;
    private String fechaFacturacion = null;
    private String bloqueado =null;
    private String periodoFacturacion=null;
    private String numeroPedidosRegistrado=null;
    private String totalUnidades=null;
    private String totalTotal=null;
    private String codPais=null;
    private String codRegion=null;
    private String codZona=null;
    private String numItems=null;
    
    private String numLote = null;
    
    private String estadoRegistro;
    
    private String indicador;
    
    private String indEsta;
    
    private String indicadorActiva;
    private String estadoConsultora;
    private String indicadorBloqueo;
    
	/**
	 * @return Returns the numItems.
	 */
	public String getNumItems() {
		return numItems;
	}
	/**
	 * @param numItems The numItems to set.
	 */
	public void setNumItems(String numItems) {
		this.numItems = numItems;
	}
	/**
	 * @return Returns the codEtatus.
	 */
	public String getCodEtatus() {
		return codEtatus;
	}
	/**
	 * @param codEtatus The codEtatus to set.
	 */
	public void setCodEtatus(String codEtatus) {
		this.codEtatus = codEtatus;
	}
	/**
	 * @return Returns the codRegion.
	 */
	public String getCodRegion() {
		return codRegion;
	}
	/**
	 * @param codRegion The codRegion to set.
	 */
	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
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
	/**
	 * @return Returns the totalTotal.
	 */
	public String getTotalTotal() {
		return totalTotal;
	}
	/**
	 * @param totalTotal The totalTotal to set.
	 */
	public void setTotalTotal(String totalTotal) {
		this.totalTotal = totalTotal;
	}
	/**
	 * @return Returns the totalUnidades.
	 */
	public String getTotalUnidades() {
		return totalUnidades;
	}
	/**
	 * @param totalUnidades The totalUnidades to set.
	 */
	public void setTotalUnidades(String totalUnidades) {
		this.totalUnidades = totalUnidades;
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
	 * @return Returns the estatus.
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus The estatus to set.
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return Returns the fechaFacturacion.
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion The fechaFacturacion to set.
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return Returns the nombreConsultora.
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	/**
	 * @param nombreConsultora The nombreConsultora to set.
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}
	/**
	 * @return Returns the primerPedido.
	 */
	public String getPrimerPedido() {
		return primerPedido;
	}
	/**
	 * @param primerPedido The primerPedido to set.
	 */
	public void setPrimerPedido(String primerPedido) {
		this.primerPedido = primerPedido;
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
	 * @return Returns the telefono.
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono The telefono to set.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return Returns the ultimoPedido.
	 */
	public String getUltimoPedido() {
		return ultimoPedido;
	}
	/**
	 * @param ultimoPedido The ultimoPedido to set.
	 */
	public void setUltimoPedido(String ultimoPedido) {
		this.ultimoPedido = ultimoPedido;
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
	public LabelDatosConsultoraValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabelDatosConsultoraValue(String codigoConsultora, String nombreConsultora, String telefono, String estatus, String primerPedido, String ultimoPedido, String region, String zona, String fechaFacturacion) {
		super();
		// TODO Auto-generated constructor stub
		this.codigoConsultora = codigoConsultora;
		this.nombreConsultora = nombreConsultora;
		this.telefono = telefono;
		this.estatus = estatus;
		this.primerPedido = primerPedido;
		this.ultimoPedido = ultimoPedido;
		this.region = region;
		this.zona = zona;
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return Returns the bloqueado.
	 */
	public String getBloqueado() {
		return bloqueado;
	}
	/**
	 * @param bloqueado The bloqueado to set.
	 */
	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}
	/**
	 * @return Returns the numeroPedidosRegistrado.
	 */
	public String getNumeroPedidosRegistrado() {
		return numeroPedidosRegistrado;
	}
	/**
	 * @param numeroPedidosRegistrado The numeroPedidosRegistrado to set.
	 */
	public void setNumeroPedidosRegistrado(String numeroPedidosRegistrado) {
		this.numeroPedidosRegistrado = numeroPedidosRegistrado;
	}
	/**
	 * @return Returns the periodoFacturacion.
	 */
	public String getPeriodoFacturacion() {
		return periodoFacturacion;
	}
	/**
	 * @param periodoFacturacion The periodoFacturacion to set.
	 */
	public void setPeriodoFacturacion(String periodoFacturacion) {
		this.periodoFacturacion = periodoFacturacion;
	}
	/**
	 * @return Returns the codPais.
	 */
	public String getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais The codPais to set.
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LabelDatosConsultoraValue)) {
			return false;
		}
		LabelDatosConsultoraValue rhs = (LabelDatosConsultoraValue) object;
		return new EqualsBuilder().append(
				this.fechaFacturacion, rhs.fechaFacturacion).append(
				this.totalTotal, rhs.totalTotal).append(this.estatus,
				rhs.estatus).append(this.numItems, rhs.numItems).append(
				this.codRegion, rhs.codRegion).append(this.codigoConsultora,
				rhs.codigoConsultora).append(this.primerPedido,
				rhs.primerPedido).append(this.codPais, rhs.codPais).append(
				this.codEtatus, rhs.codEtatus).append(this.totalUnidades,
				rhs.totalUnidades).append(this.region, rhs.region).append(
				this.auditInfo, rhs.auditInfo).append(this.ultimoPedido,
				rhs.ultimoPedido).append(this.numeroPedidosRegistrado,
				rhs.numeroPedidosRegistrado).append(this.periodoFacturacion,
				rhs.periodoFacturacion).append(this.bloqueado, rhs.bloqueado)
				.append(this.zona, rhs.zona).append(this.codZona, rhs.codZona)
				.append(this.nombreConsultora, rhs.nombreConsultora).append(
						this.telefono, rhs.telefono).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1207529343, 1627144477).append(this.fechaFacturacion).append(
				this.totalTotal).append(this.estatus).append(this.numItems)
				.append(this.codRegion).append(this.codigoConsultora).append(
						this.primerPedido).append(this.codPais).append(
						this.codEtatus).append(this.totalUnidades).append(
						this.region).append(this.auditInfo).append(
						this.ultimoPedido).append(this.numeroPedidosRegistrado)
				.append(this.periodoFacturacion).append(this.bloqueado).append(
						this.zona).append(this.codZona).append(
						this.nombreConsultora).append(this.telefono)
				.toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("nombreConsultora", this.nombreConsultora)
				.append("fechaFacturacion", this.fechaFacturacion)
				.append("zona", this.zona)
				.append("telefono", this.telefono)
				.append("codRegion", this.codRegion)
				.append("numItems", this.numItems)
				.append("auditInfo", this.auditInfo)
				.append("ultimoPedido", this.ultimoPedido)
				.append("totalTotal", this.totalTotal)
				.append("numeroPedidosRegistrado", this.numeroPedidosRegistrado)
				.append("primerPedido", this.primerPedido).append("estatus",
						this.estatus).append("totalUnidades",
						this.totalUnidades).append("codigoConsultora",
						this.codigoConsultora).append("bloqueado",
						this.bloqueado).append("region", this.region).append(
						"codZona", this.codZona).append("codEtatus",
						this.codEtatus).append("codPais", this.codPais).append(
						"periodoFacturacion", this.periodoFacturacion)
				.append(
						"numLote", this.numLote)		
				.toString();
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return Returns the numLotes.
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLotes The numLotes to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	/**
	 * @return Returns the indicador.
	 */
	public String getIndicador() {
		return indicador;
	}
	/**
	 * @param indicador The indicador to set.
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public String getIndEsta() {
		return indEsta;
	}
	public void setIndEsta(String indEsta) {
		this.indEsta = indEsta;
	}
	/**
	 * @return the indicadorActiva
	 */
	public String getIndicadorActiva() {
		return indicadorActiva;
	}
	/**
	 * @param indicadorActiva the indicadorActiva to set
	 */
	public void setIndicadorActiva(String indicadorActiva) {
		this.indicadorActiva = indicadorActiva;
	}
	/**
	 * @return the estadoConsultora
	 */
	public String getEstadoConsultora() {
		return estadoConsultora;
	}
	/**
	 * @param estadoConsultora the estadoConsultora to set
	 */
	public void setEstadoConsultora(String estadoConsultora) {
		this.estadoConsultora = estadoConsultora;
	}
	/**
	 * @return the indicadorBloqueo
	 */
	public String getIndicadorBloqueo() {
		return indicadorBloqueo;
	}
	/**
	 * @param indicadorBloqueo the indicadorBloqueo to set
	 */
	public void setIndicadorBloqueo(String indicadorBloqueo) {
		this.indicadorBloqueo = indicadorBloqueo;
	}
    
    

}