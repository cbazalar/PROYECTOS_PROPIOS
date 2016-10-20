package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoRECEjecucionMensajesReclamosForm extends BaseProcesoForm
implements Serializable {

	private static final long serialVersionUID = -2267511047394334550L;

	private String codigoPeriodo;
	
	private String codigoMarca;
	
	private String codigoCanal;

	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	
}
