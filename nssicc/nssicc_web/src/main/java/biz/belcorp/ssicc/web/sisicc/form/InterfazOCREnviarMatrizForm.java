/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazOCREnviarMatrizForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Agurto</a>
 * 
 * @struts.form name = "interfazOCREnviarMatrizForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazOCREnviarMatrizForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoMarca;

	private String codigoCanal;

	private String codigoPeriodoDesde;

	private String codigoPais;
	
	private String codigoPeriodoHasta;

		
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;		

	}


	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}	

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return Returns the codigoPeriodoDesde.
	 */
	public String getCodigoPeriodoDesde() {
		return codigoPeriodoDesde;
	}


	/**
	 * @struts.validator type="required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoPeriodoDesde The codigoPeriodoDesde to set.
	 */
	public void setCodigoPeriodoDesde(String codigoPeriodoDesde) {
		this.codigoPeriodoDesde = codigoPeriodoDesde;
	}


	/**
	 * @return Returns the codigoPeriodoHasta.
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}


	/**
	 * @struts.validator type="required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoPeriodoHasta The codigoPeriodoHasta to set.
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}

}