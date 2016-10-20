/**
 * 
 */
package biz.belcorp.ssicc.web.seguridad.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UsuarioPermisoBloqueoForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes </a> 
 */

public class UsuarioPermisoBloqueoForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6233828582044309979L;
	
	protected String codigoPais;
	protected String codigoUsuarioBloqueo;
	
	protected String nombrePais;
	protected String loginUsuario;

	protected String codigoTipoBloqueo;
	protected String codigoAccion;

	protected String nombreTipoBloqueo;
	protected String nombreAccion;

	protected UsuarioBloqueoForm[] permisosBloqueoForm;
	protected UsuarioBloqueoForm[] permisosBloqueoEliminarForm;
	
	public UsuarioPermisoBloqueoForm() {
    	this.permisosBloqueoForm = new UsuarioBloqueoForm[0];
    	this.permisosBloqueoEliminarForm = new UsuarioBloqueoForm[0];
    }

	/**
	 * @return the codigoAccion
	 */
	public String getCodigoAccion() {
		return this.codigoAccion;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return this.codigoPais;
	}
	
	/**
	 * @return the codigoTipoBloqueo
	 */
	public String getCodigoTipoBloqueo() {
		return this.codigoTipoBloqueo;
	}

	/**
	 * @return the codigoUsuarioBloqueo
	 */
	public String getCodigoUsuarioBloqueo() {
		return this.codigoUsuarioBloqueo;
	}
	
	/**
	 * @return the loginUsuario
	 */
	public String getLoginUsuario() {
		return this.loginUsuario;
	}
	/**
	 * @return the nombreAccion
	 */
	public String getNombreAccion() {
		return this.nombreAccion;
	}
	
	/**
	 * @return the nombrePais
	 */
	public String getNombrePais() {
		return this.nombrePais;
	}

	/**
	 * @return the nombreTipoBloqueo
	 */
	public String getNombreTipoBloqueo() {
		return this.nombreTipoBloqueo;
	}

	/**
	 * @return the permisosBloqueoEliminarForm
	 */
	public UsuarioBloqueoForm[] getPermisosBloqueoEliminarForm() {
		return this.permisosBloqueoEliminarForm;
	}

	/**
	 * @return the permisosBloqueoForm
	 */
	public UsuarioBloqueoForm[] getPermisosBloqueoForm() {
		return this.permisosBloqueoForm;
	}

	/**
	 * @param codigoAccion the codigoAccion to set
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * @param codigoPais the codigoPais to set	 
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @param codigoTipoBloqueo the codigoTipoBloqueo to set
	 */
	public void setCodigoTipoBloqueo(String codigoTipoBloqueo) {
		this.codigoTipoBloqueo = codigoTipoBloqueo;
	}

	/**
	 * @param codigoUsuarioBloqueo the codigoUsuarioBloqueo to set
	 *	 
	 */
	public void setCodigoUsuarioBloqueo(String codigoUsuarioBloqueo) {
		this.codigoUsuarioBloqueo = codigoUsuarioBloqueo;
	}

	/**
	 * @param loginUsuario the loginUsuario to set
	 */
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	/**
	 * @param nombreAccion the nombreAccion to set
	 */
	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	/**
	 * @param nombrePais the nombrePais to set
	 */
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
    /**
	 * @param nombreTipoBloqueo the nombreTipoBloqueo to set
	 */
	public void setNombreTipoBloqueo(String nombreTipoBloqueo) {
		this.nombreTipoBloqueo = nombreTipoBloqueo;
	}

	/**
	 * @param permisosBloqueoEliminarForm the permisosBloqueoEliminarForm to set
	 */
	public void setPermisosBloqueoEliminarForm(
			UsuarioBloqueoForm[] permisosBloqueoEliminarForm) {
		this.permisosBloqueoEliminarForm = permisosBloqueoEliminarForm;
	}

	/**
	 * @param permisosBloqueoForm the permisosBloqueoForm to set
	 */
	public void setPermisosBloqueoForm(UsuarioBloqueoForm[] permisosBloqueoForm) {
		this.permisosBloqueoForm = permisosBloqueoForm;
	}

}
