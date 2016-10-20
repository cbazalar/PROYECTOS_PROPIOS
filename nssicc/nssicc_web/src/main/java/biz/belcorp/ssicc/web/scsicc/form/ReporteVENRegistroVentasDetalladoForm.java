package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteVENRegistroVentasDetalladoForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 11/11/2014
 */
public class ReporteVENRegistroVentasDetalladoForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String fechaDesde;
	private String fechaHasta;	
	private boolean flagExcel;	
	private boolean flagPdf;
	private Date fechaDesdeD;
	private Date fechaHastaD;
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}
	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	/**
	 * @return the flagExcel
	 */
	public boolean isFlagExcel() {
		return flagExcel;
	}
	/**
	 * @param flagExcel the flagExcel to set
	 */
	public void setFlagExcel(boolean flagExcel) {
		this.flagExcel = flagExcel;
	}
	/**
	 * @return the flagPdf
	 */
	public boolean isFlagPdf() {
		return flagPdf;
	}
	/**
	 * @param flagPdf the flagPdf to set
	 */
	public void setFlagPdf(boolean flagPdf) {
		this.flagPdf = flagPdf;
	}
	/**
	 * @return the fechaDesdeD
	 */
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}
	/**
	 * @param fechaDesdeD the fechaDesdeD to set
	 */
	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}
	/**
	 * @return the fechaHastaD
	 */
	public Date getFechaHastaD() {
		return fechaHastaD;
	}
	/**
	 * @param fechaHastaD the fechaHastaD to set
	 */
	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}

}
