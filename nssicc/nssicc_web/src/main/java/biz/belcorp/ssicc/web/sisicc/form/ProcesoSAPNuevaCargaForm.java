package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoSAPNuevaCargaForm extends BaseProcesoForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1597150001686467086L;
	private String fechaInicio;
	private Date fechaInicioD;
	private String fechaFin;
	private Date fechaFinD;
	private String tipoCambio;
	private String registrosEnviados;
	private String registrosAptos;

	public ProcesoSAPNuevaCargaForm() {

		this.fechaInicioD = new Date();
		this.fechaFinD = new Date();

	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaInicioD
	 */
	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	/**
	 * @param fechaInicioD the fechaInicioD to set
	 */
	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the fechaFinD
	 */
	public Date getFechaFinD() {
		return fechaFinD;
	}

	/**
	 * @param fechaFinD the fechaFinD to set
	 */
	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}

	/**
	 * @return the tipoCambio
	 */
	public String getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/**
	 * @return the registrosEnviados
	 */
	public String getRegistrosEnviados() {
		return registrosEnviados;
	}

	/**
	 * @param registrosEnviados the registrosEnviados to set
	 */
	public void setRegistrosEnviados(String registrosEnviados) {
		this.registrosEnviados = registrosEnviados;
	}

	/**
	 * @return the registrosAptos
	 */
	public String getRegistrosAptos() {
		return registrosAptos;
	}

	/**
	 * @param registrosAptos the registrosAptos to set
	 */
	public void setRegistrosAptos(String registrosAptos) {
		this.registrosAptos = registrosAptos;
	}
	
	

}
