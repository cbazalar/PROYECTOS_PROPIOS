package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoSTOEliminarPolizasFamiliaSeguraForm extends BaseProcesoForm  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoDocumento;
	private String descripcionDocumento;
	private String indicadorDocumento;
	private String codigoPeriodo;
	private String fechaInicioCarga;
	private Date fechaInicioCargaD;
	private String fechaFinCarga;
	private Date fechaFinCargaD;
	private String horaInicioCarga;
	private String horaFinCarga;
	private String fechaInicioProceso;
	private Date fechaInicioProcesoD;
	private String fechaFinProceso;
	private Date fechaFinProcesoD;
	private String horaInicioProceso;
	private String horaFinProceso;
	private String numeroLote;
	private String estado;
	private String codigoCliente;
	private String[] regionList;
	private String descripcionRegionList;
	private String[] zonaList;
	private UploadedFile clienteFile;
	private String[] clienteList;
	private String accion;
	private String agrupacionPeriodo;
	private String agrupacionFechaCarga;
	private String agrupacionFechaProceso;
	private String agrupacionLote;
	private String agrupacionRegion;
	private String agrupacionZona;
	private String agrupacionCliente;
	private String cargaCombo;
	private String []selectedItems;
	private String secuencia;
	
	private String tipoDocumentoSearch;
	private String indicadorDocumentoSearch;
	private String codigoPeriodoSearch;
	private String fechaInicioCargaSearch;
	private String fechaFinCargaSearch;
	private String horaInicioCargaSearch;
	private String horaFinCargaSearch;
	private String fechaInicioProcesoSearch;
	private String fechaFinProcesoSearch;
	private String horaInicioProcesoSearch;
	private String horaFinProcesoSearch;
	private String numeroLoteSearch;
	private String estadoSearch;
	private String codigoClienteSearch;
	private String[] regionListSearch;
	private String[] zonaListSearch;
	private String accionSearch;
	private String agrupacionPeriodoSearch;
	private String agrupacionFechaCargaSearch;
	private String agrupacionFechaProcesoSearch;
	private String agrupacionLoteSearch;
	private String agrupacionRegionSearch;
	private String agrupacionZonaSearch;
	private String agrupacionClienteSearch;
	private String []selectedItemsSearch; 

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the descripcionDocumento
	 */
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}

	/**
	 * @param descripcionDocumento the descripcionDocumento to set
	 */
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}

	/**
	 * @return the indicadorDocumento
	 */
	public String getIndicadorDocumento() {
		return indicadorDocumento;
	}

	/**
	 * @param indicadorDocumento the indicadorDocumento to set
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
	 * @return the fechaInicioCarga
	 */
	public String getFechaInicioCarga() {
		return fechaInicioCarga;
	}

	/**
	 * @param fechaInicioCarga the fechaInicioCarga to set
	 */
	public void setFechaInicioCarga(String fechaInicioCarga) {
		this.fechaInicioCarga = fechaInicioCarga;
	}

	/**
	 * @return the fechaFinCarga
	 */
	public String getFechaFinCarga() {
		return fechaFinCarga;
	}

	/**
	 * @param fechaFinCarga the fechaFinCarga to set
	 */
	public void setFechaFinCarga(String fechaFinCarga) {
		this.fechaFinCarga = fechaFinCarga;
	}

	/**
	 * @return the horaInicioCarga
	 */
	public String getHoraInicioCarga() {
		return horaInicioCarga;
	}

	/**
	 * @param horaInicioCarga the horaInicioCarga to set
	 */
	public void setHoraInicioCarga(String horaInicioCarga) {
		this.horaInicioCarga = horaInicioCarga;
	}

	/**
	 * @return the horaFinCarga
	 */
	public String getHoraFinCarga() {
		return horaFinCarga;
	}

	/**
	 * @param horaFinCarga the horaFinCarga to set
	 */
	public void setHoraFinCarga(String horaFinCarga) {
		this.horaFinCarga = horaFinCarga;
	}

	/**
	 * @return the fechaInicioProceso
	 */
	public String getFechaInicioProceso() {
		return fechaInicioProceso;
	}

	/**
	 * @param fechaInicioProceso the fechaInicioProceso to set
	 */
	public void setFechaInicioProceso(String fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}

	/**
	 * @return the fechaFinProceso
	 */
	public String getFechaFinProceso() {
		return fechaFinProceso;
	}

	/**
	 * @param fechaFinProceso the fechaFinProceso to set
	 */
	public void setFechaFinProceso(String fechaFinProceso) {
		this.fechaFinProceso = fechaFinProceso;
	}

	/**
	 * @return the horaInicioProceso
	 */
	public String getHoraInicioProceso() {
		return horaInicioProceso;
	}

	/**
	 * @param horaInicioProceso the horaInicioProceso to set
	 */
	public void setHoraInicioProceso(String horaInicioProceso) {
		this.horaInicioProceso = horaInicioProceso;
	}

	/**
	 * @return the horaFinProceso
	 */
	public String getHoraFinProceso() {
		return horaFinProceso;
	}

	/**
	 * @param horaFinProceso the horaFinProceso to set
	 */
	public void setHoraFinProceso(String horaFinProceso) {
		this.horaFinProceso = horaFinProceso;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
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
	 * @return the descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * @param descripcionRegionList the descripcionRegionList to set
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
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
	 * @return the clienteFile
	 */
	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	/**
	 * @param clienteFile the clienteFile to set
	 */
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}

	/**
	 * @return the clienteList
	 */
	public String[] getClienteList() {
		return clienteList;
	}

	/**
	 * @param clienteList the clienteList to set
	 */
	public void setClienteList(String[] clienteList) {
		this.clienteList = clienteList;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the agrupacionPeriodo
	 */
	public String getAgrupacionPeriodo() {
		return agrupacionPeriodo;
	}

	/**
	 * @param agrupacionPeriodo the agrupacionPeriodo to set
	 */
	public void setAgrupacionPeriodo(String agrupacionPeriodo) {
		this.agrupacionPeriodo = agrupacionPeriodo;
	}

	/**
	 * @return the agrupacionFechaCarga
	 */
	public String getAgrupacionFechaCarga() {
		return agrupacionFechaCarga;
	}

	/**
	 * @param agrupacionFechaCarga the agrupacionFechaCarga to set
	 */
	public void setAgrupacionFechaCarga(String agrupacionFechaCarga) {
		this.agrupacionFechaCarga = agrupacionFechaCarga;
	}

	/**
	 * @return the agrupacionFechaProceso
	 */
	public String getAgrupacionFechaProceso() {
		return agrupacionFechaProceso;
	}

	/**
	 * @param agrupacionFechaProceso the agrupacionFechaProceso to set
	 */
	public void setAgrupacionFechaProceso(String agrupacionFechaProceso) {
		this.agrupacionFechaProceso = agrupacionFechaProceso;
	}

	/**
	 * @return the agrupacionLote
	 */
	public String getAgrupacionLote() {
		return agrupacionLote;
	}

	/**
	 * @param agrupacionLote the agrupacionLote to set
	 */
	public void setAgrupacionLote(String agrupacionLote) {
		this.agrupacionLote = agrupacionLote;
	}

	/**
	 * @return the agrupacionRegion
	 */
	public String getAgrupacionRegion() {
		return agrupacionRegion;
	}

	/**
	 * @param agrupacionRegion the agrupacionRegion to set
	 */
	public void setAgrupacionRegion(String agrupacionRegion) {
		this.agrupacionRegion = agrupacionRegion;
	}

	/**
	 * @return the agrupacionZona
	 */
	public String getAgrupacionZona() {
		return agrupacionZona;
	}

	/**
	 * @param agrupacionZona the agrupacionZona to set
	 */
	public void setAgrupacionZona(String agrupacionZona) {
		this.agrupacionZona = agrupacionZona;
	}

	/**
	 * @return the agrupacionCliente
	 */
	public String getAgrupacionCliente() {
		return agrupacionCliente;
	}

	/**
	 * @param agrupacionCliente the agrupacionCliente to set
	 */
	public void setAgrupacionCliente(String agrupacionCliente) {
		this.agrupacionCliente = agrupacionCliente;
	}

	/**
	 * @return the cargaCombo
	 */
	public String getCargaCombo() {
		return cargaCombo;
	}

	/**
	 * @param cargaCombo the cargaCombo to set
	 */
	public void setCargaCombo(String cargaCombo) {
		this.cargaCombo = cargaCombo;
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
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * @param secuencia the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * @return the tipoDocumentoSearch
	 */
	public String getTipoDocumentoSearch() {
		return tipoDocumentoSearch;
	}

	/**
	 * @param tipoDocumentoSearch the tipoDocumentoSearch to set
	 */
	public void setTipoDocumentoSearch(String tipoDocumentoSearch) {
		this.tipoDocumentoSearch = tipoDocumentoSearch;
	}

	/**
	 * @return the indicadorDocumentoSearch
	 */
	public String getIndicadorDocumentoSearch() {
		return indicadorDocumentoSearch;
	}

	/**
	 * @param indicadorDocumentoSearch the indicadorDocumentoSearch to set
	 */
	public void setIndicadorDocumentoSearch(String indicadorDocumentoSearch) {
		this.indicadorDocumentoSearch = indicadorDocumentoSearch;
	}

	/**
	 * @return the codigoPeriodoSearch
	 */
	public String getCodigoPeriodoSearch() {
		return codigoPeriodoSearch;
	}

	/**
	 * @param codigoPeriodoSearch the codigoPeriodoSearch to set
	 */
	public void setCodigoPeriodoSearch(String codigoPeriodoSearch) {
		this.codigoPeriodoSearch = codigoPeriodoSearch;
	}

	/**
	 * @return the fechaInicioCargaSearch
	 */
	public String getFechaInicioCargaSearch() {
		return fechaInicioCargaSearch;
	}

	/**
	 * @param fechaInicioCargaSearch the fechaInicioCargaSearch to set
	 */
	public void setFechaInicioCargaSearch(String fechaInicioCargaSearch) {
		this.fechaInicioCargaSearch = fechaInicioCargaSearch;
	}

	/**
	 * @return the fechaFinCargaSearch
	 */
	public String getFechaFinCargaSearch() {
		return fechaFinCargaSearch;
	}

	/**
	 * @param fechaFinCargaSearch the fechaFinCargaSearch to set
	 */
	public void setFechaFinCargaSearch(String fechaFinCargaSearch) {
		this.fechaFinCargaSearch = fechaFinCargaSearch;
	}

	/**
	 * @return the horaInicioCargaSearch
	 */
	public String getHoraInicioCargaSearch() {
		return horaInicioCargaSearch;
	}

	/**
	 * @param horaInicioCargaSearch the horaInicioCargaSearch to set
	 */
	public void setHoraInicioCargaSearch(String horaInicioCargaSearch) {
		this.horaInicioCargaSearch = horaInicioCargaSearch;
	}

	/**
	 * @return the horaFinCargaSearch
	 */
	public String getHoraFinCargaSearch() {
		return horaFinCargaSearch;
	}

	/**
	 * @param horaFinCargaSearch the horaFinCargaSearch to set
	 */
	public void setHoraFinCargaSearch(String horaFinCargaSearch) {
		this.horaFinCargaSearch = horaFinCargaSearch;
	}

	/**
	 * @return the fechaInicioProcesoSearch
	 */
	public String getFechaInicioProcesoSearch() {
		return fechaInicioProcesoSearch;
	}

	/**
	 * @param fechaInicioProcesoSearch the fechaInicioProcesoSearch to set
	 */
	public void setFechaInicioProcesoSearch(String fechaInicioProcesoSearch) {
		this.fechaInicioProcesoSearch = fechaInicioProcesoSearch;
	}

	/**
	 * @return the fechaFinProcesoSearch
	 */
	public String getFechaFinProcesoSearch() {
		return fechaFinProcesoSearch;
	}

	/**
	 * @param fechaFinProcesoSearch the fechaFinProcesoSearch to set
	 */
	public void setFechaFinProcesoSearch(String fechaFinProcesoSearch) {
		this.fechaFinProcesoSearch = fechaFinProcesoSearch;
	}

	/**
	 * @return the horaInicioProcesoSearch
	 */
	public String getHoraInicioProcesoSearch() {
		return horaInicioProcesoSearch;
	}

	/**
	 * @param horaInicioProcesoSearch the horaInicioProcesoSearch to set
	 */
	public void setHoraInicioProcesoSearch(String horaInicioProcesoSearch) {
		this.horaInicioProcesoSearch = horaInicioProcesoSearch;
	}

	/**
	 * @return the horaFinProcesoSearch
	 */
	public String getHoraFinProcesoSearch() {
		return horaFinProcesoSearch;
	}

	/**
	 * @param horaFinProcesoSearch the horaFinProcesoSearch to set
	 */
	public void setHoraFinProcesoSearch(String horaFinProcesoSearch) {
		this.horaFinProcesoSearch = horaFinProcesoSearch;
	}

	/**
	 * @return the numeroLoteSearch
	 */
	public String getNumeroLoteSearch() {
		return numeroLoteSearch;
	}

	/**
	 * @param numeroLoteSearch the numeroLoteSearch to set
	 */
	public void setNumeroLoteSearch(String numeroLoteSearch) {
		this.numeroLoteSearch = numeroLoteSearch;
	}

	/**
	 * @return the estadoSearch
	 */
	public String getEstadoSearch() {
		return estadoSearch;
	}

	/**
	 * @param estadoSearch the estadoSearch to set
	 */
	public void setEstadoSearch(String estadoSearch) {
		this.estadoSearch = estadoSearch;
	}

	/**
	 * @return the codigoClienteSearch
	 */
	public String getCodigoClienteSearch() {
		return codigoClienteSearch;
	}

	/**
	 * @param codigoClienteSearch the codigoClienteSearch to set
	 */
	public void setCodigoClienteSearch(String codigoClienteSearch) {
		this.codigoClienteSearch = codigoClienteSearch;
	}

	/**
	 * @return the regionListSearch
	 */
	public String[] getRegionListSearch() {
		return regionListSearch;
	}

	/**
	 * @param regionListSearch the regionListSearch to set
	 */
	public void setRegionListSearch(String[] regionListSearch) {
		this.regionListSearch = regionListSearch;
	}

	/**
	 * @return the zonaListSearch
	 */
	public String[] getZonaListSearch() {
		return zonaListSearch;
	}

	/**
	 * @param zonaListSearch the zonaListSearch to set
	 */
	public void setZonaListSearch(String[] zonaListSearch) {
		this.zonaListSearch = zonaListSearch;
	}

	/**
	 * @return the accionSearch
	 */
	public String getAccionSearch() {
		return accionSearch;
	}

	/**
	 * @param accionSearch the accionSearch to set
	 */
	public void setAccionSearch(String accionSearch) {
		this.accionSearch = accionSearch;
	}

	/**
	 * @return the agrupacionPeriodoSearch
	 */
	public String getAgrupacionPeriodoSearch() {
		return agrupacionPeriodoSearch;
	}

	/**
	 * @param agrupacionPeriodoSearch the agrupacionPeriodoSearch to set
	 */
	public void setAgrupacionPeriodoSearch(String agrupacionPeriodoSearch) {
		this.agrupacionPeriodoSearch = agrupacionPeriodoSearch;
	}

	/**
	 * @return the agrupacionFechaCargaSearch
	 */
	public String getAgrupacionFechaCargaSearch() {
		return agrupacionFechaCargaSearch;
	}

	/**
	 * @param agrupacionFechaCargaSearch the agrupacionFechaCargaSearch to set
	 */
	public void setAgrupacionFechaCargaSearch(String agrupacionFechaCargaSearch) {
		this.agrupacionFechaCargaSearch = agrupacionFechaCargaSearch;
	}

	/**
	 * @return the agrupacionFechaProcesoSearch
	 */
	public String getAgrupacionFechaProcesoSearch() {
		return agrupacionFechaProcesoSearch;
	}

	/**
	 * @param agrupacionFechaProcesoSearch the agrupacionFechaProcesoSearch to set
	 */
	public void setAgrupacionFechaProcesoSearch(String agrupacionFechaProcesoSearch) {
		this.agrupacionFechaProcesoSearch = agrupacionFechaProcesoSearch;
	}

	/**
	 * @return the agrupacionLoteSearch
	 */
	public String getAgrupacionLoteSearch() {
		return agrupacionLoteSearch;
	}

	/**
	 * @param agrupacionLoteSearch the agrupacionLoteSearch to set
	 */
	public void setAgrupacionLoteSearch(String agrupacionLoteSearch) {
		this.agrupacionLoteSearch = agrupacionLoteSearch;
	}

	/**
	 * @return the agrupacionRegionSearch
	 */
	public String getAgrupacionRegionSearch() {
		return agrupacionRegionSearch;
	}

	/**
	 * @param agrupacionRegionSearch the agrupacionRegionSearch to set
	 */
	public void setAgrupacionRegionSearch(String agrupacionRegionSearch) {
		this.agrupacionRegionSearch = agrupacionRegionSearch;
	}

	/**
	 * @return the agrupacionZonaSearch
	 */
	public String getAgrupacionZonaSearch() {
		return agrupacionZonaSearch;
	}

	/**
	 * @param agrupacionZonaSearch the agrupacionZonaSearch to set
	 */
	public void setAgrupacionZonaSearch(String agrupacionZonaSearch) {
		this.agrupacionZonaSearch = agrupacionZonaSearch;
	}

	/**
	 * @return the agrupacionClienteSearch
	 */
	public String getAgrupacionClienteSearch() {
		return agrupacionClienteSearch;
	}

	/**
	 * @param agrupacionClienteSearch the agrupacionClienteSearch to set
	 */
	public void setAgrupacionClienteSearch(String agrupacionClienteSearch) {
		this.agrupacionClienteSearch = agrupacionClienteSearch;
	}

	/**
	 * @return the selectedItemsSearch
	 */
	public String[] getSelectedItemsSearch() {
		return selectedItemsSearch;
	}

	/**
	 * @param selectedItemsSearch the selectedItemsSearch to set
	 */
	public void setSelectedItemsSearch(String[] selectedItemsSearch) {
		this.selectedItemsSearch = selectedItemsSearch;
	}

	/**
	 * @return the fechaInicioCargaD
	 */
	public Date getFechaInicioCargaD() {
		return fechaInicioCargaD;
	}

	/**
	 * @param fechaInicioCargaD the fechaInicioCargaD to set
	 */
	public void setFechaInicioCargaD(Date fechaInicioCargaD) {
		this.fechaInicioCargaD = fechaInicioCargaD;
	}

	/**
	 * @return the fechaFinCargaD
	 */
	public Date getFechaFinCargaD() {
		return fechaFinCargaD;
	}

	/**
	 * @param fechaFinCargaD the fechaFinCargaD to set
	 */
	public void setFechaFinCargaD(Date fechaFinCargaD) {
		this.fechaFinCargaD = fechaFinCargaD;
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