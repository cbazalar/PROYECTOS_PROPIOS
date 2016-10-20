/**
 * 
 */
package biz.belcorp.ssicc.web.seguridad.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * @author Sigcomt
 *
 */
public class UsuarioOpcionHiperConsultaForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9084406423832191193L;
	
	protected String codigoPais;
	protected String codigoUsuarioBloqueo;
	
	protected String nombrePais;
	protected String loginUsuario;

	protected String codigoOpcionConsulta;
	protected String nombreOpcionConsulta;
	
	protected UsuarioHiperConsultaForm[] opcionHiperConsultaForm;
	protected UsuarioHiperConsultaForm[] opcionHiperConsultaEliminarForm;
	
	
	public UsuarioOpcionHiperConsultaForm() {
		this.opcionHiperConsultaForm = new UsuarioHiperConsultaForm[0];
    	this.opcionHiperConsultaEliminarForm = new UsuarioHiperConsultaForm[0];
    }


	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return the codigoUsuarioBloqueo
	 */
	public String getCodigoUsuarioBloqueo() {
		return codigoUsuarioBloqueo;
	}


	/**
	 * @param codigoUsuarioBloqueo the codigoUsuarioBloqueo to set
	 */
	public void setCodigoUsuarioBloqueo(String codigoUsuarioBloqueo) {
		this.codigoUsuarioBloqueo = codigoUsuarioBloqueo;
	}


	/**
	 * @return the nombrePais
	 */
	public String getNombrePais() {
		return nombrePais;
	}


	/**
	 * @param nombrePais the nombrePais to set
	 */
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}


	/**
	 * @return the loginUsuario
	 */
	public String getLoginUsuario() {
		return loginUsuario;
	}


	/**
	 * @param loginUsuario the loginUsuario to set
	 */
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}


	/**
	 * @return the codigoOpcionConsulta
	 */
	public String getCodigoOpcionConsulta() {
		return codigoOpcionConsulta;
	}


	/**
	 * @param codigoOpcionConsulta the codigoOpcionConsulta to set
	 */
	public void setCodigoOpcionConsulta(String codigoOpcionConsulta) {
		this.codigoOpcionConsulta = codigoOpcionConsulta;
	}


	/**
	 * @return the nombreOpcionConsulta
	 */
	public String getNombreOpcionConsulta() {
		return nombreOpcionConsulta;
	}


	/**
	 * @param nombreOpcionConsulta the nombreOpcionConsulta to set
	 */
	public void setNombreOpcionConsulta(String nombreOpcionConsulta) {
		this.nombreOpcionConsulta = nombreOpcionConsulta;
	}


	/**
	 * @return the opcionHiperConsultaForm
	 */
	public UsuarioHiperConsultaForm[] getOpcionHiperConsultaForm() {
		return opcionHiperConsultaForm;
	}


	/**
	 * @param opcionHiperConsultaForm the opcionHiperConsultaForm to set
	 */
	public void setOpcionHiperConsultaForm(
			UsuarioHiperConsultaForm[] opcionHiperConsultaForm) {
		this.opcionHiperConsultaForm = opcionHiperConsultaForm;
	}


	/**
	 * @return the opcionHiperConsultaEliminarForm
	 */
	public UsuarioHiperConsultaForm[] getOpcionHiperConsultaEliminarForm() {
		return opcionHiperConsultaEliminarForm;
	}


	/**
	 * @param opcionHiperConsultaEliminarForm the opcionHiperConsultaEliminarForm to set
	 */
	public void setOpcionHiperConsultaEliminarForm(
			UsuarioHiperConsultaForm[] opcionHiperConsultaEliminarForm) {
		this.opcionHiperConsultaEliminarForm = opcionHiperConsultaEliminarForm;
	}

}
