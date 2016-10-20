package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteCRATotalActividadForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 25/11/2014
 */
public class ReporteCRATotalActividadForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;	
	private String []grupoFacturacion;
	private String  actividad;
	private String anio;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 * @struts.validator type = "required"  
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * @return the actividad
	 */
	public String getActividad() {
		return actividad;
	}



	/**
	 * @param actividad the actividad to set
	 * @struts.validator type = "required"
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/**
	 * @return the grupoFacturacion
	 */
	public String[] getGrupoFacturacion() {
		return grupoFacturacion;
	}



	/**
	 * @param grupoFacturacion the grupoFacturacion to set
	 * @struts.validator type = "required"
	 */
	public void setGrupoFacturacion(String[] grupoFacturacion) {
		this.grupoFacturacion = grupoFacturacion;
	}






}
