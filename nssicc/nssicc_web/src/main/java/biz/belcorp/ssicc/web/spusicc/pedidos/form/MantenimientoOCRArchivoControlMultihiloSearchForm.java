package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoOCRArchivoControlMultihiloSearchForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = -5738347292139308162L;
	
	private String codigoPais ;
	private String numeroFilas;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getNumeroFilas() {
		return numeroFilas;
	}
	
	public void setNumeroFilas(String numeroFilas) {
		this.numeroFilas = numeroFilas;
	}
	
}
