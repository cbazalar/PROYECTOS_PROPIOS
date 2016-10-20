package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoExcepcionesValidacionesSearchForm extends BaseSearchForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String tipoDocumento;
	private String validaciones;
	private String[] regionList;
	private String[] zonaList;
	private String codigoPeriodo;
	private String codigoConsultora;
	private String descripcionConsultora;
	private String fecha;
	private Date fechaDate;

	private String numeroFilas;
	private String[] selectedItems;
	
	private String formatoExportacion;

	public MantenimientoExcepcionesValidacionesSearchForm(){
		this.numeroFilas = "25";
	}
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @struts.validator type = "required"
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * @return the numeroFilas
	 */
	public String getNumeroFilas() {
		return numeroFilas;
	}

	/**
	 * @param numeroFilas the numeroFilas to set
	 */
	public void setNumeroFilas(String numeroFilas) {
		this.numeroFilas = numeroFilas;
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
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	
	/**
	 * @return the descripcionConsultora
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}

	/**
	 * @param descripcionConsultora the descripcionConsultora to set
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}
	
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * @return the validaciones
	 */
	public String getValidaciones() {
		return validaciones;
	}

	/**
	 * @param validaciones the validaciones to set
	 */
	public void setValidaciones(String validaciones) {
		this.validaciones = validaciones;
	}

	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	public Date getFechaDate() {
		return fechaDate;
	}

	public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
	}
}