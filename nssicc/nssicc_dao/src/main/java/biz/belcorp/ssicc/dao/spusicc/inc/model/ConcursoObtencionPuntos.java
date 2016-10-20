package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoObtencionPuntos extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private java.math.BigDecimal factorConversion;
	private Long numeroPuntosAsignar;
	private Integer indicadorComunicacion;
	private Integer indicadorPuntajeAcumulativo;
	private Integer indicadorActividad;
	private Integer indicadorConstancia;
	private Long oidConcurso;
	private Long oidMensaje;
	
	private String codigoMensaje;
	
	private Integer numeroPeriodosSinPedido;
	
	private String descripcionMensaje;
	
	private String indicadorExigePasarPedidoCampanyaAnteriorCPP;
	private String puntosAbonarRecomendacionEfectivaCPP;
	private String campanyasSinPedidoParaCancelacionPuntosCPP;
	private Integer tipoCuadre;
	
	public ConcursoObtencionPuntos() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	 * @return the factorConversion
	 */
	public java.math.BigDecimal getFactorConversion() {
		return factorConversion;
	}

	/**
	 * @param factorConversion the factorConversion to set
	 */
	public void setFactorConversion(java.math.BigDecimal factorConversion) {
		this.factorConversion = factorConversion;
	}

	/**
	 * @return the numeroPuntosAsignar
	 */
	public Long getNumeroPuntosAsignar() {
		return numeroPuntosAsignar;
	}

	/**
	 * @param numeroPuntosAsignar the numeroPuntosAsignar to set
	 */
	public void setNumeroPuntosAsignar(Long numeroPuntosAsignar) {
		this.numeroPuntosAsignar = numeroPuntosAsignar;
	}

	/**
	 * @return the indicadorComunicacion
	 */
	public Integer getIndicadorComunicacion() {
		return indicadorComunicacion;
	}

	/**
	 * @param indicadorComunicacion the indicadorComunicacion to set
	 */
	public void setIndicadorComunicacion(Integer indicadorComunicacion) {
		this.indicadorComunicacion = indicadorComunicacion;
	}

	/**
	 * @return the indicadorPuntajeAcumulativo
	 */
	public Integer getIndicadorPuntajeAcumulativo() {
		return indicadorPuntajeAcumulativo;
	}

	/**
	 * @param indicadorPuntajeAcumulativo the indicadorPuntajeAcumulativo to set
	 */
	public void setIndicadorPuntajeAcumulativo(Integer indicadorPuntajeAcumulativo) {
		this.indicadorPuntajeAcumulativo = indicadorPuntajeAcumulativo;
	}

	/**
	 * @return the indicadorActividad
	 */
	public Integer getIndicadorActividad() {
		return indicadorActividad;
	}

	/**
	 * @param indicadorActividad the indicadorActividad to set
	 */
	public void setIndicadorActividad(Integer indicadorActividad) {
		this.indicadorActividad = indicadorActividad;
	}

	/**
	 * @return the indicadorConstancia
	 */
	public Integer getIndicadorConstancia() {
		return indicadorConstancia;
	}

	/**
	 * @param indicadorConstancia the indicadorConstancia to set
	 */
	public void setIndicadorConstancia(Integer indicadorConstancia) {
		this.indicadorConstancia = indicadorConstancia;
	}

	/**
	 * @return the oidConcurso
	 */
	public Long getOidConcurso() {
		return oidConcurso;
	}

	/**
	 * @param oidConcurso the oidConcurso to set
	 */
	public void setOidConcurso(Long oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return the oidMensaje
	 */
	public Long getOidMensaje() {
		return oidMensaje;
	}

	/**
	 * @param oidMensaje the oidMensaje to set
	 */
	public void setOidMensaje(Long oidMensaje) {
		this.oidMensaje = oidMensaje;
	}

	/**
	 * @return the codigoMensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * @param codigoMensaje the codigoMensaje to set
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * @return the numeroPeriodosSinPedido
	 */
	public Integer getNumeroPeriodosSinPedido() {
		return numeroPeriodosSinPedido;
	}

	/**
	 * @param numeroPeriodosSinPedido the numeroPeriodosSinPedido to set
	 */
	public void setNumeroPeriodosSinPedido(Integer numeroPeriodosSinPedido) {
		this.numeroPeriodosSinPedido = numeroPeriodosSinPedido;
	}

	/**
	 * @return the descripcionMensaje
	 */
	public String getDescripcionMensaje() {
		return descripcionMensaje;
	}

	/**
	 * @param descripcionMensaje the descripcionMensaje to set
	 */
	public void setDescripcionMensaje(String descripcionMensaje) {
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * @return the indicadorExigePasarPedidoCampanyaAnteriorCPP
	 */
	public String getIndicadorExigePasarPedidoCampanyaAnteriorCPP() {
		return indicadorExigePasarPedidoCampanyaAnteriorCPP;
	}

	/**
	 * @param indicadorExigePasarPedidoCampanyaAnteriorCPP the indicadorExigePasarPedidoCampanyaAnteriorCPP to set
	 */
	public void setIndicadorExigePasarPedidoCampanyaAnteriorCPP(
			String indicadorExigePasarPedidoCampanyaAnteriorCPP) {
		this.indicadorExigePasarPedidoCampanyaAnteriorCPP = indicadorExigePasarPedidoCampanyaAnteriorCPP;
	}

	/**
	 * @return the puntosAbonarRecomendacionEfectivaCPP
	 */
	public String getPuntosAbonarRecomendacionEfectivaCPP() {
		return puntosAbonarRecomendacionEfectivaCPP;
	}

	/**
	 * @param puntosAbonarRecomendacionEfectivaCPP the puntosAbonarRecomendacionEfectivaCPP to set
	 */
	public void setPuntosAbonarRecomendacionEfectivaCPP(
			String puntosAbonarRecomendacionEfectivaCPP) {
		this.puntosAbonarRecomendacionEfectivaCPP = puntosAbonarRecomendacionEfectivaCPP;
	}

	/**
	 * @return the campanyasSinPedidoParaCancelacionPuntosCPP
	 */
	public String getCampanyasSinPedidoParaCancelacionPuntosCPP() {
		return campanyasSinPedidoParaCancelacionPuntosCPP;
	}

	/**
	 * @param campanyasSinPedidoParaCancelacionPuntosCPP the campanyasSinPedidoParaCancelacionPuntosCPP to set
	 */
	public void setCampanyasSinPedidoParaCancelacionPuntosCPP(
			String campanyasSinPedidoParaCancelacionPuntosCPP) {
		this.campanyasSinPedidoParaCancelacionPuntosCPP = campanyasSinPedidoParaCancelacionPuntosCPP;
	}

	/**
	 * @return the tipoCuadre
	 */
	public Integer getTipoCuadre() {
		return tipoCuadre;
	}

	/**
	 * @param tipoCuadre the tipoCuadre to set
	 */
	public void setTipoCuadre(Integer tipoCuadre) {
		this.tipoCuadre = tipoCuadre;
	}

	
	

}
