package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazACCEnviarNovedadesForm extends BaseInterfazForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8544642861218425816L;
	
	
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFact;
	private String tipoEnvio;
	
	
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
	public Date getFechaFact() {
		return fechaFact;
	}
	public void setFechaFact(Date fechaFact) {
		this.fechaFact = fechaFact;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	

}
