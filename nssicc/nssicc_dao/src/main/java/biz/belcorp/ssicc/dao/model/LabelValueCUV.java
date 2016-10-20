package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

/**
 * A simple JavaBean to represent label-value pairs. This is most commonly used
 * when constructing user interface elements which have a label to be displayed
 * to the user, and a corresponding value to be returned to the server. One
 * example is the <code>&lt;html:options&gt;</code> tag. <p/><p/>Note: this
 * class has a natural ordering that is inconsistent with equals.
 * </p>
 * 
 * @see org.apache.struts.util.LabelValueBean
 */
public class LabelValueCUV implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -313851994656863583L;

	public LabelValueCUV() {
        super();
    }

    /**
     * Construct an instance with the supplied property values.
     * 
     * @param label
     *            The label to be displayed to the user.
     * @param value
     *            The value to be returned to the server.
     */
    public LabelValueCUV(String label, String value, String precio, String oferta) {
        this.label = label;
        this.value = value;
        this.precio = precio;
        this.oferta = oferta;
    }

    // ------------------------------------------------------------- Properties

    /**
     * The property which supplies the option label visible to the end user.
     */
    private String label = null;

    /**
     * 
     * @uml.property name="label"
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * 
     * @uml.property name="label"
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * The property which supplies the value returned to the server.
     */
    private String value = null;

    /**
     * 
     * @uml.property name="value"
     */
    public String getValue() {
        return this.value;
    }

    /**
     * 
     * @uml.property name="value"
     */
    public void setValue(String value) {
        this.value = value;
    }

    private String precio = null;

	
	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	private String oferta = null;

	public String getOferta() {
		return oferta;
	}

	public void setOferta(String oferta) {
		this.oferta = oferta;
	}

	
	private String catalogo;
	private String pagina;
	private String error;

	/**
	 * @return the catalogo
	 */
	public String getCatalogo() {
		return catalogo;
	}

	/**
	 * @param catalogo the catalogo to set
	 */
	public void setCatalogo(String catalogo) {
		this.catalogo = catalogo;
	}

	/**
	 * @return the pagina
	 */
	public String getPagina() {
		return pagina;
	}

	/**
	 * @param pagina the pagina to set
	 */
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	
	

	
}