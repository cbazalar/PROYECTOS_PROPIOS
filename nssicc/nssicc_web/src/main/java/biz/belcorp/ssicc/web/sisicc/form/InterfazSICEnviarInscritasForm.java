package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazSICEnviarInscritasForm extends BaseInterfazForm
		implements Serializable {

	private static final long serialVersionUID = -9144269533025696300L;

	private String codigoMarca;

	private String codigoCanal;

	private String codigoPeriodo;

	private String codigoPais;
	
	private String codigoTipoCliente;
	
	private String []codigoSubTipoCliente;
	
	private String []codigoTipoClasificacion;
	
	private String []codigoClasificacion;
	
	private String codigoTipoVinculo;

	/**
	 * @return
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm#getCodigoPeriodo()
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoClasificacion.
	 */
	public String[] getCodigoClasificacion() {
		return codigoClasificacion;
	}

	/**
	 * @param codigoClasificacion The codigoClasificacion to set.
	 */
	public void setCodigoClasificacion(String[] codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

	/**
	 * @return Returns the codigoSubTipoCliente.
	 * 
	 */
	public String[] getCodigoSubTipoCliente() {
		return codigoSubTipoCliente;
	}

	/**
	 * @param codigoSubTipoCliente The codigoSubTipoCliente to set.
	 */
	public void setCodigoSubTipoCliente(String[] codigoSubTipoCliente) {
		this.codigoSubTipoCliente = codigoSubTipoCliente;
	}

	/**
	 * @return Returns the codigoTipoClasificacion.
	 */
	public String[] getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}

	/**
	 * @param codigoTipoClasificacion The codigoTipoClasificacion to set.
	 */
	public void setCodigoTipoClasificacion(String[] codigoTipoClasificacion) {
		this.codigoTipoClasificacion = codigoTipoClasificacion;
	}

	/**
	 * @return Returns the codigoTipoCliente.
	 */
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}

	/**
	 * @param codigoTipoCliente The codigoTipoCliente to set.
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}

	/**
	 * @return Returns the codigoTipoVinculo.
	 */
	public String getCodigoTipoVinculo() {
		return codigoTipoVinculo;
	}

	/**
	 * @param codigoTipoVinculo The codigoTipoVinculo to set.
	 */
	public void setCodigoTipoVinculo(String codigoTipoVinculo) {
		this.codigoTipoVinculo = codigoTipoVinculo;
	}
}