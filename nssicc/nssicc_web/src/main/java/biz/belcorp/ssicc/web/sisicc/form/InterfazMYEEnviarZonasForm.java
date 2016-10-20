/*
 * Created on 09/10/2006 04:18:37 PM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarZonasForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMYEEnviarZonasForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>
 * @struts.form
 *   name = "interfazMYEEnviarZonasForm"
 *   extends = "baseInterfazForm"   
 */
public class InterfazMYEEnviarZonasForm extends BaseInterfazForm implements
        Serializable {

    private String codigoMarca;

    private String codigoCanal;

    /**
     * @return Returns the codigoCanal.
     */
    public String getCodigoCanal() {
        return codigoCanal;
    }

    /**
     * @param codigoCanal
     *            The codigoCanal to set.
     */
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
     * @param codigoMarca
     *            The codigoMarca to set.
     */
    public void setCodigoMarca(String codigoMarca) {
        this.codigoMarca = codigoMarca;
    }



}
