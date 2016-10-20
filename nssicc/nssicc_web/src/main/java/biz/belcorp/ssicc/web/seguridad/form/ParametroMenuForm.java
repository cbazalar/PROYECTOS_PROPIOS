/*
 * Created on 15/11/2005 09:00:37 AM
 *
 * biz.belcorp.ssicc.web.form.ParametroMenuForm
 */
package biz.belcorp.ssicc.web.seguridad.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ParametroMenuForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class ParametroMenuForm extends BaseForm {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5576670344750276110L;

	/**
     * Holds value of property selected.
     */
    protected boolean selected;

    /**
     * Holds value of property codigoMenu.
     */
    protected String codigoMenu;

    /**
     * Holds value of property numero.
     */
    protected String numero;

    /**
     * Holds value of property nombre.
     */
    protected String nombre;

    /**
     * Holds value of property valor.
     */
    protected String valor;

    /**
     * Holds value of property estado.
     */
    protected String estado;

    /** Default empty constructor. */
    public ParametroMenuForm() {
        selected = false;
    }

    /**
     * @return Returns the codigoMenu.
     */
    public String getCodigoMenu() {
        return codigoMenu;
    }
    /**
     * @param codigoMenu The codigoMenu to set.
     */
    public void setCodigoMenu(String codigoMenu) {
        this.codigoMenu = codigoMenu;
    }
    /**
     * @return Returns the estado.
     */
    public String getEstado() {
        return estado;
    }
    /**
     * @param estado The estado to set.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * @return Returns the nombre.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param nombre The nombre to set.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return Returns the numero.
     */
    public String getNumero() {
        return numero;
    }
    /**
     * @param numero The numero to set.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     * @return Returns the selected.
     */
    public boolean isSelected() {
        return selected;
    }
    /**
     * @param selected The selected to set.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    /**
     * @return Returns the valor.
     */
    public String getValor() {
        return valor;
    }
    /**
     * @param valor The valor to set.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /*
     * To add non XDoclet-generated methods, create a file named
     * actionform-methods-MenuForm.java containing the additional code and place
     * it in your merge dir.
     */
    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
        selected = false;
    } */

}
