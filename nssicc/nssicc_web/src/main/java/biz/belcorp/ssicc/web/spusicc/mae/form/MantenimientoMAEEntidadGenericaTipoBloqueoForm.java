package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaTipoBloqueoForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oid;
	private String codigoPais;
	private String nombreEntidad;
	private String codigo;
	private String descripcion;
	private String indicadorBloqueoFinanciero;
	private String oidMotivoRechazo;
	private String oidFormaBloqueo;
	private String oidFormaDesbloqueo;
	private String nivelGravedad;
	private String indicadorEstado;
			
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
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
	 * @return the indicadorBloqueoFinanciero
	 */
	public String getIndicadorBloqueoFinanciero() {
		return indicadorBloqueoFinanciero;
	}

	/**
	 * @param indicadorBloqueoFinanciero the indicadorBloqueoFinanciero to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setIndicadorBloqueoFinanciero(String indicadorBloqueoFinanciero) {
		this.indicadorBloqueoFinanciero = indicadorBloqueoFinanciero;
	}

	/**
	 * @return the oidMotivoRechazo
	 */
	public String getOidMotivoRechazo() {
		return oidMotivoRechazo;
	}

	/**
	 * @param oidMotivoRechazo the oidMotivoRechazo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setOidMotivoRechazo(String oidMotivoRechazo) {
		this.oidMotivoRechazo = oidMotivoRechazo;
	}

	/**
	 * @return the oidFormaBloqueo
	 */
	public String getOidFormaBloqueo() {
		return oidFormaBloqueo;
	}

	/**
	 * @param oidFormaBloqueo the oidFormaBloqueo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setOidFormaBloqueo(String oidFormaBloqueo) {
		this.oidFormaBloqueo = oidFormaBloqueo;
	}

	/**
	 * @return the oidFormaDesbloqueo
	 */
	public String getOidFormaDesbloqueo() {
		return oidFormaDesbloqueo;
	}

	/**
	 * @param oidFormaDesbloqueo the oidFormaDesbloqueo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setOidFormaDesbloqueo(String oidFormaDesbloqueo) {
		this.oidFormaDesbloqueo = oidFormaDesbloqueo;
	}

	/**
	 * @return the nivelGravedad
	 */
	public String getNivelGravedad() {
		return nivelGravedad;
	}

	/**
	 * @param nivelGravedad the nivelGravedad to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setNivelGravedad(String nivelGravedad) {
		this.nivelGravedad = nivelGravedad;
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