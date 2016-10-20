package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Auditoria extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8774390775056334738L;
	private String  codigoPais;
	private String  codigoEmpresa;
	private String  nombreEmpresa;
	private String  codigoQuery;
	private String  nombreQuery;
	private String  campanha;
	private Integer numRegProcesados;
	private Integer numRegCorrectos;
	private Integer numRegErroneos;
	private String  estadoProceso;//0:error 1:exitoso
	private String  obsProceso;
	private String  estadoRegistro;
	private String descripcionQuery;


	/**
	 * @return Returns the descripcionQuery.
	 */
	public String getDescripcionQuery() {
		return descripcionQuery;
	}
	/**
	 * @param descripcionQuery The descripcionQuery to set.
	 */
	public void setDescripcionQuery(String descripcionQuery) {
		this.descripcionQuery = descripcionQuery;
	}
	public String toString() {
		 return new ToStringBuilder(this)
		 			.append("codigoPais", this.codigoPais)
		 			.append("codigoQuery", this.codigoQuery).append("nombreQuery", this.nombreQuery)
	                .append("campanha", this.campanha).toString();
	}
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @return Returns the campanha.
	 */
	public String getCampanha() {
		return campanha;
	}
	/**
	 * @param campanha The campanha to set.
	 */
	public void setCampanha(String campanha) {
		this.campanha = campanha;
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
	 * @return Returns the codigoQuery.
	 */
	public String getCodigoQuery() {
		return codigoQuery;
	}
	/**
	 * @param codigoQuery The codigoQuery to set.
	 */
	public void setCodigoQuery(String codigoQuery) {
		this.codigoQuery = codigoQuery;
	}
	/**
	 * @return Returns the estadoProceso.
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}
	/**
	 * @param estadoProceso The estadoProceso to set.
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return Returns the nombreQuery.
	 */
	public String getNombreQuery() {
		return nombreQuery;
	}
	/**
	 * @param nombreQuery The nombreQuery to set.
	 */
	public void setNombreQuery(String nombreQuery) {
		this.nombreQuery = nombreQuery;
	}
	/**
	 * @return Returns the numRegCorrectos.
	 */
	public Integer getNumRegCorrectos() {
		return numRegCorrectos;
	}
	/**
	 * @param numRegCorrectos The numRegCorrectos to set.
	 */
	public void setNumRegCorrectos(Integer numRegCorrectos) {
		this.numRegCorrectos = numRegCorrectos;
	}
	/**
	 * @return Returns the numRegErroneos.
	 */
	public Integer getNumRegErroneos() {
		return numRegErroneos;
	}
	/**
	 * @param numRegErroneos The numRegErroneos to set.
	 */
	public void setNumRegErroneos(Integer numRegErroneos) {
		this.numRegErroneos = numRegErroneos;
	}
	/**
	 * @return Returns the numRegProcesados.
	 */
	public Integer getNumRegProcesados() {
		return numRegProcesados;
	}
	/**
	 * @param numRegProcesados The numRegProcesados to set.
	 */
	public void setNumRegProcesados(Integer numRegProcesados) {
		this.numRegProcesados = numRegProcesados;
	}
	/**
	 * @return Returns the obsProceso.
	 */
	public String getObsProceso() {
		return obsProceso;
	}
	/**
	 * @param obsProceso The obsProceso to set.
	 */
	public void setObsProceso(String obsProceso) {
		this.obsProceso = obsProceso;
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
}
