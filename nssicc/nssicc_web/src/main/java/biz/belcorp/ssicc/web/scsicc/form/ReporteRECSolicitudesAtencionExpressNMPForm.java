package biz.belcorp.ssicc.web.scsicc.form;


import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteRECSolicitudesAtencionExpressNMPForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodoInicio;

	private String codigoPeriodoFin;
	
	private Date fechaFacturacionInicio;
	
	private Date fechaFacturacionFin;
	
	private String tipoReporte;
	
	private String[] regionList;

	private String[] zonaList;

	private String[] territorioList;
	
	private String[] grupoProcesoList;
	
	private String[] operacionList;
	
	private String peridoInicioFechaInicial;
	
	private String peridoInicioFechaFinal;
	
	private String peridoFinFechaInicial;
	
	private String peridoFinFechaFinal;

	/**
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

	/**
	 * @return Returns the codigoPeriodoFin.
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin
	 *            The codigoPeriodoFin to set.
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return Returns the codigoPeriodoInicio.
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio
	 *            The codigoPeriodoInicio to set.
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

    

	/**
	 * @return the fechaFacturacionInicio
	 */
	public Date getFechaFacturacionInicio() {
		return fechaFacturacionInicio;
	}

	/**
	 * @param fechaFacturacionInicio the fechaFacturacionInicio to set
	 */
	public void setFechaFacturacionInicio(Date fechaFacturacionInicio) {
		this.fechaFacturacionInicio = fechaFacturacionInicio;
	}

	/**
	 * @return the fechaFacturacionFin
	 */
	public Date getFechaFacturacionFin() {
		return fechaFacturacionFin;
	}

	/**
	 * @param fechaFacturacionFin the fechaFacturacionFin to set
	 */
	public void setFechaFacturacionFin(Date fechaFacturacionFin) {
		this.fechaFacturacionFin = fechaFacturacionFin;
	}

	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return Returns the territorioList.
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList
	 *            The territorioList to set.
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * @return Returns the tipoReporte.
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            The tipoReporte to set.
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return Returns the grupoProcesoList.
	 */
	public String[] getGrupoProcesoList() {
		return grupoProcesoList;
	}

	/**
	 * @param grupoProcesoList
	 *            The grupoProcesoList to set.
	 */
	public void setGrupoProcesoList(String[] grupoProcesoList) {
		this.grupoProcesoList = grupoProcesoList;
	}
	
	/**
	 * @return Returns the operacionList.
	 */
	public String[] getOperacionList() {
		return operacionList;
	}

	/**
	 * @param operacionList The operacionList to set.
	 * 
	 */
	public void setOperacionList(String[] operacionList) {
		this.operacionList = operacionList;
	}

	/**
	 * @return the peridoInicioFechaInicial
	 */
	public String getPeridoInicioFechaInicial() {
		return peridoInicioFechaInicial;
	}

	/**
	 * @param peridoInicioFechaInicial the peridoInicioFechaInicial to set
	 */
	public void setPeridoInicioFechaInicial(String peridoInicioFechaInicial) {
		this.peridoInicioFechaInicial = peridoInicioFechaInicial;
	}

	/**
	 * @return the peridoInicioFechaFinal
	 */
	public String getPeridoInicioFechaFinal() {
		return peridoInicioFechaFinal;
	}

	/**
	 * @param peridoInicioFechaFinal the peridoInicioFechaFinal to set
	 */
	public void setPeridoInicioFechaFinal(String peridoInicioFechaFinal) {
		this.peridoInicioFechaFinal = peridoInicioFechaFinal;
	}

	/**
	 * @return the peridoFinFechaInicial
	 */
	public String getPeridoFinFechaInicial() {
		return peridoFinFechaInicial;
	}

	/**
	 * @param peridoFinFechaInicial the peridoFinFechaInicial to set
	 */
	public void setPeridoFinFechaInicial(String peridoFinFechaInicial) {
		this.peridoFinFechaInicial = peridoFinFechaInicial;
	}

	/**
	 * @return the peridoFinFechaFinal
	 */
	public String getPeridoFinFechaFinal() {
		return peridoFinFechaFinal;
	}

	/**
	 * @param peridoFinFechaFinal the peridoFinFechaFinal to set
	 */
	public void setPeridoFinFechaFinal(String peridoFinFechaFinal) {
		this.peridoFinFechaFinal = peridoFinFechaFinal;
	}
	


	
}
