package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form de la recepci�n de Consultoras A Demanda para la Interfaz EDU.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 * @struts.form name = "interfazEDURecepcionarConsultoraEstablecidasForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazEDURecepcionarConsultoraEstablecidasForm extends
		BaseInterfazForm implements Serializable {
	
	private String codigoEmpresa;
	
	private String descripcion;
	
	private String[] listaEmpresa;
	
	
	
	/**
	 * @return the listaEmpresa
	 */
	public String[] getListaEmpresa() {
		return listaEmpresa;
	}
	/**
	 * @param listaEmpresa the listaEmpresa to set
	 */
	public void setListaEmpresa(String[] listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion The descripcion to set.
	 * @struts.validator type = "required"
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}