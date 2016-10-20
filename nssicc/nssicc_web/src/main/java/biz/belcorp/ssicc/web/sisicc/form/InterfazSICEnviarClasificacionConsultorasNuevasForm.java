package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazSICEnviarClasificacionConsultorasNuevasForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 02/12/2014
 */
public class InterfazSICEnviarClasificacionConsultorasNuevasForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	private String codigoMarca;
	private String codigoCanal;
	private String periodo;
	private String codigoTipoCliente;
	private String[] codigoSubTipoCliente;
	private String campanhas;

	public InterfazSICEnviarClasificacionConsultorasNuevasForm() {
		super();
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoTipoCliente = Constants.CODIGO_TIPO_CLIENTE_DEFAULT;
		this.codigoSubTipoCliente = new String[] { Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT };
		this.campanhas = "4";
	}

	public String getCampanhas() {
		return campanhas;
	}

	/**
	 * @param campanhas 
	 * @struts.validator type = "required"
	 */
	public void setCampanhas(String campanhas) {
		this.campanhas = campanhas;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal 
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca 
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo 
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String[] getCodigoSubTipoCliente() {
		return codigoSubTipoCliente;
	}

	/**
	 * @param subTipoCliente 
	 * @struts.validator type = "required"
	 */
	public void setCodigoSubTipoCliente(String[] subTipoCliente) {
		this.codigoSubTipoCliente = subTipoCliente;
	}

	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}

	/**
	 * @param tipoCliente 
	 * @struts.validator type = "required"
	 */
	public void setCodigoTipoCliente(String tipoCliente) {
		this.codigoTipoCliente = tipoCliente;
	}
}