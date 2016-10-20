package biz.belcorp.ssicc.web.sisicc.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarFacturacionGenexusForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 * 
 * @struts.form name = "interfazAPEEnviarFacturacionGenexusForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazAPEEnviarFacturacionGenexusForm extends
        BaseInterfazForm {

    /**
     * Holds value of property codigoPeriodo.
     */
    protected String codigoPeriodo;  
    
    private String fechaFacturacion;
    
    private Date fechaFact;

    /**
     * @return the codigoPeriodo
     */
    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    /**
     * @param codigoPeriodo the codigoPeriodo to set
     * @struts.validator type="required"
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
	 * @struts.validator type="required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	private String tipoProceso;

	/**
	 * @return the tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}

	/**
	 * @param tipoProceso the tipoProceso to set
	 * @struts.validator type="required"
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	public Date getFechaFact() {
		return fechaFact;
	}

	public void setFechaFact(Date fechaFact) {
		this.fechaFact = fechaFact;
	}
}
