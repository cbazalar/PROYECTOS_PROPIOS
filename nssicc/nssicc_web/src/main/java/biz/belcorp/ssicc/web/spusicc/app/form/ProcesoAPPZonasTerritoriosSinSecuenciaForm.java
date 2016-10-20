package biz.belcorp.ssicc.web.spusicc.app.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoAPPZonasTerritoriosSinSecuenciaForm extends BaseProcesoForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
}
