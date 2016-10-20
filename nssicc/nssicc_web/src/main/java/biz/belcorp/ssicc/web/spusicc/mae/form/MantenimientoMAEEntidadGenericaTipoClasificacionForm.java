package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaTipoClasificacionForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oidTipoClasificacion;
	private String codigoPais;
	private String nombreEntidad;
	private String codigoTipoClienteCL;
	private String codigoTipoSubCliente;
	private String codigoTipoClasificacion;
	private String descripcionTipoClasificacion;
	private String tipoClasificacionPais;
	private String indicadorRegistro;
		
	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}
	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
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
	 * @return the codigoTipoClienteCL
	 */
	public String getCodigoTipoClienteCL() {
		return codigoTipoClienteCL;
	}
	/**
	 * @param codigoTipoClienteCL the codigoTipoClienteCL to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoTipoClienteCL(String codigoTipoClienteCL) {
		this.codigoTipoClienteCL = codigoTipoClienteCL;
	}
	
	/**
	 * @return the codigoTipoSubCliente
	 */
	public String getCodigoTipoSubCliente() {
		return codigoTipoSubCliente;
	}
	/**
	 * @param codigoTipoSubCliente the codigoTipoSubCliente to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoTipoSubCliente(String codigoTipoSubCliente) {
		this.codigoTipoSubCliente = codigoTipoSubCliente;
	}
	
	/**
	 * @return the codigoTipoClasificacion
	 */
	public String getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}
	/**
	 * @param codigoTipoClasificacion the codigoTipoClasificacion to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoTipoClasificacion(String codigoTipoClasificacion) {
		this.codigoTipoClasificacion = codigoTipoClasificacion;
	}
	
	/**
	 * @return the descripcionTipoClasificacion
	 */
	public String getDescripcionTipoClasificacion() {
		return descripcionTipoClasificacion;
	}
	/**
	 * @param descripcionTipoClasificacion the descripcionTipoClasificacion to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setDescripcionTipoClasificacion(String descripcionTipoClasificacion) {
		this.descripcionTipoClasificacion = descripcionTipoClasificacion;
	}
	
	/**
	 * @return the tipoClasificacionPais
	 */
	public String getTipoClasificacionPais() {
		return tipoClasificacionPais;
	}
	/**
	 * @param tipoClasificacionPais the tipoClasificacionPais to set
	 * @struts.validator type="required"
	 */
	public void setTipoClasificacionPais(String tipoClasificacionPais) {
		this.tipoClasificacionPais = tipoClasificacionPais;
	}
	
	/**
	 * @return the indicadorRegistro
	 */
	public String getIndicadorRegistro() {
		return indicadorRegistro;
	}
	/**
	 * @param indicadorRegistro the indicadorRegistro to set
	 * @struts.validator type="required"
	 */
	public void setIndicadorRegistro(String indicadorRegistro) {
		this.indicadorRegistro = indicadorRegistro;
	}
}