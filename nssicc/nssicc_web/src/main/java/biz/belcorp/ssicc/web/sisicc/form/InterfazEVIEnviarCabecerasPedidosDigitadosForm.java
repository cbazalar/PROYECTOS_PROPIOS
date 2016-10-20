/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazEVIEnviarCabecerasPedidosDigitadosForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos� A. Cairampoma</a>
 * 
 * @struts.form name = "interfazEVIEnviarCabecerasPedidosDigitadosForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazEVIEnviarCabecerasPedidosDigitadosForm extends BaseInterfazForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String periodo ;
	
	private String fechaFacturacion ;
	
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @struts.validator type = "required"
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @struts.validator type = "required"
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

}