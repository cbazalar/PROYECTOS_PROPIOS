/*
 * Created on 15-dic-2005
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
 * <a href="NormaInterfazSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class NormaInterfazSearchForm extends BaseSearchForm{

	private static final long serialVersionUID = -4058415973580437384L;
	
	private String codigoPais;
	private String codigoTipoFormatoArchivo;
	
	
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
	 * @return Returns the codigoTipoFormatoArchivo.
	 */
	public String getCodigoTipoFormatoArchivo() {
		return codigoTipoFormatoArchivo;
	}
	/**
	 * @param codigoTipoFormatoArchivo The codigoTipoFormatoArchivo to set.
	 */
	public void setCodigoTipoFormatoArchivo(String codigoTipoFormatoArchivo) {
		this.codigoTipoFormatoArchivo = codigoTipoFormatoArchivo;
	}
}
