package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 */
public class PaisForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7444022306267304872L;

	/**
     * Holds value of property codigo.
     */
    protected String codigo;

    /**
     * Holds value of property codigoPrivilege.
     */
    protected String codigoPrivilege;

    /**
     * Holds value of property URL.
     */
    private String URL;

    /**
     * Holds value of property descripcion.
     */
    protected String descripcion;

    /**
     * Holds value of property estado.
     */
    protected String estado;

    /**
     * Holds value of property longitudCodigoCliente.
     */
    protected long longitudCodigoCliente;

    /**
     * Holds value of property longitudCodigoInstructora.
     */
    protected long longitudCodigoInstructora;

    /** Default empty constructor. */
    public PaisForm() {
    }

    /**
     * Getter for property codigo.
     * 
     * @return Value of property codigo.
     */
    @Size(max = 3)
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
     * @return Returns the codigoPrivilege.
     */
    @Size(max = 2)
    public String getCodigoPrivilege() {
        return codigoPrivilege;
    }

    /**
     * @param codigoPrivilege
     *            The codigoPrivilege to set.     
     */
    public void setCodigoPrivilege(String codigoPrivilege) {
        this.codigoPrivilege = codigoPrivilege;
    }

    /**
     * @return Returns the uRL.
     */
    @Size(max = 100)
    public String getURL() {
        return URL;
    }

    /**
     * @param url
     *            The uRL to set.
     */
    public void setURL(String url) {
        URL = url;
    }

    /**
     * Getter for property descripcion.
     * 
     * @return Value of property descripcion.
     */
    @Size(max = 40)
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
     * @return Returns the longitudCodigoCliente.
     */    
    public long getLongitudCodigoCliente() {
        return longitudCodigoCliente;
    }

    /**
     * @param longitudCodigoCliente The longitudCodigoCliente to set.     
     */
    public void setLongitudCodigoCliente(long longitudCodigoCliente) {
        this.longitudCodigoCliente = longitudCodigoCliente;
    }

    /**
     * @return Returns the longitudCodigoInstructora.
     */    
    public long getLongitudCodigoInstructora() {
        return longitudCodigoInstructora;
    }

    /**
     * @param longitudCodigoInstructora The longitudCodigoInstructora to set.     
     */
    public void setLongitudCodigoInstructora(long longitudCodigoInstructora) {
        this.longitudCodigoInstructora = longitudCodigoInstructora;
    }

    /*
     * To add non XDoclet-generated methods, create a file named
     * actionform-methods-PaisForm.java containing the additional code and place
     * it in your merge dir.
     */
    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    /*
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }*/

}
