package biz.belcorp.ssicc.web.spusicc.mav.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoMAVConfiguracionSearchForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPeriodoBusq;
	private String actividadBusq;
	private String tipoOfertaBusq;
	private String codigoProductoBusq;
	private String descripcionProductoBusq;
	private String estadoBusq;
	private String indicadorOrigen;
	private String codigoTipoCliente;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoPeriodoBusq
	 */
	public String getCodigoPeriodoBusq() {
		return codigoPeriodoBusq;
	}
	/**
	 * @param codigoPeriodoBusq the codigoPeriodoBusq to set
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPeriodoBusq(String codigoPeriodoBusq) {
		this.codigoPeriodoBusq = codigoPeriodoBusq;
	}
	/**
	 * @return the actividadBusq
	 */
	public String getActividadBusq() {
		return actividadBusq;
	}
	/**
	 * @param actividadBusq the actividadBusq to set
	 */
	public void setActividadBusq(String actividadBusq) {
		this.actividadBusq = actividadBusq;
	}
	/**
	 * @return the tipoOfertaBusq
	 */
	public String getTipoOfertaBusq() {
		return tipoOfertaBusq;
	}
	/**
	 * @param tipoOfertaBusq the tipoOfertaBusq to set
	 */
	public void setTipoOfertaBusq(String tipoOfertaBusq) {
		this.tipoOfertaBusq = tipoOfertaBusq;
	}
	/**
	 * @return the codigoProductoBusq
	 */
	public String getCodigoProductoBusq() {
		return codigoProductoBusq;
	}
	/**
	 * @param codigoProductoBusq the codigoProductoBusq to set
	 */
	public void setCodigoProductoBusq(String codigoProductoBusq) {
		this.codigoProductoBusq = codigoProductoBusq;
	}
	/**
	 * @return the descripcionProductoBusq
	 */
	public String getDescripcionProductoBusq() {
		return descripcionProductoBusq;
	}
	/**
	 * @param descripcionProductoBusq the descripcionProductoBusq to set
	 */
	public void setDescripcionProductoBusq(String descripcionProductoBusq) {
		this.descripcionProductoBusq = descripcionProductoBusq;
	}

	/**
	 * @return the estadoBusq
	 */
	public String getEstadoBusq() {
		return estadoBusq;
	}
	/**
	 * @param estadoBusq the estadoBusq to set
	 */
	public void setEstadoBusq(String estadoBusq) {
		this.estadoBusq = estadoBusq;
	}
	/**
	 * @return the indicadorOrigen
	 */
	public String getIndicadorOrigen() {
		return indicadorOrigen;
	}
	/**
	 * @param indicadorOrigen the indicadorOrigen to set
	 */
	public void setIndicadorOrigen(String indicadorOrigen) {
		this.indicadorOrigen = indicadorOrigen;
	}
	
	/**
	 * @return the codigoTipoCliente
	 */
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}
	
	/**
	 * @param codigoTipoCliente the codigoTipoCliente to set
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}
	
}
