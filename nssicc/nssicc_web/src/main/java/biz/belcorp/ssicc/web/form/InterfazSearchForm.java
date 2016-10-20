/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazSearchForm extends BaseSearchForm {
	
	private static final long serialVersionUID = 1558818116992556268L;
	
	protected String codigoPais;
	protected String codigoSistema;
	protected String codigoInterfaz;
	protected String tipoInterfaz;
	protected String descripcionInterfaz;
		
	/**
	 * @return Returns the codigoInterfaz.
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}
	/**
	 * @param codigoInterfaz The codigoInterfaz to set.
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}
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
	 * @return Returns the descripcionInterfaz.
	 */
	public String getDescripcionInterfaz() {
		return descripcionInterfaz;
	}
	/**
	 * @param descripcionInterfaz The descripcionInterfaz to set.
	 */
	public void setDescripcionInterfaz(String descripcionInterfaz) {
		this.descripcionInterfaz = descripcionInterfaz;
	}
	/**
	 * @return Returns the tipoInterfaz.
	 */
	public String getTipoInterfaz() {
		return tipoInterfaz;
	}
	/**
	 * @param tipoInterfaz The tipoInterfaz to set.
	 */
	public void setTipoInterfaz(String tipoInterfaz) {
		this.tipoInterfaz = tipoInterfaz;
	}	
}
