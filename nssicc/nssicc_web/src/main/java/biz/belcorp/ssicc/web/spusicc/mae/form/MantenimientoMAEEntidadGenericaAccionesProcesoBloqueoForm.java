package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oid;
	private String codigoPais;
	private String nombreEntidad;
	private String oidTipoBloqueo;
	private String oidProcesoBloqueo;
	private String oidAccionBloqueo;
	private String indicadorEstado;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * 
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
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the oidTipoBloqueo
	 */
	public String getOidTipoBloqueo() {
		return oidTipoBloqueo;
	}

	/**
	 * @param oidTipoBloqueo the oidTipoBloqueo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setOidTipoBloqueo(String oidTipoBloqueo) {
		this.oidTipoBloqueo = oidTipoBloqueo;
	}

	/**
	 * @return the oidProcesoBloqueo
	 */
	public String getOidProcesoBloqueo() {
		return oidProcesoBloqueo;
	}

	/**
	 * @param oidProcesoBloqueo the oidProcesoBloqueo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setOidProcesoBloqueo(String oidProcesoBloqueo) {
		this.oidProcesoBloqueo = oidProcesoBloqueo;
	}

	/**
	 * @return the oidAccionBloqueo
	 */
	public String getOidAccionBloqueo() {
		return oidAccionBloqueo;
	}

	/**
	 * @param oidAccionBloqueo the oidAccionBloqueo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setOidAccionBloqueo(String oidAccionBloqueo) {
		this.oidAccionBloqueo = oidAccionBloqueo;
	}

	/**
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	/**
	 * @param indicadorEstado the indicadorEstado to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}
}