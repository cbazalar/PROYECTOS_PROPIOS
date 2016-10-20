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
 * <a href="InterfazLARRecepcionarActualizaResultadoChequeoForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a>Efernandezo</a>
 * 
 * @struts.form name = "interfazLARRecepcionarActualizaResultadoChequeoForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazLARRecepcionarActualizaResultadoChequeoForm extends
        BaseInterfazForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -481839699116260668L;

	private String codigoMarca; 
	
    private String codigoCanal;

    private String codigoAcceso;

    private String codigoSubacceso;

    public String getCodigoAcceso() {
        return codigoAcceso;
    }
	/**
	 * @struts.validator type = "required"
	 */
    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

    public String getCodigoCanal() {
        return codigoCanal;
    }
	/**
	 * @struts.validator type = "required"
	 */
    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }

    public String getCodigoSubacceso() {
        return codigoSubacceso;
    }
	/**
	 * @struts.validator type = "required"
	 */
    public void setCodigoSubacceso(String codigoSubacceso) {
        this.codigoSubacceso = codigoSubacceso;
    }

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
    
    /**
     * Default Constructor
     */
    public InterfazLARRecepcionarActualizaResultadoChequeoForm() {

        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        this.codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
        this.codigoSubacceso = Constants.CODIGO_SUBACCESO_DEFAULT;
    }

}
