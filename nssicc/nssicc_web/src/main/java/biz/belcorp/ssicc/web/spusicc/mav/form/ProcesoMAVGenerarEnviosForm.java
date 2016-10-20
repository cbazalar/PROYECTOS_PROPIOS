package biz.belcorp.ssicc.web.spusicc.mav.form;

import java.io.Serializable;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoMAVGenerarEnviosForm extends BaseProcesoForm  implements Serializable{
	
	private static final long serialVersionUID = 454171191952455035L;
	
	private String codigoPeriodo;
	private String codigoActividad;
	
	private String tipoCliente;
	private String codigoTipoMav;

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoActividad
	 */
	public String getCodigoActividad() {
		return codigoActividad;
	}

	/**
	 * @param codigoActividad the codigoActividad to set
	 */
	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return the codigoTipoMav
	 */
	public String getCodigoTipoMav() {
		return codigoTipoMav;
	}

	/**
	 * @param codigoTipoMav the codigoTipoMav to set
	 */
	public void setCodigoTipoMav(String codigoTipoMav) {
		this.codigoTipoMav = codigoTipoMav;
	}	

}

