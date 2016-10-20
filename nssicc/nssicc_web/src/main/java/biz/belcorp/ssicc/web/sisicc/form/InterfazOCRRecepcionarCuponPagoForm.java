package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazOCRRecepcionarCuponPagoForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 05/12/2014
 */
public class InterfazOCRRecepcionarCuponPagoForm extends BaseInterfazForm {
	
	private static final long serialVersionUID = 1L;
	
	private String numLoteSTO;

	/**
	 * @return Returns the numLoteSTO.
	 */
	public String getNumLoteSTO() {
		return numLoteSTO;
	}

	/**
	 * @param numLoteSTO The numLoteSTO to set.
	 */
	public void setNumLoteSTO(String numLoteSTO) {
		this.numLoteSTO = numLoteSTO;
	}
    
	
}