package biz.belcorp.ssicc.web.spusicc.gen.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoGENProcesarGP4Form extends BaseInterfazForm implements Serializable{

	private static final long serialVersionUID = -512984909566050255L;
	
	private String codigoPais;
	private String codigoIdiomaISO;
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionD;
	private String codigoPeriodoActual;
	private String habilitadorHidden;
	
	private String fechaInicio;
	private String fechaFin;

	private String validarFechaFacturacion;
	
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
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}
	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
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
	 * @return the codigoPeriodoActual
	 */
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}
	/**
	 * @param codigoPeriodoActual the codigoPeriodoActual to set
	 */
	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}
	/**
	 * @return the habilitadorHidden
	 */
	public String getHabilitadorHidden() {
		return habilitadorHidden;
	}
	/**
	 * @param habilitadorHidden the habilitadorHidden to set
	 */
	public void setHabilitadorHidden(String habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
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
	 * @return the validarFechaFacturacion
	 */
	public String getValidarFechaFacturacion() {
		return validarFechaFacturacion;
	}
	/**
	 * @param validarFechaFacturacion the validarFechaFacturacion to set
	 */
	public void setValidarFechaFacturacion(String validarFechaFacturacion) {
		this.validarFechaFacturacion = validarFechaFacturacion;
	}	
}