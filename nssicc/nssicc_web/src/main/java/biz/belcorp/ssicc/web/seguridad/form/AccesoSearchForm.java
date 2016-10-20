package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * 
 * </p>
 * 
 * @author <a href="mailto:dhinostroza@belcorp.biz">David Hinostroza Vintes</a>
 * @struts.form name="accesoForm"
 */
public class AccesoSearchForm   extends BaseSearchForm{
	
	private static final long serialVersionUID = 2451268138336678506L;
	
	protected String codigoPais;
	protected String codigoRol;
	protected String descripcionRol;
	
	/**
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return codigoRol
	 */
	@Size(max=6)
	public String getCodigoRol() {
		return codigoRol;
	}
	
	/**
	 * @param codigoRol
	 */
	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}
	
	/**
	 * @return descripcionRol
	 */
	@Size(max = 40)
	public String getDescripcionRol() {
		return descripcionRol;
	}
	
	/**
	 * @param descripcionRol
	 */
	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}	
}
