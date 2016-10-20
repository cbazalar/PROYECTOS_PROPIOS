package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCRecomendadaPeriodoForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1482584584178485411L;
	private String codigoPais;
	private String numeroConcurso;
	private String indicadorTipo;
	private String secuencia;
	private String monto;
	
	private String numeroPedidosRecomendante;
	private String numeroPedidosRecomendada;
	
	private String[] selectedItems;

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
	 * @return the numeroConcurso
	 */
	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	/**
	 * @param numeroConcurso the numeroConcurso to set
	 */
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}

	/**
	 * @return the indicadorTipo
	 */
	public String getIndicadorTipo() {
		return indicadorTipo;
	}

	/**
	 * @param indicadorTipo the indicadorTipo to set
	 */
	public void setIndicadorTipo(String indicadorTipo) {
		this.indicadorTipo = indicadorTipo;
	}

	/**
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * @param secuencia the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * @return the numeroPedidosRecomendante
	 */
	public String getNumeroPedidosRecomendante() {
		return numeroPedidosRecomendante;
	}

	/**
	 * @param numeroPedidosRecomendante the numeroPedidosRecomendante to set
	 */
	public void setNumeroPedidosRecomendante(String numeroPedidosRecomendante) {
		this.numeroPedidosRecomendante = numeroPedidosRecomendante;
	}

	/**
	 * @return the numeroPedidosRecomendada
	 */
	public String getNumeroPedidosRecomendada() {
		return numeroPedidosRecomendada;
	}

	/**
	 * @param numeroPedidosRecomendada the numeroPedidosRecomendada to set
	 */
	public void setNumeroPedidosRecomendada(String numeroPedidosRecomendada) {
		this.numeroPedidosRecomendada = numeroPedidosRecomendada;
	}

	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	

}
