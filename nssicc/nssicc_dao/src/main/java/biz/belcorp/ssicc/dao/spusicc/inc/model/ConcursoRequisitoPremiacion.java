package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoRequisitoPremiacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private java.math.BigDecimal montoMinimoConcurso;
	private Integer numeroPedidos;
	private Integer cuotaIngreso;
	private Integer indicadorPedidoEnPeriodo;
	private java.math.BigDecimal montoMinimoPedido;
	private Integer indicadorPagoATiempo;
	private Integer diasGracia;
	private Integer indicadorAsistenciaCurso;
	private Long oidConcurso;
	private Long oidPeriodoHasta;
	private Long oidPeriodoDesde;
    private Long periodosEsperaPago;
    private Long oidperiodoMaximo;
    private Long oidValidaDeuda;
	
    private Integer indicadorDespacho;
    private String indicadorPriorizaWeb;
	
	public ConcursoRequisitoPremiacion() {
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
	 * @return the montoMinimoConcurso
	 */
	public java.math.BigDecimal getMontoMinimoConcurso() {
		return montoMinimoConcurso;
	}

	/**
	 * @param montoMinimoConcurso the montoMinimoConcurso to set
	 */
	public void setMontoMinimoConcurso(java.math.BigDecimal montoMinimoConcurso) {
		this.montoMinimoConcurso = montoMinimoConcurso;
	}

	/**
	 * @return the numeroPedidos
	 */
	public Integer getNumeroPedidos() {
		return numeroPedidos;
	}

	/**
	 * @param numeroPedidos the numeroPedidos to set
	 */
	public void setNumeroPedidos(Integer numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}

	/**
	 * @return the cuotaIngreso
	 */
	public Integer NumeroNiveles() {
		return cuotaIngreso;
	}

	/**
	 * @return the cuotaIngreso
	 */
	public Integer getCuotaIngreso() {
		return cuotaIngreso;
	}
	
	/**
	 * @param cuotaIngreso the cuotaIngreso to set
	 */
	public void setCuotaIngreso(Integer cuotaIngreso) {
		this.cuotaIngreso = cuotaIngreso;
	}

	/**
	 * @return the indicadorPedidoEnPeriodo
	 */
	public Integer getIndicadorPedidoEnPeriodo() {
		return indicadorPedidoEnPeriodo;
	}

	/**
	 * @param indicadorPedidoEnPeriodo the indicadorPedidoEnPeriodo to set
	 */
	public void setIndicadorPedidoEnPeriodo(Integer indicadorPedidoEnPeriodo) {
		this.indicadorPedidoEnPeriodo = indicadorPedidoEnPeriodo;
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
	 * @return the indicadorPagoATiempo
	 */
	public Integer getIndicadorPagoATiempo() {
		return indicadorPagoATiempo;
	}

	/**
	 * @param indicadorPagoATiempo the indicadorPagoATiempo to set
	 */
	public void setIndicadorPagoATiempo(Integer indicadorPagoATiempo) {
		this.indicadorPagoATiempo = indicadorPagoATiempo;
	}

	/**
	 * @return the diasGracia
	 */
	public Integer getDiasGracia() {
		return diasGracia;
	}

	/**
	 * @param diasGracia the diasGracia to set
	 */
	public void setDiasGracia(Integer diasGracia) {
		this.diasGracia = diasGracia;
	}

	/**
	 * @return the indicadorAsistenciaCurso
	 */
	public Integer getIndicadorAsistenciaCurso() {
		return indicadorAsistenciaCurso;
	}

	/**
	 * @param indicadorAsistenciaCurso the indicadorAsistenciaCurso to set
	 */
	public void setIndicadorAsistenciaCurso(Integer indicadorAsistenciaCurso) {
		this.indicadorAsistenciaCurso = indicadorAsistenciaCurso;
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
	 * @return the periodosEsperaPago
	 */
	public Long getPeriodosEsperaPago() {
		return periodosEsperaPago;
	}

	/**
	 * @param periodosEsperaPago the periodosEsperaPago to set
	 */
	public void setPeriodosEsperaPago(Long periodosEsperaPago) {
		this.periodosEsperaPago = periodosEsperaPago;
	}

	/**
	 * @return the oidperiodoMaximo
	 */
	public Long getOidperiodoMaximo() {
		return oidperiodoMaximo;
	}

	/**
	 * @param oidperiodoMaximo the oidperiodoMaximo to set
	 */
	public void setOidperiodoMaximo(Long oidperiodoMaximo) {
		this.oidperiodoMaximo = oidperiodoMaximo;
	}

	/**
	 * @return the oidValidaDeuda
	 */
	public Long getOidValidaDeuda() {
		return oidValidaDeuda;
	}

	/**
	 * @param oidValidaDeuda the oidValidaDeuda to set
	 */
	public void setOidValidaDeuda(Long oidValidaDeuda) {
		this.oidValidaDeuda = oidValidaDeuda;
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
	 * @return the indicadorPriorizaWeb
	 */
	public String getIndicadorPriorizaWeb() {
		return indicadorPriorizaWeb;
	}

	/**
	 * @param indicadorPriorizaWeb the indicadorPriorizaWeb to set
	 */
	public void setIndicadorPriorizaWeb(String indicadorPriorizaWeb) {
		this.indicadorPriorizaWeb = indicadorPriorizaWeb;
	}

}
