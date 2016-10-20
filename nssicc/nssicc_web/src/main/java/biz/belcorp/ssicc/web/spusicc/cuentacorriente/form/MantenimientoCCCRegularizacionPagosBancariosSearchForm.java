package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCRegularizacionPagosBancariosSearchForm extends BaseSearchForm  implements Serializable{
			 
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
				
	private String codigoCuentaCorrienteBancaria;
	
	private String codigoError;
	
	private String[] codigoConsultora;
	
	private String fechaPagoDesde;
	private Date fechaPagoDesdeD;
	
	private String fechaPagoHasta;
	private Date fechaPagoHastaD; 
	
	private String fechaProcesoDesde;
	private Date fechaProcesoDesdeD;
	
	private String fechaProcesoHasta;
	private Date fechaProcesoHastaD;
	
	private String importePagoDesde;
	
	
	private String importePagoHasta;
	
	private String nombreConsultora;
	
	private String codigoCuentaCorrienteBancariaSearch;
			
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
	 * @return the codigoCuentaCorrienteBancaria
	 */
	public String getCodigoCuentaCorrienteBancaria() {
		return codigoCuentaCorrienteBancaria;
	}

	/**
	 * @param codigoCuentaCorrienteBancaria the codigoCuentaCorrienteBancaria to set
	 */
	public void setCodigoCuentaCorrienteBancaria(
			String codigoCuentaCorrienteBancaria) {
		this.codigoCuentaCorrienteBancaria = codigoCuentaCorrienteBancaria;
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
	 * @return the importePagoDesde
	 */
	public String getImportePagoDesde() {
		return importePagoDesde;
	}

	/**
	 * @param importePagoDesde the importePagoDesde to set
	 */
	public void setImportePagoDesde(String importePagoDesde) {
		this.importePagoDesde = importePagoDesde;
	}

	/**
	 * @return the importePagoHasta
	 */
	public String getImportePagoHasta() {
		return importePagoHasta;
	}

	/**
	 * @param importePagoHasta the importePagoHasta to set
	 */
	public void setImportePagoHasta(String importePagoHasta) {
		this.importePagoHasta = importePagoHasta;
	}
	
	/**
	 * @return the codigoConsultora
	 */
	public String[] getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String[] codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return Returns the fechaPagoDesde.
	 */
	public String getFechaPagoDesde() {
		return fechaPagoDesde;
	}

	/**
	 * 
	 * @param fechaPagoDesde
	 *            The fechaPagoDesde to set.
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
	 * 
	 * @param fechaPagoHasta
	 *            The fechaPagoHasta to set.
	 */
	public void setFechaPagoHasta(String fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
	}
	
	/**
	 * @return Returns the fechaProcesoDesde
	 */
	public String getFechaProcesoDesde() {
		return fechaProcesoDesde;
	}

	/**
	 * 
	 * @param fechaProcesoDesde
	 *            The fechaProcesoDesde to set.
	 */
	public void setFechaProcesoDesde(String fechaProcesoDesde) {
		this.fechaProcesoDesde = fechaProcesoDesde;
	}

	/**
	 * @return Returns the fechaProcesoHasta
	 */
	public String getFechaProcesoHasta() {
		return fechaProcesoHasta;
	}

	/**
	 * 
	 * @param fechaProcesoHasta
	 *            The fechaProcesoHasta to set.
	 */
	public void setFechaProcesoHasta(String fechaProcesoHasta) {
		this.fechaProcesoHasta = fechaProcesoHasta;
	}

	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}

	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	/**
	 * @return the codigoCuentaCorrienteBancariaSearch
	 */
	public String getCodigoCuentaCorrienteBancariaSearch() {
		return codigoCuentaCorrienteBancariaSearch;
	}

	/**
	 * @param codigoCuentaCorrienteBancariaSearch the codigoCuentaCorrienteBancariaSearch to set
	 */
	public void setCodigoCuentaCorrienteBancariaSearch(
			String codigoCuentaCorrienteBancariaSearch) {
		this.codigoCuentaCorrienteBancariaSearch = codigoCuentaCorrienteBancariaSearch;
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
	 * @return the fechaProcesoDesdeD
	 */
	public Date getFechaProcesoDesdeD() {
		return fechaProcesoDesdeD;
	}

	/**
	 * @param fechaProcesoDesdeD the fechaProcesoDesdeD to set
	 */
	public void setFechaProcesoDesdeD(Date fechaProcesoDesdeD) {
		this.fechaProcesoDesdeD = fechaProcesoDesdeD;
	}

	/**
	 * @return the fechaProcesoHastaD
	 */
	public Date getFechaProcesoHastaD() {
		return fechaProcesoHastaD;
	}

	/**
	 * @param fechaProcesoHastaD the fechaProcesoHastaD to set
	 */
	public void setFechaProcesoHastaD(Date fechaProcesoHastaD) {
		this.fechaProcesoHastaD = fechaProcesoHastaD;
	}
}