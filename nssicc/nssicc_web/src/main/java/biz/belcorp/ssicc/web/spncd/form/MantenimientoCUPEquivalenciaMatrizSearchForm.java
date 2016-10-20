package biz.belcorp.ssicc.web.spncd.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCUPEquivalenciaMatrizSearchForm extends BaseSearchForm {
	
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String codigoPais;

	private String codigoPrograma;

	private String codigoPeriodo;

	private String codigoCupon;

	private String codigoNivel;

	/**
	 * @return Returns the codigoPais.
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoPrograma.
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return Returns the codigoNivel.
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}

	/**
	 * @param codigoNivel The codigoNivel to set.
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}

	/**
	 * @return Returns the codigoCupon.
	 */
	public String getCodigoCupon() {
		return codigoCupon;
	}

	/**
	 * @param codigoCupon The codigoCupon to set.
	 */
	public void setCodigoCupon(String codigoCupon) {
		this.codigoCupon = codigoCupon;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
}