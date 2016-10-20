package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSTOErroresValidacionForm extends BaseReporteForm implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodo;

	private String[] regionList;

	private String[] zonaList;

	private String descripcionRegionList;

	private String descripcionZonaList;

	private String fechaDesde;

	private String fechaHasta;
	
	private Date fechaDesdeD;

	private Date fechaHastaD;

	private String tipoConsulta;

	private String tipoDocumento;

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
	 * @return Returns the fechaDesde.
	 */
	public String getFechaDesde() {
		return this.fechaDesde;
	}

	/**
	 * @return Returns the fechaHasta.
	 */
	public String getFechaHasta() {
		return this.fechaHasta;
	}

	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return this.regionList;
	}

	/**
	 * @return Returns the tipoConsulta.
	 */
	public String getTipoConsulta() {
		return this.tipoConsulta;
	}

	/**
	 * @return Returns the tipoDocumento.
	 */
	public String getTipoDocumento() {
		return this.tipoDocumento;
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
	 * @param fechaDesde
	 *            The fechaDesde to set.
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @param fechaHasta
	 *            The fechaHasta to set.
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @param tipoConsulta
	 *            The tipoConsulta to set.
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * @param tipoDocumento
	 *            The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @param zonaList
	 *            The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the fechaDesdeD
	 */
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}

	/**
	 * @param fechaDesdeD the fechaDesdeD to set
	 */
	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}

	/**
	 * @return the fechaHastaD
	 */
	public Date getFechaHastaD() {
		return fechaHastaD;
	}

	/**
	 * @param fechaHastaD the fechaHastaD to set
	 */
	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}
}