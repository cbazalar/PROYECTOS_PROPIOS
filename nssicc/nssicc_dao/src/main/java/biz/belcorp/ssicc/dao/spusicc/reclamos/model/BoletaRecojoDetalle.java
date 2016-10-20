package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextllizana
 *
 */
public class BoletaRecojoDetalle extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
    private String codigoCabecera;
    private String codigoDetalle;
    private String numeroSecuencia;
    private String idCabeceraReclamo;
    private String idSolicitud;
    private String idOperacionReclamo;
    private String idProducto;
    private String codigoProducto;
    private String descripcionProducto;
    private String idMatrizFacturacion;
    private String idTipoOferta;
    private String idParametroGeneral;
    private String idParametroNivel;
    private String idLoteArticulo;
    private String unidadesReclamadas;
    private String unidadesRecogidas;
    private String observacion;
    private String idProductoDiscrepante;
    private String codigoProductoDiscrepante;
    private String descripcionProductoDisc;
    private String indicadorDiscrepante;
    private String idLineaOperacionReclamo;
    private String indicadorAlmacenFisico;
    private String indicadorEnvioXerox1;
    private String indicadorEnvioYobel1;
    private String indicadorRegresoYobel1;
    private String indicadorOCS;
    private String codigoOperacionHomol;
    private String precio;
    private String precioContable;
    private String numeroLoteEnvio;
    private String numeroLoteDevuelto;
    private String descripcionOperacion;
    private String codigoAntiguo;
    private String codigoOperacion;
    private String codigoOferta;
    private String indicadorEnvioXerox2;
    private String indicadorEnvioYobel2;
    private String indicadorRegresoYobel2;
    private String numeroLoteEnvio2;
    private String numeroLoteDevuelto2; 
    private String codigoVenta;
    private String discrepante;
    private String codigoVentaDiscrepante;
    private String periodoDiscrepante;
    private Float precioDiscrepante;
    
	private String unidadesEliminadas;
    private String cargo;
    private String abono;
    
    private String codigoPeriodoReferencia;

    private String numeroCDR;
    private String numeroDespacho;
    private String tipoCDR;
    private String codigoSAP;
    private String porcentajeDescuento;
	
	 
    private String idTipoConcurso;
    private String idDetalleOferta;
    private String idFormaPago;
    
    private Integer intUnidadesEliminadas;
    private Integer intUnidadesRecogidas;
    private Integer intUnidadesReclamadas;
    
    private String oidPedido;
    
    /**
	 * @return the unidadesEliminadas
	 */
	public String getUnidadesEliminadas() {
		return unidadesEliminadas;
	}

	/**
	 * @param unidadesEliminadas the unidadesEliminadas to set
	 */
	public void setUnidadesEliminadas(String unidadesEliminadas) {
		this.unidadesEliminadas = unidadesEliminadas;
	}

	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the abono
	 */
	public String getAbono() {
		return abono;
	}

	/**
	 * @param abono the abono to set
	 */
	public void setAbono(String abono) {
		this.abono = abono;
	}
    
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public String toString() {
		return "BoletaRecojoDetalle [codigoPais=" + codigoPais
				+ ", codigoCabecera=" + codigoCabecera + ", codigoDetalle="
				+ codigoDetalle + ", numeroSecuencia=" + numeroSecuencia
				+ ", idCabeceraReclamo=" + idCabeceraReclamo + ", idSolicitud="
				+ idSolicitud + ", idOperacionReclamo=" + idOperacionReclamo
				+ ", idProducto=" + idProducto + ", codigoProducto="
				+ codigoProducto + ", descripcionProducto="
				+ descripcionProducto + ", idMatrizFacturacion="
				+ idMatrizFacturacion + ", idTipoOferta=" + idTipoOferta
				+ ", idParametroGeneral=" + idParametroGeneral
				+ ", idParametroNivel=" + idParametroNivel
				+ ", idLoteArticulo=" + idLoteArticulo
				+ ", unidadesReclamadas=" + unidadesReclamadas
				+ ", unidadesRecogidas=" + unidadesRecogidas + ", observacion="
				+ observacion + ", idProductoDiscrepante="
				+ idProductoDiscrepante + ", codigoProductoDiscrepante="
				+ codigoProductoDiscrepante + ", descripcionProductoDisc="
				+ descripcionProductoDisc + ", indicadorDiscrepante="
				+ indicadorDiscrepante + ", idLineaOperacionReclamo="
				+ idLineaOperacionReclamo + ", indicadorAlmacenFisico="
				+ indicadorAlmacenFisico + ", indicadorEnvioXerox1="
				+ indicadorEnvioXerox1 + ", indicadorEnvioYobel1="
				+ indicadorEnvioYobel1 + ", indicadorRegresoYobel1="
				+ indicadorRegresoYobel1 + ", indicadorOCS=" + indicadorOCS
				+ ", codigoOperacionHomol=" + codigoOperacionHomol
				+ ", precio=" + precio + ", precioContable=" + precioContable
				+ ", numeroLoteEnvio=" + numeroLoteEnvio
				+ ", numeroLoteDevuelto=" + numeroLoteDevuelto
				+ ", descripcionOperacion=" + descripcionOperacion
				+ ", codigoAntiguo=" + codigoAntiguo + ", codigoOperacion="
				+ codigoOperacion + ", codigoOferta=" + codigoOferta
				+ ", indicadorEnvioXerox2=" + indicadorEnvioXerox2
				+ ", indicadorEnvioYobel2=" + indicadorEnvioYobel2
				+ ", indicadorRegresoYobel2=" + indicadorRegresoYobel2
				+ ", numeroLoteEnvio2=" + numeroLoteEnvio2
				+ ", numeroLoteDevuelto2=" + numeroLoteDevuelto2
				+ ", codigoVenta=" + codigoVenta + ", discrepante="
				+ discrepante + ", codigoVentaDiscrepante="
				+ codigoVentaDiscrepante + ", periodoDiscrepante="
				+ periodoDiscrepante + ", precioDiscrepante="
				+ precioDiscrepante + ", unidadesEliminadas="
				+ unidadesEliminadas + ", cargo=" + cargo + ", abono=" + abono
				+ ", codigoPeriodoReferencia=" + codigoPeriodoReferencia
				+ ", numeroCDR=" + numeroCDR + ", numeroDespacho="
				+ numeroDespacho + ", tipoCDR=" + tipoCDR + ", codigoSAP="
				+ codigoSAP + ", porcentajeDescuento=" + porcentajeDescuento
				+ ", idTipoConcurso=" + idTipoConcurso + ", idDetalleOferta="
				+ idDetalleOferta + ", idFormaPago=" + idFormaPago
				+ ", intUnidadesEliminadas=" + intUnidadesEliminadas
				+ ", intUnidadesRecogidas=" + intUnidadesRecogidas
				+ ", intUnidadesReclamadas=" + intUnidadesReclamadas + "]";
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoCabecera
	 */
	public String getCodigoCabecera() {
		return codigoCabecera;
	}

	/**
	 * @param codigoCabecera the codigoCabecera to set
	 */
	public void setCodigoCabecera(String codigoCabecera) {
		this.codigoCabecera = codigoCabecera;
	}

	/**
	 * @return the codigoDetalle
	 */
	public String getCodigoDetalle() {
		return codigoDetalle;
	}

	/**
	 * @param codigoDetalle the codigoDetalle to set
	 */
	public void setCodigoDetalle(String codigoDetalle) {
		this.codigoDetalle = codigoDetalle;
	}

	/**
	 * @return the numeroSecuencia
	 */
	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}

	/**
	 * @param numeroSecuencia the numeroSecuencia to set
	 */
	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	/**
	 * @return the idCabeceraReclamo
	 */
	public String getIdCabeceraReclamo() {
		return idCabeceraReclamo;
	}

	/**
	 * @param idCabeceraReclamo the idCabeceraReclamo to set
	 */
	public void setIdCabeceraReclamo(String idCabeceraReclamo) {
		this.idCabeceraReclamo = idCabeceraReclamo;
	}

	/**
	 * @return the idSolicitud
	 */
	public String getIdSolicitud() {
		return idSolicitud;
	}

	/**
	 * @param idSolicitud the idSolicitud to set
	 */
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	/**
	 * @return the idOperacionReclamo
	 */
	public String getIdOperacionReclamo() {
		return idOperacionReclamo;
	}

	/**
	 * @param idOperacionReclamo the idOperacionReclamo to set
	 */
	public void setIdOperacionReclamo(String idOperacionReclamo) {
		this.idOperacionReclamo = idOperacionReclamo;
	}

	/**
	 * @return the idProducto
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
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
	 * @return the idMatrizFacturacion
	 */
	public String getIdMatrizFacturacion() {
		return idMatrizFacturacion;
	}

	/**
	 * @param idMatrizFacturacion the idMatrizFacturacion to set
	 */
	public void setIdMatrizFacturacion(String idMatrizFacturacion) {
		this.idMatrizFacturacion = idMatrizFacturacion;
	}

	/**
	 * @return the idTipoOferta
	 */
	public String getIdTipoOferta() {
		return idTipoOferta;
	}

	/**
	 * @param idTipoOferta the idTipoOferta to set
	 */
	public void setIdTipoOferta(String idTipoOferta) {
		this.idTipoOferta = idTipoOferta;
	}

	/**
	 * @return the idParametroGeneral
	 */
	public String getIdParametroGeneral() {
		return idParametroGeneral;
	}

	/**
	 * @param idParametroGeneral the idParametroGeneral to set
	 */
	public void setIdParametroGeneral(String idParametroGeneral) {
		this.idParametroGeneral = idParametroGeneral;
	}

	/**
	 * @return the idParametroNivel
	 */
	public String getIdParametroNivel() {
		return idParametroNivel;
	}

	/**
	 * @param idParametroNivel the idParametroNivel to set
	 */
	public void setIdParametroNivel(String idParametroNivel) {
		this.idParametroNivel = idParametroNivel;
	}

	/**
	 * @return the idLoteArticulo
	 */
	public String getIdLoteArticulo() {
		return idLoteArticulo;
	}

	/**
	 * @param idLoteArticulo the idLoteArticulo to set
	 */
	public void setIdLoteArticulo(String idLoteArticulo) {
		this.idLoteArticulo = idLoteArticulo;
	}

	/**
	 * @return the unidadesReclamadas
	 */
	public String getUnidadesReclamadas() {
		return unidadesReclamadas;
	}

	/**
	 * @param unidadesReclamadas the unidadesReclamadas to set
	 */
	public void setUnidadesReclamadas(String unidadesReclamadas) {
		this.unidadesReclamadas = unidadesReclamadas;
	}

	/**
	 * @return the unidadesRecogidas
	 */
	public String getUnidadesRecogidas() {
		return unidadesRecogidas;
	}

	/**
	 * @param unidadesRecogidas the unidadesRecogidas to set
	 */
	public void setUnidadesRecogidas(String unidadesRecogidas) {
		this.unidadesRecogidas = unidadesRecogidas;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the idProductoDiscrepante
	 */
	public String getIdProductoDiscrepante() {
		return idProductoDiscrepante;
	}

	/**
	 * @param idProductoDiscrepante the idProductoDiscrepante to set
	 */
	public void setIdProductoDiscrepante(String idProductoDiscrepante) {
		this.idProductoDiscrepante = idProductoDiscrepante;
	}

	/**
	 * @return the codigoProductoDiscrepante
	 */
	public String getCodigoProductoDiscrepante() {
		return codigoProductoDiscrepante;
	}

	/**
	 * @param codigoProductoDiscrepante the codigoProductoDiscrepante to set
	 */
	public void setCodigoProductoDiscrepante(String codigoProductoDiscrepante) {
		this.codigoProductoDiscrepante = codigoProductoDiscrepante;
	}

	/**
	 * @return the indicadorDiscrepante
	 */
	public String getIndicadorDiscrepante() {
		return indicadorDiscrepante;
	}

	/**
	 * @param indicadorDiscrepante the indicadorDiscrepante to set
	 */
	public void setIndicadorDiscrepante(String indicadorDiscrepante) {
		this.indicadorDiscrepante = indicadorDiscrepante;
	}

	/**
	 * @return the idLineaOperacionReclamo
	 */
	public String getIdLineaOperacionReclamo() {
		return idLineaOperacionReclamo;
	}

	/**
	 * @param idLineaOperacionReclamo the idLineaOperacionReclamo to set
	 */
	public void setIdLineaOperacionReclamo(String idLineaOperacionReclamo) {
		this.idLineaOperacionReclamo = idLineaOperacionReclamo;
	}

	/**
	 * @return the indicadorAlmacenFisico
	 */
	public String getIndicadorAlmacenFisico() {
		return indicadorAlmacenFisico;
	}

	/**
	 * @param indicadorAlmacenFisico the indicadorAlmacenFisico to set
	 */
	public void setIndicadorAlmacenFisico(String indicadorAlmacenFisico) {
		this.indicadorAlmacenFisico = indicadorAlmacenFisico;
	}

	/**
	 * @return the indicadorEnvioXerox1
	 */
	public String getIndicadorEnvioXerox1() {
		return indicadorEnvioXerox1;
	}

	/**
	 * @param indicadorEnvioXerox1 the indicadorEnvioXerox1 to set
	 */
	public void setIndicadorEnvioXerox1(String indicadorEnvioXerox1) {
		this.indicadorEnvioXerox1 = indicadorEnvioXerox1;
	}

	/**
	 * @return the indicadorEnvioYobel1
	 */
	public String getIndicadorEnvioYobel1() {
		return indicadorEnvioYobel1;
	}

	/**
	 * @param indicadorEnvioYobel1 the indicadorEnvioYobel1 to set
	 */
	public void setIndicadorEnvioYobel1(String indicadorEnvioYobel1) {
		this.indicadorEnvioYobel1 = indicadorEnvioYobel1;
	}

	/**
	 * @return the indicadorRegresoYobel1
	 */
	public String getIndicadorRegresoYobel1() {
		return indicadorRegresoYobel1;
	}

	/**
	 * @param indicadorRegresoYobel1 the indicadorRegresoYobel1 to set
	 */
	public void setIndicadorRegresoYobel1(String indicadorRegresoYobel1) {
		this.indicadorRegresoYobel1 = indicadorRegresoYobel1;
	}

	/**
	 * @return the indicadorOCS
	 */
	public String getIndicadorOCS() {
		return indicadorOCS;
	}

	/**
	 * @param indicadorOCS the indicadorOCS to set
	 */
	public void setIndicadorOCS(String indicadorOCS) {
		this.indicadorOCS = indicadorOCS;
	}

	/**
	 * @return the codigoOperacionHomol
	 */
	public String getCodigoOperacionHomol() {
		return codigoOperacionHomol;
	}

	/**
	 * @param codigoOperacionHomol the codigoOperacionHomol to set
	 */
	public void setCodigoOperacionHomol(String codigoOperacionHomol) {
		this.codigoOperacionHomol = codigoOperacionHomol;
	}

	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	/**
	 * @return the precioContable
	 */
	public String getPrecioContable() {
		return precioContable;
	}

	/**
	 * @param precioContable the precioContable to set
	 */
	public void setPrecioContable(String precioContable) {
		this.precioContable = precioContable;
	}

	/**
	 * @return the numeroLoteEnvio
	 */
	public String getNumeroLoteEnvio() {
		return numeroLoteEnvio;
	}

	/**
	 * @param numeroLoteEnvio the numeroLoteEnvio to set
	 */
	public void setNumeroLoteEnvio(String numeroLoteEnvio) {
		this.numeroLoteEnvio = numeroLoteEnvio;
	}

	/**
	 * @return the numeroLoteDevuelto
	 */
	public String getNumeroLoteDevuelto() {
		return numeroLoteDevuelto;
	}

	/**
	 * @param numeroLoteDevuelto the numeroLoteDevuelto to set
	 */
	public void setNumeroLoteDevuelto(String numeroLoteDevuelto) {
		this.numeroLoteDevuelto = numeroLoteDevuelto;
	}

	/**
	 * @return the descripcionOperacion
	 */
	public String getDescripcionOperacion() {
		return descripcionOperacion;
	}

	/**
	 * @param descripcionOperacion the descripcionOperacion to set
	 */
	public void setDescripcionOperacion(String descripcionOperacion) {
		this.descripcionOperacion = descripcionOperacion;
	}

	

	/**
	 * @return the codigoAntiguo
	 */
	public String getCodigoAntiguo() {
		return codigoAntiguo;
	}

	/**
	 * @param codigoAntiguo the codigoAntiguo to set
	 */
	public void setCodigoAntiguo(String codigoAntiguo) {
		this.codigoAntiguo = codigoAntiguo;
	}

	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * @return the codigoOferta
	 */
	public String getCodigoOferta() {
		return codigoOferta;
	}

	/**
	 * @param codigoOferta the codigoOferta to set
	 */
	public void setCodigoOferta(String codigoOferta) {
		this.codigoOferta = codigoOferta;
	}

	/**
	 * @return the indicadorEnvioXerox2
	 */
	public String getIndicadorEnvioXerox2() {
		return indicadorEnvioXerox2;
	}

	/**
	 * @param indicadorEnvioXerox2 the indicadorEnvioXerox2 to set
	 */
	public void setIndicadorEnvioXerox2(String indicadorEnvioXerox2) {
		this.indicadorEnvioXerox2 = indicadorEnvioXerox2;
	}

	/**
	 * @return the indicadorEnvioYobel2
	 */
	public String getIndicadorEnvioYobel2() {
		return indicadorEnvioYobel2;
	}

	/**
	 * @param indicadorEnvioYobel2 the indicadorEnvioYobel2 to set
	 */
	public void setIndicadorEnvioYobel2(String indicadorEnvioYobel2) {
		this.indicadorEnvioYobel2 = indicadorEnvioYobel2;
	}

	/**
	 * @return the indicadorRegresoYobel2
	 */
	public String getIndicadorRegresoYobel2() {
		return indicadorRegresoYobel2;
	}

	/**
	 * @param indicadorRegresoYobel2 the indicadorRegresoYobel2 to set
	 */
	public void setIndicadorRegresoYobel2(String indicadorRegresoYobel2) {
		this.indicadorRegresoYobel2 = indicadorRegresoYobel2;
	}

	/**
	 * @return the numeroLoteEnvio2
	 */
	public String getNumeroLoteEnvio2() {
		return numeroLoteEnvio2;
	}

	/**
	 * @param numeroLoteEnvio2 the numeroLoteEnvio2 to set
	 */
	public void setNumeroLoteEnvio2(String numeroLoteEnvio2) {
		this.numeroLoteEnvio2 = numeroLoteEnvio2;
	}

	/**
	 * @return the numeroLoteDevuelto2
	 */
	public String getNumeroLoteDevuelto2() {
		return numeroLoteDevuelto2;
	}

	/**
	 * @param numeroLoteDevuelto2 the numeroLoteDevuelto2 to set
	 */
	public void setNumeroLoteDevuelto2(String numeroLoteDevuelto2) {
		this.numeroLoteDevuelto2 = numeroLoteDevuelto2;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the descripcionProductoDisc
	 */
	public String getDescripcionProductoDisc() {
		return descripcionProductoDisc;
	}

	/**
	 * @param descripcionProductoDisc the descripcionProductoDisc to set
	 */
	public void setDescripcionProductoDisc(String descripcionProductoDisc) {
		this.descripcionProductoDisc = descripcionProductoDisc;
	}

	/**
	 * @return the discrepante
	 */
	public String getDiscrepante() {
		return discrepante;
	}

	/**
	 * @param discrepante the discrepante to set
	 */
	public void setDiscrepante(String discrepante) {
		this.discrepante = discrepante;
	}

	/**
	 * @return the codigoVentaDiscrepante
	 */
	public String getCodigoVentaDiscrepante() {
		return codigoVentaDiscrepante;
	}

	/**
	 * @param codigoVentaDiscrepante the codigoVentaDiscrepante to set
	 */
	public void setCodigoVentaDiscrepante(String codigoVentaDiscrepante) {
		this.codigoVentaDiscrepante = codigoVentaDiscrepante;
	}

	/**
	 * @return the periodoDiscrepante
	 */
	public String getPeriodoDiscrepante() {
		return periodoDiscrepante;
	}

	/**
	 * @param periodoDiscrepante the periodoDiscrepante to set
	 */
	public void setPeriodoDiscrepante(String periodoDiscrepante) {
		this.periodoDiscrepante = periodoDiscrepante;
	}

	/**
	 * @return the precioDiscrepante
	 */
	public Float getPrecioDiscrepante() {
		return precioDiscrepante;
	}

	/**
	 * @param precioDiscrepante the precioDiscrepante to set
	 */
	public void setPrecioDiscrepante(Float precioDiscrepante) {
		this.precioDiscrepante = precioDiscrepante;
	}

	/**
	 * @return the codigoPeriodoReferencia
	 */
	public String getCodigoPeriodoReferencia() {
		return codigoPeriodoReferencia;
	}

	/**
	 * @param codigoPeriodoReferencia the codigoPeriodoReferencia to set
	 */
	public void setCodigoPeriodoReferencia(String codigoPeriodoReferencia) {
		this.codigoPeriodoReferencia = codigoPeriodoReferencia;
	}
/**
	 * @return the numeroCDR
	 */
	public String getNumeroCDR() {
		return numeroCDR;
	}

	/**
	 * @param numeroCDR the numeroCDR to set
	 */
	public void setNumeroCDR(String numeroCDR) {
		this.numeroCDR = numeroCDR;
	}

	/**
	 * @return the numeroDespacho
	 */
	public String getNumeroDespacho() {
		return numeroDespacho;
	}

	/**
	 * @param numeroDespacho the numeroDespacho to set
	 */
	public void setNumeroDespacho(String numeroDespacho) {
		this.numeroDespacho = numeroDespacho;
	}

	/**
	 * @return the tipoCDR
	 */
	public String getTipoCDR() {
		return tipoCDR;
	}

	/**
	 * @param tipoCDR the tipoCDR to set
	 */
	public void setTipoCDR(String tipoCDR) {
		this.tipoCDR = tipoCDR;
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
	 * @return the porcentajeDescuento
	 */
	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	/**
	 * @param porcentajeDescuento the porcentajeDescuento to set
	 */
	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	/**
	 * @return the idTipoConcurso
	 */
	public String getIdTipoConcurso() {
		return idTipoConcurso;
	}

	/**
	 * @param idTipoConcurso the idTipoConcurso to set
	 */
	public void setIdTipoConcurso(String idTipoConcurso) {
		this.idTipoConcurso = idTipoConcurso;
	}

	/**
	 * @return the idDetalleOferta
	 */
	public String getIdDetalleOferta() {
		return idDetalleOferta;
	}

	/**
	 * @param idDetalleOferta the idDetalleOferta to set
	 */
	public void setIdDetalleOferta(String idDetalleOferta) {
		this.idDetalleOferta = idDetalleOferta;
	}

	/**
	 * @return the idFormaPago
	 */
	public String getIdFormaPago() {
		return idFormaPago;
	}

	/**
	 * @param idFormaPago the idFormaPago to set
	 */
	public void setIdFormaPago(String idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	/**
	 * @return the intUnidadesEliminadas
	 */
	public Integer getIntUnidadesEliminadas() {
		return intUnidadesEliminadas;
	}
	/**
	 * @param intUnidadesEliminadas the intUnidadesEliminadas to set
	 */
	public void setIntUnidadesEliminadas(Integer intUnidadesEliminadas) {
		this.intUnidadesEliminadas = intUnidadesEliminadas;
	}
	
	/**
	 * @return the intUnidadesReclamadas
	 */
	public Integer getIntUnidadesReclamadas() {
		return intUnidadesReclamadas;
	}
	/**
	 * @param intUnidadesReclamadas the intUnidadesReclamadas to set
	 */
	public void setIntUnidadesReclamadas(Integer intUnidadesReclamadas) {
		this.intUnidadesReclamadas = intUnidadesReclamadas;
	}
	/**
	 * @return the intUnidadesRecogidas
	 */
	public Integer getIntUnidadesRecogidas() {
		return intUnidadesRecogidas;
	}
	
	/**
	 * @param intUnidadesRecogidas the intUnidadesRecogidas to set
	 */
	public void setIntUnidadesRecogidas(Integer intUnidadesRecogidas) {
		this.intUnidadesRecogidas = intUnidadesRecogidas;
	}
	
	public String getOidPedido() {
		return oidPedido;
	}

	public void setOidPedido(String oidPedido) {
		this.oidPedido = oidPedido;
	}
	
}
