package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazOCRRecepcionarBoletaEntregaForm extends BaseInterfazForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numLoteSTO;

	/**
	 * @return the numLoteSTO
	 */
	public String getNumLoteSTO() {
		return numLoteSTO;
	}

	/**
	 * @param numLoteSTO
	 *            the numLoteSTO to set
	 */
	public void setNumLoteSTO(String numLoteSTO) {
		this.numLoteSTO = numLoteSTO;
	}
}