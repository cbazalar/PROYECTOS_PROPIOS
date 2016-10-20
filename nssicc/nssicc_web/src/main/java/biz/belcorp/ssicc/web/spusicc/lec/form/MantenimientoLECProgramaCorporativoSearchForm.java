package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoLECProgramaCorporativoSearchForm extends BaseSearchForm implements Serializable{

	private static final long serialVersionUID = 3858049764590847123L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String descripcionPrograma;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	
	public String getDescripcionPrograma() {
		return descripcionPrograma;
	}
	
	public void setDescripcionPrograma(String descripcionPrograma) {
		this.descripcionPrograma = descripcionPrograma;
	}
}
