package biz.belcorp.ssicc.web.sisicc.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazPEREnviarRecaudosBancariosForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 * @struts.form name = "interfazPEREnviarRecaudosBancariosForm"
 */
public class InterfazPEREnviarRecaudosBancariosForm extends BaseInterfazForm  {
	
	private String codigoTipoOrigenDatos;
	
	private String numeroLoteInterno;
	
	/**
	 * @return Returns the codigoTipoOrigenDatos.
	 */
	public String getCodigoTipoOrigenDatos() {
		return codigoTipoOrigenDatos;
	}

	/**
	 * @param codigoTipoOrigenDatos The codigoTipoOrigenDatos to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoOrigenDatos(String codigoTipoOrigenDatos) {
		this.codigoTipoOrigenDatos = codigoTipoOrigenDatos;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.setNumeroLote(null);
		this.setNumeroLoteInterno(null);
	}

	/**
	 * @return Returns the numeroLoteInterno.
	 */
	public String getNumeroLoteInterno() {
		return numeroLoteInterno;
	}


	/**
	 * @param numeroLoteInterno The numeroLoteInterno to set.
	 * @struts.validator type = "required"
	 */
	public void setNumeroLoteInterno(String numeroLoteInterno) {
		this.numeroLoteInterno = numeroLoteInterno;
	}
	
	

}
