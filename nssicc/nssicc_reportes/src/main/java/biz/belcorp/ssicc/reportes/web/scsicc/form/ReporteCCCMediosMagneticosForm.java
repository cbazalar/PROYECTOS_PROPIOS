
package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



public class ReporteCCCMediosMagneticosForm extends BaseReporteForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String fechaDesde;
	private Date fechaDesdeD;
	private String fechaHasta;
	private Date fechaHastaD;
	
	
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}

	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}

	public Date getFechaHastaD() {
		return fechaHastaD;
	}

	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}

	private String formatoExportacion;
	private String tipoReporteAMostrar;
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		this.codigoPais = this.fechaDesde=this.fechaHasta="";
		this.formatoExportacion=Constants.NUMERO_CERO;
		this.tipoReporteAMostrar="";
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @return the tipoReporteAMostrar
	 */
	public String getTipoReporteAMostrar() {
		return tipoReporteAMostrar;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	/**
	 * @param tipoReporteAMostrar the tipoReporteAMostrar to set
	 */
	public void setTipoReporteAMostrar(String tipoReporteAMostrar) {
		this.tipoReporteAMostrar = tipoReporteAMostrar;
	}
	
}
