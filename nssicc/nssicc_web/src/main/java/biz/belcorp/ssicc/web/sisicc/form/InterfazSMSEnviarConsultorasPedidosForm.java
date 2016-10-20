package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form del procesamiento del consolidado de archivos de texto de OCS de la
 * Interfaz OCR.
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 * @struts.form name = "interfazSMSEnviarConsultorasPedidosForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazSMSEnviarConsultorasPedidosForm extends
		BaseInterfazForm implements Serializable {
	
	private static final long serialVersionUID = 6329568802703540006L;

	private String tipoInterfaz ;
	
	private String codigoPeriodo;
	
	private String fechaFacturacion;
	private Date fechaFacturacionDate;
	
	
	public String getTipoInterfaz() {
		return tipoInterfaz;
	}
	

	public void setTipoInterfaz(String tipoInterfaz) {
		this.tipoInterfaz = tipoInterfaz;
	}
	
	
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