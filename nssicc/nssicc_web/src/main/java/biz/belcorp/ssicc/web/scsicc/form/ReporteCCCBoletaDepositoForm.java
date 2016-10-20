package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteCCCBoletaDepositoForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 10/11/2014
 */
public class ReporteCCCBoletaDepositoForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;				
	private String codigoBanco;	
	private String tipoReporte;	
	private String fechaCobroDesde;
	private String fechaCobroHasta;
	private String fechaProcDesde;
	private String fechaProcHasta;
	private Date fechaCobroDesdeD;
	private Date fechaCobroHastaD;
	private Date fechaProcDesdeD;
	private Date fechaProcHastaD;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	
	/**
	 * @return the tipoReporte
	 * 
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}


	/**
	 * @param tipoReporte the tipoReporte to set
	 * @struts.validator type = "required"
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	/**
	 * @return Returns the codigoBanco.
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco
	 *            The codigoBanco to set.
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return the fechaCobroDesde
	 */
	public String getFechaCobroDesde() {
		return fechaCobroDesde;
	}

	/**
	 * @param fechaCobroDesde the fechaCobroDesde to set
	 */
	public void setFechaCobroDesde(String fechaCobroDesde) {
		this.fechaCobroDesde = fechaCobroDesde;
	}

	/**
	 * @return the fechaCobroHasta
	 */
	public String getFechaCobroHasta() {
		return fechaCobroHasta;
	}

	/**
	 * @param fechaCobroHasta the fechaCobroHasta to set
	 */
	public void setFechaCobroHasta(String fechaCobroHasta) {
		this.fechaCobroHasta = fechaCobroHasta;
	}

	/**
	 * @return the fechaProcDesde
	 */
	public String getFechaProcDesde() {
		return fechaProcDesde;
	}

	/**
	 * @param fechaProcDesde the fechaProcDesde to set
	 */
	public void setFechaProcDesde(String fechaProcDesde) {
		this.fechaProcDesde = fechaProcDesde;
	}

	/**
	 * @return the fechaProcHasta
	 */
	public String getFechaProcHasta() {
		return fechaProcHasta;
	}

	/**
	 * @param fechaProcHasta the fechaProcHasta to set
	 */
	public void setFechaProcHasta(String fechaProcHasta) {
		this.fechaProcHasta = fechaProcHasta;
	}

	/**
	 * @return the fechaCobroDesdeD
	 */
	public Date getFechaCobroDesdeD() {
		return fechaCobroDesdeD;
	}

	/**
	 * @param fechaCobroDesdeD the fechaCobroDesdeD to set
	 */
	public void setFechaCobroDesdeD(Date fechaCobroDesdeD) {
		this.fechaCobroDesdeD = fechaCobroDesdeD;
	}

	/**
	 * @return the fechaCobroHastaD
	 */
	public Date getFechaCobroHastaD() {
		return fechaCobroHastaD;
	}

	/**
	 * @param fechaCobroHastaD the fechaCobroHastaD to set
	 */
	public void setFechaCobroHastaD(Date fechaCobroHastaD) {
		this.fechaCobroHastaD = fechaCobroHastaD;
	}

	/**
	 * @return the fechaProcDesdeD
	 */
	public Date getFechaProcDesdeD() {
		return fechaProcDesdeD;
	}

	/**
	 * @param fechaProcDesdeD the fechaProcDesdeD to set
	 */
	public void setFechaProcDesdeD(Date fechaProcDesdeD) {
		this.fechaProcDesdeD = fechaProcDesdeD;
	}

	/**
	 * @return the fechaProcHastaD
	 */
	public Date getFechaProcHastaD() {
		return fechaProcHastaD;
	}

	/**
	 * @param fechaProcHastaD the fechaProcHastaD to set
	 */
	public void setFechaProcHastaD(Date fechaProcHastaD) {
		this.fechaProcHastaD = fechaProcHastaD;
	}

}
