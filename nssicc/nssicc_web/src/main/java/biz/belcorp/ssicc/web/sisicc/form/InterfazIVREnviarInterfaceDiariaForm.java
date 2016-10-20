/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazIVREnviarInterfaceDiariaForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Agurto</a>
 * 
 * @struts.form name = "interfazIVREnviarInterfaceDiariaForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazIVREnviarInterfaceDiariaForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoTipoBloqueo;

	private String codigoTipoCliente;

	private String codigoPais;

	public InterfazIVREnviarInterfaceDiariaForm() {

		this.codigoTipoCliente=Constants.CODIGO_TIPO_CLIENTE_DEFAULT;
		this.codigoTipoBloqueo=Constants.CODIGO_TIPO_BLOQUEO_DEFAULT;
	}
	
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
	 * @return Returns the codigoTipoBloqueo.
	 */
	public String getCodigoTipoBloqueo() {
		return codigoTipoBloqueo;
	}

	/**
	 * @param codigoTipoBloqueo
	 *            The codigoTipoBloqueo to set.
	 */
	public void setCodigoTipoBloqueo(String codigoTipoBloqueo) {
		this.codigoTipoBloqueo = codigoTipoBloqueo;
	}

	/**
	 * @return Returns the codigoTipoCliente.
	 */
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}

	/**
	 * @param codigoTipoCliente
	 *            The codigoTipoCliente to set.
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}

}