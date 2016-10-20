package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazSAMEnviarMovimientosAlmacenSiccForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 * @struts.form name = "interfazSAMEnviarMovimientosAlmacenSiccForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSAMEnviarMovimientosAlmacenSiccForm extends BaseInterfazForm implements Serializable {

	
	private static final long serialVersionUID = 5847623009430840475L;
	
	private String codigoPeriodo;
	private String fechaProceso;
	private Date fechaProcesoDate;


	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	  		
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}
	
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}
	
}