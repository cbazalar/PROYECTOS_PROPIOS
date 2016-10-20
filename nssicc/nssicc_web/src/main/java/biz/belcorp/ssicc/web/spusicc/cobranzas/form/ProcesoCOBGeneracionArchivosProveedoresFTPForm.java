package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * The Class ProcesoCOBGeneracionReportesFFVVFTPForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 10/02/2015
 */
public class ProcesoCOBGeneracionArchivosProveedoresFTPForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}			
}