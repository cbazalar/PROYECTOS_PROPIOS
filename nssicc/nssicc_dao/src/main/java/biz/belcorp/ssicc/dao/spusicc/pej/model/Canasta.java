package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Canasta extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoVenta;
	private String usuarioDigitador;
	private String usuarioActulizador;
	
	private String codigoPeriodoAnterior;
	private String codigoVentaAnterior;
	
	
	/**
	 * @return
	 */
	public String getCodigoPeriodoAnterior() {
		return codigoPeriodoAnterior;
	}

	/**
	 * @param codigoPeriodoAnterior
	 */
	public void setCodigoPeriodoAnterior(String codigoPeriodoAnterior) {
		this.codigoPeriodoAnterior = codigoPeriodoAnterior;
	}

	/**
	 * @return
	 */
	public String getCodigoVentaAnterior() {
		return codigoVentaAnterior;
	}

	/**
	 * @param codigoVentaAnterior
	 */
	public void setCodigoVentaAnterior(String codigoVentaAnterior) {
		this.codigoVentaAnterior = codigoVentaAnterior;
	}

	/**
	 * @return
	 */
	public String getUsuarioDigitador() {
		return usuarioDigitador;
	}

	/**
	 * @param usuarioDigitador
	 */
	public void setUsuarioDigitador(String usuarioDigitador) {
		this.usuarioDigitador = usuarioDigitador;
	}

	/**
	 * @return
	 */
	public String getUsuarioActulizador() {
		return usuarioActulizador;
	}

	/**
	 * @param usuarioActulizador
	 */
	public void setUsuarioActulizador(String usuarioActulizador) {
		this.usuarioActulizador = usuarioActulizador;
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
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
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
}