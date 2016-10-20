package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form del Enviar archivos de texto de Comisiones de la
 * Interfaz Ventas Retail.
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 * 
 * @struts.form name = "interfazRETEnviarComisionesForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazRETEnviarComisionesForm extends
		BaseInterfazForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -798795811501166876L;
	private String fechaInicio ;
	private String fechaFin ;	
	private Date fechaInicioD ;
	private Date fechaFinD ;	
	
	
    /*
     *  (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
  
        this.fechaInicioD = new Date();
        this.fechaFinD = new Date();
    }


	public String getFechaInicio() {
		return fechaInicio;
	}

    public String getFechaFin() {
		return fechaFin;
	}

    /**
     * @param fechaInicio The fechaInicio to set.
     * @param fechaInicio
     *            New value of property fechaInicio.
     * @struts.validator type="required"
     * @struts.validator type="date"
     * @struts.validator-var name="datePatternStrict"
     *                       value="${defaultDatePattern}"
     */

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

    /**
     * @param fechaFin The fechaFin to set.
     * @param fechaFin
     *            New value of property fechaFin.
     * @struts.validator type="required"
     * @struts.validator type="date"
     * @struts.validator-var name="datePatternStrict"
     *                       value="${defaultDatePattern}"
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



	
	

}