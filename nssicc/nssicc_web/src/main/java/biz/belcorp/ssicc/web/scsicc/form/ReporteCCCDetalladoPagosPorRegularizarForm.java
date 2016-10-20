package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCDetalladoPagosPorRegularizarForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoBanco;

	private String codigoError;

	private String fechaPagoDesde;

	private String fechaPagoHasta;

	private String fechaProcDesde;

	private String fechaProcHasta;
	
	private Date fechaPagoDesdeD;

	private Date fechaPagoHastaD;

	private Date fechaProcDesdeD;

	private Date fechaProcHastaD;

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
	 * @return Returns codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * @param codigoError
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * @return Returns the fechaPagoDesde.
	 */
	public String getFechaPagoDesde() {
		return fechaPagoDesde;
	}

	/**
	 * @param fechaPagoDesde
	 *            The fechapagoDesde to set.
	 * 
	 */
	public void setFechaPagoDesde(String fechaPagoDesde) {
		this.fechaPagoDesde = fechaPagoDesde;
	}

	/**
	 * @return Returns the fechaPagoHasta.
	 */
	public String getFechaPagoHasta() {
		return fechaPagoHasta;
	}

	/**
	 * @param fechaPagoHasta
	 *            The fechapagoHasta to set.
	 * 
	 */
	public void setFechaPagoHasta(String fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
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
	 * @return the fechaPagoDesdeD
	 */
	public Date getFechaPagoDesdeD() {
		return fechaPagoDesdeD;
	}

	/**
	 * @param fechaPagoDesdeD the fechaPagoDesdeD to set
	 */
	public void setFechaPagoDesdeD(Date fechaPagoDesdeD) {
		this.fechaPagoDesdeD = fechaPagoDesdeD;
	}

	/**
	 * @return the fechaPagoHastaD
	 */
	public Date getFechaPagoHastaD() {
		return fechaPagoHastaD;
	}

	/**
	 * @param fechaPagoHastaD the fechaPagoHastaD to set
	 */
	public void setFechaPagoHastaD(Date fechaPagoHastaD) {
		this.fechaPagoHastaD = fechaPagoHastaD;
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