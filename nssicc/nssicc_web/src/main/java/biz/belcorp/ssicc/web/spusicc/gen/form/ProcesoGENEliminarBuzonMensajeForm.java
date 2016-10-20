package biz.belcorp.ssicc.web.spusicc.gen.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class ProcesoGENEliminarBuzonMensajeForm extends BaseInterfazForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoIdiomaISO;
	private String periodoProceso;
	private String fechaProceso;
	private Date fechaProcesoDate;
	private String indicadorProceso;

	private String codigoPeriodoActual;
	private String habilitadorHidden;
	private String indicadorModEducacion;
	/* INI JJ PER-SiCC-2012-0361 */
	private String indTipoValid;
	private String indTipoPrev;
	
	private String codigoPrograma;
	private String frecuenciaSGR;

	private String codigoConexionExterna;
	private String grupoRegion;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
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
	 * @param codigoIdiomaISO
	 *            the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return the periodoProceso
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}

	/**
	 * @param periodoProceso
	 *            the periodoProceso to set
	 */
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}

	/**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso
	 *            the fechaProceso to set
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return the fechaProcesoDate
	 */
	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	/**
	 * @param fechaProcesoDate
	 *            the fechaProcesoDate to set
	 */
	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}

	/**
	 * @return the indicadorProceso
	 */
	public String getIndicadorProceso() {
		return indicadorProceso;
	}

	/**
	 * @param indicadorProceso
	 *            the indicadorProceso to set
	 */
	public void setIndicadorProceso(String indicadorProceso) {
		this.indicadorProceso = indicadorProceso;
	}

	/**
	 * @return the codigoPeriodoActual
	 */
	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}

	/**
	 * @param codigoPeriodoActual
	 *            the codigoPeriodoActual to set
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
	 * @param habilitadorHidden
	 *            the habilitadorHidden to set
	 */
	public void setHabilitadorHidden(String habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
	}

	/**
	 * @return the indicadorModEducacion
	 */
	public String getIndicadorModEducacion() {
		return indicadorModEducacion;
	}

	/**
	 * @param indicadorModEducacion
	 *            the indicadorModEducacion to set
	 */
	public void setIndicadorModEducacion(String indicadorModEducacion) {
		this.indicadorModEducacion = indicadorModEducacion;
	}

	/**
	 * @return the indTipoValid
	 */
	public String getIndTipoValid() {
		return indTipoValid;
	}

	/**
	 * @param indTipoValid
	 *            the indTipoValid to set
	 */
	public void setIndTipoValid(String indTipoValid) {
		this.indTipoValid = indTipoValid;
	}

	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma
	 *            the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the frecuenciaSGR
	 */
	public String getFrecuenciaSGR() {
		return frecuenciaSGR;
	}

	/**
	 * @param frecuenciaSGR
	 *            the frecuenciaSGR to set
	 */
	public void setFrecuenciaSGR(String frecuenciaSGR) {
		this.frecuenciaSGR = frecuenciaSGR;
	}

	/**
	 * @return the codigoConexionExterna
	 */
	public String getCodigoConexionExterna() {
		return codigoConexionExterna;
	}

	/**
	 * @param codigoConexionExterna
	 *            the codigoConexionExterna to set
	 */
	public void setCodigoConexionExterna(String codigoConexionExterna) {
		this.codigoConexionExterna = codigoConexionExterna;
	}

	/**
	 * @return the grupoRegion
	 */
	public String getGrupoRegion() {
		return grupoRegion;
	}

	/**
	 * @param grupoRegion
	 *            the grupoRegion to set
	 */
	public void setGrupoRegion(String grupoRegion) {
		this.grupoRegion = grupoRegion;
	}

	/**
	 * @return the indTipoPrev
	 */
	public String getIndTipoPrev() {
		return indTipoPrev;
	}

	/**
	 * @param indTipoPrev the indTipoPrev to set
	 */
	public void setIndTipoPrev(String indTipoPrev) {
		this.indTipoPrev = indTipoPrev;
	}
	
	
}