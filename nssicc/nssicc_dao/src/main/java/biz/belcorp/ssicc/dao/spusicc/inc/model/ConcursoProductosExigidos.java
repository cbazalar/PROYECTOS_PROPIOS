package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoProductosExigidos extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Integer unidadesExigidas;
	private java.math.BigDecimal montoExigido;
	private Integer puntosExigidos;
	private Long oidSuperGenerico;
	private Long oidGenerico;
	private Long oidTipoOferta;
	private Long oidPeriodoHasta;
	private Long oidUnidadNegocio;
	private Long oidProductos;
	private Long oidNegocio;
	private Long oidCicloVida;
	private Long oidPeriodoDesde;
	private Long oidProducto;
	private Long oidMarcaProducto;
	private Long oidproductoExigidoOriginal;
    private String indicadorAgrupacion;
        
    private String codigoSAP;
    private String descripcionTipoOferta;
	private String descripcionUnidadNegocio;
	private String descripcionSuperGenerico;
	private String descripcionCicloVida;
	private String descripcionNegocio;
	private String descripcionGenerico;
	private String descripcionMarcaProducto;
	private String descripcionAgrupacion;
	private String codigoPeriodoDesde;
	private String codigoPeriodoHasta;
	
	private String codigoSuperGenerico;
	private String codigoGenerico;
	
	private String descripcionProducto;
	
	private String oidDetalleOferta;
	private String descripcionCUV;
	private String index;
	
	public ConcursoProductosExigidos() {
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
		return "ConcursoProductosExigidos [oid=" + oid + ", unidadesExigidas="
				+ unidadesExigidas + ", montoExigido=" + montoExigido
				+ ", puntosExigidos=" + puntosExigidos + ", oidSuperGenerico="
				+ oidSuperGenerico + ", oidGenerico=" + oidGenerico
				+ ", oidTipoOferta=" + oidTipoOferta + ", oidPeriodoHasta="
				+ oidPeriodoHasta + ", oidUnidadNegocio=" + oidUnidadNegocio
				+ ", oidProductos=" + oidProductos + ", oidNegocio="
				+ oidNegocio + ", oidCicloVida=" + oidCicloVida
				+ ", oidPeriodoDesde=" + oidPeriodoDesde + ", oidProducto="
				+ oidProducto + ", oidMarcaProducto=" + oidMarcaProducto
				+ ", oidproductoExigidoOriginal=" + oidproductoExigidoOriginal
				+ ", indicadorAgrupacion=" + indicadorAgrupacion
				+ ", codigoSAP=" + codigoSAP + ", descripcionTipoOferta="
				+ descripcionTipoOferta + ", descripcionUnidadNegocio="
				+ descripcionUnidadNegocio + ", descripcionSuperGenerico="
				+ descripcionSuperGenerico + ", descripcionCicloVida="
				+ descripcionCicloVida + ", descripcionNegocio="
				+ descripcionNegocio + ", descripcionGenerico="
				+ descripcionGenerico + ", descripcionMarcaProducto="
				+ descripcionMarcaProducto + ", descripcionAgrupacion="
				+ descripcionAgrupacion + ", codigoPeriodoDesde="
				+ codigoPeriodoDesde + ", codigoPeriodoHasta="
				+ codigoPeriodoHasta + ", codigoSuperGenerico="
				+ codigoSuperGenerico + ", codigoGenerico=" + codigoGenerico
				+ ", descripcionProducto=" + descripcionProducto
				+ ", oidDetalleOferta=" + oidDetalleOferta
				+ ", descripcionCUV=" + descripcionCUV + ", index=" + index
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
	 * @return the unidadesExigidas
	 */
	public Integer getUnidadesExigidas() {
		return unidadesExigidas;
	}

	/**
	 * @param unidadesExigidas the unidadesExigidas to set
	 */
	public void setUnidadesExigidas(Integer unidadesExigidas) {
		this.unidadesExigidas = unidadesExigidas;
	}

	/**
	 * @return the montoExigido
	 */
	public java.math.BigDecimal getMontoExigido() {
		return montoExigido;
	}

	/**
	 * @param montoExigido the montoExigido to set
	 */
	public void setMontoExigido(java.math.BigDecimal montoExigido) {
		this.montoExigido = montoExigido;
	}

	/**
	 * @return the puntosExigidos
	 */
	public Integer getPuntosExigidos() {
		return puntosExigidos;
	}

	/**
	 * @param puntosExigidos the puntosExigidos to set
	 */
	public void setPuntosExigidos(Integer puntosExigidos) {
		this.puntosExigidos = puntosExigidos;
	}

	/**
	 * @return the oidSuperGenerico
	 */
	public Long getOidSuperGenerico() {
		return oidSuperGenerico;
	}

	/**
	 * @param oidSuperGenerico the oidSuperGenerico to set
	 */
	public void setOidSuperGenerico(Long oidSuperGenerico) {
		this.oidSuperGenerico = oidSuperGenerico;
	}

	/**
	 * @return the oidGenerico
	 */
	public Long getOidGenerico() {
		return oidGenerico;
	}

	/**
	 * @param oidGenerico the oidGenerico to set
	 */
	public void setOidGenerico(Long oidGenerico) {
		this.oidGenerico = oidGenerico;
	}

	/**
	 * @return the oidTipoOferta
	 */
	public Long getOidTipoOferta() {
		return oidTipoOferta;
	}

	/**
	 * @param oidTipoOferta the oidTipoOferta to set
	 */
	public void setOidTipoOferta(Long oidTipoOferta) {
		this.oidTipoOferta = oidTipoOferta;
	}

	/**
	 * @return the oidPeriodoHasta
	 */
	public Long getOidPeriodoHasta() {
		return oidPeriodoHasta;
	}

	/**
	 * @param oidPeriodoHasta the oidPeriodoHasta to set
	 */
	public void setOidPeriodoHasta(Long oidPeriodoHasta) {
		this.oidPeriodoHasta = oidPeriodoHasta;
	}

	/**
	 * @return the oidUnidadNegocio
	 */
	public Long getOidUnidadNegocio() {
		return oidUnidadNegocio;
	}

	/**
	 * @param oidUnidadNegocio the oidUnidadNegocio to set
	 */
	public void setOidUnidadNegocio(Long oidUnidadNegocio) {
		this.oidUnidadNegocio = oidUnidadNegocio;
	}

	/**
	 * @return the oidProductos
	 */
	public Long getOidProductos() {
		return oidProductos;
	}

	/**
	 * @param oidProductos the oidProductos to set
	 */
	public void setOidProductos(Long oidProductos) {
		this.oidProductos = oidProductos;
	}

	/**
	 * @return the oidNegocio
	 */
	public Long getOidNegocio() {
		return oidNegocio;
	}

	/**
	 * @param oidNegocio the oidNegocio to set
	 */
	public void setOidNegocio(Long oidNegocio) {
		this.oidNegocio = oidNegocio;
	}

	/**
	 * @return the oidCicloVida
	 */
	public Long getOidCicloVida() {
		return oidCicloVida;
	}

	/**
	 * @param oidCicloVida the oidCicloVida to set
	 */
	public void setOidCicloVida(Long oidCicloVida) {
		this.oidCicloVida = oidCicloVida;
	}

	/**
	 * @return the oidPeriodoDesde
	 */
	public Long getOidPeriodoDesde() {
		return oidPeriodoDesde;
	}

	/**
	 * @param oidPeriodoDesde the oidPeriodoDesde to set
	 */
	public void setOidPeriodoDesde(Long oidPeriodoDesde) {
		this.oidPeriodoDesde = oidPeriodoDesde;
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
	 * @return the oidMarcaProducto
	 */
	public Long getOidMarcaProducto() {
		return oidMarcaProducto;
	}

	/**
	 * @param oidMarcaProducto the oidMarcaProducto to set
	 */
	public void setOidMarcaProducto(Long oidMarcaProducto) {
		this.oidMarcaProducto = oidMarcaProducto;
	}

	/**
	 * @return the oidproductoExigidoOriginal
	 */
	public Long getOidproductoExigidoOriginal() {
		return oidproductoExigidoOriginal;
	}

	/**
	 * @param oidproductoExigidoOriginal the oidproductoExigidoOriginal to set
	 */
	public void setOidproductoExigidoOriginal(Long oidproductoExigidoOriginal) {
		this.oidproductoExigidoOriginal = oidproductoExigidoOriginal;
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
	 * @return the descripcionUnidadNegocio
	 */
	public String getDescripcionUnidadNegocio() {
		return descripcionUnidadNegocio;
	}

	/**
	 * @param descripcionUnidadNegocio the descripcionUnidadNegocio to set
	 */
	public void setDescripcionUnidadNegocio(String descripcionUnidadNegocio) {
		this.descripcionUnidadNegocio = descripcionUnidadNegocio;
	}

	/**
	 * @return the descripcionSuperGenerico
	 */
	public String getDescripcionSuperGenerico() {
		return descripcionSuperGenerico;
	}

	/**
	 * @param descripcionSuperGenerico the descripcionSuperGenerico to set
	 */
	public void setDescripcionSuperGenerico(String descripcionSuperGenerico) {
		this.descripcionSuperGenerico = descripcionSuperGenerico;
	}

	/**
	 * @return the descripcionCicloVida
	 */
	public String getDescripcionCicloVida() {
		return descripcionCicloVida;
	}

	/**
	 * @param descripcionCicloVida the descripcionCicloVida to set
	 */
	public void setDescripcionCicloVida(String descripcionCicloVida) {
		this.descripcionCicloVida = descripcionCicloVida;
	}

	/**
	 * @return the descripcionNegocio
	 */
	public String getDescripcionNegocio() {
		return descripcionNegocio;
	}

	/**
	 * @param descripcionNegocio the descripcionNegocio to set
	 */
	public void setDescripcionNegocio(String descripcionNegocio) {
		this.descripcionNegocio = descripcionNegocio;
	}

	/**
	 * @return the descripcionGenerico
	 */
	public String getDescripcionGenerico() {
		return descripcionGenerico;
	}

	/**
	 * @param descripcionGenerico the descripcionGenerico to set
	 */
	public void setDescripcionGenerico(String descripcionGenerico) {
		this.descripcionGenerico = descripcionGenerico;
	}

	/**
	 * @return the descripcionMarcaProducto
	 */
	public String getDescripcionMarcaProducto() {
		return descripcionMarcaProducto;
	}

	/**
	 * @param descripcionMarcaProducto the descripcionMarcaProducto to set
	 */
	public void setDescripcionMarcaProducto(String descripcionMarcaProducto) {
		this.descripcionMarcaProducto = descripcionMarcaProducto;
	}

	/**
	 * @return the codigoPeriodoDesde
	 */
	public String getCodigoPeriodoDesde() {
		return codigoPeriodoDesde;
	}

	/**
	 * @param codigoPeriodoDesde the codigoPeriodoDesde to set
	 */
	public void setCodigoPeriodoDesde(String codigoPeriodoDesde) {
		this.codigoPeriodoDesde = codigoPeriodoDesde;
	}

	/**
	 * @return the codigoPeriodoHasta
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}

	/**
	 * @param codigoPeriodoHasta the codigoPeriodoHasta to set
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}

	/**
	 * @return the descripcionTipoOferta
	 */
	public String getDescripcionTipoOferta() {
		return descripcionTipoOferta;
	}

	/**
	 * @param descripcionTipoOferta the descripcionTipoOferta to set
	 */
	public void setDescripcionTipoOferta(String descripcionTipoOferta) {
		this.descripcionTipoOferta = descripcionTipoOferta;
	}

	/**
	 * @return the indicadorAgrupacion
	 */
	public String getIndicadorAgrupacion() {
		return indicadorAgrupacion;
	}

	/**
	 * @param indicadorAgrupacion the indicadorAgrupacion to set
	 */
	public void setIndicadorAgrupacion(String indicadorAgrupacion) {
		this.indicadorAgrupacion = indicadorAgrupacion;
	}

	/**
	 * @return the descripcionAgrupacion
	 */
	public String getDescripcionAgrupacion() {
		return descripcionAgrupacion;
	}

	/**
	 * @param descripcionAgrupacion the descripcionAgrupacion to set
	 */
	public void setDescripcionAgrupacion(String descripcionAgrupacion) {
		this.descripcionAgrupacion = descripcionAgrupacion;
	}

	/**
	 * @return the codigoSuperGenerico
	 */
	public String getCodigoSuperGenerico() {
		return codigoSuperGenerico;
	}

	/**
	 * @param codigoSuperGenerico the codigoSuperGenerico to set
	 */
	public void setCodigoSuperGenerico(String codigoSuperGenerico) {
		this.codigoSuperGenerico = codigoSuperGenerico;
	}

	/**
	 * @return the codigoGenerico
	 */
	public String getCodigoGenerico() {
		return codigoGenerico;
	}

	/**
	 * @param codigoGenerico the codigoGenerico to set
	 */
	public void setCodigoGenerico(String codigoGenerico) {
		this.codigoGenerico = codigoGenerico;
	}

	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return the oidDetalleOferta
	 */
	public String getOidDetalleOferta() {
		return oidDetalleOferta;
	}

	/**
	 * @param oidDetalleOferta the oidDetalleOferta to set
	 */
	public void setOidDetalleOferta(String oidDetalleOferta) {
		this.oidDetalleOferta = oidDetalleOferta;
	}

	/**
	 * @return the descripcionCUV
	 */
	public String getDescripcionCUV() {
		return descripcionCUV;
	}

	/**
	 * @param descripcionCUV the descripcionCUV to set
	 */
	public void setDescripcionCUV(String descripcionCUV) {
		this.descripcionCUV = descripcionCUV;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}
	
	

}
