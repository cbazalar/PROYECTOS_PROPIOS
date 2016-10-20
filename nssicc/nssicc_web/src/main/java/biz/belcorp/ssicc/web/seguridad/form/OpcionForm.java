package biz.belcorp.ssicc.web.seguridad.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class OpcionForm extends BaseEditForm {
	
	private static final long serialVersionUID = 4495372311886932071L;
	
	protected String codigoPais;
    protected String descripcionPais;
	protected String codigoOpcion;
	protected String descripcion;
	protected String estadoOpcion;
	
	
	public String getCodigoOpcion() {
		return codigoOpcion;
	}
	
	public void setCodigoOpcion(String codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
  /**
     * @param descripcion
     *            The descripcion to set.
  */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstadoOpcion() {
		return estadoOpcion;
	}

	  /**
     * @param estadoOpcion
     *            The estadoOpcion to set.
     */
	public void setEstadoOpcion(String estadoOpcion) {
		this.estadoOpcion = estadoOpcion;
	}

	public String getCodigoPais() {
		return codigoPais;
	}
	  /**
     * @param codigoPais
     *            The descripcion to set.
     */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDescripcionPais() {
		return descripcionPais;
	}

	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}
}
