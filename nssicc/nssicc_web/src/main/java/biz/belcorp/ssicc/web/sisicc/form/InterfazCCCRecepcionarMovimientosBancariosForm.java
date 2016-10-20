package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form de la recepci�n de Movimientos Bancarios para la Interfaz BAN.
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio</a>
 * 
 * @struts.form name = "interfazCCCRecepcionarMovimientosBancariosForm" extends ="BaseInterfazForm"
 *              
 */
public class InterfazCCCRecepcionarMovimientosBancariosForm extends
	BaseInterfazForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoSociedad;
	
	private String fechaGenerar;
	
	private Date fechaGenerarDate;
	
	/**
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad the codigoSociedad to set
	 * @struts.validator type="required"
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
	/**
	 * @return Returns the fechaGenerar.
	 */
	public String getFechaGenerar() {
		return fechaGenerar;
	}

	/**
	 * @param fechaGenerar The fechaGenerar to set.
     * @struts.validator type="required"
     * @struts.validator type="date"
     * @struts.validator-var name="datePatternStrict"
     *                       value="${defaultDatePattern}"
	 */
	public void setFechaGenerar(String fechaGenerar) {
		this.fechaGenerar = fechaGenerar;
	}

	public Date getFechaGenerarDate() {
		return fechaGenerarDate;
	}

	public void setFechaGenerarDate(Date fechaGenerarDate) {
		this.fechaGenerarDate = fechaGenerarDate;
	}	
}