/*
 * Created on 12-dic-08 16:41:02
 * biz.belcorp.ssicc.sisicc.web.form.InterfazCYZEnviarDespachoProductosForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCYZEnviarDespachoProductosForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>
 * 
 * @struts.form name = "interfazCYZEnviarDespachoProductosForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazCYZEnviarDespachoProductosForm extends
        BaseInterfazForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Holds value of property codigoPeriodo.
     */
    protected String codigoPeriodo;

    /**
     * Holds value of property fechaFacturacion.
     */
    protected String fechaFacturacion;

    public Date fechaFacturacionDate;
    
    
    public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}

	/**
     * @return the codigoPeriodo
     */
    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    /**
     * @param codigoPeriodo the codigoPeriodo to set
     * @struts.validator type="required"
     */
    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    /**
     * @return the fechaFacturacion
     */
    public String getFechaFacturacion() {
        return fechaFacturacion;
    }

    /**
     * @param fechaFacturacion the fechaFacturacion to set
     * @struts.validator type="required"
     */
    public void setFechaFacturacion(String fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

}
