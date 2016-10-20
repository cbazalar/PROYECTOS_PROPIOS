package biz.belcorp.ssicc.web.seguridad.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoRolSICCForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class MantenimientoRolSICCSearchForm extends BaseSearchForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7752607892525398779L;

	/**
     * Holds value of property codigoPais.
     */
    protected String codigoPais;

    /**
     * Holds value of property codigo.
     */
    protected Integer oid;

    /**
     * Holds value of property descripcion.
     */
    protected String descripcion;


    /**
     * Holds value of property descripcionPais.
     */
    protected String descripcionPais;

    /** Default empty constructor. */
    public MantenimientoRolSICCSearchForm() {
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
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    
    
    /**
	 * @return the oid
	 */
	public Integer getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Integer oid) {
		this.oid = oid;
	}
}
