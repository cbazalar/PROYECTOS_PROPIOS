package biz.belcorp.ssicc.web.edu.form;

import java.io.Serializable;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoEDUEnvioCronogramaDictadoForm extends BaseProcesoForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String codigoPais;
	
	private String codigoEmpresa;
	
	private String[] regiones;
	
	private String codigoPeriodo;
	
	/**
	 * @return Returns the regiones.
	 */
	public String[] getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones The regiones to set.
	 * @struts.validator type = "required"  
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
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

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
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
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
}
