/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.model;

import java.io.Serializable;

public class DatosComision implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String codigoPais;
	private String tipoComisionista;
	private String codigoMarca;
	private String codigoCanal;
	private String descripcion;
	private Integer numeroDiasRecuperacionAspirante;
	private Double porcentajeRecuperacionAspirante;
	private Double porcentajeComisionAspirante;
	private Integer numeroDiasRecuperacionEjecutiva;
	private Double porcentajeRecuperacionEjecutiva;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getTipoComisionista() {
		return tipoComisionista;
	}
	public void setTipoComisionista(String tipoComisionista) {
		this.tipoComisionista = tipoComisionista;
	}
	public String getCodigoMarca() {
		return codigoMarca;
	}
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	public String getCodigoCanal() {
		return codigoCanal;
	}
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getNumeroDiasRecuperacionAspirante() {
		return numeroDiasRecuperacionAspirante;
	}
	public void setNumeroDiasRecuperacionAspirante(
			Integer numeroDiasRecuperacionAspirante) {
		this.numeroDiasRecuperacionAspirante = numeroDiasRecuperacionAspirante;
	}
	public Double getPorcentajeRecuperacionAspirante() {
		return porcentajeRecuperacionAspirante;
	}
	public void setPorcentajeRecuperacionAspirante(
			Double porcentajeRecuperacionAspirante) {
		this.porcentajeRecuperacionAspirante = porcentajeRecuperacionAspirante;
	}
	public Double getPorcentajeComisionAspirante() {
		return porcentajeComisionAspirante;
	}
	public void setPorcentajeComisionAspirante(Double porcentajeComisionAspirante) {
		this.porcentajeComisionAspirante = porcentajeComisionAspirante;
	}
	public Integer getNumeroDiasRecuperacionEjecutiva() {
		return numeroDiasRecuperacionEjecutiva;
	}
	public void setNumeroDiasRecuperacionEjecutiva(
			Integer numeroDiasRecuperacionEjecutiva) {
		this.numeroDiasRecuperacionEjecutiva = numeroDiasRecuperacionEjecutiva;
	}
	public Double getPorcentajeRecuperacionEjecutiva() {
		return porcentajeRecuperacionEjecutiva;
	}
	public void setPorcentajeRecuperacionEjecutiva(
			Double porcentajeRecuperacionEjecutiva) {
		this.porcentajeRecuperacionEjecutiva = porcentajeRecuperacionEjecutiva;
	}
	
}