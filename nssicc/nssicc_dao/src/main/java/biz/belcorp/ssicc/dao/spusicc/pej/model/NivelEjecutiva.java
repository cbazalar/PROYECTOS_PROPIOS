package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class NivelEjecutiva extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoNivel;
	
	private String descripcionNivel;
	
	private Integer pedidoPromedioDesde;
	
	private Integer pedidoPromedioHasta;
	
	private Integer incrementoPedido;

	/**
	 * @return
	 */
	public Integer getPedidoPromedioHasta() {
		return pedidoPromedioHasta;
	}

	/**
	 * @param pedidoPromedioHasta
	 */
	public void setPedidoPromedioHasta(Integer pedidoPromedioHasta) {
		this.pedidoPromedioHasta = pedidoPromedioHasta;
	}

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}

	/**
	 * @param codigoNivel
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}

	/**
	 * @return
	 */
	public String getDescripcionNivel() {
		return descripcionNivel;
	}

	/**
	 * @param descripcionNivel
	 */
	public void setDescripcionNivel(String descripcionNivel) {
		this.descripcionNivel = descripcionNivel;
	}

	/**
	 * @return
	 */
	public Integer getPedidoPromedioDesde() {
		return pedidoPromedioDesde;
	}

	/**
	 * @param pedidoPromedioDesde
	 */
	public void setPedidoPromedioDesde(Integer pedidoPromedioDesde) {
		this.pedidoPromedioDesde = pedidoPromedioDesde;
	}

	/**
	 * @return
	 */
	public Integer getIncrementoPedido() {
		return incrementoPedido;
	}

	/**
	 * @param incrementoPedido
	 */
	public void setIncrementoPedido(Integer incrementoPedido) {
		this.incrementoPedido = incrementoPedido;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}