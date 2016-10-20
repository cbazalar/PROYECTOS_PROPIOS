/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="SistemaSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class SistemaSearchForm extends BaseSearchForm {
	private static final long serialVersionUID = -7311319720660557943L;
	
	protected String codigoPais;
	protected String codigoSistema;
	protected String descripcionPais;
	protected String descripcionSistema;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}
	/**
	 * @param codigoSistema The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	/**
	 * @return Returns the descripcionPais.
	 */
	public String getDescripcionPais() {
		return descripcionPais;
	}
	/**
	 * @param descripcionPais The descripcionPais to set.
	 */
	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}
	/**
	 * @return Returns the descripcionSistema.
	 */
	@Size(max=100)
	public String getDescripcionSistema() {
		return descripcionSistema;
	}
	/**
	 * @param descripcionSistema The descripcionSistema to set.
	 */
	public void setDescripcionSistema(String descripcionSistema) {
		this.descripcionSistema = descripcionSistema;
	}	
}
