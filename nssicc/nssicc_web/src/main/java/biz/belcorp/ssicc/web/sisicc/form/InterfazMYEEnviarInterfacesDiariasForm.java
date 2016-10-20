/*
 * Created on 04-jun-2008
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
 * <a href="InterfazOCRZonasForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 * @struts.form
 *   name = "interfazMYEEnviarInterfacesDiariasForm"
 * 	 extends = "baseInterfazForm"	
*/

public class InterfazMYEEnviarInterfacesDiariasForm extends BaseInterfazForm implements Serializable {
	private String codigoMarca;
	private String codigoCanal;
	private Date fechaFacturacion;
	private String codigoPeriodo;
	
	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal The codigoCanal to set.
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
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	
	/**
	 * @return the fechaFacturacion
	 */
	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
     * @param codigoPeriodo The codigoPeriodo to set.
     * @struts.validator type="required"
     */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}		
}
