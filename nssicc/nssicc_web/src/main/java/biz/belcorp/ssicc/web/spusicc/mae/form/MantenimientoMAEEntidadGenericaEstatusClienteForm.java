package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaEstatusClienteForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oid;
	private String codigoPais;
	private String nombreEntidad;
	private String codigoTipoEstatus;
	private String codigo;
	private String descripcion;
	private String estatusPosteriorPosible;
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
	 * @return the codigoTipoEstatus
	 */
	public String getCodigoTipoEstatus() {
		return codigoTipoEstatus;
	}
	/**
	 * @param codigoTipoEstatus the codigoTipoEstatus to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoTipoEstatus(String codigoTipoEstatus) {
		this.codigoTipoEstatus = codigoTipoEstatus;
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
	 * @return the estatusPosteriorPosible
	 */
	public String getEstatusPosteriorPosible() {
		return estatusPosteriorPosible;
	}
	/**
	 * @param estatusPosteriorPosible the estatusPosteriorPosible to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setEstatusPosteriorPosible(String estatusPosteriorPosible) {
		this.estatusPosteriorPosible = estatusPosteriorPosible;
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