package biz.belcorp.ssicc.web.scsicc.mav.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class ConsultaMAVAbastecimientoMaterialSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 03/02/2015
 */
public class ConsultaMAVAbastecimientoMaterialSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String oidActividad;
	
	private String fechaFacturacion;
	
	private Date fechaFacturacionD;

	private String codigoPeriodo;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the oidActividad
	 */
	public String getOidActividad() {
		return oidActividad;
	}

	/**
	 * @param oidActividad the oidActividad to set
	 * @struts.validator type = "required"
	 */
	public void setOidActividad(String oidActividad) {
		this.oidActividad = oidActividad;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}

}
