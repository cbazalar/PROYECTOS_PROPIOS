/**
 * 
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 * 
 */
public class InterfazSMSEnviarConsultorasPagosCCRecaudoBancarioForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fechaProceso;
	private Date dfechaProceso;

	/**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return the dfechaProceso
	 */
	public Date getDfechaProceso() {
		return dfechaProceso;
	}

	/**
	 * @param dfechaProceso the dfechaProceso to set
	 */
	public void setDfechaProceso(Date dfechaProceso) {
		this.dfechaProceso = dfechaProceso;
	}
}