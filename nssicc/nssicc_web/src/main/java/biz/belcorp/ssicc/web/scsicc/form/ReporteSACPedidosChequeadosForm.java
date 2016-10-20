package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * 
 * @author <a href="mailto:mazena@csigcomt.com">Marco Zena</a>
 * 
 * @struts.form name="reporteSACPedidosChequeadosForm"
 *              extends="baseReporteForm"
 */

public class ReporteSACPedidosChequeadosForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;

	private String codigoPeriodo;
	
	private String fechaFacturacion;
	
	private Date fechaFacturacionDate;
	
	private String[] regionList;
	
	private String[] zonaList;
	
	private String descripcionRegionList;
	
	private String descripcionZonaList;
	
	
	/**
	 * 
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
	 * @return Returns the fechaFacturacion.
	 */
	public String getFechaFacturacion() {
		return this.fechaFacturacion;
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
	 * @param fechaFacturacion
	 *            The fechaFacturacion to set.	  
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
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

	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
	
}
