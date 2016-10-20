package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCEstatusVentaForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4889740619498168817L;
	private String oidEstatus;
	private String codigoPeriodoInicial;
	private String codigoPeriodoFinal;
	private String[] selectedItems;
	
	private String codigoPeriodoFinVigencia; //periodo fin de Vigencia del Concurso

	/**
	 * @return the oidEstatus
	 */
	public String getOidEstatus() {
		return oidEstatus;
	}

	/**
	 * @param oidEstatus the oidEstatus to set
	 */
	public void setOidEstatus(String oidEstatus) {
		this.oidEstatus = oidEstatus;
	}

	/**
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial the codigoPeriodoInicial to set
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal the codigoPeriodoFinal to set
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
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

	/**
	 * @return the codigoPeriodoFinVigencia
	 */
	public String getCodigoPeriodoFinVigencia() {
		return codigoPeriodoFinVigencia;
	}

	/**
	 * @param codigoPeriodoFinVigencia the codigoPeriodoFinVigencia to set
	 */
	public void setCodigoPeriodoFinVigencia(String codigoPeriodoFinVigencia) {
		this.codigoPeriodoFinVigencia = codigoPeriodoFinVigencia;
	}
	
	

}
