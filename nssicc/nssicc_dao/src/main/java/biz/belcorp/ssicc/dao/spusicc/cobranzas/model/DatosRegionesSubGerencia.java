package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class DatosRegionesSubGerencia extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer oidComisionCliente;
	private String codigoPais;
	private String codigoCanal;
	private String codigoMarca;
	private Date fechaAntiguedadDesde;
	private Date fechaAntiguedadHasta;
	private String strfechaAntiguedadDesde;
	private String strfechaAntiguedadHasta;
	private String codigoSubGerencia;
	private String descripcionSubGerencia;
	private String codigoRegion;
	private String descripcionRegion;
	private String codigoComision;
	private int oidComision;
	private int orden;
	private String codigoZona;
	private String descripcionZona;
	
	private String claveRegistro;
	
	public DatosRegionesSubGerencia() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the oidComisionCliente
	 */
	public Integer getOidComisionCliente() {
		return oidComisionCliente;
	}

	/**
	 * @param oidComisionCliente the oidComisionCliente to set
	 */
	public void setOidComisionCliente(Integer oidComisionCliente) {
		this.oidComisionCliente = oidComisionCliente;
	}

	/**
	 * @return the fechaAntiguedadDesde
	 */
	public Date getFechaAntiguedadDesde() {
		return fechaAntiguedadDesde;
	}

	/**
	 * @param fechaAntiguedadDesde the fechaAntiguedadDesde to set
	 */
	public void setFechaAntiguedadDesde(Date fechaAntiguedadDesde) {
		this.fechaAntiguedadDesde = fechaAntiguedadDesde;
	}

	/**
	 * @return the fechaAntiguedadHasta
	 */
	public Date getFechaAntiguedadHasta() {
		return fechaAntiguedadHasta;
	}

	/**
	 * @param fechaAntiguedadHasta the fechaAntiguedadHasta to set
	 */
	public void setFechaAntiguedadHasta(Date fechaAntiguedadHasta) {
		this.fechaAntiguedadHasta = fechaAntiguedadHasta;
	}

	/**
	 * @return the codigoSubGerencia
	 */
	public String getCodigoSubGerencia() {
		return codigoSubGerencia;
	}

	/**
	 * @param codigoSubGerencia the codigoSubGerencia to set
	 */
	public void setCodigoSubGerencia(String codigoSubGerencia) {
		this.codigoSubGerencia = codigoSubGerencia;
	}

	/**
	 * @return the descripcionSubGerencia
	 */
	public String getDescripcionSubGerencia() {
		return descripcionSubGerencia;
	}

	/**
	 * @param descripcionSubGerencia the descripcionSubGerencia to set
	 */
	public void setDescripcionSubGerencia(String descripcionSubGerencia) {
		this.descripcionSubGerencia = descripcionSubGerencia;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	

	/**
	 * @return the orden
	 */
	public int getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	
	
	public void setOrden(int orden) {
		this.orden = orden;
	}

	
	/**
	 * @return the codigoComision
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision the codigoComision to set
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return the oidComision
	 */
	public int getOidComision() {
		return oidComision;
	}

	/**
	 * @param oidComision the oidComision to set
	 */
	public void setOidComision(int oidComision) {
		this.oidComision = oidComision;
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
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the strfechaAntiguedadDesde
	 */
	public String getStrfechaAntiguedadDesde() {
		return strfechaAntiguedadDesde;
	}

	/**
	 * @param strfechaAntiguedadDesde the strfechaAntiguedadDesde to set
	 */
	public void setStrfechaAntiguedadDesde(String strfechaAntiguedadDesde) {
		this.strfechaAntiguedadDesde = strfechaAntiguedadDesde;
	}

	/**
	 * @return the strfechaAntiguedadHasta
	 */
	public String getStrfechaAntiguedadHasta() {
		return strfechaAntiguedadHasta;
	}

	/**
	 * @param strfechaAntiguedadHasta the strfechaAntiguedadHasta to set
	 */
	public void setStrfechaAntiguedadHasta(String strfechaAntiguedadHasta) {
		this.strfechaAntiguedadHasta = strfechaAntiguedadHasta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

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

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	/**
	 * @return the claveRegistro
	 */
	public String getClaveRegistro() {
		return this.codigoRegion + "_" + (StringUtils.isBlank(this.codigoZona) ? "" : this.codigoZona);
	}

	/**
	 * @param claveRegistro the claveRegistro to set
	 */
	public void setClaveRegistro(String claveRegistro) {
		this.claveRegistro = claveRegistro;
	}
	
	

}
