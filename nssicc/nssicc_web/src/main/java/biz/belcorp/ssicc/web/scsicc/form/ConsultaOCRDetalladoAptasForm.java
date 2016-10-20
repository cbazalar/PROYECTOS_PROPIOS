package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ConsultaOCRDetalladoAptasForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 01/09/2014
 */
public class ConsultaOCRDetalladoAptasForm extends	BaseReporteForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais ;
	private String codigoPaisSearch;
	private String resumen ;
	private String periodo ;
	private String fechaFact ;
	private String codRegion;
	private String[] codZonas;
	private String codigoConsultora;
	
	public String getFechaFact() {
		return fechaFact;
	}

    /**
     * @param fechaFact The fechaInicio to set.
     * @param fechaFact
     *            New value of property fechaFact.
     */

	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}


	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public String getResumen() {
		return resumen;
	}


	public void setResumen(String resumen) {
		this.resumen = resumen;
	}


	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	public String getCodRegion() {
		return codRegion;
	}


	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}

	public String getCodigoConsultora() {
		return codigoConsultora;
	}


	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}


	public String getCodigoPaisSearch() {
		return codigoPaisSearch;
	}


	public void setCodigoPaisSearch(String codigoPaisSearch) {
		this.codigoPaisSearch = codigoPaisSearch;
	}


	public String[] getCodZonas() {
		return codZonas;
	}


	public void setCodZonas(String[] codZonas) {
		this.codZonas = codZonas;
	}
	
}