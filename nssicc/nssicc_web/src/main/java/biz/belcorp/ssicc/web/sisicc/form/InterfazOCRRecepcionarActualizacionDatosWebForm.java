package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazOCRRecepcionarActualizacionDatosCorporativaForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 * 
 * @struts.form name = "interfazOCRRecepcionarActualizacionDatosWebForm" extends = "baseInterfazForm"
 */
public class InterfazOCRRecepcionarActualizacionDatosWebForm extends BaseInterfazForm	implements Serializable {

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