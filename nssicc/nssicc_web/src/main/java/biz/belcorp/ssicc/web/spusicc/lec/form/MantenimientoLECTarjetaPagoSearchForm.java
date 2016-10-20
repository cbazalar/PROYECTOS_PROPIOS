package biz.belcorp.ssicc.web.spusicc.lec.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoLECTarjetaPagoSearchForm extends BaseSearchForm {

	
	/**
	 * JPPS
	 */
	private static final long serialVersionUID = -1058450184038076923L;
	private String codPais;
	private String numeroTarjeta;
	
	public String getCodPais() {
		return codPais;
	}
	
	public void setCodPais(String codPais) {
		this.codPais = codPais; 
	}

    public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
    
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	public void reset() {
    	
    	this.numeroTarjeta = ""; 	
    }
	
}
