package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPEnvioFacturacionElectronicaForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte </a>
 * 
 * @struts.form name = "interfazIMPEnvioFacturacionElectronicaForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazIMPEnvioFacturacionElectronicaForm extends
BaseInterfazForm implements Serializable {
	
	private String codigoPeriodo;
	private String fechaFacturacion;
	
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return this.codigoPeriodo;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return this.fechaFacturacion;
	}
	/* (non-Javadoc)
	   * @see biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	   */
	   public void reset(ActionMapping mapping, HttpServletRequest request) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
			
			this.fechaFacturacion=(sdf.format(new Date(System.currentTimeMillis())));
			sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			this.codigoPeriodo = (String) request.getSession().getAttribute("codigoPeriodo");
			if (StringUtils.isEmpty(this.codigoPeriodo)) {
				this.codigoPeriodo = periodo;
			}
		}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 * @struts.validator type="required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

}
