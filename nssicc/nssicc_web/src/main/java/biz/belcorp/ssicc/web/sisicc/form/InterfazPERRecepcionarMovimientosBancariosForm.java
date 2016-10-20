/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazPERRecepcionarMovimientosBancariosForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Principe</a>
 * 
 * @struts.form name = "interfazPERRecepcionarMovimientosBancariosForm" extends = "baseInterfazForm"
 */
public class InterfazPERRecepcionarMovimientosBancariosForm extends BaseInterfazForm
		implements Serializable {
    
	private String fechaGenerar;
	
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



	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaGenerar = sdf.format(new Date(System.currentTimeMillis()));
	}
}
