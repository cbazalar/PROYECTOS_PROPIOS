package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOBSaldosPendientesForm extends BaseReporteForm implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodoInicial;

	private String codigoPeriodoFinal;

	private String[] regionList;

	private String[] zonaList;

	private String[] territorioList;

	private String[] seccionList;

	private String descripcionRegionList;

	private String descripcionZonaList;

	private String descripcionTerritorioList;

	private String descripcionSeccionList;

	private String flagCodigo;

	private String flagTerritorio;

	private String flagZona;

	private boolean mostrarBotonExcel;

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
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal
	 *            The codigoPeriodoFinal to set.
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return Returns the codigoPeriodoInicial.
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial
	 *            The codigoPeriodoInicial to set.
	 * 
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * @param descripcionRegionList
	 *            The descripcionRegionList to set.
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&", "\n");
		this.descripcionRegionList = temp;
	}

	/**
	 * @return Returns the descripcionTerritorioList.
	 */
	public String getDescripcionTerritorioList() {
		return descripcionTerritorioList;
	}

	/**
	 * @param descripcionTerritorioList
	 *            The descripcionTerritorioList to set.
	 */
	public void setDescripcionTerritorioList(String descripcionTerritorioList) {
		String temp = StringUtils
				.replace(descripcionTerritorioList, "&&", "\n");
		this.descripcionTerritorioList = temp;
	}

	/**
	 * @return Returns the descripcionZonaList.
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	/**
	 * @param descripcionZonaList
	 *            The descripcionZonaList to set.
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		String temp = StringUtils.replace(descripcionZonaList, "&&", "\n");
		this.descripcionZonaList = temp;
	}

	/**
	 * @return Returns the descripcionSeccionList.
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}

	/**
	 * @param descripcionSeccionList
	 *            The descripcionSeccionList to set.
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		String temp = StringUtils.replace(descripcionSeccionList, "&&", "\n");
		this.descripcionSeccionList = temp;
	}

	/**
	 * @return Returns the seccionList.
	 */
	public String[] getSeccionList() {
		return seccionList;
	}

	/**
	 * @param seccionList
	 *            The seccionList to set.
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}

	/**
	 * @return Returns the flagCodigo.
	 */
	public String getFlagCodigo() {
		return flagCodigo;
	}

	/**
	 * @param flagCodigo
	 *            The flagCodigo to set.
	 */
	public void setFlagCodigo(String flagCodigo) {
		this.flagCodigo = flagCodigo;
	}

	/**
	 * @return Returns the flagTerritorio.
	 */
	public String getFlagTerritorio() {
		return flagTerritorio;
	}

	/**
	 * @param flagTerritorio
	 *            The flagTerritorio to set.
	 */
	public void setFlagTerritorio(String flagTerritorio) {
		this.flagTerritorio = flagTerritorio;
	}

	/**
	 * @return Returns the flagZona.
	 */
	public String getFlagZona() {
		return flagZona;
	}

	/**
	 * @param flagZona
	 *            The flagZona to set.
	 */
	public void setFlagZona(String flagZona) {
		this.flagZona = flagZona;
	}

	/**
	 * @return the mostrarBotonExcel
	 */
	public boolean isMostrarBotonExcel() {
		return mostrarBotonExcel;
	}

	/**
	 * @param mostrarBotonExcel
	 *            the mostrarBotonExcel to set
	 */
	public void setMostrarBotonExcel(boolean mostrarBotonExcel) {
		this.mostrarBotonExcel = mostrarBotonExcel;
	}
}