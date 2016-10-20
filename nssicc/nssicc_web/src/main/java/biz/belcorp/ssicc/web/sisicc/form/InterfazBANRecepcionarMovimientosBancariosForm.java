/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazBANRecepcionarMovimientosBancariosForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Principe</a>
 * 
 * @struts.form name = "interfazBANRecepcionarMovimientosBancariosForm" extends = "baseInterfazForm"
 */
public class InterfazBANRecepcionarMovimientosBancariosForm extends BaseInterfazForm
		implements Serializable {
    
	private String fechaGenerar;
	private Date fechaGenerarD;
	
	/**
	 * @return Returns the fechaGenerar.
	 */
	public String getFechaGenerar() {
		return fechaGenerar;
	}

	/**
	 * @param fechaGenerar The fechaGenerar to set.
     * @struts.validator type="required"
     * @struts.validator type="date"
     * @struts.validator-var name="datePatternStrict"
     *                       value="${defaultDatePattern}"
	 */
	public void setFechaGenerar(String fechaGenerar) {
		this.fechaGenerar = fechaGenerar;
	}

	public Date getFechaGenerarD() {
		return fechaGenerarD;
	}

	public void setFechaGenerarD(Date fechaGenerarD) {
		this.fechaGenerarD = fechaGenerarD;
	}
}
