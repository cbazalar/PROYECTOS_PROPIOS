package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoParametrosConsultoras extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long oid;
	private Integer indicadorRecomendacionEfectiva;
	private Integer periodosEvaluacion;
	private Integer indicadorControlRecomendadas;
	private Integer numeroMinimoPedidos;
	private Integer numeroMinimoPedidosRecomendada;
	private java.math.BigDecimal montoMinimoPedido;
	private Integer unidadesMinimasPedido;
	private Integer indicadorReingresoPierdePuntaje;
	private Long oidConcurso;
	private Long oidPeriodoDesde;
	private Long oidPeriodoInicialEvaluacion;
	private Long oidPeriodoHasta;
	private Long oidTipoVenta;
	private Integer indicadorPremioCampEfect;
	private Integer generarPuntajeARecomendadas;
	private Long oidConcursoPuntajeRecomendada;
	private Integer tipoEvaluacion;
	
	private String codigoPeriodoEvaluacion;
	private String numeroConcursoPuntajeRecomendada;	
	
	private ConcursoParametrosGenerales concursoRecomendada;
	
	public ConcursoParametrosConsultoras() {
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
	 * @return the indicadorRecomendacionEfectiva
	 */
	public Integer getIndicadorRecomendacionEfectiva() {
		return indicadorRecomendacionEfectiva;
	}

	/**
	 * @param indicadorRecomendacionEfectiva the indicadorRecomendacionEfectiva to set
	 */
	public void setIndicadorRecomendacionEfectiva(
			Integer indicadorRecomendacionEfectiva) {
		this.indicadorRecomendacionEfectiva = indicadorRecomendacionEfectiva;
	}

	/**
	 * @return the periodosEvaluacion
	 */
	public Integer getPeriodosEvaluacion() {
		return periodosEvaluacion;
	}

	/**
	 * @param periodosEvaluacion the periodosEvaluacion to set
	 */
	public void setPeriodosEvaluacion(Integer periodosEvaluacion) {
		this.periodosEvaluacion = periodosEvaluacion;
	}

	/**
	 * @return the indicadorControlRecomendadas
	 */
	public Integer getIndicadorControlRecomendadas() {
		return indicadorControlRecomendadas;
	}

	/**
	 * @param indicadorControlRecomendadas the indicadorControlRecomendadas to set
	 */
	public void setIndicadorControlRecomendadas(Integer indicadorControlRecomendadas) {
		this.indicadorControlRecomendadas = indicadorControlRecomendadas;
	}

	/**
	 * @return the numeroMinimoPedidos
	 */
	public Integer getNumeroMinimoPedidos() {
		return numeroMinimoPedidos;
	}

	/**
	 * @param numeroMinimoPedidos the numeroMinimoPedidos to set
	 */
	public void setNumeroMinimoPedidos(Integer numeroMinimoPedidos) {
		this.numeroMinimoPedidos = numeroMinimoPedidos;
	}

	/**
	 * @return the numeroMinimoPedidosRecomendada
	 */
	public Integer getNumeroMinimoPedidosRecomendada() {
		return numeroMinimoPedidosRecomendada;
	}

	/**
	 * @param numeroMinimoPedidosRecomendada the numeroMinimoPedidosRecomendada to set
	 */
	public void setNumeroMinimoPedidosRecomendada(
			Integer numeroMinimoPedidosRecomendada) {
		this.numeroMinimoPedidosRecomendada = numeroMinimoPedidosRecomendada;
	}

	/**
	 * @return the montoMinimoPedido
	 */
	public java.math.BigDecimal getMontoMinimoPedido() {
		return montoMinimoPedido;
	}

	/**
	 * @param montoMinimoPedido the montoMinimoPedido to set
	 */
	public void setMontoMinimoPedido(java.math.BigDecimal montoMinimoPedido) {
		this.montoMinimoPedido = montoMinimoPedido;
	}

	/**
	 * @return the unidadesMinimasPedido
	 */
	public Integer getUnidadesMinimasPedido() {
		return unidadesMinimasPedido;
	}

	/**
	 * @param unidadesMinimasPedido the unidadesMinimasPedido to set
	 */
	public void setUnidadesMinimasPedido(Integer unidadesMinimasPedido) {
		this.unidadesMinimasPedido = unidadesMinimasPedido;
	}

	/**
	 * @return the indicadorReingresoPierdePuntaje
	 */
	public Integer getIndicadorReingresoPierdePuntaje() {
		return indicadorReingresoPierdePuntaje;
	}

	/**
	 * @param indicadorReingresoPierdePuntaje the indicadorReingresoPierdePuntaje to set
	 */
	public void setIndicadorReingresoPierdePuntaje(
			Integer indicadorReingresoPierdePuntaje) {
		this.indicadorReingresoPierdePuntaje = indicadorReingresoPierdePuntaje;
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
	 * @return the oidPeriodoInicialEvaluacion
	 */
	public Long getOidPeriodoInicialEvaluacion() {
		return oidPeriodoInicialEvaluacion;
	}

	/**
	 * @param oidPeriodoInicialEvaluacion the oidPeriodoInicialEvaluacion to set
	 */
	public void setOidPeriodoInicialEvaluacion(Long oidPeriodoInicialEvaluacion) {
		this.oidPeriodoInicialEvaluacion = oidPeriodoInicialEvaluacion;
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
	 * @return the oidTipoVenta
	 */
	public Long getOidTipoVenta() {
		return oidTipoVenta;
	}

	/**
	 * @param oidTipoVenta the oidTipoVenta to set
	 */
	public void setOidTipoVenta(Long oidTipoVenta) {
		this.oidTipoVenta = oidTipoVenta;
	}

	/**
	 * @return the indicadorPremioCampEfect
	 */
	public Integer getIndicadorPremioCampEfect() {
		return indicadorPremioCampEfect;
	}

	/**
	 * @param indicadorPremioCampEfect the indicadorPremioCampEfect to set
	 */
	public void setIndicadorPremioCampEfect(Integer indicadorPremioCampEfect) {
		this.indicadorPremioCampEfect = indicadorPremioCampEfect;
	}

	/**
	 * @return the generarPuntajeARecomendadas
	 */
	public Integer getGenerarPuntajeARecomendadas() {
		return generarPuntajeARecomendadas;
	}

	/**
	 * @param generarPuntajeARecomendadas the generarPuntajeARecomendadas to set
	 */
	public void setGenerarPuntajeARecomendadas(Integer generarPuntajeARecomendadas) {
		this.generarPuntajeARecomendadas = generarPuntajeARecomendadas;
	}

	/**
	 * @return the oidConcursoPuntajeRecomendada
	 */
	public Long getOidConcursoPuntajeRecomendada() {
		return oidConcursoPuntajeRecomendada;
	}

	/**
	 * @param oidConcursoPuntajeRecomendada the oidConcursoPuntajeRecomendada to set
	 */
	public void setOidConcursoPuntajeRecomendada(Long oidConcursoPuntajeRecomendada) {
		this.oidConcursoPuntajeRecomendada = oidConcursoPuntajeRecomendada;
	}

	/**
	 * @return the codigoPeriodoEvaluacion
	 */
	public String getCodigoPeriodoEvaluacion() {
		return codigoPeriodoEvaluacion;
	}

	/**
	 * @param codigoPeriodoEvaluacion the codigoPeriodoEvaluacion to set
	 */
	public void setCodigoPeriodoEvaluacion(String codigoPeriodoEvaluacion) {
		this.codigoPeriodoEvaluacion = codigoPeriodoEvaluacion;
	}

	/**
	 * @return the numeroConcursoPuntajeRecomendada
	 */
	public String getNumeroConcursoPuntajeRecomendada() {
		return numeroConcursoPuntajeRecomendada;
	}

	/**
	 * @param numeroConcursoPuntajeRecomendada the numeroConcursoPuntajeRecomendada to set
	 */
	public void setNumeroConcursoPuntajeRecomendada(
			String numeroConcursoPuntajeRecomendada) {
		this.numeroConcursoPuntajeRecomendada = numeroConcursoPuntajeRecomendada;
	}

	/**
	 * @return the concursoRecomendada
	 */
	public ConcursoParametrosGenerales getConcursoRecomendada() {
		return concursoRecomendada;
	}

	/**
	 * @param concursoRecomendada the concursoRecomendada to set
	 */
	public void setConcursoRecomendada(
			ConcursoParametrosGenerales concursoRecomendada) {
		this.concursoRecomendada = concursoRecomendada;
	}

	/**
	 * @return the tipoEvaluacion
	 */
	public Integer getTipoEvaluacion() {
		return tipoEvaluacion;
	}

	/**
	 * @param tipoEvaluacion the tipoEvaluacion to set
	 */
	public void setTipoEvaluacion(Integer tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}

}
