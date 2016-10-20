package biz.belcorp.ssicc.web._ejemplos.form;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * @author pecbazalar
 *
 */
public class MMantenimientoEjemploUsuarioForm extends BaseEditForm {

	private static final long serialVersionUID = 3399675672647899539L;
	
	/**
     * Holds value of property codigo.
     */
    protected String codigo;

    /**
     * Holds value of property login.
     */
    protected String login;

    /**
     * Holds value of property nombres.
     */
    protected String nombres;

    /**
     * Holds value of property apellidos.
     */
    protected String apellidos;

    /**
     * Holds value of property clave.
     */
    protected String clave;

    /**
     * Holds value of property confirmacionClave.
     */
    protected String confirmacionClave;

    /**
     * Holds value of property estado.
     */
    protected String estado;

    /**
     * Holds value of property codigoPais.
     */
    protected String codigoPais;

    /**
     * Holds value of property correoElectronico.
     */
    protected String correoElectronico;

    /**
     * Holds value of property claveTemporal.
     */
    protected boolean claveTemporal;

    /**
     * Holds value of property codigoIdioma.
     */
    protected String codigoIdioma;
    
    
    protected String codigoPaisRol;
   
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
     * Getter for property login.
     * 
     * @return Value of property login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Setter for property login.
     * 
     * @param login
     *            New value of property login.
     * @struts.validator type="required"
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter for property nombres.
     * 
     * @return Value of property nombres.
     */
    public String getNombres() {
        return this.nombres;
    }

    /**
     * Setter for property nombres.
     * 
     * @param nombres
     *            New value of property nombres.
     * @struts.validator type="required"
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Getter for property apellidos.
     * 
     * @return Value of property apellidos.
     */
    public String getApellidos() {
        return this.apellidos;
    }

    /**
     * Setter for property apellidos.
     * 
     * @param apellidos
     *            New value of property apellidos.
     * @struts.validator type="required"
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Getter for property clave.
     * 
     * @return Value of property clave.
     */
    public String getClave() {
        return this.clave;
    }

    /**
     * Setter for property clave.
     * 
     * @param clave
     *            New value of property clave.
     * @struts.validator type="required"
     * @struts.validator type="twofields"
     * @struts.validator-args arg1resource="usuarioForm.confirmacionClave"
     * @struts.validator-var name="secondProperty" value="confirmacionClave"
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Getter for property confirmacionClave.
     * 
     * @return Value of property confirmacionClave.
     */
    public String getConfirmacionClave() {
        return this.confirmacionClave;
    }

    /**
     * Setter for property confirmacionClave.
     * 
     * @param confirmacionClave
     *            New value of property confirmacionClave.
     * @struts.validator type="required"
     */
    public void setConfirmacionClave(String confirmacionClave) {
        this.confirmacionClave = confirmacionClave;
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
     * Getter for property codigoPais.
     * 
     * @return Value of property codigoPais.
     */
    public String getCodigoPais() {
        return this.codigoPais;
    }

    /**
     * Setter for property codigoPais.
     * 
     * @param codigoPais
     *            New value of property codigoPais.
     * @struts.validator type="required"
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * Getter for property correoElectronico.
     * 
     * @return Value of property correoElectronico.
     */
    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    /**
     * Setter for property correoElectronico.
     * 
     * @param correoElectronico
     *            New value of property correoElectronico.
     * @struts.validator type="email"
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return Returns the claveTemporal.
     */
    public boolean isClaveTemporal() {
        return this.claveTemporal;
    }

    /**
     * @param claveTemporal
     *            The claveTemporal to set.
     */
    public void setClaveTemporal(boolean claveTemporal) {
        this.claveTemporal = claveTemporal;
    }

    /**
     * @return Returns the codigoIdioma.
     */
    public String getCodigoIdioma() {
        return this.codigoIdioma;
    }

    /**
     * @param codigoIdioma
     *            The codigoIdioma to set.
     * @struts.validator type="required"
     */
    public void setCodigoIdioma(String codigoIdioma) {
        this.codigoIdioma = codigoIdioma;
    }

    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("login", this.login).append(
                "codigoPais", this.codigoPais).append(
                "apellidos", this.apellidos).append("correoElectronico", this.correoElectronico).append("codigoIdioma",
                this.codigoIdioma).append("clave", this.clave).append("confirmacionClave", this.confirmacionClave)
                .append("claveTemporal", this.claveTemporal)
                .append("estado", this.estado).append("editable", this.editable).append("newRecord", this.newRecord)
                .append("nombres", this.nombres).append("codigo",
                        this.codigo).toString();
    }

	/**
	 * @return the codigoPaisRol
	 */
	public String getCodigoPaisRol() {
		return this.codigoPaisRol;
	}

	/**
	 * @param codigoPaisRol the codigoPaisRol to set
	 */
	public void setCodigoPaisRol(String codigoPaisRol) {
		this.codigoPaisRol = codigoPaisRol;
	}
    
    
}
