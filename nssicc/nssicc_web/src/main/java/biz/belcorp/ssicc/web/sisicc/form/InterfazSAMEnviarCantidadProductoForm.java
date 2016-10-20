package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazInterfazFLXEnvioResultadoProgramasForm.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 * 
 * @struts.form name = "interfazSAMEnviarCantidadProductoForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSAMEnviarCantidadProductoForm extends BaseInterfazForm
        implements Serializable {

	
	private static final long serialVersionUID = -5574696108060599364L;
	
	private String codigoPeriodo;
    private String fechaFacturacion;
    private Date fechaFacturacionDate;
    

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
  
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
	

   
}

