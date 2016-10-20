package biz.belcorp.ssicc.web.spusicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPRETiposReemplazoSearchForm extends BaseSearchForm{

	private static final long serialVersionUID = 1231805425620652285L;
	
	private String codigoPais;
	private String codigo;
	private String descripcion;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
