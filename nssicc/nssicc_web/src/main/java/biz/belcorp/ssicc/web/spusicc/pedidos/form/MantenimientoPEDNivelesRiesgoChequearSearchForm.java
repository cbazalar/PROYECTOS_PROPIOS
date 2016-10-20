package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPEDNivelesRiesgoChequearSearchForm extends BaseSearchForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoTipoChequeo;
	
	private String codigoNivelRiesgo;
	
	/**
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return codigoTipoChequeo
	 */
	public String getCodigoTipoChequeo() {
		return codigoTipoChequeo;
	}

	/**
	 * @param codigoTipoChequeo
	 */
	public void setCodigoTipoChequeo(String codigoTipoChequeo) {
		this.codigoTipoChequeo = codigoTipoChequeo;
	}

	/**
	 * @return codigoNivelRiesgo
	 */
	public String getCodigoNivelRiesgo() {
		return codigoNivelRiesgo;
	}

	/**
	 * @param codigoNivelRiesgo
	 */
	public void setCodigoNivelRiesgo(String codigoNivelRiesgo) {
		this.codigoNivelRiesgo = codigoNivelRiesgo;
	}
}