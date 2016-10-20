package biz.belcorp.ssicc.web.spusicc.lec.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class AsociacionLECTarjetaPagoSearchForm extends BaseSearchForm{

	
	/**
	 * JPPS
	 */
	private static final long serialVersionUID = 2732310070434534959L;
	private String codPais;
	private String tipoAsociacion;
	private String numeroTarjeta;
	private String estadoTarjeta;
	private String codigoRegion;
	private String codigoZona;
	private String codigoLider;
	private String codigoPeriodo;
	

	/**
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais the codPais to set
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais; 
	}
	/**
	 * @return the numeroTarjeta
	 */
    public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
    /**
	 * @param numeroTarjeta the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	
	/**
	 * @return the tipoAsociacion
	 */
	public String getTipoAsociacion() {
		return tipoAsociacion;
	}
	/**
	 * @param tipoAsociacion the tipoAsociacion to set
	 */
	public void setTipoAsociacion(String tipoAsociacion) {
		this.tipoAsociacion = tipoAsociacion;
	}
	/**
	 * @return the estadoTarjeta
	 */
	public String getEstadoTarjeta() {
		return estadoTarjeta;
	}
	/**
	 * @param estadoTarjeta the estadoTarjeta to set
	 */
	public void setEstadoTarjeta(String estadoTarjeta) {
		this.estadoTarjeta = estadoTarjeta;
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
	 * @return the codigoLider
	 */
	public String getCodigoLider() {
		return codigoLider;
	}
	/**
	 * @param codigoLider the codigoLider to set
	 */
	public void setCodigoLider(String codigoLider) {
		this.codigoLider = codigoLider;
	}
	
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public void reset() {
    	 
    	this.numeroTarjeta = ""; 
    	this.estadoTarjeta = ""; 
    	this.codigoRegion = ""; 
    	this.codigoZona = ""; 
    	this.codigoLider = ""; 
    	
    }	


}
