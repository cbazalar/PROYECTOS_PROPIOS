package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPEDSeleccionMatrizFacturacionForm extends BaseSearchForm  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String oidMatriz;
	private String codigoPeriodo;
	
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
	 * @return the oidMatriz
	 */
	public String getOidMatriz() {
		return oidMatriz;
	}
	/**
	 * @param oidMatriz the oidMatriz to set
	 */
	public void setOidMatriz(String oidMatriz) {
		this.oidMatriz = oidMatriz;
	}
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
}