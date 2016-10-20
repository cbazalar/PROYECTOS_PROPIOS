package biz.belcorp.ssicc.web.spusicc.gen.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazPaqueteForm;

public class ProcesoGENProcesarCierreRegionForm extends BaseInterfazPaqueteForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String codigoMarca;
	private String codigoCanal;

	private String codigoPeriodo;
	private String fechaFacturacion;
	
	private Date fechaFacturacionD;
	
	private String[] regiones;
	
	private boolean mostrarBotonProcesar;
	private boolean mostrarHabilitadorHidden=true;
	private String habilitador;
	
	/* INI SA PER-SiCC-2012-1120 */
	private String indicadorEjecucionSICC;
	private String indicadorSeleccionInterfaces;
	private String indicadorEjecucionInterfaces;
	
	private String indicadorSeleccionInicial;
	/* FIN SA PER-SiCC-2012-1120 */
	
	private String frecuenciaSGR;

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
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

	/**
	 * @return the regiones
	 */
	public String[] getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
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
	 * @return the mostrarHabilitadorHidden
	 */
	public boolean isMostrarHabilitadorHidden() {
		return mostrarHabilitadorHidden;
	}

	/**
	 * @param mostrarHabilitadorHidden the mostrarHabilitadorHidden to set
	 */
	public void setMostrarHabilitadorHidden(boolean mostrarHabilitadorHidden) {
		this.mostrarHabilitadorHidden = mostrarHabilitadorHidden;
	}

	/**
	 * @return the habilitador
	 */
	public String getHabilitador() {
		return habilitador;
	}

	/**
	 * @param habilitador the habilitador to set
	 */
	public void setHabilitador(String habilitador) {
		this.habilitador = habilitador;
	}

	/**
	 * @return the indicadorEjecucionSICC
	 */
	public String getIndicadorEjecucionSICC() {
		return indicadorEjecucionSICC;
	}

	/**
	 * @param indicadorEjecucionSICC the indicadorEjecucionSICC to set
	 */
	public void setIndicadorEjecucionSICC(String indicadorEjecucionSICC) {
		this.indicadorEjecucionSICC = indicadorEjecucionSICC;
	}

	/**
	 * @return the indicadorSeleccionInterfaces
	 */
	public String getIndicadorSeleccionInterfaces() {
		return indicadorSeleccionInterfaces;
	}

	/**
	 * @param indicadorSeleccionInterfaces the indicadorSeleccionInterfaces to set
	 */
	public void setIndicadorSeleccionInterfaces(String indicadorSeleccionInterfaces) {
		this.indicadorSeleccionInterfaces = indicadorSeleccionInterfaces;
	}

	/**
	 * @return the indicadorEjecucionInterfaces
	 */
	public String getIndicadorEjecucionInterfaces() {
		return indicadorEjecucionInterfaces;
	}

	/**
	 * @param indicadorEjecucionInterfaces the indicadorEjecucionInterfaces to set
	 */
	public void setIndicadorEjecucionInterfaces(String indicadorEjecucionInterfaces) {
		this.indicadorEjecucionInterfaces = indicadorEjecucionInterfaces;
	}

	/**
	 * @return the indicadorSeleccionInicial
	 */
	public String getIndicadorSeleccionInicial() {
		return indicadorSeleccionInicial;
	}

	/**
	 * @param indicadorSeleccionInicial the indicadorSeleccionInicial to set
	 */
	public void setIndicadorSeleccionInicial(String indicadorSeleccionInicial) {
		this.indicadorSeleccionInicial = indicadorSeleccionInicial;
	}

	/**
	 * @return the frecuenciaSGR
	 */
	public String getFrecuenciaSGR() {
		return frecuenciaSGR;
	}

	/**
	 * @param frecuenciaSGR the frecuenciaSGR to set
	 */
	public void setFrecuenciaSGR(String frecuenciaSGR) {
		this.frecuenciaSGR = frecuenciaSGR;
	}
}