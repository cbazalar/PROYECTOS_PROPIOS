package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaTipoEstatusClienteForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String oidEstatus;
	private String codigo;
	private String marca;
	private String descripcion;
	private String codigoEntidad;
	private String codigoPais;
	private String nombreEntidad;
		
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
	 * @return the oidEstatus
	 */
	public String getOidEstatus() {
		return oidEstatus;
	}



	/**
	 * @param oidEstatus the oidEstatus to set
	 */
	public void setOidEstatus(String oidEstatus) {
		this.oidEstatus = oidEstatus;
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
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}



	/**
	 * @param marca the marca to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}



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
	 * @return the codigoEntidad
	 */
	public String getCodigoEntidad() {
		return codigoEntidad;
	}



	/**
	 * @param codigoEntidad the codigoEntidad to set
	 */
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
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
}