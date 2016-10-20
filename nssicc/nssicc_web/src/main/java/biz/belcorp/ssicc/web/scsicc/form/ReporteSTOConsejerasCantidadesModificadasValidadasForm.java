package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSTOConsejerasCantidadesModificadasValidadasForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String fechaInicio;
	private String fechaFin;
	private Date fechaInicioD;
	private Date fechaFinD;
	private String numeroLote;
	private String[] regionList;
	private String[] zonaList;
	private String codigoCliente;
	private String fechaInicioProceso;
	private String fechaFinProceso;
	private String horaInicioCarga;
	private String horaFinCarga;
	private Date fechaInicioProcesoD;
	private Date fechaFinProcesoD;
	private Date horaInicioCargaD;
	private Date horaFinCargaD;
	private String horaInicioProceso;
	private String horaFinProceso;
	private String codigoPeriodo;

	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String[] getRegionList() {
		return regionList;
	}

	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	public String[] getZonaList() {
		return zonaList;
	}

	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getFechaInicioProceso() {
		return fechaInicioProceso;
	}

	public void setFechaInicioProceso(String fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}

	public String getFechaFinProceso() {
		return fechaFinProceso;
	}

	public void setFechaFinProceso(String fechaFinProceso) {
		this.fechaFinProceso = fechaFinProceso;
	}

	public String getHoraInicioCarga() {
		return horaInicioCarga;
	}

	public void setHoraInicioCarga(String horaInicioCarga) {
		this.horaInicioCarga = horaInicioCarga;
	}

	public String getHoraFinCarga() {
		return horaFinCarga;
	}

	public void setHoraFinCarga(String horaFinCarga) {
		this.horaFinCarga = horaFinCarga;
	}

	public String getHoraInicioProceso() {
		return horaInicioProceso;
	}

	public void setHoraInicioProceso(String horaInicioProceso) {
		this.horaInicioProceso = horaInicioProceso;
	}

	public String getHoraFinProceso() {
		return horaFinProceso;
	}

	public void setHoraFinProceso(String horaFinProceso) {
		this.horaFinProceso = horaFinProceso;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the fechaInicioProcesoD
	 */
	public Date getFechaInicioProcesoD() {
		return fechaInicioProcesoD;
	}

	/**
	 * @param fechaInicioProcesoD the fechaInicioProcesoD to set
	 */
	public void setFechaInicioProcesoD(Date fechaInicioProcesoD) {
		this.fechaInicioProcesoD = fechaInicioProcesoD;
	}

	/**
	 * @return the fechaFinProcesoD
	 */
	public Date getFechaFinProcesoD() {
		return fechaFinProcesoD;
	}

	/**
	 * @param fechaFinProcesoD the fechaFinProcesoD to set
	 */
	public void setFechaFinProcesoD(Date fechaFinProcesoD) {
		this.fechaFinProcesoD = fechaFinProcesoD;
	}

	/**
	 * @return the horaInicioCargaD
	 */
	public Date getHoraInicioCargaD() {
		return horaInicioCargaD;
	}

	/**
	 * @param horaInicioCargaD the horaInicioCargaD to set
	 */
	public void setHoraInicioCargaD(Date horaInicioCargaD) {
		this.horaInicioCargaD = horaInicioCargaD;
	}

	/**
	 * @return the horaFinCargaD
	 */
	public Date getHoraFinCargaD() {
		return horaFinCargaD;
	}

	/**
	 * @param horaFinCargaD the horaFinCargaD to set
	 */
	public void setHoraFinCargaD(Date horaFinCargaD) {
		this.horaFinCargaD = horaFinCargaD;
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
	
	
}