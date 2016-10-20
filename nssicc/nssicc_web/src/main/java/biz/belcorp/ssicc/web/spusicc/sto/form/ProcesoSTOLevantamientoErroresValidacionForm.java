package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoSTOLevantamientoErroresValidacionForm extends BaseProcesoForm implements Serializable{
	
	private static final long serialVersionUID = -2831859700500597451L;
	
	private String tipoDocumento;
	private String[] selectedItems;
	private Date fechaInicioDate;
	private Date fechaFinDate;
	private String indicadorDocumento;
	private String indicadorGestionable;
	private String numeroLote;
	private String numeroDocumento;
	private String[] validaciones;
	private String codigoValidacion;
	private String[] regionList;
	private String[] zonaList;
	private String codigoCliente;
	private String accion;
	private Date fechaInicioProcesoDate;
	private Date fechaFinProcesoDate;
	private String numDocumento;
	
	private String codigoPeriodo;
	private String[] regionListSearch;
	private String zonasSearch;
	private String motivoRechazo;
	private String motivoRechazoAbajo;
	private String codigoUsuario;
	private String codigoOrigen;	
	private UploadedFile clienteFile; // objeto que se utilizara para el upload
	private String accionInferior;
	private String fechaProceso;
	private String codigoPeriodoActual;
	private String observaciones;
	private String observacionesAbajo;
	private String indicadorAprobado;	
	private String[] clienteList;
	private String indPedCdr;

	private String codigoZonaArribo;
	private String codigosErradosFile;
	private Integer index;
	
	private String documentoIdentidad;
	
	private String indActPorcentajeDesviacion;
	private String indActPromedioPedido;
	private String indActMontoReal;
	
	
	public Integer getIndex() {
		return index;
	}
	
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public String getIndicadorDocumento() {
		return indicadorDocumento;
	}

	public void setIndicadorDocumento(String indicadorDocumento) {
		this.indicadorDocumento = indicadorDocumento;
	}
	
	public String getIndicadorGestionable() {
		return indicadorGestionable;
	}

	public void setIndicadorGestionable(String indicadorGestionable) {
		this.indicadorGestionable = indicadorGestionable;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String[] getValidaciones() {
		return validaciones;
	}

	public void setValidaciones(String[] validaciones) {
		this.validaciones = validaciones;
	}

	public String getCodigoValidacion() {
		return codigoValidacion;
	}

	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	public String[] getRegionList() {
		return regionList;
	}

	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	public String[] getZonaList() {
		return zonaList;
	}

	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String[] getRegionListSearch() {
		return regionListSearch;
	}

	public void setRegionListSearch(String[] regionListSearch) {
		this.regionListSearch = regionListSearch;
	}

	public String getZonasSearch() {
		return zonasSearch;
	}

	public void setZonasSearch(String zonasSearch) {
		this.zonasSearch = zonasSearch;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public String getMotivoRechazoAbajo() {
		return motivoRechazoAbajo;
	}

	public void setMotivoRechazoAbajo(String motivoRechazoAbajo) {
		this.motivoRechazoAbajo = motivoRechazoAbajo;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	public String getAccionInferior() {
		return accionInferior;
	}

	public void setAccionInferior(String accionInferior) {
		this.accionInferior = accionInferior;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getCodigoPeriodoActual() {
		return codigoPeriodoActual;
	}

	public void setCodigoPeriodoActual(String codigoPeriodoActual) {
		this.codigoPeriodoActual = codigoPeriodoActual;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservacionesAbajo() {
		return observacionesAbajo;
	}

	public void setObservacionesAbajo(String observacionesAbajo) {
		this.observacionesAbajo = observacionesAbajo;
	}

	public String getIndicadorAprobado() {
		return indicadorAprobado;
	}

	public void setIndicadorAprobado(String indicadorAprobado) {
		this.indicadorAprobado = indicadorAprobado;
	}
	
	public String[] getClienteList() {
		return clienteList;
	}

	public void setClienteList(String[] clienteList) {
		this.clienteList = clienteList;
	}
	
	public String getIndPedCdr() {
		return indPedCdr;
	}
	
	public void setIndPedCdr(String indPedCdr) {
		this.indPedCdr = indPedCdr;
	}
	
	public String getCodigoZonaArribo() {
		return codigoZonaArribo;
	}
	
	public void setCodigoZonaArribo(String codigoZonaArribo) {
		this.codigoZonaArribo = codigoZonaArribo;
	}
	
	public String getCodigosErradosFile() {
		return codigosErradosFile;
	}
	
	public void setCodigosErradosFile(String codigosErradosFile) {
		this.codigosErradosFile = codigosErradosFile;
	}
	
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	
	public String getIndActPorcentajeDesviacion() {
		return indActPorcentajeDesviacion;
	}
	
	public void setIndActPorcentajeDesviacion(String indActPorcentajeDesviacion) {
		this.indActPorcentajeDesviacion = indActPorcentajeDesviacion;
	}
	
	public String getIndActPromedioPedido() {
		return indActPromedioPedido;
	}
	
	public void setIndActPromedioPedido(String indActPromedioPedido) {
		this.indActPromedioPedido = indActPromedioPedido;
	}
	
	public String getIndActMontoReal() {
		return indActMontoReal;
	}
	
	public void setIndActMontoReal(String indActMontoReal) {
		this.indActMontoReal = indActMontoReal;
	}

	public Date getFechaInicioDate() {
		return fechaInicioDate;
	}

	public void setFechaInicioDate(Date fechaInicioDate) {
		this.fechaInicioDate = fechaInicioDate;
	}

	public Date getFechaFinDate() {
		return fechaFinDate;
	}

	public void setFechaFinDate(Date fechaFinDate) {
		this.fechaFinDate = fechaFinDate;
	}

	public Date getFechaInicioProcesoDate() {
		return fechaInicioProcesoDate;
	}

	public void setFechaInicioProcesoDate(Date fechaInicioProcesoDate) {
		this.fechaInicioProcesoDate = fechaInicioProcesoDate;
	}

	public Date getFechaFinProcesoDate() {
		return fechaFinProcesoDate;
	}

	public void setFechaFinProcesoDate(Date fechaFinProcesoDate) {
		this.fechaFinProcesoDate = fechaFinProcesoDate;
	}
	
	public UploadedFile getClienteFile() {
		return clienteFile;
	}

	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}
}
