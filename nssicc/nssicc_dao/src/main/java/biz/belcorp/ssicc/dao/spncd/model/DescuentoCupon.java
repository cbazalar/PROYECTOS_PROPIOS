package biz.belcorp.ssicc.dao.spncd.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Gonzalo Javier Huertas Agurto
 *
 */
public class DescuentoCupon extends AuditableBaseObject implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4488827636487757745L;
	private String codigoPais;
	private String codigoPrograma;
	private String nivel;
	private String campanhaInicio;
	private String campanhaFin;
	private String montoVentaExigencia;
	private String montoDescuento;
	private String codigoVenta;
	private String estadoRegistro;
	private String usuario;
	

	public DescuentoCupon() {
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
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the montoVentaExigencia
	 */
	public String getMontoVentaExigencia() {
		return montoVentaExigencia;
	}

	/**
	 * @param montoVentaExigencia the montoVentaExigencia to set
	 */
	public void setMontoVentaExigencia(String montoVentaExigencia) {
		this.montoVentaExigencia = montoVentaExigencia;
	}

	/**
	 * @return the montoDescuento
	 */
	public String getMontoDescuento() {
		return montoDescuento;
	}

	/**
	 * @param montoDescuento the montoDescuento to set
	 */
	public void setMontoDescuento(String montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	/**
	 * @return the estadoRegistro
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * @return the campanhaInicio
	 */
	public String getCampanhaInicio() {
		return campanhaInicio;
	}

	/**
	 * @param campanhaInicio the campanhaInicio to set
	 */
	public void setCampanhaInicio(String campanhaInicio) {
		this.campanhaInicio = campanhaInicio;
	}

	/**
	 * @return the campanhaFin
	 */
	public String getCampanhaFin() {
		return campanhaFin;
	}

	/**
	 * @param campanhaFin the campanhaFin to set
	 */
	public void setCampanhaFin(String campanhaFin) {
		this.campanhaFin = campanhaFin;
	}
	
	
	
}