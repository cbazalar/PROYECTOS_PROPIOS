package biz.belcorp.ssicc.web.edu.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoEDUActualizacionStatusConsultoraForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * @struts.form name = "procesoEDUActualizacionStatusConsultoraForm"
 */
public class ProcesoEDUActualizacionStatusConsultoraForm extends BaseProcesoForm  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String codigoPais;
	
	private String codigoEmpresa;
	
	private String[] empresas;
	
	private String[] regiones;
	
	private String campanhaProceso;
	
	private String proceso;

	private String descripcionProceso;
	
	

	/**
	 * @return the descripcionProceso
	 */
	public String getDescripcionProceso() {
		return descripcionProceso;
	}


	/**
	 * @param descripcionProceso the descripcionProceso to set
	 */
	public void setDescripcionProceso(String descripcionProceso) {
		this.descripcionProceso = descripcionProceso;
	}


	/**
	 * @return the proceso
	 */
	public String getProceso() {
		return proceso;
	}


	/**
	 * @param proceso the proceso to set
	 */
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}


	/**
	 * @return the empresas
	 */
	public String[] getEmpresas() {
		return empresas;
	}


	/**
	 * @param empresas the empresas to set
	 */
	public void setEmpresas(String[] empresas) {
		this.empresas = empresas;
	}


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
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}


	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}


	/**
	 * @return the regiones
	 */
	public String[] getRegiones() {
		return regiones;
	}


	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}


	/**
	 * @return the campanhaProceso
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}


	/**
	 * @param campanhaProceso the campanhaProceso to set
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}
	
	
}
