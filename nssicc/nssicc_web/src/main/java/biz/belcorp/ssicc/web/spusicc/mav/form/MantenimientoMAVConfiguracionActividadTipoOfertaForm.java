package biz.belcorp.ssicc.web.spusicc.mav.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAVConfiguracionActividadTipoOfertaForm  extends BaseEditForm  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String oidTipoOfer;
	private String codigoPais;
	private String tipoActividad;
	private String actividad;
	private String tipoOferta;
	private String estado;
	private String descripcionTipoOferta;
	private String descripcionTipoActividad;
	private String descripcionActividad;
	private String  codigoTOferta;
	private String codigo;
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the descripcionTipoOferta
	 */
	public String getDescripcionTipoOferta() {
		return descripcionTipoOferta;
	}
	/**
	 * @param descripcionTipoOferta the descripcionTipoOferta to set
	 */
	public void setDescripcionTipoOferta(String descripcionTipoOferta) {
		this.descripcionTipoOferta = descripcionTipoOferta;
	}
	/**
	 * @return the descripcionTipoActividad
	 */
	public String getDescripcionTipoActividad() {
		return descripcionTipoActividad;
	}
	/**
	 * @param descripcionTipoActividad the descripcionTipoActividad to set
	 */
	public void setDescripcionTipoActividad(String descripcionTipoActividad) {
		this.descripcionTipoActividad = descripcionTipoActividad;
	}
	/**
	 * @return the descripcionActividad
	 */
	public String getDescripcionActividad() {
		return descripcionActividad;
	}
	/**
	 * @param descripcionActividad the descripcionActividad to set
	 */
	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}
	/**
	 * @return the codigoTOferta
	 */
	public String getCodigoTOferta() {
		return codigoTOferta;
	}
	/**
	 * @param codigoTOferta the codigoTOferta to set
	 */
	public void setCodigoTOferta(String codigoTOferta) {
		this.codigoTOferta = codigoTOferta;
	}
	/**
	 * @return the oidTipoOfer
	 */
	public String getOidTipoOfer() {
		return oidTipoOfer;
	}
	/**
	 * @param oidTipoOfer the oidTipoOfer to set
	 */
	public void setOidTipoOfer(String oidTipoOfer) {
		this.oidTipoOfer = oidTipoOfer;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the tipoActividad
	 */
	public String getTipoActividad() {
		return tipoActividad;
	}
	/**
	 * @param tipoActividad the tipoActividad to set
	 * 
	 */
	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}
	/**
	 * @return the actividad
	 */
	public String getActividad() {
		return actividad;
	}
	/**
	 * @param actividad the actividad to set
	 * 
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	/**
	 * @return the tipoOferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}
	/**
	 * @param tipoOferta the tipoOferta to set
	 * 
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
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
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}	
}