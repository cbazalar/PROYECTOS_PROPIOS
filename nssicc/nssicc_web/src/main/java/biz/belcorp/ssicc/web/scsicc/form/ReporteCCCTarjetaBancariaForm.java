package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
public class ReporteCCCTarjetaBancariaForm extends BaseReporteForm implements
		Serializable {

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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#setCodigoPais(java.lang.String)
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
	 * @param tipoReporte
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
	 * @return Returns the fechaCobroDesde.
	 */
	public String getFechaCobroDesde() {
		return fechaCobroDesde;
	}

	/**
	 * @param fechaCobroDesde
	 *            The fechaCobroDesde to set.
	 * 
	 */
	public void setFechaCobroDesde(String fechaCobroDesde) {
		this.fechaCobroDesde = fechaCobroDesde;
	}

	/**
	 * @return Returns the fechaCobroHasta.
	 */
	public String getFechaCobroHasta() {
		return fechaCobroHasta;
	}

	/**
	 * @param fechaCobroHasta
	 *            The fechaCobroHasta to set.
	 * 
	 */
	public void setFechaCobroHasta(String fechaCobroHasta) {
		this.fechaCobroHasta = fechaCobroHasta;
	}

	/**
	 * @return Returns the fechaProcDesde.
	 */
	public String getFechaProcDesde() {
		return fechaProcDesde;
	}

	/**
	 * @param fechaProcDesde
	 *            The fechaProcDesde to set.
	 * 
	 */
	public void setFechaProcDesde(String fechaProcDesde) {
		this.fechaProcDesde = fechaProcDesde;
	}

	/**
	 * @return Returns the fechaProcHasta.
	 */
	public String getFechaProcHasta() {
		return fechaProcHasta;
	}

	/**
	 * @param fechaProcHasta
	 *            The fechaProcHasta to set.
	 * 
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
	 * @param fechaCobroDesdeD
	 *            the fechaCobroDesdeD to set
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
	 * @param fechaCobroHastaD
	 *            the fechaCobroHastaD to set
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
	 * @param fechaProcDesdeD
	 *            the fechaProcDesdeD to set
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
	 * @param fechaProcHastaD
	 *            the fechaProcHastaD to set
	 */
	public void setFechaProcHastaD(Date fechaProcHastaD) {
		this.fechaProcHastaD = fechaProcHastaD;
	}
}