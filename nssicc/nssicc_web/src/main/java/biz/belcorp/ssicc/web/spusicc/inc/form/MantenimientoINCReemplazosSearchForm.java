package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoINCReemplazosSearchForm extends BaseSearchForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2924582709921310092L;
	private String codigoPais;
	private String oidConcurso;
	private String concurso;
	
	private String [] selectedItemsPremios;
	private String [] selectedItemsReemplazos;
	
	
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
	 * @return the oidConcurso
	 */
	public String getOidConcurso() {
		return oidConcurso;
	}
	/**
	 * @param oidConcurso the oidConcurso to set
	 */
	public void setOidConcurso(String oidConcurso) {
		this.oidConcurso = oidConcurso;
	}
	/**
	 * @return the concurso
	 */
	public String getConcurso() {
		return concurso;
	}
	/**
	 * @param concurso the concurso to set
	 */
	public void setConcurso(String concurso) {
		this.concurso = concurso;
	}
	/**
	 * @return the selectedItemsPremios
	 */
	public String[] getSelectedItemsPremios() {
		return selectedItemsPremios;
	}
	/**
	 * @param selectedItemsPremios the selectedItemsPremios to set
	 */
	public void setSelectedItemsPremios(String[] selectedItemsPremios) {
		this.selectedItemsPremios = selectedItemsPremios;
	}
	/**
	 * @return the selectedItemsReemplazos
	 */
	public String[] getSelectedItemsReemplazos() {
		return selectedItemsReemplazos;
	}
	/**
	 * @param selectedItemsReemplazos the selectedItemsReemplazos to set
	 */
	public void setSelectedItemsReemplazos(String[] selectedItemsReemplazos) {
		this.selectedItemsReemplazos = selectedItemsReemplazos;
	}
	
	

}
