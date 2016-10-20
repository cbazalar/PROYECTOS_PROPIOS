/**
 * 
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarBoletasRecojoForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 * 
 * @struts.form name = "interfazAPEEnviarBoletasRecojoForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazAPEEnviarBoletasRecojoForm extends BaseInterfazForm {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -1107738025028298671L;
	protected String codigoPeriodo;  
    private String fechaFacturacion;
    private Date fechaFacturacionD;
    

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
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

	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	
	


}
