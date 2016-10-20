package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPEnviarDocumentosMatricialesForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="cahurtado@belcorp.biz">Carlos Hurtado Ramirez </a>
 * 
 * @struts.form name = "interfazIMPEnviarDocumentosLaserForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazIMPEnviarDocumentosLaserForm extends
		BaseInterfazForm {

	private static final long serialVersionUID = 1L;

	/**
     * Holds value of property codigoPeriodo.
     */
    protected String codigoPeriodo;

    /**
     * Holds value of property fechaFacturacion.
     */
    protected String fechaFacturacion;

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

}
