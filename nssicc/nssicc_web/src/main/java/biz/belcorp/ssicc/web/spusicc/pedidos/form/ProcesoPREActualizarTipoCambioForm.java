package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPREActualizarTipoCambioForm extends BaseProcesoForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5748559351626126423L;
	
	private String codigoPeriodo;
	private String codigoPeriodoAnterior;
	private String tasaCambioActual;
	private String tasaCambioNueva;
	
	private boolean mostrarBotonProcesar;

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
	 * @return the tasaCambioActual
	 */
	public String getTasaCambioActual() {
		return tasaCambioActual;
	}

	/**
	 * @param tasaCambioActual the tasaCambioActual to set
	 */
	public void setTasaCambioActual(String tasaCambioActual) {
		this.tasaCambioActual = tasaCambioActual;
	}

	/**
	 * @return the tasaCambioNueva
	 */
	public String getTasaCambioNueva() {
		return tasaCambioNueva;
	}

	/**
	 * @param tasaCambioNueva the tasaCambioNueva to set
	 */
	public void setTasaCambioNueva(String tasaCambioNueva) {
		this.tasaCambioNueva = tasaCambioNueva;
	}

	/**
	 * @return the mostrarBotonProcesar
	 */
	public boolean isMostrarBotonProcesar() {
		return mostrarBotonProcesar;
	}

	/**
	 * @param mostrarBotonProcesar the mostrarBotonProcesar to set
	 */
	public void setMostrarBotonProcesar(boolean mostrarBotonProcesar) {
		this.mostrarBotonProcesar = mostrarBotonProcesar;
	}

	/**
	 * @return the codigoPeriodoAnterior
	 */
	public String getCodigoPeriodoAnterior() {
		return codigoPeriodoAnterior;
	}

	/**
	 * @param codigoPeriodoAnterior the codigoPeriodoAnterior to set
	 */
	public void setCodigoPeriodoAnterior(String codigoPeriodoAnterior) {
		this.codigoPeriodoAnterior = codigoPeriodoAnterior;
	}		
	
}
