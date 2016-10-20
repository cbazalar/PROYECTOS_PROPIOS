package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoArticuloLote extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Integer indicadorDespacho;
	private java.math.BigDecimal precioPublico;
	private Integer numeroUnidades;
	private String codigoVentaFicticio;
	private Long oidProducto;
	private Long oidLotePremioArticulo;
	private Integer indicadorCentroServicio;
	private Long oidCentroServGarantia;
	private Integer numeroMesesGarantia;
	private String observaciones;
	private String indicadorTipoEntrega;
	private Long oidCentroServEntrega;
	
	private String codigoSAP;
	private String codigoCentroServGarantia;
	private String codigoCentroServEntrega;
	
	private String numeroNivel;
	private String descripcionLote;
	private String numeroPremio;
	private String descripcionProducto;
	
	public boolean eliminado;
	
	private Integer indicadorPremiosWeb;
	

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public ConcursoArticuloLote() {
		this.eliminado = false;
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConcursoArticuloLote [oid=" + oid + ", indicadorDespacho="
				+ indicadorDespacho + ", precioPublico=" + precioPublico
				+ ", numeroUnidades=" + numeroUnidades
				+ ", codigoVentaFicticio=" + codigoVentaFicticio
				+ ", oidProducto=" + oidProducto + ", oidLotePremioArticulo="
				+ oidLotePremioArticulo + ", indicadorCentroServicio="
				+ indicadorCentroServicio + ", oidCentroServGarantia="
				+ oidCentroServGarantia + ", numeroMesesGarantia="
				+ numeroMesesGarantia + ", observaciones=" + observaciones
				+ ", indicadorTipoEntrega=" + indicadorTipoEntrega
				+ ", oidCentroServEntrega=" + oidCentroServEntrega
				+ ", codigoSAP=" + codigoSAP + ", codigoCentroServGarantia="
				+ codigoCentroServGarantia + ", codigoCentroServEntrega="
				+ codigoCentroServEntrega + ", numeroNivel=" + numeroNivel
				+ ", descripcionLote=" + descripcionLote + ", numeroPremio="
				+ numeroPremio + ", descripcionProducto=" + descripcionProducto
				+ ", eliminado=" + eliminado 
				+ ", indicadorPremiosWeb=" + indicadorPremiosWeb 
				+ "]";
	}

	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return the indicadorDespacho
	 */
	public Integer getIndicadorDespacho() {
		return indicadorDespacho;
	}

	/**
	 * @param indicadorDespacho the indicadorDespacho to set
	 */
	public void setIndicadorDespacho(Integer indicadorDespacho) {
		this.indicadorDespacho = indicadorDespacho;
	}

	/**
	 * @return the precioPublico
	 */
	public java.math.BigDecimal getPrecioPublico() {
		return precioPublico;
	}

	/**
	 * @param precioPublico the precioPublico to set
	 */
	public void setPrecioPublico(java.math.BigDecimal precioPublico) {
		this.precioPublico = precioPublico;
	}

	/**
	 * @return the numeroUnidades
	 */
	public Integer getNumeroUnidades() {
		return numeroUnidades;
	}

	/**
	 * @param numeroUnidades the numeroUnidades to set
	 */
	public void setNumeroUnidades(Integer numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}

	/**
	 * @return the codigoVentaFicticio
	 */
	public String getCodigoVentaFicticio() {
		return codigoVentaFicticio;
	}

	/**
	 * @param codigoVentaFicticio the codigoVentaFicticio to set
	 */
	public void setCodigoVentaFicticio(String codigoVentaFicticio) {
		this.codigoVentaFicticio = codigoVentaFicticio;
	}

	/**
	 * @return the oidProducto
	 */
	public Long getOidProducto() {
		return oidProducto;
	}

	/**
	 * @param oidProducto the oidProducto to set
	 */
	public void setOidProducto(Long oidProducto) {
		this.oidProducto = oidProducto;
	}

	/**
	 * @return the oidLotePremioArticulo
	 */
	public Long getOidLotePremioArticulo() {
		return oidLotePremioArticulo;
	}

	/**
	 * @param oidLotePremioArticulo the oidLotePremioArticulo to set
	 */
	public void setOidLotePremioArticulo(Long oidLotePremioArticulo) {
		this.oidLotePremioArticulo = oidLotePremioArticulo;
	}

	/**
	 * @return the indicadorCentroServicio
	 */
	public Integer getIndicadorCentroServicio() {
		return indicadorCentroServicio;
	}

	/**
	 * @param indicadorCentroServicio the indicadorCentroServicio to set
	 */
	public void setIndicadorCentroServicio(Integer indicadorCentroServicio) {
		this.indicadorCentroServicio = indicadorCentroServicio;
	}

	/**
	 * @return the oidCentroServGarantia
	 */
	public Long getOidCentroServGarantia() {
		return oidCentroServGarantia;
	}

	/**
	 * @param oidCentroServGarantia the oidCentroServGarantia to set
	 */
	public void setOidCentroServGarantia(Long oidCentroServGarantia) {
		this.oidCentroServGarantia = oidCentroServGarantia;
	}

	/**
	 * @return the numeroMesesGarantia
	 */
	public Integer getNumeroMesesGarantia() {
		return numeroMesesGarantia;
	}

	/**
	 * @param numeroMesesGarantia the numeroMesesGarantia to set
	 */
	public void setNumeroMesesGarantia(Integer numeroMesesGarantia) {
		this.numeroMesesGarantia = numeroMesesGarantia;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the indicadorTipoEntrega
	 */
	public String getIndicadorTipoEntrega() {
		return indicadorTipoEntrega;
	}

	/**
	 * @param indicadorTipoEntrega the indicadorTipoEntrega to set
	 */
	public void setIndicadorTipoEntrega(String indicadorTipoEntrega) {
		this.indicadorTipoEntrega = indicadorTipoEntrega;
	}

	/**
	 * @return the oidCentroServEntrega
	 */
	public Long getOidCentroServEntrega() {
		return oidCentroServEntrega;
	}

	/**
	 * @param oidCentroServEntrega the oidCentroServEntrega to set
	 */
	public void setOidCentroServEntrega(Long oidCentroServEntrega) {
		this.oidCentroServEntrega = oidCentroServEntrega;
	}

	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/**
	 * @return the numeroNivel
	 */
	public String getNumeroNivel() {
		return numeroNivel;
	}

	/**
	 * @param numeroNivel the numeroNivel to set
	 */
	public void setNumeroNivel(String numeroNivel) {
		this.numeroNivel = numeroNivel;
	}

	/**
	 * @return the descripcionLote
	 */
	public String getDescripcionLote() {
		return descripcionLote;
	}

	/**
	 * @param descripcionLote the descripcionLote to set
	 */
	public void setDescripcionLote(String descripcionLote) {
		this.descripcionLote = descripcionLote;
	}

	/**
	 * @return the numeroPremio
	 */
	public String getNumeroPremio() {
		return numeroPremio;
	}

	/**
	 * @param numeroPremio the numeroPremio to set
	 */
	public void setNumeroPremio(String numeroPremio) {
		this.numeroPremio = numeroPremio;
	}

	/**
	 * @return the codigoCentroServGarantia
	 */
	public String getCodigoCentroServGarantia() {
		return codigoCentroServGarantia;
	}

	/**
	 * @param codigoCentroServGarantia the codigoCentroServGarantia to set
	 */
	public void setCodigoCentroServGarantia(String codigoCentroServGarantia) {
		this.codigoCentroServGarantia = codigoCentroServGarantia;
	}

	/**
	 * @return the codigoCentroServEntrega
	 */
	public String getCodigoCentroServEntrega() {
		return codigoCentroServEntrega;
	}

	/**
	 * @param codigoCentroServEntrega the codigoCentroServEntrega to set
	 */
	public void setCodigoCentroServEntrega(String codigoCentroServEntrega) {
		this.codigoCentroServEntrega = codigoCentroServEntrega;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public Integer getIndicadorPremiosWeb() {
		return indicadorPremiosWeb;
	}

	public void setIndicadorPremiosWeb(Integer indicadorPremiosWeb) {
		this.indicadorPremiosWeb = indicadorPremiosWeb;
	}
	
	

}
