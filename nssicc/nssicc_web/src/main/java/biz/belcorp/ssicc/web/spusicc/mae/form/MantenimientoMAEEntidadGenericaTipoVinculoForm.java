package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaTipoVinculoForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String nombreEntidad;
	private String oidTipoVinc;
	private String codigo;
	private String estado;
	private String recomendado;
	private String descripcion;
	
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}

	/**
	 * @param nombreEntidad the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	/**
	 * @return the oidTipoVinc
	 */
	public String getOidTipoVinc() {
		return oidTipoVinc;
	}

	/**
	 * @param oidTipoVinc the oidTipoVinc to set
	 */
	public void setOidTipoVinc(String oidTipoVinc) {
		this.oidTipoVinc = oidTipoVinc;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the recomendado
	 */
	public String getRecomendado() {
		return recomendado;
	}

	/**
	 * @param recomendado the recomendado to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setRecomendado(String recomendado) {
		this.recomendado = recomendado;
	}
}