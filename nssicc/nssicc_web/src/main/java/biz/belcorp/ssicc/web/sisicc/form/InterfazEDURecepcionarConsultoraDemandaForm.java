package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form de la recepci�n de Consultoras A Demanda para la Interfaz EDU.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 * @struts.form name = "interfazEDURecepcionarConsultoraDemandaForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazEDURecepcionarConsultoraDemandaForm extends
		BaseInterfazForm implements Serializable {
	
	private String codigoEmpresa;
	private String campanha;
	private String codigoCurso;
	/**
	 * @return Returns the campanha.
	 */
	public String getCampanha() {
		return campanha;
	}
	/**
	 * @param campanha The campanha to set.
	 * @struts.validator type = "required"
	 */
	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}
	/**
	 * @return Returns the codigoCurso.
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}
	/**
	 * @param codigoCurso The codigoCurso to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	/**
	 * @return Returns the codigoEmpresa.
	 */
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
	
	
}