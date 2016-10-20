package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 * @struts.form name = "reporteZONListaUnidadesAdministrativasForm"
 */
public class ReporteZONListaUnidadesAdministrativasForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoSubGerencia;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"  
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoSubGerencia
	 */
	public String getCodigoSubGerencia() {
		return codigoSubGerencia;
	}

	/**
	 * @param codigoSubGerencia the codigoSubGerencia to set
	 * @struts.validator type = "required" 	  
	 */
	public void setCodigoSubGerencia(String codigoSubGerencia) {
		this.codigoSubGerencia = codigoSubGerencia;
	}

}
