package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRECExcepcionesTruequesForm extends BaseSearchForm implements Serializable{

	private static final long serialVersionUID = 6344073781855972050L;
	
	private String codigoPais;
	private String codigoSapDevuelve;
	private String codigoSapEnvia;
	
	private String descripcionCodigoSapDevuelve;
	private String descripcionCodigoSapEnvia;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoSapDevuelve() {
		return codigoSapDevuelve;
	}
	
	public void setCodigoSapDevuelve(String codigoSapDevuelve) {
		this.codigoSapDevuelve = codigoSapDevuelve;
	}

	public String getCodigoSapEnvia() {
		return codigoSapEnvia;
	}
	
	public void setCodigoSapEnvia(String codigoSapEnvia) {
		this.codigoSapEnvia = codigoSapEnvia;
	}
	
	public String getDescripcionCodigoSapDevuelve() {
		return descripcionCodigoSapDevuelve;
	}
	
	public void setDescripcionCodigoSapDevuelve(String descripcionCodigoSapDevuelve) {
		this.descripcionCodigoSapDevuelve = descripcionCodigoSapDevuelve;
	}
	
	public String getDescripcionCodigoSapEnvia() {
		return descripcionCodigoSapEnvia;
	}
	
	public void setDescripcionCodigoSapEnvia(String descripcionCodigoSapEnvia) {
		this.descripcionCodigoSapEnvia = descripcionCodigoSapEnvia;
	}	
}
