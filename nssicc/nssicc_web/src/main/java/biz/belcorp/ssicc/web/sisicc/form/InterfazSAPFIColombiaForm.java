/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazSAPFIColombiaForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:esanchez@sigcomt.com">Eduardo S�nchez</a>
 * 
 * @struts.form name = "interfazSAPFIColombiaForm" extends = "baseInterfazForm"
 */
public class InterfazSAPFIColombiaForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPeriodo; 
	
    private String fechaProceso;
    
    private Date fechaProcesoD;
    
    
    
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}


	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	    
    /**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}


	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
/* (non-Javadoc)
   * @see biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
   */
 
   

}
