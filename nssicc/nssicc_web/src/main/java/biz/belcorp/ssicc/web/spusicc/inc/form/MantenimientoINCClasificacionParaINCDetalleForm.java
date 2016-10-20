package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCClasificacionParaINCDetalleForm extends BaseEditForm implements Serializable {

	private static final long serialVersionUID = 9000917281186691500L;
	private Integer secuencialDetalle;
	private Integer secuencialCabecera;
	private Integer oidSubtipocliente;
	private Integer oidTipocliente;
	private Integer oidTipoClasificacion;
	private Integer oidClasificacion;
	
	private String codigoSubtipocliente;
	private String codigoTipocliente;
	private String codigoTipoClasificacion;
	private String codigoClasificacion;
	
	private String descripcionSubtipocliente;
	private String descripcionTipocliente;
	private String descripcionTipoClasificacion;
	private String descripcionClasificacion;
	
	private String  descripcion;

	/**
	 * @return the secuencialDetalle
	 */
	public Integer getSecuencialDetalle() {
		return secuencialDetalle;
	}

	/**
	 * @param secuencialDetalle the secuencialDetalle to set
	 */
	public void setSecuencialDetalle(Integer secuencialDetalle) {
		this.secuencialDetalle = secuencialDetalle;
	}

	/**
	 * @return the secuencialCabecera
	 */
	public Integer getSecuencialCabecera() {
		return secuencialCabecera;
	}

	/**
	 * @param secuencialCabecera the secuencialCabecera to set
	 */
	public void setSecuencialCabecera(Integer secuencialCabecera) {
		this.secuencialCabecera = secuencialCabecera;
	}

	/**
	 * @return the oidSubtipocliente
	 */
	public Integer getOidSubtipocliente() {
		return oidSubtipocliente;
	}

	/**
	 * @param oidSubtipocliente the oidSubtipocliente to set
	 */
	public void setOidSubtipocliente(Integer oidSubtipocliente) {
		this.oidSubtipocliente = oidSubtipocliente;
	}

	/**
	 * @return the oidTipocliente
	 */
	public Integer getOidTipocliente() {
		return oidTipocliente;
	}

	/**
	 * @param oidTipocliente the oidTipocliente to set
	 */
	public void setOidTipocliente(Integer oidTipocliente) {
		this.oidTipocliente = oidTipocliente;
	}

	/**
	 * @return the oidTipoClasificacion
	 */
	public Integer getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(Integer oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return the oidClasificacion
	 */
	public Integer getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(Integer oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the codigoSubtipocliente
	 */
	public String getCodigoSubtipocliente() {
		return codigoSubtipocliente;
	}

	/**
	 * @param codigoSubtipocliente the codigoSubtipocliente to set
	 */
	public void setCodigoSubtipocliente(String codigoSubtipocliente) {
		this.codigoSubtipocliente = codigoSubtipocliente;
	}

	/**
	 * @return the codigoTipocliente
	 */
	public String getCodigoTipocliente() {
		return codigoTipocliente;
	}

	/**
	 * @param codigoTipocliente the codigoTipocliente to set
	 */
	public void setCodigoTipocliente(String codigoTipocliente) {
		this.codigoTipocliente = codigoTipocliente;
	}

	/**
	 * @return the codigoTipoClasificacion
	 */
	public String getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}

	/**
	 * @param codigoTipoClasificacion the codigoTipoClasificacion to set
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
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

	/**
	 * @return the descripcionSubtipocliente
	 */
	public String getDescripcionSubtipocliente() {
		return descripcionSubtipocliente;
	}

	/**
	 * @param descripcionSubtipocliente the descripcionSubtipocliente to set
	 */
	public void setDescripcionSubtipocliente(String descripcionSubtipocliente) {
		this.descripcionSubtipocliente = descripcionSubtipocliente;
	}

	/**
	 * @return the descripcionTipocliente
	 */
	public String getDescripcionTipocliente() {
		return descripcionTipocliente;
	}

	/**
	 * @param descripcionTipocliente the descripcionTipocliente to set
	 */
	public void setDescripcionTipocliente(String descripcionTipocliente) {
		this.descripcionTipocliente = descripcionTipocliente;
	}

	/**
	 * @return the descripcionTipoClasificacion
	 */
	public String getDescripcionTipoClasificacion() {
		return descripcionTipoClasificacion;
	}

	/**
	 * @param descripcionTipoClasificacion the descripcionTipoClasificacion to set
	 */
	public void setDescripcionTipoClasificacion(String descripcionTipoClasificacion) {
		this.descripcionTipoClasificacion = descripcionTipoClasificacion;
	}

	/**
	 * @return the descripcionClasificacion
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}

	/**
	 * @param descripcionClasificacion the descripcionClasificacion to set
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}