package biz.belcorp.ssicc.web.scsicc.form;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACPedidoZonaForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 10/09/2014
 */
public class ReporteSACPedidoZonaForm extends BaseReporteForm{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The codigo pais. */
	private String codigoPais;
	
	/** The region list. */
	private String[] regionList;
	
	/** The zona list. */
	private String[] zonaList;	
	
	/** The descripcion region list. */
	private String descripcionRegionList;
	
	/** The descripcion zona list. */
	private String descripcionZonaList;
	
	/** The codigo periodo. */
	private String codigoPeriodo;
	
	
	/**
	 * @return Returns the codigoPais.
	 */	
	public String getCodigoPais() {
		return this.codigoPais;
	}
	
	
	/**
	 * @return Returns the codigoPeriodo.
	 */	
	public String getCodigoPeriodo() {
		return this.codigoPeriodo;
	}
	
	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return this.descripcionRegionList;
	}
	
	/**
	 * @return Returns the descripcionZonaList.
	 */
	public String getDescripcionZonaList() {
		return this.descripcionZonaList;
	}
	
	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return this.regionList;
	}
	
	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return this.zonaList;
	}
	
	
	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @param descripcionZonaList
	 *            The descripcionZonaList to set.
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		String temp = StringUtils.replace(descripcionZonaList, "&&", "\n");
		this.descripcionZonaList = temp;
	}
	
	/**
	 * @param regionList     The regionList to set.
	 * 
	 */	
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}
	
	/**
	 * @param zonaList
	 *            The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	
}
