package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteMAEConsejerasBloqueadasDesbloqueadasForm extends
		BaseReporteForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4071225490302327222L;

	private String codigoPais;
	private String codigoPeriodoInicial;
	private String codigoPeriodoFinal;
	private String[] regionList;
	private String[] zonaList;
	private String descripcionRegionList;
	private String descripcionZonaList;
	private String fechaInicio;
	private String fechaFin;
	private Date fechaInicioD;
	private Date fechaFinD;
	private String codigoTipoReporte;
	private String codigoEstado;
	private String horaInicio;
	private String horaFin;
	private String indicadorBloqueo;
	private boolean mostrarBotonExcel;
	
	/**
	 * Datos por default
	 */
	public ReporteMAEConsejerasBloqueadasDesbloqueadasForm() {
		this.codigoPeriodoInicial = "";
		this.codigoPeriodoFinal = "";
		this.codigoTipoReporte = "T";
		this.codigoEstado = "T";
		this.indicadorBloqueo = null;
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
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial the codigoPeriodoInicial to set
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal the codigoPeriodoFinal to set
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * @param descripcionRegionList the descripcionRegionList to set
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}

	/**
	 * @return the descripcionZonaList
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	/**
	 * @param descripcionZonaList the descripcionZonaList to set
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
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

	/**
	 * @return the codigoTipoReporte
	 */
	public String getCodigoTipoReporte() {
		return codigoTipoReporte;
	}

	/**
	 * @param codigoTipoReporte the codigoTipoReporte to set
	 */
	public void setCodigoTipoReporte(String codigoTipoReporte) {
		this.codigoTipoReporte = codigoTipoReporte;
	}

	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * @return the horaInicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the horaFin
	 */
	public String getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * @return the indicadorBloqueo
	 */
	public String getIndicadorBloqueo() {
		return indicadorBloqueo;
	}

	/**
	 * @param indicadorBloqueo the indicadorBloqueo to set
	 */
	public void setIndicadorBloqueo(String indicadorBloqueo) {
		this.indicadorBloqueo = indicadorBloqueo;
	}

	/**
	 * @return the mostrarBotonExcel
	 */
	public boolean isMostrarBotonExcel() {
		return mostrarBotonExcel;
	}

	/**
	 * @param mostrarBotonExcel the mostrarBotonExcel to set
	 */
	public void setMostrarBotonExcel(boolean mostrarBotonExcel) {
		this.mostrarBotonExcel = mostrarBotonExcel;
	}
	
	
}
