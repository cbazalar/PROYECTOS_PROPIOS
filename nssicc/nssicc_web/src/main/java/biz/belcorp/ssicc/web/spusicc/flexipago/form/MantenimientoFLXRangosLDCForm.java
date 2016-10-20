package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFLXRangosLDCForm extends BaseSearchForm{
	
	/**
	 * JPPS
	 */
	private static final long serialVersionUID = -7063739564148069940L;
	private String codigoPais;	
	private String numeroSegmento;
	private String valorDesde;
	private String valorHasta;
	private String valorFactor;
	
	private RangoLDCForm[] rangosLDCForm = new RangoLDCForm[0];


	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	
	public RangoLDCForm[] getRangosLDCForm() {
		return rangosLDCForm;
	}

	
	public void setRangosLDCForm(RangoLDCForm[] rangosLDCForm) {
		this.rangosLDCForm = rangosLDCForm;
	}

	
	public String getNumeroSegmento() {
		return numeroSegmento;
	}

	
	public void setNumeroSegmento(String numeroSegmento) {
		this.numeroSegmento = numeroSegmento;
	}

	
	public String getValorDesde() {
		return valorDesde;
	}

	
	public void setValorDesde(String valorDesde) {
		this.valorDesde = valorDesde;
	}

	
	public String getValorHasta() {
		return valorHasta;
	}

	
	public void setValorHasta(String valorHasta) {
		this.valorHasta = valorHasta;
	}

	public String getValorFactor() {
		return valorFactor;
	}

	
	public void setValorFactor(String valorFactor) {
		this.valorFactor = valorFactor;
	}


	
}
