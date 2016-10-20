package biz.belcorp.ssicc.reportes.web.edu.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ReporteEDUGenerarPlanillaProgramacionProcesoForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 * @struts.form name = "reporteEDUGenerarPlanillaProgramacionProcesoForm"
 */
public class ReporteEDUGenerarPlanillaProgramacionProcesoForm extends BaseReporteForm  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8367856512628008091L;

	private String codigoPais;
	
	private String codigoEmpresa;
	
	private String codigoProcesoBatch;
	
	private String[] regiones;
	
	private String tipoProceso;
	
	private String campanhaProceso;
	
	
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
	 * @return Returns the codigoProcesoBatch.
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch The codigoProcesoBatch to set.
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	/**
	 * @return Returns the tipoProceso.
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}

	/**
	 * @param tipoProceso The tipoProceso to set.
	 * @struts.validator type = "required" 
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	/**
	 * @return Returns the campanhaProceso.
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}

	/**
	 * @param campanhaProceso The campanhaProceso to set.
	 * @struts.validator type = "required" 
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}

	
	
		
}
