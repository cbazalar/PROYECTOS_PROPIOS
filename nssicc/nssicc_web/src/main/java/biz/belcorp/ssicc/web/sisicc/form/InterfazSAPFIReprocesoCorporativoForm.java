package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * 
 * <p>
 * <a href="interfazSAPFIReprocesoCorporativoForm.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 * 
 * @struts.form name = "interfazSAPFIReprocesoCorporativoForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSAPFIReprocesoCorporativoForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7558203115635586307L;
	private String fechaProceso;
	private Date fechaProcesoD;
	private String[] asientoList;

	/**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @return the fechaProcesoD
	 */
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	/**
	 * @param fechaProcesoD
	 *            the fechaProcesoD to set
	 */
	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}

	/**
	 * @return the asientoList
	 */
	public String[] getAsientoList() {
		return asientoList;
	}

	/**
	 * @param asientoList
	 *            the asientoList to set
	 */
	public void setAsientoList(String[] asientoList) {
		this.asientoList = asientoList;
	}

	/**
	 * @param fechaProceso
	 *            the fechaProceso to set
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

}
