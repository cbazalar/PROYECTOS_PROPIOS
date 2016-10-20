package biz.belcorp.ssicc.web.spusicc.sicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoSICCUsuarioForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 06/02/2015
 */
public class MantenimientoSICCUsuarioForm extends BaseEditForm {
			
	private static final long serialVersionUID = -8709797587095058694L;

	private String codigoPais;

    private String oid;

    private String login;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    private String primerNombre;
    
    private String segundoNombre;
        
    private String email;
    
    private String telefono;
    
    private String codigoUsuario;
    
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 * @struts.validator type = "required"
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 * @struts.validator type = "required"
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 * @struts.validator type = "required"
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @param primerNombre the primerNombre to set
	 * @struts.validator type = "required"
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @param segundoNombre the segundoNombre to set
	 * @struts.validator type = "required"
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 * @struts.validator type = "required"
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 * @struts.validator type = "required"
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}


}
