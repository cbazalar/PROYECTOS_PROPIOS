package biz.belcorp.ssicc.web.scsicc.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author Yahir Rivas Luna
 * 
 * @struts.form name = "reporteRETFaltantesRetailForm"
 */
public class ReporteRETFaltantesRetailForm extends BaseReporteForm {

	private static final long serialVersionUID = 6290607920552680252L;

	private String codigoPais;

	private String codigoPeriodo;

	private String[] codigoRegion;


	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.struts.action.ActionForm#reset(org.apache.struts.action.
	 * ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.codigoRegion = null;
	}


	/**
	 * @return Returns the codigoComisionIngreso.
	 */

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 *            The codigoRegion to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

}
