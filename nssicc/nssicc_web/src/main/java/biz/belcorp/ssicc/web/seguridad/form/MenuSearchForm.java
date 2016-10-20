/*
 * Created on 20/04/2005 03:50:33 PM
 * biz.belcorp.privilege.web.form.MenuSearchForm
 */
package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MenuSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class MenuSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = -6801817978681991297L;

	/**
     * Holds value of property codigoMenu.
     */
    protected String codigoMenu;

    /**
     * Holds value of property codigoPadreMenu.
     */
    protected String codigoPadreMenu;

    /**
     * Holds value of property descripcionMenu.
     */
    protected String descripcionMenu;

    /**
     * Holds value of property descripcionMenuPadre.
     */
    protected String descripcionMenuPadre;

    /**
     * Holds value of property accionMenu.
     */
    protected String accionMenu;

    /**
     * Holds value of property nivelMenu.
     */
    protected String nivelMenu;

    /**
     * Holds value of property posicionMenu.
     */
    protected String posicionMenu;

    /**
     * Holds value of property tipoOcultoMenu.
     */
    protected boolean tipoOcultoMenu;

    /** Default empty constructor. */
    public MenuSearchForm() {
    }

    /**
     * @return Returns the accionMenu.
     */
    public String getAccionMenu() {
        return accionMenu;
    }

    /**
     * @param accionMenu
     *            The accionMenu to set.
     */
    public void setAccionMenu(String accionMenu) {
        this.accionMenu = accionMenu;
    }

    /**
     * @return Returns the codigoMenu.
     */
    @Size(max = 8)
    public String getCodigoMenu() {
        return codigoMenu;
    }

    /**
     * @param codigoMenu
     *            The codigoMenu to set.
     */
    public void setCodigoMenu(String codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    /**
     * @return Returns the codigoPadreMenu.
     */
    public String getCodigoPadreMenu() {
        return codigoPadreMenu;
    }

    /**
     * @param codigoPadreMenu
     *            The codigoPadreMenu to set.
     */
    public void setCodigoPadreMenu(String codigoPadreMenu) {
        this.codigoPadreMenu = codigoPadreMenu;
    }

    /**
     * @return Returns the descripcionMenu.
     */
    @Size(max = 100)
    public String getDescripcionMenu() {
        return descripcionMenu;
    }

    /**
     * @param descripcionMenu
     *            The descripcionMenu to set.
     */
    public void setDescripcionMenu(String descripcionMenu) {
        this.descripcionMenu = descripcionMenu;
    }

    /**
     * @return Returns the descripcionMenuPadre.
     */
    @Size(max = 100)
    public String getDescripcionMenuPadre() {
        return descripcionMenuPadre;
    }

    /**
     * @param descripcionMenuPadre
     *            The descripcionMenuPadre to set.
     */
    public void setDescripcionMenuPadre(String descripcionMenuPadre) {
        this.descripcionMenuPadre = descripcionMenuPadre;
    }

    /**
     * @return Returns the nivelMenu.
     */
    @Size(max = 2)
    public String getNivelMenu() {
        return nivelMenu;
    }

    /**
     * @param nivelMenu
     *            The nivelMenu to set.
     */
    public void setNivelMenu(String nivelMenu) {
        this.nivelMenu = nivelMenu;
    }

    /**
     * @return Returns the posicionMenu.
     */
    public String getPosicionMenu() {
        return posicionMenu;
    }

    /**
     * @param posicionMenu
     *            The posicionMenu to set.
     */
    public void setPosicionMenu(String posicionMenu) {
        this.posicionMenu = posicionMenu;
    }

    /**
     * @return Returns the tipoOcultoMenu.
     */
    public boolean isTipoOcultoMenu() {
        return tipoOcultoMenu;
    }

    /**
     * @param tipoOcultoMenu
     *            The tipoOcultoMenu to set.
     */
    public void setTipoOcultoMenu(boolean tipoOcultoMenu) {
        this.tipoOcultoMenu = tipoOcultoMenu;
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    /*public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

        this.tipoOcultoMenu = false;

    }*/

}
