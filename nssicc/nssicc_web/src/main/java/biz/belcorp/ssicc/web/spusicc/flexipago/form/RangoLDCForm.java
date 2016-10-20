package biz.belcorp.ssicc.web.spusicc.flexipago.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;

public class RangoLDCForm extends BaseForm {
	
	/**
	 * JPPS
	 */
	private static final long serialVersionUID = -4428661811642151450L;
	private String oid;
	private String numeroSegmento;
	private String valorDesde;
	private String valorHasta;
	private String valorFactor;
	private String valorSegmento;
	private String estado;
	
	
	public String getOid() {
		return oid;
	}
	
	public void setOid(String oid) {
		this.oid = oid;
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
	
	public String getValorSegmento() {
		return valorSegmento;
	}
	
	public void setValorSegmento(String valorSegmento) {
		this.valorSegmento = valorSegmento;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
