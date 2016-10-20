package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCFacturarInteresSearchForm extends BaseSearchForm  implements Serializable{
			 
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
	private String codigoConsultora;
	private UploadedFile archivoCodigoConsultora;
	private String fechaProcesoDesde;
	private String fechaProcesoHasta;
	private Date fechaProcesoDesdeD;
	private Date fechaProcesoHastaD;
	private String[] listaMontoFacturado= {};
		
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
	 * @return the archivoCodigoConsultora
	 */
	public UploadedFile getArchivoCodigoConsultora() {
		return archivoCodigoConsultora;
	}

	/**
	 * @param archivoCodigoConsultora the archivoCodigoConsultora to set
	 */
	public void setArchivoCodigoConsultora(UploadedFile archivoCodigoConsultora) {
		this.archivoCodigoConsultora = archivoCodigoConsultora;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the fechaProcesoDesde
	 */
	public String getFechaProcesoDesde() {
		return fechaProcesoDesde;
	}

	/**
	 * @param fechaProcesoDesde the fechaProcesoDesde to set
	 */
	public void setFechaProcesoDesde(String fechaProcesoDesde) {
		this.fechaProcesoDesde = fechaProcesoDesde;
	}

	/**
	 * @return the fechaProcesoHasta
	 */
	public String getFechaProcesoHasta() {
		return fechaProcesoHasta;
	}

	/**
	 * @param fechaProcesoHasta the fechaProcesoHasta to set
	 */
	public void setFechaProcesoHasta(String fechaProcesoHasta) {
		this.fechaProcesoHasta = fechaProcesoHasta;
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

	/**
	 * @return the listaMontoFacturado
	 */
	public String[] getListaMontoFacturado() {
		return listaMontoFacturado;
	}

	/**
	 * @param listaMontoFacturado the listaMontoFacturado to set
	 */
	public void setListaMontoFacturado(String[] listaMontoFacturado) {
		this.listaMontoFacturado = listaMontoFacturado;
	}
}