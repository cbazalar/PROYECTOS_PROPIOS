/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazECMRecepcionarMovimientosAbonosConsultoraForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:mmacias@co.belcorp.biz">Carolina Macias</a>
 * 
 * @struts.form name = "interfazECMRecepcionarMovimientosAbonosConsultoraForm" extends = "BaseInterfazPaqueteForm"
 */
public class InterfazECMRecepcionarMovimientosAbonosConsultoraForm extends BaseInterfazForm 
	implements Serializable {
    
	
	private static final long serialVersionUID = 2411889236480376227L;
	
	
	private String fechaGenerar;
	private Date fechaGenerarDate;
	
	
	public String getFechaGenerar() {
		return fechaGenerar;
	}

	public Date getFechaGenerarDate() {
		return fechaGenerarDate;
	}

	public void setFechaGenerarDate(Date fechaGenerarDate) {
		this.fechaGenerarDate = fechaGenerarDate;
	}

	public void setFechaGenerar(String fechaGenerar) {
		this.fechaGenerar = fechaGenerar;
	}
}
