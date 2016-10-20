/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazPRIEnviarArchivosOCRForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Principe</a>
 * 
 * @struts.form name = "interfazPRIEnviarArchivosOCRForm" extends = "baseInterfazForm"
 */
public class InterfazPRIEnviarArchivosOCRForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045793065276918060L;

	private String[] codigoInterfacesEmpaquetadas;
    
    private String codigoPeriodo;

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
	
	/**
     * @return Returns the codigoPeriodo.
     */
    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    /**
     * @param codigoPeriodo The codigoPeriodo to set.
     * @struts.validator type = "required" 
     */
    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

}
