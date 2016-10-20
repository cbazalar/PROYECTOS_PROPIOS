/*
 * Created on 02-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 * @struts.form name = "interfazREUEnviarConsultorasForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazREUEnviarConsultorasForm extends BaseInterfazForm
		implements Serializable {
	private String codigoTipoCliente;

	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}

	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}

	public InterfazREUEnviarConsultorasForm() {
		this.codigoTipoCliente = Constants.CODIGO_TIPO_CLIENTE_DEFAULT;
	}
}
