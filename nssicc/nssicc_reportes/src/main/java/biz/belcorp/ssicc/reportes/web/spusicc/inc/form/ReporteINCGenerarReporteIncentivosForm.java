package biz.belcorp.ssicc.reportes.web.spusicc.inc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 *	TODO Include class description here.
 *
 * <p>
 * <a href="ReporteINCGenerarReporteIncentivosForm.java.html"> <i>View Source</i> </a>
 * </p> 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a> 
 */
public class ReporteINCGenerarReporteIncentivosForm extends BaseReporteForm
		implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoMarca;
	private String codigoCanal;
	private String descripcionMarca;
	private String tipoCierre;
	private String codigoPeriodo;
	private String fechaProceso;
	private String formatoExportacion;
	private String numeroLote;
	private String mensajeErrorMail;
	private String numeroLoteReporteDespachoInc;
	private Date fechaProcesoDate;

	public ReporteINCGenerarReporteIncentivosForm() {	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.tipoCierre = codigoPeriodo = "";
		this.fechaProceso = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaProcesoDate = new Date();
	}
	
	/**
	 * @return the mensajeErrorMail
	 */
	public String getMensajeErrorMail() {
		return mensajeErrorMail;
	}

	/**
	 * @param mensajeErrorMail the mensajeErrorMail to set
	 */
	public void setMensajeErrorMail(String mensajeErrorMail) {
		this.mensajeErrorMail = mensajeErrorMail;
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

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set	 
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the tipoCierre
	 */
	public String getTipoCierre() {
		return tipoCierre;
	}

	/**
	 * @param tipoCierre the tipoCierre to set	 
	 */
	public void setTipoCierre(String tipoCierre) {
		this.tipoCierre = tipoCierre;
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
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}


	/**
	 * @return Returns the descripcionMarca.
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca
	 *            The descripcionMarca to set.
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	/**
	 * @return the fechaProcesoDate
	 */
	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	/**
	 * @param fechaProcesoDate the fechaProcesoDate to set
	 */
	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}

	/**
	 * @return the numeroLoteReporteDespachoInc
	 */
	public String getNumeroLoteReporteDespachoInc() {
		return numeroLoteReporteDespachoInc;
	}

	/**
	 * @param numeroLoteReporteDespachoInc the numeroLoteReporteDespachoInc to set
	 */
	public void setNumeroLoteReporteDespachoInc(String numeroLoteReporteDespachoInc) {
		this.numeroLoteReporteDespachoInc = numeroLoteReporteDespachoInc;
	}
	
	
	
}
