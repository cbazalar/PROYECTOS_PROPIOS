package biz.belcorp.ssicc.web.spusicc.sgr.form;



import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
public class MantenimientoSGRPolizaSearchForm extends BaseSearchForm {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPoliza;
	private String descripcionPoliza;
	
	
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
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.form.BaseSearchForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {	
		this.codigoPais = "";
		this.codigoPoliza = this.descripcionPoliza="";
	
	}

	/**
	 * @return the codigoPoliza
	 */
	public String getCodigoPoliza() {
		return codigoPoliza;
	}

	/**
	 * @param codigoPoliza the codigoPoliza to set
	 */
	public void setCodigoPoliza(String codigoPoliza) {
		this.codigoPoliza = codigoPoliza;
	}

	/**
	 * @return the descripcionPoliza
	 */
	public String getDescripcionPoliza() {
		return descripcionPoliza;
	}

	/**
	 * @param descripcionPoliza the descripcionPoliza to set
	 */
	public void setDescripcionPoliza(String descripcionPoliza) {
		this.descripcionPoliza = descripcionPoliza;
	}
	
	

}