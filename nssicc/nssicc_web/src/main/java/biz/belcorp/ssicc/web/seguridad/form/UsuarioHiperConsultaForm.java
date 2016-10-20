/**
 * 
 */
package biz.belcorp.ssicc.web.seguridad.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * @author Sigcomt
 *
 */
public class UsuarioHiperConsultaForm extends BaseEditForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6525215063598273709L;
	
	protected boolean selected;

    protected String codigoPais;
    
    protected String codigoUsuarioBloqueo;

    protected String codigoOpcionConsulta;

    protected String nombreOpcionConsulta;
   
    protected String estado;

    public UsuarioHiperConsultaForm() {
        this.selected = false;
    }

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
