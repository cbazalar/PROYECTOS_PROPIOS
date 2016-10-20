package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazSAMRecepcionarLotesProductoForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 10/12/2014
 */
public class InterfazSAMRecepcionarLotesProductoForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	private String pesoArchivo;


	/**
	 * @return the pesoArchivo
	 */
	public String getPesoArchivo() {
		return pesoArchivo;
	}

	/**
	 * @param pesoArchivo the pesoArchivo to set
	 */
	public void setPesoArchivo(String pesoArchivo) {
		this.pesoArchivo = pesoArchivo;
	}
	
}