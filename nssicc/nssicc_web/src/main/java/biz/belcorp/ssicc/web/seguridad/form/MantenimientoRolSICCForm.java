package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

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
public class MantenimientoRolSICCForm extends BaseEditForm {

	private static final long serialVersionUID = 7663505961917031661L;

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
    
    private Long[] listaAcceso= {};
    
    private String[] listaIndicador= {};
    
    private String codigoRol; 
    
    /** Default empty constructor. */
    public MantenimientoRolSICCForm() {
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
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }


    /**
     * Getter for property descripcion.
     * 
     * @return Value of property descripcion.
     */
    @Size(max=60)
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

	/**
	 * @return the listaAcceso
	 */
	public Long[] getListaAcceso() {
		return listaAcceso;
	}

	/**
	 * @param listaAcceso the listaAcceso to set
	 */
	public void setListaAcceso(Long[] listaAcceso) {
		this.listaAcceso = listaAcceso;
	}
	
	

	/**
	 * @return the listaIndicador
	 */
	public String[] getListaIndicador() {
		return listaIndicador;
	}

	/**
	 * @param listaIndicador the listaIndicador to set
	 */
	public void setListaIndicador(String[] listaIndicador) {
		this.listaIndicador = listaIndicador;
	}
	
	/**
	 * @return codigoRol
	 */
	public String getCodigoRol() {
		return codigoRol;
	}

	/**
	 * @param codigoRol the codigoRol to set
	 */
	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}
}
