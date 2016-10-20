package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteCRATotalGrupoForm extends BaseReporteForm	implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8177423255120233624L;
	private String codigoPais;	
	private String anio;
	private String grupoFacturacion;
	private String [] actividad;

	public ReporteCRATotalGrupoForm() {
		this.grupoFacturacion="";
		this.actividad=null;	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anio = sdf.format(new Date(System.currentTimeMillis()));
		this.anio=anio;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
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
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * @return the grupoFacturacion
	 */
	public String getGrupoFacturacion() {
		return grupoFacturacion;
	}

	/**
	 * @param grupoFacturacion the grupoFacturacion to set
	 */
	public void setGrupoFacturacion(String grupoFacturacion) {
		this.grupoFacturacion = grupoFacturacion;
	}

	/**
	 * @return the actividad
	 */
	public String[] getActividad() {
		return actividad;
	}

	/**
	 * @param actividad the actividad to set
	 */
	public void setActividad(String[] actividad) {
		this.actividad = actividad;
	}
	
	
}

