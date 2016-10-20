package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRECMotivoDevolucionSearchForm extends BaseSearchForm{

	private static final long serialVersionUID = 1231805425620652285L;
	
	private String codigoPais;
	private String tipMotDev;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getTipMotDev() {
		return tipMotDev;
	}
	
	public void setTipMotDev(String tipMotDev) {
		this.tipMotDev = tipMotDev;
	}

}
