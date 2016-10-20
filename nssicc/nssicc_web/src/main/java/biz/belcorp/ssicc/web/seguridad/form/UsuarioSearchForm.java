/*
 * Created on 20/04/2005 06:26:33 PM
 * biz.belcorp.ssicc.web.form.UsuarioSearchForm
 */
package biz.belcorp.ssicc.web.seguridad.form;

import javax.validation.constraints.Size;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UsuarioSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a> 
 */
public class UsuarioSearchForm extends BaseSearchForm {
    
	private static final long serialVersionUID = -4433685871122408368L;

	/**
     * Holds value of property codigoUsuario.
     */
    protected String codigoUsuario;

    /**
     * Holds value of property loginUsuario.
     */
    protected String loginUsuario;

    /**
     * Holds value of property nombresUsuario.
     */
    protected String nombresUsuario;

    /**
     * Holds value of property apellidosUsuario.
     */
    protected String apellidosUsuario;

    /**
     * Holds value of property estadoUsuario.
     */
    protected String estadoUsuario;

    /**
     * Holds value of property codigoPaisUsuario.
     */
    protected String codigoPaisUsuario;

    /**
     * Holds value of property correoElectronicoUsuario.
     */
    protected String correoElectronicoUsuario;
    
    private String nroRCRBloqueo;
    private String nroRCRDesbloqueo;
    private String nroRCREliminacion;
    

    /** Default empty constructor. */
    public UsuarioSearchForm() {
    }

    /**
     * @return Returns the apellidosUsuario.
     */
    @Size(max = 40)
    public String getApellidosUsuario() {
        return this.apellidosUsuario;
    }

    /**
     * @return Returns the codigoPaisUsuario.
     */
    public String getCodigoPaisUsuario() {
        return this.codigoPaisUsuario;
    }

    /**
     * @return Returns the codigoUsuario.
     */
    public String getCodigoUsuario() {
        return this.codigoUsuario;
    }

    /**
     * @return Returns the correoElectronicoUsuario.
     */
    public String getCorreoElectronicoUsuario() {
        return this.correoElectronicoUsuario;
    }

    /**
     * @return Returns the estadoUsuario.
     */
    public String getEstadoUsuario() {
        return this.estadoUsuario;
    }

    /**
     * @return Returns the loginUsuario.
     */
    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    /**
     * @return Returns the nombresUsuario.
     */
    @Size(max = 40)
    public String getNombresUsuario() {
        return this.nombresUsuario;
    }

    /**
     * @param apellidosUsuario
     *            The apellidosUsuario to set.
     */
    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    /**
     * @param codigoPaisUsuario
     *            The codigoPaisUsuario to set.
     */
    public void setCodigoPaisUsuario(String codigoPaisUsuario) {
        this.codigoPaisUsuario = codigoPaisUsuario;
    }

    /**
     * @param codigoUsuario
     *            The codigoUsuario to set.
     */
    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * @param correoElectronicoUsuario
     *            The correoElectronicoUsuario to set.
     */
    public void setCorreoElectronicoUsuario(String correoElectronicoUsuario) {
        this.correoElectronicoUsuario = correoElectronicoUsuario;
    }

    /**
     * @param estadoUsuario
     *            The estadoUsuario to set.
     */
    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    /**
     * @param loginUsuario
     *            The loginUsuario to set.
     */
    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    /**
     * @param nombresUsuario
     *            The nombresUsuario to set.
     */
    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

	/**
	 * @return the nroRCRBloqueo
	 */
	public String getNroRCRBloqueo() {
		return nroRCRBloqueo;
	}

	/**
	 * @param nroRCRBloqueo the nroRCRBloqueo to set
	 */
	public void setNroRCRBloqueo(String nroRCRBloqueo) {
		this.nroRCRBloqueo = nroRCRBloqueo;
	}

	/**
	 * @return the nroRCRDesbloqueo
	 */
	public String getNroRCRDesbloqueo() {
		return nroRCRDesbloqueo;
	}

	/**
	 * @param nroRCRDesbloqueo the nroRCRDesbloqueo to set
	 */
	public void setNroRCRDesbloqueo(String nroRCRDesbloqueo) {
		this.nroRCRDesbloqueo = nroRCRDesbloqueo;
	}

	/**
	 * @return the nroRCREliminacion
	 */
	public String getNroRCREliminacion() {
		return nroRCREliminacion;
	}

	/**
	 * @param nroRCREliminacion the nroRCREliminacion to set
	 */
	public void setNroRCREliminacion(String nroRCREliminacion) {
		this.nroRCREliminacion = nroRCREliminacion;
	}
    
    
}
