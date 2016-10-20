/*
 * Created on 02-nov-2005
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
 * <a href="InterfazCOMEnviarLalnForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 *  
 * @struts.form
 *   name = "interfazCOMEnviarLalnForm"
 * 	 extends = "baseInterfazForm"	
 */
public class InterfazCOMEnviarLalnForm extends BaseInterfazForm implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1278014358017067983L;

	private String codigoPeriodo;
    
    private String[] codigoInterfacesEmpaquetadas;
    
    private String codigoIdioma;
       		
    
	public String getCodigoIdioma() {
        return codigoIdioma;
    }

    public void setCodigoIdioma(String codigoIdioma) {
        this.codigoIdioma = codigoIdioma;
    }

    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    /**
     * @param codigoPeriodo The codigoPeriodo to set.
     *
     * @struts.validator type = "required"
     */

    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }


    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	//this.codigoTipoCliente = Constants.CODIGO_TIPO_CLIENTE_DEFAULT;
    }

	/**
	 * @return Returns the codigoInterfacesEmpaquetadas.
	 */
	public String[] getCodigoInterfacesEmpaquetadas() {
		return codigoInterfacesEmpaquetadas;
	}

	/**
	 * @param codigoInterfacesEmpaquetadas The codigoInterfacesEmpaquetadas to set.
	 */
	public void setCodigoInterfacesEmpaquetadas(String[] codigoInterfacesEmpaquetadas) {
		this.codigoInterfacesEmpaquetadas = codigoInterfacesEmpaquetadas;
	}

}
