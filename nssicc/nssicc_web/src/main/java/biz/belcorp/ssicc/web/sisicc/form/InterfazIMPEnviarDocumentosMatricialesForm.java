/*
 * Created on 15/05/2009 05:15:52 PM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPEnviarDocumentosMatricialesForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="cahurtado@belcorp.biz">Carlos Hurtado Ramirez </a>
 * 
 * @struts.form name = "interfazIMPEnviarDocumentosMatricialesForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazIMPEnviarDocumentosMatricialesForm extends
		BaseInterfazForm {

	private static final long serialVersionUID = -4602231829970748965L;

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
