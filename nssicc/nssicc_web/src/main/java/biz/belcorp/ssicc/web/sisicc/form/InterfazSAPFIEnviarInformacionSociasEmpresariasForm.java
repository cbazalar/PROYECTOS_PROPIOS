/**
 * 
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazSAPFIEnviarInformacionSociasEmpresariasForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 * 
 * @struts.form name = "interfazSAPFIEnviarInformacionSociasEmpresariasForm" extends = "baseInterfazForm"
 */
public class InterfazSAPFIEnviarInformacionSociasEmpresariasForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 7127381402686819133L;
	

	private String codigoPeriodo;
	
	private String fechaFacturacion;
	
	private String habilitadorHidden;
	
	private Date fechaFacturacionDate;

	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	public String getHabilitadorHidden() {
		return habilitadorHidden;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public void setHabilitadorHidden(String habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
	}

	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
	
	
}
