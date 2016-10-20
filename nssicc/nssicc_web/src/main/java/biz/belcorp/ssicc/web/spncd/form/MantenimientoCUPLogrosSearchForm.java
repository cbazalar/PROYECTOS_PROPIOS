package biz.belcorp.ssicc.web.spncd.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCUPLogrosSearchForm extends BaseSearchForm implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoPeriodo;
	private String codigoConsultora;
	private String descripcionConsultora;
	private String codigoTipoLogro;
	private String estado;

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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora
	 *            the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the descripcionConsultora
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}

	/**
	 * @param descripcionConsultora
	 *            the descripcionConsultora to set
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}

	/**
	 * @return the codigoTipoLogro
	 */
	public String getCodigoTipoLogro() {
		return codigoTipoLogro;
	}

	/**
	 * @param codigoTipoLogro
	 *            the codigoTipoLogro to set
	 */
	public void setCodigoTipoLogro(String codigoTipoLogro) {
		this.codigoTipoLogro = codigoTipoLogro;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
}