package biz.belcorp.ssicc.web.edu.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoEDUCalificacionEnviarAptasAutomaticaForm extends BaseProcesoForm  implements Serializable {

	private static final long serialVersionUID = 2636944107550461005L;

	private String codigoPais;
	
	private String codigoEmpresa;
	
	private String codigoPeriodo;
	
	private String fechaFacturacion;
	
	private String indicadorRecodificacion;
	
	private String indicadorBloqueo;
	
	private String indicadorRegistroPlanillasNoProcesadas;
	
	private String indicadorConsultoraRezagada;

	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the fechaFacturacion.
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion The fechaFacturacion to set.
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return Returns the indicadorRecodificacion.
	 */
	public String getIndicadorRecodificacion() {
		return indicadorRecodificacion;
	}

	/**
	 * @param indicadorRecodificacion The indicadorRecodificacion to set.
	 */
	public void setIndicadorRecodificacion(String indicadorRecodificacion) {
		this.indicadorRecodificacion = indicadorRecodificacion;
	}

	/**
	 * @return Returns the indicadorBloqueo.
	 */
	public String getIndicadorBloqueo() {
		return indicadorBloqueo;
	}

	/**
	 * @param indicadorBloqueo The indicadorBloqueo to set.
	 */
	public void setIndicadorBloqueo(String indicadorBloqueo) {
		this.indicadorBloqueo = indicadorBloqueo;
	}

	public String getIndicadorRegistroPlanillasNoProcesadas() {
		return indicadorRegistroPlanillasNoProcesadas;
	}

	public void setIndicadorRegistroPlanillasNoProcesadas(
			String indicadorRegistroPlanillasNoProcesadas) {
		this.indicadorRegistroPlanillasNoProcesadas = indicadorRegistroPlanillasNoProcesadas;
	}

	/**
	 * @return Returns the indicadorConsultoraRezagada.
	 */
	public String getIndicadorConsultoraRezagada() {
		return indicadorConsultoraRezagada;
	}

	/**
	 * @param indicadorConsultoraRezagada The indicadorConsultoraRezagada to set.
	 */
	public void setIndicadorConsultoraRezagada(String indicadorConsultoraRezagada) {
		this.indicadorConsultoraRezagada = indicadorConsultoraRezagada;
	}		
}