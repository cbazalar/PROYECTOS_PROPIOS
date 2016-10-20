package biz.belcorp.ssicc.web.scsicc.hip.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BusquedaHIPClienteSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */

public class BusquedaProductoSearchForm extends BaseSearchForm implements Serializable {
		
	private static final long serialVersionUID = 9035833047705655810L;
	
	private String codigoSap;
    private String descripcionCorta;
    private String codigoSapPopup;
    private String descripcionCortaPopup;
	
	public String getCodigoSap() {
		return codigoSap;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public String getCodigoSapPopup() {
		return codigoSapPopup;
	}
	public void setCodigoSapPopup(String codigoSapPopup) {
		this.codigoSapPopup = codigoSapPopup;
	}
	public String getDescripcionCortaPopup() {
		return descripcionCortaPopup;
	}
	public void setDescripcionCortaPopup(String descripcionCortaPopup) {
		this.descripcionCortaPopup = descripcionCortaPopup;
	}
	
	   
}
