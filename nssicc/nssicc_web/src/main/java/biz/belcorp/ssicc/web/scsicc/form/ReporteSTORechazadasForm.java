package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSTORechazadasForm extends BaseReporteForm implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String tipoDocumento;
	private String codigoPeriodo;
	private String fechaInicioCarga;
	private Date fechaInicioCargaD;
	private String horaInicioCarga;
	private String fechaFinCarga;
	private Date fechaFinCargaD;
	private String horaFinCarga;
	private String fechaInicioProceso;
	private Date fechaInicioProcesoD;
	private String horaInicioProceso;
	private String fechaFinProceso;
	private Date fechaFinProcesoD;
	private String horaFinProceso;
	private String fechaProgFacturacion;
	private String fechaInicioProgFacturacion;
	private Date fechaInicioProgFacturacionD;
	private String horaInicioProgFactura;
	private String fechaFinProgFacturacion;
	private Date fechaFinProgFacturacionD;
	private String horaFinProgFactura;
	private String numeroLote;
	private String codigoCliente;
	private String codigoOrigen;
	private String[] regionList;
	private String[] zonaList;
	private String[] clienteList;
	private String cargaCombo;
	private String fechaInicioSearch;
	private String fechaFinSearch;
	private String numeroLoteSearch;
	private String codigoClienteSearch;
	private String fechaInicioProcesoSearch;
	private String fechaFinProcesoSearch;
	private String horaInicioCargaSearch;
	private String horaFinCargaSearch;
	private String horaInicioProcesoSearch;
	private String horaFinProcesoSearch;
	private String codigoPeriodoSearch;
	private String codigoOrigenSearch;
	private String fechaInicioFactProgSearch;
	private String fechaFinFactProgSearch;
	private String horaInicioFactProgSearch;
	private String horaFinFactProgSearch;
	private String descripcionDocumento;
	private String secuencia;
	private String flagFechaFact;

	/**
	 * @return flagFechaFact
	 */
	public String getFlagFechaFact() {
		return flagFechaFact;
	}

	/**
	 * @param flagFechaFact
	 */
	public void setFlagFechaFact(String flagFechaFact) {
		this.flagFechaFact = flagFechaFact;
	}

	/**
	 * @return secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * @param secuencia
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * @return descripcionDocumento
	 */
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}

	/**
	 * @param descripcionDocumento
	 */
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}

	/**
	 * @return fechaInicioFactProgSearch
	 */
	public String getFechaInicioFactProgSearch() {
		return fechaInicioFactProgSearch;
	}

	/**
	 * @param fechaInicioFactProgSearch
	 */
	public void setFechaInicioFactProgSearch(String fechaInicioFactProgSearch) {
		this.fechaInicioFactProgSearch = fechaInicioFactProgSearch;
	}

	/**
	 * @return fechaFinFactProgSearch
	 */
	public String getFechaFinFactProgSearch() {
		return fechaFinFactProgSearch;
	}

	/**
	 * @param fechaFinFactProgSearch
	 */
	public void setFechaFinFactProgSearch(String fechaFinFactProgSearch) {
		this.fechaFinFactProgSearch = fechaFinFactProgSearch;
	}

	/**
	 * @return horaInicioFactProgSearch
	 */
	public String getHoraInicioFactProgSearch() {
		return horaInicioFactProgSearch;
	}

	/**
	 * @param horaInicioFactProgSearch
	 */
	public void setHoraInicioFactProgSearch(String horaInicioFactProgSearch) {
		this.horaInicioFactProgSearch = horaInicioFactProgSearch;
	}

	/**
	 * @return horaFinFactProgSearch
	 */
	public String getHoraFinFactProgSearch() {
		return horaFinFactProgSearch;
	}

	/**
	 * @param horaFinFactProgSearch
	 */
	public void setHoraFinFactProgSearch(String horaFinFactProgSearch) {
		this.horaFinFactProgSearch = horaFinFactProgSearch;
	}

	/**
	 * @return fechaInicioSearch
	 */
	public String getFechaInicioSearch() {
		return fechaInicioSearch;
	}

	/**
	 * @param fechaInicioSearch
	 */
	public void setFechaInicioSearch(String fechaInicioSearch) {
		this.fechaInicioSearch = fechaInicioSearch;
	}

	/**
	 * @return fechaFinSearch
	 */
	public String getFechaFinSearch() {
		return fechaFinSearch;
	}

	/**
	 * @param fechaFinSearch
	 */
	public void setFechaFinSearch(String fechaFinSearch) {
		this.fechaFinSearch = fechaFinSearch;
	}

	/**
	 * @return numeroLoteSearch
	 */
	public String getNumeroLoteSearch() {
		return numeroLoteSearch;
	}

	/**
	 * @param numeroLoteSearch
	 */
	public void setNumeroLoteSearch(String numeroLoteSearch) {
		this.numeroLoteSearch = numeroLoteSearch;
	}

	/**
	 * @return codigoClienteSearch
	 */
	public String getCodigoClienteSearch() {
		return codigoClienteSearch;
	}

	/**
	 * @param codigoClienteSearch
	 */
	public void setCodigoClienteSearch(String codigoClienteSearch) {
		this.codigoClienteSearch = codigoClienteSearch;
	}

	/**
	 * @return fechaInicioProcesoSearch
	 */
	public String getFechaInicioProcesoSearch() {
		return fechaInicioProcesoSearch;
	}

	/**
	 * @param fechaInicioProcesoSearch
	 */
	public void setFechaInicioProcesoSearch(String fechaInicioProcesoSearch) {
		this.fechaInicioProcesoSearch = fechaInicioProcesoSearch;
	}

	/**
	 * @return fechaFinProcesoSearch
	 */
	public String getFechaFinProcesoSearch() {
		return fechaFinProcesoSearch;
	}

	/**
	 * @param fechaFinProcesoSearch
	 */
	public void setFechaFinProcesoSearch(String fechaFinProcesoSearch) {
		this.fechaFinProcesoSearch = fechaFinProcesoSearch;
	}

	/**
	 * @return horaInicioCargaSearch
	 */
	public String getHoraInicioCargaSearch() {
		return horaInicioCargaSearch;
	}

	/**
	 * @param horaInicioCargaSearch
	 */
	public void setHoraInicioCargaSearch(String horaInicioCargaSearch) {
		this.horaInicioCargaSearch = horaInicioCargaSearch;
	}

	/**
	 * @return horaFinCargaSearch
	 */
	public String getHoraFinCargaSearch() {
		return horaFinCargaSearch;
	}

	/**
	 * @param horaFinCargaSearch
	 */
	public void setHoraFinCargaSearch(String horaFinCargaSearch) {
		this.horaFinCargaSearch = horaFinCargaSearch;
	}

	/**
	 * @return horaInicioProcesoSearch
	 */
	public String getHoraInicioProcesoSearch() {
		return horaInicioProcesoSearch;
	}

	/**
	 * @param horaInicioProcesoSearch
	 */
	public void setHoraInicioProcesoSearch(String horaInicioProcesoSearch) {
		this.horaInicioProcesoSearch = horaInicioProcesoSearch;
	}

	/**
	 * @return horaFinProcesoSearch
	 */
	public String getHoraFinProcesoSearch() {
		return horaFinProcesoSearch;
	}

	/**
	 * @param horaFinProcesoSearch
	 */
	public void setHoraFinProcesoSearch(String horaFinProcesoSearch) {
		this.horaFinProcesoSearch = horaFinProcesoSearch;
	}

	/**
	 * @return codigoPeriodoSearch
	 */
	public String getCodigoPeriodoSearch() {
		return codigoPeriodoSearch;
	}

	/**
	 * @param codigoPeriodoSearch
	 */
	public void setCodigoPeriodoSearch(String codigoPeriodoSearch) {
		this.codigoPeriodoSearch = codigoPeriodoSearch;
	}

	/**
	 * @return codigoOrigenSearch
	 */
	public String getCodigoOrigenSearch() {
		return codigoOrigenSearch;
	}

	/**
	 * @param codigoOrigenSearch
	 */
	public void setCodigoOrigenSearch(String codigoOrigenSearch) {
		this.codigoOrigenSearch = codigoOrigenSearch;
	}

	/**
	 * @return cargaCombo
	 */
	public String getCargaCombo() {
		return cargaCombo;
	}

	/**
	 * @param cargaCombo
	 */
	public void setCargaCombo(String cargaCombo) {
		this.cargaCombo = cargaCombo;
	}

	/**
	 * @return clienteList
	 */
	public String[] getClienteList() {
		return clienteList;
	}

	/**
	 * @param clienteList
	 */
	public void setClienteList(String[] clienteList) {
		this.clienteList = clienteList;
	}

	/**
	 * @return codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	/**
	 * @param codigoOrigen
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	/**
	 * @return regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return horaInicioProgFactura
	 */
	public String getHoraInicioProgFactura() {
		return horaInicioProgFactura;
	}

	/**
	 * @param horaInicioProgFactura
	 */
	public void setHoraInicioProgFactura(String horaInicioProgFactura) {
		this.horaInicioProgFactura = horaInicioProgFactura;
	}

	/**
	 * @return horaFinProgFactura
	 */
	public String getHoraFinProgFactura() {
		return horaFinProgFactura;
	}

	/**
	 * @param horaFinProgFactura
	 */
	public void setHoraFinProgFactura(String horaFinProgFactura) {
		this.horaFinProgFactura = horaFinProgFactura;
	}

	/**
	 * @return fechaProgFacturacion
	 */
	public String getFechaProgFacturacion() {
		return fechaProgFacturacion;
	}

	/**
	 * @param fechaProgFacturacion
	 */
	public void setFechaProgFacturacion(String fechaProgFacturacion) {
		this.fechaProgFacturacion = fechaProgFacturacion;
	}

	/**
	 * @return fechaInicioProgFacturacion
	 */
	public String getFechaInicioProgFacturacion() {
		return fechaInicioProgFacturacion;
	}

	/**
	 * @param fechaInicioProgFacturacion
	 */
	public void setFechaInicioProgFacturacion(String fechaInicioProgFacturacion) {
		this.fechaInicioProgFacturacion = fechaInicioProgFacturacion;
	}

	/**
	 * @return fechaFinProgFacturacion
	 */
	public String getFechaFinProgFacturacion() {
		return fechaFinProgFacturacion;
	}

	/**
	 * @param fechaFinProgFacturacion
	 */
	public void setFechaFinProgFacturacion(String fechaFinProgFacturacion) {
		this.fechaFinProgFacturacion = fechaFinProgFacturacion;
	}

	/**
	 * @return numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return fechaFinCarga
	 */
	public String getFechaFinCarga() {
		return fechaFinCarga;
	}

	/**
	 * @param fechaFinCarga
	 */
	public void setFechaFinCarga(String fechaFinCarga) {
		this.fechaFinCarga = fechaFinCarga;
	}

	/**
	 * @return codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return fechaInicioCarga
	 */
	public String getFechaInicioCarga() {
		return fechaInicioCarga;
	}

	/**
	 * @param fechaInicioCarga
	 */
	public void setFechaInicioCarga(String fechaInicioCarga) {
		this.fechaInicioCarga = fechaInicioCarga;
	}

	/**
	 * @return horaInicioCarga
	 */
	public String getHoraInicioCarga() {
		return horaInicioCarga;
	}

	/**
	 * @param horaInicioCarga
	 */
	public void setHoraInicioCarga(String horaInicioCarga) {
		this.horaInicioCarga = horaInicioCarga;
	}

	/**
	 * @return horaFinCarga
	 */
	public String getHoraFinCarga() {
		return horaFinCarga;
	}

	/**
	 * @param horaFinCarga
	 */
	public void setHoraFinCarga(String horaFinCarga) {
		this.horaFinCarga = horaFinCarga;
	}

	/**
	 * @return fechaInicioProceso
	 */
	public String getFechaInicioProceso() {
		return fechaInicioProceso;
	}

	/**
	 * @param fechaInicioProceso
	 */
	public void setFechaInicioProceso(String fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}

	/**
	 * @return horaInicioProceso
	 */
	public String getHoraInicioProceso() {
		return horaInicioProceso;
	}

	/**
	 * @param horaInicioProceso
	 */
	public void setHoraInicioProceso(String horaInicioProceso) {
		this.horaInicioProceso = horaInicioProceso;
	}

	/**
	 * @return fechaFinProceso
	 */
	public String getFechaFinProceso() {
		return fechaFinProceso;
	}

	/**
	 * @param fechaFinProceso
	 */
	public void setFechaFinProceso(String fechaFinProceso) {
		this.fechaFinProceso = fechaFinProceso;
	}

	/**
	 * @return horaFinProceso
	 */
	public String getHoraFinProceso() {
		return horaFinProceso;
	}

	/**
	 * @param horaFinProceso
	 */
	public void setHoraFinProceso(String horaFinProceso) {
		this.horaFinProceso = horaFinProceso;
	}

	/**
	 * @return tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return codigoPais
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

	/**
	 * @return the fechaInicioProgFacturacionD
	 */
	public Date getFechaInicioProgFacturacionD() {
		return fechaInicioProgFacturacionD;
	}

	/**
	 * @param fechaInicioProgFacturacionD the fechaInicioProgFacturacionD to set
	 */
	public void setFechaInicioProgFacturacionD(Date fechaInicioProgFacturacionD) {
		this.fechaInicioProgFacturacionD = fechaInicioProgFacturacionD;
	}

	/**
	 * @return the fechaFinProgFacturacionD
	 */
	public Date getFechaFinProgFacturacionD() {
		return fechaFinProgFacturacionD;
	}

	/**
	 * @param fechaFinProgFacturacionD the fechaFinProgFacturacionD to set
	 */
	public void setFechaFinProgFacturacionD(Date fechaFinProgFacturacionD) {
		this.fechaFinProgFacturacionD = fechaFinProgFacturacionD;
	}
}