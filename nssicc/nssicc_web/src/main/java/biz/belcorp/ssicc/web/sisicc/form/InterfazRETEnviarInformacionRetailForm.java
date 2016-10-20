/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazRETEnviarInformacionRetailForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos� A. Cairampoma </a>
 * 
 * @struts.form name = "interfazRETEnviarInformacionRetailForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazRETEnviarInformacionRetailForm extends
        BaseInterfazForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;
	
	private String fechaFacturacion;

	private Date fechaFacturacionDate;

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 * @struts.validator type = "required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

    
    /**
     * Default Constructor
     */
    public InterfazRETEnviarInformacionRetailForm() {
        super();     
    }
    
//    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
//		// TODO Auto-generated method stub
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		this.fechaFacturacion = sdf.format(new Date(System.currentTimeMillis()));
//		
//
//	}
}
