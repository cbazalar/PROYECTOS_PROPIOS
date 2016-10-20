/**
 * 
 */
package biz.belcorp.ssicc.web.seguridad.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UsuarioBloqueoForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes </a> 
 */

public class UsuarioBloqueoForm extends BaseEditForm {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4541507783523466206L;

	protected boolean selected;

    protected String codigoPais;
    
    protected String codigoUsuarioBloqueo;

    protected String codigoTipoBloqueo;

    protected String codigoAccion;

    protected String nombreTipoBloqueo;

    protected String nombreAccion;
    
    protected String estado;

    public UsuarioBloqueoForm() {
        this.selected = false;
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
	 * @return the estado
	 */
	public String getEstado() {
		return this.estado;
	}

	/**
	 * @return the nombreAccion
	 */
	public String getNombreAccion() {
		return this.nombreAccion;
	}

	/**
	 * @return the nombreTipoBloqueo
	 */
	public String getNombreTipoBloqueo() {
		return this.nombreTipoBloqueo;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return this.selected;
	}

	/**
	 * @param codigoAccion the codigoAccion to set
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * @param codigoPais the codigoPais to set
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
	 */
	public void setCodigoUsuarioBloqueo(String codigoUsuarioBloqueo) {
		this.codigoUsuarioBloqueo = codigoUsuarioBloqueo;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @param nombreAccion the nombreAccion to set
	 */
	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	/**
	 * @param nombreTipoBloqueo the nombreTipoBloqueo to set
	 */
	public void setNombreTipoBloqueo(String nombreTipoBloqueo) {
		this.nombreTipoBloqueo = nombreTipoBloqueo;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
