package biz.belcorp.ssicc.web.spusicc.archi.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoArchiEntidadBorradoAntiguoFechaSearchForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = 9197743241672313478L;
	
	private String codigoPais;
	private String codigoModulo;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}
	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}
	
	
	

}
