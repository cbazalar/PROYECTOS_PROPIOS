/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazOCREnviarArchivosOCRForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos� A. Cairampoma </a>
 * 
 * @struts.form name = "interfazOCREnviarArchivosOCRForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazOCREnviarArchivosOCRForm extends   BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoMarca;

	private String codigoCanal;

	private String codigoPeriodoDesde;
	
	private String codigoPeriodoHasta;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;		

	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the codigoPeriodoDesde
	 */
	public String getCodigoPeriodoDesde() {
		return codigoPeriodoDesde;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPeriodoDesde the codigoPeriodoDesde to set
	 */
	public void setCodigoPeriodoDesde(String codigoPeriodoDesde) {
		this.codigoPeriodoDesde = codigoPeriodoDesde;
	}

	/**
	 * @return the codigoPeriodoHasta
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPeriodoHasta the codigoPeriodoHasta to set
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}
    
	
   	
}
