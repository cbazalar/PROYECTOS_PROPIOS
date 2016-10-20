package biz.belcorp.ssicc.web.edu.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ReporteEDUPlanillasEmitidasForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ReporteEDUPlanillasEmitidasForm extends BaseReporteForm  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3107093945708239533L;

	private String codigoPais;
	
	private String codigoEmpresa;
	
	private String codigoRegion;
	
	private String campanhaProceso;
		
	private String codigoCurso;
	

	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.codigoPais="";
		this.codigoEmpresa="";
	}
	

	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
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
	 * @return Returns the campanhaProceso.
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}

	/**
	 * @param campanhaProceso The campanhaProceso to set.
	 * 
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}


	/**
	 * @return Returns the codigoCurso.
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}


	/**
	 * @param codigoCurso The codigoCurso to set.
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
		
}
