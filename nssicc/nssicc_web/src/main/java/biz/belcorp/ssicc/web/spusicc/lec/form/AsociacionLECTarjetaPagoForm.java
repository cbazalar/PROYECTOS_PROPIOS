package biz.belcorp.ssicc.web.spusicc.lec.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class AsociacionLECTarjetaPagoForm extends BaseEditForm{

	/**
	 * JPPS
	 */
	private static final long serialVersionUID = 8449099305549654843L;
	private String codigoLiderGrilla;
	private String codigoLider;
	private String codigoTarjetaGrilla;
	private String numeroTarjeta;
	private String nombreLider;
	private String nAsig;
	private String nEnvi;
	private String tipoAsociacion;
	
	/**
	 * @return the codigoLiderGrilla
	 */
	public String getCodigoLiderGrilla() {
		return codigoLiderGrilla;
	}
	/**
	 * @param codigoLiderGrilla the codigoLiderGrilla to set
	 */
	public void setCodigoLiderGrilla(String codigoLiderGrilla) {
		this.codigoLiderGrilla = codigoLiderGrilla;
	}
	/**
	 * @return the codigoLider
	 */
	public String getCodigoLider() {
		return codigoLider;
	}
	/**
	 * @param codigoLider the codigoLider to set
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoLider(String codigoLider) {
		this.codigoLider = codigoLider;
	}
	/**
	 * @return the codigoTarjetaGrilla
	 */
	public String getCodigoTarjetaGrilla() {
		return codigoTarjetaGrilla;
	}
	/**
	 * @param codigoTarjetaGrilla the codigoTarjetaGrilla to set
	 */
	public void setCodigoTarjetaGrilla(String codigoTarjetaGrilla) {
		this.codigoTarjetaGrilla = codigoTarjetaGrilla;
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
	 * @return the nombreLider
	 */
	public String getNombreLider() {
		return nombreLider;
	}
	/**
	 * @param nombreLider the nombreLider to set
	 */
	public void setNombreLider(String nombreLider) {
		this.nombreLider = nombreLider;
	}
	
	/**
	 * @return the nAsig
	 */
	public String getnAsig() {
		return nAsig;
	}
	/**
	 * @param nAsig the nAsig to set
	 */
	public void setnAsig(String nAsig) {
		this.nAsig = nAsig;
	}
	/**
	 * @return the nEnvi
	 */
	public String getnEnvi() {
		return nEnvi;
	}
	/**
	 * @param nEnvi the nEnvi to set
	 */
	public void setnEnvi(String nEnvi) {
		this.nEnvi = nEnvi;
	}
	/**
	 * @return the tipoAsociacion
	 */
	public String getTipoAsociacion() {
		return tipoAsociacion;
	}
	/**
	 * @param nEnvi the tipoAsociacion to set
	 */
	public void setTipoAsociacion(String tipoAsociacion) {
		this.tipoAsociacion = tipoAsociacion;
	}
	
	
	public void reset() {
    	
    	this.codigoTarjetaGrilla = "";
    	this.numeroTarjeta = ""; 	
    	this.codigoLider= "";
    	this.nombreLider= "";
    	this.nAsig="0";
    	this.nEnvi="0";
    	this.tipoAsociacion="";
    }
	
}
