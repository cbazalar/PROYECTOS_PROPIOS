/*
 * Created on 11/04/2008
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
 * <a href="InterfazSATEnviarCronogramaZonasForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:mmacias@co.belcorp.biz">Facturacion Macias</a>
 *
 * @struts.form
 *   name = "interfazSATEnviarCronogramaZonasForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazSATEnviarCronogramaZonasForm extends BaseInterfazForm implements Serializable {
	private String fechaFacturacion;
	
	

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return this.fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	
}
