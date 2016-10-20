package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

public class DatosCuv implements Serializable{
	
	private String codigoVenta;
	private String numeroUnidades;
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
	 * @return the numeroUnidades
	 */
	public String getNumeroUnidades() {
		return numeroUnidades;
	}
	/**
	 * @param numeroUnidades the numeroUnidades to set
	 */
	public void setNumeroUnidades(String numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}
	
	

}
