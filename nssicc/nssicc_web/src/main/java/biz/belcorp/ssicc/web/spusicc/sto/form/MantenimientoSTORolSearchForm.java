package biz.belcorp.ssicc.web.spusicc.sto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoSTORolSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 11/02/2015
 */
public class MantenimientoSTORolSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = -30633847333714934L;

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
    public MantenimientoSTORolSearchForm() {
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

}
