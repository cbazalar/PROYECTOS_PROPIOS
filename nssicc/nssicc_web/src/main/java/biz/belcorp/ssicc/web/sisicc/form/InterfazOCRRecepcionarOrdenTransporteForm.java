/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * @author <a>Dennys Oliva >Iriarte</a> 
 * <p>
 * <a href="InterfazOCRRecepcionarOrdenTransporteForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * 
 * @struts.form name = "interfazOCRRecepcionarOrdenTransporteForm" extends = "baseInterfazForm"
 */
public class InterfazOCRRecepcionarOrdenTransporteForm extends BaseInterfazForm
		implements Serializable {
	
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
