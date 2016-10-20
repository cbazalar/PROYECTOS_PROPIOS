package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaSTOPedidosForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tipoDocumento;
	private String descripcionDocumento;
	private String[] selectedItems;
	private String fechaInicio;
	private Date fechaInicioD;

	private String fechaFin;
	private Date fechaFinD;

	private String indicadorDocumento;
	private String numeroLote;
	private String[] regionList;
	private String[] zonaList;
	private String codigoCliente;
	private String agrupacion;
	private String fechaInicioProceso;
	private Date fechaInicioProcesoD;

	private String fechaFinProceso;
	private Date fechaFinProcesoD;

	private String agrupacionFechaProceso;
	private String agrupacionLote;
	private String agrupacionRegion;
	private String agrupacionZona;
	
	private String agrupacionCliente;
	private String horaInicioCarga;
	private String horaFinCarga;
	private String horaInicioProceso;
	private String horaFinProceso;
	private String codigoPeriodo;
	private String agrupacionPeriodo;
	private String estado;
	private String accion;
	private String codigoOrigen;
	private String agrupacionOrigen;

	private String fechaInicioProgramadaFacturacion;
	private Date fechaInicioProgramadaFacturacionD;

	private String fechaFinProgramadaFacturacion;
	private Date fechaFinProgramadaFacturacionD;

	private String agrupacionFechaProgFacturacion;

	private String tipoDocumentoSearch;
	private String[] selectedItemsSearch;
	private String fechaInicioSearch;
	private String fechaFinSearch;
	private String indicadorDocumentoSearch;
	private String numeroLoteSearch;
	private String[] regionListSearch;
	private String[] zonaListSearch;
	private String codigoClienteSearch;
	private String agrupacionSearch;
	private String fechaInicioProcesoSearch;
	private String fechaFinProcesoSearch;
	private String agrupacionFechaProcesoSearch;
	private String agrupacionLoteSearch;
	private String agrupacionRegionSearch;
	private String agrupacionZonaSearch;
	private String agrupacionClienteSearch;
	private String horaInicioCargaSearch;
	private String horaFinCargaSearch;
	private String horaInicioProcesoSearch;
	private String horaFinProcesoSearch;
	private String codigoPeriodoSearch;
	private String agrupacionPeriodoSearch;
	private String estadoSearch;
	private String accionSearch;
	private String codigoOrigenSearch;
	private String agrupacionOrigenSearch;

	private String descripcionRegionList;

	private UploadedFile clienteFile;
	private String[] clienteList;

	private String secuencia;

	private String lineaDefecto;
	private String lineaMaxima;

	private String codigosErradosFile;	
	//sb
	private String documentoIdentidad;

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
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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
	 * @return the agrupacion
	 */
	public String getAgrupacion() {
		return agrupacion;
	}
	/**
	 * @param agrupacion the agrupacion to set
	 */
	public void setAgrupacion(String agrupacion) {
		this.agrupacion = agrupacion;
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
	 * @return the codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}
	/**
	 * @param codigoOrigen the codigoOrigen to set
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}
	/**
	 * @return the agrupacionOrigen
	 */
	public String getAgrupacionOrigen() {
		return agrupacionOrigen;
	}
	/**
	 * @param agrupacionOrigen the agrupacionOrigen to set
	 */
	public void setAgrupacionOrigen(String agrupacionOrigen) {
		this.agrupacionOrigen = agrupacionOrigen;
	}
	/**
	 * @return the fechaInicioProgramadaFacturacion
	 */
	public String getFechaInicioProgramadaFacturacion() {
		return fechaInicioProgramadaFacturacion;
	}
	/**
	 * @param fechaInicioProgramadaFacturacion the fechaInicioProgramadaFacturacion to set
	 */
	public void setFechaInicioProgramadaFacturacion(
			String fechaInicioProgramadaFacturacion) {
		this.fechaInicioProgramadaFacturacion = fechaInicioProgramadaFacturacion;
	}
	/**
	 * @return the fechaFinProgramadaFacturacion
	 */
	public String getFechaFinProgramadaFacturacion() {
		return fechaFinProgramadaFacturacion;
	}
	/**
	 * @param fechaFinProgramadaFacturacion the fechaFinProgramadaFacturacion to set
	 */
	public void setFechaFinProgramadaFacturacion(
			String fechaFinProgramadaFacturacion) {
		this.fechaFinProgramadaFacturacion = fechaFinProgramadaFacturacion;
	}
	/**
	 * @return the agrupacionFechaProgFacturacion
	 */
	public String getAgrupacionFechaProgFacturacion() {
		return agrupacionFechaProgFacturacion;
	}
	/**
	 * @param agrupacionFechaProgFacturacion the agrupacionFechaProgFacturacion to set
	 */
	public void setAgrupacionFechaProgFacturacion(
			String agrupacionFechaProgFacturacion) {
		this.agrupacionFechaProgFacturacion = agrupacionFechaProgFacturacion;
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
	 * @return the fechaInicioSearch
	 */
	public String getFechaInicioSearch() {
		return fechaInicioSearch;
	}
	/**
	 * @param fechaInicioSearch the fechaInicioSearch to set
	 */
	public void setFechaInicioSearch(String fechaInicioSearch) {
		this.fechaInicioSearch = fechaInicioSearch;
	}
	/**
	 * @return the fechaFinSearch
	 */
	public String getFechaFinSearch() {
		return fechaFinSearch;
	}
	/**
	 * @param fechaFinSearch the fechaFinSearch to set
	 */
	public void setFechaFinSearch(String fechaFinSearch) {
		this.fechaFinSearch = fechaFinSearch;
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
	 * @return the agrupacionSearch
	 */
	public String getAgrupacionSearch() {
		return agrupacionSearch;
	}
	/**
	 * @param agrupacionSearch the agrupacionSearch to set
	 */
	public void setAgrupacionSearch(String agrupacionSearch) {
		this.agrupacionSearch = agrupacionSearch;
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
	 * @return the codigoOrigenSearch
	 */
	public String getCodigoOrigenSearch() {
		return codigoOrigenSearch;
	}
	/**
	 * @param codigoOrigenSearch the codigoOrigenSearch to set
	 */
	public void setCodigoOrigenSearch(String codigoOrigenSearch) {
		this.codigoOrigenSearch = codigoOrigenSearch;
	}
	/**
	 * @return the agrupacionOrigenSearch
	 */
	public String getAgrupacionOrigenSearch() {
		return agrupacionOrigenSearch;
	}
	/**
	 * @param agrupacionOrigenSearch the agrupacionOrigenSearch to set
	 */
	public void setAgrupacionOrigenSearch(String agrupacionOrigenSearch) {
		this.agrupacionOrigenSearch = agrupacionOrigenSearch;
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
	 * @return the codigosErradosFile
	 */
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}
	/**
	 * @param codigosErradosFile the codigosErradosFile to set
	 */
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}
	/**
	 * @return the documentoIdentidad
	 */
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	/**
	 * @param documentoIdentidad the documentoIdentidad to set
	 */
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
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
	/**
	 * @return the fechaInicioProgramadaFacturacionD
	 */
	public Date getFechaInicioProgramadaFacturacionD() {
		return fechaInicioProgramadaFacturacionD;
	}
	/**
	 * @param fechaInicioProgramadaFacturacionD the fechaInicioProgramadaFacturacionD to set
	 */
	public void setFechaInicioProgramadaFacturacionD(
			Date fechaInicioProgramadaFacturacionD) {
		this.fechaInicioProgramadaFacturacionD = fechaInicioProgramadaFacturacionD;
	}
	/**
	 * @return the fechaFinProgramadaFacturacionD
	 */
	public Date getFechaFinProgramadaFacturacionD() {
		return fechaFinProgramadaFacturacionD;
	}
	/**
	 * @param fechaFinProgramadaFacturacionD the fechaFinProgramadaFacturacionD to set
	 */
	public void setFechaFinProgramadaFacturacionD(
			Date fechaFinProgramadaFacturacionD) {
		this.fechaFinProgramadaFacturacionD = fechaFinProgramadaFacturacionD;
	}
}