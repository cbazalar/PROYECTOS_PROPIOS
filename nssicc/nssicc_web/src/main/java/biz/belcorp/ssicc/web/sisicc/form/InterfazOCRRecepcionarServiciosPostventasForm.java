package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form de la recepcion de Cabecera de servicios Postventas
 * Interfaz OCR.
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva</a>
 * 
 * @struts.form name = "interfazOCRRecepcionarServiciosPostventasForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazOCRRecepcionarServiciosPostventasForm extends BaseInterfazForm implements Serializable {
	
	
    /**
	 * 
	 */
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