package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form de la recepcion de OCS Web - DD
 * Interfaz OCR.
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva</a>
 * 
 * @struts.form name = "interfazOCRRecepcionarOCSWebDDForm" extends =
 *                     "baseInterfazForm"
 */
public class InterfazOCRRecepcionarOCSWebDDForm extends BaseInterfazForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4768873183291033744L;
	private String tipoRecepcion ;
	private String codigoBatch;

	private String indValidacionSTO;	
	private String showValiAutoPediDigi;
	private Boolean indValidacionSTOBool;
	/**
	 * @return the codigoBatch
	 */
	public String getCodigoBatch() {
		return codigoBatch;
	}

	/**
	 * @param codigoBatch the codigoBatch to set
	 */
	public void setCodigoBatch(String codigoBatch) {
		this.codigoBatch = codigoBatch;
	}

	/**
	 * @return the tipoRecepcion
	 */
	public String getTipoRecepcion() {
		return tipoRecepcion;
	}

	/**
	 * @param tipoRecepcion the tipoRecepcion to set
	 */
	public void setTipoRecepcion(String tipoRecepcion) {
		this.tipoRecepcion = tipoRecepcion;
	}

	/**
	 * @return the indValidacionSTO
	 */
	public String getIndValidacionSTO() {
		return indValidacionSTO;
	}

	/**
	 * @param indValidacionSTO the indValidacionSTO to set
	 */
	public void setIndValidacionSTO(String indValidacionSTO) {
		this.indValidacionSTO = indValidacionSTO;
	}

	/**
	 * @return the showValiAutoPediDigi
	 */
	public String getShowValiAutoPediDigi() {
		return showValiAutoPediDigi;
	}

	/**
	 * @param showValiAutoPediDigi the showValiAutoPediDigi to set
	 */
	public void setShowValiAutoPediDigi(String showValiAutoPediDigi) {
		this.showValiAutoPediDigi = showValiAutoPediDigi;
	}

	/**
	 * @return the indValidacionSTOBool
	 */
	public Boolean getIndValidacionSTOBool() {
		return indValidacionSTOBool;
	}

	/**
	 * @param indValidacionSTOBool the indValidacionSTOBool to set
	 */
	public void setIndValidacionSTOBool(Boolean indValidacionSTOBool) {
		this.indValidacionSTOBool = indValidacionSTOBool;
	}
	
	
	
	
}