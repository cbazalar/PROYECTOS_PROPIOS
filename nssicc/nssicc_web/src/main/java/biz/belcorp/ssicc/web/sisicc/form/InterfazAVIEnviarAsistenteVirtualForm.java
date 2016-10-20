/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazAVIEnviarAsistenteVirtualForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 * @struts.form name = "interfazAVIEnviarAsistenteVirtualForm" extends = "baseInterfazForm"
 */
public class InterfazAVIEnviarAsistenteVirtualForm extends BaseInterfazForm
		implements Serializable {

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
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.cruceCampanya = false;

		
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
