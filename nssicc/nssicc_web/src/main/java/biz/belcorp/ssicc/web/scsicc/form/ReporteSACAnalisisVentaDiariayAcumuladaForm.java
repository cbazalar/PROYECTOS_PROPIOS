package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACAnalisisVentaDiariayAcumuladaForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 12/09/2014
 */
public class ReporteSACAnalisisVentaDiariayAcumuladaForm extends BaseReporteForm {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo pais. */
	private String codigoPais;

	/** The codigo periodo. */
	private String codigoPeriodo;

	/** The tipo negocio. */
	private String tipoNegocio;

	/** The vista reporte. */
	private String vistaReporte;
	
	/** The fecha proceso. */
	private String fechaProceso;
	
	/** The fecha proceso date. */
	private Date fechaProcesoDate;
	
	/** The reporte. */
	private String reporte;
	
	/**
	 * 
	 * @return Returns the codigoPais.
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getVistaReporte() {
		return vistaReporte;
	}

	/**
	 * @param vistaReporte The vistaReporte to set.
	 */
	public void setVistaReporte(String vistaReporte) {
		this.vistaReporte = vistaReporte;
	}
	public String getTipoNegocio() {
		return tipoNegocio;
	}
	
	/**
	 * @param tipoNegocio The tipoNegocio to set.
	 */
	public void setTipoNegocio(String tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	
	
}
