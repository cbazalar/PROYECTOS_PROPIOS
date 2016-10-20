/*
 * Created on 21/11/2005 11:45:38 AM biz.belcorp.ssicc.web.form.IdiomaSearchForm
 */
package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="IdiomaSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a> 
 */

public class IdiomaSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = -8600631277169985870L;

	/**
     * Holds value of property codigoIdioma.
     */
    protected String codigoIdioma;

    /**
     * Holds value of property codigoISOIdioma.
     */
    protected String codigoISOIdioma;

    /**
     * Holds value of property codigoSiCCIdioma.
     */
    private String codigoSiCCIdioma;

    /**
     * Holds value of property descripcionIdioma.
     */
    protected String descripcionIdioma;

    /**
     * Holds value of property estadoIdioma.
     */
    protected String estadoIdioma;

    /**
     * @return Returns the codigoIdioma.
     */
    @Size(max = 2)
    public String getCodigoIdioma() {
        return codigoIdioma;
    }

    /**
     * @param codigoIdioma
     *            The codigoIdioma to set.
     */
    public void setCodigoIdioma(String codigoIdioma) {
        this.codigoIdioma = codigoIdioma;
    }

    /**
     * @return Returns the codigoISOIdioma.
     */
    public String getCodigoISOIdioma() {
        return codigoISOIdioma;
    }

    /**
     * @param codigoISOIdioma
     *            The codigoISOIdioma to set.
     */
    public void setCodigoISOIdioma(String codigoISOIdioma) {
        this.codigoISOIdioma = codigoISOIdioma;
    }

    /**
     * @return Returns the codigoSiCCIdioma.
     */
    public String getCodigoSiCCIdioma() {
        return codigoSiCCIdioma;
    }

    /**
     * @param codigoSiCCIdioma
     *            The codigoSiCCIdioma to set.
     */
    public void setCodigoSiCCIdioma(String codigoSiCCIdioma) {
        this.codigoSiCCIdioma = codigoSiCCIdioma;
    }

    /**
     * @return Returns the descripcionIdioma.
     */
    @Size(max = 100)
    public String getDescripcionIdioma() {
        return descripcionIdioma;
    }

    /**
     * @param descripcionIdioma
     *            The descripcionIdioma to set.
     */
    public void setDescripcionIdioma(String descripcionIdioma) {
        this.descripcionIdioma = descripcionIdioma;
    }

    /**
     * @return Returns the estadoIdioma.
     */
    public String getEstadoIdioma() {
        return estadoIdioma;
    }

    /**
     * @param estadoIdioma
     *            The estadoIdioma to set.
     */
    public void setEstadoIdioma(String estadoIdioma) {
        this.estadoIdioma = estadoIdioma;
    }
}
