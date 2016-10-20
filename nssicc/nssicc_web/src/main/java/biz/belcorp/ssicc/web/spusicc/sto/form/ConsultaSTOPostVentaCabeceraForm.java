package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaSTOPostVentaCabeceraForm extends BaseSearchForm implements
		Serializable {

	private static final long serialVersionUID = -5121956172869983331L;

	private String codigoPais;
	private String consultora;
	private String nombre;
	private String zona;
	private String numDocuRUC;

	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getConsultora() {
		return consultora;
	}

	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getNumDocuRUC() {
		return numDocuRUC;
	}

	public void setNumDocuRUC(String numDocuRUC) {
		this.numDocuRUC = numDocuRUC;
	}

}
