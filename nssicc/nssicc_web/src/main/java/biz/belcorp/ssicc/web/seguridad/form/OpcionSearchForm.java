package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class OpcionSearchForm extends BaseSearchForm{
	
	private static final long serialVersionUID = -7176829321654970449L;
	
	protected String codigoPais;
	protected String codigoOpcion;
	protected String descripcion;
	protected String estadoOpcion;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the codigoOpcion
	 */
	@Size(max=5)
	public String getCodigoOpcion() {
		return codigoOpcion;
	}
	/**
	 * @param codigoOpcion the codigoOpcion to set
	 */
	public void setCodigoOpcion(String codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}
	
	/**
	 * @return the descripcion
	 */
	@Size(max = 40)
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the estadoOpcion
	 */
	public String getEstadoOpcion() {
		return estadoOpcion;
	}
	/**
	 * @param estadoOpcion the estadoOpcion to set
	 */
	public void setEstadoOpcion(String estadoOpcion) {
		this.estadoOpcion = estadoOpcion;
	}	
}
