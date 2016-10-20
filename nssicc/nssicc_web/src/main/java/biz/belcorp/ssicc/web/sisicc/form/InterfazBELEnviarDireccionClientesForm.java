/*
 * Created on 02-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazBELEnviarDireccionClientesForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 * @struts.form name = "interfazBELEnviarDireccionClientesForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazBELEnviarDireccionClientesForm extends BaseInterfazForm
        implements Serializable {

    private String codigoTipoCliente;

    /**
     * @return Returns the codigoTipoCliente.
     */
    public String getCodigoTipoCliente() {
        return codigoTipoCliente;
    }

    /**
     * @param codigoTipoCliente
     *            The codigoTipoCliente to set.
     */
    public void setCodigoTipoCliente(String codigoTipoCliente) {
        this.codigoTipoCliente = codigoTipoCliente;
    }

}
