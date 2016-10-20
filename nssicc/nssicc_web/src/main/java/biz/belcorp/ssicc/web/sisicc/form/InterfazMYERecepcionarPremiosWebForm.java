package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


public class InterfazMYERecepcionarPremiosWebForm extends BaseInterfazForm	implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String pesoArchivo;

	/**
	 * @return the pesoArchivo
	 */
	public String getPesoArchivo() {
		return pesoArchivo;
	}

	/**
	 * @param pesoArchivo the pesoArchivo to set
	 */
	public void setPesoArchivo(String pesoArchivo) {
		this.pesoArchivo = pesoArchivo;
	}
}
