package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCPuntajeExigidoForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2255353776507070142L;
	private String codigoPais;
	private String nivel;
	private String numeroConcurso;
	private String codigoPeriodo;
	private String puntajeExigido;
	
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
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
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
	 * @return the puntajeExigido
	 */
	public String getPuntajeExigido() {
		return puntajeExigido;
	}

	/**
	 * @param puntajeExigido the puntajeExigido to set
	 */
	public void setPuntajeExigido(String puntajeExigido) {
		this.puntajeExigido = puntajeExigido;
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
