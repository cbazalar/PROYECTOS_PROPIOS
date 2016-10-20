package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoOperacionCDRUsuarioSearchForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String usuario;	
	private String nombres;
	private String apellidos;
	private String operacion;
	private String numeroFilas;
	
	private String [] selectedItems;

	public MantenimientoOperacionCDRUsuarioSearchForm(){
		this.numeroFilas = "25";
	}
		
	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return
	 */
	public String getOperacion() {
		return operacion;
	}
	/**
	 * @param operacion
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	/**
	 * @return
	 */
	public String getNombres() {
		return nombres;
	}
	/**
	 * @param nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the numeroFilas
	 */
	public String getNumeroFilas() {
		return numeroFilas;
	}

	/**
	 * @param numeroFilas the numeroFilas to set
	 */
	public void setNumeroFilas(String numeroFilas) {
		this.numeroFilas = numeroFilas;
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
