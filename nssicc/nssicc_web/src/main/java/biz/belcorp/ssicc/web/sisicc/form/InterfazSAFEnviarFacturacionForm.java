/*
 * Created on 04-jun-2008
 *  
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * 
 * <p>
 * <a href="InterfazSAFEnviarFacturaionForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 * @struts.form name = "interfazSAFEnviarFacturacionForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSAFEnviarFacturacionForm extends BaseInterfazForm
		implements Serializable {

	private String fecha;
	private Date fechaD;
	
	

	public Date getFechaD() {
		return fechaD;
	}

	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org
	 * .apache.struts.action.ActionMapping,
	 * javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date(System.currentTimeMillis());
		this.fecha = sdf.format(fecha);

	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 * @struts.validator type = "required"
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
