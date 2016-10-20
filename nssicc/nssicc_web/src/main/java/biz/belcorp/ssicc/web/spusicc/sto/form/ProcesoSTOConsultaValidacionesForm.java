package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ProcesoSTOConsultaValidacionesForm extends BaseSearchForm
    implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String tipoDocumento;
	private String []selectedItems; 

	private Date fechaInicioD;
	private Date fechaFinD;
	private String indicadorDocumento;
	private String numeroLote;
	private String[] regionList;
	private String[] zonaList;
	private String codigoCliente;
	
	private Date fechaInicioProcesoD;
	private Date fechaFinProcesoD;
	
	private String codigoPeriodo;
	
	private String isActualizacionDatos;

	private String lineaDefecto;
	private String lineaMaxima;

	private String isCuponPago;

	private String numPreimpreso;
	
	
	
	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}
	/**
	 * @param regionList The regionList to set.
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
	 * @param zonaList The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	/**
	 * @return Returns the tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	
	/**
	 * @return Returns the indicadorDocumento.
	 */
	public String getIndicadorDocumento() {
		return indicadorDocumento;
	}
	
	
	/**
	 * @return Returns the numeroLote.
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	/**
	 * @param numeroLote The numeroLote to set.
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
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


	/**
	 * @param indicadorDocumento The indicadorDocumento to set.
	 */
	public void setIndicadorDocumento(String indicadorDocumento) {
		this.indicadorDocumento = indicadorDocumento;
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
	 * @return the isActualizacionDatos
	 */
	public String getIsActualizacionDatos() {
		return isActualizacionDatos;
	}

	/**
	 * @param isActualizacionDatos the isActualizacionDatos to set
	 */
	public void setIsActualizacionDatos(String isActualizacionDatos) {
		this.isActualizacionDatos = isActualizacionDatos;
	}

	/**
	 * @return the lineaDefecto
	 */
	public String getLineaDefecto() {
		return lineaDefecto;
	}

	/**
	 * @param lineaDefecto the lineaDefecto to set
	 */
	public void setLineaDefecto(String lineaDefecto) {
		this.lineaDefecto = lineaDefecto;
	}

	/**
	 * @return the lineaMaxima
	 */
	public String getLineaMaxima() {
		return lineaMaxima;
	}

	/**
	 * @param lineaMaxima the lineaMaxima to set
	 */
	public void setLineaMaxima(String lineaMaxima) {
		this.lineaMaxima = lineaMaxima;
	}

	/**
	 * @return the isCuponPago
	 */
	public String getIsCuponPago() {
		return isCuponPago;
	}

	/**
	 * @param isCuponPago the isCuponPago to set
	 */
	public void setIsCuponPago(String isCuponPago) {
		this.isCuponPago = isCuponPago;
	}

	/**
	 * @return the numPreimpreso
	 */
	public String getNumPreimpreso() {
		return numPreimpreso;
	}

	/**
	 * @param numPreimpreso the numPreimpreso to set
	 */
	public void setNumPreimpreso(String numPreimpreso) {
		this.numPreimpreso = numPreimpreso;
	}
	/**
	 * @return the fechaInicioD
	 */
	public Date getFechaInicioD() {
		return fechaInicioD;
	}
	/**
	 * @param fechaInicioD the fechaInicioD to set
	 */
	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}
	/**
	 * @return the fechaFinD
	 */
	public Date getFechaFinD() {
		return fechaFinD;
	}
	/**
	 * @param fechaFinD the fechaFinD to set
	 */
	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}
	/**
	 * @return the fechaInicioProcesoD
	 */
	public Date getFechaInicioProcesoD() {
		return fechaInicioProcesoD;
	}
	/**
	 * @param fechaInicioProcesoD the fechaInicioProcesoD to set
	 */
	public void setFechaInicioProcesoD(Date fechaInicioProcesoD) {
		this.fechaInicioProcesoD = fechaInicioProcesoD;
	}
	/**
	 * @return the fechaFinProcesoD
	 */
	public Date getFechaFinProcesoD() {
		return fechaFinProcesoD;
	}
	/**
	 * @param fechaFinProcesoD the fechaFinProcesoD to set
	 */
	public void setFechaFinProcesoD(Date fechaFinProcesoD) {
		this.fechaFinProcesoD = fechaFinProcesoD;
	}

}