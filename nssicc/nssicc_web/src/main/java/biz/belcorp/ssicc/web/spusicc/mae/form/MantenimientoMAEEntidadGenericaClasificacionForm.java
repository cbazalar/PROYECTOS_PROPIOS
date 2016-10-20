package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaClasificacionForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oidClasificacion;
	private String codigoPais;
	private String nombreEntidad;
	private String codigoTipoClienteCL;
	private String codigoTipoSubCliente;
	private String codigoTipoClasificacion;
	private String codigoClasificacion;
	private String descripcionClasificacion;
	private String indicadorRegistro;
	private String indicadorHiperconsulta;
	private String indicadorIVR;
	
	
	
	/**
	 * @return the indicadorIVR
	 */
	public String getIndicadorIVR() {
		return indicadorIVR;
	}

	/**
	 * @param indicadorIVR the indicadorIVR to set
	 */
	public void setIndicadorIVR(String indicadorIVR) {
		this.indicadorIVR = indicadorIVR;
	}

	/**
	 * @return the indicadorHiperconsulta
	 */
	public String getIndicadorHiperconsulta() {
		return indicadorHiperconsulta;
	}
	
	/**
	 * @param indicadorHiperconsulta the indicadorHiperconsulta to set
	 */
	public void setIndicadorHiperconsulta(String indicadorHiperconsulta) {
		this.indicadorHiperconsulta = indicadorHiperconsulta;
	}
	
	
	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}
	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
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
	 * @return the codigoClasificacion
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}
	/**
	 * @param codigoClasificacion the codigoClasificacion to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}
	/**
	 * @return the descripcionClasificacion
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}
	/**
	 * @param descripcionClasificacion the descripcionClasificacion to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
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