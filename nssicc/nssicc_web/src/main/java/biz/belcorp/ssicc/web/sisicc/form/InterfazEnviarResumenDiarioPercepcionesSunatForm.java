package biz.belcorp.ssicc.web.sisicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazPEREnviarArchivoPDTForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 01/12/2014
 */
public class InterfazEnviarResumenDiarioPercepcionesSunatForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	private String fechaDesde;	
	private Date fechaDesdeD;
	
	
	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	/**
	 * @return the fechaDesdeD
	 */
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}
	/**
	 * @param fechaDesdeD the fechaDesdeD to set
	 */
	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}	

}
