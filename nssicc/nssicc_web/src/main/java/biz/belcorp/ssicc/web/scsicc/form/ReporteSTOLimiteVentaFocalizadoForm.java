package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.upload.FormFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSTOLimiteVentaFocalizadoForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPeriodo;
	private String fechaInicioCarga;
	private String horaInicioCarga;
	private String fechaFinCarga;
	private String horaFinCarga;

	private String fechaInicioProceso;
	private String horaInicioProceso;
	private String fechaFinProceso;
	private String horaFinProceso;

	private String fechaProgFacturacion;
	private String horaProgFacturacion;
	private Date fechaInicioCargaD;
	private Date fechaFinCargaD;
	private Date fechaInicioProcesoD;
	private Date fechaFinProcesoD;
	private Date fechaProgFacturacionD;

	private String numeroLote;

	private String codigoCliente;

	private String[] regionList;
	private String[] zonaList;
	private String[] clienteList;

	private String detalleModificado;

	private FormFile clienteFile; // objeto que se utilizara para el upload

	/*
	 * private String cargaCombo;
	 * 
	 * private String fechaInicioSearch; private String fechaFinSearch; private
	 * String numeroLoteSearch; private String codigoClienteSearch; private
	 * String fechaInicioProcesoSearch; private String fechaFinProcesoSearch;
	 * private String horaInicioCargaSearch; private String horaFinCargaSearch;
	 * private String horaInicioProcesoSearch; private String
	 * horaFinProcesoSearch; private String codigoPeriodoSearch; private String
	 * codigoOrigenSearch; private String fechaInicioFactProgSearch; private
	 * String fechaFinFactProgSearch; private String horaInicioFactProgSearch;
	 * private String horaFinFactProgSearch; private String
	 * descripcionDocumento; private String secuencia; private String
	 * flagFechaFact;
	 * 
	 * 
	 * /**
	 * 
	 * @return flagFechaFact
	 */

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
	 * @return the horaProgFacturacion
	 */
	public String getHoraProgFacturacion() {
		return horaProgFacturacion;
	}

	/**
	 * @param horaProgFacturacion
	 *            the horaProgFacturacion to set
	 */
	public void setHoraProgFacturacion(String horaProgFacturacion) {
		this.horaProgFacturacion = horaProgFacturacion;
	}

	/**
	 * @return the detalleModificado
	 */
	public String getDetalleModificado() {
		return detalleModificado;
	}

	/**
	 * @param detalleModificado
	 *            the detalleModificado to set
	 */
	public void setDetalleModificado(String detalleModificado) {
		this.detalleModificado = detalleModificado;
	}

	/**
	 * @return the clienteFile
	 */
	public FormFile getClienteFile() {
		return clienteFile;
	}

	/**
	 * @param clienteFile
	 *            the clienteFile to set
	 */
	public void setClienteFile(FormFile clienteFile) {
		this.clienteFile = clienteFile;
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
	 * @return the fechaProgFacturacionD
	 */
	public Date getFechaProgFacturacionD() {
		return fechaProgFacturacionD;
	}

	/**
	 * @param fechaProgFacturacionD the fechaProgFacturacionD to set
	 */
	public void setFechaProgFacturacionD(Date fechaProgFacturacionD) {
		this.fechaProgFacturacionD = fechaProgFacturacionD;
	}
}