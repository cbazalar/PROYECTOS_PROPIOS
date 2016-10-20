package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class DetalleAuditoria extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2276036618608315630L;
	private String  codigoPais;
	private String  codigoEmpresa;
	private String  codigoQuery;
	
	private String  campanha;
	private Integer numItem;
	private String  codigoCliente;
	private String  codigoCurso;
	
	private String  estadoProceso;//0:error 1:exitoso
	private String  estadoCapacitacion;
	private String  ultimaCampanhaProg;
	private String  codigoPlanilla;
	
	public String toString() {
		 return new ToStringBuilder(this)
		 			.append("codigoPais", this.codigoPais)
		 			.append("codigoQuery", this.codigoQuery).append("codigoCliente", this.codigoCliente)
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
	 * @return Returns the codigoCurso.
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}
	/**
	 * @param codigoCurso The codigoCurso to set.
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	/**
	 * @return Returns the estadoCapacitacion.
	 */
	public String getEstadoCapacitacion() {
		return estadoCapacitacion;
	}
	/**
	 * @param estadoCapacitacion The estadoCapacitacion to set.
	 */
	public void setEstadoCapacitacion(String estadoCapacitacion) {
		this.estadoCapacitacion = estadoCapacitacion;
	}
	/**
	 * @return Returns the numItem.
	 */
	public Integer getNumItem() {
		return numItem;
	}
	/**
	 * @param numItem The numItem to set.
	 */
	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}
	/**
	 * @return Returns the ultimaCampanhaProg.
	 */
	public String getUltimaCampanhaProg() {
		return ultimaCampanhaProg;
	}
	/**
	 * @param ultimaCampanhaProg The ultimaCampanhaProg to set.
	 */
	public void setUltimaCampanhaProg(String ultimaCampanhaProg) {
		this.ultimaCampanhaProg = ultimaCampanhaProg;
	}
	/**
	 * @return Returns the codigoPlanilla.
	 */
	public String getCodigoPlanilla() {
		return codigoPlanilla;
	}
	/**
	 * @param codigoPlanilla The codigoPlanilla to set.
	 */
	public void setCodigoPlanilla(String codigoPlanilla) {
		this.codigoPlanilla = codigoPlanilla;
	}
	
}
