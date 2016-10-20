package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazMLBEnviarInformacionIncentivosForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 * @struts.form name = "interfazMLBEnviarInformacionIncentivosForm" extends = "BaseInterfazPaqueteForm"
 */
public class InterfazMLBEnviarInformacionIncentivosForm extends BaseInterfazForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionD;
	private String codigoPaqueteInterfaz;
	private String habilitar;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
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

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 * @struts.validator type = "required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the codigoPaqueteInterfaz
	 */
	public String getCodigoPaqueteInterfaz() {
		return codigoPaqueteInterfaz;
	}

	/**
	 * @param codigoPaqueteInterfaz the codigoPaqueteInterfaz to set
	 */
	public void setCodigoPaqueteInterfaz(String codigoPaqueteInterfaz) {
		this.codigoPaqueteInterfaz = codigoPaqueteInterfaz;
	}

	/**
	 * @return the habilitar
	 */
	public String getHabilitar() {
		return habilitar;
	}

	/**
	 * @param habilitar the habilitar to set
	 */
	public void setHabilitar(String habilitar) {
		this.habilitar = habilitar;
	}

	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	
	
}