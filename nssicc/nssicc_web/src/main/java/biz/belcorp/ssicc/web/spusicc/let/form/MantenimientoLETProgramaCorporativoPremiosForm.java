package biz.belcorp.ssicc.web.spusicc.let.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoLETProgramaCorporativoPremiosForm extends BaseEditForm{
	
	private static final long serialVersionUID = -3294245245852648841L;
	
	private String campanyaPremio;
	private String codigoNivelPremio;
	private String variablePremio;
	private String codigoPremio;
	private String descripcionPremio;
	private String precioPremio;
	
	private String correlativo;
	private String estado;
	
	private String codigoPremioAnterior;
	
	
	public String getCampanyaPremio() {
		return campanyaPremio;
	}
	
	public void setCampanyaPremio(String campanyaPremio) {
		this.campanyaPremio = campanyaPremio;
	}
	
	public String getCodigoNivelPremio() {
		return codigoNivelPremio;
	}
	
	public void setCodigoNivelPremio(String codigoNivelPremio) {
		this.codigoNivelPremio = codigoNivelPremio;
	}
	
	public String getVariablePremio() {
		return variablePremio;
	}
	
	public void setVariablePremio(String variablePremio) {
		this.variablePremio = variablePremio;
	}
	
	public String getCodigoPremio() {
		return codigoPremio;
	}
	
	public void setCodigoPremio(String codigoPremio) {
		this.codigoPremio = codigoPremio;
	}
	
	public String getDescripcionPremio() {
		return descripcionPremio;
	}
	
	public void setDescripcionPremio(String descripcionPremio) {
		this.descripcionPremio = descripcionPremio;
	}
	
	public String getPrecioPremio() {
		return precioPremio;
	}
	
	public void setPrecioPremio(String precioPremio) {
		this.precioPremio = precioPremio;
	}
	
	public String getCorrelativo() {
		return correlativo;
	}
	
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCodigoPremioAnterior() {
		return codigoPremioAnterior;
	}
	
	public void setCodigoPremioAnterior(String codigoPremioAnterior) {
		this.codigoPremioAnterior = codigoPremioAnterior;
	}
	

}
