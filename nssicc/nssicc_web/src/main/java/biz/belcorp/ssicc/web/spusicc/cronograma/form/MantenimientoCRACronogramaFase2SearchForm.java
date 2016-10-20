package biz.belcorp.ssicc.web.spusicc.cronograma.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCRACronogramaFase2SearchForm extends BaseSearchForm {



	/**
	 * 
	 */
	private static final long serialVersionUID = -212758274372685743L;
	private String codigoPais;
	private String codigoPeriodo;
	private String[] grupoZonaList;
	private String[] zonaList;
	private String name;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the grupozonaList
	 */
	public String[] getGrupoZonaList() {
		return grupoZonaList;
	}

	/**
	 * @param grupozonaList
	 *            the grupozonaList to set
	 */
	public void setGrupoZonaList(String[] grupoZonaList) {
		this.grupoZonaList = grupoZonaList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}


	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
}
