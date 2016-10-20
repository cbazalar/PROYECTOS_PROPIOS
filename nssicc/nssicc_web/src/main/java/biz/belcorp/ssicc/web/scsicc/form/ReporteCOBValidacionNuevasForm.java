package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOBValidacionNuevasForm extends BaseReporteForm implements Serializable{
	
	private static final long serialVersionUID = 4165101567522982305L;	
	
	private String codigoPais;
	private String codPeriodoInicial;	
	private String codPeriodoFinal;					
	private String[] regionList;
	private String[] zonaList;
	private String[] seccionList;
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
	 * @return the codPeriodoInicial
	 */
	public String getCodPeriodoInicial() {
		return codPeriodoInicial;
	}
	/**
	 * @param codPeriodoInicial the codPeriodoInicial to set
	 */
	public void setCodPeriodoInicial(String codPeriodoInicial) {
		this.codPeriodoInicial = codPeriodoInicial;
	}
	/**
	 * @return the codPeriodoFinal
	 */
	public String getCodPeriodoFinal() {
		return codPeriodoFinal;
	}
	/**
	 * @param codPeriodoFinal the codPeriodoFinal to set
	 */
	public void setCodPeriodoFinal(String codPeriodoFinal) {
		this.codPeriodoFinal = codPeriodoFinal;
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
	 * @return the seccionList
	 */
	public String[] getSeccionList() {
		return seccionList;
	}
	/**
	 * @param seccionList the seccionList to set
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}
	
	

}
