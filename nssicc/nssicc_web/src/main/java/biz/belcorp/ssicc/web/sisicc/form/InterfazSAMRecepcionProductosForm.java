/*
 * Created on 20/04/2006 02:55:48 PM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazSAMRecepcionProductosForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSAMRecepcionProductosForm.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>
 * 
 * @struts.form name = "interfazSAMRecepcionProductosForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSAMRecepcionProductosForm extends BaseEditForm implements
        Serializable {

    private String codigoPais;

    private boolean flagEnvioProductos;

    /**
     * @return Returns the codigoPais.
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * @param codigoPais
     *            The codigoPais to set.
     * @struts.validator type = "required" 
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * @return Returns the flagEnvioProductos.
     */
    public boolean isFlagEnvioProductos() {
        return flagEnvioProductos;
    }

    /**
     * @param flagEnvioProductos
     *            The flagEnvioProductos to set.
     */
    public void setFlagEnvioProductos(boolean flagEnvioProductos) {
        this.flagEnvioProductos = flagEnvioProductos;
    }

    /*
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.flagEnvioProductos = false;
    }

}
