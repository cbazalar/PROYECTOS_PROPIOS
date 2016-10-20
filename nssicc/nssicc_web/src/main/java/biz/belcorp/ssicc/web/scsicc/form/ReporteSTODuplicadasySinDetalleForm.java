package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteSTODuplicadasySinDetalleForm extends BaseReporteForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889795651978641360L;

	private String codigoPais;
	private String tipoDocumento;
	private String fechaInicioProceso;
	private String fechaFinProceso;
	private Date fechaInicioProcesoD;
	private Date fechaFinProcesoD;
	private String numeroLote;
	private String codigoCliente;
	private String horaInicioCarga;
	private String horaFinCarga;
	private String codigoPeriodo;
	private String tipoConsulta;

	/**
	 * Valores por default
	 */
	public ReporteSTODuplicadasySinDetalleForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicio = new Date(System.currentTimeMillis());
		Date fechaFin = new Date(System.currentTimeMillis());

		this.horaInicioCarga = "00:00";
		this.horaFinCarga = "00:00";
		this.fechaInicioProcesoD = new Date();
		Calendar hoy = Calendar.getInstance();
		hoy.add(Calendar.DATE, 1);
		this.fechaFinProcesoD=hoy.getTime();
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
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
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the fechaInicioProceso
	 */
	public String getFechaInicioProceso() {
		return fechaInicioProceso;
	}

	/**
	 * @param fechaInicioProceso
	 *            the fechaInicioProceso to set
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
	 * @param fechaFinProceso
	 *            the fechaFinProceso to set
	 */
	public void setFechaFinProceso(String fechaFinProceso) {
		this.fechaFinProceso = fechaFinProceso;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 *            the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 *            the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the horaInicioCarga
	 */
	public String getHoraInicioCarga() {
		return horaInicioCarga;
	}

	/**
	 * @param horaInicioCarga
	 *            the horaInicioCarga to set
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
	 * @param horaFinCarga
	 *            the horaFinCarga to set
	 */
	public void setHoraFinCarga(String horaFinCarga) {
		this.horaFinCarga = horaFinCarga;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the tipoConsulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * @param tipoConsulta
	 *            the tipoConsulta to set
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
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
