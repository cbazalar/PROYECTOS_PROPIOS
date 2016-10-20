package biz.belcorp.ssicc.dao.spusicc.ventas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jos A. Cairampoma
 * 
 */
public class ParametroCDR extends AuditableBaseObject implements Serializable {

	private String codigoPais;

	private String idTipoSolicitud;
	
	private String descripcionTipoSolicitud;

	private String indicadorAnulado;

	private String indicadorDevolucion;

	private String indicadorTrueque;

	private String indicadorCanje;

	private String indicadorFaltante;

	private String indicadorPremio;
	
	private String indicadorNMP;

	private String indicadorPedidoServicio;

	private String indicadorVenta;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
	
		return 0;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the indicadorAnulado.
	 */
	public String getIndicadorAnulado() {
		return indicadorAnulado;
	}

	/**
	 * @param indicadorAnulado
	 *            The indicadorAnulado to set.
	 */
	public void setIndicadorAnulado(String indicadorAnulado) {
		this.indicadorAnulado = indicadorAnulado;
	}

	/**
	 * @return Returns the indicadorCanje.
	 */
	public String getIndicadorCanje() {
		return indicadorCanje;
	}

	/**
	 * @param indicadorCanje
	 *            The indicadorCanje to set.
	 */
	public void setIndicadorCanje(String indicadorCanje) {
		this.indicadorCanje = indicadorCanje;
	}

	/**
	 * @return Returns the indicadorDevolucion.
	 */
	public String getIndicadorDevolucion() {
		return indicadorDevolucion;
	}

	/**
	 * @param indicadorDevolucion
	 *            The indicadorDevolucion to set.
	 */
	public void setIndicadorDevolucion(String indicadorDevolucion) {
		this.indicadorDevolucion = indicadorDevolucion;
	}

	/**
	 * @return Returns the indicadorFaltante.
	 */
	public String getIndicadorFaltante() {
		return indicadorFaltante;
	}

	/**
	 * @param indicadorFaltante
	 *            The indicadorFaltante to set.
	 */
	public void setIndicadorFaltante(String indicadorFaltante) {
		this.indicadorFaltante = indicadorFaltante;
	}

	/**
	 * @return Returns the indicadorPedidoServicio.
	 */
	public String getIndicadorPedidoServicio() {
		return indicadorPedidoServicio;
	}

	/**
	 * @param indicadorPedidoServicio
	 *            The indicadorPedidoServicio to set.
	 */
	public void setIndicadorPedidoServicio(String indicadorPedidoServicio) {
		this.indicadorPedidoServicio = indicadorPedidoServicio;
	}

	/**
	 * @return Returns the indicadorPremio.
	 */
	public String getIndicadorPremio() {
		return indicadorPremio;
	}

	/**
	 * @param indicadorPremio
	 *            The indicadorPremio to set.
	 */
	public void setIndicadorPremio(String indicadorPremio) {
		this.indicadorPremio = indicadorPremio;
	}

	/**
	 * @return Returns the indicadorTrueque.
	 */
	public String getIndicadorTrueque() {
		return indicadorTrueque;
	}

	/**
	 * @param indicadorTrueque
	 *            The indicadorTrueque to set.
	 */
	public void setIndicadorTrueque(String indicadorTrueque) {
		this.indicadorTrueque = indicadorTrueque;
	}

	/**
	 * @return Returns the indicadorVenta.
	 */
	public String getIndicadorVenta() {
		return indicadorVenta;
	}

	/**
	 * @param indicadorVenta
	 *            The indicadorVenta to set.
	 */
	public void setIndicadorVenta(String indicadorVenta) {
		this.indicadorVenta = indicadorVenta;
	}

	/**
	 * @return Returns the idTipoSolicitud.
	 */
	public String getIdTipoSolicitud() {
		return idTipoSolicitud;
	}

	/**
	 * @param idTipoSolicitud
	 *            The idTipoSolicitud to set.
	 */
	public void setIdTipoSolicitud(String idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}
	
	/**
	 * @return Returns the indicadorNMP.
	 */
	public String getIndicadorNMP() {
		return indicadorNMP;
	}
	/**
	 * @param indicadorNMP
	 *            The indicadorNMP to set.
	 */
	public void setIndicadorNMP(String indicadorNMP) {
		this.indicadorNMP = indicadorNMP;
	}
	/**
	 * @return Returns the descripcionTipoSolicitud.
	 */
	public String getDescripcionTipoSolicitud() {
		return descripcionTipoSolicitud;
	}
	/**
	 * @param descripcionTipoSolicitud
	 *            The descripcionTipoSolicitud to set.
	 */
	public void setDescripcionTipoSolicitud(String descripcionTipoSolicitud) {
		this.descripcionTipoSolicitud = descripcionTipoSolicitud;
	}

	@Override
	public String toString() {
		return "ParametroCDR [codigoPais=" + codigoPais + ", idTipoSolicitud="
				+ idTipoSolicitud + ", descripcionTipoSolicitud="
				+ descripcionTipoSolicitud + ", indicadorAnulado="
				+ indicadorAnulado + ", indicadorDevolucion="
				+ indicadorDevolucion + ", indicadorTrueque="
				+ indicadorTrueque + ", indicadorCanje=" + indicadorCanje
				+ ", indicadorFaltante=" + indicadorFaltante
				+ ", indicadorPremio=" + indicadorPremio + ", indicadorNMP="
				+ indicadorNMP + ", indicadorPedidoServicio="
				+ indicadorPedidoServicio + ", indicadorVenta="
				+ indicadorVenta + "]";
	}
}
