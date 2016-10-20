/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMYEEnviarMovimientosCuentaCorrienteForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>
 * 
 * @struts.form name = "interfazMYEEnviarMovimientosCuentaCorrienteForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazMYEEnviarMovimientosCuentaCorrienteForm extends
        BaseInterfazForm implements Serializable {

    private String codigoCanal;

    private String codigoAcceso;

    private String codigoSubacceso;

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

    public String getCodigoSubacceso() {
        return codigoSubacceso;
    }

    public void setCodigoSubacceso(String codigoSubacceso) {
        this.codigoSubacceso = codigoSubacceso;
    }
    
}
