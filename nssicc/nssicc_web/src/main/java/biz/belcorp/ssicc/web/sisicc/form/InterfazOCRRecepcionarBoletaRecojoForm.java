package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazOCRRecepcionarBoletaRecojoForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 * 
 * @struts.form name = "interfazOCRRecepcionarBoletaRecojoForm" extends = "baseInterfazForm"
 */
public class InterfazOCRRecepcionarBoletaRecojoForm extends BaseInterfazForm implements Serializable {

	private String numLoteSTO;

	/**
	 * @return the numLoteSTO
	 */
	public String getNumLoteSTO() {
		return numLoteSTO;
	}

	/**
	 * @param numLoteSTO the numLoteSTO to set
	 */
	public void setNumLoteSTO(String numLoteSTO) {
		this.numLoteSTO = numLoteSTO;
	}	
}