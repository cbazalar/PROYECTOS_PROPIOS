package biz.belcorp.ssicc.web.spusicc.ape.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoAPEProductoNoalmPlantaPrincipalForm extends BaseEditForm {

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoSAP;
	private String codigoProducto;
	private String descripcionProducto;
	private String codigoEmpresaExterna;
	private String indicadorImprime;
	
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
	* @return the codigoSAP
	*/
	public String getCodigoSAP() {
		return codigoSAP;
	}
	/**
	* @param codigoSAP the codigoSAP to set
	*/
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	/**
	* @return the codigoProducto
	*/
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	* @param codigoProducto the codigoProducto to set
	*/
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	* @return the descripcionProducto
	*/
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	/**
	* @param descripcionProducto the descripcionProducto to set
	*/
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	/**
	* @return the codigoEmpresaExterna
	*/
	public String getCodigoEmpresaExterna() {
		return codigoEmpresaExterna;
	}
	/**
	* @param codigoEmpresaExterna the codigoEmpresaExterna to set
	*/
	public void setCodigoEmpresaExterna(String codigoEmpresaExterna) {
		this.codigoEmpresaExterna = codigoEmpresaExterna;
	}
	/**
	* @return the indicadorImprime
	*/
	public String getIndicadorImprime() {
		return indicadorImprime;
	}
	/**
	* @param indicadorImprime the indicadorImprime to set
	*/
	public void setIndicadorImprime(String indicadorImprime) {
		this.indicadorImprime = indicadorImprime;
	}
	/**
	* @return the serialversionuid
	*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}