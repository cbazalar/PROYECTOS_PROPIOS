package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoINCClasificacionParaINCDetalleSearchForm extends BaseSearchForm implements Serializable {
	
	private static final long serialVersionUID = -7522108628190507489L;
	
	private Integer secuencialCabecera;
	private String descripcion;

	
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
	
	
	
}