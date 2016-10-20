package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaRECCDRsDigitadosForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodo;

	private String [] regionList;
	
	private String []zonaList;

	private String fechaIngreso;
	
	private Date fechaIngresoD;
	
	private String [] selectedItems;
	
	private String indicadorExpress;
	
	private String tipoConsulta;
	

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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the fechaIngreso.
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso The fechaIngreso to set.
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return Returns the selectedItems.
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems The selectedItems to set.
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public String getIndicadorExpress() {
		return indicadorExpress;
	}

	public void setIndicadorExpress(String indicadorExpress) {
		this.indicadorExpress = indicadorExpress;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * @return the fechaIngresoD
	 */
	public Date getFechaIngresoD() {
		return fechaIngresoD;
	}

	/**
	 * @param fechaIngresoD the fechaIngresoD to set
	 */
	public void setFechaIngresoD(Date fechaIngresoD) {
		this.fechaIngresoD = fechaIngresoD;
	}
}