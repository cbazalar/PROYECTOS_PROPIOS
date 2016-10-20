package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazEVIEnviarEjecutivoVirtualForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 27/11/2014
 */
public class InterfazEVIEnviarEjecutivoVirtualForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;
	
	private String codigoNuevoPeriodo;
	
	private boolean cruceCampanya;
	
	private String codigoPais;
		
	private String[] codigoInterfacesEmpaquetadas;
	

	/**
	 * @return Returns the codigoInterfacesEmpaquetadas.
	 */
	public String[] getCodigoInterfacesEmpaquetadas() {
		return codigoInterfacesEmpaquetadas;
	}
	
	/**
	 * @param codigoInterfacesEmpaquetadas
	 *            The codigoInterfacesEmpaquetadas to set.
	 */
	public void setCodigoInterfacesEmpaquetadas(
			String[] codigoInterfacesEmpaquetadas) {
		this.codigoInterfacesEmpaquetadas = codigoInterfacesEmpaquetadas;
	}
	
	/**
	 * @return Returns the codigoNuevoPeriodo.
	 */
	public String getCodigoNuevoPeriodo() {
		return codigoNuevoPeriodo;
	}


	/**
	 * @param codigoNuevoPeriodo The codigoNuevoPeriodo to set.
	 */
	public void setCodigoNuevoPeriodo(String codigoNuevoPeriodo) {
		this.codigoNuevoPeriodo = codigoNuevoPeriodo;
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}


	/**
	 * @return Returns the cruceCampanya.
	 */
	public boolean isCruceCampanya() {
		return cruceCampanya;
	}


	/**
	 * @param cruceCampanya The cruceCampanya to set.
	 */
	public void setCruceCampanya(boolean cruceCampanya) {
		this.cruceCampanya = cruceCampanya;
	}


}
