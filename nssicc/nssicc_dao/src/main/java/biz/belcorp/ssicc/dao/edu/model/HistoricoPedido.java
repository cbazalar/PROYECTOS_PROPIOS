package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class HistoricoPedido extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 320149556385825406L;
	private String id;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCliente;
	private String apellidoMaterno;
	private String apellidoPaterno;
	private String primerNombre;
	private String segundoNombre;
	private String campanhaInicioPedido;
	private String campanhaUltimoPedido;
	private String campanhaProceso;
	private String codigoUltimoNivel;
	private Integer numeroPedidosFacturados;
	private String indicadorPedido;
	private String indicadorFacturado;
	private String indicadorNuevo;
	private String indicadorConstante;
	private String indicadorPrimerPedido;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the apellidoMaterno.
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno The apellidoMaterno to set.
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return Returns the apellidoPaterno.
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno The apellidoPaterno to set.
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return Returns the campanhaInicioPedido.
	 */
	public String getCampanhaInicioPedido() {
		return campanhaInicioPedido;
	}
	/**
	 * @param campanhaInicioPedido The campanhaInicioPedido to set.
	 */
	public void setCampanhaInicioPedido(String campanhaInicioPedido) {
		this.campanhaInicioPedido = campanhaInicioPedido;
	}
	/**
	 * @return Returns the campanhaProceso.
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}
	/**
	 * @param campanhaProceso The campanhaProceso to set.
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}
	/**
	 * @return Returns the campanhaUltimoPedido.
	 */
	public String getCampanhaUltimoPedido() {
		return campanhaUltimoPedido;
	}
	/**
	 * @param campanhaUltimoPedido The campanhaUltimoPedido to set.
	 */
	public void setCampanhaUltimoPedido(String campanhaUltimoPedido) {
		this.campanhaUltimoPedido = campanhaUltimoPedido;
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
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
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
	 * @return Returns the codigoUltimoNivel.
	 */
	public String getCodigoUltimoNivel() {
		return codigoUltimoNivel;
	}
	/**
	 * @param codigoUltimoNivel The codigoUltimoNivel to set.
	 */
	public void setCodigoUltimoNivel(String codigoUltimoNivel) {
		this.codigoUltimoNivel = codigoUltimoNivel;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the indicadorConstante.
	 */
	public String getIndicadorConstante() {
		return indicadorConstante;
	}
	/**
	 * @param indicadorConstante The indicadorConstante to set.
	 */
	public void setIndicadorConstante(String indicadorConstante) {
		this.indicadorConstante = indicadorConstante;
	}
	/**
	 * @return Returns the indicadorFacturado.
	 */
	public String getIndicadorFacturado() {
		return indicadorFacturado;
	}
	/**
	 * @param indicadorFacturado The indicadorFacturado to set.
	 */
	public void setIndicadorFacturado(String indicadorFacturado) {
		this.indicadorFacturado = indicadorFacturado;
	}
	/**
	 * @return Returns the indicadorNuevo.
	 */
	public String getIndicadorNuevo() {
		return indicadorNuevo;
	}
	/**
	 * @param indicadorNuevo The indicadorNuevo to set.
	 */
	public void setIndicadorNuevo(String indicadorNuevo) {
		this.indicadorNuevo = indicadorNuevo;
	}
	/**
	 * @return Returns the indicadorPedido.
	 */
	public String getIndicadorPedido() {
		return indicadorPedido;
	}
	/**
	 * @param indicadorPedido The indicadorPedido to set.
	 */
	public void setIndicadorPedido(String indicadorPedido) {
		this.indicadorPedido = indicadorPedido;
	}
	/**
	 * @return Returns the indicadorPrimerPedido.
	 */
	public String getIndicadorPrimerPedido() {
		return indicadorPrimerPedido;
	}
	/**
	 * @param indicadorPrimerPedido The indicadorPrimerPedido to set.
	 */
	public void setIndicadorPrimerPedido(String indicadorPrimerPedido) {
		this.indicadorPrimerPedido = indicadorPrimerPedido;
	}
	/**
	 * @return Returns the nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * @param nombreEmpresa The nombreEmpresa to set.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * @return Returns the numeroPedidosFacturados.
	 */
	public Integer getNumeroPedidosFacturados() {
		return numeroPedidosFacturados;
	}
	/**
	 * @param numeroPedidosFacturados The numeroPedidosFacturados to set.
	 */
	public void setNumeroPedidosFacturados(Integer numeroPedidosFacturados) {
		this.numeroPedidosFacturados = numeroPedidosFacturados;
	}
	/**
	 * @return Returns the primerNombre.
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}
	/**
	 * @param primerNombre The primerNombre to set.
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	/**
	 * @return Returns the segundoNombre.
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}
	/**
	 * @param segundoNombre The segundoNombre to set.
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

}
