package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazSAPFIReportesSAPFIForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7558203115635586307L;
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
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}



	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
}
