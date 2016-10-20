package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazPEDEnviarPedidosChequearForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 12/12/2014
 */
public class InterfazPEDEnviarPedidosChequearForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	private String[] codigoInterfacesEmpaquetadas;

	/**
	 * @return Returns the codigoInterfacesEmpaquetadas.
	 */
	public String[] getCodigoInterfacesEmpaquetadas() {
		return codigoInterfacesEmpaquetadas;
	}

	/**
	 * @param codigoInterfacesEmpaquetadas
	 *            The codigoInterfacesEmpaquetadas to set.
	 */
	public void setCodigoInterfacesEmpaquetadas(
			String[] codigoInterfacesEmpaquetadas) {
		this.codigoInterfacesEmpaquetadas = codigoInterfacesEmpaquetadas;
	}

}
