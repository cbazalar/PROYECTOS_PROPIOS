package biz.belcorp.ssicc.web.spusicc.lec.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoLECTarjetaPagoForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6521858069160799717L;
	private String codigoTarjeta;
	private String numeroTarjeta;
	
	
	public String getCodigoTarjeta() {
		return codigoTarjeta;
	}

	public void setCodigoTarjeta(String codigoTarjeta) {
		this.codigoTarjeta = codigoTarjeta;
	}

    public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
 
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	public void reset() {
    	
    	this.codigoTarjeta = "";
    	this.numeroTarjeta = ""; 	
    }
	
}
