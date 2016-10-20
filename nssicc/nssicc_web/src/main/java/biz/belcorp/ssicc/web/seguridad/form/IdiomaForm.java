/*
 * Created on 21/11/2005 11:47:27 AM biz.belcorp.ssicc.web.form.IdiomaForm
 */
package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="IdiomaForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class IdiomaForm extends BaseEditForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1593330480959809235L;

	/**
     * Holds value of property codigo.
     */
    protected String codigo;

    /**
     * Holds value of property codigoISO.
     */
    protected String codigoISO;

    /**
     * Holds value of property codigoSiCC.
     */
    private String codigoSiCC;

    /**
     * Holds value of property descripcion.
     */
    protected String descripcion;

    /**
     * Holds value of property estado.
     */
    protected String estado;

    /** Default empty constructor. */
    public IdiomaForm() {
    }

    /**
     * @return Returns the codigo.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            The codigo to set.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return Returns the codigoISO.
     */
    @Size(max = 8)
    public String getCodigoISO() {
        return codigoISO;
    }

    /**
     * @param codigoISO
     *            The codigoISO to set.
     */
    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    /**
     * @return Returns the codigoSiCC.
     */
    @Size(max = 2)
    public String getCodigoSiCC() {
        return codigoSiCC;
    }

    /**
     * @param codigoSiCC
     *            The codigoSiCC to set.
     */
    public void setCodigoSiCC(String codigoSiCC) {
        this.codigoSiCC = codigoSiCC;
    }

    /**
     * @return Returns the descripcion.
     */
    @Size(max = 40)
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            The descripcion to set.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return Returns the estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            The estado to set.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    } */

}
