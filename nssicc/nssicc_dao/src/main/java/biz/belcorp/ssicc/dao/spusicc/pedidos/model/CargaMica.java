/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrramirez
 *
 */
public class CargaMica extends AuditableBaseObject implements Serializable{
	
	private String codigoPais;
	
	private String codigoPeriodo;
	
	private String codigoCliente;
	
	private String tipoOrden;
	
	private String fechaSolicitud;

	private String nombreCliente;
	
	private String region;
	
	private String zona;
	
	private String indicador;
	
	public String toString(){
		return new ToStringBuilder(this)
				.append("codigoPais"+this.codigoPais)
				.append("codigoPeriodo"+this.codigoPeriodo)
				.append("codigoCliente"+this.codigoCliente)
				.append("tipoOrden"+this.tipoOrden)
				.append("fechaSolicitud"+this.fechaSolicitud)
				.append("nombreCliente"+this.nombreCliente)
				.append("region"+this.region)
				.append("zona"+this.zona)
				.append("indicador"+this.indicador).toString();
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the fechaSolicitud.
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud The fechaSolicitud to set.
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * @return Returns the tipoOrden.
	 */
	public String getTipoOrden() {
		return tipoOrden;
	}

	/**
	 * @param tipoOrden The tipoOrden to set.
	 */
	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	/**
	 * @return Returns the indicador.
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador The indicador to set.
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return Returns the nombreCliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente The nombreCliente to set.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return Returns the region.
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region The region to set.
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return Returns the zona.
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona The zona to set.
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
