package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteINCPagosConcursoForm extends BaseReporteForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPago;	
	private String codigoTipoAbono;


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
	 * @return the codigoPago
	 */
	public String getCodigoPago() {
		return codigoPago;
	}

	/**
	 * @param codigoPago the codigoPago to set
	 */
	public void setCodigoPago(String codigoPago) {
		this.codigoPago = codigoPago;
	}

	/**
	 * @return the codigoTipoAbono
	 */
	public String getCodigoTipoAbono() {
		return codigoTipoAbono;
	}

	/**
	 * @param codigoTipoAbono the codigoTipoAbono to set
	 */
	public void setCodigoTipoAbono(String codigoTipoAbono) {
		this.codigoTipoAbono = codigoTipoAbono;
	}

}
