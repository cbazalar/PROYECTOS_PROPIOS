package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

public class TipoCajaProducto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3895427291450513098L;
	private String codigoPais;
	private String oidTipoCajaProducto;
	private String codigoTipoCajaProducto;
	private String descripcionTipoCajaProducto;
	
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
	 * @return the oidTipoCajaProducto
	 */
	public String getOidTipoCajaProducto() {
		return oidTipoCajaProducto;
	}
	/**
	 * @param oidTipoCajaProducto the oidTipoCajaProducto to set
	 */
	public void setOidTipoCajaProducto(String oidTipoCajaProducto) {
		this.oidTipoCajaProducto = oidTipoCajaProducto;
	}
	/**
	 * @return the codigoTipoCajaProducto
	 */
	public String getCodigoTipoCajaProducto() {
		return codigoTipoCajaProducto;
	}
	/**
	 * @param codigoTipoCajaProducto the codigoTipoCajaProducto to set
	 */
	public void setCodigoTipoCajaProducto(String codigoTipoCajaProducto) {
		this.codigoTipoCajaProducto = codigoTipoCajaProducto;
	}
	/**
	 * @return the descripcionTipoCajaProducto
	 */
	public String getDescripcionTipoCajaProducto() {
		return descripcionTipoCajaProducto;
	}
	/**
	 * @param descripcionTipoCajaProducto the descripcionTipoCajaProducto to set
	 */
	public void setDescripcionTipoCajaProducto(String descripcionTipoCajaProducto) {
		this.descripcionTipoCajaProducto = descripcionTipoCajaProducto;
	}
	
}
