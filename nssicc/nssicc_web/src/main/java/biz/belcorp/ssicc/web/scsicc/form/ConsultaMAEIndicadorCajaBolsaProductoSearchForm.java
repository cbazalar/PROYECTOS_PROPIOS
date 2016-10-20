package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaMAEIndicadorCajaBolsaProductoSearchForm extends
		BaseSearchForm implements Serializable {

	private static final long serialVersionUID = -3653177861767195401L;

	private String codigoPais;
	private String codigoSAP;
	private String indicador;

	public ConsultaMAEIndicadorCajaBolsaProductoSearchForm() {
		this.codigoSAP = "";
		this.indicador = "";
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
}
