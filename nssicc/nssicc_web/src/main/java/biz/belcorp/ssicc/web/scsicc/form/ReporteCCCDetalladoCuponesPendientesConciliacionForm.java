package biz.belcorp.ssicc.web.scsicc.form;


import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteCCCDetalladoCuponesPendientesConciliacionForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoSociedad;
	
	private String fechaCuponDesde;
	
	private String fechaCuponHasta;
	
	private Date fechaCuponDesdeD;
	
	private Date fechaCuponHastaD;
    						
	private String importeCuponDesde;
	
	private String importeCuponHasta;
	
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
	 * 
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 *            The CodigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return the fechaCuponDesde
	 */
	public String getFechaCuponDesde() {
		return fechaCuponDesde;
	}

	/**
	 * @param fechaCuponDesde the fechaCuponDesde to set
	 */
	public void setFechaCuponDesde(String fechaCuponDesde) {
		this.fechaCuponDesde = fechaCuponDesde;
	}

	/**
	 * @return the fechaCuponHasta
	 */
	public String getFechaCuponHasta() {
		return fechaCuponHasta;
	}

	/**
	 * @param fechaCuponHasta the fechaCuponHasta to set
	 */
	public void setFechaCuponHasta(String fechaCuponHasta) {
		this.fechaCuponHasta = fechaCuponHasta;
	}

	/**
	 * @return the importeCuponDesde
	 */
	public String getImporteCuponDesde() {
		return importeCuponDesde;
	}

	/**
	 * @param importeCuponDesde the importeCuponDesde to set
	 */
	public void setImporteCuponDesde(String importeCuponDesde) {
		this.importeCuponDesde = importeCuponDesde;
	}

	/**
	 * @return the importeCuponHasta
	 */
	public String getImporteCuponHasta() {
		return importeCuponHasta;
	}

	/**
	 * @param importeCuponHasta the importeCuponHasta to set
	 */
	public void setImporteCuponHasta(String importeCuponHasta) {
		this.importeCuponHasta = importeCuponHasta;
	}

	/**
	 * @return the fechaCuponDesdeD
	 */
	public Date getFechaCuponDesdeD() {
		return fechaCuponDesdeD;
	}

	/**
	 * @param fechaCuponDesdeD the fechaCuponDesdeD to set
	 */
	public void setFechaCuponDesdeD(Date fechaCuponDesdeD) {
		this.fechaCuponDesdeD = fechaCuponDesdeD;
	}

	/**
	 * @return the fechaCuponHastaD
	 */
	public Date getFechaCuponHastaD() {
		return fechaCuponHastaD;
	}

	/**
	 * @param fechaCuponHastaD the fechaCuponHastaD to set
	 */
	public void setFechaCuponHastaD(Date fechaCuponHastaD) {
		this.fechaCuponHastaD = fechaCuponHastaD;
	}
}