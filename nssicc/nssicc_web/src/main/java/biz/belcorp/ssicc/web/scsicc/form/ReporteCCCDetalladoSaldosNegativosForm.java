package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCDetalladoSaldosNegativosForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoSociedad;
    					
	private String[] regionList;

	private String[] zonaList;
	
	private String descripcionRegionList;

	private String descripcionZonaList;
		
	private String tipoVista;
	
	private String importeDesde;
	
	private String diasAntiguedad;
	
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
	 * 
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 *            The CodigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
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
	 * 
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
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
	 * 
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
		
	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	/**
	 * @param descripcionRegionList The descripcionRegionList to set.
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&","\n" );
		this.descripcionRegionList = temp;
	}
	
	
	/**
	 * @return Returns the descripcionZonaList.
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	
	/**
	 * @param descripcionZonaList The descripcionZonaList to set.
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		String temp = StringUtils.replace(descripcionZonaList, "&&","\n" );
		this.descripcionZonaList = temp;
	}

	
	/**
	 * 
	 * @return Returns the tipoVista.
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**	 
	 * @param tipoVista
	 *            The tipoVista to set.
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return the importeDesde
	 */
	public String getImporteDesde() {
		return importeDesde;
	}

	/**
	 * @param importeDesde
	 *            The importeDesde to set.
	 */
	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * @return the diasAntiguedad
	 */
	public String getDiasAntiguedad() {
		return diasAntiguedad;
	}

	/**
	 * @param diasAntiguedad the diasAntiguedad to set
	 */
	public void setDiasAntiguedad(String diasAntiguedad) {
		this.diasAntiguedad = diasAntiguedad;
	}
}