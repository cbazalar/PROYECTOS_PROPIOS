/*
 * Created on 20/04/2005 10:06:46 AM biz.belcorp.ssicc.web.form.RolSearchForm
 */
package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="RolSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class RolSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = -7148154773671793396L;

	/**
     * Holds value of property codigoPaisRol.
     */
    protected String codigoPaisRol;

    /**
     * Holds value of property codigoRol.
     */
    protected String codigoRol;

    /**
     * Holds value of property descripcionRol.
     */
    protected String descripcionRol;

    /**
     * Holds value of property estadoRol.
     */
    protected String estadoRol;

    /** Default empty constructor. */
    public RolSearchForm() {
    }

    /**
     * @return Returns the codigoPaisRol.
     */   
    public String getCodigoPaisRol() {
        return codigoPaisRol;
    }

    /**
     * @param codigoPaisRol
     *            The codigoPaisRol to set.
     */
    public void setCodigoPaisRol(String codigoPaisRol) {
        this.codigoPaisRol = codigoPaisRol;
    }

    /**
     * @return Returns the codigoRol.
     */
    @Size(max = 6)
    public String getCodigoRol() {
        return codigoRol;
    }

    /**
     * @param codigoRol
     *            The codigoRol to set.
     */
    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    /**
     * @return Returns the descripcionRol.
     */
    @Size(max = 40)
    public String getDescripcionRol() {
        return descripcionRol;
    }

    /**
     * @param descripcionRol
     *            The descripcionRol to set.
     */
    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    /**
     * @return Returns the estadoRol.
     */
    public String getEstadoRol() {
        return estadoRol;
    }

    /**
     * @param estadoRol
     *            The estadoRol to set.
     */
    public void setEstadoRol(String estadoRol) {
        this.estadoRol = estadoRol;
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
/*    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }*/

}
