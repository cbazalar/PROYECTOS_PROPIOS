package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


public class InterfazSAMEnviarMovimientosAlmacenForm  extends BaseInterfazForm implements Serializable {

	
	private static final long serialVersionUID = -2304579421621839956L;
	
	private String codigoPeriodo;
	private String fechaProceso;
	private String indicadorMaterial;
	private String indicadorPROL;
	private Date fechaProcesoDate;

	
	public String getIndicadorPROL() {
		return indicadorPROL;
	}

	public void setIndicadorPROL(String indicadorPROL) {
		this.indicadorPROL = indicadorPROL;
	}

	
	public String getIndicadorMaterial() {
		return indicadorMaterial;
	}

	public void setIndicadorMaterial(String indicadorMaterial) {
		this.indicadorMaterial = indicadorMaterial;
	}

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