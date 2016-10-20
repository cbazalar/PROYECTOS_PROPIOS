package biz.belcorp.ssicc.web.scsicc.form;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSACSaldoConsultorasForm extends BaseReporteForm {
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String[] regionList;

	private String[] zonaList;
	
	private String[] estadoList;

	private String descripcionRegionList;

	private String descripcionZonaList;
	
	private String descripcionEstadoList;
	
	private String[] numeroPedidos;

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
	 * @param regionList     The regionList to set.
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
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	
	/**
	 * @return Returns the estadoList.
	 */
	public String[] getEstadoList() {
		return estadoList;
	}

	/**
	 * @param estadoList
	 *            The estadoList to set.
	 */
	public void setEstadoList(String[] estadoList) {
		this.estadoList = estadoList;
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
	 * @return Returns the descripcionEstadoList.
	 */
	public String getDescripcionEstadoList() {
		return descripcionEstadoList;
	}

	/**
	 * @param descripcionEstadoList
	 *            The descripcionEstadoList to set.
	 */
	public void setDescripcionEstadoList(String descripcionEstadoList) {
		String temp = StringUtils.replace(descripcionEstadoList, "&&", "\n");
		this.descripcionEstadoList = temp;
	}

	/**
	 * @return the numeroPedidos
	 */
	public String[] getNumeroPedidos() {
		return numeroPedidos;
	}

	/**
	 * @param numeroPedidos the numeroPedidos to set
	 */
	public void setNumeroPedidos(String[] numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}

}