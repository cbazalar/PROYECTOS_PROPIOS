package biz.belcorp.ssicc.web.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class BusquedaUsuarioPOPUPSearchForm extends BaseSearchForm implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Holds value of property codigoUsuario.
     */
    protected String codigoUsuario;

    /**
     * Holds value of property loginUsuario.
     */
    protected String login;

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

    /**
     * @return Returns the apellidosUsuario.
     */
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
    public String getLogin() {
        return this.login;
    }

    /**
     * @return Returns the nombresUsuario.
     */
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
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @param nombresUsuario
     *            The nombresUsuario to set.
     */
    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }
}
