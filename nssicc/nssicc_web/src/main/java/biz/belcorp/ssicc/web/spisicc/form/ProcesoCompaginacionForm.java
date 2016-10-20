package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCompaginacionForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4379590148955791871L;
	
	protected String codigoPais;
	protected String codigoProcesoImpresion;
	protected String codigoPeriodo;
	protected String fechaFacturacion;
	protected Date fechaFacturacionD;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoProcesoImpresion() {
		return codigoProcesoImpresion;
	}
	public void setCodigoProcesoImpresion(String codigoProcesoImpresion) {
		this.codigoProcesoImpresion = codigoProcesoImpresion;
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
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	
	
	


}
