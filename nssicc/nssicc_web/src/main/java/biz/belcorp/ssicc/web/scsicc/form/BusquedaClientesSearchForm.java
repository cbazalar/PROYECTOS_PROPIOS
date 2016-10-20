package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class BusquedaClientesSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 30/12/2014
 */
public class BusquedaClientesSearchForm extends BaseSearchForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoCliente;	
    private String numeroDocIdentidad;	
    private String nombre1;	
    private String nombre2;	
	private String apellido1;	
	private String apellido2;
	private String criterioBusqueda1;
	private String criterioBusqueda2;
	private String codigoZona;
        

	/**
	 * @return codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}


	/**
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}


	/**
	 * @return numeroDocIdentidad
	 */
	public String getNumeroDocIdentidad() {
		return numeroDocIdentidad;
	}


	/**
	 * @param numeroDocIdentidad
	 */
	public void setNumeroDocIdentidad(String numeroDocIdentidad) {
		this.numeroDocIdentidad = numeroDocIdentidad;
	}


	/**
	 * @return nombre1
	 */
	public String getNombre1() {
		return nombre1;
	}


	/**
	 * @param nombre1
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}


	/**
	 * @return nombre2
	 */
	public String getNombre2() {
		return nombre2;
	}


	/**
	 * @param nombre2
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}


	/**
	 * @return apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}


	/**
	 * @param apellido1
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	/**
	 * @return apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}


	/**
	 * @param apellido2
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	/**
	 * @return criterioBusqueda1
	 */
	public String getCriterioBusqueda1() {
		return criterioBusqueda1;
	}


	/**
	 * @param criterioBusqueda1
	 */
	public void setCriterioBusqueda1(String criterioBusqueda1) {
		this.criterioBusqueda1 = criterioBusqueda1;
	}


	/**
	 * @return criterioBusqueda2
	 */
	public String getCriterioBusqueda2() {
		return criterioBusqueda2;
	}


	/**
	 * @param criterioBusqueda2
	 */
	public void setCriterioBusqueda2(String criterioBusqueda2) {
		this.criterioBusqueda2 = criterioBusqueda2;
	}

	/**
	 * @return codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

}
