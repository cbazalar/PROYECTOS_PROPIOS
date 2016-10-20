package biz.belcorp.ssicc.web.sisicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazPRYEnviarProyeccionParcialCentroForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 28/11/2014
 */
public class InterfazPRYEnviarProyeccionParcialCentroForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionDate;

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 * @struts.validator type = "required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return the fechaFacturacionDate
	 */
	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}
	/**
	 * @param fechaFacturacionDate the fechaFacturacionDate to set
	 */
	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}

}