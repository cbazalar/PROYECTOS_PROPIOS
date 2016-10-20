package biz.belcorp.ssicc.web.spusicc.sto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoSTORolForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 11/02/2015
 */
public class MantenimientoSTORolForm extends BaseEditForm {

	private static final long serialVersionUID = -3540593669413985016L;

	/**
     * Holds value of property codigoPais.
     */
    protected String codigoPais;

    /**
     * Holds value of property codigo.
     */
    protected String codigo;

    /**
     * Holds value of property descripcion.
     */
    protected String descripcion;

    /**
     * Holds value of property estado.
     */
    protected String estado;

    /**
     * Holds value of property descripcionPais.
     */
    protected String descripcionPais;

    /** Default empty constructor. */
    public MantenimientoSTORolForm() {
    }

    /**
     * @return Returns the codigoPais.
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * @param codigoPais
     *            The codigoPais to set.
     * @struts.validator type="required"
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * Getter for property codigo.
     * 
     * @return Value of property codigo.
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Setter for property codigo.
     * 
     * @param codigo
     *            New value of property codigo.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Getter for property descripcion.
     * 
     * @return Value of property descripcion.
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Setter for property descripcion.
     * 
     * @param descripcion
     *            New value of property descripcion.
     * @struts.validator type="required"
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Getter for property estado.
     * 
     * @return Value of property estado.
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Setter for property estado.
     * 
     * @param estado
     *            New value of property estado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Getter for property descripcionPais.
     * 
     * @return Value of property descripcionPais.
     */
    public String getDescripcionPais() {
        return descripcionPais;
    }

    /**
     * Setter for property descripcionPais.
     * 
     * @param descripcionPais
     *            New value of property descripcionPais.
     */
    public void setDescripcionPais(String descripcionPais) {
        this.descripcionPais = descripcionPais;
    }

}
