/*
 * Created on 06-ene-09 18:11:59
 * biz.belcorp.ssicc.sisicc.web.form.InterfazCYZRecepcionarProductosSolicitadosForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCYZRecepcionarProductosSolicitadosForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>
 * 
 * @struts.form name = "interfazCYZRecepcionarProductosSolicitadosForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazCYZRecepcionarProductosSolicitadosForm extends
        BaseInterfazForm {

   
	private static final long serialVersionUID = 1697699072188667833L;
	
    protected String codigoPeriodo;    
    protected String fechaFacturacion;
    private Date fechaFacturacionDate;

    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }
   
    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public String getFechaFacturacion() {
        return fechaFacturacion;
    }
    
    public void setFechaFacturacion(String fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
    

}
