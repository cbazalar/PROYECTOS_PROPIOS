/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazSAMEnviarReservaPROLForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 * @struts.form name = "interfazSAMEnviarReservaPROLForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSAMEnviarReservaPROLForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = -6085594610472895174L;
	
	private String codigoPeriodo;
	private String fechaProceso;
	private String indicadorPROL;
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
	
	public String getIndicadorPROL() {
		return indicadorPROL;
	}
	
	public void setIndicadorPROL(String indicadorPROL) {
		this.indicadorPROL = indicadorPROL;
	}

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}
	

}