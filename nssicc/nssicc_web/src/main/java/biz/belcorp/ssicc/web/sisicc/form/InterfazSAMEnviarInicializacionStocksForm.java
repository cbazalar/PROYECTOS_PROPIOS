package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazSAMEnviarInicializacionStocksForm extends BaseInterfazForm implements Serializable {
	
	private static final long serialVersionUID = 6564142247869112754L;
	
	private String codigoAlmacen;
	private String codigoEstadoMerca;
	
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	
	public String getCodigoEstadoMerca() {
		return codigoEstadoMerca;
	}
	
	public void setCodigoEstadoMerca(String codigoEstadoMerca) {
		this.codigoEstadoMerca = codigoEstadoMerca;
	}	
}
