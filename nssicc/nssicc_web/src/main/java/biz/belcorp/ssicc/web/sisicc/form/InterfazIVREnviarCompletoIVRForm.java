/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIVREnviarCompletoIVRForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos� A. Cairampoma </a>
 * 
 * @struts.form name = "interfazIVREnviarCompletoIVRForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazIVREnviarCompletoIVRForm extends
        BaseInterfazForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3947895406053441400L;

	private String codigoMarca; 
	
    private String codigoCanal;

    private String codigoAcceso;

	private String codigoPeriodo;
	
	
   	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

    public String getCodigoCanal() {
        return codigoCanal;
    }

    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }

   	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
    
    /**
     * Default Constructor
     */
    public InterfazIVREnviarCompletoIVRForm() {
        super();
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        this.codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
        
    }

	
    
}
