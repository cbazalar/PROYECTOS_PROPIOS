/**
 * 
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazMAEEnviarConsultorasBloqueadasDesbloquedasForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 *  
 * @struts.form name = "interfazMAEEnviarConsultorasBloqueadasDesbloquedasForm" extends ="baseInterfazForm"	
 */
public class InterfazMAEEnviarConsultorasBloqueadasDesbloquedasForm extends BaseInterfazForm {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] codigoInterfacesEmpaquetadas;
    private String codigoIdioma;
    private String fechaInicio;
    private String fechaFin;
    private Date fechaInicioD;
    private Date fechaFinD;
    
	/**
	 * @return the codigoInterfacesEmpaquetadas
	 */
	public String[] getCodigoInterfacesEmpaquetadas() {
		return codigoInterfacesEmpaquetadas;
	}
	/**
	 * @param codigoInterfacesEmpaquetadas the codigoInterfacesEmpaquetadas to set
	 */
	public void setCodigoInterfacesEmpaquetadas(String[] codigoInterfacesEmpaquetadas) {
		this.codigoInterfacesEmpaquetadas = codigoInterfacesEmpaquetadas;
	}
	/**
	 * @return the codigoIdioma
	 */
	public String getCodigoIdioma() {
		return codigoIdioma;
	}
	/**
	 * @param codigoIdioma the codigoIdioma to set
	 */
	public void setCodigoIdioma(String codigoIdioma) {
		this.codigoIdioma = codigoIdioma;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio The fechaInicio to set.
	 * @param fechaInicio New value of property fechaInicio.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict" value="${defaultDatePattern}" 
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/** 
	 * @param fechaFin New value of property fechaFin.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict" value="${defaultDatePattern}" 
	*/
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	/**
	 * @return the fechaInicioD
	 */
	public Date getFechaInicioD() {
		return fechaInicioD;
	}
	/**
	 * @param fechaInicioD the fechaInicioD to set
	 */
	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}
	/**
	 * @return the fechaFinD
	 */
	public Date getFechaFinD() {
		return fechaFinD;
	}
	/**
	 * @param fechaFinD the fechaFinD to set
	 */
	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public InterfazMAEEnviarConsultorasBloqueadasDesbloquedasForm() {
		this.fechaInicioD = new Date();
		this.fechaFinD = new Date();
	}
	
}
